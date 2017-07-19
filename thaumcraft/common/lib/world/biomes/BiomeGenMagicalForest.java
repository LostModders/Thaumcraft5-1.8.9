/*     */ package thaumcraft.common.lib.world.biomes;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockTallGrass.EnumType;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.BiomeGenBase.Height;
/*     */ import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigMushroom;
/*     */ import net.minecraft.world.gen.feature.WorldGenBlockBlob;
/*     */ import net.minecraft.world.gen.feature.WorldGenTallGrass;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.objects.WorldGenBigMagicTree;
/*     */ import thaumcraft.common.lib.world.objects.WorldGenGreatwoodTrees;
/*     */ 
/*     */ public class BiomeGenMagicalForest extends BiomeGenBase
/*     */ {
/*     */   protected WorldGenBigMagicTree bigTree;
/*  37 */   private static final WorldGenBlockBlob blobs = new WorldGenBlockBlob(Blocks.field_150341_Y, 0);
/*     */   
/*     */   public BiomeGenMagicalForest(int par1)
/*     */   {
/*  41 */     super(par1);
/*  42 */     this.bigTree = new WorldGenBigMagicTree(false);
/*     */     
/*  44 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 2, 1, 3));
/*  45 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 2, 1, 3));
/*     */     
/*  47 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 3, 1, 1));
/*  48 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 3, 1, 1));
/*     */     
/*  50 */     if (Config.spawnPech)
/*  51 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityPech.class, 10, 1, 2));
/*  52 */     if (Config.spawnWisp) {
/*  53 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(thaumcraft.common.entities.monster.EntityWisp.class, 10, 1, 2));
/*     */     }
/*  55 */     this.field_76760_I.field_76832_z = 2;
/*  56 */     this.field_76760_I.field_76802_A = 10;
/*  57 */     this.field_76760_I.field_76803_B = 12;
/*  58 */     this.field_76760_I.field_76833_y = 6;
/*  59 */     this.field_76760_I.field_76798_D = 6;
/*  60 */     func_76732_a(0.7F, 0.6F);
/*  61 */     func_150570_a(new BiomeGenBase.Height(0.2F, 0.2F));
/*  62 */     func_76735_a("Magical Forest");
/*  63 */     func_76739_b(Config.blueBiome ? 6728396 : 6747307);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WorldGenAbstractTree func_150567_a(Random par1Random)
/*     */   {
/*  72 */     return par1Random.nextInt(10) == 0 ? new WorldGenGreatwoodTrees(false, par1Random.nextInt(8) == 0) : par1Random.nextInt(14) == 0 ? new thaumcraft.common.lib.world.objects.WorldGenSilverwoodTrees(false, 8, 5) : this.bigTree;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WorldGenerator func_76730_b(Random par1Random)
/*     */   {
/*  81 */     return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180627_b(BlockPos p_180627_1_)
/*     */   {
/*  90 */     return Config.blueBiome ? 6728396 : 5635969;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180625_c(BlockPos p_180625_1_)
/*     */   {
/*  97 */     return Config.blueBiome ? 7851246 : 6750149;
/*     */   }
/*     */   
/*     */   public int getWaterColorMultiplier()
/*     */   {
/* 102 */     return 30702;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180624_a(World world, Random random, BlockPos pos)
/*     */   {
/* 111 */     for (int a = 0; a < 3; a++) {
/* 112 */       BlockPos pp = new BlockPos(pos);
/* 113 */       pp = pp.func_177982_a(4 + random.nextInt(8), 0, 4 + random.nextInt(8));
/* 114 */       pp = world.func_175645_m(pp);
/*     */       
/* 116 */       while ((pp.func_177956_o() > 30) && (world.func_180495_p(pp).func_177230_c() != Blocks.field_150349_c)) {
/* 117 */         pp = pp.func_177977_b();
/*     */       }
/* 119 */       Block l1 = world.func_180495_p(pp).func_177230_c();
/* 120 */       if (l1 == Blocks.field_150349_c)
/*     */       {
/* 122 */         world.func_180501_a(pp, BlocksTC.grassAmbient.func_176223_P(), 2);
/* 123 */         break;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 128 */     int k = random.nextInt(3);
/*     */     
/* 130 */     for (int l = 0; l < k; l++)
/*     */     {
/* 132 */       BlockPos p2 = new BlockPos(pos);
/* 133 */       p2 = p2.func_177982_a(random.nextInt(16) + 8, 0, random.nextInt(16) + 8);
/* 134 */       p2 = world.func_175645_m(p2);
/* 135 */       blobs.func_180709_b(world, random, p2);
/*     */     }
/*     */     
/* 138 */     for (k = 0; k < 4; k++)
/*     */     {
/* 140 */       for (l = 0; l < 4; l++)
/*     */       {
/* 142 */         if (random.nextInt(40) == 0)
/*     */         {
/* 144 */           BlockPos p2 = new BlockPos(pos);
/* 145 */           p2 = p2.func_177982_a(k * 4 + 1 + 8 + random.nextInt(3), 0, l * 4 + 1 + 8 + random.nextInt(3));
/* 146 */           p2 = world.func_175645_m(p2);
/* 147 */           WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
/* 148 */           worldgenbigmushroom.func_180709_b(world, random, p2);
/*     */         }
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/* 154 */       super.func_180624_a(world, random, pos);
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/*     */ 
/* 159 */     for (int a = 0; a < 8; a++) {
/* 160 */       BlockPos p2 = new BlockPos(pos);
/* 161 */       p2 = p2.func_177982_a(random.nextInt(16), 0, random.nextInt(16));
/* 162 */       p2 = world.func_175645_m(p2);
/*     */       
/* 164 */       while ((p2.func_177956_o() > 50) && (world.func_180495_p(p2).func_177230_c() != Blocks.field_150349_c)) {
/* 165 */         p2 = p2.func_177977_b();
/*     */       }
/* 167 */       Block l2 = world.func_180495_p(p2).func_177230_c();
/* 168 */       if ((l2 == Blocks.field_150349_c) && (world.func_180495_p(p2.func_177984_a()).func_177230_c().func_176200_f(world, p2.func_177984_a())) && (isBlockAdjacentToWood(world, p2.func_177984_a())))
/*     */       {
/*     */ 
/* 171 */         world.func_180501_a(p2.func_177984_a(), BlocksTC.vishroom.func_176223_P(), 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isBlockAdjacentToWood(IBlockAccess world, BlockPos pos)
/*     */   {
/* 178 */     int count = 0;
/* 179 */     for (int xx = -1; xx <= 1; xx++) {
/* 180 */       for (int yy = -1; yy <= 1; yy++) {
/* 181 */         for (int zz = -1; zz <= 1; zz++)
/* 182 */           if (((xx != 0) || (yy != 0) || (zz != 0)) && 
/* 183 */             (Utils.isWoodLog(world, pos.func_177982_a(xx, yy, zz)))) return true;
/*     */       }
/*     */     }
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   public BiomeGenBase func_150566_k()
/*     */   {
/* 191 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public net.minecraft.block.BlockFlower.EnumFlowerType func_180623_a(Random rand, BlockPos pos)
/*     */   {
/* 197 */     double d0 = MathHelper.func_151237_a((1.0D + field_180281_af.func_151601_a(pos.func_177958_n() / 48.0D, pos.func_177952_p() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
/* 198 */     return net.minecraft.block.BlockFlower.EnumFlowerType.values()[((int)(d0 * net.minecraft.block.BlockFlower.EnumFlowerType.values().length))];
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/biomes/BiomeGenMagicalForest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */