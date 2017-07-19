/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.tiles.misc.TileNitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockNitor
/*     */   extends BlockTC
/*     */   implements ITileEntityProvider
/*     */ {
/*  27 */   public static final PropertyEnum COLOR = PropertyEnum.func_177709_a("color", EnumDyeColor.class);
/*     */   
/*     */   public BlockNitor()
/*     */   {
/*  31 */     super(Material.field_151594_q);
/*  32 */     func_149711_c(0.1F);
/*  33 */     func_149672_a(field_149775_l);
/*  34 */     func_149715_a(1.0F);
/*  35 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(COLOR, EnumDyeColor.WHITE));
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World worldIn, int meta)
/*     */   {
/*  40 */     return new TileNitor();
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(IBlockState state)
/*     */   {
/*  45 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public MapColor func_180659_g(IBlockState state)
/*     */   {
/*  51 */     return ((EnumDyeColor)state.func_177229_b(COLOR)).func_176768_e();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180644_h(IBlockState state)
/*     */   {
/*  58 */     return ((EnumDyeColor)state.func_177229_b(COLOR)).func_176768_e().field_76291_p;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180662_a(IBlockAccess worldIn, BlockPos pos, int renderPass)
/*     */   {
/*  65 */     return ((EnumDyeColor)worldIn.func_180495_p(pos).func_177229_b(COLOR)).func_176768_e().field_76291_p;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  71 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  77 */     return meta < EnumDyeColor.values().length ? func_176223_P().func_177226_a(COLOR, EnumDyeColor.func_176764_b(meta)) : func_176223_P();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  83 */     return ((EnumDyeColor)state.func_177229_b(COLOR)).func_176765_a();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  89 */     return new BlockState(this, new IProperty[] { COLOR });
/*     */   }
/*     */   
/*     */   public IProperty[] getProperties() {
/*  93 */     return new IProperty[] { COLOR };
/*     */   }
/*     */   
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/*  98 */     EnumDyeColor type = (EnumDyeColor)state.func_177229_b(COLOR);
/*  99 */     return type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180654_a(IBlockAccess par1iBlockAccess, BlockPos pos)
/*     */   {
/* 105 */     func_149676_a(0.33F, 0.33F, 0.33F, 0.66F, 0.66F, 0.66F);
/* 106 */     super.func_180654_a(par1iBlockAccess, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 112 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 119 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 125 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockNitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */