/*    */ package thaumcraft.common.blocks.world;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockGrass;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.EnumSkyBlock;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ 
/*    */ public class BlockGrassAmbient
/*    */   extends BlockGrass
/*    */ {
/*    */   public BlockGrassAmbient()
/*    */   {
/* 24 */     func_149647_a(Thaumcraft.tabTC);
/* 25 */     func_149711_c(0.6F);
/* 26 */     func_149672_a(field_149779_h);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*    */   {
/* 32 */     BiomeGenBase biome = worldIn.func_180494_b(pos);
/*    */     
/* 34 */     int i = worldIn.func_175642_b(EnumSkyBlock.SKY, pos.func_177984_a()) - worldIn.func_175657_ab();
/* 35 */     float f = worldIn.func_72929_e(1.0F);
/* 36 */     float f1 = f < 3.1415927F ? 0.0F : 6.2831855F;
/* 37 */     f += (f1 - f) * 0.2F;
/* 38 */     i = Math.round(i * MathHelper.func_76134_b(f));
/* 39 */     i = MathHelper.func_76125_a(i, 0, 15);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 46 */     if (2 + i * 2 < 1 + rand.nextInt(13))
/*    */     {
/* 48 */       int x = MathHelper.func_76136_a(rand, -8, 8);
/* 49 */       int z = MathHelper.func_76136_a(rand, -8, 8);
/* 50 */       BlockPos pp = pos.func_177982_a(x, 5, z);
/* 51 */       int q = 0;
/* 52 */       while ((q < 10) && (pp.func_177956_o() > 50) && (worldIn.func_180495_p(pp).func_177230_c() != Blocks.field_150349_c)) {
/* 53 */         pp = pp.func_177977_b();
/* 54 */         q++;
/*    */       }
/* 56 */       if (worldIn.func_180495_p(pp).func_177230_c() == Blocks.field_150349_c) {
/* 57 */         Thaumcraft.proxy.getFX().drawWispyMotesOnBlock(pp.func_177984_a(), 400);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/BlockGrassAmbient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */