/*     */ package thaumcraft.client.renderers.entity.mob;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderBiped;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.boss.BossStatus;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCultist extends RenderBiped
/*     */ {
/*  32 */   private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/models/creature/cultist.png");
/*  33 */   private static final ResourceLocation fl = new ResourceLocation("thaumcraft", "textures/misc/wispy.png");
/*     */   
/*     */   public RenderCultist(RenderManager p_i46127_1_)
/*     */   {
/*  37 */     super(p_i46127_1_, new ModelBiped(), 0.5F);
/*  38 */     func_177094_a(new LayerHeldItem(this));
/*  39 */     LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
/*     */     {
/*     */       protected void func_177177_a()
/*     */       {
/*  43 */         this.field_177189_c = new ModelBiped();
/*  44 */         this.field_177186_d = new ModelBiped();
/*     */       }
/*  46 */     };
/*  47 */     func_177094_a(layerbipedarmor);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(EntityLiving p_110775_1_)
/*     */   {
/*  53 */     return skin;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/*  59 */     if ((par1EntityLiving instanceof EntityCultistLeader)) {
/*  60 */       BossStatus.func_82824_a((EntityCultistLeader)par1EntityLiving, false);
/*  61 */       GL11.glScalef(1.25F, 1.25F, 1.25F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76986_a(EntityLiving entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*     */   {
/*  69 */     ItemStack itemstack = entity.func_70694_bm();
/*     */     
/*  71 */     if (itemstack == null)
/*     */     {
/*  73 */       ((ModelBiped)this.field_77045_g).field_78120_m = 0;
/*     */     }
/*     */     else
/*     */     {
/*  77 */       ((ModelBiped)this.field_77045_g).field_78120_m = 1;
/*     */     }
/*     */     
/*     */ 
/*  81 */     GL11.glPushMatrix();
/*     */     
/*  83 */     float bob = 0.0F;
/*  84 */     boolean rit = ((entity instanceof EntityCultistCleric)) && (((EntityCultistCleric)entity).getIsRitualist());
/*  85 */     if (rit) {
/*  86 */       int val = new Random(entity.func_145782_y()).nextInt(1000);
/*  87 */       float c = ((EntityCultistCleric)entity).field_70173_aa + p_76986_9_ + val;
/*  88 */       bob = MathHelper.func_76126_a(c / 9.0F) * 0.1F + 0.21F;
/*  89 */       GL11.glTranslated(0.0D, bob, 0.0D);
/*     */     }
/*     */     
/*  92 */     super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */     
/*  94 */     if (rit) {
/*  95 */       GL11.glPushMatrix();
/*  96 */       GL11.glDepthMask(false);
/*  97 */       drawFloatyLine(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e() * 1.2F, entity.field_70161_v, ((EntityCultistCleric)entity).func_180486_cf().func_177958_n() + 0.5D, ((EntityCultistCleric)entity).func_180486_cf().func_177956_o() + 1.5D - bob, ((EntityCultistCleric)entity).func_180486_cf().func_177952_p() + 0.5D, p_76986_9_, 1114129, -0.03F, Math.min(((EntityCultistCleric)entity).field_70173_aa, 10) / 10.0F, 0.25F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 103 */       GL11.glDepthMask(true);
/* 104 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 107 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, float speed, float distance, float width) {
/* 111 */     Entity player = Minecraft.func_71410_x().func_175606_aa();
/* 112 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 113 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 114 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 116 */     double ePX = x2;
/* 117 */     double ePY = y2;
/* 118 */     double ePZ = z2;
/*     */     
/* 120 */     GL11.glTranslated(-iPX + ePX, -iPY + ePY, -iPZ + ePZ);
/*     */     
/* 122 */     float time = (float)(System.nanoTime() / 30000000L);
/*     */     
/* 124 */     Color co = new Color(color);
/* 125 */     float r = co.getRed() / 255.0F;
/* 126 */     float g = co.getGreen() / 255.0F;
/* 127 */     float b = co.getBlue() / 255.0F;
/*     */     
/*     */ 
/*     */ 
/* 131 */     GL11.glEnable(3042);
/* 132 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 134 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/* 136 */     double ds1x = ePX;
/* 137 */     double ds1y = ePY;
/* 138 */     double ds1z = ePZ;
/* 139 */     double dd1x = x;
/* 140 */     double dd1y = y;
/* 141 */     double dd1z = z;
/* 142 */     double dc1x = (float)(dd1x - ds1x);
/* 143 */     double dc1y = (float)(dd1y - ds1y);
/* 144 */     double dc1z = (float)(dd1z - ds1z);
/*     */     
/* 146 */     func_110776_a(fl);
/*     */     
/*     */ 
/* 149 */     tessellator.func_178180_c().func_181668_a(5, DefaultVertexFormats.field_181709_i);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 158 */     double dx2 = 0.0D;
/* 159 */     double dy2 = 0.0D;
/* 160 */     double dz2 = 0.0D;
/* 161 */     double d3 = x - ePX;
/* 162 */     double d4 = y - ePY;
/* 163 */     double d5 = z - ePZ;
/*     */     
/* 165 */     float dist = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
/* 166 */     float blocks = Math.round(dist);
/* 167 */     float length = blocks * 6.0F;
/*     */     
/* 169 */     float f9 = 0.0F;
/* 170 */     float f10 = 1.0F;
/*     */     
/* 172 */     for (int i = 0; i <= length * distance; i++)
/*     */     {
/* 174 */       float f2 = i / length;
/* 175 */       float f2a = i * 1.5F / length;
/* 176 */       f2a = Math.min(0.75F, f2a);
/* 177 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / (length / 2.0F);
/*     */       
/* 179 */       double dx = dc1x + MathHelper.func_76126_a((float)((z % 16.0D + dist * (1.0F - f2) * 6.0F - time % 32767.0F / 5.0F) / 4.0D)) * 0.5F * f3;
/* 180 */       double dy = dc1y + MathHelper.func_76126_a((float)((x % 16.0D + dist * (1.0F - f2) * 6.0F - time % 32767.0F / 5.0F) / 3.0D)) * 0.5F * f3;
/* 181 */       double dz = dc1z + MathHelper.func_76126_a((float)((y % 16.0D + dist * (1.0F - f2) * 6.0F - time % 32767.0F / 5.0F) / 2.0D)) * 0.5F * f3;
/*     */       
/* 183 */       float f13 = (1.0F - f2) * dist - time * speed;
/* 184 */       tessellator.func_178180_c().func_181662_b(dx * f2, dy * f2 - width, dz * f2).func_181673_a(f13, f10).func_181666_a(r, g, b, 0.8F).func_181675_d();
/* 185 */       tessellator.func_178180_c().func_181662_b(dx * f2, dy * f2 + width, dz * f2).func_181673_a(f13, f9).func_181666_a(r, g, b, 0.8F).func_181675_d();
/*     */     }
/*     */     
/*     */ 
/* 189 */     tessellator.func_78381_a();
/*     */     
/* 191 */     tessellator.func_178180_c().func_181668_a(5, DefaultVertexFormats.field_181709_i);
/* 192 */     for (i = 0; i <= length * distance; i++)
/*     */     {
/* 194 */       float f2 = i / length;
/* 195 */       float f2a = i * 1.5F / length;
/* 196 */       f2a = Math.min(0.75F, f2a);
/* 197 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / (length / 2.0F);
/*     */       
/* 199 */       double dx = dc1x + MathHelper.func_76126_a((float)((z % 16.0D + dist * (1.0F - f2) * 6.0F - time % 32767.0F / 5.0F) / 4.0D)) * 0.5F * f3;
/* 200 */       double dy = dc1y + MathHelper.func_76126_a((float)((x % 16.0D + dist * (1.0F - f2) * 6.0F - time % 32767.0F / 5.0F) / 3.0D)) * 0.5F * f3;
/* 201 */       double dz = dc1z + MathHelper.func_76126_a((float)((y % 16.0D + dist * (1.0F - f2) * 6.0F - time % 32767.0F / 5.0F) / 2.0D)) * 0.5F * f3;
/*     */       
/*     */ 
/* 204 */       float f13 = (1.0F - f2) * dist - time * speed;
/*     */       
/* 206 */       tessellator.func_178180_c().func_181662_b(dx * f2 - width, dy * f2, dz * f2).func_181673_a(f13, f10).func_181666_a(r, g, b, 0.8F).func_181675_d();
/* 207 */       tessellator.func_178180_c().func_181662_b(dx * f2 + width, dy * f2, dz * f2).func_181673_a(f13, f9).func_181666_a(r, g, b, 0.8F).func_181675_d();
/*     */     }
/*     */     
/* 210 */     tessellator.func_78381_a();
/*     */     
/*     */ 
/* 213 */     GL11.glDisable(3042);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderCultist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */