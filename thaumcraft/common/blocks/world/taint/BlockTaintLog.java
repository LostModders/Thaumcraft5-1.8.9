/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
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
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.CustomSoundType;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class BlockTaintLog extends BlockTC implements ITaintBlock
/*     */ {
/*  31 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", LogType.class);
/*  32 */   public static final PropertyEnum AXIS = PropertyEnum.func_177709_a("axis", EnumFacing.Axis.class);
/*     */   
/*     */   public BlockTaintLog()
/*     */   {
/*  36 */     super(ThaumcraftMaterials.MATERIAL_TAINT);
/*  37 */     setHarvestLevel("axe", 0);
/*  38 */     func_149711_c(3.0F);
/*  39 */     func_149752_b(100.0F);
/*  40 */     func_149672_a(new CustomSoundType("gore", 0.5F, 0.8F));
/*  41 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, LogType.TAINTWOOD).func_177226_a(AXIS, EnumFacing.Axis.Y));
/*  42 */     func_149675_a(true);
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/*  46 */     return 4;
/*     */   }
/*     */   
/*  49 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 4; }
/*     */   
/*     */   public void die(World world, BlockPos pos, IBlockState blockState) {
/*  52 */     world.func_175656_a(pos, BlocksTC.taintDust.func_176223_P().func_177226_a(BlockFluidBase.LEVEL, Integer.valueOf(3)));
/*  53 */     Utils.resetBiomeAt(world, pos, world.field_73012_v.nextInt(50) == 0);
/*     */   }
/*     */   
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random)
/*     */   {
/*  58 */     if (!world.field_72995_K)
/*     */     {
/*     */ 
/*  61 */       if ((AuraHelper.getAura(world, pos, Aspect.FLUX) < Config.AURABASE / 25) && (random.nextInt(10) == 0)) {
/*  62 */         die(world, pos, state);
/*     */       } else {
/*  64 */         BlockTaintFibre.spreadFibres(world, pos);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase entity)
/*     */   {
/*  71 */     return super.func_180642_a(world, pos, side, hitX, hitY, hitZ, metadata, entity).func_177226_a(AXIS, side.func_176740_k());
/*     */   }
/*     */   
/*     */ 
/*     */   protected ItemStack func_180643_i(IBlockState state)
/*     */   {
/*  77 */     return new ItemStack(Item.func_150898_a(this), 1, func_180651_a(state));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  83 */     int baseMeta = ((LogType)state.func_177229_b(VARIANT)).ordinal();
/*  84 */     return baseMeta * 3;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  90 */     int axis = meta % 3;
/*  91 */     int type = (meta - axis) / 3;
/*  92 */     return func_176223_P().func_177226_a(VARIANT, LogType.values()[type]).func_177226_a(AXIS, EnumFacing.Axis.values()[axis]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  98 */     int baseMeta = ((LogType)state.func_177229_b(VARIANT)).ordinal();
/*  99 */     return baseMeta * 3 + ((EnumFacing.Axis)state.func_177229_b(AXIS)).ordinal();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 105 */     return new BlockState(this, new IProperty[] { AXIS, VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 111 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 117 */     return ((LogType)state.func_177229_b(VARIANT)).func_176610_l() + (fullName ? "_log" : "");
/*     */   }
/*     */   
/* 120 */   public boolean canSustainLeaves(IBlockAccess world, BlockPos pos) { return true; }
/* 121 */   public boolean isWood(IBlockAccess world, BlockPos pos) { return true; }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 126 */     byte b0 = 4;
/* 127 */     int i = b0 + 1;
/*     */     
/* 129 */     if (worldIn.func_175707_a(pos.func_177982_a(-i, -i, -i), pos.func_177982_a(i, i, i)))
/*     */     {
/* 131 */       Iterator iterator = BlockPos.func_177980_a(pos.func_177982_a(-b0, -b0, -b0), pos.func_177982_a(b0, b0, b0)).iterator();
/*     */       
/* 133 */       while (iterator.hasNext())
/*     */       {
/* 135 */         BlockPos blockpos1 = (BlockPos)iterator.next();
/* 136 */         IBlockState iblockstate1 = worldIn.func_180495_p(blockpos1);
/*     */         
/* 138 */         if (iblockstate1.func_177230_c().isLeaves(worldIn, blockpos1))
/*     */         {
/* 140 */           iblockstate1.func_177230_c().beginLeavesDecay(worldIn, blockpos1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static enum LogType implements IStringSerializable
/*     */   {
/* 148 */     TAINTWOOD(0);
/*     */     
/*     */     private static final LogType[] META_LOOKUP;
/*     */     private final int meta;
/*     */     
/* 153 */     public String func_176610_l() { return name().toLowerCase(); }
/*     */     
/*     */ 
/*     */ 
/*     */     public String toString()
/*     */     {
/* 159 */       return func_176610_l();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private LogType(int meta)
/*     */     {
/* 167 */       this.meta = meta;
/*     */     }
/*     */     
/*     */ 
/*     */     public int getMetadata()
/*     */     {
/* 173 */       return this.meta;
/*     */     }
/*     */     
/*     */     public static LogType byMetadata(int meta)
/*     */     {
/* 178 */       if ((meta < 0) || (meta >= META_LOOKUP.length))
/*     */       {
/* 180 */         meta = 0;
/*     */       }
/*     */       
/* 183 */       return META_LOOKUP[meta];
/*     */     }
/*     */     
/*     */     static
/*     */     {
/* 162 */       META_LOOKUP = new LogType[values().length];
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
/* 188 */       LogType[] var0 = values();
/* 189 */       int var1 = var0.length;
/*     */       
/* 191 */       for (int var2 = 0; var2 < var1; var2++)
/*     */       {
/* 193 */         LogType var3 = var0[var2];
/* 194 */         META_LOOKUP[var3.getMetadata()] = var3;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/taint/BlockTaintLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */