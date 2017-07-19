/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelVillager;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.monster.tainted.EntityTaintVillager;
/*    */ 
/*    */ public class RenderTaintVillager
/*    */   extends RenderLiving
/*    */ {
/* 17 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/creature/villager.png");
/*    */   
/*    */   public RenderTaintVillager(RenderManager rm)
/*    */   {
/* 21 */     super(rm, new ModelVillager(0.0F), 0.5F);
/* 22 */     func_177094_a(new LayerCustomHead(((ModelVillager)super.func_177087_b()).field_78191_a));
/*    */   }
/*    */   
/*    */ 
/*    */   public ModelBase func_177087_b()
/*    */   {
/* 28 */     return (ModelVillager)super.func_177087_b();
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 33 */     return rl;
/*    */   }
/*    */   
/*    */   protected void preRenderCallback(EntityTaintVillager p_77041_1_, float p_77041_2_)
/*    */   {
/* 38 */     float f1 = 0.9375F;
/* 39 */     this.field_76989_e = 0.5F;
/* 40 */     GlStateManager.func_179152_a(f1, f1, f1);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_)
/*    */   {
/* 46 */     preRenderCallback((EntityTaintVillager)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */