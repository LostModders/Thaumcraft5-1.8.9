/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.property.ExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IUnlistedProperty;
/*     */ import net.minecraftforge.common.property.Properties.PropertyAdapter;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.CustomSoundType;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*     */ 
/*     */ public class BlockTaintFibre
/*     */   extends Block implements ITaintBlock
/*     */ {
/*  51 */   public static final IUnlistedProperty<Boolean> NORTH = new Properties.PropertyAdapter(PropertyBool.func_177716_a("north"));
/*  52 */   public static final IUnlistedProperty<Boolean> EAST = new Properties.PropertyAdapter(PropertyBool.func_177716_a("east"));
/*  53 */   public static final IUnlistedProperty<Boolean> SOUTH = new Properties.PropertyAdapter(PropertyBool.func_177716_a("south"));
/*  54 */   public static final IUnlistedProperty<Boolean> WEST = new Properties.PropertyAdapter(PropertyBool.func_177716_a("west"));
/*  55 */   public static final IUnlistedProperty<Boolean> UP = new Properties.PropertyAdapter(PropertyBool.func_177716_a("up"));
/*  56 */   public static final IUnlistedProperty<Boolean> DOWN = new Properties.PropertyAdapter(PropertyBool.func_177716_a("down"));
/*  57 */   public static final IUnlistedProperty<Integer> GROWTH = new Properties.PropertyAdapter(PropertyInteger.func_177719_a("growth", 0, 4));
/*     */   
/*     */   public BlockTaintFibre()
/*     */   {
/*  61 */     super(ThaumcraftMaterials.MATERIAL_TAINT);
/*  62 */     func_149711_c(1.0F);
/*  63 */     func_149672_a(new CustomSoundType("gore", 0.5F, 0.8F));
/*  64 */     func_149675_a(true);
/*  65 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/*  69 */     return 3;
/*     */   }
/*     */   
/*  72 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 3; }
/*     */   
/*     */   public MapColor func_180659_g(IBlockState state)
/*     */   {
/*  76 */     return MapColor.field_151678_z;
/*     */   }
/*     */   
/*     */   public void die(World world, BlockPos pos, IBlockState blockState)
/*     */   {
/*  81 */     world.func_175698_g(pos);
/*     */   }
/*     */   
/*     */   protected boolean func_149700_E()
/*     */   {
/*  86 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/*  92 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
/*     */   {
/*  98 */     state = getExtendedState(state, worldIn, pos);
/*  99 */     if ((state instanceof IExtendedBlockState)) {
/* 100 */       if ((worldIn.field_73012_v.nextInt(5) <= fortune) && ((((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 1) || (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 2) || (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 4)))
/*     */       {
/*     */ 
/*     */ 
/* 104 */         func_180635_a(worldIn, pos, new ItemStack(ItemsTC.tainted, 1, 1));
/*     */       }
/* 106 */       else if (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 3) {
/* 107 */         if (worldIn.field_73012_v.nextInt(5) <= fortune) {
/* 108 */           func_180635_a(worldIn, pos, new ItemStack(ItemsTC.tainted, 1, 0));
/*     */         }
/* 110 */         AuraHelper.pollute(worldIn, pos, 3 + worldIn.field_73012_v.nextInt(3), true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random)
/*     */   {
/* 117 */     if (!world.field_72995_K)
/*     */     {
/*     */ 
/* 120 */       state = getExtendedState(state, world, pos);
/* 121 */       if ((state instanceof IExtendedBlockState)) {
/* 122 */         if ((((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 0) && (isOnlyAdjacentToTaint(world, pos))) {
/* 123 */           die(world, pos, state);
/*     */         }
/* 125 */         else if (AuraHelper.getAura(world, pos, Aspect.FLUX) < Config.AURABASE / 25) {
/* 126 */           die(world, pos, state);
/* 127 */           Utils.resetBiomeAt(world, pos, world.field_73012_v.nextInt(25) == 0);
/*     */         } else {
/* 129 */           spreadFibres(world, pos);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void spreadFibres(World world, BlockPos pos) {
/* 136 */     if (Config.wuss) { return;
/*     */     }
/* 138 */     if (AuraHelper.getAura(world, pos, Aspect.FLUX) > Config.AURABASE / 5)
/*     */     {
/* 140 */       if (world.func_180494_b(pos).field_76756_M != Config.biomeTaintID) {
/* 141 */         Utils.setBiomeAt(world, pos, BiomeHandler.biomeTaint, world.field_73012_v.nextInt(10) == 0);
/*     */       }
/* 143 */       int xx = pos.func_177958_n() + world.field_73012_v.nextInt(3) - 1;
/* 144 */       int yy = pos.func_177956_o() + world.field_73012_v.nextInt(3) - 1;
/* 145 */       int zz = pos.func_177952_p() + world.field_73012_v.nextInt(3) - 1;
/*     */       
/* 147 */       BlockPos t = new BlockPos(xx, yy, zz);
/*     */       
/* 149 */       if (t.equals(pos)) { return;
/*     */       }
/* 151 */       IBlockState bs = world.func_180495_p(t);
/* 152 */       Material bm = bs.func_177230_c().func_149688_o();
/* 153 */       float bh = bs.func_177230_c().func_176195_g(world, t);
/* 154 */       if ((bh < 0.0F) || (bh > 10.0F)) { return;
/*     */       }
/* 156 */       if ((!bs.func_177230_c().isLeaves(world, t)) && (!bm.func_76224_d()) && ((world.func_175623_d(t)) || (bs.func_177230_c().func_176200_f(world, t)) || ((bs.func_177230_c() instanceof BlockFlower)) || ((bs.func_177230_c() instanceof IPlantable))) && (BlockUtils.isAdjacentToSolidBlock(world, t)) && (!isOnlyAdjacentToTaint(world, t)))
/*     */       {
/*     */ 
/*     */ 
/* 160 */         if (world.field_73012_v.nextFloat() < Config.taintSpreadRate) AuraHelper.drainAura(world, t, Aspect.FLUX, 1);
/* 161 */         world.func_175656_a(t, BlocksTC.taintFibre.func_176223_P());
/* 162 */         world.func_175641_c(t, BlocksTC.taintFibre, 1, 0);
/* 163 */         if (world.func_180494_b(t).field_76756_M != Config.biomeTaintID)
/* 164 */           Utils.setBiomeAt(world, t, BiomeHandler.biomeTaint);
/* 165 */         return;
/*     */       }
/*     */       
/* 168 */       if (bs.func_177230_c().isLeaves(world, t)) {
/* 169 */         EnumFacing face = null;
/* 170 */         if ((world.field_73012_v.nextFloat() < 0.6D) && ((face = BlockUtils.getFaceBlockTouching(world, t, BlocksTC.taintLog)) != null)) {
/* 171 */           world.func_175656_a(t, BlocksTC.taintFeature.func_176223_P().func_177226_a(IBlockFacing.FACING, face.func_176734_d()));
/* 172 */           if (world.field_73012_v.nextFloat() < Config.taintSpreadRate * 3.0F) AuraHelper.drainAura(world, t, Aspect.FLUX, 1);
/*     */         } else {
/* 174 */           if (world.field_73012_v.nextFloat() < Config.taintSpreadRate) AuraHelper.drainAura(world, t, Aspect.FLUX, 1);
/* 175 */           world.func_175656_a(t, BlocksTC.taintFibre.func_176223_P());
/* 176 */           world.func_175641_c(t, BlocksTC.taintFibre, 1, 0);
/*     */         }
/* 178 */         return;
/*     */       }
/*     */       
/*     */ 
/* 182 */       if (isHemmedByTaint(world, t)) {
/* 183 */         if ((Utils.isWoodLog(world, t)) && (bs.func_177230_c().func_149688_o() != ThaumcraftMaterials.MATERIAL_TAINT)) {
/* 184 */           world.func_175656_a(t, BlocksTC.taintLog.func_176223_P().func_177226_a(BlockTaintLog.VARIANT, BlockTaintLog.LogType.values()[0]).func_177226_a(BlockTaintLog.AXIS, BlockUtils.getBlockAxis(world, t)));
/*     */           
/*     */ 
/* 187 */           if (world.field_73012_v.nextFloat() < Config.taintSpreadRate * 2.0F) AuraHelper.drainAura(world, t, Aspect.FLUX, 1);
/* 188 */           return;
/*     */         }
/*     */         
/* 191 */         if ((bs.func_177230_c() == Blocks.field_150419_aX) || (bs.func_177230_c() == Blocks.field_150420_aW) || (bm == Material.field_151572_C) || (bm == Material.field_151570_A) || (bm == Material.field_151589_v) || (bm == Material.field_151583_m) || (bm == Material.field_151575_d))
/*     */         {
/* 193 */           world.func_175656_a(t, BlocksTC.taintBlock.func_176203_a(0));
/* 194 */           world.func_175641_c(t, BlocksTC.taintBlock, 1, 0);
/* 195 */           if (world.field_73012_v.nextFloat() < Config.taintSpreadRate * 2.0F) AuraHelper.drainAura(world, t, Aspect.FLUX, 1);
/* 196 */           return;
/*     */         }
/*     */         
/* 199 */         if ((bm == Material.field_151595_p) || (bm == Material.field_151578_c) || (bm == Material.field_151577_b) || (bm == Material.field_151571_B)) {
/* 200 */           world.func_175656_a(t, BlocksTC.taintBlock.func_176203_a(1));
/* 201 */           world.func_175641_c(t, BlocksTC.taintBlock, 1, 0);
/* 202 */           if (world.field_73012_v.nextFloat() < Config.taintSpreadRate * 2.0F) AuraHelper.drainAura(world, t, Aspect.FLUX, 1);
/* 203 */           return;
/*     */         }
/*     */         
/* 206 */         if (bm == Material.field_151576_e) {
/* 207 */           world.func_175656_a(t, BlocksTC.taintBlock.func_176203_a(3));
/* 208 */           world.func_175641_c(t, BlocksTC.taintBlock, 1, 0);
/* 209 */           if (world.field_73012_v.nextFloat() < Config.taintSpreadRate * 4.0F) AuraHelper.drainAura(world, t, Aspect.FLUX, 1);
/* 210 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 221 */     state = getExtendedState(state, worldIn, pos);
/* 222 */     if (((state instanceof IExtendedBlockState)) && 
/* 223 */       (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 0) && (isOnlyAdjacentToTaint(worldIn, pos))) {
/* 224 */       worldIn.func_175698_g(pos);
/*     */     }
/*     */     
/* 227 */     super.func_176204_a(worldIn, pos, state, neighborBlock);
/*     */   }
/*     */   
/*     */   public static int getAdjacentTaint(IBlockAccess world, BlockPos pos) {
/* 231 */     int count = 0;
/* 232 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 233 */       if (world.func_180495_p(pos.func_177972_a(dir)).func_177230_c().func_149688_o() != ThaumcraftMaterials.MATERIAL_TAINT) count++;
/*     */     }
/* 235 */     return count;
/*     */   }
/*     */   
/*     */   public static boolean isOnlyAdjacentToTaint(World world, BlockPos pos) {
/* 239 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 240 */       if ((!world.func_175623_d(pos.func_177972_a(dir))) && (world.func_180495_p(pos.func_177972_a(dir)).func_177230_c().func_149688_o() != ThaumcraftMaterials.MATERIAL_TAINT) && (world.func_180495_p(pos.func_177972_a(dir)).func_177230_c().func_176212_b(world, pos.func_177972_a(dir), dir.func_176734_d())))
/*     */       {
/* 242 */         return false; }
/*     */     }
/* 244 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isHemmedByTaint(World world, BlockPos pos) {
/* 248 */     int c = 0;
/* 249 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 250 */       Block block = world.func_180495_p(pos.func_177972_a(dir)).func_177230_c();
/* 251 */       if (block.func_149688_o() == ThaumcraftMaterials.MATERIAL_TAINT) { c++;
/* 252 */       } else if (world.func_175623_d(pos.func_177972_a(dir))) { c--;
/* 253 */       } else if ((!block.func_149688_o().func_76224_d()) && (!block.func_176212_b(world, pos.func_177972_a(dir), dir.func_176734_d()))) c--;
/*     */     }
/* 255 */     return c > 0;
/*     */   }
/*     */   
/*     */   public void func_176199_a(World world, BlockPos pos, Entity entity)
/*     */   {
/* 260 */     if ((!world.field_72995_K) && ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase)entity).func_70662_br()) && 
/* 261 */       (world.field_73012_v.nextInt(750) == 0)) {
/* 262 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), 200, 0, false, true));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180648_a(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam)
/*     */   {
/* 270 */     if (eventID == 1)
/*     */     {
/* 272 */       if (worldIn.field_72995_K) {
/* 273 */         worldIn.func_72980_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:roots", 0.1F, 0.9F + worldIn.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/* 275 */       return true;
/*     */     }
/* 277 */     return super.func_180648_a(worldIn, pos, state, eventID, eventParam);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/* 284 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing o) {
/* 288 */     return false;
/*     */   }
/*     */   
/*     */   private boolean drawAt(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 292 */     Block b = worldIn.func_180495_p(pos).func_177230_c();
/* 293 */     return (b != BlocksTC.taintFibre) && (b != BlocksTC.taintFeature) && (b.func_176212_b(worldIn, pos, side.func_176734_d()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180654_a(IBlockAccess iblockaccess, BlockPos pos)
/*     */   {
/* 299 */     IBlockState state = getExtendedState(iblockaccess.func_180495_p(pos), iblockaccess, pos);
/* 300 */     if ((state instanceof IExtendedBlockState)) {
/* 301 */       int c = 0;
/* 302 */       if (((Boolean)((IExtendedBlockState)state).getValue(UP)).booleanValue()) c++;
/* 303 */       if (((Boolean)((IExtendedBlockState)state).getValue(DOWN)).booleanValue()) c++;
/* 304 */       if (((Boolean)((IExtendedBlockState)state).getValue(EAST)).booleanValue()) c++;
/* 305 */       if (((Boolean)((IExtendedBlockState)state).getValue(WEST)).booleanValue()) c++;
/* 306 */       if (((Boolean)((IExtendedBlockState)state).getValue(SOUTH)).booleanValue()) c++;
/* 307 */       if (((Boolean)((IExtendedBlockState)state).getValue(NORTH)).booleanValue()) c++;
/* 308 */       if (c > 1) {
/* 309 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/* 311 */       else if ((((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 1) || (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 2))
/*     */       {
/* 313 */         func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
/*     */       }
/* 315 */       else if (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 3) {
/* 316 */         func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.375F, 0.8F);
/*     */       }
/* 318 */       else if (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 4) {
/* 319 */         func_149676_a(0.2F, 0.2F, 0.2F, 0.8F, 1.0F, 0.8F);
/*     */       } else {
/* 321 */         if (((Boolean)((IExtendedBlockState)state).getValue(UP)).booleanValue()) {
/* 322 */           func_149676_a(0.0F, 0.95F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/* 324 */         if (((Boolean)((IExtendedBlockState)state).getValue(DOWN)).booleanValue()) {
/* 325 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
/*     */         }
/* 327 */         if (((Boolean)((IExtendedBlockState)state).getValue(EAST)).booleanValue()) {
/* 328 */           func_149676_a(0.95F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/* 330 */         if (((Boolean)((IExtendedBlockState)state).getValue(WEST)).booleanValue()) {
/* 331 */           func_149676_a(0.0F, 0.0F, 0.0F, 0.05F, 1.0F, 1.0F);
/*     */         }
/* 333 */         if (((Boolean)((IExtendedBlockState)state).getValue(SOUTH)).booleanValue()) {
/* 334 */           func_149676_a(0.0F, 0.0F, 0.95F, 1.0F, 1.0F, 1.0F);
/*     */         }
/* 336 */         if (((Boolean)((IExtendedBlockState)state).getValue(NORTH)).booleanValue()) {
/* 337 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.05F);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 347 */     if (drawAt(worldIn, pos.func_177984_a(), EnumFacing.UP)) {
/* 348 */       func_149676_a(0.0F, 0.95F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 349 */       super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */     }
/* 351 */     if (drawAt(worldIn, pos.func_177977_b(), EnumFacing.DOWN)) {
/* 352 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
/* 353 */       super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */     }
/*     */     
/* 356 */     if (drawAt(worldIn, pos.func_177974_f(), EnumFacing.EAST)) {
/* 357 */       func_149676_a(0.95F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 358 */       super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */     }
/* 360 */     if (drawAt(worldIn, pos.func_177976_e(), EnumFacing.WEST)) {
/* 361 */       func_149676_a(0.0F, 0.0F, 0.0F, 0.05F, 1.0F, 1.0F);
/* 362 */       super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */     }
/*     */     
/* 365 */     if (drawAt(worldIn, pos.func_177968_d(), EnumFacing.SOUTH)) {
/* 366 */       func_149676_a(0.0F, 0.0F, 0.95F, 1.0F, 1.0F, 1.0F);
/* 367 */       super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */     }
/* 369 */     if (drawAt(worldIn, pos.func_177978_c(), EnumFacing.NORTH)) {
/* 370 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.05F);
/* 371 */       super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_176200_f(World worldIn, BlockPos pos)
/*     */   {
/* 380 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 385 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 391 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 397 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 403 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getLightValue(IBlockAccess world, BlockPos pos)
/*     */   {
/* 410 */     IBlockState state = getExtendedState(world.func_180495_p(pos), world, pos);
/* 411 */     if ((state instanceof IExtendedBlockState)) {
/* 412 */       return (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 2) || (((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 4) ? 6 : ((Integer)((IExtendedBlockState)state).getValue(GROWTH)).intValue() == 3 ? 12 : super.getLightValue(world, pos);
/*     */     }
/*     */     
/*     */ 
/* 416 */     return super.getLightValue(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 422 */     return state;
/*     */   }
/*     */   
/*     */   public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
/*     */   {
/* 427 */     if ((state instanceof IExtendedBlockState)) {
/* 428 */       IExtendedBlockState retval = (IExtendedBlockState)state;
/* 429 */       boolean d = drawAt(world, pos.func_177977_b(), EnumFacing.DOWN);
/* 430 */       boolean u = drawAt(world, pos.func_177984_a(), EnumFacing.UP);
/* 431 */       int growth = 0;
/* 432 */       Random r = new Random(pos.func_177986_g());
/* 433 */       int q = r.nextInt(50);
/* 434 */       if (d) {
/* 435 */         if (q < 4) { growth = 1;
/* 436 */         } else if ((q == 4) || (q == 5)) { growth = 2;
/* 437 */         } else if (q == 6) growth = 3;
/*     */       }
/* 439 */       if ((u) && 
/* 440 */         (q > 47)) { growth = 4;
/*     */       }
/* 442 */       return retval.withProperty(UP, Boolean.valueOf(drawAt(world, pos.func_177984_a(), EnumFacing.UP))).withProperty(DOWN, Boolean.valueOf(drawAt(world, pos.func_177977_b(), EnumFacing.DOWN))).withProperty(NORTH, Boolean.valueOf(drawAt(world, pos.func_177978_c(), EnumFacing.NORTH))).withProperty(EAST, Boolean.valueOf(drawAt(world, pos.func_177974_f(), EnumFacing.EAST))).withProperty(SOUTH, Boolean.valueOf(drawAt(world, pos.func_177968_d(), EnumFacing.SOUTH))).withProperty(WEST, Boolean.valueOf(drawAt(world, pos.func_177976_e(), EnumFacing.WEST))).withProperty(GROWTH, Integer.valueOf(growth));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 451 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 457 */     IProperty[] listedProperties = new IProperty[0];
/* 458 */     IUnlistedProperty[] unlistedProperties = { UP, DOWN, NORTH, EAST, WEST, SOUTH, GROWTH };
/* 459 */     return new ExtendedBlockState(this, listedProperties, unlistedProperties);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/taint/BlockTaintFibre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */