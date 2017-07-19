/*     */ package thaumcraft.common.lib.world.objects;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.lib.utils.TCVec3;
/*     */ import thaumcraft.common.lib.world.dim.GenCommon;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ import thaumcraft.common.tiles.misc.TileBanner;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchAltar;
/*     */ 
/*     */ public class WorldGenEldritchRing extends WorldGenerator
/*     */ {
/*     */   public int chunkX;
/*     */   public int chunkZ;
/*     */   public int width;
/*     */   
/*     */   protected Block[] GetValidSpawnBlocks()
/*     */   {
/*  28 */     return new Block[] { Blocks.field_150348_b, Blocks.field_150354_m, Blocks.field_150403_cj, Blocks.field_150349_c, Blocks.field_150351_n, Blocks.field_150346_d };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean locationIsValidSpawn(World world, BlockPos pos)
/*     */   {
/*  40 */     int distanceToAir = 0;
/*  41 */     Block checkID = world.func_180495_p(pos).func_177230_c();
/*     */     
/*  43 */     while ((checkID != Blocks.field_150350_a) && (!world.func_175623_d(pos))) {
/*  44 */       distanceToAir++;
/*  45 */       checkID = world.func_180495_p(pos.func_177981_b(distanceToAir)).func_177230_c();
/*     */     }
/*     */     
/*  48 */     if (distanceToAir > 2) {
/*  49 */       return false;
/*     */     }
/*     */     
/*  52 */     int j = distanceToAir - 1;
/*     */     
/*  54 */     Block blockID = world.func_180495_p(pos.func_177981_b(j)).func_177230_c();
/*  55 */     Block blockIDAbove = world.func_180495_p(pos.func_177981_b(j + 1)).func_177230_c();
/*  56 */     Block blockIDBelow = world.func_180495_p(pos.func_177981_b(j - 1)).func_177230_c();
/*  57 */     for (Block x : GetValidSpawnBlocks()) {
/*  58 */       if (blockIDAbove != Blocks.field_150350_a) {
/*  59 */         return false;
/*     */       }
/*  61 */       if (blockID == x)
/*  62 */         return true;
/*  63 */       if (((blockID == Blocks.field_150431_aC) || (blockID == Blocks.field_150329_H)) && (blockIDBelow == x)) {
/*  64 */         return true;
/*     */       }
/*     */     }
/*  67 */     return false;
/*     */   }
/*     */   
/*  70 */   public int height = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180709_b(World world, Random rand, BlockPos pos)
/*     */   {
/*  76 */     if ((!locationIsValidSpawn(world, pos.func_177982_a(-3, 0, -3))) || (!locationIsValidSpawn(world, pos)) || (!locationIsValidSpawn(world, pos.func_177982_a(3, 0, 0))) || (!locationIsValidSpawn(world, pos.func_177982_a(3, 0, 3))) || (!locationIsValidSpawn(world, pos.func_177982_a(0, 0, 3))) || (MazeHandler.mazesInRange(this.chunkX, this.chunkZ, this.width, this.height)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */       return false;
/*     */     }
/*     */     
/*  87 */     IBlockState replaceBlock = world.func_180494_b(pos).field_76752_A;
/*  88 */     int i = pos.func_177958_n();
/*  89 */     int k = pos.func_177952_p();
/*     */     
/*  91 */     for (int x = i - 3; x <= i + 3; x++) {
/*  92 */       for (int z = k - 3; z <= k + 3; z++) {
/*  93 */         BlockPos p = new BlockPos(x, pos.func_177956_o(), z);
/*  94 */         for (int q = -3; q < 5; q++) {
/*  95 */           Block bb = world.func_180495_p(p.func_177981_b(q)).func_177230_c();
/*  96 */           TCVec3 v1 = TCVec3.createVectorHelper(pos);
/*  97 */           TCVec3 v2 = TCVec3.createVectorHelper(p.func_177981_b(q));
/*  98 */           if ((q <= 0) && (v1.squareDistanceTo(v2) < 15.0D)) {
/*  99 */             if (rand.nextInt(3) == 0) {
/* 100 */               world.func_175656_a(p.func_177981_b(q), BlocksTC.stone.func_176203_a(4));
/*     */             } else {
/* 102 */               world.func_175656_a(p.func_177981_b(q), Blocks.field_150343_Z.func_176223_P());
/*     */             }
/* 104 */           } else if (q > 0) {
/* 105 */             world.func_175698_g(p.func_177981_b(q));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 110 */     for (int x = i - 3; x <= i + 3; x++) {
/* 111 */       for (int z = k - 3; z <= k + 3; z++) {
/* 112 */         if (((x != i - 3) && (x != i + 3)) || ((z != k - 3) && (z != k + 3))) {
/* 113 */           BlockPos p = new BlockPos(x, pos.func_177956_o(), z);
/*     */           
/* 115 */           if ((x == i) && (z == k))
/*     */           {
/* 117 */             world.func_175656_a(p.func_177984_a(), BlocksTC.eldritch.func_176223_P());
/* 118 */             world.func_175656_a(p, BlocksTC.stone.func_176203_a(4));
/* 119 */             int r = rand.nextInt(10);
/* 120 */             TileEntity te = world.func_175625_s(p.func_177984_a());
/* 121 */             if ((te != null) && ((te instanceof TileEldritchAltar))) {
/* 122 */               switch (r) {
/*     */               case 1: case 2: case 3: case 4: 
/* 124 */                 ((TileEldritchAltar)te).setSpawner(true);
/* 125 */                 ((TileEldritchAltar)te).setSpawnType((byte)0);
/* 126 */                 for (EnumFacing dir : EnumFacing.field_176754_o)
/*     */                 {
/* 128 */                   BlockPos pp = p.func_177982_a(-dir.func_82601_c() * 3, 1, dir.func_82599_e() * 3);
/* 129 */                   world.func_175656_a(pp, BlocksTC.banner.func_176203_a(1));
/* 130 */                   te = world.func_175625_s(pp);
/* 131 */                   if ((te != null) && ((te instanceof TileBanner)))
/*     */                   {
/* 133 */                     int face = 0;
/*     */                     
/* 135 */                     switch (dir.ordinal()) {
/* 136 */                     case 2:  face = 8; break;
/* 137 */                     case 3:  face = 0; break;
/* 138 */                     case 4:  face = 12; break;
/* 139 */                     case 5:  face = 4; break;
/*     */                     }
/* 141 */                     ((TileBanner)te).setBannerFacing((byte)face);
/*     */                   }
/*     */                 }
/* 144 */                 break;
/*     */               case 6: case 7: 
/* 146 */                 ((TileEldritchAltar)te).setSpawner(true);
/* 147 */                 ((TileEldritchAltar)te).setSpawnType((byte)1);
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 156 */             GenCommon.genObelisk(world, p.func_177981_b(3));
/*     */ 
/*     */ 
/*     */           }
/* 160 */           else if (((x != i - 3) && (x != i + 3)) || (((Math.abs((z - k) % 2) == 1) || (((z == k - 3) || (z == k + 3)) && (Math.abs((x - i) % 2) == 1))) && (Math.abs(x - i) != Math.abs(z - k)))) {
/* 161 */             world.func_175656_a(p.func_177984_a(), BlocksTC.eldritch.func_176203_a(1));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 167 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/objects/WorldGenEldritchRing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */