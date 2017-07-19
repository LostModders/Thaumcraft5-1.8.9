/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*    */ 
/*    */ 
/*    */ public class ContainerTurretBasic
/*    */   extends Container
/*    */ {
/*    */   private EntityTurretCrossbow turret;
/*    */   private EntityPlayer player;
/*    */   private final World theWorld;
/*    */   
/*    */   public ContainerTurretBasic(InventoryPlayer par1InventoryPlayer, World par3World, EntityTurretCrossbow ent)
/*    */   {
/* 24 */     this.turret = ent;
/* 25 */     this.theWorld = par3World;
/* 26 */     this.player = par1InventoryPlayer.field_70458_d;
/*    */     
/* 28 */     func_75146_a(new SlotTurretBasic(this.turret, 0, 80, 29));
/*    */     
/*    */ 
/* 31 */     for (int i = 0; i < 3; i++)
/*    */     {
/* 33 */       for (int j = 0; j < 9; j++)
/*    */       {
/* 35 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*    */       }
/*    */     }
/*    */     
/* 39 */     for (i = 0; i < 9; i++)
/*    */     {
/* 41 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2)
/*    */   {
/* 47 */     if (par2 == 0)
/*    */     {
/* 49 */       return true;
/*    */     }
/* 51 */     return super.func_75140_a(par1EntityPlayer, par2);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_75137_b(int par1, int par2) {}
/*    */   
/*    */ 
/*    */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*    */   {
/* 61 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*    */   {
/* 69 */     ItemStack stack = null;
/* 70 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*    */     
/*    */ 
/* 73 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/* 74 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 75 */       stack = stackInSlot.func_77946_l();
/*    */       
/* 77 */       if (slot == 0) {
/* 78 */         if (!func_75135_a(stackInSlot, 1, this.field_75151_b.size(), true)) {
/* 79 */           return null;
/*    */         }
/*    */       }
/* 82 */       else if (!func_75135_a(stackInSlot, 0, 1, false)) {
/* 83 */         return null;
/*    */       }
/*    */       
/* 86 */       if (stackInSlot.field_77994_a == 0) {
/* 87 */         slotObject.func_75215_d(null);
/*    */       } else {
/* 89 */         slotObject.func_75218_e();
/*    */       }
/*    */     }
/*    */     
/* 93 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerTurretBasic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */