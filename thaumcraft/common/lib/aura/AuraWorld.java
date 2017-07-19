/*    */ package thaumcraft.common.lib.aura;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.lib.utils.PosXY;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuraWorld
/*    */ {
/*    */   int dim;
/* 12 */   ConcurrentHashMap<PosXY, AuraChunk> auraChunks = new ConcurrentHashMap();
/* 13 */   ConcurrentHashMap<PosXY, AspectList> nodeTickets = new ConcurrentHashMap();
/*    */   
/*    */   public AuraWorld(int dim) {
/* 16 */     this.dim = dim;
/*    */   }
/*    */   
/*    */   public ConcurrentHashMap<PosXY, AuraChunk> getAuraChunks() {
/* 20 */     return this.auraChunks;
/*    */   }
/*    */   
/*    */   public void setAuraChunks(ConcurrentHashMap<PosXY, AuraChunk> auraChunks) {
/* 24 */     this.auraChunks = auraChunks;
/*    */   }
/*    */   
/*    */   public AuraChunk getAuraChunkAt(int x, int y) {
/* 28 */     return getAuraChunkAt(new PosXY(x, y));
/*    */   }
/*    */   
/*    */   public AuraChunk getAuraChunkAt(PosXY loc) {
/* 32 */     return (AuraChunk)this.auraChunks.get(loc);
/*    */   }
/*    */   
/*    */   public ConcurrentHashMap<PosXY, AspectList> getNodeTickets() {
/* 36 */     return this.nodeTickets;
/*    */   }
/*    */   
/*    */   public void setNodeTickets(ConcurrentHashMap<PosXY, AspectList> nodeTickets) {
/* 40 */     this.nodeTickets = nodeTickets;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/AuraWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */