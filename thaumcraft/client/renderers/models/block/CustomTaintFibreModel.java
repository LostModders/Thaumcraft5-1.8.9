/*     */ package thaumcraft.client.renderers.models.block;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelRotation;
/*     */ import net.minecraft.client.resources.model.SimpleBakedModel.Builder;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.Attributes;
/*     */ import net.minecraftforge.client.model.IFlexibleBakedModel;
/*     */ import net.minecraftforge.client.model.IModel;
/*     */ import net.minecraftforge.client.model.IModelState;
/*     */ import net.minecraftforge.client.model.ModelLoaderRegistry;
/*     */ import net.minecraftforge.common.property.IExtendedBlockState;
/*     */ import thaumcraft.client.lib.models.block.SmartBlockModel;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintFibre;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomTaintFibreModel
/*     */   implements IModel
/*     */ {
/*  34 */   static final ResourceLocation TEXTURE1 = new ResourceLocation("Thaumcraft:blocks/taint_fibres");
/*  35 */   static final ResourceLocation TEXTURE2 = new ResourceLocation("Thaumcraft:blocks/taint_growth_1");
/*  36 */   static final ResourceLocation TEXTURE3 = new ResourceLocation("Thaumcraft:blocks/taint_growth_2");
/*  37 */   static final ResourceLocation TEXTURE4 = new ResourceLocation("Thaumcraft:blocks/taint_growth_3");
/*  38 */   static final ResourceLocation TEXTURE5 = new ResourceLocation("Thaumcraft:blocks/taint_growth_4");
/*     */   
/*  40 */   static final ResourceLocation FIBRE_MODEL = new ResourceLocation("Thaumcraft:block/taint_fibre");
/*  41 */   static final ResourceLocation GROWTH_MODEL_1 = new ResourceLocation("Thaumcraft:block/taint_growth_1");
/*  42 */   static final ResourceLocation GROWTH_MODEL_2 = new ResourceLocation("Thaumcraft:block/taint_growth_2");
/*  43 */   static final ResourceLocation GROWTH_MODEL_3 = new ResourceLocation("Thaumcraft:block/taint_growth_3");
/*  44 */   static final ResourceLocation GROWTH_MODEL_4 = new ResourceLocation("Thaumcraft:block/taint_growth_4");
/*     */   
/*     */ 
/*     */   public CustomTaintFibreModel(IResourceManager resourceManager) {}
/*     */   
/*     */ 
/*     */   public Collection<ResourceLocation> getDependencies()
/*     */   {
/*  52 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public Collection<ResourceLocation> getTextures()
/*     */   {
/*  57 */     return Arrays.asList(new ResourceLocation[] { TEXTURE1, TEXTURE2, TEXTURE3, TEXTURE4, TEXTURE5 });
/*     */   }
/*     */   
/*     */   public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
/*     */   {
/*  62 */     return new BakedModel(format, bakedTextureGetter);
/*     */   }
/*     */   
/*     */   public IModelState getDefaultState()
/*     */   {
/*  67 */     return null;
/*     */   }
/*     */   
/*     */   public class BakedModel
/*     */     extends SmartBlockModel
/*     */   {
/*     */     public BakedModel(Function<ResourceLocation, TextureAtlasSprite> format)
/*     */     {
/*  75 */       super(false, (TextureAtlasSprite)bakedTextureGetter.apply(CustomTaintFibreModel.TEXTURE1), format, bakedTextureGetter);
/*     */     }
/*     */     
/*     */     public IBakedModel handleBlockState(IBlockState state)
/*     */     {
/*  80 */       if ((state instanceof IExtendedBlockState)) {
/*  81 */         IExtendedBlockState es = (IExtendedBlockState)state;
/*     */         
/*     */ 
/*  84 */         SimpleBakedModel.Builder b = new SimpleBakedModel.Builder(this, null);
/*  85 */         IModel model_feature = null;
/*     */         try {
/*  87 */           IModel model_fibre = ModelLoaderRegistry.getModel(CustomTaintFibreModel.FIBRE_MODEL);
/*     */           
/*     */ 
/*  90 */           if ((!((Boolean)es.getValue(BlockTaintFibre.UP)).booleanValue()) || (!((Boolean)es.getValue(BlockTaintFibre.DOWN)).booleanValue()) || (!((Boolean)es.getValue(BlockTaintFibre.EAST)).booleanValue()) || (!((Boolean)es.getValue(BlockTaintFibre.WEST)).booleanValue()) || (!((Boolean)es.getValue(BlockTaintFibre.NORTH)).booleanValue()) || (!((Boolean)es.getValue(BlockTaintFibre.SOUTH)).booleanValue()))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  96 */             if (((Boolean)es.getValue(BlockTaintFibre.UP)).booleanValue()) {
/*  97 */               IBakedModel bm = model_fibre.bake(ModelRotation.X180_Y0, Attributes.DEFAULT_BAKED_FORMAT, this.textureGetter);
/*  98 */               List<BakedQuad> list = bm.func_177550_a();
/*  99 */               for (BakedQuad bq : list) {
/* 100 */                 b.func_177648_a(bq);
/*     */               }
/*     */             }
/*     */             
/* 104 */             if (((Boolean)es.getValue(BlockTaintFibre.DOWN)).booleanValue()) {
/* 105 */               IBakedModel bm = model_fibre.bake(model_fibre.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, this.textureGetter);
/* 106 */               List<BakedQuad> list = bm.func_177550_a();
/* 107 */               for (BakedQuad bq : list) {
/* 108 */                 b.func_177648_a(bq);
/*     */               }
/*     */             }
/*     */             
/* 112 */             if (((Boolean)es.getValue(BlockTaintFibre.EAST)).booleanValue()) {
/* 113 */               IBakedModel bm = model_fibre.bake(ModelRotation.X270_Y90, Attributes.DEFAULT_BAKED_FORMAT, this.textureGetter);
/* 114 */               List<BakedQuad> list = bm.func_177550_a();
/* 115 */               for (BakedQuad bq : list) {
/* 116 */                 b.func_177648_a(bq);
/*     */               }
/*     */             }
/*     */             
/* 120 */             if (((Boolean)es.getValue(BlockTaintFibre.WEST)).booleanValue()) {
/* 121 */               IBakedModel bm = model_fibre.bake(ModelRotation.X90_Y90, Attributes.DEFAULT_BAKED_FORMAT, this.textureGetter);
/* 122 */               List<BakedQuad> list = bm.func_177550_a();
/* 123 */               for (BakedQuad bq : list) {
/* 124 */                 b.func_177648_a(bq);
/*     */               }
/*     */             }
/*     */             
/* 128 */             if (((Boolean)es.getValue(BlockTaintFibre.NORTH)).booleanValue()) {
/* 129 */               IBakedModel bm = model_fibre.bake(ModelRotation.X90_Y180, Attributes.DEFAULT_BAKED_FORMAT, this.textureGetter);
/* 130 */               List<BakedQuad> list = bm.func_177550_a();
/* 131 */               for (BakedQuad bq : list) {
/* 132 */                 b.func_177648_a(bq);
/*     */               }
/*     */             }
/*     */             
/* 136 */             if (((Boolean)es.getValue(BlockTaintFibre.SOUTH)).booleanValue()) {
/* 137 */               IBakedModel bm = model_fibre.bake(ModelRotation.X90_Y0, Attributes.DEFAULT_BAKED_FORMAT, this.textureGetter);
/* 138 */               List<BakedQuad> list = bm.func_177550_a();
/* 139 */               for (BakedQuad bq : list) {
/* 140 */                 b.func_177648_a(bq);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 146 */           switch (((Integer)es.getValue(BlockTaintFibre.GROWTH)).intValue()) {
/* 147 */           case 1:  model_feature = ModelLoaderRegistry.getModel(CustomTaintFibreModel.GROWTH_MODEL_1); break;
/* 148 */           case 2:  model_feature = ModelLoaderRegistry.getModel(CustomTaintFibreModel.GROWTH_MODEL_2); break;
/* 149 */           case 3:  model_feature = ModelLoaderRegistry.getModel(CustomTaintFibreModel.GROWTH_MODEL_3); break;
/* 150 */           case 4:  model_feature = ModelLoaderRegistry.getModel(CustomTaintFibreModel.GROWTH_MODEL_4);
/*     */           }
/*     */           
/* 153 */           if (model_feature != null) {
/* 154 */             IBakedModel bm2 = model_feature.bake(model_feature.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, this.textureGetter);
/* 155 */             List<BakedQuad> list2 = bm2.func_177550_a();
/* 156 */             for (BakedQuad bq : list2) {
/* 157 */               b.func_177648_a(bq);
/*     */             }
/*     */           }
/*     */           
/* 161 */           return b.func_177645_b();
/* 162 */         } catch (IOException e) { e.printStackTrace();
/*     */         }
/*     */       }
/*     */       
/* 166 */       return this;
/*     */     }
/*     */     
/*     */ 
/*     */     public IBakedModel handleItemState(ItemStack stack)
/*     */     {
/* 172 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/block/CustomTaintFibreModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */