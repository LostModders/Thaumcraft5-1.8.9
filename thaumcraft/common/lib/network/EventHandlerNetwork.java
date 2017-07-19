/*    */ package thaumcraft.common.lib.network;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
/*    */ import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
/*    */ import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncAll;
/*    */ 
/*    */ public class EventHandlerNetwork
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event)
/*    */   {
/* 26 */     Side side = FMLCommonHandler.instance().getEffectiveSide();
/* 27 */     if (side == Side.SERVER) {
/* 28 */       net.minecraft.entity.player.EntityPlayer p = event.player;
/* 29 */       PacketHandler.INSTANCE.sendTo(new PacketSyncAll(p), (EntityPlayerMP)p);
/* 30 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.misc.PacketConfig(), (EntityPlayerMP)p);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void clientLoggedIn(FMLNetworkEvent.ClientConnectedToServerEvent event)
/*    */   {
/* 37 */     if ((Thaumcraft.proxy.getClientWorld() != null) && (Minecraft.func_71410_x().field_71439_g != null)) {
/* 38 */       thaumcraft.client.gui.GuiResearchBrowser.completedResearch.put(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), new ArrayList());
/* 39 */       Thaumcraft.log.info("Resetting research to defaults.");
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void clientLogsOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
/*    */   {
/* 46 */     if (Thaumcraft.proxy.getClientWorld() != null) {
/* 47 */       Config.allowCheatSheet = Config.CallowCheatSheet;
/* 48 */       Config.wardedStone = Config.CwardedStone;
/* 49 */       Config.AURABASE = Config.CAURABASE;
/* 50 */       Config.allowMirrors = Config.CallowMirrors;
/* 51 */       Config.wuss = Config.Cwuss;
/* 52 */       Config.researchDifficulty = Config.CresearchDifficulty;
/* 53 */       Thaumcraft.log.info("Restoring client configs.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/EventHandlerNetwork.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */