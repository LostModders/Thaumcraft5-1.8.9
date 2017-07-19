/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.internal.EnumWarpType;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.EntityTaintSource;
/*     */ import thaumcraft.common.entities.EntityTaintSourceCloud;
/*     */ import thaumcraft.common.entities.monster.EntityWisp;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.aura.EntityAuraNode;
/*     */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.RandomItemChooser;
/*     */ import thaumcraft.common.lib.utils.RandomItemChooser.Item;
/*     */ 
/*     */ public class TaintEvents
/*     */ {
/*     */   static class TaintItem implements RandomItemChooser.Item
/*     */   {
/*     */     int weight;
/*     */     int event;
/*     */     int cost;
/*     */     boolean nearTaintAllowed;
/*     */     
/*     */     protected TaintItem(int event, int weight, int cost, boolean nearTaintAllowed)
/*     */     {
/*  43 */       this.weight = weight;
/*  44 */       this.event = event;
/*  45 */       this.cost = cost;
/*  46 */       this.nearTaintAllowed = nearTaintAllowed;
/*     */     }
/*     */     
/*     */     public double getWeight() {
/*  50 */       return this.weight;
/*     */     }
/*     */   }
/*     */   
/*  54 */   static ArrayList<RandomItemChooser.Item> events = new ArrayList();
/*     */   
/*  56 */   static { events.add(new TaintItem(0, 25, 5, true));
/*  57 */     events.add(new TaintItem(1, 5, 25, false));
/*  58 */     events.add(new TaintItem(2, 1, 40, false));
/*  59 */     events.add(new TaintItem(3, 2, 10, true));
/*  60 */     events.add(new TaintItem(4, 5, 20, true));
/*  61 */     events.add(new TaintItem(5, 5, 15, true));
/*  62 */     events.add(new TaintItem(6, 3, 20, true));
/*     */   }
/*     */   
/*     */   protected static boolean taintEvent(World world, BlockPos pos) {
/*  66 */     RandomItemChooser ric = new RandomItemChooser();
/*  67 */     TaintItem ei = (TaintItem)ric.chooseOnWeight(events);
/*  68 */     if (ei == null) { return false;
/*     */     }
/*  70 */     pos = pos.func_177982_a(world.field_73012_v.nextInt(16), 0, world.field_73012_v.nextInt(16));
/*  71 */     BlockPos p2 = world.func_175725_q(pos);
/*     */     
/*  73 */     if ((!ei.nearTaintAllowed) && (nearTaint(world, p2))) {
/*  74 */       return false;
/*     */     }
/*  76 */     if (!AuraHandler.drainAura(world, p2, Aspect.FLUX, ei.cost, false))
/*  77 */       return false;
/*  78 */     boolean didit = false;
/*  79 */     switch (ei.event) {
/*     */     case 0: 
/*  81 */       if (p2.func_177956_o() + 5 < world.func_72940_L()) {
/*  82 */         EntityWisp wisp = new EntityWisp(world);
/*  83 */         wisp.func_70012_b(p2.func_177958_n(), p2.func_177956_o() + 5, p2.func_177952_p(), 0.0F, 0.0F);
/*  84 */         if (world.field_73012_v.nextInt(3) == 0)
/*  85 */           wisp.setType(Aspect.FLUX.getTag());
/*  86 */         if (world.func_72838_d(wisp))
/*  87 */           didit = true;
/*     */       }
/*  89 */       break;
/*     */     
/*     */     case 1: 
/*  92 */       BlockPos blockpos = findSpotToStrike(world, pos);
/*  93 */       if ((world.func_175678_i(blockpos)) && 
/*  94 */         (world.func_72838_d(new thaumcraft.common.entities.EntityTaintSourceLightning(world, blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p())))) {
/*  95 */         didit = true;
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  99 */       pos = p2.func_177981_b(20);
/* 100 */       if (pos.func_177956_o() < world.func_72940_L()) {
/* 101 */         EntityTaintSourceCloud e = new EntityTaintSourceCloud(world, pos, (30 + world.field_73012_v.nextInt(10)) * 20);
/* 102 */         if (world.func_72838_d(e))
/* 103 */           didit = true; }
/* 104 */       break;
/*     */     
/*     */     case 3: 
/* 107 */       if (p2.func_177956_o() + 1 < world.func_72940_L()) {
/* 108 */         EntityTaintCrawler crawler = new EntityTaintCrawler(world);
/* 109 */         crawler.func_70012_b(p2.func_177958_n(), p2.func_177956_o() + 1, p2.func_177952_p(), 0.0F, 0.0F);
/* 110 */         if (world.func_72838_d(crawler))
/* 111 */           didit = true; }
/* 112 */       break;
/*     */     
/*     */     case 4: 
/* 115 */       List<EntityPlayer> targets = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_178781_a(p2.func_177958_n(), p2.func_177956_o(), p2.func_177952_p(), p2.func_177958_n() + 1, p2.func_177956_o() + 1, p2.func_177952_p() + 1).func_72314_b(16.0D, 16.0D, 16.0D));
/*     */       
/* 117 */       if ((targets != null) && (targets.size() > 0))
/* 118 */         for (EntityPlayer target : targets) {
/* 119 */           target.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.fluxevent.1")));
/* 120 */           if (world.field_73012_v.nextFloat() < 0.25F) {
/* 121 */             ResearchHelper.addWarpToPlayer(target, 1, EnumWarpType.NORMAL);
/*     */           } else {
/* 123 */             ResearchHelper.addWarpToPlayer(target, 2 + world.field_73012_v.nextInt(4), EnumWarpType.TEMPORARY);
/*     */           }
/* 125 */           didit = true; }
/* 126 */       break;
/*     */     
/*     */ 
/*     */     case 5: 
/* 130 */       List<EntityLivingBase> targets2 = world.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_178781_a(p2.func_177958_n(), p2.func_177956_o(), p2.func_177952_p(), p2.func_177958_n() + 1, p2.func_177956_o() + 1, p2.func_177952_p() + 1).func_72314_b(16.0D, 16.0D, 16.0D));
/*     */       
/* 132 */       if ((targets2 != null) && (targets2.size() > 0))
/* 133 */         for (EntityLivingBase target : targets2) {
/* 134 */           didit = true;
/* 135 */           if ((target instanceof EntityPlayer)) {
/* 136 */             target.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.fluxevent.2")));
/*     */           }
/* 138 */           PotionEffect pe = new PotionEffect(PotionInfectiousVisExhaust.instance.func_76396_c(), 3000, 2);
/* 139 */           pe.getCurativeItems().clear();
/*     */           try {
/* 141 */             target.func_70690_d(pe);
/*     */           } catch (Exception e) {
/* 143 */             e.printStackTrace();
/*     */           } }
/* 145 */       break;
/*     */     
/*     */ 
/*     */     case 6: 
/* 149 */       List<EntityAuraNode> targets3 = world.func_72872_a(EntityAuraNode.class, AxisAlignedBB.func_178781_a(p2.func_177958_n(), p2.func_177956_o(), p2.func_177952_p(), p2.func_177958_n() + 1, p2.func_177956_o() + 1, p2.func_177952_p() + 1).func_72314_b(16.0D, 16.0D, 16.0D));
/*     */       
/* 151 */       if ((targets3 != null) && (targets3.size() > 0)) {
/* 152 */         EntityAuraNode node = (EntityAuraNode)targets3.get(world.field_73012_v.nextInt(targets3.size()));
/* 153 */         if (node != null) {
/* 154 */           didit = true;
/* 155 */           node.setNodeType(world.field_73012_v.nextBoolean() ? 5 : world.field_73012_v.nextInt(thaumcraft.common.lib.aura.NodeType.nodeTypes.length));
/*     */         }
/*     */       }
/*     */       break;
/*     */     }
/* 160 */     if (didit) AuraHandler.drainAura(world, p2, Aspect.FLUX, ei.cost);
/* 161 */     return true;
/*     */   }
/*     */   
/*     */   private static BlockPos findSpotToStrike(World world, BlockPos pos)
/*     */   {
/* 166 */     BlockPos blockpos1 = world.func_175725_q(pos);
/* 167 */     AxisAlignedBB axisalignedbb = new AxisAlignedBB(blockpos1, new BlockPos(blockpos1.func_177958_n(), world.func_72800_K(), blockpos1.func_177952_p())).func_72314_b(4.0D, 4.0D, 4.0D);
/* 168 */     List list = world.func_175647_a(EntityLivingBase.class, axisalignedbb, new Predicate()
/*     */     {
/*     */       public boolean applyLiving(EntityLivingBase living)
/*     */       {
/* 172 */         return (living != null) && (living.func_70089_S()) && (this.val$world.func_175678_i(living.func_180425_c()));
/*     */       }
/*     */       
/*     */       public boolean apply(Object p_apply_1_) {
/* 176 */         return applyLiving((EntityLivingBase)p_apply_1_);
/*     */       }
/* 178 */     });
/* 179 */     return !list.isEmpty() ? ((EntityLivingBase)list.get(world.field_73012_v.nextInt(list.size()))).func_180425_c() : blockpos1;
/*     */   }
/*     */   
/*     */   private static boolean nearTaint(World world, BlockPos pos) {
/* 183 */     for (EnumFacing d : EnumFacing.field_176754_o) {
/* 184 */       BiomeGenBase biome = world.func_180494_b(pos.func_177967_a(d, 16));
/* 185 */       if (biome.field_76756_M == Config.biomeTaintID) {
/* 186 */         return true;
/*     */       }
/*     */     }
/* 189 */     if (EntityUtils.getEntitiesInRange(world, pos, null, EntityTaintSource.class, 32.0D).size() > 0) return true;
/* 190 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/TaintEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */