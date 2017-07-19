/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.BossStatus;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entity.ModelEldritchGolem;
/*    */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderEldritchGolem
/*    */   extends RenderLiving
/*    */ {
/*    */   protected ModelEldritchGolem modelMain;
/* 22 */   private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/models/creature/eldritch_golem.png");
/*    */   
/*    */   public RenderEldritchGolem(RenderManager rm, ModelEldritchGolem par1ModelBiped, float par2)
/*    */   {
/* 26 */     super(rm, par1ModelBiped, par2);
/* 27 */     this.modelMain = par1ModelBiped;
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 32 */     return skin;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 38 */     BossStatus.func_82824_a((EntityEldritchGolem)par1EntityLiving, false);
/* 39 */     GL11.glScalef(2.15F, 2.15F, 2.15F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void doRenderLiving(EntityLiving golem, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 45 */     GL11.glEnable(3042);
/* 46 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 47 */     GL11.glBlendFunc(770, 771);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 55 */     super.func_76986_a(golem, par2, par4, par6, par8, par9);
/*    */     
/* 57 */     GL11.glDisable(3042);
/* 58 */     GL11.glAlphaFunc(516, 0.1F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 71 */     doRenderLiving(par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderEldritchGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */