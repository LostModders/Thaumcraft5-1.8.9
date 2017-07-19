/*     */ package thaumcraft.api.research;
/*     */ 
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
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
/*     */ public class ResearchItem
/*     */ {
/*     */   public final String key;
/*     */   public final String category;
/*     */   public final AspectList tags;
/*  29 */   public String[] parents = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  34 */   public String[] parentsHidden = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  39 */   public String[] siblings = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int displayColumn;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int displayRow;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final ItemStack[] icon_item;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final ResourceLocation[] icon_resource;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int complexity;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isSpecial;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isSecondary;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isRound;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isStub;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isVirtual;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isHidden;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isAutoUnlock;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isFlipped;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 111 */   private ResearchPage[] pages = null;
/*     */   
/*     */   public ResearchItem(String key, String category)
/*     */   {
/* 115 */     this.key = key;
/* 116 */     this.category = category;
/* 117 */     this.tags = new AspectList();
/* 118 */     this.icon_resource = null;
/* 119 */     this.icon_item = null;
/* 120 */     this.displayColumn = 0;
/* 121 */     this.displayRow = 0;
/* 122 */     this.isVirtual = true;
/*     */   }
/*     */   
/*     */   public ResearchItem(String key, String category, AspectList tags, int col, int row, int complex, Object... icon)
/*     */   {
/* 127 */     this.key = key;
/* 128 */     this.category = category;
/* 129 */     this.tags = tags;
/* 130 */     if ((icon[0] instanceof ResourceLocation)) {
/* 131 */       ResourceLocation[] t = new ResourceLocation[icon.length];
/* 132 */       System.arraycopy(icon, 0, t, 0, icon.length);
/* 133 */       this.icon_resource = t;
/* 134 */     } else { this.icon_resource = null; }
/* 135 */     if ((icon[0] instanceof ItemStack)) {
/* 136 */       ItemStack[] t = new ItemStack[icon.length];
/* 137 */       System.arraycopy(icon, 0, t, 0, icon.length);
/* 138 */       this.icon_item = t;
/* 139 */     } else { this.icon_item = null; }
/* 140 */     this.displayColumn = col;
/* 141 */     this.displayRow = row;
/* 142 */     this.complexity = complex;
/* 143 */     if (this.complexity < 1) this.complexity = 1;
/* 144 */     if (this.complexity > 3) { this.complexity = 3;
/*     */     }
/*     */   }
/*     */   
/*     */   public ResearchItem setSpecial()
/*     */   {
/* 150 */     this.isSpecial = true;
/* 151 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setStub()
/*     */   {
/* 156 */     this.isStub = true;
/* 157 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public ResearchItem setHidden()
/*     */   {
/* 163 */     this.isHidden = true;
/* 164 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setParents(String... par)
/*     */   {
/* 169 */     this.parents = par;
/* 170 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchItem setParentsHidden(String... par)
/*     */   {
/* 177 */     this.parentsHidden = par;
/* 178 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setSiblings(String... sib)
/*     */   {
/* 183 */     this.siblings = sib;
/* 184 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setPages(ResearchPage... par)
/*     */   {
/* 189 */     this.pages = par;
/* 190 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchPage[] getPages() {
/* 194 */     return this.pages;
/*     */   }
/*     */   
/*     */   public ResearchItem registerResearchItem()
/*     */   {
/* 199 */     ResearchCategories.addResearch(this);
/* 200 */     return this;
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/* 205 */     return StatCollector.func_74838_a("tc.research_name." + this.key);
/*     */   }
/*     */   
/*     */   public String getText()
/*     */   {
/* 210 */     return StatCollector.func_74838_a("tc.research_text." + this.key);
/*     */   }
/*     */   
/*     */   public boolean isSpecial()
/*     */   {
/* 215 */     return this.isSpecial;
/*     */   }
/*     */   
/*     */   public boolean isStub()
/*     */   {
/* 220 */     return this.isStub;
/*     */   }
/*     */   
/*     */   public boolean isHidden()
/*     */   {
/* 225 */     return this.isHidden;
/*     */   }
/*     */   
/*     */   public boolean isVirtual()
/*     */   {
/* 230 */     return this.isVirtual;
/*     */   }
/*     */   
/*     */   public boolean isAutoUnlock() {
/* 234 */     return this.isAutoUnlock;
/*     */   }
/*     */   
/*     */   public ResearchItem setAutoUnlock()
/*     */   {
/* 239 */     this.isAutoUnlock = true;
/* 240 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isRound() {
/* 244 */     return this.isRound;
/*     */   }
/*     */   
/*     */   public ResearchItem setRound() {
/* 248 */     this.isRound = true;
/* 249 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setSecondary()
/*     */   {
/* 254 */     this.isSecondary = true;
/* 255 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isSecondary() {
/* 259 */     return this.isSecondary;
/*     */   }
/*     */   
/*     */   public int getComplexity() {
/* 263 */     return this.complexity;
/*     */   }
/*     */   
/*     */   public ResearchItem setFlipped() {
/* 267 */     this.isFlipped = true;
/* 268 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isFlipped() {
/* 272 */     return this.isFlipped;
/*     */   }
/*     */   
/*     */   public ResearchItem setComplexity(int complexity) {
/* 276 */     this.complexity = complexity;
/* 277 */     return this;
/*     */   }
/*     */   
/*     */   public int getExperience() {
/* 281 */     if ((this.tags != null) && (this.tags.visSize() > 0)) {
/* 282 */       return Math.max(1, (int)Math.sqrt(this.tags.visSize()));
/*     */     }
/* 284 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect getResearchPrimaryTag()
/*     */   {
/* 291 */     Aspect aspect = null;
/* 292 */     int highest = 0;
/* 293 */     if (this.tags != null) {
/* 294 */       for (Aspect tag : this.tags.getAspects())
/* 295 */         if (this.tags.getAmount(tag) > highest) {
/* 296 */           aspect = tag;
/* 297 */           highest = this.tags.getAmount(tag);
/*     */         }
/*     */     }
/* 300 */     return aspect;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ResearchItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */