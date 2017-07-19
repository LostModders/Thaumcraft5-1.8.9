/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.items.IRunicArmor;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.common.lib.events.PlayerEvents;
/*     */ 
/*     */ 
/*     */ public class InfusionRunicAugmentRecipe
/*     */   extends InfusionRecipe
/*     */ {
/*     */   private ItemStack[] components;
/*     */   
/*     */   public InfusionRunicAugmentRecipe()
/*     */   {
/*  26 */     super("RUNICAUGMENTATION", null, 0, null, null, new ItemStack[] { new ItemStack(Items.field_151045_i), new ItemStack(ItemsTC.salisMundus) });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public InfusionRunicAugmentRecipe(ItemStack in)
/*     */   {
/*  36 */     super("RUNICAUGMENTATION", null, 0, null, in, new ItemStack[] { new ItemStack(Items.field_151045_i), new ItemStack(ItemsTC.salisMundus) });
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  41 */     this.components = new ItemStack[] { new ItemStack(Items.field_151045_i), new ItemStack(ItemsTC.salisMundus) };
/*     */     
/*     */ 
/*     */ 
/*  45 */     int fc = PlayerEvents.getFinalCharge(in);
/*  46 */     if (fc > 0) {
/*  47 */       ArrayList<ItemStack> com = new ArrayList();
/*  48 */       com.add(new ItemStack(Items.field_151045_i));
/*  49 */       com.add(new ItemStack(ItemsTC.salisMundus));
/*  50 */       int c = 0;
/*  51 */       while (c < fc) {
/*  52 */         c++;
/*  53 */         com.add(new ItemStack(ItemsTC.salisMundus));
/*     */       }
/*  55 */       this.components = ((ItemStack[])com.toArray(this.components));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player)
/*     */   {
/*  65 */     if ((this.research != null) && (this.research[0].length() > 0) && (!ResearchHelper.isResearchComplete(player.func_70005_c_(), this.research))) {
/*  66 */       return false;
/*     */     }
/*     */     
/*  69 */     if (!(central.func_77973_b() instanceof IRunicArmor)) {
/*  70 */       return false;
/*     */     }
/*     */     
/*  73 */     ItemStack i2 = null;
/*     */     
/*  75 */     ArrayList<ItemStack> ii = new ArrayList();
/*  76 */     for (ItemStack is : input) {
/*  77 */       ii.add(is.func_77946_l());
/*     */     }
/*     */     
/*  80 */     for (ItemStack comp : getComponents(central)) {
/*  81 */       boolean b = false;
/*  82 */       for (int a = 0; a < ii.size(); a++) {
/*  83 */         i2 = ((ItemStack)ii.get(a)).func_77946_l();
/*     */         
/*     */ 
/*     */ 
/*  87 */         if (ThaumcraftApiHelper.areItemStacksEqualForCrafting(i2, comp)) {
/*  88 */           ii.remove(a);
/*  89 */           b = true;
/*  90 */           break;
/*     */         }
/*     */       }
/*  93 */       if (!b) return false;
/*     */     }
/*  95 */     return ii.size() == 0;
/*     */   }
/*     */   
/*     */   public Object getRecipeOutput(EntityPlayer player, ItemStack input, ArrayList<ItemStack> comps)
/*     */   {
/* 100 */     if (input == null) return null;
/* 101 */     ItemStack out = input.func_77946_l();
/* 102 */     int base = PlayerEvents.getHardening(input) + 1;
/* 103 */     out.func_77983_a("RS.HARDEN", new NBTTagByte((byte)base));
/* 104 */     return out;
/*     */   }
/*     */   
/*     */   public AspectList getAspects(EntityPlayer player, ItemStack input, ArrayList<ItemStack> comps)
/*     */   {
/* 109 */     AspectList out = new AspectList();
/* 110 */     int vis = (int)(32.0D * Math.pow(2.0D, PlayerEvents.getFinalCharge(input)));
/* 111 */     if (vis > 0) {
/* 112 */       out.add(Aspect.PROTECT, vis / 2);
/* 113 */       out.add(Aspect.CRYSTAL, vis / 2);
/* 114 */       out.add(Aspect.ENERGY, vis);
/*     */     }
/* 116 */     return out;
/*     */   }
/*     */   
/*     */   public int getInstability(EntityPlayer player, ItemStack input, ArrayList<ItemStack> comps)
/*     */   {
/* 121 */     int i = 5 + PlayerEvents.getFinalCharge(input) / 2;
/* 122 */     return i;
/*     */   }
/*     */   
/*     */   public ItemStack[] getComponents(ItemStack input) {
/* 126 */     ArrayList<ItemStack> com = new ArrayList();
/* 127 */     com.add(new ItemStack(Items.field_151045_i));
/* 128 */     com.add(new ItemStack(ItemsTC.salisMundus));
/* 129 */     int fc = PlayerEvents.getFinalCharge(input);
/* 130 */     if (fc > 0) {
/* 131 */       for (int c = 0; c < fc; c++) {
/* 132 */         com.add(new ItemStack(ItemsTC.salisMundus));
/*     */       }
/*     */     }
/* 135 */     return (ItemStack[])com.toArray(new ItemStack[0]);
/*     */   }
/*     */   
/*     */   public ItemStack[] getComponents()
/*     */   {
/* 140 */     return this.components;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/crafting/InfusionRunicAugmentRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */