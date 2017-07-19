/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXVent extends EntityFX
/*     */ {
/*     */   public FXVent(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int color)
/*     */   {
/*  19 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  20 */     func_70105_a(0.02F, 0.02F);
/*  21 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.1F + 0.05F);
/*  22 */     this.field_70159_w = par8;
/*  23 */     this.field_70181_x = par10;
/*  24 */     this.field_70179_y = par12;
/*  25 */     this.field_70145_X = true;
/*     */     
/*  27 */     Color c = new Color(color);
/*  28 */     this.field_70552_h = (c.getRed() / 255.0F);
/*  29 */     this.field_70551_j = (c.getBlue() / 255.0F);
/*  30 */     this.field_70553_i = (c.getGreen() / 255.0F);
/*     */     
/*  32 */     setHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, 0.125F, 5.0F);
/*     */     
/*  34 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/*  35 */     int visibleDistance = 50;
/*  36 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  37 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*  39 */     this.field_70169_q = this.field_70165_t;
/*  40 */     this.field_70167_r = this.field_70163_u;
/*  41 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*  44 */   float psm = 1.0F;
/*     */   
/*  46 */   public void setScale(float f) { this.field_70544_f *= f;
/*  47 */     this.psm *= f;
/*     */   }
/*     */   
/*     */   public void setHeading(double par1, double par3, double par5, float par7, float par8)
/*     */   {
/*  52 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*  53 */     par1 /= f2;
/*  54 */     par3 /= f2;
/*  55 */     par5 /= f2;
/*  56 */     par1 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*  57 */     par3 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*  58 */     par5 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*  59 */     par1 *= par7;
/*  60 */     par3 *= par7;
/*  61 */     par5 *= par7;
/*  62 */     this.field_70159_w = par1;
/*  63 */     this.field_70181_x = par3;
/*  64 */     this.field_70179_y = par5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  72 */     this.field_70169_q = this.field_70165_t;
/*  73 */     this.field_70167_r = this.field_70163_u;
/*  74 */     this.field_70166_s = this.field_70161_v;
/*  75 */     this.field_70546_d += 1;
/*  76 */     if (this.field_70544_f >= this.psm)
/*     */     {
/*  78 */       func_70106_y();
/*     */     }
/*     */     
/*  81 */     this.field_70181_x += 0.0025D;
/*  82 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*  83 */     this.field_70159_w *= 0.8500000190734863D;
/*  84 */     this.field_70181_x *= 0.8500000190734863D;
/*  85 */     this.field_70179_y *= 0.8500000190734863D;
/*  86 */     if (this.field_70544_f < this.psm) this.field_70544_f = ((float)(this.field_70544_f * 1.15D));
/*  87 */     if (this.field_70544_f > this.psm) this.field_70544_f = this.psm;
/*  88 */     if (this.field_70122_E)
/*     */     {
/*  90 */       this.field_70159_w *= 0.699999988079071D;
/*  91 */       this.field_70179_y *= 0.699999988079071D;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/*  96 */     this.field_70552_h = r;
/*  97 */     this.field_70553_i = g;
/*  98 */     this.field_70551_j = b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 105 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.33F);
/* 106 */     int part = (int)(1.0F + this.field_70544_f / this.psm * 4.0F);
/* 107 */     float var8 = part % 16 / 16.0F;
/* 108 */     float var9 = var8 + 0.0624375F;
/* 109 */     float var10 = part / 16 / 16.0F;
/* 110 */     float var11 = var10 + 0.0624375F;
/* 111 */     float var12 = 0.3F * this.field_70544_f;
/*     */     
/* 113 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 114 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 115 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 116 */     float var16 = 1.0F;
/*     */     
/* 118 */     int i = func_70070_b(f);
/* 119 */     int j = i >> 16 & 0xFFFF;
/* 120 */     int k = i & 0xFFFF;
/*     */     
/* 122 */     float alpha = this.field_82339_as * ((this.psm - this.field_70544_f) / this.psm);
/* 123 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha).func_181671_a(j, k).func_181675_d();
/*     */     
/* 125 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha).func_181671_a(j, k).func_181675_d();
/*     */     
/* 127 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha).func_181671_a(j, k).func_181675_d();
/*     */     
/* 129 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 137 */     return 1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXVent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */