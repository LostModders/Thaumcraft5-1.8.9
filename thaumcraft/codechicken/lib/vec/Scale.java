/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class Scale extends Transformation
/*    */ {
/*    */   public Vector3 factor;
/*    */   
/*    */   public Scale(Vector3 factor)
/*    */   {
/* 15 */     this.factor = factor;
/*    */   }
/*    */   
/*    */   public Scale(double factor) {
/* 19 */     this(new Vector3(factor, factor, factor));
/*    */   }
/*    */   
/*    */   public Scale(double x, double y, double z) {
/* 23 */     this(new Vector3(x, y, z));
/*    */   }
/*    */   
/*    */   public void apply(Vector3 vec)
/*    */   {
/* 28 */     vec.multiply(this.factor);
/*    */   }
/*    */   
/*    */ 
/*    */   public void applyN(Vector3 normal) {}
/*    */   
/*    */ 
/*    */   public void apply(Matrix4 mat)
/*    */   {
/* 37 */     mat.scale(this.factor);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void glApply()
/*    */   {
/* 43 */     net.minecraft.client.renderer.GlStateManager.func_179139_a(this.factor.x, this.factor.y, this.factor.z);
/*    */   }
/*    */   
/*    */   public Transformation inverse()
/*    */   {
/* 48 */     return new Scale(1.0D / this.factor.x, 1.0D / this.factor.y, 1.0D / this.factor.z);
/*    */   }
/*    */   
/*    */   public Transformation merge(Transformation next)
/*    */   {
/* 53 */     if ((next instanceof Scale)) {
/* 54 */       return new Scale(this.factor.copy().multiply(((Scale)next).factor));
/*    */     }
/* 56 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isRedundant()
/*    */   {
/* 61 */     return this.factor.equalsT(Vector3.one);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 66 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 67 */     return "Scale(" + new BigDecimal(this.factor.x, cont) + ", " + new BigDecimal(this.factor.y, cont) + ", " + new BigDecimal(this.factor.z, cont) + ")";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/Scale.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */