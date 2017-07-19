/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ public class WandRod
/*     */ {
/*     */   private String tag;
/*     */   private int craftCost;
/*     */   int capacity;
/*     */   protected ResourceLocation texture;
/*     */   ItemStack item;
/*     */   IWandRodOnUpdate onUpdate;
/*  49 */   private boolean isStaff = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  54 */   private boolean potencyBonus = false;
/*     */   
/*  56 */   public static LinkedHashMap<String, WandRod> rods = new LinkedHashMap();
/*     */   
/*     */   public static WandRod getRod(String tag) {
/*  59 */     return (WandRod)rods.get(tag);
/*     */   }
/*     */   
/*     */   public WandRod(String tag, int capacity, ItemStack item, int craftCost, ResourceLocation texture) {
/*  63 */     setTag(tag);
/*  64 */     this.capacity = capacity;
/*  65 */     this.texture = texture;
/*  66 */     this.item = item;
/*  67 */     setCraftCost(craftCost);
/*  68 */     rods.put(tag, this);
/*     */   }
/*     */   
/*     */   public WandRod(String tag, int capacity, ItemStack item, int craftCost, IWandRodOnUpdate onUpdate, ResourceLocation texture) {
/*  72 */     setTag(tag);
/*  73 */     this.capacity = capacity;
/*  74 */     this.texture = texture;
/*  75 */     this.item = item;
/*  76 */     setCraftCost(craftCost);
/*  77 */     rods.put(tag, this);
/*  78 */     this.onUpdate = onUpdate;
/*     */   }
/*     */   
/*     */   public String getTag() {
/*  82 */     return this.tag;
/*     */   }
/*     */   
/*     */   public void setTag(String tag) {
/*  86 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public int getCapacity() {
/*  90 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public void setCapacity(int capacity) {
/*  94 */     this.capacity = capacity;
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  98 */     return this.texture;
/*     */   }
/*     */   
/*     */   public void setTexture(ResourceLocation texture) {
/* 102 */     this.texture = texture;
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/* 106 */     return this.item;
/*     */   }
/*     */   
/*     */   public void setItem(ItemStack item) {
/* 110 */     this.item = item;
/*     */   }
/*     */   
/*     */   public int getCraftCost() {
/* 114 */     return this.craftCost;
/*     */   }
/*     */   
/*     */   public void setCraftCost(int craftCost) {
/* 118 */     this.craftCost = craftCost;
/*     */   }
/*     */   
/*     */   public IWandRodOnUpdate getOnUpdate() {
/* 122 */     return this.onUpdate;
/*     */   }
/*     */   
/*     */   public void setOnUpdate(IWandRodOnUpdate onUpdate) {
/* 126 */     this.onUpdate = onUpdate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getResearch()
/*     */   {
/* 133 */     return "ROD_" + getTag();
/*     */   }
/*     */   
/*     */   public boolean isStaff() {
/* 137 */     return this.isStaff;
/*     */   }
/*     */   
/*     */   public void setStaff(boolean isStaff) {
/* 141 */     this.isStaff = isStaff;
/*     */   }
/*     */   
/*     */   public boolean hasPotencyBonus() {
/* 145 */     return this.potencyBonus;
/*     */   }
/*     */   
/*     */   public void setPotencyBonus(boolean potencyBonus) {
/* 149 */     this.potencyBonus = potencyBonus;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/WandRod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */