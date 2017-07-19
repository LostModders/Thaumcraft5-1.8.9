/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketBiomeChange implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketBiomeChange, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int z;
/*    */   private short biome;
/*    */   
/*    */   public PacketBiomeChange() {}
/*    */   
/*    */   public PacketBiomeChange(int x, int z, short biome)
/*    */   {
/* 20 */     this.x = x;
/* 21 */     this.z = z;
/* 22 */     this.biome = biome;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 27 */     buffer.writeInt(this.x);
/* 28 */     buffer.writeInt(this.z);
/* 29 */     buffer.writeShort(this.biome);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 34 */     this.x = buffer.readInt();
/* 35 */     this.z = buffer.readInt();
/* 36 */     this.biome = buffer.readShort();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketBiomeChange message, MessageContext ctx)
/*    */   {
/* 41 */     Utils.setBiomeAt(Thaumcraft.proxy.getClientWorld(), new net.minecraft.util.BlockPos(message.x, 0, message.z), BiomeGenBase.func_150568_d(message.biome));
/* 42 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/misc/PacketBiomeChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */