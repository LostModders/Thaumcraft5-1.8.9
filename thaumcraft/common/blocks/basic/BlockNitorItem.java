/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.blocks.ItemBlockTC;
/*    */ 
/*    */ public class BlockNitorItem
/*    */   extends ItemBlockTC
/*    */ {
/*    */   public BlockNitorItem(Block block)
/*    */   {
/* 12 */     super(block);
/*    */   }
/*    */   
/*    */   public int func_82790_a(ItemStack stack, int renderPass)
/*    */   {
/* 17 */     Block block = Block.func_149634_a(stack.func_77973_b());
/* 18 */     if ((block != null) && (renderPass == 0)) {
/* 19 */       return block.func_180644_h(block.func_176203_a(stack.func_77952_i()));
/*    */     }
/* 21 */     return super.func_82790_a(stack, renderPass);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockNitorItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */