/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderRabbit;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderTaintRabbit
/*    */   extends RenderRabbit
/*    */ {
/* 11 */   private static final ResourceLocation overlay = new ResourceLocation("thaumcraft", "textures/models/creature/taintrabbit.png");
/*    */   
/*    */   public RenderTaintRabbit(RenderManager p_i46127_1_, ModelBase par1ModelBase, float par2)
/*    */   {
/* 15 */     super(p_i46127_1_, par1ModelBase, par2);
/* 16 */     func_177094_a(new LayerTaintRabbit(this));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintRabbit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */