/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.codechicken.lib.vec.Quat;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.devices.BlockEssentiaTransport;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class FXEssentiaTrail2
/*     */   extends EntityFX
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   private double startX;
/*     */   private double startY;
/*     */   private double startZ;
/*  39 */   private int count = 0;
/*  40 */   public int length = 20;
/*  41 */   private String key = "";
/*     */   
/*  43 */   private BlockPos startPos = null;
/*  44 */   private BlockPos endPos = null;
/*     */   
/*  46 */   static HashMap<String, FXEssentiaTrail2> pt = new HashMap();
/*     */   
/*     */ 
/*     */   public FXEssentiaTrail2(World w, double par2, double par4, double par6, double tx, double ty, double tz, int count, int color, float scale, int extend)
/*     */   {
/*  51 */     super(w, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*     */     
/*  53 */     this.field_70544_f = ((float)(scale * (1.0D + this.field_70146_Z.nextGaussian() * 0.15000000596046448D)));
/*  54 */     this.length = Math.max(20, extend);
/*  55 */     this.count = count;
/*  56 */     this.targetX = tx;
/*  57 */     this.targetY = ty;
/*  58 */     this.targetZ = tz;
/*  59 */     BlockPos bp1 = new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  60 */     BlockPos bp2 = new BlockPos(this.targetX, this.targetY, this.targetZ);
/*     */     
/*  62 */     IBlockState bs = w.func_180495_p(bp1);
/*     */     
/*  64 */     if ((bs.func_177230_c() instanceof BlockEssentiaTransport)) {
/*  65 */       EnumFacing f = BlockStateUtils.getFacing(bs);
/*  66 */       this.field_70165_t += f.func_82601_c() * 0.05F;
/*  67 */       this.field_70163_u += f.func_96559_d() * 0.05F;
/*  68 */       this.field_70161_v += f.func_82599_e() * 0.05F;
/*     */     }
/*     */     
/*  71 */     double dx = tx - this.field_70165_t;
/*  72 */     double dy = ty - this.field_70163_u;
/*  73 */     double dz = tz - this.field_70161_v;
/*  74 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 21.0F);
/*  75 */     if (base < 1)
/*  76 */       base = 1;
/*  77 */     this.field_70547_e = base;
/*     */     
/*  79 */     String k = bp1.func_177986_g() + "" + bp2.func_177986_g() + "" + color;
/*  80 */     if (pt.containsKey(k)) {
/*  81 */       FXEssentiaTrail2 trail2 = (FXEssentiaTrail2)pt.get(k);
/*  82 */       if ((!trail2.field_70128_L) && (trail2.vecs.size() < trail2.length)) {
/*  83 */         trail2.length += Math.max(extend, 5);
/*  84 */         trail2.field_70547_e += Math.max(extend, 5);
/*  85 */         this.field_70547_e = 0;
/*     */       }
/*     */     }
/*  88 */     if (this.field_70547_e > 0) {
/*  89 */       pt.put(k, this);
/*  90 */       this.key = k;
/*     */     }
/*     */     
/*  93 */     this.field_70159_w = (MathHelper.func_76126_a(count / 4.0F) * 0.015F);
/*  94 */     this.field_70181_x = (MathHelper.func_76126_a(count / 3.0F) * 0.015F);
/*  95 */     this.field_70179_y = (MathHelper.func_76126_a(count / 2.0F) * 0.015F);
/*     */     
/*  97 */     Color c = new Color(color);
/*  98 */     this.field_70552_h = (c.getRed() / 255.0F);
/*  99 */     this.field_70553_i = (c.getGreen() / 255.0F);
/* 100 */     this.field_70551_j = (c.getBlue() / 255.0F);
/*     */     
/* 102 */     this.field_70545_g = 0.2F;
/* 103 */     this.field_70145_X = false;
/*     */     
/* 105 */     this.vecs.add(new Quat(0.0D, 0.0D, 0.0D, 0.001D));
/* 106 */     this.vecs.add(new Quat(0.0D, 0.0D, 0.0D, 0.001D));
/*     */     
/* 108 */     this.startX = this.field_70165_t;
/* 109 */     this.startY = this.field_70163_u;
/* 110 */     this.startZ = this.field_70161_v;
/*     */     
/* 112 */     this.startPos = new BlockPos(this.startX, this.startY, this.startZ);
/* 113 */     this.endPos = bp2;
/*     */   }
/*     */   
/*     */ 
/* 117 */   CoreGLE gle = new CoreGLE();
/*     */   
/* 119 */   private static final ResourceLocation TEX0 = new ResourceLocation("thaumcraft", "textures/misc/essentia.png");
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 125 */     Tessellator.func_178181_a().func_78381_a();
/* 126 */     GL11.glPushMatrix();
/* 127 */     double ePX = this.startX - field_70556_an;
/* 128 */     double ePY = this.startY - field_70554_ao;
/* 129 */     double ePZ = this.startZ - field_70555_ap;
/* 130 */     GL11.glTranslated(ePX, ePY, ePZ);
/*     */     
/* 132 */     if ((this.points != null) && (this.points.length > 2)) {
/* 133 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(TEX0);
/*     */       
/* 135 */       this.gle.set_POLYCYL_TESS(8);
/* 136 */       this.gle.set__ROUND_TESS_PIECES(1);
/* 137 */       this.gle.gleSetJoinStyle(1042);
/* 138 */       this.gle.glePolyCone(this.points.length, this.points, this.colours, this.radii, 0.075F, this.growing < 0 ? 0.0F : 0.075F * (this.field_70546_d - this.growing + f));
/*     */     }
/*     */     
/*     */ 
/* 142 */     GL11.glPopMatrix();
/* 143 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/* 144 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/* 147 */   int layer = 1;
/*     */   double[][] points;
/*     */   
/* 150 */   public void setFXLayer(int l) { this.layer = l; }
/*     */   
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 155 */     return this.layer;
/*     */   }
/*     */   
/*     */ 
/*     */   float[][] colours;
/*     */   double[] radii;
/* 161 */   int growing = -1;
/* 162 */   ArrayList<Quat> vecs = new ArrayList();
/*     */   
/*     */ 
/*     */   public BlockPos func_180425_c()
/*     */   {
/* 167 */     return new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 171 */     this.field_70169_q = this.field_70165_t;
/* 172 */     this.field_70167_r = this.field_70163_u;
/* 173 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 175 */     if ((this.field_70546_d++ >= this.field_70547_e) || (this.length < 1)) {
/* 176 */       func_70106_y();
/* 177 */       if ((pt.containsKey(this.key)) && (((FXEssentiaTrail2)pt.get(this.key)).field_70128_L)) {
/* 178 */         pt.remove(this.key);
/*     */       }
/* 180 */       return;
/*     */     }
/*     */     
/* 183 */     this.field_70181_x += 0.01D * this.field_70545_g;
/*     */     
/* 185 */     double ds = func_174831_c(this.startPos);
/* 186 */     double de = func_174831_c(this.endPos);
/*     */     
/* 188 */     this.field_70145_X = ((ds <= 1.5D) || (de <= 1.5D));
/*     */     
/* 190 */     if (!this.field_70145_X) {
/* 191 */       func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */     }
/*     */     
/* 194 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 196 */     this.field_70159_w *= 0.985D;
/* 197 */     this.field_70181_x *= 0.985D;
/* 198 */     this.field_70179_y *= 0.985D;
/*     */     
/* 200 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.05F, 0.05F);
/* 201 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.05F, 0.05F);
/* 202 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.05F, 0.05F);
/*     */     
/* 204 */     double dx = this.targetX - this.field_70165_t;
/* 205 */     double dy = this.targetY - this.field_70163_u;
/* 206 */     double dz = this.targetZ - this.field_70161_v;
/* 207 */     double d13 = 0.01D;
/* 208 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 210 */     dx /= d11;
/* 211 */     dy /= d11;
/* 212 */     dz /= d11;
/*     */     
/* 214 */     this.field_70159_w += dx * (d13 / Math.min(1.0D, d11));
/* 215 */     this.field_70181_x += dy * (d13 / Math.min(1.0D, d11));
/* 216 */     this.field_70179_y += dz * (d13 / Math.min(1.0D, d11));
/*     */     
/* 218 */     float scale = this.field_70544_f * (0.75F + MathHelper.func_76126_a((this.count + this.field_70546_d) / 2.0F) * 0.25F);
/*     */     
/* 220 */     if (d11 < 1.0D) {
/* 221 */       float f = MathHelper.func_76126_a((float)(d11 * 1.5707963267948966D));
/* 222 */       scale *= f;
/* 223 */       this.field_70544_f *= f;
/*     */     }
/*     */     
/* 226 */     if (this.field_70544_f > 0.001D) {
/* 227 */       this.vecs.add(new Quat(scale, this.field_70165_t - this.startX, this.field_70163_u - this.startY, this.field_70161_v - this.startZ));
/*     */     } else {
/* 229 */       if (this.growing < 0) this.growing = this.field_70546_d;
/* 230 */       this.length -= 1;
/* 231 */       Thaumcraft.proxy.getFX().essentiaDropFx(this.targetX + this.field_70146_Z.nextGaussian() * 0.07500000298023224D, this.targetY + this.field_70146_Z.nextGaussian() * 0.07500000298023224D, this.targetZ + this.field_70146_Z.nextGaussian() * 0.07500000298023224D, this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 238 */     if (this.vecs.size() > this.length) {
/* 239 */       this.vecs.remove(0);
/*     */     }
/*     */     
/* 242 */     this.points = new double[this.vecs.size()][3];
/* 243 */     this.colours = new float[this.vecs.size()][3];
/* 244 */     this.radii = new double[this.vecs.size()];
/* 245 */     int c = this.vecs.size();
/* 246 */     for (Quat v : this.vecs) {
/* 247 */       c--;
/* 248 */       float variance = 1.0F + MathHelper.func_76126_a((c + this.field_70546_d) / 3.0F) * 0.2F;
/*     */       
/* 250 */       float xx = MathHelper.func_76126_a((c + this.field_70546_d) / 6.0F) * 0.03F;
/* 251 */       float yy = MathHelper.func_76126_a((c + this.field_70546_d) / 7.0F) * 0.03F;
/* 252 */       float zz = MathHelper.func_76126_a((c + this.field_70546_d) / 8.0F) * 0.03F;
/*     */       
/* 254 */       this.points[c][0] = (v.x + xx);
/* 255 */       this.points[c][1] = (v.y + yy);
/* 256 */       this.points[c][2] = (v.z + zz);
/*     */       
/* 258 */       this.radii[c] = (v.s * variance);
/*     */       
/* 260 */       if (c > this.vecs.size() - 10) {
/* 261 */         this.radii[c] *= MathHelper.func_76134_b((float)((c - (this.vecs.size() - 12)) / 10.0F * 1.5707963267948966D));
/*     */       }
/*     */       
/* 264 */       if (c == 0) this.radii[c] = 0.0D; else if (c == 1) { this.radii[c] = 0.0D;
/* 265 */       } else if (c == 2) { this.radii[c] = ((this.field_70544_f * 0.5D + this.radii[c]) / 2.0D);
/* 266 */       } else if (c == 3) { this.radii[c] = ((this.field_70544_f + this.radii[c]) / 2.0D);
/* 267 */       } else if (c == 4) { this.radii[c] = ((this.field_70544_f + this.radii[c] * 2.0D) / 3.0D);
/*     */       }
/* 269 */       float v2 = 1.0F - MathHelper.func_76126_a((c + this.field_70546_d) / 2.0F) * 0.1F;
/*     */       
/* 271 */       this.colours[c][0] = (this.field_70552_h * v2);
/* 272 */       this.colours[c][1] = (this.field_70553_i * v2);
/* 273 */       this.colours[c][2] = (this.field_70551_j * v2);
/*     */     }
/*     */     
/*     */ 
/* 277 */     if ((this.vecs.size() > 2) && (this.field_70146_Z.nextBoolean())) {
/* 278 */       int q = this.field_70146_Z.nextInt(3);
/* 279 */       if (this.field_70146_Z.nextBoolean()) {
/* 280 */         q = this.vecs.size() - 2;
/*     */       }
/* 282 */       Thaumcraft.proxy.getFX().essentiaDropFx(((Quat)this.vecs.get(q)).x + this.startX, ((Quat)this.vecs.get(q)).y + this.startY, ((Quat)this.vecs.get(q)).z + this.startZ, this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setGravity(float value)
/*     */   {
/* 290 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXEssentiaTrail2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */