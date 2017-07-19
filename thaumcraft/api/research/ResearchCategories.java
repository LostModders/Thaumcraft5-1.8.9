/*     */ package thaumcraft.api.research;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraftforge.fml.common.FMLLog;
/*     */ import org.apache.logging.log4j.Level;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResearchCategories
/*     */ {
/*  15 */   public static LinkedHashMap<String, ResearchCategoryList> researchCategories = new LinkedHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ResearchCategoryList getResearchList(String key)
/*     */   {
/*  22 */     return (ResearchCategoryList)researchCategories.get(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getCategoryName(String key)
/*     */   {
/*  31 */     return StatCollector.func_74838_a("tc.research_category." + key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ResearchItem getResearch(String key)
/*     */   {
/*  39 */     Collection rc = researchCategories.values();
/*  40 */     for (Object cat : rc) {
/*  41 */       Collection rl = ((ResearchCategoryList)cat).research.values();
/*  42 */       for (Object ri : rl) {
/*  43 */         if (((ResearchItem)ri).key.equals(key)) return (ResearchItem)ri;
/*     */       }
/*     */     }
/*  46 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerCategory(String key, String researchkey, ResourceLocation icon, ResourceLocation background)
/*     */   {
/*  58 */     if (getResearchList(key) == null) {
/*  59 */       ResearchCategoryList rl = new ResearchCategoryList(researchkey, icon, background);
/*  60 */       researchCategories.put(key, rl);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerCategory(String key, String researchkey, ResourceLocation icon, ResourceLocation background, ResourceLocation background2)
/*     */   {
/*  74 */     if (getResearchList(key) == null) {
/*  75 */       ResearchCategoryList rl = new ResearchCategoryList(researchkey, icon, background, background2);
/*  76 */       researchCategories.put(key, rl);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void addResearch(ResearchItem ri) {
/*  81 */     ResearchCategoryList rl = getResearchList(ri.category);
/*  82 */     if ((rl != null) && (!rl.research.containsKey(ri.key)))
/*     */     {
/*  84 */       if (!ri.isVirtual()) {
/*  85 */         for (ResearchItem rr : rl.research.values()) {
/*  86 */           if ((rr.displayColumn == ri.displayColumn) && (rr.displayRow == ri.displayRow)) {
/*  87 */             FMLLog.log(Level.FATAL, "[Thaumcraft] Research [" + ri.getName() + "] not added as it overlaps with existing research [" + rr.getName() + "]", new Object[0]);
/*  88 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  94 */       rl.research.put(ri.key, ri);
/*     */       
/*  96 */       if (ri.displayColumn < rl.minDisplayColumn)
/*     */       {
/*  98 */         rl.minDisplayColumn = ri.displayColumn;
/*     */       }
/*     */       
/* 101 */       if (ri.displayRow < rl.minDisplayRow)
/*     */       {
/* 103 */         rl.minDisplayRow = ri.displayRow;
/*     */       }
/*     */       
/* 106 */       if (ri.displayColumn > rl.maxDisplayColumn)
/*     */       {
/* 108 */         rl.maxDisplayColumn = ri.displayColumn;
/*     */       }
/*     */       
/* 111 */       if (ri.displayRow > rl.maxDisplayRow)
/*     */       {
/* 113 */         rl.maxDisplayRow = ri.displayRow;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ResearchCategories.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */