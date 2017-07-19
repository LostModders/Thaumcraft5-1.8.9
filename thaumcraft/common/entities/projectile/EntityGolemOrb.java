/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityGolemOrb extends EntityThrowable implements net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityGolemOrb(World par1World)
/*     */   {
/*  18 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityGolemOrb(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase t, boolean r) {
/*  22 */     super(par1World, par2EntityLiving);
/*  23 */     this.target = t;
/*  24 */     this.red = r;
/*     */   }
/*     */   
/*  27 */   int targetID = 0;
/*     */   EntityLivingBase target;
/*  29 */   public boolean red = false;
/*     */   
/*     */ 
/*     */   protected float func_70185_h()
/*     */   {
/*  34 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  40 */     int id = -1;
/*  41 */     if (this.target != null) id = this.target.func_145782_y();
/*  42 */     data.writeInt(id);
/*  43 */     data.writeBoolean(this.red);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  48 */     int id = data.readInt();
/*     */     try {
/*  50 */       if (id >= 0) this.target = ((EntityLivingBase)this.field_70170_p.func_73045_a(id));
/*     */     } catch (Exception e) {}
/*  52 */     this.red = data.readBoolean();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  60 */     if ((!this.field_70170_p.field_72995_K) && (func_85052_h() != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY)) {
/*  61 */       mop.field_72308_g.func_70097_a(DamageSource.func_76354_b(this, func_85052_h()), (float)func_85052_h().func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111264_e).func_111126_e() * (this.red ? 1.0F : 0.6F));
/*     */     }
/*     */     
/*  64 */     this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:shock", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*  65 */     if (this.field_70170_p.field_72995_K) thaumcraft.common.Thaumcraft.proxy.getFX().burst(this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F);
/*  66 */     func_70106_y();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  72 */     super.func_70071_h_();
/*  73 */     if (this.field_70173_aa > (this.red ? 240 : 160)) { func_70106_y();
/*     */     }
/*  75 */     if (this.target != null) {
/*  76 */       double d = func_70068_e(this.target);
/*  77 */       double dx = this.target.field_70165_t - this.field_70165_t;
/*  78 */       double dy = this.target.func_174813_aQ().field_72338_b + this.target.field_70131_O * 0.6D - this.field_70163_u;
/*  79 */       double dz = this.target.field_70161_v - this.field_70161_v;
/*  80 */       double d13 = 0.2D;
/*  81 */       dx /= d;
/*  82 */       dy /= d;
/*  83 */       dz /= d;
/*     */       
/*  85 */       this.field_70159_w += dx * d13;
/*  86 */       this.field_70181_x += dy * d13;
/*  87 */       this.field_70179_y += dz * d13;
/*     */       
/*  89 */       this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.25F, 0.25F);
/*  90 */       this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.25F, 0.25F);
/*  91 */       this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.25F, 0.25F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/*  98 */     if (func_180431_b(p_70097_1_))
/*     */     {
/* 100 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 104 */     func_70018_K();
/*     */     
/* 106 */     if (p_70097_1_.func_76346_g() != null)
/*     */     {
/* 108 */       Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
/*     */       
/* 110 */       if (vec3 != null)
/*     */       {
/* 112 */         this.field_70159_w = vec3.field_72450_a;
/* 113 */         this.field_70181_x = vec3.field_72448_b;
/* 114 */         this.field_70179_y = vec3.field_72449_c;
/* 115 */         this.field_70159_w *= 0.9D;
/* 116 */         this.field_70181_x *= 0.9D;
/* 117 */         this.field_70179_y *= 0.9D;
/* 118 */         this.field_70170_p.func_72956_a(this, "thaumcraft:zap", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*     */       }
/* 120 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityGolemOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */