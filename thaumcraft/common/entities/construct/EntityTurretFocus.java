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
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget.Sorter;
/*     */ import net.minecraft.entity.ai.EntityAITarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
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
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.wands.ItemWand;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class EntityTurretFocus extends EntityOwnedConstruct
/*     */ {
/*  59 */   protected AspectList vis = new AspectList();
/*  60 */   protected int maxVis = 100;
/*  61 */   protected int attackTimer = 10;
/*     */   
/*     */   public EntityTurretFocus(World worldIn) {
/*  64 */     super(worldIn);
/*  65 */     func_70105_a(0.9F, 0.9F);
/*  66 */     this.field_70714_bg.func_75776_a(2, new EntityAIWatchTarget(this));
/*  67 */     this.field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
/*  68 */     this.field_70714_bg.func_75776_a(4, new EntityAILookIdle(this));
/*  69 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  70 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestValidTarget(this, EntityLivingBase.class, 5, true, false, (Predicate)null));
/*  71 */     setTargetMob(true);
/*  72 */     this.field_70728_aV = 6;
/*     */   }
/*     */   
/*     */   public EntityTurretFocus(World worldIn, BlockPos pos, EnumFacing face) {
/*  76 */     this(worldIn);
/*  77 */     func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/*  78 */     setFacing(face);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  84 */     super.func_70088_a();
/*  85 */     this.field_70180_af.func_75682_a(18, new ItemStack(ItemsTC.wand));
/*  86 */     this.field_70180_af.func_75682_a(19, Byte.valueOf((byte)0));
/*  87 */     this.field_70180_af.func_75682_a(20, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  93 */     return func_142012_a(otherEntity.func_96124_cp());
/*     */   }
/*     */   
/*     */ 
/*     */   public Team func_96124_cp()
/*     */   {
/*  99 */     if (isOwned())
/*     */     {
/* 101 */       EntityLivingBase entitylivingbase = getOwnerEntity();
/*     */       
/* 103 */       if (entitylivingbase != null)
/*     */       {
/* 105 */         return entitylivingbase.func_96124_cp();
/*     */       }
/*     */     }
/*     */     
/* 109 */     return super.func_96124_cp();
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 115 */     return this.field_70131_O * 0.5F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 121 */     super.func_110147_ax();
/* 122 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/* 123 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/* 128 */     return 4;
/*     */   }
/*     */   
/* 131 */   boolean attackedLastTick = false;
/* 132 */   int attackCount = 0;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 136 */     super.func_70071_h_();
/*     */     
/* 138 */     doUpdateStuff();
/*     */     
/* 140 */     if (!this.field_70170_p.field_72995_K) {
/* 141 */       this.field_70177_z = this.field_70759_as;
/*     */       
/* 143 */       if (this.field_70173_aa % 4 == 0) { rechargeVis();
/*     */       }
/* 145 */       if (this.field_70173_aa % 40 == 0) { func_70691_i(1.0F);
/*     */       }
/* 147 */       if (this.attackTimer > 0) { this.attackTimer -= 1;
/*     */       }
/* 149 */       boolean at = this.attackedLastTick;
/* 150 */       this.attackedLastTick = false;
/* 151 */       if ((func_70638_az() != null) && (this.attackTimer <= 0)) {
/* 152 */         attackEntityWithFocus();
/*     */       }
/* 154 */       if ((at) && (!this.attackedLastTick)) {
/* 155 */         this.attackCount = 0;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doUpdateStuff() {
/* 161 */     if ((func_70638_az() != null) && ((func_70638_az() instanceof EntityTurretFocus)) && (!getTargetFriendly()))
/* 162 */       func_70624_b(null);
/* 163 */     if ((!this.field_70170_p.field_72995_K) && (!MinecraftServer.func_71276_C().func_71219_W()) && 
/* 164 */       (func_70638_az() != null) && ((func_70638_az() instanceof EntityPlayer)) && (func_70638_az() != getOwnerEntity())) {
/* 165 */       func_70624_b(null);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70030_z()
/*     */   {
/* 172 */     if (func_70115_ae()) this.field_70163_u += 1.0D;
/* 173 */     super.func_70030_z();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 180 */     if ((par1 == 16) && (func_70694_bm() != null) && (func_70694_bm().func_77973_b() != null) && ((func_70694_bm().func_77973_b() instanceof ItemFocusBasic)))
/*     */     {
/* 182 */       ((ItemFocusBasic)func_70694_bm().func_77973_b()).onFocusActivation(getWand(), this.field_70170_p, this, getMovingObjectPosition(), this.attackCount);
/*     */     }
/*     */     else
/*     */     {
/* 186 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount)
/*     */   {
/* 192 */     this.field_70177_z = ((float)(this.field_70177_z + func_70681_au().nextGaussian() * 45.0D));
/* 193 */     this.field_70125_A = ((float)(this.field_70125_A + func_70681_au().nextGaussian() * 20.0D));
/* 194 */     return super.func_70097_a(source, amount);
/*     */   }
/*     */   
/*     */   protected void attackEntityWithFocus()
/*     */   {
/* 199 */     if ((func_70694_bm() != null) && (func_70694_bm().func_77973_b() != null) && ((func_70694_bm().func_77973_b() instanceof ItemFocusBasic)))
/*     */     {
/* 201 */       ItemFocusBasic focus = (ItemFocusBasic)func_70694_bm().func_77973_b();
/*     */       
/* 203 */       double dis = func_70032_d(func_70638_az());
/* 204 */       if ((dis > focus.getTurretRange(func_70694_bm())) || (!EntityUtils.isVisibleTo(0.3F, this, func_70638_az(), focus.getTurretRange(func_70694_bm())))) {
/* 205 */         return;
/*     */       }
/*     */       
/* 208 */       AspectList cost = focus.getVisCost(func_70694_bm());
/* 209 */       if (consumeVis(cost, false)) {
/* 210 */         float d = (float)(dis / focus.getTurretRange(func_70694_bm()));
/* 211 */         this.field_70125_A -= focus.getTurretCorrection(func_70694_bm()) * d;
/* 212 */         if (focus.onFocusActivation(getWand(), this.field_70170_p, this, getMovingObjectPosition(), this.attackCount)) {
/* 213 */           consumeVis(cost, true);
/* 214 */           this.attackTimer = (focus.getActivationCooldown(func_70694_bm()) / 50);
/* 215 */           if (!focus.isVisCostPerTick(func_70694_bm())) {
/* 216 */             this.attackTimer = ((int)(this.attackTimer * 1.33D));
/*     */           }
/* 218 */           this.field_70125_A += focus.getTurretCorrection(func_70694_bm()) * d;
/* 219 */           this.attackedLastTick = true;
/* 220 */           this.attackCount += 1;
/* 221 */           this.field_70170_p.func_72960_a(this, (byte)16);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean consumeVis(AspectList aspects, boolean doit)
/*     */   {
/* 229 */     if (this.field_70170_p.field_72995_K) return true;
/* 230 */     if ((aspects == null) || (aspects.size() == 0)) return false;
/* 231 */     for (Aspect aspect : aspects.getAspects()) {
/* 232 */       int cost = aspects.getAmount(aspect);
/* 233 */       if (this.vis.getAmount(aspect) < cost) return false;
/*     */     }
/* 235 */     if (doit)
/* 236 */       for (Aspect aspect : aspects.getAspects()) {
/* 237 */         int cost = aspects.getAmount(aspect);
/* 238 */         this.vis.remove(aspect, cost);
/*     */       }
/* 240 */     return true;
/*     */   }
/*     */   
/*     */   protected void rechargeVis() {
/* 244 */     Aspect low = null;
/* 245 */     int amt = Integer.MAX_VALUE;
/* 246 */     for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 247 */       if ((this.vis.getAmount(aspect) < this.maxVis) && (this.vis.getAmount(aspect) < amt) && (AuraHandler.getAuraCurrent(this.field_70170_p, func_180425_c(), aspect) > 0))
/*     */       {
/* 249 */         low = aspect;
/* 250 */         amt = this.vis.getAmount(aspect);
/*     */       }
/*     */     }
/* 253 */     if (low != null) {
/* 254 */       this.vis.add(low, AuraHandler.drainAuraAvailable(this.field_70170_p, func_180425_c(), low, 1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70104_M()
/*     */   {
/* 261 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/* 267 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 275 */     super.func_70037_a(nbt);
/*     */     
/* 277 */     this.vis.readFromNBT(nbt);
/* 278 */     setFacing(EnumFacing.field_82609_l[nbt.func_74771_c("face")]);
/* 279 */     this.field_70180_af.func_75692_b(20, Byte.valueOf(nbt.func_74771_c("targets")));
/*     */     
/* 281 */     updateFocus();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 287 */     super.func_70014_b(nbt);
/*     */     
/* 289 */     this.vis.writeToNBT(nbt);
/* 290 */     nbt.func_74774_a("face", (byte)getFacing().ordinal());
/* 291 */     nbt.func_74774_a("targets", this.field_70180_af.func_75683_a(20));
/*     */   }
/*     */   
/*     */   public EnumFacing getFacing()
/*     */   {
/* 296 */     return EnumFacing.field_82609_l[this.field_70180_af.func_75683_a(19)];
/*     */   }
/*     */   
/*     */   public void setFacing(EnumFacing face) {
/* 300 */     this.field_70180_af.func_75692_b(19, Byte.valueOf((byte)face.ordinal()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70091_d(double x, double y, double z) {}
/*     */   
/*     */ 
/*     */   public void func_70060_a(float strafe, float forward, float friction) {}
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 311 */     if ((!this.field_70170_p.field_72995_K) && (isOwner(player)) && (!this.field_70128_L)) {
/* 312 */       if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof IWand))) {
/* 313 */         func_85030_a("thaumcraft:zap", 1.0F, 1.0F);
/* 314 */         dropFocus();
/* 315 */         func_70099_a(new ItemStack(ItemsTC.turretPlacer, 1, 1), 0.5F);
/* 316 */         func_70106_y();
/* 317 */         player.func_71038_i();
/*     */       } else {
/* 319 */         player.openGui(Thaumcraft.instance, 15, this.field_70170_p, func_145782_y(), 0, 0);
/*     */       }
/* 321 */       return true;
/*     */     }
/*     */     
/* 324 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource cause)
/*     */   {
/* 330 */     super.func_70645_a(cause);
/*     */     
/* 332 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 334 */       dropFocus();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void dropFocus() {
/* 339 */     if (func_70694_bm() != null) {
/* 340 */       func_70099_a(func_70694_bm(), 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/* 346 */     float b = p_70628_2_ * 0.15F;
/* 347 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(ItemsTC.mind, 1, 1), 0.5F);
/* 348 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(ItemsTC.gear), 0.5F);
/* 349 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plank), 0.5F);
/* 350 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plank), 0.5F);
/*     */   }
/*     */   
/*     */   public ItemStack getWand()
/*     */   {
/* 355 */     return this.field_70180_af.func_82710_f(18);
/*     */   }
/*     */   
/*     */   public void setWand(ItemStack ws)
/*     */   {
/* 360 */     this.field_70180_af.func_75692_b(18, ws);
/*     */   }
/*     */   
/*     */   public void updateFocus() {
/* 364 */     ItemStack wand = new ItemStack(ItemsTC.wand);
/* 365 */     ItemWand ws = (ItemWand)wand.func_77973_b();
/* 366 */     ws.setCap(wand, ConfigItems.WAND_CAP_GOLD);
/* 367 */     ws.setRod(wand, ConfigItems.WAND_ROD_GREATWOOD);
/* 368 */     if (func_70694_bm() != null) {
/* 369 */       ws.setFocus(wand, func_70694_bm());
/* 370 */       ItemFocusBasic focus = (ItemFocusBasic)func_70694_bm().func_77973_b();
/* 371 */       func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(focus.getTurretRange(func_70694_bm()));
/*     */     }
/* 373 */     setWand(wand);
/*     */   }
/*     */   
/*     */   protected MovingObjectPosition getMovingObjectPosition()
/*     */   {
/* 378 */     float f = this.field_70127_C + (this.field_70125_A - this.field_70127_C);
/* 379 */     float f1 = this.field_70126_B + (this.field_70177_z - this.field_70126_B);
/* 380 */     double d0 = this.field_70169_q + (this.field_70165_t - this.field_70169_q);
/* 381 */     double d1 = this.field_70167_r + (this.field_70163_u - this.field_70167_r) + func_70047_e();
/* 382 */     double d2 = this.field_70166_s + (this.field_70161_v - this.field_70166_s);
/* 383 */     Vec3 vec3 = new Vec3(d0, d1, d2);
/* 384 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/* 385 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/* 386 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/* 387 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/* 388 */     float f6 = f3 * f4;
/* 389 */     float f7 = f2 * f4;
/* 390 */     double d3 = 5.0D;
/* 391 */     Vec3 vec31 = vec3.func_72441_c(f6 * d3, f5 * d3, f7 * d3);
/* 392 */     return this.field_70170_p.func_147447_a(vec3, vec31, true, false, false);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70646_bf()
/*     */   {
/* 398 */     return 20;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/* 404 */     if ((IAnimals.class.isAssignableFrom(clazz)) && (!IMob.class.isAssignableFrom(clazz)) && (getTargetAnimal())) return true;
/* 405 */     if ((IMob.class.isAssignableFrom(clazz)) && (getTargetMob())) return true;
/* 406 */     if ((EntityPlayer.class.isAssignableFrom(clazz)) && (getTargetPlayer())) {
/* 407 */       if ((!this.field_70170_p.field_72995_K) && (!MinecraftServer.func_71276_C().func_71219_W()) && (!getTargetFriendly())) {
/* 408 */         setTargetPlayer(false);
/* 409 */         return false;
/*     */       }
/* 411 */       return true;
/*     */     }
/*     */     
/* 414 */     return false;
/*     */   }
/*     */   
/*     */   public boolean getTargetAnimal()
/*     */   {
/* 419 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 0);
/*     */   }
/*     */   
/*     */   public void setTargetAnimal(boolean par1)
/*     */   {
/* 424 */     byte var2 = this.field_70180_af.func_75683_a(20);
/* 425 */     if (par1) this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 0))); else
/* 426 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 0)));
/* 427 */     func_70624_b(null);
/*     */   }
/*     */   
/*     */   public boolean getTargetMob()
/*     */   {
/* 432 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 1);
/*     */   }
/*     */   
/*     */   public void setTargetMob(boolean par1)
/*     */   {
/* 437 */     byte var2 = this.field_70180_af.func_75683_a(20);
/* 438 */     if (par1) this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 1))); else
/* 439 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 1)));
/* 440 */     func_70624_b(null);
/*     */   }
/*     */   
/*     */   public boolean getTargetPlayer()
/*     */   {
/* 445 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 2);
/*     */   }
/*     */   
/*     */   public void setTargetPlayer(boolean par1)
/*     */   {
/* 450 */     byte var2 = this.field_70180_af.func_75683_a(20);
/* 451 */     if (par1) this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 2))); else
/* 452 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 2)));
/* 453 */     func_70624_b(null);
/*     */   }
/*     */   
/*     */   public boolean getTargetFriendly()
/*     */   {
/* 458 */     return Utils.getBit(this.field_70180_af.func_75683_a(20), 3);
/*     */   }
/*     */   
/*     */   public void setTargetFriendly(boolean par1)
/*     */   {
/* 463 */     byte var2 = this.field_70180_af.func_75683_a(20);
/* 464 */     if (par1) this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.setBit(var2, 3))); else
/* 465 */       this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)Utils.clearBit(var2, 3)));
/* 466 */     func_70624_b(null);
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
/* 479 */       this.theWatcher = p_i1631_1_;
/* 480 */       func_75248_a(2);
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_75250_a()
/*     */     {
/* 486 */       if (this.theWatcher.func_70638_az() != null)
/*     */       {
/* 488 */         this.closestEntity = this.theWatcher.func_70638_az();
/*     */       }
/*     */       
/* 491 */       return this.closestEntity != null;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_75253_b()
/*     */     {
/* 497 */       float d = (float)getTargetDistance();
/* 498 */       return this.closestEntity.func_70089_S();
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75249_e()
/*     */     {
/* 504 */       this.lookTime = (40 + this.theWatcher.func_70681_au().nextInt(40));
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75251_c()
/*     */     {
/* 510 */       this.closestEntity = null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75246_d()
/*     */     {
/* 516 */       this.theWatcher.func_70671_ap().func_75650_a(this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + this.closestEntity.func_70047_e(), this.closestEntity.field_70161_v, 10.0F, this.theWatcher.func_70646_bf());
/* 517 */       this.lookTime -= 1;
/*     */     }
/*     */     
/*     */     protected double getTargetDistance()
/*     */     {
/* 522 */       IAttributeInstance iattributeinstance = this.theWatcher.func_110148_a(SharedMonsterAttributes.field_111265_b);
/* 523 */       return iattributeinstance == null ? 16.0D : iattributeinstance.func_111126_e();
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
/* 537 */       this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false);
/*     */     }
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45879_1_, Class p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_)
/*     */     {
/* 542 */       this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate)null);
/*     */     }
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45880_1_, Class p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate tselector)
/*     */     {
/* 547 */       super(p_i45880_4_, p_i45880_5_);
/* 548 */       this.targetClass = p_i45880_2_;
/* 549 */       this.targetChance = p_i45880_3_;
/* 550 */       this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(p_i45880_1_);
/* 551 */       func_75248_a(1);
/* 552 */       this.targetEntitySelector = new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00001621";
/*     */         
/*     */         public boolean applySelection(EntityLivingBase entity) {
/* 557 */           if ((tselector != null) && (!tselector.apply(entity)))
/*     */           {
/* 559 */             return false;
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 564 */           if ((entity instanceof EntityPlayer))
/*     */           {
/* 566 */             double d0 = EntityTurretFocus.EntityAINearestValidTarget.this.func_111175_f();
/*     */             
/* 568 */             if (entity.func_70093_af())
/*     */             {
/* 570 */               d0 *= 0.800000011920929D;
/*     */             }
/*     */             
/* 573 */             if (entity.func_82150_aj())
/*     */             {
/* 575 */               float f = ((EntityPlayer)entity).func_82243_bO();
/*     */               
/* 577 */               if (f < 0.1F)
/*     */               {
/* 579 */                 f = 0.1F;
/*     */               }
/*     */               
/* 582 */               d0 *= 0.7F * f;
/*     */             }
/*     */             
/* 585 */             if (entity.func_70032_d(EntityTurretFocus.EntityAINearestValidTarget.this.field_75299_d) > d0)
/*     */             {
/* 587 */               return false;
/*     */             }
/*     */           }
/*     */           
/* 591 */           return EntityTurretFocus.EntityAINearestValidTarget.this.func_75296_a(entity, false);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_)
/*     */         {
/* 596 */           return applySelection((EntityLivingBase)p_apply_1_);
/*     */         }
/*     */       };
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_75253_b()
/*     */     {
/* 604 */       EntityLivingBase entitylivingbase = this.field_75299_d.func_70638_az();
/* 605 */       if (entitylivingbase == null)
/*     */       {
/* 607 */         return false;
/*     */       }
/* 609 */       if (!entitylivingbase.func_70089_S())
/*     */       {
/* 611 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 615 */       Team team = this.field_75299_d.func_96124_cp();
/* 616 */       Team team1 = entitylivingbase.func_96124_cp();
/*     */       
/* 618 */       if ((team != null) && (team1 == team) && (!((EntityTurretFocus)this.field_75299_d).getTargetFriendly()))
/*     */       {
/* 620 */         return false;
/*     */       }
/*     */       
/* 623 */       if ((team != null) && (team1 != team) && (((EntityTurretFocus)this.field_75299_d).getTargetFriendly()))
/*     */       {
/* 625 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 629 */       double d0 = func_111175_f();
/*     */       
/* 631 */       if (this.field_75299_d.func_70068_e(entitylivingbase) > d0 * d0)
/*     */       {
/* 633 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 637 */       if (this.field_75297_f)
/*     */       {
/* 639 */         if (this.field_75299_d.func_70635_at().func_75522_a(entitylivingbase))
/*     */         {
/* 641 */           this.targetUnseenTicks = 0;
/*     */         }
/* 643 */         else if (++this.targetUnseenTicks > 60)
/*     */         {
/* 645 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 649 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected boolean func_75296_a(EntityLivingBase p_75296_1_, boolean p_75296_2_)
/*     */     {
/* 658 */       if (!isGoodTarget(this.field_75299_d, p_75296_1_, p_75296_2_, this.field_75297_f))
/*     */       {
/* 660 */         return false;
/*     */       }
/* 662 */       if (!this.field_75299_d.func_180485_d(new BlockPos(p_75296_1_)))
/*     */       {
/* 664 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 668 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private boolean isGoodTarget(EntityLiving attacker, EntityLivingBase posTar, boolean p_179445_2_, boolean checkSight)
/*     */     {
/* 675 */       if (posTar == null)
/*     */       {
/* 677 */         return false;
/*     */       }
/* 679 */       if (posTar == attacker)
/*     */       {
/* 681 */         return false;
/*     */       }
/* 683 */       if (!posTar.func_70089_S())
/*     */       {
/* 685 */         return false;
/*     */       }
/* 687 */       if (!attacker.func_70686_a(posTar.getClass()))
/*     */       {
/* 689 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 693 */       Team team = attacker.func_96124_cp();
/* 694 */       Team team1 = posTar.func_96124_cp();
/* 695 */       if ((team != null) && (team1 == team) && (!((EntityTurretFocus)attacker).getTargetFriendly()))
/*     */       {
/* 697 */         return false;
/*     */       }
/*     */       
/* 700 */       if ((team != null) && (team1 != team) && (((EntityTurretFocus)attacker).getTargetFriendly()))
/*     */       {
/* 702 */         return false;
/*     */       }
/*     */       
/* 705 */       if (((attacker instanceof IEntityOwnable)) && (org.apache.commons.lang3.StringUtils.isNotEmpty(((IEntityOwnable)attacker).func_152113_b())))
/*     */       {
/* 707 */         if (((posTar instanceof IEntityOwnable)) && (((IEntityOwnable)attacker).func_152113_b().equals(((IEntityOwnable)posTar).func_152113_b())) && (!((EntityTurretFocus)attacker).getTargetFriendly()))
/*     */         {
/*     */ 
/*     */ 
/* 711 */           return false;
/*     */         }
/*     */         
/* 714 */         if ((!(posTar instanceof IEntityOwnable)) && (!(posTar instanceof EntityPlayer)) && (((EntityTurretFocus)attacker).getTargetFriendly())) {
/* 715 */           return false;
/*     */         }
/*     */         
/* 718 */         if (((posTar instanceof IEntityOwnable)) && (!((IEntityOwnable)attacker).func_152113_b().equals(((IEntityOwnable)posTar).func_152113_b())) && (((EntityTurretFocus)attacker).getTargetFriendly()))
/*     */         {
/*     */ 
/*     */ 
/* 722 */           return false;
/*     */         }
/*     */         
/* 725 */         if ((posTar == ((IEntityOwnable)attacker).func_70902_q()) && (!((EntityTurretFocus)attacker).getTargetFriendly()))
/*     */         {
/* 727 */           return false;
/*     */         }
/*     */         
/*     */       }
/* 731 */       else if (((posTar instanceof EntityPlayer)) && (!p_179445_2_) && (((EntityPlayer)posTar).field_71075_bZ.field_75102_a) && (!((EntityTurretFocus)attacker).getTargetFriendly()))
/*     */       {
/*     */ 
/* 734 */         return false;
/*     */       }
/*     */       
/* 737 */       return (!checkSight) || (attacker.func_70635_at().func_75522_a(posTar));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public boolean func_75250_a()
/*     */     {
/* 744 */       if ((this.targetChance > 0) && (this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0))
/*     */       {
/* 746 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 750 */       double d0 = func_111175_f();
/* 751 */       List list = this.field_75299_d.field_70170_p.func_175647_a(this.targetClass, this.field_75299_d.func_174813_aQ().func_72314_b(d0, 4.0D, d0), Predicates.and(this.targetEntitySelector, EntitySelectors.field_180132_d));
/* 752 */       Collections.sort(list, this.theNearestAttackableTargetSorter);
/*     */       
/* 754 */       if (list.isEmpty())
/*     */       {
/* 756 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 760 */       this.targetEntity = ((EntityLivingBase)list.get(0));
/* 761 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void func_75249_e()
/*     */     {
/* 771 */       this.field_75299_d.func_70624_b(this.targetEntity);
/* 772 */       this.targetUnseenTicks = 0;
/* 773 */       super.func_75249_e();
/*     */     }
/*     */     
/*     */     public class Sorter implements Comparator
/*     */     {
/*     */       private final Entity theEntity;
/*     */       private static final String __OBFID = "CL_00001622";
/*     */       
/*     */       public Sorter(Entity p_i1662_1_)
/*     */       {
/* 783 */         this.theEntity = p_i1662_1_;
/*     */       }
/*     */       
/*     */       public int compare(Entity p_compare_1_, Entity p_compare_2_)
/*     */       {
/* 788 */         double d0 = this.theEntity.func_70068_e(p_compare_1_);
/* 789 */         double d1 = this.theEntity.func_70068_e(p_compare_2_);
/* 790 */         return d0 > d1 ? 1 : d0 < d1 ? -1 : 0;
/*     */       }
/*     */       
/*     */       public int compare(Object p_compare_1_, Object p_compare_2_)
/*     */       {
/* 795 */         return compare((Entity)p_compare_1_, (Entity)p_compare_2_);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/EntityTurretFocus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */