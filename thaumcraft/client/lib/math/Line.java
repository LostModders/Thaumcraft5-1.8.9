/*     */ package thaumcraft.client.lib.math;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Line
/*     */ {
/*     */   private final Vec4 origin;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final Vec4 direction;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Line fromSegment(Vec4 pa, Vec4 pb)
/*     */   {
/*  21 */     return new Line(pa, new Vec4(pb.x - pa.x, pb.y - pa.y, pb.z - pa.z, 0.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Line(Vec4 origin, Vec4 direction)
/*     */   {
/*  33 */     String message = null;
/*  34 */     if (origin == null) {
/*  35 */       message = "nullValue.OriginIsNull";
/*  36 */     } else if (direction == null) {
/*  37 */       message = "nullValue.DirectionIsNull";
/*  38 */     } else if (direction.getLength3() <= 0.0D)
/*  39 */       message = "Geom.Line.DirectionIsZeroVector";
/*  40 */     if (message != null)
/*     */     {
/*  42 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  45 */     this.origin = origin;
/*  46 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public final Vec4 getDirection()
/*     */   {
/*  51 */     return this.direction;
/*     */   }
/*     */   
/*     */   public final Vec4 getOrigin()
/*     */   {
/*  56 */     return this.origin;
/*     */   }
/*     */   
/*     */   public final Vec4 getPointAt(double t)
/*     */   {
/*  61 */     return Vec4.fromLine3(this.origin, t, this.direction);
/*     */   }
/*     */   
/*     */   public final double selfDot()
/*     */   {
/*  66 */     return this.origin.dot3(this.direction);
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
/*     */   public final boolean equals(Object o)
/*     */   {
/*  81 */     if (this == o)
/*  82 */       return true;
/*  83 */     if ((o == null) || (getClass() != o.getClass())) {
/*  84 */       return false;
/*     */     }
/*  86 */     Line line = (Line)o;
/*     */     
/*  88 */     if (!this.direction.equals(line.direction)) {
/*  89 */       return false;
/*     */     }
/*  91 */     if (!line.origin.equals(this.origin)) {
/*  92 */       return false;
/*     */     }
/*  94 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final int hashCode()
/*     */   {
/* 101 */     int result = this.origin.hashCode();
/* 102 */     result = 29 * result + this.direction.hashCode();
/* 103 */     return result;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 108 */     return "Origin: " + this.origin + ", Direction: " + this.direction;
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
/*     */   public final Vec4 nearestPointTo(Vec4 p)
/*     */   {
/* 136 */     Vec4 w = p.subtract3(this.origin);
/*     */     
/* 138 */     double c1 = w.dot3(this.direction);
/* 139 */     double c2 = this.direction.dot3(this.direction);
/*     */     
/* 141 */     return this.origin.add3(this.direction.multiply3(c1 / c2));
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
/*     */   public final double distanceTo(Vec4 p)
/*     */   {
/* 156 */     return p.distanceTo3(nearestPointTo(p));
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
/*     */   public static Vec4 nearestPointOnSegment(Vec4 p0, Vec4 p1, Vec4 p)
/*     */   {
/* 172 */     Vec4 v = p1.subtract3(p0);
/* 173 */     Vec4 w = p.subtract3(p0);
/*     */     
/* 175 */     double c1 = w.dot3(v);
/* 176 */     double c2 = v.dot3(v);
/*     */     
/* 178 */     if (c1 <= 0.0D)
/* 179 */       return p0;
/* 180 */     if (c2 <= c1) {
/* 181 */       return p1;
/*     */     }
/* 183 */     return p0.add3(v.multiply3(c1 / c2));
/*     */   }
/*     */   
/*     */   public static double distanceToSegment(Vec4 p0, Vec4 p1, Vec4 p)
/*     */   {
/* 188 */     Vec4 pb = nearestPointOnSegment(p0, p1, p);
/*     */     
/* 190 */     return p.distanceTo3(pb);
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
/*     */   public boolean isPointBehindLineOrigin(Vec4 point)
/*     */   {
/* 265 */     double dot = point.subtract3(getOrigin()).dot3(getDirection());
/* 266 */     return dot < 0.0D;
/*     */   }
/*     */   
/*     */   public Vec4 nearestIntersectionPoint(Intersection[] intersections)
/*     */   {
/* 271 */     Vec4 intersectionPoint = null;
/*     */     
/*     */ 
/* 274 */     double nearestDistance = Double.MAX_VALUE;
/* 275 */     for (Intersection intersection : intersections)
/*     */     {
/*     */ 
/* 278 */       if (!isPointBehindLineOrigin(intersection.getIntersectionPoint()))
/*     */       {
/* 280 */         double d = intersection.getIntersectionPoint().distanceTo3(getOrigin());
/* 281 */         if (d < nearestDistance)
/*     */         {
/* 283 */           intersectionPoint = intersection.getIntersectionPoint();
/* 284 */           nearestDistance = d;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 289 */     return intersectionPoint;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/math/Line.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */