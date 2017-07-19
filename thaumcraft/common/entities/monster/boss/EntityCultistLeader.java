/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityCultistLeader extends EntityThaumcraftBoss implements IRangedAttackMob
/*     */ {
/*     */   public EntityCultistLeader(World p_i1745_1_)
/*     */   {
/*  47 */     super(p_i1745_1_);
/*  48 */     func_70105_a(0.75F, 2.25F);
/*  49 */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*  50 */     this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 16.0D, 1.0D, 30, 40, 24.0F));
/*  51 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.1D, false));
/*  52 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  53 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  54 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  55 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  56 */     this.field_70715_bh.func_75776_a(1, new thaumcraft.common.entities.ai.combat.AICultistHurtByTarget(this, true));
/*  57 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  58 */     this.field_70728_aV = 40;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  64 */     super.func_110147_ax();
/*  65 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32D);
/*  66 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(150.0D);
/*  67 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  74 */     super.func_70088_a();
/*  75 */     func_70096_w().func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void generateName() {
/*  79 */     int t = (int)func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
/*  80 */     if (t >= 0) {
/*  81 */       func_96094_a(String.format(net.minecraft.util.StatCollector.func_74838_a("entity.Thaumcraft.CultistLeader.name.custom"), new Object[] { getTitle(), ChampionModifier.mods[t].getModNameLocalized() }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private String getTitle()
/*     */   {
/*  89 */     return this.titles[func_70096_w().func_75683_a(16)];
/*     */   }
/*     */   
/*     */   private void setTitle(int title) {
/*  93 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)title));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  99 */     super.func_70014_b(nbt);
/* 100 */     nbt.func_74774_a("title", func_70096_w().func_75683_a(16));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 106 */     super.func_70037_a(nbt);
/* 107 */     setTitle(nbt.func_74771_c("title"));
/*     */   }
/*     */   
/* 110 */   String[] titles = { "Alberic", "Anselm", "Bastian", "Beturian", "Chabier", "Chorache", "Chuse", "Dodorol", "Ebardo", "Ferrando", "Fertus", "Guillen", "Larpe", "Obano", "Zelipe" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_82164_bB()
/*     */   {
/* 118 */     func_70062_b(4, new ItemStack(ItemsTC.crimsonPraetorHelm));
/* 119 */     func_70062_b(3, new ItemStack(ItemsTC.crimsonPraetorChest));
/* 120 */     func_70062_b(2, new ItemStack(ItemsTC.crimsonPraetorLegs));
/* 121 */     func_70062_b(1, new ItemStack(ItemsTC.crimsonBoots));
/* 122 */     if (this.field_70170_p.func_175659_aa() == EnumDifficulty.EASY) {
/* 123 */       func_70062_b(0, new ItemStack(ItemsTC.voidSword));
/*     */     } else {
/* 125 */       func_70062_b(0, new ItemStack(ItemsTC.crimsonBlade));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_180483_b(DifficultyInstance diff)
/*     */   {
/* 131 */     float f = diff.func_180170_c();
/* 132 */     if ((func_70694_bm() != null) && (this.field_70146_Z.nextFloat() < 0.5F * f))
/*     */     {
/* 134 */       EnchantmentHelper.func_77504_a(this.field_70146_Z, func_70694_bm(), (int)(7.0F + f * this.field_70146_Z.nextInt(22)));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 140 */     return ((el instanceof EntityCultist)) || ((el instanceof EntityCultistLeader));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/* 146 */     if ((clazz == EntityCultistCleric.class) || (clazz == EntityCultistLeader.class) || (clazz == EntityCultistKnight.class))
/*     */     {
/*     */ 
/* 149 */       return false; }
/* 150 */     return super.func_70686_a(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 156 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 161 */     func_70099_a(new ItemStack(ItemsTC.lootBag, 1, 2), 1.5F);
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
/*     */   {
/* 167 */     func_82164_bB();
/* 168 */     func_180483_b(diff);
/* 169 */     setTitle(this.field_70146_Z.nextInt(this.titles.length));
/* 170 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/* 175 */     super.func_70619_bc();
/* 176 */     List<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityCultist.class, 8.0D);
/* 177 */     for (Entity e : list) {
/*     */       try {
/* 179 */         if (((e instanceof EntityCultist)) && (!((EntityCultist)e).func_82165_m(Potion.field_76428_l.field_76415_H))) {
/* 180 */           ((EntityCultist)e).func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 60, 1));
/*     */         }
/*     */       }
/*     */       catch (Exception e1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
/* 188 */     if (func_70685_l(entitylivingbase)) {
/* 189 */       func_71038_i();
/* 190 */       func_70671_ap().func_75650_a(entitylivingbase.field_70165_t, entitylivingbase.func_174813_aQ().field_72338_b + entitylivingbase.field_70131_O / 2.0F, entitylivingbase.field_70161_v, 30.0F, 30.0F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 195 */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, true);
/* 196 */       blast.field_70165_t += blast.field_70159_w / 2.0D;
/* 197 */       blast.field_70161_v += blast.field_70179_y / 2.0D;
/* 198 */       blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
/*     */       
/* 200 */       double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
/* 201 */       double d1 = entitylivingbase.func_174813_aQ().field_72338_b + entitylivingbase.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/* 202 */       double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
/*     */       
/* 204 */       blast.func_70186_c(d0, d1 + 2.0D, d2, 0.66F, 3.0F);
/*     */       
/* 206 */       func_85030_a("thaumcraft:egattack", 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 207 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70656_aK()
/*     */   {
/* 214 */     if (this.field_70170_p.field_72995_K) {
/* 215 */       for (int i = 0; i < 20; i++)
/*     */       {
/* 217 */         double d0 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 218 */         double d1 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 219 */         double d2 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 220 */         double d3 = 2.0D;
/*     */         
/* 222 */         Thaumcraft.proxy.getFX().cultistSpawn(this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N + d0 * d3, this.field_70163_u + this.field_70146_Z.nextFloat() * this.field_70131_O + d1 * d3, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N + d2 * d3, d0, d1, d2);
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 228 */       this.field_70170_p.func_72960_a(this, (byte)20);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/boss/EntityCultistLeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */