/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.items.IVariantItems;
/*    */ 
/*    */ 
/*    */ public class ItemChunksEdible
/*    */   extends ItemFood
/*    */   implements IVariantItems
/*    */ {
/*    */   public final int field_77855_a;
/*    */   
/*    */   public ItemChunksEdible()
/*    */   {
/* 21 */     super(1, 0.3F, false);
/* 22 */     this.field_77855_a = 10;
/* 23 */     func_77625_d(64);
/* 24 */     func_77627_a(true);
/* 25 */     func_77656_e(0);
/*    */   }
/*    */   
/* 28 */   private String[] variants = { "beef", "chicken", "pork", "fish", "rabbit", "mutton" };
/*    */   
/*    */ 
/*    */   public int func_77626_a(ItemStack par1ItemStack)
/*    */   {
/* 33 */     return this.field_77855_a;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 39 */     for (int a = 0; a < this.variants.length; a++) {
/* 40 */       par3List.add(new ItemStack(this, 1, a));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 47 */     return super.func_77658_a() + "." + this.variants[par1ItemStack.func_77952_i()];
/*    */   }
/*    */   
/*    */   public String[] getVariantNames()
/*    */   {
/* 52 */     return this.variants;
/*    */   }
/*    */   
/*    */   public int[] getVariantMeta()
/*    */   {
/* 57 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/consumables/ItemChunksEdible.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */