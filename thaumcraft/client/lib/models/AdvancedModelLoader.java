/*    */ package thaumcraft.client.lib.models;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.FMLLog;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class AdvancedModelLoader
/*    */ {
/* 25 */   private static Map<String, IModelCustomLoader> instances = ;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void registerModelHandler(IModelCustomLoader modelHandler)
/*    */   {
/* 33 */     for (String suffix : modelHandler.getSuffixes())
/*    */     {
/* 35 */       instances.put(suffix, modelHandler);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static IModelCustom loadModel(ResourceLocation resource)
/*    */     throws IllegalArgumentException, WavefrontObject.ModelFormatException
/*    */   {
/* 48 */     String name = resource.func_110623_a();
/* 49 */     int i = name.lastIndexOf('.');
/* 50 */     if (i == -1)
/*    */     {
/* 52 */       FMLLog.severe("The resource name %s is not valid", new Object[] { resource });
/* 53 */       throw new IllegalArgumentException("The resource name is not valid");
/*    */     }
/* 55 */     String suffix = name.substring(i + 1);
/* 56 */     IModelCustomLoader loader = (IModelCustomLoader)instances.get(suffix);
/* 57 */     if (loader == null)
/*    */     {
/* 59 */       FMLLog.severe("The resource name %s is not supported", new Object[] { resource });
/* 60 */       throw new IllegalArgumentException("The resource name is not supported");
/*    */     }
/*    */     
/* 63 */     return loader.loadInstance(resource);
/*    */   }
/*    */   
/*    */   public static Collection<String> getSupportedSuffixes()
/*    */   {
/* 68 */     return instances.keySet();
/*    */   }
/*    */   
/*    */ 
/*    */   static
/*    */   {
/* 74 */     registerModelHandler(new ObjModelLoader());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/AdvancedModelLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */