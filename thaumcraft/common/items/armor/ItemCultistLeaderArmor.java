/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.common.util.EnumHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ import thaumcraft.api.items.IRunicArmor;
/*     */ import thaumcraft.client.renderers.models.gear.ModelLeaderArmor;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemCultistLeaderArmor extends ItemArmor implements IRepairable, IRunicArmor
/*     */ {
/*  23 */   public static ItemArmor.ArmorMaterial ARMORMAT_PREATOR = EnumHelper.addArmorMaterial("FORTRESS", "FORTRESS", 45, new int[] { 3, 8, 6, 3 }, 20);
/*     */   
/*     */   public ItemCultistLeaderArmor(int j, int k)
/*     */   {
/*  27 */     super(ARMORMAT_PREATOR, j, k);
/*  28 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  33 */     return "thaumcraft:textures/models/armor/cultist_leader_armor.png";
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  39 */     return EnumRarity.RARE;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  45 */     return par2ItemStack.func_77969_a(new ItemStack(Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  54 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*  58 */   ModelBiped model1 = null;
/*  59 */   ModelBiped model2 = null;
/*  60 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/*  67 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*     */     
/*  69 */     if (this.model1 == null) {
/*  70 */       this.model1 = new ModelLeaderArmor(1.0F);
/*     */     }
/*  72 */     if (this.model2 == null) {
/*  73 */       this.model2 = new ModelLeaderArmor(0.5F);
/*     */     }
/*  75 */     if ((type == 1) || (type == 3)) {
/*  76 */       this.model = this.model1;
/*     */     } else {
/*  78 */       this.model = this.model2;
/*     */     }
/*     */     
/*  81 */     if (this.model != null) {
/*  82 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/*  83 */       this.model.field_178720_f.field_78806_j = (armorSlot == 0);
/*  84 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/*  85 */       this.model.field_178723_h.field_78806_j = (armorSlot == 1);
/*  86 */       this.model.field_178724_i.field_78806_j = (armorSlot == 1);
/*  87 */       this.model.field_178721_j.field_78806_j = (armorSlot == 2);
/*  88 */       this.model.field_178722_k.field_78806_j = (armorSlot == 2);
/*  89 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/*  91 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/*  92 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/*  93 */       this.model.field_78118_o = false;
/*  94 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/*  95 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/*  97 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/*  99 */           EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/* 101 */           if (enumaction == EnumAction.BLOCK)
/*     */           {
/* 103 */             this.model.field_78120_m = 3;
/*     */           }
/* 105 */           else if (enumaction == EnumAction.BOW)
/*     */           {
/* 107 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 114 */     return this.model;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemCultistLeaderArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */