/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.block.ModelTubeValve;
/*    */ import thaumcraft.common.tiles.essentia.TileTubeValve;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileTubeValveRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelTubeValve model;
/* 18 */   private static final ResourceLocation TEX_VALVE = new ResourceLocation("thaumcraft", "textures/models/valve.png");
/*    */   
/*    */ 
/*    */   public TileTubeValveRenderer()
/*    */   {
/* 23 */     this.model = new ModelTubeValve();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void renderEntityAt(TileTubeValve valve, double x, double y, double z, float fq)
/*    */   {
/* 30 */     func_147499_a(TEX_VALVE);
/*    */     
/* 32 */     GL11.glPushMatrix();
/*    */     
/* 34 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*    */     
/* 36 */     if (valve.facing.func_96559_d() == 0) {
/* 37 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */     } else {
/* 39 */       GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 40 */       GL11.glRotatef(90.0F, valve.facing.func_96559_d(), 0.0F, 0.0F);
/*    */     }
/* 42 */     GL11.glRotatef(90.0F, valve.facing.func_82601_c(), valve.facing.func_96559_d(), valve.facing.func_82599_e());
/*    */     
/*    */ 
/* 45 */     GL11.glRotated(-valve.rotation * 1.5D, 0.0D, 1.0D, 0.0D);
/* 46 */     GL11.glTranslated(0.0D, -0.03F - valve.rotation / 360.0F * 0.09F, 0.0D);
/*    */     
/* 48 */     GL11.glPushMatrix();
/* 49 */     this.model.renderRing();
/* 50 */     GL11.glScaled(0.75D, 1.0D, 0.75D);
/* 51 */     this.model.renderRod();
/* 52 */     GL11.glPopMatrix();
/*    */     
/* 54 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity tileentity, double d, double d1, double d2, float f, int q)
/*    */   {
/* 62 */     renderEntityAt((TileTubeValve)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileTubeValveRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */