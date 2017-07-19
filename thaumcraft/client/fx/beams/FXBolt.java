/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FXBolt
/*     */   extends EntityFX
/*     */ {
/*     */   public FXBolt(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue)
/*     */   {
/*  30 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*     */     
/*  32 */     this.field_70552_h = red;
/*  33 */     this.field_70553_i = green;
/*  34 */     this.field_70551_j = blue;
/*     */     
/*  36 */     func_70105_a(0.02F, 0.02F);
/*  37 */     this.field_70145_X = true;
/*  38 */     this.field_70159_w = 0.0D;
/*  39 */     this.field_70181_x = 0.0D;
/*  40 */     this.field_70179_y = 0.0D;
/*  41 */     this.tX = (tx - x);
/*  42 */     this.tY = (ty - y);
/*  43 */     this.tZ = (tz - z);
/*     */     
/*     */ 
/*  46 */     this.field_70547_e = 3;
/*     */     
/*     */ 
/*  49 */     Vec3 vs = new Vec3(0.0D, 0.0D, 0.0D);
/*  50 */     Vec3 ve = new Vec3(this.tX, this.tY, this.tZ);
/*     */     
/*  52 */     this.length = ((float)(ve.func_72433_c() * 3.141592653589793D));
/*     */     
/*  54 */     int steps = (int)this.length;
/*     */     
/*  56 */     this.points.add(vs);
/*  57 */     this.pointsWidth.add(Float.valueOf(1.0F));
/*  58 */     this.dr = ((float)(this.field_70146_Z.nextInt(50) * 3.141592653589793D));
/*     */     
/*  60 */     float ampl = 0.1F;
/*     */     
/*  62 */     for (int a = 1; a < steps - 1; a++) {
/*  63 */       float dist = a * (this.length / steps) + this.dr;
/*  64 */       double dx = this.tX / steps * a + MathHelper.func_76126_a(dist / 4.0F) * ampl;
/*  65 */       double dy = this.tY / steps * a + MathHelper.func_76126_a(dist / 3.0F) * ampl;
/*  66 */       double dz = this.tZ / steps * a + MathHelper.func_76126_a(dist / 2.0F) * ampl;
/*  67 */       dx += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*  68 */       dy += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*  69 */       dz += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/*  71 */       Vec3 vp = new Vec3(dx, dy, dz);
/*  72 */       this.points.add(vp);
/*  73 */       this.pointsWidth.add(Float.valueOf(1.0F));
/*     */     }
/*  75 */     this.pointsWidth.add(Float.valueOf(1.0F));
/*  76 */     this.points.add(ve);
/*     */     
/*  78 */     this.seed = this.field_70146_Z.nextInt(1000);
/*     */   }
/*     */   
/*  81 */   ArrayList<Vec3> points = new ArrayList();
/*  82 */   ArrayList<Float> pointsWidth = new ArrayList();
/*  83 */   float dr = 0.0F;
/*  84 */   long seed = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  92 */     this.field_70169_q = this.field_70165_t;
/*  93 */     this.field_70167_r = this.field_70163_u;
/*  94 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  96 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/*  98 */       func_70106_y();
/*     */     }
/*     */     
/* 101 */     Random rr = new Random(this.seed);
/*     */     
/* 103 */     this.points.clear();
/* 104 */     this.pointsWidth.clear();
/* 105 */     Vec3 vs = new Vec3(0.0D, 0.0D, 0.0D);
/* 106 */     Vec3 ve = new Vec3(this.tX, this.tY, this.tZ);
/* 107 */     int steps = (int)this.length;
/* 108 */     this.points.add(vs);
/* 109 */     this.pointsWidth.add(Float.valueOf(1.0F));
/* 110 */     float ampl = 0.15F * this.field_70546_d;
/* 111 */     for (int a = 1; a < steps - 1; a++) {
/* 112 */       float dist = a * (this.length / steps) + this.dr;
/* 113 */       double dx = this.tX / steps * a + MathHelper.func_76126_a(dist / 4.0F) * ampl;
/* 114 */       double dy = this.tY / steps * a + MathHelper.func_76126_a(dist / 3.0F) * ampl;
/* 115 */       double dz = this.tZ / steps * a + MathHelper.func_76126_a(dist / 2.0F) * ampl;
/* 116 */       dx += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/* 117 */       dy += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/* 118 */       dz += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/* 119 */       Vec3 vp = new Vec3(dx, dy, dz);
/* 120 */       this.points.add(vp);
/* 121 */       this.pointsWidth.add(Float.valueOf(rr.nextInt(4) == 0 ? 1.0F - this.field_70546_d * 0.25F : 1.0F));
/*     */     }
/* 123 */     this.pointsWidth.add(Float.valueOf(1.0F));
/* 124 */     this.points.add(ve);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRGB(float r, float g, float b)
/*     */   {
/* 130 */     this.field_70552_h = r;
/* 131 */     this.field_70553_i = g;
/* 132 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 135 */   private Entity targetEntity = null;
/* 136 */   private double tX = 0.0D;
/* 137 */   private double tY = 0.0D;
/* 138 */   private double tZ = 0.0D;
/*     */   
/* 140 */   ResourceLocation beam = new ResourceLocation("thaumcraft", "textures/misc/beaml.png");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float cosyaw, float cospitch, float sinyaw, float cossinpitch, float f5)
/*     */   {
/* 147 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 149 */     GL11.glPushMatrix();
/* 150 */     double ePX = this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an;
/* 151 */     double ePY = this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao;
/* 152 */     double ePZ = this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap;
/* 153 */     GL11.glTranslated(ePX, ePY, ePZ);
/*     */     
/*     */ 
/* 156 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam);
/*     */     
/* 158 */     GL11.glDepthMask(false);
/* 159 */     GL11.glEnable(3042);
/* 160 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 162 */     GL11.glDisable(2884);
/*     */     
/* 164 */     int i = 220;
/* 165 */     int j = i >> 16 & 0xFFFF;
/* 166 */     int k = i & 0xFFFF;
/*     */     
/* 168 */     wr.func_181668_a(5, DefaultVertexFormats.field_181704_d);
/* 169 */     float f9 = 0.0F;
/* 170 */     float f10 = 1.0F;
/* 171 */     for (int c = 0; c < this.points.size(); c++) {
/* 172 */       float size = 0.15F * ((Float)this.pointsWidth.get(c)).floatValue();
/*     */       
/* 174 */       float f13 = c / this.length;
/* 175 */       Vec3 vc = (Vec3)this.points.get(c);
/* 176 */       Vec3 vp = c == 0 ? (Vec3)this.points.get(c) : (Vec3)this.points.get(c - 1);
/* 177 */       Vec3 vn = c == this.points.size() - 1 ? (Vec3)this.points.get(c) : (Vec3)this.points.get(c + 1);
/* 178 */       Vec3 v1 = vp.func_178788_d(vc);
/* 179 */       Vec3 v2 = vc.func_178788_d(vn);
/* 180 */       Vec3 v = v1.func_178787_e(v2).func_72432_b();
/* 181 */       v = v.func_178789_a(1.5707964F);
/* 182 */       Vector3 vf = new Vector3(v).multiply(size);
/* 183 */       wr.func_181662_b(vc.field_72450_a + vf.x, vc.field_72448_b + vf.y, vc.field_72449_c + vf.z).func_181673_a(f13, f10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F / Math.max(1, this.field_70546_d)).func_181671_a(j, k).func_181675_d();
/* 184 */       wr.func_181662_b(vc.field_72450_a - vf.x, vc.field_72448_b - vf.y, vc.field_72449_c - vf.z).func_181673_a(f13, f9).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F / Math.max(1, this.field_70546_d)).func_181671_a(j, k).func_181675_d();
/*     */     }
/*     */     
/* 187 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 189 */     wr.func_181668_a(5, DefaultVertexFormats.field_181704_d);
/*     */     
/* 191 */     for (int c = 0; c < this.points.size(); c++) {
/* 192 */       float size = 0.15F * ((Float)this.pointsWidth.get(c)).floatValue();
/* 193 */       float f13 = c / this.length;
/* 194 */       Vec3 vc = (Vec3)this.points.get(c);
/* 195 */       Vec3 vp = c == 0 ? (Vec3)this.points.get(c) : (Vec3)this.points.get(c - 1);
/* 196 */       Vec3 vn = c == this.points.size() - 1 ? (Vec3)this.points.get(c) : (Vec3)this.points.get(c + 1);
/* 197 */       Vec3 v1 = vp.func_178788_d(vc);
/* 198 */       Vec3 v2 = vc.func_178788_d(vn);
/* 199 */       Vec3 v = v1.func_178787_e(v2).func_72432_b();
/* 200 */       v = v.func_178785_b(1.5707964F);
/* 201 */       Vector3 vf = new Vector3(v).multiply(size);
/* 202 */       wr.func_181662_b(vc.field_72450_a + vf.x, vc.field_72448_b + vf.y, vc.field_72449_c + vf.z).func_181673_a(f13, f10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F / Math.max(1, this.field_70546_d)).func_181671_a(j, k).func_181675_d();
/* 203 */       wr.func_181662_b(vc.field_72450_a - vf.x, vc.field_72448_b - vf.y, vc.field_72449_c - vf.z).func_181673_a(f13, f9).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F / Math.max(1, this.field_70546_d)).func_181671_a(j, k).func_181675_d();
/*     */     }
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
/* 240 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 242 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 243 */     GL11.glEnable(2884);
/* 244 */     GL11.glBlendFunc(770, 771);
/* 245 */     GL11.glDisable(3042);
/* 246 */     GL11.glDepthMask(true);
/*     */     
/* 248 */     GL11.glPopMatrix();
/*     */     
/* 250 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getMCParticleTexture());
/*     */     
/* 252 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/* 255 */   public float length = 1.0F;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/beams/FXBolt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */