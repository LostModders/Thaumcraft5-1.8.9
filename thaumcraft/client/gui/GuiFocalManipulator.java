/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*     */ 
/*     */ @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */ public class GuiFocalManipulator extends GuiContainer
/*     */ {
/*     */   private TileFocalManipulator table;
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   
/*     */   public GuiFocalManipulator(InventoryPlayer par1InventoryPlayer, TileFocalManipulator table)
/*     */   {
/*  45 */     super(new thaumcraft.common.container.ContainerFocalManipulator(par1InventoryPlayer, table));
/*     */     
/*  47 */     this.table = table;
/*  48 */     this.field_146999_f = 192;
/*  49 */     this.field_147000_g = 233;
/*     */     
/*  51 */     if (table.size > 0) {
/*  52 */       gatherInfo();
/*  53 */       this.selected = table.upgrade;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_73863_a(int par1, int par2, float par3)
/*     */   {
/*  67 */     super.func_73863_a(par1, par2, par3);
/*  68 */     GL11.glBlendFunc(770, 771);
/*     */     
/*     */ 
/*  71 */     this.xSize_lo = par1;
/*  72 */     this.ySize_lo = par2;
/*     */     
/*  74 */     int baseX = this.field_147003_i;
/*  75 */     int baseY = this.field_147009_r;
/*     */     
/*  77 */     int mposx = 0;
/*  78 */     int mposy = 0;
/*     */     
/*  80 */     if (this.rank > 0) {
/*  81 */       for (int a = 0; a < this.possibleUpgrades.size(); a++) {
/*  82 */         mposx = par1 - (baseX + 48 + a * 16);
/*  83 */         mposy = par2 - (baseY + 104);
/*  84 */         if ((mposx >= 0) && (mposy >= 0) && (mposx < 16) && (mposy < 16)) {
/*  85 */           FocusUpgradeType u = (FocusUpgradeType)this.possibleUpgrades.get(a);
/*  86 */           List list = new ArrayList();
/*  87 */           list.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.UNDERLINE + u.getLocalizedName());
/*  88 */           list.add(u.getLocalizedText());
/*  89 */           drawHoveringTextFixed(list, baseX + this.field_146999_f - 36, baseY + 24, this.field_146289_q, this.field_146294_l - (baseX + this.field_146999_f - 16));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  94 */     if (this.selected >= 0) {
/*  95 */       mposx = par1 - (baseX + 48);
/*  96 */       mposy = par2 - (baseY + 48);
/*  97 */       if ((mposx >= 0) && (mposy >= 0) && (mposx < 36) && (mposy < 36)) {
/*  98 */         List list = new ArrayList();
/*  99 */         list.add(StatCollector.func_74838_a("wandtable.text1"));
/* 100 */         drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */       }
/* 102 */       mposx = par1 - (baseX + 108);
/* 103 */       mposy = par2 - (baseY + 58);
/* 104 */       if ((mposx >= 0) && (mposy >= 0) && (mposx < 36) && (mposy < 16)) {
/* 105 */         List list = new ArrayList();
/* 106 */         list.add(StatCollector.func_74838_a("wandtable.text2"));
/* 107 */         drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */       }
/* 109 */       if ((this.table.size == 0) && ((this.rank * 6 <= this.field_146297_k.field_71439_g.field_71068_ca) || (this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d)))
/*     */       {
/* 111 */         mposx = par1 - (baseX + 48);
/* 112 */         mposy = par2 - (baseY + 88);
/* 113 */         if ((mposx >= 0) && (mposy >= 0) && (mposx < 96) && (mposy < 8)) {
/* 114 */           List list = new ArrayList();
/* 115 */           list.add(StatCollector.func_74838_a("wandtable.text3"));
/* 116 */           drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 121 */     for (int a = 0; a < this.upgrades.size(); a++) {
/* 122 */       mposx = par1 - (baseX + 56 + a * 16);
/* 123 */       mposy = par2 - (baseY + 32);
/* 124 */       if ((mposx >= 0) && (mposy >= 0) && (mposx < 16) && (mposy < 16)) {
/* 125 */         FocusUpgradeType u = (FocusUpgradeType)this.upgrades.get(a);
/* 126 */         List list = new ArrayList();
/* 127 */         list.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.UNDERLINE + u.getLocalizedName());
/* 128 */         list.add(u.getLocalizedText());
/* 129 */         drawHoveringTextFixed(list, baseX + this.field_146999_f - 36, baseY + 24, this.field_146289_q, this.field_146294_l - (baseX + this.field_146999_f - 16));
/*     */       }
/*     */     }
/* 132 */     GlStateManager.func_179084_k();
/*     */   }
/*     */   
/* 135 */   int selected = -1;
/* 136 */   int rank = 0;
/*     */   long time;
/* 138 */   long nextSparkle = 0L;
/*     */   
/* 140 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_wandtable.png");
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/* 144 */     this.time = System.currentTimeMillis();
/* 145 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 146 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 147 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 148 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/* 150 */     GL11.glEnable(3042);
/* 151 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 153 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/* 155 */     if ((this.table.func_70301_a(0) == null) || (this.table.rank < 0) || (this.table.reset)) {
/* 156 */       this.rank = 0;
/* 157 */       this.selected = -1;
/* 158 */       this.possibleUpgrades.clear();
/* 159 */       this.upgrades.clear();
/* 160 */       this.aspects = new AspectList();
/* 161 */       this.table.reset = false;
/* 162 */       this.table.rank = 0;
/*     */     }
/*     */     
/* 165 */     if (this.rank > 0) {
/* 166 */       for (int a = 0; a < this.possibleUpgrades.size(); a++) {
/* 167 */         FocusUpgradeType u = (FocusUpgradeType)this.possibleUpgrades.get(a);
/* 168 */         if (this.selected == u.id) {
/* 169 */           func_73729_b(k + 48 + a * 16, l + 104, 200, 0, 16, 16);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 174 */     if ((this.rank > 0) && (this.selected >= 0) && (this.table.func_70301_a(0) != null)) {
/* 175 */       int xp = this.rank * 6;
/*     */       
/* 177 */       if ((this.table.size == 0) && ((xp <= this.field_146297_k.field_71439_g.field_71068_ca) || (this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d))) {
/* 178 */         func_73729_b(k + 48, l + 88, 8, 240, 96, 8);
/*     */       }
/* 180 */       func_73729_b(k + 108, l + 59, 200, 16, 16, 16);
/* 181 */       int start = 0;
/* 182 */       if (this.table.aspects.size() > 0) {
/* 183 */         for (Aspect aspect : this.table.aspects.getAspectsSortedByName()) {
/* 184 */           if ((aspect != null) && (this.table.aspects.getAmount(aspect) != 0)) {
/* 185 */             int size = (int)(this.table.aspects.getAmount(aspect) / this.table.size * 96.0F);
/* 186 */             Color c = new Color(aspect.getColor());
/* 187 */             GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 0.9F);
/* 188 */             func_73729_b(k + 48 + start, l + 88, 112 + start, 240, size, 8);
/*     */             
/* 190 */             start += size;
/*     */             
/* 192 */             if (this.table.func_145831_w().field_73012_v.nextInt(66) == 0) {
/* 193 */               float x = 48 + start;
/* 194 */               float y = 92.0F;
/* 195 */               float xx = (46 + this.rank * 16 - x) / 9.0F;
/* 196 */               float yy = (38.0F - y) / 9.0F;
/* 197 */               this.sparkles.put(Long.valueOf(this.time), new Sparkle(x, y, xx, yy, c.getRed() / 285.0F, c.getGreen() / 285.0F, c.getBlue() / 285.0F));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 204 */       this.field_146289_q.func_175063_a("" + xp, k + 125, l + 64, xp > this.field_146297_k.field_71439_g.field_71068_ca ? 16151160 : 10092429);
/*     */       
/*     */ 
/* 207 */       AspectList al = this.aspects;
/* 208 */       if (this.table.size > 0) al = this.table.aspects;
/* 209 */       int q = 0;
/* 210 */       for (Aspect a : al.getAspectsSortedByName()) {
/* 211 */         if (a != null) {
/* 212 */           GL11.glPushMatrix();
/* 213 */           GL11.glTranslated(k + 49, l + 68 - al.size() * 2.5D, 0.0D);
/* 214 */           GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 215 */           this.field_146289_q.func_175063_a(a.getName(), 0.0F, q * 10, a.getColor());
/* 216 */           String s = "" + al.getAmount(a);
/* 217 */           this.field_146289_q.func_175063_a(s, 48.0F, q * 10, a.getColor());
/* 218 */           GL11.glPopMatrix();
/* 219 */           q++;
/*     */         }
/*     */       }
/*     */     }
/* 223 */     if (this.rank > 0) {
/* 224 */       if (this.nextSparkle < this.time) {
/* 225 */         this.nextSparkle = (this.time + (this.table.size > 0 ? '\n' : 'Ǵ') + this.table.func_145831_w().field_73012_v.nextInt(200));
/* 226 */         this.sparkles.put(Long.valueOf(this.time), new Sparkle(42 + this.rank * 16 + this.table.func_145831_w().field_73012_v.nextInt(12), 34 + this.table.func_145831_w().field_73012_v.nextInt(12), 0.0F, 0.0F, 0.5F + this.table.func_145831_w().field_73012_v.nextFloat() * 0.3F, 0.8F - this.table.func_145831_w().field_73012_v.nextFloat() * 0.3F, 0.8F - this.table.func_145831_w().field_73012_v.nextFloat() * 0.3F));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 234 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 235 */       for (int a = 0; a < this.possibleUpgrades.size(); a++) {
/* 236 */         FocusUpgradeType u = (FocusUpgradeType)this.possibleUpgrades.get(a);
/* 237 */         GL11.glPushMatrix();
/* 238 */         GL11.glEnable(3042);
/* 239 */         GL11.glBlendFunc(770, 771);
/* 240 */         this.field_146297_k.field_71446_o.func_110577_a(u.icon);
/* 241 */         UtilsFX.drawTexturedQuadFull(k + 48 + a * 16, l + 104, this.field_73735_i);
/* 242 */         GL11.glDisable(3042);
/* 243 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/* 246 */     else if ((this.rank == 0) && (this.table.func_70301_a(0) != null)) {
/* 247 */       try { gatherInfo();
/*     */       } catch (Exception e) {} }
/* 249 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 250 */     for (int a = 0; a < this.upgrades.size(); a++) {
/* 251 */       FocusUpgradeType u = (FocusUpgradeType)this.upgrades.get(a);
/* 252 */       GL11.glPushMatrix();
/* 253 */       GL11.glEnable(3042);
/* 254 */       GL11.glBlendFunc(770, 771);
/* 255 */       this.field_146297_k.field_71446_o.func_110577_a(u.icon);
/* 256 */       UtilsFX.drawTexturedQuadFull(k + 56 + a * 16, l + 32, this.field_73735_i);
/* 257 */       GL11.glDisable(3042);
/* 258 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 261 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/* 264 */   DecimalFormat myFormatter = new DecimalFormat("#######.#");
/* 265 */   ArrayList<FocusUpgradeType> possibleUpgrades = new ArrayList();
/* 266 */   ArrayList<FocusUpgradeType> upgrades = new ArrayList();
/* 267 */   AspectList aspects = new AspectList();
/*     */   
/*     */   private void gatherInfo()
/*     */   {
/* 271 */     this.possibleUpgrades.clear();
/* 272 */     this.upgrades.clear();
/* 273 */     this.aspects = new AspectList();
/*     */     
/* 275 */     ItemFocusBasic focus = (ItemFocusBasic)this.table.func_70301_a(0).func_77973_b();
/* 276 */     short[] s = focus.getAppliedUpgrades(this.table.func_70301_a(0));
/* 277 */     this.rank = 1;
/* 278 */     int fu = 0;
/* 279 */     for (; (this.rank <= 5) && 
/* 280 */           (s[(this.rank - 1)] != -1); this.rank += 1)
/*     */     {
/*     */ 
/*     */ 
/* 283 */       this.upgrades.add(FocusUpgradeType.types[s[(this.rank - 1)]]);
/* 284 */       fu++;
/*     */     }
/*     */     
/* 287 */     if (fu == 5) {
/* 288 */       this.rank = -1;
/*     */     } else {
/* 290 */       FocusUpgradeType[] ut = focus.getPossibleUpgradesByRank(this.table.func_70301_a(0), this.rank);
/* 291 */       if (ut == null) return;
/* 292 */       for (int a = 0; a < ut.length; a++) {
/* 293 */         if (focus.canApplyUpgrade(this.table.func_70301_a(0), Minecraft.func_71410_x().field_71439_g, ut[a], this.rank)) {
/* 294 */           this.possibleUpgrades.add(ut[a]);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 300 */     if (this.table.size > 0) {
/* 301 */       this.selected = this.table.upgrade;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_146979_b(int p_146979_1_, int p_146979_2_)
/*     */   {
/* 308 */     this.field_146297_k.field_71446_o.func_110577_a(thaumcraft.client.fx.ParticleEngine.particleTexture);
/* 309 */     Long[] keys = (Long[])this.sparkles.keySet().toArray(new Long[0]);
/* 310 */     for (Long key : keys) {
/* 311 */       Sparkle s = (Sparkle)this.sparkles.get(key);
/* 312 */       drawSparkle(s.x, s.y, s.frame, s.r, s.g, s.b);
/* 313 */       if (s.nextframe < this.time) {
/* 314 */         s.frame += 1;
/* 315 */         s.nextframe = (this.time + 50L);
/* 316 */         s.x += s.mx;
/* 317 */         s.y += s.my;
/*     */       }
/* 319 */       if (s.frame == 9) {
/* 320 */         this.sparkles.remove(key);
/*     */       } else {
/* 322 */         this.sparkles.put(key, s);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_73864_a(int mx, int my, int par3)
/*     */   {
/*     */     try
/*     */     {
/* 331 */       super.func_73864_a(mx, my, par3);
/*     */     }
/*     */     catch (IOException e) {}
/* 334 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 335 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/* 338 */     int var7 = mx - (gx + 48);
/* 339 */     int var8 = my - (gy + 88);
/*     */     
/* 341 */     if ((this.table.size == 0) && (this.selected >= 0) && ((this.rank * 6 <= this.field_146297_k.field_71439_g.field_71068_ca) || (this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d)) && (var7 >= 0) && (var8 >= 0) && (var7 < 96) && (var8 < 8))
/*     */     {
/*     */ 
/*     */ 
/* 345 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.selected);
/* 346 */       playButtonClick();
/* 347 */       return;
/*     */     }
/*     */     
/* 350 */     if (this.table.size == 0) {
/* 351 */       for (int a = 0; a < this.possibleUpgrades.size(); a++) {
/* 352 */         FocusUpgradeType u = (FocusUpgradeType)this.possibleUpgrades.get(a);
/* 353 */         var7 = mx - (gx + 48 + a * 16);
/* 354 */         var8 = my - (gy + 104);
/* 355 */         if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16)) {
/* 356 */           this.aspects = new AspectList();
/* 357 */           if (this.selected == u.id) {
/* 358 */             this.selected = -1;
/*     */           }
/*     */           else {
/* 361 */             this.selected = u.id;
/* 362 */             int amt = 100;
/* 363 */             for (int q = 1; q < this.rank; q++) amt = (int)(amt * 1.5D);
/* 364 */             AspectList tal = new AspectList();
/* 365 */             for (Aspect as : FocusUpgradeType.types[this.selected].aspects.getAspects()) {
/* 366 */               tal.add(as, amt);
/*     */             }
/* 368 */             this.aspects = thaumcraft.api.aspects.AspectHelper.reduceToPrimals(tal);
/*     */           }
/* 370 */           playButtonClick();
/* 371 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void playButtonClick() {
/* 378 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 383 */   HashMap<Long, Sparkle> sparkles = new HashMap();
/*     */   
/*     */   private class Sparkle { float x;
/*     */     float y;
/*     */     float mx;
/*     */     float my;
/*     */     float r;
/*     */     float g;
/*     */     float b;
/* 392 */     public Sparkle(float x, float y, float mx, float my, float r, float g, float b) { this.x = x;
/* 393 */       this.y = y;
/* 394 */       this.mx = mx;
/* 395 */       this.my = my;
/* 396 */       this.frame = 0;
/* 397 */       this.r = r;
/* 398 */       this.g = g;
/* 399 */       this.b = b;
/* 400 */       this.nextframe = (System.currentTimeMillis() + 50L); }
/*     */     
/*     */     long nextframe;
/*     */     int frame; }
/*     */   
/* 405 */   private void drawSparkle(double x, double y, int frame, float r, float g, float b) { GL11.glPushMatrix();
/* 406 */     GL11.glEnable(3042);
/* 407 */     GL11.glBlendFunc(770, 1);
/* 408 */     GL11.glColor4f(r, g, b, 0.9F);
/* 409 */     GL11.glTranslated(x, y, 200.0D);
/* 410 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 411 */     float var8 = frame / 16.0F;
/* 412 */     float var9 = var8 + 0.0624375F;
/* 413 */     float var10 = 0.4375F;
/* 414 */     float var11 = var10 + 0.0624375F;
/* 415 */     tessellator.func_178180_c().func_181668_a(7, net.minecraft.client.renderer.vertex.DefaultVertexFormats.field_181711_k);
/* 416 */     int i = 220;
/* 417 */     int j = i >> 16 & 0xFFFF;
/* 418 */     int k = i & 0xFFFF;
/* 419 */     tessellator.func_178180_c().func_181662_b(-4.0D, 4.0D, this.field_73735_i).func_181673_a(var9, var11).func_181671_a(j, k).func_181666_a(r, g, b, 0.9F).func_181675_d();
/* 420 */     tessellator.func_178180_c().func_181662_b(4.0D, 4.0D, this.field_73735_i).func_181673_a(var9, var10).func_181671_a(j, k).func_181666_a(r, g, b, 0.9F).func_181675_d();
/* 421 */     tessellator.func_178180_c().func_181662_b(4.0D, -4.0D, this.field_73735_i).func_181673_a(var8, var10).func_181671_a(j, k).func_181666_a(r, g, b, 0.9F).func_181675_d();
/* 422 */     tessellator.func_178180_c().func_181662_b(-4.0D, -4.0D, this.field_73735_i).func_181673_a(var8, var11).func_181671_a(j, k).func_181666_a(r, g, b, 0.9F).func_181675_d();
/* 423 */     tessellator.func_78381_a();
/* 424 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 425 */     GL11.glBlendFunc(770, 771);
/* 426 */     GL11.glDisable(3042);
/* 427 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   protected void drawHoveringTextFixed(List listin, int x, int y, FontRenderer font, int width)
/*     */   {
/* 432 */     if (!listin.isEmpty())
/*     */     {
/*     */ 
/* 435 */       GlStateManager.func_179101_C();
/* 436 */       RenderHelper.func_74518_a();
/* 437 */       GlStateManager.func_179140_f();
/* 438 */       GlStateManager.func_179097_i();
/*     */       
/*     */ 
/* 441 */       List list = new ArrayList();
/* 442 */       for (Object o : listin) {
/* 443 */         String s = (String)o;
/* 444 */         s = trimStringNewline(s);
/*     */         
/* 446 */         List list2 = font.func_78271_c(s, width);
/*     */         
/* 448 */         for (iterator = list2.iterator(); iterator.hasNext();)
/*     */         {
/* 450 */           String s1 = (String)iterator.next();
/* 451 */           list.add(s1);
/*     */         }
/*     */       }
/*     */       Iterator iterator;
/* 455 */       int k = 0;
/* 456 */       Iterator iterator = list.iterator();
/*     */       
/* 458 */       while (iterator.hasNext())
/*     */       {
/* 460 */         String s = (String)iterator.next();
/* 461 */         int l = font.func_78256_a(s);
/*     */         
/* 463 */         if (l > k)
/*     */         {
/* 465 */           k = l;
/*     */         }
/*     */       }
/*     */       
/* 469 */       int j2 = x + 12;
/* 470 */       int k2 = y - 12;
/* 471 */       int i1 = 8;
/*     */       
/* 473 */       if (list.size() > 1)
/*     */       {
/* 475 */         i1 += 2 + (list.size() - 1) * 10;
/*     */       }
/*     */       
/*     */ 
/* 479 */       this.field_73735_i = 300.0F;
/* 480 */       this.field_146296_j.field_77023_b = 300.0F;
/* 481 */       int j1 = -267386864;
/* 482 */       func_73733_a(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
/* 483 */       func_73733_a(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
/* 484 */       func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
/* 485 */       func_73733_a(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
/* 486 */       func_73733_a(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
/* 487 */       int k1 = 1347420415;
/* 488 */       int l1 = (k1 & 0xFEFEFE) >> 1 | k1 & 0xFF000000;
/* 489 */       func_73733_a(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
/* 490 */       func_73733_a(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
/* 491 */       func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
/* 492 */       func_73733_a(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
/*     */       
/* 494 */       for (int i2 = 0; i2 < list.size(); i2++)
/*     */       {
/* 496 */         String s1 = (String)list.get(i2);
/* 497 */         font.func_175063_a(s1, j2, k2, -1);
/*     */         
/* 499 */         if (i2 == 0)
/*     */         {
/* 501 */           k2 += 2;
/*     */         }
/*     */         
/* 504 */         k2 += 10;
/*     */       }
/*     */       
/* 507 */       this.field_73735_i = 0.0F;
/* 508 */       this.field_146296_j.field_77023_b = 0.0F;
/* 509 */       GlStateManager.func_179145_e();
/* 510 */       GlStateManager.func_179126_j();
/* 511 */       RenderHelper.func_74519_b();
/* 512 */       GlStateManager.func_179091_B();
/*     */     }
/*     */   }
/*     */   
/*     */   private String trimStringNewline(String p_78273_1_)
/*     */   {
/* 518 */     while ((p_78273_1_ != null) && (p_78273_1_.endsWith("\n")))
/*     */     {
/* 520 */       p_78273_1_ = p_78273_1_.substring(0, p_78273_1_.length() - 1);
/*     */     }
/*     */     
/* 523 */     return p_78273_1_;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiFocalManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */