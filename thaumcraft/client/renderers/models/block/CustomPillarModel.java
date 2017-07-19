/*    */ package thaumcraft.client.renderers.models.block;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.client.resources.model.IBakedModel;
/*    */ import net.minecraft.client.resources.model.SimpleBakedModel.Builder;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.IFlexibleBakedModel;
/*    */ import net.minecraftforge.client.model.IModel;
/*    */ import net.minecraftforge.client.model.IModelState;
/*    */ import thaumcraft.client.lib.models.MeshLoader;
/*    */ import thaumcraft.client.lib.models.MeshModel;
/*    */ import thaumcraft.client.lib.models.block.SmartBlockModel;
/*    */ import thaumcraft.codechicken.lib.vec.Vector3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomPillarModel
/*    */   implements IModel
/*    */ {
/* 32 */   public static final ResourceLocation T_NORMAL = new ResourceLocation("Thaumcraft:blocks/pillar_normal");
/* 33 */   public static final ResourceLocation T_ANCIENT = new ResourceLocation("Thaumcraft:blocks/pillar_ancient");
/* 34 */   public static final ResourceLocation T_ELDRITCH = new ResourceLocation("Thaumcraft:blocks/pillar_eldritch");
/*    */   ResourceLocation model;
/*    */   MeshModel sourceMesh;
/*    */   ResourceLocation tex;
/*    */   
/*    */   public CustomPillarModel(IResourceManager resourceManager, ResourceLocation ta)
/*    */   {
/* 41 */     this.model = new ResourceLocation("Thaumcraft", "models/obj/pillar.obj");
/* 42 */     this.tex = ta;
/*    */     try {
/* 44 */       this.sourceMesh = new MeshLoader().loadFromResource(this.model);
/*    */     } catch (IOException e) {
/* 46 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public Collection<ResourceLocation> getDependencies()
/*    */   {
/* 52 */     return Collections.emptyList();
/*    */   }
/*    */   
/*    */   public Collection<ResourceLocation> getTextures()
/*    */   {
/* 57 */     return Arrays.asList(new ResourceLocation[] { this.tex });
/*    */   }
/*    */   
/*    */   public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
/*    */   {
/* 62 */     return new BakedModel(format, bakedTextureGetter);
/*    */   }
/*    */   
/*    */   public IModelState getDefaultState()
/*    */   {
/* 67 */     return null;
/*    */   }
/*    */   
/*    */   public class BakedModel
/*    */     extends SmartBlockModel
/*    */   {
/*    */     public BakedModel(Function<ResourceLocation, TextureAtlasSprite> format)
/*    */     {
/* 75 */       super(false, (TextureAtlasSprite)bakedTextureGetter.apply(CustomPillarModel.this.tex), format, bakedTextureGetter);
/*    */     }
/*    */     
/*    */     public IBakedModel handleBlockState(IBlockState state)
/*    */     {
/* 80 */       MeshModel mm = CustomPillarModel.this.sourceMesh.clone();
/* 81 */       SimpleBakedModel.Builder b = new SimpleBakedModel.Builder(this, null);
/* 82 */       switch (CustomPillarModel.1.$SwitchMap$net$minecraft$util$EnumFacing[((net.minecraft.util.EnumFacing)state.func_177229_b(thaumcraft.common.blocks.basic.BlockPillar.FACING)).ordinal()]) {
/* 83 */       case 1:  mm.rotate(Math.toRadians(270.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(1.0D, 0.0D, 0.0D)); break;
/* 84 */       case 2:  mm.rotate(Math.toRadians(90.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(0.0D, 0.0D, 1.0D)); break;
/* 85 */       case 3:  mm.rotate(Math.toRadians(180.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(1.0D, 0.0D, 1.0D));
/*    */       }
/*    */       
/* 88 */       b.func_177646_a(func_177554_e());
/* 89 */       for (BakedQuad quad : mm.bakeModel(func_177554_e())) {
/* 90 */         b.func_177648_a(quad);
/*    */       }
/* 92 */       return b.func_177645_b();
/*    */     }
/*    */     
/*    */ 
/*    */     public IBakedModel handleItemState(ItemStack stack)
/*    */     {
/* 98 */       return this;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/block/CustomPillarModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */