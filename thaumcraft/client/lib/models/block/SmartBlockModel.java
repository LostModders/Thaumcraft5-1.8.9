/*    */ package thaumcraft.client.lib.models.block;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.IFlexibleBakedModel;
/*    */ import net.minecraftforge.client.model.ISmartBlockModel;
/*    */ import net.minecraftforge.client.model.ISmartItemModel;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public abstract class SmartBlockModel
/*    */   implements IFlexibleBakedModel, ISmartBlockModel, ISmartItemModel
/*    */ {
/*    */   private boolean isAmbientOcclusion;
/*    */   private boolean isGui3d;
/*    */   private TextureAtlasSprite texture;
/*    */   private VertexFormat format;
/*    */   protected Function<ResourceLocation, TextureAtlasSprite> textureGetter;
/*    */   
/*    */   public SmartBlockModel(boolean ambientOcclusion, boolean gui3d, TextureAtlasSprite texture, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
/*    */   {
/* 36 */     this.isAmbientOcclusion = ambientOcclusion;
/* 37 */     this.isGui3d = gui3d;
/* 38 */     this.texture = texture;
/* 39 */     this.format = format;
/* 40 */     this.textureGetter = bakedTextureGetter;
/*    */   }
/*    */   
/*    */   public boolean func_177555_b()
/*    */   {
/* 45 */     return this.isAmbientOcclusion;
/*    */   }
/*    */   
/*    */   public boolean func_177556_c()
/*    */   {
/* 50 */     return this.isGui3d;
/*    */   }
/*    */   
/*    */   public boolean func_177553_d()
/*    */   {
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   public TextureAtlasSprite func_177554_e()
/*    */   {
/* 60 */     return this.texture;
/*    */   }
/*    */   
/*    */   public ItemCameraTransforms func_177552_f()
/*    */   {
/* 65 */     return ItemCameraTransforms.field_178357_a;
/*    */   }
/*    */   
/*    */   public List<BakedQuad> func_177551_a(EnumFacing side)
/*    */   {
/* 70 */     return Collections.EMPTY_LIST;
/*    */   }
/*    */   
/*    */   public List<BakedQuad> func_177550_a()
/*    */   {
/* 75 */     return Collections.EMPTY_LIST;
/*    */   }
/*    */   
/*    */   public VertexFormat getFormat()
/*    */   {
/* 80 */     return this.format;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/block/SmartBlockModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */