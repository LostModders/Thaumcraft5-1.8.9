/*    */ package thaumcraft.client.gui.plugins;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import thaumcraft.client.gui.GuiTurretFocus;
/*    */ 
/*    */ public class GuiToggleButton extends GuiButton
/*    */ {
/*    */   Runnable runnable;
/*    */   public static boolean toggled;
/*    */   
/*    */   public GuiToggleButton(int buttonId, int x, int y, int width, int height, String buttonText, Runnable runnable)
/*    */   {
/* 16 */     super(buttonId, x, y, width, height, buttonText);
/* 17 */     this.runnable = runnable;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_146112_a(Minecraft mc, int xx, int yy)
/*    */   {
/* 25 */     if (this.field_146125_m)
/*    */     {
/* 27 */       this.runnable.run();
/*    */       
/* 29 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 30 */       mc.func_110434_K().func_110577_a(GuiTurretFocus.tex);
/* 31 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 32 */       this.field_146123_n = ((xx >= this.field_146128_h) && (yy >= this.field_146129_i) && (xx < this.field_146128_h + this.field_146120_f) && (yy < this.field_146129_i + this.field_146121_g));
/*    */       
/*    */ 
/* 35 */       GlStateManager.func_179147_l();
/* 36 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 37 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 39 */       func_73729_b(this.field_146128_h, this.field_146129_i, 192, 16, 8, 8);
/*    */       
/* 41 */       if (toggled) {
/* 42 */         func_73729_b(this.field_146128_h, this.field_146129_i, 192, 24, 8, 8);
/*    */       }
/*    */       
/* 45 */       func_73731_b(fontrenderer, net.minecraft.util.StatCollector.func_74838_a(this.field_146126_j), this.field_146128_h + 12, this.field_146129_i, 16777215);
/*    */       
/* 47 */       func_146119_b(mc, xx, yy);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/plugins/GuiToggleButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */