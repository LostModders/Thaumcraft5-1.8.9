/*     */ package thaumcraft.client.renderers.entity.construct;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCultistPortalLesser
/*     */   extends Render
/*     */ {
/*     */   public RenderCultistPortalLesser(RenderManager renderManager)
/*     */   {
/*  27 */     super(renderManager);
/*  28 */     this.field_76989_e = 0.0F;
/*  29 */     this.field_76987_f = 0.0F;
/*     */   }
/*     */   
/*  32 */   public static final ResourceLocation portaltex = new ResourceLocation("thaumcraft", "textures/misc/cultist_portal.png");
/*     */   
/*     */   public void renderPortal(EntityCultistPortalLesser portal, double px, double py, double pz, float par8, float f)
/*     */   {
/*  36 */     if (portal.isActive()) {
/*  37 */       long nt = System.nanoTime();
/*  38 */       long time = nt / 50000000L;
/*  39 */       float scaley = 1.4F;
/*  40 */       int e = (int)Math.min(50.0F, portal.activeCounter + f);
/*     */       
/*  42 */       if (portal.field_70737_aN > 0) {
/*  43 */         double d = Math.sin(portal.field_70737_aN * 72 * 3.141592653589793D / 180.0D);
/*  44 */         scaley = (float)(scaley - d / 4.0D);
/*  45 */         e = (int)(e + 6.0D * d);
/*     */       }
/*     */       
/*  48 */       if (portal.pulse > 0) {
/*  49 */         double d = Math.sin(portal.pulse * 36 * 3.141592653589793D / 180.0D);
/*  50 */         scaley = (float)(scaley + d / 4.0D);
/*  51 */         e = (int)(e + 12.0D * d);
/*     */       }
/*     */       
/*  54 */       float scale = e / 50.0F * 1.25F;
/*     */       
/*  56 */       py += portal.field_70131_O / 2.0F;
/*     */       
/*  58 */       float m = (1.0F - portal.func_110143_aJ() / portal.func_110138_aP()) / 3.0F;
/*  59 */       float bob = MathHelper.func_76126_a(portal.activeCounter / (5.0F - 12.0F * m)) * m + m;
/*  60 */       float bob2 = MathHelper.func_76126_a(portal.activeCounter / (6.0F - 15.0F * m)) * m + m;
/*  61 */       float alpha = 1.0F - bob;
/*     */       
/*  63 */       scaley -= bob / 4.0F;
/*  64 */       scale -= bob2 / 3.0F;
/*     */       
/*  66 */       func_110776_a(portaltex);
/*  67 */       GL11.glPushMatrix();
/*     */       
/*  69 */       GL11.glEnable(3042);
/*  70 */       GL11.glBlendFunc(770, 771);
/*  71 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/*     */       
/*  73 */       if ((Minecraft.func_71410_x().func_175606_aa() instanceof EntityPlayer))
/*     */       {
/*  75 */         Tessellator tessellator = Tessellator.func_178181_a();
/*  76 */         float arX = ActiveRenderInfo.func_178808_b();
/*  77 */         float arZ = ActiveRenderInfo.func_178803_d();
/*  78 */         float arYZ = ActiveRenderInfo.func_178805_e();
/*  79 */         float arXY = ActiveRenderInfo.func_178807_f();
/*  80 */         float arXZ = ActiveRenderInfo.func_178809_c();
/*     */         
/*  82 */         tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*  83 */         Vec3 v1 = new Vec3(-arX - arYZ, -arXZ, -arZ - arXY);
/*  84 */         Vec3 v2 = new Vec3(-arX + arYZ, arXZ, -arZ + arXY);
/*  85 */         Vec3 v3 = new Vec3(arX + arYZ, arXZ, arZ + arXY);
/*  86 */         Vec3 v4 = new Vec3(arX - arYZ, -arXZ, arZ - arXY);
/*  87 */         int frame = 15 - (int)time % 16;
/*  88 */         float f2 = frame / 16.0F;
/*  89 */         float f3 = f2 + 0.0625F;
/*  90 */         float f4 = 0.0F;
/*  91 */         float f5 = 1.0F;
/*  92 */         int i = 220;
/*  93 */         int j = i >> 16 & 0xFFFF;
/*  94 */         int k = i & 0xFFFF;
/*  95 */         tessellator.func_178180_c().func_181662_b(px + v1.field_72450_a * scale, py + v1.field_72448_b * scaley, pz + v1.field_72449_c * scale).func_181673_a(f3, f4).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/*  96 */         tessellator.func_178180_c().func_181662_b(px + v2.field_72450_a * scale, py + v2.field_72448_b * scaley, pz + v2.field_72449_c * scale).func_181673_a(f3, f5).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/*  97 */         tessellator.func_178180_c().func_181662_b(px + v3.field_72450_a * scale, py + v3.field_72448_b * scaley, pz + v3.field_72449_c * scale).func_181673_a(f2, f5).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/*  98 */         tessellator.func_178180_c().func_181662_b(px + v4.field_72450_a * scale, py + v4.field_72448_b * scaley, pz + v4.field_72449_c * scale).func_181673_a(f2, f4).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/*  99 */         tessellator.func_78381_a();
/*     */       }
/*     */       
/* 102 */       GL11.glDisable(32826);
/* 103 */       GL11.glDisable(3042);
/* 104 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 111 */     renderPortal((EntityCultistPortalLesser)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 117 */     return portaltex;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/RenderCultistPortalLesser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */