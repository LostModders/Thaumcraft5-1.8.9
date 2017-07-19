/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.lib.aura.AuraHandler;
/*    */ 
/*    */ 
/*    */ public class ItemCreativeFluxSponge
/*    */   extends Item
/*    */ {
/*    */   public ItemCreativeFluxSponge()
/*    */   {
/* 23 */     func_77625_d(1);
/* 24 */     func_77627_a(false);
/* 25 */     func_77656_e(0);
/* 26 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 31 */     super.func_77624_a(stack, player, list, par4);
/* 32 */     list.add(EnumChatFormatting.GREEN + "Right-click to drain all");
/* 33 */     list.add(EnumChatFormatting.GREEN + "flux from 9x9 chunk area");
/* 34 */     list.add(EnumChatFormatting.DARK_PURPLE + "Creative only");
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 40 */     return EnumRarity.EPIC;
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
/*    */   {
/* 45 */     if (worldIn.field_72995_K) {
/* 46 */       playerIn.func_71038_i();
/* 47 */       playerIn.field_70170_p.func_72980_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, "thaumcraft:craftstart", 0.15F, 1.0F, false);
/*    */     } else {
/* 49 */       int q = 0;
/* 50 */       BlockPos p = playerIn.func_180425_c();
/* 51 */       for (int x = -4; x <= 4; x++) {
/* 52 */         for (int z = -4; z <= 4; z++)
/* 53 */           q += AuraHandler.drainAuraAvailable(worldIn, p.func_177982_a(16 * x, 0, 16 * z), Aspect.FLUX, Config.AURABASE);
/*    */       }
/* 55 */       playerIn.func_145747_a(new ChatComponentText(EnumChatFormatting.GREEN + "" + q + " flux drained from 81 chunks."));
/*    */     }
/* 57 */     return super.func_77659_a(itemStackIn, worldIn, playerIn);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemCreativeFluxSponge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */