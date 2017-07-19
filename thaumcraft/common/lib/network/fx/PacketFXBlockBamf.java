/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketFXBlockBamf implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketFXBlockBamf, IMessage>
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private int color;
/*    */   private byte flags;
/*    */   
/*    */   public PacketFXBlockBamf() {}
/*    */   
/*    */   public PacketFXBlockBamf(double x, double y, double z, int color, boolean sound, boolean flair, boolean upwards)
/*    */   {
/* 26 */     this.x = x;
/* 27 */     this.y = y;
/* 28 */     this.z = z;
/* 29 */     this.color = color;
/* 30 */     int f = 0;
/* 31 */     if (sound) f = Utils.setBit(f, 0);
/* 32 */     if (flair) f = Utils.setBit(f, 1);
/* 33 */     if (upwards) f = Utils.setBit(f, 2);
/* 34 */     this.flags = ((byte)f);
/*    */   }
/*    */   
/*    */   public PacketFXBlockBamf(BlockPos pos, int color, boolean sound, boolean flair, boolean upwards)
/*    */   {
/* 39 */     this.x = (pos.func_177958_n() + 0.5D);
/* 40 */     this.y = (pos.func_177956_o() + 0.5D);
/* 41 */     this.z = (pos.func_177952_p() + 0.5D);
/* 42 */     this.color = color;
/* 43 */     int f = 0;
/* 44 */     if (sound) f = Utils.setBit(f, 0);
/* 45 */     if (flair) f = Utils.setBit(f, 1);
/* 46 */     if (upwards) f = Utils.setBit(f, 2);
/* 47 */     this.flags = ((byte)f);
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 53 */     buffer.writeDouble(this.x);
/* 54 */     buffer.writeDouble(this.y);
/* 55 */     buffer.writeDouble(this.z);
/* 56 */     buffer.writeInt(this.color);
/* 57 */     buffer.writeByte(this.flags);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 62 */     this.x = buffer.readDouble();
/* 63 */     this.y = buffer.readDouble();
/* 64 */     this.z = buffer.readDouble();
/* 65 */     this.color = buffer.readInt();
/* 66 */     this.flags = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(final PacketFXBlockBamf message, MessageContext ctx)
/*    */   {
/* 71 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketFXBlockBamf.this.processMessage(message); } });
/* 72 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */   void processMessage(PacketFXBlockBamf message) {
/* 77 */     if (message.color != 55537) {
/* 78 */       Thaumcraft.proxy.getFX().drawBamf(message.x, message.y, message.z, message.color, Utils.getBit(message.flags, 0), Utils.getBit(message.flags, 1), Utils.getBit(message.flags, 2));
/*    */     } else {
/* 80 */       Thaumcraft.proxy.getFX().drawBamf(message.x, message.y, message.z, Utils.getBit(message.flags, 0), Utils.getBit(message.flags, 1), Utils.getBit(message.flags, 2));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/fx/PacketFXBlockBamf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */