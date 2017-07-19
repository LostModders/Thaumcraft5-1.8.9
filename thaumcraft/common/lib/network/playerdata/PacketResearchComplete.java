/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Random;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.client.gui.GuiResearchBrowser;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketResearchComplete implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketResearchComplete, IMessage>
/*    */ {
/*    */   private String key;
/*    */   byte flags;
/*    */   
/*    */   public PacketResearchComplete() {}
/*    */   
/*    */   public PacketResearchComplete(String key, byte flags)
/*    */   {
/* 31 */     this.key = key;
/* 32 */     this.flags = flags;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 37 */     ByteBufUtils.writeUTF8String(buffer, this.key);
/* 38 */     buffer.writeByte(this.flags);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 43 */     this.key = ByteBufUtils.readUTF8String(buffer);
/* 44 */     this.flags = buffer.readByte();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketResearchComplete message, MessageContext ctx)
/*    */   {
/* 50 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketResearchComplete.this.processMessage(message); } });
/* 51 */     return null;
/*    */   }
/*    */   
/* 54 */   private static ConcurrentHashMap<String, Long> spam = new ConcurrentHashMap();
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketResearchComplete message) {
/* 58 */     if ((message.key != null) && (message.key.length() > 0)) {
/* 59 */       Thaumcraft.proxy.getResearchManager().completeResearch(Minecraft.func_71410_x().field_71439_g, message.key, message.flags);
/* 60 */       if (ResearchCategories.getResearch(message.key) == null) {
/* 61 */         IChatComponent text = new ChatComponentTranslation("tc.addclue", new Object[0]);
/* 62 */         if (message.key.startsWith("!")) {
/* 63 */           Aspect aspect = Aspect.getAspect(message.key.substring(1));
/* 64 */           if (aspect != null)
/* 65 */             text = new ChatComponentTranslation("tc.addaspect", new Object[] { "" + aspect.getName() });
/*    */         }
/* 67 */         String kk = text.func_150260_c();
/* 68 */         if ((!spam.containsKey(kk)) || (((Long)spam.get(kk)).longValue() <= System.currentTimeMillis()))
/*    */         {
/*    */ 
/* 71 */           spam.put(kk, Long.valueOf(System.currentTimeMillis() + 500L));
/* 72 */           Minecraft.func_71410_x().field_71439_g.func_145747_a(text);
/* 73 */           Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:learn", 0.3F, 1.0F + Minecraft.func_71410_x().field_71439_g.field_70170_p.field_73012_v.nextFloat() * 0.1F);
/*    */         }
/*    */       }
/* 76 */       else if (!ResearchCategories.getResearch(message.key).isVirtual()) {
/* 77 */         thaumcraft.client.lib.RenderEventHandler.researchPopup.queueResearchInformation(ResearchCategories.getResearch(message.key));
/*    */       }
/* 79 */       if ((Minecraft.func_71410_x().field_71462_r instanceof GuiResearchBrowser)) {
/* 80 */         ArrayList<String> al = (ArrayList)GuiResearchBrowser.completedResearch.get(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
/* 81 */         if (al == null) al = new ArrayList();
/* 82 */         al.add(message.key);
/* 83 */         GuiResearchBrowser.completedResearch.put(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), al);
/* 84 */         ((GuiResearchBrowser)Minecraft.func_71410_x().field_71462_r).updateResearch();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/playerdata/PacketResearchComplete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */