/*     */ package thaumcraft.client.renderers.models.block;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector2f;
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
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.client.lib.models.MeshLoader;
/*     */ import thaumcraft.client.lib.models.MeshModel;
/*     */ import thaumcraft.client.lib.models.MeshPart;
/*     */ import thaumcraft.client.lib.models.block.SmartBlockModel;
/*     */ import thaumcraft.common.blocks.devices.BlockTube;
/*     */ import thaumcraft.common.blocks.devices.BlockTube.TubeType;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class CustomTubeModel
/*     */   implements IModel
/*     */ {
/*  38 */   public static final ResourceLocation TA = new ResourceLocation("Thaumcraft:blocks/tube");
/*     */   ResourceLocation model;
/*     */   MeshModel sourceMesh;
/*     */   
/*     */   public CustomTubeModel(IResourceManager resourceManager)
/*     */   {
/*  44 */     this.model = new ResourceLocation("Thaumcraft", "models/obj/tube.obj");
/*     */     try {
/*  46 */       this.sourceMesh = new MeshLoader().loadFromResource(this.model);
/*     */     } catch (IOException e) {
/*  48 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public Collection<ResourceLocation> getDependencies()
/*     */   {
/*  54 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public Collection<ResourceLocation> getTextures()
/*     */   {
/*  59 */     return Arrays.asList(new ResourceLocation[] { TA });
/*     */   }
/*     */   
/*     */   public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
/*     */   {
/*  64 */     return new BakedModel(format, bakedTextureGetter);
/*     */   }
/*     */   
/*     */   public IModelState getDefaultState()
/*     */   {
/*  69 */     return null;
/*     */   }
/*     */   
/*     */   public class BakedModel
/*     */     extends SmartBlockModel
/*     */   {
/*     */     public BakedModel(Function<ResourceLocation, TextureAtlasSprite> format)
/*     */     {
/*  77 */       super(false, (TextureAtlasSprite)bakedTextureGetter.apply(CustomTubeModel.TA), format, bakedTextureGetter);
/*     */     }
/*     */     
/*     */     public IBakedModel handleBlockState(IBlockState state)
/*     */     {
/*  82 */       SimpleBakedModel.Builder b = new SimpleBakedModel.Builder(this, null);
/*  83 */       Boolean[] cons = { Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false) };
/*  84 */       if ((state instanceof IExtendedBlockState)) {
/*  85 */         cons = (Boolean[])((IExtendedBlockState)state).getValue(BlockTube.CONNECTIONS);
/*     */       }
/*     */       
/*  88 */       MeshModel mesh = new MeshModel();
/*  89 */       mesh.positions = CustomTubeModel.this.sourceMesh.positions;
/*  90 */       mesh.normals = CustomTubeModel.this.sourceMesh.normals;
/*  91 */       int i = 0;
/*  92 */       for (Vector2f v : CustomTubeModel.this.sourceMesh.texCoords) {
/*  93 */         float n = 0.0F;
/*  94 */         float m = 0.0F;
/*  95 */         if ((i >= 4) && (i < 28)) {
/*  96 */           if (state.func_177229_b(BlockTube.TYPE) == BlockTube.TubeType.RESTRICT) m = 0.125F;
/*  97 */           if (state.func_177229_b(BlockTube.TYPE) == BlockTube.TubeType.BUFFER) m = 0.25F;
/*  98 */           if (state.func_177229_b(BlockTube.TYPE) == BlockTube.TubeType.FILTER) m = 0.375F;
/*     */         }
/* 100 */         if ((i < 4) && 
/* 101 */           (state.func_177229_b(BlockTube.TYPE) == BlockTube.TubeType.FILTER)) { n = 0.1875F;
/*     */         }
/* 103 */         mesh.addTexCoords(v.x + n, v.y + m);
/* 104 */         i++;
/*     */       }
/* 106 */       if (state.func_177229_b(BlockTube.TYPE) != BlockTube.TubeType.BUFFER) {
/* 107 */         if (state.func_177229_b(BlockTube.TYPE) == BlockTube.TubeType.FILTER) {
/* 108 */           mesh.addPart((MeshPart)CustomTubeModel.this.sourceMesh.parts.get(0), 1);
/*     */         } else
/* 110 */           mesh.addPart((MeshPart)CustomTubeModel.this.sourceMesh.parts.get(0));
/*     */       } else {
/* 112 */         mesh.addPart((MeshPart)CustomTubeModel.this.sourceMesh.parts.get(7));
/*     */       }
/* 114 */       int c = 1;
/* 115 */       for (Boolean bb : cons) {
/* 116 */         if (bb.booleanValue()) {
/* 117 */           mesh.addPart((MeshPart)CustomTubeModel.this.sourceMesh.parts.get(c));
/*     */         }
/* 119 */         c++;
/*     */       }
/*     */       
/* 122 */       b.func_177646_a(func_177554_e());
/* 123 */       for (BakedQuad quad : mesh.bakeModel(func_177554_e())) {
/* 124 */         b.func_177648_a(quad);
/*     */       }
/*     */       
/* 127 */       return b.func_177645_b();
/*     */     }
/*     */     
/*     */     public IBakedModel handleItemState(ItemStack stack)
/*     */     {
/* 132 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/block/CustomTubeModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */