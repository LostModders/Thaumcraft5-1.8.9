/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumAction;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.internal.EnumWarpType;
/*    */ import thaumcraft.api.research.ResearchHelper;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ 
/*    */ public class ItemSanitySoap extends net.minecraft.item.Item
/*    */ {
/*    */   public ItemSanitySoap()
/*    */   {
/* 21 */     func_77637_a(Thaumcraft.tabTC);
/* 22 */     func_77627_a(false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77626_a(ItemStack p_77626_1_)
/*    */   {
/* 31 */     return 200;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public EnumAction func_77661_b(ItemStack p_77661_1_)
/*    */   {
/* 40 */     return EnumAction.BLOCK;
/*    */   }
/*    */   
/*    */ 
/*    */   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
/*    */   {
/* 46 */     p_77659_3_.func_71008_a(p_77659_1_, func_77626_a(p_77659_1_));
/* 47 */     return p_77659_1_;
/*    */   }
/*    */   
/*    */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*    */   {
/* 52 */     int ticks = func_77626_a(stack) - count;
/* 53 */     if (ticks > 195) player.func_71034_by();
/* 54 */     if (player.field_70170_p.field_72995_K) {
/* 55 */       if (player.field_70170_p.field_73012_v.nextFloat() < 0.2F) {
/* 56 */         player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:roots", 0.1F, 1.5F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*    */       }
/* 58 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(5); a++) {
/* 59 */         Thaumcraft.proxy.getFX().crucibleBubble((float)player.field_70165_t - 0.5F + player.field_70170_p.field_73012_v.nextFloat(), (float)player.func_174813_aQ().field_72338_b + player.field_70170_p.field_73012_v.nextFloat() * player.field_70131_O, (float)player.field_70161_v - 0.5F + player.field_70170_p.field_73012_v.nextFloat(), 1.0F, 0.8F, 0.9F);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int par4)
/*    */   {
/* 70 */     int qq = func_77626_a(stack) - par4;
/* 71 */     if (qq > 195) {
/* 72 */       stack.field_77994_a -= 1;
/* 73 */       if (!world.field_72995_K) {
/* 74 */         float chance = 0.33F;
/* 75 */         if (player.func_70644_a(thaumcraft.common.lib.potions.PotionWarpWard.instance)) chance += 0.25F;
/* 76 */         int i = MathHelper.func_76128_c(player.field_70165_t);
/* 77 */         int j = MathHelper.func_76128_c(player.field_70163_u);
/* 78 */         int k = MathHelper.func_76128_c(player.field_70161_v);
/* 79 */         if (world.func_180495_p(new net.minecraft.util.BlockPos(i, j, k)).func_177230_c() == thaumcraft.api.blocks.BlocksTC.purifyingFluid) chance += 0.25F;
/* 80 */         if ((world.field_73012_v.nextFloat() < chance) && (Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_()) > 0)) {
/* 81 */           ResearchHelper.addWarpToPlayer(player, -1, EnumWarpType.NORMAL);
/*    */         }
/* 83 */         if (Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_()) > 0) {
/* 84 */           ResearchHelper.addWarpToPlayer(player, -Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_()), EnumWarpType.TEMPORARY);
/*    */         }
/*    */       }
/*    */       else {
/* 88 */         player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:craftstart", 0.25F, 1.0F, false);
/* 89 */         for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(20); a++) {
/* 90 */           Thaumcraft.proxy.getFX().crucibleBubble((float)player.field_70165_t - 0.5F + player.field_70170_p.field_73012_v.nextFloat() * 1.5F, (float)player.func_174813_aQ().field_72338_b + player.field_70170_p.field_73012_v.nextFloat() * player.field_70131_O, (float)player.field_70161_v - 0.5F + player.field_70170_p.field_73012_v.nextFloat() * 1.5F, 1.0F, 0.7F, 0.9F);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemSanitySoap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */