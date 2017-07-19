/*    */ package thaumcraft.client.renderers.entity.projectile;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*    */ import thaumcraft.client.lib.models.IModelCustom;
/*    */ import thaumcraft.common.entities.projectile.EntityFrostShard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderFrostShard
/*    */   extends Render
/*    */ {
/*    */   private IModelCustom model;
/* 23 */   private static final ResourceLocation ORB = new ResourceLocation("thaumcraft", "models/obj/orb.obj");
/*    */   
/*    */   public RenderFrostShard(RenderManager rm) {
/* 26 */     super(rm);
/* 27 */     this.model = AdvancedModelLoader.loadModel(ORB);
/*    */   }
/*    */   
/*    */   public void renderShard(EntityFrostShard shard, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 32 */     func_180548_c(shard);
/* 33 */     GL11.glPushMatrix();
/* 34 */     GL11.glEnable(32826);
/* 35 */     GL11.glEnable(3042);
/* 36 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 38 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*    */     
/* 40 */     Random rnd = new Random(shard.func_145782_y());
/*    */     
/* 42 */     GL11.glRotatef(shard.field_70126_B + (shard.field_70177_z - shard.field_70126_B) * par9, 0.0F, 1.0F, 0.0F);
/* 43 */     GL11.glRotatef(shard.field_70127_C + (shard.field_70125_A - shard.field_70127_C) * par9, 0.0F, 0.0F, 1.0F);
/*    */     
/* 45 */     GL11.glPushMatrix();
/* 46 */     float base = shard.getDamage() * 0.1F;
/* 47 */     GL11.glScalef(base + rnd.nextFloat() * 0.1F, base + rnd.nextFloat() * 0.1F, base + rnd.nextFloat() * 0.1F);
/*    */     
/* 49 */     this.model.renderAll();
/* 50 */     GL11.glPopMatrix();
/* 51 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 52 */     GL11.glDisable(3042);
/* 53 */     GL11.glDisable(32826);
/* 54 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 60 */     renderShard((EntityFrostShard)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/* 63 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/frostshard.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 66 */     return rl;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/projectile/RenderFrostShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */