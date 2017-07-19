/*    */ package thaumcraft.common.blocks.misc;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockContainer;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.tiles.misc.TileHole;
/*    */ 
/*    */ 
/*    */ public class BlockHole
/*    */   extends BlockContainer
/*    */ {
/*    */   public BlockHole()
/*    */   {
/* 30 */     super(Material.field_151576_e);
/* 31 */     func_149722_s();
/* 32 */     func_149752_b(6000000.0F);
/* 33 */     func_149672_a(Block.field_149775_l);
/* 34 */     func_149715_a(0.7F);
/* 35 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 36 */     func_149675_a(true);
/* 37 */     func_149647_a(null);
/*    */   }
/*    */   
/*    */   public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
/*    */   {
/* 42 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {}
/*    */   
/*    */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing o) {
/* 49 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {}
/*    */   
/*    */ 
/*    */   public void func_180654_a(IBlockAccess iblockaccess, BlockPos pos)
/*    */   {
/* 59 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public AxisAlignedBB func_180646_a(World worldIn, BlockPos pos)
/*    */   {
/* 64 */     return AxisAlignedBB.func_178781_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */   
/*    */   public boolean func_149686_d() {
/* 68 */     return false;
/*    */   }
/*    */   
/* 71 */   public boolean func_149662_c() { return false; }
/*    */   
/*    */   public TileEntity func_149915_a(World var1, int var2) {
/* 74 */     return new TileHole();
/*    */   }
/*    */   
/*    */   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
/* 78 */     return Item.func_150899_d(0);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/misc/BlockHole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */