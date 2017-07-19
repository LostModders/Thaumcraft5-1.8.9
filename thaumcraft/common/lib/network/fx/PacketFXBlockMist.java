/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXBlockMist implements IMessage, IMessageHandler<PacketFXBlockMist, IMessage>
/*    */ {
/*    */   private long loc;
/*    */   private int color;
/*    */   
/*    */   public PacketFXBlockMist() {}
/*    */   
/*    */   public PacketFXBlockMist(BlockPos pos, int color)
/*    */   {
/* 19 */     this.loc = pos.func_177986_g();
/* 20 */     this.color = color;
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 26 */     buffer.writeLong(this.loc);
/* 27 */     buffer.writeInt(this.color);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 32 */     this.loc = buffer.readLong();
/* 33 */     this.color = buffer.readInt();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketFXBlockMist message, MessageContext ctx)
/*    */   {
/* 38 */     Thaumcraft.proxy.getFX().drawBlockMistParticles(BlockPos.func_177969_a(message.loc), message.color);
/* 39 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/fx/PacketFXBlockMist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */