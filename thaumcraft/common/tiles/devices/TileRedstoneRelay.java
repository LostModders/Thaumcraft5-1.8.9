/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Transformation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ 
/*     */ public class TileRedstoneRelay
/*     */   extends TileThaumcraft
/*     */ {
/*  21 */   private int in = 1;
/*  22 */   private int out = 15;
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbt)
/*     */   {
/*  28 */     setIn(nbt.func_74771_c("in"));
/*  29 */     setOut(nbt.func_74771_c("out"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbt)
/*     */   {
/*  35 */     nbt.func_74774_a("in", (byte)getIn());
/*  36 */     nbt.func_74774_a("out", (byte)getOut());
/*     */   }
/*     */   
/*     */   public void increaseIn() {
/*  40 */     if (!this.field_145850_b.field_72995_K) {
/*  41 */       setIn(getIn() + 1);
/*  42 */       if (getIn() > 15) setIn(1);
/*  43 */       func_70296_d();
/*  44 */       this.field_145850_b.func_175689_h(func_174877_v());
/*     */     }
/*     */   }
/*     */   
/*     */   public void increaseOut() {
/*  49 */     if (!this.field_145850_b.field_72995_K) {
/*  50 */       setOut(getOut() + 1);
/*  51 */       if (getOut() > 15) setOut(1);
/*  52 */       func_70296_d();
/*  53 */       this.field_145850_b.func_175689_h(func_174877_v());
/*     */     }
/*     */   }
/*     */   
/*     */   public MovingObjectPosition rayTrace(World world, Vec3 vec3d, Vec3 vec3d1, MovingObjectPosition fullblock)
/*     */   {
/*  59 */     return fullblock;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids)
/*     */   {
/*  65 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/*     */     
/*  67 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(0), getCuboid0(facing)));
/*  68 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(1), getCuboid1(facing)));
/*     */   }
/*     */   
/*     */   public Cuboid6 getCuboid0(EnumFacing facing) {
/*  72 */     Transformation rot = thaumcraft.codechicken.lib.vec.Rotation.quarterRotations[0];
/*  73 */     switch (facing) {
/*  74 */     case WEST:  rot = thaumcraft.codechicken.lib.vec.Rotation.quarterRotations[1]; break;
/*  75 */     case NORTH:  rot = thaumcraft.codechicken.lib.vec.Rotation.quarterRotations[2]; break;
/*  76 */     case EAST:  rot = thaumcraft.codechicken.lib.vec.Rotation.quarterRotations[3];
/*     */     }
/*  78 */     return new Cuboid6(-0.4375D, 0.125D, -0.4375D, -0.0625D, 0.3125D, -0.0625D).apply(rot).add(new Vector3(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o(), func_174877_v().func_177952_p() + 0.5D));
/*     */   }
/*     */   
/*     */   public Cuboid6 getCuboid1(EnumFacing facing)
/*     */   {
/*  83 */     Transformation rot = thaumcraft.codechicken.lib.vec.Rotation.quarterRotations[0];
/*  84 */     switch (facing) {
/*  85 */     case WEST:  rot = thaumcraft.codechicken.lib.vec.Rotation.quarterRotations[1]; break;
/*  86 */     case NORTH:  rot = thaumcraft.codechicken.lib.vec.Rotation.quarterRotations[2]; break;
/*  87 */     case EAST:  rot = thaumcraft.codechicken.lib.vec.Rotation.quarterRotations[3];
/*     */     }
/*  89 */     return new Cuboid6(-0.1875D, 0.125D, 0.0625D, 0.1875D, 0.3125D, 0.4375D).apply(rot).add(new Vector3(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o(), func_174877_v().func_177952_p() + 0.5D));
/*     */   }
/*     */   
/*     */   public int getOut()
/*     */   {
/*  94 */     return this.out;
/*     */   }
/*     */   
/*     */   public void setOut(int out) {
/*  98 */     this.out = out;
/*     */   }
/*     */   
/*     */   public int getIn() {
/* 102 */     return this.in;
/*     */   }
/*     */   
/*     */   public void setIn(int in) {
/* 106 */     this.in = in;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileRedstoneRelay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */