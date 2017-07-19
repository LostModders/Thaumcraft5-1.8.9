/*    */ package thaumcraft.common.tiles.misc;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.blocks.TileThaumcraft;
/*    */ 
/*    */ public class TileBanner
/*    */   extends TileThaumcraft
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 17 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o() - 1, func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 2, func_174877_v().func_177952_p() + 1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 22 */   private byte facing = 0;
/* 23 */   private byte color = -1;
/* 24 */   private Aspect aspect = null;
/* 25 */   private boolean onWall = false;
/*    */   
/*    */   public byte getBannerFacing() {
/* 28 */     return this.facing;
/*    */   }
/*    */   
/*    */   public void setBannerFacing(byte face) {
/* 32 */     this.facing = face;
/* 33 */     func_70296_d();
/*    */   }
/*    */   
/*    */   public boolean getWall() {
/* 37 */     return this.onWall;
/*    */   }
/*    */   
/*    */   public void setWall(boolean b) {
/* 41 */     this.onWall = b;
/* 42 */     func_70296_d();
/*    */   }
/*    */   
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 47 */     this.facing = nbttagcompound.func_74771_c("facing");
/* 48 */     setColor(nbttagcompound.func_74771_c("color"));
/* 49 */     String as = nbttagcompound.func_74779_i("aspect");
/* 50 */     if ((as != null) && (as.length() > 0)) setAspect(Aspect.getAspect(as)); else
/* 51 */       this.aspect = null;
/* 52 */     this.onWall = nbttagcompound.func_74767_n("wall");
/*    */   }
/*    */   
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 57 */     nbttagcompound.func_74774_a("facing", this.facing);
/* 58 */     nbttagcompound.func_74774_a("color", getColor());
/* 59 */     nbttagcompound.func_74778_a("aspect", getAspect() == null ? "" : getAspect().getTag());
/* 60 */     nbttagcompound.func_74757_a("wall", this.onWall);
/*    */   }
/*    */   
/*    */   public Aspect getAspect() {
/* 64 */     return this.aspect;
/*    */   }
/*    */   
/*    */   public void setAspect(Aspect aspect) {
/* 68 */     this.aspect = aspect;
/*    */   }
/*    */   
/*    */   public byte getColor() {
/* 72 */     return this.color;
/*    */   }
/*    */   
/*    */   public void setColor(byte color) {
/* 76 */     this.color = color;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileBanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */