/*     */ package thaumcraft.common;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.fml.common.network.IGuiHandler;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.lib.RenderEventHandler;
/*     */ import thaumcraft.common.container.ContainerArcaneBore;
/*     */ import thaumcraft.common.container.ContainerArcaneWorkbench;
/*     */ import thaumcraft.common.container.ContainerAuraTotem;
/*     */ import thaumcraft.common.container.ContainerFocalManipulator;
/*     */ import thaumcraft.common.container.ContainerFocusPouch;
/*     */ import thaumcraft.common.container.ContainerGolemBuilder;
/*     */ import thaumcraft.common.container.ContainerHandMirror;
/*     */ import thaumcraft.common.container.ContainerPech;
/*     */ import thaumcraft.common.container.ContainerResearchTable;
/*     */ import thaumcraft.common.container.ContainerSmelter;
/*     */ import thaumcraft.common.container.ContainerSpa;
/*     */ import thaumcraft.common.container.ContainerThaumatorium;
/*     */ import thaumcraft.common.container.ContainerTurretAdvanced;
/*     */ import thaumcraft.common.container.ContainerTurretBasic;
/*     */ import thaumcraft.common.container.ContainerTurretFocus;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced;
/*     */ import thaumcraft.common.entities.construct.EntityTurretFocus;
/*     */ import thaumcraft.common.entities.construct.golem.ItemGolemBell;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.aura.AuraThread;
/*     */ import thaumcraft.common.lib.events.KeyHandler;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*     */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*     */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*     */ import thaumcraft.common.tiles.crafting.TileSmelter;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneBore;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotem;
/*     */ import thaumcraft.common.tiles.devices.TileSpa;
/*     */ import thaumcraft.common.tiles.devices.TileThaumatorium;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonProxy
/*     */   implements IGuiHandler
/*     */ {
/*     */   public PlayerKnowledge playerKnowledge;
/*     */   public ResearchManager researchManager;
/*  63 */   public WandManager wandManager = new WandManager();
/*     */   
/*     */   public PlayerKnowledge getPlayerKnowledge() {
/*  66 */     return this.playerKnowledge;
/*     */   }
/*     */   
/*     */   public ResearchManager getResearchManager() {
/*  70 */     return this.researchManager;
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getCompletedResearch() {
/*  74 */     return this.playerKnowledge.researchCompleted;
/*     */   }
/*     */   
/*     */   public Map<String, HashMap<String, Byte>> getCompletedResearchFlags() {
/*  78 */     return this.playerKnowledge.researchCompletedFlags;
/*     */   }
/*     */   
/*     */   public void registerBlockMesh(Block block, int metadata, String name) {}
/*     */   
/*     */   public void registerVariantName(Item item, String name) {}
/*     */   
/*     */   public void registerItemMesh(Item block, int metadata, String name) {}
/*     */   
/*     */   public void registerItemMesh(Item item, int metadata, String name, boolean variant) {}
/*     */   
/*     */   public void registerDisplayInformationInit() {}
/*     */   
/*  91 */   AuraThread auraThread = null;
/*     */   
/*     */   public AuraThread getAuraThread() {
/*  94 */     return this.auraThread; }
/*     */   
/*     */   public void setAuraThread(AuraThread t)
/*     */   {
/*  98 */     this.auraThread = t;
/*  99 */     Thread thread = new Thread(Thaumcraft.proxy.getAuraThread());
/* 100 */     thread.start();
/*     */   }
/*     */   
/*     */   public void registerHandlers() {}
/*     */   
/*     */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/* 107 */     return null;
/*     */   }
/*     */   
/*     */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/* 112 */     switch (ID) {
/* 113 */     case 1:  return new ContainerPech(player.field_71071_by, world, (EntityPech)((WorldServer)world).func_73045_a(x));
/* 114 */     case 15:  return new ContainerTurretFocus(player.field_71071_by, world, (EntityTurretFocus)((WorldServer)world).func_73045_a(x));
/* 115 */     case 16:  return new ContainerTurretBasic(player.field_71071_by, world, (EntityTurretCrossbow)((WorldServer)world).func_73045_a(x));
/* 116 */     case 17:  return new ContainerTurretAdvanced(player.field_71071_by, world, (EntityTurretCrossbowAdvanced)((WorldServer)world).func_73045_a(x));
/*     */     
/*     */     case 8: 
/* 119 */       return new ContainerAuraTotem(player.field_71071_by, (TileAuraTotem)world.func_175625_s(new BlockPos(x, y, z)));
/* 120 */     case 9:  return new ContainerSmelter(player.field_71071_by, (TileSmelter)world.func_175625_s(new BlockPos(x, y, z)));
/* 121 */     case 3:  return new ContainerThaumatorium(player.field_71071_by, (TileThaumatorium)world.func_175625_s(new BlockPos(x, y, z)));
/* 122 */     case 5:  return new ContainerFocusPouch(player.field_71071_by, world, x, y, z);
/* 123 */     case 10:  return new ContainerResearchTable(player.field_71071_by, (TileResearchTable)world.func_175625_s(new BlockPos(x, y, z)));
/* 124 */     case 13:  return new ContainerArcaneWorkbench(player.field_71071_by, (TileArcaneWorkbench)world.func_175625_s(new BlockPos(x, y, z)));
/* 125 */     case 14:  return new ContainerArcaneBore(player.field_71071_by, (TileArcaneBore)world.func_175625_s(new BlockPos(x, y, z)));
/* 126 */     case 4:  return new ContainerHandMirror(player.field_71071_by, world, x, y, z);
/* 127 */     case 6:  return new ContainerSpa(player.field_71071_by, (TileSpa)world.func_175625_s(new BlockPos(x, y, z)));
/* 128 */     case 7:  return new ContainerFocalManipulator(player.field_71071_by, (TileFocalManipulator)world.func_175625_s(new BlockPos(x, y, z)));
/* 129 */     case 19:  return new ContainerGolemBuilder(player.field_71071_by, (TileGolemBuilder)world.func_175625_s(new BlockPos(x, y, z)));
/*     */     case 18: 
/* 131 */       ISealEntity se = ItemGolemBell.getSeal(player);
/* 132 */       if (se != null) return se.getSeal().returnContainer(world, player, new BlockPos(x, y, z), se.getSealPos().face, se);
/*     */       break;
/*     */     }
/* 135 */     return null;
/*     */   }
/*     */   
/*     */   public World getClientWorld() {
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   public void registerKeyBindings() {}
/*     */   
/*     */   public boolean isShiftKeyDown()
/*     */   {
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public FXDispatcher getFX() {
/* 150 */     return null;
/*     */   }
/*     */   
/*     */   public RenderEventHandler getRenderEventHandler()
/*     */   {
/* 155 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void registerFromSubItems(Item item, String name) {}
/*     */   
/*     */ 
/*     */   public KeyHandler getKeyBindings()
/*     */   {
/* 165 */     return null;
/*     */   }
/*     */   
/*     */   public void registerDisplayInformationPreInit() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/CommonProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */