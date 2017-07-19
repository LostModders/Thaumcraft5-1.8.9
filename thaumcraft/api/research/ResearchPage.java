/*     */ package thaumcraft.api.research;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ 
/*     */ public class ResearchPage
/*     */ {
/*     */   public static enum PageType
/*     */   {
/*  18 */     TEXT, 
/*  19 */     IMAGE, 
/*  20 */     CRUCIBLE_CRAFTING, 
/*  21 */     ARCANE_CRAFTING, 
/*  22 */     ASPECTS, 
/*  23 */     NORMAL_CRAFTING, 
/*  24 */     INFUSION_CRAFTING, 
/*  25 */     COMPOUND_CRAFTING, 
/*     */     
/*  27 */     SMELTING;
/*     */     
/*     */     private PageType() {} }
/*  30 */   public PageType type = PageType.TEXT;
/*     */   
/*  32 */   public String text = null;
/*  33 */   public String research = null;
/*  34 */   public ResourceLocation image = null;
/*  35 */   public AspectList aspects = null;
/*  36 */   public Object recipe = null;
/*  37 */   public Object recipeOutput = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(String text)
/*     */   {
/*  43 */     this.type = PageType.TEXT;
/*  44 */     this.text = text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(IRecipe recipe)
/*     */   {
/*  51 */     this.type = PageType.NORMAL_CRAFTING;
/*  52 */     this.recipe = recipe;
/*  53 */     this.recipeOutput = recipe.func_77571_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(IRecipe[] recipe)
/*     */   {
/*  60 */     this.type = PageType.NORMAL_CRAFTING;
/*  61 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(IArcaneRecipe[] recipe)
/*     */   {
/*  68 */     this.type = PageType.ARCANE_CRAFTING;
/*  69 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(CrucibleRecipe[] recipe)
/*     */   {
/*  76 */     this.type = PageType.CRUCIBLE_CRAFTING;
/*  77 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(InfusionRecipe[] recipe)
/*     */   {
/*  84 */     this.type = PageType.INFUSION_CRAFTING;
/*  85 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(List recipe)
/*     */   {
/*  92 */     this.type = PageType.COMPOUND_CRAFTING;
/*  93 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(IArcaneRecipe recipe)
/*     */   {
/* 100 */     this.type = PageType.ARCANE_CRAFTING;
/* 101 */     this.recipe = recipe;
/* 102 */     this.recipeOutput = recipe.func_77571_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(CrucibleRecipe recipe)
/*     */   {
/* 109 */     this.type = PageType.CRUCIBLE_CRAFTING;
/* 110 */     this.recipe = recipe;
/* 111 */     this.recipeOutput = recipe.getRecipeOutput();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(ItemStack input)
/*     */   {
/* 118 */     this.type = PageType.SMELTING;
/* 119 */     this.recipe = input;
/* 120 */     this.recipeOutput = FurnaceRecipes.func_77602_a().func_151395_a(input);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(InfusionRecipe recipe)
/*     */   {
/* 127 */     this.type = PageType.INFUSION_CRAFTING;
/* 128 */     this.recipe = recipe;
/* 129 */     if ((recipe.getRecipeOutput() instanceof ItemStack)) {
/* 130 */       this.recipeOutput = ((ItemStack)recipe.getRecipeOutput());
/*     */     } else {
/* 132 */       this.recipeOutput = recipe.getRecipeInput();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResearchPage(ResourceLocation image, String caption)
/*     */   {
/* 149 */     this.type = PageType.IMAGE;
/* 150 */     this.image = image;
/* 151 */     this.text = caption;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(AspectList as)
/*     */   {
/* 158 */     this.type = PageType.ASPECTS;
/* 159 */     this.aspects = as;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTranslatedText()
/*     */   {
/* 167 */     String ret = "";
/* 168 */     if (this.text != null) {
/* 169 */       ret = StatCollector.func_74838_a(this.text);
/* 170 */       if (ret.isEmpty()) ret = this.text;
/*     */     }
/* 172 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */   public ResearchPage setRequisite(String research)
/*     */   {
/* 178 */     this.research = research;
/* 179 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ResearchPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */