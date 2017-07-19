/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumFacing.Axis;
/*     */ import net.minecraft.util.EnumFacing.Plane;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ public class BlockPillar extends BlockTC
/*     */ {
/*  29 */   public static final PropertyEnum TYPE = PropertyEnum.func_177709_a("type", PillarType.class);
/*  30 */   public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
/*     */   
/*  32 */   private final Random rand = new Random();
/*     */   
/*     */   public BlockPillar() {
/*  35 */     super(Material.field_151576_e);
/*     */     
/*  37 */     func_149711_c(2.5F);
/*  38 */     func_149672_a(field_149769_e);
/*  39 */     func_149647_a(null);
/*     */     
/*  41 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  42 */     bs.func_177226_a(FACING, EnumFacing.NORTH);
/*  43 */     bs.func_177226_a(TYPE, PillarType.NORMAL);
/*  44 */     func_180632_j(bs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  53 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB func_180646_a(World world, BlockPos pos)
/*     */   {
/*  66 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*  67 */     return super.func_180646_a(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180638_a(World world, BlockPos pos, IBlockState state, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/*  73 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/*  74 */     super.func_180638_a(world, pos, state, axisalignedbb, arraylist, par7Entity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  81 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  82 */     bs.func_177226_a(FACING, placer.func_174811_aO());
/*  83 */     bs.func_177226_a(TYPE, PillarType.values()[(meta / 4)]);
/*  84 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
/*     */   {
/*  90 */     EnumFacing enumfacing = EnumFacing.func_176731_b(MathHelper.func_76128_c(placer.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3).func_176734_d();
/*  91 */     state = state.func_177226_a(FACING, enumfacing);
/*  92 */     state = state.func_177226_a(TYPE, PillarType.values()[(stack.func_77952_i() / 4)]);
/*  93 */     worldIn.func_180501_a(pos, state, 3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/* 100 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 105 */     int t = ((PillarType)state.func_177229_b(TYPE)).ordinal();
/* 106 */     if (t == 0) func_180635_a(worldIn, pos, new ItemStack(BlocksTC.stone, 2, 0));
/* 107 */     if (t == 1) func_180635_a(worldIn, pos, new ItemStack(BlocksTC.stone, 2, 2));
/* 108 */     if (t == 2) func_180635_a(worldIn, pos, new ItemStack(BlocksTC.stone, 2, 4));
/* 109 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 115 */     EnumFacing enumfacing = EnumFacing.func_176731_b(meta);
/*     */     
/* 117 */     if (enumfacing.func_176740_k() == EnumFacing.Axis.Y)
/*     */     {
/* 119 */       enumfacing = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 122 */     return func_176223_P().func_177226_a(FACING, enumfacing).func_177226_a(TYPE, PillarType.values()[((meta - enumfacing.ordinal()) / 4)]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 128 */     int baseMeta = ((PillarType)state.func_177229_b(TYPE)).ordinal();
/* 129 */     return ((EnumFacing)state.func_177229_b(FACING)).ordinal() + baseMeta * 4;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 135 */     return new BlockState(this, new IProperty[] { FACING, TYPE });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 142 */     PillarType type = (PillarType)state.func_177229_b(TYPE);
/* 143 */     return "pillar_" + type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 149 */     return new IProperty[] { TYPE };
/*     */   }
/*     */   
/*     */ 
/*     */   public static enum PillarType
/*     */     implements IStringSerializable
/*     */   {
/* 156 */     NORMAL,  ANCIENT,  ELDRITCH;
/*     */     
/*     */     private PillarType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 161 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 167 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockPillar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */