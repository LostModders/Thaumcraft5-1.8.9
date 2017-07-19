/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneEar;
/*     */ 
/*     */ public class BlockArcaneEar extends BlockTCDevice implements IBlockFacing, IBlockEnabled
/*     */ {
/*     */   public BlockArcaneEar()
/*     */   {
/*  26 */     super(net.minecraft.block.material.Material.field_151575_d, TileArcaneEar.class);
/*  27 */     func_149672_a(Block.field_149766_f);
/*  28 */     func_149711_c(1.0F);
/*     */     
/*  30 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  31 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  32 */     bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*  33 */     func_180632_j(bs);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  39 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  50 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  56 */     IBlockState bs = func_176223_P();
/*  57 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing);
/*  58 */     bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*  59 */     return bs;
/*     */   }
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  64 */     TileArcaneEar tile = (TileArcaneEar)worldIn.func_175625_s(pos);
/*  65 */     if (tile != null)
/*     */     {
/*  67 */       tile.updateTone();
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/*  73 */     TileArcaneEar tile = (TileArcaneEar)worldIn.func_175625_s(pos);
/*  74 */     if (tile != null)
/*     */     {
/*  76 */       tile.updateTone();
/*     */     }
/*  78 */     if (!worldIn.func_180495_p(pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d())).func_177230_c().isSideSolid(worldIn, pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d()), BlockStateUtils.getFacing(state)))
/*     */     {
/*  80 */       func_176226_b(worldIn, pos, func_176223_P(), 0);
/*  81 */       worldIn.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  87 */     if (world.field_72995_K) return true;
/*  88 */     TileArcaneEar tile = (TileArcaneEar)world.func_175625_s(pos);
/*  89 */     if (tile != null)
/*     */     {
/*  91 */       tile.changePitch();
/*  92 */       tile.triggerNote(world, pos, true);
/*     */     }
/*  94 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149744_f() {
/*  98 */     return true;
/*     */   }
/*     */   
/*     */   public int func_180656_a(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side)
/*     */   {
/* 103 */     return BlockStateUtils.isEnabled(state.func_177230_c().func_176201_c(state)) ? 15 : 0;
/*     */   }
/*     */   
/*     */   public int func_176211_b(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side)
/*     */   {
/* 108 */     return BlockStateUtils.isEnabled(state.func_177230_c().func_176201_c(state)) ? 15 : 0;
/*     */   }
/*     */   
/*     */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/* 113 */     return worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177230_c().isSideSolid(worldIn, pos.func_177972_a(side.func_176734_d()), side);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 120 */     EnumFacing facing = BlockStateUtils.getFacing(func_176201_c(worldIn.func_180495_p(pos)));
/* 121 */     switch (facing.ordinal()) {
/* 122 */     case 0:  func_149676_a(0.125F, 0.625F, 0.125F, 0.875F, 1.0F, 0.875F); break;
/* 123 */     case 1:  func_149676_a(0.125F, 0.0F, 0.125F, 0.875F, 0.375F, 0.875F); break;
/* 124 */     case 2:  func_149676_a(0.125F, 0.125F, 0.625F, 0.875F, 0.875F, 1.0F); break;
/* 125 */     case 3:  func_149676_a(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.375F); break;
/* 126 */     case 4:  func_149676_a(0.625F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F); break;
/* 127 */     case 5:  func_149676_a(0.0F, 0.125F, 0.125F, 0.375F, 0.875F, 0.875F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 134 */     func_180654_a(worldIn, pos);
/* 135 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180648_a(World worldIn, BlockPos pos, IBlockState state, int par5, int par6)
/*     */   {
/* 141 */     float var7 = (float)Math.pow(2.0D, (par6 - 12) / 12.0D);
/* 142 */     if (par5 <= 4) {
/* 143 */       if (par5 >= 0) {
/* 144 */         String var8 = "harp";
/*     */         
/* 146 */         if (par5 == 1)
/*     */         {
/* 148 */           var8 = "bd";
/*     */         }
/*     */         
/* 151 */         if (par5 == 2)
/*     */         {
/* 153 */           var8 = "snare";
/*     */         }
/*     */         
/* 156 */         if (par5 == 3)
/*     */         {
/* 158 */           var8 = "hat";
/*     */         }
/*     */         
/* 161 */         if (par5 == 4)
/*     */         {
/* 163 */           var8 = "bassattack";
/*     */         }
/*     */         
/* 166 */         worldIn.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "note." + var8, 3.0F, var7);
/*     */       }
/* 168 */       worldIn.func_175688_a(EnumParticleTypes.NOTE, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, par6 / 24.0D, 0.0D, 0.0D, new int[0]);
/* 169 */       return true;
/*     */     }
/* 171 */     return super.func_180648_a(worldIn, pos, state, par5, par6);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockArcaneEar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */