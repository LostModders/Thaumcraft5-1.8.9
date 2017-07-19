/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entity.ModelPech;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderPech
/*    */   extends RenderLiving
/*    */ {
/*    */   protected ModelPech modelMain;
/*    */   protected ModelPech modelOverlay;
/* 21 */   private static final ResourceLocation[] skin = { new ResourceLocation("thaumcraft", "textures/models/creature/pech_forage.png"), new ResourceLocation("thaumcraft", "textures/models/creature/pech_thaum.png"), new ResourceLocation("thaumcraft", "textures/models/creature/pech_stalker.png") };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public RenderPech(RenderManager rm, ModelPech par1ModelBiped, float par2)
/*    */   {
/* 29 */     super(rm, par1ModelBiped, par2);
/* 30 */     this.modelMain = par1ModelBiped;
/* 31 */     this.modelOverlay = new ModelPech();
/* 32 */     func_177094_a(new LayerHeldItemPech(this));
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 37 */     return skin[((thaumcraft.common.entities.monster.EntityPech)entity).getPechType()];
/*    */   }
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 42 */     float f2 = 1.0F;
/* 43 */     GL11.glColor3f(f2, f2, f2);
/* 44 */     double d3 = par4 - par1EntityLiving.func_70033_W();
/*    */     
/* 46 */     if (par1EntityLiving.func_70093_af())
/*    */     {
/* 48 */       d3 -= 0.125D;
/*    */     }
/*    */     
/* 51 */     super.func_76986_a(par1EntityLiving, par2, d3, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 57 */     doRenderLiving(par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */