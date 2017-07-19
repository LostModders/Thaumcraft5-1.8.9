/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXVisSparkle extends EntityFX
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   
/*     */   public FXVisSparkle(World par1World, double par2, double par4, double par6, double tx, double ty, double tz)
/*     */   {
/*  18 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  19 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  20 */     this.field_70544_f = 0.0F;
/*     */     
/*  22 */     this.targetX = tx;
/*  23 */     this.targetY = ty;
/*  24 */     this.targetZ = tz;
/*     */     
/*  26 */     this.field_70547_e = 1000;
/*     */     
/*  28 */     float f3 = 0.01F;
/*  29 */     this.field_70159_w = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  30 */     this.field_70181_x = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  31 */     this.field_70179_y = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  32 */     this.sizeMod = (45 + this.field_70146_Z.nextInt(15));
/*  33 */     this.field_70552_h = 0.2F;
/*  34 */     this.field_70553_i = (0.6F + this.field_70146_Z.nextFloat() * 0.3F);
/*  35 */     this.field_70551_j = 0.2F;
/*     */     
/*  37 */     this.field_70545_g = 0.2F;
/*  38 */     this.field_70145_X = true;
/*     */   }
/*     */   
/*     */ 
/*  42 */   float sizeMod = 0.0F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  49 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.3F + 6.0F;
/*     */     
/*  51 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  52 */     int part = this.field_70546_d % 16;
/*  53 */     float var8 = part / 16.0F;
/*  54 */     float var9 = var8 + 0.0624375F;
/*  55 */     float var10 = 0.5F;
/*  56 */     float var11 = var10 + 0.0624375F;
/*     */     
/*  58 */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     
/*  60 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  62 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  64 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  66 */     float var16 = 1.0F;
/*     */     
/*  68 */     int i = 240;
/*  69 */     int j = i >> 16 & 0xFFFF;
/*  70 */     int k = i & 0xFFFF;
/*     */     
/*  72 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  74 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  76 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  78 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  85 */     this.field_70169_q = this.field_70165_t;
/*  86 */     this.field_70167_r = this.field_70163_u;
/*  87 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  89 */     if (this.field_70546_d++ >= this.field_70547_e) {
/*  90 */       func_70106_y();
/*  91 */       return;
/*     */     }
/*     */     
/*  94 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/*  96 */     this.field_70159_w *= 0.985D;
/*  97 */     this.field_70181_x *= 0.985D;
/*  98 */     this.field_70179_y *= 0.985D;
/*     */     
/* 100 */     double dx = this.targetX - this.field_70165_t;
/* 101 */     double dy = this.targetY - this.field_70163_u;
/* 102 */     double dz = this.targetZ - this.field_70161_v;
/* 103 */     double d13 = 0.10000000149011612D;
/* 104 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/* 105 */     if (d11 < 2.0D) this.field_70544_f *= 0.95F;
/* 106 */     if (d11 < 0.2D) this.field_70547_e = this.field_70546_d;
/* 107 */     if (this.field_70546_d < 10) this.field_70544_f = (this.field_70546_d / this.sizeMod);
/* 108 */     dx /= d11;
/* 109 */     dy /= d11;
/* 110 */     dz /= d11;
/*     */     
/* 112 */     this.field_70159_w += dx * d13;
/* 113 */     this.field_70181_x += dy * d13;
/* 114 */     this.field_70179_y += dz * d13;
/*     */     
/* 116 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.1F, 0.1F);
/* 117 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.1F, 0.1F);
/* 118 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.1F, 0.1F);
/*     */   }
/*     */   
/*     */   public void setGravity(float value) {
/* 122 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXVisSparkle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */