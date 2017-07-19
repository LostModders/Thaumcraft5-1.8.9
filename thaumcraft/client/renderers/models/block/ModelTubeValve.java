/*    */ package thaumcraft.client.renderers.models.block;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ public class ModelTubeValve
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer ValveRod;
/*    */   ModelRenderer ValveRing;
/*    */   
/*    */   public ModelTubeValve()
/*    */   {
/* 15 */     this.field_78090_t = 64;
/* 16 */     this.field_78089_u = 32;
/*    */     
/* 18 */     this.ValveRod = new ModelRenderer(this, 0, 10);
/* 19 */     this.ValveRod.func_78789_a(-1.0F, 2.0F, -1.0F, 2, 2, 2);
/* 20 */     this.ValveRod.func_78793_a(0.0F, 0.0F, 0.0F);
/* 21 */     this.ValveRod.func_78787_b(64, 32);
/* 22 */     this.ValveRod.field_78809_i = true;
/* 23 */     setRotation(this.ValveRod, 0.0F, 0.0F, 0.0F);
/*    */     
/* 25 */     this.ValveRing = new ModelRenderer(this, 0, 0);
/* 26 */     this.ValveRing.func_78789_a(-2.0F, 4.0F, -2.0F, 4, 1, 4);
/* 27 */     this.ValveRing.func_78793_a(0.0F, 0.0F, 0.0F);
/* 28 */     this.ValveRing.func_78787_b(64, 32);
/* 29 */     this.ValveRing.field_78809_i = true;
/* 30 */     setRotation(this.ValveRing, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void renderRod()
/*    */   {
/* 35 */     this.ValveRod.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   public void renderRing()
/*    */   {
/* 40 */     this.ValveRing.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 45 */     model.field_78795_f = x;
/* 46 */     model.field_78796_g = y;
/* 47 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/block/ModelTubeValve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */