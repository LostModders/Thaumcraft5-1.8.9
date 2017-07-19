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
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class BlockStoneSlabTC
/*     */   extends BlockSlab
/*     */ {
/*  26 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", StoneType.class);
/*     */   
/*     */   public BlockStoneSlabTC()
/*     */   {
/*  30 */     super(Material.field_151576_e);
/*     */     
/*  32 */     IBlockState iblockstate = this.field_176227_L.func_177621_b();
/*     */     
/*  34 */     if (!func_176552_j())
/*     */     {
/*  36 */       iblockstate = iblockstate.func_177226_a(field_176554_a, BlockSlab.EnumBlockHalf.BOTTOM);
/*  37 */       func_149647_a(Thaumcraft.tabTC);
/*     */     }
/*     */     
/*  40 */     func_180632_j(iblockstate.func_177226_a(VARIANT, StoneType.ARCANE));
/*  41 */     this.field_149783_u = (!func_176552_j());
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/*  47 */     return Item.func_150898_a(BlocksTC.slabStone);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_180665_b(World worldIn, BlockPos pos)
/*     */   {
/*  54 */     return Item.func_150898_a(BlocksTC.slabStone);
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_150002_b(int meta)
/*     */   {
/*  60 */     return func_149739_a();
/*     */   }
/*     */   
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/*  65 */     return ((StoneType)state.func_177229_b(VARIANT)).func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty func_176551_l()
/*     */   {
/*  71 */     return VARIANT;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object func_176553_a(ItemStack stack)
/*     */   {
/*  77 */     return (StoneType)func_176203_a(stack.func_77960_j() & 0x7).func_177229_b(VARIANT);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item itemIn, CreativeTabs tab, List list)
/*     */   {
/*  84 */     if (itemIn != Item.func_150898_a(BlocksTC.doubleSlabStone))
/*     */     {
/*  86 */       StoneType[] aenumtype = StoneType.values();
/*  87 */       int i = aenumtype.length;
/*     */       
/*  89 */       for (int j = 0; j < i; j++)
/*     */       {
/*  91 */         StoneType enumtype = aenumtype[j];
/*  92 */         list.add(new ItemStack(itemIn, 1, enumtype.ordinal()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 100 */     IBlockState iblockstate = func_176223_P().func_177226_a(VARIANT, StoneType.values()[(meta & 0x7)]);
/*     */     
/* 102 */     if (!func_176552_j())
/*     */     {
/* 104 */       iblockstate = iblockstate.func_177226_a(field_176554_a, (meta & 0x8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
/*     */     }
/*     */     
/* 107 */     return iblockstate;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 113 */     byte b0 = 0;
/* 114 */     int i = b0 | ((StoneType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/* 116 */     if ((!func_176552_j()) && (state.func_177229_b(field_176554_a) == BlockSlab.EnumBlockHalf.TOP))
/*     */     {
/* 118 */       i |= 0x8;
/*     */     }
/*     */     
/* 121 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 127 */     return func_176552_j() ? new BlockState(this, new IProperty[] { VARIANT }) : new BlockState(this, new IProperty[] { field_176554_a, VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/* 133 */     return ((StoneType)state.func_177229_b(VARIANT)).ordinal();
/*     */   }
/*     */   
/*     */   public boolean func_176552_j()
/*     */   {
/* 138 */     return this == BlocksTC.doubleSlabStone;
/*     */   }
/*     */   
/*     */   public static enum StoneType implements IStringSerializable
/*     */   {
/* 143 */     ARCANE, 
/* 144 */     ARCANE_BRICK, 
/* 145 */     ANCIENT, 
/* 146 */     ELDRITCH;
/*     */     
/*     */     private StoneType() {}
/*     */     
/*     */     public String func_176610_l()
/*     */     {
/* 152 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 158 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockStoneSlabTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */