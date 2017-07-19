/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.entities.EntityTaintSourceLightning;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderTaintSourceLightning
/*     */   extends Render
/*     */ {
/*     */   public RenderTaintSourceLightning(RenderManager rm)
/*     */   {
/*  26 */     super(rm);
/*     */   }
/*     */   
/*     */ 
/*     */   public void doRender(EntityTaintSourceLightning entity, double x, double y, double z, float p_76986_8_, float partialTicks)
/*     */   {
/*  32 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  33 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/*  34 */     GlStateManager.func_179090_x();
/*  35 */     GlStateManager.func_179140_f();
/*  36 */     GlStateManager.func_179147_l();
/*  37 */     GlStateManager.func_179112_b(770, 1);
/*  38 */     double[] adouble = new double[8];
/*  39 */     double[] adouble1 = new double[8];
/*  40 */     double d3 = 0.0D;
/*  41 */     double d4 = 0.0D;
/*  42 */     Random random = new Random(entity.boltVertex);
/*     */     
/*  44 */     for (int i = 7; i >= 0; i--)
/*     */     {
/*  46 */       adouble[i] = d3;
/*  47 */       adouble1[i] = d4;
/*  48 */       d3 += random.nextInt(11) - 5;
/*  49 */       d4 += random.nextInt(11) - 5;
/*     */     }
/*     */     
/*  52 */     for (int k1 = 0; k1 < 4; k1++)
/*     */     {
/*  54 */       Random random1 = new Random(entity.boltVertex);
/*     */       
/*  56 */       for (int j = 0; j < 3; j++)
/*     */       {
/*  58 */         int k = 7;
/*  59 */         int l = 0;
/*     */         
/*  61 */         if (j > 0)
/*     */         {
/*  63 */           k = 7 - j;
/*     */         }
/*     */         
/*  66 */         if (j > 0)
/*     */         {
/*  68 */           l = k - 2;
/*     */         }
/*     */         
/*  71 */         double d5 = adouble[k] - d3;
/*  72 */         double d6 = adouble1[k] - d4;
/*     */         
/*  74 */         for (int i1 = k; i1 >= l; i1--)
/*     */         {
/*  76 */           double d7 = d5;
/*  77 */           double d8 = d6;
/*     */           
/*  79 */           if (j == 0)
/*     */           {
/*  81 */             d5 += random1.nextInt(11) - 5;
/*  82 */             d6 += random1.nextInt(11) - 5;
/*     */           }
/*     */           else
/*     */           {
/*  86 */             d5 += random1.nextInt(31) - 15;
/*  87 */             d6 += random1.nextInt(31) - 15;
/*     */           }
/*     */           
/*  90 */           worldrenderer.func_181668_a(5, DefaultVertexFormats.field_181706_f);
/*  91 */           float f2 = 0.5F;
/*  92 */           double d9 = 0.1D + k1 * 0.2D;
/*     */           
/*  94 */           if (j == 0)
/*     */           {
/*  96 */             d9 *= (i1 * 0.1D + 1.0D);
/*     */           }
/*     */           
/*  99 */           double d10 = 0.1D + k1 * 0.2D;
/*     */           
/* 101 */           if (j == 0)
/*     */           {
/* 103 */             d10 *= ((i1 - 1) * 0.1D + 1.0D);
/*     */           }
/*     */           
/* 106 */           for (int j1 = 0; j1 < 5; j1++)
/*     */           {
/* 108 */             double d11 = x + 0.5D - d9;
/* 109 */             double d12 = z + 0.5D - d9;
/*     */             
/* 111 */             if ((j1 == 1) || (j1 == 2))
/*     */             {
/* 113 */               d11 += d9 * 2.0D;
/*     */             }
/*     */             
/* 116 */             if ((j1 == 2) || (j1 == 3))
/*     */             {
/* 118 */               d12 += d9 * 2.0D;
/*     */             }
/*     */             
/* 121 */             double d13 = x + 0.5D - d10;
/* 122 */             double d14 = z + 0.5D - d10;
/*     */             
/* 124 */             if ((j1 == 1) || (j1 == 2))
/*     */             {
/* 126 */               d13 += d10 * 2.0D;
/*     */             }
/*     */             
/* 129 */             if ((j1 == 2) || (j1 == 3))
/*     */             {
/* 131 */               d14 += d10 * 2.0D;
/*     */             }
/*     */             
/* 134 */             worldrenderer.func_181662_b(d13 + d5, y + i1 * 16, d14 + d6).func_181666_a(0.9F * f2, 0.3F * f2, 1.0F * f2, 0.3F).func_181675_d();
/* 135 */             worldrenderer.func_181662_b(d11 + d7, y + (i1 + 1) * 16, d12 + d8).func_181666_a(0.9F * f2, 0.3F * f2, 1.0F * f2, 0.3F).func_181675_d();
/*     */           }
/*     */           
/* 138 */           tessellator.func_78381_a();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 143 */     GlStateManager.func_179084_k();
/* 144 */     GlStateManager.func_179145_e();
/* 145 */     GlStateManager.func_179098_w();
/*     */   }
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityTaintSourceLightning entity)
/*     */   {
/* 150 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 156 */     return getEntityTexture((EntityTaintSourceLightning)entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
/*     */   {
/* 162 */     doRender((EntityTaintSourceLightning)entity, x, y, z, p_76986_8_, partialTicks);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/RenderTaintSourceLightning.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */