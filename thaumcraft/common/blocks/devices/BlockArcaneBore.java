/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockPistonBase;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockFacing;
/*    */ import thaumcraft.common.tiles.devices.TileArcaneBore;
/*    */ 
/*    */ public class BlockArcaneBore extends BlockTCDevice implements IBlockFacing
/*    */ {
/*    */   public BlockArcaneBore()
/*    */   {
/* 23 */     super(net.minecraft.block.material.Material.field_151575_d, TileArcaneBore.class);
/* 24 */     func_149672_a(Block.field_149766_f);
/*    */   }
/*    */   
/*    */   public int func_180651_a(IBlockState state)
/*    */   {
/* 29 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*    */   {
/* 35 */     IBlockState bs = func_176223_P();
/* 36 */     bs = bs.func_177226_a(IBlockFacing.FACING, BlockPistonBase.func_180695_a(worldIn, pos, placer));
/* 37 */     return bs;
/*    */   }
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 42 */     if ((!world.field_72995_K) && (!playerIn.func_70093_af()) && ((playerIn.func_70694_bm() == null) || (!(playerIn.func_70694_bm().func_77973_b() instanceof IWand))))
/*    */     {
/* 44 */       playerIn.openGui(Thaumcraft.instance, 14, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side)
/*    */   {
/* 51 */     if (side.func_176736_b() >= 0) return false;
/* 52 */     if (worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177230_c() != BlocksTC.arcaneBoreBase) return false;
/* 53 */     return super.func_176198_a(worldIn, pos, side);
/*    */   }
/*    */   
/*    */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*    */   {
/* 58 */     TileArcaneBore tile = (TileArcaneBore)worldIn.func_175625_s(pos);
/* 59 */     if (tile != null)
/*    */     {
/* 61 */       tile.refresh = true;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int func_149645_b()
/*    */   {
/* 69 */     return -1;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 75 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 81 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockArcaneBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */