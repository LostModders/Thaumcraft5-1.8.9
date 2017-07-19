/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ public class TileInfernalFurnace extends TileThaumcraftInventory implements ITickable
/*     */ {
/*     */   public int furnaceCookTime;
/*     */   public int furnaceMaxCookTime;
/*     */   public int speedyTime;
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  31 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 1.3D, func_174877_v().func_177956_o() - 1.3D, func_174877_v().func_177952_p() - 1.3D, func_174877_v().func_177958_n() + 2.3D, func_174877_v().func_177956_o() + 2.3D, func_174877_v().func_177952_p() + 2.3D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TileInfernalFurnace()
/*     */   {
/*  42 */     this.furnaceCookTime = 0;
/*  43 */     this.furnaceMaxCookTime = 0;
/*  44 */     this.speedyTime = 0;
/*  45 */     this.itemStacks = new ItemStack[32];
/*     */   }
/*     */   
/*     */ 
/*     */   public int[] func_180463_a(EnumFacing par1)
/*     */   {
/*  51 */     return par1 == EnumFacing.UP ? new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 } : new int[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180461_b(int par1, ItemStack par2ItemStack, EnumFacing par3)
/*     */   {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/*  65 */     super.func_145839_a(nbttagcompound);
/*  66 */     this.furnaceCookTime = nbttagcompound.func_74765_d("CookTime");
/*  67 */     this.speedyTime = nbttagcompound.func_74765_d("SpeedyTime");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  72 */     super.func_145841_b(nbttagcompound);
/*  73 */     nbttagcompound.func_74777_a("CookTime", (short)this.furnaceCookTime);
/*  74 */     nbttagcompound.func_74777_a("SpeedyTime", (short)this.speedyTime);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  80 */     if (this.facingX == -5) {
/*  81 */       setFacing();
/*     */     }
/*     */     
/*  84 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*  86 */       boolean cookedflag = false;
/*     */       
/*  88 */       if (this.furnaceCookTime > 0) {
/*  89 */         this.furnaceCookTime -= 1;
/*  90 */         cookedflag = true;
/*     */       }
/*     */       
/*  93 */       if (this.furnaceMaxCookTime == 0) {
/*  94 */         this.furnaceMaxCookTime = calcCookTime();
/*     */       }
/*     */       
/*  97 */       if (this.furnaceCookTime > this.furnaceMaxCookTime) {
/*  98 */         this.furnaceCookTime = this.furnaceMaxCookTime;
/*     */       }
/*     */       
/*     */ 
/* 102 */       if ((this.furnaceCookTime == 0) && (cookedflag)) {
/* 103 */         for (int a = 0; a < func_70302_i_(); a++) {
/* 104 */           if (this.itemStacks[a] != null) {
/* 105 */             ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.itemStacks[a]);
/* 106 */             if (itemstack != null)
/*     */             {
/* 108 */               if (this.speedyTime > 0) {
/* 109 */                 this.speedyTime -= 1;
/*     */               }
/* 111 */               ejectItem(itemstack.func_77946_l(), this.itemStacks[a]);
/* 112 */               this.field_145850_b.func_175641_c(func_174877_v(), thaumcraft.api.blocks.BlocksTC.infernalFurnace, 3, 0);
/* 113 */               if (func_145831_w().field_73012_v.nextInt(12) == 0)
/* 114 */                 AuraHelper.pollute(func_145831_w(), func_174877_v().func_177972_a(getFacing().func_176734_d()), 1, true);
/* 115 */               this.itemStacks[a].field_77994_a -= 1;
/* 116 */               if (this.itemStacks[a].field_77994_a > 0) break;
/* 117 */               this.itemStacks[a] = null; break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 124 */       if (this.speedyTime <= 0) {
/* 125 */         this.speedyTime = (AuraHandler.drainAuraAvailable(func_145831_w(), func_174877_v(), thaumcraft.api.aspects.Aspect.FIRE, 5) * 5);
/*     */       }
/*     */       
/*     */ 
/* 129 */       if ((this.furnaceCookTime == 0) && (!cookedflag)) {
/* 130 */         for (int a = 0; a < func_70302_i_(); a++) {
/* 131 */           if ((this.itemStacks[a] != null) && (canSmelt(a))) {
/* 132 */             this.furnaceMaxCookTime = calcCookTime();
/* 133 */             this.furnaceCookTime = this.furnaceMaxCookTime;
/* 134 */             break;
/*     */           }
/* 136 */           if ((this.itemStacks[a] != null) && (!canSmelt(a))) {
/* 137 */             destroyItem(a);
/* 138 */             func_70296_d();
/* 139 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private int getBellows()
/*     */   {
/* 148 */     int bellows = 0;
/* 149 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 150 */       if (dir != EnumFacing.UP) {
/* 151 */         BlockPos p2 = this.field_174879_c.func_177967_a(dir, 2);
/* 152 */         net.minecraft.tileentity.TileEntity tile = this.field_145850_b.func_175625_s(p2);
/* 153 */         if ((tile != null) && ((tile instanceof TileBellows)) && (thaumcraft.common.lib.utils.BlockStateUtils.getFacing(this.field_145850_b.func_180495_p(p2)) == dir.func_176734_d()) && (this.field_145850_b.func_175687_A(p2) == 0))
/*     */         {
/*     */ 
/* 156 */           bellows++; }
/*     */       }
/*     */     }
/* 159 */     return Math.min(3, bellows);
/*     */   }
/*     */   
/*     */   private int calcCookTime() {
/* 163 */     return (this.speedyTime > 0 ? 80 : 140) - 20 * getBellows();
/*     */   }
/*     */   
/*     */   public boolean addItemsToInventory(ItemStack items)
/*     */   {
/* 168 */     for (int a = 0; a < func_70302_i_(); a++) {
/* 169 */       if ((this.itemStacks[a] != null) && (this.itemStacks[a].func_77969_a(items)) && (this.itemStacks[a].field_77994_a + items.field_77994_a <= items.func_77976_d()))
/*     */       {
/*     */ 
/* 172 */         this.itemStacks[a].field_77994_a += items.field_77994_a;
/* 173 */         if (!canSmelt(a)) {
/* 174 */           destroyItem(a);
/*     */         }
/* 176 */         func_70296_d();
/* 177 */         return true;
/*     */       }
/* 179 */       if (this.itemStacks[a] == null) {
/* 180 */         func_70299_a(a, items);
/* 181 */         if (!canSmelt(a)) {
/* 182 */           destroyItem(a);
/*     */         }
/* 184 */         func_70296_d();
/* 185 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   private void destroyItem(int slot) {
/* 193 */     this.itemStacks[slot] = null;
/*     */     
/* 195 */     this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.5F, "random.fizz", 0.3F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F, false);
/*     */     
/* 197 */     double var21 = this.field_174879_c.func_177958_n() + this.field_145850_b.field_73012_v.nextFloat();
/* 198 */     double var22 = this.field_174879_c.func_177956_o() + 1;
/* 199 */     double var23 = this.field_174879_c.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat();
/* 200 */     this.field_145850_b.func_175688_a(EnumParticleTypes.LAVA, var21, var22, var23, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */   }
/*     */   
/* 203 */   public int facingX = -5;
/* 204 */   public int facingZ = -5;
/*     */   
/*     */ 
/*     */   public void ejectItem(ItemStack items, ItemStack furnaceItemStack)
/*     */   {
/* 209 */     if (items == null) return;
/* 210 */     ItemStack bit = items.func_77946_l();
/* 211 */     int bellows = getBellows();
/*     */     
/* 213 */     float lx = 0.5F;lx += this.facingX * 1.2F;
/* 214 */     float lz = 0.5F;lz += this.facingZ * 1.2F;
/* 215 */     float mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03F : this.facingX * 0.13F;
/* 216 */     float mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03F : this.facingZ * 0.13F;
/*     */     
/* 218 */     EntityItem entityitem = new EntityItem(this.field_145850_b, this.field_174879_c.func_177958_n() + lx, this.field_174879_c.func_177956_o() + 0.4F, this.field_174879_c.func_177952_p() + lz, items);
/*     */     
/*     */ 
/*     */ 
/* 222 */     entityitem.field_70159_w = mx;
/* 223 */     entityitem.field_70179_y = mz;
/* 224 */     entityitem.field_70181_x = 0.0D;
/* 225 */     this.field_145850_b.func_72838_d(entityitem);
/*     */     
/* 227 */     if (ThaumcraftApi.getSmeltingBonus(furnaceItemStack) != null) {
/* 228 */       ItemStack bonus = ThaumcraftApi.getSmeltingBonus(furnaceItemStack).func_77946_l();
/* 229 */       if (bonus != null) {
/* 230 */         if (bellows == 0) {
/* 231 */           if (this.field_145850_b.field_73012_v.nextInt(4) == 0) bonus.field_77994_a += 1;
/*     */         } else {
/* 233 */           for (int a = 0; a < bellows; a++) if (this.field_145850_b.field_73012_v.nextFloat() < 0.44F) { bonus.field_77994_a += 1;
/*     */             }
/*     */         }
/*     */       }
/* 237 */       if ((bonus != null) && (bonus.field_77994_a > 0)) {
/* 238 */         mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03F : this.facingX * 0.13F;
/* 239 */         mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03F : this.facingZ * 0.13F;
/* 240 */         EntityItem entityitem2 = new EntityItem(this.field_145850_b, this.field_174879_c.func_177958_n() + lx, this.field_174879_c.func_177956_o() + 0.4F, this.field_174879_c.func_177952_p() + lz, bonus);
/*     */         
/*     */ 
/*     */ 
/* 244 */         entityitem2.field_70159_w = mx;
/* 245 */         entityitem2.field_70179_y = mz;
/* 246 */         entityitem2.field_70181_x = 0.0D;
/* 247 */         this.field_145850_b.func_72838_d(entityitem2);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 252 */     int var2 = items.field_77994_a;
/* 253 */     float var3 = FurnaceRecipes.func_77602_a().func_151398_b(bit);
/*     */     
/*     */ 
/* 256 */     if (var3 == 0.0F)
/*     */     {
/* 258 */       var2 = 0;
/*     */     }
/* 260 */     else if (var3 < 1.0F)
/*     */     {
/* 262 */       int var4 = MathHelper.func_76141_d(var2 * var3);
/*     */       
/* 264 */       if ((var4 < MathHelper.func_76123_f(var2 * var3)) && ((float)Math.random() < var2 * var3 - var4))
/*     */       {
/* 266 */         var4++;
/*     */       }
/*     */       
/* 269 */       var2 = var4;
/*     */     }
/*     */     
/* 272 */     while (var2 > 0)
/*     */     {
/* 274 */       int var4 = EntityXPOrb.func_70527_a(var2);
/* 275 */       var2 -= var4;
/* 276 */       EntityXPOrb xp = new EntityXPOrb(this.field_145850_b, this.field_174879_c.func_177958_n() + lx, this.field_174879_c.func_177956_o() + 0.4F, this.field_174879_c.func_177952_p() + lz, var4);
/*     */       
/*     */ 
/*     */ 
/* 280 */       mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.025F : this.facingX * 0.13F;
/* 281 */       mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.025F : this.facingZ * 0.13F;
/* 282 */       xp.field_70159_w = mx;
/* 283 */       xp.field_70179_y = mz;
/* 284 */       xp.field_70181_x = 0.0D;
/* 285 */       this.field_145850_b.func_72838_d(xp);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean canSmelt(int slotIn)
/*     */   {
/* 292 */     if (this.itemStacks[slotIn] == null)
/*     */     {
/* 294 */       return false;
/*     */     }
/*     */     
/* 297 */     ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.itemStacks[slotIn]);
/* 298 */     if (itemstack == null)
/*     */     {
/* 300 */       return false;
/*     */     }
/*     */     
/* 303 */     return true;
/*     */   }
/*     */   
/*     */   private void setFacing() {
/* 307 */     this.facingX = 0;
/* 308 */     this.facingZ = 0;
/* 309 */     EnumFacing face = getFacing().func_176734_d();
/* 310 */     this.facingX = face.func_82601_c();
/* 311 */     this.facingZ = face.func_82599_e();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 318 */     if (i == 3)
/*     */     {
/* 320 */       if (this.field_145850_b.field_72995_K) {
/* 321 */         for (int a = 0; a < 5; a++) {
/* 322 */           Thaumcraft.proxy.getFX().furnaceLavaFx(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), this.facingX, this.facingZ);
/* 323 */           this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.5F, "liquid.lavapop", 0.1F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.15F, false);
/*     */         }
/*     */       }
/*     */       
/* 327 */       return true;
/*     */     }
/*     */     
/* 330 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileInfernalFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */