/*    */ package thaumcraft.common.entities.construct.golem.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiGolemCraftButton
/*    */   extends GuiButton
/*    */ {
/*    */   public GuiGolemCraftButton(int buttonId, int x, int y)
/*    */   {
/* 15 */     super(buttonId, x, y, 24, 16, "");
/*    */   }
/*    */   
/* 18 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_golembuilder.png");
/*    */   
/*    */ 
/*    */   public void func_146112_a(Minecraft mc, int xx, int yy)
/*    */   {
/* 23 */     if (this.field_146125_m)
/*    */     {
/* 25 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 26 */       mc.func_110434_K().func_110577_a(tex);
/* 27 */       GlStateManager.func_179131_c(0.9F, 0.9F, 0.9F, 0.9F);
/* 28 */       this.field_146123_n = ((xx >= this.field_146128_h) && (yy >= this.field_146129_i) && (xx < this.field_146128_h + this.field_146120_f) && (yy < this.field_146129_i + this.field_146121_g));
/* 29 */       int k = func_146114_a(this.field_146123_n);
/*    */       
/* 31 */       GlStateManager.func_179147_l();
/* 32 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 33 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 35 */       if ((this.field_146124_l) && (k == 2)) {
/* 36 */         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       }
/*    */       
/* 39 */       func_73729_b(this.field_146128_h, this.field_146129_i, 216, 64, 24, 16);
/*    */       
/* 41 */       if (!this.field_146124_l) {
/* 42 */         func_73729_b(this.field_146128_h, this.field_146129_i, 216, 40, 24, 16);
/*    */       }
/*    */       
/* 45 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       
/* 47 */       func_146119_b(mc, xx, yy);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/gui/GuiGolemCraftButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */