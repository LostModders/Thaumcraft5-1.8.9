/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.utils.TCVec3;
/*     */ 
/*     */ public class FXGeneric extends EntityFX
/*     */ {
/*     */   public FXGeneric(World world, double x, double y, double z, double xx, double yy, double zz)
/*     */   {
/*  21 */     super(world, x, y, z, xx, yy, zz);
/*  22 */     func_70105_a(0.1F, 0.1F);
/*  23 */     func_70107_b(x, y, z);
/*  24 */     this.field_70169_q = this.field_70165_t;
/*  25 */     this.field_70167_r = this.field_70163_u;
/*  26 */     this.field_70166_s = this.field_70161_v;
/*  27 */     this.field_70142_S = x;
/*  28 */     this.field_70137_T = y;
/*  29 */     this.field_70136_U = z;
/*  30 */     this.field_70548_b = 0.0F;
/*  31 */     this.field_70549_c = 0.0F;
/*  32 */     this.field_70159_w = xx;
/*  33 */     this.field_70181_x = yy;
/*  34 */     this.field_70179_y = zz;
/*  35 */     this.field_70145_X = true;
/*     */   }
/*     */   
/*     */ 
/*  39 */   int layer = 0;
/*     */   
/*     */   public void setLayer(int layer)
/*     */   {
/*  43 */     this.layer = layer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70538_b(float particleRedIn, float particleGreenIn, float particleBlueIn)
/*     */   {
/*  50 */     super.func_70538_b(particleRedIn, particleGreenIn, particleBlueIn);
/*  51 */     this.dr = particleRedIn;
/*  52 */     this.dg = particleGreenIn;
/*  53 */     this.db = particleBlueIn;
/*     */   }
/*     */   
/*     */   public void setRBGColorF(float particleRedIn, float particleGreenIn, float particleBlueIn, float r2, float g2, float b2) {
/*  57 */     super.func_70538_b(particleRedIn, particleGreenIn, particleBlueIn);
/*  58 */     this.dr = r2;
/*  59 */     this.dg = g2;
/*  60 */     this.db = b2;
/*     */   }
/*     */   
/*  63 */   float dr = 0.0F;
/*  64 */   float dg = 0.0F;
/*  65 */   float db = 0.0F;
/*     */   
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  70 */     return this.layer;
/*     */   }
/*     */   
/*  73 */   boolean loop = false;
/*     */   
/*     */   public void setLoop(boolean loop) {
/*  76 */     this.loop = loop;
/*     */   }
/*     */   
/*  79 */   protected float particleScaleMod = 0.0F;
/*     */   
/*     */   public void setScale(float scale) {
/*  82 */     this.field_70544_f = scale;
/*     */   }
/*     */   
/*     */   public void setScale(float scale, float end) {
/*  86 */     this.field_70544_f = scale;
/*  87 */     this.particleScaleMod = ((scale - end) / this.field_70547_e);
/*     */   }
/*     */   
/*     */   public void setRotationSpeed(float rot) {
/*  91 */     this.rotationSpeed = rot;
/*     */   }
/*     */   
/*     */   public void setRotationSpeed(float start, float rot) {
/*  95 */     this.rotation = start;
/*  96 */     this.rotationSpeed = rot;
/*     */   }
/*     */   
/*  99 */   float rotationSpeed = 0.0F;
/* 100 */   float rotation = 0.0F;
/*     */   
/* 102 */   int delay = 0;
/*     */   
/*     */   public void setMaxAge(int max, int delay) {
/* 105 */     this.field_70547_e = max;
/* 106 */     this.field_70547_e += delay;
/* 107 */     this.delay = delay;
/*     */   }
/*     */   
/* 110 */   int startParticle = 0;
/* 111 */   int numParticles = 1;
/* 112 */   int particleInc = 1;
/*     */   
/*     */   public void setParticles(int startParticle, int numParticles, int particleInc) {
/* 115 */     this.startParticle = startParticle;
/* 116 */     this.numParticles = numParticles;
/* 117 */     this.particleInc = particleInc;
/* 118 */     func_70536_a(startParticle);
/*     */   }
/*     */   
/* 121 */   float particleAlphaMod = 0.0F;
/*     */   
/*     */   public void func_82338_g(float a1)
/*     */   {
/* 125 */     super.func_82338_g(a1);
/* 126 */     this.particleAlphaMod = 0.0F;
/*     */   }
/*     */   
/*     */   public void setAlphaF(float a1, float a2) {
/* 130 */     super.func_82338_g(a1);
/* 131 */     this.particleAlphaMod = ((a1 - a2) / this.field_70547_e);
/*     */   }
/*     */   
/* 134 */   double slowDown = 0.9800000190734863D;
/*     */   
/*     */   public void setSlowDown(double slowDown) {
/* 137 */     this.slowDown = slowDown;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 143 */     this.field_70169_q = this.field_70165_t;
/* 144 */     this.field_70167_r = this.field_70163_u;
/* 145 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 147 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 149 */       func_70106_y();
/*     */     }
/*     */     
/* 152 */     this.rotation += this.rotationSpeed;
/*     */     
/* 154 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 160 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 162 */     this.field_70159_w *= this.slowDown;
/* 163 */     this.field_70181_x *= this.slowDown;
/* 164 */     this.field_70179_y *= this.slowDown;
/*     */     
/*     */ 
/* 167 */     this.field_70159_w += this.windX;
/* 168 */     this.field_70179_y += this.windZ;
/*     */     
/* 170 */     if ((this.field_70122_E) && (this.slowDown != 1.0D))
/*     */     {
/* 172 */       this.field_70159_w *= 0.699999988079071D;
/* 173 */       this.field_70179_y *= 0.699999988079071D;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 178 */   boolean angled = false;
/*     */   
/* 180 */   public void func_70082_c(float yaw, float pitch) { this.angleYaw = yaw;
/* 181 */     this.anglePitch = pitch;
/* 182 */     this.angled = true;
/*     */   }
/*     */   
/*     */   float angleYaw;
/*     */   float anglePitch;
/*     */   public void setGravity(float g)
/*     */   {
/* 189 */     this.field_70545_g = g;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70536_a(int p_70536_1_)
/*     */   {
/* 195 */     this.field_94054_b = (p_70536_1_ % this.gridSize);
/* 196 */     this.field_94055_c = (p_70536_1_ / this.gridSize);
/*     */   }
/*     */   
/* 199 */   int gridSize = 16;
/*     */   double windX;
/*     */   
/* 202 */   public void setGridSize(int gridSize) { this.gridSize = gridSize; }
/*     */   
/*     */ 
/*     */   double windZ;
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 208 */     if (this.loop) {
/* 209 */       func_70536_a(this.startParticle + this.field_70546_d / this.particleInc % this.numParticles);
/*     */     } else {
/* 211 */       float fs = this.field_70546_d / this.field_70547_e;
/* 212 */       func_70536_a((int)(this.startParticle + Math.min(this.numParticles * fs, this.numParticles - 1)));
/*     */     }
/*     */     
/* 215 */     if (this.field_70546_d < this.delay) return;
/* 216 */     this.field_82339_as -= this.particleAlphaMod;
/* 217 */     float t = this.field_82339_as;
/* 218 */     if ((this.field_70546_d <= 1) || (this.field_70546_d >= this.field_70547_e - 1)) this.field_82339_as = (t / 2.0F);
/* 219 */     if (this.field_82339_as < 0.0F) this.field_82339_as = 0.0F;
/* 220 */     if (this.field_82339_as > 1.0F) { this.field_82339_as = 1.0F;
/*     */     }
/* 222 */     this.field_70544_f -= this.particleScaleMod;
/* 223 */     if (this.field_70544_f < 0.0F) { this.field_70544_f = 0.0F;
/*     */     }
/* 225 */     if (this.depthIgnore) GlStateManager.func_179097_i();
/* 226 */     draw(wr, entity, f, f1, f2, f3, f4, f5);
/* 227 */     if (this.depthIgnore) { GlStateManager.func_179126_j();
/*     */     }
/* 229 */     this.field_82339_as = t;
/*     */   }
/*     */   
/*     */   public void draw(WorldRenderer wr, Entity p_180434_2_, float pticks, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_)
/*     */   {
/* 234 */     float f6 = this.field_94054_b / this.gridSize;
/* 235 */     float f7 = f6 + 1.0F / this.gridSize;
/* 236 */     float f8 = this.field_94055_c / this.gridSize;
/* 237 */     float f9 = f8 + 1.0F / this.gridSize;
/* 238 */     float f10 = 0.1F * this.field_70544_f;
/*     */     
/* 240 */     if (this.field_70550_a != null)
/*     */     {
/* 242 */       f6 = this.field_70550_a.func_94209_e();
/* 243 */       f7 = this.field_70550_a.func_94212_f();
/* 244 */       f8 = this.field_70550_a.func_94206_g();
/* 245 */       f9 = this.field_70550_a.func_94210_h();
/*     */     }
/*     */     
/* 248 */     Tessellator.func_178181_a().func_78381_a();
/* 249 */     GL11.glPushMatrix();
/*     */     
/* 251 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * pticks - field_70556_an);
/* 252 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * pticks - field_70554_ao);
/* 253 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * pticks - field_70555_ap);
/* 254 */     GL11.glTranslated(f11, f12, f13);
/*     */     
/* 256 */     GL11.glPushMatrix();
/*     */     
/* 258 */     float fs = MathHelper.func_76131_a((this.field_70546_d + pticks) / this.field_70547_e, 0.0F, 1.0F);
/* 259 */     float pr = this.field_70552_h + (this.dr - this.field_70552_h) * fs;
/* 260 */     float pg = this.field_70553_i + (this.dg - this.field_70553_i) * fs;
/* 261 */     float pb = this.field_70551_j + (this.db - this.field_70551_j) * fs;
/*     */     
/* 263 */     if (this.angled) {
/* 264 */       GL11.glRotatef(-this.angleYaw + 90.0F, 0.0F, 1.0F, 0.0F);
/* 265 */       GL11.glRotatef(this.anglePitch + 90.0F, 1.0F, 0.0F, 0.0F);
/*     */     } else {
/* 267 */       UtilsFX.rotateToPlayer();
/*     */     }
/*     */     
/* 270 */     GL11.glRotatef(this.rotation + pticks * this.rotationSpeed, 0.0F, 0.0F, 1.0F);
/* 271 */     int i = func_70070_b(pticks);
/* 272 */     int j = i >> 16 & 0xFFFF;
/* 273 */     int k = i & 0xFFFF;
/* 274 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 275 */     wr.func_181662_b(-f10, -f10, 0.0D).func_181673_a(f7, f9).func_181666_a(pr, pg, pb, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/* 276 */     wr.func_181662_b(-f10, f10, 0.0D).func_181673_a(f7, f8).func_181666_a(pr, pg, pb, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/* 277 */     wr.func_181662_b(f10, f10, 0.0D).func_181673_a(f6, f8).func_181666_a(pr, pg, pb, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/* 278 */     wr.func_181662_b(f10, -f10, 0.0D).func_181673_a(f6, f9).func_181666_a(pr, pg, pb, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/* 279 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 281 */     GL11.glPopMatrix();
/*     */     
/* 283 */     GL11.glPopMatrix();
/* 284 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setWind(double d)
/*     */   {
/* 290 */     int m = this.field_70170_p.func_72853_d();
/* 291 */     TCVec3 vsource = TCVec3.createVectorHelper(0.0D, 0.0D, 0.0D);
/* 292 */     TCVec3 vtar = TCVec3.createVectorHelper(0.1D, 0.0D, 0.0D);
/* 293 */     vtar.rotateAroundY(m * (40 + this.field_70170_p.field_73012_v.nextInt(10)) / 180.0F * 3.1415927F);
/* 294 */     TCVec3 vres = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
/* 295 */     this.windX = (vres.xCoord * d);
/* 296 */     this.windZ = (vres.zCoord * d);
/*     */   }
/*     */   
/* 299 */   boolean depthIgnore = false;
/*     */   
/*     */   public void setDepthIgnore(boolean b) {
/* 302 */     this.depthIgnore = b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXGeneric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */