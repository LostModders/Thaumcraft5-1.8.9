/*     */ package thaumcraft.client.renderers.entity.construct;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.models.IModelCustom;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderTurretCrossbowAdvanced
/*     */   extends RenderLiving
/*     */ {
/*     */   private IModelCustom model;
/*  26 */   private static final ResourceLocation TURMODEL = new ResourceLocation("thaumcraft", "models/obj/crossbow_advanced.obj");
/*     */   
/*     */   public RenderTurretCrossbowAdvanced(RenderManager rm)
/*     */   {
/*  30 */     super(rm, null, 0.5F);
/*  31 */     this.model = AdvancedModelLoader.loadModel(TURMODEL);
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderTurret(EntityTurretCrossbow turret, double x, double y, double z, float par8, float pTicks)
/*     */   {
/*  37 */     func_180548_c(turret);
/*  38 */     GL11.glPushMatrix();
/*  39 */     GL11.glEnable(32826);
/*  40 */     GL11.glEnable(3042);
/*  41 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  43 */     GL11.glTranslated(x, y + 0.75D, z);
/*     */     
/*  45 */     GL11.glPushMatrix();
/*  46 */     this.model.renderPart("legs");
/*  47 */     GL11.glPopMatrix();
/*     */     
/*  49 */     GL11.glPushMatrix();
/*  50 */     if (turret.field_70737_aN > 0) {
/*  51 */       GlStateManager.func_179131_c(1.0F, 0.5F, 0.5F, 1.0F);
/*  52 */       float jiggle = turret.field_70737_aN / 500.0F;
/*  53 */       GL11.glTranslated(turret.func_70681_au().nextGaussian() * jiggle, turret.func_70681_au().nextGaussian() * jiggle, turret.func_70681_au().nextGaussian() * jiggle);
/*     */     }
/*     */     
/*  56 */     GL11.glRotatef(turret.field_70758_at + (turret.field_70759_as - turret.field_70758_at) * pTicks, 0.0F, -1.0F, 0.0F);
/*  57 */     GL11.glRotatef(turret.field_70127_C + (turret.field_70125_A - turret.field_70127_C) * pTicks, 1.0F, 0.0F, 0.0F);
/*     */     
/*  59 */     this.model.renderPart("mech");
/*  60 */     this.model.renderPart("box");
/*  61 */     this.model.renderPart("shield");
/*  62 */     this.model.renderPart("brain");
/*     */     
/*  64 */     GL11.glPushMatrix();
/*  65 */     GL11.glTranslated(0.0D, 0.0D, MathHelper.func_76126_a(MathHelper.func_76129_c(turret.loadProgressForRender) * 3.1415927F * 2.0F) / 12.0F);
/*  66 */     this.model.renderPart("loader");
/*  67 */     GL11.glPopMatrix();
/*     */     
/*  69 */     float sp = 0.0F;
/*  70 */     if (func_77040_d(turret, pTicks) > -9990.0F) {
/*  71 */       sp = MathHelper.func_76126_a(MathHelper.func_76129_c(func_77040_d(turret, pTicks)) * 3.1415927F * 2.0F) * 20.0F;
/*     */     }
/*     */     
/*  74 */     GL11.glTranslated(0.0D, 0.0D, 0.375D);
/*     */     
/*  76 */     GL11.glPushMatrix();
/*  77 */     GL11.glRotatef(sp, 0.0F, 1.0F, 0.0F);
/*  78 */     this.model.renderPart("bow1");
/*  79 */     GL11.glPopMatrix();
/*     */     
/*  81 */     GL11.glPushMatrix();
/*  82 */     GL11.glRotatef(sp, 0.0F, -1.0F, 0.0F);
/*  83 */     this.model.renderPart("bow2");
/*  84 */     GL11.glPopMatrix();
/*     */     
/*  86 */     GL11.glPopMatrix();
/*  87 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  89 */     GL11.glDisable(3042);
/*  90 */     GL11.glDisable(32826);
/*  91 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   protected float func_77040_d(EntityLivingBase e, float p_77040_2_)
/*     */   {
/*  96 */     ((EntityTurretCrossbow)e).loadProgressForRender = ((EntityTurretCrossbow)e).getLoadProgress(p_77040_2_);
/*  97 */     return super.func_77040_d(e, p_77040_2_);
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(int orientation) {
/* 101 */     GL11.glTranslated(0.0D, 0.5D, 0.0D);
/* 102 */     if (orientation == 0) {
/* 103 */       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 104 */     } else if (orientation != 1) {
/* 105 */       if (orientation == 2) {
/* 106 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 107 */       } else if (orientation == 3) {
/* 108 */         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 109 */       } else if (orientation == 4) {
/* 110 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 111 */       } else if (orientation == 5)
/* 112 */         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */     }
/* 114 */     GL11.glTranslated(0.0D, -0.5D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 121 */     renderTurret((EntityTurretCrossbow)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/* 124 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/crossbow_advanced.png");
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 128 */     return rl;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/RenderTurretCrossbowAdvanced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */