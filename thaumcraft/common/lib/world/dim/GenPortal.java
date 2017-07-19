/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ 
/*     */ public class GenPortal
/*     */   extends GenCommon
/*     */ {
/*     */   static void generatePortal(World world, Random random, int cx, int cz, int y, Cell cell)
/*     */   {
/*  15 */     int x = cx * 16;
/*  16 */     int z = cz * 16;
/*     */     
/*  18 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) { for (int c = 1; c < 12; c++) {
/*  19 */           placeBlock(world, x + a, y + c, z + b, 9, cell);
/*     */         }
/*     */       }
/*  22 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) for (int c = 1; c < 12; c++) {
/*  23 */           if (((a == 2) || (a == 14) || (b == 2) || (b == 14)) && 
/*  24 */             ((a != 2) || (b <= 3) || (b >= 12) || (!cell.west) || (c >= 10)) && 
/*  25 */             ((a != 14) || (b <= 3) || (b >= 12) || (!cell.east) || (c >= 10)) && 
/*  26 */             ((b != 2) || (a <= 3) || (a >= 12) || (!cell.north) || (c >= 10)) && (
/*  27 */             (b != 14) || (a <= 3) || (a >= 12) || (!cell.south) || (c >= 10))) {
/*  28 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/*  31 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) { for (int c = 2; c < 11; c++) {
/*  32 */           if (((a == 3) || (a == 13) || (b == 3) || (b == 13)) && ((a > 4) || (b > 4)) && ((a > 4) || (b < 12)) && ((a < 12) || (b > 4)) && ((a < 12) || (b < 12)))
/*     */           {
/*     */ 
/*  35 */             placeBlock(world, x + a, y + c, z + b, 2, cell);
/*     */           }
/*     */         }
/*     */       }
/*  39 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) {
/*  40 */         placeBlock(world, x + a, y, z + b, 8, cell);
/*  41 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/*  42 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/*  43 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */         
/*  45 */         if ((a > 1) && (a < 15) && (b > 1) && (b < 15)) {
/*  46 */           int q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
/*  47 */           for (int g = 0; g < q - 1; g++) { placeBlock(world, x + a, y + 1 + g, z + b, 19, cell);
/*     */           }
/*     */         }
/*  50 */         if ((a > 3) && (a < 13) && (b > 3) && (b < 13)) {
/*  51 */           int q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
/*  52 */           for (int g = 0; g < q; g++) { placeBlock(world, x + a, y + 11 - g, z + b, 19, cell);
/*     */           }
/*     */         }
/*     */       }
/*  56 */     for (int g = 0; g < 5; g++) {
/*  57 */       placeBlock(world, x + 6 + g, y + 2, z + 4, 10, EnumFacing.NORTH, cell);
/*  58 */       placeBlock(world, x + 6 + g, y + 2, z + 12, 10, EnumFacing.SOUTH, cell);
/*  59 */       placeBlock(world, x + 12, y + 2, z + 6 + g, 10, EnumFacing.EAST, cell);
/*  60 */       placeBlock(world, x + 4, y + 2, z + 6 + g, 10, EnumFacing.WEST, cell);
/*     */     }
/*     */     
/*     */ 
/*  64 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */     
/*  66 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) { for (int c = 1; c < 12; c++) {
/*  67 */           if (((a <= 4) && (b <= 4)) || ((a <= 4) && (b >= 12)) || ((a >= 12) && (b <= 4)) || ((a >= 12) && (b >= 12)))
/*     */           {
/*  69 */             placeBlock(world, x + a, y + c, z + b, 15, cell);
/*     */           }
/*     */         }
/*     */       }
/*  73 */     placeBlock(world, x + 5, y + 3, z + 5, 10, EnumFacing.NORTH, cell);
/*  74 */     placeBlock(world, x + 4, y + 3, z + 5, 10, EnumFacing.NORTH, cell);
/*  75 */     placeBlock(world, x + 5, y + 3, z + 4, 10, EnumFacing.WEST, cell);
/*  76 */     placeBlock(world, x + 5, y + 8, z + 5, 11, EnumFacing.NORTH, cell);
/*  77 */     placeBlock(world, x + 4, y + 8, z + 5, 11, EnumFacing.NORTH, cell);
/*  78 */     placeBlock(world, x + 5, y + 8, z + 4, 11, EnumFacing.WEST, cell);
/*     */     
/*  80 */     placeBlock(world, x + 12, y + 3, z + 5, 10, EnumFacing.NORTH, cell);
/*  81 */     placeBlock(world, x + 11, y + 3, z + 5, 10, EnumFacing.NORTH, cell);
/*  82 */     placeBlock(world, x + 11, y + 3, z + 4, 10, EnumFacing.EAST, cell);
/*  83 */     placeBlock(world, x + 12, y + 8, z + 5, 11, EnumFacing.NORTH, cell);
/*  84 */     placeBlock(world, x + 11, y + 8, z + 5, 11, EnumFacing.NORTH, cell);
/*  85 */     placeBlock(world, x + 11, y + 8, z + 4, 11, EnumFacing.EAST, cell);
/*     */     
/*  87 */     placeBlock(world, x + 5, y + 3, z + 11, 10, EnumFacing.SOUTH, cell);
/*  88 */     placeBlock(world, x + 4, y + 3, z + 11, 10, EnumFacing.SOUTH, cell);
/*  89 */     placeBlock(world, x + 5, y + 3, z + 12, 10, EnumFacing.WEST, cell);
/*  90 */     placeBlock(world, x + 5, y + 8, z + 11, 11, EnumFacing.SOUTH, cell);
/*  91 */     placeBlock(world, x + 4, y + 8, z + 11, 11, EnumFacing.SOUTH, cell);
/*  92 */     placeBlock(world, x + 5, y + 8, z + 12, 11, EnumFacing.WEST, cell);
/*     */     
/*  94 */     placeBlock(world, x + 12, y + 3, z + 11, 10, EnumFacing.SOUTH, cell);
/*  95 */     placeBlock(world, x + 11, y + 3, z + 11, 10, EnumFacing.SOUTH, cell);
/*  96 */     placeBlock(world, x + 11, y + 3, z + 12, 10, EnumFacing.EAST, cell);
/*  97 */     placeBlock(world, x + 12, y + 8, z + 11, 11, EnumFacing.SOUTH, cell);
/*  98 */     placeBlock(world, x + 11, y + 8, z + 11, 11, EnumFacing.SOUTH, cell);
/*  99 */     placeBlock(world, x + 11, y + 8, z + 12, 11, EnumFacing.EAST, cell);
/*     */     
/* 101 */     world.func_175656_a(new BlockPos(x + 8, y + 2, z + 8), BlocksTC.eldritch.func_176203_a(1));
/* 102 */     world.func_175656_a(new BlockPos(x + 8, y + 3, z + 8), BlocksTC.eldritch.func_176203_a(6));
/* 103 */     genObelisk(world, new BlockPos(x + 8, y + 4, z + 8));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/GenPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */