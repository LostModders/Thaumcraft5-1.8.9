/*    */ package thaumcraft.common.lib.aura;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import net.minecraft.world.chunk.Chunk;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.lib.utils.PosXY;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuraChunk
/*    */ {
/*    */   PosXY loc;
/*    */   short base;
/* 14 */   AspectList currentAspects = new AspectList();
/*    */   WeakReference<Chunk> chunkRef;
/*    */   
/*    */   public AuraChunk(PosXY loc)
/*    */   {
/* 19 */     this.loc = loc;
/*    */   }
/*    */   
/*    */   public AuraChunk(Chunk chunk, short base, AspectList currentAspects) {
/* 23 */     this.loc = new PosXY(chunk.field_76635_g, chunk.field_76647_h);
/* 24 */     this.chunkRef = new WeakReference(chunk);
/* 25 */     this.base = base;
/* 26 */     this.currentAspects = currentAspects;
/*    */   }
/*    */   
/*    */   public boolean isModified() {
/* 30 */     if ((this.chunkRef != null) && (this.chunkRef.get() != null)) {
/* 31 */       return ((Chunk)this.chunkRef.get()).func_76601_a(false);
/*    */     }
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   public short getBase() {
/* 37 */     return this.base;
/*    */   }
/*    */   
/*    */   public void setBase(short base) {
/* 41 */     this.base = base;
/*    */   }
/*    */   
/*    */   public AspectList getCurrentAspects() {
/* 45 */     return this.currentAspects;
/*    */   }
/*    */   
/*    */   public void setCurrentAspects(AspectList currentAspects) {
/* 49 */     this.currentAspects = currentAspects;
/*    */   }
/*    */   
/*    */   public PosXY getLoc() {
/* 53 */     return this.loc;
/*    */   }
/*    */   
/*    */   public void setLoc(PosXY loc) {
/* 57 */     this.loc = loc;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/aura/AuraChunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */