/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.events.PlayerEvents;
/*    */ 
/*    */ public class ItemBootsTraveller extends ItemArmor implements thaumcraft.api.items.IRepairable, thaumcraft.api.items.IRunicArmor
/*    */ {
/*    */   public ItemBootsTraveller(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 17 */     super(enumarmormaterial, j, k);
/* 18 */     func_77656_e(350);
/*    */   }
/*    */   
/*    */ 
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*    */   {
/* 24 */     return "thaumcraft:textures/models/armor/bootstraveler.png";
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 30 */     return par2ItemStack.func_77969_a(new ItemStack(net.minecraft.init.Items.field_151116_aA)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 37 */     return net.minecraft.item.EnumRarity.RARE;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 42 */     return 0;
/*    */   }
/*    */   
/*    */   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
/*    */   {
/* 47 */     if ((!player.field_71075_bZ.field_75100_b) && (player.field_70701_bs > 0.0F))
/*    */     {
/* 49 */       if ((player.field_70170_p.field_72995_K) && (!player.func_70093_af())) {
/* 50 */         if (!Thaumcraft.instance.playerEvents.prevStep.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 51 */           Thaumcraft.instance.playerEvents.prevStep.put(Integer.valueOf(player.func_145782_y()), Float.valueOf(player.field_70138_W));
/*    */         }
/* 53 */         player.field_70138_W = 1.0F;
/*    */       }
/*    */       
/* 56 */       if (player.field_70122_E) {
/* 57 */         float bonus = 0.055F;
/* 58 */         if (player.func_70090_H()) bonus /= 4.0F;
/* 59 */         player.func_70060_a(0.0F, 1.0F, bonus);
/*    */       }
/* 61 */       else if (Hover.getHover(player.func_145782_y())) {
/* 62 */         player.field_70747_aH = 0.03F;
/*    */       } else {
/* 64 */         player.field_70747_aH = 0.05F;
/*    */       }
/*    */     }
/*    */     
/* 68 */     if (player.field_70143_R > 0.0F) {
/* 69 */       player.field_70143_R -= 0.25F;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemBootsTraveller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */