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
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ 
/*    */ public class PacketSyncAll implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketSyncAll, IMessage>
/*    */ {
/*    */   protected String name;
/*    */   
/*    */   public PacketSyncAll() {}
/*    */   
/*    */   public PacketSyncAll(EntityPlayer player)
/*    */   {
/* 26 */     this.name = player.func_70005_c_();
/* 27 */     this.data = ResearchManager.getResearchForPlayer(this.name);
/* 28 */     this.flags = ResearchManager.getResearchFlagsForPlayer(this.name);
/* 29 */     this.warpTemp = Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(this.name);
/* 30 */     this.warpSticky = Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(this.name);
/* 31 */     this.warpPerm = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(this.name);
/*    */   }
/*    */   
/* 34 */   protected ArrayList<String> data = new ArrayList();
/* 35 */   protected HashMap<String, Byte> flags = new HashMap();
/*    */   
/* 37 */   protected int warpTemp = 0;
/* 38 */   protected int warpSticky = 0;
/* 39 */   protected int warpPerm = 0;
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 43 */     ByteBufUtils.writeUTF8String(buffer, this.name);
/*    */     
/* 45 */     if ((this.data != null) && (this.data.size() > 0)) {
/* 46 */       buffer.writeShort(this.data.size());
/* 47 */       for (String re : this.data)
/* 48 */         if (re != null) {
/* 49 */           ByteBufUtils.writeUTF8String(buffer, re);
/* 50 */           buffer.writeByte(this.flags.get(re) != null ? ((Byte)this.flags.get(re)).byteValue() : 0);
/*    */         }
/* 52 */     } else { buffer.writeShort(0);
/*    */     }
/* 54 */     buffer.writeShort(this.warpTemp);
/* 55 */     buffer.writeShort(this.warpSticky);
/* 56 */     buffer.writeShort(this.warpPerm);
/*    */   }
/*    */   
/*    */ 
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 62 */     this.name = ByteBufUtils.readUTF8String(buffer);
/*    */     
/* 64 */     short size = buffer.readShort();
/* 65 */     this.data = new ArrayList();
/* 66 */     for (int a = 0; a < size; a++) {
/* 67 */       String s = ByteBufUtils.readUTF8String(buffer);
/* 68 */       this.data.add(s);
/* 69 */       this.flags.put(s, Byte.valueOf(buffer.readByte()));
/*    */     }
/*    */     
/* 72 */     this.warpTemp = buffer.readShort();
/* 73 */     this.warpSticky = buffer.readShort();
/* 74 */     this.warpPerm = buffer.readShort();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncAll message, MessageContext ctx)
/*    */   {
/* 80 */     boolean old = ResearchManager.loadingBlocked;
/* 81 */     ResearchManager.loadingBlocked = true;
/*    */     
/*    */ 
/* 84 */     Thaumcraft.proxy.getPlayerKnowledge().wipePlayerKnowledge(message.name);
/*    */     
/*    */     String key;
/* 87 */     for (Iterator i$ = message.data.iterator(); i$.hasNext(); 
/* 88 */         ResearchManager.completeResearchUnsaved(message.name, key, ((Byte)message.flags.get(key)).byteValue()))
/*    */     {
/* 87 */       key = (String)i$.next();
/* 88 */       Thaumcraft.proxy.getResearchManager();
/*    */     }
/* 90 */     thaumcraft.client.gui.GuiResearchBrowser.completedResearch.put(message.name, message.data);
/*    */     
/*    */ 
/* 93 */     Thaumcraft.proxy.getPlayerKnowledge().setWarpTemp(message.name, message.warpTemp);
/* 94 */     Thaumcraft.proxy.getPlayerKnowledge().setWarpSticky(message.name, message.warpSticky);
/* 95 */     Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(message.name, message.warpPerm);
/*    */     
/* 97 */     ResearchManager.loadingBlocked = old;
/* 98 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncAll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */