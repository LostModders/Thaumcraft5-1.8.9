/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ public class GenPassage extends GenCommon
/*     */ {
/*     */   static void generateDefaultPassage(World world, Random random, int cx, int cz, int y, Cell cell)
/*     */   {
/*  20 */     int x = cx * 16;
/*  21 */     int z = cz * 16;
/*     */     
/*  23 */     for (int w = 2; w < 7; w++) for (int h = 2; h < 7; h++) { for (int d = 1; d < 7; d++) {
/*  24 */           placeBlock(world, x + 4 + w, y + 2 + d, z + 4 + h, 9, cell);
/*     */         }
/*     */       }
/*  27 */     generateConnections(world, random, cx, cz, y, cell, 4, false);
/*     */     
/*  29 */     int mod = 0;
/*  30 */     if ((cell.north) && (cell.south) && (cell.west) && (cell.east) && (random.nextBoolean())) {
/*  31 */       mod = 1;
/*     */     }
/*     */     
/*  34 */     for (int w = 1; w < 8; w++) { for (int h = 1; h < 8; h++) {
/*  35 */         if ((w == 4) && (h == 4) && (mod == 1)) {
/*  36 */           placeBlock(world, x + 4 + w, y + 2, z + 4 + h, 7, cell);
/*  37 */           placeBlock(world, x + 4 + w, y + 8, z + 4 + h, 7, cell);
/*     */         } else {
/*  39 */           placeBlock(world, x + 4 + w, y + 2, z + 4 + h, (cell.feature == 21) && (random.nextInt(3) == 0) ? 1 : 2, cell);
/*  40 */           placeBlock(world, x + 4 + w, y + 8, z + 4 + h, (cell.feature == 21) && (random.nextInt(3) == 0) ? 1 : 2, cell);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*  45 */         placeBlock(world, x + 4 + w, y + 1, z + 4 + h, 8, cell);
/*  46 */         placeBlock(world, x + 4 + w, y + 9, z + 4 + h, 8, cell);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  51 */     if (cell.north) {
/*  52 */       for (int w = 2 + mod; w < 9 - mod; w++) for (int h = 2 + mod; h < 9 - mod; h++) {
/*  53 */           placeBlock(world, x + 3 + w, y + 10 - h, z + 5, PAT_CONNECT[h][w], EnumFacing.NORTH, cell);
/*     */         }
/*  55 */       if (mod == 0) {
/*  56 */         if (cell.west) {
/*  57 */           placeBlock(world, x + 6, y + 3, z + 6, 3, EnumFacing.EAST, cell);
/*  58 */           placeBlock(world, x + 6, y + 7, z + 6, 5, EnumFacing.EAST, cell);
/*     */         }
/*  60 */         if (cell.east) {
/*  61 */           placeBlock(world, x + 10, y + 3, z + 6, 3, EnumFacing.EAST, cell);
/*  62 */           placeBlock(world, x + 10, y + 7, z + 6, 5, EnumFacing.EAST, cell);
/*     */         }
/*     */       }
/*     */     } else {
/*  66 */       for (int w = 1; w < 8; w++) { for (int h = 1; h < 8; h++) {
/*  67 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 5, (cell.feature == 21) && (random.nextInt(3) == 0) ? 1 : 2, cell);
/*  68 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 4, 8, cell);
/*  69 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 3, 1, cell);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  79 */       for (int w = 2; w < 7; w++) {
/*  80 */         placeBlock(world, x + 4 + w, y + 3, z + 6, 3, EnumFacing.EAST, cell);
/*  81 */         placeBlock(world, x + 4 + w, y + 7, z + 6, 5, EnumFacing.EAST, cell);
/*     */       }
/*     */     }
/*     */     
/*  85 */     if (cell.south) {
/*  86 */       for (int w = 2 + mod; w < 9 - mod; w++) for (int h = 2 + mod; h < 9 - mod; h++) {
/*  87 */           placeBlock(world, x + 3 + w, y + 10 - h, z + 11, PAT_CONNECT[h][w], EnumFacing.SOUTH, cell);
/*     */         }
/*  89 */       if (mod == 0) {
/*  90 */         if (cell.west) {
/*  91 */           placeBlock(world, x + 6, y + 3, z + 10, 4, EnumFacing.EAST, cell);
/*  92 */           placeBlock(world, x + 6, y + 7, z + 10, 6, EnumFacing.EAST, cell);
/*     */         }
/*  94 */         if (cell.east) {
/*  95 */           placeBlock(world, x + 10, y + 3, z + 10, 4, EnumFacing.EAST, cell);
/*  96 */           placeBlock(world, x + 10, y + 7, z + 10, 6, EnumFacing.EAST, cell);
/*     */         }
/*     */       }
/*     */     } else {
/* 100 */       for (int w = 1; w < 8; w++) { for (int h = 1; h < 8; h++) {
/* 101 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 11, (cell.feature == 21) && (random.nextInt(3) == 0) ? 1 : 2, cell);
/* 102 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 12, 8, cell);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 113 */       for (int w = 2; w < 7; w++) {
/* 114 */         placeBlock(world, x + 4 + w, y + 3, z + 10, 4, EnumFacing.EAST, cell);
/* 115 */         placeBlock(world, x + 4 + w, y + 7, z + 10, 6, EnumFacing.EAST, cell);
/*     */       }
/*     */     }
/*     */     
/* 119 */     if (cell.east) {
/* 120 */       for (int w = 2 + mod; w < 9 - mod; w++) for (int h = 2 + mod; h < 9 - mod; h++) {
/* 121 */           placeBlock(world, x + 11, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], EnumFacing.EAST, cell);
/*     */         }
/* 123 */       if (mod == 0) {
/* 124 */         if (cell.north) {
/* 125 */           placeBlock(world, x + 10, y + 3, z + 6, 4, EnumFacing.NORTH, cell);
/* 126 */           placeBlock(world, x + 10, y + 7, z + 6, 6, EnumFacing.NORTH, cell);
/*     */         }
/* 128 */         if (cell.south) {
/* 129 */           placeBlock(world, x + 10, y + 3, z + 10, 4, EnumFacing.NORTH, cell);
/* 130 */           placeBlock(world, x + 10, y + 7, z + 10, 6, EnumFacing.NORTH, cell);
/*     */         }
/*     */       }
/*     */     } else {
/* 134 */       for (int w = 1; w < 8; w++) { for (int h = 1; h < 8; h++) {
/* 135 */           placeBlock(world, x + 11, y + 9 - h, z + 4 + w, (cell.feature == 21) && (random.nextInt(3) == 0) ? 1 : 2, cell);
/* 136 */           placeBlock(world, x + 12, y + 9 - h, z + 4 + w, 8, cell);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 147 */       for (int w = 2; w < 7; w++) {
/* 148 */         placeBlock(world, x + 10, y + 3, z + 4 + w, 4, EnumFacing.NORTH, cell);
/* 149 */         placeBlock(world, x + 10, y + 7, z + 4 + w, 6, EnumFacing.NORTH, cell);
/*     */       }
/*     */     }
/*     */     
/* 153 */     if (cell.west) {
/* 154 */       for (int w = 2 + mod; w < 9 - mod; w++) for (int h = 2 + mod; h < 9 - mod; h++) {
/* 155 */           placeBlock(world, x + 5, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], EnumFacing.WEST, cell);
/*     */         }
/* 157 */       if (mod == 0) {
/* 158 */         if (cell.north) {
/* 159 */           placeBlock(world, x + 6, y + 3, z + 6, 3, EnumFacing.NORTH, cell);
/* 160 */           placeBlock(world, x + 6, y + 7, z + 6, 5, EnumFacing.NORTH, cell);
/*     */         }
/* 162 */         if (cell.south) {
/* 163 */           placeBlock(world, x + 6, y + 3, z + 10, 3, EnumFacing.NORTH, cell);
/* 164 */           placeBlock(world, x + 6, y + 7, z + 10, 5, EnumFacing.NORTH, cell);
/*     */         }
/*     */       }
/*     */     } else {
/* 168 */       for (int w = 1; w < 8; w++) { for (int h = 1; h < 8; h++) {
/* 169 */           placeBlock(world, x + 5, y + 9 - h, z + 4 + w, (cell.feature == 21) && (random.nextInt(3) == 0) ? 1 : 2, cell);
/* 170 */           placeBlock(world, x + 4, y + 9 - h, z + 4 + w, 8, cell);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 181 */       for (int w = 2; w < 7; w++) {
/* 182 */         placeBlock(world, x + 6, y + 3, z + 4 + w, 3, EnumFacing.NORTH, cell);
/* 183 */         placeBlock(world, x + 6, y + 7, z + 4 + w, 5, EnumFacing.NORTH, cell);
/*     */       }
/*     */     }
/*     */     
/* 187 */     if (mod == 1) {
/* 188 */       placeBlock(world, x + 5, y + 3, z + 5, 3, EnumFacing.EAST, cell);
/* 189 */       placeBlock(world, x + 5, y + 7, z + 5, 5, EnumFacing.EAST, cell);
/* 190 */       placeBlock(world, x + 5, y + 3, z + 6, 3, EnumFacing.NORTH, cell);
/* 191 */       placeBlock(world, x + 5, y + 7, z + 6, 5, EnumFacing.NORTH, cell);
/*     */       
/* 193 */       placeBlock(world, x + 11, y + 3, z + 5, 3, EnumFacing.EAST, cell);
/* 194 */       placeBlock(world, x + 11, y + 7, z + 5, 5, EnumFacing.EAST, cell);
/* 195 */       placeBlock(world, x + 11, y + 3, z + 6, 4, EnumFacing.NORTH, cell);
/* 196 */       placeBlock(world, x + 11, y + 7, z + 6, 6, EnumFacing.NORTH, cell);
/*     */       
/* 198 */       placeBlock(world, x + 5, y + 3, z + 11, 3, EnumFacing.NORTH, cell);
/* 199 */       placeBlock(world, x + 5, y + 7, z + 11, 5, EnumFacing.NORTH, cell);
/* 200 */       placeBlock(world, x + 6, y + 3, z + 11, 4, EnumFacing.EAST, cell);
/* 201 */       placeBlock(world, x + 6, y + 7, z + 11, 6, EnumFacing.EAST, cell);
/*     */       
/* 203 */       placeBlock(world, x + 11, y + 3, z + 11, 4, EnumFacing.NORTH, cell);
/* 204 */       placeBlock(world, x + 11, y + 7, z + 11, 6, EnumFacing.NORTH, cell);
/* 205 */       placeBlock(world, x + 10, y + 3, z + 11, 4, EnumFacing.EAST, cell);
/* 206 */       placeBlock(world, x + 10, y + 7, z + 11, 6, EnumFacing.EAST, cell);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 211 */     if (cell.feature == 22) {
/* 212 */       for (int w = -4; w <= 4; w++) { for (int h = -4; h < 5; h++) { for (int j = -4; j <= 4; j++) {
/* 213 */             BlockPos p = new BlockPos(x + 8 + w, y + 4 + h, z + 8 + j);
/* 214 */             if (((world.func_175623_d(p)) || (world.func_180495_p(p).func_177230_c() == BlocksTC.stone) || (world.func_180495_p(p).func_177230_c() == BlocksTC.stairsAncient)) && (random.nextBoolean()))
/*     */             {
/*     */ 
/* 217 */               placeBlock(world, x + 8 + w, y + 4 + h, z + 8 + j, 21, cell);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 224 */     if (cell.feature == 23) {
/* 225 */       for (int w = -4; w <= 4; w++) for (int h = -3; h <= 3; h++) for (int j = -4; j <= 4; j++) {
/* 226 */             BlockPos p = new BlockPos(x + 8 + w, y + 4 + h, z + 8 + j);
/* 227 */             if ((world.func_175623_d(p)) && (thaumcraft.common.lib.utils.BlockUtils.isAdjacentToSolidBlock(world, p)) && 
/* 228 */               (random.nextInt(3) != 0)) { world.func_175656_a(p, BlocksTC.taintFibre.func_176223_P());
/*     */             }
/*     */           }
/* 231 */       AuraHelper.pollute(world, new BlockPos(x + 8, y + 4, z + 8), Config.AURABASE * 5, false);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 236 */     if (cell.feature == 24) {
/* 237 */       for (int w = -3; w <= 3; w++) for (int h = -3; h <= 3; h++) for (int j = -3; j <= 3; j++) {
/* 238 */             BlockPos p = new BlockPos(x + 8 + w, y + 4 + h, z + 8 + j);
/* 239 */             if ((world.func_175623_d(p)) && (random.nextFloat() < 0.35F)) {
/* 240 */               world.func_175656_a(p, Blocks.field_150321_G.func_176223_P());
/*     */             }
/* 242 */             world.func_175656_a(new BlockPos(x + 8, y + 4, z + 8), Blocks.field_150474_ac.func_176223_P());
/* 243 */             TileEntityMobSpawner var12 = (TileEntityMobSpawner)world.func_175625_s(new BlockPos(x + 8, y + 4, z + 8));
/* 244 */             if (var12 != null) var12.func_145881_a().func_98272_a("Thaumcraft.MindSpider");
/*     */           }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/GenPassage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */