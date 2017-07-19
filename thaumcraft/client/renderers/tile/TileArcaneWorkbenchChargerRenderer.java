/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*    */ import thaumcraft.client.lib.models.IModelCustom;
/*    */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbenchCharger;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileArcaneWorkbenchChargerRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private IModelCustom model;
/* 22 */   private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "models/obj/vis_relay.obj");
/* 23 */   private static final ResourceLocation RELAY_TEX = new ResourceLocation("thaumcraft", "textures/models/vis_relay.png");
/*    */   
/* 25 */   public TileArcaneWorkbenchChargerRenderer() { this.model = AdvancedModelLoader.loadModel(RELAY); }
/*    */   
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileArcaneWorkbenchCharger tile, double par2, double par4, double par6, float par8)
/*    */   {
/* 31 */     int ticks = Minecraft.func_71410_x().func_175606_aa().field_70173_aa;
/*    */     
/* 33 */     GL11.glPushMatrix();
/* 34 */     GL11.glTranslated(par2 + 0.5D, par4 + 0.5D, par6 + 0.5D);
/* 35 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 36 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 37 */     GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
/* 38 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 39 */     GL11.glPushMatrix();
/* 40 */     func_147499_a(RELAY_TEX);
/*    */     
/* 42 */     this.model.renderPart("RingFloat");
/*    */     
/* 44 */     GL11.glPushMatrix();
/* 45 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 46 */     GL11.glTranslated(0.0D, 0.0D, 0.5D);
/* 47 */     for (int a = 0; a < 4; a++) {
/* 48 */       this.model.renderPart("Support");
/* 49 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*    */     }
/* 51 */     GL11.glPopMatrix();
/*    */     
/* 53 */     GL11.glEnable(3042);
/* 54 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 56 */     float scale = MathHelper.func_76126_a((ticks + par8) / 2.0F) * 0.05F + 0.95F;
/* 57 */     int j = (int)(50.0F + 150.0F * scale);
/* 58 */     int k = j % 65536;
/* 59 */     int l = j / 65536;
/* 60 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 61 */     this.model.renderPart("Crystal");
/*    */     
/* 63 */     GL11.glDisable(3042);
/* 64 */     GL11.glPopMatrix();
/* 65 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int p_180535_9_)
/*    */   {
/* 72 */     renderTileEntityAt((TileArcaneWorkbenchCharger)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileArcaneWorkbenchChargerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */