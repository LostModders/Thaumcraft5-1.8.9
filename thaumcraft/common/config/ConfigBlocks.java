/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.ItemBlockTC;
/*     */ import thaumcraft.common.blocks.basic.BlockBannerTC;
/*     */ import thaumcraft.common.blocks.basic.BlockBannerTCItem;
/*     */ import thaumcraft.common.blocks.basic.BlockCandle;
/*     */ import thaumcraft.common.blocks.basic.BlockCandleItem;
/*     */ import thaumcraft.common.blocks.basic.BlockEtherealBloom;
/*     */ import thaumcraft.common.blocks.basic.BlockFluidDeath;
/*     */ import thaumcraft.common.blocks.basic.BlockFluidPure;
/*     */ import thaumcraft.common.blocks.basic.BlockMetalTC;
/*     */ import thaumcraft.common.blocks.basic.BlockNitor;
/*     */ import thaumcraft.common.blocks.basic.BlockNitorItem;
/*     */ import thaumcraft.common.blocks.basic.BlockPavingStone;
/*     */ import thaumcraft.common.blocks.basic.BlockPillar;
/*     */ import thaumcraft.common.blocks.basic.BlockPlanksTC;
/*     */ import thaumcraft.common.blocks.basic.BlockPlanksTC.PlankType;
/*     */ import thaumcraft.common.blocks.basic.BlockStairsTC;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneSlabTC;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneSlabTCItem;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneTC;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneTC.StoneType;
/*     */ import thaumcraft.common.blocks.basic.BlockTable;
/*     */ import thaumcraft.common.blocks.basic.BlockTranslucent;
/*     */ import thaumcraft.common.blocks.basic.BlockWoodSlabTC;
/*     */ import thaumcraft.common.blocks.basic.BlockWoodSlabTCItem;
/*     */ import thaumcraft.common.blocks.devices.BlockAlembic;
/*     */ import thaumcraft.common.blocks.devices.BlockArcaneBore;
/*     */ import thaumcraft.common.blocks.devices.BlockArcaneBoreBase;
/*     */ import thaumcraft.common.blocks.devices.BlockArcaneEar;
/*     */ import thaumcraft.common.blocks.devices.BlockArcaneWorkbench;
/*     */ import thaumcraft.common.blocks.devices.BlockArcaneWorkbenchCharger;
/*     */ import thaumcraft.common.blocks.devices.BlockAuraTotem;
/*     */ import thaumcraft.common.blocks.devices.BlockBellows;
/*     */ import thaumcraft.common.blocks.devices.BlockBrainBox;
/*     */ import thaumcraft.common.blocks.devices.BlockCentrifuge;
/*     */ import thaumcraft.common.blocks.devices.BlockCrystallizer;
/*     */ import thaumcraft.common.blocks.devices.BlockDioptra;
/*     */ import thaumcraft.common.blocks.devices.BlockEssentiaTransport;
/*     */ import thaumcraft.common.blocks.devices.BlockGolemBuilder;
/*     */ import thaumcraft.common.blocks.devices.BlockHungryChest;
/*     */ import thaumcraft.common.blocks.devices.BlockInfernalFurnace;
/*     */ import thaumcraft.common.blocks.devices.BlockInfusionMatrix;
/*     */ import thaumcraft.common.blocks.devices.BlockJar;
/*     */ import thaumcraft.common.blocks.devices.BlockJarItem;
/*     */ import thaumcraft.common.blocks.devices.BlockLamp;
/*     */ import thaumcraft.common.blocks.devices.BlockLevitator;
/*     */ import thaumcraft.common.blocks.devices.BlockMirror;
/*     */ import thaumcraft.common.blocks.devices.BlockMirrorItem;
/*     */ import thaumcraft.common.blocks.devices.BlockNodeStabilizer;
/*     */ import thaumcraft.common.blocks.devices.BlockPatternCrafter;
/*     */ import thaumcraft.common.blocks.devices.BlockPedestal;
/*     */ import thaumcraft.common.blocks.devices.BlockRechargePedestal;
/*     */ import thaumcraft.common.blocks.devices.BlockRedstoneRelay;
/*     */ import thaumcraft.common.blocks.devices.BlockResearchTable;
/*     */ import thaumcraft.common.blocks.devices.BlockSmelter;
/*     */ import thaumcraft.common.blocks.devices.BlockSmelterAux;
/*     */ import thaumcraft.common.blocks.devices.BlockSmelterVent;
/*     */ import thaumcraft.common.blocks.devices.BlockSpa;
/*     */ import thaumcraft.common.blocks.devices.BlockThaumatorium;
/*     */ import thaumcraft.common.blocks.devices.BlockTube;
/*     */ import thaumcraft.common.blocks.devices.BlockWandWorkbench;
/*     */ import thaumcraft.common.blocks.devices.BlockWaterJug;
/*     */ import thaumcraft.common.blocks.misc.BlockBarrier;
/*     */ import thaumcraft.common.blocks.misc.BlockEffect;
/*     */ import thaumcraft.common.blocks.misc.BlockHole;
/*     */ import thaumcraft.common.blocks.misc.BlockPlaceholder;
/*     */ import thaumcraft.common.blocks.world.BlockGrassAmbient;
/*     */ import thaumcraft.common.blocks.world.BlockLoot;
/*     */ import thaumcraft.common.blocks.world.eldritch.BlockEldritch;
/*     */ import thaumcraft.common.blocks.world.eldritch.BlockVacuum;
/*     */ import thaumcraft.common.blocks.world.ore.BlockCrystal;
/*     */ import thaumcraft.common.blocks.world.ore.BlockCrystalItem;
/*     */ import thaumcraft.common.blocks.world.ore.BlockOreTC;
/*     */ import thaumcraft.common.blocks.world.plants.BlockLeavesTC;
/*     */ import thaumcraft.common.blocks.world.plants.BlockLogsTC;
/*     */ import thaumcraft.common.blocks.world.plants.BlockPlantCinderpearl;
/*     */ import thaumcraft.common.blocks.world.plants.BlockPlantShimmerleaf;
/*     */ import thaumcraft.common.blocks.world.plants.BlockPlantVishroom;
/*     */ import thaumcraft.common.blocks.world.plants.BlockSaplingTC;
/*     */ import thaumcraft.common.blocks.world.plants.BlockSaplingTCItem;
/*     */ import thaumcraft.common.blocks.world.taint.BlockFluxGoo;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaint;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintDust;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintFeature;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintFibre;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintLog;
/*     */ import thaumcraft.common.lib.CustomSoundType;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ import thaumcraft.common.tiles.TilePlaceholder;
/*     */ import thaumcraft.common.tiles.crafting.TileAlembic;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbenchCharger;
/*     */ import thaumcraft.common.tiles.crafting.TileCrucible;
/*     */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*     */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*     */ import thaumcraft.common.tiles.crafting.TileInfusionMatrix;
/*     */ import thaumcraft.common.tiles.crafting.TilePedestal;
/*     */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*     */ import thaumcraft.common.tiles.crafting.TileSmelter;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneBore;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneBoreBase;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneEar;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotemPole;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotemPull;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotemPush;
/*     */ import thaumcraft.common.tiles.devices.TileBellows;
/*     */ import thaumcraft.common.tiles.devices.TileDioptra;
/*     */ import thaumcraft.common.tiles.devices.TileHungryChest;
/*     */ import thaumcraft.common.tiles.devices.TileInfernalFurnace;
/*     */ import thaumcraft.common.tiles.devices.TileJarBrain;
/*     */ import thaumcraft.common.tiles.devices.TileLampArcane;
/*     */ import thaumcraft.common.tiles.devices.TileLampFertility;
/*     */ import thaumcraft.common.tiles.devices.TileLampGrowth;
/*     */ import thaumcraft.common.tiles.devices.TileLevitator;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ import thaumcraft.common.tiles.devices.TileNodeStabilizer;
/*     */ import thaumcraft.common.tiles.devices.TilePatternCrafter;
/*     */ import thaumcraft.common.tiles.devices.TileRechargePedestal;
/*     */ import thaumcraft.common.tiles.devices.TileRedstoneRelay;
/*     */ import thaumcraft.common.tiles.devices.TileSpa;
/*     */ import thaumcraft.common.tiles.devices.TileThaumatorium;
/*     */ import thaumcraft.common.tiles.devices.TileThaumatoriumTop;
/*     */ import thaumcraft.common.tiles.devices.TileWaterJug;
/*     */ import thaumcraft.common.tiles.essentia.TileCentrifuge;
/*     */ import thaumcraft.common.tiles.essentia.TileCrystallizer;
/*     */ import thaumcraft.common.tiles.essentia.TileEssentiaInput;
/*     */ import thaumcraft.common.tiles.essentia.TileEssentiaOutput;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillableVoid;
/*     */ import thaumcraft.common.tiles.essentia.TileTube;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeBuffer;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeFilter;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeOneway;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeRestrict;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeValve;
/*     */ import thaumcraft.common.tiles.misc.TileBanner;
/*     */ import thaumcraft.common.tiles.misc.TileBarrierStone;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchAltar;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchCap;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchCrabSpawner;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchLock;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchObelisk;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchPortal;
/*     */ import thaumcraft.common.tiles.misc.TileEtherealBloom;
/*     */ import thaumcraft.common.tiles.misc.TileHole;
/*     */ import thaumcraft.common.tiles.misc.TileNitor;
/*     */ 
/*     */ public class ConfigBlocks
/*     */ {
/*     */   public static void init()
/*     */   {
/* 170 */     initializeBlocks();
/* 171 */     registerTileEntities();
/*     */     
/*     */ 
/* 174 */     BlocksTC.oreAmber.setHarvestLevel("pickaxe", 1);
/* 175 */     BlocksTC.oreCinnabar.setHarvestLevel("pickaxe", 2);
/*     */     
/*     */ 
/* 178 */     BlockUtils.portableHoleBlackList.add("minecraft:bed");
/* 179 */     BlockUtils.portableHoleBlackList.add("minecraft:piston");
/* 180 */     BlockUtils.portableHoleBlackList.add("minecraft:piston_head");
/* 181 */     BlockUtils.portableHoleBlackList.add("minecraft:sticky_piston");
/* 182 */     BlockUtils.portableHoleBlackList.add("minecraft:piston_extension");
/* 183 */     BlockUtils.portableHoleBlackList.add("minecraft:wooden_door");
/* 184 */     BlockUtils.portableHoleBlackList.add("minecraft:spruce_door");
/* 185 */     BlockUtils.portableHoleBlackList.add("minecraft:birch_door");
/* 186 */     BlockUtils.portableHoleBlackList.add("minecraft:jungle_door");
/* 187 */     BlockUtils.portableHoleBlackList.add("minecraft:acacia_door");
/* 188 */     BlockUtils.portableHoleBlackList.add("minecraft:dark_oak_door");
/* 189 */     BlockUtils.portableHoleBlackList.add("minecraft:iron_door");
/* 190 */     BlockUtils.portableHoleBlackList.add("thaumcraft:infernal_furnace");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void initializeBlocks()
/*     */   {
/* 198 */     BlocksTC.oreAmber = registerBlock(new BlockOreTC(), "ore_amber").func_149711_c(1.25F);
/* 199 */     BlocksTC.oreCinnabar = registerBlock(new BlockOreTC(), "ore_cinnabar").func_149711_c(2.0F);
/* 200 */     BlocksTC.crystalAir = registerBlockBase(new BlockCrystal(Aspect.AIR), "crystal_aer", BlockCrystalItem.class);
/* 201 */     BlocksTC.crystalFire = registerBlockBase(new BlockCrystal(Aspect.FIRE), "crystal_ignis", BlockCrystalItem.class);
/* 202 */     BlocksTC.crystalWater = registerBlockBase(new BlockCrystal(Aspect.WATER), "crystal_aqua", BlockCrystalItem.class);
/* 203 */     BlocksTC.crystalEarth = registerBlockBase(new BlockCrystal(Aspect.EARTH), "crystal_terra", BlockCrystalItem.class);
/* 204 */     BlocksTC.crystalOrder = registerBlockBase(new BlockCrystal(Aspect.ORDER), "crystal_ordo", BlockCrystalItem.class);
/* 205 */     BlocksTC.crystalEntropy = registerBlockBase(new BlockCrystal(Aspect.ENTROPY), "crystal_perditio", BlockCrystalItem.class);
/* 206 */     BlocksTC.crystalTaint = registerBlockBase(new BlockCrystal(Aspect.FLUX), "crystal_vitium", BlockCrystalItem.class);
/*     */     
/* 208 */     BlocksTC.log = registerBlock(new BlockLogsTC(), "log");
/* 209 */     BlocksTC.leaf = registerBlockBase(new BlockLeavesTC(), "leaf", thaumcraft.common.blocks.world.plants.BlockLeavesTCItem.class);
/* 210 */     BlocksTC.plank = registerBlock(new BlockPlanksTC(), "plank");
/* 211 */     BlocksTC.stairsGreatwood = registerBlockBase(new BlockStairsTC(BlocksTC.plank.func_176223_P().func_177226_a(BlockPlanksTC.VARIANT, BlockPlanksTC.PlankType.GREATWOOD)), "greatwood_stairs");
/* 212 */     BlocksTC.stairsSilverwood = registerBlockBase(new BlockStairsTC(BlocksTC.plank.func_176223_P().func_177226_a(BlockPlanksTC.VARIANT, BlockPlanksTC.PlankType.SILVERWOOD)), "silverwood_stairs");
/* 213 */     BlocksTC.slabWood = new BlockWoodSlabTC().func_149711_c(2.0F).func_149752_b(5.0F).func_149672_a(Block.field_149766_f).func_149663_c("slab_wood");
/* 214 */     BlocksTC.doubleSlabWood = new BlockWoodSlabTC().func_149711_c(2.0F).func_149752_b(5.0F).func_149672_a(Block.field_149766_f).func_149663_c("slab_wood_double");
/* 215 */     GameRegistry.registerBlock(BlocksTC.slabWood, BlockWoodSlabTCItem.class, "slab_wood");
/* 216 */     GameRegistry.registerBlock(BlocksTC.doubleSlabWood, "slab_wood_double");
/*     */     
/* 218 */     BlocksTC.stone = registerBlock(new BlockStoneTC(), "stone");
/* 219 */     BlocksTC.stairsArcane = registerBlockBase(new BlockStairsTC(BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ARCANE)), "arcane_stairs");
/* 220 */     BlocksTC.stairsArcaneBrick = registerBlockBase(new BlockStairsTC(BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ARCANE_BRICK)), "arcane_brick_stairs");
/* 221 */     BlocksTC.stairsAncient = registerBlockBase(new BlockStairsTC(BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ANCIENT)), "ancient_stairs");
/* 222 */     BlocksTC.slabStone = new BlockStoneSlabTC().func_149711_c(2.0F).func_149752_b(10.0F).func_149672_a(Block.field_149780_i).func_149663_c("slab_stone");
/* 223 */     BlocksTC.doubleSlabStone = new BlockStoneSlabTC().func_149711_c(2.0F).func_149752_b(10.0F).func_149672_a(Block.field_149780_i).func_149663_c("slab_stone_double");
/* 224 */     GameRegistry.registerBlock(BlocksTC.slabStone, BlockStoneSlabTCItem.class, "slab_stone");
/* 225 */     GameRegistry.registerBlock(BlocksTC.doubleSlabStone, "slab_stone_double");
/* 226 */     BlocksTC.sapling = new BlockSaplingTC().func_149663_c("sapling");
/* 227 */     GameRegistry.registerBlock(BlocksTC.sapling, BlockSaplingTCItem.class, "sapling");
/* 228 */     BlocksTC.shimmerleaf = registerBlockBase(new BlockPlantShimmerleaf(), "shimmerleaf");
/* 229 */     BlocksTC.cinderpearl = registerBlockBase(new BlockPlantCinderpearl(), "cinderpearl");
/* 230 */     BlocksTC.vishroom = registerBlockBase(new BlockPlantVishroom(), "vishroom");
/* 231 */     BlocksTC.bloom = registerBlockBase(new BlockEtherealBloom(), "bloom");
/* 232 */     BlocksTC.translucent = registerBlock(new BlockTranslucent(), "translucent");
/* 233 */     BlocksTC.fleshBlock = registerBlock(new BlockTC(Material.field_151583_m), "flesh_block").func_149672_a(new CustomSoundType("gore", 0.5F, 0.8F)).func_149711_c(0.25F);
/* 234 */     BlocksTC.lootCrate = registerBlock(new BlockLoot(Material.field_151575_d), "loot_crate").func_149672_a(Block.field_149766_f);
/* 235 */     BlocksTC.lootUrn = registerBlock(new BlockLoot(Material.field_151576_e), "loot_urn").func_149672_a(new CustomSoundType("urnbreak", 1.0F, 0.7F));
/* 236 */     BlocksTC.taintFibre = registerBlockBase(new BlockTaintFibre(), "taint_fibre");
/* 237 */     BlocksTC.taintBlock = registerBlock(new BlockTaint(), "taint");
/* 238 */     BlocksTC.taintFeature = registerBlock(new BlockTaintFeature(), "taint_feature");
/* 239 */     BlocksTC.taintLog = registerBlock(new BlockTaintLog(), "taint_log");
/* 240 */     BlocksTC.eldritch = registerBlock(new BlockEldritch(), "eldritch");
/* 241 */     BlocksTC.grassAmbient = registerBlockBase(new BlockGrassAmbient(), "grass_ambient");
/*     */     
/*     */ 
/* 244 */     BlocksTC.tableWood = registerBlock(new BlockTable(Material.field_151575_d), "table_wood").func_149672_a(Block.field_149766_f).func_149711_c(2.0F);
/* 245 */     BlocksTC.tableStone = registerBlock(new BlockTable(Material.field_151576_e), "table_stone").func_149672_a(Block.field_149769_e).func_149711_c(2.5F);
/* 246 */     BlocksTC.candle = registerBlock(new BlockCandle(), "candle", BlockCandleItem.class);
/* 247 */     BlocksTC.nitor = registerBlock(new BlockNitor(), "nitor", BlockNitorItem.class);
/* 248 */     BlocksTC.pedestal = registerBlock(new BlockPedestal(), "pedestal");
/* 249 */     BlocksTC.banner = registerBlock(new BlockBannerTC(), "banner", BlockBannerTCItem.class);
/* 250 */     BlocksTC.metal = registerBlock(new BlockMetalTC(), "metal");
/* 251 */     BlocksTC.pavingStone = registerBlock(new BlockPavingStone(), "paving_stone");
/* 252 */     BlocksTC.barrier = registerBlockBase(new BlockBarrier(), "barrier");
/* 253 */     BlocksTC.pillar = registerBlock(new BlockPillar(), "pillar");
/* 254 */     BlocksTC.redstoneRelay = registerBlock(new BlockRedstoneRelay(), "redstone_relay");
/*     */     
/*     */ 
/* 257 */     BlocksTC.researchTable = registerBlock(new BlockResearchTable(), "research_table");
/* 258 */     BlocksTC.arcaneWorkbench = registerBlock(new BlockArcaneWorkbench(), "arcane_workbench");
/* 259 */     BlocksTC.arcaneWorkbenchCharger = registerBlock(new BlockArcaneWorkbenchCharger(), "arcane_workbench_charger");
/* 260 */     BlocksTC.crucible = registerBlock(new thaumcraft.common.blocks.devices.BlockCrucible(), "crucible");
/* 261 */     BlocksTC.arcaneEar = registerBlock(new BlockArcaneEar(), "arcane_ear");
/* 262 */     BlocksTC.lampArcane = registerBlock(new BlockLamp(TileLampArcane.class), "lamp_arcane");
/* 263 */     BlocksTC.lampFertility = registerBlock(new BlockLamp(TileLampFertility.class), "lamp_fertility");
/* 264 */     BlocksTC.lampGrowth = registerBlock(new BlockLamp(TileLampGrowth.class), "lamp_growth");
/* 265 */     BlocksTC.levitator = registerBlock(new BlockLevitator(), "levitator");
/* 266 */     BlocksTC.crystallizer = registerBlock(new BlockCrystallizer(), "crystallizer");
/* 267 */     BlocksTC.centrifuge = registerBlock(new BlockCentrifuge(), "centrifuge");
/* 268 */     BlocksTC.bellows = registerBlock(new BlockBellows(), "bellows");
/* 269 */     BlocksTC.smelterBasic = registerBlock(new BlockSmelter(), "smelter_basic");
/* 270 */     BlocksTC.smelterThaumium = registerBlock(new BlockSmelter(), "smelter_thaumium");
/* 271 */     BlocksTC.smelterVoid = registerBlock(new BlockSmelter(), "smelter_void");
/* 272 */     BlocksTC.smelterAux = registerBlock(new BlockSmelterAux(), "smelter_aux");
/* 273 */     BlocksTC.smelterVent = registerBlock(new BlockSmelterVent(), "smelter_vent");
/* 274 */     BlocksTC.alembic = registerBlock(new BlockAlembic(), "alembic");
/* 275 */     BlocksTC.dioptra = registerBlock(new BlockDioptra(), "dioptra");
/* 276 */     BlocksTC.rechargePedestal = registerBlock(new BlockRechargePedestal(), "recharge_pedestal");
/* 277 */     BlocksTC.wandWorkbench = registerBlock(new BlockWandWorkbench(), "wand_workbench");
/* 278 */     BlocksTC.hungryChest = registerBlockBase(new BlockHungryChest(), "hungry_chest");
/* 279 */     BlocksTC.tube = registerBlock(new BlockTube(), "tube");
/* 280 */     BlocksTC.jar = registerBlock(new BlockJar(), "jar", BlockJarItem.class);
/* 281 */     BlocksTC.infusionMatrix = registerBlock(new BlockInfusionMatrix(), "infusion_matrix");
/* 282 */     BlocksTC.infernalFurnace = registerBlock(new BlockInfernalFurnace(), "infernal_furnace");
/* 283 */     BlocksTC.waterJug = registerBlock(new BlockWaterJug(), "water_jug");
/* 284 */     BlocksTC.arcaneBore = registerBlock(new BlockArcaneBore(), "arcane_bore");
/* 285 */     BlocksTC.arcaneBoreBase = registerBlock(new BlockArcaneBoreBase(), "arcane_bore_base");
/* 286 */     BlocksTC.thaumatorium = registerBlock(new BlockThaumatorium(), "thaumatorium");
/* 287 */     BlocksTC.brainBox = registerBlock(new BlockBrainBox(), "brain_box");
/* 288 */     BlocksTC.auraTotem = registerBlock(new BlockAuraTotem(), "aura_totem");
/* 289 */     BlocksTC.spa = registerBlock(new BlockSpa(), "spa");
/* 290 */     BlocksTC.golemBuilder = registerBlock(new BlockGolemBuilder(), "golem_builder");
/* 291 */     BlocksTC.nodeStabilizer = registerBlock(new BlockNodeStabilizer(), "node_stabilizer");
/*     */     
/* 293 */     BlocksTC.mirror = new BlockMirror(TileMirror.class).func_149663_c("mirror");
/* 294 */     GameRegistry.registerBlock(BlocksTC.mirror, BlockMirrorItem.class, "mirror");
/* 295 */     BlocksTC.mirrorEssentia = new BlockMirror(TileMirrorEssentia.class).func_149663_c("mirror_essentia");
/* 296 */     GameRegistry.registerBlock(BlocksTC.mirrorEssentia, BlockMirrorItem.class, "mirror_essentia");
/*     */     
/* 298 */     BlocksTC.essentiaTransportInput = registerBlock(new BlockEssentiaTransport(TileEssentiaInput.class), "essentia_input");
/* 299 */     BlocksTC.essentiaTransportOutput = registerBlock(new BlockEssentiaTransport(TileEssentiaOutput.class), "essentia_output");
/*     */     
/* 301 */     BlocksTC.patternCrafter = registerBlock(new BlockPatternCrafter(), "pattern_crafter");
/*     */     
/*     */ 
/* 304 */     FluidRegistry.registerFluid(FluidFluxGoo.instance);
/* 305 */     BlocksTC.fluxGoo = new BlockFluxGoo();
/* 306 */     GameRegistry.registerBlock(BlocksTC.fluxGoo, "flux_goo");
/*     */     
/* 308 */     FluidRegistry.registerFluid(FluidDeath.instance);
/* 309 */     BlocksTC.liquidDeath = new BlockFluidDeath();
/* 310 */     GameRegistry.registerBlock(BlocksTC.liquidDeath, "liquid_death");
/*     */     
/* 312 */     FluidRegistry.registerFluid(FluidPure.instance);
/* 313 */     BlocksTC.purifyingFluid = new BlockFluidPure();
/* 314 */     GameRegistry.registerBlock(BlocksTC.purifyingFluid, "purifying_fluid");
/*     */     
/* 316 */     FluidRegistry.registerFluid(FluidTaintDust.instance);
/* 317 */     BlocksTC.taintDust = new BlockTaintDust();
/* 318 */     GameRegistry.registerBlock(BlocksTC.taintDust, "taint_dust");
/*     */     
/*     */ 
/* 321 */     BlocksTC.vacuum = registerBlockBase(new BlockVacuum(), "vacuum");
/* 322 */     BlocksTC.hole = registerBlockBase(new BlockHole(), "hole");
/* 323 */     BlocksTC.effect = registerBlock(new BlockEffect(), "effect");
/* 324 */     BlocksTC.placeholder = registerBlock(new BlockPlaceholder(), "placeholder");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void registerTileEntities()
/*     */   {
/* 331 */     GameRegistry.registerTileEntity(TileArcaneEar.class, "TileArcaneEar");
/* 332 */     GameRegistry.registerTileEntity(TileLevitator.class, "TileLevitator");
/* 333 */     GameRegistry.registerTileEntity(TileDioptra.class, "TileDioptra");
/* 334 */     GameRegistry.registerTileEntity(TileCrucible.class, "TileCrucible");
/* 335 */     GameRegistry.registerTileEntity(TileArcaneWorkbench.class, "TileArcaneWorkbench");
/* 336 */     GameRegistry.registerTileEntity(TileFocalManipulator.class, "TileFocalManipulator");
/* 337 */     GameRegistry.registerTileEntity(TilePedestal.class, "TilePedestal");
/* 338 */     GameRegistry.registerTileEntity(TileRechargePedestal.class, "TileRechargePedestal");
/* 339 */     GameRegistry.registerTileEntity(TileArcaneWorkbenchCharger.class, "TileArcaneWorkbenchCharger");
/* 340 */     GameRegistry.registerTileEntity(TileResearchTable.class, "TileResearchTable");
/* 341 */     GameRegistry.registerTileEntity(TileTube.class, "TileTube");
/* 342 */     GameRegistry.registerTileEntity(TileTubeValve.class, "TileTubeValve");
/* 343 */     GameRegistry.registerTileEntity(TileTubeFilter.class, "TileTubeFilter");
/* 344 */     GameRegistry.registerTileEntity(TileTubeRestrict.class, "TileTubeRestrict");
/* 345 */     GameRegistry.registerTileEntity(TileTubeOneway.class, "TileTubeOneway");
/* 346 */     GameRegistry.registerTileEntity(TileHungryChest.class, "TileChestHungry");
/* 347 */     GameRegistry.registerTileEntity(TileTubeBuffer.class, "TileTubeBuffer");
/* 348 */     GameRegistry.registerTileEntity(TileCentrifuge.class, "TileCentrifuge");
/* 349 */     GameRegistry.registerTileEntity(TileCrystallizer.class, "TileCrystallizer");
/* 350 */     GameRegistry.registerTileEntity(TileJarFillable.class, "TileJar");
/* 351 */     GameRegistry.registerTileEntity(TileJarFillableVoid.class, "TileJarVoid");
/* 352 */     GameRegistry.registerTileEntity(TileJarBrain.class, "TileJarBrain");
/* 353 */     GameRegistry.registerTileEntity(TileBellows.class, "TileBellows");
/* 354 */     GameRegistry.registerTileEntity(TileSmelter.class, "TileSmelter");
/* 355 */     GameRegistry.registerTileEntity(TileAlembic.class, "TileAlembic");
/* 356 */     GameRegistry.registerTileEntity(TileInfusionMatrix.class, "TileInfusionMatrix");
/* 357 */     GameRegistry.registerTileEntity(TileWaterJug.class, "TileWaterJug");
/* 358 */     GameRegistry.registerTileEntity(TileInfernalFurnace.class, "TileInfernalFurnace");
/* 359 */     GameRegistry.registerTileEntity(TileArcaneBore.class, "TileArcaneBore");
/* 360 */     GameRegistry.registerTileEntity(TileArcaneBoreBase.class, "TileArcaneBoreBase");
/* 361 */     GameRegistry.registerTileEntity(TileThaumatorium.class, "TileThaumatorium");
/* 362 */     GameRegistry.registerTileEntity(TileThaumatoriumTop.class, "TileThaumatoriumTop");
/* 363 */     GameRegistry.registerTileEntity(TileAuraTotemPush.class, "TileAuraTotemPush");
/* 364 */     GameRegistry.registerTileEntity(TileAuraTotemPull.class, "TileAuraTotemPull");
/* 365 */     GameRegistry.registerTileEntity(TileAuraTotemPole.class, "TileAuraTotemPole");
/* 366 */     GameRegistry.registerTileEntity(TileSpa.class, "TileSpa");
/* 367 */     GameRegistry.registerTileEntity(TileLampGrowth.class, "TileLampGrowth");
/* 368 */     GameRegistry.registerTileEntity(TileLampArcane.class, "TileLampArcane");
/* 369 */     GameRegistry.registerTileEntity(TileLampFertility.class, "TileLampFertility");
/* 370 */     GameRegistry.registerTileEntity(TileMirror.class, "TileMirror");
/* 371 */     GameRegistry.registerTileEntity(TileMirrorEssentia.class, "TileMirrorEssentia");
/* 372 */     GameRegistry.registerTileEntity(TileRedstoneRelay.class, "TileRedstoneRelay");
/* 373 */     GameRegistry.registerTileEntity(TileGolemBuilder.class, "TileGolemBuilder");
/* 374 */     GameRegistry.registerTileEntity(TileNodeStabilizer.class, "TileNodeStabilizer");
/* 375 */     GameRegistry.registerTileEntity(TileEssentiaInput.class, "TileEssentiaInput");
/* 376 */     GameRegistry.registerTileEntity(TileEssentiaOutput.class, "TileEssentiaOutput");
/* 377 */     GameRegistry.registerTileEntity(TilePatternCrafter.class, "TilePatternCrafter");
/*     */     
/*     */ 
/* 380 */     GameRegistry.registerTileEntity(TilePlaceholder.class, "TilePlaceholder");
/* 381 */     GameRegistry.registerTileEntity(TileHole.class, "TileHole");
/* 382 */     GameRegistry.registerTileEntity(TileNitor.class, "TileNitor");
/* 383 */     GameRegistry.registerTileEntity(TileEtherealBloom.class, "TileEtherealBloom");
/* 384 */     GameRegistry.registerTileEntity(TileBanner.class, "TileBanner");
/* 385 */     GameRegistry.registerTileEntity(TileBarrierStone.class, "TileBarrierStone");
/* 386 */     GameRegistry.registerTileEntity(TileEldritchPortal.class, "TileEldritchPortal");
/* 387 */     GameRegistry.registerTileEntity(TileEldritchLock.class, "TileEldritchLock");
/* 388 */     GameRegistry.registerTileEntity(TileEldritchAltar.class, "TileEldritchAltar");
/* 389 */     GameRegistry.registerTileEntity(TileEldritchObelisk.class, "TileEldritchObelisk");
/* 390 */     GameRegistry.registerTileEntity(TileEldritchCap.class, "TileEldritchCap");
/* 391 */     GameRegistry.registerTileEntity(TileEldritchCrabSpawner.class, "TileEldritchCrabSpawner");
/* 392 */     GameRegistry.registerTileEntity(TileOwned.class, "TileOwned");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static Block registerBlock(BlockTC block, String name)
/*     */   {
/* 399 */     return registerBlock(block, name, null);
/*     */   }
/*     */   
/*     */   private static Block registerBlock(BlockTC block, String name, Class ib)
/*     */   {
/* 404 */     block.func_149663_c(name);
/* 405 */     if ((ib != null) && (!block.defineVariantsForItemBlock())) {
/* 406 */       GameRegistry.registerBlock(block, ib, name);
/* 407 */       Thaumcraft.proxy.registerVariantName(Item.func_150898_a(block), name);
/*     */ 
/*     */     }
/* 410 */     else if (block.hasProperties())
/*     */     {
/* 412 */       Class clazz = ItemBlockTC.class;
/* 413 */       if (ib != null) clazz = ib;
/* 414 */       GameRegistry.registerBlock(block, clazz, name);
/*     */       
/* 416 */       for (IBlockState state : block.states)
/*     */       {
/* 418 */         String stateName = block.getStateName(state, true);
/* 419 */         Thaumcraft.proxy.registerVariantName(Item.func_150898_a(block), stateName);
/* 420 */         Thaumcraft.proxy.registerBlockMesh(block, block.func_176201_c(state), stateName);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 425 */       GameRegistry.registerBlock(block, name);
/* 426 */       Thaumcraft.proxy.registerVariantName(Item.func_150898_a(block), name);
/* 427 */       Thaumcraft.proxy.registerBlockMesh(block, 0, name);
/*     */     }
/*     */     
/* 430 */     return block;
/*     */   }
/*     */   
/* 433 */   private static Block registerBlockBase(Block block, String name) { return registerBlockBase(block, name, null); }
/*     */   
/*     */ 
/*     */   private static Block registerBlockBase(Block block, String name, Class ib)
/*     */   {
/* 438 */     block.func_149663_c(name);
/*     */     
/* 440 */     if (ib == null) {
/* 441 */       GameRegistry.registerBlock(block, name);
/*     */     } else {
/* 443 */       GameRegistry.registerBlock(block, ib, name);
/*     */     }
/*     */     
/* 446 */     Thaumcraft.proxy.registerVariantName(Item.func_150898_a(block), name);
/* 447 */     Thaumcraft.proxy.registerBlockMesh(block, 0, name);
/*     */     
/* 449 */     return block;
/*     */   }
/*     */   
/*     */   public static final class FluidPure
/*     */     extends Fluid
/*     */   {
/*     */     public static final String name = "purifying_fluid";
/* 456 */     public static final FluidPure instance = new FluidPure();
/*     */     
/*     */     private FluidPure()
/*     */     {
/* 460 */       super(new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/water_flow"));
/* 461 */       setLuminosity(5);
/* 462 */       setRarity(EnumRarity.RARE);
/*     */     }
/*     */     
/*     */ 
/*     */     public int getColor()
/*     */     {
/* 468 */       return 2013252778;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class FluidDeath extends Fluid
/*     */   {
/*     */     public static final String name = "liquid_death";
/* 475 */     public static final FluidDeath instance = new FluidDeath();
/*     */     
/*     */     private FluidDeath()
/*     */     {
/* 479 */       super(new ResourceLocation("thaumcraft:blocks/animatedglow"), new ResourceLocation("thaumcraft:blocks/animatedglow"));
/*     */       
/* 481 */       setViscosity(1500);
/* 482 */       setRarity(EnumRarity.RARE);
/*     */     }
/*     */     
/*     */     public int getColor()
/*     */     {
/* 487 */       return -263978855;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class FluidFluxGoo extends Fluid
/*     */   {
/*     */     public static final String name = "flux_goo";
/* 494 */     public static final FluidFluxGoo instance = new FluidFluxGoo();
/*     */     
/*     */     private FluidFluxGoo()
/*     */     {
/* 498 */       super(new ResourceLocation("thaumcraft:blocks/flux_goo"), new ResourceLocation("thaumcraft:blocks/flux_goo"));
/* 499 */       setViscosity(6000);
/* 500 */       setDensity(8);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class FluidTaintDust extends Fluid
/*     */   {
/*     */     public static final String name = "taint_dust";
/* 507 */     public static final FluidTaintDust instance = new FluidTaintDust();
/*     */     
/*     */     private FluidTaintDust()
/*     */     {
/* 511 */       super(new ResourceLocation("thaumcraft:blocks/taint_dust"), new ResourceLocation("thaumcraft:blocks/taint_dust"));
/* 512 */       setViscosity(8000);
/* 513 */       setDensity(2000);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/config/ConfigBlocks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */