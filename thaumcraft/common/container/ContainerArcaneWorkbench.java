/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*     */ 
/*     */ public class ContainerArcaneWorkbench extends Container
/*     */ {
/*     */   private TileArcaneWorkbench tileEntity;
/*     */   private InventoryPlayer ip;
/*     */   
/*     */   public ContainerArcaneWorkbench(InventoryPlayer par1InventoryPlayer, TileArcaneWorkbench e)
/*     */   {
/*  23 */     this.tileEntity = e;
/*  24 */     this.tileEntity.inventory.field_70465_c = this;
/*  25 */     this.ip = par1InventoryPlayer;
/*  26 */     func_75146_a(new SlotCraftingArcaneWorkbench(par1InventoryPlayer.field_70458_d, this.tileEntity.inventory, this.tileEntity.inventory, 9, 160, 64));
/*     */     
/*     */ 
/*  29 */     func_75146_a(new SlotLimitedByWand(this.tileEntity.inventory, 10, 160, 24));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  34 */     for (int var6 = 0; var6 < 3; var6++)
/*     */     {
/*  36 */       for (int var7 = 0; var7 < 3; var7++)
/*     */       {
/*  38 */         func_75146_a(new Slot(this.tileEntity.inventory, var7 + var6 * 3, 40 + var7 * 24, 40 + var6 * 24));
/*     */       }
/*     */     }
/*     */     
/*  42 */     for (var6 = 0; var6 < 3; var6++)
/*     */     {
/*  44 */       for (int var7 = 0; var7 < 9; var7++)
/*     */       {
/*  46 */         func_75146_a(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 16 + var7 * 18, 151 + var6 * 18));
/*     */       }
/*     */     }
/*     */     
/*  50 */     for (var6 = 0; var6 < 9; var6++)
/*     */     {
/*  52 */       func_75146_a(new Slot(par1InventoryPlayer, var6, 16 + var6 * 18, 209));
/*     */     }
/*     */     
/*  55 */     func_75130_a(this.tileEntity.inventory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75130_a(IInventory par1IInventory)
/*     */   {
/*  64 */     InventoryCrafting ic = new InventoryCrafting(new ContainerDummy(), 3, 3);
/*  65 */     for (int a = 0; a < 9; a++) {
/*  66 */       ic.func_70299_a(a, this.tileEntity.inventory.func_70301_a(a));
/*     */     }
/*  68 */     this.tileEntity.inventory.setInventorySlotContentsSoftly(9, CraftingManager.func_77594_a().func_82787_a(ic, this.tileEntity.func_145831_w()));
/*     */     
/*     */ 
/*  71 */     if ((this.tileEntity.inventory.func_70301_a(9) == null) && (this.tileEntity.inventory.func_70301_a(10) != null) && ((this.tileEntity.inventory.func_70301_a(10).func_77973_b() instanceof IWand)))
/*     */     {
/*     */ 
/*  74 */       IWand wand = (IWand)this.tileEntity.inventory.func_70301_a(10).func_77973_b();
/*     */       
/*  76 */       if (wand.consumeAllVis(this.tileEntity.inventory.func_70301_a(10), this.ip.field_70458_d, ThaumcraftCraftingManager.findMatchingArcaneRecipeAspects(this.tileEntity.inventory, this.ip.field_70458_d), false, true))
/*     */       {
/*     */ 
/*     */ 
/*  80 */         this.tileEntity.inventory.setInventorySlotContentsSoftly(9, ThaumcraftCraftingManager.findMatchingArcaneRecipe(this.tileEntity.inventory, this.ip.field_70458_d));
/*     */       }
/*     */     }
/*     */     
/*  84 */     this.tileEntity.func_70296_d();
/*  85 */     this.tileEntity.func_145831_w().func_175689_h(this.tileEntity.func_174877_v());
/*  86 */     func_75142_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/*  94 */     super.func_75134_a(par1EntityPlayer);
/*     */     
/*  96 */     if (!this.tileEntity.func_145831_w().field_72995_K)
/*     */     {
/*  98 */       this.tileEntity.inventory.field_70465_c = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 105 */     return this.tileEntity.func_145831_w().func_175625_s(this.tileEntity.func_174877_v()) == this.tileEntity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par1)
/*     */   {
/* 115 */     ItemStack var2 = null;
/* 116 */     Slot var3 = (Slot)this.field_75151_b.get(par1);
/*     */     
/* 118 */     if ((var3 != null) && (var3.func_75216_d()))
/*     */     {
/* 120 */       ItemStack var4 = var3.func_75211_c();
/* 121 */       var2 = var4.func_77946_l();
/*     */       
/* 123 */       if (par1 == 0)
/*     */       {
/* 125 */         if (!func_75135_a(var4, 11, 47, true))
/*     */         {
/* 127 */           return null;
/*     */         }
/*     */         
/* 130 */         var3.func_75220_a(var4, var2);
/*     */       }
/* 132 */       else if ((par1 >= 11) && (par1 < 38))
/*     */       {
/* 134 */         if (((var4.func_77973_b() instanceof IWand)) && (!((IWand)var4.func_77973_b()).isStaff(var4)))
/*     */         {
/* 136 */           if (!func_75135_a(var4, 1, 2, false)) {
/* 137 */             return null;
/*     */           }
/* 139 */           var3.func_75220_a(var4, var2);
/*     */         }
/* 141 */         else if (!func_75135_a(var4, 38, 47, false))
/*     */         {
/* 143 */           return null;
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 148 */       else if ((par1 >= 38) && (par1 < 47))
/*     */       {
/* 150 */         if (((var4.func_77973_b() instanceof IWand)) && (!((IWand)var4.func_77973_b()).isStaff(var4)))
/*     */         {
/* 152 */           if (!func_75135_a(var4, 1, 2, false)) {
/* 153 */             return null;
/*     */           }
/* 155 */           var3.func_75220_a(var4, var2);
/*     */         }
/* 157 */         else if (!func_75135_a(var4, 11, 38, false))
/*     */         {
/* 159 */           return null;
/*     */         }
/*     */         
/*     */       }
/* 163 */       else if (!func_75135_a(var4, 11, 47, false))
/*     */       {
/* 165 */         return null;
/*     */       }
/*     */       
/* 168 */       if (var4.field_77994_a == 0)
/*     */       {
/* 170 */         var3.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 174 */         var3.func_75218_e();
/*     */       }
/*     */       
/* 177 */       if (var4.field_77994_a == var2.field_77994_a)
/*     */       {
/* 179 */         return null;
/*     */       }
/*     */       
/* 182 */       var3.func_82870_a(this.ip.field_70458_d, var4);
/*     */     }
/*     */     
/* 185 */     return var2;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
/*     */   {
/* 191 */     if (par3 == 4) {
/* 192 */       par2 = 1;
/* 193 */       return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
/*     */     }
/* 195 */     if (((par1 == 0) || (par1 == 1)) && (par2 > 0)) par2 = 0;
/* 196 */     return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_94530_a(ItemStack stack, Slot slot)
/*     */   {
/* 203 */     return (slot.field_75224_c != this.tileEntity) && (super.func_94530_a(stack, slot));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerArcaneWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */