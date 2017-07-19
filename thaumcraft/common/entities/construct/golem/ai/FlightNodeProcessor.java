/*    */ package thaumcraft.common.entities.construct.golem.ai;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.pathfinding.PathPoint;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.pathfinder.NodeProcessor;
/*    */ 
/*    */ public class FlightNodeProcessor extends NodeProcessor
/*    */ {
/*    */   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_)
/*    */   {
/* 18 */     super.func_176162_a(p_176162_1_, p_176162_2_);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_176163_a()
/*    */   {
/* 24 */     super.func_176163_a();
/*    */   }
/*    */   
/*    */ 
/*    */   public PathPoint func_176161_a(Entity p_176161_1_)
/*    */   {
/* 30 */     return func_176159_a(MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72340_a), MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72338_b + 0.5D), MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72339_c));
/*    */   }
/*    */   
/*    */ 
/*    */   public PathPoint func_176160_a(Entity p_176160_1_, double p_176160_2_, double p_176160_4_, double p_176160_6_)
/*    */   {
/* 36 */     return func_176159_a(MathHelper.func_76128_c(p_176160_2_ - p_176160_1_.field_70130_N / 2.0F), MathHelper.func_76128_c(p_176160_4_ + 0.5D), MathHelper.func_76128_c(p_176160_6_ - p_176160_1_.field_70130_N / 2.0F));
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_176164_a(PathPoint[] p_176164_1_, Entity p_176164_2_, PathPoint p_176164_3_, PathPoint p_176164_4_, float p_176164_5_)
/*    */   {
/* 42 */     int i = 0;
/* 43 */     EnumFacing[] aenumfacing = EnumFacing.values();
/* 44 */     int j = aenumfacing.length;
/*    */     
/* 46 */     for (int k = 0; k < j; k++)
/*    */     {
/* 48 */       EnumFacing enumfacing = aenumfacing[k];
/* 49 */       PathPoint pathpoint2 = func_176185_a(p_176164_2_, p_176164_3_.field_75839_a + enumfacing.func_82601_c(), p_176164_3_.field_75837_b + enumfacing.func_96559_d(), p_176164_3_.field_75838_c + enumfacing.func_82599_e());
/*    */       
/* 51 */       if ((pathpoint2 != null) && (!pathpoint2.field_75842_i) && (pathpoint2.func_75829_a(p_176164_4_) < p_176164_5_))
/*    */       {
/* 53 */         p_176164_1_[(i++)] = pathpoint2;
/*    */       }
/*    */     }
/*    */     
/* 57 */     return i;
/*    */   }
/*    */   
/*    */   private PathPoint func_176185_a(Entity p_176185_1_, int p_176185_2_, int p_176185_3_, int p_176185_4_)
/*    */   {
/* 62 */     int l = func_176186_b(p_176185_1_, p_176185_2_, p_176185_3_, p_176185_4_);
/* 63 */     return l == -1 ? func_176159_a(p_176185_2_, p_176185_3_, p_176185_4_) : null;
/*    */   }
/*    */   
/*    */   private int func_176186_b(Entity p_176186_1_, int p_176186_2_, int p_176186_3_, int p_176186_4_)
/*    */   {
/* 68 */     for (int l = p_176186_2_; l < p_176186_2_ + this.field_176168_c; l++)
/*    */     {
/* 70 */       for (int i1 = p_176186_3_; i1 < p_176186_3_ + this.field_176165_d; i1++)
/*    */       {
/* 72 */         for (int j1 = p_176186_4_; j1 < p_176186_4_ + this.field_176166_e; j1++)
/*    */         {
/* 74 */           BlockPos blockpos = new BlockPos(l, i1, j1);
/* 75 */           Block block = this.field_176169_a.func_180495_p(blockpos).func_177230_c();
/*    */           
/* 77 */           if ((!this.field_176169_a.func_175623_d(blockpos)) && (!block.func_176205_b(this.field_176169_a, blockpos)))
/*    */           {
/* 79 */             return 0;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 85 */     return -1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/FlightNodeProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */