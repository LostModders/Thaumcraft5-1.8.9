/*     */ package thaumcraft.client.lib.math;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Plane
/*     */ {
/*     */   private final Vec4 n;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Plane(Vec4 vec)
/*     */   {
/*  17 */     if (vec == null)
/*     */     {
/*  19 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  22 */     if (vec.getLengthSquared3() == 0.0D)
/*     */     {
/*  24 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  27 */     this.n = vec;
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
/*     */   public Plane(double nx, double ny, double nz, double d)
/*     */   {
/*  42 */     if ((nx == 0.0D) && (ny == 0.0D) && (nz == 0.0D))
/*     */     {
/*  44 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  47 */     this.n = new Vec4(nx, ny, nz, d);
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
/*     */   public static Plane fromPoints(Vec4 pa, Vec4 pb, Vec4 pc)
/*     */   {
/*  65 */     if ((pa == null) || (pb == null) || (pc == null))
/*     */     {
/*  67 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  70 */     Vec4 vab = pb.subtract3(pa);
/*  71 */     Vec4 vac = pc.subtract3(pa);
/*  72 */     Vec4 n = vab.cross3(vac);
/*  73 */     double d = -n.dot3(pa);
/*     */     
/*  75 */     return new Plane(n.x, n.y, n.z, d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Vec4 getNormal()
/*     */   {
/*  85 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final double getDistance()
/*     */   {
/*  95 */     return this.n.w;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Vec4 getVector()
/*     */   {
/* 105 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Plane normalize()
/*     */   {
/* 116 */     double length = this.n.getLength3();
/* 117 */     if (length == 0.0D) {
/* 118 */       return this;
/*     */     }
/* 120 */     return new Plane(new Vec4(this.n.x / length, this.n.y / length, this.n.z / length, this.n.w / length));
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
/*     */   public final double dot(Vec4 p)
/*     */   {
/* 138 */     if (p == null)
/*     */     {
/* 140 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 143 */     return this.n.x * p.x + this.n.y * p.y + this.n.z * p.z + this.n.w * p.w;
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
/*     */   public Vec4 intersect(Line line)
/*     */   {
/* 157 */     if (line == null)
/*     */     {
/* 159 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 162 */     double t = intersectDistance(line);
/*     */     
/* 164 */     if (Double.isNaN(t)) {
/* 165 */       return null;
/*     */     }
/* 167 */     if (Double.isInfinite(t)) {
/* 168 */       return line.getOrigin();
/*     */     }
/* 170 */     return line.getPointAt(t);
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
/*     */   public double intersectDistance(Line line)
/*     */   {
/* 186 */     if (line == null)
/*     */     {
/* 188 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 191 */     double ldotv = this.n.dot3(line.getDirection());
/* 192 */     if (ldotv == 0.0D)
/*     */     {
/* 194 */       double ldots = this.n.dot4(line.getOrigin());
/* 195 */       if (ldots == 0.0D) {
/* 196 */         return Double.POSITIVE_INFINITY;
/*     */       }
/* 198 */       return NaN.0D;
/*     */     }
/*     */     
/* 201 */     return -this.n.dot4(line.getOrigin()) / ldotv;
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
/*     */   public Vec4 intersect(Vec4 pa, Vec4 pb)
/*     */   {
/* 217 */     if ((pa == null) || (pb == null))
/*     */     {
/* 219 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 225 */       if (pa.equals(pb))
/*     */       {
/* 227 */         double d = distanceTo(pa);
/* 228 */         if (d == 0.0D) {
/* 229 */           return pa;
/*     */         }
/* 231 */         return null;
/*     */       }
/*     */       
/* 234 */       Line l = Line.fromSegment(pa, pb);
/* 235 */       double t = intersectDistance(l);
/*     */       
/* 237 */       if (Double.isInfinite(t)) {
/* 238 */         return Vec4.INFINITY;
/*     */       }
/* 240 */       if ((Double.isNaN(t)) || (t < 0.0D) || (t > 1.0D)) {
/* 241 */         return null;
/*     */       }
/* 243 */       return l.getPointAt(t);
/*     */     }
/*     */     catch (IllegalArgumentException e) {}
/*     */     
/* 247 */     return null;
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
/*     */   public Vec4[] clip(Vec4 pa, Vec4 pb)
/*     */   {
/* 269 */     if ((pa == null) || (pb == null))
/*     */     {
/* 271 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 274 */     if (pa.equals(pb)) {
/* 275 */       return null;
/*     */     }
/*     */     
/* 278 */     Line line = Line.fromSegment(pa, pb);
/* 279 */     double ldotv = this.n.dot3(line.getDirection());
/*     */     
/*     */ 
/* 282 */     if (ldotv == 0.0D)
/*     */     {
/* 284 */       double ldots = this.n.dot4(line.getOrigin());
/* 285 */       if (ldots == 0.0D) {
/* 286 */         return new Vec4[] { pa, pb };
/*     */       }
/* 288 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 292 */     double t = -this.n.dot4(line.getOrigin()) / ldotv;
/* 293 */     if ((t < 0.0D) || (t > 1.0D)) {
/* 294 */       return null;
/*     */     }
/* 296 */     Vec4 p = line.getPointAt(t);
/* 297 */     if (ldotv > 0.0D) {
/* 298 */       return new Vec4[] { p, pb };
/*     */     }
/* 300 */     return new Vec4[] { pa, p };
/*     */   }
/*     */   
/*     */   public double distanceTo(Vec4 p)
/*     */   {
/* 305 */     return this.n.dot4(p);
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
/*     */   public int onSameSide(Vec4 pa, Vec4 pb)
/*     */   {
/* 320 */     if ((pa == null) || (pb == null))
/*     */     {
/* 322 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 325 */     double da = distanceTo(pa);
/* 326 */     double db = distanceTo(pb);
/*     */     
/* 328 */     if ((da < 0.0D) && (db < 0.0D)) {
/* 329 */       return -1;
/*     */     }
/* 331 */     if ((da > 0.0D) && (db > 0.0D)) {
/* 332 */       return 1;
/*     */     }
/* 334 */     return 0;
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
/*     */   public int onSameSide(Vec4[] pts)
/*     */   {
/* 348 */     if (pts == null)
/*     */     {
/* 350 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 353 */     double d = distanceTo(pts[0]);
/* 354 */     int side = d > 0.0D ? 1 : d < 0.0D ? -1 : 0;
/* 355 */     if (side == 0) {
/* 356 */       return 0;
/*     */     }
/* 358 */     for (int i = 1; i < pts.length; i++)
/*     */     {
/* 360 */       if (pts[i] == null)
/*     */       {
/* 362 */         throw new IllegalArgumentException();
/*     */       }
/*     */       
/* 365 */       d = distanceTo(pts[i]);
/* 366 */       if (((side != -1) || (d >= 0.0D)) && ((side != 1) || (d <= 0.0D)))
/*     */       {
/*     */ 
/* 369 */         return 0;
/*     */       }
/*     */     }
/* 372 */     return side;
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
/*     */   public boolean equals(Object o)
/*     */   {
/* 416 */     if (this == o)
/* 417 */       return true;
/* 418 */     if (!(o instanceof Plane)) {
/* 419 */       return false;
/*     */     }
/* 421 */     Plane plane = (Plane)o;
/*     */     
/* 423 */     return this.n != null ? this.n.equals(plane.n) : plane.n == null;
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 429 */     return this.n != null ? this.n.hashCode() : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public final String toString()
/*     */   {
/* 435 */     return this.n.toString();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/math/Plane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */