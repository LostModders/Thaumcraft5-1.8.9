/*    */ package thaumcraft.client.renderers.models.block;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ public class ModelBore
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Base;
/*    */   ModelRenderer Side1;
/*    */   ModelRenderer Side2;
/*    */   ModelRenderer NozCrossbar;
/*    */   ModelRenderer NozFront;
/*    */   ModelRenderer NozMid;
/*    */   
/*    */   public ModelBore()
/*    */   {
/* 19 */     this.field_78090_t = 128;
/* 20 */     this.field_78089_u = 64;
/*    */     
/* 22 */     this.Base = new ModelRenderer(this, 0, 32);
/* 23 */     this.Base.func_78789_a(-6.0F, 0.0F, -6.0F, 12, 2, 12);
/* 24 */     this.Base.func_78793_a(0.0F, 0.0F, 0.0F);
/* 25 */     this.Base.func_78787_b(64, 32);
/* 26 */     this.Base.field_78809_i = true;
/* 27 */     setRotation(this.Base, 0.0F, 0.0F, 0.0F);
/* 28 */     this.Side1 = new ModelRenderer(this, 0, 0);
/* 29 */     this.Side1.func_78789_a(-2.0F, 2.0F, -5.5F, 4, 8, 1);
/* 30 */     this.Side1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 31 */     this.Side1.func_78787_b(64, 32);
/* 32 */     this.Side1.field_78809_i = true;
/* 33 */     setRotation(this.Side1, 0.0F, 0.0F, 0.0F);
/* 34 */     this.Side2 = new ModelRenderer(this, 0, 0);
/* 35 */     this.Side2.func_78789_a(-2.0F, 2.0F, 4.5F, 4, 8, 1);
/* 36 */     this.Side2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 37 */     this.Side2.func_78787_b(64, 32);
/* 38 */     this.Side2.field_78809_i = true;
/* 39 */     setRotation(this.Side2, 0.0F, 0.0F, 0.0F);
/* 40 */     this.NozCrossbar = new ModelRenderer(this, 0, 48);
/* 41 */     this.NozCrossbar.func_78789_a(-1.0F, -1.0F, -6.0F, 2, 2, 12);
/* 42 */     this.NozCrossbar.func_78793_a(0.0F, 8.0F, 0.0F);
/* 43 */     this.NozCrossbar.func_78787_b(64, 32);
/* 44 */     this.NozCrossbar.field_78809_i = true;
/* 45 */     setRotation(this.NozCrossbar, 0.0F, 0.0F, 0.0F);
/* 46 */     this.NozFront = new ModelRenderer(this, 30, 14);
/* 47 */     this.NozFront.func_78789_a(4.0F, -2.5F, -2.5F, 4, 5, 5);
/* 48 */     this.NozFront.func_78793_a(0.0F, 8.0F, 0.0F);
/* 49 */     this.NozFront.func_78787_b(64, 32);
/* 50 */     this.NozFront.field_78809_i = true;
/* 51 */     setRotation(this.NozFront, 0.0F, 0.0F, 0.0F);
/* 52 */     this.NozMid = new ModelRenderer(this, 0, 14);
/* 53 */     this.NozMid.func_78789_a(-2.0F, -4.0F, -4.0F, 6, 8, 8);
/* 54 */     this.NozMid.func_78793_a(0.0F, 8.0F, 0.0F);
/* 55 */     this.NozMid.func_78787_b(64, 32);
/* 56 */     this.NozMid.field_78809_i = true;
/* 57 */     setRotation(this.NozMid, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void renderBase()
/*    */   {
/* 62 */     float f5 = 0.0625F;
/* 63 */     this.Base.func_78785_a(f5);
/* 64 */     this.Side1.func_78785_a(f5);
/* 65 */     this.Side2.func_78785_a(f5);
/* 66 */     this.NozCrossbar.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   public void renderNozzle()
/*    */   {
/* 71 */     float f5 = 0.0625F;
/* 72 */     this.NozFront.func_78785_a(f5);
/* 73 */     this.NozMid.func_78785_a(f5);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 80 */     model.field_78795_f = x;
/* 81 */     model.field_78796_g = y;
/* 82 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/block/ModelBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */