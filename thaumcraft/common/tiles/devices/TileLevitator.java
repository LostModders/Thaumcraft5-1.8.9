/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class TileLevitator extends thaumcraft.api.blocks.TileThaumcraft implements ITickable
/*     */ {
/*  29 */   private int[] ranges = { 4, 8, 16, 32 };
/*  30 */   private int range = 1;
/*  31 */   private int rangeActual = 0;
/*  32 */   private int counter = 0;
/*  33 */   private int vis = 0;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/*  37 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/*  38 */     if (this.rangeActual > this.ranges[this.range]) {
/*  39 */       this.rangeActual = 0;
/*     */     }
/*  41 */     int p = this.counter % this.ranges[this.range];
/*  42 */     if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177967_a(facing, 1 + p)).func_177230_c().func_149662_c()) {
/*  43 */       if (1 + p < this.rangeActual) {
/*  44 */         this.rangeActual = (1 + p);
/*     */       }
/*  46 */       this.counter = -1;
/*  47 */     } else if (1 + p > this.rangeActual) {
/*  48 */       this.rangeActual = (1 + p);
/*     */     }
/*     */     
/*  51 */     this.counter += 1;
/*     */     
/*  53 */     if ((!this.field_145850_b.field_72995_K) && (this.vis < 10)) {
/*  54 */       this.vis += AuraHandler.drainAuraAvailable(this.field_145850_b, func_174877_v(), thaumcraft.api.aspects.Aspect.AIR, 1) * 1200;
/*  55 */       func_70296_d();
/*  56 */       this.field_145850_b.func_175689_h(func_174877_v());
/*     */     }
/*     */     
/*  59 */     if ((this.rangeActual > 0) && (this.vis > 0) && (BlockStateUtils.isEnabled(func_145832_p())))
/*     */     {
/*  61 */       List<Entity> targets = this.field_145850_b.func_72872_a(Entity.class, net.minecraft.util.AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n() - (facing.func_82601_c() < 0 ? this.rangeActual : 0), this.field_174879_c.func_177956_o() - (facing.func_96559_d() < 0 ? this.rangeActual : 0), this.field_174879_c.func_177952_p() - (facing.func_82599_e() < 0 ? this.rangeActual : 0), this.field_174879_c.func_177958_n() + 1 + (facing.func_82601_c() > 0 ? this.rangeActual : 0), this.field_174879_c.func_177956_o() + 1 + (facing.func_96559_d() > 0 ? this.rangeActual : 0), this.field_174879_c.func_177952_p() + 1 + (facing.func_82599_e() > 0 ? this.rangeActual : 0)));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */       boolean lifted = false;
/*  70 */       if (targets.size() > 0)
/*  71 */         for (Entity e : targets)
/*  72 */           if (((e instanceof EntityItem)) || (e.func_70104_M()) || ((e instanceof EntityHorse))) {
/*  73 */             lifted = true;
/*  74 */             drawFXAt(e);
/*  75 */             if ((e.func_70093_af()) && (facing == EnumFacing.UP)) {
/*  76 */               if (e.field_70181_x < 0.0D) e.field_70181_x *= 0.8999999761581421D;
/*     */             } else {
/*  78 */               e.field_70159_w += 0.1F * facing.func_82601_c();
/*  79 */               e.field_70181_x += 0.1F * facing.func_96559_d();
/*  80 */               e.field_70179_y += 0.1F * facing.func_82599_e();
/*  81 */               if ((facing.func_176740_k() != net.minecraft.util.EnumFacing.Axis.Y) && (!e.field_70122_E)) {
/*  82 */                 if (e.field_70181_x < 0.0D) e.field_70181_x *= 0.8999999761581421D;
/*  83 */                 e.field_70181_x += 0.07999999821186066D;
/*     */               }
/*  85 */               if (e.field_70159_w > 0.3499999940395355D) e.field_70159_w = 0.3499999940395355D;
/*  86 */               if (e.field_70181_x > 0.3499999940395355D) e.field_70181_x = 0.3499999940395355D;
/*  87 */               if (e.field_70179_y > 0.3499999940395355D) e.field_70179_y = 0.3499999940395355D;
/*  88 */               if (e.field_70159_w < -0.3499999940395355D) e.field_70159_w = -0.3499999940395355D;
/*  89 */               if (e.field_70181_x < -0.3499999940395355D) e.field_70181_x = -0.3499999940395355D;
/*  90 */               if (e.field_70179_y < -0.3499999940395355D) e.field_70179_y = -0.3499999940395355D;
/*     */             }
/*  92 */             e.field_70143_R = 0.0F;
/*  93 */             this.vis -= getCost();
/*  94 */             if (this.vis <= 0) break;
/*     */           }
/*  96 */       drawFX(facing);
/*     */       
/*  98 */       if ((lifted) && (!this.field_145850_b.field_72995_K) && (this.counter % 20 == 0)) {
/*  99 */         func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawFX(EnumFacing facing) {
/* 105 */     if ((this.field_145850_b.field_72995_K) && (this.field_145850_b.field_73012_v.nextFloat() < 0.2F)) {
/* 106 */       float x = this.field_174879_c.func_177972_a(facing).func_177958_n() + (facing.func_82601_c() != 0 ? 0.0F : 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F);
/* 107 */       float y = this.field_174879_c.func_177972_a(facing).func_177956_o() + (facing.func_96559_d() != 0 ? 0.0F : 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F);
/* 108 */       float z = this.field_174879_c.func_177972_a(facing).func_177952_p() + (facing.func_82599_e() != 0 ? 0.0F : 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F);
/* 109 */       Thaumcraft.proxy.getFX().drawLevitatorParticles(x, y, z, facing.func_82601_c() / 100.0D, facing.func_96559_d() / 100.0D, facing.func_82599_e() / 100.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawFXAt(Entity e)
/*     */   {
/* 115 */     if ((this.field_145850_b.field_72995_K) && (this.field_145850_b.field_73012_v.nextFloat() < 0.2F)) {
/* 116 */       float x = (float)(e.field_70165_t + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * e.field_70130_N);
/* 117 */       float y = (float)(e.field_70163_u + this.field_145850_b.field_73012_v.nextFloat() * e.field_70131_O);
/* 118 */       float z = (float)(e.field_70161_v + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * e.field_70130_N);
/* 119 */       Thaumcraft.proxy.getFX().drawLevitatorParticles(x, y, z, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01D, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01D, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbt)
/*     */   {
/* 129 */     this.range = nbt.func_74771_c("range");
/* 130 */     this.vis = nbt.func_74762_e("vis");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbt)
/*     */   {
/* 136 */     nbt.func_74774_a("range", (byte)this.range);
/* 137 */     nbt.func_74768_a("vis", this.vis);
/*     */   }
/*     */   
/*     */   public int getCost() {
/* 141 */     return this.ranges[this.range] * 2;
/*     */   }
/*     */   
/*     */   public void increaseRange(EntityPlayer playerIn) {
/* 145 */     this.rangeActual = 0;
/* 146 */     if (!this.field_145850_b.field_72995_K) {
/* 147 */       this.range += 1;
/* 148 */       if (this.range >= this.ranges.length) this.range = 0;
/* 149 */       func_70296_d();
/* 150 */       this.field_145850_b.func_175689_h(func_174877_v());
/* 151 */       playerIn.func_145747_a(new ChatComponentText(String.format(net.minecraft.util.StatCollector.func_74838_a("tc.levitator"), new Object[] { Integer.valueOf(this.ranges[this.range]), Integer.valueOf(getCost()) })));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MovingObjectPosition rayTrace(World world, Vec3 vec3d, Vec3 vec3d1, MovingObjectPosition fullblock)
/*     */   {
/* 161 */     return fullblock;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids)
/*     */   {
/* 167 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/* 168 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(0), getCuboidByFacing(facing)));
/*     */   }
/*     */   
/*     */   public Cuboid6 getCuboidByFacing(EnumFacing facing) {
/* 172 */     switch (facing) {
/* 173 */     default:  return new Cuboid6(func_174877_v().func_177958_n() + 0.375D, func_174877_v().func_177956_o() + 0.0625D, func_174877_v().func_177952_p() + 0.375D, func_174877_v().func_177958_n() + 0.625D, func_174877_v().func_177956_o() + 0.125D, func_174877_v().func_177952_p() + 0.625D);
/*     */     case DOWN: 
/* 175 */       return new Cuboid6(func_174877_v().func_177958_n() + 0.375D, func_174877_v().func_177956_o() + 0.875D, func_174877_v().func_177952_p() + 0.375D, func_174877_v().func_177958_n() + 0.625D, func_174877_v().func_177956_o() + 0.9375D, func_174877_v().func_177952_p() + 0.625D);
/*     */     case EAST: 
/* 177 */       return new Cuboid6(func_174877_v().func_177958_n() + 0.0625D, func_174877_v().func_177956_o() + 0.375D, func_174877_v().func_177952_p() + 0.375D, func_174877_v().func_177958_n() + 0.125D, func_174877_v().func_177956_o() + 0.625D, func_174877_v().func_177952_p() + 0.625D);
/*     */     case WEST: 
/* 179 */       return new Cuboid6(func_174877_v().func_177958_n() + 0.875D, func_174877_v().func_177956_o() + 0.375D, func_174877_v().func_177952_p() + 0.375D, func_174877_v().func_177958_n() + 0.9375D, func_174877_v().func_177956_o() + 0.625D, func_174877_v().func_177952_p() + 0.625D);
/*     */     case SOUTH: 
/* 181 */       return new Cuboid6(func_174877_v().func_177958_n() + 0.375D, func_174877_v().func_177956_o() + 0.375D, func_174877_v().func_177952_p() + 0.0625D, func_174877_v().func_177958_n() + 0.625D, func_174877_v().func_177956_o() + 0.625D, func_174877_v().func_177952_p() + 0.125D);
/*     */     }
/* 183 */     return new Cuboid6(func_174877_v().func_177958_n() + 0.375D, func_174877_v().func_177956_o() + 0.375D, func_174877_v().func_177952_p() + 0.875D, func_174877_v().func_177958_n() + 0.625D, func_174877_v().func_177956_o() + 0.625D, func_174877_v().func_177952_p() + 0.9375D);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileLevitator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */