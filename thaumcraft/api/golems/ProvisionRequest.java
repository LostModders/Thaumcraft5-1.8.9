/*    */ package thaumcraft.api.golems;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ProvisionRequest
/*    */ {
/*    */   private thaumcraft.api.golems.seals.ISealEntity seal;
/*    */   private ItemStack stack;
/*    */   
/*    */   ProvisionRequest(thaumcraft.api.golems.seals.ISealEntity seal, ItemStack stack) {
/* 11 */     this.seal = seal;
/* 12 */     this.stack = stack.func_77946_l();
/* 13 */     this.stack.field_77994_a = this.stack.func_77976_d();
/*    */   }
/*    */   
/*    */   public thaumcraft.api.golems.seals.ISealEntity getSeal() {
/* 17 */     return this.seal;
/*    */   }
/*    */   
/*    */   public ItemStack getStack() {
/* 21 */     return this.stack;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean equals(Object p_equals_1_)
/*    */   {
/* 27 */     if (this == p_equals_1_)
/*    */     {
/* 29 */       return true;
/*    */     }
/* 31 */     if (!(p_equals_1_ instanceof ProvisionRequest))
/*    */     {
/* 33 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 37 */     ProvisionRequest pr = (ProvisionRequest)p_equals_1_;
/* 38 */     return !this.seal.getSealPos().equals(pr.getSeal().getSealPos()) ? false : isItemStackEqual(this.stack, pr.getStack());
/*    */   }
/*    */   
/*    */ 
/*    */   private boolean isItemStackEqual(ItemStack first, ItemStack other)
/*    */   {
/* 44 */     return first.field_77994_a == other.field_77994_a;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/ProvisionRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */