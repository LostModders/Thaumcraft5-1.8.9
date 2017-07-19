/*     */ package thaumcraft.common.entities.construct.golem;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EntityTracker;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagLong;
/*     */ import net.minecraft.network.play.server.S0BPacketAnimation;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.golems.parts.GolemAddon;
/*     */ import thaumcraft.api.golems.parts.GolemArm;
/*     */ import thaumcraft.api.golems.parts.GolemArm.IArmFunction;
/*     */ import thaumcraft.api.golems.parts.GolemHead;
/*     */ import thaumcraft.api.golems.parts.GolemHead.IHeadFunction;
/*     */ import thaumcraft.api.golems.parts.GolemLeg;
/*     */ import thaumcraft.api.golems.parts.GolemMaterial;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.construct.EntityOwnedConstruct;
/*     */ import thaumcraft.common.entities.construct.golem.ai.AIFollowOwner;
/*     */ import thaumcraft.common.entities.construct.golem.ai.AIGotoBlock;
/*     */ import thaumcraft.common.entities.construct.golem.ai.AIGotoEntity;
/*     */ import thaumcraft.common.entities.construct.golem.ai.AIGotoHome;
/*     */ import thaumcraft.common.entities.construct.golem.ai.AIOwnerHurtByTarget;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class EntityThaumcraftGolem extends EntityOwnedConstruct implements thaumcraft.api.golems.IGolemAPI, IRangedAttackMob
/*     */ {
/*     */   public EntityThaumcraftGolem(World worldIn)
/*     */   {
/*  65 */     super(worldIn);
/*  66 */     func_70105_a(0.4F, 0.9F);
/*  67 */     this.field_70715_bh.field_75782_a.clear();
/*  68 */     this.field_70714_bg.func_75776_a(2, new AIGotoEntity(this));
/*  69 */     this.field_70714_bg.func_75776_a(3, new AIGotoBlock(this));
/*  70 */     this.field_70714_bg.func_75776_a(4, new AIGotoHome(this));
/*  71 */     this.field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  72 */     this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
/*     */     
/*  74 */     this.field_70728_aV = 5;
/*     */   }
/*     */   
/*  77 */   int rankXp = 0;
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  82 */     super.func_70088_a();
/*  83 */     this.field_70180_af.func_75682_a(18, Integer.valueOf(0));
/*  84 */     this.field_70180_af.func_75682_a(19, Integer.valueOf(0));
/*  85 */     this.field_70180_af.func_75682_a(20, new Byte((byte)0));
/*  86 */     this.field_70180_af.func_75682_a(21, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public IGolemProperties getProperties() {
/*  90 */     ByteBuffer bb = ByteBuffer.allocate(8);
/*  91 */     bb.putInt(this.field_70180_af.func_75679_c(18));
/*  92 */     bb.putInt(this.field_70180_af.func_75679_c(19));
/*  93 */     return GolemProperties.fromLong(bb.getLong(0));
/*     */   }
/*     */   
/*     */   public void setProperties(IGolemProperties prop) {
/*  97 */     ByteBuffer bb = ByteBuffer.allocate(8);
/*  98 */     bb.putLong(prop.toLong());
/*  99 */     bb.rewind();
/* 100 */     this.field_70180_af.func_75692_b(18, Integer.valueOf(bb.getInt()));
/* 101 */     this.field_70180_af.func_75692_b(19, Integer.valueOf(bb.getInt()));
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 107 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 113 */     super.func_110147_ax();
/*     */     
/* 115 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/* 116 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
/* 117 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/* 118 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/* 119 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void updateEntityAttributes()
/*     */   {
/* 126 */     int mh = 10 + getProperties().getMaterial().healthMod;
/* 127 */     if (getProperties().hasTrait(EnumGolemTrait.FRAGILE)) mh = (int)(mh * 0.75D);
/* 128 */     mh += getProperties().getRank();
/* 129 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(mh);
/*     */     
/*     */ 
/* 132 */     this.field_70138_W = (getProperties().hasTrait(EnumGolemTrait.WHEELED) ? 0.5F : 0.6F);
/*     */     
/* 134 */     func_175449_a(func_180486_cf() == BlockPos.field_177992_a ? func_180425_c() : func_180486_cf(), getProperties().hasTrait(EnumGolemTrait.SCOUT) ? 32 : 16);
/*     */     
/* 136 */     this.field_70699_by = func_175447_b(this.field_70170_p);
/*     */     
/* 138 */     if ((func_70661_as() instanceof PathNavigateGround)) {
/* 139 */       ((PathNavigateGround)func_70661_as()).func_179690_a(true);
/*     */     }
/*     */     
/*     */ 
/* 143 */     if (getProperties().hasTrait(EnumGolemTrait.FIGHTER)) {
/* 144 */       double da = getProperties().getMaterial().damage;
/* 145 */       if (getProperties().hasTrait(EnumGolemTrait.BRUTAL)) da = Math.max(da * 1.5D, da + 1.0D);
/* 146 */       da += getProperties().getRank() * 0.25D;
/* 147 */       func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(da);
/*     */     }
/*     */     else {
/* 150 */       func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*     */     }
/* 152 */     createAI();
/*     */   }
/*     */   
/*     */   private void createAI()
/*     */   {
/* 157 */     this.field_70714_bg.field_75782_a.clear();
/* 158 */     this.field_70715_bh.field_75782_a.clear();
/*     */     
/* 160 */     if (isFollowingOwner()) {
/* 161 */       this.field_70714_bg.func_75776_a(4, new AIFollowOwner(this, 1.0D, 10.0F, 2.0F));
/*     */     } else {
/* 163 */       this.field_70714_bg.func_75776_a(3, new AIGotoEntity(this));
/* 164 */       this.field_70714_bg.func_75776_a(4, new AIGotoBlock(this));
/* 165 */       this.field_70714_bg.func_75776_a(5, new AIGotoHome(this));
/*     */     }
/*     */     
/* 168 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/* 169 */     this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
/*     */     
/* 171 */     if (getProperties().hasTrait(EnumGolemTrait.FIGHTER))
/*     */     {
/* 173 */       if ((func_70661_as() instanceof PathNavigateGround)) {
/* 174 */         this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*     */       }
/*     */       
/* 177 */       if (getProperties().hasTrait(EnumGolemTrait.RANGED)) {
/* 178 */         EntityAIArrowAttack aa = null;
/* 179 */         if (getProperties().getArms().function != null) {
/* 180 */           aa = getProperties().getArms().function.getRangedAttackAI(this);
/*     */         }
/* 182 */         if (aa != null) {
/* 183 */           this.field_70714_bg.func_75776_a(1, aa);
/*     */         }
/*     */       }
/* 186 */       this.field_70714_bg.func_75776_a(2, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.15D, false));
/*     */       
/* 188 */       if (isFollowingOwner()) {
/* 189 */         this.field_70715_bh.func_75776_a(1, new AIOwnerHurtByTarget(this));
/* 190 */         this.field_70715_bh.func_75776_a(2, new thaumcraft.common.entities.construct.golem.ai.AIOwnerHurtTarget(this));
/*     */       }
/*     */       
/* 193 */       this.field_70715_bh.func_75776_a(3, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70617_f_()
/*     */   {
/* 201 */     return isBesideClimbableBlock();
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData ld)
/*     */   {
/* 207 */     func_175449_a(func_180425_c(), 16);
/* 208 */     updateEntityAttributes();
/* 209 */     return ld;
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/* 214 */     int armor = getProperties().getMaterial().armor;
/* 215 */     if (getProperties().hasTrait(EnumGolemTrait.ARMORED)) armor = (int)Math.max(armor * 1.5D, armor + 1);
/* 216 */     if (getProperties().hasTrait(EnumGolemTrait.FRAGILE)) armor = (int)(armor * 0.75D);
/* 217 */     return armor;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 223 */     func_82168_bl();
/* 224 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 236 */     super.func_70071_h_();
/*     */     
/* 238 */     if (!this.field_70170_p.field_72995_K) {
/* 239 */       if ((this.task != null) && (this.task.isSuspended())) { this.task = null;
/*     */       }
/* 241 */       if ((func_70638_az() != null) && (func_70638_az().field_70128_L)) { func_70624_b(null);
/*     */       }
/* 243 */       if ((func_70638_az() != null) && (getProperties().hasTrait(EnumGolemTrait.RANGED)) && (func_70068_e(func_70638_az()) > 1024.0D)) {
/* 244 */         func_70624_b(null);
/*     */       }
/* 246 */       if ((!MinecraftServer.func_71276_C().func_71219_W()) && 
/* 247 */         (func_70638_az() != null) && ((func_70638_az() instanceof EntityPlayer))) { func_70624_b(null);
/*     */       }
/*     */       
/* 250 */       if (this.field_70173_aa % (getProperties().hasTrait(EnumGolemTrait.REPAIR) ? 40 : 100) == 0) { func_70691_i(1.0F);
/*     */       }
/* 252 */       if (getProperties().hasTrait(EnumGolemTrait.CLIMBER)) {
/* 253 */         setBesideClimbableBlock((!this.field_82175_bq) && (this.field_70123_F));
/*     */       }
/*     */     }
/* 256 */     if (getProperties().getHead().function != null)
/* 257 */       getProperties().getHead().function.onUpdateTick(this);
/* 258 */     if (getProperties().getArms().function != null)
/* 259 */       getProperties().getArms().function.onUpdateTick(this);
/* 260 */     if (getProperties().getLegs().function != null)
/* 261 */       getProperties().getLegs().function.onUpdateTick(this);
/* 262 */     if (getProperties().getAddon().function != null) {
/* 263 */       getProperties().getAddon().function.onUpdateTick(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 273 */     if (par1 == 5)
/*     */     {
/* 275 */       Thaumcraft.proxy.getFX().drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O + 0.1D, this.field_70161_v, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.5F, false, '°' + (this.field_70146_Z.nextBoolean() ? 0 : 3), 3, 1, 6, 0, 2.0F, 0.0F, 1);
/*     */ 
/*     */ 
/*     */     }
/* 279 */     else if (par1 == 6)
/*     */     {
/* 281 */       Thaumcraft.proxy.getFX().drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O + 0.1D, this.field_70161_v, 0.0D, 0.025D, 0.0D, 0.1F, 1.0F, 1.0F, 0.5F, false, 15, 1, 1, 10, 0, 2.0F, 0.0F, 1);
/*     */ 
/*     */ 
/*     */     }
/* 285 */     else if (par1 == 7)
/*     */     {
/* 287 */       Thaumcraft.proxy.getFX().drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O + 0.1D, this.field_70161_v, 0.0D, 0.05D, 0.0D, 1.0F, 1.0F, 1.0F, 0.5F, false, 160, 10, 1, 10, 0, 2.0F, 0.0F, 1);
/*     */ 
/*     */ 
/*     */     }
/* 291 */     else if (par1 == 8)
/*     */     {
/* 293 */       Thaumcraft.proxy.getFX().drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O + 0.1D, this.field_70161_v, 0.0D, 0.01D, 0.0D, 1.0F, 1.0F, 0.1F, 0.5F, false, 14, 1, 1, 20, 0, 2.0F, 0.0F, 1);
/*     */ 
/*     */ 
/*     */     }
/* 297 */     else if (par1 == 9)
/*     */     {
/* 299 */       for (int a = 0; a < 5; a++) {
/* 300 */         Thaumcraft.proxy.getFX().drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O, this.field_70161_v, this.field_70146_Z.nextGaussian() * 0.009999999776482582D, this.field_70146_Z.nextFloat() * 0.02D, this.field_70146_Z.nextGaussian() * 0.009999999776482582D, 1.0F, 1.0F, 1.0F, 0.5F, false, 13, 1, 1, 20 + this.field_70146_Z.nextInt(20), 0, 0.3F + this.field_70146_Z.nextFloat() * 0.4F, 0.0F, 1);
/*     */ 
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 307 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public float getGolemMoveSpeed() {
/* 312 */     return 1.0F + getProperties().getRank() * 0.025F + (getProperties().hasTrait(EnumGolemTrait.LIGHT) ? 0.2F : 0.0F) + (getProperties().hasTrait(EnumGolemTrait.HEAVY) ? -0.175F : 0.0F) + (getProperties().hasTrait(EnumGolemTrait.FLYER) ? -0.33F : 0.0F) + (getProperties().hasTrait(EnumGolemTrait.WHEELED) ? 0.25F : 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected net.minecraft.pathfinding.PathNavigate func_175447_b(World worldIn)
/*     */   {
/* 322 */     return getProperties().hasTrait(EnumGolemTrait.CLIMBER) ? new net.minecraft.pathfinding.PathNavigateClimber(this, worldIn) : getProperties().hasTrait(EnumGolemTrait.FLYER) ? new thaumcraft.common.entities.construct.golem.ai.PathNavigateGolemAir(this, worldIn) : new thaumcraft.common.entities.construct.golem.ai.PathNavigateGolemGround(this, worldIn);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70612_e(float f1, float f2)
/*     */   {
/* 330 */     if (func_70613_aW())
/*     */     {
/* 332 */       if (getProperties().hasTrait(EnumGolemTrait.FLYER))
/*     */       {
/* 334 */         func_70060_a(f1, f2, 0.1F);
/* 335 */         func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 336 */         this.field_70159_w *= 0.8999999761581421D;
/* 337 */         this.field_70181_x *= 0.8999999761581421D;
/* 338 */         this.field_70179_y *= 0.8999999761581421D;
/*     */         
/* 340 */         if (func_70638_az() == null)
/*     */         {
/* 342 */           this.field_70181_x -= 0.005D;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 347 */         super.func_70612_e(f1, f2);
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/* 352 */       super.func_70612_e(f1, f2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 365 */     return (getProperties().hasTrait(EnumGolemTrait.HEAVY)) && (!getProperties().hasTrait(EnumGolemTrait.FLYER));
/*     */   }
/*     */   
/*     */   public void func_180430_e(float distance, float damageMultiplier)
/*     */   {
/* 370 */     if ((!getProperties().hasTrait(EnumGolemTrait.FLYER)) && (!getProperties().hasTrait(EnumGolemTrait.CLIMBER))) {
/* 371 */       super.func_180430_e(distance, damageMultiplier);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 380 */     super.func_70037_a(nbt);
/* 381 */     setProperties(GolemProperties.fromLong(nbt.func_74763_f("props")));
/*     */     
/* 383 */     func_175449_a(BlockPos.func_177969_a(nbt.func_74763_f("homepos")), 16);
/*     */     
/* 385 */     this.field_70180_af.func_75692_b(20, Byte.valueOf(nbt.func_74771_c("gflags")));
/*     */     
/* 387 */     this.rankXp = nbt.func_74762_e("rankXP");
/*     */     
/* 389 */     setGolemColor(nbt.func_74771_c("color"));
/*     */     
/* 391 */     updateEntityAttributes();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 397 */     super.func_70014_b(nbt);
/*     */     
/* 399 */     nbt.func_74772_a("props", getProperties().toLong());
/*     */     
/* 401 */     nbt.func_74772_a("homepos", func_180486_cf().func_177986_g());
/*     */     
/* 403 */     nbt.func_74774_a("gflags", this.field_70180_af.func_75683_a(20));
/*     */     
/* 405 */     nbt.func_74768_a("rankXP", this.rankXp);
/*     */     
/* 407 */     nbt.func_74774_a("color", getGolemColor());
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70665_d(DamageSource ds, float damage)
/*     */   {
/* 413 */     if ((ds.func_76347_k()) && (getProperties().hasTrait(EnumGolemTrait.FIREPROOF))) {
/* 414 */       return;
/*     */     }
/* 416 */     if ((ds.func_94541_c()) && (getProperties().hasTrait(EnumGolemTrait.BLASTPROOF))) {
/* 417 */       damage = Math.min(func_110138_aP() / 2.0F, damage * 0.3F);
/*     */     }
/* 419 */     if (ds == DamageSource.field_76367_g) return;
/* 420 */     if ((ds == DamageSource.field_76368_d) || (ds == DamageSource.field_76380_i)) {
/* 421 */       func_70012_b(func_180486_cf().func_177958_n() + 0.5D, func_180486_cf().func_177956_o() + 0.5D, func_180486_cf().func_177952_p() + 0.5D, 0.0F, 0.0F);
/*     */     }
/* 423 */     super.func_70665_d(ds, damage);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 429 */     if ((player.func_70093_af()) || ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemNameTag))))
/* 430 */       return false;
/* 431 */     if ((!this.field_70170_p.field_72995_K) && (isOwner(player)) && (!this.field_70128_L)) {
/* 432 */       if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof IWand))) {
/* 433 */         func_85030_a("thaumcraft:zap", 1.0F, 1.0F);
/* 434 */         if (this.task != null) this.task.setReserved(false);
/* 435 */         dropCarried();
/* 436 */         ItemStack placer = new ItemStack(ItemsTC.golemPlacer);
/* 437 */         placer.func_77983_a("props", new NBTTagLong(getProperties().toLong()));
/* 438 */         placer.func_77983_a("xp", new net.minecraft.nbt.NBTTagInt(this.rankXp));
/* 439 */         func_70099_a(placer, 0.5F);
/* 440 */         func_70106_y();
/* 441 */         player.func_71038_i();
/*     */       }
/* 443 */       else if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemGolemBell)) && (thaumcraft.api.research.ResearchHelper.isResearchComplete(player.func_70005_c_(), "GOLEMDIRECT")))
/*     */       {
/* 445 */         if (this.task != null) this.task.setReserved(false);
/* 446 */         func_85030_a("thaumcraft:scan", 1.0F, 1.0F);
/* 447 */         setFollowingOwner(!isFollowingOwner());
/* 448 */         if (isFollowingOwner()) {
/* 449 */           player.func_145747_a(new ChatComponentTranslation("golem.follow", new Object[] { "" }));
/* 450 */           if (Config.showGolemEmotes) this.field_70170_p.func_72960_a(this, (byte)5);
/*     */         } else {
/* 452 */           player.func_145747_a(new ChatComponentTranslation("golem.stay", new Object[] { "" }));
/* 453 */           if (Config.showGolemEmotes) this.field_70170_p.func_72960_a(this, (byte)8);
/* 454 */           func_175449_a(func_180425_c(), 16);
/*     */         }
/* 456 */         updateEntityAttributes();
/*     */         
/* 458 */         player.func_71038_i();
/*     */       }
/* 460 */       else if (player.func_70694_bm() != null) {
/* 461 */         int[] ids = OreDictionary.getOreIDs(player.func_70694_bm());
/* 462 */         if ((ids != null) && (ids.length > 0)) {
/* 463 */           for (int id : ids) {
/* 464 */             String s = OreDictionary.getOreName(id);
/* 465 */             if (s.startsWith("dye")) {
/* 466 */               for (int a = 0; a < thaumcraft.common.config.ConfigAspects.dyes.length; a++) {
/* 467 */                 if (s.equals(thaumcraft.common.config.ConfigAspects.dyes[a])) {
/* 468 */                   func_85030_a("thaumcraft:zap", 1.0F, 1.0F);
/* 469 */                   setGolemColor((byte)(16 - a));
/* 470 */                   player.func_70694_bm().field_77994_a -= 1;
/* 471 */                   player.func_71038_i();
/* 472 */                   return true;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 479 */       return true;
/*     */     }
/*     */     
/* 482 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource cause)
/*     */   {
/* 488 */     if (this.task != null) this.task.setReserved(false);
/* 489 */     super.func_70645_a(cause);
/*     */     
/* 491 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 493 */       dropCarried();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void dropCarried() {
/* 498 */     for (ItemStack s : getCarrying()) {
/* 499 */       if (s != null) func_70099_a(s, 0.25F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/* 505 */     float b = p_70628_2_ * 0.15F;
/* 506 */     for (ItemStack stack : getProperties().generateComponents()) {
/* 507 */       ItemStack s = stack.func_77946_l();
/* 508 */       if (this.field_70146_Z.nextFloat() < 0.3F + b) {
/* 509 */         if (s.field_77994_a > 0) s.field_77994_a -= this.field_70146_Z.nextInt(s.field_77994_a);
/* 510 */         func_70099_a(s, 0.25F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBesideClimbableBlock()
/*     */   {
/* 518 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 0);
/*     */   }
/*     */   
/*     */   public void setBesideClimbableBlock(boolean p_70839_1_)
/*     */   {
/* 523 */     byte b0 = this.field_70180_af.func_75683_a(20);
/*     */     
/* 525 */     if (p_70839_1_)
/*     */     {
/* 527 */       b0 = (byte)Utils.setBit(b0, 0);
/*     */     }
/*     */     else
/*     */     {
/* 531 */       b0 = (byte)Utils.clearBit(b0, 0);
/*     */     }
/*     */     
/* 534 */     this.field_70180_af.func_75692_b(20, Byte.valueOf(b0));
/*     */   }
/*     */   
/*     */   public boolean isFollowingOwner()
/*     */   {
/* 539 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 1);
/*     */   }
/*     */   
/*     */   public void setFollowingOwner(boolean par1)
/*     */   {
/* 544 */     byte var2 = this.field_70180_af.func_75683_a(20);
/*     */     
/* 546 */     if (par1)
/*     */     {
/* 548 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 1)));
/*     */     }
/*     */     else
/*     */     {
/* 552 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 1)));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70624_b(EntityLivingBase entitylivingbaseIn)
/*     */   {
/* 558 */     super.func_70624_b(entitylivingbaseIn);
/* 559 */     setInCombat(func_70638_az() != null);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isInCombat()
/*     */   {
/* 565 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 3);
/*     */   }
/*     */   
/*     */   public void setInCombat(boolean par1)
/*     */   {
/* 570 */     byte var2 = this.field_70180_af.func_75683_a(20);
/*     */     
/* 572 */     if (par1)
/*     */     {
/* 574 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 3)));
/*     */     }
/*     */     else
/*     */     {
/* 578 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 3)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70652_k(Entity ent)
/*     */   {
/* 586 */     float dmg = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 587 */     int kb = 0;
/*     */     
/* 589 */     if ((ent instanceof EntityLivingBase))
/*     */     {
/* 591 */       dmg += EnchantmentHelper.func_152377_a(func_70694_bm(), ((EntityLivingBase)ent).func_70668_bt());
/* 592 */       kb += EnchantmentHelper.func_77501_a(this);
/*     */     }
/*     */     
/* 595 */     boolean flag = ent.func_70097_a(DamageSource.func_76358_a(this), dmg);
/*     */     
/* 597 */     if (flag)
/*     */     {
/* 599 */       if (((ent instanceof EntityLivingBase)) && (getProperties().hasTrait(EnumGolemTrait.DEFT))) {
/* 600 */         thaumcraft.common.lib.utils.EntityUtils.setRecentlyHit((EntityLivingBase)ent, 100);
/*     */       }
/* 602 */       if (kb > 0)
/*     */       {
/* 604 */         ent.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * kb * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * kb * 0.5F);
/* 605 */         this.field_70159_w *= 0.6D;
/* 606 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 609 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 611 */       if (j > 0)
/*     */       {
/* 613 */         ent.func_70015_d(j * 4);
/*     */       }
/*     */       
/* 616 */       func_174815_a(this, ent);
/*     */       
/* 618 */       if (getProperties().getArms().function != null) {
/* 619 */         getProperties().getArms().function.onMeleeAttack(this, ent);
/*     */       }
/*     */       
/* 622 */       if (((ent instanceof EntityLiving)) && (!((EntityLiving)ent).func_70089_S())) {
/* 623 */         addRankXp(8);
/*     */       }
/*     */     }
/*     */     
/* 627 */     return flag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 633 */   protected Task task = null;
/* 634 */   protected int taskID = Integer.MAX_VALUE;
/*     */   public static final int XPM = 1000;
/*     */   
/* 637 */   public Task getTask() { if ((this.task == null) && (this.taskID != Integer.MAX_VALUE)) {
/* 638 */       this.task = thaumcraft.common.entities.construct.golem.tasks.TaskHandler.getTask(this.field_70170_p.field_73011_w.func_177502_q(), this.taskID);
/* 639 */       this.taskID = Integer.MAX_VALUE;
/*     */     }
/* 641 */     return this.task;
/*     */   }
/*     */   
/*     */   public void setTask(Task task) {
/* 645 */     this.task = task;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addRankXp(int xp)
/*     */   {
/* 654 */     if ((!getProperties().hasTrait(EnumGolemTrait.SMART)) || (this.field_70170_p.field_72995_K)) return;
/* 655 */     int rank = getProperties().getRank();
/* 656 */     if (rank < 10) {
/* 657 */       this.rankXp += xp;
/* 658 */       int xn = (rank + 1) * (rank + 1) * 1000;
/* 659 */       if (this.rankXp >= xn) {
/* 660 */         this.rankXp -= xn;
/* 661 */         IGolemProperties props = getProperties();
/* 662 */         props.setRank(rank + 1);
/* 663 */         setProperties(props);
/* 664 */         if (Config.showGolemEmotes) {
/* 665 */           this.field_70170_p.func_72960_a(this, (byte)9);
/* 666 */           this.field_70170_p.func_72956_a(this, "random.levelup", 0.25F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack holdItem(ItemStack stack)
/*     */   {
/* 674 */     if ((stack == null) || (stack.field_77994_a <= 0)) return null;
/* 675 */     for (int a = 0; a < (getProperties().hasTrait(EnumGolemTrait.HAULER) ? 2 : 1); a++) {
/* 676 */       if (func_70035_c()[a] == null) {
/* 677 */         func_70062_b(a, stack);
/* 678 */         return null;
/*     */       }
/* 680 */       if ((func_70035_c()[a].field_77994_a < func_70035_c()[a].func_77976_d()) && (ItemStack.func_179545_c(func_70035_c()[a], stack)) && (ItemStack.func_77970_a(func_70035_c()[a], stack)))
/*     */       {
/*     */ 
/* 683 */         int d = Math.min(stack.field_77994_a, func_70035_c()[a].func_77976_d() - func_70035_c()[a].field_77994_a);
/* 684 */         stack.field_77994_a -= d;
/* 685 */         func_70035_c()[a].field_77994_a += d;
/* 686 */         if (stack.field_77994_a <= 0) { stack = null;
/*     */         }
/*     */       }
/*     */     }
/* 690 */     return stack;
/*     */   }
/*     */   
/*     */   public ItemStack dropItem(ItemStack stack)
/*     */   {
/* 695 */     ItemStack out = null;
/* 696 */     for (int a = 0; a < (getProperties().hasTrait(EnumGolemTrait.HAULER) ? 2 : 1); a++)
/* 697 */       if (func_70035_c()[a] != null) {
/* 698 */         if (stack == null) {
/* 699 */           out = func_70035_c()[a].func_77946_l();
/*     */           
/* 701 */           func_70062_b(a, null);
/*     */         }
/* 703 */         else if ((ItemStack.func_179545_c(func_70035_c()[a], stack)) && (ItemStack.func_77970_a(func_70035_c()[a], stack)))
/*     */         {
/* 705 */           out = func_70035_c()[a].func_77946_l();
/* 706 */           out.field_77994_a = Math.min(stack.field_77994_a, out.field_77994_a);
/* 707 */           func_70035_c()[a].field_77994_a -= stack.field_77994_a;
/* 708 */           if (func_70035_c()[a].field_77994_a <= 0) {
/* 709 */             func_70062_b(a, null);
/*     */           }
/*     */         }
/* 712 */         if (out != null) break;
/*     */       }
/* 714 */     if ((getProperties().hasTrait(EnumGolemTrait.HAULER)) && (func_70035_c()[0] == null) && (func_70035_c()[1] != null))
/*     */     {
/* 716 */       func_70062_b(0, func_70035_c()[1].func_77946_l());
/* 717 */       func_70062_b(1, null);
/*     */     }
/*     */     
/* 720 */     return out;
/*     */   }
/*     */   
/*     */   public boolean canCarry(ItemStack stack, boolean partial)
/*     */   {
/* 725 */     for (int a = 0; a < (getProperties().hasTrait(EnumGolemTrait.HAULER) ? 2 : 1); a++) {
/* 726 */       if (func_70035_c()[a] == null) return true;
/* 727 */       if ((func_70035_c()[a].field_77994_a < func_70035_c()[a].func_77976_d()) && (ItemStack.func_179545_c(func_70035_c()[a], stack)) && (ItemStack.func_77970_a(func_70035_c()[a], stack)))
/*     */       {
/*     */ 
/* 730 */         return true; }
/*     */     }
/* 732 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isCarrying(ItemStack stack)
/*     */   {
/* 737 */     if (stack == null) return false;
/* 738 */     for (int a = 0; a < (getProperties().hasTrait(EnumGolemTrait.HAULER) ? 2 : 1); a++) {
/* 739 */       if ((func_70035_c()[a] != null) && (func_70035_c()[a].field_77994_a > 0) && 
/* 740 */         (ItemStack.func_179545_c(func_70035_c()[a], stack)) && (ItemStack.func_77970_a(func_70035_c()[a], stack)))
/* 741 */         return true;
/*     */     }
/* 743 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack[] getCarrying()
/*     */   {
/* 748 */     if (getProperties().hasTrait(EnumGolemTrait.HAULER))
/* 749 */       return new ItemStack[] { func_70035_c()[0], func_70035_c()[1] };
/* 750 */     return new ItemStack[] { func_70035_c()[0] };
/*     */   }
/*     */   
/*     */   public EntityLivingBase getGolemEntity()
/*     */   {
/* 755 */     return this;
/*     */   }
/*     */   
/*     */   public World getGolemWorld()
/*     */   {
/* 760 */     return func_130014_f_();
/*     */   }
/*     */   
/*     */ 
/*     */   public void swingArm()
/*     */   {
/* 766 */     if ((!this.field_82175_bq) || (this.field_110158_av >= 3) || (this.field_110158_av < 0))
/*     */     {
/* 768 */       this.field_110158_av = -1;
/* 769 */       this.field_82175_bq = true;
/*     */       
/* 771 */       if ((this.field_70170_p instanceof WorldServer))
/*     */       {
/* 773 */         ((WorldServer)this.field_70170_p).func_73039_n().func_151247_a(this, new S0BPacketAnimation(this, 0));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_82196_d(EntityLivingBase target, float range)
/*     */   {
/* 781 */     if (getProperties().getArms().function != null) {
/* 782 */       getProperties().getArms().function.onRangedAttack(this, target, range);
/*     */     }
/*     */   }
/*     */   
/*     */   public byte getGolemColor()
/*     */   {
/* 788 */     return this.field_70180_af.func_75683_a(21);
/*     */   }
/*     */   
/*     */   public void setGolemColor(byte b) {
/* 792 */     this.field_70180_af.func_75692_b(21, Byte.valueOf(b));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/EntityThaumcraftGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */