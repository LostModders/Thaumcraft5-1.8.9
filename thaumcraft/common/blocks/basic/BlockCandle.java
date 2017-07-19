/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.crafting.IInfusionStabiliser;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ 
/*     */ public class BlockCandle
/*     */   extends BlockTC
/*     */   implements IInfusionStabiliser
/*     */ {
/*  28 */   public static final PropertyEnum COLOR = PropertyEnum.func_177709_a("color", EnumDyeColor.class);
/*     */   
/*     */   public BlockCandle()
/*     */   {
/*  32 */     super(Material.field_151594_q);
/*  33 */     func_149711_c(0.1F);
/*  34 */     func_149672_a(field_149775_l);
/*  35 */     func_149715_a(0.9375F);
/*  36 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(COLOR, EnumDyeColor.WHITE));
/*     */   }
/*     */   
/*     */ 
/*     */   public MapColor func_180659_g(IBlockState state)
/*     */   {
/*  42 */     return ((EnumDyeColor)state.func_177229_b(COLOR)).func_176768_e();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180644_h(IBlockState state)
/*     */   {
/*  49 */     return ((EnumDyeColor)state.func_177229_b(COLOR)).func_176768_e().field_76291_p;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180662_a(IBlockAccess worldIn, BlockPos pos, int renderPass)
/*     */   {
/*  56 */     return ((EnumDyeColor)worldIn.func_180495_p(pos).func_177229_b(COLOR)).func_176768_e().field_76291_p;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  62 */     return meta < EnumDyeColor.values().length ? func_176223_P().func_177226_a(COLOR, EnumDyeColor.func_176764_b(meta)) : func_176223_P();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  68 */     return ((EnumDyeColor)state.func_177229_b(COLOR)).func_176765_a();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  74 */     return new BlockState(this, new IProperty[] { COLOR });
/*     */   }
/*     */   
/*     */   public IProperty[] getProperties() {
/*  78 */     return new IProperty[] { COLOR };
/*     */   }
/*     */   
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/*  83 */     EnumDyeColor type = (EnumDyeColor)state.func_177229_b(COLOR);
/*  84 */     return type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176196_c(World par1World, BlockPos pos)
/*     */   {
/*  90 */     return World.func_175683_a(par1World, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/*  96 */     if (!func_176196_c(worldIn, pos.func_177977_b()))
/*     */     {
/*  98 */       func_176226_b(worldIn, pos, state, 0);
/*  99 */       worldIn.func_175698_g(pos);
/*     */     }
/* 101 */     super.func_176204_a(worldIn, pos, state, neighborBlock);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176198_a(World par1World, BlockPos pos, EnumFacing par5)
/*     */   {
/* 107 */     return func_176196_c(par1World, pos.func_177977_b());
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess par1iBlockAccess, BlockPos pos)
/*     */   {
/* 112 */     func_149676_a(0.375F, 0.0F, 0.375F, 0.625F, 0.5F, 0.625F);
/* 113 */     super.func_180654_a(par1iBlockAccess, pos);
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side)
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 124 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 131 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 137 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180655_c(World par1World, BlockPos pos, IBlockState state, Random par5Random)
/*     */   {
/* 143 */     double var7 = pos.func_177958_n() + 0.5F;
/* 144 */     double var9 = pos.func_177956_o() + 0.7F;
/* 145 */     double var11 = pos.func_177952_p() + 0.5F;
/*     */     
/* 147 */     par1World.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, var7, var9, var11, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     
/* 149 */     par1World.func_175688_a(EnumParticleTypes.FLAME, var7, var9, var11, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean canStabaliseInfusion(World world, BlockPos pos)
/*     */   {
/* 156 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockCandle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */