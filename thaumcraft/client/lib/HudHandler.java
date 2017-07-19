/*     */ package thaumcraft.client.lib;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiIngame;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.items.IFocusPicker;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ 
/*     */ public class HudHandler
/*     */ {
/*  40 */   final ResourceLocation HUD = new ResourceLocation("thaumcraft", "textures/gui/hud.png");
/*     */   
/*  42 */   public static AspectList currentAura = new AspectList();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderThaumometerHud(Minecraft mc, Float partialTicks, EntityPlayer player, long time) {
/*  46 */     GL11.glPushMatrix();
/*  47 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  48 */     GL11.glClear(256);
/*  49 */     GL11.glMatrixMode(5889);
/*  50 */     GL11.glLoadIdentity();
/*  51 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/*  52 */     GL11.glMatrixMode(5888);
/*  53 */     GL11.glLoadIdentity();
/*  54 */     int k = sr.func_78326_a();
/*  55 */     int l = sr.func_78328_b();
/*     */     
/*  57 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*  58 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  60 */     GL11.glEnable(3042);
/*  61 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  63 */     mc.field_71446_o.func_110577_a(this.HUD);
/*     */     
/*  65 */     float t = currentAura.getAmount(Aspect.VOID);
/*  66 */     int single = currentAura.getAmount(Aspect.CRYSTAL);
/*  67 */     int m = 0;
/*  68 */     float gap = 0.0F;
/*  69 */     for (Aspect aspect : thaumcraft.api.aspects.AspectHelper.getAuraAspects(currentAura).getAspects()) {
/*  70 */       if (aspect != null) {
/*  71 */         float a = currentAura.getAmount(aspect) / t;
/*  72 */         if (single > 0) {
/*  73 */           gap = 1.0F - a;
/*  74 */           if (gap < 0.0F) gap = 0.0F; else gap *= 64.0F;
/*     */         }
/*  76 */         if (a > 0.0F) {
/*  77 */           if (a > 1.0F) {
/*  78 */             m = (int)((a - 1.0F) * 64.0F);
/*  79 */             a = 1.0F;
/*     */           }
/*  81 */           GL11.glPushMatrix();
/*  82 */           Color c = new Color(aspect.getColor());
/*  83 */           GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 0.9F);
/*  84 */           GL11.glTranslated(5.0D, 9.0F + gap, 0.0D);
/*  85 */           GL11.glScaled(1.0D, a, 1.0D);
/*  86 */           UtilsFX.drawTexturedQuad(0, 0, 88, 56, 8, 64, -90.0D);
/*  87 */           GL11.glPopMatrix();
/*  88 */           gap += a * 64.0F;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  93 */     GL11.glPushMatrix();
/*  94 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  95 */     UtilsFX.drawTexturedQuad(1, 1, 72, 48, 16, 80, -90.0D);
/*  96 */     GL11.glPopMatrix();
/*     */     
/*  98 */     if (single > 0) {
/*  99 */       GL11.glPushMatrix();
/* 100 */       UtilsFX.drawTexturedQuad(2, 13 + m, 109, 61, 14, 5, -90.0D);
/* 101 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 104 */     GL11.glDisable(3042);
/*     */     
/* 106 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderSanityHud(Minecraft mc, Float partialTicks, EntityPlayer player, long time)
/*     */   {
/* 114 */     GL11.glPushMatrix();
/*     */     
/* 116 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/* 117 */     GL11.glClear(256);
/* 118 */     GL11.glMatrixMode(5889);
/* 119 */     GL11.glLoadIdentity();
/* 120 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/* 121 */     GL11.glMatrixMode(5888);
/* 122 */     GL11.glLoadIdentity();
/* 123 */     int k = sr.func_78326_a();
/* 124 */     int l = sr.func_78328_b();
/*     */     
/* 126 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/* 127 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 129 */     GL11.glEnable(3042);
/* 130 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 132 */     mc.field_71446_o.func_110577_a(this.HUD);
/*     */     
/* 134 */     GL11.glPushMatrix();
/* 135 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 136 */     UtilsFX.drawTexturedQuad(1, 1, 152, 0, 20, 76, -90.0D);
/* 137 */     GL11.glPopMatrix();
/*     */     
/* 139 */     float tw = Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_());
/* 140 */     int p = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_());
/* 141 */     int s = Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
/* 142 */     int t = Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_());
/* 143 */     float mod = 1.0F;
/* 144 */     if (tw > 100.0F) {
/* 145 */       mod = 100.0F / tw;
/* 146 */       tw = 100.0F;
/*     */     }
/* 148 */     int gap = (int)((100.0F - tw) / 100.0F * 48.0F);
/* 149 */     int wt = (int)(t / 100.0F * 48.0F * mod);
/* 150 */     int ws = (int)(s / 100.0F * 48.0F * mod);
/*     */     
/* 152 */     if (t > 0) {
/* 153 */       GL11.glPushMatrix();
/* 154 */       GL11.glColor4f(1.0F, 0.5F, 1.0F, 1.0F);
/* 155 */       UtilsFX.drawTexturedQuad(7, 21 + gap, 200, gap, 8, wt + gap, -90.0D);
/* 156 */       GL11.glPopMatrix();
/*     */     }
/* 158 */     if (s > 0) {
/* 159 */       GL11.glPushMatrix();
/* 160 */       GL11.glColor4f(0.75F, 0.0F, 0.75F, 1.0F);
/* 161 */       UtilsFX.drawTexturedQuad(7, 21 + wt + gap, 200, wt + gap, 8, wt + ws + gap, -90.0D);
/* 162 */       GL11.glPopMatrix();
/*     */     }
/* 164 */     if (p > 0) {
/* 165 */       GL11.glPushMatrix();
/* 166 */       GL11.glColor4f(0.5F, 0.0F, 0.5F, 1.0F);
/* 167 */       UtilsFX.drawTexturedQuad(7, 21 + wt + ws + gap, 200, wt + ws + gap, 8, 48, -90.0D);
/* 168 */       GL11.glPopMatrix();
/*     */     }
/* 170 */     GL11.glPushMatrix();
/* 171 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 172 */     UtilsFX.drawTexturedQuad(1, 1, 176, 0, 20, 76, -90.0D);
/* 173 */     GL11.glPopMatrix();
/* 174 */     if (tw >= 100.0F) {
/* 175 */       GL11.glPushMatrix();
/* 176 */       UtilsFX.drawTexturedQuad(1, 1, 216, 0, 20, 16, -90.0D);
/* 177 */       GL11.glPopMatrix();
/*     */     }
/* 179 */     GL11.glDisable(3042);
/*     */     
/* 181 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderChargeMeters(Minecraft mc, float renderTickTime, EntityPlayer player, long time)
/*     */   {
/* 189 */     GL11.glPushMatrix();
/*     */     
/* 191 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/* 192 */     GL11.glClear(256);
/* 193 */     GL11.glMatrixMode(5889);
/* 194 */     GL11.glLoadIdentity();
/* 195 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/* 196 */     GL11.glMatrixMode(5888);
/* 197 */     GL11.glLoadIdentity();
/* 198 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*     */     
/* 200 */     int ww = sr.func_78326_a();
/* 201 */     int hh = sr.func_78328_b();
/*     */     
/* 203 */     int start = 0;
/* 204 */     int total = 0;
/*     */     
/* 206 */     if (Config.chargeBarPos == 2) {
/* 207 */       for (RenderEventHandler.ChargeEntry ce : RenderEventHandler.chargedItems.values()) {
/* 208 */         if (ce.time >= time - 10000L)
/* 209 */           total++;
/*     */       }
/* 211 */       total *= 10;
/*     */     }
/*     */     
/* 214 */     float shift = 1.0F;
/*     */     
/* 216 */     for (RenderEventHandler.ChargeEntry ce : RenderEventHandler.chargedItems.values())
/*     */     {
/* 218 */       float level = ce.charge * 16.0F;
/* 219 */       shift = 1.0F;
/* 220 */       if (level > 0.0F) {
/* 221 */         if (ce.time < time - 10000L) continue;
/* 222 */         if (ce.time > time - 500L) {
/* 223 */           shift = (float)(time - ce.time) / 500.0F;
/*     */         }
/* 225 */         if (ce.time < time - 9500L) {
/* 226 */           shift = 1.0F - (float)(time - ce.time - 9500L) / 500.0F;
/*     */         }
/*     */       }
/*     */       
/* 230 */       mc.field_71446_o.func_110577_a(this.HUD);
/*     */       
/* 232 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 233 */       GL11.glDisable(2929);
/* 234 */       GL11.glDepthMask(false);
/* 235 */       GL11.glEnable(3042);
/* 236 */       GL11.glBlendFunc(770, 771);
/* 237 */       GL11.glDisable(3008);
/*     */       
/* 239 */       if ((level == 0.0F) && (player.field_70173_aa / 10 % 2 == 0)) { GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/* 241 */       int x = 0;
/* 242 */       int y = 0;
/*     */       
/* 244 */       switch (Config.chargeBarPos) {
/*     */       case 0: 
/* 246 */         x = (int)(-20.0F + 22.0F * shift);
/* 247 */         y = hh / 2 + 17 + start * 20;
/* 248 */         break;
/*     */       case 1: 
/* 250 */         x = ww - (int)(-2.0F + 22.0F * shift);
/* 251 */         y = hh / 2 + 17 + start * 20;
/* 252 */         break;
/*     */       case 2: 
/* 254 */         x = ww / 2 - 18 - start * 20 + total;
/* 255 */         y = (int)(-4.0F + 22.0F * shift);
/*     */       }
/*     */       
/*     */       
/*     */ 
/* 260 */       UtilsFX.drawTexturedQuad(x, y, 144, 99, 16, 3, -91.0D);
/* 261 */       UtilsFX.drawTexturedQuad(x, y, 144, 96, Math.round(level), 3, -91.0D);
/*     */       
/* 263 */       if (ce.diff != 0) {
/* 264 */         GL11.glBlendFunc(770, 1);
/* 265 */         float f = (float)(time - ce.tickTime) / 1000.0F;
/* 266 */         int d = Math.round(f * level);
/* 267 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F - f);
/* 268 */         if (ce.diff > 0) {
/* 269 */           UtilsFX.drawTexturedQuad(x + d - 3, y - 2, 160, 94, 5, 7, -91.0D);
/*     */         } else {
/* 271 */           UtilsFX.drawTexturedQuad(x - d - 5 + Math.round(level), y - 2, 160, 94, 5, 7, -91.0D);
/*     */         }
/* 273 */         GL11.glBlendFunc(770, 771);
/*     */       }
/*     */       
/*     */ 
/* 277 */       GL11.glDepthMask(true);
/* 278 */       GL11.glEnable(2929);
/* 279 */       GL11.glEnable(3008);
/*     */       
/* 281 */       UtilsFX.renderItemInGUI(x, y - 16, 100, ce.item);
/* 282 */       RenderHelper.func_74520_c();
/* 283 */       GlStateManager.func_179140_f();
/*     */       
/* 285 */       start++;
/* 286 */       if (hh / 2 + 50 + start * 21 > hh)
/*     */         break;
/*     */     }
/* 289 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 294 */   HashMap<Integer, AspectList> oldvals = new HashMap();
/* 295 */   long nextsync = 0L;
/* 296 */   DecimalFormat secondsFormatter = new DecimalFormat("#######.#");
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderCastingWandHud(Minecraft mc, float partialTicks, EntityPlayer player, long time, ItemStack wandstack)
/*     */   {
/* 302 */     IWand wand = (IWand)wandstack.func_77973_b();
/*     */     
/* 304 */     if (this.oldvals.get(Integer.valueOf(player.field_71071_by.field_70461_c)) == null) {
/* 305 */       this.oldvals.put(Integer.valueOf(player.field_71071_by.field_70461_c), wand.getAllVis(wandstack));
/*     */     }
/* 307 */     else if (this.nextsync <= time) {
/* 308 */       this.oldvals.put(Integer.valueOf(player.field_71071_by.field_70461_c), wand.getAllVis(wandstack));
/* 309 */       this.nextsync = (time + 1000L);
/*     */     }
/*     */     
/* 312 */     short short1 = 240;
/* 313 */     short short2 = 240;
/* 314 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, short1 / 1.0F, short2 / 1.0F);
/* 315 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 317 */     GL11.glPushMatrix();
/*     */     
/*     */ 
/* 320 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/* 321 */     GL11.glClear(256);
/* 322 */     GL11.glMatrixMode(5889);
/* 323 */     GL11.glLoadIdentity();
/* 324 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/* 325 */     GL11.glMatrixMode(5888);
/* 326 */     GL11.glLoadIdentity();
/* 327 */     int k = sr.func_78326_a();
/* 328 */     int l = sr.func_78328_b();
/*     */     
/* 330 */     int dailLocation = Config.dialBottom ? l - 32 : 0;
/*     */     
/* 332 */     GL11.glTranslatef(0.0F, dailLocation, -2000.0F);
/*     */     
/* 334 */     GL11.glEnable(3042);
/* 335 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 337 */     mc.field_71446_o.func_110577_a(this.HUD);
/*     */     
/* 339 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 341 */     GL11.glPushMatrix();
/* 342 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 343 */     UtilsFX.drawTexturedQuad(0, 0, 0, 0, 64, 64, -90.0D);
/* 344 */     GL11.glPopMatrix();
/*     */     
/* 346 */     GL11.glTranslatef(16.0F, 16.0F, 0.0F);
/*     */     
/* 348 */     int max = wand.getMaxVis(wandstack);
/* 349 */     ItemFocusBasic focus = wand.getFocus(wandstack);
/* 350 */     ItemStack focusStack = wand.getFocusStack(wandstack);
/* 351 */     int count = 0;
/*     */     
/* 353 */     AspectList aspects = wand.getAllVis(wandstack);
/* 354 */     for (Aspect aspect : aspects.getAspects()) {
/* 355 */       int amt = aspects.getAmount(aspect);
/* 356 */       GL11.glPushMatrix();
/* 357 */       if (!Config.dialBottom) GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 358 */       GL11.glRotatef(-15 + count * 24, 0.0F, 0.0F, 1.0F);
/* 359 */       GL11.glTranslatef(0.0F, -32.0F, 0.0F);
/* 360 */       GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 361 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 363 */       int loc = (int)(30.0F * amt / max);
/*     */       
/* 365 */       GL11.glPushMatrix();
/* 366 */       Color ac = new Color(aspect.getColor());
/* 367 */       GL11.glColor4f(ac.getRed() / 255.0F, ac.getGreen() / 255.0F, ac.getBlue() / 255.0F, 0.8F);
/* 368 */       UtilsFX.drawTexturedQuad(-4, 35 - loc, 104, 0, 8, loc, -90.0D);
/* 369 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 370 */       GL11.glPopMatrix();
/*     */       
/* 372 */       GL11.glPushMatrix();
/* 373 */       UtilsFX.drawTexturedQuad(-8, -3, 72, 0, 16, 42, -90.0D);
/* 374 */       GL11.glPopMatrix();
/* 375 */       int sh = 0;
/* 376 */       if ((focus != null) && (focus.getVisCost(focusStack).getAmount(aspect) > 0)) {
/* 377 */         GL11.glPushMatrix();
/* 378 */         UtilsFX.drawTexturedQuad(-4, -8, 136, 0, 8, 8, -90.0D);
/* 379 */         sh = 8;
/* 380 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 383 */       if (((AspectList)this.oldvals.get(Integer.valueOf(player.field_71071_by.field_70461_c))).getAmount(aspect) > amt) {
/* 384 */         GL11.glPushMatrix();
/* 385 */         UtilsFX.drawTexturedQuad(-4, -8 - sh, 128, 0, 8, 8, -90.0D);
/* 386 */         GL11.glPopMatrix();
/*     */       }
/* 388 */       else if (((AspectList)this.oldvals.get(Integer.valueOf(player.field_71071_by.field_70461_c))).getAmount(aspect) < amt) {
/* 389 */         GL11.glPushMatrix();
/* 390 */         UtilsFX.drawTexturedQuad(-4, -8 - sh, 120, 0, 8, 8, -90.0D);
/* 391 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 394 */       if (player.func_70093_af()) {
/* 395 */         GL11.glPushMatrix();
/* 396 */         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/* 397 */         String msg = amt + "";
/* 398 */         mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, -32, -4, 16777215);
/* 399 */         GL11.glPopMatrix();
/*     */         
/* 401 */         if ((focus != null) && (focus.getVisCost(focusStack).getAmount(aspect) > 0)) {
/* 402 */           float mod = wand.getConsumptionModifier(wandstack, player, aspect, false);
/* 403 */           GL11.glPushMatrix();
/* 404 */           GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/* 405 */           msg = "" + this.secondsFormatter.format(focus.getVisCost(focusStack).getAmount(aspect) * mod);
/* 406 */           mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, 8, -4, 16777215);
/* 407 */           GL11.glPopMatrix();
/*     */         }
/*     */         
/* 410 */         mc.field_71446_o.func_110577_a(this.HUD);
/*     */       }
/*     */       
/* 413 */       GL11.glPopMatrix();
/* 414 */       count++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 419 */     if (focus != null) {
/* 420 */       ItemStack picked = null;
/* 421 */       if ((focus instanceof IFocusPicker)) {
/* 422 */         IFocusPicker wt = (IFocusPicker)focus;
/* 423 */         picked = wt.getPickedBlock(player.field_71071_by.func_70448_g());
/* 424 */         if (picked != null) {
/* 425 */           renderWandTradeHud(partialTicks, player, time, picked);
/*     */         }
/*     */       }
/* 428 */       if (picked == null)
/*     */       {
/* 430 */         GL11.glPushMatrix();
/* 431 */         GL11.glTranslatef(-24.0F, -24.0F, 90.0F);
/* 432 */         RenderHelper.func_74520_c();
/* 433 */         GL11.glDisable(2896);
/* 434 */         GL11.glEnable(32826);
/* 435 */         GL11.glEnable(2903);
/* 436 */         GL11.glEnable(2896);
/*     */         try {
/* 438 */           mc.func_175599_af().func_180450_b(wand.getFocusStack(wandstack), 16, 16);
/*     */         } catch (Exception e) {}
/* 440 */         GL11.glDisable(2896);
/* 441 */         GL11.glPopMatrix();
/*     */         
/* 443 */         float f = thaumcraft.common.items.wands.WandManager.getCooldown(player);
/* 444 */         if (f > 0.0F) {
/* 445 */           GL11.glPushMatrix();
/* 446 */           GL11.glTranslatef(0.0F, 0.0F, 150.0F);
/* 447 */           GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 448 */           String secs = this.secondsFormatter.format(f) + "s";
/* 449 */           int w = mc.field_71466_p.func_78256_a(secs) / 2;
/* 450 */           mc.field_71456_v.func_73731_b(mc.field_71466_p, secs, -w, -4, 16777215);
/* 451 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 457 */     GL11.glDisable(3042);
/*     */     
/* 459 */     GL11.glPopMatrix();
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
/*     */ 
/* 471 */   ItemStack lastItem = null;
/* 472 */   int lastCount = 0;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderWandTradeHud(float partialTicks, EntityPlayer player, long time, ItemStack picked) {
/* 476 */     if (picked == null) return;
/* 477 */     Minecraft mc = Minecraft.func_71410_x();
/* 478 */     int amount = this.lastCount;
/* 479 */     if ((this.lastItem == null) || (player.field_71071_by.field_70459_e) || (!picked.func_77969_a(this.lastItem))) {
/* 480 */       amount = 0;
/* 481 */       for (ItemStack is : player.field_71071_by.field_70462_a) {
/* 482 */         if ((is != null) && (is.func_77969_a(picked))) {
/* 483 */           amount += is.field_77994_a;
/*     */         }
/*     */       }
/* 486 */       this.lastItem = picked;
/* 487 */       player.field_71071_by.field_70459_e = false;
/*     */     }
/* 489 */     this.lastCount = amount;
/*     */     
/* 491 */     GL11.glPushMatrix();
/* 492 */     RenderHelper.func_74520_c();
/* 493 */     GL11.glDisable(2896);
/* 494 */     GL11.glEnable(32826);
/* 495 */     GL11.glEnable(2903);
/* 496 */     GL11.glEnable(2896);
/*     */     try {
/* 498 */       mc.func_175599_af().func_180450_b(picked, -8, -8);
/*     */     } catch (Exception e) {}
/* 500 */     GL11.glDisable(2896);
/*     */     
/* 502 */     GL11.glPushMatrix();
/* 503 */     String am = "" + amount;
/* 504 */     int sw = mc.field_71466_p.func_78256_a(am);
/* 505 */     GL11.glTranslatef(0.0F, -mc.field_71466_p.field_78288_b, 500.0F);
/* 506 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 507 */     for (int a = -1; a <= 1; a++) for (int b = -1; b <= 1; b++)
/* 508 */         if (((a == 0) || (b == 0)) && ((a != 0) || (b != 0)))
/* 509 */           mc.field_71466_p.func_78276_b(am, a + 16 - sw, b + 24, 0);
/* 510 */     mc.field_71466_p.func_78276_b(am, 16 - sw, 24, 16777215);
/* 511 */     GL11.glPopMatrix();
/*     */     
/* 513 */     GL11.glPopMatrix();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 588 */   final ResourceLocation TAGBACK = new ResourceLocation("thaumcraft", "textures/aspects/_back.png");
/*     */   
/*     */   public void renderAspectsInGui(GuiContainer gui, EntityPlayer player) {
/* 591 */     Minecraft mc = net.minecraftforge.fml.client.FMLClientHandler.instance().getClient();
/* 592 */     ScaledResolution var13 = new ScaledResolution(Minecraft.func_71410_x());
/* 593 */     int var14 = var13.func_78326_a();
/* 594 */     int var15 = var13.func_78328_b();
/* 595 */     int var16 = Mouse.getX() * var14 / mc.field_71443_c;
/* 596 */     int var17 = var15 - Mouse.getY() * var15 / mc.field_71440_d - 1;
/*     */     
/* 598 */     GL11.glPushMatrix();
/*     */     
/* 600 */     GL11.glPushAttrib(1048575);
/*     */     
/* 602 */     GL11.glDisable(2896);
/* 603 */     for (int var20 = 0; var20 < gui.field_147002_h.field_75151_b.size(); var20++)
/*     */     {
/*     */ 
/* 606 */       int xs = UtilsFX.getGuiXSize(gui);
/* 607 */       int ys = UtilsFX.getGuiYSize(gui);
/* 608 */       int shift = 0;
/* 609 */       int shift2 = 0;
/* 610 */       int shiftx = -8;
/* 611 */       int shifty = -8;
/*     */       
/*     */ 
/* 614 */       if (((gui instanceof net.minecraft.client.renderer.InventoryEffectRenderer)) && (!mc.field_71439_g.func_70651_bq().isEmpty())) {
/* 615 */         shift = 160;
/* 616 */         shift2 = 200;
/*     */       }
/* 618 */       if (Thaumcraft.instance.aspectShift) {
/* 619 */         shiftx -= 8;
/* 620 */         shifty -= 8;
/*     */       }
/* 622 */       Slot var23 = (Slot)gui.field_147002_h.field_75151_b.get(var20);
/* 623 */       int guiLeft = shift + (gui.field_146294_l - xs - shift2) / 2;
/* 624 */       int guiTop = (gui.field_146295_m - ys) / 2;
/* 625 */       if (isMouseOverSlot(var23, var16, var17, guiLeft, guiTop))
/*     */       {
/* 627 */         if (var23.func_75211_c() != null)
/*     */         {
/*     */ 
/*     */ 
/* 631 */           AspectList tags = thaumcraft.common.lib.crafting.ThaumcraftCraftingManager.getObjectTags(var23.func_75211_c());
/*     */           
/* 633 */           if (tags != null)
/*     */           {
/* 635 */             int x = var16 + 17;
/* 636 */             int y = var17 + 7 - 33;
/* 637 */             GL11.glDisable(2929);
/*     */             
/* 639 */             int index = 0;
/* 640 */             if (tags.size() > 0)
/* 641 */               for (Aspect tag : tags.getAspectsSortedByAmount())
/* 642 */                 if (tag != null) {
/* 643 */                   x = var16 + 17 + index * 18;
/* 644 */                   y = var17 + 7 - 33;
/*     */                   
/* 646 */                   mc.field_71446_o.func_110577_a(this.TAGBACK);
/* 647 */                   GL11.glPushMatrix();
/* 648 */                   GL11.glEnable(3042);
/* 649 */                   GL11.glBlendFunc(770, 771);
/* 650 */                   GL11.glTranslated(x + shiftx - 2, y + shifty - 2, 0.0D);
/* 651 */                   GL11.glScaled(1.25D, 1.25D, 0.0D);
/* 652 */                   UtilsFX.drawTexturedQuadFull(0, 0, UtilsFX.getGuiZLevel(gui));
/* 653 */                   GL11.glDisable(3042);
/* 654 */                   GL11.glPopMatrix();
/*     */                   
/* 656 */                   UtilsFX.drawTag(x + shiftx, y + shifty, tag, tags.getAmount(tag), 0, UtilsFX.getGuiZLevel(gui));
/*     */                   
/*     */ 
/* 659 */                   index++;
/*     */                 }
/* 661 */             GL11.glEnable(2929);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 666 */     GL11.glPopAttrib();
/*     */     
/* 668 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3, int par4, int par5)
/*     */   {
/* 676 */     int var4 = par4;
/* 677 */     int var5 = par5;
/* 678 */     par2 -= var4;
/* 679 */     par3 -= var5;
/* 680 */     return (par2 >= par1Slot.field_75223_e - 1) && (par2 < par1Slot.field_75223_e + 16 + 1) && (par3 >= par1Slot.field_75221_f - 1) && (par3 < par1Slot.field_75221_f + 16 + 1);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/HudHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */