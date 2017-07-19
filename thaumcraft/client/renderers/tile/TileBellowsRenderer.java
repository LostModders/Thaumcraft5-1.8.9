/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.renderers.models.block.ModelBellows;
/*     */ import thaumcraft.client.renderers.models.block.ModelBoreBase;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileBellows;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeBuffer;
/*     */ 
/*     */ 
/*     */ public class TileBellowsRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private ModelBellows model;
/*     */   private ModelBoreBase model2;
/*     */   
/*     */   public TileBellowsRenderer()
/*     */   {
/*  30 */     this.model = new ModelBellows();
/*  31 */     this.model2 = new ModelBoreBase();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(double x, double y, double z, int orientation)
/*     */   {
/*  36 */     GL11.glTranslatef((float)x + 0.5F, (float)y - 0.5F, (float)z + 0.5F);
/*  37 */     if (orientation == 0) {
/*  38 */       GL11.glTranslatef(0.0F, 1.0F, -1.0F);
/*  39 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  40 */     } else if (orientation == 1) {
/*  41 */       GL11.glTranslatef(0.0F, 1.0F, 1.0F);
/*  42 */       GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
/*  43 */     } else if (orientation == 2) {
/*  44 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  45 */     } else if (orientation == 4) {
/*  46 */       GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*  47 */     } else if (orientation == 5) {
/*  48 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  53 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/models/bellows.png");
/*  54 */   private static final ResourceLocation TEX_BORE = new ResourceLocation("thaumcraft", "textures/models/bore.png");
/*     */   
/*     */   public void renderEntityAt(TileBellows bellows, double x, double y, double z, float fq)
/*     */   {
/*  58 */     float scale = 0.0F;
/*  59 */     EnumFacing dir = EnumFacing.WEST;
/*  60 */     boolean extension = false;
/*  61 */     if (bellows.func_145831_w() == null) {
/*  62 */       EntityPlayer p = Minecraft.func_71410_x().field_71439_g;
/*  63 */       scale = MathHelper.func_76126_a(p.field_70173_aa / 8.0F) * 0.3F + 0.7F;
/*     */     } else {
/*  65 */       scale = bellows.inflation;
/*  66 */       dir = BlockStateUtils.getFacing(bellows.func_145832_p());
/*  67 */       TileEntity te = bellows.func_145831_w().func_175625_s(bellows.func_174877_v().func_177972_a(BlockStateUtils.getFacing(bellows.func_145832_p())));
/*  68 */       if ((te != null) && ((te instanceof TileTubeBuffer))) {
/*  69 */         extension = true;
/*     */       }
/*     */     }
/*     */     
/*  73 */     float tscale = 0.125F + scale * 0.875F;
/*     */     
/*  75 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*     */     
/*  77 */     if (extension) {
/*  78 */       func_147499_a(TEX_BORE);
/*  79 */       GL11.glPushMatrix();
/*  80 */       GL11.glTranslatef((float)x + 0.5F + dir.func_82601_c(), (float)y + dir.func_96559_d(), (float)z + 0.5F + dir.func_82599_e());
/*  81 */       switch (dir.func_176734_d().ordinal()) {
/*  82 */       case 0:  GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
/*  83 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F); break;
/*  84 */       case 1:  GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/*  85 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/*  86 */       case 2:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  87 */       case 3:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/*  88 */       case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/*  89 */       case 5:  GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*  91 */       this.model2.renderNozzle();
/*  92 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*  95 */     func_147499_a(TEX);
/*  96 */     GL11.glPushMatrix();
/*  97 */     GL11.glEnable(2977);
/*  98 */     GL11.glEnable(32826);
/*  99 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 100 */     translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal());
/*     */     
/*     */ 
/* 103 */     GL11.glTranslatef(0.0F, 1.0F, 0.0F);
/*     */     
/* 105 */     GL11.glPushMatrix();
/* 106 */     GL11.glScalef(0.5F, (scale + 0.1F) / 2.0F, 0.5F);
/* 107 */     this.model.Bag.func_78793_a(0.0F, 0.5F, 0.0F);
/* 108 */     this.model.Bag.func_78785_a(0.0625F);
/* 109 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 110 */     GL11.glPopMatrix();
/* 111 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*     */     
/* 113 */     GL11.glPushMatrix();
/* 114 */     GL11.glTranslatef(0.0F, -tscale / 2.0F + 0.5F, 0.0F);
/* 115 */     this.model.TopPlank.func_78785_a(0.0625F);
/* 116 */     GL11.glTranslatef(0.0F, tscale / 2.0F - 0.5F, 0.0F);
/* 117 */     GL11.glPopMatrix();
/*     */     
/* 119 */     GL11.glPushMatrix();
/* 120 */     GL11.glTranslatef(0.0F, tscale / 2.0F - 0.5F, 0.0F);
/* 121 */     this.model.BottomPlank.func_78785_a(0.0625F);
/* 122 */     GL11.glTranslatef(0.0F, -tscale / 2.0F + 0.5F, 0.0F);
/* 123 */     GL11.glPopMatrix();
/*     */     
/* 125 */     this.model.render();
/* 126 */     GL11.glDisable(32826);
/* 127 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 128 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180535_a(TileEntity tileentity, double d, double d1, double d2, float f, int q)
/*     */   {
/* 139 */     renderEntityAt((TileBellows)tileentity, d, d1, d2, f);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileBellowsRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */