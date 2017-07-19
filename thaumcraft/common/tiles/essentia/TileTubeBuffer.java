/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.common.tiles.devices.TileBellows;
/*     */ 
/*     */ 
/*     */ public class TileTubeBuffer
/*     */   extends TileTube
/*     */   implements IAspectContainer
/*     */ {
/*  28 */   public AspectList aspects = new AspectList();
/*  29 */   public final int MAXAMOUNT = 8;
/*  30 */   public byte[] chokedSides = { 0, 0, 0, 0, 0, 0 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  37 */     this.aspects.readFromNBT(nbttagcompound);
/*  38 */     byte[] sides = nbttagcompound.func_74770_j("open");
/*  39 */     if ((sides != null) && (sides.length == 6)) {
/*  40 */       for (int a = 0; a < 6; a++)
/*  41 */         this.openSides[a] = (sides[a] == 1 ? 1 : false);
/*     */     }
/*  43 */     this.chokedSides = nbttagcompound.func_74770_j("choke");
/*  44 */     if ((this.chokedSides == null) || (this.chokedSides.length < 6)) {
/*  45 */       this.chokedSides = new byte[] { 0, 0, 0, 0, 0, 0 };
/*     */     }
/*  47 */     this.facing = EnumFacing.field_82609_l[nbttagcompound.func_74762_e("side")];
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  53 */     this.aspects.writeToNBT(nbttagcompound);
/*  54 */     byte[] sides = new byte[6];
/*  55 */     for (int a = 0; a < 6; a++) {
/*  56 */       sides[a] = (this.openSides[a] != 0 ? 1 : 0);
/*     */     }
/*  58 */     nbttagcompound.func_74773_a("open", sides);
/*  59 */     nbttagcompound.func_74773_a("choke", this.chokedSides);
/*  60 */     nbttagcompound.func_74768_a("side", this.facing.ordinal());
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/*  66 */     return this.aspects;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  74 */     if (am != 1) return am;
/*  75 */     if (this.aspects.visSize() < 8) {
/*  76 */       this.aspects.add(tt, am);
/*  77 */       func_70296_d();
/*  78 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/*  79 */       return 0;
/*     */     }
/*  81 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  86 */     if (this.aspects.getAmount(tt) >= am) {
/*  87 */       this.aspects.remove(tt, am);
/*  88 */       func_70296_d();
/*  89 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/*  90 */       return true;
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/*  97 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/* 103 */     return this.aspects.getAmount(tag) >= amt;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot) {
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag) {
/* 111 */     return this.aspects.getAmount(tag);
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 116 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/* 124 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/* 129 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/* 134 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 142 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing loc)
/*     */   {
/* 147 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc)
/*     */   {
/* 152 */     return (this.bellows <= 0) || (this.chokedSides[loc.ordinal()] == 1) ? 1 : this.chokedSides[loc.ordinal()] == 2 ? 0 : this.bellows * 32;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/* 157 */     return this.aspects.size() > 0 ? this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.getAspects().length)] : null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/* 162 */     return this.aspects.visSize();
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 167 */     if (!canOutputTo(face)) return 0;
/* 168 */     TileEntity te = null;
/* 169 */     IEssentiaTransport ic = null;
/* 170 */     int suction = 0;
/* 171 */     te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, face);
/* 172 */     if (te != null) {
/* 173 */       ic = (IEssentiaTransport)te;
/* 174 */       suction = ic.getSuctionAmount(face.func_176734_d());
/*     */     }
/* 176 */     for (EnumFacing dir : EnumFacing.field_82609_l)
/* 177 */       if ((canOutputTo(dir)) && (dir != face)) {
/* 178 */         te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, dir);
/* 179 */         if (te != null) {
/* 180 */           ic = (IEssentiaTransport)te;
/* 181 */           int sa = ic.getSuctionAmount(dir.func_176734_d());
/* 182 */           Aspect su = ic.getSuctionType(dir.func_176734_d());
/* 183 */           if (((su == aspect) || (su == null)) && (suction < sa) && (getSuctionAmount(dir) < sa)) return 0;
/*     */         }
/*     */       }
/* 186 */     if (amount > this.aspects.getAmount(aspect)) amount = this.aspects.getAmount(aspect);
/* 187 */     return takeFromContainer(aspect, amount) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 192 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/* 195 */   int count = 0;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/* 199 */     this.count += 1;
/* 200 */     if ((this.bellows < 0) || (this.count % 20 == 0)) {
/* 201 */       getBellows();
/*     */     }
/* 203 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 205 */       if (this.count % 5 == 0) { getClass(); if (this.aspects.visSize() < 8) {
/* 206 */           fillBuffer();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void fillBuffer() {
/* 213 */     TileEntity te = null;
/* 214 */     IEssentiaTransport ic = null;
/* 215 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 216 */       te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, dir);
/* 217 */       if (te != null) {
/* 218 */         ic = (IEssentiaTransport)te;
/*     */         
/* 220 */         if ((ic.getEssentiaAmount(dir.func_176734_d()) > 0) && (ic.getSuctionAmount(dir.func_176734_d()) < getSuctionAmount(dir)) && (getSuctionAmount(dir) >= ic.getMinimumSuction()))
/*     */         {
/*     */ 
/*     */ 
/* 224 */           Aspect ta = ic.getEssentiaType(dir.func_176734_d());
/* 225 */           addToContainer(ta, ic.takeEssentia(ta, 1, dir.func_176734_d()));
/*     */           
/* 227 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 235 */   int bellows = -1;
/*     */   
/*     */   public void getBellows() {
/* 238 */     this.bellows = TileBellows.getBellows(this.field_145850_b, this.field_174879_c, EnumFacing.field_82609_l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos bp, EnumFacing side)
/*     */   {
/* 246 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, player, this.field_174879_c);
/* 247 */     if (hit == null) { return false;
/*     */     }
/* 249 */     if ((hit.subHit >= 0) && (hit.subHit < 6))
/*     */     {
/* 251 */       player.func_71038_i();
/* 252 */       if (player.func_70093_af()) {
/* 253 */         player.field_70170_p.func_72980_b(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, "thaumcraft:squeek", 0.6F, 2.0F + world.field_73012_v.nextFloat() * 0.2F, false);
/* 254 */         if (!this.field_145850_b.field_72995_K)
/*     */         {
/* 256 */           int tmp123_120 = hit.subHit; byte[] tmp123_115 = this.chokedSides;tmp123_115[tmp123_120] = ((byte)(tmp123_115[tmp123_120] + 1));
/* 257 */           if (this.chokedSides[hit.subHit] > 2) this.chokedSides[hit.subHit] = 0;
/* 258 */           func_70296_d();
/* 259 */           world.func_175689_h(bp);
/*     */         }
/*     */       } else {
/* 262 */         player.field_70170_p.func_72980_b(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*     */         
/* 264 */         this.openSides[hit.subHit] = (this.openSides[hit.subHit] == 0 ? 1 : false);
/* 265 */         EnumFacing dir = EnumFacing.field_82609_l[hit.subHit];
/* 266 */         TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(dir));
/* 267 */         if ((tile != null) && ((tile instanceof TileTube))) {
/* 268 */           ((TileTube)tile).openSides[dir.func_176734_d().ordinal()] = this.openSides[hit.subHit];
/* 269 */           world.func_175689_h(this.field_174879_c.func_177972_a(dir));
/* 270 */           tile.func_70296_d();
/*     */         }
/* 272 */         func_70296_d();
/* 273 */         world.func_175689_h(bp);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 278 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canConnectSide(EnumFacing side)
/*     */   {
/* 290 */     TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(side));
/* 291 */     return (tile != null) && ((tile instanceof IEssentiaTransport));
/*     */   }
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids)
/*     */   {
/* 296 */     float min = 0.375F;
/* 297 */     float max = 0.625F;
/* 298 */     if (canConnectSide(EnumFacing.DOWN)) cuboids.add(new IndexedCuboid6(Integer.valueOf(0), new Cuboid6(this.field_174879_c.func_177958_n() + min, this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p() + min, this.field_174879_c.func_177958_n() + max, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + max)));
/* 299 */     if (canConnectSide(EnumFacing.UP)) cuboids.add(new IndexedCuboid6(Integer.valueOf(1), new Cuboid6(this.field_174879_c.func_177958_n() + min, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + min, this.field_174879_c.func_177958_n() + max, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + max)));
/* 300 */     if (canConnectSide(EnumFacing.NORTH)) cuboids.add(new IndexedCuboid6(Integer.valueOf(2), new Cuboid6(this.field_174879_c.func_177958_n() + min, this.field_174879_c.func_177956_o() + min, this.field_174879_c.func_177952_p(), this.field_174879_c.func_177958_n() + max, this.field_174879_c.func_177956_o() + max, this.field_174879_c.func_177952_p() + 0.5D)));
/* 301 */     if (canConnectSide(EnumFacing.SOUTH)) cuboids.add(new IndexedCuboid6(Integer.valueOf(3), new Cuboid6(this.field_174879_c.func_177958_n() + min, this.field_174879_c.func_177956_o() + min, this.field_174879_c.func_177952_p() + 0.5D, this.field_174879_c.func_177958_n() + max, this.field_174879_c.func_177956_o() + max, this.field_174879_c.func_177952_p() + 1)));
/* 302 */     if (canConnectSide(EnumFacing.WEST)) cuboids.add(new IndexedCuboid6(Integer.valueOf(4), new Cuboid6(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + min, this.field_174879_c.func_177952_p() + min, this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + max, this.field_174879_c.func_177952_p() + max)));
/* 303 */     if (canConnectSide(EnumFacing.EAST)) { cuboids.add(new IndexedCuboid6(Integer.valueOf(5), new Cuboid6(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + min, this.field_174879_c.func_177952_p() + min, this.field_174879_c.func_177958_n() + 1, this.field_174879_c.func_177956_o() + max, this.field_174879_c.func_177952_p() + max)));
/*     */     }
/* 305 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6(this.field_174879_c.func_177958_n() + 0.25F, this.field_174879_c.func_177956_o() + 0.25F, this.field_174879_c.func_177952_p() + 0.25F, this.field_174879_c.func_177958_n() + 0.75F, this.field_174879_c.func_177956_o() + 0.75F, this.field_174879_c.func_177952_p() + 0.75F)));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileTubeBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */