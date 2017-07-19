/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.RenderHelper;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderPrimalOrb
/*     */   extends Render
/*     */ {
/*     */   public RenderPrimalOrb(RenderManager rm)
/*     */   {
/*  27 */     super(rm);
/*  28 */     this.field_76989_e = 0.0F;
/*     */   }
/*     */   
/*     */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks)
/*     */   {
/*  33 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*  35 */     GL11.glPushMatrix();
/*     */     
/*  37 */     RenderHelper.func_74518_a();
/*  38 */     float f1 = entity.field_70173_aa / 80.0F;
/*  39 */     float f3 = 0.9F;
/*  40 */     float f2 = 0.0F;
/*     */     
/*  42 */     Random random = new Random(entity.func_145782_y());
/*  43 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  44 */     GL11.glDisable(3553);
/*  45 */     GL11.glShadeModel(7425);
/*  46 */     GL11.glEnable(3042);
/*  47 */     GL11.glBlendFunc(770, 1);
/*  48 */     GL11.glDisable(3008);
/*  49 */     GL11.glEnable(2884);
/*  50 */     GL11.glDepthMask(false);
/*  51 */     GL11.glPushMatrix();
/*  52 */     for (int i = 0; i < 12; i++) {
/*  53 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  54 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  55 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/*  56 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  57 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  58 */       GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
/*  59 */       tessellator.func_178180_c().func_181668_a(6, DefaultVertexFormats.field_181706_f);
/*  60 */       float fa = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
/*  61 */       float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
/*  62 */       fa /= 30.0F / (Math.min(entity.field_70173_aa, 10) / 10.0F);
/*  63 */       f4 /= 30.0F / (Math.min(entity.field_70173_aa, 10) / 10.0F);
/*  64 */       tessellator.func_178180_c().func_181662_b(0.0D, 0.0D, 0.0D).func_181669_b(255, 255, 255, (int)(255.0F * (1.0F - f1))).func_181675_d();
/*  65 */       tessellator.func_178180_c().func_181662_b(-0.866D * f4, fa, -0.5F * f4).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  66 */       tessellator.func_178180_c().func_181662_b(0.866D * f4, fa, -0.5F * f4).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  67 */       tessellator.func_178180_c().func_181662_b(0.0D, fa, 1.0F * f4).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  68 */       tessellator.func_178180_c().func_181662_b(-0.866D * f4, fa, -0.5F * f4).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  69 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  72 */     GL11.glPopMatrix();
/*  73 */     GL11.glDepthMask(true);
/*  74 */     GL11.glDisable(2884);
/*  75 */     GL11.glDisable(3042);
/*  76 */     GL11.glShadeModel(7424);
/*  77 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  78 */     GL11.glEnable(3553);
/*  79 */     GL11.glEnable(3008);
/*  80 */     RenderHelper.func_74519_b();
/*  81 */     GL11.glPopMatrix();
/*     */     
/*  83 */     GL11.glPushMatrix();
/*  84 */     GL11.glTranslated(x, y, z);
/*  85 */     GL11.glEnable(3042);
/*  86 */     GL11.glBlendFunc(770, 1);
/*  87 */     func_110776_a(ParticleEngine.particleTexture);
/*  88 */     f2 = entity.field_70173_aa % 13 / 16.0F;
/*  89 */     f3 = f2 + 0.0624375F;
/*  90 */     float f4 = 0.125F;
/*  91 */     float f5 = f4 + 0.0624375F;
/*  92 */     float f6 = 1.0F;
/*  93 */     float f7 = 0.5F;
/*  94 */     float f8 = 0.5F;
/*  95 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
/*  96 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  97 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  98 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  99 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181710_j);
/* 100 */     tessellator.func_178180_c().func_181662_b(0.0F - f7, 0.0F - f8, 0.0D).func_181673_a(f2, f5).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 101 */     tessellator.func_178180_c().func_181662_b(f6 - f7, 0.0F - f8, 0.0D).func_181673_a(f3, f5).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 102 */     tessellator.func_178180_c().func_181662_b(f6 - f7, 1.0F - f8, 0.0D).func_181673_a(f3, f4).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 103 */     tessellator.func_178180_c().func_181662_b(0.0F - f7, 1.0F - f8, 0.0D).func_181673_a(f2, f4).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 104 */     tessellator.func_78381_a();
/* 105 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 106 */     GL11.glDisable(3042);
/* 107 */     GL11.glDisable(32826);
/* 108 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*     */   {
/* 116 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 122 */     return TextureMap.field_110575_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderPrimalOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */