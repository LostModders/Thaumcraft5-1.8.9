/*    */ package thaumcraft.client.lib.models;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class MeshPart
/*    */ {
/*    */   public String name;
/*    */   public Material material;
/*    */   public List<int[]> indices;
/* 10 */   public int tintIndex = -1;
/*    */   
/*    */   public MeshPart() {
/* 13 */     this.indices = new java.util.ArrayList();
/*    */   }
/*    */   
/*    */   public MeshPart(MeshPart p, int ti) {
/* 17 */     this.indices = new java.util.ArrayList();
/* 18 */     this.name = p.name;
/* 19 */     this.material = p.material;
/* 20 */     this.indices = p.indices;
/* 21 */     this.tintIndex = ti;
/*    */   }
/*    */   
/*    */   public void addTriangleFace(int[] a, int[] b, int[] c)
/*    */   {
/* 26 */     this.indices.add(a);
/* 27 */     this.indices.add(b);
/* 28 */     this.indices.add(c);
/* 29 */     this.indices.add(c);
/*    */   }
/*    */   
/*    */   public void addQuadFace(int[] a, int[] b, int[] c, int[] d) {
/* 33 */     this.indices.add(a);
/* 34 */     this.indices.add(b);
/* 35 */     this.indices.add(c);
/* 36 */     this.indices.add(d);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/MeshPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */