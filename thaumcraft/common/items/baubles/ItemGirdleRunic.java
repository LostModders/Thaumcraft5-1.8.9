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
/*    */ import thaumcraft.api.items.ItemRunic;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.items.IVariantItems;
/*    */ import thaumcraft.common.lib.events.PlayerEvents;
/*    */ 
/*    */ public class ItemGirdleRunic
/*    */   extends ItemRunic implements IBauble, IVariantItems
/*    */ {
/*    */   public ItemGirdleRunic()
/*    */   {
/* 23 */     super(10);
/* 24 */     this.field_77777_bU = 1;
/* 25 */     this.canRepair = false;
/* 26 */     func_77656_e(0);
/* 27 */     func_77637_a(Thaumcraft.tabTC);
/* 28 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */   public String[] getVariantNames()
/*    */   {
/* 33 */     return new String[] { "base", "kinetic" };
/*    */   }
/*    */   
/*    */   public int[] getVariantMeta()
/*    */   {
/* 38 */     return new int[] { 0, 1 };
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 45 */     return super.func_77658_a() + "." + par1ItemStack.func_77952_i();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 51 */     par3List.add(new ItemStack(this, 1, 0));
/* 52 */     par3List.add(new ItemStack(this, 1, 1));
/*    */   }
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack par1ItemStack)
/*    */   {
/* 57 */     return EnumRarity.RARE;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 62 */     return itemstack.func_77952_i() == 0 ? 10 : 9;
/*    */   }
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack itemstack)
/*    */   {
/* 67 */     return BaubleType.BELT;
/*    */   }
/*    */   
/*    */ 
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}
/*    */   
/*    */   public void onEquipped(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 75 */     PlayerEvents.markRunicDirty(player);
/*    */   }
/*    */   
/*    */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 80 */     PlayerEvents.markRunicDirty(player);
/*    */   }
/*    */   
/*    */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 85 */     return true;
/*    */   }
/*    */   
/*    */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/baubles/ItemGirdleRunic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */