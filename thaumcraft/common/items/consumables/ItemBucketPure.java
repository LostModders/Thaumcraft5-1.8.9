/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.ForgeEventFactory;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemBucketPure extends net.minecraft.item.Item
/*    */ {
/*    */   public ItemBucketPure()
/*    */   {
/* 19 */     func_77637_a(Thaumcraft.tabTC);
/* 20 */     func_77627_a(false);
/* 21 */     func_77625_d(1);
/*    */   }
/*    */   
/*    */ 
/*    */   public ItemStack func_77659_a(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
/*    */   {
/* 27 */     boolean flag = false;
/* 28 */     MovingObjectPosition movingobjectposition = func_77621_a(worldIn, playerIn, flag);
/*    */     
/* 30 */     if (movingobjectposition == null)
/*    */     {
/* 32 */       return itemStackIn;
/*    */     }
/*    */     
/*    */ 
/* 36 */     ItemStack ret = ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, movingobjectposition);
/* 37 */     if (ret != null) { return ret;
/*    */     }
/* 39 */     if (movingobjectposition.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK)
/*    */     {
/* 41 */       BlockPos blockpos = movingobjectposition.func_178782_a();
/*    */       
/* 43 */       if (!worldIn.func_175660_a(playerIn, blockpos))
/*    */       {
/* 45 */         return itemStackIn;
/*    */       }
/*    */       
/*    */ 
/* 49 */       BlockPos blockpos1 = blockpos.func_177972_a(movingobjectposition.field_178784_b);
/*    */       
/* 51 */       if (!playerIn.func_175151_a(blockpos1, movingobjectposition.field_178784_b, itemStackIn))
/*    */       {
/* 53 */         return itemStackIn;
/*    */       }
/*    */       
/* 56 */       if ((tryPlaceContainedLiquid(worldIn, blockpos1)) && (!playerIn.field_71075_bZ.field_75098_d))
/*    */       {
/* 58 */         playerIn.func_71029_a(net.minecraft.stats.StatList.field_75929_E[net.minecraft.item.Item.func_150891_b(this)]);
/* 59 */         return new ItemStack(net.minecraft.init.Items.field_151133_ar);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 64 */     return itemStackIn;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean tryPlaceContainedLiquid(World world, BlockPos pos)
/*    */   {
/* 74 */     Material material = world.func_180495_p(pos).func_177230_c().func_149688_o();
/* 75 */     boolean flag = !material.func_76220_a();
/*    */     
/* 77 */     if ((!world.func_175623_d(pos)) && (!flag)) {
/* 78 */       return false;
/*    */     }
/* 80 */     if ((!world.field_72995_K) && (flag) && (!material.func_76224_d()))
/*    */     {
/* 82 */       world.func_175655_b(pos, true);
/*    */     }
/*    */     
/* 85 */     world.func_175656_a(pos, thaumcraft.api.blocks.BlocksTC.purifyingFluid.func_176223_P());
/*    */     
/* 87 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemBucketPure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */