/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockStaticLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.blocks.misc.BlockPlaceholder;
/*     */ import thaumcraft.common.blocks.misc.BlockPlaceholder.PlaceholderType;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileInfernalFurnace;
/*     */ 
/*     */ public class BlockInfernalFurnace extends BlockTCDevice implements IBlockFacingHorizontal
/*     */ {
/*     */   public BlockInfernalFurnace()
/*     */   {
/*  33 */     super(Material.field_151576_e, TileInfernalFurnace.class);
/*  34 */     func_149672_a(Block.field_149769_e);
/*  35 */     func_149715_a(0.9F);
/*  36 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  37 */     bs.func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.NORTH);
/*  38 */     func_180632_j(bs);
/*     */     
/*  40 */     func_149647_a(null);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/*  46 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*  47 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */   
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/*  57 */     return EnumWorldBlockLayer.CUTOUT_MIPPED;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  63 */     IBlockState bs = func_176223_P();
/*  64 */     bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_174811_aO().func_176734_d());
/*  65 */     return bs;
/*     */   }
/*     */   
/*  68 */   public static boolean ignore = false;
/*     */   
/*     */   public static void destroyFurnace(World worldIn, BlockPos pos, IBlockState state, BlockPos startpos) {
/*  71 */     if ((ignore) || (worldIn.field_72995_K)) return;
/*  72 */     ignore = true;
/*  73 */     for (int a = -1; a <= 1; a++) {
/*  74 */       for (int b = -1; b <= 1; b++) {
/*  75 */         for (int c = -1; c <= 1; c++)
/*  76 */           if (pos.func_177982_a(a, b, c) != startpos) {
/*  77 */             IBlockState bs = worldIn.func_180495_p(pos.func_177982_a(a, b, c));
/*  78 */             if ((bs.func_177230_c() == BlocksTC.placeholder) && (bs.func_177229_b(BlockPlaceholder.VARIANT) == BlockPlaceholder.PlaceholderType.FURNACE_BRICK)) {
/*  79 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150385_bj.func_176223_P());
/*     */             }
/*  81 */             if ((bs.func_177230_c() == BlocksTC.placeholder) && (bs.func_177229_b(BlockPlaceholder.VARIANT) == BlockPlaceholder.PlaceholderType.FURNACE_OBSIDIAN))
/*  82 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150343_Z.func_176223_P());
/*     */           }
/*     */       }
/*     */     }
/*  86 */     if (worldIn.func_175623_d(pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d())))
/*  87 */       worldIn.func_175656_a(pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d()), Blocks.field_150411_aY.func_176223_P());
/*  88 */     worldIn.func_175656_a(pos, Blocks.field_150353_l.func_176223_P());
/*  89 */     ignore = false;
/*     */   }
/*     */   
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/*  94 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 100 */     destroyFurnace(worldIn, pos, state, pos);
/* 101 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
/*     */   {
/* 106 */     if (entity.field_70165_t < pos.func_177958_n() + 0.3F) entity.field_70159_w += 9.999999747378752E-5D;
/* 107 */     if (entity.field_70165_t > pos.func_177958_n() + 0.7F) entity.field_70159_w -= 9.999999747378752E-5D;
/* 108 */     if (entity.field_70161_v < pos.func_177952_p() + 0.3F) entity.field_70179_y += 9.999999747378752E-5D;
/* 109 */     if (entity.field_70161_v > pos.func_177952_p() + 0.7F) entity.field_70179_y -= 9.999999747378752E-5D;
/* 110 */     if ((entity instanceof EntityItem)) {
/* 111 */       entity.field_70181_x = 0.02500000037252903D;
/* 112 */       if (entity.field_70122_E) {
/* 113 */         TileInfernalFurnace taf = (TileInfernalFurnace)world.func_175625_s(pos);
/* 114 */         if (taf.addItemsToInventory(((EntityItem)entity).func_92059_d())) {
/* 115 */           entity.func_70106_y();
/*     */         }
/*     */       }
/*     */     }
/* 119 */     else if (((entity instanceof EntityLivingBase)) && 
/* 120 */       (!entity.func_70045_F()))
/*     */     {
/* 122 */       entity.func_70097_a(DamageSource.field_76371_c, 3.0F);
/* 123 */       entity.func_70015_d(10);
/*     */     }
/*     */     
/* 126 */     super.func_180634_a(world, pos, state, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockInfernalFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */