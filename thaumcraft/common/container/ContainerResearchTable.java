/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.lib.research.ResearchNoteData;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*     */ 
/*     */ public class ContainerResearchTable extends Container
/*     */ {
/*     */   public TileResearchTable tileEntity;
/*     */   String[] aspects;
/*     */   EntityPlayer player;
/*     */   
/*     */   public ContainerResearchTable(InventoryPlayer iinventory, TileResearchTable iinventory1)
/*     */   {
/*  24 */     this.player = iinventory.field_70458_d;
/*  25 */     this.tileEntity = iinventory1;
/*  26 */     this.aspects = ((String[])Aspect.aspects.keySet().toArray(new String[0]));
/*     */     
/*  28 */     func_75146_a(new SlotLimitedByClass(thaumcraft.api.items.IScribeTools.class, iinventory1, 0, 14, 10));
/*  29 */     func_75146_a(new SlotLimitedByClass(thaumcraft.common.items.resources.ItemResearchNotes.class, iinventory1, 1, 70, 10));
/*     */     
/*  31 */     bindPlayerInventory(iinventory);
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  35 */     for (int i = 0; i < 3; i++) {
/*  36 */       for (int j = 0; j < 9; j++) {
/*  37 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 48 + j * 18, 175 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  42 */     for (int i = 0; i < 9; i++) {
/*  43 */       func_75146_a(new Slot(inventoryPlayer, i, 48 + i * 18, 233));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button)
/*     */   {
/*  50 */     if (button == 1) {
/*  51 */       return true;
/*     */     }
/*  53 */     if (button == 5) {
/*  54 */       if (this.tileEntity.data == null) this.tileEntity.gatherResults();
/*  55 */       if ((this.tileEntity.data.aspects != null) && (InventoryUtils.isPlayerCarrying(par1EntityPlayer, new ItemStack(ItemsTC.knowledgeFragment)) > 0)) {
/*     */         Aspect a;
/*  57 */         for (Iterator i$ = Aspect.getPrimalAspects().iterator(); i$.hasNext(); this.tileEntity.data.aspects.add(a, 1)) a = (Aspect)i$.next();
/*  58 */         this.tileEntity.updateNoteAndConsumeInk();
/*  59 */         InventoryUtils.consumeInventoryItem(par1EntityPlayer, ItemsTC.knowledgeFragment, 0);
/*     */       }
/*  61 */       return true;
/*     */     }
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/*  68 */     ItemStack stack = null;
/*  69 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */     
/*     */ 
/*  72 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/*  73 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  74 */       stack = stackInSlot.func_77946_l();
/*     */       
/*     */ 
/*  77 */       if (slot < 2) {
/*  78 */         if (!func_75135_a(stackInSlot, 2, this.field_75151_b.size(), true))
/*     */         {
/*  80 */           return null;
/*     */         }
/*     */       }
/*  83 */       else if (!func_75135_a(stackInSlot, 0, 2, false)) {
/*  84 */         return null;
/*     */       }
/*     */       
/*  87 */       if (stackInSlot.field_77994_a == 0) {
/*  88 */         slotObject.func_75215_d(null);
/*     */       } else {
/*  90 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/*  94 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_75135_a(ItemStack par1ItemStack, int par2, int par3, boolean par4)
/*     */   {
/* 100 */     boolean var5 = false;
/* 101 */     int var6 = par2;
/*     */     
/* 103 */     if (par4)
/*     */     {
/* 105 */       var6 = par3 - 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 111 */     if (par1ItemStack.func_77985_e())
/*     */     {
/* 113 */       while ((par1ItemStack.field_77994_a > 0) && (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2))))
/*     */       {
/* 115 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 116 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 118 */         if ((var8 != null) && (var7.func_75214_a(par1ItemStack)) && (var8.func_77973_b() == par1ItemStack.func_77973_b()) && ((!par1ItemStack.func_77981_g()) || (par1ItemStack.func_77952_i() == var8.func_77952_i())) && (ItemStack.func_77970_a(par1ItemStack, var8)))
/*     */         {
/*     */ 
/*     */ 
/* 122 */           int var9 = var8.field_77994_a + par1ItemStack.field_77994_a;
/*     */           
/* 124 */           if (var9 <= par1ItemStack.func_77976_d())
/*     */           {
/* 126 */             par1ItemStack.field_77994_a = 0;
/* 127 */             var8.field_77994_a = var9;
/* 128 */             var7.func_75218_e();
/* 129 */             var5 = true;
/*     */           }
/* 131 */           else if (var8.field_77994_a < par1ItemStack.func_77976_d())
/*     */           {
/* 133 */             par1ItemStack.field_77994_a -= par1ItemStack.func_77976_d() - var8.field_77994_a;
/* 134 */             var8.field_77994_a = par1ItemStack.func_77976_d();
/* 135 */             var7.func_75218_e();
/* 136 */             var5 = true;
/*     */           }
/*     */         }
/*     */         
/* 140 */         if (par4)
/*     */         {
/* 142 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 146 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 151 */     if (par1ItemStack.field_77994_a > 0)
/*     */     {
/* 153 */       if (par4)
/*     */       {
/* 155 */         var6 = par3 - 1;
/*     */       }
/*     */       else
/*     */       {
/* 159 */         var6 = par2;
/*     */       }
/*     */       
/* 162 */       while (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2)))
/*     */       {
/* 164 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 165 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 167 */         if ((var8 == null) && (var7.func_75214_a(par1ItemStack)))
/*     */         {
/* 169 */           var7.func_75215_d(par1ItemStack.func_77946_l());
/* 170 */           var7.func_75218_e();
/* 171 */           par1ItemStack.field_77994_a = 0;
/* 172 */           var5 = true;
/* 173 */           break;
/*     */         }
/*     */         
/* 176 */         if (par4)
/*     */         {
/* 178 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 182 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 187 */     return var5;
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer player)
/*     */   {
/* 192 */     return this.tileEntity.func_70300_a(player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerResearchTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */