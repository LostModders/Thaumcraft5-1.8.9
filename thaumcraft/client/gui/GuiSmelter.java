/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.container.ContainerSmelter;
/*    */ import thaumcraft.common.tiles.crafting.TileSmelter;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiSmelter
/*    */   extends GuiContainer
/*    */ {
/*    */   private TileSmelter furnaceInventory;
/*    */   
/*    */   public GuiSmelter(InventoryPlayer par1InventoryPlayer, TileSmelter par2TileEntityFurnace)
/*    */   {
/* 22 */     super(new ContainerSmelter(par1InventoryPlayer, par2TileEntityFurnace));
/* 23 */     this.furnaceInventory = par2TileEntityFurnace;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 33 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_smelter.png");
/*    */   
/*    */ 
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 40 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 41 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 42 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 43 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*    */     
/* 45 */     GL11.glEnable(3042);
/* 46 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/*    */ 
/* 49 */     if (this.furnaceInventory.getBurnTimeRemainingScaled(20) > 0)
/*    */     {
/* 51 */       int i1 = this.furnaceInventory.getBurnTimeRemainingScaled(20);
/* 52 */       func_73729_b(k + 80, l + 26 + 20 - i1, 176, 20 - i1, 16, i1);
/*    */     }
/*    */     
/* 55 */     int i1 = this.furnaceInventory.getCookProgressScaled(46);
/* 56 */     func_73729_b(k + 106, l + 13 + 46 - i1, 216, 46 - i1, 9, i1);
/*    */     
/* 58 */     i1 = this.furnaceInventory.getVisScaled(48);
/* 59 */     func_73729_b(k + 61, l + 12 + 48 - i1, 200, 48 - i1, 8, i1);
/*    */     
/*    */ 
/*    */ 
/* 63 */     func_73729_b(k + 60, l + 8, 232, 0, 10, 55);
/* 64 */     GL11.glDisable(3042);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiSmelter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */