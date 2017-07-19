/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import net.minecraftforge.common.DimensionManager;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ import thaumcraft.common.items.tools.ItemElementalShovel;
/*    */ import thaumcraft.common.items.tools.ItemThaumometer;
/*    */ 
/*    */ public class PacketItemKeyToServer implements IMessage, net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketItemKeyToServer, IMessage>
/*    */ {
/*    */   private int dim;
/*    */   private int playerid;
/*    */   private byte key;
/*    */   
/*    */   public PacketItemKeyToServer() {}
/*    */   
/*    */   public PacketItemKeyToServer(EntityPlayer player, int key)
/*    */   {
/* 25 */     this.dim = player.field_70170_p.field_73011_w.func_177502_q();
/* 26 */     this.playerid = player.func_145782_y();
/* 27 */     this.key = ((byte)key);
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 32 */     buffer.writeInt(this.dim);
/* 33 */     buffer.writeInt(this.playerid);
/* 34 */     buffer.writeByte(this.key);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 39 */     this.dim = buffer.readInt();
/* 40 */     this.playerid = buffer.readInt();
/* 41 */     this.key = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketItemKeyToServer message, MessageContext ctx)
/*    */   {
/* 46 */     World world = DimensionManager.getWorld(message.dim);
/* 47 */     if (world == null) { return null;
/*    */     }
/* 49 */     net.minecraft.entity.Entity player = world.func_73045_a(message.playerid);
/*    */     
/* 51 */     if ((player != null) && ((player instanceof EntityPlayer)) && (((EntityPlayer)player).func_70694_bm() != null))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 58 */       if ((message.key == 1) && ((((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof IWand))) {
/* 59 */         thaumcraft.common.items.wands.WandManager.toggleMisc(((EntityPlayer)player).func_70694_bm(), world, (EntityPlayer)player);
/*    */       }
/*    */       
/* 62 */       if ((message.key == 1) && ((((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemElementalShovel))) {
/* 63 */         ((ItemElementalShovel)((EntityPlayer)player).func_70694_bm().func_77973_b());byte b = ItemElementalShovel.getOrientation(((EntityPlayer)player).func_70694_bm());
/* 64 */         ((ItemElementalShovel)((EntityPlayer)player).func_70694_bm().func_77973_b());ItemElementalShovel.setOrientation(((EntityPlayer)player).func_70694_bm(), (byte)(b + 1));
/*    */       }
/*    */       
/* 67 */       if ((message.key == 2) && ((((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemThaumometer))) {
/* 68 */         ItemThaumometer.changeVis(((EntityPlayer)player).func_70694_bm(), world, (EntityPlayer)player);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 73 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/misc/PacketItemKeyToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */