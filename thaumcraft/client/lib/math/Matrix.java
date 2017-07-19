/*      */ package thaumcraft.client.lib.math;
/*      */ 
/*      */ public class Matrix
/*      */ {
/*    5 */   public static final Matrix IDENTITY = new Matrix(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, true);
/*      */   
/*      */   public final double m11;
/*      */   
/*      */   public final double m12;
/*      */   
/*      */   public final double m13;
/*      */   
/*      */   public final double m14;
/*      */   
/*      */   public final double m21;
/*      */   
/*      */   public final double m22;
/*      */   
/*      */   public final double m23;
/*      */   
/*      */   public final double m24;
/*      */   
/*      */   public final double m31;
/*      */   
/*      */   public final double m32;
/*      */   
/*      */   public final double m33;
/*      */   
/*      */   public final double m34;
/*      */   
/*      */   public final double m41;
/*      */   
/*      */   public final double m42;
/*      */   
/*      */   public final double m43;
/*      */   
/*      */   public final double m44;
/*      */   protected static final double EPSILON = 1.0E-6D;
/*      */   protected static final double NEAR_ZERO_THRESHOLD = 1.0E-8D;
/*      */   private static final int NUM_ELEMENTS = 16;
/*      */   private final boolean isOrthonormalTransform;
/*      */   private int hashCode;
/*      */   
/*      */   public Matrix(double value)
/*      */   {
/*   46 */     this(value, 0.0D, 0.0D, 0.0D, 0.0D, value, 0.0D, 0.0D, 0.0D, 0.0D, value, 0.0D, 0.0D, 0.0D, 0.0D, value);
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
/*      */   public Matrix(double m11, double m12, double m13, double m14, double m21, double m22, double m23, double m24, double m31, double m32, double m33, double m34, double m41, double m42, double m43, double m44)
/*      */   {
/*   59 */     this(m11, m12, m13, m14, m21, m22, m23, m24, m31, m32, m33, m34, m41, m42, m43, m44, false);
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
/*      */   Matrix(double m11, double m12, double m13, double m14, double m21, double m22, double m23, double m24, double m31, double m32, double m33, double m34, double m41, double m42, double m43, double m44, boolean isOrthonormalTransform)
/*      */   {
/*   74 */     this.m11 = m11;
/*   75 */     this.m12 = m12;
/*   76 */     this.m13 = m13;
/*   77 */     this.m14 = m14;
/*   78 */     this.m21 = m21;
/*   79 */     this.m22 = m22;
/*   80 */     this.m23 = m23;
/*   81 */     this.m24 = m24;
/*   82 */     this.m31 = m31;
/*   83 */     this.m32 = m32;
/*   84 */     this.m33 = m33;
/*   85 */     this.m34 = m34;
/*   86 */     this.m41 = m41;
/*   87 */     this.m42 = m42;
/*   88 */     this.m43 = m43;
/*   89 */     this.m44 = m44;
/*   90 */     this.isOrthonormalTransform = isOrthonormalTransform;
/*      */   }
/*      */   
/*      */   public final boolean equals(Object obj)
/*      */   {
/*   95 */     if (this == obj)
/*   96 */       return true;
/*   97 */     if ((obj == null) || (obj.getClass() != getClass())) {
/*   98 */       return false;
/*      */     }
/*  100 */     Matrix that = (Matrix)obj;
/*  101 */     return (this.m11 == that.m11) && (this.m12 == that.m12) && (this.m13 == that.m13) && (this.m14 == that.m14) && (this.m21 == that.m21) && (this.m22 == that.m22) && (this.m23 == that.m23) && (this.m24 == that.m24) && (this.m31 == that.m31) && (this.m32 == that.m32) && (this.m33 == that.m33) && (this.m34 == that.m34) && (this.m41 == that.m41) && (this.m42 == that.m42) && (this.m43 == that.m43) && (this.m44 == that.m44);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final int hashCode()
/*      */   {
/*  109 */     if (this.hashCode == 0)
/*      */     {
/*      */ 
/*      */ 
/*  113 */       long tmp = Double.doubleToLongBits(this.m11);
/*  114 */       int result = (int)(tmp ^ tmp >>> 32);
/*  115 */       tmp = Double.doubleToLongBits(this.m12);
/*  116 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  117 */       tmp = Double.doubleToLongBits(this.m13);
/*  118 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  119 */       tmp = Double.doubleToLongBits(this.m14);
/*  120 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  121 */       tmp = Double.doubleToLongBits(this.m21);
/*  122 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  123 */       tmp = Double.doubleToLongBits(this.m22);
/*  124 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  125 */       tmp = Double.doubleToLongBits(this.m23);
/*  126 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  127 */       tmp = Double.doubleToLongBits(this.m24);
/*  128 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  129 */       tmp = Double.doubleToLongBits(this.m31);
/*  130 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  131 */       tmp = Double.doubleToLongBits(this.m32);
/*  132 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  133 */       tmp = Double.doubleToLongBits(this.m33);
/*  134 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  135 */       tmp = Double.doubleToLongBits(this.m34);
/*  136 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  137 */       tmp = Double.doubleToLongBits(this.m41);
/*  138 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  139 */       tmp = Double.doubleToLongBits(this.m42);
/*  140 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  141 */       tmp = Double.doubleToLongBits(this.m43);
/*  142 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  143 */       tmp = Double.doubleToLongBits(this.m44);
/*  144 */       result = 29 * result + (int)(tmp ^ tmp >>> 32);
/*  145 */       this.hashCode = result;
/*      */     }
/*  147 */     return this.hashCode;
/*      */   }
/*      */   
/*      */   public static Matrix fromArray(double[] compArray, int offset, boolean rowMajor)
/*      */   {
/*  152 */     if (compArray == null)
/*      */     {
/*  154 */       throw new IllegalArgumentException();
/*      */     }
/*  156 */     if (compArray.length - offset < 16)
/*      */     {
/*  158 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  161 */     if (rowMajor)
/*      */     {
/*      */ 
/*  164 */       return new Matrix(compArray[(0 + offset)], compArray[(1 + offset)], compArray[(2 + offset)], compArray[(3 + offset)], compArray[(4 + offset)], compArray[(5 + offset)], compArray[(6 + offset)], compArray[(7 + offset)], compArray[(8 + offset)], compArray[(9 + offset)], compArray[(10 + offset)], compArray[(11 + offset)], compArray[(12 + offset)], compArray[(13 + offset)], compArray[(14 + offset)], compArray[(15 + offset)]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  189 */     return new Matrix(compArray[(0 + offset)], compArray[(4 + offset)], compArray[(8 + offset)], compArray[(12 + offset)], compArray[(1 + offset)], compArray[(5 + offset)], compArray[(9 + offset)], compArray[(13 + offset)], compArray[(2 + offset)], compArray[(6 + offset)], compArray[(10 + offset)], compArray[(14 + offset)], compArray[(3 + offset)], compArray[(7 + offset)], compArray[(11 + offset)], compArray[(15 + offset)]);
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
/*      */   public final double[] toArray(double[] compArray, int offset, boolean rowMajor)
/*      */   {
/*  215 */     if (compArray == null)
/*      */     {
/*  217 */       throw new IllegalArgumentException();
/*      */     }
/*  219 */     if (compArray.length - offset < 16)
/*      */     {
/*  221 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  224 */     if (rowMajor)
/*      */     {
/*      */ 
/*      */ 
/*  228 */       compArray[(0 + offset)] = this.m11;
/*  229 */       compArray[(1 + offset)] = this.m12;
/*  230 */       compArray[(2 + offset)] = this.m13;
/*  231 */       compArray[(3 + offset)] = this.m14;
/*      */       
/*  233 */       compArray[(4 + offset)] = this.m21;
/*  234 */       compArray[(5 + offset)] = this.m22;
/*  235 */       compArray[(6 + offset)] = this.m23;
/*  236 */       compArray[(7 + offset)] = this.m24;
/*      */       
/*  238 */       compArray[(8 + offset)] = this.m31;
/*  239 */       compArray[(9 + offset)] = this.m32;
/*  240 */       compArray[(10 + offset)] = this.m33;
/*  241 */       compArray[(11 + offset)] = this.m34;
/*      */       
/*  243 */       compArray[(12 + offset)] = this.m41;
/*  244 */       compArray[(13 + offset)] = this.m42;
/*  245 */       compArray[(14 + offset)] = this.m43;
/*  246 */       compArray[(15 + offset)] = this.m44;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  252 */       compArray[(0 + offset)] = this.m11;
/*  253 */       compArray[(4 + offset)] = this.m12;
/*  254 */       compArray[(8 + offset)] = this.m13;
/*  255 */       compArray[(12 + offset)] = this.m14;
/*      */       
/*  257 */       compArray[(1 + offset)] = this.m21;
/*  258 */       compArray[(5 + offset)] = this.m22;
/*  259 */       compArray[(9 + offset)] = this.m23;
/*  260 */       compArray[(13 + offset)] = this.m24;
/*      */       
/*  262 */       compArray[(2 + offset)] = this.m31;
/*  263 */       compArray[(6 + offset)] = this.m32;
/*  264 */       compArray[(10 + offset)] = this.m33;
/*  265 */       compArray[(14 + offset)] = this.m34;
/*      */       
/*  267 */       compArray[(3 + offset)] = this.m41;
/*  268 */       compArray[(7 + offset)] = this.m42;
/*  269 */       compArray[(11 + offset)] = this.m43;
/*  270 */       compArray[(15 + offset)] = this.m44;
/*      */     }
/*      */     
/*  273 */     return compArray;
/*      */   }
/*      */   
/*      */   public final String toString()
/*      */   {
/*  278 */     StringBuilder sb = new StringBuilder();
/*  279 */     sb.append("(");
/*  280 */     sb.append(this.m11).append(", ").append(this.m12).append(", ").append(this.m13).append(", ").append(this.m14);
/*  281 */     sb.append(", \r\n");
/*  282 */     sb.append(this.m21).append(", ").append(this.m22).append(", ").append(this.m23).append(", ").append(this.m24);
/*  283 */     sb.append(", \r\n");
/*  284 */     sb.append(this.m31).append(", ").append(this.m32).append(", ").append(this.m33).append(", ").append(this.m34);
/*  285 */     sb.append(", \r\n");
/*  286 */     sb.append(this.m41).append(", ").append(this.m42).append(", ").append(this.m43).append(", ").append(this.m44);
/*  287 */     sb.append(")");
/*  288 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public final double getM11()
/*      */   {
/*  293 */     return this.m11;
/*      */   }
/*      */   
/*      */   public final double getM12()
/*      */   {
/*  298 */     return this.m12;
/*      */   }
/*      */   
/*      */   public final double getM13()
/*      */   {
/*  303 */     return this.m13;
/*      */   }
/*      */   
/*      */   public final double getM14()
/*      */   {
/*  308 */     return this.m14;
/*      */   }
/*      */   
/*      */   public final double getM21()
/*      */   {
/*  313 */     return this.m21;
/*      */   }
/*      */   
/*      */   public final double getM22()
/*      */   {
/*  318 */     return this.m22;
/*      */   }
/*      */   
/*      */   public final double getM23()
/*      */   {
/*  323 */     return this.m23;
/*      */   }
/*      */   
/*      */   public final double getM24()
/*      */   {
/*  328 */     return this.m24;
/*      */   }
/*      */   
/*      */   public final double getM31()
/*      */   {
/*  333 */     return this.m31;
/*      */   }
/*      */   
/*      */   public final double getM32()
/*      */   {
/*  338 */     return this.m32;
/*      */   }
/*      */   
/*      */   public final double getM33()
/*      */   {
/*  343 */     return this.m33;
/*      */   }
/*      */   
/*      */   public final double getM34()
/*      */   {
/*  348 */     return this.m34;
/*      */   }
/*      */   
/*      */   public final double getM41()
/*      */   {
/*  353 */     return this.m41;
/*      */   }
/*      */   
/*      */   public final double getM42()
/*      */   {
/*  358 */     return this.m42;
/*      */   }
/*      */   
/*      */   public final double getM43()
/*      */   {
/*  363 */     return this.m43;
/*      */   }
/*      */   
/*      */   public final double getM44()
/*      */   {
/*  368 */     return this.m44;
/*      */   }
/*      */   
/*      */   public final double m11()
/*      */   {
/*  373 */     return this.m11;
/*      */   }
/*      */   
/*      */   public final double m12()
/*      */   {
/*  378 */     return this.m12;
/*      */   }
/*      */   
/*      */   public final double m13()
/*      */   {
/*  383 */     return this.m13;
/*      */   }
/*      */   
/*      */   public final double m14()
/*      */   {
/*  388 */     return this.m14;
/*      */   }
/*      */   
/*      */   public final double m21()
/*      */   {
/*  393 */     return this.m21;
/*      */   }
/*      */   
/*      */   public final double m22()
/*      */   {
/*  398 */     return this.m22;
/*      */   }
/*      */   
/*      */   public final double m23()
/*      */   {
/*  403 */     return this.m23;
/*      */   }
/*      */   
/*      */   public final double m24()
/*      */   {
/*  408 */     return this.m24;
/*      */   }
/*      */   
/*      */   public final double m31()
/*      */   {
/*  413 */     return this.m31;
/*      */   }
/*      */   
/*      */   public final double m32()
/*      */   {
/*  418 */     return this.m32;
/*      */   }
/*      */   
/*      */   public final double m33()
/*      */   {
/*  423 */     return this.m33;
/*      */   }
/*      */   
/*      */   public final double m34()
/*      */   {
/*  428 */     return this.m34;
/*      */   }
/*      */   
/*      */   public final double m41()
/*      */   {
/*  433 */     return this.m41;
/*      */   }
/*      */   
/*      */   public final double m42()
/*      */   {
/*  438 */     return this.m42;
/*      */   }
/*      */   
/*      */   public final double m43()
/*      */   {
/*  443 */     return this.m43;
/*      */   }
/*      */   
/*      */   public final double m44()
/*      */   {
/*  448 */     return this.m44;
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
/*      */   public static Matrix fromAxes(Vec4[] axes)
/*      */   {
/*  472 */     if (axes == null)
/*      */     {
/*  474 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  477 */     if (axes.length < 3)
/*      */     {
/*  479 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  482 */     if ((axes[0] == null) || (axes[1] == null) || (axes[2] == null))
/*      */     {
/*  484 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  487 */     Vec4 s = axes[0].normalize3();
/*  488 */     Vec4 f = s.cross3(axes[1]).normalize3();
/*  489 */     Vec4 u = f.cross3(s).normalize3();
/*      */     
/*  491 */     return new Matrix(s.x, u.x, f.x, 0.0D, s.y, u.y, f.y, 0.0D, s.z, u.z, f.z, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, true);
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
/*      */   private static Matrix fromQuaternion(double x, double y, double z, double w, boolean normalize)
/*      */   {
/*  587 */     if (normalize)
/*      */     {
/*  589 */       double length = Math.sqrt(x * x + y * y + z * z + w * w);
/*  590 */       if ((!isZero(length)) && (length != 1.0D))
/*      */       {
/*  592 */         x /= length;
/*  593 */         y /= length;
/*  594 */         z /= length;
/*  595 */         w /= length;
/*      */       }
/*      */     }
/*      */     
/*  599 */     return new Matrix(1.0D - 2.0D * y * y - 2.0D * z * z, 2.0D * x * y - 2.0D * z * w, 2.0D * x * z + 2.0D * y * w, 0.0D, 2.0D * x * y + 2.0D * z * w, 1.0D - 2.0D * x * x - 2.0D * z * z, 2.0D * y * z - 2.0D * x * w, 0.0D, 2.0D * x * z - 2.0D * y * w, 2.0D * y * z + 2.0D * x * w, 1.0D - 2.0D * x * x - 2.0D * y * y, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, true);
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
/*      */   public static Matrix fromScale(double scale)
/*      */   {
/*  705 */     return fromScale(scale, scale, scale);
/*      */   }
/*      */   
/*      */   public static Matrix fromScale(Vec4 scale)
/*      */   {
/*  710 */     if (scale == null)
/*      */     {
/*  712 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  715 */     return fromScale(scale.x, scale.y, scale.z);
/*      */   }
/*      */   
/*      */   public static Matrix fromScale(double scaleX, double scaleY, double scaleZ)
/*      */   {
/*  720 */     return new Matrix(scaleX, 0.0D, 0.0D, 0.0D, 0.0D, scaleY, 0.0D, 0.0D, 0.0D, 0.0D, scaleZ, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Matrix fromTranslation(Vec4 translation)
/*      */   {
/*  731 */     if (translation == null)
/*      */     {
/*  733 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  736 */     return fromTranslation(translation.x, translation.y, translation.z);
/*      */   }
/*      */   
/*      */   public static Matrix fromTranslation(double x, double y, double z)
/*      */   {
/*  741 */     return new Matrix(1.0D, 0.0D, 0.0D, x, 0.0D, 1.0D, 0.0D, y, 0.0D, 0.0D, 1.0D, z, 0.0D, 0.0D, 0.0D, 1.0D, true);
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
/*      */   public static Matrix fromLocalOrientation(Vec4 origin, Vec4[] axes)
/*      */   {
/*  797 */     if (origin == null)
/*      */     {
/*  799 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  802 */     if (axes == null)
/*      */     {
/*  804 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  807 */     if (axes.length < 3)
/*      */     {
/*  809 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  812 */     if ((axes[0] == null) || (axes[1] == null) || (axes[2] == null))
/*      */     {
/*  814 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  817 */     return fromTranslation(origin).multiply(fromAxes(axes));
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
/*      */   public static Matrix fromViewLookAt(Vec4 eye, Vec4 center, Vec4 up)
/*      */   {
/*  844 */     if ((eye == null) || (center == null) || (up == null))
/*      */     {
/*  846 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  849 */     if (eye.distanceTo3(center) <= 1.0E-6D)
/*      */     {
/*  851 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  854 */     Vec4 forward = center.subtract3(eye);
/*  855 */     Vec4 f = forward.normalize3();
/*      */     
/*  857 */     Vec4 s = f.cross3(up);
/*  858 */     s = s.normalize3();
/*      */     
/*  860 */     if (s.getLength3() <= 1.0E-6D)
/*      */     {
/*  862 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  865 */     Vec4 u = s.cross3(f);
/*  866 */     u = u.normalize3();
/*      */     
/*  868 */     Matrix mAxes = new Matrix(s.x, s.y, s.z, 0.0D, u.x, u.y, u.z, 0.0D, -f.x, -f.y, -f.z, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, true);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  874 */     Matrix mEye = fromTranslation(-eye.x, -eye.y, -eye.z);
/*      */     
/*  876 */     return mAxes.multiply(mEye);
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
/*      */   public static Matrix fromModelLookAt(Vec4 eye, Vec4 center, Vec4 up)
/*      */   {
/*  900 */     if ((eye == null) || (center == null) || (up == null))
/*      */     {
/*  902 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  905 */     if (eye.distanceTo3(center) <= 1.0E-6D)
/*      */     {
/*  907 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  910 */     Vec4 forward = center.subtract3(eye);
/*  911 */     Vec4 f = forward.normalize3();
/*      */     
/*  913 */     Vec4 s = up.cross3(f);
/*  914 */     s = s.normalize3();
/*      */     
/*  916 */     if (s.getLength3() <= 1.0E-6D)
/*      */     {
/*  918 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  921 */     Vec4 u = f.cross3(s);
/*  922 */     u = u.normalize3();
/*      */     
/*  924 */     Matrix mAxes = new Matrix(s.x, u.x, f.x, 0.0D, s.y, u.y, f.y, 0.0D, s.z, u.z, f.z, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, true);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  930 */     Matrix mEye = fromTranslation(eye.x, eye.y, eye.z);
/*      */     
/*  932 */     return mEye.multiply(mAxes);
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
/*      */   public static Matrix fromPerspective(double width, double height, double near, double far)
/*      */   {
/*  995 */     if (width <= 0.0D)
/*      */     {
/*  997 */       throw new IllegalArgumentException();
/*      */     }
/*  999 */     if (height <= 0.0D)
/*      */     {
/* 1001 */       throw new IllegalArgumentException();
/*      */     }
/* 1003 */     if (near <= 0.0D)
/*      */     {
/* 1005 */       throw new IllegalArgumentException();
/*      */     }
/* 1007 */     if (far <= 0.0D)
/*      */     {
/* 1009 */       throw new IllegalArgumentException();
/*      */     }
/* 1011 */     if (far <= near)
/*      */     {
/* 1013 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1016 */     return new Matrix(2.0D / width, 0.0D, 0.0D, 0.0D, 0.0D, 2.0D * near / height, 0.0D, 0.0D, 0.0D, 0.0D, -(far + near) / (far - near), -(2.0D * far * near) / (far - near), 0.0D, 0.0D, -1.0D, 0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Matrix fromOrthographic(double width, double height, double near, double far)
/*      */   {
/* 1025 */     if (width <= 0.0D)
/*      */     {
/* 1027 */       throw new IllegalArgumentException();
/*      */     }
/* 1029 */     if (height <= 0.0D)
/*      */     {
/* 1031 */       throw new IllegalArgumentException();
/*      */     }
/* 1033 */     if (near <= 0.0D)
/*      */     {
/* 1035 */       throw new IllegalArgumentException();
/*      */     }
/* 1037 */     if (far <= 0.0D)
/*      */     {
/* 1039 */       throw new IllegalArgumentException();
/*      */     }
/* 1041 */     if (far <= near)
/*      */     {
/* 1043 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1046 */     return new Matrix(2.0D / width, 0.0D, 0.0D, 0.0D, 0.0D, 2.0D / height, 0.0D, 0.0D, 0.0D, 0.0D, -2.0D / (far - near), -(far + near) / (far - near), 0.0D, 0.0D, 0.0D, 1.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Matrix fromOrthographic2D(double width, double height)
/*      */   {
/* 1055 */     if (width <= 0.0D)
/*      */     {
/* 1057 */       throw new IllegalArgumentException();
/*      */     }
/* 1059 */     if (height <= 0.0D)
/*      */     {
/* 1061 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1064 */     return new Matrix(2.0D / width, 0.0D, 0.0D, 0.0D, 0.0D, 2.0D / height, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Matrix fromCovarianceOfVertices(Iterable<? extends Vec4> points)
/*      */   {
/* 1547 */     if (points == null)
/*      */     {
/* 1549 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1552 */     Vec4 mean = Vec4.computeAveragePoint(points);
/* 1553 */     if (mean == null) {
/* 1554 */       return null;
/*      */     }
/* 1556 */     int count = 0;
/* 1557 */     double c11 = 0.0D;
/* 1558 */     double c22 = 0.0D;
/* 1559 */     double c33 = 0.0D;
/* 1560 */     double c12 = 0.0D;
/* 1561 */     double c13 = 0.0D;
/* 1562 */     double c23 = 0.0D;
/*      */     
/* 1564 */     for (Vec4 vec : points)
/*      */     {
/* 1566 */       if (vec != null)
/*      */       {
/*      */ 
/* 1569 */         count++;
/* 1570 */         c11 += (vec.x - mean.x) * (vec.x - mean.x);
/* 1571 */         c22 += (vec.y - mean.y) * (vec.y - mean.y);
/* 1572 */         c33 += (vec.z - mean.z) * (vec.z - mean.z);
/* 1573 */         c12 += (vec.x - mean.x) * (vec.y - mean.y);
/* 1574 */         c13 += (vec.x - mean.x) * (vec.z - mean.z);
/* 1575 */         c23 += (vec.y - mean.y) * (vec.z - mean.z);
/*      */       }
/*      */     }
/* 1578 */     if (count == 0) {
/* 1579 */       return null;
/*      */     }
/* 1581 */     return new Matrix(c11 / count, c12 / count, c13 / count, 0.0D, c12 / count, c22 / count, c23 / count, 0.0D, c13 / count, c23 / count, c33 / count, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
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
/*      */   public static void computeEigensystemFromSymmetricMatrix3(Matrix matrix, double[] outEigenvalues, Vec4[] outEigenvectors)
/*      */   {
/* 1687 */     if (matrix == null)
/*      */     {
/* 1689 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1692 */     if ((matrix.m12 != matrix.m21) || (matrix.m13 != matrix.m31) || (matrix.m23 != matrix.m32))
/*      */     {
/* 1694 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1700 */     double EPSILON = 1.0E-10D;
/* 1701 */     int MAX_SWEEPS = 32;
/*      */     
/*      */ 
/*      */ 
/* 1705 */     double m11 = matrix.m11;
/* 1706 */     double m12 = matrix.m12;
/* 1707 */     double m13 = matrix.m13;
/* 1708 */     double m22 = matrix.m22;
/* 1709 */     double m23 = matrix.m23;
/* 1710 */     double m33 = matrix.m33;
/*      */     
/* 1712 */     double[][] r = new double[3][3];
/* 1713 */     r[0][0] = (r[1][1] = r[2][2] = 1.0D);
/*      */     
/* 1715 */     for (int a = 0; a < 32; a++)
/*      */     {
/*      */ 
/* 1718 */       if ((Math.abs(m12) < 1.0E-10D) && (Math.abs(m13) < 1.0E-10D) && (Math.abs(m23) < 1.0E-10D)) {
/*      */         break;
/*      */       }
/*      */       
/* 1722 */       if (m12 != 0.0D)
/*      */       {
/* 1724 */         double u = (m22 - m11) * 0.5D / m12;
/* 1725 */         double u2 = u * u;
/* 1726 */         double u2p1 = u2 + 1.0D;
/* 1727 */         double t = u2p1 != u2 ? (u < 0.0D ? -1.0D : 1.0D) * (Math.sqrt(u2p1) - Math.abs(u)) : 0.5D / u;
/*      */         
/*      */ 
/* 1730 */         double c = 1.0D / Math.sqrt(t * t + 1.0D);
/* 1731 */         double s = c * t;
/*      */         
/* 1733 */         m11 -= t * m12;
/* 1734 */         m22 += t * m12;
/* 1735 */         m12 = 0.0D;
/*      */         
/* 1737 */         double temp = c * m13 - s * m23;
/* 1738 */         m23 = s * m13 + c * m23;
/* 1739 */         m13 = temp;
/*      */         
/* 1741 */         for (int i = 0; i < 3; i++)
/*      */         {
/* 1743 */           temp = c * r[i][0] - s * r[i][1];
/* 1744 */           r[i][1] = (s * r[i][0] + c * r[i][1]);
/* 1745 */           r[i][0] = temp;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1750 */       if (m13 != 0.0D)
/*      */       {
/* 1752 */         double u = (m33 - m11) * 0.5D / m13;
/* 1753 */         double u2 = u * u;
/* 1754 */         double u2p1 = u2 + 1.0D;
/* 1755 */         double t = u2p1 != u2 ? (u < 0.0D ? -1.0D : 1.0D) * (Math.sqrt(u2p1) - Math.abs(u)) : 0.5D / u;
/*      */         
/*      */ 
/* 1758 */         double c = 1.0D / Math.sqrt(t * t + 1.0D);
/* 1759 */         double s = c * t;
/*      */         
/* 1761 */         m11 -= t * m13;
/* 1762 */         m33 += t * m13;
/* 1763 */         m13 = 0.0D;
/*      */         
/* 1765 */         double temp = c * m12 - s * m23;
/* 1766 */         m23 = s * m12 + c * m23;
/* 1767 */         m12 = temp;
/*      */         
/* 1769 */         for (int i = 0; i < 3; i++)
/*      */         {
/* 1771 */           temp = c * r[i][0] - s * r[i][2];
/* 1772 */           r[i][2] = (s * r[i][0] + c * r[i][2]);
/* 1773 */           r[i][0] = temp;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1778 */       if (m23 != 0.0D)
/*      */       {
/* 1780 */         double u = (m33 - m22) * 0.5D / m23;
/* 1781 */         double u2 = u * u;
/* 1782 */         double u2p1 = u2 + 1.0D;
/* 1783 */         double t = u2p1 != u2 ? (u < 0.0D ? -1.0D : 1.0D) * (Math.sqrt(u2p1) - Math.abs(u)) : 0.5D / u;
/*      */         
/*      */ 
/* 1786 */         double c = 1.0D / Math.sqrt(t * t + 1.0D);
/* 1787 */         double s = c * t;
/*      */         
/* 1789 */         m22 -= t * m23;
/* 1790 */         m33 += t * m23;
/* 1791 */         m23 = 0.0D;
/*      */         
/* 1793 */         double temp = c * m12 - s * m13;
/* 1794 */         m13 = s * m12 + c * m13;
/* 1795 */         m12 = temp;
/*      */         
/* 1797 */         for (int i = 0; i < 3; i++)
/*      */         {
/* 1799 */           temp = c * r[i][1] - s * r[i][2];
/* 1800 */           r[i][2] = (s * r[i][1] + c * r[i][2]);
/* 1801 */           r[i][1] = temp;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1806 */     outEigenvalues[0] = m11;
/* 1807 */     outEigenvalues[1] = m22;
/* 1808 */     outEigenvalues[2] = m33;
/*      */     
/* 1810 */     outEigenvectors[0] = new Vec4(r[0][0], r[1][0], r[2][0]);
/* 1811 */     outEigenvectors[1] = new Vec4(r[0][1], r[1][1], r[2][1]);
/* 1812 */     outEigenvectors[2] = new Vec4(r[0][2], r[1][2], r[2][2]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Matrix add(Matrix matrix)
/*      */   {
/* 1821 */     if (matrix == null)
/*      */     {
/* 1823 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1826 */     return new Matrix(this.m11 + matrix.m11, this.m12 + matrix.m12, this.m13 + matrix.m13, this.m14 + matrix.m14, this.m21 + matrix.m21, this.m22 + matrix.m22, this.m23 + matrix.m23, this.m24 + matrix.m24, this.m31 + matrix.m31, this.m32 + matrix.m32, this.m33 + matrix.m33, this.m34 + matrix.m34, this.m41 + matrix.m41, this.m42 + matrix.m42, this.m43 + matrix.m43, this.m44 + matrix.m44);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Matrix subtract(Matrix matrix)
/*      */   {
/* 1835 */     if (matrix == null)
/*      */     {
/* 1837 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1840 */     return new Matrix(this.m11 - matrix.m11, this.m12 - matrix.m12, this.m13 - matrix.m13, this.m14 - matrix.m14, this.m21 - matrix.m21, this.m22 - matrix.m22, this.m23 - matrix.m23, this.m24 - matrix.m24, this.m31 - matrix.m31, this.m32 - matrix.m32, this.m33 - matrix.m33, this.m34 - matrix.m34, this.m41 - matrix.m41, this.m42 - matrix.m42, this.m43 - matrix.m43, this.m44 - matrix.m44);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Matrix multiplyComponents(double value)
/*      */   {
/* 1849 */     return new Matrix(this.m11 * value, this.m12 * value, this.m13 * value, this.m14 * value, this.m21 * value, this.m22 * value, this.m23 * value, this.m24 * value, this.m31 * value, this.m32 * value, this.m33 * value, this.m34 * value, this.m41 * value, this.m42 * value, this.m43 * value, this.m44 * value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Matrix multiply(Matrix matrix)
/*      */   {
/* 1858 */     if (matrix == null)
/*      */     {
/* 1860 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1863 */     return new Matrix(this.m11 * matrix.m11 + this.m12 * matrix.m21 + this.m13 * matrix.m31 + this.m14 * matrix.m41, this.m11 * matrix.m12 + this.m12 * matrix.m22 + this.m13 * matrix.m32 + this.m14 * matrix.m42, this.m11 * matrix.m13 + this.m12 * matrix.m23 + this.m13 * matrix.m33 + this.m14 * matrix.m43, this.m11 * matrix.m14 + this.m12 * matrix.m24 + this.m13 * matrix.m34 + this.m14 * matrix.m44, this.m21 * matrix.m11 + this.m22 * matrix.m21 + this.m23 * matrix.m31 + this.m24 * matrix.m41, this.m21 * matrix.m12 + this.m22 * matrix.m22 + this.m23 * matrix.m32 + this.m24 * matrix.m42, this.m21 * matrix.m13 + this.m22 * matrix.m23 + this.m23 * matrix.m33 + this.m24 * matrix.m43, this.m21 * matrix.m14 + this.m22 * matrix.m24 + this.m23 * matrix.m34 + this.m24 * matrix.m44, this.m31 * matrix.m11 + this.m32 * matrix.m21 + this.m33 * matrix.m31 + this.m34 * matrix.m41, this.m31 * matrix.m12 + this.m32 * matrix.m22 + this.m33 * matrix.m32 + this.m34 * matrix.m42, this.m31 * matrix.m13 + this.m32 * matrix.m23 + this.m33 * matrix.m33 + this.m34 * matrix.m43, this.m31 * matrix.m14 + this.m32 * matrix.m24 + this.m33 * matrix.m34 + this.m34 * matrix.m44, this.m41 * matrix.m11 + this.m42 * matrix.m21 + this.m43 * matrix.m31 + this.m44 * matrix.m41, this.m41 * matrix.m12 + this.m42 * matrix.m22 + this.m43 * matrix.m32 + this.m44 * matrix.m42, this.m41 * matrix.m13 + this.m42 * matrix.m23 + this.m43 * matrix.m33 + this.m44 * matrix.m43, this.m41 * matrix.m14 + this.m42 * matrix.m24 + this.m43 * matrix.m34 + this.m44 * matrix.m44, (this.isOrthonormalTransform) && (matrix.isOrthonormalTransform));
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
/*      */   public final Matrix divideComponents(double value)
/*      */   {
/* 1890 */     if (isZero(value))
/*      */     {
/* 1892 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1895 */     return new Matrix(this.m11 / value, this.m12 / value, this.m13 / value, this.m14 / value, this.m21 / value, this.m22 / value, this.m23 / value, this.m24 / value, this.m31 / value, this.m32 / value, this.m33 / value, this.m34 / value, this.m41 / value, this.m42 / value, this.m43 / value, this.m44 / value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Matrix divideComponents(Matrix matrix)
/*      */   {
/* 1904 */     if (matrix == null)
/*      */     {
/* 1906 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1909 */     return new Matrix(this.m11 / matrix.m11, this.m12 / matrix.m12, this.m13 / matrix.m13, this.m14 / matrix.m14, this.m21 / matrix.m21, this.m22 / matrix.m22, this.m23 / matrix.m23, this.m24 / matrix.m24, this.m31 / matrix.m31, this.m32 / matrix.m32, this.m33 / matrix.m33, this.m34 / matrix.m34, this.m41 / matrix.m41, this.m42 / matrix.m42, this.m43 / matrix.m43, this.m44 / matrix.m44);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Matrix negate()
/*      */   {
/* 1918 */     return new Matrix(0.0D - this.m11, 0.0D - this.m12, 0.0D - this.m13, 0.0D - this.m14, 0.0D - this.m21, 0.0D - this.m22, 0.0D - this.m23, 0.0D - this.m24, 0.0D - this.m31, 0.0D - this.m32, 0.0D - this.m33, 0.0D - this.m34, 0.0D - this.m41, 0.0D - this.m42, 0.0D - this.m43, 0.0D - this.m44, this.isOrthonormalTransform);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Vec4 transformBy3(Matrix matrix, double x, double y, double z)
/*      */   {
/* 1929 */     if (matrix == null)
/*      */     {
/* 1931 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1934 */     return new Vec4(matrix.m11 * x + matrix.m12 * y + matrix.m13 * z, matrix.m21 * x + matrix.m22 * y + matrix.m23 * z, matrix.m31 * x + matrix.m32 * y + matrix.m33 * z);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final double getDeterminant()
/*      */   {
/* 1946 */     double result = 0.0D;
/*      */     
/* 1948 */     result += this.m11 * (this.m22 * (this.m33 * this.m44 - this.m43 * this.m34) - this.m23 * (this.m32 * this.m44 - this.m42 * this.m34) + this.m24 * (this.m32 * this.m43 - this.m42 * this.m33));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1953 */     result -= this.m12 * (this.m21 * (this.m33 * this.m44 - this.m43 * this.m34) - this.m23 * (this.m31 * this.m44 - this.m41 * this.m34) + this.m24 * (this.m31 * this.m43 - this.m41 * this.m33));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1958 */     result += this.m13 * (this.m21 * (this.m32 * this.m44 - this.m42 * this.m34) - this.m22 * (this.m31 * this.m44 - this.m41 * this.m34) + this.m24 * (this.m31 * this.m42 - this.m41 * this.m32));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1963 */     result -= this.m14 * (this.m21 * (this.m32 * this.m43 - this.m42 - this.m33) - this.m22 * (this.m31 * this.m43 - this.m41 * this.m33) + this.m23 * (this.m31 * this.m42 - this.m41 * this.m32));
/*      */     
/*      */ 
/*      */ 
/* 1967 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */   public final Matrix getTranspose()
/*      */   {
/* 1973 */     return new Matrix(this.m11, this.m21, this.m31, this.m41, this.m12, this.m22, this.m32, this.m42, this.m13, this.m23, this.m33, this.m43, this.m14, this.m24, this.m34, this.m44, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final double getTrace()
/*      */   {
/* 1984 */     return this.m11 + this.m22 + this.m33 + this.m44;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final Matrix getInverse()
/*      */   {
/* 1994 */     if (this.isOrthonormalTransform) {
/* 1995 */       return computeTransformInverse(this);
/*      */     }
/* 1997 */     return computeGeneralInverse(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static Matrix computeTransformInverse(Matrix a)
/*      */   {
/* 2004 */     return new Matrix(a.m11, a.m21, a.m31, 0.0D - a.m11 * a.m14 - a.m21 * a.m24 - a.m31 * a.m34, a.m12, a.m22, a.m32, 0.0D - a.m12 * a.m14 - a.m22 * a.m24 - a.m32 * a.m34, a.m13, a.m23, a.m33, 0.0D - a.m13 * a.m14 - a.m23 * a.m24 - a.m33 * a.m34, 0.0D, 0.0D, 0.0D, 1.0D, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static Matrix computeGeneralInverse(Matrix a)
/*      */   {
/* 2015 */     double[][] A = new double[4][4];
/* 2016 */     A[0][0] = a.m11;
/* 2017 */     A[0][1] = a.m12;
/* 2018 */     A[0][2] = a.m13;
/* 2019 */     A[0][3] = a.m14;
/* 2020 */     A[1][0] = a.m21;
/* 2021 */     A[1][1] = a.m22;
/* 2022 */     A[1][2] = a.m23;
/* 2023 */     A[1][3] = a.m24;
/* 2024 */     A[2][0] = a.m31;
/* 2025 */     A[2][1] = a.m32;
/* 2026 */     A[2][2] = a.m33;
/* 2027 */     A[2][3] = a.m34;
/* 2028 */     A[3][0] = a.m41;
/* 2029 */     A[3][1] = a.m42;
/* 2030 */     A[3][2] = a.m43;
/* 2031 */     A[3][3] = a.m44;
/*      */     
/* 2033 */     int[] indx = new int[4];
/* 2034 */     double d = ludcmp(A, indx);
/*      */     
/*      */ 
/* 2037 */     for (int i = 0; i < 4; i++)
/*      */     {
/* 2039 */       d *= A[i][i];
/*      */     }
/*      */     
/*      */ 
/* 2043 */     if (Math.abs(d) < 1.0E-8D) {
/* 2044 */       return null;
/*      */     }
/* 2046 */     double[][] Y = new double[4][4];
/* 2047 */     double[] col = new double[4];
/* 2048 */     for (int j = 0; j < 4; j++)
/*      */     {
/* 2050 */       for (int i = 0; i < 4; i++)
/*      */       {
/* 2052 */         col[i] = 0.0D;
/*      */       }
/*      */       
/* 2055 */       col[j] = 1.0D;
/* 2056 */       lubksb(A, indx, col);
/*      */       
/* 2058 */       for (int i = 0; i < 4; i++)
/*      */       {
/* 2060 */         Y[i][j] = col[i];
/*      */       }
/*      */     }
/*      */     
/* 2064 */     return new Matrix(Y[0][0], Y[0][1], Y[0][2], Y[0][3], Y[1][0], Y[1][1], Y[1][2], Y[1][3], Y[2][0], Y[2][1], Y[2][2], Y[2][3], Y[3][0], Y[3][1], Y[3][2], Y[3][3]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void lubksb(double[][] A, int[] indx, double[] b)
/*      */   {
/* 2074 */     int ii = -1;
/* 2075 */     for (int i = 0; i < 4; i++)
/*      */     {
/* 2077 */       int ip = indx[i];
/* 2078 */       double sum = b[ip];
/* 2079 */       b[ip] = b[i];
/*      */       
/* 2081 */       if (ii != -1)
/*      */       {
/* 2083 */         for (int j = ii; j <= i - 1; j++)
/*      */         {
/* 2085 */           sum -= A[i][j] * b[j];
/*      */         }
/*      */         
/* 2088 */       } else if (sum != 0.0D)
/*      */       {
/* 2090 */         ii = i;
/*      */       }
/*      */       
/* 2093 */       b[i] = sum;
/*      */     }
/*      */     
/* 2096 */     for (int i = 3; i >= 0; i--)
/*      */     {
/* 2098 */       double sum = b[i];
/* 2099 */       for (int j = i + 1; j < 4; j++)
/*      */       {
/* 2101 */         sum -= A[i][j] * b[j];
/*      */       }
/*      */       
/* 2104 */       b[i] = (sum / A[i][i]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static double ludcmp(double[][] A, int[] indx)
/*      */   {
/* 2111 */     double TINY = 1.0E-20D;
/*      */     
/* 2113 */     double[] vv = new double[4];
/* 2114 */     double d = 1.0D;
/*      */     
/* 2116 */     for (int i = 0; i < 4; i++)
/*      */     {
/* 2118 */       double big = 0.0D;
/* 2119 */       for (int j = 0; j < 4; j++) {
/*      */         double temp;
/* 2121 */         if ((temp = Math.abs(A[i][j])) > big) {
/* 2122 */           big = temp;
/*      */         }
/*      */       }
/* 2125 */       if (big == 0.0D) {
/* 2126 */         return 0.0D;
/*      */       }
/* 2128 */       vv[i] = (1.0D / big);
/*      */     }
/*      */     
/*      */ 
/* 2132 */     for (int j = 0; j < 4; j++)
/*      */     {
/* 2134 */       for (int i = 0; i < j; i++)
/*      */       {
/* 2136 */         double sum = A[i][j];
/* 2137 */         for (int k = 0; k < i; k++)
/*      */         {
/* 2139 */           sum -= A[i][k] * A[k][j];
/*      */         }
/*      */         
/* 2142 */         A[i][j] = sum;
/*      */       }
/*      */       
/* 2145 */       double big = 0.0D;
/*      */       
/* 2147 */       int imax = -1;
/* 2148 */       for (int i = j; i < 4; i++)
/*      */       {
/* 2150 */         double sum = A[i][j];
/* 2151 */         for (int k = 0; k < j; k++)
/*      */         {
/* 2153 */           sum -= A[i][k] * A[k][j];
/*      */         }
/*      */         
/* 2156 */         A[i][j] = sum;
/*      */         double dum;
/* 2158 */         if ((dum = vv[i] * Math.abs(sum)) >= big)
/*      */         {
/* 2160 */           big = dum;
/* 2161 */           imax = i;
/*      */         }
/*      */       }
/*      */       
/* 2165 */       if (j != imax)
/*      */       {
/* 2167 */         for (int k = 0; k < 4; k++)
/*      */         {
/* 2169 */           double dum = A[imax][k];
/* 2170 */           A[imax][k] = A[j][k];
/* 2171 */           A[j][k] = dum;
/*      */         }
/*      */         
/* 2174 */         d = -d;
/* 2175 */         vv[imax] = vv[j];
/*      */       }
/*      */       
/* 2178 */       indx[j] = imax;
/* 2179 */       if (A[j][j] == 0.0D) {
/* 2180 */         A[j][j] = 1.0E-20D;
/*      */       }
/* 2182 */       if (j != 3)
/*      */       {
/* 2184 */         double dum = 1.0D / A[j][j];
/* 2185 */         for (int i = j + 1; i < 4; i++)
/*      */         {
/* 2187 */           A[i][j] *= dum;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 2192 */     return d;
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
/*      */   public final Vec4 getTranslation()
/*      */   {
/* 2320 */     return new Vec4(this.m14, this.m24, this.m34);
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
/*      */   public Vec4 extractEyePoint()
/*      */   {
/* 2339 */     double x = -(this.m11 * this.m14) - this.m21 * this.m24 - this.m31 * this.m34;
/* 2340 */     double y = -(this.m12 * this.m14) - this.m22 * this.m24 - this.m32 * this.m34;
/* 2341 */     double z = -(this.m13 * this.m14) - this.m23 * this.m24 - this.m33 * this.m34;
/*      */     
/* 2343 */     return new Vec4(x, y, z);
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
/*      */   public Vec4 extractForwardVector()
/*      */   {
/* 2361 */     return new Vec4(-this.m31, -this.m32, -this.m33);
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
/* 2451 */   private static final Double POSITIVE_ZERO = Double.valueOf(0.0D);
/*      */   
/* 2453 */   private static final Double NEGATIVE_ZERO = Double.valueOf(-0.0D);
/*      */   
/*      */   private static boolean isZero(double value)
/*      */   {
/* 2457 */     return (POSITIVE_ZERO.compareTo(Double.valueOf(value)) == 0) || (NEGATIVE_ZERO.compareTo(Double.valueOf(value)) == 0);
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/math/Matrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */