/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemElementalSword extends ItemSword implements IRepairable
/*     */ {
/*     */   public ItemElementalSword(Item.ToolMaterial enumtoolmaterial)
/*     */   {
/*  33 */     super(enumtoolmaterial);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  39 */     ItemStack w1 = new ItemStack(this);
/*  40 */     EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.ARCING, 2);
/*  41 */     par3List.add(w1);
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  47 */     return EnumRarity.RARE;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  53 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
/*     */   {
/*  58 */     super.func_77663_a(stack, worldIn, entityIn, itemSlot, isSelected);
/*     */     
/*  60 */     if (entityIn.field_70173_aa % 100 == 0) {
/*  61 */       List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(stack);
/*  62 */       if (!list.contains(EnumInfusionEnchantment.ARCING)) {
/*  63 */         EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.ARCING, 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/*  71 */     super.onUsingTick(stack, player, count);
/*  72 */     int ticks = func_77626_a(stack) - count;
/*  73 */     if (player.field_70181_x < 0.0D) {
/*  74 */       player.field_70181_x /= 1.2000000476837158D;
/*  75 */       player.field_70143_R /= 1.2F;
/*     */     }
/*     */     
/*     */ 
/*  79 */     player.field_70181_x += 0.07999999821186066D;
/*  80 */     if (player.field_70181_x > 0.5D) player.field_70181_x = 0.20000000298023224D;
/*  81 */     if ((player instanceof EntityPlayerMP)) {
/*  82 */       EntityUtils.resetFloatCounter((EntityPlayerMP)player);
/*     */     }
/*     */     
/*  85 */     List targets = player.field_70170_p.func_72839_b(player, player.func_174813_aQ().func_72314_b(2.5D, 2.5D, 2.5D));
/*     */     
/*  87 */     if (targets.size() > 0) {
/*  88 */       for (int var9 = 0; var9 < targets.size(); var9++)
/*     */       {
/*  90 */         Entity entity = (Entity)targets.get(var9);
/*  91 */         if ((!(entity instanceof EntityPlayer)) && ((entity instanceof EntityLivingBase)) && 
/*  92 */           (!entity.field_70128_L) && (
/*  93 */           (player.field_70154_o == null) || (player.field_70154_o != entity)))
/*     */         {
/*  95 */           Vec3 p = new Vec3(player.field_70165_t, player.field_70163_u, player.field_70161_v);
/*  96 */           Vec3 t = new Vec3(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*  97 */           double distance = p.func_72438_d(t) + 0.1D;
/*     */           
/*  99 */           Vec3 r = new Vec3(t.field_72450_a - p.field_72450_a, t.field_72448_b - p.field_72448_b, t.field_72449_c - p.field_72449_c);
/*     */           
/* 101 */           entity.field_70159_w += r.field_72450_a / 2.5D / distance;
/* 102 */           entity.field_70181_x += r.field_72448_b / 2.5D / distance;
/* 103 */           entity.field_70179_y += r.field_72449_c / 2.5D / distance;
/*     */         }
/*     */       }
/*     */     }
/* 107 */     if (player.field_70170_p.field_72995_K) {
/* 108 */       int miny = (int)(player.func_174813_aQ().field_72338_b - 2.0D);
/* 109 */       if (player.field_70122_E) miny = MathHelper.func_76128_c(player.func_174813_aQ().field_72338_b);
/* 110 */       for (int a = 0; a < 5; a++) {
/* 111 */         Thaumcraft.proxy.getFX().smokeSpiral(player.field_70165_t, player.func_174813_aQ().field_72338_b + player.field_70131_O / 2.0F, player.field_70161_v, 1.5F, player.field_70170_p.field_73012_v.nextInt(360), miny, 14540253);
/*     */       }
/*     */       
/*     */ 
/* 115 */       if (player.field_70122_E) {
/* 116 */         float r1 = player.field_70170_p.field_73012_v.nextFloat() * 360.0F;
/* 117 */         float mx = -MathHelper.func_76126_a(r1 / 180.0F * 3.1415927F) / 5.0F;
/* 118 */         float mz = MathHelper.func_76134_b(r1 / 180.0F * 3.1415927F) / 5.0F;
/* 119 */         player.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, player.field_70165_t, player.func_174813_aQ().field_72338_b + 0.10000000149011612D, player.field_70161_v, mx, 0.0D, mz, new int[0]);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 124 */     else if ((ticks == 0) || (ticks % 20 == 0)) {
/* 125 */       player.field_70170_p.func_72956_a(player, "thaumcraft:wind", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     }
/*     */     
/*     */ 
/* 129 */     if (ticks % 20 == 0) stack.func_77972_a(1, player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemElementalSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */