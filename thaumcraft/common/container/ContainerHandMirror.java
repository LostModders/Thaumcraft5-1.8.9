/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.items.tools.ItemHandMirror;
/*     */ 
/*     */ public class ContainerHandMirror extends Container
/*     */ {
/*     */   private World worldObj;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int posZ;
/*  19 */   public IInventory input = new InventoryHandMirror(this);
/*  20 */   ItemStack mirror = null;
/*  21 */   EntityPlayer player = null;
/*     */   
/*     */   public ContainerHandMirror(InventoryPlayer iinventory, World par2World, int par3, int par4, int par5)
/*     */   {
/*  25 */     this.worldObj = par2World;
/*  26 */     this.posX = par3;
/*  27 */     this.posY = par4;
/*  28 */     this.posZ = par5;
/*  29 */     this.player = iinventory.field_70458_d;
/*  30 */     this.mirror = iinventory.func_70448_g();
/*     */     
/*  32 */     func_75146_a(new Slot(this.input, 0, 80, 24));
/*  33 */     bindPlayerInventory(iinventory);
/*  34 */     func_75130_a(this.input);
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  38 */     for (int i = 0; i < 3; i++) {
/*  39 */       for (int j = 0; j < 9; j++) {
/*  40 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  45 */     for (int i = 0; i < 9; i++) {
/*  46 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75130_a(IInventory par1IInventory)
/*     */   {
/*  53 */     if ((this.input.func_70301_a(0) != null) && (ItemStack.func_77989_b(this.input.func_70301_a(0), this.mirror)))
/*     */     {
/*  55 */       this.player.field_71070_bA = this.player.field_71069_bz;
/*     */     }
/*  57 */     else if ((!this.worldObj.field_72995_K) && (this.input.func_70301_a(0) != null) && (this.player != null) && 
/*  58 */       (ItemHandMirror.transport(this.mirror, this.input.func_70301_a(0), this.player, this.worldObj))) {
/*  59 */       this.input.func_70299_a(0, null);
/*  60 */       for (int var4 = 0; var4 < this.field_75149_d.size(); var4++)
/*     */       {
/*  62 */         ((net.minecraft.inventory.ICrafting)this.field_75149_d.get(var4)).func_71111_a(this, 0, null);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_75144_a(int slotId, int clickedButton, int mode, EntityPlayer playerIn)
/*     */   {
/*     */     try
/*     */     {
/*  74 */       ItemStack s = func_75139_a(slotId).func_75211_c();
/*  75 */       if ((s != null) && ((s.func_77973_b() instanceof ItemHandMirror))) return null;
/*     */     }
/*     */     catch (Exception e) {}
/*  78 */     return super.func_75144_a(slotId, clickedButton, mode, playerIn);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/*  88 */     ItemStack stack = null;
/*  89 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */     
/*     */ 
/*  92 */     if ((slotObject != null) && (slotObject.func_75216_d()) && (!(slotObject.func_75211_c().func_77973_b() instanceof ItemHandMirror))) {
/*  93 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  94 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  96 */       if (slot == 0) {
/*  97 */         if (!mergeItemStack(stackInSlot, 1, this.field_75151_b.size(), true, 64))
/*     */         {
/*  99 */           return null;
/*     */         }
/*     */       }
/* 102 */       else if (!mergeItemStack(stackInSlot, 0, 1, false, 64)) {
/* 103 */         return null;
/*     */       }
/*     */       
/* 106 */       if (stackInSlot.field_77994_a == 0) {
/* 107 */         slotObject.func_75215_d(null);
/*     */       } else {
/* 109 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/* 113 */     return stack;
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer var1)
/*     */   {
/* 118 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 124 */     super.func_75134_a(par1EntityPlayer);
/*     */     
/* 126 */     if (!this.worldObj.field_72995_K)
/*     */     {
/* 128 */       for (int var2 = 0; var2 < 1; var2++)
/*     */       {
/* 130 */         ItemStack var3 = this.input.func_70304_b(var2);
/*     */         
/* 132 */         if (var3 != null)
/*     */         {
/* 134 */           par1EntityPlayer.func_71019_a(var3, false);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4, int limit)
/*     */   {
/* 142 */     boolean var5 = false;
/* 143 */     int var6 = par2;
/*     */     
/* 145 */     if (par4)
/*     */     {
/* 147 */       var6 = par3 - 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 153 */     if (par1ItemStack.func_77985_e())
/*     */     {
/* 155 */       while ((par1ItemStack.field_77994_a > 0) && (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2))))
/*     */       {
/* 157 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 158 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 160 */         if ((var8 != null) && (var8.func_77973_b() == par1ItemStack.func_77973_b()) && ((!par1ItemStack.func_77981_g()) || (par1ItemStack.func_77952_i() == var8.func_77952_i())) && (ItemStack.func_77970_a(par1ItemStack, var8)))
/*     */         {
/*     */ 
/*     */ 
/* 164 */           int var9 = var8.field_77994_a + par1ItemStack.field_77994_a;
/*     */           
/* 166 */           if (var9 <= Math.min(par1ItemStack.func_77976_d(), limit))
/*     */           {
/* 168 */             par1ItemStack.field_77994_a = 0;
/* 169 */             var8.field_77994_a = var9;
/* 170 */             var7.func_75218_e();
/* 171 */             var5 = true;
/*     */           }
/* 173 */           else if (var8.field_77994_a < Math.min(par1ItemStack.func_77976_d(), limit))
/*     */           {
/* 175 */             par1ItemStack.field_77994_a -= Math.min(par1ItemStack.func_77976_d(), limit) - var8.field_77994_a;
/* 176 */             var8.field_77994_a = Math.min(par1ItemStack.func_77976_d(), limit);
/* 177 */             var7.func_75218_e();
/* 178 */             var5 = true;
/*     */           }
/*     */         }
/*     */         
/* 182 */         if (par4)
/*     */         {
/* 184 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 188 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 193 */     if (par1ItemStack.field_77994_a > 0)
/*     */     {
/* 195 */       if (par4)
/*     */       {
/* 197 */         var6 = par3 - 1;
/*     */       }
/*     */       else
/*     */       {
/* 201 */         var6 = par2;
/*     */       }
/*     */       
/* 204 */       while (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2)))
/*     */       {
/* 206 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 207 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 209 */         if (var8 == null)
/*     */         {
/* 211 */           ItemStack res = par1ItemStack.func_77946_l();
/* 212 */           res.field_77994_a = Math.min(res.field_77994_a, limit);
/* 213 */           var7.func_75215_d(res);
/* 214 */           var7.func_75218_e();
/* 215 */           par1ItemStack.field_77994_a -= res.field_77994_a;
/* 216 */           var5 = true;
/* 217 */           break;
/*     */         }
/*     */         
/* 220 */         if (par4)
/*     */         {
/* 222 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 226 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 231 */     return var5;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerHandMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */