/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.research.ResearchHelper;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemCrimsonRites extends Item
/*    */ {
/*    */   public ItemCrimsonRites()
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
/* 32 */     list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.crimson_rites.text.0"));
/* 33 */     list.add(EnumChatFormatting.DARK_BLUE + StatCollector.func_74838_a("item.crimson_rites.text.1"));
/*    */   }
/*    */   
/*    */ 
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 39 */     if ((!world.field_72995_K) && (ResearchHelper.completeResearch(player, "CRIMSON")))
/* 40 */       world.func_72956_a(player, "thaumcraft:learn", 0.75F, 1.0F);
/* 41 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemCrimsonRites.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */