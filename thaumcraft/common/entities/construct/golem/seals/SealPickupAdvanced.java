/*    */ package thaumcraft.common.entities.construct.golem.seals;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class SealPickupAdvanced
/*    */   extends SealPickup implements ISealConfigToggles
/*    */ {
/*    */   public String getKey()
/*    */   {
/* 15 */     return "Thaumcraft:pickup_advanced";
/*    */   }
/*    */   
/*    */   public int getFilterSize()
/*    */   {
/* 20 */     return 9;
/*    */   }
/*    */   
/*    */   public ResourceLocation getSealIcon()
/*    */   {
/* 25 */     return this.icon;
/*    */   }
/*    */   
/* 28 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_pickup_advanced");
/*    */   
/* 30 */   private static final ItemStack item = new ItemStack(ItemsTC.seals, 1, 7);
/*    */   
/*    */   public int[] getGuiCategories()
/*    */   {
/* 34 */     return new int[] { 2, 1, 3, 0, 4 };
/*    */   }
/*    */   
/*    */   public EnumGolemTrait[] getRequiredTags()
/*    */   {
/* 39 */     return new EnumGolemTrait[] { EnumGolemTrait.SMART };
/*    */   }
/*    */   
/*    */   public ISealConfigToggles.SealToggle[] getToggles()
/*    */   {
/* 44 */     return this.props;
/*    */   }
/*    */   
/*    */   public void setToggle(int indx, boolean value)
/*    */   {
/* 49 */     this.props[indx].setValue(value);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealPickupAdvanced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */