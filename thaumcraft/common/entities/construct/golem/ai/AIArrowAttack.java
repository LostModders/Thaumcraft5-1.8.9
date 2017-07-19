/*     */ package thaumcraft.common.entities.construct.golem.ai;
/*     */ 
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ public class AIArrowAttack
/*     */   extends EntityAIArrowAttack
/*     */ {
/*     */   private final EntityLiving entityHost;
/*     */   private final IRangedAttackMob rangedAttackEntityHost;
/*     */   private int rangedAttackTime;
/*     */   private double entityMoveSpeed;
/*     */   private int field_75318_f;
/*     */   private int field_96561_g;
/*     */   private int maxRangedAttackTime;
/*     */   private float field_96562_i;
/*     */   private float maxAttackDistance;
/*     */   
/*     */   public AIArrowAttack(IRangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn)
/*     */   {
/*  29 */     super(attacker, movespeed, p_i1650_4_, maxAttackTime, maxAttackDistanceIn);
/*  30 */     this.rangedAttackTime = -1;
/*     */     
/*  32 */     if (!(attacker instanceof EntityLivingBase))
/*     */     {
/*  34 */       throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
/*     */     }
/*     */     
/*     */ 
/*  38 */     this.rangedAttackEntityHost = attacker;
/*  39 */     this.entityHost = ((EntityLiving)attacker);
/*  40 */     this.entityMoveSpeed = movespeed;
/*  41 */     this.field_96561_g = p_i1650_4_;
/*  42 */     this.maxRangedAttackTime = maxAttackTime;
/*  43 */     this.field_96562_i = maxAttackDistanceIn;
/*  44 */     this.maxAttackDistance = (maxAttackDistanceIn * maxAttackDistanceIn);
/*  45 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  53 */     if (this.entityHost.func_70638_az() == null)
/*     */     {
/*  55 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  59 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  66 */     return (func_75250_a()) || (!this.entityHost.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  72 */     this.field_75318_f = 0;
/*  73 */     this.rangedAttackTime = -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  79 */     if (this.entityHost.func_70638_az() == null) return;
/*  80 */     double d0 = this.entityHost.func_70092_e(this.entityHost.func_70638_az().field_70165_t, this.entityHost.func_70638_az().func_174813_aQ().field_72338_b, this.entityHost.func_70638_az().field_70161_v);
/*  81 */     boolean flag = this.entityHost.func_70635_at().func_75522_a(this.entityHost.func_70638_az());
/*     */     
/*  83 */     if (flag)
/*     */     {
/*  85 */       this.field_75318_f += 1;
/*     */     }
/*     */     else
/*     */     {
/*  89 */       this.field_75318_f = 0;
/*     */     }
/*     */     
/*  92 */     if ((d0 <= this.maxAttackDistance) && (this.field_75318_f >= 20))
/*     */     {
/*  94 */       this.entityHost.func_70661_as().func_75499_g();
/*     */     }
/*     */     else
/*     */     {
/*  98 */       this.entityHost.func_70661_as().func_75497_a(this.entityHost.func_70638_az(), this.entityMoveSpeed);
/*     */     }
/*     */     
/* 101 */     this.entityHost.func_70671_ap().func_75651_a(this.entityHost.func_70638_az(), 10.0F, 30.0F);
/*     */     
/* 103 */     if (--this.rangedAttackTime == 0)
/*     */     {
/* 105 */       if ((d0 > this.maxAttackDistance) || (!flag))
/*     */       {
/* 107 */         return;
/*     */       }
/*     */       
/* 110 */       float f = MathHelper.func_76133_a(d0) / this.field_96562_i;
/* 111 */       float lvt_5_1_ = MathHelper.func_76131_a(f, 0.1F, 1.0F);
/* 112 */       this.rangedAttackEntityHost.func_82196_d(this.entityHost.func_70638_az(), lvt_5_1_);
/* 113 */       this.rangedAttackTime = MathHelper.func_76141_d(f * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
/*     */     }
/* 115 */     else if (this.rangedAttackTime < 0)
/*     */     {
/* 117 */       float f2 = MathHelper.func_76133_a(d0) / this.field_96562_i;
/* 118 */       this.rangedAttackTime = MathHelper.func_76141_d(f2 * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/AIArrowAttack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */