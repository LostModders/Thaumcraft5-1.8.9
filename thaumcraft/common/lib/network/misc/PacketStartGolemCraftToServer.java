/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.network.NetHandlerPlayServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.tiles.crafting.TileGolemBuilder;
/*    */ 
/*    */ public class PacketStartGolemCraftToServer implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketStartGolemCraftToServer, IMessage>
/*    */ {
/*    */   private int dim;
/*    */   private int playerid;
/*    */   private long pos;
/*    */   private long golem;
/*    */   
/*    */   public PacketStartGolemCraftToServer() {}
/*    */   
/*    */   public PacketStartGolemCraftToServer(EntityPlayer player, BlockPos pos, long golem)
/*    */   {
/* 23 */     this.dim = player.field_70170_p.field_73011_w.func_177502_q();
/* 24 */     this.playerid = player.func_145782_y();
/* 25 */     this.pos = pos.func_177986_g();
/* 26 */     this.golem = golem;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 31 */     buffer.writeInt(this.dim);
/* 32 */     buffer.writeInt(this.playerid);
/* 33 */     buffer.writeLong(this.pos);
/* 34 */     buffer.writeLong(this.golem);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 39 */     this.dim = buffer.readInt();
/* 40 */     this.playerid = buffer.readInt();
/* 41 */     this.pos = buffer.readLong();
/* 42 */     this.golem = buffer.readLong();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketStartGolemCraftToServer message, MessageContext ctx)
/*    */   {
/* 47 */     World world = net.minecraftforge.common.DimensionManager.getWorld(message.dim);
/* 48 */     if ((world == null) || ((ctx.getServerHandler().field_147369_b != null) && (ctx.getServerHandler().field_147369_b.func_145782_y() != message.playerid))) { return null;
/*    */     }
/* 50 */     net.minecraft.entity.Entity player = world.func_73045_a(message.playerid);
/* 51 */     BlockPos bp = BlockPos.func_177969_a(message.pos);
/*    */     
/* 53 */     if ((player != null) && ((player instanceof EntityPlayer)) && (bp != null)) {
/* 54 */       net.minecraft.tileentity.TileEntity te = world.func_175625_s(bp);
/* 55 */       if ((te != null) && ((te instanceof TileGolemBuilder))) {
/* 56 */         ((TileGolemBuilder)te).startCraft(message.golem, (EntityPlayer)player);
/*    */       }
/*    */     }
/* 59 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/misc/PacketStartGolemCraftToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */