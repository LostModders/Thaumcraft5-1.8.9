/*    */ package thaumcraft.client.lib.models.block;
/*    */ 
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.ICustomModelLoader;
/*    */ import net.minecraftforge.client.model.IModel;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.renderers.models.block.CustomCrystalModel;
/*    */ import thaumcraft.client.renderers.models.block.CustomPillarModel;
/*    */ import thaumcraft.client.renderers.models.block.CustomTaintFibreModel;
/*    */ import thaumcraft.client.renderers.models.block.CustomTubeModel;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class BlockModelLoader implements ICustomModelLoader
/*    */ {
/*    */   private IResourceManager resourceManager;
/*    */   
/*    */   public void func_110549_a(IResourceManager resourceManager)
/*    */   {
/* 21 */     this.resourceManager = resourceManager;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean accepts(ResourceLocation l)
/*    */   {
/* 28 */     return (l.func_110624_b().equalsIgnoreCase("Thaumcraft")) && (l.func_110623_a().startsWith("models/block/builtin/"));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public IModel loadModel(ResourceLocation l)
/*    */   {
/* 35 */     String r = l.func_110623_a().substring("models/block/builtin/".length());
/* 36 */     if (r.equals("tube")) {
/* 37 */       return new CustomTubeModel(this.resourceManager);
/*    */     }
/* 39 */     if (r.equals("pillar_normal")) {
/* 40 */       return new CustomPillarModel(this.resourceManager, CustomPillarModel.T_NORMAL);
/*    */     }
/* 42 */     if (r.equals("pillar_ancient")) {
/* 43 */       return new CustomPillarModel(this.resourceManager, CustomPillarModel.T_ANCIENT);
/*    */     }
/* 45 */     if (r.equals("pillar_eldritch")) {
/* 46 */       return new CustomPillarModel(this.resourceManager, CustomPillarModel.T_ELDRITCH);
/*    */     }
/* 48 */     if (r.startsWith("crystal")) {
/* 49 */       return new CustomCrystalModel(this.resourceManager);
/*    */     }
/* 51 */     if (r.equals("taint_fibre")) {
/* 52 */       return new CustomTaintFibreModel(this.resourceManager);
/*    */     }
/* 54 */     throw new RuntimeException("A builtin model '" + r + "' is not defined.");
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/block/BlockModelLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */