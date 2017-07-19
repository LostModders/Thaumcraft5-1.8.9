/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ 
/*     */ public class BlockTranslucent
/*     */   extends BlockTC
/*     */ {
/*  24 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", TransType.class);
/*     */   
/*     */   public BlockTranslucent()
/*     */   {
/*  28 */     super(Material.field_151592_s);
/*  29 */     func_149647_a(Thaumcraft.tabTC);
/*  30 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, TransType.AMBER_BLOCK));
/*  31 */     func_149711_c(0.5F);
/*  32 */     func_149672_a(Block.field_149769_e);
/*     */   }
/*     */   
/*     */   public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
/*     */   {
/*  37 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_176225_a(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/*  49 */     IBlockState iblockstate = worldIn.func_180495_p(pos);
/*  50 */     Block block = iblockstate.func_177230_c();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  65 */     return block == this ? false : super.func_176225_a(worldIn, pos, side);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/*  72 */     return EnumWorldBlockLayer.TRANSLUCENT;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  78 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149656_h()
/*     */   {
/*  84 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  90 */     return func_176223_P().func_177226_a(VARIANT, TransType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  96 */     int meta = ((TransType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/*  98 */     return meta;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 104 */     return new BlockState(this, new IProperty[] { VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 110 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 116 */     TransType type = (TransType)state.func_177229_b(VARIANT);
/*     */     
/* 118 */     return type.func_176610_l();
/*     */   }
/*     */   
/*     */   public static enum TransType
/*     */     implements IStringSerializable
/*     */   {
/* 124 */     AMBER_BLOCK, 
/* 125 */     AMBER_BRICK, 
/* 126 */     EMPTY;
/*     */     
/*     */     private TransType() {}
/*     */     
/*     */     public String func_176610_l()
/*     */     {
/* 132 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 138 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockTranslucent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */