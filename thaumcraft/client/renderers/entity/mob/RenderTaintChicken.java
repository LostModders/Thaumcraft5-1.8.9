/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.monster.tainted.EntityTaintChicken;
/*    */ 
/*    */ public class RenderTaintChicken extends RenderLiving
/*    */ {
/*    */   public RenderTaintChicken(RenderManager p_i46127_1_, ModelBase par1ModelBase, float par2)
/*    */   {
/* 17 */     super(p_i46127_1_, par1ModelBase, par2);
/*    */   }
/*    */   
/* 20 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/creature/chicken.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 23 */     return rl;
/*    */   }
/*    */   
/*    */   public void renderChicken(EntityTaintChicken par1EntityChicken, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 28 */     super.func_76986_a(par1EntityChicken, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected float getWingRotation(EntityTaintChicken par1EntityChicken, float par2)
/*    */   {
/* 33 */     float var3 = par1EntityChicken.field_756_e + (par1EntityChicken.field_752_b - par1EntityChicken.field_756_e) * par2;
/* 34 */     float var4 = par1EntityChicken.field_757_d + (par1EntityChicken.destPos - par1EntityChicken.field_757_d) * par2;
/* 35 */     return (MathHelper.func_76126_a(var3) + 1.0F) * var4;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected float func_77044_a(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 44 */     return getWingRotation((EntityTaintChicken)par1EntityLiving, par2);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 50 */     renderChicken((EntityTaintChicken)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintChicken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */