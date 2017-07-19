/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.models.IModelCustom;
/*     */ 
/*     */ public class FXSonic extends EntityFX
/*     */ {
/*     */   public FXSonic(World world, double d, double d1, double d2, Entity target, int age)
/*     */   {
/*  23 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  25 */     this.field_70552_h = 1.0F;
/*  26 */     this.field_70553_i = 1.0F;
/*  27 */     this.field_70551_j = 1.0F;
/*  28 */     this.field_70545_g = 0.0F;
/*  29 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*     */     
/*  31 */     this.field_70547_e = (age + this.field_70146_Z.nextInt(age / 2));
/*     */     
/*  33 */     this.field_70145_X = false;
/*     */     
/*  35 */     func_70105_a(0.01F, 0.01F);
/*     */     
/*  37 */     this.field_70145_X = true;
/*     */     
/*  39 */     this.field_70544_f = 1.0F;
/*     */     
/*  41 */     this.target = target;
/*  42 */     this.yaw = target.func_70079_am();
/*  43 */     this.pitch = target.field_70125_A;
/*     */     
/*  45 */     this.field_70169_q = (this.field_70165_t = target.field_70165_t);
/*  46 */     this.field_70167_r = (this.field_70163_u = target.field_70163_u + target.func_70047_e());
/*  47 */     this.field_70166_s = (this.field_70161_v = target.field_70161_v);
/*     */   }
/*     */   
/*     */ 
/*  51 */   Entity target = null;
/*  52 */   float yaw = 0.0F;
/*  53 */   float pitch = 0.0F;
/*     */   
/*     */   private IModelCustom model;
/*  56 */   private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "models/obj/hemis.obj");
/*     */   
/*     */   public void func_180434_a(WorldRenderer wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  60 */     Tessellator.func_178181_a().func_78381_a();
/*  61 */     GL11.glPushMatrix();
/*  62 */     GL11.glDepthMask(false);
/*  63 */     GL11.glEnable(3042);
/*  64 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  66 */     if (this.model == null) {
/*  67 */       this.model = AdvancedModelLoader.loadModel(MODEL);
/*     */     }
/*     */     
/*  70 */     float fade = (this.field_70546_d + f) / this.field_70547_e;
/*     */     
/*  72 */     float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  73 */     float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  74 */     float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*  75 */     GL11.glTranslated(xx, yy, zz);
/*  76 */     float b = 1.0F;
/*  77 */     int frame = Math.min(15, (int)(14.0F * fade) + 1);
/*  78 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(new ResourceLocation("thaumcraft", "textures/models/ripple" + frame + ".png"));
/*  79 */     b = 0.5F;
/*  80 */     int i = 220;
/*  81 */     int j = i % 65536;
/*  82 */     int k = i / 65536;
/*  83 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*  84 */     GL11.glRotatef(-this.yaw, 0.0F, 1.0F, 0.0F);
/*  85 */     GL11.glRotatef(this.pitch, 1.0F, 0.0F, 0.0F);
/*  86 */     GL11.glTranslated(0.0D, 0.0D, 2.0F * this.target.field_70131_O + this.target.field_70130_N / 2.0F);
/*  87 */     GL11.glScaled(0.25D * this.target.field_70131_O, 0.25D * this.target.field_70131_O, -1.0F * this.target.field_70131_O);
/*  88 */     GL11.glColor4f(b, b, b, 1.0F);
/*  89 */     this.model.renderAll();
/*     */     
/*  91 */     GL11.glDisable(3042);
/*  92 */     GL11.glDepthMask(true);
/*  93 */     GL11.glPopMatrix();
/*     */     
/*  95 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getMCParticleTexture());
/*  96 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 102 */     this.field_70169_q = this.field_70165_t;
/* 103 */     this.field_70167_r = this.field_70163_u;
/* 104 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 106 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 107 */       func_70106_y();
/*     */     }
/*     */     
/* 110 */     this.field_70165_t = this.target.field_70165_t;
/* 111 */     this.field_70163_u = (this.target.field_70163_u + this.target.func_70047_e());
/* 112 */     this.field_70161_v = this.target.field_70161_v;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/other/FXSonic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */