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
/*     */ public class EntityTaintCow extends EntityMob implements ITaintedMob
/*     */ {
/*     */   public EntityTaintCow(World par1World)
/*     */   {
/*  35 */     super(par1World);
/*  36 */     func_70105_a(0.9F, 1.3F);
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
/*     */   protected void func_110147_ax()
/*     */   {
/*  66 */     super.func_110147_ax();
/*  67 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
/*  68 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
/*  69 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  76 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  82 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/*  88 */     return "mob.cow.say";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/*  94 */     return "mob.cow.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 100 */     return "mob.cow.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_)
/*     */   {
/* 106 */     func_85030_a("mob.cow.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 111 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 118 */     return 0.4F;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 123 */     super.func_70636_d();
/* 124 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/* 125 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(10); a++) {
/* 126 */         Thaumcraft.proxy.getFX().splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected net.minecraft.item.Item func_146068_u() {
/* 132 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 137 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 138 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 0), this.field_70131_O / 2.0F);
/*     */     } else {
/* 140 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 1), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity victim)
/*     */   {
/* 147 */     if (super.func_70652_k(victim))
/*     */     {
/* 149 */       if ((victim instanceof EntityLivingBase))
/*     */       {
/* 151 */         byte b0 = 0;
/*     */         
/* 153 */         if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL)
/*     */         {
/* 155 */           b0 = 3;
/*     */         }
/* 157 */         else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD)
/*     */         {
/* 159 */           b0 = 6;
/*     */         }
/*     */         
/* 162 */         if ((b0 > 0) && (this.field_70146_Z.nextInt(b0 + 1) > 2))
/*     */         {
/* 164 */           ((EntityLivingBase)victim).func_70690_d(new net.minecraft.potion.PotionEffect(PotionFluxTaint.instance.func_76396_c(), b0 * 20, 0));
/*     */         }
/*     */       }
/*     */       
/* 168 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 172 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintCow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */