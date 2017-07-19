/*     */ package thaumcraft.client.gui.plugins;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class GuiHoverButton extends GuiButton
/*     */ {
/*     */   String description;
/*     */   GuiScreen screen;
/*     */   int color;
/*     */   
/*     */   public GuiHoverButton(GuiScreen screen, int buttonId, int x, int y, int width, int height, String buttonText, String description, Object tex)
/*     */   {
/*  24 */     super(buttonId, x, y, width, height, buttonText);
/*  25 */     this.description = description;
/*  26 */     this.tex = tex;
/*  27 */     this.screen = screen;
/*  28 */     this.color = 16777215;
/*     */   }
/*     */   
/*     */   public GuiHoverButton(GuiScreen screen, int buttonId, int x, int y, int width, int height, String buttonText, String description, Object tex, int color) {
/*  32 */     super(buttonId, x, y, width, height, buttonText);
/*  33 */     this.description = description;
/*  34 */     this.tex = tex;
/*  35 */     this.screen = screen;
/*  36 */     this.color = color;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  42 */   Object tex = null;
/*     */   
/*     */ 
/*     */   public void func_146112_a(Minecraft mc, int xx, int yy)
/*     */   {
/*  47 */     if (this.field_146125_m)
/*     */     {
/*  49 */       FontRenderer fontrenderer = mc.field_71466_p;
/*  50 */       Color c = new Color(this.color);
/*  51 */       GlStateManager.func_179131_c(0.9F * (c.getRed() / 255.0F), 0.9F * (c.getGreen() / 255.0F), 0.9F * (c.getBlue() / 255.0F), 0.9F);
/*  52 */       this.field_146123_n = ((xx >= this.field_146128_h - this.field_146120_f / 2) && (yy >= this.field_146129_i - this.field_146121_g / 2) && (xx < this.field_146128_h - this.field_146120_f / 2 + this.field_146120_f) && (yy < this.field_146129_i - this.field_146121_g / 2 + this.field_146121_g));
/*  53 */       int k = func_146114_a(this.field_146123_n);
/*     */       
/*  55 */       GlStateManager.func_179147_l();
/*  56 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/*  57 */       GlStateManager.func_179112_b(770, 771);
/*     */       
/*  59 */       if (k == 2) {
/*  60 */         GlStateManager.func_179131_c(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/*     */       }
/*     */       
/*  63 */       if ((this.tex instanceof Aspect)) {
/*  64 */         mc.func_110434_K().func_110577_a(((Aspect)this.tex).getImage());
/*  65 */         Color c2 = new Color(((Aspect)this.tex).getColor());
/*  66 */         if (k != 2) {
/*  67 */           GlStateManager.func_179131_c(c2.getRed() / 290.0F, c2.getGreen() / 290.0F, c2.getBlue() / 290.0F, 0.9F);
/*     */         } else
/*  69 */           GlStateManager.func_179131_c(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, 1.0F);
/*  70 */         func_146110_a(this.field_146128_h - this.field_146120_f / 2, this.field_146129_i - this.field_146121_g / 2, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */       }
/*     */       
/*  73 */       if ((this.tex instanceof ResourceLocation)) {
/*  74 */         mc.func_110434_K().func_110577_a((ResourceLocation)this.tex);
/*  75 */         func_146110_a(this.field_146128_h - this.field_146120_f / 2, this.field_146129_i - this.field_146121_g / 2, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */       }
/*     */       
/*  78 */       if ((this.tex instanceof TextureAtlasSprite)) {
/*  79 */         func_175175_a(this.field_146128_h - this.field_146120_f / 2, this.field_146129_i - this.field_146121_g / 2, (TextureAtlasSprite)this.tex, 16, 16);
/*     */       }
/*     */       
/*  82 */       if ((this.tex instanceof ItemStack)) {
/*  83 */         this.field_73735_i -= 90.0F;
/*  84 */         UtilsFX.renderItemStackShaded(mc, (ItemStack)this.tex, this.field_146128_h - this.field_146120_f / 2, this.field_146129_i - this.field_146121_g / 2, null, k == 2 ? 1.0F : 0.9F);
/*  85 */         this.field_73735_i += 90.0F;
/*     */       }
/*     */       
/*  88 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*  89 */       func_146119_b(mc, xx, yy);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_146111_b(int xx, int yy)
/*     */   {
/*  97 */     FontRenderer fontrenderer = Minecraft.func_71410_x().field_71466_p;
/*  98 */     this.field_73735_i += 90.0F;
/*  99 */     ArrayList<String> text = new ArrayList();
/* 100 */     text.add(this.field_146126_j);
/* 101 */     int m = 8;
/* 102 */     if (this.description != null) {
/* 103 */       m = 0;
/* 104 */       text.add("§o§9" + this.description);
/*     */     }
/* 106 */     UtilsFX.drawCustomTooltip(this.screen, fontrenderer, text, xx + 4, yy + m, -99);
/* 107 */     this.field_73735_i -= 90.0F;
/* 108 */     net.minecraft.client.renderer.RenderHelper.func_74518_a();
/* 109 */     GlStateManager.func_179140_f();
/* 110 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY)
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/plugins/GuiHoverButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */