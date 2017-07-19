/*    */ package thaumcraft.common.lib.enchantment;
/*    */ 
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnumEnchantmentType;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemBook;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.items.armor.ItemThaumostaticHarness;
/*    */ 
/*    */ public class EnchantmentHaste extends Enchantment
/*    */ {
/*    */   public EnchantmentHaste(int par1, int par2)
/*    */   {
/* 15 */     super(par1, new ResourceLocation("haste"), par2, EnumEnchantmentType.ARMOR);
/* 16 */     func_77322_b("haste");
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77321_a(int par1)
/*    */   {
/* 22 */     return 15 + (par1 - 1) * 9;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77317_b(int par1)
/*    */   {
/* 31 */     return super.func_77321_a(par1) + 50;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77325_b()
/*    */   {
/* 40 */     return 3;
/*    */   }
/*    */   
/*    */   public boolean func_92089_a(ItemStack is)
/*    */   {
/* 45 */     if ((is != null) && ((((is.func_77973_b() instanceof ItemArmor)) && ((((ItemArmor)is.func_77973_b()).field_77881_a == 3) || ((is.func_77973_b() instanceof ItemThaumostaticHarness)))) || ((is.func_77973_b() instanceof ItemBook))))
/*    */     {
/*    */ 
/* 48 */       return true;
/*    */     }
/* 50 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack)
/*    */   {
/* 55 */     return func_92089_a(stack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/enchantment/EnchantmentHaste.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */