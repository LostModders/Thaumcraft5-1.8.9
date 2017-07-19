/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.common.entities.construct.EntityTurretFocus;
/*    */ 
/*    */ 
/*    */ public class SlotTurretFocus
/*    */   extends SlotMobEquipment
/*    */ {
/*    */   public SlotTurretFocus(EntityTurretFocus entity, int par3, int par4, int par5)
/*    */   {
/* 13 */     super(entity, par3, par4, par5);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_75214_a(ItemStack stack)
/*    */   {
/* 20 */     return (stack != null) && (stack.func_77973_b() != null) && ((stack.func_77973_b() instanceof ItemFocusBasic)) && (((ItemFocusBasic)stack.func_77973_b()).canBePlacedInTurret());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_75218_e()
/*    */   {
/* 27 */     ((EntityTurretFocus)this.entity).updateFocus();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/SlotTurretFocus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */