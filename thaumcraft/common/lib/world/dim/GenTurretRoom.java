/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.IEntityLivingData;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.construct.EntityTurretEldritch;
/*    */ 
/*    */ public class GenTurretRoom
/*    */   extends GenCommon
/*    */ {
/*    */   static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell)
/*    */   {
/* 15 */     GenPassage.generateDefaultPassage(world, random, cx, cz, y, cell);
/*    */     
/* 17 */     int x = cx * 16;
/* 18 */     int z = cz * 16;
/*    */     
/* 20 */     if (random.nextBoolean()) {
/* 21 */       placeBlock(world, x + 8, y + 2, z + 8, 15, cell);
/* 22 */       placeBlock(world, x + 8, y + 3, z + 8, 15, cell);
/* 23 */       EntityTurretEldritch turret = new EntityTurretEldritch(world, new BlockPos(x + 8, y + 4, z + 8), EnumFacing.UP);
/* 24 */       turret.func_180482_a(world.func_175649_E(new BlockPos(x + 8, y + 4, z + 8)), (IEntityLivingData)null);
/* 25 */       turret.setValidSpawn();
/* 26 */       world.func_72838_d(turret);
/* 27 */       turret.setFacing(EnumFacing.UP);
/*    */     } else {
/* 29 */       placeBlock(world, x + 8, y + 8, z + 8, 15, cell);
/* 30 */       placeBlock(world, x + 8, y + 7, z + 8, 15, cell);
/* 31 */       EntityTurretEldritch turret = new EntityTurretEldritch(world, new BlockPos(x + 8, y + 6, z + 8), EnumFacing.DOWN);
/* 32 */       turret.func_180482_a(world.func_175649_E(new BlockPos(x + 8, y + 4, z + 8)), (IEntityLivingData)null);
/* 33 */       turret.setValidSpawn();
/* 34 */       world.func_72838_d(turret);
/* 35 */       turret.setFacing(EnumFacing.DOWN);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/GenTurretRoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */