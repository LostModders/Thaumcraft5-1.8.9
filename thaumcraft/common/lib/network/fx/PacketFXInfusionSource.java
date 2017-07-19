/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.tiles.crafting.TileInfusionMatrix;
/*    */ 
/*    */ public class PacketFXInfusionSource implements net.minecraftforge.fml.common.network.simpleimpl.IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketFXInfusionSource, net.minecraftforge.fml.common.network.simpleimpl.IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private byte dx;
/*    */   private byte dy;
/*    */   private byte dz;
/*    */   private int color;
/*    */   
/*    */   public PacketFXInfusionSource() {}
/*    */   
/*    */   public PacketFXInfusionSource(BlockPos pos, byte dx, byte dy, byte dz, int color)
/*    */   {
/* 24 */     this.x = pos.func_177958_n();
/* 25 */     this.y = pos.func_177956_o();
/* 26 */     this.z = pos.func_177952_p();
/* 27 */     this.dx = dx;
/* 28 */     this.dy = dy;
/* 29 */     this.dz = dz;
/* 30 */     this.color = color;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 35 */     buffer.writeInt(this.x);
/* 36 */     buffer.writeInt(this.y);
/* 37 */     buffer.writeInt(this.z);
/* 38 */     buffer.writeInt(this.color);
/* 39 */     buffer.writeByte(this.dx);
/* 40 */     buffer.writeByte(this.dy);
/* 41 */     buffer.writeByte(this.dz);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 46 */     this.x = buffer.readInt();
/* 47 */     this.y = buffer.readInt();
/* 48 */     this.z = buffer.readInt();
/* 49 */     this.color = buffer.readInt();
/* 50 */     this.dx = buffer.readByte();
/* 51 */     this.dy = buffer.readByte();
/* 52 */     this.dz = buffer.readByte();
/*    */   }
/*    */   
/*    */   public net.minecraftforge.fml.common.network.simpleimpl.IMessage onMessage(PacketFXInfusionSource message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 57 */     int tx = message.x - message.dx;
/* 58 */     int ty = message.y - message.dy;
/* 59 */     int tz = message.z - message.dz;
/* 60 */     String key = tx + ":" + ty + ":" + tz + ":" + message.color;
/* 61 */     net.minecraft.tileentity.TileEntity tile = Thaumcraft.proxy.getClientWorld().func_175625_s(new BlockPos(message.x, message.y, message.z));
/* 62 */     if ((tile != null) && ((tile instanceof TileInfusionMatrix))) {
/* 63 */       int count = 15;
/* 64 */       if ((Thaumcraft.proxy.getClientWorld().func_175625_s(new BlockPos(tx, ty, tz)) != null) && ((Thaumcraft.proxy.getClientWorld().func_175625_s(new BlockPos(tx, ty, tz)) instanceof thaumcraft.common.tiles.crafting.TilePedestal)))
/*    */       {
/* 66 */         count = 60; }
/* 67 */       TileInfusionMatrix is = (TileInfusionMatrix)tile;
/* 68 */       if (is.sourceFX.containsKey(key)) {
/* 69 */         thaumcraft.common.tiles.crafting.TileInfusionMatrix.SourceFX sf = (thaumcraft.common.tiles.crafting.TileInfusionMatrix.SourceFX)is.sourceFX.get(key);
/* 70 */         sf.ticks = count;
/* 71 */         is.sourceFX.put(key, sf);
/*    */       } else {
/* 73 */         TileInfusionMatrix tmp253_251 = is;tmp253_251.getClass();is.sourceFX.put(key, new thaumcraft.common.tiles.crafting.TileInfusionMatrix.SourceFX(tmp253_251, new BlockPos(tx, ty, tz), count, message.color));
/*    */       }
/*    */     }
/*    */     
/* 77 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/fx/PacketFXInfusionSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */