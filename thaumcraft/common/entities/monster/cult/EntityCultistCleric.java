/*     */ package thaumcraft.common.entities.monster.cult;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
/*     */ import thaumcraft.common.entities.ai.misc.AIAltarFocus;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ 
/*     */ public class EntityCultistCleric extends EntityCultist implements IRangedAttackMob, IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityCultistCleric(World p_i1745_1_)
/*     */   {
/*  40 */     super(p_i1745_1_);
/*  41 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  42 */     this.field_70714_bg.func_75776_a(1, new AIAltarFocus(this));
/*  43 */     this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 2.0D, 1.0D, 20, 40, 24.0F));
/*  44 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/*  45 */     this.field_70714_bg.func_75776_a(4, new EntityAIRestrictOpenDoor(this));
/*  46 */     this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true));
/*  47 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  48 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  49 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  50 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  51 */     this.field_70715_bh.func_75776_a(1, new thaumcraft.common.entities.ai.combat.AICultistHurtByTarget(this, true));
/*  52 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  59 */     super.func_110147_ax();
/*  60 */     func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111267_a).func_111128_a(24.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_180481_a(DifficultyInstance diff)
/*     */   {
/*  66 */     func_70062_b(4, new ItemStack(ItemsTC.crimsonRobeHelm));
/*  67 */     func_70062_b(3, new ItemStack(ItemsTC.crimsonRobeChest));
/*  68 */     func_70062_b(2, new ItemStack(ItemsTC.crimsonRobeLegs));
/*  69 */     if (this.field_70146_Z.nextFloat() < (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD ? 0.3F : 0.1F))
/*     */     {
/*  71 */       func_70062_b(1, new ItemStack(ItemsTC.crimsonBoots));
/*     */     }
/*     */   }
/*     */   
/*  75 */   public int rage = 0;
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/*  79 */     double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
/*  80 */     double d1 = entitylivingbase.func_174813_aQ().field_72338_b + entitylivingbase.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/*  81 */     double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
/*  82 */     func_71038_i();
/*  83 */     if (this.field_70146_Z.nextFloat() > 0.66F) {
/*  84 */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, true);
/*  85 */       blast.field_70165_t += blast.field_70159_w / 2.0D;
/*  86 */       blast.field_70161_v += blast.field_70179_y / 2.0D;
/*  87 */       blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
/*  88 */       blast.func_70186_c(d0, d1 + 2.0D, d2, 0.66F, 3.0F);
/*  89 */       func_85030_a("thaumcraft:egattack", 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/*  90 */       this.field_70170_p.func_72838_d(blast);
/*     */     } else {
/*  92 */       float f1 = MathHelper.func_76129_c(f) * 0.5F;
/*  93 */       this.field_70170_p.func_180498_a((EntityPlayer)null, 1009, func_180425_c(), 0);
/*     */       
/*  95 */       for (int i = 0; i < 3; i++)
/*     */       {
/*  97 */         EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1);
/*  98 */         entitysmallfireball.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F + 0.5D);
/*  99 */         this.field_70170_p.func_72838_d(entitysmallfireball);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 107 */     return !getIsRitualist();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70088_a()
/*     */   {
/* 113 */     super.func_70088_a();
/* 114 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public boolean getIsRitualist()
/*     */   {
/* 119 */     return (this.field_70180_af.func_75683_a(16) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setIsRitualist(boolean par1)
/*     */   {
/* 124 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 126 */     if (par1)
/*     */     {
/* 128 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else
/*     */     {
/* 132 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/* 139 */     if (func_180431_b(p_70097_1_))
/*     */     {
/* 141 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 145 */     setIsRitualist(false);
/* 146 */     return super.func_70097_a(p_70097_1_, p_70097_2_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 156 */     super.func_70037_a(par1NBTTagCompound);
/* 157 */     this.field_70180_af.func_75692_b(16, Byte.valueOf(par1NBTTagCompound.func_74771_c("Flags")));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 163 */     super.func_70014_b(par1NBTTagCompound);
/* 164 */     par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(16));
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/* 169 */     data.writeInt(func_180486_cf().func_177958_n());
/* 170 */     data.writeInt(func_180486_cf().func_177956_o());
/* 171 */     data.writeInt(func_180486_cf().func_177952_p());
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/* 176 */     func_175449_a(new BlockPos(data.readInt(), data.readInt(), data.readInt()), 8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 183 */     super.func_70071_h_();
/* 184 */     if ((this.field_70170_p.field_72995_K) && (getIsRitualist())) {
/* 185 */       double d0 = func_180486_cf().func_177958_n() + 0.5D - this.field_70165_t;
/* 186 */       double d1 = func_180486_cf().func_177956_o() + 1.5D - (this.field_70163_u + func_70047_e());
/* 187 */       double d2 = func_180486_cf().func_177952_p() + 0.5D - this.field_70161_v;
/* 188 */       double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 189 */       float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 190 */       float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
/* 191 */       this.field_70125_A = updateRotation(this.field_70125_A, f1, 10.0F);
/* 192 */       this.field_70759_as = updateRotation(this.field_70759_as, f, func_70646_bf());
/*     */     }
/* 194 */     if ((!this.field_70170_p.field_72995_K) && (getIsRitualist()) && 
/* 195 */       (this.rage >= 5)) { setIsRitualist(false);
/*     */     }
/*     */   }
/*     */   
/*     */   private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_)
/*     */   {
/* 201 */     float f3 = MathHelper.func_76142_g(p_75652_2_ - p_75652_1_);
/*     */     
/* 203 */     if (f3 > p_75652_3_)
/*     */     {
/* 205 */       f3 = p_75652_3_;
/*     */     }
/*     */     
/* 208 */     if (f3 < -p_75652_3_)
/*     */     {
/* 210 */       f3 = -p_75652_3_;
/*     */     }
/*     */     
/* 213 */     return p_75652_1_ + f3;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 219 */     return "thaumcraft:chant";
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 225 */     return 500;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 233 */     if (par1 == 19)
/*     */     {
/* 235 */       for (int i = 0; i < 3; i++)
/*     */       {
/* 237 */         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 238 */         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 239 */         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 240 */         this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2, new int[0]);
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 248 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/cult/EntityCultistCleric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */