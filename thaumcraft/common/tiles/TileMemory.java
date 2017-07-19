/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ 
/*    */ public class TileMemory
/*    */   extends TileEntity
/*    */ {
/* 13 */   public IBlockState oldblock = Blocks.field_150350_a.func_176223_P();
/*    */   
/*    */   public NBTTagCompound tileEntityCompound;
/*    */   
/*    */ 
/*    */   public TileMemory() {}
/*    */   
/*    */ 
/*    */   public TileMemory(IBlockState bi)
/*    */   {
/* 23 */     this.oldblock = bi;
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*    */   {
/* 46 */     super.func_145839_a(nbttagcompound);
/*    */     
/* 48 */     Block b = Block.func_149729_e(nbttagcompound.func_74762_e("oldblock"));
/* 49 */     int meta = nbttagcompound.func_74762_e("oldmeta");
/* 50 */     this.oldblock = b.func_176203_a(meta);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*    */   {
/* 60 */     super.func_145841_b(nbttagcompound);
/* 61 */     nbttagcompound.func_74768_a("oldblock", Block.func_149682_b(this.oldblock.func_177230_c()));
/* 62 */     nbttagcompound.func_74768_a("oldmeta", this.oldblock.func_177230_c().func_176201_c(this.oldblock));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/TileMemory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */