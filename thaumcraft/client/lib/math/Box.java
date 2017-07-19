/*      */ package thaumcraft.client.lib.math;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Box
/*      */ {
/*   41 */   protected static final int[][] ProjectionHullTable = { null, { 7, 6, 5, 4 }, { 0, 1, 2, 3 }, null, { 3, 2, 6, 7 }, { 3, 2, 6, 5, 4, 7 }, { 0, 1, 2, 6, 7, 3 }, null, { 1, 0, 4, 5 }, { 1, 0, 4, 7, 6, 5 }, { 2, 3, 0, 4, 5, 1 }, null, null, null, null, null, { 2, 1, 5, 6 }, { 2, 1, 5, 4, 7, 6 }, { 3, 0, 1, 5, 6, 2 }, null, { 3, 2, 1, 5, 6, 7 }, { 3, 2, 1, 5, 4, 7 }, { 3, 0, 1, 5, 6, 7 }, null, { 2, 1, 0, 4, 5, 6 }, { 2, 1, 0, 4, 7, 6 }, { 2, 3, 0, 4, 5, 6 }, null, null, null, null, null, { 0, 3, 7, 4 }, { 0, 3, 7, 6, 5, 4 }, { 1, 2, 3, 7, 4, 0 }, null, { 0, 3, 2, 6, 7, 4 }, { 0, 3, 2, 6, 5, 4 }, { 0, 1, 2, 6, 7, 4 }, null, { 1, 0, 3, 7, 4, 5 }, { 1, 0, 3, 7, 6, 5 }, { 1, 2, 3, 7, 4, 5 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 bottomCenter;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 topCenter;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final Vec4 center;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final Vec4 r;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final Vec4 s;
/*      */   
/*      */ 
/*      */ 
/*      */   protected final Vec4 t;
/*      */   
/*      */ 
/*      */ 
/*      */   protected final Vec4 ru;
/*      */   
/*      */ 
/*      */ 
/*      */   protected final Vec4 su;
/*      */   
/*      */ 
/*      */ 
/*      */   protected final Vec4 tu;
/*      */   
/*      */ 
/*      */ 
/*      */   protected final double rLength;
/*      */   
/*      */ 
/*      */ 
/*      */   protected final double sLength;
/*      */   
/*      */ 
/*      */ 
/*      */   protected final double tLength;
/*      */   
/*      */ 
/*      */ 
/*      */   protected final Plane[] planes;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Box(Vec4 bottomCenter, Vec4 topCenter, Vec4 center, Vec4 r, Vec4 s, Vec4 t, Vec4 ru, Vec4 su, Vec4 tu, double rlength, double sLength, double tLength, Plane[] planes)
/*      */   {
/*  105 */     this.bottomCenter = bottomCenter;
/*  106 */     this.topCenter = topCenter;
/*  107 */     this.center = center;
/*  108 */     this.r = r;
/*  109 */     this.s = s;
/*  110 */     this.t = t;
/*  111 */     this.ru = ru;
/*  112 */     this.su = su;
/*  113 */     this.tu = tu;
/*  114 */     this.rLength = rlength;
/*  115 */     this.sLength = sLength;
/*  116 */     this.tLength = tLength;
/*  117 */     this.planes = planes;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Box(Vec4[] axes, double rMin, double rMax, double sMin, double sMax, double tMin, double tMax)
/*      */   {
/*  143 */     if ((axes == null) || (axes[0] == null) || (axes[1] == null) || (axes[2] == null))
/*      */     {
/*  145 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  148 */     this.ru = axes[0];
/*  149 */     this.su = axes[1];
/*  150 */     this.tu = axes[2];
/*      */     
/*  152 */     this.r = this.ru.multiply3(rMax - rMin);
/*  153 */     this.s = this.su.multiply3(sMax - sMin);
/*  154 */     this.t = this.tu.multiply3(tMax - tMin);
/*      */     
/*  156 */     this.rLength = this.r.getLength3();
/*  157 */     this.sLength = this.s.getLength3();
/*  158 */     this.tLength = this.t.getLength3();
/*      */     
/*      */ 
/*  161 */     this.planes = new Plane[6];
/*  162 */     this.planes[0] = new Plane(-this.ru.x, -this.ru.y, -this.ru.z, rMin);
/*  163 */     this.planes[1] = new Plane(this.ru.x, this.ru.y, this.ru.z, -rMax);
/*  164 */     this.planes[2] = new Plane(-this.su.x, -this.su.y, -this.su.z, sMin);
/*  165 */     this.planes[3] = new Plane(this.su.x, this.su.y, this.su.z, -sMax);
/*  166 */     this.planes[4] = new Plane(-this.tu.x, -this.tu.y, -this.tu.z, tMin);
/*  167 */     this.planes[5] = new Plane(this.tu.x, this.tu.y, this.tu.z, -tMax);
/*      */     
/*  169 */     double a = 0.5D * (rMin + rMax);
/*  170 */     double b = 0.5D * (sMin + sMax);
/*  171 */     double c = 0.5D * (tMin + tMax);
/*  172 */     this.center = this.ru.multiply3(a).add3(this.su.multiply3(b)).add3(this.tu.multiply3(c));
/*      */     
/*  174 */     Vec4 rHalf = this.r.multiply3(0.5D);
/*  175 */     this.topCenter = this.center.add3(rHalf);
/*  176 */     this.bottomCenter = this.center.subtract3(rHalf);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Box(Vec4 point)
/*      */   {
/*  188 */     if (point == null)
/*      */     {
/*  190 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  193 */     this.ru = new Vec4(1.0D, 0.0D, 0.0D, 1.0D);
/*  194 */     this.su = new Vec4(0.0D, 1.0D, 0.0D, 1.0D);
/*  195 */     this.tu = new Vec4(0.0D, 0.0D, 1.0D, 1.0D);
/*      */     
/*  197 */     this.r = this.ru;
/*  198 */     this.s = this.su;
/*  199 */     this.t = this.tu;
/*      */     
/*  201 */     this.rLength = 1.0D;
/*  202 */     this.sLength = 1.0D;
/*  203 */     this.tLength = 1.0D;
/*      */     
/*      */ 
/*  206 */     this.planes = new Plane[6];
/*  207 */     double d = 0.5D * point.getLength3();
/*  208 */     this.planes[0] = new Plane(-this.ru.x, -this.ru.y, -this.ru.z, -(d + 0.5D));
/*  209 */     this.planes[1] = new Plane(this.ru.x, this.ru.y, this.ru.z, -(d + 0.5D));
/*  210 */     this.planes[2] = new Plane(-this.su.x, -this.su.y, -this.su.z, -(d + 0.5D));
/*  211 */     this.planes[3] = new Plane(this.su.x, this.su.y, this.su.z, -(d + 0.5D));
/*  212 */     this.planes[4] = new Plane(-this.tu.x, -this.tu.y, -this.tu.z, -(d + 0.5D));
/*  213 */     this.planes[5] = new Plane(this.tu.x, this.tu.y, this.tu.z, -(d + 0.5D));
/*      */     
/*  215 */     this.center = this.ru.add3(this.su).add3(this.tu).multiply3(0.5D);
/*      */     
/*  217 */     Vec4 rHalf = this.r.multiply3(0.5D);
/*  218 */     this.topCenter = this.center.add3(rHalf);
/*  219 */     this.bottomCenter = this.center.subtract3(rHalf);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getCenter()
/*      */   {
/*  229 */     return this.center;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getBottomCenter()
/*      */   {
/*  239 */     return this.bottomCenter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getTopCenter()
/*      */   {
/*  249 */     return this.topCenter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getRAxis()
/*      */   {
/*  259 */     return this.r;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getSAxis()
/*      */   {
/*  269 */     return this.s;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getTAxis()
/*      */   {
/*  279 */     return this.t;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getUnitRAxis()
/*      */   {
/*  289 */     return this.ru;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getUnitSAxis()
/*      */   {
/*  299 */     return this.su;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4 getUnitTAxis()
/*      */   {
/*  309 */     return this.tu;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4[] getCorners()
/*      */   {
/*  320 */     Vec4 ll = this.s.add3(this.t).multiply3(-0.5D);
/*  321 */     Vec4 lr = this.t.subtract3(this.s).multiply3(0.5D);
/*  322 */     Vec4 ur = this.s.add3(this.t).multiply3(0.5D);
/*  323 */     Vec4 ul = this.s.subtract3(this.t).multiply3(0.5D);
/*      */     
/*  325 */     Vec4[] corners = new Vec4[8];
/*  326 */     corners[0] = this.bottomCenter.add3(ll);
/*  327 */     corners[1] = this.bottomCenter.add3(lr);
/*  328 */     corners[2] = this.bottomCenter.add3(ur);
/*  329 */     corners[3] = this.bottomCenter.add3(ul);
/*  330 */     corners[4] = this.topCenter.add3(ll);
/*  331 */     corners[5] = this.topCenter.add3(lr);
/*  332 */     corners[6] = this.topCenter.add3(ur);
/*  333 */     corners[7] = this.topCenter.add3(ul);
/*      */     
/*  335 */     return corners;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Plane[] getPlanes()
/*      */   {
/*  345 */     return this.planes;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getRLength()
/*      */   {
/*  355 */     return this.rLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getSLength()
/*      */   {
/*  365 */     return this.sLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getTLength()
/*      */   {
/*  375 */     return this.tLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDiameter()
/*      */   {
/*  386 */     return Math.sqrt(this.rLength * this.rLength + this.sLength * this.sLength + this.tLength * this.tLength);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getRadius()
/*      */   {
/*  397 */     return 0.5D * getDiameter();
/*      */   }
/*      */   
/*      */   public Box translate(Vec4 point)
/*      */   {
/*  402 */     Vec4 bc = this.bottomCenter.add3(point);
/*  403 */     Vec4 tc = this.topCenter.add3(point);
/*  404 */     Vec4 c = this.center.add3(point);
/*      */     
/*  406 */     Plane[] newPlanes = new Plane[this.planes.length];
/*  407 */     for (int i = 0; i < this.planes.length; i++)
/*      */     {
/*  409 */       Plane pl = this.planes[i];
/*  410 */       Vec4 n = pl.getNormal();
/*  411 */       newPlanes[i] = new Plane(n.x, n.y, n.z, pl.getDistance() - n.dot3(point));
/*      */     }
/*      */     
/*  414 */     return new Box(bc, tc, c, this.r, this.s, this.t, this.ru, this.su, this.tu, this.rLength, this.sLength, this.tLength, newPlanes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Box computeBoundingBox(Iterable<? extends Vec4> points)
/*      */   {
/*  431 */     if (points == null)
/*      */     {
/*  433 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  436 */     Vec4[] axes = Math3d.computePrincipalAxes(points);
/*  437 */     if (axes == null)
/*      */     {
/*  439 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  442 */     Vec4 r = axes[0];
/*  443 */     Vec4 s = axes[1];
/*  444 */     Vec4 t = axes[2];
/*      */     
/*      */ 
/*  447 */     double minDotR = Double.MAX_VALUE;
/*  448 */     double maxDotR = -minDotR;
/*  449 */     double minDotS = Double.MAX_VALUE;
/*  450 */     double maxDotS = -minDotS;
/*  451 */     double minDotT = Double.MAX_VALUE;
/*  452 */     double maxDotT = -minDotT;
/*      */     
/*  454 */     for (Vec4 p : points)
/*      */     {
/*  456 */       if (p != null)
/*      */       {
/*      */ 
/*  459 */         double pdr = p.dot3(r);
/*  460 */         if (pdr < minDotR)
/*  461 */           minDotR = pdr;
/*  462 */         if (pdr > maxDotR) {
/*  463 */           maxDotR = pdr;
/*      */         }
/*  465 */         double pds = p.dot3(s);
/*  466 */         if (pds < minDotS)
/*  467 */           minDotS = pds;
/*  468 */         if (pds > maxDotS) {
/*  469 */           maxDotS = pds;
/*      */         }
/*  471 */         double pdt = p.dot3(t);
/*  472 */         if (pdt < minDotT)
/*  473 */           minDotT = pdt;
/*  474 */         if (pdt > maxDotT)
/*  475 */           maxDotT = pdt;
/*      */       }
/*      */     }
/*  478 */     if (maxDotR == minDotR)
/*  479 */       maxDotR = minDotR + 1.0D;
/*  480 */     if (maxDotS == minDotS)
/*  481 */       maxDotS = minDotS + 1.0D;
/*  482 */     if (maxDotT == minDotT) {
/*  483 */       maxDotT = minDotT + 1.0D;
/*      */     }
/*  485 */     return new Box(axes, minDotR, maxDotR, minDotS, maxDotS, minDotT, maxDotT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Box union(Iterable<? extends Box> iterable)
/*      */   {
/*  594 */     if (iterable == null)
/*      */     {
/*  596 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  599 */     ArrayList<Box> boxes = new ArrayList();
/*      */     
/*  601 */     for (Box box : iterable)
/*      */     {
/*  603 */       if (box != null)
/*      */       {
/*      */ 
/*  606 */         boxes.add(box);
/*      */       }
/*      */     }
/*  609 */     if (boxes.size() == 0)
/*      */     {
/*  611 */       return null;
/*      */     }
/*  613 */     if (boxes.size() == 1)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  618 */       return (Box)boxes.get(0);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  625 */     ArrayList<Vec4> corners = new ArrayList(8 * boxes.size());
/*  626 */     for (Box box : boxes)
/*      */     {
/*  628 */       corners.addAll(Arrays.asList(box.getCorners()));
/*      */     }
/*      */     
/*  631 */     return computeBoundingBox(corners);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double getEffectiveRadius2(Plane plane)
/*      */   {
/*  695 */     if (plane == null) {
/*  696 */       return 0.0D;
/*      */     }
/*      */     
/*      */ 
/*  700 */     Vec4 n = plane.getNormal();
/*  701 */     return 0.5D * (Math.abs(this.s.dot3(n)) + Math.abs(this.t.dot3(n)));
/*      */   }
/*      */   
/*      */ 
/*      */   public double getEffectiveRadius(Plane plane)
/*      */   {
/*  707 */     if (plane == null) {
/*  708 */       return 0.0D;
/*      */     }
/*      */     
/*  711 */     Vec4 n = plane.getNormal();
/*  712 */     return 0.5D * (Math.abs(this.s.dot3(n)) + Math.abs(this.t.dot3(n)) + Math.abs(this.r.dot3(n)));
/*      */   }
/*      */   
/*      */ 
/*      */   protected double intersectsAt(Plane plane, double effectiveRadius, Vec4[] endpoints)
/*      */   {
/*  718 */     double dq1 = plane.dot(endpoints[0]);
/*  719 */     boolean bq1 = dq1 <= -effectiveRadius;
/*      */     
/*      */ 
/*  722 */     double dq2 = plane.dot(endpoints[1]);
/*  723 */     boolean bq2 = dq2 <= -effectiveRadius;
/*      */     
/*  725 */     if ((bq1) && (bq2)) {
/*  726 */       return -1.0D;
/*      */     }
/*  728 */     if (bq1 == bq2) {
/*  729 */       return 0.0D;
/*      */     }
/*      */     
/*  732 */     double t = (effectiveRadius + dq1) / plane.getNormal().dot3(endpoints[0].subtract3(endpoints[1]));
/*      */     
/*  734 */     Vec4 newEndPoint = endpoints[0].add3(endpoints[1].subtract3(endpoints[0]).multiply3(t));
/*      */     
/*  736 */     if (bq1) {
/*  737 */       endpoints[0] = newEndPoint;
/*      */     } else {
/*  739 */       endpoints[1] = newEndPoint;
/*      */     }
/*  741 */     return t;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean intersects(Plane plane)
/*      */   {
/*  747 */     if (plane == null)
/*      */     {
/*  749 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  752 */     double effectiveRadius = getEffectiveRadius(plane);
/*  753 */     return intersects(plane, effectiveRadius) >= 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */   protected double intersects(Plane plane, double effectiveRadius)
/*      */   {
/*  759 */     double dq1 = plane.dot(this.bottomCenter);
/*  760 */     boolean bq1 = dq1 <= -effectiveRadius;
/*      */     
/*      */ 
/*  763 */     double dq2 = plane.dot(this.topCenter);
/*  764 */     boolean bq2 = dq2 <= -effectiveRadius;
/*      */     
/*  766 */     if ((bq1) && (bq2)) {
/*  767 */       return -1.0D;
/*      */     }
/*  769 */     if (bq1 == bq2) {
/*  770 */       return 0.0D;
/*      */     }
/*  772 */     return 1.0D;
/*      */   }
/*      */   
/*      */ 
/*      */   public Intersection[] intersect(Line line)
/*      */   {
/*  778 */     return Math3d.polytopeIntersect(line, this.planes);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean intersects(Line line)
/*      */   {
/*  784 */     if (line == null)
/*      */     {
/*  786 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  789 */     return Math3d.polytopeIntersect(line, this.planes) != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object o)
/*      */   {
/* 1076 */     if (this == o)
/* 1077 */       return true;
/* 1078 */     if (!(o instanceof Box)) {
/* 1079 */       return false;
/*      */     }
/* 1081 */     Box box = (Box)o;
/*      */     
/* 1083 */     if (this.center != null ? !this.center.equals(box.center) : box.center != null)
/* 1084 */       return false;
/* 1085 */     if (this.r != null ? !this.r.equals(box.r) : box.r != null)
/* 1086 */       return false;
/* 1087 */     if (this.s != null ? !this.s.equals(box.s) : box.s != null) {
/* 1088 */       return false;
/*      */     }
/* 1090 */     if (this.t != null ? !this.t.equals(box.t) : box.t != null) {
/* 1091 */       return false;
/*      */     }
/* 1093 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1099 */     int result = this.center != null ? this.center.hashCode() : 0;
/* 1100 */     result = 31 * result + (this.r != null ? this.r.hashCode() : 0);
/* 1101 */     result = 31 * result + (this.s != null ? this.s.hashCode() : 0);
/* 1102 */     result = 31 * result + (this.t != null ? this.t.hashCode() : 0);
/* 1103 */     return result;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/math/Box.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */