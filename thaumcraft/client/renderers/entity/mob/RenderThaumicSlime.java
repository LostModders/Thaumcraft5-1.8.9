/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderSlime;
/*    */ import net.minecraft.entity.monster.EntitySlime;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */ public class RenderThaumicSlime extends RenderSlime
/*    */ {
/*    */   public RenderThaumicSlime(RenderManager p_i46141_1_, ModelBase p_i46141_2_, float p_i46141_3_)
/*    */   {
/* 15 */     super(p_i46141_1_, p_i46141_2_, p_i46141_3_);
/*    */   }
/*    */   
/* 18 */   private static final ResourceLocation slimeTexture = new ResourceLocation("thaumcraft", "textures/models/creature/tslime.png");
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(EntitySlime entity)
/*    */   {
/* 23 */     return slimeTexture;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderThaumicSlime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */