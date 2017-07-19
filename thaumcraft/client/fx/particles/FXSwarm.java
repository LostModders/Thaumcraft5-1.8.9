/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXSwarm extends EntityFX
/*     */ {
/*     */   private Entity target;
/*  16 */   private float turnSpeed = 10.0F;
/*  17 */   private float speed = 0.2F;
/*     */   
/*     */   public FXSwarm(World par1World, double x, double y, double z, Entity target, float r, float g, float b)
/*     */   {
/*  21 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*  22 */     this.field_70552_h = r;
/*  23 */     this.field_70553_i = g;
/*  24 */     this.field_70551_j = b;
/*  25 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.5F + 1.0F);
/*     */     
/*  27 */     this.target = target;
/*     */     
/*  29 */     float f3 = 0.2F;
/*  30 */     this.field_70159_w = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  31 */     this.field_70181_x = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  32 */     this.field_70179_y = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*     */     
/*  34 */     this.field_70545_g = 0.1F;
/*  35 */     this.field_70145_X = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public FXSwarm(World par1World, double x, double y, double z, Entity target, float r, float g, float b, float sp, float ts, float pg)
/*     */   {
/*  41 */     this(par1World, x, y, z, target, r, g, b);
/*  42 */     this.speed = sp;
/*     */     
/*  44 */     this.field_70545_g = pg;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  50 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.25F + 1.0F;
/*     */     
/*  52 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  53 */     int part = 7 + this.field_70546_d % 8;
/*     */     
/*  55 */     float var8 = part / 16.0F;
/*  56 */     float var9 = var8 + 0.0624375F;
/*  57 */     float var10 = 0.25F;
/*  58 */     float var11 = var10 + 0.0624375F;
/*  59 */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     
/*  61 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  63 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  65 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  67 */     float var16 = 1.0F;
/*     */     
/*  69 */     float trans = (50.0F - this.deathtimer) / 50.0F;
/*     */     
/*  71 */     int i = 240;
/*  72 */     int j = i >> 16 & 0xFFFF;
/*  73 */     int k = i & 0xFFFF;
/*  74 */     float dd = 1.0F;
/*  75 */     if (((this.target instanceof EntityLivingBase)) && (((EntityLivingBase)this.target).field_70737_aN > 0)) { dd = 2.0F;
/*     */     }
/*  77 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16 / dd, this.field_70551_j * var16 / dd, trans).func_181671_a(j, k).func_181675_d();
/*     */     
/*  79 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16 / dd, this.field_70551_j * var16 / dd, trans).func_181671_a(j, k).func_181675_d();
/*     */     
/*  81 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16 / dd, this.field_70551_j * var16 / dd, trans).func_181671_a(j, k).func_181675_d();
/*     */     
/*  83 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16 / dd, this.field_70551_j * var16 / dd, trans).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  90 */     return 1;
/*     */   }
/*     */   
/*  93 */   int deathtimer = 0;
/*     */   
/*     */   public void func_70071_h_() {
/*  96 */     this.field_70169_q = this.field_70165_t;
/*  97 */     this.field_70167_r = this.field_70163_u;
/*  98 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 100 */     this.field_70546_d += 1;
/*     */     
/* 102 */     if ((this.target == null) || (this.target.field_70128_L) || (((this.target instanceof EntityLivingBase)) && (((EntityLivingBase)this.target).field_70725_aQ > 0)))
/*     */     {
/*     */ 
/* 105 */       this.deathtimer += 1;
/* 106 */       this.field_70159_w *= 0.9D;
/* 107 */       this.field_70179_y *= 0.9D;
/* 108 */       this.field_70181_x -= this.field_70545_g / 2.0F;
/* 109 */       if (this.deathtimer > 50) {
/* 110 */         func_70106_y();
/*     */       }
/*     */     } else {
/* 113 */       this.field_70181_x += this.field_70545_g;
/*     */     }
/*     */     
/* 116 */     func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 117 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 119 */     this.field_70159_w *= 0.985D;
/* 120 */     this.field_70181_x *= 0.985D;
/* 121 */     this.field_70179_y *= 0.985D;
/*     */     
/* 123 */     if ((this.target != null) && (!this.target.field_70128_L) && ((!(this.target instanceof EntityLivingBase)) || (((EntityLivingBase)this.target).field_70725_aQ <= 0)))
/*     */     {
/*     */ 
/* 126 */       boolean hurt = false;
/*     */       
/* 128 */       if ((this.target instanceof EntityLivingBase))
/* 129 */         hurt = ((EntityLivingBase)this.target).field_70737_aN > 0;
/* 130 */       if ((func_70068_e(this.target) > this.target.field_70130_N) && (!hurt)) {
/* 131 */         faceEntity(this.target, this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F)), this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F)));
/*     */       }
/*     */       else
/*     */       {
/* 135 */         faceEntity(this.target, -(this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F))), -(this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F))));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 141 */       this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/*     */ 
/* 144 */       this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/*     */ 
/* 147 */       this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/* 149 */       setHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.speed, 15.0F);
/*     */     }
/*     */     
/* 152 */     if ((buzzcount.size() < 3) && (this.field_70146_Z.nextInt(50) == 0) && (this.field_70170_p.func_72890_a(this, 8.0D) != null))
/*     */     {
/* 154 */       this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:fly", 0.03F, 0.5F + this.field_70146_Z.nextFloat() * 0.4F, false);
/*     */       
/* 156 */       buzzcount.add(Long.valueOf(System.nanoTime() + 1500000L));
/*     */     }
/*     */     
/* 159 */     if ((buzzcount.size() >= 3) && (((Long)buzzcount.get(0)).longValue() < System.nanoTime()))
/* 160 */       buzzcount.remove(0);
/*     */   }
/*     */   
/* 163 */   private static ArrayList<Long> buzzcount = new ArrayList();
/*     */   
/*     */   public void faceEntity(Entity par1Entity, float par2, float par3) {
/* 166 */     double d0 = par1Entity.field_70165_t - this.field_70165_t;
/* 167 */     double d1 = par1Entity.field_70161_v - this.field_70161_v;
/* 168 */     double d2 = (par1Entity.func_174813_aQ().field_72338_b + par1Entity.func_174813_aQ().field_72337_e) / 2.0D - (func_174813_aQ().field_72338_b + func_174813_aQ().field_72337_e) / 2.0D;
/*     */     
/*     */ 
/* 171 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d1 * d1);
/* 172 */     float f2 = (float)(Math.atan2(d1, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 173 */     float f3 = (float)-(Math.atan2(d2, d3) * 180.0D / 3.141592653589793D);
/* 174 */     this.field_70125_A = updateRotation(this.field_70125_A, f3, par3);
/* 175 */     this.field_70177_z = updateRotation(this.field_70177_z, f2, par2);
/*     */   }
/*     */   
/*     */   private float updateRotation(float par1, float par2, float par3) {
/* 179 */     float f3 = MathHelper.func_76142_g(par2 - par1);
/*     */     
/* 181 */     if (f3 > par3) {
/* 182 */       f3 = par3;
/*     */     }
/*     */     
/* 185 */     if (f3 < -par3) {
/* 186 */       f3 = -par3;
/*     */     }
/*     */     
/* 189 */     return par1 + f3;
/*     */   }
/*     */   
/*     */   public void setHeading(double par1, double par3, double par5, float par7, float par8)
/*     */   {
/* 194 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*     */     
/* 196 */     par1 /= f2;
/* 197 */     par3 /= f2;
/* 198 */     par5 /= f2;
/* 199 */     par1 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 202 */     par3 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 205 */     par5 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 208 */     par1 *= par7;
/* 209 */     par3 *= par7;
/* 210 */     par5 *= par7;
/* 211 */     this.field_70159_w = par1;
/* 212 */     this.field_70181_x = par3;
/* 213 */     this.field_70179_y = par5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 316 */   public int particle = 40;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXSwarm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */