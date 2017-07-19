/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*    */ 
/*    */ public class SlotLimitedHasAspects extends Slot
/*    */ {
/*    */   public SlotLimitedHasAspects(IInventory par2IInventory, int par3, int par4, int par5)
/*    */   {
/* 13 */     super(par2IInventory, par3, par4, par5);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_75214_a(ItemStack par1ItemStack)
/*    */   {
/* 19 */     AspectList al = ThaumcraftCraftingManager.getObjectTags(par1ItemStack);
/* 20 */     if ((al != null) && (al.size() > 0)) return true;
/* 21 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/SlotLimitedHasAspects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */