/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class FXWispyOrb
/*     */   extends EntityFX
/*     */ {
/*  14 */   public int particle = 128;
/*  15 */   float as = 0.0F;
/*     */   
/*     */   public FXWispyOrb(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int age)
/*     */   {
/*  19 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  20 */     this.field_70552_h = (0.25F + this.field_70146_Z.nextFloat() * 0.75F);
/*  21 */     this.field_70553_i = (0.25F + this.field_70146_Z.nextFloat() * 0.75F);
/*  22 */     this.field_70551_j = (0.25F + this.field_70146_Z.nextFloat() * 0.75F);
/*  23 */     func_70105_a(0.02F, 0.02F);
/*  24 */     this.field_70145_X = true;
/*  25 */     this.field_70544_f *= (this.field_70146_Z.nextFloat() * 0.5F + 0.3F);
/*  26 */     this.field_70159_w = (par8 * 0.20000000298023224D + (float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/*  27 */     this.field_70181_x = (par10 * 0.20000000298023224D + (float)Math.random() * 0.02F);
/*  28 */     this.field_70179_y = (par12 * 0.20000000298023224D + (float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/*  29 */     this.field_70547_e = ((int)(age + age / 2 * this.field_70146_Z.nextFloat()));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  36 */     this.field_70169_q = this.field_70165_t;
/*  37 */     this.field_70167_r = this.field_70163_u;
/*  38 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*  41 */   public double bubblespeed = 0.002D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  49 */     this.field_70169_q = this.field_70165_t;
/*  50 */     this.field_70167_r = this.field_70163_u;
/*  51 */     this.field_70166_s = this.field_70161_v;
/*  52 */     this.field_70181_x += this.bubblespeed;
/*  53 */     if (this.bubblespeed > 0.0D) {
/*  54 */       this.field_70159_w += (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.01F;
/*  55 */       this.field_70179_y += (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.01F;
/*     */     }
/*     */     
/*  58 */     this.field_70165_t += this.field_70159_w;
/*  59 */     this.field_70163_u += this.field_70181_x;
/*  60 */     this.field_70161_v += this.field_70179_y;
/*     */     
/*  62 */     this.field_70159_w *= 0.8500000238418579D;
/*  63 */     this.field_70181_x *= 0.8500000238418579D;
/*  64 */     this.field_70179_y *= 0.8500000238418579D;
/*     */     
/*  66 */     if (this.field_70547_e-- <= 0)
/*     */     {
/*  68 */       func_70106_y();
/*     */     }
/*     */     
/*  71 */     this.particle += 1;
/*     */     
/*  73 */     this.field_82339_as -= this.as;
/*     */     
/*  75 */     if (this.particle >= 144) this.particle = 128;
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/*  79 */     this.field_70552_h = r;
/*  80 */     this.field_70553_i = g;
/*  81 */     this.field_70551_j = b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82338_g(float alpha)
/*     */   {
/*  88 */     super.func_82338_g(alpha);
/*  89 */     this.as = (this.field_82339_as / this.field_70547_e);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  96 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as);
/*     */     
/*  98 */     float var8 = this.particle % 16 / 16.0F;
/*  99 */     float var9 = var8 + 0.0624375F;
/* 100 */     float var10 = this.particle / 16 / 16.0F;
/* 101 */     float var11 = var10 + 0.0624375F;
/* 102 */     float var12 = 0.1F * this.field_70544_f;
/* 103 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 104 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 105 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 106 */     float var16 = 1.0F;
/*     */     
/* 108 */     int i = 240;
/* 109 */     int j = i >> 16 & 0xFFFF;
/* 110 */     int k = i & 0xFFFF;
/*     */     
/* 112 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*     */     
/* 114 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*     */     
/* 116 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*     */     
/* 118 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXWispyOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */