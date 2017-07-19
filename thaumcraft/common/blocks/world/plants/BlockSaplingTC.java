/*     */ package thaumcraft.common.blocks.world.plants;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBush;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenTrees;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.event.terraingen.TerrainGen;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.world.objects.WorldGenGreatwoodTrees;
/*     */ import thaumcraft.common.lib.world.objects.WorldGenSilverwoodTrees;
/*     */ 
/*     */ public class BlockSaplingTC extends BlockBush implements IGrowable
/*     */ {
/*  32 */   public static final PropertyEnum TYPE = PropertyEnum.func_177709_a("type", BlockLogsTC.LogType.class);
/*  33 */   public static final PropertyInteger STAGE = PropertyInteger.func_177719_a("stage", 0, 1);
/*     */   
/*     */   public BlockSaplingTC()
/*     */   {
/*  37 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(TYPE, BlockLogsTC.LogType.GREATWOOD).func_177226_a(STAGE, Integer.valueOf(0)));
/*  38 */     float f = 0.4F;
/*  39 */     func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
/*  40 */     func_149647_a(Thaumcraft.tabTC);
/*  41 */     func_149672_a(field_149779_h);
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/*  45 */     return 60;
/*     */   }
/*     */   
/*  48 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 30; }
/*     */   
/*     */ 
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/*  53 */     if (!worldIn.field_72995_K)
/*     */     {
/*  55 */       super.func_180650_b(worldIn, pos, state, rand);
/*     */       
/*  57 */       if ((worldIn.func_175671_l(pos.func_177984_a()) >= 9) && (rand.nextInt(7) == 0))
/*     */       {
/*  59 */         grow(worldIn, pos, state, rand);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/*  67 */     if (((Integer)state.func_177229_b(STAGE)).intValue() == 0)
/*     */     {
/*  69 */       worldIn.func_180501_a(pos, state.func_177231_a(STAGE), 4);
/*     */     }
/*     */     else
/*     */     {
/*  73 */       generateTree(worldIn, pos, state, rand);
/*     */     }
/*     */   }
/*     */   
/*     */   public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/*  79 */     if (!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
/*  80 */     Object object = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
/*  81 */     int i = 0;
/*  82 */     int j = 0;
/*     */     
/*  84 */     switch (SwitchEnumType.WOOD_TYPE_LOOKUP[((BlockLogsTC.LogType)state.func_177229_b(TYPE)).getMetadata()]) {
/*     */     case 1: 
/*  86 */       object = new WorldGenGreatwoodTrees(true, false); break;
/*  87 */     case 2:  object = new WorldGenSilverwoodTrees(true, 7, 4);
/*     */     }
/*     */     
/*  90 */     IBlockState iblockstate1 = net.minecraft.init.Blocks.field_150350_a.func_176223_P();
/*     */     
/*  92 */     worldIn.func_180501_a(pos, iblockstate1, 4);
/*     */     
/*  94 */     if (!((WorldGenerator)object).func_180709_b(worldIn, rand, pos.func_177982_a(i, 0, j)))
/*     */     {
/*  96 */       worldIn.func_180501_a(pos, state, 4);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/* 103 */     return ((BlockLogsTC.LogType)state.func_177229_b(TYPE)).getMetadata();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item itemIn, CreativeTabs tab, List list)
/*     */   {
/* 110 */     BlockLogsTC.LogType[] aenumtype = BlockLogsTC.LogType.values();
/* 111 */     int i = aenumtype.length;
/*     */     
/* 113 */     for (int j = 0; j < i; j++)
/*     */     {
/* 115 */       BlockLogsTC.LogType enumtype = aenumtype[j];
/* 116 */       list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176473_a(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
/*     */   {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180670_a(World worldIn, Random rand, BlockPos pos, IBlockState state)
/*     */   {
/* 129 */     return worldIn.field_73012_v.nextFloat() < 0.25D;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176474_b(World worldIn, Random rand, BlockPos pos, IBlockState state)
/*     */   {
/* 135 */     grow(worldIn, pos, state, rand);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 142 */     return func_176223_P().func_177226_a(TYPE, BlockLogsTC.LogType.byMetadata(meta & 0x7)).func_177226_a(STAGE, Integer.valueOf((meta & 0x8) >> 3));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 150 */     byte b0 = 0;
/* 151 */     int i = b0 | ((BlockLogsTC.LogType)state.func_177229_b(TYPE)).getMetadata();
/* 152 */     i |= ((Integer)state.func_177229_b(STAGE)).intValue() << 3;
/* 153 */     return i;
/*     */   }
/*     */   
/*     */   public String getStateName(IBlockState state)
/*     */   {
/* 158 */     return ((BlockLogsTC.LogType)state.func_177229_b(TYPE)).func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 164 */     return new BlockState(this, new IProperty[] { TYPE, STAGE });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumType
/*     */   {
/* 169 */     static final int[] WOOD_TYPE_LOOKUP = new int[BlockLogsTC.LogType.values().length];
/*     */     
/*     */     static
/*     */     {
/*     */       try
/*     */       {
/* 175 */         WOOD_TYPE_LOOKUP[BlockLogsTC.LogType.GREATWOOD.getMetadata()] = 1;
/*     */       }
/*     */       catch (NoSuchFieldError var6) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 184 */         WOOD_TYPE_LOOKUP[BlockLogsTC.LogType.SILVERWOOD.getMetadata()] = 2;
/*     */       }
/*     */       catch (NoSuchFieldError var5) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/plants/BlockSaplingTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */