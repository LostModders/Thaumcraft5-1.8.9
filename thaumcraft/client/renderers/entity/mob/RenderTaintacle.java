/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.BossStatus;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entity.ModelTaintacle;
/*    */ import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintacle
/*    */   extends RenderLiving
/*    */ {
/*    */   public RenderTaintacle(RenderManager rm, float shadow, int length)
/*    */   {
/* 22 */     super(rm, new ModelTaintacle(length), shadow);
/*    */   }
/*    */   
/* 25 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/creature/taintacle.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 28 */     return rl;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 34 */     if ((par1EntityLiving instanceof EntityTaintacleGiant)) {
/* 35 */       BossStatus.func_82824_a((EntityTaintacleGiant)par1EntityLiving, false);
/* 36 */       GL11.glScalef(1.33F, 1.33F, 1.33F);
/*    */     }
/* 38 */     super.func_77041_b(par1EntityLiving, par2);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintacle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */