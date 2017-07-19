/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*    */ 
/*    */ public class ItemAlumentum extends net.minecraft.item.Item
/*    */ {
/*    */   public ItemAlumentum()
/*    */   {
/* 14 */     func_77625_d(64);
/* 15 */     func_77627_a(true);
/* 16 */     func_77656_e(0);
/*    */   }
/*    */   
/*    */ 
/*    */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*    */   {
/* 22 */     if (!player.field_71075_bZ.field_75098_d)
/*    */     {
/* 24 */       itemstack.field_77994_a -= 1;
/*    */     }
/* 26 */     world.func_72956_a(player, "random.bow", 0.3F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/* 27 */     if (!world.field_72995_K)
/*    */     {
/* 29 */       world.func_72838_d(new EntityAlumentum(world, player));
/*    */     }
/* 31 */     return itemstack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemAlumentum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */