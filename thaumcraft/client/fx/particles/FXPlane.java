/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXPlane extends EntityFX
/*     */ {
/*     */   float angle;
/*     */   float angleYaw;
/*     */   float anglePitch;
/*     */   
/*     */   public FXPlane(net.minecraft.world.World world, double d, double d1, double d2, double m, double m1, double m2, int life)
/*     */   {
/*  19 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  21 */     this.field_70552_h = 1.0F;
/*  22 */     this.field_70553_i = 1.0F;
/*  23 */     this.field_70551_j = 1.0F;
/*  24 */     this.field_70545_g = 0.0F;
/*  25 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  26 */     this.field_70547_e = life;
/*  27 */     this.field_70145_X = false;
/*  28 */     func_70105_a(0.01F, 0.01F);
/*  29 */     this.field_70169_q = this.field_70165_t;
/*  30 */     this.field_70167_r = this.field_70163_u;
/*  31 */     this.field_70166_s = this.field_70161_v;
/*  32 */     this.field_70145_X = true;
/*  33 */     this.field_70544_f = 1.0F;
/*  34 */     this.field_82339_as = 0.0F;
/*     */     
/*     */ 
/*  37 */     double dx = m - this.field_70165_t;
/*  38 */     double dy = m1 - this.field_70163_u;
/*  39 */     double dz = m2 - this.field_70161_v;
/*     */     
/*  41 */     this.field_70159_w = (dx / this.field_70547_e);
/*  42 */     this.field_70181_x = (dy / this.field_70547_e);
/*  43 */     this.field_70179_y = (dz / this.field_70547_e);
/*     */     
/*  45 */     this.field_94054_b = 6;
/*  46 */     this.field_94055_c = 10;
/*     */     
/*  48 */     double d3 = MathHelper.func_76133_a(dx * dx + dz * dz);
/*  49 */     this.angleYaw = 0.0F;
/*  50 */     this.anglePitch = 0.0F;
/*  51 */     if (d3 >= 1.0E-7D)
/*     */     {
/*  53 */       this.angleYaw = ((float)(MathHelper.func_181159_b(dz, dx) * 180.0D / 3.141592653589793D) - 90.0F);
/*  54 */       this.anglePitch = ((float)-(MathHelper.func_181159_b(dy, d3) * 180.0D / 3.141592653589793D));
/*     */     }
/*     */     
/*  57 */     this.angle = ((float)(this.field_70146_Z.nextGaussian() * 20.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  68 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  75 */     Tessellator.func_178181_a().func_78381_a();
/*  76 */     GL11.glPushMatrix();
/*     */     
/*  78 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as / 2.0F);
/*     */     
/*  80 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  81 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  82 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  84 */     GL11.glTranslated(var13, var14, var15);
/*  85 */     GL11.glRotatef(-this.angleYaw + 90.0F, 0.0F, 1.0F, 0.0F);
/*  86 */     GL11.glRotatef(this.anglePitch + 90.0F, 0.0F, 0.0F, 1.0F);
/*  87 */     GL11.glRotatef(this.angle, 0.0F, 1.0F, 0.0F);
/*     */     
/*     */ 
/*  90 */     this.field_94054_b = Math.round((this.field_70546_d + f) / this.field_70547_e * 8.0F);
/*     */     
/*  92 */     float var8 = this.field_94054_b / 16.0F;
/*  93 */     float var9 = var8 + 0.0625F;
/*  94 */     float var10 = this.field_94055_c / 16.0F;
/*  95 */     float var11 = var10 + 0.0625F;
/*     */     
/*  97 */     float var12 = this.field_70544_f * (0.5F + (this.field_70546_d + f) / this.field_70547_e);
/*     */     
/*  99 */     float var16 = 1.0F;
/*     */     
/*     */ 
/*     */ 
/* 103 */     int i = 240;
/* 104 */     int j = i >> 16 & 0xFFFF;
/* 105 */     int k = i & 0xFFFF;
/*     */     
/* 107 */     GL11.glDisable(2884);
/*     */     
/* 109 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 110 */     wr.func_181662_b(-0.5D * var12, 0.5D * var12, 0.0D).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/* 111 */     wr.func_181662_b(0.5D * var12, 0.5D * var12, 0.0D).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/* 112 */     wr.func_181662_b(0.5D * var12, -0.5D * var12, 0.0D).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/* 113 */     wr.func_181662_b(-0.5D * var12, -0.5D * var12, 0.0D).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/* 114 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 116 */     GL11.glEnable(2884);
/*     */     
/* 118 */     GL11.glPopMatrix();
/* 119 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 126 */     this.field_70169_q = this.field_70165_t;
/* 127 */     this.field_70167_r = this.field_70163_u;
/* 128 */     this.field_70166_s = this.field_70161_v;
/* 129 */     float threshold = this.field_70547_e / 5.0F;
/*     */     
/* 131 */     if (this.field_70546_d <= threshold) {
/* 132 */       this.field_82339_as = (this.field_70546_d / threshold);
/*     */     } else {
/* 134 */       this.field_82339_as = ((this.field_70547_e - this.field_70546_d) / this.field_70547_e);
/*     */     }
/*     */     
/* 137 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 138 */       func_70106_y();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 143 */     this.field_70165_t += this.field_70159_w;
/* 144 */     this.field_70163_u += this.field_70181_x;
/* 145 */     this.field_70161_v += this.field_70179_y;
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 150 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXPlane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */