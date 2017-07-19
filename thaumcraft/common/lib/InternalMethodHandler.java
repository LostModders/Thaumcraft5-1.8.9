/*     */ package thaumcraft.common.lib;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.api.internal.EnumWarpType;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategoryList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.api.research.ResearchPage;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.construct.golem.seals.ItemSealPlacer;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealHandler;
/*     */ import thaumcraft.common.entities.construct.golem.tasks.TaskHandler;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.events.PlayerEvents;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXPollute;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class InternalMethodHandler implements thaumcraft.api.internal.IInternalMethodHandler
/*     */ {
/*     */   public boolean isResearchComplete(String username, String researchkey)
/*     */   {
/*  46 */     return ResearchManager.isResearchComplete(username, researchkey);
/*     */   }
/*     */   
/*     */   public AspectList getObjectAspects(ItemStack is)
/*     */   {
/*  51 */     return ThaumcraftCraftingManager.getObjectTags(is);
/*     */   }
/*     */   
/*     */   public AspectList generateTags(Item item, int meta)
/*     */   {
/*  56 */     return ThaumcraftCraftingManager.generateTags(item, meta);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting)
/*     */   {
/*  62 */     if ((wand.func_77973_b() instanceof IWand)) {
/*  63 */       return ((IWand)wand.func_77973_b()).consumeAllVis(wand, player, cost, doit, crafting);
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public boolean consumeVisFromInventory(EntityPlayer player, AspectList cost)
/*     */   {
/*  70 */     return WandManager.consumeVisFromInventory(player, cost);
/*     */   }
/*     */   
/*     */   public void addWarpToPlayer(EntityPlayer player, int amount, EnumWarpType type)
/*     */   {
/*  75 */     if ((amount == 0) || (player.field_70170_p.field_72995_K) || (Thaumcraft.proxy.getPlayerKnowledge() == null)) return;
/*  76 */     if ((amount < 0) && (getPlayerWarp(player, type) + amount < 0)) amount = getPlayerWarp(player, type);
/*  77 */     if (type == EnumWarpType.TEMPORARY) {
/*  78 */       Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(player.func_70005_c_(), amount);
/*  79 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)2), (EntityPlayerMP)player);
/*  80 */       PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)2, amount), (EntityPlayerMP)player);
/*     */     }
/*  82 */     if (type == EnumWarpType.PERMANENT) {
/*  83 */       Thaumcraft.proxy.getPlayerKnowledge().addWarpPerm(player.func_70005_c_(), amount);
/*  84 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)0), (EntityPlayerMP)player);
/*  85 */       PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)0, amount), (EntityPlayerMP)player);
/*     */     }
/*  87 */     if (type == EnumWarpType.NORMAL) {
/*  88 */       Thaumcraft.proxy.getPlayerKnowledge().addWarpSticky(player.func_70005_c_(), amount);
/*  89 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)1), (EntityPlayerMP)player);
/*  90 */       PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)1, amount), (EntityPlayerMP)player);
/*     */     }
/*     */     
/*  93 */     Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_()));
/*     */   }
/*     */   
/*     */ 
/*     */   public int getPlayerWarp(EntityPlayer player, EnumWarpType type)
/*     */   {
/*  99 */     if (type == EnumWarpType.TEMPORARY) return Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_());
/* 100 */     if (type == EnumWarpType.PERMANENT) return Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_());
/* 101 */     if (type == EnumWarpType.NORMAL) return Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
/* 102 */     return 0;
/*     */   }
/*     */   
/*     */   public void markRunicDirty(Entity entity)
/*     */   {
/* 107 */     PlayerEvents.markRunicDirty(entity);
/*     */   }
/*     */   
/*     */   public boolean completeResearch(EntityPlayer player, String researchkey)
/*     */   {
/* 112 */     if ((!ResearchManager.isResearchComplete(player.func_70005_c_(), researchkey)) && (ResearchManager.doesPlayerHaveRequisites(player.func_70005_c_(), researchkey))) {
/* 113 */       PacketHandler.INSTANCE.sendTo(new PacketResearchComplete(researchkey, (byte)Utils.setBit(0, 1)), (EntityPlayerMP)player);
/* 114 */       Thaumcraft.proxy.getResearchManager().completeResearch(player, researchkey, (byte)Utils.setBit(0, 1));
/* 115 */       if ((ResearchCategories.getResearch(researchkey) != null) && 
/* 116 */         (ResearchCategories.getResearch(researchkey).siblings != null)) {
/* 117 */         for (String sibling : ResearchCategories.getResearch(researchkey).siblings) {
/* 118 */           if ((!ResearchManager.isResearchComplete(player.func_70005_c_(), sibling)) && (ResearchManager.doesPlayerHaveRequisites(player.func_70005_c_(), sibling)))
/*     */           {
/* 120 */             PacketHandler.INSTANCE.sendTo(new PacketResearchComplete(sibling, (byte)Utils.setBit(0, 1)), (EntityPlayerMP)player);
/* 121 */             Thaumcraft.proxy.getResearchManager().completeResearch(player, sibling, (byte)Utils.setBit(0, 1));
/*     */           }
/*     */         }
/*     */       }
/* 125 */       for (String rc : ResearchCategories.researchCategories.keySet()) {
/* 126 */         for (ResearchItem ri : ResearchCategories.getResearchList(rc).research.values()) {
/* 127 */           if ((ri != null) && (ri.getPages() != null)) {
/* 128 */             for (ResearchPage page : ri.getPages())
/* 129 */               if ((page.research != null) && (page.research.equals(researchkey))) {
/* 130 */                 IChatComponent text = new net.minecraft.util.ChatComponentTranslation("tc.addpage", new Object[] { ri.getName() });
/* 131 */                 player.func_145747_a(text);
/* 132 */                 ResearchManager.setNewPageFlag(player.func_70005_c_(), ri.key);
/* 133 */                 PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncResearchFlags(player, ri.key), (EntityPlayerMP)player);
/* 134 */                 break;
/*     */               }
/*     */           }
/*     */         }
/*     */       }
/* 139 */       player.func_71023_q(5);
/* 140 */       return true;
/*     */     }
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   public boolean drainAura(World world, BlockPos pos, Aspect aspect, int amount)
/*     */   {
/* 147 */     return AuraHandler.drainAura(world, pos, aspect, amount);
/*     */   }
/*     */   
/*     */   public int drainAuraAvailable(World world, BlockPos pos, Aspect aspect, int amount)
/*     */   {
/* 152 */     return AuraHandler.drainAuraAvailable(world, pos, aspect, amount);
/*     */   }
/*     */   
/*     */   public void addAura(World world, BlockPos pos, Aspect aspect, int amount)
/*     */   {
/* 157 */     AuraHandler.addRechargeTicket(world, pos, aspect, amount);
/*     */   }
/*     */   
/*     */   public int getAura(World world, BlockPos pos, Aspect aspect)
/*     */   {
/* 162 */     return AuraHandler.getAuraCurrent(world, pos, aspect);
/*     */   }
/*     */   
/*     */   public int getAuraBase(World world, BlockPos pos)
/*     */   {
/* 167 */     return AuraHandler.getAuraBase(world, pos);
/*     */   }
/*     */   
/*     */   public void pollute(World world, BlockPos pos, int amount, boolean showEffect)
/*     */   {
/* 172 */     if (world.field_72995_K) return;
/* 173 */     AuraHandler.addRechargeTicket(world, pos, Aspect.FLUX, amount);
/* 174 */     if (showEffect) {
/* 175 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXPollute(pos, amount), new net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint(world.field_73011_w.func_177502_q(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void registerSeal(ISeal seal)
/*     */   {
/* 182 */     SealHandler.registerSeal(seal);
/*     */   }
/*     */   
/*     */   public ISeal getSeal(String key)
/*     */   {
/* 187 */     return SealHandler.getSeal(key);
/*     */   }
/*     */   
/*     */   public thaumcraft.api.golems.seals.ISealEntity getSealEntity(int dim, SealPos pos)
/*     */   {
/* 192 */     return SealHandler.getSealEntity(dim, pos);
/*     */   }
/*     */   
/*     */   public void addGolemTask(int dim, Task task)
/*     */   {
/* 197 */     TaskHandler.addTask(dim, task);
/*     */   }
/*     */   
/*     */   public boolean shouldPreserveAura(World world, EntityPlayer player, BlockPos pos, Aspect aspect)
/*     */   {
/* 202 */     return AuraHandler.shouldPreserveAura(world, player, pos, aspect);
/*     */   }
/*     */   
/*     */   public ItemStack getSealStack(String key)
/*     */   {
/* 207 */     return ItemSealPlacer.getSealStack(key);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/InternalMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */