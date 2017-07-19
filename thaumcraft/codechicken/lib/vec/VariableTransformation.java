/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public abstract class VariableTransformation extends Transformation
/*    */ {
/*    */   public Matrix4 mat;
/*    */   
/*    */   public VariableTransformation(Matrix4 mat)
/*    */   {
/* 12 */     this.mat = mat;
/*    */   }
/*    */   
/*    */ 
/*    */   public void applyN(Vector3 normal)
/*    */   {
/* 18 */     apply(normal);
/*    */   }
/*    */   
/*    */ 
/*    */   public void apply(Matrix4 mat)
/*    */   {
/* 24 */     mat.multiply(this.mat);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void glApply()
/*    */   {
/* 31 */     this.mat.glApply();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/VariableTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */