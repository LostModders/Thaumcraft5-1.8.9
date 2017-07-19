/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class Gen2x2 extends GenCommon
/*     */ {
/*     */   static void generateUpperLeft(World world, Random random, int cx, int cz, int y, Cell cell)
/*     */   {
/*  11 */     int x = cx * 16;
/*  12 */     int z = cz * 16;
/*     */     
/*  14 */     for (int a = 2; a <= 15; a++) for (int b = 2; b <= 15; b++) { for (int c = 1; c < 12; c++) {
/*  15 */           placeBlock(world, x + a, y + c, z + b, 9, cell);
/*     */         }
/*     */       }
/*  18 */     for (int a = 2; a <= 15; a++) for (int b = 2; b <= 15; b++) for (int c = 1; c < 12; c++) {
/*  19 */           if (((a == 2) || (b == 2)) && 
/*  20 */             ((a != 2) || (b <= 4) || (b >= 12) || (!cell.west) || (c >= 10)) && (
/*  21 */             (b != 2) || (a <= 4) || (a >= 12) || (!cell.north) || (c >= 10))) {
/*  22 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/*  25 */     for (int a = 3; a <= 15; a++) for (int b = 3; b <= 15; b++) { for (int c = 2; c < 11; c++) {
/*  26 */           if ((a == 3) || (b == 3)) {
/*  27 */             placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */           }
/*     */         }
/*     */       }
/*  31 */     for (int a = 2; a <= 15; a++) { for (int b = 2; b <= 15; b++)
/*     */       {
/*  33 */         placeBlock(world, x + a, y, z + b, 8, cell);
/*  34 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/*     */         
/*  36 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/*  37 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */       }
/*     */     }
/*  40 */     for (int g = 4; g <= 15; g++) {
/*  41 */       placeBlock(world, x + g, y + 2, z + 4, 10, EnumFacing.NORTH, cell);
/*  42 */       placeBlock(world, x + g, y + 10, z + 4, 11, EnumFacing.NORTH, cell);
/*     */     }
/*  44 */     for (int g = 4; g <= 15; g++) {
/*  45 */       placeBlock(world, x + 4, y + 2, z + g, 10, EnumFacing.WEST, cell);
/*  46 */       placeBlock(world, x + 4, y + 10, z + g, 11, EnumFacing.WEST, cell);
/*     */     }
/*     */     
/*  49 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */   }
/*     */   
/*     */   static void generateUpperRight(World world, Random random, int cx, int cz, int y, Cell cell) {
/*  53 */     int x = cx * 16;
/*  54 */     int z = cz * 16;
/*     */     
/*  56 */     for (int a = 0; a <= 14; a++) for (int b = 2; b <= 15; b++) { for (int c = 1; c < 12; c++) {
/*  57 */           placeBlock(world, x + a, y + c, z + b, 9, cell);
/*     */         }
/*     */       }
/*  60 */     for (int a = 0; a <= 14; a++) for (int b = 2; b <= 15; b++) for (int c = 1; c < 12; c++) {
/*  61 */           if (((a == 14) || (b == 2)) && 
/*  62 */             ((a != 14) || (b <= 4) || (b >= 12) || (!cell.east) || (c >= 10)) && (
/*  63 */             (b != 2) || (a <= 4) || (a >= 12) || (!cell.north) || (c >= 10))) {
/*  64 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/*  67 */     for (int a = 0; a <= 13; a++) for (int b = 3; b <= 15; b++) { for (int c = 2; c < 11; c++) {
/*  68 */           if ((a == 13) || (b == 3)) {
/*  69 */             placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */           }
/*     */         }
/*     */       }
/*  73 */     for (int a = 0; a <= 14; a++) { for (int b = 2; b <= 15; b++)
/*     */       {
/*  75 */         placeBlock(world, x + a, y, z + b, 8, cell);
/*  76 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/*     */         
/*  78 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/*  79 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */       }
/*     */     }
/*  82 */     for (int g = 0; g <= 11; g++) {
/*  83 */       placeBlock(world, x + g, y + 2, z + 4, 10, EnumFacing.NORTH, cell);
/*  84 */       placeBlock(world, x + g, y + 10, z + 4, 11, EnumFacing.NORTH, cell);
/*     */     }
/*  86 */     for (int g = 4; g <= 15; g++) {
/*  87 */       placeBlock(world, x + 12, y + 2, z + g, 10, EnumFacing.EAST, cell);
/*  88 */       placeBlock(world, x + 12, y + 10, z + g, 11, EnumFacing.EAST, cell);
/*     */     }
/*     */     
/*  91 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */   }
/*     */   
/*     */   static void generateLowerLeft(World world, Random random, int cx, int cz, int y, Cell cell) {
/*  95 */     int x = cx * 16;
/*  96 */     int z = cz * 16;
/*     */     
/*  98 */     for (int a = 2; a <= 15; a++) for (int b = 0; b <= 14; b++) { for (int c = 1; c < 12; c++) {
/*  99 */           placeBlock(world, x + a, y + c, z + b, 9, cell);
/*     */         }
/*     */       }
/* 102 */     for (int a = 2; a <= 15; a++) for (int b = 0; b <= 14; b++) for (int c = 1; c < 12; c++) {
/* 103 */           if (((a == 2) || (b == 14)) && 
/* 104 */             ((a != 2) || (b <= 4) || (b >= 12) || (!cell.west) || (c >= 10)) && (
/* 105 */             (b != 14) || (a <= 4) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 106 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/* 109 */     for (int a = 3; a <= 15; a++) for (int b = 0; b <= 13; b++) { for (int c = 2; c < 11; c++) {
/* 110 */           if ((a == 3) || (b == 13)) {
/* 111 */             placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */           }
/*     */         }
/*     */       }
/* 115 */     for (int a = 2; a <= 15; a++) { for (int b = 0; b <= 14; b++)
/*     */       {
/* 117 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 118 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/*     */         
/* 120 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/* 121 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */       }
/*     */     }
/* 124 */     for (int g = 4; g <= 15; g++) {
/* 125 */       placeBlock(world, x + g, y + 2, z + 12, 10, EnumFacing.SOUTH, cell);
/* 126 */       placeBlock(world, x + g, y + 10, z + 12, 11, EnumFacing.SOUTH, cell);
/*     */     }
/* 128 */     for (int g = 0; g <= 11; g++) {
/* 129 */       placeBlock(world, x + 4, y + 2, z + g, 10, EnumFacing.WEST, cell);
/* 130 */       placeBlock(world, x + 4, y + 10, z + g, 11, EnumFacing.WEST, cell);
/*     */     }
/*     */     
/* 133 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */   }
/*     */   
/*     */   static void generateLowerRight(World world, Random random, int cx, int cz, int y, Cell cell) {
/* 137 */     int x = cx * 16;
/* 138 */     int z = cz * 16;
/*     */     
/* 140 */     for (int a = 0; a <= 14; a++) for (int b = 0; b <= 14; b++) { for (int c = 1; c < 12; c++) {
/* 141 */           placeBlock(world, x + a, y + c, z + b, 9, cell);
/*     */         }
/*     */       }
/* 144 */     for (int a = 0; a <= 14; a++) for (int b = 0; b <= 14; b++) for (int c = 1; c < 12; c++) {
/* 145 */           if (((a == 14) || (b == 14)) && 
/* 146 */             ((a != 14) || (b <= 4) || (b >= 12) || (!cell.east) || (c >= 10)) && (
/* 147 */             (b != 14) || (a <= 4) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 148 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/* 151 */     for (int a = 0; a <= 13; a++) for (int b = 0; b <= 13; b++) { for (int c = 2; c < 11; c++) {
/* 152 */           if ((a == 13) || (b == 13)) {
/* 153 */             placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */           }
/*     */         }
/*     */       }
/* 157 */     for (int a = 0; a <= 14; a++) { for (int b = 0; b <= 14; b++)
/*     */       {
/* 159 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 160 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/*     */         
/* 162 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/* 163 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */       }
/*     */     }
/* 166 */     for (int g = 0; g <= 11; g++) {
/* 167 */       placeBlock(world, x + g, y + 2, z + 12, 10, EnumFacing.SOUTH, cell);
/* 168 */       placeBlock(world, x + g, y + 10, z + 12, 11, EnumFacing.SOUTH, cell);
/*     */     }
/* 170 */     for (int g = 0; g <= 12; g++) {
/* 171 */       placeBlock(world, x + 12, y + 2, z + g, 10, EnumFacing.EAST, cell);
/* 172 */       placeBlock(world, x + 12, y + 10, z + g, 11, EnumFacing.EAST, cell);
/*     */     }
/*     */     
/* 175 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/Gen2x2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */