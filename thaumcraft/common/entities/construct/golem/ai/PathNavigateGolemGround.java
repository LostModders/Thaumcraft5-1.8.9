/*     */ package thaumcraft.common.entities.construct.golem.ai;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class PathNavigateGolemGround extends PathNavigateGround
/*     */ {
/*     */   protected GolemNodeProcessor field_179695_a;
/*     */   private boolean field_179694_f;
/*     */   
/*     */   public PathNavigateGolemGround(EntityLiving p_i45875_1_, World worldIn)
/*     */   {
/*  24 */     super(p_i45875_1_, worldIn);
/*     */   }
/*     */   
/*     */ 
/*     */   protected net.minecraft.pathfinding.PathFinder func_179679_a()
/*     */   {
/*  30 */     this.field_179695_a = new GolemNodeProcessor();
/*  31 */     this.field_179695_a.func_176175_a(true);
/*  32 */     return new net.minecraft.pathfinding.PathFinder(this.field_179695_a);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private int func_179687_p()
/*     */   {
/*  39 */     if ((this.field_75515_a.func_70090_H()) && (func_179684_h()))
/*     */     {
/*  41 */       int i = (int)this.field_75515_a.func_174813_aQ().field_72338_b;
/*  42 */       Block block = this.field_75513_b.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, MathHelper.func_76128_c(this.field_75515_a.field_70161_v))).func_177230_c();
/*  43 */       int j = 0;
/*     */       
/*     */       do
/*     */       {
/*  47 */         if ((block != net.minecraft.init.Blocks.field_150358_i) && (block != net.minecraft.init.Blocks.field_150355_j))
/*     */         {
/*  49 */           return i;
/*     */         }
/*     */         
/*  52 */         i++;
/*  53 */         block = this.field_75513_b.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, MathHelper.func_76128_c(this.field_75515_a.field_70161_v))).func_177230_c();
/*  54 */         j++;
/*     */       }
/*  56 */       while (j <= 16);
/*     */       
/*  58 */       return (int)this.field_75515_a.func_174813_aQ().field_72338_b;
/*     */     }
/*     */     
/*     */ 
/*  62 */     return (int)(this.field_75515_a.func_174813_aQ().field_72338_b + 0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_75487_m()
/*     */   {
/*  69 */     super.func_75487_m();
/*     */     
/*  71 */     if (this.field_179694_f)
/*     */     {
/*  73 */       if (this.field_75513_b.func_175678_i(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), (int)(this.field_75515_a.func_174813_aQ().field_72338_b + 0.5D), MathHelper.func_76128_c(this.field_75515_a.field_70161_v))))
/*     */       {
/*  75 */         return;
/*     */       }
/*     */       
/*  78 */       for (int i = 0; i < this.field_75514_c.func_75874_d(); i++)
/*     */       {
/*  80 */         PathPoint pathpoint = this.field_75514_c.func_75877_a(i);
/*     */         
/*  82 */         if (this.field_75513_b.func_175678_i(new BlockPos(pathpoint.field_75839_a, pathpoint.field_75837_b, pathpoint.field_75838_c)))
/*     */         {
/*  84 */           this.field_75514_c.func_75871_b(i - 1);
/*  85 */           return;
/*     */         }
/*     */       }
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean func_179683_a(int p_179683_1_, int p_179683_2_, int p_179683_3_, int p_179683_4_, int p_179683_5_, int p_179683_6_, Vec3 p_179683_7_, double p_179683_8_, double p_179683_10_)
/*     */   {
/* 174 */     int k1 = p_179683_1_ - p_179683_4_ / 2;
/* 175 */     int l1 = p_179683_3_ - p_179683_6_ / 2;
/*     */     
/* 177 */     if (!func_179692_b(k1, p_179683_2_, l1, p_179683_4_, p_179683_5_, p_179683_6_, p_179683_7_, p_179683_8_, p_179683_10_))
/*     */     {
/* 179 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 183 */     for (int i2 = k1; i2 < k1 + p_179683_4_; i2++)
/*     */     {
/* 185 */       for (int j2 = l1; j2 < l1 + p_179683_6_; j2++)
/*     */       {
/* 187 */         double d2 = i2 + 0.5D - p_179683_7_.field_72450_a;
/* 188 */         double d3 = j2 + 0.5D - p_179683_7_.field_72449_c;
/*     */         
/* 190 */         if (d2 * p_179683_8_ + d3 * p_179683_10_ >= 0.0D)
/*     */         {
/* 192 */           Block block = this.field_75513_b.func_180495_p(new BlockPos(i2, p_179683_2_ - 1, j2)).func_177230_c();
/* 193 */           Material material = block.func_149688_o();
/*     */           
/* 195 */           if (material == Material.field_151579_a)
/*     */           {
/* 197 */             return false;
/*     */           }
/*     */           
/* 200 */           if ((material == Material.field_151586_h) && (!this.field_75515_a.func_70090_H()))
/*     */           {
/* 202 */             return false;
/*     */           }
/*     */           
/* 205 */           if (material == Material.field_151587_i)
/*     */           {
/* 207 */             return false;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 213 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean func_179692_b(int p_179692_1_, int p_179692_2_, int p_179692_3_, int p_179692_4_, int p_179692_5_, int p_179692_6_, Vec3 p_179692_7_, double p_179692_8_, double p_179692_10_)
/*     */   {
/* 219 */     Iterator iterator = BlockPos.func_177980_a(new BlockPos(p_179692_1_, p_179692_2_, p_179692_3_), new BlockPos(p_179692_1_ + p_179692_4_ - 1, p_179692_2_ + p_179692_5_ - 1, p_179692_3_ + p_179692_6_ - 1)).iterator();
/*     */     
/* 221 */     while (iterator.hasNext())
/*     */     {
/* 223 */       BlockPos blockpos = (BlockPos)iterator.next();
/* 224 */       double d2 = blockpos.func_177958_n() + 0.5D - p_179692_7_.field_72450_a;
/* 225 */       double d3 = blockpos.func_177952_p() + 0.5D - p_179692_7_.field_72449_c;
/*     */       
/* 227 */       if (d2 * p_179692_8_ + d3 * p_179692_10_ >= 0.0D)
/*     */       {
/* 229 */         Block block = this.field_75513_b.func_180495_p(blockpos).func_177230_c();
/*     */         
/* 231 */         if (!block.func_176205_b(this.field_75513_b, blockpos))
/*     */         {
/* 233 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 238 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_179690_a(boolean p_179690_1_)
/*     */   {
/* 244 */     this.field_179695_a.func_176176_c(p_179690_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_179689_e()
/*     */   {
/* 250 */     return this.field_179695_a.func_176173_e();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_179688_b(boolean p_179688_1_)
/*     */   {
/* 256 */     this.field_179695_a.func_176172_b(p_179688_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_179691_c(boolean p_179691_1_)
/*     */   {
/* 262 */     this.field_179695_a.func_176175_a(p_179691_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_179686_g()
/*     */   {
/* 268 */     return this.field_179695_a.func_176179_b();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_179693_d(boolean p_179693_1_)
/*     */   {
/* 274 */     this.field_179695_a.func_176178_d(p_179693_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_179684_h()
/*     */   {
/* 280 */     return this.field_179695_a.func_176174_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_179685_e(boolean p_179685_1_)
/*     */   {
/* 286 */     this.field_179694_f = p_179685_1_;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/PathNavigateGolemGround.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */