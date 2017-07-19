/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import net.minecraftforge.common.BiomeManager;
/*     */ import net.minecraftforge.common.BiomeManager.BiomeEntry;
/*     */ import net.minecraftforge.common.BiomeManager.BiomeType;
/*     */ import net.minecraftforge.fml.common.event.FMLInterModComms;
/*     */ import net.minecraftforge.fml.common.registry.EntityRegistry;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.EntityFallingTaint;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ import thaumcraft.common.entities.EntityPermanentItem;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.entities.EntityTaintSourceCloud;
/*     */ import thaumcraft.common.entities.construct.EntityNodeMagnet;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced;
/*     */ import thaumcraft.common.entities.construct.EntityTurretEldritch;
/*     */ import thaumcraft.common.entities.construct.EntityTurretFocus;
/*     */ import thaumcraft.common.entities.construct.golem.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.entities.monster.EntityBrainyZombie;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchCrab;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.EntityFireBat;
/*     */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*     */ import thaumcraft.common.entities.monster.EntityMindSpider;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.entities.monster.EntityWisp;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*     */ import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintChicken;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCow;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCreeper;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintPig;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintRabbit;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSheep;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSwarm;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintVillager;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintacle;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintacleSmall;
/*     */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*     */ import thaumcraft.common.entities.projectile.EntityBottleTaint;
/*     */ import thaumcraft.common.entities.projectile.EntityEldritchOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityEmber;
/*     */ import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityFrostShard;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemDart;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityGrapple;
/*     */ import thaumcraft.common.entities.projectile.EntityHomingShard;
/*     */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityShockOrb;
/*     */ import thaumcraft.common.lib.aura.EntityAuraNode;
/*     */ 
/*     */ public class ConfigEntities
/*     */ {
/*  76 */   public static int entWizardId = 190;
/*  77 */   public static int entBankerId = 191;
/*     */   
/*     */ 
/*     */ 
/*     */   public static void init()
/*     */   {
/*  83 */     int id = 0;
/*     */     
/*  85 */     EntityRegistry.registerModEntity(EntityAuraNode.class, "AuraNode", id++, Thaumcraft.instance, 160, 20, true);
/*  86 */     EntityRegistry.registerModEntity(EntityTaintSourceCloud.class, "TaintCloud", id++, Thaumcraft.instance, 64, 20, false);
/*  87 */     EntityRegistry.registerModEntity(thaumcraft.common.entities.EntityTaintSourceLightning.class, "TaintLightning", id++, Thaumcraft.instance, 64, 20, false);
/*  88 */     EntityRegistry.registerModEntity(EntityCultistPortalGreater.class, "CultistPortalGreater", id++, Thaumcraft.instance, 64, 20, false, 6842578, 32896);
/*  89 */     EntityRegistry.registerModEntity(EntityCultistPortalLesser.class, "CultistPortalLesser", id++, Thaumcraft.instance, 64, 20, false, 9438728, 6316242);
/*     */     
/*     */ 
/*  92 */     EntityRegistry.registerModEntity(EntitySpecialItem.class, "SpecialItem", id++, Thaumcraft.instance, 64, 20, true);
/*  93 */     EntityRegistry.registerModEntity(EntityPermanentItem.class, "PermanentItem", id++, Thaumcraft.instance, 64, 20, true);
/*  94 */     EntityRegistry.registerModEntity(EntityFollowingItem.class, "FollowItem", id++, Thaumcraft.instance, 64, 20, false);
/*  95 */     EntityRegistry.registerModEntity(EntityFallingTaint.class, "FallingTaint", id++, Thaumcraft.instance, 64, 3, true);
/*     */     
/*     */ 
/*  98 */     EntityRegistry.registerModEntity(EntityAlumentum.class, "Alumentum", id++, Thaumcraft.instance, 64, 20, true);
/*  99 */     EntityRegistry.registerModEntity(EntityPrimalOrb.class, "PrimalOrb", id++, Thaumcraft.instance, 64, 20, true);
/* 100 */     EntityRegistry.registerModEntity(EntityFrostShard.class, "FrostShard", id++, Thaumcraft.instance, 64, 20, true);
/* 101 */     EntityRegistry.registerModEntity(EntityPrimalArrow.class, "PrimalArrow", id++, Thaumcraft.instance, 64, 20, false);
/* 102 */     EntityRegistry.registerModEntity(EntityGolemDart.class, "GolemDart", id++, Thaumcraft.instance, 64, 20, false);
/* 103 */     EntityRegistry.registerModEntity(EntityPechBlast.class, "PechBlast", id++, Thaumcraft.instance, 64, 20, true);
/* 104 */     EntityRegistry.registerModEntity(EntityEldritchOrb.class, "EldritchOrb", id++, Thaumcraft.instance, 64, 20, true);
/* 105 */     EntityRegistry.registerModEntity(EntityBottleTaint.class, "BottleTaint", id++, Thaumcraft.instance, 64, 20, true);
/* 106 */     EntityRegistry.registerModEntity(EntityGolemOrb.class, "GolemOrb", id++, Thaumcraft.instance, 64, 3, true);
/* 107 */     EntityRegistry.registerModEntity(EntityHomingShard.class, "HomingShard", id++, Thaumcraft.instance, 64, 3, true);
/* 108 */     EntityRegistry.registerModEntity(EntityShockOrb.class, "ShockOrb", id++, Thaumcraft.instance, 64, 20, true);
/* 109 */     EntityRegistry.registerModEntity(EntityExplosiveOrb.class, "ExplosiveOrb", id++, Thaumcraft.instance, 64, 20, true);
/* 110 */     EntityRegistry.registerModEntity(EntityEmber.class, "Ember", id++, Thaumcraft.instance, 64, 20, true);
/* 111 */     EntityRegistry.registerModEntity(EntityGrapple.class, "Grapple", id++, Thaumcraft.instance, 64, 20, true);
/*     */     
/*     */ 
/* 114 */     EntityRegistry.registerModEntity(EntityTurretFocus.class, "TurretFocus", id++, Thaumcraft.instance, 64, 3, true);
/* 115 */     EntityRegistry.registerModEntity(EntityTurretCrossbow.class, "TurretBasic", id++, Thaumcraft.instance, 64, 3, true);
/* 116 */     EntityRegistry.registerModEntity(EntityTurretCrossbowAdvanced.class, "TurretAdvanced", id++, Thaumcraft.instance, 64, 3, true);
/* 117 */     EntityRegistry.registerModEntity(EntityTurretEldritch.class, "TurretEldritch", id++, Thaumcraft.instance, 64, 3, true);
/* 118 */     EntityRegistry.registerModEntity(EntityNodeMagnet.class, "NodeMagnet", id++, Thaumcraft.instance, 64, 3, true);
/*     */     
/*     */ 
/* 121 */     EntityRegistry.registerModEntity(EntityThaumcraftGolem.class, "Golem", id++, Thaumcraft.instance, 64, 3, true);
/*     */     
/*     */ 
/* 124 */     EntityRegistry.registerModEntity(EntityEldritchWarden.class, "EldritchWarden", id++, Thaumcraft.instance, 64, 3, true, 6842578, 8421504);
/* 125 */     EntityRegistry.registerModEntity(EntityEldritchGolem.class, "EldritchGolem", id++, Thaumcraft.instance, 64, 3, true, 6842578, 8947848);
/* 126 */     EntityRegistry.registerModEntity(EntityCultistLeader.class, "CultistLeader", id++, Thaumcraft.instance, 64, 3, true, 6842578, 9438728);
/* 127 */     EntityRegistry.registerModEntity(EntityTaintacleGiant.class, "TaintacleGiant", id++, Thaumcraft.instance, 96, 3, false, 6842578, 10618530);
/*     */     
/*     */ 
/* 130 */     EntityRegistry.registerModEntity(EntityBrainyZombie.class, "BrainyZombie", id++, Thaumcraft.instance, 64, 3, true, 49407, -16744448);
/* 131 */     EntityRegistry.registerModEntity(EntityGiantBrainyZombie.class, "GiantBrainyZombie", id++, Thaumcraft.instance, 64, 3, true, 49407, -16760832);
/* 132 */     EntityRegistry.registerModEntity(EntityWisp.class, "Wisp", id++, Thaumcraft.instance, 64, 3, false, 49407, -1);
/* 133 */     EntityRegistry.registerModEntity(EntityFireBat.class, "Firebat", id++, Thaumcraft.instance, 64, 3, false, 49407, -806354944);
/* 134 */     EntityRegistry.registerModEntity(EntityPech.class, "Pech", id++, Thaumcraft.instance, 64, 3, true, 49407, -12582848);
/* 135 */     EntityRegistry.registerModEntity(EntityMindSpider.class, "MindSpider", id++, Thaumcraft.instance, 64, 3, true, 4996656, 4473924);
/* 136 */     EntityRegistry.registerModEntity(EntityEldritchGuardian.class, "EldritchGuardian", id++, Thaumcraft.instance, 64, 3, true, 8421504, 0);
/* 137 */     EntityRegistry.registerModEntity(EntityCultistKnight.class, "CultistKnight", id++, Thaumcraft.instance, 64, 3, true, 9438728, 128);
/* 138 */     EntityRegistry.registerModEntity(EntityCultistCleric.class, "CultistCleric", id++, Thaumcraft.instance, 64, 3, true, 9438728, 8388608);
/* 139 */     EntityRegistry.registerModEntity(EntityEldritchCrab.class, "EldritchCrab", id++, Thaumcraft.instance, 64, 3, true, 8421504, 5570560);
/* 140 */     EntityRegistry.registerModEntity(thaumcraft.common.entities.monster.EntityInhabitedZombie.class, "InhabitedZombie", id++, Thaumcraft.instance, 64, 3, true, 8421504, 5570560);
/*     */     
/*     */ 
/* 143 */     EntityRegistry.registerModEntity(thaumcraft.common.entities.monster.EntityThaumicSlime.class, "ThaumSlime", id++, Thaumcraft.instance, 64, 3, true, 10618530, 33023);
/* 144 */     EntityRegistry.registerModEntity(EntityTaintCrawler.class, "TaintCrawler", id++, Thaumcraft.instance, 64, 3, true, 10618530, 3158064);
/* 145 */     EntityRegistry.registerModEntity(EntityTaintacle.class, "Taintacle", id++, Thaumcraft.instance, 64, 3, false, 10618530, 4469572);
/* 146 */     EntityRegistry.registerModEntity(EntityTaintacleSmall.class, "TaintacleTiny", id++, Thaumcraft.instance, 64, 3, false);
/* 147 */     EntityRegistry.registerModEntity(EntityTaintSwarm.class, "TaintSwarm", id++, Thaumcraft.instance, 64, 3, false, 10618530, 16744576);
/* 148 */     EntityRegistry.registerModEntity(EntityTaintChicken.class, "TaintedChicken", id++, Thaumcraft.instance, 64, 3, true, 10618530, 12632256);
/* 149 */     EntityRegistry.registerModEntity(EntityTaintRabbit.class, "TaintedRabbit", id++, Thaumcraft.instance, 64, 3, true, 10618530, 15777984);
/* 150 */     EntityRegistry.registerModEntity(EntityTaintCow.class, "TaintedCow", id++, Thaumcraft.instance, 64, 3, true, 10618530, 8272443);
/* 151 */     EntityRegistry.registerModEntity(EntityTaintCreeper.class, "TaintedCreeper", id++, Thaumcraft.instance, 64, 3, true, 10618530, 65280);
/* 152 */     EntityRegistry.registerModEntity(EntityTaintPig.class, "TaintedPig", id++, Thaumcraft.instance, 64, 3, true, 10618530, 15702511);
/* 153 */     EntityRegistry.registerModEntity(EntityTaintSheep.class, "TaintedSheep", id++, Thaumcraft.instance, 64, 3, true, 10618530, 8421504);
/* 154 */     EntityRegistry.registerModEntity(EntityTaintVillager.class, "TaintedVillager", id++, Thaumcraft.instance, 64, 3, true, 10618530, 65535);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void initEntitySpawns()
/*     */   {
/* 161 */     ArrayList<BiomeGenBase> biomes = new ArrayList();
/* 162 */     BiomeManager.BiomeEntry be; for (Iterator i$ = BiomeManager.getBiomes(BiomeManager.BiomeType.WARM).iterator(); i$.hasNext(); biomes.add(be.biome)) be = (BiomeManager.BiomeEntry)i$.next();
/* 163 */     BiomeManager.BiomeEntry be; for (Iterator i$ = BiomeManager.getBiomes(BiomeManager.BiomeType.COOL).iterator(); i$.hasNext(); biomes.add(be.biome)) be = (BiomeManager.BiomeEntry)i$.next();
/* 164 */     BiomeManager.BiomeEntry be; for (Iterator i$ = BiomeManager.getBiomes(BiomeManager.BiomeType.ICY).iterator(); i$.hasNext(); biomes.add(be.biome)) be = (BiomeManager.BiomeEntry)i$.next();
/* 165 */     BiomeManager.BiomeEntry be; for (Iterator i$ = BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT).iterator(); i$.hasNext(); biomes.add(be.biome)) { be = (BiomeManager.BiomeEntry)i$.next();
/*     */     }
/* 167 */     BiomeGenBase[] allBiomes = (BiomeGenBase[])biomes.toArray(new BiomeGenBase[] { null });
/*     */     
/* 169 */     if (Config.spawnAngryZombie) {
/* 170 */       for (BiomeGenBase bgb : biomes) {
/* 171 */         if (((bgb.func_76747_a(EnumCreatureType.MONSTER) != null ? 1 : 0) & (bgb.func_76747_a(EnumCreatureType.MONSTER).size() > 0 ? 1 : 0)) != 0)
/*     */         {
/* 173 */           EntityRegistry.addSpawn(EntityBrainyZombie.class, 10, 1, 1, EnumCreatureType.MONSTER, new BiomeGenBase[] { bgb });
/*     */         }
/*     */       }
/*     */     }
/* 177 */     if (Config.spawnPech) {
/* 178 */       for (BiomeGenBase bgb : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.MAGICAL)) {
/* 179 */         if (((bgb.func_76747_a(EnumCreatureType.MONSTER) != null ? 1 : 0) & (bgb.func_76747_a(EnumCreatureType.MONSTER).size() > 0 ? 1 : 0)) != 0)
/*     */         {
/* 181 */           EntityRegistry.addSpawn(EntityPech.class, 10, 1, 1, EnumCreatureType.MONSTER, new BiomeGenBase[] { bgb });
/*     */         }
/*     */       }
/*     */     }
/* 185 */     if (Config.spawnFireBat) {
/* 186 */       EntityRegistry.addSpawn(EntityFireBat.class, 10, 1, 2, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
/*     */       
/*     */ 
/* 189 */       Calendar calendar = Calendar.getInstance();
/* 190 */       calendar.setTimeInMillis(System.currentTimeMillis());
/* 191 */       if ((calendar.get(2) + 1 == 10) && (calendar.get(5) == 31)) {
/* 192 */         EntityRegistry.addSpawn(EntityFireBat.class, 5, 1, 2, EnumCreatureType.MONSTER, (BiomeGenBase[])biomes.toArray(allBiomes));
/*     */       }
/*     */     }
/*     */     
/* 196 */     if (Config.spawnWisp) {
/* 197 */       EntityRegistry.addSpawn(EntityWisp.class, 5, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
/*     */     }
/*     */     
/*     */ 
/* 201 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Zombie:0");
/* 202 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Spider:0");
/* 203 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Blaze:0");
/* 204 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Enderman:0");
/* 205 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Skeleton:0");
/* 206 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Witch:1");
/*     */     
/* 208 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.EldritchCrab:0");
/* 209 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.Taintacle:2");
/* 210 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.InhabitedZombie:3");
/*     */     
/* 212 */     championModWhitelist.put(EntityCultist.class, Integer.valueOf(1));
/*     */     
/* 214 */     championModWhitelist.put(EntityPech.class, Integer.valueOf(1));
/* 215 */     championModWhitelist.put(thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss.class, Integer.valueOf(200));
/*     */   }
/*     */   
/* 218 */   public static HashMap<Class, Integer> championModWhitelist = new HashMap();
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/config/ConfigEntities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */