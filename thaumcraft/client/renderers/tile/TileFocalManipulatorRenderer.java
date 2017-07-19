/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileFocalManipulatorRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 22 */   EntityItem entityitem = null;
/*    */   
/*    */ 
/*    */   public void renderTileEntityAt(TileFocalManipulator table, double par2, double par4, double par6, float par8)
/*    */   {
/* 27 */     if ((table.func_145831_w() != null) && (table.func_70301_a(0) != null) && ((table.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic)))
/*    */     {
/* 29 */       float ticks = Minecraft.func_71410_x().func_175606_aa().field_70173_aa + par8;
/* 30 */       GL11.glPushMatrix();
/* 31 */       GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/* 32 */       GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
/* 33 */       ItemStack is = table.func_70301_a(0).func_77946_l();
/* 34 */       this.entityitem = new EntityItem(table.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/* 35 */       this.entityitem.field_70290_d = (MathHelper.func_76126_a(ticks / 14.0F) * 0.2F + 0.2F);
/* 36 */       RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
/* 37 */       rendermanager.func_147940_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 38 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int p)
/*    */   {
/* 45 */     renderTileEntityAt((TileFocalManipulator)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileFocalManipulatorRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */