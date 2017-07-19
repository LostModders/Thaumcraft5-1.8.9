/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintSwarm extends RenderLiving
/*    */ {
/*    */   public RenderTaintSwarm(RenderManager rm)
/*    */   {
/* 17 */     super(rm, null, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 28 */     return TextureMap.field_110575_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintSwarm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */