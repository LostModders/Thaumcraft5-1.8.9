/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ 
/*    */ public class SlotLimitedByWand
/*    */   extends Slot
/*    */ {
/* 12 */   int limit = 64;
/*    */   
/*    */   public SlotLimitedByWand(IInventory par2IInventory, int par3, int par4, int par5)
/*    */   {
/* 16 */     super(par2IInventory, par3, par4, par5);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_75214_a(ItemStack stack)
/*    */   {
/* 23 */     return (stack != null) && (stack.func_77973_b() != null) && ((stack.func_77973_b() instanceof IWand)) && (!((IWand)stack.func_77973_b()).isStaff(stack));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_75219_a()
/*    */   {
/* 31 */     return this.limit;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
/*    */   {
/* 37 */     super.func_82870_a(par1EntityPlayer, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/SlotLimitedByWand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */