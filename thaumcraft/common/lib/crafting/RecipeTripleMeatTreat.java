/*    */ package thaumcraft.common.lib.crafting;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.ForgeHooks;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class RecipeTripleMeatTreat implements IRecipe
/*    */ {
/*    */   public boolean func_77569_a(InventoryCrafting inv, World worldIn)
/*    */   {
/* 16 */     boolean sugar = false;
/* 17 */     ArrayList<Integer> meats = new ArrayList();
/* 18 */     for (int a = 0; a < 3; a++)
/* 19 */       for (int b = 0; b < 3; b++)
/* 20 */         if (inv.func_70463_b(a, b) != null) {
/* 21 */           ItemStack stack = inv.func_70463_b(a, b).func_77946_l();
/* 22 */           if ((stack.func_77973_b() == Items.field_151102_aT) && (sugar)) return false;
/* 23 */           if ((stack.func_77973_b() == Items.field_151102_aT) && (!sugar)) {
/* 24 */             sugar = true;
/*    */ 
/*    */           }
/* 27 */           else if (stack.func_77973_b() == ItemsTC.chunks) {
/* 28 */             if ((meats.contains(Integer.valueOf(stack.func_77952_i()))) || (meats.size() >= 3)) return false;
/* 29 */             meats.add(Integer.valueOf(stack.func_77952_i()));
/*    */           }
/*    */           else {
/* 32 */             return false;
/*    */           }
/*    */         }
/* 35 */     return (sugar) && (meats.size() == 3);
/*    */   }
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting inv)
/*    */   {
/* 40 */     return new ItemStack(ItemsTC.tripleMeatTreat);
/*    */   }
/*    */   
/*    */   public int func_77570_a()
/*    */   {
/* 45 */     return 9;
/*    */   }
/*    */   
/*    */   public ItemStack func_77571_b()
/*    */   {
/* 50 */     return new ItemStack(ItemsTC.tripleMeatTreat);
/*    */   }
/*    */   
/*    */   public ItemStack[] func_179532_b(InventoryCrafting inv)
/*    */   {
/* 55 */     ItemStack[] aitemstack = new ItemStack[inv.func_70302_i_()];
/*    */     
/* 57 */     for (int i = 0; i < aitemstack.length; i++)
/*    */     {
/* 59 */       ItemStack itemstack = inv.func_70301_a(i);
/* 60 */       aitemstack[i] = ForgeHooks.getContainerItem(itemstack);
/*    */     }
/*    */     
/* 63 */     return aitemstack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/crafting/RecipeTripleMeatTreat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */