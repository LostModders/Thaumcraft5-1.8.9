/*    */ package thaumcraft.client.renderers.entity.construct;
/*    */ 
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel.EnumLimbSide;
/*    */ 
/*    */ public class PartModelDarts extends thaumcraft.api.golems.parts.PartModel
/*    */ {
/*    */   public PartModelDarts(net.minecraft.util.ResourceLocation objModel, net.minecraft.util.ResourceLocation objTexture, thaumcraft.api.golems.parts.PartModel.EnumAttachPoint attachPoint)
/*    */   {
/* 10 */     super(objModel, objTexture, attachPoint);
/*    */   }
/*    */   
/*    */   public float preRenderArmRotationX(IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side, float inputRot)
/*    */   {
/* 15 */     if (golem.isInCombat()) {
/* 16 */       inputRot = 90.0F - golem.getGolemEntity().field_70127_C + inputRot / 10.0F;
/*    */     }
/* 18 */     return inputRot;
/*    */   }
/*    */   
/*    */   public float preRenderArmRotationY(IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side, float inputRot)
/*    */   {
/* 23 */     if (golem.isInCombat()) {
/* 24 */       inputRot /= 10.0F;
/*    */     }
/* 26 */     return inputRot;
/*    */   }
/*    */   
/*    */   public float preRenderArmRotationZ(IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side, float inputRot)
/*    */   {
/* 31 */     if (golem.isInCombat()) {
/* 32 */       inputRot /= 10.0F;
/*    */     }
/* 34 */     return inputRot;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/PartModelDarts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */