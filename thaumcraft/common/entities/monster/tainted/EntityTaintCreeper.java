/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ import thaumcraft.common.entities.ai.combat.AICreeperSwell;
/*     */ 
/*     */ public class EntityTaintCreeper
/*     */   extends EntityMob
/*     */   implements ITaintedMob
/*     */ {
/*     */   private int lastActiveTime;
/*     */   private int timeSinceIgnited;
/*  46 */   private int fuseTime = 30;
/*     */   
/*     */ 
/*  49 */   private int explosionRadius = 3;
/*     */   
/*     */   public EntityTaintCreeper(World par1World)
/*     */   {
/*  53 */     super(par1World);
/*  54 */     this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
/*  55 */     this.field_70714_bg.func_75776_a(2, new AICreeperSwell(this));
/*  56 */     this.field_70714_bg.func_75776_a(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
/*  57 */     this.field_70714_bg.func_75776_a(4, new AIAttackOnCollide(this, 1.0D, false));
/*  58 */     this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
/*  59 */     this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  60 */     this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
/*  61 */     this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  62 */     this.field_70715_bh.func_75776_a(2, new EntityAIHurtByTarget(this, false, new Class[0]));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  68 */     super.func_110147_ax();
/*  69 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*  70 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*  71 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*  72 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  78 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  84 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180430_e(float distance, float damageMultiplier)
/*     */   {
/*  90 */     super.func_180430_e(distance, damageMultiplier);
/*  91 */     this.timeSinceIgnited = ((int)(this.timeSinceIgnited + distance * 1.5F));
/*     */     
/*  93 */     if (this.timeSinceIgnited > this.fuseTime - 5)
/*     */     {
/*  95 */       this.timeSinceIgnited = (this.fuseTime - 5);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 102 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/* 104 */     if (this.field_70180_af.func_75683_a(17) == 1)
/*     */     {
/* 106 */       par1NBTTagCompound.func_74757_a("powered", true);
/*     */     }
/*     */     
/* 109 */     par1NBTTagCompound.func_74777_a("Fuse", (short)this.fuseTime);
/* 110 */     par1NBTTagCompound.func_74774_a("ExplosionRadius", (byte)this.explosionRadius);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 117 */     super.func_70037_a(par1NBTTagCompound);
/* 118 */     this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)(par1NBTTagCompound.func_74767_n("powered") ? 1 : 0)));
/*     */     
/* 120 */     if (par1NBTTagCompound.func_74764_b("Fuse"))
/*     */     {
/* 122 */       this.fuseTime = par1NBTTagCompound.func_74765_d("Fuse");
/*     */     }
/*     */     
/* 125 */     if (par1NBTTagCompound.func_74764_b("ExplosionRadius"))
/*     */     {
/* 127 */       this.explosionRadius = par1NBTTagCompound.func_74771_c("ExplosionRadius");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/* 134 */     super.func_70088_a();
/* 135 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)-1));
/* 136 */     this.field_70180_af.func_75682_a(17, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 143 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 148 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 149 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 0), this.field_70131_O / 2.0F);
/*     */     } else {
/* 151 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 1), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 160 */     if (func_70089_S())
/*     */     {
/* 162 */       this.lastActiveTime = this.timeSinceIgnited;
/* 163 */       int var1 = getCreeperState();
/*     */       
/* 165 */       if ((var1 > 0) && (this.timeSinceIgnited == 0))
/*     */       {
/* 167 */         this.field_70170_p.func_72956_a(this, "random.fuse", 1.0F, 0.5F);
/*     */       }
/*     */       
/* 170 */       this.timeSinceIgnited += var1;
/*     */       
/* 172 */       if (this.timeSinceIgnited < 0)
/*     */       {
/* 174 */         this.timeSinceIgnited = 0;
/*     */       }
/*     */       
/* 177 */       if (this.timeSinceIgnited >= 30)
/*     */       {
/* 179 */         this.timeSinceIgnited = 30;
/*     */         
/* 181 */         if (!this.field_70170_p.field_72995_K)
/*     */         {
/* 183 */           this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u + this.field_70131_O / 2.0F, this.field_70161_v, 1.5F, false);
/* 184 */           List ents = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_178781_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v).func_72314_b(6.0D, 6.0D, 6.0D));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 189 */           if (ents.size() > 0) {
/* 190 */             for (Object ent : ents) {
/* 191 */               EntityLivingBase el = (EntityLivingBase)ent;
/* 192 */               if ((!(el instanceof ITaintedMob)) && (!el.func_70662_br())) {
/* 193 */                 el.func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), 100, 0, false, true));
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 198 */           int x = (int)this.field_70165_t;
/* 199 */           int y = (int)this.field_70163_u;
/* 200 */           int z = (int)this.field_70161_v;
/* 201 */           for (int a = 0; a < 10; a++) {
/* 202 */             int xx = x + (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 5.0F);
/* 203 */             int zz = z + (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 5.0F);
/* 204 */             if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 205 */               BlockPos bp = new BlockPos(xx, y, zz);
/* 206 */               IBlockState bs = this.field_70170_p.func_180495_p(bp);
/* 207 */               if ((this.field_70170_p.func_175677_d(bp.func_177977_b(), false)) && (bs.func_177230_c().func_176200_f(this.field_70170_p, bp)))
/*     */               {
/* 209 */                 this.field_70170_p.func_175656_a(bp, BlocksTC.taintFibre.func_176223_P());
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 215 */           func_70106_y();
/*     */         } else {
/* 217 */           for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(100); a++) { Thaumcraft.proxy.getFX().taintsplosionFX(this);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 222 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 227 */     super.func_70636_d();
/* 228 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/* 229 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(10); a++)
/* 230 */         Thaumcraft.proxy.getFX().splooshFX(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public float getCreeperFlashIntensity(float par1) {
/* 235 */     return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * par1) / 28.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 244 */     return "mob.creeper.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 253 */     return "mob.creeper.death";
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 258 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 264 */     return true;
/*     */   }
/*     */   
/*     */   public int getCreeperState()
/*     */   {
/* 269 */     return this.field_70180_af.func_75683_a(16);
/*     */   }
/*     */   
/*     */   public void setCreeperState(int par1)
/*     */   {
/* 274 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)par1));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */