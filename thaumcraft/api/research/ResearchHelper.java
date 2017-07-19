/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ 
/*    */ public class ResearchHelper
/*    */ {
/*    */   public static boolean completeResearch(EntityPlayer player, String researchkey)
/*    */   {
/* 10 */     return ThaumcraftApi.internalMethods.completeResearch(player, researchkey);
/*    */   }
/*    */   
/*    */   public static boolean isResearchComplete(String username, String[] researchkeys) {
/* 14 */     for (String key : researchkeys) if (!isResearchComplete(username, key)) return false;
/* 15 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean isResearchComplete(String username, String researchkey) {
/* 19 */     return ThaumcraftApi.internalMethods.isResearchComplete(username, researchkey);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void addWarpToPlayer(EntityPlayer player, int amount, thaumcraft.api.internal.EnumWarpType type)
/*    */   {
/* 29 */     ThaumcraftApi.internalMethods.addWarpToPlayer(player, amount, type);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static int getPlayerWarp(EntityPlayer player, thaumcraft.api.internal.EnumWarpType type)
/*    */   {
/* 39 */     return ThaumcraftApi.internalMethods.getPlayerWarp(player, type);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ResearchHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */