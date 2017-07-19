/*     */ package thaumcraft.codechicken.lib.raytracer;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ public class RayTracer
/*     */ {
/*  24 */   private Vector3 vec = new Vector3();
/*  25 */   private Vector3 vec2 = new Vector3();
/*     */   
/*  27 */   private Vector3 s_vec = new Vector3();
/*     */   
/*     */   private double s_dist;
/*     */   private int s_side;
/*     */   private IndexedCuboid6 c_cuboid;
/*  32 */   private static ThreadLocal<RayTracer> t_inst = new ThreadLocal();
/*     */   
/*     */   public static RayTracer instance() {
/*  35 */     RayTracer inst = (RayTracer)t_inst.get();
/*  36 */     if (inst == null)
/*  37 */       t_inst.set(inst = new RayTracer());
/*  38 */     return inst;
/*     */   }
/*     */   
/*     */   private void traceSide(int side, Vector3 start, Vector3 end, Cuboid6 cuboid) {
/*  42 */     this.vec.set(start);
/*  43 */     Vector3 hit = null;
/*  44 */     switch (side) {
/*     */     case 0: 
/*  46 */       hit = this.vec.XZintercept(end, cuboid.min.y);
/*  47 */       break;
/*     */     case 1: 
/*  49 */       hit = this.vec.XZintercept(end, cuboid.max.y);
/*  50 */       break;
/*     */     case 2: 
/*  52 */       hit = this.vec.XYintercept(end, cuboid.min.z);
/*  53 */       break;
/*     */     case 3: 
/*  55 */       hit = this.vec.XYintercept(end, cuboid.max.z);
/*  56 */       break;
/*     */     case 4: 
/*  58 */       hit = this.vec.YZintercept(end, cuboid.min.x);
/*  59 */       break;
/*     */     case 5: 
/*  61 */       hit = this.vec.YZintercept(end, cuboid.max.x);
/*     */     }
/*     */     
/*  64 */     if (hit == null) {
/*  65 */       return;
/*     */     }
/*  67 */     switch (side) {
/*     */     case 0: 
/*     */     case 1: 
/*  70 */       if ((!MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x)) || (!MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z))) {
/*     */         return;
/*     */       }
/*     */       break;
/*     */     case 2: case 3: 
/*  75 */       if ((!MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x)) || (!MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y))) {
/*     */         return;
/*     */       }
/*     */       break;
/*     */     case 4: case 5: 
/*  80 */       if ((!MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y)) || (!MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z))) {
/*     */         return;
/*     */       }
/*     */       break;
/*     */     }
/*  85 */     double dist = this.vec2.set(hit).subtract(start).magSquared();
/*  86 */     if (dist < this.s_dist) {
/*  87 */       this.s_side = side;
/*  88 */       this.s_dist = dist;
/*  89 */       this.s_vec.set(this.vec);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid) {
/*  94 */     this.s_dist = Double.MAX_VALUE;
/*  95 */     this.s_side = -1;
/*     */     
/*  97 */     for (int i = 0; i < 6; i++) {
/*  98 */       traceSide(i, start, end, cuboid);
/*     */     }
/* 100 */     return this.s_side >= 0;
/*     */   }
/*     */   
/*     */   public ExtendedMOP rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid, BlockCoord pos, Object data) {
/* 104 */     return rayTraceCuboid(start, end, cuboid) ? new ExtendedMOP(this.s_vec, this.s_side, pos, data, this.s_dist) : null;
/*     */   }
/*     */   
/*     */   public ExtendedMOP rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid, Entity entity, Object data) {
/* 108 */     return rayTraceCuboid(start, end, cuboid) ? new ExtendedMOP(entity, this.s_vec, data, this.s_dist) : null;
/*     */   }
/*     */   
/*     */   public void rayTraceCuboids(Vector3 start, Vector3 end, List<IndexedCuboid6> cuboids, BlockCoord pos, Block block, List<ExtendedMOP> hitList) {
/* 112 */     for (IndexedCuboid6 cuboid : cuboids) {
/* 113 */       ExtendedMOP mop = rayTraceCuboid(start, end, cuboid, pos, cuboid.data);
/* 114 */       if (mop != null)
/* 115 */         hitList.add(mop);
/*     */     }
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition retraceBlock(World world, EntityPlayer player, BlockPos pos) {
/* 120 */     IBlockState b = world.func_180495_p(pos);
/* 121 */     Vec3 headVec = getCorrectedHeadVec(player);
/* 122 */     Vec3 lookVec = player.func_70676_i(1.0F);
/* 123 */     double reach = getBlockReachDistance(player);
/* 124 */     Vec3 endVec = headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
/* 125 */     return b.func_177230_c().func_180636_a(world, pos, headVec, endVec);
/*     */   }
/*     */   
/*     */   private static double getBlockReachDistance_server(EntityPlayerMP player) {
/* 129 */     return player.field_71134_c.getBlockReachDistance();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private static double getBlockReachDistance_client() {
/* 134 */     return Minecraft.func_71410_x().field_71442_b.func_78757_d();
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition retrace(EntityPlayer player) {
/* 138 */     return retrace(player, getBlockReachDistance(player));
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition retrace(EntityPlayer player, double reach) {
/* 142 */     Vec3 headVec = getCorrectedHeadVec(player);
/* 143 */     Vec3 lookVec = player.func_70676_i(1.0F);
/* 144 */     Vec3 endVec = headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
/* 145 */     return player.field_70170_p.func_147447_a(headVec, endVec, true, false, true);
/*     */   }
/*     */   
/*     */   public static Vec3 getCorrectedHeadVec(EntityPlayer player) {
/* 149 */     Vector3 v = Vector3.fromEntity(player);
/* 150 */     if (player.field_70170_p.field_72995_K) {
/* 151 */       v.y += player.func_70047_e();
/*     */     } else {
/* 153 */       v.y += player.func_70047_e();
/* 154 */       if (((player instanceof EntityPlayerMP)) && (player.func_70093_af()))
/* 155 */         v.y -= 0.08D;
/*     */     }
/* 157 */     return v.vec3();
/*     */   }
/*     */   
/*     */   public static Vec3 getStartVec(EntityPlayer player) {
/* 161 */     return getCorrectedHeadVec(player);
/*     */   }
/*     */   
/*     */   public static double getBlockReachDistance(EntityPlayer player) {
/* 165 */     return (player instanceof EntityPlayerMP) ? getBlockReachDistance_server((EntityPlayerMP)player) : player.field_70170_p.field_72995_K ? getBlockReachDistance_client() : 5.0D;
/*     */   }
/*     */   
/*     */   public static Vec3 getEndVec(EntityPlayer player)
/*     */   {
/* 170 */     Vec3 headVec = getCorrectedHeadVec(player);
/* 171 */     Vec3 lookVec = player.func_70676_i(1.0F);
/* 172 */     double reach = getBlockReachDistance(player);
/* 173 */     return headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/raytracer/RayTracer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */