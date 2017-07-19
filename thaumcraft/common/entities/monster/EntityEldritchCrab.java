/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.monster.EntitySpider.GroupData;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ 
/*     */ public class EntityEldritchCrab extends EntityMob implements IEldritchMob
/*     */ {
/*     */   public EntityEldritchCrab(World par1World)
/*     */   {
/*  39 */     super(par1World);
/*  40 */     func_70105_a(0.8F, 0.6F);
/*  41 */     this.field_70728_aV = 6;
/*  42 */     ((PathNavigateGround)func_70661_as()).func_179688_b(true);
/*  43 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  44 */     this.field_70714_bg.func_75776_a(2, new EntityAILeapAtTarget(this, 0.63F));
/*  45 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/*  46 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  47 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  48 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true, new Class[0]));
/*  49 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, true));
/*  50 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityCultist.class, true));
/*     */   }
/*     */   
/*     */   public double func_70033_W()
/*     */   {
/*  55 */     return func_70115_ae() ? 0.5D : 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  61 */     super.func_110147_ax();
/*  62 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  63 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*  64 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(hasHelm() ? 0.275D : 0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  70 */     super.func_70088_a();
/*  71 */     this.field_70180_af.func_75682_a(22, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public boolean func_98052_bS()
/*     */   {
/*  76 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  82 */     return hasHelm() ? 5 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data)
/*     */   {
/*  88 */     if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) {
/*  89 */       setHelm(true);
/*     */     } else {
/*  91 */       setHelm(this.field_70146_Z.nextFloat() < 0.33F);
/*     */     }
/*     */     
/*  94 */     if (data == null)
/*     */     {
/*  96 */       data = new EntitySpider.GroupData();
/*     */       
/*  98 */       if ((this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) && (this.field_70170_p.field_73012_v.nextFloat() < 0.1F * diff.func_180170_c()))
/*     */       {
/* 100 */         ((EntitySpider.GroupData)data).func_111104_a(this.field_70170_p.field_73012_v);
/*     */       }
/*     */     }
/*     */     
/* 104 */     if ((data instanceof EntitySpider.GroupData))
/*     */     {
/* 106 */       int i = ((EntitySpider.GroupData)data).field_111105_a;
/*     */       
/* 108 */       if ((i > 0) && (Potion.field_76425_a[i] != null))
/*     */       {
/* 110 */         func_70690_d(new PotionEffect(i, Integer.MAX_VALUE));
/*     */       }
/*     */     }
/*     */     
/* 114 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean hasHelm()
/*     */   {
/* 120 */     return (this.field_70180_af.func_75683_a(22) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setHelm(boolean par1)
/*     */   {
/* 125 */     byte var2 = this.field_70180_af.func_75683_a(22);
/*     */     
/* 127 */     if (par1)
/*     */     {
/* 129 */       this.field_70180_af.func_75692_b(22, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else
/*     */     {
/* 133 */       this.field_70180_af.func_75692_b(22, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/* 137 */   private int attackTime = 0;
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 142 */     super.func_70071_h_();
/*     */     
/* 144 */     this.attackTime -= 1;
/*     */     
/* 146 */     if (this.field_70173_aa < 20) { this.field_70143_R = 0.0F;
/*     */     }
/* 148 */     if ((this.field_70154_o == null) && (func_70643_av() != null) && (func_70643_av().field_70153_n == null) && (!this.field_70122_E) && (!hasHelm()) && (!func_70643_av().field_70128_L) && (this.field_70163_u - func_70643_av().field_70163_u >= func_70643_av().field_70131_O / 2.0F) && (func_70068_e(func_70643_av()) < 4.0D))
/*     */     {
/*     */ 
/*     */ 
/* 152 */       func_70078_a(func_70643_av());
/*     */     }
/*     */     
/* 155 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70154_o != null) && 
/* 156 */       (this.attackTime <= 0)) {
/* 157 */       this.attackTime = (10 + this.field_70146_Z.nextInt(10));
/* 158 */       func_70652_k(this.field_70154_o);
/* 159 */       if ((this.field_70154_o != null) && (this.field_70146_Z.nextFloat() < 0.2D)) { func_110145_l(this.field_70154_o);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 167 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/* 173 */     super.func_70628_a(p_70628_1_, p_70628_2_);
/*     */     
/* 175 */     if ((p_70628_1_) && ((this.field_70146_Z.nextInt(3) == 0) || (this.field_70146_Z.nextInt(1 + p_70628_2_) > 0)))
/*     */     {
/* 177 */       func_145779_a(Items.field_151079_bi, 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 183 */     if (super.func_70652_k(p_70652_1_)) {
/* 184 */       func_85030_a("thaumcraft:crabclaw", 1.0F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/* 185 */       return true; }
/* 186 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 192 */     boolean b = super.func_70097_a(source, damage);
/*     */     
/* 194 */     if ((hasHelm()) && (func_110143_aJ() / func_110138_aP() <= 0.5F)) {
/* 195 */       setHelm(false);
/* 196 */       func_70669_a(new ItemStack(ItemsTC.crimsonPlateChest));
/* 197 */       func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */     }
/*     */     
/* 200 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 207 */     super.func_70037_a(par1NBTTagCompound);
/* 208 */     this.field_70180_af.func_75692_b(22, Byte.valueOf(par1NBTTagCompound.func_74771_c("Flags")));
/* 209 */     if (!hasHelm()) {
/* 210 */       func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 220 */     super.func_70014_b(par1NBTTagCompound);
/* 221 */     par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(22));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 227 */     return 160;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 233 */     return "thaumcraft:crabtalk";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 239 */     return "game.hostile.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 245 */     return "thaumcraft:crabdeath";
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_)
/*     */   {
/* 251 */     func_85030_a("mob.spider.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumCreatureAttribute func_70668_bt()
/*     */   {
/* 257 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70687_e(PotionEffect p_70687_1_)
/*     */   {
/* 263 */     return p_70687_1_.func_76456_a() == Potion.field_76436_u.field_76415_H ? false : super.func_70687_e(p_70687_1_);
/*     */   }
/*     */   
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 268 */     return el instanceof EntityEldritchCrab;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityEldritchCrab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */