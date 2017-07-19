/*    */ package thaumcraft.common.blocks.world.plants;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class BlockLeavesTCItem extends ItemBlock
/*    */ {
/*    */   private final BlockLeavesTC leaves;
/*    */   
/*    */   public BlockLeavesTCItem(Block block)
/*    */   {
/* 14 */     super(block);
/* 15 */     this.leaves = ((BlockLeavesTC)block);
/* 16 */     func_77656_e(0);
/* 17 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */   public int func_77647_b(int damage)
/*    */   {
/* 22 */     return damage | 0x4;
/*    */   }
/*    */   
/*    */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */   public int func_82790_a(ItemStack stack, int renderPass)
/*    */   {
/* 28 */     return this.leaves.func_180644_h(this.leaves.func_176203_a(stack.func_77960_j()));
/*    */   }
/*    */   
/*    */   public String func_77667_c(ItemStack stack)
/*    */   {
/* 33 */     return super.func_77658_a() + "." + this.leaves.getWoodTCType(stack.func_77960_j()).func_176610_l();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/plants/BlockLeavesTCItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */