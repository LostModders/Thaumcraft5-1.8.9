/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ public class BlockTable extends BlockTC
/*    */ {
/*    */   public BlockTable(Material mat)
/*    */   {
/* 23 */     super(mat);
/*    */   }
/*    */   
/*    */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side)
/*    */   {
/* 28 */     if (side == EnumFacing.UP) return true;
/* 29 */     return super.isSideSolid(world, pos, side);
/*    */   }
/*    */   
/*    */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*    */   {
/* 34 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 40 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 46 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 54 */     if (world.field_72995_K) { return true;
/*    */     }
/* 56 */     if ((player.field_71071_by.func_70448_g() != null) && (this == BlocksTC.tableWood) && ((player.field_71071_by.func_70448_g().func_77973_b() instanceof thaumcraft.api.items.IScribeTools)))
/*    */     {
/* 58 */       IBlockState bs = BlocksTC.researchTable.func_176223_P();
/* 59 */       bs = bs.func_177226_a(thaumcraft.common.blocks.IBlockFacing.FACING, player.func_174811_aO());
/* 60 */       world.func_175656_a(pos, bs);
/* 61 */       TileResearchTable tile = (TileResearchTable)world.func_175625_s(pos);
/* 62 */       tile.func_70299_a(0, player.field_71071_by.func_70448_g().func_77946_l());
/* 63 */       player.field_71071_by.func_70448_g().field_77994_a -= 1;
/* 64 */       player.func_70062_b(0, null);
/* 65 */       player.field_71071_by.func_70296_d();
/* 66 */       tile.func_70296_d();
/* 67 */       world.func_175689_h(pos);
/*    */     }
/*    */     
/* 70 */     if ((player.field_71071_by.func_70448_g() != null) && (this == BlocksTC.tableWood) && ((player.field_71071_by.func_70448_g().func_77973_b() instanceof IWand)) && (!((IWand)player.field_71071_by.func_70448_g().func_77973_b()).isStaff(player.field_71071_by.func_70448_g())))
/*    */     {
/*    */ 
/* 73 */       world.func_175656_a(pos, BlocksTC.arcaneWorkbench.func_176223_P());
/* 74 */       TileArcaneWorkbench tile = (TileArcaneWorkbench)world.func_175625_s(pos);
/* 75 */       tile.inventory.setInventorySlotContentsSoftly(10, player.field_71071_by.func_70448_g().func_77946_l());
/* 76 */       player.field_71071_by.func_70448_g().field_77994_a -= 1;
/* 77 */       player.func_70062_b(0, null);
/* 78 */       player.field_71071_by.func_70296_d();
/* 79 */       tile.func_70296_d();
/* 80 */       world.func_175689_h(pos);
/*    */     }
/*    */     
/* 83 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */