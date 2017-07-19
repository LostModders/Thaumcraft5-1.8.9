/*      */ package thaumcraft.common.config;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.entity.monster.EntityEnderman;
/*      */ import net.minecraft.entity.passive.EntityBat;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.nbt.NBTTagByte;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.ThaumcraftApi.EntityTagsNBT;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.blocks.BlocksTC;
/*      */ import thaumcraft.api.crafting.CrucibleRecipe;
/*      */ import thaumcraft.api.crafting.IArcaneRecipe;
/*      */ import thaumcraft.api.crafting.InfusionRecipe;
/*      */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*      */ import thaumcraft.api.entities.IEldritchMob;
/*      */ import thaumcraft.api.golems.GolemHelper;
/*      */ import thaumcraft.api.items.ItemsTC;
/*      */ import thaumcraft.api.research.ResearchCategories;
/*      */ import thaumcraft.api.research.ResearchItem;
/*      */ import thaumcraft.api.research.ResearchPage;
/*      */ import thaumcraft.api.research.ScanBlock;
/*      */ import thaumcraft.api.research.ScanBlockState;
/*      */ import thaumcraft.api.research.ScanEntity;
/*      */ import thaumcraft.api.research.ScanItem;
/*      */ import thaumcraft.api.research.ScanningManager;
/*      */ import thaumcraft.api.wands.IWand;
/*      */ import thaumcraft.api.wands.WandCap;
/*      */ import thaumcraft.api.wands.WandRod;
/*      */ import thaumcraft.common.lib.aura.EntityAuraNode;
/*      */ import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
/*      */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*      */ 
/*      */ public class ConfigResearch
/*      */ {
/*   49 */   public static ItemStack wand = null;
/*      */   
/*      */   public static void init() {
/*   52 */     wand = new ItemStack(ItemsTC.wand);
/*   53 */     ((IWand)wand.func_77973_b()).setCap(wand, ConfigItems.WAND_CAP_IRON);
/*   54 */     ((IWand)wand.func_77973_b()).setRod(wand, ConfigItems.WAND_ROD_WOOD);
/*      */     
/*   56 */     initCategories();
/*   57 */     initBasicResearch();
/*   58 */     initThaumaturgyResearch();
/*   59 */     initAlchemyResearch();
/*   60 */     initArtificeResearch();
/*   61 */     initGolemancyResearch();
/*   62 */     initEldritchResearch();
/*      */   }
/*      */   
/*   65 */   public static String[] TCCategories = { "BASICS", "THAUMATURGY", "ALCHEMY", "ARTIFICE", "GOLEMANCY", "ELDRITCH" };
/*      */   
/*      */   private static void initCategories() {
/*   68 */     ResearchCategories.registerCategory("BASICS", null, new ResourceLocation("thaumcraft", "textures/items/thaumonomicon_cheat.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_1.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
/*      */     
/*      */ 
/*      */ 
/*   72 */     ResearchCategories.registerCategory("THAUMATURGY", null, new ResourceLocation("thaumcraft", "textures/research/r_thaumaturgy.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_2.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
/*      */     
/*      */ 
/*      */ 
/*   76 */     ResearchCategories.registerCategory("ALCHEMY", null, new ResourceLocation("thaumcraft", "textures/research/r_crucible.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_3.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
/*      */     
/*      */ 
/*      */ 
/*   80 */     ResearchCategories.registerCategory("ARTIFICE", null, new ResourceLocation("thaumcraft", "textures/research/r_artifice.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_4.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
/*      */     
/*      */ 
/*      */ 
/*   84 */     ResearchCategories.registerCategory("GOLEMANCY", "BASICGOLEMANCY", new ResourceLocation("thaumcraft", "textures/research/r_golemancy.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_5.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
/*      */     
/*      */ 
/*      */ 
/*   88 */     ResearchCategories.registerCategory("ELDRITCH", "ELDRITCHMINOR", new ResourceLocation("thaumcraft", "textures/research/r_eldritch.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_6.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initThaumaturgyResearch()
/*      */   {
/*   96 */     new ResearchItem("BASICTHAUMATURGY", "THAUMATURGY", new AspectList(), 0, 0, 0, new Object[] { wand }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BASICTHAUMATURGY.1"), new ResearchPage("tc.research_page.BASICTHAUMATURGY.2"), new ResearchPage((IRecipe)recipes.get("WandCapIron")), new ResearchPage((IRecipe)recipes.get("WandBasic")) }).setAutoUnlock().setStub().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  103 */     new ResearchItem("NODETAPPER1", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.ENERGY, 3).add(Aspect.MOTION, 3).add(Aspect.EXCHANGE, 3), -4, 0, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_nodetap1.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODETAPPER1.1") }).setParents(new String[] { "BASICTHAUMATURGY" }).setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  108 */     new ResearchItem("NODETAPPER2", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.ENERGY, 3).add(Aspect.MOTION, 3).add(Aspect.EXCHANGE, 3), -6, -1, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_nodetap2.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODETAPPER2.1") }).setParents(new String[] { "NODETAPPER1" }).setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  113 */     new ResearchItem("AURAPRESERVE", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.ENERGY, 3).add(Aspect.PROTECT, 3).add(Aspect.EXCHANGE, 3), -4, -2, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_aurapreserve.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.AURAPRESERVE.1") }).setParents(new String[] { "NODETAPPER1" }).setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  118 */     new ResearchItem("AURAMANIP1", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.EXCHANGE, 3).add(Aspect.WATER, 3).add(Aspect.AVERSION, 3), -8, 0, 3, new Object[] { new ItemStack(BlocksTC.auraTotem, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.AURAMANIP1.1"), new ResearchPage((InfusionRecipe)recipes.get("TotemPush")), new ResearchPage("tc.research_page.AURAMANIP1.2"), new ResearchPage((IArcaneRecipe)recipes.get("PoleInner")), new ResearchPage("tc.research_page.AURAMANIP1.3"), new ResearchPage((IArcaneRecipe)recipes.get("PoleOuter")) }).setParents(new String[] { "NODETAPPER2", "INFUSION" }).setSpecial().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  128 */     new ResearchItem("AURAMANIP2", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.EXCHANGE, 3).add(Aspect.WATER, 3).add(Aspect.DESIRE, 3), -9, -1, 3, new Object[] { new ItemStack(BlocksTC.auraTotem, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.AURAMANIP2.1"), new ResearchPage((InfusionRecipe)recipes.get("TotemPull")) }).setParents(new String[] { "AURAMANIP1" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  133 */     new ResearchItem("AURAPOLESTABLE", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.ORDER, 3).add(Aspect.MOTION, 3), -9, 1, 2, new Object[] { new ItemStack(BlocksTC.auraTotem, 1, 4) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.AURAPOLESTABLE.1"), new ResearchPage((IArcaneRecipe)recipes.get("PoleStable")) }).setParents(new String[] { "AURAMANIP1" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  139 */     new ResearchItem("NODESTABLE", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.MOTION, 6).add(Aspect.MECHANISM, 3).add(Aspect.TRAP, 8), -8, -2, 3, new Object[] { new ItemStack(BlocksTC.nodeStabilizer) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODESTABLE.1"), new ResearchPage((IArcaneRecipe)recipes.get("NodeStable")) }).setParents(new String[] { "AURAMANIP1" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  144 */     new ResearchItem("NODEMAGNET", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.MOTION, 6).add(Aspect.MECHANISM, 3).add(Aspect.DESIRE, 8), -11, 0, 3, new Object[] { new ItemStack(ItemsTC.turretPlacer, 1, 2) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODEMAGNET.1"), new ResearchPage((InfusionRecipe)recipes.get("NodeMagnet")), new ResearchPage("tc.research_page.NODEMAGNET.2"), new ResearchPage("tc.research_page.NODEMAGNET.3").setRequisite("!NODEMAGNETDANGER") }).setHidden().setSpecial().setParents(new String[] { "NODESTABLE", "AURAPOLESTABLE", "MINDBIOTHAUMIC", "CRYSTALFARMER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  149 */     ThaumcraftApi.addWarpToResearch("NODEMAGNET", 3);
/*      */     
/*      */ 
/*      */ 
/*  153 */     new ResearchItem("FOCUSFIRE", "THAUMATURGY", new AspectList().add(Aspect.FIRE, 6).add(Aspect.AIR, 3).add(Aspect.ENERGY, 3), 3, 0, 1, new Object[] { new ItemStack(ItemsTC.focusFire) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSFIRE.1"), new ResearchPage("tc.research_page.FOCUSFIRE.2"), new ResearchPage((IArcaneRecipe)recipes.get("FocusFire")) }).setParents(new String[] { "BASICTHAUMATURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  158 */     new ResearchItem("FOCUSSHOCK", "THAUMATURGY", new AspectList().add(Aspect.AIR, 6).add(Aspect.FIRE, 3).add(Aspect.ENERGY, 3), 5, -2, 1, new Object[] { new ItemStack(ItemsTC.focusShock) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSSHOCK.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusShock")) }).setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  163 */     new ResearchItem("FOCUSFROST", "THAUMATURGY", new AspectList().add(Aspect.WATER, 6).add(Aspect.COLD, 3).add(Aspect.ENERGY, 3), 6, -1, 1, new Object[] { new ItemStack(ItemsTC.focusFrost) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSFROST.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusFrost")) }).setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  168 */     new ResearchItem("FOCUSEXCAVATION", "THAUMATURGY", new AspectList().add(Aspect.EARTH, 6).add(Aspect.ENTROPY, 3).add(Aspect.ENERGY, 3), 6, 1, 2, new Object[] { new ItemStack(ItemsTC.focusExcavation) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSEXCAVATION.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusExcavation")) }).setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  173 */     new ResearchItem("FOCUSTRADE", "THAUMATURGY", new AspectList().add(Aspect.EXCHANGE, 6).add(Aspect.EARTH, 3).add(Aspect.ENERGY, 3), 5, 2, 2, new Object[] { new ItemStack(ItemsTC.focusEqualTrade) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSTRADE.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusTrade")) }).setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  178 */     new ResearchItem("FOCUSBUILDER", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 6).add(Aspect.EARTH, 3).add(Aspect.ORDER, 3).add(Aspect.MOTION, 3), 3, 3, 2, new Object[] { new ItemStack(ItemsTC.focusBuilder) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSBUILDER.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusBuilder")), new ResearchPage("tc.research_page.FOCUSBUILDER.2") }).setParents(new String[] { "FOCUSTRADE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  184 */     new ResearchItem("FOCUSPORTABLEHOLE", "THAUMATURGY", new AspectList().add(Aspect.MOTION, 3).add(Aspect.ENTROPY, 3).add(Aspect.ELDRITCH, 6).add(Aspect.AIR, 3), 6, 3, 2, new Object[] { new ItemStack(ItemsTC.focusHole) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSPORTABLEHOLE.1"), new ResearchPage((InfusionRecipe)recipes.get("FocusPortableHole")) }).setHidden().setParents(new String[] { "FOCUSTRADE", "FOCUSEXCAVATION", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  189 */     new ResearchItem("FOCUSHELLBAT", "THAUMATURGY", new AspectList().add(Aspect.MOTION, 3).add(Aspect.BEAST, 6).add(Aspect.FIRE, 3).add(Aspect.ENERGY, 3), 3, -2, 2, new Object[] { new ItemStack(ItemsTC.focusHellbat) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSHELLBAT.1"), new ResearchPage((InfusionRecipe)recipes.get("FocusHellbat")) }).setHidden().setParents(new String[] { "FOCUSFIRE", "INFUSION", "!BAT" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  193 */     ScanningManager.addScannableThing(new ScanEntity("!BAT", EntityBat.class, true));
/*  194 */     ThaumcraftApi.addWarpToResearch("FOCUSHELLBAT", 2);
/*  195 */     ThaumcraftApi.addWarpToItem(new ItemStack(ItemsTC.focusHellbat), 1);
/*      */     
/*  197 */     new ResearchItem("FOCUSGRAPPLE", "THAUMATURGY", new AspectList().add(Aspect.MOTION, 6).add(Aspect.AIR, 3).add(Aspect.WATER, 3), 6, -4, 1, new Object[] { new ItemStack(ItemsTC.focusGrapple) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSGRAPPLE.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusGrapple")) }).setParents(new String[] { "FOCUSSHOCK" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  203 */     new ResearchItem("VAMPBAT", "THAUMATURGY", new AspectList().add(Aspect.DESIRE, 5).add(Aspect.LIFE, 5).add(Aspect.ENERGY, 5), 4, -3, 1, new Object[] { new ResourceLocation("thaumcraft", "textures/foci/vampirebats.png") }).setPages(new ResearchPage[] { new ResearchPage("focus.upgrade.vampirebats.text") }).setSecondary().setParents(new String[] { "FOCUSHELLBAT" }).setParentsHidden(new String[] { "FOCALMANIPULATION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  207 */     ThaumcraftApi.addWarpToResearch("VAMPBAT", 2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  225 */     new ResearchItem("FOCUSPOUCH", "THAUMATURGY", new AspectList().add(Aspect.VOID, 6).add(Aspect.TOOL, 3), -2, -2, 1, new Object[] { new ItemStack(ItemsTC.focusPouch) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSPOUCH.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusPouch")) }).setParents(new String[] { "BASICTHAUMATURGY" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  232 */     new ResearchItem("CAP_iron", "THAUMATURGY").setAutoUnlock().registerResearchItem();
/*      */     
/*  234 */     new ResearchItem("CAP_gold", "THAUMATURGY", new AspectList().add(Aspect.METAL, 3).add(Aspect.DESIRE, 3).add(Aspect.TOOL, 3), 0, 2, 1, new Object[] { new ItemStack(ItemsTC.wandCaps, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_gold.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapGold")) }).setParents(new String[] { "BASICTHAUMATURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  238 */     new ResearchItem("CAP_brass", "THAUMATURGY", new AspectList().add(Aspect.METAL, 3).add(Aspect.ENERGY, 3).add(Aspect.TOOL, 3), 0, 4, 1, new Object[] { new ItemStack(ItemsTC.wandCaps, 1, 2) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_brass.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapBrass")) }).setParents(new String[] { "CAP_gold", "METALLURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  242 */     new ResearchItem("CAP_thaumium", "THAUMATURGY", new AspectList().add(Aspect.METAL, 6).add(Aspect.ENERGY, 6).add(Aspect.TOOL, 3).add(Aspect.AURA, 3), 0, 6, 2, new Object[] { new ItemStack(ItemsTC.wandCaps, 1, 3) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_thaumium.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapThaumiumInert")), new ResearchPage((InfusionRecipe)recipes.get("WandCapThaumium")) }).setParents(new String[] { "CAP_brass", "METALLURGY", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  248 */     new ResearchItem("ROD_wood", "THAUMATURGY").setAutoUnlock().registerResearchItem();
/*      */     
/*  250 */     new ResearchItem("ROD_greatwood", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.PLANT, 6).add(Aspect.ENERGY, 3), -3, 2, 1, new Object[] { new ItemStack(ItemsTC.wandRods, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_greatwood.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodGreatwood")) }).setParents(new String[] { "BASICTHAUMATURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  255 */     new ResearchItem("PRIMALRODS", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.AIR, 6).add(Aspect.FIRE, 6).add(Aspect.EARTH, 6).add(Aspect.WATER, 6).add(Aspect.ORDER, 6).add(Aspect.ENTROPY, 6).add(Aspect.ENERGY, 3), -5, 3, 3, new Object[] { new ItemStack(ItemsTC.wandRods, 1, 2), new ItemStack(ItemsTC.wandRods, 1, 3), new ItemStack(ItemsTC.wandRods, 1, 4), new ItemStack(ItemsTC.wandRods, 1, 5), new ItemStack(ItemsTC.wandRods, 1, 6), new ItemStack(ItemsTC.wandRods, 1, 7) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMALRODS.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodReed")), new ResearchPage("tc.research_page.PRIMALRODS.2"), new ResearchPage((InfusionRecipe)recipes.get("WandRodBlaze")), new ResearchPage("tc.research_page.PRIMALRODS.3"), new ResearchPage((InfusionRecipe)recipes.get("WandRodObsidian")), new ResearchPage("tc.research_page.PRIMALRODS.4"), new ResearchPage((InfusionRecipe)recipes.get("WandRodIce")), new ResearchPage("tc.research_page.PRIMALRODS.5"), new ResearchPage((InfusionRecipe)recipes.get("WandRodQuartz")), new ResearchPage("tc.research_page.PRIMALRODS.6"), new ResearchPage((InfusionRecipe)recipes.get("WandRodBone")) }).setParents(new String[] { "ROD_greatwood", "INFUSION" }).setSiblings(new String[] { "ROD_reed", "ROD_blaze", "ROD_obsidian", "ROD_ice", "ROD_quartz", "ROD_bone" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  269 */     new ResearchItem("ROD_silverwood", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 6).add(Aspect.PLANT, 6).add(Aspect.ENERGY, 9), -3, 4, 3, new Object[] { new ItemStack(ItemsTC.wandRods, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_silverwood.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodSilverwood")) }).setParents(new String[] { "ROD_greatwood", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  274 */     ArrayList<IArcaneRecipe> scer = new ArrayList();
/*  275 */     AspectList al1 = new AspectList();
/*  276 */     AspectList al2 = new AspectList();
/*  277 */     AspectList al3 = new AspectList();
/*      */     
/*  279 */     int cost = ConfigItems.WAND_CAP_IRON.getCraftCost() * ConfigItems.WAND_ROD_WOOD.getCraftCost() * 12;
/*  280 */     Aspect as; for (Iterator i$ = Aspect.getPrimalAspects().iterator(); i$.hasNext(); al1.add(as, cost)) as = (Aspect)i$.next();
/*  281 */     ItemStack sceptre1 = new ItemStack(ItemsTC.wand);
/*  282 */     ((IWand)sceptre1.func_77973_b()).setCap(sceptre1, ConfigItems.WAND_CAP_IRON);
/*  283 */     ((IWand)sceptre1.func_77973_b()).setRod(sceptre1, ConfigItems.WAND_ROD_WOOD);
/*  284 */     sceptre1.func_77983_a("sceptre", new NBTTagByte((byte)1));
/*  285 */     ShapedArcaneRecipe r1 = new ShapedArcaneRecipe("SCEPTRE", sceptre1, al1, new Object[] { " TF", " RT", "T  ", Character.valueOf('T'), ConfigItems.WAND_CAP_IRON.getItem(), Character.valueOf('R'), ConfigItems.WAND_ROD_WOOD.getItem(), Character.valueOf('F'), new ItemStack(ItemsTC.primalCharm) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  290 */     scer.add(r1);
/*      */     
/*  292 */     cost = ConfigItems.WAND_CAP_GOLD.getCraftCost() * ConfigItems.WAND_ROD_GREATWOOD.getCraftCost() * 12;
/*  293 */     Aspect as; for (Iterator i$ = Aspect.getPrimalAspects().iterator(); i$.hasNext(); al2.add(as, cost)) as = (Aspect)i$.next();
/*  294 */     ItemStack sceptre2 = new ItemStack(ItemsTC.wand);
/*  295 */     ((IWand)sceptre2.func_77973_b()).setCap(sceptre2, ConfigItems.WAND_CAP_GOLD);
/*  296 */     ((IWand)sceptre2.func_77973_b()).setRod(sceptre2, ConfigItems.WAND_ROD_GREATWOOD);
/*  297 */     sceptre2.func_77983_a("sceptre", new NBTTagByte((byte)1));
/*  298 */     ShapedArcaneRecipe r2 = new ShapedArcaneRecipe("SCEPTRE", sceptre2, al2, new Object[] { " TF", " RT", "T  ", Character.valueOf('T'), ConfigItems.WAND_CAP_GOLD.getItem(), Character.valueOf('R'), ConfigItems.WAND_ROD_GREATWOOD.getItem(), Character.valueOf('F'), new ItemStack(ItemsTC.primalCharm) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  303 */     scer.add(r2);
/*      */     
/*  305 */     cost = ConfigItems.WAND_CAP_THAUMIUM.getCraftCost() * ConfigItems.WAND_ROD_SILVERWOOD.getCraftCost() * 12;
/*  306 */     for (Aspect as : Aspect.getPrimalAspects()) {
/*  307 */       al3.add(as, cost);
/*      */     }
/*  309 */     ItemStack sceptre3 = new ItemStack(ItemsTC.wand);
/*  310 */     ((IWand)sceptre3.func_77973_b()).setCap(sceptre3, ConfigItems.WAND_CAP_THAUMIUM);
/*  311 */     ((IWand)sceptre3.func_77973_b()).setRod(sceptre3, ConfigItems.WAND_ROD_SILVERWOOD);
/*  312 */     sceptre3.func_77983_a("sceptre", new NBTTagByte((byte)1));
/*  313 */     ShapedArcaneRecipe r3 = new ShapedArcaneRecipe("SCEPTRE", sceptre3, al3, new Object[] { " TF", " RT", "T  ", Character.valueOf('T'), ConfigItems.WAND_CAP_THAUMIUM.getItem(), Character.valueOf('R'), ConfigItems.WAND_ROD_SILVERWOOD.getItem(), Character.valueOf('F'), new ItemStack(ItemsTC.primalCharm) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  318 */     scer.add(r3);
/*      */     
/*  320 */     new ResearchItem("SCEPTRE", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 6).add(Aspect.CRAFT, 6).add(Aspect.PLANT, 6).add(Aspect.ENERGY, 9), -2, 6, 3, new Object[] { sceptre3 }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SCEPTRE.1"), new ResearchPage((IArcaneRecipe[])scer.toArray(new IArcaneRecipe[0])) }).setParents(new String[] { "ROD_silverwood" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  325 */     new ResearchItem("ROD_greatwood_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.PLANT, 6).add(Aspect.ENERGY, 3), -4, 6, 1, new Object[] { new ItemStack(ItemsTC.wandRods, 1, 8) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_greatwood_staff.1"), new ResearchPage("tc.research_page.ROD_greatwood_staff.2"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodGreatwoodStaff")) }).setParents(new String[] { "ROD_silverwood" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  332 */     new ResearchItem("PRIMALSTAVES", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.AIR, 6).add(Aspect.FIRE, 6).add(Aspect.EARTH, 6).add(Aspect.WATER, 6).add(Aspect.ORDER, 6).add(Aspect.ENTROPY, 6).add(Aspect.ENERGY, 3), -6, 4, 3, new Object[] { new ItemStack(ItemsTC.wandRods, 1, 10), new ItemStack(ItemsTC.wandRods, 1, 11), new ItemStack(ItemsTC.wandRods, 1, 12), new ItemStack(ItemsTC.wandRods, 1, 13), new ItemStack(ItemsTC.wandRods, 1, 14), new ItemStack(ItemsTC.wandRods, 1, 15) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMALSTAVES.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodReedStaff")), new ResearchPage((IArcaneRecipe)recipes.get("WandRodBlazeStaff")), new ResearchPage((IArcaneRecipe)recipes.get("WandRodObsidianStaff")), new ResearchPage((IArcaneRecipe)recipes.get("WandRodIceStaff")), new ResearchPage((IArcaneRecipe)recipes.get("WandRodQuartzStaff")), new ResearchPage((IArcaneRecipe)recipes.get("WandRodBoneStaff")) }).setParents(new String[] { "PRIMALRODS", "ROD_greatwood_staff", "INFUSION" }).setSecondary().setSiblings(new String[] { "ROD_reed_staff", "ROD_blaze_staff", "ROD_obsidian_staff", "ROD_ice_staff", "ROD_quartz_staff", "ROD_bone_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  343 */     new ResearchItem("ROD_silverwood_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 6).add(Aspect.PLANT, 6).add(Aspect.ENERGY, 9), -3, 8, 3, new Object[] { new ItemStack(ItemsTC.wandRods, 1, 9) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_silverwood_staff.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodSilverwoodStaff")) }).setSecondary().setParents(new String[] { "ROD_silverwood", "ROD_greatwood_staff" }).setFlipped().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  348 */     new ResearchItem("WANDPED", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.ENERGY, 3).add(Aspect.EXCHANGE, 3).add(Aspect.ENERGY, 3), -4, -4, 2, new Object[] { new ItemStack(BlocksTC.rechargePedestal) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.WANDPED.1"), new ResearchPage((InfusionRecipe)recipes.get("WandPed")) }).setParents(new String[] { "INFUSION", "AURAPRESERVE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  354 */     new ResearchItem("WORKBENCHCHARGER", "THAUMATURGY", new AspectList().add(Aspect.AURA, 3).add(Aspect.MECHANISM, 3).add(Aspect.ENERGY, 6), -3, -6, 2, new Object[] { new ItemStack(BlocksTC.arcaneWorkbenchCharger) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.WORKBENCHCHARGER.1"), new ResearchPage((IArcaneRecipe)recipes.get("WorkbenchCharger")) }).setParents(new String[] { "WANDPED" }).setParentsHidden(new String[] { "ROD_greatwood" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  360 */     new ResearchItem("VISAMULET", "THAUMATURGY", new AspectList().add(Aspect.AURA, 3).add(Aspect.ENERGY, 6).add(Aspect.ENERGY, 3).add(Aspect.VOID, 3), -5, -6, 2, new Object[] { new ItemStack(ItemsTC.amuletVis, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.VISAMULET.1"), new ResearchPage((InfusionRecipe)recipes.get("VisAmulet")) }).setParents(new String[] { "WANDPED" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  367 */     new ResearchItem("FOCALMANIPULATION", "THAUMATURGY", new AspectList().add(Aspect.ENERGY, 8).add(Aspect.TOOL, 8).add(Aspect.CRAFT, 5).add(Aspect.CRYSTAL, 5).add(Aspect.ENERGY, 5), 1, -3, 2, new Object[] { new ItemStack(BlocksTC.wandWorkbench) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCALMANIPULATION.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocalManipulator")), new ResearchPage("tc.research_page.FOCALMANIPULATION.2") }).setParentsHidden(new String[] { "FOCUSFROST", "FOCUSSHOCK", "FOCUSTRADE", "FOCUSEXCAVATION" }).setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initArtificeResearch()
/*      */   {
/*  381 */     new ResearchItem("BASICARTIFACE", "ARTIFICE", new AspectList(), 0, 1, 0, new Object[] { new ItemStack(ItemsTC.primalCharm) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BASICARTIFACE.1"), new ResearchPage((IArcaneRecipe)recipes.get("PrimalCharm")), new ResearchPage((IRecipe)recipes.get("MundaneAmulet")), new ResearchPage((IRecipe)recipes.get("MundaneRing")), new ResearchPage((IRecipe)recipes.get("MundaneBelt")), new ResearchPage((IRecipe)recipes.get("FancyAmulet")), new ResearchPage((IRecipe)recipes.get("FancyRing")), new ResearchPage((IRecipe)recipes.get("FancyBelt")), new ResearchPage((IArcaneRecipe)recipes.get("MirrorGlass")) }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  390 */     new ResearchItem("ARCANESTONE", "ARTIFICE", new AspectList(), -2, 0, 0, new Object[] { new ItemStack(BlocksTC.stone) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANESTONE.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneStone1")), new ResearchPage((IRecipe)recipes.get("ArcaneStone2")), new ResearchPage((IRecipe)recipes.get("ArcaneStone3")), new ResearchPage((IRecipe)recipes.get("ArcaneStone4")), new ResearchPage((IRecipe)recipes.get("ArcaneStone5")), new ResearchPage((IRecipe)recipes.get("ArcaneStone6")) }).setStub().setAutoUnlock().setRound().setSiblings(new String[] { "BASICARTIFACE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  397 */     new ResearchItem("ENCHFABRIC", "ARTIFICE", new AspectList(), -2, 2, 0, new Object[] { new ItemStack(ItemsTC.fabric) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENCHFABRIC.1"), new ResearchPage((IArcaneRecipe)recipes.get("EnchantedFabric")), new ResearchPage("tc.research_page.ENCHFABRIC.2"), new ResearchPage((IArcaneRecipe)recipes.get("RobeChest")), new ResearchPage((IArcaneRecipe)recipes.get("RobeLegs")), new ResearchPage((IArcaneRecipe)recipes.get("RobeBoots")) }).setStub().setAutoUnlock().setRound().setSiblings(new String[] { "BASICARTIFACE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  403 */     new ResearchItem("TABLE", "ARTIFICE", new AspectList(), 0, -3, 0, new Object[] { new ItemStack(BlocksTC.tableWood) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TABLE.1"), new ResearchPage((IRecipe)recipes.get("TableWood")), new ResearchPage((IRecipe)recipes.get("TableStone")) }).setStub().setAutoUnlock().setRound().setSiblings(new String[] { "BASICARTIFACE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  407 */     new ResearchItem("ARCTABLE", "ARTIFICE", new AspectList(), -1, -4, 0, new Object[] { new ItemStack(BlocksTC.arcaneWorkbench) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCTABLE.1"), new ResearchPage((List)recipes.get("ArcTable")) }).setStub().setAutoUnlock().setRound().setSiblings(new String[] { "TABLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  411 */     new ResearchItem("RESTABLE", "ARTIFICE", new AspectList(), 1, -4, 0, new Object[] { new ItemStack(BlocksTC.researchTable) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESTABLE.1"), new ResearchPage((List)recipes.get("ResTable")) }).setStub().setAutoUnlock().setRound().setSiblings(new String[] { "TABLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  415 */     new ResearchItem("THAUMOMETER", "ARTIFICE", new AspectList(), 2, 2, 0, new Object[] { new ItemStack(ItemsTC.thaumometer) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.THAUMOMETER.1"), new ResearchPage((IRecipe)recipes.get("Thaumometer")) }).setStub().setAutoUnlock().setRound().setSiblings(new String[] { "BASICARTIFACE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  419 */     new ResearchItem("PAVEBARRIER", "ARTIFICE", new AspectList().add(Aspect.EARTH, 3).add(Aspect.ENTROPY, 3).add(Aspect.MOTION, 3), -4, -2, 1, new Object[] { new ItemStack(BlocksTC.pavingStone, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PAVEBARRIER.1"), new ResearchPage((IArcaneRecipe)recipes.get("PaveBarrier")), new ResearchPage("tc.research_page.PAVEBARRIER.2") }).setParents(new String[] { "ARCANESTONE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  425 */     new ResearchItem("PAVETRAVEL", "ARTIFICE", new AspectList().add(Aspect.EARTH, 3).add(Aspect.AIR, 3).add(Aspect.MOTION, 3), -3, -2, 1, new Object[] { new ItemStack(BlocksTC.pavingStone, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PAVETRAVEL.1"), new ResearchPage((IArcaneRecipe)recipes.get("PaveTravel")) }).setParents(new String[] { "ARCANESTONE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  430 */     new ResearchItem("LEVITATOR", "ARTIFICE", new AspectList().add(Aspect.MOTION, 3).add(Aspect.FLIGHT, 3).add(Aspect.AIR, 3), -3, -4, 1, new Object[] { new ItemStack(BlocksTC.levitator) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.LEVITATOR.1"), new ResearchPage((IArcaneRecipe)recipes.get("Levitator")), new ResearchPage("tc.research_page.LEVITATOR.2") }).setParents(new String[] { "NITOR", "PAVETRAVEL", "!volatus" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  435 */     new ResearchItem("REDSTONERELAY", "ARTIFICE", new AspectList().add(Aspect.ENERGY, 6).add(Aspect.AVERSION, 3).add(Aspect.ORDER, 3), 2, -2, 1, new Object[] { new ItemStack(BlocksTC.redstoneRelay) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.REDSTONERELAY.1"), new ResearchPage((IArcaneRecipe)recipes.get("RedstoneRelay")), new ResearchPage("tc.research_page.REDSTONERELAY.2") }).setParents(new String[] { "BASICARTIFACE" }).setFlipped().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  441 */     new ResearchItem("HOVERHARNESS", "ARTIFICE", new AspectList().add(Aspect.FLIGHT, 6).add(Aspect.AIR, 6).add(Aspect.MECHANISM, 3), -5, -5, 3, new Object[] { new ItemStack(ItemsTC.thaumostaticHarness) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HOVERHARNESS.1"), new ResearchPage((InfusionRecipe)recipes.get("HoverHarness")), new ResearchPage("tc.research_page.HOVERHARNESS.2") }).setHidden().setParents(new String[] { "LEVITATOR", "WANDPED", "!volatus" }).setParentsHidden(new String[] { "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  446 */     new ResearchItem("HOVERGIRDLE", "ARTIFICE", new AspectList().add(Aspect.FLIGHT, 6).add(Aspect.AIR, 3).add(Aspect.MOTION, 6), -6, -6, 3, new Object[] { new ItemStack(ItemsTC.girdleHover) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HOVERGIRDLE.1"), new ResearchPage((InfusionRecipe)recipes.get("HoverGirdle")) }).setParents(new String[] { "HOVERHARNESS" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  450 */     new ResearchItem("GOGGLES", "ARTIFICE", new AspectList().add(Aspect.SENSES, 3).add(Aspect.AURA, 3).add(Aspect.ENERGY, 3), 4, 2, 1, new Object[] { new ItemStack(ItemsTC.goggles) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOGGLES.1"), new ResearchPage((IArcaneRecipe)recipes.get("Goggles")) }).setParents(new String[] { "THAUMOMETER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  455 */     new ResearchItem("DIOPTRA", "ARTIFICE", new AspectList().add(Aspect.SENSES, 3).add(Aspect.AURA, 3).add(Aspect.ENERGY, 3).add(Aspect.EARTH, 3), 6, 3, 2, new Object[] { new ItemStack(BlocksTC.dioptra) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.DIOPTRA.1"), new ResearchPage((IArcaneRecipe)recipes.get("Dioptra")) }).setParents(new String[] { "GOGGLES" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  460 */     new ResearchItem("ARCANEEAR", "ARTIFICE", new AspectList().add(Aspect.SENSES, 3).add(Aspect.MECHANISM, 3).add(Aspect.AIR, 3), 6, 0, 1, new Object[] { new ItemStack(BlocksTC.arcaneEar) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANEEAR.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneEar")) }).setParents(new String[] { "GOGGLES" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  467 */     new ResearchItem("BELLOWS", "ARTIFICE", new AspectList().add(Aspect.AIR, 6).add(Aspect.MECHANISM, 3).add(Aspect.MOTION, 3), 2, 0, 1, new Object[] { new ItemStack(BlocksTC.bellows, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BELLOWS.1"), new ResearchPage((IArcaneRecipe)recipes.get("Bellows")), new ResearchPage("tc.research_page.BELLOWS.2") }).setParents(new String[] { "BASICARTIFACE" }).setSecondary().registerResearchItem().setFlipped();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  472 */     new ResearchItem("INFERNALFURNACE", "ARTIFICE", new AspectList().add(Aspect.FIRE, 6).add(Aspect.METAL, 3).add(Aspect.CRAFT, 3).add(Aspect.AURA, 3), 4, -1, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_infernalfurnace.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.INFERNALFURNACE.1"), new ResearchPage((List)recipes.get("InfernalFurnace")), new ResearchPage("tc.research_page.INFERNALFURNACE.2") }).setParents(new String[] { "BASICARTIFACE", "NITOR", "ALUMENTUM" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  477 */     ThaumcraftApi.addWarpToResearch("INFERNALFURNACE", 2);
/*      */     
/*  479 */     new ResearchItem("INFUSION", "ARTIFICE", new AspectList().add(Aspect.ENERGY, 6).add(Aspect.MECHANISM, 3).add(Aspect.CRAFT, 6), 0, 4, 2, new Object[] { new ItemStack(BlocksTC.infusionMatrix) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.INFUSION.1"), new ResearchPage((IArcaneRecipe)recipes.get("InfusionMatrix")), new ResearchPage((IArcaneRecipe)recipes.get("ArcanePedestal")), new ResearchPage("tc.research_page.INFUSION.2"), new ResearchPage((List)recipes.get("InfusionAltar")), new ResearchPage("tc.research_page.INFUSION.3"), new ResearchPage("tc.research_page.INFUSION.4"), new ResearchPage("tc.research_page.INFUSION.5"), new ResearchPage("tc.research_page.INFUSION.ANCIENT").setRequisite("!ORBLOCK1"), new ResearchPage((List)recipes.get("InfusionAltarAncient")).setRequisite("!ORBLOCK1"), new ResearchPage((IArcaneRecipe)recipes.get("AncientPedestal")).setRequisite("!ORBLOCK1"), new ResearchPage("tc.research_page.INFUSION.ELDRITCH").setRequisite("!ORBLOCK2"), new ResearchPage((List)recipes.get("InfusionAltarEldritch")).setRequisite("!ORBLOCK2"), new ResearchPage((IArcaneRecipe)recipes.get("EldritchPedestal")).setRequisite("!ORBLOCK2") }).setParents(new String[] { "BASICARTIFACE", "DISTILESSENTIA" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  493 */     new ResearchItem("INFUSIONBOOST", "ARTIFICE", new AspectList().add(Aspect.ENERGY, 6).add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.EXCHANGE, 3), 0, 6, 2, new Object[] { new ItemStack(BlocksTC.stone, 1, 8), new ItemStack(BlocksTC.stone, 1, 9) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.INFUSIONBOOST.1"), new ResearchPage((IArcaneRecipe)recipes.get("MatrixSpeed")), new ResearchPage((IArcaneRecipe)recipes.get("MatrixCost")) }).setSecondary().setHidden().setParents(new String[] { "INFUSION", "ALUMENTUM", "NITOR", "ELDRITCHMINOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  498 */     new ResearchItem("RUNICARMOR", "ARTIFICE", new AspectList().add(Aspect.PROTECT, 6).add(Aspect.AIR, 3).add(Aspect.ENERGY, 3).add(Aspect.MIND, 3), -3, 6, 3, new Object[] { new ItemStack(ItemsTC.amuletRunic) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICARMOR.1"), new ResearchPage("tc.research_page.RUNICARMOR.2"), new ResearchPage((InfusionRecipe)recipes.get("RunicRing")), new ResearchPage((InfusionRecipe)recipes.get("RunicAmulet")), new ResearchPage((InfusionRecipe)recipes.get("RunicGirdle")) }).setParents(new String[] { "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  503 */     new ResearchItem("RUNICSPECIAL", "ARTIFICE", new AspectList().add(Aspect.ORDER, 3).add(Aspect.PROTECT, 3).add(Aspect.ENERGY, 6), -2, 7, 2, new Object[] { new ItemStack(ItemsTC.ringRunic, 1, 2) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICSPECIAL.1"), new ResearchPage((InfusionRecipe)recipes.get("RunicRingCharged")), new ResearchPage("tc.research_page.RUNICSPECIAL.2"), new ResearchPage((InfusionRecipe)recipes.get("RunicRingHealing")), new ResearchPage("tc.research_page.RUNICSPECIAL.3"), new ResearchPage((InfusionRecipe)recipes.get("RunicGirdleKinetic")), new ResearchPage("tc.research_page.RUNICSPECIAL.4"), new ResearchPage((InfusionRecipe)recipes.get("RunicAmuletEmergency")) }).setParents(new String[] { "RUNICARMOR" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  511 */     ArrayList<InfusionRecipe> raug = new ArrayList();
/*  512 */     for (int a = 0; a <= 4; a++) {
/*  513 */       ItemStack in = new ItemStack(ItemsTC.clothChest);
/*  514 */       if (a > 0) in.func_77983_a("RS.HARDEN", new NBTTagByte((byte)a));
/*  515 */       raug.add(new InfusionRunicAugmentRecipe(in));
/*      */     }
/*  517 */     new ResearchItem("RUNICAUGMENTATION", "ARTIFICE", new AspectList().add(Aspect.ORDER, 3).add(Aspect.PROTECT, 3).add(Aspect.EXCHANGE, 4).add(Aspect.DESIRE, 4), -4, 7, 1, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_runicupg.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICAUGMENTATION.1"), new ResearchPage((InfusionRecipe[])raug.toArray(new InfusionRecipe[0])), new ResearchPage("tc.research_page.RUNICAUGMENTATION.2") }).setParents(new String[] { "RUNICARMOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  522 */     ArrayList<IArcaneRecipe> banners = new ArrayList();
/*  523 */     for (int a = 0; a < 16; a++) {
/*  524 */       banners.add((IArcaneRecipe)recipes.get("Banner_" + a));
/*      */     }
/*  526 */     ItemStack is = new ItemStack(BlocksTC.banner, 1, 0);
/*  527 */     is.func_77982_d(new NBTTagCompound());
/*  528 */     is.func_77978_p().func_74774_a("color", (byte)10);
/*  529 */     new ResearchItem("BANNERS", "ARTIFICE", new AspectList(), 0, -5, 1, new Object[] { is }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BANNERS.1"), new ResearchPage((IArcaneRecipe[])banners.toArray(new IArcaneRecipe[0])) }).setSiblings(new String[] { "TABLE" }).setStub().setAutoUnlock().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  534 */     new ResearchItem("BOOTSTRAVELLER", "ARTIFICE", new AspectList().add(Aspect.MOTION, 3).add(Aspect.EARTH, 3).add(Aspect.FLIGHT, 3).add(Aspect.WATER, 3), -4, 3, 2, new Object[] { new ItemStack(ItemsTC.travellerBoots) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BOOTSTRAVELLER.1"), new ResearchPage((InfusionRecipe)recipes.get("BootsTraveller")) }).setParents(new String[] { "ENCHFABRIC", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  539 */     ScanningManager.addScannableThing(new ScanBlockState("!TELEPORT", BlocksTC.eldritch.func_176203_a(6), false));
/*  540 */     ScanningManager.addScannableThing(new ScanBlock("!TELEPORT", Blocks.field_150427_aO));
/*  541 */     ScanningManager.addScannableThing(new ScanBlock("!TELEPORT", Blocks.field_150384_bq));
/*  542 */     ScanningManager.addScannableThing(new ScanBlock("!TELEPORT", Blocks.field_150378_br));
/*  543 */     ScanningManager.addScannableThing(new ScanItem("!TELEPORT", new ItemStack(Items.field_151079_bi)));
/*  544 */     ScanningManager.addScannableThing(new ScanEntity("!TELEPORT", EntityEnderman.class, true));
/*      */     
/*  546 */     if (Config.allowMirrors) {
/*  547 */       new ResearchItem("MIRROR", "ARTIFICE", new AspectList().add(Aspect.MOTION, 6).add(Aspect.ELDRITCH, 3).add(Aspect.DARKNESS, 3).add(Aspect.CRYSTAL, 3), -1, 9, 2, new Object[] { new ItemStack(BlocksTC.mirror) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MIRROR.1"), new ResearchPage("tc.research_page.MIRROR.2"), new ResearchPage((InfusionRecipe)recipes.get("Mirror")), new ResearchPage("tc.research_page.MIRROR.3") }).setHidden().setParents(new String[] { "INFUSION", "!TELEPORT" }).registerResearchItem();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  553 */       new ResearchItem("MIRRORHAND", "ARTIFICE", new AspectList().add(Aspect.TOOL, 6).add(Aspect.ELDRITCH, 3).add(Aspect.CRYSTAL, 3).add(Aspect.MOTION, 3), -2, 10, 2, new Object[] { new ItemStack(ItemsTC.handMirror) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MIRRORHAND.1"), new ResearchPage((InfusionRecipe)recipes.get("MirrorHand")) }).setSecondary().setParents(new String[] { "MIRROR" }).registerResearchItem();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  558 */       new ResearchItem("MIRRORESSENTIA", "ARTIFICE", new AspectList().add(Aspect.MOTION, 6).add(Aspect.ELDRITCH, 3).add(Aspect.WATER, 3).add(Aspect.ENERGY, 3), 0, 10, 2, new Object[] { new ItemStack(BlocksTC.mirrorEssentia) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MIRRORESSENTIA.1"), new ResearchPage((InfusionRecipe)recipes.get("MirrorEssentia")), new ResearchPage("tc.research_page.MIRRORESSENTIA.2") }).setSecondary().setParents(new String[] { "MIRROR" }).registerResearchItem();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  566 */     new ResearchItem("ARCANELAMP", "ARTIFICE", new AspectList().add(Aspect.LIGHT, 3).add(Aspect.SENSES, 3).add(Aspect.DARKNESS, 3), -6, -1, 1, new Object[] { new ItemStack(BlocksTC.lampArcane) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANELAMP.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneLamp")), new ResearchPage("tc.research_page.ARCANELAMP.2").setRequisite("ARCANEBORE") }).setSecondary().setParents(new String[] { "BASICARTIFACE", "NITOR", "!lux" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  572 */     new ResearchItem("LAMPGROWTH", "ARTIFICE", new AspectList().add(Aspect.LIGHT, 3).add(Aspect.PLANT, 6).add(Aspect.LIFE, 3).add(Aspect.TOOL, 3), -7, -2, 2, new Object[] { new ItemStack(BlocksTC.lampGrowth) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.LAMPGROWTH.1"), new ResearchPage((InfusionRecipe)recipes.get("LampGrowth")) }).setHidden().setParentsHidden(new String[] { "INFUSION" }).setParents(new String[] { "ARCANELAMP", "!herba" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  577 */     new ResearchItem("LAMPFERTILITY", "ARTIFICE", new AspectList().add(Aspect.BEAST, 6).add(Aspect.LIFE, 6).add(Aspect.LIGHT, 3).add(Aspect.DESIRE, 6), -7, 0, 2, new Object[] { new ItemStack(BlocksTC.lampFertility) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.LAMPFERTILITY.1"), new ResearchPage((InfusionRecipe)recipes.get("LampFertility")) }).setHidden().setParentsHidden(new String[] { "INFUSION" }).setParents(new String[] { "ARCANELAMP", "!victus", "!desiderium" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  583 */     new ResearchItem("BONEBOW", "ARTIFICE", new AspectList().add(Aspect.AVERSION, 3).add(Aspect.FLIGHT, 3).add(Aspect.UNDEAD, 3), -9, -1, 1, new Object[] { new ItemStack(ItemsTC.boneBow) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BONEBOW.1"), new ResearchPage((IArcaneRecipe)recipes.get("BoneBow")) }).setHidden().setParents(new String[] { "!BONE", "!BOW" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  587 */     ScanningManager.addScannableThing(new ScanItem("!BONE", new ItemStack(Items.field_151103_aS)));
/*  588 */     ScanningManager.addScannableThing(new ScanItem("!BOW", new ItemStack(Items.field_151031_f, 1, 32767)));
/*      */     
/*      */ 
/*  591 */     ArrayList<IArcaneRecipe> rcbb = new ArrayList();
/*  592 */     for (int a = 0; a < 6; a++) {
/*  593 */       rcbb.add((IArcaneRecipe)recipes.get("PrimalArrow_" + a));
/*      */     }
/*  595 */     new ResearchItem("PRIMALARROW", "ARTIFICE", new AspectList().add(Aspect.AVERSION, 3).add(Aspect.AIR, 3).add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.EARTH, 3).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), -9, 0, 2, new Object[] { new ItemStack(ItemsTC.primalArrows, 1, 32767) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMALARROW.1"), new ResearchPage((IArcaneRecipe[])rcbb.toArray(new IArcaneRecipe[0])), new ResearchPage("tc.research_page.PRIMALARROW.2"), new ResearchPage("tc.research_page.PRIMALARROW.3") }).setHidden().setParents(new String[] { "!ARROW" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  601 */     ScanningManager.addScannableThing(new ScanItem("!ARROW", new ItemStack(Items.field_151032_g)));
/*      */     
/*      */ 
/*  604 */     new ResearchItem("ELEMENTALTOOLS", "ARTIFICE", new AspectList().add(Aspect.TOOL, 6).add(Aspect.AVERSION, 6).add(Aspect.AIR, 3).add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.EARTH, 3).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), 4, 6, 2, new Object[] { new ItemStack(ItemsTC.elementalAxe), new ItemStack(ItemsTC.elementalPick), new ItemStack(ItemsTC.elementalSword), new ItemStack(ItemsTC.elementalShovel), new ItemStack(ItemsTC.elementalHoe) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELEMENTALTOOLS.1"), new ResearchPage((InfusionRecipe)recipes.get("ElementalAxe")), new ResearchPage("tc.research_page.ELEMENTALTOOLS.2"), new ResearchPage("tc.research_page.ELEMENTALTOOLS.3"), new ResearchPage((InfusionRecipe)recipes.get("ElementalPick")), new ResearchPage("tc.research_page.ELEMENTALTOOLS.4"), new ResearchPage("tc.research_page.ELEMENTALTOOLS.5"), new ResearchPage((InfusionRecipe)recipes.get("ElementalSword")), new ResearchPage("tc.research_page.ELEMENTALTOOLS.6"), new ResearchPage((InfusionRecipe)recipes.get("ElementalShovel")), new ResearchPage("tc.research_page.ELEMENTALTOOLS.7"), new ResearchPage("tc.research_page.ELEMENTALTOOLS.8"), new ResearchPage((InfusionRecipe)recipes.get("ElementalHoe")) }).setParents(new String[] { "METALLURGY", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  616 */     ArrayList<InfusionRecipe> ieref = new ArrayList();
/*  617 */     for (int a = 0; a < EnumInfusionEnchantment.REFINING.maxLevel; a++) {
/*  618 */       ItemStack in = new ItemStack(Items.field_151035_b);
/*  619 */       if (a > 0) EnumInfusionEnchantment.addInfusionEnchantment(in, EnumInfusionEnchantment.REFINING, a);
/*  620 */       ieref.add(new InfusionEnchantmentRecipe((InfusionEnchantmentRecipe)recipes.get("IEREFINING"), in));
/*      */     }
/*      */     
/*  623 */     ArrayList<InfusionRecipe> iesou = new ArrayList();
/*  624 */     for (int a = 0; a < EnumInfusionEnchantment.SOUNDING.maxLevel; a++) {
/*  625 */       ItemStack in = new ItemStack(Items.field_151050_s);
/*  626 */       if (a > 0) EnumInfusionEnchantment.addInfusionEnchantment(in, EnumInfusionEnchantment.SOUNDING, a);
/*  627 */       iesou.add(new InfusionEnchantmentRecipe((InfusionEnchantmentRecipe)recipes.get("IESOUNDING"), in));
/*      */     }
/*      */     
/*  630 */     ArrayList<InfusionRecipe> iearc = new ArrayList();
/*  631 */     for (int a = 0; a < EnumInfusionEnchantment.ARCING.maxLevel; a++) {
/*  632 */       ItemStack in = new ItemStack(Items.field_151041_m);
/*  633 */       if (a > 0) EnumInfusionEnchantment.addInfusionEnchantment(in, EnumInfusionEnchantment.ARCING, a);
/*  634 */       iearc.add(new InfusionEnchantmentRecipe((InfusionEnchantmentRecipe)recipes.get("IEARCING"), in));
/*      */     }
/*      */     
/*  637 */     ArrayList<InfusionRecipe> ieess = new ArrayList();
/*  638 */     for (int a = 0; a < EnumInfusionEnchantment.ESSENCE.maxLevel; a++) {
/*  639 */       ItemStack in = new ItemStack(Items.field_151052_q);
/*  640 */       if (a > 0) EnumInfusionEnchantment.addInfusionEnchantment(in, EnumInfusionEnchantment.ESSENCE, a);
/*  641 */       ieess.add(new InfusionEnchantmentRecipe((InfusionEnchantmentRecipe)recipes.get("IEESSENCE"), in));
/*      */     }
/*      */     
/*  644 */     ItemStack in0 = new ItemStack(Items.field_151053_p);
/*  645 */     InfusionEnchantmentRecipe ierburrow = new InfusionEnchantmentRecipe((InfusionEnchantmentRecipe)recipes.get("IEBURROWING"), in0);
/*  646 */     ItemStack in1 = new ItemStack(Items.field_151051_r);
/*  647 */     InfusionEnchantmentRecipe iercollect = new InfusionEnchantmentRecipe((InfusionEnchantmentRecipe)recipes.get("IECOLLECTOR"), in1);
/*  648 */     ItemStack in2 = new ItemStack(Items.field_151037_a);
/*  649 */     InfusionEnchantmentRecipe ierdestructive = new InfusionEnchantmentRecipe((InfusionEnchantmentRecipe)recipes.get("IEDESTRUCTIVE"), in2);
/*      */     
/*      */ 
/*  652 */     new ResearchItem("RUNICAUGMENTATION", "ARTIFICE", new AspectList().add(Aspect.ORDER, 3).add(Aspect.PROTECT, 3).add(Aspect.EXCHANGE, 4).add(Aspect.DESIRE, 4), -4, 7, 1, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_runicupg.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICAUGMENTATION.1"), new ResearchPage((InfusionRecipe[])raug.toArray(new InfusionRecipe[0])), new ResearchPage("tc.research_page.RUNICAUGMENTATION.2") }).setParents(new String[] { "RUNICARMOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  657 */     new ResearchItem("INFUSIONENCHANTMENT", "ARTIFICE", new AspectList().add(Aspect.ENERGY, 6).add(Aspect.MIND, 3).add(Aspect.AVERSION, 3).add(Aspect.PROTECT, 3).add(Aspect.TOOL, 3), 6, 6, 3, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_inf_enchant.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.1"), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.2"), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.COLLECTOR"), new ResearchPage(iercollect), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.BURROWING"), new ResearchPage(ierburrow), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.REFINING"), new ResearchPage((InfusionRecipe[])ieref.toArray(new InfusionRecipe[0])), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.SOUNDING"), new ResearchPage((InfusionRecipe[])iesou.toArray(new InfusionRecipe[0])), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.DESTRUCTIVE"), new ResearchPage(ierdestructive), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.ARCING"), new ResearchPage((InfusionRecipe[])iearc.toArray(new InfusionRecipe[0])), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.ESSENCE"), new ResearchPage((InfusionRecipe[])ieess.toArray(new InfusionRecipe[0])) }).setParents(new String[] { "ELEMENTALTOOLS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  670 */     new ResearchItem("VERDANTHEART", "ARTIFICE", new AspectList().add(Aspect.LIFE, 8).add(Aspect.ORDER, 6).add(Aspect.WATER, 6).add(Aspect.PLANT, 6), 2, 8, 3, new Object[] { new ItemStack(ItemsTC.ringVerdant) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.VERDANTHEART.1"), new ResearchPage((InfusionRecipe)recipes.get("VerdantHeart")) }).setParents(new String[] { "INFUSION", "WANDPED" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  675 */     new ResearchItem("VERDANTHEARTLIFE", "ARTIFICE", new AspectList().add(Aspect.LIFE, 8).add(Aspect.MAN, 6).add(Aspect.DESIRE, 6).add(Aspect.AIR, 6).add(Aspect.PLANT, 6), 4, 8, 3, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_inf_heart.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.VERDANTHEARTLIFE.1"), new ResearchPage((InfusionRecipe)recipes.get("VerdantHeartLife")), new ResearchPage("tc.research_page.VERDANTHEARTLIFE.2"), new ResearchPage((InfusionRecipe)recipes.get("VerdantHeartSustain")) }).setParents(new String[] { "INFUSIONENCHANTMENT", "VERDANTHEART" }).setParentsHidden(new String[] { "INFUSIONENCHANTMENT" }).setFlipped().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  683 */     new ResearchItem("JARBRAIN", "ARTIFICE", new AspectList().add(Aspect.DESIRE, 3).add(Aspect.MIND, 3).add(Aspect.UNDEAD, 3).add(Aspect.DESIRE, 3), -6, 6, 2, new Object[] { new ItemStack(BlocksTC.jar, 1, 2) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.JARBRAIN.1"), new ResearchPage((InfusionRecipe)recipes.get("JarBrain")) }).setParents(new String[] { "INFUSION", "!BRAIN" }).setHidden().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  687 */     ScanningManager.addScannableThing(new ScanEntity("!BRAIN", thaumcraft.common.entities.monster.EntityBrainyZombie.class, true));
/*  688 */     ScanningManager.addScannableThing(new ScanItem("!BRAIN", new ItemStack(ItemsTC.brain)));
/*      */     
/*  690 */     ThaumcraftApi.addWarpToResearch("JARBRAIN", 3);
/*  691 */     ThaumcraftApi.addWarpToItem(new ItemStack(BlocksTC.jar, 1, 2), 1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  696 */     new ResearchItem("ARMORFORTRESS", "ARTIFICE", new AspectList().add(Aspect.METAL, 3).add(Aspect.PROTECT, 5).add(Aspect.CRAFT, 5), -6, 3, 2, new Object[] { new ItemStack(ItemsTC.fortressHelm) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARMORFORTRESS.1"), new ResearchPage("tc.research_page.ARMORFORTRESS.2"), new ResearchPage((InfusionRecipe)recipes.get("ThaumiumFortressHelm")), new ResearchPage((InfusionRecipe)recipes.get("ThaumiumFortressChest")), new ResearchPage((InfusionRecipe)recipes.get("ThaumiumFortressLegs")) }).setParents(new String[] { "METALLURGY", "BOOTSTRAVELLER", "!praemunio" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  704 */     new ResearchItem("HELMGOGGLES", "ARTIFICE", new AspectList().add(Aspect.SENSES, 5).add(Aspect.AURA, 3).add(Aspect.PROTECT, 3), -7, 2, 2, new Object[] { new ItemStack(ItemsTC.goggles) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HELMGOGGLES.1"), new ResearchPage((InfusionRecipe)recipes.get("HelmGoggles")) }).setParentsHidden(new String[] { "GOGGLES" }).setParents(new String[] { "ARMORFORTRESS" }).setHidden().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  708 */     new ResearchItem("MASKGRINNINGDEVIL", "ARTIFICE", new AspectList().add(Aspect.LIFE, 5).add(Aspect.MIND, 5).add(Aspect.PROTECT, 3), -8, 2, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_mask0.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MASKGRINNINGDEVIL.1"), new ResearchPage((InfusionRecipe)recipes.get("MaskGrinningDevil")) }).setParents(new String[] { "ARMORFORTRESS" }).setHidden().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  712 */     new ResearchItem("MASKANGRYGHOST", "ARTIFICE", new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.DEATH, 5).add(Aspect.PROTECT, 3), -8, 4, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_mask1.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MASKANGRYGHOST.1"), new ResearchPage((InfusionRecipe)recipes.get("MaskAngryGhost")) }).setParents(new String[] { "ARMORFORTRESS" }).setHidden().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  716 */     new ResearchItem("MASKSIPPINGFIEND", "ARTIFICE", new AspectList().add(Aspect.UNDEAD, 5).add(Aspect.LIFE, 5).add(Aspect.PROTECT, 3), -7, 4, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_mask2.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MASKSIPPINGFIEND.1"), new ResearchPage((InfusionRecipe)recipes.get("MaskSippingFiend")) }).setParents(new String[] { "ARMORFORTRESS" }).setHidden().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  721 */     ThaumcraftApi.addWarpToResearch("MASKANGRYGHOST", 1);
/*  722 */     ThaumcraftApi.addWarpToResearch("MASKSIPPINGFIEND", 1);
/*      */   }
/*      */   
/*      */   private static void initAlchemyResearch() {
/*  726 */     new ResearchItem("PHIAL", "ALCHEMY", new AspectList(), 0, -2, 0, new Object[] { new ItemStack(ItemsTC.phial, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PHIAL.1"), new ResearchPage((IRecipe)recipes.get("Phial")) }).setStub().setRound().setAutoUnlock().setSiblings(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  732 */     ArrayList<CrucibleRecipe> cruc = new ArrayList();
/*  733 */     for (int a = 0; a < 6; a++) {
/*  734 */       cruc.add((CrucibleRecipe)recipes.get("BalancedShard_" + a));
/*      */     }
/*      */     
/*  737 */     new ResearchItem("CRUCIBLE", "ALCHEMY", new AspectList(), 0, 0, 0, new Object[] { new ItemStack(BlocksTC.crucible) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CRUCIBLE.1"), new ResearchPage("tc.research_page.CRUCIBLE.2"), new ResearchPage("tc.research_page.CRUCIBLE.3"), new ResearchPage((List)recipes.get("Crucible")), new ResearchPage("tc.research_page.CRUCIBLE.4"), new ResearchPage((CrucibleRecipe[])cruc.toArray(new CrucibleRecipe[0])), new ResearchPage("tc.research_page.CRUCIBLE.5"), new ResearchPage(new ItemStack(ItemsTC.shard, 1, 7)), new ResearchPage((IArcaneRecipe)recipes.get("Filter")), new ResearchPage((IArcaneRecipe)recipes.get("MorphicResonator")) }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  746 */     new ResearchItem("NITOR", "ALCHEMY", new AspectList().add(Aspect.LIGHT, 3).add(Aspect.FIRE, 1), 2, -2, 1, new Object[] { new ItemStack(BlocksTC.nitor, 1, 32767) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NITOR.1"), new ResearchPage((CrucibleRecipe)recipes.get("Nitor")) }).setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  752 */     new ResearchItem("ALUMENTUM", "ALCHEMY", new AspectList().add(Aspect.ENERGY, 3).add(Aspect.FIRE, 1), 2, 2, 1, new Object[] { new ItemStack(ItemsTC.alumentum) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ALUMENTUM.1"), new ResearchPage((CrucibleRecipe)recipes.get("Alumentum")) }).setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  758 */     new ResearchItem("ALCHEMICALDUPLICATION", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.DESIRE, 6).add(Aspect.ENERGY, 3), -4, 2, 1, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_alchmult.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ALCHEMICALDUPLICATION.1"), new ResearchPage((CrucibleRecipe)recipes.get("AltGunpowder")), new ResearchPage((CrucibleRecipe)recipes.get("AltSlime")), new ResearchPage((CrucibleRecipe)recipes.get("AltGlowstone")), new ResearchPage((CrucibleRecipe)recipes.get("AltInk")), new ResearchPage((CrucibleRecipe)recipes.get("AltPrisCrys")), new ResearchPage((CrucibleRecipe)recipes.get("AltPrisShard")) }).setParents(new String[] { "TALLOW" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  768 */     new ResearchItem("ALCHEMICALMANUFACTURE", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.CRAFT, 6).add(Aspect.ENERGY, 3), -4, -2, 1, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_alchman.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ALCHEMICALMANUFACTURE.1"), new ResearchPage((CrucibleRecipe)recipes.get("AltClay")), new ResearchPage((CrucibleRecipe)recipes.get("AltWeb")), new ResearchPage((CrucibleRecipe)recipes.get("AltMossyCobble")), new ResearchPage((CrucibleRecipe)recipes.get("AltIce")), new ResearchPage((CrucibleRecipe)recipes.get("AltLava")), new ResearchPage((CrucibleRecipe)recipes.get("AltGrass")) }).setParents(new String[] { "TALLOW" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  778 */     new ResearchItem("ENTROPICPROCESSING", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.ENTROPY, 6).add(Aspect.ENERGY, 3), -5, 0, 1, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_alchent.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENTROPICPROCESSING.1"), new ResearchPage((CrucibleRecipe)recipes.get("AltBonemeal")), new ResearchPage((CrucibleRecipe)recipes.get("AltBlaze")), new ResearchPage((CrucibleRecipe)recipes.get("AltLeather")), new ResearchPage((CrucibleRecipe)recipes.get("AltString")), new ResearchPage((CrucibleRecipe)recipes.get("AltMyc")) }).setParents(new String[] { "TALLOW" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  787 */     new ResearchItem("LIQUIDDEATH", "ALCHEMY", new AspectList().add(Aspect.DEATH, 5).add(Aspect.ENTROPY, 5).add(Aspect.WATER, 1), -7, 3, 2, new Object[] { new ItemStack(ItemsTC.bucketDeath) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.LIQUIDDEATH.1"), new ResearchPage((CrucibleRecipe)recipes.get("LiquidDeath")) }).setHidden().setParents(new String[] { "ENTROPICPROCESSING", "!mortuus" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  791 */     ThaumcraftApi.addWarpToResearch("LIQUIDDEATH", 3);
/*  792 */     ThaumcraftApi.addWarpToItem(new ItemStack(ItemsTC.bucketDeath), 1);
/*      */     
/*  794 */     new ResearchItem("BOTTLETAINT", "ALCHEMY", new AspectList().add(Aspect.FLUX, 5).add(Aspect.ENERGY, 3).add(Aspect.ENTROPY, 3).add(Aspect.WATER, 1), -8, 1, 2, new Object[] { new ItemStack(ItemsTC.bottleTaint) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BOTTLETAINT.1"), new ResearchPage((CrucibleRecipe)recipes.get("BottleTaint")) }).setHidden().setParents(new String[] { "ENTROPICPROCESSING", "!vitium" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  798 */     ThaumcraftApi.addWarpToResearch("BOTTLETAINT", 2);
/*  799 */     ThaumcraftApi.addWarpToItem(new ItemStack(ItemsTC.bottleTaint), 1);
/*      */     
/*  801 */     new ResearchItem("TAINTSHARD", "ALCHEMY", new AspectList().add(Aspect.FLUX, 5).add(Aspect.AURA, 3).add(Aspect.ENTROPY, 5), -8, -1, 1, new Object[] { new ItemStack(ItemsTC.shard, 1, 6) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TAINTSHARD.1"), new ResearchPage((CrucibleRecipe)recipes.get("TaintShard")) }).setParents(new String[] { "ENTROPICPROCESSING", "!vitium", "!TAINTSHARD" }).setSecondary().setHidden().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  805 */     ThaumcraftApi.addWarpToResearch("BOTTLETAINT", 1);
/*  806 */     ScanningManager.addScannableThing(new ScanItem("!TAINTSHARD", new ItemStack(ItemsTC.shard, 1, 6)));
/*  807 */     ScanningManager.addScannableThing(new ScanBlock("!TAINTSHARD", BlocksTC.crystalTaint));
/*      */     
/*  809 */     new ResearchItem("METALLURGY", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.MECHANISM, 3), 0, 2, 1, new Object[] { new ItemStack(ItemsTC.ingots) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.METALLURGY.1"), new ResearchPage("tc.research_page.METALLURGY.2"), new ResearchPage((CrucibleRecipe)recipes.get("Thaumium")), new ResearchPage((CrucibleRecipe)recipes.get("AlchemicalBrass")), new ResearchPage((IRecipe)recipes.get("BrassGear")), new ResearchPage((IRecipe)recipes.get("BrassPlate")), new ResearchPage((IRecipe)recipes.get("ThaumiumGear")), new ResearchPage((IRecipe)recipes.get("ThaumiumPlate")), new ResearchPage((IRecipe)recipes.get("ThaumiumAxe")), new ResearchPage((IRecipe)recipes.get("ThaumiumSword")), new ResearchPage((IRecipe)recipes.get("ThaumiumPick")), new ResearchPage((IRecipe)recipes.get("ThaumiumShovel")), new ResearchPage((IRecipe)recipes.get("ThaumiumHoe")), new ResearchPage((IRecipe)recipes.get("ThaumiumHelm")), new ResearchPage((IRecipe)recipes.get("ThaumiumChest")), new ResearchPage((IRecipe)recipes.get("ThaumiumLegs")), new ResearchPage((IRecipe)recipes.get("ThaumiumBoots")) }).setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  830 */     ArrayList<ResearchPage> purepages = new ArrayList();
/*  831 */     ArrayList<ResearchPage> transpages = new ArrayList();
/*  832 */     purepages.add(new ResearchPage("tc.research_page.PUREMETAL.1"));
/*  833 */     purepages.add(new ResearchPage((CrucibleRecipe)recipes.get("PureIron")));
/*  834 */     purepages.add(new ResearchPage((CrucibleRecipe)recipes.get("PureGold")));
/*  835 */     purepages.add(new ResearchPage((CrucibleRecipe)recipes.get("PureCinnabar")));
/*  836 */     transpages.add(new ResearchPage("tc.research_page.TRANSMETAL.1"));
/*  837 */     transpages.add(new ResearchPage((CrucibleRecipe)recipes.get("TransIron")));
/*  838 */     transpages.add(new ResearchPage((CrucibleRecipe)recipes.get("TransGold")));
/*  839 */     if ((Config.foundCopperOre) && (Config.foundCopperIngot)) {
/*  840 */       purepages.add(new ResearchPage((CrucibleRecipe)recipes.get("PureCopper")));
/*  841 */       transpages.add(new ResearchPage((CrucibleRecipe)recipes.get("TransCopper")));
/*      */     }
/*  843 */     if ((Config.foundTinOre) && (Config.foundTinIngot)) {
/*  844 */       purepages.add(new ResearchPage((CrucibleRecipe)recipes.get("PureTin")));
/*  845 */       transpages.add(new ResearchPage((CrucibleRecipe)recipes.get("TransTin")));
/*      */     }
/*  847 */     if ((Config.foundSilverOre) && (Config.foundSilverIngot)) {
/*  848 */       purepages.add(new ResearchPage((CrucibleRecipe)recipes.get("PureSilver")));
/*  849 */       transpages.add(new ResearchPage((CrucibleRecipe)recipes.get("TransSilver")));
/*      */     }
/*  851 */     if ((Config.foundLeadOre) && (Config.foundLeadIngot)) {
/*  852 */       purepages.add(new ResearchPage((CrucibleRecipe)recipes.get("PureLead")));
/*  853 */       transpages.add(new ResearchPage((CrucibleRecipe)recipes.get("TransLead")));
/*      */     }
/*      */     
/*  856 */     new ResearchItem("PUREMETAL", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 3), -1, 4, 1, new Object[] { new ItemStack(ItemsTC.clusters) }).setPages((ResearchPage[])purepages.toArray(new ResearchPage[1])).setParents(new String[] { "METALLURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*  859 */     new ResearchItem("TRANSMETAL", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 3).add(Aspect.FLUX, 3), 1, 4, 1, new Object[] { new ItemStack(Items.field_151074_bl) }).setPages((ResearchPage[])transpages.toArray(new ResearchPage[1])).setParents(new String[] { "METALLURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*  862 */     new ResearchItem("TALLOW", "ALCHEMY", new AspectList().add(Aspect.BEAST, 3).add(Aspect.FIRE, 1), -2, 0, 1, new Object[] { new ItemStack(ItemsTC.tallow) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TALLOW.1"), new ResearchPage((CrucibleRecipe)recipes.get("Tallow")), new ResearchPage((IRecipe)recipes.get("TallowCandle")) }).setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  866 */     new ResearchItem("ETHEREALBLOOM", "ALCHEMY", new AspectList().add(Aspect.LIGHT, 1).add(Aspect.PLANT, 6).add(Aspect.LIFE, 3).add(Aspect.FLUX, 6), -6, -3, 2, new Object[] { new ItemStack(BlocksTC.bloom) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ETHEREALBLOOM.1"), new ResearchPage((CrucibleRecipe)recipes.get("EtherealBloom")), new ResearchPage("tc.research_page.ETHEREALBLOOM.2") }).setHidden().setParents(new String[] { "ALCHEMICALMANUFACTURE", "!vitium" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  871 */     new ResearchItem("BATHSALTS", "ALCHEMY", new AspectList().add(Aspect.MIND, 3).add(Aspect.AURA, 3).add(Aspect.ORDER, 3).add(Aspect.LIFE, 3), -4, -4, 2, new Object[] { new ItemStack(ItemsTC.bathSalts) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BATHSALTS.1"), new ResearchPage((CrucibleRecipe)recipes.get("BathSalts")) }).setHidden().setParents(new String[] { "ALCHEMICALMANUFACTURE", "@BATHSALTS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  876 */     new ResearchItem("SANESOAP", "ALCHEMY", new AspectList().add(Aspect.MIND, 5).add(Aspect.ORDER, 5).add(Aspect.LIFE, 5).add(Aspect.ELDRITCH, 5), -3, -6, 1, new Object[] { new ItemStack(ItemsTC.sanitySoap) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SANESOAP.1"), new ResearchPage((CrucibleRecipe)recipes.get("SaneSoap")) }).setParents(new String[] { "BATHSALTS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  880 */     new ResearchItem("ARCANESPA", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.MECHANISM, 3).add(Aspect.ORDER, 3), -5, -6, 1, new Object[] { new ItemStack(BlocksTC.spa) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANESPA.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneSpa")) }).setSecondary().setParents(new String[] { "BATHSALTS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  885 */     new ResearchItem("DISTILESSENTIA", "ALCHEMY", new AspectList().add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.CRAFT, 3), 4, 0, 1, new Object[] { new ItemStack(BlocksTC.smelterBasic, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.DISTILESSENTIA.1"), new ResearchPage((IArcaneRecipe)recipes.get("EssentiaSmelter")), new ResearchPage("tc.research_page.DISTILESSENTIA.2"), new ResearchPage((IArcaneRecipe)recipes.get("Alembic")), new ResearchPage("tc.research_page.DISTILESSENTIA.3"), new ResearchPage("tc.research_page.DISTILESSENTIA.4").setRequisite("BELLOWS") }).setSiblings(new String[] { "JARLABEL" }).setParents(new String[] { "NITOR", "ALUMENTUM" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  895 */     new ResearchItem("DISTILESSENTIA2", "ALCHEMY", new AspectList().add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.CRAFT, 3), 5, -2, 1, new Object[] { new ItemStack(BlocksTC.smelterThaumium) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.DISTILESSENTIA2.1"), new ResearchPage((IArcaneRecipe)recipes.get("EssentiaSmelter2")) }).setParents(new String[] { "DISTILESSENTIA", "IMPROVED_DISTILLATION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  900 */     new ResearchItem("DISTILESSENTIA3", "ALCHEMY", new AspectList().add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.CRAFT, 3), 5, -4, 1, new Object[] { new ItemStack(BlocksTC.smelterVoid) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.DISTILESSENTIA3.1"), new ResearchPage((IArcaneRecipe)recipes.get("AdvAlchemyConstruct")), new ResearchPage((IArcaneRecipe)recipes.get("EssentiaSmelter3")) }).setHidden().setParents(new String[] { "DISTILESSENTIA2", "PRIMPEARL" }).setParentsHidden(new String[] { "VOIDMETAL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  906 */     new ResearchItem("IMPROVED_DISTILLATION", "ALCHEMY", new AspectList().add(Aspect.FIRE, 5).add(Aspect.TOOL, 3).add(Aspect.WATER, 5).add(Aspect.CRAFT, 5), 7, -2, 2, new Object[] { new ItemStack(BlocksTC.smelterAux) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.IMPROVED_DISTILLATION.1"), new ResearchPage((IArcaneRecipe)recipes.get("SmelterAux")), new ResearchPage("tc.research_page.IMPROVED_DISTILLATION.2"), new ResearchPage((IArcaneRecipe)recipes.get("SmelterVent")) }).setParents(new String[] { "TUBEFILTER" }).setFlipped().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  912 */     ArrayList<IRecipe> rc = new ArrayList();
/*  913 */     for (int a = 0; a < Aspect.aspects.values().size(); a++) {
/*  914 */       rc.add((IRecipe)recipes.get("JarLabel" + a));
/*      */     }
/*      */     
/*  917 */     new ResearchItem("JARLABEL", "ALCHEMY", new AspectList(), 5, 1, 0, new Object[] { new ItemStack(BlocksTC.jar) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.JARLABEL.1"), new ResearchPage((IArcaneRecipe)recipes.get("WardedJar")), new ResearchPage("tc.research_page.JARLABEL.2"), new ResearchPage((IRecipe)recipes.get("JarLabel")), new ResearchPage("tc.research_page.JARLABEL.3"), new ResearchPage((IRecipe[])rc.toArray(new IRecipe[0])), new ResearchPage((IRecipe)recipes.get("JarLabelNull")), new ResearchPage("tc.research_page.JARLABEL.4"), new ResearchPage((IRecipe)recipes.get("BrassBrace")) }).setParents(new String[] { "DISTILESSENTIA" }).setHidden().setStub().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  925 */     new ResearchItem("JARVOID", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.VOID, 6), 6, 3, 1, new Object[] { new ItemStack(BlocksTC.jar, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.JARVOID.1"), new ResearchPage((IArcaneRecipe)recipes.get("JarVoid")) }).setParents(new String[] { "JARLABEL" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  929 */     new ResearchItem("TUBES", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.EXCHANGE, 6), 7, 0, 1, new Object[] { new ItemStack(BlocksTC.tube) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TUBES.1"), new ResearchPage((IArcaneRecipe)recipes.get("Tube")), new ResearchPage("tc.research_page.TUBES.2"), new ResearchPage((IArcaneRecipe)recipes.get("TubeValve")), new ResearchPage("tc.research_page.TUBES.3"), new ResearchPage((IArcaneRecipe)recipes.get("Resonator")), new ResearchPage("tc.research_page.TUBES.4") }).setParents(new String[] { "DISTILESSENTIA" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  939 */     new ResearchItem("TUBEFILTER", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.EXCHANGE, 6).add(Aspect.ORDER, 3), 9, -1, 1, new Object[] { new ItemStack(BlocksTC.tube, 1, 4) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TUBEFILTER.1"), new ResearchPage((IArcaneRecipe)recipes.get("TubeFilter")), new ResearchPage("tc.research_page.TUBEFILTER.2"), new ResearchPage((IArcaneRecipe)recipes.get("TubeRestrict")), new ResearchPage((IArcaneRecipe)recipes.get("TubeOneway")), new ResearchPage("tc.research_page.TUBEFILTER.3"), new ResearchPage("tc.research_page.TUBEFILTER.4"), new ResearchPage((IArcaneRecipe)recipes.get("TubeBuffer")), new ResearchPage((IArcaneRecipe)recipes.get("AlchemicalConstruct")) }).setParents(new String[] { "TUBES" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  950 */     new ResearchItem("ESSENTIACRYSTAL", "ALCHEMY", new AspectList().add(Aspect.WATER, 5).add(Aspect.CRYSTAL, 5).add(Aspect.EXCHANGE, 3).add(Aspect.ENERGY, 5), 10, -2, 1, new Object[] { new ItemStack(BlocksTC.crystallizer) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ESSENTIACRYSTAL.1"), new ResearchPage((IArcaneRecipe)recipes.get("EssentiaCrystalizer")) }).setParents(new String[] { "TUBEFILTER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  955 */     new ResearchItem("CENTRIFUGE", "ALCHEMY", new AspectList().add(Aspect.FLUX, 3).add(Aspect.ENERGY, 3).add(Aspect.EXCHANGE, 3).add(Aspect.CRAFT, 3), 10, 0, 2, new Object[] { new ItemStack(BlocksTC.centrifuge) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CENTRIFUGE.1"), new ResearchPage((IArcaneRecipe)recipes.get("Centrifuge")) }).setParents(new String[] { "TUBEFILTER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  960 */     new ResearchItem("THAUMATORIUM", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.ENERGY, 6).add(Aspect.EXCHANGE, 3).add(Aspect.CRAFT, 3), 9, -4, 3, new Object[] { new ItemStack(BlocksTC.metal, 1, 2) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.THAUMATORIUM.1"), new ResearchPage((List)recipes.get("Thaumatorium")), new ResearchPage("tc.research_page.THAUMATORIUM.2"), new ResearchPage("tc.research_page.THAUMATORIUM.3").setRequisite("MINDCLOCKWORK"), new ResearchPage((IArcaneRecipe)recipes.get("MnemonicMatrix")).setRequisite("MINDCLOCKWORK") }).setParents(new String[] { "IMPROVED_DISTILLATION" }).setFlipped().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  968 */     new ResearchItem("ESSENTIATRANSPORT", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.MOTION, 6).add(Aspect.EXCHANGE, 3).add(Aspect.DESIRE, 3).add(Aspect.AVERSION, 3), 11, -5, 2, new Object[] { new ItemStack(BlocksTC.essentiaTransportInput), new ItemStack(BlocksTC.essentiaTransportOutput) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ESSENTIATRANSPORT.1"), new ResearchPage((IArcaneRecipe)recipes.get("EssentiaTransportIn")), new ResearchPage("tc.research_page.ESSENTIATRANSPORT.2"), new ResearchPage((IArcaneRecipe)recipes.get("EssentiaTransportOut")) }).setParents(new String[] { "THAUMATORIUM", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  974 */     new ResearchItem("WATERJUG", "ALCHEMY", new AspectList().add(Aspect.WATER, 6).add(Aspect.EARTH, 3).add(Aspect.CRAFT, 3), -5, 4, 1, new Object[] { new ItemStack(BlocksTC.waterJug) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.WATERJUG.1"), new ResearchPage((InfusionRecipe)recipes.get("WaterJug")) }).setParents(new String[] { "INFUSION", "ALCHEMICALDUPLICATION" }).registerResearchItem();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initGolemancyResearch()
/*      */   {
/*  986 */     ScanningManager.addScannableThing(new ScanEntity("BASICGOLEMANCY", net.minecraft.entity.monster.EntityGolem.class, true));
/*  987 */     ScanningManager.addScannableThing(new ScanEntity("BASICGOLEMANCY", thaumcraft.common.entities.construct.EntityOwnedConstruct.class, true));
/*      */     
/*  989 */     new ResearchItem("BASICGOLEMANCY", "GOLEMANCY", new AspectList(), 0, 0, 0, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_golemancy.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BASICGOLEMANCY.1") }).setSiblings(new String[] { "MATSTUDWOOD" }).setStub().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  993 */     new ResearchItem("HUNGRYCHEST", "GOLEMANCY", new AspectList().add(Aspect.DESIRE, 3).add(Aspect.VOID, 3), 2, -2, 1, new Object[] { new ItemStack(BlocksTC.hungryChest) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HUNGRYCHEST.1"), new ResearchPage((IArcaneRecipe)recipes.get("HungryChest")) }).setSecondary().setParents(new String[] { "BASICGOLEMANCY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  998 */     new ResearchItem("MATSTUDWOOD", "GOLEMANCY", new AspectList(), -2, -2, 0, new Object[] { new ItemStack(BlocksTC.plank) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MATSTUDWOOD.1") }).setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1002 */     new ResearchItem("MATSTUDIRON", "GOLEMANCY", new AspectList().add(Aspect.METAL, 5).add(Aspect.CRAFT, 3), -2, -4, 1, new Object[] { new ItemStack(ItemsTC.plate, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MATSTUDIRON.1"), new ResearchPage((IRecipe)recipes.get("IronPlate")) }).setParents(new String[] { "MATSTUDWOOD" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1007 */     new ResearchItem("MATSTUDCLAY", "GOLEMANCY", new AspectList().add(Aspect.EARTH, 5).add(Aspect.CRAFT, 3), -3, -3, 1, new Object[] { new ItemStack(Blocks.field_150405_ch) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MATSTUDCLAY.1") }).setParents(new String[] { "MATSTUDIRON" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1011 */     new ResearchItem("MATSTUDBRASS", "GOLEMANCY", new AspectList().add(Aspect.METAL, 3).add(Aspect.CRAFT, 3).add(Aspect.ENERGY, 5), -1, -5, 2, new Object[] { new ItemStack(ItemsTC.plate, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MATSTUDBRASS.1"), new ResearchPage((IRecipe)recipes.get("BrassGear")), new ResearchPage((IRecipe)recipes.get("BrassPlate")) }).setParents(new String[] { "MATSTUDIRON", "METALLURGY" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1016 */     new ResearchItem("MATSTUDTHAUMIUM", "GOLEMANCY", new AspectList().add(Aspect.METAL, 3).add(Aspect.CRAFT, 3).add(Aspect.ORDER, 5), -1, -7, 2, new Object[] { new ItemStack(ItemsTC.plate, 1, 2) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MATSTUDTHAUMIUM.1"), new ResearchPage((IRecipe)recipes.get("ThaumiumGear")), new ResearchPage((IRecipe)recipes.get("ThaumiumPlate")) }).setParents(new String[] { "MATSTUDBRASS", "METALLURGY" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1021 */     new ResearchItem("MATSTUDVOID", "GOLEMANCY", new AspectList().add(Aspect.METAL, 5).add(Aspect.CRAFT, 5).add(Aspect.ELDRITCH, 8), -2, -6, 3, new Object[] { new ItemStack(ItemsTC.plate, 1, 3) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MATSTUDVOID.1"), new ResearchPage((IRecipe)recipes.get("VoidGear")), new ResearchPage((IRecipe)recipes.get("VoidPlate")) }).setParents(new String[] { "MATSTUDBRASS", "MATSTUDTHAUMIUM", "VOIDMETAL" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1026 */     new ResearchItem("MINDCLOCKWORK", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.MIND, 3).add(Aspect.SENSES, 3).add(Aspect.ENERGY, 6).add(Aspect.LIFE, 3), 0, 3, 1, new Object[] { new ItemStack(ItemsTC.mind, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MINDCLOCKWORK.1"), new ResearchPage((IArcaneRecipe)recipes.get("MindClockwork")), new ResearchPage("tc.research_page.MINDCLOCKWORK.2"), new ResearchPage((List)recipes.get("GolemPress")), new ResearchPage("tc.research_page.MINDCLOCKWORK.3"), new ResearchPage("tc.research_page.MINDCLOCKWORK.4") }).setParents(new String[] { "BASICGOLEMANCY", "DISTILESSENTIA", "TALLOW", "NITOR" }).setSiblings(new String[] { "CONTROLSEALS" }).setSpecial().setHidden().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1033 */     new ResearchItem("GOLEMDIRECT", "GOLEMANCY", new AspectList().add(Aspect.SENSES, 5).add(Aspect.MIND, 5).add(Aspect.SOUL, 8), 1, 4, 3, new Object[] { new ResourceLocation("thaumcraft", "textures/misc/golem/head_basic.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMDIRECT.1") }).setParents(new String[] { "MINDCLOCKWORK" }).setSecondary().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1037 */     new ResearchItem("MINDBIOTHAUMIC", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.MIND, 3).add(Aspect.SENSES, 3).add(Aspect.UNDEAD, 3), 0, 7, 2, new Object[] { new ItemStack(ItemsTC.mind, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MINDBIOTHAUMIC.1"), new ResearchPage((InfusionRecipe)recipes.get("MindBiothaumic")), new ResearchPage("tc.research_page.MINDBIOTHAUMIC.2") }).setParents(new String[] { "MINDCLOCKWORK", "INFUSION", "!BRAIN" }).setHidden().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1042 */     ThaumcraftApi.addWarpToResearch("MINDBIOTHAUMIC", 3);
/*      */     
/* 1044 */     new ResearchItem("CONTROLSEALS", "GOLEMANCY", new AspectList(), -2, 5, 1, new Object[] { new ItemStack(ItemsTC.seals) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CONTROLSEALS.1"), new ResearchPage((IArcaneRecipe)recipes.get("SealBlank")), new ResearchPage((IArcaneRecipe)recipes.get("GolemBell")), new ResearchPage("tc.research_page.CONTROLSEALS.2"), new ResearchPage("tc.research_page.CONTROLSEALS.3"), new ResearchPage("tc.research_page.CONTROLSEALS.4") }).setParents(new String[] { "DISTILESSENTIA", "TALLOW", "NITOR" }).setSiblings(new String[] { "SEALCOLLECT" }).setHidden().setStub().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1054 */     new ResearchItem("SEALCOLLECT", "GOLEMANCY", new AspectList(), -4, 4, 1, new Object[] { GolemHelper.getSealStack("Thaumcraft:pickup") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALCOLLECT.1"), new ResearchPage((CrucibleRecipe)recipes.get("SealCollect")), new ResearchPage("tc.research_page.SEALCOLLECT.2").setRequisite("MINDBIOTHAUMIC"), new ResearchPage((CrucibleRecipe)recipes.get("SealCollectAdv")).setRequisite("MINDBIOTHAUMIC") }).setParents(new String[] { "CONTROLSEALS" }).setStub().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1060 */     new ResearchItem("SEALSTORE", "GOLEMANCY", new AspectList().add(Aspect.VOID, 3).add(Aspect.MIND, 3).add(Aspect.EXCHANGE, 5), -6, 4, 1, new Object[] { GolemHelper.getSealStack("Thaumcraft:fill") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALSTORE.1"), new ResearchPage((CrucibleRecipe)recipes.get("SealStore")), new ResearchPage("tc.research_page.SEALSTORE.2"), new ResearchPage("tc.research_page.SEALSTORE.3").setRequisite("MINDBIOTHAUMIC"), new ResearchPage((CrucibleRecipe)recipes.get("SealStoreAdv")).setRequisite("MINDBIOTHAUMIC") }).setParents(new String[] { "SEALCOLLECT" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1068 */     new ResearchItem("SEALEMPTY", "GOLEMANCY", new AspectList().add(Aspect.VOID, 3).add(Aspect.MIND, 3).add(Aspect.EXCHANGE, 5), -8, 4, 1, new Object[] { GolemHelper.getSealStack("Thaumcraft:empty") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALEMPTY.1"), new ResearchPage((CrucibleRecipe)recipes.get("SealEmpty")), new ResearchPage("tc.research_page.SEALEMPTY.2").setRequisite("MINDBIOTHAUMIC"), new ResearchPage((CrucibleRecipe)recipes.get("SealEmptyAdv")).setRequisite("MINDBIOTHAUMIC") }).setParents(new String[] { "SEALSTORE" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1075 */     new ResearchItem("SEALPROVIDE", "GOLEMANCY", new AspectList().add(Aspect.VOID, 3).add(Aspect.MIND, 5).add(Aspect.EXCHANGE, 4), -7, 6, 1, new Object[] { GolemHelper.getSealStack("Thaumcraft:provider") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALPROVIDE.1"), new ResearchPage((CrucibleRecipe)recipes.get("SealProvide")), new ResearchPage("tc.research_page.SEALPROVIDE.2") }).setParents(new String[] { "SEALEMPTY", "MINDBIOTHAUMIC" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1081 */     new ResearchItem("SEALGUARD", "GOLEMANCY", new AspectList().add(Aspect.MAN, 3).add(Aspect.MIND, 3).add(Aspect.PROTECT, 5).add(Aspect.AVERSION, 5), -12, 4, 2, new Object[] { GolemHelper.getSealStack("Thaumcraft:guard") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALGUARD.1"), new ResearchPage((IArcaneRecipe)recipes.get("ModuleAggression")), new ResearchPage((CrucibleRecipe)recipes.get("SealGuard")), new ResearchPage("tc.research_page.SEALGUARD.2").setRequisite("MINDBIOTHAUMIC"), new ResearchPage((CrucibleRecipe)recipes.get("SealGuardAdv")).setRequisite("MINDBIOTHAUMIC") }).setParents(new String[] { "CONTROLSEALS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1090 */     ThaumcraftApi.addWarpToResearch("SEALGUARD", 1);
/*      */     
/* 1092 */     new ResearchItem("SEALBUTCHER", "GOLEMANCY", new AspectList().add(Aspect.MAN, 3).add(Aspect.MIND, 3).add(Aspect.BEAST, 5), -11, 6, 2, new Object[] { GolemHelper.getSealStack("Thaumcraft:butcher") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALBUTCHER.1"), new ResearchPage((InfusionRecipe)recipes.get("SealButcher")) }).setParents(new String[] { "MINDBIOTHAUMIC", "INFUSION", "SEALGUARD" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1096 */     ThaumcraftApi.addWarpToResearch("SEALBUTCHER", 1);
/*      */     
/* 1098 */     new ResearchItem("SEALUSE", "GOLEMANCY", new AspectList().add(Aspect.MAN, 3).add(Aspect.MIND, 3).add(Aspect.CRAFT, 5), -3, 6, 1, new Object[] { GolemHelper.getSealStack("Thaumcraft:use") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALUSE.1"), new ResearchPage((CrucibleRecipe)recipes.get("SealUse")) }).setParents(new String[] { "CONTROLSEALS", "MINDBIOTHAUMIC", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1103 */     new ResearchItem("SEALHARVEST", "GOLEMANCY", new AspectList().add(Aspect.MAN, 3).add(Aspect.MIND, 3).add(Aspect.PLANT, 5), -5, 6, 2, new Object[] { GolemHelper.getSealStack("Thaumcraft:harvest") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALHARVEST.1"), new ResearchPage((InfusionRecipe)recipes.get("SealHarvest")) }).setParents(new String[] { "SEALUSE", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1108 */     new ResearchItem("SEALBREAK", "GOLEMANCY", new AspectList().add(Aspect.MAN, 3).add(Aspect.MIND, 3).add(Aspect.ENTROPY, 5).add(Aspect.TOOL, 5), -10, 4, 2, new Object[] { GolemHelper.getSealStack("Thaumcraft:breaker") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALBREAK.1"), new ResearchPage((InfusionRecipe)recipes.get("SealBreak")) }).setParents(new String[] { "CONTROLSEALS", "INFUSION", "GOLEMBREAKER" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1113 */     new ResearchItem("SEALLUMBER", "GOLEMANCY", new AspectList().add(Aspect.MAN, 3).add(Aspect.MIND, 3).add(Aspect.PLANT, 5).add(Aspect.TOOL, 5), -9, 6, 2, new Object[] { GolemHelper.getSealStack("Thaumcraft:lumber") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SEALLUMBER.1"), new ResearchPage((CrucibleRecipe)recipes.get("SealLumber")) }).setParents(new String[] { "SEALBREAK", "MINDBIOTHAUMIC" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1120 */     new ResearchItem("GOLEMVISION", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.SENSES, 5).add(Aspect.CRYSTAL, 3).add(Aspect.MIND, 3), -2, 2, 2, new Object[] { new ItemStack(ItemsTC.modules, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMVISION.1"), new ResearchPage((IArcaneRecipe)recipes.get("ModuleVision")) }).setParents(new String[] { "MINDCLOCKWORK" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1125 */     new ResearchItem("GOLEMCLIMBER", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.BEAST, 3).add(Aspect.MOTION, 5).add(Aspect.MIND, 3), -4, 2, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/misc/golem/legs_climber.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMCLIMBER.1") }).setParents(new String[] { "MINDCLOCKWORK" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1130 */     new ResearchItem("GOLEMFLYER", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.FLIGHT, 5).add(Aspect.MOTION, 3).add(Aspect.MIND, 3), -6, 2, 3, new Object[] { new ResourceLocation("thaumcraft", "textures/misc/golem/legs_flyer.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMFLYER.1") }).setParents(new String[] { "GOLEMCLIMBER", "LEVITATOR" }).setHidden().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1135 */     new ResearchItem("GOLEMBREAKER", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.ENTROPY, 3).add(Aspect.TOOL, 5).add(Aspect.MIND, 3), -8, 2, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/misc/golem/arms_breakers.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMBREAKER.1") }).setParents(new String[] { "MINDCLOCKWORK" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1140 */     new ResearchItem("GOLEMCOMBATADV", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 5).add(Aspect.AVERSION, 5).add(Aspect.PROTECT, 5).add(Aspect.MIND, 5), -12, 2, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/misc/golem/addon_fighter.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMCOMBATADV.1") }).setParents(new String[] { "SEALGUARD" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1147 */     new ResearchItem("ARCANEPATTERNCRAFTER", "GOLEMANCY", new AspectList().add(Aspect.EXCHANGE, 3).add(Aspect.VOID, 3).add(Aspect.CRAFT, 3).add(Aspect.MAN, 3), 2, 1, 1, new Object[] { new ItemStack(BlocksTC.patternCrafter) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANEPATTERNCRAFTER.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcanePatternCrafter")), new ResearchPage("tc.research_page.ARCANEPATTERNCRAFTER.2") }).setParents(new String[] { "MINDCLOCKWORK", "!fabrico" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1157 */     new ResearchItem("ARCANEBORE", "GOLEMANCY", new AspectList().add(Aspect.EARTH, 6).add(Aspect.MOTION, 3).add(Aspect.MECHANISM, 3).add(Aspect.TOOL, 3), 5, 5, 2, new Object[] { new ItemStack(BlocksTC.arcaneBore) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANEBORE.1"), new ResearchPage((InfusionRecipe)recipes.get("ArcaneBore")), new ResearchPage("tc.research_page.ARCANEBORE.2"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneBoreBase")), new ResearchPage("tc.research_page.ARCANEBORE.3") }).setParents(new String[] { "INFUSION", "BASICTURRET", "FOCUSEXCAVATION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1164 */     new ResearchItem("BASICTURRET", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AVERSION, 3).add(Aspect.SENSES, 3).add(Aspect.MIND, 3), 3, 3, 1, new Object[] { new ItemStack(ItemsTC.turretPlacer, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BASICTURRET.1"), new ResearchPage((IArcaneRecipe)recipes.get("AutomatedCrossbow")) }).setHidden().setParents(new String[] { "MINDCLOCKWORK", "!DISPENSER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1169 */     ScanningManager.addScannableThing(new ScanBlock("!DISPENSER", Blocks.field_150367_z));
/* 1170 */     ScanningManager.addScannableThing(new ScanItem("!DISPENSER", new ItemStack(Blocks.field_150367_z)));
/*      */     
/* 1172 */     new ResearchItem("FOCUSTURRET", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AVERSION, 3).add(Aspect.SENSES, 3).add(Aspect.MIND, 3).add(Aspect.AURA, 3), 3, 7, 2, new Object[] { new ItemStack(ItemsTC.turretPlacer, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSTURRET.1"), new ResearchPage((InfusionRecipe)recipes.get("AutoCaster")) }).setParents(new String[] { "MINDBIOTHAUMIC", "ARCANEBORE" }).setFlipped().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1177 */     new ResearchItem("ADVANCEDTURRET", "GOLEMANCY", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AVERSION, 3).add(Aspect.SENSES, 3).add(Aspect.MIND, 3), 3, 5, 2, new Object[] { new ItemStack(ItemsTC.turretPlacer, 1, 3) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ADVANCEDTURRET.1"), new ResearchPage((IArcaneRecipe)recipes.get("AdvancedCrossbow")) }).setSecondary().setParents(new String[] { "MINDBIOTHAUMIC", "BASICTURRET" }).registerResearchItem();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initBasicResearch()
/*      */   {
/* 1210 */     new ResearchItem("ASPECTS", "BASICS", new AspectList(), 0, 0, 0, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_aspects.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ASPECTS.1"), new ResearchPage("tc.research_page.ASPECTS.2"), new ResearchPage("tc.research_page.ASPECTS.3") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1214 */     new ResearchItem("PECH", "BASICS", new AspectList(), -4, -4, 0, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_pech.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PECH.1"), new ResearchPage("tc.research_page.PECH.2") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1218 */     new ResearchItem("NODES", "BASICS", new AspectList(), -2, 0, 0, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_nodes.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODES.1"), new ResearchPage("tc.research_page.NODES.2"), new ResearchPage("tc.research_page.NODES.3"), new ResearchPage("tc.research_page.NODES.4"), new ResearchPage("tc.research_page.NODES.A").setRequisite("!NODE_ASTRAL"), new ResearchPage("tc.research_page.NODES.D").setRequisite("!NODE_DARK"), new ResearchPage("tc.research_page.NODES.H").setRequisite("!NODE_HUNGRY"), new ResearchPage("tc.research_page.NODES.P").setRequisite("!NODE_PURE"), new ResearchPage("tc.research_page.NODES.T").setRequisite("!NODE_TAINT"), new ResearchPage("tc.research_page.NODES.U").setRequisite("!NODE_UNSTABLE") }).setStub().setRound().setAutoUnlock().setSiblings(new String[] { "ASPECTS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1229 */     ScanningManager.addScannableThing(new ScanEntity("!NODE_DARK", EntityAuraNode.class, true, new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("type", Byte.valueOf(1)) }));
/* 1230 */     ScanningManager.addScannableThing(new ScanEntity("!NODE_HUNGRY", EntityAuraNode.class, true, new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("type", Byte.valueOf(2)) }));
/* 1231 */     ScanningManager.addScannableThing(new ScanEntity("!NODE_PURE", EntityAuraNode.class, true, new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("type", Byte.valueOf(3)) }));
/* 1232 */     ScanningManager.addScannableThing(new ScanEntity("!NODE_TAINT", EntityAuraNode.class, true, new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("type", Byte.valueOf(4)) }));
/* 1233 */     ScanningManager.addScannableThing(new ScanEntity("!NODE_UNSTABLE", EntityAuraNode.class, true, new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("type", Byte.valueOf(5)) }));
/* 1234 */     ScanningManager.addScannableThing(new ScanEntity("!NODE_ASTRAL", EntityAuraNode.class, true, new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("type", Byte.valueOf(6)) }));
/*      */     
/* 1236 */     new ResearchItem("WARP", "BASICS", new AspectList(), 0, 2, 0, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_warp.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.WARP.1"), new ResearchPage("tc.research_page.WARP.2"), new ResearchPage("tc.research_page.WARP.3") }).setStub().setRound().setAutoUnlock().setSiblings(new String[] { "ASPECTS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1240 */     new ResearchItem("RESEARCH", "BASICS", new AspectList(), 2, 0, 0, new Object[] { new ItemStack(ItemsTC.scribingTools) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESEARCH.1"), new ResearchPage("tc.research_page.RESEARCH.2"), new ResearchPage("tc.research_page.RESEARCH.3"), new ResearchPage("tc.research_page.RESEARCH.4"), new ResearchPage("tc.research_page.RESEARCH.5"), new ResearchPage("tc.research_page.RESEARCH.6"), new ResearchPage("tc.research_page.RESEARCH.7"), new ResearchPage("tc.research_page.RESEARCH.8"), new ResearchPage("tc.research_page.RESEARCH.9"), new ResearchPage("tc.research_page.RESEARCH.10"), new ResearchPage("tc.research_page.RESEARCH.11"), new ResearchPage((IRecipe)recipes.get("Thaumometer")), new ResearchPage("tc.research_page.RESEARCH.12"), new ResearchPage((IRecipe)recipes.get("Scribe1")), new ResearchPage((IRecipe)recipes.get("Scribe2")), new ResearchPage((IRecipe)recipes.get("Scribe3")) }).setAutoUnlock().setStub().setRound().setSiblings(new String[] { "ASPECTS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1260 */     new ResearchItem("KNOWFRAG", "BASICS", new AspectList(), 3, -2, 0, new Object[] { new ItemStack(ItemsTC.knowledgeFragment) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.KNOWFRAG.1") }).setStub().setRound().setAutoUnlock().setSiblings(new String[] { "RESEARCH" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1264 */     new ResearchItem("THAUMONOMICON", "BASICS", new AspectList(), 1, -2, 0, new Object[] { new ItemStack(ItemsTC.thaumonomicon) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.THAUMONOMICON.1"), new ResearchPage((List)recipes.get("Thaumonomicon")) }).setStub().setRound().setAutoUnlock().setSiblings(new String[] { "RESEARCH" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1268 */     new ResearchItem("ORE", "BASICS", new AspectList(), -2, -4, 0, new Object[] { new ItemStack(BlocksTC.oreCinnabar) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ORE.1"), new ResearchPage("tc.research_page.ORE.2"), new ResearchPage("tc.research_page.ORE.3"), new ResearchPage("tc.research_page.ORE.4") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1276 */     ArrayList<InfusionRecipe> rc = new ArrayList();
/* 1277 */     rc.add((InfusionRecipe)recipes.get("ClusterAir"));
/* 1278 */     rc.add((InfusionRecipe)recipes.get("ClusterFire"));
/* 1279 */     rc.add((InfusionRecipe)recipes.get("ClusterWater"));
/* 1280 */     rc.add((InfusionRecipe)recipes.get("ClusterEarth"));
/* 1281 */     rc.add((InfusionRecipe)recipes.get("ClusterOrder"));
/* 1282 */     rc.add((InfusionRecipe)recipes.get("ClusterEntropy"));
/* 1283 */     rc.add((InfusionRecipe)recipes.get("ClusterFlux"));
/*      */     
/* 1285 */     new ResearchItem("CRYSTALFARMER", "BASICS", new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FLUX, 3).add(Aspect.AIR, 3).add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.EARTH, 3).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), -1, -5, 2, new Object[] { new ItemStack(BlocksTC.crystalAir), new ItemStack(BlocksTC.crystalFire), new ItemStack(BlocksTC.crystalWater), new ItemStack(BlocksTC.crystalEarth), new ItemStack(BlocksTC.crystalOrder), new ItemStack(BlocksTC.crystalEntropy) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CRYSTALFARMER.1"), new ResearchPage((InfusionRecipe[])rc.toArray(new InfusionRecipe[0])) }).setHidden().setParents(new String[] { "INFUSION", "ORE", "!vitreus" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1292 */     new ResearchItem("PLANTS", "BASICS", new AspectList(), -2, -2, 0, new Object[] { new ItemStack(BlocksTC.shimmerleaf, 1, 0) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PLANTS.1"), new ResearchPage((IRecipe)recipes.get("PlankGreatwood")), new ResearchPage("tc.research_page.PLANTS.2"), new ResearchPage((IRecipe)recipes.get("PlankSilverwood")), new ResearchPage("tc.research_page.PLANTS.3"), new ResearchPage("tc.research_page.PLANTS.4"), new ResearchPage("tc.research_page.PLANTS.5") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1303 */     new ResearchItem("ENCHANT", "BASICS", new AspectList(), -4, -2, 0, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_enchant.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENCHANT.1"), new ResearchPage("tc.research_page.ENCHANT.2") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1313 */     new ResearchItem("RESEARCHER1", "BASICS", new AspectList().add(Aspect.MIND, 3).add(Aspect.SENSES, 3).add(Aspect.ORDER, 3), 4, 1, 1, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_researcher1.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESEARCHER1.1") }).setRound().setParents(new String[] { "RESEARCH" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1320 */     new ResearchItem("RESEARCHER2", "BASICS", new AspectList().add(Aspect.MIND, 6).add(Aspect.ORDER, 3).add(Aspect.SENSES, 3).add(Aspect.ENERGY, 3), 3, 3, 2, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_researcher2.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESEARCHER2.1") }).setRound().setSpecial().setParents(new String[] { "RESEARCHER1" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1328 */     ThaumcraftApi.addWarpToResearch("RESEARCHER2", 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initEldritchResearch()
/*      */   {
/* 1336 */     new ResearchItem("CRIMSON", "ELDRITCH", new AspectList(), 0, 2, 0, new Object[] { new ItemStack(ItemsTC.crimsonRites) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CRIMSON.1") }).setStub().setHidden().setRound().setSiblings(new String[] { "ELDRITCHMINOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1340 */     ThaumcraftApi.addWarpToResearch("CRIMSON", 3);
/*      */     
/* 1342 */     new ResearchItem("ELDRITCHMINOR", "ELDRITCH", new AspectList(), 1, 0, 0, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_eldritchminor.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELDRITCHMINOR.1"), new ResearchPage((CrucibleRecipe)recipes.get("VoidSeed")) }).setStub().setHidden().setRound().setSpecial().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1346 */     new ResearchItem("ELDRITCHMAJOR", "ELDRITCH", new AspectList(), -1, 0, 0, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_eldritchmajor.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELDRITCHMAJOR.1"), new ResearchPage("tc.research_page.ELDRITCHMAJOR.2") }).setStub().setHidden().setRound().setSpecial().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1350 */     new ResearchItem("OCULUS", "ELDRITCH", new AspectList().add(Aspect.MIND, 3).add(Aspect.DARKNESS, 3).add(Aspect.EXCHANGE, 3).add(Aspect.MOTION, 6).add(Aspect.ELDRITCH, 6), -2, 3, 1, new Object[] { new ItemStack(ItemsTC.eldritchEye) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.OCULUS.1"), new ResearchPage((InfusionRecipe)recipes.get("EldritchEye")), new ResearchPage("tc.research_page.OCULUS.2") }).setRound().setHidden().setParents(new String[] { "CRIMSON", "ELDRITCHMAJOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1355 */     ThaumcraftApi.addWarpToResearch("OCULUS", 6);
/*      */     
/* 1357 */     new ResearchItem("SINSTONE", "ELDRITCH", new AspectList().add(Aspect.SENSES, 3).add(Aspect.DARKNESS, 3).add(Aspect.ELDRITCH, 3).add(Aspect.AURA, 3), -1, 4, 1, new Object[] { new ItemStack(ItemsTC.sinisterStone) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SINSTONE.1"), new ResearchPage((InfusionRecipe)recipes.get("SinStone")) }).setParents(new String[] { "GOGGLES", "INFUSION", "OCULUS" }).setHidden().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1361 */     ThaumcraftApi.addWarpToResearch("SINSTONE", 2);
/* 1362 */     ThaumcraftApi.addWarpToItem(new ItemStack(ItemsTC.sinisterStone), 1);
/*      */     
/* 1364 */     new ResearchItem("ENTEROUTER", "ELDRITCH", new AspectList(), -3, 4, 1, new Object[] { new ResourceLocation("thaumcraft", "textures/research/r_outer.png") }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENTEROUTER.1") }).setStub().setHidden().setRound().setParents(new String[] { "OCULUS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1368 */     new ResearchItem("OUTERREV", "ELDRITCH", new AspectList(), -4, 5, 0, new Object[] { new ItemStack(ItemsTC.runedTablet) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.OUTERREV.1") }).setSecondary().setHidden().setSpecial().setParents(new String[] { "ENTEROUTER", "!ORBOSS", "!ORMOB", "!ORBLOCK1", "!ORBLOCK2", "!ORBLOCK3" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1373 */     ScanningManager.addScannableThing(new ScanEntity("!ORMOB", IEldritchMob.class, true));
/* 1374 */     ScanningManager.addScannableThing(new ScanEntity("!ORBOSS", thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss.class, true));
/* 1375 */     ScanningManager.addScannableThing(new ScanBlockState("!ORBLOCK1", BlocksTC.stone.func_176203_a(2), true));
/* 1376 */     ScanningManager.addScannableThing(new ScanBlockState("!ORBLOCK2", BlocksTC.stone.func_176203_a(4), true));
/* 1377 */     ScanningManager.addScannableThing(new ScanBlockState("!ORBLOCK3", BlocksTC.stone.func_176203_a(11), true));
/*      */     
/* 1379 */     new ResearchItem("PRIMPEARL", "ELDRITCH", new AspectList().add(Aspect.ENERGY, 9).add(Aspect.ELDRITCH, 9).add(Aspect.ORDER, 9), 2, 3, 2, new Object[] { new ItemStack(ItemsTC.primordialPearl) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMPEARL.1"), new ResearchPage("tc.research_page.PRIMPEARL.2") }).setHidden().setParents(new String[] { "ELDRITCHMINOR", "!PRIMPEARL" }).registerResearchItem();
/*      */     
/*      */ 
/* 1382 */     ScanningManager.addScannableThing(new ScanItem("!PRIMPEARL", new ItemStack(ItemsTC.primordialPearl)));
/*      */     
/* 1384 */     new ResearchItem("PRIMALCRUSHER", "ELDRITCH", new AspectList().add(Aspect.EARTH, 6).add(Aspect.TOOL, 6).add(Aspect.ENTROPY, 6).add(Aspect.VOID, 6).add(Aspect.AVERSION, 6).add(Aspect.ELDRITCH, 6).add(Aspect.DESIRE, 6), 1, 5, 2, new Object[] { new ItemStack(ItemsTC.primalCrusher) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMALCRUSHER.1"), new ResearchPage((InfusionRecipe)recipes.get("PrimalCrusher")), new ResearchPage("tc.research_page.PRIMALCRUSHER.2") }).setHidden().setParents(new String[] { "PRIMPEARL" }).setParentsHidden(new String[] { "VOIDMETAL", "ELEMENTALTOOLS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1392 */     new ResearchItem("SANITYCHECK", "ELDRITCH", new AspectList().add(Aspect.MIND, 5).add(Aspect.ELDRITCH, 3).add(Aspect.SENSES, 5), 3, -1, 1, new Object[] { new ItemStack(ItemsTC.sanityChecker) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SANITYCHECK.1"), new ResearchPage((InfusionRecipe)recipes.get("SanityCheck")) }).setParents(new String[] { "ELDRITCHMINOR", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1398 */     new ResearchItem("VOIDMETAL", "ELDRITCH", new AspectList().add(Aspect.METAL, 3).add(Aspect.ELDRITCH, 3).add(Aspect.VOID, 5).merge(Aspect.FLUX, 3), 1, -2, 2, new Object[] { new ItemStack(ItemsTC.ingots, 1, 1) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.VOIDMETAL.1"), new ResearchPage((CrucibleRecipe)recipes.get("VoidMetal")), new ResearchPage("tc.research_page.VOIDMETAL.2"), new ResearchPage((IRecipe)recipes.get("VoidGear")), new ResearchPage((IRecipe)recipes.get("VoidPlate")), new ResearchPage((IRecipe)recipes.get("VoidAxe")), new ResearchPage((IRecipe)recipes.get("VoidSword")), new ResearchPage((IRecipe)recipes.get("VoidPick")), new ResearchPage((IRecipe)recipes.get("VoidShovel")), new ResearchPage((IRecipe)recipes.get("VoidHoe")), new ResearchPage((IRecipe)recipes.get("VoidHelm")), new ResearchPage((IRecipe)recipes.get("VoidChest")), new ResearchPage((IRecipe)recipes.get("VoidLegs")), new ResearchPage((IRecipe)recipes.get("VoidBoots")) }).setParents(new String[] { "METALLURGY", "ELDRITCHMINOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1410 */     new ResearchItem("CAP_void", "ELDRITCH", new AspectList().add(Aspect.VOID, 5).add(Aspect.ELDRITCH, 5).add(Aspect.TOOL, 3).add(Aspect.ENERGY, 3).add(Aspect.AURA, 3), 3, -3, 3, new Object[] { new ItemStack(ItemsTC.wandCaps, 1, 6) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_void.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapVoidInert")), new ResearchPage((InfusionRecipe)recipes.get("WandCapVoid")) }).setHidden().setParents(new String[] { "CAP_thaumium", "VOIDMETAL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1414 */     ThaumcraftApi.addWarpToResearch("CAP_void", 1);
/*      */     
/*      */ 
/*      */ 
/* 1418 */     new ResearchItem("ARMORVOIDFORTRESS", "ELDRITCH", new AspectList().add(Aspect.PROTECT, 3).add(Aspect.VOID, 6).add(Aspect.ELDRITCH, 9).add(Aspect.SOUL, 6), -1, -3, 2, new Object[] { new ItemStack(ItemsTC.voidRobeHelm) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARMORVOIDFORTRESS.1"), new ResearchPage((InfusionRecipe)recipes.get("VoidRobeHelm")), new ResearchPage((InfusionRecipe)recipes.get("VoidRobeChest")), new ResearchPage((InfusionRecipe)recipes.get("VoidRobeLegs")) }).setParents(new String[] { "VOIDMETAL", "ENCHFABRIC", "ELDRITCHMAJOR" }).setHidden().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1425 */     new ResearchItem("FOCUSPRIMAL", "ELDRITCH", new AspectList().add(Aspect.AIR, 6).add(Aspect.WATER, 6).add(Aspect.FIRE, 6).add(Aspect.EARTH, 6).add(Aspect.ORDER, 6).add(Aspect.ENTROPY, 6).add(Aspect.ENERGY, 6), 4, 1, 2, new Object[] { new ItemStack(ItemsTC.focusPrimal) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSPRIMAL.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusPrimal")) }).setHidden().setParents(new String[] { "ELDRITCHMINOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/* 1429 */     ThaumcraftApi.addWarpToResearch("FOCUSPRIMAL", 2);
/* 1430 */     ThaumcraftApi.addWarpToItem(new ItemStack(ItemsTC.focusPrimal), 1);
/*      */     
/*      */ 
/*      */ 
/* 1434 */     new ResearchItem("ROD_primal_staff", "ELDRITCH", new AspectList().add(Aspect.AIR, 9).add(Aspect.EARTH, 9).add(Aspect.FIRE, 9).add(Aspect.WATER, 9).add(Aspect.ORDER, 9).add(Aspect.ENTROPY, 9).add(Aspect.TOOL, 9).add(Aspect.ENERGY, 12), 6, 2, 3, new Object[] { new ItemStack(ItemsTC.wandRods, 1, 16) }).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_primal_staff.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodPrimalStaff")) }).setParents(new String[] { "FOCUSPRIMAL" }).setParentsHidden(new String[] { "ROD_silverwood_staff", "ROD_bone_staff", "ROD_greatwood_staff", "ROD_blaze_staff", "ROD_reed_staff", "ROD_obsidian_staff", "ROD_quartz_staff", "ROD_ice_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1440 */     ThaumcraftApi.addWarpToResearch("ROD_primal_staff", 3);
/* 1441 */     ThaumcraftApi.addWarpToItem(new ItemStack(ItemsTC.wandRods, 1, 16), 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 1447 */   public static HashMap<String, Object> recipes = new HashMap();
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/config/ConfigResearch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */