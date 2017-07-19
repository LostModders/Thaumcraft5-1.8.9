/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelPech
/*     */   extends ModelBiped
/*     */ {
/*     */   ModelRenderer Jowls;
/*     */   ModelRenderer LowerPack;
/*     */   ModelRenderer UpperPack;
/*     */   
/*     */   public ModelPech()
/*     */   {
/*  24 */     this.field_78090_t = 128;
/*  25 */     this.field_78089_u = 64;
/*     */     
/*  27 */     this.field_78115_e = new ModelRenderer(this, 34, 12);
/*  28 */     this.field_78115_e.func_78789_a(-3.0F, 0.0F, 0.0F, 6, 10, 6);
/*  29 */     this.field_78115_e.func_78793_a(0.0F, 9.0F, -3.0F);
/*  30 */     this.field_78115_e.func_78787_b(128, 64);
/*  31 */     this.field_78115_e.field_78809_i = true;
/*  32 */     setRotation(this.field_78115_e, 0.3129957F, 0.0F, 0.0F);
/*     */     
/*  34 */     this.field_178721_j = new ModelRenderer(this, 35, 1);
/*  35 */     this.field_178721_j.field_78809_i = true;
/*  36 */     this.field_178721_j.func_78789_a(-2.9F, 0.0F, 0.0F, 3, 6, 3);
/*  37 */     this.field_178721_j.func_78793_a(0.0F, 18.0F, 0.0F);
/*  38 */     this.field_178721_j.func_78787_b(128, 64);
/*  39 */     this.field_178721_j.field_78809_i = true;
/*  40 */     setRotation(this.field_178721_j, 0.0F, 0.0F, 0.0F);
/*  41 */     this.field_178721_j.field_78809_i = false;
/*     */     
/*  43 */     this.field_178722_k = new ModelRenderer(this, 35, 1);
/*  44 */     this.field_178722_k.func_78789_a(-0.1F, 0.0F, 0.0F, 3, 6, 3);
/*  45 */     this.field_178722_k.func_78793_a(0.0F, 18.0F, 0.0F);
/*  46 */     this.field_178722_k.func_78787_b(128, 64);
/*  47 */     this.field_178722_k.field_78809_i = true;
/*  48 */     setRotation(this.field_178722_k, 0.0F, 0.0F, 0.0F);
/*     */     
/*  50 */     this.field_78116_c = new ModelRenderer(this, 2, 11);
/*  51 */     this.field_78116_c.func_78789_a(-3.5F, -5.0F, -5.0F, 7, 5, 5);
/*  52 */     this.field_78116_c.func_78793_a(0.0F, 8.0F, 0.0F);
/*  53 */     this.field_78116_c.func_78787_b(128, 64);
/*  54 */     this.field_78116_c.field_78809_i = true;
/*  55 */     setRotation(this.field_78116_c, 0.0F, 0.0F, 0.0F);
/*     */     
/*  57 */     this.Jowls = new ModelRenderer(this, 1, 21);
/*  58 */     this.Jowls.func_78789_a(-4.0F, -1.0F, -6.0F, 8, 3, 5);
/*  59 */     this.Jowls.func_78793_a(0.0F, 8.0F, 0.0F);
/*  60 */     this.Jowls.func_78787_b(128, 64);
/*  61 */     this.Jowls.field_78809_i = true;
/*  62 */     setRotation(this.Jowls, 0.0F, 0.0F, 0.0F);
/*     */     
/*  64 */     this.LowerPack = new ModelRenderer(this, 0, 0);
/*  65 */     this.LowerPack.func_78789_a(-5.0F, 0.0F, 0.0F, 10, 5, 5);
/*  66 */     this.LowerPack.func_78793_a(0.0F, 10.0F, 3.5F);
/*  67 */     this.LowerPack.func_78787_b(128, 64);
/*  68 */     this.LowerPack.field_78809_i = true;
/*  69 */     setRotation(this.LowerPack, 0.3013602F, 0.0F, 0.0F);
/*     */     
/*  71 */     this.UpperPack = new ModelRenderer(this, 64, 1);
/*  72 */     this.UpperPack.func_78789_a(-7.5F, -14.0F, 0.0F, 15, 14, 11);
/*  73 */     this.UpperPack.func_78793_a(0.0F, 10.0F, 3.0F);
/*  74 */     this.UpperPack.func_78787_b(128, 64);
/*  75 */     this.UpperPack.field_78809_i = true;
/*  76 */     setRotation(this.UpperPack, 0.4537856F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*  79 */     this.field_178723_h = new ModelRenderer(this, 52, 2);
/*  80 */     this.field_178723_h.field_78809_i = true;
/*  81 */     this.field_178723_h.func_78789_a(-2.0F, 0.0F, -1.0F, 2, 6, 2);
/*  82 */     this.field_178723_h.func_78793_a(-3.0F, 10.0F, -1.0F);
/*  83 */     this.field_178723_h.func_78787_b(128, 64);
/*  84 */     this.field_178723_h.field_78809_i = true;
/*  85 */     setRotation(this.field_178723_h, 0.0F, 0.0F, 0.0F);
/*  86 */     this.field_178723_h.field_78809_i = false;
/*     */     
/*  88 */     this.field_178724_i = new ModelRenderer(this, 52, 2);
/*  89 */     this.field_178724_i.func_78789_a(0.0F, 0.0F, -1.0F, 2, 6, 2);
/*  90 */     this.field_178724_i.func_78793_a(3.0F, 10.0F, -1.0F);
/*  91 */     this.field_178724_i.func_78787_b(128, 64);
/*  92 */     this.field_178724_i.field_78809_i = true;
/*  93 */     setRotation(this.field_178724_i, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  99 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/* 100 */     this.field_78115_e.func_78785_a(par7);
/* 101 */     this.field_178721_j.func_78785_a(par7);
/* 102 */     this.field_178722_k.func_78785_a(par7);
/* 103 */     this.field_78116_c.func_78785_a(par7);
/* 104 */     this.Jowls.func_78785_a(par7);
/* 105 */     this.LowerPack.func_78785_a(par7);
/* 106 */     this.UpperPack.func_78785_a(par7);
/* 107 */     this.field_178723_h.func_78785_a(par7);
/* 108 */     this.field_178724_i.func_78785_a(par7);
/*     */   }
/*     */   
/*     */ 
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 114 */     model.field_78795_f = x;
/* 115 */     model.field_78796_g = y;
/* 116 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 122 */     this.field_78116_c.field_78796_g = (par4 / 57.295776F);
/* 123 */     this.field_78116_c.field_78795_f = (par5 / 57.295776F);
/*     */     
/* 125 */     float mumble = 0.0F;
/* 126 */     if ((entity instanceof EntityPech)) {
/* 127 */       mumble = ((EntityPech)entity).mumble;
/*     */     }
/*     */     
/* 130 */     this.Jowls.field_78796_g = this.field_78116_c.field_78796_g;
/* 131 */     this.Jowls.field_78795_f = (this.field_78116_c.field_78795_f + (0.2617994F + MathHelper.func_76134_b(par1 * 0.6662F) * par2 * 0.25F) + 0.34906587F * Math.abs(MathHelper.func_76126_a(mumble)));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 136 */     this.field_178723_h.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 137 */     this.field_178724_i.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 138 */     this.field_178723_h.field_78808_h = 0.0F;
/* 139 */     this.field_178724_i.field_78808_h = 0.0F;
/* 140 */     this.field_178721_j.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 141 */     this.field_178722_k.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 142 */     this.field_178721_j.field_78796_g = 0.0F;
/* 143 */     this.field_178722_k.field_78796_g = 0.0F;
/*     */     
/* 145 */     this.LowerPack.field_78796_g = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.125F);
/* 146 */     this.LowerPack.field_78808_h = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.125F);
/*     */     
/* 148 */     if (this.field_78093_q)
/*     */     {
/* 150 */       this.field_178723_h.field_78795_f += -0.62831855F;
/* 151 */       this.field_178724_i.field_78795_f += -0.62831855F;
/* 152 */       this.field_178721_j.field_78795_f = -1.2566371F;
/* 153 */       this.field_178722_k.field_78795_f = -1.2566371F;
/* 154 */       this.field_178721_j.field_78796_g = 0.31415927F;
/* 155 */       this.field_178722_k.field_78796_g = -0.31415927F;
/*     */     }
/*     */     
/* 158 */     this.field_178723_h.field_78796_g = 0.0F;
/* 159 */     this.field_178724_i.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 163 */     if (this.field_78095_p > -9990.0F)
/*     */     {
/* 165 */       float f6 = this.field_78095_p;
/* 166 */       this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g;
/* 167 */       this.field_178724_i.field_78796_g += this.field_78115_e.field_78796_g;
/* 168 */       this.field_178724_i.field_78795_f += this.field_78115_e.field_78796_g;
/* 169 */       f6 = 1.0F - this.field_78095_p;
/* 170 */       f6 *= f6;
/* 171 */       f6 *= f6;
/* 172 */       f6 = 1.0F - f6;
/* 173 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 174 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/* 175 */       this.field_178723_h.field_78795_f = ((float)(this.field_178723_h.field_78795_f - (f7 * 1.2D + f8)));
/* 176 */       this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g * 2.0F;
/* 177 */       this.field_178723_h.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 180 */     if (entity.func_70093_af())
/*     */     {
/* 182 */       this.field_178723_h.field_78795_f += 0.4F;
/* 183 */       this.field_178724_i.field_78795_f += 0.4F;
/*     */     }
/*     */     
/* 186 */     this.field_178723_h.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 187 */     this.field_178724_i.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 188 */     this.field_178723_h.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 189 */     this.field_178724_i.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/entity/ModelPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */