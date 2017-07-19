/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockFacing;
/*    */ import thaumcraft.common.tiles.essentia.TileCrystallizer;
/*    */ 
/*    */ public class BlockCrystallizer extends BlockTCDevice implements IBlockFacing
/*    */ {
/*    */   public BlockCrystallizer()
/*    */   {
/* 18 */     super(Material.field_151575_d, TileCrystallizer.class);
/* 19 */     func_149672_a(Block.field_149766_f);
/*    */   }
/*    */   
/*    */   public int func_180651_a(IBlockState state)
/*    */   {
/* 24 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 30 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_149645_b()
/*    */   {
/* 42 */     return -1;
/*    */   }
/*    */   
/*    */ 
/*    */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*    */   {
/* 48 */     IBlockState bs = func_176223_P();
/* 49 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing.func_176734_d());
/* 50 */     return bs;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockCrystallizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */