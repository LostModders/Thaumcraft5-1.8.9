/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.EntityMindSpider;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketMiscEvent;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*     */ import thaumcraft.common.lib.potions.PotionDeathGaze;
/*     */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*     */ import thaumcraft.common.lib.potions.PotionSunScorned;
/*     */ import thaumcraft.common.lib.potions.PotionThaumarhia;
/*     */ import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ 
/*     */ public class WarpEvents
/*     */ {
/*     */   public static void checkWarpEvent(EntityPlayer player)
/*     */   {
/*  45 */     int warp = Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_());
/*  46 */     int actualwarp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_()) + Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
/*     */     
/*     */ 
/*  49 */     int gearWarp = getWarpFromGear(player);
/*  50 */     warp += gearWarp;
/*     */     
/*  52 */     int warpCounter = Thaumcraft.proxy.getPlayerKnowledge().getWarpCounter(player.func_70005_c_());
/*     */     
/*  54 */     int r = player.field_70170_p.field_73012_v.nextInt(100);
/*     */     
/*  56 */     if ((warpCounter > 0) && (warp > 0) && (r <= Math.sqrt(warpCounter)))
/*     */     {
/*  58 */       warp = Math.min(100, (warp + warp + warpCounter) / 3);
/*     */       
/*  60 */       warpCounter = (int)(warpCounter - Math.max(5.0D, Math.sqrt(warpCounter) * 2.0D - gearWarp * 2));
/*     */       
/*  62 */       Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), warpCounter);
/*     */       
/*  64 */       int eff = player.field_70170_p.field_73012_v.nextInt(warp);
/*     */       
/*     */ 
/*  67 */       ItemStack helm = player.field_71071_by.field_70460_b[3];
/*  68 */       if ((helm != null) && ((helm.func_77973_b() instanceof thaumcraft.common.items.armor.ItemFortressArmor)) && 
/*  69 */         (helm.func_77942_o()) && (helm.func_77978_p().func_74764_b("mask")) && (helm.func_77978_p().func_74762_e("mask") == 0))
/*     */       {
/*     */ 
/*  72 */         eff -= 2 + player.field_70170_p.field_73012_v.nextInt(4);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  77 */       PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((short)0), (EntityPlayerMP)player);
/*     */       
/*     */ 
/*     */ 
/*  81 */       if (eff > 0)
/*     */       {
/*     */ 
/*     */ 
/*  85 */         if (eff <= 4) {
/*  86 */           if (!Config.nostress) { player.field_70170_p.func_72956_a(player, "creeper.primed", 1.0F, 0.5F);
/*     */           }
/*     */         }
/*  89 */         else if (eff <= 8) {
/*  90 */           if (!Config.nostress) { player.field_70170_p.func_72908_a(player.field_70165_t + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 10.0F, player.field_70163_u + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 10.0F, player.field_70161_v + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 10.0F, "random.explode", 4.0F, (1.0F + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*  97 */         else if (eff <= 12) {
/*  98 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.11")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 103 */         else if (eff <= 16) {
/* 104 */           PotionEffect pe = new PotionEffect(PotionVisExhaust.instance.func_76396_c(), 5000, Math.min(3, warp / 15), true, true);
/* 105 */           pe.getCurativeItems().clear();
/*     */           try {
/* 107 */             player.func_70690_d(pe);
/*     */           } catch (Exception e) {
/* 109 */             e.printStackTrace();
/*     */           }
/*     */           
/* 112 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.1")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 117 */         else if (eff <= 20) {
/* 118 */           PotionEffect pe = new PotionEffect(PotionThaumarhia.instance.func_76396_c(), Math.min(32000, 10 * warp), 0, true, true);
/* 119 */           pe.getCurativeItems().clear();
/*     */           try {
/* 121 */             player.func_70690_d(pe);
/*     */           } catch (Exception e) {
/* 123 */             e.printStackTrace();
/*     */           }
/*     */           
/* 126 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.15")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 131 */         else if (eff <= 24) {
/* 132 */           PotionEffect pe = new PotionEffect(PotionUnnaturalHunger.instance.func_76396_c(), 5000, Math.min(3, warp / 15), true, true);
/* 133 */           pe.getCurativeItems().clear();
/* 134 */           pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
/* 135 */           pe.addCurativeItem(new ItemStack(ItemsTC.brain));
/*     */           try {
/* 137 */             player.func_70690_d(pe);
/*     */           } catch (Exception e) {
/* 139 */             e.printStackTrace();
/*     */           }
/*     */           
/* 142 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.2")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 147 */         else if (eff <= 28) {
/* 148 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.12")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 153 */         else if (eff <= 32) {
/* 154 */           spawnMist(player, warp, 1);
/*     */ 
/*     */         }
/* 157 */         else if (eff <= 36) {
/*     */           try {
/* 159 */             player.func_70690_d(new PotionEffect(thaumcraft.common.lib.potions.PotionBlurredVision.instance.func_76396_c(), Math.min(32000, 10 * warp), 0, true, true));
/*     */           } catch (Exception e) {
/* 161 */             e.printStackTrace();
/*     */           }
/*     */           
/*     */         }
/* 165 */         else if (eff <= 40) {
/* 166 */           PotionEffect pe = new PotionEffect(PotionSunScorned.instance.func_76396_c(), 5000, Math.min(3, warp / 15), true, true);
/* 167 */           pe.getCurativeItems().clear();
/*     */           try {
/* 169 */             player.func_70690_d(pe);
/*     */           } catch (Exception e) {
/* 171 */             e.printStackTrace();
/*     */           }
/*     */           
/* 174 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.5")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 179 */         else if (eff <= 44) {
/*     */           try {
/* 181 */             player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 1200, Math.min(3, warp / 15), true, true));
/*     */           } catch (Exception e) {
/* 183 */             e.printStackTrace();
/*     */           }
/* 185 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.9")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 190 */         else if (eff <= 48) {
/* 191 */           PotionEffect pe = new PotionEffect(PotionInfectiousVisExhaust.instance.func_76396_c(), 6000, Math.min(3, warp / 15));
/* 192 */           pe.getCurativeItems().clear();
/*     */           try {
/* 194 */             player.func_70690_d(pe);
/*     */           } catch (Exception e) {
/* 196 */             e.printStackTrace();
/*     */           }
/*     */           
/* 199 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.1")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 204 */         else if (eff <= 52) {
/* 205 */           player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, Math.min(40 * warp, 6000), 0, true, true));
/* 206 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.10")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 211 */         else if (eff <= 56) {
/* 212 */           PotionEffect pe = new PotionEffect(PotionDeathGaze.instance.func_76396_c(), 6000, Math.min(3, warp / 15), true, true);
/* 213 */           pe.getCurativeItems().clear();
/*     */           try {
/* 215 */             player.func_70690_d(pe);
/*     */           }
/*     */           catch (Exception e) {
/* 218 */             e.printStackTrace();
/*     */           }
/*     */           
/* 221 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.4")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 226 */         else if (eff <= 60) {
/* 227 */           suddenlySpiders(player, warp, false);
/*     */ 
/*     */         }
/* 230 */         else if (eff <= 64) {
/* 231 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.13")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 236 */         else if (eff <= 68) {
/* 237 */           spawnMist(player, warp, warp / 30);
/*     */ 
/*     */         }
/* 240 */         else if (eff <= 72) {
/*     */           try {
/* 242 */             player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, Math.min(32000, 5 * warp), 0, true, true));
/*     */           } catch (Exception e) {
/* 244 */             e.printStackTrace();
/*     */           }
/*     */           
/*     */         }
/* 248 */         else if (eff == 76) {
/* 249 */           if (Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_()) > 0) {
/* 250 */             Thaumcraft.proxy.getPlayerKnowledge().addWarpSticky(player.func_70005_c_(), -1);
/* 251 */             PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)1), (EntityPlayerMP)player);
/* 252 */             PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketWarpMessage(player, (byte)1, -1), (EntityPlayerMP)player);
/*     */           }
/* 254 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.14")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 259 */         else if (eff <= 80) {
/* 260 */           PotionEffect pe = new PotionEffect(PotionUnnaturalHunger.instance.func_76396_c(), 6000, Math.min(3, warp / 15), true, true);
/* 261 */           pe.getCurativeItems().clear();
/* 262 */           pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
/* 263 */           pe.addCurativeItem(new ItemStack(ItemsTC.brain));
/*     */           try {
/* 265 */             player.func_70690_d(pe);
/*     */           } catch (Exception e) {
/* 267 */             e.printStackTrace();
/*     */           }
/*     */           
/* 270 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.2")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 275 */         else if (eff <= 88) {
/* 276 */           spawnPortal(player);
/*     */ 
/*     */         }
/* 279 */         else if (eff <= 92) {
/* 280 */           suddenlySpiders(player, warp, true);
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 285 */           spawnMist(player, warp, warp / 15);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 290 */       if (actualwarp > 10)
/*     */       {
/* 292 */         if ((!ResearchHelper.isResearchComplete(player.func_70005_c_(), "BATHSALTS")) && (!ResearchHelper.isResearchComplete(player.func_70005_c_(), "@BATHSALTS")))
/*     */         {
/* 294 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.8")));
/* 295 */           ResearchHelper.completeResearch(player, "@BATHSALTS");
/*     */         }
/*     */       }
/* 298 */       if (actualwarp > 25)
/*     */       {
/* 300 */         if (!ResearchHelper.isResearchComplete(player.func_70005_c_(), "ELDRITCHMINOR")) {
/* 301 */           ResearchHelper.completeResearch(player, "ELDRITCHMINOR");
/*     */         }
/*     */       }
/*     */       
/* 305 */       if (actualwarp > 50)
/*     */       {
/* 307 */         if (!ResearchHelper.isResearchComplete(player.func_70005_c_(), "ELDRITCHMAJOR")) {
/* 308 */           ResearchHelper.completeResearch(player, "ELDRITCHMAJOR");
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 313 */     Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(player.func_70005_c_(), -1);
/* 314 */     PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)2), (EntityPlayerMP)player);
/*     */   }
/*     */   
/*     */   private static void spawnMist(EntityPlayer player, int warp, int guardian) {
/* 318 */     PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((short)1), (EntityPlayerMP)player);
/*     */     
/*     */ 
/*     */ 
/* 322 */     if (guardian > 0) {
/* 323 */       guardian = Math.min(8, guardian);
/* 324 */       for (int a = 0; a < guardian; a++) {
/* 325 */         spawnGuardian(player);
/*     */       }
/*     */     }
/* 328 */     player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.6")));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void spawnPortal(EntityPlayer player)
/*     */   {
/* 335 */     EntityCultistPortalLesser eg = new EntityCultistPortalLesser(player.field_70170_p);
/* 336 */     int i = MathHelper.func_76128_c(player.field_70165_t);
/* 337 */     int j = MathHelper.func_76128_c(player.field_70163_u);
/* 338 */     int k = MathHelper.func_76128_c(player.field_70161_v);
/* 339 */     for (int l = 0; l < 50; l++)
/*     */     {
/* 341 */       int i1 = i + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 342 */       int j1 = j + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 343 */       int k1 = k + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/*     */       
/* 345 */       if (World.func_175683_a(player.field_70170_p, new BlockPos(i1, j1 - 1, k1)))
/*     */       {
/* 347 */         eg.func_70107_b(i1 + 0.5D, j1 + 1.0D, k1 + 0.5D);
/*     */         
/* 349 */         if ((player.field_70170_p.func_72855_b(eg.func_174813_aQ())) && (player.field_70170_p.func_72945_a(eg, eg.func_174813_aQ()).isEmpty()) && (!player.field_70170_p.func_72953_d(eg.func_174813_aQ())))
/*     */         {
/*     */ 
/*     */ 
/* 353 */           eg.func_180482_a(player.field_70170_p.func_175649_E(new BlockPos(eg)), (net.minecraft.entity.IEntityLivingData)null);
/* 354 */           player.field_70170_p.func_72838_d(eg);
/* 355 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.16")));
/*     */           
/*     */ 
/* 358 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void spawnGuardian(EntityPlayer player) {
/* 365 */     EntityEldritchGuardian eg = new EntityEldritchGuardian(player.field_70170_p);
/* 366 */     int i = MathHelper.func_76128_c(player.field_70165_t);
/* 367 */     int j = MathHelper.func_76128_c(player.field_70163_u);
/* 368 */     int k = MathHelper.func_76128_c(player.field_70161_v);
/* 369 */     for (int l = 0; l < 50; l++)
/*     */     {
/* 371 */       int i1 = i + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 372 */       int j1 = j + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 373 */       int k1 = k + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/*     */       
/* 375 */       if (World.func_175683_a(player.field_70170_p, new BlockPos(i1, j1 - 1, k1)))
/*     */       {
/* 377 */         eg.func_70107_b(i1, j1, k1);
/*     */         
/* 379 */         if ((player.field_70170_p.func_72855_b(eg.func_174813_aQ())) && (player.field_70170_p.func_72945_a(eg, eg.func_174813_aQ()).isEmpty()) && (!player.field_70170_p.func_72953_d(eg.func_174813_aQ())))
/*     */         {
/*     */ 
/*     */ 
/* 383 */           eg.func_70624_b(player);
/* 384 */           player.field_70170_p.func_72838_d(eg);
/* 385 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void suddenlySpiders(EntityPlayer player, int warp, boolean real)
/*     */   {
/* 393 */     int spawns = Math.min(50, warp);
/*     */     
/* 395 */     for (int a = 0; a < spawns; a++)
/*     */     {
/* 397 */       EntityMindSpider spider = new EntityMindSpider(player.field_70170_p);
/* 398 */       int i = MathHelper.func_76128_c(player.field_70165_t);
/* 399 */       int j = MathHelper.func_76128_c(player.field_70163_u);
/* 400 */       int k = MathHelper.func_76128_c(player.field_70161_v);
/* 401 */       boolean success = false;
/* 402 */       for (int l = 0; l < 50; l++)
/*     */       {
/* 404 */         int i1 = i + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 405 */         int j1 = j + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 406 */         int k1 = k + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/*     */         
/* 408 */         if (World.func_175683_a(player.field_70170_p, new BlockPos(i1, j1 - 1, k1)))
/*     */         {
/* 410 */           spider.func_70107_b(i1, j1, k1);
/*     */           
/* 412 */           if ((player.field_70170_p.func_72855_b(spider.func_174813_aQ())) && (player.field_70170_p.func_72945_a(spider, spider.func_174813_aQ()).isEmpty()) && (!player.field_70170_p.func_72953_d(spider.func_174813_aQ())))
/*     */           {
/*     */ 
/*     */ 
/* 416 */             success = true;
/* 417 */             break;
/*     */           }
/*     */         }
/*     */       }
/* 421 */       if (success)
/*     */       {
/* 423 */         spider.func_70624_b(player);
/* 424 */         if (!real) {
/* 425 */           spider.setViewer(player.func_70005_c_());
/* 426 */           spider.setHarmless(true);
/*     */         }
/*     */         
/* 429 */         player.field_70170_p.func_72838_d(spider);
/*     */       }
/*     */     }
/*     */     
/* 433 */     player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.7")));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void checkDeathGaze(EntityPlayer player)
/*     */   {
/* 440 */     PotionEffect pe = player.func_70660_b(PotionDeathGaze.instance);
/* 441 */     if (pe == null) return;
/* 442 */     int level = pe.func_76458_c();
/* 443 */     int range = Math.min(8 + level * 3, 24);
/* 444 */     List list = player.field_70170_p.func_72839_b(player, player.func_174813_aQ().func_72314_b(range, range, range));
/*     */     
/*     */ 
/* 447 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/* 449 */       Entity entity = (Entity)list.get(i);
/* 450 */       if ((entity.func_70067_L()) && ((entity instanceof EntityLivingBase)) && (((EntityLivingBase)entity).func_70089_S()))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 457 */         if (thaumcraft.common.lib.utils.EntityUtils.isVisibleTo(0.75F, player, entity, range))
/*     */         {
/* 459 */           if ((entity != null) && (player.func_70685_l(entity)) && 
/* 460 */             ((!(entity instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())) && 
/* 461 */             (!((EntityLivingBase)entity).func_82165_m(Potion.field_82731_v.func_76396_c()))) {
/* 462 */             ((EntityLivingBase)entity).func_70604_c(player);
/* 463 */             ((EntityLivingBase)entity).func_130011_c(player);
/* 464 */             if ((entity instanceof EntityCreature)) {
/* 465 */               ((EntityCreature)entity).func_70624_b(player);
/*     */             }
/* 467 */             ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_82731_v.func_76396_c(), 80));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static int getWarpFromGear(EntityPlayer player)
/*     */   {
/* 477 */     int w = PlayerEvents.getFinalWarp(player.func_71045_bC(), player);
/*     */     
/* 479 */     for (int a = 0; a < 4; a++) {
/* 480 */       w += PlayerEvents.getFinalWarp(player.field_71071_by.func_70440_f(a), player);
/*     */     }
/*     */     
/* 483 */     IInventory baubles = baubles.api.BaublesApi.getBaubles(player);
/* 484 */     for (int a = 0; a < 4; a++) {
/* 485 */       w += PlayerEvents.getFinalWarp(baubles.func_70301_a(a), player);
/*     */     }
/*     */     
/* 488 */     return w;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/WarpEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */