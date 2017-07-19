/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Vector3f;
/*     */ import org.lwjgl.util.vector.Vector4f;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Vector3
/*     */   implements Copyable<Vector3>
/*     */ {
/*  23 */   public static Vector3 zero = new Vector3();
/*  24 */   public static Vector3 one = new Vector3(1.0D, 1.0D, 1.0D);
/*  25 */   public static Vector3 center = new Vector3(0.5D, 0.5D, 0.5D);
/*     */   
/*     */   public double x;
/*     */   public double y;
/*     */   public double z;
/*     */   
/*     */   public Vector3() {}
/*     */   
/*     */   public Vector3(double d, double d1, double d2)
/*     */   {
/*  35 */     this.x = d;
/*  36 */     this.y = d1;
/*  37 */     this.z = d2;
/*     */   }
/*     */   
/*     */   public Vector3(Vector3 vec) {
/*  41 */     this.x = vec.x;
/*  42 */     this.y = vec.y;
/*  43 */     this.z = vec.z;
/*     */   }
/*     */   
/*     */   public Vector3(double[] da) {
/*  47 */     this(da[0], da[1], da[2]);
/*     */   }
/*     */   
/*     */   public Vector3(Vec3 vec) {
/*  51 */     this.x = vec.field_72450_a;
/*  52 */     this.y = vec.field_72448_b;
/*  53 */     this.z = vec.field_72449_c;
/*     */   }
/*     */   
/*     */   public Vector3(BlockCoord coord) {
/*  57 */     this.x = coord.x;
/*  58 */     this.y = coord.y;
/*  59 */     this.z = coord.z;
/*     */   }
/*     */   
/*     */   public Vector3(BlockPos pos) {
/*  63 */     this.x = pos.func_177958_n();
/*  64 */     this.y = pos.func_177956_o();
/*  65 */     this.z = pos.func_177952_p();
/*     */   }
/*     */   
/*     */   public Vector3 copy() {
/*  69 */     return new Vector3(this);
/*     */   }
/*     */   
/*     */   public static Vector3 fromEntity(Entity e) {
/*  73 */     return new Vector3(e.field_70165_t, e.field_70163_u, e.field_70161_v);
/*     */   }
/*     */   
/*     */   public static Vector3 fromEntityCenter(Entity e) {
/*  77 */     return new Vector3(e.field_70165_t, e.field_70163_u - e.func_70033_W() + e.field_70131_O / 2.0F, e.field_70161_v);
/*     */   }
/*     */   
/*     */   public static Vector3 fromTile(TileEntity tile) {
/*  81 */     return new Vector3(tile.func_174877_v());
/*     */   }
/*     */   
/*     */   public static Vector3 fromTileCenter(TileEntity tile) {
/*  85 */     return fromTile(tile).add(0.5D);
/*     */   }
/*     */   
/*     */   public static Vector3 fromAxes(double[] da) {
/*  89 */     return new Vector3(da[2], da[0], da[1]);
/*     */   }
/*     */   
/*     */   public Vector3 set(double d, double d1, double d2) {
/*  93 */     this.x = d;
/*  94 */     this.y = d1;
/*  95 */     this.z = d2;
/*  96 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 set(Vector3 vec) {
/* 100 */     this.x = vec.x;
/* 101 */     this.y = vec.y;
/* 102 */     this.z = vec.z;
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public double getSide(int side) {
/* 107 */     switch (side) {
/*     */     case 0: 
/*     */     case 1: 
/* 110 */       return this.y;
/*     */     case 2: 
/*     */     case 3: 
/* 113 */       return this.z;
/*     */     case 4: 
/*     */     case 5: 
/* 116 */       return this.x;
/*     */     }
/* 118 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public Vector3 setSide(int s, double v) {
/* 122 */     switch (s) {
/*     */     case 0: 
/*     */     case 1: 
/* 125 */       this.y = v;
/* 126 */       break;
/*     */     case 2: 
/*     */     case 3: 
/* 129 */       this.z = v;
/* 130 */       break;
/*     */     case 4: 
/*     */     case 5: 
/* 133 */       this.x = v;
/* 134 */       break;
/*     */     default: 
/* 136 */       throw new IndexOutOfBoundsException("Switch Falloff");
/*     */     }
/* 138 */     return this;
/*     */   }
/*     */   
/*     */   public double dotProduct(Vector3 vec) {
/* 142 */     double d = vec.x * this.x + vec.y * this.y + vec.z * this.z;
/*     */     
/* 144 */     if ((d > 1.0D) && (d < 1.00001D)) {
/* 145 */       d = 1.0D;
/* 146 */     } else if ((d < -1.0D) && (d > -1.00001D))
/* 147 */       d = -1.0D;
/* 148 */     return d;
/*     */   }
/*     */   
/*     */   public double dotProduct(double d, double d1, double d2) {
/* 152 */     return d * this.x + d1 * this.y + d2 * this.z;
/*     */   }
/*     */   
/*     */   public Vector3 crossProduct(Vector3 vec) {
/* 156 */     double d = this.y * vec.z - this.z * vec.y;
/* 157 */     double d1 = this.z * vec.x - this.x * vec.z;
/* 158 */     double d2 = this.x * vec.y - this.y * vec.x;
/* 159 */     this.x = d;
/* 160 */     this.y = d1;
/* 161 */     this.z = d2;
/* 162 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 add(double d, double d1, double d2) {
/* 166 */     this.x += d;
/* 167 */     this.y += d1;
/* 168 */     this.z += d2;
/* 169 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 add(Vector3 vec) {
/* 173 */     this.x += vec.x;
/* 174 */     this.y += vec.y;
/* 175 */     this.z += vec.z;
/* 176 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 add(double d) {
/* 180 */     return add(d, d, d);
/*     */   }
/*     */   
/*     */   public Vector3 sub(Vector3 vec) {
/* 184 */     return subtract(vec);
/*     */   }
/*     */   
/*     */   public Vector3 subtract(Vector3 vec) {
/* 188 */     this.x -= vec.x;
/* 189 */     this.y -= vec.y;
/* 190 */     this.z -= vec.z;
/* 191 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 negate(Vector3 vec) {
/* 195 */     this.x = (-this.x);
/* 196 */     this.y = (-this.y);
/* 197 */     this.z = (-this.z);
/* 198 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(double d) {
/* 202 */     this.x *= d;
/* 203 */     this.y *= d;
/* 204 */     this.z *= d;
/* 205 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(Vector3 f) {
/* 209 */     this.x *= f.x;
/* 210 */     this.y *= f.y;
/* 211 */     this.z *= f.z;
/* 212 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(double fx, double fy, double fz) {
/* 216 */     this.x *= fx;
/* 217 */     this.y *= fy;
/* 218 */     this.z *= fz;
/* 219 */     return this;
/*     */   }
/*     */   
/*     */   public double mag() {
/* 223 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */   
/*     */   public double magSquared() {
/* 227 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*     */   }
/*     */   
/*     */   public Vector3 normalize() {
/* 231 */     double d = mag();
/* 232 */     if (d != 0.0D) {
/* 233 */       multiply(1.0D / d);
/*     */     }
/* 235 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 239 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 240 */     return "Vector3(" + new BigDecimal(this.x, cont) + ", " + new BigDecimal(this.y, cont) + ", " + new BigDecimal(this.z, cont) + ")";
/*     */   }
/*     */   
/*     */   public Vector3 perpendicular() {
/* 244 */     if (this.z == 0.0D)
/* 245 */       return zCrossProduct();
/* 246 */     return xCrossProduct();
/*     */   }
/*     */   
/*     */   public Vector3 xCrossProduct() {
/* 250 */     double d = this.z;
/* 251 */     double d1 = -this.y;
/* 252 */     this.x = 0.0D;
/* 253 */     this.y = d;
/* 254 */     this.z = d1;
/* 255 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 zCrossProduct() {
/* 259 */     double d = this.y;
/* 260 */     double d1 = -this.x;
/* 261 */     this.x = d;
/* 262 */     this.y = d1;
/* 263 */     this.z = 0.0D;
/* 264 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 yCrossProduct() {
/* 268 */     double d = -this.z;
/* 269 */     double d1 = this.x;
/* 270 */     this.x = d;
/* 271 */     this.y = 0.0D;
/* 272 */     this.z = d1;
/* 273 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 rotate(double angle, Vector3 axis) {
/* 277 */     Quat.aroundAxis(axis.copy().normalize(), angle).rotate(this);
/* 278 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 rotate(Quat rotator) {
/* 282 */     rotator.rotate(this);
/* 283 */     return this;
/*     */   }
/*     */   
/*     */   public Vec3 vec3() {
/* 287 */     return new Vec3(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public double angle(Vector3 vec) {
/* 291 */     return Math.acos(copy().normalize().dotProduct(vec.copy().normalize()));
/*     */   }
/*     */   
/*     */   public boolean isZero() {
/* 295 */     return (this.x == 0.0D) && (this.y == 0.0D) && (this.z == 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isAxial() {
/* 299 */     return (this.y == 0.0D) || (this.z == 0.0D);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vector3f vector3f() {
/* 304 */     return new Vector3f((float)this.x, (float)this.y, (float)this.z);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vector4f vector4f() {
/* 309 */     return new Vector4f((float)this.x, (float)this.y, (float)this.z, 1.0F);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glVertex() {
/* 314 */     GL11.glVertex3d(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public Vector3 YZintercept(Vector3 end, double px) {
/* 318 */     double dx = end.x - this.x;
/* 319 */     double dy = end.y - this.y;
/* 320 */     double dz = end.z - this.z;
/*     */     
/* 322 */     if (dx == 0.0D) {
/* 323 */       return null;
/*     */     }
/* 325 */     double d = (px - this.x) / dx;
/* 326 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 327 */       return this;
/*     */     }
/* 329 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 330 */       return null;
/*     */     }
/* 332 */     this.x = px;
/* 333 */     this.y += d * dy;
/* 334 */     this.z += d * dz;
/* 335 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 XZintercept(Vector3 end, double py) {
/* 339 */     double dx = end.x - this.x;
/* 340 */     double dy = end.y - this.y;
/* 341 */     double dz = end.z - this.z;
/*     */     
/* 343 */     if (dy == 0.0D) {
/* 344 */       return null;
/*     */     }
/* 346 */     double d = (py - this.y) / dy;
/* 347 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 348 */       return this;
/*     */     }
/* 350 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 351 */       return null;
/*     */     }
/* 353 */     this.x += d * dx;
/* 354 */     this.y = py;
/* 355 */     this.z += d * dz;
/* 356 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 XYintercept(Vector3 end, double pz) {
/* 360 */     double dx = end.x - this.x;
/* 361 */     double dy = end.y - this.y;
/* 362 */     double dz = end.z - this.z;
/*     */     
/* 364 */     if (dz == 0.0D) {
/* 365 */       return null;
/*     */     }
/* 367 */     double d = (pz - this.z) / dz;
/* 368 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 369 */       return this;
/*     */     }
/* 371 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 372 */       return null;
/*     */     }
/* 374 */     this.x += d * dx;
/* 375 */     this.y += d * dy;
/* 376 */     this.z = pz;
/* 377 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 negate() {
/* 381 */     this.x = (-this.x);
/* 382 */     this.y = (-this.y);
/* 383 */     this.z = (-this.z);
/* 384 */     return this;
/*     */   }
/*     */   
/*     */   public Translation translation() {
/* 388 */     return new Translation(this);
/*     */   }
/*     */   
/*     */   public double scalarProject(Vector3 b) {
/* 392 */     double l = b.mag();
/* 393 */     return l == 0.0D ? 0.0D : dotProduct(b) / l;
/*     */   }
/*     */   
/*     */   public Vector3 project(Vector3 b) {
/* 397 */     double l = b.magSquared();
/* 398 */     if (l == 0.0D) {
/* 399 */       set(0.0D, 0.0D, 0.0D);
/* 400 */       return this;
/*     */     }
/* 402 */     double m = dotProduct(b) / l;
/* 403 */     set(b).multiply(m);
/* 404 */     return this;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o)
/*     */   {
/* 409 */     if (!(o instanceof Vector3))
/* 410 */       return false;
/* 411 */     Vector3 v = (Vector3)o;
/* 412 */     return (this.x == v.x) && (this.y == v.y) && (this.z == v.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equalsT(Vector3 v)
/*     */   {
/* 421 */     return (MathHelper.between(this.x - 1.0E-5D, v.x, this.x + 1.0E-5D)) && (MathHelper.between(this.y - 1.0E-5D, v.y, this.y + 1.0E-5D)) && (MathHelper.between(this.z - 1.0E-5D, v.z, this.z + 1.0E-5D));
/*     */   }
/*     */   
/*     */ 
/*     */   public Vector3 apply(Transformation t)
/*     */   {
/* 427 */     t.apply(this);
/* 428 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 $tilde() {
/* 432 */     return normalize();
/*     */   }
/*     */   
/*     */   public Vector3 unary_$tilde() {
/* 436 */     return normalize();
/*     */   }
/*     */   
/*     */   public Vector3 $plus(Vector3 v) {
/* 440 */     return add(v);
/*     */   }
/*     */   
/*     */   public Vector3 $minus(Vector3 v) {
/* 444 */     return subtract(v);
/*     */   }
/*     */   
/*     */   public Vector3 $times(double d) {
/* 448 */     return multiply(d);
/*     */   }
/*     */   
/*     */   public Vector3 $div(double d) {
/* 452 */     return multiply(1.0D / d);
/*     */   }
/*     */   
/*     */   public Vector3 $times(Vector3 v) {
/* 456 */     return crossProduct(v);
/*     */   }
/*     */   
/*     */   public double $dot$times(Vector3 v) {
/* 460 */     return dotProduct(v);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/Vector3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */