/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ public class FocusUpgradeType
/*     */ {
/*  10 */   public static FocusUpgradeType[] types = new FocusUpgradeType[20];
/*     */   
/*     */ 
/*     */   public short id;
/*     */   
/*     */ 
/*     */   public ResourceLocation icon;
/*     */   
/*     */ 
/*     */   public String name;
/*     */   
/*     */   public String text;
/*     */   
/*     */   public AspectList aspects;
/*     */   
/*  25 */   private static int lastID = 0;
/*     */   
/*     */   public FocusUpgradeType(ResourceLocation icon, String name, String text, AspectList aspects) {
/*  28 */     this.id = ((short)lastID);
/*  29 */     lastID += 1;
/*  30 */     this.icon = icon;
/*  31 */     this.name = name;
/*  32 */     this.text = text;
/*  33 */     this.aspects = aspects;
/*     */     
/*     */ 
/*  36 */     if (this.id >= types.length) {
/*  37 */       FocusUpgradeType[] temp = new FocusUpgradeType[this.id + 1];
/*  38 */       System.arraycopy(types, 0, temp, 0, types.length);
/*  39 */       types = temp;
/*     */     }
/*     */     
/*  42 */     types[this.id] = this;
/*     */   }
/*     */   
/*     */   public String getLocalizedName() {
/*  46 */     return StatCollector.func_74838_a(this.name);
/*     */   }
/*     */   
/*     */   public String getLocalizedText() {
/*  50 */     return StatCollector.func_74838_a(this.text);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/*  55 */     if ((obj instanceof FocusUpgradeType))
/*  56 */       return this.id == ((FocusUpgradeType)obj).id;
/*  57 */     return false;
/*     */   }
/*     */   
/*     */ 
/*  61 */   public static FocusUpgradeType potency = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/potency.png"), "focus.upgrade.potency.name", "focus.upgrade.potency.text", new AspectList().add(Aspect.AVERSION, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  66 */   public static FocusUpgradeType frugal = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/frugal.png"), "focus.upgrade.frugal.name", "focus.upgrade.frugal.text", new AspectList().add(Aspect.DESIRE, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  71 */   public static FocusUpgradeType treasure = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/treasure.png"), "focus.upgrade.treasure.name", "focus.upgrade.treasure.text", new AspectList().add(Aspect.DESIRE, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  76 */   public static FocusUpgradeType enlarge = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/enlarge.png"), "focus.upgrade.enlarge.name", "focus.upgrade.enlarge.text", new AspectList().add(Aspect.MOTION, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  81 */   public static FocusUpgradeType alchemistsfire = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/alchemistsfire.png"), "focus.upgrade.alchemistsfire.name", "focus.upgrade.alchemistsfire.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.WATER, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  86 */   public static FocusUpgradeType alchemistsfrost = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/alchemistsfrost.png"), "focus.upgrade.alchemistsfrost.name", "focus.upgrade.alchemistsfrost.text", new AspectList().add(Aspect.COLD, 1).add(Aspect.TRAP, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  91 */   public static FocusUpgradeType architect = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/architect.png"), "focus.upgrade.architect.name", "focus.upgrade.architect.text", new AspectList().add(Aspect.CRAFT, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  96 */   public static FocusUpgradeType extend = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/extend.png"), "focus.upgrade.extend.name", "focus.upgrade.extend.text", new AspectList().add(Aspect.EXCHANGE, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 101 */   public static FocusUpgradeType silktouch = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/silktouch.png"), "focus.upgrade.silktouch.name", "focus.upgrade.silktouch.text", new AspectList().add(Aspect.DESIRE, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/FocusUpgradeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */