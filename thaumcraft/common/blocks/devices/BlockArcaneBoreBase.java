/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumFacing.Axis;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*    */ import thaumcraft.common.tiles.devices.TileArcaneBoreBase;
/*    */ 
/*    */ public class BlockArcaneBoreBase extends BlockTCDevice implements IBlockFacingHorizontal
/*    */ {
/*    */   public BlockArcaneBoreBase()
/*    */   {
/* 17 */     super(net.minecraft.block.material.Material.field_151575_d, TileArcaneBoreBase.class);
/* 18 */     func_149672_a(Block.field_149766_f);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_149645_b()
/*    */   {
/* 24 */     return -1;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 30 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public int func_180651_a(IBlockState state)
/*    */   {
/* 41 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side)
/*    */   {
/* 47 */     return side.func_176740_k() == EnumFacing.Axis.Y;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockArcaneBoreBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */