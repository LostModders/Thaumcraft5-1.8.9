/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityEmber extends EntityThrowable implements net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityEmber(World par1World)
/*     */   {
/*  19 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityEmber(World par1World, EntityLivingBase p, float scatter) {
/*  23 */     super(par1World, p);
/*  24 */     Vec3 v = p.func_70040_Z();
/*  25 */     func_70012_b(p.field_70165_t + v.field_72450_a / 2.0D, p.field_70163_u + p.func_70047_e() + v.field_72448_b / 2.0D, p.field_70161_v + v.field_72449_c / 2.0D, p.field_70177_z, p.field_70125_A);
/*  26 */     func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, func_70182_d(), scatter);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  31 */     return 0.0F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d()
/*     */   {
/*  36 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  42 */     if (this.field_70173_aa > this.duration) { func_70106_y();
/*     */     }
/*  44 */     if (this.duration <= 20) {
/*  45 */       this.field_70159_w *= 0.95D;
/*  46 */       this.field_70181_x *= 0.95D;
/*  47 */       this.field_70179_y *= 0.95D;
/*     */     } else {
/*  49 */       this.field_70159_w *= 0.975D;
/*  50 */       this.field_70181_x *= 0.975D;
/*  51 */       this.field_70179_y *= 0.975D;
/*     */     }
/*     */     
/*  54 */     if (this.field_70122_E) {
/*  55 */       this.field_70159_w *= 0.66D;
/*  56 */       this.field_70181_x *= 0.66D;
/*  57 */       this.field_70179_y *= 0.66D;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  62 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  67 */   public int duration = 20;
/*  68 */   public int firey = 0;
/*  69 */   public float damage = 1.0F;
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  73 */     data.writeByte(this.duration);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  78 */     this.duration = data.readByte();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  87 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  89 */       if (mop.field_72308_g != null)
/*     */       {
/*  91 */         if ((!mop.field_72308_g.func_70045_F()) && (mop.field_72308_g.func_70097_a(new EntityDamageSourceIndirect("fireball", this, func_85052_h()).func_76361_j(), this.damage)))
/*     */         {
/*     */ 
/*  94 */           mop.field_72308_g.func_70015_d(3 + this.firey);
/*     */         }
/*     */       }
/*  97 */       else if (this.field_70146_Z.nextFloat() < 0.025F * this.firey)
/*     */       {
/*  99 */         boolean flag = true;
/*     */         
/* 101 */         if ((func_85052_h() != null) && ((func_85052_h() instanceof net.minecraft.entity.EntityLiving)))
/*     */         {
/* 103 */           flag = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing");
/*     */         }
/*     */         
/* 106 */         if (flag)
/*     */         {
/* 108 */           BlockPos blockpos = mop.func_178782_a().func_177972_a(mop.field_178784_b);
/*     */           
/* 110 */           if (this.field_70170_p.func_175623_d(blockpos))
/*     */           {
/* 112 */             this.field_70170_p.func_175656_a(blockpos, net.minecraft.init.Blocks.field_150480_ab.func_176223_P());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 118 */     func_70106_y();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 126 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 132 */     super.func_70014_b(par1NBTTagCompound);
/* 133 */     par1NBTTagCompound.func_74776_a("damage", this.damage);
/* 134 */     par1NBTTagCompound.func_74768_a("firey", this.firey);
/* 135 */     par1NBTTagCompound.func_74768_a("duration", this.duration);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 141 */     super.func_70037_a(par1NBTTagCompound);
/* 142 */     this.damage = par1NBTTagCompound.func_74760_g("damage");
/* 143 */     this.firey = par1NBTTagCompound.func_74762_e("firey");
/* 144 */     this.duration = par1NBTTagCompound.func_74762_e("duration");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/* 151 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/* 157 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityEmber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */