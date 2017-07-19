/*    */ package thaumcraft.api;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OreDictionaryEntries
/*    */ {
/*    */   public static void initializeOreDictionary()
/*    */   {
/* 14 */     OreDictionary.registerOre("oreAmber", new ItemStack(BlocksTC.oreAmber));
/* 15 */     OreDictionary.registerOre("oreCinnabar", new ItemStack(BlocksTC.oreCinnabar));
/* 16 */     OreDictionary.registerOre("oreCrystalAir", new ItemStack(BlocksTC.crystalAir, 1, 32767));
/* 17 */     OreDictionary.registerOre("oreCrystalEarth", new ItemStack(BlocksTC.crystalEarth, 1, 32767));
/* 18 */     OreDictionary.registerOre("oreCrystalWater", new ItemStack(BlocksTC.crystalWater, 1, 32767));
/* 19 */     OreDictionary.registerOre("oreCrystalFire", new ItemStack(BlocksTC.crystalFire, 1, 32767));
/* 20 */     OreDictionary.registerOre("oreCrystalOrder", new ItemStack(BlocksTC.crystalOrder, 1, 32767));
/* 21 */     OreDictionary.registerOre("oreCrystalEntropy", new ItemStack(BlocksTC.crystalEntropy, 1, 32767));
/* 22 */     OreDictionary.registerOre("oreCrystalTaint", new ItemStack(BlocksTC.crystalTaint, 1, 32767));
/*    */     
/* 24 */     OreDictionary.registerOre("logWood", new ItemStack(BlocksTC.log, 1, 0));
/* 25 */     OreDictionary.registerOre("logWood", new ItemStack(BlocksTC.log, 1, 3));
/* 26 */     OreDictionary.registerOre("plankWood", new ItemStack(BlocksTC.plank, 1, 0));
/* 27 */     OreDictionary.registerOre("plankWood", new ItemStack(BlocksTC.plank, 1, 1));
/* 28 */     OreDictionary.registerOre("slabWood", new ItemStack(BlocksTC.slabWood, 1, 0));
/* 29 */     OreDictionary.registerOre("slabWood", new ItemStack(BlocksTC.slabWood, 1, 1));
/* 30 */     OreDictionary.registerOre("treeSapling", new ItemStack(BlocksTC.sapling, 1, 0));
/* 31 */     OreDictionary.registerOre("treeSapling", new ItemStack(BlocksTC.sapling, 1, 1));
/*    */     
/* 33 */     OreDictionary.registerOre("shardAir", new ItemStack(ItemsTC.shard, 1, 0));
/* 34 */     OreDictionary.registerOre("shardFire", new ItemStack(ItemsTC.shard, 1, 1));
/* 35 */     OreDictionary.registerOre("shardWater", new ItemStack(ItemsTC.shard, 1, 2));
/* 36 */     OreDictionary.registerOre("shardEarth", new ItemStack(ItemsTC.shard, 1, 3));
/* 37 */     OreDictionary.registerOre("shardOrder", new ItemStack(ItemsTC.shard, 1, 4));
/* 38 */     OreDictionary.registerOre("shardEntropy", new ItemStack(ItemsTC.shard, 1, 5));
/* 39 */     OreDictionary.registerOre("shardTainted", new ItemStack(ItemsTC.shard, 1, 6));
/* 40 */     OreDictionary.registerOre("shardBalanced", new ItemStack(ItemsTC.shard, 1, 7));
/* 41 */     OreDictionary.registerOre("nitor", new ItemStack(BlocksTC.nitor, 1, 32767));
/*    */     
/* 43 */     OreDictionary.registerOre("gemAmber", new ItemStack(ItemsTC.amber));
/* 44 */     OreDictionary.registerOre("quicksilver", new ItemStack(ItemsTC.quicksilver));
/* 45 */     OreDictionary.registerOre("nuggetGold", new ItemStack(ItemsTC.coin));
/*    */     
/* 47 */     OreDictionary.registerOre("nuggetIron", new ItemStack(ItemsTC.nuggets, 1, 0));
/* 48 */     OreDictionary.registerOre("nuggetCopper", new ItemStack(ItemsTC.nuggets, 1, 1));
/* 49 */     OreDictionary.registerOre("nuggetTin", new ItemStack(ItemsTC.nuggets, 1, 2));
/* 50 */     OreDictionary.registerOre("nuggetSilver", new ItemStack(ItemsTC.nuggets, 1, 3));
/* 51 */     OreDictionary.registerOre("nuggetLead", new ItemStack(ItemsTC.nuggets, 1, 4));
/* 52 */     OreDictionary.registerOre("nuggetQuicksilver", new ItemStack(ItemsTC.nuggets, 1, 5));
/* 53 */     OreDictionary.registerOre("nuggetThaumium", new ItemStack(ItemsTC.nuggets, 1, 6));
/* 54 */     OreDictionary.registerOre("nuggetVoid", new ItemStack(ItemsTC.nuggets, 1, 7));
/* 55 */     OreDictionary.registerOre("nuggetBrass", new ItemStack(ItemsTC.nuggets, 1, 8));
/*    */     
/* 57 */     OreDictionary.registerOre("ingotThaumium", new ItemStack(ItemsTC.ingots, 1, 0));
/* 58 */     OreDictionary.registerOre("ingotVoid", new ItemStack(ItemsTC.ingots, 1, 1));
/* 59 */     OreDictionary.registerOre("ingotBrass", new ItemStack(ItemsTC.ingots, 1, 2));
/*    */     
/* 61 */     OreDictionary.registerOre("blockThaumium", new ItemStack(BlocksTC.metal, 1, 0));
/* 62 */     OreDictionary.registerOre("blockVoid", new ItemStack(BlocksTC.metal, 1, 1));
/* 63 */     OreDictionary.registerOre("blockBrass", new ItemStack(BlocksTC.metal, 1, 4));
/*    */     
/* 65 */     OreDictionary.registerOre("plateIron", new ItemStack(ItemsTC.plate, 1, 1));
/* 66 */     OreDictionary.registerOre("gearBrass", new ItemStack(ItemsTC.gear, 1, 0));
/* 67 */     OreDictionary.registerOre("plateBrass", new ItemStack(ItemsTC.plate, 1, 0));
/* 68 */     OreDictionary.registerOre("gearThaumium", new ItemStack(ItemsTC.gear, 1, 1));
/* 69 */     OreDictionary.registerOre("plateThaumium", new ItemStack(ItemsTC.plate, 1, 2));
/* 70 */     OreDictionary.registerOre("gearVoid", new ItemStack(ItemsTC.gear, 1, 2));
/* 71 */     OreDictionary.registerOre("plateVoid", new ItemStack(ItemsTC.plate, 1, 3));
/*    */     
/* 73 */     OreDictionary.registerOre("clusterIron", new ItemStack(ItemsTC.clusters, 1, 0));
/* 74 */     OreDictionary.registerOre("clusterGold", new ItemStack(ItemsTC.clusters, 1, 1));
/* 75 */     OreDictionary.registerOre("clusterCopper", new ItemStack(ItemsTC.clusters, 1, 2));
/* 76 */     OreDictionary.registerOre("clusterTin", new ItemStack(ItemsTC.clusters, 1, 3));
/* 77 */     OreDictionary.registerOre("clusterSilver", new ItemStack(ItemsTC.clusters, 1, 4));
/* 78 */     OreDictionary.registerOre("clusterLead", new ItemStack(ItemsTC.clusters, 1, 5));
/* 79 */     OreDictionary.registerOre("clusterCinnabar", new ItemStack(ItemsTC.clusters, 1, 6));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/OreDictionaryEntries.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */