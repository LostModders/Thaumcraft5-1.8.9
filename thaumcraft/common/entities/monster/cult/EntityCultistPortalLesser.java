/*     */ package thaumcraft.common.entities.monster.cult;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityCultistPortalLesser extends EntityMob
/*     */ {
/*     */   public EntityCultistPortalLesser(World par1World)
/*     */   {
/*  26 */     super(par1World);
/*  27 */     this.field_70178_ae = true;
/*  28 */     this.field_70728_aV = 10;
/*  29 */     func_70105_a(1.5F, 3.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  35 */     return 4;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  41 */     super.func_70088_a();
/*  42 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public boolean isActive()
/*     */   {
/*  47 */     return (this.field_70180_af.func_75683_a(16) & 0x4) != 0;
/*     */   }
/*     */   
/*     */   public void setActive(boolean tamed)
/*     */   {
/*  52 */     byte b0 = this.field_70180_af.func_75683_a(16);
/*     */     
/*  54 */     if (tamed)
/*     */     {
/*  56 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x4)));
/*     */     }
/*     */     else
/*     */     {
/*  60 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFB)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  68 */     super.func_110147_ax();
/*  69 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
/*  70 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*  71 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  76 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  85 */     super.func_70014_b(nbt);
/*  86 */     nbt.func_74774_a("flags", this.field_70180_af.func_75683_a(16));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/*  95 */     super.func_70037_a(nbt);
/*  96 */     this.field_70180_af.func_75692_b(16, Byte.valueOf(nbt.func_74771_c("flags")));
/*     */   }
/*     */   
/*     */   public boolean func_70067_L()
/*     */   {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70091_d(double par1, double par3, double par5) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70636_d() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70112_a(double par1)
/*     */   {
/* 123 */     return par1 < 4096.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/* 131 */     return 15728880;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 140 */     return 1.0F;
/*     */   }
/*     */   
/* 143 */   int stagecounter = 100;
/* 144 */   public int activeCounter = 0;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 148 */     super.func_70071_h_();
/* 149 */     if (isActive()) {
/* 150 */       this.activeCounter += 1;
/*     */     }
/* 152 */     if (!this.field_70170_p.field_72995_K) {
/* 153 */       if (!isActive()) {
/* 154 */         if (this.field_70173_aa % 10 == 0) {
/* 155 */           EntityPlayer p = this.field_70170_p.func_72890_a(this, 32.0D);
/* 156 */           if (p != null) {
/* 157 */             setActive(true);
/* 158 */             func_85030_a("thaumcraft:craftstart", 1.0F, 1.0F);
/*     */           }
/*     */         }
/*     */       }
/* 162 */       else if (this.stagecounter-- <= 0) {
/* 163 */         EntityPlayer p = this.field_70170_p.func_72890_a(this, 32.0D);
/* 164 */         if ((p != null) && (func_70685_l(p))) {
/* 165 */           int count = this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL ? 4 : this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD ? 6 : 2;
/*     */           try {
/* 167 */             List l = this.field_70170_p.func_72872_a(EntityCultist.class, func_174813_aQ().func_72314_b(32.0D, 32.0D, 32.0D));
/* 168 */             if (l != null) count -= l.size();
/*     */           } catch (Exception e) {}
/* 170 */           if (count > 0) {
/* 171 */             this.field_70170_p.func_72960_a(this, (byte)16);
/* 172 */             spawnMinions();
/*     */           }
/*     */         }
/* 175 */         this.stagecounter = (50 + this.field_70146_Z.nextInt(50));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 180 */     if (this.pulse > 0) this.pulse -= 1;
/*     */   }
/*     */   
/*     */   int getTiming() {
/* 184 */     List<net.minecraft.entity.Entity> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityCultist.class, 32.0D);
/* 185 */     return l.size() * 20;
/*     */   }
/*     */   
/*     */   void spawnMinions()
/*     */   {
/* 190 */     EntityCultist cultist = null;
/* 191 */     if (this.field_70146_Z.nextFloat() > 0.33D) {
/* 192 */       cultist = new EntityCultistKnight(this.field_70170_p);
/*     */     } else {
/* 194 */       cultist = new EntityCultistCleric(this.field_70170_p);
/*     */     }
/* 196 */     cultist.func_70107_b(this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25D, this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat());
/*     */     
/*     */ 
/*     */ 
/* 200 */     cultist.func_180482_a(this.field_70170_p.func_175649_E(new net.minecraft.util.BlockPos(cultist.func_180425_c())), (IEntityLivingData)null);
/* 201 */     this.field_70170_p.func_72838_d(cultist);
/* 202 */     cultist.func_70656_aK();
/* 203 */     cultist.func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/* 204 */     func_70097_a(DamageSource.field_76380_i, 5 + this.field_70146_Z.nextInt(5));
/*     */   }
/*     */   
/*     */   protected boolean func_70814_o()
/*     */   {
/* 209 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer p)
/*     */   {
/* 214 */     if ((func_70068_e(p) < 3.0D) && (p.func_70097_a(DamageSource.func_76354_b(this, this), 4.0F)))
/*     */     {
/* 216 */       func_85030_a("thaumcraft:zap", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F + 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 223 */     return 0.75F;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 228 */     return 540;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 233 */     return "thaumcraft:monolith";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 239 */     return "thaumcraft:zap";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 244 */     return "thaumcraft:shock";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 250 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 258 */   public int pulse = 0;
/*     */   
/*     */   protected void func_70628_a(boolean flag, int fortune) {}
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte msg) {
/* 264 */     if (msg == 16)
/*     */     {
/* 266 */       this.pulse = 10;
/*     */     }
/*     */     else
/*     */     {
/* 270 */       super.func_70103_a(msg);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70690_d(PotionEffect p_70690_1_) {}
/*     */   
/*     */ 
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource p_70645_1_)
/*     */   {
/* 283 */     if (!this.field_70170_p.field_72995_K) {
/* 284 */       this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5F, false, false);
/*     */     }
/*     */     
/* 287 */     super.func_70645_a(p_70645_1_);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/cult/EntityCultistPortalLesser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */