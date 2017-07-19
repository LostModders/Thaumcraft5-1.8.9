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
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class FXArc
/*     */   extends EntityFX
/*     */ {
/*  22 */   public int particle = 16;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXArc(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, double hg)
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
/*     */ 
/*  47 */     this.field_70547_e = 1;
/*     */     
/*  49 */     double xx = 0.0D;
/*  50 */     double yy = 0.0D;
/*  51 */     double zz = 0.0D;
/*     */     
/*  53 */     double gravity = 0.115D;
/*  54 */     double noise = 0.25D;
/*     */     
/*  56 */     Vec3 vs = new Vec3(xx, yy, zz);
/*  57 */     Vec3 ve = new Vec3(this.tX, this.tY, this.tZ);
/*  58 */     Vec3 vc = new Vec3(xx, yy, zz);
/*     */     
/*  60 */     this.length = ((float)ve.func_72433_c());
/*  61 */     Vec3 vv = Utils.calculateVelocity(vs, ve, hg, gravity);
/*  62 */     double l = Utils.distanceSquared3d(new Vec3(0.0D, 0.0D, 0.0D), vv);
/*     */     
/*  64 */     this.points.add(vs);
/*     */     
/*  66 */     int c = 0;
/*  67 */     while ((Utils.distanceSquared3d(ve, vc) > l) && (c < 50))
/*     */     {
/*  69 */       Vec3 vt = vc.func_72441_c(vv.field_72450_a, vv.field_72448_b, vv.field_72449_c);
/*  70 */       vc = new Vec3(vt.field_72450_a, vt.field_72448_b, vt.field_72449_c);
/*  71 */       vt = vt.func_72441_c((this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise, (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise, (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise);
/*     */       
/*     */ 
/*     */ 
/*  75 */       this.points.add(vt);
/*  76 */       vv = vv.func_178786_a(0.0D, gravity / 1.9D, 0.0D);
/*  77 */       c++;
/*     */     }
/*     */     
/*  80 */     this.points.add(ve);
/*     */   }
/*     */   
/*  83 */   ArrayList<Vec3> points = new ArrayList();
/*     */   
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
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b)
/*     */   {
/* 104 */     this.field_70552_h = r;
/* 105 */     this.field_70553_i = g;
/* 106 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 109 */   private Entity targetEntity = null;
/* 110 */   private double tX = 0.0D;
/* 111 */   private double tY = 0.0D;
/* 112 */   private double tZ = 0.0D;
/*     */   
/* 114 */   ResourceLocation beam = new ResourceLocation("thaumcraft", "textures/misc/beamh.png");
/*     */   
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 119 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 121 */     GL11.glPushMatrix();
/* 122 */     double ePX = this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an;
/* 123 */     double ePY = this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao;
/* 124 */     double ePZ = this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap;
/* 125 */     GL11.glTranslated(ePX, ePY, ePZ);
/* 126 */     float size = 0.25F;
/*     */     
/* 128 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam);
/*     */     
/* 130 */     GL11.glDepthMask(false);
/* 131 */     GL11.glEnable(3042);
/* 132 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 134 */     GL11.glDisable(2884);
/*     */     
/* 136 */     int i = 220;
/* 137 */     int j = i >> 16 & 0xFFFF;
/* 138 */     int k = i & 0xFFFF;
/*     */     
/* 140 */     wr.func_181668_a(5, DefaultVertexFormats.field_181711_k);
/*     */     
/* 142 */     float f9 = 0.0F;
/* 143 */     float f10 = 1.0F;
/* 144 */     for (int c = 0; c < this.points.size(); c++) {
/* 145 */       Vec3 v = (Vec3)this.points.get(c);
/* 146 */       float f13 = c / this.length;
/* 147 */       double dx = v.field_72450_a;
/* 148 */       double dy = v.field_72448_b;
/* 149 */       double dz = v.field_72449_c;
/* 150 */       wr.func_181662_b(dx, dy - size, dz).func_181673_a(f13, f10).func_181671_a(j, k).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F).func_181675_d();
/* 151 */       wr.func_181662_b(dx, dy + size, dz).func_181673_a(f13, f9).func_181671_a(j, k).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F).func_181675_d();
/*     */     }
/*     */     
/* 154 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 156 */     wr.func_181668_a(5, DefaultVertexFormats.field_181711_k);
/*     */     
/* 158 */     for (int c = 0; c < this.points.size(); c++) {
/* 159 */       Vec3 v = (Vec3)this.points.get(c);
/* 160 */       float f13 = c / this.length;
/* 161 */       double dx = v.field_72450_a;
/* 162 */       double dy = v.field_72448_b;
/* 163 */       double dz = v.field_72449_c;
/* 164 */       wr.func_181662_b(dx - size, dy, dz - size).func_181673_a(f13, f10).func_181671_a(j, k).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F).func_181675_d();
/* 165 */       wr.func_181662_b(dx + size, dy, dz + size).func_181673_a(f13, f9).func_181671_a(j, k).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F).func_181675_d();
/*     */     }
/*     */     
/* 168 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 170 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 171 */     GL11.glEnable(2884);
/* 172 */     GL11.glBlendFunc(770, 771);
/* 173 */     GL11.glDisable(3042);
/* 174 */     GL11.glDepthMask(true);
/*     */     
/* 176 */     GL11.glPopMatrix();
/*     */     
/* 178 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getMCParticleTexture());
/*     */     
/* 180 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/* 183 */   public int blendmode = 1;
/* 184 */   public float length = 1.0F;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/beams/FXArc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */