/*     */ package thaumcraft.api.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ 
/*     */ 
/*     */ public class InfusionRecipe
/*     */ {
/*     */   public AspectList aspects;
/*     */   public String[] research;
/*     */   public Object[] components;
/*     */   public Object recipeInput;
/*     */   public Object recipeOutput;
/*     */   public int instability;
/*     */   
/*     */   public InfusionRecipe(String research, Object output, int inst, AspectList aspects2, Object input, Object[] recipe)
/*     */   {
/*  23 */     this(new String[] { research }, output, inst, aspects2, input, recipe);
/*     */   }
/*     */   
/*     */   public InfusionRecipe(String[] research, Object output, int inst, AspectList aspects2, Object input, Object[] recipe) {
/*  27 */     this.research = research;
/*  28 */     this.recipeOutput = output;
/*  29 */     this.recipeInput = input;
/*  30 */     this.aspects = aspects2;
/*  31 */     this.components = recipe;
/*  32 */     this.instability = inst;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player)
/*     */   {
/*  40 */     if (getRecipeInput() == null) { return false;
/*     */     }
/*  42 */     if ((this.research != null) && (this.research[0].length() > 0) && (!ResearchHelper.isResearchComplete(player.func_70005_c_(), this.research))) {
/*  43 */       return false;
/*     */     }
/*     */     
/*  46 */     ItemStack i2 = central.func_77946_l();
/*  47 */     if (((getRecipeInput() instanceof ItemStack)) && (((ItemStack)getRecipeInput()).func_77952_i() == 32767))
/*     */     {
/*  49 */       i2.func_77964_b(32767);
/*     */     }
/*     */     
/*  52 */     if (!ThaumcraftApiHelper.areItemStacksEqualForCrafting(i2, getRecipeInput())) { return false;
/*     */     }
/*  54 */     ArrayList<ItemStack> ii = new ArrayList();
/*  55 */     for (ItemStack is : input) {
/*  56 */       ii.add(is.func_77946_l());
/*     */     }
/*     */     
/*  59 */     for (Object comp : getComponents()) {
/*  60 */       boolean b = false;
/*  61 */       for (int a = 0; a < ii.size(); a++) {
/*  62 */         i2 = ((ItemStack)ii.get(a)).func_77946_l();
/*  63 */         if (ThaumcraftApiHelper.areItemStacksEqualForCrafting(i2, comp)) {
/*  64 */           ii.remove(a);
/*  65 */           b = true;
/*  66 */           break;
/*     */         }
/*     */       }
/*  69 */       if (!b) return false;
/*     */     }
/*  71 */     return ii.size() == 0;
/*     */   }
/*     */   
/*     */   public String[] getResearch() {
/*  75 */     return this.research;
/*     */   }
/*     */   
/*     */   public Object getRecipeInput() {
/*  79 */     return this.recipeInput;
/*     */   }
/*     */   
/*     */   public Object[] getComponents() {
/*  83 */     return this.components;
/*     */   }
/*     */   
/*     */   public Object getRecipeOutput() {
/*  87 */     return this.recipeOutput;
/*     */   }
/*     */   
/*     */   public AspectList getAspects() {
/*  91 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public Object getRecipeOutput(EntityPlayer player, ItemStack input, ArrayList<ItemStack> comps)
/*     */   {
/*  96 */     return this.recipeOutput;
/*     */   }
/*     */   
/*     */   public AspectList getAspects(EntityPlayer player, ItemStack input, ArrayList<ItemStack> comps) {
/* 100 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public int getInstability(EntityPlayer player, ItemStack input, ArrayList<ItemStack> comps) {
/* 104 */     return this.instability;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/crafting/InfusionRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */