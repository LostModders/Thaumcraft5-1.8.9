/*    */ package thaumcraft.api.items;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract interface IArchitect
/*    */ {
/*    */   public abstract ArrayList<BlockPos> getArchitectBlocks(ItemStack paramItemStack, World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing, EntityPlayer paramEntityPlayer);
/*    */   
/*    */   public abstract boolean showAxis(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, EnumFacing paramEnumFacing, EnumAxis paramEnumAxis);
/*    */   
/*    */   public static enum EnumAxis
/*    */   {
/* 26 */     X, 
/* 27 */     Y, 
/* 28 */     Z;
/*    */     
/*    */     private EnumAxis() {}
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/items/IArchitect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */