/*     */ package thaumcraft.common.items.baubles;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.BaublesApi;
/*     */ import baubles.api.IBauble;
/*     */ import java.util.List;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IRechargable;
/*     */ import thaumcraft.api.items.IRunicArmor;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.IVariantItems;
/*     */ import thaumcraft.common.lib.events.PlayerEvents;
/*     */ 
/*     */ public class ItemAmuletVis extends Item implements IBauble, IRunicArmor, IVariantItems
/*     */ {
/*     */   public ItemAmuletVis()
/*     */   {
/*  31 */     this.field_77777_bU = 1;
/*  32 */     this.canRepair = false;
/*  33 */     func_77656_e(0);
/*  34 */     func_77637_a(Thaumcraft.tabTC);
/*  35 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */   public String[] getVariantNames()
/*     */   {
/*  40 */     return new String[] { "found", "crafted" };
/*     */   }
/*     */   
/*     */   public int[] getVariantMeta()
/*     */   {
/*  45 */     return new int[] { 0, 1 };
/*     */   }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  50 */     return itemstack.func_77952_i() == 0 ? EnumRarity.UNCOMMON : EnumRarity.RARE;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack stack)
/*     */   {
/*  56 */     return super.func_77658_a() + "." + stack.func_77952_i();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  62 */     par3List.add(new ItemStack(this, 1, 0));
/*  63 */     par3List.add(new ItemStack(this, 1, 1));
/*     */   }
/*     */   
/*     */   public BaubleType getBaubleType(ItemStack itemstack)
/*     */   {
/*  68 */     return BaubleType.AMULET;
/*     */   }
/*     */   
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/*  73 */     if (((player instanceof EntityPlayer)) && (!player.field_70170_p.field_72995_K)) if (player.field_70173_aa % (itemstack.func_77952_i() == 0 ? 40 : 5) == 0)
/*     */       {
/*  75 */         ItemStack[] inv = ((EntityPlayer)player).field_71071_by.field_70462_a;
/*  76 */         for (int a = 0; a < InventoryPlayer.func_70451_h(); a++) {
/*  77 */           if ((inv[a] != null) && ((inv[a].func_77973_b() instanceof IRechargable))) {
/*  78 */             IRechargable chargable = (IRechargable)inv[a].func_77973_b();
/*  79 */             if (chargable.handleRecharge(player.field_70170_p, inv[a], new BlockPos(player), (EntityPlayer)player, 1) != null) { return;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*  84 */         IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
/*  85 */         for (int a = 0; a < 4; a++) {
/*  86 */           if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof IRechargable))) {
/*  87 */             IRechargable chargable = (IRechargable)baubles.func_70301_a(a).func_77973_b();
/*  88 */             if (chargable.handleRecharge(player.field_70170_p, baubles.func_70301_a(a), new BlockPos(player), (EntityPlayer)player, 1) != null) { return;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*  93 */         inv = ((EntityPlayer)player).field_71071_by.field_70460_b;
/*  94 */         for (int a = 0; a < inv.length; a++) {
/*  95 */           if ((inv[a] != null) && ((inv[a].func_77973_b() instanceof IRechargable))) {
/*  96 */             IRechargable chargable = (IRechargable)inv[a].func_77973_b();
/*  97 */             if (chargable.handleRecharge(player.field_70170_p, inv[a], new BlockPos(player), (EntityPlayer)player, 1) != null) { return;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */   }
/*     */   
/*     */   public void onEquipped(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 106 */     PlayerEvents.markRunicDirty(player);
/*     */   }
/*     */   
/*     */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 111 */     PlayerEvents.markRunicDirty(player);
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 116 */     if (stack.func_77952_i() == 0) {
/* 117 */       list.add(EnumChatFormatting.AQUA + StatCollector.func_74838_a("item.amulet_vis.text"));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   public int getRunicCharge(ItemStack itemstack) {
/* 132 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/baubles/ItemAmuletVis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */