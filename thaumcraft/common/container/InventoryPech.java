/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ 
/*     */ public class InventoryPech implements IInventory
/*     */ {
/*     */   private final EntityPech theMerchant;
/*  13 */   private ItemStack[] theInventory = new ItemStack[5];
/*     */   private final EntityPlayer thePlayer;
/*     */   private Container eventHandler;
/*     */   
/*     */   public InventoryPech(EntityPlayer par1EntityPlayer, EntityPech par2IMerchant, Container par1Container)
/*     */   {
/*  19 */     this.thePlayer = par1EntityPlayer;
/*  20 */     this.theMerchant = par2IMerchant;
/*  21 */     this.eventHandler = par1Container;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  30 */     return this.theInventory.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  39 */     return this.theInventory[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  49 */     if (this.theInventory[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  53 */       if (this.theInventory[par1].field_77994_a <= par2)
/*     */       {
/*  55 */         ItemStack var3 = this.theInventory[par1];
/*  56 */         this.theInventory[par1] = null;
/*  57 */         this.eventHandler.func_75130_a(this);
/*  58 */         return var3;
/*     */       }
/*     */       
/*     */ 
/*  62 */       ItemStack var3 = this.theInventory[par1].func_77979_a(par2);
/*     */       
/*  64 */       if (this.theInventory[par1].field_77994_a == 0)
/*     */       {
/*  66 */         this.theInventory[par1] = null;
/*     */       }
/*     */       
/*  69 */       this.eventHandler.func_75130_a(this);
/*  70 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  75 */     return null;
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
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  88 */     if (this.theInventory[par1] != null)
/*     */     {
/*  90 */       ItemStack itemstack = this.theInventory[par1];
/*  91 */       this.theInventory[par1] = null;
/*  92 */       return itemstack;
/*     */     }
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
/* 106 */     this.theInventory[par1] = par2ItemStack;
/*     */     
/* 108 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 110 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/* 112 */     this.eventHandler.func_75130_a(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 122 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 131 */     return this.theMerchant.isTamed();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 140 */     return par1 == 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70296_d()
/*     */   {
/* 146 */     this.eventHandler.func_75130_a(this);
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 152 */     return "entity.Pech.name";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 157 */     return false;
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 162 */     return null;
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
/* 173 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */   public int func_174890_g()
/*     */   {
/* 181 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/InventoryPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */