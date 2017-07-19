/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.models.IModelCustom;
/*     */ import thaumcraft.common.tiles.essentia.TileCrystallizer;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileCrystallizerRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*     */   private IModelCustom model2;
/*  24 */   private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "models/obj/crystallizer.obj");
/*  25 */   private static final ResourceLocation CRYSTAL = new ResourceLocation("thaumcraft", "models/obj/vis_relay.obj");
/*  26 */   private static final ResourceLocation TEX_R = new ResourceLocation("thaumcraft", "textures/models/vis_relay.png");
/*  27 */   private static final ResourceLocation TEX_C = new ResourceLocation("thaumcraft", "textures/models/crystallizer.png");
/*     */   
/*  29 */   public TileCrystallizerRenderer() { this.model = AdvancedModelLoader.loadModel(RELAY);
/*  30 */     this.model2 = AdvancedModelLoader.loadModel(CRYSTAL);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileCrystallizer tile, double par2, double par4, double par6, float par8)
/*     */   {
/*  39 */     int facing = tile.getFacing().ordinal();
/*     */     
/*  41 */     int ticks = Minecraft.func_71410_x().func_175606_aa().field_70173_aa;
/*     */     
/*  43 */     GL11.glPushMatrix();
/*  44 */     translateFromOrientation(par2, par4, par6, facing);
/*  45 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  46 */     GL11.glPushMatrix();
/*  47 */     func_147499_a(TEX_C);
/*     */     
/*  49 */     this.model.renderAll();
/*     */     
/*  51 */     GL11.glEnable(3042);
/*  52 */     GL11.glBlendFunc(770, 771);
/*  53 */     func_147499_a(TEX_R);
/*  54 */     GL11.glColor3f(tile.cr, tile.cg, tile.cb);
/*  55 */     for (int q = 0; q < 4; q++) {
/*  56 */       GL11.glPushMatrix();
/*  57 */       GL11.glScaled(0.75D, 0.75D, 0.75D);
/*  58 */       float glow = MathHelper.func_76126_a((ticks + par8 + q * 10) / 2.0F) * 0.05F + 0.95F;
/*  59 */       int j = 50 + (int)(150.0F * glow);
/*  60 */       int k = j % 65536;
/*  61 */       int l = j / 65536;
/*  62 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       
/*     */ 
/*  65 */       GL11.glRotatef(90 * q, 0.0F, 0.0F, 1.0F);
/*  66 */       GL11.glTranslated(0.34D, 0.0D, 1.2125D);
/*  67 */       GL11.glRotatef(tile.spin + tile.spinInc * par8, 0.0F, 0.0F, 1.0F);
/*     */       
/*  69 */       this.model2.renderPart("Crystal");
/*     */       
/*  71 */       GL11.glPopMatrix();
/*     */     }
/*  73 */     GL11.glDisable(3042);
/*     */     
/*  75 */     GL11.glPopMatrix();
/*     */     
/*  77 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  78 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(double x, double y, double z, int orientation)
/*     */   {
/*  83 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*  84 */     if (orientation == 0) {
/*  85 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  86 */     } else if (orientation == 1) {
/*  87 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  88 */     } else if (orientation != 2) {
/*  89 */       if (orientation == 3) {
/*  90 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  91 */       } else if (orientation == 4) {
/*  92 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  93 */       } else if (orientation == 5)
/*  94 */         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/*  96 */     GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*     */   }
/*     */   
/*     */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int q)
/*     */   {
/* 101 */     renderTileEntityAt((TileCrystallizer)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileCrystallizerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */