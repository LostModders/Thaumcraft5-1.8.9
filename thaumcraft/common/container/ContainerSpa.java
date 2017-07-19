/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.items.consumables.ItemBathSalts;
/*    */ import thaumcraft.common.tiles.devices.TileSpa;
/*    */ 
/*    */ public class ContainerSpa extends Container
/*    */ {
/*    */   private TileSpa spa;
/*    */   private int lastBreakTime;
/*    */   
/*    */   public ContainerSpa(InventoryPlayer par1InventoryPlayer, TileSpa tileEntity)
/*    */   {
/* 19 */     this.spa = tileEntity;
/* 20 */     func_75146_a(new SlotLimitedByClass(ItemBathSalts.class, tileEntity, 0, 65, 31));
/*    */     
/*    */ 
/* 23 */     for (int i = 0; i < 3; i++)
/*    */     {
/* 25 */       for (int j = 0; j < 9; j++)
/*    */       {
/* 27 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*    */       }
/*    */     }
/*    */     
/* 31 */     for (i = 0; i < 9; i++)
/*    */     {
/* 33 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75140_a(EntityPlayer p, int button)
/*    */   {
/* 42 */     if (button == 1) {
/* 43 */       this.spa.toggleMix();
/*    */     }
/* 45 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*    */   {
/* 52 */     return this.spa.func_70300_a(par1EntityPlayer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*    */   {
/* 60 */     ItemStack stack = null;
/* 61 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*    */     
/*    */ 
/* 64 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/* 65 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 66 */       stack = stackInSlot.func_77946_l();
/*    */       
/* 68 */       if (slot == 0) {
/* 69 */         if ((!this.spa.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 1, this.field_75151_b.size(), true)))
/*    */         {
/*    */ 
/* 72 */           return null;
/*    */         }
/*    */         
/*    */       }
/* 76 */       else if ((!this.spa.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 0, 1, false)))
/*    */       {
/* 78 */         return null;
/*    */       }
/*    */       
/* 81 */       if (stackInSlot.field_77994_a == 0) {
/* 82 */         slotObject.func_75215_d(null);
/*    */       } else {
/* 84 */         slotObject.func_75218_e();
/*    */       }
/*    */     }
/*    */     
/* 88 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerSpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */