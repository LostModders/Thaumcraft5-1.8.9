/*     */ package thaumcraft.common.lib.aura;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.utils.PosXY;
/*     */ 
/*     */ public class AuraThread implements Runnable
/*     */ {
/*  20 */   private final long INTERVAL = 500L;
/*     */   
/*  22 */   private boolean stop = false;
/*     */   
/*  24 */   Random rand = new Random(System.currentTimeMillis());
/*     */   
/*     */   public void run()
/*     */   {
/*  28 */     Thaumcraft.log.info("Starting aura thread");
/*     */     
/*  30 */     while (!this.stop)
/*     */     {
/*  32 */       if (AuraHandler.auras.isEmpty()) {
/*  33 */         Thaumcraft.log.warn("No auras found!");
/*  34 */         break;
/*     */       }
/*     */       
/*  37 */       long startTime = System.currentTimeMillis();
/*     */       
/*  39 */       for (Iterator i$ = AuraHandler.auras.values().iterator(); i$.hasNext();) { auraWorld = (AuraWorld)i$.next();
/*  40 */         for (AuraChunk auraChunk : auraWorld.auraChunks.values()) {
/*  41 */           processAuraChunk(auraWorld, auraChunk);
/*     */         }
/*     */       }
/*     */       AuraWorld auraWorld;
/*  45 */       long executionTime = System.currentTimeMillis() - startTime;
/*     */       try
/*     */       {
/*  48 */         if (executionTime > 500L)
/*  49 */           Thaumcraft.log.warn("AURAS TAKING " + (executionTime - 500L) + " ms LONGER THAN NORMAL");
/*  50 */         Thread.sleep(Math.max(1L, 500L - executionTime));
/*     */       }
/*     */       catch (InterruptedException e) {}
/*     */     }
/*  54 */     Thaumcraft.log.info("Stopping aura thread");
/*  55 */     Thaumcraft.proxy.setAuraThread(null);
/*     */   }
/*     */   
/*     */   private void processAuraChunk(AuraWorld auraWorld, AuraChunk auraChunk) {
/*  59 */     java.util.List<Integer> directions = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3) });
/*  60 */     java.util.Collections.shuffle(directions, this.rand);
/*  61 */     int x = auraChunk.loc.x;
/*  62 */     int y = auraChunk.loc.y;
/*  63 */     short base = auraChunk.getBase();
/*  64 */     AspectList current = auraChunk.getCurrentAspects();
/*  65 */     AspectList ticketaura = (AspectList)auraWorld.getNodeTickets().get(auraChunk.loc);
/*  66 */     boolean dirty = false;
/*     */     
/*     */ 
/*  69 */     if (ticketaura != null)
/*     */     {
/*     */ 
/*  72 */       float smallbase = base * 0.1F;
/*  73 */       Aspect[] aspects = ticketaura.copy().getAspects();
/*  74 */       for (Aspect aspect : aspects)
/*     */       {
/*  76 */         int amount = current.getAmount(aspect);
/*  77 */         int change = amount;
/*  78 */         int len = ticketaura.getAmount(aspect);
/*  79 */         ticketaura.reduce(aspect, len);
/*  80 */         int i; if (len > 10) {
/*  81 */           int i = len / 10;
/*  82 */           len = 10;
/*     */         }
/*     */         else {
/*  85 */           i = 1;
/*     */         }
/*  87 */         int modify = aspect == Aspect.FLUX ? 2 : 1;
/*  88 */         for (int c = 0; c < len; c++)
/*     */         {
/*     */ 
/*     */ 
/*  92 */           if (change > base)
/*     */           {
/*  94 */             float f = (change - base) / smallbase / modify;
/*  95 */             if (this.rand.nextFloat() > f) {
/*  96 */               change += i;
/*     */             }
/*  98 */             else if (this.rand.nextFloat() > 0.33D) {
/*  99 */               updateRandomChunk(aspect, auraWorld, auraChunk, i);
/*     */             }
/*     */           }
/*     */           else {
/* 103 */             change += i;
/*     */           }
/*     */         }
/* 106 */         int total = change - amount;
/* 107 */         if (total != 0) {
/* 108 */           current.add(aspect, total);
/* 109 */           dirty = true;
/*     */         }
/*     */       }
/*     */     }
/* 113 */     Aspect[] aspects = current.getAspects();
/*     */     
/* 115 */     for (Aspect aspect : aspects) {
/* 116 */       int currentAvailableAmount = current.getAmount(aspect);
/* 117 */       AuraChunk neighbour = null;
/* 118 */       int lowest = Integer.MAX_VALUE;
/* 119 */       for (Integer a : directions) {
/* 120 */         EnumFacing dir = EnumFacing.func_176731_b(a.intValue());
/* 121 */         AuraChunk n = auraWorld.getAuraChunkAt(x + dir.func_82601_c(), y + dir.func_82599_e());
/* 122 */         if (n != null) {
/* 123 */           int currentamount = n.getCurrentAspects().getAmount(aspect);
/* 124 */           if (((neighbour == null) || (lowest > currentamount)) && (currentamount < n.getBase()) && (currentAvailableAmount > currentamount)) {
/* 125 */             neighbour = n;
/* 126 */             lowest = currentamount;
/*     */           }
/*     */         }
/*     */       }
/* 130 */       if (neighbour != null) {
/* 131 */         AspectList neighbourCurrent = neighbour.getCurrentAspects();
/* 132 */         int neighbouramount = neighbourCurrent.getAmount(aspect);
/* 133 */         int m = (int)Math.max(Config.AURABASE / 20, Math.min(Config.AURABASE / 5, neighbouramount * (aspect == Aspect.FLUX ? 0.66D : 0.25D)));
/* 134 */         if ((neighbouramount < currentAvailableAmount - m) && (neighbouramount < neighbour.getBase())) {
/* 135 */           neighbourCurrent.add(aspect, 1);
/* 136 */           current.reduce(aspect, 1);
/* 137 */           dirty = true;
/* 138 */           markChunkAsDirty(neighbour, auraWorld.dim);
/*     */         }
/*     */       }
/* 141 */       if ((aspect != Aspect.FLUX) && (currentAvailableAmount < base / 15.0F) && (this.rand.nextFloat() < (base / 15.0F - currentAvailableAmount) / (base * 75.0F))) {
/* 142 */         current.add(Aspect.FLUX, 1);
/* 143 */         dirty = true;
/*     */       }
/*     */     }
/*     */     
/* 147 */     if (dirty) {
/* 148 */       markChunkAsDirty(auraChunk, auraWorld.dim);
/*     */     }
/*     */     
/* 151 */     int flux = current.getAmount(Aspect.FLUX);
/* 152 */     if ((flux > base * 0.75D) && (this.rand.nextFloat() < flux / (Config.AURABASE * 100))) {
/* 153 */       AuraHandler.taintTrigger.put(Integer.valueOf(auraWorld.dim), new net.minecraft.util.BlockPos(x * 16, 0, y * 16));
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateRandomChunk(Aspect aspect, AuraWorld auraWorld, AuraChunk auraChunk, int amount)
/*     */   {
/* 159 */     EnumFacing rd = EnumFacing.field_176754_o[this.rand.nextInt(4)];
/* 160 */     PosXY p = new PosXY(auraChunk.loc);
/* 161 */     p.x += rd.func_82601_c() * MathHelper.func_76136_a(this.rand, 1, 3);
/* 162 */     p.y += rd.func_82599_e() * MathHelper.func_76136_a(this.rand, 1, 3);
/* 163 */     AuraHandler.addRechargeTicket(auraWorld.dim, p, aspect, amount);
/*     */   }
/*     */   
/*     */   private void markChunkAsDirty(AuraChunk chunk, int dim) {
/* 167 */     if (chunk.isModified()) return;
/* 168 */     PosXY pos = new PosXY(chunk.loc.x, chunk.loc.y);
/* 169 */     if (!AuraHandler.dirtyChunks.containsKey(Integer.valueOf(dim)))
/* 170 */       AuraHandler.dirtyChunks.put(Integer.valueOf(dim), new CopyOnWriteArrayList());
/* 171 */     CopyOnWriteArrayList<PosXY> dc = (CopyOnWriteArrayList)AuraHandler.dirtyChunks.get(Integer.valueOf(dim));
/* 172 */     if (!dc.contains(pos))
/* 173 */       dc.add(pos);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 177 */     this.stop = true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/AuraThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */