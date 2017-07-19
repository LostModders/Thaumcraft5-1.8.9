/*    */ package thaumcraft.common.lib.aura;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class NTPure extends NTNormal
/*    */ {
/*    */   public NTPure(int id)
/*    */   {
/*  9 */     super(id);
/*    */   }
/*    */   
/*    */ 
/*    */   void performPeriodicEvent(EntityAuraNode node)
/*    */   {
/* 15 */     super.performPeriodicEvent(node);
/* 16 */     if ((AuraHandler.drainAura(node.field_70170_p, new net.minecraft.util.BlockPos(node.field_70165_t, node.field_70163_u, node.field_70161_v), thaumcraft.api.aspects.Aspect.FLUX, 1)) && (node.field_70170_p.field_73012_v.nextFloat() < 0.025F))
/*    */     {
/* 18 */       node.setNodeSize(node.getNodeSize() - 1);
/* 19 */       if (node.getNodeSize() <= 0) node.func_70106_y();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/NTPure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */