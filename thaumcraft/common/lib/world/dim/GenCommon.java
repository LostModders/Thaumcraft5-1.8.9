/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneTC;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneTC.StoneType;
/*     */ import thaumcraft.common.blocks.world.eldritch.BlockEldritch;
/*     */ import thaumcraft.common.blocks.world.eldritch.BlockEldritch.EldritchType;
/*     */ import thaumcraft.common.items.resources.ItemShard.ShardType;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchCrabSpawner;
/*     */ 
/*     */ public class GenCommon
/*     */ {
/*     */   static void placeBlock(World world, int i, int j, int k, int l, Cell cell)
/*     */   {
/*  25 */     placeBlock(world, i, j, k, l, null, cell);
/*     */   }
/*     */   
/*     */   static void placeBlock(World world, int x, int y, int z, int b, EnumFacing dir, Cell cell) {
/*  29 */     BlockPos pos = new BlockPos(x, y, z);
/*  30 */     IBlockState block = null;
/*  31 */     switch (b) {
/*     */     case 15: 
/*  33 */       block = BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ANCIENT_DOORWAY);
/*  34 */       decoCommon.remove(pos);
/*  35 */       crabSpawner.remove(pos);
/*  36 */       decoUrn.remove(pos);
/*  37 */       break;
/*     */     case 16: 
/*  39 */       block = BlocksTC.eldritch.func_176223_P().func_177226_a(BlockEldritch.TYPE, BlockEldritch.EldritchType.LOCK);
/*  40 */       decoCommon.remove(pos);
/*  41 */       crabSpawner.remove(pos);
/*  42 */       decoUrn.remove(pos);
/*  43 */       break;
/*     */     case 8: 
/*  45 */       block = BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ANCIENT_ROCK);
/*  46 */       break;
/*     */     case 17: 
/*  48 */       block = BlocksTC.eldritch.func_176223_P().func_177226_a(BlockEldritch.TYPE, BlockEldritch.EldritchType.DOOR);
/*  49 */       break;
/*     */     case 9: 
/*  51 */       block = Blocks.field_150350_a.func_176223_P();
/*  52 */       decoCommon.remove(pos);
/*  53 */       crabSpawner.remove(pos);
/*  54 */       decoUrn.remove(pos);
/*  55 */       break;
/*     */     case 1: 
/*  57 */       block = Blocks.field_150350_a.func_176223_P();
/*  58 */       decoCommon.remove(pos);
/*  59 */       crabSpawner.remove(pos);
/*  60 */       decoUrn.remove(pos);
/*  61 */       punctureLoc.add(pos);
/*  62 */       break;
/*     */     case 2: 
/*  64 */       if ((cell.feature != 7) || (world.field_73012_v.nextInt(3) != 0))
/*     */       {
/*     */ 
/*  67 */         if (world.field_73012_v.nextInt(25) == 0) {
/*  68 */           boolean crab = cell.feature == 7;
/*  69 */           if (((crab) && (cell.feature == 0)) || ((crab) && (cell.feature == 7))) {
/*  70 */             crabSpawner.add(pos);
/*     */           } else
/*  72 */             decoCommon.add(pos);
/*     */         }
/*  74 */         block = BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ANCIENT); }
/*  75 */       break;
/*     */     
/*     */     case 21: 
/*  78 */       block = BlocksTC.stone.func_176203_a(6);
/*  79 */       if (world.field_73012_v.nextInt(25) == 0) {
/*  80 */         block = BlocksTC.stone.func_176203_a(7);
/*     */       }
/*  82 */       else if (world.field_73012_v.nextInt(25) == 0) {
/*  83 */         boolean crab = cell.feature == 7;
/*  84 */         if (((crab) && (cell.feature == 0)) || ((crab) && (cell.feature == 7)) || ((crab) && (cell.feature == 12)))
/*  85 */           crabSpawner.add(pos); }
/*  86 */       break;
/*     */     
/*     */     case 18: 
/*  89 */       block = BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ANCIENT_ROCK);
/*  90 */       break;
/*     */     case 19: 
/*  92 */       if (world.func_180495_p(pos) != BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ANCIENT_ROCK)) {
/*  93 */         block = BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ANCIENT_TILE);
/*     */       }
/*     */       
/*     */       break;
/*     */     case 10: 
/*  98 */       int meta = 0;
/*  99 */       switch (dir) {
/* 100 */       case NORTH:  meta = 3; break;
/* 101 */       case SOUTH:  meta = 2; break;
/* 102 */       case EAST:  meta = 0; break;
/* 103 */       case WEST:  meta = 1; break;
/*     */       }
/*     */       
/* 106 */       block = BlocksTC.stairsAncient.func_176203_a(meta);
/* 107 */       break;
/*     */     
/*     */     case 11: 
/* 110 */       int meta = 0;
/* 111 */       switch (dir) {
/* 112 */       case NORTH:  meta = 7; break;
/* 113 */       case SOUTH:  meta = 6; break;
/* 114 */       case EAST:  meta = 4; break;
/* 115 */       case WEST:  meta = 5; break;
/*     */       }
/*     */       
/* 118 */       block = BlocksTC.stairsAncient.func_176203_a(meta);
/* 119 */       break;
/*     */     
/*     */     case 3: 
/* 122 */       if (world.field_73012_v.nextFloat() < 0.005D) decoUrn.add(pos);
/* 123 */       int meta = 0;
/* 124 */       switch (dir.ordinal()) {
/* 125 */       case 2: case 3:  meta = 1; break;
/* 126 */       case 4: case 5:  meta = 3;
/*     */       }
/* 128 */       block = BlocksTC.stairsAncient.func_176203_a(meta);
/* 129 */       break;
/*     */     
/*     */     case 4: 
/* 132 */       if (world.field_73012_v.nextFloat() < 0.005D) decoUrn.add(pos);
/* 133 */       int meta = 0;
/* 134 */       switch (dir.ordinal()) {
/* 135 */       case 2: case 3:  meta = 0; break;
/* 136 */       case 4: case 5:  meta = 2;
/*     */       }
/* 138 */       block = BlocksTC.stairsAncient.func_176203_a(meta);
/* 139 */       break;
/*     */     
/*     */     case 5: 
/* 142 */       int meta = 0;
/* 143 */       switch (dir.ordinal()) {
/* 144 */       case 2: case 3:  meta = 5; break;
/* 145 */       case 4: case 5:  meta = 7;
/*     */       }
/* 147 */       block = BlocksTC.stairsAncient.func_176203_a(meta);
/* 148 */       break;
/*     */     
/*     */     case 6: 
/* 151 */       int meta = 0;
/* 152 */       switch (dir.ordinal()) {
/* 153 */       case 2: case 3:  meta = 4; break;
/* 154 */       case 4: case 5:  meta = 6;
/*     */       }
/* 156 */       block = BlocksTC.stairsAncient.func_176203_a(meta);
/* 157 */       break;
/*     */     case 7: 
/* 159 */       block = BlocksTC.stone.func_176203_a(7);
/*     */     }
/*     */     
/* 162 */     if (block != null) {
/* 163 */       world.func_180501_a(pos, block, (block == BlocksTC.stone.func_176223_P().func_177226_a(BlockStoneTC.VARIANT, BlockStoneTC.StoneType.ANCIENT_ROCK)) || (block.func_177230_c() == Blocks.field_150350_a) ? 0 : 3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void genObelisk(World world, BlockPos pos)
/*     */   {
/* 171 */     world.func_175656_a(pos, BlocksTC.eldritch.func_176203_a(2));
/* 172 */     world.func_175656_a(pos.func_177981_b(1), BlocksTC.eldritch.func_176203_a(3));
/* 173 */     world.func_175656_a(pos.func_177981_b(2), BlocksTC.eldritch.func_176203_a(3));
/* 174 */     world.func_175656_a(pos.func_177981_b(3), BlocksTC.eldritch.func_176203_a(3));
/* 175 */     world.func_175656_a(pos.func_177981_b(4), BlocksTC.eldritch.func_176203_a(3));
/*     */   }
/*     */   
/* 178 */   static ArrayList<BlockPos> decoCommon = new ArrayList();
/* 179 */   static ArrayList<BlockPos> crabSpawner = new ArrayList();
/* 180 */   static ArrayList<BlockPos> decoUrn = new ArrayList();
/* 181 */   static ArrayList<BlockPos> punctureLoc = new ArrayList();
/*     */   static final int PUNCTURE = 1;
/*     */   
/*     */   static void processDecorations(World world) {
/* 185 */     for (BlockPos cc : decoUrn) {
/* 186 */       if (world.func_175623_d(cc.func_177984_a())) {
/* 187 */         world.func_175656_a(cc, BlocksTC.stone.func_176203_a(5));
/* 188 */         float rr = world.field_73012_v.nextFloat();
/* 189 */         int meta = rr < 0.1F ? 1 : rr < 0.025F ? 2 : 0;
/* 190 */         world.func_175656_a(cc.func_177984_a(), BlocksTC.lootUrn.func_176203_a(meta));
/*     */       }
/*     */     }
/*     */     
/* 194 */     for (BlockPos cc : decoCommon) {
/* 195 */       int exp = BlockUtils.countExposedSides(world, cc);
/* 196 */       if ((exp > 0) && ((exp == 1) || (!isVacuumVisible(world, cc))) && (BlockUtils.isBlockTouching(world, cc, BlocksTC.stone))) {
/* 197 */         int type = world.field_73012_v.nextInt(8) != 0 ? 1 : world.field_73012_v.nextInt(3) != 0 ? 0 : 2;
/* 198 */         switch (type) {
/*     */         case 0: 
/* 200 */           world.func_175656_a(cc, BlocksTC.stone.func_176203_a(4));
/* 201 */           break;
/*     */         case 1: 
/* 203 */           world.func_175656_a(cc, BlocksTC.stone.func_176203_a(11));
/* 204 */           break;
/*     */         case 2: 
/* 206 */           for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 207 */             if (world.func_175623_d(cc.func_177972_a(dir))) {
/* 208 */               Block oreBlock = ItemShard.ShardType.byMetadata(world.field_73012_v.nextInt(7)).getOre();
/* 209 */               world.func_180501_a(cc.func_177972_a(dir), oreBlock.func_176203_a(1 + world.field_73012_v.nextInt(3)), 0);
/* 210 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     
/* 218 */     for (BlockPos cc : crabSpawner) {
/* 219 */       int exp = BlockUtils.countExposedSides(world, cc);
/* 220 */       if ((exp == 1) && (BlockUtils.isBlockTouching(world, cc, BlocksTC.stone))) {
/* 221 */         world.func_175656_a(cc, BlocksTC.eldritch.func_176203_a(7));
/* 222 */         TileEntity te = world.func_175625_s(cc);
/* 223 */         if ((te != null) && ((te instanceof TileEldritchCrabSpawner))) {
/* 224 */           for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 225 */             if (world.func_175623_d(cc.func_177972_a(dir))) {
/* 226 */               ((TileEldritchCrabSpawner)te).setVentFacing((byte)dir.ordinal());
/* 227 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 235 */     for (BlockPos cc : punctureLoc) {
/* 236 */       for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 237 */         if ((world.func_180495_p(cc.func_177972_a(dir)) == BlocksTC.stone.func_176203_a(3)) && (world.func_180495_p(cc.func_177967_a(dir, 2)) == BlocksTC.stone.func_176203_a(12)))
/*     */         {
/* 239 */           world.func_175698_g(cc.func_177972_a(dir));
/* 240 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 245 */     decoCommon.clear();
/* 246 */     crabSpawner.clear();
/* 247 */     decoUrn.clear();
/* 248 */     punctureLoc.clear();
/*     */   }
/*     */   
/*     */   static boolean isVacuumVisible(World world, BlockPos pos) {
/* 252 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 253 */       if ((!world.func_180495_p(pos.func_177972_a(dir)).func_177230_c().func_149662_c()) && (world.func_180495_p(pos.func_177972_a(dir.func_176734_d())).func_177230_c() == BlocksTC.vacuum))
/*     */       {
/* 255 */         return true; }
/*     */     }
/* 257 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   static final int STONE = 2;
/*     */   
/*     */   static final int BASE_ROCK = 8;
/*     */   
/*     */   static final int AIR_REPL = 9;
/*     */   
/*     */   static final int STAIR_DIRECTIONAL = 10;
/*     */   static final int STAIR_DIRECTIONAL_INV = 11;
/*     */   static final int SLAB = 12;
/*     */   static final int DOOR_BLOCK = 15;
/*     */   static final int DOOR_LOCK = 16;
/*     */   static final int VOID_DOOR = 17;
/*     */   static final int ROCK = 18;
/*     */   static final int STONE_NOSPAWN = 19;
/*     */   static final int CRUST = 21;
/*     */   static void generateConnections(World world, Random random, int cx, int cz, int y, Cell cell, int depth, boolean justthetip)
/*     */   {
/* 278 */     int x = cx * 16;
/* 279 */     int z = cz * 16;
/*     */     
/* 281 */     if (cell.north) {
/* 282 */       for (int d = 0; d <= depth; d++) {
/* 283 */         for (int w = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 284 */             w < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); w++) {
/* 285 */           for (int h = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 286 */               h < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); h++)
/* 287 */             if ((d != depth) || (!justthetip) || (PAT_CONNECT[h][w] != 8))
/* 288 */               placeBlock(world, x + 3 + w, y + 10 - h, z + d, PAT_CONNECT[h][w], EnumFacing.NORTH, cell);
/*     */         }
/*     */       }
/*     */     }
/* 292 */     if (cell.south) {
/* 293 */       for (int d = 0; d <= depth; d++) {
/* 294 */         for (int w = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 295 */             w < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); w++) {
/* 296 */           for (int h = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 297 */               h < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); h++)
/* 298 */             if ((d != depth) || (!justthetip) || (PAT_CONNECT[h][w] != 8))
/* 299 */               placeBlock(world, x + 3 + w, y + 10 - h, z + 16 - d, PAT_CONNECT[h][w], EnumFacing.SOUTH, cell);
/*     */         }
/*     */       }
/*     */     }
/* 303 */     if (cell.east) {
/* 304 */       for (int d = 0; d <= depth; d++) {
/* 305 */         for (int w = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 306 */             w < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); w++) {
/* 307 */           for (int h = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 308 */               h < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); h++)
/* 309 */             if ((d != depth) || (!justthetip) || (PAT_CONNECT[h][w] != 8))
/* 310 */               placeBlock(world, x + 16 - d, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], EnumFacing.EAST, cell);
/*     */         }
/*     */       }
/*     */     }
/* 314 */     if (cell.west) {
/* 315 */       for (int d = 0; d <= depth; d++) {
/* 316 */         for (int w = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 317 */             w < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); w++) {
/* 318 */           for (int h = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 319 */               h < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); h++) {
/* 320 */             if ((d != depth) || (!justthetip) || (PAT_CONNECT[h][w] != 8))
/* 321 */               placeBlock(world, x + d, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], EnumFacing.WEST, cell);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 328 */   static final int[][] PAT_CONNECT = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 8, 8, 8, 8, 8, 8, 8, 0, 0 }, { 0, 8, 8, 2, 2, 2, 2, 2, 8, 8, 0 }, { 0, 8, 2, 5, 9, 9, 9, 6, 2, 8, 0 }, { 0, 8, 2, 9, 9, 9, 9, 9, 2, 8, 0 }, { 0, 8, 2, 9, 9, 9, 9, 9, 2, 8, 0 }, { 0, 8, 2, 9, 9, 9, 9, 9, 2, 8, 0 }, { 0, 8, 2, 3, 9, 9, 9, 4, 2, 8, 0 }, { 0, 8, 8, 2, 2, 2, 2, 2, 8, 8, 0 }, { 0, 0, 8, 8, 8, 8, 8, 8, 8, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
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
/*     */   static void generateChamberA(World world, Random random, int cx, int cz, int y, Cell cell)
/*     */   {
/* 342 */     int x = cx * 16;
/* 343 */     int z = cz * 16;
/*     */     
/* 345 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) { for (int c = 1; c < 12; c++) {
/* 346 */           placeBlock(world, x + a, y + c, z + b, 9, cell);
/*     */         }
/*     */       }
/* 349 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) for (int c = 1; c < 12; c++) {
/* 350 */           if (((a == 2) || (a == 14) || (b == 2) || (b == 14)) && 
/* 351 */             ((a != 2) || (b <= 3) || (b >= 12) || (!cell.west) || (c >= 10)) && 
/* 352 */             ((a != 14) || (b <= 3) || (b >= 12) || (!cell.east) || (c >= 10)) && 
/* 353 */             ((b != 2) || (a <= 3) || (a >= 12) || (!cell.north) || (c >= 10)) && (
/* 354 */             (b != 14) || (a <= 3) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 355 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/* 358 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) for (int c = 2; c < 11; c++) {
/* 359 */           if ((a == 3) || (a == 13) || (b == 3) || (b == 13)) {
/* 360 */             if (((c > 3) && (c < 9) && ((a == 8) || (b == 8))) || ((c > 4) && (c < 8) && ((a == 7) || (b == 7) || (a == 9) || (b == 9)))) {
/* 361 */               if (((a != 8) && (b != 8)) || (c != 6)) placeBlock(world, x + a, y + c, z + b, 19, cell);
/*     */             } else {
/* 363 */               placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */             }
/*     */           }
/*     */         }
/* 367 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) {
/* 368 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 369 */         placeBlock(world, x + a, y + 1, z + b, 2, cell);
/* 370 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/* 371 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */         
/* 373 */         if ((a > 1) && (a < 15) && (b > 1) && (b < 15)) {
/* 374 */           int q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
/* 375 */           for (int g = 0; g < q - 1; g++) { placeBlock(world, x + a, y + 1 + g, z + b, 2, cell);
/*     */           }
/*     */         }
/* 378 */         if ((a > 3) && (a < 13) && (b > 3) && (b < 13)) {
/* 379 */           int q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
/* 380 */           for (int g = 0; g < q; g++) { placeBlock(world, x + a, y + 11 - g, z + b, 2, cell);
/*     */           }
/*     */         }
/*     */       }
/* 384 */     for (int g = 0; g < 5; g++) {
/* 385 */       placeBlock(world, x + 6 + g, y + 2, z + 4, 10, EnumFacing.NORTH, cell);
/* 386 */       placeBlock(world, x + 6 + g, y + 2, z + 12, 10, EnumFacing.SOUTH, cell);
/* 387 */       placeBlock(world, x + 12, y + 2, z + 6 + g, 10, EnumFacing.EAST, cell);
/* 388 */       placeBlock(world, x + 4, y + 2, z + 6 + g, 10, EnumFacing.WEST, cell);
/*     */     }
/*     */   }
/*     */   
/*     */   static void generateChamberB(World world, Random random, int cx, int cz, int y, Cell cell) {
/* 393 */     int x = cx * 16;
/* 394 */     int z = cz * 16;
/*     */     
/* 396 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) { for (int c = 1; c < 12; c++) {
/* 397 */           placeBlock(world, x + a, y + c, z + b, 9, cell);
/*     */         }
/*     */       }
/* 400 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) for (int c = 1; c < 12; c++) {
/* 401 */           if (((a == 2) || (a == 14) || (b == 2) || (b == 14)) && 
/* 402 */             ((a != 2) || (b <= 3) || (b >= 12) || (!cell.west) || (c >= 10)) && 
/* 403 */             ((a != 14) || (b <= 3) || (b >= 12) || (!cell.east) || (c >= 10)) && 
/* 404 */             ((b != 2) || (a <= 3) || (a >= 12) || (!cell.north) || (c >= 10)) && (
/* 405 */             (b != 14) || (a <= 3) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 406 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/* 409 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) { for (int c = 2; c < 11; c++) {
/* 410 */           if ((a == 3) || (a == 13) || (b == 3) || (b == 13)) {
/* 411 */             placeBlock(world, x + a, y + c, z + b, 2, cell);
/*     */           }
/*     */         }
/*     */       }
/* 415 */     for (int a = 2; a <= 14; a++) { for (int b = 2; b <= 14; b++) {
/* 416 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 417 */         placeBlock(world, x + a, y + 1, z + b, 2, cell);
/* 418 */         placeBlock(world, x + a, y + 11, z + b, 8, cell);
/* 419 */         placeBlock(world, x + a, y + 10, z + b, 2, cell);
/*     */         
/* 421 */         if ((a > 3) && (a < 13) && (b > 3) && (b < 13)) {
/* 422 */           if (((a <= 5) && (b <= 5)) || ((a <= 5) && (b >= 11)) || ((a >= 11) && (b <= 5)) || ((a >= 11) && (b >= 11))) {
/* 423 */             placeBlock(world, x + a, y + 2, z + b, 2, cell);
/* 424 */             placeBlock(world, x + a, y + 9, z + b, 2, cell);
/*     */           }
/* 426 */           if (((a == 5) && (b == 5)) || ((a == 5) && (b == 11)) || ((a == 11) && (b == 5)) || ((a == 11) && (b == 11))) {
/* 427 */             world.func_175656_a(new BlockPos(x + a, y + 3, z + b), BlocksTC.stone.func_176203_a(5));
/* 428 */             world.func_175656_a(new BlockPos(x + a, y + 8, z + b), BlocksTC.stone.func_176203_a(5));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 433 */     for (int g = 0; g < 5; g++) {
/* 434 */       placeBlock(world, x + 6 + g, y + 2, z + 4, 10, EnumFacing.NORTH, cell);
/* 435 */       placeBlock(world, x + 6 + g, y + 2, z + 12, 10, EnumFacing.SOUTH, cell);
/* 436 */       placeBlock(world, x + 12, y + 2, z + 6 + g, 10, EnumFacing.EAST, cell);
/* 437 */       placeBlock(world, x + 4, y + 2, z + 6 + g, 10, EnumFacing.WEST, cell);
/*     */       
/* 439 */       placeBlock(world, x + 6 + g, y + 9, z + 4, 11, EnumFacing.NORTH, cell);
/* 440 */       placeBlock(world, x + 6 + g, y + 9, z + 12, 11, EnumFacing.SOUTH, cell);
/* 441 */       placeBlock(world, x + 12, y + 9, z + 6 + g, 11, EnumFacing.EAST, cell);
/* 442 */       placeBlock(world, x + 4, y + 9, z + 6 + g, 11, EnumFacing.WEST, cell);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/GenCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */