/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class ItemThaumiumArmor extends ItemArmor implements thaumcraft.api.items.IRepairable, thaumcraft.api.items.IRunicArmor
/*    */ {
/*    */   public ItemThaumiumArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 14 */     super(enumarmormaterial, j, k);
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 19 */     return 0;
/*    */   }
/*    */   
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*    */   {
/* 24 */     if ((stack.func_77973_b() == ItemsTC.thaumiumHelm) || (stack.func_77973_b() == ItemsTC.thaumiumChest) || (stack.func_77973_b() == ItemsTC.thaumiumBoots))
/*    */     {
/*    */ 
/* 27 */       return "thaumcraft:textures/models/armor/thaumium_1.png";
/*    */     }
/* 29 */     if (stack.func_77973_b() == ItemsTC.thaumiumLegs) {
/* 30 */       return "thaumcraft:textures/models/armor/thaumium_2.png";
/*    */     }
/* 32 */     return "thaumcraft:textures/models/armor/thaumium_1.png";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 39 */     return EnumRarity.UNCOMMON;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 45 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemThaumiumArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */