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
/*     */ public class ArcaneSceptreRecipe implements thaumcraft.api.crafting.IArcaneRecipe
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
/*  33 */     ItemStack cap1 = inv.func_70463_b(1, 0);
/*  34 */     ItemStack cap2 = inv.func_70463_b(2, 1);
/*  35 */     ItemStack cap3 = inv.func_70463_b(0, 2);
/*  36 */     ItemStack rod = inv.func_70463_b(1, 1);
/*  37 */     ItemStack focus = inv.func_70463_b(2, 0);
/*     */     
/*  39 */     if ((inv.func_70463_b(0, 0) != null) || (inv.func_70463_b(0, 1) != null) || (inv.func_70463_b(1, 2) != null) || (inv.func_70463_b(2, 2) != null))
/*     */     {
/*     */ 
/*  42 */       return null;
/*     */     }
/*  44 */     if ((cap1 != null) && (cap2 != null) && (cap3 != null) && (rod != null) && (focus != null) && (checkItemEquals(focus, new ItemStack(ItemsTC.primalCharm))) && (checkItemEquals(cap1, cap2)) && (checkItemEquals(cap1, cap3)))
/*     */     {
/*     */ 
/*     */ 
/*  48 */       for (WandCap wc : WandCap.caps.values()) {
/*  49 */         if (checkItemEquals(cap1, wc.getItem())) {
/*  50 */           bc = wc.getTag();
/*  51 */           cc = wc.getCraftCost();
/*  52 */           break;
/*     */         }
/*     */       }
/*     */       
/*  56 */       for (WandRod wr : WandRod.rods.values()) {
/*  57 */         if ((checkItemEquals(rod, wr.getItem())) && (!wr.isStaff())) {
/*  58 */           br = wr.getTag();
/*  59 */           cr = wr.getCraftCost();
/*  60 */           break;
/*     */         }
/*     */       }
/*     */       
/*  64 */       if ((bc != null) && (br != null)) {
/*  65 */         int cost = cc * cr * 10;
/*  66 */         out = new ItemStack(ItemsTC.wand);
/*  67 */         ((IWand)out.func_77973_b()).setCap(out, (WandCap)WandCap.caps.get(bc));
/*  68 */         ((IWand)out.func_77973_b()).setRod(out, (WandRod)WandRod.rods.get(br));
/*  69 */         out.func_77983_a("sceptre", new net.minecraft.nbt.NBTTagByte((byte)1));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  74 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects(InventoryCrafting inv)
/*     */   {
/*  80 */     AspectList al = new AspectList();
/*     */     
/*  82 */     int cc = -1;
/*  83 */     int cr = -1;
/*  84 */     ItemStack cap1 = inv.func_70463_b(1, 0);
/*  85 */     ItemStack cap2 = inv.func_70463_b(2, 1);
/*  86 */     ItemStack cap3 = inv.func_70463_b(0, 2);
/*  87 */     ItemStack rod = inv.func_70463_b(1, 1);
/*  88 */     ItemStack focus = inv.func_70463_b(2, 0);
/*     */     
/*  90 */     if ((inv.func_70463_b(0, 0) != null) || (inv.func_70463_b(0, 1) != null) || (inv.func_70463_b(1, 2) != null) || (inv.func_70463_b(2, 2) != null))
/*     */     {
/*     */ 
/*  93 */       return al; }
/*     */     int cost;
/*  95 */     if ((cap1 != null) && (cap2 != null) && (cap3 != null) && (rod != null) && (focus != null) && (checkItemEquals(focus, new ItemStack(ItemsTC.primalCharm))) && (checkItemEquals(cap1, cap2)) && (checkItemEquals(cap1, cap3)))
/*     */     {
/*     */ 
/*     */ 
/*  99 */       for (WandCap wc : WandCap.caps.values()) {
/* 100 */         if (checkItemEquals(cap1, wc.getItem())) {
/* 101 */           cc = wc.getCraftCost();
/* 102 */           break;
/*     */         }
/*     */       }
/*     */       
/* 106 */       for (WandRod wr : WandRod.rods.values()) {
/* 107 */         if ((checkItemEquals(rod, wr.getItem())) && (!wr.isStaff())) {
/* 108 */           cr = wr.getCraftCost();
/* 109 */           break;
/*     */         }
/*     */       }
/*     */       
/* 113 */       if ((cc >= 0) && (cr >= 0)) {
/* 114 */         cost = cc * cr * 10;
/* 115 */         for (Aspect as : Aspect.getPrimalAspects()) {
/* 116 */           al.add(as, cost);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 121 */     return al;
/*     */   }
/*     */   
/*     */   public ItemStack func_77571_b()
/*     */   {
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   public boolean matches(InventoryCrafting inv, World world, EntityPlayer player)
/*     */   {
/* 131 */     if ((player != null) && (!ResearchHelper.isResearchComplete(player.func_70005_c_(), "SCEPTRE"))) { return false;
/*     */     }
/* 133 */     ItemStack cap1 = inv.func_70463_b(1, 0);
/* 134 */     ItemStack cap2 = inv.func_70463_b(2, 1);
/* 135 */     ItemStack cap3 = inv.func_70463_b(0, 2);
/* 136 */     ItemStack rod = inv.func_70463_b(1, 1);
/* 137 */     ItemStack focus = inv.func_70463_b(2, 0);
/*     */     
/* 139 */     if ((inv.func_70463_b(0, 0) != null) || (inv.func_70463_b(0, 1) != null) || (inv.func_70463_b(1, 2) != null) || (inv.func_70463_b(2, 2) != null))
/*     */     {
/*     */ 
/* 142 */       return false;
/*     */     }
/* 144 */     return checkMatch(cap1, cap2, cap3, rod, focus, player);
/*     */   }
/*     */   
/*     */   private boolean checkMatch(ItemStack cap1, ItemStack cap2, ItemStack cap3, ItemStack rod, ItemStack focus, EntityPlayer player)
/*     */   {
/* 149 */     boolean bc = false;
/* 150 */     boolean br = false;
/*     */     
/* 152 */     if ((cap1 != null) && (cap2 != null) && (cap3 != null) && (rod != null) && (focus != null) && (checkItemEquals(focus, new ItemStack(ItemsTC.primalCharm))) && (checkItemEquals(cap1, cap2)) && (checkItemEquals(cap1, cap3)))
/*     */     {
/*     */ 
/*     */ 
/* 156 */       for (WandCap wc : WandCap.caps.values()) {
/* 157 */         if ((checkItemEquals(cap1, wc.getItem())) && ((player == null) || (ResearchHelper.isResearchComplete(player.func_70005_c_(), wc.getResearch()))))
/*     */         {
/* 159 */           bc = true;
/* 160 */           break;
/*     */         }
/*     */       }
/*     */       
/* 164 */       for (WandRod wr : WandRod.rods.values()) {
/* 165 */         if ((checkItemEquals(rod, wr.getItem())) && (!wr.isStaff()) && ((player == null) || (ResearchHelper.isResearchComplete(player.func_70005_c_(), wr.getResearch()))))
/*     */         {
/* 167 */           br = true;
/* 168 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 174 */     return (br) && (bc);
/*     */   }
/*     */   
/*     */   private boolean checkItemEquals(ItemStack target, ItemStack input)
/*     */   {
/* 179 */     if (((input == null) && (target != null)) || ((input != null) && (target == null)))
/*     */     {
/* 181 */       return false;
/*     */     }
/* 183 */     return (target.func_77973_b() == input.func_77973_b()) && (target.func_77952_i() == input.func_77952_i());
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77570_a()
/*     */   {
/* 189 */     return 9;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 194 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getResearch()
/*     */   {
/* 199 */     return new String[] { "" };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_)
/*     */   {
/* 206 */     ItemStack[] aitemstack = new ItemStack[p_179532_1_.func_70302_i_()];
/*     */     
/* 208 */     for (int i = 0; i < Math.min(9, aitemstack.length); i++)
/*     */     {
/* 210 */       ItemStack itemstack = p_179532_1_.func_70301_a(i);
/* 211 */       aitemstack[i] = ForgeHooks.getContainerItem(itemstack);
/*     */     }
/*     */     
/* 214 */     return aitemstack;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77569_a(InventoryCrafting inv, World world)
/*     */   {
/* 220 */     return ((inv instanceof IArcaneWorkbench)) && (matches(inv, world, null));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/crafting/ArcaneSceptreRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */