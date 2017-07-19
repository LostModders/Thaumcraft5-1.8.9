/*    */ package thaumcraft.api.golems.parts;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ 
/*    */ public class GolemHead
/*    */ {
/*  9 */   protected static GolemHead[] heads = new GolemHead[1];
/*    */   
/*    */   public byte id;
/*    */   
/*    */   public String key;
/*    */   
/*    */   public String[] research;
/*    */   public ResourceLocation icon;
/*    */   public Object[] components;
/*    */   public EnumGolemTrait[] traits;
/*    */   public IHeadFunction function;
/*    */   public PartModel model;
/*    */   
/*    */   public GolemHead(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags)
/*    */   {
/* 24 */     this.key = key;
/* 25 */     this.research = research;
/* 26 */     this.icon = icon;
/* 27 */     this.components = comp;
/* 28 */     this.traits = tags;
/* 29 */     this.model = model;
/* 30 */     this.function = null;
/*    */   }
/*    */   
/*    */   public GolemHead(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, IHeadFunction function, EnumGolemTrait[] tags) {
/* 34 */     this(key, research, icon, model, comp, tags);
/* 35 */     this.function = function;
/*    */   }
/*    */   
/* 38 */   private static byte lastID = 0;
/*    */   
/* 40 */   public static void register(GolemHead thing) { thing.id = lastID;
/* 41 */     lastID = (byte)(lastID + 1);
/*    */     
/* 43 */     if (thing.id >= heads.length) {
/* 44 */       GolemHead[] temp = new GolemHead[thing.id + 1];
/* 45 */       System.arraycopy(heads, 0, temp, 0, heads.length);
/* 46 */       heads = temp;
/*    */     }
/* 48 */     heads[thing.id] = thing;
/*    */   }
/*    */   
/*    */   public String getLocalizedName() {
/* 52 */     return StatCollector.func_74838_a("golem.head." + this.key.toLowerCase());
/*    */   }
/*    */   
/*    */   public String getLocalizedDescription() {
/* 56 */     return StatCollector.func_74838_a("golem.head.text." + this.key.toLowerCase());
/*    */   }
/*    */   
/*    */   public static GolemHead[] getHeads() {
/* 60 */     return heads;
/*    */   }
/*    */   
/*    */   public static abstract interface IHeadFunction
/*    */     extends IGenericFunction
/*    */   {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/parts/GolemHead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */