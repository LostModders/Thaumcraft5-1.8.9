/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraftforge.common.ISpecialArmor;
/*     */ import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
/*     */ import net.minecraftforge.common.util.EnumHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IGoggles;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ import thaumcraft.api.items.IRunicArmor;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.renderers.models.gear.ModelFortressArmor;
/*     */ 
/*     */ public class ItemFortressArmor extends ItemArmor implements IRepairable, IRunicArmor, ISpecialArmor, IGoggles, thaumcraft.api.items.IRevealer
/*     */ {
/*  30 */   public static ItemArmor.ArmorMaterial ARMORMAT_FORTRESS = EnumHelper.addArmorMaterial("FORTRESS", "FORTRESS", 40, new int[] { 3, 7, 6, 3 }, 25);
/*     */   
/*     */ 
/*     */   public ItemFortressArmor(ItemArmor.ArmorMaterial material, int renderIndex, int armorType)
/*     */   {
/*  35 */     super(material, renderIndex, armorType);
/*     */   }
/*     */   
/*     */ 
/*  39 */   ModelBiped model1 = null;
/*  40 */   ModelBiped model2 = null;
/*  41 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/*  48 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*  49 */     if (this.model1 == null) {
/*  50 */       this.model1 = new ModelFortressArmor(1.0F);
/*     */     }
/*  52 */     if (this.model2 == null) {
/*  53 */       this.model2 = new ModelFortressArmor(0.5F);
/*     */     }
/*  55 */     if ((type == 1) || (type == 3)) {
/*  56 */       this.model = this.model1;
/*     */     } else {
/*  58 */       this.model = this.model2;
/*     */     }
/*     */     
/*  61 */     if (this.model != null) {
/*  62 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/*  63 */       this.model.field_178720_f.field_78806_j = (armorSlot == 0);
/*  64 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/*  65 */       this.model.field_178723_h.field_78806_j = (armorSlot == 1);
/*  66 */       this.model.field_178724_i.field_78806_j = (armorSlot == 1);
/*  67 */       this.model.field_178721_j.field_78806_j = (armorSlot == 2);
/*  68 */       this.model.field_178722_k.field_78806_j = (armorSlot == 2);
/*  69 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/*  71 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/*  72 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/*  73 */       this.model.field_78118_o = false;
/*  74 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/*  75 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/*  77 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/*  79 */           EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/*  81 */           if (enumaction == EnumAction.BLOCK)
/*     */           {
/*  83 */             this.model.field_78120_m = 3;
/*     */           }
/*  85 */           else if (enumaction == EnumAction.BOW)
/*     */           {
/*  87 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  94 */     return this.model;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/* 100 */     return "thaumcraft:textures/models/armor/fortress_armor.png";
/*     */   }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 105 */     return EnumRarity.RARE;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 110 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("goggles"))) {
/* 111 */       list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.goggles.name"));
/*     */     }
/*     */     
/* 114 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("mask"))) {
/* 115 */       list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a(new StringBuilder().append("item.fortress_helm.mask.").append(stack.func_77978_p().func_74762_e("mask")).toString()));
/*     */     }
/*     */     
/*     */ 
/* 119 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/* 125 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/* 134 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
/*     */   {
/* 143 */     int priority = 0;
/* 144 */     double ratio = this.field_77879_b / 25.0D;
/*     */     
/* 146 */     if (source.func_82725_o() == true) {
/* 147 */       priority = 1;
/* 148 */       ratio = this.field_77879_b / 35.0D;
/*     */     }
/* 150 */     else if ((source.func_76347_k() == true) || (source.func_94541_c())) {
/* 151 */       priority = 1;
/* 152 */       ratio = this.field_77879_b / 20.0D;
/* 153 */     } else if (source.func_76363_c()) {
/* 154 */       priority = 0;
/* 155 */       ratio = 0.0D;
/*     */     }
/*     */     
/* 158 */     if ((player instanceof EntityPlayer)) {
/* 159 */       double set = 0.875D;
/* 160 */       for (int a = 1; a < 4; a++) {
/* 161 */         ItemStack piece = ((EntityPlayer)player).field_71071_by.field_70460_b[a];
/* 162 */         if ((piece != null) && ((piece.func_77973_b() instanceof ItemFortressArmor))) {
/* 163 */           set += 0.125D;
/* 164 */           if ((piece.func_77942_o()) && (piece.func_77978_p().func_74764_b("mask"))) {
/* 165 */             set += 0.05D;
/*     */           }
/*     */         }
/*     */       }
/* 169 */       ratio *= set;
/*     */     }
/*     */     
/* 172 */     return new ISpecialArmor.ArmorProperties(priority, ratio, armor.func_77958_k() + 1 - armor.func_77952_i());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
/*     */   {
/* 179 */     return this.field_77879_b;
/*     */   }
/*     */   
/*     */   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
/*     */   {
/* 184 */     if (source != DamageSource.field_76379_h) {
/* 185 */       stack.func_77972_a(damage, entity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 192 */     return (itemstack.func_77942_o()) && (itemstack.func_77978_p().func_74764_b("goggles"));
/*     */   }
/*     */   
/*     */   public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 197 */     return (itemstack.func_77942_o()) && (itemstack.func_77978_p().func_74764_b("goggles"));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemFortressArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */