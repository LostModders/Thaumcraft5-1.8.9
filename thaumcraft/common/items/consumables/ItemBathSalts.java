/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemBathSalts
/*    */   extends Item
/*    */ {
/*    */   public ItemBathSalts()
/*    */   {
/* 13 */     func_77637_a(Thaumcraft.tabTC);
/* 14 */     func_77627_a(false);
/*    */   }
/*    */   
/*    */ 
/*    */   public int getEntityLifespan(ItemStack itemStack, World world)
/*    */   {
/* 20 */     return 200;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemBathSalts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */