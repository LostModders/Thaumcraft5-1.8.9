/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.construct.EntityNodeMagnet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelNodeMagnet
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer crystal;
/*     */   ModelRenderer leg2;
/*     */   ModelRenderer tripod;
/*     */   ModelRenderer leg3;
/*     */   ModelRenderer leg4;
/*     */   ModelRenderer leg1;
/*     */   ModelRenderer magl;
/*     */   ModelRenderer magbase;
/*     */   ModelRenderer magcross;
/*     */   ModelRenderer magr;
/*     */   ModelRenderer base;
/*     */   ModelRenderer domebase;
/*     */   ModelRenderer dome;
/*     */   
/*     */   public ModelNodeMagnet()
/*     */   {
/*  32 */     this.field_78090_t = 64;
/*  33 */     this.field_78089_u = 32;
/*     */     
/*     */ 
/*  36 */     this.leg2 = new ModelRenderer(this, 20, 10);
/*  37 */     this.leg2.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/*  38 */     this.leg2.func_78793_a(0.0F, 12.0F, 0.0F);
/*  39 */     this.leg2.func_78787_b(64, 32);
/*  40 */     setRotation(this.leg2, 0.5235988F, 1.570796F, 0.0F);
/*  41 */     this.tripod = new ModelRenderer(this, 13, 0);
/*  42 */     this.tripod.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 2, 3);
/*  43 */     this.tripod.func_78793_a(0.0F, 12.0F, 0.0F);
/*  44 */     this.tripod.func_78787_b(64, 32);
/*  45 */     setRotation(this.tripod, 0.0F, 0.0F, 0.0F);
/*  46 */     this.leg3 = new ModelRenderer(this, 20, 10);
/*  47 */     this.leg3.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/*  48 */     this.leg3.func_78793_a(0.0F, 12.0F, 0.0F);
/*  49 */     this.leg3.func_78787_b(64, 32);
/*  50 */     setRotation(this.leg3, 0.5235988F, 3.141593F, 0.0F);
/*  51 */     this.leg4 = new ModelRenderer(this, 20, 10);
/*  52 */     this.leg4.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/*  53 */     this.leg4.func_78793_a(0.0F, 12.0F, 0.0F);
/*  54 */     this.leg4.func_78787_b(64, 32);
/*  55 */     setRotation(this.leg4, 0.5235988F, 4.712389F, 0.0F);
/*  56 */     this.leg1 = new ModelRenderer(this, 20, 10);
/*  57 */     this.leg1.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/*  58 */     this.leg1.func_78793_a(0.0F, 12.0F, 0.0F);
/*  59 */     this.leg1.func_78787_b(64, 32);
/*  60 */     setRotation(this.leg1, 0.5235988F, 0.0F, 0.0F);
/*     */     
/*  62 */     this.base = new ModelRenderer(this, 32, 0);
/*  63 */     this.base.func_78789_a(-3.0F, -6.0F, -3.0F, 6, 6, 6);
/*  64 */     this.base.func_78793_a(0.0F, 13.0F, 0.0F);
/*  65 */     this.base.func_78787_b(64, 32);
/*  66 */     setRotation(this.base, 0.0F, 0.0F, 0.0F);this.crystal = new ModelRenderer(this, 32, 25);
/*  67 */     this.crystal.func_78789_a(-1.0F, -4.0F, 5.0F, 2, 2, 2);
/*  68 */     this.crystal.func_78793_a(0.0F, 0.0F, 0.0F);
/*  69 */     this.crystal.func_78787_b(64, 32);
/*  70 */     setRotation(this.crystal, 0.0F, 0.0F, 0.0F);
/*  71 */     this.domebase = new ModelRenderer(this, 32, 19);
/*  72 */     this.domebase.func_78789_a(-2.0F, -5.0F, 3.0F, 4, 4, 1);
/*  73 */     this.domebase.func_78793_a(0.0F, 0.0F, 0.0F);
/*  74 */     this.domebase.func_78787_b(64, 32);
/*  75 */     setRotation(this.domebase, 0.0F, 0.0F, 0.0F);
/*  76 */     this.dome = new ModelRenderer(this, 44, 16);
/*  77 */     this.dome.func_78789_a(-2.0F, -5.0F, 4.0F, 4, 4, 4);
/*  78 */     this.dome.func_78793_a(0.0F, 0.0F, 0.0F);
/*  79 */     this.dome.func_78787_b(64, 32);
/*  80 */     setRotation(this.dome, 0.0F, 0.0F, 0.0F);
/*     */     
/*  82 */     this.magbase = new ModelRenderer(this, 0, 18);
/*  83 */     this.magbase.func_78789_a(-1.0F, -1.0F, -4.0F, 2, 2, 1);
/*  84 */     this.magbase.func_78793_a(0.0F, -3.0F, 0.0F);
/*  85 */     this.magbase.func_78787_b(64, 32);
/*  86 */     setRotation(this.magbase, 0.0F, 0.0F, 0.0F);
/*  87 */     this.magcross = new ModelRenderer(this, 0, 14);
/*  88 */     this.magcross.func_78789_a(-3.0F, -1.0F, -6.0F, 6, 2, 2);
/*  89 */     this.magcross.func_78793_a(0.0F, -3.0F, 0.0F);
/*  90 */     this.magcross.func_78787_b(64, 32);
/*  91 */     setRotation(this.magcross, 0.0F, 0.0F, 0.0F);
/*  92 */     this.magr = new ModelRenderer(this, 0, 8);
/*  93 */     this.magr.func_78789_a(-4.0F, -1.0F, -9.0F, 2, 2, 4);
/*  94 */     this.magr.func_78793_a(0.0F, -3.0F, 0.0F);
/*  95 */     this.magr.func_78787_b(64, 32);
/*  96 */     this.magr.field_78809_i = true;
/*  97 */     setRotation(this.magr, 0.0F, 0.0F, 0.0F);
/*  98 */     this.magl = new ModelRenderer(this, 0, 8);
/*  99 */     this.magl.func_78789_a(2.0F, -1.0F, -9.0F, 2, 2, 4);
/* 100 */     this.magl.func_78793_a(0.0F, -3.0F, 0.0F);
/* 101 */     this.magl.func_78787_b(64, 32);
/* 102 */     this.magr.field_78809_i = true;
/* 103 */     setRotation(this.magl, 0.0F, 0.0F, 0.0F);
/*     */     
/* 105 */     this.base.func_78792_a(this.crystal);
/* 106 */     this.base.func_78792_a(this.dome);
/* 107 */     this.base.func_78792_a(this.domebase);
/* 108 */     this.base.func_78792_a(this.magbase);
/* 109 */     this.base.func_78792_a(this.magcross);
/* 110 */     this.base.func_78792_a(this.magl);
/* 111 */     this.base.func_78792_a(this.magr);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 117 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 118 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 120 */     this.leg2.func_78785_a(f5);
/* 121 */     this.tripod.func_78785_a(f5);
/* 122 */     this.leg3.func_78785_a(f5);
/* 123 */     this.leg4.func_78785_a(f5);
/* 124 */     this.leg1.func_78785_a(f5);
/*     */     
/* 126 */     GL11.glEnable(3042);
/* 127 */     GL11.glBlendFunc(770, 771);
/* 128 */     this.base.func_78785_a(f5);
/* 129 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */ 
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 135 */     model.field_78795_f = x;
/* 136 */     model.field_78796_g = y;
/* 137 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float headpitch, float headyaw, float p_78087_6_, Entity entity)
/*     */   {
/* 143 */     this.base.field_78796_g = (headpitch / 57.295776F);
/* 144 */     this.base.field_78795_f = (headyaw / 57.295776F);
/* 145 */     this.magbase.field_78808_h = (this.magcross.field_78808_h = this.magl.field_78808_h = this.magr.field_78808_h = ((EntityNodeMagnet)entity).rot / 57.295776F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/entity/ModelNodeMagnet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */