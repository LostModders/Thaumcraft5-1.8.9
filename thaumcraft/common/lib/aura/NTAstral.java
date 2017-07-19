/*    */ package thaumcraft.common.lib.aura;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class NTAstral extends NTNormal {
/*  6 */   public NTAstral(int id) { super(id); }
/*    */   
/*    */ 
/*    */ 
/*    */   int calculateStrength(EntityAuraNode node)
/*    */   {
/* 12 */     int m = node.field_70170_p.field_73011_w.func_76559_b(node.field_70170_p.func_72912_H().func_76073_f());
/* 13 */     float b = 1.0F + (Math.abs(m - 4) - 2) / 5.0F;
/* 14 */     b -= (node.func_70013_c(1.0F) - 0.5F) / 3.0F;
/* 15 */     return (int)Math.max(1.0D, Math.sqrt(node.getNodeSize() / 3.0F) * b);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/NTAstral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */