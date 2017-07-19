/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ 
/*    */ public class PacketSyncResearch implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketSyncResearch, IMessage>
/*    */ {
/*    */   protected String name;
/*    */   
/*    */   public PacketSyncResearch() {}
/*    */   
/*    */   public PacketSyncResearch(EntityPlayer player)
/*    */   {
/* 24 */     this.name = player.func_70005_c_();
/* 25 */     this.data = ResearchManager.getResearchForPlayer(this.name);
/* 26 */     this.flags = ResearchManager.getResearchFlagsForPlayer(this.name);
/*    */   }
/*    */   
/*    */ 
/* 30 */   protected ArrayList<String> data = new ArrayList();
/* 31 */   protected HashMap<String, Byte> flags = new HashMap();
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 35 */     ByteBufUtils.writeUTF8String(buffer, this.name);
/* 36 */     if ((this.data != null) && (this.data.size() > 0)) {
/* 37 */       buffer.writeShort(this.data.size());
/* 38 */       for (String re : this.data)
/* 39 */         if (re != null) {
/* 40 */           ByteBufUtils.writeUTF8String(buffer, re);
/* 41 */           buffer.writeByte(this.flags.get(re) != null ? ((Byte)this.flags.get(re)).byteValue() : 0);
/*    */         }
/* 43 */     } else { buffer.writeShort(0);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 48 */     this.name = ByteBufUtils.readUTF8String(buffer);
/* 49 */     short size = buffer.readShort();
/* 50 */     this.data = new ArrayList();
/* 51 */     for (int a = 0; a < size; a++) {
/* 52 */       String s = ByteBufUtils.readUTF8String(buffer);
/* 53 */       this.data.add(s);
/* 54 */       this.flags.put(s, Byte.valueOf(buffer.readByte()));
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncResearch message, MessageContext ctx) {
/*    */     String key;
/* 61 */     for (Iterator i$ = message.data.iterator(); i$.hasNext(); 
/* 62 */         ResearchManager.completeResearchUnsaved(message.name, key, ((Byte)message.flags.get(key)).byteValue()))
/*    */     {
/* 61 */       key = (String)i$.next();
/* 62 */       Thaumcraft.proxy.getResearchManager();
/*    */     }
/* 64 */     thaumcraft.client.gui.GuiResearchBrowser.completedResearch.put(message.name, message.data);
/* 65 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncResearch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */