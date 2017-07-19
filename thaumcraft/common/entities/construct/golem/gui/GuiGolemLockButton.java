/*    */ package thaumcraft.common.entities.construct.golem.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.api.golems.seals.ISealEntity;
/*    */ 
/*    */ public class GuiGolemLockButton extends GuiButton
/*    */ {
/*    */   ISealEntity seal;
/*    */   
/*    */   public GuiGolemLockButton(int buttonId, int x, int y, int width, int height, ISealEntity seal)
/*    */   {
/* 18 */     super(buttonId, x, y, width, height, "");
/* 19 */     this.seal = seal;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 24 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int xx, int yy)
/*    */   {
/* 28 */     if (this.field_146125_m)
/*    */     {
/* 30 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 31 */       mc.func_110434_K().func_110577_a(tex);
/* 32 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 33 */       this.field_146123_n = ((xx >= this.field_146128_h) && (yy >= this.field_146129_i) && (xx < this.field_146128_h + this.field_146120_f) && (yy < this.field_146129_i + this.field_146121_g));
/*    */       
/* 35 */       int k = func_146114_a(this.field_146123_n);
/* 36 */       if (k == 2) {
/* 37 */         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       } else {
/* 39 */         GlStateManager.func_179131_c(0.9F, 0.9F, 0.9F, 0.9F);
/*    */       }
/* 41 */       GlStateManager.func_179147_l();
/* 42 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 43 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 45 */       if (this.seal.isLocked()) {
/* 46 */         func_73729_b(this.field_146128_h, this.field_146129_i, 32, 136, 16, 16);
/*    */       } else {
/* 48 */         func_73729_b(this.field_146128_h, this.field_146129_i, 48, 136, 16, 16);
/*    */       }
/*    */       
/* 51 */       if (k == 2) {
/* 52 */         String s = this.seal.isLocked() ? StatCollector.func_74838_a("golem.prop.lock") : StatCollector.func_74838_a("golem.prop.unlock");
/* 53 */         func_73732_a(fontrenderer, s, this.field_146128_h + 8, this.field_146129_i + 17, 16777215);
/*    */       }
/* 55 */       func_146119_b(mc, xx, yy);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/gui/GuiGolemLockButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */