/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class Translation extends Transformation
/*    */ {
/*    */   public Vector3 vec;
/*    */   
/*    */   public Translation(Vector3 vec)
/*    */   {
/* 15 */     this.vec = vec;
/*    */   }
/*    */   
/*    */   public Translation(double x, double y, double z) {
/* 19 */     this(new Vector3(x, y, z));
/*    */   }
/*    */   
/*    */   public void apply(Vector3 vec)
/*    */   {
/* 24 */     vec.add(this.vec);
/*    */   }
/*    */   
/*    */ 
/*    */   public void applyN(Vector3 normal) {}
/*    */   
/*    */ 
/*    */   public void apply(Matrix4 mat)
/*    */   {
/* 33 */     mat.translate(this.vec);
/*    */   }
/*    */   
/*    */   public Transformation at(Vector3 point)
/*    */   {
/* 38 */     return this;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void glApply()
/*    */   {
/* 44 */     net.minecraft.client.renderer.GlStateManager.func_179137_b(this.vec.x, this.vec.y, this.vec.z);
/*    */   }
/*    */   
/*    */   public Transformation inverse()
/*    */   {
/* 49 */     return new Translation(-this.vec.x, -this.vec.y, -this.vec.z);
/*    */   }
/*    */   
/*    */   public Transformation merge(Transformation next)
/*    */   {
/* 54 */     if ((next instanceof Translation)) {
/* 55 */       return new Translation(this.vec.copy().add(((Translation)next).vec));
/*    */     }
/* 57 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isRedundant()
/*    */   {
/* 62 */     return this.vec.equalsT(Vector3.zero);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 67 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 68 */     return "Translation(" + new BigDecimal(this.vec.x, cont) + ", " + new BigDecimal(this.vec.y, cont) + ", " + new BigDecimal(this.vec.z, cont) + ")";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/Translation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */