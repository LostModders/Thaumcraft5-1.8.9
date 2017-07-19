/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityRabbit;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class LayerTaintRabbit implements net.minecraft.client.renderer.entity.layers.LayerRenderer
/*    */ {
/* 10 */   private static final ResourceLocation overlay = new ResourceLocation("thaumcraft", "textures/models/creature/taintrabbit.png");
/*    */   
/*    */   private final RenderTaintRabbit field_177149_b;
/*    */   
/*    */   public LayerTaintRabbit(RenderTaintRabbit p_i46109_1_)
/*    */   {
/* 16 */     this.field_177149_b = p_i46109_1_;
/*    */   }
/*    */   
/*    */   public void func_177148_a(EntityRabbit p_177148_1_, float p_177148_2_, float p_177148_3_, float p_177148_4_, float p_177148_5_, float p_177148_6_, float p_177148_7_, float p_177148_8_)
/*    */   {
/* 21 */     if (!p_177148_1_.func_82150_aj()) {
/* 22 */       this.field_177149_b.func_110776_a(overlay);
/* 23 */       GlStateManager.func_179137_b(0.0D, -0.01D, 0.0D);
/* 24 */       GlStateManager.func_179139_a(1.01D, 1.01D, 1.01D);
/* 25 */       GlStateManager.func_179147_l();
/* 26 */       GlStateManager.func_179118_c();
/* 27 */       GlStateManager.func_179112_b(770, 771);
/* 28 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 29 */       this.field_177149_b.func_177087_b().func_78088_a(p_177148_1_, p_177148_2_, p_177148_3_, p_177148_5_, p_177148_6_, p_177148_7_, p_177148_8_);
/* 30 */       GlStateManager.func_179084_k();
/* 31 */       GlStateManager.func_179141_d();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_177142_b()
/*    */   {
/* 38 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_177141_a(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
/*    */   {
/* 44 */     func_177148_a((EntityRabbit)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/LayerTaintRabbit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */