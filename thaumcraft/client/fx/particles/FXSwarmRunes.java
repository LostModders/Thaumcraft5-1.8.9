/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXSwarmRunes extends net.minecraft.client.particle.EntityFX
/*     */ {
/*     */   private Entity target;
/*  14 */   private float turnSpeed = 10.0F;
/*  15 */   private float speed = 0.2F;
/*     */   
/*     */   public FXSwarmRunes(World par1World, double x, double y, double z, Entity target, float r, float g, float b)
/*     */   {
/*  19 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*  20 */     this.field_70552_h = r;
/*  21 */     this.field_70553_i = g;
/*  22 */     this.field_70551_j = b;
/*  23 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.5F + 1.0F);
/*     */     
/*  25 */     this.target = target;
/*     */     
/*  27 */     float f3 = 0.2F;
/*  28 */     this.field_70159_w = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  29 */     this.field_70181_x = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  30 */     this.field_70179_y = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*     */     
/*  32 */     this.field_70545_g = 0.1F;
/*  33 */     this.field_70145_X = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public FXSwarmRunes(World par1World, double x, double y, double z, Entity target, float r, float g, float b, float sp, float ts, float pg)
/*     */   {
/*  39 */     this(par1World, x, y, z, target, r, g, b);
/*  40 */     this.speed = sp;
/*     */     
/*  42 */     this.field_70545_g = pg;
/*  43 */     this.particle = this.field_70146_Z.nextInt(16);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  49 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.25F + 1.0F;
/*     */     
/*  51 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*     */     
/*  53 */     float var8 = this.particle / 16.0F;
/*  54 */     float var9 = var8 + 0.0624375F;
/*  55 */     float var10 = 0.375F;
/*  56 */     float var11 = var10 + 0.0624375F;
/*  57 */     float var12 = 0.07F * this.field_70544_f * bob;
/*     */     
/*  59 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  61 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  63 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  65 */     float var16 = 1.0F;
/*     */     
/*  67 */     float trans = (50.0F - this.deathtimer) / 50.0F * 0.66F;
/*     */     
/*  69 */     int i = 240;
/*  70 */     int j = i >> 16 & 0xFFFF;
/*  71 */     int k = i & 0xFFFF;
/*  72 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, trans).func_181671_a(j, k).func_181675_d();
/*     */     
/*  74 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, trans).func_181671_a(j, k).func_181675_d();
/*     */     
/*  76 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, trans).func_181671_a(j, k).func_181675_d();
/*     */     
/*  78 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, trans).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  85 */     return 0;
/*     */   }
/*     */   
/*  88 */   int deathtimer = 0;
/*     */   
/*     */   public void func_70071_h_() {
/*  91 */     this.field_70169_q = this.field_70165_t;
/*  92 */     this.field_70167_r = this.field_70163_u;
/*  93 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  95 */     this.field_70546_d += 1;
/*     */     
/*     */ 
/*     */ 
/*  99 */     if ((this.field_70546_d > 200) || (this.target == null) || (this.target.field_70128_L) || (((this.target instanceof EntityLivingBase)) && (((EntityLivingBase)this.target).field_70725_aQ > 0))) {
/* 100 */       this.deathtimer += 1;
/* 101 */       this.field_70159_w *= 0.9D;
/* 102 */       this.field_70179_y *= 0.9D;
/* 103 */       this.field_70181_x -= this.field_70545_g / 2.0F;
/* 104 */       if (this.deathtimer > 50) {
/* 105 */         func_70106_y();
/*     */       }
/*     */     } else {
/* 108 */       this.field_70181_x += this.field_70545_g;
/*     */     }
/*     */     
/* 111 */     func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 112 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 114 */     this.field_70159_w *= 0.985D;
/* 115 */     this.field_70181_x *= 0.985D;
/* 116 */     this.field_70179_y *= 0.985D;
/*     */     
/* 118 */     if ((this.field_70546_d < 200) && (this.target != null) && (!this.target.field_70128_L) && ((!(this.target instanceof EntityLivingBase)) || (((EntityLivingBase)this.target).field_70725_aQ <= 0))) {
/* 119 */       boolean hurt = false;
/*     */       
/* 121 */       if ((this.target instanceof EntityLivingBase))
/* 122 */         hurt = ((EntityLivingBase)this.target).field_70737_aN > 0;
/* 123 */       if ((func_70068_e(this.target) > this.target.field_70130_N * this.target.field_70130_N) && (!hurt)) {
/* 124 */         faceEntity(this.target, this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F)), this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F)));
/*     */       }
/*     */       else
/*     */       {
/* 128 */         if ((hurt) && (func_70068_e(this.target) < this.target.field_70130_N * this.target.field_70130_N)) this.field_70546_d += 100;
/* 129 */         faceEntity(this.target, -(this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F))), -(this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F))));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 135 */       this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/*     */ 
/* 138 */       this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/*     */ 
/* 141 */       this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/* 143 */       setHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.speed, 15.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void faceEntity(Entity par1Entity, float par2, float par3)
/*     */   {
/* 150 */     double d0 = par1Entity.field_70165_t - this.field_70165_t;
/* 151 */     double d1 = par1Entity.field_70161_v - this.field_70161_v;
/* 152 */     double d2 = (par1Entity.func_174813_aQ().field_72338_b + par1Entity.func_174813_aQ().field_72337_e) / 2.0D - (func_174813_aQ().field_72338_b + func_174813_aQ().field_72337_e) / 2.0D;
/*     */     
/*     */ 
/* 155 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d1 * d1);
/* 156 */     float f2 = (float)(Math.atan2(d1, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 157 */     float f3 = (float)-(Math.atan2(d2, d3) * 180.0D / 3.141592653589793D);
/* 158 */     this.field_70125_A = updateRotation(this.field_70125_A, f3, par3);
/* 159 */     this.field_70177_z = updateRotation(this.field_70177_z, f2, par2);
/*     */   }
/*     */   
/*     */   private float updateRotation(float par1, float par2, float par3) {
/* 163 */     float f3 = MathHelper.func_76142_g(par2 - par1);
/*     */     
/* 165 */     if (f3 > par3) {
/* 166 */       f3 = par3;
/*     */     }
/*     */     
/* 169 */     if (f3 < -par3) {
/* 170 */       f3 = -par3;
/*     */     }
/*     */     
/* 173 */     return par1 + f3;
/*     */   }
/*     */   
/*     */   public void setHeading(double par1, double par3, double par5, float par7, float par8)
/*     */   {
/* 178 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*     */     
/* 180 */     par1 /= f2;
/* 181 */     par3 /= f2;
/* 182 */     par5 /= f2;
/* 183 */     par1 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 186 */     par3 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 189 */     par5 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 192 */     par1 *= par7;
/* 193 */     par3 *= par7;
/* 194 */     par5 *= par7;
/* 195 */     this.field_70159_w = par1;
/* 196 */     this.field_70181_x = par3;
/* 197 */     this.field_70179_y = par5;
/*     */   }
/*     */   
/* 200 */   public int particle = 0;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXSwarmRunes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */