/*    */ package thaumcraft.common.lib.utils;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomItemChooser
/*    */ {
/*    */   public Item chooseOnWeight(List<Item> items)
/*    */   {
/* 12 */     double completeWeight = 0.0D;
/* 13 */     for (Item item : items)
/* 14 */       completeWeight += item.getWeight();
/* 15 */     double r = Math.random() * completeWeight;
/* 16 */     double countWeight = 0.0D;
/* 17 */     for (Item item : items) {
/* 18 */       countWeight += item.getWeight();
/* 19 */       if (countWeight >= r)
/* 20 */         return item;
/*    */     }
/* 22 */     throw new RuntimeException("Should never be shown.");
/*    */   }
/*    */   
/*    */   public static abstract interface Item
/*    */   {
/*    */     public abstract double getWeight();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/utils/RandomItemChooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */