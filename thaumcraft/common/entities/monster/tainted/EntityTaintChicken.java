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
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
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
/*     */ public class EntityTaintChicken extends EntityMob implements ITaintedMob
/*     */ {
/*  33 */   public boolean field_753_a = false;
/*  34 */   public float field_752_b = 0.0F;
/*  35 */   public float destPos = 0.0F;
/*     */   public float field_757_d;
/*     */   public float field_756_e;
/*  38 */   public float field_755_h = 1.0F;
/*     */   
/*     */   public EntityTaintChicken(World par1World)
/*     */   {
/*  42 */     super(par1World);
/*  43 */     func_70105_a(0.5F, 0.8F);
/*  44 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  45 */     this.field_70714_bg.func_75776_a(2, new AIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  46 */     this.field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAILeapAtTarget(this, 0.3F));
/*  47 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  48 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityAnimal.class, 1.0D, true));
/*  49 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
/*  50 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  51 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  52 */     this.field_70715_bh.func_75776_a(0, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  53 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  54 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
/*  55 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, false));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  61 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  67 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  73 */     super.func_110147_ax();
/*  74 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
/*  75 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/*  76 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  83 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/*  89 */     super.func_70636_d();
/*  90 */     this.field_756_e = this.field_752_b;
/*  91 */     this.field_757_d = this.destPos;
/*  92 */     this.destPos = ((float)(this.destPos + (this.field_70122_E ? -1 : 4) * 0.3D));
/*     */     
/*  94 */     if (this.destPos < 0.0F)
/*     */     {
/*  96 */       this.destPos = 0.0F;
/*     */     }
/*     */     
/*  99 */     if (this.destPos > 1.0F)
/*     */     {
/* 101 */       this.destPos = 1.0F;
/*     */     }
/*     */     
/* 104 */     if ((!this.field_70122_E) && (this.field_755_h < 1.0F))
/*     */     {
/* 106 */       this.field_755_h = 1.0F;
/*     */     }
/*     */     
/* 109 */     this.field_755_h = ((float)(this.field_755_h * 0.9D));
/*     */     
/* 111 */     if ((!this.field_70122_E) && (this.field_70181_x < 0.0D))
/*     */     {
/* 113 */       this.field_70181_x *= 0.9D;
/*     */     }
/*     */     
/* 116 */     this.field_752_b += this.field_755_h * 2.0F;
/*     */     
/* 118 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/* 119 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(10); a++) {
/* 120 */         Thaumcraft.proxy.getFX().splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 131 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 137 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 143 */     return "mob.chicken.say";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 149 */     return "mob.chicken.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 155 */     return "mob.chicken.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_)
/*     */   {
/* 161 */     func_85030_a("mob.chicken.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 166 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 172 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 177 */     if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
/* 178 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 0), this.field_70131_O / 2.0F);
/*     */     } else {
/* 180 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 1), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity victim)
/*     */   {
/* 186 */     if (super.func_70652_k(victim))
/*     */     {
/* 188 */       if ((victim instanceof EntityLivingBase))
/*     */       {
/* 190 */         byte b0 = 0;
/*     */         
/* 192 */         if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL)
/*     */         {
/* 194 */           b0 = 3;
/*     */         }
/* 196 */         else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD)
/*     */         {
/* 198 */           b0 = 6;
/*     */         }
/*     */         
/* 201 */         if ((b0 > 0) && (this.field_70146_Z.nextInt(b0 + 1) > 2))
/*     */         {
/* 203 */           ((EntityLivingBase)victim).func_70690_d(new net.minecraft.potion.PotionEffect(PotionFluxTaint.instance.func_76396_c(), b0 * 20, 0));
/*     */         }
/*     */       }
/*     */       
/* 207 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 211 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintChicken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */