/*    */ package thaumcraft.client.renderers.entity.projectile;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.projectile.EntityEmber;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderEmber
/*    */   extends Render
/*    */ {
/*    */   public RenderEmber(RenderManager rm)
/*    */   {
/* 25 */     super(rm);
/* 26 */     this.field_76989_e = 0.0F;
/*    */   }
/*    */   
/* 29 */   private Random random = new Random();
/*    */   
/*    */   public void renderEntityAt(EntityEmber entity, double x, double y, double z, float fq, float pticks)
/*    */   {
/* 33 */     Tessellator tessellator = Tessellator.func_178181_a();
/*    */     
/* 35 */     GL11.glPushMatrix();
/*    */     
/* 37 */     GL11.glTranslated(x, y, z);
/* 38 */     GL11.glDepthMask(false);
/* 39 */     GlStateManager.func_179147_l();
/* 40 */     GlStateManager.func_179112_b(770, 1);
/*    */     
/* 42 */     func_110776_a(ParticleEngine.particleTexture);
/* 43 */     int p = (int)(8.0F * (entity.field_70173_aa / entity.duration));
/* 44 */     float f2 = (7 + p) / 16.0F;
/* 45 */     float f3 = f2 + 0.0625F;
/* 46 */     float f4 = 0.5625F;
/* 47 */     float f5 = f4 + 0.0625F;
/*    */     
/* 49 */     float f6 = 1.0F;
/* 50 */     float f7 = 0.5F;
/* 51 */     float f8 = 0.5F;
/*    */     
/* 53 */     float fc = entity.field_70173_aa / entity.duration;
/* 54 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
/*    */     
/* 56 */     float particleScale = 0.25F + fc;
/*    */     
/* 58 */     GL11.glScalef(particleScale, particleScale, particleScale);
/*    */     
/* 60 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 61 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 62 */     tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/* 63 */     int i = 220;
/* 64 */     int j = i >> 16 & 0xFFFF;
/* 65 */     int k = i & 0xFFFF;
/* 66 */     tessellator.func_178180_c().func_181662_b(-f7, -f8, 0.0D).func_181673_a(f2, f5).func_181666_a(1.0F, 1.0F, 1.0F, 0.9F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 67 */     tessellator.func_178180_c().func_181662_b(f6 - f7, -f8, 0.0D).func_181673_a(f3, f5).func_181666_a(1.0F, 1.0F, 1.0F, 0.9F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 68 */     tessellator.func_178180_c().func_181662_b(f6 - f7, 1.0F - f8, 0.0D).func_181673_a(f3, f4).func_181666_a(1.0F, 1.0F, 1.0F, 0.9F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 69 */     tessellator.func_178180_c().func_181662_b(-f7, 1.0F - f8, 0.0D).func_181673_a(f2, f4).func_181666_a(1.0F, 1.0F, 1.0F, 0.9F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 70 */     tessellator.func_78381_a();
/* 71 */     GlStateManager.func_179084_k();
/* 72 */     GL11.glDepthMask(true);
/* 73 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 74 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 81 */     renderEntityAt((EntityEmber)entity, d, d1, d2, f, f1);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 87 */     return TextureMap.field_110575_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderEmber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */