/*    */ package thaumcraft.api.golems;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public enum EnumGolemTrait
/*    */ {
/*  7 */   SMART(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_smart.png")), 
/*  8 */   DEFT(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_deft.png")), 
/*  9 */   CLUMSY(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_clumsy.png")), 
/* 10 */   FIGHTER(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_fighter.png")), 
/* 11 */   WHEELED(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_wheeled.png")), 
/* 12 */   FLYER(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_flyer.png")), 
/* 13 */   CLIMBER(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_climber.png")), 
/* 14 */   HEAVY(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_heavy.png")), 
/* 15 */   LIGHT(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_light.png")), 
/* 16 */   FRAGILE(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_fragile.png")), 
/* 17 */   REPAIR(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_repair.png")), 
/* 18 */   SCOUT(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_scout.png")), 
/* 19 */   ARMORED(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_armored.png")), 
/* 20 */   BRUTAL(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_brutal.png")), 
/* 21 */   FIREPROOF(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_fireproof.png")), 
/* 22 */   BREAKER(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_breaker.png")), 
/* 23 */   HAULER(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_hauler.png")), 
/* 24 */   RANGED(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_ranged.png")), 
/* 25 */   BLASTPROOF(new ResourceLocation("thaumcraft", "textures/misc/golem/tag_blastproof.png"));
/*    */   
/*    */   static {
/* 28 */     CLUMSY.opposite = DEFT;
/* 29 */     DEFT.opposite = CLUMSY;
/*    */     
/* 31 */     HEAVY.opposite = LIGHT;
/* 32 */     LIGHT.opposite = HEAVY;
/*    */     
/* 34 */     FRAGILE.opposite = ARMORED;
/* 35 */     ARMORED.opposite = FRAGILE;
/*    */   }
/*    */   
/*    */   public ResourceLocation icon;
/*    */   public EnumGolemTrait opposite;
/*    */   private EnumGolemTrait(ResourceLocation icon)
/*    */   {
/* 42 */     this.icon = icon;
/*    */   }
/*    */   
/*    */   public String getLocalizedName() {
/* 46 */     return net.minecraft.util.StatCollector.func_74838_a("golem.trait." + name().toLowerCase());
/*    */   }
/*    */   
/*    */   public String getLocalizedDescription() {
/* 50 */     return net.minecraft.util.StatCollector.func_74838_a("golem.trait.text." + name().toLowerCase());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/EnumGolemTrait.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */