/*    */ package thaumcraft.api.internal;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ 
/*    */ public class WorldCoordinates implements Comparable
/*    */ {
/*    */   public BlockPos pos;
/*    */   public int dim;
/*    */   
/*    */   public WorldCoordinates() {}
/*    */   
/*    */   public WorldCoordinates(BlockPos pos, int d)
/*    */   {
/* 17 */     this.pos = pos;
/* 18 */     this.dim = d;
/*    */   }
/*    */   
/*    */   public WorldCoordinates(TileEntity tile)
/*    */   {
/* 23 */     this.pos = tile.func_174877_v();
/* 24 */     this.dim = tile.func_145831_w().field_73011_w.func_177502_q();
/*    */   }
/*    */   
/*    */   public WorldCoordinates(WorldCoordinates par1ChunkCoordinates)
/*    */   {
/* 29 */     this.pos = par1ChunkCoordinates.pos;
/* 30 */     this.dim = par1ChunkCoordinates.dim;
/*    */   }
/*    */   
/*    */   public boolean equals(Object par1Obj)
/*    */   {
/* 35 */     if (!(par1Obj instanceof WorldCoordinates))
/*    */     {
/* 37 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 41 */     WorldCoordinates coordinates = (WorldCoordinates)par1Obj;
/* 42 */     return (this.pos.equals(coordinates.pos)) && (this.dim == coordinates.dim);
/*    */   }
/*    */   
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 48 */     return this.pos.func_177958_n() + this.pos.func_177956_o() << 8 + this.pos.func_177952_p() << 16 + this.dim << 24;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int compareWorldCoordinate(WorldCoordinates par1)
/*    */   {
/* 56 */     return this.dim == par1.dim ? this.pos.compareTo(par1.pos) : -1;
/*    */   }
/*    */   
/*    */   public void set(BlockPos pos, int d)
/*    */   {
/* 61 */     this.pos = pos;
/* 62 */     this.dim = d;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public double getDistanceSquared(BlockPos pos)
/*    */   {
/* 70 */     return this.pos.func_177951_i(pos);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public double getDistanceSquaredToWorldCoordinates(WorldCoordinates par1ChunkCoordinates)
/*    */   {
/* 78 */     return getDistanceSquared(par1ChunkCoordinates.pos);
/*    */   }
/*    */   
/*    */   public int compareTo(Object par1Obj)
/*    */   {
/* 83 */     return compareWorldCoordinate((WorldCoordinates)par1Obj);
/*    */   }
/*    */   
/*    */   public void readNBT(NBTTagCompound nbt) {
/* 87 */     int x = nbt.func_74762_e("w_x");
/* 88 */     int y = nbt.func_74762_e("w_y");
/* 89 */     int z = nbt.func_74762_e("w_z");
/* 90 */     this.pos = new BlockPos(x, y, z);
/* 91 */     this.dim = nbt.func_74762_e("w_d");
/*    */   }
/*    */   
/*    */   public void writeNBT(NBTTagCompound nbt) {
/* 95 */     nbt.func_74768_a("w_x", this.pos.func_177958_n());
/* 96 */     nbt.func_74768_a("w_y", this.pos.func_177956_o());
/* 97 */     nbt.func_74768_a("w_z", this.pos.func_177952_p());
/* 98 */     nbt.func_74768_a("w_d", this.dim);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/internal/WorldCoordinates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */