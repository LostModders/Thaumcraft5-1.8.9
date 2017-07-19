/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.EnumHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ import thaumcraft.api.items.IWarpingGear;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemPrimalCrusher
/*     */   extends ItemTool
/*     */   implements IRepairable, IWarpingGear
/*     */ {
/*  38 */   public static Item.ToolMaterial material = EnumHelper.addToolMaterial("PRIMALVOID", 5, 500, 8.0F, 4.0F, 20);
/*     */   
/*     */ 
/*  41 */   private static final Set isEffective = Sets.newHashSet(new Block[] { Blocks.field_150347_e, Blocks.field_150334_T, Blocks.field_150333_U, Blocks.field_150348_b, Blocks.field_150322_A, Blocks.field_150341_Y, Blocks.field_150366_p, Blocks.field_150339_S, Blocks.field_150365_q, Blocks.field_150340_R, Blocks.field_150352_o, Blocks.field_150482_ag, Blocks.field_150484_ah, Blocks.field_150432_aD, Blocks.field_150424_aL, Blocks.field_150369_x, Blocks.field_150368_y, Blocks.field_150450_ax, Blocks.field_150439_ay, Blocks.field_150448_aq, Blocks.field_150319_E, Blocks.field_150318_D, Blocks.field_150408_cc, Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150431_aC, Blocks.field_150433_aE, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, Blocks.field_150391_bh, BlocksTC.taintBlock, BlocksTC.taintFibre, Blocks.field_150343_Z });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemPrimalCrusher()
/*     */   {
/*  52 */     super(3.5F, material, isEffective);
/*  53 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_150897_b(Block p_150897_1_)
/*     */   {
/*  59 */     return (p_150897_1_.func_149688_o() != Material.field_151575_d) && (p_150897_1_.func_149688_o() != Material.field_151584_j) && (p_150897_1_.func_149688_o() != Material.field_151585_k);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
/*     */   {
/*  67 */     return (p_150893_2_.func_149688_o() != Material.field_151573_f) && (p_150893_2_.func_149688_o() != Material.field_151574_g) && (p_150893_2_.func_149688_o() != Material.field_151576_e) ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.field_77864_a;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Set<String> getToolClasses(ItemStack stack)
/*     */   {
/*  74 */     return ImmutableSet.of("shovel", "pickaxe");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  80 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 1)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */   private boolean isEffectiveAgainst(Block block)
/*     */   {
/*  85 */     for (Object b : isEffective) {
/*  86 */       if (b == block) {
/*  87 */         return true;
/*     */       }
/*     */     }
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public int func_77619_b()
/*     */   {
/*  95 */     return 20;
/*     */   }
/*     */   
/*     */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*     */   {
/* 100 */     return 2;
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*     */   {
/* 105 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*     */     
/* 107 */     if ((stack.func_77951_h()) && (entity != null) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/* 108 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*     */     }
/*     */     
/* 111 */     if (entity.field_70173_aa % 100 == 0) {
/* 112 */       List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(stack);
/* 113 */       if (!list.contains(EnumInfusionEnchantment.DESTRUCTIVE)) {
/* 114 */         EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/*     */       }
/* 116 */       if (!list.contains(EnumInfusionEnchantment.REFINING)) {
/* 117 */         EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.REFINING, 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 125 */     ItemStack w1 = new ItemStack(this);
/* 126 */     EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/* 127 */     EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.REFINING, 1);
/* 128 */     par3List.add(w1);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemPrimalCrusher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */