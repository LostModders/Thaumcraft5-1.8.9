/*    */ package thaumcraft.client.renderers.entity.projectile;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderElectricOrb
/*    */   extends Render
/*    */ {
/*    */   public RenderElectricOrb(RenderManager rm)
/*    */   {
/* 26 */     super(rm);
/* 27 */     this.field_76989_e = 0.0F;
/*    */   }
/*    */   
/* 30 */   private Random random = new Random();
/*    */   
/*    */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks)
/*    */   {
/* 34 */     Tessellator tessellator = Tessellator.func_178181_a();
/*    */     
/* 36 */     GL11.glPushMatrix();
/* 37 */     GL11.glTranslated(x, y, z);
/* 38 */     GL11.glEnable(3042);
/* 39 */     GL11.glBlendFunc(770, 1);
/*    */     
/* 41 */     func_110776_a(ParticleEngine.particleTexture);
/*    */     
/* 43 */     float f2 = (1 + entity.field_70173_aa % 6) / 8.0F;
/* 44 */     float f3 = f2 + 0.125F;
/* 45 */     float f4 = 0.875F;
/* 46 */     if (((entity instanceof EntityGolemOrb)) && (((EntityGolemOrb)entity).red)) {
/* 47 */       f4 = 0.75F;
/*    */     }
/* 49 */     float f5 = f4 + 0.125F;
/*    */     
/* 51 */     float f6 = 1.0F;
/* 52 */     float f7 = 0.5F;
/* 53 */     float f8 = 0.5F;
/*    */     
/* 55 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
/*    */     
/* 57 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 58 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 59 */     float bob = MathHelper.func_76126_a(entity.field_70173_aa / 5.0F) * 0.2F + 0.2F;
/* 60 */     GL11.glScalef(1.0F + bob, 1.0F + bob, 1.0F + bob);
/* 61 */     tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/* 62 */     int i = 220;
/* 63 */     int j = i >> 16 & 0xFFFF;
/* 64 */     int k = i & 0xFFFF;
/* 65 */     tessellator.func_178180_c().func_181662_b(-f7, -f8, 0.0D).func_181673_a(f2, f5).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 66 */     tessellator.func_178180_c().func_181662_b(f6 - f7, -f8, 0.0D).func_181673_a(f3, f5).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 67 */     tessellator.func_178180_c().func_181662_b(f6 - f7, 1.0F - f8, 0.0D).func_181673_a(f3, f4).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 68 */     tessellator.func_178180_c().func_181662_b(-f7, 1.0F - f8, 0.0D).func_181673_a(f2, f4).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181671_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 69 */     tessellator.func_78381_a();
/* 70 */     GL11.glBlendFunc(770, 771);
/* 71 */     GL11.glDisable(3042);
/* 72 */     GL11.glDisable(32826);
/* 73 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/*    */ 
/* 76 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 83 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 89 */     return TextureMap.field_110575_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderElectricOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */