/*    */ package thaumcraft.client.lib.models;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.resources.model.IBakedModel;
/*    */ import net.minecraft.client.resources.model.ModelManager;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.ISmartBlockModel;
/*    */ import net.minecraftforge.client.model.ISmartItemModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomMeshModel
/*    */   implements ISmartItemModel, ISmartBlockModel, IInitializeBakedModel
/*    */ {
/*    */   String variant;
/*    */   ResourceLocation model;
/*    */   ItemCameraTransforms transforms;
/*    */   List<BakedQuad> faceQuads;
/*    */   List<BakedQuad> generalQuads;
/*    */   MeshModel sourceMesh;
/*    */   TextureAtlasSprite iconSprite;
/*    */   
/*    */   public CustomMeshModel(String variant)
/*    */   {
/* 34 */     this.variant = variant;
/* 35 */     this.model = new ResourceLocation("Thaumcraft", "models/obj/" + variant + ".obj");
/* 36 */     this.faceQuads = new ArrayList();
/* 37 */     this.generalQuads = new ArrayList();
/*    */     try
/*    */     {
/* 40 */       this.generalQuads.clear();
/* 41 */       this.sourceMesh = new MeshLoader().loadFromResource(this.model);
/*    */     } catch (IOException e) {
/* 43 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void initialize(ItemCameraTransforms cameraTransforms, ResourceLocation icon, ModelManager modelManager)
/*    */   {
/* 50 */     this.transforms = cameraTransforms;
/* 51 */     this.iconSprite = modelManager.func_174952_b().func_110572_b(icon.toString());
/*    */     
/* 53 */     this.generalQuads = this.sourceMesh.bakeModel(modelManager);
/*    */   }
/*    */   
/*    */   public IBakedModel handleItemState(ItemStack stack)
/*    */   {
/* 58 */     return this;
/*    */   }
/*    */   
/*    */   public IBakedModel handleBlockState(IBlockState state)
/*    */   {
/* 63 */     return this;
/*    */   }
/*    */   
/*    */   public List func_177551_a(EnumFacing face)
/*    */   {
/* 68 */     return this.faceQuads;
/*    */   }
/*    */   
/*    */   public List func_177550_a()
/*    */   {
/* 73 */     return this.generalQuads;
/*    */   }
/*    */   
/*    */   public boolean func_177555_b()
/*    */   {
/* 78 */     return false;
/*    */   }
/*    */   
/*    */   public boolean func_177556_c()
/*    */   {
/* 83 */     return false;
/*    */   }
/*    */   
/*    */   public boolean func_177553_d()
/*    */   {
/* 88 */     return false;
/*    */   }
/*    */   
/*    */   public TextureAtlasSprite func_177554_e()
/*    */   {
/* 93 */     return this.iconSprite;
/*    */   }
/*    */   
/*    */   public ItemCameraTransforms func_177552_f()
/*    */   {
/* 98 */     return this.transforms;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/CustomMeshModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */