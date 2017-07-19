/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import thaumcraft.common.config.Config;
/*    */ 
/*    */ public class PacketConfig implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketConfig, IMessage>
/*    */ {
/*    */   boolean b1;
/*    */   boolean b2;
/*    */   boolean b3;
/*    */   boolean b5;
/*    */   byte by1;
/*    */   byte by2;
/*    */   int ab;
/*    */   
/*    */   public void toBytes(ByteBuf dos)
/*    */   {
/* 19 */     dos.writeBoolean(Config.allowCheatSheet);
/* 20 */     dos.writeBoolean(Config.wardedStone);
/* 21 */     dos.writeBoolean(Config.allowMirrors);
/* 22 */     dos.writeBoolean(Config.wuss);
/* 23 */     dos.writeByte(Config.researchDifficulty);
/* 24 */     dos.writeByte(Config.researchAmount);
/* 25 */     dos.writeInt(Config.CAURABASE);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf dat)
/*    */   {
/* 30 */     this.b1 = dat.readBoolean();
/* 31 */     this.b2 = dat.readBoolean();
/* 32 */     this.b3 = dat.readBoolean();
/* 33 */     this.b5 = dat.readBoolean();
/* 34 */     this.by1 = dat.readByte();
/* 35 */     this.by2 = dat.readByte();
/* 36 */     this.ab = dat.readInt();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketConfig message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 41 */     Config.allowCheatSheet = message.b1;
/* 42 */     Config.wardedStone = message.b2;
/* 43 */     Config.allowMirrors = message.b3;
/* 44 */     Config.wuss = message.b5;
/* 45 */     Config.researchDifficulty = message.by1;
/* 46 */     Config.researchAmount = message.by2;
/* 47 */     Config.AURABASE = message.ab;
/* 48 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/misc/PacketConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */