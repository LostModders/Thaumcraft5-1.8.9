/*    */ package thaumcraft.common.entities.construct.golem.seals;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles.SealToggle;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class SealGuardAdvanced extends SealGuard implements ISealConfigToggles
/*    */ {
/*    */   public String getKey()
/*    */   {
/* 14 */     return "Thaumcraft:guard_advanced";
/*    */   }
/*    */   
/*    */   public ResourceLocation getSealIcon()
/*    */   {
/* 19 */     return this.icon;
/*    */   }
/*    */   
/* 22 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_guard_advanced");
/*    */   
/* 24 */   private static final ItemStack item = new ItemStack(ItemsTC.seals, 1, 10);
/*    */   
/*    */   public ISealConfigToggles.SealToggle[] getToggles()
/*    */   {
/* 28 */     return this.props;
/*    */   }
/*    */   
/*    */   public void setToggle(int indx, boolean value)
/*    */   {
/* 33 */     this.props[indx].setValue(value);
/*    */   }
/*    */   
/*    */   public int[] getGuiCategories()
/*    */   {
/* 38 */     return new int[] { 2, 3, 0, 4 };
/*    */   }
/*    */   
/*    */   public EnumGolemTrait[] getRequiredTags()
/*    */   {
/* 43 */     return new EnumGolemTrait[] { EnumGolemTrait.FIGHTER, EnumGolemTrait.SMART };
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/seals/SealGuardAdvanced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */