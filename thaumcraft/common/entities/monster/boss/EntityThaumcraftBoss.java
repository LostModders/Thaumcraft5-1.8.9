/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ 
/*     */ public class EntityThaumcraftBoss extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData
/*     */ {
/*     */   public EntityThaumcraftBoss(World world)
/*     */   {
/*  32 */     super(world);
/*  33 */     this.field_70728_aV = 50;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/*  39 */     super.func_70037_a(nbt);
/*  40 */     if (nbt.func_74764_b("HomeD")) {
/*  41 */       func_175449_a(new BlockPos(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ")), nbt.func_74762_e("HomeD"));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  48 */     super.func_70014_b(nbt);
/*  49 */     if ((func_180486_cf() != null) && (func_110174_bM() > 0.0F)) {
/*  50 */       nbt.func_74768_a("HomeD", (int)func_110174_bM());
/*  51 */       nbt.func_74768_a("HomeX", func_180486_cf().func_177958_n());
/*  52 */       nbt.func_74768_a("HomeY", func_180486_cf().func_177956_o());
/*  53 */       nbt.func_74768_a("HomeZ", func_180486_cf().func_177952_p());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  60 */     super.func_110147_ax();
/*  61 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.95D);
/*  62 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  68 */     super.func_70088_a();
/*  69 */     func_70096_w().func_75682_a(14, new Short((short)0));
/*     */   }
/*     */   
/*  72 */   HashMap<Integer, Integer> aggro = new HashMap();
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/*  76 */     if (getSpawnTimer() == 0) {
/*  77 */       super.func_70619_bc();
/*     */     }
/*  79 */     if ((func_70638_az() != null) && (func_70638_az().field_70128_L)) {
/*  80 */       func_70624_b(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) {
/*  85 */     func_175449_a(func_180425_c(), 24);
/*  86 */     generateName();
/*  87 */     return data;
/*     */   }
/*     */   
/*     */   public int getAnger()
/*     */   {
/*  92 */     return this.field_70180_af.func_75693_b(14);
/*     */   }
/*     */   
/*     */   public void setAnger(int par1)
/*     */   {
/*  97 */     this.field_70180_af.func_75692_b(14, Short.valueOf((short)par1));
/*     */   }
/*     */   
/* 100 */   int spawnTimer = 0;
/*     */   
/*     */   public int getSpawnTimer() {
/* 103 */     return this.spawnTimer;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 108 */     super.func_70071_h_();
/* 109 */     if (getSpawnTimer() > 0) { this.spawnTimer -= 1;
/*     */     }
/* 111 */     if (getAnger() > 0) setAnger(getAnger() - 1);
/* 112 */     if ((this.field_70170_p.field_72995_K) && (this.field_70146_Z.nextInt(15) == 0) && (getAnger() > 0)) {
/* 113 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 114 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 115 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 116 */       this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N - this.field_70130_N / 2.0D, func_174813_aQ().field_72338_b + this.field_70131_O + this.field_70146_Z.nextFloat() * 0.5D, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N - this.field_70130_N / 2.0D, d0, d1, d2, new int[0]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 123 */     if (!this.field_70170_p.field_72995_K) {
/* 124 */       if (this.field_70173_aa % 30 == 0)
/*     */       {
/* 126 */         func_70691_i(1.0F);
/*     */       }
/*     */       
/* 129 */       if ((func_70638_az() != null) && (this.field_70173_aa % 20 == 0)) {
/* 130 */         ArrayList<Integer> dl = new ArrayList();
/* 131 */         int players = 0;
/* 132 */         int hei = func_70638_az().func_145782_y();
/* 133 */         int ad = this.aggro.containsKey(Integer.valueOf(hei)) ? ((Integer)this.aggro.get(Integer.valueOf(hei))).intValue() : 0;
/* 134 */         int ld = ad;
/* 135 */         Entity newTarget = null;
/* 136 */         for (Integer ei : this.aggro.keySet()) {
/* 137 */           int ca = ((Integer)this.aggro.get(ei)).intValue();
/* 138 */           if ((ca > ad + 25) && (ca > ad * 1.1D) && (ca > ld)) {
/* 139 */             newTarget = this.field_70170_p.func_73045_a(hei);
/* 140 */             if ((newTarget == null) || (newTarget.field_70128_L) || (func_70068_e(newTarget) > 16384.0D)) {
/* 141 */               dl.add(ei);
/*     */             } else {
/* 143 */               hei = ei.intValue();
/* 144 */               ld = ei.intValue();
/* 145 */               if ((newTarget instanceof EntityPlayer)) {
/* 146 */                 players++;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         Integer ei;
/* 152 */         for (Iterator i$ = dl.iterator(); i$.hasNext(); this.aggro.remove(ei)) { ei = (Integer)i$.next();
/*     */         }
/* 154 */         if ((newTarget != null) && (hei != func_70638_az().func_145782_y())) {
/* 155 */           func_70624_b((EntityLivingBase)newTarget);
/*     */         }
/*     */         
/* 158 */         float om = func_110138_aP();
/*     */         
/* 160 */         IAttributeInstance iattributeinstance = func_110148_a(SharedMonsterAttributes.field_111267_a);
/* 161 */         IAttributeInstance iattributeinstance2 = func_110148_a(SharedMonsterAttributes.field_111264_e);
/*     */         
/* 163 */         for (int a = 0; a < 5; a++) {
/* 164 */           iattributeinstance2.func_111124_b(thaumcraft.common.lib.utils.EntityUtils.DMGBUFF[a]);
/* 165 */           iattributeinstance.func_111124_b(thaumcraft.common.lib.utils.EntityUtils.HPBUFF[a]);
/*     */         }
/*     */         
/* 168 */         for (int a = 0; a < Math.min(5, players - 1); a++) {
/* 169 */           iattributeinstance.func_111121_a(thaumcraft.common.lib.utils.EntityUtils.HPBUFF[a]);
/* 170 */           iattributeinstance2.func_111121_a(thaumcraft.common.lib.utils.EntityUtils.DMGBUFF[a]);
/*     */         }
/*     */         
/* 173 */         double mm = func_110138_aP() / om;
/* 174 */         func_70606_j((float)(func_110143_aJ() * mm));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180431_b(DamageSource ds)
/*     */   {
/* 183 */     return (super.func_180431_b(ds)) || (getSpawnTimer() > 0);
/*     */   }
/*     */   
/*     */   public boolean func_70648_aU()
/*     */   {
/* 188 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/* 193 */     return (super.func_70104_M()) && (!func_180431_b(DamageSource.field_76366_f));
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_70682_h(int air)
/*     */   {
/* 199 */     return air;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */   public boolean func_98052_bS()
/*     */   {
/* 207 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_180483_b(DifficultyInstance diff) {}
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 217 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 222 */     return el instanceof thaumcraft.api.entities.IEldritchMob;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int fortune)
/*     */   {
/* 229 */     thaumcraft.common.lib.utils.EntityUtils.entityDropSpecialItem(this, new ItemStack(ItemsTC.primordialPearl, 1 + this.field_70146_Z.nextInt(2)), this.field_70131_O / 2.0F);
/* 230 */     func_70099_a(new ItemStack(ItemsTC.lootBag, 1, 2), 1.5F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 237 */     if (!this.field_70170_p.field_72995_K) {
/* 238 */       if ((source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase))) {
/* 239 */         int target = source.func_76346_g().func_145782_y();
/* 240 */         int ad = (int)damage;
/* 241 */         if (this.aggro.containsKey(Integer.valueOf(target))) {
/* 242 */           ad += ((Integer)this.aggro.get(Integer.valueOf(target))).intValue();
/*     */         }
/* 244 */         this.aggro.put(Integer.valueOf(target), Integer.valueOf(ad));
/*     */       }
/*     */       
/* 247 */       if (damage > 35.0F) {
/* 248 */         if (getAnger() == 0) {
/*     */           try {
/* 250 */             func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 200, (int)(damage / 15.0F)));
/* 251 */             func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 200, (int)(damage / 10.0F)));
/* 252 */             func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 200, (int)(damage / 40.0F)));
/* 253 */             setAnger(200);
/*     */           } catch (Exception e) {}
/* 255 */           if ((source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
/* 256 */             ((EntityPlayer)source.func_76346_g()).func_145747_a(new ChatComponentText(func_70005_c_() + " " + net.minecraft.util.StatCollector.func_74838_a("tc.boss.enrage")));
/*     */           }
/*     */         }
/* 259 */         damage = 35.0F;
/*     */       }
/*     */     }
/*     */     
/* 263 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */   public void generateName() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/boss/EntityThaumcraftBoss.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */