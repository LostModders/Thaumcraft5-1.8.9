/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ public class TileTube
/*     */   extends TileThaumcraft implements IEssentiaTransport, IWandable, ITickable
/*     */ {
/*  37 */   public EnumFacing facing = EnumFacing.NORTH;
/*  38 */   public boolean[] openSides = { true, true, true, true, true, true };
/*  39 */   Aspect essentiaType = null;
/*  40 */   int essentiaAmount = 0;
/*  41 */   Aspect suctionType = null;
/*  42 */   int suction = 0;
/*  43 */   int venting = 0;
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  48 */     this.essentiaType = Aspect.getAspect(nbttagcompound.func_74779_i("type"));
/*  49 */     this.essentiaAmount = nbttagcompound.func_74762_e("amount");
/*     */     
/*  51 */     this.facing = EnumFacing.field_82609_l[nbttagcompound.func_74762_e("side")];
/*  52 */     byte[] sides = nbttagcompound.func_74770_j("open");
/*  53 */     if ((sides != null) && (sides.length == 6)) {
/*  54 */       for (int a = 0; a < 6; a++) {
/*  55 */         this.openSides[a] = (sides[a] == 1 ? 1 : false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  62 */     if (this.essentiaType != null) nbttagcompound.func_74778_a("type", this.essentiaType.getTag());
/*  63 */     nbttagcompound.func_74768_a("amount", this.essentiaAmount);
/*     */     
/*  65 */     byte[] sides = new byte[6];
/*  66 */     for (int a = 0; a < 6; a++) {
/*  67 */       sides[a] = (this.openSides[a] != 0 ? 1 : 0);
/*     */     }
/*  69 */     nbttagcompound.func_74768_a("side", this.facing.ordinal());
/*  70 */     nbttagcompound.func_74773_a("open", sides);
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/*  75 */     super.func_145839_a(nbttagcompound);
/*  76 */     this.suctionType = Aspect.getAspect(nbttagcompound.func_74779_i("stype"));
/*  77 */     this.suction = nbttagcompound.func_74762_e("samount");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  82 */     super.func_145841_b(nbttagcompound);
/*  83 */     if (this.suctionType != null) nbttagcompound.func_74778_a("stype", this.suctionType.getTag());
/*  84 */     nbttagcompound.func_74768_a("samount", this.suction);
/*     */   }
/*     */   
/*     */ 
/*  88 */   int count = 0;
/*     */   public static final int freq = 5;
/*  90 */   int ventColor = 0;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  95 */     if (this.venting > 0) this.venting -= 1;
/*  96 */     if (this.count == 0) this.count = this.field_145850_b.field_73012_v.nextInt(10);
/*  97 */     if (!this.field_145850_b.field_72995_K) {
/*  98 */       if (this.venting <= 0) {
/*  99 */         if (++this.count % 2 == 0) {
/* 100 */           calculateSuction(null, false, false);
/* 101 */           checkVenting();
/* 102 */           if ((this.essentiaType != null) && (this.essentiaAmount == 0)) this.essentiaType = null;
/*     */         }
/* 104 */         if ((this.count % 5 == 0) && (this.suction > 0)) {
/* 105 */           equalizeWithNeighbours(false);
/*     */         }
/*     */       }
/*     */     }
/* 109 */     else if (this.venting > 0) {
/* 110 */       Random r = new Random(hashCode() * 4);
/* 111 */       float rp = r.nextFloat() * 360.0F;
/* 112 */       float ry = r.nextFloat() * 360.0F;
/* 113 */       double fx = -MathHelper.func_76126_a(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F);
/* 114 */       double fz = MathHelper.func_76134_b(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F);
/* 115 */       double fy = -MathHelper.func_76126_a(rp / 180.0F * 3.1415927F);
/*     */       
/* 117 */       Thaumcraft.proxy.getFX().drawVentParticles(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, fx / 5.0D, fy / 5.0D, fx / 5.0D, this.ventColor);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void calculateSuction(Aspect filter, boolean restrict, boolean directional)
/*     */   {
/* 125 */     this.suction = 0;
/* 126 */     this.suctionType = null;
/* 127 */     EnumFacing loc = null;
/* 128 */     for (int dir = 0; dir < 6; dir++)
/*     */       try {
/* 130 */         loc = EnumFacing.field_82609_l[dir];
/* 131 */         if ((!directional) || (this.facing == loc.func_176734_d()))
/*     */         {
/* 133 */           if (isConnectable(loc)) {
/* 134 */             TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, loc);
/* 135 */             if (te != null) {
/* 136 */               IEssentiaTransport ic = (IEssentiaTransport)te;
/* 137 */               if ((filter != null) && (ic.getSuctionType(loc.func_176734_d()) != null) && (ic.getSuctionType(loc.func_176734_d()) != filter))
/*     */                 continue;
/* 139 */               if ((filter == null) && (getEssentiaAmount(loc) > 0) && (ic.getSuctionType(loc.func_176734_d()) != null) && (getEssentiaType(loc) != ic.getSuctionType(loc.func_176734_d()))) {
/*     */                 continue;
/*     */               }
/*     */               
/* 143 */               if ((filter != null) && (getEssentiaAmount(loc) > 0) && (getEssentiaType(loc) != null) && (ic.getSuctionType(loc.func_176734_d()) != null) && (getEssentiaType(loc) != ic.getSuctionType(loc.func_176734_d()))) {
/*     */                 continue;
/*     */               }
/*     */               
/* 147 */               int suck = ic.getSuctionAmount(loc.func_176734_d());
/* 148 */               if ((suck > 0) && (suck > this.suction + 1)) {
/* 149 */                 Aspect st = ic.getSuctionType(loc.func_176734_d());
/* 150 */                 if (st == null) st = filter;
/* 151 */                 setSuction(st, restrict ? suck / 2 : suck - 1);
/*     */               }
/*     */             }
/*     */           } }
/*     */       } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   void checkVenting() {
/* 159 */     EnumFacing loc = null;
/* 160 */     for (int dir = 0; dir < 6; dir++)
/*     */       try {
/* 162 */         loc = EnumFacing.field_82609_l[dir];
/* 163 */         if (isConnectable(loc)) {
/* 164 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, loc);
/* 165 */           if (te != null) {
/* 166 */             IEssentiaTransport ic = (IEssentiaTransport)te;
/* 167 */             int suck = ic.getSuctionAmount(loc.func_176734_d());
/* 168 */             if ((this.suction > 0) && ((suck == this.suction) || (suck == this.suction - 1)) && (this.suctionType != ic.getSuctionType(loc.func_176734_d())) && (!(te instanceof TileTubeFilter)))
/*     */             {
/* 170 */               int c = -1;
/* 171 */               if (this.suctionType != null) {
/* 172 */                 c = Config.aspectOrder.indexOf(this.suctionType);
/*     */               }
/* 174 */               this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.tube, 1, c);
/* 175 */               this.venting = 40;
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   void equalizeWithNeighbours(boolean directional) {
/* 183 */     EnumFacing loc = null;
/*     */     
/* 185 */     if (this.essentiaAmount > 0) { return;
/*     */     }
/* 187 */     for (int dir = 0; dir < 6; dir++) {
/*     */       try {
/* 189 */         loc = EnumFacing.field_82609_l[dir];
/* 190 */         if (((!directional) || (this.facing != loc.func_176734_d())) && 
/* 191 */           (isConnectable(loc)))
/*     */         {
/* 193 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, loc);
/* 194 */           if (te != null) {
/* 195 */             IEssentiaTransport ic = (IEssentiaTransport)te;
/* 196 */             if (!ic.canOutputTo(loc.func_176734_d()))
/*     */               continue;
/* 198 */             if (((getSuctionType(null) == null) || (getSuctionType(null) == ic.getEssentiaType(loc.func_176734_d())) || (ic.getEssentiaType(loc.func_176734_d()) == null)) && (getSuctionAmount(null) > ic.getSuctionAmount(loc.func_176734_d())) && (getSuctionAmount(null) >= ic.getMinimumSuction()))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/* 203 */               Aspect a = getSuctionType(null);
/* 204 */               if (a == null) {
/* 205 */                 a = ic.getEssentiaType(loc.func_176734_d());
/* 206 */                 if (a == null) { a = ic.getEssentiaType(null);
/*     */                 }
/*     */               }
/* 209 */               int am = addEssentia(a, ic.takeEssentia(a, 1, loc.func_176734_d()), loc);
/*     */               
/* 211 */               if (am > 0) {
/* 212 */                 if (this.field_145850_b.field_73012_v.nextInt(100) == 0)
/* 213 */                   this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.tube, 0, 0);
/* 214 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (Exception e) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isConnectable(EnumFacing face) {
/* 224 */     if (face == null) return false;
/* 225 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/* 230 */     if (face == null) return false;
/* 231 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/* 236 */     if (face == null) return false;
/* 237 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount)
/*     */   {
/* 242 */     this.suctionType = aspect;
/* 243 */     this.suction = amount;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing loc)
/*     */   {
/* 248 */     return this.suctionType;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc)
/*     */   {
/* 253 */     return this.suction;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/* 258 */     return this.essentiaType;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/* 263 */     return this.essentiaAmount;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 268 */     if ((canOutputTo(face)) && (this.essentiaType == aspect) && (this.essentiaAmount > 0) && (amount > 0)) {
/* 269 */       this.essentiaAmount -= 1;
/* 270 */       if (this.essentiaAmount <= 0) this.essentiaType = null;
/* 271 */       func_70296_d();
/* 272 */       return 1;
/*     */     }
/* 274 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 279 */     if ((canInputFrom(face)) && (this.essentiaAmount == 0) && (amount > 0)) {
/* 280 */       this.essentiaType = aspect;
/* 281 */       this.essentiaAmount += 1;
/* 282 */       func_70296_d();
/* 283 */       return 1;
/*     */     }
/* 285 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 290 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 297 */     if (i == 0)
/*     */     {
/* 299 */       if (this.field_145850_b.field_72995_K) {
/* 300 */         this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "thaumcraft:creak", 1.0F, 1.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/* 302 */       return true;
/*     */     }
/*     */     
/* 305 */     if (i == 1)
/*     */     {
/* 307 */       if (this.field_145850_b.field_72995_K) {
/* 308 */         if (this.venting <= 0)
/* 309 */           this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "random.fizz", 0.1F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, false);
/* 310 */         this.venting = 50;
/* 311 */         if ((j == -1) || (j >= Config.aspectOrder.size())) {
/* 312 */           this.ventColor = 11184810;
/*     */         } else {
/* 314 */           this.ventColor = ((Aspect)Config.aspectOrder.get(j)).getColor();
/*     */         }
/*     */       }
/* 317 */       return true;
/*     */     }
/*     */     
/* 320 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos bp, EnumFacing side)
/*     */   {
/* 329 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, player, this.field_174879_c);
/* 330 */     if (hit == null) { return false;
/*     */     }
/* 332 */     if ((hit.subHit >= 0) && (hit.subHit < 6))
/*     */     {
/* 334 */       player.field_70170_p.func_72980_b(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/* 335 */       player.func_71038_i();
/* 336 */       func_70296_d();
/* 337 */       world.func_175689_h(this.field_174879_c);
/* 338 */       this.openSides[hit.subHit] = (this.openSides[hit.subHit] == 0 ? 1 : false);
/* 339 */       EnumFacing dir = EnumFacing.field_82609_l[hit.subHit];
/* 340 */       TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(dir));
/* 341 */       if ((tile != null) && ((tile instanceof TileTube))) {
/* 342 */         ((TileTube)tile).openSides[dir.func_176734_d().ordinal()] = this.openSides[hit.subHit];
/* 343 */         world.func_175689_h(this.field_174879_c.func_177972_a(dir));
/* 344 */         tile.func_70296_d();
/*     */       }
/*     */     }
/*     */     
/* 348 */     if (hit.subHit == 6)
/*     */     {
/* 350 */       player.field_70170_p.func_72980_b(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/* 351 */       player.func_71038_i();
/* 352 */       int a = this.facing.ordinal();
/* 353 */       func_70296_d();
/* 354 */       do { a++; if (a >= 20) break;
/* 355 */       } while ((!canConnectSide(EnumFacing.field_82609_l[(a % 6)].func_176734_d())) || (!isConnectable(EnumFacing.field_82609_l[(a % 6)].func_176734_d())));
/*     */       
/* 357 */       a %= 6;
/* 358 */       this.facing = EnumFacing.field_82609_l[a];
/* 359 */       world.func_175689_h(this.field_174879_c);
/* 360 */       func_70296_d();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 366 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 386 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public MovingObjectPosition rayTrace(World world, Vec3 vec3d, Vec3 vec3d1, MovingObjectPosition fullblock)
/*     */   {
/* 393 */     return fullblock;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canConnectSide(EnumFacing side)
/*     */   {
/* 404 */     TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(side));
/* 405 */     return (tile != null) && ((tile instanceof IEssentiaTransport));
/*     */   }
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids)
/*     */   {
/* 410 */     float min = 0.4375F;
/* 411 */     float max = 0.5625F;
/* 412 */     if (canConnectSide(EnumFacing.DOWN)) cuboids.add(new IndexedCuboid6(Integer.valueOf(0), new Cuboid6(this.field_174879_c.func_177958_n() + min, this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p() + min, this.field_174879_c.func_177958_n() + max, this.field_174879_c.func_177956_o() + 0.40625D, this.field_174879_c.func_177952_p() + max)));
/* 413 */     if (canConnectSide(EnumFacing.UP)) cuboids.add(new IndexedCuboid6(Integer.valueOf(1), new Cuboid6(this.field_174879_c.func_177958_n() + min, this.field_174879_c.func_177956_o() + 0.59375D, this.field_174879_c.func_177952_p() + min, this.field_174879_c.func_177958_n() + max, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + max)));
/* 414 */     if (canConnectSide(EnumFacing.NORTH)) cuboids.add(new IndexedCuboid6(Integer.valueOf(2), new Cuboid6(this.field_174879_c.func_177958_n() + min, this.field_174879_c.func_177956_o() + min, this.field_174879_c.func_177952_p(), this.field_174879_c.func_177958_n() + max, this.field_174879_c.func_177956_o() + max, this.field_174879_c.func_177952_p() + 0.40625D)));
/* 415 */     if (canConnectSide(EnumFacing.SOUTH)) cuboids.add(new IndexedCuboid6(Integer.valueOf(3), new Cuboid6(this.field_174879_c.func_177958_n() + min, this.field_174879_c.func_177956_o() + min, this.field_174879_c.func_177952_p() + 0.59375D, this.field_174879_c.func_177958_n() + max, this.field_174879_c.func_177956_o() + max, this.field_174879_c.func_177952_p() + 1)));
/* 416 */     if (canConnectSide(EnumFacing.WEST)) cuboids.add(new IndexedCuboid6(Integer.valueOf(4), new Cuboid6(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + min, this.field_174879_c.func_177952_p() + min, this.field_174879_c.func_177958_n() + 0.40625D, this.field_174879_c.func_177956_o() + max, this.field_174879_c.func_177952_p() + max)));
/* 417 */     if (canConnectSide(EnumFacing.EAST)) { cuboids.add(new IndexedCuboid6(Integer.valueOf(5), new Cuboid6(this.field_174879_c.func_177958_n() + 0.59375D, this.field_174879_c.func_177956_o() + min, this.field_174879_c.func_177952_p() + min, this.field_174879_c.func_177958_n() + 1, this.field_174879_c.func_177956_o() + max, this.field_174879_c.func_177952_p() + max)));
/*     */     }
/* 419 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6(this.field_174879_c.func_177958_n() + 0.375D, this.field_174879_c.func_177956_o() + 0.375D, this.field_174879_c.func_177952_p() + 0.375D, this.field_174879_c.func_177958_n() + 0.625D, this.field_174879_c.func_177956_o() + 0.625D, this.field_174879_c.func_177952_p() + 0.625D)));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileTube.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */