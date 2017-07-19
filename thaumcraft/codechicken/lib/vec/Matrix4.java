/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.DoubleBuffer;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ 
/*     */ public class Matrix4
/*     */   extends Transformation
/*     */   implements Copyable<Matrix4>
/*     */ {
/*  19 */   private static DoubleBuffer glBuf = ByteBuffer.allocateDirect(128).order(ByteOrder.nativeOrder()).asDoubleBuffer();
/*     */   public double m00;
/*     */   public double m01;
/*     */   public double m02;
/*     */   public double m03;
/*     */   
/*     */   public Matrix4() {
/*  26 */     this.m00 = (this.m11 = this.m22 = this.m33 = 1.0D);
/*     */   }
/*     */   
/*     */   public double m10;
/*     */   public double m11;
/*     */   public double m12;
/*     */   
/*     */   public Matrix4(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33) {
/*  34 */     this.m00 = d00;
/*  35 */     this.m01 = d01;
/*  36 */     this.m02 = d02;
/*  37 */     this.m03 = d03;
/*  38 */     this.m10 = d10;
/*  39 */     this.m11 = d11;
/*  40 */     this.m12 = d12;
/*  41 */     this.m13 = d13;
/*  42 */     this.m20 = d20;
/*  43 */     this.m21 = d21;
/*  44 */     this.m22 = d22;
/*  45 */     this.m23 = d23;
/*  46 */     this.m30 = d30;
/*  47 */     this.m31 = d31;
/*  48 */     this.m32 = d32;
/*  49 */     this.m33 = d33;
/*     */   }
/*     */   
/*     */   public Matrix4(Matrix4 mat)
/*     */   {
/*  54 */     set(mat);
/*     */   }
/*     */   
/*     */   public Matrix4 setIdentity()
/*     */   {
/*  59 */     this.m00 = (this.m11 = this.m22 = this.m33 = 1.0D);
/*  60 */     this.m01 = (this.m02 = this.m03 = this.m10 = this.m12 = this.m13 = this.m20 = this.m21 = this.m23 = this.m30 = this.m31 = this.m32 = 0.0D);
/*     */     
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 translate(Vector3 vec)
/*     */   {
/*  67 */     this.m03 += this.m00 * vec.x + this.m01 * vec.y + this.m02 * vec.z;
/*  68 */     this.m13 += this.m10 * vec.x + this.m11 * vec.y + this.m12 * vec.z;
/*  69 */     this.m23 += this.m20 * vec.x + this.m21 * vec.y + this.m22 * vec.z;
/*  70 */     this.m33 += this.m30 * vec.x + this.m31 * vec.y + this.m32 * vec.z;
/*     */     
/*  72 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 scale(Vector3 vec)
/*     */   {
/*  77 */     this.m00 *= vec.x;
/*  78 */     this.m10 *= vec.x;
/*  79 */     this.m20 *= vec.x;
/*  80 */     this.m30 *= vec.x;
/*  81 */     this.m01 *= vec.y;
/*  82 */     this.m11 *= vec.y;
/*  83 */     this.m21 *= vec.y;
/*  84 */     this.m31 *= vec.y;
/*  85 */     this.m02 *= vec.z;
/*  86 */     this.m12 *= vec.z;
/*  87 */     this.m22 *= vec.z;
/*  88 */     this.m32 *= vec.z;
/*     */     
/*  90 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 rotate(double angle, Vector3 axis)
/*     */   {
/*  95 */     double c = Math.cos(angle);
/*  96 */     double s = Math.sin(angle);
/*  97 */     double mc = 1.0D - c;
/*  98 */     double xy = axis.x * axis.y;
/*  99 */     double yz = axis.y * axis.z;
/* 100 */     double xz = axis.x * axis.z;
/* 101 */     double xs = axis.x * s;
/* 102 */     double ys = axis.y * s;
/* 103 */     double zs = axis.z * s;
/*     */     
/* 105 */     double f00 = axis.x * axis.x * mc + c;
/* 106 */     double f10 = xy * mc + zs;
/* 107 */     double f20 = xz * mc - ys;
/*     */     
/* 109 */     double f01 = xy * mc - zs;
/* 110 */     double f11 = axis.y * axis.y * mc + c;
/* 111 */     double f21 = yz * mc + xs;
/*     */     
/* 113 */     double f02 = xz * mc + ys;
/* 114 */     double f12 = yz * mc - xs;
/* 115 */     double f22 = axis.z * axis.z * mc + c;
/*     */     
/* 117 */     double t00 = this.m00 * f00 + this.m01 * f10 + this.m02 * f20;
/* 118 */     double t10 = this.m10 * f00 + this.m11 * f10 + this.m12 * f20;
/* 119 */     double t20 = this.m20 * f00 + this.m21 * f10 + this.m22 * f20;
/* 120 */     double t30 = this.m30 * f00 + this.m31 * f10 + this.m32 * f20;
/* 121 */     double t01 = this.m00 * f01 + this.m01 * f11 + this.m02 * f21;
/* 122 */     double t11 = this.m10 * f01 + this.m11 * f11 + this.m12 * f21;
/* 123 */     double t21 = this.m20 * f01 + this.m21 * f11 + this.m22 * f21;
/* 124 */     double t31 = this.m30 * f01 + this.m31 * f11 + this.m32 * f21;
/* 125 */     this.m02 = (this.m00 * f02 + this.m01 * f12 + this.m02 * f22);
/* 126 */     this.m12 = (this.m10 * f02 + this.m11 * f12 + this.m12 * f22);
/* 127 */     this.m22 = (this.m20 * f02 + this.m21 * f12 + this.m22 * f22);
/* 128 */     this.m32 = (this.m30 * f02 + this.m31 * f12 + this.m32 * f22);
/* 129 */     this.m00 = t00;
/* 130 */     this.m10 = t10;
/* 131 */     this.m20 = t20;
/* 132 */     this.m30 = t30;
/* 133 */     this.m01 = t01;
/* 134 */     this.m11 = t11;
/* 135 */     this.m21 = t21;
/* 136 */     this.m31 = t31;
/*     */     
/* 138 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 rotate(Rotation rotation)
/*     */   {
/* 143 */     rotation.apply(this);
/* 144 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 leftMultiply(Matrix4 mat)
/*     */   {
/* 149 */     double n00 = this.m00 * mat.m00 + this.m10 * mat.m01 + this.m20 * mat.m02 + this.m30 * mat.m03;
/* 150 */     double n01 = this.m01 * mat.m00 + this.m11 * mat.m01 + this.m21 * mat.m02 + this.m31 * mat.m03;
/* 151 */     double n02 = this.m02 * mat.m00 + this.m12 * mat.m01 + this.m22 * mat.m02 + this.m32 * mat.m03;
/* 152 */     double n03 = this.m03 * mat.m00 + this.m13 * mat.m01 + this.m23 * mat.m02 + this.m33 * mat.m03;
/* 153 */     double n10 = this.m00 * mat.m10 + this.m10 * mat.m11 + this.m20 * mat.m12 + this.m30 * mat.m13;
/* 154 */     double n11 = this.m01 * mat.m10 + this.m11 * mat.m11 + this.m21 * mat.m12 + this.m31 * mat.m13;
/* 155 */     double n12 = this.m02 * mat.m10 + this.m12 * mat.m11 + this.m22 * mat.m12 + this.m32 * mat.m13;
/* 156 */     double n13 = this.m03 * mat.m10 + this.m13 * mat.m11 + this.m23 * mat.m12 + this.m33 * mat.m13;
/* 157 */     double n20 = this.m00 * mat.m20 + this.m10 * mat.m21 + this.m20 * mat.m22 + this.m30 * mat.m23;
/* 158 */     double n21 = this.m01 * mat.m20 + this.m11 * mat.m21 + this.m21 * mat.m22 + this.m31 * mat.m23;
/* 159 */     double n22 = this.m02 * mat.m20 + this.m12 * mat.m21 + this.m22 * mat.m22 + this.m32 * mat.m23;
/* 160 */     double n23 = this.m03 * mat.m20 + this.m13 * mat.m21 + this.m23 * mat.m22 + this.m33 * mat.m23;
/* 161 */     double n30 = this.m00 * mat.m30 + this.m10 * mat.m31 + this.m20 * mat.m32 + this.m30 * mat.m33;
/* 162 */     double n31 = this.m01 * mat.m30 + this.m11 * mat.m31 + this.m21 * mat.m32 + this.m31 * mat.m33;
/* 163 */     double n32 = this.m02 * mat.m30 + this.m12 * mat.m31 + this.m22 * mat.m32 + this.m32 * mat.m33;
/* 164 */     double n33 = this.m03 * mat.m30 + this.m13 * mat.m31 + this.m23 * mat.m32 + this.m33 * mat.m33;
/*     */     
/* 166 */     this.m00 = n00;
/* 167 */     this.m01 = n01;
/* 168 */     this.m02 = n02;
/* 169 */     this.m03 = n03;
/* 170 */     this.m10 = n10;
/* 171 */     this.m11 = n11;
/* 172 */     this.m12 = n12;
/* 173 */     this.m13 = n13;
/* 174 */     this.m20 = n20;
/* 175 */     this.m21 = n21;
/* 176 */     this.m22 = n22;
/* 177 */     this.m23 = n23;
/* 178 */     this.m30 = n30;
/* 179 */     this.m31 = n31;
/* 180 */     this.m32 = n32;
/* 181 */     this.m33 = n33;
/*     */     
/* 183 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 multiply(Matrix4 mat)
/*     */   {
/* 188 */     double n00 = this.m00 * mat.m00 + this.m01 * mat.m10 + this.m02 * mat.m20 + this.m03 * mat.m30;
/* 189 */     double n01 = this.m00 * mat.m01 + this.m01 * mat.m11 + this.m02 * mat.m21 + this.m03 * mat.m31;
/* 190 */     double n02 = this.m00 * mat.m02 + this.m01 * mat.m12 + this.m02 * mat.m22 + this.m03 * mat.m32;
/* 191 */     double n03 = this.m00 * mat.m03 + this.m01 * mat.m13 + this.m02 * mat.m23 + this.m03 * mat.m33;
/* 192 */     double n10 = this.m10 * mat.m00 + this.m11 * mat.m10 + this.m12 * mat.m20 + this.m13 * mat.m30;
/* 193 */     double n11 = this.m10 * mat.m01 + this.m11 * mat.m11 + this.m12 * mat.m21 + this.m13 * mat.m31;
/* 194 */     double n12 = this.m10 * mat.m02 + this.m11 * mat.m12 + this.m12 * mat.m22 + this.m13 * mat.m32;
/* 195 */     double n13 = this.m10 * mat.m03 + this.m11 * mat.m13 + this.m12 * mat.m23 + this.m13 * mat.m33;
/* 196 */     double n20 = this.m20 * mat.m00 + this.m21 * mat.m10 + this.m22 * mat.m20 + this.m23 * mat.m30;
/* 197 */     double n21 = this.m20 * mat.m01 + this.m21 * mat.m11 + this.m22 * mat.m21 + this.m23 * mat.m31;
/* 198 */     double n22 = this.m20 * mat.m02 + this.m21 * mat.m12 + this.m22 * mat.m22 + this.m23 * mat.m32;
/* 199 */     double n23 = this.m20 * mat.m03 + this.m21 * mat.m13 + this.m22 * mat.m23 + this.m23 * mat.m33;
/* 200 */     double n30 = this.m30 * mat.m00 + this.m31 * mat.m10 + this.m32 * mat.m20 + this.m33 * mat.m30;
/* 201 */     double n31 = this.m30 * mat.m01 + this.m31 * mat.m11 + this.m32 * mat.m21 + this.m33 * mat.m31;
/* 202 */     double n32 = this.m30 * mat.m02 + this.m31 * mat.m12 + this.m32 * mat.m22 + this.m33 * mat.m32;
/* 203 */     double n33 = this.m30 * mat.m03 + this.m31 * mat.m13 + this.m32 * mat.m23 + this.m33 * mat.m33;
/*     */     
/* 205 */     this.m00 = n00;
/* 206 */     this.m01 = n01;
/* 207 */     this.m02 = n02;
/* 208 */     this.m03 = n03;
/* 209 */     this.m10 = n10;
/* 210 */     this.m11 = n11;
/* 211 */     this.m12 = n12;
/* 212 */     this.m13 = n13;
/* 213 */     this.m20 = n20;
/* 214 */     this.m21 = n21;
/* 215 */     this.m22 = n22;
/* 216 */     this.m23 = n23;
/* 217 */     this.m30 = n30;
/* 218 */     this.m31 = n31;
/* 219 */     this.m32 = n32;
/* 220 */     this.m33 = n33;
/*     */     
/* 222 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 transpose()
/*     */   {
/* 227 */     double n00 = this.m00;
/* 228 */     double n10 = this.m01;
/* 229 */     double n20 = this.m02;
/* 230 */     double n30 = this.m03;
/* 231 */     double n01 = this.m10;
/* 232 */     double n11 = this.m11;
/* 233 */     double n21 = this.m12;
/* 234 */     double n31 = this.m13;
/* 235 */     double n02 = this.m20;
/* 236 */     double n12 = this.m21;
/* 237 */     double n22 = this.m22;
/* 238 */     double n32 = this.m23;
/* 239 */     double n03 = this.m30;
/* 240 */     double n13 = this.m31;
/* 241 */     double n23 = this.m32;
/* 242 */     double n33 = this.m33;
/*     */     
/* 244 */     this.m00 = n00;
/* 245 */     this.m01 = n01;
/* 246 */     this.m02 = n02;
/* 247 */     this.m03 = n03;
/* 248 */     this.m10 = n10;
/* 249 */     this.m11 = n11;
/* 250 */     this.m12 = n12;
/* 251 */     this.m13 = n13;
/* 252 */     this.m20 = n20;
/* 253 */     this.m21 = n21;
/* 254 */     this.m22 = n22;
/* 255 */     this.m23 = n23;
/* 256 */     this.m30 = n30;
/* 257 */     this.m31 = n31;
/* 258 */     this.m32 = n32;
/* 259 */     this.m33 = n33;
/*     */     
/* 261 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 copy()
/*     */   {
/* 266 */     return new Matrix4(this);
/*     */   }
/*     */   
/*     */   public Matrix4 set(Matrix4 mat)
/*     */   {
/* 271 */     this.m00 = mat.m00;
/* 272 */     this.m01 = mat.m01;
/* 273 */     this.m02 = mat.m02;
/* 274 */     this.m03 = mat.m03;
/* 275 */     this.m10 = mat.m10;
/* 276 */     this.m11 = mat.m11;
/* 277 */     this.m12 = mat.m12;
/* 278 */     this.m13 = mat.m13;
/* 279 */     this.m20 = mat.m20;
/* 280 */     this.m21 = mat.m21;
/* 281 */     this.m22 = mat.m22;
/* 282 */     this.m23 = mat.m23;
/* 283 */     this.m30 = mat.m30;
/* 284 */     this.m31 = mat.m31;
/* 285 */     this.m32 = mat.m32;
/* 286 */     this.m33 = mat.m33;
/*     */     
/* 288 */     return this;
/*     */   }
/*     */   
/*     */   public double m13;
/*     */   public double m20;
/*     */   public double m21;
/* 294 */   public void apply(Matrix4 mat) { mat.multiply(this); }
/*     */   
/*     */ 
/*     */   private void mult3x3(Vector3 vec)
/*     */   {
/* 299 */     double x = this.m00 * vec.x + this.m01 * vec.y + this.m02 * vec.z;
/* 300 */     double y = this.m10 * vec.x + this.m11 * vec.y + this.m12 * vec.z;
/* 301 */     double z = this.m20 * vec.x + this.m21 * vec.y + this.m22 * vec.z;
/*     */     
/* 303 */     vec.x = x;
/* 304 */     vec.y = y;
/* 305 */     vec.z = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void apply(Vector3 vec)
/*     */   {
/* 311 */     mult3x3(vec);
/* 312 */     vec.add(this.m03, this.m13, this.m23);
/*     */   }
/*     */   
/*     */ 
/*     */   public void applyN(Vector3 vec)
/*     */   {
/* 318 */     mult3x3(vec);
/* 319 */     vec.normalize();
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 325 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 326 */     return "[" + new BigDecimal(this.m00, cont) + "," + new BigDecimal(this.m01, cont) + "," + new BigDecimal(this.m02, cont) + "," + new BigDecimal(this.m03, cont) + "]\n" + "[" + new BigDecimal(this.m10, cont) + "," + new BigDecimal(this.m11, cont) + "," + new BigDecimal(this.m12, cont) + "," + new BigDecimal(this.m13, cont) + "]\n" + "[" + new BigDecimal(this.m20, cont) + "," + new BigDecimal(this.m21, cont) + "," + new BigDecimal(this.m22, cont) + "," + new BigDecimal(this.m23, cont) + "]\n" + "[" + new BigDecimal(this.m30, cont) + "," + new BigDecimal(this.m31, cont) + "," + new BigDecimal(this.m32, cont) + "," + new BigDecimal(this.m33, cont) + "]"; }
/*     */   
/*     */   public double m22;
/*     */   public double m23;
/*     */   public double m30;
/*     */   public double m31;
/*     */   public double m32;
/*     */   public double m33;
/* 334 */   public Matrix4 apply(Transformation t) { t.apply(this);
/* 335 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glApply()
/*     */   {
/* 342 */     glBuf.put(this.m00).put(this.m10).put(this.m20).put(this.m30).put(this.m01).put(this.m11).put(this.m21).put(this.m31).put(this.m02).put(this.m12).put(this.m22).put(this.m32).put(this.m03).put(this.m13).put(this.m23).put(this.m33);
/*     */     
/*     */ 
/*     */ 
/* 346 */     glBuf.flip();
/* 347 */     GL11.glMultMatrix(glBuf);
/*     */   }
/*     */   
/*     */ 
/*     */   public Transformation inverse()
/*     */   {
/* 353 */     throw new IrreversibleTransformationException(this);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/Matrix4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */