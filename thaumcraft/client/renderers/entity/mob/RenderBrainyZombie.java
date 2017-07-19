/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelZombieVillager;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderZombie;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderBrainyZombie
/*    */   extends RenderZombie
/*    */ {
/*    */   public RenderBrainyZombie(RenderManager rm)
/*    */   {
/* 21 */     super(rm);
/*    */   }
/*    */   
/* 24 */   private static final ResourceLocation field_110865_p = new ResourceLocation("thaumcraft", "textures/models/creature/bzombie.png");
/* 25 */   private static final ResourceLocation field_110864_q = new ResourceLocation("thaumcraft", "textures/models/creature/bzombievil.png");
/*    */   private ModelBiped field_82434_o;
/*    */   private ModelZombieVillager field_82432_p;
/* 28 */   private int field_82431_q = 1;
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(EntityZombie entity)
/*    */   {
/* 33 */     return entity.func_82231_m() ? field_110864_q : field_110865_p;
/*    */   }
/*    */   
/*    */   protected void preRenderScale(EntityGiantBrainyZombie z, float par2)
/*    */   {
/* 38 */     GL11.glScalef(z.getAnger(), z.getAnger(), z.getAnger());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void preRenderCallback(EntityZombie par1EntityLiving, float par2)
/*    */   {
/* 45 */     if ((par1EntityLiving instanceof EntityGiantBrainyZombie)) {
/* 46 */       preRenderScale((EntityGiantBrainyZombie)par1EntityLiving, par2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderBrainyZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */