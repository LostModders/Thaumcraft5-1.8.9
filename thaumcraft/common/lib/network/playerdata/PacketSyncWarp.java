/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ 
/*    */ public class PacketSyncWarp implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketSyncWarp, IMessage>
/*    */ {
/*    */   protected String name;
/* 17 */   protected int data = 0;
/* 18 */   protected byte type = 0;
/*    */   
/*    */   public PacketSyncWarp() {}
/*    */   
/*    */   public PacketSyncWarp(EntityPlayer player, byte type) {
/* 23 */     this.name = player.func_70005_c_();
/* 24 */     if (type == 0) this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(this.name);
/* 25 */     if (type == 1) this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(this.name);
/* 26 */     if (type == 2) this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(this.name);
/* 27 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 32 */     ByteBufUtils.writeUTF8String(buffer, this.name);
/* 33 */     buffer.writeInt(this.data);
/* 34 */     buffer.writeByte(this.type);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 39 */     this.name = ByteBufUtils.readUTF8String(buffer);
/* 40 */     this.data = buffer.readInt();
/* 41 */     this.type = buffer.readByte();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncWarp message, MessageContext ctx)
/*    */   {
/* 47 */     if (message.type == 0) {
/* 48 */       Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(message.name, message.data);
/*    */     }
/* 50 */     else if (message.type == 1) {
/* 51 */       Thaumcraft.proxy.getPlayerKnowledge().setWarpSticky(message.name, message.data);
/*    */     } else {
/* 53 */       Thaumcraft.proxy.getPlayerKnowledge().setWarpTemp(message.name, message.data);
/*    */     }
/*    */     
/* 56 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncWarp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */