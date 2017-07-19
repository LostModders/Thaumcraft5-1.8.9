/*    */ package thaumcraft.client.renderers.models.block;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelJar
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer Core;
/*    */   public ModelRenderer Brine;
/*    */   public ModelRenderer Lid;
/*    */   public ModelRenderer LidExtension;
/*    */   
/*    */   public ModelJar()
/*    */   {
/* 19 */     this.field_78090_t = 64;
/* 20 */     this.field_78089_u = 32;
/*    */     
/* 22 */     this.Core = new ModelRenderer(this, 0, 0);
/* 23 */     this.Core.func_78789_a(-5.0F, -12.0F, -5.0F, 10, 12, 10);
/* 24 */     this.Core.func_78793_a(0.0F, 0.0F, 0.0F);
/* 25 */     this.Core.func_78787_b(64, 32);
/* 26 */     this.Core.field_78809_i = true;
/* 27 */     setRotation(this.Core, 0.0F, 0.0F, 0.0F);
/*    */     
/* 29 */     this.Brine = new ModelRenderer(this, 0, 0);
/* 30 */     this.Brine.func_78789_a(-4.0F, -11.0F, -4.0F, 8, 10, 8);
/* 31 */     this.Brine.func_78793_a(0.0F, 0.0F, 0.0F);
/* 32 */     this.Brine.func_78787_b(64, 32);
/* 33 */     this.Brine.field_78809_i = true;
/* 34 */     setRotation(this.Brine, 0.0F, 0.0F, 0.0F);
/*    */     
/* 36 */     this.Lid = new ModelRenderer(this, 32, 24);
/* 37 */     this.Lid.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 2, 6);
/* 38 */     this.Lid.func_78793_a(0.0F, -14.0F, 0.0F);
/* 39 */     this.Lid.func_78787_b(64, 32);
/* 40 */     this.Lid.field_78809_i = true;
/*    */     
/*    */ 
/* 43 */     this.LidExtension = new ModelRenderer(this, 0, 23);
/* 44 */     this.LidExtension.func_78789_a(-2.0F, -16.0F, -2.0F, 4, 2, 4);
/* 45 */     this.LidExtension.func_78793_a(0.0F, 0.0F, 0.0F);
/* 46 */     this.LidExtension.func_78787_b(64, 32);
/* 47 */     this.LidExtension.field_78809_i = true;
/*    */   }
/*    */   
/*    */   public void renderBrine()
/*    */   {
/* 52 */     GL11.glEnable(3042);
/* 53 */     GL11.glBlendFunc(770, 771);
/* 54 */     this.Brine.func_78785_a(0.0625F);
/* 55 */     GL11.glDisable(3042);
/*    */   }
/*    */   
/*    */   public void renderLidExtension()
/*    */   {
/* 60 */     this.LidExtension.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   public void renderLidBrace()
/*    */   {
/* 65 */     this.Lid.func_78785_a(0.0625F);
/*    */   }
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
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 79 */     model.field_78795_f = x;
/* 80 */     model.field_78796_g = y;
/* 81 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/block/ModelJar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */