/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.IEntityLivingData;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.EnumDifficulty;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.blocks.devices.BlockPedestal;
/*    */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*    */ import thaumcraft.common.lib.utils.EntityUtils;
/*    */ import thaumcraft.common.tiles.crafting.TilePedestal;
/*    */ 
/*    */ public class GenKeyRoom extends GenCommon
/*    */ {
/*    */   static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell)
/*    */   {
/* 24 */     int x = cx * 16;
/* 25 */     int z = cz * 16;
/*    */     
/* 27 */     GenCommon.generateChamberA(world, random, cx, cz, y, cell);
/*    */     
/* 29 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*    */     
/* 31 */     world.func_175656_a(new BlockPos(x + 8, y + 2, z + 8), BlocksTC.pedestal.func_176223_P().func_177226_a(BlockPedestal.VARIANT, thaumcraft.common.blocks.devices.BlockPedestal.PedestalType.ANCIENT));
/* 32 */     TileEntity te = world.func_175625_s(new BlockPos(x + 8, y + 2, z + 8));
/* 33 */     if ((te != null) && ((te instanceof TilePedestal))) {
/* 34 */       ((TilePedestal)te).func_70299_a(0, new ItemStack(ItemsTC.runedTablet));
/*    */     }
/*    */     
/* 37 */     int zz = 2 + (world.func_175659_aa() == EnumDifficulty.NORMAL ? 1 : world.func_175659_aa() == EnumDifficulty.HARD ? 2 : 0);
/* 38 */     for (int qq = 0; qq < zz; qq++)
/*    */     {
/* 40 */       EntityEldritchGuardian eg = new EntityEldritchGuardian(world);
/* 41 */       double i1 = x + 8.5D + MathHelper.func_76136_a(world.field_73012_v, 1, 3) * MathHelper.func_76136_a(world.field_73012_v, -1, 1);
/* 42 */       double j1 = y + 3;
/* 43 */       double k1 = z + 8.5D + MathHelper.func_76136_a(world.field_73012_v, 1, 3) * MathHelper.func_76136_a(world.field_73012_v, -1, 1);
/*    */       
/* 45 */       eg.func_70107_b(i1, j1, k1);
/* 46 */       eg.func_180482_a(world.func_175649_E(new BlockPos(eg)), (IEntityLivingData)null);
/* 47 */       eg.func_175449_a(new BlockPos(x + 8, y + 2, z + 8), 16);
/* 48 */       world.func_72838_d(eg);
/* 49 */       if ((qq == 0) || (qq == 3)) EntityUtils.makeChampion(eg, true);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/dim/GenKeyRoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */