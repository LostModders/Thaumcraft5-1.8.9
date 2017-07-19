/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.container.ContainerHandMirror;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiHandMirror
/*    */   extends GuiContainer
/*    */ {
/* 18 */   int ci = 0;
/*    */   
/*    */   public GuiHandMirror(InventoryPlayer par1InventoryPlayer, World world, int x, int y, int z)
/*    */   {
/* 22 */     super(new ContainerHandMirror(par1InventoryPlayer, world, x, y, z));
/* 23 */     this.ci = par1InventoryPlayer.field_70461_c;
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
/* 35 */     return false;
/*    */   }
/*    */   
/* 38 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_handmirror.png");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 45 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 46 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 47 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 48 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 49 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/* 50 */     GL11.glEnable(3042);
/* 51 */     GL11.glBlendFunc(770, 771);
/* 52 */     func_73729_b(var5 + 8 + this.ci * 18, var6 + 142, 240, 0, 16, 16);
/* 53 */     GL11.glDisable(3042);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiHandMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */