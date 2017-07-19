/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ 
/*     */ public class FXBoreSparkle extends EntityFX
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   
/*     */   public FXBoreSparkle(net.minecraft.world.World par1World, double par2, double par4, double par6, double tx, double ty, double tz)
/*     */   {
/*  19 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  20 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  21 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.5F + 0.5F);
/*     */     
/*  23 */     this.targetX = tx;
/*  24 */     this.targetY = ty;
/*  25 */     this.targetZ = tz;
/*     */     
/*  27 */     double dx = tx - this.field_70165_t;
/*  28 */     double dy = ty - this.field_70163_u;
/*  29 */     double dz = tz - this.field_70161_v;
/*  30 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/*  31 */     if (base < 1)
/*  32 */       base = 1;
/*  33 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  35 */     float f3 = 0.01F;
/*  36 */     this.field_70159_w = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  37 */     this.field_70181_x = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  38 */     this.field_70179_y = ((float)this.field_70146_Z.nextGaussian() * f3);
/*     */     
/*  40 */     this.field_70552_h = 0.2F;
/*  41 */     this.field_70553_i = (0.6F + this.field_70146_Z.nextFloat() * 0.3F);
/*  42 */     this.field_70551_j = 0.2F;
/*     */     
/*  44 */     this.field_70545_g = 0.2F;
/*  45 */     this.field_70145_X = false;
/*     */     
/*  47 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/*  48 */     int visibleDistance = 64;
/*  49 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  50 */       visibleDistance = 32;
/*  51 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) {
/*  52 */       this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  57 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.5F + 1.0F;
/*     */     
/*  59 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  60 */     int part = this.field_70546_d % 4;
/*     */     
/*  62 */     float var8 = part / 16.0F;
/*  63 */     float var9 = var8 + 0.0624375F;
/*  64 */     float var10 = 0.25F;
/*  65 */     float var11 = var10 + 0.0624375F;
/*  66 */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     
/*  68 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  70 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  72 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  74 */     float var16 = 1.0F;
/*     */     
/*  76 */     int i = 240;
/*  77 */     int j = i >> 16 & 0xFFFF;
/*  78 */     int k = i & 0xFFFF;
/*     */     
/*  80 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  82 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  84 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  86 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  92 */     this.field_70169_q = this.field_70165_t;
/*  93 */     this.field_70167_r = this.field_70163_u;
/*  94 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  96 */     if ((this.field_70546_d++ >= this.field_70547_e) || ((MathHelper.func_76128_c(this.field_70165_t) == MathHelper.func_76128_c(this.targetX)) && (MathHelper.func_76128_c(this.field_70163_u) == MathHelper.func_76128_c(this.targetY)) && (MathHelper.func_76128_c(this.field_70161_v) == MathHelper.func_76128_c(this.targetZ))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */       func_70106_y();
/* 103 */       return;
/*     */     }
/*     */     
/*     */ 
/* 107 */     if (!this.field_70145_X)
/* 108 */       func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 109 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 111 */     this.field_70159_w *= 0.985D;
/* 112 */     this.field_70181_x *= 0.985D;
/* 113 */     this.field_70179_y *= 0.985D;
/*     */     
/* 115 */     double dx = this.targetX - this.field_70165_t;
/* 116 */     double dy = this.targetY - this.field_70163_u;
/* 117 */     double dz = this.targetZ - this.field_70161_v;
/* 118 */     double d13 = 0.3D;
/* 119 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 121 */     if (d11 < 4.0D) {
/* 122 */       this.field_70544_f *= 0.9F;
/* 123 */       d13 = 0.6D;
/*     */     }
/*     */     
/* 126 */     dx /= d11;
/* 127 */     dy /= d11;
/* 128 */     dz /= d11;
/*     */     
/* 130 */     this.field_70159_w += dx * d13;
/* 131 */     this.field_70181_x += dy * d13;
/* 132 */     this.field_70179_y += dz * d13;
/*     */     
/* 134 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.35F, 0.35F);
/* 135 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.35F, 0.35F);
/* 136 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.35F, 0.35F);
/*     */   }
/*     */   
/*     */   public void setGravity(float value) {
/* 140 */     this.field_70545_g = value;
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
/* 237 */   public int particle = 24;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXBoreSparkle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */