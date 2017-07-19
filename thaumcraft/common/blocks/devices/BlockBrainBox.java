/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class BlockBrainBox extends BlockTC implements IBlockFacingHorizontal, IBlockEnabled
/*     */ {
/*     */   public BlockBrainBox()
/*     */   {
/*  25 */     super(Material.field_151573_f);
/*  26 */     func_149672_a(Block.field_149777_j);
/*  27 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  28 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  29 */     func_180632_j(bs);
/*  30 */     func_149711_c(1.0F);
/*  31 */     func_149752_b(10.0F);
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*     */   {
/*  36 */     return true;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  41 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  46 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  52 */     return false;
/*     */   }
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/*  57 */     if (worldIn.func_180495_p(pos.func_177972_a(BlockStateUtils.getFacing(state))).func_177230_c() != BlocksTC.thaumatorium) {
/*  58 */       func_176226_b(worldIn, pos, BlocksTC.brainBox.func_176223_P(), 0);
/*  59 */       worldIn.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/*  65 */     if (worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177230_c() != BlocksTC.thaumatorium) return false;
/*  66 */     if (worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177229_b(FACING) == side) return false;
/*  67 */     return super.func_176198_a(worldIn, pos, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  73 */     IBlockState bs = func_176223_P();
/*  74 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing.func_176734_d());
/*  75 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  81 */     IBlockState bs = func_176223_P();
/*  82 */     bs = bs.func_177226_a(IBlockFacing.FACING, BlockStateUtils.getFacing(meta));
/*  83 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  89 */     byte b0 = 0;
/*  90 */     int i = b0 | ((EnumFacing)state.func_177229_b(IBlockFacing.FACING)).func_176745_a();
/*  91 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  97 */     return new BlockState(this, new IProperty[] { IBlockFacing.FACING });
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess world, BlockPos pos)
/*     */   {
/* 102 */     func_149676_a(0.1875F, 0.1875F, 0.1875F, 0.8125F, 0.8125F, 0.8125F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockBrainBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */