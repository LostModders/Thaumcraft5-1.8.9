/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.entity.RenderAuraNode;
/*     */ 
/*     */ 
/*     */ public class FXBurst
/*     */   extends EntityFX
/*     */ {
/*     */   public FXBurst(World world, double d, double d1, double d2, float f)
/*     */   {
/*  21 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  23 */     this.field_70552_h = 1.0F;
/*  24 */     this.field_70553_i = 1.0F;
/*  25 */     this.field_70551_j = 1.0F;
/*  26 */     this.field_70545_g = 0.0F;
/*  27 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  28 */     this.field_70544_f *= f;
/*  29 */     this.field_70547_e = 31;
/*  30 */     this.field_70145_X = false;
/*  31 */     func_70105_a(0.01F, 0.01F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  40 */     Tessellator.func_178181_a().func_78381_a();
/*  41 */     GL11.glPushMatrix();
/*     */     
/*     */ 
/*  44 */     GL11.glDepthMask(false);
/*  45 */     GL11.glEnable(3042);
/*  46 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  48 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(RenderAuraNode.texture);
/*     */     
/*  50 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*     */     
/*  52 */     float var8 = this.field_70546_d % 32 / 32.0F;
/*  53 */     float var9 = var8 + 0.03125F;
/*  54 */     float var10 = 0.96875F;
/*  55 */     float var11 = 1.0F;
/*  56 */     float var12 = this.field_70544_f;
/*     */     
/*  58 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  59 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  60 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*  61 */     float var16 = 1.0F;
/*     */     
/*  63 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*  64 */     int i = 240;
/*  65 */     int j = i >> 16 & 0xFFFF;
/*  66 */     int k = i & 0xFFFF;
/*  67 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  69 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  71 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/*  73 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F).func_181671_a(j, k).func_181675_d();
/*     */     
/*     */ 
/*  76 */     Tessellator.func_178181_a().func_78381_a();
/*  77 */     GL11.glBlendFunc(770, 771);
/*  78 */     GL11.glDisable(3042);
/*  79 */     GL11.glDepthMask(true);
/*     */     
/*  81 */     GL11.glPopMatrix();
/*  82 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getMCParticleTexture());
/*  83 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  90 */     this.field_70169_q = this.field_70165_t;
/*  91 */     this.field_70167_r = this.field_70163_u;
/*  92 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  94 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/*  96 */       func_70106_y();
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
/*     */   public void setGravity(float value)
/*     */   {
/* 109 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXBurst.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */