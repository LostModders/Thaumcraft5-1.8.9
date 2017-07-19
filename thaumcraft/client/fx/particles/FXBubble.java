/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXBubble extends EntityFX
/*     */ {
/*  14 */   public int particle = 16;
/*     */   
/*     */   public FXBubble(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int age)
/*     */   {
/*  18 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  19 */     this.field_70552_h = 1.0F;
/*  20 */     this.field_70553_i = 0.0F;
/*  21 */     this.field_70551_j = 0.5F;
/*  22 */     func_70105_a(0.02F, 0.02F);
/*  23 */     this.field_70145_X = true;
/*  24 */     this.field_70544_f *= (this.field_70146_Z.nextFloat() * 0.3F + 0.2F);
/*  25 */     this.field_70159_w = (par8 * 0.20000000298023224D + (float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/*  26 */     this.field_70181_x = (par10 * 0.20000000298023224D + (float)Math.random() * 0.02F);
/*  27 */     this.field_70179_y = (par12 * 0.20000000298023224D + (float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/*  28 */     this.field_70547_e = ((int)(age + 2 + 8.0D / (Math.random() * 0.8D + 0.2D)));
/*     */     
/*  30 */     Entity renderentity = Minecraft.func_71410_x().func_175606_aa();
/*  31 */     int visibleDistance = 50;
/*  32 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  33 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*  35 */     this.field_70169_q = this.field_70165_t;
/*  36 */     this.field_70167_r = this.field_70163_u;
/*  37 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*     */   public void setFroth() {
/*  41 */     this.field_70544_f *= 0.75F;
/*  42 */     this.field_70547_e = (4 + this.field_70146_Z.nextInt(3));
/*  43 */     this.bubblespeed = -0.001D;
/*  44 */     this.field_70159_w /= 5.0D;
/*  45 */     this.field_70181_x /= 10.0D;
/*  46 */     this.field_70179_y /= 5.0D;
/*     */   }
/*     */   
/*     */   public void setFroth2() {
/*  50 */     this.field_70544_f *= 0.75F;
/*  51 */     this.field_70547_e = (12 + this.field_70146_Z.nextInt(12));
/*  52 */     this.bubblespeed = -0.005D;
/*  53 */     this.field_70159_w /= 5.0D;
/*  54 */     this.field_70181_x /= 10.0D;
/*  55 */     this.field_70179_y /= 5.0D;
/*     */   }
/*     */   
/*  58 */   public double bubblespeed = 0.002D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  66 */     this.field_70169_q = this.field_70165_t;
/*  67 */     this.field_70167_r = this.field_70163_u;
/*  68 */     this.field_70166_s = this.field_70161_v;
/*  69 */     this.field_70181_x += this.bubblespeed;
/*  70 */     if (this.bubblespeed > 0.0D) {
/*  71 */       this.field_70159_w += (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.01F;
/*  72 */       this.field_70179_y += (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.01F;
/*     */     }
/*     */     
/*  75 */     this.field_70165_t += this.field_70159_w;
/*  76 */     this.field_70163_u += this.field_70181_x;
/*  77 */     this.field_70161_v += this.field_70179_y;
/*     */     
/*  79 */     this.field_70159_w *= 0.8500000238418579D;
/*  80 */     this.field_70181_x *= 0.8500000238418579D;
/*  81 */     this.field_70179_y *= 0.8500000238418579D;
/*     */     
/*  83 */     if (this.field_70547_e-- <= 0)
/*     */     {
/*  85 */       func_70106_y();
/*     */ 
/*     */     }
/*  88 */     else if (this.field_70547_e <= 2) {
/*  89 */       this.particle += 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/*  94 */     this.field_70552_h = r;
/*  95 */     this.field_70553_i = g;
/*  96 */     this.field_70551_j = b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 103 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as);
/*     */     
/* 105 */     float var8 = this.particle % 16 / 16.0F;
/* 106 */     float var9 = var8 + 0.0624375F;
/* 107 */     float var10 = this.particle / 16 / 16.0F;
/* 108 */     float var11 = var10 + 0.0624375F;
/* 109 */     float var12 = 0.1F * this.field_70544_f;
/* 110 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 111 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 112 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 113 */     float var16 = 1.0F;
/*     */     
/* 115 */     int i = 240;
/* 116 */     int j = i >> 16 & 0xFFFF;
/* 117 */     int k = i & 0xFFFF;
/*     */     
/* 119 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*     */     
/* 121 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*     */     
/* 123 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*     */     
/* 125 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXBubble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */