/*      */ package thaumcraft.common.config;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockCauldron;
/*      */ import net.minecraft.block.BlockPistonBase;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.CraftingManager;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.nbt.NBTTagByte;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagInt;
/*      */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*      */ import net.minecraftforge.oredict.RecipeSorter;
/*      */ import net.minecraftforge.oredict.RecipeSorter.Category;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*      */ import thaumcraft.api.blocks.BlocksTC;
/*      */ import thaumcraft.api.crafting.ShapelessArcaneRecipe;
/*      */ import thaumcraft.api.golems.GolemHelper;
/*      */ import thaumcraft.api.items.ItemsTC;
/*      */ import thaumcraft.api.wands.IWand;
/*      */ import thaumcraft.api.wands.WandCap;
/*      */ import thaumcraft.api.wands.WandRod;
/*      */ import thaumcraft.api.wands.WandTriggerRegistry;
/*      */ import thaumcraft.common.CommonProxy;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.items.wands.WandManager;
/*      */ import thaumcraft.common.lib.crafting.ArcaneSceptreRecipe;
/*      */ import thaumcraft.common.lib.crafting.ArcaneWandRecipe;
/*      */ import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.RecipeTripleMeatTreat;
/*      */ import thaumcraft.common.lib.crafting.RecipesRobeArmorDyes;
/*      */ import thaumcraft.common.lib.crafting.RecipesVoidRobeArmorDyes;
/*      */ import thaumcraft.common.lib.crafting.ShapelessNBTOreRecipe;
/*      */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*      */ import thaumcraft.common.lib.utils.Utils;
/*      */ 
/*      */ public class ConfigRecipes
/*      */ {
/*   50 */   static ItemStack basicWand = new ItemStack(ItemsTC.wand);
/*      */   
/*      */   public static void init()
/*      */   {
/*   54 */     ((IWand)basicWand.func_77973_b()).setCap(basicWand, ConfigItems.WAND_CAP_IRON);
/*   55 */     ((IWand)basicWand.func_77973_b()).setRod(basicWand, ConfigItems.WAND_ROD_WOOD);
/*      */     
/*   57 */     initializeSmelting();
/*   58 */     initializeNormalRecipes();
/*   59 */     initializeArcaneRecipes();
/*   60 */     initializeInfusionRecipes();
/*   61 */     initializeAlchemyRecipes();
/*   62 */     initializeCompoundRecipes();
/*      */     
/*   64 */     RecipeSorter.register("forge:shapelessorenbt", ShapelessNBTOreRecipe.class, RecipeSorter.Category.SHAPELESS, "after:forge:shapelessore");
/*   65 */     RecipeSorter.register("forge:robearmordye", RecipesRobeArmorDyes.class, RecipeSorter.Category.SHAPELESS, "after:forge:shapelessorenbt");
/*   66 */     RecipeSorter.register("forge:shapelessarcanerecipe", ShapelessArcaneRecipe.class, RecipeSorter.Category.SHAPELESS, "after:forge:shapelessorenbt");
/*   67 */     RecipeSorter.register("forge:voidrobearmordye", RecipesVoidRobeArmorDyes.class, RecipeSorter.Category.SHAPELESS, "after:forge:robearmordye");
/*   68 */     RecipeSorter.register("forge:triplemeattreat", RecipeTripleMeatTreat.class, RecipeSorter.Category.SHAPELESS, "after:forge:voidrobearmordye");
/*   69 */     RecipeSorter.register("forge:arcanerecipe", thaumcraft.api.crafting.ShapedArcaneRecipe.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
/*   70 */     RecipeSorter.register("forge:arcanewand", ArcaneWandRecipe.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
/*   71 */     RecipeSorter.register("forge:arcanesceptre", ArcaneSceptreRecipe.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
/*      */   }
/*      */   
/*      */ 
/*      */   private static void initializeCompoundRecipes()
/*      */   {
/*   77 */     ItemStack empty = new ItemStack(BlocksTC.translucent, 1, 2);
/*      */     
/*      */ 
/*   80 */     ConfigResearch.recipes.put("InfernalFurnace", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Arrays.asList(new ItemStack[] { new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), empty, new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Items.field_151129_at), new ItemStack(Blocks.field_150411_aY), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj) }) }));
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
/*   93 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 2, Blocks.field_150343_Z.func_176223_P(), "Thaumcraft");
/*   94 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 2, Blocks.field_150385_bj.func_176223_P(), "Thaumcraft");
/*   95 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 2, Blocks.field_150411_aY.func_176223_P(), "Thaumcraft");
/*      */     
/*   97 */     ConfigResearch.recipes.put("InfusionAltar", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 75).add(Aspect.EARTH, 75).add(Aspect.ORDER, 75).add(Aspect.AIR, 75).add(Aspect.ENTROPY, 75).add(Aspect.WATER, 75), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Arrays.asList(new ItemStack[] { empty, null, empty, null, new ItemStack(BlocksTC.infusionMatrix), null, empty, null, empty, new ItemStack(BlocksTC.stone, 1, 0), null, new ItemStack(BlocksTC.stone, 1, 0), null, null, null, new ItemStack(BlocksTC.stone, 1, 0), null, new ItemStack(BlocksTC.stone, 1, 0), new ItemStack(BlocksTC.stone, 1, 0), null, new ItemStack(BlocksTC.stone, 1, 0), null, new ItemStack(BlocksTC.pedestal, 1, 0), null, new ItemStack(BlocksTC.stone, 1, 0), null, new ItemStack(BlocksTC.stone, 1, 0) }) }));
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
/*  111 */     ConfigResearch.recipes.put("InfusionAltarAncient", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 75).add(Aspect.EARTH, 75).add(Aspect.ORDER, 75).add(Aspect.AIR, 75).add(Aspect.ENTROPY, 75).add(Aspect.WATER, 75), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Arrays.asList(new ItemStack[] { empty, null, empty, null, new ItemStack(BlocksTC.infusionMatrix), null, empty, null, empty, new ItemStack(BlocksTC.stone, 1, 2), null, new ItemStack(BlocksTC.stone, 1, 2), null, null, null, new ItemStack(BlocksTC.stone, 1, 2), null, new ItemStack(BlocksTC.stone, 1, 2), new ItemStack(BlocksTC.stone, 1, 2), null, new ItemStack(BlocksTC.stone, 1, 2), null, new ItemStack(BlocksTC.pedestal, 1, 2), null, new ItemStack(BlocksTC.stone, 1, 2), null, new ItemStack(BlocksTC.stone, 1, 2) }) }));
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
/*  125 */     ConfigResearch.recipes.put("InfusionAltarEldritch", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 75).add(Aspect.EARTH, 75).add(Aspect.ORDER, 125).add(Aspect.AIR, 75).add(Aspect.ENTROPY, 75).add(Aspect.WATER, 75), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Arrays.asList(new ItemStack[] { empty, null, empty, null, new ItemStack(BlocksTC.infusionMatrix), null, empty, null, empty, new ItemStack(BlocksTC.stone, 1, 4), null, new ItemStack(BlocksTC.stone, 1, 4), null, null, null, new ItemStack(BlocksTC.stone, 1, 4), null, new ItemStack(BlocksTC.stone, 1, 4), new ItemStack(BlocksTC.stone, 1, 4), null, new ItemStack(BlocksTC.stone, 1, 4), null, new ItemStack(BlocksTC.pedestal, 1, 1), null, new ItemStack(BlocksTC.stone, 1, 4), null, new ItemStack(BlocksTC.stone, 1, 4) }) }));
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
/*  139 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 3, BlocksTC.infusionMatrix.func_176223_P(), "Thaumcraft");
/*      */     
/*      */ 
/*  142 */     ConfigResearch.recipes.put("Thaumatorium", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 50).add(Aspect.ORDER, 100).add(Aspect.WATER, 100), Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(1), Arrays.asList(new ItemStack[] { new ItemStack(BlocksTC.metal, 1, 2), new ItemStack(BlocksTC.metal, 1, 2), new ItemStack(BlocksTC.crucible) }) }));
/*      */     
/*  144 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 4, BlocksTC.metal.func_176203_a(2), "Thaumcraft");
/*  145 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 5, BlocksTC.crucible.func_176223_P(), "Thaumcraft");
/*      */     
/*      */ 
/*  148 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 6, BlocksTC.eldritch.func_176203_a(0), "Thaumcraft");
/*      */     
/*      */ 
/*      */ 
/*  152 */     ConfigResearch.recipes.put("GolemPress", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 50).add(Aspect.ORDER, 50).add(Aspect.AIR, 50), Integer.valueOf(2), Integer.valueOf(2), Integer.valueOf(2), Arrays.asList(new ItemStack[] { empty, new ItemStack(Blocks.field_150411_aY), empty, empty, new ItemStack(Items.field_151066_bu), new ItemStack(Blocks.field_150331_J), new ItemStack(Blocks.field_150467_bQ), new ItemStack(BlocksTC.tableStone) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  159 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 7, Blocks.field_150331_J.func_176223_P().func_177226_a(BlockPistonBase.field_176321_a, net.minecraft.util.EnumFacing.UP), "Thaumcraft");
/*      */     
/*  161 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 8, Blocks.field_150383_bp.func_176223_P(), "Thaumcraft");
/*  162 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 9, BlocksTC.tableStone.func_176223_P(), "Thaumcraft");
/*  163 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 10, Blocks.field_150411_aY.func_176223_P(), "Thaumcraft");
/*      */     
/*      */ 
/*  166 */     ConfigResearch.recipes.put("Thaumonomicon", Arrays.asList(new Object[] { new AspectList(), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Arrays.asList(new ItemStack[] { basicWand, new ItemStack(Blocks.field_150342_X) }) }));
/*  167 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 0, Blocks.field_150342_X.func_176223_P(), "Thaumcraft");
/*      */     
/*  169 */     ConfigResearch.recipes.put("ArcTable", Arrays.asList(new Object[] { new AspectList(), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Arrays.asList(new ItemStack[] { basicWand, new ItemStack(BlocksTC.tableWood) }) }));
/*      */     
/*  171 */     ConfigResearch.recipes.put("ResTable", Arrays.asList(new Object[] { new AspectList(), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Arrays.asList(new ItemStack[] { new ItemStack(ItemsTC.scribingTools), new ItemStack(BlocksTC.tableWood) }) }));
/*      */     
/*  173 */     ConfigResearch.recipes.put("Crucible", Arrays.asList(new Object[] { new AspectList(), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Arrays.asList(new ItemStack[] { basicWand, new ItemStack(Items.field_151066_bu) }) }));
/*  174 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager.triggers, 1, Blocks.field_150383_bp.func_176223_P(), "Thaumcraft");
/*      */   }
/*      */   
/*      */   private static void initializeAlchemyRecipes()
/*      */   {
/*  179 */     Aspect[] aspect = { Aspect.AIR, Aspect.FIRE, Aspect.WATER, Aspect.EARTH, Aspect.ORDER, Aspect.ENTROPY };
/*      */     
/*  181 */     for (int a = 0; a < 6; a++) {
/*  182 */       AspectList al = new AspectList();
/*  183 */       for (int b = 0; b < 6; b++) {
/*  184 */         if (b != a)
/*  185 */           al.add(aspect[b], 2);
/*      */       }
/*  187 */       ConfigResearch.recipes.put("BalancedShard_" + a, ThaumcraftApi.addCrucibleRecipe("CRUCIBLE", new ItemStack(ItemsTC.shard, 1, 7), new ItemStack(ItemsTC.shard, 1, a), al));
/*      */     }
/*      */     
/*      */ 
/*  191 */     ConfigResearch.recipes.put("Alumentum", ThaumcraftApi.addCrucibleRecipe("ALUMENTUM", new ItemStack(ItemsTC.alumentum), new ItemStack(Items.field_151044_h, 1, 32767), new AspectList().merge(Aspect.ENERGY, 3).merge(Aspect.FIRE, 3).merge(Aspect.ENTROPY, 3)));
/*      */     
/*      */ 
/*      */ 
/*  195 */     ConfigResearch.recipes.put("Nitor", ThaumcraftApi.addCrucibleRecipe("NITOR", new ItemStack(BlocksTC.nitor, 1, 4), "dustGlowstone", new AspectList().merge(Aspect.ENERGY, 3).merge(Aspect.FIRE, 3).merge(Aspect.LIGHT, 3)));
/*      */     
/*  197 */     String[] dyes = { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White" };
/*      */     
/*      */ 
/*  200 */     for (int a = 0; a < 16; a++) {
/*  201 */       shapelessOreDictRecipe(new ItemStack(BlocksTC.nitor, 1, a), new Object[] { "dye" + dyes[(15 - a)], "nitor" });
/*      */     }
/*  203 */     ConfigResearch.recipes.put("Thaumium", ThaumcraftApi.addCrucibleRecipe("METALLURGY", new ItemStack(ItemsTC.ingots, 1, 0), "ingotIron", new AspectList().merge(Aspect.EARTH, 2).merge(Aspect.ORDER, 2)));
/*      */     
/*      */ 
/*  206 */     ConfigResearch.recipes.put("AlchemicalBrass", ThaumcraftApi.addCrucibleRecipe("METALLURGY", new ItemStack(ItemsTC.ingots, 1, 2), "ingotIron", new AspectList().merge(Aspect.ENERGY, 1).merge(Aspect.WATER, 1)));
/*      */     
/*      */ 
/*  209 */     ConfigResearch.recipes.put("VoidMetal", ThaumcraftApi.addCrucibleRecipe("VOIDMETAL", new ItemStack(ItemsTC.ingots, 1, 1), new ItemStack(ItemsTC.voidSeed), new AspectList().merge(Aspect.METAL, 7).merge(Aspect.FLUX, 1)));
/*      */     
/*      */ 
/*  212 */     ConfigResearch.recipes.put("VoidSeed", ThaumcraftApi.addCrucibleRecipe("ELDRITCHMINOR", new ItemStack(ItemsTC.voidSeed), new ItemStack(Items.field_151014_N), new AspectList().merge(Aspect.DARKNESS, 8).merge(Aspect.VOID, 8).merge(Aspect.ELDRITCH, 2)));
/*      */     
/*      */ 
/*  215 */     ConfigResearch.recipes.put("Tallow", ThaumcraftApi.addCrucibleRecipe("TALLOW", new ItemStack(ItemsTC.tallow), new ItemStack(Items.field_151078_bh), new AspectList().merge(Aspect.FIRE, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  220 */     ConfigResearch.recipes.put("AltGunpowder", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151016_H, 2, 0), new ItemStack(Items.field_151016_H), new AspectList().merge(Aspect.FIRE, 4).merge(Aspect.ENTROPY, 4)));
/*      */     
/*      */ 
/*  223 */     ConfigResearch.recipes.put("AltSlime", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151123_aH, 2, 0), new ItemStack(Items.field_151123_aH), new AspectList().merge(Aspect.WATER, 2).merge(Aspect.LIFE, 1)));
/*      */     
/*      */ 
/*  226 */     ConfigResearch.recipes.put("AltGlowstone", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151114_aO, 2, 0), "dustGlowstone", new AspectList().merge(Aspect.LIGHT, 3).merge(Aspect.SENSES, 1)));
/*      */     
/*      */ 
/*  229 */     ConfigResearch.recipes.put("AltInk", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151100_aR, 2, 0), new ItemStack(Items.field_151100_aR, 1, 0), new AspectList().merge(Aspect.WATER, 2).merge(Aspect.SENSES, 2)));
/*      */     
/*      */ 
/*  232 */     ConfigResearch.recipes.put("AltPrisCrys", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_179563_cD, 2, 0), new ItemStack(Items.field_179563_cD), new AspectList().merge(Aspect.WATER, 1).merge(Aspect.CRYSTAL, 2).merge(Aspect.LIGHT, 1)));
/*      */     
/*      */ 
/*  235 */     ConfigResearch.recipes.put("AltPrisShard", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_179562_cC, 2, 0), new ItemStack(Items.field_179562_cC), new AspectList().merge(Aspect.WATER, 2).merge(Aspect.EARTH, 1)));
/*      */     
/*      */ 
/*      */ 
/*  239 */     ConfigResearch.recipes.put("AltClay", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Items.field_151119_aD, 1, 0), new ItemStack(Blocks.field_150346_d), new AspectList().merge(Aspect.WATER, 1).merge(Aspect.EARTH, 1)));
/*      */     
/*      */ 
/*  242 */     ConfigResearch.recipes.put("AltWeb", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Blocks.field_150321_G), new ItemStack(Items.field_151007_F), new AspectList().merge(Aspect.TRAP, 2)));
/*      */     
/*      */ 
/*  245 */     ConfigResearch.recipes.put("AltMossyCobble", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Blocks.field_150341_Y), new ItemStack(Blocks.field_150347_e), new AspectList().merge(Aspect.PLANT, 2)));
/*      */     
/*      */ 
/*  248 */     ConfigResearch.recipes.put("AltIce", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Blocks.field_150432_aD), new ItemStack(Blocks.field_150433_aE), new AspectList().merge(Aspect.ORDER, 1).merge(Aspect.COLD, 1)));
/*      */     
/*      */ 
/*  251 */     ConfigResearch.recipes.put("AltLava", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Items.field_151129_at), new ItemStack(Items.field_151133_ar), new AspectList().merge(Aspect.EARTH, 2).merge(Aspect.FIRE, 4)));
/*      */     
/*      */ 
/*  254 */     ConfigResearch.recipes.put("AltGrass", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Blocks.field_150349_c), new ItemStack(Blocks.field_150346_d), new AspectList().merge(Aspect.PLANT, 1)));
/*      */     
/*      */ 
/*      */ 
/*  258 */     ConfigResearch.recipes.put("AltBonemeal", ThaumcraftApi.addCrucibleRecipe("ENTROPICPROCESSING", new ItemStack(Items.field_151100_aR, 4, 15), new ItemStack(Items.field_151103_aS), new AspectList().merge(Aspect.ENTROPY, 1)));
/*      */     
/*      */ 
/*  261 */     ConfigResearch.recipes.put("AltBlaze", ThaumcraftApi.addCrucibleRecipe("ENTROPICPROCESSING", new ItemStack(Items.field_151065_br, 3, 0), new ItemStack(Items.field_151072_bj), new AspectList().merge(Aspect.ENTROPY, 1)));
/*      */     
/*      */ 
/*  264 */     ConfigResearch.recipes.put("AltLeather", ThaumcraftApi.addCrucibleRecipe("ENTROPICPROCESSING", new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151078_bh), new AspectList().merge(Aspect.ENTROPY, 1).merge(Aspect.FIRE, 1)));
/*      */     
/*      */ 
/*  267 */     ConfigResearch.recipes.put("AltString", ThaumcraftApi.addCrucibleRecipe("ENTROPICPROCESSING", new ItemStack(Items.field_151007_F, 4, 0), new ItemStack(Blocks.field_150325_L, 1, 32767), new AspectList().merge(Aspect.ENTROPY, 1)));
/*      */     
/*      */ 
/*  270 */     ConfigResearch.recipes.put("AltMyc", ThaumcraftApi.addCrucibleRecipe("ENTROPICPROCESSING", new ItemStack(Blocks.field_150391_bh), new ItemStack(Blocks.field_150346_d), new AspectList().merge(Aspect.ENTROPY, 1).merge(Aspect.PLANT, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  276 */     ConfigResearch.recipes.put("PureIron", ThaumcraftApi.addCrucibleRecipe("PUREMETAL", new ItemStack(ItemsTC.clusters, 1, 0), "oreIron", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     
/*      */ 
/*  279 */     ConfigResearch.recipes.put("PureGold", ThaumcraftApi.addCrucibleRecipe("PUREMETAL", new ItemStack(ItemsTC.clusters, 1, 1), "oreGold", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     
/*      */ 
/*  282 */     ConfigResearch.recipes.put("PureCinnabar", ThaumcraftApi.addCrucibleRecipe("PUREMETAL", new ItemStack(ItemsTC.clusters, 1, 6), "oreCinnabar", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     
/*      */ 
/*  285 */     if (Config.foundCopperIngot) {
/*  286 */       ConfigResearch.recipes.put("PureCopper", ThaumcraftApi.addCrucibleRecipe("PUREMETAL", new ItemStack(ItemsTC.clusters, 1, 2), "oreCopper", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*  289 */     if (Config.foundTinIngot) {
/*  290 */       ConfigResearch.recipes.put("PureTin", ThaumcraftApi.addCrucibleRecipe("PUREMETAL", new ItemStack(ItemsTC.clusters, 1, 3), "oreTin", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*  293 */     if (Config.foundSilverIngot) {
/*  294 */       ConfigResearch.recipes.put("PureSilver", ThaumcraftApi.addCrucibleRecipe("PUREMETAL", new ItemStack(ItemsTC.clusters, 1, 4), "oreSilver", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*  297 */     if (Config.foundLeadIngot) {
/*  298 */       ConfigResearch.recipes.put("PureLead", ThaumcraftApi.addCrucibleRecipe("PUREMETAL", new ItemStack(ItemsTC.clusters, 1, 5), "oreLead", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  303 */     ConfigResearch.recipes.put("TransIron", ThaumcraftApi.addCrucibleRecipe("TRANSMETAL", new ItemStack(ItemsTC.nuggets, 3, 0), "nuggetIron", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.FLUX, 1)));
/*      */     
/*      */ 
/*  306 */     ConfigResearch.recipes.put("TransGold", ThaumcraftApi.addCrucibleRecipe("TRANSMETAL", new ItemStack(Items.field_151074_bl, 3, 0), new ItemStack(Items.field_151074_bl), new AspectList().merge(Aspect.METAL, 2).merge(Aspect.DESIRE, 1).merge(Aspect.FLUX, 1)));
/*      */     
/*      */ 
/*  309 */     if (Config.foundCopperIngot) {
/*  310 */       ConfigResearch.recipes.put("TransCopper", ThaumcraftApi.addCrucibleRecipe("TRANSMETAL", new ItemStack(ItemsTC.nuggets, 3, 1), "nuggetCopper", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.EXCHANGE, 1).merge(Aspect.FLUX, 1)));
/*      */     }
/*      */     
/*  313 */     if (Config.foundTinIngot) {
/*  314 */       ConfigResearch.recipes.put("TransTin", ThaumcraftApi.addCrucibleRecipe("TRANSMETAL", new ItemStack(ItemsTC.nuggets, 3, 2), "nuggetTin", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.CRYSTAL, 1).merge(Aspect.FLUX, 1)));
/*      */     }
/*      */     
/*  317 */     if (Config.foundSilverIngot) {
/*  318 */       ConfigResearch.recipes.put("TransSilver", ThaumcraftApi.addCrucibleRecipe("TRANSMETAL", new ItemStack(ItemsTC.nuggets, 3, 3), "nuggetSilver", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.DESIRE, 1).merge(Aspect.FLUX, 1)));
/*      */     }
/*      */     
/*  321 */     if (Config.foundLeadIngot) {
/*  322 */       ConfigResearch.recipes.put("TransLead", ThaumcraftApi.addCrucibleRecipe("TRANSMETAL", new ItemStack(ItemsTC.nuggets, 3, 4), "nuggetLead", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.ORDER, 1).merge(Aspect.FLUX, 1)));
/*      */     }
/*      */     
/*      */ 
/*  326 */     ConfigResearch.recipes.put("EtherealBloom", ThaumcraftApi.addCrucibleRecipe("ETHEREALBLOOM", new ItemStack(BlocksTC.bloom), new ItemStack(BlocksTC.shimmerleaf), new AspectList().add(Aspect.LIGHT, 8).add(Aspect.PLANT, 16).add(Aspect.LIFE, 16).add(Aspect.FLUX, 16)));
/*      */     
/*      */ 
/*      */ 
/*  330 */     ConfigResearch.recipes.put("LiquidDeath", ThaumcraftApi.addCrucibleRecipe("LIQUIDDEATH", new ItemStack(ItemsTC.bucketDeath), new ItemStack(Items.field_151133_ar), new AspectList().add(Aspect.DEATH, 32).add(Aspect.CRYSTAL, 32).add(Aspect.ENTROPY, 32)));
/*      */     
/*      */ 
/*      */ 
/*  334 */     ItemStack bt = new ItemStack(ItemsTC.phial, 1, 1);
/*  335 */     ((IEssentiaContainerItem)bt.func_77973_b()).setAspects(bt, new AspectList().add(Aspect.FLUX, 8));
/*  336 */     ConfigResearch.recipes.put("BottleTaint", ThaumcraftApi.addCrucibleRecipe("BOTTLETAINT", new ItemStack(ItemsTC.bottleTaint), bt, new AspectList().add(Aspect.FLUX, 8).add(Aspect.WATER, 8)));
/*      */     
/*      */ 
/*      */ 
/*  340 */     ConfigResearch.recipes.put("TaintShard", ThaumcraftApi.addCrucibleRecipe("TAINTSHARD", new ItemStack(ItemsTC.shard, 1, 6), new ItemStack(ItemsTC.shard, 1, 32767), new AspectList().add(Aspect.FLUX, 4).add(Aspect.ENTROPY, 4)));
/*      */     
/*      */ 
/*      */ 
/*  344 */     ConfigResearch.recipes.put("BathSalts", ThaumcraftApi.addCrucibleRecipe("BATHSALTS", new ItemStack(ItemsTC.bathSalts), new ItemStack(ItemsTC.salisMundus), new AspectList().add(Aspect.MIND, 6).add(Aspect.AIR, 6).add(Aspect.ORDER, 6).add(Aspect.LIFE, 6)));
/*      */     
/*      */ 
/*      */ 
/*  348 */     ConfigResearch.recipes.put("SaneSoap", ThaumcraftApi.addCrucibleRecipe("SANESOAP", new ItemStack(ItemsTC.sanitySoap), new ItemStack(BlocksTC.fleshBlock), new AspectList().add(Aspect.MIND, 16).add(Aspect.ELDRITCH, 16).add(Aspect.ORDER, 16).add(Aspect.LIFE, 16)));
/*      */     
/*      */ 
/*      */ 
/*  352 */     ConfigResearch.recipes.put("SealCollect", ThaumcraftApi.addCrucibleRecipe("SEALCOLLECT", GolemHelper.getSealStack("Thaumcraft:pickup"), new ItemStack(ItemsTC.seals), new AspectList().add(Aspect.DESIRE, 4)));
/*      */     
/*      */ 
/*  355 */     ConfigResearch.recipes.put("SealCollectAdv", ThaumcraftApi.addCrucibleRecipe(new String[] { "SEALCOLLECT", "MINDBIOTHAUMIC" }, GolemHelper.getSealStack("Thaumcraft:pickup_advanced"), GolemHelper.getSealStack("Thaumcraft:pickup"), new AspectList().add(Aspect.SENSES, 4).add(Aspect.MIND, 4)));
/*      */     
/*      */ 
/*      */ 
/*  359 */     ConfigResearch.recipes.put("SealStore", ThaumcraftApi.addCrucibleRecipe("SEALSTORE", GolemHelper.getSealStack("Thaumcraft:fill"), new ItemStack(ItemsTC.seals), new AspectList().add(Aspect.AVERSION, 4)));
/*      */     
/*      */ 
/*  362 */     ConfigResearch.recipes.put("SealStoreAdv", ThaumcraftApi.addCrucibleRecipe(new String[] { "SEALSTORE", "MINDBIOTHAUMIC" }, GolemHelper.getSealStack("Thaumcraft:fill_advanced"), GolemHelper.getSealStack("Thaumcraft:fill"), new AspectList().add(Aspect.SENSES, 4).add(Aspect.MIND, 4)));
/*      */     
/*      */ 
/*      */ 
/*  366 */     ConfigResearch.recipes.put("SealEmpty", ThaumcraftApi.addCrucibleRecipe("SEALEMPTY", GolemHelper.getSealStack("Thaumcraft:empty"), new ItemStack(ItemsTC.seals), new AspectList().add(Aspect.VOID, 4)));
/*      */     
/*      */ 
/*  369 */     ConfigResearch.recipes.put("SealEmptyAdv", ThaumcraftApi.addCrucibleRecipe(new String[] { "SEALEMPTY", "MINDBIOTHAUMIC" }, GolemHelper.getSealStack("Thaumcraft:empty_advanced"), GolemHelper.getSealStack("Thaumcraft:empty"), new AspectList().add(Aspect.SENSES, 4).add(Aspect.MIND, 4)));
/*      */     
/*      */ 
/*      */ 
/*  373 */     ConfigResearch.recipes.put("SealProvide", ThaumcraftApi.addCrucibleRecipe("SEALPROVIDE", GolemHelper.getSealStack("Thaumcraft:provider"), GolemHelper.getSealStack("Thaumcraft:empty_advanced"), new AspectList().add(Aspect.EXCHANGE, 4).add(Aspect.DESIRE, 4)));
/*      */     
/*      */ 
/*      */ 
/*  377 */     ConfigResearch.recipes.put("SealGuard", ThaumcraftApi.addCrucibleRecipe("SEALGUARD", GolemHelper.getSealStack("Thaumcraft:guard"), new ItemStack(ItemsTC.seals), new AspectList().add(Aspect.AVERSION, 8).add(Aspect.PROTECT, 8)));
/*      */     
/*      */ 
/*  380 */     ConfigResearch.recipes.put("SealGuardAdv", ThaumcraftApi.addCrucibleRecipe(new String[] { "SEALGUARD", "MINDBIOTHAUMIC" }, GolemHelper.getSealStack("Thaumcraft:guard_advanced"), GolemHelper.getSealStack("Thaumcraft:guard"), new AspectList().add(Aspect.SENSES, 8).add(Aspect.MIND, 8)));
/*      */     
/*      */ 
/*      */ 
/*  384 */     ConfigResearch.recipes.put("SealLumber", ThaumcraftApi.addCrucibleRecipe(new String[] { "SEALLUMBER" }, GolemHelper.getSealStack("Thaumcraft:lumber"), GolemHelper.getSealStack("Thaumcraft:breaker"), new AspectList().add(Aspect.PLANT, 16).add(Aspect.SENSES, 8)));
/*      */     
/*      */ 
/*      */ 
/*  388 */     ConfigResearch.recipes.put("SealUse", ThaumcraftApi.addCrucibleRecipe(new String[] { "SEALUSE" }, GolemHelper.getSealStack("Thaumcraft:use"), new ItemStack(ItemsTC.seals), new AspectList().add(Aspect.CRAFT, 8).add(Aspect.SENSES, 4).add(Aspect.MIND, 4)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initializeArcaneRecipes()
/*      */   {
/*  397 */     ArcaneWandRecipe wr = new ArcaneWandRecipe();
/*  398 */     ThaumcraftApi.getCraftingRecipes().add(wr);
/*  399 */     CraftingManager.func_77594_a().func_180302_a(wr);
/*      */     
/*  401 */     ArcaneSceptreRecipe sr = new ArcaneSceptreRecipe();
/*  402 */     ThaumcraftApi.getCraftingRecipes().add(sr);
/*  403 */     CraftingManager.func_77594_a().func_180302_a(sr);
/*      */     
/*      */ 
/*  406 */     ConfigResearch.recipes.put("SealBlank", ThaumcraftApi.addShapelessArcaneCraftingRecipe("CONTROLSEALS", new ItemStack(ItemsTC.seals, 3), new AspectList().add(Aspect.FIRE, 10).add(Aspect.ORDER, 10), new Object[] { new ItemStack(Items.field_151119_aD), new ItemStack(ItemsTC.tallow), "dyeRed", "nitor" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  411 */     ConfigResearch.recipes.put("GolemBell", ThaumcraftApi.addArcaneCraftingRecipe("CONTROLSEALS", new ItemStack(ItemsTC.golemBell), new AspectList().add(Aspect.ORDER, 10).add(Aspect.AIR, 10), new Object[] { " QQ", " QQ", "S  ", Character.valueOf('S'), "stickWood", Character.valueOf('Q'), "gemQuartz" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  416 */     ConfigResearch.recipes.put("ModuleVision", ThaumcraftApi.addArcaneCraftingRecipe("GOLEMVISION", new ItemStack(ItemsTC.modules, 1, 0), new AspectList().add(Aspect.WATER, 25).add(Aspect.AIR, 25), new Object[] { "B B", "E E", "PGP", Character.valueOf('B'), new ItemStack(Items.field_151069_bo), Character.valueOf('E'), new ItemStack(Items.field_151071_bq), Character.valueOf('P'), "plateBrass", Character.valueOf('G'), "gearBrass" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  421 */     ConfigResearch.recipes.put("ModuleAggression", ThaumcraftApi.addArcaneCraftingRecipe("SEALGUARD", new ItemStack(ItemsTC.modules, 1, 1), new AspectList().add(Aspect.FIRE, 25).add(Aspect.ENTROPY, 25), new Object[] { " R ", "RTR", "PGP", Character.valueOf('R'), "paneGlass", Character.valueOf('T'), new ItemStack(Items.field_151065_br), Character.valueOf('P'), "plateBrass", Character.valueOf('G'), "gearBrass" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  427 */     ConfigResearch.recipes.put("MirrorGlass", ThaumcraftApi.addShapelessArcaneCraftingRecipe("BASICARTIFACE", new ItemStack(ItemsTC.mirroredGlass), new AspectList().add(Aspect.FIRE, 25).add(Aspect.EARTH, 25), new Object[] { new ItemStack(ItemsTC.quicksilver), "paneGlass" }));
/*      */     
/*      */ 
/*      */ 
/*  431 */     ConfigResearch.recipes.put("PrimalCharm", ThaumcraftApi.addShapelessArcaneCraftingRecipe("BASICARTIFACE", new ItemStack(ItemsTC.primalCharm), new AspectList().add(Aspect.EARTH, 25).add(Aspect.FIRE, 25).add(Aspect.AIR, 25).add(Aspect.WATER, 25).add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25), new Object[] { Items.field_151043_k, "shardBalanced", "shardAir", "shardFire", "shardWater", "shardEarth", "shardOrder", "shardEntropy" }));
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
/*  442 */     for (int a = 0; a < 16; a++) {
/*  443 */       ItemStack banner = new ItemStack(BlocksTC.banner, 1, 0);
/*  444 */       banner.func_77982_d(new NBTTagCompound());
/*  445 */       banner.func_77978_p().func_74774_a("color", (byte)a);
/*  446 */       ConfigResearch.recipes.put("Banner_" + a, ThaumcraftApi.addArcaneCraftingRecipe("BANNERS", banner, new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 5), new Object[] { "WS", "WS", "WB", Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 15 - a), Character.valueOf('S'), "stickWood", Character.valueOf('B'), "slabWood" }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  453 */     ConfigResearch.recipes.put("WorkbenchCharger", ThaumcraftApi.addArcaneCraftingRecipe("WORKBENCHCHARGER", new ItemStack(BlocksTC.arcaneWorkbenchCharger), new AspectList().add(Aspect.FIRE, 100).add(Aspect.ORDER, 100).add(Aspect.AIR, 100), new Object[] { " R ", "W W", "I I", Character.valueOf('I'), "ingotIron", Character.valueOf('R'), new ItemStack(ItemsTC.shard, 1, 7), Character.valueOf('W'), new ItemStack(ItemsTC.wandRods, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  461 */     ConfigResearch.recipes.put("FocalManipulator", ThaumcraftApi.addArcaneCraftingRecipe("FOCALMANIPULATION", new ItemStack(BlocksTC.wandWorkbench), new AspectList().add(Aspect.FIRE, 150).add(Aspect.AIR, 150).add(Aspect.ENTROPY, 150).add(Aspect.EARTH, 150).add(Aspect.WATER, 150).add(Aspect.ORDER, 150), new Object[] { "IQI", "SPS", "GTG", Character.valueOf('Q'), new ItemStack(BlocksTC.slabStone), Character.valueOf('S'), new ItemStack(BlocksTC.stone), Character.valueOf('T'), new ItemStack(BlocksTC.tableStone), Character.valueOf('I'), "ingotIron", Character.valueOf('G'), new ItemStack(Items.field_151043_k), Character.valueOf('P'), new ItemStack(ItemsTC.primalCharm) }));
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
/*  473 */     ConfigResearch.recipes.put("ArcaneStone1", ThaumcraftApi.addArcaneCraftingRecipe("ARCANESTONE", new ItemStack(BlocksTC.stone, 9, 0), new AspectList().add(Aspect.EARTH, 5).add(Aspect.FIRE, 5), new Object[] { "SSS", "SCS", "SSS", Character.valueOf('S'), "stone", Character.valueOf('C'), new ItemStack(ItemsTC.shard, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*  477 */     ConfigResearch.recipes.put("ArcaneStone2", GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.stone, 4, 1), new Object[] { "SS", "SS", Character.valueOf('S'), new ItemStack(BlocksTC.stone, 1, 0) }));
/*      */     
/*      */ 
/*  480 */     ConfigResearch.recipes.put("ArcaneStone3", GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.stairsArcane, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 0) }));
/*      */     
/*      */ 
/*  483 */     ConfigResearch.recipes.put("ArcaneStone4", GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.stairsArcaneBrick, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 1) }));
/*      */     
/*      */ 
/*  486 */     ConfigResearch.recipes.put("ArcaneStone5", GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.slabStone, 6, 0), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 0) }));
/*      */     
/*      */ 
/*  489 */     ConfigResearch.recipes.put("ArcaneStone6", GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.slabStone, 6, 1), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*  493 */     ConfigResearch.recipes.put("PaveBarrier", ThaumcraftApi.addArcaneCraftingRecipe("PAVEBARRIER", new ItemStack(BlocksTC.pavingStone, 4, 0), new AspectList().add(Aspect.FIRE, 50).add(Aspect.ORDER, 50), new Object[] { "SAS", "SBS", Character.valueOf('S'), new ItemStack(BlocksTC.stone, 1, 1), Character.valueOf('A'), "shardFire", Character.valueOf('B'), "shardOrder" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  499 */     ConfigResearch.recipes.put("PaveTravel", ThaumcraftApi.addArcaneCraftingRecipe("PAVETRAVEL", new ItemStack(BlocksTC.pavingStone, 4, 1), new AspectList().add(Aspect.EARTH, 50).add(Aspect.AIR, 50), new Object[] { "SAS", "SBS", Character.valueOf('S'), new ItemStack(BlocksTC.stone, 1, 1), Character.valueOf('A'), "shardAir", Character.valueOf('B'), "shardEarth" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  505 */     ConfigResearch.recipes.put("ArcaneLamp", ThaumcraftApi.addArcaneCraftingRecipe("ARCANELAMP", new ItemStack(BlocksTC.lampArcane), new AspectList().add(Aspect.FIRE, 25).add(Aspect.AIR, 25), new Object[] { " S ", "IAI", " N ", Character.valueOf('A'), new ItemStack(BlocksTC.translucent), Character.valueOf('S'), new ItemStack(Blocks.field_150453_bW), Character.valueOf('N'), "nitor", Character.valueOf('I'), "ingotIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  512 */     ConfigResearch.recipes.put("ArcaneSpa", ThaumcraftApi.addArcaneCraftingRecipe("ARCANESPA", new ItemStack(BlocksTC.spa), new AspectList().add(Aspect.WATER, 25).add(Aspect.ORDER, 15).add(Aspect.EARTH, 15), new Object[] { "QIQ", "SJS", "SPS", Character.valueOf('P'), "gearBrass", Character.valueOf('J'), new ItemStack(BlocksTC.jar), Character.valueOf('S'), new ItemStack(BlocksTC.stone, 1, 0), Character.valueOf('Q'), new ItemStack(Blocks.field_150371_ca), Character.valueOf('I'), new ItemStack(Blocks.field_150411_aY) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  520 */     ConfigResearch.recipes.put("Levitator", ThaumcraftApi.addArcaneCraftingRecipe("LEVITATOR", new ItemStack(BlocksTC.levitator), new AspectList().add(Aspect.AIR, 20).add(Aspect.EARTH, 15), new Object[] { "WEW", "BNB", "WGW", Character.valueOf('W'), "plankWood", Character.valueOf('E'), "shardAir", Character.valueOf('N'), "nitor", Character.valueOf('B'), "ingotIron", Character.valueOf('G'), "gearBrass" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  527 */     ConfigResearch.recipes.put("RedstoneRelay", ThaumcraftApi.addArcaneCraftingRecipe("REDSTONERELAY", new ItemStack(BlocksTC.redstoneRelay), new AspectList().add(Aspect.FIRE, 5).add(Aspect.ORDER, 5), new Object[] { "   ", "TGC", "SSS", Character.valueOf('T'), new ItemStack(Blocks.field_150429_aA), Character.valueOf('G'), "gearBrass", Character.valueOf('C'), "shardOrder", Character.valueOf('S'), new ItemStack(Blocks.field_150333_U) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  533 */     ConfigResearch.recipes.put("ArcaneEar", ThaumcraftApi.addArcaneCraftingRecipe("ARCANEEAR", new ItemStack(BlocksTC.arcaneEar), new AspectList().add(Aspect.AIR, 10).add(Aspect.ORDER, 5), new Object[] { "PBP", " P ", "WRW", Character.valueOf('W'), "slabWood", Character.valueOf('R'), Items.field_151137_ax, Character.valueOf('P'), ItemsTC.plate, Character.valueOf('B'), new ItemStack(ItemsTC.brain) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  539 */     ConfigResearch.recipes.put("PoleInner", ThaumcraftApi.addArcaneCraftingRecipe("AURAMANIP1", new ItemStack(BlocksTC.auraTotem, 1, 3), new AspectList().add(Aspect.AIR, 50).add(Aspect.EARTH, 125).add(Aspect.ORDER, 75), new Object[] { "WGW", "WNW", "WGW", Character.valueOf('N'), "shardEarth", Character.valueOf('W'), new ItemStack(BlocksTC.plank, 1, 1), Character.valueOf('G'), new ItemStack(BlocksTC.plank, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  544 */     ConfigResearch.recipes.put("PoleOuter", ThaumcraftApi.addArcaneCraftingRecipe("AURAMANIP1", new ItemStack(BlocksTC.auraTotem, 1, 2), new AspectList().add(Aspect.AIR, 125).add(Aspect.EARTH, 50).add(Aspect.ORDER, 125), new Object[] { "WGW", "WNW", "WGW", Character.valueOf('N'), "shardAir", Character.valueOf('W'), new ItemStack(BlocksTC.plank, 1, 1), Character.valueOf('G'), new ItemStack(BlocksTC.plank, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  549 */     ConfigResearch.recipes.put("PoleStable", ThaumcraftApi.addArcaneCraftingRecipe("AURAPOLESTABLE", new ItemStack(BlocksTC.auraTotem, 1, 4), new AspectList().add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.ORDER, 250), new Object[] { "WGW", "WNW", "WGW", Character.valueOf('N'), "shardBalanced", Character.valueOf('W'), new ItemStack(BlocksTC.plank, 1, 1), Character.valueOf('G'), new ItemStack(BlocksTC.plank, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  554 */     ConfigResearch.recipes.put("BoneBow", ThaumcraftApi.addArcaneCraftingRecipe("BONEBOW", new ItemStack(ItemsTC.boneBow), new AspectList().add(Aspect.AIR, 50).add(Aspect.ENTROPY, 100), new Object[] { "SB ", "SEB", "SB ", Character.valueOf('E'), "shardEntropy", Character.valueOf('B'), Items.field_151103_aS, Character.valueOf('S'), Items.field_151007_F }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  559 */     Aspect[] pa = { Aspect.AIR, Aspect.FIRE, Aspect.WATER, Aspect.EARTH, Aspect.ORDER, Aspect.ENTROPY };
/*  560 */     for (int a = 0; a < 6; a++) {
/*  561 */       ConfigResearch.recipes.put("PrimalArrow_" + a, ThaumcraftApi.addArcaneCraftingRecipe("PRIMALARROW", new ItemStack(ItemsTC.primalArrows, 8, a), new AspectList().add(pa[a], 25), new Object[] { "AAA", "ASA", "AAA", Character.valueOf('A'), Items.field_151032_g, Character.valueOf('S'), new ItemStack(ItemsTC.shard, 1, a) }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  568 */     ConfigResearch.recipes.put("InfusionMatrix", ThaumcraftApi.addArcaneCraftingRecipe("INFUSION", new ItemStack(BlocksTC.infusionMatrix), new AspectList().add(Aspect.ORDER, 100), new Object[] { "SBS", "BNB", "SBS", Character.valueOf('S'), new ItemStack(BlocksTC.stone, 1, 1), Character.valueOf('N'), "nitor", Character.valueOf('B'), new ItemStack(ItemsTC.shard, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*  572 */     ConfigResearch.recipes.put("MatrixSpeed", ThaumcraftApi.addArcaneCraftingRecipe("INFUSIONBOOST", new ItemStack(BlocksTC.stone, 1, 8), new AspectList().add(Aspect.AIR, 250).add(Aspect.ORDER, 250).add(Aspect.ENTROPY, 250), new Object[] { "SNS", "NGN", "SNS", Character.valueOf('S'), new ItemStack(BlocksTC.stone), Character.valueOf('N'), "nitor", Character.valueOf('G'), new ItemStack(Blocks.field_150484_ah) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  577 */     ConfigResearch.recipes.put("MatrixCost", ThaumcraftApi.addArcaneCraftingRecipe("INFUSIONBOOST", new ItemStack(BlocksTC.stone, 1, 9), new AspectList().add(Aspect.WATER, 250).add(Aspect.ORDER, 250).add(Aspect.ENTROPY, 250), new Object[] { "SAS", "AGA", "SAS", Character.valueOf('S'), new ItemStack(BlocksTC.stone), Character.valueOf('A'), new ItemStack(ItemsTC.alumentum), Character.valueOf('G'), new ItemStack(Blocks.field_150484_ah) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  583 */     ConfigResearch.recipes.put("ArcanePedestal", ThaumcraftApi.addArcaneCraftingRecipe("INFUSION", new ItemStack(BlocksTC.pedestal), new AspectList().add(Aspect.AIR, 5), new Object[] { "SSS", " B ", "SSS", Character.valueOf('S'), new ItemStack(BlocksTC.slabStone), Character.valueOf('B'), new ItemStack(BlocksTC.stone) }));
/*      */     
/*      */ 
/*  586 */     ConfigResearch.recipes.put("AncientPedestal", ThaumcraftApi.addArcaneCraftingRecipe(new String[] { "INFUSION", "!ORBLOCK1" }, new ItemStack(BlocksTC.pedestal, 1, 2), new AspectList().add(Aspect.AIR, 25).add(Aspect.ENTROPY, 25).add(Aspect.ORDER, 150), new Object[] { "SSS", " B ", "SSS", Character.valueOf('S'), new ItemStack(BlocksTC.slabStone, 1, 2), Character.valueOf('B'), new ItemStack(BlocksTC.stone, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*  590 */     ConfigResearch.recipes.put("EldritchPedestal", ThaumcraftApi.addArcaneCraftingRecipe(new String[] { "INFUSION", "!ORBLOCK2" }, new ItemStack(BlocksTC.pedestal, 1, 1), new AspectList().add(Aspect.AIR, 25).add(Aspect.ENTROPY, 150).add(Aspect.ORDER, 25), new Object[] { "SSS", " B ", "SSS", Character.valueOf('S'), new ItemStack(BlocksTC.slabStone, 1, 3), Character.valueOf('B'), new ItemStack(BlocksTC.stone, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  596 */     ConfigResearch.recipes.put("WardedJar", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(BlocksTC.jar), new AspectList().add(Aspect.WATER, 5), new Object[] { "GWG", "G G", "GGG", Character.valueOf('W'), "slabWood", Character.valueOf('G'), "paneGlass" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  601 */     ConfigResearch.recipes.put("JarVoid", ThaumcraftApi.addArcaneCraftingRecipe("JARVOID", new ItemStack(BlocksTC.jar, 1, 1), new AspectList().add(Aspect.WATER, 25).add(Aspect.ENTROPY, 25), new Object[] { "O", "J", "P", Character.valueOf('O'), new ItemStack(Blocks.field_150343_Z), Character.valueOf('P'), Items.field_151065_br, Character.valueOf('J'), new ItemStack(BlocksTC.jar) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  607 */     ConfigResearch.recipes.put("WandCapGold", ThaumcraftApi.addArcaneCraftingRecipe("CAP_gold", new ItemStack(ItemsTC.wandCaps, 1, 1), new AspectList().add(Aspect.ORDER, ((WandCap)WandCap.caps.get("gold")).getCraftCost() * 8).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("gold")).getCraftCost() * 8).add(Aspect.AIR, ((WandCap)WandCap.caps.get("gold")).getCraftCost() * 8), new Object[] { "NNN", "N N", Character.valueOf('N'), Items.field_151074_bl }));
/*      */     
/*      */ 
/*      */ 
/*  611 */     ConfigResearch.recipes.put("WandCapBrass", ThaumcraftApi.addArcaneCraftingRecipe("CAP_brass", new ItemStack(ItemsTC.wandCaps, 1, 2), new AspectList().add(Aspect.ORDER, ((WandCap)WandCap.caps.get("brass")).getCraftCost() * 8).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("brass")).getCraftCost() * 8).add(Aspect.AIR, ((WandCap)WandCap.caps.get("brass")).getCraftCost() * 8), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetBrass" }));
/*      */     
/*      */ 
/*      */ 
/*  615 */     ConfigResearch.recipes.put("WandCapThaumiumInert", ThaumcraftApi.addArcaneCraftingRecipe("CAP_thaumium", new ItemStack(ItemsTC.wandCaps, 1, 3), new AspectList().add(Aspect.ORDER, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost() * 8).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost() * 8).add(Aspect.AIR, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost() * 8), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetThaumium" }));
/*      */     
/*      */ 
/*      */ 
/*  619 */     ConfigResearch.recipes.put("WandCapVoidInert", ThaumcraftApi.addArcaneCraftingRecipe("CAP_void", new ItemStack(ItemsTC.wandCaps, 1, 5), new AspectList().add(Aspect.ENTROPY, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 8).add(Aspect.ORDER, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 8).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 8).add(Aspect.AIR, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 8), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetVoid" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  624 */     ConfigResearch.recipes.put("WandRodGreatwood", ThaumcraftApi.addArcaneCraftingRecipe("ROD_greatwood", new ItemStack(ItemsTC.wandRods, 1, 0), new AspectList().add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("greatwood")).getCraftCost() * 8), new Object[] { " G", "G ", Character.valueOf('G'), new ItemStack(BlocksTC.log, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*  628 */     ConfigResearch.recipes.put("WandRodGreatwoodStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_greatwood_staff", new ItemStack(ItemsTC.wandRods, 1, 8), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("greatwood_staff")).getCraftCost() * 8), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('G'), new ItemStack(ItemsTC.wandRods, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  633 */     ConfigResearch.recipes.put("WandRodSilverwoodStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_silverwood_staff", new ItemStack(ItemsTC.wandRods, 1, 9), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("silverwood_staff")).getCraftCost() * 8), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('G'), new ItemStack(ItemsTC.wandRods, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  638 */     ConfigResearch.recipes.put("WandRodObsidianStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_obsidian_staff", new ItemStack(ItemsTC.wandRods, 1, 10), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("obsidian_staff")).getCraftCost() * 4).add(Aspect.EARTH, ((WandRod)WandRod.rods.get("obsidian_staff")).getCraftCost() * 4), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('G'), new ItemStack(ItemsTC.wandRods, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  643 */     ConfigResearch.recipes.put("WandRodBlazeStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_blaze_staff", new ItemStack(ItemsTC.wandRods, 1, 11), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("blaze_staff")).getCraftCost() * 4).add(Aspect.FIRE, ((WandRod)WandRod.rods.get("blaze_staff")).getCraftCost() * 4), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('G'), new ItemStack(ItemsTC.wandRods, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  648 */     ConfigResearch.recipes.put("WandRodIceStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_ice_staff", new ItemStack(ItemsTC.wandRods, 1, 12), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("ice_staff")).getCraftCost() * 4).add(Aspect.WATER, ((WandRod)WandRod.rods.get("ice_staff")).getCraftCost() * 4), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('G'), new ItemStack(ItemsTC.wandRods, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  653 */     ConfigResearch.recipes.put("WandRodQuartzStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_quartz_staff", new ItemStack(ItemsTC.wandRods, 1, 13), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("quartz_staff")).getCraftCost() * 4).add(Aspect.ORDER, ((WandRod)WandRod.rods.get("quartz_staff")).getCraftCost() * 4), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('G'), new ItemStack(ItemsTC.wandRods, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  658 */     ConfigResearch.recipes.put("WandRodBoneStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_bone_staff", new ItemStack(ItemsTC.wandRods, 1, 14), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("bone_staff")).getCraftCost() * 4).add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("bone_staff")).getCraftCost() * 4), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('G'), new ItemStack(ItemsTC.wandRods, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  663 */     ConfigResearch.recipes.put("WandRodReedStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_reed_staff", new ItemStack(ItemsTC.wandRods, 1, 15), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("reed_staff")).getCraftCost() * 4).add(Aspect.AIR, ((WandRod)WandRod.rods.get("reed_staff")).getCraftCost() * 4), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('G'), new ItemStack(ItemsTC.wandRods, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  670 */     ConfigResearch.recipes.put("FocusShock", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSSHOCK", new ItemStack(ItemsTC.focusShock), new AspectList().add(Aspect.AIR, 75).add(Aspect.ORDER, 75).add(Aspect.ENTROPY, 50), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), Items.field_151174_bG, Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), "shardAir" }));
/*      */     
/*      */ 
/*      */ 
/*  674 */     ConfigResearch.recipes.put("FocusFire", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSFIRE", new ItemStack(ItemsTC.focusFire), new AspectList().add(Aspect.FIRE, 100).add(Aspect.ENTROPY, 50), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), Items.field_151059_bz, Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), "shardFire" }));
/*      */     
/*      */ 
/*      */ 
/*  678 */     ConfigResearch.recipes.put("FocusFrost", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSFROST", new ItemStack(ItemsTC.focusFrost), new AspectList().add(Aspect.WATER, 75).add(Aspect.ORDER, 75).add(Aspect.ENTROPY, 50), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), Items.field_151045_i, Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), "shardWater" }));
/*      */     
/*      */ 
/*      */ 
/*  682 */     ConfigResearch.recipes.put("FocusExcavation", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSEXCAVATION", new ItemStack(ItemsTC.focusExcavation), new AspectList().add(Aspect.EARTH, 75).add(Aspect.ENTROPY, 75).add(Aspect.ORDER, 50), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), "gemEmerald", Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), "shardEarth" }));
/*      */     
/*      */ 
/*      */ 
/*  686 */     ConfigResearch.recipes.put("FocusTrade", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSTRADE", new ItemStack(ItemsTC.focusEqualTrade), new AspectList().add(Aspect.ORDER, 75).add(Aspect.ENTROPY, 75).add(Aspect.EARTH, 50), new Object[] { "CQE", "Q#Q", "CQE", Character.valueOf('#'), new ItemStack(ItemsTC.quicksilver), Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), "shardBalanced", Character.valueOf('E'), "shardBalanced" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  691 */     ConfigResearch.recipes.put("FocusBuilder", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSBUILDER", new ItemStack(ItemsTC.focusBuilder), new AspectList().add(Aspect.ORDER, 75).add(Aspect.AIR, 50).add(Aspect.EARTH, 75), new Object[] { "CQE", "Q#Q", "EQC", Character.valueOf('#'), new ItemStack(ItemsTC.amber), Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), "shardAir", Character.valueOf('E'), "shardEarth" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  696 */     ConfigResearch.recipes.put("FocusPrimal", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSPRIMAL", new ItemStack(ItemsTC.focusPrimal), new AspectList().add(Aspect.EARTH, 150).add(Aspect.ENTROPY, 150).add(Aspect.ORDER, 150).add(Aspect.AIR, 150).add(Aspect.FIRE, 150).add(Aspect.WATER, 150), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), new ItemStack(ItemsTC.primalCharm), Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), Items.field_151045_i }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  701 */     ConfigResearch.recipes.put("FocusGrapple", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSGRAPPLE", new ItemStack(ItemsTC.focusGrapple), new AspectList().add(Aspect.AIR, 75).add(Aspect.WATER, 75).add(Aspect.ORDER, 50), new Object[] { "AQW", "Q#Q", "WQA", Character.valueOf('#'), Items.field_151123_aH, Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('A'), "shardAir", Character.valueOf('W'), "shardWater" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  706 */     ConfigResearch.recipes.put("FocusPouch", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSPOUCH", new ItemStack(ItemsTC.focusPouch), new AspectList().add(Aspect.EARTH, 25).add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25), new Object[] { "LGL", "LBL", "LLL", Character.valueOf('B'), new ItemStack(ItemsTC.baubles, 1, 2), Character.valueOf('L'), Items.field_151116_aA, Character.valueOf('G'), Items.field_151043_k }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  713 */     ConfigResearch.recipes.put("ArcaneBoreBase", ThaumcraftApi.addArcaneCraftingRecipe("ARCANEBORE", new ItemStack(BlocksTC.arcaneBoreBase), new AspectList().add(Aspect.AIR, 100).add(Aspect.ORDER, 100), new Object[] { "WIW", "IDI", "WGW", Character.valueOf('W'), new ItemStack(BlocksTC.plank), Character.valueOf('G'), "gearBrass", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('D'), new ItemStack(Blocks.field_150367_z, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  720 */     ConfigResearch.recipes.put("EnchantedFabric", ThaumcraftApi.addArcaneCraftingRecipe("ENCHFABRIC", new ItemStack(ItemsTC.fabric), new AspectList().add(Aspect.AIR, 5).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5).add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5), new Object[] { " S ", "SCS", " S ", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('C'), new ItemStack(Blocks.field_150325_L, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  726 */     ConfigResearch.recipes.put("RobeChest", ThaumcraftApi.addArcaneCraftingRecipe("ENCHFABRIC", new ItemStack(ItemsTC.clothChest, 1), new AspectList().add(Aspect.AIR, 30), new Object[] { "I I", "III", "III", Character.valueOf('I'), new ItemStack(ItemsTC.fabric) }));
/*      */     
/*  728 */     ConfigResearch.recipes.put("RobeLegs", ThaumcraftApi.addArcaneCraftingRecipe("ENCHFABRIC", new ItemStack(ItemsTC.clothLegs, 1), new AspectList().add(Aspect.WATER, 25), new Object[] { "III", "I I", "I I", Character.valueOf('I'), new ItemStack(ItemsTC.fabric) }));
/*      */     
/*  730 */     ConfigResearch.recipes.put("RobeBoots", ThaumcraftApi.addArcaneCraftingRecipe("ENCHFABRIC", new ItemStack(ItemsTC.clothBoots, 1), new AspectList().add(Aspect.EARTH, 20), new Object[] { "I I", "I I", Character.valueOf('I'), new ItemStack(ItemsTC.fabric) }));
/*      */     
/*      */ 
/*      */ 
/*  734 */     ConfigResearch.recipes.put("Goggles", ThaumcraftApi.addArcaneCraftingRecipe("GOGGLES", new ItemStack(ItemsTC.goggles), new AspectList().add(Aspect.AIR, 25).add(Aspect.FIRE, 25).add(Aspect.WATER, 25).add(Aspect.EARTH, 25).add(Aspect.ENTROPY, 25).add(Aspect.ORDER, 25), new Object[] { "LGL", "L L", "TGT", Character.valueOf('T'), new ItemStack(ItemsTC.thaumometer, 1, 32767), Character.valueOf('G'), "ingotBrass", Character.valueOf('L'), Items.field_151116_aA }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  740 */     ConfigResearch.recipes.put("Dioptra", ThaumcraftApi.addArcaneCraftingRecipe("DIOPTRA", new ItemStack(BlocksTC.dioptra), new AspectList().add(Aspect.AIR, 25).add(Aspect.EARTH, 25).add(Aspect.ORDER, 25), new Object[] { "APA", "IGI", "AAA", Character.valueOf('A'), new ItemStack(BlocksTC.stone), Character.valueOf('G'), new ItemStack(ItemsTC.goggles), Character.valueOf('P'), new ItemStack(BlocksTC.slabStone), Character.valueOf('I'), "ingotIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  747 */     ConfigResearch.recipes.put("HungryChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHEST", new ItemStack(BlocksTC.hungryChest), new AspectList().add(Aspect.AIR, 15).add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15), new Object[] { "WTW", "W W", "WWW", Character.valueOf('W'), new ItemStack(BlocksTC.plank), Character.valueOf('T'), new ItemStack(Blocks.field_150415_aT) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  752 */     ConfigResearch.recipes.put("Filter", ThaumcraftApi.addArcaneCraftingRecipe("CRUCIBLE", new ItemStack(ItemsTC.filter, 2, 0), new AspectList().add(Aspect.ORDER, 15).add(Aspect.WATER, 15), new Object[] { "GWG", Character.valueOf('G'), Items.field_151043_k, Character.valueOf('W'), new ItemStack(BlocksTC.plank, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  757 */     ConfigResearch.recipes.put("MorphicResonator", ThaumcraftApi.addArcaneCraftingRecipe("CRUCIBLE", new ItemStack(ItemsTC.morphicResonator), new AspectList().add(Aspect.ORDER, 50).add(Aspect.ENTROPY, 50), new Object[] { " G ", "BFB", " G ", Character.valueOf('G'), "paneGlass", Character.valueOf('B'), "plateBrass", Character.valueOf('F'), "shardTainted" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  762 */     ConfigResearch.recipes.put("EssentiaSmelter", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(BlocksTC.smelterBasic), new AspectList().add(Aspect.FIRE, 25).add(Aspect.WATER, 25), new Object[] { "BCB", "SFS", "SSS", Character.valueOf('C'), new ItemStack(BlocksTC.crucible), Character.valueOf('F'), new ItemStack(Blocks.field_150460_al), Character.valueOf('S'), "cobblestone", Character.valueOf('B'), "plateBrass" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  768 */     ConfigResearch.recipes.put("EssentiaSmelter2", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA2", new ItemStack(BlocksTC.smelterThaumium), new AspectList().add(Aspect.FIRE, 350).add(Aspect.WATER, 350), new Object[] { "BFB", "IGI", "III", Character.valueOf('C'), new ItemStack(BlocksTC.crucible), Character.valueOf('F'), new ItemStack(BlocksTC.smelterBasic), Character.valueOf('G'), new ItemStack(BlocksTC.metal, 1, 2), Character.valueOf('I'), "ingotThaumium", Character.valueOf('B'), "plateBrass" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  774 */     ConfigResearch.recipes.put("EssentiaSmelter3", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA3", new ItemStack(BlocksTC.smelterVoid), new AspectList().add(Aspect.FIRE, 750).add(Aspect.WATER, 750), new Object[] { "BFB", "IGI", "III", Character.valueOf('C'), new ItemStack(BlocksTC.crucible), Character.valueOf('F'), new ItemStack(BlocksTC.smelterThaumium), Character.valueOf('G'), new ItemStack(BlocksTC.metal, 1, 3), Character.valueOf('I'), "ingotVoid", Character.valueOf('B'), "plateBrass" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  780 */     ConfigResearch.recipes.put("AdvAlchemyConstruct", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA3", new ItemStack(BlocksTC.metal, 1, 3), new AspectList().add(Aspect.WATER, 200).add(Aspect.ORDER, 200).add(Aspect.EARTH, 200), new Object[] { "VAV", "APA", "VAV", Character.valueOf('A'), new ItemStack(BlocksTC.metal, 1, 2), Character.valueOf('V'), new ItemStack(ItemsTC.ingots, 1, 1), Character.valueOf('P'), new ItemStack(ItemsTC.primordialPearl) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  786 */     ConfigResearch.recipes.put("Alembic", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(BlocksTC.alembic), new AspectList().add(Aspect.AIR, 15).add(Aspect.WATER, 25), new Object[] { "WFW", "SBS", "WFW", Character.valueOf('W'), new ItemStack(BlocksTC.plank), Character.valueOf('B'), Items.field_151133_ar, Character.valueOf('F'), new ItemStack(ItemsTC.filter), Character.valueOf('S'), "plateBrass" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  792 */     ConfigResearch.recipes.put("SmelterAux", ThaumcraftApi.addArcaneCraftingRecipe("IMPROVED_DISTILLATION", new ItemStack(BlocksTC.smelterAux), new AspectList().add(Aspect.AIR, 50).add(Aspect.WATER, 50).add(Aspect.EARTH, 25), new Object[] { "WTW", "RGR", "IBI", Character.valueOf('W'), new ItemStack(BlocksTC.plank), Character.valueOf('B'), new ItemStack(BlocksTC.bellows), Character.valueOf('R'), "plateBrass", Character.valueOf('T'), new ItemStack(BlocksTC.tube, 1, 4), Character.valueOf('F'), new ItemStack(ItemsTC.filter), Character.valueOf('I'), "ingotIron", Character.valueOf('G'), new ItemStack(BlocksTC.metal, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  800 */     ConfigResearch.recipes.put("SmelterVent", ThaumcraftApi.addArcaneCraftingRecipe("IMPROVED_DISTILLATION", new ItemStack(BlocksTC.smelterVent), new AspectList().add(Aspect.AIR, 125).add(Aspect.WATER, 125).add(Aspect.ORDER, 100), new Object[] { "IBI", "MGF", "IBI", Character.valueOf('I'), "ingotIron", Character.valueOf('B'), "ingotBrass", Character.valueOf('F'), new ItemStack(ItemsTC.filter), Character.valueOf('M'), new ItemStack(ItemsTC.morphicResonator), Character.valueOf('G'), new ItemStack(BlocksTC.metal, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  807 */     ConfigResearch.recipes.put("EssentiaTransportIn", ThaumcraftApi.addArcaneCraftingRecipe("ESSENTIATRANSPORT", new ItemStack(BlocksTC.essentiaTransportInput), new AspectList().add(Aspect.WATER, 75).add(Aspect.AIR, 50).add(Aspect.ORDER, 50), new Object[] { "BQB", "IGI", "IPI", Character.valueOf('I'), "ingotIron", Character.valueOf('B'), "ingotBrass", Character.valueOf('Q'), new ItemStack(Blocks.field_150367_z, 1, 0), Character.valueOf('P'), new ItemStack(BlocksTC.tube, 1, 0), Character.valueOf('G'), new ItemStack(BlocksTC.metal, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  813 */     ConfigResearch.recipes.put("EssentiaTransportOut", ThaumcraftApi.addArcaneCraftingRecipe("ESSENTIATRANSPORT", new ItemStack(BlocksTC.essentiaTransportOutput), new AspectList().add(Aspect.WATER, 75).add(Aspect.AIR, 50).add(Aspect.ORDER, 50), new Object[] { "BQB", "IGI", "IPI", Character.valueOf('I'), "ingotIron", Character.valueOf('B'), "ingotBrass", Character.valueOf('Q'), new ItemStack(Blocks.field_150438_bZ, 1, 0), Character.valueOf('P'), new ItemStack(BlocksTC.tube, 1, 0), Character.valueOf('G'), new ItemStack(BlocksTC.metal, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  820 */     ConfigResearch.recipes.put("Bellows", ThaumcraftApi.addArcaneCraftingRecipe("BELLOWS", new ItemStack(BlocksTC.bellows, 1, 0), new AspectList().add(Aspect.AIR, 25).add(Aspect.ORDER, 10), new Object[] { "WW ", "LCI", "WW ", Character.valueOf('W'), "plankWood", Character.valueOf('C'), "shardAir", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('L'), Items.field_151116_aA }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  826 */     ConfigResearch.recipes.put("Tube", ThaumcraftApi.addArcaneCraftingRecipe("TUBES", new ItemStack(BlocksTC.tube, 8, 0), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { " Q ", "IGI", " B ", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('B'), Items.field_151074_bl, Character.valueOf('G'), "blockGlass", Character.valueOf('Q'), new ItemStack(ItemsTC.nuggets, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*  830 */     ConfigResearch.recipes.put("Resonator", ThaumcraftApi.addArcaneCraftingRecipe("TUBES", new ItemStack(ItemsTC.resonator), new AspectList().add(Aspect.WATER, 5).add(Aspect.AIR, 5), new Object[] { "I I", "INI", " S ", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('N'), Items.field_151128_bU, Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*  833 */     ConfigResearch.recipes.put("TubeValve", ThaumcraftApi.addShapelessArcaneCraftingRecipe("TUBES", new ItemStack(BlocksTC.tube, 1, 1), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { new ItemStack(BlocksTC.tube, 1, 0), new ItemStack(Blocks.field_150442_at) }));
/*      */     
/*      */ 
/*  836 */     ConfigResearch.recipes.put("TubeFilter", ThaumcraftApi.addShapelessArcaneCraftingRecipe("TUBEFILTER", new ItemStack(BlocksTC.tube, 1, 4), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 10), new Object[] { new ItemStack(BlocksTC.tube, 1, 0), new ItemStack(ItemsTC.filter) }));
/*      */     
/*      */ 
/*  839 */     ConfigResearch.recipes.put("TubeRestrict", ThaumcraftApi.addShapelessArcaneCraftingRecipe("TUBEFILTER", new ItemStack(BlocksTC.tube, 1, 2), new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 10), new Object[] { new ItemStack(BlocksTC.tube, 1, 0), "stone" }));
/*      */     
/*      */ 
/*  842 */     ConfigResearch.recipes.put("TubeOneway", ThaumcraftApi.addShapelessArcaneCraftingRecipe("TUBEFILTER", new ItemStack(BlocksTC.tube, 1, 3), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10), new Object[] { new ItemStack(BlocksTC.tube, 1, 0), "dyeBlue" }));
/*      */     
/*      */ 
/*  845 */     ConfigResearch.recipes.put("TubeBuffer", ThaumcraftApi.addArcaneCraftingRecipe("TUBEFILTER", new ItemStack(BlocksTC.tube, 1, 5), new AspectList().add(Aspect.WATER, 25).add(Aspect.ORDER, 25), new Object[] { "PVP", "T T", "PRP", Character.valueOf('T'), new ItemStack(BlocksTC.tube, 1, 0), Character.valueOf('V'), new ItemStack(BlocksTC.tube, 1, 1), Character.valueOf('R'), new ItemStack(BlocksTC.tube, 1, 2), Character.valueOf('P'), new ItemStack(ItemsTC.phial) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  851 */     ConfigResearch.recipes.put("AlchemicalConstruct", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(BlocksTC.metal, 1, 2), new AspectList().add(Aspect.WATER, 50).add(Aspect.ORDER, 50), new Object[] { "IVI", "TWT", "IVI", Character.valueOf('W'), new ItemStack(BlocksTC.plank), Character.valueOf('V'), new ItemStack(BlocksTC.tube, 1, 1), Character.valueOf('T'), new ItemStack(BlocksTC.tube, 1, 0), Character.valueOf('I'), "ingotIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  859 */     ConfigResearch.recipes.put("Centrifuge", ThaumcraftApi.addArcaneCraftingRecipe("CENTRIFUGE", new ItemStack(BlocksTC.centrifuge), new AspectList().add(Aspect.WATER, 100).add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100), new Object[] { " T ", "RCP", " T ", Character.valueOf('T'), new ItemStack(BlocksTC.tube, 1, 0), Character.valueOf('P'), "gearBrass", Character.valueOf('R'), new ItemStack(ItemsTC.morphicResonator), Character.valueOf('C'), new ItemStack(BlocksTC.metal, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  866 */     ConfigResearch.recipes.put("EssentiaCrystalizer", ThaumcraftApi.addArcaneCraftingRecipe("ESSENTIACRYSTAL", new ItemStack(BlocksTC.crystallizer), new AspectList().add(Aspect.WATER, 100).add(Aspect.EARTH, 250).add(Aspect.ORDER, 100), new Object[] { "IDI", "QCQ", "WTW", Character.valueOf('T'), new ItemStack(BlocksTC.tube, 1, 0), Character.valueOf('D'), "gearBrass", Character.valueOf('Q'), "shardBalanced", Character.valueOf('I'), "ingotIron", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(BlocksTC.metal, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  874 */     ConfigResearch.recipes.put("MnemonicMatrix", ThaumcraftApi.addArcaneCraftingRecipe("THAUMATORIUM", new ItemStack(BlocksTC.brainBox), new AspectList().add(Aspect.FIRE, 50).add(Aspect.WATER, 50).add(Aspect.ORDER, 50), new Object[] { "IAI", "ABA", "IAI", Character.valueOf('B'), new ItemStack(ItemsTC.mind, 1, 0), Character.valueOf('A'), "gemAmber", Character.valueOf('I'), "ingotIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  880 */     ConfigResearch.recipes.put("MindClockwork", ThaumcraftApi.addArcaneCraftingRecipe("MINDCLOCKWORK", new ItemStack(ItemsTC.mind, 1, 0), new AspectList().add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.ORDER, 10), new Object[] { "PPP", "PGP", "BCB", Character.valueOf('G'), "gearBrass", Character.valueOf('B'), "plateBrass", Character.valueOf('P'), "paneGlass", Character.valueOf('C'), new ItemStack(Items.field_151132_bS) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  886 */     ConfigResearch.recipes.put("AutomatedCrossbow", ThaumcraftApi.addArcaneCraftingRecipe("BASICTURRET", new ItemStack(ItemsTC.turretPlacer, 1, 0), new AspectList().add(Aspect.FIRE, 50).add(Aspect.AIR, 100).add(Aspect.ORDER, 50), new Object[] { "BGI", "WMW", "S S", Character.valueOf('G'), "gearBrass", Character.valueOf('I'), "ingotIron", Character.valueOf('S'), "stickWood", Character.valueOf('M'), new ItemStack(ItemsTC.mind), Character.valueOf('B'), new ItemStack(Items.field_151031_f), Character.valueOf('W'), new ItemStack(BlocksTC.plank) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  892 */     ConfigResearch.recipes.put("AdvancedCrossbow", ThaumcraftApi.addArcaneCraftingRecipe("ADVANCEDTURRET", new ItemStack(ItemsTC.turretPlacer, 1, 3), new AspectList().add(Aspect.FIRE, 50).add(Aspect.AIR, 75).add(Aspect.ORDER, 125), new Object[] { "PMP", "PTP", "BGB", Character.valueOf('G'), "gearBrass", Character.valueOf('T'), new ItemStack(ItemsTC.turretPlacer, 1, 0), Character.valueOf('P'), "plateIron", Character.valueOf('B'), "plateBrass", Character.valueOf('M'), new ItemStack(ItemsTC.mind, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  898 */     ConfigResearch.recipes.put("NodeStable", ThaumcraftApi.addArcaneCraftingRecipe("NODESTABLE", new ItemStack(BlocksTC.nodeStabilizer), new AspectList().add(Aspect.WATER, 25).add(Aspect.EARTH, 25).add(Aspect.ORDER, 50), new Object[] { " G ", "QPQ", "SNS", Character.valueOf('S'), new ItemStack(BlocksTC.stone, 1, 1), Character.valueOf('G'), new ItemStack(Items.field_151043_k), Character.valueOf('P'), new ItemStack(Blocks.field_150331_J), Character.valueOf('Q'), new ItemStack(Blocks.field_150371_ca), Character.valueOf('N'), "nitor" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  906 */     ConfigResearch.recipes.put("ArcanePatternCrafter", ThaumcraftApi.addArcaneCraftingRecipe("ARCANEPATTERNCRAFTER", new ItemStack(BlocksTC.patternCrafter), new AspectList().add(Aspect.ORDER, 100).add(Aspect.FIRE, 100).add(Aspect.ENTROPY, 100), new Object[] { " H ", "GCG", " W ", Character.valueOf('H'), new ItemStack(Blocks.field_150438_bZ), Character.valueOf('W'), new ItemStack(BlocksTC.plank), Character.valueOf('G'), "gearBrass", Character.valueOf('C'), new ItemStack(Blocks.field_150462_ai) }));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initializeInfusionRecipes()
/*      */   {
/*  916 */     ConfigResearch.recipes.put("SealHarvest", ThaumcraftApi.addInfusionCraftingRecipe("SEALHARVEST", GolemHelper.getSealStack("Thaumcraft:harvest"), 0, new AspectList().add(Aspect.PLANT, 4).add(Aspect.SENSES, 4).add(Aspect.MAN, 4), new ItemStack(ItemsTC.seals), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(Items.field_151080_bb), new ItemStack(Items.field_151081_bc), new ItemStack(Items.field_151100_aR, 1, 3), new ItemStack(Items.field_151120_aE), new ItemStack(Blocks.field_150434_aF) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  921 */     ConfigResearch.recipes.put("SealButcher", ThaumcraftApi.addInfusionCraftingRecipe("SEALBUTCHER", GolemHelper.getSealStack("Thaumcraft:butcher"), 0, new AspectList().add(Aspect.BEAST, 4).add(Aspect.SENSES, 4).add(Aspect.MAN, 4), GolemHelper.getSealStack("Thaumcraft:guard"), new Object[] { new ItemStack(Items.field_151116_aA), new ItemStack(Blocks.field_150325_L, 1, 32767), new ItemStack(Items.field_179555_bs), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_179561_bm), new ItemStack(Items.field_151082_bd) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  926 */     ConfigResearch.recipes.put("SealBreak", ThaumcraftApi.addInfusionCraftingRecipe("SEALBREAK", GolemHelper.getSealStack("Thaumcraft:breaker"), 1, new AspectList().add(Aspect.TOOL, 4).add(Aspect.ENTROPY, 4).add(Aspect.MAN, 4), new ItemStack(ItemsTC.seals), new Object[] { new ItemStack(Items.field_151006_E), new ItemStack(Items.field_151005_D), new ItemStack(Items.field_151011_C) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  931 */     ConfigResearch.recipes.put("ClusterAir", ThaumcraftApi.addInfusionCraftingRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalAir), 0, new AspectList().add(Aspect.AIR, 8).add(Aspect.CRYSTAL, 8).add(Aspect.TRAP, 4), new ItemStack(ItemsTC.shard, 1, 0), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*  934 */     ConfigResearch.recipes.put("ClusterFire", ThaumcraftApi.addInfusionCraftingRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalFire), 0, new AspectList().add(Aspect.FIRE, 8).add(Aspect.CRYSTAL, 8).add(Aspect.TRAP, 4), new ItemStack(ItemsTC.shard, 1, 1), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*  937 */     ConfigResearch.recipes.put("ClusterWater", ThaumcraftApi.addInfusionCraftingRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalWater), 0, new AspectList().add(Aspect.WATER, 8).add(Aspect.CRYSTAL, 8).add(Aspect.TRAP, 4), new ItemStack(ItemsTC.shard, 1, 2), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*  940 */     ConfigResearch.recipes.put("ClusterEarth", ThaumcraftApi.addInfusionCraftingRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalEarth), 0, new AspectList().add(Aspect.EARTH, 8).add(Aspect.CRYSTAL, 8).add(Aspect.TRAP, 4), new ItemStack(ItemsTC.shard, 1, 3), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*  943 */     ConfigResearch.recipes.put("ClusterOrder", ThaumcraftApi.addInfusionCraftingRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalOrder), 0, new AspectList().add(Aspect.ORDER, 8).add(Aspect.CRYSTAL, 8).add(Aspect.TRAP, 4), new ItemStack(ItemsTC.shard, 1, 4), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*  946 */     ConfigResearch.recipes.put("ClusterEntropy", ThaumcraftApi.addInfusionCraftingRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalEntropy), 0, new AspectList().add(Aspect.ENTROPY, 8).add(Aspect.CRYSTAL, 8).add(Aspect.TRAP, 4), new ItemStack(ItemsTC.shard, 1, 5), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*  949 */     ConfigResearch.recipes.put("ClusterFlux", ThaumcraftApi.addInfusionCraftingRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalTaint), 4, new AspectList().add(Aspect.FLUX, 8).add(Aspect.CRYSTAL, 8).add(Aspect.TRAP, 4), new ItemStack(ItemsTC.shard, 1, 6), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  954 */     ConfigResearch.recipes.put("TotemPull", ThaumcraftApi.addInfusionCraftingRecipe("AURAMANIP1", new ItemStack(BlocksTC.auraTotem, 1, 1), 5, new AspectList().add(Aspect.ENERGY, 25).add(Aspect.AURA, 25).add(Aspect.MOTION, 25).add(Aspect.DESIRE, 25), new ItemStack(BlocksTC.plank, 1, 1), new Object[] { new ItemStack(BlocksTC.plank, 1, 0), new ItemStack(Blocks.field_150486_ae), "shardBalanced", new ItemStack(ItemsTC.salisMundus), "gemAmber", new ItemStack(Items.field_151146_bM), new ItemStack(ItemsTC.wispyEssence) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  959 */     ConfigResearch.recipes.put("TotemPush", ThaumcraftApi.addInfusionCraftingRecipe("AURAMANIP1", new ItemStack(BlocksTC.auraTotem, 1, 0), 5, new AspectList().add(Aspect.ENERGY, 25).add(Aspect.AURA, 25).add(Aspect.MOTION, 25).add(Aspect.AVERSION, 25), new ItemStack(BlocksTC.plank, 1, 1), new Object[] { new ItemStack(BlocksTC.plank, 1, 0), new ItemStack(Blocks.field_150486_ae), "shardBalanced", new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.quicksilver), new ItemStack(Blocks.field_150331_J), new ItemStack(ItemsTC.wispyEssence) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  966 */     ConfigResearch.recipes.put("WaterJug", ThaumcraftApi.addInfusionCraftingRecipe("WATERJUG", new ItemStack(BlocksTC.waterJug), 1, new AspectList().add(Aspect.WATER, 16).add(Aspect.CRAFT, 8).add(Aspect.EARTH, 8), new ItemStack(Blocks.field_150405_ch), new Object[] { new ItemStack(Items.field_151131_as), new ItemStack(Items.field_151131_as), "shardWater", new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  972 */     ConfigResearch.recipes.put("WandCapThaumium", ThaumcraftApi.addInfusionCraftingRecipe("CAP_thaumium", new ItemStack(ItemsTC.wandCaps, 1, 4), 5, new AspectList().add(Aspect.ENERGY, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost() * 2).add(Aspect.AURA, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost()), new ItemStack(ItemsTC.wandCaps, 1, 3), new ItemStack[] { new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*      */ 
/*  976 */     ConfigResearch.recipes.put("WandCapVoid", ThaumcraftApi.addInfusionCraftingRecipe("CAP_void", new ItemStack(ItemsTC.wandCaps, 1, 6), 8, new AspectList().add(Aspect.ENERGY, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2).add(Aspect.VOID, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2).add(Aspect.ELDRITCH, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2).add(Aspect.AURA, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2), new ItemStack(ItemsTC.wandCaps, 1, 5), new ItemStack[] { new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  982 */     ConfigResearch.recipes.put("WandRodObsidian", ThaumcraftApi.addInfusionCraftingRecipe("ROD_obsidian", new ItemStack(ItemsTC.wandRods, 1, 2), 3, new AspectList().add(Aspect.EARTH, ((WandRod)WandRod.rods.get("obsidian")).getCraftCost() * 2).add(Aspect.ENERGY, ((WandRod)WandRod.rods.get("obsidian")).getCraftCost()).add(Aspect.DARKNESS, ((WandRod)WandRod.rods.get("obsidian")).getCraftCost()), new ItemStack(Blocks.field_150343_Z), new Object[] { "shardBalanced", "shardEarth" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  987 */     ConfigResearch.recipes.put("WandRodIce", ThaumcraftApi.addInfusionCraftingRecipe("ROD_ice", new ItemStack(ItemsTC.wandRods, 1, 4), 3, new AspectList().add(Aspect.WATER, ((WandRod)WandRod.rods.get("ice")).getCraftCost() * 2).add(Aspect.ENERGY, ((WandRod)WandRod.rods.get("ice")).getCraftCost()).add(Aspect.COLD, ((WandRod)WandRod.rods.get("ice")).getCraftCost()), new ItemStack(Blocks.field_150432_aD), new Object[] { "shardBalanced", "shardWater" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  992 */     ConfigResearch.recipes.put("WandRodQuartz", ThaumcraftApi.addInfusionCraftingRecipe("ROD_quartz", new ItemStack(ItemsTC.wandRods, 1, 5), 3, new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("quartz")).getCraftCost() * 2).add(Aspect.ENERGY, ((WandRod)WandRod.rods.get("quartz")).getCraftCost()).add(Aspect.CRYSTAL, ((WandRod)WandRod.rods.get("quartz")).getCraftCost()), new ItemStack(Blocks.field_150371_ca), new Object[] { "shardBalanced", "shardOrder" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  997 */     ConfigResearch.recipes.put("WandRodReed", ThaumcraftApi.addInfusionCraftingRecipe("ROD_reed", new ItemStack(ItemsTC.wandRods, 1, 7), 3, new AspectList().add(Aspect.AIR, ((WandRod)WandRod.rods.get("reed")).getCraftCost() * 2).add(Aspect.ENERGY, ((WandRod)WandRod.rods.get("reed")).getCraftCost()).add(Aspect.MOTION, ((WandRod)WandRod.rods.get("reed")).getCraftCost()), new ItemStack(Items.field_151120_aE), new Object[] { "shardBalanced", "shardAir" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1002 */     ConfigResearch.recipes.put("WandRodBlaze", ThaumcraftApi.addInfusionCraftingRecipe("ROD_blaze", new ItemStack(ItemsTC.wandRods, 1, 3), 3, new AspectList().add(Aspect.FIRE, ((WandRod)WandRod.rods.get("blaze")).getCraftCost() * 2).add(Aspect.ENERGY, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()).add(Aspect.BEAST, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()), new ItemStack(Items.field_151072_bj), new Object[] { "shardBalanced", "shardFire" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1007 */     ConfigResearch.recipes.put("WandRodBone", ThaumcraftApi.addInfusionCraftingRecipe("ROD_bone", new ItemStack(ItemsTC.wandRods, 1, 6), 3, new AspectList().add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("bone")).getCraftCost() * 2).add(Aspect.ENERGY, ((WandRod)WandRod.rods.get("bone")).getCraftCost()).add(Aspect.UNDEAD, ((WandRod)WandRod.rods.get("bone")).getCraftCost()), new ItemStack(Items.field_151103_aS), new Object[] { "shardBalanced", "shardEntropy" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1012 */     ConfigResearch.recipes.put("WandRodSilverwood", ThaumcraftApi.addInfusionCraftingRecipe("ROD_silverwood", new ItemStack(ItemsTC.wandRods, 1, 1), 5, new AspectList().add(Aspect.AIR, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.FIRE, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.WATER, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.EARTH, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.ORDER, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.ENERGY, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()), new ItemStack(BlocksTC.log, 1, 3), new Object[] { "shardBalanced", "shardAir", "shardFire", "shardWater", "shardEarth", "shardOrder", "shardEntropy" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1019 */     ConfigResearch.recipes.put("WandRodPrimalStaff", ThaumcraftApi.addInfusionCraftingRecipe("ROD_primal_staff", new ItemStack(ItemsTC.wandRods, 1, 16), 8, new AspectList().add(Aspect.AIR, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.FIRE, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.WATER, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.EARTH, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.ORDER, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.ENERGY, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost() * 2), new ItemStack(ItemsTC.wandRods, 1, 1), new Object[] { new ItemStack(ItemsTC.primalCharm), new ItemStack(ItemsTC.wandRods, 1, 2), new ItemStack(ItemsTC.wandRods, 1, 3), new ItemStack(ItemsTC.wandRods, 1, 4), new ItemStack(ItemsTC.primalCharm), new ItemStack(ItemsTC.wandRods, 1, 5), new ItemStack(ItemsTC.wandRods, 1, 6), new ItemStack(ItemsTC.wandRods, 1, 7) }));
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
/* 1030 */     ConfigResearch.recipes.put("FocusHellbat", ThaumcraftApi.addInfusionCraftingRecipe("FOCUSHELLBAT", new ItemStack(ItemsTC.focusHellbat), 3, new AspectList().add(Aspect.FIRE, 25).add(Aspect.AIR, 15).add(Aspect.BEAST, 15).add(Aspect.ENTROPY, 25), new ItemStack(Items.field_151064_bs), new Object[] { new ItemStack(Items.field_151128_bU), "shardFire", new ItemStack(Items.field_151128_bU), "shardAir", new ItemStack(Items.field_151128_bU), "shardEntropy" }));
/*      */     
/*      */ 
/*      */ 
/* 1034 */     ConfigResearch.recipes.put("FocusPortableHole", ThaumcraftApi.addInfusionCraftingRecipe("FOCUSPORTABLEHOLE", new ItemStack(ItemsTC.focusHole), 3, new AspectList().add(Aspect.MOTION, 25).add(Aspect.ELDRITCH, 10).add(Aspect.EXCHANGE, 10).add(Aspect.ENTROPY, 25), new ItemStack(Items.field_151079_bi), new Object[] { new ItemStack(Items.field_151128_bU), "shardEarth", new ItemStack(Items.field_151128_bU), "shardAir", new ItemStack(Items.field_151128_bU), "shardEntropy" }));
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
/* 1056 */     ConfigResearch.recipes.put("WandPed", ThaumcraftApi.addInfusionCraftingRecipe("WANDPED", new ItemStack(BlocksTC.rechargePedestal), 3, new AspectList().add(Aspect.AURA, 10).add(Aspect.ENERGY, 15).add(Aspect.EXCHANGE, 15), new ItemStack(BlocksTC.pedestal), new ItemStack[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151045_i), new ItemStack(ItemsTC.primalCharm), new ItemStack(Items.field_151045_i) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1063 */     ConfigResearch.recipes.put("JarBrain", ThaumcraftApi.addInfusionCraftingRecipe("JARBRAIN", new ItemStack(BlocksTC.jar, 1, 2), 4, new AspectList().add(Aspect.MIND, 10).add(Aspect.SENSES, 10).add(Aspect.UNDEAD, 20), new ItemStack(BlocksTC.jar, 1, 0), new ItemStack[] { new ItemStack(ItemsTC.brain), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151131_as), new ItemStack(Items.field_151070_bp) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1070 */     ConfigResearch.recipes.put("HoverHarness", ThaumcraftApi.addInfusionCraftingRecipe("HOVERHARNESS", new ItemStack(ItemsTC.thaumostaticHarness), 6, new AspectList().add(Aspect.FLIGHT, 32).add(Aspect.ENERGY, 32).add(Aspect.MECHANISM, 32).add(Aspect.MOTION, 16), new ItemStack(Items.field_151027_R), new Object[] { "shardAir", "shardAir", new ItemStack(BlocksTC.plank), new ItemStack(BlocksTC.plank), new ItemStack(ItemsTC.morphicResonator), "plateBrass", "plateBrass", "ingotIron", "ingotIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1077 */     ConfigResearch.recipes.put("HoverGirdle", ThaumcraftApi.addInfusionCraftingRecipe("HOVERGIRDLE", new ItemStack(ItemsTC.girdleHover), 8, new AspectList().add(Aspect.FLIGHT, 16).add(Aspect.ENERGY, 32).add(Aspect.AIR, 32).add(Aspect.MOTION, 16), new ItemStack(ItemsTC.baubles, 1, 2), new Object[] { "shardAir", new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151043_k), "shardEarth", new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151043_k) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1082 */     ConfigResearch.recipes.put("VisAmulet", ThaumcraftApi.addInfusionCraftingRecipe("VISAMULET", new ItemStack(ItemsTC.amuletVis, 1, 1), 6, new AspectList().add(Aspect.AURA, 24).add(Aspect.ENERGY, 64).add(Aspect.ENERGY, 64).add(Aspect.VOID, 24), new ItemStack(ItemsTC.baubles, 1, 0), new Object[] { new ItemStack(ItemsTC.primalCharm), new ItemStack(ItemsTC.shard, 1, 0), new ItemStack(ItemsTC.shard, 1, 1), new ItemStack(ItemsTC.primalCharm), new ItemStack(ItemsTC.shard, 1, 2), new ItemStack(ItemsTC.shard, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1089 */     ConfigResearch.recipes.put("RunicAmulet", ThaumcraftApi.addInfusionCraftingRecipe("RUNICARMOR", new ItemStack(ItemsTC.amuletRunic, 1, 0), 4, new AspectList().add(Aspect.PROTECT, 30).add(Aspect.ENERGY, 50), new ItemStack(ItemsTC.baubles, 1, 0), new Object[] { new ItemStack(ItemsTC.primalCharm), "gemAmber", new ItemStack(ItemsTC.fabric), "nitor", "nitor", new ItemStack(ItemsTC.scribingTools) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1094 */     ConfigResearch.recipes.put("RunicAmuletEmergency", ThaumcraftApi.addInfusionCraftingRecipe("RUNICSPECIAL", new ItemStack(ItemsTC.amuletRunic, 1, 1), 7, new AspectList().add(Aspect.PROTECT, 30).add(Aspect.EARTH, 50).add(Aspect.VOID, 32), new ItemStack(ItemsTC.amuletRunic, 1, 0), new Object[] { "shardBalanced", "shardEarth", "shardEarth", new ItemStack(Items.field_151068_bn, 1, 8233), "shardEarth", "shardEarth" }));
/*      */     
/*      */ 
/*      */ 
/* 1098 */     ConfigResearch.recipes.put("RunicRing", ThaumcraftApi.addInfusionCraftingRecipe("RUNICARMOR", new ItemStack(ItemsTC.ringRunic, 1, 1), 3, new AspectList().add(Aspect.PROTECT, 25).add(Aspect.ENERGY, 45), new ItemStack(ItemsTC.baubles, 1, 1), new Object[] { new ItemStack(ItemsTC.primalCharm), "gemAmber", new ItemStack(ItemsTC.fabric), "nitor", new ItemStack(ItemsTC.scribingTools) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1103 */     ConfigResearch.recipes.put("RunicRingCharged", ThaumcraftApi.addInfusionCraftingRecipe("RUNICSPECIAL", new ItemStack(ItemsTC.ringRunic, 1, 2), 6, new AspectList().add(Aspect.PROTECT, 25).add(Aspect.ENERGY, 64), new ItemStack(ItemsTC.ringRunic, 1, 1), new Object[] { "shardBalanced", "shardFire", "shardFire", new ItemStack(Items.field_151068_bn, 1, 8226), "shardFire", "shardFire" }));
/*      */     
/*      */ 
/*      */ 
/* 1107 */     ConfigResearch.recipes.put("RunicRingHealing", ThaumcraftApi.addInfusionCraftingRecipe("RUNICSPECIAL", new ItemStack(ItemsTC.ringRunic, 1, 3), 6, new AspectList().add(Aspect.PROTECT, 25).add(Aspect.WATER, 32).add(Aspect.LIFE, 32), new ItemStack(ItemsTC.ringRunic, 1, 1), new Object[] { "shardBalanced", "shardWater", "shardWater", new ItemStack(Items.field_151068_bn, 1, 8257), "shardWater", "shardWater" }));
/*      */     
/*      */ 
/*      */ 
/* 1111 */     ConfigResearch.recipes.put("RunicGirdle", ThaumcraftApi.addInfusionCraftingRecipe("RUNICARMOR", new ItemStack(ItemsTC.girdleRunic, 1, 0), 4, new AspectList().add(Aspect.PROTECT, 40).add(Aspect.ENERGY, 64), new ItemStack(ItemsTC.baubles, 1, 2), new Object[] { new ItemStack(ItemsTC.primalCharm), "gemAmber", new ItemStack(ItemsTC.fabric), "nitor", "nitor", "nitor", new ItemStack(ItemsTC.scribingTools) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1117 */     ConfigResearch.recipes.put("RunicGirdleKinetic", ThaumcraftApi.addInfusionCraftingRecipe("RUNICSPECIAL", new ItemStack(ItemsTC.girdleRunic, 1, 1), 7, new AspectList().add(Aspect.PROTECT, 40).add(Aspect.AIR, 64), new ItemStack(ItemsTC.girdleRunic, 1, 0), new Object[] { "shardBalanced", "shardAir", "shardAir", new ItemStack(Items.field_151068_bn, 1, 16428), "shardAir", "shardAir" }));
/*      */     
/*      */ 
/*      */ 
/* 1121 */     ConfigResearch.recipes.put("RunicGirdleKinetic_2", ThaumcraftApi.addInfusionCraftingRecipe("RUNICSPECIAL", new ItemStack(ItemsTC.girdleRunic, 1, 1), 7, new AspectList().add(Aspect.PROTECT, 40).add(Aspect.AIR, 64), new ItemStack(ItemsTC.girdleRunic, 1, 0), new Object[] { "shardBalanced", "shardAir", "shardAir", new ItemStack(Items.field_151068_bn, 1, 24620), "shardAir", "shardAir" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1126 */     InfusionRunicAugmentRecipe ra = new InfusionRunicAugmentRecipe();
/* 1127 */     ThaumcraftApi.getCraftingRecipes().add(ra);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1137 */     if (Config.allowMirrors) {
/* 1138 */       ConfigResearch.recipes.put("Mirror", ThaumcraftApi.addInfusionCraftingRecipe("MIRROR", new ItemStack(BlocksTC.mirror), 1, new AspectList().add(Aspect.MOTION, 8).add(Aspect.DARKNESS, 8).add(Aspect.EXCHANGE, 8), new ItemStack(ItemsTC.mirroredGlass), new Object[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151079_bi) }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1143 */       ConfigResearch.recipes.put("MirrorHand", ThaumcraftApi.addInfusionCraftingRecipe("MIRRORHAND", new ItemStack(ItemsTC.handMirror), 5, new AspectList().add(Aspect.TOOL, 16).add(Aspect.MOTION, 16), new ItemStack(BlocksTC.mirror), new ItemStack[] { new ItemStack(Items.field_151055_y), new ItemStack(Items.field_151111_aL), new ItemStack(Items.field_151148_bJ) }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1148 */       ConfigResearch.recipes.put("MirrorEssentia", ThaumcraftApi.addInfusionCraftingRecipe("MIRRORESSENTIA", new ItemStack(BlocksTC.mirrorEssentia), 2, new AspectList().add(Aspect.MOTION, 8).add(Aspect.WATER, 8).add(Aspect.EXCHANGE, 8), new ItemStack(ItemsTC.mirroredGlass), new Object[] { "ingotIron", "ingotIron", "ingotIron", new ItemStack(Items.field_151079_bi) }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1155 */     ItemStack isEA = new ItemStack(ItemsTC.elementalAxe);
/* 1156 */     EnumInfusionEnchantment.addInfusionEnchantment(isEA, EnumInfusionEnchantment.COLLECTOR, 1);
/* 1157 */     EnumInfusionEnchantment.addInfusionEnchantment(isEA, EnumInfusionEnchantment.BURROWING, 1);
/* 1158 */     ConfigResearch.recipes.put("ElementalAxe", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALTOOLS", isEA, 1, new AspectList().add(Aspect.WATER, 16).add(Aspect.PLANT, 8), new ItemStack(ItemsTC.thaumiumAxe), new Object[] { "shardWater", "shardWater", new ItemStack(Items.field_151045_i), new ItemStack(BlocksTC.plank) }));
/*      */     
/*      */ 
/*      */ 
/* 1162 */     ItemStack isEP = new ItemStack(ItemsTC.elementalPick);
/* 1163 */     EnumInfusionEnchantment.addInfusionEnchantment(isEP, EnumInfusionEnchantment.REFINING, 1);
/* 1164 */     EnumInfusionEnchantment.addInfusionEnchantment(isEP, EnumInfusionEnchantment.SOUNDING, 2);
/* 1165 */     ConfigResearch.recipes.put("ElementalPick", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALTOOLS", isEP, 1, new AspectList().add(Aspect.FIRE, 8).add(Aspect.METAL, 8).add(Aspect.SENSES, 8), new ItemStack(ItemsTC.thaumiumPick), new Object[] { "shardFire", "shardFire", new ItemStack(Items.field_151045_i), new ItemStack(BlocksTC.plank) }));
/*      */     
/*      */ 
/*      */ 
/* 1169 */     ItemStack isESW = new ItemStack(ItemsTC.elementalSword);
/* 1170 */     EnumInfusionEnchantment.addInfusionEnchantment(isESW, EnumInfusionEnchantment.ARCING, 2);
/* 1171 */     ConfigResearch.recipes.put("ElementalSword", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALTOOLS", isESW, 1, new AspectList().add(Aspect.AIR, 8).add(Aspect.MOTION, 8).add(Aspect.AVERSION, 8), new ItemStack(ItemsTC.thaumiumSword), new Object[] { "shardAir", "shardAir", new ItemStack(Items.field_151045_i), new ItemStack(BlocksTC.plank) }));
/*      */     
/*      */ 
/*      */ 
/* 1175 */     ItemStack isES = new ItemStack(ItemsTC.elementalShovel);
/* 1176 */     EnumInfusionEnchantment.addInfusionEnchantment(isES, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/* 1177 */     ConfigResearch.recipes.put("ElementalShovel", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALTOOLS", isES, 1, new AspectList().add(Aspect.EARTH, 16).add(Aspect.CRAFT, 8), new ItemStack(ItemsTC.thaumiumShovel), new Object[] { "shardEarth", "shardEarth", new ItemStack(Items.field_151045_i), new ItemStack(BlocksTC.plank) }));
/*      */     
/*      */ 
/*      */ 
/* 1181 */     ConfigResearch.recipes.put("ElementalHoe", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALTOOLS", new ItemStack(ItemsTC.elementalHoe), 1, new AspectList().add(Aspect.ORDER, 8).add(Aspect.PLANT, 8).add(Aspect.ENTROPY, 8), new ItemStack(ItemsTC.thaumiumHoe), new Object[] { "shardOrder", "shardEntropy", new ItemStack(Items.field_151045_i), new ItemStack(BlocksTC.plank) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1187 */     InfusionEnchantmentRecipe IEBURROWING = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.BURROWING, new AspectList().add(Aspect.SENSES, 24).add(Aspect.EARTH, 32), new Object[] { new ItemStack(ItemsTC.knowledgeFragment), new ItemStack(Items.field_151134_bR), new ItemStack(Items.field_179556_br) });
/*      */     
/*      */ 
/* 1190 */     ThaumcraftApi.getCraftingRecipes().add(IEBURROWING);
/* 1191 */     ConfigResearch.recipes.put("IEBURROWING", IEBURROWING);
/*      */     
/* 1193 */     InfusionEnchantmentRecipe IECOLLECTOR = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.COLLECTOR, new AspectList().add(Aspect.DESIRE, 16).add(Aspect.WATER, 24), new Object[] { new ItemStack(ItemsTC.knowledgeFragment), new ItemStack(Items.field_151134_bR), new ItemStack(Items.field_151058_ca) });
/*      */     
/*      */ 
/* 1196 */     ThaumcraftApi.getCraftingRecipes().add(IECOLLECTOR);
/* 1197 */     ConfigResearch.recipes.put("IECOLLECTOR", IECOLLECTOR);
/*      */     
/* 1199 */     InfusionEnchantmentRecipe IEDESTRUCTIVE = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.DESTRUCTIVE, new AspectList().add(Aspect.AVERSION, 48).add(Aspect.ENTROPY, 64), new Object[] { new ItemStack(ItemsTC.knowledgeFragment), new ItemStack(Items.field_151134_bR), new ItemStack(Blocks.field_150335_W) });
/*      */     
/*      */ 
/* 1202 */     ThaumcraftApi.getCraftingRecipes().add(IEDESTRUCTIVE);
/* 1203 */     ConfigResearch.recipes.put("IEDESTRUCTIVE", IEDESTRUCTIVE);
/*      */     
/* 1205 */     InfusionEnchantmentRecipe IEREFINING = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.REFINING, new AspectList().add(Aspect.ORDER, 16).add(Aspect.EXCHANGE, 12), new Object[] { new ItemStack(ItemsTC.knowledgeFragment), new ItemStack(Items.field_151134_bR), new ItemStack(ItemsTC.salisMundus) });
/*      */     
/*      */ 
/* 1208 */     ThaumcraftApi.getCraftingRecipes().add(IEREFINING);
/* 1209 */     ConfigResearch.recipes.put("IEREFINING", IEREFINING);
/*      */     
/* 1211 */     InfusionEnchantmentRecipe IESOUNDING = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.SOUNDING, new AspectList().add(Aspect.SENSES, 8).add(Aspect.FIRE, 12), new Object[] { new ItemStack(ItemsTC.knowledgeFragment), new ItemStack(Items.field_151134_bR), new ItemStack(Items.field_151148_bJ) });
/*      */     
/*      */ 
/* 1214 */     ThaumcraftApi.getCraftingRecipes().add(IESOUNDING);
/* 1215 */     ConfigResearch.recipes.put("IESOUNDING", IESOUNDING);
/*      */     
/* 1217 */     InfusionEnchantmentRecipe IEARCING = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.ARCING, new AspectList().add(Aspect.ENERGY, 8).add(Aspect.AIR, 12), new Object[] { new ItemStack(ItemsTC.knowledgeFragment), new ItemStack(Items.field_151134_bR), new ItemStack(Blocks.field_150451_bX) });
/*      */     
/*      */ 
/* 1220 */     ThaumcraftApi.getCraftingRecipes().add(IEARCING);
/* 1221 */     ConfigResearch.recipes.put("IEARCING", IEARCING);
/*      */     
/* 1223 */     InfusionEnchantmentRecipe IEESSENCE = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.ESSENCE, new AspectList().add(Aspect.BEAST, 8).add(Aspect.FLUX, 12), new Object[] { new ItemStack(ItemsTC.knowledgeFragment), new ItemStack(Items.field_151134_bR), new ItemStack(ItemsTC.crystalEssence) });
/*      */     
/*      */ 
/* 1226 */     ThaumcraftApi.getCraftingRecipes().add(IEESSENCE);
/* 1227 */     ConfigResearch.recipes.put("IEESSENCE", IEESSENCE);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1232 */     ConfigResearch.recipes.put("BootsTraveller", ThaumcraftApi.addInfusionCraftingRecipe("BOOTSTRAVELLER", new ItemStack(ItemsTC.travellerBoots), 1, new AspectList().add(Aspect.FLIGHT, 25).add(Aspect.MOTION, 25), new ItemStack(Items.field_151021_T), new Object[] { "shardAir", "shardAir", new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.fabric), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151115_aP, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1238 */     ConfigResearch.recipes.put("MindBiothaumic", ThaumcraftApi.addInfusionCraftingRecipe("MINDBIOTHAUMIC", new ItemStack(ItemsTC.mind, 1, 1), 4, new AspectList().add(Aspect.MIND, 16).add(Aspect.MECHANISM, 16), new ItemStack(ItemsTC.mind, 1, 0), new Object[] { new ItemStack(ItemsTC.brain), new ItemStack(Items.field_151113_aN) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1243 */     ConfigResearch.recipes.put("ArcaneBore", ThaumcraftApi.addInfusionCraftingRecipe("ARCANEBORE", new ItemStack(BlocksTC.arcaneBore), 4, new AspectList().add(Aspect.ENERGY, 16).add(Aspect.EARTH, 32).add(Aspect.MECHANISM, 32).add(Aspect.VOID, 16).add(Aspect.MOTION, 16), new ItemStack(ItemsTC.mind), new Object[] { new ItemStack(BlocksTC.plank), new ItemStack(BlocksTC.plank), "gearBrass", "plateBrass", new ItemStack(Items.field_151046_w), new ItemStack(Items.field_151047_v), "shardAir", "shardEarth" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1251 */     ConfigResearch.recipes.put("AutoCaster", ThaumcraftApi.addInfusionCraftingRecipe("FOCUSTURRET", new ItemStack(ItemsTC.turretPlacer, 1, 1), 5, new AspectList().add(Aspect.AURA, 16).add(Aspect.MECHANISM, 32).add(Aspect.MIND, 16).add(Aspect.SENSES, 16).add(Aspect.AVERSION, 16), new ItemStack(ItemsTC.mind, 1, 1), new Object[] { "shardBalanced", new ItemStack(ItemsTC.morphicResonator), new ItemStack(BlocksTC.plank), "gearBrass", basicWand, "plateBrass", new ItemStack(BlocksTC.plank) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1258 */     ConfigResearch.recipes.put("NodeMagnet", ThaumcraftApi.addInfusionCraftingRecipe("NODEMAGNET", new ItemStack(ItemsTC.turretPlacer, 1, 2), 4, new AspectList().add(Aspect.MECHANISM, 16).add(Aspect.MOTION, 32).add(Aspect.AURA, 64), new ItemStack(ItemsTC.morphicResonator), new Object[] { "gearBrass", "plateBrass", "blockIron", new ItemStack(ItemsTC.mind, 1, 1), new ItemStack(BlocksTC.plank), new ItemStack(BlocksTC.crystalAir), new ItemStack(BlocksTC.crystalFire), new ItemStack(BlocksTC.crystalWater), new ItemStack(BlocksTC.crystalEarth), new ItemStack(BlocksTC.crystalOrder), new ItemStack(BlocksTC.crystalEntropy) }));
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
/*      */ 
/* 1289 */     ConfigResearch.recipes.put("LampGrowth", ThaumcraftApi.addInfusionCraftingRecipe("LAMPGROWTH", new ItemStack(BlocksTC.lampGrowth), 4, new AspectList().add(Aspect.PLANT, 16).add(Aspect.LIGHT, 8).add(Aspect.LIFE, 8).add(Aspect.TOOL, 8), new ItemStack(BlocksTC.lampArcane), new Object[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151100_aR, 1, 15), "shardEarth", new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151100_aR, 1, 15), "shardEarth" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1295 */     ConfigResearch.recipes.put("LampFertility", ThaumcraftApi.addInfusionCraftingRecipe("LAMPFERTILITY", new ItemStack(BlocksTC.lampFertility), 4, new AspectList().add(Aspect.BEAST, 16).add(Aspect.LIGHT, 8).add(Aspect.LIFE, 8).add(Aspect.DESIRE, 8), new ItemStack(BlocksTC.lampArcane), new Object[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151015_O), "shardFire", new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151172_bF), "shardFire" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1301 */     ConfigResearch.recipes.put("ThaumiumFortressHelm", ThaumcraftApi.addInfusionCraftingRecipe("ARMORFORTRESS", new ItemStack(ItemsTC.fortressHelm), 3, new AspectList().add(Aspect.METAL, 24).add(Aspect.PROTECT, 16).add(Aspect.ENERGY, 16), new ItemStack(ItemsTC.thaumiumHelm), new Object[] { "plateThaumium", "plateThaumium", new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151166_bC) }));
/*      */     
/*      */ 
/*      */ 
/* 1305 */     ConfigResearch.recipes.put("ThaumiumFortressChest", ThaumcraftApi.addInfusionCraftingRecipe("ARMORFORTRESS", new ItemStack(ItemsTC.fortressChest), 3, new AspectList().add(Aspect.METAL, 24).add(Aspect.PROTECT, 24).add(Aspect.ENERGY, 16), new ItemStack(ItemsTC.thaumiumChest), new Object[] { "plateThaumium", "plateThaumium", "plateThaumium", "plateThaumium", new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151116_aA) }));
/*      */     
/*      */ 
/*      */ 
/* 1309 */     ConfigResearch.recipes.put("ThaumiumFortressLegs", ThaumcraftApi.addInfusionCraftingRecipe("ARMORFORTRESS", new ItemStack(ItemsTC.fortressLegs), 3, new AspectList().add(Aspect.METAL, 24).add(Aspect.PROTECT, 20).add(Aspect.ENERGY, 16), new ItemStack(ItemsTC.thaumiumLegs), new Object[] { "plateThaumium", "plateThaumium", "plateThaumium", new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151116_aA) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1315 */     ConfigResearch.recipes.put("VoidRobeHelm", ThaumcraftApi.addInfusionCraftingRecipe("ARMORVOIDFORTRESS", new ItemStack(ItemsTC.voidRobeHelm), 6, new AspectList().add(Aspect.METAL, 16).add(Aspect.SENSES, 16).add(Aspect.PROTECT, 16).add(Aspect.ENERGY, 16).add(Aspect.ELDRITCH, 16).add(Aspect.VOID, 16), new ItemStack(ItemsTC.voidHelm), new Object[] { new ItemStack(ItemsTC.goggles), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.fabric) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1321 */     ConfigResearch.recipes.put("VoidRobeChest", ThaumcraftApi.addInfusionCraftingRecipe("ARMORVOIDFORTRESS", new ItemStack(ItemsTC.voidRobeChest), 6, new AspectList().add(Aspect.METAL, 24).add(Aspect.PROTECT, 24).add(Aspect.ENERGY, 16).add(Aspect.ELDRITCH, 16).add(Aspect.VOID, 24), new ItemStack(ItemsTC.voidChest), new Object[] { new ItemStack(ItemsTC.clothChest), "plateVoid", "plateVoid", new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.fabric), new ItemStack(Items.field_151116_aA) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1326 */     ConfigResearch.recipes.put("VoidRobeLegs", ThaumcraftApi.addInfusionCraftingRecipe("ARMORVOIDFORTRESS", new ItemStack(ItemsTC.voidRobeLegs), 6, new AspectList().add(Aspect.METAL, 20).add(Aspect.PROTECT, 20).add(Aspect.ENERGY, 16).add(Aspect.ELDRITCH, 16).add(Aspect.VOID, 20), new ItemStack(ItemsTC.voidLegs), new Object[] { new ItemStack(ItemsTC.clothLegs), "plateVoid", "plateVoid", new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.fabric), new ItemStack(Items.field_151116_aA) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1333 */     ConfigResearch.recipes.put("HelmGoggles", ThaumcraftApi.addInfusionCraftingRecipe("HELMGOGGLES", new Object[] { "goggles", new NBTTagByte(1) }, 5, new AspectList().add(Aspect.SENSES, 32).add(Aspect.AURA, 16).add(Aspect.PROTECT, 16), new ItemStack(ItemsTC.fortressHelm, 1, 32767), new Object[] { new ItemStack(Items.field_151123_aH), new ItemStack(ItemsTC.goggles, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1340 */     ConfigResearch.recipes.put("MaskGrinningDevil", ThaumcraftApi.addInfusionCraftingRecipe("MASKGRINNINGDEVIL", new Object[] { "mask", new NBTTagInt(0) }, 8, new AspectList().add(Aspect.MIND, 64).add(Aspect.LIFE, 64).add(Aspect.PROTECT, 16), new ItemStack(ItemsTC.fortressHelm, 1, 32767), new Object[] { new ItemStack(Items.field_151100_aR, 1, 0), "ingotIron", new ItemStack(Items.field_151116_aA), new ItemStack(BlocksTC.shimmerleaf), new ItemStack(ItemsTC.brain), "ingotIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1348 */     ConfigResearch.recipes.put("MaskAngryGhost", ThaumcraftApi.addInfusionCraftingRecipe("MASKANGRYGHOST", new Object[] { "mask", new NBTTagInt(1) }, 8, new AspectList().add(Aspect.ENTROPY, 64).add(Aspect.DEATH, 64).add(Aspect.PROTECT, 16), new ItemStack(ItemsTC.fortressHelm, 1, 32767), new Object[] { new ItemStack(Items.field_151100_aR, 1, 15), "ingotIron", new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151144_bL, 1, 1), "ingotIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1356 */     ConfigResearch.recipes.put("MaskSippingFiend", ThaumcraftApi.addInfusionCraftingRecipe("MASKSIPPINGFIEND", new Object[] { "mask", new NBTTagInt(2) }, 8, new AspectList().add(Aspect.UNDEAD, 64).add(Aspect.LIFE, 64).add(Aspect.PROTECT, 16), new ItemStack(ItemsTC.fortressHelm, 1, 32767), new Object[] { new ItemStack(Items.field_151100_aR, 1, 1), "ingotIron", new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151117_aB), "ingotIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1366 */     ConfigResearch.recipes.put("SanityCheck", ThaumcraftApi.addInfusionCraftingRecipe("SANITYCHECK", new ItemStack(ItemsTC.sanityChecker), 4, new AspectList().add(Aspect.MIND, 24).add(Aspect.SENSES, 24).add(Aspect.ELDRITCH, 8), new ItemStack(ItemsTC.thaumometer, 1, 32767), new ItemStack[] { new ItemStack(ItemsTC.mirroredGlass), new ItemStack(ItemsTC.brain), new ItemStack(ItemsTC.morphicResonator) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1371 */     ConfigResearch.recipes.put("SinStone", ThaumcraftApi.addInfusionCraftingRecipe("SINSTONE", new ItemStack(ItemsTC.sinisterStone), 5, new AspectList().add(Aspect.SENSES, 8).add(Aspect.DARKNESS, 8).add(Aspect.ELDRITCH, 8).add(Aspect.AURA, 8), new ItemStack(Items.field_151145_ak), new Object[] { "nitor", "shardOrder", new ItemStack(ItemsTC.knowledgeFragment), "shardEntropy" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1376 */     ItemStack isPC = new ItemStack(ItemsTC.primalCrusher);
/* 1377 */     EnumInfusionEnchantment.addInfusionEnchantment(isPC, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/* 1378 */     EnumInfusionEnchantment.addInfusionEnchantment(isPC, EnumInfusionEnchantment.REFINING, 1);
/* 1379 */     ConfigResearch.recipes.put("PrimalCrusher", ThaumcraftApi.addInfusionCraftingRecipe("PRIMALCRUSHER", isPC, 6, new AspectList().add(Aspect.EARTH, 24).add(Aspect.TOOL, 24).add(Aspect.ENTROPY, 16).add(Aspect.VOID, 16).add(Aspect.AVERSION, 16).add(Aspect.ELDRITCH, 16).add(Aspect.DESIRE, 16), new ItemStack(ItemsTC.primordialPearl), new ItemStack[] { new ItemStack(ItemsTC.primalCharm), new ItemStack(ItemsTC.voidPick, 1, 32767), new ItemStack(ItemsTC.voidShovel, 1, 32767), new ItemStack(ItemsTC.primalCharm), new ItemStack(ItemsTC.elementalPick, 1, 32767), new ItemStack(ItemsTC.elementalShovel, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1387 */     ConfigResearch.recipes.put("EldritchEye", ThaumcraftApi.addInfusionCraftingRecipe("OCULUS", new ItemStack(ItemsTC.eldritchEye), 5, new AspectList().add(Aspect.ELDRITCH, 64).add(Aspect.VOID, 16).add(Aspect.DARKNESS, 16).add(Aspect.MOTION, 16), new ItemStack(Items.field_151061_bv), new ItemStack[] { new ItemStack(ItemsTC.voidSeed), new ItemStack(Items.field_151043_k) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1392 */     ConfigResearch.recipes.put("VerdantHeart", ThaumcraftApi.addInfusionCraftingRecipe("VERDANTHEART", new ItemStack(ItemsTC.ringVerdant), 5, new AspectList().add(Aspect.LIFE, 48).add(Aspect.ORDER, 24).add(Aspect.PLANT, 48), new ItemStack(ItemsTC.baubles, 1, 10), new Object[] { new ItemStack(ItemsTC.primordialPearl), Utils.makeShard(Aspect.LIFE), new ItemStack(Items.field_151117_aB), Utils.makeShard(Aspect.PLANT) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1397 */     ConfigResearch.recipes.put("VerdantHeartLife", ThaumcraftApi.addInfusionCraftingRecipe("VERDANTHEARTLIFE", new Object[] { "type", new NBTTagByte(1) }, 5, new AspectList().add(Aspect.LIFE, 64).add(Aspect.MAN, 64), new ItemStack(ItemsTC.ringVerdant, 1, 32767), new Object[] { new ItemStack(Items.field_151153_ao), Utils.makeShard(Aspect.LIFE), new ItemStack(Items.field_151068_bn, 1, 8193), Utils.makeShard(Aspect.MAN) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1403 */     ConfigResearch.recipes.put("VerdantHeartSustain", ThaumcraftApi.addInfusionCraftingRecipe("VERDANTHEARTLIFE", new Object[] { "type", new NBTTagByte(2) }, 5, new AspectList().add(Aspect.DESIRE, 64).add(Aspect.AIR, 64), new ItemStack(ItemsTC.ringVerdant, 1, 32767), new Object[] { new ItemStack(ItemsTC.tripleMeatTreat), Utils.makeShard(Aspect.DESIRE), new ItemStack(Items.field_151068_bn, 1, 8237), Utils.makeShard(Aspect.AIR) }));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initializeNormalRecipes()
/*      */   {
/* 1413 */     GameRegistry.addRecipe(new RecipesRobeArmorDyes());
/* 1414 */     GameRegistry.addRecipe(new RecipesVoidRobeArmorDyes());
/*      */     
/* 1416 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 0), new Object[] { "#", Character.valueOf('#'), Items.field_151042_j });
/* 1417 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 6), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 0) });
/* 1418 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 7), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 1) });
/* 1419 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 8), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 2) });
/* 1420 */     oreDictRecipe(new ItemStack(Items.field_151042_j), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetIron" });
/* 1421 */     oreDictRecipe(new ItemStack(ItemsTC.ingots, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetThaumium" });
/* 1422 */     oreDictRecipe(new ItemStack(ItemsTC.ingots, 1, 1), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetVoid" });
/* 1423 */     oreDictRecipe(new ItemStack(ItemsTC.ingots, 1, 2), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetBrass" });
/* 1424 */     oreDictRecipe(new ItemStack(ItemsTC.quicksilver), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetQuicksilver" });
/*      */     
/* 1426 */     oreDictRecipe(new ItemStack(Items.field_151043_k), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.coin) });
/*      */     
/* 1428 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.nuggets, 9, 5), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.quicksilver) });
/*      */     
/* 1430 */     oreDictRecipe(new ItemStack(BlocksTC.metal, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 0) });
/* 1431 */     oreDictRecipe(new ItemStack(ItemsTC.ingots, 9, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.metal, 1, 0) });
/*      */     
/* 1433 */     oreDictRecipe(new ItemStack(BlocksTC.metal, 1, 1), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 1) });
/* 1434 */     oreDictRecipe(new ItemStack(ItemsTC.ingots, 9, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.metal, 1, 1) });
/*      */     
/* 1436 */     oreDictRecipe(new ItemStack(BlocksTC.metal, 1, 4), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 2) });
/* 1437 */     oreDictRecipe(new ItemStack(ItemsTC.ingots, 9, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.metal, 1, 4) });
/*      */     
/* 1439 */     oreDictRecipe(new ItemStack(BlocksTC.fleshBlock), new Object[] { "###", "###", "###", Character.valueOf('#'), Items.field_151078_bh });
/* 1440 */     oreDictRecipe(new ItemStack(Items.field_151078_bh, 9, 0), new Object[] { "#", Character.valueOf('#'), BlocksTC.fleshBlock });
/*      */     
/* 1442 */     oreDictRecipe(new ItemStack(BlocksTC.translucent, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), "gemAmber" });
/* 1443 */     oreDictRecipe(new ItemStack(BlocksTC.translucent, 4, 1), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(BlocksTC.translucent, 1, 0) });
/* 1444 */     oreDictRecipe(new ItemStack(BlocksTC.translucent, 4, 0), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(BlocksTC.translucent, 1, 1) });
/* 1445 */     oreDictRecipe(new ItemStack(ItemsTC.amber, 9, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.translucent, 1, 0) });
/*      */     
/* 1447 */     ConfigResearch.recipes.put("BrassPlate", oreDictRecipe(new ItemStack(ItemsTC.plate, 3, 0), new Object[] { "BBB", Character.valueOf('B'), "ingotBrass" }));
/*      */     
/* 1449 */     ConfigResearch.recipes.put("BrassGear", oreDictRecipe(new ItemStack(ItemsTC.gear, 1, 0), new Object[] { "BBB", "BIB", "BBB", Character.valueOf('I'), "ingotIron", Character.valueOf('B'), "nuggetBrass" }));
/*      */     
/*      */ 
/* 1452 */     ConfigResearch.recipes.put("IronPlate", oreDictRecipe(new ItemStack(ItemsTC.plate, 3, 1), new Object[] { "BBB", Character.valueOf('B'), "ingotIron" }));
/*      */     
/*      */ 
/* 1455 */     ConfigResearch.recipes.put("ThaumiumPlate", oreDictRecipe(new ItemStack(ItemsTC.plate, 3, 2), new Object[] { "BBB", Character.valueOf('B'), "ingotThaumium" }));
/*      */     
/* 1457 */     ConfigResearch.recipes.put("ThaumiumGear", oreDictRecipe(new ItemStack(ItemsTC.gear, 1, 1), new Object[] { "BBB", "BIB", "BBB", Character.valueOf('I'), "ingotIron", Character.valueOf('B'), "nuggetThaumium" }));
/*      */     
/*      */ 
/* 1460 */     ConfigResearch.recipes.put("VoidPlate", oreDictRecipe(new ItemStack(ItemsTC.plate, 3, 3), new Object[] { "BBB", Character.valueOf('B'), "ingotVoid" }));
/*      */     
/* 1462 */     ConfigResearch.recipes.put("VoidGear", oreDictRecipe(new ItemStack(ItemsTC.gear, 1, 2), new Object[] { "BBB", "BIB", "BBB", Character.valueOf('I'), "ingotIron", Character.valueOf('B'), "nuggetVoid" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1467 */     ConfigResearch.recipes.put("MundaneAmulet", oreDictRecipe(new ItemStack(ItemsTC.baubles, 1, 0), new Object[] { " S ", "S S", " I ", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('I'), "ingotBrass" }));
/*      */     
/*      */ 
/* 1470 */     ConfigResearch.recipes.put("MundaneRing", oreDictRecipe(new ItemStack(ItemsTC.baubles, 1, 1), new Object[] { "NNN", "N N", "NNN", Character.valueOf('N'), "nuggetBrass" }));
/*      */     
/* 1472 */     ConfigResearch.recipes.put("MundaneBelt", oreDictRecipe(new ItemStack(ItemsTC.baubles, 1, 2), new Object[] { " L ", "L L", " I ", Character.valueOf('L'), new ItemStack(Items.field_151116_aA), Character.valueOf('I'), "ingotBrass" }));
/*      */     
/*      */ 
/* 1475 */     ConfigResearch.recipes.put("FancyAmulet", oreDictRecipe(new ItemStack(ItemsTC.baubles, 1, 9), new Object[] { " S ", "SGS", " I ", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('G'), new ItemStack(Items.field_151045_i), Character.valueOf('I'), "ingotGold" }));
/*      */     
/*      */ 
/* 1478 */     ConfigResearch.recipes.put("FancyRing", oreDictRecipe(new ItemStack(ItemsTC.baubles, 1, 10), new Object[] { "NGN", "N N", "NNN", Character.valueOf('G'), new ItemStack(Items.field_151045_i), Character.valueOf('N'), "nuggetGold" }));
/*      */     
/*      */ 
/* 1481 */     ConfigResearch.recipes.put("FancyBelt", oreDictRecipe(new ItemStack(ItemsTC.baubles, 1, 11), new Object[] { " L ", "LGL", " I ", Character.valueOf('L'), new ItemStack(Items.field_151116_aA), Character.valueOf('G'), new ItemStack(Items.field_151045_i), Character.valueOf('I'), "ingotGold" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1486 */     CraftingManager.func_77594_a().func_180302_a(new RecipeTripleMeatTreat());
/*      */     
/*      */ 
/* 1489 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ItemsTC.quicksilver), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.shimmerleaf) });
/*      */     
/* 1491 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(Items.field_151065_br), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.cinderpearl) });
/*      */     
/*      */ 
/*      */ 
/* 1495 */     ConfigResearch.recipes.put("JarLabel", shapelessOreDictRecipe(new ItemStack(ItemsTC.label, 4, 0), new Object[] { "dyeBlack", "slimeball", Items.field_151121_aF, Items.field_151121_aF, Items.field_151121_aF, Items.field_151121_aF }));
/*      */     
/* 1497 */     int count = 0;
/* 1498 */     for (Aspect aspect : Aspect.aspects.values()) {
/* 1499 */       ItemStack essence = new ItemStack(ItemsTC.phial, 1, 1);
/* 1500 */       ((IEssentiaContainerItem)essence.func_77973_b()).setAspects(essence, new AspectList().add(aspect, 8));
/* 1501 */       ItemStack output = new ItemStack(ItemsTC.label, 1, 1);
/* 1502 */       ((IEssentiaContainerItem)output.func_77973_b()).setAspects(output, new AspectList().add(aspect, 1));
/*      */       
/* 1504 */       ConfigResearch.recipes.put("JarLabel" + count, shapelessNBTOreRecipe(output, new Object[] { new ItemStack(ItemsTC.label), essence }));
/*      */       
/* 1506 */       count++;
/*      */     }
/*      */     
/* 1509 */     ConfigResearch.recipes.put("JarLabelNull", shapelessOreDictRecipe(new ItemStack(ItemsTC.label), new Object[] { new ItemStack(ItemsTC.label, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/* 1513 */     ConfigResearch.recipes.put("WandBasic", oreDictRecipe(basicWand, new Object[] { "  I", " S ", "I  ", Character.valueOf('I'), new ItemStack(ItemsTC.wandCaps, 1, 0), Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/* 1516 */     ConfigResearch.recipes.put("WandCapIron", oreDictRecipe(new ItemStack(ItemsTC.wandCaps, 1, 0), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1521 */     ConfigResearch.recipes.put("PlankGreatwood", GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.plank, 4, 0), new Object[] { "W", Character.valueOf('W'), new ItemStack(BlocksTC.log, 1, 0) }));
/*      */     
/* 1523 */     ConfigResearch.recipes.put("PlankSilverwood", GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.plank, 4, 1), new Object[] { "W", Character.valueOf('W'), new ItemStack(BlocksTC.log, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/* 1527 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.stairsGreatwood, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.plank, 1, 0) });
/*      */     
/* 1529 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.stairsSilverwood, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.plank, 1, 1) });
/*      */     
/* 1531 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.stairsArcane, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 0) });
/*      */     
/* 1533 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.stairsArcaneBrick, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 1) });
/*      */     
/* 1535 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.stairsAncient, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 2) });
/*      */     
/*      */ 
/*      */ 
/* 1539 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.slabWood, 6, 0), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.plank, 1, 0) });
/*      */     
/* 1541 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.slabWood, 6, 1), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.plank, 1, 1) });
/*      */     
/* 1543 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.slabStone, 6, 0), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 0) });
/*      */     
/* 1545 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.slabStone, 6, 1), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 1) });
/*      */     
/* 1547 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.slabStone, 6, 2), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 2) });
/*      */     
/* 1549 */     GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.slabStone, 6, 3), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(BlocksTC.stone, 1, 4) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1554 */     ConfigResearch.recipes.put("Phial", oreDictRecipe(new ItemStack(ItemsTC.phial, 8, 0), new Object[] { " C ", "G G", " G ", Character.valueOf('G'), "blockGlass", Character.valueOf('C'), Items.field_151119_aD }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1559 */     ConfigResearch.recipes.put("TableWood", oreDictRecipe(new ItemStack(BlocksTC.tableWood), new Object[] { "SSS", "W W", Character.valueOf('S'), "slabWood", Character.valueOf('W'), "plankWood" }));
/*      */     
/* 1561 */     ConfigResearch.recipes.put("TableStone", oreDictRecipe(new ItemStack(BlocksTC.tableStone), new Object[] { "SSS", "W W", Character.valueOf('S'), new ItemStack(Blocks.field_150333_U), Character.valueOf('W'), "stone" }));
/*      */     
/*      */ 
/*      */ 
/* 1565 */     ConfigResearch.recipes.put("Scribe1", shapelessOreDictRecipe(new ItemStack(ItemsTC.scribingTools), new Object[] { new ItemStack(ItemsTC.phial, 1, 0), Items.field_151008_G, "dyeBlack" }));
/*      */     
/* 1567 */     ConfigResearch.recipes.put("Scribe2", shapelessOreDictRecipe(new ItemStack(ItemsTC.scribingTools), new Object[] { Items.field_151069_bo, Items.field_151008_G, "dyeBlack" }));
/*      */     
/* 1569 */     ConfigResearch.recipes.put("Scribe3", shapelessOreDictRecipe(new ItemStack(ItemsTC.scribingTools), new Object[] { new ItemStack(ItemsTC.scribingTools, 1, 32767), "dyeBlack" }));
/*      */     
/*      */ 
/*      */ 
/* 1573 */     ConfigResearch.recipes.put("Thaumometer", oreDictRecipe(new ItemStack(ItemsTC.thaumometer), new Object[] { " 1 ", "IGI", " 1 ", Character.valueOf('I'), Items.field_151043_k, Character.valueOf('G'), new ItemStack(Blocks.field_150359_w), Character.valueOf('1'), new ItemStack(ItemsTC.shard, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1580 */     ConfigResearch.recipes.put("ThaumiumHelm", oreDictRecipe(new ItemStack(ItemsTC.thaumiumHelm, 1), new Object[] { "III", "I I", Character.valueOf('I'), "ingotThaumium" }));
/* 1581 */     ConfigResearch.recipes.put("ThaumiumChest", oreDictRecipe(new ItemStack(ItemsTC.thaumiumChest, 1), new Object[] { "I I", "III", "III", Character.valueOf('I'), "ingotThaumium" }));
/* 1582 */     ConfigResearch.recipes.put("ThaumiumLegs", oreDictRecipe(new ItemStack(ItemsTC.thaumiumLegs, 1), new Object[] { "III", "I I", "I I", Character.valueOf('I'), "ingotThaumium" }));
/* 1583 */     ConfigResearch.recipes.put("ThaumiumBoots", oreDictRecipe(new ItemStack(ItemsTC.thaumiumBoots, 1), new Object[] { "I I", "I I", Character.valueOf('I'), "ingotThaumium" }));
/* 1584 */     ConfigResearch.recipes.put("ThaumiumShovel", oreDictRecipe(new ItemStack(ItemsTC.thaumiumShovel, 1), new Object[] { "I", "S", "S", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/* 1585 */     ConfigResearch.recipes.put("ThaumiumPick", oreDictRecipe(new ItemStack(ItemsTC.thaumiumPick, 1), new Object[] { "III", " S ", " S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/* 1586 */     ConfigResearch.recipes.put("ThaumiumAxe", oreDictRecipe(new ItemStack(ItemsTC.thaumiumAxe, 1), new Object[] { "II", "SI", "S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/* 1587 */     ConfigResearch.recipes.put("ThaumiumHoe", oreDictRecipe(new ItemStack(ItemsTC.thaumiumHoe, 1), new Object[] { "II", "S ", "S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/* 1588 */     ConfigResearch.recipes.put("ThaumiumSword", oreDictRecipe(new ItemStack(ItemsTC.thaumiumSword, 1), new Object[] { "I", "I", "S", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/* 1591 */     ConfigResearch.recipes.put("VoidHelm", oreDictRecipe(new ItemStack(ItemsTC.voidHelm, 1), new Object[] { "III", "I I", Character.valueOf('I'), "ingotVoid" }));
/* 1592 */     ConfigResearch.recipes.put("VoidChest", oreDictRecipe(new ItemStack(ItemsTC.voidChest, 1), new Object[] { "I I", "III", "III", Character.valueOf('I'), "ingotVoid" }));
/* 1593 */     ConfigResearch.recipes.put("VoidLegs", oreDictRecipe(new ItemStack(ItemsTC.voidLegs, 1), new Object[] { "III", "I I", "I I", Character.valueOf('I'), "ingotVoid" }));
/* 1594 */     ConfigResearch.recipes.put("VoidBoots", oreDictRecipe(new ItemStack(ItemsTC.voidBoots, 1), new Object[] { "I I", "I I", Character.valueOf('I'), "ingotVoid" }));
/* 1595 */     ConfigResearch.recipes.put("VoidShovel", oreDictRecipe(new ItemStack(ItemsTC.voidShovel, 1), new Object[] { "I", "S", "S", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/* 1596 */     ConfigResearch.recipes.put("VoidPick", oreDictRecipe(new ItemStack(ItemsTC.voidPick, 1), new Object[] { "III", " S ", " S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/* 1597 */     ConfigResearch.recipes.put("VoidAxe", oreDictRecipe(new ItemStack(ItemsTC.voidAxe, 1), new Object[] { "II", "SI", "S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/* 1598 */     ConfigResearch.recipes.put("VoidHoe", oreDictRecipe(new ItemStack(ItemsTC.voidHoe, 1), new Object[] { "II", "S ", "S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/* 1599 */     ConfigResearch.recipes.put("VoidSword", oreDictRecipe(new ItemStack(ItemsTC.voidSword, 1), new Object[] { "I", "I", "S", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/* 1602 */     ConfigResearch.recipes.put("TallowCandle", GameRegistry.addShapedRecipe(new ItemStack(BlocksTC.candle, 3, 0), new Object[] { " S ", " T ", " T ", Character.valueOf('S'), Items.field_151007_F, Character.valueOf('T'), new ItemStack(ItemsTC.tallow) }));
/*      */     
/* 1604 */     for (int a = 1; a < 16; a++) {
/* 1605 */       shapelessOreDictRecipe(new ItemStack(BlocksTC.candle, 1, a), new Object[] { ConfigAspects.dyes[(15 - a)], new ItemStack(BlocksTC.candle, 1, 0) });
/*      */     }
/* 1607 */     GameRegistry.addShapelessRecipe(new ItemStack(BlocksTC.candle, 1, 0), new Object[] { new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(BlocksTC.candle, 1, 32767) });
/*      */     
/*      */ 
/* 1610 */     ConfigResearch.recipes.put("BrassBrace", oreDictRecipe(new ItemStack(ItemsTC.jarBrace, 2), new Object[] { "NSN", "S S", "NSN", Character.valueOf('N'), "nuggetBrass", Character.valueOf('S'), "stickWood" }));
/*      */   }
/*      */   
/*      */   private static void initializeSmelting()
/*      */   {
/* 1615 */     GameRegistry.addSmelting(BlocksTC.oreCinnabar, new ItemStack(ItemsTC.quicksilver), 1.0F);
/* 1616 */     GameRegistry.addSmelting(BlocksTC.oreAmber, new ItemStack(ItemsTC.amber), 1.0F);
/* 1617 */     GameRegistry.addSmelting(BlocksTC.log, new ItemStack(Items.field_151044_h, 1, 1), 0.5F);
/* 1618 */     GameRegistry.addSmelting(ItemsTC.coin, new ItemStack(Items.field_151074_bl), 0.0F);
/* 1619 */     GameRegistry.addSmelting(new ItemStack(ItemsTC.clusters, 1, 0), new ItemStack(Items.field_151042_j, 2, 0), 1.0F);
/* 1620 */     GameRegistry.addSmelting(new ItemStack(ItemsTC.clusters, 1, 1), new ItemStack(Items.field_151043_k, 2, 0), 1.0F);
/* 1621 */     GameRegistry.addSmelting(new ItemStack(ItemsTC.clusters, 1, 6), new ItemStack(ItemsTC.quicksilver, 2, 0), 1.0F);
/* 1622 */     GameRegistry.addSmelting(new ItemStack(ItemsTC.shard, 1, 7), new ItemStack(ItemsTC.salisMundus), 1.0F);
/*      */     
/* 1624 */     ThaumcraftApi.addSmeltingBonus("oreGold", new ItemStack(Items.field_151074_bl, 0, 0));
/* 1625 */     ThaumcraftApi.addSmeltingBonus("oreIron", new ItemStack(ItemsTC.nuggets, 0, 0));
/* 1626 */     ThaumcraftApi.addSmeltingBonus("oreCinnabar", new ItemStack(ItemsTC.nuggets, 0, 5));
/* 1627 */     ThaumcraftApi.addSmeltingBonus("oreCopper", new ItemStack(ItemsTC.nuggets, 0, 1));
/* 1628 */     ThaumcraftApi.addSmeltingBonus("oreTin", new ItemStack(ItemsTC.nuggets, 0, 2));
/* 1629 */     ThaumcraftApi.addSmeltingBonus("oreSilver", new ItemStack(ItemsTC.nuggets, 0, 3));
/* 1630 */     ThaumcraftApi.addSmeltingBonus("oreLead", new ItemStack(ItemsTC.nuggets, 0, 4));
/*      */     
/* 1632 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 0), new ItemStack(ItemsTC.nuggets, 0, 0));
/* 1633 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 1), new ItemStack(Items.field_151074_bl, 0, 0));
/*      */     
/* 1635 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 6), new ItemStack(ItemsTC.nuggets, 0, 5));
/* 1636 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 2), new ItemStack(ItemsTC.nuggets, 0, 1));
/* 1637 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 3), new ItemStack(ItemsTC.nuggets, 0, 2));
/* 1638 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 4), new ItemStack(ItemsTC.nuggets, 0, 3));
/* 1639 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 5), new ItemStack(ItemsTC.nuggets, 0, 4));
/*      */     
/* 1641 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151082_bd), new ItemStack(ItemsTC.chunks, 0, 0));
/* 1642 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151076_bf), new ItemStack(ItemsTC.chunks, 0, 1));
/* 1643 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151147_al), new ItemStack(ItemsTC.chunks, 0, 2));
/* 1644 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151115_aP, 1, 32767), new ItemStack(ItemsTC.chunks, 0, 3));
/* 1645 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_179558_bo), new ItemStack(ItemsTC.chunks, 0, 4));
/* 1646 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_179561_bm), new ItemStack(ItemsTC.chunks, 0, 5));
/*      */   }
/*      */   
/*      */   static IRecipe oreDictRecipe(ItemStack res, Object[] params) {
/* 1650 */     IRecipe rec = new net.minecraftforge.oredict.ShapedOreRecipe(res, params);
/* 1651 */     CraftingManager.func_77594_a().func_77592_b().add(rec);
/* 1652 */     return rec;
/*      */   }
/*      */   
/*      */   static IRecipe shapelessOreDictRecipe(ItemStack res, Object[] params) {
/* 1656 */     IRecipe rec = new net.minecraftforge.oredict.ShapelessOreRecipe(res, params);
/* 1657 */     CraftingManager.func_77594_a().func_77592_b().add(rec);
/* 1658 */     return rec;
/*      */   }
/*      */   
/*      */   static IRecipe shapelessNBTOreRecipe(ItemStack res, Object[] params) {
/* 1662 */     IRecipe rec = new ShapelessNBTOreRecipe(res, params);
/* 1663 */     CraftingManager.func_77594_a().func_77592_b().add(rec);
/* 1664 */     return rec;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/config/ConfigRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */