/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.monster.tainted.EntityTaintCow;
/*    */ 
/*    */ public class RenderTaintCow extends RenderLiving
/*    */ {
/*    */   public RenderTaintCow(RenderManager rm, ModelBase par1ModelBase, float par2)
/*    */   {
/* 15 */     super(rm, par1ModelBase, par2);
/*    */   }
/*    */   
/* 18 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/creature/cow.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 21 */     return rl;
/*    */   }
/*    */   
/*    */   public void renderCow(EntityTaintCow par1EntityCow, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 26 */     super.func_76986_a(par1EntityCow, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 32 */     renderCow((EntityTaintCow)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintCow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */