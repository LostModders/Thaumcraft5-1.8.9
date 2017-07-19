/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ import thaumcraft.common.entities.ai.misc.AIConvertGrass;
/*     */ 
/*     */ public class EntityTaintSheep extends EntityMob implements IShearable, ITaintedMob
/*     */ {
/*     */   private int sheepTimer;
/*  44 */   private AIConvertGrass field_48137_c = new AIConvertGrass(this);
/*     */   
/*     */   public EntityTaintSheep(World par1World)
/*     */   {
/*  48 */     super(par1World);
/*  49 */     func_70105_a(0.9F, 1.3F);
/*  50 */     ((PathNavigateGround)func_70661_as()).func_179690_a(true);
/*  51 */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*  52 */     this.field_70714_bg.func_75776_a(2, this.field_48137_c);
/*  53 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  54 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  55 */     this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
/*  56 */     this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  57 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  58 */     this.field_70715_bh.func_75776_a(0, new EntityAIHurtByTarget(this, false, new Class[0]));
/*  59 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  60 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  66 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  72 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  78 */     super.func_110147_ax();
/*  79 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  80 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/*  81 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  91 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/*  97 */     this.sheepTimer = this.field_48137_c.func_48396_h();
/*  98 */     super.func_70619_bc();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 104 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 106 */       this.sheepTimer = Math.max(0, this.sheepTimer - 1);
/*     */     }
/*     */     
/* 109 */     super.func_70636_d();
/* 110 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/* 111 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(10); a++) {
/* 112 */         Thaumcraft.proxy.getFX().splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {
/* 118 */     super.func_70088_a();
/* 119 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 125 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 130 */     if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 131 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 132 */         func_70099_a(new ItemStack(ItemsTC.tainted, 1, 0), this.field_70131_O / 2.0F);
/*     */       } else {
/* 134 */         func_70099_a(new ItemStack(ItemsTC.tainted, 1, 1), this.field_70131_O / 2.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70103_a(byte par1) {
/* 140 */     if (par1 == 10)
/*     */     {
/* 142 */       this.sheepTimer = 40;
/*     */     }
/*     */     else
/*     */     {
/* 146 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public float func_44003_c(float par1)
/*     */   {
/* 152 */     return this.sheepTimer < 4 ? (this.sheepTimer - par1) / 4.0F : (this.sheepTimer >= 4) && (this.sheepTimer <= 36) ? 1.0F : this.sheepTimer <= 0 ? 0.0F : -(this.sheepTimer - 40 - par1) / 4.0F;
/*     */   }
/*     */   
/*     */   public float func_44002_d(float par1)
/*     */   {
/* 157 */     if ((this.sheepTimer > 4) && (this.sheepTimer <= 36))
/*     */     {
/* 159 */       float var2 = (this.sheepTimer - 4 - par1) / 32.0F;
/* 160 */       return 0.62831855F + 0.2199115F * MathHelper.func_76126_a(var2 * 28.7F);
/*     */     }
/*     */     
/*     */ 
/* 164 */     return this.sheepTimer > 0 ? 0.62831855F : this.field_70125_A / 57.295776F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 172 */     super.func_70014_b(par1NBTTagCompound);
/* 173 */     par1NBTTagCompound.func_74757_a("Sheared", getSheared());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 179 */     super.func_70037_a(par1NBTTagCompound);
/* 180 */     setSheared(par1NBTTagCompound.func_74767_n("Sheared"));
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 186 */     return "mob.sheep.say";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 192 */     return "mob.sheep.say";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 198 */     return "mob.sheep.say";
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_)
/*     */   {
/* 204 */     func_85030_a("mob.sheep.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 209 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getSheared()
/*     */   {
/* 215 */     return (this.field_70180_af.func_75683_a(16) & 0x10) != 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSheared(boolean par1)
/*     */   {
/* 221 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 223 */     if (par1)
/*     */     {
/* 225 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 | 0x10)));
/*     */     }
/*     */     else
/*     */     {
/* 229 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 & 0xFFFFFFEF)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
/*     */   {
/* 238 */     return !getSheared();
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
/*     */   {
/* 244 */     ArrayList<ItemStack> ret = new ArrayList();
/* 245 */     setSheared(true);
/* 246 */     int i = 1 + this.field_70146_Z.nextInt(3);
/* 247 */     for (int j = 0; j < i; j++)
/*     */     {
/* 249 */       ret.add(new ItemStack(net.minecraft.init.Blocks.field_150325_L, 1, 10));
/*     */     }
/* 251 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity victim)
/*     */   {
/* 257 */     if (super.func_70652_k(victim))
/*     */     {
/* 259 */       if ((victim instanceof EntityLivingBase))
/*     */       {
/* 261 */         byte b0 = 0;
/*     */         
/* 263 */         if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL)
/*     */         {
/* 265 */           b0 = 3;
/*     */         }
/* 267 */         else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD)
/*     */         {
/* 269 */           b0 = 6;
/*     */         }
/*     */         
/* 272 */         if ((b0 > 0) && (this.field_70146_Z.nextInt(b0 + 1) > 2))
/*     */         {
/* 274 */           ((EntityLivingBase)victim).func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), b0 * 20, 0));
/*     */         }
/*     */       }
/*     */       
/* 278 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 282 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintSheep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */