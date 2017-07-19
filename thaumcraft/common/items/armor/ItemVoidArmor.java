/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.items.IRunicArmor;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemVoidArmor extends ItemArmor implements thaumcraft.api.items.IRepairable, IRunicArmor, thaumcraft.api.items.IWarpingGear
/*    */ {
/*    */   public ItemVoidArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 19 */     super(enumarmormaterial, j, k);
/* 20 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 25 */     return 0;
/*    */   }
/*    */   
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*    */   {
/* 30 */     if ((stack.func_77973_b() == ItemsTC.voidHelm) || (stack.func_77973_b() == ItemsTC.voidChest) || (stack.func_77973_b() == ItemsTC.voidBoots))
/*    */     {
/*    */ 
/* 33 */       return "thaumcraft:textures/models/armor/void_1.png";
/*    */     }
/* 35 */     if (stack.func_77973_b() == ItemsTC.voidLegs) {
/* 36 */       return "thaumcraft:textures/models/armor/void_2.png";
/*    */     }
/* 38 */     return "thaumcraft:textures/models/armor/void_1.png";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 45 */     return EnumRarity.UNCOMMON;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 51 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 1)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*    */   {
/* 57 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 59 */     if ((!world.field_72995_K) && (stack.func_77951_h()) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/* 60 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onArmorTick(World world, EntityPlayer player, ItemStack armor)
/*    */   {
/* 66 */     super.onArmorTick(world, player, armor);
/* 67 */     if ((!world.field_72995_K) && (armor.func_77952_i() > 0) && (player.field_70173_aa % 20 == 0)) {
/* 68 */       armor.func_77972_a(-1, player);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 74 */     return 1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemVoidArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */