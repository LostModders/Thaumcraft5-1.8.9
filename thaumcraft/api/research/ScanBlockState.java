/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class ScanBlockState implements IScanThing
/*    */ {
/*    */   String research;
/*    */   IBlockState blockState;
/*    */   
/*    */   public ScanBlockState(String research, IBlockState blockState)
/*    */   {
/* 14 */     this.research = research;
/* 15 */     this.blockState = blockState;
/*    */   }
/*    */   
/*    */   public ScanBlockState(String research, IBlockState blockState, boolean item) {
/* 19 */     this.research = research;
/* 20 */     this.blockState = blockState;
/* 21 */     if (item) {
/* 22 */       ScanningManager.addScannableThing(new ScanItem(research, new net.minecraft.item.ItemStack(blockState.func_177230_c(), 1, blockState.func_177230_c().func_176201_c(blockState))));
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj)
/*    */   {
/* 28 */     if ((obj != null) && ((obj instanceof BlockPos)) && (player.field_70170_p.func_180495_p((BlockPos)obj) == this.blockState)) {
/* 29 */       return true;
/*    */     }
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   public String getResearchKey()
/*    */   {
/* 36 */     return this.research;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ScanBlockState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */