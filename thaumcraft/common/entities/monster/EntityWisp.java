/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityFlying;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXWispZap;
/*     */ 
/*     */ public class EntityWisp extends EntityFlying implements IMob
/*     */ {
/*     */   public int courseChangeCooldown;
/*     */   public double waypointX;
/*     */   public double waypointY;
/*     */   public double waypointZ;
/*     */   private int aggroCooldown;
/*     */   public int prevAttackCounter;
/*     */   public int attackCounter;
/*     */   private BlockPos currentFlightTarget;
/*     */   
/*     */   public EntityWisp(World world)
/*     */   {
/*  47 */     super(world);
/*  48 */     this.courseChangeCooldown = 0;
/*  49 */     this.aggroCooldown = 0;
/*  50 */     this.prevAttackCounter = 0;
/*  51 */     this.attackCounter = 0;
/*  52 */     func_70105_a(0.9F, 0.9F);
/*  53 */     this.field_70728_aV = 5;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  59 */     super.func_110147_ax();
/*  60 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(22.0D);
/*  61 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/*  62 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/*     */   }
/*     */   
/*     */   protected boolean func_70041_e_()
/*     */   {
/*  67 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70682_h(int par1)
/*     */   {
/*  73 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource damagesource, float i)
/*     */   {
/*  79 */     if ((damagesource.func_76364_f() instanceof EntityLivingBase)) {
/*  80 */       func_70624_b((EntityLivingBase)damagesource.func_76364_f());
/*  81 */       this.aggroCooldown = 200;
/*     */     }
/*  83 */     if ((damagesource.func_76346_g() instanceof EntityLivingBase)) {
/*  84 */       func_70624_b((EntityLivingBase)damagesource.func_76346_g());
/*  85 */       this.aggroCooldown = 200;
/*     */     }
/*  87 */     return super.func_70097_a(damagesource, i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  94 */     super.func_70088_a();
/*  95 */     this.field_70180_af.func_75682_a(22, String.valueOf(""));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 101 */     super.func_70645_a(par1DamageSource);
/* 102 */     if (this.field_70170_p.field_72995_K) {
/* 103 */       Thaumcraft.proxy.getFX().burst(this.field_70165_t, this.field_70163_u + 0.44999998807907104D, this.field_70161_v, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 110 */     super.func_70071_h_();
/* 111 */     if ((this.field_70170_p.field_72995_K) && 
/* 112 */       (this.field_70173_aa <= 1)) {
/* 113 */       Thaumcraft.proxy.getFX().burst(this.field_70165_t, this.field_70163_u + 0.44999998807907104D, this.field_70161_v, 1.0F);
/*     */     }
/*     */     
/* 116 */     if ((this.field_70170_p.field_72995_K) && (this.field_70170_p.field_73012_v.nextBoolean()) && (Aspect.getAspect(getType()) != null)) {
/* 117 */       Color color = new Color(Aspect.getAspect(getType()).getColor());
/* 118 */       Thaumcraft.proxy.getFX().wispFX(this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F, this.field_70163_u + 0.44999998807907104D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F, this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F, 0.1F, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 126 */     this.field_70181_x *= 0.6000000238418579D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getType()
/*     */   {
/* 133 */     return this.field_70180_af.func_75681_e(22);
/*     */   }
/*     */   
/*     */   public void setType(String t) {
/* 137 */     this.field_70180_af.func_75692_b(22, String.valueOf(t));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 144 */     super.func_70636_d();
/*     */     
/* 146 */     if (func_70613_aW())
/*     */     {
/* 148 */       if ((!this.field_70170_p.field_72995_K) && (Aspect.getAspect(getType()) == null)) {
/* 149 */         if (this.field_70170_p.field_73012_v.nextInt(10) != 0) {
/* 150 */           ArrayList<Aspect> as = Aspect.getPrimalAspects();
/* 151 */           setType(((Aspect)as.get(this.field_70170_p.field_73012_v.nextInt(as.size()))).getTag());
/*     */         } else {
/* 153 */           ArrayList<Aspect> as = Aspect.getCompoundAspects();
/* 154 */           setType(((Aspect)as.get(this.field_70170_p.field_73012_v.nextInt(as.size()))).getTag());
/*     */         }
/*     */       }
/*     */       
/* 158 */       if ((!this.field_70170_p.field_72995_K) && (this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL))
/*     */       {
/* 160 */         func_70106_y();
/*     */       }
/*     */       
/* 163 */       this.prevAttackCounter = this.attackCounter;
/*     */       
/* 165 */       double attackrange = 16.0D;
/*     */       
/*     */ 
/* 168 */       if ((func_70638_az() == null) || (!func_70685_l(func_70638_az())))
/*     */       {
/* 170 */         if ((this.currentFlightTarget != null) && ((!this.field_70170_p.func_175623_d(this.currentFlightTarget)) || (this.currentFlightTarget.func_177956_o() < 1) || (this.currentFlightTarget.func_177956_o() > this.field_70170_p.func_175725_q(this.currentFlightTarget).func_177981_b(8).func_177956_o())))
/*     */         {
/*     */ 
/*     */ 
/* 174 */           this.currentFlightTarget = null;
/*     */         }
/*     */         
/* 177 */         if ((this.currentFlightTarget == null) || (this.field_70146_Z.nextInt(30) == 0) || (func_174831_c(this.currentFlightTarget) < 4.0D))
/*     */         {
/*     */ 
/* 180 */           this.currentFlightTarget = new BlockPos((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 186 */         double var1 = this.currentFlightTarget.func_177958_n() + 0.5D - this.field_70165_t;
/* 187 */         double var3 = this.currentFlightTarget.func_177956_o() + 0.1D - this.field_70163_u;
/* 188 */         double var5 = this.currentFlightTarget.func_177952_p() + 0.5D - this.field_70161_v;
/* 189 */         this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 190 */         this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 191 */         this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 192 */         float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 193 */         float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 194 */         this.field_70701_bs = 0.15F;
/* 195 */         this.field_70177_z += var8;
/*     */       }
/* 197 */       else if ((func_70068_e(func_70638_az()) > attackrange * attackrange / 2.0D) && (func_70685_l(func_70638_az())))
/*     */       {
/*     */ 
/* 200 */         double var1 = func_70638_az().field_70165_t - this.field_70165_t;
/* 201 */         double var3 = func_70638_az().field_70163_u + func_70638_az().func_70047_e() * 0.66F - this.field_70163_u;
/* 202 */         double var5 = func_70638_az().field_70161_v - this.field_70161_v;
/* 203 */         this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 204 */         this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 205 */         this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 206 */         float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 207 */         float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 208 */         this.field_70701_bs = 0.5F;
/* 209 */         this.field_70177_z += var8;
/*     */       }
/*     */       
/* 212 */       if (((func_70638_az() instanceof EntityPlayer)) && (((EntityPlayer)func_70638_az()).field_71075_bZ.field_75102_a))
/*     */       {
/* 214 */         func_70624_b(null);
/*     */       }
/*     */       
/* 217 */       if ((func_70638_az() != null) && (func_70638_az().field_70128_L))
/*     */       {
/* 219 */         func_70624_b(null);
/*     */       }
/*     */       
/* 222 */       this.aggroCooldown -= 1;
/*     */       
/*     */ 
/* 225 */       if ((this.field_70170_p.field_73012_v.nextInt(1000) == 0) && ((func_70638_az() == null) || (this.aggroCooldown-- <= 0)))
/*     */       {
/* 227 */         func_70624_b(this.field_70170_p.func_72890_a(this, 16.0D));
/* 228 */         if (func_70638_az() != null)
/*     */         {
/* 230 */           this.aggroCooldown = 50;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 235 */       if ((!this.field_70128_L) && (func_70638_az() != null) && (func_70638_az().func_70068_e(this) < attackrange * attackrange))
/*     */       {
/* 237 */         double d5 = func_70638_az().field_70165_t - this.field_70165_t;
/* 238 */         double d6 = func_70638_az().func_174813_aQ().field_72338_b + func_70638_az().field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/* 239 */         double d7 = func_70638_az().field_70161_v - this.field_70161_v;
/* 240 */         this.field_70761_aq = (this.field_70177_z = -(float)Math.atan2(d5, d7) * 180.0F / 3.141593F);
/* 241 */         if (func_70685_l(func_70638_az()))
/*     */         {
/* 243 */           this.attackCounter += 1;
/* 244 */           if (this.attackCounter == 20)
/*     */           {
/* 246 */             this.field_70170_p.func_72956_a(this, "thaumcraft:zap", 1.0F, 1.1F);
/*     */             
/* 248 */             thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new PacketFXWispZap(func_145782_y(), func_70638_az().func_145782_y()), new net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.func_177502_q(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */             
/*     */ 
/*     */ 
/* 252 */             float damage = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*     */             
/* 254 */             if ((Math.abs(func_70638_az().field_70159_w) > 0.10000000149011612D) || (Math.abs(func_70638_az().field_70181_x) > 0.10000000149011612D) || (Math.abs(func_70638_az().field_70179_y) > 0.10000000149011612D))
/*     */             {
/*     */ 
/* 257 */               if (this.field_70170_p.field_73012_v.nextFloat() < 0.4F) {
/* 258 */                 func_70638_az().func_70097_a(DamageSource.func_76358_a(this), damage);
/*     */               }
/*     */             }
/* 261 */             else if (this.field_70170_p.field_73012_v.nextFloat() < 0.66F) {
/* 262 */               func_70638_az().func_70097_a(DamageSource.func_76358_a(this), damage + 1.0F);
/*     */             }
/* 264 */             this.attackCounter = (-20 + this.field_70170_p.field_73012_v.nextInt(20));
/*     */           }
/*     */         }
/* 267 */         else if (this.attackCounter > 0)
/*     */         {
/* 269 */           this.attackCounter -= 1;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 280 */     return "thaumcraft:wisplive";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 286 */     return "random.fizz";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 292 */     return "thaumcraft:wispdead";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 298 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 304 */     if (Aspect.getAspect(getType()) != null) {
/* 305 */       ItemStack ess = new ItemStack(ItemsTC.wispyEssence);
/* 306 */       AspectList al = new AspectList();
/* 307 */       ((ItemGenericEssentiaContainer)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.getAspect(getType()), 2));
/*     */       
/* 309 */       func_70099_a(ess, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 317 */     return 0.25F;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 322 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 328 */     int count = 0;
/*     */     try {
/* 330 */       List l = this.field_70170_p.func_72872_a(EntityWisp.class, func_174813_aQ().func_72314_b(16.0D, 16.0D, 16.0D));
/*     */       
/* 332 */       if (l != null) count = l.size();
/*     */     }
/*     */     catch (Exception e) {}
/* 335 */     return (count < 8) && (this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL) && (isValidLightLevel()) && (super.func_70601_bi());
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean isValidLightLevel()
/*     */   {
/* 341 */     BlockPos blockpos = new BlockPos(this.field_70165_t, func_174813_aQ().field_72338_b, this.field_70161_v);
/*     */     
/*     */ 
/* 344 */     if (this.field_70170_p.func_175642_b(net.minecraft.world.EnumSkyBlock.SKY, blockpos) > this.field_70146_Z.nextInt(32))
/*     */     {
/* 346 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 350 */     int i = this.field_70170_p.func_175671_l(blockpos);
/*     */     
/* 352 */     if (this.field_70170_p.func_72911_I())
/*     */     {
/* 354 */       int j = this.field_70170_p.func_175657_ab();
/* 355 */       this.field_70170_p.func_175692_b(10);
/* 356 */       i = this.field_70170_p.func_175671_l(blockpos);
/* 357 */       this.field_70170_p.func_175692_b(j);
/*     */     }
/*     */     
/* 360 */     return i <= this.field_70146_Z.nextInt(8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbttagcompound)
/*     */   {
/* 367 */     super.func_70014_b(nbttagcompound);
/* 368 */     nbttagcompound.func_74778_a("Type", getType());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbttagcompound)
/*     */   {
/* 374 */     super.func_70037_a(nbttagcompound);
/* 375 */     setType(nbttagcompound.func_74779_i("Type"));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70641_bl()
/*     */   {
/* 381 */     return 2;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/EntityWisp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */