/*    */ package thaumcraft.api.golems.parts;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartModel
/*    */ {
/*    */   private ResourceLocation objModel;
/*    */   private ResourceLocation texture;
/*    */   private EnumAttachPoint attachPoint;
/*    */   
/*    */   public static enum EnumAttachPoint
/*    */   {
/* 23 */     ARMS,  LEGS,  BODY,  HEAD;
/*    */     
/*    */     private EnumAttachPoint() {} }
/* 26 */   public PartModel(ResourceLocation objModel, ResourceLocation objTexture, EnumAttachPoint attachPoint) { this.objModel = objModel;
/* 27 */     this.texture = objTexture;
/* 28 */     this.attachPoint = attachPoint;
/*    */   }
/*    */   
/*    */   public ResourceLocation getObjModel() {
/* 32 */     return this.objModel;
/*    */   }
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 36 */     return this.texture;
/*    */   }
/*    */   
/*    */   public EnumAttachPoint getAttachPoint() {
/* 40 */     return this.attachPoint;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean useMaterialTextureForObjectPart(String partName)
/*    */   {
/* 51 */     return partName.startsWith("bm");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void preRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, EnumLimbSide side) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void postRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, EnumLimbSide side) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public float preRenderArmRotationX(IGolemAPI golem, float partialTicks, EnumLimbSide side, float inputRot)
/*    */   {
/* 76 */     return inputRot;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public float preRenderArmRotationY(IGolemAPI golem, float partialTicks, EnumLimbSide side, float inputRot)
/*    */   {
/* 85 */     return inputRot;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public float preRenderArmRotationZ(IGolemAPI golem, float partialTicks, EnumLimbSide side, float inputRot)
/*    */   {
/* 94 */     return inputRot;
/*    */   }
/*    */   
/*    */   public static enum EnumLimbSide {
/* 98 */     LEFT,  RIGHT,  MIDDLE;
/*    */     
/*    */     private EnumLimbSide() {}
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/parts/PartModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */