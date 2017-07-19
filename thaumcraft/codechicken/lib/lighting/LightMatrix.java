/*     */ package thaumcraft.codechicken.lib.lighting;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import thaumcraft.codechicken.lib.colour.ColourRGBA;
/*     */ import thaumcraft.codechicken.lib.render.CCRenderPipeline;
/*     */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*     */ import thaumcraft.codechicken.lib.render.CCRenderState.IVertexOperation;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ 
/*     */ public class LightMatrix implements CCRenderState.IVertexOperation
/*     */ {
/*  16 */   public static final int operationIndex = ;
/*     */   
/*  18 */   public int computed = 0;
/*  19 */   public float[][] ao = new float[13][4];
/*  20 */   public int[][] brightness = new int[13][4];
/*     */   
/*     */   public IBlockAccess access;
/*  23 */   public BlockCoord pos = new BlockCoord();
/*     */   
/*  25 */   private int sampled = 0;
/*  26 */   private float[] aSamples = new float[27];
/*  27 */   private int[] bSamples = new int[27];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  32 */   public static final int[][] ssamplem = { { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 18, 19, 20, 21, 22, 23, 24, 25, 26 }, { 0, 9, 18, 1, 10, 19, 2, 11, 20 }, { 6, 15, 24, 7, 16, 25, 8, 17, 26 }, { 0, 3, 6, 9, 12, 15, 18, 21, 24 }, { 2, 5, 8, 11, 14, 17, 20, 23, 26 }, { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, { 3, 12, 21, 4, 13, 22, 5, 14, 23 }, { 3, 12, 21, 4, 13, 22, 5, 14, 23 }, { 1, 4, 7, 10, 13, 16, 19, 22, 25 }, { 1, 4, 7, 10, 13, 16, 19, 22, 25 }, { 13, 13, 13, 13, 13, 13, 13, 13, 13 } };
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
/*  46 */   public static final int[][] qsamplem = { { 0, 1, 3, 4 }, { 5, 1, 2, 4 }, { 6, 7, 3, 4 }, { 5, 7, 8, 4 } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  51 */   public static final float[] sideao = { 0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F, 0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F, 1.0F };
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
/*     */   public void locate(IBlockAccess a, int x, int y, int z)
/*     */   {
/*  79 */     this.access = a;
/*  80 */     this.pos.set(x, y, z);
/*  81 */     this.computed = 0;
/*  82 */     this.sampled = 0;
/*     */   }
/*     */   
/*     */   public void sample(int i) {
/*  86 */     if ((this.sampled & 1 << i) == 0) {
/*  87 */       int x = this.pos.x + i % 3 - 1;
/*  88 */       int y = this.pos.y + i / 9 - 1;
/*  89 */       int z = this.pos.z + i / 3 % 3 - 1;
/*  90 */       IBlockState b = this.access.func_180495_p(new BlockPos(x, y, z));
/*  91 */       this.bSamples[i] = this.access.func_175626_b(new BlockPos(x, y, z), b.func_177230_c().getLightValue(this.access, new BlockPos(x, y, z)));
/*  92 */       this.aSamples[i] = b.func_177230_c().func_149685_I();
/*     */     }
/*     */   }
/*     */   
/*     */   public int[] brightness(int side) {
/*  97 */     sideSample(side);
/*  98 */     return this.brightness[side];
/*     */   }
/*     */   
/*     */   public float[] ao(int side) {
/* 102 */     sideSample(side);
/* 103 */     return this.ao[side];
/*     */   }
/*     */   
/*     */   public void sideSample(int side) {
/* 107 */     if ((this.computed & 1 << side) == 0) {
/* 108 */       int[] ssample = ssamplem[side];
/* 109 */       for (int q = 0; q < 4; q++) {
/* 110 */         int[] qsample = qsamplem[q];
/* 111 */         if (Minecraft.func_71379_u()) {
/* 112 */           interp(side, q, ssample[qsample[0]], ssample[qsample[1]], ssample[qsample[2]], ssample[qsample[3]]);
/*     */         } else
/* 114 */           interp(side, q, ssample[4], ssample[4], ssample[4], ssample[4]);
/*     */       }
/* 116 */       this.computed |= 1 << side;
/*     */     }
/*     */   }
/*     */   
/*     */   private void interp(int s, int q, int a, int b, int c, int d) {
/* 121 */     sample(a);sample(b);sample(c);sample(d);
/* 122 */     this.ao[s][q] = (interpAO(this.aSamples[a], this.aSamples[b], this.aSamples[c], this.aSamples[d]) * sideao[s]);
/* 123 */     this.brightness[s][q] = interpBrightness(this.bSamples[a], this.bSamples[b], this.bSamples[c], this.bSamples[d]);
/*     */   }
/*     */   
/*     */   public static float interpAO(float a, float b, float c, float d) {
/* 127 */     return (a + b + c + d) / 4.0F;
/*     */   }
/*     */   
/*     */   public static int interpBrightness(int a, int b, int c, int d) {
/* 131 */     if (a == 0)
/* 132 */       a = d;
/* 133 */     if (b == 0)
/* 134 */       b = d;
/* 135 */     if (c == 0)
/* 136 */       c = d;
/* 137 */     return a + b + c + d >> 2 & 0xFF00FF;
/*     */   }
/*     */   
/*     */   public boolean load()
/*     */   {
/* 142 */     CCRenderState.pipeline.addDependency(CCRenderState.colourAttrib);
/* 143 */     CCRenderState.pipeline.addDependency(CCRenderState.lightCoordAttrib);
/* 144 */     return true;
/*     */   }
/*     */   
/*     */   public void operate()
/*     */   {
/* 149 */     LC lc = CCRenderState.lc;
/* 150 */     float[] a = ao(lc.side);
/* 151 */     float f = a[0] * lc.fa + a[1] * lc.fb + a[2] * lc.fc + a[3] * lc.fd;
/* 152 */     int[] b = brightness(lc.side);
/* 153 */     CCRenderState.setColour(ColourRGBA.multiplyC(CCRenderState.colour, f));
/* 154 */     CCRenderState.setBrightness((int)(b[0] * lc.fa + b[1] * lc.fb + b[2] * lc.fc + b[3] * lc.fd) & 0xFF00FF);
/*     */   }
/*     */   
/*     */   public int operationID()
/*     */   {
/* 159 */     return operationIndex;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/codechicken/lib/lighting/LightMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */