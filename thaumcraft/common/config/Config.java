/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.potion.PotionHelper;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraftforge.common.ChestGenHooks;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.common.config.Property;
/*     */ import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
/*     */ import net.minecraftforge.fml.common.registry.GameData;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.EntityFallingTaint;
/*     */ import thaumcraft.common.entities.construct.EntityOwnedConstruct;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchCrab;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.EntityInhabitedZombie;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.entities.monster.EntityWisp;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ import thaumcraft.common.lib.enchantment.EnchantmentHaste;
/*     */ import thaumcraft.common.lib.enchantment.EnchantmentRepair;
/*     */ import thaumcraft.common.lib.potions.PotionBlurredVision;
/*     */ import thaumcraft.common.lib.potions.PotionDeathGaze;
/*     */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*     */ import thaumcraft.common.lib.potions.PotionSunScorned;
/*     */ import thaumcraft.common.lib.potions.PotionThaumarhia;
/*     */ import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
/*     */ import thaumcraft.common.lib.potions.PotionWarpWard;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeGenEerie;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeGenEldritch;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeGenMagicalForest;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeGenTaint;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Config
/*     */ {
/*     */   public static Configuration config;
/*     */   public static final String CATEGORY_GRAPHICS = "Graphics";
/*     */   public static final String CATEGORY_ENCH = "Enchantments";
/*     */   public static final String CATEGORY_ENTITIES = "Entities";
/*     */   public static final String CATEGORY_BIOMES = "Biomes";
/*     */   public static final String CATEGORY_RESEARCH = "Research";
/*     */   public static final String CATEGORY_WORLD = "World_Generation";
/*     */   public static final String CATEGORY_REGEN = "World_Regeneration";
/*     */   public static final String CATEGORY_SPAWN = "Monster_Spawning";
/*     */   public static final String CATEGORY_RUNIC = "Runic_Shielding";
/*  84 */   public static int overworldDim = 0;
/*  85 */   public static int biomeTaintID = 192;
/*  86 */   public static int biomeMagicalForestID = 193;
/*  87 */   public static int biomeEerieID = 194;
/*  88 */   public static int biomeEldritchID = 195;
/*  89 */   public static int biomeTaintWeight = 2;
/*  90 */   public static int biomeMagicalForestWeight = 5;
/*  91 */   public static float taintSpreadRate = 0.05F;
/*  92 */   public static boolean wuss = false;
/*  93 */   public static int dimensionOuterId = -42;
/*  94 */   public static boolean championMobs = true;
/*  95 */   public static int AURABASE = 100;
/*  96 */   public static int oreDensity = 100;
/*  97 */   public static int dustDegrade = 25;
/*  98 */   public static boolean soilToDirt = false;
/*     */   
/*     */ 
/* 101 */   public static int shieldRecharge = 2000;
/* 102 */   public static int shieldWait = 4000;
/* 103 */   public static int shieldCost = 1;
/*     */   
/*     */ 
/* 106 */   public static boolean largeTagText = false;
/* 107 */   public static boolean colorBlind = false;
/* 108 */   public static boolean shaders = true;
/* 109 */   public static boolean nostress = false;
/* 110 */   public static boolean crooked = true;
/* 111 */   public static boolean showTags = false;
/* 112 */   public static boolean blueBiome = false;
/* 113 */   public static boolean allowMirrors = true;
/* 114 */   public static boolean dialBottom = false;
/* 115 */   public static boolean showGolemEmotes = true;
/* 116 */   public static int nodeRefresh = 10;
/*     */   
/*     */   public static final float auraSize = 4.0F;
/* 119 */   public static boolean genAura = true;
/* 120 */   public static boolean genStructure = true;
/* 121 */   public static boolean genCinnibar = true;
/* 122 */   public static boolean genAmber = true;
/* 123 */   public static boolean genCrystals = true;
/* 124 */   public static boolean genTrees = true;
/* 125 */   public static boolean genTaint = true;
/*     */   
/* 127 */   public static boolean regenAura = false;
/* 128 */   public static boolean regenStructure = false;
/* 129 */   public static boolean regenCinnibar = false;
/* 130 */   public static boolean regenAmber = false;
/* 131 */   public static boolean regenCrystals = false;
/* 132 */   public static boolean regenTrees = false;
/* 133 */   public static boolean regenTaint = false;
/* 134 */   public static String regenKey = "DEFAULT";
/* 135 */   public static boolean wardedStone = true;
/* 136 */   public static boolean allowCheatSheet = true;
/* 137 */   public static boolean golemChestInteract = true;
/* 138 */   public static int nodeRarity = 33;
/* 139 */   public static int specialNodeRarity = 10;
/* 140 */   public static int chargeBarPos = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 146 */   public static int researchDifficulty = 0;
/* 147 */   public static int researchAmount = 1;
/*     */   
/* 149 */   public static int CAURABASE = 100;
/* 150 */   public static boolean CwardedStone = true;
/* 151 */   public static boolean CallowCheatSheet = true;
/* 152 */   public static boolean CallowMirrors = true;
/* 153 */   public static boolean ChardNode = true;
/* 154 */   public static boolean Cwuss = false;
/* 155 */   public static int CresearchDifficulty = 0;
/* 156 */   public static int CresearchAmount = 1;
/*     */   
/* 158 */   public static boolean spawnAngryZombie = true;
/* 159 */   public static boolean spawnFireBat = true;
/* 160 */   public static boolean spawnTaintacle = true;
/* 161 */   public static boolean spawnWisp = true;
/* 162 */   public static boolean spawnTaintSpore = true;
/* 163 */   public static boolean spawnPech = true;
/* 164 */   public static boolean spawnElder = true;
/*     */   
/*     */   public static void initialize(File file) {
/* 167 */     config = new Configuration(file);
/* 168 */     config.addCustomCategoryComment("Graphics", "Graphics");
/* 169 */     config.addCustomCategoryComment("Enchantments", "Custom enchantments");
/* 170 */     config.addCustomCategoryComment("Monster_Spawning", "Will these mobs spawn");
/* 171 */     config.addCustomCategoryComment("Research", "Various research related things.");
/* 172 */     config.addCustomCategoryComment("World_Generation", "Settings to turn certain world-gen on or off.");
/* 173 */     config.addCustomCategoryComment("World_Regeneration", "If a chunk is encountered that skipped TC worldgen, then the game will attempt to regenerate certain world features if they are set to true. CAUTION: Best used for worlds created before you added this mod, and only if you know what you are doing. Backups are advised.");
/* 174 */     config.addCustomCategoryComment("Biomes", "Biomes and effects");
/* 175 */     config.addCustomCategoryComment("Runic_Shielding", "Runic Shielding");
/*     */     
/*     */ 
/* 178 */     config.load();
/*     */     
/* 180 */     syncConfigurable();
/*     */     
/*     */ 
/*     */ 
/* 184 */     Property biomeTaintProp = config.get("Biomes", "biome_taint", biomeTaintID);
/* 185 */     biomeTaintProp.comment = "Taint biome id";
/* 186 */     biomeTaintID = biomeTaintProp.getInt();
/* 187 */     if (net.minecraft.world.biome.BiomeGenBase.func_150565_n()[biomeTaintID] != null) {
/* 188 */       biomeTaintID = BiomeHandler.getFirstFreeBiomeSlot(biomeTaintID);
/* 189 */       biomeTaintProp.set(biomeTaintID);
/*     */     }
/*     */     try {
/* 192 */       BiomeHandler.biomeTaint = new BiomeGenTaint(biomeTaintID);
/*     */     } catch (Exception e) {
/* 194 */       Thaumcraft.log.fatal("Could not register Taint Biome");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 199 */     Property mfcp = config.get("Biomes", "magical_forest_biome_weight", 3);
/* 200 */     mfcp.comment = "higher values increases number of magical forest biomes. If you are using biome addon mods you probably want to increase this weight quite a bit";
/* 201 */     biomeMagicalForestWeight = mfcp.getInt();
/*     */     
/* 203 */     Property biomeMFProp = config.get("Biomes", "biome_magical_forest", biomeMagicalForestID);
/* 204 */     biomeMFProp.comment = "Magical Forest biome id";
/* 205 */     biomeMagicalForestID = biomeMFProp.getInt();
/* 206 */     if (net.minecraft.world.biome.BiomeGenBase.func_150565_n()[biomeMagicalForestID] != null) {
/* 207 */       biomeMagicalForestID = BiomeHandler.getFirstFreeBiomeSlot(biomeMagicalForestID);
/* 208 */       biomeMFProp.set(biomeMagicalForestID);
/*     */     }
/*     */     try {
/* 211 */       BiomeHandler.biomeMagicalForest = new BiomeGenMagicalForest(biomeMagicalForestID);
/*     */     } catch (Exception e) {
/* 213 */       Thaumcraft.log.fatal("Could not register Magical Forest Biome");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 218 */     Property biomeEerieProp = config.get("Biomes", "biome_eerie", biomeEerieID);
/* 219 */     biomeEerieProp.comment = "Eerie biome id";
/* 220 */     biomeEerieID = biomeEerieProp.getInt();
/* 221 */     if (net.minecraft.world.biome.BiomeGenBase.func_150565_n()[biomeEerieID] != null) {
/* 222 */       biomeEerieID = BiomeHandler.getFirstFreeBiomeSlot(biomeEerieID);
/* 223 */       biomeEerieProp.set(biomeEerieID);
/*     */     }
/*     */     try {
/* 226 */       BiomeHandler.biomeEerie = new BiomeGenEerie(biomeEerieID);
/*     */     } catch (Exception e) {
/* 228 */       Thaumcraft.log.fatal("Could not register Eerie Biome");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 233 */     Property biomeEldritchProp = config.get("Biomes", "biome_eldritch", biomeEldritchID);
/* 234 */     biomeEldritchProp.comment = "Eldritch Lands biome id";
/* 235 */     biomeEldritchID = biomeEldritchProp.getInt();
/* 236 */     if (net.minecraft.world.biome.BiomeGenBase.func_150565_n()[biomeEldritchID] != null) {
/* 237 */       biomeEldritchID = BiomeHandler.getFirstFreeBiomeSlot(biomeEldritchID);
/* 238 */       biomeEldritchProp.set(biomeEldritchID);
/*     */     }
/*     */     try {
/* 241 */       BiomeHandler.biomeEldritchLands = new BiomeGenEldritch(biomeEldritchID);
/*     */     } catch (Exception e) {
/* 243 */       Thaumcraft.log.fatal("Could not register Eldritch Lands Biome");
/*     */     }
/*     */     
/*     */ 
/* 247 */     Property dimEldritch = config.get("Biomes", "outer_lands_dim", dimensionOuterId);
/* 248 */     dimensionOuterId = dimEldritch.getInt();
/*     */     
/* 250 */     Property mdim = config.get("Biomes", "main_dim", overworldDim);
/* 251 */     mdim.comment = "The dimension considered to be your 'overworld'. Certain TC structures will only spawn in this dim.";
/* 252 */     overworldDim = mdim.getInt();
/*     */     
/*     */ 
/* 255 */     int encIdx = 150;
/*     */     
/* 257 */     Property enchHas = config.get("Enchantments", "ench_haste", encIdx++);
/* 258 */     enchHaste = new EnchantmentHaste(enchHas.getInt(), 3);
/* 259 */     thaumcraft.api.ThaumcraftEnchantments.HASTE = enchHas.getInt();
/* 260 */     Enchantment.addToBookList(enchHaste);
/*     */     
/* 262 */     Property enchRep = config.get("Enchantments", "ench_repair", encIdx++);
/* 263 */     enchRepair = new EnchantmentRepair(enchRep.getInt(), 2);
/* 264 */     thaumcraft.api.ThaumcraftEnchantments.REPAIR = enchRep.getInt();
/* 265 */     Enchantment.addToBookList(enchRepair);
/*     */     
/*     */ 
/* 268 */     config.save();
/*     */   }
/*     */   
/*     */   public static void save() {
/* 272 */     config.save();
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void initPotions()
/*     */   {
/* 304 */     PotionFluxTaint.instance = new PotionFluxTaint(true, 6697847);
/* 305 */     PotionFluxTaint.init();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 312 */     PotionVisExhaust.instance = new PotionVisExhaust(true, 6702199);
/* 313 */     PotionVisExhaust.init();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 320 */     PotionInfectiousVisExhaust.instance = new PotionInfectiousVisExhaust(true, 6706551);
/* 321 */     PotionInfectiousVisExhaust.init();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 328 */     PotionUnnaturalHunger.instance = new PotionUnnaturalHunger(true, 4482611);
/* 329 */     PotionUnnaturalHunger.init();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 336 */     PotionWarpWard.instance = new PotionWarpWard(false, 14742263);
/* 337 */     PotionWarpWard.init();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 344 */     PotionDeathGaze.instance = new PotionDeathGaze(true, 6702131);
/* 345 */     PotionDeathGaze.init();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 352 */     PotionBlurredVision.instance = new PotionBlurredVision(true, 8421504);
/* 353 */     PotionBlurredVision.init();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 360 */     PotionSunScorned.instance = new PotionSunScorned(true, 16308330);
/* 361 */     PotionSunScorned.init();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 368 */     PotionThaumarhia.instance = new PotionThaumarhia(true, 6702199);
/* 369 */     PotionThaumarhia.init();
/*     */   }
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
/*     */   public static void syncConfigurable()
/*     */   {
/* 387 */     Property cbp = config.get("Graphics", "charge_bar_pos", chargeBarPos);
/* 388 */     cbp.comment = "The location of the HUD for item vis charge levels. 0 = left (default), 1 = right, 2 = top";
/* 389 */     chargeBarPos = MathHelper.func_76125_a(cbp.getInt(), 0, 2);
/*     */     
/* 391 */     Property cb = config.get("Graphics", "color_blind", colorBlind);
/* 392 */     cb.comment = "Setting this to true will make certain colors higher contrast or darker to prevent them from being 'invisible' to color blind people.";
/* 393 */     colorBlind = cb.getBoolean(false);
/*     */     
/* 395 */     Property ltt = config.get("Graphics", "large_tag_text", largeTagText);
/* 396 */     ltt.comment = "Setting this to true will make the amount text in aspect tags twice as large. Useful for certain resolutions and custom fonts.";
/* 397 */     largeTagText = ltt.getBoolean(false);
/*     */     
/* 399 */     Property shad = config.get("Graphics", "shaders", shaders);
/* 400 */     shad.comment = "This setting will disable certain thaumcraft shaders for those who experience FPS drops.";
/* 401 */     shaders = shad.getBoolean(false);
/*     */     
/* 403 */     Property nost = config.get("Graphics", "no_stress", nostress);
/* 404 */     nost.comment = "Set to true to disable anxiety triggers like the heartbeat sound.";
/* 405 */     nostress = nost.getBoolean(false);
/*     */     
/* 407 */     Property ocd = config.get("Graphics", "crooked", crooked);
/* 408 */     ocd.comment = "Hate crooked labels, kittens, puppies and all things awesome? If yes, set this to false.";
/* 409 */     crooked = ocd.getBoolean(true);
/*     */     
/* 411 */     Property dbp = config.get("Graphics", "wand_dial_bottom", dialBottom);
/* 412 */     dbp.comment = "Set to true to have the wand dial display in the bottom left instead of the top left.";
/* 413 */     dialBottom = dbp.getBoolean(false);
/*     */     
/* 415 */     Property showtags = config.get("Graphics", "display_aspects", false);
/* 416 */     showtags.comment = "Item aspects are hidden by default and pressing shift reveals them. Changing this setting to 'true' will reverse this behaviour and always display aspects unless shift is pressed.";
/* 417 */     showTags = showtags.getBoolean(false);
/*     */     
/* 419 */     Property blueb = config.get("Graphics", "blue_magical_forest", blueBiome);
/* 420 */     blueb.comment = "Set this to true to get the old blue magical forest back.";
/* 421 */     blueBiome = blueb.getBoolean(false);
/*     */     
/* 423 */     Property sge = config.get("Graphics", "show_golem_emotes", showGolemEmotes);
/* 424 */     sge.comment = "Will golems display emote particles if they recieve orders or encounter problems";
/* 425 */     showGolemEmotes = sge.getBoolean(true);
/*     */     
/*     */ 
/*     */ 
/* 429 */     genAura = config.get("World_Generation", "generate_aura_nodes", true).getBoolean(true);
/* 430 */     genStructure = config.get("World_Generation", "generate_structures", true).getBoolean(true);
/* 431 */     genCinnibar = config.get("World_Generation", "generate_cinnibar_ore", true).getBoolean(true);
/* 432 */     genAmber = config.get("World_Generation", "generate_amber_ore", true).getBoolean(true);
/* 433 */     genCrystals = config.get("World_Generation", "generate_vis_crystals", true).getBoolean(true);
/* 434 */     genTrees = config.get("World_Generation", "generate_trees", true).getBoolean(true);
/* 435 */     Property gt = config.get("World_Generation", "generate_taint", genTaint);
/* 436 */     gt.comment = "Can taint biomes generate at worldgen";
/* 437 */     genTaint = gt.getBoolean(true);
/*     */     
/* 439 */     Property aurasize = config.get("World_Generation", "aura_base", AURABASE);
/* 440 */     aurasize.comment = "The default base value of an aura that is used to generate the aura in each chunk. Default value is 100.";
/* 441 */     CAURABASE = AURABASE = aurasize.getInt();
/*     */     
/* 443 */     Property nodRare = config.get("World_Generation", "node_rarity", nodeRarity);
/* 444 */     nodRare.comment = "How rare nodes are in the world. The number means there will be (on average) one node per N chunks.";
/* 445 */     nodeRarity = nodRare.getInt();
/*     */     
/* 447 */     Property nodSpec = config.get("World_Generation", "special_node_rarity", specialNodeRarity);
/* 448 */     nodSpec.comment = "The chance of a node being special (pure, dark, unstable, etc.). The number means roughly 1 in N nodes will be special, so setting the number to 5 will mean 1 in 5 nodes may be special.";
/* 449 */     specialNodeRarity = nodSpec.getInt();
/* 450 */     if (specialNodeRarity < 3) { specialNodeRarity = 3;
/*     */     }
/*     */     
/* 453 */     Property regKey = config.get("World_Regeneration", "regen_key", "DEFAULT");
/* 454 */     regKey.comment = "This key is used to keep track of which chunk have been generated/regenerated. Changing it will cause the regeneration code to run again, so only change it if you want it to happen. Useful to regen only one world feature at a time.";
/* 455 */     regenKey = regKey.getString();
/* 456 */     regenAura = config.get("World_Regeneration", "aura_nodes", false).getBoolean(false);
/* 457 */     regenStructure = config.get("World_Regeneration", "structures", false).getBoolean(false);
/* 458 */     regenCinnibar = config.get("World_Regeneration", "cinnibar_ore", false).getBoolean(false);
/* 459 */     regenAmber = config.get("World_Regeneration", "amber_ore", false).getBoolean(false);
/* 460 */     regenCrystals = config.get("World_Regeneration", "vis_crystals", false).getBoolean(false);
/* 461 */     regenTrees = config.get("World_Regeneration", "trees", false).getBoolean(false);
/* 462 */     regenTaint = config.get("World_Regeneration", "taint", false).getBoolean(false);
/*     */     
/*     */ 
/* 465 */     Property resDif = config.get("Research", "research_difficulty", 0);
/* 466 */     resDif.comment = "0 = normal, -1 = easy (all research items are directly purchased with levels), 1 = Hard (all research items need to be solved via the research table)";
/* 467 */     CresearchDifficulty = researchDifficulty = resDif.getInt(0);
/* 468 */     Property resAmt = config.get("Research", "research_amount", 1);
/* 469 */     resAmt.comment = "This setting is useful for cooperative or team play. When a research is completed this is the amount of discoveries that will be created (default 1, max 64) Setting it less than 1 will create a discovery that will not be used up when learned.";
/* 470 */     if (resAmt.getInt(0) > 64) resAmt.set(64);
/* 471 */     if (resAmt.getInt(0) < 0) resAmt.set(0);
/* 472 */     CresearchAmount = researchAmount = resAmt.getInt(1);
/*     */     
/*     */ 
/* 475 */     spawnAngryZombie = config.get("Monster_Spawning", "spawn_angry_zombies", true).getBoolean(true);
/* 476 */     spawnFireBat = config.get("Monster_Spawning", "spawn_fire_bats", true).getBoolean(true);
/* 477 */     spawnWisp = config.get("Monster_Spawning", "spawn_wisps", true).getBoolean(true);
/* 478 */     spawnTaintacle = config.get("Monster_Spawning", "spawn_taintacles", true).getBoolean(true);
/* 479 */     spawnTaintSpore = config.get("Monster_Spawning", "spawn_taint_spores", true).getBoolean(true);
/* 480 */     spawnPech = config.get("Monster_Spawning", "spawn_pechs", true).getBoolean(true);
/* 481 */     spawnElder = config.get("Monster_Spawning", "spawn_eldercreatures", true).getBoolean(true);
/*     */     
/* 483 */     Property cm = config.get("Monster_Spawning", "champion_mobs", championMobs);
/* 484 */     cm.comment = "Setting this to false will disable spawning champion mobs. Even when false they will still have a greatly reduced chance of spawning in certain dangerous places.";
/* 485 */     championMobs = cm.getBoolean(true);
/*     */     
/* 487 */     Property am = config.get("general", "allow_mirrors", allowMirrors);
/* 488 */     am.comment = "Setting this to false will disable arcane mirror research and crafting recipes.";
/* 489 */     CallowMirrors = allowMirrors = am.getBoolean(true);
/*     */     
/* 491 */     Property wm = config.get("general", "wuss_mode", wuss);
/* 492 */     wm.comment = "Setting this to true disables Warp, Taint spread and similar mechanics. You wuss.";
/* 493 */     Cwuss = wuss = wm.getBoolean(false);
/*     */     
/*     */ 
/* 496 */     Property cheatsheet = config.get("general", "allow_cheat_sheet", false);
/* 497 */     cheatsheet.comment = "Enables a version of the Thauminomicon in creative mode that grants you all the research when you first use it.";
/* 498 */     CallowCheatSheet = allowCheatSheet = cheatsheet.getBoolean(false);
/*     */     
/* 500 */     Property wardstone = config.get("general", "allow_warded_stone", true);
/* 501 */     wardstone.comment = "If set to false, warded stone, doors and glass will just be cosmetic in nature and not have its hardened properties (everyone will be able to break it with equal ease).";
/* 502 */     CwardedStone = wardedStone = wardstone.getBoolean(false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 536 */     Property ts = config.get("general", "taint_spread", (int)(taintSpreadRate * 100.0F));
/* 537 */     ts.comment = "The % chance of taint spread costing flux from the aura.";
/* 538 */     taintSpreadRate = ts.getInt() / 100.0F;
/*     */     
/* 540 */     Property od = config.get("World_Generation", "ore_density", oreDensity);
/* 541 */     od.comment = "The % of normal ore amounts that will be spawned. For example 50 will spawn half the ores while 200 will spawn double. Default 100";
/* 542 */     oreDensity = Math.max(1, od.getInt());
/*     */     
/* 544 */     Property dd = config.get("World_Generation", "dust_degrade", dustDegrade);
/* 545 */     dd.comment = "The 1 in N chance of degraded taint dust levels lowering each block update. In other words - if you want to have dust go away faster set this lower. Default 25";
/* 546 */     dustDegrade = Math.max(1, dd.getInt());
/*     */     
/* 548 */     Property std = config.get("general", "tainted_soil_to_dirt", false);
/* 549 */     std.comment = "If set to true, tainted soil will turn into dirt, not degraded dust. Default false.";
/* 550 */     soilToDirt = std.getBoolean(false);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 555 */     Property rss = config.get("Runic_Shielding", "runic_shield_recharge_speed", shieldRecharge);
/* 556 */     rss.comment = "How many milliseconds passes between runic shielding recharge ticks. Lower values equals faster recharge. Minimum of 500.";
/* 557 */     shieldRecharge = Math.max(500, rss.getInt());
/* 558 */     Property rsd = config.get("Runic_Shielding", "runic_shield_recharge_delay", shieldWait);
/* 559 */     rsd.comment = "How many milliseconds passes after a shield has been reduced to zero before it can start recharging again. Minimum of 0.";
/* 560 */     shieldWait = Math.max(0, rsd.getInt());
/* 561 */     Property rsc = config.get("Runic_Shielding", "runic_shield_cost", shieldCost);
/* 562 */     rsc.comment = "How much aer and terra vis it costs to reacharge a single unit of shielding. Minimum of 0.";
/* 563 */     shieldCost = Math.max(0, rsc.getInt());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 568 */   public static Enchantment enchHaste = null;
/* 569 */   public static Enchantment enchRepair = null;
/*     */   
/*     */   public static void initLoot()
/*     */   {
/* 573 */     Random rand = new Random(System.currentTimeMillis());
/*     */     
/* 575 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.coin, 1), 2500, new int[] { 0 });
/* 576 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.coin, 2), 2250, new int[] { 1 });
/* 577 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.coin, 3), 2000, new int[] { 2 });
/* 578 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.primordialPearl), 1, new int[] { 2 });
/* 579 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151156_bN), 1, new int[] { 2 });
/* 580 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151045_i), 10, new int[] { 0 });
/* 581 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151045_i), 50, new int[] { 1, 2 });
/* 582 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151166_bC), 15, new int[] { 0 });
/* 583 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151166_bC), 75, new int[] { 1, 2 });
/* 584 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151043_k), 100, new int[] { 0, 1, 2 });
/* 585 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151079_bi), 100, new int[] { 0, 1, 2 });
/* 586 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.knowledgeFragment), 25, new int[] { 0, 1, 2 });
/* 587 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 0), 10, new int[] { 0 });
/* 588 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 1), 10, new int[] { 0 });
/* 589 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 2), 10, new int[] { 0 });
/* 590 */     for (int a = 3; a <= 8; a++) {
/* 591 */       ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, a), 5, new int[] { 1 });
/* 592 */       ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, a), 7, new int[] { 2 });
/*     */     }
/* 594 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.amuletVis, 1, 0), 6, new int[] { 1, 2 });
/* 595 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.ringRunic, 1, 0), 5, new int[] { 1, 2 });
/* 596 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 5, new int[] { 0 });
/* 597 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 10, new int[] { 1 });
/* 598 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 20, new int[] { 2 });
/* 599 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 1, new int[] { 0 });
/* 600 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 2, new int[] { 1 });
/* 601 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 3, new int[] { 2 });
/* 602 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 3, new int[] { 0 });
/* 603 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 6, new int[] { 1 });
/* 604 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 9, new int[] { 2 });
/* 605 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151122_aG), 10, new int[] { 0, 1, 2 });
/*     */     
/* 607 */     for (int i = 0; i <= 15; i++) {
/* 608 */       for (int j = 0; j <= 1; j++) {
/*     */         int k;
/*     */         int k;
/* 611 */         if (j == 0) k = i | 0x2000; else k = i | 0x4000;
/* 612 */         for (int l = 0; l <= 2; l++)
/*     */         {
/* 614 */           int i1 = k;
/* 615 */           if (l != 0) if (l == 1) i1 = k | 0x20; else if (l == 2) i1 = k | 0x40;
/* 616 */           List list1 = PotionHelper.func_77917_b(i1, false);
/* 617 */           if ((list1 != null) && (!list1.isEmpty()))
/*     */           {
/* 619 */             ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151068_bn, 1, i1), l + 1, new int[] { 0, 1, 2 });
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 625 */     ItemStack[] commonLoot = { new ItemStack(ItemsTC.lootBag, 1, 0), new ItemStack(ItemsTC.ingots), new ItemStack(ItemsTC.amber) };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 630 */     ItemStack[] uncommonLoot = { new ItemStack(ItemsTC.lootBag, 1, 1), new ItemStack(ItemsTC.baubles, 1, 0), new ItemStack(ItemsTC.baubles, 1, 1), new ItemStack(ItemsTC.baubles, 1, 2), new ItemStack(ItemsTC.knowledgeFragment) };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 637 */     ItemStack[] rareLoot = { new ItemStack(ItemsTC.lootBag, 1, 2), new ItemStack(ItemsTC.thaumonomicon), new ItemStack(ItemsTC.thaumiumSword), new ItemStack(ItemsTC.thaumiumAxe), new ItemStack(ItemsTC.thaumiumHoe), new ItemStack(ItemsTC.thaumiumPick), new ItemStack(ItemsTC.ringRunic), new ItemStack(ItemsTC.baubles, 1, 3), new ItemStack(ItemsTC.baubles, 1, 4), new ItemStack(ItemsTC.baubles, 1, 5), new ItemStack(ItemsTC.baubles, 1, 6), new ItemStack(ItemsTC.baubles, 1, 7), new ItemStack(ItemsTC.baubles, 1, 8), new ItemStack(ItemsTC.amuletVis, 1, 0) };
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
/* 653 */     for (ItemStack is : commonLoot) {
/* 654 */       ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(is, 1, 3, 5));
/* 655 */       ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(is, 1, 3, 5));
/* 656 */       ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(is, 1, 3, 5));
/* 657 */       ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(is, 1, 3, 4));
/* 658 */       ChestGenHooks.addItem("strongholdCorridor", new WeightedRandomChestContent(is, 1, 3, 4));
/* 659 */       ChestGenHooks.addItem("strongholdCrossing", new WeightedRandomChestContent(is, 1, 3, 4));
/* 660 */       ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(is, 1, 3, 4));
/*     */     }
/*     */     
/* 663 */     for (ItemStack is : uncommonLoot) {
/* 664 */       ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(is, 1, 2, 4));
/* 665 */       ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(is, 1, 2, 4));
/* 666 */       ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(is, 1, 2, 4));
/* 667 */       ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(is, 1, 2, 3));
/* 668 */       ChestGenHooks.addItem("strongholdCorridor", new WeightedRandomChestContent(is, 1, 2, 3));
/* 669 */       ChestGenHooks.addItem("strongholdCrossing", new WeightedRandomChestContent(is, 1, 2, 3));
/* 670 */       ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(is, 1, 2, 3));
/*     */     }
/*     */     
/*     */ 
/* 674 */     ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(new ItemStack(ItemsTC.knowledgeFragment), 3, 6, 20));
/*     */     
/* 676 */     for (ItemStack is : rareLoot) {
/* 677 */       ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(is, 1, 1, 1));
/* 678 */       ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(is, 1, 1, 1));
/* 679 */       ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(is, 1, 1, 1));
/* 680 */       ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(is, 1, 1, 1));
/* 681 */       ChestGenHooks.addItem("strongholdCorridor", new WeightedRandomChestContent(is, 1, 1, 1));
/* 682 */       ChestGenHooks.addItem("strongholdCrossing", new WeightedRandomChestContent(is, 1, 1, 1));
/* 683 */       ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(is, 1, 1, 1));
/*     */     }
/*     */     
/* 686 */     ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(new ItemStack(ItemsTC.ingots), 1, 3, 10));
/*     */   }
/*     */   
/*     */   public static void initModCompatibility()
/*     */   {
/* 691 */     String[] ores = OreDictionary.getOreNames();
/*     */     boolean first;
/* 693 */     for (String ore : ores)
/* 694 */       if (ore != null)
/*     */       {
/* 696 */         if ((ore.equals("oreCopper")) && (OreDictionary.getOres(ore).size() > 0))
/*     */         {
/* 698 */           foundCopperOre = true;
/* 699 */           for (ItemStack is : OreDictionary.getOres(ore))
/* 700 */             Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 2), 1.0F);
/*     */         }
/* 702 */         if ((ore.equals("oreTin")) && (OreDictionary.getOres(ore).size() > 0))
/*     */         {
/* 704 */           foundTinOre = true;
/* 705 */           for (ItemStack is : OreDictionary.getOres(ore))
/* 706 */             Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 3), 1.0F);
/*     */         }
/* 708 */         if ((ore.equals("oreSilver")) && (OreDictionary.getOres(ore).size() > 0))
/*     */         {
/* 710 */           foundSilverOre = true;
/* 711 */           for (ItemStack is : OreDictionary.getOres(ore))
/* 712 */             Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 4), 1.0F);
/*     */         }
/* 714 */         if ((ore.equals("oreLead")) && (OreDictionary.getOres(ore).size() > 0))
/*     */         {
/* 716 */           foundLeadOre = true;
/* 717 */           for (ItemStack is : OreDictionary.getOres(ore))
/* 718 */             Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 5), 1.0F);
/*     */         }
/*     */         boolean first;
/* 721 */         if (ore.equals("ingotCopper")) {
/* 722 */           first = true;
/* 723 */           for (ItemStack is : OreDictionary.getOres(ore)) {
/* 724 */             if (is.field_77994_a > 1) is.field_77994_a = 1;
/* 725 */             foundCopperIngot = true;
/* 726 */             CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 1), new Object[] { "#", Character.valueOf('#'), is });
/*     */             
/* 728 */             if (first) {
/* 729 */               first = false;
/* 730 */               FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ItemsTC.clusters, 1, 2), new ItemStack(is.func_77973_b(), 2, is.func_77952_i()), 1.0F);
/*     */               
/* 732 */               ConfigRecipes.oreDictRecipe(is, new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 1) });
/*     */             }
/*     */           }
/*     */         } else {
/*     */           boolean first;
/* 737 */           if (ore.equals("ingotTin")) {
/* 738 */             first = true;
/* 739 */             for (ItemStack is : OreDictionary.getOres(ore)) {
/* 740 */               if (is.field_77994_a > 1) is.field_77994_a = 1;
/* 741 */               foundTinIngot = true;
/* 742 */               CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 2), new Object[] { "#", Character.valueOf('#'), is });
/* 743 */               if (first) {
/* 744 */                 first = false;
/* 745 */                 FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ItemsTC.clusters, 1, 3), new ItemStack(is.func_77973_b(), 2, is.func_77952_i()), 1.0F);
/*     */                 
/* 747 */                 ConfigRecipes.oreDictRecipe(is, new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 2) });
/*     */               }
/*     */             }
/*     */           } else {
/*     */             boolean first;
/* 752 */             if (ore.equals("ingotSilver")) {
/* 753 */               first = true;
/* 754 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/* 755 */                 if (is.field_77994_a > 1) is.field_77994_a = 1;
/* 756 */                 foundSilverIngot = true;
/* 757 */                 CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 3), new Object[] { "#", Character.valueOf('#'), is });
/* 758 */                 if (first) {
/* 759 */                   first = false;
/* 760 */                   FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ItemsTC.clusters, 1, 4), new ItemStack(is.func_77973_b(), 2, is.func_77952_i()), 1.0F);
/*     */                   
/* 762 */                   ConfigRecipes.oreDictRecipe(is, new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 3) });
/*     */                 }
/*     */               }
/*     */             }
/* 766 */             else if ((ore.equals("oreUranium")) || (ore.equals("itemDropUranium")) || (ore.equals("ingotUranium"))) {
/* 767 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/* 768 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 2).add(Aspect.DEATH, 1).add(Aspect.ENERGY, 2));
/*     */               }
/* 770 */             } else if ((ore.equals("ingotBrass")) || (ore.equals("ingotBronze"))) {
/* 771 */               for (ItemStack is : OreDictionary.getOres(ore))
/* 772 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 3).add(Aspect.TOOL, 1));
/* 773 */             } else if ((ore.equals("dustBrass")) || (ore.equals("dustBronze"))) {
/* 774 */               for (ItemStack is : OreDictionary.getOres(ore))
/* 775 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.TOOL, 1));
/* 776 */             } else if ((ore.equals("gemRuby")) || (ore.equals("gemGreenSapphire")) || (ore.equals("gemSapphire"))) {
/* 777 */               for (ItemStack is : OreDictionary.getOres(ore))
/* 778 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.DESIRE, 2));
/* 779 */             } else if (ore.equals("woodRubber")) {
/* 780 */               for (ItemStack is : OreDictionary.getOres(ore))
/* 781 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.PLANT, 3).add(Aspect.TOOL, 1));
/* 782 */             } else if (ore.equals("itemRubber")) {
/* 783 */               for (ItemStack is : OreDictionary.getOres(ore))
/* 784 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.MOTION, 2).add(Aspect.TOOL, 2));
/* 785 */             } else if (ore.equals("ingotSteel")) {
/* 786 */               for (ItemStack is : OreDictionary.getOres(ore))
/* 787 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 1));
/* 788 */             } else if (ore.equals("crystalQuartz")) {
/* 789 */               for (ItemStack is : OreDictionary.getOres(ore))
/* 790 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.ENERGY, 1));
/* 791 */             } else if (ore.equals("ingotLead")) {
/* 792 */               first = true;
/* 793 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/* 794 */                 if (is.field_77994_a > 1)
/* 795 */                   is.field_77994_a = 1;
/* 796 */                 foundLeadIngot = true;
/* 797 */                 CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 4), new Object[] { "#", Character.valueOf('#'), is });
/* 798 */                 if (first) {
/* 799 */                   first = false;
/* 800 */                   FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ItemsTC.clusters, 1, 5), new ItemStack(is.func_77973_b(), 2, is.func_77952_i()), 1.0F);
/*     */                   
/* 802 */                   ConfigRecipes.oreDictRecipe(is, new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 4) });
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 809 */     Thaumcraft.log.info("Adding entities to MFR safari net blacklist.");
/* 810 */     registerSafariNetBlacklist(EntityOwnedConstruct.class);
/* 811 */     registerSafariNetBlacklist(EntityFallingTaint.class);
/* 812 */     registerSafariNetBlacklist(EntityWisp.class);
/* 813 */     registerSafariNetBlacklist(EntityPech.class);
/* 814 */     registerSafariNetBlacklist(EntityEldritchGuardian.class);
/* 815 */     registerSafariNetBlacklist(EntityEldritchWarden.class);
/* 816 */     registerSafariNetBlacklist(EntityEldritchGolem.class);
/* 817 */     registerSafariNetBlacklist(EntityCultistCleric.class);
/* 818 */     registerSafariNetBlacklist(EntityCultistKnight.class);
/* 819 */     registerSafariNetBlacklist(EntityCultistLeader.class);
/* 820 */     registerSafariNetBlacklist(EntityCultistPortalGreater.class);
/* 821 */     registerSafariNetBlacklist(EntityCultistPortalLesser.class);
/* 822 */     registerSafariNetBlacklist(EntityEldritchCrab.class);
/* 823 */     registerSafariNetBlacklist(EntityInhabitedZombie.class);
/*     */   }
/*     */   
/*     */   public static void registerSafariNetBlacklist(Class<?> blacklistedEntity)
/*     */   {
/*     */     try {
/* 829 */       Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
/* 830 */       if (registry != null) {
/* 831 */         Method reg = registry.getMethod("registerSafariNetBlacklist", new Class[] { Class.class });
/* 832 */         reg.invoke(registry, new Object[] { blacklistedEntity });
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public static void initMisc()
/*     */   {
/* 840 */     for (Item item : GameData.getItemRegistry().typeSafeIterable()) {
/* 841 */       if ((item != null) && ((item instanceof IPlantable))) {
/* 842 */         IBlockState bs = ((IPlantable)item).getPlant(null, null);
/* 843 */         if (bs != null) {
/* 844 */           ThaumcraftApi.registerSeed(bs.func_177230_c(), new ItemStack(item));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 849 */     CropUtils.addStandardCrop(Blocks.field_150440_ba, 32767);
/* 850 */     CropUtils.addStandardCrop(Blocks.field_150423_aK, 32767);
/* 851 */     CropUtils.addStackedCrop(Blocks.field_150436_aH, 32767);
/* 852 */     CropUtils.addStackedCrop(Blocks.field_150434_aF, 32767);
/* 853 */     CropUtils.addStandardCrop(Blocks.field_150388_bm, 3);
/* 854 */     ThaumcraftApi.registerSeed(Blocks.field_150375_by, new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BROWN.func_176767_b()));
/*     */     
/* 856 */     Utils.addSpecialMiningResult(new ItemStack(Blocks.field_150366_p), new ItemStack(ItemsTC.clusters, 1, 0), 1.0F);
/* 857 */     Utils.addSpecialMiningResult(new ItemStack(Blocks.field_150352_o), new ItemStack(ItemsTC.clusters, 1, 1), 1.0F);
/* 858 */     Utils.addSpecialMiningResult(new ItemStack(BlocksTC.oreCinnabar), new ItemStack(ItemsTC.clusters, 1, 6), 1.0F);
/*     */     
/* 860 */     Collection<Aspect> pa = Aspect.aspects.values();
/* 861 */     for (Aspect aspect : pa) {
/* 862 */       aspectOrder.add(aspect);
/*     */     }
/*     */   }
/*     */   
/* 866 */   public static ArrayList<Aspect> aspectOrder = new ArrayList();
/*     */   
/*     */ 
/* 869 */   public static boolean foundCopperIngot = false;
/* 870 */   public static boolean foundTinIngot = false;
/* 871 */   public static boolean foundSilverIngot = false;
/* 872 */   public static boolean foundLeadIngot = false;
/* 873 */   public static boolean foundCopperOre = false;
/* 874 */   public static boolean foundTinOre = false;
/* 875 */   public static boolean foundSilverOre = false;
/* 876 */   public static boolean foundLeadOre = false;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/config/Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */