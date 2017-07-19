/*     */ package thaumcraft.client.lib.math;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cylinder
/*     */ {
/*     */   protected final Vec4 bottomCenter;
/*     */   
/*     */ 
/*     */   protected final Vec4 topCenter;
/*     */   
/*     */ 
/*     */   protected final Vec4 axisUnitDirection;
/*     */   
/*     */ 
/*     */   protected final double cylinderRadius;
/*     */   
/*     */ 
/*     */   protected final double cylinderHeight;
/*     */   
/*     */ 
/*     */   public Cylinder(Vec4 bottomCenter, Vec4 topCenter, double cylinderRadius)
/*     */   {
/*  24 */     if ((bottomCenter == null) || (topCenter == null) || (bottomCenter.equals(topCenter)))
/*     */     {
/*     */ 
/*  27 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  30 */     if (cylinderRadius <= 0.0D)
/*     */     {
/*     */ 
/*  33 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  40 */     this.bottomCenter = bottomCenter.toHomogeneousPoint3();
/*  41 */     this.topCenter = topCenter.toHomogeneousPoint3();
/*  42 */     this.cylinderHeight = this.bottomCenter.distanceTo3(this.topCenter);
/*  43 */     this.cylinderRadius = cylinderRadius;
/*  44 */     this.axisUnitDirection = this.topCenter.subtract3(this.bottomCenter).normalize3();
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
/*     */   public Cylinder(Vec4 bottomCenter, Vec4 topCenter, double cylinderRadius, Vec4 unitDirection)
/*     */   {
/*  61 */     if ((bottomCenter == null) || (topCenter == null) || (bottomCenter.equals(topCenter)))
/*     */     {
/*     */ 
/*  64 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  67 */     if (cylinderRadius <= 0.0D)
/*     */     {
/*     */ 
/*  70 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  77 */     this.bottomCenter = bottomCenter.toHomogeneousPoint3();
/*  78 */     this.topCenter = topCenter.toHomogeneousPoint3();
/*  79 */     this.cylinderHeight = this.bottomCenter.distanceTo3(this.topCenter);
/*  80 */     this.cylinderRadius = cylinderRadius;
/*  81 */     this.axisUnitDirection = unitDirection;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vec4 getAxisUnitDirection()
/*     */   {
/*  91 */     return this.axisUnitDirection;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vec4 getBottomCenter()
/*     */   {
/* 101 */     return this.bottomCenter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vec4 getTopCenter()
/*     */   {
/* 111 */     return this.topCenter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getCylinderRadius()
/*     */   {
/* 121 */     return this.cylinderRadius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getCylinderHeight()
/*     */   {
/* 131 */     return this.cylinderHeight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vec4 getCenter()
/*     */   {
/* 141 */     Vec4 b = this.bottomCenter;
/* 142 */     Vec4 t = this.topCenter;
/* 143 */     return new Vec4((b.x + t.x) / 2.0D, (b.y + t.y) / 2.0D, (b.z + t.z) / 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDiameter()
/*     */   {
/* 152 */     return 2.0D * getRadius();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getRadius()
/*     */   {
/* 159 */     double halfHeight = this.bottomCenter.distanceTo3(this.topCenter) / 2.0D;
/* 160 */     return Math.sqrt(halfHeight * halfHeight + this.cylinderRadius * this.cylinderRadius);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVolume()
/*     */   {
/* 170 */     return 3.141592653589793D * this.cylinderRadius * this.cylinderRadius * this.cylinderHeight;
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
/*     */   public Intersection[] intersect(Line line)
/*     */   {
/* 266 */     if (line == null)
/*     */     {
/* 268 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 271 */     double[] tVals = new double[2];
/* 272 */     if (!intcyl(line.getOrigin(), line.getDirection(), this.bottomCenter, this.axisUnitDirection, this.cylinderRadius, tVals))
/*     */     {
/* 274 */       return null;
/*     */     }
/* 276 */     if (!clipcyl(line.getOrigin(), line.getDirection(), this.bottomCenter, this.topCenter, this.axisUnitDirection, tVals))
/*     */     {
/* 278 */       return null;
/*     */     }
/* 280 */     if ((!Double.isInfinite(tVals[0])) && (!Double.isInfinite(tVals[1])) && (tVals[0] >= 0.0D) && (tVals[1] >= 0.0D)) {
/* 281 */       return new Intersection[] { new Intersection(line.getPointAt(tVals[0]), false), new Intersection(line.getPointAt(tVals[1]), false) };
/*     */     }
/* 283 */     if ((!Double.isInfinite(tVals[0])) && (tVals[0] >= 0.0D))
/* 284 */       return new Intersection[] { new Intersection(line.getPointAt(tVals[0]), false) };
/* 285 */     if ((!Double.isInfinite(tVals[1])) && (tVals[1] >= 0.0D))
/* 286 */       return new Intersection[] { new Intersection(line.getPointAt(tVals[1]), false) };
/* 287 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean intersects(Line line)
/*     */   {
/* 293 */     if (line == null)
/*     */     {
/*     */ 
/* 296 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 299 */     return intersect(line) != null;
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
/*     */   protected boolean intcyl(Vec4 raybase, Vec4 raycos, Vec4 base, Vec4 axis, double radius, double[] tVals)
/*     */   {
/* 313 */     Vec4 RC = raybase.subtract3(base);
/* 314 */     Vec4 n = raycos.cross3(axis);
/*     */     
/*     */     double ln;
/* 317 */     if ((ln = n.getLength3()) == 0.0D)
/*     */     {
/* 319 */       double d = RC.dot3(axis);
/* 320 */       Vec4 D = RC.subtract3(axis.multiply3(d));
/* 321 */       d = D.getLength3();
/* 322 */       tVals[0] = Double.NEGATIVE_INFINITY;
/* 323 */       tVals[1] = Double.POSITIVE_INFINITY;
/*     */       
/* 325 */       return d <= radius;
/*     */     }
/*     */     
/* 328 */     n = n.normalize3();
/* 329 */     double d = Math.abs(RC.dot3(n));
/* 330 */     boolean hit = d <= radius;
/*     */     
/*     */ 
/* 333 */     if (hit)
/*     */     {
/* 335 */       Vec4 O = RC.cross3(axis);
/* 336 */       double t = -O.dot3(n) / ln;
/* 337 */       O = n.cross3(axis);
/* 338 */       O = O.normalize3();
/* 339 */       double s = Math.abs(Math.sqrt(radius * radius - d * d) / raycos.dot3(O));
/* 340 */       tVals[0] = (t - s);
/* 341 */       tVals[1] = (t + s);
/*     */     }
/*     */     
/* 344 */     return hit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean clipcyl(Vec4 raybase, Vec4 raycos, Vec4 bot, Vec4 top, Vec4 axis, double[] tVals)
/*     */   {
/* 354 */     double in = tVals[0];
/* 355 */     double out = tVals[1];
/*     */     
/* 357 */     double dc = axis.dot3(raycos);
/* 358 */     double dwb = axis.dot3(raybase) - axis.dot3(bot);
/* 359 */     double dwt = axis.dot3(raybase) - axis.dot3(top);
/*     */     
/*     */ 
/* 362 */     if (dc == 0.0D)
/*     */     {
/* 364 */       if (dwb <= 0.0D)
/* 365 */         return false;
/* 366 */       if (dwt >= 0.0D) {
/* 367 */         return false;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 372 */       double tb = -dwb / dc;
/*     */       
/* 374 */       double tt = -dwt / dc;
/*     */       
/*     */ 
/* 377 */       if (dc >= 0.0D)
/*     */       {
/* 379 */         if (tb > out)
/* 380 */           return false;
/* 381 */         if (tt < in)
/* 382 */           return false;
/* 383 */         if ((tb > in) && (tb < out))
/* 384 */           in = tb;
/* 385 */         if ((tt > in) && (tt < out)) {
/* 386 */           out = tt;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 391 */         if (tb < in)
/* 392 */           return false;
/* 393 */         if (tt > out)
/* 394 */           return false;
/* 395 */         if ((tb > in) && (tb < out))
/* 396 */           out = tb;
/* 397 */         if ((tt > in) && (tt < out)) {
/* 398 */           in = tt;
/*     */         }
/*     */       }
/*     */     }
/* 402 */     tVals[0] = in;
/* 403 */     tVals[1] = out;
/* 404 */     return in < out;
/*     */   }
/*     */   
/*     */ 
/*     */   protected double intersects(Plane plane, double effectiveRadius)
/*     */   {
/* 410 */     double dq1 = plane.dot(this.bottomCenter);
/* 411 */     boolean bq1 = dq1 <= -effectiveRadius;
/*     */     
/*     */ 
/* 414 */     double dq2 = plane.dot(this.topCenter);
/* 415 */     boolean bq2 = dq2 <= -effectiveRadius;
/*     */     
/* 417 */     if ((bq1) && (bq2)) {
/* 418 */       return -1.0D;
/*     */     }
/* 420 */     if (bq1 == bq2) {
/* 421 */       return 0.0D;
/*     */     }
/* 423 */     return 1.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   protected double intersectsAt(Plane plane, double effectiveRadius, Vec4[] endpoints)
/*     */   {
/* 429 */     double dq1 = plane.dot(endpoints[0]);
/* 430 */     boolean bq1 = dq1 <= -effectiveRadius;
/*     */     
/*     */ 
/*     */ 
/* 434 */     double dq2 = plane.dot(endpoints[1]);
/* 435 */     boolean bq2 = dq2 <= -effectiveRadius;
/*     */     
/* 437 */     if ((bq1) && (bq2)) {
/* 438 */       return -1.0D;
/*     */     }
/* 440 */     if (bq1 == bq2) {
/* 441 */       return 0.0D;
/*     */     }
/*     */     
/* 444 */     double t = (effectiveRadius + dq1) / plane.getNormal().dot3(endpoints[0].subtract3(endpoints[1]));
/*     */     
/* 446 */     Vec4 newEndPoint = endpoints[0].add3(endpoints[1].subtract3(endpoints[0]).multiply3(t));
/* 447 */     if (bq1) {
/* 448 */       endpoints[0] = newEndPoint;
/*     */     } else {
/* 450 */       endpoints[1] = newEndPoint;
/*     */     }
/* 452 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getEffectiveRadius(Plane plane)
/*     */   {
/* 458 */     if (plane == null) {
/* 459 */       return 0.0D;
/*     */     }
/*     */     
/* 462 */     double dot = plane.getNormal().dot3(this.axisUnitDirection);
/* 463 */     double scale = 1.0D - dot * dot;
/* 464 */     if (scale <= 0.0D) {
/* 465 */       return 0.0D;
/*     */     }
/* 467 */     return this.cylinderRadius * Math.sqrt(scale);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean intersects(Plane plane)
/*     */   {
/* 473 */     if (plane == null)
/*     */     {
/* 475 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 478 */     double effectiveRadius = getEffectiveRadius(plane);
/* 479 */     return intersects(plane, effectiveRadius) >= 0.0D;
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
/*     */   public boolean equals(Object o)
/*     */   {
/* 894 */     if (this == o)
/* 895 */       return true;
/* 896 */     if (!(o instanceof Cylinder)) {
/* 897 */       return false;
/*     */     }
/* 899 */     Cylinder cylinder = (Cylinder)o;
/*     */     
/* 901 */     if (Double.compare(cylinder.cylinderHeight, this.cylinderHeight) != 0)
/* 902 */       return false;
/* 903 */     if (Double.compare(cylinder.cylinderRadius, this.cylinderRadius) != 0)
/* 904 */       return false;
/* 905 */     if (this.axisUnitDirection != null ? !this.axisUnitDirection.equals(cylinder.axisUnitDirection) : cylinder.axisUnitDirection != null)
/*     */     {
/* 907 */       return false; }
/* 908 */     if (this.bottomCenter != null ? !this.bottomCenter.equals(cylinder.bottomCenter) : cylinder.bottomCenter != null) {
/* 909 */       return false;
/*     */     }
/* 911 */     if (this.topCenter != null ? !this.topCenter.equals(cylinder.topCenter) : cylinder.topCenter != null) {
/* 912 */       return false;
/*     */     }
/* 914 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 922 */     int result = this.bottomCenter != null ? this.bottomCenter.hashCode() : 0;
/* 923 */     result = 31 * result + (this.topCenter != null ? this.topCenter.hashCode() : 0);
/* 924 */     result = 31 * result + (this.axisUnitDirection != null ? this.axisUnitDirection.hashCode() : 0);
/* 925 */     long temp = this.cylinderRadius != 0.0D ? Double.doubleToLongBits(this.cylinderRadius) : 0L;
/* 926 */     result = 31 * result + (int)(temp ^ temp >>> 32);
/* 927 */     temp = this.cylinderHeight != 0.0D ? Double.doubleToLongBits(this.cylinderHeight) : 0L;
/* 928 */     result = 31 * result + (int)(temp ^ temp >>> 32);
/* 929 */     return result;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 934 */     return this.cylinderRadius + ", " + this.bottomCenter.toString() + ", " + this.topCenter.toString() + ", " + this.axisUnitDirection.toString();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/math/Cylinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */