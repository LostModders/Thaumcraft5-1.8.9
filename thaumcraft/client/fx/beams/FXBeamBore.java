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
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBeamBore extends EntityFX
/*     */ {
/*  21 */   public int particle = 16;
/*     */   
/*  23 */   private double offset = 0.0D;
/*     */   
/*  25 */   private double tX = 0.0D;
/*  26 */   private double tY = 0.0D;
/*  27 */   private double tZ = 0.0D;
/*  28 */   private double ptX = 0.0D;
/*  29 */   private double ptY = 0.0D;
/*  30 */   private double ptZ = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXBeamBore(World par1World, double px, double py, double pz, double tx, double ty, double tz, float red, float green, float blue, int age)
/*     */   {
/*  38 */     super(par1World, px, py, pz, 0.0D, 0.0D, 0.0D);
/*     */     
/*     */ 
/*  41 */     this.field_70552_h = red;
/*  42 */     this.field_70553_i = green;
/*  43 */     this.field_70551_j = blue;
/*  44 */     func_70105_a(0.02F, 0.02F);
/*  45 */     this.field_70145_X = true;
/*  46 */     this.field_70159_w = 0.0D;
/*  47 */     this.field_70181_x = 0.0D;
/*  48 */     this.field_70179_y = 0.0D;
/*  49 */     this.tX = tx;
/*  50 */     this.tY = ty;
/*  51 */     this.tZ = tz;
/*  52 */     this.prevYaw = this.rotYaw;
/*  53 */     this.prevPitch = this.rotPitch;
/*  54 */     this.field_70547_e = age;
/*     */     
/*  56 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/*  57 */     int visibleDistance = 64;
/*  58 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 32;
/*  59 */     if ((renderentity != null) && (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance)) this.field_70547_e = 0;
/*     */   }
/*     */   
/*     */   public void updateBeam(double x, double y, double z)
/*     */   {
/*  64 */     this.tX = x;
/*  65 */     this.tY = y;
/*  66 */     this.tZ = z;
/*  67 */     while (this.field_70547_e - this.field_70546_d < 4) { this.field_70547_e += 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  77 */     this.field_70169_q = this.field_70165_t;
/*  78 */     this.field_70167_r = (this.field_70163_u + this.offset);
/*  79 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  81 */     this.ptX = this.tX;
/*  82 */     this.ptY = this.tY;
/*  83 */     this.ptZ = this.tZ;
/*     */     
/*  85 */     this.prevYaw = this.rotYaw;
/*  86 */     this.prevPitch = this.rotPitch;
/*     */     
/*  88 */     float xd = (float)(this.field_70165_t - this.tX);
/*  89 */     float yd = (float)(this.field_70163_u - this.tY);
/*  90 */     float zd = (float)(this.field_70161_v - this.tZ);
/*  91 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*  92 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*  93 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*  94 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*  95 */     this.prevYaw = this.rotYaw;
/*  96 */     this.prevPitch = this.rotPitch;
/*     */     
/*  98 */     if (this.impact > 0) { this.impact -= 1;
/*     */     }
/* 100 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 102 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/* 107 */     this.field_70552_h = r;
/* 108 */     this.field_70553_i = g;
/* 109 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 112 */   private float length = 0.0F;
/* 113 */   private float rotYaw = 0.0F;
/* 114 */   private float rotPitch = 0.0F;
/* 115 */   private float prevYaw = 0.0F;
/* 116 */   private float prevPitch = 0.0F;
/* 117 */   private Entity targetEntity = null;
/*     */   
/* 119 */   private int type = 0;
/*     */   
/* 121 */   public void setType(int type) { this.type = type; }
/*     */   
/*     */ 
/* 124 */   private float endMod = 1.0F;
/*     */   
/* 126 */   public void setEndMod(float endMod) { this.endMod = endMod; }
/*     */   
/*     */ 
/* 129 */   private boolean reverse = false;
/*     */   
/* 131 */   public void setReverse(boolean reverse) { this.reverse = reverse; }
/*     */   
/*     */ 
/* 134 */   private boolean pulse = true;
/*     */   
/* 136 */   public void setPulse(boolean pulse) { this.pulse = pulse; }
/*     */   
/*     */ 
/* 139 */   private int rotationspeed = 5;
/*     */   
/* 141 */   public void setRotationspeed(int rotationspeed) { this.rotationspeed = rotationspeed; }
/*     */   
/*     */ 
/* 144 */   private float prevSize = 0.0F;
/*     */   
/*     */   public int impact;
/* 147 */   ResourceLocation beam = new ResourceLocation("thaumcraft", "textures/misc/beam.png");
/* 148 */   ResourceLocation beam1 = new ResourceLocation("thaumcraft", "textures/misc/beam1.png");
/* 149 */   ResourceLocation beam2 = new ResourceLocation("thaumcraft", "textures/misc/beam2.png");
/* 150 */   ResourceLocation beam3 = new ResourceLocation("thaumcraft", "textures/misc/beam3.png");
/*     */   
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 155 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 157 */     GL11.glPushMatrix();
/* 158 */     float var9 = 1.0F;
/* 159 */     float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
/* 160 */     float rot = (float)(this.field_70170_p.field_73011_w.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
/*     */     
/* 162 */     float size = 1.0F;
/* 163 */     if (this.pulse) {
/* 164 */       size = Math.min(this.field_70546_d / 4.0F, 1.0F);
/* 165 */       size = (float)(this.prevSize + (size - this.prevSize) * f);
/*     */     }
/*     */     
/* 168 */     float op = 0.4F;
/* 169 */     if ((this.pulse) && (this.field_70547_e - this.field_70546_d <= 4)) {
/* 170 */       op = 0.4F - (4 - (this.field_70547_e - this.field_70546_d)) * 0.1F;
/*     */     }
/*     */     
/* 173 */     switch (this.type) {
/* 174 */     default:  Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam); break;
/* 175 */     case 1:  Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam1); break;
/* 176 */     case 2:  Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam2); break;
/* 177 */     case 3:  Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam3);
/*     */     }
/*     */     
/* 180 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 181 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/*     */     
/* 183 */     GL11.glDisable(2884);
/*     */     
/* 185 */     float var11 = slide + f;
/* 186 */     if (this.reverse) var11 *= -1.0F;
/* 187 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/* 189 */     GL11.glEnable(3042);
/* 190 */     GL11.glBlendFunc(770, 1);
/* 191 */     GL11.glDepthMask(false);
/*     */     
/* 193 */     float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 194 */     float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 195 */     float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 196 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 198 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 199 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 200 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 201 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 202 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 204 */     double var44 = -0.15D * size;
/* 205 */     double var17 = 0.15D * size;
/* 206 */     double var44b = -0.15D * size * this.endMod;
/* 207 */     double var17b = 0.15D * size * this.endMod;
/* 208 */     int i = 200;
/* 209 */     int j = i >> 16 & 0xFFFF;
/* 210 */     int k = i & 0xFFFF;
/* 211 */     GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 212 */     for (int t = 0; t < 3; t++)
/*     */     {
/* 214 */       double var29 = this.length * size * var9;
/* 215 */       double var31 = 0.0D;
/* 216 */       double var33 = 1.0D;
/* 217 */       double var35 = -1.0F + var12 + t / 3.0F;
/* 218 */       double var37 = this.length * size * var9 + var35;
/*     */       
/* 220 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 221 */       wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 222 */       wr.func_181662_b(var44b, var29, 0.0D).func_181673_a(var33, var37).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_181671_a(j, k).func_181675_d();
/* 223 */       wr.func_181662_b(var44, 0.0D, 0.0D).func_181673_a(var33, var35).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_181671_a(j, k).func_181675_d();
/* 224 */       wr.func_181662_b(var17, 0.0D, 0.0D).func_181673_a(var31, var35).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_181671_a(j, k).func_181675_d();
/* 225 */       wr.func_181662_b(var17b, var29, 0.0D).func_181673_a(var31, var37).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_181671_a(j, k).func_181675_d();
/* 226 */       Tessellator.func_178181_a().func_78381_a();
/*     */     }
/*     */     
/*     */ 
/* 230 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 231 */     GL11.glDepthMask(true);
/* 232 */     GL11.glBlendFunc(770, 771);
/* 233 */     GL11.glDisable(3042);
/* 234 */     GL11.glEnable(2884);
/* 235 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/* 238 */     if (this.impact > 0) { renderImpact(Tessellator.func_178181_a(), f, f1, f2, f3, f4, f5);
/*     */     }
/* 240 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getMCParticleTexture());
/* 241 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 242 */     this.prevSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderImpact(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 249 */     GL11.glPushMatrix();
/* 250 */     GL11.glDepthMask(false);
/* 251 */     GL11.glEnable(3042);
/* 252 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 254 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(thaumcraft.client.fx.ParticleEngine.particleTexture);
/*     */     
/* 256 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 257 */     int part = this.field_70546_d % 16;
/*     */     
/* 259 */     float var8 = part / 16.0F;
/* 260 */     float var9 = var8 + 0.0624375F;
/* 261 */     float var10 = 0.3125F;
/* 262 */     float var11 = var10 + 0.0624375F;
/* 263 */     float var12 = this.endMod / 2.0F / (6 - this.impact);
/*     */     
/* 265 */     float var13 = (float)(this.ptX + (this.tX - this.ptX) * f - field_70556_an);
/* 266 */     float var14 = (float)(this.ptY + (this.tY - this.ptY) * f - field_70554_ao);
/* 267 */     float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * f - field_70555_ap);
/* 268 */     float var16 = 1.0F;
/* 269 */     int i = 200;
/* 270 */     int j = i >> 16 & 0xFFFF;
/* 271 */     int k = i & 0xFFFF;
/* 272 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 273 */     tessellator.func_178180_c().func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_181671_a(j, k).func_181675_d();
/* 274 */     tessellator.func_178180_c().func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_181671_a(j, k).func_181675_d();
/* 275 */     tessellator.func_178180_c().func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_181671_a(j, k).func_181675_d();
/* 276 */     tessellator.func_178180_c().func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 278 */     tessellator.func_78381_a();
/* 279 */     GL11.glBlendFunc(770, 771);
/* 280 */     GL11.glDisable(3042);
/* 281 */     GL11.glDepthMask(true);
/* 282 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/beams/FXBeamBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */