/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemHoe;
/*     */ import net.minecraft.item.ItemPickaxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ 
/*     */ public class SlotCraftingArcaneWorkbench extends Slot
/*     */ {
/*     */   private final InventoryCrafting craftMatrix;
/*     */   private EntityPlayer thePlayer;
/*     */   private int amountCrafted;
/*     */   
/*     */   public SlotCraftingArcaneWorkbench(EntityPlayer par1EntityPlayer, InventoryCrafting par2IInventory, IInventory par3IInventory, int par4, int par5, int par6)
/*     */   {
/*  28 */     super(par3IInventory, par4, par5, par6);
/*  29 */     this.thePlayer = par1EntityPlayer;
/*  30 */     this.craftMatrix = par2IInventory;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75214_a(ItemStack stack)
/*     */   {
/*  36 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_75209_a(int amount)
/*     */   {
/*  42 */     if (func_75216_d())
/*     */     {
/*  44 */       this.amountCrafted += Math.min(amount, func_75211_c().field_77994_a);
/*     */     }
/*     */     
/*  47 */     return super.func_75209_a(amount);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_75210_a(ItemStack stack, int amount)
/*     */   {
/*  53 */     this.amountCrafted += amount;
/*  54 */     func_75208_c(stack);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_75208_c(ItemStack stack)
/*     */   {
/*  60 */     if (this.amountCrafted > 0)
/*     */     {
/*  62 */       stack.func_77980_a(this.thePlayer.field_70170_p, this.thePlayer, this.amountCrafted);
/*     */     }
/*     */     
/*  65 */     this.amountCrafted = 0;
/*     */     
/*  67 */     if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150462_ai))
/*     */     {
/*  69 */       this.thePlayer.func_71029_a(AchievementList.field_76017_h);
/*     */     }
/*     */     
/*  72 */     if ((stack.func_77973_b() instanceof ItemPickaxe))
/*     */     {
/*  74 */       this.thePlayer.func_71029_a(AchievementList.field_76018_i);
/*     */     }
/*     */     
/*  77 */     if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150460_al))
/*     */     {
/*  79 */       this.thePlayer.func_71029_a(AchievementList.field_76015_j);
/*     */     }
/*     */     
/*  82 */     if ((stack.func_77973_b() instanceof ItemHoe))
/*     */     {
/*  84 */       this.thePlayer.func_71029_a(AchievementList.field_76013_l);
/*     */     }
/*     */     
/*  87 */     if (stack.func_77973_b() == Items.field_151025_P)
/*     */     {
/*  89 */       this.thePlayer.func_71029_a(AchievementList.field_76014_m);
/*     */     }
/*     */     
/*  92 */     if (stack.func_77973_b() == Items.field_151105_aU)
/*     */     {
/*  94 */       this.thePlayer.func_71029_a(AchievementList.field_76011_n);
/*     */     }
/*     */     
/*  97 */     if (((stack.func_77973_b() instanceof ItemPickaxe)) && (((ItemPickaxe)stack.func_77973_b()).func_150913_i() != net.minecraft.item.Item.ToolMaterial.WOOD))
/*     */     {
/*  99 */       this.thePlayer.func_71029_a(AchievementList.field_76012_o);
/*     */     }
/*     */     
/* 102 */     if ((stack.func_77973_b() instanceof net.minecraft.item.ItemSword))
/*     */     {
/* 104 */       this.thePlayer.func_71029_a(AchievementList.field_76024_r);
/*     */     }
/*     */     
/* 107 */     if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150381_bn))
/*     */     {
/* 109 */       this.thePlayer.func_71029_a(AchievementList.field_75998_D);
/*     */     }
/*     */     
/* 112 */     if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150342_X))
/*     */     {
/* 114 */       this.thePlayer.func_71029_a(AchievementList.field_76000_F);
/*     */     }
/*     */     
/* 117 */     if ((stack.func_77973_b() == Items.field_151153_ao) && (stack.func_77960_j() == 1))
/*     */     {
/* 119 */       this.thePlayer.func_71029_a(AchievementList.field_180219_M);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_82870_a(EntityPlayer playerIn, ItemStack stack)
/*     */   {
/* 126 */     FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, this.craftMatrix);
/* 127 */     func_75208_c(stack);
/* 128 */     ForgeHooks.setCraftingPlayer(playerIn);
/*     */     
/* 130 */     AspectList aspects = thaumcraft.common.lib.crafting.ThaumcraftCraftingManager.findMatchingArcaneRecipeAspects(this.craftMatrix, this.thePlayer);
/* 131 */     if ((aspects.size() > 0) && (this.craftMatrix.func_70301_a(10) != null)) {
/* 132 */       IWand wand = (IWand)this.craftMatrix.func_70301_a(10).func_77973_b();
/* 133 */       wand.consumeAllVis(this.craftMatrix.func_70301_a(10), playerIn, aspects, true, true);
/*     */     }
/*     */     
/* 136 */     ItemStack[] aitemstack = CraftingManager.func_77594_a().func_180303_b(this.craftMatrix, playerIn.field_70170_p);
/* 137 */     ForgeHooks.setCraftingPlayer(null);
/*     */     
/* 139 */     for (int i = 0; i < Math.min(9, aitemstack.length); i++)
/*     */     {
/* 141 */       ItemStack itemstack1 = this.craftMatrix.func_70301_a(i);
/* 142 */       ItemStack itemstack2 = aitemstack[i];
/*     */       
/* 144 */       if (itemstack1 != null)
/*     */       {
/* 146 */         this.craftMatrix.func_70298_a(i, 1);
/*     */       }
/*     */       
/* 149 */       if (itemstack2 != null)
/*     */       {
/* 151 */         if (this.craftMatrix.func_70301_a(i) == null)
/*     */         {
/* 153 */           this.craftMatrix.func_70299_a(i, itemstack2);
/*     */         }
/* 155 */         else if (!this.thePlayer.field_71071_by.func_70441_a(itemstack2))
/*     */         {
/* 157 */           this.thePlayer.func_71019_a(itemstack2, false);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/container/SlotCraftingArcaneWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */