/*    */ package thaumcraft.client.lib.models;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
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
/*    */ public class Material
/*    */ {
/*    */   public String Name;
/*    */   public Vector3f AmbientColor;
/*    */   public Vector3f DiffuseColor;
/*    */   public Vector3f SpecularColor;
/*    */   public float SpecularCoefficient;
/*    */   public float Transparency;
/*    */   public int IlluminationModel;
/*    */   public String AmbientTextureMap;
/*    */   public String DiffuseTextureMap;
/*    */   public String SpecularTextureMap;
/*    */   public String SpecularHighlightTextureMap;
/*    */   public String BumpMap;
/*    */   public String DisplacementMap;
/*    */   public String StencilDecalMap;
/*    */   public String AlphaTextureMap;
/*    */   
/*    */   public Material(String materialName)
/*    */   {
/* 34 */     this.Name = materialName;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/models/Material.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */