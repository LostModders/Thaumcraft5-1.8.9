/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.renderers.models.ModelCube;
/*     */ import thaumcraft.common.blocks.basic.BlockPillar;
/*     */ import thaumcraft.common.blocks.basic.BlockPillar.PillarType;
/*     */ import thaumcraft.common.tiles.crafting.TileInfusionMatrix;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileInfusionMatrixRenderer extends net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
/*     */ {
/*  30 */   private ModelCube model = new ModelCube(0);
/*  31 */   private ModelCube model_over = new ModelCube(32);
/*  32 */   int type = 0;
/*     */   
/*  34 */   public TileInfusionMatrixRenderer(int type) { this.type = type; }
/*     */   
/*     */   private void drawHalo(TileEntity is, double x, double y, double z, float par8, int count)
/*     */   {
/*  38 */     GL11.glPushMatrix();
/*  39 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*     */     
/*  41 */     int q = !FMLClientHandler.instance().getClient().field_71474_y.field_74347_j ? 10 : 20;
/*     */     
/*  43 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*  45 */     RenderHelper.func_74518_a();
/*  46 */     float f1 = count / 500.0F;
/*  47 */     float f3 = 0.9F;
/*  48 */     float f2 = 0.0F;
/*     */     
/*  50 */     Random random = new Random(245L);
/*  51 */     GL11.glDisable(3553);
/*  52 */     GL11.glShadeModel(7425);
/*  53 */     GL11.glEnable(3042);
/*  54 */     GL11.glBlendFunc(770, 1);
/*  55 */     GL11.glDisable(3008);
/*  56 */     GL11.glEnable(2884);
/*  57 */     GL11.glDepthMask(false);
/*  58 */     GL11.glPushMatrix();
/*  59 */     for (int i = 0; i < q; i++)
/*     */     {
/*  61 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  62 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  63 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/*  64 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  65 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  66 */       GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
/*  67 */       tessellator.func_178180_c().func_181668_a(6, DefaultVertexFormats.field_181706_f);
/*  68 */       float fa = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
/*  69 */       float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
/*  70 */       fa /= 20.0F / (Math.min(count, 50) / 50.0F);
/*  71 */       f4 /= 20.0F / (Math.min(count, 50) / 50.0F);
/*  72 */       tessellator.func_178180_c().func_181662_b(0.0D, 0.0D, 0.0D).func_181669_b(255, 255, 255, (int)(255.0F * (1.0F - f1))).func_181675_d();
/*  73 */       tessellator.func_178180_c().func_181662_b(-0.866D * f4, fa, -0.5F * f4).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  74 */       tessellator.func_178180_c().func_181662_b(0.866D * f4, fa, -0.5F * f4).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  75 */       tessellator.func_178180_c().func_181662_b(0.0D, fa, 1.0F * f4).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  76 */       tessellator.func_178180_c().func_181662_b(-0.866D * f4, fa, -0.5F * f4).func_181669_b(255, 0, 255, 0).func_181675_d();
/*  77 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  80 */     GL11.glPopMatrix();
/*  81 */     GL11.glDepthMask(true);
/*  82 */     GL11.glDisable(2884);
/*  83 */     GL11.glDisable(3042);
/*  84 */     GL11.glShadeModel(7424);
/*  85 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  86 */     GL11.glEnable(3553);
/*  87 */     GL11.glEnable(3008);
/*  88 */     RenderHelper.func_74519_b();
/*  89 */     GL11.glBlendFunc(770, 771);
/*  90 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*  93 */   private static final ResourceLocation tex1 = new ResourceLocation("thaumcraft", "textures/blocks/infuser_normal.png");
/*  94 */   private static final ResourceLocation tex2 = new ResourceLocation("thaumcraft", "textures/blocks/infuser_ancient.png");
/*  95 */   private static final ResourceLocation tex3 = new ResourceLocation("thaumcraft", "textures/blocks/infuser_eldritch.png");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderInfusionMatrix(TileInfusionMatrix is, double par2, double par4, double par6, float par8)
/*     */   {
/* 103 */     GL11.glPushMatrix();
/*     */     
/* 105 */     ResourceLocation t = tex1;
/*     */     
/* 107 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.5F, (float)par6 + 0.5F);
/* 108 */     float ticks = Minecraft.func_71410_x().func_175606_aa().field_70173_aa + par8;
/*     */     
/* 110 */     if (is.func_145831_w() != null) {
/* 111 */       GL11.glRotatef(ticks % 360.0F * is.startUp, 0.0F, 1.0F, 0.0F);
/* 112 */       GL11.glRotatef(35.0F * is.startUp, 1.0F, 0.0F, 0.0F);
/* 113 */       GL11.glRotatef(45.0F * is.startUp, 0.0F, 0.0F, 1.0F);
/* 114 */       IBlockState bs = is.func_145831_w().func_180495_p(is.func_174877_v().func_177982_a(-1, -2, -1));
/* 115 */       if (bs.func_177228_b().containsKey(BlockPillar.TYPE)) {
/* 116 */         Comparable p = bs.func_177229_b(BlockPillar.TYPE);
/* 117 */         if (p == BlockPillar.PillarType.ANCIENT) t = tex2;
/* 118 */         if (p == BlockPillar.PillarType.ELDRITCH) { t = tex3;
/*     */         }
/*     */       }
/*     */     }
/* 122 */     func_147499_a(t);
/*     */     
/* 124 */     float instability = Math.min(6.0F, 1.0F + is.instability * 0.66F * (Math.min(is.craftCount, 50) / 50.0F));
/*     */     
/* 126 */     float b1 = 0.0F;
/* 127 */     float b2 = 0.0F;
/* 128 */     float b3 = 0.0F;
/* 129 */     int aa = 0;
/* 130 */     int bb = 0;
/* 131 */     int cc = 0;
/* 132 */     for (int a = 0; a < 2; a++) {
/* 133 */       for (int b = 0; b < 2; b++) {
/* 134 */         for (int c = 0; c < 2; c++) {
/* 135 */           if (is.active) {
/* 136 */             b1 = MathHelper.func_76126_a((ticks + a * 10) / (15.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/* 137 */             b2 = MathHelper.func_76126_a((ticks + b * 10) / (14.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/* 138 */             b3 = MathHelper.func_76126_a((ticks + c * 10) / (13.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/*     */           }
/*     */           
/* 141 */           aa = a == 0 ? -1 : 1;
/* 142 */           bb = b == 0 ? -1 : 1;
/* 143 */           cc = c == 0 ? -1 : 1;
/*     */           
/* 145 */           GL11.glPushMatrix();
/* 146 */           GL11.glTranslatef(b1 + aa * 0.25F, b2 + bb * 0.25F, b3 + cc * 0.25F);
/* 147 */           if (a > 0) GL11.glRotatef(90.0F, a, 0.0F, 0.0F);
/* 148 */           if (b > 0) GL11.glRotatef(90.0F, 0.0F, b, 0.0F);
/* 149 */           if (c > 0) { GL11.glRotatef(90.0F, 0.0F, 0.0F, c);
/*     */           }
/* 151 */           GL11.glScaled(0.45D, 0.45D, 0.45D);
/*     */           
/* 153 */           this.model.render();
/*     */           
/* 155 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */     }
/* 159 */     if (is.active) {
/* 160 */       GL11.glPushMatrix();
/* 161 */       GL11.glAlphaFunc(516, 0.003921569F);
/* 162 */       GL11.glEnable(3042);
/* 163 */       GL11.glBlendFunc(770, 1);
/*     */       
/* 165 */       for (int a = 0; a < 2; a++) {
/* 166 */         for (int b = 0; b < 2; b++) {
/* 167 */           for (int c = 0; c < 2; c++)
/*     */           {
/* 169 */             b1 = MathHelper.func_76126_a((ticks + a * 10) / (15.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/* 170 */             b2 = MathHelper.func_76126_a((ticks + b * 10) / (14.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/* 171 */             b3 = MathHelper.func_76126_a((ticks + c * 10) / (13.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/*     */             
/* 173 */             aa = a == 0 ? -1 : 1;
/* 174 */             bb = b == 0 ? -1 : 1;
/* 175 */             cc = c == 0 ? -1 : 1;
/*     */             
/* 177 */             GL11.glPushMatrix();
/*     */             
/* 179 */             GL11.glTranslatef(b1 + aa * 0.25F, b2 + bb * 0.25F, b3 + cc * 0.25F);
/* 180 */             if (a > 0) GL11.glRotatef(90.0F, a, 0.0F, 0.0F);
/* 181 */             if (b > 0) GL11.glRotatef(90.0F, 0.0F, b, 0.0F);
/* 182 */             if (c > 0) { GL11.glRotatef(90.0F, 0.0F, 0.0F, c);
/*     */             }
/* 184 */             GL11.glScaled(0.45D, 0.45D, 0.45D);
/*     */             
/* 186 */             int j = 15728880;
/* 187 */             int k = j % 65536;
/* 188 */             int l = j / 65536;
/* 189 */             OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 190 */             GL11.glColor4f(0.8F, 0.1F, 1.0F, (MathHelper.func_76126_a((ticks + a * 2 + b * 3 + c * 4) / 4.0F) * 0.1F + 0.2F) * is.startUp);
/* 191 */             this.model_over.render();
/* 192 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */             
/* 194 */             GL11.glPopMatrix();
/*     */           }
/*     */         }
/*     */       }
/* 198 */       GL11.glBlendFunc(770, 771);
/* 199 */       GL11.glDisable(3042);
/* 200 */       GL11.glAlphaFunc(516, 0.1F);
/* 201 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 204 */     GL11.glPopMatrix();
/*     */     
/* 206 */     if (is.crafting) {
/* 207 */       drawHalo(is, par2, par4, par6, par8, is.craftCount);
/*     */     }
/* 209 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180535_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int q)
/*     */   {
/* 215 */     switch (this.type) {
/* 216 */     case 0:  renderInfusionMatrix((TileInfusionMatrix)par1TileEntity, par2, par4, par6, par8); break;
/* 217 */     case 1:  func_180535_a((TileInfusionMatrix)par1TileEntity, par2, par4, par6, par8, q);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileInfusionMatrixRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */