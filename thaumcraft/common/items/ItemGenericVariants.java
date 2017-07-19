/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class ItemGenericVariants
/*    */   extends Item implements IVariantItems
/*    */ {
/*    */   protected String[] variants;
/*    */   
/*    */   public ItemGenericVariants(String[] variants)
/*    */   {
/* 17 */     func_77625_d(64);
/* 18 */     func_77627_a(true);
/* 19 */     func_77656_e(0);
/* 20 */     this.variants = variants;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 28 */     for (int a = 0; a < getVariantNames().length; a++) {
/* 29 */       par3List.add(new ItemStack(this, 1, a));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 36 */     if (getVariantNames()[par1ItemStack.func_77952_i()].equals("*")) {
/* 37 */       return super.func_77658_a();
/*    */     }
/* 39 */     return super.func_77658_a() + "." + getVariantNames()[par1ItemStack.func_77952_i()];
/*    */   }
/*    */   
/*    */   public String[] getVariantNames()
/*    */   {
/* 44 */     return this.variants;
/*    */   }
/*    */   
/*    */   public int[] getVariantMeta()
/*    */   {
/* 49 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/ItemGenericVariants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */