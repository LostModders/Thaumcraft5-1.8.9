/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
/*    */ import thaumcraft.common.tiles.devices.TileArcaneBore;
/*    */ 
/*    */ public class ContainerArcaneBore extends Container
/*    */ {
/*    */   private TileArcaneBore tileEntity;
/*    */   
/*    */   public ContainerArcaneBore(InventoryPlayer iinventory, TileArcaneBore e)
/*    */   {
/* 19 */     this.tileEntity = e;
/* 20 */     func_75146_a(new SlotLimitedByClass(ItemFocusExcavation.class, e, 0, 26, 18));
/* 21 */     func_75146_a(new SlotLimitedByClass(ItemPickaxe.class, e, 1, 74, 18));
/* 22 */     bindPlayerInventory(iinventory);
/*    */   }
/*    */   
/*    */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/* 26 */     for (int i = 0; i < 3; i++) {
/* 27 */       for (int j = 0; j < 9; j++) {
/* 28 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 59 + i * 18));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 33 */     for (int i = 0; i < 9; i++) {
/* 34 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 117));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*    */   {
/* 42 */     return this.tileEntity.func_145831_w().func_175625_s(this.tileEntity.func_174877_v()) == this.tileEntity;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*    */   {
/* 50 */     ItemStack stack = null;
/* 51 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*    */     
/*    */ 
/* 54 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/* 55 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 56 */       stack = stackInSlot.func_77946_l();
/*    */       
/*    */ 
/* 59 */       if (slot <= 1) {
/* 60 */         if (!func_75135_a(stackInSlot, 2, this.field_75151_b.size(), true)) {
/* 61 */           return null;
/*    */         }
/*    */       }
/* 64 */       else if (slot > 1) {
/* 65 */         if ((stackInSlot.func_77973_b() instanceof ItemFocusExcavation)) {
/* 66 */           if (!func_75135_a(stackInSlot, 0, 1, false)) {
/* 67 */             return null;
/*    */           }
/*    */           
/*    */         }
/* 71 */         else if (((stackInSlot.func_77973_b() instanceof ItemPickaxe)) && 
/* 72 */           (!func_75135_a(stackInSlot, 1, 2, false))) {
/* 73 */           return null;
/*    */ 
/*    */ 
/*    */         }
/*    */         
/*    */ 
/*    */ 
/*    */ 
/*    */       }
/* 82 */       else if (!func_75135_a(stackInSlot, 2, 38, false))
/*    */       {
/* 84 */         return null;
/*    */       }
/*    */       
/* 87 */       if (stackInSlot.field_77994_a == 0) {
/* 88 */         slotObject.func_75215_d(null);
/*    */       } else {
/* 90 */         slotObject.func_75218_e();
/*    */       }
/*    */       
/* 93 */       if (stackInSlot.field_77994_a == stack.field_77994_a)
/*    */       {
/* 95 */         return null;
/*    */       }
/*    */     }
/*    */     
/* 99 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerArcaneBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */