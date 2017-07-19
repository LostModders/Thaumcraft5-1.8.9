/*     */ package thaumcraft.client.renderers.models.block;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.SimpleBakedModel.Builder;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.IFlexibleBakedModel;
/*     */ import net.minecraftforge.client.model.IModel;
/*     */ import net.minecraftforge.client.model.IModelState;
/*     */ import net.minecraftforge.common.property.IExtendedBlockState;
/*     */ import thaumcraft.client.lib.models.MeshLoader;
/*     */ import thaumcraft.client.lib.models.MeshModel;
/*     */ import thaumcraft.client.lib.models.MeshPart;
/*     */ import thaumcraft.client.lib.models.block.SmartBlockModel;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.blocks.world.ore.BlockCrystal;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomCrystalModel
/*     */   implements IModel
/*     */ {
/*  35 */   public static final ResourceLocation TEXTURE = new ResourceLocation("Thaumcraft:blocks/crystal");
/*     */   ResourceLocation model;
/*     */   MeshModel sourceMesh;
/*     */   ResourceLocation tex;
/*     */   
/*     */   public CustomCrystalModel(IResourceManager resourceManager)
/*     */   {
/*  42 */     this.model = new ResourceLocation("Thaumcraft", "models/obj/crystal.obj");
/*  43 */     this.tex = TEXTURE;
/*     */     try {
/*  45 */       this.sourceMesh = new MeshLoader().loadFromResource(this.model);
/*  46 */       for (MeshPart mp : this.sourceMesh.parts) {
/*  47 */         mp.tintIndex = 0;
/*     */       }
/*     */     } catch (IOException e) {
/*  50 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public Collection<ResourceLocation> getDependencies()
/*     */   {
/*  56 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public Collection<ResourceLocation> getTextures()
/*     */   {
/*  61 */     return Arrays.asList(new ResourceLocation[] { this.tex });
/*     */   }
/*     */   
/*     */   public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
/*     */   {
/*  66 */     return new BakedModel(format, bakedTextureGetter);
/*     */   }
/*     */   
/*     */   public IModelState getDefaultState()
/*     */   {
/*  71 */     return null;
/*     */   }
/*     */   
/*     */   public class BakedModel
/*     */     extends SmartBlockModel
/*     */   {
/*     */     public BakedModel(Function<ResourceLocation, TextureAtlasSprite> format)
/*     */     {
/*  79 */       super(false, (TextureAtlasSprite)bakedTextureGetter.apply(CustomCrystalModel.this.tex), format, bakedTextureGetter);
/*     */     }
/*     */     
/*     */     public IBakedModel handleBlockState(IBlockState state)
/*     */     {
/*  84 */       if ((state instanceof IExtendedBlockState)) {
/*  85 */         IExtendedBlockState es = (IExtendedBlockState)state;
/*     */         
/*  87 */         MeshModel mm = CustomCrystalModel.this.sourceMesh.clone();
/*     */         
/*     */ 
/*     */ 
/*  91 */         int m = ((BlockCrystal)state.func_177230_c()).getGrowth(state) + 1;
/*     */         
/*     */ 
/*     */ 
/*  95 */         SimpleBakedModel.Builder b = new SimpleBakedModel.Builder(this, null);
/*  96 */         b.func_177646_a(func_177554_e());
/*  97 */         if ((!((Boolean)es.getValue(BlockCrystal.UP)).booleanValue()) || (!((Boolean)es.getValue(BlockCrystal.DOWN)).booleanValue()) || (!((Boolean)es.getValue(BlockCrystal.EAST)).booleanValue()) || (!((Boolean)es.getValue(BlockCrystal.WEST)).booleanValue()) || (!((Boolean)es.getValue(BlockCrystal.NORTH)).booleanValue()) || (!((Boolean)es.getValue(BlockCrystal.SOUTH)).booleanValue()))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 104 */           if (((Boolean)es.getValue(BlockCrystal.UP)).booleanValue()) {
/* 105 */             List<Integer> c = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7) });
/* 106 */             Collections.shuffle(c, new Random(1 + ((Integer)es.getValue(BlockCrystal.SEED)).intValue() * 1000));
/* 107 */             mm.parts.clear();
/* 108 */             for (int a = 0; a < m; a++) mm.parts.add(CustomCrystalModel.this.sourceMesh.parts.get(((Integer)c.get(a)).intValue()));
/* 109 */             MeshModel mod = mm.clone();
/* 110 */             mod.rotate(Math.toRadians(180.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 1.0D, 1.0D));
/* 111 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 112 */               b.func_177648_a(quad);
/*     */             }
/*     */           }
/*     */           
/* 116 */           if (((Boolean)es.getValue(BlockCrystal.DOWN)).booleanValue()) {
/* 117 */             List<Integer> c = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7) });
/* 118 */             Collections.shuffle(c, new Random(2 + ((Integer)es.getValue(BlockCrystal.SEED)).intValue() * 2000));
/* 119 */             mm.parts.clear();
/* 120 */             for (int a = 0; a < m; a++) mm.parts.add(CustomCrystalModel.this.sourceMesh.parts.get(((Integer)c.get(a)).intValue()));
/* 121 */             for (BakedQuad quad : mm.bakeModel(func_177554_e())) {
/* 122 */               b.func_177648_a(quad);
/*     */             }
/*     */           }
/*     */           
/* 126 */           if (((Boolean)es.getValue(BlockCrystal.EAST)).booleanValue()) {
/* 127 */             List<Integer> c = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7) });
/* 128 */             Collections.shuffle(c, new Random(3 + ((Integer)es.getValue(BlockCrystal.SEED)).intValue() * 3000));
/* 129 */             mm.parts.clear();
/* 130 */             for (int a = 0; a < m; a++) mm.parts.add(CustomCrystalModel.this.sourceMesh.parts.get(((Integer)c.get(a)).intValue()));
/* 131 */             MeshModel mod = mm.clone();
/* 132 */             mod.rotate(Math.toRadians(90.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 0.0D, 0.0D));
/* 133 */             mod.rotate(Math.toRadians(270.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(1.0D, 1.0D, 0.0D));
/* 134 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 135 */               b.func_177648_a(quad);
/*     */             }
/*     */           }
/*     */           
/* 139 */           if (((Boolean)es.getValue(BlockCrystal.WEST)).booleanValue()) {
/* 140 */             List<Integer> c = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7) });
/* 141 */             Collections.shuffle(c, new Random(4 + ((Integer)es.getValue(BlockCrystal.SEED)).intValue() * 4000));
/* 142 */             mm.parts.clear();
/* 143 */             for (int a = 0; a < m; a++) mm.parts.add(CustomCrystalModel.this.sourceMesh.parts.get(((Integer)c.get(a)).intValue()));
/* 144 */             MeshModel mod = mm.clone();
/* 145 */             mod.rotate(Math.toRadians(90.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 0.0D, 0.0D));
/* 146 */             mod.rotate(Math.toRadians(90.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(0.0D, 1.0D, 1.0D));
/* 147 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 148 */               b.func_177648_a(quad);
/*     */             }
/*     */           }
/*     */           
/* 152 */           if (((Boolean)es.getValue(BlockCrystal.NORTH)).booleanValue()) {
/* 153 */             List<Integer> c = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7) });
/* 154 */             Collections.shuffle(c, new Random(5 + ((Integer)es.getValue(BlockCrystal.SEED)).intValue() * 5000));
/* 155 */             mm.parts.clear();
/* 156 */             for (int a = 0; a < m; a++) mm.parts.add(CustomCrystalModel.this.sourceMesh.parts.get(((Integer)c.get(a)).intValue()));
/* 157 */             MeshModel mod = mm.clone();
/* 158 */             mod.rotate(Math.toRadians(90.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 1.0D, 0.0D));
/* 159 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 160 */               b.func_177648_a(quad);
/*     */             }
/*     */           }
/*     */           
/* 164 */           if (((Boolean)es.getValue(BlockCrystal.SOUTH)).booleanValue()) {
/* 165 */             List<Integer> c = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7) });
/* 166 */             Collections.shuffle(c, new Random(6 + ((Integer)es.getValue(BlockCrystal.SEED)).intValue() * 6000));
/* 167 */             mm.parts.clear();
/* 168 */             for (int a = 0; a < m; a++) mm.parts.add(CustomCrystalModel.this.sourceMesh.parts.get(((Integer)c.get(a)).intValue()));
/* 169 */             MeshModel mod = mm.clone();
/* 170 */             mod.rotate(Math.toRadians(90.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 0.0D, 0.0D));
/* 171 */             mod.rotate(Math.toRadians(180.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(1.0D, 1.0D, 1.0D));
/* 172 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 173 */               b.func_177648_a(quad);
/*     */             }
/*     */           }
/*     */         }
/* 177 */         return b.func_177645_b();
/*     */       }
/*     */       
/* 180 */       return this;
/*     */     }
/*     */     
/*     */ 
/*     */     public IBakedModel handleItemState(ItemStack stack)
/*     */     {
/* 186 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/block/CustomCrystalModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */