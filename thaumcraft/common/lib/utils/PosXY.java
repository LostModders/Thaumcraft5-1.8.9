/*    */ package thaumcraft.common.lib.utils;
/*    */ 
/*    */ public class PosXY
/*    */   implements Comparable
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   
/*    */   public PosXY() {}
/*    */   
/*    */   public PosXY(int x, int z)
/*    */   {
/* 13 */     this.x = x;
/* 14 */     this.y = z;
/*    */   }
/*    */   
/*    */   public PosXY(PosXY c)
/*    */   {
/* 19 */     this.x = c.x;
/* 20 */     this.y = c.y;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o)
/*    */   {
/* 25 */     if (!(o instanceof PosXY))
/*    */     {
/* 27 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 31 */     PosXY chunkcoordinates = (PosXY)o;
/* 32 */     return (this.x == chunkcoordinates.x) && (this.y == chunkcoordinates.y);
/*    */   }
/*    */   
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 38 */     return this.x + this.y << 8;
/*    */   }
/*    */   
/*    */   public int compareTo(PosXY c)
/*    */   {
/* 43 */     return this.y == c.y ? this.x - c.x : this.y - c.y;
/*    */   }
/*    */   
/*    */   public void set(int x, int z)
/*    */   {
/* 48 */     this.x = x;
/* 49 */     this.y = z;
/*    */   }
/*    */   
/*    */   public float getDistanceSquared(int x, int z)
/*    */   {
/* 54 */     float f = this.x - x;
/* 55 */     float f2 = this.y - z;
/* 56 */     return f * f + f2 * f2;
/*    */   }
/*    */   
/*    */   public float getDistanceSquaredToChunkCoordinates(PosXY c)
/*    */   {
/* 61 */     return getDistanceSquared(c.x, c.y);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 66 */     return "Pos{x=" + this.x + ", y=" + this.y + '}';
/*    */   }
/*    */   
/*    */   public int compareTo(Object o)
/*    */   {
/* 71 */     return compareTo((PosXY)o);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/utils/PosXY.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */