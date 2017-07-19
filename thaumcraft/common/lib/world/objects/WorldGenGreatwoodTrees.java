/*     */ package thaumcraft.common.lib.world.objects;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockChest;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraftforge.common.ChestGenHooks;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ 
/*     */ public class WorldGenGreatwoodTrees
/*     */   extends WorldGenAbstractTree
/*     */ {
/*  26 */   static final byte[] otherCoordPairs = { 2, 0, 0, 1, 2, 1 };
/*     */   
/*     */ 
/*  29 */   Random rand = new Random();
/*     */   
/*     */   World worldObj;
/*     */   
/*  33 */   int[] basePos = { 0, 0, 0 };
/*  34 */   int heightLimit = 0;
/*     */   int height;
/*  36 */   double heightAttenuation = 0.618D;
/*  37 */   double branchDensity = 1.0D;
/*  38 */   double branchSlope = 0.38D;
/*  39 */   double scaleWidth = 1.2D;
/*  40 */   double leafDensity = 0.9D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  45 */   int trunkSize = 2;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  50 */   int heightLimitLimit = 11;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  55 */   int leafDistanceLimit = 4;
/*     */   
/*     */ 
/*     */   int[][] leafNodes;
/*     */   
/*  60 */   boolean spiders = false;
/*     */   
/*     */   public WorldGenGreatwoodTrees(boolean par1, boolean spiders)
/*     */   {
/*  64 */     super(par1);
/*  65 */     this.spiders = spiders;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeList()
/*     */   {
/*  73 */     this.height = ((int)(this.heightLimit * this.heightAttenuation));
/*     */     
/*  75 */     if (this.height >= this.heightLimit)
/*     */     {
/*  77 */       this.height = (this.heightLimit - 1);
/*     */     }
/*     */     
/*  80 */     int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  82 */     if (var1 < 1)
/*     */     {
/*  84 */       var1 = 1;
/*     */     }
/*     */     
/*  87 */     int[][] var2 = new int[var1 * this.heightLimit][4];
/*  88 */     int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/*  89 */     int var4 = 1;
/*  90 */     int var5 = this.basePos[1] + this.height;
/*  91 */     int var6 = var3 - this.basePos[1];
/*  92 */     var2[0][0] = this.basePos[0];
/*  93 */     var2[0][1] = var3;
/*  94 */     var2[0][2] = this.basePos[2];
/*  95 */     var2[0][3] = var5;
/*  96 */     var3--;
/*     */     
/*  98 */     while (var6 >= 0)
/*     */     {
/* 100 */       int var7 = 0;
/* 101 */       float var8 = layerSize(var6);
/*     */       
/* 103 */       if (var8 < 0.0F)
/*     */       {
/* 105 */         var3--;
/* 106 */         var6--;
/*     */       }
/*     */       else
/*     */       {
/* 110 */         for (double var9 = 0.5D; var7 < var1; var7++)
/*     */         {
/* 112 */           double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
/* 113 */           double var13 = this.rand.nextFloat() * 2.0D * 3.141592653589793D;
/* 114 */           int var15 = MathHelper.func_76128_c(var11 * Math.sin(var13) + this.basePos[0] + var9);
/* 115 */           int var16 = MathHelper.func_76128_c(var11 * Math.cos(var13) + this.basePos[2] + var9);
/* 116 */           int[] var17 = { var15, var3, var16 };
/* 117 */           int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };
/*     */           
/* 119 */           if (checkBlockLine(var17, var18) == -1)
/*     */           {
/* 121 */             int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 122 */             double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
/* 123 */             double var22 = var20 * this.branchSlope;
/*     */             
/* 125 */             if (var17[1] - var22 > var5)
/*     */             {
/* 127 */               var19[1] = var5;
/*     */             }
/*     */             else
/*     */             {
/* 131 */               var19[1] = ((int)(var17[1] - var22));
/*     */             }
/*     */             
/* 134 */             if (checkBlockLine(var19, var17) == -1)
/*     */             {
/* 136 */               var2[var4][0] = var15;
/* 137 */               var2[var4][1] = var3;
/* 138 */               var2[var4][2] = var16;
/* 139 */               var2[var4][3] = var19[1];
/* 140 */               var4++;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 145 */         var3--;
/* 146 */         var6--;
/*     */       }
/*     */     }
/*     */     
/* 150 */     this.leafNodes = new int[var4][4];
/* 151 */     System.arraycopy(var2, 0, this.leafNodes, 0, var4);
/*     */   }
/*     */   
/*     */   void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block par6)
/*     */   {
/* 156 */     int var7 = (int)(par4 + 0.618D);
/* 157 */     byte var8 = otherCoordPairs[par5];
/* 158 */     byte var9 = otherCoordPairs[(par5 + 3)];
/* 159 */     int[] var10 = { par1, par2, par3 };
/* 160 */     int[] var11 = { 0, 0, 0 };
/* 161 */     int var12 = -var7;
/* 162 */     int var13 = -var7;
/*     */     
/* 164 */     for (var11[par5] = var10[par5]; var12 <= var7; var12++)
/*     */     {
/* 166 */       var10[var8] += var12;
/* 167 */       var13 = -var7;
/*     */       
/* 169 */       while (var13 <= var7)
/*     */       {
/* 171 */         double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
/*     */         
/* 173 */         if (var15 > par4 * par4)
/*     */         {
/* 175 */           var13++;
/*     */         }
/*     */         else
/*     */         {
/*     */           try {
/* 180 */             var10[var9] += var13;
/* 181 */             Block block = this.worldObj.func_180495_p(new BlockPos(var11[0], var11[1], var11[2])).func_177230_c();
/*     */             
/* 183 */             if ((block == Blocks.field_150350_a) || (block == BlocksTC.leaf))
/*     */             {
/*     */ 
/* 186 */               if ((block == null) || (block.canBeReplacedByLeaves(this.worldObj, new BlockPos(var11[0], var11[1], var11[2]))))
/*     */               {
/* 188 */                 func_175903_a(this.worldObj, new BlockPos(var11[0], var11[1], var11[2]), par6.func_176223_P());
/*     */               }
/*     */             }
/*     */           } catch (Exception e) {}
/* 192 */           var13++;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   float layerSize(int par1)
/*     */   {
/* 203 */     if (par1 < this.heightLimit * 0.3D)
/*     */     {
/* 205 */       return -1.618F;
/*     */     }
/*     */     
/*     */ 
/* 209 */     float var2 = this.heightLimit / 2.0F;
/* 210 */     float var3 = this.heightLimit / 2.0F - par1;
/*     */     float var4;
/*     */     float var4;
/* 213 */     if (var3 == 0.0F)
/*     */     {
/* 215 */       var4 = var2;
/*     */     } else { float var4;
/* 217 */       if (Math.abs(var3) >= var2)
/*     */       {
/* 219 */         var4 = 0.0F;
/*     */       }
/*     */       else
/*     */       {
/* 223 */         var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
/*     */       }
/*     */     }
/* 226 */     var4 *= 0.5F;
/* 227 */     return var4;
/*     */   }
/*     */   
/*     */ 
/*     */   float leafSize(int par1)
/*     */   {
/* 233 */     return (par1 >= 0) && (par1 < this.leafDistanceLimit) ? 2.0F : (par1 != 0) && (par1 != this.leafDistanceLimit - 1) ? 3.0F : -1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNode(int par1, int par2, int par3)
/*     */   {
/* 241 */     int var4 = par2;
/*     */     
/* 243 */     for (int var5 = par2 + this.leafDistanceLimit; var4 < var5; var4++)
/*     */     {
/* 245 */       float var6 = leafSize(var4 - par2);
/* 246 */       genTreeLayer(par1, var4, par3, var6, (byte)1, BlocksTC.leaf);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3)
/*     */   {
/* 255 */     int[] var4 = { 0, 0, 0 };
/* 256 */     byte var5 = 0;
/*     */     
/*     */ 
/* 259 */     for (byte var6 = 0; var5 < 3; var5 = (byte)(var5 + 1))
/*     */     {
/* 261 */       par2ArrayOfInteger[var5] -= par1ArrayOfInteger[var5];
/*     */       
/* 263 */       if (Math.abs(var4[var5]) > Math.abs(var4[var6]))
/*     */       {
/* 265 */         var6 = var5;
/*     */       }
/*     */     }
/*     */     
/* 269 */     if (var4[var6] != 0)
/*     */     {
/* 271 */       byte var7 = otherCoordPairs[var6];
/* 272 */       byte var8 = otherCoordPairs[(var6 + 3)];
/*     */       byte var9;
/*     */       byte var9;
/* 275 */       if (var4[var6] > 0)
/*     */       {
/* 277 */         var9 = 1;
/*     */       }
/*     */       else
/*     */       {
/* 281 */         var9 = -1;
/*     */       }
/*     */       
/* 284 */       double var10 = var4[var7] / var4[var6];
/* 285 */       double var12 = var4[var8] / var4[var6];
/* 286 */       int[] var14 = { 0, 0, 0 };
/* 287 */       int var15 = 0;
/*     */       
/* 289 */       for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9)
/*     */       {
/* 291 */         var14[var6] = MathHelper.func_76128_c(par1ArrayOfInteger[var6] + var15 + 0.5D);
/* 292 */         var14[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
/* 293 */         var14[var8] = MathHelper.func_76128_c(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
/* 294 */         byte var17 = 1;
/* 295 */         int var18 = Math.abs(var14[0] - par1ArrayOfInteger[0]);
/* 296 */         int var19 = Math.abs(var14[2] - par1ArrayOfInteger[2]);
/* 297 */         int var20 = Math.max(var18, var19);
/*     */         
/* 299 */         if (var20 > 0)
/*     */         {
/* 301 */           if (var18 == var20)
/*     */           {
/* 303 */             var17 = 0;
/*     */           }
/* 305 */           else if (var19 == var20)
/*     */           {
/* 307 */             var17 = 2;
/*     */           }
/*     */         }
/* 310 */         if (isReplaceable(this.worldObj, new BlockPos(var14[0], var14[1], var14[2]))) {
/* 311 */           func_175903_a(this.worldObj, new BlockPos(var14[0], var14[1], var14[2]), par3.func_176203_a(var17));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void generateLeaves()
/*     */   {
/* 321 */     int var1 = 0;
/*     */     
/* 323 */     for (int var2 = this.leafNodes.length; var1 < var2; var1++)
/*     */     {
/* 325 */       int var3 = this.leafNodes[var1][0];
/* 326 */       int var4 = this.leafNodes[var1][1];
/* 327 */       int var5 = this.leafNodes[var1][2];
/* 328 */       generateLeafNode(var3, var4, var5);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean leafNodeNeedsBase(int par1)
/*     */   {
/* 337 */     return par1 >= this.heightLimit * 0.2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateTrunk()
/*     */   {
/* 346 */     int var1 = this.basePos[0];
/* 347 */     int var2 = this.basePos[1];
/* 348 */     int var3 = this.basePos[1] + this.height;
/* 349 */     int var4 = this.basePos[2];
/* 350 */     int[] var5 = { var1, var2, var4 };
/* 351 */     int[] var6 = { var1, var3, var4 };
/* 352 */     placeBlockLine(var5, var6, BlocksTC.log);
/*     */     
/* 354 */     if (this.trunkSize == 2)
/*     */     {
/* 356 */       var5[0] += 1;
/* 357 */       var6[0] += 1;
/* 358 */       placeBlockLine(var5, var6, BlocksTC.log);
/* 359 */       var5[2] += 1;
/* 360 */       var6[2] += 1;
/* 361 */       placeBlockLine(var5, var6, BlocksTC.log);
/* 362 */       var5[0] += -1;
/* 363 */       var6[0] += -1;
/* 364 */       placeBlockLine(var5, var6, BlocksTC.log);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeBases()
/*     */   {
/* 373 */     int var1 = 0;
/* 374 */     int var2 = this.leafNodes.length;
/*     */     
/* 376 */     for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++)
/*     */     {
/* 378 */       int[] var4 = this.leafNodes[var1];
/* 379 */       int[] var5 = { var4[0], var4[1], var4[2] };
/* 380 */       var3[1] = var4[3];
/* 381 */       int var6 = var3[1] - this.basePos[1];
/*     */       
/* 383 */       if (leafNodeNeedsBase(var6))
/*     */       {
/* 385 */         placeBlockLine(var3, var5, BlocksTC.log);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger)
/*     */   {
/* 396 */     int[] var3 = { 0, 0, 0 };
/* 397 */     byte var4 = 0;
/*     */     
/*     */ 
/* 400 */     for (byte var5 = 0; var4 < 3; var4 = (byte)(var4 + 1))
/*     */     {
/* 402 */       par2ArrayOfInteger[var4] -= par1ArrayOfInteger[var4];
/*     */       
/* 404 */       if (Math.abs(var3[var4]) > Math.abs(var3[var5]))
/*     */       {
/* 406 */         var5 = var4;
/*     */       }
/*     */     }
/*     */     
/* 410 */     if (var3[var5] == 0)
/*     */     {
/* 412 */       return -1;
/*     */     }
/*     */     
/*     */ 
/* 416 */     byte var6 = otherCoordPairs[var5];
/* 417 */     byte var7 = otherCoordPairs[(var5 + 3)];
/*     */     byte var8;
/*     */     byte var8;
/* 420 */     if (var3[var5] > 0)
/*     */     {
/* 422 */       var8 = 1;
/*     */     }
/*     */     else
/*     */     {
/* 426 */       var8 = -1;
/*     */     }
/*     */     
/* 429 */     double var9 = var3[var6] / var3[var5];
/* 430 */     double var11 = var3[var7] / var3[var5];
/* 431 */     int[] var13 = { 0, 0, 0 };
/* 432 */     int var14 = 0;
/*     */     
/*     */ 
/* 435 */     for (int var15 = var3[var5] + var8; var14 != var15; var14 += var8)
/*     */     {
/* 437 */       par1ArrayOfInteger[var5] += var14;
/* 438 */       var13[var6] = MathHelper.func_76128_c(par1ArrayOfInteger[var6] + var14 * var9);
/* 439 */       var13[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var14 * var11);
/*     */       try {
/* 441 */         Block var16 = this.worldObj.func_180495_p(new BlockPos(var13[0], var13[1], var13[2])).func_177230_c();
/*     */         
/* 443 */         if ((var16 != Blocks.field_150350_a) && (var16 != BlocksTC.leaf)) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */     
/*     */ 
/* 451 */     return var14 == var15 ? -1 : Math.abs(var14);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean validTreeLocation(int x, int z)
/*     */   {
/* 461 */     int[] var1 = { this.basePos[0] + x, this.basePos[1], this.basePos[2] + z };
/* 462 */     int[] var2 = { this.basePos[0] + x, this.basePos[1] + this.heightLimit - 1, this.basePos[2] + z };
/*     */     try {
/* 464 */       Block var3 = this.worldObj.func_180495_p(new BlockPos(this.basePos[0] + x, this.basePos[1] - 1, this.basePos[2] + z)).func_177230_c();
/* 465 */       boolean isSoil = var3.canSustainPlant(this.worldObj, new BlockPos(this.basePos[0] + x, this.basePos[1] - 1, this.basePos[2] + z), EnumFacing.UP, (BlockSapling)Blocks.field_150345_g);
/* 466 */       if (!isSoil)
/*     */       {
/* 468 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 472 */       int var4 = checkBlockLine(var1, var2);
/*     */       
/* 474 */       if (var4 == -1)
/*     */       {
/* 476 */         return true;
/*     */       }
/* 478 */       if (var4 < 6)
/*     */       {
/* 480 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 484 */       this.heightLimit = var4;
/* 485 */       return true;
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/* 489 */     return false;
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
/*     */   public void setScale(double par1, double par3, double par5) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180709_b(World par1World, Random par2Random, BlockPos pos)
/*     */   {
/* 512 */     this.worldObj = par1World;
/* 513 */     long var6 = par2Random.nextLong();
/* 514 */     this.rand.setSeed(var6);
/* 515 */     this.basePos[0] = pos.func_177958_n();
/* 516 */     this.basePos[1] = pos.func_177956_o();
/* 517 */     this.basePos[2] = pos.func_177952_p();
/*     */     
/* 519 */     if (this.heightLimit == 0)
/*     */     {
/* 521 */       this.heightLimit = (this.heightLimitLimit + this.rand.nextInt(this.heightLimitLimit));
/*     */     }
/*     */     
/* 524 */     int x = 0;
/* 525 */     int z = 0;
/* 526 */     boolean valid = false;
/*     */     
/*     */ 
/* 529 */     for (int a = -1; a < 2; a++) {
/*     */       label196:
/* 531 */       for (int b = -1; b < 2; b++) {
/* 532 */         for (x = 0; x < this.trunkSize; x++) {
/* 533 */           for (z = 0; z < this.trunkSize; z++) {
/* 534 */             if (!validTreeLocation(x + a, z + b))
/*     */               break label196;
/*     */           }
/*     */         }
/* 538 */         valid = true;
/* 539 */         this.basePos[0] += a;
/* 540 */         this.basePos[2] += b;
/*     */         
/*     */         break label208;
/*     */       }
/*     */     }
/*     */     label208:
/* 546 */     if (!valid) { return false;
/*     */     }
/* 548 */     generateLeafNodeList();
/* 549 */     generateLeaves();
/* 550 */     generateLeafNodeBases();
/* 551 */     generateTrunk();
/*     */     
/* 553 */     this.scaleWidth = 1.66D;
/*     */     
/* 555 */     this.basePos[0] = pos.func_177958_n();
/* 556 */     this.basePos[1] = (pos.func_177956_o() + this.height);
/* 557 */     this.basePos[2] = pos.func_177952_p();
/* 558 */     generateLeafNodeList();
/* 559 */     generateLeaves();
/* 560 */     generateLeafNodeBases();
/* 561 */     generateTrunk();
/*     */     
/* 563 */     if (this.spiders) {
/* 564 */       this.worldObj.func_175656_a(pos.func_177977_b(), Blocks.field_150474_ac.func_176223_P());
/* 565 */       TileEntityMobSpawner var14 = (TileEntityMobSpawner)par1World.func_175625_s(pos.func_177977_b());
/* 566 */       if (var14 != null)
/*     */       {
/* 568 */         var14.func_145881_a().func_98272_a("CaveSpider");
/* 569 */         for (int a = 0; a < 50; a++) {
/* 570 */           int xx = pos.func_177958_n() - 7 + par2Random.nextInt(14);
/* 571 */           int yy = pos.func_177956_o() + par2Random.nextInt(10);
/* 572 */           int zz = pos.func_177952_p() - 7 + par2Random.nextInt(14);
/* 573 */           if ((par1World.func_175623_d(new BlockPos(xx, yy, zz))) && ((BlockUtils.isBlockTouching(par1World, new BlockPos(xx, yy, zz), BlocksTC.leaf)) || (BlockUtils.isBlockTouching(par1World, new BlockPos(xx, yy, zz), BlocksTC.log))))
/*     */           {
/*     */ 
/* 576 */             this.worldObj.func_175656_a(new BlockPos(xx, yy, zz), Blocks.field_150321_G.func_176223_P());
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 583 */         par1World.func_175656_a(pos.func_177979_c(2), Blocks.field_150486_ae.func_176223_P());
/* 584 */         TileEntityChest var16 = (TileEntityChest)par1World.func_175625_s(pos.func_177979_c(2));
/*     */         
/* 586 */         if (var16 != null)
/*     */         {
/* 588 */           ChestGenHooks loot = ChestGenHooks.getInfo("dungeonChest");
/* 589 */           WeightedRandomChestContent.func_177630_a(this.rand, loot.getItems(this.rand), var16, loot.getCount(this.rand));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 596 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/world/objects/WorldGenGreatwoodTrees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */