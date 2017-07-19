/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ 
/*    */ public class GenNestRoom extends GenCommon
/*    */ {
/*    */   static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell)
/*    */   {
/* 12 */     int x = cx * 16;
/* 13 */     int z = cz * 16;
/*    */     
/* 15 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) { for (int c = 1; c < 10; c++) {
/* 16 */           placeBlock(world, x + a, y + c, z + b, 9, cell);
/*    */         }
/*    */       }
/* 19 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) { for (int c = 1; c < 10; c++) {
/* 20 */           if (((a == 2) || (a == 14) || (b == 2) || (b == 14)) && 
/* 21 */             ((a != 2) || (b <= 3) || (b >= 12) || (!cell.west) || (c >= 10)) && 
/* 22 */             ((a != 14) || (b <= 3) || (b >= 12) || (!cell.east) || (c >= 10)) && 
/* 23 */             ((b != 2) || (a <= 3) || (a >= 12) || (!cell.north) || (c >= 10)) && (
/* 24 */             (b != 14) || (a <= 3) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 25 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*    */           }
/*    */         }
/*    */       }
/* 29 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) { for (int c = 2; c < 9; c++) {
/* 30 */           if ((a == 3) || (a == 13) || (b == 3) || (b == 13)) {
/* 31 */             placeBlock(world, x + a, y + c, z + b, 21, cell);
/*    */           }
/* 33 */           if (((a == 4) && (!cell.west)) || ((a == 12) && (!cell.east)) || ((b == 4) && (!cell.north)) || ((b == 12) && (!cell.south) && (random.nextBoolean()))) {
/* 34 */             placeBlock(world, x + a, y + c, z + b, 21, cell);
/*    */           }
/*    */         }
/*    */       }
/* 38 */     for (int a = 2; a <= 14; a++) { for (int b = 2; b <= 14; b++)
/*    */       {
/* 40 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 41 */         placeBlock(world, x + a, y + 1, z + b, 21, cell);
/*    */         
/* 43 */         placeBlock(world, x + a, y + 10, z + b, 8, cell);
/* 44 */         placeBlock(world, x + a, y + 9, z + b, 21, cell);
/*    */         
/* 46 */         placeBlock(world, x + a, y + 8, z + b, 21, cell);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 58 */     placeBlock(world, x + 8, y + 2, z + 8, 21, cell);
/* 59 */     placeBlock(world, x + 8, y + 3, z + 8, 21, cell);
/* 60 */     placeBlock(world, x + 8, y + 4, z + 8, 21, cell);
/* 61 */     placeBlock(world, x + 7, y + 2, z + 8, 21, cell);
/* 62 */     placeBlock(world, x + 8, y + 2, z + 7, 21, cell);
/* 63 */     placeBlock(world, x + 9, y + 2, z + 8, 21, cell);
/* 64 */     placeBlock(world, x + 8, y + 2, z + 9, 21, cell);
/* 65 */     if (random.nextBoolean()) placeBlock(world, x + 7, y + 3, z + 8, 21, cell);
/* 66 */     if (random.nextBoolean()) placeBlock(world, x + 8, y + 3, z + 7, 21, cell);
/* 67 */     if (random.nextBoolean()) placeBlock(world, x + 9, y + 3, z + 8, 21, cell);
/* 68 */     if (random.nextBoolean()) { placeBlock(world, x + 8, y + 3, z + 9, 21, cell);
/*    */     }
/* 70 */     if (random.nextBoolean()) { placeBlock(world, x + 8, y + 5, z + 8, 7, cell);
/*    */     }
/* 72 */     placeBlock(world, x + 8, y + 8, z + 8, 21, cell);
/* 73 */     placeBlock(world, x + 8, y + 7, z + 8, 21, cell);
/* 74 */     placeBlock(world, x + 8, y + 6, z + 8, 21, cell);
/* 75 */     placeBlock(world, x + 7, y + 8, z + 8, 21, cell);
/* 76 */     placeBlock(world, x + 8, y + 8, z + 7, 21, cell);
/* 77 */     placeBlock(world, x + 9, y + 8, z + 8, 21, cell);
/* 78 */     placeBlock(world, x + 8, y + 8, z + 9, 21, cell);
/* 79 */     if (random.nextBoolean()) placeBlock(world, x + 7, y + 7, z + 8, 21, cell);
/* 80 */     if (random.nextBoolean()) placeBlock(world, x + 8, y + 7, z + 7, 21, cell);
/* 81 */     if (random.nextBoolean()) placeBlock(world, x + 9, y + 7, z + 8, 21, cell);
/* 82 */     if (random.nextBoolean()) { placeBlock(world, x + 8, y + 7, z + 9, 21, cell);
/*    */     }
/* 84 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*    */     
/* 86 */     for (int a = -5; a <= 5; a++) {
/* 87 */       for (int b = -5; b <= 5; b++) {
/* 88 */         if ((random.nextFloat() < 0.15F) && (world.func_175623_d(new BlockPos(x + 8 + a, y + 2, z + 8 + b)))) {
/* 89 */           float rr = random.nextFloat();
/* 90 */           int md = rr < 0.4F ? 1 : rr < 0.15F ? 2 : 0;
/* 91 */           world.func_175656_a(new BlockPos(x + 8 + a, y + 2, z + 8 + b), random.nextFloat() < 0.2F ? BlocksTC.lootCrate.func_176203_a(md) : BlocksTC.lootUrn.func_176203_a(md));
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/GenNestRoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */