/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketAspectCombinationToServer;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketAspectPlaceToServer;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ResearchManager.HexEntry;
/*     */ import thaumcraft.common.lib.research.ResearchNoteData;
/*     */ import thaumcraft.common.lib.utils.HexUtils.Hex;
/*     */ import thaumcraft.common.lib.utils.HexUtils.Pixel;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*     */ 
/*     */ @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */ public class GuiResearchTable extends GuiContainer
/*     */ {
/*     */   private static boolean RESEARCHER_1;
/*     */   private static boolean RESEARCHER_2;
/*  51 */   private final int HEX_SIZE = 9;
/*     */   
/*     */   private float xSize_lo;
/*     */   
/*     */   private float ySize_lo;
/*     */   
/*  57 */   private long butcount1 = 0L;
/*  58 */   private long butcount2 = 0L;
/*  59 */   private int page = 0;
/*  60 */   private int lastPage = 0;
/*     */   
/*  62 */   private int isMouseButtonDown = 0;
/*     */   
/*     */   private TileResearchTable tileEntity;
/*     */   
/*     */   private FontRenderer galFontRenderer;
/*     */   
/*     */   private String username;
/*     */   
/*     */   EntityPlayer player;
/*  71 */   public Aspect select1 = null;
/*  72 */   public Aspect select2 = null;
/*  73 */   private AspectList aspectlist = new AspectList();
/*     */   
/*  75 */   private HashMap<String, Rune> runes = new HashMap();
/*     */   
/*     */   private class Rune {
/*     */     int q;
/*     */     int r;
/*     */     long start;
/*     */     long decay;
/*     */     int rune;
/*     */     
/*  84 */     public Rune(int q, int r, long start, long decay, int rune) { this.q = q;
/*  85 */       this.r = r;
/*  86 */       this.start = start;
/*  87 */       this.decay = decay;
/*  88 */       this.rune = rune;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public GuiResearchTable(EntityPlayer player, TileResearchTable e)
/*     */   {
/*  95 */     super(new thaumcraft.common.container.ContainerResearchTable(player.field_71071_by, e));
/*     */     
/*  97 */     this.tileEntity = e;
/*  98 */     this.field_146999_f = 255;
/*  99 */     this.field_147000_g = 255;
/* 100 */     this.galFontRenderer = FMLClientHandler.instance().getClient().field_71464_q;
/*     */     
/* 102 */     this.username = player.func_70005_c_();
/* 103 */     this.player = player;
/*     */     
/* 105 */     RESEARCHER_1 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER1");
/* 106 */     RESEARCHER_2 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER2");
/*     */     
/* 108 */     int count = 0;
/* 109 */     for (Aspect aspect : Aspect.aspects.values()) {
/* 110 */       this.aspectlist.add(aspect, count);
/* 111 */       count++;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146979_b(int mx, int my)
/*     */   {
/* 123 */     Minecraft mc = Minecraft.func_71410_x();
/* 124 */     long time = System.nanoTime() / 1000000L;
/*     */   }
/*     */   
/*     */ 
/* 128 */   private float popupScale = 0.05F;
/*     */   
/*     */   private Aspect draggedAspect;
/*     */   
/* 132 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_research_table.png");
/* 133 */   ResourceLocation texback = new ResourceLocation("thaumcraft", "textures/aspects/_back.png");
/* 134 */   ResourceLocation texparch = new ResourceLocation("thaumcraft", "textures/research/parchment3.png");
/* 135 */   ResourceLocation texh1 = new ResourceLocation("thaumcraft", "textures/gui/hex1.png");
/* 136 */   ResourceLocation texh2 = new ResourceLocation("thaumcraft", "textures/gui/hex2.png");
/* 137 */   ResourceLocation texscript = new ResourceLocation("thaumcraft", "textures/misc/script.png");
/*     */   
/*     */ 
/*     */   public void func_73863_a(int mx, int my, float par3)
/*     */   {
/* 142 */     super.func_73863_a(mx, my, par3);
/* 143 */     this.xSize_lo = mx;
/* 144 */     this.ySize_lo = my;
/* 145 */     int var5 = this.field_147003_i;
/* 146 */     int var6 = this.field_147009_r;
/* 147 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 148 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/* 150 */     if ((this.note != null) && (RESEARCHER_1) && (!this.note.isComplete()) && (InventoryUtils.isPlayerCarrying(this.player, new ItemStack(ItemsTC.knowledgeFragment)) > 0)) {
/* 151 */       int var7 = mx - (gx + 40);
/* 152 */       int var8 = my - (gy + 6);
/* 153 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 20) && (var8 < 20))
/*     */       {
/* 155 */         UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { StatCollector.func_74838_a("tc.research.add") }), mx, my, 11);
/*     */       }
/*     */     }
/*     */     
/* 159 */     RenderHelper.func_74518_a();
/* 160 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 162 */     if (org.lwjgl.input.Mouse.isButtonDown(0))
/*     */     {
/* 164 */       int sx = gx + 10;
/* 165 */       int sy = gy + 40;
/*     */       
/* 167 */       if ((this.isMouseButtonDown == 0) && (mx >= sx) && (mx < sx + 80) && (my >= sy) && (my < sy + 80))
/*     */       {
/* 169 */         Aspect aspect = getClickedAspect(mx, my, gx, gy, false);
/* 170 */         if (aspect != null) {
/* 171 */           playButtonAspect();
/* 172 */           this.isMouseButtonDown = 1;
/* 173 */           this.draggedAspect = aspect;
/*     */         }
/*     */       }
/* 176 */       else if ((this.isMouseButtonDown == 1) && (this.draggedAspect != null)) {
/* 177 */         GL11.glEnable(3042);
/* 178 */         drawOrb(mx - 8, my - 8, this.draggedAspect.getColor());
/* 179 */         GL11.glDisable(3042);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 184 */       if ((this.isMouseButtonDown == 1) && (this.draggedAspect != null))
/*     */       {
/* 186 */         if (this.note != null) {
/* 187 */           int mouseX = mx - (gx + 169);
/* 188 */           int mouseY = my - (gy + 83);
/* 189 */           HexUtils.Hex hp = new HexUtils.Pixel(mouseX, mouseY).toHex(9);
/* 190 */           if ((this.note.hexEntries.containsKey(hp.toString())) && (((ResearchManager.HexEntry)this.note.hexEntries.get(hp.toString())).type == 0))
/*     */           {
/* 192 */             playButtonCombine();
/* 193 */             playButtonWrite();
/* 194 */             PacketHandler.INSTANCE.sendToServer(new PacketAspectPlaceToServer(this.player, (byte)hp.q, (byte)hp.r, this.tileEntity.func_174877_v(), this.draggedAspect));
/*     */             
/* 196 */             this.draggedAspect = null;
/*     */           }
/*     */         }
/*     */         
/* 200 */         if (this.draggedAspect != null) {
/* 201 */           boolean skip = false;
/* 202 */           int mouseX = mx - (gx + 20);
/* 203 */           int mouseY = my - (gy + 146);
/* 204 */           if ((mouseX >= -16) && (mouseY >= -16) && (mouseX < 16) && (mouseY < 16)) {
/* 205 */             playButtonAspect();
/* 206 */             this.select1 = this.draggedAspect;
/* 207 */             skip = true;
/*     */           }
/*     */           
/* 210 */           mouseX = mx - (gx + 79);
/* 211 */           mouseY = my - (gy + 146);
/* 212 */           if ((!skip) && (mouseX >= -16) && (mouseY >= -16) && (mouseX < 16) && (mouseY < 16)) {
/* 213 */             playButtonAspect();
/* 214 */             this.select2 = this.draggedAspect;
/* 215 */             skip = true;
/*     */           }
/* 217 */           if (!skip) {
/* 218 */             Aspect aspect = getClickedAspect(mx, my, gx, gy, false);
/*     */             
/* 220 */             if (aspect == this.draggedAspect)
/*     */             {
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
/* 237 */               if (this.select1 == null) {
/* 238 */                 this.select1 = this.draggedAspect;
/*     */               }
/* 240 */               else if (this.select2 == null) {
/* 241 */                 this.select2 = this.draggedAspect;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 250 */       this.isMouseButtonDown = 0;
/* 251 */       this.draggedAspect = null;
/*     */     }
/*     */     
/*     */ 
/* 255 */     drawAspectText(var5 + 10, var6 + 40, mx, my);
/*     */     
/* 257 */     if ((this.note != null) && ((this.tileEntity.func_70301_a(0) == null) || (this.tileEntity.func_70301_a(0).func_77952_i() == this.tileEntity.func_70301_a(0).func_77958_k())))
/*     */     {
/* 259 */       int sx = Math.max(this.field_146289_q.func_78256_a(StatCollector.func_74838_a("tile.researchtable.noink.0")), this.field_146289_q.func_78256_a(StatCollector.func_74838_a("tile.researchtable.noink.1"))) / 2;
/*     */       
/* 261 */       UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { StatCollector.func_74838_a("tile.researchtable.noink.0"), StatCollector.func_74838_a("tile.researchtable.noink.1") }), gx + 157 - sx, gy + 84, 11);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 268 */   ItemStack kf = new ItemStack(ItemsTC.knowledgeFragment);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146976_a(float partialTicks, int par2, int par3)
/*     */   {
/* 275 */     int var5 = this.field_147003_i;
/* 276 */     int var6 = this.field_147009_r;
/*     */     
/* 278 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 279 */     GL11.glEnable(3042);
/* 280 */     GL11.glBlendFunc(770, 771);
/* 281 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 282 */     func_73729_b(var5, var6, 0, 0, 255, 167);
/* 283 */     func_73729_b(var5 + 40, var6 + 167, 0, 166, 184, 88);
/*     */     
/* 285 */     if ((RESEARCHER_1) && (this.note != null) && (!this.note.isComplete()) && (InventoryUtils.isPlayerCarrying(this.player, new ItemStack(ItemsTC.knowledgeFragment)) > 0)) {
/* 286 */       GlStateManager.func_179094_E();
/* 287 */       GlStateManager.func_179109_b(var5 + 51, var6 + 18, 0.0F);
/* 288 */       float h = MathHelper.func_76126_a((this.player.field_70173_aa + partialTicks) / 4.0F) * 0.2F;
/* 289 */       GlStateManager.func_179152_a(1.0F + h, 1.0F + h, 1.0F + h);
/* 290 */       this.field_146296_j.func_180450_b(this.kf, -8, -8);
/* 291 */       GlStateManager.func_179121_F();
/*     */     }
/*     */     
/* 294 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*     */     
/* 296 */     if (this.page < this.lastPage) {
/* 297 */       func_73729_b(var5 + 51, var6 + 121, 208, 208, 24, 8);
/*     */     }
/*     */     
/* 300 */     if (this.page > 0) {
/* 301 */       func_73729_b(var5 + 27, var6 + 121, 184, 208, 24, 8);
/*     */     }
/*     */     
/* 304 */     if ((this.butcount2 < System.nanoTime()) && (this.select1 != null) && (this.select2 != null)) {
/* 305 */       func_73729_b(var5 + 35, var6 + 139, 184, 184, 32, 16);
/* 306 */       drawOrb(var5 + 43, var6 + 139);
/*     */     }
/* 308 */     else if ((this.butcount2 >= System.nanoTime()) && (this.select1 != null) && (this.select2 != null)) {
/* 309 */       func_73729_b(var5 + 35, var6 + 139, 184, 184, 32, 16);
/* 310 */       func_73729_b(var5 + 35, var6 + 139, 184, 168, 32, 16);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 315 */     if ((this.tileEntity.data != null) && (this.tileEntity.data.aspects != null)) {
/* 316 */       drawAspects(var5 + 10, var6 + 40);
/*     */     } else {
/* 318 */       this.select1 = null;
/* 319 */       this.select2 = null;
/*     */     }
/*     */     
/* 322 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 323 */     RenderHelper.func_74518_a();
/*     */     
/* 325 */     drawResearchData(var5, var6, par2, par3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void drawAspects(int x, int y)
/*     */   {
/* 332 */     GL11.glPushMatrix();
/* 333 */     AspectList aspects = this.tileEntity.data.aspects;
/* 334 */     int count = aspects.size();
/* 335 */     this.lastPage = ((count - 20) / 5);
/* 336 */     count = 0;
/* 337 */     int drawn = 0;
/* 338 */     for (Aspect aspect : aspects.getAspectsSortedByName()) {
/* 339 */       count++;
/* 340 */       if ((count - 1 >= this.page * 5) && 
/* 341 */         (drawn < 25)) {
/* 342 */         int xx = drawn / 5 * 16;
/* 343 */         int yy = drawn % 5 * 16;
/* 344 */         UtilsFX.drawTag(x + xx, y + yy, aspect, aspects.getAmount(aspect), 0, this.field_73735_i, 771, 1.0F);
/* 345 */         drawn++;
/*     */       }
/*     */     }
/*     */     
/* 349 */     if ((this.select1 != null) && (aspects.getAmount(this.select1) <= 0)) {
/* 350 */       this.select1 = null;
/*     */     }
/*     */     
/* 353 */     if ((this.select2 != null) && (aspects.getAmount(this.select2) <= 0)) {
/* 354 */       this.select2 = null;
/*     */     }
/*     */     
/* 357 */     if (this.select1 != null) {
/* 358 */       UtilsFX.drawTag(x + 3, y + 99, this.select1, 0.0F, 0, this.field_73735_i);
/*     */     }
/* 360 */     if (this.select2 != null) {
/* 361 */       UtilsFX.drawTag(x + 61, y + 99, this.select2, 0.0F, 0, this.field_73735_i);
/*     */     }
/* 363 */     GL11.glPopMatrix();
/* 364 */     GL11.glDisable(2896);
/*     */   }
/*     */   
/*     */   private void drawAspectText(int x, int y, int mx, int my) {
/* 368 */     int var7 = 0;
/* 369 */     int var8 = 0;
/*     */     
/* 371 */     if ((this.tileEntity.data != null) && (this.tileEntity.data.aspects != null)) {
/* 372 */       AspectList aspects = this.tileEntity.data.aspects;
/* 373 */       int count = 0;
/* 374 */       int drawn = 0;
/* 375 */       for (Aspect aspect : aspects.getAspectsSortedByName())
/*     */       {
/* 377 */         count++;
/* 378 */         if ((count - 1 >= this.page * 5) && 
/* 379 */           (drawn < 25)) {
/* 380 */           int xx = drawn / 5 * 16;
/* 381 */           int yy = drawn % 5 * 16;
/* 382 */           var7 = mx - (x + xx);
/* 383 */           var8 = my - (y + yy);
/* 384 */           if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */           {
/* 386 */             UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { aspect.getName(), aspect.getLocalizedDescription() }), mx, my - 8, 11);
/*     */             
/*     */ 
/* 389 */             if ((RESEARCHER_1) && (!aspect.isPrimal())) {
/* 390 */               GL11.glPushMatrix();
/* 391 */               GL11.glEnable(3042);
/* 392 */               GL11.glBlendFunc(770, 771);
/* 393 */               this.field_146297_k.field_71446_o.func_110577_a(this.texback);
/* 394 */               GL11.glPushMatrix();
/* 395 */               GL11.glTranslated(mx + 6, my + 6, 0.0D);
/* 396 */               GL11.glScaled(1.25D, 1.25D, 0.0D);
/* 397 */               UtilsFX.drawTexturedQuadFull(0, 0, 0.0D);
/* 398 */               GL11.glPopMatrix();
/* 399 */               GL11.glPushMatrix();
/* 400 */               GL11.glTranslated(mx + 24, my + 6, 0.0D);
/* 401 */               GL11.glScaled(1.25D, 1.25D, 0.0D);
/* 402 */               UtilsFX.drawTexturedQuadFull(0, 0, 0.0D);
/* 403 */               GL11.glPopMatrix();
/* 404 */               UtilsFX.drawTag(mx + 26, my + 8, aspect.getComponents()[1], 0.0F, 0, 0.0D);
/* 405 */               UtilsFX.drawTag(mx + 8, my + 8, aspect.getComponents()[0], 0.0F, 0, 0.0D);
/* 406 */               GL11.glDisable(3042);
/* 407 */               GL11.glDisable(2896);
/* 408 */               GL11.glPopMatrix();
/*     */             }
/* 410 */             return;
/*     */           }
/* 412 */           drawn++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 417 */     if (this.select1 != null) {
/* 418 */       var7 = mx - (x + 3);
/* 419 */       var8 = my - (y + 99);
/* 420 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/* 422 */         UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { this.select1.getName(), this.select1.getLocalizedDescription() }), mx, my - 8, 11);
/*     */         
/*     */ 
/* 425 */         return;
/*     */       }
/*     */     }
/* 428 */     if (this.select2 != null) {
/* 429 */       var7 = mx - (x + 61);
/* 430 */       var8 = my - (y + 99);
/* 431 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/* 433 */         UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { this.select2.getName(), this.select2.getLocalizedDescription() }), mx, my - 8, 11);
/*     */         
/*     */ 
/* 436 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class Coord2D
/*     */   {
/*     */     int x;
/*     */     int y;
/*     */     
/*     */     Coord2D(int x, int y) {
/* 447 */       this.x = x;
/* 448 */       this.y = y;
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawResearchData(int x, int y, int mx, int my) {
/* 453 */     GL11.glPushMatrix();
/* 454 */     GL11.glEnable(3042);
/* 455 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 457 */     GlStateManager.func_179140_f();
/*     */     
/* 459 */     drawSheet(x, y, mx, my);
/*     */     
/* 461 */     GlStateManager.func_179145_e();
/* 462 */     RenderHelper.func_74518_a();
/*     */     
/* 464 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawHex(HexUtils.Hex hex, int x, int y) {
/* 468 */     GL11.glPushMatrix();
/* 469 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 470 */     GL11.glEnable(3042);
/* 471 */     GL11.glBlendFunc(770, 771);
/* 472 */     this.field_146297_k.field_71446_o.func_110577_a(this.texh1);
/* 473 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.25F);
/* 474 */     HexUtils.Pixel pix = hex.toPixel(9);
/* 475 */     GL11.glTranslated(x + pix.x, y + pix.y, 0.0D);
/*     */     
/* 477 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 478 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181711_k);
/* 479 */     int i = 240;
/* 480 */     int j = i >> 16 & 0xFFFF;
/* 481 */     int k = i & 0xFFFF;
/* 482 */     tessellator.func_178180_c().func_181662_b(-8.0D, 8.0D, this.field_73735_i).func_181673_a(0.0D, 1.0D).func_181671_a(j, k).func_181666_a(1.0F, 1.0F, 1.0F, 0.25F).func_181675_d();
/* 483 */     tessellator.func_178180_c().func_181662_b(8.0D, 8.0D, this.field_73735_i).func_181673_a(1.0D, 1.0D).func_181671_a(j, k).func_181666_a(1.0F, 1.0F, 1.0F, 0.25F).func_181675_d();
/* 484 */     tessellator.func_178180_c().func_181662_b(8.0D, -8.0D, this.field_73735_i).func_181673_a(1.0D, 0.0D).func_181671_a(j, k).func_181666_a(1.0F, 1.0F, 1.0F, 0.25F).func_181675_d();
/* 485 */     tessellator.func_178180_c().func_181662_b(-8.0D, -8.0D, this.field_73735_i).func_181673_a(0.0D, 0.0D).func_181671_a(j, k).func_181666_a(1.0F, 1.0F, 1.0F, 0.25F).func_181675_d();
/* 486 */     tessellator.func_78381_a();
/* 487 */     GL11.glAlphaFunc(516, 0.1F);
/* 488 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   private void drawHexHighlight(HexUtils.Hex hex, int x, int y)
/*     */   {
/* 494 */     GL11.glPushMatrix();
/* 495 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 496 */     GL11.glEnable(3042);
/* 497 */     GL11.glBlendFunc(770, 1);
/* 498 */     this.field_146297_k.field_71446_o.func_110577_a(this.texh2);
/* 499 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 500 */     HexUtils.Pixel pix = hex.toPixel(9);
/* 501 */     GL11.glTranslated(x + pix.x, y + pix.y, 0.0D);
/*     */     
/* 503 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 504 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181711_k);
/* 505 */     int i = 200;
/* 506 */     int j = i >> 16 & 0xFFFF;
/* 507 */     int k = i & 0xFFFF;
/* 508 */     tessellator.func_178180_c().func_181662_b(-8.0D, 8.0D, this.field_73735_i).func_181673_a(0.0D, 1.0D).func_181671_a(j, k).func_181666_a(1.0F, 1.0F, 1.0F, 0.6F).func_181675_d();
/* 509 */     tessellator.func_178180_c().func_181662_b(8.0D, 8.0D, this.field_73735_i).func_181673_a(1.0D, 1.0D).func_181671_a(j, k).func_181666_a(1.0F, 1.0F, 1.0F, 0.6F).func_181675_d();
/* 510 */     tessellator.func_178180_c().func_181662_b(8.0D, -8.0D, this.field_73735_i).func_181673_a(1.0D, 0.0D).func_181671_a(j, k).func_181666_a(1.0F, 1.0F, 1.0F, 0.6F).func_181675_d();
/* 511 */     tessellator.func_178180_c().func_181662_b(-8.0D, -8.0D, this.field_73735_i).func_181673_a(0.0D, 0.0D).func_181671_a(j, k).func_181666_a(1.0F, 1.0F, 1.0F, 0.6F).func_181675_d();
/* 512 */     tessellator.func_78381_a();
/* 513 */     GL11.glBlendFunc(770, 771);
/* 514 */     GL11.glAlphaFunc(516, 0.1F);
/* 515 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawLine(double x, double y, double x2, double y2)
/*     */   {
/* 520 */     int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
/* 521 */     float alpha = 0.3F + MathHelper.func_76126_a((float)(count + x)) * 0.3F + 0.3F;
/*     */     
/* 523 */     Tessellator var12 = Tessellator.func_178181_a();
/* 524 */     GL11.glPushMatrix();
/* 525 */     GL11.glLineWidth(3.0F);
/* 526 */     GL11.glDisable(3553);
/*     */     
/* 528 */     GL11.glBlendFunc(770, 1);
/* 529 */     var12.func_178180_c().func_181668_a(3, DefaultVertexFormats.field_181706_f);
/* 530 */     var12.func_178180_c().func_181662_b(x, y, 0.0D).func_181666_a(0.0F, 0.6F, 0.8F, alpha).func_181675_d();
/* 531 */     var12.func_178180_c().func_181662_b(x2, y2, 0.0D).func_181666_a(0.0F, 0.6F, 0.8F, alpha).func_181675_d();
/* 532 */     var12.func_78381_a();
/* 533 */     GL11.glBlendFunc(770, 771);
/* 534 */     GL11.glDisable(32826);
/* 535 */     GL11.glEnable(3553);
/* 536 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 537 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/* 541 */   public ResearchNoteData note = null;
/*     */   
/* 543 */   long lastRuneCheck = 0L;
/*     */   
/*     */   private void drawSheet(int x, int y, int mx, int my)
/*     */   {
/* 547 */     this.note = ResearchManager.getData(this.tileEntity.func_70301_a(1));
/*     */     
/* 549 */     if ((this.note == null) || (this.note.key == null) || (this.note.key.length() == 0)) {
/* 550 */       this.runes.clear();
/* 551 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 556 */     this.field_146297_k.field_71446_o.func_110577_a(this.texparch);
/* 557 */     func_73729_b(x + 94, y + 8, 0, 0, 150, 150);
/* 558 */     long time = System.currentTimeMillis();
/* 559 */     if (this.lastRuneCheck < time) {
/* 560 */       this.lastRuneCheck = (time + 250L);
/* 561 */       int k = this.field_146297_k.field_71441_e.field_73012_v.nextInt(120) - 60;
/* 562 */       int l = this.field_146297_k.field_71441_e.field_73012_v.nextInt(120) - 60;
/* 563 */       HexUtils.Hex hp = new HexUtils.Pixel(k, l).toHex(9);
/* 564 */       if ((!this.runes.containsKey(hp.toString())) && (!this.note.hexes.containsKey(hp.toString())))
/*     */       {
/* 566 */         this.runes.put(hp.toString(), new Rune(hp.q, hp.r, time, this.lastRuneCheck + 15000L + this.field_146297_k.field_71441_e.field_73012_v.nextInt(10000), this.field_146297_k.field_71441_e.field_73012_v.nextInt(16)));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 572 */     if (this.runes.size() > 0) {
/* 573 */       Rune[] rns = (Rune[])this.runes.values().toArray(new Rune[0]);
/* 574 */       for (int a = 0; a < rns.length; a++) {
/* 575 */         Rune rune = rns[a];
/* 576 */         if (rune.decay < time) {
/* 577 */           this.runes.remove(rune.q + ":" + rune.r);
/*     */         }
/*     */         else {
/* 580 */           HexUtils.Pixel pix = new HexUtils.Hex(rune.q, rune.r).toPixel(9);
/* 581 */           float progress = (float)(time - rune.start) / (float)(rune.decay - rune.start);
/* 582 */           float alpha = 0.5F;
/* 583 */           if (progress < 0.25F) {
/* 584 */             alpha = progress * 2.0F;
/*     */           }
/* 586 */           else if (progress > 0.5F) {
/* 587 */             alpha = 1.0F - progress;
/*     */           }
/* 589 */           drawRune(x + 169 + pix.x, y + 83 + pix.y, rune.rune, alpha * 0.66F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 595 */     int mouseX = mx - (x + 169);
/* 596 */     int mouseY = my - (y + 83);
/* 597 */     HexUtils.Hex hp = new HexUtils.Pixel(mouseX, mouseY).toHex(9);
/*     */     
/*     */ 
/* 600 */     this.lines.clear();
/* 601 */     this.checked.clear();
/* 602 */     this.highlight.clear();
/* 603 */     for (HexUtils.Hex hex : this.note.hexes.values()) {
/* 604 */       if (((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).type == 1) {
/* 605 */         checkConnections(hex);
/*     */       }
/*     */     }
/*     */     
/* 609 */     for (HexUtils.Hex[] con : this.lines.values()) {
/* 610 */       HexUtils.Pixel p1 = con[0].toPixel(9);
/* 611 */       HexUtils.Pixel p2 = con[1].toPixel(9);
/* 612 */       drawLine(x + 169 + p1.x, y + 83 + p1.y, x + 169 + p2.x, y + 83 + p2.y);
/*     */     }
/*     */     
/* 615 */     this.field_146297_k.field_71446_o.func_110577_a(this.texh1);
/* 616 */     GL11.glPushMatrix();
/*     */     
/* 618 */     if (!this.note.isComplete()) {
/* 619 */       for (HexUtils.Hex hex : this.note.hexes.values()) {
/* 620 */         if (((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).type != 1) {
/* 621 */           if (!this.note.isComplete()) {
/* 622 */             if (hex.equals(hp)) {
/* 623 */               drawHexHighlight(hex, x + 169, y + 83);
/*     */             } else
/* 625 */               drawHex(hex, x + 169, y + 83);
/*     */           }
/*     */         } else {
/* 628 */           drawOrb(x + 161 + hex.toPixel(9).x, y + 75 + hex.toPixel(9).y);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 633 */     for (HexUtils.Hex hex : this.note.hexes.values()) {
/* 634 */       if ((((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).type == 1) || (this.highlight.contains(hex.toString()))) {
/* 635 */         HexUtils.Pixel pix = hex.toPixel(9);
/* 636 */         UtilsFX.drawTag(x + 161 + pix.x, y + 75 + pix.y, ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect, 0.0F, 0, this.field_73735_i, 771, 1.0F, false);
/* 637 */       } else if (((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).type == 2) {
/* 638 */         HexUtils.Pixel pix = hex.toPixel(9);
/* 639 */         UtilsFX.drawTag(x + 161 + pix.x, y + 75 + pix.y, ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect, 0.0F, 0, this.field_73735_i, 771, 0.66F, true);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 645 */     if (!this.note.isComplete()) {
/* 646 */       for (Iterator i$ = this.note.hexes.values().iterator(); i$.hasNext(); 
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
/* 671 */           goto 1520)
/*     */       {
/* 646 */         HexUtils.Hex hex = (HexUtils.Hex)i$.next();
/* 647 */         if ((((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect != null) && (hex.equals(hp))) {
/* 648 */           Aspect aspect = ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect;
/* 649 */           UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { aspect.getName(), aspect.getLocalizedDescription() }), mx, my - 8, 11);
/*     */           
/*     */ 
/* 652 */           if ((!RESEARCHER_1) || (aspect.isPrimal())) break;
/* 653 */           GL11.glPushMatrix();
/* 654 */           GL11.glEnable(3042);
/* 655 */           GL11.glBlendFunc(770, 771);
/* 656 */           this.field_146297_k.field_71446_o.func_110577_a(this.texback);
/* 657 */           GL11.glPushMatrix();
/* 658 */           GL11.glTranslated(mx + 6, my + 6, 0.0D);
/* 659 */           GL11.glScaled(1.25D, 1.25D, 0.0D);
/* 660 */           UtilsFX.drawTexturedQuadFull(0, 0, 0.0D);
/* 661 */           GL11.glPopMatrix();
/* 662 */           GL11.glPushMatrix();
/* 663 */           GL11.glTranslated(mx + 24, my + 6, 0.0D);
/* 664 */           GL11.glScaled(1.25D, 1.25D, 0.0D);
/* 665 */           UtilsFX.drawTexturedQuadFull(0, 0, 0.0D);
/* 666 */           GL11.glPopMatrix();
/* 667 */           UtilsFX.drawTag(mx + 26, my + 8, aspect.getComponents()[1], 0.0F, 0, 0.0D);
/* 668 */           UtilsFX.drawTag(mx + 8, my + 8, aspect.getComponents()[0], 0.0F, 0, 0.0D);
/* 669 */           GL11.glDisable(3042);
/* 670 */           GL11.glDisable(2896);
/* 671 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 680 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 687 */   private HashMap<String, HexUtils.Hex[]> lines = new HashMap();
/* 688 */   private ArrayList<String> checked = new ArrayList();
/* 689 */   private ArrayList<String> highlight = new ArrayList();
/*     */   
/*     */   private void checkConnections(HexUtils.Hex hex) {
/* 692 */     this.checked.add(hex.toString());
/* 693 */     for (int a = 0; a < 6; a++) {
/* 694 */       HexUtils.Hex target = hex.getNeighbour(a);
/* 695 */       if ((!this.checked.contains(target.toString())) && 
/* 696 */         (this.note.hexEntries.containsKey(target.toString())) && (((ResearchManager.HexEntry)this.note.hexEntries.get(target.toString())).type >= 1))
/*     */       {
/* 698 */         Aspect aspect1 = ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect;
/* 699 */         Aspect aspect2 = ((ResearchManager.HexEntry)this.note.hexEntries.get(target.toString())).aspect;
/*     */         
/* 701 */         if (((!aspect1.isPrimal()) && ((aspect1.getComponents()[0] == aspect2) || (aspect1.getComponents()[1] == aspect2))) || ((!aspect2.isPrimal()) && ((aspect2.getComponents()[0] == aspect1) || (aspect2.getComponents()[1] == aspect1))))
/*     */         {
/* 703 */           String k1 = hex.toString() + ":" + target.toString();
/* 704 */           String k2 = target.toString() + ":" + hex.toString();
/* 705 */           if ((!this.lines.containsKey(k1)) && (!this.lines.containsKey(k2))) {
/* 706 */             this.lines.put(k1, new HexUtils.Hex[] { hex, target });
/* 707 */             this.highlight.add(target.toString());
/*     */           }
/* 709 */           checkConnections(target);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawRune(double x, double y, int rune, float alpha)
/*     */   {
/* 717 */     GL11.glPushMatrix();
/* 718 */     this.field_146297_k.field_71446_o.func_110577_a(this.texscript);
/* 719 */     GL11.glColor4f(0.0F, 0.0F, 0.0F, alpha);
/*     */     
/* 721 */     GL11.glTranslated(x, y, 0.0D);
/* 722 */     if (rune < 16) {
/* 723 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/*     */     }
/*     */     
/* 726 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 727 */     float var8 = 0.0625F * rune;
/* 728 */     float var9 = var8 + 0.0625F;
/* 729 */     float var10 = 0.0F;
/* 730 */     float var11 = 1.0F;
/* 731 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181709_i);
/* 732 */     tessellator.func_178180_c().func_181662_b(-5.0D, 5.0D, this.field_73735_i).func_181673_a(var9, var11).func_181666_a(0.0F, 0.0F, 0.0F, alpha).func_181675_d();
/* 733 */     tessellator.func_178180_c().func_181662_b(5.0D, 5.0D, this.field_73735_i).func_181673_a(var9, var10).func_181666_a(0.0F, 0.0F, 0.0F, alpha).func_181675_d();
/* 734 */     tessellator.func_178180_c().func_181662_b(5.0D, -5.0D, this.field_73735_i).func_181673_a(var8, var10).func_181666_a(0.0F, 0.0F, 0.0F, alpha).func_181675_d();
/* 735 */     tessellator.func_178180_c().func_181662_b(-5.0D, -5.0D, this.field_73735_i).func_181673_a(var8, var11).func_181666_a(0.0F, 0.0F, 0.0F, alpha).func_181675_d();
/* 736 */     tessellator.func_78381_a();
/* 737 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_73864_a(int mx, int my, int par3)
/*     */     throws java.io.IOException
/*     */   {
/* 745 */     super.func_73864_a(mx, my, par3);
/*     */     
/* 747 */     if ((this.butcount1 > System.nanoTime()) || (this.butcount2 > System.nanoTime())) { return;
/*     */     }
/* 749 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 750 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/* 753 */     int var7 = mx - (gx + 35);
/* 754 */     int var8 = my - (gy + 139);
/*     */     
/* 756 */     if ((var7 >= 0) && (var8 >= 0) && (var7 < 32) && (var8 < 16) && (this.butcount2 < System.nanoTime()) && (this.select1 != null) && (this.select2 != null))
/*     */     {
/*     */ 
/* 759 */       this.butcount2 = (System.nanoTime() + 200000000L);
/* 760 */       playButtonClick();
/* 761 */       playButtonCombine();
/* 762 */       PacketHandler.INSTANCE.sendToServer(new PacketAspectCombinationToServer(this.player, this.tileEntity.func_174877_v(), this.select1, this.select2));
/* 763 */       return;
/*     */     }
/*     */     
/*     */ 
/* 767 */     var7 = mx - (gx + 27);
/* 768 */     var8 = my - (gy + 121);
/* 769 */     if ((this.page > 0) && (var7 >= 0) && (var8 >= 0) && (var7 < 24) && (var8 < 8))
/*     */     {
/* 771 */       this.page -= 1;
/* 772 */       playButtonScroll();
/* 773 */       return;
/*     */     }
/*     */     
/* 776 */     var7 = mx - (gx + 51);
/* 777 */     var8 = my - (gy + 121);
/* 778 */     if ((this.page < this.lastPage) && (var7 >= 0) && (var8 >= 0) && (var7 < 24) && (var8 < 8))
/*     */     {
/* 780 */       this.page += 1;
/* 781 */       playButtonScroll();
/* 782 */       return;
/*     */     }
/*     */     
/* 785 */     if (this.select1 != null) {
/* 786 */       var7 = mx - (gx + 11);
/* 787 */       var8 = my - (gy + 137);
/* 788 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/* 790 */         this.select1 = null;
/* 791 */         playButtonAspect();
/* 792 */         return;
/*     */       }
/*     */     }
/* 795 */     if (this.select2 != null) {
/* 796 */       var7 = mx - (gx + 71);
/* 797 */       var8 = my - (gy + 137);
/* 798 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/* 800 */         this.select2 = null;
/* 801 */         playButtonAspect();
/* 802 */         return;
/*     */       }
/*     */     }
/*     */     
/* 806 */     if (this.note != null) {
/* 807 */       checkClickedHex(mx, my, gx, gy);
/*     */       
/* 809 */       if ((RESEARCHER_1) && (!this.note.isComplete()) && (InventoryUtils.isPlayerCarrying(this.player, new ItemStack(ItemsTC.knowledgeFragment)) > 0)) {
/* 810 */         var7 = mx - (gx + 40);
/* 811 */         var8 = my - (gy + 6);
/* 812 */         if ((var7 >= 0) && (var8 >= 0) && (var7 < 20) && (var8 < 20))
/*     */         {
/* 814 */           this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 5);
/* 815 */           playButtonClick();
/* 816 */           playButtonWrite();
/* 817 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 822 */     if ((func_146272_n()) && (RESEARCHER_2)) {
/* 823 */       Aspect aspect = getClickedAspect(mx, my, gx, gy, true);
/* 824 */       if ((aspect != null) && (!aspect.isPrimal()) && (this.tileEntity.data != null) && (this.tileEntity.data.aspects != null)) {
/* 825 */         AspectList aspects = this.tileEntity.data.aspects;
/* 826 */         if ((aspects != null) && (aspects.getAmount(aspect.getComponents()[0]) > 0) && (aspects.getAmount(aspect.getComponents()[1]) > 0))
/*     */         {
/*     */ 
/* 829 */           this.draggedAspect = null;
/* 830 */           playButtonCombine();
/* 831 */           PacketHandler.INSTANCE.sendToServer(new PacketAspectCombinationToServer(this.player, this.tileEntity.func_174877_v(), aspect.getComponents()[0], aspect.getComponents()[1]));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void checkClickedHex(int mx, int my, int gx, int gy)
/*     */   {
/* 841 */     int mouseX = mx - (gx + 169);
/* 842 */     int mouseY = my - (gy + 83);
/* 843 */     HexUtils.Hex hp = new HexUtils.Pixel(mouseX, mouseY).toHex(9);
/*     */     
/* 845 */     if ((this.note.hexes.containsKey(hp.toString())) && (((ResearchManager.HexEntry)this.note.hexEntries.get(hp.toString())).type == 2)) {
/* 846 */       playButtonCombine();
/* 847 */       playButtonErase();
/* 848 */       PacketHandler.INSTANCE.sendToServer(new PacketAspectPlaceToServer(this.player, (byte)hp.q, (byte)hp.r, this.tileEntity.func_174877_v(), null));
/*     */       
/* 850 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   private Aspect getClickedAspect(int mx, int my, int gx, int gy, boolean ignoreZero) {
/* 855 */     if ((this.tileEntity.data != null) && (this.tileEntity.data.aspects != null)) {
/* 856 */       AspectList aspects = this.tileEntity.data.aspects;
/* 857 */       int count = 0;
/* 858 */       int drawn = 0;
/* 859 */       for (Aspect aspect : aspects.getAspectsSortedByName()) {
/* 860 */         count++;
/* 861 */         if ((count - 1 >= this.page * 5) && 
/* 862 */           (drawn < 25)) {
/* 863 */           int xx = drawn / 5 * 16;
/* 864 */           int yy = drawn % 5 * 16;
/* 865 */           int var7 = mx - (gx + xx + 10);
/* 866 */           int var8 = my - (gy + yy + 40);
/* 867 */           if (((ignoreZero) || (aspects.getAmount(aspect) > 0)) && 
/* 868 */             (var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */           {
/* 870 */             return aspect;
/*     */           }
/*     */           
/* 873 */           drawn++;
/*     */         }
/*     */       }
/*     */     }
/* 877 */     return null;
/*     */   }
/*     */   
/*     */   private void playButtonClick() {
/* 881 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonAspect()
/*     */   {
/* 887 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:hhoff", 0.2F, 1.0F + this.field_146297_k.func_175606_aa().field_70170_p.field_73012_v.nextFloat() * 0.1F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonCombine()
/*     */   {
/* 893 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:hhon", 0.3F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonWrite()
/*     */   {
/* 899 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:write", 0.2F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonErase()
/*     */   {
/* 905 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:erase", 0.2F, 1.0F + this.field_146297_k.func_175606_aa().field_70170_p.field_73012_v.nextFloat() * 0.1F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonScroll()
/*     */   {
/* 911 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:key", 0.3F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void drawOrb(double x, double y)
/*     */   {
/* 917 */     int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
/* 918 */     float red = 0.7F + MathHelper.func_76126_a((float)((count + x) / 10.0D)) * 0.15F + 0.15F;
/* 919 */     float green = 0.7F + MathHelper.func_76126_a((float)((count + x + y) / 11.0D)) * 0.15F + 0.15F;
/* 920 */     float blue = 0.7F + MathHelper.func_76126_a((float)((count + y) / 12.0D)) * 0.15F + 0.15F;
/* 921 */     Color c = new Color(red, green, blue);
/* 922 */     drawOrb(x, y, c.getRGB());
/*     */   }
/*     */   
/*     */   private void drawOrb(double x, double y, int color)
/*     */   {
/* 927 */     int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
/* 928 */     Color c = new Color(color);
/* 929 */     float red = c.getRed() / 255.0F;
/* 930 */     float green = c.getGreen() / 255.0F;
/* 931 */     float blue = c.getBlue() / 255.0F;
/* 932 */     if (thaumcraft.common.config.Config.colorBlind) {
/* 933 */       red /= 1.8F;
/* 934 */       green /= 1.8F;
/* 935 */       blue /= 1.8F;
/*     */     }
/* 937 */     GL11.glPushMatrix();
/* 938 */     this.field_146297_k.field_71446_o.func_110577_a(thaumcraft.client.fx.ParticleEngine.particleTexture);
/* 939 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 940 */     GL11.glTranslated(x, y, 0.0D);
/* 941 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 942 */     int part = count % 8;
/* 943 */     float var8 = 0.5F + part / 8.0F;
/* 944 */     float var9 = var8 + 0.0624375F;
/* 945 */     float var10 = 0.5F;
/* 946 */     float var11 = var10 + 0.0624375F;
/* 947 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181711_k);
/* 948 */     int i = 240;
/* 949 */     int j = i >> 16 & 0xFFFF;
/* 950 */     int k = i & 0xFFFF;
/* 951 */     tessellator.func_178180_c().func_181662_b(0.0D, 16.0D, this.field_73735_i).func_181673_a(var9, var11).func_181671_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 952 */     tessellator.func_178180_c().func_181662_b(16.0D, 16.0D, this.field_73735_i).func_181673_a(var9, var10).func_181671_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 953 */     tessellator.func_178180_c().func_181662_b(16.0D, 0.0D, this.field_73735_i).func_181673_a(var8, var10).func_181671_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 954 */     tessellator.func_178180_c().func_181662_b(0.0D, 0.0D, this.field_73735_i).func_181673_a(var8, var11).func_181671_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 955 */     tessellator.func_78381_a();
/* 956 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiResearchTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */