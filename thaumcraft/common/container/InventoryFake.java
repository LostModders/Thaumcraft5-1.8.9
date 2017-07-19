/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class InventoryFake implements IInventory
/*     */ {
/*     */   private ItemStack[] stackList;
/*     */   
/*     */   public InventoryFake(ItemStack[] stackList)
/*     */   {
/*  15 */     this.stackList = stackList;
/*     */   }
/*     */   
/*     */   public InventoryFake(ArrayList<ItemStack> stackList) {
/*  19 */     this.stackList = ((ItemStack[])stackList.toArray(new ItemStack[0]));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  27 */     return this.stackList.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  35 */     return par1 >= func_70302_i_() ? null : this.stackList[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  45 */     if (this.stackList[par1] != null) {
/*  46 */       ItemStack var2 = this.stackList[par1];
/*  47 */       this.stackList[par1] = null;
/*  48 */       return var2;
/*     */     }
/*  50 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  60 */     if (this.stackList[par1] != null)
/*     */     {
/*     */ 
/*  63 */       if (this.stackList[par1].field_77994_a <= par2) {
/*  64 */         ItemStack var3 = this.stackList[par1];
/*  65 */         this.stackList[par1] = null;
/*  66 */         return var3;
/*     */       }
/*  68 */       ItemStack var3 = this.stackList[par1].func_77979_a(par2);
/*     */       
/*  70 */       if (this.stackList[par1].field_77994_a == 0) {
/*  71 */         this.stackList[par1] = null;
/*     */       }
/*     */       
/*  74 */       return var3;
/*     */     }
/*     */     
/*  77 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/*  87 */     this.stackList[par1] = par2ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/*  96 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 105 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70296_d() {}
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 119 */     return "container.fake";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 129 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 141 */     return 0;
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
/* 153 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/InventoryFake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */