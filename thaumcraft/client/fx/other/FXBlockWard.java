/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBlockWard
/*     */   extends EntityFX
/*     */ {
/*     */   public FXBlockWard(World world, double d, double d1, double d2, EnumFacing side, float f, float f1, float f2)
/*     */   {
/*  23 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  25 */     this.side = side;
/*     */     
/*  27 */     this.field_70545_g = 0.0F;
/*  28 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  29 */     this.field_70547_e = (12 + this.field_70146_Z.nextInt(5));
/*  30 */     this.field_70145_X = false;
/*  31 */     func_70105_a(0.01F, 0.01F);
/*  32 */     this.field_70169_q = this.field_70165_t;
/*  33 */     this.field_70167_r = this.field_70163_u;
/*  34 */     this.field_70166_s = this.field_70161_v;
/*  35 */     this.field_70145_X = true;
/*  36 */     this.field_70544_f = ((float)(1.4D + this.field_70146_Z.nextGaussian() * 0.30000001192092896D));
/*  37 */     this.rotation = this.field_70146_Z.nextInt(360);
/*  38 */     this.sx = MathHelper.func_76131_a(f - 0.6F + this.field_70146_Z.nextFloat() * 0.2F, -0.4F, 0.4F);
/*  39 */     this.sy = MathHelper.func_76131_a(f1 - 0.6F + this.field_70146_Z.nextFloat() * 0.2F, -0.4F, 0.4F);
/*  40 */     this.sz = MathHelper.func_76131_a(f2 - 0.6F + this.field_70146_Z.nextFloat() * 0.2F, -0.4F, 0.4F);
/*  41 */     if (side.func_82601_c() != 0) this.sx = 0.0F;
/*  42 */     if (side.func_96559_d() != 0) this.sy = 0.0F;
/*  43 */     if (side.func_82599_e() != 0) this.sz = 0.0F;
/*  44 */     for (int a = 0; a < 15; a++) {
/*  45 */       this.tex1[a] = new ResourceLocation("thaumcraft", "textures/models/hemis" + (a + 1) + ".png");
/*     */     }
/*     */   }
/*     */   
/*  49 */   ResourceLocation[] tex1 = new ResourceLocation[15];
/*     */   
/*     */   EnumFacing side;
/*  52 */   int rotation = 0;
/*  53 */   float sx = 0.0F;
/*  54 */   float sy = 0.0F;
/*  55 */   float sz = 0.0F;
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  61 */     Tessellator.func_178181_a().func_78381_a();
/*  62 */     GL11.glPushMatrix();
/*  63 */     float fade = (this.field_70546_d + f) / this.field_70547_e;
/*  64 */     int frame = Math.min(15, (int)(15.0F * fade));
/*  65 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(this.tex1[(frame - 1)]);
/*     */     
/*  67 */     GL11.glDepthMask(false);
/*  68 */     GL11.glEnable(3042);
/*  69 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  71 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as / 2.0F);
/*     */     
/*  73 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  74 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  75 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  77 */     GL11.glTranslated(var13 + this.sx, var14 + this.sy, var15 + this.sz);
/*     */     
/*  79 */     GL11.glRotatef(90.0F, this.side.func_96559_d(), -this.side.func_82601_c(), this.side.func_82599_e());
/*  80 */     GL11.glRotatef(this.rotation, 0.0F, 0.0F, 1.0F);
/*  81 */     if (this.side.func_82599_e() > 0) {
/*  82 */       GL11.glTranslated(0.0D, 0.0D, 0.5049999952316284D);
/*  83 */       GL11.glRotatef(180.0F, 0.0F, -1.0F, 0.0F);
/*     */     } else {
/*  85 */       GL11.glTranslated(0.0D, 0.0D, -0.5049999952316284D);
/*     */     }
/*     */     
/*  88 */     float var12 = this.field_70544_f;
/*     */     
/*  90 */     float var16 = 1.0F;
/*     */     
/*  92 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*  93 */     int i = 240;
/*  94 */     int j = i >> 16 & 0xFFFF;
/*  95 */     int k = i & 0xFFFF;
/*  96 */     wr.func_181662_b(-0.5D * var12, 0.5D * var12, 0.0D).func_181673_a(0.0D, 1.0D).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/*  97 */     wr.func_181662_b(0.5D * var12, 0.5D * var12, 0.0D).func_181673_a(1.0D, 1.0D).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/*  98 */     wr.func_181662_b(0.5D * var12, -0.5D * var12, 0.0D).func_181673_a(1.0D, 0.0D).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/*  99 */     wr.func_181662_b(-0.5D * var12, -0.5D * var12, 0.0D).func_181673_a(0.0D, 0.0D).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_181671_a(j, k).func_181675_d();
/* 100 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 102 */     GL11.glDisable(3042);
/* 103 */     GL11.glDepthMask(true);
/*     */     
/* 105 */     GL11.glPopMatrix();
/* 106 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getMCParticleTexture());
/* 107 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 114 */     this.field_70169_q = this.field_70165_t;
/* 115 */     this.field_70167_r = this.field_70163_u;
/* 116 */     this.field_70166_s = this.field_70161_v;
/* 117 */     float threshold = this.field_70547_e / 5.0F;
/* 118 */     if (this.field_70546_d <= threshold) {
/* 119 */       this.field_82339_as = (this.field_70546_d / threshold);
/*     */     } else {
/* 121 */       this.field_82339_as = ((this.field_70547_e - this.field_70546_d) / this.field_70547_e);
/*     */     }
/* 123 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 125 */       func_70106_y();
/*     */     }
/*     */     
/* 128 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/*     */     
/* 130 */     this.field_70165_t += this.field_70159_w;
/* 131 */     this.field_70163_u += this.field_70181_x;
/* 132 */     this.field_70161_v += this.field_70179_y;
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 137 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/other/FXBlockWard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */