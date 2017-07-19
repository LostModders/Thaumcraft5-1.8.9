/*     */ package thaumcraft.api.aspects;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.apache.commons.lang3.text.WordUtils;
/*     */ import thaumcraft.api.research.ScanAspect;
/*     */ import thaumcraft.api.research.ScanningManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Aspect
/*     */ {
/*     */   String tag;
/*     */   Aspect[] components;
/*     */   int color;
/*     */   private String chatcolor;
/*     */   ResourceLocation image;
/*     */   int blend;
/*  28 */   public static HashMap<Integer, Aspect> mixList = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Aspect(String tag, int color, Aspect[] components, ResourceLocation image, int blend)
/*     */   {
/*  39 */     if (aspects.containsKey(tag)) throw new IllegalArgumentException(tag + " already registered!");
/*  40 */     this.tag = tag;
/*  41 */     this.components = components;
/*  42 */     this.color = color;
/*  43 */     this.image = image;
/*  44 */     this.blend = blend;
/*  45 */     aspects.put(tag, this);
/*  46 */     ScanningManager.addScannableThing(new ScanAspect("!" + tag, this));
/*  47 */     if (components != null) {
/*  48 */       int h = (components[0].getTag() + components[1].getTag()).hashCode();
/*  49 */       mixList.put(Integer.valueOf(h), this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect(String tag, int color, Aspect[] components)
/*     */   {
/*  57 */     this(tag, color, components, new ResourceLocation("thaumcraft", "textures/aspects/" + tag.toLowerCase() + ".png"), 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect(String tag, int color, Aspect[] components, int blend)
/*     */   {
/*  64 */     this(tag, color, components, new ResourceLocation("thaumcraft", "textures/aspects/" + tag.toLowerCase() + ".png"), blend);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Aspect(String tag, int color, String chatcolor, int blend)
/*     */   {
/*  72 */     this(tag, color, (Aspect[])null, blend);
/*  73 */     setChatcolor(chatcolor);
/*     */   }
/*     */   
/*     */   public int getColor() {
/*  77 */     return this.color;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  81 */     return WordUtils.capitalizeFully(this.tag);
/*     */   }
/*     */   
/*     */   public String getLocalizedDescription() {
/*  85 */     return StatCollector.func_74838_a("tc.aspect." + this.tag);
/*     */   }
/*     */   
/*     */   public String getTag() {
/*  89 */     return this.tag;
/*     */   }
/*     */   
/*     */   public void setTag(String tag) {
/*  93 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public Aspect[] getComponents() {
/*  97 */     return this.components;
/*     */   }
/*     */   
/*     */   public void setComponents(Aspect[] components) {
/* 101 */     this.components = components;
/*     */   }
/*     */   
/*     */   public ResourceLocation getImage() {
/* 105 */     return this.image;
/*     */   }
/*     */   
/*     */   public static Aspect getAspect(String tag) {
/* 109 */     return (Aspect)aspects.get(tag);
/*     */   }
/*     */   
/*     */   public int getBlend() {
/* 113 */     return this.blend;
/*     */   }
/*     */   
/*     */   public void setBlend(int blend) {
/* 117 */     this.blend = blend;
/*     */   }
/*     */   
/*     */   public boolean isPrimal() {
/* 121 */     return (getComponents() == null) || (getComponents().length != 2);
/*     */   }
/*     */   
/*     */   public static ArrayList<Aspect> getPrimalAspects()
/*     */   {
/* 126 */     ArrayList<Aspect> primals = new ArrayList();
/* 127 */     Collection<Aspect> pa = aspects.values();
/* 128 */     for (Aspect aspect : pa) {
/* 129 */       if (aspect.isPrimal()) primals.add(aspect);
/*     */     }
/* 131 */     return primals;
/*     */   }
/*     */   
/*     */   public static ArrayList<Aspect> getCompoundAspects() {
/* 135 */     ArrayList<Aspect> compounds = new ArrayList();
/* 136 */     Collection<Aspect> pa = aspects.values();
/* 137 */     for (Aspect aspect : pa) {
/* 138 */       if (!aspect.isPrimal()) compounds.add(aspect);
/*     */     }
/* 140 */     return compounds;
/*     */   }
/*     */   
/*     */   public String getChatcolor() {
/* 144 */     return this.chatcolor;
/*     */   }
/*     */   
/*     */   public void setChatcolor(String chatcolor) {
/* 148 */     this.chatcolor = chatcolor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 153 */   public static LinkedHashMap<String, Aspect> aspects = new LinkedHashMap();
/*     */   
/*     */ 
/* 156 */   public static final Aspect AIR = new Aspect("aer", 16777086, "e", 1);
/* 157 */   public static final Aspect EARTH = new Aspect("terra", 5685248, "2", 1);
/* 158 */   public static final Aspect FIRE = new Aspect("ignis", 16734721, "c", 1);
/* 159 */   public static final Aspect WATER = new Aspect("aqua", 3986684, "3", 1);
/* 160 */   public static final Aspect ORDER = new Aspect("ordo", 14013676, "7", 1);
/* 161 */   public static final Aspect ENTROPY = new Aspect("perditio", 4210752, "8", 771);
/*     */   
/*     */ 
/* 164 */   public static final Aspect VOID = new Aspect("vacuos", 8947848, new Aspect[] { AIR, ENTROPY }, 771);
/* 165 */   public static final Aspect LIGHT = new Aspect("lux", 16766341, new Aspect[] { AIR, FIRE });
/* 166 */   public static final Aspect MOTION = new Aspect("motus", 13487348, new Aspect[] { AIR, ORDER });
/* 167 */   public static final Aspect COLD = new Aspect("gelum", 14811135, new Aspect[] { FIRE, ENTROPY });
/* 168 */   public static final Aspect CRYSTAL = new Aspect("vitreus", 8454143, new Aspect[] { EARTH, AIR });
/* 169 */   public static final Aspect METAL = new Aspect("metallum", 11908557, new Aspect[] { EARTH, ORDER });
/* 170 */   public static final Aspect LIFE = new Aspect("victus", 14548997, new Aspect[] { EARTH, WATER });
/* 171 */   public static final Aspect DEATH = new Aspect("mortuus", 8943496, new Aspect[] { WATER, ENTROPY });
/* 172 */   public static final Aspect ENERGY = new Aspect("potentia", 12648447, new Aspect[] { ORDER, FIRE });
/* 173 */   public static final Aspect EXCHANGE = new Aspect("permutatio", 5735255, new Aspect[] { ENTROPY, ORDER });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 182 */   public static final Aspect AURA = new Aspect("auram", 16761087, new Aspect[] { ENERGY, AIR });
/* 183 */   public static final Aspect FLUX = new Aspect("vitium", 8388736, new Aspect[] { ENTROPY, ENERGY });
/* 184 */   public static final Aspect DARKNESS = new Aspect("tenebrae", 2236962, new Aspect[] { VOID, LIGHT });
/* 185 */   public static final Aspect ELDRITCH = new Aspect("alienis", 8409216, new Aspect[] { VOID, DARKNESS });
/* 186 */   public static final Aspect FLIGHT = new Aspect("volatus", 15198167, new Aspect[] { AIR, MOTION });
/* 187 */   public static final Aspect PLANT = new Aspect("herba", 109568, new Aspect[] { LIFE, EARTH });
/*     */   
/* 189 */   public static final Aspect TOOL = new Aspect("instrumentum", 4210926, new Aspect[] { METAL, ENERGY });
/* 190 */   public static final Aspect CRAFT = new Aspect("fabrico", 8428928, new Aspect[] { EXCHANGE, TOOL });
/* 191 */   public static final Aspect MECHANISM = new Aspect("machina", 8421536, new Aspect[] { MOTION, TOOL });
/* 192 */   public static final Aspect TRAP = new Aspect("vinculum", 10125440, new Aspect[] { MOTION, ENTROPY });
/*     */   
/* 194 */   public static final Aspect SOUL = new Aspect("spiritus", 15461371, new Aspect[] { LIFE, DEATH });
/* 195 */   public static final Aspect MIND = new Aspect("cognitio", 16761523, new Aspect[] { FIRE, SOUL });
/* 196 */   public static final Aspect SENSES = new Aspect("sensus", 1038847, new Aspect[] { AIR, SOUL });
/* 197 */   public static final Aspect AVERSION = new Aspect("aversio", 12603472, new Aspect[] { SOUL, ENTROPY });
/* 198 */   public static final Aspect PROTECT = new Aspect("praemunio", 49344, new Aspect[] { SOUL, EARTH });
/* 199 */   public static final Aspect DESIRE = new Aspect("desiderium", 15121988, new Aspect[] { SOUL, VOID });
/*     */   
/* 201 */   public static final Aspect UNDEAD = new Aspect("exanimis", 3817472, new Aspect[] { MOTION, DEATH });
/* 202 */   public static final Aspect BEAST = new Aspect("bestia", 10445833, new Aspect[] { MOTION, LIFE });
/* 203 */   public static final Aspect MAN = new Aspect("humanus", 16766912, new Aspect[] { SOUL, LIFE });
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/aspects/Aspect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */