/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelSilverfish;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintCrawler
/*    */   extends RenderLiving
/*    */ {
/* 18 */   private static final ResourceLocation textures = new ResourceLocation("thaumcraft", "textures/models/creature/crawler.png");
/*    */   
/*    */   public RenderTaintCrawler(RenderManager p_i46144_1_)
/*    */   {
/* 22 */     super(p_i46144_1_, new ModelSilverfish(), 0.2F);
/*    */   }
/*    */   
/*    */   protected float func_180584_a(EntityTaintCrawler p_180584_1_)
/*    */   {
/* 27 */     return 180.0F;
/*    */   }
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityTaintCrawler entity)
/*    */   {
/* 32 */     return textures;
/*    */   }
/*    */   
/*    */ 
/*    */   protected float func_77037_a(EntityLivingBase p_77037_1_)
/*    */   {
/* 38 */     return func_180584_a((EntityTaintCrawler)p_77037_1_);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 44 */     return getEntityTexture((EntityTaintCrawler)entity);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 50 */     GL11.glScalef(0.7F, 0.7F, 0.7F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintCrawler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */