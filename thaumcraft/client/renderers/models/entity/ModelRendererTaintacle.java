/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModelRendererTaintacle extends net.minecraft.client.model.ModelRenderer
/*     */ {
/*     */   private int textureOffsetX;
/*     */   private int textureOffsetY;
/*     */   private boolean compiled;
/*     */   private int displayList;
/*     */   private ModelBase baseModel;
/*     */   
/*     */   public ModelRendererTaintacle(ModelBase par1ModelBase)
/*     */   {
/*  18 */     super(par1ModelBase);
/*     */   }
/*     */   
/*     */   public ModelRendererTaintacle(ModelBase par1ModelBase, int par2, int par3)
/*     */   {
/*  23 */     this(par1ModelBase);
/*  24 */     func_78784_a(par2, par3);
/*     */   }
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
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void render(float par1, float scale)
/*     */   {
/*  46 */     if (!this.field_78807_k)
/*     */     {
/*  48 */       if (this.field_78806_j)
/*     */       {
/*  50 */         if (!this.compiled)
/*     */         {
/*  52 */           compileDisplayList(par1);
/*     */         }
/*     */         
/*  55 */         GL11.glTranslatef(this.field_82906_o, this.field_82908_p, this.field_82907_q);
/*     */         
/*     */         int i;
/*  58 */         if ((this.field_78795_f == 0.0F) && (this.field_78796_g == 0.0F) && (this.field_78808_h == 0.0F))
/*     */         {
/*  60 */           if ((this.field_78800_c == 0.0F) && (this.field_78797_d == 0.0F) && (this.field_78798_e == 0.0F))
/*     */           {
/*  62 */             if (this.field_78805_m == null) {
/*  63 */               int j = 15728880;
/*  64 */               int k = j % 65536;
/*  65 */               int l = j / 65536;
/*  66 */               OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */             }
/*  68 */             GL11.glCallList(this.displayList);
/*     */             
/*  70 */             if (this.field_78805_m == null)
/*     */               break label554;
/*  72 */             for (i = 0; i < this.field_78805_m.size();)
/*     */             {
/*  74 */               GL11.glPushMatrix();
/*  75 */               GL11.glScalef(scale, scale, scale);
/*  76 */               ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/*  77 */               GL11.glPopMatrix();i++; continue;
/*     */               
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */               GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
/*  84 */               if (this.field_78805_m == null) {
/*  85 */                 int j = 15728880;
/*  86 */                 int k = j % 65536;
/*  87 */                 int l = j / 65536;
/*  88 */                 OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */               }
/*  90 */               GL11.glCallList(this.displayList);
/*     */               
/*  92 */               if (this.field_78805_m != null)
/*     */               {
/*  94 */                 for (int i = 0; i < this.field_78805_m.size(); i++)
/*     */                 {
/*  96 */                   GL11.glPushMatrix();
/*  97 */                   GL11.glScalef(scale, scale, scale);
/*  98 */                   ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/*  99 */                   GL11.glPopMatrix();
/*     */                 }
/*     */               }
/*     */               
/* 103 */               GL11.glTranslatef(-this.field_78800_c * par1, -this.field_78797_d * par1, -this.field_78798_e * par1); break;
/*     */               
/*     */ 
/*     */ 
/*     */ 
/* 108 */               GL11.glPushMatrix();
/* 109 */               GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
/*     */               
/* 111 */               if (this.field_78808_h != 0.0F)
/*     */               {
/* 113 */                 GL11.glRotatef(this.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */               }
/*     */               
/* 116 */               if (this.field_78796_g != 0.0F)
/*     */               {
/* 118 */                 GL11.glRotatef(this.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */               }
/*     */               
/* 121 */               if (this.field_78795_f != 0.0F)
/*     */               {
/* 123 */                 GL11.glRotatef(this.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */               }
/*     */               
/* 126 */               if (this.field_78805_m == null) {
/* 127 */                 int j = 15728880;
/* 128 */                 int k = j % 65536;
/* 129 */                 int l = j / 65536;
/* 130 */                 OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */               }
/* 132 */               GL11.glCallList(this.displayList);
/*     */               
/* 134 */               if (this.field_78805_m != null)
/*     */               {
/* 136 */                 for (int i = 0; i < this.field_78805_m.size(); i++)
/*     */                 {
/* 138 */                   GL11.glPushMatrix();
/* 139 */                   GL11.glScalef(scale, scale, scale);
/* 140 */                   ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/* 141 */                   GL11.glPopMatrix();
/*     */                 }
/*     */               }
/*     */               
/* 145 */               GL11.glPopMatrix();
/*     */             } } }
/*     */         label554:
/* 148 */         GL11.glTranslatef(-this.field_82906_o, -this.field_82908_p, -this.field_82907_q);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   private void compileDisplayList(float par1)
/*     */   {
/* 161 */     this.displayList = net.minecraft.client.renderer.GLAllocation.func_74526_a(1);
/* 162 */     GL11.glNewList(this.displayList, 4864);
/* 163 */     net.minecraft.client.renderer.Tessellator tessellator = net.minecraft.client.renderer.Tessellator.func_178181_a();
/*     */     
/* 165 */     for (int i = 0; i < this.field_78804_l.size(); i++)
/*     */     {
/* 167 */       ((net.minecraft.client.model.ModelBox)this.field_78804_l.get(i)).func_178780_a(tessellator.func_178180_c(), par1);
/*     */     }
/*     */     
/* 170 */     GL11.glEndList();
/* 171 */     this.compiled = true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/entity/ModelRendererTaintacle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */