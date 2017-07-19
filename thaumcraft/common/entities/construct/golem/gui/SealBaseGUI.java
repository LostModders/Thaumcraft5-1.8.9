/*     */ package thaumcraft.common.entities.construct.golem.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.client.gui.plugins.GuiHoverButton;
/*     */ import thaumcraft.client.gui.plugins.GuiPlusMinusButton;
/*     */ import thaumcraft.client.lib.CustomRenderItem;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class SealBaseGUI
/*     */   extends GuiContainer
/*     */ {
/*     */   ISealEntity seal;
/*     */   int middleX;
/*     */   int middleY;
/*  48 */   int category = -1;
/*     */   int[] categories;
/*     */   
/*     */   public SealBaseGUI(InventoryPlayer player, World world, ISealEntity seal)
/*     */   {
/*  53 */     super(new SealBaseContainer(player, world, seal));
/*     */     
/*  55 */     this.seal = seal;
/*  56 */     this.field_146999_f = 176;
/*  57 */     this.field_147000_g = 232;
/*  58 */     this.middleX = (this.field_146999_f / 2);
/*  59 */     this.middleY = ((this.field_147000_g - 72) / 2 - 8);
/*  60 */     if ((seal.getSeal() instanceof ISealGui)) {
/*  61 */       this.categories = ((ISealGui)seal.getSeal()).getGuiCategories();
/*     */     } else {
/*  63 */       this.categories = new int[] { 0, 4 };
/*     */     }
/*     */   }
/*     */   
/*     */   private ModelManager getModelmanager() {
/*     */     try {
/*  69 */       return (ModelManager)ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.func_71410_x(), new String[] { "modelManager", "field_175617_aL", "aP" });
/*     */     } catch (Exception e) {}
/*  71 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_73866_w_()
/*     */   {
/*  77 */     super.func_73866_w_();
/*  78 */     this.field_146296_j = new CustomRenderItem();
/*  79 */     setupCategories();
/*     */   }
/*     */   
/*     */   private void setupCategories() {
/*  83 */     this.field_146292_n.clear();
/*     */     
/*  85 */     int c = 0;
/*  86 */     float slice = 60.0F / this.categories.length;
/*  87 */     float start = -180.0F + (this.categories.length - 1) * slice / 2.0F;
/*  88 */     if (slice > 24.0F) slice = 24.0F;
/*  89 */     if (slice < 12.0F) slice = 12.0F;
/*  90 */     for (int cat : this.categories) {
/*  91 */       if (this.category < 0) this.category = cat;
/*  92 */       if (this.categories.length > 1) {
/*  93 */         int xx = (int)(MathHelper.func_76134_b((start - c * slice) / 180.0F * 3.1415927F) * 86.0F);
/*  94 */         int yy = (int)(MathHelper.func_76126_a((start - c * slice) / 180.0F * 3.1415927F) * 86.0F);
/*  95 */         this.field_146292_n.add(new GuiGolemCategoryButton(c, this.field_147003_i + this.middleX + xx, this.field_147009_r + this.middleY + yy, 16, 16, "button.category." + cat, cat, this.category == cat));
/*     */       }
/*     */       
/*  98 */       c++;
/*     */     }
/*     */     
/* 101 */     int xx = (int)(MathHelper.func_76134_b((start - c * slice) / 180.0F * 3.1415927F) * 86.0F);
/* 102 */     int yy = (int)(MathHelper.func_76126_a((start - c * slice) / 180.0F * 3.1415927F) * 86.0F);
/* 103 */     this.field_146292_n.add(new GuiGolemRedstoneButton(27, this.field_147003_i + this.middleX + xx - 8, this.field_147009_r + this.middleY + yy - 8, 16, 16, this.seal));
/*     */     
/* 105 */     switch (this.category) {
/*     */     case 0: 
/* 107 */       this.field_146292_n.add(new GuiPlusMinusButton(80, this.field_147003_i + this.middleX - 5 - 14, this.field_147009_r + this.middleY - 17, 10, 10, true));
/* 108 */       this.field_146292_n.add(new GuiPlusMinusButton(81, this.field_147003_i + this.middleX - 5 + 14, this.field_147009_r + this.middleY - 17, 10, 10, false));
/*     */       
/* 110 */       this.field_146292_n.add(new GuiPlusMinusButton(82, this.field_147003_i + this.middleX + 18 - 12, this.field_147009_r + this.middleY + 4, 10, 10, true));
/* 111 */       this.field_146292_n.add(new GuiPlusMinusButton(83, this.field_147003_i + this.middleX + 18 + 11, this.field_147009_r + this.middleY + 4, 10, 10, false));
/*     */       
/* 113 */       this.field_146292_n.add(new GuiGolemLockButton(25, this.field_147003_i + this.middleX - 32, this.field_147009_r + this.middleY, 16, 16, this.seal));
/* 114 */       break;
/*     */     case 1: 
/* 116 */       if ((this.seal.getSeal() instanceof ISealConfigFilter)) {
/* 117 */         int s = ((ISealConfigFilter)this.seal.getSeal()).getFilterSize();
/* 118 */         int sy = 16 + (s - 1) / 3 * 12;
/* 119 */         this.field_146292_n.add(new GuiGolemBWListButton(20, this.field_147003_i + this.middleX - 8, this.field_147009_r + this.middleY + (s - 1) / 3 * 24 - sy + 27, 16, 16, (ISealConfigFilter)this.seal.getSeal()));
/*     */       }
/* 121 */       break;
/*     */     
/*     */     case 2: 
/* 124 */       this.field_146292_n.add(new GuiPlusMinusButton(90, this.field_147003_i + this.middleX - 5 - 14, this.field_147009_r + this.middleY - 25, 10, 10, true));
/* 125 */       this.field_146292_n.add(new GuiPlusMinusButton(91, this.field_147003_i + this.middleX - 5 + 14, this.field_147009_r + this.middleY - 25, 10, 10, false));
/* 126 */       this.field_146292_n.add(new GuiPlusMinusButton(92, this.field_147003_i + this.middleX - 5 - 14, this.field_147009_r + this.middleY, 10, 10, true));
/* 127 */       this.field_146292_n.add(new GuiPlusMinusButton(93, this.field_147003_i + this.middleX - 5 + 14, this.field_147009_r + this.middleY, 10, 10, false));
/* 128 */       this.field_146292_n.add(new GuiPlusMinusButton(94, this.field_147003_i + this.middleX - 5 - 14, this.field_147009_r + this.middleY + 25, 10, 10, true));
/* 129 */       this.field_146292_n.add(new GuiPlusMinusButton(95, this.field_147003_i + this.middleX - 5 + 14, this.field_147009_r + this.middleY + 25, 10, 10, false));
/* 130 */       break;
/*     */     case 3: 
/* 132 */       if ((this.seal.getSeal() instanceof ISealConfigToggles)) {
/* 133 */         ISealConfigToggles cp = (ISealConfigToggles)this.seal.getSeal();
/* 134 */         int s = cp.getToggles().length < 9 ? 6 : cp.getToggles().length < 6 ? 7 : cp.getToggles().length < 4 ? 8 : 5;
/* 135 */         int h = (cp.getToggles().length - 1) * s;
/* 136 */         int w = 12;
/* 137 */         for (ISealConfigToggles.SealToggle prop : cp.getToggles()) {
/* 138 */           int ww = 12 + Math.min(100, this.field_146289_q.func_78256_a(StatCollector.func_74838_a(prop.getName())));
/* 139 */           ww /= 2;
/* 140 */           if (ww > w) w = ww;
/*     */         }
/* 142 */         int p = 0;
/* 143 */         for (ISealConfigToggles.SealToggle prop : cp.getToggles()) {
/* 144 */           this.field_146292_n.add(new GuiGolemPropButton(30 + p, this.field_147003_i + this.middleX - w, this.field_147009_r + this.middleY - 5 - h + p * (s * 2), 8, 8, prop.getName(), prop));
/* 145 */           p++;
/*     */         } }
/* 147 */       break;
/*     */     
/*     */     case 4: 
/* 150 */       EnumGolemTrait[] tags = this.seal.getSeal().getRequiredTags();
/* 151 */       if ((tags != null) && (tags.length > 0)) {
/* 152 */         int p = 0;
/* 153 */         for (EnumGolemTrait tag : tags) {
/* 154 */           this.field_146292_n.add(new GuiHoverButton(this, 500 + p, this.field_147003_i + this.middleX + p * 18 - (tags.length - 1) * 9, this.field_147009_r + this.middleY - 8, 16, 16, tag.getLocalizedName(), tag.getLocalizedDescription(), tag.icon));
/*     */           
/* 156 */           p++;
/*     */         }
/*     */       }
/*     */       
/* 160 */       tags = this.seal.getSeal().getForbiddenTags();
/* 161 */       if ((tags != null) && (tags.length > 0)) {
/* 162 */         int p = 0;
/* 163 */         for (EnumGolemTrait tag : tags) {
/* 164 */           this.field_146292_n.add(new GuiHoverButton(this, 600 + p, this.field_147003_i + this.middleX + p * 18 - (tags.length - 1) * 9, this.field_147009_r + this.middleY + 24, 16, 16, tag.getLocalizedName(), tag.getLocalizedDescription(), tag.icon));
/*     */           
/* 166 */           p++;
/*     */         }
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   protected boolean func_146983_a(int par1)
/*     */   {
/* 176 */     return false;
/*     */   }
/*     */   
/* 179 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*     */   
/*     */ 
/*     */   protected void func_146976_a(float par1, int mouseX, int mouseY)
/*     */   {
/* 184 */     GL11.glEnable(3042);
/* 185 */     GL11.glBlendFunc(770, 771);
/* 186 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 187 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 188 */     func_73729_b(this.field_147003_i + this.middleX - 80, this.field_147009_r + this.middleY - 80, 96, 0, 160, 160);
/* 189 */     func_73729_b(this.field_147003_i, this.field_147009_r + 143, 0, 167, 176, 89);
/*     */     
/* 191 */     func_73732_a(this.field_146289_q, StatCollector.func_74838_a("button.category." + this.category), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 64, 16777215);
/*     */     
/*     */ 
/* 194 */     GL11.glEnable(3042);
/* 195 */     GL11.glBlendFunc(770, 771);
/* 196 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 197 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 199 */     switch (this.category) {
/*     */     case 0: 
/* 201 */       func_73729_b(this.field_147003_i + this.middleX + 17, this.field_147009_r + this.middleY + 3, 2, 18, 12, 12);
/* 202 */       if ((this.seal.getColor() >= 1) && (this.seal.getColor() <= 16)) {
/* 203 */         Color c = new Color(EnumDyeColor.func_176764_b(this.seal.getColor() - 1).func_176768_e().field_76291_p);
/* 204 */         GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/* 205 */         func_73729_b(this.field_147003_i + this.middleX + 20, this.field_147009_r + this.middleY + 6, 74, 31, 6, 6);
/* 206 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */       
/* 209 */       int mx = mouseX - this.field_147003_i;
/* 210 */       int my = mouseY - this.field_147009_r;
/*     */       
/* 212 */       if ((mx >= this.middleX + 5) && (mx <= this.middleX + 41) && (my >= this.middleY + 3) && (my <= this.middleY + 15)) {
/* 213 */         if ((this.seal.getColor() >= 1) && (this.seal.getColor() <= 16)) {
/* 214 */           String s = "color." + EnumDyeColor.func_176764_b(this.seal.getColor() - 1).func_176610_l();
/* 215 */           String s2 = StatCollector.func_74838_a("golem.prop.color");
/* 216 */           s2 = s2.replace("%s", StatCollector.func_74838_a(s));
/* 217 */           func_73732_a(this.field_146289_q, s2, this.field_147003_i + this.middleX + 23, this.field_147009_r + this.middleY + 17, 16777215);
/*     */         } else {
/* 219 */           func_73732_a(this.field_146289_q, StatCollector.func_74838_a("golem.prop.colorall"), this.field_147003_i + this.middleX + 23, this.field_147009_r + this.middleY + 17, 16777215);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 224 */       func_73732_a(this.field_146289_q, StatCollector.func_74838_a("golem.prop.priority"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 28, 12299007);
/*     */       
/* 226 */       func_73732_a(this.field_146289_q, "" + this.seal.getPriority(), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 16, 16777215);
/*     */       
/*     */ 
/* 229 */       if (this.seal.getOwner().equals(this.field_146297_k.field_71439_g.func_110124_au().toString())) {
/* 230 */         func_73732_a(this.field_146289_q, StatCollector.func_74838_a("golem.prop.owner"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY + 32, 12299007);
/*     */       }
/*     */       break;
/*     */     case 1: 
/* 234 */       if ((this.seal.getSeal() instanceof ISealConfigFilter)) {
/* 235 */         int s = ((ISealConfigFilter)this.seal.getSeal()).getFilterSize();
/* 236 */         int sx = 16 + (s - 1) % 3 * 12;
/* 237 */         int sy = 16 + (s - 1) / 3 * 12;
/* 238 */         for (int a = 0; a < s; a++) {
/* 239 */           int x = a % 3;
/* 240 */           int y = a / 3;
/* 241 */           func_73729_b(this.field_147003_i + this.middleX + x * 24 - sx, this.field_147009_r + this.middleY + y * 24 - sy, 0, 56, 32, 32);
/*     */         } }
/* 243 */       break;
/*     */     
/*     */     case 2: 
/* 246 */       func_73732_a(this.field_146289_q, StatCollector.func_74838_a("button.caption.y"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 24 - 9, 14540253);
/* 247 */       func_73732_a(this.field_146289_q, StatCollector.func_74838_a("button.caption.x"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 9, 14540253);
/* 248 */       func_73732_a(this.field_146289_q, StatCollector.func_74838_a("button.caption.z"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY + 24 - 9, 14540253);
/*     */       
/* 250 */       func_73732_a(this.field_146289_q, "" + this.seal.getArea().func_177956_o(), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 24, 16777215);
/* 251 */       func_73732_a(this.field_146289_q, "" + this.seal.getArea().func_177958_n(), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY, 16777215);
/* 252 */       func_73732_a(this.field_146289_q, "" + this.seal.getArea().func_177952_p(), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY + 24, 16777215);
/* 253 */       break;
/*     */     case 4: 
/* 255 */       func_73732_a(this.field_146289_q, StatCollector.func_74838_a("button.caption.required"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 26, 14540253);
/* 256 */       func_73732_a(this.field_146289_q, StatCollector.func_74838_a("button.caption.forbidden"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY + 6, 14540253);
/*     */     }
/*     */     
/*     */     
/* 260 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_146979_b(int mouseX, int mouseY)
/*     */   {
/* 266 */     RenderHelper.func_74518_a();
/* 267 */     Iterator iterator = this.field_146292_n.iterator();
/* 268 */     while (iterator.hasNext())
/*     */     {
/* 270 */       GuiButton guibutton = (GuiButton)iterator.next();
/* 271 */       if (guibutton.func_146115_a())
/*     */       {
/* 273 */         guibutton.func_146111_b(mouseX - this.field_147003_i, mouseY - this.field_147009_r);
/* 274 */         break;
/*     */       }
/*     */     }
/* 277 */     RenderHelper.func_74520_c();
/*     */   }
/*     */   
/*     */   protected void func_146284_a(GuiButton button)
/*     */     throws IOException
/*     */   {
/* 283 */     if ((button.field_146127_k < this.categories.length) && (this.categories[button.field_146127_k] != this.category)) {
/* 284 */       this.category = this.categories[button.field_146127_k];
/* 285 */       ((SealBaseContainer)this.field_147002_h).category = this.category;
/* 286 */       ((SealBaseContainer)this.field_147002_h).setupCategories();
/* 287 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, button.field_146127_k);
/* 288 */       setupCategories();
/*     */ 
/*     */ 
/*     */     }
/* 292 */     else if ((this.category == 0) && (button.field_146127_k == 25) && (this.seal.getOwner().equals(this.field_146297_k.field_71439_g.func_110124_au().toString()))) {
/* 293 */       this.seal.setLocked(!this.seal.isLocked());
/* 294 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.seal.isLocked() ? 25 : 26);
/*     */ 
/*     */ 
/*     */     }
/* 298 */     else if ((this.category == 1) && ((this.seal.getSeal() instanceof ISealConfigFilter)) && (button.field_146127_k == 20)) {
/* 299 */       ISealConfigFilter cp = (ISealConfigFilter)this.seal.getSeal();
/* 300 */       cp.setBlacklist(!cp.isBlacklist());
/* 301 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, cp.isBlacklist() ? 20 : 21);
/*     */ 
/*     */ 
/*     */     }
/* 305 */     else if ((this.category == 3) && ((this.seal.getSeal() instanceof ISealConfigToggles)) && (button.field_146127_k >= 30) && (button.field_146127_k < 30 + ((ISealConfigToggles)this.seal.getSeal()).getToggles().length))
/*     */     {
/* 307 */       ISealConfigToggles cp = (ISealConfigToggles)this.seal.getSeal();
/* 308 */       cp.setToggle(button.field_146127_k - 30, !cp.getToggles()[(button.field_146127_k - 30)].getValue());
/* 309 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, cp.getToggles()[(button.field_146127_k - 30)].getValue() ? button.field_146127_k : button.field_146127_k + 30);
/*     */ 
/*     */ 
/*     */     }
/* 313 */     else if ((button.field_146127_k == 27) && (this.seal.getOwner().equals(this.field_146297_k.field_71439_g.func_110124_au().toString()))) {
/* 314 */       this.seal.setRedstoneSensitive(!this.seal.isRedstoneSensitive());
/* 315 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.seal.isRedstoneSensitive() ? 27 : 28);
/*     */     }
/*     */     else
/*     */     {
/* 319 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, button.field_146127_k);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/gui/SealBaseGUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */