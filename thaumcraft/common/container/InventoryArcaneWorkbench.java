/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import thaumcraft.api.crafting.IArcaneWorkbench;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ 
/*     */ public class InventoryArcaneWorkbench extends InventoryCrafting implements ISidedInventory, IArcaneWorkbench
/*     */ {
/*     */   public ItemStack[] field_70466_a;
/*     */   public Container field_70465_c;
/*     */   private final int inventoryWidth;
/*     */   private final int inventoryHeight;
/*     */   
/*     */   public InventoryArcaneWorkbench(Container p_i1807_1_, int width, int height)
/*     */   {
/*  20 */     super(p_i1807_1_, width, height);
/*  21 */     int k = width * height;
/*  22 */     this.field_70466_a = new ItemStack[k + 2];
/*  23 */     this.field_70465_c = p_i1807_1_;
/*  24 */     this.inventoryWidth = width;
/*  25 */     this.inventoryHeight = height;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  31 */     return 9;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int index)
/*     */   {
/*  37 */     return index >= 11 ? null : this.field_70466_a[index];
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70463_b(int row, int column)
/*     */   {
/*  43 */     return (row >= 0) && (row < this.inventoryWidth) && (column >= 0) && (column <= this.inventoryHeight) ? func_70301_a(row + column * this.inventoryWidth) : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/*  49 */     return "container.arcaneworkbench";
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70304_b(int index)
/*     */   {
/*  55 */     if (this.field_70466_a[index] != null)
/*     */     {
/*  57 */       ItemStack itemstack = this.field_70466_a[index];
/*  58 */       this.field_70466_a[index] = null;
/*  59 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*  63 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int index, int count)
/*     */   {
/*  70 */     if (this.field_70466_a[index] != null)
/*     */     {
/*     */ 
/*     */ 
/*  74 */       if (this.field_70466_a[index].field_77994_a <= count)
/*     */       {
/*  76 */         ItemStack itemstack = this.field_70466_a[index];
/*  77 */         this.field_70466_a[index] = null;
/*  78 */         try { this.field_70465_c.func_75130_a(this); } catch (Exception e) {}
/*  79 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  83 */       ItemStack itemstack = this.field_70466_a[index].func_77979_a(count);
/*     */       
/*  85 */       if (this.field_70466_a[index].field_77994_a == 0)
/*     */       {
/*  87 */         this.field_70466_a[index] = null;
/*     */       }
/*     */       try {
/*  90 */         this.field_70465_c.func_75130_a(this); } catch (Exception e) {}
/*  91 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  96 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int index, ItemStack stack)
/*     */   {
/* 103 */     this.field_70466_a[index] = stack;
/* 104 */     try { this.field_70465_c.func_75130_a(this);
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public void func_174888_l() {
/* 110 */     for (int i = 0; i < this.field_70466_a.length; i++)
/*     */     {
/* 112 */       this.field_70466_a[i] = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_174923_h()
/*     */   {
/* 119 */     return this.inventoryHeight;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_174922_i()
/*     */   {
/* 125 */     return this.inventoryWidth;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 130 */     if ((i == 10) && (itemstack != null)) {
/* 131 */       if (!(itemstack.func_77973_b() instanceof IWand)) return false;
/* 132 */       IWand wand = (IWand)itemstack.func_77973_b();
/* 133 */       return !wand.isStaff(itemstack);
/*     */     }
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_180462_a(int i, ItemStack itemstack, EnumFacing direction)
/*     */   {
/* 140 */     if ((i != 10) || (itemstack == null) || (!(itemstack.func_77973_b() instanceof IWand))) return false;
/* 141 */     IWand wand = (IWand)itemstack.func_77973_b();
/* 142 */     return !wand.isStaff(itemstack);
/*     */   }
/*     */   
/*     */   public boolean func_180461_b(int i, ItemStack itemstack, EnumFacing direction)
/*     */   {
/* 147 */     return i == 10;
/*     */   }
/*     */   
/*     */   public int[] func_180463_a(EnumFacing side)
/*     */   {
/* 152 */     return new int[] { 10 };
/*     */   }
/*     */   
/*     */   public void setInventorySlotContentsSoftly(int index, ItemStack stack)
/*     */   {
/* 157 */     this.field_70466_a[index] = stack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/InventoryArcaneWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */