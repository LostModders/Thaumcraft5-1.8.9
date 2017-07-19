/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.api.internal.EnumWarpType;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.projectile.EntityEldritchOrb;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXSonic;
/*     */ import thaumcraft.common.lib.network.misc.PacketMiscEvent;
/*     */ 
/*     */ public class EntityEldritchGuardian extends EntityMob implements IRangedAttackMob, IEldritchMob
/*     */ {
/*     */   public EntityEldritchGuardian(World p_i1745_1_)
/*     */   {
/*  59 */     super(p_i1745_1_);
/*  60 */     ((PathNavigateGround)func_70661_as()).func_179688_b(true);
/*  61 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  62 */     this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 8.0D, 1.0D, 20, 40, 24.0F));
/*  63 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/*  64 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  65 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
/*  66 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  67 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  68 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  69 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  70 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityCultist.class, true));
/*  71 */     func_70105_a(0.8F, 2.25F);
/*  72 */     this.field_70728_aV = 20;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  78 */     super.func_110147_ax();
/*  79 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*  80 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/*  81 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
/*  82 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  88 */     super.func_70088_a();
/*  89 */     func_70096_w().func_75682_a(12, Byte.valueOf((byte)0));
/*  90 */     func_70096_w().func_75682_a(13, Byte.valueOf((byte)0));
/*  91 */     func_70096_w().func_75682_a(14, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/* 100 */     return 4;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_98052_bS()
/*     */   {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 115 */     if (source.func_82725_o()) {
/* 116 */       damage /= 2.0F;
/*     */     }
/* 118 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 128 */     super.func_70071_h_();
/*     */     
/* 130 */     if (this.field_70170_p.field_72995_K) {
/* 131 */       if (this.armLiftL > 0.0F) this.armLiftL -= 0.05F;
/* 132 */       if (this.armLiftR > 0.0F) this.armLiftR -= 0.05F;
/* 133 */       float x = (float)(this.field_70165_t + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 134 */       float z = (float)(this.field_70161_v + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 135 */       Thaumcraft.proxy.getFX().wispFXEG(x, (float)(this.field_70163_u + 0.22D * this.field_70131_O), z, this);
/*     */ 
/*     */     }
/* 138 */     else if (this.field_70170_p.field_73011_w.func_177502_q() != Config.dimensionOuterId)
/*     */     {
/* 140 */       if (((this.field_70173_aa == 0) || (this.field_70173_aa % 100 == 0)) && (this.field_70170_p.func_175659_aa() != EnumDifficulty.EASY))
/*     */       {
/* 142 */         double d6 = this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD ? 576.0D : 256.0D;
/* 143 */         for (int i = 0; i < this.field_70170_p.field_73010_i.size(); i++)
/*     */         {
/* 145 */           EntityPlayer entityplayer1 = (EntityPlayer)this.field_70170_p.field_73010_i.get(i);
/*     */           
/* 147 */           if (entityplayer1.func_70089_S())
/*     */           {
/* 149 */             double d5 = entityplayer1.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */             
/* 151 */             if (d5 < d6)
/*     */             {
/* 153 */               PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((short)2), (net.minecraft.entity.player.EntityPlayerMP)entityplayer1);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 168 */     boolean flag = super.func_70652_k(p_70652_1_);
/*     */     
/* 170 */     if (flag)
/*     */     {
/* 172 */       int i = this.field_70170_p.func_175659_aa().func_151525_a();
/*     */       
/* 174 */       if ((func_70694_bm() == null) && (func_70027_ad()) && (this.field_70146_Z.nextFloat() < i * 0.3F))
/*     */       {
/* 176 */         p_70652_1_.func_70015_d(2 * i);
/*     */       }
/*     */     }
/*     */     
/* 180 */     return flag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 189 */     return "thaumcraft:egidle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 199 */     return "thaumcraft:egdeath";
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 205 */     return 500;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 211 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_82164_bB()
/*     */   {
/* 219 */     super.func_82164_bB();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 225 */     if (this.field_70146_Z.nextBoolean()) {
/* 226 */       ItemStack ess = new ItemStack(ItemsTC.wispyEssence);
/* 227 */       AspectList al = new AspectList();
/* 228 */       ((ItemGenericEssentiaContainer)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.UNDEAD, 2));
/* 229 */       func_70099_a(ess, 1.0F);
/*     */     }
/*     */     
/* 232 */     if (this.field_70146_Z.nextBoolean()) {
/* 233 */       ItemStack ess = new ItemStack(ItemsTC.wispyEssence);
/* 234 */       AspectList al = new AspectList();
/* 235 */       ((ItemGenericEssentiaContainer)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.ELDRITCH, 2));
/* 236 */       func_70099_a(ess, 1.0F);
/*     */     }
/*     */     
/* 239 */     if (this.field_70146_Z.nextInt(10 + (this.field_70170_p.field_73011_w.func_177502_q() == Config.dimensionOuterId ? 10 : 0)) <= i / 2.0F) {
/* 240 */       func_145779_a(ItemsTC.eldritchEye, 1);
/*     */     }
/*     */     
/* 243 */     super.func_70628_a(flag, i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumCreatureAttribute func_70668_bt()
/*     */   {
/* 252 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 261 */     super.func_70014_b(nbt);
/* 262 */     if ((func_180486_cf() != null) && (func_110174_bM() > 0.0F)) {
/* 263 */       nbt.func_74768_a("HomeD", (int)func_110174_bM());
/* 264 */       nbt.func_74768_a("HomeX", func_180486_cf().func_177958_n());
/* 265 */       nbt.func_74768_a("HomeY", func_180486_cf().func_177956_o());
/* 266 */       nbt.func_74768_a("HomeZ", func_180486_cf().func_177952_p());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 277 */     super.func_70037_a(nbt);
/* 278 */     if (nbt.func_74764_b("HomeD")) {
/* 279 */       func_175449_a(new BlockPos(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ")), nbt.func_74762_e("HomeD"));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
/*     */   {
/* 287 */     IEntityLivingData dd = super.func_180482_a(diff, data);
/* 288 */     float f = diff.func_180170_c();
/* 289 */     if (this.field_70170_p.field_73011_w.func_177502_q() == Config.dimensionOuterId) {
/* 290 */       int bh = (int)func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 291 */       func_110149_m(func_110139_bj() + bh);
/*     */     }
/* 293 */     return dd;
/*     */   }
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/* 298 */     super.func_70619_bc();
/* 299 */     if ((this.field_70170_p.field_73011_w.func_177502_q() == Config.dimensionOuterId) && (this.field_70172_ad <= 0) && (this.field_70173_aa % 25 == 0)) {
/* 300 */       int bh = (int)func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 301 */       if (func_110139_bj() < bh)
/* 302 */         func_110149_m(func_110139_bj() + 1.0F);
/*     */     }
/*     */   }
/*     */   
/* 306 */   public float armLiftL = 0.0F;
/* 307 */   public float armLiftR = 0.0F;
/*     */   
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte p_70103_1_)
/*     */   {
/* 313 */     if (p_70103_1_ == 15)
/*     */     {
/* 315 */       this.armLiftL = 0.5F;
/*     */ 
/*     */     }
/* 318 */     else if (p_70103_1_ == 16)
/*     */     {
/* 320 */       this.armLiftR = 0.5F;
/*     */     }
/* 322 */     else if (p_70103_1_ == 17)
/*     */     {
/* 324 */       this.armLiftL = 0.9F;
/* 325 */       this.armLiftR = 0.9F;
/*     */     }
/*     */     else
/*     */     {
/* 329 */       super.func_70103_a(p_70103_1_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 339 */     return !func_110175_bO();
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 345 */     return 2.1F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 351 */     List ents = this.field_70170_p.func_72872_a(EntityEldritchGuardian.class, AxisAlignedBB.func_178781_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 357 */     return ents.size() > 0 ? false : super.func_70601_bi();
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70814_o()
/*     */   {
/* 363 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 369 */     return 1.5F;
/*     */   }
/*     */   
/* 372 */   boolean lastBlast = false;
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/* 376 */     if (this.field_70146_Z.nextFloat() > 0.1F) {
/* 377 */       EntityEldritchOrb blast = new EntityEldritchOrb(this.field_70170_p, this);
/* 378 */       this.lastBlast = (!this.lastBlast);
/*     */       
/* 380 */       this.field_70170_p.func_72960_a(this, (byte)(this.lastBlast ? 16 : 15));
/*     */       
/* 382 */       int rr = this.lastBlast ? 90 : 180;
/* 383 */       double xx = MathHelper.func_76134_b((this.field_70177_z + rr) % 360.0F / 180.0F * 3.1415927F) * 0.5F;
/* 384 */       double yy = 0.057777777D * this.field_70131_O;
/* 385 */       double zz = MathHelper.func_76126_a((this.field_70177_z + rr) % 360.0F / 180.0F * 3.1415927F) * 0.5F;
/* 386 */       blast.func_70107_b(blast.field_70165_t - xx, blast.field_70163_u, blast.field_70161_v - zz);
/*     */       
/* 388 */       Vec3 v = func_70040_Z();
/* 389 */       blast.func_70186_c(v.field_72450_a, v.field_72448_b, v.field_72449_c, 1.0F, 2.0F);
/*     */       
/* 391 */       func_85030_a("thaumcraft:egattack", 2.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 392 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/* 394 */     else if (func_70685_l(entitylivingbase)) {
/* 395 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXSonic(func_145782_y()), new net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.func_177502_q(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */       try {
/* 397 */         entitylivingbase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 400, 0));
/*     */       }
/*     */       catch (Exception e) {}
/* 400 */       if ((entitylivingbase instanceof EntityPlayer)) {
/* 401 */         ResearchHelper.addWarpToPlayer((EntityPlayer)entitylivingbase, 1 + this.field_70170_p.field_73012_v.nextInt(3), EnumWarpType.TEMPORARY);
/*     */       }
/*     */       
/* 404 */       func_85030_a("thaumcraft:egscreech", 3.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 411 */     return el instanceof IEldritchMob;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityEldritchGuardian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */