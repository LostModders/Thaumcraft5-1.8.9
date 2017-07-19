/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotMobEquipment
/*    */   extends Slot
/*    */ {
/*    */   EntityLiving entity;
/*    */   
/*    */   public SlotMobEquipment(EntityLiving entity, int par3, int par4, int par5)
/*    */   {
/* 15 */     super(null, par3, par4, par5);
/* 16 */     this.entity = entity;
/*    */   }
/*    */   
/*    */ 
/*    */   public ItemStack func_75211_c()
/*    */   {
/* 22 */     return this.entity.func_71124_b(getSlotIndex());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_75215_d(ItemStack stack)
/*    */   {
/* 29 */     this.entity.func_70062_b(getSlotIndex(), stack);
/*    */     
/* 31 */     if ((stack != null) && (stack.field_77994_a > func_75219_a()))
/*    */     {
/* 33 */       stack.field_77994_a = func_75219_a();
/*    */     }
/* 35 */     func_75218_e();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75218_e() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public int func_75219_a()
/*    */   {
/* 47 */     return 64;
/*    */   }
/*    */   
/*    */ 
/*    */   public ItemStack func_75209_a(int amount)
/*    */   {
/* 53 */     if (func_75211_c() != null)
/*    */     {
/*    */ 
/*    */ 
/* 57 */       if (func_75211_c().field_77994_a <= amount)
/*    */       {
/* 59 */         ItemStack itemstack = func_75211_c();
/* 60 */         func_75215_d(null);
/* 61 */         return itemstack;
/*    */       }
/*    */       
/*    */ 
/* 65 */       ItemStack itemstack = func_75211_c().func_77979_a(amount);
/* 66 */       if (func_75211_c().field_77994_a == 0)
/*    */       {
/* 68 */         func_75215_d(null);
/*    */       }
/* 70 */       return itemstack;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 75 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75217_a(IInventory inv, int slotIn)
/*    */   {
/* 83 */     return slotIn == getSlotIndex();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/SlotMobEquipment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */