/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.model.PositionTextureVertex;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.lib.TexturedQuadTC;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.models.IModelCustom;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileDioptra;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileDioptraRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  30 */   private static final ResourceLocation crystal = new ResourceLocation("thaumcraft", "models/obj/vis_relay.obj");
/*  31 */   private static final ResourceLocation crystalTexture = new ResourceLocation("thaumcraft", "textures/models/vis_relay.png");
/*  32 */   private static final ResourceLocation gridTexture = new ResourceLocation("thaumcraft", "textures/misc/gridblock.png");
/*  33 */   private static final ResourceLocation sideTexture = new ResourceLocation("thaumcraft", "textures/models/dioptra_side.png");
/*     */   
/*     */   public TileDioptraRenderer()
/*     */   {
/*  37 */     this.model = AdvancedModelLoader.loadModel(crystal);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180535_a(TileEntity te, double x, double y, double z, float pt, int p_180535_9_)
/*     */   {
/*  44 */     TileDioptra tco = (TileDioptra)te;
/*  45 */     if (tco.type < 0) { return;
/*     */     }
/*  47 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*     */ 
/*  50 */     GL11.glPushMatrix();
/*  51 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*     */     
/*  53 */     GL11.glEnable(3042);
/*  54 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  56 */     GL11.glPushMatrix();
/*  57 */     GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  58 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*  61 */     func_147499_a(crystalTexture);
/*     */     
/*  63 */     float t = this.field_147501_a.field_147551_g != null ? this.field_147501_a.field_147551_g.field_70173_aa + pt : 0.0F;
/*     */     
/*  65 */     float rc = 1.0F;
/*  66 */     float gc = 1.0F;
/*  67 */     float bc = 1.0F;
/*     */     
/*  69 */     Aspect as = tco.getAspect(tco.type);
/*  70 */     if (as != null) {
/*  71 */       Color c = new Color(as.getColor());
/*  72 */       rc = c.getRed() / 255.0F;
/*  73 */       gc = c.getGreen() / 255.0F;
/*  74 */       bc = c.getBlue() / 255.0F;
/*     */     } else {
/*  76 */       rc = MathHelper.func_76126_a(t / 12.0F) * 0.3F + 0.7F;
/*  77 */       gc = MathHelper.func_76126_a(t / 11.0F) * 0.3F + 0.7F;
/*  78 */       bc = MathHelper.func_76126_a(t / 10.0F) * 0.3F + 0.7F;
/*     */     }
/*     */     
/*  81 */     GL11.glColor3f(rc, gc, bc);
/*  82 */     GL11.glScaled(1.3D, 1.3D, 1.2D);
/*  83 */     this.model.renderPart("Crystal");
/*  84 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/*     */ 
/*  88 */     if (BlockStateUtils.isEnabled(tco.func_145832_p())) {
/*  89 */       GL11.glBlendFunc(770, 1);
/*  90 */       GL11.glPushMatrix();
/*  91 */       GL11.glTranslated(-0.495D, 0.501D, -0.495D);
/*  92 */       func_147499_a(gridTexture);
/*  93 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  94 */       GL11.glScaled(0.99D, 1.0D, 0.99D);
/*     */       
/*  96 */       for (int a = 0; a < 12; a++)
/*  97 */         for (int b = 0; b < 12; b++) {
/*  98 */           byte t1 = tco.grid_type[(a + b * 13)];
/*  99 */           if (t1 > 10) t1 = (byte)(t1 - 10);
/* 100 */           if (tco.getAspect(t1) != null) {
/* 101 */             Color c1 = new Color(tco.getAspect(t1).getColor());
/* 102 */             byte t2 = tco.grid_type[(a + 1 + b * 13)];
/* 103 */             if (t2 > 10) t2 = (byte)(t2 - 10);
/* 104 */             if (tco.getAspect(t2) != null) {
/* 105 */               Color c2 = new Color(tco.getAspect(t2).getColor());
/* 106 */               byte t3 = tco.grid_type[(a + 1 + (b + 1) * 13)];
/* 107 */               if (t3 > 10) t3 = (byte)(t3 - 10);
/* 108 */               if (tco.getAspect(t3) != null) {
/* 109 */                 Color c3 = new Color(tco.getAspect(t3).getColor());
/* 110 */                 byte t4 = tco.grid_type[(a + (b + 1) * 13)];
/* 111 */                 if (t4 > 10) t4 = (byte)(t4 - 10);
/* 112 */                 if (tco.getAspect(t4) != null) {
/* 113 */                   Color c4 = new Color(tco.getAspect(t4).getColor());
/* 114 */                   double d3 = a - 6;
/* 115 */                   double d5 = b - 6;
/* 116 */                   double dis = Math.sqrt(d3 * d3 + d5 * d5);
/* 117 */                   float s = MathHelper.func_76126_a((float)((tco.counter - dis * 10.0D) / 8.0D));
/* 118 */                   float rr = c1.getRed() + c2.getRed() + c3.getRed() + c4.getRed();
/* 119 */                   float gg = c1.getGreen() + c2.getGreen() + c3.getGreen() + c4.getGreen();
/* 120 */                   float bb = c1.getBlue() + c2.getBlue() + c3.getBlue() + c4.getBlue();
/* 121 */                   Color co = new Color(rr / 1020.0F, gg / 1020.0F, bb / 1020.0F);
/*     */                   
/* 123 */                   TexturedQuadTC quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(a / 12.0F, tco.grid_amt[(a + b * 13)] / (Config.AURABASE * 1.5F), b / 12.0F, 0.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, tco.grid_amt[(a + 1 + b * 13)] / (Config.AURABASE * 1.5F), b / 12.0F, 1.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, tco.grid_amt[(a + 1 + (b + 1) * 13)] / (Config.AURABASE * 1.5F), (b + 1) / 12.0F, 1.0F, 0.0F), new PositionTextureVertex(a / 12.0F, tco.grid_amt[(a + (b + 1) * 13)] / (Config.AURABASE * 1.5F), (b + 1) / 12.0F, 0.0F, 0.0F) });
/*     */                   
/*     */ 
/*     */ 
/*     */ 
/* 128 */                   quad.flipFace();
/* 129 */                   quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), co.getRGB(), 0.9F);
/*     */                   
/* 131 */                   if (a == 0) {
/* 132 */                     quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(0.0F, 0.0F, b / 12.0F, 0.0F, 1.0F), new PositionTextureVertex(0.0F, tco.grid_amt[(b * 13)] / (Config.AURABASE * 1.5F), b / 12.0F, 1.0F, 1.0F), new PositionTextureVertex(0.0F, tco.grid_amt[((b + 1) * 13)] / (Config.AURABASE * 1.5F), (b + 1) / 12.0F, 1.0F, 0.0F), new PositionTextureVertex(0.0F, 0.0F, (b + 1) / 12.0F, 0.0F, 0.0F) });
/*     */                     
/*     */ 
/*     */ 
/*     */ 
/* 137 */                     quad.flipFace();
/* 138 */                     quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), co.getRGB(), 0.9F);
/*     */                   }
/*     */                   
/* 141 */                   if (a == 11) {
/* 142 */                     quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(1.0F, 0.0F, b / 12.0F, 0.0F, 1.0F), new PositionTextureVertex(1.0F, tco.grid_amt[(a + 1 + b * 13)] / (Config.AURABASE * 1.5F), b / 12.0F, 1.0F, 1.0F), new PositionTextureVertex(1.0F, tco.grid_amt[(a + 1 + (b + 1) * 13)] / (Config.AURABASE * 1.5F), (b + 1) / 12.0F, 1.0F, 0.0F), new PositionTextureVertex(1.0F, 0.0F, (b + 1) / 12.0F, 0.0F, 0.0F) });
/*     */                     
/*     */ 
/*     */ 
/*     */ 
/* 147 */                     quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), co.getRGB(), 0.9F);
/*     */                   }
/*     */                   
/* 150 */                   if (b == 0) {
/* 151 */                     quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(a / 12.0F, 0.0F, 0.0F, 0.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, 0.0F, 0.0F, 1.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, tco.grid_amt[(a + 1)] / (Config.AURABASE * 1.5F), 0.0F, 1.0F, 0.0F), new PositionTextureVertex(a / 12.0F, tco.grid_amt[a] / (Config.AURABASE * 1.5F), 0.0F, 0.0F, 0.0F) });
/*     */                     
/*     */ 
/*     */ 
/*     */ 
/* 156 */                     quad.flipFace();
/* 157 */                     quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), co.getRGB(), 0.9F);
/*     */                   }
/*     */                   
/* 160 */                   if (b == 11) {
/* 161 */                     quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(a / 12.0F, 0.0F, 1.0F, 0.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, 0.0F, 1.0F, 1.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, tco.grid_amt[(a + 1 + (b + 1) * 13)] / (Config.AURABASE * 1.5F), 1.0F, 1.0F, 0.0F), new PositionTextureVertex(a / 12.0F, tco.grid_amt[(a + (b + 1) * 13)] / (Config.AURABASE * 1.5F), 1.0F, 0.0F, 0.0F) });
/*     */                     
/*     */ 
/*     */ 
/*     */ 
/* 166 */                     quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), co.getRGB(), 0.9F);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             } } }
/* 171 */       GL11.glPopMatrix();
/*     */       
/* 173 */       GL11.glPushMatrix();
/* 174 */       GlStateManager.func_179129_p();
/* 175 */       GL11.glTranslated(0.0D, 1.0D, 0.0D);
/* 176 */       GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
/* 177 */       for (int q = 0; q < 4; q++) {
/* 178 */         GL11.glPushMatrix();
/* 179 */         GL11.glRotatef(90.0F * q, 1.0F, 0.0F, 0.0F);
/* 180 */         GL11.glTranslated(0.0D, 0.0D, -0.5D);
/* 181 */         UtilsFX.renderQuadCentered(sideTexture, 1.0F, rc, gc, bc, 220, 1, 0.8F);
/* 182 */         GL11.glPopMatrix();
/*     */       }
/* 184 */       GlStateManager.func_179089_o();
/* 185 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 188 */     GL11.glDisable(3042);
/* 189 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileDioptraRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */