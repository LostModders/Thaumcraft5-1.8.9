/*     */ package thaumcraft.common.items.baubles;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import java.util.List;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.FoodStats;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.items.IRechargable;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.events.PlayerEvents;
/*     */ 
/*     */ public class ItemRingVerdant extends Item implements IBauble, IRechargable
/*     */ {
/*     */   public ItemRingVerdant()
/*     */   {
/*  35 */     this.field_77777_bU = 1;
/*  36 */     this.canRepair = false;
/*  37 */     func_77656_e(200);
/*  38 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  48 */     return EnumRarity.RARE;
/*     */   }
/*     */   
/*     */   public BaubleType getBaubleType(ItemStack itemstack)
/*     */   {
/*  53 */     return BaubleType.RING;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  59 */     par3List.add(new ItemStack(this));
/*  60 */     ItemStack vhbl = new ItemStack(this);
/*  61 */     vhbl.func_77983_a("type", new NBTTagByte((byte)1));
/*  62 */     par3List.add(vhbl);
/*  63 */     ItemStack vhbl2 = new ItemStack(this);
/*  64 */     vhbl2.func_77983_a("type", new NBTTagByte((byte)2));
/*  65 */     par3List.add(vhbl2);
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  70 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74771_c("type") == 1)) {
/*  71 */       list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a("item.verdant.life.text"));
/*     */     }
/*  73 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74771_c("type") == 2)) {
/*  74 */       list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a("item.verdant.sustain.text"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/*  80 */     if ((!player.field_70170_p.field_72995_K) && (player.field_70173_aa % 20 == 0)) {
/*  81 */       if ((player.func_70660_b(Potion.field_82731_v) != null) && (itemstack.func_77952_i() + 20 <= itemstack.func_77958_k())) {
/*  82 */         itemstack.func_77972_a(20, player);
/*  83 */         player.func_82170_o(Potion.field_82731_v.func_76396_c());
/*  84 */         return;
/*     */       }
/*  86 */       if ((player.func_70660_b(Potion.field_76436_u) != null) && (itemstack.func_77952_i() + 10 <= itemstack.func_77958_k())) {
/*  87 */         itemstack.func_77972_a(10, player);
/*  88 */         player.func_82170_o(Potion.field_76436_u.func_76396_c());
/*  89 */         return;
/*     */       }
/*  91 */       if ((player.func_70660_b(PotionFluxTaint.instance) != null) && (itemstack.func_77952_i() + 5 <= itemstack.func_77958_k())) {
/*  92 */         itemstack.func_77972_a(5, player);
/*  93 */         player.func_82170_o(PotionFluxTaint.instance.func_76396_c());
/*  94 */         return;
/*     */       }
/*  96 */       if ((itemstack.func_77942_o()) && (itemstack.func_77978_p().func_74771_c("type") == 1) && (player.func_110143_aJ() < player.func_110138_aP()) && (itemstack.func_77952_i() + 5 <= itemstack.func_77958_k()))
/*     */       {
/*  98 */         itemstack.func_77972_a(5, player);
/*  99 */         player.func_70691_i(1.0F);
/* 100 */         return;
/*     */       }
/* 102 */       if ((itemstack.func_77942_o()) && (itemstack.func_77978_p().func_74771_c("type") == 2) && (itemstack.func_77952_i() + 1 <= itemstack.func_77958_k())) {
/* 103 */         if (player.func_70086_ai() < 100) {
/* 104 */           itemstack.func_77972_a(1, player);
/* 105 */           player.func_70050_g(300);
/* 106 */           return;
/*     */         }
/* 108 */         if (((player instanceof EntityPlayer)) && (((EntityPlayer)player).func_71043_e(false))) {
/* 109 */           itemstack.func_77972_a(1, player);
/* 110 */           ((EntityPlayer)player).func_71024_bL().func_75122_a(1, 0.3F);
/* 111 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
/* 118 */     return true;
/*     */   }
/*     */   
/* 121 */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) { return true; }
/*     */   
/*     */   public Aspect handleRecharge(World world, ItemStack is, BlockPos pos, EntityPlayer player, int amount)
/*     */   {
/* 125 */     if ((player == null) || ((!AuraHandler.shouldPreserveAura(world, player, pos, Aspect.EARTH)) && (!AuraHandler.shouldPreserveAura(world, player, pos, Aspect.WATER))))
/*     */     {
/* 127 */       amount = Math.min(amount, is.func_77952_i());
/* 128 */       int q = Math.round((AuraHelper.drainAuraAvailable(world, pos, Aspect.EARTH, amount) + AuraHelper.drainAuraAvailable(world, pos, Aspect.WATER, amount)) / 2.0F);
/* 129 */       is.func_77964_b(is.func_77952_i() - q);
/* 130 */       return q > 0 ? Aspect.LIFE : null;
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspectsInChargable(ItemStack is)
/*     */   {
/* 138 */     return new AspectList().add(Aspect.LIFE, is.func_77958_k() - is.func_77952_i());
/*     */   }
/*     */   
/*     */   public float getChargeLevel(ItemStack is)
/*     */   {
/* 143 */     return 1.0F - is.func_77952_i() / is.func_77958_k();
/*     */   }
/*     */   
/*     */ 
/*     */   public void onEquipped(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 149 */     PlayerEvents.markRunicDirty(player);
/*     */   }
/*     */   
/*     */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 154 */     PlayerEvents.markRunicDirty(player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/baubles/ItemRingVerdant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */