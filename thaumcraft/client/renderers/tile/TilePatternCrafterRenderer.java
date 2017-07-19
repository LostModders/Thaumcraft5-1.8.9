/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.block.ModelBoreBase;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ import thaumcraft.common.tiles.devices.TilePatternCrafter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TilePatternCrafterRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelBoreBase model;
/*    */   
/*    */   public TilePatternCrafterRenderer()
/*    */   {
/* 30 */     this.model = new ModelBoreBase();
/*    */   }
/*    */   
/*    */ 
/* 34 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/blocks/pattern_crafter_modes.png");
/* 35 */   private static final ResourceLocation ICON = new ResourceLocation("thaumcraft", "items/brass_gear");
/*    */   
/*    */ 
/*    */   public void renderEntityAt(TilePatternCrafter pc, double x, double y, double z, float fq)
/*    */   {
/* 40 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 41 */     int f = 3;
/* 42 */     if (pc.func_145831_w() != null) {
/* 43 */       f = BlockStateUtils.getFacing(pc.func_145832_p()).ordinal();
/*    */     }
/*    */     
/* 46 */     GL11.glPushMatrix();
/*    */     
/* 48 */     GL11.glTranslatef((float)x + 0.5F, (float)y + 0.75F, (float)z + 0.5F);
/*    */     
/* 50 */     switch (f) {
/* 51 */     case 5:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 52 */     case 4:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 53 */     case 2:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 56 */     GL11.glPushMatrix();
/* 57 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 58 */     GL11.glTranslatef(0.0F, 0.0F, -0.5F);
/*    */     
/* 60 */     UtilsFX.renderQuadCentered(TEX, 10, 1, pc.type, 0.5F, 1.0F, 1.0F, 1.0F, pc.func_145838_q().func_176207_c(pc.func_145831_w(), pc.func_174877_v()), 771, 1.0F);
/*    */     
/* 62 */     GL11.glPopMatrix();
/*    */     
/* 64 */     GL11.glPushMatrix();
/* 65 */     GL11.glTranslatef(-0.2F, -0.40625F, 0.05F);
/* 66 */     GL11.glRotatef(-pc.rot % 360.0F, 0.0F, 0.0F, 1.0F);
/* 67 */     GL11.glScaled(0.5D, 0.5D, 1.0D);
/* 68 */     GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 69 */     UtilsFX.renderItemIn2D(ICON.toString(), 0.1F);
/* 70 */     GL11.glPopMatrix();
/*    */     
/* 72 */     GL11.glPushMatrix();
/* 73 */     GL11.glTranslatef(0.2F, -0.40625F, 0.05F);
/* 74 */     GL11.glRotatef(pc.rot % 360.0F, 0.0F, 0.0F, 1.0F);
/* 75 */     GL11.glScaled(0.5D, 0.5D, 1.0D);
/* 76 */     GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 77 */     UtilsFX.renderItemIn2D(ICON.toString(), 0.1F);
/* 78 */     GL11.glPopMatrix();
/*    */     
/* 80 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity tileentity, double d, double d1, double d2, float f, int ff)
/*    */   {
/* 87 */     renderEntityAt((TilePatternCrafter)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TilePatternCrafterRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */