/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.RecipesArmorDyes;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.items.armor.ItemRobeArmor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipesRobeArmorDyes
/*     */   extends RecipesArmorDyes
/*     */ {
/*     */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
/*     */   {
/*  23 */     ItemStack itemstack = null;
/*  24 */     ArrayList arraylist = new ArrayList();
/*     */     
/*  26 */     for (int i = 0; i < par1InventoryCrafting.func_70302_i_(); i++)
/*     */     {
/*  28 */       ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(i);
/*     */       
/*  30 */       if (itemstack1 != null)
/*     */       {
/*  32 */         if ((itemstack1.func_77973_b() instanceof ItemArmor))
/*     */         {
/*  34 */           ItemArmor itemarmor = (ItemArmor)itemstack1.func_77973_b();
/*     */           
/*  36 */           if ((!(itemarmor instanceof ItemRobeArmor)) || (itemstack != null))
/*     */           {
/*  38 */             return false;
/*     */           }
/*     */           
/*  41 */           itemstack = itemstack1;
/*     */         }
/*     */         else
/*     */         {
/*  45 */           if (itemstack1.func_77973_b() != Items.field_151100_aR)
/*     */           {
/*  47 */             return false;
/*     */           }
/*     */           
/*  50 */           arraylist.add(itemstack1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  55 */     return (itemstack != null) && (!arraylist.isEmpty());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting)
/*     */   {
/*  63 */     ItemStack itemstack = null;
/*  64 */     int[] aint = new int[3];
/*  65 */     int i = 0;
/*  66 */     int j = 0;
/*  67 */     ItemArmor itemarmor = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */     for (int k = 0; k < par1InventoryCrafting.func_70302_i_(); k++)
/*     */     {
/*  76 */       ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(k);
/*     */       
/*  78 */       if (itemstack1 != null)
/*     */       {
/*  80 */         if ((itemstack1.func_77973_b() instanceof ItemArmor))
/*     */         {
/*  82 */           itemarmor = (ItemArmor)itemstack1.func_77973_b();
/*     */           
/*  84 */           if ((!(itemarmor instanceof ItemRobeArmor)) || (itemstack != null))
/*     */           {
/*  86 */             return null;
/*     */           }
/*     */           
/*  89 */           itemstack = itemstack1.func_77946_l();
/*  90 */           itemstack.field_77994_a = 1;
/*     */           
/*  92 */           if (itemarmor.func_82816_b_(itemstack1))
/*     */           {
/*  94 */             int l = itemarmor.func_82814_b(itemstack);
/*  95 */             float f = (l >> 16 & 0xFF) / 255.0F;
/*  96 */             float f1 = (l >> 8 & 0xFF) / 255.0F;
/*  97 */             float f2 = (l & 0xFF) / 255.0F;
/*  98 */             i = (int)(i + Math.max(f, Math.max(f1, f2)) * 255.0F);
/*  99 */             aint[0] = ((int)(aint[0] + f * 255.0F));
/* 100 */             aint[1] = ((int)(aint[1] + f1 * 255.0F));
/* 101 */             aint[2] = ((int)(aint[2] + f2 * 255.0F));
/* 102 */             j++;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 107 */           if (itemstack1.func_77973_b() != Items.field_151100_aR)
/*     */           {
/* 109 */             return null;
/*     */           }
/*     */           
/* 112 */           float[] afloat = EntitySheep.func_175513_a(EnumDyeColor.func_176766_a(itemstack1.func_77952_i()));
/*     */           
/* 114 */           int j1 = (int)(afloat[0] * 255.0F);
/* 115 */           int k1 = (int)(afloat[1] * 255.0F);
/* 116 */           int i1 = (int)(afloat[2] * 255.0F);
/* 117 */           i += Math.max(j1, Math.max(k1, i1));
/* 118 */           aint[0] += j1;
/* 119 */           aint[1] += k1;
/* 120 */           aint[2] += i1;
/* 121 */           j++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 126 */     if (itemarmor == null)
/*     */     {
/* 128 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 132 */     k = aint[0] / j;
/* 133 */     int l1 = aint[1] / j;
/* 134 */     int l = aint[2] / j;
/* 135 */     float f = i / j;
/* 136 */     float f1 = Math.max(k, Math.max(l1, l));
/* 137 */     k = (int)(k * f / f1);
/* 138 */     l1 = (int)(l1 * f / f1);
/* 139 */     l = (int)(l * f / f1);
/* 140 */     int i1 = (k << 8) + l1;
/* 141 */     i1 = (i1 << 8) + l;
/* 142 */     itemarmor.func_82813_b(itemstack, i1);
/* 143 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/crafting/RecipesRobeArmorDyes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */