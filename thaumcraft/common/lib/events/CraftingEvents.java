/*    */ package thaumcraft.common.lib.events;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*    */ import net.minecraftforge.fml.common.IFuelHandler;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.internal.EnumWarpType;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.items.resources.ItemPhial;
/*    */ import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class CraftingEvents implements IFuelHandler
/*    */ {
/*    */   public int getBurnTime(ItemStack fuel)
/*    */   {
/* 29 */     if (fuel.func_77969_a(new ItemStack(ItemsTC.alumentum))) return 6400;
/* 30 */     if (fuel.func_77969_a(new ItemStack(BlocksTC.log))) return 400;
/* 31 */     return 0;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onCrafting(PlayerEvent.ItemCraftedEvent event)
/*    */   {
/* 37 */     int warp = thaumcraft.api.ThaumcraftApi.getWarp(event.crafting);
/* 38 */     if ((!Config.wuss) && (warp > 0) && 
/* 39 */       (!event.player.field_70170_p.field_72995_K)) {
/* 40 */       thaumcraft.api.research.ResearchHelper.addWarpToPlayer(event.player, warp, EnumWarpType.NORMAL);
/*    */     }
/*    */     
/*    */ 
/* 44 */     if ((event.crafting.func_77973_b() == ItemsTC.label) && (event.crafting.func_77942_o())) {
/* 45 */       for (int var2 = 0; var2 < 9; var2++)
/*    */       {
/* 47 */         ItemStack var3 = event.craftMatrix.func_70301_a(var2);
/* 48 */         if ((var3 != null) && ((var3.func_77973_b() instanceof ItemPhial)))
/*    */         {
/* 50 */           var3.field_77994_a += 1;
/* 51 */           event.craftMatrix.func_70299_a(var2, var3);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SubscribeEvent
/*    */   public void harvestEvent(BlockEvent.HarvestDropsEvent event)
/*    */   {
/* 65 */     EntityPlayer player = event.harvester;
/* 66 */     if ((event.drops == null) || (event.drops.size() == 0) || (player == null)) { return;
/*    */     }
/* 68 */     ItemStack held = player.field_71071_by.func_70448_g();
/*    */     
/* 70 */     if ((held != null) && ((held.func_77973_b() instanceof IWand)) && (((IWand)held.func_77973_b()).getFocus(held) != null) && (((IWand)held.func_77973_b()).getFocus(held).isUpgradedWith(((IWand)held.func_77973_b()).getFocusStack(held), ItemFocusExcavation.dowsing)))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 77 */       int fortune = 0;
/* 78 */       if ((held.func_77973_b() instanceof IWand)) {
/* 79 */         fortune = ((IWand)held.func_77973_b()).getFocus(held).getUpgradeLevel(((IWand)held.func_77973_b()).getFocusStack(held), thaumcraft.api.wands.FocusUpgradeType.treasure);
/*    */       }
/*    */       
/*    */ 
/* 83 */       float chance = 0.2F + fortune * 0.075F;
/*    */       
/* 85 */       for (int a = 0; a < event.drops.size(); a++)
/*    */       {
/* 87 */         ItemStack is = (ItemStack)event.drops.get(a);
/* 88 */         ItemStack smr = Utils.findSpecialMiningResult(is, chance, event.world.field_73012_v);
/* 89 */         if (!is.func_77969_a(smr)) {
/* 90 */           event.drops.set(a, smr);
/* 91 */           if (!event.world.field_72995_K) {
/* 92 */             event.world.func_72908_a(event.pos.func_177958_n() + 0.5F, event.pos.func_177956_o() + 0.5F, event.pos.func_177952_p() + 0.5F, "random.orb", 0.2F, 0.7F + event.world.field_73012_v.nextFloat() * 0.2F);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/CraftingEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */