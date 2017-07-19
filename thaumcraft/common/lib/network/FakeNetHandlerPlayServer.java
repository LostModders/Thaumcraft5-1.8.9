/*    */ package thaumcraft.common.lib.network;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.network.NetHandlerPlayServer;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C00PacketKeepAlive;
/*    */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ import net.minecraft.network.play.client.C0CPacketInput;
/*    */ import net.minecraft.network.play.client.C0DPacketCloseWindow;
/*    */ import net.minecraft.network.play.client.C0EPacketClickWindow;
/*    */ import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
/*    */ import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
/*    */ import net.minecraft.network.play.client.C11PacketEnchantItem;
/*    */ import net.minecraft.network.play.client.C12PacketUpdateSign;
/*    */ import net.minecraft.network.play.client.C14PacketTabComplete;
/*    */ import net.minecraft.network.play.client.C15PacketClientSettings;
/*    */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*    */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FakeNetHandlerPlayServer
/*    */   extends NetHandlerPlayServer
/*    */ {
/*    */   public FakeNetHandlerPlayServer(MinecraftServer server, NetworkManager networkManagerIn, EntityPlayerMP playerIn)
/*    */   {
/* 39 */     super(server, networkManagerIn, playerIn);
/*    */   }
/*    */   
/*    */   public void func_73660_a() {}
/*    */   
/*    */   public void func_147360_c(String reason) {}
/*    */   
/*    */   public void func_147358_a(C0CPacketInput packetIn) {}
/*    */   
/*    */   public void func_147347_a(C03PacketPlayer packetIn) {}
/*    */   
/*    */   public void func_147359_a(Packet packetIn) {}
/*    */   
/*    */   public void func_147340_a(C02PacketUseEntity packetIn) {}
/*    */   
/*    */   public void func_147342_a(C16PacketClientStatus packetIn) {}
/*    */   
/*    */   public void func_147356_a(C0DPacketCloseWindow packetIn) {}
/*    */   
/*    */   public void func_147351_a(C0EPacketClickWindow packetIn) {}
/*    */   
/*    */   public void func_147338_a(C11PacketEnchantItem packetIn) {}
/*    */   
/*    */   public void func_147344_a(C10PacketCreativeInventoryAction packetIn) {}
/*    */   
/*    */   public void func_147339_a(C0FPacketConfirmTransaction packetIn) {}
/*    */   
/*    */   public void func_147343_a(C12PacketUpdateSign packetIn) {}
/*    */   
/*    */   public void func_147353_a(C00PacketKeepAlive packetIn) {}
/*    */   
/*    */   public void func_147341_a(C14PacketTabComplete packetIn) {}
/*    */   
/*    */   public void func_147352_a(C15PacketClientSettings packetIn) {}
/*    */   
/*    */   public void func_147349_a(C17PacketCustomPayload packetIn) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/FakeNetHandlerPlayServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */