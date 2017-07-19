/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.items.IGoggles;
/*    */ import thaumcraft.api.items.IRepairable;
/*    */ import thaumcraft.api.items.IRevealer;
/*    */ import thaumcraft.api.items.IRunicArmor;
/*    */ import thaumcraft.api.items.IVisDiscountGear;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemGoggles
/*    */   extends ItemArmor
/*    */   implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, IRunicArmor
/*    */ {
/*    */   public ItemGoggles(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 26 */     super(enumarmormaterial, j, k);
/* 27 */     func_77656_e(350);
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 32 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*    */   {
/* 38 */     return "thaumcraft:textures/models/armor/goggles.png";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 45 */     return EnumRarity.RARE;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 52 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*    */   {
/* 57 */     return 5;
/*    */   }
/*    */   
/*    */   public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 62 */     return true;
/*    */   }
/*    */   
/*    */   public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemGoggles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */