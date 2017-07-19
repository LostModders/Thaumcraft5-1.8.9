/*     */ package thaumcraft.common.lib.network.misc;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealEntity;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealHandler;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class PacketSealToClient implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketSealToClient, IMessage>
/*     */ {
/*     */   BlockPos pos;
/*     */   EnumFacing face;
/*     */   String type;
/*     */   long area;
/*  28 */   boolean[] props = null;
/*     */   boolean blacklist;
/*     */   byte filtersize;
/*     */   ItemStack[] filter;
/*     */   byte priority;
/*     */   byte color;
/*     */   boolean locked;
/*     */   boolean redstone;
/*     */   String owner;
/*     */   
/*     */   public PacketSealToClient() {}
/*     */   
/*     */   public PacketSealToClient(ISealEntity se) {
/*  41 */     this.pos = se.getSealPos().pos;
/*  42 */     this.face = se.getSealPos().face;
/*  43 */     this.type = (se.getSeal() == null ? "REMOVE" : se.getSeal().getKey());
/*  44 */     if ((se.getSeal() != null) && ((se.getSeal() instanceof ISealConfigArea))) {
/*  45 */       this.area = se.getArea().func_177986_g();
/*     */     }
/*  47 */     if ((se.getSeal() != null) && ((se.getSeal() instanceof ISealConfigToggles))) {
/*  48 */       ISealConfigToggles cp = (ISealConfigToggles)se.getSeal();
/*  49 */       this.props = new boolean[cp.getToggles().length];
/*  50 */       for (int a = 0; a < cp.getToggles().length; a++) {
/*  51 */         this.props[a] = cp.getToggles()[a].getValue();
/*     */       }
/*     */     }
/*  54 */     if ((se.getSeal() != null) && ((se.getSeal() instanceof ISealConfigFilter))) {
/*  55 */       ISealConfigFilter cp = (ISealConfigFilter)se.getSeal();
/*  56 */       this.blacklist = cp.isBlacklist();
/*  57 */       this.filtersize = ((byte)cp.getFilterSize());
/*  58 */       this.filter = cp.getInv();
/*     */     }
/*  60 */     this.priority = se.getPriority();
/*  61 */     this.color = se.getColor();
/*  62 */     this.locked = se.isLocked();
/*  63 */     this.redstone = se.isRedstoneSensitive();
/*  64 */     this.owner = se.getOwner();
/*     */   }
/*     */   
/*     */   public void toBytes(ByteBuf dos)
/*     */   {
/*  69 */     dos.writeLong(this.pos.func_177986_g());
/*  70 */     dos.writeByte(this.face.ordinal());
/*  71 */     dos.writeByte(this.priority);
/*  72 */     dos.writeByte(this.color);
/*  73 */     dos.writeBoolean(this.locked);
/*  74 */     dos.writeBoolean(this.redstone);
/*  75 */     ByteBufUtils.writeUTF8String(dos, this.owner);
/*  76 */     ByteBufUtils.writeUTF8String(dos, this.type);
/*  77 */     dos.writeBoolean(this.blacklist);
/*  78 */     dos.writeByte(this.filtersize);
/*  79 */     for (int a = 0; a < this.filtersize; a++) {
/*  80 */       Utils.writeItemStackToBuffer(dos, this.filter[a]);
/*     */     }
/*  82 */     if (this.area != 0L) dos.writeLong(this.area);
/*  83 */     if (this.props != null) {
/*  84 */       for (boolean b : this.props) dos.writeBoolean(b);
/*     */     }
/*     */   }
/*     */   
/*     */   public void fromBytes(ByteBuf dat) {
/*  89 */     this.pos = BlockPos.func_177969_a(dat.readLong());
/*  90 */     this.face = EnumFacing.field_82609_l[dat.readByte()];
/*  91 */     this.priority = dat.readByte();
/*  92 */     this.color = dat.readByte();
/*  93 */     this.locked = dat.readBoolean();
/*  94 */     this.redstone = dat.readBoolean();
/*  95 */     this.owner = ByteBufUtils.readUTF8String(dat);
/*  96 */     this.type = ByteBufUtils.readUTF8String(dat);
/*  97 */     this.blacklist = dat.readBoolean();
/*  98 */     this.filtersize = dat.readByte();
/*  99 */     this.filter = new ItemStack[this.filtersize];
/* 100 */     for (int a = 0; a < this.filtersize; a++) {
/* 101 */       this.filter[a] = Utils.readItemStackFromBuffer(dat);
/*     */     }
/* 103 */     if ((!this.type.equals("REMOVE")) && (SealHandler.getSeal(this.type) != null)) {
/* 104 */       if ((SealHandler.getSeal(this.type) instanceof ISealConfigArea))
/* 105 */         try { this.area = dat.readLong();
/*     */         } catch (Exception e) {}
/* 107 */       if ((SealHandler.getSeal(this.type) instanceof ISealConfigToggles)) {
/*     */         try {
/* 109 */           ISealConfigToggles cp = (ISealConfigToggles)SealHandler.getSeal(this.type);
/* 110 */           this.props = new boolean[cp.getToggles().length];
/* 111 */           for (int a = 0; a < cp.getToggles().length; a++) {
/* 112 */             this.props[a] = dat.readBoolean();
/*     */           }
/*     */         }
/*     */         catch (Exception e) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public IMessage onMessage(PacketSealToClient message, MessageContext ctx) {
/* 121 */     if (message.type.equals("REMOVE")) {
/* 122 */       SealHandler.removeSealEntity(Thaumcraft.proxy.getClientWorld(), new SealPos(message.pos, message.face), true);
/*     */     } else {
/*     */       try {
/* 125 */         SealEntity seal = new SealEntity(Thaumcraft.proxy.getClientWorld(), new SealPos(message.pos, message.face), (ISeal)SealHandler.getSeal(message.type).getClass().newInstance());
/*     */         
/*     */ 
/*     */ 
/* 129 */         if (message.area != 0L) seal.setArea(BlockPos.func_177969_a(message.area));
/* 130 */         if ((message.props != null) && ((seal.getSeal() instanceof ISealConfigToggles))) {
/* 131 */           ISealConfigToggles cp = (ISealConfigToggles)seal.getSeal();
/* 132 */           for (int a = 0; a < message.props.length; a++) {
/* 133 */             cp.setToggle(a, message.props[a]);
/*     */           }
/*     */         }
/* 136 */         if ((seal.getSeal() instanceof ISealConfigFilter)) {
/* 137 */           ISealConfigFilter cp = (ISealConfigFilter)seal.getSeal();
/* 138 */           cp.setBlacklist(message.blacklist);
/* 139 */           for (int a = 0; a < message.filtersize; a++) {
/* 140 */             cp.setFilterSlot(a, message.filter[a]);
/*     */           }
/*     */         }
/* 143 */         seal.setPriority(message.priority);
/* 144 */         seal.setColor(message.color);
/* 145 */         seal.setLocked(message.locked);
/* 146 */         seal.setRedstoneSensitive(message.redstone);
/* 147 */         seal.setOwner(message.owner);
/* 148 */         SealHandler.addSealEntity(Thaumcraft.proxy.getClientWorld(), seal);
/*     */       } catch (Exception e) {
/* 150 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     
/* 154 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/misc/PacketSealToClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */