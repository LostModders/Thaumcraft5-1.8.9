/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemBlockTC
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemBlockTC(Block block)
/*    */   {
/* 12 */     super(block);
/*    */     
/* 14 */     func_77656_e(0);
/* 15 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77647_b(int metadata)
/*    */   {
/* 21 */     return metadata;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack stack)
/*    */   {
/* 27 */     BlockTC block = (BlockTC)this.field_150939_a;
/*    */     
/* 29 */     if (block.hasProperties())
/*    */     {
/* 31 */       return super.func_77658_a() + "." + block.getStateName(block.func_176203_a(stack.func_77960_j()), false);
/*    */     }
/*    */     
/* 34 */     return super.func_77658_a();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/ItemBlockTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */