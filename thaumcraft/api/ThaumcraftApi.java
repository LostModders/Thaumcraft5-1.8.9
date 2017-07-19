/*     */ package thaumcraft.api;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*     */ import thaumcraft.api.crafting.ShapelessArcaneRecipe;
/*     */ import thaumcraft.api.internal.DummyInternalMethodHandler;
/*     */ import thaumcraft.api.internal.IInternalMethodHandler;
/*     */ import thaumcraft.api.internal.WeightedRandomLoot;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategoryList;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.api.research.ResearchPage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThaumcraftApi
/*     */ {
/*  43 */   public static IInternalMethodHandler internalMethods = new DummyInternalMethodHandler();
/*     */   
/*     */ 
/*  46 */   public static ArrayList<EntityTags> scanEntities = new ArrayList();
/*     */   
/*     */   public static class EntityTagsNBT {
/*  49 */     public EntityTagsNBT(String name, Object value) { this.name = name;
/*  50 */       this.value = value; }
/*     */     
/*     */     public String name;
/*     */     public Object value; }
/*     */   
/*     */   public static class EntityTags { public String entityName;
/*     */     
/*  57 */     public EntityTags(String entityName, AspectList aspects, ThaumcraftApi.EntityTagsNBT... nbts) { this.entityName = entityName;
/*  58 */       this.nbts = nbts;
/*  59 */       this.aspects = aspects;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public ThaumcraftApi.EntityTagsNBT[] nbts;
/*     */     
/*     */ 
/*     */ 
/*     */     public AspectList aspects;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerEntityTag(String entityName, AspectList aspects, EntityTagsNBT... nbt)
/*     */   {
/*  78 */     scanEntities.add(new EntityTags(entityName, aspects, nbt));
/*     */   }
/*     */   
/*     */ 
/*  82 */   private static ArrayList craftingRecipes = new ArrayList();
/*  83 */   private static HashMap<Object, ItemStack> smeltingBonus = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addSmeltingBonus(ItemStack in, ItemStack out)
/*     */   {
/*  92 */     smeltingBonus.put(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77952_i()) }), new ItemStack(out.func_77973_b(), 0, out.func_77952_i()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addSmeltingBonus(String in, ItemStack out)
/*     */   {
/* 104 */     smeltingBonus.put(in, new ItemStack(out.func_77973_b(), 0, out.func_77952_i()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ItemStack getSmeltingBonus(ItemStack in)
/*     */   {
/* 113 */     ItemStack out = (ItemStack)smeltingBonus.get(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77952_i()) }));
/* 114 */     if (out == null) {
/* 115 */       out = (ItemStack)smeltingBonus.get(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(32767) }));
/*     */     }
/* 117 */     if (out == null)
/* 118 */       for (int id : OreDictionary.getOreIDs(in)) {
/* 119 */         String od = OreDictionary.getOreName(id);
/* 120 */         out = (ItemStack)smeltingBonus.get(od);
/* 121 */         if (out != null)
/*     */           break;
/*     */       }
/* 124 */     return out;
/*     */   }
/*     */   
/*     */   public static List getCraftingRecipes() {
/* 128 */     return craftingRecipes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShapedArcaneRecipe addArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object... recipe)
/*     */   {
/* 139 */     ShapedArcaneRecipe r = new ShapedArcaneRecipe(research, result, aspects, recipe);
/* 140 */     craftingRecipes.add(r);
/* 141 */     CraftingManager.func_77594_a().func_180302_a(r);
/* 142 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShapedArcaneRecipe addArcaneCraftingRecipe(String[] research, ItemStack result, AspectList aspects, Object... recipe)
/*     */   {
/* 153 */     ShapedArcaneRecipe r = new ShapedArcaneRecipe(research, result, aspects, recipe);
/* 154 */     craftingRecipes.add(r);
/* 155 */     CraftingManager.func_77594_a().func_180302_a(r);
/* 156 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShapelessArcaneRecipe addShapelessArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object... recipe)
/*     */   {
/* 167 */     ShapelessArcaneRecipe r = new ShapelessArcaneRecipe(research, result, aspects, recipe);
/* 168 */     craftingRecipes.add(r);
/* 169 */     CraftingManager.func_77594_a().func_180302_a(r);
/* 170 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShapelessArcaneRecipe addShapelessArcaneCraftingRecipe(String[] research, ItemStack result, AspectList aspects, Object... recipe)
/*     */   {
/* 181 */     ShapelessArcaneRecipe r = new ShapelessArcaneRecipe(research, result, aspects, recipe);
/* 182 */     craftingRecipes.add(r);
/* 183 */     CraftingManager.func_77594_a().func_180302_a(r);
/* 184 */     return r;
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
/*     */   public static InfusionRecipe addInfusionCraftingRecipe(String research, Object result, int instability, AspectList aspects, Object input, Object[] recipe)
/*     */   {
/* 201 */     if ((!(result instanceof ItemStack)) && (!(result instanceof Object[]))) return null;
/* 202 */     InfusionRecipe r = new InfusionRecipe(research, result, instability, aspects, input, recipe);
/* 203 */     craftingRecipes.add(r);
/* 204 */     return r;
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
/*     */   public static InfusionRecipe addInfusionCraftingRecipe(String[] research, Object result, int instability, AspectList aspects, Object input, Object[] recipe)
/*     */   {
/* 221 */     if ((!(result instanceof ItemStack)) && (!(result instanceof Object[]))) return null;
/* 222 */     InfusionRecipe r = new InfusionRecipe(research, result, instability, aspects, input, recipe);
/* 223 */     craftingRecipes.add(r);
/* 224 */     return r;
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
/*     */   public static InfusionRecipe getInfusionRecipe(ItemStack res)
/*     */   {
/* 249 */     for (Object r : ) {
/* 250 */       if (((r instanceof InfusionRecipe)) && 
/* 251 */         ((((InfusionRecipe)r).getRecipeOutput() instanceof ItemStack)) && 
/* 252 */         (((ItemStack)((InfusionRecipe)r).getRecipeOutput()).func_77969_a(res))) {
/* 253 */         return (InfusionRecipe)r;
/*     */       }
/*     */     }
/*     */     
/* 257 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CrucibleRecipe addCrucibleRecipe(String key, ItemStack result, Object catalyst, AspectList tags)
/*     */   {
/* 268 */     return addCrucibleRecipe(new String[] { key }, result, catalyst, tags);
/*     */   }
/*     */   
/*     */   public static CrucibleRecipe addCrucibleRecipe(String[] keys, ItemStack result, Object catalyst, AspectList tags) {
/* 272 */     CrucibleRecipe rc = new CrucibleRecipe(keys, result, catalyst, tags);
/* 273 */     getCraftingRecipes().add(rc);
/* 274 */     return rc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CrucibleRecipe getCrucibleRecipe(ItemStack stack)
/*     */   {
/* 282 */     for (Object r : ) {
/* 283 */       if (((r instanceof CrucibleRecipe)) && 
/* 284 */         (((CrucibleRecipe)r).getRecipeOutput().func_77969_a(stack))) {
/* 285 */         return (CrucibleRecipe)r;
/*     */       }
/*     */     }
/* 288 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CrucibleRecipe getCrucibleRecipeFromHash(int hash)
/*     */   {
/* 296 */     for (Object r : ) {
/* 297 */       if (((r instanceof CrucibleRecipe)) && 
/* 298 */         (((CrucibleRecipe)r).hash == hash)) {
/* 299 */         return (CrucibleRecipe)r;
/*     */       }
/*     */     }
/* 302 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 310 */   private static HashMap<int[], Object[]> keyCache = new HashMap();
/*     */   
/*     */   public static Object[] getCraftingRecipeKey(EntityPlayer player, ItemStack stack) {
/* 313 */     int[] key = { Item.func_150891_b(stack.func_77973_b()), stack.func_77952_i() };
/* 314 */     if (keyCache.containsKey(key)) {
/* 315 */       if (keyCache.get(key) == null) return null;
/* 316 */       if (ResearchHelper.isResearchComplete(player.func_70005_c_(), (String)((Object[])keyCache.get(key))[0])) {
/* 317 */         return (Object[])keyCache.get(key);
/*     */       }
/* 319 */       return null;
/*     */     }
/* 321 */     for (ResearchCategoryList rcl : ResearchCategories.researchCategories.values()) {
/* 322 */       for (ResearchItem ri : rcl.research.values()) {
/* 323 */         if (ri.getPages() != null)
/* 324 */           for (int a = 0; a < ri.getPages().length; a++) {
/* 325 */             ResearchPage page = ri.getPages()[a];
/* 326 */             if ((page.recipe != null) && ((page.recipe instanceof CrucibleRecipe[]))) {
/* 327 */               CrucibleRecipe[] crs = (CrucibleRecipe[])page.recipe;
/* 328 */               for (CrucibleRecipe cr : crs) {
/* 329 */                 if (cr.getRecipeOutput().func_77969_a(stack)) {
/* 330 */                   keyCache.put(key, new Object[] { ri.key, Integer.valueOf(a) });
/* 331 */                   if (ResearchHelper.isResearchComplete(player.func_70005_c_(), ri.key)) {
/* 332 */                     return new Object[] { ri.key, Integer.valueOf(a) };
/*     */                   }
/*     */                 }
/*     */               }
/* 336 */             } else if ((page.recipe != null) && ((page.recipe instanceof InfusionRecipe[]))) {
/* 337 */               InfusionRecipe[] crs = (InfusionRecipe[])page.recipe;
/* 338 */               for (InfusionRecipe cr : crs) {
/* 339 */                 if (((cr.getRecipeOutput() instanceof ItemStack)) && (((ItemStack)cr.getRecipeOutput()).func_77969_a(stack))) {
/* 340 */                   keyCache.put(key, new Object[] { ri.key, Integer.valueOf(a) });
/* 341 */                   if (ResearchHelper.isResearchComplete(player.func_70005_c_(), ri.key)) {
/* 342 */                     return new Object[] { ri.key, Integer.valueOf(a) };
/*     */                   }
/*     */                 }
/*     */               }
/* 346 */             } else if ((page.recipe != null) && ((page.recipe instanceof IRecipe[]))) {
/* 347 */               IRecipe[] crs = (IRecipe[])page.recipe;
/* 348 */               for (IRecipe cr : crs) {
/* 349 */                 if (cr.func_77571_b().func_77969_a(stack)) {
/* 350 */                   keyCache.put(key, new Object[] { ri.key, Integer.valueOf(a) });
/* 351 */                   if (ResearchHelper.isResearchComplete(player.func_70005_c_(), ri.key)) {
/* 352 */                     return new Object[] { ri.key, Integer.valueOf(a) };
/*     */                   }
/*     */                 }
/*     */               }
/* 356 */             } else if ((page.recipeOutput == null) || (stack == null) || (!(page.recipeOutput instanceof ItemStack)) || (!((ItemStack)page.recipeOutput).func_77969_a(stack))) { if ((page.recipeOutput instanceof String)) { if (!ThaumcraftApiHelper.containsMatch(true, new ItemStack[] { stack }, OreDictionary.getOres((String)page.recipeOutput))) {}
/*     */               }
/*     */               
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/*     */ 
/* 364 */               keyCache.put(key, new Object[] { ri.key, Integer.valueOf(a) });
/* 365 */               if (ResearchHelper.isResearchComplete(player.func_70005_c_(), ri.key)) {
/* 366 */                 return new Object[] { ri.key, Integer.valueOf(a) };
/*     */               }
/* 368 */               return null;
/*     */             }
/*     */           }
/*     */       }
/*     */     }
/* 373 */     keyCache.put(key, null);
/* 374 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 379 */   public static ConcurrentHashMap<List, AspectList> objectTags = new ConcurrentHashMap();
/* 380 */   public static ConcurrentHashMap<List, int[]> groupedObjectTags = new ConcurrentHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean exists(Item item, int meta)
/*     */   {
/* 389 */     AspectList tmp = (AspectList)objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(meta) }));
/* 390 */     if (tmp == null) {
/* 391 */       tmp = (AspectList)objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(32767) }));
/* 392 */       if ((meta == 32767) && (tmp == null)) {
/* 393 */         int index = 0;
/*     */         do {
/* 395 */           tmp = (AspectList)objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(index) }));
/* 396 */           index++;
/* 397 */         } while ((index < 16) && (tmp == null));
/*     */       }
/* 399 */       if (tmp == null) { return false;
/*     */       }
/*     */     }
/* 402 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerObjectTag(ItemStack item, AspectList aspects)
/*     */   {
/* 412 */     if (aspects == null) aspects = new AspectList();
/*     */     try {
/* 414 */       objectTags.put(Arrays.asList(new Object[] { item.func_77973_b(), Integer.valueOf(item.func_77952_i()) }), aspects);
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerObjectTag(ItemStack item, int[] meta, AspectList aspects)
/*     */   {
/* 427 */     if (aspects == null) aspects = new AspectList();
/*     */     try {
/* 429 */       objectTags.put(Arrays.asList(new Object[] { item.func_77973_b(), Integer.valueOf(meta[0]) }), aspects);
/* 430 */       for (int m : meta) {
/* 431 */         groupedObjectTags.put(Arrays.asList(new Object[] { item.func_77973_b(), Integer.valueOf(m) }), meta);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerObjectTag(String oreDict, AspectList aspects)
/*     */   {
/* 443 */     if (aspects == null) aspects = new AspectList();
/* 444 */     List<ItemStack> ores = OreDictionary.getOres(oreDict);
/* 445 */     if ((ores != null) && (ores.size() > 0)) {
/* 446 */       for (ItemStack ore : ores) {
/*     */         try {
/* 448 */           objectTags.put(Arrays.asList(new Object[] { ore.func_77973_b(), Integer.valueOf(ore.func_77952_i()) }), aspects);
/*     */         }
/*     */         catch (Exception e) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerComplexObjectTag(ItemStack item, AspectList aspects)
/*     */   {
/* 464 */     if (!exists(item.func_77973_b(), item.func_77952_i())) {
/* 465 */       AspectList tmp = AspectHelper.generateTags(item.func_77973_b(), item.func_77952_i());
/* 466 */       if ((tmp != null) && (tmp.size() > 0)) {
/* 467 */         for (Aspect tag : tmp.getAspects()) {
/* 468 */           aspects.add(tag, tmp.getAmount(tag));
/*     */         }
/*     */       }
/* 471 */       registerObjectTag(item, aspects);
/*     */     } else {
/* 473 */       AspectList tmp = AspectHelper.getObjectAspects(item);
/* 474 */       for (Aspect tag : aspects.getAspects()) {
/* 475 */         tmp.merge(tag, tmp.getAmount(tag));
/*     */       }
/* 477 */       registerObjectTag(item, tmp);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 482 */   private static HashMap<Object, Integer> warpMap = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addWarpToItem(ItemStack craftresult, int amount)
/*     */   {
/* 491 */     warpMap.put(Arrays.asList(new Object[] { craftresult.func_77973_b(), Integer.valueOf(craftresult.func_77952_i()) }), Integer.valueOf(amount));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addWarpToResearch(String research, int amount)
/*     */   {
/* 500 */     warpMap.put(research, Integer.valueOf(amount));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getWarp(Object in)
/*     */   {
/* 509 */     if (in == null) return 0;
/* 510 */     if ((in instanceof ItemStack)) if (warpMap.containsKey(Arrays.asList(new Object[] { ((ItemStack)in).func_77973_b(), Integer.valueOf(((ItemStack)in).func_77952_i()) }))) {
/* 511 */         return ((Integer)warpMap.get(Arrays.asList(new Object[] { ((ItemStack)in).func_77973_b(), Integer.valueOf(((ItemStack)in).func_77952_i()) }))).intValue();
/*     */       }
/* 513 */     if (((in instanceof String)) && (warpMap.containsKey((String)in))) {
/* 514 */       return ((Integer)warpMap.get((String)in)).intValue();
/*     */     }
/* 516 */     return 0;
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
/*     */   public static void addLootBagItem(ItemStack item, int weight, int... bagTypes)
/*     */   {
/* 532 */     if ((bagTypes == null) || (bagTypes.length == 0)) {
/* 533 */       WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
/*     */     } else {
/* 535 */       for (int rarity : bagTypes) {
/* 536 */         switch (rarity) {
/* 537 */         case 0:  WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight)); break;
/* 538 */         case 1:  WeightedRandomLoot.lootBagUncommon.add(new WeightedRandomLoot(item, weight)); break;
/* 539 */         case 2:  WeightedRandomLoot.lootBagRare.add(new WeightedRandomLoot(item, weight));
/*     */         }
/*     */         
/*     */       }
/*     */     }
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
/* 565 */   public static HashMap<String, ItemStack> seedList = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerSeed(Block block, ItemStack seed)
/*     */   {
/* 575 */     seedList.put(block.func_149739_a(), seed);
/*     */   }
/*     */   
/*     */   public static ItemStack getSeed(Block block) {
/* 579 */     return (ItemStack)seedList.get(block.func_149739_a());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/ThaumcraftApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */