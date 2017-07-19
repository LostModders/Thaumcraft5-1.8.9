/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.DataWatcher;
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
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.EnderTeleportEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.api.internal.EnumWarpType;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.entities.projectile.EntityEldritchOrb;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXSonic;
/*     */ 
/*     */ public class EntityEldritchWarden extends EntityThaumcraftBoss implements IRangedAttackMob, IEldritchMob
/*     */ {
/*     */   public EntityEldritchWarden(World p_i1745_1_)
/*     */   {
/*  52 */     super(p_i1745_1_);
/*     */     
/*  54 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  55 */     this.field_70714_bg.func_75776_a(2, new thaumcraft.common.entities.ai.combat.AILongRangeAttack(this, 3.0D, 1.0D, 20, 40, 24.0F));
/*  56 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.1D, false));
/*  57 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  58 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
/*  59 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  60 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  61 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  62 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  63 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, thaumcraft.common.entities.monster.cult.EntityCultist.class, true));
/*  64 */     func_70105_a(1.5F, 3.5F);
/*     */   }
/*     */   
/*     */   public void generateName()
/*     */   {
/*  69 */     int t = (int)func_110148_a(thaumcraft.common.lib.utils.EntityUtils.CHAMPION_MOD).func_111126_e();
/*  70 */     if (t >= 0) {
/*  71 */       func_96094_a(String.format(net.minecraft.util.StatCollector.func_74838_a("entity.Thaumcraft.EldritchWarden.name.custom"), new Object[] { getTitle(), ChampionModifier.mods[t].getModNameLocalized() }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private String getTitle()
/*     */   {
/*  78 */     return this.titles[func_70096_w().func_75683_a(16)];
/*     */   }
/*     */   
/*     */   private void setTitle(int title) {
/*  82 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)title));
/*     */   }
/*     */   
/*  85 */   String[] titles = { "Aphoom-Zhah", "Basatan", "Chaugnar Faugn", "Mnomquah", "Nyogtha", "Oorn", "Shaikorth", "Rhan-Tegoth", "Rhogog", "Shudde M'ell", "Vulthoom", "Yag-Kosha", "Yibb-Tstll", "Zathog", "Zushakon" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  93 */     super.func_110147_ax();
/*  94 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
/*  95 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.33D);
/*  96 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/* 102 */     super.func_70088_a();
/* 103 */     func_70096_w().func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 109 */     super.func_70014_b(nbt);
/* 110 */     nbt.func_74774_a("title", func_70096_w().func_75683_a(16));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 116 */     super.func_70037_a(nbt);
/* 117 */     setTitle(nbt.func_74771_c("title"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/* 126 */     return super.func_70658_aO() + 4;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/* 132 */     if (this.fieldFrenzyCounter == 0) {
/* 133 */       super.func_70619_bc();
/*     */     }
/*     */     
/* 136 */     if ((this.field_70172_ad <= 0) && (this.field_70173_aa % 25 == 0)) {
/* 137 */       int bh = (int)(func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * 0.66D);
/* 138 */       if (func_110139_bj() < bh) {
/* 139 */         func_110149_m(func_110139_bj() + 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 145 */     if (getSpawnTimer() == 150) this.field_70170_p.func_72960_a(this, (byte)18);
/* 146 */     super.func_70071_h_();
/* 147 */     if (this.field_70170_p.field_72995_K) {
/* 148 */       if (this.armLiftL > 0.0F) this.armLiftL -= 0.05F;
/* 149 */       if (this.armLiftR > 0.0F) this.armLiftR -= 0.05F;
/* 150 */       float x = (float)(this.field_70165_t + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 151 */       float z = (float)(this.field_70161_v + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 152 */       Thaumcraft.proxy.getFX().wispFXEG(x, (float)(this.field_70163_u + 0.25D * this.field_70131_O), z, this);
/*     */       
/* 154 */       if (this.spawnTimer > 0) {
/* 155 */         float he = Math.max(1.0F, this.field_70131_O * ((150 - this.spawnTimer) / 150.0F));
/* 156 */         for (int a = 0; a < 33; a++) {
/* 157 */           Thaumcraft.proxy.getFX().smokeSpiral(this.field_70165_t, func_174813_aQ().field_72338_b + he / 2.0F, this.field_70161_v, he, this.field_70146_Z.nextInt(360), MathHelper.func_76128_c(func_174813_aQ().field_72338_b) - 1, 2232623);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 174 */     super.func_70636_d();
/* 175 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 176 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 177 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 179 */     for (int l = 0; l < 4; l++)
/*     */     {
/* 181 */       i = MathHelper.func_76128_c(this.field_70165_t + (l % 2 * 2 - 1) * 0.25F);
/* 182 */       j = MathHelper.func_76128_c(this.field_70163_u);
/* 183 */       k = MathHelper.func_76128_c(this.field_70161_v + (l / 2 % 2 * 2 - 1) * 0.25F);
/* 184 */       BlockPos bp = new BlockPos(i, j, k);
/* 185 */       if (this.field_70170_p.func_175623_d(bp))
/*     */       {
/* 187 */         this.field_70170_p.func_175656_a(bp, BlocksTC.effect.func_176203_a(1));
/*     */       }
/*     */     }
/*     */     
/* 191 */     if ((!this.field_70170_p.field_72995_K) && (this.fieldFrenzyCounter > 0)) {
/* 192 */       if (this.fieldFrenzyCounter == 150) {
/* 193 */         teleportHome();
/*     */       }
/* 195 */       performFieldFrenzy();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 200 */   boolean fieldFrenzy = false;
/* 201 */   int fieldFrenzyCounter = 0;
/*     */   
/*     */   private void performFieldFrenzy()
/*     */   {
/* 205 */     if ((this.fieldFrenzyCounter < 121) && (this.fieldFrenzyCounter % 10 == 0)) {
/* 206 */       this.field_70170_p.func_72960_a(this, (byte)17);
/* 207 */       double radius = (150 - this.fieldFrenzyCounter) / 8.0D;
/* 208 */       int d = 1 + this.fieldFrenzyCounter / 8;
/* 209 */       int i = MathHelper.func_76128_c(this.field_70165_t);
/* 210 */       int j = MathHelper.func_76128_c(this.field_70163_u);
/* 211 */       int k = MathHelper.func_76128_c(this.field_70161_v);
/* 212 */       for (int q = 0; q < 180 / d; q++)
/*     */       {
/* 214 */         double radians = Math.toRadians(q * 2 * d);
/* 215 */         int deltaX = (int)(radius * Math.cos(radians));
/* 216 */         int deltaZ = (int)(radius * Math.sin(radians));
/* 217 */         BlockPos bp = new BlockPos(i + deltaX, j, k + deltaZ);
/* 218 */         if ((this.field_70170_p.func_175623_d(bp)) && (this.field_70170_p.func_175677_d(bp.func_177977_b(), false)))
/*     */         {
/* 220 */           this.field_70170_p.func_175656_a(bp, BlocksTC.effect.func_176203_a(1));
/* 221 */           this.field_70170_p.func_175684_a(bp, BlocksTC.effect, 250 + this.field_70146_Z.nextInt(150));
/*     */           
/* 223 */           if (this.field_70146_Z.nextFloat() < 0.3F) {
/* 224 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(bp, this, 0.5F + this.field_70146_Z.nextFloat() * 0.2F, 0.0F, 0.5F + this.field_70146_Z.nextFloat() * 0.2F), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.func_177502_q(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 229 */       this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:zap", 1.0F, 0.9F + this.field_70146_Z.nextFloat() * 0.1F);
/*     */     }
/*     */     
/* 232 */     this.fieldFrenzyCounter -= 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void teleportHome()
/*     */   {
/* 239 */     EnderTeleportEvent event = new EnderTeleportEvent(this, func_180486_cf().func_177958_n(), func_180486_cf().func_177956_o(), func_180486_cf().func_177952_p(), 0.0F);
/* 240 */     if (MinecraftForge.EVENT_BUS.post(event)) {
/* 241 */       return;
/*     */     }
/* 243 */     double d3 = this.field_70165_t;
/* 244 */     double d4 = this.field_70163_u;
/* 245 */     double d5 = this.field_70161_v;
/* 246 */     this.field_70165_t = event.targetX;
/* 247 */     this.field_70163_u = event.targetY;
/* 248 */     this.field_70161_v = event.targetZ;
/* 249 */     boolean flag = false;
/* 250 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 251 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 252 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/* 253 */     BlockPos bp = new BlockPos(i, j, k);
/* 254 */     if (this.field_70170_p.func_175667_e(bp))
/*     */     {
/* 256 */       bp = new BlockPos(i, j, k);
/* 257 */       boolean flag1 = false;
/* 258 */       int tries = 20;
/* 259 */       while ((!flag1) && (tries > 0))
/*     */       {
/* 261 */         Block block = this.field_70170_p.func_180495_p(bp.func_177977_b()).func_177230_c();
/* 262 */         Block block2 = this.field_70170_p.func_180495_p(bp).func_177230_c();
/* 263 */         if ((block.func_149688_o().func_76230_c()) && (!block2.func_149688_o().func_76230_c()))
/*     */         {
/* 265 */           flag1 = true;
/*     */         }
/*     */         else
/*     */         {
/* 269 */           i = MathHelper.func_76128_c(this.field_70165_t) + this.field_70146_Z.nextInt(8) - this.field_70146_Z.nextInt(8);
/* 270 */           k = MathHelper.func_76128_c(this.field_70161_v) + this.field_70146_Z.nextInt(8) - this.field_70146_Z.nextInt(8);
/* 271 */           tries--;
/*     */         }
/*     */       }
/*     */       
/* 275 */       if (flag1)
/*     */       {
/* 277 */         func_70107_b(i + 0.5D, j + 0.1D, k + 0.5D);
/*     */         
/* 279 */         if (this.field_70170_p.func_72945_a(this, func_174813_aQ()).isEmpty())
/*     */         {
/* 281 */           flag = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 286 */     if (!flag)
/*     */     {
/* 288 */       func_70107_b(d3, d4, d5);
/* 289 */       return;
/*     */     }
/*     */     
/*     */ 
/* 293 */     short short1 = 128;
/*     */     
/* 295 */     for (int l = 0; l < short1; l++)
/*     */     {
/* 297 */       double d6 = l / (short1 - 1.0D);
/* 298 */       float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 299 */       float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 300 */       float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 301 */       double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 302 */       double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * this.field_70131_O;
/* 303 */       double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 304 */       this.field_70170_p.func_175688_a(net.minecraft.util.EnumParticleTypes.PORTAL, d7, d8, d9, f, f1, f2, new int[0]);
/*     */     }
/*     */     
/* 307 */     this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 308 */     func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180431_b(DamageSource ds)
/*     */   {
/* 317 */     return (this.fieldFrenzyCounter > 0) || (super.func_180431_b(ds));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 323 */     if ((func_180431_b(source)) || (source == DamageSource.field_76369_e) || (source == DamageSource.field_82727_n))
/*     */     {
/* 325 */       return false;
/*     */     }
/*     */     
/* 328 */     boolean aef = super.func_70097_a(source, damage);
/*     */     
/* 330 */     if ((!this.field_70170_p.field_72995_K) && (aef) && (!this.fieldFrenzy) && (func_110139_bj() <= 0.0F)) {
/* 331 */       this.fieldFrenzy = true;
/* 332 */       this.fieldFrenzyCounter = 150;
/*     */     }
/*     */     
/* 335 */     return aef;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
/*     */   {
/* 342 */     this.spawnTimer = 150;
/* 343 */     setTitle(this.field_70146_Z.nextInt(this.titles.length));
/* 344 */     func_110149_m((float)(func_110139_bj() + func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * 0.66D));
/* 345 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 351 */     return 3.1F;
/*     */   }
/*     */   
/* 354 */   boolean lastBlast = false;
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/* 358 */     if (this.field_70146_Z.nextFloat() > 0.2F) {
/* 359 */       EntityEldritchOrb blast = new EntityEldritchOrb(this.field_70170_p, this);
/* 360 */       this.lastBlast = (!this.lastBlast);
/*     */       
/* 362 */       this.field_70170_p.func_72960_a(this, (byte)(this.lastBlast ? 16 : 15));
/*     */       
/* 364 */       int rr = this.lastBlast ? 90 : 180;
/* 365 */       double xx = MathHelper.func_76134_b((this.field_70177_z + rr) % 360.0F / 180.0F * 3.1415927F) * 0.5F;
/* 366 */       double yy = 0.13D;
/* 367 */       double zz = MathHelper.func_76126_a((this.field_70177_z + rr) % 360.0F / 180.0F * 3.1415927F) * 0.5F;
/* 368 */       blast.func_70107_b(blast.field_70165_t - xx, blast.field_70163_u - yy, blast.field_70161_v - zz);
/*     */       
/* 370 */       double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
/* 371 */       double d1 = entitylivingbase.field_70163_u - this.field_70163_u - entitylivingbase.field_70131_O / 2.0F;
/* 372 */       double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
/*     */       
/* 374 */       blast.func_70186_c(d0, d1, d2, 1.0F, 2.0F);
/*     */       
/* 376 */       func_85030_a("thaumcraft:egattack", 2.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 377 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/* 379 */     else if (func_70685_l(entitylivingbase)) {
/* 380 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXSonic(func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.func_177502_q(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */       
/*     */ 
/* 383 */       entitylivingbase.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F);
/*     */       
/*     */       try
/*     */       {
/* 387 */         entitylivingbase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 400, 0));
/* 388 */         entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 400, 0));
/*     */       }
/*     */       catch (Exception e) {}
/* 391 */       if ((entitylivingbase instanceof EntityPlayer)) {
/* 392 */         ResearchHelper.addWarpToPlayer((EntityPlayer)entitylivingbase, 3 + this.field_70170_p.field_73012_v.nextInt(3), EnumWarpType.TEMPORARY);
/*     */       }
/*     */       
/* 395 */       func_85030_a("thaumcraft:egscreech", 4.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 400 */   public float armLiftL = 0.0F;
/* 401 */   public float armLiftR = 0.0F;
/*     */   
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte p_70103_1_)
/*     */   {
/* 407 */     if (p_70103_1_ == 15)
/*     */     {
/* 409 */       this.armLiftL = 0.5F;
/*     */ 
/*     */     }
/* 412 */     else if (p_70103_1_ == 16)
/*     */     {
/* 414 */       this.armLiftR = 0.5F;
/*     */ 
/*     */     }
/* 417 */     else if (p_70103_1_ == 17)
/*     */     {
/* 419 */       this.armLiftL = 0.9F;
/* 420 */       this.armLiftR = 0.9F;
/*     */ 
/*     */     }
/* 423 */     else if (p_70103_1_ == 18)
/*     */     {
/* 425 */       this.spawnTimer = 150;
/*     */     }
/*     */     else
/*     */     {
/* 429 */       super.func_70103_a(p_70103_1_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/* 437 */     if (clazz == EntityEldritchGuardian.class)
/* 438 */       return false;
/* 439 */     return super.func_70686_a(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 448 */     return "thaumcraft:egidle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 458 */     return "thaumcraft:egdeath";
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 464 */     return 500;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/boss/EntityEldritchWarden.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */