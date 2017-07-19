/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ public class PacketFXBlockArc implements net.minecraftforge.fml.common.network.simpleimpl.IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketFXBlockArc, net.minecraftforge.fml.common.network.simpleimpl.IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private float tx;
/*    */   private float ty;
/*    */   private float tz;
/*    */   private float r;
/*    */   private float g;
/*    */   private float b;
/*    */   
/*    */   public PacketFXBlockArc() {}
/*    */   
/*    */   public PacketFXBlockArc(net.minecraft.util.BlockPos pos, net.minecraft.entity.Entity source, float r, float g, float b)
/*    */   {
/* 21 */     this.x = pos.func_177958_n();
/* 22 */     this.y = pos.func_177956_o();
/* 23 */     this.z = pos.func_177952_p();
/* 24 */     this.tx = ((float)source.field_70165_t);
/* 25 */     this.ty = ((float)(source.func_174813_aQ().field_72338_b + source.field_70131_O / 2.0F));
/* 26 */     this.tz = ((float)source.field_70161_v);
/* 27 */     this.r = r;
/* 28 */     this.g = g;
/* 29 */     this.b = b;
/*    */   }
/*    */   
/*    */   public PacketFXBlockArc(net.minecraft.util.BlockPos pos, net.minecraft.util.BlockPos pos2, float r, float g, float b)
/*    */   {
/* 34 */     this.x = pos.func_177958_n();
/* 35 */     this.y = pos.func_177956_o();
/* 36 */     this.z = pos.func_177952_p();
/* 37 */     this.tx = (pos2.func_177958_n() + 0.5F);
/* 38 */     this.ty = (pos2.func_177956_o() + 0.5F);
/* 39 */     this.tz = (pos2.func_177952_p() + 0.5F);
/* 40 */     this.r = r;
/* 41 */     this.g = g;
/* 42 */     this.b = b;
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 48 */     buffer.writeInt(this.x);
/* 49 */     buffer.writeInt(this.y);
/* 50 */     buffer.writeInt(this.z);
/* 51 */     buffer.writeFloat(this.tx);
/* 52 */     buffer.writeFloat(this.ty);
/* 53 */     buffer.writeFloat(this.tz);
/* 54 */     buffer.writeFloat(this.r);
/* 55 */     buffer.writeFloat(this.g);
/* 56 */     buffer.writeFloat(this.b);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 61 */     this.x = buffer.readInt();
/* 62 */     this.y = buffer.readInt();
/* 63 */     this.z = buffer.readInt();
/* 64 */     this.tx = buffer.readFloat();
/* 65 */     this.ty = buffer.readFloat();
/* 66 */     this.tz = buffer.readFloat();
/* 67 */     this.r = buffer.readFloat();
/* 68 */     this.g = buffer.readFloat();
/* 69 */     this.b = buffer.readFloat();
/*    */   }
/*    */   
/*    */   public net.minecraftforge.fml.common.network.simpleimpl.IMessage onMessage(PacketFXBlockArc message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 74 */     thaumcraft.common.Thaumcraft.proxy.getFX().arcLightning(message.tx, message.ty, message.tz, message.x + 0.5D, message.y + 0.5D, message.z + 0.5D, message.r, message.g, message.b, 0.5F);
/*    */     
/*    */ 
/*    */ 
/* 78 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/fx/PacketFXBlockArc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */