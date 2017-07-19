/*     */ package thaumcraft.common.lib.world;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraft.world.gen.structure.MapGenScatteredFeature;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import net.minecraftforge.common.BiomeManager;
/*     */ import net.minecraftforge.common.BiomeManager.BiomeEntry;
/*     */ import net.minecraftforge.common.BiomeManager.BiomeType;
/*     */ import net.minecraftforge.fml.common.IWorldGenerator;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ import thaumcraft.common.items.resources.ItemShard.ShardType;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.aura.EntityAuraNode;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ import thaumcraft.common.lib.world.objects.WorldGenEldritchRing;
/*     */ import thaumcraft.common.lib.world.objects.WorldGenGreatwoodTrees;
/*     */ import thaumcraft.common.lib.world.objects.WorldGenMound;
/*     */ import thaumcraft.common.lib.world.objects.WorldGenSilverwoodTrees;
/*     */ 
/*     */ public class ThaumcraftWorldGenerator implements IWorldGenerator
/*     */ {
/*     */   public void initialize()
/*     */   {
/*  45 */     BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeHandler.biomeMagicalForest, Config.biomeMagicalForestWeight));
/*  46 */     BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeHandler.biomeMagicalForest, Config.biomeMagicalForestWeight));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
/*     */   {
/*  53 */     worldGeneration(random, chunkX, chunkZ, world, true);
/*     */     
/*  55 */     AuraHandler.generateAura(chunkProvider.func_73154_d(chunkX, chunkZ), random);
/*     */   }
/*     */   
/*     */   public void worldGeneration(Random random, int chunkX, int chunkZ, World world, boolean newGen)
/*     */   {
/*  60 */     if (world.field_73011_w.func_177502_q() == Config.dimensionOuterId) {
/*  61 */       MazeHandler.generateEldritch(world, random, chunkX, chunkZ);
/*  62 */       world.func_72964_e(chunkX, chunkZ).func_76630_e();
/*     */     }
/*     */     else {
/*  65 */       generateAll(world, random, chunkX, chunkZ, newGen);
/*     */       
/*  67 */       if (world.field_73011_w.func_177502_q() == -1) {
/*  68 */         generateNether(world, random, chunkX, chunkZ, newGen);
/*     */       }
/*  70 */       else if (world.field_73011_w.func_177502_q() == Config.overworldDim) {
/*  71 */         generateSurface(world, random, chunkX, chunkZ, newGen);
/*     */       }
/*  73 */       if (!newGen) {
/*  74 */         world.func_72964_e(chunkX, chunkZ).func_76630_e();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  81 */   HashMap<BlockPos, Boolean> structureNode = new HashMap();
/*     */   
/*     */   private void generateSurface(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/*  84 */     boolean auraGen = false;
/*  85 */     int blacklist = BiomeHandler.getDimBlacklist(world.field_73011_w.func_177502_q());
/*     */     
/*  87 */     if ((blacklist == -1) && (Config.genStructure) && (world.field_73011_w.func_177502_q() == Config.overworldDim) && (!world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat")) && ((newGen) || (Config.regenStructure)))
/*     */     {
/*     */ 
/*     */ 
/*  91 */       int randPosX = chunkX * 16 + random.nextInt(16);
/*  92 */       int randPosZ = chunkZ * 16 + random.nextInt(16);
/*  93 */       BlockPos p = world.func_175725_q(new BlockPos(randPosX, 0, randPosZ)).func_177979_c(9);
/*     */       
/*  95 */       if (p.func_177956_o() < world.func_72940_L()) {
/*  96 */         if (random.nextInt(150) == 0) {
/*  97 */           WorldGenerator mound = new WorldGenMound();
/*  98 */           if (mound.func_180709_b(world, random, p))
/*     */           {
/* 100 */             auraGen = true;
/* 101 */             spawnNode(world, p.func_177982_a(9, 8, 9), 1, 1.5F);
/*     */           }
/*     */         }
/* 104 */         else if (random.nextInt(66) == 0) {
/* 105 */           WorldGenEldritchRing stonering = new WorldGenEldritchRing();
/* 106 */           BlockPos p2 = p.func_177981_b(8);
/* 107 */           int w = 8 + random.nextInt(5) * 2;
/* 108 */           int h = 8 + random.nextInt(5) * 2;
/* 109 */           stonering.chunkX = chunkX;
/* 110 */           stonering.chunkZ = chunkZ;
/* 111 */           stonering.width = w;
/* 112 */           stonering.height = h;
/* 113 */           if (stonering.func_180709_b(world, random, p2)) {
/* 114 */             world.func_175656_a(p2.func_177981_b(2), BlocksTC.eldritch.func_176203_a(6));
/*     */             
/* 116 */             Thread t = new Thread(new thaumcraft.common.lib.world.dim.MazeThread(chunkX, chunkZ, w, h, random.nextLong()));
/* 117 */             t.start();
/*     */           }
/*     */         }
/* 120 */         else if (random.nextInt(500) == 0) {
/* 121 */           BlockPos p2 = p.func_177981_b(8);
/* 122 */           IBlockState bs = world.func_180495_p(p2);
/* 123 */           if ((bs.func_177230_c().func_149688_o() == Material.field_151578_c) || (bs.func_177230_c().func_149688_o() == Material.field_151576_e) || (bs.func_177230_c().func_149688_o() == Material.field_151595_p) || (bs.func_177230_c().func_149688_o() == Material.field_151597_y))
/*     */           {
/*     */ 
/* 126 */             EntityCultistPortalLesser eg = new EntityCultistPortalLesser(world);
/* 127 */             eg.func_70107_b(p2.func_177958_n() + 0.5D, p2.func_177956_o() + 1, p2.func_177952_p() + 0.5D);
/* 128 */             eg.func_180482_a(world.func_175649_E(new BlockPos(eg)), (IEntityLivingData)null);
/* 129 */             world.func_72838_d(eg);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateNodes(World world, Random random, int chunkX, int chunkZ, boolean newGen, int blacklist)
/*     */   {
/* 138 */     if ((blacklist != 0) && (blacklist != 2) && (Config.genAura) && ((newGen) || (Config.regenAura)))
/*     */     {
/* 140 */       BlockPos var7 = null;
/*     */       try
/*     */       {
/* 143 */         var7 = new MapGenScatteredFeature().func_180706_b(world, world.func_175645_m(new BlockPos(chunkX * 16 + 8, 64, chunkZ * 16 + 8)));
/*     */       }
/*     */       catch (Exception e) {}
/*     */       
/* 147 */       if ((var7 != null) && (!this.structureNode.containsKey(var7))) {
/* 148 */         this.structureNode.put(var7, Boolean.valueOf(true));
/* 149 */         BlockPos bp = var7.func_177981_b(3);
/* 150 */         spawnNode(world, bp, -1, 1.0F);
/*     */       } else {
/* 152 */         int x = chunkX * 16 + random.nextInt(16);
/* 153 */         int z = chunkZ * 16 + random.nextInt(16);
/* 154 */         int h = world.func_175645_m(new BlockPos(x, 64, z)).func_177956_o();
/* 155 */         if (h < world.func_72940_L() / 3) h = world.func_72940_L() / 3;
/* 156 */         int y = 8 + random.nextInt(h);
/* 157 */         BlockPos bp = new BlockPos(x, y, z);
/* 158 */         for (; !world.func_175623_d(bp); 
/*     */             
/* 160 */             return)
/*     */         {
/*     */           label221:
/* 159 */           bp = bp.func_177981_b(2);
/* 160 */           if ((world.func_180495_p(bp).func_177230_c() != Blocks.field_150357_h) && (bp.func_177956_o() < world.func_72940_L())) break label221;
/*     */         }
/* 162 */         if ((world.func_175623_d(bp)) && (random.nextInt(Math.max(2, Config.nodeRarity)) == 0)) {
/* 163 */           spawnNode(world, bp, -1, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void spawnNode(World world, BlockPos bp, int type, float sizemod)
/*     */   {
/* 171 */     EntityAuraNode e = new EntityAuraNode(world);
/* 172 */     e.func_70012_b(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, 0.0F, 0.0F);
/* 173 */     world.func_72838_d(e);
/* 174 */     e.randomizeNode();
/* 175 */     if (type >= 0) {
/* 176 */       e.setNodeType(type);
/*     */     }
/* 178 */     e.setNodeSize((int)(e.getNodeSize() * sizemod));
/* 179 */     if (e.getNodeType() == 4) {
/* 180 */       AuraHelper.pollute(world, bp, Config.AURABASE, false);
/* 181 */       for (int a = 0; a < 16; a++) {
/* 182 */         BlockPos tt = bp.func_177982_a(world.field_73012_v.nextInt(16) - world.field_73012_v.nextInt(16), world.field_73012_v.nextInt(16) - world.field_73012_v.nextInt(16), world.field_73012_v.nextInt(16) - world.field_73012_v.nextInt(16));
/*     */         
/*     */ 
/*     */ 
/* 186 */         IBlockState ts = world.func_180495_p(tt);
/* 187 */         if (((world.func_175623_d(tt)) || (ts.func_177230_c().func_176200_f(world, tt))) && (BlockUtils.isAdjacentToSolidBlock(world, tt))) {
/* 188 */           world.func_175656_a(tt, BlocksTC.taintFibre.func_176223_P());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateVegetation(World world, Random random, int chunkX, int chunkZ, boolean newGen)
/*     */   {
/* 196 */     BiomeGenBase bgb = world.func_180494_b(new BlockPos(chunkX * 16 + 8, 50, chunkZ * 16 + 8));
/* 197 */     if (BiomeHandler.getBiomeBlacklist(bgb.field_76756_M) != -1) { return;
/*     */     }
/*     */     
/* 200 */     if (random.nextInt(60) == 3) {
/* 201 */       generateSilverwood(world, random, chunkX, chunkZ);
/*     */     }
/*     */     
/*     */ 
/* 205 */     if (random.nextInt(25) == 7) {
/* 206 */       generateGreatwood(world, random, chunkX, chunkZ);
/*     */     }
/*     */     
/*     */ 
/* 210 */     int randPosX = chunkX * 16 + random.nextInt(16);
/* 211 */     int randPosZ = chunkZ * 16 + random.nextInt(16);
/* 212 */     BlockPos bp = world.func_175645_m(new BlockPos(randPosX, 0, randPosZ));
/*     */     
/* 214 */     if ((world.func_180494_b(bp).field_76752_A.func_177230_c() == Blocks.field_150354_m) && (world.func_180494_b(bp).field_76750_F > 1.0F) && (random.nextInt(30) == 0))
/*     */     {
/*     */ 
/* 217 */       generateFlowers(world, random, bp, BlocksTC.cinderpearl, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateOres(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/* 222 */     BiomeGenBase bgb = world.func_180494_b(new BlockPos(chunkX * 16 + 8, 50, chunkZ * 16 + 8));
/* 223 */     if ((BiomeHandler.getBiomeBlacklist(bgb.field_76756_M) == 0) || (BiomeHandler.getBiomeBlacklist(bgb.field_76756_M) == 2)) {
/* 224 */       return;
/*     */     }
/* 226 */     float density = Config.oreDensity / 100.0F;
/*     */     
/* 228 */     if (world.field_73011_w.func_177502_q() == -1) { return;
/*     */     }
/* 230 */     if ((Config.genCinnibar) && ((newGen) || (Config.regenCinnibar)))
/*     */     {
/* 232 */       for (int i = 0; i < Math.round(18.0F * density); i++)
/*     */       {
/* 234 */         int randPosX = chunkX * 16 + random.nextInt(16);
/* 235 */         int randPosY = random.nextInt(world.func_72800_K() / 5);
/* 236 */         int randPosZ = chunkZ * 16 + random.nextInt(16);
/* 237 */         IBlockState block = world.func_180495_p(new BlockPos(randPosX, randPosY, randPosZ));
/* 238 */         if (block == Blocks.field_150348_b.func_176223_P())
/*     */         {
/* 240 */           world.func_180501_a(new BlockPos(randPosX, randPosY, randPosZ), BlocksTC.oreCinnabar.func_176223_P(), 0);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 245 */     if ((Config.genAmber) && ((newGen) || (Config.regenAmber)))
/*     */     {
/* 247 */       for (int i = 0; i < Math.round(20.0F * density); i++)
/*     */       {
/* 249 */         int randPosX = chunkX * 16 + random.nextInt(16);
/* 250 */         int randPosZ = chunkZ * 16 + random.nextInt(16);
/*     */         
/* 252 */         int randPosY = world.func_175645_m(new BlockPos(randPosX, 0, randPosZ)).func_177956_o() - random.nextInt(25);
/*     */         
/* 254 */         IBlockState block = world.func_180495_p(new BlockPos(randPosX, randPosY, randPosZ));
/* 255 */         if (block == Blocks.field_150348_b.func_176223_P())
/*     */         {
/* 257 */           world.func_180501_a(new BlockPos(randPosX, randPosY, randPosZ), BlocksTC.oreAmber.func_176223_P(), 0);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 262 */     if ((Config.genCrystals) && ((newGen) || (Config.regenCrystals))) {
/* 263 */       int t = 8;
/* 264 */       int maxCrystals = Math.round(64.0F * density);
/* 265 */       int cc = 0;
/*     */       
/* 267 */       if (world.field_73011_w.func_177502_q() == -1) { t = 1;
/*     */       }
/*     */       
/* 270 */       for (int i = 0; i < Math.round(t * density); i++)
/*     */       {
/* 272 */         int randPosX = chunkX * 16 + random.nextInt(16);
/* 273 */         int randPosZ = chunkZ * 16 + random.nextInt(16);
/* 274 */         int randPosY = random.nextInt(Math.max(5, world.func_175645_m(new BlockPos(randPosX, 0, randPosZ)).func_177956_o() - 5));
/* 275 */         BlockPos bp = new BlockPos(randPosX, randPosY, randPosZ);
/* 276 */         int md = random.nextInt(6);
/* 277 */         if (random.nextInt(3) == 0) {
/* 278 */           thaumcraft.api.aspects.Aspect tag = BiomeHandler.getRandomBiomeTag(world.func_180494_b(bp).field_76756_M, random);
/* 279 */           if (tag == null) {
/* 280 */             md = random.nextInt(6);
/*     */           } else {
/* 282 */             md = ItemShard.ShardType.getMetaByAspect(tag);
/*     */           }
/*     */         }
/* 285 */         Block oreBlock = ItemShard.ShardType.byMetadata(md).getOre();
/* 286 */         for (int xx = -1; xx <= 1; xx++) {
/* 287 */           for (int yy = -1; yy <= 1; yy++)
/* 288 */             for (int zz = -1; zz <= 1; zz++)
/* 289 */               if (random.nextInt(3) != 0) {
/* 290 */                 IBlockState bs = world.func_180495_p(bp.func_177982_a(xx, yy, zz));
/* 291 */                 Material bm = bs.func_177230_c().func_149688_o();
/* 292 */                 if ((!bm.func_76224_d()) && ((world.func_175623_d(bp.func_177982_a(xx, yy, zz))) || (bs.func_177230_c().func_176200_f(world, bp.func_177982_a(xx, yy, zz)))) && (BlockUtils.isBlockTouching(world, bp.func_177982_a(xx, yy, zz), Material.field_151576_e, true)))
/*     */                 {
/*     */ 
/* 295 */                   int amt = 1 + random.nextInt(3);
/* 296 */                   world.func_180501_a(bp.func_177982_a(xx, yy, zz), oreBlock.func_176203_a(amt), 0);
/* 297 */                   cc += amt;
/*     */                 }
/*     */               }
/*     */         }
/* 301 */         if (cc > maxCrystals) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateAll(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/* 309 */     boolean auraGen = false;
/* 310 */     int blacklist = BiomeHandler.getDimBlacklist(world.field_73011_w.func_177502_q());
/*     */     
/* 312 */     if ((blacklist == -1) && (Config.genTrees) && (!world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat")) && ((newGen) || (Config.regenTrees))) {
/* 313 */       generateVegetation(world, random, chunkX, chunkZ, newGen);
/*     */     }
/*     */     
/* 316 */     if ((blacklist != 0) && (blacklist != 2)) { generateOres(world, random, chunkX, chunkZ, newGen);
/*     */     }
/*     */     
/* 319 */     if (!auraGen) { generateNodes(world, random, chunkX, chunkZ, newGen, blacklist);
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateNether(World world, Random random, int chunkX, int chunkZ, boolean newGen)
/*     */   {
/* 325 */     boolean auraGen = false;
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
/*     */   public static boolean generateFlowers(World world, Random random, BlockPos pos, Block block, int md)
/*     */   {
/* 338 */     WorldGenerator flowers = new thaumcraft.common.lib.world.objects.WorldGenCustomFlowers(block, md);
/* 339 */     return flowers.func_180709_b(world, random, pos);
/*     */   }
/*     */   
/*     */   public static boolean generateGreatwood(World world, Random random, int chunkX, int chunkZ)
/*     */   {
/* 344 */     int x = chunkX * 16 + random.nextInt(16);
/* 345 */     int z = chunkZ * 16 + random.nextInt(16);
/* 346 */     BlockPos bp = world.func_175725_q(new BlockPos(x, 0, z));
/* 347 */     int bio = world.func_180494_b(bp).field_76756_M;
/* 348 */     if (BiomeHandler.getBiomeSupportsGreatwood(bio) > random.nextFloat()) {
/* 349 */       boolean t = new WorldGenGreatwoodTrees(false, random.nextInt(8) == 0).func_180709_b(world, random, bp);
/* 350 */       return t;
/*     */     }
/* 352 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean generateSilverwood(World world, Random random, int chunkX, int chunkZ)
/*     */   {
/* 357 */     int x = chunkX * 16 + random.nextInt(16);
/* 358 */     int z = chunkZ * 16 + random.nextInt(16);
/* 359 */     BlockPos bp = world.func_175725_q(new BlockPos(x, 0, z));
/* 360 */     BiomeGenBase bio = world.func_180494_b(bp);
/* 361 */     if ((!bio.equals(BiomeHandler.biomeMagicalForest)) && ((BiomeDictionary.isBiomeOfType(bio, BiomeDictionary.Type.MAGICAL)) || (bio.field_76756_M == BiomeGenBase.field_76785_t.field_76756_M) || (bio.field_76756_M == BiomeGenBase.field_150582_Q.field_76756_M)))
/*     */     {
/*     */ 
/* 364 */       boolean t = new WorldGenSilverwoodTrees(false, 7, 4).func_180709_b(world, random, bp);
/* 365 */       return t;
/*     */     }
/* 367 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/ThaumcraftWorldGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */