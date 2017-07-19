/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import baubles.api.IBauble;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.items.IRechargable;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InfusionEnchantmentRecipe
/*     */   extends InfusionRecipe
/*     */ {
/*     */   EnumInfusionEnchantment enchantment;
/*     */   
/*     */   public InfusionEnchantmentRecipe(EnumInfusionEnchantment ench, AspectList as, Object[] components)
/*     */   {
/*  37 */     super(ench.research, null, 4, as, null, components);
/*  38 */     this.enchantment = ench;
/*     */   }
/*     */   
/*     */   public InfusionEnchantmentRecipe(InfusionEnchantmentRecipe recipe, ItemStack in) {
/*  42 */     super(recipe.enchantment.research, null, recipe.instability, recipe.aspects, in, recipe.components);
/*  43 */     this.enchantment = recipe.enchantment;
/*     */   }
/*     */   
/*     */   public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player)
/*     */   {
/*  48 */     if ((central == null) || ((this.research != null) && (this.research[0].length() > 0) && (!ResearchHelper.isResearchComplete(player.func_70005_c_(), this.research)))) {
/*  49 */       return false;
/*     */     }
/*  51 */     if (EnumInfusionEnchantment.getInfusionEnchantmentLevel(central, this.enchantment) >= this.enchantment.maxLevel) { return false;
/*     */     }
/*     */     
/*  54 */     if (!this.enchantment.toolClasses.contains("all")) {
/*  55 */       Multimap<String, AttributeModifier> itemMods = central.func_111283_C();
/*  56 */       boolean cool = false;
/*     */       
/*  58 */       if ((itemMods != null) && (itemMods.containsKey(SharedMonsterAttributes.field_111264_e.func_111108_a())) && (this.enchantment.toolClasses.contains("weapon")))
/*     */       {
/*  60 */         cool = true;
/*     */       }
/*     */       
/*  63 */       if ((!cool) && ((central.func_77973_b() instanceof ItemTool))) {
/*  64 */         Set<String> tcs = ((ItemTool)central.func_77973_b()).getToolClasses(central);
/*  65 */         for (String tc : tcs) {
/*  66 */           if (this.enchantment.toolClasses.contains(tc)) {
/*  67 */             cool = true;
/*  68 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  74 */       if ((!cool) && ((central.func_77973_b() instanceof ItemArmor))) {
/*  75 */         String at = "none";
/*  76 */         switch (((ItemArmor)central.func_77973_b()).field_77881_a) {
/*  77 */         case 0:  at = "helm"; break;
/*  78 */         case 1:  at = "chest"; break;
/*  79 */         case 2:  at = "legs"; break;
/*  80 */         case 3:  at = "boots";
/*     */         }
/*  82 */         if ((this.enchantment.toolClasses.contains("armor")) || (this.enchantment.toolClasses.contains(at))) {
/*  83 */           cool = true;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  88 */       if ((!cool) && ((central.func_77973_b() instanceof IBauble))) {
/*  89 */         String at = "none";
/*  90 */         switch (((IBauble)central.func_77973_b()).getBaubleType(central)) {
/*  91 */         case AMULET:  at = "amulet"; break;
/*  92 */         case BELT:  at = "belt"; break;
/*  93 */         case RING:  at = "ring";
/*     */         }
/*  95 */         if ((this.enchantment.toolClasses.contains("bauble")) || (this.enchantment.toolClasses.contains(at))) {
/*  96 */           cool = true;
/*     */         }
/*     */       }
/*     */       
/* 100 */       if ((!cool) && ((central.func_77973_b() instanceof IRechargable)) && (this.enchantment.toolClasses.contains("chargable"))) {
/* 101 */         cool = true;
/*     */       }
/*     */       
/*     */ 
/* 105 */       if (!cool) { return false;
/*     */       }
/*     */     }
/* 108 */     ItemStack i2 = null;
/*     */     
/* 110 */     ArrayList<ItemStack> ii = new ArrayList();
/* 111 */     for (ItemStack is : input) {
/* 112 */       ii.add(is.func_77946_l());
/*     */     }
/*     */     
/* 115 */     for (Object comp : this.components) {
/* 116 */       boolean b = false;
/* 117 */       for (int a = 0; a < ii.size(); a++) {
/* 118 */         i2 = ((ItemStack)ii.get(a)).func_77946_l();
/* 119 */         if (ThaumcraftApiHelper.areItemStacksEqualForCrafting(i2, comp)) {
/* 120 */           ii.remove(a);
/* 121 */           b = true;
/* 122 */           break;
/*     */         }
/*     */       }
/* 125 */       if (!b) { return false;
/*     */       }
/*     */     }
/* 128 */     return ii.size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object getRecipeOutput(EntityPlayer player, ItemStack input, ArrayList<ItemStack> comps)
/*     */   {
/* 134 */     if (input == null) { return null;
/*     */     }
/* 136 */     ItemStack out = input.func_77946_l();
/* 137 */     int cl = EnumInfusionEnchantment.getInfusionEnchantmentLevel(out, this.enchantment);
/*     */     
/* 139 */     if (cl >= this.enchantment.maxLevel) { return null;
/*     */     }
/* 141 */     List<EnumInfusionEnchantment> el = EnumInfusionEnchantment.getInfusionEnchantments(input);
/*     */     
/* 143 */     Random rand = new Random(System.nanoTime());
/* 144 */     if (rand.nextInt(10) < el.size()) {
/* 145 */       int base = 1;
/* 146 */       if (input.func_77942_o()) base += input.func_77978_p().func_74771_c("TC.WARP");
/* 147 */       out.func_77983_a("TC.WARP", new NBTTagByte((byte)base));
/*     */     }
/*     */     
/* 150 */     EnumInfusionEnchantment.addInfusionEnchantment(out, this.enchantment, cl + 1);
/*     */     
/* 152 */     return out;
/*     */   }
/*     */   
/*     */   public AspectList getAspects(EntityPlayer player, ItemStack input, ArrayList<ItemStack> comps)
/*     */   {
/* 157 */     AspectList out = new AspectList();
/*     */     
/* 159 */     if (input == null) { return out;
/*     */     }
/* 161 */     int cl = EnumInfusionEnchantment.getInfusionEnchantmentLevel(input, this.enchantment) + 1;
/*     */     
/* 163 */     if (cl > this.enchantment.maxLevel) { return out;
/*     */     }
/* 165 */     List<EnumInfusionEnchantment> el = EnumInfusionEnchantment.getInfusionEnchantments(input);
/* 166 */     int otherEnchantments = el.size();
/* 167 */     if (el.contains(this.enchantment)) { otherEnchantments--;
/*     */     }
/* 169 */     float modifier = cl + otherEnchantments * 0.33F;
/*     */     
/* 171 */     for (Aspect a : getAspects().getAspects()) {
/* 172 */       out.add(a, (int)(getAspects().getAmount(a) * modifier));
/*     */     }
/*     */     
/* 175 */     return out;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/crafting/InfusionEnchantmentRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */