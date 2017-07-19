/*      */ package thaumcraft.client.lib.math;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ 
/*    5 */ public class Vec4 { public static final Vec4 INFINITY = new Vec4(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D);
/*      */   
/*    7 */   public static final Vec4 ZERO = new Vec4(0.0D, 0.0D, 0.0D, 1.0D);
/*    8 */   public static final Vec4 ONE = new Vec4(1.0D, 1.0D, 1.0D, 1.0D);
/*    9 */   public static final Vec4 UNIT_X = new Vec4(1.0D, 0.0D, 0.0D, 0.0D);
/*   10 */   public static final Vec4 UNIT_NEGATIVE_X = new Vec4(-1.0D, 0.0D, 0.0D, 0.0D);
/*   11 */   public static final Vec4 UNIT_Y = new Vec4(0.0D, 1.0D, 0.0D, 0.0D);
/*   12 */   public static final Vec4 UNIT_NEGATIVE_Y = new Vec4(0.0D, -1.0D, 0.0D, 0.0D);
/*   13 */   public static final Vec4 UNIT_Z = new Vec4(0.0D, 0.0D, 1.0D, 0.0D);
/*   14 */   public static final Vec4 UNIT_NEGATIVE_Z = new Vec4(0.0D, 0.0D, -1.0D, 0.0D);
/*   15 */   public static final Vec4 UNIT_W = new Vec4(0.0D, 0.0D, 0.0D, 1.0D);
/*   16 */   public static final Vec4 UNIT_NEGATIVE_W = new Vec4(0.0D, 0.0D, 0.0D, -1.0D);
/*      */   
/*      */   public final double x;
/*      */   
/*      */   public final double y;
/*      */   
/*      */   public final double z;
/*      */   
/*      */   public final double w;
/*      */   private static final double DEFAULT_W = 1.0D;
/*      */   private int hashCode;
/*      */   
/*      */   public Vec4(double value)
/*      */   {
/*   30 */     this(value, value, value);
/*      */   }
/*      */   
/*      */   public Vec4(double x, double y)
/*      */   {
/*   35 */     this(x, y, 0.0D, 1.0D);
/*      */   }
/*      */   
/*      */   public Vec4(double x, double y, double z)
/*      */   {
/*   40 */     this(x, y, z, 1.0D);
/*      */   }
/*      */   
/*      */   public Vec4(double x, double y, double z, double w)
/*      */   {
/*   45 */     this.x = x;
/*   46 */     this.y = y;
/*   47 */     this.z = z;
/*   48 */     this.w = w;
/*      */   }
/*      */   
/*      */   public final boolean equals(Object obj)
/*      */   {
/*   53 */     if (this == obj)
/*   54 */       return true;
/*   55 */     if ((obj == null) || (obj.getClass() != getClass())) {
/*   56 */       return false;
/*      */     }
/*   58 */     Vec4 that = (Vec4)obj;
/*   59 */     return (this.x == that.x) && (this.y == that.y) && (this.z == that.z) && (this.w == that.w);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final int hashCode()
/*      */   {
/*   67 */     if (this.hashCode == 0)
/*      */     {
/*      */ 
/*      */ 
/*   71 */       long tmp = Double.doubleToLongBits(this.x);
/*   72 */       int result = (int)(tmp ^ tmp >>> 32);
/*   73 */       tmp = Double.doubleToLongBits(this.y);
/*   74 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*   75 */       tmp = Double.doubleToLongBits(this.z);
/*   76 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*   77 */       tmp = Double.doubleToLongBits(this.w);
/*   78 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*   79 */       this.hashCode = result;
/*      */     }
/*   81 */     return this.hashCode;
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
/*      */   public static Vec4 fromDoubleArray(double[] array, int offset, int length)
/*      */   {
/*  103 */     if (array == null)
/*      */     {
/*  105 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  108 */     if (offset < 0)
/*      */     {
/*  110 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  113 */     if (length < 1)
/*      */     {
/*  115 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  118 */     if (array.length < offset + length)
/*      */     {
/*  120 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  123 */     if (length == 1) {
/*  124 */       return new Vec4(array[offset], 0.0D);
/*      */     }
/*  126 */     if (length == 2) {
/*  127 */       return new Vec4(array[offset], array[(offset + 1)]);
/*      */     }
/*  129 */     if (length == 3) {
/*  130 */       return new Vec4(array[offset], array[(offset + 1)], array[(offset + 2)]);
/*      */     }
/*  132 */     return new Vec4(array[offset], array[(offset + 1)], array[(offset + 2)], array[(offset + 3)]);
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
/*      */   public static Vec4 fromFloatArray(float[] array, int offset, int length)
/*      */   {
/*  154 */     if (array == null)
/*      */     {
/*  156 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  159 */     if (offset < 0)
/*      */     {
/*  161 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  164 */     if (length < 1)
/*      */     {
/*  166 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  169 */     if (array.length < offset + length)
/*      */     {
/*  171 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  174 */     if (length == 2) {
/*  175 */       return new Vec4(array[offset], array[(offset + 1)], 0.0D);
/*      */     }
/*  177 */     if (length == 3) {
/*  178 */       return new Vec4(array[offset], array[(offset + 1)], array[(offset + 2)]);
/*      */     }
/*  180 */     return new Vec4(array[offset], array[(offset + 1)], array[(offset + 2)], array[(offset + 3)]);
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
/*      */   public static Vec4 fromArray2(double[] array, int offset)
/*      */   {
/*  199 */     if (array == null)
/*      */     {
/*  201 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  204 */     return fromDoubleArray(array, offset, 2);
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
/*      */   public static Vec4 fromArray3(double[] array, int offset)
/*      */   {
/*  224 */     if (array == null)
/*      */     {
/*  226 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  229 */     return fromDoubleArray(array, offset, 3);
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
/*      */   public static Vec4 fromArray4(double[] array, int offset)
/*      */   {
/*  250 */     if (array == null)
/*      */     {
/*  252 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  255 */     return fromDoubleArray(array, offset, 4);
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
/*      */   public final double[] toDoubleArray(double[] array, int offset, int length)
/*      */   {
/*  278 */     if (array == null)
/*      */     {
/*  280 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  283 */     if (offset < 0)
/*      */     {
/*  285 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  288 */     if (length < 1)
/*      */     {
/*  290 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  293 */     if (array.length < offset + length)
/*      */     {
/*  295 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  298 */     array[offset] = this.x;
/*      */     
/*  300 */     if (length > 1)
/*  301 */       array[(offset + 1)] = this.y;
/*  302 */     if (length > 2)
/*  303 */       array[(offset + 2)] = this.z;
/*  304 */     if (length > 3) {
/*  305 */       array[(offset + 3)] = this.w;
/*      */     }
/*  307 */     return array;
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
/*      */   public final float[] toFloatArray(float[] array, int offset, int length)
/*      */   {
/*  330 */     if (array == null)
/*      */     {
/*  332 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  335 */     if (offset < 0)
/*      */     {
/*  337 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  340 */     if (length < 1)
/*      */     {
/*  342 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  345 */     if (array.length < offset + length)
/*      */     {
/*  347 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  350 */     array[offset] = ((float)this.x);
/*  351 */     array[(offset + 1)] = ((float)this.y);
/*      */     
/*  353 */     if (length > 2)
/*  354 */       array[(offset + 2)] = ((float)this.z);
/*  355 */     if (length > 3) {
/*  356 */       array[(offset + 3)] = ((float)this.w);
/*      */     }
/*  358 */     return array;
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
/*      */   public final double[] toArray2(double[] array, int offset)
/*      */   {
/*  377 */     if (array == null)
/*      */     {
/*  379 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  382 */     return toDoubleArray(array, offset, 2);
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
/*      */   public final double[] toArray3(double[] array, int offset)
/*      */   {
/*  401 */     if (array == null)
/*      */     {
/*  403 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  406 */     return toDoubleArray(array, offset, 3);
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
/*      */   public final double[] toArray4(double[] array, int offset)
/*      */   {
/*  426 */     if (array == null)
/*      */     {
/*  428 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  431 */     return toDoubleArray(array, offset, 4);
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
/*      */   public Vec4 toHomogeneousPoint3()
/*      */   {
/*  450 */     if (this.w == 1.0D) {
/*  451 */       return this;
/*      */     }
/*  453 */     return new Vec4(this.x, this.y, this.z, 1.0D);
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
/*      */   public Vec4 toHomogeneousDirection3()
/*      */   {
/*  472 */     if (this.w == 0.0D) {
/*  473 */       return this;
/*      */     }
/*  475 */     return new Vec4(this.x, this.y, this.z, 0.0D);
/*      */   }
/*      */   
/*      */   public final String toString()
/*      */   {
/*  480 */     StringBuilder sb = new StringBuilder();
/*  481 */     sb.append("(");
/*  482 */     sb.append(this.x).append(", ");
/*  483 */     sb.append(this.y).append(", ");
/*  484 */     sb.append(this.z).append(", ");
/*  485 */     sb.append(this.w);
/*  486 */     sb.append(")");
/*  487 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public final double getX()
/*      */   {
/*  492 */     return this.x;
/*      */   }
/*      */   
/*      */   public final double getY()
/*      */   {
/*  497 */     return this.y;
/*      */   }
/*      */   
/*      */   public final double getZ()
/*      */   {
/*  502 */     return this.z;
/*      */   }
/*      */   
/*      */   public final double getW()
/*      */   {
/*  507 */     return this.w;
/*      */   }
/*      */   
/*      */   public final double x()
/*      */   {
/*  512 */     return this.x;
/*      */   }
/*      */   
/*      */   public final double y()
/*      */   {
/*  517 */     return this.y;
/*      */   }
/*      */   
/*      */   public final double z()
/*      */   {
/*  522 */     return this.z;
/*      */   }
/*      */   
/*      */   public final double w()
/*      */   {
/*  527 */     return this.w;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Vec4 fromLine3(Vec4 origin, double t, Vec4 direction)
/*      */   {
/*  536 */     if ((origin == null) || (direction == null))
/*      */     {
/*  538 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  541 */     return new Vec4(origin.x + direction.x * t, origin.y + direction.y * t, origin.z + direction.z * t);
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
/*      */   public final Vec4 add3(Vec4 vec4)
/*      */   {
/*  582 */     if (vec4 == null)
/*      */     {
/*  584 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  587 */     return new Vec4(this.x + vec4.x, this.y + vec4.y, this.z + vec4.z, this.w);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Vec4 add3(double x, double y, double z)
/*      */   {
/*  596 */     return new Vec4(this.x + x, this.y + y, this.z + z, this.w);
/*      */   }
/*      */   
/*      */   public final Vec4 subtract3(Vec4 vec4)
/*      */   {
/*  601 */     if (vec4 == null)
/*      */     {
/*  603 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  606 */     return new Vec4(this.x - vec4.x, this.y - vec4.y, this.z - vec4.z, this.w);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Vec4 subtract3(double x, double y, double z)
/*      */   {
/*  615 */     return new Vec4(this.x - x, this.y - y, this.z - z, this.w);
/*      */   }
/*      */   
/*      */   public final Vec4 multiply3(double value)
/*      */   {
/*  620 */     return new Vec4(this.x * value, this.y * value, this.z * value, this.w);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Vec4 multiply3(Vec4 vec4)
/*      */   {
/*  629 */     if (vec4 == null)
/*      */     {
/*  631 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  634 */     return new Vec4(this.x * vec4.x, this.y * vec4.y, this.z * vec4.z, this.w);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Vec4 divide3(double value)
/*      */   {
/*  643 */     if (value == 0.0D)
/*      */     {
/*  645 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  648 */     return new Vec4(this.x / value, this.y / value, this.z / value, this.w);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Vec4 divide3(Vec4 vec4)
/*      */   {
/*  657 */     if (vec4 == null)
/*      */     {
/*  659 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  662 */     return new Vec4(this.x / vec4.x, this.y / vec4.y, this.z / vec4.z, this.w);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Vec4 getNegative3()
/*      */   {
/*  671 */     return new Vec4(0.0D - this.x, 0.0D - this.y, 0.0D - this.z, this.w);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Vec4 getAbs3()
/*      */   {
/*  680 */     return new Vec4(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final double getLength3()
/*      */   {
/*  689 */     return Math.sqrt(getLengthSquared3());
/*      */   }
/*      */   
/*      */   public final double getLengthSquared3()
/*      */   {
/*  694 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final Vec4 normalize3()
/*      */   {
/*  701 */     double length = getLength3();
/*      */     
/*  703 */     if (length == 0.0D)
/*      */     {
/*  705 */       return this;
/*      */     }
/*      */     
/*      */ 
/*  709 */     return new Vec4(this.x / length, this.y / length, this.z / length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final double distanceTo2(Vec4 vec4)
/*      */   {
/*  718 */     if (vec4 == null)
/*      */     {
/*  720 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  723 */     double dx = vec4.x - this.x;
/*  724 */     double dy = vec4.y - this.y;
/*  725 */     return Math.sqrt(dx * dx + dy * dy);
/*      */   }
/*      */   
/*      */   public final double distanceTo3(Vec4 vec4)
/*      */   {
/*  730 */     if (vec4 == null)
/*      */     {
/*  732 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  735 */     return Math.sqrt(distanceToSquared3(vec4));
/*      */   }
/*      */   
/*      */   public final double distanceToSquared3(Vec4 vec4)
/*      */   {
/*  740 */     if (vec4 == null)
/*      */     {
/*  742 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*      */ 
/*  746 */     double result = 0.0D;
/*  747 */     double tmp = this.x - vec4.x;
/*  748 */     result += tmp * tmp;
/*  749 */     tmp = this.y - vec4.y;
/*  750 */     result += tmp * tmp;
/*  751 */     tmp = this.z - vec4.z;
/*  752 */     result += tmp * tmp;
/*  753 */     return result;
/*      */   }
/*      */   
/*      */   public final double dot3(Vec4 vec4)
/*      */   {
/*  758 */     if (vec4 == null)
/*      */     {
/*  760 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  763 */     return this.x * vec4.x + this.y * vec4.y + this.z * vec4.z;
/*      */   }
/*      */   
/*      */   public final double dot4(Vec4 vec4)
/*      */   {
/*  768 */     if (vec4 == null)
/*      */     {
/*  770 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  773 */     return this.x * vec4.x + this.y * vec4.y + this.z * vec4.z + this.w * vec4.w;
/*      */   }
/*      */   
/*      */   public final double dotSelf3()
/*      */   {
/*  778 */     return dot3(this);
/*      */   }
/*      */   
/*      */   public final double dotSelf4()
/*      */   {
/*  783 */     return dot4(this);
/*      */   }
/*      */   
/*      */   public final Vec4 cross3(Vec4 vec4)
/*      */   {
/*  788 */     if (vec4 == null)
/*      */     {
/*  790 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  793 */     return new Vec4(this.y * vec4.z - this.z * vec4.y, this.z * vec4.x - this.x * vec4.z, this.x * vec4.y - this.y * vec4.x);
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
/*      */   public final Vec4 projectOnto3(Vec4 vec4)
/*      */   {
/*  864 */     if (vec4 == null)
/*      */     {
/*  866 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  869 */     double dot = dot3(vec4);
/*  870 */     double length = vec4.getLength3();
/*      */     
/*  872 */     if ((length != 0.0D) && (length != 1.0D))
/*  873 */       dot /= length * length;
/*  874 */     return vec4.multiply3(dot);
/*      */   }
/*      */   
/*      */   public final Vec4 perpendicularTo3(Vec4 vec4)
/*      */   {
/*  879 */     if (vec4 == null)
/*      */     {
/*  881 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  884 */     return subtract3(projectOnto3(vec4));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vec4[] perpendicularVectors()
/*      */   {
/*  896 */     Vec4 v = this;
/*  897 */     Vec4 v1 = (v.y <= v.x) && (v.y <= v.z) ? UNIT_Y : (v.x <= v.y) && (v.x <= v.z) ? UNIT_X : UNIT_Z;
/*  898 */     Vec4 va = v.cross3(v1).normalize3();
/*  899 */     Vec4 vb = v.cross3(va).normalize3();
/*      */     
/*  901 */     return new Vec4[] { va, vb };
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
/*      */   public static Vec4 min3(Vec4 value1, Vec4 value2)
/*      */   {
/*  950 */     if ((value1 == null) || (value2 == null))
/*      */     {
/*  952 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  955 */     return new Vec4(value1.x < value2.x ? value1.x : value2.x, value1.y < value2.y ? value1.y : value2.y, value1.z < value2.z ? value1.z : value2.z);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Vec4 max3(Vec4 value1, Vec4 value2)
/*      */   {
/*  963 */     if ((value1 == null) || (value2 == null))
/*      */     {
/*  965 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  968 */     return new Vec4(value1.x > value2.x ? value1.x : value2.x, value1.y > value2.y ? value1.y : value2.y, value1.z > value2.z ? value1.z : value2.z);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Vec4 clamp3(Vec4 vec4, double min, double max)
/*      */   {
/*  976 */     if (vec4 == null)
/*      */     {
/*  978 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  981 */     return new Vec4(vec4.x > max ? max : vec4.x < min ? min : vec4.x, vec4.y > max ? max : vec4.y < min ? min : vec4.y, vec4.z > max ? max : vec4.z < min ? min : vec4.z);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Vec4 mix3(double amount, Vec4 value1, Vec4 value2)
/*      */   {
/*  989 */     if ((value1 == null) || (value2 == null))
/*      */     {
/*  991 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  994 */     if (amount < 0.0D)
/*  995 */       return value1;
/*  996 */     if (amount > 1.0D) {
/*  997 */       return value2;
/*      */     }
/*  999 */     double t1 = 1.0D - amount;
/* 1000 */     return new Vec4(value1.x * t1 + value2.x * amount, value1.y * t1 + value2.y * amount, value1.z * t1 + value2.z * amount);
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
/*      */   public static Vec4 computeAveragePoint(Iterable<? extends Vec4> points)
/*      */   {
/* 1019 */     if (points == null)
/*      */     {
/* 1021 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1024 */     int count = 0;
/* 1025 */     double x = 0.0D;
/* 1026 */     double y = 0.0D;
/* 1027 */     double z = 0.0D;
/* 1028 */     double w = 0.0D;
/*      */     
/* 1030 */     for (Vec4 vec : points)
/*      */     {
/* 1032 */       if (vec != null)
/*      */       {
/*      */ 
/* 1035 */         count++;
/* 1036 */         x += vec.x;
/* 1037 */         y += vec.y;
/* 1038 */         z += vec.z;
/* 1039 */         w += vec.w;
/*      */       }
/*      */     }
/* 1042 */     if (count == 0) {
/* 1043 */       return null;
/*      */     }
/* 1045 */     return new Vec4(x / count, y / count, z / count, w / count);
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
/*      */   public static double getAverageDistance(Iterable<? extends Vec4> points)
/*      */   {
/* 1101 */     if (points == null)
/*      */     {
/* 1103 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1106 */     double totalDistance = 0.0D;
/* 1107 */     int count = 0;
/*      */     
/* 1109 */     for (Iterator i$ = points.iterator(); i$.hasNext();) { p1 = (Vec4)i$.next();
/*      */       
/* 1111 */       for (Vec4 p2 : points)
/*      */       {
/* 1113 */         if (p1 != p2)
/*      */         {
/* 1115 */           double d = p1.distanceTo3(p2);
/* 1116 */           totalDistance += d;
/* 1117 */           count++;
/*      */         }
/*      */       }
/*      */     }
/*      */     Vec4 p1;
/* 1122 */     return count == 0 ? 0.0D : totalDistance / count;
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
/*      */   public static Vec4[] computeExtrema(Vec4[] points)
/*      */   {
/* 1139 */     if (points == null)
/*      */     {
/* 1141 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1144 */     if (points.length == 0) {
/* 1145 */       return null;
/*      */     }
/* 1147 */     double xmin = points[0].x;
/* 1148 */     double ymin = points[0].y;
/* 1149 */     double zmin = points[0].z;
/* 1150 */     double xmax = xmin;
/* 1151 */     double ymax = ymin;
/* 1152 */     double zmax = zmin;
/*      */     
/* 1154 */     for (int i = 1; i < points.length; i++)
/*      */     {
/* 1156 */       double x = points[i].x;
/* 1157 */       if (x > xmax)
/*      */       {
/* 1159 */         xmax = x;
/*      */       }
/* 1161 */       else if (x < xmin)
/*      */       {
/* 1163 */         xmin = x;
/*      */       }
/*      */       
/* 1166 */       double y = points[i].y;
/* 1167 */       if (y > ymax)
/*      */       {
/* 1169 */         ymax = y;
/*      */       }
/* 1171 */       else if (y < ymin)
/*      */       {
/* 1173 */         ymin = y;
/*      */       }
/*      */       
/* 1176 */       double z = points[i].z;
/* 1177 */       if (z > zmax)
/*      */       {
/* 1179 */         zmax = z;
/*      */       }
/* 1181 */       else if (z < zmin)
/*      */       {
/* 1183 */         zmin = z;
/*      */       }
/*      */     }
/*      */     
/* 1187 */     return new Vec4[] { new Vec4(xmin, ymin, zmin), new Vec4(xmax, ymax, zmax) };
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
/*      */   public static boolean areColinear(Vec4 a, Vec4 b, Vec4 c)
/*      */   {
/* 1256 */     if ((a == null) || (b == null) || (c == null))
/*      */     {
/* 1258 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1261 */     Vec4 ab = b.subtract3(a).normalize3();
/* 1262 */     Vec4 bc = c.subtract3(b).normalize3();
/*      */     
/* 1264 */     return Math.abs(ab.dot3(bc)) > 0.999D;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/math/Vec4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */