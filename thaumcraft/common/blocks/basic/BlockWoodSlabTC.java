/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockSlab;
/*     */ import net.minecraft.block.BlockSlab.EnumBlockHalf;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ 
/*     */ public class BlockWoodSlabTC
/*     */   extends BlockSlab
/*     */ {
/*  29 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", PlankType.class);
/*     */   
/*     */   public BlockWoodSlabTC()
/*     */   {
/*  33 */     super(Material.field_151575_d);
/*  34 */     IBlockState iblockstate = this.field_176227_L.func_177621_b();
/*     */     
/*  36 */     if (!func_176552_j())
/*     */     {
/*  38 */       iblockstate = iblockstate.func_177226_a(field_176554_a, BlockSlab.EnumBlockHalf.BOTTOM);
/*  39 */       func_149647_a(Thaumcraft.tabTC);
/*     */     }
/*     */     
/*  42 */     func_180632_j(iblockstate.func_177226_a(VARIANT, PlankType.GREATWOOD));
/*  43 */     this.field_149783_u = (!func_176552_j());
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/*  47 */     return 20;
/*     */   }
/*     */   
/*  50 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 5; }
/*     */   
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/*  55 */     return Item.func_150898_a(BlocksTC.slabWood);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_180665_b(World worldIn, BlockPos pos)
/*     */   {
/*  62 */     return Item.func_150898_a(BlocksTC.slabWood);
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_150002_b(int meta)
/*     */   {
/*  68 */     return func_149739_a();
/*     */   }
/*     */   
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/*  73 */     return ((PlankType)state.func_177229_b(VARIANT)).func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty func_176551_l()
/*     */   {
/*  79 */     return VARIANT;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object func_176553_a(ItemStack stack)
/*     */   {
/*  85 */     return (PlankType)func_176203_a(stack.func_77960_j() & 0x7).func_177229_b(VARIANT);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item itemIn, CreativeTabs tab, List list)
/*     */   {
/*  92 */     if (itemIn != Item.func_150898_a(BlocksTC.doubleSlabWood))
/*     */     {
/*  94 */       PlankType[] aenumtype = PlankType.values();
/*  95 */       int i = aenumtype.length;
/*     */       
/*  97 */       for (int j = 0; j < i; j++)
/*     */       {
/*  99 */         PlankType enumtype = aenumtype[j];
/* 100 */         list.add(new ItemStack(itemIn, 1, enumtype.ordinal()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 108 */     IBlockState iblockstate = func_176223_P().func_177226_a(VARIANT, PlankType.values()[(meta & 0x7)]);
/*     */     
/* 110 */     if (!func_176552_j())
/*     */     {
/* 112 */       iblockstate = iblockstate.func_177226_a(field_176554_a, (meta & 0x8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
/*     */     }
/*     */     
/* 115 */     return iblockstate;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 121 */     byte b0 = 0;
/* 122 */     int i = b0 | ((PlankType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/* 124 */     if ((!func_176552_j()) && (state.func_177229_b(field_176554_a) == BlockSlab.EnumBlockHalf.TOP))
/*     */     {
/* 126 */       i |= 0x8;
/*     */     }
/*     */     
/* 129 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 135 */     return func_176552_j() ? new BlockState(this, new IProperty[] { VARIANT }) : new BlockState(this, new IProperty[] { field_176554_a, VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/* 141 */     return ((PlankType)state.func_177229_b(VARIANT)).ordinal();
/*     */   }
/*     */   
/*     */   public boolean func_176552_j()
/*     */   {
/* 146 */     return this == BlocksTC.doubleSlabWood;
/*     */   }
/*     */   
/*     */   public static enum PlankType
/*     */     implements IStringSerializable
/*     */   {
/* 152 */     GREATWOOD, 
/* 153 */     SILVERWOOD;
/*     */     
/*     */     private PlankType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 158 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 164 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockWoodSlabTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */