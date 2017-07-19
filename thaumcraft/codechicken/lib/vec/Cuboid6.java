/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class Cuboid6
/*     */   implements Copyable<Cuboid6>
/*     */ {
/*  13 */   public static Cuboid6 full = new Cuboid6(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   public Vector3 min;
/*     */   public Vector3 max;
/*     */   
/*     */   public Cuboid6()
/*     */   {
/*  19 */     this(new Vector3(), new Vector3());
/*     */   }
/*     */   
/*     */   public Cuboid6(Vector3 min, Vector3 max) {
/*  23 */     this.min = min;
/*  24 */     this.max = max;
/*     */   }
/*     */   
/*     */   public Cuboid6(AxisAlignedBB aabb) {
/*  28 */     this.min = new Vector3(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c);
/*  29 */     this.max = new Vector3(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f);
/*     */   }
/*     */   
/*     */   public Cuboid6(Cuboid6 cuboid) {
/*  33 */     this.min = cuboid.min.copy();
/*  34 */     this.max = cuboid.max.copy();
/*     */   }
/*     */   
/*     */   public Cuboid6(double minx, double miny, double minz, double maxx, double maxy, double maxz) {
/*  38 */     this.min = new Vector3(minx, miny, minz);
/*  39 */     this.max = new Vector3(maxx, maxy, maxz);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB aabb() {
/*  43 */     return AxisAlignedBB.func_178781_a(this.min.x, this.min.y, this.min.z, this.max.x, this.max.y, this.max.z);
/*     */   }
/*     */   
/*     */   public Cuboid6 copy() {
/*  47 */     return new Cuboid6(this);
/*     */   }
/*     */   
/*     */   public Cuboid6 set(Cuboid6 c) {
/*  51 */     return set(c.min, c.max);
/*     */   }
/*     */   
/*     */   public Cuboid6 set(Vector3 min, Vector3 max) {
/*  55 */     this.min.set(min);
/*  56 */     this.max.set(max);
/*  57 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 set(double minx, double miny, double minz, double maxx, double maxy, double maxz) {
/*  61 */     this.min.set(minx, miny, minz);
/*  62 */     this.max.set(maxx, maxy, maxz);
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 add(Vector3 vec) {
/*  67 */     this.min.add(vec);
/*  68 */     this.max.add(vec);
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 sub(Vector3 vec) {
/*  73 */     this.min.subtract(vec);
/*  74 */     this.max.subtract(vec);
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 expand(double d) {
/*  79 */     return expand(new Vector3(d, d, d));
/*     */   }
/*     */   
/*     */   public Cuboid6 expand(Vector3 vec) {
/*  83 */     this.min.sub(vec);
/*  84 */     this.max.add(vec);
/*  85 */     return this;
/*     */   }
/*     */   
/*     */   public void setBlockBounds(Block block) {
/*  89 */     block.func_149676_a((float)this.min.x, (float)this.min.y, (float)this.min.z, (float)this.max.x, (float)this.max.y, (float)this.max.z);
/*     */   }
/*     */   
/*     */   public boolean intersects(Cuboid6 b) {
/*  93 */     return (this.max.x - 1.0E-5D > b.min.x) && (b.max.x - 1.0E-5D > this.min.x) && (this.max.y - 1.0E-5D > b.min.y) && (b.max.y - 1.0E-5D > this.min.y) && (this.max.z - 1.0E-5D > b.min.z) && (b.max.z - 1.0E-5D > this.min.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Cuboid6 offset(Cuboid6 o)
/*     */   {
/* 102 */     this.min.add(o.min);
/* 103 */     this.max.add(o.max);
/* 104 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 center() {
/* 108 */     return this.min.copy().add(this.max).multiply(0.5D);
/*     */   }
/*     */   
/*     */   public static boolean intersects(Cuboid6 a, Cuboid6 b) {
/* 112 */     return (a != null) && (b != null) && (a.intersects(b));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 116 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 117 */     return "Cuboid: (" + new BigDecimal(this.min.x, cont) + ", " + new BigDecimal(this.min.y, cont) + ", " + new BigDecimal(this.min.z, cont) + ") -> (" + new BigDecimal(this.max.x, cont) + ", " + new BigDecimal(this.max.y, cont) + ", " + new BigDecimal(this.max.z, cont) + ")";
/*     */   }
/*     */   
/*     */   public Cuboid6 enclose(Vector3 vec)
/*     */   {
/* 122 */     if (this.min.x > vec.x) this.min.x = vec.x;
/* 123 */     if (this.min.y > vec.y) this.min.y = vec.y;
/* 124 */     if (this.min.z > vec.z) this.min.z = vec.z;
/* 125 */     if (this.max.x < vec.x) this.max.x = vec.x;
/* 126 */     if (this.max.y < vec.y) this.max.y = vec.y;
/* 127 */     if (this.max.z < vec.z) this.max.z = vec.z;
/* 128 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 enclose(Cuboid6 c) {
/* 132 */     if (this.min.x > c.min.x) this.min.x = c.min.x;
/* 133 */     if (this.min.y > c.min.y) this.min.y = c.min.y;
/* 134 */     if (this.min.z > c.min.z) this.min.z = c.min.z;
/* 135 */     if (this.max.x < c.max.x) this.max.x = c.max.x;
/* 136 */     if (this.max.y < c.max.y) this.max.y = c.max.y;
/* 137 */     if (this.max.z < c.max.z) this.max.z = c.max.z;
/* 138 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 apply(Transformation t) {
/* 142 */     t.apply(this.min);
/* 143 */     t.apply(this.max);
/*     */     
/* 145 */     if (this.min.x > this.max.x) { double temp = this.min.x;this.min.x = this.max.x;this.max.x = temp; }
/* 146 */     if (this.min.y > this.max.y) { double temp = this.min.y;this.min.y = this.max.y;this.max.y = temp; }
/* 147 */     if (this.min.z > this.max.z) { double temp = this.min.z;this.min.z = this.max.z;this.max.z = temp; }
/* 148 */     return this;
/*     */   }
/*     */   
/*     */   public double getSide(int s) {
/* 152 */     switch (s) {
/* 153 */     case 0:  return this.min.y;
/* 154 */     case 1:  return this.max.y;
/* 155 */     case 2:  return this.min.z;
/* 156 */     case 3:  return this.max.z;
/* 157 */     case 4:  return this.min.x;
/* 158 */     case 5:  return this.max.x;
/*     */     }
/* 160 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public Cuboid6 setSide(int s, double d) {
/* 164 */     switch (s) {
/* 165 */     case 0:  this.min.y = d; break;
/* 166 */     case 1:  this.max.y = d; break;
/* 167 */     case 2:  this.min.z = d; break;
/* 168 */     case 3:  this.max.z = d; break;
/* 169 */     case 4:  this.min.x = d; break;
/* 170 */     case 5:  this.max.x = d; break;
/* 171 */     default:  throw new IndexOutOfBoundsException("Switch Falloff");
/*     */     }
/* 173 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/Cuboid6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */