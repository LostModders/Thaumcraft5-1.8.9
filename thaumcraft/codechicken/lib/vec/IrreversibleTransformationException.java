/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ public class IrreversibleTransformationException
/*    */   extends RuntimeException
/*    */ {
/*    */   public ITransformation t;
/*    */   
/*    */   public IrreversibleTransformationException(ITransformation t)
/*    */   {
/* 10 */     this.t = t;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 16 */     return "The following transformation is irreversible:\n" + this.t;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/IrreversibleTransformationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */