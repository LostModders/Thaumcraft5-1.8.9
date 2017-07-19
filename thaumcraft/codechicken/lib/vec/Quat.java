/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class Quat
/*     */   implements Copyable<Quat>
/*     */ {
/*     */   public double x;
/*     */   public double y;
/*     */   public double z;
/*     */   public double s;
/*     */   
/*     */   public Quat()
/*     */   {
/*  19 */     this.s = 1.0D;
/*  20 */     this.x = 0.0D;
/*  21 */     this.y = 0.0D;
/*  22 */     this.z = 0.0D;
/*     */   }
/*     */   
/*     */   public Quat(Quat quat)
/*     */   {
/*  27 */     this.x = quat.x;
/*  28 */     this.y = quat.y;
/*  29 */     this.z = quat.z;
/*  30 */     this.s = quat.s;
/*     */   }
/*     */   
/*     */   public Quat(double d, double d1, double d2, double d3)
/*     */   {
/*  35 */     this.x = d1;
/*  36 */     this.y = d2;
/*  37 */     this.z = d3;
/*  38 */     this.s = d;
/*     */   }
/*     */   
/*     */   public Quat set(Quat quat)
/*     */   {
/*  43 */     this.x = quat.x;
/*  44 */     this.y = quat.y;
/*  45 */     this.z = quat.z;
/*  46 */     this.s = quat.s;
/*     */     
/*  48 */     return this;
/*     */   }
/*     */   
/*     */   public Quat set(double d, double d1, double d2, double d3)
/*     */   {
/*  53 */     this.x = d1;
/*  54 */     this.y = d2;
/*  55 */     this.z = d3;
/*  56 */     this.s = d;
/*     */     
/*  58 */     return this;
/*     */   }
/*     */   
/*     */   public static Quat aroundAxis(double ax, double ay, double az, double angle)
/*     */   {
/*  63 */     return new Quat().setAroundAxis(ax, ay, az, angle);
/*     */   }
/*     */   
/*     */   public static Quat aroundAxis(Vector3 axis, double angle)
/*     */   {
/*  68 */     return aroundAxis(axis.x, axis.y, axis.z, angle);
/*     */   }
/*     */   
/*     */   public Quat setAroundAxis(double ax, double ay, double az, double angle)
/*     */   {
/*  73 */     angle *= 0.5D;
/*  74 */     double d4 = MathHelper.sin(angle);
/*  75 */     return set(MathHelper.cos(angle), ax * d4, ay * d4, az * d4);
/*     */   }
/*     */   
/*     */   public Quat setAroundAxis(Vector3 axis, double angle)
/*     */   {
/*  80 */     return setAroundAxis(axis.x, axis.y, axis.z, angle);
/*     */   }
/*     */   
/*     */   public Quat multiply(Quat quat)
/*     */   {
/*  85 */     double d = this.s * quat.s - this.x * quat.x - this.y * quat.y - this.z * quat.z;
/*  86 */     double d1 = this.s * quat.x + this.x * quat.s - this.y * quat.z + this.z * quat.y;
/*  87 */     double d2 = this.s * quat.y + this.x * quat.z + this.y * quat.s - this.z * quat.x;
/*  88 */     double d3 = this.s * quat.z - this.x * quat.y + this.y * quat.x + this.z * quat.s;
/*  89 */     this.s = d;
/*  90 */     this.x = d1;
/*  91 */     this.y = d2;
/*  92 */     this.z = d3;
/*     */     
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public Quat rightMultiply(Quat quat)
/*     */   {
/*  99 */     double d = this.s * quat.s - this.x * quat.x - this.y * quat.y - this.z * quat.z;
/* 100 */     double d1 = this.s * quat.x + this.x * quat.s + this.y * quat.z - this.z * quat.y;
/* 101 */     double d2 = this.s * quat.y - this.x * quat.z + this.y * quat.s + this.z * quat.x;
/* 102 */     double d3 = this.s * quat.z + this.x * quat.y - this.y * quat.x + this.z * quat.s;
/* 103 */     this.s = d;
/* 104 */     this.x = d1;
/* 105 */     this.y = d2;
/* 106 */     this.z = d3;
/*     */     
/* 108 */     return this;
/*     */   }
/*     */   
/*     */   public double mag()
/*     */   {
/* 113 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.s * this.s);
/*     */   }
/*     */   
/*     */   public Quat normalize()
/*     */   {
/* 118 */     double d = mag();
/* 119 */     if (d != 0.0D)
/*     */     {
/* 121 */       d = 1.0D / d;
/* 122 */       this.x *= d;
/* 123 */       this.y *= d;
/* 124 */       this.z *= d;
/* 125 */       this.s *= d;
/*     */     }
/*     */     
/* 128 */     return this;
/*     */   }
/*     */   
/*     */   public Quat copy()
/*     */   {
/* 133 */     return new Quat(this);
/*     */   }
/*     */   
/*     */   public void rotate(Vector3 vec)
/*     */   {
/* 138 */     double d = -this.x * vec.x - this.y * vec.y - this.z * vec.z;
/* 139 */     double d1 = this.s * vec.x + this.y * vec.z - this.z * vec.y;
/* 140 */     double d2 = this.s * vec.y - this.x * vec.z + this.z * vec.x;
/* 141 */     double d3 = this.s * vec.z + this.x * vec.y - this.y * vec.x;
/* 142 */     vec.x = (d1 * this.s - d * this.x - d2 * this.z + d3 * this.y);
/* 143 */     vec.y = (d2 * this.s - d * this.y + d1 * this.z - d3 * this.x);
/* 144 */     vec.z = (d3 * this.s - d * this.z - d1 * this.y + d2 * this.x);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 149 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 150 */     return "Quat(" + new BigDecimal(this.s, cont) + ", " + new BigDecimal(this.x, cont) + ", " + new BigDecimal(this.y, cont) + ", " + new BigDecimal(this.z, cont) + ")";
/*     */   }
/*     */   
/*     */   public Rotation rotation()
/*     */   {
/* 155 */     return new Rotation(this);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/vec/Quat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */