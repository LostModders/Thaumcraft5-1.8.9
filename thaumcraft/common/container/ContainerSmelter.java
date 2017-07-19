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
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.crafting.TileSmelter;
/*     */ 
/*     */ public class ContainerSmelter extends Container
/*     */ {
/*     */   private TileSmelter furnace;
/*     */   private int lastCookTime;
/*     */   private int lastBurnTime;
/*     */   private int lastItemBurnTime;
/*     */   private int lastVis;
/*     */   private int lastSmelt;
/*     */   private int lastFlux;
/*     */   
/*     */   public ContainerSmelter(InventoryPlayer par1InventoryPlayer, TileSmelter tileEntity)
/*     */   {
/*  28 */     this.furnace = tileEntity;
/*  29 */     func_75146_a(new SlotLimitedHasAspects(tileEntity, 0, 80, 8));
/*  30 */     func_75146_a(new Slot(tileEntity, 1, 80, 48));
/*     */     
/*     */ 
/*  33 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  35 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  37 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  41 */     for (i = 0; i < 9; i++)
/*     */     {
/*  43 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75132_a(ICrafting par1ICrafting)
/*     */   {
/*  51 */     super.func_75132_a(par1ICrafting);
/*  52 */     par1ICrafting.func_175173_a(this, this.furnace);
/*  53 */     par1ICrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*  54 */     par1ICrafting.func_71112_a(this, 1, this.furnace.furnaceBurnTime);
/*  55 */     par1ICrafting.func_71112_a(this, 2, this.furnace.currentItemBurnTime);
/*  56 */     par1ICrafting.func_71112_a(this, 3, this.furnace.vis);
/*  57 */     par1ICrafting.func_71112_a(this, 4, this.furnace.smeltTime);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75142_b()
/*     */   {
/*  63 */     super.func_75142_b();
/*     */     
/*  65 */     for (int i = 0; i < this.field_75149_d.size(); i++)
/*     */     {
/*  67 */       ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */       
/*  69 */       if (this.lastCookTime != this.furnace.furnaceCookTime)
/*     */       {
/*  71 */         icrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*     */       }
/*     */       
/*  74 */       if (this.lastBurnTime != this.furnace.furnaceBurnTime)
/*     */       {
/*  76 */         icrafting.func_71112_a(this, 1, this.furnace.furnaceBurnTime);
/*     */       }
/*     */       
/*  79 */       if (this.lastItemBurnTime != this.furnace.currentItemBurnTime)
/*     */       {
/*  81 */         icrafting.func_71112_a(this, 2, this.furnace.currentItemBurnTime);
/*     */       }
/*     */       
/*  84 */       if (this.lastVis != this.furnace.vis)
/*     */       {
/*  86 */         icrafting.func_71112_a(this, 3, this.furnace.vis);
/*     */       }
/*     */       
/*  89 */       if (this.lastSmelt != this.furnace.smeltTime)
/*     */       {
/*  91 */         icrafting.func_71112_a(this, 4, this.furnace.smeltTime);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  97 */     this.lastCookTime = this.furnace.furnaceCookTime;
/*  98 */     this.lastBurnTime = this.furnace.furnaceBurnTime;
/*  99 */     this.lastItemBurnTime = this.furnace.currentItemBurnTime;
/* 100 */     this.lastVis = this.furnace.vis;
/* 101 */     this.lastSmelt = this.furnace.smeltTime;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2)
/*     */   {
/* 108 */     if (par1 == 0)
/*     */     {
/* 110 */       this.furnace.furnaceCookTime = par2;
/*     */     }
/*     */     
/* 113 */     if (par1 == 1)
/*     */     {
/* 115 */       this.furnace.furnaceBurnTime = par2;
/*     */     }
/*     */     
/* 118 */     if (par1 == 2)
/*     */     {
/* 120 */       this.furnace.currentItemBurnTime = par2;
/*     */     }
/*     */     
/* 123 */     if (par1 == 3)
/*     */     {
/* 125 */       this.furnace.vis = par2;
/*     */     }
/*     */     
/* 128 */     if (par1 == 4)
/*     */     {
/* 130 */       this.furnace.smeltTime = par2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 138 */     return this.furnace.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/* 144 */     ItemStack itemstack = null;
/* 145 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 147 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/* 149 */       ItemStack itemstack1 = slot.func_75211_c();
/* 150 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 152 */       if ((par2 != 1) && (par2 != 0))
/*     */       {
/* 154 */         AspectList al = ThaumcraftCraftingManager.getObjectTags(itemstack1);
/*     */         
/* 156 */         if (TileSmelter.isItemFuel(itemstack1))
/*     */         {
/* 158 */           if (!func_75135_a(itemstack1, 1, 2, false))
/*     */           {
/* 160 */             if (!func_75135_a(itemstack1, 0, 1, false))
/*     */             {
/* 162 */               return null;
/*     */             }
/*     */           }
/*     */         }
/* 166 */         else if ((al != null) && (al.size() > 0))
/*     */         {
/* 168 */           if (!func_75135_a(itemstack1, 0, 1, false))
/*     */           {
/* 170 */             return null;
/*     */           }
/*     */         }
/* 173 */         else if ((par2 >= 2) && (par2 < 29))
/*     */         {
/* 175 */           if (!func_75135_a(itemstack1, 29, 38, false))
/*     */           {
/* 177 */             return null;
/*     */           }
/*     */         }
/* 180 */         else if ((par2 >= 29) && (par2 < 38) && (!func_75135_a(itemstack1, 2, 29, false)))
/*     */         {
/* 182 */           return null;
/*     */         }
/*     */       }
/* 185 */       else if (!func_75135_a(itemstack1, 2, 38, false))
/*     */       {
/* 187 */         return null;
/*     */       }
/*     */       
/* 190 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 192 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 196 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 199 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 201 */         return null;
/*     */       }
/*     */       
/* 204 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 207 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/ContainerSmelter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */