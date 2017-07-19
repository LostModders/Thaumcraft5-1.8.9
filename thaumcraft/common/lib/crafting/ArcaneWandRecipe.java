/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.IArcaneWorkbench;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ 
/*     */ public class ArcaneWandRecipe implements thaumcraft.api.crafting.IArcaneRecipe
/*     */ {
/*     */   private static final int MAX_CRAFT_GRID_WIDTH = 3;
/*     */   private static final int MAX_CRAFT_GRID_HEIGHT = 3;
/*  22 */   private boolean mirrored = true;
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_77572_b(InventoryCrafting inv)
/*     */   {
/*  28 */     ItemStack out = null;
/*  29 */     String bc = null;
/*  30 */     String br = null;
/*  31 */     int cc = 0;
/*  32 */     int cr = 0;
/*  33 */     ItemStack cap1 = inv.func_70463_b(0, 2);
/*  34 */     ItemStack cap2 = inv.func_70463_b(2, 0);
/*  35 */     ItemStack rod = inv.func_70463_b(1, 1);
/*  36 */     if ((inv.func_70463_b(0, 0) != null) || (inv.func_70463_b(0, 1) != null) || (inv.func_70463_b(1, 0) != null) || (inv.func_70463_b(1, 2) != null) || (inv.func_70463_b(2, 1) != null) || (inv.func_70463_b(2, 2) != null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  41 */       return null; }
/*  42 */     if ((cap1 != null) && (cap2 != null) && (rod != null) && (checkItemEquals(cap1, cap2)))
/*     */     {
/*  44 */       for (WandCap wc : WandCap.caps.values()) {
/*  45 */         if (checkItemEquals(cap1, wc.getItem())) {
/*  46 */           bc = wc.getTag();
/*  47 */           cc = wc.getCraftCost();
/*  48 */           break;
/*     */         }
/*     */       }
/*     */       
/*  52 */       for (WandRod wr : WandRod.rods.values()) {
/*  53 */         if (checkItemEquals(rod, wr.getItem())) {
/*  54 */           br = wr.getTag();
/*  55 */           cr = wr.getCraftCost();
/*  56 */           break;
/*     */         }
/*     */       }
/*     */       
/*  60 */       if ((bc != null) && (br != null)) {
/*  61 */         int cost = cc * cr * 6;
/*  62 */         out = new ItemStack(ItemsTC.wand);
/*  63 */         ((IWand)out.func_77973_b()).setCap(out, (WandCap)WandCap.caps.get(bc));
/*  64 */         ((IWand)out.func_77973_b()).setRod(out, (WandRod)WandRod.rods.get(br));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  69 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects(InventoryCrafting inv)
/*     */   {
/*  75 */     AspectList al = new AspectList();
/*     */     
/*  77 */     int cc = -1;
/*  78 */     int cr = -1;
/*  79 */     ItemStack cap1 = inv.func_70463_b(0, 2);
/*  80 */     ItemStack cap2 = inv.func_70463_b(2, 0);
/*  81 */     ItemStack rod = inv.func_70463_b(1, 1);
/*  82 */     if ((inv.func_70463_b(0, 0) != null) || (inv.func_70463_b(0, 1) != null) || (inv.func_70463_b(1, 0) != null) || (inv.func_70463_b(1, 2) != null) || (inv.func_70463_b(2, 1) != null) || (inv.func_70463_b(2, 2) != null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  87 */       return al; }
/*  88 */     int cost; if ((cap1 != null) && (cap2 != null) && (rod != null) && (checkItemEquals(cap1, cap2)))
/*     */     {
/*  90 */       for (WandCap wc : WandCap.caps.values()) {
/*  91 */         if (checkItemEquals(cap1, wc.getItem())) {
/*  92 */           cc = wc.getCraftCost();
/*  93 */           break;
/*     */         }
/*     */       }
/*     */       
/*  97 */       for (WandRod wr : WandRod.rods.values()) {
/*  98 */         if (checkItemEquals(rod, wr.getItem())) {
/*  99 */           cr = wr.getCraftCost();
/* 100 */           break;
/*     */         }
/*     */       }
/*     */       
/* 104 */       if ((cc >= 0) && (cr >= 0)) {
/* 105 */         cost = cc * cr * 6;
/* 106 */         for (Aspect as : Aspect.getPrimalAspects()) {
/* 107 */           al.add(as, cost);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 112 */     return al;
/*     */   }
/*     */   
/*     */   public ItemStack func_77571_b()
/*     */   {
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public boolean matches(InventoryCrafting inv, World world, EntityPlayer player)
/*     */   {
/* 122 */     ItemStack cap1 = inv.func_70463_b(0, 2);
/* 123 */     ItemStack cap2 = inv.func_70463_b(2, 0);
/* 124 */     ItemStack rod = inv.func_70463_b(1, 1);
/*     */     
/* 126 */     if ((inv.func_70463_b(0, 0) != null) || (inv.func_70463_b(0, 1) != null) || (inv.func_70463_b(1, 0) != null) || (inv.func_70463_b(1, 2) != null) || (inv.func_70463_b(2, 1) != null) || (inv.func_70463_b(2, 2) != null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 131 */       return false;
/*     */     }
/*     */     
/* 134 */     return checkMatch(cap1, cap2, rod, player);
/*     */   }
/*     */   
/*     */   private boolean checkMatch(ItemStack cap1, ItemStack cap2, ItemStack rod, EntityPlayer player)
/*     */   {
/* 139 */     boolean bc = false;
/* 140 */     boolean br = false;
/*     */     
/* 142 */     if ((cap1 != null) && (cap2 != null) && (rod != null) && (checkItemEquals(cap1, cap2)))
/*     */     {
/* 144 */       for (WandCap wc : WandCap.caps.values()) {
/* 145 */         if ((checkItemEquals(cap1, wc.getItem())) && ((player == null) || (ResearchHelper.isResearchComplete(player.func_70005_c_(), wc.getResearch()))))
/*     */         {
/* 147 */           bc = true;
/* 148 */           break;
/*     */         }
/*     */       }
/*     */       
/* 152 */       for (WandRod wr : WandRod.rods.values()) {
/* 153 */         if ((checkItemEquals(rod, wr.getItem())) && ((player == null) || (ResearchHelper.isResearchComplete(player.func_70005_c_(), wr.getResearch()))))
/*     */         {
/* 155 */           br = true;
/* 156 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 162 */     return (br) && (bc);
/*     */   }
/*     */   
/*     */   private boolean checkItemEquals(ItemStack target, ItemStack input)
/*     */   {
/* 167 */     if (((input == null) && (target != null)) || ((input != null) && (target == null)))
/*     */     {
/* 169 */       return false;
/*     */     }
/* 171 */     return (target.func_77973_b() == input.func_77973_b()) && ((!target.func_77942_o()) || (ItemStack.func_77970_a(target, input))) && ((target.func_77952_i() == 32767) || (target.func_77952_i() == input.func_77952_i()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_77570_a()
/*     */   {
/* 178 */     return 9;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 183 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getResearch()
/*     */   {
/* 188 */     return new String[] { "" };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_)
/*     */   {
/* 195 */     ItemStack[] aitemstack = new ItemStack[p_179532_1_.func_70302_i_()];
/*     */     
/* 197 */     for (int i = 0; i < Math.min(9, aitemstack.length); i++)
/*     */     {
/* 199 */       ItemStack itemstack = p_179532_1_.func_70301_a(i);
/* 200 */       aitemstack[i] = ForgeHooks.getContainerItem(itemstack);
/*     */     }
/*     */     
/* 203 */     return aitemstack;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77569_a(InventoryCrafting inv, World world)
/*     */   {
/* 209 */     return ((inv instanceof IArcaneWorkbench)) && (matches(inv, world, null));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/crafting/ArcaneWandRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */