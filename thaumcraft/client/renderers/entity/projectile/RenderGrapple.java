/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.projectile.EntityGrapple;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderGrapple
/*     */   extends Render
/*     */ {
/*     */   public RenderGrapple(RenderManager rm)
/*     */   {
/*  28 */     super(rm);
/*  29 */     this.field_76989_e = 0.0F;
/*     */   }
/*     */   
/*  32 */   ResourceLocation beam = new ResourceLocation("thaumcraft", "textures/misc/particles.png");
/*     */   
/*     */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {
/*  35 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  36 */     boolean sticky = ((EntityGrapple)entity).sticky;
/*  37 */     GL11.glPushMatrix();
/*  38 */     GL11.glTranslated(x, y, z);
/*  39 */     GL11.glEnable(3042);
/*  40 */     GL11.glBlendFunc(770, 1);
/*  41 */     GL11.glDisable(2884);
/*  42 */     func_110776_a(ParticleEngine.particleTexture);
/*     */     
/*  44 */     float f2 = (1 + entity.field_70173_aa % 6) / 8.0F;
/*  45 */     float f3 = f2 + 0.125F;
/*  46 */     float f4 = 0.875F;
/*  47 */     float f5 = f4 + 0.125F;
/*     */     
/*  49 */     float f7 = 0.5F;
/*     */     
/*  51 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
/*     */     
/*  53 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  54 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  55 */     float bob = MathHelper.func_76126_a(entity.field_70173_aa / 5.0F) * 0.2F + 0.2F;
/*  56 */     GL11.glScalef(1.0F + bob, 1.0F + bob, 1.0F + bob);
/*  57 */     tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*  58 */     int i = 220;
/*  59 */     int j = i >> 16 & 0xFFFF;
/*  60 */     int k = i & 0xFFFF;
/*  61 */     tessellator.func_178180_c().func_181662_b(-f7, -f7, 0.0D).func_181673_a(f2, f5).func_181666_a(sticky ? 0.0F : 1.0F, 1.0F, sticky ? 0.2F : 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  62 */     tessellator.func_178180_c().func_181662_b(f7, -f7, 0.0D).func_181673_a(f3, f5).func_181666_a(sticky ? 0.0F : 1.0F, 1.0F, sticky ? 0.2F : 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  63 */     tessellator.func_178180_c().func_181662_b(f7, f7, 0.0D).func_181673_a(f3, f4).func_181666_a(sticky ? 0.0F : 1.0F, 1.0F, sticky ? 0.2F : 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  64 */     tessellator.func_178180_c().func_181662_b(-f7, f7, 0.0D).func_181673_a(f2, f4).func_181666_a(sticky ? 0.0F : 1.0F, 1.0F, sticky ? 0.2F : 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  65 */     tessellator.func_78381_a();
/*  66 */     GL11.glEnable(2884);
/*  67 */     GL11.glBlendFunc(770, 771);
/*  68 */     GL11.glDisable(3042);
/*  69 */     GL11.glDisable(32826);
/*  70 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  72 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/*  75 */     calcPoints(((EntityGrapple)entity).func_85052_h(), (EntityGrapple)entity, pticks);
/*     */     
/*     */ 
/*  78 */     GL11.glPushMatrix();
/*     */     
/*  80 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(this.beam);
/*     */     
/*  82 */     GL11.glDepthMask(false);
/*  83 */     GL11.glEnable(3042);
/*  84 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  86 */     GL11.glDisable(2884);
/*     */     
/*  88 */     for (int c = 0; c < this.points.size(); c++) {
/*  89 */       int pp = (c + entity.field_70173_aa) % 13;
/*  90 */       float alpha = Math.min(1.0F, 1.0F - c / (this.points.size() / 4.0F));
/*  91 */       Vec3 vc = (Vec3)this.points.get(c);
/*  92 */       GL11.glPushMatrix();
/*  93 */       GL11.glTranslated(x + vc.field_72450_a, y + vc.field_72448_b, z + vc.field_72449_c);
/*  94 */       UtilsFX.renderBillboardQuad(0.07500000298023224D, 16, 16, 32 + pp);
/*  95 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*  98 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  99 */     GL11.glEnable(2884);
/* 100 */     GL11.glBlendFunc(770, 771);
/* 101 */     GL11.glDisable(3042);
/* 102 */     GL11.glDepthMask(true);
/* 103 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/* 106 */   public ArrayList<Vec3> points = new ArrayList();
/* 107 */   public float length = 1.0F;
/*     */   
/*     */   private void calcPoints(EntityLivingBase thrower, EntityGrapple grapple, float pt) {
/* 110 */     if ((thrower == null) || (grapple == null)) return;
/* 111 */     double tx = thrower.field_70142_S + (thrower.field_70165_t - thrower.field_70142_S) * pt;
/* 112 */     double ty = thrower.field_70137_T + (thrower.field_70163_u - thrower.field_70137_T) * pt;
/* 113 */     double tz = thrower.field_70136_U + (thrower.field_70161_v - thrower.field_70136_U) * pt;
/* 114 */     double gx = grapple.field_70142_S + (grapple.field_70165_t - grapple.field_70142_S) * pt;
/* 115 */     double gy = grapple.field_70137_T + (grapple.field_70163_u - grapple.field_70137_T) * pt;
/* 116 */     double gz = grapple.field_70136_U + (grapple.field_70161_v - grapple.field_70136_U) * pt;
/* 117 */     this.points.clear();
/* 118 */     Vec3 vs = new Vec3(0.0D, 0.0D, 0.0D);
/* 119 */     Vec3 ve = new Vec3(tx - gx, ty - gy + thrower.field_70131_O / 2.0F, tz - gz);
/* 120 */     this.length = ((float)(ve.func_72433_c() * 5.0D));
/* 121 */     int steps = (int)this.length;
/* 122 */     this.points.add(vs);
/* 123 */     for (int a = 1; a < steps - 1; a++) {
/* 124 */       float dist = a * (this.length / steps);
/* 125 */       double dx = (tx - gx) / steps * a + MathHelper.func_76126_a(dist / 10.0F) * grapple.ampl;
/* 126 */       double dy = (ty - gy + thrower.field_70131_O / 2.0F) / steps * a + MathHelper.func_76126_a(dist / 8.0F) * grapple.ampl;
/* 127 */       double dz = (tz - gz) / steps * a + MathHelper.func_76126_a(dist / 6.0F) * grapple.ampl;
/* 128 */       Vec3 vp = new Vec3(dx, dy, dz);
/* 129 */       this.points.add(vp);
/*     */     }
/* 131 */     this.points.add(ve);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*     */   {
/* 139 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 145 */     return TextureMap.field_110575_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderGrapple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */