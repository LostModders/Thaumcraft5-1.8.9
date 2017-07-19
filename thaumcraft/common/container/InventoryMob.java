/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryMob
/*     */   implements IInventory
/*     */ {
/*     */   public ItemStack[] inventory;
/*     */   public Entity ent;
/*     */   public boolean inventoryChanged;
/*     */   public int slotCount;
/*  21 */   public int stacklimit = 64;
/*     */   
/*     */   public InventoryMob(Entity entity, int slots) {
/*  24 */     this.slotCount = slots;
/*  25 */     this.inventory = new ItemStack[this.slotCount];
/*  26 */     this.inventoryChanged = false;
/*  27 */     this.ent = entity;
/*     */   }
/*     */   
/*     */   public InventoryMob(Entity entity, int slots, int lim) {
/*  31 */     this.slotCount = slots;
/*  32 */     this.inventory = new ItemStack[this.slotCount];
/*  33 */     this.inventoryChanged = false;
/*  34 */     this.stacklimit = lim;
/*  35 */     this.ent = entity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int i, int j)
/*     */   {
/* 125 */     ItemStack[] aitemstack = this.inventory;
/* 126 */     if (aitemstack[i] != null) {
/* 127 */       if (aitemstack[i].field_77994_a <= j) {
/* 128 */         ItemStack itemstack = aitemstack[i];
/* 129 */         aitemstack[i] = null;
/* 130 */         return itemstack;
/*     */       }
/*     */       
/* 133 */       ItemStack itemstack1 = aitemstack[i].func_77979_a(j);
/* 134 */       if (aitemstack[i].field_77994_a == 0) {
/* 135 */         aitemstack[i] = null;
/*     */       }
/* 137 */       return itemstack1;
/*     */     }
/*     */     
/* 140 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int i, ItemStack itemstack)
/*     */   {
/* 146 */     ItemStack[] aitemstack = this.inventory;
/* 147 */     aitemstack[i] = itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/* 180 */     return this.inventory.length + 1;
/*     */   }
/*     */   
/*     */   public ItemStack func_70301_a(int i)
/*     */   {
/* 185 */     ItemStack[] aitemstack = this.inventory;
/* 186 */     return aitemstack[i];
/*     */   }
/*     */   
/*     */   public int func_70297_j_()
/*     */   {
/* 191 */     return this.stacklimit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer entityplayer)
/*     */   {
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack func_70304_b(int var1)
/*     */   {
/* 227 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 302 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70296_d()
/*     */   {
/* 307 */     this.inventoryChanged = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 313 */     return "Inventory";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 318 */     return false;
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 323 */     return null;
/*     */   }
/*     */   
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
/* 342 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */   public int func_174890_g()
/*     */   {
/* 350 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/InventoryMob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */