/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.tools.ItemThaumometer;
/*     */ import thaumcraft.common.lib.aura.EntityAuraNode;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderAuraNode
/*     */   extends Render
/*     */ {
/*     */   public RenderAuraNode(RenderManager rm)
/*     */   {
/*  30 */     super(rm);
/*  31 */     this.field_76989_e = 0.0F;
/*     */   }
/*     */   
/*  34 */   int size1 = 4;
/*  35 */   int size2 = 0;
/*     */   
/*     */ 
/*     */   private void doRender(EntityAuraNode entity, double x, double y, double z, float fq, float pticks)
/*     */   {
/*  40 */     if (entity.field_70128_L) return;
/*  41 */     double vr = 8000.0D;
/*  42 */     long t = System.currentTimeMillis();
/*  43 */     boolean canSee = EntityUtils.hasRevealer(Minecraft.func_71410_x().func_175606_aa());
/*  44 */     if (!canSee) {
/*  45 */       canSee = (Minecraft.func_71410_x().field_71439_g.func_70694_bm() != null) && ((Minecraft.func_71410_x().field_71439_g.func_70694_bm().func_77973_b() instanceof ItemThaumometer)) && (EntityUtils.isVisibleTo(0.8F, Minecraft.func_71410_x().func_175606_aa(), entity, 16.0F));
/*     */       
/*     */ 
/*  48 */       vr = 300.0D;
/*     */     }
/*     */     
/*  51 */     if (!canSee) { return;
/*     */     }
/*  53 */     double d = entity.func_70068_e(Minecraft.func_71410_x().func_175606_aa());
/*  54 */     if (d > vr) { return;
/*     */     }
/*  56 */     float alpha = 1.0F - (float)Math.min(1.0D, d / (vr * 0.8999999761581421D));
/*     */     
/*  58 */     int color = 8947848;
/*  59 */     int blend = 1;
/*  60 */     int type = 1;
/*  61 */     float size = 0.15F + entity.getNodeSize() / (Config.AURABASE * 1.5F);
/*  62 */     if (entity.getAspect() != null) {
/*  63 */       color = entity.getAspect().getColor();
/*  64 */       blend = entity.getAspect().getBlend();
/*  65 */       type = 1 + entity.getNodeType();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  71 */     GlStateManager.func_179094_E();
/*  72 */     func_110776_a(texture);
/*  73 */     GlStateManager.func_179097_i();
/*     */     
/*  75 */     UtilsFX.renderFacingQuad(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 32, 32, entity.field_70173_aa % 32, size, color, 0.75F * alpha, blend, pticks);
/*     */     
/*     */ 
/*     */ 
/*  79 */     float s = 1.0F - MathHelper.func_76126_a((entity.field_70173_aa + pticks) / 8.0F) / 5.0F;
/*     */     
/*  81 */     UtilsFX.renderFacingQuad(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 32, 32, 800 + entity.field_70173_aa % 16, s * size * 0.7F, color, 0.9F * alpha, blend, pticks);
/*     */     
/*     */ 
/*     */ 
/*  85 */     UtilsFX.renderFacingQuad(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 32, 32, 32 * type + entity.field_70173_aa % 32, size / 3.0F, 16777215, 1.0F * alpha, type == 2 ? 771 : 1, pticks);
/*     */     
/*     */ 
/*     */ 
/*  89 */     GlStateManager.func_179126_j();
/*     */     
/*  91 */     GlStateManager.func_179121_F();
/*     */     
/*  93 */     if (d < 30.0D) {
/*  94 */       float sc = 1.0F - (float)Math.min(1.0D, d / 25.0D);
/*  95 */       GlStateManager.func_179094_E();
/*  96 */       GlStateManager.func_179137_b(x, y, z);
/*  97 */       GlStateManager.func_179139_a(0.025D * sc, 0.025D * sc, 0.025D * sc);
/*  98 */       UtilsFX.rotateToPlayer();
/*  99 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 100 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 105 */       UtilsFX.drawTag(-8, -32, entity.getAspect(), entity.getNodeSize(), 0, 0.0D);
/* 106 */       GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
/* 107 */       String text = StatCollector.func_74838_a("nodetype." + entity.getNodeType());
/* 108 */       int sw = Minecraft.func_71410_x().field_71466_p.func_78256_a(text);
/* 109 */       Minecraft.func_71410_x().field_71466_p.func_175065_a(text, -sw / 2.0F, -72.0F, 16777215, false);
/* 110 */       GlStateManager.func_179121_F();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*     */   {
/* 118 */     doRender((EntityAuraNode)entity, d, d1, d2, f, f1);
/*     */   }
/*     */   
/* 121 */   public static final ResourceLocation texture = new ResourceLocation("thaumcraft", "textures/misc/nodes.png");
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 125 */     return texture;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/RenderAuraNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */