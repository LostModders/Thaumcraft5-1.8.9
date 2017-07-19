/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.items.IRepairable;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemThaumiumPickaxe extends ItemPickaxe implements IRepairable
/*    */ {
/*    */   public ItemThaumiumPickaxe(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 16 */     super(enumtoolmaterial);
/* 17 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public Set<String> getToolClasses(ItemStack stack)
/*    */   {
/* 22 */     return ImmutableSet.of("pickaxe");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 29 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemThaumiumPickaxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */