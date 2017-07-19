/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityNote;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.tiles.devices.TileArcaneEar;
/*    */ 
/*    */ public class PacketNote implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketNote, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int dim;
/*    */   private byte note;
/*    */   
/*    */   public PacketNote() {}
/*    */   
/*    */   public PacketNote(int x, int y, int z, int dim)
/*    */   {
/* 26 */     this.x = x;
/* 27 */     this.y = y;
/* 28 */     this.z = z;
/* 29 */     this.dim = dim;
/* 30 */     this.note = -1;
/*    */   }
/*    */   
/* 33 */   public PacketNote(int x, int y, int z, int dim, byte note) { this.x = x;
/* 34 */     this.y = y;
/* 35 */     this.z = z;
/* 36 */     this.dim = dim;
/* 37 */     this.note = note;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 42 */     buffer.writeInt(this.x);
/* 43 */     buffer.writeInt(this.y);
/* 44 */     buffer.writeInt(this.z);
/* 45 */     buffer.writeInt(this.dim);
/* 46 */     buffer.writeByte(this.note);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 51 */     this.x = buffer.readInt();
/* 52 */     this.y = buffer.readInt();
/* 53 */     this.z = buffer.readInt();
/* 54 */     this.dim = buffer.readInt();
/* 55 */     this.note = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketNote message, MessageContext ctx)
/*    */   {
/* 60 */     if (ctx.side == net.minecraftforge.fml.relauncher.Side.CLIENT) {
/* 61 */       if (message.note >= 0) {
/* 62 */         TileEntity tile = Thaumcraft.proxy.getClientWorld().func_175625_s(new BlockPos(message.x, message.y, message.z));
/* 63 */         if ((tile != null) && ((tile instanceof TileEntityNote))) {
/* 64 */           ((TileEntityNote)tile).field_145879_a = message.note;
/*    */         }
/* 66 */         else if ((tile != null) && ((tile instanceof TileArcaneEar))) {
/* 67 */           ((TileArcaneEar)tile).note = message.note;
/*    */         }
/*    */       }
/*    */     }
/* 71 */     else if (message.note == -1) {
/* 72 */       World world = net.minecraftforge.common.DimensionManager.getWorld(message.dim);
/* 73 */       if (world == null) return null;
/* 74 */       TileEntity tile = world.func_175625_s(new BlockPos(message.x, message.y, message.z));
/* 75 */       byte note = -1;
/* 76 */       if ((tile != null) && ((tile instanceof TileEntityNote))) {
/* 77 */         note = ((TileEntityNote)tile).field_145879_a;
/*    */       }
/* 79 */       else if ((tile != null) && ((tile instanceof TileArcaneEar))) {
/* 80 */         note = ((TileArcaneEar)tile).note;
/*    */       }
/*    */       
/* 83 */       if (note >= 0) {
/* 84 */         thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new PacketNote(message.x, message.y, message.z, message.dim, note), new NetworkRegistry.TargetPoint(message.dim, message.x, message.y, message.z, 8.0D));
/*    */       }
/*    */     }
/*    */     
/* 88 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/misc/PacketNote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */