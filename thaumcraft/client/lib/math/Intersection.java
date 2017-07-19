/*     */ package thaumcraft.client.lib.math;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Intersection
/*     */ {
/*     */   protected Vec4 intersectionPoint;
/*     */   
/*     */ 
/*     */   protected Double intersectionLength;
/*     */   
/*     */ 
/*     */   protected boolean isTangent;
/*     */   
/*     */ 
/*     */   protected Object object;
/*     */   
/*     */ 
/*     */   public Intersection(Vec4 intersectionPoint, boolean isTangent)
/*     */   {
/*  21 */     if (intersectionPoint == null)
/*     */     {
/*  23 */       throw new IllegalArgumentException();
/*     */     }
/*  25 */     this.intersectionPoint = intersectionPoint;
/*  26 */     this.isTangent = isTangent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Intersection(Vec4 intersectionPoint, double intersectionLength, boolean isTangent)
/*     */   {
/*  41 */     this(intersectionPoint, isTangent);
/*     */     
/*  43 */     this.intersectionLength = Double.valueOf(intersectionLength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getObject()
/*     */   {
/*  86 */     return this.object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setObject(Object object)
/*     */   {
/*  96 */     this.object = object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vec4 getIntersectionPoint()
/*     */   {
/* 106 */     return this.intersectionPoint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIntersectionPoint(Vec4 intersectionPoint)
/*     */   {
/* 116 */     this.intersectionPoint = intersectionPoint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isTangent()
/*     */   {
/* 126 */     return this.isTangent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTangent(boolean tangent)
/*     */   {
/* 136 */     this.isTangent = tangent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Double getIntersectionLength()
/*     */   {
/* 147 */     return this.intersectionLength;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 198 */     if (this == o)
/* 199 */       return true;
/* 200 */     if ((o == null) || (getClass() != o.getClass())) {
/* 201 */       return false;
/*     */     }
/* 203 */     Intersection that = (Intersection)o;
/*     */     
/* 205 */     if (this.isTangent != that.isTangent) {
/* 206 */       return false;
/*     */     }
/* 208 */     if (!this.intersectionPoint.equals(that.intersectionPoint)) {
/* 209 */       return false;
/*     */     }
/* 211 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 218 */     int result = this.intersectionPoint.hashCode();
/* 219 */     result = 29 * result + (this.isTangent ? 1 : 0);
/* 220 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 226 */     String pt = "Intersection Point: " + this.intersectionPoint;
/* 227 */     String tang = this.isTangent ? " is a tangent." : " not a tangent";
/* 228 */     return pt + tang;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/math/Intersection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */