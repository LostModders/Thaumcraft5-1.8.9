/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class PacketWarpMessage implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketWarpMessage, IMessage>
/*    */ {
/* 16 */   protected int data = 0;
/* 17 */   protected byte type = 0;
/*    */   
/*    */   public PacketWarpMessage() {}
/*    */   
/*    */   public PacketWarpMessage(EntityPlayer player, byte type, int change)
/*    */   {
/* 23 */     this.data = change;
/* 24 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     buffer.writeInt(this.data);
/* 30 */     buffer.writeByte(this.type);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 35 */     this.data = buffer.readInt();
/* 36 */     this.type = buffer.readByte();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketWarpMessage message, MessageContext ctx)
/*    */   {
/* 42 */     if (message.data != 0)
/* 43 */       Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketWarpMessage.this.processMessage(message); }
/*    */       });
/* 45 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketWarpMessage message) {
/* 50 */     if ((message.type == 0) && (message.data > 0)) {
/* 51 */       String text = StatCollector.func_74838_a("tc.addwarp");
/* 52 */       if (message.data < 0) {
/* 53 */         text = StatCollector.func_74838_a("tc.removewarp");
/*    */       } else {
/* 55 */         Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:whispers", 0.5F, 1.0F);
/*    */       }
/*    */     }
/* 58 */     else if (message.type == 1) {
/* 59 */       String text = StatCollector.func_74838_a("tc.addwarpsticky");
/* 60 */       if (message.data < 0) {
/* 61 */         text = StatCollector.func_74838_a("tc.removewarpsticky");
/*    */       } else {
/* 63 */         Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:whispers", 0.5F, 1.0F);
/*    */       }
/* 65 */       Minecraft.func_71410_x().field_71439_g.func_145747_a(new ChatComponentText(text));
/*    */     }
/* 67 */     else if (message.data > 0) {
/* 68 */       String text = StatCollector.func_74838_a("tc.addwarptemp");
/* 69 */       if (message.data < 0) {
/* 70 */         text = StatCollector.func_74838_a("tc.removewarptemp");
/*    */       }
/* 72 */       Minecraft.func_71410_x().field_71439_g.func_145747_a(new ChatComponentText(text));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/playerdata/PacketWarpMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */