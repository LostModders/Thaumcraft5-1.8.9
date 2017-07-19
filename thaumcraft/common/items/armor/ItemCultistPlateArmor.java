/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IRepairable;
/*     */ import thaumcraft.api.items.IRunicArmor;
/*     */ import thaumcraft.client.renderers.models.gear.ModelKnightArmor;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemCultistPlateArmor extends ItemArmor implements IRepairable, IRunicArmor
/*     */ {
/*     */   public ItemCultistPlateArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  23 */     super(enumarmormaterial, j, k);
/*  24 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  30 */     return (entity instanceof thaumcraft.common.entities.monster.EntityInhabitedZombie) ? "thaumcraft:textures/models/armor/zombie_plate_armor.png" : "thaumcraft:textures/models/armor/cultist_plate_armor.png";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  38 */     return EnumRarity.UNCOMMON;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  44 */     return par2ItemStack.func_77969_a(new ItemStack(net.minecraft.init.Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  53 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*  57 */   ModelBiped model1 = null;
/*  58 */   ModelBiped model2 = null;
/*  59 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/*  66 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*     */     
/*  68 */     if (this.model1 == null) {
/*  69 */       this.model1 = new ModelKnightArmor(1.0F);
/*     */     }
/*  71 */     if (this.model2 == null) {
/*  72 */       this.model2 = new ModelKnightArmor(0.5F);
/*     */     }
/*  74 */     if ((type == 1) || (type == 3)) {
/*  75 */       this.model = this.model1;
/*     */     } else {
/*  77 */       this.model = this.model2;
/*     */     }
/*     */     
/*  80 */     if (this.model != null) {
/*  81 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/*  82 */       this.model.field_178720_f.field_78806_j = (armorSlot == 0);
/*  83 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/*  84 */       this.model.field_178723_h.field_78806_j = (armorSlot == 1);
/*  85 */       this.model.field_178724_i.field_78806_j = (armorSlot == 1);
/*  86 */       this.model.field_178721_j.field_78806_j = (armorSlot == 2);
/*  87 */       this.model.field_178722_k.field_78806_j = (armorSlot == 2);
/*  88 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/*  90 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/*  91 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/*  92 */       this.model.field_78118_o = false;
/*  93 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/*  94 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/*  96 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/*  98 */           EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/* 100 */           if (enumaction == EnumAction.BLOCK)
/*     */           {
/* 102 */             this.model.field_78120_m = 3;
/*     */           }
/* 104 */           else if (enumaction == EnumAction.BOW)
/*     */           {
/* 106 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 113 */     return this.model;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/ItemCultistPlateArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */