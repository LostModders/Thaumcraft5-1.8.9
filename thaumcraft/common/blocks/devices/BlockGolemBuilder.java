/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockCauldron;
/*    */ import net.minecraft.block.BlockPistonBase;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*    */ import thaumcraft.common.blocks.misc.BlockPlaceholder;
/*    */ import thaumcraft.common.blocks.misc.BlockPlaceholder.PlaceholderType;
/*    */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*    */ 
/*    */ public class BlockGolemBuilder
/*    */   extends BlockTCDevice implements IBlockFacingHorizontal
/*    */ {
/*    */   public BlockGolemBuilder()
/*    */   {
/* 28 */     super(Material.field_151576_e, TileGolemBuilder.class);
/* 29 */     func_149672_a(Block.field_149769_e);
/* 30 */     func_149647_a(null);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149686_d()
/*    */   {
/* 42 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_149645_b()
/*    */   {
/* 48 */     return -1;
/*    */   }
/*    */   
/*    */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*    */   {
/* 53 */     return Item.func_150898_a(Blocks.field_150331_J);
/*    */   }
/*    */   
/*    */   public int func_180651_a(IBlockState state)
/*    */   {
/* 58 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*    */   {
/* 64 */     destroy(worldIn, pos, state, pos);
/* 65 */     super.func_180663_b(worldIn, pos, state);
/*    */   }
/*    */   
/* 68 */   public static boolean ignore = false;
/*    */   
/* 70 */   public static void destroy(World worldIn, BlockPos pos, IBlockState state, BlockPos startpos) { if ((ignore) || (worldIn.field_72995_K)) return;
/* 71 */     ignore = true;
/* 72 */     for (int a = -1; a <= 1; a++) {
/* 73 */       for (int b = 0; b <= 1; b++)
/* 74 */         for (int c = -1; c <= 1; c++)
/* 75 */           if (pos.func_177982_a(a, b, c) != startpos) {
/* 76 */             IBlockState bs = worldIn.func_180495_p(pos.func_177982_a(a, b, c));
/* 77 */             if ((bs.func_177230_c() == BlocksTC.placeholder) && (bs.func_177229_b(BlockPlaceholder.VARIANT) == BlockPlaceholder.PlaceholderType.GB_BARS)) {
/* 78 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150411_aY.func_176223_P());
/*    */             }
/* 80 */             if ((bs.func_177230_c() == BlocksTC.placeholder) && (bs.func_177229_b(BlockPlaceholder.VARIANT) == BlockPlaceholder.PlaceholderType.GB_ANVIL)) {
/* 81 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150467_bQ.func_176223_P());
/*    */             }
/* 83 */             if ((bs.func_177230_c() == BlocksTC.placeholder) && (bs.func_177229_b(BlockPlaceholder.VARIANT) == BlockPlaceholder.PlaceholderType.GB_CAULDRON)) {
/* 84 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150383_bp.func_176223_P());
/*    */             }
/* 86 */             if ((bs.func_177230_c() == BlocksTC.placeholder) && (bs.func_177229_b(BlockPlaceholder.VARIANT) == BlockPlaceholder.PlaceholderType.GB_TABLE))
/* 87 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), BlocksTC.tableStone.func_176223_P());
/*    */           }
/*    */     }
/* 90 */     if (pos != startpos) worldIn.func_175656_a(pos, Blocks.field_150331_J.func_176223_P().func_177226_a(BlockPistonBase.field_176321_a, EnumFacing.UP));
/* 91 */     ignore = false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 97 */     if (world.field_72995_K) return true;
/* 98 */     player.openGui(Thaumcraft.instance, 19, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 99 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockGolemBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */