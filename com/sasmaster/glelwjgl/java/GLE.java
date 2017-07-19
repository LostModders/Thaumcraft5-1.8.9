/*    */ package com.sasmaster.glelwjgl.java;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract interface GLE
/*    */ {
/* 43 */   public static final String VERSION = new String("$Id: GLE.java,v 1.3 1998/05/02 12:06:39 descarte Exp descarte $");
/*    */   public static final int SUMMARY = 0;
/*    */   public static final int VERBOSE = 1;
/*    */   public static final int TUBE_JN_RAW = 1;
/*    */   public static final int TUBE_JN_ANGLE = 2;
/*    */   public static final int TUBE_JN_CUT = 3;
/*    */   public static final int TUBE_JN_ROUND = 4;
/*    */   public static final int TUBE_JN_MASK = 15;
/*    */   public static final int TUBE_JN_CAP = 16;
/*    */   public static final int TUBE_NORM_FACET = 256;
/*    */   public static final int TUBE_NORM_EDGE = 512;
/*    */   public static final int TUBE_NORM_PATH_EDGE = 1024;
/*    */   public static final int TUBE_NORM_MASK = 3840;
/*    */   public static final int TUBE_CONTOUR_CLOSED = 4096;
/*    */   public static final int GLE_TEXTURE_ENABLE = 65536;
/*    */   public static final int GLE_TEXTURE_STYLE_MASK = 255;
/*    */   public static final int GLE_TEXTURE_VERTEX_FLAT = 1;
/*    */   public static final int GLE_TEXTURE_NORMAL_FLAT = 2;
/*    */   public static final int GLE_TEXTURE_VERTEX_CYL = 3;
/*    */   public static final int GLE_TEXTURE_NORMAL_CYL = 4;
/*    */   public static final int GLE_TEXTURE_VERTEX_SPH = 5;
/*    */   public static final int GLE_TEXTURE_NORMAL_SPH = 6;
/*    */   public static final int GLE_TEXTURE_VERTEX_MODEL_FLAT = 7;
/*    */   public static final int GLE_TEXTURE_NORMAL_MODEL_FLAT = 8;
/*    */   public static final int GLE_TEXTURE_VERTEX_MODEL_CYL = 9;
/*    */   public static final int GLE_TEXTURE_NORMAL_MODEL_CYL = 10;
/*    */   public static final int GLE_TEXTURE_VERTEX_MODEL_SPH = 11;
/*    */   public static final int GLE_TEXTURE_NORMAL_MODEL_SPH = 12;
/*    */   
/*    */   public abstract int gleGetJoinStyle();
/*    */   
/*    */   public abstract void gleSetJoinStyle(int paramInt);
/*    */   
/*    */   public abstract void gleTextureMode(int paramInt);
/*    */   
/*    */   public abstract void glePolyCylinder(int paramInt, double[][] paramArrayOfDouble, float[][] paramArrayOfFloat, double paramDouble, float paramFloat1, float paramFloat2)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void glePolyCone(int paramInt, double[][] paramArrayOfDouble, float[][] paramArrayOfFloat, double[] paramArrayOfDouble1, float paramFloat1, float paramFloat2)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void gleExtrusion(int paramInt1, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2, double[] paramArrayOfDouble, int paramInt2, double[][] paramArrayOfDouble3, float[][] paramArrayOfFloat)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void gleTwistExtrusion(int paramInt1, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt2, double[][] paramArrayOfDouble4, float[][] paramArrayOfFloat, double[] paramArrayOfDouble5)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void gleSuperExtrusion(int paramInt1, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2, double[] paramArrayOfDouble, int paramInt2, double[][] paramArrayOfDouble3, float[][] paramArrayOfFloat, double[][][] paramArrayOfDouble4)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void gleSpiral(int paramInt, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2, double[] paramArrayOfDouble, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double[][] paramArrayOfDouble3, double[][] paramArrayOfDouble4, double paramDouble5, double paramDouble6)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void gleLathe(int paramInt, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2, double[] paramArrayOfDouble, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double[][] paramArrayOfDouble3, double[][] paramArrayOfDouble4, double paramDouble5, double paramDouble6)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void gleHelicoid(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2, double paramDouble6, double paramDouble7)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void gleToroid(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2, double paramDouble6, double paramDouble7)
/*    */     throws GLEException;
/*    */   
/*    */   public abstract void gleScrew(int paramInt, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2, double[] paramArrayOfDouble, double paramDouble1, double paramDouble2, double paramDouble3)
/*    */     throws GLEException;
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/com/sasmaster/glelwjgl/java/GLE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */