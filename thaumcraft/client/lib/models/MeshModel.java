/*     */ package thaumcraft.client.lib.models;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector2f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.FaceBakery;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import thaumcraft.codechicken.lib.vec.Rotation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ public class MeshModel
/*     */ {
/*     */   public List<Vector3f> positions;
/*     */   public List<Vector3f> normals;
/*     */   public List<Vector2f> texCoords;
/*     */   public List<MeshPart> parts;
/*     */   
/*     */   public MeshModel()
/*     */   {
/*  25 */     this.parts = new ArrayList();
/*     */   }
/*     */   
/*     */   public MeshModel clone() {
/*  29 */     MeshModel mm = new MeshModel();
/*  30 */     mm.parts = new ArrayList();
/*     */     MeshPart mp;
/*  32 */     for (Iterator i$ = this.parts.iterator(); i$.hasNext(); mm.parts.add(mp)) mp = (MeshPart)i$.next();
/*  33 */     if (this.positions != null) {
/*  34 */       mm.positions = new ArrayList();
/*  35 */       Vector3f mp; for (Iterator i$ = this.positions.iterator(); i$.hasNext(); mm.positions.add((Vector3f)mp.clone())) mp = (Vector3f)i$.next();
/*     */     }
/*  37 */     if (this.normals != null) {
/*  38 */       mm.normals = new ArrayList();
/*  39 */       Vector3f mp; for (Iterator i$ = this.normals.iterator(); i$.hasNext(); mm.normals.add((Vector3f)mp.clone())) mp = (Vector3f)i$.next();
/*     */     }
/*  41 */     if (this.texCoords != null) {
/*  42 */       mm.texCoords = new ArrayList();
/*  43 */       Vector2f mp; for (Iterator i$ = this.texCoords.iterator(); i$.hasNext(); mm.texCoords.add((Vector2f)mp.clone())) mp = (Vector2f)i$.next();
/*     */     }
/*  45 */     return mm;
/*     */   }
/*     */   
/*     */   public void rotate(double d, Vector3 axis, Vector3 offset) {
/*  49 */     Rotation r = new Rotation(d, axis);
/*  50 */     List<Vector3f> p = new ArrayList();
/*  51 */     for (Vector3f v : this.positions) {
/*  52 */       Vector3 vec = new Vector3(v.x, v.y, v.z);
/*  53 */       r.apply(vec);
/*  54 */       vec = vec.add(offset);
/*  55 */       p.add(new Vector3f((float)vec.x, (float)vec.y, (float)vec.z));
/*     */     }
/*  57 */     this.positions = p;
/*     */   }
/*     */   
/*     */   public void addPosition(float x, float y, float z) {
/*  61 */     if (this.positions == null)
/*  62 */       this.positions = new ArrayList();
/*  63 */     this.positions.add(new Vector3f(x, y, z));
/*     */   }
/*     */   
/*     */   public void addNormal(float x, float y, float z) {
/*  67 */     if (this.normals == null)
/*  68 */       this.normals = new ArrayList();
/*  69 */     this.normals.add(new Vector3f(x, y, z));
/*     */   }
/*     */   
/*     */   public void addTexCoords(float x, float y) {
/*  73 */     if (this.texCoords == null)
/*  74 */       this.texCoords = new ArrayList();
/*  75 */     this.texCoords.add(new Vector2f(x, y));
/*     */   }
/*     */   
/*     */   public void addPart(MeshPart part) {
/*  79 */     this.parts.add(part);
/*     */   }
/*     */   
/*     */   public void addPart(MeshPart part, int ti) {
/*  83 */     this.parts.add(new MeshPart(part, ti));
/*     */   }
/*     */   
/*     */   private int getColorValue(Vector3f color) {
/*  87 */     int r = (int)color.x;
/*  88 */     int g = (int)color.y;
/*  89 */     int b = (int)color.z;
/*  90 */     return 0xFF000000 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public List<BakedQuad> bakeModel(ModelManager manager) {
/*  94 */     List<BakedQuad> bakeList = new ArrayList();
/*     */     
/*  96 */     for (int j = 0; j < this.parts.size(); j++)
/*     */     {
/*  98 */       MeshPart part = (MeshPart)this.parts.get(j);
/*     */       
/* 100 */       TextureAtlasSprite sprite = null;
/* 101 */       int color = -1;
/*     */       
/* 103 */       if (part.material != null) {
/* 104 */         if (part.material.DiffuseTextureMap != null) {
/* 105 */           sprite = manager.func_174952_b().func_110572_b(part.material.DiffuseTextureMap);
/* 106 */         } else if (part.material.AmbientTextureMap != null) {
/* 107 */           sprite = manager.func_174952_b().func_110572_b(part.material.AmbientTextureMap);
/*     */         }
/* 109 */         if (part.material.DiffuseColor != null) {
/* 110 */           color = getColorValue(part.material.DiffuseColor);
/*     */         }
/*     */       }
/*     */       
/* 114 */       for (int i = 0; i < part.indices.size(); i += 4) {
/* 115 */         BakedQuad quad = bakeQuad(part, i, sprite, color);
/* 116 */         bakeList.add(quad);
/*     */       }
/*     */     }
/* 119 */     return bakeList;
/*     */   }
/*     */   
/*     */   public List<BakedQuad> bakeModel(TextureAtlasSprite sprite) {
/* 123 */     List<BakedQuad> bakeList = new ArrayList();
/*     */     
/* 125 */     for (int j = 0; j < this.parts.size(); j++)
/*     */     {
/* 127 */       MeshPart part = (MeshPart)this.parts.get(j);
/*     */       
/*     */ 
/* 130 */       int color = -1;
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
/* 143 */       for (int i = 0; i < part.indices.size(); i += 4) {
/* 144 */         BakedQuad quad = bakeQuad(part, i, sprite, color);
/* 145 */         bakeList.add(quad);
/*     */       }
/*     */     }
/* 148 */     return bakeList;
/*     */   }
/*     */   
/*     */   private BakedQuad bakeQuad(MeshPart part, int startIndex, TextureAtlasSprite sprite, int color) {
/* 152 */     int[] faceData = new int[28];
/* 153 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 155 */       Vector3f position = new Vector3f(0.0F, 0.0F, 0.0F);
/* 156 */       Vector2f texCoord = new Vector2f(0.0F, 0.0F);
/* 157 */       int p = 0;
/* 158 */       int[] indices = (int[])part.indices.get(startIndex + i);
/*     */       
/* 160 */       if (this.positions != null) {
/* 161 */         position = (Vector3f)this.positions.get(indices[(p++)]);
/*     */       }
/* 163 */       if (this.normals != null) {
/* 164 */         p++;
/*     */       }
/* 166 */       if (this.texCoords != null) {
/* 167 */         texCoord = (Vector2f)this.texCoords.get(indices[(p++)]);
/*     */       }
/* 169 */       storeVertexData(faceData, i, position, texCoord, sprite, color);
/*     */     }
/* 171 */     return new BakedQuad(faceData, part.name.contains("focus") ? 1 : part.tintIndex, FaceBakery.func_178410_a(faceData));
/*     */   }
/*     */   
/*     */   private static void storeVertexData(int[] faceData, int storeIndex, Vector3f position, Vector2f faceUV, TextureAtlasSprite sprite, int shadeColor) {
/* 175 */     int l = storeIndex * 7;
/* 176 */     faceData[(l + 0)] = Float.floatToRawIntBits(position.x);
/* 177 */     faceData[(l + 1)] = Float.floatToRawIntBits(position.y);
/* 178 */     faceData[(l + 2)] = Float.floatToRawIntBits(position.z);
/* 179 */     faceData[(l + 3)] = shadeColor;
/* 180 */     if (sprite != null) {
/* 181 */       faceData[(l + 4)] = Float.floatToRawIntBits(sprite.func_94214_a(faceUV.x * 16.0F));
/* 182 */       faceData[(l + 5)] = Float.floatToRawIntBits(sprite.func_94207_b(faceUV.y * 16.0F));
/*     */     } else {
/* 184 */       faceData[(l + 4)] = Float.floatToRawIntBits(faceUV.x);
/* 185 */       faceData[(l + 5)] = Float.floatToRawIntBits(faceUV.y);
/*     */     }
/* 187 */     faceData[(l + 6)] = 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/MeshModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */