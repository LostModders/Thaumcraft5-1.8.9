/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.items.ItemGenericVariants;
/*    */ 
/*    */ public class ItemLootBag extends ItemGenericVariants
/*    */ {
/*    */   public ItemLootBag()
/*    */   {
/* 17 */     super(new String[] { "common", "uncommon", "rare" });
/* 18 */     func_77625_d(16);
/* 19 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack stack)
/*    */   {
/* 26 */     switch (stack.func_77952_i()) {
/* 27 */     case 1:  return EnumRarity.UNCOMMON;
/* 28 */     case 2:  return EnumRarity.RARE;
/*    */     }
/* 30 */     return EnumRarity.COMMON;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 35 */     super.func_77624_a(stack, player, list, par4);
/* 36 */     list.add(net.minecraft.util.StatCollector.func_74838_a("tc.lootbag"));
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 41 */     if (!world.field_72995_K) {
/* 42 */       int q = 8 + world.field_73012_v.nextInt(5);
/* 43 */       for (int a = 0; a < q; a++) {
/* 44 */         ItemStack is = thaumcraft.common.lib.utils.Utils.generateLoot(stack.func_77952_i(), world.field_73012_v);
/* 45 */         if (is != null) {
/* 46 */           world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, is.func_77946_l()));
/*    */         }
/*    */       }
/*    */       
/* 50 */       world.func_72956_a(player, "thaumcraft:coins", 0.75F, 1.0F);
/*    */     }
/* 52 */     stack.field_77994_a -= 1;
/* 53 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemLootBag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */