/*     */ package thaumcraft.common.entities.construct;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget.Sorter;
/*     */ import net.minecraft.entity.ai.EntityAITarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.IAnimals;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class EntityTurretCrossbowAdvanced extends EntityTurretCrossbow
/*     */ {
/*     */   public EntityTurretCrossbowAdvanced(World worldIn)
/*     */   {
/*  46 */     super(worldIn);
/*  47 */     func_70105_a(0.95F, 1.5F);
/*  48 */     this.field_70714_bg.field_75782_a.clear();
/*  49 */     this.field_70715_bh.field_75782_a.clear();
/*     */     
/*  51 */     this.field_70714_bg.func_75776_a(1, new EntityAIArrowAttack(this, 0.0D, 20, 40, 24.0F));
/*  52 */     this.field_70714_bg.func_75776_a(2, new EntityAIWatchTarget(this));
/*  53 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  54 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestValidTarget(this, EntityLivingBase.class, 5, true, false, (Predicate)null));
/*  55 */     setTargetMob(true);
/*  56 */     this.field_70138_W = 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/*  62 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public EntityTurretCrossbowAdvanced(World worldIn, BlockPos pos) {
/*  66 */     this(worldIn);
/*  67 */     func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  73 */     super.func_70088_a();
/*  74 */     this.field_70180_af.func_75682_a(20, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  80 */     if ((IAnimals.class.isAssignableFrom(clazz)) && (!IMob.class.isAssignableFrom(clazz)) && (getTargetAnimal())) return true;
/*  81 */     if ((IMob.class.isAssignableFrom(clazz)) && (getTargetMob())) return true;
/*  82 */     if ((EntityPlayer.class.isAssignableFrom(clazz)) && (getTargetPlayer())) {
/*  83 */       if ((!this.field_70170_p.field_72995_K) && (!MinecraftServer.func_71276_C().func_71219_W()) && (!getTargetFriendly())) {
/*  84 */         setTargetPlayer(false);
/*  85 */         return false;
/*     */       }
/*  87 */       return true;
/*     */     }
/*     */     
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public boolean getTargetAnimal()
/*     */   {
/*  95 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 0);
/*     */   }
/*     */   
/*     */   public void setTargetAnimal(boolean par1)
/*     */   {
/* 100 */     byte var2 = this.field_70180_af.func_75683_a(20);
/* 101 */     if (par1) this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 0))); else
/* 102 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 0)));
/* 103 */     func_70624_b(null);
/*     */   }
/*     */   
/*     */   public boolean getTargetMob()
/*     */   {
/* 108 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 1);
/*     */   }
/*     */   
/*     */   public void setTargetMob(boolean par1)
/*     */   {
/* 113 */     byte var2 = this.field_70180_af.func_75683_a(20);
/* 114 */     if (par1) this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 1))); else
/* 115 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 1)));
/* 116 */     func_70624_b(null);
/*     */   }
/*     */   
/*     */   public boolean getTargetPlayer()
/*     */   {
/* 121 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 2);
/*     */   }
/*     */   
/*     */   public void setTargetPlayer(boolean par1)
/*     */   {
/* 126 */     byte var2 = this.field_70180_af.func_75683_a(20);
/* 127 */     if (par1) this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 2))); else
/* 128 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 2)));
/* 129 */     func_70624_b(null);
/*     */   }
/*     */   
/*     */   public boolean getTargetFriendly()
/*     */   {
/* 134 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 3);
/*     */   }
/*     */   
/*     */   public void setTargetFriendly(boolean par1)
/*     */   {
/* 139 */     byte var2 = this.field_70180_af.func_75683_a(20);
/* 140 */     if (par1) this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 3))); else
/* 141 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 3)));
/* 142 */     func_70624_b(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Team func_96124_cp()
/*     */   {
/* 149 */     if (isOwned())
/*     */     {
/* 151 */       EntityLivingBase entitylivingbase = getOwnerEntity();
/*     */       
/* 153 */       if (entitylivingbase != null)
/*     */       {
/* 155 */         return entitylivingbase.func_96124_cp();
/*     */       }
/*     */     }
/*     */     
/* 159 */     return super.func_96124_cp();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 166 */     super.func_110147_ax();
/* 167 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/* 172 */     return 8;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 179 */     super.func_70071_h_();
/* 180 */     if ((!this.field_70170_p.field_72995_K) && (!MinecraftServer.func_71276_C().func_71219_W()) && 
/* 181 */       (func_70638_az() != null) && ((func_70638_az() instanceof EntityPlayer)) && (func_70638_az() != getOwnerEntity())) {
/* 182 */       func_70624_b(null);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 190 */     super.func_70037_a(nbt);
/* 191 */     this.field_70180_af.func_75692_b(20, Byte.valueOf(nbt.func_74771_c("targets")));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 197 */     super.func_70014_b(nbt);
/* 198 */     nbt.func_74774_a("targets", this.field_70180_af.func_75683_a(20));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70653_a(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_)
/*     */   {
/* 204 */     super.func_70653_a(p_70653_1_, p_70653_2_, p_70653_3_ / 3.0D, p_70653_5_ / 3.0D);
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 209 */     if ((!this.field_70170_p.field_72995_K) && (isOwner(player)) && (!this.field_70128_L)) {
/* 210 */       if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof IWand))) {
/* 211 */         func_85030_a("thaumcraft:zap", 1.0F, 1.0F);
/* 212 */         dropAmmo();
/* 213 */         func_70099_a(new ItemStack(ItemsTC.turretPlacer, 1, 3), 0.5F);
/* 214 */         func_70106_y();
/* 215 */         player.func_71038_i();
/*     */       } else {
/* 217 */         player.openGui(Thaumcraft.instance, 17, this.field_70170_p, func_145782_y(), 0, 0);
/*     */       }
/* 219 */       return true;
/*     */     }
/*     */     
/* 222 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */   public void func_70091_d(double x, double y, double z)
/*     */   {
/* 227 */     super.func_70091_d(x / 2.0D, y, z / 2.0D);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/* 232 */     float b = p_70628_2_ * 0.15F;
/* 233 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(ItemsTC.mind, 1, 1), 0.5F);
/* 234 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(ItemsTC.gear), 0.5F);
/* 235 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plank), 0.5F);
/* 236 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plank), 0.5F);
/* 237 */     if (this.field_70146_Z.nextFloat() < 0.3F + b) func_70099_a(new ItemStack(ItemsTC.plate, 1, 0), 0.5F);
/* 238 */     if (this.field_70146_Z.nextFloat() < 0.4F + b) func_70099_a(new ItemStack(ItemsTC.plate, 1, 1), 0.5F);
/* 239 */     if (this.field_70146_Z.nextFloat() < 0.4F + b) { func_70099_a(new ItemStack(ItemsTC.plate, 1, 1), 0.5F);
/*     */     }
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
/* 253 */       this.theWatcher = p_i1631_1_;
/* 254 */       func_75248_a(2);
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_75250_a()
/*     */     {
/* 260 */       if (this.theWatcher.func_70638_az() != null)
/*     */       {
/* 262 */         this.closestEntity = this.theWatcher.func_70638_az();
/*     */       }
/*     */       
/* 265 */       return this.closestEntity != null;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_75253_b()
/*     */     {
/* 271 */       float d = (float)getTargetDistance();
/* 272 */       return this.closestEntity.func_70089_S();
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75249_e()
/*     */     {
/* 278 */       this.lookTime = (40 + this.theWatcher.func_70681_au().nextInt(40));
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75251_c()
/*     */     {
/* 284 */       this.closestEntity = null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75246_d()
/*     */     {
/* 290 */       this.theWatcher.func_70671_ap().func_75650_a(this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + this.closestEntity.func_70047_e(), this.closestEntity.field_70161_v, 10.0F, this.theWatcher.func_70646_bf());
/* 291 */       this.lookTime -= 1;
/*     */     }
/*     */     
/*     */     protected double getTargetDistance()
/*     */     {
/* 296 */       IAttributeInstance iattributeinstance = this.theWatcher.func_110148_a(SharedMonsterAttributes.field_111265_b);
/* 297 */       return iattributeinstance == null ? 16.0D : iattributeinstance.func_111126_e();
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
/*     */     private int targetUnseenTicks;
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45878_1_, Class p_i45878_2_, boolean p_i45878_3_) {
/* 311 */       this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false);
/*     */     }
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45879_1_, Class p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_)
/*     */     {
/* 316 */       this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate)null);
/*     */     }
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45880_1_, Class p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate tselector)
/*     */     {
/* 321 */       super(p_i45880_4_, p_i45880_5_);
/* 322 */       this.targetClass = p_i45880_2_;
/* 323 */       this.targetChance = p_i45880_3_;
/* 324 */       this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(p_i45880_1_);
/* 325 */       func_75248_a(1);
/* 326 */       this.targetEntitySelector = new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00001621";
/*     */         
/*     */         public boolean applySelection(EntityLivingBase entity) {
/* 331 */           if ((tselector != null) && (!tselector.apply(entity)))
/*     */           {
/* 333 */             return false;
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 338 */           if ((entity instanceof EntityPlayer))
/*     */           {
/* 340 */             double d0 = EntityTurretCrossbowAdvanced.EntityAINearestValidTarget.this.func_111175_f();
/*     */             
/* 342 */             if (entity.func_70093_af())
/*     */             {
/* 344 */               d0 *= 0.800000011920929D;
/*     */             }
/*     */             
/* 347 */             if (entity.func_82150_aj())
/*     */             {
/* 349 */               float f = ((EntityPlayer)entity).func_82243_bO();
/*     */               
/* 351 */               if (f < 0.1F)
/*     */               {
/* 353 */                 f = 0.1F;
/*     */               }
/*     */               
/* 356 */               d0 *= 0.7F * f;
/*     */             }
/*     */             
/* 359 */             if (entity.func_70032_d(EntityTurretCrossbowAdvanced.EntityAINearestValidTarget.this.field_75299_d) > d0)
/*     */             {
/* 361 */               return false;
/*     */             }
/*     */           }
/*     */           
/* 365 */           return EntityTurretCrossbowAdvanced.EntityAINearestValidTarget.this.func_75296_a(entity, false);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_)
/*     */         {
/* 370 */           return applySelection((EntityLivingBase)p_apply_1_);
/*     */         }
/*     */       };
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_75253_b()
/*     */     {
/* 378 */       EntityLivingBase entitylivingbase = this.field_75299_d.func_70638_az();
/* 379 */       if (entitylivingbase == null)
/*     */       {
/* 381 */         return false;
/*     */       }
/* 383 */       if (!entitylivingbase.func_70089_S())
/*     */       {
/* 385 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 389 */       Team team = this.field_75299_d.func_96124_cp();
/* 390 */       Team team1 = entitylivingbase.func_96124_cp();
/*     */       
/* 392 */       if ((team != null) && (team1 == team) && (!((EntityTurretCrossbowAdvanced)this.field_75299_d).getTargetFriendly()))
/*     */       {
/* 394 */         return false;
/*     */       }
/*     */       
/* 397 */       if ((team != null) && (team1 != team) && (((EntityTurretCrossbowAdvanced)this.field_75299_d).getTargetFriendly()))
/*     */       {
/* 399 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 403 */       double d0 = func_111175_f();
/*     */       
/* 405 */       if (this.field_75299_d.func_70068_e(entitylivingbase) > d0 * d0)
/*     */       {
/* 407 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 411 */       if (this.field_75297_f)
/*     */       {
/* 413 */         if (this.field_75299_d.func_70635_at().func_75522_a(entitylivingbase))
/*     */         {
/* 415 */           this.targetUnseenTicks = 0;
/*     */         }
/* 417 */         else if (++this.targetUnseenTicks > 60)
/*     */         {
/* 419 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 423 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected boolean func_75296_a(EntityLivingBase p_75296_1_, boolean p_75296_2_)
/*     */     {
/* 432 */       if (!isGoodTarget(this.field_75299_d, p_75296_1_, p_75296_2_, this.field_75297_f))
/*     */       {
/* 434 */         return false;
/*     */       }
/* 436 */       if (!this.field_75299_d.func_180485_d(new BlockPos(p_75296_1_)))
/*     */       {
/* 438 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 442 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private boolean isGoodTarget(EntityLiving attacker, EntityLivingBase posTar, boolean p_179445_2_, boolean checkSight)
/*     */     {
/* 449 */       if (posTar == null)
/*     */       {
/* 451 */         return false;
/*     */       }
/* 453 */       if (posTar == attacker)
/*     */       {
/* 455 */         return false;
/*     */       }
/* 457 */       if (!posTar.func_70089_S())
/*     */       {
/* 459 */         return false;
/*     */       }
/* 461 */       if (!attacker.func_70686_a(posTar.getClass()))
/*     */       {
/* 463 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 467 */       Team team = attacker.func_96124_cp();
/* 468 */       Team team1 = posTar.func_96124_cp();
/* 469 */       if ((team != null) && (team1 == team) && (!((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly()))
/*     */       {
/* 471 */         return false;
/*     */       }
/*     */       
/* 474 */       if ((team != null) && (team1 != team) && (((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly()))
/*     */       {
/* 476 */         return false;
/*     */       }
/*     */       
/* 479 */       if (((attacker instanceof IEntityOwnable)) && (org.apache.commons.lang3.StringUtils.isNotEmpty(((IEntityOwnable)attacker).func_152113_b())))
/*     */       {
/* 481 */         if (((posTar instanceof IEntityOwnable)) && (((IEntityOwnable)attacker).func_152113_b().equals(((IEntityOwnable)posTar).func_152113_b())) && (!((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly()))
/*     */         {
/*     */ 
/*     */ 
/* 485 */           return false;
/*     */         }
/*     */         
/* 488 */         if ((!(posTar instanceof IEntityOwnable)) && (!(posTar instanceof EntityPlayer)) && (((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly())) {
/* 489 */           return false;
/*     */         }
/*     */         
/* 492 */         if (((posTar instanceof IEntityOwnable)) && (!((IEntityOwnable)attacker).func_152113_b().equals(((IEntityOwnable)posTar).func_152113_b())) && (((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly()))
/*     */         {
/*     */ 
/*     */ 
/* 496 */           return false;
/*     */         }
/*     */         
/* 499 */         if ((posTar == ((IEntityOwnable)attacker).func_70902_q()) && (!((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly()))
/*     */         {
/* 501 */           return false;
/*     */         }
/*     */         
/*     */       }
/* 505 */       else if (((posTar instanceof EntityPlayer)) && (!p_179445_2_) && (((EntityPlayer)posTar).field_71075_bZ.field_75102_a) && (!((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly()))
/*     */       {
/*     */ 
/* 508 */         return false;
/*     */       }
/*     */       
/* 511 */       return (!checkSight) || (attacker.func_70635_at().func_75522_a(posTar));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean func_75250_a()
/*     */     {
/* 518 */       if ((this.targetChance > 0) && (this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0))
/*     */       {
/* 520 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 524 */       double d0 = func_111175_f();
/* 525 */       List list = this.field_75299_d.field_70170_p.func_175647_a(this.targetClass, this.field_75299_d.func_174813_aQ().func_72314_b(d0, 4.0D, d0), Predicates.and(this.targetEntitySelector, EntitySelectors.field_180132_d));
/* 526 */       Collections.sort(list, this.theNearestAttackableTargetSorter);
/*     */       
/* 528 */       if (list.isEmpty())
/*     */       {
/* 530 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 534 */       this.targetEntity = ((EntityLivingBase)list.get(0));
/* 535 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void func_75249_e()
/*     */     {
/* 545 */       this.field_75299_d.func_70624_b(this.targetEntity);
/* 546 */       this.targetUnseenTicks = 0;
/* 547 */       super.func_75249_e();
/*     */     }
/*     */     
/*     */     public class Sorter implements Comparator
/*     */     {
/*     */       private final Entity theEntity;
/*     */       private static final String __OBFID = "CL_00001622";
/*     */       
/*     */       public Sorter(Entity p_i1662_1_)
/*     */       {
/* 557 */         this.theEntity = p_i1662_1_;
/*     */       }
/*     */       
/*     */       public int compare(Entity p_compare_1_, Entity p_compare_2_)
/*     */       {
/* 562 */         double d0 = this.theEntity.func_70068_e(p_compare_1_);
/* 563 */         double d1 = this.theEntity.func_70068_e(p_compare_2_);
/* 564 */         return d0 > d1 ? 1 : d0 < d1 ? -1 : 0;
/*     */       }
/*     */       
/*     */       public int compare(Object p_compare_1_, Object p_compare_2_)
/*     */       {
/* 569 */         return compare((Entity)p_compare_1_, (Entity)p_compare_2_);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/EntityTurretCrossbowAdvanced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */