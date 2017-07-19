/*     */ package thaumcraft.common.blocks.world.plants;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumFacing.Axis;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ public class BlockLogsTC
/*     */   extends BlockTC
/*     */ {
/*  24 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", LogType.class);
/*  25 */   public static final PropertyEnum AXIS = PropertyEnum.func_177709_a("axis", EnumFacing.Axis.class);
/*     */   
/*     */   public BlockLogsTC()
/*     */   {
/*  29 */     super(Material.field_151575_d);
/*  30 */     setHarvestLevel("axe", 0);
/*  31 */     func_149711_c(2.0F);
/*  32 */     func_149752_b(5.0F);
/*  33 */     func_149672_a(Block.field_149766_f);
/*  34 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, LogType.GREATWOOD).func_177226_a(AXIS, EnumFacing.Axis.Y));
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase entity)
/*     */   {
/*  40 */     return super.func_180642_a(world, pos, side, hitX, hitY, hitZ, metadata, entity).func_177226_a(AXIS, side.func_176740_k());
/*     */   }
/*     */   
/*     */ 
/*     */   public int getLightValue(IBlockAccess world, BlockPos pos)
/*     */   {
/*  46 */     return func_180651_a(world.func_180495_p(pos)) == 1 ? 6 : super.getLightValue(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ItemStack func_180643_i(IBlockState state)
/*     */   {
/*  52 */     return new ItemStack(Item.func_150898_a(this), 1, func_180651_a(state));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  59 */     int baseMeta = ((LogType)state.func_177229_b(VARIANT)).ordinal();
/*  60 */     return baseMeta * 3;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  66 */     int axis = meta % 3;
/*  67 */     int type = (meta - axis) / 3;
/*  68 */     return func_176223_P().func_177226_a(VARIANT, LogType.values()[type]).func_177226_a(AXIS, EnumFacing.Axis.values()[axis]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  74 */     int baseMeta = ((LogType)state.func_177229_b(VARIANT)).ordinal();
/*  75 */     return baseMeta * 3 + ((EnumFacing.Axis)state.func_177229_b(AXIS)).ordinal();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  81 */     return new BlockState(this, new IProperty[] { AXIS, VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/*  87 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/*  93 */     return ((LogType)state.func_177229_b(VARIANT)).func_176610_l() + (fullName ? "_log" : "");
/*     */   }
/*     */   
/*  96 */   public boolean canSustainLeaves(IBlockAccess world, BlockPos pos) { return true; }
/*  97 */   public boolean isWood(IBlockAccess world, BlockPos pos) { return true; }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 102 */     byte b0 = 4;
/* 103 */     int i = b0 + 1;
/*     */     
/* 105 */     if (worldIn.func_175707_a(pos.func_177982_a(-i, -i, -i), pos.func_177982_a(i, i, i)))
/*     */     {
/* 107 */       Iterator iterator = BlockPos.func_177980_a(pos.func_177982_a(-b0, -b0, -b0), pos.func_177982_a(b0, b0, b0)).iterator();
/*     */       
/* 109 */       while (iterator.hasNext())
/*     */       {
/* 111 */         BlockPos blockpos1 = (BlockPos)iterator.next();
/* 112 */         IBlockState iblockstate1 = worldIn.func_180495_p(blockpos1);
/*     */         
/* 114 */         if (iblockstate1.func_177230_c().isLeaves(worldIn, blockpos1))
/*     */         {
/* 116 */           iblockstate1.func_177230_c().beginLeavesDecay(worldIn, blockpos1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
/*     */   {
/* 124 */     return 5;
/*     */   }
/*     */   
/* 127 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 5; }
/*     */   
/*     */   public static enum LogType
/*     */     implements IStringSerializable
/*     */   {
/* 132 */     GREATWOOD(0), 
/* 133 */     SILVERWOOD(1);
/*     */     
/*     */     private static final LogType[] META_LOOKUP;
/*     */     private final int meta;
/*     */     
/* 138 */     public String func_176610_l() { return name().toLowerCase(); }
/*     */     
/*     */ 
/*     */ 
/*     */     public String toString()
/*     */     {
/* 144 */       return func_176610_l();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private LogType(int meta)
/*     */     {
/* 152 */       this.meta = meta;
/*     */     }
/*     */     
/*     */ 
/*     */     public int getMetadata()
/*     */     {
/* 158 */       return this.meta;
/*     */     }
/*     */     
/*     */     public static LogType byMetadata(int meta)
/*     */     {
/* 163 */       if ((meta < 0) || (meta >= META_LOOKUP.length))
/*     */       {
/* 165 */         meta = 0;
/*     */       }
/*     */       
/* 168 */       return META_LOOKUP[meta];
/*     */     }
/*     */     
/*     */     static
/*     */     {
/* 147 */       META_LOOKUP = new LogType[values().length];
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
/* 173 */       LogType[] var0 = values();
/* 174 */       int var1 = var0.length;
/*     */       
/* 176 */       for (int var2 = 0; var2 < var1; var2++)
/*     */       {
/* 178 */         LogType var3 = var0[var2];
/* 179 */         META_LOOKUP[var3.getMetadata()] = var3;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/plants/BlockLogsTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */