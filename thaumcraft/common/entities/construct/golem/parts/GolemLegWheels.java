/*    */ package thaumcraft.common.entities.construct.golem.parts;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ 
/*    */ public class GolemLegWheels implements thaumcraft.api.golems.parts.GolemLeg.ILegFunction
/*    */ {
/* 15 */   public static HashMap<Integer, Float> ani = new HashMap();
/*    */   
/*    */   public void onUpdateTick(IGolemAPI golem)
/*    */   {
/* 19 */     if (golem.getGolemWorld().field_72995_K)
/*    */     {
/* 21 */       double dist = golem.getGolemEntity().func_70011_f(golem.getGolemEntity().field_70169_q, golem.getGolemEntity().field_70167_r, golem.getGolemEntity().field_70166_s);
/* 22 */       float lastRot = 0.0F;
/*    */       
/* 24 */       if (ani.containsKey(Integer.valueOf(golem.getGolemEntity().func_145782_y()))) {
/* 25 */         lastRot = ((Float)ani.get(Integer.valueOf(golem.getGolemEntity().func_145782_y()))).floatValue();
/*    */       }
/*    */       
/* 28 */       double d0 = golem.getGolemEntity().field_70165_t - golem.getGolemEntity().field_70169_q;
/* 29 */       double d1 = golem.getGolemEntity().field_70163_u - golem.getGolemEntity().field_70167_r;
/* 30 */       double d2 = golem.getGolemEntity().field_70161_v - golem.getGolemEntity().field_70166_s;
/* 31 */       float dirTravel = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/*    */       
/* 33 */       double dir = 360.0F - (golem.getGolemEntity().field_70177_z - dirTravel);
/* 34 */       lastRot = (float)(lastRot + dist / 1.571D * dir);
/* 35 */       if (lastRot > 360.0F) { lastRot -= 360.0F;
/*    */       }
/* 37 */       ani.put(Integer.valueOf(golem.getGolemEntity().func_145782_y()), Float.valueOf(lastRot));
/*    */       
/* 39 */       if ((golem.getGolemEntity().field_70122_E) && (!golem.getGolemEntity().func_70090_H()) && (dist > 0.25D)) {
/* 40 */         int i = MathHelper.func_76128_c(golem.getGolemEntity().field_70165_t);
/* 41 */         int j = MathHelper.func_76128_c(golem.getGolemEntity().field_70163_u - 0.20000000298023224D);
/* 42 */         int k = MathHelper.func_76128_c(golem.getGolemEntity().field_70161_v);
/* 43 */         BlockPos blockpos = new BlockPos(i, j, k);
/* 44 */         IBlockState iblockstate = golem.getGolemEntity().field_70170_p.func_180495_p(blockpos);
/* 45 */         Block block = iblockstate.func_177230_c();
/*    */         
/* 47 */         if (block.func_149645_b() != -1)
/*    */         {
/* 49 */           golem.getGolemEntity().field_70170_p.func_175688_a(net.minecraft.util.EnumParticleTypes.BLOCK_CRACK, golem.getGolemEntity().field_70165_t + (golem.getGolemWorld().field_73012_v.nextFloat() - 0.5D) * golem.getGolemEntity().field_70130_N, golem.getGolemEntity().func_174813_aQ().field_72338_b + 0.1D, golem.getGolemEntity().field_70161_v + (golem.getGolemWorld().field_73012_v.nextFloat() - 0.5D) * golem.getGolemEntity().field_70130_N, -golem.getGolemEntity().field_70159_w * 4.0D, 1.5D, -golem.getGolemEntity().field_70179_y * 4.0D, new int[] { Block.func_176210_f(iblockstate) });
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/parts/GolemLegWheels.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */