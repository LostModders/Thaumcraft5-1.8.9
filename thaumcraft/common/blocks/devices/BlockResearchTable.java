/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ public class BlockResearchTable extends BlockTCDevice implements IBlockFacingHorizontal
/*    */ {
/*    */   public BlockResearchTable()
/*    */   {
/* 21 */     super(Material.field_151575_d, TileResearchTable.class);
/* 22 */     func_149672_a(Block.field_149766_f);
/*    */   }
/*    */   
/*    */   public int func_180651_a(IBlockState state)
/*    */   {
/* 27 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side)
/*    */   {
/* 44 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 52 */     if (world.field_72995_K) { return true;
/*    */     }
/* 54 */     player.openGui(Thaumcraft.instance, 10, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */     
/* 56 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*    */   {
/* 62 */     IBlockState bs = func_176223_P();
/* 63 */     bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_174811_aO());
/* 64 */     return bs;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockResearchTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */