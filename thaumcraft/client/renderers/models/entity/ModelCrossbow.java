/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelCrossbow
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer crossl3;
/*     */   ModelRenderer crossr3;
/*     */   ModelRenderer loadbarcross;
/*     */   ModelRenderer loadbarl;
/*     */   ModelRenderer loadbarr;
/*     */   ModelRenderer barrel;
/*     */   ModelRenderer basebarcross;
/*     */   ModelRenderer ammobox;
/*     */   ModelRenderer crossbow;
/*     */   ModelRenderer basebarr;
/*     */   ModelRenderer basebarl;
/*     */   ModelRenderer crossl1;
/*     */   ModelRenderer crossl2;
/*     */   ModelRenderer crossr1;
/*     */   ModelRenderer crossr2;
/*     */   ModelRenderer tripod;
/*     */   ModelRenderer leg3;
/*     */   ModelRenderer leg4;
/*     */   ModelRenderer leg1;
/*     */   ModelRenderer leg2;
/*     */   
/*     */   public ModelCrossbow()
/*     */   {
/*  37 */     this.field_78090_t = 64;
/*  38 */     this.field_78089_u = 32;
/*     */     
/*  40 */     this.crossbow = new ModelRenderer(this, 28, 14);
/*  41 */     this.crossbow.func_78789_a(-2.0F, 0.0F, -7.0F, 4, 2, 14);
/*  42 */     this.crossbow.func_78793_a(0.0F, 10.0F, 0.0F);
/*  43 */     this.crossbow.func_78787_b(64, 32);
/*  44 */     this.crossbow.field_78809_i = true;
/*  45 */     setRotation(this.crossbow, 0.0F, 0.0F, 0.0F);
/*  46 */     this.basebarr = new ModelRenderer(this, 40, 23);
/*  47 */     this.basebarr.func_78789_a(-1.0F, 0.0F, 7.0F, 1, 2, 5);
/*  48 */     this.basebarr.func_78793_a(0.0F, 0.0F, 0.0F);
/*  49 */     this.basebarr.func_78787_b(64, 32);
/*  50 */     this.basebarr.field_78809_i = true;
/*  51 */     setRotation(this.basebarr, 0.0F, -0.1396263F, 0.0F);
/*  52 */     this.basebarl = new ModelRenderer(this, 40, 23);
/*  53 */     this.basebarl.func_78789_a(0.0F, 0.0F, 7.0F, 1, 2, 5);
/*  54 */     this.basebarl.func_78793_a(0.0F, 0.0F, 0.0F);
/*  55 */     this.basebarl.func_78787_b(64, 32);
/*  56 */     this.basebarl.field_78809_i = true;
/*  57 */     setRotation(this.basebarl, 0.0F, 0.1396263F, 0.0F);
/*  58 */     this.barrel = new ModelRenderer(this, 20, 28);
/*  59 */     this.barrel.func_78789_a(-1.0F, -1.0F, -8.0F, 2, 2, 2);
/*  60 */     this.barrel.func_78793_a(0.0F, 0.0F, 0.0F);
/*  61 */     this.barrel.func_78787_b(64, 32);
/*  62 */     this.barrel.field_78809_i = true;
/*  63 */     setRotation(this.barrel, 0.0F, 0.0F, 0.0F);
/*  64 */     this.basebarcross = new ModelRenderer(this, 0, 13);
/*  65 */     this.basebarcross.func_78789_a(-2.0F, 0.5F, 10.0F, 4, 1, 1);
/*  66 */     this.basebarcross.func_78793_a(0.0F, 0.0F, 0.0F);
/*  67 */     this.basebarcross.func_78787_b(64, 32);
/*  68 */     this.basebarcross.field_78809_i = true;
/*  69 */     setRotation(this.basebarcross, 0.0F, 0.0F, 0.0F);
/*  70 */     this.ammobox = new ModelRenderer(this, 38, 0);
/*  71 */     this.ammobox.func_78789_a(-2.0F, -5.0F, -6.0F, 4, 5, 9);
/*  72 */     this.ammobox.func_78793_a(0.0F, 0.0F, 0.0F);
/*  73 */     this.ammobox.func_78787_b(64, 32);
/*  74 */     this.ammobox.field_78809_i = true;
/*  75 */     setRotation(this.ammobox, 0.0F, 0.0F, 0.0F);
/*     */     
/*  77 */     this.loadbarcross = new ModelRenderer(this, 0, 13);
/*  78 */     this.loadbarcross.func_78789_a(-2.0F, -8.5F, -0.5F, 4, 1, 1);
/*  79 */     this.loadbarcross.func_78793_a(0.0F, 0.0F, 0.0F);
/*  80 */     this.loadbarcross.func_78787_b(64, 32);
/*  81 */     this.loadbarcross.field_78809_i = true;
/*  82 */     setRotation(this.loadbarcross, -0.5585054F, 0.0F, 0.0F);
/*  83 */     this.loadbarl = new ModelRenderer(this, 0, 15);
/*  84 */     this.loadbarl.func_78789_a(2.0F, -9.0F, -1.0F, 1, 11, 2);
/*  85 */     this.loadbarl.func_78793_a(0.0F, 0.0F, 0.0F);
/*  86 */     this.loadbarl.func_78787_b(64, 32);
/*  87 */     this.loadbarl.field_78809_i = true;
/*  88 */     setRotation(this.loadbarl, -0.5585054F, 0.0F, 0.0F);
/*  89 */     this.loadbarr = new ModelRenderer(this, 0, 15);
/*  90 */     this.loadbarr.func_78789_a(-3.0F, -9.0F, -1.0F, 1, 11, 2);
/*  91 */     this.loadbarr.func_78793_a(0.0F, 0.0F, 0.0F);
/*  92 */     this.loadbarr.func_78787_b(64, 32);
/*  93 */     this.loadbarr.field_78809_i = true;
/*  94 */     setRotation(this.loadbarr, -0.5585054F, 0.0F, 0.0F);
/*     */     
/*  96 */     this.crossl1 = new ModelRenderer(this, 0, 0);
/*  97 */     this.crossl1.func_78789_a(0.0F, 0.0F, -6.0F, 5, 2, 1);
/*  98 */     this.crossl1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  99 */     this.crossl1.func_78787_b(64, 32);
/* 100 */     this.crossl1.field_78809_i = true;
/* 101 */     setRotation(this.crossl1, 0.0F, -0.2443461F, 0.0F);
/* 102 */     this.crossl2 = new ModelRenderer(this, 0, 0);
/* 103 */     this.crossl2.func_78789_a(4.0F, 0.0F, -5.0F, 3, 2, 1);
/* 104 */     this.crossl2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */     this.crossl2.func_78787_b(64, 32);
/* 106 */     this.crossl2.field_78809_i = true;
/* 107 */     setRotation(this.crossl2, 0.0F, -0.2443461F, 0.0F);
/* 108 */     this.crossl3 = new ModelRenderer(this, 0, 0);
/* 109 */     this.crossl3.func_78789_a(6.0F, 0.0F, -4.0F, 2, 2, 1);
/* 110 */     this.crossl3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 111 */     this.crossl3.func_78787_b(64, 32);
/* 112 */     this.crossl3.field_78809_i = true;
/* 113 */     setRotation(this.crossl3, 0.0F, -0.2443461F, 0.0F);
/* 114 */     this.crossr1 = new ModelRenderer(this, 0, 0);
/* 115 */     this.crossr1.func_78789_a(-5.0F, 0.0F, -6.0F, 5, 2, 1);
/* 116 */     this.crossr1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 117 */     this.crossr1.func_78787_b(64, 32);
/* 118 */     this.crossr1.field_78809_i = true;
/* 119 */     setRotation(this.crossr1, 0.0F, 0.2443461F, 0.0F);
/* 120 */     this.crossr2 = new ModelRenderer(this, 0, 0);
/* 121 */     this.crossr2.func_78789_a(-7.0F, 0.0F, -5.0F, 3, 2, 1);
/* 122 */     this.crossr2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 123 */     this.crossr2.func_78787_b(64, 32);
/* 124 */     this.crossr2.field_78809_i = true;
/* 125 */     setRotation(this.crossr2, 0.0F, 0.2443461F, 0.0F);
/* 126 */     this.crossr3 = new ModelRenderer(this, 0, 0);
/* 127 */     this.crossr3.func_78789_a(-8.0F, 0.0F, -4.0F, 2, 2, 1);
/* 128 */     this.crossr3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 129 */     this.crossr3.func_78787_b(64, 32);
/* 130 */     this.crossr3.field_78809_i = true;
/* 131 */     setRotation(this.crossr3, 0.0F, 0.2443461F, 0.0F);
/*     */     
/* 133 */     this.leg2 = new ModelRenderer(this, 20, 10);
/* 134 */     this.leg2.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/* 135 */     this.leg2.func_78793_a(0.0F, 12.0F, 0.0F);
/* 136 */     this.leg2.func_78787_b(64, 32);
/* 137 */     this.leg2.field_78809_i = true;
/* 138 */     setRotation(this.leg2, 0.5235988F, 1.570796F, 0.0F);
/* 139 */     this.tripod = new ModelRenderer(this, 13, 0);
/* 140 */     this.tripod.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 2, 3);
/* 141 */     this.tripod.func_78793_a(0.0F, 12.0F, 0.0F);
/* 142 */     this.tripod.func_78787_b(64, 32);
/* 143 */     this.tripod.field_78809_i = true;
/* 144 */     setRotation(this.tripod, 0.0F, 0.0F, 0.0F);
/* 145 */     this.leg3 = new ModelRenderer(this, 20, 10);
/* 146 */     this.leg3.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/* 147 */     this.leg3.func_78793_a(0.0F, 12.0F, 0.0F);
/* 148 */     this.leg3.func_78787_b(64, 32);
/* 149 */     this.leg3.field_78809_i = true;
/* 150 */     setRotation(this.leg3, 0.5235988F, 3.141593F, 0.0F);
/* 151 */     this.leg4 = new ModelRenderer(this, 20, 10);
/* 152 */     this.leg4.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/* 153 */     this.leg4.func_78793_a(0.0F, 12.0F, 0.0F);
/* 154 */     this.leg4.func_78787_b(64, 32);
/* 155 */     this.leg4.field_78809_i = true;
/* 156 */     setRotation(this.leg4, 0.5235988F, 4.712389F, 0.0F);
/* 157 */     this.leg1 = new ModelRenderer(this, 20, 10);
/* 158 */     this.leg1.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/* 159 */     this.leg1.func_78793_a(0.0F, 12.0F, 0.0F);
/* 160 */     this.leg1.func_78787_b(64, 32);
/* 161 */     this.leg1.field_78809_i = true;
/* 162 */     setRotation(this.leg1, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 164 */     this.crossbow.func_78792_a(this.ammobox);
/* 165 */     this.crossbow.func_78792_a(this.barrel);
/* 166 */     this.crossbow.func_78792_a(this.basebarcross);
/* 167 */     this.crossbow.func_78792_a(this.basebarr);
/* 168 */     this.crossbow.func_78792_a(this.basebarl);
/*     */     
/* 170 */     this.crossbow.func_78792_a(this.loadbarcross);
/* 171 */     this.crossbow.func_78792_a(this.loadbarl);
/* 172 */     this.crossbow.func_78792_a(this.loadbarr);
/*     */     
/* 174 */     this.crossbow.func_78792_a(this.crossl1);
/* 175 */     this.crossbow.func_78792_a(this.crossl2);
/* 176 */     this.crossbow.func_78792_a(this.crossl3);
/* 177 */     this.crossbow.func_78792_a(this.crossr1);
/* 178 */     this.crossbow.func_78792_a(this.crossr2);
/* 179 */     this.crossbow.func_78792_a(this.crossr3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 185 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 186 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 188 */     this.crossbow.func_78785_a(f5);
/*     */     
/* 190 */     this.leg2.func_78785_a(f5);
/* 191 */     this.tripod.func_78785_a(f5);
/* 192 */     this.leg3.func_78785_a(f5);
/* 193 */     this.leg4.func_78785_a(f5);
/* 194 */     this.leg1.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 199 */     model.field_78795_f = x;
/* 200 */     model.field_78796_g = y;
/* 201 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float headpitch, float headyaw, float p_78087_6_, Entity entity)
/*     */   {
/* 206 */     this.crossbow.field_78796_g = (headpitch / 57.295776F);
/* 207 */     this.crossbow.field_78795_f = (headyaw / 57.295776F);
/*     */     
/* 209 */     if (this.field_78095_p > -9990.0F)
/*     */     {
/* 211 */       float f6 = this.field_78095_p;
/* 212 */       this.crossl1.field_78796_g = (this.crossl2.field_78796_g = this.crossl3.field_78796_g = -0.2F + MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/*     */       
/* 214 */       this.crossr1.field_78796_g = (this.crossr2.field_78796_g = this.crossr3.field_78796_g = 0.2F - MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/*     */     }
/*     */     
/*     */ 
/* 218 */     float lp = ((EntityTurretCrossbow)entity).loadProgressForRender;
/* 219 */     this.loadbarcross.field_78795_f = (this.loadbarl.field_78795_f = this.loadbarr.field_78795_f = -0.5F + MathHelper.func_76126_a(MathHelper.func_76129_c(lp) * 3.1415927F * 2.0F) * 0.5F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/entity/ModelCrossbow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */