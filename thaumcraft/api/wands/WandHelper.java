/*    */ package thaumcraft.api.wands;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.internal.IInternalMethodHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WandHelper
/*    */ {
/*    */   public static boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting)
/*    */   {
/* 22 */     return ThaumcraftApi.internalMethods.consumeVisFromWand(wand, player, cost, doit, crafting);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean consumeVisFromInventory(EntityPlayer player, AspectList cost)
/*    */   {
/* 33 */     return ThaumcraftApi.internalMethods.consumeVisFromInventory(player, cost);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/wands/WandHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */