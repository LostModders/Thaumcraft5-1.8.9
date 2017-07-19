/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityTaintSwarm extends EntityMob implements ITaintedMob
/*     */ {
/*     */   private BlockPos currentFlightTarget;
/*     */   
/*     */   public EntityTaintSwarm(World par1World)
/*     */   {
/*  37 */     super(par1World);
/*  38 */     func_70105_a(2.0F, 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  44 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  50 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  56 */     super.func_70088_a();
/*  57 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/*  64 */     return 15728880;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  70 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/*  79 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/*  88 */     return 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/*  98 */     return "thaumcraft:swarm";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 107 */     return "thaumcraft:swarmattack";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 116 */     return "thaumcraft:swarmattack";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70104_M()
/*     */   {
/* 125 */     return false;
/*     */   }
/*     */   
/* 128 */   public int damBonus = 0;
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 133 */     super.func_110147_ax();
/* 134 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/* 135 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2 + this.damBonus);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getIsSummoned()
/*     */   {
/* 141 */     return (this.field_70180_af.func_75683_a(16) & 0x2) != 0;
/*     */   }
/*     */   
/*     */   public void setIsSummoned(boolean par1)
/*     */   {
/* 146 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 148 */     if (par1)
/*     */     {
/* 150 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 | 0x2)));
/*     */     }
/*     */     else
/*     */     {
/* 154 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFC)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 161 */   public ArrayList swarm = new ArrayList();
/*     */   private int attackTime;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 166 */     super.func_70071_h_();
/*     */     
/*     */ 
/* 169 */     this.field_70181_x *= 0.6000000238418579D;
/*     */     
/* 171 */     if (this.field_70170_p.field_72995_K) {
/* 172 */       for (int a = 0; a < this.swarm.size(); a++) {
/* 173 */         if ((this.swarm.get(a) == null) || (((Entity)this.swarm.get(a)).field_70128_L)) {
/* 174 */           this.swarm.remove(a);
/* 175 */           break;
/*     */         }
/*     */       }
/*     */       
/* 179 */       if (this.swarm.size() < Math.max(Thaumcraft.proxy.getFX().particleCount(25), 10)) {
/* 180 */         this.swarm.add(Thaumcraft.proxy.getFX().swarmParticleFX(this, 0.22F, 15.0F, 0.08F));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 187 */     super.func_70636_d();
/*     */     
/* 189 */     if (func_70638_az() == null)
/*     */     {
/* 191 */       if (getIsSummoned()) {
/* 192 */         func_70097_a(DamageSource.field_76377_j, 5.0F);
/*     */       }
/* 194 */       if ((this.currentFlightTarget != null) && ((!this.field_70170_p.func_175623_d(this.currentFlightTarget)) || (this.currentFlightTarget.func_177956_o() < 1) || (this.currentFlightTarget.func_177956_o() > this.field_70170_p.func_175725_q(this.currentFlightTarget).func_177981_b(2).func_177956_o()) || (this.field_70170_p.func_180494_b(this.currentFlightTarget).field_76756_M != Config.biomeTaintID)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 199 */         this.currentFlightTarget = null;
/*     */       }
/*     */       
/* 202 */       if ((this.currentFlightTarget == null) || (this.field_70146_Z.nextInt(30) == 0) || (func_174831_c(this.currentFlightTarget) < 4.0D))
/*     */       {
/*     */ 
/* 205 */         this.currentFlightTarget = new BlockPos((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 211 */       double var1 = this.currentFlightTarget.func_177958_n() + 0.5D - this.field_70165_t;
/* 212 */       double var3 = this.currentFlightTarget.func_177956_o() + 0.1D - this.field_70163_u;
/* 213 */       double var5 = this.currentFlightTarget.func_177952_p() + 0.5D - this.field_70161_v;
/* 214 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.015000000014901161D;
/* 215 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 216 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.015000000014901161D;
/* 217 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 218 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 219 */       this.field_70701_bs = 0.1F;
/* 220 */       this.field_70177_z += var8;
/*     */     }
/* 222 */     else if (func_70638_az() != null) {
/* 223 */       double var1 = func_70638_az().field_70165_t - this.field_70165_t;
/* 224 */       double var3 = func_70638_az().field_70163_u + func_70638_az().func_70047_e() - this.field_70163_u;
/* 225 */       double var5 = func_70638_az().field_70161_v - this.field_70161_v;
/* 226 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.025000000149011613D;
/* 227 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 228 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.02500000001490116D;
/* 229 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 230 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 231 */       this.field_70701_bs = 0.1F;
/* 232 */       this.field_70177_z += var8;
/*     */     }
/*     */     
/* 235 */     if (func_70638_az() == null)
/*     */     {
/* 237 */       func_70624_b((EntityLivingBase)findPlayerToAttack());
/*     */     }
/* 239 */     else if (func_70638_az().func_70089_S())
/*     */     {
/* 241 */       float f = func_70638_az().func_70032_d(this);
/*     */       
/* 243 */       if (func_70685_l(func_70638_az()))
/*     */       {
/* 245 */         attackEntity(func_70638_az(), f);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 250 */       func_70624_b(null);
/*     */     }
/*     */     
/* 253 */     if (((func_70638_az() instanceof EntityPlayer)) && (((EntityPlayer)func_70638_az()).field_71075_bZ.field_75102_a))
/*     */     {
/* 255 */       func_70624_b(null);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/* 265 */     super.func_70619_bc();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 275 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145773_az()
/*     */   {
/* 288 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 297 */     if (func_180431_b(par1DamageSource))
/*     */     {
/* 299 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 303 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void attackEntity(Entity par1Entity, float par2)
/*     */   {
/* 311 */     if ((this.attackTime <= 0) && (par2 < 3.0F) && (par1Entity.func_174813_aQ().field_72337_e > func_174813_aQ().field_72338_b) && (par1Entity.func_174813_aQ().field_72338_b < func_174813_aQ().field_72337_e))
/*     */     {
/* 313 */       if (getIsSummoned()) {
/* 314 */         EntityUtils.setRecentlyHit((EntityLivingBase)par1Entity, 100);
/*     */       }
/*     */       
/* 317 */       this.attackTime = (10 + this.field_70146_Z.nextInt(5));
/*     */       
/* 319 */       double mx = par1Entity.field_70159_w;
/* 320 */       double my = par1Entity.field_70181_x;
/* 321 */       double mz = par1Entity.field_70179_y;
/* 322 */       if ((func_70652_k(par1Entity)) && 
/* 323 */         (!this.field_70170_p.field_72995_K) && ((par1Entity instanceof EntityLivingBase))) {
/* 324 */         ((EntityLivingBase)par1Entity).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100, 0));
/*     */       }
/*     */       
/* 327 */       par1Entity.field_70160_al = false;
/* 328 */       par1Entity.field_70159_w = mx;
/* 329 */       par1Entity.field_70181_x = my;
/* 330 */       par1Entity.field_70179_y = mz;
/*     */       
/* 332 */       this.field_70170_p.func_72956_a(this, "thaumcraft:swarmattack", 0.3F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Entity findPlayerToAttack()
/*     */   {
/* 340 */     double var1 = 8.0D;
/* 341 */     return getIsSummoned() ? null : this.field_70170_p.func_72890_a(this, var1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 350 */     super.func_70037_a(par1NBTTagCompound);
/* 351 */     this.field_70180_af.func_75692_b(16, Byte.valueOf(par1NBTTagCompound.func_74771_c("Flags")));
/* 352 */     this.damBonus = par1NBTTagCompound.func_74771_c("damBonus");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 361 */     super.func_70014_b(par1NBTTagCompound);
/* 362 */     par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(16));
/* 363 */     par1NBTTagCompound.func_74774_a("damBonus", (byte)this.damBonus);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 372 */     int var1 = MathHelper.func_76128_c(func_174813_aQ().field_72338_b);
/*     */     
/* 374 */     int var2 = MathHelper.func_76128_c(this.field_70165_t);
/* 375 */     int var3 = MathHelper.func_76128_c(this.field_70161_v);
/* 376 */     int var4 = this.field_70170_p.func_175699_k(new BlockPos(var2, var1, var3));
/* 377 */     byte var5 = 7;
/*     */     
/* 379 */     return var4 > this.field_70146_Z.nextInt(var5) ? false : super.func_70601_bi();
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70814_o()
/*     */   {
/* 385 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 391 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 396 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 397 */       func_70099_a(new ItemStack(ItemsTC.tainted), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintSwarm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */