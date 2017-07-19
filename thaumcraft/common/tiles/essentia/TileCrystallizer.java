/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class TileCrystallizer extends TileThaumcraft implements IAspectContainer, IEssentiaTransport, ITickable
/*     */ {
/*  29 */   public Aspect aspect = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  35 */     this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
/*  36 */     this.fuel = nbttagcompound.func_74762_e("fuel");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  42 */     if (this.aspect != null) nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
/*  43 */     nbttagcompound.func_74768_a("fuel", this.fuel);
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/*  49 */     AspectList al = new AspectList();
/*  50 */     if (this.aspect != null) al.add(this.aspect, 1);
/*  51 */     return al;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  60 */     if (am == 0) return am;
/*  61 */     if (this.aspect == null) {
/*  62 */       am--;
/*  63 */       this.aspect = tt;
/*  64 */       this.field_145850_b.func_175689_h(func_174877_v());
/*  65 */       func_70296_d();
/*     */     }
/*  67 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  72 */     if ((this.aspect != null) && (am == 1)) {
/*  73 */       this.aspect = null;
/*  74 */       this.field_145850_b.func_175689_h(func_174877_v());
/*  75 */       func_70296_d();
/*  76 */       return true;
/*     */     }
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/*  83 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/*  89 */     if ((amt == 1) && (this.aspect != null) && (tag == this.aspect)) return true;
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/*  95 */     for (Aspect tt : ot.getAspects()) {
/*  96 */       if ((this.aspect == null) || (this.aspect != tt) || (ot.getAmount(tt) != 1)) return false;
/*     */     }
/*  98 */     return true;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 103 */     return (this.aspect != null) && (tag == this.aspect) ? 1 : 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 108 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/* 116 */     return face == getFacing();
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/* 121 */     return face == getFacing();
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/* 126 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public int getMinimumSuction()
/*     */   {
/* 135 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing loc)
/*     */   {
/* 140 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc)
/*     */   {
/* 145 */     return (loc == getFacing()) && (this.aspect == null) ? 128 : gettingPower() ? 0 : 64;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/* 150 */     return this.aspect;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/* 155 */     return this.aspect == null ? 0 : 1;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 160 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 165 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/* 168 */   int count = 0;
/* 169 */   int progress = 0;
/* 170 */   int fuel = 0;
/* 171 */   final int progMax = 200;
/* 172 */   public float spin = 0.0F;
/* 173 */   public float spinInc = 0.0F;
/*     */   
/* 175 */   float tr = 1.0F;
/* 176 */   float tg = 1.0F;
/* 177 */   float tb = 1.0F;
/* 178 */   public float cr = 1.0F;
/* 179 */   public float cg = 1.0F;
/* 180 */   public float cb = 1.0F;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/* 185 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 187 */       if ((++this.count % 5 == 0) && 
/* 188 */         (!gettingPower())) {
/* 189 */         if (this.aspect == null) {
/* 190 */           fillReservoir();
/* 191 */           this.progress = 0;
/*     */         } else {
/* 193 */           if (this.fuel <= 0) {
/* 194 */             this.fuel += AuraHandler.drainAuraAvailable(func_145831_w(), func_174877_v(), Aspect.EARTH, 4) * 25;
/*     */           }
/* 196 */           if (this.fuel > 0) {
/* 197 */             int q = Math.min(5, Math.min(Math.max(1, (200 - this.progress) / 5), this.fuel));
/* 198 */             this.fuel -= q;
/* 199 */             this.progress += q * 5;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 204 */       if ((this.aspect != null) && (this.progress >= 200)) {
/* 205 */         eject();
/* 206 */         this.aspect = null;
/* 207 */         this.progress = 0;
/* 208 */         this.field_145850_b.func_175689_h(func_174877_v());
/* 209 */         func_70296_d();
/*     */       }
/*     */     }
/*     */     else {
/* 213 */       if (this.aspect == null) {
/* 214 */         this.tr = (this.tg = this.tb = 1.0F);
/*     */       } else {
/* 216 */         Color c = new Color(this.aspect.getColor());
/* 217 */         this.tr = (c.getRed() / 220.0F);
/* 218 */         this.tg = (c.getGreen() / 220.0F);
/* 219 */         this.tb = (c.getBlue() / 220.0F);
/*     */       }
/* 221 */       if (this.cr < this.tr) this.cr += 0.05F; if (this.cr > this.tr) this.cr -= 0.05F;
/* 222 */       if (this.cg < this.tg) this.cg += 0.05F; if (this.cg > this.tg) this.cg -= 0.05F;
/* 223 */       if (this.cb < this.tb) this.cb += 0.05F; if (this.cb > this.tb) this.cb -= 0.05F;
/* 224 */       this.spin += this.spinInc;
/* 225 */       if (this.spin > 360.0F) { this.spin -= 360.0F;
/*     */       }
/* 227 */       if ((this.aspect != null) && (this.spinInc < 20.0F) && (!gettingPower())) {
/* 228 */         this.spinInc += 0.1F;
/* 229 */         if (this.spinInc > 20.0F) this.spinInc = 20.0F;
/*     */       }
/* 231 */       else if (((this.aspect == null) || (gettingPower())) && (this.spinInc > 0.0F)) {
/* 232 */         this.spinInc -= 0.2F;
/* 233 */         if (this.spinInc < 0.0F) { this.spinInc = 0.0F;
/*     */         }
/*     */       }
/* 236 */       if (this.venting > 0) {
/* 237 */         this.venting -= 1;
/* 238 */         float fx = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 239 */         float fz = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 240 */         float fy = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 241 */         float fx2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 242 */         float fz2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 243 */         float fy2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 244 */         int color = 16777215;
/* 245 */         Thaumcraft.proxy.getFX().drawVentParticles(func_174877_v().func_177958_n() + 0.5F + fx + getFacing().func_176734_d().func_82601_c() / 2.1F, func_174877_v().func_177956_o() + 0.5F + fy + getFacing().func_176734_d().func_96559_d() / 2.1F, func_174877_v().func_177952_p() + 0.5F + fz + getFacing().func_176734_d().func_82599_e() / 2.1F, getFacing().func_176734_d().func_82601_c() / 4.0F + fx2, getFacing().func_176734_d().func_96559_d() / 4.0F + fy2, getFacing().func_176734_d().func_82599_e() / 4.0F + fz2, color);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 256 */   int venting = 0;
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 261 */     if (i >= 0)
/*     */     {
/* 263 */       if (this.field_145850_b.field_72995_K) {
/* 264 */         this.venting = 7;
/*     */       }
/* 266 */       return true;
/*     */     }
/*     */     
/* 269 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   public void eject()
/*     */   {
/* 274 */     ItemStack stack = new ItemStack(ItemsTC.crystalEssence, 1, 0);
/* 275 */     ((ItemGenericEssentiaContainer)stack.func_77973_b()).setAspects(stack, new AspectList().add(this.aspect, 1));
/* 276 */     TileEntity inventory = this.field_145850_b.func_175625_s(func_174877_v().func_177972_a(getFacing().func_176734_d()));
/* 277 */     if ((inventory != null) && ((inventory instanceof IInventory))) {
/* 278 */       stack = InventoryUtils.placeItemStackIntoInventory(stack, (IInventory)inventory, getFacing(), true);
/*     */     }
/*     */     
/* 281 */     if (stack != null) {
/* 282 */       spawnItem(stack);
/*     */     }
/* 284 */     this.field_145850_b.func_72908_a(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o() + 0.5D, func_174877_v().func_177952_p() + 0.5D, "random.fizz", 0.25F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean spawnItem(ItemStack stack)
/*     */   {
/* 290 */     EntityItem ie2 = new EntityItem(this.field_145850_b, func_174877_v().func_177958_n() + 0.5D + getFacing().func_176734_d().func_82601_c() * 0.65D, func_174877_v().func_177956_o() + 0.5D + getFacing().func_176734_d().func_96559_d() * 0.65D, func_174877_v().func_177952_p() + 0.5D + getFacing().func_176734_d().func_82599_e() * 0.65D, stack);
/*     */     
/*     */ 
/*     */ 
/* 294 */     ie2.field_70159_w = (getFacing().func_176734_d().func_82601_c() * 0.04F);
/* 295 */     ie2.field_70181_x = (getFacing().func_176734_d().func_96559_d() * 0.04F);
/* 296 */     ie2.field_70179_y = (getFacing().func_176734_d().func_82599_e() * 0.04F);
/* 297 */     this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 0, 0);
/* 298 */     return this.field_145850_b.func_72838_d(ie2);
/*     */   }
/*     */   
/*     */   void fillReservoir() {
/* 302 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), getFacing());
/* 303 */     if (te != null) {
/* 304 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 305 */       if (!ic.canOutputTo(getFacing().func_176734_d())) { return;
/*     */       }
/* 307 */       Aspect ta = null;
/*     */       
/* 309 */       if ((ic.getEssentiaAmount(getFacing().func_176734_d()) > 0) && 
/* 310 */         (ic.getSuctionAmount(getFacing().func_176734_d()) < getSuctionAmount(getFacing())) && (getSuctionAmount(getFacing()) >= ic.getMinimumSuction()))
/*     */       {
/* 312 */         ta = ic.getEssentiaType(getFacing().func_176734_d());
/*     */       }
/*     */       
/*     */ 
/* 316 */       if ((ta != null) && (ic.getSuctionAmount(getFacing().func_176734_d()) < getSuctionAmount(getFacing()))) {
/* 317 */         addToContainer(ta, ic.takeEssentia(ta, 1, getFacing().func_176734_d()));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileCrystallizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */