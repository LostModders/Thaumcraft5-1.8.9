/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.crafting.TileSmelter;
/*     */ 
/*     */ public class BlockSmelter extends BlockTCDevice implements IBlockEnabled, IBlockFacingHorizontal
/*     */ {
/*     */   public BlockSmelter()
/*     */   {
/*  32 */     super(Material.field_151573_f, TileSmelter.class);
/*  33 */     func_149672_a(Block.field_149777_j);
/*     */     
/*  35 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  36 */     bs.func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.NORTH);
/*  37 */     bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*  38 */     func_180632_j(bs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  48 */     IBlockState bs = func_176223_P();
/*  49 */     bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_174811_aO().func_176734_d());
/*  50 */     bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*  51 */     return bs;
/*     */   }
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/*  56 */     TileEntity te = worldIn.func_175625_s(pos);
/*  57 */     if ((te != null) && ((te instanceof TileSmelter))) {
/*  58 */       ((TileSmelter)te).checkNeighbours();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  64 */     if ((!world.field_72995_K) && (!playerIn.func_70093_af())) {
/*  65 */       playerIn.openGui(Thaumcraft.instance, 9, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */     }
/*  67 */     return true;
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, BlockPos pos)
/*     */   {
/*  72 */     return BlockStateUtils.isEnabled(world.func_180495_p(pos).func_177230_c().func_176201_c(world.func_180495_p(pos))) ? 13 : super.getLightValue(world, pos);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/*  77 */     return true;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  82 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180641_l(World world, BlockPos pos)
/*     */   {
/*  88 */     TileEntity te = world.func_175625_s(pos);
/*  89 */     if ((te != null) && ((te instanceof IInventory))) {
/*  90 */       return Container.func_94526_b((IInventory)te);
/*     */     }
/*  92 */     return 0;
/*     */   }
/*     */   
/*     */   public static void setFurnaceState(World world, BlockPos pos, boolean state) {
/*  96 */     if (state == BlockStateUtils.isEnabled(world.func_180495_p(pos).func_177230_c().func_176201_c(world.func_180495_p(pos)))) return;
/*  97 */     TileEntity tileentity = world.func_175625_s(pos);
/*  98 */     keepInventory = true;
/*  99 */     world.func_180501_a(pos, world.func_180495_p(pos).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(state)), 3);
/* 100 */     world.func_180501_a(pos, world.func_180495_p(pos).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(state)), 3);
/* 101 */     if (tileentity != null)
/*     */     {
/* 103 */       tileentity.func_145829_t();
/* 104 */       world.func_175690_a(pos, tileentity);
/*     */     }
/* 106 */     keepInventory = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 112 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/*     */     
/* 114 */     if (((tileentity instanceof TileSmelter)) && (!worldIn.field_72995_K) && (((TileSmelter)tileentity).vis > 0))
/*     */     {
/* 116 */       int ess = ((TileSmelter)tileentity).vis;
/* 117 */       AuraHelper.pollute(worldIn, pos, ess, true);
/*     */     }
/*     */     
/* 120 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(World w, BlockPos pos, IBlockState state, Random r)
/*     */   {
/* 126 */     if (BlockStateUtils.isEnabled(state)) {
/* 127 */       float f = pos.func_177958_n() + 0.5F;
/* 128 */       float f1 = pos.func_177956_o() + 0.2F + r.nextFloat() * 5.0F / 16.0F;
/* 129 */       float f2 = pos.func_177952_p() + 0.5F;
/* 130 */       float f3 = 0.52F;
/* 131 */       float f4 = r.nextFloat() * 0.5F - 0.25F;
/*     */       
/* 133 */       if (BlockStateUtils.getFacing(state) == EnumFacing.WEST) {
/* 134 */         w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D, new int[0]);
/* 135 */         w.func_175688_a(EnumParticleTypes.FLAME, f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */       
/* 138 */       if (BlockStateUtils.getFacing(state) == EnumFacing.EAST) {
/* 139 */         w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D, new int[0]);
/* 140 */         w.func_175688_a(EnumParticleTypes.FLAME, f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */       
/* 143 */       if (BlockStateUtils.getFacing(state) == EnumFacing.NORTH) {
/* 144 */         w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D, new int[0]);
/* 145 */         w.func_175688_a(EnumParticleTypes.FLAME, f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */       
/* 148 */       if (BlockStateUtils.getFacing(state) == EnumFacing.SOUTH) {
/* 149 */         w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D, new int[0]);
/* 150 */         w.func_175688_a(EnumParticleTypes.FLAME, f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockSmelter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */