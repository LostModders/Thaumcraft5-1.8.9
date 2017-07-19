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
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderInhabitedZombie extends RenderZombie
/*    */ {
/*    */   public RenderInhabitedZombie(RenderManager p_i46127_1_)
/*    */   {
/* 17 */     super(p_i46127_1_);
/*    */   }
/*    */   
/* 20 */   private static final ResourceLocation t1 = new ResourceLocation("thaumcraft", "textures/models/creature/czombie.png");
/*    */   private ModelBiped field_82434_o;
/*    */   private ModelZombieVillager field_82432_p;
/* 23 */   private int field_82431_q = 1;
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(EntityZombie entity)
/*    */   {
/* 28 */     return t1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderInhabitedZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */