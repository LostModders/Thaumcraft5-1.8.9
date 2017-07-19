/*    */ package thaumcraft.client.renderers.entity.construct;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel.EnumAttachPoint;
/*    */ 
/*    */ 
/*    */ public class PartModelClaws
/*    */   extends thaumcraft.api.golems.parts.PartModel
/*    */ {
/* 11 */   public PartModelClaws(ResourceLocation objModel, ResourceLocation objTexture, PartModel.EnumAttachPoint attachPoint) { super(objModel, objTexture, attachPoint); }
/*    */   
/* 13 */   float f = 0.0F;
/*    */   
/*    */   public void preRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, thaumcraft.api.golems.parts.PartModel.EnumLimbSide side) {
/* 16 */     if (partName.startsWith("claw")) {
/* 17 */       this.f = 0.0F;
/* 18 */       this.f = (golem.getGolemEntity().func_70678_g(partialTicks) * 4.1F);
/* 19 */       this.f *= this.f;
/* 20 */       net.minecraft.client.renderer.GlStateManager.func_179137_b(0.0D, -0.2D, 0.0D);
/* 21 */       net.minecraft.client.renderer.GlStateManager.func_179114_b(this.f, partName.endsWith("1") ? 1.0F : -1.0F, 0.0F, 0.0F);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/PartModelClaws.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */