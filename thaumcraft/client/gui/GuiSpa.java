/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.tiles.devices.TileSpa;
/*     */ 
/*     */ @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */ public class GuiSpa extends GuiContainer
/*     */ {
/*     */   private TileSpa spa;
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   
/*     */   public GuiSpa(InventoryPlayer par1InventoryPlayer, TileSpa teSpa)
/*     */   {
/*  34 */     super(new thaumcraft.common.container.ContainerSpa(par1InventoryPlayer, teSpa));
/*  35 */     this.spa = teSpa;
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
/*  48 */     super.func_73863_a(par1, par2, par3);
/*  49 */     this.xSize_lo = par1;
/*  50 */     this.ySize_lo = par2;
/*     */     
/*  52 */     int baseX = this.field_147003_i;
/*  53 */     int baseY = this.field_147009_r;
/*     */     
/*  55 */     int mposx = par1 - (baseX + 104);
/*  56 */     int mposy = par2 - (baseY + 10);
/*     */     
/*  58 */     if ((mposx >= 0) && (mposy >= 0) && (mposx < 10) && (mposy < 55))
/*     */     {
/*  60 */       List list = new ArrayList();
/*  61 */       FluidStack fluid = this.spa.tank.getFluid();
/*  62 */       if (fluid != null) {
/*  63 */         list.add(fluid.getFluid().getLocalizedName(fluid));
/*  64 */         list.add(fluid.amount + " mb");
/*  65 */         drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */       }
/*     */     }
/*     */     
/*  69 */     mposx = par1 - (baseX + 88);
/*  70 */     mposy = par2 - (baseY + 34);
/*     */     
/*  72 */     if ((mposx >= 0) && (mposy >= 0) && (mposx < 10) && (mposy < 10))
/*     */     {
/*  74 */       List list = new ArrayList();
/*  75 */       if (this.spa.getMix()) {
/*  76 */         list.add(StatCollector.func_74838_a("text.spa.mix.true"));
/*     */       } else {
/*  78 */         list.add(StatCollector.func_74838_a("text.spa.mix.false"));
/*     */       }
/*  80 */       drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  85 */   ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_spa.png");
/*     */   
/*     */ 
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/*  90 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  91 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*  92 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*  93 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*  95 */     GL11.glEnable(3042);
/*  96 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  98 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/* 100 */     if (this.spa.getMix()) {
/* 101 */       func_73729_b(k + 89, l + 35, 208, 16, 8, 8);
/*     */     } else {
/* 103 */       func_73729_b(k + 89, l + 35, 208, 32, 8, 8);
/*     */     }
/*     */     
/*     */ 
/* 107 */     if (this.spa.tank.getFluidAmount() > 0) {
/* 108 */       FluidStack fluid = this.spa.tank.getFluid();
/* 109 */       if (fluid != null) {
/* 110 */         TextureAtlasSprite icon = func_175371_a(fluid.getFluid().getBlock());
/* 111 */         if (icon != null) {
/* 112 */           float bar = this.spa.tank.getFluidAmount() / this.spa.tank.getCapacity();
/* 113 */           renderFluid(icon, fluid.getFluid());
/* 114 */           this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 115 */           func_73729_b(k + 107, l + 15, 107, 15, 10, (int)(48.0F - 48.0F * bar));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 120 */     func_73729_b(k + 106, l + 11, 232, 0, 10, 55);
/*     */     
/* 122 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   private TextureAtlasSprite func_175371_a(Block p_175371_1_)
/*     */   {
/* 127 */     return Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178122_a(p_175371_1_.func_176223_P());
/*     */   }
/*     */   
/*     */   public void renderFluid(TextureAtlasSprite icon, Fluid fluid) {
/* 131 */     GL11.glPushMatrix();
/* 132 */     this.field_146297_k.field_71446_o.func_110577_a(TextureMap.field_110575_b);
/* 133 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 134 */     Color cc = new Color(fluid.getColor());
/* 135 */     GL11.glColor3f(cc.getRed() / 255.0F, cc.getGreen() / 255.0F, cc.getBlue() / 255.0F);
/* 136 */     for (int a = 0; a < 6; a++) {
/* 137 */       func_175175_a((this.field_147003_i + 107) * 2, (this.field_147009_r + 15) * 2 + a * 16, icon, 16, 16);
/*     */     }
/* 139 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 140 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   protected void func_73864_a(int mx, int my, int par3) throws IOException
/*     */   {
/* 145 */     super.func_73864_a(mx, my, par3);
/*     */     
/* 147 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 148 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/* 151 */     int var7 = mx - (gx + 89);
/* 152 */     int var8 = my - (gy + 35);
/*     */     
/* 154 */     if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 8))
/*     */     {
/*     */ 
/* 157 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/* 158 */       playButtonClick();
/* 159 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonClick()
/*     */   {
/* 166 */     this.field_146297_k.func_175606_aa().field_70170_p.func_72980_b(this.field_146297_k.func_175606_aa().field_70165_t, this.field_146297_k.func_175606_aa().field_70163_u, this.field_146297_k.func_175606_aa().field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/gui/GuiSpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */