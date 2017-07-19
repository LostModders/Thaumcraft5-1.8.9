/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ 
/*     */ public class EntityTaintPig extends EntityMob implements ITaintedMob
/*     */ {
/*     */   public EntityTaintPig(World par1World)
/*     */   {
/*  35 */     super(par1World);
/*  36 */     func_70105_a(0.9F, 0.9F);
/*  37 */     ((PathNavigateGround)func_70661_as()).func_179690_a(true);
/*  38 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  39 */     this.field_70714_bg.func_75776_a(2, new AIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  40 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  41 */     this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
/*  42 */     this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  43 */     this.field_70714_bg.func_75776_a(7, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  44 */     this.field_70714_bg.func_75776_a(8, new AIAttackOnCollide(this, EntityAnimal.class, 1.0D, false));
/*  45 */     this.field_70715_bh.func_75776_a(0, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  46 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  47 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
/*  48 */     this.field_70715_bh.func_75776_a(8, new EntityAINearestAttackableTarget(this, EntityAnimal.class, false));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  54 */     super.func_110147_ax();
/*  55 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  56 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*  57 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.275D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  63 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  69 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  74 */     super.func_70636_d();
/*  75 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/*  76 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(10); a++) {
/*  77 */         Thaumcraft.proxy.getFX().splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/*  84 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  90 */     super.func_70088_a();
/*  91 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  97 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 103 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 109 */     return "mob.pig.say";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 115 */     return "mob.pig.say";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 121 */     return "mob.pig.death";
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_)
/*     */   {
/* 127 */     func_85030_a("mob.pig.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70647_i()
/*     */   {
/* 133 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected net.minecraft.item.Item func_146068_u()
/*     */   {
/* 141 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 146 */     if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 147 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 148 */         func_70099_a(new ItemStack(ItemsTC.tainted, 1, 0), this.field_70131_O / 2.0F);
/*     */       } else {
/* 150 */         func_70099_a(new ItemStack(ItemsTC.tainted, 1, 1), this.field_70131_O / 2.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity victim) {
/* 156 */     if (super.func_70652_k(victim))
/*     */     {
/* 158 */       if ((victim instanceof EntityLivingBase))
/*     */       {
/* 160 */         byte b0 = 0;
/*     */         
/* 162 */         if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL)
/*     */         {
/* 164 */           b0 = 3;
/*     */         }
/* 166 */         else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD)
/*     */         {
/* 168 */           b0 = 6;
/*     */         }
/*     */         
/* 171 */         if ((b0 > 0) && (this.field_70146_Z.nextInt(b0 + 1) > 2))
/*     */         {
/* 173 */           ((EntityLivingBase)victim).func_70690_d(new net.minecraft.potion.PotionEffect(PotionFluxTaint.instance.func_76396_c(), b0 * 20, 0));
/*     */         }
/*     */       }
/*     */       
/* 177 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 181 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintPig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */