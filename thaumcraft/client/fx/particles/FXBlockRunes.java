/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXBlockRunes extends EntityFX
/*     */ {
/*     */   public FXBlockRunes(net.minecraft.world.World world, double d, double d1, double d2, float f1, float f2, float f3, int m)
/*     */   {
/*  15 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*  16 */     if (f1 == 0.0F) { f1 = 1.0F;
/*     */     }
/*  18 */     this.rotation = (this.field_70146_Z.nextInt(4) * 90);
/*     */     
/*  20 */     this.field_70552_h = f1;
/*  21 */     this.field_70553_i = f2;
/*  22 */     this.field_70551_j = f3;
/*  23 */     this.field_70545_g = 0.0F;
/*  24 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  25 */     this.field_70547_e = (3 * m);
/*  26 */     this.field_70145_X = false;
/*  27 */     func_70105_a(0.01F, 0.01F);
/*  28 */     this.field_70169_q = this.field_70165_t;
/*  29 */     this.field_70167_r = this.field_70163_u;
/*  30 */     this.field_70166_s = this.field_70161_v;
/*  31 */     this.field_70145_X = true;
/*  32 */     this.runeIndex = ((int)(Math.random() * 16.0D + 224.0D));
/*  33 */     this.ofx = (this.field_70146_Z.nextFloat() * 0.2D);
/*  34 */     this.ofy = (-0.3D + this.field_70146_Z.nextFloat() * 0.6D);
/*  35 */     this.field_70544_f = ((float)(1.0D + this.field_70146_Z.nextGaussian() * 0.10000000149011612D));
/*  36 */     this.field_82339_as = 0.0F;
/*     */   }
/*     */   
/*  39 */   double ofx = 0.0D;
/*  40 */   double ofy = 0.0D;
/*  41 */   float rotation = 0.0F;
/*  42 */   int runeIndex = 0;
/*     */   
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  47 */     Tessellator.func_178181_a().func_78381_a();
/*  48 */     GL11.glPushMatrix();
/*     */     
/*  50 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as / 2.0F);
/*     */     
/*  52 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  53 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  54 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  56 */     GL11.glTranslated(var13, var14, var15);
/*  57 */     GL11.glRotatef(this.rotation, 0.0F, 1.0F, 0.0F);
/*  58 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  59 */     GL11.glTranslated(this.ofx, this.ofy, -0.51D);
/*     */     
/*  61 */     float var8 = this.runeIndex % 16 / 16.0F;
/*  62 */     float var9 = var8 + 0.0624375F;
/*  63 */     float var10 = 0.375F;
/*  64 */     float var11 = var10 + 0.0624375F;
/*  65 */     float var12 = 0.3F * this.field_70544_f;
/*     */     
/*  67 */     float var16 = 1.0F;
/*     */     
/*  69 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */     
/*  71 */     int i = 240;
/*  72 */     int j = i >> 16 & 0xFFFF;
/*  73 */     int k = i & 0xFFFF;
/*     */     
/*  75 */     wr.func_181662_b(-0.5D * var12, 0.5D * var12, 0.0D).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/*  76 */     wr.func_181662_b(0.5D * var12, 0.5D * var12, 0.0D).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/*  77 */     wr.func_181662_b(0.5D * var12, -0.5D * var12, 0.0D).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/*  78 */     wr.func_181662_b(-0.5D * var12, -0.5D * var12, 0.0D).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/*  79 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/*  81 */     GL11.glPopMatrix();
/*  82 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  89 */     this.field_70169_q = this.field_70165_t;
/*  90 */     this.field_70167_r = this.field_70163_u;
/*  91 */     this.field_70166_s = this.field_70161_v;
/*  92 */     float threshold = this.field_70547_e / 5.0F;
/*     */     
/*  94 */     if (this.field_70546_d <= threshold) {
/*  95 */       this.field_82339_as = (this.field_70546_d / threshold);
/*     */     } else {
/*  97 */       this.field_82339_as = ((this.field_70547_e - this.field_70546_d) / this.field_70547_e);
/*     */     }
/*     */     
/* 100 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 101 */       func_70106_y();
/*     */     }
/*     */     
/* 104 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/*     */     
/* 106 */     this.field_70165_t += this.field_70159_w;
/* 107 */     this.field_70163_u += this.field_70181_x;
/* 108 */     this.field_70161_v += this.field_70179_y;
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 113 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXBlockRunes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */