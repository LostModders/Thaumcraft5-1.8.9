/*     */ package thaumcraft.client.lib.models;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ class MaterialLibrary
/*     */   extends Dictionary<String, Material>
/*     */ {
/*  24 */   static final Set<String> unknownCommands = new HashSet();
/*     */   
/*  26 */   private final Dictionary<String, Material> materialLibrary = new Hashtable();
/*     */   
/*     */ 
/*     */   private Material currentMaterial;
/*     */   
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  35 */     return this.materialLibrary.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty()
/*     */   {
/*  40 */     return this.materialLibrary.isEmpty();
/*     */   }
/*     */   
/*     */   public Enumeration<String> keys()
/*     */   {
/*  45 */     return this.materialLibrary.keys();
/*     */   }
/*     */   
/*     */   public Enumeration<Material> elements()
/*     */   {
/*  50 */     return this.materialLibrary.elements();
/*     */   }
/*     */   
/*     */   public Material get(Object key)
/*     */   {
/*  55 */     return (Material)this.materialLibrary.get(key);
/*     */   }
/*     */   
/*     */   public Material put(String key, Material value)
/*     */   {
/*  60 */     return (Material)this.materialLibrary.put(key, value);
/*     */   }
/*     */   
/*     */   public Material remove(Object key)
/*     */   {
/*  65 */     return (Material)this.materialLibrary.remove(key);
/*     */   }
/*     */   
/*     */   public void loadFromStream(ResourceLocation loc) throws IOException
/*     */   {
/*  70 */     IResource res = Minecraft.func_71410_x().func_110442_L().func_110536_a(loc);
/*  71 */     InputStreamReader lineStream = new InputStreamReader(res.func_110527_b(), Charsets.UTF_8);
/*  72 */     BufferedReader lineReader = new BufferedReader(lineStream);
/*     */     for (;;)
/*     */     {
/*  75 */       String currentLine = lineReader.readLine();
/*  76 */       if (currentLine == null) {
/*     */         break;
/*     */       }
/*  79 */       if ((currentLine.length() != 0) && (!currentLine.startsWith("#")))
/*     */       {
/*     */ 
/*     */ 
/*  83 */         String[] fields = currentLine.split(" ", 2);
/*  84 */         String keyword = fields[0];
/*  85 */         String data = fields[1];
/*     */         
/*  87 */         if (keyword.equalsIgnoreCase("newmtl")) {
/*  88 */           pushMaterial(data);
/*  89 */         } else if (keyword.equalsIgnoreCase("Ka")) {
/*  90 */           this.currentMaterial.AmbientColor = parseVector3f(data);
/*  91 */         } else if (keyword.equalsIgnoreCase("Kd")) {
/*  92 */           this.currentMaterial.DiffuseColor = parseVector3f(data);
/*  93 */         } else if (keyword.equalsIgnoreCase("Ks")) {
/*  94 */           this.currentMaterial.SpecularColor = parseVector3f(data);
/*  95 */         } else if (keyword.equalsIgnoreCase("Ns")) {
/*  96 */           this.currentMaterial.SpecularCoefficient = parseFloat(data);
/*  97 */         } else if (keyword.equalsIgnoreCase("Tr")) {
/*  98 */           this.currentMaterial.Transparency = parseFloat(data);
/*  99 */         } else if (keyword.equalsIgnoreCase("illum")) {
/* 100 */           this.currentMaterial.IlluminationModel = parseInt(data);
/* 101 */         } else if (keyword.equalsIgnoreCase("map_Ka")) {
/* 102 */           this.currentMaterial.AmbientTextureMap = data;
/* 103 */           ResourceLocation texture = new ResourceLocation(data);
/* 104 */           if (!texture.func_110623_a().contains("item")) {
/* 105 */             Thaumcraft.modelRegistrationHelper.registerItemSprite(texture);
/*     */           } else
/* 107 */             Thaumcraft.modelRegistrationHelper.registerBlockSprite(texture);
/* 108 */         } else if (keyword.equalsIgnoreCase("map_Kd")) {
/* 109 */           this.currentMaterial.DiffuseTextureMap = data;
/* 110 */           ResourceLocation texture = new ResourceLocation(data);
/* 111 */           if (!texture.func_110623_a().contains("item")) {
/* 112 */             Thaumcraft.modelRegistrationHelper.registerItemSprite(texture);
/*     */           } else
/* 114 */             Thaumcraft.modelRegistrationHelper.registerBlockSprite(texture);
/* 115 */         } else if (keyword.equalsIgnoreCase("map_Ks")) {
/* 116 */           this.currentMaterial.SpecularTextureMap = data;
/* 117 */         } else if (keyword.equalsIgnoreCase("map_Ns")) {
/* 118 */           this.currentMaterial.SpecularHighlightTextureMap = data;
/* 119 */         } else if (keyword.equalsIgnoreCase("map_d")) {
/* 120 */           this.currentMaterial.AlphaTextureMap = data;
/* 121 */         } else if (keyword.equalsIgnoreCase("map_bump")) {
/* 122 */           this.currentMaterial.BumpMap = data;
/* 123 */         } else if (keyword.equalsIgnoreCase("bump")) {
/* 124 */           this.currentMaterial.BumpMap = data;
/* 125 */         } else if (keyword.equalsIgnoreCase("disp")) {
/* 126 */           this.currentMaterial.DisplacementMap = data;
/* 127 */         } else if (keyword.equalsIgnoreCase("decal")) {
/* 128 */           this.currentMaterial.StencilDecalMap = data;
/*     */         }
/* 130 */         else if (!unknownCommands.contains(keyword)) {
/* 131 */           unknownCommands.add(keyword);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private float parseFloat(String data)
/*     */   {
/* 139 */     return Float.parseFloat(data);
/*     */   }
/*     */   
/*     */   private int parseInt(String data) {
/* 143 */     return Integer.parseInt(data);
/*     */   }
/*     */   
/*     */   private Vector3f parseVector3f(String data) {
/* 147 */     String[] parts = data.split(" ");
/* 148 */     return new Vector3f(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void pushMaterial(String materialName)
/*     */   {
/* 156 */     this.currentMaterial = new Material(materialName);
/* 157 */     this.materialLibrary.put(this.currentMaterial.Name, this.currentMaterial);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/MaterialLibrary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */