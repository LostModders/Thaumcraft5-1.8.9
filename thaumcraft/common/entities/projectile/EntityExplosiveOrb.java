/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityExplosiveOrb extends EntityThrowable
/*     */ {
/*     */   public EntityExplosiveOrb(World par1World)
/*     */   {
/*  15 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityExplosiveOrb(World par1World, EntityLivingBase p) {
/*  19 */     super(par1World, p);
/*  20 */     Vec3 v = p.func_70040_Z();
/*  21 */     func_70012_b(p.field_70165_t + v.field_72450_a / 2.0D, p.field_70163_u + p.func_70047_e() + v.field_72448_b / 2.0D, p.field_70161_v + v.field_72449_c / 2.0D, p.field_70177_z, p.field_70125_A);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  26 */     return 0.01F;
/*     */   }
/*     */   
/*  29 */   public float strength = 1.0F;
/*  30 */   public boolean onFire = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(net.minecraft.util.MovingObjectPosition mop)
/*     */   {
/*  37 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  39 */       if (mop.field_72308_g != null)
/*     */       {
/*  41 */         mop.field_72308_g.func_70097_a(causeFireballDamage(this, func_85052_h()), this.strength * 1.5F);
/*     */       }
/*     */       
/*  44 */       this.field_70170_p.func_72885_a((Entity)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.strength, this.onFire, false);
/*  45 */       func_70106_y();
/*     */     }
/*  47 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public static DamageSource causeFireballDamage(EntityExplosiveOrb p_76362_0_, Entity p_76362_1_)
/*     */   {
/*  52 */     return p_76362_1_ == null ? new net.minecraft.util.EntityDamageSourceIndirect("onFire", p_76362_0_, p_76362_0_).func_76361_j().func_76349_b() : new net.minecraft.util.EntityDamageSourceIndirect("fireball", p_76362_0_, p_76362_1_).func_76361_j().func_76349_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  60 */     super.func_70071_h_();
/*  61 */     if (this.field_70170_p.field_72995_K) {
/*  62 */       thaumcraft.common.Thaumcraft.proxy.getFX().drawGenericParticles(this.field_70169_q + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F, this.field_70167_r + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F, this.field_70166_s + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.8F, false, 151, 9, 1, 7 + this.field_70146_Z.nextInt(5), 0, 2.0F + this.field_70146_Z.nextFloat(), 0.5F, 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */     if (this.field_70173_aa > 500) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/*  75 */     if (func_180431_b(p_70097_1_))
/*     */     {
/*  77 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  81 */     func_70018_K();
/*     */     
/*  83 */     if (p_70097_1_.func_76346_g() != null)
/*     */     {
/*  85 */       Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
/*     */       
/*  87 */       if (vec3 != null)
/*     */       {
/*  89 */         this.field_70159_w = vec3.field_72450_a;
/*  90 */         this.field_70181_x = vec3.field_72448_b;
/*  91 */         this.field_70179_y = vec3.field_72449_c;
/*  92 */         this.field_70159_w *= 0.9D;
/*  93 */         this.field_70181_x *= 0.9D;
/*  94 */         this.field_70179_y *= 0.9D;
/*     */       }
/*  96 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityExplosiveOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */