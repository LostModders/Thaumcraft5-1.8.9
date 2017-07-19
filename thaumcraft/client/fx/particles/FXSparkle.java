/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ 
/*     */ public class FXSparkle extends EntityFX
/*     */ {
/*     */   public FXSparkle(World world, double d, double d1, double d2, float f, float f1, float f2, float f3, int m)
/*     */   {
/*  15 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*  16 */     if (f1 == 0.0F) {
/*  17 */       f1 = 1.0F;
/*     */     }
/*  19 */     this.field_70552_h = f1;
/*  20 */     this.field_70553_i = f2;
/*  21 */     this.field_70551_j = f3;
/*  22 */     this.field_70545_g = 0.0F;
/*  23 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  24 */     this.field_70544_f *= f;
/*  25 */     this.field_70547_e = (3 * m);
/*  26 */     this.multiplier = m;
/*  27 */     this.field_70145_X = false;
/*  28 */     func_70105_a(0.01F, 0.01F);
/*  29 */     this.field_70169_q = this.field_70165_t;
/*  30 */     this.field_70167_r = this.field_70163_u;
/*  31 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*     */   public FXSparkle(World world, double d, double d1, double d2, float f, int type, int m) {
/*  35 */     this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F, m);
/*  36 */     this.currentColor = type;
/*  37 */     switch (type) {
/*     */     case 0: 
/*  39 */       this.field_70552_h = (0.75F + world.field_73012_v.nextFloat() * 0.25F);
/*  40 */       this.field_70553_i = (0.25F + world.field_73012_v.nextFloat() * 0.25F);
/*  41 */       this.field_70551_j = (0.75F + world.field_73012_v.nextFloat() * 0.25F);
/*  42 */       break;
/*     */     case 1: 
/*  44 */       this.field_70552_h = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  45 */       this.field_70553_i = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  46 */       this.field_70551_j = 0.2F;
/*  47 */       break;
/*     */     case 2: 
/*  49 */       this.field_70552_h = 0.2F;
/*  50 */       this.field_70553_i = 0.2F;
/*  51 */       this.field_70551_j = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  52 */       break;
/*     */     case 3: 
/*  54 */       this.field_70552_h = 0.2F;
/*  55 */       this.field_70553_i = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  56 */       this.field_70551_j = 0.2F;
/*  57 */       break;
/*     */     case 4: 
/*  59 */       this.field_70552_h = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  60 */       this.field_70553_i = 0.2F;
/*  61 */       this.field_70551_j = 0.2F;
/*  62 */       break;
/*     */     case 5: 
/*  64 */       this.blendmode = 771;
/*  65 */       this.field_70552_h = (world.field_73012_v.nextFloat() * 0.1F);
/*  66 */       this.field_70553_i = (world.field_73012_v.nextFloat() * 0.1F);
/*  67 */       this.field_70551_j = (world.field_73012_v.nextFloat() * 0.1F);
/*  68 */       break;
/*     */     case 6: 
/*  70 */       this.field_70552_h = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  71 */       this.field_70553_i = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  72 */       this.field_70551_j = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  73 */       break;
/*     */     case 7: 
/*  75 */       this.field_70552_h = 0.2F;
/*  76 */       this.field_70553_i = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  77 */       this.field_70551_j = (0.6F + world.field_73012_v.nextFloat() * 0.3F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   public FXSparkle(World world, double d, double d1, double d2, double x, double y, double z, float f, int type, int m)
/*     */   {
/*  85 */     this(world, d, d1, d2, f, type, m);
/*     */     
/*  87 */     double dx = x - this.field_70165_t;
/*  88 */     double dy = y - this.field_70163_u;
/*  89 */     double dz = z - this.field_70161_v;
/*     */     
/*  91 */     this.field_70159_w = (dx / this.field_70547_e);
/*  92 */     this.field_70181_x = (dy / this.field_70547_e);
/*  93 */     this.field_70179_y = (dz / this.field_70547_e);
/*     */   }
/*     */   
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  98 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  99 */     int part = this.particle + this.field_70546_d / this.multiplier;
/*     */     
/* 101 */     float var8 = part % 4 / 16.0F;
/* 102 */     float var9 = var8 + 0.0624375F;
/* 103 */     float var10 = 0.25F;
/* 104 */     float var11 = var10 + 0.0624375F;
/* 105 */     float var12 = 0.1F * this.field_70544_f;
/* 106 */     if (this.shrink)
/* 107 */       var12 *= (this.field_70547_e - this.field_70546_d + 1) / this.field_70547_e;
/* 108 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 109 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 110 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 111 */     float var16 = 1.0F;
/*     */     
/* 113 */     int i = 240;
/* 114 */     int j = i >> 16 & 0xFFFF;
/* 115 */     int k = i & 0xFFFF;
/*     */     
/* 117 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 119 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 121 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 123 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 130 */     return this.blendmode == 1 ? 0 : 1;
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
/*     */   public void func_70071_h_()
/*     */   {
/* 145 */     this.field_70169_q = this.field_70165_t;
/* 146 */     this.field_70167_r = this.field_70163_u;
/* 147 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 149 */     if ((this.field_70546_d == 0) && (this.tinkle) && (this.field_70170_p.field_73012_v.nextInt(10) == 0)) {
/* 150 */       this.field_70170_p.func_72956_a(this, "random.orb", 0.02F, 0.7F * ((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.6F + 2.0F));
/*     */     }
/*     */     
/*     */ 
/* 154 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 156 */       func_70106_y();
/*     */     }
/*     */     
/* 159 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/* 160 */     if (!this.field_70145_X) {
/* 161 */       func_145771_j(this.field_70165_t, (func_174813_aQ().field_72338_b + func_174813_aQ().field_72337_e) / 2.0D, this.field_70161_v);
/*     */     }
/*     */     
/*     */ 
/* 165 */     this.field_70165_t += this.field_70159_w;
/* 166 */     this.field_70163_u += this.field_70181_x;
/* 167 */     this.field_70161_v += this.field_70179_y;
/* 168 */     if (this.slowdown) {
/* 169 */       this.field_70159_w *= 0.9080000019073486D;
/* 170 */       this.field_70181_x *= 0.9080000019073486D;
/* 171 */       this.field_70179_y *= 0.9080000019073486D;
/* 172 */       if (this.field_70122_E) {
/* 173 */         this.field_70159_w *= 0.699999988079071D;
/* 174 */         this.field_70179_y *= 0.699999988079071D;
/*     */       }
/*     */     }
/*     */     
/* 178 */     if (this.leyLineEffect)
/*     */     {
/* 180 */       FXSparkle fx = new FXSparkle(this.field_70170_p, this.field_70169_q + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, this.field_70167_r + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, this.field_70166_s + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, 1.0F, this.currentColor, 3 + this.field_70170_p.field_73012_v.nextInt(3));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 187 */       fx.field_70145_X = true;
/* 188 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 194 */     this.field_70545_g = value;
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
/* 290 */   public boolean leyLineEffect = false;
/* 291 */   public int multiplier = 2;
/* 292 */   public boolean shrink = true;
/* 293 */   public int particle = 16;
/* 294 */   public boolean tinkle = false;
/* 295 */   public int blendmode = 1;
/* 296 */   public boolean slowdown = true;
/* 297 */   public int currentColor = 0;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXSparkle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */