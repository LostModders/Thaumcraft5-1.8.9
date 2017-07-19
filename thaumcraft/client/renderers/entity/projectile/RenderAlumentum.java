/*    */ package thaumcraft.client.renderers.entity.projectile;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*    */ 
/*    */ public class RenderAlumentum
/*    */   extends Render
/*    */ {
/*    */   public RenderAlumentum(RenderManager rm)
/*    */   {
/* 15 */     super(rm);
/* 16 */     this.field_76989_e = 0.1F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderEntityAt(EntityAlumentum tg, double x, double y, double z, float fq) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 29 */     renderEntityAt((EntityAlumentum)entity, d, d1, d2, f);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 36 */     return TextureMap.field_110575_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderAlumentum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */