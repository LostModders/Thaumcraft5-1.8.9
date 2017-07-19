/*     */ package thaumcraft.common.blocks.world.plants;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLeaves;
/*     */ import net.minecraft.block.BlockPlanks.EnumType;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeColorHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockLeavesTC
/*     */   extends BlockLeaves
/*     */ {
/*  38 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177708_a("variant", BlockLogsTC.LogType.class, new Predicate()
/*     */   {
/*     */ 
/*     */     public boolean apply(BlockLogsTC.LogType type)
/*     */     {
/*  43 */       return type.getMetadata() < 4;
/*     */     }
/*     */     
/*     */     public boolean apply(Object p_apply_1_) {
/*  47 */       return apply((BlockLogsTC.LogType)p_apply_1_);
/*     */     }
/*  38 */   });
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
/*     */   public BlockLeavesTC()
/*     */   {
/*  54 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockLogsTC.LogType.GREATWOOD).func_177226_a(field_176236_b, Boolean.valueOf(true)).func_177226_a(field_176237_a, Boolean.valueOf(true)));
/*     */     
/*     */ 
/*  57 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/*  61 */     return 60;
/*     */   }
/*     */   
/*  64 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 30; }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/*  70 */     return Blocks.field_150362_t.func_180664_k();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  76 */     return Blocks.field_150362_t.func_149662_c();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_176225_a(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/*  83 */     return (func_149662_c()) && (worldIn.func_180495_p(pos).func_177230_c() == this) ? false : super.func_176225_a(worldIn, pos, side);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149635_D()
/*     */   {
/*  90 */     return ColorizerFoliage.func_77470_a(0.5D, 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180644_h(IBlockState state)
/*     */   {
/*  98 */     return func_180651_a(state) == 1 ? 16777215 : ColorizerFoliage.func_77468_c();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180662_a(IBlockAccess worldIn, BlockPos pos, int renderPass)
/*     */   {
/* 105 */     return func_180651_a(worldIn.func_180495_p(pos)) == 1 ? 16777215 : BiomeColorHelper.func_180287_b(worldIn, pos);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/* 112 */     return state.func_177230_c() == this ? ((BlockLogsTC.LogType)state.func_177229_b(VARIANT)).getMetadata() : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176222_j(World worldIn, BlockPos pos)
/*     */   {
/* 118 */     IBlockState iblockstate = worldIn.func_180495_p(pos);
/* 119 */     return iblockstate.func_177230_c().func_176201_c(iblockstate) & 0x3;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item itemIn, CreativeTabs tab, List list)
/*     */   {
/* 126 */     list.add(new ItemStack(itemIn, 1, 0));
/* 127 */     list.add(new ItemStack(itemIn, 1, 1));
/*     */   }
/*     */   
/*     */ 
/*     */   protected ItemStack func_180643_i(IBlockState state)
/*     */   {
/* 133 */     return new ItemStack(Item.func_150898_a(this), 1, ((BlockLogsTC.LogType)state.func_177229_b(VARIANT)).getMetadata());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 140 */     return func_176223_P().func_177226_a(VARIANT, getWoodTCType(meta)).func_177226_a(field_176237_a, Boolean.valueOf((meta & 0x4) == 0)).func_177226_a(field_176236_b, Boolean.valueOf((meta & 0x8) > 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 148 */     byte b0 = 0;
/* 149 */     int i = b0 | ((BlockLogsTC.LogType)state.func_177229_b(VARIANT)).getMetadata();
/*     */     
/* 151 */     if (!((Boolean)state.func_177229_b(field_176237_a)).booleanValue())
/*     */     {
/* 153 */       i |= 0x4;
/*     */     }
/*     */     
/* 156 */     if (((Boolean)state.func_177229_b(field_176236_b)).booleanValue())
/*     */     {
/* 158 */       i |= 0x8;
/*     */     }
/*     */     
/* 161 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_176232_d(IBlockState state)
/*     */   {
/* 167 */     return ((BlockLogsTC.LogType)state.func_177229_b(VARIANT)).getMetadata() == 0 ? 44 : 200;
/*     */   }
/*     */   
/*     */   protected void func_176234_a(World worldIn, BlockPos pos, IBlockState state, int chance)
/*     */   {
/* 172 */     if ((state.func_177229_b(VARIANT) == BlockLogsTC.LogType.SILVERWOOD) && (worldIn.field_73012_v.nextInt((int)(chance * 1.5D)) == 0))
/*     */     {
/* 174 */       func_180635_a(worldIn, pos, new ItemStack(ItemsTC.quicksilver, 1, 0));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/* 181 */     return Item.func_150898_a(BlocksTC.sapling);
/*     */   }
/*     */   
/*     */   public BlockLogsTC.LogType getWoodTCType(int meta)
/*     */   {
/* 186 */     return BlockLogsTC.LogType.byMetadata(meta & 0x3);
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 192 */     return new BlockState(this, new IProperty[] { VARIANT, field_176236_b, field_176237_a });
/*     */   }
/*     */   
/*     */ 
/*     */   public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
/*     */   {
/* 198 */     IBlockState state = world.func_180495_p(pos);
/* 199 */     return new ArrayList(Arrays.asList(new ItemStack[] { new ItemStack(this, 1, ((BlockLogsTC.LogType)state.func_177229_b(VARIANT)).getMetadata()) }));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BlockPlanks.EnumType func_176233_b(int meta)
/*     */   {
/* 206 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/plants/BlockLeavesTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */