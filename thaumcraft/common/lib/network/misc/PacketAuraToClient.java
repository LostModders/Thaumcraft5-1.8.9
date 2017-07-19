/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketAuraToClient
/*    */   implements IMessage, IMessageHandler<PacketAuraToClient, IMessage>
/*    */ {
/* 16 */   AspectList aura = new AspectList();
/*    */   
/*    */   public PacketAuraToClient() {}
/*    */   
/*    */   public PacketAuraToClient(AspectList al) {
/* 21 */     this.aura = al;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf dos)
/*    */   {
/* 26 */     if ((this.aura != null) && (this.aura.size() > 0)) {
/* 27 */       dos.writeByte(this.aura.size());
/* 28 */       for (Aspect a : this.aura.getAspects()) if (a != null) {
/* 29 */           ByteBufUtils.writeUTF8String(dos, a.getTag());
/* 30 */           dos.writeShort(this.aura.getAmount(a));
/*    */         }
/* 32 */     } else { dos.writeShort(0);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf dat) {
/* 37 */     short size = (short)dat.readByte();
/* 38 */     this.aura = new AspectList();
/* 39 */     for (int a = 0; a < size; a++) {
/* 40 */       String tag = ByteBufUtils.readUTF8String(dat);
/* 41 */       short amount = dat.readShort();
/* 42 */       this.aura.add(Aspect.getAspect(tag), amount);
/*    */     }
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketAuraToClient message, MessageContext ctx)
/*    */   {
/* 48 */     AspectList r = message.aura;
/* 49 */     thaumcraft.client.lib.HudHandler.currentAura = r.copy();
/*    */     
/* 51 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/misc/PacketAuraToClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */