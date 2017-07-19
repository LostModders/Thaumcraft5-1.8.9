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
/*    */ import thaumcraft.common.tiles.devices.TileRechargePedestal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileRechargePedestalRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   public void renderTileEntityAt(TileRechargePedestal ped, double par2, double par4, double par6, float partialTicks)
/*    */   {
/* 27 */     if ((ped != null) && (ped.func_145831_w() != null) && (ped.func_70301_a(0) != null)) {
/* 28 */       EntityItem entityitem = null;
/* 29 */       float ticks = Minecraft.func_71410_x().func_175606_aa().field_70173_aa + partialTicks;
/* 30 */       GL11.glPushMatrix();
/* 31 */       float h = MathHelper.func_76126_a(ticks % 32767.0F / 16.0F) * 0.05F;
/* 32 */       GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.15F + h, (float)par6 + 0.5F);
/* 33 */       GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
/*    */       
/* 35 */       ItemStack is = ped.func_70301_a(0).func_77946_l();
/* 36 */       is.field_77994_a = 1;
/* 37 */       entityitem = new EntityItem(ped.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/* 38 */       entityitem.field_70290_d = 0.0F;
/* 39 */       RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
/* 40 */       rendermanager.func_147940_a(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 41 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int p_180535_9_)
/*    */   {
/* 50 */     renderTileEntityAt((TileRechargePedestal)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileRechargePedestalRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */