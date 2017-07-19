/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileLampArcane;
/*     */ 
/*     */ public class BlockLamp extends BlockTCDevice implements IBlockFacing, IBlockEnabled
/*     */ {
/*     */   public BlockLamp(Class tc)
/*     */   {
/*  26 */     super(Material.field_151573_f, tc);
/*  27 */     func_149672_a(Block.field_149777_j);
/*  28 */     func_149711_c(1.0F);
/*  29 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  30 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.DOWN);
/*  31 */     bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true));
/*  32 */     func_180632_j(bs);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  38 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  49 */     return 0;
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, BlockPos pos)
/*     */   {
/*  54 */     return BlockStateUtils.isEnabled(world.func_180495_p(pos).func_177230_c().func_176201_c(world.func_180495_p(pos))) ? 15 : super.getLightValue(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  60 */     IBlockState bs = func_176223_P();
/*  61 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing.func_176734_d());
/*  62 */     bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*  63 */     return bs;
/*     */   }
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  68 */     TileEntity te = worldIn.func_175625_s(pos);
/*  69 */     if ((te != null) && ((te instanceof TileLampArcane))) {
/*  70 */       ((TileLampArcane)te).removeLights();
/*     */     }
/*  72 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/*  77 */     if (worldIn.func_175623_d(pos.func_177972_a(BlockStateUtils.getFacing(state)))) {
/*  78 */       func_176226_b(worldIn, pos, func_176223_P(), 0);
/*  79 */       worldIn.func_175698_g(pos);
/*  80 */       return;
/*     */     }
/*     */     
/*  83 */     TileEntity te = worldIn.func_175625_s(pos);
/*  84 */     if ((te != null) && ((te instanceof TileLampArcane)) && 
/*  85 */       (BlockStateUtils.isEnabled(state)) && (worldIn.func_175640_z(pos))) {
/*  86 */       ((TileLampArcane)te).removeLights();
/*     */     }
/*     */     
/*     */ 
/*  90 */     super.func_176204_a(worldIn, pos, state, neighborBlock);
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/*  95 */     func_149676_a(0.25F, 0.125F, 0.25F, 0.75F, 0.875F, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 101 */     func_180654_a(worldIn, pos);
/* 102 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockLamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */