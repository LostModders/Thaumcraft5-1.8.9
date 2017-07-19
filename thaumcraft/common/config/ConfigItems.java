/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.construct.ItemTurretPlacer;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealBreaker;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealButcher;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealEmpty;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealFillAdvanced;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealGuard;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealGuardAdvanced;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealHandler;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealPickup;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealPickupAdvanced;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealProvide;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealUse;
/*     */ import thaumcraft.common.items.IVariantItems;
/*     */ import thaumcraft.common.items.ItemGenericVariants;
/*     */ import thaumcraft.common.items.armor.ItemCultistBoots;
/*     */ import thaumcraft.common.items.armor.ItemCultistLeaderArmor;
/*     */ import thaumcraft.common.items.armor.ItemCultistPlateArmor;
/*     */ import thaumcraft.common.items.armor.ItemCultistRobeArmor;
/*     */ import thaumcraft.common.items.armor.ItemFortressArmor;
/*     */ import thaumcraft.common.items.armor.ItemRobeArmor;
/*     */ import thaumcraft.common.items.armor.ItemThaumiumArmor;
/*     */ import thaumcraft.common.items.armor.ItemVoidArmor;
/*     */ import thaumcraft.common.items.armor.ItemVoidRobeArmor;
/*     */ import thaumcraft.common.items.baubles.ItemAmuletRunic;
/*     */ import thaumcraft.common.items.baubles.ItemAmuletVis;
/*     */ import thaumcraft.common.items.baubles.ItemBaubles;
/*     */ import thaumcraft.common.items.baubles.ItemGirdleHover;
/*     */ import thaumcraft.common.items.baubles.ItemRingVerdant;
/*     */ import thaumcraft.common.items.consumables.ItemBathSalts;
/*     */ import thaumcraft.common.items.consumables.ItemBottleTaint;
/*     */ import thaumcraft.common.items.consumables.ItemBucketDeath;
/*     */ import thaumcraft.common.items.consumables.ItemBucketPure;
/*     */ import thaumcraft.common.items.consumables.ItemChunksEdible;
/*     */ import thaumcraft.common.items.consumables.ItemCrimsonRites;
/*     */ import thaumcraft.common.items.consumables.ItemLootBag;
/*     */ import thaumcraft.common.items.consumables.ItemRunedTablet;
/*     */ import thaumcraft.common.items.consumables.ItemSanitySoap;
/*     */ import thaumcraft.common.items.consumables.ItemTripleMeatTreat;
/*     */ import thaumcraft.common.items.consumables.ItemZombieBrain;
/*     */ import thaumcraft.common.items.resources.ItemCrystal;
/*     */ import thaumcraft.common.items.resources.ItemLabel;
/*     */ import thaumcraft.common.items.resources.ItemPrimalCharm;
/*     */ import thaumcraft.common.items.resources.ItemResearchNotes;
/*     */ import thaumcraft.common.items.resources.ItemShard;
/*     */ import thaumcraft.common.items.resources.ItemTainted;
/*     */ import thaumcraft.common.items.tools.ItemCreativeFluxSponge;
/*     */ import thaumcraft.common.items.tools.ItemCrimsonBlade;
/*     */ import thaumcraft.common.items.tools.ItemElementalAxe;
/*     */ import thaumcraft.common.items.tools.ItemElementalHoe;
/*     */ import thaumcraft.common.items.tools.ItemElementalPickaxe;
/*     */ import thaumcraft.common.items.tools.ItemHandMirror;
/*     */ import thaumcraft.common.items.tools.ItemPrimalCrusher;
/*     */ import thaumcraft.common.items.tools.ItemSanityChecker;
/*     */ import thaumcraft.common.items.tools.ItemScribingTools;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumAxe;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumHoe;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumPickaxe;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumSword;
/*     */ import thaumcraft.common.items.tools.ItemThaumometer;
/*     */ import thaumcraft.common.items.tools.ItemThaumonomicon;
/*     */ import thaumcraft.common.items.tools.ItemVoidAxe;
/*     */ import thaumcraft.common.items.tools.ItemVoidHoe;
/*     */ import thaumcraft.common.items.tools.ItemVoidPickaxe;
/*     */ import thaumcraft.common.items.tools.ItemVoidSword;
/*     */ import thaumcraft.common.items.wands.ItemFocusPouch;
/*     */ import thaumcraft.common.items.wands.WandRodPrimalOnUpdate;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusBuilder;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusFire;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusGrapple;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusPech;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusShard;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusShock;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusTrade;
/*     */ 
/*     */ public class ConfigItems
/*     */ {
/*     */   public static WandCap WAND_CAP_IRON;
/*     */   public static WandCap WAND_CAP_COPPER;
/*     */   public static WandCap WAND_CAP_GOLD;
/*     */   public static WandCap WAND_CAP_BRASS;
/*     */   public static WandCap WAND_CAP_SILVER;
/*     */   public static WandCap WAND_CAP_THAUMIUM;
/*     */   public static WandCap WAND_CAP_VOID;
/*     */   public static WandRod WAND_ROD_WOOD;
/*     */   public static WandRod WAND_ROD_GREATWOOD;
/*     */   public static WandRod WAND_ROD_SILVERWOOD;
/*     */   public static WandRod WAND_ROD_OBSIDIAN;
/*     */   public static WandRod WAND_ROD_BLAZE;
/*     */   public static WandRod WAND_ROD_ICE;
/*     */   public static WandRod WAND_ROD_QUARTZ;
/*     */   public static WandRod WAND_ROD_BONE;
/*     */   public static WandRod WAND_ROD_REED;
/*     */   public static WandRod STAFF_ROD_GREATWOOD;
/*     */   public static WandRod STAFF_ROD_SILVERWOOD;
/*     */   public static WandRod STAFF_ROD_OBSIDIAN;
/*     */   public static WandRod STAFF_ROD_BLAZE;
/*     */   public static WandRod STAFF_ROD_ICE;
/*     */   public static WandRod STAFF_ROD_QUARTZ;
/*     */   public static WandRod STAFF_ROD_BONE;
/*     */   public static WandRod STAFF_ROD_REED;
/*     */   public static WandRod STAFF_ROD_PRIMAL;
/*     */   
/*     */   public static void preInit()
/*     */   {
/* 124 */     initializeItems();
/*     */     
/*     */ 
/* 127 */     WAND_CAP_IRON = new WandCap("iron", 1.1F, 0, new ItemStack(ItemsTC.wandCaps, 1, 0), 1, new ResourceLocation("thaumcraft", "items/wand/cap_iron_mat"));
/* 128 */     WAND_CAP_GOLD = new WandCap("gold", 1.0F, 0, new ItemStack(ItemsTC.wandCaps, 1, 1), 3, new ResourceLocation("thaumcraft", "items/wand/cap_gold_mat"));
/* 129 */     WAND_CAP_BRASS = new WandCap("brass", 1.0F, 1, new ItemStack(ItemsTC.wandCaps, 1, 2), 3, new ResourceLocation("thaumcraft", "items/wand/cap_brass_mat"));
/* 130 */     WAND_CAP_THAUMIUM = new WandCap("thaumium", 0.9F, 0, new ItemStack(ItemsTC.wandCaps, 1, 4), 6, new ResourceLocation("thaumcraft", "items/wand/cap_thaumium_mat"));
/* 131 */     WAND_CAP_VOID = new WandCap("void", 1.0F, 3, new ItemStack(ItemsTC.wandCaps, 1, 6), 9, new ResourceLocation("thaumcraft", "items/wand/cap_void_mat"));
/*     */     
/* 133 */     WAND_ROD_WOOD = new WandRod("wood", 100, new ItemStack(Items.field_151055_y), 1, new ResourceLocation("thaumcraft", "items/wand/rod_wood_mat"));
/* 134 */     WAND_ROD_GREATWOOD = new WandRod("greatwood", 250, new ItemStack(ItemsTC.wandRods, 1, 0), 3, new ResourceLocation("thaumcraft", "items/wand/rod_greatwood_mat"));
/* 135 */     WAND_ROD_SILVERWOOD = new WandRod("silverwood", 500, new ItemStack(ItemsTC.wandRods, 1, 1), 9, new ResourceLocation("thaumcraft", "items/wand/rod_silverwood_mat"));
/* 136 */     WAND_ROD_OBSIDIAN = new WandRod("obsidian", 375, new ItemStack(ItemsTC.wandRods, 1, 2), 6, new WandRodPrimalOnUpdate(Aspect.EARTH), new ResourceLocation("thaumcraft", "items/wand/rod_obsidian_mat"));
/* 137 */     WAND_ROD_BLAZE = new WandRod("blaze", 375, new ItemStack(ItemsTC.wandRods, 1, 3), 6, new WandRodPrimalOnUpdate(Aspect.FIRE), new ResourceLocation("thaumcraft", "items/wand/rod_blaze_mat"));
/* 138 */     WAND_ROD_ICE = new WandRod("ice", 375, new ItemStack(ItemsTC.wandRods, 1, 4), 6, new WandRodPrimalOnUpdate(Aspect.WATER), new ResourceLocation("thaumcraft", "items/wand/rod_ice_mat"));
/* 139 */     WAND_ROD_QUARTZ = new WandRod("quartz", 375, new ItemStack(ItemsTC.wandRods, 1, 5), 6, new WandRodPrimalOnUpdate(Aspect.ORDER), new ResourceLocation("thaumcraft", "items/wand/rod_quartz_mat"));
/* 140 */     WAND_ROD_BONE = new WandRod("bone", 375, new ItemStack(ItemsTC.wandRods, 1, 6), 6, new WandRodPrimalOnUpdate(Aspect.ENTROPY), new ResourceLocation("thaumcraft", "items/wand/rod_bone_mat"));
/* 141 */     WAND_ROD_REED = new WandRod("reed", 375, new ItemStack(ItemsTC.wandRods, 1, 7), 6, new WandRodPrimalOnUpdate(Aspect.AIR), new ResourceLocation("thaumcraft", "items/wand/rod_reed_mat"));
/*     */     
/* 143 */     STAFF_ROD_GREATWOOD = new WandRod("greatwood_staff", 375, new ItemStack(ItemsTC.wandRods, 1, 8), 8, new ResourceLocation("thaumcraft", "items/wand/rod_greatwood_mat"));
/* 144 */     STAFF_ROD_SILVERWOOD = new WandRod("silverwood_staff", 750, new ItemStack(ItemsTC.wandRods, 1, 9), 24, new ResourceLocation("thaumcraft", "items/wand/rod_silverwood_mat"));
/* 145 */     STAFF_ROD_OBSIDIAN = new WandRod("obsidian_staff", 500, new ItemStack(ItemsTC.wandRods, 1, 10), 14, new WandRodPrimalOnUpdate(Aspect.EARTH), new ResourceLocation("thaumcraft", "items/wand/rod_obsidian_mat"));
/* 146 */     STAFF_ROD_BLAZE = new WandRod("blaze_staff", 500, new ItemStack(ItemsTC.wandRods, 1, 11), 14, new WandRodPrimalOnUpdate(Aspect.FIRE), new ResourceLocation("thaumcraft", "items/wand/rod_blaze_mat"));
/* 147 */     STAFF_ROD_ICE = new WandRod("ice_staff", 500, new ItemStack(ItemsTC.wandRods, 1, 12), 14, new WandRodPrimalOnUpdate(Aspect.WATER), new ResourceLocation("thaumcraft", "items/wand/rod_ice_mat"));
/* 148 */     STAFF_ROD_QUARTZ = new WandRod("quartz_staff", 500, new ItemStack(ItemsTC.wandRods, 1, 13), 14, new WandRodPrimalOnUpdate(Aspect.ORDER), new ResourceLocation("thaumcraft", "items/wand/rod_quartz_mat"));
/* 149 */     STAFF_ROD_BONE = new WandRod("bone_staff", 500, new ItemStack(ItemsTC.wandRods, 1, 14), 14, new WandRodPrimalOnUpdate(Aspect.ENTROPY), new ResourceLocation("thaumcraft", "items/wand/rod_bone_mat"));
/* 150 */     STAFF_ROD_REED = new WandRod("reed_staff", 500, new ItemStack(ItemsTC.wandRods, 1, 15), 14, new WandRodPrimalOnUpdate(Aspect.AIR), new ResourceLocation("thaumcraft", "items/wand/rod_reed_mat"));
/* 151 */     STAFF_ROD_GREATWOOD.setStaff(true);
/* 152 */     STAFF_ROD_SILVERWOOD.setStaff(true);
/* 153 */     STAFF_ROD_OBSIDIAN.setStaff(true);
/* 154 */     STAFF_ROD_BLAZE.setStaff(true);
/* 155 */     STAFF_ROD_ICE.setStaff(true);
/* 156 */     STAFF_ROD_QUARTZ.setStaff(true);
/* 157 */     STAFF_ROD_BONE.setStaff(true);
/* 158 */     STAFF_ROD_REED.setStaff(true);
/*     */     
/* 160 */     STAFF_ROD_PRIMAL = new WandRod("primal_staff", 750, new ItemStack(ItemsTC.wandRods, 1, 16), 28, new WandRodPrimalOnUpdate(), new ResourceLocation("thaumcraft", "items/wand/rod_primal_mat"));
/* 161 */     STAFF_ROD_PRIMAL.setStaff(true);
/* 162 */     STAFF_ROD_PRIMAL.setPotencyBonus(true);
/*     */   }
/*     */   
/*     */   public static void init() {
/* 166 */     ItemsTC.seals = registerItem(new thaumcraft.common.entities.construct.golem.seals.ItemSealPlacer(), "seal", true);
/*     */   }
/*     */   
/*     */ 
/*     */   public static void postInit() {}
/*     */   
/*     */   public static void initSeals()
/*     */   {
/* 174 */     SealHandler.registerSeal(new SealPickup());
/* 175 */     SealHandler.registerSeal(new SealPickupAdvanced());
/* 176 */     SealHandler.registerSeal(new thaumcraft.common.entities.construct.golem.seals.SealFill());
/* 177 */     SealHandler.registerSeal(new SealFillAdvanced());
/* 178 */     SealHandler.registerSeal(new SealEmpty());
/* 179 */     SealHandler.registerSeal(new thaumcraft.common.entities.construct.golem.seals.SealEmptyAdvanced());
/* 180 */     SealHandler.registerSeal(new thaumcraft.common.entities.construct.golem.seals.SealHarvest());
/* 181 */     SealHandler.registerSeal(new SealButcher());
/* 182 */     SealHandler.registerSeal(new SealGuard());
/* 183 */     SealHandler.registerSeal(new SealGuardAdvanced());
/* 184 */     SealHandler.registerSeal(new thaumcraft.common.entities.construct.golem.seals.SealLumber());
/* 185 */     SealHandler.registerSeal(new SealBreaker());
/* 186 */     SealHandler.registerSeal(new SealUse());
/* 187 */     SealHandler.registerSeal(new SealProvide());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void initializeItems()
/*     */   {
/* 194 */     ItemsTC.thaumonomicon = registerItem(new ItemThaumonomicon(), "thaumonomicon", true);
/* 195 */     ItemsTC.thaumometer = registerItem(new ItemThaumometer(), "thaumometer", true);
/* 196 */     ItemsTC.resonator = registerItem(new thaumcraft.common.items.tools.ItemResonator(), "resonator", true);
/* 197 */     ItemsTC.sinisterStone = registerItem(new thaumcraft.common.items.tools.ItemSinisterStone(), "sinister_stone", true);
/* 198 */     ItemsTC.sanityChecker = registerItem(new ItemSanityChecker(), "sanity_checker", true);
/* 199 */     ItemsTC.handMirror = registerItem(new ItemHandMirror(), "hand_mirror", true);
/*     */     
/* 201 */     ItemsTC.thaumiumAxe = registerItem(new ItemThaumiumAxe(ThaumcraftMaterials.TOOLMAT_THAUMIUM), "thaumium_axe", true);
/* 202 */     ItemsTC.thaumiumSword = registerItem(new ItemThaumiumSword(ThaumcraftMaterials.TOOLMAT_THAUMIUM), "thaumium_sword", true);
/* 203 */     ItemsTC.thaumiumShovel = registerItem(new thaumcraft.common.items.tools.ItemThaumiumShovel(ThaumcraftMaterials.TOOLMAT_THAUMIUM), "thaumium_shovel", true);
/* 204 */     ItemsTC.thaumiumPick = registerItem(new ItemThaumiumPickaxe(ThaumcraftMaterials.TOOLMAT_THAUMIUM), "thaumium_pick", true);
/* 205 */     ItemsTC.thaumiumHoe = registerItem(new ItemThaumiumHoe(ThaumcraftMaterials.TOOLMAT_THAUMIUM), "thaumium_hoe", true);
/*     */     
/* 207 */     ItemsTC.voidAxe = registerItem(new ItemVoidAxe(ThaumcraftMaterials.TOOLMAT_VOID), "void_axe", true);
/* 208 */     ItemsTC.voidSword = registerItem(new ItemVoidSword(ThaumcraftMaterials.TOOLMAT_VOID), "void_sword", true);
/* 209 */     ItemsTC.voidShovel = registerItem(new thaumcraft.common.items.tools.ItemVoidShovel(ThaumcraftMaterials.TOOLMAT_VOID), "void_shovel", true);
/* 210 */     ItemsTC.voidPick = registerItem(new ItemVoidPickaxe(ThaumcraftMaterials.TOOLMAT_VOID), "void_pick", true);
/* 211 */     ItemsTC.voidHoe = registerItem(new ItemVoidHoe(ThaumcraftMaterials.TOOLMAT_VOID), "void_hoe", true);
/*     */     
/* 213 */     ItemsTC.elementalAxe = registerItem(new ItemElementalAxe(ThaumcraftMaterials.TOOLMAT_ELEMENTAL), "elemental_axe", true);
/* 214 */     ItemsTC.elementalSword = registerItem(new thaumcraft.common.items.tools.ItemElementalSword(ThaumcraftMaterials.TOOLMAT_ELEMENTAL), "elemental_sword", true);
/* 215 */     ItemsTC.elementalShovel = registerItem(new thaumcraft.common.items.tools.ItemElementalShovel(ThaumcraftMaterials.TOOLMAT_ELEMENTAL), "elemental_shovel", true);
/* 216 */     ItemsTC.elementalPick = registerItem(new ItemElementalPickaxe(ThaumcraftMaterials.TOOLMAT_ELEMENTAL), "elemental_pick", true);
/* 217 */     ItemsTC.elementalHoe = registerItem(new ItemElementalHoe(ThaumcraftMaterials.TOOLMAT_ELEMENTAL), "elemental_hoe", true);
/*     */     
/* 219 */     ItemsTC.primalCrusher = registerItem(new ItemPrimalCrusher(), "primal_crusher", true);
/* 220 */     ItemsTC.crimsonBlade = registerItem(new ItemCrimsonBlade(), "crimson_blade", true);
/*     */     
/* 222 */     ItemsTC.boneBow = registerItem(new thaumcraft.common.items.tools.ItemBowBone(), "bone_bow", true);
/* 223 */     ItemsTC.primalArrows = registerItem(new ItemGenericVariants(new String[] { "air", "fire", "water", "earth", "order", "entropy" }), "primal_arrow", true);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 228 */     ItemsTC.wand = registerItem(new thaumcraft.common.items.wands.ItemWand(), "wand", false);
/* 229 */     ItemsTC.wandCaps = registerItem(new ItemGenericVariants(new String[] { "iron", "gold", "brass", "thaumium_inert", "thaumium", "void_inert", "void" }), "wand_cap", true);
/*     */     
/* 231 */     ItemsTC.wandRods = registerItem(new ItemGenericVariants(new String[] { "greatwood", "silverwood", "obsidian", "blaze", "ice", "quartz", "bone", "reed", "greatwood_staff", "silverwood_staff", "obsidian_staff", "blaze_staff", "ice_staff", "quartz_staff", "bone_staff", "reed_staff", "primal_staff" }), "wand_rod", true);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 236 */     ItemsTC.focusPouch = registerItem(new ItemFocusPouch(), "focus_pouch", true);
/*     */     
/* 238 */     ItemsTC.focusShock = registerItem(new ItemFocusShock(), "focus_shock", true);
/* 239 */     ItemsTC.focusFire = registerItem(new ItemFocusFire(), "focus_fire", true);
/* 240 */     ItemsTC.focusFrost = registerItem(new thaumcraft.common.items.wands.foci.ItemFocusFrost(), "focus_frost", true);
/* 241 */     ItemsTC.focusExcavation = registerItem(new ItemFocusExcavation(), "focus_excavation", true);
/* 242 */     ItemsTC.focusEqualTrade = registerItem(new ItemFocusTrade(), "focus_equal_trade", true);
/* 243 */     ItemsTC.focusHole = registerItem(new thaumcraft.common.items.wands.foci.ItemFocusPortableHole(), "focus_hole", true);
/* 244 */     ItemsTC.focusHellbat = registerItem(new thaumcraft.common.items.wands.foci.ItemFocusHellbat(), "focus_hellbat", true);
/* 245 */     ItemsTC.focusPrimal = registerItem(new thaumcraft.common.items.wands.foci.ItemFocusPrimal(), "focus_primal", true);
/* 246 */     ItemsTC.focusPech = registerItem(new ItemFocusPech(), "focus_pech", true);
/* 247 */     ItemsTC.focusShard = registerItem(new ItemFocusShard(), "focus_shard", true);
/* 248 */     ItemsTC.focusGrapple = registerItem(new ItemFocusGrapple(), "focus_grapple", true);
/* 249 */     ItemsTC.focusBuilder = registerItem(new ItemFocusBuilder(), "focus_builder", true);
/*     */     
/*     */ 
/* 252 */     ItemsTC.goggles = registerItem(new thaumcraft.common.items.armor.ItemGoggles(ThaumcraftMaterials.ARMORMAT_SPECIAL, 4, 0), "goggles", true);
/*     */     
/* 254 */     ItemsTC.thaumiumHelm = registerItem(new ItemThaumiumArmor(ThaumcraftMaterials.ARMORMAT_THAUMIUM, 2, 0), "thaumium_helm", true);
/* 255 */     ItemsTC.thaumiumChest = registerItem(new ItemThaumiumArmor(ThaumcraftMaterials.ARMORMAT_THAUMIUM, 2, 1), "thaumium_chest", true);
/* 256 */     ItemsTC.thaumiumLegs = registerItem(new ItemThaumiumArmor(ThaumcraftMaterials.ARMORMAT_THAUMIUM, 2, 2), "thaumium_legs", true);
/* 257 */     ItemsTC.thaumiumBoots = registerItem(new ItemThaumiumArmor(ThaumcraftMaterials.ARMORMAT_THAUMIUM, 2, 3), "thaumium_boots", true);
/*     */     
/* 259 */     ItemsTC.clothChest = registerItem(new ItemRobeArmor(ThaumcraftMaterials.ARMORMAT_SPECIAL, 1, 1), "cloth_chest", true);
/* 260 */     ItemsTC.clothLegs = registerItem(new ItemRobeArmor(ThaumcraftMaterials.ARMORMAT_SPECIAL, 2, 2), "cloth_legs", true);
/* 261 */     ItemsTC.clothBoots = registerItem(new ItemRobeArmor(ThaumcraftMaterials.ARMORMAT_SPECIAL, 1, 3), "cloth_boots", true);
/*     */     
/* 263 */     ItemsTC.travellerBoots = registerItem(new thaumcraft.common.items.armor.ItemBootsTraveller(ThaumcraftMaterials.ARMORMAT_SPECIAL, 4, 3), "traveller_boots", true);
/*     */     
/* 265 */     ItemsTC.fortressHelm = registerItem(new ItemFortressArmor(ItemFortressArmor.ARMORMAT_FORTRESS, 4, 0), "fortress_helm", true);
/* 266 */     ItemsTC.fortressChest = registerItem(new ItemFortressArmor(ItemFortressArmor.ARMORMAT_FORTRESS, 4, 1), "fortress_chest", true);
/* 267 */     ItemsTC.fortressLegs = registerItem(new ItemFortressArmor(ItemFortressArmor.ARMORMAT_FORTRESS, 4, 2), "fortress_legs", true);
/*     */     
/* 269 */     ItemsTC.voidHelm = registerItem(new ItemVoidArmor(ThaumcraftMaterials.ARMORMAT_VOID, 2, 0), "void_helm", true);
/* 270 */     ItemsTC.voidChest = registerItem(new ItemVoidArmor(ThaumcraftMaterials.ARMORMAT_VOID, 2, 1), "void_chest", true);
/* 271 */     ItemsTC.voidLegs = registerItem(new ItemVoidArmor(ThaumcraftMaterials.ARMORMAT_VOID, 2, 2), "void_legs", true);
/* 272 */     ItemsTC.voidBoots = registerItem(new ItemVoidArmor(ThaumcraftMaterials.ARMORMAT_VOID, 2, 3), "void_boots", true);
/*     */     
/* 274 */     ItemsTC.voidRobeHelm = registerItem(new ItemVoidRobeArmor(ThaumcraftMaterials.ARMORMAT_VOID, 4, 0), "void_robe_helm", true);
/* 275 */     ItemsTC.voidRobeChest = registerItem(new ItemVoidRobeArmor(ThaumcraftMaterials.ARMORMAT_VOID, 4, 1), "void_robe_chest", true);
/* 276 */     ItemsTC.voidRobeLegs = registerItem(new ItemVoidRobeArmor(ThaumcraftMaterials.ARMORMAT_VOID, 4, 2), "void_robe_legs", true);
/*     */     
/* 278 */     ItemsTC.crimsonPlateHelm = registerItem(new ItemCultistPlateArmor(ItemArmor.ArmorMaterial.IRON, 4, 0), "crimson_plate_helm", true);
/* 279 */     ItemsTC.crimsonPlateChest = registerItem(new ItemCultistPlateArmor(ItemArmor.ArmorMaterial.IRON, 4, 1), "crimson_plate_chest", true);
/* 280 */     ItemsTC.crimsonPlateLegs = registerItem(new ItemCultistPlateArmor(ItemArmor.ArmorMaterial.IRON, 4, 2), "crimson_plate_legs", true);
/*     */     
/* 282 */     ItemsTC.crimsonBoots = registerItem(new ItemCultistBoots(ItemArmor.ArmorMaterial.IRON, 2, 3), "crimson_boots", true);
/*     */     
/* 284 */     ItemsTC.crimsonRobeHelm = registerItem(new ItemCultistRobeArmor(ItemArmor.ArmorMaterial.IRON, 4, 0), "crimson_robe_helm", true);
/* 285 */     ItemsTC.crimsonRobeChest = registerItem(new ItemCultistRobeArmor(ItemArmor.ArmorMaterial.IRON, 4, 1), "crimson_robe_chest", true);
/* 286 */     ItemsTC.crimsonRobeLegs = registerItem(new ItemCultistRobeArmor(ItemArmor.ArmorMaterial.IRON, 4, 2), "crimson_robe_legs", true);
/*     */     
/* 288 */     ItemsTC.crimsonPraetorHelm = registerItem(new ItemCultistLeaderArmor(4, 0), "crimson_praetor_helm", true);
/* 289 */     ItemsTC.crimsonPraetorChest = registerItem(new ItemCultistLeaderArmor(4, 1), "crimson_praetor_chest", true);
/* 290 */     ItemsTC.crimsonPraetorLegs = registerItem(new ItemCultistLeaderArmor(4, 2), "crimson_praetor_legs", true);
/*     */     
/* 292 */     ItemsTC.thaumostaticHarness = registerItem(new thaumcraft.common.items.armor.ItemThaumostaticHarness(ThaumcraftMaterials.ARMORMAT_SPECIAL, 4, 1), "thaumostatic_harness", true);
/*     */     
/*     */ 
/* 295 */     ItemsTC.baubles = registerItem(new ItemBaubles(), "baubles", true);
/* 296 */     ItemsTC.amuletRunic = registerItem(new ItemAmuletRunic(), "amulet_runic", true);
/* 297 */     ItemsTC.girdleRunic = registerItem(new thaumcraft.common.items.baubles.ItemGirdleRunic(), "girdle_runic", true);
/* 298 */     ItemsTC.ringRunic = registerItem(new thaumcraft.common.items.baubles.ItemRingRunic(), "ring_runic", true);
/* 299 */     ItemsTC.amuletVis = registerItem(new ItemAmuletVis(), "amulet_vis", true);
/* 300 */     ItemsTC.girdleHover = registerItem(new ItemGirdleHover(), "girdle_hover", true);
/* 301 */     ItemsTC.ringVerdant = registerItem(new ItemRingVerdant(), "ring_verdant", true);
/*     */     
/*     */ 
/* 304 */     ItemsTC.brain = registerItem(new ItemZombieBrain(), "brain", true);
/* 305 */     ItemsTC.chunks = registerItem(new ItemChunksEdible(), "chunk", true);
/* 306 */     ItemsTC.tripleMeatTreat = registerItem(new ItemTripleMeatTreat(), "triple_meat_treat", true);
/* 307 */     ItemsTC.alumentum = registerItem(new thaumcraft.common.items.consumables.ItemAlumentum(), "alumentum", true);
/* 308 */     ItemsTC.sanitySoap = registerItem(new ItemSanitySoap(), "sanity_soap", true);
/* 309 */     ItemsTC.bathSalts = registerItem(new ItemBathSalts(), "bath_salts", true);
/* 310 */     ItemsTC.bucketDeath = registerItem(new ItemBucketDeath(), "bucket_death", true);
/* 311 */     ItemsTC.bucketPure = registerItem(new ItemBucketPure(), "bucket_pure", true);
/* 312 */     ItemsTC.bottleTaint = registerItem(new ItemBottleTaint(), "bottle_taint", true);
/* 313 */     ItemsTC.lootBag = registerItem(new ItemLootBag(), "loot_bag", true);
/* 314 */     ItemsTC.turretPlacer = registerItem(new ItemTurretPlacer(), "turret", true);
/*     */     
/* 316 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(ConfigBlocks.FluidDeath.instance, 1000), new ItemStack(ItemsTC.bucketDeath), new ItemStack(Items.field_151133_ar));
/*     */     
/*     */ 
/* 319 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(ConfigBlocks.FluidPure.instance, 1000), new ItemStack(ItemsTC.bucketPure), new ItemStack(Items.field_151133_ar));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 325 */     ItemsTC.shard = registerItem(new ItemShard(), "shard", true);
/* 326 */     ItemsTC.amber = registerItem(new Item(), "amber", true);
/* 327 */     ItemsTC.quicksilver = registerItem(new Item(), "quicksilver", true);
/* 328 */     ItemsTC.ingots = registerItem(new ItemGenericVariants(new String[] { "thaumium", "void", "brass" }), "ingot", true);
/* 329 */     ItemsTC.nuggets = registerItem(new ItemGenericVariants(new String[] { "iron", "copper", "tin", "silver", "lead", "quicksilver", "thaumium", "void", "brass" }), "nugget", true);
/* 330 */     ItemsTC.clusters = registerItem(new ItemGenericVariants(new String[] { "iron", "gold", "copper", "tin", "silver", "lead", "cinnabar" }), "cluster", true);
/*     */     
/*     */ 
/* 333 */     ItemsTC.fabric = registerItem(new Item(), "fabric", true);
/* 334 */     ItemsTC.tallow = registerItem(new Item(), "tallow", true);
/* 335 */     ItemsTC.gear = registerItem(new ItemGenericVariants(new String[] { "brass", "thaumium", "void" }), "gear", true);
/* 336 */     ItemsTC.plate = registerItem(new ItemGenericVariants(new String[] { "brass", "iron", "thaumium", "void" }), "plate", true);
/* 337 */     ItemsTC.filter = registerItem(new Item(), "filter", true);
/* 338 */     ItemsTC.morphicResonator = registerItem(new Item(), "morphic_resonator", true);
/* 339 */     ItemsTC.salisMundus = registerItem(new Item(), "salis_mundus", true);
/* 340 */     ItemsTC.mirroredGlass = registerItem(new Item(), "mirrored_glass", true);
/* 341 */     ItemsTC.voidSeed = registerItem(new Item(), "void_seed", true);
/* 342 */     ItemsTC.primalCharm = registerItem(new ItemPrimalCharm(), "primal_charm", true);
/* 343 */     ItemsTC.label = registerItem(new ItemLabel(), "label", true);
/* 344 */     ItemsTC.scribingTools = registerItem(new ItemScribingTools(), "scribing_tools", true);
/* 345 */     ItemsTC.mind = registerItem(new ItemGenericVariants(new String[] { "clockwork", "biothaumic" }), "mind", true);
/* 346 */     ItemsTC.modules = registerItem(new ItemGenericVariants(new String[] { "vision", "aggression" }), "module", true);
/*     */     
/*     */ 
/* 349 */     ItemsTC.tainted = registerItem(new ItemTainted(), "tainted", true);
/* 350 */     ItemsTC.coin = registerItem(new Item(), "coin", true);
/* 351 */     ItemsTC.eldritchEye = registerItem(new Item(), "eldritch_eye", true);
/* 352 */     ItemsTC.crimsonRites = registerItem(new ItemCrimsonRites(), "crimson_rites", true);
/* 353 */     ItemsTC.runedTablet = registerItem(new ItemRunedTablet(), "runed_tablet", true);
/* 354 */     ItemsTC.primordialPearl = registerItem(new Item(), "primordial_pearl", true);
/* 355 */     ItemsTC.knowledgeFragment = registerItem(new thaumcraft.common.items.resources.ItemKnowledgeFragment(), "knowledge_fragment", true);
/* 356 */     ItemsTC.researchNotes = registerItem(new ItemResearchNotes(), "research_notes", true);
/*     */     
/* 358 */     ItemsTC.crystalEssence = registerItem(new ItemCrystal(), "crystal_essence", true);
/* 359 */     ItemsTC.wispyEssence = registerItem(new ItemGenericEssentiaContainer(2), "wispy_essence", true);
/* 360 */     ItemsTC.phial = registerItem(new thaumcraft.common.items.resources.ItemPhial(), "phial", true);
/*     */     
/* 362 */     ItemsTC.creativePlacer = registerItem(new thaumcraft.common.items.consumables.ItemCreativePlacer(), "creative_placer", true);
/* 363 */     ItemsTC.creativeFluxSponge = registerItem(new ItemCreativeFluxSponge(), "creative_flux_sponge", true);
/* 364 */     ItemsTC.jarBrace = registerItem(new Item(), "jar_brace", true);
/*     */     
/*     */ 
/* 367 */     ItemsTC.golemBell = registerItem(new thaumcraft.common.entities.construct.golem.ItemGolemBell(), "golem_bell", true);
/* 368 */     ItemsTC.golemPlacer = registerItem(new thaumcraft.common.entities.construct.golem.ItemGolemPlacer(), "golem", true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static Item registerItem(Item item, String name, boolean registerInventory)
/*     */   {
/* 375 */     item.func_77655_b(name);
/* 376 */     item.func_77637_a(Thaumcraft.tabTC);
/* 377 */     GameRegistry.registerItem(item, name, "Thaumcraft");
/* 378 */     if (registerInventory) {
/* 379 */       if ((item instanceof IVariantItems)) {
/* 380 */         for (int i = 0; i < ((IVariantItems)item).getVariantNames().length; i++) {
/* 381 */           int m = i;
/* 382 */           if (((IVariantItems)item).getVariantMeta() != null) {
/* 383 */             m = ((IVariantItems)item).getVariantMeta()[i];
/*     */           }
/* 385 */           String qq = name + "_" + ((IVariantItems)item).getVariantNames()[i];
/* 386 */           if (((IVariantItems)item).getVariantNames()[i].equals("*")) qq = name;
/* 387 */           Thaumcraft.proxy.registerItemMesh(item, m, qq, true);
/*     */         }
/*     */       } else
/* 390 */         Thaumcraft.proxy.registerFromSubItems(item, name);
/*     */     }
/* 392 */     return item;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/config/ConfigItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */