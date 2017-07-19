/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ 
/*    */ public class ItemRunedTablet
/*    */   extends Item
/*    */ {
/*    */   public ItemRunedTablet()
/*    */   {
/* 18 */     func_77625_d(1);
/* 19 */     func_77627_a(true);
/* 20 */     func_77656_e(0);
/* 21 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack stack)
/*    */   {
/* 26 */     return EnumRarity.RARE;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 31 */     super.func_77624_a(stack, player, list, par4);
/* 32 */     list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.runed_tablet.text"));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemRunedTablet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */