/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.entity.RenderAuraNode;
/*     */ import thaumcraft.client.renderers.models.ModelCube;
/*     */ import thaumcraft.common.tiles.misc.TileEtherealBloom;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileEtherealBloomRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  21 */   private static final ResourceLocation tx1 = new ResourceLocation("thaumcraft", "textures/models/crystalcapacitor.png");
/*  22 */   private static final ResourceLocation tx2 = new ResourceLocation("thaumcraft", "textures/models/bloom_leaves.png");
/*  23 */   private static final ResourceLocation tx3 = new ResourceLocation("thaumcraft", "textures/models/bloom_stalk.png");
/*  24 */   private ModelCube model = new ModelCube();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180535_a(TileEntity tile, double x, double y, double z, float pt, int p_180535_9_)
/*     */   {
/*  32 */     float rc1 = ((TileEtherealBloom)tile).growthCounter + pt;
/*  33 */     float rc2 = rc1;
/*  34 */     float rc3 = rc1 - 33.0F;
/*  35 */     float rc4 = rc1 - 66.0F;
/*     */     
/*  37 */     if (rc1 > 100.0F) rc1 = 100.0F;
/*  38 */     if (rc2 > 50.0F) { rc2 = 50.0F;
/*     */     }
/*  40 */     if (rc3 < 0.0F) rc3 = 0.0F;
/*  41 */     if (rc3 > 33.0F) { rc3 = 33.0F;
/*     */     }
/*  43 */     if (rc4 < 0.0F) rc4 = 0.0F;
/*  44 */     if (rc4 > 33.0F) { rc4 = 33.0F;
/*     */     }
/*  46 */     float scale1 = rc1 / 100.0F;
/*  47 */     float scale2 = rc2 / 60.0F + 0.1666666F;
/*  48 */     float scale3 = rc3 / 33.0F;
/*  49 */     float scale4 = rc4 / 33.0F * 0.7F;
/*     */     
/*  51 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*     */ 
/*  54 */     GL11.glPushMatrix();
/*  55 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  56 */     GL11.glEnable(3042);
/*  57 */     GL11.glBlendFunc(770, 1);
/*     */     
/*     */ 
/*  60 */     GL11.glPushMatrix();
/*  61 */     GL11.glDepthMask(false);
/*  62 */     GL11.glDisable(2884);
/*  63 */     int i = ((TileEtherealBloom)tile).counter % 32;
/*  64 */     func_147499_a(RenderAuraNode.texture);
/*  65 */     UtilsFX.renderFacingQuad(tile.func_174877_v().func_177958_n() + 0.5D, tile.func_174877_v().func_177956_o() + scale1, tile.func_174877_v().func_177952_p() + 0.5D, 32, 32, 192 + i, scale1, 11197951, scale1, 1, pt);
/*     */     
/*  67 */     GL11.glEnable(2884);
/*  68 */     GL11.glDepthMask(true);
/*  69 */     GL11.glPopMatrix();
/*     */     
/*  71 */     GL11.glPushMatrix();
/*  72 */     GL11.glTranslated(x + 0.5D - scale4 / 8.0F, y + scale1 - scale4 / 6.0F, z + 0.5D - scale4 / 8.0F);
/*  73 */     GL11.glScaled(scale4 / 4.0F, scale4 / 3.0F, scale4 / 4.0F);
/*  74 */     func_147499_a(tx1);
/*  75 */     this.model.render();
/*  76 */     GL11.glPopMatrix();
/*     */     
/*  78 */     GL11.glDisable(3042);
/*     */     
/*  80 */     float r1 = MathHelper.func_76126_a((((TileEtherealBloom)tile).counter + pt) / 12.0F) * 2.0F;
/*  81 */     float r2 = MathHelper.func_76126_a((((TileEtherealBloom)tile).counter + pt) / 11.0F) * 2.0F;
/*     */     
/*  83 */     GL11.glPushMatrix();
/*  84 */     GL11.glTranslated(x + 0.5D, y + 0.25D, z + 0.5D);
/*  85 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  86 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  87 */     for (int a = 0; a < 4; a++) {
/*  88 */       GL11.glPushMatrix();
/*  89 */       GL11.glScaled(scale3, scale1, scale3);
/*  90 */       GL11.glRotatef(90 * a, 1.0F, 0.0F, 0.0F);
/*  91 */       GL11.glRotatef(r1, 0.0F, 1.0F, 0.0F);
/*  92 */       GL11.glRotatef(r2, 0.0F, 0.0F, 1.0F);
/*  93 */       UtilsFX.renderQuadCentered(tx2, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*  94 */       GL11.glPopMatrix();
/*     */     }
/*  96 */     GL11.glPopMatrix();
/*     */     
/*  98 */     GL11.glPushMatrix();
/*  99 */     GL11.glTranslated(x + 0.5D, y + 0.6D, z + 0.5D);
/* 100 */     GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 101 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 102 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 103 */     for (int a = 0; a < 4; a++) {
/* 104 */       GL11.glPushMatrix();
/* 105 */       GL11.glScaled(scale4, scale1 * 0.7F, scale4);
/* 106 */       GL11.glRotatef(90 * a, 1.0F, 0.0F, 0.0F);
/* 107 */       GL11.glRotatef(r2, 0.0F, 1.0F, 0.0F);
/* 108 */       GL11.glRotatef(r1, 0.0F, 0.0F, 1.0F);
/* 109 */       UtilsFX.renderQuadCentered(tx2, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/* 110 */       GL11.glPopMatrix();
/*     */     }
/* 112 */     GL11.glPopMatrix();
/*     */     
/* 114 */     GL11.glPushMatrix();
/* 115 */     GL11.glTranslated(x + 0.5D, y, z + 0.5D);
/* 116 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 117 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 118 */     for (int a = 0; a < 4; a++) {
/* 119 */       GL11.glPushMatrix();
/* 120 */       GL11.glTranslated(scale1 / 2.0F, 0.0D, 0.0D);
/* 121 */       GL11.glScaled(scale1, scale2, scale2);
/* 122 */       GL11.glRotatef(90 * a, 1.0F, 0.0F, 0.0F);
/* 123 */       UtilsFX.renderQuadCentered(tx3, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/* 124 */       GL11.glPopMatrix();
/*     */     }
/* 126 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/* 129 */     GL11.glAlphaFunc(516, 0.1F);
/* 130 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileEtherealBloomRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */