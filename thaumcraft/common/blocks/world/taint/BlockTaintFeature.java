/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumFacing.Axis;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class BlockTaintFeature
/*     */   extends BlockTC
/*     */   implements ITaintBlock
/*     */ {
/*     */   public BlockTaintFeature()
/*     */   {
/*  41 */     super(ThaumcraftMaterials.MATERIAL_TAINT);
/*  42 */     func_149711_c(0.1F);
/*  43 */     func_149715_a(0.625F);
/*  44 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  45 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  46 */     func_180632_j(bs);
/*  47 */     func_149675_a(true);
/*  48 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   protected boolean func_149700_E()
/*     */   {
/*  53 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  59 */     if (!worldIn.field_72995_K)
/*     */     {
/*  61 */       if (worldIn.field_73012_v.nextFloat() < 0.333F) {
/*  62 */         Entity e = new EntityTaintCrawler(worldIn);
/*  63 */         e.func_70012_b(pos.func_177958_n() + 0.5F, pos.func_177956_o() + 0.5F, pos.func_177952_p() + 0.5F, worldIn.field_73012_v.nextInt(360), 0.0F);
/*  64 */         worldIn.func_72838_d(e);
/*     */       } else {
/*  66 */         AuraHelper.pollute(worldIn, pos, 1, true);
/*     */       }
/*     */     }
/*  69 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */   public void die(World world, BlockPos pos, IBlockState blockState)
/*     */   {
/*  74 */     world.func_175656_a(pos, BlocksTC.taintDust.func_176223_P().func_177226_a(BlockFluidBase.LEVEL, Integer.valueOf(world.field_73012_v.nextInt(2))));
/*  75 */     Utils.resetBiomeAt(world, pos, world.field_73012_v.nextInt(25) == 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random)
/*     */   {
/*  81 */     if (!world.field_72995_K)
/*     */     {
/*     */ 
/*  84 */       if ((AuraHelper.getAura(world, pos, Aspect.FLUX) < Config.AURABASE / 25) && (random.nextInt(10) == 0)) {
/*  85 */         die(world, pos, state);
/*  86 */         return;
/*     */       }
/*  88 */       if ((AuraHelper.getAura(world, pos, Aspect.FLUX) <= Config.AURABASE / 5) && (random.nextInt(200) == 0)) {
/*  89 */         AuraHelper.pollute(world, pos, 1, true);
/*  90 */         return;
/*     */       }
/*  92 */       BlockTaintFibre.spreadFibres(world, pos);
/*     */       
/*  94 */       if ((world.func_180495_p(pos.func_177977_b()).func_177230_c() == BlocksTC.taintLog) && (world.func_180495_p(pos.func_177977_b()).func_177229_b(BlockTaintLog.AXIS) == EnumFacing.Axis.Y) && (world.field_73012_v.nextInt(100) == 0))
/*     */       {
/*     */ 
/*  97 */         if (world.field_73012_v.nextFloat() < Config.taintSpreadRate * 5.0F) AuraHelper.drainAura(world, pos, Aspect.FLUX, 1);
/*  98 */         world.func_175656_a(pos, BlocksTC.taintBlock.func_176203_a(2));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/* 106 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/* 111 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
/*     */   {
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   public int func_176207_c(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 121 */     return 200;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 127 */     if ((!worldIn.field_72995_K) && (!worldIn.func_180495_p(pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d())).func_177230_c().func_176212_b(worldIn, pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d()), BlockStateUtils.getFacing(state))))
/*     */     {
/* 129 */       func_180635_a(worldIn, pos, new ItemStack(ItemsTC.tainted));
/* 130 */       worldIn.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 137 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 143 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/* 149 */     IBlockState bs = func_176223_P();
/* 150 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing);
/* 151 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 158 */     IBlockState bs = func_176223_P();
/* 159 */     bs = bs.func_177226_a(IBlockFacing.FACING, BlockStateUtils.getFacing(meta));
/* 160 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 166 */     byte b0 = 0;
/* 167 */     int i = b0 | ((EnumFacing)state.func_177229_b(IBlockFacing.FACING)).func_176745_a();
/* 168 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 174 */     ArrayList<IProperty> ip = new ArrayList();
/* 175 */     ip.add(IBlockFacing.FACING);
/* 176 */     return new BlockState(this, (IProperty[])ip.toArray(new IProperty[ip.size()]));
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 181 */     EnumFacing facing = BlockStateUtils.getFacing(func_176201_c(worldIn.func_180495_p(pos)));
/* 182 */     switch (facing.ordinal()) {
/* 183 */     case 0:  func_149676_a(0.125F, 0.625F, 0.125F, 0.875F, 1.0F, 0.875F); break;
/* 184 */     case 1:  func_149676_a(0.125F, 0.0F, 0.125F, 0.875F, 0.375F, 0.875F); break;
/* 185 */     case 2:  func_149676_a(0.125F, 0.125F, 0.625F, 0.875F, 0.875F, 1.0F); break;
/* 186 */     case 3:  func_149676_a(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.375F); break;
/* 187 */     case 4:  func_149676_a(0.625F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F); break;
/* 188 */     case 5:  func_149676_a(0.0F, 0.125F, 0.125F, 0.375F, 0.875F, 0.875F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 195 */     func_180654_a(worldIn, pos);
/* 196 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/taint/BlockTaintFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */