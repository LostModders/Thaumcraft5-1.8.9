/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ 
/*    */ public class PacketSyncResearchFlags implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketSyncResearchFlags, IMessage>
/*    */ {
/*    */   String player;
/*    */   String key;
/*    */   byte flags;
/*    */   
/*    */   public PacketSyncResearchFlags() {}
/*    */   
/*    */   public PacketSyncResearchFlags(EntityPlayer player, String key)
/*    */   {
/* 20 */     this.player = player.func_70005_c_();
/* 21 */     this.key = key;
/* 22 */     Thaumcraft.proxy.getResearchManager(); if (ResearchManager.getResearchFlagsForPlayer(this.player).get(key) != null) {
/* 23 */       Thaumcraft.proxy.getResearchManager();this.flags = ((Byte)ResearchManager.getResearchFlagsForPlayer(this.player).get(key)).byteValue();
/*    */     }
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 28 */     ByteBufUtils.writeUTF8String(buffer, this.player);
/* 29 */     ByteBufUtils.writeUTF8String(buffer, this.key);
/* 30 */     buffer.writeByte(this.flags);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 35 */     this.player = ByteBufUtils.readUTF8String(buffer);
/* 36 */     this.key = ByteBufUtils.readUTF8String(buffer);
/* 37 */     this.flags = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketSyncResearchFlags message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 42 */     Thaumcraft.proxy.getResearchManager();ResearchManager.setResearchFlags(message.player, message.key, message.flags);
/* 43 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncResearchFlags.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */