/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.container.InventoryArcaneWorkbench;
/*    */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*    */ 
/*    */ public class BlockArcaneWorkbench extends BlockTCDevice
/*    */ {
/*    */   public BlockArcaneWorkbench()
/*    */   {
/* 22 */     super(net.minecraft.block.material.Material.field_151575_d, TileArcaneWorkbench.class);
/* 23 */     func_149672_a(Block.field_149766_f);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 29 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 41 */     if (world.field_72995_K) return true;
/* 42 */     player.openGui(Thaumcraft.instance, 13, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 43 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_180663_b(World world, BlockPos pos, IBlockState state)
/*    */   {
/* 49 */     TileEntity tileEntity = world.func_175625_s(pos);
/* 50 */     if ((tileEntity != null) && ((tileEntity instanceof TileArcaneWorkbench))) {
/* 51 */       InventoryArcaneWorkbench inventory = ((TileArcaneWorkbench)tileEntity).inventory;
/*    */       
/* 53 */       for (int i = 0; i < 11; i++)
/* 54 */         if (i != 9) {
/* 55 */           ItemStack item = inventory.func_70301_a(i);
/* 56 */           if ((item != null) && (item.field_77994_a > 0)) {
/* 57 */             float rx = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 58 */             float ry = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 59 */             float rz = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/*    */             
/* 61 */             EntityItem entityItem = new EntityItem(world, pos.func_177958_n() + rx, pos.func_177956_o() + ry, pos.func_177952_p() + rz, item.func_77946_l());
/*    */             
/*    */ 
/*    */ 
/* 65 */             float factor = 0.05F;
/* 66 */             entityItem.field_70159_w = (world.field_73012_v.nextGaussian() * factor);
/* 67 */             entityItem.field_70181_x = (world.field_73012_v.nextGaussian() * factor + 0.20000000298023224D);
/* 68 */             entityItem.field_70179_y = (world.field_73012_v.nextGaussian() * factor);
/* 69 */             world.func_72838_d(entityItem);
/* 70 */             inventory.setInventorySlotContentsSoftly(i, null);
/*    */           }
/*    */         }
/*    */     }
/* 74 */     super.func_180663_b(world, pos, state);
/* 75 */     world.func_175713_t(pos);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockArcaneWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */