/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.items.resources.ItemShard;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotem;
/*     */ 
/*     */ public class ContainerAuraTotem extends Container
/*     */ {
/*     */   private TileAuraTotem totem;
/*     */   private int lastBreakTime;
/*     */   private int lastTime;
/*     */   
/*     */   public ContainerAuraTotem(InventoryPlayer par1InventoryPlayer, TileAuraTotem tileEntity)
/*     */   {
/*  22 */     this.totem = tileEntity;
/*  23 */     func_75146_a(new SlotLimitedByClass(ItemShard.class, tileEntity, 0, 80, 24));
/*     */     
/*     */ 
/*  26 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  28 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  30 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  34 */     for (i = 0; i < 9; i++)
/*     */     {
/*  36 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/*  44 */     return this.totem.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/*  52 */     ItemStack stack = null;
/*  53 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */     
/*     */ 
/*  56 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/*  57 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  58 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  60 */       if (slot == 0) {
/*  61 */         if ((!this.totem.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 1, this.field_75151_b.size(), true)))
/*     */         {
/*     */ 
/*  64 */           return null;
/*     */         }
/*     */         
/*     */       }
/*  68 */       else if ((!this.totem.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 0, 1, false)))
/*     */       {
/*  70 */         return null;
/*     */       }
/*     */       
/*  73 */       if (stackInSlot.field_77994_a == 0) {
/*  74 */         slotObject.func_75215_d(null);
/*     */       } else {
/*  76 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/*  80 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75132_a(ICrafting par1ICrafting)
/*     */   {
/*  87 */     super.func_75132_a(par1ICrafting);
/*  88 */     par1ICrafting.func_71112_a(this, 0, this.totem.time);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75142_b()
/*     */   {
/*  96 */     super.func_75142_b();
/*     */     
/*  98 */     for (int i = 0; i < this.field_75149_d.size(); i++)
/*     */     {
/* 100 */       ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */       
/* 102 */       if (this.lastTime != this.totem.time)
/*     */       {
/* 104 */         icrafting.func_71112_a(this, 0, this.totem.time);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 109 */     this.lastTime = this.totem.time;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2)
/*     */   {
/* 116 */     if (par1 == 0)
/*     */     {
/* 118 */       this.totem.time = par2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerAuraTotem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */