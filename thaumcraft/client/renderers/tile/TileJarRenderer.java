/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.lib.RenderBlocks;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.block.ModelBrain;
/*     */ import thaumcraft.client.renderers.models.block.ModelJar;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.tiles.devices.TileJarBrain;
/*     */ import thaumcraft.common.tiles.essentia.TileJar;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileJarRenderer extends TileEntitySpecialRenderer
/*     */ {
/*  36 */   private ModelJar model = new ModelJar();
/*  37 */   private ModelBrain brain = new ModelBrain();
/*     */   
/*  39 */   private static final ResourceLocation TEX_LABEL = new ResourceLocation("thaumcraft", "textures/models/label.png");
/*  40 */   private static final ResourceLocation TEX_BRAIN = new ResourceLocation("thaumcraft", "textures/models/brain2.png");
/*  41 */   private static final ResourceLocation TEX_BRINE = new ResourceLocation("thaumcraft", "textures/models/jarbrine.png");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileJar tile, double x, double y, double z, float f)
/*     */   {
/*  48 */     GL11.glPushMatrix();
/*  49 */     GL11.glDisable(2884);
/*     */     
/*  51 */     GL11.glTranslatef((float)x + 0.5F, (float)y + 0.01F, (float)z + 0.5F);
/*  52 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/*  54 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  56 */     if ((tile instanceof TileJarBrain)) {
/*  57 */       renderBrain((TileJarBrain)tile, x, y, z, f);
/*     */     }
/*  59 */     else if ((tile instanceof TileJarFillable)) {
/*  60 */       GL11.glDisable(2896);
/*     */       
/*  62 */       if (((TileJarFillable)tile).blocked) {
/*  63 */         GL11.glPushMatrix();
/*  64 */         func_147499_a(TEX_BRINE);
/*  65 */         GL11.glScaled(1.001D, 1.001D, 1.001D);
/*  66 */         this.model.renderLidBrace();
/*  67 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/*  70 */       if (ThaumcraftApiHelper.getConnectableTile(tile.func_145831_w(), tile.func_174877_v(), EnumFacing.UP) != null) {
/*  71 */         GL11.glPushMatrix();
/*  72 */         func_147499_a(TEX_BRINE);
/*  73 */         GL11.glScaled(0.9D, 1.0D, 0.9D);
/*  74 */         this.model.renderLidExtension();
/*  75 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/*  78 */       if (((TileJarFillable)tile).aspectFilter != null) {
/*  79 */         GL11.glPushMatrix();
/*  80 */         GL11.glBlendFunc(770, 771);
/*  81 */         switch (((TileJarFillable)tile).facing) {
/*  82 */         case 3:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/*  83 */         case 5:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  84 */         case 4:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/*  87 */         float rot = (((TileJarFillable)tile).aspectFilter.getTag().hashCode() + tile.func_174877_v().func_177958_n() + ((TileJarFillable)tile).facing) % 4 - 2;
/*     */         
/*  89 */         GL11.glPushMatrix();
/*  90 */         GL11.glTranslatef(0.0F, -0.4F, 0.315F);
/*  91 */         if (Config.crooked) GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
/*  92 */         UtilsFX.renderQuadCentered(TEX_LABEL, 0.5F, 1.0F, 1.0F, 1.0F, -99, 771, 1.0F);
/*  93 */         GL11.glPopMatrix();
/*     */         
/*  95 */         GL11.glPushMatrix();
/*  96 */         GL11.glTranslatef(0.0F, -0.4F, 0.316F);
/*  97 */         if (Config.crooked) GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
/*  98 */         GL11.glScaled(0.021D, 0.021D, 0.021D);
/*  99 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */         
/* 101 */         UtilsFX.drawTag(-8, -8, ((TileJarFillable)tile).aspectFilter);
/* 102 */         GL11.glPopMatrix();
/*     */         
/* 104 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 107 */       if (((TileJarFillable)tile).amount > 0) {
/* 108 */         renderLiquid((TileJarFillable)tile, x, y, z, f);
/*     */       }
/* 110 */       GL11.glEnable(2896);
/*     */     }
/*     */     
/* 113 */     GL11.glEnable(2884);
/* 114 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderLiquid(TileJarFillable te, double x, double y, double z, float f)
/*     */   {
/* 120 */     GL11.glPushMatrix();
/* 121 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/* 123 */     World world = te.func_145831_w();
/* 124 */     RenderBlocks renderBlocks = new RenderBlocks();
/*     */     
/* 126 */     GL11.glDisable(2896);
/*     */     
/* 128 */     float level = te.amount / te.maxAmount * 0.625F;
/*     */     
/* 130 */     Tessellator t = Tessellator.func_178181_a();
/*     */     
/* 132 */     renderBlocks.setRenderBounds(0.25D, 0.0625D, 0.25D, 0.75D, 0.0625D + level, 0.75D);
/*     */     
/* 134 */     t.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181711_k);
/* 135 */     Color co = new Color(0);
/* 136 */     if (te.aspect != null) {
/* 137 */       co = new Color(te.aspect.getColor());
/*     */     }
/*     */     
/* 140 */     TextureAtlasSprite icon = Minecraft.func_71410_x().func_147117_R().func_110572_b("thaumcraft:blocks/animatedglow");
/*     */     
/* 142 */     func_147499_a(TextureMap.field_110575_b);
/*     */     
/* 144 */     renderBlocks.renderFaceYNeg(BlocksTC.jar, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 145 */     renderBlocks.renderFaceYPos(BlocksTC.jar, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 146 */     renderBlocks.renderFaceZNeg(BlocksTC.jar, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 147 */     renderBlocks.renderFaceZPos(BlocksTC.jar, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 148 */     renderBlocks.renderFaceXNeg(BlocksTC.jar, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/* 149 */     renderBlocks.renderFaceXPos(BlocksTC.jar, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
/*     */     
/* 151 */     t.func_78381_a();
/* 152 */     GL11.glEnable(2896);
/*     */     
/* 154 */     GL11.glPopMatrix();
/*     */     
/* 156 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderBrain(TileJarBrain te, double x, double y, double z, float f)
/*     */   {
/* 162 */     float bob = MathHelper.func_76126_a(Minecraft.func_71410_x().field_71439_g.field_70173_aa / 14.0F) * 0.03F + 0.03F;
/* 163 */     GL11.glPushMatrix();
/* 164 */     GL11.glTranslatef(0.0F, -0.8F + bob, 0.0F);
/*     */     
/* 166 */     for (float f2 = te.rota - te.rotb; f2 >= 3.141593F; f2 -= 6.283185F) {}
/* 167 */     while (f2 < -3.141593F) f2 += 6.283185F;
/* 168 */     float f3 = te.rotb + f2 * f;
/* 169 */     GL11.glRotatef(f3 * 180.0F / 3.141593F, 0.0F, 1.0F, 0.0F);
/* 170 */     GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 171 */     func_147499_a(TEX_BRAIN);
/* 172 */     GL11.glScalef(0.4F, 0.4F, 0.4F);
/* 173 */     this.brain.render();
/* 174 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 175 */     GL11.glPopMatrix();
/* 176 */     GL11.glPushMatrix();
/* 177 */     func_147499_a(TEX_BRINE);
/* 178 */     this.model.renderBrine();
/* 179 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int q)
/*     */   {
/* 185 */     renderTileEntityAt((TileJar)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileJarRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */