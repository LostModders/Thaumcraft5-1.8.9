/*    */ package thaumcraft.common.items.resources;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.item.EntityXPOrb;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemKnowledgeFragment
/*    */   extends Item
/*    */ {
/*    */   public ItemKnowledgeFragment()
/*    */   {
/* 18 */     func_77625_d(64);
/*    */   }
/*    */   
/*    */ 
/*    */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*    */   {
/* 24 */     if (!world.field_72995_K)
/*    */     {
/* 26 */       if (!player.field_71075_bZ.field_75098_d) itemstack.field_77994_a -= 1;
/* 27 */       int i = 4 + world.field_73012_v.nextInt(5);
/* 28 */       while (i > 0)
/*    */       {
/* 30 */         int j = EntityXPOrb.func_70527_a(i);
/* 31 */         i -= j;
/* 32 */         world.func_72838_d(new EntityXPOrb(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, j));
/*    */       }
/*    */     }
/* 35 */     return itemstack;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 40 */     list.add("§6" + StatCollector.func_74838_a("item.knowledge_fragment.help"));
/* 41 */     super.func_77624_a(stack, player, list, par4);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/resources/ItemKnowledgeFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */