/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.common.container.ContainerAuraTotem;
/*    */ import thaumcraft.common.tiles.devices.TileAuraTotem;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiAuraTotem
/*    */   extends GuiContainer
/*    */ {
/*    */   private TileAuraTotem inventory;
/*    */   
/*    */   public GuiAuraTotem(InventoryPlayer par1InventoryPlayer, TileAuraTotem te)
/*    */   {
/* 24 */     super(new ContainerAuraTotem(par1InventoryPlayer, te));
/* 25 */     this.inventory = te;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void drawGuiContainerForegroundLayer() {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected boolean func_146983_a(int par1)
/*    */   {
/* 37 */     return false;
/*    */   }
/*    */   
/* 40 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_auratotem.png");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 47 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 48 */     GL11.glEnable(3042);
/* 49 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 50 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 51 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 52 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/* 54 */     int i1 = this.inventory.getTimeScaled(48);
/* 55 */     if (this.inventory.getAspect() != null) {
/* 56 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 57 */       Color c = new Color(this.inventory.getAspect().getColor());
/* 58 */       GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/* 59 */       func_73729_b(var5 + 64, var6 + 52, 176, 48, i1, 8);
/* 60 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } else {
/* 62 */       func_73729_b(var5 + 64, var6 + 52, 176, 58, i1, 8);
/*    */     }
/*    */     
/* 65 */     func_73729_b(var5 + 60, var6 + 51, 176, 32, 56, 10);
/* 66 */     GL11.glDisable(3042);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiAuraTotem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */