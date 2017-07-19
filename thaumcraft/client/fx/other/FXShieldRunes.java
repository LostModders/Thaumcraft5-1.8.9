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
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.models.IModelCustom;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ 
/*     */ public class FXShieldRunes extends EntityFX
/*     */ {
/*     */   public FXShieldRunes(World world, double d, double d1, double d2, Entity target, int age, float yaw, float pitch)
/*     */   {
/*  25 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  27 */     this.field_70552_h = 1.0F;
/*  28 */     this.field_70553_i = 1.0F;
/*  29 */     this.field_70551_j = 1.0F;
/*  30 */     this.field_70545_g = 0.0F;
/*  31 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*     */     
/*  33 */     this.field_70547_e = (age + this.field_70146_Z.nextInt(age / 2));
/*     */     
/*  35 */     this.field_70145_X = false;
/*     */     
/*  37 */     func_70105_a(0.01F, 0.01F);
/*     */     
/*  39 */     this.field_70145_X = true;
/*     */     
/*  41 */     this.field_70544_f = 1.0F;
/*     */     
/*  43 */     this.target = target;
/*  44 */     this.yaw = yaw;
/*  45 */     this.pitch = pitch;
/*     */     
/*  47 */     this.field_70169_q = (this.field_70165_t = target.field_70165_t);
/*  48 */     this.field_70167_r = (this.field_70163_u = (target.func_174813_aQ().field_72338_b + target.func_174813_aQ().field_72337_e) / 2.0D);
/*  49 */     this.field_70166_s = (this.field_70161_v = target.field_70161_v);
/*     */     
/*  51 */     for (int a = 0; a < 15; a++) {
/*  52 */       this.tex1[a] = new ResourceLocation("thaumcraft", "textures/models/ripple" + (a + 1) + ".png");
/*  53 */       this.tex2[a] = new ResourceLocation("thaumcraft", "textures/models/hemis" + (a + 1) + ".png");
/*     */     }
/*     */   }
/*     */   
/*  57 */   ResourceLocation[] tex1 = new ResourceLocation[15];
/*  58 */   ResourceLocation[] tex2 = new ResourceLocation[15];
/*     */   
/*  60 */   Entity target = null;
/*  61 */   float yaw = 0.0F;
/*  62 */   float pitch = 0.0F;
/*     */   
/*     */   private IModelCustom model;
/*  65 */   private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "models/obj/hemis.obj");
/*     */   
/*     */   public void func_180434_a(WorldRenderer wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  69 */     Tessellator.func_178181_a().func_78381_a();
/*  70 */     GL11.glPushMatrix();
/*  71 */     GL11.glDisable(2884);
/*     */     
/*  73 */     GL11.glEnable(3042);
/*  74 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  76 */     if (this.model == null) {
/*  77 */       this.model = AdvancedModelLoader.loadModel(MODEL);
/*     */     }
/*     */     
/*  80 */     float fade = (this.field_70546_d + f) / this.field_70547_e;
/*     */     
/*  82 */     float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  83 */     float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  84 */     float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*  85 */     GL11.glTranslated(xx, yy, zz);
/*  86 */     float b = 1.0F;
/*  87 */     int frame = Math.min(15, (int)(14.0F * fade) + 1);
/*  88 */     if (((this.target instanceof net.minecraft.entity.monster.EntityMob)) && (!(this.target instanceof EntityCultist))) {
/*  89 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(this.tex1[(frame - 1)]);
/*  90 */       b = 0.5F;
/*     */     } else {
/*  92 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(this.tex2[(frame - 1)]);
/*     */     }
/*  94 */     int i = 220;
/*  95 */     int j = i % 65536;
/*  96 */     int k = i / 65536;
/*  97 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*  98 */     GL11.glRotatef(180.0F - this.yaw, 0.0F, 1.0F, 0.0F);
/*  99 */     GL11.glRotatef(-this.pitch, 1.0F, 0.0F, 0.0F);
/* 100 */     GL11.glScaled(0.4D * this.target.field_70131_O, 0.4D * this.target.field_70131_O, 0.4D * this.target.field_70131_O);
/* 101 */     GL11.glColor4f(b, b, b, Math.min(1.0F, (1.0F - fade) * 3.0F));
/* 102 */     this.model.renderAll();
/*     */     
/* 104 */     GL11.glBlendFunc(770, 771);
/* 105 */     GL11.glDisable(3042);
/*     */     
/* 107 */     GL11.glEnable(2884);
/* 108 */     GL11.glPopMatrix();
/*     */     
/* 110 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getMCParticleTexture());
/* 111 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 117 */     this.field_70169_q = this.field_70165_t;
/* 118 */     this.field_70167_r = this.field_70163_u;
/* 119 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 121 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 122 */       func_70106_y();
/*     */     }
/*     */     
/* 125 */     this.field_70165_t = this.target.field_70165_t;
/* 126 */     this.field_70163_u = ((this.target.func_174813_aQ().field_72338_b + this.target.func_174813_aQ().field_72337_e) / 2.0D);
/* 127 */     this.field_70161_v = this.target.field_70161_v;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/other/FXShieldRunes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */