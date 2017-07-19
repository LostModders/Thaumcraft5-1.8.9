/*    */ package thaumcraft.common.lib.aura;
/*    */ 
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import thaumcraft.common.lib.utils.TCVec3;
/*    */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*    */ 
/*    */ public class NTDark extends NTNormal
/*    */ {
/*    */   public NTDark(int id)
/*    */   {
/* 13 */     super(id);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   void performPeriodicEvent(EntityAuraNode node)
/*    */   {
/* 20 */     super.performPeriodicEvent(node);
/*    */     
/* 22 */     int rad = node.field_70170_p.field_73012_v.nextInt(180) * 2;
/*    */     
/* 24 */     TCVec3 vsource = TCVec3.createVectorHelper(node.field_70165_t, node.field_70163_u, node.field_70161_v);
/* 25 */     int r = (int)(4.0D + Math.sqrt(node.getNodeSize()));
/* 26 */     for (int q = 0; q < r; q++) {
/* 27 */       TCVec3 vtar = TCVec3.createVectorHelper(q, 0.0D, 0.0D);
/* 28 */       vtar.rotateAroundY(rad / 180.0F * 3.1415927F);
/* 29 */       TCVec3 vres = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
/* 30 */       net.minecraft.util.BlockPos t = new net.minecraft.util.BlockPos(MathHelper.func_76128_c(vres.xCoord), MathHelper.func_76128_c(vres.yCoord), MathHelper.func_76128_c(vres.zCoord));
/*    */       
/* 32 */       BiomeGenBase biome = node.field_70170_p.func_180494_b(t);
/* 33 */       if (biome.field_76756_M != BiomeHandler.biomeEerie.field_76756_M) {
/* 34 */         thaumcraft.common.lib.utils.Utils.setBiomeAt(node.field_70170_p, t, BiomeHandler.biomeEerie);
/* 35 */         break;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   int calculateStrength(EntityAuraNode node)
/*    */   {
/* 42 */     int m = node.field_70170_p.field_73011_w.func_76559_b(node.field_70170_p.func_72912_H().func_76073_f());
/* 43 */     float b = 1.0F - (Math.abs(m - 4) - 2) / 5.0F;
/* 44 */     b += (node.func_70013_c(1.0F) - 0.5F) / 3.0F;
/* 45 */     return (int)Math.max(1.0D, Math.sqrt(node.getNodeSize() / 3.0F) * b);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/NTDark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */