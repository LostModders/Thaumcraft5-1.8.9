/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*     */ 
/*     */ public class ContainerFocalManipulator extends Container
/*     */ {
/*     */   private TileFocalManipulator table;
/*     */   private int lastBreakTime;
/*     */   
/*     */   public ContainerFocalManipulator(InventoryPlayer par1InventoryPlayer, TileFocalManipulator tileEntity)
/*     */   {
/*  19 */     this.table = tileEntity;
/*  20 */     func_75146_a(new SlotLimitedByClass(ItemFocusBasic.class, tileEntity, 0, 88, 60));
/*     */     
/*     */ 
/*  23 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  25 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  27 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 16 + j * 18, 151 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  31 */     for (i = 0; i < 9; i++)
/*     */     {
/*  33 */       func_75146_a(new Slot(par1InventoryPlayer, i, 16 + i * 18, 209));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer p, int button)
/*     */   {
/*  41 */     if ((button >= 0) && 
/*  42 */       (!this.table.startCraft(button, p))) {
/*  43 */       this.table.func_145831_w().func_72908_a(this.table.func_174877_v().func_177958_n(), this.table.func_174877_v().func_177956_o(), this.table.func_174877_v().func_177952_p(), "thaumcraft:craftfail", 0.33F, 1.0F);
/*     */     }
/*     */     
/*     */ 
/*  47 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/*  54 */     return this.table.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/*  60 */     ItemStack itemstack = null;
/*  61 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/*  63 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/*  65 */       ItemStack itemstack1 = slot.func_75211_c();
/*  66 */       itemstack = itemstack1.func_77946_l();
/*     */       
/*  68 */       if (par2 != 0)
/*     */       {
/*  70 */         if ((itemstack1.func_77973_b() instanceof ItemFocusBasic))
/*     */         {
/*  72 */           if (!func_75135_a(itemstack1, 0, 1, false))
/*     */           {
/*  74 */             return null;
/*     */           }
/*     */         }
/*  77 */         else if ((par2 >= 1) && (par2 < 28))
/*     */         {
/*  79 */           if (!func_75135_a(itemstack1, 28, 37, false))
/*     */           {
/*  81 */             return null;
/*     */           }
/*     */         }
/*  84 */         else if ((par2 >= 28) && (par2 < 37) && (!func_75135_a(itemstack1, 1, 28, false)))
/*     */         {
/*  86 */           return null;
/*     */         }
/*     */       }
/*  89 */       else if (!func_75135_a(itemstack1, 1, 37, false))
/*     */       {
/*  91 */         return null;
/*     */       }
/*     */       
/*  94 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/*  96 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 100 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 103 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 105 */         return null;
/*     */       }
/*     */       
/* 108 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 111 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerFocalManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */