/*     */ package thaumcraft.codechicken.lib.render;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import thaumcraft.codechicken.lib.colour.ColourRGBA;
/*     */ import thaumcraft.codechicken.lib.lighting.LC;
/*     */ import thaumcraft.codechicken.lib.lighting.LightMatrix;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Transformation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CCRenderState
/*     */ {
/*     */   private static int nextOperationIndex;
/*     */   
/*     */   public static int registerOperation()
/*     */   {
/*  29 */     return nextOperationIndex++;
/*     */   }
/*     */   
/*     */   public static int operationCount() {
/*  33 */     return nextOperationIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  59 */   private static ArrayList<VertexAttribute<?>> vertexAttributes = new ArrayList();
/*     */   
/*  61 */   private static int registerVertexAttribute(VertexAttribute<?> attr) { vertexAttributes.add(attr);
/*  62 */     return vertexAttributes.size() - 1;
/*     */   }
/*     */   
/*     */ 
/*  66 */   public static VertexAttribute<?> getAttribute(int index) { return (VertexAttribute)vertexAttributes.get(index); }
/*     */   
/*     */   public static abstract interface IVertexSource { public abstract Vertex5[] getVertices();
/*     */     
/*     */     public abstract <T> T getAttributes(CCRenderState.VertexAttribute<T> paramVertexAttribute);
/*     */     
/*     */     public abstract boolean hasAttribute(CCRenderState.VertexAttribute<?> paramVertexAttribute);
/*     */     
/*     */     public abstract void prepareVertex(); }
/*     */   
/*  76 */   public static abstract class VertexAttribute<T> implements CCRenderState.IVertexOperation { public final int attributeIndex = CCRenderState.registerVertexAttribute(this);
/*  77 */     private final int operationIndex = CCRenderState.registerOperation();
/*     */     
/*     */ 
/*     */ 
/*  81 */     public boolean active = false;
/*     */     
/*     */ 
/*     */ 
/*     */     public abstract T newArray(int paramInt);
/*     */     
/*     */ 
/*     */     public int operationID()
/*     */     {
/*  90 */       return this.operationIndex;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void arrayCopy(Object src, int srcPos, Object dst, int destPos, int length) {
/*  95 */     System.arraycopy(src, srcPos, dst, destPos, length);
/*  96 */     if ((dst instanceof Copyable[])) {
/*  97 */       Object[] oa = (Object[])dst;
/*  98 */       Copyable<Object>[] c = (Copyable[])dst;
/*  99 */       for (int i = destPos; i < destPos + length; i++)
/* 100 */         if (c[i] != null)
/* 101 */           oa[i] = c[i].copy();
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T> T copyOf(VertexAttribute<T> attr, T src, int length) {
/* 106 */     T dst = attr.newArray(length);
/* 107 */     arrayCopy(src, 0, dst, 0, ((Object[])src).length);
/* 108 */     return dst;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 134 */   public static VertexAttribute<Vector3[]> normalAttrib = new VertexAttribute()
/*     */   {
/*     */     private Vector3[] normalRef;
/*     */     
/*     */     public Vector3[] newArray(int length) {
/* 139 */       return new Vector3[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 144 */       this.normalRef = ((Vector3[])CCRenderState.model.getAttributes(this));
/* 145 */       if (CCRenderState.model.hasAttribute(this)) {
/* 146 */         return this.normalRef != null;
/*     */       }
/* 148 */       if (CCRenderState.model.hasAttribute(CCRenderState.sideAttrib)) {
/* 149 */         CCRenderState.pipeline.addDependency(CCRenderState.sideAttrib);
/* 150 */         return true;
/*     */       }
/* 152 */       throw new IllegalStateException("Normals requested but neither normal or side attrutes are provided by the model");
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 157 */       if (this.normalRef != null) {
/* 158 */         CCRenderState.setNormal(this.normalRef[CCRenderState.vertexIndex]);
/*     */       } else
/* 160 */         CCRenderState.setNormal(thaumcraft.codechicken.lib.vec.Rotation.axes[CCRenderState.side]);
/*     */     }
/*     */   };
/* 163 */   public static VertexAttribute<int[]> colourAttrib = new VertexAttribute()
/*     */   {
/*     */     private int[] colourRef;
/*     */     
/*     */     public int[] newArray(int length) {
/* 168 */       return new int[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 173 */       this.colourRef = ((int[])CCRenderState.model.getAttributes(this));
/* 174 */       return (this.colourRef != null) || (!CCRenderState.model.hasAttribute(this));
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 179 */       if (this.colourRef != null) {
/* 180 */         CCRenderState.setColour(ColourRGBA.multiply(CCRenderState.baseColour, this.colourRef[CCRenderState.vertexIndex]));
/*     */       } else
/* 182 */         CCRenderState.setColour(CCRenderState.baseColour);
/*     */     }
/*     */   };
/* 185 */   public static VertexAttribute<int[]> lightingAttrib = new VertexAttribute()
/*     */   {
/*     */     private int[] colourRef;
/*     */     
/*     */     public int[] newArray(int length) {
/* 190 */       return new int[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 195 */       if ((!CCRenderState.computeLighting) || (!CCRenderState.useColour) || (!CCRenderState.model.hasAttribute(this))) {
/* 196 */         return false;
/*     */       }
/* 198 */       this.colourRef = ((int[])CCRenderState.model.getAttributes(this));
/* 199 */       if (this.colourRef != null) {
/* 200 */         CCRenderState.pipeline.addDependency(CCRenderState.colourAttrib);
/* 201 */         return true;
/*     */       }
/* 203 */       return false;
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 208 */       CCRenderState.setColour(ColourRGBA.multiply(CCRenderState.colour, this.colourRef[CCRenderState.vertexIndex]));
/*     */     }
/*     */   };
/* 211 */   public static VertexAttribute<int[]> sideAttrib = new VertexAttribute()
/*     */   {
/*     */     private int[] sideRef;
/*     */     
/*     */     public int[] newArray(int length) {
/* 216 */       return new int[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 221 */       this.sideRef = ((int[])CCRenderState.model.getAttributes(this));
/* 222 */       if (CCRenderState.model.hasAttribute(this)) {
/* 223 */         return this.sideRef != null;
/*     */       }
/* 225 */       CCRenderState.pipeline.addDependency(CCRenderState.normalAttrib);
/* 226 */       return true;
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 231 */       if (this.sideRef != null) {
/* 232 */         CCRenderState.side = this.sideRef[CCRenderState.vertexIndex];
/*     */       } else {
/* 234 */         CCRenderState.side = CCModel.findSide(CCRenderState.normal);
/*     */       }
/*     */     }
/*     */   };
/*     */   
/*     */ 
/* 240 */   public static VertexAttribute<LC[]> lightCoordAttrib = new VertexAttribute() {
/*     */     private LC[] lcRef;
/* 242 */     private Vector3 vec = new Vector3();
/* 243 */     private Vector3 pos = new Vector3();
/*     */     
/*     */     public LC[] newArray(int length)
/*     */     {
/* 247 */       return new LC[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 252 */       this.lcRef = ((LC[])CCRenderState.model.getAttributes(this));
/* 253 */       if (CCRenderState.model.hasAttribute(this)) {
/* 254 */         return this.lcRef != null;
/*     */       }
/* 256 */       this.pos.set(CCRenderState.lightMatrix.pos.x, CCRenderState.lightMatrix.pos.y, CCRenderState.lightMatrix.pos.z);
/* 257 */       CCRenderState.pipeline.addDependency(CCRenderState.sideAttrib);
/* 258 */       CCRenderState.pipeline.addRequirement(Transformation.operationIndex);
/* 259 */       return true;
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 264 */       if (this.lcRef != null) {
/* 265 */         CCRenderState.lc.set(this.lcRef[CCRenderState.vertexIndex]);
/*     */       } else {
/* 267 */         CCRenderState.lc.compute(this.vec.set(CCRenderState.vert.vec).sub(this.pos), CCRenderState.side);
/*     */       }
/*     */     }
/*     */   };
/*     */   
/*     */   public static IVertexSource model;
/*     */   public static int firstVertexIndex;
/*     */   public static int lastVertexIndex;
/*     */   public static int vertexIndex;
/* 276 */   public static CCRenderPipeline pipeline = new CCRenderPipeline();
/*     */   
/*     */   public static int baseColour;
/*     */   
/*     */   public static int alphaOverride;
/*     */   public static boolean useNormals;
/*     */   public static boolean computeLighting;
/*     */   public static boolean useColour;
/* 284 */   public static LightMatrix lightMatrix = new LightMatrix();
/*     */   
/*     */ 
/* 287 */   public static Vertex5 vert = new Vertex5();
/*     */   public static boolean hasNormal;
/* 289 */   public static Vector3 normal = new Vector3();
/*     */   
/*     */   public static boolean hasColour;
/*     */   
/*     */   public static int colour;
/*     */   public static boolean hasBrightness;
/*     */   public static int brightness;
/*     */   public static int side;
/* 297 */   public static LC lc = new LC();
/*     */   
/*     */   public static void reset() {
/* 300 */     model = null;
/* 301 */     pipeline.reset();
/* 302 */     useNormals = hasNormal = hasBrightness = hasColour = 0;
/* 303 */     useColour = computeLighting = 1;
/* 304 */     baseColour = alphaOverride = -1;
/*     */   }
/*     */   
/*     */   public static void setPipeline(IVertexOperation... ops) {
/* 308 */     pipeline.setPipeline(ops);
/*     */   }
/*     */   
/*     */   public static void setPipeline(IVertexSource model, int start, int end, IVertexOperation... ops) {
/* 312 */     pipeline.reset();
/* 313 */     setModel(model, start, end);
/* 314 */     pipeline.setPipeline(ops);
/*     */   }
/*     */   
/*     */   public static void bindModel(IVertexSource model) {
/* 318 */     if (model != model) {
/* 319 */       model = model;
/* 320 */       pipeline.rebuild();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setModel(IVertexSource source) {
/* 325 */     setModel(source, 0, source.getVertices().length);
/*     */   }
/*     */   
/*     */   public static void setModel(IVertexSource source, int start, int end) {
/* 329 */     bindModel(source);
/* 330 */     setVertexRange(start, end);
/*     */   }
/*     */   
/*     */   public static void setVertexRange(int start, int end) {
/* 334 */     firstVertexIndex = start;
/* 335 */     lastVertexIndex = end;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setNormal(double x, double y, double z)
/*     */   {
/* 369 */     hasNormal = true;
/* 370 */     normal.set(x, y, z);
/*     */   }
/*     */   
/*     */   public static void setNormal(Vector3 n) {
/* 374 */     hasNormal = true;
/* 375 */     normal.set(n);
/*     */   }
/*     */   
/*     */   public static void setColour(int c) {
/* 379 */     hasColour = true;
/* 380 */     colour = c;
/*     */   }
/*     */   
/*     */   public static void setBrightness(int b) {
/* 384 */     hasBrightness = true;
/* 385 */     brightness = b;
/*     */   }
/*     */   
/*     */   public static void setBrightness(IBlockAccess world, BlockPos pos) {
/* 389 */     setBrightness(world.func_180495_p(pos).func_177230_c().func_176207_c(world, pos));
/*     */   }
/*     */   
/*     */   public static void pullLightmap() {
/* 393 */     setBrightness((int)OpenGlHelper.lastBrightnessY << 16 | (int)OpenGlHelper.lastBrightnessX);
/*     */   }
/*     */   
/*     */   public static void pushLightmap() {
/* 397 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, brightness & 0xFFFF, brightness >>> 16);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void setDynamic()
/*     */   {
/* 404 */     useNormals = true;
/* 405 */     computeLighting = false;
/*     */   }
/*     */   
/*     */   public static void changeTexture(String texture) {
/* 409 */     changeTexture(new ResourceLocation(texture));
/*     */   }
/*     */   
/*     */   public static void changeTexture(ResourceLocation texture) {
/* 413 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void draw()
/*     */   {
/* 431 */     Tessellator.func_178181_a().func_78381_a();
/*     */   }
/*     */   
/*     */   public static abstract interface IVertexOperation
/*     */   {
/*     */     public abstract boolean load();
/*     */     
/*     */     public abstract void operate();
/*     */     
/*     */     public abstract int operationID();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/render/CCRenderState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */