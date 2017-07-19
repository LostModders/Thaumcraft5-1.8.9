/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ public class TCVec3
/*     */ {
/*  10 */   public static final TCVec3Pool vec3dPool = new TCVec3Pool(-1, -1);
/*     */   
/*     */ 
/*     */   public final TCVec3Pool myVec3LocalPool;
/*     */   
/*     */ 
/*     */   public double xCoord;
/*     */   
/*     */ 
/*     */   public double yCoord;
/*     */   
/*     */ 
/*     */   public double zCoord;
/*     */   
/*     */ 
/*     */ 
/*     */   public static TCVec3 createVectorHelper(double par0, double par2, double par4)
/*     */   {
/*  28 */     return new TCVec3(vec3dPool, par0, par2, par4);
/*     */   }
/*     */   
/*     */   public static TCVec3 createVectorHelper(BlockPos p)
/*     */   {
/*  33 */     return new TCVec3(vec3dPool, p.func_177958_n(), p.func_177956_o(), p.func_177952_p());
/*     */   }
/*     */   
/*     */   protected TCVec3(TCVec3Pool par1Vec3Pool, double par2, double par4, double par6)
/*     */   {
/*  38 */     if (par2 == -0.0D)
/*     */     {
/*  40 */       par2 = 0.0D;
/*     */     }
/*     */     
/*  43 */     if (par4 == -0.0D)
/*     */     {
/*  45 */       par4 = 0.0D;
/*     */     }
/*     */     
/*  48 */     if (par6 == -0.0D)
/*     */     {
/*  50 */       par6 = 0.0D;
/*     */     }
/*     */     
/*  53 */     this.xCoord = par2;
/*  54 */     this.yCoord = par4;
/*  55 */     this.zCoord = par6;
/*  56 */     this.myVec3LocalPool = par1Vec3Pool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected TCVec3 setComponents(double par1, double par3, double par5)
/*     */   {
/*  65 */     this.xCoord = par1;
/*  66 */     this.yCoord = par3;
/*  67 */     this.zCoord = par5;
/*  68 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public TCVec3 subtract(TCVec3 par1Vec3)
/*     */   {
/*  78 */     return this.myVec3LocalPool.getVecFromPool(par1Vec3.xCoord - this.xCoord, par1Vec3.yCoord - this.yCoord, par1Vec3.zCoord - this.zCoord);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 normalize()
/*     */   {
/*  86 */     double var1 = MathHelper.func_76133_a(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
/*  87 */     return var1 < 1.0E-4D ? this.myVec3LocalPool.getVecFromPool(0.0D, 0.0D, 0.0D) : this.myVec3LocalPool.getVecFromPool(this.xCoord / var1, this.yCoord / var1, this.zCoord / var1);
/*     */   }
/*     */   
/*     */   public double dotProduct(TCVec3 par1Vec3)
/*     */   {
/*  92 */     return this.xCoord * par1Vec3.xCoord + this.yCoord * par1Vec3.yCoord + this.zCoord * par1Vec3.zCoord;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public TCVec3 crossProduct(TCVec3 par1Vec3)
/*     */   {
/* 102 */     return this.myVec3LocalPool.getVecFromPool(this.yCoord * par1Vec3.zCoord - this.zCoord * par1Vec3.yCoord, this.zCoord * par1Vec3.xCoord - this.xCoord * par1Vec3.zCoord, this.xCoord * par1Vec3.yCoord - this.yCoord * par1Vec3.xCoord);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 addVector(double par1, double par3, double par5)
/*     */   {
/* 111 */     return this.myVec3LocalPool.getVecFromPool(this.xCoord + par1, this.yCoord + par3, this.zCoord + par5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double distanceTo(TCVec3 par1Vec3)
/*     */   {
/* 119 */     double var2 = par1Vec3.xCoord - this.xCoord;
/* 120 */     double var4 = par1Vec3.yCoord - this.yCoord;
/* 121 */     double var6 = par1Vec3.zCoord - this.zCoord;
/* 122 */     return MathHelper.func_76133_a(var2 * var2 + var4 * var4 + var6 * var6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double squareDistanceTo(TCVec3 par1Vec3)
/*     */   {
/* 130 */     double var2 = par1Vec3.xCoord - this.xCoord;
/* 131 */     double var4 = par1Vec3.yCoord - this.yCoord;
/* 132 */     double var6 = par1Vec3.zCoord - this.zCoord;
/* 133 */     return var2 * var2 + var4 * var4 + var6 * var6;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double squareDistanceTo(double par1, double par3, double par5)
/*     */   {
/* 141 */     double var7 = par1 - this.xCoord;
/* 142 */     double var9 = par3 - this.yCoord;
/* 143 */     double var11 = par5 - this.zCoord;
/* 144 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double lengthVector()
/*     */   {
/* 152 */     return MathHelper.func_76133_a(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 getIntermediateWithXValue(TCVec3 par1Vec3, double par2)
/*     */   {
/* 161 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 162 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 163 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 165 */     if (var4 * var4 < 1.0000000116860974E-7D)
/*     */     {
/* 167 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 171 */     double var10 = (par2 - this.xCoord) / var4;
/* 172 */     return (var10 >= 0.0D) && (var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 getIntermediateWithYValue(TCVec3 par1Vec3, double par2)
/*     */   {
/* 182 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 183 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 184 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 186 */     if (var6 * var6 < 1.0000000116860974E-7D)
/*     */     {
/* 188 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 192 */     double var10 = (par2 - this.yCoord) / var6;
/* 193 */     return (var10 >= 0.0D) && (var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 getIntermediateWithZValue(TCVec3 par1Vec3, double par2)
/*     */   {
/* 203 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 204 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 205 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 207 */     if (var8 * var8 < 1.0000000116860974E-7D)
/*     */     {
/* 209 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 213 */     double var10 = (par2 - this.zCoord) / var8;
/* 214 */     return (var10 >= 0.0D) && (var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 220 */     return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void rotateAroundX(float par1)
/*     */   {
/* 228 */     float var2 = MathHelper.func_76134_b(par1);
/* 229 */     float var3 = MathHelper.func_76126_a(par1);
/* 230 */     double var4 = this.xCoord;
/* 231 */     double var6 = this.yCoord * var2 + this.zCoord * var3;
/* 232 */     double var8 = this.zCoord * var2 - this.yCoord * var3;
/* 233 */     this.xCoord = var4;
/* 234 */     this.yCoord = var6;
/* 235 */     this.zCoord = var8;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void rotateAroundY(float par1)
/*     */   {
/* 243 */     float var2 = MathHelper.func_76134_b(par1);
/* 244 */     float var3 = MathHelper.func_76126_a(par1);
/* 245 */     double var4 = this.xCoord * var2 + this.zCoord * var3;
/* 246 */     double var6 = this.yCoord;
/* 247 */     double var8 = this.zCoord * var2 - this.xCoord * var3;
/* 248 */     this.xCoord = var4;
/* 249 */     this.yCoord = var6;
/* 250 */     this.zCoord = var8;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void rotateAroundZ(float par1)
/*     */   {
/* 259 */     float var2 = MathHelper.func_76134_b(par1);
/* 260 */     float var3 = MathHelper.func_76126_a(par1);
/* 261 */     double var4 = this.xCoord * var2 + this.yCoord * var3;
/* 262 */     double var6 = this.yCoord * var2 - this.xCoord * var3;
/* 263 */     double var8 = this.zCoord;
/* 264 */     this.xCoord = var4;
/* 265 */     this.yCoord = var6;
/* 266 */     this.zCoord = var8;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/utils/TCVec3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */