/*    */ package thaumcraft.client.fx.strips;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FixedList<T>
/*    */   extends ArrayList<T>
/*    */ {
/*    */   private int maxSize;
/*    */   
/*    */   public FixedList(int capacity, Class<T> type)
/*    */   {
/* 19 */     super(capacity);
/* 20 */     this.maxSize = capacity;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void insert(T t)
/*    */   {
/* 31 */     add(0, t);
/* 32 */     while (size() > this.maxSize) {
/* 33 */       remove(size() - 1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/strips/FixedList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */