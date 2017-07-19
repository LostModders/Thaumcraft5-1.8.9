/*     */ package thaumcraft.client.renderers.entity.construct;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.models.IModelCustom;
/*     */ import thaumcraft.common.entities.construct.EntityTurretFocus;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderTurretFocus
/*     */   extends RenderLiving
/*     */ {
/*     */   private IModelCustom model;
/*  27 */   private static final ResourceLocation TURMODEL = new ResourceLocation("thaumcraft", "models/obj/turret.obj");
/*     */   
/*     */   public RenderTurretFocus(RenderManager rm)
/*     */   {
/*  31 */     super(rm, null, 0.5F);
/*  32 */     this.model = AdvancedModelLoader.loadModel(TURMODEL);
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderTurret(EntityTurretFocus turret, double x, double y, double z, float par8, float pTicks)
/*     */   {
/*  38 */     func_180548_c(turret);
/*  39 */     GL11.glPushMatrix();
/*  40 */     GL11.glEnable(32826);
/*  41 */     GL11.glEnable(3042);
/*  42 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  44 */     GL11.glTranslated(x, y, z);
/*     */     
/*  46 */     GL11.glPushMatrix();
/*  47 */     translateFromOrientation(turret.getFacing().ordinal());
/*  48 */     this.model.renderPart("base");
/*  49 */     GL11.glPopMatrix();
/*     */     
/*  51 */     GL11.glPushMatrix();
/*  52 */     if (turret.field_70737_aN > 0) {
/*  53 */       GlStateManager.func_179131_c(1.0F, 0.5F, 0.5F, 1.0F);
/*  54 */       float jiggle = turret.field_70737_aN / 500.0F;
/*  55 */       GL11.glTranslated(turret.func_70681_au().nextGaussian() * jiggle, turret.func_70681_au().nextGaussian() * jiggle, turret.func_70681_au().nextGaussian() * jiggle);
/*     */     }
/*  57 */     GL11.glTranslated(0.0D, 0.5D, 0.0D);
/*  58 */     GL11.glRotatef(turret.field_70758_at + (turret.field_70759_as - turret.field_70758_at) * pTicks, 0.0F, -1.0F, 0.0F);
/*  59 */     GL11.glRotatef(turret.field_70127_C + (turret.field_70125_A - turret.field_70127_C) * pTicks, 1.0F, 0.0F, 0.0F);
/*     */     
/*  61 */     this.model.renderPart("ball");
/*     */     
/*  63 */     if ((turret.func_70694_bm() != null) && ((turret.func_70694_bm().func_77973_b() instanceof ItemFocusBasic))) {
/*  64 */       Color col = new Color(((ItemFocusBasic)turret.func_70694_bm().func_77973_b()).getFocusColor(turret.func_70694_bm()));
/*  65 */       GL11.glColor4f(col.getRed() / 255.0F, col.getGreen() / 255.0F, col.getBlue() / 255.0F, 0.9F);
/*  66 */       this.model.renderPart("focus");
/*     */     }
/*     */     
/*  69 */     GL11.glPopMatrix();
/*  70 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  72 */     GL11.glDisable(3042);
/*  73 */     GL11.glDisable(32826);
/*  74 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(int orientation) {
/*  78 */     GL11.glTranslated(0.0D, 0.5D, 0.0D);
/*  79 */     if (orientation == 0) {
/*  80 */       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  81 */     } else if (orientation != 1) {
/*  82 */       if (orientation == 2) {
/*  83 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  84 */       } else if (orientation == 3) {
/*  85 */         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  86 */       } else if (orientation == 4) {
/*  87 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  88 */       } else if (orientation == 5)
/*  89 */         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */     }
/*  91 */     GL11.glTranslated(0.0D, -0.5D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  98 */     renderTurret((EntityTurretFocus)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/* 101 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/focus_turret.png");
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 105 */     return rl;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/RenderTurretFocus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */