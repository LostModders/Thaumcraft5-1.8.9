/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Rotations;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class ModelCustomArmor extends ModelBiped
/*     */ {
/*     */   public ModelCustomArmor(float f, int i, int j, int k)
/*     */   {
/*  16 */     super(f, i, j, k);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity)
/*     */   {
/*  24 */     if ((entity instanceof EntityLivingBase)) {
/*  25 */       this.field_78095_p = ((EntityLivingBase)entity).func_70678_g(UtilsFX.sysPartialTicks);
/*     */     }
/*     */     
/*  28 */     if ((entity instanceof EntityArmorStand))
/*     */     {
/*  30 */       setRotationAnglesStand(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entity);
/*     */     }
/*  32 */     else if (((entity instanceof net.minecraft.entity.monster.EntitySkeleton)) || ((entity instanceof net.minecraft.entity.monster.EntityZombie))) {
/*  33 */       setRotationAnglesZombie(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entity);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*  39 */       this.field_78116_c.field_78796_g = (p_78087_4_ / 57.295776F);
/*  40 */       this.field_78116_c.field_78795_f = (p_78087_5_ / 57.295776F);
/*  41 */       this.field_178723_h.field_78795_f = (MathHelper.func_76134_b(p_78087_1_ * 0.6662F + 3.1415927F) * 2.0F * p_78087_2_ * 0.5F);
/*  42 */       this.field_178724_i.field_78795_f = (MathHelper.func_76134_b(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F);
/*  43 */       this.field_178723_h.field_78808_h = 0.0F;
/*  44 */       this.field_178724_i.field_78808_h = 0.0F;
/*  45 */       this.field_178721_j.field_78795_f = (MathHelper.func_76134_b(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_);
/*  46 */       this.field_178722_k.field_78795_f = (MathHelper.func_76134_b(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_);
/*  47 */       this.field_178721_j.field_78796_g = 0.0F;
/*  48 */       this.field_178722_k.field_78796_g = 0.0F;
/*     */       
/*  50 */       if (this.field_78093_q)
/*     */       {
/*  52 */         this.field_178723_h.field_78795_f += -0.62831855F;
/*  53 */         this.field_178724_i.field_78795_f += -0.62831855F;
/*  54 */         this.field_178721_j.field_78795_f = -1.2566371F;
/*  55 */         this.field_178722_k.field_78795_f = -1.2566371F;
/*  56 */         this.field_178721_j.field_78796_g = 0.31415927F;
/*  57 */         this.field_178722_k.field_78796_g = -0.31415927F;
/*     */       }
/*     */       
/*  60 */       if (this.field_78119_l != 0)
/*     */       {
/*  62 */         this.field_178724_i.field_78795_f = (this.field_178724_i.field_78795_f * 0.5F - 0.31415927F * this.field_78119_l);
/*     */       }
/*     */       
/*  65 */       this.field_178723_h.field_78796_g = 0.0F;
/*  66 */       this.field_178723_h.field_78808_h = 0.0F;
/*     */       
/*  68 */       switch (this.field_78120_m)
/*     */       {
/*     */       case 0: 
/*     */       case 2: 
/*     */       default: 
/*     */         break;
/*     */       case 1: 
/*  75 */         this.field_178723_h.field_78795_f = (this.field_178723_h.field_78795_f * 0.5F - 0.31415927F * this.field_78120_m);
/*  76 */         break;
/*     */       case 3: 
/*  78 */         this.field_178723_h.field_78795_f = (this.field_178723_h.field_78795_f * 0.5F - 0.31415927F * this.field_78120_m);
/*  79 */         this.field_178723_h.field_78796_g = -0.5235988F;
/*     */       }
/*     */       
/*  82 */       this.field_178724_i.field_78796_g = 0.0F;
/*     */       
/*     */ 
/*     */ 
/*  86 */       if (this.field_78095_p > -9990.0F)
/*     */       {
/*  88 */         float f6 = this.field_78095_p;
/*  89 */         this.field_78115_e.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/*  90 */         this.field_178723_h.field_78798_e = (MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F);
/*  91 */         this.field_178723_h.field_78800_c = (-MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F);
/*  92 */         this.field_178724_i.field_78798_e = (-MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F);
/*  93 */         this.field_178724_i.field_78800_c = (MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F);
/*  94 */         this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g;
/*  95 */         this.field_178724_i.field_78796_g += this.field_78115_e.field_78796_g;
/*  96 */         this.field_178724_i.field_78795_f += this.field_78115_e.field_78796_g;
/*  97 */         f6 = 1.0F - this.field_78095_p;
/*  98 */         f6 *= f6;
/*  99 */         f6 *= f6;
/* 100 */         f6 = 1.0F - f6;
/* 101 */         float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 102 */         float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/* 103 */         this.field_178723_h.field_78795_f = ((float)(this.field_178723_h.field_78795_f - (f7 * 1.2D + f8)));
/* 104 */         this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g * 2.0F;
/* 105 */         this.field_178723_h.field_78808_h += MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F;
/*     */       }
/*     */       
/* 108 */       if (this.field_78117_n)
/*     */       {
/* 110 */         this.field_78115_e.field_78795_f = 0.5F;
/* 111 */         this.field_178723_h.field_78795_f += 0.4F;
/* 112 */         this.field_178724_i.field_78795_f += 0.4F;
/* 113 */         this.field_178721_j.field_78798_e = 4.0F;
/* 114 */         this.field_178722_k.field_78798_e = 4.0F;
/* 115 */         this.field_178721_j.field_78797_d = 13.0F;
/* 116 */         this.field_178722_k.field_78797_d = 13.0F;
/* 117 */         this.field_78116_c.field_78797_d = 4.5F;
/*     */         
/* 119 */         this.field_78115_e.field_78797_d = 4.5F;
/* 120 */         this.field_178723_h.field_78797_d = 5.0F;
/* 121 */         this.field_178724_i.field_78797_d = 5.0F;
/*     */       }
/*     */       else
/*     */       {
/* 125 */         this.field_78115_e.field_78795_f = 0.0F;
/* 126 */         this.field_178721_j.field_78798_e = 0.1F;
/* 127 */         this.field_178722_k.field_78798_e = 0.1F;
/* 128 */         this.field_178721_j.field_78797_d = 12.0F;
/* 129 */         this.field_178722_k.field_78797_d = 12.0F;
/* 130 */         this.field_78116_c.field_78797_d = 0.0F;
/*     */         
/* 132 */         this.field_78115_e.field_78797_d = 0.0F;
/* 133 */         this.field_178723_h.field_78797_d = 2.0F;
/* 134 */         this.field_178724_i.field_78797_d = 2.0F;
/*     */       }
/*     */       
/* 137 */       this.field_178723_h.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 138 */       this.field_178724_i.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 139 */       this.field_178723_h.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 140 */       this.field_178724_i.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */       
/* 142 */       if (this.field_78118_o)
/*     */       {
/* 144 */         float f6 = 0.0F;
/* 145 */         float f7 = 0.0F;
/* 146 */         this.field_178723_h.field_78808_h = 0.0F;
/* 147 */         this.field_178724_i.field_78808_h = 0.0F;
/* 148 */         this.field_178723_h.field_78796_g = (-(0.1F - f6 * 0.6F) + this.field_78116_c.field_78796_g);
/* 149 */         this.field_178724_i.field_78796_g = (0.1F - f6 * 0.6F + this.field_78116_c.field_78796_g + 0.4F);
/* 150 */         this.field_178723_h.field_78795_f = (-1.5707964F + this.field_78116_c.field_78795_f);
/* 151 */         this.field_178724_i.field_78795_f = (-1.5707964F + this.field_78116_c.field_78795_f);
/* 152 */         this.field_178723_h.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 153 */         this.field_178724_i.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 154 */         this.field_178723_h.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 155 */         this.field_178724_i.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 156 */         this.field_178723_h.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 157 */         this.field_178724_i.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */       }
/*     */       
/* 160 */       func_178685_a(this.field_78116_c, this.field_178720_f);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAnglesZombie(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 171 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*     */     
/* 173 */     float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
/* 174 */     float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);
/*     */     
/*     */ 
/* 177 */     this.field_178723_h.field_78808_h = 0.0F;
/* 178 */     this.field_178724_i.field_78808_h = 0.0F;
/* 179 */     this.field_178723_h.field_78796_g = (-(0.1F - f6 * 0.6F));
/* 180 */     this.field_178724_i.field_78796_g = (0.1F - f6 * 0.6F);
/* 181 */     this.field_178723_h.field_78795_f = -1.5707964F;
/* 182 */     this.field_178724_i.field_78795_f = -1.5707964F;
/* 183 */     this.field_178723_h.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 184 */     this.field_178724_i.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 185 */     this.field_178723_h.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 186 */     this.field_178724_i.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 187 */     this.field_178723_h.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 188 */     this.field_178724_i.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAnglesStand(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 194 */     if ((p_78087_7_ instanceof EntityArmorStand))
/*     */     {
/* 196 */       EntityArmorStand entityarmorstand = (EntityArmorStand)p_78087_7_;
/* 197 */       this.field_78116_c.field_78795_f = (0.017453292F * entityarmorstand.func_175418_s().func_179415_b());
/* 198 */       this.field_78116_c.field_78796_g = (0.017453292F * entityarmorstand.func_175418_s().func_179416_c());
/* 199 */       this.field_78116_c.field_78808_h = (0.017453292F * entityarmorstand.func_175418_s().func_179413_d());
/* 200 */       this.field_78116_c.func_78793_a(0.0F, 1.0F, 0.0F);
/* 201 */       this.field_78115_e.field_78795_f = (0.017453292F * entityarmorstand.func_175408_t().func_179415_b());
/* 202 */       this.field_78115_e.field_78796_g = (0.017453292F * entityarmorstand.func_175408_t().func_179416_c());
/* 203 */       this.field_78115_e.field_78808_h = (0.017453292F * entityarmorstand.func_175408_t().func_179413_d());
/* 204 */       this.field_178724_i.field_78795_f = (0.017453292F * entityarmorstand.func_175404_u().func_179415_b());
/* 205 */       this.field_178724_i.field_78796_g = (0.017453292F * entityarmorstand.func_175404_u().func_179416_c());
/* 206 */       this.field_178724_i.field_78808_h = (0.017453292F * entityarmorstand.func_175404_u().func_179413_d());
/* 207 */       this.field_178723_h.field_78795_f = (0.017453292F * entityarmorstand.func_175411_v().func_179415_b());
/* 208 */       this.field_178723_h.field_78796_g = (0.017453292F * entityarmorstand.func_175411_v().func_179416_c());
/* 209 */       this.field_178723_h.field_78808_h = (0.017453292F * entityarmorstand.func_175411_v().func_179413_d());
/* 210 */       this.field_178722_k.field_78795_f = (0.017453292F * entityarmorstand.func_175403_w().func_179415_b());
/* 211 */       this.field_178722_k.field_78796_g = (0.017453292F * entityarmorstand.func_175403_w().func_179416_c());
/* 212 */       this.field_178722_k.field_78808_h = (0.017453292F * entityarmorstand.func_175403_w().func_179413_d());
/* 213 */       this.field_178722_k.func_78793_a(1.9F, 11.0F, 0.0F);
/* 214 */       this.field_178721_j.field_78795_f = (0.017453292F * entityarmorstand.func_175407_x().func_179415_b());
/* 215 */       this.field_178721_j.field_78796_g = (0.017453292F * entityarmorstand.func_175407_x().func_179416_c());
/* 216 */       this.field_178721_j.field_78808_h = (0.017453292F * entityarmorstand.func_175407_x().func_179413_d());
/* 217 */       this.field_178721_j.func_78793_a(-1.9F, 11.0F, 0.0F);
/* 218 */       func_178685_a(this.field_78116_c, this.field_178720_f);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/gear/ModelCustomArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */