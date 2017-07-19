/*    */ package thaumcraft.common.entities.construct.golem.seals;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class SealFillAdvanced extends SealFill implements ISealConfigToggles
/*    */ {
/*    */   public String getKey()
/*    */   {
/* 14 */     return "Thaumcraft:fill_advanced";
/*    */   }
/*    */   
/*    */   public int getFilterSize()
/*    */   {
/* 19 */     return 9;
/*    */   }
/*    */   
/*    */   public ResourceLocation getSealIcon()
/*    */   {
/* 24 */     return this.icon;
/*    */   }
/*    */   
/* 27 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_fill_advanced");
/*    */   
/* 29 */   private static final ItemStack item = new ItemStack(ItemsTC.seals, 1, 3);
/*    */   
/*    */   public int[] getGuiCategories()
/*    */   {
/* 33 */     return new int[] { 1, 3, 0, 4 };
/*    */   }
/*    */   
/*    */   public ISealConfigToggles.SealToggle[] getToggles()
/*    */   {
/* 38 */     return this.props;
/*    */   }
/*    */   
/*    */   public void setToggle(int indx, boolean value)
/*    */   {
/* 43 */     this.props[indx].setValue(value);
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumGolemTrait[] getRequiredTags()
/*    */   {
/* 49 */     return new EnumGolemTrait[] { EnumGolemTrait.SMART };
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealFillAdvanced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */