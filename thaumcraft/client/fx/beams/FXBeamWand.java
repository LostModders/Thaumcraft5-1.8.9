/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBeamWand extends EntityFX
/*     */ {
/*  23 */   public int particle = 16;
/*  24 */   EntityLivingBase sourceEntity = null;
/*  25 */   private double offset = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXBeamWand(World par1World, EntityLivingBase p, double tx, double ty, double tz, float red, float green, float blue, int age)
/*     */   {
/*  33 */     super(par1World, p.field_70165_t, p.field_70163_u, p.field_70161_v, 0.0D, 0.0D, 0.0D);
/*     */     
/*     */ 
/*  36 */     this.offset = (p.field_70131_O / 2.0F + 0.25D);
/*     */     
/*  38 */     this.field_70552_h = red;
/*  39 */     this.field_70553_i = green;
/*  40 */     this.field_70551_j = blue;
/*  41 */     this.sourceEntity = p;
/*  42 */     func_70105_a(0.02F, 0.02F);
/*  43 */     this.field_70145_X = true;
/*  44 */     this.field_70159_w = 0.0D;
/*  45 */     this.field_70181_x = 0.0D;
/*  46 */     this.field_70179_y = 0.0D;
/*  47 */     this.tX = tx;
/*  48 */     this.tY = ty;
/*  49 */     this.tZ = tz;
/*  50 */     float xd = (float)(p.field_70165_t - this.tX);
/*  51 */     float yd = (float)(p.field_70163_u + this.offset - this.tY);
/*  52 */     float zd = (float)(p.field_70161_v - this.tZ);
/*  53 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*  54 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*  55 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*  56 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*  57 */     this.prevYaw = this.rotYaw;
/*  58 */     this.prevPitch = this.rotPitch;
/*     */     
/*  60 */     this.field_70547_e = age;
/*     */     
/*  62 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/*  63 */     int visibleDistance = 50;
/*  64 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  65 */     if (renderentity.func_70011_f(p.field_70165_t, p.field_70163_u, p.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
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
/*     */   public void updateBeam(double x, double y, double z)
/*     */   {
/* 109 */     this.tX = x;
/* 110 */     this.tY = y;
/* 111 */     this.tZ = z;
/* 112 */     while (this.field_70547_e - this.field_70546_d < 4) { this.field_70547_e += 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 122 */     this.field_70169_q = this.sourceEntity.field_70165_t;
/* 123 */     this.field_70167_r = (this.sourceEntity.field_70163_u + this.offset);
/* 124 */     this.field_70166_s = this.sourceEntity.field_70161_v;
/* 125 */     this.ptX = this.tX;
/* 126 */     this.ptY = this.tY;
/* 127 */     this.ptZ = this.tZ;
/*     */     
/* 129 */     this.prevYaw = this.rotYaw;
/* 130 */     this.prevPitch = this.rotPitch;
/*     */     
/* 132 */     float xd = (float)(this.sourceEntity.field_70165_t - this.tX);
/* 133 */     float yd = (float)(this.sourceEntity.field_70163_u + this.offset - this.tY);
/* 134 */     float zd = (float)(this.sourceEntity.field_70161_v - this.tZ);
/*     */     
/* 136 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*     */     
/* 138 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*     */     
/* 140 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*     */     
/*     */ 
/*     */ 
/* 144 */     for (this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D)); this.rotPitch - this.prevPitch < -180.0F; this.prevPitch -= 360.0F) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 149 */     while (this.rotPitch - this.prevPitch >= 180.0F)
/*     */     {
/* 151 */       this.prevPitch += 360.0F;
/*     */     }
/*     */     
/* 154 */     while (this.rotYaw - this.prevYaw < -180.0F)
/*     */     {
/* 156 */       this.prevYaw -= 360.0F;
/*     */     }
/*     */     
/* 159 */     while (this.rotYaw - this.prevYaw >= 180.0F)
/*     */     {
/* 161 */       this.prevYaw += 360.0F;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 166 */     if (this.impact > 0) { this.impact -= 1;
/*     */     }
/* 168 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 170 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/* 175 */     this.field_70552_h = r;
/* 176 */     this.field_70553_i = g;
/* 177 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 180 */   private float length = 0.0F;
/* 181 */   private float rotYaw = 0.0F;
/* 182 */   private float rotPitch = 0.0F;
/* 183 */   private float prevYaw = 0.0F;
/* 184 */   private float prevPitch = 0.0F;
/* 185 */   private Entity targetEntity = null;
/* 186 */   private double tX = 0.0D;
/* 187 */   private double tY = 0.0D;
/* 188 */   private double tZ = 0.0D;
/* 189 */   private double ptX = 0.0D;
/* 190 */   private double ptY = 0.0D;
/* 191 */   private double ptZ = 0.0D;
/*     */   
/* 193 */   private int type = 0;
/*     */   
/* 195 */   public void setType(int type) { this.type = type; }
/*     */   
/*     */ 
/* 198 */   private float endMod = 1.0F;
/*     */   
/* 200 */   public void setEndMod(float endMod) { this.endMod = endMod; }
/*     */   
/*     */ 
/* 203 */   private boolean reverse = false;
/*     */   
/* 205 */   public void setReverse(boolean reverse) { this.reverse = reverse; }
/*     */   
/*     */ 
/* 208 */   private boolean pulse = true;
/*     */   
/* 210 */   public void setPulse(boolean pulse) { this.pulse = pulse; }
/*     */   
/*     */ 
/* 213 */   private int rotationspeed = 5;
/*     */   
/* 215 */   public void setRotationspeed(int rotationspeed) { this.rotationspeed = rotationspeed; }
/*     */   
/*     */ 
/* 218 */   private float prevSize = 0.0F;
/*     */   
/*     */   public int impact;
/* 221 */   ResourceLocation beam = new ResourceLocation("thaumcraft", "textures/misc/beam.png");
/* 222 */   ResourceLocation beam1 = new ResourceLocation("thaumcraft", "textures/misc/beam1.png");
/* 223 */   ResourceLocation beam2 = new ResourceLocation("thaumcraft", "textures/misc/beam2.png");
/* 224 */   ResourceLocation beam3 = new ResourceLocation("thaumcraft", "textures/misc/beam3.png");
/*     */   
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 229 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 231 */     GL11.glPushMatrix();
/* 232 */     float var9 = 1.0F;
/* 233 */     float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
/* 234 */     float rot = (float)(this.field_70170_p.field_73011_w.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
/*     */     
/* 236 */     float size = 1.0F;
/* 237 */     if (this.pulse) {
/* 238 */       size = Math.min(this.field_70546_d / 4.0F, 1.0F);
/* 239 */       size = (float)(this.prevSize + (size - this.prevSize) * f);
/*     */     }
/*     */     
/* 242 */     float op = 0.4F;
/* 243 */     if ((this.pulse) && (this.field_70547_e - this.field_70546_d <= 4)) {
/* 244 */       op = 0.4F - (4 - (this.field_70547_e - this.field_70546_d)) * 0.1F;
/*     */     }
/* 246 */     switch (this.type) {
/* 247 */     default:  Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam); break;
/* 248 */     case 1:  Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam1); break;
/* 249 */     case 2:  Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam2); break;
/* 250 */     case 3:  Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam3);
/*     */     }
/*     */     
/* 253 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 254 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/*     */     
/* 256 */     GL11.glDisable(2884);
/*     */     
/* 258 */     float var11 = slide + f;
/* 259 */     if (this.reverse) var11 *= -1.0F;
/* 260 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/* 262 */     GL11.glEnable(3042);
/* 263 */     GL11.glBlendFunc(770, 1);
/* 264 */     GL11.glDepthMask(false);
/*     */     
/* 266 */     double prex = this.sourceEntity.field_70169_q;
/* 267 */     double prey = this.sourceEntity.field_70167_r + this.offset;
/* 268 */     double prez = this.sourceEntity.field_70166_s;
/* 269 */     double px = this.sourceEntity.field_70165_t;
/* 270 */     double py = this.sourceEntity.field_70163_u + this.offset;
/* 271 */     double pz = this.sourceEntity.field_70161_v;
/*     */     
/* 273 */     prex -= MathHelper.func_76134_b(this.sourceEntity.field_70126_B / 180.0F * 3.141593F) * 0.066F;
/* 274 */     prey -= 0.06D;
/* 275 */     prez -= MathHelper.func_76126_a(this.sourceEntity.field_70126_B / 180.0F * 3.141593F) * 0.04F;
/* 276 */     Vec3 vec3d = this.sourceEntity.func_70676_i(1.0F);
/* 277 */     prex += vec3d.field_72450_a * 0.3D;
/* 278 */     prey += vec3d.field_72448_b * 0.3D;
/* 279 */     prez += vec3d.field_72449_c * 0.3D;
/*     */     
/* 281 */     px -= MathHelper.func_76134_b(this.sourceEntity.field_70177_z / 180.0F * 3.141593F) * 0.066F;
/* 282 */     py -= 0.06D;
/* 283 */     pz -= MathHelper.func_76126_a(this.sourceEntity.field_70177_z / 180.0F * 3.141593F) * 0.04F;
/* 284 */     vec3d = this.sourceEntity.func_70676_i(1.0F);
/* 285 */     px += vec3d.field_72450_a * 0.3D;
/* 286 */     py += vec3d.field_72448_b * 0.3D;
/* 287 */     pz += vec3d.field_72449_c * 0.3D;
/*     */     
/* 289 */     float xx = (float)(prex + (px - prex) * f - field_70556_an);
/* 290 */     float yy = (float)(prey + (py - prey) * f - field_70554_ao);
/* 291 */     float zz = (float)(prez + (pz - prez) * f - field_70555_ap);
/* 292 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 294 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 295 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 296 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 297 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 298 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 300 */     double var44 = -0.15D * size;
/* 301 */     double var17 = 0.15D * size;
/* 302 */     double var44b = -0.15D * size * this.endMod;
/* 303 */     double var17b = 0.15D * size * this.endMod;
/* 304 */     int i = 200;
/* 305 */     int j = i >> 16 & 0xFFFF;
/* 306 */     int k = i & 0xFFFF;
/*     */     
/* 308 */     GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 309 */     for (int t = 0; t < 3; t++)
/*     */     {
/* 311 */       double var29 = this.length * size * var9;
/* 312 */       double var31 = 0.0D;
/* 313 */       double var33 = 1.0D;
/* 314 */       double var35 = -1.0F + var12 + t / 3.0F;
/* 315 */       double var37 = this.length * size * var9 + var35;
/*     */       
/* 317 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 318 */       wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 319 */       wr.func_181662_b(var44b, var29, 0.0D).func_181673_a(var33, var37).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_181671_a(j, k).func_181675_d();
/* 320 */       wr.func_181662_b(var44, 0.0D, 0.0D).func_181673_a(var33, var35).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_181671_a(j, k).func_181675_d();
/* 321 */       wr.func_181662_b(var17, 0.0D, 0.0D).func_181673_a(var31, var35).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_181671_a(j, k).func_181675_d();
/* 322 */       wr.func_181662_b(var17b, var29, 0.0D).func_181673_a(var31, var37).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_181671_a(j, k).func_181675_d();
/* 323 */       Tessellator.func_178181_a().func_78381_a();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 328 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 329 */     GL11.glDepthMask(true);
/* 330 */     GL11.glBlendFunc(770, 771);
/* 331 */     GL11.glDisable(3042);
/* 332 */     GL11.glEnable(2884);
/*     */     
/* 334 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/* 337 */     if (this.impact > 0) { renderImpact(Tessellator.func_178181_a(), f, f1, f2, f3, f4, f5);
/*     */     }
/* 339 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getMCParticleTexture());
/* 340 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 341 */     this.prevSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderImpact(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 348 */     GL11.glPushMatrix();
/* 349 */     GL11.glDepthMask(false);
/* 350 */     GL11.glEnable(3042);
/* 351 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 353 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(thaumcraft.client.fx.ParticleEngine.particleTexture);
/*     */     
/* 355 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 356 */     int part = this.field_70546_d % 16;
/*     */     
/* 358 */     float var8 = part / 16.0F;
/* 359 */     float var9 = var8 + 0.0624375F;
/* 360 */     float var10 = 0.3125F;
/* 361 */     float var11 = var10 + 0.0624375F;
/* 362 */     float var12 = this.endMod / 2.0F / (6 - this.impact);
/*     */     
/* 364 */     float var13 = (float)(this.ptX + (this.tX - this.ptX) * f - field_70556_an);
/* 365 */     float var14 = (float)(this.ptY + (this.tY - this.ptY) * f - field_70554_ao);
/* 366 */     float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * f - field_70555_ap);
/* 367 */     float var16 = 1.0F;
/*     */     
/* 369 */     int i = 200;
/* 370 */     int j = i >> 16 & 0xFFFF;
/* 371 */     int k = i & 0xFFFF;
/*     */     
/* 373 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 374 */     tessellator.func_178180_c().func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_181671_a(j, k).func_181675_d();
/* 375 */     tessellator.func_178180_c().func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_181671_a(j, k).func_181675_d();
/* 376 */     tessellator.func_178180_c().func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_181671_a(j, k).func_181675_d();
/* 377 */     tessellator.func_178180_c().func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 379 */     tessellator.func_78381_a();
/* 380 */     GL11.glBlendFunc(770, 771);
/* 381 */     GL11.glDisable(3042);
/* 382 */     GL11.glDepthMask(true);
/* 383 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/beams/FXBeamWand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */