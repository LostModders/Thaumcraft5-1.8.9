/*    */ package thaumcraft.common.lib.network;
/*    */ 
/*    */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockBamf;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockMist;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXInfusionSource;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXPollute;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXScanSource;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXShield;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXSlash;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXSonic;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXWispZap;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXZap;
/*    */ import thaumcraft.common.lib.network.misc.PacketAuraToClient;
/*    */ import thaumcraft.common.lib.network.misc.PacketBiomeChange;
/*    */ import thaumcraft.common.lib.network.misc.PacketBoreDig;
/*    */ import thaumcraft.common.lib.network.misc.PacketConfig;
/*    */ import thaumcraft.common.lib.network.misc.PacketFlyToServer;
/*    */ import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;
/*    */ import thaumcraft.common.lib.network.misc.PacketItemKeyToServer;
/*    */ import thaumcraft.common.lib.network.misc.PacketMiscEvent;
/*    */ import thaumcraft.common.lib.network.misc.PacketNote;
/*    */ import thaumcraft.common.lib.network.misc.PacketSealToClient;
/*    */ import thaumcraft.common.lib.network.misc.PacketStartGolemCraftToServer;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketAspectCombinationToServer;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketAspectPlaceToServer;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketPlayerCompleteToServer;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncAll;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncResearch;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncResearchFlags;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
/*    */ 
/*    */ 
/*    */ public class PacketHandler
/*    */ {
/* 42 */   public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("Thaumcraft".toLowerCase());
/*    */   
/*    */   public static void init()
/*    */   {
/* 46 */     int idx = 0;
/*    */     
/*    */ 
/* 49 */     INSTANCE.registerMessage(PacketBiomeChange.class, PacketBiomeChange.class, idx++, Side.CLIENT);
/* 50 */     INSTANCE.registerMessage(PacketConfig.class, PacketConfig.class, idx++, Side.CLIENT);
/* 51 */     INSTANCE.registerMessage(PacketMiscEvent.class, PacketMiscEvent.class, idx++, Side.CLIENT);
/* 52 */     INSTANCE.registerMessage(PacketStartGolemCraftToServer.class, PacketStartGolemCraftToServer.class, idx++, Side.SERVER);
/*    */     
/*    */ 
/* 55 */     INSTANCE.registerMessage(PacketSyncAll.class, PacketSyncAll.class, idx++, Side.CLIENT);
/* 56 */     INSTANCE.registerMessage(PacketSyncResearch.class, PacketSyncResearch.class, idx++, Side.CLIENT);
/* 57 */     INSTANCE.registerMessage(PacketResearchComplete.class, PacketResearchComplete.class, idx++, Side.CLIENT);
/* 58 */     INSTANCE.registerMessage(PacketAspectCombinationToServer.class, PacketAspectCombinationToServer.class, idx++, Side.SERVER);
/* 59 */     INSTANCE.registerMessage(PacketPlayerCompleteToServer.class, PacketPlayerCompleteToServer.class, idx++, Side.SERVER);
/* 60 */     INSTANCE.registerMessage(PacketAspectPlaceToServer.class, PacketAspectPlaceToServer.class, idx++, Side.SERVER);
/*    */     
/*    */ 
/* 63 */     INSTANCE.registerMessage(PacketAuraToClient.class, PacketAuraToClient.class, idx++, Side.CLIENT);
/* 64 */     INSTANCE.registerMessage(PacketSealToClient.class, PacketSealToClient.class, idx++, Side.CLIENT);
/* 65 */     INSTANCE.registerMessage(PacketBoreDig.class, PacketBoreDig.class, idx++, Side.CLIENT);
/* 66 */     INSTANCE.registerMessage(PacketNote.class, PacketNote.class, idx++, Side.CLIENT);
/* 67 */     INSTANCE.registerMessage(PacketSyncWarp.class, PacketSyncWarp.class, idx++, Side.CLIENT);
/* 68 */     INSTANCE.registerMessage(PacketWarpMessage.class, PacketWarpMessage.class, idx++, Side.CLIENT);
/* 69 */     INSTANCE.registerMessage(PacketNote.class, PacketNote.class, idx++, Side.SERVER);
/* 70 */     INSTANCE.registerMessage(PacketItemKeyToServer.class, PacketItemKeyToServer.class, idx++, Side.SERVER);
/* 71 */     INSTANCE.registerMessage(PacketFocusChangeToServer.class, PacketFocusChangeToServer.class, idx++, Side.SERVER);
/* 72 */     INSTANCE.registerMessage(PacketFlyToServer.class, PacketFlyToServer.class, idx++, Side.SERVER);
/* 73 */     INSTANCE.registerMessage(PacketSyncResearchFlags.class, PacketSyncResearchFlags.class, idx++, Side.SERVER);
/* 74 */     INSTANCE.registerMessage(PacketSyncResearchFlags.class, PacketSyncResearchFlags.class, idx++, Side.CLIENT);
/*    */     
/*    */ 
/* 77 */     INSTANCE.registerMessage(PacketFXPollute.class, PacketFXPollute.class, idx++, Side.CLIENT);
/* 78 */     INSTANCE.registerMessage(PacketFXBlockBamf.class, PacketFXBlockBamf.class, idx++, Side.CLIENT);
/* 79 */     INSTANCE.registerMessage(PacketFXBlockMist.class, PacketFXBlockMist.class, idx++, Side.CLIENT);
/* 80 */     INSTANCE.registerMessage(PacketFXBlockArc.class, PacketFXBlockArc.class, idx++, Side.CLIENT);
/* 81 */     INSTANCE.registerMessage(PacketFXEssentiaSource.class, PacketFXEssentiaSource.class, idx++, Side.CLIENT);
/* 82 */     INSTANCE.registerMessage(PacketFXInfusionSource.class, PacketFXInfusionSource.class, idx++, Side.CLIENT);
/* 83 */     INSTANCE.registerMessage(PacketFXShield.class, PacketFXShield.class, idx++, Side.CLIENT);
/* 84 */     INSTANCE.registerMessage(PacketFXSonic.class, PacketFXSonic.class, idx++, Side.CLIENT);
/* 85 */     INSTANCE.registerMessage(PacketFXWispZap.class, PacketFXWispZap.class, idx++, Side.CLIENT);
/* 86 */     INSTANCE.registerMessage(PacketFXZap.class, PacketFXZap.class, idx++, Side.CLIENT);
/* 87 */     INSTANCE.registerMessage(PacketFXSlash.class, PacketFXSlash.class, idx++, Side.CLIENT);
/* 88 */     INSTANCE.registerMessage(PacketFXScanSource.class, PacketFXScanSource.class, idx++, Side.CLIENT);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/PacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */