/*    */ package thaumcraft.common.lib.aura;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ public class NTTaint extends NTNormal
/*    */ {
/*    */   public NTTaint(int id) {
/*  8 */     super(id);
/*    */   }
/*    */   
/*    */   void performPeriodicEvent(EntityAuraNode node)
/*    */   {
/* 13 */     super.performPeriodicEvent(node);
/* 14 */     float f = AuraHandler.getAuraCurrent(node.field_70170_p, node.func_180425_c(), Aspect.FLUX) / AuraHandler.getAuraBase(node.field_70170_p, node.func_180425_c());
/* 15 */     if (node.field_70170_p.field_73012_v.nextFloat() > f * 0.8F) {
/* 16 */       AuraHandler.addNodeRechargeTicket(node, Aspect.FLUX, (int)Math.max(1.0D, super.calculateStrength(node) * 0.2D));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/NTTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */