/*    */ package thaumcraft.common.entities.construct.golem.seals;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class SealEmptyAdvanced
/*    */   extends SealEmpty
/*    */   implements ISealConfigToggles
/*    */ {
/*    */   public String getKey()
/*    */   {
/* 17 */     return "Thaumcraft:empty_advanced";
/*    */   }
/*    */   
/*    */   public int getFilterSize()
/*    */   {
/* 22 */     return 9;
/*    */   }
/*    */   
/*    */   public ResourceLocation getSealIcon()
/*    */   {
/* 27 */     return this.icon;
/*    */   }
/*    */   
/* 30 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_empty_advanced");
/*    */   
/* 32 */   private static final ItemStack item = new ItemStack(ItemsTC.seals, 1, 5);
/*    */   
/*    */   public ItemStack[] getInv(int c)
/*    */   {
/* 36 */     if ((getToggles()[4].value) && (!isBlacklist())) {
/* 37 */       ArrayList<ItemStack> w = new ArrayList();
/* 38 */       for (ItemStack s : super.getInv()) {
/* 39 */         if (s != null) {
/* 40 */           w.add(s);
/*    */         }
/*    */       }
/* 43 */       if (w.size() > 0) {
/* 44 */         int i = Math.abs(c % w.size());
/* 45 */         return new ItemStack[] { (ItemStack)w.get(i) };
/*    */       }
/*    */     }
/* 48 */     return super.getInv();
/*    */   }
/*    */   
/*    */   public ItemStack[] getInv()
/*    */   {
/* 53 */     return super.getInv();
/*    */   }
/*    */   
/*    */   public ISealConfigToggles.SealToggle[] getToggles()
/*    */   {
/* 58 */     return this.props;
/*    */   }
/*    */   
/*    */ 
/*    */   public int[] getGuiCategories()
/*    */   {
/* 64 */     return new int[] { 1, 3, 0, 4 };
/*    */   }
/*    */   
/*    */   public void setToggle(int indx, boolean value)
/*    */   {
/* 69 */     this.props[indx].setValue(value);
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumGolemTrait[] getRequiredTags()
/*    */   {
/* 75 */     return new EnumGolemTrait[] { EnumGolemTrait.SMART };
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealEmptyAdvanced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */