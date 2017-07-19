/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.config.Config;
/*    */ 
/*    */ public class PacketMiscEvent implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketMiscEvent, IMessage>
/*    */ {
/*    */   private short type;
/*    */   public static final short WARP_EVENT = 0;
/*    */   public static final short MIST_EVENT = 1;
/*    */   public static final short MIST_EVENT_SHORT = 2;
/*    */   
/*    */   public PacketMiscEvent() {}
/*    */   
/*    */   public PacketMiscEvent(short type)
/*    */   {
/* 23 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 28 */     buffer.writeShort(this.type);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 33 */     this.type = buffer.readShort();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketMiscEvent message, MessageContext ctx)
/*    */   {
/* 39 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketMiscEvent.this.processMessage(message); } });
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketMiscEvent message) {
/* 45 */     EntityPlayer p = Minecraft.func_71410_x().field_71439_g;
/* 46 */     switch (message.type) {
/*    */     case 0: 
/* 48 */       thaumcraft.client.lib.ShaderHandler.warpVignette = 100;
/* 49 */       if (!Config.nostress) p.field_70170_p.func_72980_b(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:heartbeat", 1.0F, 1.0F, false);
/*    */       break;
/*    */     case 1: 
/* 52 */       thaumcraft.client.lib.RenderEventHandler.fogFiddled = true;
/* 53 */       thaumcraft.client.lib.RenderEventHandler.fogDuration = 2400;
/* 54 */       break;
/*    */     case 2: 
/* 56 */       thaumcraft.client.lib.RenderEventHandler.fogFiddled = true;
/* 57 */       if (thaumcraft.client.lib.RenderEventHandler.fogDuration < 200) {
/* 58 */         thaumcraft.client.lib.RenderEventHandler.fogDuration = 200;
/*    */       }
/*    */       break;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/misc/PacketMiscEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */