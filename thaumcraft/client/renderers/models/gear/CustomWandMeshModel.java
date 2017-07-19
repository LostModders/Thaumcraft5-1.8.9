/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.block.model.ModelBlock;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import net.minecraft.client.resources.model.SimpleBakedModel;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.ISmartItemModel;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.client.lib.models.IInitializeBakedModel;
/*     */ import thaumcraft.client.lib.models.MeshLoader;
/*     */ import thaumcraft.client.lib.models.MeshModel;
/*     */ import thaumcraft.client.lib.models.MeshPart;
/*     */ import thaumcraft.client.lib.models.ModelRegistrationHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomWandMeshModel
/*     */   implements ISmartItemModel, IInitializeBakedModel
/*     */ {
/*     */   String variant;
/*     */   ResourceLocation model;
/*     */   ItemCameraTransforms transforms;
/*     */   List<BakedQuad> faceQuads;
/*     */   List<BakedQuad> generalQuads;
/*     */   MeshModel sourceMesh;
/*     */   TextureAtlasSprite iconSprite;
/*  47 */   private static final List<List<BakedQuad>> empty_face_quads = ;
/*  48 */   static { for (int i = 0; i < 6; i++)
/*  49 */       empty_face_quads.add(new LinkedList());
/*     */   }
/*     */   
/*  52 */   static HashMap<String, CustomWandMeshModel> variants = new HashMap();
/*  53 */   public static HashMap<String, SimpleBakedModel> cache = new HashMap();
/*     */   
/*     */   public CustomWandMeshModel(String variant, WandCap cap) {
/*  56 */     setup(variant, false);
/*  57 */     ((MeshPart)this.sourceMesh.parts.get(4)).material.DiffuseTextureMap = cap.getTexture().toString();
/*  58 */     this.sourceMesh.parts.remove(3);
/*  59 */     this.sourceMesh.parts.remove(2);
/*  60 */     this.sourceMesh.parts.remove(1);
/*  61 */     this.sourceMesh.parts.remove(0);
/*  62 */     variants.put(variant, this);
/*     */   }
/*     */   
/*     */   public CustomWandMeshModel(String variant, ItemFocusBasic focus) {
/*  66 */     setup(variant, false);
/*  67 */     this.sourceMesh.parts.remove(4);
/*  68 */     ((MeshPart)this.sourceMesh.parts.get(3)).material.DiffuseTextureMap = focus.getFocusTexture().toString();
/*  69 */     this.sourceMesh.parts.remove(2);
/*  70 */     this.sourceMesh.parts.remove(1);
/*  71 */     this.sourceMesh.parts.remove(0);
/*  72 */     variants.put(variant, this);
/*     */   }
/*     */   
/*     */   public CustomWandMeshModel(String variant, WandRod rod) {
/*  76 */     setup(variant, rod.isStaff());
/*  77 */     ((MeshPart)this.sourceMesh.parts.get(0)).material.DiffuseTextureMap = rod.getTexture().toString();
/*  78 */     if (!rod.isStaff()) this.sourceMesh.parts.remove(4);
/*  79 */     this.sourceMesh.parts.remove(3);
/*  80 */     this.sourceMesh.parts.remove(2);
/*  81 */     this.sourceMesh.parts.remove(1);
/*  82 */     variants.put(variant, this);
/*     */   }
/*     */   
/*     */   public CustomWandMeshModel(String variant, WandCap cap, boolean staff) {
/*  86 */     setup(variant, staff);
/*  87 */     ((MeshPart)this.sourceMesh.parts.get(1)).material.DiffuseTextureMap = cap.getTexture().toString();
/*  88 */     ((MeshPart)this.sourceMesh.parts.get(2)).material.DiffuseTextureMap = cap.getTexture().toString();
/*  89 */     if (!staff) this.sourceMesh.parts.remove(4);
/*  90 */     this.sourceMesh.parts.remove(3);
/*  91 */     this.sourceMesh.parts.remove(0);
/*  92 */     variants.put(variant, this);
/*     */   }
/*     */   
/*     */   public CustomWandMeshModel(String itemName) {
/*  96 */     setup(itemName, false);
/*     */   }
/*     */   
/*     */   public void setup(String variant, boolean staff) {
/* 100 */     this.variant = variant;
/* 101 */     if (staff) {
/* 102 */       this.model = new ResourceLocation("Thaumcraft", "models/obj/staff.obj");
/*     */     } else {
/* 104 */       this.model = new ResourceLocation("Thaumcraft", "models/obj/wand.obj");
/*     */     }
/* 106 */     this.faceQuads = new ArrayList();
/* 107 */     this.generalQuads = new ArrayList();
/*     */     try
/*     */     {
/* 110 */       this.generalQuads.clear();
/* 111 */       this.sourceMesh = new MeshLoader().loadFromResource(this.model);
/*     */     } catch (IOException e) {
/* 113 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void initialize(ItemCameraTransforms cameraTransforms, ResourceLocation icon, ModelManager modelManager)
/*     */   {
/* 119 */     this.transforms = cameraTransforms;
/* 120 */     this.iconSprite = modelManager.func_174952_b().func_110572_b(icon.toString());
/*     */     
/* 122 */     this.generalQuads = this.sourceMesh.bakeModel(modelManager);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IBakedModel handleItemState(ItemStack stack)
/*     */   {
/* 130 */     IWand wand = (IWand)stack.func_77973_b();
/* 131 */     WandRod rod = wand.getRod(stack);
/* 132 */     WandCap cap = wand.getCap(stack);
/* 133 */     ItemFocusBasic focus = wand.getFocus(stack);
/*     */     
/* 135 */     String cacheKey = (wand.isSceptre(stack) ? "sceptre" : rod.isStaff() ? "staff" : "wand") + rod.getTag() + cap.getTag();
/* 136 */     if (focus != null) cacheKey = cacheKey + focus.getFocusId();
/* 137 */     if (cache.containsKey(cacheKey)) { return (IBakedModel)cache.get(cacheKey);
/*     */     }
/* 139 */     List<BakedQuad> quads = Lists.newArrayList();
/*     */     
/* 141 */     String key = (rod.isStaff() ? "staff" : "wand") + rod.getTag();
/* 142 */     if (!variants.containsKey(key)) return this;
/* 143 */     for (BakedQuad q : ((CustomWandMeshModel)variants.get(key)).generalQuads) {
/* 144 */       quads.add(q);
/*     */     }
/*     */     
/* 147 */     key = (rod.isStaff() ? "staff" : "wand") + cap.getTag();
/* 148 */     if (!variants.containsKey(key)) return this;
/* 149 */     for (BakedQuad q : ((CustomWandMeshModel)variants.get(key)).generalQuads) {
/* 150 */       quads.add(q);
/*     */     }
/*     */     
/* 153 */     if (wand.isSceptre(stack)) {
/* 154 */       key = "sceptre" + cap.getTag();
/* 155 */       if (!variants.containsKey(key)) return this;
/* 156 */       for (BakedQuad q : ((CustomWandMeshModel)variants.get(key)).generalQuads) {
/* 157 */         quads.add(q);
/*     */       }
/*     */     }
/*     */     
/* 161 */     if (focus != null) {
/* 162 */       key = "focus" + focus.getFocusId();
/* 163 */       if (!variants.containsKey(key)) return this;
/* 164 */       for (BakedQuad q : ((CustomWandMeshModel)variants.get(key)).generalQuads) {
/* 165 */         quads.add(q);
/*     */       }
/*     */     }
/*     */     
/* 169 */     ItemCameraTransforms trans = func_177552_f();
/*     */     
/* 171 */     if (rod.isStaff()) {
/* 172 */       ModelRegistrationHelper helper = new ModelRegistrationHelper();
/* 173 */       ResourceLocation icon = new ResourceLocation("Thaumcraft", "item/staff");
/* 174 */       ModelBlock modelblock = helper.loadModelResource(icon);
/* 175 */       if (modelblock != null) {
/* 176 */         trans = new ItemCameraTransforms(modelblock.func_181682_g());
/*     */       }
/*     */     }
/*     */     
/* 180 */     SimpleBakedModel model = new SimpleBakedModel(quads, empty_face_quads, func_177555_b(), func_177556_c(), func_177554_e(), trans);
/*     */     
/*     */ 
/*     */ 
/* 184 */     if (model != null) {
/* 185 */       cache.put(cacheKey, model);
/*     */     }
/*     */     
/* 188 */     return model;
/*     */   }
/*     */   
/*     */   public List func_177551_a(EnumFacing face)
/*     */   {
/* 193 */     return this.faceQuads;
/*     */   }
/*     */   
/*     */   public List func_177550_a()
/*     */   {
/* 198 */     return this.generalQuads;
/*     */   }
/*     */   
/*     */   public boolean func_177555_b()
/*     */   {
/* 203 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_177556_c()
/*     */   {
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_177553_d()
/*     */   {
/* 213 */     return false;
/*     */   }
/*     */   
/*     */   public TextureAtlasSprite func_177554_e()
/*     */   {
/* 218 */     return this.iconSprite;
/*     */   }
/*     */   
/*     */   public ItemCameraTransforms func_177552_f()
/*     */   {
/* 223 */     return this.transforms;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/gear/CustomWandMeshModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */