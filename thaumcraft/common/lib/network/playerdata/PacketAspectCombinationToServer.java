/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.lib.research.ResearchNoteData;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ public class PacketAspectCombinationToServer implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketAspectCombinationToServer, IMessage>
/*    */ {
/*    */   private int dim;
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   Aspect aspect1;
/*    */   Aspect aspect2;
/*    */   
/*    */   public PacketAspectCombinationToServer() {}
/*    */   
/*    */   public PacketAspectCombinationToServer(EntityPlayer player, BlockPos pos, Aspect aspect1, Aspect aspect2)
/*    */   {
/* 28 */     this.dim = player.field_70170_p.field_73011_w.func_177502_q();
/* 29 */     this.x = pos.func_177958_n();
/* 30 */     this.y = pos.func_177956_o();
/* 31 */     this.z = pos.func_177952_p();
/* 32 */     this.aspect1 = aspect1;
/* 33 */     this.aspect2 = aspect2;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 38 */     buffer.writeInt(this.dim);
/* 39 */     buffer.writeInt(this.x);
/* 40 */     buffer.writeInt(this.y);
/* 41 */     buffer.writeInt(this.z);
/* 42 */     ByteBufUtils.writeUTF8String(buffer, this.aspect1.getTag());
/* 43 */     ByteBufUtils.writeUTF8String(buffer, this.aspect2.getTag());
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 48 */     this.dim = buffer.readInt();
/* 49 */     this.x = buffer.readInt();
/* 50 */     this.y = buffer.readInt();
/* 51 */     this.z = buffer.readInt();
/* 52 */     this.aspect1 = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
/* 53 */     this.aspect2 = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
/*    */   }
/*    */   
/*    */ 
/*    */   public IMessage onMessage(PacketAspectCombinationToServer message, MessageContext ctx)
/*    */   {
/* 59 */     World world = net.minecraftforge.common.DimensionManager.getWorld(message.dim);
/* 60 */     if (world == null) { return null;
/*    */     }
/* 62 */     BlockPos pos = new BlockPos(message.x, message.y, message.z);
/*    */     
/* 64 */     if ((message.aspect1 != null) && (message.aspect2 != null)) {
/* 65 */       Aspect combo = thaumcraft.common.lib.research.ResearchManager.getCombinationResult(message.aspect1, message.aspect2);
/*    */       
/* 67 */       net.minecraft.tileentity.TileEntity rt = world.func_175625_s(pos);
/* 68 */       if ((rt != null) && ((rt instanceof TileResearchTable))) {
/* 69 */         if (((TileResearchTable)rt).data == null) ((TileResearchTable)rt).gatherResults();
/* 70 */         if ((((TileResearchTable)rt).data != null) && (((TileResearchTable)rt).data.aspects != null) && (((TileResearchTable)rt).data.aspects.getAmount(message.aspect1) > 0) && (((TileResearchTable)rt).data.aspects.getAmount(message.aspect2) > 0))
/*    */         {
/*    */ 
/*    */ 
/* 74 */           ((TileResearchTable)rt).data.aspects.remove(message.aspect1, 1);
/* 75 */           ((TileResearchTable)rt).data.aspects.remove(message.aspect2, 1);
/* 76 */           ((TileResearchTable)rt).data.aspects.add(combo, 1);
/* 77 */           ((TileResearchTable)rt).updateNoteAndConsumeInk();
/*    */         }
/*    */       }
/*    */     }
/* 81 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/playerdata/PacketAspectCombinationToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */