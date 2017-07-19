/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.internal.EnumWarpType;
/*    */ import thaumcraft.api.research.ResearchHelper;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemZombieBrain extends ItemFood
/*    */ {
/*    */   public ItemZombieBrain()
/*    */   {
/* 18 */     super(4, 0.2F, true);
/* 19 */     func_77844_a(Potion.field_76438_s.field_76415_H, 30, 0, 0.8F);
/* 20 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public void func_77849_c(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 25 */     if ((!world.field_72995_K) && ((player instanceof EntityPlayerMP))) {
/* 26 */       if (world.field_73012_v.nextFloat() < 0.1F) {
/* 27 */         ResearchHelper.addWarpToPlayer(player, 1, EnumWarpType.NORMAL);
/*    */       } else {
/* 29 */         ResearchHelper.addWarpToPlayer(player, 1 + world.field_73012_v.nextInt(3), EnumWarpType.TEMPORARY);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemZombieBrain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */