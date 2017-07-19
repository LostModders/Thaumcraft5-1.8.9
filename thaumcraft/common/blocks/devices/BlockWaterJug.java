/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*    */ import net.minecraftforge.fluids.FluidRegistry;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.tiles.devices.TileWaterJug;
/*    */ 
/*    */ public class BlockWaterJug extends BlockTCDevice
/*    */ {
/*    */   public BlockWaterJug()
/*    */   {
/* 25 */     super(net.minecraft.block.material.Material.field_151576_e, TileWaterJug.class);
/* 26 */     func_149672_a(Block.field_149769_e);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 32 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 38 */     return false;
/*    */   }
/*    */   
/*    */   public void func_180654_a(IBlockAccess world, BlockPos pos)
/*    */   {
/* 43 */     func_149676_a(0.1875F, 0.0F, 0.1875F, 0.8125F, 1.0F, 0.8125F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 52 */     if ((!world.field_72995_K) && 
/* 53 */       (FluidContainerRegistry.isEmptyContainer(player.field_71071_by.func_70448_g()))) {
/* 54 */       int cap = FluidContainerRegistry.getContainerCapacity(new FluidStack(FluidRegistry.WATER, 1), player.field_71071_by.func_70448_g());
/* 55 */       ItemStack res = FluidContainerRegistry.fillFluidContainer(new FluidStack(FluidRegistry.WATER, cap), player.field_71071_by.func_70448_g());
/* 56 */       if (res != null) {
/* 57 */         player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
/* 58 */         if (!player.field_71071_by.func_70441_a(res)) {
/* 59 */           player.func_71019_a(res, false);
/*    */         }
/* 61 */         player.field_71069_bz.func_75142_b();
/* 62 */         TileEntity te = world.func_175625_s(pos);
/* 63 */         if ((te != null) && ((te instanceof TileWaterJug))) {
/* 64 */           TileWaterJug tile = (TileWaterJug)te;
/* 65 */           tile.charge -= cap;
/* 66 */           te.func_70296_d();
/*    */         }
/* 68 */         world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "game.neutral.swim", 0.33F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockWaterJug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */