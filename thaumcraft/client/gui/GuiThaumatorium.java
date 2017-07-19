/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.container.ContainerThaumatorium;
/*     */ import thaumcraft.common.tiles.devices.TileThaumatorium;
/*     */ 
/*     */ @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */ public class GuiThaumatorium extends GuiContainer
/*     */ {
/*     */   private TileThaumatorium inventory;
/*  31 */   private ContainerThaumatorium container = null;
/*  32 */   private int index = 0;
/*  33 */   private int lastSize = 0;
/*  34 */   private net.minecraft.entity.player.EntityPlayer player = null;
/*     */   
/*     */   public GuiThaumatorium(InventoryPlayer par1InventoryPlayer, TileThaumatorium par2TileEntityFurnace)
/*     */   {
/*  38 */     super(new ContainerThaumatorium(par1InventoryPlayer, par2TileEntityFurnace));
/*  39 */     this.inventory = par2TileEntityFurnace;
/*  40 */     this.container = ((ContainerThaumatorium)this.field_147002_h);
/*  41 */     this.container.updateRecipes();
/*  42 */     this.lastSize = this.container.recipes.size();
/*  43 */     this.player = par1InventoryPlayer.field_70458_d;
/*  44 */     refreshIndex();
/*     */   }
/*     */   
/*     */   void refreshIndex() {
/*  48 */     if ((this.inventory.recipeHash != null) && (this.container.recipes.size() > 0)) {
/*  49 */       for (int a = 0; a < this.container.recipes.size(); a++) {
/*  50 */         if (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(a)).hash))) {
/*  51 */           this.index = a;
/*  52 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  57 */     this.startAspect = 0;
/*     */   }
/*     */   
/*  60 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_thaumatorium.png");
/*     */   
/*     */ 
/*     */   protected void func_146976_a(float par1, int mx, int my)
/*     */   {
/*  65 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  66 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*  67 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*  68 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*  70 */     GL11.glEnable(3042);
/*  71 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/*     */ 
/*  74 */     if (this.index >= this.container.recipes.size()) {
/*  75 */       this.index = (this.container.recipes.size() - 1);
/*     */     }
/*     */     
/*  78 */     if (this.container.recipes.size() > 0) {
/*  79 */       if (this.lastSize != this.container.recipes.size()) {
/*  80 */         this.lastSize = this.container.recipes.size();
/*  81 */         refreshIndex();
/*     */       }
/*     */       
/*  84 */       if (this.index < 0) { this.index = 0;
/*     */       }
/*  86 */       if (this.container.recipes.size() > 1)
/*     */       {
/*  88 */         if (this.index > 0) {
/*  89 */           func_73729_b(k + 128, l + 16, 192, 16, 16, 8);
/*     */         } else {
/*  91 */           func_73729_b(k + 128, l + 16, 176, 16, 16, 8);
/*     */         }
/*     */         
/*  94 */         if (this.index < this.container.recipes.size() - 1) {
/*  95 */           func_73729_b(k + 128, l + 24, 192, 24, 16, 8);
/*     */         } else {
/*  97 */           func_73729_b(k + 128, l + 24, 176, 24, 16, 8);
/*     */         }
/*     */       }
/*     */       
/* 101 */       if (((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.size() > 6)
/*     */       {
/* 103 */         if (this.startAspect > 0) {
/* 104 */           func_73729_b(k + 32, l + 40, 192, 32, 8, 16);
/*     */         } else {
/* 106 */           func_73729_b(k + 32, l + 40, 176, 32, 8, 16);
/*     */         }
/*     */         
/* 109 */         if (this.startAspect < ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.size() - 1) {
/* 110 */           func_73729_b(k + 136, l + 40, 200, 32, 8, 16);
/*     */         } else {
/* 112 */           func_73729_b(k + 136, l + 40, 184, 32, 8, 16);
/*     */         }
/*     */       } else {
/* 115 */         this.startAspect = 0;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 120 */       if ((this.inventory.recipeHash != null) && (this.inventory.recipeHash.size() > 0))
/*     */       {
/* 122 */         int x = mx - (k + 112);
/* 123 */         int y = my - (l + 16);
/* 124 */         if (((x >= 0) && (y >= 0) && (x < 16) && (y < 16)) || (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(this.index)).hash))))
/*     */         {
/* 126 */           GL11.glPushMatrix();
/* 127 */           GL11.glEnable(3042);
/* 128 */           func_73729_b(k + 104, l + 8, 176, 96, 48, 48);
/* 129 */           GL11.glDisable(3042);
/* 130 */           GL11.glPopMatrix();
/*     */         }
/*     */         
/* 133 */         GL11.glPushMatrix();
/* 134 */         GL11.glEnable(3042);
/* 135 */         float alpha = 0.6F + MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 5.0F) * 0.4F + 0.4F;
/* 136 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/* 137 */         func_73729_b(k + 88, l + 16, 176, 56, 24, 24);
/* 138 */         GL11.glDisable(3042);
/* 139 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 142 */       drawAspects(k, l);
/*     */       
/* 144 */       drawOutput(k, l, mx, my);
/*     */       
/* 146 */       if (this.inventory.maxRecipes > 1) {
/* 147 */         GL11.glPushMatrix();
/* 148 */         GL11.glTranslatef(k + 136, l + 33, 0.0F);
/* 149 */         GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 150 */         String text = this.inventory.recipeHash.size() + "/" + this.inventory.maxRecipes;
/*     */         
/* 152 */         int ll = this.field_146289_q.func_78256_a(text) / 2;
/* 153 */         this.field_146289_q.func_78276_b(text, -ll, 0, 16777215);
/* 154 */         GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 155 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 160 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/* 163 */   int startAspect = 0;
/*     */   
/*     */   private void drawAspects(int k, int l) {
/* 166 */     int count = 0;
/* 167 */     int pos = 0;
/* 168 */     if (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(this.index)).hash)))
/* 169 */       for (Aspect aspect : ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.getAspectsSortedByName()) {
/* 170 */         if (count >= this.startAspect)
/*     */         {
/* 172 */           GL11.glPushMatrix();
/* 173 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 174 */           func_73729_b(k + 41 + 16 * pos, l + 57, 176, 8, 14, 6);
/*     */           
/* 176 */           int i1 = (int)(this.inventory.essentia.getAmount(aspect) / ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.getAmount(aspect) * 12.0F);
/*     */           
/*     */ 
/* 179 */           Color c = new Color(aspect.getColor());
/* 180 */           GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/* 181 */           func_73729_b(k + 42 + 16 * pos, l + 58, 176, 0, i1, 4);
/* 182 */           GL11.glPopMatrix();
/*     */           
/* 184 */           pos++;
/*     */         }
/* 186 */         count++;
/* 187 */         if (count >= 6 + this.startAspect)
/*     */           break;
/*     */       }
/* 190 */     count = 0;
/* 191 */     pos = 0;
/* 192 */     for (Aspect aspect : ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.getAspectsSortedByName()) {
/* 193 */       if (count >= this.startAspect) {
/* 194 */         UtilsFX.drawTag(k + 40 + 16 * pos, l + 40, aspect, ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.getAmount(aspect), 0, this.field_73735_i);
/* 195 */         pos++;
/*     */       }
/* 197 */       count++;
/* 198 */       if (count >= 6 + this.startAspect)
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/* 203 */   private void drawOutput(int x, int y, int mx, int my) { GL11.glPushMatrix();
/* 204 */     boolean dull = false;
/* 205 */     if ((this.inventory.recipeHash.size() < this.inventory.maxRecipes) || (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(this.index)).hash))))
/*     */     {
/* 207 */       dull = true;
/* 208 */       float alpha = 0.3F + MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 4.0F) * 0.3F + 0.3F;
/* 209 */       GL11.glColor4f(0.5F, 0.5F, 0.5F, alpha);
/*     */     }
/*     */     
/*     */ 
/* 213 */     GlStateManager.func_179094_E();
/* 214 */     net.minecraft.client.renderer.RenderHelper.func_74520_c();
/* 215 */     GlStateManager.func_179140_f();
/* 216 */     GlStateManager.func_179091_B();
/* 217 */     GlStateManager.func_179142_g();
/* 218 */     GlStateManager.func_179145_e();
/* 219 */     this.field_146296_j.field_77023_b = 100.0F;
/* 220 */     this.field_146296_j.func_180450_b(((CrucibleRecipe)this.container.recipes.get(this.index)).getRecipeOutput(), x + 112, y + 16);
/* 221 */     this.field_146296_j.func_175030_a(this.field_146289_q, ((CrucibleRecipe)this.container.recipes.get(this.index)).getRecipeOutput(), x + 112, y + 16);
/* 222 */     this.field_146296_j.field_77023_b = 0.0F;
/* 223 */     GlStateManager.func_179140_f();
/* 224 */     GlStateManager.func_179121_F();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 236 */     int xx = mx - (x + 112);
/* 237 */     int yy = my - (y + 16);
/* 238 */     if ((xx >= 0) && (yy >= 0) && (xx < 16) && (yy < 16)) {
/* 239 */       func_146285_a(((CrucibleRecipe)this.container.recipes.get(this.index)).getRecipeOutput(), mx, my);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 245 */     GL11.glDisable(3042);
/* 246 */     GL11.glDisable(2896);
/* 247 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   protected void func_73864_a(int mx, int my, int par3)
/*     */     throws IOException
/*     */   {
/* 253 */     super.func_73864_a(mx, my, par3);
/*     */     
/* 255 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 256 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/* 259 */     int x = mx - (gx + 112);
/* 260 */     int y = my - (gy + 16);
/*     */     
/* 262 */     if ((this.container.recipes.size() > 0) && (this.index >= 0) && (this.index < this.container.recipes.size())) {
/* 263 */       if ((x >= 0) && (y >= 0) && (x < 16) && (y < 16) && (
/* 264 */         (this.inventory.recipeHash.size() < this.inventory.maxRecipes) || (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(this.index)).hash)))))
/*     */       {
/* 266 */         this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.index);
/* 267 */         playButtonSelect();
/*     */       }
/*     */       
/*     */ 
/* 271 */       if (this.container.recipes.size() > 1)
/*     */       {
/* 273 */         if (this.index > 0) {
/* 274 */           x = mx - (gx + 128);
/* 275 */           y = my - (gy + 16);
/* 276 */           if ((x >= 0) && (y >= 0) && (x < 16) && (y < 8)) {
/* 277 */             this.index -= 1;
/* 278 */             playButtonClick();
/*     */           }
/*     */         }
/*     */         
/* 282 */         if (this.index < this.container.recipes.size() - 1) {
/* 283 */           x = mx - (gx + 128);
/* 284 */           y = my - (gy + 24);
/* 285 */           if ((x >= 0) && (y >= 0) && (x < 16) && (y < 8)) {
/* 286 */             this.index += 1;
/* 287 */             playButtonClick();
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 292 */       if (((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.size() > 6)
/*     */       {
/* 294 */         if (this.startAspect > 0) {
/* 295 */           x = mx - (gx + 32);
/* 296 */           y = my - (gy + 40);
/* 297 */           if ((x >= 0) && (y >= 0) && (x < 8) && (y < 16)) {
/* 298 */             this.startAspect -= 1;
/* 299 */             playButtonClick();
/*     */           }
/*     */         }
/*     */         
/* 303 */         if (this.startAspect < ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.size() - 1) {
/* 304 */           x = mx - (gx + 136);
/* 305 */           y = my - (gy + 40);
/* 306 */           if ((x >= 0) && (y >= 0) && (x < 8) && (y < 16)) {
/* 307 */             this.startAspect += 1;
/* 308 */             playButtonClick();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonSelect()
/*     */   {
/* 318 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:hhon", 0.3F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonClick()
/*     */   {
/* 324 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiThaumatorium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */