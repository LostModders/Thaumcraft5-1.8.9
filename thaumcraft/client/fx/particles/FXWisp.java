/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ 
/*     */ public class FXWisp extends EntityFX
/*     */ {
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, float f1, float f2)
/*     */   {
/*  16 */     this(world, d, d1, d2, 1.0F, f, f1, f2);
/*     */   }
/*     */   
/*     */ 
/*  20 */   Entity target = null;
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, float red, float green, float blue)
/*     */   {
/*  24 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*  25 */     if (red == 0.0F) {
/*  26 */       red = 1.0F;
/*     */     }
/*  28 */     this.field_70552_h = red;
/*  29 */     this.field_70553_i = green;
/*  30 */     this.field_70551_j = blue;
/*  31 */     this.field_70545_g = 0.0F;
/*  32 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  33 */     this.field_70544_f *= f;
/*  34 */     this.moteParticleScale = this.field_70544_f;
/*  35 */     this.field_70547_e = ((int)(36.0D / (Math.random() * 0.3D + 0.7D)));
/*  36 */     this.moteHalfLife = (this.field_70547_e / 2);
/*  37 */     this.field_70145_X = false;
/*  38 */     func_70105_a(0.1F, 0.1F);
/*  39 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/*  40 */     int visibleDistance = 50;
/*  41 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  42 */       visibleDistance = 25;
/*  43 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance)
/*  44 */       this.field_70547_e = 0;
/*  45 */     this.field_70169_q = this.field_70165_t;
/*  46 */     this.field_70167_r = this.field_70163_u;
/*  47 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, int type) {
/*  51 */     this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F);
/*     */     
/*  53 */     switch (type) {
/*     */     case 0: 
/*  55 */       this.field_70552_h = (0.75F + world.field_73012_v.nextFloat() * 0.25F);
/*  56 */       this.field_70553_i = (0.25F + world.field_73012_v.nextFloat() * 0.25F);
/*  57 */       this.field_70551_j = (0.75F + world.field_73012_v.nextFloat() * 0.25F);
/*  58 */       break;
/*     */     case 1: 
/*  60 */       this.field_70552_h = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  61 */       this.field_70553_i = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  62 */       this.field_70551_j = 0.2F;
/*  63 */       break;
/*     */     case 2: 
/*  65 */       this.field_70552_h = 0.2F;
/*  66 */       this.field_70553_i = 0.2F;
/*  67 */       this.field_70551_j = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  68 */       break;
/*     */     case 3: 
/*  70 */       this.field_70552_h = 0.2F;
/*  71 */       this.field_70553_i = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  72 */       this.field_70551_j = 0.2F;
/*  73 */       break;
/*     */     case 4: 
/*  75 */       this.field_70552_h = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  76 */       this.field_70553_i = 0.2F;
/*  77 */       this.field_70551_j = 0.2F;
/*  78 */       break;
/*     */     case 5: 
/*  80 */       this.blendmode = 771;
/*  81 */       this.field_70552_h = (world.field_73012_v.nextFloat() * 0.1F);
/*  82 */       this.field_70553_i = (world.field_73012_v.nextFloat() * 0.1F);
/*  83 */       this.field_70551_j = (world.field_73012_v.nextFloat() * 0.1F);
/*  84 */       break;
/*     */     case 6: 
/*  86 */       this.field_70552_h = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  87 */       this.field_70553_i = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  88 */       this.field_70551_j = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  89 */       break;
/*     */     case 7: 
/*  91 */       this.field_70552_h = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  92 */       this.field_70553_i = (0.5F + world.field_73012_v.nextFloat() * 0.2F);
/*  93 */       this.field_70551_j = (0.3F + world.field_73012_v.nextFloat() * 0.1F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, int type)
/*     */   {
/* 101 */     this(world, d, d1, d2, f, type);
/* 102 */     if (this.field_70547_e > 0) {
/* 103 */       double dx = x - this.field_70165_t;
/* 104 */       double dy = y - this.field_70163_u;
/* 105 */       double dz = z - this.field_70161_v;
/*     */       
/* 107 */       this.field_70159_w = (dx / this.field_70547_e);
/* 108 */       this.field_70181_x = (dy / this.field_70547_e);
/* 109 */       this.field_70179_y = (dz / this.field_70547_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public FXWisp(World world, double d, double d1, double d2, Entity tar, int type)
/*     */   {
/* 116 */     this(world, d, d1, d2, 0.4F, type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue)
/*     */   {
/* 123 */     this(world, d, d1, d2, f, red, green, blue);
/* 124 */     if (this.field_70547_e > 0) {
/* 125 */       double dx = x - this.field_70165_t;
/* 126 */       double dy = y - this.field_70163_u;
/* 127 */       double dz = z - this.field_70161_v;
/*     */       
/* 129 */       this.field_70159_w = (dx / this.field_70547_e);
/* 130 */       this.field_70181_x = (dy / this.field_70547_e);
/* 131 */       this.field_70179_y = (dz / this.field_70547_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 139 */     float agescale = 0.0F;
/* 140 */     if (this.shrink) {
/* 141 */       agescale = (this.field_70547_e - this.field_70546_d) / this.field_70547_e;
/*     */     }
/*     */     else {
/* 144 */       agescale = this.field_70546_d / this.moteHalfLife;
/* 145 */       if (agescale > 1.0F) {
/* 146 */         agescale = 2.0F - agescale;
/*     */       }
/*     */     }
/* 149 */     this.field_70544_f = (this.moteParticleScale * agescale);
/*     */     
/* 151 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*     */     
/* 153 */     float f10 = 0.5F * this.field_70544_f;
/* 154 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 155 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 156 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/* 158 */     float var8 = 0.0F;
/* 159 */     float var9 = 0.125F;
/* 160 */     float var10 = 0.875F;
/* 161 */     float var11 = 1.0F;
/*     */     
/* 163 */     int i = 240;
/* 164 */     int j = i >> 16 & 0xFFFF;
/* 165 */     int k = i & 0xFFFF;
/*     */     
/*     */ 
/* 168 */     wr.func_181662_b(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10).func_181673_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F).func_181671_a(j, k).func_181675_d();
/* 169 */     wr.func_181662_b(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10).func_181673_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F).func_181671_a(j, k).func_181675_d();
/* 170 */     wr.func_181662_b(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10).func_181673_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F).func_181671_a(j, k).func_181675_d();
/* 171 */     wr.func_181662_b(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10).func_181673_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 177 */     return this.blendmode == 1 ? 0 : 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 182 */     this.field_70169_q = this.field_70165_t;
/* 183 */     this.field_70167_r = this.field_70163_u;
/* 184 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 186 */     if ((this.field_70546_d == 0) && (this.tinkle) && (this.field_70170_p.field_73012_v.nextInt(3) == 0)) {
/* 187 */       this.field_70170_p.func_72956_a(this, "random.orb", 0.02F, 0.5F * ((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.6F + 2.0F));
/*     */     }
/*     */     
/*     */ 
/* 191 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 193 */       func_70106_y();
/*     */     }
/*     */     
/* 196 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/*     */     
/* 198 */     if (!this.field_70145_X)
/* 199 */       func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 200 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 202 */     if (this.target != null)
/*     */     {
/*     */ 
/* 205 */       this.field_70159_w *= 0.985D;
/* 206 */       this.field_70181_x *= 0.985D;
/* 207 */       this.field_70179_y *= 0.985D;
/*     */       
/* 209 */       double dx = this.target.field_70165_t - this.field_70165_t;
/* 210 */       double dy = this.target.field_70163_u + this.target.field_70131_O / 2.0F - this.field_70163_u;
/* 211 */       double dz = this.target.field_70161_v - this.field_70161_v;
/* 212 */       double d13 = 0.2D;
/* 213 */       double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */       
/* 215 */       dx /= d11;
/* 216 */       dy /= d11;
/* 217 */       dz /= d11;
/*     */       
/* 219 */       this.field_70159_w += dx * d13;
/* 220 */       this.field_70181_x += dy * d13;
/* 221 */       this.field_70179_y += dz * d13;
/*     */       
/* 223 */       this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.2F, 0.2F);
/* 224 */       this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.2F, 0.2F);
/* 225 */       this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.2F, 0.2F);
/*     */     }
/*     */     else {
/* 228 */       this.field_70159_w *= 0.9800000190734863D;
/* 229 */       this.field_70181_x *= 0.9800000190734863D;
/* 230 */       this.field_70179_y *= 0.9800000190734863D;
/* 231 */       if (this.field_70122_E) {
/* 232 */         this.field_70159_w *= 0.699999988079071D;
/* 233 */         this.field_70179_y *= 0.699999988079071D;
/*     */       }
/*     */     }
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
/*     */ 
/*     */   public void setGravity(float value)
/*     */   {
/* 335 */     this.field_70545_g = value;
/*     */   }
/*     */   
/* 338 */   public boolean shrink = false;
/*     */   float moteParticleScale;
/*     */   int moteHalfLife;
/* 341 */   public boolean tinkle = false;
/* 342 */   public int blendmode = 1;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXWisp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */