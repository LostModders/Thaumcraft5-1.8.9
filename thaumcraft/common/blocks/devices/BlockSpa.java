/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*    */ import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import net.minecraftforge.fluids.FluidTank;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.tiles.devices.TileSpa;
/*    */ 
/*    */ public class BlockSpa extends BlockTCDevice
/*    */ {
/*    */   public BlockSpa()
/*    */   {
/* 23 */     super(net.minecraft.block.material.Material.field_151576_e, TileSpa.class);
/* 24 */     func_149672_a(net.minecraft.block.Block.field_149769_e);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 41 */     if (world.field_72995_K) return true;
/* 42 */     net.minecraft.tileentity.TileEntity tileEntity = world.func_175625_s(pos);
/* 43 */     if (((tileEntity instanceof TileSpa)) && (!player.func_70093_af())) {
/* 44 */       FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(player.field_71071_by.func_70448_g());
/* 45 */       if (fs != null) {
/* 46 */         int volume = fs.amount;
/* 47 */         TileSpa tile = (TileSpa)tileEntity;
/* 48 */         if ((tile.tank.getFluidAmount() < tile.tank.getCapacity()) && ((tile.tank.getFluid() == null) || (tile.tank.getFluid().isFluidEqual(fs))))
/*    */         {
/* 50 */           tile.fill(EnumFacing.UP, FluidContainerRegistry.getFluidForFilledItem(player.field_71071_by.func_70448_g()), true);
/* 51 */           ItemStack emptyContainer = null;
/* 52 */           FluidContainerRegistry.FluidContainerData[] fcs = FluidContainerRegistry.getRegisteredFluidContainerData();
/* 53 */           for (FluidContainerRegistry.FluidContainerData fcd : fcs) {
/* 54 */             if (fcd.filledContainer.func_77969_a(player.field_71071_by.func_70448_g())) {
/* 55 */               emptyContainer = fcd.emptyContainer.func_77946_l();
/*    */             }
/*    */           }
/* 58 */           player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
/* 59 */           if ((emptyContainer != null) && 
/* 60 */             (!player.field_71071_by.func_70441_a(emptyContainer))) {
/* 61 */             player.func_71019_a(emptyContainer, false);
/*    */           }
/*    */           
/* 64 */           player.field_71069_bz.func_75142_b();
/* 65 */           tile.func_70296_d();
/* 66 */           world.func_175689_h(pos);
/* 67 */           world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "game.neutral.swim", 0.33F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*    */         }
/*    */       }
/*    */       else {
/* 71 */         player.openGui(thaumcraft.common.Thaumcraft.instance, 6, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */       }
/*    */     }
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockSpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */