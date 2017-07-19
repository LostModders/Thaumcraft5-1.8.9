/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintFibre;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ public class EntityTaintCrawler extends EntityMob implements ITaintedMob
/*     */ {
/*     */   public EntityTaintCrawler(World par1World)
/*     */   {
/*  45 */     super(par1World);
/*  46 */     func_70105_a(0.4F, 0.3F);
/*  47 */     this.field_70728_aV = 3;
/*  48 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  49 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  50 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
/*  51 */     this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  52 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  53 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
/*  54 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/*  60 */     return !ITaintedMob.class.isAssignableFrom(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase otherEntity)
/*     */   {
/*  66 */     return ((otherEntity instanceof ITaintedMob)) || (func_142012_a(otherEntity.func_96124_cp()));
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/*  72 */     return 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  78 */     super.func_110147_ax();
/*  79 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
/*  80 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.275D);
/*  81 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/*  86 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/*  92 */     return "mob.silverfish.say";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/*  98 */     return "mob.silverfish.hit";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 104 */     return "mob.silverfish.kill";
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_)
/*     */   {
/* 110 */     func_85030_a("mob.silverfish.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 116 */     return false;
/*     */   }
/*     */   
/* 119 */   BlockPos lastPos = new BlockPos(0, 0, 0);
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 123 */     if ((this.field_70170_p.field_72995_K) && (this.field_70146_Z.nextFloat() < 0.05F)) {
/* 124 */       Thaumcraft.proxy.getFX().drawPollutionParticles(func_180425_c());
/*     */     }
/*     */     
/* 127 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa % 5 == 0) && (this.lastPos != func_180425_c())) {
/* 128 */       this.lastPos = func_180425_c();
/* 129 */       IBlockState bs = this.field_70170_p.func_180495_p(func_180425_c());
/* 130 */       Material bm = bs.func_177230_c().func_149688_o();
/*     */       
/* 132 */       if ((!bs.func_177230_c().isLeaves(this.field_70170_p, func_180425_c())) && (!bm.func_76224_d()) && (bm != ThaumcraftMaterials.MATERIAL_TAINT) && ((this.field_70170_p.func_175623_d(func_180425_c())) || (bs.func_177230_c().func_176200_f(this.field_70170_p, func_180425_c())) || ((bs.func_177230_c() instanceof BlockFlower)) || ((bs.func_177230_c() instanceof net.minecraftforge.common.IPlantable))) && (thaumcraft.common.lib.utils.BlockUtils.isAdjacentToSolidBlock(this.field_70170_p, func_180425_c())) && (!BlockTaintFibre.isOnlyAdjacentToTaint(this.field_70170_p, func_180425_c())))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 138 */         if (this.field_70170_p.field_73012_v.nextFloat() < Config.taintSpreadRate / 3.0F) AuraHelper.drainAura(this.field_70170_p, func_180425_c(), Aspect.FLUX, 1);
/* 139 */         this.field_70170_p.func_175656_a(func_180425_c(), BlocksTC.taintFibre.func_176223_P());
/*     */       }
/*     */     }
/*     */     
/* 143 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean func_70814_o()
/*     */   {
/* 150 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumCreatureAttribute func_70668_bt()
/*     */   {
/* 156 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 162 */     return ItemsTC.tainted;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 167 */     if (this.field_70170_p.field_73012_v.nextInt(8) == 0) {
/* 168 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 169 */         func_70099_a(new ItemStack(ItemsTC.tainted, 1, 0), this.field_70131_O / 2.0F);
/*     */       } else {
/* 171 */         func_70099_a(new ItemStack(ItemsTC.tainted, 1, 1), this.field_70131_O / 2.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 177 */     return p_180482_2_;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity victim)
/*     */   {
/* 183 */     if (super.func_70652_k(victim))
/*     */     {
/* 185 */       if ((victim instanceof EntityLivingBase))
/*     */       {
/* 187 */         byte b0 = 0;
/*     */         
/* 189 */         if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL)
/*     */         {
/* 191 */           b0 = 3;
/*     */         }
/* 193 */         else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD)
/*     */         {
/* 195 */           b0 = 6;
/*     */         }
/*     */         
/* 198 */         if ((b0 > 0) && (this.field_70146_Z.nextInt(b0 + 1) > 2))
/*     */         {
/* 200 */           ((EntityLivingBase)victim).func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), b0 * 20, 0));
/*     */         }
/*     */       }
/*     */       
/* 204 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 208 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintCrawler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */