/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.effect.EntityWeatherEffect;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.blocks.world.taint.BlockFluxGoo;
/*     */ 
/*     */ public class EntityTaintSourceLightning extends EntityWeatherEffect
/*     */ {
/*     */   private int lightningState;
/*     */   public long boltVertex;
/*     */   private int boltLivingTime;
/*     */   
/*     */   public EntityTaintSourceLightning(World par1World)
/*     */   {
/*  27 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityTaintSourceLightning(World worldIn, double posX, double posY, double posZ)
/*     */   {
/*  32 */     super(worldIn);
/*  33 */     func_70012_b(posX, posY, posZ, 0.0F, 0.0F);
/*  34 */     this.lightningState = 2;
/*  35 */     this.boltVertex = this.field_70146_Z.nextLong();
/*  36 */     this.boltLivingTime = (this.field_70146_Z.nextInt(3) + 1);
/*     */     
/*  38 */     if ((!worldIn.field_72995_K) && ((worldIn.func_175659_aa() == EnumDifficulty.NORMAL) || (worldIn.func_175659_aa() == EnumDifficulty.HARD)) && (worldIn.func_175697_a(new BlockPos(this), 10)))
/*     */     {
/*     */ 
/*     */ 
/*  42 */       BlockPos blockpos = new BlockPos(this);
/*     */       
/*  44 */       if ((worldIn.func_175623_d(blockpos)) && (BlocksTC.fluxGoo.func_176196_c(worldIn, blockpos)))
/*     */       {
/*  46 */         this.field_70170_p.func_175656_a(blockpos, BlocksTC.fluxGoo.func_176223_P().func_177226_a(BlockFluxGoo.LEVEL, Integer.valueOf(7)));
/*  47 */         this.field_70170_p.func_175684_a(blockpos, BlocksTC.fluxGoo, 2);
/*     */       }
/*     */       
/*  50 */       for (int i = 0; i < 4; i++)
/*     */       {
/*  52 */         BlockPos blockpos1 = blockpos.func_177982_a(this.field_70146_Z.nextInt(5) - 2, this.field_70146_Z.nextInt(5) - 2, this.field_70146_Z.nextInt(5) - 2);
/*     */         
/*  54 */         if ((worldIn.func_175623_d(blockpos1)) && (BlocksTC.fluxGoo.func_176196_c(worldIn, blockpos1)))
/*     */         {
/*  56 */           this.field_70170_p.func_175656_a(blockpos1, BlocksTC.fluxGoo.func_176223_P().func_177226_a(BlockFluxGoo.LEVEL, Integer.valueOf(7)));
/*  57 */           this.field_70170_p.func_175684_a(blockpos1, BlocksTC.fluxGoo, 3);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  67 */     super.func_70071_h_();
/*     */     
/*  69 */     if (this.lightningState == 2)
/*     */     {
/*  71 */       this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "ambient.weather.thunder", 10000.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.2F);
/*  72 */       this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "random.explode", 2.0F, 0.5F + this.field_70146_Z.nextFloat() * 0.2F);
/*     */     }
/*     */     
/*  75 */     this.lightningState -= 1;
/*     */     
/*  77 */     if (this.lightningState < 0)
/*     */     {
/*  79 */       if (this.boltLivingTime == 0)
/*     */       {
/*  81 */         func_70106_y();
/*     */       }
/*  83 */       else if (this.lightningState < -this.field_70146_Z.nextInt(10))
/*     */       {
/*  85 */         this.boltLivingTime -= 1;
/*  86 */         this.lightningState = 1;
/*  87 */         this.boltVertex = this.field_70146_Z.nextLong();
/*  88 */         BlockPos blockpos = new BlockPos(this);
/*     */         
/*  90 */         if ((!this.field_70170_p.field_72995_K) && (this.field_70170_p.func_175697_a(blockpos, 10)) && (this.field_70170_p.func_175623_d(blockpos)) && (BlocksTC.fluxGoo.func_176196_c(this.field_70170_p, blockpos)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*  95 */           this.field_70170_p.func_175656_a(blockpos, BlocksTC.fluxGoo.func_176223_P().func_177226_a(BlockFluxGoo.LEVEL, Integer.valueOf(7)));
/*  96 */           this.field_70170_p.func_175684_a(blockpos, BlocksTC.fluxGoo, 4);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 101 */     if (this.lightningState >= 0)
/*     */     {
/* 103 */       if (!this.field_70170_p.field_72995_K)
/*     */       {
/* 105 */         double d0 = 3.0D;
/* 106 */         List list = this.field_70170_p.func_72839_b(this, new net.minecraft.util.AxisAlignedBB(this.field_70165_t - d0, this.field_70163_u - d0, this.field_70161_v - d0, this.field_70165_t + d0, this.field_70163_u + 6.0D + d0, this.field_70161_v + d0));
/*     */         
/* 108 */         for (int i = 0; i < list.size(); i++)
/*     */         {
/* 110 */           Entity entity = (Entity)list.get(i);
/* 111 */           entity.func_70097_a(net.minecraft.util.DamageSource.field_180137_b, 3.0F);
/* 112 */           if ((entity instanceof EntityLivingBase)) {
/* 113 */             ((EntityLivingBase)entity).func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), 1200, 0, false, true));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {}
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound tagCompund) {}
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound tagCompound) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/EntityTaintSourceLightning.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */