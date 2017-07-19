/*     */ package thaumcraft.client.renderers.entity.construct;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.boss.BossStatus;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCultistPortalGreater
/*     */   extends Render
/*     */ {
/*     */   public RenderCultistPortalGreater(RenderManager renderManager)
/*     */   {
/*  29 */     super(renderManager);
/*  30 */     this.field_76989_e = 0.1F;
/*  31 */     this.field_76987_f = 0.5F;
/*     */   }
/*     */   
/*  34 */   public static final ResourceLocation portaltex = new ResourceLocation("thaumcraft", "textures/misc/cultist_portal.png");
/*     */   
/*     */ 
/*     */   public void renderPortal(EntityCultistPortalGreater portal, double px, double py, double pz, float par8, float f)
/*     */   {
/*  39 */     if (BossStatus.field_82826_b < 100) {
/*  40 */       BossStatus.func_82824_a(portal, false);
/*     */     }
/*  42 */     long nt = System.nanoTime();
/*  43 */     long time = nt / 50000000L;
/*  44 */     float scaley = 1.5F;
/*  45 */     int e = (int)Math.min(50.0F, portal.field_70173_aa + f);
/*     */     
/*  47 */     if (portal.field_70737_aN > 0) {
/*  48 */       double d = Math.sin(portal.field_70737_aN * 72 * 3.141592653589793D / 180.0D);
/*  49 */       scaley = (float)(scaley - d / 4.0D);
/*  50 */       e = (int)(e + 6.0D * d);
/*     */     }
/*     */     
/*  53 */     if (portal.pulse > 0) {
/*  54 */       double d = Math.sin(portal.pulse * 36 * 3.141592653589793D / 180.0D);
/*  55 */       scaley = (float)(scaley + d / 4.0D);
/*  56 */       e = (int)(e + 12.0D * d);
/*     */     }
/*     */     
/*  59 */     float scale = e / 50.0F * 1.3F;
/*     */     
/*  61 */     py += portal.field_70131_O / 2.0F;
/*     */     
/*  63 */     float m = (1.0F - portal.func_110143_aJ() / portal.func_110138_aP()) / 3.0F;
/*  64 */     float bob = MathHelper.func_76126_a(portal.field_70173_aa / (5.0F - 12.0F * m)) * m + m;
/*  65 */     float bob2 = MathHelper.func_76126_a(portal.field_70173_aa / (6.0F - 15.0F * m)) * m + m;
/*  66 */     float alpha = 1.0F - bob;
/*     */     
/*  68 */     scaley -= bob / 4.0F;
/*  69 */     scale -= bob2 / 3.0F;
/*     */     
/*  71 */     func_110776_a(portaltex);
/*  72 */     GL11.glPushMatrix();
/*     */     
/*  74 */     GL11.glEnable(3042);
/*  75 */     GL11.glBlendFunc(770, 771);
/*  76 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/*     */     
/*  78 */     if ((Minecraft.func_71410_x().func_175606_aa() instanceof EntityPlayer))
/*     */     {
/*  80 */       Tessellator tessellator = Tessellator.func_178181_a();
/*  81 */       float arX = ActiveRenderInfo.func_178808_b();
/*  82 */       float arZ = ActiveRenderInfo.func_178803_d();
/*  83 */       float arYZ = ActiveRenderInfo.func_178805_e();
/*  84 */       float arXY = ActiveRenderInfo.func_178807_f();
/*  85 */       float arXZ = ActiveRenderInfo.func_178809_c();
/*     */       
/*  87 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().func_175606_aa();
/*  88 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * f;
/*  89 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * f;
/*  90 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * f;
/*  91 */       tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*  92 */       Vec3 v1 = new Vec3(-arX - arYZ, -arXZ, -arZ - arXY);
/*  93 */       Vec3 v2 = new Vec3(-arX + arYZ, arXZ, -arZ + arXY);
/*  94 */       Vec3 v3 = new Vec3(arX + arYZ, arXZ, arZ + arXY);
/*  95 */       Vec3 v4 = new Vec3(arX - arYZ, -arXZ, arZ - arXY);
/*  96 */       int frame = 15 - (int)time % 16;
/*  97 */       float f2 = frame / 16.0F;
/*  98 */       float f3 = f2 + 0.0625F;
/*  99 */       float f4 = 0.0F;
/* 100 */       float f5 = 1.0F;
/* 101 */       int i = 220;
/* 102 */       int j = i >> 16 & 0xFFFF;
/* 103 */       int k = i & 0xFFFF;
/* 104 */       tessellator.func_178180_c().func_181662_b(px + v1.field_72450_a * scale, py + v1.field_72448_b * scaley, pz + v1.field_72449_c * scale).func_181673_a(f3, f4).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/* 105 */       tessellator.func_178180_c().func_181662_b(px + v2.field_72450_a * scale, py + v2.field_72448_b * scaley, pz + v2.field_72449_c * scale).func_181673_a(f3, f5).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/* 106 */       tessellator.func_178180_c().func_181662_b(px + v3.field_72450_a * scale, py + v3.field_72448_b * scaley, pz + v3.field_72449_c * scale).func_181673_a(f2, f5).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/* 107 */       tessellator.func_178180_c().func_181662_b(px + v4.field_72450_a * scale, py + v4.field_72448_b * scaley, pz + v4.field_72449_c * scale).func_181673_a(f2, f4).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_181671_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/* 108 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/* 111 */     GL11.glDisable(32826);
/* 112 */     GL11.glDisable(3042);
/* 113 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 119 */     renderPortal((EntityCultistPortalGreater)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 125 */     return portaltex;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/RenderCultistPortalGreater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */