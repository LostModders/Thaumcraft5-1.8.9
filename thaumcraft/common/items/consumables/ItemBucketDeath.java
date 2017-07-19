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
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemBucketDeath extends net.minecraft.item.Item
/*    */ {
/*    */   public ItemBucketDeath()
/*    */   {
/* 20 */     func_77637_a(Thaumcraft.tabTC);
/* 21 */     func_77627_a(false);
/* 22 */     func_77625_d(1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ItemStack func_77659_a(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
/*    */   {
/* 29 */     boolean flag = false;
/* 30 */     MovingObjectPosition movingobjectposition = func_77621_a(worldIn, playerIn, flag);
/*    */     
/* 32 */     if (movingobjectposition == null)
/*    */     {
/* 34 */       return itemStackIn;
/*    */     }
/*    */     
/*    */ 
/* 38 */     ItemStack ret = ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, movingobjectposition);
/* 39 */     if (ret != null) { return ret;
/*    */     }
/* 41 */     if (movingobjectposition.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK)
/*    */     {
/* 43 */       BlockPos blockpos = movingobjectposition.func_178782_a();
/*    */       
/* 45 */       if (!worldIn.func_175660_a(playerIn, blockpos))
/*    */       {
/* 47 */         return itemStackIn;
/*    */       }
/*    */       
/*    */ 
/* 51 */       BlockPos blockpos1 = blockpos.func_177972_a(movingobjectposition.field_178784_b);
/*    */       
/* 53 */       if (!playerIn.func_175151_a(blockpos1, movingobjectposition.field_178784_b, itemStackIn))
/*    */       {
/* 55 */         return itemStackIn;
/*    */       }
/*    */       
/* 58 */       if ((tryPlaceContainedLiquid(worldIn, blockpos1)) && (!playerIn.field_71075_bZ.field_75098_d))
/*    */       {
/* 60 */         playerIn.func_71029_a(net.minecraft.stats.StatList.field_75929_E[net.minecraft.item.Item.func_150891_b(this)]);
/* 61 */         return new ItemStack(net.minecraft.init.Items.field_151133_ar);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 66 */     return itemStackIn;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean tryPlaceContainedLiquid(World world, BlockPos pos)
/*    */   {
/* 76 */     Material material = world.func_180495_p(pos).func_177230_c().func_149688_o();
/* 77 */     boolean flag = !material.func_76220_a();
/*    */     
/* 79 */     if ((!world.func_175623_d(pos)) && (!flag)) {
/* 80 */       return false;
/*    */     }
/* 82 */     if ((!world.field_72995_K) && (flag) && (!material.func_76224_d()))
/*    */     {
/* 84 */       world.func_175655_b(pos, true);
/*    */     }
/*    */     
/* 87 */     world.func_175656_a(pos, BlocksTC.liquidDeath.func_176223_P());
/*    */     
/* 89 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemBucketDeath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */