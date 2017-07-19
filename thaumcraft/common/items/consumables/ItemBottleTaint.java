/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.entities.projectile.EntityBottleTaint;
/*    */ 
/*    */ public class ItemBottleTaint extends net.minecraft.item.Item
/*    */ {
/*    */   public ItemBottleTaint()
/*    */   {
/* 15 */     this.field_77777_bU = 8;
/* 16 */     func_77656_e(0);
/* 17 */     func_77637_a(Thaumcraft.tabTC);
/* 18 */     func_77627_a(false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 25 */     if (!player.field_71075_bZ.field_75098_d)
/*    */     {
/* 27 */       stack.field_77994_a -= 1;
/*    */     }
/*    */     
/* 30 */     world.func_72956_a(player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*    */     
/* 32 */     if (!world.field_72995_K)
/*    */     {
/* 34 */       world.func_72838_d(new EntityBottleTaint(world, player));
/*    */     }
/*    */     
/* 37 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemBottleTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */