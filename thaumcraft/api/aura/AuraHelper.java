/*    */ package thaumcraft.api.aura;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.internal.IInternalMethodHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuraHelper
/*    */ {
/*    */   public static boolean drainAura(World world, BlockPos pos, Aspect aspect, int amount)
/*    */   {
/* 21 */     return ThaumcraftApi.internalMethods.drainAura(world, pos, aspect, amount);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static int drainAuraAvailable(World world, BlockPos pos, Aspect aspect, int amount)
/*    */   {
/* 33 */     return ThaumcraftApi.internalMethods.drainAuraAvailable(world, pos, aspect, amount);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void addAura(World world, BlockPos pos, Aspect aspect, int amount)
/*    */   {
/* 46 */     ThaumcraftApi.internalMethods.addAura(world, pos, aspect, amount);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static int getAura(World world, BlockPos pos, Aspect aspect)
/*    */   {
/* 57 */     return ThaumcraftApi.internalMethods.getAura(world, pos, aspect);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static int getAuraBase(World world, BlockPos pos)
/*    */   {
/* 67 */     return ThaumcraftApi.internalMethods.getAuraBase(world, pos);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean shouldPreserveAura(World world, EntityPlayer player, BlockPos pos, Aspect aspect)
/*    */   {
/* 80 */     return ThaumcraftApi.internalMethods.shouldPreserveAura(world, player, pos, aspect);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void pollute(World world, BlockPos pos, int amount, boolean showEffect)
/*    */   {
/* 91 */     ThaumcraftApi.internalMethods.pollute(world, pos, amount, showEffect);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/aura/AuraHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */