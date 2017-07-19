/*    */ package thaumcraft.api.golems.parts;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IRangedAttackMob;
/*    */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ 
/*    */ public class GolemArm
/*    */ {
/* 14 */   protected static GolemArm[] arms = new GolemArm[1];
/*    */   
/*    */   public byte id;
/*    */   
/*    */   public String key;
/*    */   
/*    */   public String[] research;
/*    */   public ResourceLocation icon;
/*    */   public Object[] components;
/*    */   public EnumGolemTrait[] traits;
/*    */   public IArmFunction function;
/*    */   public PartModel model;
/*    */   
/*    */   public GolemArm(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags)
/*    */   {
/* 29 */     this.key = key;
/* 30 */     this.research = research;
/* 31 */     this.icon = icon;
/* 32 */     this.components = comp;
/* 33 */     this.traits = tags;
/* 34 */     this.model = model;
/* 35 */     this.function = null;
/*    */   }
/*    */   
/*    */   public GolemArm(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, IArmFunction function, EnumGolemTrait[] tags) {
/* 39 */     this(key, research, icon, model, comp, tags);
/* 40 */     this.function = function;
/*    */   }
/*    */   
/* 43 */   private static byte lastID = 0;
/*    */   
/* 45 */   public static void register(GolemArm thing) { thing.id = lastID;
/* 46 */     lastID = (byte)(lastID + 1);
/*    */     
/* 48 */     if (thing.id >= arms.length) {
/* 49 */       GolemArm[] temp = new GolemArm[thing.id + 1];
/* 50 */       System.arraycopy(arms, 0, temp, 0, arms.length);
/* 51 */       arms = temp;
/*    */     }
/* 53 */     arms[thing.id] = thing;
/*    */   }
/*    */   
/*    */   public String getLocalizedName() {
/* 57 */     return StatCollector.func_74838_a("golem.arm." + this.key.toLowerCase());
/*    */   }
/*    */   
/*    */   public String getLocalizedDescription() {
/* 61 */     return StatCollector.func_74838_a("golem.arm.text." + this.key.toLowerCase());
/*    */   }
/*    */   
/*    */   public static GolemArm[] getArms() {
/* 65 */     return arms;
/*    */   }
/*    */   
/*    */   public static abstract interface IArmFunction
/*    */     extends IGenericFunction
/*    */   {
/*    */     public abstract void onMeleeAttack(IGolemAPI paramIGolemAPI, Entity paramEntity);
/*    */     
/*    */     public abstract void onRangedAttack(IGolemAPI paramIGolemAPI, EntityLivingBase paramEntityLivingBase, float paramFloat);
/*    */     
/*    */     public abstract EntityAIArrowAttack getRangedAttackAI(IRangedAttackMob paramIRangedAttackMob);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/parts/GolemArm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */