/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.block.ModelBanner;
/*    */ import thaumcraft.common.tiles.misc.TileBanner;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileBannerRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 23 */   private ModelBanner model = new ModelBanner();
/* 24 */   private static final ResourceLocation TEX_CULT = new ResourceLocation("thaumcraft", "textures/models/banner_cultist.png");
/*    */   
/* 26 */   private static final ResourceLocation TEX_BLANK = new ResourceLocation("thaumcraft", "textures/models/banner_blank.png");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileBanner banner, double par2, double par4, double par6, float par8)
/*    */   {
/* 34 */     boolean flag = banner.func_145831_w() != null;
/* 35 */     long k = flag ? banner.func_145831_w().func_82737_E() : 0L;
/*    */     
/* 37 */     GL11.glPushMatrix();
/* 38 */     if ((banner.getAspect() == null) && (banner.getColor() == -1)) {
/* 39 */       func_147499_a(TEX_CULT);
/*    */     } else {
/* 41 */       func_147499_a(TEX_BLANK);
/*    */     }
/* 43 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.5F, (float)par6 + 0.5F);
/* 44 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 45 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 47 */     if (banner.func_145831_w() != null) {
/* 48 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 49 */       float f2 = banner.getBannerFacing() * 360 / 16.0F;
/* 50 */       GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 53 */     if (!banner.getWall()) {
/* 54 */       this.model.renderPole();
/*    */     } else {
/* 56 */       GL11.glTranslated(0.0D, 1.0D, -0.4125D);
/*    */     }
/*    */     
/* 59 */     this.model.renderBeam();
/*    */     
/* 61 */     if (banner.getColor() != -1) {
/* 62 */       Color c = new Color(EnumDyeColor.func_176766_a(banner.getColor()).func_176768_e().field_76291_p);
/* 63 */       GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/*    */     }
/*    */     
/* 66 */     this.model.renderTabs();
/*    */     
/* 68 */     float f3 = banner.func_174877_v().func_177958_n() * 7 + banner.func_174877_v().func_177956_o() * 9 + banner.func_174877_v().func_177952_p() * 13 + (float)k + par8;
/* 69 */     float rx = (0.005F + 0.005F * MathHelper.func_76134_b(f3 * 3.1415927F * 0.02F)) * 3.1415927F;
/* 70 */     this.model.Banner.field_78795_f = rx;
/* 71 */     this.model.renderBanner();
/*    */     
/* 73 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 75 */     if (banner.getAspect() != null) {
/* 76 */       GL11.glPushMatrix();
/* 77 */       GL11.glTranslatef(0.0F, 0.0F, 0.05001F);
/* 78 */       GL11.glScaled(0.0375D, 0.0375D, 0.0375D);
/* 79 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 80 */       GL11.glRotatef(-rx * 57.295776F * 2.0F, 1.0F, 0.0F, 0.0F);
/*    */       
/* 82 */       UtilsFX.drawTag(-8, 4, banner.getAspect(), 0.0F, 0, 0.0D, 771, 0.75F, false);
/* 83 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/*    */ 
/* 87 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int p_180535_9_)
/*    */   {
/* 94 */     renderTileEntityAt((TileBanner)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileBannerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */