/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumFacing.Axis;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class BlockSmelterVent extends BlockTC implements IBlockFacingHorizontal
/*     */ {
/*     */   public BlockSmelterVent()
/*     */   {
/*  26 */     super(net.minecraft.block.material.Material.field_151573_f);
/*  27 */     func_149672_a(Block.field_149777_j);
/*  28 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.NORTH));
/*  29 */     func_149711_c(1.0F);
/*  30 */     func_149752_b(10.0F);
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*     */   {
/*  35 */     return true;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  40 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */   
/*     */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/*  48 */     return (super.func_176198_a(worldIn, pos, side)) && (side.func_176740_k().func_176722_c()) && ((worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177230_c() instanceof BlockSmelter)) && (BlockStateUtils.getFacing(worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d()))) != side);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  56 */     IBlockState bs = func_176223_P();
/*  57 */     if (!facing.func_176740_k().func_176722_c()) facing = EnumFacing.NORTH;
/*  58 */     bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, facing.func_176734_d());
/*  59 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  65 */     IBlockState bs = func_176223_P();
/*  66 */     bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, BlockStateUtils.getFacing(meta));
/*  67 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  73 */     return 0x0 | ((EnumFacing)state.func_177229_b(IBlockFacingHorizontal.FACING)).func_176745_a();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  79 */     ArrayList<IProperty> ip = new ArrayList();
/*  80 */     ip.add(IBlockFacingHorizontal.FACING);
/*  81 */     return ip.size() == 0 ? super.func_180661_e() : new BlockState(this, (IProperty[])ip.toArray(new IProperty[ip.size()]));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  87 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/*  98 */     EnumFacing facing = BlockStateUtils.getFacing(func_176201_c(worldIn.func_180495_p(pos)));
/*  99 */     switch (facing.ordinal()) {
/* 100 */     case 2:  func_149676_a(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.5F); break;
/* 101 */     case 3:  func_149676_a(0.125F, 0.125F, 0.5F, 0.875F, 0.875F, 1.0F); break;
/* 102 */     case 4:  func_149676_a(0.0F, 0.125F, 0.125F, 0.5F, 0.875F, 0.875F); break;
/* 103 */     case 5:  func_149676_a(0.5F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 110 */     func_180654_a(worldIn, pos);
/* 111 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockSmelterVent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */