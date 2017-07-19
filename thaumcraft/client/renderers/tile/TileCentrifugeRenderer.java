/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.block.ModelCentrifuge;
/*    */ import thaumcraft.common.tiles.essentia.TileCentrifuge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileCentrifugeRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelCentrifuge model;
/* 21 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/models/centrifuge.png");
/*    */   
/*    */   public TileCentrifugeRenderer()
/*    */   {
/* 25 */     this.model = new ModelCentrifuge();
/*    */   }
/*    */   
/*    */   public void renderEntityAt(TileCentrifuge cf, double x, double y, double z, float fq)
/*    */   {
/* 30 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*    */     
/* 32 */     func_147499_a(TEX);
/*    */     
/* 34 */     GL11.glPushMatrix();
/*    */     
/* 36 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/* 37 */     this.model.renderBoxes();
/* 38 */     GL11.glRotated(cf.rotation, 0.0D, 1.0D, 0.0D);
/* 39 */     this.model.renderSpinnyBit();
/*    */     
/* 41 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity tileentity, double d, double d1, double d2, float f, int q)
/*    */   {
/* 49 */     renderEntityAt((TileCentrifuge)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileCentrifugeRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */