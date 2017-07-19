/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemThaumiumSword extends ItemSword implements thaumcraft.api.items.IRepairable
/*    */ {
/*    */   public ItemThaumiumSword(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 12 */     super(enumtoolmaterial);
/* 13 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 20 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.api.items.ItemsTC.ingots)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemThaumiumSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */