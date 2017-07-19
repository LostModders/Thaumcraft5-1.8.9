/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*     */ import thaumcraft.common.items.resources.ItemShard.ShardType;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderPrimalArrow extends Render
/*     */ {
/*     */   public RenderPrimalArrow(RenderManager renderManager)
/*     */   {
/*  25 */     super(renderManager);
/*     */   }
/*     */   
/*  28 */   private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
/*  29 */   int size1 = 0;
/*  30 */   int size2 = 0;
/*     */   
/*     */ 
/*     */   public void renderArrow(EntityPrimalArrow arrow, double x, double y, double z, float ns, float prt)
/*     */   {
/*  35 */     func_180548_c(arrow);
/*  36 */     GL11.glPushMatrix();
/*     */     
/*  38 */     GL11.glEnable(3042);
/*  39 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  41 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  42 */     GL11.glRotatef(arrow.field_70126_B + (arrow.field_70177_z - arrow.field_70126_B) * prt - 90.0F, 0.0F, 1.0F, 0.0F);
/*  43 */     GL11.glRotatef(arrow.field_70127_C + (arrow.field_70125_A - arrow.field_70127_C) * prt, 0.0F, 0.0F, 1.0F);
/*  44 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  45 */     byte b0 = 0;
/*  46 */     float f2 = 0.0F;
/*  47 */     float f3 = 0.5F;
/*  48 */     float f4 = (0 + b0 * 10) / 32.0F;
/*  49 */     float f5 = (5 + b0 * 10) / 32.0F;
/*  50 */     float f6 = 0.0F;
/*  51 */     float f7 = 0.15625F;
/*  52 */     float f8 = (5 + b0 * 10) / 32.0F;
/*  53 */     float f9 = (10 + b0 * 10) / 32.0F;
/*  54 */     float f10 = arrow.type < 8 ? 0.05625F : 0.033F;
/*  55 */     GL11.glEnable(32826);
/*  56 */     float f11 = arrow.field_70249_b - prt;
/*     */     
/*  58 */     if (f11 > 0.0F)
/*     */     {
/*  60 */       float f12 = -MathHelper.func_76126_a(f11 * 3.0F) * f11;
/*  61 */       GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     
/*  64 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, (100.0F - arrow.field_70252_j) / (arrow.type < 8 ? 100.0F : 20.0F));
/*     */     
/*  66 */     GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/*  67 */     GL11.glScalef(f10, f10, f10);
/*  68 */     GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
/*  69 */     GL11.glNormal3f(f10, 0.0F, 0.0F);
/*  70 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  71 */     tessellator.func_178180_c().func_181662_b(-7.0D, -2.0D, -2.0D).func_181673_a(f6, f8).func_181675_d();
/*  72 */     tessellator.func_178180_c().func_181662_b(-7.0D, -2.0D, 2.0D).func_181673_a(f7, f8).func_181675_d();
/*  73 */     tessellator.func_178180_c().func_181662_b(-7.0D, 2.0D, 2.0D).func_181673_a(f7, f9).func_181675_d();
/*  74 */     tessellator.func_178180_c().func_181662_b(-7.0D, 2.0D, -2.0D).func_181673_a(f6, f9).func_181675_d();
/*  75 */     tessellator.func_78381_a();
/*  76 */     GL11.glNormal3f(-f10, 0.0F, 0.0F);
/*  77 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  78 */     tessellator.func_178180_c().func_181662_b(-7.0D, 2.0D, -2.0D).func_181673_a(f6, f8).func_181675_d();
/*  79 */     tessellator.func_178180_c().func_181662_b(-7.0D, 2.0D, 2.0D).func_181673_a(f7, f8).func_181675_d();
/*  80 */     tessellator.func_178180_c().func_181662_b(-7.0D, -2.0D, 2.0D).func_181673_a(f7, f9).func_181675_d();
/*  81 */     tessellator.func_178180_c().func_181662_b(-7.0D, -2.0D, -2.0D).func_181673_a(f6, f9).func_181675_d();
/*  82 */     tessellator.func_78381_a();
/*     */     
/*  84 */     for (int i = 0; i < 4; i++)
/*     */     {
/*  86 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  87 */       GL11.glNormal3f(0.0F, 0.0F, f10);
/*  88 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  89 */       tessellator.func_178180_c().func_181662_b(-8.0D, -2.0D, 0.0D).func_181673_a(f2, f4).func_181675_d();
/*  90 */       tessellator.func_178180_c().func_181662_b(8.0D, -2.0D, 0.0D).func_181673_a(f3, f4).func_181675_d();
/*  91 */       tessellator.func_178180_c().func_181662_b(8.0D, 2.0D, 0.0D).func_181673_a(f3, f5).func_181675_d();
/*  92 */       tessellator.func_178180_c().func_181662_b(-8.0D, 2.0D, 0.0D).func_181673_a(f2, f5).func_181675_d();
/*  93 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  96 */     GL11.glDisable(32826);
/*     */     
/*  98 */     GL11.glDisable(3042);
/*  99 */     GL11.glPopMatrix();
/*     */     
/* 101 */     GL11.glPushMatrix();
/*     */     
/* 103 */     func_110776_a(ParticleEngine.particleTexture);
/*     */     
/* 105 */     if (arrow.type < 8) {
/* 106 */       int i = 72 + arrow.field_70173_aa % 8;
/* 107 */       UtilsFX.renderFacingQuad(arrow.field_70165_t, arrow.field_70163_u, arrow.field_70161_v, 16, 16, i, 0.25F, ItemShard.ShardType.byMetadata(arrow.type).getAspect().getColor(), (100.0F - arrow.field_70252_j) / 100.0F, arrow.type < 5 ? 1 : 771, prt);
/*     */     }
/*     */     
/*     */ 
/* 111 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation getArrowTextures(EntityPrimalArrow par1EntityArrow)
/*     */   {
/* 117 */     return arrowTextures;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 126 */     return getArrowTextures((EntityPrimalArrow)par1Entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 132 */     renderArrow((EntityPrimalArrow)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderPrimalArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */