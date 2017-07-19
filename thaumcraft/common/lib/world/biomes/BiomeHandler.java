/*     */ package thaumcraft.common.lib.world.biomes;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class BiomeHandler
/*     */ {
/*  17 */   public static HashMap<BiomeDictionary.Type, List> biomeInfo = new HashMap();
/*     */   public static BiomeGenBase biomeTaint;
/*     */   public static BiomeGenBase biomeEerie;
/*     */   
/*     */   public static void registerBiomeInfo(BiomeDictionary.Type type, float auraLevel, Aspect tag, boolean greatwood, float greatwoodchance) {
/*  22 */     biomeInfo.put(type, java.util.Arrays.asList(new Object[] { Float.valueOf(auraLevel), tag, Boolean.valueOf(greatwood), Float.valueOf(greatwoodchance) }));
/*     */   }
/*     */   
/*     */   public static float getBiomeAuraModifier(BiomeGenBase biome) {
/*     */     try {
/*  27 */       BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(biome);
/*  28 */       float average = 0.0F;
/*  29 */       int count = 0;
/*  30 */       for (BiomeDictionary.Type type : types) {
/*  31 */         average += ((Float)((List)biomeInfo.get(type)).get(0)).floatValue();
/*  32 */         count++;
/*     */       }
/*  34 */       return average / count;
/*     */     } catch (Exception e) {}
/*  36 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public static Aspect getRandomBiomeTag(int biomeId, Random random) {
/*     */     try {
/*  41 */       BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(BiomeGenBase.func_150568_d(biomeId));
/*  42 */       BiomeDictionary.Type type = types[random.nextInt(types.length)];
/*  43 */       return (Aspect)((List)biomeInfo.get(type)).get(1);
/*     */     } catch (Exception e) {}
/*  45 */     return null;
/*     */   }
/*     */   
/*     */   public static float getBiomeSupportsGreatwood(int biomeId) {
/*     */     try {
/*  50 */       BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(BiomeGenBase.func_150568_d(biomeId));
/*  51 */       for (BiomeDictionary.Type type : types) {
/*  52 */         if (((Boolean)((List)biomeInfo.get(type)).get(2)).booleanValue()) return ((Float)((List)biomeInfo.get(type)).get(3)).floatValue();
/*     */       }
/*     */     } catch (Exception e) {}
/*  55 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public static BiomeGenBase biomeMagicalForest;
/*     */   
/*     */   public static BiomeGenBase biomeEldritchLands;
/*  62 */   public static Collection<Aspect> c = Aspect.aspects.values();
/*  63 */   public static ArrayList<Aspect> basicAspects = new ArrayList();
/*  64 */   public static ArrayList<Aspect> complexAspects = new ArrayList();
/*  65 */   public static HashMap<Integer, Integer> dimensionBlacklist = new HashMap();
/*  66 */   public static HashMap<Integer, Integer> biomeBlacklist = new HashMap();
/*     */   
/*     */   public static int getFirstFreeBiomeSlot(int old) {
/*  69 */     for (int a = 0; a < BiomeGenBase.func_150565_n().length; a++) {
/*  70 */       if (BiomeGenBase.func_150565_n()[a] == null) {
/*  71 */         Thaumcraft.log.warn("Biome slot " + old + " already occupied. Using first free biome slot at " + a);
/*  72 */         return a;
/*     */       }
/*     */     }
/*  75 */     return -1;
/*     */   }
/*     */   
/*     */   public static void addDimBlacklist(int dim, int level) {
/*  79 */     dimensionBlacklist.put(Integer.valueOf(dim), Integer.valueOf(level));
/*     */   }
/*     */   
/*     */   public static int getDimBlacklist(int dim) {
/*  83 */     if (!dimensionBlacklist.containsKey(Integer.valueOf(dim))) return -1; return ((Integer)dimensionBlacklist.get(Integer.valueOf(dim))).intValue();
/*     */   }
/*     */   
/*     */   public static void addBiomeBlacklist(int biome, int level) {
/*  87 */     biomeBlacklist.put(Integer.valueOf(biome), Integer.valueOf(level));
/*     */   }
/*     */   
/*     */   public static int getBiomeBlacklist(int biome) {
/*  91 */     if (!biomeBlacklist.containsKey(Integer.valueOf(biome))) return -1; return ((Integer)biomeBlacklist.get(Integer.valueOf(biome))).intValue();
/*     */   }
/*     */   
/*     */   public static void registerBiomes() {
/*  95 */     BiomeDictionary.registerBiomeType(biomeTaint, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WASTELAND });
/*  96 */     BiomeDictionary.registerBiomeType(biomeEerie, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY });
/*  97 */     BiomeDictionary.registerBiomeType(biomeEldritchLands, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.END });
/*  98 */     BiomeDictionary.registerBiomeType(biomeMagicalForest, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST });
/*     */     
/*     */ 
/* 101 */     registerBiomeInfo(BiomeDictionary.Type.WATER, 1.0F, Aspect.WATER, false, 0.0F);
/* 102 */     registerBiomeInfo(BiomeDictionary.Type.OCEAN, 1.0F, Aspect.WATER, false, 0.0F);
/* 103 */     registerBiomeInfo(BiomeDictionary.Type.RIVER, 1.0F, Aspect.WATER, false, 0.0F);
/* 104 */     registerBiomeInfo(BiomeDictionary.Type.WET, 1.0F, Aspect.WATER, false, 0.0F);
/* 105 */     registerBiomeInfo(BiomeDictionary.Type.LUSH, 1.2F, Aspect.WATER, true, 0.5F);
/*     */     
/* 107 */     registerBiomeInfo(BiomeDictionary.Type.HOT, 1.0F, Aspect.FIRE, false, 0.0F);
/* 108 */     registerBiomeInfo(BiomeDictionary.Type.DESERT, 0.75F, Aspect.FIRE, false, 0.0F);
/* 109 */     registerBiomeInfo(BiomeDictionary.Type.NETHER, 0.5F, Aspect.FIRE, false, 0.0F);
/* 110 */     registerBiomeInfo(BiomeDictionary.Type.MESA, 1.0F, Aspect.FIRE, false, 0.0F);
/* 111 */     registerBiomeInfo(BiomeDictionary.Type.SPOOKY, 0.75F, Aspect.FIRE, false, 0.0F);
/*     */     
/* 113 */     registerBiomeInfo(BiomeDictionary.Type.DENSE, 1.0F, Aspect.ORDER, false, 0.0F);
/* 114 */     registerBiomeInfo(BiomeDictionary.Type.SNOWY, 0.75F, Aspect.ORDER, false, 0.0F);
/* 115 */     registerBiomeInfo(BiomeDictionary.Type.COLD, 0.75F, Aspect.ORDER, false, 0.0F);
/* 116 */     registerBiomeInfo(BiomeDictionary.Type.FROZEN, 1.0F, Aspect.ORDER, false, 0.0F);
/* 117 */     registerBiomeInfo(BiomeDictionary.Type.MUSHROOM, 1.3F, Aspect.ORDER, false, 0.0F);
/* 118 */     registerBiomeInfo(BiomeDictionary.Type.MAGICAL, 1.0F, Aspect.ORDER, true, 1.0F);
/*     */     
/* 120 */     registerBiomeInfo(BiomeDictionary.Type.CONIFEROUS, 1.0F, Aspect.EARTH, true, 0.2F);
/* 121 */     registerBiomeInfo(BiomeDictionary.Type.FOREST, 1.2F, Aspect.EARTH, true, 1.0F);
/* 122 */     registerBiomeInfo(BiomeDictionary.Type.SANDY, 0.75F, Aspect.EARTH, false, 0.0F);
/* 123 */     registerBiomeInfo(BiomeDictionary.Type.BEACH, 0.75F, Aspect.EARTH, false, 0.0F);
/* 124 */     registerBiomeInfo(BiomeDictionary.Type.JUNGLE, 1.2F, Aspect.EARTH, false, 0.0F);
/*     */     
/* 126 */     registerBiomeInfo(BiomeDictionary.Type.SAVANNA, 0.75F, Aspect.AIR, true, 0.2F);
/* 127 */     registerBiomeInfo(BiomeDictionary.Type.MOUNTAIN, 0.75F, Aspect.AIR, false, 0.0F);
/* 128 */     registerBiomeInfo(BiomeDictionary.Type.HILLS, 1.0F, Aspect.AIR, false, 0.0F);
/* 129 */     registerBiomeInfo(BiomeDictionary.Type.PLAINS, 0.75F, Aspect.AIR, true, 0.2F);
/* 130 */     registerBiomeInfo(BiomeDictionary.Type.END, 0.5F, Aspect.AIR, false, 0.0F);
/*     */     
/* 132 */     registerBiomeInfo(BiomeDictionary.Type.DRY, 0.5F, Aspect.ENTROPY, false, 0.0F);
/* 133 */     registerBiomeInfo(BiomeDictionary.Type.SPARSE, 0.75F, Aspect.ENTROPY, false, 0.0F);
/* 134 */     registerBiomeInfo(BiomeDictionary.Type.SWAMP, 1.2F, Aspect.ENTROPY, true, 0.2F);
/* 135 */     registerBiomeInfo(BiomeDictionary.Type.WASTELAND, 0.5F, Aspect.ENTROPY, false, 0.0F);
/* 136 */     registerBiomeInfo(BiomeDictionary.Type.DEAD, 0.25F, Aspect.ENTROPY, false, 0.0F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/biomes/BiomeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */