/*     */ package thaumcraft.common.lib.aura;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.utils.PosXY;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*     */ 
/*     */ public class AuraHandler
/*     */ {
/*  24 */   static ConcurrentHashMap<Integer, AuraWorld> auras = new ConcurrentHashMap();
/*  25 */   public static ConcurrentHashMap<Integer, CopyOnWriteArrayList<PosXY>> dirtyChunks = new ConcurrentHashMap();
/*  26 */   public static ConcurrentHashMap<Integer, BlockPos> taintTrigger = new ConcurrentHashMap();
/*     */   
/*     */   public static AuraWorld getAuraWorld(int dim) {
/*  29 */     return (AuraWorld)auras.get(Integer.valueOf(dim));
/*     */   }
/*     */   
/*     */   public static AuraChunk getAuraChunk(int dim, int x, int y) {
/*  33 */     if (auras.containsKey(Integer.valueOf(dim))) {
/*  34 */       return ((AuraWorld)auras.get(Integer.valueOf(dim))).getAuraChunkAt(x, y);
/*     */     }
/*  36 */     return null;
/*     */   }
/*     */   
/*     */   public static void addAuraWorld(int dim) {
/*  40 */     if (!auras.containsKey(Integer.valueOf(dim))) {
/*  41 */       auras.put(Integer.valueOf(dim), new AuraWorld(dim));
/*  42 */       Thaumcraft.log.info("Creating aura cache for world " + dim);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void removeAuraWorld(int dim) {
/*  47 */     auras.remove(Integer.valueOf(dim));
/*  48 */     Thaumcraft.log.info("Removing aura cache for world " + dim);
/*     */   }
/*     */   
/*     */   public static void addAuraChunk(int dim, Chunk chunk, short base, AspectList currentAspects) {
/*  52 */     AuraWorld aw = (AuraWorld)auras.get(Integer.valueOf(dim));
/*  53 */     if (aw == null) {
/*  54 */       aw = new AuraWorld(dim);
/*     */     }
/*     */     
/*  57 */     aw.getAuraChunks().put(new PosXY(chunk.field_76635_g, chunk.field_76647_h), new AuraChunk(chunk, base, currentAspects));
/*     */     
/*  59 */     auras.put(Integer.valueOf(dim), aw);
/*     */   }
/*     */   
/*     */   public static void removeAuraChunk(int dim, int x, int y) {
/*  63 */     AuraWorld aw = (AuraWorld)auras.get(Integer.valueOf(dim));
/*  64 */     if (aw != null) {
/*  65 */       aw.getAuraChunks().remove(new PosXY(x, y));
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean drainAura(World world, BlockPos pos, AspectList al) {
/*  70 */     for (Aspect aspect : al.getAspects()) {
/*  71 */       if ((aspect != null) && 
/*  72 */         (!drainAura(world, pos, aspect, al.getAmount(aspect), false))) return false;
/*     */     }
/*  74 */     for (Aspect aspect : al.getAspects()) {
/*  75 */       if (aspect != null)
/*  76 */         drainAura(world, pos, aspect, al.getAmount(aspect), true);
/*     */     }
/*  78 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean drainAura(World world, BlockPos pos, Aspect aspect, int amount) {
/*  82 */     return drainAura(world, pos, aspect, amount, true);
/*     */   }
/*     */   
/*     */   public static int drainAuraAvailable(World world, BlockPos pos, Aspect aspect, int amount) {
/*  86 */     return drainAuraAvailable(world, pos, aspect, amount, true);
/*     */   }
/*     */   
/*     */   public static boolean drainAura(World world, BlockPos pos, Aspect aspect, int amount, boolean doit) {
/*  90 */     AuraChunk ac = getAuraChunk(world.field_73011_w.func_177502_q(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/*  91 */     return modifyAuraChunk(ac, aspect, -amount, doit);
/*     */   }
/*     */   
/*     */   public static int getAuraCurrent(World world, BlockPos pos, Aspect aspect) {
/*  95 */     AuraChunk ac = getAuraChunk(world.field_73011_w.func_177502_q(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/*  96 */     return ac != null ? ac.getCurrentAspects().getAmount(aspect) : 0;
/*     */   }
/*     */   
/*     */   public static int getAuraBase(World world, BlockPos pos) {
/* 100 */     AuraChunk ac = getAuraChunk(world.field_73011_w.func_177502_q(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/* 101 */     return ac != null ? ac.getBase() : 0;
/*     */   }
/*     */   
/*     */   public static boolean shouldPreserveAura(World world, EntityPlayer player, BlockPos pos, Aspect aspect) {
/* 105 */     return ((player == null) || (thaumcraft.api.research.ResearchHelper.isResearchComplete(player.func_70005_c_(), "AURAPRESERVE"))) && (getAuraCurrent(world, pos, aspect) / getAuraBase(world, pos) < 0.1D);
/*     */   }
/*     */   
/*     */ 
/*     */   public static int drainAuraAvailable(World world, BlockPos pos, Aspect aspect, int amount, boolean doit)
/*     */   {
/* 111 */     boolean didit = false;
/*     */     try {
/* 113 */       AuraChunk ac = getAuraChunk(world.field_73011_w.func_177502_q(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/* 114 */       if (amount > ac.getCurrentAspects().getAmount(aspect)) {
/* 115 */         amount = ac.getCurrentAspects().getAmount(aspect);
/*     */       }
/* 117 */       didit = modifyAuraChunk(ac, aspect, -amount, doit);
/*     */     } catch (Exception e) {}
/* 119 */     return didit ? amount : 0;
/*     */   }
/*     */   
/*     */   private static boolean modifyAuraChunk(AuraChunk ac, Aspect aspect, int amount, boolean doit) {
/* 123 */     if (ac != null) {
/* 124 */       if (amount < 0) {
/* 125 */         if (Math.abs(amount) > ac.getCurrentAspects().getAmount(aspect)) return false;
/* 126 */         if (doit) ac.getCurrentAspects().reduce(aspect, Math.abs(amount));
/*     */       }
/* 128 */       else if (doit) { ac.getCurrentAspects().add(aspect, amount);
/*     */       }
/* 130 */       return true;
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */   
/*     */   public static void addNodeRechargeTicket(EntityAuraNode node, Aspect aspect, int strength)
/*     */   {
/* 137 */     PosXY pos = new PosXY(MathHelper.func_76128_c(node.field_70165_t) >> 4, MathHelper.func_76128_c(node.field_70161_v) >> 4);
/* 138 */     addRechargeTicket(node.field_70170_p.field_73011_w.func_177502_q(), pos, aspect, strength);
/*     */   }
/*     */   
/*     */   public static void addRechargeTicket(World world, BlockPos bp, Aspect aspect) {
/* 142 */     PosXY pos = new PosXY(bp.func_177958_n() >> 4, bp.func_177952_p() >> 4);
/* 143 */     addRechargeTicket(world.field_73011_w.func_177502_q(), pos, aspect, 1);
/*     */   }
/*     */   
/*     */   public static void addRechargeTicket(World world, BlockPos bp, Aspect aspect, int amt) {
/* 147 */     PosXY pos = new PosXY(bp.func_177958_n() >> 4, bp.func_177952_p() >> 4);
/* 148 */     addRechargeTicket(world.field_73011_w.func_177502_q(), pos, aspect, amt);
/*     */   }
/*     */   
/*     */   public static void addRechargeTicket(int dim, PosXY pos, Aspect aspect, int amt) {
/* 152 */     AuraWorld aw = (AuraWorld)auras.get(Integer.valueOf(dim));
/* 153 */     if (aw != null) {
/* 154 */       if (!aw.getNodeTickets().containsKey(pos)) {
/* 155 */         aw.getNodeTickets().put(pos, new AspectList());
/*     */       }
/* 157 */       AspectList al = (AspectList)aw.getNodeTickets().get(pos);
/* 158 */       if (al != null) al.add(aspect, amt);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void generateAura(Chunk chunk, Random rand) {
/* 163 */     BiomeGenBase bgb = chunk.func_177412_p().func_180494_b(new BlockPos(chunk.field_76635_g * 16 + 8, 50, chunk.field_76647_h * 16 + 8));
/*     */     
/* 165 */     if (BiomeHandler.getBiomeBlacklist(bgb.field_76756_M) != -1) { return;
/*     */     }
/* 167 */     float life = BiomeHandler.getBiomeAuraModifier(bgb);
/* 168 */     for (int a = 0; a < 4; a++) {
/* 169 */       EnumFacing dir = EnumFacing.func_176731_b(a);
/* 170 */       BiomeGenBase bgb2 = chunk.func_177412_p().func_180494_b(new BlockPos((chunk.field_76635_g + dir.func_82601_c()) * 16 + 8, 50, (chunk.field_76647_h + dir.func_82599_e()) * 16 + 8));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 175 */       life += BiomeHandler.getBiomeAuraModifier(bgb2);
/*     */     }
/* 177 */     life /= 5.0F;
/*     */     
/* 179 */     Aspect auraBonus = BiomeHandler.getRandomBiomeTag(bgb.field_76756_M, rand);
/*     */     
/* 181 */     short base = 0;
/* 182 */     AspectList current = new AspectList();
/* 183 */     for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 184 */       float noise = aspect == auraBonus ? 1.0F : 0.5F + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/* 185 */       short b = (short)(int)(life * Config.AURABASE * noise);
/* 186 */       if (b > base) base = b;
/* 187 */       current.add(aspect, (int)(life * (Config.AURABASE * (0.8D + rand.nextFloat() * 0.2F)) * noise));
/*     */     }
/*     */     
/* 190 */     addAuraChunk(chunk.func_177412_p().field_73011_w.func_177502_q(), chunk, base, current);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/AuraHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */