/*     */ package thaumcraft.client;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.model.ModelChicken;
/*     */ import net.minecraft.client.model.ModelCow;
/*     */ import net.minecraft.client.model.ModelPig;
/*     */ import net.minecraft.client.model.ModelRabbit;
/*     */ import net.minecraft.client.model.ModelSlime;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.BlockModelShapes;
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.ItemModelMesher;
/*     */ import net.minecraft.client.renderer.block.statemap.StateMapperBase;
/*     */ import net.minecraft.client.renderer.entity.RenderEntityItem;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.entity.RenderSnowball;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelBakery;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.model.ModelLoader;
/*     */ import net.minecraftforge.client.model.ModelLoaderRegistry;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventBus;
/*     */ import net.minecraftforge.fml.common.registry.VillagerRegistry;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.gui.GuiArcaneBore;
/*     */ import thaumcraft.client.gui.GuiArcaneWorkbench;
/*     */ import thaumcraft.client.gui.GuiAuraTotem;
/*     */ import thaumcraft.client.gui.GuiFocalManipulator;
/*     */ import thaumcraft.client.gui.GuiFocusPouch;
/*     */ import thaumcraft.client.gui.GuiGolemBuilder;
/*     */ import thaumcraft.client.gui.GuiHandMirror;
/*     */ import thaumcraft.client.gui.GuiPech;
/*     */ import thaumcraft.client.gui.GuiResearchBrowser;
/*     */ import thaumcraft.client.gui.GuiResearchTable;
/*     */ import thaumcraft.client.gui.GuiSmelter;
/*     */ import thaumcraft.client.gui.GuiSpa;
/*     */ import thaumcraft.client.gui.GuiThaumatorium;
/*     */ import thaumcraft.client.gui.GuiTurretAdvanced;
/*     */ import thaumcraft.client.gui.GuiTurretBasic;
/*     */ import thaumcraft.client.gui.GuiTurretFocus;
/*     */ import thaumcraft.client.lib.ModelHelper;
/*     */ import thaumcraft.client.lib.RenderEventHandler;
/*     */ import thaumcraft.client.lib.ender.ShaderHelper;
/*     */ import thaumcraft.client.lib.models.CustomMeshModel;
/*     */ import thaumcraft.client.lib.models.ModelRegistrationHelper;
/*     */ import thaumcraft.client.lib.models.block.BlockModelLoader;
/*     */ import thaumcraft.client.renderers.entity.RenderAuraNode;
/*     */ import thaumcraft.client.renderers.entity.RenderFallingTaint;
/*     */ import thaumcraft.client.renderers.entity.RenderSpecialItem;
/*     */ import thaumcraft.client.renderers.entity.RenderTaintSourceCloud;
/*     */ import thaumcraft.client.renderers.entity.RenderTaintSourceLightning;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderCultistPortalGreater;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderCultistPortalLesser;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderNodeMagnet;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderThaumcraftGolem;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderTurretCrossbow;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderTurretCrossbowAdvanced;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderTurretEldritch;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderTurretFocus;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderBrainyZombie;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderCultist;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderEldritchCrab;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderEldritchGolem;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderEldritchGuardian;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderFireBat;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderInhabitedZombie;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderMindSpider;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderPech;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintChicken;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintCow;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintCrawler;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintCreeper;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintPig;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintRabbit;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintSheep;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintSwarm;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintVillager;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintacle;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderThaumicSlime;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderWisp;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderAlumentum;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderEldritchOrb;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderElectricOrb;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderEmber;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderExplosiveOrb;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderFrostShard;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderGrapple;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderHomingShard;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderPechBlast;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderPrimalArrow;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderPrimalOrb;
/*     */ import thaumcraft.client.renderers.models.block.CustomJarInventoryModel;
/*     */ import thaumcraft.client.renderers.models.entity.ModelEldritchGolem;
/*     */ import thaumcraft.client.renderers.models.entity.ModelEldritchGuardian;
/*     */ import thaumcraft.client.renderers.models.entity.ModelPech;
/*     */ import thaumcraft.client.renderers.models.entity.ModelTaintSheep2;
/*     */ import thaumcraft.client.renderers.models.gear.CustomWandMeshModel;
/*     */ import thaumcraft.client.renderers.tile.TileAlembicRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileArcaneBoreBaseRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileArcaneBoreRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileArcaneWorkbenchChargerRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileArcaneWorkbenchRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileBannerRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileBellowsRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileCentrifugeRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileCrucibleRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileCrystallizerRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileEldritchCapRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileEldritchCrabSpawnerRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileEldritchLockRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileEldritchObeliskRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileEldritchPortalRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileEtherealBloomRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileFocalManipulatorRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileHoleRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileHungryChestRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileInfusionMatrixRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileJarRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileMirrorRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileNodeStabilizerRenderer;
/*     */ import thaumcraft.client.renderers.tile.TilePatternCrafterRenderer;
/*     */ import thaumcraft.client.renderers.tile.TilePedestalRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileRechargePedestalRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileResearchTableRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileThaumatoriumRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileTubeBufferRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileTubeOnewayRenderer;
/*     */ import thaumcraft.client.renderers.tile.TileTubeValveRenderer;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ import thaumcraft.common.entities.EntityFallingTaint;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ import thaumcraft.common.entities.EntityPermanentItem;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.entities.EntityTaintSourceCloud;
/*     */ import thaumcraft.common.entities.construct.EntityNodeMagnet;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced;
/*     */ import thaumcraft.common.entities.construct.EntityTurretEldritch;
/*     */ import thaumcraft.common.entities.construct.EntityTurretFocus;
/*     */ import thaumcraft.common.entities.construct.golem.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.entities.construct.golem.ItemGolemBell;
/*     */ import thaumcraft.common.entities.monster.EntityBrainyZombie;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchCrab;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.EntityFireBat;
/*     */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*     */ import thaumcraft.common.entities.monster.EntityInhabitedZombie;
/*     */ import thaumcraft.common.entities.monster.EntityMindSpider;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.entities.monster.EntityThaumicSlime;
/*     */ import thaumcraft.common.entities.monster.EntityWisp;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*     */ import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintChicken;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCow;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCreeper;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintPig;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintRabbit;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSheep;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSwarm;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintVillager;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintacle;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintacleSmall;
/*     */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*     */ import thaumcraft.common.entities.projectile.EntityBottleTaint;
/*     */ import thaumcraft.common.entities.projectile.EntityEldritchOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityEmber;
/*     */ import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityFrostShard;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemDart;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityGrapple;
/*     */ import thaumcraft.common.entities.projectile.EntityHomingShard;
/*     */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityShockOrb;
/*     */ import thaumcraft.common.items.ISubItems;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.aura.EntityAuraNode;
/*     */ import thaumcraft.common.lib.events.KeyHandler;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.tiles.crafting.TileAlembic;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbenchCharger;
/*     */ import thaumcraft.common.tiles.crafting.TileCrucible;
/*     */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*     */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*     */ import thaumcraft.common.tiles.crafting.TileInfusionMatrix;
/*     */ import thaumcraft.common.tiles.crafting.TilePedestal;
/*     */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*     */ import thaumcraft.common.tiles.crafting.TileSmelter;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneBore;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneBoreBase;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotem;
/*     */ import thaumcraft.common.tiles.devices.TileBellows;
/*     */ import thaumcraft.common.tiles.devices.TileDioptra;
/*     */ import thaumcraft.common.tiles.devices.TileHungryChest;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ import thaumcraft.common.tiles.devices.TileNodeStabilizer;
/*     */ import thaumcraft.common.tiles.devices.TilePatternCrafter;
/*     */ import thaumcraft.common.tiles.devices.TileRechargePedestal;
/*     */ import thaumcraft.common.tiles.devices.TileSpa;
/*     */ import thaumcraft.common.tiles.devices.TileThaumatorium;
/*     */ import thaumcraft.common.tiles.essentia.TileCentrifuge;
/*     */ import thaumcraft.common.tiles.essentia.TileCrystallizer;
/*     */ import thaumcraft.common.tiles.essentia.TileJar;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeBuffer;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeOneway;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeValve;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchAltar;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchCap;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchCrabSpawner;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchLock;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchObelisk;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchPortal;
/*     */ import thaumcraft.common.tiles.misc.TileEtherealBloom;
/*     */ import thaumcraft.common.tiles.misc.TileHole;
/*     */ 
/*     */ public class ClientProxy extends CommonProxy
/*     */ {
/*     */   protected PlayerKnowledge playerResearch;
/*     */   protected ResearchManager researchManager;
/*     */   public WandManager wandManager;
/*     */   public RenderEventHandler renderEventHandler;
/*     */   
/*     */   public void registerHandlers()
/*     */   {
/* 271 */     this.renderEventHandler = new RenderEventHandler();
/*     */     
/* 273 */     MinecraftForge.EVENT_BUS.register(this.renderEventHandler);
/* 274 */     MinecraftForge.EVENT_BUS.register(ParticleEngine.instance);
/*     */     
/* 276 */     ShaderHelper.initShaders();
/*     */     
/* 278 */     Thaumcraft.modelRegistrationHelper = new ModelRegistrationHelper();
/* 279 */     ModelLoaderRegistry.registerLoader(new BlockModelLoader());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 284 */     MinecraftForge.EVENT_BUS.register(BlocksTC.levitator);
/* 285 */     MinecraftForge.EVENT_BUS.register(BlocksTC.dioptra);
/* 286 */     MinecraftForge.EVENT_BUS.register(BlocksTC.tube);
/* 287 */     MinecraftForge.EVENT_BUS.register(BlocksTC.redstoneRelay);
/* 288 */     MinecraftForge.EVENT_BUS.register(BlocksTC.patternCrafter);
/*     */     
/*     */ 
/* 291 */     Item fluid0 = Item.func_150898_a(BlocksTC.fluxGoo);
/* 292 */     ModelBakery.addVariantName(fluid0, new String[0]);
/* 293 */     ModelLoader.setCustomMeshDefinition(fluid0, new ItemMeshDefinition() {
/*     */       public ModelResourceLocation func_178113_a(ItemStack stack) {
/* 295 */         return ClientProxy.fluidGooLocation;
/*     */       }
/* 297 */     });
/* 298 */     ModelLoader.setCustomStateMapper(BlocksTC.fluxGoo, new StateMapperBase() {
/*     */       protected ModelResourceLocation func_178132_a(IBlockState state) {
/* 300 */         return ClientProxy.fluidGooLocation;
/*     */       }
/*     */       
/* 303 */     });
/* 304 */     Item fluid1 = Item.func_150898_a(BlocksTC.taintDust);
/* 305 */     ModelBakery.addVariantName(fluid1, new String[0]);
/* 306 */     ModelLoader.setCustomMeshDefinition(fluid1, new ItemMeshDefinition() {
/*     */       public ModelResourceLocation func_178113_a(ItemStack stack) {
/* 308 */         return ClientProxy.taintDustLocation;
/*     */       }
/* 310 */     });
/* 311 */     ModelLoader.setCustomStateMapper(BlocksTC.taintDust, new StateMapperBase() {
/*     */       protected ModelResourceLocation func_178132_a(IBlockState state) {
/* 313 */         return ClientProxy.taintDustLocation;
/*     */       }
/*     */       
/* 316 */     });
/* 317 */     Item fluid = Item.func_150898_a(BlocksTC.liquidDeath);
/* 318 */     ModelBakery.addVariantName(fluid, new String[0]);
/* 319 */     ModelLoader.setCustomMeshDefinition(fluid, new ItemMeshDefinition() {
/*     */       public ModelResourceLocation func_178113_a(ItemStack stack) {
/* 321 */         return ClientProxy.fluidDeathLocation;
/*     */       }
/* 323 */     });
/* 324 */     ModelLoader.setCustomStateMapper(BlocksTC.liquidDeath, new StateMapperBase() {
/*     */       protected ModelResourceLocation func_178132_a(IBlockState state) {
/* 326 */         return ClientProxy.fluidDeathLocation;
/*     */       }
/*     */       
/* 329 */     });
/* 330 */     Item fluid2 = Item.func_150898_a(BlocksTC.purifyingFluid);
/* 331 */     ModelBakery.addVariantName(fluid2, new String[0]);
/* 332 */     ModelLoader.setCustomMeshDefinition(fluid2, new ItemMeshDefinition() {
/*     */       public ModelResourceLocation func_178113_a(ItemStack stack) {
/* 334 */         return ClientProxy.fluidPureLocation;
/*     */       }
/* 336 */     });
/* 337 */     ModelLoader.setCustomStateMapper(BlocksTC.purifyingFluid, new StateMapperBase() {
/*     */       protected ModelResourceLocation func_178132_a(IBlockState state) {
/* 339 */         return ClientProxy.fluidPureLocation;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/* 344 */   private static ModelResourceLocation fluidGooLocation = new ModelResourceLocation("Thaumcraft:flux_goo", "fluid");
/* 345 */   private static ModelResourceLocation taintDustLocation = new ModelResourceLocation("Thaumcraft:taint_dust", "fluid");
/* 346 */   private static ModelResourceLocation fluidDeathLocation = new ModelResourceLocation("Thaumcraft:liquid_death", "fluid");
/* 347 */   private static ModelResourceLocation fluidPureLocation = new ModelResourceLocation("Thaumcraft:purifying_fluid", "fluid");
/*     */   
/*     */   KeyHandler kh;
/*     */   
/*     */   public void registerKeyBindings()
/*     */   {
/* 353 */     FMLCommonHandler.instance().bus().register(this.kh);
/*     */   }
/*     */   
/*     */   public KeyHandler getKeyBindings()
/*     */   {
/* 358 */     return this.kh;
/*     */   }
/*     */   
/*     */   public RenderEventHandler getRenderEventHandler()
/*     */   {
/* 363 */     return this.renderEventHandler;
/*     */   }
/*     */   
/*     */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/* 368 */     if ((world instanceof WorldClient)) {
/* 369 */       switch (ID) {
/* 370 */       case 1:  return new GuiPech(player.field_71071_by, world, (EntityPech)((WorldClient)world).func_73045_a(x));
/* 371 */       case 15:  return new GuiTurretFocus(player.field_71071_by, world, (EntityTurretFocus)((WorldClient)world).func_73045_a(x));
/* 372 */       case 16:  return new GuiTurretBasic(player.field_71071_by, world, (EntityTurretCrossbow)((WorldClient)world).func_73045_a(x));
/* 373 */       case 17:  return new GuiTurretAdvanced(player.field_71071_by, world, (EntityTurretCrossbowAdvanced)((WorldClient)world).func_73045_a(x));
/*     */       case 9: 
/* 375 */         return new GuiSmelter(player.field_71071_by, (TileSmelter)world.func_175625_s(new BlockPos(x, y, z)));
/* 376 */       case 3:  return new GuiThaumatorium(player.field_71071_by, (TileThaumatorium)world.func_175625_s(new BlockPos(x, y, z)));
/* 377 */       case 8:  return new GuiAuraTotem(player.field_71071_by, (TileAuraTotem)world.func_175625_s(new BlockPos(x, y, z)));
/* 378 */       case 10:  return new GuiResearchTable(player, (TileResearchTable)world.func_175625_s(new BlockPos(x, y, z)));
/* 379 */       case 12:  return new GuiResearchBrowser();
/* 380 */       case 13:  return new GuiArcaneWorkbench(player.field_71071_by, (TileArcaneWorkbench)world.func_175625_s(new BlockPos(x, y, z)));
/* 381 */       case 14:  return new GuiArcaneBore(player.field_71071_by, (TileArcaneBore)world.func_175625_s(new BlockPos(x, y, z)));
/* 382 */       case 4:  return new GuiHandMirror(player.field_71071_by, world, x, y, z);
/* 383 */       case 5:  return new GuiFocusPouch(player.field_71071_by, world, x, y, z);
/* 384 */       case 6:  return new GuiSpa(player.field_71071_by, (TileSpa)world.func_175625_s(new BlockPos(x, y, z)));
/* 385 */       case 7:  return new GuiFocalManipulator(player.field_71071_by, (TileFocalManipulator)world.func_175625_s(new BlockPos(x, y, z)));
/* 386 */       case 19:  return new GuiGolemBuilder(player.field_71071_by, (TileGolemBuilder)world.func_175625_s(new BlockPos(x, y, z)));
/*     */       case 18: 
/* 388 */         ISealEntity se = ItemGolemBell.getSeal(player);
/* 389 */         if (se != null) return se.getSeal().returnGui(world, player, new BlockPos(x, y, z), se.getSealPos().face, se);
/*     */         break;
/*     */       }
/*     */     }
/* 393 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerDisplayInformationInit()
/*     */   {
/* 407 */     for (ItemModelEntry modelEntry : itemsToRegister)
/*     */     {
/* 409 */       if (modelEntry.variant) {
/* 410 */         registerVariantName(modelEntry.item, modelEntry.name);
/*     */       }
/* 412 */       ModelHelper.getItemModelMesher().func_178086_a(modelEntry.item, modelEntry.metadata, new ModelResourceLocation("Thaumcraft:" + modelEntry.name, "inventory"));
/*     */     }
/* 414 */     setOtherItemRenderers();
/*     */     
/*     */ 
/* 417 */     for (ModelEntry modelEntry : blocksToRegister)
/*     */     {
/* 419 */       ModelHelper.registerBlock(modelEntry.block, modelEntry.metadata, "Thaumcraft:" + modelEntry.name);
/*     */     }
/*     */     
/* 422 */     setOtherBlockRenderers();
/*     */     
/*     */ 
/* 425 */     setupEntityRenderers();
/*     */     
/*     */ 
/* 428 */     setupTileRenderers();
/*     */   }
/*     */   
/*     */   public void registerVariantName(Item item, String name) {
/* 432 */     ModelBakery.registerItemVariants(item, new ResourceLocation[] { new ResourceLocation("Thaumcraft", name) });
/*     */   }
/*     */   
/*     */ 
/*     */   public void registerBlockMesh(Block block, int metadata, String name)
/*     */   {
/* 438 */     blocksToRegister.add(new ModelEntry(block, metadata, name));
/*     */   }
/*     */   
/*     */ 
/*     */   public void registerItemMesh(Item item, int metadata, String name)
/*     */   {
/* 444 */     itemsToRegister.add(new ItemModelEntry(item, metadata, name, false));
/*     */   }
/*     */   
/*     */ 
/*     */   public void registerItemMesh(Item item, int metadata, String name, boolean variant)
/*     */   {
/* 450 */     itemsToRegister.add(new ItemModelEntry(item, metadata, name, variant));
/*     */   }
/*     */   
/* 453 */   private static ArrayList<ModelEntry> blocksToRegister = new ArrayList();
/* 454 */   private static ArrayList<ItemModelEntry> itemsToRegister = new ArrayList();
/*     */   private FXDispatcher fx;
/*     */   
/*     */   private static class ModelEntry
/*     */   {
/*     */     public Block block;
/*     */     public int metadata;
/*     */     public String name;
/*     */     
/*     */     public ModelEntry(Block block, int metadata, String name) {
/* 464 */       this.block = block;
/* 465 */       this.metadata = metadata;
/* 466 */       this.name = name;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ItemModelEntry
/*     */   {
/*     */     public Item item;
/*     */     public int metadata;
/*     */     public String name;
/*     */     public boolean variant;
/*     */     
/*     */     public ItemModelEntry(Item item, int metadata, String name, boolean variant)
/*     */     {
/* 479 */       this.item = item;
/* 480 */       this.metadata = metadata;
/* 481 */       this.name = name;
/* 482 */       this.variant = variant;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public World getClientWorld()
/*     */   {
/* 490 */     return FMLClientHandler.instance().getClient().field_71441_e;
/*     */   }
/*     */   
/*     */   public ClientProxy()
/*     */   {
/* 263 */     this.playerResearch = new PlayerKnowledge();
/* 264 */     this.researchManager = new ResearchManager();
/* 265 */     this.wandManager = new WandManager();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 349 */     this.kh = new KeyHandler();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 493 */     this.fx = new FXDispatcher();
/*     */   }
/*     */   
/*     */   public FXDispatcher getFX() {
/* 497 */     return this.fx;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isShiftKeyDown()
/*     */   {
/* 503 */     return GuiScreen.func_146272_n();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setOtherBlockRenderers()
/*     */   {
/* 509 */     ModelRegistrationHelper helper = Thaumcraft.modelRegistrationHelper;
/*     */     
/*     */ 
/* 512 */     for (int a = 0; a < 16; a++) {
/* 513 */       ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.candle), a, new ModelResourceLocation("Thaumcraft:candle", "inventory"));
/*     */       
/* 515 */       ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.nitor), a, new ModelResourceLocation("Thaumcraft:nitor", "inventory"));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 520 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.slabWood), new String[] { "Thaumcraft:greatwood_half_slab" });
/* 521 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.slabWood), new String[] { "Thaumcraft:silverwood_half_slab" });
/* 522 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.slabStone), new String[] { "Thaumcraft:arcane_half_slab" });
/* 523 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.slabStone), new String[] { "Thaumcraft:arcane_brick_half_slab" });
/* 524 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.slabStone), new String[] { "Thaumcraft:ancient_half_slab" });
/* 525 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.slabStone), new String[] { "Thaumcraft:eldritch_half_slab" });
/* 526 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.slabWood), 0, new ModelResourceLocation("Thaumcraft:greatwood_half_slab", "inventory"));
/* 527 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.slabWood), 1, new ModelResourceLocation("Thaumcraft:silverwood_half_slab", "inventory"));
/* 528 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.slabStone), 0, new ModelResourceLocation("Thaumcraft:arcane_half_slab", "inventory"));
/* 529 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.slabStone), 1, new ModelResourceLocation("Thaumcraft:arcane_brick_half_slab", "inventory"));
/* 530 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.slabStone), 2, new ModelResourceLocation("Thaumcraft:ancient_half_slab", "inventory"));
/* 531 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.slabStone), 3, new ModelResourceLocation("Thaumcraft:eldritch_half_slab", "inventory"));
/*     */     
/* 533 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.mirror), new String[] { "Thaumcraft:mirror", "Thaumcraft:mirror_on" });
/* 534 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.mirrorEssentia), new String[] { "Thaumcraft:mirror_essentia", "Thaumcraft:mirror_essentia_on" });
/* 535 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.mirror), 0, new ModelResourceLocation("Thaumcraft:mirror", "inventory"));
/* 536 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.mirror), 1, new ModelResourceLocation("Thaumcraft:mirror_on", "inventory"));
/* 537 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.mirrorEssentia), 0, new ModelResourceLocation("Thaumcraft:mirror_essentia", "inventory"));
/* 538 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.mirrorEssentia), 1, new ModelResourceLocation("Thaumcraft:mirror_essentia_on", "inventory"));
/*     */     
/*     */ 
/* 541 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.sapling), new String[] { "Thaumcraft:greatwood_sapling", "Thaumcraft:silverwood_sapling" });
/*     */     
/* 543 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.sapling), 0, new ModelResourceLocation("Thaumcraft:greatwood_sapling", "inventory"));
/*     */     
/* 545 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.sapling), 1, new ModelResourceLocation("Thaumcraft:silverwood_sapling", "inventory"));
/*     */     
/*     */ 
/*     */ 
/* 549 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.leaf), new String[] { "Thaumcraft:greatwood_sapling", "Thaumcraft:greatwood_leaves" });
/*     */     
/* 551 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.leaf), 0, new ModelResourceLocation("Thaumcraft:greatwood_leaves", "inventory"));
/*     */     
/* 553 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.leaf), new String[] { "Thaumcraft:greatwood_sapling", "Thaumcraft:silverwood_leaves" });
/*     */     
/* 555 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.leaf), 1, new ModelResourceLocation("Thaumcraft:silverwood_leaves", "inventory"));
/*     */     
/*     */ 
/*     */ 
/* 559 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.banner), 0, new ModelResourceLocation("Thaumcraft:banner", "inventory"));
/*     */     
/* 561 */     ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.banner), new String[] { "Thaumcraft:banner_cultist", "Thaumcraft:banner_cultist" });
/*     */     
/* 563 */     ModelHelper.getItemModelMesher().func_178086_a(Item.func_150898_a(BlocksTC.banner), 1, new ModelResourceLocation("Thaumcraft:banner_cultist", "inventory"));
/*     */     
/*     */ 
/*     */ 
/* 567 */     Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178083_a().func_174954_c().func_178123_a(new Block[] { BlocksTC.hungryChest });
/*     */     
/*     */ 
/*     */ 
/* 571 */     Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178083_a().func_174954_c().func_178123_a(new Block[] { BlocksTC.arcaneWorkbenchCharger });
/*     */     
/*     */ 
/*     */ 
/* 575 */     Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178083_a().func_174954_c().func_178123_a(new Block[] { BlocksTC.bellows });
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 580 */     for (int a = 0; a < 4; a++) {
/* 581 */       ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.jar), new String[] { "Thaumcraft:jar_normal_" + a });
/* 582 */       ModelBakery.addVariantName(Item.func_150898_a(BlocksTC.jar), new String[] { "Thaumcraft:jar_void_" + a });
/*     */     }
/*     */     
/* 585 */     ResourceLocation loc = new ModelResourceLocation("Thaumcraft:jar_normal", "inventory");
/* 586 */     ResourceLocation loc2 = new ModelResourceLocation("Thaumcraft:jar_void", "inventory");
/* 587 */     IBakedModel model = new CustomJarInventoryModel("jar_normal");
/* 588 */     helper.registerCustomItemModel(loc, model, "jar_normal");
/* 589 */     IBakedModel model2 = new CustomJarInventoryModel("jar_void");
/* 590 */     helper.registerCustomItemModel(loc2, model2, "jar_void");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerCustomBlockModel(ModelRegistrationHelper helper, String blockName, String stateName)
/*     */   {
/* 599 */     ResourceLocation loc = new ModelResourceLocation("Thaumcraft:" + blockName, stateName);
/* 600 */     IBakedModel model = new CustomMeshModel(blockName);
/*     */     
/* 602 */     helper.registerCustomBlockModel(loc, model, blockName);
/*     */   }
/*     */   
/*     */   public void registerBlockTexture(Block block, String blockName) {
/* 606 */     registerBlockTexture(block, 0, blockName);
/*     */   }
/*     */   
/*     */   public void registerBlockTexture(Block block, int meta, String blockName) {
/* 610 */     RenderItem renderItem = Minecraft.func_71410_x().func_175599_af();
/* 611 */     renderItem.func_175037_a().func_178086_a(Item.func_150898_a(block), meta, new ModelResourceLocation("Thaumcraft:" + blockName, "inventory"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void registerFromSubItems(Item item, String name)
/*     */   {
/* 617 */     if ((item instanceof ISubItems)) {
/* 618 */       for (int i : ((ISubItems)item).getSubItems())
/* 619 */         Thaumcraft.proxy.registerItemMesh(item, i, name);
/*     */     } else {
/* 621 */       ArrayList<ItemStack> list = new ArrayList();
/* 622 */       item.func_150895_a(item, Thaumcraft.tabTC, list);
/* 623 */       if (list.size() > 0) {
/* 624 */         for (ItemStack i : list) {
/* 625 */           Thaumcraft.proxy.registerItemMesh(item, i.func_77952_i(), name);
/*     */         }
/*     */       } else {
/* 628 */         Thaumcraft.proxy.registerItemMesh(item, 0, name);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setOtherItemRenderers() {
/* 634 */     ModelRegistrationHelper helper = Thaumcraft.modelRegistrationHelper;
/* 635 */     registerWandItemModel(helper, "wand");
/* 636 */     registerItemTexture(ItemsTC.wand, 0, "wand");
/* 637 */     ModelBakery.addVariantName(ItemsTC.sinisterStone, new String[] { "Thaumcraft:sinister_stone", "Thaumcraft:sinister_stone_on" });
/*     */   }
/*     */   
/*     */   public void registerCustomItemModel(ModelRegistrationHelper helper, String itemName)
/*     */   {
/* 642 */     ResourceLocation loc = new ModelResourceLocation("Thaumcraft:" + itemName, "inventory");
/* 643 */     IBakedModel model = new CustomMeshModel(itemName);
/* 644 */     helper.registerCustomItemModel(loc, model, itemName);
/*     */   }
/*     */   
/*     */   public void registerItemTexture(Item item, String itemName) {
/* 648 */     registerItemTexture(item, 0, itemName);
/*     */   }
/*     */   
/*     */   public void registerItemTexture(Item item, int meta, String itemName) {
/* 652 */     RenderItem renderItem = Minecraft.func_71410_x().func_175599_af();
/* 653 */     renderItem.func_175037_a().func_178086_a(item, meta, new ModelResourceLocation("Thaumcraft:" + itemName, "inventory"));
/* 654 */     ModelBakery.addVariantName(item, new String[] { "Thaumcraft:" + itemName });
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static void registerWandItemModel(ModelRegistrationHelper helper, String itemName)
/*     */   {
/* 660 */     ResourceLocation loc = new ModelResourceLocation("Thaumcraft:" + itemName, "inventory");
/* 661 */     IBakedModel model = new CustomWandMeshModel(itemName);
/* 662 */     helper.registerCustomItemModel(loc, model, itemName);
/*     */     
/* 664 */     for (WandCap cap : WandCap.caps.values()) {
/* 665 */       String key2 = "wand" + cap.getTag();
/* 666 */       ResourceLocation loc2 = new ModelResourceLocation("Thaumcraft:" + key2, "inventory");
/* 667 */       IBakedModel model2 = new CustomWandMeshModel(key2, cap, false);
/* 668 */       helper.registerCustomItemModel(loc2, model2, key2);
/*     */       
/* 670 */       String key3 = "staff" + cap.getTag();
/* 671 */       ResourceLocation loc3 = new ModelResourceLocation("Thaumcraft:" + key3, "inventory");
/* 672 */       IBakedModel model3 = new CustomWandMeshModel(key3, cap, true);
/* 673 */       helper.registerCustomItemModel(loc3, model3, key3);
/*     */       
/* 675 */       String key4 = "sceptre" + cap.getTag();
/* 676 */       ResourceLocation loc4 = new ModelResourceLocation("Thaumcraft:" + key4, "inventory");
/* 677 */       IBakedModel model4 = new CustomWandMeshModel(key4, cap);
/* 678 */       helper.registerCustomItemModel(loc4, model4, key4);
/*     */     }
/*     */     
/* 681 */     for (WandRod rod : WandRod.rods.values()) {
/* 682 */       String key = (rod.isStaff() ? "staff" : "wand") + rod.getTag();
/* 683 */       ResourceLocation loc2 = new ModelResourceLocation("Thaumcraft:" + key, "inventory");
/* 684 */       IBakedModel model2 = new CustomWandMeshModel(key, rod);
/* 685 */       helper.registerCustomItemModel(loc2, model2, key);
/*     */     }
/*     */     
/* 688 */     for (ItemFocusBasic focus : ItemFocusBasic.foci.values()) {
/* 689 */       String key = "focus" + focus.getFocusId();
/* 690 */       ResourceLocation loc2 = new ModelResourceLocation("Thaumcraft:" + key, "inventory");
/* 691 */       IBakedModel model2 = new CustomWandMeshModel(key, focus);
/* 692 */       helper.registerCustomItemModel(loc2, model2, key);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setupTileRenderers()
/*     */   {
/* 698 */     registerTileEntitySpecialRenderer(TileCrucible.class, new TileCrucibleRenderer());
/* 699 */     registerTileEntitySpecialRenderer(TileDioptra.class, new thaumcraft.client.renderers.tile.TileDioptraRenderer());
/* 700 */     registerTileEntitySpecialRenderer(TilePedestal.class, new TilePedestalRenderer());
/* 701 */     registerTileEntitySpecialRenderer(TileRechargePedestal.class, new TileRechargePedestalRenderer());
/* 702 */     registerTileEntitySpecialRenderer(TileArcaneWorkbench.class, new TileArcaneWorkbenchRenderer());
/* 703 */     registerTileEntitySpecialRenderer(TileArcaneWorkbenchCharger.class, new TileArcaneWorkbenchChargerRenderer());
/* 704 */     registerTileEntitySpecialRenderer(TileFocalManipulator.class, new TileFocalManipulatorRenderer());
/* 705 */     registerTileEntitySpecialRenderer(TileHungryChest.class, new TileHungryChestRenderer());
/* 706 */     registerTileEntitySpecialRenderer(TileTubeOneway.class, new TileTubeOnewayRenderer());
/* 707 */     registerTileEntitySpecialRenderer(TileTubeValve.class, new TileTubeValveRenderer());
/* 708 */     registerTileEntitySpecialRenderer(TileTubeBuffer.class, new TileTubeBufferRenderer());
/* 709 */     registerTileEntitySpecialRenderer(TileJar.class, new TileJarRenderer());
/* 710 */     registerTileEntitySpecialRenderer(TileBellows.class, new TileBellowsRenderer());
/* 711 */     registerTileEntitySpecialRenderer(TileAlembic.class, new TileAlembicRenderer());
/* 712 */     registerTileEntitySpecialRenderer(TileInfusionMatrix.class, new TileInfusionMatrixRenderer(0));
/* 713 */     registerTileEntitySpecialRenderer(TileResearchTable.class, new TileResearchTableRenderer());
/* 714 */     registerTileEntitySpecialRenderer(TileArcaneBore.class, new TileArcaneBoreRenderer());
/* 715 */     registerTileEntitySpecialRenderer(TileArcaneBoreBase.class, new TileArcaneBoreBaseRenderer());
/* 716 */     registerTileEntitySpecialRenderer(TileThaumatorium.class, new TileThaumatoriumRenderer());
/* 717 */     registerTileEntitySpecialRenderer(TileCrystallizer.class, new TileCrystallizerRenderer());
/* 718 */     registerTileEntitySpecialRenderer(TileCentrifuge.class, new TileCentrifugeRenderer());
/* 719 */     TileMirrorRenderer tmr = new TileMirrorRenderer();
/* 720 */     registerTileEntitySpecialRenderer(TileMirror.class, tmr);
/* 721 */     registerTileEntitySpecialRenderer(TileMirrorEssentia.class, tmr);
/* 722 */     registerTileEntitySpecialRenderer(TileGolemBuilder.class, new thaumcraft.client.renderers.tile.TileGolemBuilderRenderer());
/* 723 */     registerTileEntitySpecialRenderer(TileNodeStabilizer.class, new TileNodeStabilizerRenderer());
/* 724 */     registerTileEntitySpecialRenderer(TilePatternCrafter.class, new TilePatternCrafterRenderer());
/*     */     
/*     */ 
/* 727 */     registerTileEntitySpecialRenderer(TileHole.class, new TileHoleRenderer());
/* 728 */     registerTileEntitySpecialRenderer(TileEtherealBloom.class, new TileEtherealBloomRenderer());
/* 729 */     registerTileEntitySpecialRenderer(thaumcraft.common.tiles.misc.TileBanner.class, new TileBannerRenderer());
/* 730 */     registerTileEntitySpecialRenderer(TileEldritchAltar.class, new TileEldritchCapRenderer(new ResourceLocation("thaumcraft", "textures/models/obelisk_cap_altar.png")));
/* 731 */     registerTileEntitySpecialRenderer(TileEldritchCap.class, new TileEldritchCapRenderer(new ResourceLocation("thaumcraft", "textures/models/obelisk_cap.png")));
/* 732 */     registerTileEntitySpecialRenderer(TileEldritchObelisk.class, new TileEldritchObeliskRenderer());
/* 733 */     registerTileEntitySpecialRenderer(TileEldritchLock.class, new TileEldritchLockRenderer());
/* 734 */     registerTileEntitySpecialRenderer(TileEldritchPortal.class, new TileEldritchPortalRenderer());
/* 735 */     registerTileEntitySpecialRenderer(TileEldritchCrabSpawner.class, new TileEldritchCrabSpawnerRenderer());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void registerTileEntitySpecialRenderer(Class tile, TileEntitySpecialRenderer renderer)
/*     */   {
/* 742 */     ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
/*     */   }
/*     */   
/*     */   public void setupEntityRenderers()
/*     */   {
/* 747 */     RenderingRegistry.registerEntityRenderingHandler(EntityAuraNode.class, new RenderAuraNode(Minecraft.func_71410_x().func_175598_ae()));
/* 748 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpecialItem.class, new RenderSpecialItem(Minecraft.func_71410_x().func_175598_ae(), Minecraft.func_71410_x().func_175599_af()));
/* 749 */     RenderingRegistry.registerEntityRenderingHandler(EntityFollowingItem.class, new RenderEntityItem(Minecraft.func_71410_x().func_175598_ae(), Minecraft.func_71410_x().func_175599_af()));
/* 750 */     RenderingRegistry.registerEntityRenderingHandler(EntityPermanentItem.class, new RenderSpecialItem(Minecraft.func_71410_x().func_175598_ae(), Minecraft.func_71410_x().func_175599_af()));
/* 751 */     RenderingRegistry.registerEntityRenderingHandler(EntityFallingTaint.class, new RenderFallingTaint(Minecraft.func_71410_x().func_175598_ae()));
/* 752 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintSourceCloud.class, new RenderTaintSourceCloud(Minecraft.func_71410_x().func_175598_ae()));
/* 753 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.EntityTaintSourceLightning.class, new RenderTaintSourceLightning(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/*     */ 
/* 756 */     RenderingRegistry.registerEntityRenderingHandler(EntityBrainyZombie.class, new RenderBrainyZombie(Minecraft.func_71410_x().func_175598_ae()));
/* 757 */     RenderingRegistry.registerEntityRenderingHandler(EntityInhabitedZombie.class, new RenderInhabitedZombie(Minecraft.func_71410_x().func_175598_ae()));
/* 758 */     RenderingRegistry.registerEntityRenderingHandler(EntityGiantBrainyZombie.class, new RenderBrainyZombie(Minecraft.func_71410_x().func_175598_ae()));
/* 759 */     RenderingRegistry.registerEntityRenderingHandler(EntityFireBat.class, new RenderFireBat(Minecraft.func_71410_x().func_175598_ae()));
/* 760 */     RenderingRegistry.registerEntityRenderingHandler(EntityPech.class, new RenderPech(Minecraft.func_71410_x().func_175598_ae(), new ModelPech(), 0.25F));
/* 761 */     RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp(Minecraft.func_71410_x().func_175598_ae()));
/* 762 */     RenderingRegistry.registerEntityRenderingHandler(EntityThaumicSlime.class, new RenderThaumicSlime(Minecraft.func_71410_x().func_175598_ae(), new ModelSlime(16), 0.25F));
/*     */     
/* 764 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintCrawler.class, new RenderTaintCrawler(Minecraft.func_71410_x().func_175598_ae()));
/* 765 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintChicken.class, new RenderTaintChicken(Minecraft.func_71410_x().func_175598_ae(), new ModelChicken(), 0.3F));
/* 766 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintRabbit.class, new RenderTaintRabbit(Minecraft.func_71410_x().func_175598_ae(), new ModelRabbit(), 0.3F));
/* 767 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintCow.class, new RenderTaintCow(Minecraft.func_71410_x().func_175598_ae(), new ModelCow(), 0.7F));
/* 768 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintCreeper.class, new RenderTaintCreeper(Minecraft.func_71410_x().func_175598_ae()));
/* 769 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintPig.class, new RenderTaintPig(Minecraft.func_71410_x().func_175598_ae(), new ModelPig(), 0.7F));
/* 770 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintSheep.class, new RenderTaintSheep(Minecraft.func_71410_x().func_175598_ae(), new ModelTaintSheep2(), 0.7F));
/* 771 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintVillager.class, new RenderTaintVillager(Minecraft.func_71410_x().func_175598_ae()));
/* 772 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintacle.class, new RenderTaintacle(Minecraft.func_71410_x().func_175598_ae(), 0.6F, 10));
/* 773 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintacleSmall.class, new RenderTaintacle(Minecraft.func_71410_x().func_175598_ae(), 0.2F, 6));
/* 774 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintSwarm.class, new RenderTaintSwarm(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/* 776 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistKnight.class, new RenderCultist(Minecraft.func_71410_x().func_175598_ae()));
/* 777 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistLeader.class, new RenderCultist(Minecraft.func_71410_x().func_175598_ae()));
/* 778 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistCleric.class, new RenderCultist(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/* 780 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchGuardian.class, new RenderEldritchGuardian(Minecraft.func_71410_x().func_175598_ae(), new ModelEldritchGuardian(), 0.5F));
/* 781 */     RenderingRegistry.registerEntityRenderingHandler(EntityMindSpider.class, new RenderMindSpider(Minecraft.func_71410_x().func_175598_ae()));
/* 782 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchCrab.class, new RenderEldritchCrab(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/* 784 */     RenderingRegistry.registerEntityRenderingHandler(EntityTurretEldritch.class, new RenderTurretEldritch(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/*     */ 
/* 787 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintacleGiant.class, new RenderTaintacle(Minecraft.func_71410_x().func_175598_ae(), 1.0F, 14));
/* 788 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistPortalGreater.class, new RenderCultistPortalGreater(Minecraft.func_71410_x().func_175598_ae()));
/* 789 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistPortalLesser.class, new RenderCultistPortalLesser(Minecraft.func_71410_x().func_175598_ae()));
/* 790 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchWarden.class, new RenderEldritchGuardian(Minecraft.func_71410_x().func_175598_ae(), new ModelEldritchGuardian(), 0.6F));
/* 791 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchGolem.class, new RenderEldritchGolem(Minecraft.func_71410_x().func_175598_ae(), new ModelEldritchGolem(), 0.5F));
/*     */     
/*     */ 
/* 794 */     RenderingRegistry.registerEntityRenderingHandler(EntityAlumentum.class, new RenderAlumentum(Minecraft.func_71410_x().func_175598_ae()));
/* 795 */     RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveOrb.class, new RenderExplosiveOrb(Minecraft.func_71410_x().func_175598_ae()));
/* 796 */     RenderingRegistry.registerEntityRenderingHandler(EntityPechBlast.class, new RenderPechBlast(Minecraft.func_71410_x().func_175598_ae()));
/* 797 */     RenderingRegistry.registerEntityRenderingHandler(EntityEmber.class, new RenderEmber(Minecraft.func_71410_x().func_175598_ae()));
/* 798 */     RenderingRegistry.registerEntityRenderingHandler(EntityFrostShard.class, new RenderFrostShard(Minecraft.func_71410_x().func_175598_ae()));
/* 799 */     RenderingRegistry.registerEntityRenderingHandler(EntityPrimalOrb.class, new RenderPrimalOrb(Minecraft.func_71410_x().func_175598_ae()));
/* 800 */     RenderingRegistry.registerEntityRenderingHandler(EntityShockOrb.class, new RenderElectricOrb(Minecraft.func_71410_x().func_175598_ae()));
/* 801 */     RenderingRegistry.registerEntityRenderingHandler(EntityGolemOrb.class, new RenderElectricOrb(Minecraft.func_71410_x().func_175598_ae()));
/* 802 */     RenderingRegistry.registerEntityRenderingHandler(EntityHomingShard.class, new RenderHomingShard(Minecraft.func_71410_x().func_175598_ae()));
/* 803 */     RenderingRegistry.registerEntityRenderingHandler(EntityPrimalArrow.class, new RenderPrimalArrow(Minecraft.func_71410_x().func_175598_ae()));
/* 804 */     RenderingRegistry.registerEntityRenderingHandler(EntityGolemDart.class, new RenderPrimalArrow(Minecraft.func_71410_x().func_175598_ae()));
/* 805 */     RenderingRegistry.registerEntityRenderingHandler(EntityBottleTaint.class, new RenderSnowball(Minecraft.func_71410_x().func_175598_ae(), ItemsTC.bottleTaint, Minecraft.func_71410_x().func_175599_af()));
/* 806 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchOrb.class, new RenderEldritchOrb(Minecraft.func_71410_x().func_175598_ae()));
/* 807 */     RenderingRegistry.registerEntityRenderingHandler(EntityGrapple.class, new RenderGrapple(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/*     */ 
/* 810 */     RenderingRegistry.registerEntityRenderingHandler(EntityTurretFocus.class, new RenderTurretFocus(Minecraft.func_71410_x().func_175598_ae()));
/* 811 */     RenderingRegistry.registerEntityRenderingHandler(EntityTurretCrossbowAdvanced.class, new RenderTurretCrossbowAdvanced(Minecraft.func_71410_x().func_175598_ae()));
/* 812 */     RenderingRegistry.registerEntityRenderingHandler(EntityTurretCrossbow.class, new RenderTurretCrossbow(Minecraft.func_71410_x().func_175598_ae()));
/* 813 */     RenderingRegistry.registerEntityRenderingHandler(EntityNodeMagnet.class, new RenderNodeMagnet(Minecraft.func_71410_x().func_175598_ae()));
/* 814 */     RenderingRegistry.registerEntityRenderingHandler(EntityThaumcraftGolem.class, new RenderThaumcraftGolem(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 825 */     VillagerRegistry.instance().registerVillagerSkin(ConfigEntities.entWizardId, new ResourceLocation("thaumcraft", "textures/models/creature/wizard.png"));
/* 826 */     VillagerRegistry.instance().registerVillagerSkin(ConfigEntities.entBankerId, new ResourceLocation("thaumcraft", "textures/models/creature/moneychanger.png"));
/*     */     
/* 828 */     TileEntityItemStackRenderer.field_147719_a = new ModeledBlockInventoryRenderer(TileEntityItemStackRenderer.field_147719_a);
/*     */   }
/*     */   
/*     */   public void registerDisplayInformationPreInit() {}
/*     */   
/* 833 */   public class ModeledBlockInventoryRenderer extends TileEntityItemStackRenderer { private TileHungryChest temc = new TileHungryChest();
/* 834 */     private TileArcaneWorkbenchCharger tawc = new TileArcaneWorkbenchCharger();
/* 835 */     private TileBellows tbell = new TileBellows();
/* 836 */     private TileInfusionMatrix tmatrix = new TileInfusionMatrix();
/* 837 */     private TileArcaneBore tbore = new TileArcaneBore();
/* 838 */     private TileArcaneBoreBase tborebase = new TileArcaneBoreBase();
/* 839 */     private TileCrystallizer tcrystalizer = new TileCrystallizer();
/* 840 */     private TileCentrifuge tcentrifuge = new TileCentrifuge();
/* 841 */     private TileNodeStabilizer tnodestab = new TileNodeStabilizer();
/*     */     private TileEntityItemStackRenderer chainedTEISR;
/*     */     
/*     */     public ModeledBlockInventoryRenderer(TileEntityItemStackRenderer currentTEISR)
/*     */     {
/* 846 */       this.chainedTEISR = currentTEISR;
/*     */     }
/*     */     
/*     */     public void func_179022_a(ItemStack itemStack) {
/* 850 */       Block block = Block.func_149634_a(itemStack.func_77973_b());
/* 851 */       if (block == BlocksTC.hungryChest) {
/* 852 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.temc, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/* 854 */       else if (block == BlocksTC.arcaneWorkbenchCharger) {
/* 855 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tawc, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/* 857 */       else if (block == BlocksTC.bellows) {
/* 858 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tbell, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/* 860 */       else if (block == BlocksTC.infusionMatrix) {
/* 861 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tmatrix, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/* 863 */       else if (block == BlocksTC.arcaneBore) {
/* 864 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tbore, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/* 866 */       else if (block == BlocksTC.arcaneBoreBase) {
/* 867 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tborebase, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/* 869 */       else if (block == BlocksTC.crystallizer) {
/* 870 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tcrystalizer, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/* 872 */       else if (block == BlocksTC.centrifuge) {
/* 873 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tcentrifuge, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/* 875 */       else if (block == BlocksTC.nodeStabilizer) {
/* 876 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.tnodestab, 0.0D, 0.0D, 0.0D, 0.0F);
/*     */       }
/*     */       else {
/* 879 */         this.chainedTEISR.func_179022_a(itemStack);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/ClientProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */