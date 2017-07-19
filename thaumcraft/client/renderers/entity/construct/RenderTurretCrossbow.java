/*    */ package thaumcraft.client.renderers.entity.construct;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.client.renderers.models.entity.ModelCrossbow;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderTurretCrossbow
/*    */   extends RenderLiving
/*    */ {
/*    */   public RenderTurretCrossbow(RenderManager rm)
/*    */   {
/* 18 */     super(rm, new ModelCrossbow(), 0.5F);
/*    */   }
/*    */   
/*    */   protected float func_77040_d(EntityLivingBase e, float p_77040_2_)
/*    */   {
/* 23 */     ((EntityTurretCrossbow)e).loadProgressForRender = ((EntityTurretCrossbow)e).getLoadProgress(p_77040_2_);
/* 24 */     return super.func_77040_d(e, p_77040_2_);
/*    */   }
/*    */   
/* 27 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/crossbow.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 31 */     return rl;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/RenderTurretCrossbow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */