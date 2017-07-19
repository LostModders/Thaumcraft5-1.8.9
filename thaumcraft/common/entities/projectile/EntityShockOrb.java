/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityShockOrb extends EntityThrowable
/*     */ {
/*     */   public EntityShockOrb(World par1World)
/*     */   {
/*  20 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityShockOrb(World par1World, EntityLivingBase p) {
/*  24 */     super(par1World, p);
/*  25 */     Vec3 v = p.func_70040_Z();
/*  26 */     func_70012_b(p.field_70165_t + v.field_72450_a / 2.0D, p.field_70163_u + p.func_70047_e() + v.field_72448_b / 2.0D, p.field_70161_v + v.field_72449_c / 2.0D, p.field_70177_z, p.field_70125_A);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  31 */     return 0.05F;
/*     */   }
/*     */   
/*  34 */   public int area = 4;
/*  35 */   public int damage = 5;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  42 */     if (!this.field_70170_p.field_72995_K) {
/*  43 */       java.util.ArrayList<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, Entity.class, this.area);
/*  44 */       for (Entity e : list) {
/*  45 */         if (EntityUtils.canEntityBeSeen(this, e))
/*  46 */           e.func_70097_a(DamageSource.func_76354_b(this, func_85052_h()), this.damage);
/*     */       }
/*  48 */       for (int a = 0; a < 20; a++) {
/*  49 */         int xx = MathHelper.floor_double(this.field_70165_t) + this.field_70146_Z.nextInt(this.area) - this.field_70146_Z.nextInt(this.area);
/*  50 */         int yy = MathHelper.floor_double(this.field_70163_u) + this.area;
/*  51 */         int zz = MathHelper.floor_double(this.field_70161_v) + this.field_70146_Z.nextInt(this.area) - this.field_70146_Z.nextInt(this.area);
/*  52 */         BlockPos bp = new BlockPos(xx, yy, zz);
/*  53 */         while ((this.field_70170_p.func_175623_d(bp)) && (yy > MathHelper.floor_double(this.field_70163_u) - this.area)) {
/*  54 */           yy--;
/*  55 */           bp = new BlockPos(xx, yy, zz);
/*     */         }
/*  57 */         if ((this.field_70170_p.func_175623_d(bp.func_177984_a())) && (!this.field_70170_p.func_175623_d(bp)) && (this.field_70170_p.func_180495_p(bp.func_177984_a()).func_177230_c() != BlocksTC.effect) && (EntityUtils.canEntityBeSeen(this, xx + 0.5D, yy + 1.5D, zz + 0.5D)))
/*     */         {
/*     */ 
/*  60 */           this.field_70170_p.func_180501_a(bp.func_177984_a(), BlocksTC.effect.func_176203_a(0), 3);
/*     */         }
/*     */       }
/*     */     } else {
/*  64 */       thaumcraft.common.Thaumcraft.proxy.getFX().burst(this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0F); }
/*  65 */     this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:shock", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*  66 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  71 */     super.func_70071_h_();
/*  72 */     if (this.field_70173_aa > 500) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/*  79 */     if (func_180431_b(p_70097_1_))
/*     */     {
/*  81 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  85 */     func_70018_K();
/*     */     
/*  87 */     if (p_70097_1_.func_76346_g() != null)
/*     */     {
/*  89 */       Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
/*     */       
/*  91 */       if (vec3 != null)
/*     */       {
/*  93 */         this.field_70159_w = vec3.field_72450_a;
/*  94 */         this.field_70181_x = vec3.field_72448_b;
/*  95 */         this.field_70179_y = vec3.field_72449_c;
/*  96 */         this.field_70159_w *= 0.9D;
/*  97 */         this.field_70181_x *= 0.9D;
/*  98 */         this.field_70179_y *= 0.9D;
/*  99 */         this.field_70170_p.func_72956_a(this, "thaumcraft:zap", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*     */       }
/* 101 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 105 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityShockOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */