/*    */ package thaumcraft.client.gui.plugins;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiScrollButton extends GuiButton
/*    */ {
/*    */   public GuiScrollButton(int buttonId, int x, int y, int width, int height, boolean minus)
/*    */   {
/* 14 */     super(buttonId, x, y, width, height, "");
/* 15 */     this.minus = minus;
/*    */   }
/*    */   
/* 18 */   boolean minus = false;
/*    */   
/* 20 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*    */   
/*    */ 
/*    */   public void func_146112_a(Minecraft mc, int xx, int yy)
/*    */   {
/* 25 */     if (this.field_146125_m)
/*    */     {
/* 27 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 28 */       mc.func_110434_K().func_110577_a(tex);
/* 29 */       GlStateManager.func_179131_c(0.9F, 0.9F, 0.9F, 0.9F);
/*    */       
/* 31 */       this.field_146123_n = ((xx >= this.field_146128_h) && (yy >= this.field_146129_i) && (xx < this.field_146128_h + this.field_146120_f) && (yy < this.field_146129_i + this.field_146121_g));
/* 32 */       int k = func_146114_a(this.field_146123_n);
/* 33 */       if (k == 2) GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 34 */       GlStateManager.func_179147_l();
/* 35 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 36 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 38 */       func_73729_b(this.field_146128_h, this.field_146129_i, this.minus ? 20 : 30, 0, 10, 10);
/*    */       
/* 40 */       func_146119_b(mc, xx, yy);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/plugins/GuiScrollButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */