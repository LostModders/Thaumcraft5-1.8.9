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
/*     */ import thaumcraft.common.items.armor.ItemVoidRobeArmor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipesVoidRobeArmorDyes
/*     */   extends RecipesArmorDyes
/*     */ {
/*     */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
/*     */   {
/*  24 */     ItemStack itemstack = null;
/*  25 */     ArrayList arraylist = new ArrayList();
/*     */     
/*  27 */     for (int i = 0; i < par1InventoryCrafting.func_70302_i_(); i++)
/*     */     {
/*  29 */       ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(i);
/*     */       
/*  31 */       if (itemstack1 != null)
/*     */       {
/*  33 */         if ((itemstack1.func_77973_b() instanceof ItemArmor))
/*     */         {
/*  35 */           ItemArmor itemarmor = (ItemArmor)itemstack1.func_77973_b();
/*     */           
/*  37 */           if ((!(itemarmor instanceof ItemVoidRobeArmor)) || (itemstack != null))
/*     */           {
/*  39 */             return false;
/*     */           }
/*     */           
/*  42 */           itemstack = itemstack1;
/*     */         }
/*     */         else
/*     */         {
/*  46 */           if (itemstack1.func_77973_b() != Items.field_151100_aR)
/*     */           {
/*  48 */             return false;
/*     */           }
/*     */           
/*  51 */           arraylist.add(itemstack1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  56 */     return (itemstack != null) && (!arraylist.isEmpty());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting)
/*     */   {
/*  64 */     ItemStack itemstack = null;
/*  65 */     int[] aint = new int[3];
/*  66 */     int i = 0;
/*  67 */     int j = 0;
/*  68 */     ItemArmor itemarmor = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  75 */     for (int k = 0; k < par1InventoryCrafting.func_70302_i_(); k++)
/*     */     {
/*  77 */       ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(k);
/*     */       
/*  79 */       if (itemstack1 != null)
/*     */       {
/*  81 */         if ((itemstack1.func_77973_b() instanceof ItemArmor))
/*     */         {
/*  83 */           itemarmor = (ItemArmor)itemstack1.func_77973_b();
/*     */           
/*  85 */           if ((!(itemarmor instanceof ItemVoidRobeArmor)) || (itemstack != null))
/*     */           {
/*  87 */             return null;
/*     */           }
/*     */           
/*  90 */           itemstack = itemstack1.func_77946_l();
/*  91 */           itemstack.field_77994_a = 1;
/*     */           
/*  93 */           if (itemarmor.func_82816_b_(itemstack1))
/*     */           {
/*  95 */             int l = itemarmor.func_82814_b(itemstack);
/*  96 */             float f = (l >> 16 & 0xFF) / 255.0F;
/*  97 */             float f1 = (l >> 8 & 0xFF) / 255.0F;
/*  98 */             float f2 = (l & 0xFF) / 255.0F;
/*  99 */             i = (int)(i + Math.max(f, Math.max(f1, f2)) * 255.0F);
/* 100 */             aint[0] = ((int)(aint[0] + f * 255.0F));
/* 101 */             aint[1] = ((int)(aint[1] + f1 * 255.0F));
/* 102 */             aint[2] = ((int)(aint[2] + f2 * 255.0F));
/* 103 */             j++;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 108 */           if (itemstack1.func_77973_b() != Items.field_151100_aR)
/*     */           {
/* 110 */             return null;
/*     */           }
/*     */           
/* 113 */           float[] afloat = EntitySheep.func_175513_a(EnumDyeColor.func_176766_a(itemstack1.func_77952_i()));
/*     */           
/* 115 */           int j1 = (int)(afloat[0] * 255.0F);
/* 116 */           int k1 = (int)(afloat[1] * 255.0F);
/* 117 */           int i1 = (int)(afloat[2] * 255.0F);
/* 118 */           i += Math.max(j1, Math.max(k1, i1));
/* 119 */           aint[0] += j1;
/* 120 */           aint[1] += k1;
/* 121 */           aint[2] += i1;
/* 122 */           j++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 127 */     if (itemarmor == null)
/*     */     {
/* 129 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 133 */     k = aint[0] / j;
/* 134 */     int l1 = aint[1] / j;
/* 135 */     int l = aint[2] / j;
/* 136 */     float f = i / j;
/* 137 */     float f1 = Math.max(k, Math.max(l1, l));
/* 138 */     k = (int)(k * f / f1);
/* 139 */     l1 = (int)(l1 * f / f1);
/* 140 */     l = (int)(l * f / f1);
/* 141 */     int i1 = (k << 8) + l1;
/* 142 */     i1 = (i1 << 8) + l;
/* 143 */     itemarmor.func_82813_b(itemstack, i1);
/* 144 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/crafting/RecipesVoidRobeArmorDyes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */