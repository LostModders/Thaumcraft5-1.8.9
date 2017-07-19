/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.lib.UtilsFX.Vector;
/*     */ import thaumcraft.common.entities.projectile.EntityHomingShard;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderHomingShard
/*     */   extends Render
/*     */ {
/*     */   public RenderHomingShard(RenderManager rm)
/*     */   {
/*  28 */     super(rm);
/*  29 */     this.field_76989_e = 0.0F;
/*     */   }
/*     */   
/*  32 */   private Random random = new Random();
/*     */   
/*     */   public void renderEntityAt(EntityHomingShard entity, double x, double y, double z, float fq, float pticks) {
/*  35 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*  37 */     GL11.glPushMatrix();
/*  38 */     GL11.glDepthMask(false);
/*  39 */     GL11.glEnable(3042);
/*  40 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  42 */     func_110776_a(ParticleEngine.particleTexture);
/*     */     
/*  44 */     float f2 = (8 + entity.field_70173_aa % 8) / 16.0F;
/*  45 */     float f3 = f2 + 0.0625F;
/*  46 */     float f4 = 0.25F;
/*  47 */     float f5 = f4 + 0.0625F;
/*     */     
/*  49 */     float f6 = 1.0F;
/*  50 */     float f7 = 0.5F;
/*  51 */     float f8 = 0.5F;
/*     */     
/*  53 */     GL11.glColor4f(0.405F, 0.075F, 0.525F, 1.0F);
/*     */     
/*  55 */     GL11.glPushMatrix();
/*  56 */     GL11.glTranslated(x, y, z);
/*  57 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  58 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  59 */     GL11.glScalef(0.4F + 0.1F * entity.getStrength(), 0.4F + 0.1F * entity.getStrength(), 0.4F + 0.1F * entity.getStrength());
/*  60 */     tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*  61 */     int i = 240;
/*  62 */     int j = i >> 16 & 0xFFFF;
/*  63 */     int k = i & 0xFFFF;
/*  64 */     tessellator.func_178180_c().func_181662_b(-f7, -f8, 0.0D).func_181673_a(f2, f5).func_181666_a(0.405F, 0.075F, 0.525F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  65 */     tessellator.func_178180_c().func_181662_b(f6 - f7, -f8, 0.0D).func_181673_a(f3, f5).func_181666_a(0.405F, 0.075F, 0.525F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  66 */     tessellator.func_178180_c().func_181662_b(f6 - f7, 1.0F - f8, 0.0D).func_181673_a(f3, f4).func_181666_a(0.405F, 0.075F, 0.525F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  67 */     tessellator.func_178180_c().func_181662_b(-f7, 1.0F - f8, 0.0D).func_181673_a(f2, f4).func_181666_a(0.405F, 0.075F, 0.525F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  68 */     tessellator.func_78381_a();
/*  69 */     GL11.glPopMatrix();
/*     */     
/*  71 */     GL11.glPushMatrix();
/*  72 */     GL11.glBlendFunc(770, 1);
/*  73 */     func_110776_a(beamTexture);
/*  74 */     Minecraft mc = Minecraft.func_71410_x();
/*  75 */     EntityPlayerSP p = mc.field_71439_g;
/*  76 */     double doubleX = p.field_70142_S + (p.field_70165_t - p.field_70142_S) * pticks;
/*  77 */     double doubleY = p.field_70137_T + (p.field_70163_u - p.field_70137_T) * pticks;
/*  78 */     double doubleZ = p.field_70136_U + (p.field_70161_v - p.field_70136_U) * pticks;
/*  79 */     UtilsFX.Vector player = new UtilsFX.Vector((float)doubleX, (float)doubleY, (float)doubleZ);
/*     */     
/*  81 */     double dX = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * pticks;
/*  82 */     double dY = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * pticks;
/*  83 */     double dZ = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * pticks;
/*  84 */     UtilsFX.Vector start = new UtilsFX.Vector((float)dX, (float)dY, (float)dZ);
/*     */     
/*  86 */     if (entity.vl.size() == 0) {
/*  87 */       entity.vl.add(start);
/*     */     }
/*     */     
/*  90 */     GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
/*  91 */     UtilsFX.Vector vs = new UtilsFX.Vector((float)dX, (float)dY, (float)dZ);
/*     */     
/*  93 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181711_k);
/*  94 */     int c = entity.vl.size();
/*  95 */     for (UtilsFX.Vector nv : entity.vl) {
/*  96 */       UtilsFX.drawBeam(vs, nv, player, 0.25F * (c / entity.vl.size()), 240, 0.405F, 0.075F, 0.525F, 1.0F);
/*  97 */       vs = nv;
/*  98 */       c--;
/*     */     }
/*     */     
/* 101 */     tessellator.func_78381_a();
/*     */     
/* 103 */     GL11.glPopMatrix();
/*     */     
/* 105 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 106 */     GL11.glBlendFunc(770, 771);
/* 107 */     GL11.glDisable(3042);
/* 108 */     GL11.glDisable(32826);
/*     */     
/* 110 */     GL11.glDepthMask(true);
/* 111 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 116 */   private static final ResourceLocation beamTexture = new ResourceLocation("thaumcraft", "textures/misc/beaml.png");
/*     */   
/*     */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*     */   {
/* 120 */     renderEntityAt((EntityHomingShard)entity, d, d1, d2, f, f1);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 125 */     return TextureMap.field_110575_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderHomingShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */