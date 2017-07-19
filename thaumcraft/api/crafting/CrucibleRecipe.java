/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ 
/*    */ 
/*    */ public class CrucibleRecipe
/*    */ {
/*    */   private ItemStack recipeOutput;
/*    */   public Object catalyst;
/*    */   public AspectList aspects;
/*    */   public String[] research;
/*    */   public int hash;
/*    */   
/*    */   public CrucibleRecipe(String[] researchKey, ItemStack result, Object cat, AspectList tags)
/*    */   {
/* 22 */     this.recipeOutput = result;
/* 23 */     this.aspects = tags;
/* 24 */     this.research = researchKey;
/* 25 */     this.catalyst = cat;
/* 26 */     if ((cat instanceof String)) {
/* 27 */       this.catalyst = OreDictionary.getOres((String)cat);
/*    */     }
/* 29 */     String hc = "";
/* 30 */     for (String ss : this.research) hc = hc + ss;
/* 31 */     hc = hc + result.toString();
/* 32 */     for (Aspect tag : tags.getAspects()) {
/* 33 */       hc = hc + tag.getTag() + tags.getAmount(tag);
/*    */     }
/* 35 */     if ((cat instanceof ItemStack)) {
/* 36 */       hc = hc + ((ItemStack)cat).toString();
/*    */     }
/* 38 */     else if (((cat instanceof List)) && (((List)this.catalyst).size() > 0)) {
/* 39 */       for (ItemStack is : (List)this.catalyst) {
/* 40 */         hc = hc + is.toString();
/*    */       }
/*    */     }
/*    */     
/* 44 */     this.hash = hc.hashCode();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean matches(AspectList itags, ItemStack cat)
/*    */   {
/* 50 */     if (((this.catalyst instanceof ItemStack)) && (!OreDictionary.itemMatches((ItemStack)this.catalyst, cat, false))) {
/* 51 */       return false;
/*    */     }
/* 53 */     if (((this.catalyst instanceof List)) && (((List)this.catalyst).size() > 0)) {
/* 54 */       if (!ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { cat }, (List)this.catalyst))
/* 55 */         return false;
/*    */     }
/* 57 */     if (itags == null) return false;
/* 58 */     for (Aspect tag : this.aspects.getAspects()) {
/* 59 */       if (itags.getAmount(tag) < this.aspects.getAmount(tag)) return false;
/*    */     }
/* 61 */     return true;
/*    */   }
/*    */   
/*    */   public boolean catalystMatches(ItemStack cat) {
/* 65 */     if (((this.catalyst instanceof ItemStack)) && (OreDictionary.itemMatches((ItemStack)this.catalyst, cat, false))) {
/* 66 */       return true;
/*    */     }
/* 68 */     if (((this.catalyst instanceof List)) && (((List)this.catalyst).size() > 0)) {
/* 69 */       if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { cat }, (List)this.catalyst)) return true;
/*    */     }
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   public AspectList removeMatching(AspectList itags) {
/* 75 */     AspectList temptags = new AspectList();
/* 76 */     temptags.aspects.putAll(itags.aspects);
/*    */     
/* 78 */     for (Aspect tag : this.aspects.getAspects()) {
/* 79 */       temptags.remove(tag, this.aspects.getAmount(tag));
/*    */     }
/*    */     
/* 82 */     itags = temptags;
/* 83 */     return itags;
/*    */   }
/*    */   
/*    */   public ItemStack getRecipeOutput() {
/* 87 */     return this.recipeOutput;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/crafting/CrucibleRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */