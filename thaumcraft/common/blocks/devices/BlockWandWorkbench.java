/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*    */ 
/*    */ public class BlockWandWorkbench extends BlockTCDevice
/*    */ {
/*    */   public BlockWandWorkbench()
/*    */   {
/* 18 */     super(Material.field_151576_e, TileFocalManipulator.class);
/* 19 */     func_149672_a(Block.field_149769_e);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 25 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 31 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 37 */     if (world.field_72995_K) return true;
/* 38 */     player.openGui(Thaumcraft.instance, 7, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockWandWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */