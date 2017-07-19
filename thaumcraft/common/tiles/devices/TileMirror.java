/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.particle.EntitySpellParticleFX.AmbientMobFactory;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class TileMirror extends TileThaumcraft implements IInventory, ITickable
/*     */ {
/*  34 */   public boolean linked = false;
/*     */   public int linkX;
/*     */   public int linkY;
/*     */   public int linkZ;
/*     */   public int linkDim;
/*     */   public int instability;
/*     */   
/*     */   public void restoreLink()
/*     */   {
/*  43 */     if (isDestinationValid()) {
/*  44 */       World targetWorld = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/*  45 */       if (targetWorld == null) return;
/*  46 */       TileEntity te = targetWorld.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  47 */       if ((te != null) && ((te instanceof TileMirror))) {
/*  48 */         TileMirror tm = (TileMirror)te;
/*  49 */         tm.linked = true;
/*  50 */         tm.linkX = func_174877_v().func_177958_n();
/*  51 */         tm.linkY = func_174877_v().func_177956_o();
/*  52 */         tm.linkZ = func_174877_v().func_177952_p();
/*  53 */         tm.linkDim = this.field_145850_b.field_73011_w.func_177502_q();
/*  54 */         targetWorld.func_175689_h(tm.func_174877_v());
/*  55 */         this.linked = true;
/*  56 */         func_70296_d();
/*  57 */         tm.func_70296_d();
/*  58 */         this.field_145850_b.func_175689_h(func_174877_v());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void invalidateLink() {
/*  64 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/*  65 */     if (targetWorld == null) return;
/*  66 */     if (!Utils.isChunkLoaded(targetWorld, this.linkX, this.linkZ)) return;
/*  67 */     TileEntity te = targetWorld.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  68 */     if ((te != null) && ((te instanceof TileMirror))) {
/*  69 */       TileMirror tm = (TileMirror)te;
/*  70 */       tm.linked = false;
/*  71 */       func_70296_d();
/*  72 */       tm.func_70296_d();
/*  73 */       targetWorld.func_175689_h(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isLinkValid()
/*     */   {
/*  79 */     if (!this.linked) return false;
/*  80 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/*  81 */     if (targetWorld == null) {
/*  82 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  87 */     TileEntity te = targetWorld.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  88 */     if ((te == null) || (!(te instanceof TileMirror))) {
/*  89 */       this.linked = false;
/*  90 */       func_70296_d();
/*  91 */       this.field_145850_b.func_175689_h(func_174877_v());
/*  92 */       return false;
/*     */     }
/*  94 */     TileMirror tm = (TileMirror)te;
/*  95 */     if (!tm.linked) {
/*  96 */       this.linked = false;
/*  97 */       func_70296_d();
/*  98 */       this.field_145850_b.func_175689_h(func_174877_v());
/*  99 */       return false;
/*     */     }
/* 101 */     if ((tm.linkX != func_174877_v().func_177958_n()) || (tm.linkY != func_174877_v().func_177956_o()) || (tm.linkZ != func_174877_v().func_177952_p()) || (tm.linkDim != this.field_145850_b.field_73011_w.func_177502_q()))
/*     */     {
/*     */ 
/* 104 */       this.linked = false;
/* 105 */       func_70296_d();
/* 106 */       this.field_145850_b.func_175689_h(func_174877_v());
/* 107 */       return false;
/*     */     }
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isLinkValidSimple() {
/* 113 */     if (!this.linked) return false;
/* 114 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/* 115 */     if (targetWorld == null) {
/* 116 */       return false;
/*     */     }
/*     */     
/* 119 */     TileEntity te = targetWorld.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 120 */     if ((te == null) || (!(te instanceof TileMirror))) {
/* 121 */       return false;
/*     */     }
/* 123 */     TileMirror tm = (TileMirror)te;
/* 124 */     if (!tm.linked) {
/* 125 */       return false;
/*     */     }
/* 127 */     if ((tm.linkX != func_174877_v().func_177958_n()) || (tm.linkY != func_174877_v().func_177956_o()) || (tm.linkZ != func_174877_v().func_177952_p()) || (tm.linkDim != this.field_145850_b.field_73011_w.func_177502_q()))
/*     */     {
/*     */ 
/* 130 */       return false;
/*     */     }
/* 132 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isDestinationValid() {
/* 136 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/* 137 */     if (targetWorld == null) {
/* 138 */       return false;
/*     */     }
/*     */     
/* 141 */     TileEntity te = targetWorld.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 142 */     if ((te == null) || (!(te instanceof TileMirror))) {
/* 143 */       this.linked = false;
/* 144 */       func_70296_d();
/* 145 */       this.field_145850_b.func_175689_h(func_174877_v());
/* 146 */       return false;
/*     */     }
/*     */     
/* 149 */     TileMirror tm = (TileMirror)te;
/* 150 */     if (tm.isLinkValid()) {
/* 151 */       return false;
/*     */     }
/* 153 */     return true;
/*     */   }
/*     */   
/*     */   public boolean transport(EntityItem ie) {
/* 157 */     ItemStack items = ie.func_92059_d();
/* 158 */     if ((!this.linked) || (!isLinkValid())) return false;
/* 159 */     World world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/* 160 */     TileEntity target = world.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 161 */     if ((target != null) && ((target instanceof TileMirror))) {
/* 162 */       ((TileMirror)target).addStack(items);
/* 163 */       addInstability(null, items.field_77994_a);
/* 164 */       ie.func_70106_y();
/* 165 */       func_70296_d();
/* 166 */       target.func_70296_d();
/* 167 */       this.field_145850_b.func_175641_c(func_174877_v(), this.field_145854_h, 1, 0);
/* 168 */       return true;
/*     */     }
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   public boolean transportDirect(ItemStack items) {
/* 174 */     if ((items == null) || (items.field_77994_a <= 0)) return false;
/* 175 */     addStack(items.func_77946_l());
/* 176 */     func_70296_d();
/* 177 */     return true;
/*     */   }
/*     */   
/*     */   public void eject() {
/* 181 */     if ((this.outputStacks.size() > 0) && (this.count > 20)) {
/* 182 */       int i = this.field_145850_b.field_73012_v.nextInt(this.outputStacks.size());
/* 183 */       if (this.outputStacks.get(i) != null) {
/* 184 */         ItemStack outItem = ((ItemStack)this.outputStacks.get(i)).func_77946_l();
/* 185 */         outItem.field_77994_a = 1;
/* 186 */         if (spawnItem(outItem)) {
/* 187 */           ((ItemStack)this.outputStacks.get(i)).field_77994_a -= 1;
/* 188 */           addInstability(null, 1);
/* 189 */           this.field_145850_b.func_175641_c(func_174877_v(), this.field_145854_h, 1, 0);
/* 190 */           if (((ItemStack)this.outputStacks.get(i)).field_77994_a <= 0) {
/* 191 */             this.outputStacks.remove(i);
/*     */           }
/* 193 */           func_70296_d();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean spawnItem(ItemStack stack) {
/*     */     try {
/* 201 */       EnumFacing face = BlockStateUtils.getFacing(func_145832_p());
/* 202 */       EntityItem ie2 = new EntityItem(this.field_145850_b, func_174877_v().func_177958_n() + 0.5D - face.func_82601_c() * 0.3D, func_174877_v().func_177956_o() + 0.25D - face.func_96559_d() * 0.3D, func_174877_v().func_177952_p() + 0.5D - face.func_82599_e() * 0.3D, stack);
/*     */       
/*     */ 
/*     */ 
/* 206 */       ie2.field_70159_w = (face.func_82601_c() * 0.15F);
/* 207 */       ie2.field_70181_x = (face.func_96559_d() * 0.15F);
/* 208 */       ie2.field_70179_y = (face.func_82599_e() * 0.15F);
/* 209 */       ie2.field_71088_bW = 20;
/* 210 */       this.field_145850_b.func_72838_d(ie2);
/* 211 */       return true;
/*     */     } catch (Exception e) {}
/* 213 */     return false;
/*     */   }
/*     */   
/*     */   protected void addInstability(World targetWorld, int amt)
/*     */   {
/* 218 */     this.instability += amt;
/* 219 */     func_70296_d();
/* 220 */     if (targetWorld != null) {
/* 221 */       TileEntity te = targetWorld.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 222 */       if ((te != null) && ((te instanceof TileMirror))) {
/* 223 */         ((TileMirror)te).instability += amt;
/* 224 */         if (((TileMirror)te).instability < 0) ((TileMirror)te).instability = 0;
/* 225 */         te.func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 234 */     super.readCustomNBT(nbttagcompound);
/* 235 */     this.linked = nbttagcompound.func_74767_n("linked");
/* 236 */     this.linkX = nbttagcompound.func_74762_e("linkX");
/* 237 */     this.linkY = nbttagcompound.func_74762_e("linkY");
/* 238 */     this.linkZ = nbttagcompound.func_74762_e("linkZ");
/* 239 */     this.linkDim = nbttagcompound.func_74762_e("linkDim");
/* 240 */     this.instability = nbttagcompound.func_74762_e("instability");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 246 */     super.writeCustomNBT(nbttagcompound);
/* 247 */     nbttagcompound.func_74757_a("linked", this.linked);
/* 248 */     nbttagcompound.func_74768_a("linkX", this.linkX);
/* 249 */     nbttagcompound.func_74768_a("linkY", this.linkY);
/* 250 */     nbttagcompound.func_74768_a("linkZ", this.linkZ);
/* 251 */     nbttagcompound.func_74768_a("linkDim", this.linkDim);
/* 252 */     nbttagcompound.func_74768_a("instability", this.instability);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 262 */     if (i == 1)
/*     */     {
/* 264 */       if (this.field_145850_b.field_72995_K) {
/* 265 */         EnumFacing face = BlockStateUtils.getFacing(func_145832_p());
/* 266 */         for (int q = 0; q < Thaumcraft.proxy.getFX().particleCount(1); q++) {
/* 267 */           double xx = func_174877_v().func_177958_n() + 0.33D + this.field_145850_b.field_73012_v.nextFloat() * 0.33F - face.func_82601_c() / 2.0D;
/* 268 */           double yy = func_174877_v().func_177956_o() + 0.33D + this.field_145850_b.field_73012_v.nextFloat() * 0.33F - face.func_96559_d() / 2.0D;
/* 269 */           double zz = func_174877_v().func_177952_p() + 0.33D + this.field_145850_b.field_73012_v.nextFloat() * 0.33F - face.func_82599_e() / 2.0D;
/* 270 */           EntitySpellParticleFX.AmbientMobFactory amf = new EntitySpellParticleFX.AmbientMobFactory();
/* 271 */           EntityFX var21 = amf.func_178902_a(0, this.field_145850_b, xx, yy, zz, 0.0D, 0.0D, 0.0D, new int[0]);
/* 272 */           var21.field_70159_w = (face.func_82601_c() * 0.05D);
/* 273 */           var21.field_70181_x = (face.func_96559_d() * 0.05D);
/* 274 */           var21.field_70179_y = (face.func_82599_e() * 0.05D);
/* 275 */           Minecraft.func_71410_x().field_71452_i.func_78873_a(var21);
/*     */         }
/*     */       }
/* 278 */       return true;
/*     */     }
/* 280 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/* 283 */   int count = 0;
/* 284 */   int inc = 40;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/* 289 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 291 */       eject();
/*     */       
/* 293 */       checkInstability();
/*     */       
/* 295 */       if (this.count++ % this.inc == 0) {
/* 296 */         if (!isLinkValidSimple()) {
/* 297 */           if (this.inc < 600) this.inc += 20;
/* 298 */           restoreLink();
/*     */         } else {
/* 300 */           this.inc = 40;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkInstability() {
/* 307 */     if (this.instability > 128) {
/* 308 */       AuraHelper.pollute(this.field_145850_b, this.field_174879_c, 1, true);
/* 309 */       this.instability -= 128;
/* 310 */       func_70296_d();
/*     */     }
/* 312 */     if ((this.instability > 0) && (this.count % 100 == 0)) {
/* 313 */       this.instability -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 318 */   private ArrayList<ItemStack> outputStacks = new ArrayList();
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 323 */     super.func_145839_a(nbtCompound);
/* 324 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
/* 325 */     this.outputStacks = new ArrayList();
/*     */     
/* 327 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 329 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 330 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 332 */       this.outputStacks.add(ItemStack.func_77949_a(nbttagcompound1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 342 */     super.func_145841_b(nbtCompound);
/* 343 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 345 */     for (int i = 0; i < this.outputStacks.size(); i++)
/*     */     {
/* 347 */       if ((this.outputStacks.get(i) != null) && (((ItemStack)this.outputStacks.get(i)).field_77994_a > 0)) {
/* 348 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 349 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 350 */         ((ItemStack)this.outputStacks.get(i)).func_77955_b(nbttagcompound1);
/* 351 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 355 */     nbtCompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/* 361 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/* 367 */     return null;
/*     */   }
/*     */   
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/* 372 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 379 */     return null;
/*     */   }
/*     */   
/*     */   public void addStack(ItemStack stack) {
/* 383 */     this.outputStacks.add(stack);
/* 384 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 390 */     World world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/* 391 */     TileEntity target = world.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 392 */     if ((target != null) && ((target instanceof TileMirror))) {
/* 393 */       ((TileMirror)target).addStack(par2ItemStack.func_77946_l());
/* 394 */       addInstability(null, par2ItemStack.field_77994_a);
/* 395 */       this.field_145850_b.func_175641_c(func_174877_v(), this.field_145854_h, 1, 0);
/*     */     } else {
/* 397 */       spawnItem(par2ItemStack.func_77946_l());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 404 */     return 64;
/*     */   }
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer var1)
/*     */   {
/* 409 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int var1, ItemStack var2)
/*     */   {
/* 414 */     World world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/* 415 */     TileEntity target = world.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 416 */     if ((target != null) && ((target instanceof TileMirror))) {
/* 417 */       return true;
/*     */     }
/* 419 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 426 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 432 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 438 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 456 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_174890_g()
/*     */   {
/* 468 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */