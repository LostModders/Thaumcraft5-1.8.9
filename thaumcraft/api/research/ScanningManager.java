/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.StatCollector;
/*    */ 
/*    */ 
/*    */ public class ScanningManager
/*    */ {
/* 11 */   static ArrayList<IScanThing> things = new ArrayList();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void addScannableThing(IScanThing obj)
/*    */   {
/* 20 */     things.add(obj);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void scanTheThing(EntityPlayer player, Object object)
/*    */   {
/* 29 */     boolean found = false;
/* 30 */     for (IScanThing thing : things) {
/* 31 */       if ((thing.checkThing(player, object)) && 
/* 32 */         (ResearchHelper.completeResearch(player, thing.getResearchKey()))) {
/* 33 */         found = true;
/*    */       }
/*    */     }
/*    */     
/* 37 */     if (!found) {
/* 38 */       player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.unknownobject")));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean isThingStillScannable(EntityPlayer player, Object object)
/*    */   {
/* 48 */     for (IScanThing thing : things) {
/* 49 */       if ((thing.checkThing(player, object)) && 
/* 50 */         (!ResearchHelper.isResearchComplete(player.func_70005_c_(), thing.getResearchKey()))) {
/* 51 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 55 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ScanningManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */