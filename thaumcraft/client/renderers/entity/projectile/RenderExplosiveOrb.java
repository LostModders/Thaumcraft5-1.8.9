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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderExplosiveOrb
/*    */   extends Render
/*    */ {
/*    */   public RenderExplosiveOrb(RenderManager rm)
/*    */   {
/* 27 */     super(rm);
/* 28 */     this.field_76989_e = 0.0F;
/*    */   }
/*    */   
/* 31 */   private Random random = new Random();
/*    */   
/*    */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks)
/*    */   {
/* 35 */     Tessellator tessellator = Tessellator.func_178181_a();
/*    */     
/* 37 */     GL11.glPushMatrix();
/* 38 */     GL11.glTranslated(x, y, z);
/* 39 */     GL11.glDepthMask(false);
/* 40 */     GlStateManager.func_179147_l();
/* 41 */     GlStateManager.func_179112_b(770, 771);
/*    */     
/* 43 */     func_110776_a(ParticleEngine.particleTexture2);
/*    */     
/* 45 */     float f2 = entity.field_70173_aa % 4 / 16.0F;
/* 46 */     float f3 = f2 + 0.0625F;
/* 47 */     float f4 = 0.8125F;
/* 48 */     float f5 = f4 + 0.0625F;
/*    */     
/* 50 */     float f6 = 1.0F;
/* 51 */     float f7 = 0.5F;
/* 52 */     float f8 = 0.5F;
/*    */     
/* 54 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
/* 55 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 56 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 57 */     GL11.glScalef(0.7F, 0.7F, 0.7F);
/* 58 */     tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/* 59 */     int i = 220;
/* 60 */     int j = i >> 16 & 0xFFFF;
/* 61 */     int k = i & 0xFFFF;
/* 62 */     tessellator.func_178180_c().func_181662_b(-f7, -f8, 0.0D).func_181673_a(f2, f5).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 63 */     tessellator.func_178180_c().func_181662_b(f6 - f7, -f8, 0.0D).func_181673_a(f3, f5).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 64 */     tessellator.func_178180_c().func_181662_b(f6 - f7, 1.0F - f8, 0.0D).func_181673_a(f3, f4).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 65 */     tessellator.func_178180_c().func_181662_b(-f7, 1.0F - f8, 0.0D).func_181673_a(f2, f4).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 66 */     tessellator.func_78381_a();
/* 67 */     GlStateManager.func_179084_k();
/* 68 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 69 */     GL11.glDepthMask(true);
/* 70 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 76 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 82 */     return TextureMap.field_110575_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderExplosiveOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */