/*     */ package thaumcraft.common;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.RegistryDefaulted;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*     */ import net.minecraftforge.fml.common.Mod.Instance;
/*     */ import net.minecraftforge.fml.common.SidedProxy;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLInterModComms;
/*     */ import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
/*     */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventBus;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.OreDictionaryEntries;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.lib.models.ModelRegistrationHelper;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigAspects;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.config.ConfigRecipes;
/*     */ import thaumcraft.common.config.ConfigResearch;
/*     */ import thaumcraft.common.items.BehaviorDispenseAlumetum;
/*     */ import thaumcraft.common.lib.CreativeTabThaumcraft;
/*     */ import thaumcraft.common.lib.InternalMethodHandler;
/*     */ import thaumcraft.common.lib.events.ChunkEvents;
/*     */ import thaumcraft.common.lib.events.CommandThaumcraft;
/*     */ import thaumcraft.common.lib.events.CraftingEvents;
/*     */ import thaumcraft.common.lib.events.EntityEvents;
/*     */ import thaumcraft.common.lib.events.PlayerEvents;
/*     */ import thaumcraft.common.lib.events.ServerTickEventsFML;
/*     */ import thaumcraft.common.lib.events.ToolEvents;
/*     */ import thaumcraft.common.lib.events.WorldEvents;
/*     */ import thaumcraft.common.lib.network.EventHandlerNetwork;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*     */ import thaumcraft.common.lib.world.dim.WorldProviderOuter;
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
/*     */ @Mod(modid="Thaumcraft", name="Thaumcraft", version="5.2.4", guiFactory="thaumcraft.client.ThaumcraftGuiFactory", dependencies="required-after:Forge@[11.15.1.1738,);required-after:Baubles@[1.1.3.0,)", acceptedMinecraftVersions="[1.8.9]")
/*     */ public class Thaumcraft
/*     */ {
/*     */   public static final String MODID = "Thaumcraft";
/*     */   public static final String MODNAME = "Thaumcraft";
/*     */   public static final String VERSION = "5.2.4";
/*     */   @SidedProxy(clientSide="thaumcraft.client.ClientProxy", serverSide="thaumcraft.common.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */   @Mod.Instance("Thaumcraft")
/*     */   public static Thaumcraft instance;
/*     */   public WorldEvents worldEvents;
/*     */   public ChunkEvents chunkEvents;
/*     */   public ServerTickEventsFML serverTickEvents;
/*     */   public CraftingEvents craftingEvents;
/*     */   public EventHandlerNetwork networkEventHandler;
/*     */   ResearchManager researchManager;
/*     */   public ThaumcraftWorldGenerator worldGen;
/*     */   public PlayerEvents playerEvents;
/*     */   public ToolEvents toolEvents;
/*     */   public EntityEvents entityEvents;
/*     */   public static ModelRegistrationHelper modelRegistrationHelper;
/*     */   public File modDir;
/* 101 */   public static final Logger log = LogManager.getLogger("THAUMCRAFT");
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/* 105 */     event.getModMetadata().version = "5.2.4";
/* 106 */     this.modDir = event.getModConfigurationDirectory();
/*     */     try
/*     */     {
/* 109 */       Config.initialize(event.getSuggestedConfigurationFile());
/*     */     } catch (Exception e) {
/* 111 */       log.error("Thaumcraft has a problem loading it's configuration");
/*     */     } finally {
/* 113 */       if (Config.config != null) { Config.save();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 122 */     thaumcraft.api.ThaumcraftApi.internalMethods = new InternalMethodHandler();
/*     */     
/*     */ 
/*     */ 
/* 126 */     this.worldEvents = new WorldEvents();
/* 127 */     this.chunkEvents = new ChunkEvents();
/* 128 */     this.serverTickEvents = new ServerTickEventsFML();
/* 129 */     this.craftingEvents = new CraftingEvents();
/* 130 */     this.playerEvents = new PlayerEvents();
/* 131 */     this.toolEvents = new ToolEvents();
/* 132 */     this.entityEvents = new EntityEvents();
/*     */     
/* 134 */     proxy.playerKnowledge = new PlayerKnowledge();
/* 135 */     proxy.researchManager = new ResearchManager();
/*     */     
/*     */ 
/*     */ 
/* 139 */     PacketHandler.init();
/* 140 */     this.networkEventHandler = new EventHandlerNetwork();
/* 141 */     MinecraftForge.EVENT_BUS.register(this.networkEventHandler);
/*     */     
/*     */ 
/*     */ 
/* 145 */     MinecraftForge.EVENT_BUS.register(this.serverTickEvents);
/*     */     
/* 147 */     MinecraftForge.EVENT_BUS.register(this.worldEvents);
/* 148 */     MinecraftForge.TERRAIN_GEN_BUS.register(this.worldEvents);
/*     */     
/* 150 */     MinecraftForge.EVENT_BUS.register(this.chunkEvents);
/*     */     
/* 152 */     MinecraftForge.EVENT_BUS.register(this.playerEvents);
/* 153 */     MinecraftForge.EVENT_BUS.register(this.entityEvents);
/* 154 */     MinecraftForge.EVENT_BUS.register(this.toolEvents);
/*     */     
/* 156 */     GameRegistry.registerFuelHandler(this.craftingEvents);
/* 157 */     MinecraftForge.EVENT_BUS.register(this.craftingEvents);
/*     */     
/* 159 */     GameRegistry.registerWorldGenerator(this.worldGen = new ThaumcraftWorldGenerator(), 0);
/*     */     
/*     */ 
/*     */ 
/* 163 */     Config.save();
/* 164 */     ConfigBlocks.init();
/* 165 */     ConfigItems.preInit();
/* 166 */     ConfigItems.initSeals();
/* 167 */     OreDictionaryEntries.initializeOreDictionary();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 176 */     proxy.registerHandlers();
/* 177 */     this.worldGen.initialize();
/*     */     
/* 179 */     MinecraftForge.EVENT_BUS.register(instance);
/* 180 */     FMLCommonHandler.instance().bus().register(instance);
/*     */     
/* 182 */     BiomeHandler.registerBiomes();
/*     */     
/* 184 */     proxy.registerDisplayInformationPreInit();
/*     */   }
/*     */   
/* 187 */   public static boolean isHalloween = false;
/*     */   
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void init(FMLInitializationEvent evt)
/*     */   {
/* 193 */     ConfigEntities.init();
/*     */     
/* 195 */     ConfigItems.init();
/*     */     
/* 197 */     proxy.registerDisplayInformationInit();
/*     */     
/* 199 */     BlockDispenser.field_149943_a.func_82595_a(ItemsTC.alumentum, new BehaviorDispenseAlumetum());
/*     */     
/*     */ 
/* 202 */     NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
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
/* 214 */     proxy.registerKeyBindings();
/*     */     
/* 216 */     DimensionManager.registerProviderType(Config.dimensionOuterId, WorldProviderOuter.class, false);
/* 217 */     DimensionManager.registerDimension(Config.dimensionOuterId, Config.dimensionOuterId);
/*     */   }
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void postInit(FMLPostInitializationEvent evt)
/*     */   {
/* 223 */     Config.initPotions();
/* 224 */     ConfigEntities.initEntitySpawns();
/* 225 */     Config.initModCompatibility();
/* 226 */     ConfigItems.postInit();
/* 227 */     ConfigRecipes.init();
/* 228 */     ConfigAspects.init();
/* 229 */     ConfigResearch.init();
/* 230 */     Config.initLoot();
/* 231 */     Config.initMisc();
/*     */     
/*     */ 
/* 234 */     ImmutableList<FMLInterModComms.IMCMessage> messages = FMLInterModComms.fetchRuntimeMessages(this);
/* 235 */     for (FMLInterModComms.IMCMessage message : messages) {
/* 236 */       if ((message.key.equals("portableHoleBlacklist")) && (message.isStringMessage())) {
/* 237 */         BlockUtils.portableHoleBlackList.add(message.getStringValue());
/*     */       }
/* 239 */       if ((message.key.equals("harvestStandardCrop")) && (message.isItemStackMessage())) {
/* 240 */         ItemStack crop = message.getItemStackValue();
/* 241 */         CropUtils.addStandardCrop(crop, crop.func_77952_i());
/*     */       }
/* 243 */       if ((message.key.equals("harvestClickableCrop")) && (message.isItemStackMessage())) {
/* 244 */         ItemStack crop = message.getItemStackValue();
/* 245 */         CropUtils.addClickableCrop(crop, crop.func_77952_i());
/*     */       }
/* 247 */       if ((message.key.equals("harvestStackedCrop")) && (message.isItemStackMessage())) {
/* 248 */         ItemStack crop = message.getItemStackValue();
/* 249 */         CropUtils.addStackedCrop(crop, crop.func_77952_i());
/*     */       }
/*     */       
/* 252 */       if ((message.key.equals("nativeCluster")) && (message.isStringMessage())) {
/* 253 */         String[] t = message.getStringValue().split(",");
/* 254 */         if ((t != null) && (t.length == 5)) {
/*     */           try {
/* 256 */             ItemStack ore = new ItemStack(Item.func_150899_d(Integer.parseInt(t[0])), 1, Integer.parseInt(t[1]));
/* 257 */             ItemStack cluster = new ItemStack(Item.func_150899_d(Integer.parseInt(t[2])), 1, Integer.parseInt(t[3]));
/* 258 */             Utils.addSpecialMiningResult(ore, cluster, Float.parseFloat(t[4]));
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/* 263 */       if ((message.key.equals("lampBlacklist")) && (message.isItemStackMessage())) {
/* 264 */         ItemStack crop = message.getItemStackValue();
/* 265 */         CropUtils.blacklistLamp(crop, crop.func_77952_i());
/*     */       }
/* 267 */       if ((message.key.equals("dimensionBlacklist")) && (message.isStringMessage())) {
/* 268 */         String[] t = message.getStringValue().split(":");
/* 269 */         if ((t != null) && (t.length == 2)) {
/*     */           try {
/* 271 */             BiomeHandler.addDimBlacklist(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/* 276 */       if ((message.key.equals("biomeBlacklist")) && (message.isStringMessage())) {
/* 277 */         String[] t = message.getStringValue().split(":");
/* 278 */         if ((t != null) && (t.length == 2) && (BiomeGenBase.func_150568_d(Integer.parseInt(t[0])) != null)) {
/*     */           try {
/* 280 */             BiomeHandler.addBiomeBlacklist(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/* 285 */       if ((message.key.equals("championWhiteList")) && (message.isStringMessage())) {
/*     */         try
/*     */         {
/* 288 */           String[] t = message.getStringValue().split(":");
/* 289 */           Class oclass = (Class)EntityList.field_75625_b.get(t[0]);
/* 290 */           if (oclass != null)
/*     */           {
/*     */ 
/* 293 */             ConfigEntities.championModWhitelist.put(oclass, Integer.valueOf(Integer.parseInt(t[1])));
/*     */           }
/*     */         } catch (Exception e) {
/* 296 */           log.error("Failed to Whitelist [" + message.getStringValue() + "] with [ championWhiteList ] message.");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void serverLoad(FMLServerStartingEvent event)
/*     */   {
/* 306 */     event.registerServerCommand(new CommandThaumcraft());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
/* 311 */     if (eventArgs.modID.equals("Thaumcraft")) {
/* 312 */       Config.syncConfigurable();
/* 313 */       if ((Config.config != null) && (Config.config.hasChanged())) {
/* 314 */         Config.save();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 319 */   public static CreativeTabs tabTC = new CreativeTabThaumcraft(CreativeTabs.getNextID(), "thaumcraft");
/*     */   
/* 321 */   public boolean aspectShift = false;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/Thaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */