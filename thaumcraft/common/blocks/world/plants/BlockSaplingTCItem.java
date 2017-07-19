/*    */ package thaumcraft.common.blocks.world.plants;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ 
/*    */ public class BlockSaplingTCItem extends ItemBlock
/*    */ {
/*    */   public BlockSaplingTCItem(Block par1)
/*    */   {
/* 10 */     super(par1);
/* 11 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77647_b(int metadata)
/*    */   {
/* 17 */     return metadata;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(net.minecraft.item.ItemStack stack)
/*    */   {
/* 23 */     return super.func_77658_a() + "." + ((BlockSaplingTC)this.field_150939_a).getStateName(this.field_150939_a.func_176203_a(stack.func_77960_j()));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/plants/BlockSaplingTCItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */