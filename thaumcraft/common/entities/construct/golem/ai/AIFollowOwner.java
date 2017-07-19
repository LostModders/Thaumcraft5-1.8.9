/*     */ package thaumcraft.common.entities.construct.golem.ai;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.construct.EntityOwnedConstruct;
/*     */ 
/*     */ public class AIFollowOwner extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityOwnedConstruct thePet;
/*     */   private EntityLivingBase theOwner;
/*     */   World theWorld;
/*     */   private double field_75336_f;
/*     */   private PathNavigate petPathfinder;
/*     */   private int field_75343_h;
/*     */   float maxDist;
/*     */   float minDist;
/*     */   private boolean field_75344_i;
/*     */   
/*     */   public AIFollowOwner(EntityOwnedConstruct p_i1625_1_, double p_i1625_2_, float p_i1625_4_, float p_i1625_5_)
/*     */   {
/*  26 */     this.thePet = p_i1625_1_;
/*  27 */     this.theWorld = p_i1625_1_.field_70170_p;
/*  28 */     this.field_75336_f = p_i1625_2_;
/*  29 */     this.petPathfinder = p_i1625_1_.func_70661_as();
/*  30 */     this.minDist = p_i1625_4_;
/*  31 */     this.maxDist = p_i1625_5_;
/*  32 */     func_75248_a(3);
/*     */     
/*  34 */     if ((!(p_i1625_1_.func_70661_as() instanceof PathNavigateGround)) && (!(p_i1625_1_.func_70661_as() instanceof PathNavigateGolemAir)))
/*     */     {
/*  36 */       throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_75250_a()
/*     */   {
/*  42 */     EntityLivingBase entitylivingbase = this.thePet.getOwnerEntity();
/*     */     
/*  44 */     if (entitylivingbase == null)
/*     */     {
/*  46 */       return false;
/*     */     }
/*  48 */     if (this.thePet.func_70068_e(entitylivingbase) < this.minDist * this.minDist)
/*     */     {
/*  50 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  54 */     this.theOwner = entitylivingbase;
/*  55 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  61 */     return (!this.petPathfinder.func_75500_f()) && (this.thePet.func_70068_e(this.theOwner) > this.maxDist * this.maxDist);
/*     */   }
/*     */   
/*     */   public void func_75249_e()
/*     */   {
/*  66 */     this.field_75343_h = 0;
/*  67 */     if ((this.thePet.func_70661_as() instanceof PathNavigateGround)) {
/*  68 */       this.field_75344_i = ((PathNavigateGround)this.thePet.func_70661_as()).func_179689_e();
/*  69 */       ((PathNavigateGround)this.thePet.func_70661_as()).func_179690_a(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_75251_c()
/*     */   {
/*  75 */     this.theOwner = null;
/*  76 */     this.petPathfinder.func_75499_g();
/*  77 */     if ((this.thePet.func_70661_as() instanceof PathNavigateGround)) {
/*  78 */       ((PathNavigateGround)this.thePet.func_70661_as()).func_179690_a(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/*  84 */     this.thePet.func_70671_ap().func_75651_a(this.theOwner, 10.0F, this.thePet.func_70646_bf());
/*     */     
/*  86 */     if (--this.field_75343_h <= 0)
/*     */     {
/*  88 */       this.field_75343_h = 10;
/*     */       
/*  90 */       if (!this.petPathfinder.func_75497_a(this.theOwner, this.field_75336_f))
/*     */       {
/*  92 */         if (!this.thePet.func_110167_bD())
/*     */         {
/*  94 */           if (this.thePet.func_70068_e(this.theOwner) >= 144.0D)
/*     */           {
/*  96 */             int i = MathHelper.func_76128_c(this.theOwner.field_70165_t) - 2;
/*  97 */             int j = MathHelper.func_76128_c(this.theOwner.field_70161_v) - 2;
/*  98 */             int k = MathHelper.func_76128_c(this.theOwner.func_174813_aQ().field_72338_b);
/*     */             
/* 100 */             for (int l = 0; l <= 4; l++)
/*     */             {
/* 102 */               for (int i1 = 0; i1 <= 4; i1++)
/*     */               {
/* 104 */                 if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (World.func_175683_a(this.theWorld, new BlockPos(i + l, k - 1, j + i1))) && (!this.theWorld.func_180495_p(new BlockPos(i + l, k, j + i1)).func_177230_c().func_149686_d()) && (!this.theWorld.func_180495_p(new BlockPos(i + l, k + 1, j + i1)).func_177230_c().func_149686_d()))
/*     */                 {
/* 106 */                   this.thePet.func_70012_b(i + l + 0.5F, k, j + i1 + 0.5F, this.thePet.field_70177_z, this.thePet.field_70125_A);
/* 107 */                   this.petPathfinder.func_75499_g();
/* 108 */                   return;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/AIFollowOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */