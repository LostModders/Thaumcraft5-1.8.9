/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.items.IScribeTools;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.block.ModelResearchTable;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ import thaumcraft.common.lib.research.ResearchNoteData;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileResearchTableRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 31 */   private ModelResearchTable tableModel = new ModelResearchTable();
/*    */   
/* 33 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/blocks/research_table_model.png");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileResearchTable table, double par2, double par4, double par6, float par8)
/*    */   {
/* 41 */     GL11.glPushMatrix();
/* 42 */     func_147499_a(TEX);
/* 43 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/* 44 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 45 */     switch (thaumcraft.common.lib.utils.BlockStateUtils.getFacing(table.func_145832_p())) {
/* 46 */     case EAST:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 47 */     case WEST:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 48 */     case SOUTH:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 51 */     if ((table.func_70301_a(1) != null) && (table.func_70301_a(1).func_77973_b() == ItemsTC.researchNotes)) {
/* 52 */       ResearchNoteData rd = ResearchManager.getData(table.func_70301_a(1));
/* 53 */       int color = 10066329;
/* 54 */       if (rd != null) color = rd.color;
/* 55 */       this.tableModel.renderScroll(color);
/*    */     }
/*    */     
/* 58 */     if ((table.func_70301_a(0) != null) && ((table.func_70301_a(0).func_77973_b() instanceof IScribeTools))) {
/* 59 */       this.tableModel.renderInkwell();
/* 60 */       GL11.glPushMatrix();
/* 61 */       GL11.glEnable(3042);
/* 62 */       GL11.glBlendFunc(770, 771);
/* 63 */       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 64 */       GL11.glTranslated(-0.5D, 0.1D, 0.125D);
/* 65 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 66 */       GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 67 */       UtilsFX.renderItemIn2D("thaumcraft:blocks/tablequill", 0.0625F);
/* 68 */       GL11.glDisable(3042);
/* 69 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 72 */     GL11.glPopMatrix();
/*    */     
/* 74 */     GL11.glPushMatrix();
/*    */     
/*    */ 
/* 77 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int q)
/*    */   {
/* 83 */     renderTileEntityAt((TileResearchTable)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileResearchTableRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */