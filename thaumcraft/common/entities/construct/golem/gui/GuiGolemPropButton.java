/*    */ package thaumcraft.common.entities.construct.golem.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*    */ 
/*    */ public class GuiGolemPropButton extends GuiButton
/*    */ {
/*    */   ISealConfigToggles.SealToggle prop;
/*    */   
/*    */   public GuiGolemPropButton(int buttonId, int x, int y, int width, int height, String buttonText, ISealConfigToggles.SealToggle prop)
/*    */   {
/* 17 */     super(buttonId, x, y, width, height, buttonText);
/* 18 */     this.prop = prop;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 23 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int xx, int yy)
/*    */   {
/* 27 */     if (this.field_146125_m)
/*    */     {
/* 29 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 30 */       mc.func_110434_K().func_110577_a(tex);
/* 31 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 32 */       this.field_146123_n = ((xx >= this.field_146128_h) && (yy >= this.field_146129_i) && (xx < this.field_146128_h + this.field_146120_f) && (yy < this.field_146129_i + this.field_146121_g));
/*    */       
/* 34 */       GlStateManager.func_179147_l();
/* 35 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 36 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 38 */       func_73729_b(this.field_146128_h - 2, this.field_146129_i - 2, 2, 18, 12, 12);
/*    */       
/* 40 */       if (this.prop.getValue()) {
/* 41 */         func_73729_b(this.field_146128_h - 2, this.field_146129_i - 2, 18, 18, 12, 12);
/*    */       }
/*    */       
/* 44 */       func_73731_b(fontrenderer, StatCollector.func_74838_a(this.field_146126_j), this.field_146128_h + 12, this.field_146129_i, 16777215);
/*    */       
/* 46 */       func_146119_b(mc, xx, yy);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/gui/GuiGolemPropButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */