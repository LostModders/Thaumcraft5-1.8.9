/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelTrunk
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer chestLid;
/*     */   public ModelRenderer chestBelow;
/*     */   public ModelRenderer chestKnob;
/*     */   
/*     */   public ModelTrunk()
/*     */   {
/*  26 */     this.chestLid = new ModelRenderer(this, 0, 0).func_78787_b(64, 64);
/*  27 */     this.chestLid.func_78790_a(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
/*  28 */     this.chestLid.field_78800_c = 1.0F;
/*  29 */     this.chestLid.field_78797_d = 7.0F;
/*  30 */     this.chestLid.field_78798_e = 15.0F;
/*  31 */     this.chestKnob = new ModelRenderer(this, 0, 0).func_78787_b(64, 64);
/*  32 */     this.chestKnob.func_78790_a(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
/*  33 */     this.chestKnob.field_78800_c = 8.0F;
/*  34 */     this.chestKnob.field_78797_d = 7.0F;
/*  35 */     this.chestKnob.field_78798_e = 15.0F;
/*  36 */     this.chestBelow = new ModelRenderer(this, 0, 19).func_78787_b(64, 64);
/*  37 */     this.chestBelow.func_78790_a(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
/*  38 */     this.chestBelow.field_78800_c = 1.0F;
/*  39 */     this.chestBelow.field_78797_d = 6.0F;
/*  40 */     this.chestBelow.field_78798_e = 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  46 */     this.chestKnob.field_78795_f = this.chestLid.field_78795_f;
/*  47 */     this.chestLid.func_78785_a(0.0625F);
/*  48 */     this.chestBelow.func_78785_a(0.0625F);
/*  49 */     this.chestKnob.func_78785_a(0.0625F);
/*     */     
/*  51 */     GL11.glPushMatrix();
/*     */     
/*  53 */     GL11.glTranslatef(this.chestKnob.field_82906_o, this.chestKnob.field_82908_p, this.chestKnob.field_82907_q);
/*     */     
/*     */ 
/*  56 */     GL11.glEnable(3042);
/*  57 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  59 */     GL11.glPushMatrix();
/*  60 */     GL11.glTranslatef(this.chestKnob.field_78800_c * 0.0625F, this.chestKnob.field_78797_d * 0.0625F, this.chestKnob.field_78798_e * 0.0625F);
/*     */     
/*  62 */     if (this.chestKnob.field_78808_h != 0.0F)
/*     */     {
/*  64 */       GL11.glRotatef(this.chestKnob.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     
/*  67 */     if (this.chestKnob.field_78796_g != 0.0F)
/*     */     {
/*  69 */       GL11.glRotatef(this.chestKnob.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */     
/*  72 */     if (this.chestKnob.field_78795_f != 0.0F)
/*     */     {
/*  74 */       GL11.glRotatef(this.chestKnob.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  95 */     GL11.glPopMatrix();
/*     */     
/*  97 */     GL11.glDisable(3042);
/*     */     
/*  99 */     GL11.glTranslatef(-this.chestKnob.field_82906_o, -this.chestKnob.field_82908_p, -this.chestKnob.field_82907_q);
/*     */     
/* 101 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/entity/ModelTrunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */