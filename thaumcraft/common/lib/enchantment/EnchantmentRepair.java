/*    */ package thaumcraft.common.lib.enchantment;
/*    */ 
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnumEnchantmentType;
/*    */ import net.minecraft.item.ItemBook;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.items.IRepairable;
/*    */ 
/*    */ public class EnchantmentRepair extends Enchantment
/*    */ {
/*    */   public EnchantmentRepair(int par1, int par2)
/*    */   {
/* 14 */     super(par1, new ResourceLocation("repair"), par2, EnumEnchantmentType.ALL);
/* 15 */     func_77322_b("repair");
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77321_a(int par1)
/*    */   {
/* 21 */     return 20 + (par1 - 1) * 10;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77317_b(int par1)
/*    */   {
/* 30 */     return super.func_77321_a(par1) + 50;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77325_b()
/*    */   {
/* 39 */     return 2;
/*    */   }
/*    */   
/*    */   public boolean func_92089_a(ItemStack stack)
/*    */   {
/* 44 */     if ((stack.func_77984_f()) && (((stack.func_77973_b() instanceof IRepairable)) || ((stack.func_77973_b() instanceof ItemBook))))
/* 45 */       return true;
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack)
/*    */   {
/* 51 */     return func_92089_a(stack);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_77326_a(Enchantment par1Enchantment)
/*    */   {
/* 57 */     return (super.func_77326_a(par1Enchantment)) && (par1Enchantment.field_77352_x != Enchantment.field_77347_r.field_77352_x);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/enchantment/EnchantmentRepair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */