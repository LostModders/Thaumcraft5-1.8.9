/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntitySlime;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.ReflectionHelper;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class EntityThaumicSlime
/*     */   extends EntitySlime implements ITaintedMob
/*     */ {
/*     */   public EntityThaumicSlime(World par1World)
/*     */   {
/*  31 */     super(par1World);
/*  32 */     int i = 1 << 1 + this.field_70146_Z.nextInt(3);
/*  33 */     func_70799_a(i);
/*     */   }
/*     */   
/*  36 */   int launched = 10;
/*     */   
/*     */   public EntityThaumicSlime(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
/*     */   {
/*  40 */     super(par1World);
/*  41 */     func_70799_a(1);
/*  42 */     this.field_70163_u = ((par2EntityLiving.func_174813_aQ().field_72338_b + par2EntityLiving.func_174813_aQ().field_72337_e) / 2.0D);
/*  43 */     double var6 = par3EntityLiving.field_70165_t - par2EntityLiving.field_70165_t;
/*  44 */     double var8 = par3EntityLiving.func_174813_aQ().field_72338_b + par3EntityLiving.field_70131_O / 3.0F - this.field_70163_u;
/*  45 */     double var10 = par3EntityLiving.field_70161_v - par2EntityLiving.field_70161_v;
/*  46 */     double var12 = MathHelper.func_76133_a(var6 * var6 + var10 * var10);
/*     */     
/*  48 */     if (var12 >= 1.0E-7D)
/*     */     {
/*  50 */       float var14 = (float)(Math.atan2(var10, var6) * 180.0D / 3.141592653589793D) - 90.0F;
/*  51 */       float var15 = (float)-(Math.atan2(var8, var12) * 180.0D / 3.141592653589793D);
/*  52 */       double var16 = var6 / var12;
/*  53 */       double var18 = var10 / var12;
/*  54 */       func_70012_b(par2EntityLiving.field_70165_t + var16, this.field_70163_u, par2EntityLiving.field_70161_v + var18, var14, var15);
/*  55 */       float var20 = (float)var12 * 0.2F;
/*  56 */       setThrowableHeading(var6, var8 + var20, var10, 1.5F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
/*     */   {
/*  62 */     float var9 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*  63 */     par1 /= var9;
/*  64 */     par3 /= var9;
/*  65 */     par5 /= var9;
/*  66 */     par1 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/*  67 */     par3 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/*  68 */     par5 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/*  69 */     par1 *= par7;
/*  70 */     par3 *= par7;
/*  71 */     par5 *= par7;
/*  72 */     this.field_70159_w = par1;
/*  73 */     this.field_70181_x = par3;
/*  74 */     this.field_70179_y = par5;
/*  75 */     float var10 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
/*  76 */     this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D));
/*  77 */     this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(par3, var10) * 180.0D / 3.141592653589793D));
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
/*     */   {
/*  83 */     int i = this.field_70146_Z.nextInt(3);
/*     */     
/*  85 */     if ((i < 2) && (this.field_70146_Z.nextFloat() < 0.5F * p_180482_1_.func_180170_c()))
/*     */     {
/*  87 */       i++;
/*     */     }
/*     */     
/*  90 */     int j = 1 << i;
/*  91 */     func_70799_a(j);
/*  92 */     return super.func_180482_a(p_180482_1_, p_180482_2_);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70799_a(int par1)
/*     */   {
/*  98 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)par1));
/*  99 */     func_70105_a(0.51000005F * par1, 0.51000005F * par1);
/* 100 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 101 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(par1 * par1);
/* 102 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2F + 0.1F * par1);
/* 103 */     func_70606_j(func_110138_aP());
/* 104 */     this.field_70728_aV = (par1 + 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 111 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 117 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 127 */     boolean wasOnGround = ((Boolean)ReflectionHelper.getPrivateValue(EntitySlime.class, this, new String[] { "wasOnGround", "field_175452_bi", "bk" })).booleanValue();
/*     */     
/* 129 */     int i = func_70809_q();
/*     */     
/* 131 */     if ((this.field_70122_E) && (!wasOnGround)) {
/* 132 */       Utils.setPrivateFinalValue(EntitySlime.class, this, Boolean.valueOf(true), new String[] { "wasOnGround", "field_175452_bi", "bk" });
/* 133 */       float sa = this.field_70813_a;
/* 134 */       super.func_70071_h_();
/* 135 */       this.field_70813_a = sa;
/*     */       
/* 137 */       if (this.field_70170_p.field_72995_K) {
/* 138 */         for (int j = 0; j < i * 2; j++)
/*     */         {
/* 140 */           Thaumcraft.proxy.getFX().slimeJumpFX(this, i);
/*     */         }
/*     */       }
/* 143 */       if (func_70804_p())
/*     */       {
/* 145 */         func_85030_a(func_70803_o(), func_70599_aP(), ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) / 0.8F);
/*     */       }
/*     */       
/* 148 */       this.field_70813_a = -0.5F;
/*     */       
/* 150 */       Utils.setPrivateFinalValue(EntitySlime.class, this, Boolean.valueOf(this.field_70122_E), new String[] { "wasOnGround", "field_175452_bi", "bk" });
/*     */       
/* 152 */       func_70808_l();
/*     */     }
/*     */     else {
/* 155 */       super.func_70071_h_();
/*     */     }
/*     */     
/* 158 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 160 */       if (this.launched > 0) {
/* 161 */         this.launched -= 1;
/* 162 */         for (int j = 0; j < i * (this.launched + 1); j++)
/*     */         {
/* 164 */           Thaumcraft.proxy.getFX().slimeJumpFX(this, i);
/*     */         }
/*     */       }
/*     */       
/* 168 */       float ff = func_70809_q();
/* 169 */       func_70105_a(0.6F * ff, 0.6F * ff);
/* 170 */       func_70105_a(0.51000005F * ff, 0.51000005F * ff);
/* 171 */     } else if (!this.field_70128_L)
/*     */     {
/* 173 */       EntityPlayer entityplayer = this.field_70170_p.func_72890_a(this, 16.0D);
/*     */       
/* 175 */       if (entityplayer != null)
/*     */       {
/* 177 */         if (this.spitCounter > 0) this.spitCounter -= 1;
/* 178 */         func_70625_a(entityplayer, 10.0F, 20.0F);
/* 179 */         if ((func_70032_d(entityplayer) > 4.0F) && (this.spitCounter <= 0) && (func_70809_q() > 2)) {
/* 180 */           this.spitCounter = 101;
/*     */           
/* 182 */           if (!this.field_70170_p.field_72995_K) {
/* 183 */             EntityThaumicSlime flyslime = new EntityThaumicSlime(this.field_70170_p, this, entityplayer);
/* 184 */             this.field_70170_p.func_72838_d(flyslime);
/*     */           }
/* 186 */           this.field_70170_p.func_72956_a(this, "thaumcraft:gore", 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/* 187 */           func_70799_a(func_70809_q() - 1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 193 */   int spitCounter = 100;
/*     */   
/*     */ 
/*     */ 
/*     */   protected EntityThaumicSlime createInstance()
/*     */   {
/* 199 */     return new EntityThaumicSlime(this.field_70170_p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70106_y()
/*     */   {
/* 208 */     int i = func_70809_q();
/*     */     
/* 210 */     if ((!this.field_70170_p.field_72995_K) && (i > 1) && (func_110143_aJ() <= 0.0F))
/*     */     {
/* 212 */       for (int k = 0; k < i; k++)
/*     */       {
/* 214 */         float f = (k % 2 - 0.5F) * i / 4.0F;
/* 215 */         float f1 = (k / 2 - 0.5F) * i / 4.0F;
/* 216 */         EntityThaumicSlime entityslime = createInstance();
/* 217 */         entityslime.func_70799_a(1);
/* 218 */         entityslime.func_70012_b(this.field_70165_t + f, this.field_70163_u + 0.5D, this.field_70161_v + f1, this.field_70146_Z.nextFloat() * 360.0F, 0.0F);
/* 219 */         this.field_70170_p.func_72838_d(entityslime);
/*     */       }
/*     */     }
/*     */     
/* 223 */     this.field_70128_L = true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 252 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int func_70805_n()
/*     */   {
/* 260 */     return func_70809_q() + 1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70800_m()
/*     */   {
/* 266 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_175451_e(EntityLivingBase p_175451_1_)
/*     */   {
/* 272 */     int i = func_70809_q();
/* 273 */     if (this.launched > 0) i += 2;
/* 274 */     if ((func_70685_l(p_175451_1_)) && (func_70068_e(p_175451_1_) < 0.6D * i * 0.6D * i) && (p_175451_1_.func_70097_a(DamageSource.func_76358_a(this), func_70805_n())))
/*     */     {
/* 276 */       func_85030_a("mob.attack", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
/* 277 */       func_174815_a(this, p_175451_1_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 284 */     return func_70809_q() > 1 ? ItemsTC.tainted : Item.func_150899_d(0);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityThaumicSlime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */