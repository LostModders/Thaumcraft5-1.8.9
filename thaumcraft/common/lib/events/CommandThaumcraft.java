/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategoryList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.api.research.ResearchPage;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ public class CommandThaumcraft extends net.minecraft.command.CommandBase
/*     */ {
/*     */   private List aliases;
/*     */   
/*     */   public CommandThaumcraft()
/*     */   {
/*  29 */     this.aliases = new java.util.ArrayList();
/*  30 */     this.aliases.add("thaumcraft");
/*  31 */     this.aliases.add("thaum");
/*  32 */     this.aliases.add("tc");
/*     */   }
/*     */   
/*     */   public String func_71517_b()
/*     */   {
/*  37 */     return "thaumcraft";
/*     */   }
/*     */   
/*     */   public List<String> func_71514_a()
/*     */   {
/*  42 */     return this.aliases;
/*     */   }
/*     */   
/*     */   public String func_71518_a(ICommandSender icommandsender)
/*     */   {
/*  47 */     return "/thaumcraft <action> [<player> [<params>]]";
/*     */   }
/*     */   
/*     */   public int func_82362_a()
/*     */   {
/*  52 */     return 2;
/*     */   }
/*     */   
/*     */   public boolean func_82358_a(String[] astring, int i)
/*     */   {
/*  57 */     return i == 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_71515_b(ICommandSender icommandsender, String[] astring)
/*     */     throws net.minecraft.command.CommandException
/*     */   {
/*  64 */     if (astring.length == 0) {
/*  65 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*  66 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*  67 */       return;
/*     */     }
/*  69 */     if (astring[0].equalsIgnoreCase("help")) {
/*  70 */       icommandsender.func_145747_a(new ChatComponentTranslation("§3You can also use /thaum or /tc instead of /thaumcraft.", new Object[0]));
/*  71 */       icommandsender.func_145747_a(new ChatComponentTranslation("§3Use this to give research to a player.", new Object[0]));
/*  72 */       icommandsender.func_145747_a(new ChatComponentTranslation("  /thaumcraft research <list|player> <all|reset|<research>>", new Object[0]));
/*     */       
/*     */ 
/*  75 */       icommandsender.func_145747_a(new ChatComponentTranslation("§3Use this to give set a players warp level.", new Object[0]));
/*  76 */       icommandsender.func_145747_a(new ChatComponentTranslation("  /thaumcraft warp <player> <add|set> <amount> <PERM|TEMP>", new Object[0]));
/*  77 */       icommandsender.func_145747_a(new ChatComponentTranslation("  not specifying perm or temp will just add normal warp", new Object[0]));
/*  78 */     } else if (astring.length >= 2) {
/*  79 */       if ((astring[0].equalsIgnoreCase("research")) && (astring[1].equalsIgnoreCase("list"))) {
/*  80 */         listResearch(icommandsender);
/*     */       } else {
/*  82 */         EntityPlayerMP entityplayermp = func_82359_c(icommandsender, astring[1]);
/*     */         
/*  84 */         if (astring[0].equalsIgnoreCase("research")) {
/*  85 */           if (astring.length == 3) {
/*  86 */             if (astring[2].equalsIgnoreCase("all")) {
/*  87 */               giveAllResearch(icommandsender, entityplayermp);
/*  88 */             } else if (astring[2].equalsIgnoreCase("reset")) {
/*  89 */               resetResearch(icommandsender, entityplayermp);
/*     */             } else {
/*  91 */               giveResearch(icommandsender, entityplayermp, astring[2]);
/*     */             }
/*     */           } else {
/*  94 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*  95 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft research <list|player> <all|reset|<research>>", new Object[0]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 107 */         else if (astring[0].equalsIgnoreCase("warp")) {
/* 108 */           if ((astring.length >= 4) && (astring[2].equalsIgnoreCase("set"))) {
/* 109 */             int i = func_180528_a(astring[3], 0);
/* 110 */             setWarp(icommandsender, entityplayermp, i, astring.length == 5 ? astring[4] : "");
/*     */           }
/* 112 */           else if ((astring.length >= 4) && (astring[2].equalsIgnoreCase("add"))) {
/* 113 */             int i = func_175764_a(astring[3], -100, 100);
/* 114 */             addWarp(icommandsender, entityplayermp, i, astring.length == 5 ? astring[4] : "");
/*     */           }
/*     */           else {
/* 117 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */             
/*     */ 
/* 120 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft warp <player> <add|set> <amount> <PERM|TEMP>", new Object[0]));
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 125 */           icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */           
/* 127 */           icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 134 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */       
/* 136 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setWarp(ICommandSender icommandsender, EntityPlayerMP player, int i, String type)
/*     */   {
/* 190 */     if (type.equalsIgnoreCase("PERM")) {
/* 191 */       Thaumcraft.proxy.playerKnowledge.setWarpPerm(player.func_70005_c_(), i);
/* 192 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)0), player);
/*     */     }
/* 194 */     else if (type.equalsIgnoreCase("TEMP")) {
/* 195 */       Thaumcraft.proxy.playerKnowledge.setWarpTemp(player.func_70005_c_(), i);
/* 196 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)2), player);
/*     */     } else {
/* 198 */       Thaumcraft.proxy.playerKnowledge.setWarpSticky(player.func_70005_c_(), i);
/* 199 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)1), player);
/*     */     }
/* 201 */     player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " set your warp to " + i, new Object[0]));
/*     */     
/*     */ 
/* 204 */     icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */   }
/*     */   
/*     */ 
/*     */   private void addWarp(ICommandSender icommandsender, EntityPlayerMP player, int i, String type)
/*     */   {
/* 210 */     if (type.equalsIgnoreCase("PERM")) {
/* 211 */       Thaumcraft.proxy.playerKnowledge.addWarpPerm(player.func_70005_c_(), i);
/* 212 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)0), player);
/* 213 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketWarpMessage(player, (byte)0, i), player);
/*     */     }
/* 215 */     else if (type.equalsIgnoreCase("TEMP")) {
/* 216 */       Thaumcraft.proxy.playerKnowledge.addWarpTemp(player.func_70005_c_(), i);
/* 217 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)2), player);
/* 218 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketWarpMessage(player, (byte)2, i), player);
/*     */     } else {
/* 220 */       Thaumcraft.proxy.playerKnowledge.addWarpSticky(player.func_70005_c_(), i);
/* 221 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)1), player);
/* 222 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketWarpMessage(player, (byte)1, i), player);
/*     */     }
/*     */     
/* 225 */     player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " added " + i + " warp to your total.", new Object[0]));
/* 226 */     icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */   }
/*     */   
/*     */   private void listResearch(ICommandSender icommandsender)
/*     */   {
/* 231 */     Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
/*     */     
/* 233 */     for (ResearchCategoryList cat : rc) {
/* 234 */       Collection<ResearchItem> rl = cat.research.values();
/* 235 */       for (ResearchItem ri : rl) {
/* 236 */         icommandsender.func_145747_a(new ChatComponentTranslation("§5" + ri.key, new Object[0]));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void giveResearch(ICommandSender icommandsender, EntityPlayerMP player, String research)
/*     */   {
/* 243 */     if (ResearchCategories.getResearch(research) != null) {
/* 244 */       giveRecursiveResearch(player, research, (byte)thaumcraft.common.lib.utils.Utils.setBit(0, 1));
/* 245 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncResearch(player), player);
/* 246 */       player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " gave you " + research + " research and its requisites.", new Object[0]));
/* 247 */       icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */     } else {
/* 249 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cResearch does not exist.", new Object[0]));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void giveRecursiveResearch(EntityPlayer player, String research, byte b)
/*     */   {
/* 255 */     ResearchItem res = ResearchCategories.getResearch(research);
/*     */     
/* 257 */     if ((res != null) && (res.getPages() != null)) {
/* 258 */       for (ResearchPage page : res.getPages()) {
/* 259 */         if (page.research != null) {
/* 260 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, page.research, b);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 265 */     if (!ResearchManager.isResearchComplete(player.func_70005_c_(), research))
/*     */     {
/* 267 */       Thaumcraft.proxy.getResearchManager().completeResearch(player, research, b);
/*     */       
/* 269 */       if (b != 0) {
/* 270 */         for (String rc : ResearchCategories.researchCategories.keySet()) {
/* 271 */           for (ResearchItem ri : ResearchCategories.getResearchList(rc).research.values()) {
/* 272 */             if (ri.getPages() != null) {
/* 273 */               for (ResearchPage page : ri.getPages()) {
/* 274 */                 if ((page.research != null) && (page.research.equals(research))) {
/* 275 */                   ResearchManager.setNewPageFlag(player.func_70005_c_(), ri.key);
/* 276 */                   break;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 283 */       if ((res != null) && (res.parents != null)) {
/* 284 */         for (String rsi : res.parents)
/* 285 */           giveRecursiveResearch(player, rsi, b);
/*     */       }
/* 287 */       if ((res != null) && (res.parentsHidden != null)) {
/* 288 */         for (String rsi : res.parentsHidden)
/* 289 */           giveRecursiveResearch(player, rsi, b);
/*     */       }
/* 291 */       if ((res != null) && (res.siblings != null)) {
/* 292 */         for (String rsi : res.siblings)
/* 293 */           giveRecursiveResearch(player, rsi, b);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void giveAllResearch(ICommandSender icommandsender, EntityPlayerMP player) {
/* 299 */     Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
/* 300 */     for (ResearchCategoryList cat : rc) {
/* 301 */       Collection<ResearchItem> rl = cat.research.values();
/* 302 */       for (ResearchItem ri : rl) {
/* 303 */         giveRecursiveResearch(player, ri.key, (byte)0);
/*     */       }
/*     */     }
/* 306 */     player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " has given you all research.", new Object[0]));
/* 307 */     icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/* 308 */     PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncResearch(player), player);
/*     */   }
/*     */   
/*     */   void resetResearch(ICommandSender icommandsender, EntityPlayerMP player) {
/* 312 */     Thaumcraft.proxy.getPlayerKnowledge().researchCompleted.remove(player.func_70005_c_());
/* 313 */     Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
/* 314 */     for (ResearchCategoryList cat : rc) {
/* 315 */       Collection<ResearchItem> res = cat.research.values();
/* 316 */       for (ResearchItem ri : res) {
/* 317 */         if (ri.isAutoUnlock()) {
/* 318 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, ri.key, (byte)0);
/*     */         }
/*     */       }
/*     */     }
/* 322 */     player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " has reset you research.", new Object[0]));
/* 323 */     icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/* 324 */     PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncResearch(player), player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/events/CommandThaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */