/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ 
/*    */ public class Line3
/*    */ {
/*    */   public static final double tol = 1.0E-4D;
/*    */   
/*    */   public Vector3 pt1;
/*    */   public Vector3 pt2;
/*    */   
/*    */   public Line3(Vector3 pt1, Vector3 pt2)
/*    */   {
/* 13 */     this.pt1 = pt1;
/* 14 */     this.pt2 = pt2;
/*    */   }
/*    */   
/*    */   public Line3()
/*    */   {
/* 19 */     this(new Vector3(), new Vector3());
/*    */   }
/*    */   
/*    */ 
/*    */   public static boolean intersection2D(Line3 line1, Line3 line2, Vector3 store)
/*    */   {
/* 25 */     double xD1 = line1.pt2.x - line1.pt1.x;
/* 26 */     double zD1 = line1.pt2.z - line1.pt1.z;
/* 27 */     double xD2 = line2.pt2.x - line2.pt1.x;
/* 28 */     double zD2 = line2.pt2.z - line2.pt1.z;
/*    */     
/* 30 */     double xD3 = line1.pt1.x - line2.pt1.x;
/* 31 */     double zD3 = line1.pt1.z - line2.pt1.z;
/*    */     
/* 33 */     double div = zD2 * xD1 - xD2 * zD1;
/* 34 */     if (div == 0.0D)
/* 35 */       return false;
/* 36 */     double ua = (xD2 * zD3 - zD2 * xD3) / div;
/* 37 */     store.set(line1.pt1.x + ua * xD1, 0.0D, line1.pt1.z + ua * zD1);
/*    */     
/* 39 */     if ((store.x >= Math.min(line1.pt1.x, line1.pt2.x) - 1.0E-4D) && (store.x >= Math.min(line2.pt1.x, line2.pt2.x) - 1.0E-4D) && (store.z >= Math.min(line1.pt1.z, line1.pt2.z) - 1.0E-4D) && (store.z >= Math.min(line2.pt1.z, line2.pt2.z) - 1.0E-4D) && (store.x <= Math.max(line1.pt1.x, line1.pt2.x) + 1.0E-4D) && (store.x <= Math.max(line2.pt1.x, line2.pt2.x) + 1.0E-4D) && (store.z <= Math.max(line1.pt1.z, line1.pt2.z) + 1.0E-4D) && (store.z <= Math.max(line2.pt1.z, line2.pt2.z) + 1.0E-4D))
/*    */     {
/*    */ 
/*    */ 
/* 43 */       return true;
/*    */     }
/* 45 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/Line3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */