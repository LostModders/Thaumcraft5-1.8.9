/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.items.IRepairable;
/*    */ import thaumcraft.api.items.IRunicArmor;
/*    */ import thaumcraft.api.items.IVisDiscountGear;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemCultistBoots extends ItemArmor implements IRepairable, IRunicArmor, thaumcraft.api.items.IWarpingGear, IVisDiscountGear
/*    */ {
/*    */   public ItemCultistBoots(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 19 */     super(enumarmormaterial, j, k);
/* 20 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*    */   {
/* 26 */     return "thaumcraft:textures/models/armor/cultistboots.png";
/*    */   }
/*    */   
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 31 */     return par2ItemStack.func_77969_a(new ItemStack(net.minecraft.init.Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 38 */     return EnumRarity.UNCOMMON;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 43 */     return 0;
/*    */   }
/*    */   
/*    */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 48 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*    */   {
/* 54 */     return 1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemCultistBoots.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */