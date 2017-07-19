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
/*    */ public class GLEContext
/*    */ {
/* 33 */   public static final String VERSION = new String("$Id: GLEContext.java,v 1.1 1998/05/03 16:18:47 descarte Exp descarte $");
/*    */   
/*    */ 
/*    */ 
/* 37 */   private int joinStyle = 1;
/*    */   
/*    */ 
/*    */ 
/* 41 */   protected int ncp = 0;
/* 42 */   protected double[][] contour = (double[][])null;
/* 43 */   protected double[][] contourNormal = (double[][])null;
/* 44 */   protected double[] up = null;
/* 45 */   protected int npoints = 0;
/* 46 */   protected double[][] pointArray = (double[][])null;
/* 47 */   protected float[][] colourArray = (float[][])null;
/* 48 */   protected double[][][] xformArray = (double[][][])null;
/*    */   
/*    */   protected final int getJoinStyle()
/*    */   {
/* 52 */     return this.joinStyle;
/*    */   }
/*    */   
/*    */   protected final void setJoinStyle(int style)
/*    */   {
/* 57 */     this.joinStyle = style;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/com/sasmaster/glelwjgl/java/GLEContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */