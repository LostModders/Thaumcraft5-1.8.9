/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXPollute implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketFXPollute, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private byte amount;
/*    */   
/*    */   public PacketFXPollute() {}
/*    */   
/*    */   public PacketFXPollute(BlockPos pos, int amt)
/*    */   {
/* 19 */     this.x = pos.func_177958_n();
/* 20 */     this.y = pos.func_177956_o();
/* 21 */     this.z = pos.func_177952_p();
/* 22 */     this.amount = ((byte)amt);
/*    */   }
/*    */   
/*    */   public PacketFXPollute(BlockPos pos, int amt, boolean vary) {
/* 26 */     this(pos, amt);
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 32 */     buffer.writeInt(this.x);
/* 33 */     buffer.writeInt(this.y);
/* 34 */     buffer.writeInt(this.z);
/* 35 */     buffer.writeByte(this.amount);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 40 */     this.x = buffer.readInt();
/* 41 */     this.y = buffer.readInt();
/* 42 */     this.z = buffer.readInt();
/* 43 */     this.amount = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketFXPollute message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 48 */     for (int a = 0; a < Math.min(Thaumcraft.proxy.getFX().particleCount(20), message.amount); a++)
/* 49 */       Thaumcraft.proxy.getFX().drawPollutionParticles(new BlockPos(message.x, message.y, message.z));
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/fx/PacketFXPollute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */