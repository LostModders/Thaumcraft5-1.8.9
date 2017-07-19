/*     */ package thaumcraft.common.entities.construct.golem.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.block.BlockFence;
/*     */ import net.minecraft.block.BlockFenceGate;
/*     */ import net.minecraft.block.BlockRailBase;
/*     */ import net.minecraft.block.BlockWall;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.pathfinder.NodeProcessor;
/*     */ 
/*     */ public class GolemNodeProcessor extends NodeProcessor
/*     */ {
/*     */   private boolean field_176180_f;
/*     */   private boolean field_176181_g;
/*     */   private boolean field_176183_h;
/*     */   private boolean field_176184_i;
/*     */   private boolean field_176182_j;
/*     */   
/*     */   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_)
/*     */   {
/*  31 */     super.func_176162_a(p_176162_1_, p_176162_2_);
/*  32 */     this.field_176182_j = this.field_176183_h;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176163_a()
/*     */   {
/*  38 */     super.func_176163_a();
/*  39 */     this.field_176183_h = this.field_176182_j;
/*     */   }
/*     */   
/*     */ 
/*     */   public PathPoint func_176161_a(Entity p_176161_1_)
/*     */   {
/*     */     int i;
/*     */     
/*  47 */     if ((this.field_176184_i) && (p_176161_1_.func_70090_H()))
/*     */     {
/*  49 */       int i = (int)p_176161_1_.func_174813_aQ().field_72338_b;
/*     */       
/*  51 */       for (Block block = this.field_176169_a.func_180495_p(new BlockPos(MathHelper.func_76128_c(p_176161_1_.field_70165_t), i, MathHelper.func_76128_c(p_176161_1_.field_70161_v))).func_177230_c(); 
/*  52 */           (block == Blocks.field_150358_i) || (block == Blocks.field_150355_j); 
/*  53 */           block = this.field_176169_a.func_180495_p(new BlockPos(MathHelper.func_76128_c(p_176161_1_.field_70165_t), i, MathHelper.func_76128_c(p_176161_1_.field_70161_v))).func_177230_c())
/*     */       {
/*  55 */         i++;
/*     */       }
/*     */       
/*  58 */       this.field_176183_h = false;
/*     */     }
/*     */     else
/*     */     {
/*  62 */       i = MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72338_b + 0.5D);
/*     */     }
/*     */     
/*  65 */     return func_176159_a(MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72340_a), i, MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72339_c));
/*     */   }
/*     */   
/*     */ 
/*     */   public PathPoint func_176160_a(Entity p_176160_1_, double p_176160_2_, double p_176160_4_, double p_176160_6_)
/*     */   {
/*  71 */     return func_176159_a(MathHelper.func_76128_c(p_176160_2_ - p_176160_1_.field_70130_N / 2.0F), MathHelper.func_76128_c(p_176160_4_), MathHelper.func_76128_c(p_176160_6_ - p_176160_1_.field_70130_N / 2.0F));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176164_a(PathPoint[] p_176164_1_, Entity entity, PathPoint pp1, PathPoint pp2, float p_176164_5_)
/*     */   {
/*  77 */     int i = 0;
/*  78 */     byte b0 = 0;
/*     */     
/*  80 */     if (canWalkOver(entity, pp1.field_75839_a, pp1.field_75837_b + 1, pp1.field_75838_c) == 1)
/*     */     {
/*  82 */       b0 = 1;
/*  83 */       if (entity.field_70138_W < 0.6D) {
/*  84 */         b0 = 0;
/*     */       }
/*     */     }
/*     */     
/*  88 */     PathPoint pathpoint2 = func_176171_a(entity, pp1.field_75839_a, pp1.field_75837_b, pp1.field_75838_c + 1, b0);
/*  89 */     PathPoint pathpoint3 = func_176171_a(entity, pp1.field_75839_a - 1, pp1.field_75837_b, pp1.field_75838_c, b0);
/*  90 */     PathPoint pathpoint4 = func_176171_a(entity, pp1.field_75839_a + 1, pp1.field_75837_b, pp1.field_75838_c, b0);
/*  91 */     PathPoint pathpoint5 = func_176171_a(entity, pp1.field_75839_a, pp1.field_75837_b, pp1.field_75838_c - 1, b0);
/*     */     
/*  93 */     if ((pathpoint2 != null) && (!pathpoint2.field_75842_i) && (pathpoint2.func_75829_a(pp2) < p_176164_5_))
/*     */     {
/*  95 */       p_176164_1_[(i++)] = pathpoint2;
/*     */     }
/*     */     
/*  98 */     if ((pathpoint3 != null) && (!pathpoint3.field_75842_i) && (pathpoint3.func_75829_a(pp2) < p_176164_5_))
/*     */     {
/* 100 */       p_176164_1_[(i++)] = pathpoint3;
/*     */     }
/*     */     
/* 103 */     if ((pathpoint4 != null) && (!pathpoint4.field_75842_i) && (pathpoint4.func_75829_a(pp2) < p_176164_5_))
/*     */     {
/* 105 */       p_176164_1_[(i++)] = pathpoint4;
/*     */     }
/*     */     
/* 108 */     if ((pathpoint5 != null) && (!pathpoint5.field_75842_i) && (pathpoint5.func_75829_a(pp2) < p_176164_5_))
/*     */     {
/* 110 */       p_176164_1_[(i++)] = pathpoint5;
/*     */     }
/*     */     
/* 113 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   private PathPoint func_176171_a(Entity entity, int x, int y, int z, int step)
/*     */   {
/* 119 */     PathPoint pathpoint = null;
/* 120 */     int i1 = canWalkOver(entity, x, y, z);
/*     */     
/* 122 */     if (i1 == 2)
/*     */     {
/* 124 */       return func_176159_a(x, y, z);
/*     */     }
/*     */     
/*     */ 
/* 128 */     if (i1 == 1)
/*     */     {
/* 130 */       pathpoint = func_176159_a(x, y, z);
/*     */     }
/*     */     
/* 133 */     if ((pathpoint == null) && (step > 0) && (i1 != -3) && (i1 != -4) && (canWalkOver(entity, x, y + step, z) == 1))
/*     */     {
/* 135 */       pathpoint = func_176159_a(x, y + step, z);
/* 136 */       y += step;
/*     */     }
/*     */     
/* 139 */     if (pathpoint != null)
/*     */     {
/* 141 */       int j1 = 0;
/*     */       
/*     */ 
/* 144 */       for (int k1 = 0; y > 0; pathpoint = func_176159_a(x, y, z))
/*     */       {
/* 146 */         k1 = canWalkOver(entity, x, y - 1, z);
/*     */         
/* 148 */         if ((this.field_176183_h) && (k1 == -1))
/*     */         {
/* 150 */           return null;
/*     */         }
/*     */         
/* 153 */         if (k1 != 1) {
/*     */           break;
/*     */         }
/*     */         
/*     */ 
/* 158 */         if (j1++ >= entity.func_82143_as())
/*     */         {
/* 160 */           return null;
/*     */         }
/*     */         
/* 163 */         y--;
/*     */         
/* 165 */         if (y <= 0)
/*     */         {
/* 167 */           return null;
/*     */         }
/*     */       }
/*     */       
/* 171 */       if (k1 == -2)
/*     */       {
/* 173 */         return null;
/*     */       }
/*     */     }
/*     */     
/* 177 */     return pathpoint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private int canWalkOver(Entity p_176177_1_, int p_176177_2_, int p_176177_3_, int p_176177_4_)
/*     */   {
/* 184 */     return canWalkOver(this.field_176169_a, p_176177_1_, p_176177_2_, p_176177_3_, p_176177_4_, this.field_176168_c, this.field_176165_d, this.field_176166_e, this.field_176183_h, this.field_176181_g, this.field_176180_f);
/*     */   }
/*     */   
/*     */ 
/*     */   public static int canWalkOver(IBlockAccess ba, Entity entity, int x, int y, int z, int p_176170_5_, int p_176170_6_, int p_176170_7_, boolean p_176170_8_, boolean p_176170_9_, boolean p_176170_10_)
/*     */   {
/* 190 */     boolean flag3 = false;
/* 191 */     BlockPos blockpos = new BlockPos(entity);
/*     */     
/* 193 */     for (int k1 = x; k1 < x + p_176170_5_; k1++)
/*     */     {
/* 195 */       for (int l1 = y; l1 < y + p_176170_6_; l1++)
/*     */       {
/* 197 */         for (int i2 = z; i2 < z + p_176170_7_; i2++)
/*     */         {
/* 199 */           BlockPos blockpos1 = new BlockPos(k1, l1, i2);
/* 200 */           Block block = ba.func_180495_p(blockpos1).func_177230_c();
/*     */           
/* 202 */           if (block.func_149688_o() != Material.field_151579_a)
/*     */           {
/* 204 */             if ((block != Blocks.field_150415_aT) && (block != Blocks.field_180400_cw))
/*     */             {
/* 206 */               if ((block != Blocks.field_150358_i) && (block != Blocks.field_150355_j))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/* 211 */                 if ((!p_176170_10_) && ((block instanceof BlockDoor)) && (block.func_149688_o() == Material.field_151575_d))
/*     */                 {
/* 213 */                   return 0;
/*     */                 }
/*     */               }
/*     */               else
/*     */               {
/* 218 */                 if (p_176170_8_)
/*     */                 {
/*     */ 
/* 221 */                   return -1;
/*     */                 }
/*     */                 
/* 224 */                 flag3 = true;
/*     */               }
/*     */               
/*     */             }
/*     */             else {
/* 229 */               flag3 = true;
/*     */             }
/*     */             
/* 232 */             if ((entity.field_70170_p.func_180495_p(blockpos1).func_177230_c() instanceof BlockRailBase))
/*     */             {
/* 234 */               if ((!(entity.field_70170_p.func_180495_p(blockpos).func_177230_c() instanceof BlockRailBase)) && (!(entity.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c() instanceof BlockRailBase)))
/*     */               {
/*     */ 
/* 237 */                 return -3;
/*     */               }
/*     */             }
/* 240 */             else if ((!block.func_176205_b(ba, blockpos1)) && ((!p_176170_9_) || (!(block instanceof BlockDoor)) || (block.func_149688_o() != Material.field_151575_d)))
/*     */             {
/* 242 */               if (((block instanceof BlockFence)) || ((block instanceof BlockFenceGate)) || ((block instanceof BlockWall)))
/*     */               {
/* 244 */                 return -3;
/*     */               }
/*     */               
/* 247 */               if ((block == Blocks.field_150415_aT) || (block == Blocks.field_180400_cw))
/*     */               {
/* 249 */                 return -4;
/*     */               }
/*     */               
/* 252 */               Material material = block.func_149688_o();
/*     */               
/* 254 */               if (material != Material.field_151587_i)
/*     */               {
/* 256 */                 return 0;
/*     */               }
/*     */               
/* 259 */               if (!entity.func_180799_ab())
/*     */               {
/* 261 */                 return -2;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 269 */     return flag3 ? 2 : 1;
/*     */   }
/*     */   
/*     */   public void func_176175_a(boolean p_176175_1_)
/*     */   {
/* 274 */     this.field_176180_f = p_176175_1_;
/*     */   }
/*     */   
/*     */   public void func_176172_b(boolean p_176172_1_)
/*     */   {
/* 279 */     this.field_176181_g = p_176172_1_;
/*     */   }
/*     */   
/*     */   public void func_176176_c(boolean p_176176_1_)
/*     */   {
/* 284 */     this.field_176183_h = p_176176_1_;
/*     */   }
/*     */   
/*     */   public void func_176178_d(boolean p_176178_1_)
/*     */   {
/* 289 */     this.field_176184_i = p_176178_1_;
/*     */   }
/*     */   
/*     */   public boolean func_176179_b()
/*     */   {
/* 294 */     return this.field_176180_f;
/*     */   }
/*     */   
/*     */   public boolean func_176174_d()
/*     */   {
/* 299 */     return this.field_176184_i;
/*     */   }
/*     */   
/*     */   public boolean func_176173_e()
/*     */   {
/* 304 */     return this.field_176183_h;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/GolemNodeProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */