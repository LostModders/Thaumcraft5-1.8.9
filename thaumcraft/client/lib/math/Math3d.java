/*     */ package thaumcraft.client.lib.math;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Math3d
/*     */ {
/*     */   public static final double SECOND_TO_MILLIS = 1000.0D;
/*     */   public static final double MINUTE_TO_MILLIS = 60000.0D;
/*     */   public static final double HOUR_TO_MILLIS = 3600000.0D;
/*     */   public static final double DAY_TO_MILLIS = 8.64E7D;
/*     */   public static final double METERS_TO_KILOMETERS = 0.001D;
/*     */   public static final double METERS_TO_MILES = 6.21371192E-4D;
/*     */   public static final double METERS_TO_NAUTICAL_MILES = 5.39956803E-4D;
/*     */   public static final double METERS_TO_YARDS = 1.0936133D;
/*     */   public static final double METERS_TO_FEET = 3.280839895D;
/*     */   public static final double SQUARE_METERS_TO_SQUARE_KILOMETERS = 1.0E-6D;
/*     */   public static final double SQUARE_METERS_TO_SQUARE_MILES = 3.86102159E-7D;
/*     */   public static final double SQUARE_METERS_TO_SQUARE_YARDS = 1.19599005D;
/*     */   public static final double SQUARE_METERS_TO_SQUARE_FEET = 10.7639104D;
/*     */   public static final double SQUARE_METERS_TO_HECTARES = 1.0E-4D;
/*     */   public static final double SQUARE_METERS_TO_ACRES = 2.47105381E-4D;
/*     */   
/*     */   public static double logBase2(double value)
/*     */   {
/*  37 */     return Math.log(value) / Math.log(2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isPowerOfTwo(int value)
/*     */   {
/*  49 */     return value == powerOfTwoCeiling(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int powerOfTwoCeiling(int reference)
/*     */   {
/*  61 */     int power = (int)Math.ceil(Math.log(reference) / Math.log(2.0D));
/*  62 */     return (int)Math.pow(2.0D, power);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int powerOfTwoFloor(int reference)
/*     */   {
/*  74 */     int power = (int)Math.floor(Math.log(reference) / Math.log(2.0D));
/*  75 */     return (int)Math.pow(2.0D, power);
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
/*     */   protected static int[] computePowers(int base, int numPowers)
/*     */   {
/*  89 */     int[] powers = new int[numPowers];
/*     */     
/*  91 */     powers[0] = 1;
/*  92 */     for (int i = 1; i < numPowers; i++)
/*     */     {
/*  94 */       powers[i] += base * powers[(i - 1)];
/*     */     }
/*     */     
/*  97 */     return powers;
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
/*     */   public static double clamp(double v, double min, double max)
/*     */   {
/* 111 */     return v > max ? max : v < min ? min : v;
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
/*     */   public static int clamp(int v, int min, int max)
/*     */   {
/* 125 */     return v > max ? max : v < min ? min : v;
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
/*     */   public static double stepValue(double value, double min, double max)
/*     */   {
/* 149 */     if (value <= min)
/*     */     {
/* 151 */       return 0.0D;
/*     */     }
/* 153 */     if (value >= max)
/*     */     {
/* 155 */       return 1.0D;
/*     */     }
/*     */     
/*     */ 
/* 159 */     return (value - min) / (max - min);
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
/*     */   public static double smoothStepValue(double value, double min, double max)
/*     */   {
/* 190 */     if (value <= min)
/*     */     {
/* 192 */       return 0.0D;
/*     */     }
/* 194 */     if (value >= max)
/*     */     {
/* 196 */       return 1.0D;
/*     */     }
/*     */     
/*     */ 
/* 200 */     double step = (value - min) / (max - min);
/* 201 */     return step * step * (3.0D - 2.0D * step);
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
/*     */   public static double computeInterpolationFactor(double v, double x, double y)
/*     */   {
/* 219 */     return clamp((v - x) / (y - x), 0.0D, 1.0D);
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
/*     */   public static double mix(double a, double x, double y)
/*     */   {
/* 237 */     double t = clamp(a, 0.0D, 1.0D);
/* 238 */     return x + t * (y - x);
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
/*     */   public static double mixSmooth(double a, double x, double y)
/*     */   {
/* 260 */     double t = clamp(a, 0.0D, 1.0D);
/* 261 */     t = t * t * (3.0D - 2.0D * t);
/* 262 */     return x + t * (y - x);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertMetersToFeet(double meters)
/*     */   {
/* 274 */     return meters * 3.280839895D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertMetersToMiles(double meters)
/*     */   {
/* 286 */     return meters * 6.21371192E-4D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertFeetToMeters(double feet)
/*     */   {
/* 298 */     return feet / 3.280839895D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertSecondsToMillis(double seconds)
/*     */   {
/* 310 */     return seconds * 1000.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertMillisToSeconds(double millis)
/*     */   {
/* 322 */     return millis / 1000.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertMinutesToMillis(double minutes)
/*     */   {
/* 334 */     return minutes * 60000.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertMillisToMinutes(double millis)
/*     */   {
/* 346 */     return millis / 60000.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertHoursToMillis(double hours)
/*     */   {
/* 358 */     return hours * 3600000.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertMillisToHours(double mills)
/*     */   {
/* 370 */     return mills / 3600000.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertDaysToMillis(double millis)
/*     */   {
/* 382 */     return millis * 8.64E7D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double convertMillisToDays(double millis)
/*     */   {
/* 394 */     return millis / 8.64E7D;
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
/*     */   public static Vec4 computeArrayNormal(Vec4[] coords)
/*     */   {
/* 409 */     Vec4[] verts = findThreeIndependentVertices(coords);
/* 410 */     return verts != null ? computeTriangleNormal(verts[0], verts[1], verts[2]) : null;
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
/*     */   public static Vec4[] findThreeIndependentVertices(FloatBuffer coords, int stride)
/*     */   {
/* 424 */     int xstride = stride > 0 ? stride : 3;
/*     */     
/* 426 */     if ((coords == null) || (coords.limit() < 3 * xstride)) {
/* 427 */       return null;
/*     */     }
/* 429 */     Vec4 a = new Vec4(coords.get(0), coords.get(1), coords.get(2));
/* 430 */     Vec4 b = null;
/* 431 */     Vec4 c = null;
/*     */     
/* 433 */     int k = xstride;
/* 434 */     for (; k < coords.limit(); k += xstride)
/*     */     {
/* 436 */       b = new Vec4(coords.get(k), coords.get(k + 1), coords.get(k + 2));
/* 437 */       if ((b.x != a.x) || (b.y != a.y) || (b.z != a.z))
/*     */         break;
/* 439 */       b = null;
/*     */     }
/*     */     
/* 442 */     if (b == null) {
/* 443 */       return null;
/*     */     }
/* 445 */     for (k += xstride; k < coords.limit(); k += xstride)
/*     */     {
/* 447 */       c = new Vec4(coords.get(k), coords.get(k + 1), coords.get(k + 2));
/*     */       
/*     */ 
/* 450 */       if (((c.x != a.x) || (c.y != a.y) || (c.z != a.z)) && ((c.x != b.x) || (c.y != b.y) || (c.z != b.z)) && 
/*     */       
/* 452 */         (!Vec4.areColinear(a, b, c))) {
/*     */         break;
/*     */       }
/*     */       
/* 456 */       c = null;
/*     */     }
/*     */     
/* 459 */     return c != null ? new Vec4[] { a, b, c } : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Vec4[] findThreeIndependentVertices(Vec4[] coords)
/*     */   {
/* 471 */     if ((coords == null) || (coords.length < 3)) {
/* 472 */       return null;
/*     */     }
/* 474 */     Vec4 a = coords[0];
/* 475 */     Vec4 b = null;
/* 476 */     Vec4 c = null;
/*     */     
/* 478 */     for (int k = 1; 
/* 479 */         k < coords.length; k++)
/*     */     {
/* 481 */       b = coords[k];
/* 482 */       if ((b.x != a.x) || (b.y != a.y) || (b.z != a.z))
/*     */         break;
/* 484 */       b = null;
/*     */     }
/*     */     
/* 487 */     if (b == null) {
/* 488 */       return null;
/*     */     }
/* 490 */     for (; k < coords.length; k++)
/*     */     {
/* 492 */       c = coords[k];
/*     */       
/*     */ 
/* 495 */       if (((c.x != a.x) || (c.y != a.y) || (c.z != a.z)) && ((c.x != b.x) || (c.y != b.y) || (c.z != b.z)) && 
/*     */       
/* 497 */         (!Vec4.areColinear(a, b, c))) {
/*     */         break;
/*     */       }
/*     */       
/* 501 */       c = null;
/*     */     }
/*     */     
/* 504 */     return c != null ? new Vec4[] { a, b, c } : null;
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
/*     */   public static Vec4 computeTriangleNormal(Vec4 a, Vec4 b, Vec4 c)
/*     */   {
/* 520 */     if ((a == null) || (b == null) || (c == null))
/*     */     {
/* 522 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 525 */     double x = (b.y - a.y) * (c.z - a.z) - (b.z - a.z) * (c.y - a.y);
/* 526 */     double y = (b.z - a.z) * (c.x - a.x) - (b.x - a.x) * (c.z - a.z);
/* 527 */     double z = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
/*     */     
/* 529 */     double length = x * x + y * y + z * z;
/* 530 */     if (length == 0.0D) {
/* 531 */       return new Vec4(x, y, z);
/*     */     }
/* 533 */     length = Math.sqrt(length);
/* 534 */     return new Vec4(x / length, y / length, z / length);
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
/*     */   public static double computePolygonAreaFromVertices(Iterable<? extends Vec4> points)
/*     */   {
/* 550 */     if (points == null)
/*     */     {
/* 552 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 555 */     Iterator<? extends Vec4> iter = points.iterator();
/* 556 */     if (!iter.hasNext())
/*     */     {
/* 558 */       return 0.0D;
/*     */     }
/*     */     
/* 561 */     double area = 0.0D;
/* 562 */     Vec4 firstPoint = (Vec4)iter.next();
/* 563 */     Vec4 point = firstPoint;
/*     */     
/* 565 */     while (iter.hasNext())
/*     */     {
/* 567 */       Vec4 nextLocation = (Vec4)iter.next();
/*     */       
/* 569 */       area += point.x * nextLocation.y;
/* 570 */       area -= nextLocation.x * point.y;
/*     */       
/* 572 */       point = nextLocation;
/*     */     }
/*     */     
/*     */ 
/* 576 */     if (!point.equals(firstPoint))
/*     */     {
/* 578 */       area += point.x * firstPoint.y;
/* 579 */       area -= firstPoint.x * point.y;
/*     */     }
/*     */     
/* 582 */     area /= 2.0D;
/* 583 */     return area;
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
/*     */   public static Vec4[] computePrincipalAxes(Iterable<? extends Vec4> points)
/*     */   {
/* 603 */     if (points == null)
/*     */     {
/* 605 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 610 */     Matrix covariance = Matrix.fromCovarianceOfVertices(points);
/* 611 */     if (covariance == null) {
/* 612 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 616 */     double[] eigenValues = new double[3];
/* 617 */     Vec4[] eigenVectors = new Vec4[3];
/* 618 */     Matrix.computeEigensystemFromSymmetricMatrix3(covariance, eigenValues, eigenVectors);
/*     */     
/*     */ 
/*     */ 
/* 622 */     Integer[] indexArray = { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2) };
/* 623 */     Arrays.sort(indexArray, new Comparator()
/*     */     {
/*     */       public int compare(Integer a, Integer b)
/*     */       {
/* 627 */         return Double.compare(this.val$eigenValues[a.intValue()], this.val$eigenValues[b.intValue()]);
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 633 */     });
/* 634 */     return new Vec4[] { eigenVectors[indexArray[2].intValue()].normalize3(), eigenVectors[indexArray[1].intValue()].normalize3(), eigenVectors[indexArray[0].intValue()].normalize3() };
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
/*     */   public static boolean isPolygonClosed2(Iterable<? extends Vec4> points)
/*     */   {
/* 656 */     if (points == null)
/*     */     {
/* 658 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 661 */     Iterator<? extends Vec4> iter = points.iterator();
/* 662 */     if (!iter.hasNext())
/*     */     {
/* 664 */       return false;
/*     */     }
/*     */     
/* 667 */     Vec4 firstPoint = (Vec4)iter.next();
/* 668 */     Vec4 lastPoint = null;
/*     */     
/* 670 */     while (iter.hasNext())
/*     */     {
/* 672 */       lastPoint = (Vec4)iter.next();
/*     */     }
/*     */     
/* 675 */     return (lastPoint != null) && (lastPoint.x == firstPoint.x) && (lastPoint.y == firstPoint.y);
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
/*     */   public static boolean computeCircleThroughPoints(Vec4 p0, Vec4 p1, Vec4 p2, Vec4[] centerOut, Vec4[] axisOut, double[] radiusOut)
/*     */   {
/* 701 */     if ((p0 == null) || (p1 == null) || (p2 == null))
/*     */     {
/* 703 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 706 */     Vec4 v0 = p1.subtract3(p0);
/* 707 */     Vec4 v1 = p2.subtract3(p1);
/* 708 */     Vec4 v2 = p2.subtract3(p0);
/*     */     
/* 710 */     double d0 = v0.dot3(v2);
/* 711 */     double d1 = -v0.dot3(v1);
/* 712 */     double d2 = v1.dot3(v2);
/*     */     
/* 714 */     double t0 = d1 + d2;
/* 715 */     double t1 = d0 + d2;
/* 716 */     double t2 = d0 + d1;
/*     */     
/* 718 */     double e0 = d0 * t0;
/* 719 */     double e1 = d1 * t1;
/* 720 */     double e2 = d2 * t2;
/*     */     
/* 722 */     double max_e = Math.max(Math.max(e0, e1), e2);
/* 723 */     double min_e = Math.min(Math.min(e0, e1), e2);
/*     */     
/* 725 */     double E = e0 + e1 + e2;
/*     */     
/* 727 */     double tolerance = 1.0E-6D;
/* 728 */     if (Math.abs(E) <= tolerance * (max_e - min_e)) {
/* 729 */       return false;
/*     */     }
/* 731 */     double radiusSquared = 0.5D * t0 * t1 * t2 / E;
/*     */     
/* 733 */     if (radiusSquared < 0.0D) {
/* 734 */       return false;
/*     */     }
/* 736 */     double radius = Math.sqrt(radiusSquared);
/*     */     
/* 738 */     Vec4 center = p0.multiply3(e0 / E);
/* 739 */     center = center.add3(p1.multiply3(e1 / E));
/* 740 */     center = center.add3(p2.multiply3(e2 / E));
/*     */     
/* 742 */     Vec4 axis = v2.cross3(v0);
/* 743 */     axis = axis.normalize3();
/*     */     
/* 745 */     if (centerOut != null)
/* 746 */       centerOut[0] = center;
/* 747 */     if (axisOut != null)
/* 748 */       axisOut[0] = axis;
/* 749 */     if (radiusOut != null)
/* 750 */       radiusOut[0] = radius;
/* 751 */     return true;
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
/*     */   public static Intersection[] polytopeIntersect(Line line, Plane[] planes)
/*     */   {
/* 773 */     if (line == null)
/*     */     {
/* 775 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 784 */     double fMax = -1.7976931348623157E308D;
/* 785 */     double bMin = Double.MAX_VALUE;
/* 786 */     boolean isTangent = false;
/*     */     
/* 788 */     Vec4 u = line.getDirection();
/* 789 */     Vec4 p = line.getOrigin();
/*     */     
/* 791 */     for (Plane plane : planes)
/*     */     {
/* 793 */       Vec4 n = plane.getNormal();
/* 794 */       double d = -plane.getDistance();
/*     */       
/* 796 */       double s = u.dot3(n);
/* 797 */       if (s == 0.0D)
/*     */       {
/* 799 */         double pdn = p.dot3(n);
/* 800 */         if (pdn > d) {
/* 801 */           return null;
/*     */         }
/*     */         
/* 804 */         if (pdn == d) {
/* 805 */           isTangent = true;
/*     */         }
/*     */         
/*     */       }
/*     */       else
/*     */       {
/* 811 */         double a = (d - p.dot3(n)) / s;
/* 812 */         if (u.dot3(n) < 0.0D)
/*     */         {
/* 814 */           if (a > fMax)
/*     */           {
/* 816 */             if (a > bMin)
/* 817 */               return null;
/* 818 */             fMax = a;
/*     */           }
/*     */           
/*     */ 
/*     */         }
/* 823 */         else if (a < bMin)
/*     */         {
/* 825 */           if ((a < 0.0D) || (a < fMax))
/* 826 */             return null;
/* 827 */           bMin = a;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 833 */     if (fMax >= 0.0D) {
/* 834 */       return new Intersection[] { new Intersection(p.add3(u.multiply3(fMax)), isTangent), new Intersection(p.add3(u.multiply3(bMin)), isTangent) };
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 840 */     return new Intersection[] { new Intersection(p.add3(u.multiply3(bMin)), isTangent) };
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/math/Math3d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */