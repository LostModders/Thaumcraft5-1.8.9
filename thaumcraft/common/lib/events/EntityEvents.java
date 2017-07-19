/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityEnderPearl;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityGolem;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntityRabbit;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemExpireEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDropsEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.damagesource.DamageSourceThaumcraft;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ import thaumcraft.common.entities.construct.EntityOwnedConstruct;
/*     */ import thaumcraft.common.entities.monster.EntityBrainyZombie;
/*     */ import thaumcraft.common.entities.monster.EntityThaumicSlime;
/*     */ import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintChicken;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCow;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCreeper;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintPig;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSheep;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintVillager;
/*     */ import thaumcraft.common.items.armor.ItemFortressArmor;
/*     */ import thaumcraft.common.items.consumables.ItemBathSalts;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXShield;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.lib.world.dim.Cell;
/*     */ import thaumcraft.common.lib.world.dim.CellLoc;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ 
/*     */ public class EntityEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void itemExpire(ItemExpireEvent event)
/*     */   {
/* 100 */     if ((event.entityItem.func_92059_d() != null) && (event.entityItem.func_92059_d().func_77973_b() != null) && ((event.entityItem.func_92059_d().func_77973_b() instanceof ItemBathSalts)))
/*     */     {
/* 102 */       BlockPos bp = new BlockPos(event.entityItem);
/* 103 */       IBlockState bs = event.entityItem.field_70170_p.func_180495_p(bp);
/* 104 */       if ((bs.func_177230_c() == Blocks.field_150355_j) && (bs.func_177230_c().func_176201_c(bs) == 0)) {
/* 105 */         event.entityItem.field_70170_p.func_175656_a(bp, BlocksTC.purifyingFluid.func_176223_P());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void livingTick(LivingEvent.LivingUpdateEvent event)
/*     */   {
/* 114 */     if (((event.entity instanceof EntityMob)) && (!event.entity.field_70128_L)) {
/* 115 */       EntityMob mob = (EntityMob)event.entity;
/* 116 */       int t = (int)mob.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
/*     */       try {
/* 118 */         if ((t >= 0) && (ChampionModifier.mods[t].type == 0)) {
/* 119 */           ChampionModifier.mods[t].effect.performEffect(mob, null, null, 0.0F);
/*     */         }
/*     */       } catch (Exception e) {
/* 122 */         if (t >= ChampionModifier.mods.length) {
/* 123 */           mob.func_70106_y();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void livingDeath(LivingDeathEvent event)
/*     */   {
/* 133 */     if ((!event.entityLiving.field_70170_p.field_72995_K) && (!(event.entityLiving instanceof EntityOwnedConstruct)) && (!(event.entityLiving instanceof EntityGolem)) && (!(event.entityLiving instanceof ITaintedMob)) && (event.entityLiving.func_70644_a(PotionFluxTaint.instance)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 139 */       Entity entity = null;
/*     */       
/* 141 */       if ((event.entityLiving instanceof EntityCreeper)) {
/* 142 */         entity = new EntityTaintCreeper(event.entityLiving.field_70170_p);
/*     */       }
/* 144 */       else if ((event.entityLiving instanceof EntitySheep)) {
/* 145 */         entity = new EntityTaintSheep(event.entityLiving.field_70170_p);
/*     */       }
/* 147 */       else if ((event.entityLiving instanceof EntityCow)) {
/* 148 */         entity = new EntityTaintCow(event.entityLiving.field_70170_p);
/*     */       }
/* 150 */       else if ((event.entityLiving instanceof EntityPig)) {
/* 151 */         entity = new EntityTaintPig(event.entityLiving.field_70170_p);
/*     */       }
/* 153 */       else if ((event.entityLiving instanceof EntityChicken)) {
/* 154 */         entity = new EntityTaintChicken(event.entityLiving.field_70170_p);
/*     */       }
/* 156 */       else if ((event.entityLiving instanceof EntityVillager)) {
/* 157 */         entity = new EntityTaintVillager(event.entityLiving.field_70170_p);
/*     */       }
/* 159 */       else if ((event.entityLiving.func_70668_bt() == EnumCreatureAttribute.ARTHROPOD) || ((event.entityLiving instanceof EntityAnimal)))
/*     */       {
/* 161 */         int n = (int)Math.max(1.0D, Math.sqrt(event.entityLiving.func_110138_aP() + 2.0F));
/* 162 */         for (int a = 0; a < n; a++) {
/* 163 */           Entity e = new EntityTaintCrawler(event.entityLiving.field_70170_p);
/* 164 */           e.func_70012_b(event.entityLiving.field_70165_t + (event.entityLiving.field_70170_p.field_73012_v.nextFloat() - event.entityLiving.field_70170_p.field_73012_v.nextFloat()) * event.entityLiving.field_70130_N, event.entityLiving.field_70163_u + event.entityLiving.field_70170_p.field_73012_v.nextFloat() * event.entityLiving.field_70131_O, event.entityLiving.field_70161_v + (event.entityLiving.field_70170_p.field_73012_v.nextFloat() - event.entityLiving.field_70170_p.field_73012_v.nextFloat()) * event.entityLiving.field_70130_N, event.entityLiving.field_70170_p.field_73012_v.nextInt(360), 0.0F);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 169 */           event.entityLiving.field_70170_p.func_72838_d(e);
/*     */         }
/* 171 */         event.entityLiving.func_70106_y();
/* 172 */         event.setCanceled(true);
/*     */       }
/* 174 */       else if ((event.entityLiving instanceof EntityRabbit)) {
/* 175 */         entity = new thaumcraft.common.entities.monster.tainted.EntityTaintRabbit(event.entityLiving.field_70170_p);
/* 176 */         ((EntityRabbit)entity).func_175529_r(((EntityRabbit)event.entityLiving).func_175531_cl());
/*     */       }
/*     */       else {
/* 179 */         entity = new EntityThaumicSlime(event.entityLiving.field_70170_p);
/* 180 */         if (entity != null) { ((EntityThaumicSlime)entity).func_70799_a((int)(1.0F + Math.min(event.entityLiving.func_110138_aP() / 10.0F, 6.0F)));
/*     */         }
/*     */       }
/* 183 */       if (entity != null)
/*     */       {
/* 185 */         entity.func_70012_b(event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, event.entityLiving.field_70177_z, 0.0F);
/* 186 */         event.entityLiving.field_70170_p.func_72838_d(entity);
/* 187 */         if (!(event.entityLiving instanceof EntityPlayer)) {
/* 188 */           event.entityLiving.func_70106_y();
/* 189 */           event.setCanceled(true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void entityHurt(LivingHurtEvent event)
/*     */   {
/* 200 */     if ((event.source.func_76364_f() != null) && ((event.source.func_76364_f() instanceof EntityPlayer))) {
/* 201 */       EntityPlayer leecher = (EntityPlayer)event.source.func_76364_f();
/* 202 */       ItemStack helm = leecher.field_71071_by.field_70460_b[3];
/* 203 */       if ((helm != null) && ((helm.func_77973_b() instanceof ItemFortressArmor)) && 
/* 204 */         (helm.func_77942_o()) && (helm.func_77978_p().func_74764_b("mask")) && (helm.func_77978_p().func_74762_e("mask") == 2) && (leecher.field_70170_p.field_73012_v.nextFloat() < event.ammount / 12.0F))
/*     */       {
/*     */ 
/*     */ 
/* 208 */         leecher.func_70691_i(1.0F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 213 */     if ((event.entity instanceof EntityPlayer))
/*     */     {
/* 215 */       EntityPlayer player = (EntityPlayer)event.entity;
/*     */       
/*     */ 
/* 218 */       if ((event.source.func_76364_f() != null) && ((event.source.func_76364_f() instanceof EntityLivingBase))) {
/* 219 */         EntityLivingBase attacker = (EntityLivingBase)event.source.func_76364_f();
/* 220 */         ItemStack helm = player.field_71071_by.field_70460_b[3];
/* 221 */         if ((helm != null) && ((helm.func_77973_b() instanceof ItemFortressArmor)) && 
/* 222 */           (helm.func_77942_o()) && (helm.func_77978_p().func_74764_b("mask")) && (helm.func_77978_p().func_74762_e("mask") == 1) && (player.field_70170_p.field_73012_v.nextFloat() < event.ammount / 10.0F))
/*     */         {
/*     */           try
/*     */           {
/* 226 */             attacker.func_70690_d(new PotionEffect(Potion.field_82731_v.func_76396_c(), 80));
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/* 231 */       int charge = (int)player.func_110139_bj();
/*     */       
/* 233 */       if ((charge > 0) && (PlayerEvents.runicInfo.containsKey(Integer.valueOf(player.func_145782_y()))) && (PlayerEvents.lastMaxCharge.containsKey(Integer.valueOf(player.func_145782_y()))))
/*     */       {
/*     */ 
/* 236 */         long time = System.currentTimeMillis();
/*     */         
/* 238 */         int target = -1;
/* 239 */         if (event.source.func_76346_g() != null) target = event.source.func_76346_g().func_145782_y();
/* 240 */         if (event.source == DamageSource.field_76379_h) target = -2;
/* 241 */         if (event.source == DamageSource.field_82729_p) { target = -3;
/*     */         }
/* 243 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(event.entity.func_145782_y(), target), new NetworkRegistry.TargetPoint(event.entity.field_70170_p.field_73011_w.func_177502_q(), event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, 32.0D));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 248 */         String key = player.func_145782_y() + ":" + 2;
/* 249 */         if ((charge <= event.ammount) && (((Integer[])PlayerEvents.runicInfo.get(Integer.valueOf(player.func_145782_y())))[2].intValue() > 0) && ((!PlayerEvents.upgradeCooldown.containsKey(key)) || (((Long)PlayerEvents.upgradeCooldown.get(key)).longValue() < time)))
/*     */         {
/* 251 */           PlayerEvents.upgradeCooldown.put(key, Long.valueOf(time + 20000L));
/* 252 */           player.field_70170_p.func_72885_a(player, player.field_70165_t, player.field_70163_u + player.field_70131_O / 2.0F, player.field_70161_v, 1.5F + ((Integer[])PlayerEvents.runicInfo.get(Integer.valueOf(player.func_145782_y())))[2].intValue() * 0.5F, false, false);
/*     */         }
/*     */         
/* 255 */         key = player.func_145782_y() + ":" + 3;
/* 256 */         if ((charge <= event.ammount) && (((Integer[])PlayerEvents.runicInfo.get(Integer.valueOf(player.func_145782_y())))[3].intValue() > 0) && ((!PlayerEvents.upgradeCooldown.containsKey(key)) || (((Long)PlayerEvents.upgradeCooldown.get(key)).longValue() < time)))
/*     */         {
/* 258 */           PlayerEvents.upgradeCooldown.put(key, Long.valueOf(time + 20000L));
/* 259 */           synchronized (player) {
/*     */             try {
/* 261 */               player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 240, ((Integer[])PlayerEvents.runicInfo.get(Integer.valueOf(player.func_145782_y())))[3].intValue()));
/*     */             } catch (Exception e) {}
/*     */           }
/* 264 */           player.field_70170_p.func_72956_a(player, "thaumcraft:runicShieldEffect", 1.0F, 1.0F);
/*     */         }
/*     */         
/* 267 */         key = player.func_145782_y() + ":" + 4;
/* 268 */         if ((charge <= event.ammount) && (((Integer[])PlayerEvents.runicInfo.get(Integer.valueOf(player.func_145782_y())))[4].intValue() > 0) && ((!PlayerEvents.upgradeCooldown.containsKey(key)) || (((Long)PlayerEvents.upgradeCooldown.get(key)).longValue() < time)))
/*     */         {
/* 270 */           PlayerEvents.upgradeCooldown.put(key, Long.valueOf(time + 60000L));
/* 271 */           int t = 8 * ((Integer[])PlayerEvents.runicInfo.get(Integer.valueOf(player.func_145782_y())))[4].intValue();
/* 272 */           charge = Math.min(((Integer[])PlayerEvents.runicInfo.get(Integer.valueOf(player.func_145782_y())))[0].intValue(), t);
/* 273 */           player.field_70170_p.func_72956_a(player, "thaumcraft:runicShieldCharge", 1.0F, 1.0F);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/* 279 */     else if ((event.entity instanceof EntityMob)) {
/* 280 */       IAttributeInstance cai = ((EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD);
/* 281 */       if (((cai != null) && (cai.func_111126_e() >= 0.0D)) || ((event.entity instanceof IEldritchMob)))
/*     */       {
/* 283 */         EntityMob mob = (EntityMob)event.entity;
/* 284 */         int t = (int)cai.func_111126_e();
/*     */         
/* 286 */         if (((t == 5) || ((event.entity instanceof IEldritchMob))) && (mob.func_110139_bj() > 0.0F)) {
/* 287 */           int target = -1;
/* 288 */           if (event.source.func_76346_g() != null) {
/* 289 */             target = event.source.func_76346_g().func_145782_y();
/*     */           }
/* 291 */           if (event.source == DamageSource.field_76379_h) target = -2;
/* 292 */           if (event.source == DamageSource.field_82729_p) target = -3;
/* 293 */           PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(mob.func_145782_y(), target), new NetworkRegistry.TargetPoint(event.entity.field_70170_p.field_73011_w.func_177502_q(), event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, 32.0D));
/*     */           
/*     */ 
/* 296 */           event.entity.field_70170_p.func_72908_a(event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, "thaumcraft:runicShieldEffect", 0.66F, 1.1F + event.entity.field_70170_p.field_73012_v.nextFloat() * 0.1F);
/*     */ 
/*     */         }
/* 299 */         else if ((t >= 0) && (ChampionModifier.mods[t].type == 2) && (event.source.func_76364_f() != null) && ((event.source.func_76364_f() instanceof EntityLivingBase)))
/*     */         {
/* 301 */           EntityLivingBase attacker = (EntityLivingBase)event.source.func_76364_f();
/* 302 */           event.ammount = ChampionModifier.mods[t].effect.performEffect(mob, attacker, event.source, event.ammount);
/*     */         }
/*     */       }
/*     */       
/* 306 */       if ((event.ammount > 0.0F) && (event.source.func_76364_f() != null) && ((event.entity instanceof EntityLivingBase)) && ((event.source.func_76364_f() instanceof EntityMob)) && (((EntityMob)event.source.func_76364_f()).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() >= 0.0D))
/*     */       {
/*     */ 
/* 309 */         EntityMob mob = (EntityMob)event.source.func_76364_f();
/* 310 */         int t = (int)mob.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
/* 311 */         if (ChampionModifier.mods[t].type == 1) {
/* 312 */           event.ammount = ChampionModifier.mods[t].effect.performEffect(mob, (EntityLivingBase)event.entity, event.source, event.ammount);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void itemPickup(EntityItemPickupEvent event)
/*     */   {
/* 321 */     if (event.entityPlayer.func_70005_c_().startsWith("FakeThaumcraft")) {
/* 322 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void entityConstuct(EntityEvent.EntityConstructing event) {
/* 328 */     if ((event.entity instanceof EntityMob)) {
/* 329 */       EntityMob mob = (EntityMob)event.entity;
/* 330 */       mob.func_110140_aT().func_111150_b(EntityUtils.CHAMPION_MOD).func_111128_a(-2.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void livingDrops(LivingDropsEvent event) {
/* 336 */     boolean fakeplayer = (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof net.minecraftforge.common.util.FakePlayer));
/*     */     
/*     */ 
/* 339 */     if ((!event.entity.field_70170_p.field_72995_K) && (event.recentlyHit) && (!fakeplayer) && ((event.entity instanceof EntityMob)) && (!(event.entity instanceof EntityThaumcraftBoss)) && (((EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() >= 0.0D))
/*     */     {
/*     */ 
/*     */ 
/* 343 */       int i = 5 + event.entity.field_70170_p.field_73012_v.nextInt(3);
/* 344 */       while (i > 0)
/*     */       {
/* 346 */         int j = EntityXPOrb.func_70527_a(i);
/* 347 */         i -= j;
/* 348 */         event.entity.field_70170_p.func_72838_d(new EntityXPOrb(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, j));
/*     */       }
/*     */       
/* 351 */       int lb = Math.min(2, MathHelper.func_76141_d((event.entity.field_70170_p.field_73012_v.nextInt(9) + event.lootingLevel) / 5.0F));
/* 352 */       event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ItemsTC.lootBag, 1, lb)));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 361 */     if (((event.entityLiving instanceof EntityZombie)) && (!(event.entityLiving instanceof EntityBrainyZombie)))
/*     */     {
/* 363 */       if ((event.recentlyHit) && (event.entity.field_70170_p.field_73012_v.nextInt(10) - event.lootingLevel < 1)) {
/* 364 */         event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ItemsTC.brain)));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 372 */     if (((event.entityLiving instanceof EntityVillager)) && (event.entity.field_70170_p.field_73012_v.nextInt(10) - event.lootingLevel < 1))
/*     */     {
/* 374 */       event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ItemsTC.coin)));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 382 */     if (((event.entityLiving instanceof EntityCultist)) && (!fakeplayer) && (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityPlayer)))
/*     */     {
/* 384 */       int c = !thaumcraft.common.lib.research.ResearchManager.isResearchComplete(((EntityPlayer)event.source.func_76346_g()).func_70005_c_(), "CRIMSON") ? 4 : 50;
/* 385 */       if (InventoryUtils.isPlayerCarrying((EntityPlayer)event.source.func_76346_g(), new ItemStack(ItemsTC.crimsonRites)) > 0) c = 100;
/* 386 */       if (event.entity.field_70170_p.field_73012_v.nextInt(c) == 0) {
/* 387 */         event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ItemsTC.crimsonRites)));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 395 */     if (event.source == DamageSourceThaumcraft.dissolve) {
/* 396 */       AspectList aspects = AspectHelper.getEntityAspects(event.entityLiving);
/* 397 */       if ((aspects != null) && (aspects.size() > 0)) {
/* 398 */         for (Aspect aspect : aspects.getAspects()) {
/* 399 */           if (!event.entity.field_70170_p.field_73012_v.nextBoolean()) {
/* 400 */             int size = 1 + event.entity.field_70170_p.field_73012_v.nextInt(aspects.getAmount(aspect));
/* 401 */             size = Math.max(1, size / 2);
/* 402 */             ItemStack stack = new ItemStack(ItemsTC.crystalEssence, size, 0);
/* 403 */             ((ItemGenericEssentiaContainer)stack.func_77973_b()).setAspects(stack, new AspectList().add(aspect, 1));
/* 404 */             event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, stack));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 416 */   public static HashMap<String, ArrayList<WeakReference<Entity>>> linkedEntities = new HashMap();
/*     */   
/*     */   @SubscribeEvent
/*     */   public void entitySpawns(EntityJoinWorldEvent event) {
/* 420 */     if (!event.world.field_72995_K) {
/* 421 */       if ((event.entity instanceof EntityEnderPearl)) {
/* 422 */         int x = MathHelper.func_76128_c(event.entity.field_70165_t);
/* 423 */         int y = MathHelper.func_76128_c(event.entity.field_70163_u);
/* 424 */         int z = MathHelper.func_76128_c(event.entity.field_70161_v);
/*     */         
/* 426 */         for (int xx = -5; xx <= 5; xx++) for (int yy = -5; yy <= 5; yy++) for (int zz = -5; zz <= 5; zz++) {
/* 427 */               TileEntity tile = event.world.func_175625_s(new BlockPos(x + xx, y + yy, z + zz));
/* 428 */               if ((tile != null) && ((tile instanceof TileOwned))) {
/* 429 */                 if ((((EntityEnderPearl)event.entity).func_85052_h() instanceof EntityPlayer)) {
/* 430 */                   ((EntityPlayer)((EntityEnderPearl)event.entity).func_85052_h()).func_145747_a(new ChatComponentText("§5§oThe magic of a nearby warded object destroys the ender pearl."));
/*     */                 }
/* 432 */                 event.entity.func_70106_y();
/*     */                 break label196;
/*     */               }
/*     */             }
/*     */       }
/*     */       label196:
/* 438 */       if ((event.entity instanceof EntityPlayer)) {
/* 439 */         ArrayList<WeakReference<Entity>> dudes = (ArrayList)linkedEntities.get(event.entity.func_70005_c_());
/* 440 */         if (dudes != null) {
/* 441 */           for (WeakReference<Entity> dude : dudes) {
/* 442 */             if ((dude.get() != null) && (((Entity)dude.get()).field_71088_bW == 0)) {
/* 443 */               ((Entity)dude.get()).field_71088_bW = ((Entity)dude.get()).func_82147_ab();
/* 444 */               ((Entity)dude.get()).func_71027_c(event.world.field_73011_w.func_177502_q());
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*     */       }
/* 450 */       else if ((event.entity instanceof EntityMob)) {
/* 451 */         EntityMob mob = (EntityMob)event.entity;
/*     */         
/* 453 */         if (mob.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() < -1.0D)
/*     */         {
/* 455 */           int c = event.world.field_73012_v.nextInt(100);
/*     */           
/* 457 */           if ((event.world.func_175659_aa() == EnumDifficulty.EASY) || (!Config.championMobs)) c += 2;
/* 458 */           if (event.world.func_175659_aa() == EnumDifficulty.HARD) c -= (Config.championMobs ? 2 : 0);
/* 459 */           if (event.world.field_73011_w.func_177502_q() == Config.dimensionOuterId) c -= 3;
/* 460 */           BiomeGenBase bg = mob.field_70170_p.func_180494_b(new BlockPos(mob));
/* 461 */           if ((BiomeDictionary.isBiomeOfType(bg, BiomeDictionary.Type.SPOOKY)) || (BiomeDictionary.isBiomeOfType(bg, BiomeDictionary.Type.NETHER)) || (BiomeDictionary.isBiomeOfType(bg, BiomeDictionary.Type.END)))
/*     */           {
/* 463 */             c -= (Config.championMobs ? 2 : 1);
/*     */           }
/* 465 */           if (isDangerousLocation(mob.field_70170_p, MathHelper.func_76143_f(mob.field_70165_t), MathHelper.func_76143_f(mob.field_70163_u), MathHelper.func_76143_f(mob.field_70161_v)))
/*     */           {
/*     */ 
/*     */ 
/* 469 */             c -= (Config.championMobs ? 10 : 3);
/*     */           }
/*     */           
/* 472 */           int cc = 0;
/* 473 */           boolean whitelisted = false;
/* 474 */           for (Class clazz : ConfigEntities.championModWhitelist.keySet()) {
/* 475 */             if (clazz.isAssignableFrom(event.entity.getClass())) {
/* 476 */               whitelisted = true;
/* 477 */               if ((Config.championMobs) || ((event.entity instanceof EntityThaumcraftBoss))) {
/* 478 */                 cc = Math.max(cc, ((Integer)ConfigEntities.championModWhitelist.get(clazz)).intValue() - 1);
/*     */               }
/*     */             }
/*     */           }
/* 482 */           c -= cc;
/*     */           
/* 484 */           if ((whitelisted) && (c <= 0) && (mob.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() >= 10.0D)) {
/* 485 */             EntityUtils.makeChampion(mob, false);
/*     */           } else {
/* 487 */             IAttributeInstance modai = mob.func_110148_a(EntityUtils.CHAMPION_MOD);
/* 488 */             modai.func_111124_b(ChampionModifier.ATTRIBUTE_MOD_NONE);
/* 489 */             modai.func_111121_a(ChampionModifier.ATTRIBUTE_MOD_NONE);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isDangerousLocation(World world, int x, int y, int z) {
/* 497 */     if (world.field_73011_w.func_177502_q() == Config.dimensionOuterId) {
/* 498 */       int xx = x >> 4;
/* 499 */       int zz = z >> 4;
/* 500 */       Cell c = MazeHandler.getFromHashMap(new CellLoc(xx, zz));
/* 501 */       if ((c != null) && ((c.feature == 6) || (c.feature == 8))) {
/* 502 */         return true;
/*     */       }
/*     */     }
/* 505 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/EntityEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */