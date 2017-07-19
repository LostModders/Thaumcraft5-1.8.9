/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.event.entity.living.LivingDropsEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowLooseEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowNockEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.event.entity.player.FillBucketEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
/*     */ import net.minecraftforge.event.world.BlockEvent.BreakEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*     */ import net.minecraftforge.fluids.BlockFluidClassic;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event.Result;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*     */ import thaumcraft.common.items.armor.Hover;
/*     */ import thaumcraft.common.items.tools.ItemBowBone;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXScanSource;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXSlash;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ public class ToolEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void playerAttack(AttackEntityEvent event)
/*     */   {
/*  74 */     ItemStack heldItem = event.entityPlayer.func_70694_bm();
/*  75 */     if (heldItem != null) {
/*  76 */       List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/*     */       
/*     */ 
/*  79 */       if ((list.contains(EnumInfusionEnchantment.ARCING)) && (event.target.func_70089_S())) {
/*  80 */         int rank = EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldItem, EnumInfusionEnchantment.ARCING);
/*  81 */         List targets = event.entityPlayer.field_70170_p.func_72839_b(event.entityPlayer, event.target.func_174813_aQ().func_72314_b(1.5D + rank, 1.0F + rank / 2.0F, 1.5D + rank));
/*     */         
/*  83 */         int count = 0;
/*  84 */         if (targets.size() > 1) {
/*  85 */           for (int var9 = 0; var9 < targets.size(); var9++)
/*     */           {
/*  87 */             Entity var10 = (Entity)targets.get(var9);
/*  88 */             if ((!var10.field_70128_L) && (
/*  89 */               (!(var10 instanceof IEntityOwnable)) || (((IEntityOwnable)var10).func_70902_q() == null) || (!((IEntityOwnable)var10).func_70902_q().equals(event.entityPlayer))))
/*     */             {
/*  91 */               if (((var10 instanceof EntityLiving)) && (var10.func_145782_y() != event.target.func_145782_y()) && (
/*  92 */                 (!(var10 instanceof EntityPlayer)) || (((EntityPlayer)var10).func_70005_c_() != event.entityPlayer.func_70005_c_())))
/*     */               {
/*     */ 
/*     */ 
/*  96 */                 if (var10.func_70089_S()) {
/*  97 */                   float f = (float)event.entityPlayer.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*     */                   
/*  99 */                   event.entityPlayer.func_70652_k(var10);
/* 100 */                   if (var10.func_70097_a(DamageSource.func_76365_a(event.entityPlayer), f * 0.5F))
/*     */                   {
/*     */                     try {
/* 103 */                       if ((var10 instanceof EntityLivingBase)) {
/* 104 */                         EnchantmentHelper.func_151384_a((EntityLivingBase)var10, event.entityPlayer);
/*     */                       }
/*     */                     }
/*     */                     catch (Exception e) {}
/*     */                     
/*     */ 
/*     */ 
/* 111 */                     var10.func_70024_g(-MathHelper.func_76126_a(event.entityPlayer.field_70177_z * 3.1415927F / 180.0F) * 0.5F, 0.1D, MathHelper.func_76134_b(event.entityPlayer.field_70177_z * 3.1415927F / 180.0F) * 0.5F);
/*     */                     
/*     */ 
/*     */ 
/* 115 */                     count++;
/* 116 */                     if (!event.entityPlayer.field_70170_p.field_72995_K) {
/* 117 */                       PacketHandler.INSTANCE.sendToAllAround(new PacketFXSlash(event.target.func_145782_y(), var10.func_145782_y()), new NetworkRegistry.TargetPoint(event.entityPlayer.field_70170_p.field_73011_w.func_177502_q(), event.target.field_70165_t, event.target.field_70163_u, event.target.field_70161_v, 64.0D));
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */               
/*     */ 
/* 124 */               if (count >= rank) break;
/*     */             }
/*     */           }
/* 127 */           if ((count > 0) && (!event.entityPlayer.field_70170_p.field_72995_K)) {
/* 128 */             event.entityPlayer.field_70170_p.func_72956_a(event.target, "thaumcraft:swing", 1.0F, 0.9F + event.entityPlayer.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/* 129 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXSlash(event.entityPlayer.func_145782_y(), event.target.func_145782_y()), new NetworkRegistry.TargetPoint(event.entityPlayer.field_70170_p.field_73011_w.func_177502_q(), event.entityPlayer.field_70165_t, event.entityPlayer.field_70163_u, event.entityPlayer.field_70161_v, 64.0D));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void playerInteract(PlayerInteractEvent event)
/*     */   {
/* 141 */     if ((event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && (!event.world.field_72995_K) && (event.entityPlayer != null)) {
/* 142 */       ItemStack heldItem = event.entityPlayer.func_70694_bm();
/*     */       
/* 144 */       if (heldItem != null) {
/* 145 */         List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/*     */         
/*     */ 
/* 148 */         if ((list.contains(EnumInfusionEnchantment.SOUNDING)) && (!event.entityPlayer.func_70093_af())) {
/* 149 */           heldItem.func_77972_a(5, event.entityPlayer);
/* 150 */           event.world.func_72908_a(event.pos.func_177958_n() + 0.5D, event.pos.func_177956_o() + 0.5D, event.pos.func_177952_p() + 0.5D, "thaumcraft:wandfail", 0.2F, 0.2F + event.world.field_73012_v.nextFloat() * 0.2F);
/*     */           
/* 152 */           PacketHandler.INSTANCE.sendTo(new PacketFXScanSource(event.pos, EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldItem, EnumInfusionEnchantment.SOUNDING)), (EntityPlayerMP)event.entityPlayer);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 160 */     if ((event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) && (event.entityPlayer != null)) {
/* 161 */       this.lastFaceClicked.put(Integer.valueOf(event.entityPlayer.func_145782_y()), event.face);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 172 */   HashMap<Integer, EnumFacing> lastFaceClicked = new HashMap();
/*     */   
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void breakBlockEvent(BlockEvent.BreakEvent event)
/*     */   {
/* 178 */     if ((!event.world.field_72995_K) && (event.getPlayer() != null)) {
/* 179 */       ItemStack heldItem = event.getPlayer().func_70694_bm();
/*     */       
/* 181 */       if (heldItem != null) {
/* 182 */         List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/* 183 */         if (ForgeHooks.isToolEffective(event.world, event.pos, heldItem))
/*     */         {
/*     */ 
/* 186 */           if ((list.contains(EnumInfusionEnchantment.BURROWING)) && (!event.getPlayer().func_70093_af()) && (isValidBurrowBlock(event.world, event.pos))) {
/* 187 */             event.setCanceled(true);
/* 188 */             BlockUtils.breakFurthestBlock(event.world, event.pos, event.state, event.getPlayer());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isValidBurrowBlock(World world, BlockPos pos)
/*     */   {
/* 197 */     return (Utils.isWoodLog(world, pos)) || (Utils.isOreBlock(world, pos));
/*     */   }
/*     */   
/* 200 */   boolean blockDestructiveRecursion = false;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void harvestBlockEvent(BlockEvent.HarvestDropsEvent event) {
/* 204 */     if ((!event.world.field_72995_K) && (event.harvester != null)) {
/* 205 */       ItemStack heldItem = event.harvester.func_70694_bm();
/*     */       
/* 207 */       if (heldItem != null) {
/* 208 */         List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/*     */         
/* 210 */         if ((event.isSilkTouching) || (ForgeHooks.isToolEffective(event.world, event.pos, heldItem)) || (((heldItem.func_77973_b() instanceof ItemTool)) && (((ItemTool)heldItem.func_77973_b()).func_150893_a(heldItem, event.state.func_177230_c()) > 1.0F)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 215 */           if (list.contains(EnumInfusionEnchantment.REFINING)) {
/* 216 */             int fortune = 1 + EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldItem, EnumInfusionEnchantment.REFINING);
/* 217 */             float chance = fortune * 0.125F;
/* 218 */             boolean b = false;
/* 219 */             for (int a = 0; a < event.drops.size(); a++)
/*     */             {
/* 221 */               ItemStack is = (ItemStack)event.drops.get(a);
/* 222 */               ItemStack smr = Utils.findSpecialMiningResult(is, chance, event.world.field_73012_v);
/* 223 */               if (!is.func_77969_a(smr)) {
/* 224 */                 event.drops.set(a, smr);
/* 225 */                 b = true;
/*     */               }
/*     */             }
/* 228 */             if (b) {
/* 229 */               event.world.func_72908_a(event.pos.func_177958_n() + 0.5F, event.pos.func_177956_o() + 0.5F, event.pos.func_177952_p() + 0.5F, "random.orb", 0.2F, 0.7F + event.world.field_73012_v.nextFloat() * 0.2F);
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 234 */           if ((!this.blockDestructiveRecursion) && (list.contains(EnumInfusionEnchantment.DESTRUCTIVE)) && (!event.harvester.func_70093_af())) {
/* 235 */             this.blockDestructiveRecursion = true;
/*     */             
/* 237 */             EnumFacing face = (EnumFacing)this.lastFaceClicked.get(Integer.valueOf(event.harvester.func_145782_y()));
/* 238 */             if (face == null) {
/* 239 */               face = BlockPistonBase.func_180695_a(event.world, event.pos, event.harvester);
/*     */             }
/* 241 */             for (int aa = -1; aa <= 1; aa++)
/* 242 */               for (int bb = -1; bb <= 1; bb++)
/*     */               {
/* 244 */                 if ((aa != 0) || (bb != 0))
/*     */                 {
/* 246 */                   int xx = 0;
/* 247 */                   int yy = 0;
/* 248 */                   int zz = 0;
/*     */                   
/* 250 */                   if (face.ordinal() <= 1) {
/* 251 */                     xx = aa;
/* 252 */                     zz = bb;
/* 253 */                   } else if (face.ordinal() <= 3) {
/* 254 */                     xx = aa;
/* 255 */                     yy = bb;
/*     */                   } else {
/* 257 */                     zz = aa;
/* 258 */                     yy = bb;
/*     */                   }
/*     */                   
/* 261 */                   Block bl = event.world.func_180495_p(event.pos.func_177982_a(xx, yy, zz)).func_177230_c();
/*     */                   
/* 263 */                   if ((bl.func_176195_g(event.world, event.pos.func_177982_a(xx, yy, zz)) >= 0.0F) && ((ForgeHooks.isToolEffective(event.world, event.pos.func_177982_a(xx, yy, zz), heldItem)) || (((heldItem.func_77973_b() instanceof ItemTool)) && (((ItemTool)heldItem.func_77973_b()).func_150893_a(heldItem, bl) > 1.0F))))
/*     */                   {
/*     */ 
/*     */ 
/*     */ 
/* 268 */                     heldItem.func_77972_a(1, event.harvester);
/* 269 */                     BlockUtils.harvestBlock(event.world, event.harvester, event.pos.func_177982_a(xx, yy, zz));
/*     */                   }
/*     */                 } }
/* 272 */             this.blockDestructiveRecursion = false;
/*     */           }
/*     */           
/*     */ 
/* 276 */           if ((list.contains(EnumInfusionEnchantment.COLLECTOR)) && (!event.harvester.func_70093_af())) {
/* 277 */             InventoryUtils.dropHarvestsAtPos(event.world, event.pos, event.drops, true, 10, event.harvester);
/* 278 */             event.drops.clear();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void breakSpeedEvent(PlayerEvent.BreakSpeed event)
/*     */   {
/* 288 */     if ((!event.entityPlayer.field_70122_E) && (Hover.getHover(event.entityPlayer.func_145782_y()))) {
/* 289 */       event.newSpeed = (event.originalSpeed * 5.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void bowNocked(ArrowNockEvent event) {
/* 295 */     if (event.entityPlayer.field_71071_by.func_146028_b(ItemsTC.primalArrows))
/*     */     {
/* 297 */       event.entityPlayer.func_71008_a(event.result, event.result.func_77973_b().func_77626_a(event.result));
/*     */       
/* 299 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void bowShot(ArrowLooseEvent event) {
/* 305 */     if (event.entityPlayer.field_71071_by.func_146028_b(ItemsTC.primalArrows))
/*     */     {
/* 307 */       float f = 0.0F;
/* 308 */       float dam = 2.0F;
/*     */       
/* 310 */       if ((event.bow.func_77973_b() instanceof ItemBowBone)) {
/* 311 */         f = event.charge / 10.0F;
/* 312 */         f = (f * f + f * 2.0F) / 3.0F;
/*     */         
/* 314 */         if (f < 0.1D)
/*     */         {
/* 316 */           return;
/*     */         }
/* 318 */         dam = 2.5F;
/*     */       } else {
/* 320 */         f = event.charge / 20.0F;
/* 321 */         f = (f * f + f * 2.0F) / 3.0F;
/*     */         
/* 323 */         if (f < 0.1D)
/*     */         {
/* 325 */           return;
/*     */         }
/*     */       }
/*     */       
/* 329 */       if (f > 1.0F)
/*     */       {
/* 331 */         f = 1.0F;
/*     */       }
/*     */       
/* 334 */       int type = 0;
/*     */       
/* 336 */       for (int j = 0; j < event.entityPlayer.field_71071_by.field_70462_a.length; j++)
/*     */       {
/* 338 */         if ((event.entityPlayer.field_71071_by.field_70462_a[j] != null) && (event.entityPlayer.field_71071_by.field_70462_a[j].func_77973_b() == ItemsTC.primalArrows))
/*     */         {
/*     */ 
/* 341 */           type = event.entityPlayer.field_71071_by.field_70462_a[j].func_77952_i();
/* 342 */           break;
/*     */         }
/*     */       }
/*     */       
/* 346 */       EntityPrimalArrow entityarrow = new EntityPrimalArrow(event.entityPlayer.field_70170_p, event.entityPlayer, f * dam, type);
/*     */       
/*     */ 
/* 349 */       if ((event.bow.func_77973_b() instanceof ItemBowBone)) {
/* 350 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + 0.5D);
/*     */       }
/* 352 */       else if (f == 1.0F)
/*     */       {
/* 354 */         entityarrow.func_70243_d(true);
/*     */       }
/*     */       
/*     */ 
/* 358 */       int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, event.bow);
/*     */       
/* 360 */       if (k > 0)
/*     */       {
/* 362 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D);
/*     */       }
/*     */       
/* 365 */       int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, event.bow);
/* 366 */       if (type == 3) l++;
/* 367 */       if (l > 0)
/*     */       {
/* 369 */         entityarrow.func_70240_a(l);
/*     */       }
/*     */       
/* 372 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, event.bow) > 0)
/*     */       {
/* 374 */         entityarrow.func_70015_d(100);
/*     */       }
/*     */       
/* 377 */       event.bow.func_77972_a(1, event.entityPlayer);
/* 378 */       event.entityPlayer.field_70170_p.func_72956_a(event.entityPlayer, "random.bow", 1.0F, 1.0F / (event.entityPlayer.field_70170_p.field_73012_v.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*     */       
/* 380 */       boolean flag = false;
/* 381 */       if ((EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, event.bow) > 0) && (event.entityPlayer.field_70170_p.field_73012_v.nextFloat() < 0.33F))
/*     */       {
/* 383 */         flag = true;
/*     */       }
/* 385 */       if ((!event.entityPlayer.field_71075_bZ.field_75098_d) || (!flag)) {
/* 386 */         InventoryUtils.consumeInventoryItem(event.entityPlayer, ItemsTC.primalArrows, type);
/*     */       }
/*     */       
/* 389 */       if (!event.entityPlayer.field_70170_p.field_72995_K)
/*     */       {
/* 391 */         event.entityPlayer.field_70170_p.func_72838_d(entityarrow);
/*     */       }
/*     */       
/* 394 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void fillBucket(FillBucketEvent event) {
/* 400 */     if (event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/* 401 */       IBlockState bs = event.world.func_180495_p(event.target.func_178782_a());
/* 402 */       if ((bs.func_177230_c() == BlocksTC.purifyingFluid) && (((BlockFluidClassic)bs.func_177230_c()).isSourceBlock(event.world, event.target.func_178782_a()))) {
/* 403 */         event.world.func_175698_g(event.target.func_178782_a());
/* 404 */         event.result = new ItemStack(ItemsTC.bucketPure);
/* 405 */         event.setResult(Event.Result.ALLOW);
/* 406 */         return;
/*     */       }
/* 408 */       if ((bs.func_177230_c() == BlocksTC.liquidDeath) && (((BlockFluidClassic)bs.func_177230_c()).isSourceBlock(event.world, event.target.func_178782_a()))) {
/* 409 */         event.world.func_175698_g(event.target.func_178782_a());
/* 410 */         event.result = new ItemStack(ItemsTC.bucketDeath);
/* 411 */         event.setResult(Event.Result.ALLOW);
/* 412 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void livingDrops(LivingDropsEvent event) {
/* 419 */     if ((event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityPlayer))) {
/* 420 */       ItemStack heldItem = ((EntityPlayer)event.source.func_76346_g()).func_70694_bm();
/*     */       
/* 422 */       if (heldItem != null) {
/* 423 */         List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/*     */         
/*     */ 
/* 426 */         if (list.contains(EnumInfusionEnchantment.COLLECTOR)) {
/* 427 */           boolean b = false;
/* 428 */           for (int a = 0; a < event.drops.size(); a++)
/*     */           {
/* 430 */             EntityItem ei = (EntityItem)event.drops.get(a);
/* 431 */             ItemStack is = ei.func_92059_d().func_77946_l();
/* 432 */             EntityItem nei = new EntityFollowingItem(event.entity.field_70170_p, ei.field_70165_t, ei.field_70163_u, ei.field_70161_v, is, event.source.func_76346_g(), 10);
/* 433 */             nei.field_70159_w = ei.field_70159_w;
/* 434 */             nei.field_70181_x = ei.field_70181_x;
/* 435 */             nei.field_70179_y = ei.field_70179_y;
/* 436 */             nei.func_174869_p();
/* 437 */             ei.func_70106_y();
/* 438 */             event.drops.set(a, nei);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 443 */         if (list.contains(EnumInfusionEnchantment.ESSENCE)) {
/* 444 */           AspectList aspects = AspectHelper.getEntityAspects(event.entityLiving).copy();
/* 445 */           if ((aspects != null) && (aspects.size() > 0)) {
/* 446 */             int q = EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldItem, EnumInfusionEnchantment.ESSENCE);
/* 447 */             Aspect[] al = aspects.getAspects();
/* 448 */             int b = event.entity.field_70170_p.field_73012_v.nextInt(5) < q ? 0 : 99;
/* 449 */             while ((b < q) && (al != null) && (al.length > 0)) {
/* 450 */               Aspect aspect = al[event.entity.field_70170_p.field_73012_v.nextInt(al.length)];
/* 451 */               if (aspects.getAmount(aspect) > 0) {
/* 452 */                 aspects.remove(aspect, 1);
/* 453 */                 ItemStack stack = new ItemStack(ItemsTC.crystalEssence, 1, 0);
/* 454 */                 ((ItemGenericEssentiaContainer)stack.func_77973_b()).setAspects(stack, new AspectList().add(aspect, 1));
/* 455 */                 if (list.contains(EnumInfusionEnchantment.COLLECTOR)) {
/* 456 */                   event.drops.add(new EntityFollowingItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, stack, event.source.func_76346_g(), 10));
/*     */ 
/*     */                 }
/*     */                 else
/*     */                 {
/*     */ 
/* 462 */                   event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, stack));
/*     */                 }
/*     */                 
/*     */ 
/*     */ 
/*     */ 
/* 468 */                 b++;
/*     */               }
/* 470 */               al = aspects.getAspects();
/* 471 */               if (event.entity.field_70170_p.field_73012_v.nextInt(q) == 0) b += 1 + event.entity.field_70170_p.field_73012_v.nextInt(2);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/ToolEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */