/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ public class EntityTaintacle extends EntityMob implements ITaintedMob, thaumcraft.common.entities.IEntityReach
/*     */ {
/*  33 */   public float flailIntensity = 1.0F;
/*     */   
/*     */   public EntityTaintacle(World par1World)
/*     */   {
/*  37 */     super(par1World);
/*  38 */     func_70105_a(0.8F, 3.0F);
/*  39 */     this.field_70728_aV = 10;
/*     */     
/*  41 */     this.field_70714_bg.func_75776_a(1, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/*  42 */     this.field_70714_bg.func_75776_a(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  43 */     this.field_70714_bg.func_75776_a(3, new EntityAILookIdle(this));
/*  44 */     this.field_70715_bh.func_75776_a(0, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false, new Class[0]));
/*  45 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  51 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  57 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/*  64 */     boolean onTaint = (this.field_70170_p.func_180495_p(func_180425_c()).func_177230_c().func_149688_o() == ThaumcraftMaterials.MATERIAL_TAINT) || (this.field_70170_p.func_180495_p(func_180425_c().func_177977_b()).func_177230_c().func_149688_o() == ThaumcraftMaterials.MATERIAL_TAINT);
/*     */     
/*     */ 
/*  67 */     return (onTaint) && (this.field_70170_p.func_175659_aa() != net.minecraft.world.EnumDifficulty.PEACEFUL);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  74 */     super.func_110147_ax();
/*  75 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*  76 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*  77 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70091_d(double par1, double par3, double par5)
/*     */   {
/*  83 */     par1 = 0.0D;
/*  84 */     par5 = 0.0D;
/*  85 */     if (par3 > 0.0D) par3 = 0.0D;
/*  86 */     super.func_70091_d(par1, par3, par5);
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  91 */     super.func_70071_h_();
/*     */     
/*  93 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa % 20 == 0))
/*     */     {
/*  95 */       boolean onTaint = (this.field_70170_p.func_180495_p(func_180425_c()).func_177230_c().func_149688_o() == ThaumcraftMaterials.MATERIAL_TAINT) || (this.field_70170_p.func_180495_p(func_180425_c().func_177977_b()).func_177230_c().func_149688_o() == ThaumcraftMaterials.MATERIAL_TAINT);
/*     */       
/*  97 */       if (!onTaint) { func_70665_d(DamageSource.field_76366_f, 1.0F);
/*     */       }
/*  99 */       if ((!(this instanceof EntityTaintacleSmall)) && (this.field_70173_aa % 40 == 0) && (func_70638_az() != null) && (func_70068_e(func_70638_az()) > 16.0D) && (func_70068_e(func_70638_az()) < 256.0D) && (func_70635_at().func_75522_a(func_70638_az())))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 105 */         spawnTentacles(func_70638_az());
/*     */       }
/*     */     }
/*     */     
/* 109 */     if (this.field_70170_p.field_72995_K) {
/* 110 */       if (this.flailIntensity > 1.0F) { this.flailIntensity -= 0.01F;
/*     */       }
/* 112 */       if ((this.field_70173_aa < this.field_70131_O * 10.0F) && (this.field_70122_E)) {
/* 113 */         Thaumcraft.proxy.getFX().tentacleAriseFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void spawnTentacles(Entity entity)
/*     */   {
/* 120 */     if ((this.field_70170_p.func_180494_b(entity.func_180425_c()).field_76756_M == Config.biomeEldritchID) || (this.field_70170_p.func_180495_p(entity.func_180425_c()).func_177230_c().func_149688_o() == ThaumcraftMaterials.MATERIAL_TAINT) || (this.field_70170_p.func_180495_p(entity.func_180425_c().func_177977_b()).func_177230_c().func_149688_o() == ThaumcraftMaterials.MATERIAL_TAINT))
/*     */     {
/*     */ 
/* 123 */       EntityTaintacleSmall taintlet = new EntityTaintacleSmall(this.field_70170_p);
/* 124 */       taintlet.func_70012_b(entity.field_70165_t + this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), entity.field_70163_u, entity.field_70161_v + this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), 0.0F, 0.0F);
/*     */       
/*     */ 
/*     */ 
/* 128 */       this.field_70170_p.func_72838_d(taintlet);
/* 129 */       func_85030_a("thaumcraft:tentacle", func_70599_aP(), func_70647_i());
/* 130 */       if ((this.field_70170_p.func_180494_b(entity.func_180425_c()).field_76756_M == Config.biomeEldritchID) && (this.field_70170_p.func_175623_d(entity.func_180425_c())) && (thaumcraft.common.lib.utils.BlockUtils.isAdjacentToSolidBlock(this.field_70170_p, entity.func_180425_c())))
/*     */       {
/* 132 */         this.field_70170_p.func_175656_a(entity.func_180425_c(), BlocksTC.taintFibre.func_176223_P());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void faceEntity(Entity par1Entity, float par2)
/*     */   {
/* 141 */     double d0 = par1Entity.field_70165_t - this.field_70165_t;
/* 142 */     double d1 = par1Entity.field_70161_v - this.field_70161_v;
/*     */     
/*     */ 
/* 145 */     float f2 = (float)(Math.atan2(d1, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 146 */     this.field_70177_z = func_70663_b(this.field_70177_z, f2, par2);
/*     */   }
/*     */   
/*     */   protected float func_70663_b(float par1, float par2, float par3)
/*     */   {
/* 151 */     float f3 = MathHelper.func_76142_g(par2 - par1);
/*     */     
/* 153 */     if (f3 > par3)
/*     */     {
/* 155 */       f3 = par3;
/*     */     }
/*     */     
/* 158 */     if (f3 < -par3)
/*     */     {
/* 160 */       f3 = -par3;
/*     */     }
/*     */     
/* 163 */     return par1 + f3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 170 */     return 200;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 177 */     return "thaumcraft:roots";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected float func_70647_i()
/*     */   {
/* 184 */     return 1.3F - this.field_70131_O / 10.0F;
/*     */   }
/*     */   
/*     */   protected float func_70599_aP()
/*     */   {
/* 189 */     return this.field_70131_O / 8.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 196 */     return "thaumcraft:tentacle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 203 */     return "thaumcraft:tentacle";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 209 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 214 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 215 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 0), this.field_70131_O / 2.0F);
/*     */     } else {
/* 217 */       func_70099_a(new ItemStack(ItemsTC.tainted, 1, 1), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 224 */     if (par1 == 16)
/*     */     {
/* 226 */       this.flailIntensity = 3.0F;
/*     */     }
/*     */     else
/*     */     {
/* 230 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 237 */     this.field_70170_p.func_72960_a(this, (byte)16);
/* 238 */     func_85030_a("thaumcraft:tentacle", func_70599_aP(), func_70647_i());
/* 239 */     return super.func_70652_k(p_70652_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public double getReach()
/*     */   {
/* 245 */     return 2.0F + this.field_70146_Z.nextFloat();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintacle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */