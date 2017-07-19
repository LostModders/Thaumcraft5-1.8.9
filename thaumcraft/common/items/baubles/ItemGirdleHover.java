/*    */ package thaumcraft.common.items.baubles;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.items.IRunicArmor;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.events.PlayerEvents;
/*    */ 
/*    */ public class ItemGirdleHover
/*    */   extends Item
/*    */   implements IBauble, IRunicArmor
/*    */ {
/*    */   public ItemGirdleHover()
/*    */   {
/* 23 */     this.field_77777_bU = 1;
/* 24 */     this.canRepair = false;
/* 25 */     func_77656_e(0);
/* 26 */     func_77637_a(Thaumcraft.tabTC);
/* 27 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 34 */     par3List.add(new ItemStack(this, 1, 0));
/*    */   }
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack par1ItemStack)
/*    */   {
/* 39 */     return EnumRarity.RARE;
/*    */   }
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack itemstack)
/*    */   {
/* 44 */     return BaubleType.BELT;
/*    */   }
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 49 */     if (player.field_70143_R > 0.0F) {
/* 50 */       player.field_70143_R -= 0.33F;
/*    */     }
/*    */   }
/*    */   
/*    */   public void onEquipped(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 56 */     PlayerEvents.markRunicDirty(player);
/*    */   }
/*    */   
/*    */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 61 */     PlayerEvents.markRunicDirty(player);
/*    */   }
/*    */   
/*    */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 66 */     return true;
/*    */   }
/*    */   
/*    */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 71 */     return true;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 76 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/baubles/ItemGirdleHover.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */