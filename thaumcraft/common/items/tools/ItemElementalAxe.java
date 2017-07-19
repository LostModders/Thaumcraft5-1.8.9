/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemAxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemElementalAxe
/*     */   extends ItemAxe implements IRepairable
/*     */ {
/*     */   public ItemElementalAxe(Item.ToolMaterial enumtoolmaterial)
/*     */   {
/*  36 */     super(enumtoolmaterial);
/*     */   }
/*     */   
/*     */   public Set<String> getToolClasses(ItemStack stack)
/*     */   {
/*  41 */     return ImmutableSet.of("axe");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  48 */     return EnumRarity.RARE;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  54 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EnumAction func_77661_b(ItemStack itemstack)
/*     */   {
/*  61 */     return EnumAction.BOW;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77626_a(ItemStack p_77626_1_)
/*     */   {
/*  67 */     return 72000;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
/*     */   {
/*  73 */     p_77659_3_.func_71008_a(p_77659_1_, func_77626_a(p_77659_1_));
/*  74 */     return p_77659_1_;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/*  80 */     ArrayList<Entity> stuff = EntityUtils.getEntitiesInRange(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, player, EntityItem.class, 10.0D);
/*     */     
/*     */ 
/*  83 */     if ((stuff != null) && (stuff.size() > 0))
/*     */     {
/*  85 */       for (Entity e : stuff) {
/*  86 */         if ((!(e instanceof EntityFollowingItem)) || (((EntityFollowingItem)e).target == null)) {
/*  87 */           if ((!e.field_70128_L) && ((e instanceof EntityItem))) {
/*  88 */             double d6 = e.field_70165_t - player.field_70165_t;
/*  89 */             double d8 = e.field_70163_u - player.field_70163_u + player.field_70131_O / 2.0F;
/*  90 */             double d10 = e.field_70161_v - player.field_70161_v;
/*  91 */             double d11 = MathHelper.func_76133_a(d6 * d6 + d8 * d8 + d10 * d10);
/*  92 */             d6 /= d11;
/*  93 */             d8 /= d11;
/*  94 */             d10 /= d11;
/*  95 */             double d13 = 0.3D;
/*  96 */             e.field_70159_w -= d6 * d13;
/*  97 */             e.field_70181_x -= d8 * d13 - 0.1D;
/*  98 */             e.field_70179_y -= d10 * d13;
/*  99 */             if (e.field_70159_w > 0.25D) e.field_70159_w = 0.25D;
/* 100 */             if (e.field_70159_w < -0.25D) e.field_70159_w = -0.25D;
/* 101 */             if (e.field_70181_x > 0.25D) e.field_70181_x = 0.25D;
/* 102 */             if (e.field_70181_x < -0.25D) e.field_70181_x = -0.25D;
/* 103 */             if (e.field_70179_y > 0.25D) e.field_70179_y = 0.25D;
/* 104 */             if (e.field_70179_y < -0.25D) e.field_70179_y = -0.25D;
/* 105 */             if (player.field_70170_p.field_72995_K) {
/* 106 */               Thaumcraft.proxy.getFX().crucibleBubble((float)e.field_70165_t + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125F, (float)e.field_70163_u + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125F, (float)e.field_70161_v + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125F, 0.33F, 0.33F, 1.0F);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 119 */     ItemStack w1 = new ItemStack(this);
/* 120 */     EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.BURROWING, 1);
/* 121 */     EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.COLLECTOR, 1);
/* 122 */     par3List.add(w1);
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
/*     */   {
/* 127 */     super.func_77663_a(stack, worldIn, entityIn, itemSlot, isSelected);
/*     */     
/* 129 */     if (entityIn.field_70173_aa % 100 == 0) {
/* 130 */       List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(stack);
/* 131 */       if (!list.contains(EnumInfusionEnchantment.BURROWING)) {
/* 132 */         EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.BURROWING, 1);
/*     */       }
/* 134 */       if (!list.contains(EnumInfusionEnchantment.COLLECTOR)) {
/* 135 */         EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.COLLECTOR, 1);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemElementalAxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */