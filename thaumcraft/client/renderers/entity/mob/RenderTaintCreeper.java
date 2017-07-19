/*     */ package thaumcraft.client.renderers.entity.mob;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelCreeper;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCreeper;
/*     */ 
/*     */ 
/*     */ public class RenderTaintCreeper
/*     */   extends RenderLiving
/*     */ {
/*  18 */   private ModelBase field_27008_a = new ModelCreeper(2.0F);
/*     */   
/*     */   public RenderTaintCreeper(RenderManager rm)
/*     */   {
/*  22 */     super(rm, new ModelCreeper(), 0.5F);
/*     */   }
/*     */   
/*  25 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/creature/creeper.png");
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/*  29 */     return rl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void updateCreeperScale(EntityTaintCreeper par1EntityCreeper, float par2)
/*     */   {
/*  37 */     float var4 = par1EntityCreeper.getCreeperFlashIntensity(par2);
/*  38 */     float var5 = 1.0F + MathHelper.func_76126_a(var4 * 100.0F) * var4 * 0.01F;
/*     */     
/*  40 */     if (var4 < 0.0F)
/*     */     {
/*  42 */       var4 = 0.0F;
/*     */     }
/*     */     
/*  45 */     if (var4 > 1.0F)
/*     */     {
/*  47 */       var4 = 1.0F;
/*     */     }
/*     */     
/*  50 */     var4 *= var4;
/*  51 */     var4 *= var4;
/*  52 */     float var6 = (1.0F + var4 * 0.4F) * var5;
/*  53 */     float var7 = (1.0F + var4 * 0.1F) / var5;
/*  54 */     GL11.glScalef(var6, var7, var6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int updateCreeperColorMultiplier(EntityTaintCreeper par1EntityCreeper, float par2, float par3)
/*     */   {
/*  62 */     float var5 = par1EntityCreeper.getCreeperFlashIntensity(par3);
/*     */     
/*  64 */     if ((int)(var5 * 10.0F) % 2 == 0)
/*     */     {
/*  66 */       return 0;
/*     */     }
/*     */     
/*     */ 
/*  70 */     int var6 = (int)(var5 * 0.2F * 255.0F);
/*     */     
/*  72 */     if (var6 < 0)
/*     */     {
/*  74 */       var6 = 0;
/*     */     }
/*     */     
/*  77 */     if (var6 > 255)
/*     */     {
/*  79 */       var6 = 255;
/*     */     }
/*     */     
/*  82 */     short var7 = 255;
/*  83 */     short var8 = 255;
/*  84 */     short var9 = 255;
/*  85 */     return var6 << 24 | var7 << 16 | var8 << 8 | var9;
/*     */   }
/*     */   
/*     */ 
/*  89 */   private static final ResourceLocation field_110831_a = new ResourceLocation("thaumcraft", "textures/entity/creeper/creeper_armor.png");
/*     */   
/*     */ 
/*     */ 
/*     */   protected int func_27007_b(EntityTaintCreeper par1EntityCreeper, int par2, float par3)
/*     */   {
/*  95 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/* 105 */     updateCreeperScale((EntityTaintCreeper)par1EntityLiving, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int func_77030_a(EntityLivingBase par1EntityLiving, float par2, float par3)
/*     */   {
/* 114 */     return updateCreeperColorMultiplier((EntityTaintCreeper)par1EntityLiving, par2, par3);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/RenderTaintCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */