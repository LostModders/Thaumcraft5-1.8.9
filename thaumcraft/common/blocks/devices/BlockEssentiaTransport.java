/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockFacing;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ 
/*    */ public class BlockEssentiaTransport
/*    */   extends BlockTCDevice implements IBlockFacing
/*    */ {
/*    */   public BlockEssentiaTransport(Class te)
/*    */   {
/* 25 */     super(Material.field_151573_f, te);
/* 26 */     func_149672_a(Block.field_149777_j);
/* 27 */     func_149711_c(1.0F);
/* 28 */     func_149752_b(10.0F);
/* 29 */     IBlockState bs = this.field_176227_L.func_177621_b();
/* 30 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/* 31 */     func_180632_j(bs);
/*    */   }
/*    */   
/*    */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*    */   {
/* 36 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 42 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public int func_180651_a(IBlockState state)
/*    */   {
/* 53 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*    */   {
/* 59 */     IBlockState bs = func_176223_P();
/* 60 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing);
/* 61 */     return bs;
/*    */   }
/*    */   
/*    */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*    */   {
/* 66 */     EnumFacing facing = BlockStateUtils.getFacing(func_176201_c(worldIn.func_180495_p(pos)));
/* 67 */     switch (facing.ordinal()) {
/* 68 */     case 0:  func_149676_a(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F); break;
/* 69 */     case 1:  func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F); break;
/* 70 */     case 2:  func_149676_a(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F); break;
/* 71 */     case 3:  func_149676_a(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F); break;
/* 72 */     case 4:  func_149676_a(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F); break;
/* 73 */     case 5:  func_149676_a(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
/*    */     }
/*    */     
/*    */   }
/*    */   
/*    */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*    */   {
/* 80 */     func_180654_a(worldIn, pos);
/* 81 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockEssentiaTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */