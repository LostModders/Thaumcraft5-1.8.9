/*     */ package thaumcraft.common.lib.research;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PlayerKnowledge
/*     */ {
/*   9 */   public Map<String, ArrayList<String>> researchCompleted = new HashMap();
/*  10 */   public Map<String, HashMap<String, Byte>> researchCompletedFlags = new HashMap();
/*  11 */   public Map<String, Integer> warpCount = new HashMap();
/*  12 */   public Map<String, Integer> warp = new HashMap();
/*  13 */   public Map<String, Integer> warpSticky = new HashMap();
/*  14 */   public Map<String, Integer> warpTemp = new HashMap();
/*     */   
/*     */   public void wipePlayerKnowledge(String player) {
/*  17 */     this.researchCompleted.remove(player);
/*     */     
/*  19 */     this.warp.remove(player);
/*  20 */     this.warpTemp.remove(player);
/*  21 */     this.warpSticky.remove(player);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getWarpCounter(String player)
/*     */   {
/*  27 */     int known = 0;
/*  28 */     if (!this.warpCount.containsKey(player)) {
/*  29 */       this.warpCount.put(player, Integer.valueOf(0));
/*     */     } else {
/*  31 */       known = ((Integer)this.warpCount.get(player)).intValue();
/*     */     }
/*  33 */     return known;
/*     */   }
/*     */   
/*     */   public void setWarpCounter(String player, int amount) {
/*  37 */     this.warpCount.put(player, Integer.valueOf(amount));
/*     */   }
/*     */   
/*     */   public int getWarpTotal(String player) {
/*  41 */     return getWarpPerm(player) + getWarpTemp(player) + getWarpSticky(player);
/*     */   }
/*     */   
/*     */   public int getWarpPerm(String player) {
/*  45 */     if (player == null) return 0;
/*  46 */     int known = 0;
/*     */     try {
/*  48 */       if (!this.warp.containsKey(player)) {
/*  49 */         this.warp.put(player, Integer.valueOf(0));
/*     */       } else {
/*  51 */         known = ((Integer)this.warp.get(player)).intValue();
/*     */       }
/*     */     } catch (Exception e) {}
/*  54 */     return known;
/*     */   }
/*     */   
/*     */   public int getWarpTemp(String player) {
/*  58 */     if (player == null) return 0;
/*  59 */     int known = 0;
/*     */     try {
/*  61 */       if (!this.warpTemp.containsKey(player)) {
/*  62 */         this.warpTemp.put(player, Integer.valueOf(0));
/*     */       } else {
/*  64 */         known = ((Integer)this.warpTemp.get(player)).intValue();
/*     */       }
/*     */     } catch (Exception e) {}
/*  67 */     return known;
/*     */   }
/*     */   
/*     */   public int getWarpSticky(String player) {
/*  71 */     if (player == null) return 0;
/*  72 */     int known = 0;
/*     */     try {
/*  74 */       if (!this.warpSticky.containsKey(player)) {
/*  75 */         this.warpSticky.put(player, Integer.valueOf(0));
/*     */       } else {
/*  77 */         known = ((Integer)this.warpSticky.get(player)).intValue();
/*     */       }
/*     */     } catch (Exception e) {}
/*  80 */     return known;
/*     */   }
/*     */   
/*     */   public void addWarpTemp(String player, int amount) {
/*  84 */     int er = getWarpTemp(player) + amount;
/*  85 */     this.warpTemp.put(player, Integer.valueOf(Math.max(0, er)));
/*     */   }
/*     */   
/*     */   public void addWarpPerm(String player, int amount) {
/*  89 */     int er = getWarpPerm(player) + amount;
/*  90 */     this.warp.put(player, Integer.valueOf(Math.max(0, er)));
/*     */   }
/*     */   
/*     */   public void addWarpSticky(String player, int amount) {
/*  94 */     int er = getWarpSticky(player) + amount;
/*  95 */     this.warpSticky.put(player, Integer.valueOf(Math.max(0, er)));
/*     */   }
/*     */   
/*     */   public void setWarpSticky(String player, int amount) {
/*  99 */     this.warpSticky.put(player, Integer.valueOf(Math.max(0, amount)));
/*     */   }
/*     */   
/*     */   public void setWarpPerm(String player, int amount) {
/* 103 */     this.warp.put(player, Integer.valueOf(Math.max(0, amount)));
/*     */   }
/*     */   
/*     */   public void setWarpTemp(String player, int amount) {
/* 107 */     this.warpTemp.put(player, Integer.valueOf(Math.max(0, amount)));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/research/PlayerKnowledge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */