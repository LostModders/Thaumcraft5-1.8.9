/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.block.BlockCauldron;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ISpecialArmor;
/*     */ import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
/*     */ import net.minecraftforge.common.util.EnumHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.items.IGoggles;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ import thaumcraft.api.items.IRevealer;
/*     */ import thaumcraft.api.items.IRunicArmor;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.renderers.models.gear.ModelRobe;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemVoidRobeArmor extends ItemArmor implements IRepairable, IRunicArmor, thaumcraft.api.items.IVisDiscountGear, IGoggles, IRevealer, ISpecialArmor, thaumcraft.api.items.IWarpingGear
/*     */ {
/*  36 */   public static ItemArmor.ArmorMaterial ARMORMAT_VOIDROBE = EnumHelper.addArmorMaterial("VOIDROBE", "VOIDROBE", 18, new int[] { 4, 8, 7, 4 }, 10);
/*     */   
/*     */   public ItemVoidRobeArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  40 */     super(enumarmormaterial, j, k);
/*  41 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  48 */     return type == null ? "thaumcraft:textures/models/armor/void_robe_armor_overlay.png" : "thaumcraft:textures/models/armor/void_robe_armor.png";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  55 */     return EnumRarity.EPIC;
/*     */   }
/*     */   
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  60 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 1)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*     */   {
/*  67 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*     */     
/*  69 */     if ((!world.field_72995_K) && (stack.func_77951_h()) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/*  70 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack armor)
/*     */   {
/*  76 */     super.onArmorTick(world, player, armor);
/*  77 */     if ((!world.field_72995_K) && (armor.func_77952_i() > 0) && (player.field_70173_aa % 20 == 0)) {
/*  78 */       armor.func_77972_a(-1, player);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  86 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/*  91 */     int type = ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
/*  92 */     return type == 0;
/*     */   }
/*     */   
/*     */   public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/*  97 */     int type = ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
/*  98 */     return type == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*     */   {
/* 105 */     return 5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 110 */   ModelBiped model1 = null;
/* 111 */   ModelBiped model2 = null;
/* 112 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/* 120 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*     */     
/* 122 */     if (this.model1 == null) {
/* 123 */       this.model1 = new ModelRobe(1.0F);
/*     */     }
/* 125 */     if (this.model2 == null) {
/* 126 */       this.model2 = new ModelRobe(0.5F);
/*     */     }
/* 128 */     if ((type == 1) || (type == 3)) {
/* 129 */       this.model = this.model1;
/*     */     } else {
/* 131 */       this.model = this.model2;
/*     */     }
/*     */     
/* 134 */     if (this.model != null) {
/* 135 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/* 136 */       this.model.field_178720_f.field_78806_j = (armorSlot == 0);
/* 137 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/* 138 */       this.model.field_178723_h.field_78806_j = (armorSlot == 1);
/* 139 */       this.model.field_178724_i.field_78806_j = (armorSlot == 1);
/* 140 */       this.model.field_178721_j.field_78806_j = (armorSlot == 2);
/* 141 */       this.model.field_178722_k.field_78806_j = (armorSlot == 2);
/* 142 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/* 144 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/* 145 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/* 146 */       this.model.field_78118_o = false;
/* 147 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/* 148 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/* 150 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/* 152 */           EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/* 154 */           if (enumaction == EnumAction.BLOCK)
/*     */           {
/* 156 */             this.model.field_78120_m = 3;
/*     */           }
/* 158 */           else if (enumaction == EnumAction.BOW)
/*     */           {
/* 160 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 167 */     return this.model;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_82816_b_(ItemStack par1ItemStack)
/*     */   {
/* 176 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_82814_b(ItemStack par1ItemStack)
/*     */   {
/* 183 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/* 185 */     if (nbttagcompound == null)
/*     */     {
/* 187 */       return 6961280;
/*     */     }
/*     */     
/*     */ 
/* 191 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/* 192 */     return nbttagcompound1.func_74764_b("color") ? nbttagcompound1.func_74762_e("color") : nbttagcompound1 == null ? 6961280 : 6961280;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82815_c(ItemStack par1ItemStack)
/*     */   {
/* 199 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/* 201 */     if (nbttagcompound != null)
/*     */     {
/* 203 */       NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */       
/* 205 */       if (nbttagcompound1.func_74764_b("color"))
/*     */       {
/* 207 */         nbttagcompound1.func_82580_o("color");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82813_b(ItemStack par1ItemStack, int par2)
/*     */   {
/* 216 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/* 218 */     if (nbttagcompound == null)
/*     */     {
/* 220 */       nbttagcompound = new NBTTagCompound();
/* 221 */       par1ItemStack.func_77982_d(nbttagcompound);
/*     */     }
/*     */     
/* 224 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */     
/* 226 */     if (!nbttagcompound.func_74764_b("display"))
/*     */     {
/* 228 */       nbttagcompound.func_74782_a("display", nbttagcompound1);
/*     */     }
/*     */     
/* 231 */     nbttagcompound1.func_74768_a("color", par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
/*     */   {
/* 240 */     int priority = 0;
/* 241 */     double ratio = this.field_77879_b / 25.0D;
/*     */     
/* 243 */     if (source.func_82725_o()) {
/* 244 */       priority = 1;
/* 245 */       ratio = this.field_77879_b / 35.0D;
/* 246 */     } else if (source.func_76363_c()) {
/* 247 */       priority = 0;
/* 248 */       ratio = 0.0D;
/*     */     }
/* 250 */     return new ISpecialArmor.ArmorProperties(priority, ratio, armor.func_77958_k() + 1 - armor.func_77952_i());
/*     */   }
/*     */   
/*     */   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
/*     */   {
/* 255 */     return this.field_77879_b;
/*     */   }
/*     */   
/*     */   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
/*     */   {
/* 260 */     if (source != DamageSource.field_76379_h) { stack.func_77972_a(damage, entity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 267 */     IBlockState bs = world.func_180495_p(pos);
/* 268 */     if (bs.func_177230_c() == Blocks.field_150383_bp) {
/* 269 */       int i = ((Integer)bs.func_177229_b(BlockCauldron.field_176591_a)).intValue();
/* 270 */       if ((!world.field_72995_K) && (i > 0)) {
/* 271 */         func_82815_c(stack);
/* 272 */         Blocks.field_150383_bp.func_176590_a(world, pos, bs, i - 1);
/* 273 */         return true;
/*     */       }
/*     */     }
/* 276 */     return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*     */   {
/* 282 */     return 2;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemVoidRobeArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */