/*    */ package thaumcraft.client.lib.models;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObjModelLoader
/*    */   implements IModelCustomLoader
/*    */ {
/*    */   public String getType()
/*    */   {
/* 12 */     return "OBJ model";
/*    */   }
/*    */   
/* 15 */   private static final String[] types = { "obj" };
/*    */   
/*    */   public String[] getSuffixes()
/*    */   {
/* 19 */     return types;
/*    */   }
/*    */   
/*    */   public IModelCustom loadInstance(ResourceLocation resource)
/*    */     throws WavefrontObject.ModelFormatException
/*    */   {
/* 25 */     return new WavefrontObject(resource);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/ObjModelLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */