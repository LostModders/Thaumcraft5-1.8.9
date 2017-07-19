/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityRabbit;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ 
/*     */ public class EntityTaintRabbit extends EntityRabbit implements ITaintedMob
/*     */ {
/*  28 */   private int field_175540_bm = 0;
/*  29 */   private int field_175535_bn = 0;
/*  30 */   private boolean field_175536_bo = false;
/*  31 */   private boolean field_175537_bp = false;
/*  32 */   private int field_175538_bq = 0;
/*     */   private int carrotTicks;
/*     */   private EntityPlayer field_175543_bt;
/*     */   
/*     */   public EntityTaintRabbit(World worldIn) {
/*  37 */     super(worldIn);
/*  38 */     this.field_70714_bg.field_75782_a.clear();
/*     */     
/*  40 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  41 */     this.field_70714_bg.func_75776_a(2, new AIAttackOnCollide(this, EntityPlayer.class, 1.4D, false));
/*  42 */     this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 0.6D));
/*  43 */     this.field_70714_bg.func_75776_a(8, new AIAttackOnCollide(this, EntityAnimal.class, 1.4D, false));
/*  44 */     this.field_70714_bg.func_75776_a(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
/*  45 */     this.field_70715_bh.func_75776_a(0, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  46 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  47 */     this.field_70715_bh.func_75776_a(8, new EntityAINearestAttackableTarget(this, EntityAnimal.class, false));
/*  48 */     func_175515_b(0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  54 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  60 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  66 */     return func_175531_cl() == 99 ? 8 : 3;
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityRabbit func_90011_a(EntityAgeable ageable)
/*     */   {
/*  72 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/*  78 */     int j = this.field_70146_Z.nextInt(2) + this.field_70146_Z.nextInt(1 + p_70628_2_);
/*     */     
/*     */ 
/*  81 */     for (int k = 0; k < j; k++)
/*     */     {
/*  83 */       func_145779_a(ItemsTC.tainted, 1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70877_b(ItemStack stack)
/*     */   {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  96 */     super.func_110147_ax();
/*  97 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/*  98 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15.0D);
/*  99 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/* 100 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 106 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 107 */     func_85030_a("mob.attack", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
/*     */     
/* 109 */     if (func_175531_cl() == 99)
/*     */     {
/* 111 */       f *= 2.0F;
/*     */     }
/*     */     
/* 114 */     int i = 0;
/*     */     
/* 116 */     if ((p_70652_1_ instanceof EntityLivingBase))
/*     */     {
/* 118 */       f += EnchantmentHelper.func_152377_a(func_70694_bm(), ((EntityLivingBase)p_70652_1_).func_70668_bt());
/* 119 */       i += EnchantmentHelper.func_77501_a(this);
/*     */     }
/*     */     
/* 122 */     boolean flag = p_70652_1_.func_70097_a(net.minecraft.util.DamageSource.func_76358_a(this), f);
/*     */     
/* 124 */     if (flag)
/*     */     {
/*     */ 
/* 127 */       if ((p_70652_1_ instanceof EntityLivingBase))
/*     */       {
/* 129 */         byte b0 = 0;
/*     */         
/* 131 */         if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL)
/*     */         {
/* 133 */           b0 = 3;
/*     */         }
/* 135 */         else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD)
/*     */         {
/* 137 */           b0 = 6;
/*     */         }
/*     */         
/* 140 */         if ((b0 > 0) && (this.field_70146_Z.nextInt(b0 + 1) > 2))
/*     */         {
/* 142 */           ((EntityLivingBase)p_70652_1_).func_70690_d(new net.minecraft.potion.PotionEffect(PotionFluxTaint.instance.func_76396_c(), b0 * 20, 0));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 147 */       if (i > 0)
/*     */       {
/* 149 */         p_70652_1_.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/* 150 */         this.field_70159_w *= 0.6D;
/* 151 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 154 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 156 */       if (j > 0)
/*     */       {
/* 158 */         p_70652_1_.func_70015_d(j * 4);
/*     */       }
/*     */       
/* 161 */       func_174815_a(this, p_70652_1_);
/*     */     }
/*     */     
/* 164 */     return flag;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintRabbit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */