/*     */ package thaumcraft.client.lib.models;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.block.model.ModelBlock;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelBakery;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import net.minecraft.util.IRegistry;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.ModelBakeEvent;
/*     */ import net.minecraftforge.client.event.TextureStitchEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventBus;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import thaumcraft.client.renderers.models.gear.CustomWandMeshModel;
/*     */ 
/*     */ public class ModelRegistrationHelper
/*     */ {
/*     */   protected List<ResourceLocation> itemTextures;
/*     */   protected List<ResourceLocation> blockTextures;
/*     */   protected Map<ResourceLocation, IBakedModel> itemsToInject;
/*     */   protected Map<ResourceLocation, IBakedModel> blocksToInject;
/*     */   
/*     */   public ModelRegistrationHelper()
/*     */   {
/*  39 */     this.itemTextures = new ArrayList();
/*  40 */     this.blockTextures = new ArrayList();
/*  41 */     this.itemsToInject = new Hashtable();
/*  42 */     this.blocksToInject = new Hashtable();
/*  43 */     MinecraftForge.EVENT_BUS.register(this);
/*     */   }
/*     */   
/*     */   public void registerCustomItemModel(ResourceLocation resourceLocation, IBakedModel bakedModel, String itemName) {
/*  47 */     this.itemsToInject.put(resourceLocation, bakedModel);
/*     */   }
/*     */   
/*     */   public void registerCustomBlockModel(ResourceLocation resourceLocation, IBakedModel bakedModel, String blockName) {
/*  51 */     this.blocksToInject.put(resourceLocation, bakedModel);
/*     */   }
/*     */   
/*     */   public void registerItemSprite(ResourceLocation resourceLocation) {
/*  55 */     if (!this.itemTextures.contains(resourceLocation))
/*  56 */       this.itemTextures.add(resourceLocation);
/*     */   }
/*     */   
/*     */   public void registerBlockSprite(ResourceLocation resourceLocation) {
/*  60 */     if (!this.blockTextures.contains(resourceLocation))
/*  61 */       this.blockTextures.add(resourceLocation);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTextureStitch(TextureStitchEvent event) {
/*  66 */     if (event.map == Minecraft.func_71410_x().func_147117_R()) {
/*  67 */       registerSprites(event.map, this.blockTextures);
/*     */     } else {
/*  69 */       registerSprites(event.map, this.itemTextures);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onModelBake(ModelBakeEvent event) {
/*  75 */     CustomWandMeshModel.cache.clear();
/*  76 */     registerCustomBakedModels(event.modelManager, event.modelRegistry, event.modelBakery);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void registerCustomBakedModels(ModelManager modelManager, IRegistry modelRegistry, ModelBakery modelBakery)
/*     */   {
/*  84 */     for (Map.Entry<ResourceLocation, IBakedModel> entry : this.itemsToInject.entrySet())
/*     */     {
/*  86 */       ResourceLocation loc = (ResourceLocation)entry.getKey();
/*  87 */       IBakedModel model = (IBakedModel)entry.getValue();
/*     */       
/*  89 */       if ((model instanceof IInitializeBakedModel))
/*     */       {
/*  91 */         IInitializeBakedModel initializeModel = (IInitializeBakedModel)model;
/*     */         
/*  93 */         ItemCameraTransforms transforms = ItemCameraTransforms.field_178357_a;
/*     */         
/*  95 */         ResourceLocation icon = new ResourceLocation(loc.func_110624_b(), "item/" + loc.func_110623_a());
/*  96 */         if ((loc.func_110623_a().contains("wand")) || (loc.func_110623_a().contains("focus")) || (loc.func_110623_a().contains("sceptre")))
/*     */         {
/*     */ 
/*  99 */           icon = new ResourceLocation(loc.func_110624_b(), "item/wand");
/*     */         }
/* 101 */         else if (loc.func_110623_a().contains("staff")) {
/* 102 */           icon = new ResourceLocation(loc.func_110624_b(), "item/staff");
/*     */         }
/* 104 */         ModelBlock modelblock = loadModelResource(icon);
/* 105 */         if (modelblock != null) {
/* 106 */           transforms = new ItemCameraTransforms(modelblock.func_181682_g());
/*     */         }
/*     */         
/* 109 */         initializeModel.initialize(transforms, icon, modelManager);
/*     */       }
/*     */       
/* 112 */       modelRegistry.func_82595_a(loc, model);
/*     */     }
/*     */     
/* 115 */     for (Map.Entry<ResourceLocation, IBakedModel> entry : this.blocksToInject.entrySet())
/*     */     {
/* 117 */       ResourceLocation loc = (ResourceLocation)entry.getKey();
/* 118 */       IBakedModel model = (IBakedModel)entry.getValue();
/*     */       
/* 120 */       if ((model instanceof IInitializeBakedModel)) {
/* 121 */         IInitializeBakedModel initializeModel = (IInitializeBakedModel)model;
/*     */         
/* 123 */         ItemCameraTransforms transforms = ItemCameraTransforms.field_178357_a;
/*     */         
/* 125 */         ResourceLocation icon = new ResourceLocation(loc.func_110624_b(), "block/" + loc.func_110623_a());
/* 126 */         ModelBlock modelblock = loadModelResource(icon);
/* 127 */         if (modelblock != null) {
/* 128 */           transforms = new ItemCameraTransforms(modelblock.func_181682_g());
/*     */         }
/*     */         
/* 131 */         initializeModel.initialize(transforms, icon, modelManager);
/*     */       }
/*     */       
/* 134 */       modelRegistry.func_82595_a(loc, model);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void registerSprites(TextureMap map, List<ResourceLocation> texturesToRegister) {
/* 139 */     for (ResourceLocation loc : texturesToRegister) {
/* 140 */       map.func_174942_a(loc);
/*     */     }
/*     */   }
/*     */   
/*     */   protected ResourceLocation getModelLocation(ResourceLocation loc) {
/* 145 */     return new ResourceLocation(loc.func_110624_b(), "models/" + loc.func_110623_a() + ".json");
/*     */   }
/*     */   
/*     */   protected ModelBlock loadModelResource(Map<ResourceLocation, ModelBlock> map, ResourceLocation loc) {
/* 149 */     Reader reader = null;
/* 150 */     ModelBlock modelblock = (ModelBlock)map.get(loc);
/*     */     
/* 152 */     if (modelblock != null) {
/* 153 */       return modelblock;
/*     */     }
/* 155 */     if (loc.func_110623_a().startsWith("builtin/")) {
/* 156 */       return null;
/*     */     }
/*     */     try {
/* 159 */       IResource iresource = Minecraft.func_71410_x().func_110442_L().func_110536_a(getModelLocation(loc));
/* 160 */       if (iresource != null) {
/* 161 */         reader = new InputStreamReader(iresource.func_110527_b(), com.google.common.base.Charsets.UTF_8);
/*     */         
/* 163 */         modelblock = ModelBlock.func_178307_a(reader);
/* 164 */         modelblock.field_178317_b = loc.toString();
/* 165 */         map.put(loc, modelblock);
/*     */         
/* 167 */         ResourceLocation parentLoc = modelblock.func_178305_e();
/* 168 */         if (parentLoc != null)
/*     */         {
/* 170 */           ModelBlock parentModel = loadModelResource(map, parentLoc);
/* 171 */           if (parentModel != null) {
/* 172 */             modelblock.func_178299_a(map);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (IOException e) {
/* 177 */       e.printStackTrace();
/*     */     }
/*     */     
/* 180 */     return modelblock;
/*     */   }
/*     */   
/*     */   public ModelBlock loadModelResource(ResourceLocation loc) {
/* 184 */     return loadModelResource(new Hashtable(), loc);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/ModelRegistrationHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */