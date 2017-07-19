/*     */ package thaumcraft.common.entities.construct.golem.ai;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget.Sorter;
/*     */ import net.minecraft.entity.ai.EntityAITarget;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class AINearestValidTarget extends EntityAITarget
/*     */ {
/*     */   protected final Class targetClass;
/*     */   private final int targetChance;
/*     */   protected final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
/*     */   protected Predicate targetEntitySelector;
/*     */   protected EntityLivingBase targetEntity;
/*     */   private int targetUnseenTicks;
/*     */   
/*     */   public AINearestValidTarget(EntityCreature p_i45878_1_, Class p_i45878_2_, boolean p_i45878_3_)
/*     */   {
/*  34 */     this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false);
/*     */   }
/*     */   
/*     */   public AINearestValidTarget(EntityCreature p_i45879_1_, Class p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_)
/*     */   {
/*  39 */     this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate)null);
/*     */   }
/*     */   
/*     */   public AINearestValidTarget(EntityCreature p_i45880_1_, Class p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate tselector)
/*     */   {
/*  44 */     super(p_i45880_1_, p_i45880_4_, p_i45880_5_);
/*  45 */     this.targetClass = p_i45880_2_;
/*  46 */     this.targetChance = p_i45880_3_;
/*  47 */     this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(p_i45880_1_);
/*  48 */     func_75248_a(1);
/*  49 */     this.targetEntitySelector = new Predicate()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001621";
/*     */       
/*     */       public boolean applySelection(EntityLivingBase entity) {
/*  54 */         if ((tselector != null) && (!tselector.apply(entity)))
/*     */         {
/*  56 */           return false;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*  61 */         if ((entity instanceof EntityPlayer))
/*     */         {
/*  63 */           double d0 = AINearestValidTarget.this.func_111175_f();
/*     */           
/*  65 */           if (entity.func_70093_af())
/*     */           {
/*  67 */             d0 *= 0.800000011920929D;
/*     */           }
/*     */           
/*  70 */           if (entity.func_82150_aj())
/*     */           {
/*  72 */             float f = ((EntityPlayer)entity).func_82243_bO();
/*     */             
/*  74 */             if (f < 0.1F)
/*     */             {
/*  76 */               f = 0.1F;
/*     */             }
/*     */             
/*  79 */             d0 *= 0.7F * f;
/*     */           }
/*     */           
/*  82 */           if (entity.func_70032_d(AINearestValidTarget.this.field_75299_d) > d0)
/*     */           {
/*  84 */             return false;
/*     */           }
/*     */         }
/*     */         
/*  88 */         return AINearestValidTarget.this.func_75296_a(entity, false);
/*     */       }
/*     */       
/*     */       public boolean apply(Object p_apply_1_)
/*     */       {
/*  93 */         return applySelection((EntityLivingBase)p_apply_1_);
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/* 101 */     EntityLivingBase entitylivingbase = this.field_75299_d.func_70638_az();
/* 102 */     if (entitylivingbase == null)
/*     */     {
/* 104 */       return false;
/*     */     }
/* 106 */     if (!entitylivingbase.func_70089_S())
/*     */     {
/* 108 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 112 */     Team team = this.field_75299_d.func_96124_cp();
/* 113 */     Team team1 = entitylivingbase.func_96124_cp();
/*     */     
/* 115 */     if ((team != null) && (team1 == team) && (!((ITargets)this.field_75299_d).getTargetFriendly()))
/*     */     {
/* 117 */       return false;
/*     */     }
/*     */     
/* 120 */     if ((team != null) && (team1 != team) && (((ITargets)this.field_75299_d).getTargetFriendly()))
/*     */     {
/* 122 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 126 */     double d0 = func_111175_f();
/*     */     
/* 128 */     if (this.field_75299_d.func_70068_e(entitylivingbase) > d0 * d0)
/*     */     {
/* 130 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 134 */     if (this.field_75297_f)
/*     */     {
/* 136 */       if (this.field_75299_d.func_70635_at().func_75522_a(entitylivingbase))
/*     */       {
/* 138 */         this.targetUnseenTicks = 0;
/*     */       }
/* 140 */       else if (++this.targetUnseenTicks > 60)
/*     */       {
/* 142 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 146 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_75296_a(EntityLivingBase p_75296_1_, boolean p_75296_2_)
/*     */   {
/* 155 */     if (!isGoodTarget(this.field_75299_d, p_75296_1_, p_75296_2_, this.field_75297_f))
/*     */     {
/* 157 */       return false;
/*     */     }
/* 159 */     if (!this.field_75299_d.func_180485_d(new net.minecraft.util.BlockPos(p_75296_1_)))
/*     */     {
/* 161 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 165 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean isGoodTarget(EntityLiving attacker, EntityLivingBase posTar, boolean p_179445_2_, boolean checkSight)
/*     */   {
/* 172 */     if (posTar == null)
/*     */     {
/* 174 */       return false;
/*     */     }
/* 176 */     if (posTar == attacker)
/*     */     {
/* 178 */       return false;
/*     */     }
/* 180 */     if (!posTar.func_70089_S())
/*     */     {
/* 182 */       return false;
/*     */     }
/* 184 */     if (!attacker.func_70686_a(posTar.getClass()))
/*     */     {
/* 186 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 190 */     Team team = attacker.func_96124_cp();
/* 191 */     Team team1 = posTar.func_96124_cp();
/* 192 */     if ((team != null) && (team1 == team) && (!((ITargets)attacker).getTargetFriendly()))
/*     */     {
/* 194 */       return false;
/*     */     }
/*     */     
/* 197 */     if ((team != null) && (team1 != team) && (((ITargets)attacker).getTargetFriendly()))
/*     */     {
/* 199 */       return false;
/*     */     }
/*     */     
/* 202 */     if (((attacker instanceof IEntityOwnable)) && (org.apache.commons.lang3.StringUtils.isNotEmpty(((IEntityOwnable)attacker).func_152113_b())))
/*     */     {
/* 204 */       if (((posTar instanceof IEntityOwnable)) && (((IEntityOwnable)attacker).func_152113_b().equals(((IEntityOwnable)posTar).func_152113_b())) && (!((ITargets)attacker).getTargetFriendly()))
/*     */       {
/*     */ 
/*     */ 
/* 208 */         return false;
/*     */       }
/*     */       
/* 211 */       if ((!(posTar instanceof IEntityOwnable)) && (!(posTar instanceof EntityPlayer)) && (((ITargets)attacker).getTargetFriendly())) {
/* 212 */         return false;
/*     */       }
/*     */       
/* 215 */       if (((posTar instanceof IEntityOwnable)) && (!((IEntityOwnable)attacker).func_152113_b().equals(((IEntityOwnable)posTar).func_152113_b())) && (((ITargets)attacker).getTargetFriendly()))
/*     */       {
/*     */ 
/*     */ 
/* 219 */         return false;
/*     */       }
/*     */       
/* 222 */       if ((posTar == ((IEntityOwnable)attacker).func_70902_q()) && (!((ITargets)attacker).getTargetFriendly()))
/*     */       {
/* 224 */         return false;
/*     */       }
/*     */       
/*     */     }
/* 228 */     else if (((posTar instanceof EntityPlayer)) && (!p_179445_2_) && (((EntityPlayer)posTar).field_71075_bZ.field_75102_a) && (!((ITargets)attacker).getTargetFriendly()))
/*     */     {
/*     */ 
/* 231 */       return false;
/*     */     }
/*     */     
/* 234 */     return (!checkSight) || (attacker.func_70635_at().func_75522_a(posTar));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/* 241 */     if ((this.targetChance > 0) && (this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0))
/*     */     {
/* 243 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 247 */     double d0 = func_111175_f();
/* 248 */     List list = this.field_75299_d.field_70170_p.func_175647_a(this.targetClass, this.field_75299_d.func_174813_aQ().func_72314_b(d0, 4.0D, d0), Predicates.and(this.targetEntitySelector, EntitySelectors.field_180132_d));
/*     */     
/* 250 */     java.util.Collections.sort(list, this.theNearestAttackableTargetSorter);
/*     */     
/* 252 */     if (list.isEmpty())
/*     */     {
/* 254 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 258 */     this.targetEntity = ((EntityLivingBase)list.get(0));
/* 259 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 269 */     this.field_75299_d.func_70624_b(this.targetEntity);
/* 270 */     this.targetUnseenTicks = 0;
/* 271 */     super.func_75249_e();
/*     */   }
/*     */   
/*     */   public class Sorter implements Comparator
/*     */   {
/*     */     private final Entity theEntity;
/*     */     private static final String __OBFID = "CL_00001622";
/*     */     
/*     */     public Sorter(Entity p_i1662_1_)
/*     */     {
/* 281 */       this.theEntity = p_i1662_1_;
/*     */     }
/*     */     
/*     */     public int compare(Entity p_compare_1_, Entity p_compare_2_)
/*     */     {
/* 286 */       double d0 = this.theEntity.func_70068_e(p_compare_1_);
/* 287 */       double d1 = this.theEntity.func_70068_e(p_compare_2_);
/* 288 */       return d0 > d1 ? 1 : d0 < d1 ? -1 : 0;
/*     */     }
/*     */     
/*     */     public int compare(Object p_compare_1_, Object p_compare_2_)
/*     */     {
/* 293 */       return compare((Entity)p_compare_1_, (Entity)p_compare_2_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/AINearestValidTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */