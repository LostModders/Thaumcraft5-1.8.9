/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.block.BlockCauldron;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ 
/*     */ public class ItemRobeArmor extends ItemArmor implements thaumcraft.api.items.IRepairable, thaumcraft.api.items.IVisDiscountGear, thaumcraft.api.items.IRunicArmor
/*     */ {
/*     */   public ItemRobeArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  22 */     super(enumarmormaterial, j, k);
/*     */   }
/*     */   
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  27 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82816_b_(ItemStack par1ItemStack)
/*     */   {
/*  33 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_82814_b(ItemStack par1ItemStack)
/*     */   {
/*  39 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/*  41 */     if (nbttagcompound == null)
/*     */     {
/*  43 */       return 6961280;
/*     */     }
/*     */     
/*     */ 
/*  47 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*  48 */     return nbttagcompound1.func_74764_b("color") ? nbttagcompound1.func_74762_e("color") : nbttagcompound1 == null ? 6961280 : 6961280;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82815_c(ItemStack par1ItemStack)
/*     */   {
/*  55 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/*  57 */     if (nbttagcompound != null)
/*     */     {
/*  59 */       NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */       
/*  61 */       if (nbttagcompound1.func_74764_b("color"))
/*     */       {
/*  63 */         nbttagcompound1.func_82580_o("color");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82813_b(ItemStack par1ItemStack, int par2)
/*     */   {
/*  72 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/*  74 */     if (nbttagcompound == null)
/*     */     {
/*  76 */       nbttagcompound = new NBTTagCompound();
/*  77 */       par1ItemStack.func_77982_d(nbttagcompound);
/*     */     }
/*     */     
/*  80 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */     
/*  82 */     if (!nbttagcompound.func_74764_b("display"))
/*     */     {
/*  84 */       nbttagcompound.func_74782_a("display", nbttagcompound1);
/*     */     }
/*     */     
/*  87 */     nbttagcompound1.func_74768_a("color", par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  94 */     if ((stack.func_77973_b() == ItemsTC.clothChest) || (stack.func_77973_b() == ItemsTC.clothBoots))
/*     */     {
/*  96 */       return type == null ? "thaumcraft:textures/models/armor/robes_1.png" : "thaumcraft:textures/models/armor/robes_1_overlay.png";
/*     */     }
/*  98 */     if (stack.func_77973_b() == ItemsTC.clothLegs) {
/*  99 */       return type == null ? "thaumcraft:textures/models/armor/robes_2.png" : "thaumcraft:textures/models/armor/robes_2_overlay.png";
/*     */     }
/* 101 */     return type == null ? "thaumcraft:textures/models/armor/robes_1.png" : "thaumcraft:textures/models/armor/robes_1_overlay.png";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 108 */     return EnumRarity.UNCOMMON;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/* 114 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.fabric)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*     */   {
/* 120 */     return this.field_77881_a == 3 ? 1 : 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 129 */     IBlockState bs = world.func_180495_p(pos);
/* 130 */     if (bs.func_177230_c() == net.minecraft.init.Blocks.field_150383_bp) {
/* 131 */       int i = ((Integer)bs.func_177229_b(BlockCauldron.field_176591_a)).intValue();
/* 132 */       if ((!world.field_72995_K) && (i > 0)) {
/* 133 */         func_82815_c(stack);
/* 134 */         net.minecraft.init.Blocks.field_150383_bp.func_176590_a(world, pos, bs, i - 1);
/* 135 */         return true;
/*     */       }
/*     */     }
/* 138 */     return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemRobeArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */