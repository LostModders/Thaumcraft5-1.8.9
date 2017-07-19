/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.block.ModelBore;
/*     */ import thaumcraft.client.renderers.models.block.ModelBoreEmit;
/*     */ import thaumcraft.client.renderers.models.block.ModelJar;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneBore;
/*     */ 
/*     */ 
/*     */ public class TileArcaneBoreRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private ModelBoreEmit modelEmit;
/*     */   private ModelBore modelBore;
/*     */   private ModelJar modelJar;
/*     */   
/*     */   public TileArcaneBoreRenderer()
/*     */   {
/*  28 */     this.modelEmit = new ModelBoreEmit();
/*  29 */     this.modelBore = new ModelBore();
/*  30 */     this.modelJar = new ModelJar();
/*     */   }
/*     */   
/*  33 */   private static final ResourceLocation TEX_BORE = new ResourceLocation("thaumcraft", "textures/models/bore.png");
/*  34 */   private static final ResourceLocation TEX_VORT = new ResourceLocation("thaumcraft", "textures/misc/vortex.png");
/*  35 */   private static final ResourceLocation TEX_JAR = new ResourceLocation("thaumcraft", "textures/models/jar.png");
/*     */   
/*     */   public void renderEntityAt(TileArcaneBore bore, double x, double y, double z, float fq)
/*     */   {
/*  39 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*     */     
/*  41 */     func_147499_a(TEX_BORE);
/*     */     
/*  43 */     GL11.glPushMatrix();
/*  44 */     GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
/*     */     
/*     */ 
/*  47 */     GL11.glRotatef(bore.rotX - bore.vRadX + fq * bore.speedX, 0.0F, 1.0F, 0.0F);
/*     */     
/*  49 */     GL11.glPushMatrix();
/*  50 */     if (bore.baseOrientation.ordinal() == 1) GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  51 */     GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/*  52 */     this.modelBore.renderBase();
/*  53 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/*  56 */     GL11.glRotatef(bore.rotZ - bore.vRadZ + fq * bore.speedZ, 0.0F, 0.0F, 1.0F);
/*     */     
/*  58 */     GL11.glPushMatrix();
/*  59 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  60 */     GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/*  61 */     this.modelBore.renderNozzle();
/*  62 */     GL11.glPopMatrix();
/*     */     
/*  64 */     GL11.glPushMatrix();
/*  65 */     GL11.glRotatef(bore.topRotation, 0.0F, 1.0F, 0.0F);
/*  66 */     GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/*  67 */     this.modelEmit.render(bore.hasFocus);
/*  68 */     GL11.glPopMatrix();
/*     */     
/*  70 */     GL11.glPushMatrix();
/*  71 */     float rotation = Minecraft.func_71410_x().func_175606_aa().field_70173_aa % 45 + fq;
/*  72 */     GL11.glTranslatef(0.0F, -0.17F, 0.0F);
/*  73 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  74 */     GL11.glRotatef(-(rotation * 8.0F), 0.0F, 0.0F, 1.0F);
/*  75 */     GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
/*     */     
/*  77 */     UtilsFX.renderQuadCentered(TEX_VORT, 0.4F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*  78 */     GL11.glPopMatrix();
/*     */     
/*  80 */     GL11.glPushMatrix();
/*  81 */     rotation = Minecraft.func_71410_x().func_175606_aa().field_70173_aa % 45 + fq;
/*  82 */     GL11.glTranslatef(0.0F, -0.21F, 0.0F);
/*  83 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  84 */     GL11.glRotatef(rotation * 8.0F, 0.0F, 0.0F, 1.0F);
/*  85 */     GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
/*  86 */     UtilsFX.renderQuadCentered(TEX_VORT, 0.3F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*  87 */     GL11.glPopMatrix();
/*     */     
/*  89 */     GL11.glPushMatrix();
/*  90 */     rotation = Minecraft.func_71410_x().func_175606_aa().field_70173_aa % 45 + fq;
/*  91 */     GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/*  92 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  93 */     GL11.glRotatef(-(rotation * 8.0F), 0.0F, 0.0F, 1.0F);
/*  94 */     GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
/*  95 */     UtilsFX.renderQuadCentered(TEX_VORT, 0.2F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*  96 */     GL11.glPopMatrix();
/*     */     
/*  98 */     GL11.glPushMatrix();
/*  99 */     func_147499_a(TEX_JAR);
/* 100 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 101 */     GL11.glTranslatef(0.0F, 0.3F, 0.0F);
/* 102 */     GL11.glScalef(0.6F, 0.6F, 0.6F);
/* 103 */     GL11.glDepthMask(false);
/* 104 */     GL11.glEnable(3042);
/* 105 */     GL11.glBlendFunc(770, 771);
/* 106 */     this.modelJar.Core.func_78785_a(0.0625F);
/* 107 */     GL11.glDisable(3042);
/* 108 */     GL11.glDepthMask(true);
/* 109 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 110 */     GL11.glPopMatrix();
/*     */     
/* 112 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180535_a(TileEntity tileentity, double d, double d1, double d2, float f, int gg)
/*     */   {
/* 119 */     renderEntityAt((TileArcaneBore)tileentity, d, d1, d2, f);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileArcaneBoreRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */