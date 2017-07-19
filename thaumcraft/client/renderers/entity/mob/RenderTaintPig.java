/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.monster.tainted.EntityTaintPig;
/*    */ 
/*    */ public class RenderTaintPig extends RenderLiving
/*    */ {
/*    */   public RenderTaintPig(RenderManager rm, ModelBase par1ModelBase, float par3)
/*    */   {
/* 15 */     super(rm, par1ModelBase, par3);
/*    */   }
/*    */   
/* 18 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/creature/pig.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 22 */     return rl;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_40286_a(EntityTaintPig par1EntityPig, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 28 */     super.func_76986_a(par1EntityPig, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 35 */     func_40286_a((EntityTaintPig)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintPig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */