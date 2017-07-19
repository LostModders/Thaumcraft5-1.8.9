/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class EntityFireBat extends EntityMob
/*     */ {
/*     */   private BlockPos currentFlightTarget;
/*  33 */   public EntityLivingBase owner = null;
/*     */   
/*     */   public EntityFireBat(World par1World)
/*     */   {
/*  37 */     super(par1World);
/*  38 */     func_70105_a(0.5F, 0.9F);
/*  39 */     setIsBatHanging(true);
/*  40 */     this.field_70178_ae = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70088_a()
/*     */   {
/*  46 */     super.func_70088_a();
/*  47 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/*  54 */     return 15728880;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/*  63 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/*  72 */     return 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70647_i()
/*     */   {
/*  81 */     return super.func_70647_i() * 0.95F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/*  90 */     return (getIsBatHanging()) && (this.field_70146_Z.nextInt(4) != 0) ? null : "mob.bat.idle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/*  99 */     return "mob.bat.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 108 */     return "mob.bat.death";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70104_M()
/*     */   {
/* 117 */     return false;
/*     */   }
/*     */   
/* 120 */   public int damBonus = 0;
/*     */   
/*     */   private int attackTime;
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 127 */     super.func_110147_ax();
/* 128 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(getIsDevil() ? 15.0D : 5.0D);
/* 129 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(getIsSummoned() ? (getIsDevil() ? 3 : 2) + this.damBonus : 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getIsBatHanging()
/*     */   {
/* 135 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 0);
/*     */   }
/*     */   
/*     */   public void setIsBatHanging(boolean par1)
/*     */   {
/* 140 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 142 */     if (par1)
/*     */     {
/* 144 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 0)));
/*     */     }
/*     */     else
/*     */     {
/* 148 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 0)));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getIsSummoned()
/*     */   {
/* 154 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 1);
/*     */   }
/*     */   
/*     */   public void setIsSummoned(boolean par1)
/*     */   {
/* 159 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 161 */     if (par1)
/*     */     {
/* 163 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 1)));
/*     */     }
/*     */     else
/*     */     {
/* 167 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 1)));
/*     */     }
/*     */     
/* 170 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(par1 ? (getIsDevil() ? 3 : 2) + this.damBonus : 1.0D);
/*     */   }
/*     */   
/*     */   public boolean getIsExplosive()
/*     */   {
/* 175 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 2);
/*     */   }
/*     */   
/*     */   public void setIsExplosive(boolean par1)
/*     */   {
/* 180 */     byte var2 = this.field_70180_af.func_75683_a(16);
/* 181 */     if (par1)
/*     */     {
/* 183 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 2)));
/*     */     }
/*     */     else
/*     */     {
/* 187 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 2)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getIsDevil()
/*     */   {
/* 194 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 3);
/*     */   }
/*     */   
/*     */   public void setIsDevil(boolean par1)
/*     */   {
/* 199 */     byte var2 = this.field_70180_af.func_75683_a(16);
/* 200 */     if (par1)
/*     */     {
/* 202 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 3)));
/*     */     }
/*     */     else
/*     */     {
/* 206 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 3)));
/*     */     }
/*     */     
/* 209 */     if (par1) {
/* 210 */       func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(getIsSummoned() ? (par1 ? 3 : 2) + this.damBonus : 1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getIsVampire()
/*     */   {
/* 216 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 4);
/*     */   }
/*     */   
/*     */   public void setIsVampire(boolean par1)
/*     */   {
/* 221 */     byte var2 = this.field_70180_af.func_75683_a(16);
/* 222 */     if (par1)
/*     */     {
/* 224 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 4)));
/*     */     }
/*     */     else
/*     */     {
/* 228 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 4)));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 234 */     if (func_70026_G())
/*     */     {
/* 236 */       func_70097_a(DamageSource.field_76369_e, 1.0F);
/*     */     }
/*     */     
/* 239 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 248 */     super.func_70071_h_();
/*     */     
/* 250 */     if ((this.field_70170_p.field_72995_K) && (getIsExplosive())) {
/* 251 */       Thaumcraft.proxy.getFX().drawGenericParticles(this.field_70169_q + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F, this.field_70167_r + this.field_70131_O / 2.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F, this.field_70166_s + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.8F, false, 151, 9, 1, 7 + this.field_70146_Z.nextInt(5), 0, 1.0F + this.field_70146_Z.nextFloat() * 0.5F, 0.0F, 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 259 */     if (getIsBatHanging())
/*     */     {
/* 261 */       this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/* 262 */       this.field_70163_u = (MathHelper.func_76128_c(this.field_70163_u) + 1.0D - this.field_70131_O);
/*     */     }
/*     */     else
/*     */     {
/* 266 */       this.field_70181_x *= 0.6000000238418579D;
/*     */     }
/*     */     
/* 269 */     if ((this.field_70170_p.field_72995_K) && (!getIsVampire())) {
/* 270 */       this.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, this.field_70169_q + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70167_r + this.field_70131_O / 2.0F + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70166_s + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 275 */       this.field_70170_p.func_175688_a(EnumParticleTypes.FLAME, this.field_70169_q + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70167_r + this.field_70131_O / 2.0F + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70166_s + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, 0.0D, 0.0D, 0.0D, new int[0]);
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
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/* 289 */     super.func_70619_bc();
/*     */     
/* 291 */     if (this.attackTime > 0) { this.attackTime -= 1;
/*     */     }
/* 293 */     BlockPos blockpos = new BlockPos(this);
/* 294 */     BlockPos blockpos1 = blockpos.func_177984_a();
/*     */     
/* 296 */     if (getIsBatHanging())
/*     */     {
/* 298 */       if (!this.field_70170_p.func_180495_p(blockpos1).func_177230_c().func_149721_r())
/*     */       {
/* 300 */         setIsBatHanging(false);
/* 301 */         this.field_70170_p.func_180498_a((EntityPlayer)null, 1015, blockpos, 0);
/*     */       }
/*     */       else
/*     */       {
/* 305 */         if (this.field_70146_Z.nextInt(200) == 0)
/*     */         {
/* 307 */           this.field_70759_as = this.field_70146_Z.nextInt(360);
/*     */         }
/*     */         
/* 310 */         if (this.field_70170_p.func_72890_a(this, 4.0D) != null)
/*     */         {
/* 312 */           setIsBatHanging(false);
/* 313 */           this.field_70170_p.func_180498_a((EntityPlayer)null, 1015, blockpos, 0);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/* 319 */     else if (func_70638_az() == null)
/*     */     {
/* 321 */       if (getIsSummoned()) {
/* 322 */         func_70097_a(DamageSource.field_76377_j, 2.0F);
/*     */       }
/* 324 */       if ((this.currentFlightTarget != null) && ((!this.field_70170_p.func_175623_d(this.currentFlightTarget)) || (this.currentFlightTarget.func_177956_o() < 1)))
/*     */       {
/*     */ 
/*     */ 
/* 328 */         this.currentFlightTarget = null;
/*     */       }
/*     */       
/* 331 */       if ((this.currentFlightTarget == null) || (this.field_70146_Z.nextInt(30) == 0) || (func_174831_c(this.currentFlightTarget) < 4.0D))
/*     */       {
/*     */ 
/* 334 */         this.currentFlightTarget = new BlockPos((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 340 */       double var1 = this.currentFlightTarget.func_177958_n() + 0.5D - this.field_70165_t;
/* 341 */       double var3 = this.currentFlightTarget.func_177956_o() + 0.1D - this.field_70163_u;
/* 342 */       double var5 = this.currentFlightTarget.func_177952_p() + 0.5D - this.field_70161_v;
/* 343 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 344 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 345 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 346 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 347 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 348 */       this.field_70701_bs = 0.5F;
/* 349 */       this.field_70177_z += var8;
/*     */       
/* 351 */       if ((this.field_70146_Z.nextInt(100) == 0) && (this.field_70170_p.func_180495_p(blockpos1).func_177230_c().func_149721_r()))
/*     */       {
/* 353 */         setIsBatHanging(true);
/*     */       }
/*     */     } else {
/* 356 */       double var1 = func_70638_az().field_70165_t - this.field_70165_t;
/* 357 */       double var3 = func_70638_az().field_70163_u + func_70638_az().func_70047_e() * 0.66F - this.field_70163_u;
/* 358 */       double var5 = func_70638_az().field_70161_v - this.field_70161_v;
/* 359 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 360 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 361 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 362 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 363 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 364 */       this.field_70701_bs = 0.5F;
/* 365 */       this.field_70177_z += var8;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 370 */     if (func_70638_az() == null)
/*     */     {
/* 372 */       func_70624_b(findPlayerToAttack());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 380 */     else if (func_70638_az().func_70089_S())
/*     */     {
/* 382 */       float f = func_70638_az().func_70032_d(this);
/*     */       
/* 384 */       if (func_70685_l(func_70638_az()))
/*     */       {
/* 386 */         attackEntity(func_70638_az(), f);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 391 */       func_70624_b(null);
/*     */     }
/*     */     
/* 394 */     if (((func_70638_az() instanceof EntityPlayer)) && (((EntityPlayer)func_70638_az()).field_71075_bZ.field_75102_a))
/*     */     {
/* 396 */       func_70624_b(null);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 408 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180430_e(float par1, float damageMultiplier) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145773_az()
/*     */   {
/* 431 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 440 */     if ((func_180431_b(par1DamageSource)) || (par1DamageSource.func_76347_k()) || (par1DamageSource.func_94541_c()))
/*     */     {
/*     */ 
/* 443 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 447 */     if ((!this.field_70170_p.field_72995_K) && (getIsBatHanging()))
/*     */     {
/* 449 */       setIsBatHanging(false);
/*     */     }
/*     */     
/* 452 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 460 */     return super.func_70652_k(p_70652_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void attackEntity(Entity par1Entity, float par2)
/*     */   {
/* 466 */     if ((this.attackTime <= 0) && (par2 < Math.max(2.5F, par1Entity.field_70130_N * 1.1F)) && (par1Entity.func_174813_aQ().field_72337_e > func_174813_aQ().field_72338_b) && (par1Entity.func_174813_aQ().field_72338_b < func_174813_aQ().field_72337_e))
/*     */     {
/*     */ 
/*     */ 
/* 470 */       if (getIsSummoned()) {
/* 471 */         EntityUtils.setRecentlyHit((EntityLivingBase)par1Entity, 100);
/*     */       }
/*     */       
/* 474 */       if (getIsVampire()) {
/* 475 */         if ((this.owner != null) && (!this.owner.func_82165_m(Potion.field_76428_l.field_76415_H))) {
/* 476 */           this.owner.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 26, 1));
/*     */         }
/* 478 */         func_70691_i(1.0F);
/*     */       }
/*     */       
/* 481 */       this.attackTime = 20;
/* 482 */       if (((getIsExplosive()) || (this.field_70170_p.field_73012_v.nextInt(10) == 0)) && (!this.field_70170_p.field_72995_K) && (!getIsDevil())) {
/* 483 */         par1Entity.field_70172_ad = 0;
/* 484 */         this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5F + (getIsExplosive() ? this.damBonus * 0.33F : 0.0F), false, false);
/* 485 */         func_70106_y();
/*     */       }
/* 487 */       else if ((getIsVampire()) || (this.field_70170_p.field_73012_v.nextBoolean())) {
/* 488 */         double mx = par1Entity.field_70159_w;
/* 489 */         double my = par1Entity.field_70181_x;
/* 490 */         double mz = par1Entity.field_70179_y;
/* 491 */         func_70652_k(par1Entity);
/* 492 */         par1Entity.field_70160_al = false;
/* 493 */         par1Entity.field_70159_w = mx;
/* 494 */         par1Entity.field_70181_x = my;
/* 495 */         par1Entity.field_70179_y = mz;
/*     */       } else {
/* 497 */         par1Entity.func_70015_d(getIsSummoned() ? 4 : 2);
/*     */       }
/*     */       
/* 500 */       this.field_70170_p.func_72956_a(this, "mob.bat.hurt", 0.5F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected EntityLivingBase findPlayerToAttack()
/*     */   {
/* 509 */     double var1 = 12.0D;
/* 510 */     return getIsSummoned() ? null : this.field_70170_p.func_72890_a(this, var1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 519 */     super.func_70037_a(par1NBTTagCompound);
/* 520 */     this.field_70180_af.func_75692_b(16, Byte.valueOf(par1NBTTagCompound.func_74771_c("BatFlags")));
/* 521 */     this.damBonus = par1NBTTagCompound.func_74771_c("damBonus");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 530 */     super.func_70014_b(par1NBTTagCompound);
/* 531 */     par1NBTTagCompound.func_74774_a("BatFlags", this.field_70180_af.func_75683_a(16));
/* 532 */     par1NBTTagCompound.func_74774_a("damBonus", (byte)this.damBonus);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 541 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 542 */     int j = MathHelper.func_76128_c(func_174813_aQ().field_72338_b);
/* 543 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/* 544 */     BlockPos blockpos = new BlockPos(i, j, k);
/* 545 */     int var4 = this.field_70170_p.func_175699_k(blockpos);
/* 546 */     byte var5 = 7;
/* 547 */     return var4 > this.field_70146_Z.nextInt(var5) ? false : super.func_70601_bi();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 556 */     if (!getIsSummoned()) return Items.field_151016_H; return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   protected boolean func_70814_o()
/*     */   {
/* 561 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityFireBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */