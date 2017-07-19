/*    */ package thaumcraft.common.tiles.essentia;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ 
/*    */ public class TileTubeRestrict
/*    */   extends TileTube
/*    */ {
/*    */   void calculateSuction(Aspect filter, boolean restrict, boolean dir)
/*    */   {
/* 11 */     super.calculateSuction(filter, true, dir);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileTubeRestrict.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */