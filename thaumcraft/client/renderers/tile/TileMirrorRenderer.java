/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ 
/*     */ 
/*     */ public class TileMirrorRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   FloatBuffer fBuffer;
/*     */   
/*  33 */   public TileMirrorRenderer() { this.fBuffer = GLAllocation.func_74529_h(16); }
/*     */   
/*  35 */   private static final ResourceLocation t1 = new ResourceLocation("thaumcraft", "textures/misc/tunnel.png");
/*  36 */   private static final ResourceLocation t2 = new ResourceLocation("thaumcraft", "textures/misc/particlefield.png");
/*     */   
/*     */   public void drawPlaneYPos(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/*  40 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/*  41 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/*  42 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/*  43 */     GL11.glDisable(2896);
/*  44 */     Random random = new Random(31100L);
/*  45 */     float offset = 0.99F;
/*  46 */     float p = 0.1875F;
/*  47 */     for (int i = 0; i < 16; i++)
/*     */     {
/*  49 */       GL11.glPushMatrix();
/*  50 */       float f5 = 16 - i;
/*  51 */       float f6 = 0.0625F;
/*  52 */       float f7 = 1.0F / (f5 + 1.0F);
/*  53 */       if (i == 0)
/*     */       {
/*  55 */         func_147499_a(t1);
/*  56 */         f7 = 0.1F;
/*  57 */         f5 = 65.0F;
/*  58 */         f6 = 0.125F;
/*  59 */         GL11.glEnable(3042);
/*  60 */         GL11.glBlendFunc(770, 771);
/*     */       }
/*  62 */       if (i == 1)
/*     */       {
/*  64 */         func_147499_a(t2);
/*  65 */         GL11.glEnable(3042);
/*  66 */         GL11.glBlendFunc(1, 1);
/*  67 */         f6 = 0.5F;
/*     */       }
/*  69 */       float f8 = (float)(y + offset);
/*  70 */       float f9 = (float)(f8 - ActiveRenderInfo.func_178804_a().field_72448_b);
/*  71 */       float f10 = (float)(f8 + f5 - ActiveRenderInfo.func_178804_a().field_72448_b);
/*  72 */       float f11 = f9 / f10;
/*  73 */       f11 = (float)(y + offset) + f11;
/*  74 */       GL11.glTranslatef(px, f11, pz);
/*  75 */       GL11.glTexGeni(8192, 9472, 9217);
/*  76 */       GL11.glTexGeni(8193, 9472, 9217);
/*  77 */       GL11.glTexGeni(8194, 9472, 9217);
/*  78 */       GL11.glTexGeni(8195, 9472, 9216);
/*  79 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/*  80 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/*  81 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*  82 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/*  83 */       GL11.glEnable(3168);
/*  84 */       GL11.glEnable(3169);
/*  85 */       GL11.glEnable(3170);
/*  86 */       GL11.glEnable(3171);
/*  87 */       GL11.glPopMatrix();
/*  88 */       GL11.glMatrixMode(5890);
/*  89 */       GL11.glPushMatrix();
/*  90 */       GL11.glLoadIdentity();
/*  91 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/*  92 */       GL11.glScalef(f6, f6, f6);
/*  93 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/*  94 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/*  95 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/*  96 */       GL11.glTranslatef(-px, -pz, -py);
/*     */       
/*  98 */       GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72450_a * f5 / f9, ActiveRenderInfo.func_178804_a().field_72449_c * f5 / f9, -py);
/*     */       
/* 100 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 101 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 102 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 103 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 104 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 105 */       if (i == 0)
/*     */       {
/* 107 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 110 */       tessellator.func_178180_c().func_181662_b(x + p, y + offset, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 111 */       tessellator.func_178180_c().func_181662_b(x + p, y + offset, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 112 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + offset, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 113 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + offset, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 115 */       tessellator.func_78381_a();
/* 116 */       GL11.glPopMatrix();
/* 117 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 120 */     GL11.glDisable(3042);
/* 121 */     GL11.glDisable(3168);
/* 122 */     GL11.glDisable(3169);
/* 123 */     GL11.glDisable(3170);
/* 124 */     GL11.glDisable(3171);
/* 125 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneYNeg(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 130 */     float f1 = (float)TileEntityRendererDispatcher.field_147554_b;
/* 131 */     float f2 = (float)TileEntityRendererDispatcher.field_147555_c;
/* 132 */     float f3 = (float)TileEntityRendererDispatcher.field_147552_d;
/* 133 */     GL11.glDisable(2896);
/* 134 */     Random random = new Random(31100L);
/* 135 */     float offset = 0.01F;
/* 136 */     float p = 0.1875F;
/* 137 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 139 */       GL11.glPushMatrix();
/* 140 */       float f5 = 16 - i;
/* 141 */       float f6 = 0.0625F;
/* 142 */       float f7 = 1.0F / (f5 + 1.0F);
/* 143 */       if (i == 0)
/*     */       {
/* 145 */         func_147499_a(t1);
/* 146 */         f7 = 0.1F;
/* 147 */         f5 = 65.0F;
/* 148 */         f6 = 0.125F;
/* 149 */         GL11.glEnable(3042);
/* 150 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 152 */       if (i == 1)
/*     */       {
/* 154 */         func_147499_a(t2);
/* 155 */         GL11.glEnable(3042);
/* 156 */         GL11.glBlendFunc(1, 1);
/* 157 */         f6 = 0.5F;
/*     */       }
/* 159 */       float f8 = (float)-(y + offset);
/* 160 */       float f9 = (float)(f8 + ActiveRenderInfo.func_178804_a().field_72448_b);
/* 161 */       float f10 = (float)(f8 + f5 + ActiveRenderInfo.func_178804_a().field_72448_b);
/* 162 */       float f11 = f9 / f10;
/* 163 */       f11 = (float)(y + offset) + f11;
/* 164 */       GL11.glTranslatef(f1, f11, f3);
/* 165 */       GL11.glTexGeni(8192, 9472, 9217);
/* 166 */       GL11.glTexGeni(8193, 9472, 9217);
/* 167 */       GL11.glTexGeni(8194, 9472, 9217);
/* 168 */       GL11.glTexGeni(8195, 9472, 9216);
/* 169 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 170 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 171 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 172 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 173 */       GL11.glEnable(3168);
/* 174 */       GL11.glEnable(3169);
/* 175 */       GL11.glEnable(3170);
/* 176 */       GL11.glEnable(3171);
/* 177 */       GL11.glPopMatrix();
/* 178 */       GL11.glMatrixMode(5890);
/* 179 */       GL11.glPushMatrix();
/* 180 */       GL11.glLoadIdentity();
/* 181 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 182 */       GL11.glScalef(f6, f6, f6);
/* 183 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 184 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 185 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 186 */       GL11.glTranslatef(-f1, -f3, -f2);
/*     */       
/*     */ 
/* 189 */       GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72450_a * f5 / f9, ActiveRenderInfo.func_178804_a().field_72449_c * f5 / f9, -f2);
/* 190 */       Tessellator tessellator = Tessellator.func_178181_a();
/*     */       
/* 192 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 193 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 194 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 195 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 196 */       if (i == 0)
/*     */       {
/* 198 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 201 */       tessellator.func_178180_c().func_181662_b(x + p, y + offset, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 202 */       tessellator.func_178180_c().func_181662_b(x + p, y + offset, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 203 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + offset, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 204 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + offset, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 206 */       tessellator.func_78381_a();
/* 207 */       GL11.glPopMatrix();
/* 208 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 211 */     GL11.glDisable(3042);
/* 212 */     GL11.glDisable(3168);
/* 213 */     GL11.glDisable(3169);
/* 214 */     GL11.glDisable(3170);
/* 215 */     GL11.glDisable(3171);
/* 216 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZNeg(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 221 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 222 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 223 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 224 */     GL11.glDisable(2896);
/* 225 */     Random random = new Random(31100L);
/* 226 */     float offset = 0.01F;
/* 227 */     float p = 0.1875F;
/* 228 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 230 */       GL11.glPushMatrix();
/* 231 */       float f5 = 16 - i;
/* 232 */       float f6 = 0.0625F;
/* 233 */       float f7 = 1.0F / (f5 + 1.0F);
/* 234 */       if (i == 0)
/*     */       {
/* 236 */         func_147499_a(t1);
/* 237 */         f7 = 0.1F;
/* 238 */         f5 = 65.0F;
/* 239 */         f6 = 0.125F;
/* 240 */         GL11.glEnable(3042);
/* 241 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 243 */       if (i == 1)
/*     */       {
/* 245 */         func_147499_a(t2);
/* 246 */         GL11.glEnable(3042);
/* 247 */         GL11.glBlendFunc(1, 1);
/* 248 */         f6 = 0.5F;
/*     */       }
/* 250 */       float f8 = (float)-(z + offset);
/* 251 */       float f9 = (float)(f8 + ActiveRenderInfo.func_178804_a().field_72449_c);
/* 252 */       float f10 = (float)(f8 + f5 + ActiveRenderInfo.func_178804_a().field_72449_c);
/* 253 */       float f11 = f9 / f10;
/* 254 */       f11 = (float)(z + offset) + f11;
/* 255 */       GL11.glTranslatef(px, py, f11);
/* 256 */       GL11.glTexGeni(8192, 9472, 9217);
/* 257 */       GL11.glTexGeni(8193, 9472, 9217);
/* 258 */       GL11.glTexGeni(8194, 9472, 9217);
/* 259 */       GL11.glTexGeni(8195, 9472, 9216);
/* 260 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 261 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 262 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */       
/* 264 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 265 */       GL11.glEnable(3168);
/* 266 */       GL11.glEnable(3169);
/* 267 */       GL11.glEnable(3170);
/* 268 */       GL11.glEnable(3171);
/* 269 */       GL11.glPopMatrix();
/* 270 */       GL11.glMatrixMode(5890);
/* 271 */       GL11.glPushMatrix();
/* 272 */       GL11.glLoadIdentity();
/* 273 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 274 */       GL11.glScalef(f6, f6, f6);
/* 275 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 276 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 277 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 278 */       GL11.glTranslatef(-px, -py, -pz);
/*     */       
/*     */ 
/* 281 */       GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72450_a * f5 / f9, ActiveRenderInfo.func_178804_a().field_72448_b * f5 / f9, -pz);
/*     */       
/*     */ 
/* 284 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 285 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 286 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 287 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 288 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 289 */       if (i == 0)
/*     */       {
/* 291 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 294 */       tessellator.func_178180_c().func_181662_b(x + p, y + 1.0D - p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 295 */       tessellator.func_178180_c().func_181662_b(x + p, y + p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 296 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 297 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + 1.0D - p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 299 */       tessellator.func_78381_a();
/* 300 */       GL11.glPopMatrix();
/* 301 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 304 */     GL11.glDisable(3042);
/* 305 */     GL11.glDisable(3168);
/* 306 */     GL11.glDisable(3169);
/* 307 */     GL11.glDisable(3170);
/* 308 */     GL11.glDisable(3171);
/* 309 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZPos(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 314 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 315 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 316 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 317 */     GL11.glDisable(2896);
/* 318 */     Random random = new Random(31100L);
/* 319 */     float offset = 0.99F;
/* 320 */     float p = 0.1875F;
/* 321 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 323 */       GL11.glPushMatrix();
/* 324 */       float f5 = 16 - i;
/* 325 */       float f6 = 0.0625F;
/* 326 */       float f7 = 1.0F / (f5 + 1.0F);
/* 327 */       if (i == 0)
/*     */       {
/* 329 */         func_147499_a(t1);
/* 330 */         f7 = 0.1F;
/* 331 */         f5 = 65.0F;
/* 332 */         f6 = 0.125F;
/* 333 */         GL11.glEnable(3042);
/* 334 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 336 */       if (i == 1)
/*     */       {
/* 338 */         func_147499_a(t2);
/* 339 */         GL11.glEnable(3042);
/* 340 */         GL11.glBlendFunc(1, 1);
/* 341 */         f6 = 0.5F;
/*     */       }
/* 343 */       float f8 = (float)(z + offset);
/* 344 */       float f9 = (float)(f8 - ActiveRenderInfo.func_178804_a().field_72449_c);
/* 345 */       float f10 = (float)(f8 + f5 - ActiveRenderInfo.func_178804_a().field_72449_c);
/* 346 */       float f11 = f9 / f10;
/* 347 */       f11 = (float)(z + offset) + f11;
/* 348 */       GL11.glTranslatef(px, py, f11);
/* 349 */       GL11.glTexGeni(8192, 9472, 9217);
/* 350 */       GL11.glTexGeni(8193, 9472, 9217);
/* 351 */       GL11.glTexGeni(8194, 9472, 9217);
/* 352 */       GL11.glTexGeni(8195, 9472, 9216);
/* 353 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 354 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 355 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */       
/* 357 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 358 */       GL11.glEnable(3168);
/* 359 */       GL11.glEnable(3169);
/* 360 */       GL11.glEnable(3170);
/* 361 */       GL11.glEnable(3171);
/* 362 */       GL11.glPopMatrix();
/* 363 */       GL11.glMatrixMode(5890);
/* 364 */       GL11.glPushMatrix();
/* 365 */       GL11.glLoadIdentity();
/* 366 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 367 */       GL11.glScalef(f6, f6, f6);
/* 368 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 369 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 370 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 371 */       GL11.glTranslatef(-px, -py, -pz);
/*     */       
/*     */ 
/* 374 */       GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72450_a * f5 / f9, ActiveRenderInfo.func_178804_a().field_72448_b * f5 / f9, -pz);
/*     */       
/* 376 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 377 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 378 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 379 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 380 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 381 */       if (i == 0)
/*     */       {
/* 383 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 386 */       tessellator.func_178180_c().func_181662_b(x + p, y + p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 387 */       tessellator.func_178180_c().func_181662_b(x + p, y + 1.0D - p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 388 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + 1.0D - p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 389 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 391 */       tessellator.func_78381_a();
/* 392 */       GL11.glPopMatrix();
/* 393 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 396 */     GL11.glDisable(3042);
/* 397 */     GL11.glDisable(3168);
/* 398 */     GL11.glDisable(3169);
/* 399 */     GL11.glDisable(3170);
/* 400 */     GL11.glDisable(3171);
/* 401 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXNeg(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 406 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 407 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 408 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 409 */     GL11.glDisable(2896);
/* 410 */     Random random = new Random(31100L);
/* 411 */     float offset = 0.01F;
/* 412 */     float p = 0.1875F;
/* 413 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 415 */       GL11.glPushMatrix();
/* 416 */       float f5 = 16 - i;
/* 417 */       float f6 = 0.0625F;
/* 418 */       float f7 = 1.0F / (f5 + 1.0F);
/* 419 */       if (i == 0)
/*     */       {
/* 421 */         func_147499_a(t1);
/* 422 */         f7 = 0.1F;
/* 423 */         f5 = 65.0F;
/* 424 */         f6 = 0.125F;
/* 425 */         GL11.glEnable(3042);
/* 426 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 428 */       if (i == 1)
/*     */       {
/* 430 */         func_147499_a(t2);
/* 431 */         GL11.glEnable(3042);
/* 432 */         GL11.glBlendFunc(1, 1);
/* 433 */         f6 = 0.5F;
/*     */       }
/* 435 */       float f8 = (float)-(x + offset);
/* 436 */       float f9 = (float)(f8 + ActiveRenderInfo.func_178804_a().field_72450_a);
/* 437 */       float f10 = (float)(f8 + f5 + ActiveRenderInfo.func_178804_a().field_72450_a);
/* 438 */       float f11 = f9 / f10;
/* 439 */       f11 = (float)(x + offset) + f11;
/* 440 */       GL11.glTranslatef(f11, py, pz);
/* 441 */       GL11.glTexGeni(8192, 9472, 9217);
/* 442 */       GL11.glTexGeni(8193, 9472, 9217);
/* 443 */       GL11.glTexGeni(8194, 9472, 9217);
/* 444 */       GL11.glTexGeni(8195, 9472, 9216);
/*     */       
/*     */ 
/* 447 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 448 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 449 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 450 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 451 */       GL11.glEnable(3168);
/* 452 */       GL11.glEnable(3169);
/* 453 */       GL11.glEnable(3170);
/* 454 */       GL11.glEnable(3171);
/* 455 */       GL11.glPopMatrix();
/* 456 */       GL11.glMatrixMode(5890);
/* 457 */       GL11.glPushMatrix();
/* 458 */       GL11.glLoadIdentity();
/* 459 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 460 */       GL11.glScalef(f6, f6, f6);
/* 461 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 462 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 463 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 464 */       GL11.glTranslatef(-pz, -py, -px);
/*     */       
/*     */ 
/* 467 */       GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72449_c * f5 / f9, ActiveRenderInfo.func_178804_a().field_72448_b * f5 / f9, -px);
/*     */       
/*     */ 
/* 470 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 471 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 472 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 473 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 474 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 475 */       if (i == 0)
/*     */       {
/* 477 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 480 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 1.0D - p, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 481 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 1.0D - p, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 482 */       tessellator.func_178180_c().func_181662_b(x + offset, y + p, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 483 */       tessellator.func_178180_c().func_181662_b(x + offset, y + p, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 485 */       tessellator.func_78381_a();
/* 486 */       GL11.glPopMatrix();
/* 487 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 490 */     GL11.glDisable(3042);
/* 491 */     GL11.glDisable(3168);
/* 492 */     GL11.glDisable(3169);
/* 493 */     GL11.glDisable(3170);
/* 494 */     GL11.glDisable(3171);
/* 495 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXPos(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 500 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 501 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 502 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 503 */     GL11.glDisable(2896);
/* 504 */     Random random = new Random(31100L);
/* 505 */     float offset = 0.99F;
/* 506 */     float p = 0.1875F;
/* 507 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 509 */       GL11.glPushMatrix();
/* 510 */       float f5 = 16 - i;
/* 511 */       float f6 = 0.0625F;
/* 512 */       float f7 = 1.0F / (f5 + 1.0F);
/* 513 */       if (i == 0)
/*     */       {
/* 515 */         func_147499_a(t1);
/* 516 */         f7 = 0.1F;
/* 517 */         f5 = 65.0F;
/* 518 */         f6 = 0.125F;
/* 519 */         GL11.glEnable(3042);
/* 520 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 522 */       if (i == 1)
/*     */       {
/* 524 */         func_147499_a(t2);
/* 525 */         GL11.glEnable(3042);
/* 526 */         GL11.glBlendFunc(1, 1);
/* 527 */         f6 = 0.5F;
/*     */       }
/* 529 */       float f8 = (float)(x + offset);
/* 530 */       float f9 = (float)(f8 - ActiveRenderInfo.func_178804_a().field_72450_a);
/* 531 */       float f10 = (float)(f8 + f5 - ActiveRenderInfo.func_178804_a().field_72450_a);
/* 532 */       float f11 = f9 / f10;
/* 533 */       f11 = (float)(x + offset) + f11;
/* 534 */       GL11.glTranslatef(f11, py, pz);
/* 535 */       GL11.glTexGeni(8192, 9472, 9217);
/* 536 */       GL11.glTexGeni(8193, 9472, 9217);
/* 537 */       GL11.glTexGeni(8194, 9472, 9217);
/* 538 */       GL11.glTexGeni(8195, 9472, 9216);
/*     */       
/*     */ 
/* 541 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 542 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 543 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 544 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 545 */       GL11.glEnable(3168);
/* 546 */       GL11.glEnable(3169);
/* 547 */       GL11.glEnable(3170);
/* 548 */       GL11.glEnable(3171);
/* 549 */       GL11.glPopMatrix();
/* 550 */       GL11.glMatrixMode(5890);
/* 551 */       GL11.glPushMatrix();
/* 552 */       GL11.glLoadIdentity();
/* 553 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 554 */       GL11.glScalef(f6, f6, f6);
/* 555 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 556 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 557 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 558 */       GL11.glTranslatef(-pz, -py, -px);
/*     */       
/*     */ 
/* 561 */       GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72449_c * f5 / f9, ActiveRenderInfo.func_178804_a().field_72448_b * f5 / f9, -px);
/*     */       
/*     */ 
/* 564 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 565 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 566 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 567 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 568 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 569 */       if (i == 0)
/*     */       {
/* 571 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 574 */       tessellator.func_178180_c().func_181662_b(x + offset, y + p, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 575 */       tessellator.func_178180_c().func_181662_b(x + offset, y + p, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 576 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 1.0D - p, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 577 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 1.0D - p, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 579 */       tessellator.func_78381_a();
/* 580 */       GL11.glPopMatrix();
/* 581 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 584 */     GL11.glDisable(3042);
/* 585 */     GL11.glDisable(3168);
/* 586 */     GL11.glDisable(3169);
/* 587 */     GL11.glDisable(3170);
/* 588 */     GL11.glDisable(3171);
/* 589 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3)
/*     */   {
/* 594 */     this.fBuffer.clear();
/* 595 */     this.fBuffer.put(f).put(f1).put(f2).put(f3);
/* 596 */     this.fBuffer.flip();
/* 597 */     return this.fBuffer;
/*     */   }
/*     */   
/* 600 */   private static ResourceLocation mp = new ResourceLocation("thaumcraft", "textures/blocks/mirrorpane.png");
/* 601 */   private static ResourceLocation mpt = new ResourceLocation("thaumcraft", "textures/blocks/mirrorpanetrans.png");
/*     */   
/*     */ 
/*     */   public void func_180535_a(TileEntity te, double x, double y, double z, float f, int q)
/*     */   {
/* 606 */     EnumFacing dir = BlockStateUtils.getFacing(te.func_145832_p());
/* 607 */     boolean linked = false;
/* 608 */     if ((te instanceof TileMirror)) {
/* 609 */       linked = ((TileMirror)te).linked;
/*     */     }
/* 611 */     if ((te instanceof TileMirrorEssentia)) linked = ((TileMirrorEssentia)te).linked;
/* 612 */     int b = te.func_145838_q().func_176207_c(te.func_145831_w(), te.func_174877_v());
/*     */     
/* 614 */     if ((linked) && (FMLClientHandler.instance().getClient().field_71439_g.func_174831_c(te.func_174877_v()) < 1024.0D)) {
/* 615 */       GL11.glPushMatrix();
/* 616 */       switch (dir) {
/* 617 */       case DOWN:  drawPlaneYPos(te, x, y, z, f); break;
/* 618 */       case UP:  drawPlaneYNeg(te, x, y, z, f); break;
/* 619 */       case WEST:  drawPlaneXPos(te, x, y, z, f); break;
/* 620 */       case EAST:  drawPlaneXNeg(te, x, y, z, f); break;
/* 621 */       case NORTH:  drawPlaneZPos(te, x, y, z, f); break;
/* 622 */       case SOUTH:  drawPlaneZNeg(te, x, y, z, f);
/*     */       }
/* 624 */       GL11.glPopMatrix();
/* 625 */       GL11.glPushMatrix();
/* 626 */       GL11.glEnable(3042);
/* 627 */       GL11.glBlendFunc(770, 771);
/* 628 */       translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.02F);
/* 629 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 630 */       GL11.glTranslated(0.5D, -0.5D, 0.0D);
/* 631 */       UtilsFX.renderQuadCentered(mpt, 1.0F, 1.0F, 1.0F, 1.0F, b, 771, 1.0F);
/* 632 */       GL11.glDisable(3042);
/* 633 */       GL11.glPopMatrix();
/*     */     } else {
/* 635 */       GL11.glPushMatrix();
/* 636 */       GL11.glEnable(3042);
/* 637 */       GL11.glBlendFunc(770, 771);
/* 638 */       translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.02F);
/* 639 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 640 */       GL11.glTranslated(0.5D, -0.5D, 0.0D);
/* 641 */       UtilsFX.renderQuadCentered(mp, 1.0F, 1.0F, 1.0F, 1.0F, b, 771, 1.0F);
/* 642 */       GL11.glDisable(3042);
/* 643 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 646 */     GL11.glPushMatrix();
/* 647 */     GL11.glEnable(3042);
/* 648 */     GL11.glBlendFunc(770, 771);
/* 649 */     translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.01F);
/* 650 */     UtilsFX.renderItemIn2D(te.func_145838_q() == BlocksTC.mirror ? "thaumcraft:blocks/mirrorframe" : "thaumcraft:blocks/mirrorframe2", 0.0625F);
/* 651 */     GL11.glDisable(3042);
/* 652 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(float x, float y, float z, int orientation, float off)
/*     */   {
/* 657 */     if (orientation == 0)
/*     */     {
/* 659 */       GL11.glTranslatef(x, y + 1.0F, z + 1.0F);
/* 660 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*     */     }
/* 662 */     else if (orientation == 1)
/*     */     {
/* 664 */       GL11.glTranslatef(x, y, z);
/* 665 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 666 */     } else if (orientation == 2)
/*     */     {
/* 668 */       GL11.glTranslatef(x, y, z + 1.0F);
/* 669 */     } else if (orientation == 3)
/*     */     {
/* 671 */       GL11.glTranslatef(x + 1.0F, y, z);
/* 672 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 673 */     } else if (orientation == 4)
/*     */     {
/* 675 */       GL11.glTranslatef(x + 1.0F, y, z + 1.0F);
/* 676 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 677 */     } else if (orientation == 5)
/*     */     {
/* 679 */       GL11.glTranslatef(x, y, z);
/* 680 */       GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/* 682 */     GL11.glTranslatef(0.0F, 0.0F, -off);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileMirrorRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */