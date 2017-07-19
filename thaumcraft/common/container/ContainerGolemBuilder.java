/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*     */ 
/*     */ public class ContainerGolemBuilder extends Container
/*     */ {
/*     */   private TileGolemBuilder builder;
/*     */   
/*     */   public ContainerGolemBuilder(InventoryPlayer par1InventoryPlayer, TileGolemBuilder tileEntity)
/*     */   {
/*  20 */     this.builder = tileEntity;
/*  21 */     func_75146_a(new SlotOutput(tileEntity, 0, 160, 104));
/*     */     
/*     */ 
/*  24 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  26 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  28 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 24 + j * 18, 142 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  32 */     for (i = 0; i < 9; i++)
/*     */     {
/*  34 */       func_75146_a(new Slot(par1InventoryPlayer, i, 24 + i * 18, 200));
/*     */     }
/*     */   }
/*     */   
/*  38 */   public static boolean redo = false;
/*     */   private int lastCost;
/*     */   private int lastMaxCost;
/*     */   
/*  42 */   public ItemStack func_75144_a(int slotId, int clickedButton, int mode, EntityPlayer playerIn) { redo = true;
/*  43 */     return super.func_75144_a(slotId, clickedButton, mode, playerIn);
/*     */   }
/*     */   
/*     */   public void func_75141_a(int p_75141_1_, ItemStack p_75141_2_)
/*     */   {
/*  48 */     redo = true;
/*  49 */     super.func_75141_a(p_75141_1_, p_75141_2_);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer p, int button)
/*     */   {
/*  55 */     if (button == 99) redo = true;
/*  56 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75132_a(ICrafting par1ICrafting)
/*     */   {
/*  62 */     super.func_75132_a(par1ICrafting);
/*  63 */     par1ICrafting.func_71112_a(this, 0, this.builder.cost);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75142_b()
/*     */   {
/*  72 */     super.func_75142_b();
/*     */     
/*  74 */     for (int i = 0; i < this.field_75149_d.size(); i++)
/*     */     {
/*  76 */       ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */       
/*  78 */       if (this.lastCost != this.builder.cost)
/*     */       {
/*  80 */         icrafting.func_71112_a(this, 0, this.builder.cost);
/*     */       }
/*     */       
/*  83 */       if (this.lastMaxCost != this.builder.maxCost)
/*     */       {
/*  85 */         icrafting.func_71112_a(this, 1, this.builder.maxCost);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  90 */     this.lastCost = this.builder.cost;
/*  91 */     this.lastMaxCost = this.builder.maxCost;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2)
/*     */   {
/*  98 */     if (par1 == 0)
/*     */     {
/* 100 */       this.builder.cost = par2;
/*     */     }
/* 102 */     if (par1 == 1)
/*     */     {
/* 104 */       this.builder.maxCost = par2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 112 */     return this.builder.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/* 120 */     ItemStack stack = null;
/* 121 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */     
/*     */ 
/* 124 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/* 125 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 126 */       stack = stackInSlot.func_77946_l();
/*     */       
/* 128 */       if (slot == 0) {
/* 129 */         if ((!this.builder.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 1, this.field_75151_b.size(), true)))
/*     */         {
/*     */ 
/* 132 */           return null;
/*     */         }
/*     */         
/*     */       }
/* 136 */       else if ((!this.builder.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 0, 1, false)))
/*     */       {
/* 138 */         return null;
/*     */       }
/*     */       
/* 141 */       if (stackInSlot.field_77994_a == 0) {
/* 142 */         slotObject.func_75215_d(null);
/*     */       } else {
/* 144 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/* 148 */     return stack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerGolemBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */