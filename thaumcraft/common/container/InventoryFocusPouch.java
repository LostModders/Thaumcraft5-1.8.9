/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryFocusPouch
/*     */   implements IInventory
/*     */ {
/*     */   public ItemStack[] stackList;
/*     */   private Container eventHandler;
/*     */   
/*     */   public InventoryFocusPouch(Container par1Container)
/*     */   {
/*  22 */     this.stackList = new ItemStack[18];
/*  23 */     this.eventHandler = par1Container;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  32 */     return this.stackList.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  41 */     return par1 >= func_70302_i_() ? null : this.stackList[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  51 */     if (this.stackList[par1] != null)
/*     */     {
/*  53 */       ItemStack var2 = this.stackList[par1];
/*  54 */       this.stackList[par1] = null;
/*  55 */       return var2;
/*     */     }
/*     */     
/*     */ 
/*  59 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  70 */     if (this.stackList[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  74 */       if (this.stackList[par1].field_77994_a <= par2)
/*     */       {
/*  76 */         ItemStack var3 = this.stackList[par1];
/*  77 */         this.stackList[par1] = null;
/*  78 */         this.eventHandler.func_75130_a(this);
/*  79 */         return var3;
/*     */       }
/*     */       
/*     */ 
/*  83 */       ItemStack var3 = this.stackList[par1].func_77979_a(par2);
/*     */       
/*  85 */       if (this.stackList[par1].field_77994_a == 0)
/*     */       {
/*  87 */         this.stackList[par1] = null;
/*     */       }
/*     */       
/*  90 */       this.eventHandler.func_75130_a(this);
/*  91 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  96 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 106 */     this.stackList[par1] = par2ItemStack;
/* 107 */     this.eventHandler.func_75130_a(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 117 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 126 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 132 */     return (itemstack != null) && ((itemstack.func_77973_b() instanceof ItemFocusBasic));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70296_d() {}
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 141 */     return "container.focuspouch";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 151 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 162 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */   public int func_174890_g()
/*     */   {
/* 170 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/InventoryFocusPouch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */