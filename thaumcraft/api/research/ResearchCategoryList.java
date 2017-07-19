/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.ResourceLocation;
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
/*    */ public class ResearchCategoryList
/*    */ {
/*    */   public int minDisplayColumn;
/*    */   public int minDisplayRow;
/*    */   public int maxDisplayColumn;
/*    */   public int maxDisplayRow;
/*    */   public ResourceLocation icon;
/*    */   public ResourceLocation background;
/*    */   public ResourceLocation background2;
/*    */   public String researchKey;
/*    */   
/*    */   public ResearchCategoryList(String researchKey, ResourceLocation icon, ResourceLocation background)
/*    */   {
/* 30 */     this.researchKey = researchKey;
/* 31 */     this.icon = icon;
/* 32 */     this.background = background;
/* 33 */     this.background2 = null;
/*    */   }
/*    */   
/*    */   public ResearchCategoryList(String researchKey, ResourceLocation icon, ResourceLocation background, ResourceLocation background2) {
/* 37 */     this.researchKey = researchKey;
/* 38 */     this.icon = icon;
/* 39 */     this.background = background;
/* 40 */     this.background2 = background2;
/*    */   }
/*    */   
/*    */ 
/* 44 */   public Map<String, ResearchItem> research = new HashMap();
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ResearchCategoryList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */