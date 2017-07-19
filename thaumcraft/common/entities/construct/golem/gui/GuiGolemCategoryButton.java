/*    */ package thaumcraft.common.entities.construct.golem.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ class GuiGolemCategoryButton extends net.minecraft.client.gui.GuiButton
/*    */ {
/*    */   int icon;
/*    */   boolean active;
/*    */   
/*    */   public GuiGolemCategoryButton(int buttonId, int x, int y, int width, int height, String buttonText, int i, boolean act)
/*    */   {
/* 15 */     super(buttonId, x, y, width, height, buttonText);
/* 16 */     this.icon = i;
/* 17 */     this.active = act;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 23 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*    */   
/*    */ 
/*    */   public void func_146112_a(Minecraft mc, int xx, int yy)
/*    */   {
/* 28 */     if (this.field_146125_m)
/*    */     {
/* 30 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 31 */       mc.func_110434_K().func_110577_a(tex);
/* 32 */       GlStateManager.func_179131_c(0.9F, 0.9F, 0.9F, 0.9F);
/* 33 */       this.field_146123_n = ((xx >= this.field_146128_h - 8) && (yy >= this.field_146129_i - 8) && (xx < this.field_146128_h - 8 + this.field_146120_f) && (yy < this.field_146129_i - 8 + this.field_146121_g));
/* 34 */       int k = func_146114_a(this.field_146123_n);
/*    */       
/* 36 */       GlStateManager.func_179147_l();
/* 37 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 38 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 40 */       if (this.active) {
/* 41 */         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       }
/* 43 */       else if (k != 2) {
/* 44 */         GlStateManager.func_179131_c(0.7F, 0.7F, 0.7F, 0.7F);
/*    */       }
/*    */       
/* 47 */       func_73729_b(this.field_146128_h - 8, this.field_146129_i - 8, this.icon * 16, 120, 16, 16);
/*    */       
/* 49 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       
/* 51 */       if (k == 2) {
/* 52 */         this.field_73735_i += 90.0F;
/* 53 */         String s = net.minecraft.util.StatCollector.func_74838_a(this.field_146126_j);
/* 54 */         func_73731_b(fontrenderer, s, this.field_146128_h - 10 - fontrenderer.func_78256_a(s), this.field_146129_i - 4, 16777215);
/* 55 */         this.field_73735_i -= 90.0F;
/*    */       }
/*    */       
/* 58 */       func_146119_b(mc, xx, yy);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY)
/*    */   {
/* 66 */     return (this.field_146124_l) && (this.field_146125_m) && (mouseX >= this.field_146128_h - 8) && (mouseY >= this.field_146129_i - 8) && (mouseX < this.field_146128_h - 8 + this.field_146120_f) && (mouseY < this.field_146129_i - 8 + this.field_146121_g);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/gui/GuiGolemCategoryButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */