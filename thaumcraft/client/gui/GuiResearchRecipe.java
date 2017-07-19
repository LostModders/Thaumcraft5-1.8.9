/*      */ package thaumcraft.client.gui;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.entity.EntityPlayerSP;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.gui.GuiButton;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.WorldRenderer;
/*      */ import net.minecraft.client.renderer.entity.RenderItem;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.FurnaceRecipes;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.item.crafting.ShapedRecipes;
/*      */ import net.minecraft.item.crafting.ShapelessRecipes;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*      */ import net.minecraftforge.oredict.ShapedOreRecipe;
/*      */ import net.minecraftforge.oredict.ShapelessOreRecipe;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.crafting.CrucibleRecipe;
/*      */ import thaumcraft.api.crafting.IArcaneRecipe;
/*      */ import thaumcraft.api.crafting.InfusionRecipe;
/*      */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*      */ import thaumcraft.api.crafting.ShapelessArcaneRecipe;
/*      */ import thaumcraft.api.research.ResearchCategories;
/*      */ import thaumcraft.api.research.ResearchHelper;
/*      */ import thaumcraft.api.research.ResearchItem;
/*      */ import thaumcraft.api.research.ResearchPage;
/*      */ import thaumcraft.api.research.ResearchPage.PageType;
/*      */ import thaumcraft.client.lib.TCFontRenderer;
/*      */ import thaumcraft.client.lib.UtilsFX;
/*      */ import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ 
/*      */ @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */ public class GuiResearchRecipe extends GuiScreen
/*      */ {
/*   62 */   public static LinkedList<Object[]> history = new LinkedList();
/*      */   
/*   64 */   protected int paneWidth = 256;
/*   65 */   protected int paneHeight = 181;
/*      */   
/*      */ 
/*      */   protected double guiMapX;
/*      */   
/*      */ 
/*      */   protected double guiMapY;
/*      */   
/*      */ 
/*   74 */   protected int mouseX = 0;
/*      */   
/*      */ 
/*   77 */   protected int mouseY = 0;
/*      */   
/*      */   private GuiButton button;
/*      */   
/*      */   private ResearchItem research;
/*   82 */   private ResearchPage[] pages = null;
/*   83 */   private int page = 0;
/*   84 */   private int maxPages = 0;
/*      */   
/*   86 */   TCFontRenderer fr = null;
/*      */   
/*   88 */   HashMap<Aspect, ArrayList<ItemStack>> aspectItems = new HashMap();
/*      */   float pt;
/*      */   
/*      */   public GuiResearchRecipe(ResearchItem research, int page, double x, double y)
/*      */   {
/*   93 */     this.research = research;
/*   94 */     this.guiMapX = x;
/*   95 */     this.guiMapY = y;
/*      */     
/*   97 */     this.field_146297_k = Minecraft.func_71410_x();
/*      */     
/*   99 */     this.pages = research.getPages();
/*      */     
/*  101 */     List<ResearchPage> p1 = Arrays.asList(this.pages);
/*  102 */     ArrayList<ResearchPage> p2 = new ArrayList();
/*  103 */     for (ResearchPage pp : p1) {
/*  104 */       if ((pp == null) || (pp.research == null) || (ResearchHelper.isResearchComplete(this.field_146297_k.field_71439_g.func_70005_c_(), pp.research)))
/*      */       {
/*      */ 
/*  107 */         p2.add(pp);
/*      */       }
/*      */     }
/*  110 */     this.pages = ((ResearchPage[])p2.toArray(new ResearchPage[0]));
/*      */     
/*  112 */     if (research.key.equals("ASPECTS"))
/*      */     {
/*  114 */       List itemList = com.google.common.collect.Lists.newArrayList();
/*  115 */       Iterator iterator = Item.field_150901_e.iterator();
/*  116 */       while (iterator.hasNext())
/*      */       {
/*  118 */         Item item = (Item)iterator.next();
/*  119 */         if ((item != null) && (item.func_77640_w() != null))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  124 */           for (net.minecraft.creativetab.CreativeTabs tab : item.getCreativeTabs())
/*      */           {
/*  126 */             item.func_150895_a(item, tab, itemList);
/*      */           }
/*      */         }
/*      */       }
/*  130 */       for (Object io : itemList) {
/*      */         try {
/*  132 */           ItemStack is = (ItemStack)io;
/*  133 */           AspectList tags = thaumcraft.common.lib.crafting.ThaumcraftCraftingManager.getObjectTags(is);
/*  134 */           if ((tags != null) && (tags.size() > 0)) {
/*  135 */             for (Aspect a : tags.getAspects()) {
/*  136 */               ArrayList<ItemStack> items = (ArrayList)this.aspectItems.get(a);
/*  137 */               if (items == null) {
/*  138 */                 items = new ArrayList();
/*      */               }
/*  140 */               ItemStack is2 = is.func_77946_l();
/*  141 */               is2.field_77994_a = tags.getAmount(a);
/*  142 */               items.add(is2);
/*  143 */               this.aspectItems.put(a, items);
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (Exception e) {}
/*      */       }
/*      */       
/*      */ 
/*  151 */       ArrayList<ResearchPage> tpl = new ArrayList();
/*  152 */       for (ResearchPage p : research.getPages()) { tpl.add(p);
/*      */       }
/*  154 */       AspectList tempal = new AspectList();
/*  155 */       Aspect aspect; for (Iterator i$ = Aspect.aspects.values().iterator(); i$.hasNext(); tempal.add(aspect, 1)) { aspect = (Aspect)i$.next();
/*      */       }
/*  157 */       AspectList tal = new AspectList();
/*  158 */       int count = 0;
/*      */       
/*  160 */       for (Aspect aspect : tempal.getAspectsSortedByName()) {
/*  161 */         if (count <= 4) {
/*  162 */           count++;
/*  163 */           tal.add(aspect, 0);
/*      */         }
/*  165 */         if (count == 4) {
/*  166 */           count = 0;
/*  167 */           tpl.add(new ResearchPage(tal.copy()));
/*  168 */           tal = new AspectList();
/*      */         }
/*      */       }
/*      */       
/*  172 */       if (count > 0) {
/*  173 */         tpl.add(new ResearchPage(tal));
/*      */       }
/*  175 */       this.pages = ((ResearchPage[])tpl.toArray(this.pages));
/*      */     }
/*      */     
/*  178 */     this.maxPages = this.pages.length;
/*      */     
/*  180 */     if (page < 0) page = 0;
/*  181 */     this.fr = new TCFontRenderer(this.field_146297_k.field_71474_y, TCFontRenderer.FONT_NORMAL, this.field_146297_k.field_71446_o, true);
/*  182 */     if (page % 2 == 1) page--;
/*  183 */     this.page = page;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_73866_w_() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_73863_a(int par1, int par2, float par3)
/*      */   {
/*  199 */     this.pt = par3;
/*  200 */     func_146276_q_();
/*  201 */     genResearchBackground(par1, par2, par3);
/*      */     
/*  203 */     int sw = (this.field_146294_l - this.paneWidth) / 2;
/*  204 */     int sh = (this.field_146295_m - this.paneHeight) / 2;
/*      */     
/*      */ 
/*  207 */     if (!history.isEmpty()) {
/*  208 */       int mx = par1 - (sw + 118);
/*  209 */       int my = par2 - (sh + 189);
/*  210 */       if ((mx >= 0) && (my >= 0) && (mx < 20) && (my < 12)) {
/*  211 */         this.field_146289_q.func_175063_a(StatCollector.func_74838_a("recipe.return"), par1, par2, 16777215);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*  218 */   ResourceLocation tex1 = new ResourceLocation("thaumcraft", "textures/gui/gui_researchbook.png");
/*  219 */   ResourceLocation tex2 = new ResourceLocation("thaumcraft", "textures/gui/gui_researchbook_overlay.png");
/*  220 */   ResourceLocation tex3 = new ResourceLocation("thaumcraft", "textures/aspects/_back.png");
/*      */   
/*      */   protected void genResearchBackground(int par1, int par2, float par3)
/*      */   {
/*  224 */     int sw = (this.field_146294_l - this.paneWidth) / 2;
/*  225 */     int sh = (this.field_146295_m - this.paneHeight) / 2;
/*      */     
/*  227 */     float var10 = (this.field_146294_l - this.paneWidth * 1.3F) / 2.0F;
/*  228 */     float var11 = (this.field_146295_m - this.paneHeight * 1.3F) / 2.0F;
/*      */     
/*  230 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  231 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex1);
/*      */     
/*  233 */     GlStateManager.func_179147_l();
/*  234 */     GlStateManager.func_179112_b(770, 771);
/*      */     
/*  236 */     GL11.glPushMatrix();
/*  237 */     GL11.glTranslatef(var10, var11, 0.0F);
/*  238 */     GL11.glScalef(1.3F, 1.3F, 1.0F);
/*  239 */     func_73729_b(0, 0, 0, 0, this.paneWidth, this.paneHeight);
/*  240 */     GL11.glPopMatrix();
/*      */     
/*  242 */     this.reference.clear();
/*      */     
/*  244 */     int current = 0;
/*  245 */     for (int a = 0; a < this.pages.length; a++) {
/*  246 */       GlStateManager.func_179101_C();
/*  247 */       RenderHelper.func_74518_a();
/*  248 */       GlStateManager.func_179140_f();
/*      */       
/*  250 */       GlStateManager.func_179147_l();
/*  251 */       GlStateManager.func_179112_b(770, 771);
/*  252 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/*  254 */       if (((current == this.page) || (current == this.page + 1)) && (current < this.maxPages)) {
/*  255 */         drawPage(this.pages[a], current % 2, sw, sh, par1, par2);
/*      */       }
/*  257 */       current++;
/*      */       
/*  259 */       this.field_73735_i = 0.0F;
/*  260 */       this.field_146296_j.field_77023_b = 0.0F;
/*  261 */       GlStateManager.func_179145_e();
/*      */       
/*  263 */       RenderHelper.func_74519_b();
/*  264 */       GlStateManager.func_179091_B();
/*      */       
/*  266 */       if (current > this.page + 1) break;
/*      */     }
/*  268 */     GlStateManager.func_179147_l();
/*  269 */     GlStateManager.func_179112_b(770, 771);
/*  270 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     
/*  272 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex1);
/*  273 */     int mx1 = par1 - (sw + 261);
/*  274 */     int my1 = par2 - (sh + 189);
/*  275 */     int mx2 = par1 - (sw - 17);
/*  276 */     int my2 = par2 - (sh + 189);
/*      */     
/*  278 */     float bob = MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 3.0F) * 0.2F + 0.1F;
/*      */     
/*      */ 
/*  281 */     if (!history.isEmpty()) {
/*  282 */       drawTexturedModalRectScaled(sw + 118, sh + 189, 38, 202, 20, 12, bob);
/*      */     }
/*      */     
/*  285 */     if (this.page > 0) {
/*  286 */       drawTexturedModalRectScaled(sw - 16, sh + 190, 0, 184, 12, 8, bob);
/*      */     }
/*  288 */     if (this.page < this.maxPages - 2) {
/*  289 */       drawTexturedModalRectScaled(sw + 262, sh + 190, 12, 184, 12, 8, bob);
/*      */     }
/*      */     
/*  292 */     if (this.tipText != null) {
/*  293 */       UtilsFX.drawCustomTooltip(this, this.field_146289_q, this.tipText, par1, par2, 11);
/*  294 */       this.tipText = null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*  299 */   private long lastCycle = 0L;
/*      */   
/*      */   private void drawPage(ResearchPage pageParm, int side, int x, int y, int mx, int my)
/*      */   {
/*  303 */     if (this.lastCycle < System.currentTimeMillis()) {
/*  304 */       this.cycle += 1;
/*  305 */       this.lastCycle = (System.currentTimeMillis() + 1000L);
/*      */     }
/*      */     
/*  308 */     GlStateManager.func_179147_l();
/*  309 */     GlStateManager.func_179112_b(770, 771);
/*      */     
/*  311 */     if ((this.page == 0) && (side == 0)) {
/*  312 */       func_73729_b(x + 4, y - 13, 24, 184, 96, 4);
/*  313 */       func_73729_b(x + 4, y + 4, 24, 184, 96, 4);
/*  314 */       int offset = this.field_146289_q.func_78256_a(this.research.getName());
/*  315 */       if (offset <= 130) {
/*  316 */         this.field_146289_q.func_78276_b(this.research.getName(), x + 52 - offset / 2, y - 6, 3158064);
/*      */       } else {
/*  318 */         float vv = 130.0F / offset;
/*  319 */         GL11.glPushMatrix();
/*  320 */         GL11.glTranslatef(x + 52 - offset / 2 * vv, y - 6.0F * vv, 0.0F);
/*  321 */         GL11.glScalef(vv, vv, vv);
/*  322 */         this.field_146289_q.func_78276_b(this.research.getName(), 0, 0, 3158064);
/*  323 */         GL11.glPopMatrix();
/*      */       }
/*  325 */       y += 25;
/*      */     }
/*      */     
/*  328 */     GlStateManager.func_179147_l();
/*  329 */     GlStateManager.func_179112_b(770, 771);
/*      */     
/*  331 */     if (pageParm.type == ResearchPage.PageType.TEXT) {
/*  332 */       drawTextPage(side, x, y - 10, pageParm.getTranslatedText());
/*      */     }
/*  334 */     else if (pageParm.type == ResearchPage.PageType.ASPECTS) {
/*  335 */       drawAspectPage(side, x - 8, y - 8, mx, my, pageParm.aspects);
/*      */     }
/*  337 */     else if (pageParm.type == ResearchPage.PageType.CRUCIBLE_CRAFTING) {
/*  338 */       drawCruciblePage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  340 */     else if (pageParm.type == ResearchPage.PageType.NORMAL_CRAFTING) {
/*  341 */       drawCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  343 */     else if (pageParm.type == ResearchPage.PageType.ARCANE_CRAFTING) {
/*  344 */       drawArcaneCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  346 */     else if (pageParm.type == ResearchPage.PageType.COMPOUND_CRAFTING) {
/*  347 */       drawCompoundCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  349 */     else if (pageParm.type == ResearchPage.PageType.INFUSION_CRAFTING) {
/*  350 */       drawInfusionPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*  355 */     else if (pageParm.type == ResearchPage.PageType.SMELTING) {
/*  356 */       drawSmeltingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void drawTextPage(int side, int x, int y, String text)
/*      */   {
/*  363 */     this.fr.drawSplitString(text, x - 15 + side * 152, y, 139, 0, this);
/*      */     
/*  365 */     GlStateManager.func_179147_l();
/*  366 */     GlStateManager.func_179112_b(770, 771);
/*      */   }
/*      */   
/*      */   private void drawCompoundCraftingPage(int side, int x, int y, int mx, int my, ResearchPage page) {
/*  370 */     List r = (List)page.recipe;
/*      */     
/*  372 */     if (r != null) {
/*  373 */       GlStateManager.func_179147_l();
/*  374 */       GlStateManager.func_179112_b(770, 771);
/*      */       
/*  376 */       AspectList aspects = (AspectList)r.get(0);
/*  377 */       int dx = ((Integer)r.get(1)).intValue();
/*  378 */       int dy = ((Integer)r.get(2)).intValue();
/*  379 */       int dz = ((Integer)r.get(3)).intValue();
/*  380 */       int xoff = 64 - (dx * 16 + dz * 16) / 2;
/*  381 */       int yoff = -dy * 25;
/*      */       
/*  383 */       List<ItemStack> items = (List)r.get(4);
/*      */       
/*  385 */       GL11.glPushMatrix();
/*  386 */       int start = side * 152;
/*      */       
/*  388 */       String text = StatCollector.func_74838_a("recipe.type.construct");
/*  389 */       int offset = this.field_146289_q.func_78256_a(text);
/*  390 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/*  392 */       int mposx = mx;
/*  393 */       int mposy = my;
/*      */       
/*  395 */       if ((aspects != null) && (aspects.size() > 0)) {
/*  396 */         int count = 0;
/*  397 */         for (Aspect tag : aspects.getAspectsSortedByAmount()) {
/*  398 */           UtilsFX.drawTag(x + start + 14 + 18 * count + (5 - aspects.size()) * 8, y + 182, tag, aspects.getAmount(tag), 0, 0.0D, 771, 1.0F, false);
/*      */           
/*  400 */           count++;
/*      */         }
/*  402 */         count = 0;
/*  403 */         for (Aspect tag : aspects.getAspectsSortedByAmount()) {
/*  404 */           int tx = x + start + 14 + 18 * count + (5 - aspects.size()) * 8;
/*  405 */           int ty = y + 182;
/*  406 */           if ((mposx >= tx) && (mposy >= ty) && (mposx < tx + 16) && (mposy < ty + 16))
/*      */           {
/*  408 */             this.tipText = Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() });
/*      */           }
/*  410 */           count++;
/*      */         }
/*      */       }
/*      */       
/*  414 */       this.field_146297_k.field_71446_o.func_110577_a(this.tex2);
/*      */       
/*  416 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/*  418 */       if ((aspects != null) && (aspects.size() > 0)) {
/*  419 */         GL11.glPushMatrix();
/*  420 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
/*  421 */         GlStateManager.func_179147_l();
/*  422 */         GlStateManager.func_179112_b(770, 771);
/*  423 */         GL11.glTranslatef(x + start, y + 174, 0.0F);
/*  424 */         GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  425 */         func_73729_b(0, 0, 68, 76, 12, 12);
/*  426 */         GL11.glPopMatrix();
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  431 */       GL11.glPushMatrix();
/*  432 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/*  433 */       GlStateManager.func_179147_l();
/*  434 */       GlStateManager.func_179112_b(770, 771);
/*  435 */       GL11.glTranslatef(x + start + xoff, y + 100 + yoff, 0.0F);
/*  436 */       GL11.glTranslatef(-8 - xoff, -119 + Math.max(3 - dx, 3 - dz) * 8 + dx * 4 + dz * 4 + dy * 50, 0.0F);
/*  437 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  438 */       func_73729_b(0, 0, 0, 72, 64, 44);
/*  439 */       GL11.glPopMatrix();
/*      */       
/*  441 */       int count = 0;
/*  442 */       for (int j = 0; j < dy; j++) {
/*  443 */         for (int k = dz - 1; k >= 0; k--) {
/*  444 */           for (int i = dx - 1; i >= 0; i--)
/*      */           {
/*  446 */             int px = x + start + xoff + i * 16 + k * 16;
/*  447 */             int py = y + 100 + yoff - i * 8 + k * 8 + j * 50;
/*      */             
/*  449 */             if ((items.get(count) != null) && (InventoryUtils.cycleItemStack(items.get(count), count) != null))
/*  450 */               drawStackAt(InventoryUtils.cycleItemStack(items.get(count), count), px, py, mx, my, true);
/*  451 */             count++;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  462 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void drawAspectPage(int side, int x, int y, int mx, int my, AspectList aspects)
/*      */   {
/*  515 */     if ((aspects != null) && (aspects.size() > 0)) {
/*  516 */       GL11.glPushMatrix();
/*  517 */       int start = side * 152;
/*      */       
/*  519 */       int mposx = mx;
/*  520 */       int mposy = my;
/*  521 */       int count = 0;
/*  522 */       for (Aspect aspect : aspects.getAspectsSortedByName())
/*      */       {
/*  524 */         if (aspect.getImage() != null)
/*      */         {
/*  526 */           GL11.glPushMatrix();
/*  527 */           int tx = x + start;
/*  528 */           int ty = y + count * 50;
/*  529 */           if ((mposx >= tx) && (mposy >= ty) && (mposx < tx + 40) && (mposy < ty + 40))
/*      */           {
/*  531 */             this.field_146297_k.field_71446_o.func_110577_a(this.tex3);
/*  532 */             GL11.glPushMatrix();
/*  533 */             GlStateManager.func_179147_l();
/*  534 */             GlStateManager.func_179112_b(770, 771);
/*  535 */             GL11.glTranslated(x + start - 5, y + count * 50 - 5, 0.0D);
/*  536 */             GL11.glScaled(2.5D, 2.5D, 0.0D);
/*  537 */             UtilsFX.drawTexturedQuadFull(0, 0, this.field_73735_i);
/*  538 */             GL11.glPopMatrix();
/*      */           }
/*  540 */           GL11.glScalef(2.0F, 2.0F, 2.0F);
/*  541 */           UtilsFX.drawTag((x + start) / 2, (y + count * 50) / 2, aspect, aspects.getAmount(aspect), 0, this.field_73735_i);
/*  542 */           GL11.glPopMatrix();
/*  543 */           String text = aspect.getName();
/*  544 */           int offset = this.fr.getStringWidth(text) / 2;
/*  545 */           this.fr.drawString(text, x + start + 16 - offset, y + 33 + count * 50, 5263440);
/*      */           
/*  547 */           if (aspect.getComponents() != null) {
/*  548 */             GL11.glPushMatrix();
/*  549 */             GL11.glScalef(1.5F, 1.5F, 1.5F);
/*  550 */             UtilsFX.drawTag((int)((x + start + 54) / 1.5F), (int)((y + 4 + count * 50) / 1.5F), aspect.getComponents()[0], 0.0F, 0, this.field_73735_i);
/*  551 */             UtilsFX.drawTag((int)((x + start + 96) / 1.5F), (int)((y + 4 + count * 50) / 1.5F), aspect.getComponents()[1], 0.0F, 0, this.field_73735_i);
/*  552 */             GL11.glPopMatrix();
/*      */             
/*      */ 
/*  555 */             text = aspect.getComponents()[0].getName();
/*  556 */             offset = this.fr.getStringWidth(text) / 2;
/*  557 */             this.fr.drawString("§o" + text, x + start + 16 - offset + 50, y + 30 + count * 50, 5263440);
/*      */             
/*  559 */             text = aspect.getComponents()[1].getName();
/*  560 */             offset = this.fr.getStringWidth(text) / 2;
/*  561 */             this.fr.drawString("§o" + text, x + start + 16 - offset + 92, y + 30 + count * 50, 5263440);
/*      */             
/*  563 */             this.field_146289_q.func_78276_b("=", x + start + 7 + 32, y + 12 + count * 50, 10066329);
/*  564 */             this.field_146289_q.func_78276_b("+", x + start + 4 + 79, y + 12 + count * 50, 10066329);
/*      */           } else {
/*  566 */             this.fr.drawString(StatCollector.func_74838_a("tc.aspect.primal"), x + start + 48, y + 12 + count * 50, 4473924);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*  571 */         count++;
/*      */       }
/*      */       
/*  574 */       count = 0;
/*  575 */       for (Aspect aspect : aspects.getAspectsSortedByName())
/*      */       {
/*  577 */         int tx = x + start;
/*  578 */         int ty = y + count * 50;
/*  579 */         if ((mposx >= tx) && (mposy >= ty) && (mposx < tx + 40) && (mposy < ty + 40))
/*      */         {
/*  581 */           ArrayList<ItemStack> items = (ArrayList)this.aspectItems.get(aspect);
/*  582 */           if ((items != null) && (items.size() > 0)) {
/*  583 */             int xcount = 0;
/*  584 */             int ycount = 0;
/*  585 */             int cc = 0;
/*  586 */             int sp = 0;
/*  587 */             if (items.size() > 16) sp = this.field_146297_k.field_71439_g.field_70173_aa / 5 % items.size();
/*  588 */             int max = Math.min(16, items.size());
/*  589 */             while ((cc < max) && (ycount < 2)) {
/*  590 */               ItemStack item = (ItemStack)items.get(sp);
/*  591 */               GL11.glPushMatrix();
/*  592 */               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  593 */               RenderHelper.func_74520_c();
/*  594 */               GL11.glEnable(2884);
/*  595 */               this.field_146296_j.func_180450_b(InventoryUtils.cycleItemStack(item), mposx + 8 + xcount * 17, 17 * ycount + (mposy - (4 + max / 8 * 8)));
/*      */               
/*  597 */               this.field_146296_j.func_180453_a(this.field_146297_k.field_71466_p, InventoryUtils.cycleItemStack(item), mposx + 8 + xcount * 17, 17 * ycount + (mposy - (4 + max / 8 * 8)), null);
/*      */               
/*      */ 
/*  600 */               RenderHelper.func_74518_a();
/*      */               
/*  602 */               GL11.glPopMatrix();
/*  603 */               xcount++;
/*  604 */               if (xcount >= 8) {
/*  605 */                 xcount = 0;
/*  606 */                 ycount++;
/*      */               }
/*  608 */               sp++;
/*  609 */               if (sp >= items.size()) sp = 0;
/*  610 */               cc++;
/*      */             }
/*  612 */             GL11.glEnable(2896);
/*      */           }
/*      */         }
/*  615 */         count++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  620 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawArcaneCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
/*  625 */     GlStateManager.func_179147_l();
/*  626 */     GlStateManager.func_179112_b(770, 771);
/*  627 */     IArcaneRecipe recipe = null;
/*  628 */     Object tr = null;
/*  629 */     if ((pageParm.recipe instanceof Object[])) {
/*      */       try {
/*  631 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       } catch (Exception e) {
/*  633 */         this.cycle = 0;
/*  634 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       }
/*      */     } else {
/*  637 */       tr = pageParm.recipe;
/*      */     }
/*      */     
/*  640 */     if ((tr instanceof ShapedArcaneRecipe)) {
/*  641 */       recipe = (ShapedArcaneRecipe)tr;
/*      */     }
/*  643 */     else if ((tr instanceof ShapelessArcaneRecipe)) {
/*  644 */       recipe = (ShapelessArcaneRecipe)tr;
/*      */     }
/*  646 */     if (recipe == null) { return;
/*      */     }
/*  648 */     GL11.glPushMatrix();
/*  649 */     int start = side * 152;
/*      */     
/*  651 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex2);
/*      */     
/*  653 */     GL11.glPushMatrix();
/*  654 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  655 */     GlStateManager.func_179147_l();
/*  656 */     GlStateManager.func_179112_b(770, 771);
/*  657 */     GL11.glTranslatef(x + start, y, 0.0F);
/*  658 */     GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  659 */     func_73729_b(2, 27, 112, 15, 52, 52);
/*  660 */     func_73729_b(20, 7, 20, 3, 16, 16);
/*  661 */     GL11.glPopMatrix();
/*      */     
/*  663 */     GL11.glPushMatrix();
/*  664 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
/*  665 */     GlStateManager.func_179147_l();
/*  666 */     GlStateManager.func_179112_b(770, 771);
/*  667 */     GL11.glTranslatef(x + start, y + 164, 0.0F);
/*  668 */     GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  669 */     func_73729_b(0, 0, 68, 76, 12, 12);
/*  670 */     GL11.glPopMatrix();
/*      */     
/*  672 */     int mposx = mx;
/*  673 */     int mposy = my;
/*      */     
/*  675 */     AspectList tags = recipe.getAspects();
/*  676 */     if ((tags != null) && (tags.size() > 0)) {
/*  677 */       int count = 0;
/*  678 */       for (Aspect tag : tags.getAspectsSortedByAmount()) {
/*  679 */         UtilsFX.drawTag(x + start + 14 + 18 * count + (5 - tags.size()) * 8, y + 172, tag, tags.getAmount(tag), 0, 0.0D, 771, 1.0F);
/*      */         
/*  681 */         count++;
/*      */       }
/*      */     }
/*      */     
/*  685 */     String text = StatCollector.func_74838_a("recipe.type.arcane");
/*  686 */     int offset = this.field_146289_q.func_78256_a(text);
/*  687 */     this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */     
/*  689 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  690 */     GL11.glTranslated(0.0D, 0.0D, 100.0D);
/*      */     
/*  692 */     drawStackAt(InventoryUtils.cycleItemStack(recipe.func_77571_b(), 0), x + 48 + start, y + 22, mx, my, false);
/*      */     
/*      */ 
/*  695 */     if ((recipe != null) && ((recipe instanceof ShapedArcaneRecipe))) {
/*  696 */       int rw = ((ShapedArcaneRecipe)recipe).width;
/*  697 */       int rh = ((ShapedArcaneRecipe)recipe).height;
/*  698 */       Object[] items = ((ShapedArcaneRecipe)recipe).getInput();
/*      */       
/*  700 */       for (int i = 0; (i < rw) && (i < 3); i++) {
/*  701 */         for (int j = 0; (j < rh) && (j < 3); j++) {
/*  702 */           if (items[(i + j * rw)] != null)
/*      */           {
/*  704 */             drawStackAt(InventoryUtils.cycleItemStack(items[(i + j * rw)], i + j * rw), x + start + 16 + i * 32, y + 66 + j * 32, mx, my, true);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  709 */     if ((recipe != null) && ((recipe instanceof ShapelessArcaneRecipe)))
/*      */     {
/*  711 */       List<Object> items = ((ShapelessArcaneRecipe)recipe).getInput();
/*      */       
/*  713 */       for (int i = 0; (i < items.size()) && (i < 9); i++) {
/*  714 */         if (items.get(i) != null)
/*      */         {
/*  716 */           drawStackAt(InventoryUtils.cycleItemStack(items.get(i), i), x + start + 16 + i % 3 * 32, y + 66 + i / 3 * 32, mx, my, true);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  721 */     if ((tags != null) && (tags.size() > 0)) {
/*  722 */       int count = 0;
/*  723 */       for (Aspect tag : tags.getAspectsSortedByAmount()) {
/*  724 */         int tx = x + start + 14 + 18 * count + (5 - tags.size()) * 8;
/*  725 */         int ty = y + 172;
/*  726 */         if ((mposx >= tx) && (mposy >= ty) && (mposx < tx + 16) && (mposy < ty + 16))
/*      */         {
/*  728 */           this.tipText = Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() });
/*      */         }
/*  730 */         count++;
/*      */       }
/*      */     }
/*      */     
/*  734 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   private void drawCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
/*  738 */     GlStateManager.func_179147_l();
/*  739 */     GlStateManager.func_179112_b(770, 771);
/*  740 */     IRecipe recipe = null;
/*  741 */     Object tr = null;
/*  742 */     if ((pageParm.recipe instanceof Object[])) {
/*      */       try {
/*  744 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       } catch (Exception e) {
/*  746 */         this.cycle = 0;
/*  747 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       }
/*      */     } else {
/*  750 */       tr = pageParm.recipe;
/*      */     }
/*      */     
/*  753 */     if ((tr instanceof ShapedRecipes)) {
/*  754 */       recipe = (ShapedRecipes)tr;
/*      */     }
/*  756 */     else if ((tr instanceof ShapelessRecipes)) {
/*  757 */       recipe = (ShapelessRecipes)tr;
/*      */     }
/*  759 */     else if ((tr instanceof ShapedOreRecipe)) {
/*  760 */       recipe = (ShapedOreRecipe)tr;
/*      */     }
/*  762 */     else if ((tr instanceof ShapelessOreRecipe)) {
/*  763 */       recipe = (ShapelessOreRecipe)tr;
/*      */     }
/*      */     
/*  766 */     if (recipe == null) { return;
/*      */     }
/*  768 */     GL11.glPushMatrix();
/*  769 */     int start = side * 152;
/*      */     
/*  771 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex2);
/*      */     
/*  773 */     GL11.glPushMatrix();
/*  774 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  775 */     GlStateManager.func_179147_l();
/*  776 */     GlStateManager.func_179112_b(770, 771);
/*  777 */     GL11.glTranslatef(x + start, y, 0.0F);
/*  778 */     GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  779 */     func_73729_b(2, 32, 60, 15, 52, 52);
/*  780 */     func_73729_b(20, 12, 20, 3, 16, 16);
/*  781 */     GL11.glPopMatrix();
/*      */     
/*  783 */     int mposx = mx;
/*  784 */     int mposy = my;
/*      */     
/*  786 */     drawStackAt(InventoryUtils.cycleItemStack(recipe.func_77571_b(), 0), x + 48 + start, y + 32, mx, my, false);
/*      */     
/*  788 */     if ((recipe != null) && (((recipe instanceof ShapedRecipes)) || ((recipe instanceof ShapedOreRecipe)))) {
/*  789 */       String text = StatCollector.func_74838_a("recipe.type.workbench");
/*  790 */       int offset = this.field_146289_q.func_78256_a(text);
/*  791 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/*  793 */       int rw = 0;
/*  794 */       int rh = 0;
/*  795 */       Object[] items = null;
/*  796 */       if ((recipe instanceof ShapedRecipes)) {
/*  797 */         rw = ((ShapedRecipes)recipe).field_77576_b;
/*  798 */         rh = ((ShapedRecipes)recipe).field_77577_c;
/*  799 */         items = ((ShapedRecipes)recipe).field_77574_d;
/*      */       } else {
/*  801 */         rw = ((Integer)ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (ShapedOreRecipe)recipe, new String[] { "width" })).intValue();
/*  802 */         rh = ((Integer)ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (ShapedOreRecipe)recipe, new String[] { "height" })).intValue();
/*  803 */         items = ((ShapedOreRecipe)recipe).getInput();
/*      */       }
/*      */       
/*  806 */       for (int i = 0; (i < rw) && (i < 3); i++) {
/*  807 */         for (int j = 0; (j < rh) && (j < 3); j++) {
/*  808 */           if (items[(i + j * rw)] != null)
/*      */           {
/*  810 */             drawStackAt(InventoryUtils.cycleItemStack(items[(i + j * rw)], i + j * rw), x + start + 16 + i * 32, y + 76 + j * 32, mx, my, true);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  816 */     if ((recipe != null) && (((recipe instanceof ShapelessRecipes)) || ((recipe instanceof ShapelessOreRecipe))))
/*      */     {
/*  818 */       String text = StatCollector.func_74838_a("recipe.type.workbenchshapeless");
/*  819 */       int offset = this.field_146289_q.func_78256_a(text);
/*  820 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/*  822 */       List<Object> items = null;
/*  823 */       if ((recipe instanceof ShapelessRecipes)) {
/*  824 */         items = new ArrayList();
/*  825 */         items.addAll(((ShapelessRecipes)recipe).field_77579_b);
/*      */       } else {
/*  827 */         items = ((ShapelessOreRecipe)recipe).getInput();
/*      */       }
/*  829 */       for (int i = 0; (i < items.size()) && (i < 9); i++) {
/*  830 */         if (items.get(i) != null)
/*      */         {
/*  832 */           drawStackAt(InventoryUtils.cycleItemStack(items.get(i), i), x + start + 16 + i % 3 * 32, y + 76 + i / 3 * 32, mx, my, true);
/*      */         }
/*      */       }
/*      */     }
/*  836 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   private void drawCruciblePage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
/*  840 */     GlStateManager.func_179147_l();
/*  841 */     GlStateManager.func_179112_b(770, 771);
/*  842 */     CrucibleRecipe rc = null;
/*  843 */     Object tr = null;
/*  844 */     if ((pageParm.recipe instanceof Object[])) {
/*      */       try {
/*  846 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       } catch (Exception e) {
/*  848 */         this.cycle = 0;
/*  849 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       }
/*      */     } else {
/*  852 */       tr = pageParm.recipe;
/*      */     }
/*      */     
/*  855 */     if ((tr instanceof CrucibleRecipe)) {
/*  856 */       rc = (CrucibleRecipe)tr;
/*      */     }
/*      */     
/*  859 */     if (rc != null) {
/*  860 */       GL11.glPushMatrix();
/*  861 */       int start = side * 152;
/*      */       
/*  863 */       String text = StatCollector.func_74838_a("recipe.type.crucible");
/*  864 */       int offset = this.field_146289_q.func_78256_a(text);
/*  865 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/*  867 */       this.field_146297_k.field_71446_o.func_110577_a(this.tex2);
/*      */       
/*  869 */       GL11.glPushMatrix();
/*  870 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  871 */       GlStateManager.func_179147_l();
/*  872 */       GlStateManager.func_179112_b(770, 771);
/*  873 */       GL11.glTranslatef(x + start, y + 28, 0.0F);
/*  874 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  875 */       func_73729_b(0, 0, 0, 3, 56, 17);
/*  876 */       GL11.glTranslatef(0.0F, 32.0F, 0.0F);
/*  877 */       func_73729_b(0, 0, 0, 20, 56, 48);
/*  878 */       GL11.glTranslatef(21.0F, -8.0F, 0.0F);
/*  879 */       func_73729_b(0, 0, 100, 84, 11, 13);
/*  880 */       GL11.glPopMatrix();
/*      */       
/*  882 */       int mposx = mx;
/*  883 */       int mposy = my;
/*      */       
/*  885 */       int total = 0;
/*  886 */       int rows = (rc.aspects.size() - 1) / 3;
/*  887 */       int shift = (3 - rc.aspects.size() % 3) * 10;
/*  888 */       int sx = x + start + 28;
/*  889 */       int sy = y + 96 + 32 - 10 * rows;
/*  890 */       for (Aspect tag : rc.aspects.getAspectsSortedByName()) {
/*  891 */         int m = 0;
/*  892 */         if ((total / 3 >= rows) && ((rows > 1) || (rc.aspects.size() < 3))) m = 1;
/*  893 */         int vx = sx + total % 3 * 20 + shift * m;
/*  894 */         int vy = sy + total / 3 * 20;
/*  895 */         UtilsFX.drawTag(vx, vy, tag, rc.aspects.getAmount(tag), 0, this.field_73735_i);
/*      */         
/*  897 */         total++;
/*      */       }
/*      */       
/*  900 */       drawStackAt(rc.getRecipeOutput(), x + 48 + start, y + 36, mx, my, false);
/*      */       
/*  902 */       drawStackAt(InventoryUtils.cycleItemStack(rc.catalyst, 0), x + 26 + start, y + 72, mx, my, true);
/*      */       
/*  904 */       total = 0;
/*  905 */       for (Aspect tag : rc.aspects.getAspectsSortedByName()) {
/*  906 */         int m = 0;
/*  907 */         if ((total / 3 >= rows) && ((rows > 1) || (rc.aspects.size() < 3))) m = 1;
/*  908 */         int vx = sx + total % 3 * 20 + shift * m;
/*  909 */         int vy = sy + total / 3 * 20;
/*      */         
/*  911 */         if ((mposx >= vx) && (mposy >= vy) && (mposx < vx + 16) && (mposy < vy + 16)) {
/*  912 */           this.tipText = Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() });
/*      */         }
/*      */         
/*  915 */         total++;
/*      */       }
/*  917 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   class Coord2D {
/*      */     int x;
/*      */     int y;
/*      */     
/*  925 */     Coord2D(int x, int y) { this.x = x;
/*  926 */       this.y = y;
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawSmeltingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
/*  931 */     GlStateManager.func_179147_l();
/*  932 */     GlStateManager.func_179112_b(770, 771);
/*  933 */     ItemStack in = (ItemStack)pageParm.recipe;
/*  934 */     ItemStack out = null;
/*  935 */     if (in != null) {
/*  936 */       out = FurnaceRecipes.func_77602_a().func_151395_a(in);
/*      */     }
/*  938 */     if ((in != null) && (out != null)) {
/*  939 */       GL11.glPushMatrix();
/*  940 */       int start = side * 152;
/*      */       
/*  942 */       String text = StatCollector.func_74838_a("recipe.type.smelting");
/*  943 */       int offset = this.field_146289_q.func_78256_a(text);
/*  944 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/*  946 */       this.field_146297_k.field_71446_o.func_110577_a(this.tex2);
/*      */       
/*  948 */       GL11.glPushMatrix();
/*  949 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  950 */       GlStateManager.func_179147_l();
/*  951 */       GlStateManager.func_179112_b(770, 771);
/*  952 */       GL11.glTranslatef(x + start, y + 28, 0.0F);
/*  953 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  954 */       func_73729_b(0, 0, 0, 192, 56, 64);
/*  955 */       GL11.glPopMatrix();
/*      */       
/*  957 */       int mposx = mx;
/*  958 */       int mposy = my;
/*      */       
/*  960 */       drawStackAt(in, x + 48 + start, y + 64, mx, my, true);
/*      */       
/*  962 */       drawStackAt(out, x + 48 + start, y + 144, mx, my, false);
/*      */       
/*      */ 
/*  965 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawInfusionPage(int side, int x, int y, int mx, int my, ResearchPage pageParm)
/*      */   {
/*  971 */     Object tr = null;
/*  972 */     if ((pageParm.recipe instanceof Object[])) {
/*      */       try {
/*  974 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       } catch (Exception e) {
/*  976 */         this.cycle = 0;
/*  977 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       }
/*      */     } else {
/*  980 */       tr = pageParm.recipe;
/*      */     }
/*      */     
/*  983 */     InfusionRecipe ri = (InfusionRecipe)tr;
/*  984 */     Object[] components = ri.getComponents();
/*      */     
/*  986 */     if (ri != null)
/*      */     {
/*  988 */       GlStateManager.func_179147_l();
/*  989 */       GlStateManager.func_179112_b(770, 771);
/*      */       
/*      */ 
/*  992 */       GL11.glPushMatrix();
/*  993 */       int start = side * 152;
/*      */       
/*  995 */       AspectList aspects = ri.getAspects();
/*  996 */       Object output = ri.getRecipeOutput();
/*  997 */       if ((ri instanceof InfusionRunicAugmentRecipe)) {
/*  998 */         ItemStack[] c = ((InfusionRunicAugmentRecipe)ri).getComponents((ItemStack)ri.getRecipeInput());
/*  999 */         components = c;
/* 1000 */         ArrayList<ItemStack> com = new ArrayList();
/* 1001 */         for (ItemStack s : c) com.add(s);
/* 1002 */         aspects = ri.getAspects(this.field_146297_k.field_71439_g, (ItemStack)ri.getRecipeInput(), com);
/* 1003 */         output = ((InfusionRunicAugmentRecipe)ri).getRecipeOutput(this.field_146297_k.field_71439_g, (ItemStack)ri.getRecipeInput(), com);
/*      */       }
/*      */       
/* 1006 */       if ((ri instanceof InfusionEnchantmentRecipe)) {
/* 1007 */         ArrayList<ItemStack> com = new ArrayList();
/* 1008 */         for (Object s : components) {
/* 1009 */           if ((s instanceof ItemStack)) com.add((ItemStack)s);
/*      */         }
/* 1011 */         aspects = ri.getAspects(this.field_146297_k.field_71439_g, (ItemStack)ri.getRecipeInput(), com);
/* 1012 */         output = ((InfusionEnchantmentRecipe)ri).getRecipeOutput(null, (ItemStack)ri.getRecipeInput(), com);
/*      */       }
/*      */       
/* 1015 */       String text = StatCollector.func_74838_a("recipe.type.infusion");
/* 1016 */       int offset = this.field_146289_q.func_78256_a(text);
/* 1017 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/* 1019 */       this.field_146297_k.field_71446_o.func_110577_a(this.tex2);
/*      */       
/* 1021 */       GL11.glPushMatrix();
/* 1022 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1023 */       GlStateManager.func_179147_l();
/* 1024 */       GlStateManager.func_179112_b(770, 771);
/* 1025 */       GL11.glTranslatef(x + start, y + 20, 0.0F);
/* 1026 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/* 1027 */       func_73729_b(0, 0, 0, 3, 56, 17);
/* 1028 */       GL11.glTranslatef(0.0F, 19.0F, 0.0F);
/* 1029 */       func_73729_b(0, 0, 200, 77, 60, 44);
/* 1030 */       GL11.glPopMatrix();
/*      */       
/* 1032 */       int mposx = mx;
/* 1033 */       int mposy = my;
/*      */       
/* 1035 */       int total = 0;
/* 1036 */       int rows = (aspects.size() - 1) / 5;
/* 1037 */       int shift = (5 - aspects.size() % 5) * 10;
/* 1038 */       int sx = x + start + 8;
/* 1039 */       int sy = y + 164 - 10 * rows;
/* 1040 */       for (Aspect tag : aspects.getAspectsSortedByName()) {
/* 1041 */         int m = 0;
/* 1042 */         if ((total / 5 >= rows) && ((rows > 1) || (aspects.size() < 5))) m = 1;
/* 1043 */         int vx = sx + total % 5 * 20 + shift * m;
/* 1044 */         int vy = sy + total / 5 * 20;
/* 1045 */         UtilsFX.drawTag(vx, vy, tag, aspects.getAmount(tag), 0, this.field_73735_i);
/* 1046 */         total++;
/*      */       }
/*      */       
/* 1049 */       ItemStack idisp = null;
/* 1050 */       if ((output instanceof ItemStack)) {
/* 1051 */         idisp = InventoryUtils.cycleItemStack((ItemStack)output);
/*      */       } else {
/* 1053 */         idisp = InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_77946_l();
/*      */         try {
/* 1055 */           Object[] obj = (Object[])output;
/* 1056 */           NBTBase tag = (NBTBase)obj[1];
/* 1057 */           idisp.func_77983_a((String)obj[0], tag);
/*      */         }
/*      */         catch (Exception e) {}
/*      */       }
/* 1061 */       drawStackAt(idisp, x + 48 + start, y + 28, mx, my, false);
/*      */       
/* 1063 */       ItemStack rinp = InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_77946_l();
/*      */       
/* 1065 */       drawStackAt(rinp, x + 48 + start, y + 94, mx, my, true);
/*      */       
/*      */ 
/* 1068 */       GL11.glPushMatrix();
/* 1069 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1070 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/* 1072 */       int le = components.length;
/* 1073 */       ArrayList<Coord2D> coords = new ArrayList();
/* 1074 */       float pieSlice = 360 / le;
/* 1075 */       float currentRot = -90.0F;
/* 1076 */       for (int a = 0; a < le; a++) {
/* 1077 */         int xx = (int)(MathHelper.func_76134_b(currentRot / 180.0F * 3.1415927F) * 40.0F) - 8;
/* 1078 */         int yy = (int)(MathHelper.func_76126_a(currentRot / 180.0F * 3.1415927F) * 40.0F) - 8;
/* 1079 */         currentRot += pieSlice;
/* 1080 */         coords.add(new Coord2D(xx, yy));
/*      */       }
/*      */       
/* 1083 */       ArrayList<ItemStack> cmps = new ArrayList();
/*      */       
/* 1085 */       total = 0;
/* 1086 */       sx = x + 56 + start;
/* 1087 */       sy = y + 102;
/*      */       
/*      */ 
/* 1090 */       for (Object ingredient : components) {
/* 1091 */         int vx = sx + ((Coord2D)coords.get(total)).x;
/* 1092 */         int vy = sy + ((Coord2D)coords.get(total)).y;
/* 1093 */         ItemStack is = InventoryUtils.cycleItemStack(ingredient);
/* 1094 */         drawStackAt(is.func_77946_l().func_77979_a(1), vx, vy, mx, my, true);
/* 1095 */         total++;
/* 1096 */         cmps.add(is.func_77946_l());
/*      */       }
/*      */       
/* 1099 */       GL11.glPopMatrix();
/*      */       
/* 1101 */       int inst = Math.min(5, ri.getInstability(this.field_146297_k.field_71439_g, rinp, cmps) / 2);
/* 1102 */       text = StatCollector.func_74838_a("tc.inst") + " " + StatCollector.func_74838_a(new StringBuilder().append("tc.inst.").append(inst).toString());
/*      */       
/* 1104 */       offset = this.field_146289_q.func_78256_a(text);
/* 1105 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 194, 5263440);
/*      */       
/* 1107 */       total = 0;
/* 1108 */       rows = (aspects.size() - 1) / 5;
/* 1109 */       shift = (5 - aspects.size() % 5) * 10;
/* 1110 */       sx = x + start + 8;
/* 1111 */       sy = y + 164 - 10 * rows;
/* 1112 */       for (Aspect tag : aspects.getAspectsSortedByName()) {
/* 1113 */         int m = 0;
/* 1114 */         if ((total / 5 >= rows) && ((rows > 1) || (aspects.size() < 5))) m = 1;
/* 1115 */         int vx = sx + total % 5 * 20 + shift * m;
/* 1116 */         int vy = sy + total / 5 * 20;
/*      */         
/* 1118 */         if ((mposx >= vx) && (mposy >= vy) && (mposx < vx + 16) && (mposy < vy + 16)) {
/* 1119 */           this.tipText = Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() });
/*      */         }
/*      */         
/* 1122 */         total++;
/*      */       }
/* 1124 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void func_73869_a(char par1, int par2)
/*      */     throws IOException
/*      */   {
/* 1246 */     if ((par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) || (par2 == 1)) {
/* 1247 */       history.clear();
/* 1248 */       this.field_146297_k.func_147108_a(new GuiResearchBrowser(this.guiMapX, this.guiMapY));
/* 1249 */     } else if ((par2 == 203) || (par2 == 200) || (par2 == 201)) {
/* 1250 */       prevPage();
/* 1251 */     } else if ((par2 == 205) || (par2 == 208) || (par2 == 209)) {
/* 1252 */       nextPage(); }
/* 1253 */     if (par2 == 14) {
/* 1254 */       goBack();
/*      */     }
/*      */     else {
/* 1257 */       super.func_73869_a(par1, par2);
/*      */     }
/*      */   }
/*      */   
/*      */   private void nextPage() {
/* 1262 */     if (this.page < this.maxPages - 2) {
/* 1263 */       this.page += 2;
/* 1264 */       this.lastCycle = 0L;
/* 1265 */       this.cycle = -1;
/* 1266 */       Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66F, 1.0F, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void prevPage()
/*      */   {
/* 1273 */     if (this.page >= 2) {
/* 1274 */       this.page -= 2;
/* 1275 */       this.lastCycle = 0L;
/* 1276 */       this.cycle = -1;
/* 1277 */       Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66F, 1.0F, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void goBack()
/*      */   {
/* 1284 */     if (!history.isEmpty()) {
/* 1285 */       Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66F, 1.0F, false);
/*      */       
/*      */ 
/* 1288 */       Object[] o = (Object[])history.pop();
/* 1289 */       this.field_146297_k.func_147108_a(new GuiResearchRecipe(ResearchCategories.getResearch((String)o[0]), ((Integer)o[1]).intValue(), this.guiMapX, this.guiMapY));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/* 1294 */   ArrayList<List> reference = new ArrayList();
/*      */   
/*      */ 
/*      */   protected void func_73864_a(int par1, int par2, int par3)
/*      */   {
/* 1299 */     int var4 = (this.field_146294_l - this.paneWidth) / 2;
/* 1300 */     int var5 = (this.field_146295_m - this.paneHeight) / 2;
/*      */     
/*      */ 
/* 1303 */     int mx = par1 - (var4 + 261);
/* 1304 */     int my = par2 - (var5 + 189);
/* 1305 */     if ((mx >= 0) && (my >= 0) && (mx < 14) && (my < 10)) {
/* 1306 */       nextPage();
/*      */     }
/*      */     
/*      */ 
/* 1310 */     mx = par1 - (var4 - 17);
/* 1311 */     my = par2 - (var5 + 189);
/* 1312 */     if ((mx >= 0) && (my >= 0) && (mx < 14) && (my < 10)) {
/* 1313 */       prevPage();
/*      */     }
/*      */     
/*      */ 
/* 1317 */     mx = par1 - (var4 + 118);
/* 1318 */     my = par2 - (var5 + 189);
/* 1319 */     if ((mx >= 0) && (my >= 0) && (mx < 20) && (my < 12)) {
/* 1320 */       goBack();
/*      */     }
/*      */     
/*      */ 
/* 1324 */     if (this.reference.size() > 0) {
/* 1325 */       for (List coords : this.reference) {
/* 1326 */         if ((par1 >= ((Integer)coords.get(0)).intValue()) && (par2 >= ((Integer)coords.get(1)).intValue()) && (par1 < ((Integer)coords.get(0)).intValue() + 16) && (par2 < ((Integer)coords.get(1)).intValue() + 16))
/*      */         {
/* 1328 */           Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66F, 1.0F, false);
/*      */           
/*      */ 
/* 1331 */           history.push(new Object[] { this.research.key, Integer.valueOf(this.page) });
/* 1332 */           this.field_146297_k.func_147108_a(new GuiResearchRecipe(ResearchCategories.getResearch((String)coords.get(2)), ((Integer)coords.get(3)).intValue(), this.guiMapX, this.guiMapY));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1342 */       super.func_73864_a(par1, par2, par3);
/*      */     } catch (IOException e) {}
/*      */   }
/*      */   
/* 1346 */   private int cycle = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean func_73868_f()
/*      */   {
/* 1354 */     return false;
/*      */   }
/*      */   
/*      */   public Object[] findRecipeReference(ItemStack item) {
/* 1358 */     return thaumcraft.api.ThaumcraftApi.getCraftingRecipeKey(this.field_146297_k.field_71439_g, item);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void drawStackAt(ItemStack itemstack, int x, int y, int mx, int my, boolean clickthrough)
/*      */   {
/* 1367 */     UtilsFX.renderItemStack(this.field_146297_k, itemstack, x, y, null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1393 */     if ((mx >= x) && (my >= y) && (mx < x + 16) && (my < y + 16) && (itemstack != null) && (itemstack.func_77973_b() != null))
/*      */     {
/* 1395 */       if (clickthrough) {
/* 1396 */         List addtext = itemstack.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/* 1397 */         Object[] ref = findRecipeReference(itemstack);
/* 1398 */         if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/* 1399 */           addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/* 1400 */           this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */         }
/* 1402 */         this.tipText = addtext;
/*      */       } else {
/* 1404 */         this.tipText = itemstack.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/* 1409 */   List tipText = null;
/*      */   
/*      */   public void drawTexturedModalRectScaled(int par1, int par2, int par3, int par4, int par5, int par6, float scale)
/*      */   {
/* 1413 */     GL11.glPushMatrix();
/* 1414 */     float var7 = 0.00390625F;
/* 1415 */     float var8 = 0.00390625F;
/* 1416 */     Tessellator var9 = Tessellator.func_178181_a();
/* 1417 */     GL11.glTranslatef(par1 + par5 / 2.0F, par2 + par6 / 2.0F, 0.0F);
/* 1418 */     GL11.glScalef(1.0F + scale, 1.0F + scale, 1.0F);
/* 1419 */     var9.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1420 */     var9.func_178180_c().func_181662_b(-par5 / 2.0F, par6 / 2.0F, this.field_73735_i).func_181673_a((par3 + 0) * var7, (par4 + par6) * var8).func_181675_d();
/* 1421 */     var9.func_178180_c().func_181662_b(par5 / 2.0F, par6 / 2.0F, this.field_73735_i).func_181673_a((par3 + par5) * var7, (par4 + par6) * var8).func_181675_d();
/* 1422 */     var9.func_178180_c().func_181662_b(par5 / 2.0F, -par6 / 2.0F, this.field_73735_i).func_181673_a((par3 + par5) * var7, (par4 + 0) * var8).func_181675_d();
/* 1423 */     var9.func_178180_c().func_181662_b(-par5 / 2.0F, -par6 / 2.0F, this.field_73735_i).func_181673_a((par3 + 0) * var7, (par4 + 0) * var8).func_181675_d();
/* 1424 */     var9.func_78381_a();
/* 1425 */     GL11.glPopMatrix();
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiResearchRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */