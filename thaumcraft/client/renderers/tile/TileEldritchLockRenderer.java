/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.renderers.models.ModelCube;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchLock;
/*     */ 
/*     */ 
/*     */ public class TileEldritchLockRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   FloatBuffer fBuffer;
/*     */   private boolean inrange;
/*  33 */   private ModelCube model = new ModelCube(0);
/*     */   
/*     */   public TileEldritchLockRenderer()
/*     */   {
/*  37 */     this.fBuffer = GLAllocation.func_74529_h(16);
/*     */   }
/*     */   
/*  40 */   private static final ResourceLocation t1 = new ResourceLocation("thaumcraft", "textures/misc/tunnel.png");
/*  41 */   private static final ResourceLocation t2 = new ResourceLocation("thaumcraft", "textures/misc/particlefield.png");
/*  42 */   private static final ResourceLocation t3 = new ResourceLocation("thaumcraft", "textures/misc/particlefield32.png");
/*  43 */   private static final ResourceLocation t4 = new ResourceLocation("thaumcraft", "textures/models/eldritch_cube.png");
/*  44 */   private static final ResourceLocation t5 = new ResourceLocation("thaumcraft", "textures/models/eldritch_lock.png");
/*     */   
/*  46 */   ItemStack is = null;
/*  47 */   EntityItem entityitem = null;
/*     */   
/*     */ 
/*     */   public void func_180535_a(TileEntity te, double x, double y, double z, float f, int q)
/*     */   {
/*  52 */     this.inrange = (Minecraft.func_71410_x().func_175606_aa().func_174831_c(te.func_174877_v()) < 512.0D);
/*     */     
/*  54 */     if (this.is == null) { this.is = new ItemStack(ItemsTC.runedTablet);
/*     */     }
/*  56 */     float bob = 0.0F;
/*  57 */     float count = Minecraft.func_71410_x().func_175606_aa().field_70173_aa + f;
/*     */     
/*  59 */     GL11.glPushMatrix();
/*     */     
/*  61 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*     */     
/*  63 */     EnumFacing dir = EnumFacing.field_82609_l[((TileEldritchLock)te).getLockFacing()];
/*     */     
/*  65 */     func_147499_a(t5);
/*  66 */     GL11.glPushMatrix();
/*  67 */     GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
/*  68 */     GL11.glRotated(180 + dir.func_176736_b() * 90, 0.0D, 1.0D, 0.0D);
/*  69 */     this.model.render();
/*  70 */     GL11.glPopMatrix();
/*     */     
/*  72 */     func_147499_a(t4);
/*     */     
/*  74 */     for (int u = 0; u < 4; u++) {
/*  75 */       GL11.glPushMatrix();
/*  76 */       GL11.glRotated(90 * u, dir.func_82601_c(), dir.func_96559_d(), dir.func_82599_e());
/*  77 */       for (int a = 1; a < 5 - (((TileEldritchLock)te).count + u * 5) / 20; a++) {
/*  78 */         GL11.glPushMatrix();
/*  79 */         GL11.glTranslated(0.0D, 0.25F + 0.5F * a, 0.0D);
/*  80 */         float w = MathHelper.func_76126_a((count + a * 10 + u * 20) / 20.0F) * 0.1F;
/*  81 */         if ((a == 1) || (a == 4)) w = w / 2.0F + 0.2F;
/*  82 */         GL11.glScaled(0.5D + w, 0.5D, 0.5D + w);
/*  83 */         this.model.render();
/*  84 */         GL11.glPopMatrix();
/*     */       }
/*  86 */       GL11.glPopMatrix();
/*     */     }
/*  88 */     GL11.glPopMatrix();
/*     */     
/*  90 */     if (((TileEldritchLock)te).count >= 0) {
/*  91 */       GL11.glPushMatrix();
/*  92 */       GL11.glTranslatef((float)x + 0.5F + dir.func_82601_c() * 0.525F, (float)y + 0.16F, (float)z + 0.5F + dir.func_82599_e() * 0.525F);
/*  93 */       switch (dir.ordinal()) {
/*  94 */       case 5:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  95 */       case 4:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/*  96 */       case 2:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*  98 */       GL11.glScaled(1.0D, 1.0D, 1.0D);
/*  99 */       if (this.entityitem == null)
/* 100 */         this.entityitem = new EntityItem(te.func_145831_w(), 0.0D, 0.0D, 0.0D, this.is);
/* 101 */       this.entityitem.field_70290_d = 0.0F;
/*     */       
/* 103 */       RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
/* 104 */       rendermanager.func_147940_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 105 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 108 */     GL11.glPushMatrix();
/* 109 */     GL11.glDisable(2912);
/* 110 */     switch (((TileEldritchLock)te).getLockFacing()) {
/* 111 */     case 2:  drawPlaneZNeg(x, y, z, f, 3); break;
/* 112 */     case 3:  drawPlaneZPos(x, y, z, f, 3); break;
/* 113 */     case 4:  drawPlaneXNeg(x, y, z, f, 3); break;
/* 114 */     case 5:  drawPlaneXPos(x, y, z, f, 3);
/*     */     }
/* 116 */     GL11.glEnable(2912);
/* 117 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawPlaneZPos(double x, double y, double z, float f, int height)
/*     */   {
/* 126 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 127 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 128 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 129 */     GL11.glDisable(2896);
/* 130 */     Random random = new Random(31100L);
/* 131 */     float offset = 0.5F;
/*     */     
/* 133 */     if (this.inrange)
/*     */     {
/* 135 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 137 */         GL11.glPushMatrix();
/* 138 */         float f5 = 16 - i;
/* 139 */         float f6 = 0.0625F;
/* 140 */         float f7 = 1.0F / (f5 + 1.0F);
/* 141 */         if (i == 0)
/*     */         {
/* 143 */           func_147499_a(t1);
/* 144 */           f7 = 0.1F;
/* 145 */           f5 = 65.0F;
/* 146 */           f6 = 0.125F;
/* 147 */           GL11.glEnable(3042);
/* 148 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 150 */         if (i == 1)
/*     */         {
/* 152 */           func_147499_a(t2);
/* 153 */           GL11.glEnable(3042);
/* 154 */           GL11.glBlendFunc(1, 1);
/* 155 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 158 */         float f8 = (float)-(z + offset);
/* 159 */         float f9 = (float)(f8 + ActiveRenderInfo.func_178804_a().field_72449_c);
/* 160 */         float f10 = (float)(f8 + f5 + ActiveRenderInfo.func_178804_a().field_72449_c);
/* 161 */         float f11 = f9 / f10;
/* 162 */         f11 = (float)(z + offset) + f11;
/* 163 */         GL11.glTranslatef(px, py, f11);
/* 164 */         GL11.glTexGeni(8192, 9472, 9217);
/* 165 */         GL11.glTexGeni(8193, 9472, 9217);
/* 166 */         GL11.glTexGeni(8194, 9472, 9217);
/* 167 */         GL11.glTexGeni(8195, 9472, 9216);
/* 168 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 169 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 170 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 172 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 173 */         GL11.glEnable(3168);
/* 174 */         GL11.glEnable(3169);
/* 175 */         GL11.glEnable(3170);
/* 176 */         GL11.glEnable(3171);
/* 177 */         GL11.glPopMatrix();
/* 178 */         GL11.glMatrixMode(5890);
/* 179 */         GL11.glPushMatrix();
/* 180 */         GL11.glLoadIdentity();
/* 181 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 182 */         GL11.glScalef(f6, f6, f6);
/* 183 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 184 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 185 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 186 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 189 */         GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72450_a * f5 / f9, ActiveRenderInfo.func_178804_a().field_72448_b * f5 / f9, -pz);
/*     */         
/*     */ 
/* 192 */         Tessellator tessellator = Tessellator.func_178181_a();
/* 193 */         tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 194 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 195 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 196 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 197 */         if (i == 0)
/*     */         {
/* 199 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/*     */ 
/* 203 */         tessellator.func_178180_c().func_181662_b(x - 2.0D, y + 3.0D, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 204 */         tessellator.func_178180_c().func_181662_b(x - 2.0D, y - 2.0D, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 205 */         tessellator.func_178180_c().func_181662_b(x + 3.0D, y - 2.0D, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 206 */         tessellator.func_178180_c().func_181662_b(x + 3.0D, y + 3.0D, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */         
/* 208 */         tessellator.func_78381_a();
/* 209 */         GL11.glPopMatrix();
/* 210 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 213 */       GL11.glPushMatrix();
/* 214 */       func_147499_a(t3);
/* 215 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 216 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181709_i);
/*     */       
/* 218 */       tessellator.func_178180_c().func_181662_b(x - 2.0D, y + 3.0D, z + offset).func_181673_a(1.0D, 1.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 219 */       tessellator.func_178180_c().func_181662_b(x - 2.0D, y - 2.0D, z + offset).func_181673_a(1.0D, 0.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 220 */       tessellator.func_178180_c().func_181662_b(x + 3.0D, y - 2.0D, z + offset).func_181673_a(0.0D, 0.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 221 */       tessellator.func_178180_c().func_181662_b(x + 3.0D, y + 3.0D, z + offset).func_181673_a(0.0D, 1.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/*     */       
/* 223 */       tessellator.func_78381_a();
/* 224 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 227 */     GL11.glDisable(3042);
/* 228 */     GL11.glDisable(3168);
/* 229 */     GL11.glDisable(3169);
/* 230 */     GL11.glDisable(3170);
/* 231 */     GL11.glDisable(3171);
/* 232 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZNeg(double x, double y, double z, float f, int height)
/*     */   {
/* 237 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 238 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 239 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 240 */     GL11.glDisable(2896);
/* 241 */     Random random = new Random(31100L);
/* 242 */     float offset = 0.5F;
/* 243 */     if (this.inrange) {
/* 244 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 246 */         GL11.glPushMatrix();
/* 247 */         float f5 = 16 - i;
/* 248 */         float f6 = 0.0625F;
/* 249 */         float f7 = 1.0F / (f5 + 1.0F);
/* 250 */         if (i == 0)
/*     */         {
/* 252 */           func_147499_a(t1);
/* 253 */           f7 = 0.1F;
/* 254 */           f5 = 65.0F;
/* 255 */           f6 = 0.125F;
/* 256 */           GL11.glEnable(3042);
/* 257 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 259 */         if (i == 1)
/*     */         {
/* 261 */           func_147499_a(t2);
/* 262 */           GL11.glEnable(3042);
/* 263 */           GL11.glBlendFunc(1, 1);
/* 264 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 267 */         float f8 = (float)(z + offset);
/* 268 */         float f9 = (float)(f8 - ActiveRenderInfo.func_178804_a().field_72449_c);
/* 269 */         float f10 = (float)(f8 + f5 - ActiveRenderInfo.func_178804_a().field_72449_c);
/* 270 */         float f11 = f9 / f10;
/* 271 */         f11 = (float)(z + offset) + f11;
/* 272 */         GL11.glTranslatef(px, py, f11);
/* 273 */         GL11.glTexGeni(8192, 9472, 9217);
/* 274 */         GL11.glTexGeni(8193, 9472, 9217);
/* 275 */         GL11.glTexGeni(8194, 9472, 9217);
/* 276 */         GL11.glTexGeni(8195, 9472, 9216);
/* 277 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 278 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 279 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 281 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 282 */         GL11.glEnable(3168);
/* 283 */         GL11.glEnable(3169);
/* 284 */         GL11.glEnable(3170);
/* 285 */         GL11.glEnable(3171);
/* 286 */         GL11.glPopMatrix();
/* 287 */         GL11.glMatrixMode(5890);
/* 288 */         GL11.glPushMatrix();
/* 289 */         GL11.glLoadIdentity();
/* 290 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 291 */         GL11.glScalef(f6, f6, f6);
/* 292 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 293 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 294 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 295 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 298 */         GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72450_a * f5 / f9, ActiveRenderInfo.func_178804_a().field_72448_b * f5 / f9, -pz);
/*     */         
/* 300 */         Tessellator tessellator = Tessellator.func_178181_a();
/* 301 */         tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 302 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 303 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 304 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 305 */         if (i == 0)
/*     */         {
/* 307 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/*     */ 
/* 311 */         tessellator.func_178180_c().func_181662_b(x - 2.0D, y - 2.0D, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 312 */         tessellator.func_178180_c().func_181662_b(x - 2.0D, y + 3.0D, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 313 */         tessellator.func_178180_c().func_181662_b(x + 3.0D, y + 3.0D, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 314 */         tessellator.func_178180_c().func_181662_b(x + 3.0D, y - 2.0D, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */         
/* 316 */         tessellator.func_78381_a();
/* 317 */         GL11.glPopMatrix();
/* 318 */         GL11.glMatrixMode(5888);
/*     */       }
/* 320 */     } else { GL11.glPushMatrix();
/* 321 */       func_147499_a(t3);
/* 322 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 323 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181709_i);
/*     */       
/*     */ 
/* 326 */       tessellator.func_178180_c().func_181662_b(x - 2.0D, y - 2.0D, z + offset).func_181673_a(1.0D, 1.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 327 */       tessellator.func_178180_c().func_181662_b(x - 2.0D, y + 3.0D, z + offset).func_181673_a(1.0D, 0.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 328 */       tessellator.func_178180_c().func_181662_b(x + 3.0D, y + 3.0D, z + offset).func_181673_a(0.0D, 0.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 329 */       tessellator.func_178180_c().func_181662_b(x + 3.0D, y - 2.0D, z + offset).func_181673_a(0.0D, 1.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/*     */       
/* 331 */       tessellator.func_78381_a();
/* 332 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 335 */     GL11.glDisable(3042);
/* 336 */     GL11.glDisable(3168);
/* 337 */     GL11.glDisable(3169);
/* 338 */     GL11.glDisable(3170);
/* 339 */     GL11.glDisable(3171);
/* 340 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXPos(double x, double y, double z, float f, int height)
/*     */   {
/* 345 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 346 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 347 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 348 */     GL11.glDisable(2896);
/* 349 */     Random random = new Random(31100L);
/* 350 */     float offset = 0.5F;
/* 351 */     if (this.inrange) {
/* 352 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 354 */         GL11.glPushMatrix();
/* 355 */         float f5 = 16 - i;
/* 356 */         float f6 = 0.0625F;
/* 357 */         float f7 = 1.0F / (f5 + 1.0F);
/* 358 */         if (i == 0)
/*     */         {
/* 360 */           func_147499_a(t1);
/* 361 */           f7 = 0.1F;
/* 362 */           f5 = 65.0F;
/* 363 */           f6 = 0.125F;
/* 364 */           GL11.glEnable(3042);
/* 365 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 367 */         if (i == 1)
/*     */         {
/* 369 */           func_147499_a(t2);
/* 370 */           GL11.glEnable(3042);
/* 371 */           GL11.glBlendFunc(1, 1);
/* 372 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 375 */         float f8 = (float)-(x + offset);
/* 376 */         float f9 = (float)(f8 + ActiveRenderInfo.func_178804_a().field_72450_a);
/* 377 */         float f10 = (float)(f8 + f5 + ActiveRenderInfo.func_178804_a().field_72450_a);
/* 378 */         float f11 = f9 / f10;
/* 379 */         f11 = (float)(x + offset) + f11;
/* 380 */         GL11.glTranslatef(f11, py, pz);
/* 381 */         GL11.glTexGeni(8192, 9472, 9217);
/* 382 */         GL11.glTexGeni(8193, 9472, 9217);
/* 383 */         GL11.glTexGeni(8194, 9472, 9217);
/* 384 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 387 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 388 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 389 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 390 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 391 */         GL11.glEnable(3168);
/* 392 */         GL11.glEnable(3169);
/* 393 */         GL11.glEnable(3170);
/* 394 */         GL11.glEnable(3171);
/* 395 */         GL11.glPopMatrix();
/* 396 */         GL11.glMatrixMode(5890);
/* 397 */         GL11.glPushMatrix();
/* 398 */         GL11.glLoadIdentity();
/* 399 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 400 */         GL11.glScalef(f6, f6, f6);
/* 401 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 402 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 403 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 404 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 407 */         GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72449_c * f5 / f9, ActiveRenderInfo.func_178804_a().field_72448_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 410 */         Tessellator tessellator = Tessellator.func_178181_a();
/* 411 */         tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 412 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 413 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 414 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 415 */         if (i == 0)
/*     */         {
/* 417 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 420 */         tessellator.func_178180_c().func_181662_b(x + offset, y + 3.0D, z - 2.0D).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 421 */         tessellator.func_178180_c().func_181662_b(x + offset, y + 3.0D, z + 3.0D).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 422 */         tessellator.func_178180_c().func_181662_b(x + offset, y - 2.0D, z + 3.0D).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 423 */         tessellator.func_178180_c().func_181662_b(x + offset, y - 2.0D, z - 2.0D).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */         
/* 425 */         tessellator.func_78381_a();
/* 426 */         GL11.glPopMatrix();
/* 427 */         GL11.glMatrixMode(5888);
/*     */       }
/* 429 */     } else { GL11.glPushMatrix();
/* 430 */       func_147499_a(t3);
/* 431 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 432 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181709_i);
/*     */       
/*     */ 
/* 435 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 3.0D, z - 2.0D).func_181673_a(1.0D, 1.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 436 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 3.0D, z + 3.0D).func_181673_a(1.0D, 0.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 437 */       tessellator.func_178180_c().func_181662_b(x + offset, y - 2.0D, z + 3.0D).func_181673_a(0.0D, 0.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 438 */       tessellator.func_178180_c().func_181662_b(x + offset, y - 2.0D, z - 2.0D).func_181673_a(0.0D, 1.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/*     */       
/* 440 */       tessellator.func_78381_a();
/* 441 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 444 */     GL11.glDisable(3042);
/* 445 */     GL11.glDisable(3168);
/* 446 */     GL11.glDisable(3169);
/* 447 */     GL11.glDisable(3170);
/* 448 */     GL11.glDisable(3171);
/* 449 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXNeg(double x, double y, double z, float f, int height)
/*     */   {
/* 454 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 455 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 456 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 457 */     GL11.glDisable(2896);
/* 458 */     Random random = new Random(31100L);
/* 459 */     float offset = 0.5F;
/* 460 */     if (this.inrange) {
/* 461 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 463 */         GL11.glPushMatrix();
/* 464 */         float f5 = 16 - i;
/* 465 */         float f6 = 0.0625F;
/* 466 */         float f7 = 1.0F / (f5 + 1.0F);
/* 467 */         if (i == 0)
/*     */         {
/* 469 */           func_147499_a(t1);
/* 470 */           f7 = 0.1F;
/* 471 */           f5 = 65.0F;
/* 472 */           f6 = 0.125F;
/* 473 */           GL11.glEnable(3042);
/* 474 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 476 */         if (i == 1)
/*     */         {
/* 478 */           func_147499_a(t2);
/* 479 */           GL11.glEnable(3042);
/* 480 */           GL11.glBlendFunc(1, 1);
/* 481 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 484 */         float f8 = (float)(x + offset);
/* 485 */         float f9 = (float)(f8 - ActiveRenderInfo.func_178804_a().field_72450_a);
/* 486 */         float f10 = (float)(f8 + f5 - ActiveRenderInfo.func_178804_a().field_72450_a);
/* 487 */         float f11 = f9 / f10;
/* 488 */         f11 = (float)(x + offset) + f11;
/* 489 */         GL11.glTranslatef(f11, py, pz);
/* 490 */         GL11.glTexGeni(8192, 9472, 9217);
/* 491 */         GL11.glTexGeni(8193, 9472, 9217);
/* 492 */         GL11.glTexGeni(8194, 9472, 9217);
/* 493 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 496 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 497 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 498 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 499 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 500 */         GL11.glEnable(3168);
/* 501 */         GL11.glEnable(3169);
/* 502 */         GL11.glEnable(3170);
/* 503 */         GL11.glEnable(3171);
/* 504 */         GL11.glPopMatrix();
/* 505 */         GL11.glMatrixMode(5890);
/* 506 */         GL11.glPushMatrix();
/* 507 */         GL11.glLoadIdentity();
/* 508 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 509 */         GL11.glScalef(f6, f6, f6);
/* 510 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 511 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 512 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 513 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 516 */         GL11.glTranslated(ActiveRenderInfo.func_178804_a().field_72449_c * f5 / f9, ActiveRenderInfo.func_178804_a().field_72448_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 519 */         Tessellator tessellator = Tessellator.func_178181_a();
/* 520 */         tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 521 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 522 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 523 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 524 */         if (i == 0)
/*     */         {
/* 526 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/*     */ 
/* 530 */         tessellator.func_178180_c().func_181662_b(x + offset, y - 2.0D, z - 2.0D).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 531 */         tessellator.func_178180_c().func_181662_b(x + offset, y - 2.0D, z + 3.0D).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 532 */         tessellator.func_178180_c().func_181662_b(x + offset, y + 3.0D, z + 3.0D).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 533 */         tessellator.func_178180_c().func_181662_b(x + offset, y + 3.0D, z - 2.0D).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */         
/* 535 */         tessellator.func_78381_a();
/* 536 */         GL11.glPopMatrix();
/* 537 */         GL11.glMatrixMode(5888);
/*     */       }
/* 539 */     } else { GL11.glPushMatrix();
/* 540 */       func_147499_a(t3);
/* 541 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 542 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181709_i);
/*     */       
/*     */ 
/* 545 */       tessellator.func_178180_c().func_181662_b(x + offset, y - 2.0D, z - 2.0D).func_181673_a(1.0D, 1.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 546 */       tessellator.func_178180_c().func_181662_b(x + offset, y - 2.0D, z + 3.0D).func_181673_a(1.0D, 0.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 547 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 3.0D, z + 3.0D).func_181673_a(0.0D, 0.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/* 548 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 3.0D, z - 2.0D).func_181673_a(0.0D, 1.0D).func_181666_a(0.5F, 0.5F, 0.5F, 1.0F).func_181675_d();
/*     */       
/* 550 */       tessellator.func_78381_a();
/* 551 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 554 */     GL11.glDisable(3042);
/* 555 */     GL11.glDisable(3168);
/* 556 */     GL11.glDisable(3169);
/* 557 */     GL11.glDisable(3170);
/* 558 */     GL11.glDisable(3171);
/* 559 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3)
/*     */   {
/* 564 */     this.fBuffer.clear();
/* 565 */     this.fBuffer.put(f).put(f1).put(f2).put(f3);
/* 566 */     this.fBuffer.flip();
/* 567 */     return this.fBuffer;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/tile/TileEldritchLockRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */