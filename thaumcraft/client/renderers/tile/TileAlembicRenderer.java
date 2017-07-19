/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.IEssentiaTransport;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.block.ModelBoreBase;
/*    */ import thaumcraft.common.tiles.crafting.TileAlembic;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileAlembicRenderer extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelBoreBase modelBore;
/*    */   
/*    */   public TileAlembicRenderer()
/*    */   {
/* 23 */     this.modelBore = new ModelBoreBase();
/*    */   }
/*    */   
/*    */ 
/* 27 */   private static final ResourceLocation TEX_LABEL = new ResourceLocation("thaumcraft", "textures/models/label.png");
/* 28 */   private static final ResourceLocation TEX_BORE = new ResourceLocation("thaumcraft", "textures/models/bore.png");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileAlembic tile, double x, double y, double z, float f)
/*    */   {
/* 35 */     if (tile.aspectFilter != null) {
/* 36 */       GL11.glPushMatrix();
/* 37 */       GL11.glBlendFunc(770, 771);
/* 38 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 39 */       GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/* 40 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 41 */       switch (tile.facing) {
/* 42 */       case 5:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 43 */       case 3:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 44 */       case 2:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*    */       }
/*    */       
/* 47 */       GL11.glPushMatrix();
/* 48 */       GL11.glTranslatef(0.0F, 0.5F, -0.376F);
/* 49 */       UtilsFX.renderQuadCentered(TEX_LABEL, 0.44F, 1.0F, 1.0F, 1.0F, -99, 771, 1.0F);
/* 50 */       GL11.glPopMatrix();
/*    */       
/* 52 */       GL11.glPushMatrix();
/* 53 */       GL11.glTranslatef(0.0F, 0.5F, -0.377F);
/* 54 */       GL11.glScaled(0.02D, 0.02D, 0.02D);
/* 55 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */       
/* 57 */       UtilsFX.drawTag(-8, -8, tile.aspectFilter);
/* 58 */       GL11.glPopMatrix();
/*    */       
/* 60 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 63 */     if (tile.func_145831_w() != null) {
/* 64 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 65 */       func_147499_a(TEX_BORE);
/* 66 */       for (EnumFacing dir : EnumFacing.field_176754_o) {
/* 67 */         if (tile.canOutputTo(dir)) {
/* 68 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(tile.func_145831_w(), tile.func_174877_v(), dir);
/* 69 */           if ((te != null) && (((IEssentiaTransport)te).canInputFrom(dir.func_176734_d()))) {
/* 70 */             GL11.glPushMatrix();
/* 71 */             GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/* 72 */             switch (dir.ordinal()) {
/* 73 */             case 0:  GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
/* 74 */               GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F); break;
/* 75 */             case 1:  GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 76 */               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/* 77 */             case 2:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 78 */             case 3:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 79 */             case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 80 */             case 5:  GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*    */             }
/* 82 */             this.modelBore.renderNozzle();
/* 83 */             GL11.glPopMatrix();
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int q)
/*    */   {
/* 94 */     renderTileEntityAt((TileAlembic)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileAlembicRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */