/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockBush;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.EnumPlantType;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.tiles.misc.TileEtherealBloom;
/*    */ 
/*    */ public class BlockEtherealBloom extends BlockBush
/*    */ {
/*    */   public BlockEtherealBloom()
/*    */   {
/* 21 */     super(Material.field_151585_k);
/* 22 */     func_149647_a(Thaumcraft.tabTC);
/* 23 */     func_149672_a(Block.field_149779_h);
/* 24 */     func_149715_a(0.8F);
/*    */   }
/*    */   
/*    */   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
/*    */   {
/* 29 */     super.func_180633_a(worldIn, pos, state, placer, stack);
/* 30 */     TileEtherealBloom tile = (TileEtherealBloom)worldIn.func_175625_s(pos);
/* 31 */     if (tile != null)
/*    */     {
/* 33 */       tile.growthCounter = 0;
/*    */     }
/*    */   }
/*    */   
/*    */   protected boolean canPlaceBlockOn(Block ground, IBlockState state)
/*    */   {
/* 39 */     return ground.func_149730_j();
/*    */   }
/*    */   
/*    */   public boolean hasTileEntity(IBlockState state)
/*    */   {
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public TileEntity createTileEntity(World world, IBlockState state)
/*    */   {
/* 49 */     return new TileEtherealBloom();
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
/*    */   {
/* 55 */     return EnumPlantType.Cave;
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_149645_b()
/*    */   {
/* 61 */     return -1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockEtherealBloom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */