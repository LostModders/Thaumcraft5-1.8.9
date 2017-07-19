/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ 
/*    */ public class GenLibraryRoom
/*    */   extends GenCommon
/*    */ {
/*    */   static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell)
/*    */   {
/* 14 */     int x = cx * 16;
/* 15 */     int z = cz * 16;
/*    */     
/* 17 */     GenCommon.generateChamberB(world, random, cx, cz, y, cell);
/*    */     
/* 19 */     world.func_175656_a(new BlockPos(x + 5, y + 4, z + 5), BlocksTC.stone.func_176203_a(11));
/* 20 */     world.func_175656_a(new BlockPos(x + 5, y + 5, z + 5), BlocksTC.slabStone.func_176203_a(2));
/* 21 */     world.func_175656_a(new BlockPos(x + 5, y + 4, z + 11), BlocksTC.stone.func_176203_a(11));
/* 22 */     world.func_175656_a(new BlockPos(x + 5, y + 5, z + 11), BlocksTC.slabStone.func_176203_a(2));
/* 23 */     world.func_175656_a(new BlockPos(x + 11, y + 4, z + 5), BlocksTC.stone.func_176203_a(11));
/* 24 */     world.func_175656_a(new BlockPos(x + 11, y + 5, z + 5), BlocksTC.slabStone.func_176203_a(2));
/* 25 */     world.func_175656_a(new BlockPos(x + 11, y + 4, z + 11), BlocksTC.stone.func_176203_a(11));
/* 26 */     world.func_175656_a(new BlockPos(x + 11, y + 5, z + 11), BlocksTC.slabStone.func_176203_a(2));
/*    */     
/* 28 */     world.func_175656_a(new BlockPos(x + 5, y + 7, z + 5), BlocksTC.stone.func_176203_a(11));
/* 29 */     world.func_175656_a(new BlockPos(x + 5, y + 6, z + 5), BlocksTC.slabStone.func_176203_a(10));
/* 30 */     world.func_175656_a(new BlockPos(x + 5, y + 7, z + 11), BlocksTC.stone.func_176203_a(11));
/* 31 */     world.func_175656_a(new BlockPos(x + 5, y + 6, z + 11), BlocksTC.slabStone.func_176203_a(10));
/* 32 */     world.func_175656_a(new BlockPos(x + 11, y + 7, z + 5), BlocksTC.stone.func_176203_a(11));
/* 33 */     world.func_175656_a(new BlockPos(x + 11, y + 6, z + 5), BlocksTC.slabStone.func_176203_a(10));
/* 34 */     world.func_175656_a(new BlockPos(x + 11, y + 7, z + 11), BlocksTC.stone.func_176203_a(11));
/* 35 */     world.func_175656_a(new BlockPos(x + 11, y + 6, z + 11), BlocksTC.slabStone.func_176203_a(10));
/*    */     
/* 37 */     world.func_175656_a(new BlockPos(x + 8, y + 2, z + 8), BlocksTC.stone.func_176203_a(5));
/* 38 */     world.func_175656_a(new BlockPos(x + 8, y + 3, z + 8), BlocksTC.stone.func_176203_a(11));
/* 39 */     world.func_175656_a(new BlockPos(x + 8, y + 4, z + 8), BlocksTC.slabStone.func_176203_a(2));
/*    */     
/* 41 */     world.func_175656_a(new BlockPos(x + 8, y + 9, z + 8), BlocksTC.stone.func_176203_a(5));
/* 42 */     world.func_175656_a(new BlockPos(x + 8, y + 8, z + 8), BlocksTC.stone.func_176203_a(11));
/* 43 */     world.func_175656_a(new BlockPos(x + 8, y + 7, z + 8), BlocksTC.slabStone.func_176203_a(10));
/*    */     
/* 45 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/GenLibraryRoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */