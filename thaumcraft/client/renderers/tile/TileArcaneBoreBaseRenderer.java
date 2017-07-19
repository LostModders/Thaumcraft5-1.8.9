/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.block.ModelBoreBase;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ import thaumcraft.common.tiles.devices.TileArcaneBoreBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileArcaneBoreBaseRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelBoreBase model;
/*    */   
/*    */   public TileArcaneBoreBaseRenderer()
/*    */   {
/* 24 */     this.model = new ModelBoreBase();
/*    */   }
/*    */   
/* 27 */   private static final ResourceLocation TEX_BORE = new ResourceLocation("thaumcraft", "textures/models/bore.png");
/*    */   
/*    */   public void renderEntityAt(TileArcaneBoreBase bore, double x, double y, double z, float fq)
/*    */   {
/* 31 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 32 */     int f = 2;
/* 33 */     if (bore.func_145831_w() != null) {
/* 34 */       f = BlockStateUtils.getFacing(bore.func_145832_p()).ordinal();
/*    */     }
/*    */     
/* 37 */     func_147499_a(TEX_BORE);
/*    */     
/* 39 */     GL11.glPushMatrix();
/*    */     
/* 41 */     GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/*    */     
/* 43 */     GL11.glPushMatrix();
/* 44 */     this.model.render();
/* 45 */     GL11.glPopMatrix();
/*    */     
/* 47 */     GL11.glPushMatrix();
/* 48 */     switch (f) {
/* 49 */     case 2:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 50 */     case 3:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 51 */     case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 52 */     case 5:  GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*    */     }
/* 54 */     this.model.renderNozzle();
/* 55 */     GL11.glPopMatrix();
/*    */     
/*    */ 
/* 58 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity tileentity, double d, double d1, double d2, float f, int ff)
/*    */   {
/* 65 */     renderEntityAt((TileArcaneBoreBase)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileArcaneBoreBaseRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */