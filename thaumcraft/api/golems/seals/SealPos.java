/*    */ package thaumcraft.api.golems.seals;
/*    */ 
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class SealPos
/*    */ {
/*    */   public BlockPos pos;
/*    */   public net.minecraft.util.EnumFacing face;
/*    */   
/*    */   public SealPos(BlockPos pos, net.minecraft.util.EnumFacing face)
/*    */   {
/* 12 */     this.pos = pos;
/* 13 */     this.face = face;
/*    */   }
/*    */   
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 19 */     byte b0 = (byte)(this.face.ordinal() + 1);
/* 20 */     int i = 31 * b0 + this.pos.func_177958_n();
/* 21 */     i = 31 * i + this.pos.func_177956_o();
/* 22 */     i = 31 * i + this.pos.func_177952_p();
/* 23 */     return i;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean equals(Object p_equals_1_)
/*    */   {
/* 29 */     if (this == p_equals_1_)
/*    */     {
/* 31 */       return true;
/*    */     }
/* 33 */     if (!(p_equals_1_ instanceof SealPos))
/*    */     {
/* 35 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 39 */     SealPos sp = (SealPos)p_equals_1_;
/* 40 */     return !this.pos.equals(sp.pos) ? false : this.face.equals(sp.face);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/seals/SealPos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */