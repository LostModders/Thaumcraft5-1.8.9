/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*    */ 
/*    */ 
/*    */ public class SlotTurretBasic
/*    */   extends SlotMobEquipment
/*    */ {
/*    */   public SlotTurretBasic(EntityTurretCrossbow turret, int par3, int par4, int par5)
/*    */   {
/* 14 */     super(turret, par3, par4, par5);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_75214_a(ItemStack stack)
/*    */   {
/* 21 */     return (stack != null) && (stack.func_77973_b() != null) && ((stack.func_77973_b() == Items.field_151032_g) || (stack.func_77973_b() == ItemsTC.primalArrows));
/*    */   }
/*    */   
/*    */   public void func_75218_e() {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/SlotTurretBasic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */