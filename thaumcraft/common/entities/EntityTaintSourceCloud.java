/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ public class EntityTaintSourceCloud extends EntityTaintSource
/*     */ {
/*     */   private int rainSoundCounter;
/*     */   
/*     */   public EntityTaintSourceCloud(World par1World)
/*     */   {
/*  25 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityTaintSourceCloud(World world, BlockPos pos, int lifespan) {
/*  29 */     super(world, pos, lifespan);
/*     */     
/*  31 */     func_70105_a(64.0F, 64.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  37 */     super.func_70088_a();
/*  38 */     if (this.field_70170_p.field_72995_K) {
/*  39 */       thaumcraft.client.lib.RenderEventHandler.clouds.put(Integer.valueOf(func_145782_y()), this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  46 */     super.func_70071_h_();
/*     */     
/*  48 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/*  50 */       addFluxRainParticles();
/*     */       
/*  52 */       addCloudparticles();
/*     */ 
/*     */ 
/*     */     }
/*  56 */     else if (this.field_70146_Z.nextInt(20) == 0) { createPools();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void addCloudparticles()
/*     */   {
/*  63 */     BlockPos blockpos = new BlockPos(this);
/*     */     
/*  65 */     byte b0 = 18;
/*     */     
/*  67 */     for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(1); a++) {
/*  68 */       BlockPos bp = blockpos.func_177982_a(this.field_70146_Z.nextInt(b0) - this.field_70146_Z.nextInt(b0), 1, this.field_70146_Z.nextInt(b0) - this.field_70146_Z.nextInt(b0));
/*  69 */       Thaumcraft.proxy.getFX().drawTaintCloudParticles(bp.func_177958_n() + this.field_70146_Z.nextFloat(), bp.func_177956_o() + this.field_70146_Z.nextFloat(), bp.func_177952_p() + this.field_70146_Z.nextFloat());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void createPools()
/*     */   {
/*  79 */     BlockPos blockpos = new BlockPos(this);
/*     */     
/*  81 */     byte b0 = 16;
/*     */     
/*  83 */     BlockPos blockpos1 = this.field_70170_p.func_175725_q(blockpos.func_177982_a(this.field_70146_Z.nextInt(b0) - this.field_70146_Z.nextInt(b0), 0, this.field_70146_Z.nextInt(b0) - this.field_70146_Z.nextInt(b0)));
/*     */     
/*     */ 
/*  86 */     if (BlockUtils.distance(new BlockPos(blockpos1.func_177958_n(), 0, blockpos1.func_177952_p()), new BlockPos(blockpos.func_177958_n(), 0, blockpos.func_177952_p())) < 256.0D)
/*     */     {
/*     */ 
/*     */ 
/*  90 */       BlockPos blockpos2 = blockpos1.func_177977_b();
/*  91 */       Block blockBelow = this.field_70170_p.func_180495_p(blockpos2).func_177230_c();
/*  92 */       Block block = this.field_70170_p.func_180495_p(blockpos1).func_177230_c();
/*     */       
/*  94 */       if (blockpos1.func_177956_o() <= blockpos.func_177956_o())
/*     */       {
/*     */ 
/*  97 */         if ((blockBelow.func_149688_o() != Material.field_151587_i) && (blockBelow.func_149688_o() != Material.field_151586_h) && (blockBelow.func_149688_o() != ThaumcraftMaterials.MATERIAL_TAINT) && (block.func_149688_o() != ThaumcraftMaterials.MATERIAL_TAINT) && (block.func_176200_f(this.field_70170_p, blockpos1)))
/*     */         {
/*     */ 
/*     */ 
/* 101 */           if (AuraHandler.drainAura(this.field_70170_p, blockpos1, thaumcraft.api.aspects.Aspect.FLUX, 1)) {
/* 102 */             this.field_70170_p.func_175656_a(blockpos1, thaumcraft.api.blocks.BlocksTC.fluxGoo.func_176223_P());
/*     */           } else {
/* 104 */             this.lifespan -= 20;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   private void addFluxRainParticles()
/*     */   {
/* 116 */     BlockPos blockpos = new BlockPos(this);
/*     */     
/* 118 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 120 */     byte b0 = 16;
/* 121 */     double d0 = 0.0D;
/* 122 */     double d1 = 0.0D;
/* 123 */     double d2 = 0.0D;
/* 124 */     int i = 0;
/* 125 */     int j = 5;
/*     */     
/* 127 */     if (mc.field_71474_y.field_74362_aa == 1)
/*     */     {
/* 129 */       j >>= 1;
/*     */     }
/* 131 */     else if (mc.field_71474_y.field_74362_aa == 2)
/*     */     {
/* 133 */       j = 0;
/*     */     }
/*     */     
/* 136 */     for (int k = 0; k < j; k++)
/*     */     {
/* 138 */       BlockPos blockpos1 = this.field_70170_p.func_175725_q(blockpos.func_177982_a(this.field_70146_Z.nextInt(b0) - this.field_70146_Z.nextInt(b0), 0, this.field_70146_Z.nextInt(b0) - this.field_70146_Z.nextInt(b0)));
/*     */       
/*     */ 
/* 141 */       if (BlockUtils.distance(new BlockPos(blockpos1.func_177958_n(), 0, blockpos1.func_177952_p()), new BlockPos(blockpos.func_177958_n(), 0, blockpos.func_177952_p())) <= 256.0D)
/*     */       {
/*     */ 
/*     */ 
/* 145 */         BlockPos blockpos2 = blockpos1.func_177977_b();
/* 146 */         Block block = this.field_70170_p.func_180495_p(blockpos2).func_177230_c();
/*     */         
/* 148 */         if ((blockpos1.func_177956_o() <= blockpos.func_177956_o() + b0) && (blockpos1.func_177956_o() >= blockpos.func_177956_o() - b0))
/*     */         {
/* 150 */           float f1 = this.field_70146_Z.nextFloat();
/* 151 */           float f2 = this.field_70146_Z.nextFloat();
/*     */           
/* 153 */           if (block.func_149688_o() == Material.field_151587_i)
/*     */           {
/* 155 */             this.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, blockpos1.func_177958_n() + f1, blockpos1.func_177956_o() + 0.1F - block.func_149665_z(), blockpos1.func_177952_p() + f2, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           }
/* 157 */           else if (block.func_149688_o() != Material.field_151579_a)
/*     */           {
/* 159 */             block.func_180654_a(this.field_70170_p, blockpos2);
/* 160 */             i++;
/*     */             
/* 162 */             if (this.field_70146_Z.nextInt(i) == 0)
/*     */             {
/* 164 */               d0 = blockpos2.func_177958_n() + f1;
/* 165 */               d1 = blockpos2.func_177956_o() + 0.1F + block.func_149669_A() - 1.0D;
/* 166 */               d2 = blockpos2.func_177952_p() + f2;
/*     */             }
/*     */             
/* 169 */             Thaumcraft.proxy.getFX().fluxRainSplashFX(blockpos1);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 175 */     if ((i > 0) && (this.field_70146_Z.nextInt(3) < this.rainSoundCounter++))
/*     */     {
/* 177 */       this.rainSoundCounter = 0;
/*     */       
/* 179 */       if ((d1 > blockpos.func_177956_o() + 1) && (this.field_70170_p.func_175725_q(blockpos).func_177956_o() > net.minecraft.util.MathHelper.func_76141_d(blockpos.func_177956_o())))
/*     */       {
/* 181 */         this.field_70170_p.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.1F, 0.5F, false);
/*     */       }
/*     */       else
/*     */       {
/* 185 */         this.field_70170_p.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.2F, 1.0F, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/EntityTaintSourceCloud.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */