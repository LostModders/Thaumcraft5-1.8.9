/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagShort;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.baubles.ItemGirdleHover;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketFlyToServer;
/*     */ 
/*     */ public class Hover
/*     */ {
/*     */   public static final int FUELMAX = 6000;
/*     */   public static final int EFFICIENCY = 20;
/*  29 */   static HashMap<Integer, Boolean> hovering = new HashMap();
/*     */   
/*  31 */   static HashMap<Integer, Long> timing = new HashMap();
/*     */   
/*     */   public static boolean toggleHover(EntityPlayer player, int playerId, ItemStack armor) {
/*  34 */     boolean hover = hovering.get(Integer.valueOf(playerId)) == null ? false : ((Boolean)hovering.get(Integer.valueOf(playerId))).booleanValue();
/*  35 */     short fuel = 0;
/*     */     
/*  37 */     if ((armor.func_77942_o()) && (armor.func_77978_p().func_74764_b("fuel"))) {
/*  38 */       fuel = armor.func_77978_p().func_74765_d("fuel");
/*     */     }
/*     */     
/*  41 */     if ((!hover) && (fuel <= 0)) { return false;
/*     */     }
/*  43 */     hovering.put(Integer.valueOf(playerId), Boolean.valueOf(!hover));
/*  44 */     if (player.field_70170_p.field_72995_K) {
/*  45 */       PacketHandler.INSTANCE.sendToServer(new PacketFlyToServer(player, !hover));
/*  46 */       player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, !hover ? "thaumcraft:hhon" : "thaumcraft:hhoff", 0.33F, 1.0F, false);
/*     */     }
/*     */     
/*  49 */     return !hover;
/*     */   }
/*     */   
/*     */   public static void setHover(int playerId, boolean hover) {
/*  53 */     hovering.put(Integer.valueOf(playerId), Boolean.valueOf(hover));
/*     */   }
/*     */   
/*     */   public static boolean getHover(int playerId) {
/*  57 */     return hovering.containsKey(Integer.valueOf(playerId)) ? ((Boolean)hovering.get(Integer.valueOf(playerId))).booleanValue() : false;
/*     */   }
/*     */   
/*     */   public static void handleHoverArmor(EntityPlayer player, ItemStack armor)
/*     */   {
/*  62 */     if ((hovering.get(Integer.valueOf(player.func_145782_y())) == null) && (armor.func_77942_o()) && (armor.func_77978_p().func_74764_b("hover")))
/*     */     {
/*  64 */       hovering.put(Integer.valueOf(player.func_145782_y()), Boolean.valueOf(armor.func_77978_p().func_74771_c("hover") == 1));
/*  65 */       if (player.field_70170_p.field_72995_K) {
/*  66 */         PacketHandler.INSTANCE.sendToServer(new PacketFlyToServer(player, armor.func_77978_p().func_74771_c("hover") == 1));
/*     */       }
/*     */     }
/*     */     
/*  70 */     boolean hover = hovering.get(Integer.valueOf(player.func_145782_y())) == null ? false : ((Boolean)hovering.get(Integer.valueOf(player.func_145782_y()))).booleanValue();
/*  71 */     World world = player.field_70170_p;
/*     */     
/*  73 */     player.field_71075_bZ.field_75100_b = hover;
/*     */     
/*  75 */     short fuel = 0;
/*  76 */     if ((armor.func_77942_o()) && (armor.func_77978_p().func_74764_b("fuel"))) {
/*  77 */       fuel = armor.func_77978_p().func_74765_d("fuel");
/*     */     }
/*     */     
/*  80 */     if ((world.field_72995_K) && ((player instanceof EntityPlayerSP)))
/*     */     {
/*  82 */       if ((hover) && (expendCharge(player, armor, fuel)))
/*     */       {
/*  84 */         long currenttime = System.currentTimeMillis();
/*  85 */         long time = 0L;
/*  86 */         if (timing.get(Integer.valueOf(player.func_145782_y())) != null) { time = ((Long)timing.get(Integer.valueOf(player.func_145782_y()))).longValue();
/*     */         }
/*  88 */         if (time < currenttime) {
/*  89 */           time = currenttime + (player.func_70051_ag() ? 'Ϩ' : 'Ұ');
/*     */           
/*  91 */           timing.put(Integer.valueOf(player.func_145782_y()), Long.valueOf(time));
/*     */           
/*  93 */           player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:jacobs", player.func_70051_ag() ? 0.1F : 0.05F, 1.0F + player.field_70170_p.field_73012_v.nextFloat() * 0.05F + (player.func_70051_ag() ? 0.1F : 0.0F), false);
/*     */         }
/*     */         
/*     */ 
/*  97 */         int haste = EnchantmentHelper.func_77506_a(Config.enchHaste.field_77352_x, armor);
/*  98 */         float mod = 0.825F + 0.075F * haste;
/*     */         
/* 100 */         if ((BaublesApi.getBaubles(player).func_70301_a(3) != null) && ((BaublesApi.getBaubles(player).func_70301_a(3).func_77973_b() instanceof ItemGirdleHover)))
/*     */         {
/* 102 */           mod += 0.175F;
/*     */         }
/*     */         
/* 105 */         if (mod > 1.0F) { mod = 1.0F;
/*     */         }
/* 107 */         player.field_70159_w *= mod;
/* 108 */         player.field_70179_y *= mod;
/*     */ 
/*     */       }
/* 111 */       else if (hover) { toggleHover(player, player.func_145782_y(), armor);
/*     */       }
/*     */     }
/*     */     else {
/* 115 */       if ((armor.func_77942_o()) && (!armor.func_77978_p().func_74764_b("hover"))) {
/* 116 */         armor.func_77983_a("hover", new NBTTagByte((byte)(hover ? 1 : 0)));
/*     */       }
/* 118 */       if ((hover) && (expendCharge(player, armor, fuel))) {
/* 119 */         if ((player instanceof EntityPlayerMP)) {
/* 120 */           resetFloatCounter((EntityPlayerMP)player);
/*     */         }
/* 122 */         player.field_70143_R = 0.0F;
/* 123 */         if ((armor.func_77942_o()) && (armor.func_77978_p().func_74764_b("hover")) && (armor.func_77978_p().func_74771_c("hover") != 1))
/*     */         {
/* 125 */           armor.func_77983_a("hover", new NBTTagByte((byte)(hover ? 1 : 0)));
/*     */         }
/*     */       } else {
/* 128 */         if (hover) toggleHover(player, player.func_145782_y(), armor);
/* 129 */         player.field_70143_R *= 0.75F;
/* 130 */         if ((armor.func_77942_o()) && (armor.func_77978_p().func_74764_b("hover")) && (armor.func_77978_p().func_74771_c("hover") == 1))
/*     */         {
/* 132 */           armor.func_77983_a("hover", new NBTTagByte((byte)(hover ? 1 : 0)));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean expendCharge(EntityPlayer player, ItemStack armor, short fuel)
/*     */   {
/* 140 */     if (fuel > 0) {
/* 141 */       fuel = (short)(fuel - 1);
/* 142 */       if (player.func_70051_ag()) {
/* 143 */         fuel = (short)(fuel - 2);
/*     */       }
/* 145 */       if (!player.field_70170_p.field_72995_K) {
/* 146 */         armor.func_77983_a("fuel", new NBTTagShort(fuel));
/*     */       }
/* 148 */       return true;
/*     */     }
/*     */     
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public static void resetFloatCounter(EntityPlayerMP player) {
/*     */     try {
/* 156 */       ObfuscationReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, player.field_71135_a, Integer.valueOf(0), new String[] { "floatingTickCount", "g", "field_147365_f" });
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/armor/Hover.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */