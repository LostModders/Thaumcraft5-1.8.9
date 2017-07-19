/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXGeneric;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketFXScanSource
/*    */   implements IMessage, IMessageHandler<PacketFXScanSource, IMessage>
/*    */ {
/*    */   private long loc;
/*    */   private int size;
/*    */   
/*    */   public PacketFXScanSource() {}
/*    */   
/*    */   public PacketFXScanSource(BlockPos pos, int size)
/*    */   {
/* 29 */     this.loc = pos.func_177986_g();
/* 30 */     this.size = size;
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 36 */     buffer.writeLong(this.loc);
/* 37 */     buffer.writeByte(this.size);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 42 */     this.loc = buffer.readLong();
/* 43 */     this.size = buffer.readByte();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXScanSource message, MessageContext ctx)
/*    */   {
/* 49 */     startScan(Minecraft.func_71410_x().field_71439_g.field_70170_p, BlockPos.func_177969_a(message.loc), message.size);
/* 50 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void startScan(World world, BlockPos pos, int r) {
/* 55 */     int range = 4 + r * 4;
/* 56 */     ArrayList<BlockPos> positions = new ArrayList();
/*    */     
/* 58 */     for (int xx = -range; xx <= range; xx++) {
/* 59 */       for (int yy = -range; yy <= range; yy++)
/* 60 */         for (int zz = -range; zz <= range; zz++) {
/* 61 */           BlockPos p = pos.func_177982_a(xx, yy, zz);
/* 62 */           if (Utils.isOreBlock(world, p)) positions.add(p);
/*    */         }
/*    */     }
/* 65 */     for (BlockPos p : positions) {
/* 66 */       int dis = MathHelper.func_76128_c(Math.sqrt(pos.func_177957_d(p.func_177958_n(), p.func_177956_o(), p.func_177952_p())));
/* 67 */       FXGeneric fb = new FXGeneric(world, p.func_177958_n() + 0.5D, p.func_177956_o() + 0.5D, p.func_177952_p() + 0.5D, 0.0D, 0.0D, 0.0D);
/* 68 */       fb.setMaxAge(40, dis);
/* 69 */       fb.func_70538_b(0.5F, 0.5F, 1.0F);
/* 70 */       fb.setAlphaF(0.66F, 0.0F);
/* 71 */       fb.setLoop(true);
/* 72 */       fb.setParticles(80, 16, 1);
/* 73 */       fb.setScale(5.0F, 1.0F);
/* 74 */       fb.setLayer(0);
/* 75 */       fb.setDepthIgnore(true);
/* 76 */       fb.setRotationSpeed(0.0F);
/* 77 */       ParticleEngine.instance.addEffect(world, fb);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/network/fx/PacketFXScanSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */