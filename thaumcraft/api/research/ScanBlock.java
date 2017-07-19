/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class ScanBlock implements IScanThing
/*    */ {
/*    */   String research;
/*    */   Block block;
/*    */   
/*    */   public ScanBlock(String research, Block block)
/*    */   {
/* 13 */     this.research = research;
/* 14 */     this.block = block;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean checkThing(EntityPlayer player, Object obj)
/*    */   {
/* 20 */     if ((obj != null) && ((obj instanceof net.minecraft.util.BlockPos)) && (player.field_70170_p.func_180495_p((net.minecraft.util.BlockPos)obj).func_177230_c() == this.block)) {
/* 21 */       return true;
/*    */     }
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   public String getResearchKey()
/*    */   {
/* 28 */     return this.research;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ScanBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */