/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest2;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.village.VillageCollection;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class EntityTaintVillager extends EntityMob implements ITaintedMob
/*     */ {
/*     */   private int randomTickDivider;
/*     */   Village villageObj;
/*     */   
/*     */   public EntityTaintVillager(World par1World)
/*     */   {
/*  42 */     this(par1World, 0);
/*     */   }
/*     */   
/*     */   public EntityTaintVillager(World par1World, int par2)
/*     */   {
/*  47 */     super(par1World);
/*  48 */     this.randomTickDivider = 0;
/*  49 */     this.villageObj = null;
/*  50 */     ((PathNavigateGround)func_70661_as()).func_179690_a(true);
/*  51 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  52 */     this.field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAIMoveIndoors(this));
/*  53 */     this.field_70714_bg.func_75776_a(2, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  54 */     this.field_70714_bg.func_75776_a(3, new EntityAIRestrictOpenDoor(this));
/*  55 */     this.field_70714_bg.func_75776_a(4, new EntityAIOpenDoor(this, true));
/*  56 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  57 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
/*  58 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
/*  59 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityVillager.class, 5.0F, 0.02F));
/*  60 */     this.field_70714_bg.func_75776_a(9, new EntityAIWander(this, 1.0D));
/*  61 */     this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));
/*  62 */     this.field_70715_bh.func_75776_a(0, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  63 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  69 */     super.func_110147_ax();
/*  70 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*  71 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*  72 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  78 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  84 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  89 */     super.func_70636_d();
/*  90 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/*  91 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(10); a++) {
/*  92 */         Thaumcraft.proxy.getFX().splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70629_bd()
/*     */   {
/* 101 */     if (--this.randomTickDivider <= 0)
/*     */     {
/* 103 */       BlockPos blockpos = new BlockPos(this);
/* 104 */       this.field_70170_p.field_72982_D.func_176060_a(blockpos);
/* 105 */       this.randomTickDivider = (70 + this.field_70146_Z.nextInt(50));
/* 106 */       this.villageObj = this.field_70170_p.field_72982_D.func_176056_a(blockpos, 32);
/*     */       
/* 108 */       if (this.villageObj == null)
/*     */       {
/* 110 */         func_110177_bN();
/*     */       }
/*     */       else
/*     */       {
/* 114 */         BlockPos blockpos1 = this.villageObj.func_180608_a();
/* 115 */         func_175449_a(blockpos1, (int)(this.villageObj.func_75568_b() * 1.0F));
/*     */       }
/*     */     }
/*     */     
/* 119 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/* 125 */     super.func_70088_a();
/* 126 */     this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 137 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 146 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 153 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 158 */     if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 159 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 0), this.field_70131_O / 2.0F);
/*     */     } else {
/* 161 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 1), this.field_70131_O / 2.0F);
/*     */     }
/* 163 */     if (this.field_70170_p.field_73012_v.nextInt(8) < 1 + i) { func_70099_a(new ItemStack(ItemsTC.coin), 1.5F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 173 */     return "mob.villager.idle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 182 */     return "mob.villager.hit";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 191 */     return "mob.villager.death";
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 196 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70604_c(EntityLivingBase par1EntityLiving)
/*     */   {
/* 202 */     super.func_70604_c(par1EntityLiving);
/*     */     
/* 204 */     if ((this.villageObj != null) && (par1EntityLiving != null))
/*     */     {
/* 206 */       this.villageObj.func_75575_a(par1EntityLiving);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity victim)
/*     */   {
/* 213 */     if (super.func_70652_k(victim))
/*     */     {
/* 215 */       if ((victim instanceof EntityLivingBase))
/*     */       {
/* 217 */         byte b0 = 0;
/*     */         
/* 219 */         if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL)
/*     */         {
/* 221 */           b0 = 3;
/*     */         }
/* 223 */         else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD)
/*     */         {
/* 225 */           b0 = 6;
/*     */         }
/*     */         
/* 228 */         if ((b0 > 0) && (this.field_70146_Z.nextInt(b0 + 1) > 2))
/*     */         {
/* 230 */           ((EntityLivingBase)victim).func_70690_d(new net.minecraft.potion.PotionEffect(PotionFluxTaint.instance.func_76396_c(), b0 * 20, 0));
/*     */         }
/*     */       }
/*     */       
/* 234 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 238 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */