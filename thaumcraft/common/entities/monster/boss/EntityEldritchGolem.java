/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.world.BlockLoot;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ 
/*     */ public class EntityEldritchGolem extends EntityThaumcraftBoss implements IEldritchMob, IRangedAttackMob
/*     */ {
/*     */   public EntityEldritchGolem(World p_i1745_1_)
/*     */   {
/*  43 */     super(p_i1745_1_);
/*  44 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  45 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.1D, false));
/*  46 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  47 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  48 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  49 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  50 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true, new Class[0]));
/*  51 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  52 */     func_70105_a(1.75F, 3.5F);
/*  53 */     this.field_70178_ae = true;
/*     */   }
/*     */   
/*     */   public void generateName()
/*     */   {
/*  58 */     int t = (int)func_110148_a(thaumcraft.common.lib.utils.EntityUtils.CHAMPION_MOD).func_111126_e();
/*  59 */     if (t >= 0) {
/*  60 */       func_96094_a(String.format(net.minecraft.util.StatCollector.func_74838_a("entity.Thaumcraft.EldritchGolem.name.custom"), new Object[] { ChampionModifier.mods[t].getModNameLocalized() }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  69 */     super.func_70088_a();
/*  70 */     func_70096_w().func_75682_a(12, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public boolean isHeadless() {
/*  74 */     return func_70096_w().func_75683_a(12) == 1;
/*     */   }
/*     */   
/*     */   public void setHeadless(boolean par1)
/*     */   {
/*  79 */     this.field_70180_af.func_75692_b(12, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  85 */     super.func_70014_b(nbt);
/*  86 */     nbt.func_74757_a("headless", isHeadless());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/*  95 */     super.func_70037_a(nbt);
/*  96 */     setHeadless(nbt.func_74767_n("headless"));
/*  97 */     if (isHeadless()) {
/*  98 */       makeHeadless();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 107 */     return isHeadless() ? 3.33F : 3.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/* 113 */     return super.func_70658_aO() + 6;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 119 */     super.func_110147_ax();
/* 120 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/* 121 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0D);
/* 122 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 128 */     return "mob.irongolem.hit";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 134 */     return "mob.irongolem.death";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_)
/*     */   {
/* 141 */     func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
/*     */   {
/* 147 */     this.spawnTimer = 100;
/* 148 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 154 */     super.func_70636_d();
/*     */     
/* 156 */     if (this.attackTimer > 0)
/*     */     {
/* 158 */       this.attackTimer -= 1;
/*     */     }
/*     */     
/* 161 */     if ((this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7D) && (this.field_70146_Z.nextInt(5) == 0))
/*     */     {
/*     */ 
/* 164 */       IBlockState bs = this.field_70170_p.func_180495_p(func_180425_c());
/*     */       
/* 166 */       if (bs.func_177230_c().func_149688_o() != Material.field_151579_a)
/*     */       {
/* 168 */         this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, func_174813_aQ().field_72338_b + 0.1D, this.field_70161_v + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D, new int[] { Block.func_176210_f(bs) });
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 176 */       if ((!this.field_70170_p.field_72995_K) && 
/* 177 */         ((bs.func_177230_c() instanceof BlockLoot))) {
/* 178 */         this.field_70170_p.func_175655_b(func_180425_c(), true);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 183 */     if (!this.field_70170_p.field_72995_K) {
/* 184 */       IBlockState bs = this.field_70170_p.func_180495_p(func_180425_c());
/* 185 */       float h = bs.func_177230_c().func_176195_g(this.field_70170_p, func_180425_c());
/* 186 */       if ((h >= 0.0F) && (h <= 0.15F)) {
/* 187 */         this.field_70170_p.func_175655_b(func_180425_c(), true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 195 */     if ((!this.field_70170_p.field_72995_K) && (damage > func_110143_aJ()) && (!isHeadless())) {
/* 196 */       setHeadless(true);
/* 197 */       this.spawnTimer = 100;
/* 198 */       double xx = MathHelper.func_76134_b(this.field_70177_z % 360.0F / 180.0F * 3.1415927F) * 0.75F;
/* 199 */       double zz = MathHelper.func_76126_a(this.field_70177_z % 360.0F / 180.0F * 3.1415927F) * 0.75F;
/* 200 */       this.field_70170_p.func_72876_a(this, this.field_70165_t + xx, this.field_70163_u + func_70047_e(), this.field_70161_v + zz, 2.0F, false);
/* 201 */       makeHeadless();
/* 202 */       return false;
/*     */     }
/* 204 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */   void makeHeadless() {
/* 208 */     this.field_70714_bg.func_75776_a(2, new thaumcraft.common.entities.ai.combat.AILongRangeAttack(this, 3.0D, 1.0D, 5, 5, 24.0F));
/*     */   }
/*     */   
/* 211 */   int beamCharge = 0;
/* 212 */   boolean chargingBeam = false;
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity target)
/*     */   {
/* 217 */     if (this.attackTimer > 0) { return false;
/*     */     }
/* 219 */     this.attackTimer = 10;
/* 220 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 221 */     boolean flag = target.func_70097_a(DamageSource.func_76358_a(this), (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.75F);
/*     */     
/*     */ 
/* 224 */     if (flag)
/*     */     {
/* 226 */       target.field_70181_x += 0.2000000059604645D;
/* 227 */       if (isHeadless()) {
/* 228 */         target.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 234 */     return flag;
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/* 239 */     if ((func_70685_l(entitylivingbase)) && (!this.chargingBeam) && (this.beamCharge > 0)) {
/* 240 */       this.beamCharge -= 15 + this.field_70146_Z.nextInt(5);
/* 241 */       func_70671_ap().func_75650_a(entitylivingbase.field_70165_t, entitylivingbase.func_174813_aQ().field_72338_b + entitylivingbase.field_70131_O / 2.0F, entitylivingbase.field_70161_v, 30.0F, 30.0F);
/*     */       
/*     */ 
/*     */ 
/* 245 */       Vec3 v = func_70676_i(1.0F);
/* 246 */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, false);
/* 247 */       blast.field_70165_t += v.field_72450_a;
/* 248 */       blast.field_70161_v += v.field_72449_c;
/* 249 */       blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
/*     */       
/* 251 */       double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
/* 252 */       double d1 = entitylivingbase.field_70163_u - this.field_70163_u - entitylivingbase.field_70131_O / 2.0F;
/* 253 */       double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
/*     */       
/* 255 */       blast.func_70186_c(d0, d1, d2, 0.66F, 5.0F);
/*     */       
/* 257 */       func_85030_a("thaumcraft:egattack", 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 258 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte p_70103_1_)
/*     */   {
/* 267 */     if (p_70103_1_ == 4)
/*     */     {
/* 269 */       this.attackTimer = 10;
/* 270 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */ 
/*     */     }
/* 273 */     else if (p_70103_1_ == 18)
/*     */     {
/* 275 */       this.spawnTimer = 150;
/*     */ 
/*     */     }
/* 278 */     else if (p_70103_1_ == 19)
/*     */     {
/* 280 */       if (this.arcing == 0) {
/* 281 */         float radius = 2.0F + this.field_70146_Z.nextFloat() * 2.0F;
/* 282 */         double radians = Math.toRadians(this.field_70146_Z.nextInt(360));
/* 283 */         double deltaX = radius * Math.cos(radians);
/* 284 */         double deltaZ = radius * Math.sin(radians);
/*     */         
/* 286 */         int bx = MathHelper.func_76128_c(this.field_70165_t + deltaX);
/* 287 */         int by = MathHelper.func_76128_c(this.field_70163_u);
/* 288 */         int bz = MathHelper.func_76128_c(this.field_70161_v + deltaZ);
/* 289 */         BlockPos bp = new BlockPos(bx, by, bz);
/* 290 */         int c = 0;
/* 291 */         while ((c < 5) && (this.field_70170_p.func_175623_d(bp))) {
/* 292 */           c++;
/* 293 */           by--;
/*     */         }
/* 295 */         if ((this.field_70170_p.func_175623_d(bp.func_177984_a())) && (!this.field_70170_p.func_175623_d(bp))) {
/* 296 */           this.ax = bx;
/* 297 */           this.ay = by;
/* 298 */           this.az = bz;
/* 299 */           this.arcing = (8 + this.field_70146_Z.nextInt(5));
/* 300 */           this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:jacobs", 0.8F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.05F, false);
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 306 */       super.func_70103_a(p_70103_1_);
/*     */     }
/*     */   }
/*     */   
/* 310 */   int arcing = 0;
/* 311 */   int ax = 0;
/* 312 */   int ay = 0;
/* 313 */   int az = 0;
/*     */   private int attackTimer;
/*     */   
/*     */   public void func_70071_h_() {
/* 317 */     if (getSpawnTimer() == 150) this.field_70170_p.func_72960_a(this, (byte)18);
/* 318 */     if (getSpawnTimer() > 0) {
/* 319 */       func_70691_i(2.0F);
/*     */     }
/* 321 */     super.func_70071_h_();
/* 322 */     if (this.field_70170_p.field_72995_K) {
/* 323 */       if (isHeadless()) {
/* 324 */         this.field_70125_A = 0.0F;
/* 325 */         float f1 = MathHelper.func_76134_b(-this.field_70761_aq * 0.017453292F - 3.1415927F);
/* 326 */         float f2 = MathHelper.func_76126_a(-this.field_70761_aq * 0.017453292F - 3.1415927F);
/* 327 */         float f3 = -MathHelper.func_76134_b(-this.field_70125_A * 0.017453292F);
/* 328 */         float f4 = MathHelper.func_76126_a(-this.field_70125_A * 0.017453292F);
/* 329 */         Vec3 v = new Vec3(f2 * f3, f4, f1 * f3);
/*     */         
/* 331 */         if (this.field_70146_Z.nextInt(20) == 0) {
/* 332 */           float a = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) / 2.0F;
/* 333 */           float b = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) / 2.0F;
/*     */           
/* 335 */           Thaumcraft.proxy.getFX().spark((float)(this.field_70165_t + v.field_72450_a + a), (float)this.field_70163_u + func_70047_e() - 0.25F, (float)(this.field_70161_v + v.field_72449_c + b), 0.3F, 0.65F + this.field_70146_Z.nextFloat() * 0.1F, 1.0F, 1.0F, 0.8F);
/*     */         }
/*     */         
/*     */ 
/* 339 */         Thaumcraft.proxy.getFX().drawVentParticles((float)this.field_70165_t + v.field_72450_a * 0.66D, (float)this.field_70163_u + func_70047_e() - 0.75F, (float)this.field_70161_v + v.field_72449_c * 0.66D, 0.0D, 0.001D, 0.0D, 5592405, 4.0F);
/*     */         
/*     */ 
/*     */ 
/* 343 */         if (this.arcing > 0) {
/* 344 */           Thaumcraft.proxy.getFX().arcLightning(this.field_70165_t, this.field_70163_u + this.field_70131_O / 2.0F, this.field_70161_v, this.ax + 0.5D, this.ay + 1, this.az + 0.5D, 0.65F + this.field_70146_Z.nextFloat() * 0.1F, 1.0F, 1.0F, 1.0F - this.arcing / 10.0F);
/*     */           
/* 346 */           this.arcing -= 1;
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 351 */       if ((isHeadless()) && (this.beamCharge <= 0)) {
/* 352 */         this.chargingBeam = true;
/*     */       }
/* 354 */       if ((isHeadless()) && (this.chargingBeam)) {
/* 355 */         this.beamCharge += 1;
/* 356 */         this.field_70170_p.func_72960_a(this, (byte)19);
/* 357 */         if (this.beamCharge == 150) this.chargingBeam = false;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer()
/*     */   {
/* 365 */     return this.attackTimer;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/boss/EntityEldritchGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */