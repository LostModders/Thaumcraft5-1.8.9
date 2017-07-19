/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.lib.utils.InventoryUtils;
/*    */ import thaumcraft.common.tiles.devices.TileRechargePedestal;
/*    */ 
/*    */ public class BlockRechargePedestal extends BlockTCDevice
/*    */ {
/*    */   public BlockRechargePedestal()
/*    */   {
/* 20 */     super(net.minecraft.block.material.Material.field_151576_e, TileRechargePedestal.class);
/* 21 */     func_149672_a(Block.field_149769_e);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 27 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 42 */     if (world.field_72995_K) { return true;
/*    */     }
/* 44 */     net.minecraft.tileentity.TileEntity tile = world.func_175625_s(pos);
/*    */     
/* 46 */     if ((tile != null) && ((tile instanceof TileRechargePedestal)))
/*    */     {
/* 48 */       TileRechargePedestal ped = (TileRechargePedestal)tile;
/* 49 */       if ((ped.func_70301_a(0) == null) && (player.field_71071_by.func_70448_g() != null) && ((player.field_71071_by.func_70448_g().func_77973_b() instanceof thaumcraft.api.items.IRechargable)))
/*    */       {
/*    */ 
/* 52 */         ItemStack i = player.func_71045_bC().func_77946_l();
/* 53 */         i.field_77994_a = 1;
/* 54 */         ped.func_70299_a(0, i);
/* 55 */         player.func_71045_bC().field_77994_a -= 1;
/* 56 */         if (player.func_71045_bC().field_77994_a == 0) {
/* 57 */           player.func_70062_b(0, null);
/*    */         }
/* 59 */         player.field_71071_by.func_70296_d();
/* 60 */         world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "random.pop", 0.2F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.6F);
/*    */         
/*    */ 
/* 63 */         return true; }
/* 64 */       if (ped.func_70301_a(0) != null) {
/* 65 */         InventoryUtils.dropItemsAtEntity(world, pos, player);
/* 66 */         world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "random.pop", 0.2F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.5F);
/*    */         
/*    */ 
/* 69 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 73 */     return super.func_180639_a(world, pos, state, player, side, hitX, hitY, hitZ);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockRechargePedestal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */