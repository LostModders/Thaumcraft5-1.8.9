/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintSourceCloud
/*    */   extends Render
/*    */ {
/*    */   public RenderTaintSourceCloud(RenderManager rm)
/*    */   {
/* 34 */     super(rm);
/* 35 */     this.field_76989_e = 0.0F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 42 */   public static final ResourceLocation texture = new ResourceLocation("textures/environment/rain.png");
/*    */   
/*    */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {}
/*    */   
/* 46 */   protected ResourceLocation func_110775_a(Entity entity) { return texture; }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/RenderTaintSourceCloud.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */