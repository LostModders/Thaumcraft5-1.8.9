/*     */ package thaumcraft.common.lib.world.objects;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class WorldGenSilverwoodTrees extends WorldGenAbstractTree
/*     */ {
/*     */   private final int minTreeHeight;
/*     */   private final int randomTreeHeight;
/*  21 */   boolean worldgen = false;
/*     */   
/*     */   public WorldGenSilverwoodTrees(boolean doBlockNotify, int minTreeHeight, int randomTreeHeight)
/*     */   {
/*  25 */     super(doBlockNotify);
/*     */     
/*  27 */     this.worldgen = (!doBlockNotify);
/*  28 */     this.minTreeHeight = minTreeHeight;
/*  29 */     this.randomTreeHeight = randomTreeHeight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180709_b(World world, Random random, BlockPos pos)
/*     */   {
/*  37 */     int height = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;
/*     */     
/*  39 */     boolean flag = true;
/*     */     
/*  41 */     int x = pos.func_177958_n();
/*  42 */     int y = pos.func_177956_o();
/*  43 */     int z = pos.func_177952_p();
/*     */     
/*  45 */     if ((y >= 1) && (y + height + 1 <= 256))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  50 */       for (int i1 = y; i1 <= y + 1 + height; i1++)
/*     */       {
/*  52 */         byte spread = 1;
/*     */         
/*  54 */         if (i1 == y)
/*     */         {
/*  56 */           spread = 0;
/*     */         }
/*     */         
/*  59 */         if (i1 >= y + 1 + height - 2)
/*     */         {
/*  61 */           spread = 3;
/*     */         }
/*     */         
/*  64 */         for (int j1 = x - spread; (j1 <= x + spread) && (flag); j1++)
/*     */         {
/*  66 */           for (int k1 = z - spread; (k1 <= z + spread) && (flag); k1++)
/*     */           {
/*  68 */             if ((i1 >= 0) && (i1 < 256))
/*     */             {
/*  70 */               Block block = world.func_180495_p(new BlockPos(j1, i1, k1)).func_177230_c();
/*     */               
/*  72 */               if ((!block.isAir(world, new BlockPos(j1, i1, k1))) && (!block.isLeaves(world, new BlockPos(j1, i1, k1))) && (!block.func_176200_f(world, new BlockPos(j1, i1, k1))))
/*     */               {
/*     */ 
/*  75 */                 if (i1 > y)
/*     */                 {
/*  77 */                   flag = false;
/*     */                 }
/*     */               }
/*     */             }
/*     */             else
/*     */             {
/*  83 */               flag = false;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  89 */       if (!flag)
/*     */       {
/*  91 */         return false;
/*     */       }
/*     */       
/*     */ 
/*  95 */       Block block1 = world.func_180495_p(new BlockPos(x, y - 1, z)).func_177230_c();
/*     */       
/*  97 */       boolean isSoil = block1.canSustainPlant(world, new BlockPos(x, y - 1, z), EnumFacing.UP, (BlockSapling)Blocks.field_150345_g);
/*  98 */       if ((isSoil) && (y < 256 - height - 1))
/*     */       {
/* 100 */         block1.onPlantGrow(world, new BlockPos(x, y - 1, z), new BlockPos(x, y, z));
/* 101 */         int start = y + height - 5;
/* 102 */         int end = y + height + 3 + random.nextInt(3);
/*     */         
/*     */ 
/* 105 */         for (int k2 = start; k2 <= end; k2++)
/*     */         {
/* 107 */           int cty = MathHelper.func_76125_a(k2, y + height - 3, y + height);
/*     */           
/* 109 */           for (int xx = x - 5; xx <= x + 5; xx++)
/*     */           {
/* 111 */             for (int zz = z - 5; zz <= z + 5; zz++)
/*     */             {
/* 113 */               double d3 = xx - x;
/* 114 */               double d4 = k2 - cty;
/* 115 */               double d5 = zz - z;
/* 116 */               double dist = d3 * d3 + d4 * d4 + d5 * d5;
/*     */               
/* 118 */               if ((dist < 10 + random.nextInt(8)) && (world.func_180495_p(new BlockPos(xx, k2, zz)).func_177230_c().canBeReplacedByLeaves(world, new BlockPos(xx, k2, zz))))
/*     */               {
/*     */ 
/* 121 */                 func_175903_a(world, new BlockPos(xx, k2, zz), BlocksTC.leaf.func_176203_a(1));
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 130 */         for (k2 = 0; k2 < height; k2++)
/*     */         {
/* 132 */           Block block2 = world.func_180495_p(new BlockPos(x, y + k2, z)).func_177230_c();
/* 133 */           if ((block2.isAir(world, new BlockPos(x, y + k2, z))) || (block2.isLeaves(world, new BlockPos(x, y + k2, z))) || (block2.func_176200_f(world, new BlockPos(x, y + k2, z))))
/*     */           {
/*     */ 
/* 136 */             func_175903_a(world, new BlockPos(x, y + k2, z), BlocksTC.log.func_176203_a(4));
/* 137 */             func_175903_a(world, new BlockPos(x - 1, y + k2, z), BlocksTC.log.func_176203_a(4));
/* 138 */             func_175903_a(world, new BlockPos(x + 1, y + k2, z), BlocksTC.log.func_176203_a(4));
/* 139 */             func_175903_a(world, new BlockPos(x, y + k2, z - 1), BlocksTC.log.func_176203_a(4));
/* 140 */             func_175903_a(world, new BlockPos(x, y + k2, z + 1), BlocksTC.log.func_176203_a(4));
/*     */           }
/*     */         }
/*     */         
/* 144 */         if (random.nextFloat() < 0.3F) {
/* 145 */           int q = height / 2 - 1 + random.nextInt(3);
/* 146 */           ThaumcraftWorldGenerator.spawnNode(world, new BlockPos(x, y + q, z), 3, 0.33F);
/*     */         }
/*     */         
/* 149 */         func_175903_a(world, new BlockPos(x, y + k2, z), BlocksTC.log.func_176203_a(4));
/* 150 */         func_175903_a(world, new BlockPos(x - 1, y, z - 1), BlocksTC.log.func_176203_a(4));
/* 151 */         func_175903_a(world, new BlockPos(x + 1, y, z + 1), BlocksTC.log.func_176203_a(4));
/* 152 */         func_175903_a(world, new BlockPos(x - 1, y, z + 1), BlocksTC.log.func_176203_a(4));
/* 153 */         func_175903_a(world, new BlockPos(x + 1, y, z - 1), BlocksTC.log.func_176203_a(4));
/* 154 */         if (random.nextInt(3) != 0) func_175903_a(world, new BlockPos(x - 1, y + 1, z - 1), BlocksTC.log.func_176203_a(4));
/* 155 */         if (random.nextInt(3) != 0) func_175903_a(world, new BlockPos(x + 1, y + 1, z + 1), BlocksTC.log.func_176203_a(4));
/* 156 */         if (random.nextInt(3) != 0) func_175903_a(world, new BlockPos(x - 1, y + 1, z + 1), BlocksTC.log.func_176203_a(4));
/* 157 */         if (random.nextInt(3) != 0) func_175903_a(world, new BlockPos(x + 1, y + 1, z - 1), BlocksTC.log.func_176203_a(4));
/* 158 */         func_175903_a(world, new BlockPos(x - 2, y, z), BlocksTC.log.func_176203_a(3));
/* 159 */         func_175903_a(world, new BlockPos(x + 2, y, z), BlocksTC.log.func_176203_a(3));
/* 160 */         func_175903_a(world, new BlockPos(x, y, z - 2), BlocksTC.log.func_176203_a(5));
/* 161 */         func_175903_a(world, new BlockPos(x, y, z + 2), BlocksTC.log.func_176203_a(5));
/* 162 */         func_175903_a(world, new BlockPos(x - 2, y - 1, z), BlocksTC.log.func_176203_a(4));
/* 163 */         func_175903_a(world, new BlockPos(x + 2, y - 1, z), BlocksTC.log.func_176203_a(4));
/* 164 */         func_175903_a(world, new BlockPos(x, y - 1, z - 2), BlocksTC.log.func_176203_a(4));
/* 165 */         func_175903_a(world, new BlockPos(x, y - 1, z + 2), BlocksTC.log.func_176203_a(4));
/* 166 */         func_175903_a(world, new BlockPos(x - 1, y + (height - 4), z - 1), BlocksTC.log.func_176203_a(4));
/* 167 */         func_175903_a(world, new BlockPos(x + 1, y + (height - 4), z + 1), BlocksTC.log.func_176203_a(4));
/* 168 */         func_175903_a(world, new BlockPos(x - 1, y + (height - 4), z + 1), BlocksTC.log.func_176203_a(4));
/* 169 */         func_175903_a(world, new BlockPos(x + 1, y + (height - 4), z - 1), BlocksTC.log.func_176203_a(4));
/* 170 */         if (random.nextInt(3) == 0) func_175903_a(world, new BlockPos(x - 1, y + (height - 5), z - 1), BlocksTC.log.func_176203_a(4));
/* 171 */         if (random.nextInt(3) == 0) func_175903_a(world, new BlockPos(x + 1, y + (height - 5), z + 1), BlocksTC.log.func_176203_a(4));
/* 172 */         if (random.nextInt(3) == 0) func_175903_a(world, new BlockPos(x - 1, y + (height - 5), z + 1), BlocksTC.log.func_176203_a(4));
/* 173 */         if (random.nextInt(3) == 0) func_175903_a(world, new BlockPos(x + 1, y + (height - 5), z - 1), BlocksTC.log.func_176203_a(4));
/* 174 */         func_175903_a(world, new BlockPos(x - 2, y + (height - 4), z), BlocksTC.log.func_176203_a(3));
/* 175 */         func_175903_a(world, new BlockPos(x + 2, y + (height - 4), z), BlocksTC.log.func_176203_a(3));
/* 176 */         func_175903_a(world, new BlockPos(x, y + (height - 4), z - 2), BlocksTC.log.func_176203_a(5));
/* 177 */         func_175903_a(world, new BlockPos(x, y + (height - 4), z + 2), BlocksTC.log.func_176203_a(5));
/*     */         
/* 179 */         if (this.worldgen) {
/* 180 */           WorldGenerator flowers = new WorldGenCustomFlowers(BlocksTC.shimmerleaf, 0);
/* 181 */           flowers.func_180709_b(world, random, new BlockPos(x, y, z));
/*     */         }
/*     */         
/* 184 */         return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 190 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 196 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/objects/WorldGenSilverwoodTrees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */