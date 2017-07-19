/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import thaumcraft.api.aspects.Aspect;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WandCap
/*     */ {
/*     */   private String tag;
/*     */   private int craftCost;
/*     */   float baseCostModifier;
/*     */   int chargeBonus;
/*     */   List<Aspect> specialCostModifierAspects;
/*     */   float specialCostModifier;
/*     */   ResourceLocation texture;
/*     */   ItemStack item;
/*  57 */   public static LinkedHashMap<String, WandCap> caps = new LinkedHashMap();
/*     */   
/*     */   public WandCap(String tag, float discount, int charge, ItemStack item, int craftCost, ResourceLocation texture) {
/*  60 */     setTag(tag);
/*  61 */     this.baseCostModifier = discount;
/*  62 */     this.specialCostModifierAspects = null;
/*  63 */     this.texture = texture;
/*  64 */     this.item = item;
/*  65 */     this.chargeBonus = charge;
/*  66 */     setCraftCost(craftCost);
/*  67 */     caps.put(tag, this);
/*     */   }
/*     */   
/*     */   public WandCap(String tag, float discount, int charge, List<Aspect> specialAspects, float discountSpecial, ItemStack item, int craftCost, ResourceLocation texture) {
/*  71 */     setTag(tag);
/*  72 */     this.baseCostModifier = discount;
/*  73 */     this.specialCostModifierAspects = specialAspects;
/*  74 */     this.specialCostModifier = discountSpecial;
/*  75 */     this.texture = texture;
/*  76 */     this.item = item;
/*  77 */     this.chargeBonus = charge;
/*  78 */     setCraftCost(craftCost);
/*  79 */     caps.put(tag, this);
/*     */   }
/*     */   
/*     */   public float getBaseCostModifier() {
/*  83 */     return this.baseCostModifier;
/*     */   }
/*     */   
/*     */   public List<Aspect> getSpecialCostModifierAspects() {
/*  87 */     return this.specialCostModifierAspects;
/*     */   }
/*     */   
/*     */   public float getSpecialCostModifier() {
/*  91 */     return this.specialCostModifier;
/*     */   }
/*     */   
/*     */   public int getChargeBonus() {
/*  95 */     return this.chargeBonus;
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  99 */     return this.texture;
/*     */   }
/*     */   
/*     */   public void setTexture(ResourceLocation texture) {
/* 103 */     this.texture = texture;
/*     */   }
/*     */   
/*     */   public String getTag() {
/* 107 */     return this.tag;
/*     */   }
/*     */   
/*     */   public void setTag(String tag) {
/* 111 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public ItemStack getItem()
/*     */   {
/* 116 */     return this.item;
/*     */   }
/*     */   
/*     */   public void setItem(ItemStack item) {
/* 120 */     this.item = item;
/*     */   }
/*     */   
/*     */   public int getCraftCost() {
/* 124 */     return this.craftCost;
/*     */   }
/*     */   
/*     */   public void setCraftCost(int craftCost) {
/* 128 */     this.craftCost = craftCost;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getResearch()
/*     */   {
/* 135 */     return "CAP_" + getTag();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/WandCap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */