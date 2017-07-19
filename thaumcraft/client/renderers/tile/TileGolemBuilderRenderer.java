/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.block.BlockStaticLiquid;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockModelShapes;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*    */ import thaumcraft.client.lib.models.IModelCustom;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileGolemBuilderRenderer extends TileEntitySpecialRenderer
/*    */ {
/*    */   private IModelCustom model;
/* 27 */   private static final ResourceLocation TM = new ResourceLocation("thaumcraft", "models/obj/golembuilder.obj");
/* 28 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/models/golems/golembuilder.png");
/*    */   
/* 30 */   public TileGolemBuilderRenderer() { this.model = AdvancedModelLoader.loadModel(TM); }
/*    */   
/*    */ 
/* 33 */   EntityItem entityitem = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileGolemBuilder tile, double par2, double par4, double par6, float pt)
/*    */   {
/* 41 */     GL11.glPushMatrix();
/* 42 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/* 43 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 45 */     func_147499_a(TEX);
/*    */     
/* 47 */     EnumFacing facing = BlockStateUtils.getFacing(tile.func_145832_p());
/* 48 */     if (tile.func_145831_w() != null) {
/* 49 */       switch (facing.ordinal()) {
/* 50 */       case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 51 */       case 3:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 52 */       case 2:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */       }
/*    */       
/*    */     }
/* 56 */     this.model.renderAllExcept(new String[] { "press" });
/*    */     
/* 58 */     GL11.glPushMatrix();
/* 59 */     float h = tile.press;
/* 60 */     double s = Math.sin(Math.toRadians(h)) * 0.625D;
/* 61 */     GL11.glTranslated(0.0D, -s, 0.0D);
/* 62 */     this.model.renderPart("press");
/* 63 */     GL11.glPopMatrix();
/*    */     
/* 65 */     GL11.glTranslatef(-0.3125F, 0.625F, 1.3125F);
/* 66 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 67 */     TextureAtlasSprite icon = Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178122_a(Blocks.field_150353_l.func_176223_P());
/* 68 */     UtilsFX.renderQuadFromIcon(icon, 0.625F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*    */     
/* 70 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int q)
/*    */   {
/* 77 */     renderTileEntityAt((TileGolemBuilder)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileGolemBuilderRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */