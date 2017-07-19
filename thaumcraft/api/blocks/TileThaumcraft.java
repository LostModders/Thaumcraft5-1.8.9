/*    */ package thaumcraft.api.blocks;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileThaumcraft
/*    */   extends TileEntity
/*    */ {
/*    */   public void func_145839_a(NBTTagCompound nbt)
/*    */   {
/* 29 */     super.func_145839_a(nbt);
/* 30 */     readCustomNBT(nbt);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbt) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_145841_b(NBTTagCompound nbt)
/*    */   {
/* 41 */     super.func_145841_b(nbt);
/* 42 */     writeCustomNBT(nbt);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbt) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public Packet func_145844_m()
/*    */   {
/* 53 */     NBTTagCompound nbt = new NBTTagCompound();
/* 54 */     writeCustomNBT(nbt);
/* 55 */     return new S35PacketUpdateTileEntity(func_174877_v(), 64537, nbt);
/*    */   }
/*    */   
/*    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*    */   {
/* 60 */     super.onDataPacket(net, pkt);
/* 61 */     readCustomNBT(pkt.func_148857_g());
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
/*    */   {
/* 67 */     return oldState.func_177230_c() != newState.func_177230_c();
/*    */   }
/*    */   
/*    */   public EnumFacing getFacing() {
/*    */     try {
/* 72 */       return EnumFacing.func_82600_a(func_145832_p() & 0x7);
/*    */     } catch (Exception e) {}
/* 74 */     return EnumFacing.UP;
/*    */   }
/*    */   
/*    */   public boolean gettingPower() {
/* 78 */     return this.field_145850_b.func_175640_z(func_174877_v());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/blocks/TileThaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */