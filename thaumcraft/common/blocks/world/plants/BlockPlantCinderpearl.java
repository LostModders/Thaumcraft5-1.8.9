/*    */ package thaumcraft.common.blocks.world.plants;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockBush;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.EnumPlantType;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class BlockPlantCinderpearl
/*    */   extends BlockBush
/*    */ {
/*    */   public BlockPlantCinderpearl()
/*    */   {
/* 23 */     super(Material.field_151585_k);
/* 24 */     func_149647_a(Thaumcraft.tabTC);
/* 25 */     func_149672_a(Block.field_149779_h);
/* 26 */     func_149715_a(0.5F);
/*    */   }
/*    */   
/*    */   protected boolean canPlaceBlockOn(Block ground, IBlockState state)
/*    */   {
/* 31 */     return (ground == Blocks.field_150354_m) || (ground == Blocks.field_150346_d) || (ground == Blocks.field_150406_ce) || (ground == Blocks.field_150405_ch);
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
/*    */   {
/* 37 */     return EnumPlantType.Desert;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(World world, BlockPos pos, IBlockState state, Random rand)
/*    */   {
/* 44 */     if (rand.nextBoolean()) {
/* 45 */       float xr = pos.func_177958_n() + 0.5F + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/* 46 */       float yr = pos.func_177956_o() + 0.6F + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/* 47 */       float zr = pos.func_177952_p() + 0.5F + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/* 48 */       world.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, xr, yr, zr, 0.0D, 0.0D, 0.0D, new int[0]);
/* 49 */       world.func_175688_a(EnumParticleTypes.FLAME, xr, yr, zr, 0.0D, 0.0D, 0.0D, new int[0]);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/plants/BlockPlantCinderpearl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */