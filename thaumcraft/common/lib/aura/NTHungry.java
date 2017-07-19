/*    */ package thaumcraft.common.lib.aura;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class NTHungry extends NTNormal
/*    */ {
/*    */   public NTHungry(int id)
/*    */   {
/*  9 */     super(id);
/*    */   }
/*    */   
/*    */ 
/*    */   void performTickEvent(EntityAuraNode node) {}
/*    */   
/*    */   void performPeriodicEvent(EntityAuraNode node)
/*    */   {
/* 17 */     if (node.field_70170_p.field_72995_K) return;
/* 18 */     thaumcraft.api.aspects.Aspect aspect = node.getAspect();
/* 19 */     int str = calculateStrength(node);
/* 20 */     float f = thaumcraft.api.aura.AuraHelper.getAura(node.field_70170_p, node.func_180425_c(), aspect) / thaumcraft.api.aura.AuraHelper.getAuraBase(node.field_70170_p, node.func_180425_c());
/* 21 */     if ((aspect != null) && (node.field_70170_p.field_73012_v.nextFloat() < f) && (AuraHandler.drainAura(node.field_70170_p, node.func_180425_c(), aspect, str)) && 
/* 22 */       (node.field_70170_p.field_73012_v.nextInt(1 + node.getNodeSize() * 2) == 0)) {
/* 23 */       node.setNodeSize(node.getNodeSize() + 1);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   int calculateStrength(EntityAuraNode node)
/*    */   {
/* 30 */     return (int)Math.max(1.0F, super.calculateStrength(node) * 0.1F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/NTHungry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */