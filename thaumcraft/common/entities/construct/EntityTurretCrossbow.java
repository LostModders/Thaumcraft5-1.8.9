/*     */ package thaumcraft.common.entities.construct;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget.Sorter;
/*     */ import net.minecraft.entity.ai.EntityAITarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityDispenser;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*     */ 
/*     */ public class EntityTurretCrossbow extends EntityOwnedConstruct implements IRangedAttackMob
/*     */ {
/*     */   public EntityTurretCrossbow(World worldIn)
/*     */   {
/*  53 */     super(worldIn);
/*  54 */     func_70105_a(0.95F, 1.25F);
/*  55 */     this.field_70714_bg.func_75776_a(1, new EntityAIArrowAttack(this, 0.0D, 20, 60, 24.0F));
/*  56 */     this.field_70714_bg.func_75776_a(2, new EntityAIWatchTarget(this));
/*  57 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  58 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestValidTarget(this, EntityLiving.class, 5, true, false, IMob.field_82192_a));
/*  59 */     this.field_70138_W = 0.0F;
/*     */   }
/*     */   
/*     */   public EntityTurretCrossbow(World worldIn, BlockPos pos) {
/*  63 */     this(worldIn);
/*  64 */     func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_82196_d(EntityLivingBase target, float range)
/*     */   {
/*  70 */     if ((func_70694_bm() != null) && (func_70694_bm().field_77994_a > 0)) {
/*  71 */       if (func_70694_bm().func_77973_b() == ItemsTC.primalArrows) {
/*  72 */         EntityPrimalArrow entityarrow = new EntityPrimalArrow(this.field_70170_p, this, 1.6F, func_70694_bm().func_77952_i());
/*  73 */         entityarrow.func_70239_b(3.0D + range * 2.0F + this.field_70146_Z.nextGaussian() * 0.25D);
/*  74 */         entityarrow.func_70240_a(func_70694_bm().func_77952_i() == 3 ? 1 : 0);
/*  75 */         Vec3 vec3d = func_70676_i(1.0F);
/*  76 */         entityarrow.field_70165_t -= vec3d.field_72450_a * 0.8999999761581421D;
/*  77 */         entityarrow.field_70163_u -= vec3d.field_72448_b * 0.8999999761581421D;
/*  78 */         entityarrow.field_70161_v -= vec3d.field_72449_c * 0.8999999761581421D;
/*  79 */         double d0 = target.field_70165_t - this.field_70165_t;
/*  80 */         double d1 = target.func_174813_aQ().field_72338_b + target.func_70047_e() + range * range * 4.0F - entityarrow.field_70163_u;
/*  81 */         double d2 = target.field_70161_v - this.field_70161_v;
/*  82 */         entityarrow.func_70186_c(d0, d1, d2, 1.6F, 1.0F);
/*  83 */         this.field_70170_p.func_72838_d(entityarrow);
/*     */       } else {
/*  85 */         EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, target, 1.6F, 3.0F);
/*  86 */         entityarrow.func_70239_b(2.5D + range * 2.0F + this.field_70146_Z.nextGaussian() * 0.25D);
/*  87 */         Vec3 vec3d = func_70676_i(1.0F);
/*  88 */         entityarrow.field_70165_t -= vec3d.field_72450_a * 0.8999999761581421D;
/*  89 */         entityarrow.field_70163_u -= vec3d.field_72448_b * 0.8999999761581421D;
/*  90 */         entityarrow.field_70161_v -= vec3d.field_72449_c * 0.8999999761581421D;
/*  91 */         entityarrow.field_70251_a = 1;
/*  92 */         double d0 = target.field_70165_t - this.field_70165_t;
/*  93 */         double d1 = target.func_174813_aQ().field_72338_b + target.func_70047_e() + range * range * 4.0F - entityarrow.field_70163_u;
/*  94 */         double d2 = target.field_70161_v - this.field_70161_v;
/*  95 */         entityarrow.func_70186_c(d0, d1, d2, 1.6F, 2.0F);
/*  96 */         this.field_70170_p.func_72838_d(entityarrow);
/*     */       }
/*  98 */       this.field_70170_p.func_72960_a(this, (byte)16);
/*  99 */       func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 100 */       func_70694_bm().field_77994_a -= 1;
/* 101 */       if (func_70694_bm().field_77994_a <= 0) {
/* 102 */         func_70062_b(0, null);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 112 */     if (par1 == 16)
/*     */     {
/* 114 */       if (!this.field_82175_bq) {
/* 115 */         this.field_110158_av = -1;
/* 116 */         this.field_82175_bq = true;
/*     */       }
/*     */       
/*     */     }
/* 120 */     else if (par1 == 17)
/*     */     {
/* 122 */       if (!this.isLoadInProgress) {
/* 123 */         this.loadProgressInt = -1;
/* 124 */         this.isLoadInProgress = true;
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/* 129 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/* 133 */   int loadProgressInt = 0;
/* 134 */   boolean isLoadInProgress = false;
/* 135 */   float loadProgress = 0.0F;
/* 136 */   float prevLoadProgress = 0.0F;
/* 137 */   public float loadProgressForRender = 0.0F;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getLoadProgress(float pt)
/*     */   {
/* 142 */     float f1 = this.loadProgress - this.prevLoadProgress;
/*     */     
/* 144 */     if (f1 < 0.0F)
/*     */     {
/* 146 */       f1 += 1.0F;
/*     */     }
/*     */     
/* 149 */     return this.prevLoadProgress + f1 * pt;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_82168_bl()
/*     */   {
/* 155 */     if (this.field_82175_bq)
/*     */     {
/* 157 */       this.field_110158_av += 1;
/*     */       
/* 159 */       if (this.field_110158_av >= 6)
/*     */       {
/* 161 */         this.field_110158_av = 0;
/* 162 */         this.field_82175_bq = false;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 167 */       this.field_110158_av = 0;
/*     */     }
/* 169 */     this.field_70733_aJ = (this.field_110158_av / 6.0F);
/*     */     
/* 171 */     if (this.isLoadInProgress)
/*     */     {
/* 173 */       this.loadProgressInt += 1;
/*     */       
/* 175 */       if (this.loadProgressInt >= 10)
/*     */       {
/* 177 */         this.loadProgressInt = 0;
/* 178 */         this.isLoadInProgress = false;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 183 */       this.loadProgressInt = 0;
/*     */     }
/* 185 */     this.loadProgress = (this.loadProgressInt / 10.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70030_z()
/*     */   {
/* 191 */     this.prevLoadProgress = this.loadProgress;
/* 192 */     if ((!this.field_70170_p.field_72995_K) && ((func_70694_bm() == null) || (func_70694_bm().field_77994_a <= 0))) {
/* 193 */       BlockPos p = func_180425_c().func_177977_b();
/* 194 */       TileEntity t = this.field_70170_p.func_175625_s(p);
/* 195 */       if ((t != null) && ((t instanceof TileEntityDispenser)) && (BlockDispenser.func_149937_b(t.func_145832_p()) == EnumFacing.UP)) {
/* 196 */         TileEntityDispenser d = (TileEntityDispenser)t;
/* 197 */         for (int a = 0; a < d.func_70302_i_(); a++) {
/* 198 */           if ((d.func_70301_a(a) != null) && ((d.func_70301_a(a).func_77973_b() == Items.field_151032_g) || (d.func_70301_a(a).func_77973_b() == ItemsTC.primalArrows)))
/*     */           {
/* 200 */             func_70062_b(0, d.func_70298_a(a, d.func_70301_a(a).field_77994_a));
/* 201 */             func_85030_a("thaumcraft:cameraticks", 1.0F, 1.0F);
/* 202 */             this.field_70170_p.func_72960_a(this, (byte)17);
/* 203 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 208 */     if (func_70115_ae()) this.field_70163_u += 0.3D;
/* 209 */     super.func_70030_z();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/* 216 */     return func_142012_a(otherEntity.func_96124_cp());
/*     */   }
/*     */   
/*     */ 
/*     */   public Team func_96124_cp()
/*     */   {
/* 222 */     if (isOwned())
/*     */     {
/* 224 */       EntityLivingBase entitylivingbase = getOwnerEntity();
/*     */       
/* 226 */       if (entitylivingbase != null)
/*     */       {
/* 228 */         return entitylivingbase.func_96124_cp();
/*     */       }
/*     */     }
/*     */     
/* 232 */     return super.func_96124_cp();
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 238 */     return this.field_70131_O * 0.66F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 244 */     super.func_110147_ax();
/* 245 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/* 246 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(24.0D);
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/* 251 */     return 2;
/*     */   }
/*     */   
/* 254 */   boolean attackedLastTick = false;
/* 255 */   int attackCount = 0;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 259 */     super.func_70071_h_();
/*     */     
/* 261 */     if ((func_70638_az() != null) && (func_142014_c(func_70638_az()))) { func_70624_b(null);
/*     */     }
/* 263 */     if (!this.field_70170_p.field_72995_K) {
/* 264 */       this.field_70177_z = this.field_70759_as;
/*     */       
/* 266 */       if (this.field_70173_aa % 80 == 0) func_70691_i(1.0F);
/*     */     }
/*     */     else {
/* 269 */       func_82168_bl();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70104_M()
/*     */   {
/* 281 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/* 287 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 295 */     super.func_70037_a(nbt);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 301 */     super.func_70014_b(nbt);
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount)
/*     */   {
/* 306 */     this.field_70177_z = ((float)(this.field_70177_z + func_70681_au().nextGaussian() * 45.0D));
/* 307 */     this.field_70125_A = ((float)(this.field_70125_A + func_70681_au().nextGaussian() * 20.0D));
/* 308 */     return super.func_70097_a(source, amount);
/*     */   }
/*     */   
/*     */   public void func_70653_a(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_)
/*     */   {
/* 313 */     super.func_70653_a(p_70653_1_, p_70653_2_, p_70653_3_ / 10.0D, p_70653_5_ / 10.0D);
/* 314 */     if (this.field_70181_x > 0.1D) this.field_70181_x = 0.1D;
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 319 */     if ((!this.field_70170_p.field_72995_K) && (isOwner(player)) && (!this.field_70128_L)) {
/* 320 */       if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof IWand))) {
/* 321 */         func_85030_a("thaumcraft:zap", 1.0F, 1.0F);
/* 322 */         dropAmmo();
/* 323 */         func_70099_a(new ItemStack(ItemsTC.turretPlacer, 1, 0), 0.5F);
/* 324 */         func_70106_y();
/* 325 */         player.func_71038_i();
/*     */       } else {
/* 327 */         player.openGui(Thaumcraft.instance, 16, this.field_70170_p, func_145782_y(), 0, 0);
/*     */       }
/* 329 */       return true;
/*     */     }
/*     */     
/* 332 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */   public void func_70091_d(double x, double y, double z)
/*     */   {
/* 337 */     super.func_70091_d(x / 5.0D, y, z / 5.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource cause)
/*     */   {
/* 343 */     super.func_70645_a(cause);
/*     */     
/* 345 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 347 */       dropAmmo();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void dropAmmo() {
/* 352 */     if (func_70694_bm() != null) {
/* 353 */       func_70099_a(func_70694_bm(), 0.5F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/* 359 */     float b = p_70628_2_ * 0.15F;
/* 360 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(ItemsTC.mind), 0.5F);
/* 361 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(ItemsTC.gear), 0.5F);
/* 362 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plank), 0.5F);
/* 363 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plank), 0.5F);
/*     */   }
/*     */   
/*     */   protected MovingObjectPosition getMovingObjectPosition()
/*     */   {
/* 368 */     float f = this.field_70127_C + (this.field_70125_A - this.field_70127_C);
/* 369 */     float f1 = this.field_70126_B + (this.field_70177_z - this.field_70126_B);
/* 370 */     double d0 = this.field_70169_q + (this.field_70165_t - this.field_70169_q);
/* 371 */     double d1 = this.field_70167_r + (this.field_70163_u - this.field_70167_r) + func_70047_e();
/* 372 */     double d2 = this.field_70166_s + (this.field_70161_v - this.field_70166_s);
/* 373 */     Vec3 vec3 = new Vec3(d0, d1, d2);
/* 374 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/* 375 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/* 376 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/* 377 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/* 378 */     float f6 = f3 * f4;
/* 379 */     float f7 = f2 * f4;
/* 380 */     double d3 = 5.0D;
/* 381 */     Vec3 vec31 = vec3.func_72441_c(f6 * d3, f5 * d3, f7 * d3);
/* 382 */     return this.field_70170_p.func_147447_a(vec3, vec31, true, false, false);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70646_bf()
/*     */   {
/* 388 */     return 20;
/*     */   }
/*     */   
/*     */ 
/*     */   protected class EntityAIWatchTarget
/*     */     extends EntityAIBase
/*     */   {
/*     */     protected EntityLiving theWatcher;
/*     */     protected Entity closestEntity;
/*     */     private int lookTime;
/*     */     
/*     */     public EntityAIWatchTarget(EntityLiving p_i1631_1_)
/*     */     {
/* 401 */       this.theWatcher = p_i1631_1_;
/* 402 */       func_75248_a(2);
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_75250_a()
/*     */     {
/* 408 */       if (this.theWatcher.func_70638_az() != null)
/*     */       {
/* 410 */         this.closestEntity = this.theWatcher.func_70638_az();
/*     */       }
/*     */       
/* 413 */       return this.closestEntity != null;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_75253_b()
/*     */     {
/* 419 */       float d = (float)getTargetDistance();
/* 420 */       return this.closestEntity.func_70089_S();
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75249_e()
/*     */     {
/* 426 */       this.lookTime = (40 + this.theWatcher.func_70681_au().nextInt(40));
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75251_c()
/*     */     {
/* 432 */       this.closestEntity = null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75246_d()
/*     */     {
/* 438 */       this.theWatcher.func_70671_ap().func_75650_a(this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + this.closestEntity.func_70047_e(), this.closestEntity.field_70161_v, 10.0F, this.theWatcher.func_70646_bf());
/* 439 */       this.lookTime -= 1;
/*     */     }
/*     */     
/*     */     protected double getTargetDistance()
/*     */     {
/* 444 */       IAttributeInstance iattributeinstance = this.theWatcher.func_110148_a(SharedMonsterAttributes.field_111265_b);
/* 445 */       return iattributeinstance == null ? 16.0D : iattributeinstance.func_111126_e();
/*     */     }
/*     */   }
/*     */   
/*     */   protected class EntityAINearestValidTarget extends EntityAITarget
/*     */   {
/*     */     protected final Class targetClass;
/*     */     private final int targetChance;
/*     */     protected final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
/*     */     protected Predicate targetEntitySelector;
/*     */     protected EntityLivingBase targetEntity;
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45878_1_, Class p_i45878_2_, boolean p_i45878_3_)
/*     */     {
/* 459 */       this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false);
/*     */     }
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45879_1_, Class p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_)
/*     */     {
/* 464 */       this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate)null);
/*     */     }
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45880_1_, Class p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate tselector)
/*     */     {
/* 469 */       super(p_i45880_4_, p_i45880_5_);
/* 470 */       this.targetClass = p_i45880_2_;
/* 471 */       this.targetChance = p_i45880_3_;
/* 472 */       this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(p_i45880_1_);
/* 473 */       func_75248_a(1);
/* 474 */       this.targetEntitySelector = new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00001621";
/*     */         
/*     */         public boolean applySelection(EntityLivingBase entity) {
/* 479 */           if ((tselector != null) && (!tselector.apply(entity)))
/*     */           {
/* 481 */             return false;
/*     */           }
/*     */           
/*     */ 
/* 485 */           if ((entity instanceof EntityPlayer))
/*     */           {
/* 487 */             double d0 = EntityTurretCrossbow.EntityAINearestValidTarget.this.func_111175_f();
/*     */             
/* 489 */             if (entity.func_70093_af())
/*     */             {
/* 491 */               d0 *= 0.800000011920929D;
/*     */             }
/*     */             
/* 494 */             if (entity.func_82150_aj())
/*     */             {
/* 496 */               float f = ((EntityPlayer)entity).func_82243_bO();
/*     */               
/* 498 */               if (f < 0.1F)
/*     */               {
/* 500 */                 f = 0.1F;
/*     */               }
/*     */               
/* 503 */               d0 *= 0.7F * f;
/*     */             }
/*     */             
/* 506 */             if (entity.func_70032_d(EntityTurretCrossbow.EntityAINearestValidTarget.this.field_75299_d) > d0)
/*     */             {
/* 508 */               return false;
/*     */             }
/*     */           }
/*     */           
/* 512 */           return EntityTurretCrossbow.EntityAINearestValidTarget.this.func_75296_a(entity, false);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_)
/*     */         {
/* 517 */           return applySelection((EntityLivingBase)p_apply_1_);
/*     */         }
/*     */       };
/*     */     }
/*     */     
/*     */ 
/*     */     protected boolean func_75296_a(EntityLivingBase p_75296_1_, boolean p_75296_2_)
/*     */     {
/* 525 */       if (!func_179445_a(this.field_75299_d, p_75296_1_, p_75296_2_, this.field_75297_f))
/*     */       {
/* 527 */         return false;
/*     */       }
/* 529 */       if (!this.field_75299_d.func_180485_d(new BlockPos(p_75296_1_)))
/*     */       {
/* 531 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 535 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean func_75250_a()
/*     */     {
/* 542 */       if ((this.targetChance > 0) && (this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0))
/*     */       {
/* 544 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 548 */       double d0 = func_111175_f();
/* 549 */       List list = this.field_75299_d.field_70170_p.func_175647_a(this.targetClass, this.field_75299_d.func_174813_aQ().func_72314_b(d0, 4.0D, d0), Predicates.and(this.targetEntitySelector, EntitySelectors.field_180132_d));
/* 550 */       Collections.sort(list, this.theNearestAttackableTargetSorter);
/*     */       
/* 552 */       if (list.isEmpty())
/*     */       {
/* 554 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 558 */       this.targetEntity = ((EntityLivingBase)list.get(0));
/* 559 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void func_75249_e()
/*     */     {
/* 567 */       this.field_75299_d.func_70624_b(this.targetEntity);
/* 568 */       super.func_75249_e();
/*     */     }
/*     */     
/*     */     public class Sorter implements Comparator
/*     */     {
/*     */       private final Entity theEntity;
/*     */       private static final String __OBFID = "CL_00001622";
/*     */       
/*     */       public Sorter(Entity p_i1662_1_)
/*     */       {
/* 578 */         this.theEntity = p_i1662_1_;
/*     */       }
/*     */       
/*     */       public int compare(Entity p_compare_1_, Entity p_compare_2_)
/*     */       {
/* 583 */         double d0 = this.theEntity.func_70068_e(p_compare_1_);
/* 584 */         double d1 = this.theEntity.func_70068_e(p_compare_2_);
/* 585 */         return d0 > d1 ? 1 : d0 < d1 ? -1 : 0;
/*     */       }
/*     */       
/*     */       public int compare(Object p_compare_1_, Object p_compare_2_)
/*     */       {
/* 590 */         return compare((Entity)p_compare_1_, (Entity)p_compare_2_);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/EntityTurretCrossbow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */