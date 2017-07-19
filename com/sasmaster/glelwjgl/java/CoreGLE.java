/*      */ package com.sasmaster.glelwjgl.java;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.nio.DoubleBuffer;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.util.glu.GLU;
/*      */ import org.lwjgl.util.glu.GLUtessellator;
/*      */ import org.lwjgl.util.glu.GLUtessellatorCallback;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CoreGLE
/*      */   implements GLE
/*      */ {
/*   57 */   public static final String VERSION = new String("$Id: CoreGLE.java,v 1.5 1998/05/20 00:19:43 descarte Exp descarte $");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   62 */   private static final String GLE_VERSION = new String("095");
/*      */   
/*      */ 
/*      */ 
/*   66 */   private final GLEContext context_ = new GLEContext();
/*      */   
/*      */ 
/*      */ 
/*   70 */   private int _POLYCYL_TESS = 20;
/*      */   
/*   72 */   public void set_POLYCYL_TESS(int _POLYCYL_TESS) { this._POLYCYL_TESS = _POLYCYL_TESS; }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   80 */   private int __ROUND_TESS_PIECES = 5;
/*      */   
/*   82 */   public void set__ROUND_TESS_PIECES(int __ROUND_TESS_PIECES) { this.__ROUND_TESS_PIECES = __ROUND_TESS_PIECES; }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   91 */   private static GLU glu_ = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   97 */   private tessellCallBack tessCallback = new tessellCallBack(glu_);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int gleGetJoinStyle()
/*      */   {
/*  110 */     return this.context_.getJoinStyle();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleSetJoinStyle(int style)
/*      */   {
/*  119 */     this.context_.setJoinStyle(style);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  130 */   private float SLICE = 1.0F;
/*  131 */   private float SLICE_PROGRESS = 0.0F;
/*      */   
/*      */ 
/*      */ 
/*      */   public void gleTextureMode(int mode) {}
/*      */   
/*      */ 
/*      */ 
/*      */   private void gen_polycone(int npoints, double[][] pointArray, float[][] colourArray, double radius, double[][][] xformArray, float texSlice, float start)
/*      */   {
/*  141 */     this.SLICE = texSlice;
/*  142 */     this.SLICE_PROGRESS = start;
/*      */     
/*  144 */     double[][] circle = new double[this._POLYCYL_TESS][2];
/*  145 */     double[][] norm = new double[this._POLYCYL_TESS][2];
/*      */     
/*  147 */     double[] v21 = new double[3];
/*  148 */     double len = 0.0D;
/*  149 */     double[] up = new double[3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  156 */     if (xformArray != null) {
/*  157 */       radius = 1.0D;
/*      */     }
/*  159 */     double s = Math.sin(6.283185307179586D / this._POLYCYL_TESS);
/*  160 */     double c = Math.cos(6.283185307179586D / this._POLYCYL_TESS);
/*      */     
/*  162 */     norm[0][0] = 1.0D;
/*  163 */     norm[0][1] = 0.0D;
/*  164 */     circle[0][0] = radius;
/*  165 */     circle[0][1] = 0.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  170 */     for (int i = 1; i < this._POLYCYL_TESS; i++) {
/*  171 */       norm[i][0] = (norm[(i - 1)][0] * c - norm[(i - 1)][1] * s);
/*  172 */       norm[i][1] = (norm[(i - 1)][0] * s + norm[(i - 1)][1] * c);
/*  173 */       circle[i][0] = (radius * norm[i][0]);
/*  174 */       circle[i][1] = (radius * norm[i][1]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  183 */     i = 0;
/*  184 */     i = intersect.FIND_NON_DEGENERATE_POINT(i, npoints, len, v21, pointArray);
/*      */     
/*  186 */     len = matrix.VEC_LENGTH(v21);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  191 */     if (i == npoints) {
/*  192 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  198 */     if ((v21[0] == 0.0D) && (v21[2] == 0.0D)) {
/*  199 */       up[0] = (up[1] = up[2] = 1.0D);
/*      */     } else {
/*  201 */       up[0] = (up[2] = 0.0D);
/*  202 */       up[1] = 1.0D;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  208 */     int savedStyle = gleGetJoinStyle();
/*  209 */     gleSetJoinStyle(savedStyle | 0x1000);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  215 */     if (!GL11.glIsEnabled(2896)) {
/*  216 */       gleSuperExtrusion(this._POLYCYL_TESS, circle, (double[][])null, up, npoints, pointArray, colourArray, xformArray);
/*      */     }
/*      */     else
/*      */     {
/*  220 */       gleSuperExtrusion(this._POLYCYL_TESS, circle, norm, up, npoints, pointArray, colourArray, xformArray);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  228 */     gleSetJoinStyle(savedStyle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void glePolyCylinder(int npoints, double[][] pointArray, float[][] colourArray, double radius, float texSlice, float start)
/*      */     throws GLEException
/*      */   {
/*  248 */     gen_polycone(npoints, pointArray, colourArray, radius, (double[][][])null, texSlice, start);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void glePolyCone(int npoints, double[][] pointArray, float[][] colourArray, double[] radiusArray, float texSlice, float start)
/*      */     throws GLEException
/*      */   {
/*  266 */     double[][][] xforms = new double[npoints][2][3];
/*  267 */     for (int i = 0; i < npoints; i++) {
/*  268 */       xforms[i][0][0] = radiusArray[i];
/*  269 */       xforms[i][0][1] = 0.0D;
/*  270 */       xforms[i][0][2] = 0.0D;
/*  271 */       xforms[i][1][0] = 0.0D;
/*  272 */       xforms[i][1][1] = radiusArray[i];
/*  273 */       xforms[i][1][2] = 0.0D;
/*      */     }
/*  275 */     gen_polycone(npoints, pointArray, colourArray, 1.0D, xforms, texSlice, start);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleExtrusion(int ncp, double[][] contour, double[][] contourNormal, double[] up, int npoints, double[][] pointArray, float[][] colourArray)
/*      */     throws GLEException
/*      */   {
/*  301 */     gleSuperExtrusion(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, (double[][][])null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleTwistExtrusion(int ncp, double[][] contour, double[][] contourNormal, double[] up, int npoints, double[][] pointArray, float[][] colourArray, double[] twistArray)
/*      */     throws GLEException
/*      */   {
/*  332 */     double[][][] xforms = new double[npoints][2][3];
/*  333 */     double angle = 0.0D;
/*  334 */     double si = 0.0D;
/*  335 */     double co = 0.0D;
/*      */     
/*  337 */     for (int j = 0; j < npoints; j++) {
/*  338 */       angle = 0.017453292519943295D * twistArray[j];
/*  339 */       si = Math.sin(angle);
/*  340 */       co = Math.cos(angle);
/*  341 */       xforms[j][0][0] = co;
/*  342 */       xforms[j][0][1] = (-si);
/*  343 */       xforms[j][0][2] = 0.0D;
/*  344 */       xforms[j][1][0] = si;
/*  345 */       xforms[j][1][1] = co;
/*  346 */       xforms[j][1][2] = 0.0D;
/*      */     }
/*  348 */     gleSuperExtrusion(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, xforms);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleSuperExtrusion(int ncp, double[][] contour, double[][] contourNormal, double[] up, int npoints, double[][] pointArray, float[][] colourArray, double[][][] xformArray)
/*      */     throws GLEException
/*      */   {
/*  382 */     this.context_.ncp = ncp;
/*  383 */     this.context_.contour = contour;
/*  384 */     this.context_.contourNormal = contourNormal;
/*  385 */     this.context_.up = up;
/*  386 */     this.context_.npoints = npoints;
/*  387 */     this.context_.pointArray = pointArray;
/*  388 */     this.context_.colourArray = colourArray;
/*  389 */     this.context_.xformArray = xformArray;
/*      */     
/*  391 */     switch (gleGetJoinStyle() & 0xF) {
/*      */     case 1: 
/*  393 */       extrusion_raw_join(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, xformArray);
/*      */       
/*      */ 
/*  396 */       break;
/*      */     
/*      */     case 2: 
/*  399 */       extrusion_angle_join(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, xformArray);
/*      */       
/*      */ 
/*  402 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 3: 
/*      */     case 4: 
/*  409 */       extrusion_round_or_cut_join(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, xformArray);
/*      */       
/*      */ 
/*  412 */       break;
/*      */     
/*      */     default: 
/*  415 */       throw new GLEException("Join style is complete rubbish!");
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleSpiral(int ncp, double[][] contour, double[][] contourNormal, double[] up, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta)
/*      */     throws GLEException
/*      */   {
/*  451 */     int npoints = (int)(this._POLYCYL_TESS / 360.0D * Math.abs(sweepTheta) + 4.0D);
/*      */     
/*  453 */     double[][] points = (double[][])null;
/*  454 */     double[][][] xforms = (double[][][])null;
/*  455 */     double delta = 0.0D;
/*  456 */     double deltaAngle = 0.0D;
/*  457 */     double cdelta = 0.0D;
/*  458 */     double sdelta = 0.0D;
/*  459 */     double sprev = 0.0D;
/*  460 */     double cprev = 0.0D;
/*  461 */     double scurr = 0.0D;
/*  462 */     double ccurr = 0.0D;
/*  463 */     double[][] mA = new double[2][2];
/*  464 */     double[][] mB = new double[2][2];
/*  465 */     double[][] run = new double[2][2];
/*  466 */     double[] deltaTrans = new double[2];
/*  467 */     double[] trans = new double[2];
/*      */     
/*  469 */     points = new double[npoints][3];
/*  470 */     if (startTransform == null) {
/*  471 */       xforms = (double[][][])null;
/*      */     } else {
/*  473 */       xforms = new double[npoints][2][3];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  479 */     deltaAngle = 0.017453292519943295D * sweepTheta / (npoints - 3);
/*  480 */     startTheta *= 0.017453292519943295D;
/*  481 */     startTheta -= deltaAngle;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  486 */     cprev = Math.cos(startTheta);
/*  487 */     sprev = Math.sin(startTheta);
/*      */     
/*  489 */     cdelta = Math.cos(deltaAngle);
/*  490 */     sdelta = Math.sin(deltaAngle);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  495 */     delta = deltaAngle / 6.283185307179586D;
/*  496 */     dzdTheta *= delta;
/*  497 */     drdTheta *= delta;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  502 */     startZ -= dzdTheta;
/*  503 */     startRadius -= drdTheta;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  508 */     for (int i = 0; i < npoints; i++) {
/*  509 */       points[i][0] = (startRadius * cprev);
/*  510 */       points[i][1] = (startRadius * sprev);
/*  511 */       points[i][2] = startZ;
/*      */       
/*  513 */       startZ += dzdTheta;
/*  514 */       startRadius += drdTheta;
/*  515 */       ccurr = cprev * cdelta - sprev * sdelta;
/*  516 */       scurr = cprev * sdelta + sprev * cdelta;
/*  517 */       cprev = ccurr;
/*  518 */       sprev = scurr;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  525 */     if (startTransform != null) {
/*  526 */       if (dTransformdTheta == null) {
/*  527 */         for (int i = 0; i < npoints; i++) {
/*  528 */           xforms[i][0][0] = startTransform[0][0];
/*  529 */           xforms[i][0][1] = startTransform[0][1];
/*  530 */           xforms[i][0][2] = startTransform[0][2];
/*  531 */           xforms[i][1][0] = startTransform[1][0];
/*  532 */           xforms[i][1][1] = startTransform[1][1];
/*  533 */           xforms[i][1][2] = startTransform[1][2];
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  550 */         deltaTrans[0] = (delta * dTransformdTheta[0][2]);
/*  551 */         deltaTrans[1] = (delta * dTransformdTheta[1][2]);
/*  552 */         trans[0] = startTransform[0][2];
/*  553 */         trans[1] = startTransform[1][2];
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  558 */         delta /= 32.0D;
/*  559 */         mA[0][0] = (1.0D + delta * dTransformdTheta[0][0]);
/*  560 */         mA[0][1] = (delta * dTransformdTheta[0][1]);
/*  561 */         mA[1][0] = (delta * dTransformdTheta[1][0]);
/*  562 */         mA[1][1] = (1.0D + delta * dTransformdTheta[1][1]);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  567 */         mB = matrix.MATRIX_PRODUCT_2X2(mA, mA);
/*  568 */         mA = matrix.MATRIX_PRODUCT_2X2(mB, mB);
/*  569 */         mB = matrix.MATRIX_PRODUCT_2X2(mA, mA);
/*  570 */         mA = matrix.MATRIX_PRODUCT_2X2(mB, mB);
/*  571 */         mB = matrix.MATRIX_PRODUCT_2X2(mA, mA);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  576 */         run = matrix.COPY_MATRIX_2X2(startTransform);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  581 */         xforms[0][0][0] = startTransform[0][0];
/*  582 */         xforms[0][0][1] = startTransform[0][1];
/*  583 */         xforms[0][0][2] = startTransform[0][2];
/*  584 */         xforms[0][1][0] = startTransform[1][0];
/*  585 */         xforms[0][1][1] = startTransform[1][1];
/*  586 */         xforms[0][1][2] = startTransform[1][2];
/*      */         
/*  588 */         for (int j = 0; j < npoints; j++) {
/*  589 */           xforms[j][0][0] = run[0][0];
/*  590 */           xforms[j][0][1] = run[0][1];
/*  591 */           xforms[j][1][0] = run[1][0];
/*  592 */           xforms[j][1][1] = run[1][1];
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  599 */           mA = matrix.MATRIX_PRODUCT_2X2(mB, run);
/*  600 */           run = matrix.COPY_MATRIX_2X2(mA);
/*      */           
/*  602 */           xforms[j][0][2] = trans[0];
/*  603 */           xforms[j][1][2] = trans[1];
/*      */           
/*  605 */           trans[0] += deltaTrans[0];
/*  606 */           trans[1] += deltaTrans[1];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  614 */     int saveStyle = gleGetJoinStyle();
/*  615 */     int style = saveStyle;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  625 */     style &= 0xFFFFFFF0;
/*  626 */     style |= 0x2;
/*  627 */     gleSetJoinStyle(style);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  632 */     gleSuperExtrusion(ncp, contour, contourNormal, up, npoints, points, (float[][])null, xforms);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  638 */     gleSetJoinStyle(saveStyle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleLathe(int ncp, double[][] contour, double[][] contourNormal, double[] up, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta)
/*      */     throws GLEException
/*      */   {
/*  675 */     double[] localup = new double[3];
/*  676 */     double len = 0.0D;
/*  677 */     double[] trans = new double[2];
/*  678 */     double[][] start = new double[2][3];
/*  679 */     double[][] delt = new double[2][3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  687 */     if (up != null) {
/*  688 */       if (up[1] != 0.0D) {
/*  689 */         localup[0] = up[0];
/*  690 */         localup[1] = 0.0D;
/*  691 */         localup[2] = up[2];
/*      */         
/*  693 */         len = matrix.VEC_LENGTH(localup);
/*  694 */         if (len != 0.0D) {
/*  695 */           len = 1.0D / len;
/*  696 */           localup[0] *= len;
/*  697 */           localup[2] *= len;
/*  698 */           localup = matrix.VEC_SCALE(len, localup);
/*      */         } else {
/*  700 */           localup[0] = 0.0D;
/*  701 */           localup[2] = 1.0D;
/*      */         }
/*      */       } else {
/*  704 */         localup = matrix.VEC_COPY(up);
/*      */       }
/*      */     } else {
/*  707 */       localup[0] = 0.0D;
/*  708 */       localup[2] = 1.0D;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  718 */     trans[0] = (localup[2] * drdTheta - localup[0] * dzdTheta);
/*  719 */     trans[1] = (localup[0] * drdTheta + localup[2] * dzdTheta);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  724 */     if (startTransform != null) {
/*  725 */       if (dTransformdTheta != null) {
/*  726 */         delt = matrix.COPY_MATRIX_2X3(dTransformdTheta);
/*  727 */         delt[0][2] += trans[0];
/*  728 */         delt[1][2] += trans[1];
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  733 */         delt[0][0] = 0.0D;
/*  734 */         delt[0][1] = 0.0D;
/*  735 */         delt[0][2] = trans[0];
/*  736 */         delt[1][0] = 0.0D;
/*  737 */         delt[1][1] = 0.0D;
/*  738 */         delt[1][2] = trans[1];
/*      */       }
/*  740 */       gleSpiral(ncp, contour, contourNormal, up, startRadius, 0.0D, startZ, 0.0D, startTransform, delt, startTheta, sweepTheta);
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  748 */       start[0][0] = 1.0D;
/*  749 */       start[0][1] = 0.0D;
/*  750 */       start[0][2] = 0.0D;
/*  751 */       start[1][0] = 0.0D;
/*  752 */       start[1][1] = 1.0D;
/*  753 */       start[1][2] = 0.0D;
/*      */       
/*  755 */       delt[0][0] = 0.0D;
/*  756 */       delt[0][1] = 0.0D;
/*  757 */       delt[0][2] = trans[0];
/*  758 */       delt[1][0] = 0.0D;
/*  759 */       delt[1][1] = 0.0D;
/*  760 */       delt[1][2] = trans[1];
/*  761 */       gleSpiral(ncp, contour, contourNormal, up, startRadius, 0.0D, startZ, 0.0D, start, delt, startTheta, sweepTheta);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleHelicoid(double rToroid, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta)
/*      */     throws GLEException
/*      */   {
/*  792 */     super_helix(rToroid, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta, "Spiral");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleToroid(double rToroid, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta)
/*      */     throws GLEException
/*      */   {
/*  821 */     super_helix(rToroid, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta, "Lathe");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void gleScrew(int ncp, double[][] contour, double[][] contourNormal, double[] up, double startz, double endz, double twist)
/*      */     throws GLEException
/*      */   {
/*  850 */     int numsegs = (int)Math.abs(twist / 18.0D) + 4;
/*  851 */     double[][] path = new double[numsegs][3];
/*  852 */     double[] twarr = new double[numsegs];
/*  853 */     double delta = 0.0D;
/*  854 */     double currz = 0.0D;
/*  855 */     double currang = 0.0D;
/*  856 */     double delang = 0.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  861 */     delta = (endz - startz) / (numsegs - 3);
/*  862 */     currz = startz - delta;
/*  863 */     delang = twist / (numsegs - 3);
/*  864 */     currang = -delang;
/*      */     
/*  866 */     for (int i = 0; i < numsegs; i++) {
/*  867 */       path[i][0] = 0.0D;
/*  868 */       path[i][1] = 0.0D;
/*  869 */       path[i][2] = currz;
/*  870 */       currz += delta;
/*  871 */       twarr[i] = currang;
/*  872 */       currang += delang;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  878 */     gleTwistExtrusion(ncp, contour, contourNormal, up, numsegs, path, (float[][])null, twarr);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void super_helix(double rToroid, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta, String callback)
/*      */   {
/*  896 */     double[][] circle = new double[this._POLYCYL_TESS][2];
/*  897 */     double[][] norm = new double[this._POLYCYL_TESS][2];
/*  898 */     double c = 0.0D;
/*  899 */     double s = 0.0D;
/*  900 */     double[] up = new double[3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  905 */     s = Math.sin(6.283185307179586D / this._POLYCYL_TESS);
/*  906 */     c = Math.cos(6.283185307179586D / this._POLYCYL_TESS);
/*      */     
/*  908 */     norm[0][0] = 1.0D;
/*  909 */     norm[0][1] = 0.0D;
/*  910 */     circle[0][0] = rToroid;
/*  911 */     circle[0][1] = 0.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  916 */     for (int i = 1; i < this._POLYCYL_TESS; i++) {
/*  917 */       norm[i][0] = (norm[(i - 1)][0] * c - norm[(i - 1)][1] * s);
/*  918 */       norm[i][1] = (norm[(i - 1)][0] * s + norm[(i - 1)][1] * c);
/*  919 */       circle[i][0] = (rToroid * norm[i][0]);
/*  920 */       circle[i][1] = (rToroid * norm[i][1]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  926 */     up[1] = (up[2] = 0.0D);
/*  927 */     up[0] = 1.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  932 */     int saveStyle = gleGetJoinStyle();
/*  933 */     int style = saveStyle;
/*  934 */     style |= 0x1000;
/*  935 */     style |= 0x400;
/*  936 */     gleSetJoinStyle(style);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  941 */     if (!GL11.glIsEnabled(2896)) {
/*  942 */       if (callback.equals("Spiral")) {
/*  943 */         gleSpiral(this._POLYCYL_TESS, circle, (double[][])null, up, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta);
/*      */ 
/*      */ 
/*      */       }
/*  947 */       else if (callback.equals("Lathe")) {
/*  948 */         gleLathe(this._POLYCYL_TESS, circle, (double[][])null, up, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta);
/*      */       }
/*      */       else
/*      */       {
/*  952 */         throw new GLEException("Specified callback " + callback + " is not registered. Use either ``Spiral'' or ``Lathe''");
/*      */       }
/*      */       
/*      */     }
/*  956 */     else if (callback.equals("Spiral")) {
/*  957 */       gleSpiral(this._POLYCYL_TESS, circle, norm, up, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta);
/*      */ 
/*      */ 
/*      */     }
/*  961 */     else if (callback.equals("Lathe")) {
/*  962 */       gleLathe(this._POLYCYL_TESS, circle, norm, up, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta);
/*      */     }
/*      */     else
/*      */     {
/*  966 */       throw new GLEException("Specified callback " + callback + " is not registered. Use either ``Spiral'' or ``Lathe''");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  974 */     gleSetJoinStyle(saveStyle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double[] up_sanity_check(double[] up, int npoints, double[][] pointArray)
/*      */   {
/* 1001 */     double len = 0.0D;
/* 1002 */     double[] diff = null;
/* 1003 */     double[] vtmp = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1009 */     diff = matrix.VEC_DIFF(pointArray[1], pointArray[0]);
/* 1010 */     len = matrix.VEC_LENGTH(diff);
/* 1011 */     if (len == 0.0D)
/*      */     {
/*      */ 
/*      */ 
/* 1015 */       for (int i = 1; i < npoints - 2; i++) {
/* 1016 */         diff = matrix.VEC_DIFF(pointArray[(i + 1)], pointArray[i]);
/* 1017 */         len = matrix.VEC_LENGTH(diff);
/* 1018 */         if (len != 0.0D) {
/*      */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1027 */     len = 1.0D / len;
/* 1028 */     diff = matrix.VEC_SCALE(len, diff);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1034 */     vtmp = matrix.VEC_PERP(up, diff);
/*      */     
/* 1036 */     len = matrix.VEC_LENGTH(vtmp);
/* 1037 */     if (len == 0.0D)
/*      */     {
/*      */ 
/*      */ 
/* 1041 */       System.err.println("Extrusion: Warning: ");
/* 1042 */       System.err.println("contour up vector parallel to tubing direction");
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1047 */       vtmp = matrix.VEC_COPY(diff);
/*      */     }
/* 1049 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void extrusion_raw_join(int ncp, double[][] contour, double[][] contourNormal, double[] up, int npoints, double[][] pointArray, float[][] colourArray, double[][][] xformArray)
/*      */   {
/* 1063 */     int i = 0;
/* 1064 */     int j = 0;
/* 1065 */     int inext = 0;
/* 1066 */     double[][] m = (double[][])null;
/* 1067 */     double len = 0.0D;
/* 1068 */     double[] diff = new double[3];
/* 1069 */     double[] bi_0 = new double[3];
/* 1070 */     double[] yup = new double[3];
/* 1071 */     double[] nrmv = new double[3];
/* 1072 */     boolean no_norm = contourNormal == null;
/* 1073 */     boolean no_cols = colourArray == null;
/* 1074 */     boolean no_xform = xformArray == null;
/* 1075 */     double[][] mem_anchor = (double[][])null;
/* 1076 */     double[][] front_loop = (double[][])null;
/* 1077 */     double[][] back_loop = (double[][])null;
/* 1078 */     double[][] front_norm = (double[][])null;
/* 1079 */     double[][] back_norm = (double[][])null;
/* 1080 */     double[][] tmp = (double[][])null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1087 */     nrmv[0] = (nrmv[1] = 0.0D);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1094 */     if (!no_xform) {
/* 1095 */       front_loop = new double[ncp][3];
/* 1096 */       back_loop = new double[ncp][3];
/* 1097 */       front_norm = new double[ncp][3];
/* 1098 */       back_norm = new double[ncp][3];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1105 */     if (up == null) {
/* 1106 */       yup[0] = 0.0D;
/* 1107 */       yup[1] = 1.0D;
/* 1108 */       yup[2] = 0.0D;
/*      */     } else {
/* 1110 */       yup = matrix.VEC_COPY(up);
/*      */     }
/* 1112 */     up = matrix.VEC_COPY(yup);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1118 */     yup = up_sanity_check(up, npoints, pointArray);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1124 */     i = 1;
/* 1125 */     inext = i;
/* 1126 */     inext = intersect.FIND_NON_DEGENERATE_POINT(inext, npoints, len, diff, pointArray);
/*      */     
/*      */ 
/* 1129 */     len = matrix.VEC_LENGTH(diff);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1134 */     if (!no_xform) {
/* 1135 */       for (j = 0; j < ncp; j++) {
/* 1136 */         front_loop[j] = matrix.MAT_DOT_VEC_2X3(xformArray[(inext - 1)], contour[j]);
/*      */         
/*      */ 
/* 1139 */         front_loop[j][2] = 0.0D;
/*      */       }
/* 1141 */       if (!no_norm) {
/* 1142 */         for (j = 0; j < ncp; j++) {
/* 1143 */           front_norm[j] = matrix.NORM_XFORM_2X2(xformArray[(inext - 1)], contourNormal[j]);
/*      */           
/*      */ 
/* 1146 */           front_norm[j][2] = 0.0D;
/* 1147 */           back_norm[j][2] = 0.0D;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1155 */     while (inext < npoints - 1)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1161 */       bi_0 = intersect.bisecting_plane(pointArray[(i - 1)], pointArray[i], pointArray[inext]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1170 */       yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1180 */       m = matrix.uviewpoint_d(pointArray[i], pointArray[inext], yup);
/* 1181 */       DoubleBuffer mbuffer = BufferUtils.createDoubleBuffer(16);
/* 1182 */       mbuffer.put(new double[] { m[0][0], m[0][1], m[0][2], m[0][3], m[1][0], m[1][1], m[1][2], m[1][3], m[2][0], m[2][1], m[2][2], m[2][3], m[3][0], m[3][1], m[3][2], m[3][3] });
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1190 */       mbuffer.flip();
/* 1191 */       GL11.glPushMatrix();
/* 1192 */       GL11.glMultMatrix(mbuffer);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1200 */       if (no_xform) {
/* 1201 */         if (no_cols) {
/* 1202 */           if (no_norm) {
/* 1203 */             draw_raw_segment_plain(ncp, contour, inext, len);
/*      */           }
/* 1205 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 1206 */             draw_raw_segment_facet_n(ncp, contour, contourNormal, inext, len);
/*      */           }
/*      */           else {
/* 1209 */             draw_raw_segment_edge_n(ncp, contour, contourNormal, inext, len);
/*      */           }
/*      */           
/*      */ 
/*      */         }
/* 1214 */         else if (no_norm) {
/* 1215 */           draw_raw_segment_color(ncp, contour, colourArray, inext, len);
/*      */ 
/*      */         }
/* 1218 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 1219 */           draw_raw_segment_c_and_facet_n(ncp, contour, colourArray, contourNormal, inext, len);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 1224 */           draw_raw_segment_c_and_edge_n(ncp, contour, colourArray, contourNormal, inext, len);
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/* 1235 */         for (j = 0; j < ncp; j++) {
/* 1236 */           back_loop[j] = matrix.MAT_DOT_VEC_2X3(xformArray[inext], contour[j]);
/*      */           
/*      */ 
/* 1239 */           back_loop[j][2] = (-len);
/* 1240 */           front_loop[j][2] = 0.0D;
/*      */         }
/*      */         
/* 1243 */         if (!no_norm) {
/* 1244 */           for (j = 0; j < ncp; j++) {
/* 1245 */             back_norm[j] = matrix.NORM_XFORM_2X2(xformArray[inext], contourNormal[j]);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1251 */         if (no_cols) {
/* 1252 */           if (no_norm) {
/* 1253 */             draw_segment_plain(ncp, front_loop, back_loop, inext, len);
/*      */ 
/*      */ 
/*      */           }
/* 1257 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 1258 */             draw_binorm_segment_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, len);
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/* 1264 */             draw_binorm_segment_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, len);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1272 */           if ((gleGetJoinStyle() & 0x10) == 16) {
/* 1273 */             nrmv[2] = 1.0D;
/* 1274 */             GL11.glNormal3d(nrmv[0], nrmv[1], nrmv[2]);
/* 1275 */             draw_front_contour_cap(ncp, front_loop);
/* 1276 */             nrmv[2] = -1.0D;
/* 1277 */             GL11.glNormal3d(nrmv[0], nrmv[1], nrmv[2]);
/* 1278 */             draw_back_contour_cap(ncp, back_loop);
/*      */           }
/*      */         } else {
/* 1281 */           if (no_norm) {
/* 1282 */             draw_segment_color(ncp, front_loop, back_loop, colourArray[(inext - 1)], colourArray[inext], inext, len);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           }
/* 1289 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 1290 */             draw_binorm_segment_c_and_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, colourArray[(inext - 1)], colourArray[inext], inext, len);
/*      */ 
/*      */ 
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/*      */ 
/* 1299 */             draw_binorm_segment_c_and_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, colourArray[(inext - 1)], colourArray[inext], inext, len);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1310 */           if ((gleGetJoinStyle() & 0x10) == 16) {
/* 1311 */             GL11.glColor3f(colourArray[(inext - 1)][0], colourArray[(inext - 1)][1], colourArray[(inext - 1)][2]);
/* 1312 */             nrmv[2] = 1.0D;
/* 1313 */             GL11.glNormal3d(nrmv[0], nrmv[1], nrmv[2]);
/* 1314 */             draw_front_contour_cap(ncp, front_loop);
/*      */             
/* 1316 */             GL11.glColor3f(colourArray[inext][0], colourArray[inext][1], colourArray[inext][2]);
/* 1317 */             nrmv[2] = -1.0D;
/* 1318 */             GL11.glNormal3d(nrmv[0], nrmv[1], nrmv[2]);
/* 1319 */             draw_back_contour_cap(ncp, back_loop);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1327 */       GL11.glPopMatrix();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1332 */       tmp = front_loop;
/* 1333 */       front_loop = back_loop;
/* 1334 */       back_loop = tmp;
/* 1335 */       tmp = front_norm;
/* 1336 */       front_norm = back_norm;
/* 1337 */       back_norm = tmp;
/*      */       
/* 1339 */       i = inext;
/*      */       
/*      */ 
/*      */ 
/* 1343 */       inext = intersect.FIND_NON_DEGENERATE_POINT(inext, npoints, len, diff, pointArray);
/*      */       
/*      */ 
/* 1346 */       len = matrix.VEC_LENGTH(diff);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_raw_segment_plain(int ncp, double[][] contour, int inext, double len)
/*      */   {
/* 1365 */     double[] point = new double[3];
/*      */     
/* 1367 */     System.err.println("draw_raw_segment_plain()");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1372 */     GL11.glBegin(5);
/* 1373 */     for (int j = 0; j < ncp; j++) {
/* 1374 */       point[0] = contour[j][0];
/* 1375 */       point[1] = contour[j][1];
/* 1376 */       point[2] = 0.0D;
/* 1377 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1381 */       point[2] = (-len);
/* 1382 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1387 */     if ((gleGetJoinStyle() & 0x1000) == 4096)
/*      */     {
/*      */ 
/*      */ 
/* 1391 */       point[0] = contour[0][0];
/* 1392 */       point[1] = contour[0][1];
/* 1393 */       point[2] = 0.0D;
/* 1394 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1398 */       point[2] = (-len);
/* 1399 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/* 1403 */     GL11.glEnd();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1408 */     if ((gleGetJoinStyle() & 0x10) == 16)
/*      */     {
/*      */ 
/*      */ 
/* 1412 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1417 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_raw_segment_color(int ncp, double[][] contour, float[][] color_array, int inext, double len)
/*      */   {
/* 1434 */     double[] point = new double[3];
/* 1435 */     System.out.println("draw_raw_segment_color");
/*      */     
/*      */ 
/*      */ 
/* 1439 */     GL11.glBegin(5);
/* 1440 */     double tc = 0.0D;
/* 1441 */     for (int j = 0; j < ncp; j++) {
/* 1442 */       tc = j / ncp;
/* 1443 */       point[0] = contour[j][0];
/* 1444 */       point[1] = contour[j][1];
/* 1445 */       point[2] = 0.0D;
/* 1446 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 1447 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/* 1448 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/* 1450 */       point[2] = (-len);
/* 1451 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS + this.SLICE);
/* 1452 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1453 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/* 1456 */     if ((gleGetJoinStyle() & 0x1000) == 4096)
/*      */     {
/*      */ 
/*      */ 
/* 1460 */       point[0] = contour[0][0];
/* 1461 */       point[1] = contour[0][1];
/* 1462 */       point[2] = 0.0D;
/*      */       
/* 1464 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/*      */       
/* 1466 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/* 1467 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1471 */       point[2] = (-len);
/* 1472 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS + this.SLICE);
/* 1473 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1474 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1479 */     GL11.glEnd();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1484 */     if ((gleGetJoinStyle() & 0x10) == 16)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1489 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 1490 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/* 1491 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1496 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS + this.SLICE);
/* 1497 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1498 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     }
/*      */     
/* 1501 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_raw_segment_edge_n(int ncp, double[][] contour, double[][] cont_normal, int inext, double len)
/*      */   {
/* 1517 */     double[] point = new double[3];
/* 1518 */     double[] norm = new double[3];
/*      */     
/* 1520 */     System.err.println("draw_raw_segment_edge_n");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1525 */     norm[2] = 0.0D;
/* 1526 */     GL11.glBegin(5);
/* 1527 */     for (int j = 0; j < ncp; j++) {
/* 1528 */       norm[0] = cont_normal[j][0];
/* 1529 */       norm[1] = cont_normal[j][1];
/* 1530 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1532 */       point[0] = contour[j][0];
/* 1533 */       point[1] = contour[j][1];
/* 1534 */       point[2] = 0.0D;
/* 1535 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1539 */       point[2] = (-len);
/* 1540 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1545 */     if ((gleGetJoinStyle() & 0x1000) == 4096)
/*      */     {
/*      */ 
/*      */ 
/* 1549 */       norm[0] = cont_normal[0][0];
/* 1550 */       norm[1] = cont_normal[0][1];
/* 1551 */       norm[2] = 0.0D;
/* 1552 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1554 */       point[0] = contour[0][0];
/* 1555 */       point[1] = contour[0][1];
/* 1556 */       point[2] = 0.0D;
/* 1557 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1561 */       point[2] = (-len);
/* 1562 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/* 1566 */     GL11.glEnd();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1571 */     if ((gleGetJoinStyle() & 0x10) == 16)
/*      */     {
/*      */ 
/*      */ 
/* 1575 */       norm[0] = (norm[1] = 0.0D);
/* 1576 */       norm[2] = 1.0D;
/* 1577 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1578 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1583 */       norm[2] = -1.0D;
/* 1584 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1585 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_raw_segment_c_and_edge_n(int ncp, double[][] contour, float[][] color_array, double[][] cont_normal, int inext, double len)
/*      */   {
/* 1602 */     double[] point = new double[3];
/* 1603 */     double[] norm = new double[3];
/*      */     
/*      */ 
/* 1606 */     System.out.println("draw_raw_segment_c_and_edge_n");
/*      */     
/*      */ 
/*      */ 
/* 1610 */     norm[2] = 0.0D;
/* 1611 */     GL11.glBegin(5);
/* 1612 */     for (int j = 0; j < ncp; j++) {
/* 1613 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/*      */       
/* 1615 */       norm[0] = cont_normal[j][0];
/* 1616 */       norm[1] = cont_normal[j][1];
/* 1617 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1619 */       point[0] = contour[j][0];
/* 1620 */       point[1] = contour[j][1];
/* 1621 */       point[2] = 0.0D;
/* 1622 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1626 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1627 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1629 */       point[2] = (-len);
/* 1630 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1636 */     if ((gleGetJoinStyle() & 0x1000) == 4096)
/*      */     {
/*      */ 
/*      */ 
/* 1640 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/*      */       
/* 1642 */       norm[0] = cont_normal[0][0];
/* 1643 */       norm[1] = cont_normal[0][1];
/* 1644 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1646 */       point[0] = contour[0][0];
/* 1647 */       point[1] = contour[0][1];
/* 1648 */       point[2] = 0.0D;
/* 1649 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1653 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1654 */       norm[0] = cont_normal[0][0];
/* 1655 */       norm[1] = cont_normal[0][1];
/* 1656 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1658 */       point[2] = (-len);
/* 1659 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1664 */     GL11.glEnd();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1669 */     if ((gleGetJoinStyle() & 0x10) == 16)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1674 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/* 1675 */       norm[0] = (norm[1] = 0.0D);
/* 1676 */       norm[2] = 1.0D;
/* 1677 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1678 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1683 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1684 */       norm[2] = -1.0D;
/* 1685 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1686 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_raw_segment_facet_n(int ncp, double[][] contour, double[][] cont_normal, int inext, double len)
/*      */   {
/* 1702 */     double[] point = new double[3];
/* 1703 */     double[] norm = new double[3];
/* 1704 */     System.out.println("draw_raw_segment_facet_n");
/*      */     
/*      */ 
/*      */ 
/* 1708 */     norm[2] = 0.0D;
/*      */     
/* 1710 */     GL11.glBegin(5);
/* 1711 */     for (int j = 0; j < ncp - 1; j++)
/*      */     {
/*      */ 
/*      */ 
/* 1715 */       norm[0] = cont_normal[j][0];
/* 1716 */       norm[1] = cont_normal[j][1];
/* 1717 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1719 */       point[0] = contour[j][0];
/* 1720 */       point[1] = contour[j][1];
/* 1721 */       point[2] = 0.0D;
/* 1722 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1726 */       point[2] = (-len);
/* 1727 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1731 */       point[0] = contour[(j + 1)][0];
/* 1732 */       point[1] = contour[(j + 1)][1];
/* 1733 */       point[2] = 0.0D;
/* 1734 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1738 */       point[2] = (-len);
/* 1739 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1745 */     if ((gleGetJoinStyle() & 0x1000) == 4096)
/*      */     {
/*      */ 
/*      */ 
/* 1749 */       norm[0] = cont_normal[(ncp - 1)][0];
/* 1750 */       norm[1] = cont_normal[(ncp - 1)][1];
/* 1751 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1753 */       point[0] = contour[(ncp - 1)][0];
/* 1754 */       point[1] = contour[(ncp - 1)][1];
/* 1755 */       point[2] = 0.0D;
/* 1756 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1760 */       point[2] = (-len);
/* 1761 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1765 */       point[0] = contour[0][0];
/* 1766 */       point[1] = contour[0][1];
/* 1767 */       point[2] = 0.0D;
/* 1768 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1772 */       point[2] = (-len);
/* 1773 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1778 */     GL11.glEnd();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1783 */     if ((gleGetJoinStyle() & 0x10) == 16)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1788 */       norm[0] = (norm[1] = 0.0D);
/* 1789 */       norm[2] = 1.0D;
/* 1790 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1791 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1796 */       norm[2] = -1.0D;
/* 1797 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1798 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_raw_segment_c_and_facet_n(int ncp, double[][] contour, float[][] color_array, double[][] cont_normal, int inext, double len)
/*      */   {
/* 1815 */     System.out.println("draw_raw_segment_c_and_facet_n");
/* 1816 */     double[] point = new double[3];
/* 1817 */     double[] norm = new double[3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1822 */     norm[2] = 0.0D;
/* 1823 */     GL11.glBegin(5);
/* 1824 */     for (int j = 0; j < ncp - 1; j++)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1833 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/*      */       
/* 1835 */       norm[0] = cont_normal[j][0];
/* 1836 */       norm[1] = cont_normal[j][1];
/* 1837 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1839 */       point[0] = contour[j][0];
/* 1840 */       point[1] = contour[j][1];
/* 1841 */       point[2] = 0.0D;
/* 1842 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1846 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1847 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1848 */       point[2] = (-len);
/* 1849 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1853 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/* 1854 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1856 */       point[0] = contour[(j + 1)][0];
/* 1857 */       point[1] = contour[(j + 1)][1];
/* 1858 */       point[2] = 0.0D;
/* 1859 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1863 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1864 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1865 */       point[2] = (-len);
/* 1866 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1872 */     if ((gleGetJoinStyle() & 0x1000) == 4096)
/*      */     {
/*      */ 
/*      */ 
/* 1876 */       point[0] = contour[(ncp - 1)][0];
/* 1877 */       point[1] = contour[(ncp - 1)][1];
/* 1878 */       point[2] = 0.0D;
/* 1879 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/*      */       
/* 1881 */       norm[0] = cont_normal[(ncp - 1)][0];
/* 1882 */       norm[1] = cont_normal[(ncp - 1)][1];
/* 1883 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1884 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1888 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1889 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1891 */       point[2] = (-len);
/* 1892 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1896 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/*      */       
/* 1898 */       norm[0] = cont_normal[0][0];
/* 1899 */       norm[1] = cont_normal[0][1];
/* 1900 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1902 */       point[0] = contour[0][0];
/* 1903 */       point[1] = contour[0][1];
/* 1904 */       point[2] = 0.0D;
/* 1905 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/*      */ 
/*      */ 
/* 1909 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1910 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1912 */       point[2] = (-len);
/* 1913 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1919 */     GL11.glEnd();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1924 */     if ((gleGetJoinStyle() & 0x10) == 16)
/*      */     {
/*      */ 
/*      */ 
/* 1928 */       GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/* 1929 */       norm[0] = (norm[1] = 0.0D);
/* 1930 */       norm[2] = 1.0D;
/* 1931 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1932 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1937 */       GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/* 1938 */       norm[2] = -1.0D;
/* 1939 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1940 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_raw_style_end_cap(int ncp, double[][] contour, double zval, boolean frontwards)
/*      */   {
/* 1952 */     System.out.println("draw_raw_style_end_cap");
/* 1953 */     GLUtessellator tobj = GLU.gluNewTess();
/* 1954 */     tobj.gluTessProperty(100140, 100130.0D);
/* 1955 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 1956 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 1957 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 1958 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/*      */ 
/* 1961 */     tobj.gluTessBeginPolygon(null);
/* 1962 */     tobj.gluTessBeginContour();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1967 */     if (frontwards) {
/* 1968 */       for (int j = 0; j < ncp; j++) {
/* 1969 */         double[] vertex = new double[3];
/* 1970 */         vertex[0] = contour[j][0];
/* 1971 */         vertex[1] = contour[j][1];
/* 1972 */         vertex[2] = zval;
/*      */         
/* 1974 */         tobj.gluTessVertex(vertex, 0, vertex);
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/* 1980 */       for (int j = ncp - 1; j > -1; j--) {
/* 1981 */         double[] vertex = new double[3];
/* 1982 */         vertex[0] = contour[j][0];
/* 1983 */         vertex[1] = contour[j][1];
/* 1984 */         vertex[2] = zval;
/*      */         
/* 1986 */         tobj.gluTessVertex(vertex, 0, vertex);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1991 */     tobj.gluTessEndContour();
/* 1992 */     tobj.gluTessEndPolygon();
/* 1993 */     tobj.gluDeleteTess();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_front_contour_cap(int ncp, double[][] contour)
/*      */   {
/* 2002 */     GLUtessellator tobj = GLU.gluNewTess();
/* 2003 */     tobj.gluTessProperty(100140, 100130.0D);
/* 2004 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 2005 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 2006 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 2007 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/*      */ 
/* 2010 */     tobj.gluTessBeginPolygon(null);
/* 2011 */     tobj.gluTessBeginContour();
/*      */     
/* 2013 */     for (int j = 0; j < ncp; j++)
/*      */     {
/* 2015 */       tobj.gluTessVertex(contour[j], 0, contour[j]);
/*      */     }
/*      */     
/*      */ 
/* 2019 */     tobj.gluTessEndContour();
/* 2020 */     tobj.gluTessEndPolygon();
/* 2021 */     tobj.gluDeleteTess();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_back_contour_cap(int ncp, double[][] contour)
/*      */   {
/* 2031 */     GLUtessellator tobj = GLU.gluNewTess();
/* 2032 */     tobj.gluTessProperty(100140, 100132.0D);
/* 2033 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 2034 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 2035 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 2036 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/*      */ 
/* 2039 */     tobj.gluTessBeginPolygon(null);
/* 2040 */     tobj.gluTessBeginContour();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2051 */     for (int j = ncp - 1; j > -1; j--)
/*      */     {
/* 2053 */       tobj.gluTessVertex(contour[j], 0, contour[j]);
/*      */     }
/*      */     
/*      */ 
/* 2057 */     tobj.gluTessEndContour();
/* 2058 */     tobj.gluTessEndPolygon();
/* 2059 */     tobj.gluDeleteTess();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void extrusion_angle_join(int ncp, double[][] contour, double[][] cont_normal, double[] up, int npoints, double[][] point_array, float[][] color_array, double[][][] xform_array)
/*      */   {
/* 2111 */     int i = 0;
/* 2112 */     int j = 0;
/* 2113 */     int inext = 0;
/* 2114 */     int inextnext = 0;
/* 2115 */     double[][] m = new double[4][4];
/* 2116 */     double len = 0.0D;
/* 2117 */     double len_seg = 0.0D;
/* 2118 */     double[] diff = new double[3];
/* 2119 */     double[] bi_0 = new double[3];
/* 2120 */     double[] bi_1 = new double[3];
/*      */     
/*      */ 
/* 2123 */     double[] bisector_0 = new double[3];
/* 2124 */     double[] bisector_1 = new double[3];
/*      */     
/*      */ 
/* 2127 */     double[] end_point_0 = new double[3];
/* 2128 */     double[] end_point_1 = new double[3];
/* 2129 */     double[] origin = new double[3];
/* 2130 */     double[] neg_z = new double[3];
/* 2131 */     double[] yup = new double[3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2150 */     if (up == null) {
/* 2151 */       yup[0] = 0.0D;
/* 2152 */       yup[1] = 1.0D;
/* 2153 */       yup[2] = 0.0D;
/*      */     } else {
/* 2155 */       yup = matrix.VEC_COPY(up);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2161 */     yup = up_sanity_check(yup, npoints, point_array);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2166 */     origin[0] = 0.0D;
/* 2167 */     origin[1] = 0.0D;
/* 2168 */     origin[2] = 0.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2173 */     neg_z[0] = 0.0D;
/* 2174 */     neg_z[1] = 0.0D;
/* 2175 */     neg_z[2] = 1.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2180 */     i = 1;
/* 2181 */     inext = i;
/* 2182 */     inext = intersect.FIND_NON_DEGENERATE_POINT(inext, npoints, len, diff, point_array);
/*      */     
/* 2184 */     len = matrix.VEC_LENGTH(diff);
/* 2185 */     len_seg = len;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2192 */     bi_0 = intersect.bisecting_plane(point_array[0], point_array[1], point_array[inext]);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2199 */     yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2209 */     double[][] front_loop = new double[ncp][3];
/* 2210 */     double[][] back_loop = new double[ncp][3];
/* 2211 */     double[][] front_norm = new double[ncp][3];
/* 2212 */     double[][] back_norm = new double[ncp][3];
/* 2213 */     double[][] norm_loop = front_norm;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2218 */     if (cont_normal != null) {
/* 2219 */       if (xform_array == null) {
/* 2220 */         for (j = 0; j < ncp; j++) {
/* 2221 */           norm_loop[j][0] = cont_normal[j][0];
/* 2222 */           norm_loop[j][1] = cont_normal[j][1];
/* 2223 */           norm_loop[j][2] = 0.0D;
/*      */         }
/*      */       }
/* 2226 */       for (j = 0; j < ncp; j++) {
/* 2227 */         front_norm[j] = matrix.NORM_XFORM_2X2(xform_array[(inext - 1)], cont_normal[j]);
/*      */         
/*      */ 
/* 2230 */         front_norm[j][2] = 0.0D;
/* 2231 */         back_norm[j][2] = 0.0D;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2236 */     boolean first_time = true;
/*      */     
/*      */ 
/*      */ 
/* 2240 */     while (inext < npoints - 1) {
/* 2241 */       inextnext = inext;
/*      */       
/*      */ 
/*      */ 
/* 2245 */       inextnext = intersect.FIND_NON_DEGENERATE_POINT(inextnext, npoints, len, diff, point_array);
/*      */       
/*      */ 
/* 2248 */       len = matrix.VEC_LENGTH(diff);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2253 */       bi_1 = intersect.bisecting_plane(point_array[i], point_array[inext], point_array[inextnext]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2261 */       m = matrix.uviewpoint_d(point_array[i], point_array[inext], yup);
/*      */       
/*      */ 
/* 2264 */       DoubleBuffer mbuffer = BufferUtils.createDoubleBuffer(16);
/*      */       
/* 2266 */       mbuffer.put(new double[] { m[0][0], m[0][1], m[0][2], m[0][3], m[1][0], m[1][1], m[1][2], m[1][3], m[2][0], m[2][1], m[2][2], m[2][3], m[3][0], m[3][1], m[3][2], m[3][3] });
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2274 */       mbuffer.flip();
/* 2275 */       GL11.glPushMatrix();
/* 2276 */       GL11.glMultMatrix(mbuffer);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2282 */       bisector_0 = matrix.MAT_DOT_VEC_3X3(m, bi_0);
/* 2283 */       bisector_1 = matrix.MAT_DOT_VEC_3X3(m, bi_1);
/*      */       
/* 2285 */       neg_z[2] = (-len_seg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2293 */       for (j = 0; j < ncp; j++)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2300 */         if (cont_normal != null)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2306 */           if (xform_array != null)
/*      */           {
/*      */ 
/*      */ 
/* 2310 */             back_norm[j] = matrix.NORM_XFORM_2X2(xform_array[inext], cont_normal[j]);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2327 */           if ((gleGetJoinStyle() & 0x400) == 1024)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 2332 */             if (xform_array == null) {
/* 2333 */               back_norm[j][0] = cont_normal[j][0];
/* 2334 */               back_norm[j][1] = cont_normal[j][1];
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2344 */             front_norm[j][2] = 0.0D;
/* 2345 */             front_norm[j] = matrix.VEC_PERP(front_norm[j], bisector_0);
/*      */             
/* 2347 */             front_norm[j] = matrix.VEC_NORMALIZE(front_norm[j]);
/*      */             
/*      */ 
/* 2350 */             back_norm[j][2] = 0.0D;
/* 2351 */             back_norm[j] = matrix.VEC_PERP(back_norm[j], bisector_1);
/*      */             
/* 2353 */             back_norm[j] = matrix.VEC_NORMALIZE(back_norm[j]);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2368 */         if (xform_array == null) {
/* 2369 */           end_point_0[0] = contour[j][0];
/* 2370 */           end_point_0[1] = contour[j][1];
/*      */           
/* 2372 */           end_point_1[0] = contour[j][0];
/* 2373 */           end_point_1[1] = contour[j][1];
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 2378 */           end_point_0 = matrix.MAT_DOT_VEC_2X3(xform_array[(inext - 1)], contour[j]);
/*      */           
/*      */ 
/* 2381 */           end_point_1 = matrix.MAT_DOT_VEC_2X3(xform_array[(inext - 1)], contour[j]);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 2386 */         end_point_0[2] = 0.0D;
/* 2387 */         end_point_1[2] = (-len_seg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2393 */         front_loop[j] = intersect.INNERSECT(origin, bisector_0, end_point_0, end_point_1);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2415 */         if (xform_array != null)
/*      */         {
/*      */ 
/*      */ 
/* 2419 */           end_point_0 = matrix.MAT_DOT_VEC_2X3(xform_array[inext], contour[j]);
/*      */           
/*      */ 
/* 2422 */           end_point_1 = matrix.MAT_DOT_VEC_2X3(xform_array[inext], contour[j]);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 2428 */         end_point_0[2] = 0.0D;
/* 2429 */         end_point_1[2] = (-len_seg);
/*      */         
/* 2431 */         back_loop[j] = intersect.INNERSECT(neg_z, bisector_1, end_point_0, end_point_1);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2458 */       if ((gleGetJoinStyle() & 0x10) == 16) {
/* 2459 */         if (first_time) {
/* 2460 */           if (color_array != null) {
/* 2461 */             GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/*      */           }
/* 2463 */           first_time = false;
/* 2464 */           draw_angle_style_front_cap(ncp, bisector_0, front_loop);
/*      */         }
/* 2466 */         if (inext == npoints - 2) {
/* 2467 */           if (color_array != null) {
/* 2468 */             GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/*      */           }
/* 2470 */           draw_angle_style_back_cap(ncp, bisector_1, back_loop);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2487 */       if ((xform_array == null) && ((gleGetJoinStyle() & 0x400) != 1024))
/*      */       {
/* 2489 */         if (color_array == null) {
/* 2490 */           if (cont_normal == null) {
/* 2491 */             draw_segment_plain(ncp, front_loop, back_loop, inext, len_seg);
/*      */ 
/*      */           }
/* 2494 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 2495 */             draw_segment_facet_n(ncp, front_loop, back_loop, norm_loop, inext, len_seg);
/*      */           }
/*      */           else
/*      */           {
/* 2499 */             draw_segment_edge_n(ncp, front_loop, back_loop, norm_loop, inext, len_seg);
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */         }
/* 2505 */         else if (cont_normal == null) {
/* 2506 */           draw_segment_color(ncp, front_loop, back_loop, color_array[(inext - 1)], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/* 2512 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 2513 */           draw_segment_c_and_facet_n(ncp, front_loop, back_loop, norm_loop, color_array[(inext - 1)], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/* 2521 */           draw_segment_c_and_edge_n(ncp, front_loop, back_loop, norm_loop, color_array[(inext - 1)], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/* 2532 */       else if (color_array == null) {
/* 2533 */         if (cont_normal == null) {
/* 2534 */           draw_segment_plain(ncp, front_loop, back_loop, inext, len_seg);
/*      */ 
/*      */ 
/*      */         }
/* 2538 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 2539 */           draw_binorm_segment_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, len_seg);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 2545 */           draw_binorm_segment_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, len_seg);
/*      */ 
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */       }
/* 2553 */       else if (cont_normal == null) {
/* 2554 */         draw_segment_color(ncp, front_loop, back_loop, color_array[(inext - 1)], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/* 2560 */       else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 2561 */         draw_binorm_segment_c_and_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, color_array[(inext - 1)], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 2571 */         draw_binorm_segment_c_and_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, color_array[(inext - 1)], color_array[inext], inext, len_seg);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2590 */       GL11.glPopMatrix();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2595 */       len_seg = len;
/* 2596 */       i = inext;
/* 2597 */       inext = inextnext;
/* 2598 */       bi_0 = matrix.VEC_COPY(bi_1);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2603 */       double[][] tmp = front_norm;
/* 2604 */       front_norm = back_norm;
/* 2605 */       back_norm = tmp;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2610 */       yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_angle_style_front_cap(int ncp, double[] bi, double[][] point_array)
/*      */   {
/* 2620 */     GLUtessellator tobj = GLU.gluNewTess();
/* 2621 */     tobj.gluTessProperty(100140, 100130.0D);
/* 2622 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 2623 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 2624 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 2625 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/* 2627 */     if (bi[2] < 0.0D) {
/* 2628 */       bi = matrix.VEC_SCALE(-1.0D, bi);
/*      */     }
/*      */     
/* 2631 */     GL11.glNormal3d(bi[0], bi[1], bi[2]);
/*      */     
/*      */ 
/* 2634 */     tobj.gluTessBeginPolygon(null);
/* 2635 */     tobj.gluTessBeginContour();
/* 2636 */     for (int j = 0; j < ncp; j++)
/*      */     {
/* 2638 */       tobj.gluTessVertex(point_array[j], 0, point_array[j]);
/*      */     }
/*      */     
/* 2641 */     tobj.gluTessEndContour();
/* 2642 */     tobj.gluTessEndPolygon();
/* 2643 */     tobj.gluDeleteTess();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_angle_style_back_cap(int ncp, double[] bi, double[][] point_array)
/*      */   {
/* 2653 */     GLUtessellator tobj = GLU.gluNewTess();
/* 2654 */     tobj.gluTessProperty(100140, 100130.0D);
/* 2655 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 2656 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 2657 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 2658 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/* 2660 */     if (bi[2] > 0.0D) {
/* 2661 */       bi = matrix.VEC_SCALE(-1.0D, bi);
/*      */     }
/*      */     
/* 2664 */     GL11.glNormal3d(bi[0], bi[1], bi[2]);
/*      */     
/* 2666 */     tobj.gluTessBeginPolygon(null);
/* 2667 */     tobj.gluTessBeginContour();
/* 2668 */     for (int j = ncp - 1; j >= 0; j--)
/*      */     {
/* 2670 */       tobj.gluTessVertex(point_array[j], 0, point_array[j]);
/*      */     }
/*      */     
/* 2673 */     tobj.gluTessEndContour();
/* 2674 */     tobj.gluTessEndPolygon();
/* 2675 */     tobj.gluDeleteTess();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_segment_plain(int ncp, double[][] front_contour, double[][] back_contour, int inext, double len)
/*      */   {
/* 2689 */     System.out.println("draw_segment_plain");
/*      */     
/* 2691 */     GL11.glBegin(5);
/* 2692 */     for (int j = 0; j < ncp; j++) {
/* 2693 */       double tc = j / ncp;
/* 2694 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2695 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/* 2696 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS + this.SLICE);
/* 2697 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     }
/*      */     
/* 2700 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2701 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2702 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/* 2703 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS + this.SLICE);
/* 2704 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/* 2706 */     GL11.glEnd();
/* 2707 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_segment_color(int ncp, double[][] front_contour, double[][] back_contour, float[] color_last, float[] color_next, int inext, double len)
/*      */   {
/* 2720 */     GL11.glBegin(5);
/* 2721 */     double tc = 0.0D;
/* 2722 */     for (int j = 0; j < ncp; j++) {
/* 2723 */       tc = j / ncp;
/* 2724 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2725 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2726 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/* 2727 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS + this.SLICE);
/* 2728 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2729 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     }
/*      */     
/* 2732 */     if ((gleGetJoinStyle() & 0x1000) == 4096)
/*      */     {
/*      */ 
/*      */ 
/* 2736 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2737 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2738 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/* 2739 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS + this.SLICE);
/* 2740 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2741 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/*      */     
/* 2744 */     GL11.glEnd();
/*      */     
/* 2746 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_segment_edge_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] norm_cont, int inext, double len)
/*      */   {
/* 2754 */     System.out.println("draw_segment_edge_n");
/* 2755 */     GL11.glBegin(5);
/* 2756 */     for (int j = 0; j < ncp; j++) {
/* 2757 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2758 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/* 2759 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     }
/*      */     
/* 2762 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2763 */       GL11.glNormal3d(norm_cont[0][0], norm_cont[0][1], norm_cont[0][2]);
/* 2764 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/* 2765 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/* 2767 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_segment_c_and_edge_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] norm_cont, float[] color_last, float[] color_next, int inext, double len)
/*      */   {
/* 2778 */     GL11.glBegin(5);
/* 2779 */     double tc = 0.0D;
/* 2780 */     for (int j = 0; j < ncp; j++) {
/* 2781 */       tc = j / ncp;
/* 2782 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2783 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2784 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2785 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 2787 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS + this.SLICE);
/* 2788 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2789 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2790 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     }
/*      */     
/* 2793 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2794 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2795 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2796 */       GL11.glNormal3d(norm_cont[0][0], norm_cont[0][1], norm_cont[0][2]);
/* 2797 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 2799 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS + this.SLICE);
/* 2800 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2801 */       GL11.glNormal3d(norm_cont[0][0], norm_cont[0][1], norm_cont[0][2]);
/* 2802 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/* 2804 */     GL11.glEnd();
/* 2805 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_segment_facet_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] norm_cont, int inext, double len)
/*      */   {
/* 2819 */     GL11.glBegin(5);
/* 2820 */     for (int j = 0; j < ncp - 1; j++) {
/* 2821 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2822 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/*      */ 
/*      */ 
/* 2826 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */       
/*      */ 
/*      */ 
/* 2830 */       GL11.glVertex3d(front_contour[(j + 1)][0], front_contour[(j + 1)][1], front_contour[(j + 1)][2]);
/*      */       
/*      */ 
/*      */ 
/* 2834 */       GL11.glVertex3d(back_contour[(j + 1)][0], back_contour[(j + 1)][1], back_contour[(j + 1)][2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2840 */     if ((gleGetJoinStyle() & 0x1000) == 4096)
/*      */     {
/*      */ 
/*      */ 
/* 2844 */       GL11.glNormal3d(norm_cont[(ncp - 1)][0], norm_cont[(ncp - 1)][1], norm_cont[(ncp - 1)][2]);
/* 2845 */       GL11.glVertex3d(front_contour[(ncp - 1)][0], front_contour[(ncp - 1)][1], front_contour[(ncp - 1)][2]);
/*      */       
/*      */ 
/*      */ 
/* 2849 */       GL11.glVertex3d(back_contour[(ncp - 1)][0], back_contour[(ncp - 1)][1], back_contour[(ncp - 1)][2]);
/*      */       
/*      */ 
/*      */ 
/* 2853 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/*      */ 
/*      */ 
/* 2857 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/*      */     
/*      */ 
/* 2861 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_segment_c_and_facet_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] norm_cont, float[] color_last, float[] color_next, int inext, double len)
/*      */   {
/* 2872 */     System.out.println("draw_segment_c_and_facet_n");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2883 */     GL11.glBegin(5);
/* 2884 */     for (int j = 0; j < ncp - 1; j++) {
/* 2885 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2886 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2887 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 2889 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2890 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2891 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */       
/* 2893 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2894 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2895 */       GL11.glVertex3d(front_contour[(j + 1)][0], front_contour[(j + 1)][1], front_contour[(j + 1)][2]);
/*      */       
/* 2897 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2898 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2899 */       GL11.glVertex3d(back_contour[(j + 1)][0], back_contour[(j + 1)][1], back_contour[(j + 1)][2]);
/*      */     }
/*      */     
/* 2902 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2903 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2904 */       GL11.glNormal3d(norm_cont[(ncp - 1)][0], norm_cont[(ncp - 1)][1], norm_cont[(ncp - 1)][2]);
/* 2905 */       GL11.glVertex3d(front_contour[(ncp - 1)][0], front_contour[(ncp - 1)][1], front_contour[(ncp - 1)][2]);
/*      */       
/* 2907 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2908 */       GL11.glNormal3d(norm_cont[(ncp - 1)][0], norm_cont[(ncp - 1)][1], norm_cont[(ncp - 1)][2]);
/* 2909 */       GL11.glVertex3d(back_contour[(ncp - 1)][0], back_contour[(ncp - 1)][1], back_contour[(ncp - 1)][2]);
/*      */       
/* 2911 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2912 */       GL11.glNormal3d(norm_cont[(ncp - 1)][0], norm_cont[(ncp - 1)][1], norm_cont[(ncp - 1)][2]);
/* 2913 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 2915 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2916 */       GL11.glNormal3d(norm_cont[(ncp - 1)][0], norm_cont[(ncp - 1)][1], norm_cont[(ncp - 1)][2]);
/* 2917 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/* 2919 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_binorm_segment_edge_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] front_norm, double[][] back_norm, int inext, double len)
/*      */   {
/* 2932 */     GL11.glBegin(5);
/* 2933 */     double tc = 0.0D;
/* 2934 */     for (int j = 0; j < ncp; j++) {
/* 2935 */       tc = j / ncp;
/* 2936 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2937 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 2938 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/* 2939 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS + this.SLICE);
/* 2940 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 2941 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     }
/*      */     
/* 2944 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2945 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2946 */       GL11.glNormal3d(front_norm[0][0], front_norm[0][1], front_norm[0][2]);
/* 2947 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/* 2948 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS + this.SLICE);
/* 2949 */       GL11.glNormal3d(back_norm[0][0], back_norm[0][1], back_norm[0][2]);
/* 2950 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/* 2952 */     GL11.glEnd();
/* 2953 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_binorm_segment_c_and_edge_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] front_norm, double[][] back_norm, float[] color_last, float[] color_next, int inext, double len)
/*      */   {
/* 2965 */     GL11.glBegin(5);
/* 2966 */     double tc = 0.0D;
/* 2967 */     for (int j = 0; j < ncp; j++)
/*      */     {
/* 2969 */       tc = j / ncp;
/*      */       
/* 2971 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2972 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2973 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 2974 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 2976 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS + this.SLICE);
/* 2977 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2978 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 2979 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     }
/*      */     
/* 2982 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2983 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2984 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 2985 */       GL11.glNormal3d(front_norm[0][0], front_norm[0][1], front_norm[0][2]);
/* 2986 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 2988 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS + this.SLICE);
/* 2989 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 2990 */       GL11.glNormal3d(back_norm[0][0], back_norm[0][1], back_norm[0][2]);
/* 2991 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/* 2993 */     GL11.glEnd();
/* 2994 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_binorm_segment_facet_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] front_norm, double[][] back_norm, int inext, double len)
/*      */   {
/* 3009 */     System.out.println("draw_binorm_segment_facet_n");
/* 3010 */     GL11.glBegin(5);
/* 3011 */     for (int j = 0; j < ncp - 1; j++) {
/* 3012 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 3013 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 3015 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 3016 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */       
/* 3018 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 3019 */       GL11.glVertex3d(front_contour[(j + 1)][0], front_contour[(j + 1)][1], front_contour[(j + 1)][2]);
/*      */       
/* 3021 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 3022 */       GL11.glVertex3d(back_contour[(j + 1)][0], back_contour[(j + 1)][1], back_contour[(j + 1)][2]);
/*      */     }
/*      */     
/* 3025 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 3026 */       GL11.glNormal3d(front_norm[(ncp - 1)][0], front_norm[(ncp - 1)][1], front_norm[(ncp - 1)][2]);
/* 3027 */       GL11.glVertex3d(front_contour[(ncp - 1)][0], front_contour[(ncp - 1)][1], front_contour[(ncp - 1)][2]);
/*      */       
/* 3029 */       GL11.glNormal3d(back_norm[(ncp - 1)][0], back_norm[(ncp - 1)][1], back_norm[(ncp - 1)][2]);
/* 3030 */       GL11.glVertex3d(back_contour[(ncp - 1)][0], back_contour[(ncp - 1)][1], back_contour[(ncp - 1)][2]);
/*      */       
/* 3032 */       GL11.glNormal3d(front_norm[(ncp - 1)][0], front_norm[(ncp - 1)][1], front_norm[(ncp - 1)][2]);
/* 3033 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 3035 */       GL11.glNormal3d(back_norm[(ncp - 1)][0], back_norm[(ncp - 1)][1], back_norm[(ncp - 1)][2]);
/* 3036 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/* 3038 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_binorm_segment_c_and_facet_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] front_norm, double[][] back_norm, float[] color_last, float[] color_next, int inext, double len)
/*      */   {
/* 3049 */     System.out.println("draw_binorm_segment_c_and_facet_n");
/* 3050 */     GL11.glBegin(5);
/* 3051 */     for (int j = 0; j < ncp - 1; j++) {
/* 3052 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 3053 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 3054 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 3056 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 3057 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 3058 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */       
/* 3060 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 3061 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 3062 */       GL11.glVertex3d(front_contour[(j + 1)][0], front_contour[(j + 1)][1], front_contour[(j + 1)][2]);
/*      */       
/* 3064 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 3065 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 3066 */       GL11.glVertex3d(back_contour[(j + 1)][0], back_contour[(j + 1)][1], back_contour[(j + 1)][2]);
/*      */     }
/*      */     
/* 3069 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 3070 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 3071 */       GL11.glNormal3d(front_norm[(ncp - 1)][0], front_norm[(ncp - 1)][1], front_norm[(ncp - 1)][2]);
/* 3072 */       GL11.glVertex3d(front_contour[(ncp - 1)][0], front_contour[(ncp - 1)][1], front_contour[(ncp - 1)][2]);
/*      */       
/* 3074 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 3075 */       GL11.glNormal3d(back_norm[(ncp - 1)][0], back_norm[(ncp - 1)][1], back_norm[(ncp - 1)][2]);
/* 3076 */       GL11.glVertex3d(back_contour[(ncp - 1)][0], back_contour[(ncp - 1)][1], back_contour[(ncp - 1)][2]);
/*      */       
/* 3078 */       GL11.glColor3f(color_last[0], color_last[1], color_last[2]);
/* 3079 */       GL11.glNormal3d(front_norm[(ncp - 1)][0], front_norm[(ncp - 1)][1], front_norm[(ncp - 1)][2]);
/* 3080 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 3082 */       GL11.glColor3f(color_next[0], color_next[1], color_next[2]);
/* 3083 */       GL11.glNormal3d(back_norm[(ncp - 1)][0], back_norm[(ncp - 1)][1], back_norm[(ncp - 1)][2]);
/* 3084 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     }
/* 3086 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void extrusion_round_or_cut_join(int ncp, double[][] contour, double[][] cont_normal, double[] up, int npoints, double[][] point_array, float[][] color_array, double[][][] xform_array)
/*      */   {
/* 3103 */     int i = 0;
/* 3104 */     int j = 0;
/* 3105 */     int inext = 0;
/* 3106 */     int inextnext = 0;
/* 3107 */     double[][] m = new double[4][4];
/* 3108 */     double tube_len = 0.0D;
/* 3109 */     double seg_len = 0.0D;
/* 3110 */     double[] diff = new double[3];
/* 3111 */     double[] bi_0 = new double[3];
/* 3112 */     double[] bi_1 = new double[3];
/* 3113 */     double[] bisector_0 = new double[3];
/* 3114 */     double[] bisector_1 = new double[3];
/* 3115 */     double[] cut_0 = new double[3];
/* 3116 */     double[] cut_1 = new double[3];
/* 3117 */     double[] lcut_0 = new double[3];
/* 3118 */     double[] lcut_1 = new double[3];
/* 3119 */     boolean valid_cut_0 = false;
/* 3120 */     boolean valid_cut_1 = false;
/* 3121 */     double[] end_point_0 = new double[3];
/* 3122 */     double[] end_point_1 = new double[3];
/* 3123 */     double[] torsion_point_0 = new double[3];
/* 3124 */     double[] torsion_point_1 = new double[3];
/* 3125 */     double[] isect_point = new double[3];
/* 3126 */     double[] origin = new double[3];
/* 3127 */     double[] neg_z = new double[3];
/* 3128 */     double[] yup = new double[3];
/* 3129 */     double[][] front_cap = (double[][])null;
/* 3130 */     double[][] back_cap = (double[][])null;
/* 3131 */     double[][] front_loop = (double[][])null;
/* 3132 */     double[][] back_loop = (double[][])null;
/* 3133 */     double[][] front_norm = (double[][])null;
/* 3134 */     double[][] back_norm = (double[][])null;
/* 3135 */     double[][] norm_loop = (double[][])null;
/* 3136 */     double[][] tmp = (double[][])null;
/* 3137 */     boolean[] front_is_trimmed = null;
/* 3138 */     boolean[] back_is_trimmed = null;
/* 3139 */     float[] front_color = null;
/* 3140 */     float[] back_color = null;
/* 3141 */     boolean join_style_is_cut = false;
/* 3142 */     double dot = 0.0D;
/* 3143 */     boolean first_time = true;
/* 3144 */     double[] cut_vec = null;
/* 3145 */     String cap_callback = null;
/* 3146 */     String tmp_cap_callback = null;
/*      */     
/* 3148 */     if ((gleGetJoinStyle() & 0x3) == 3) {
/* 3149 */       join_style_is_cut = true;
/* 3150 */       cap_callback = new String("cut");
/*      */     } else {
/* 3152 */       join_style_is_cut = false;
/* 3153 */       cap_callback = new String("round");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3160 */     if (up == null) {
/* 3161 */       yup[0] = 0.0D;
/* 3162 */       yup[1] = 1.0D;
/* 3163 */       yup[2] = 0.0D;
/*      */     } else {
/* 3165 */       yup = matrix.VEC_COPY(up);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3171 */     yup = up_sanity_check(yup, npoints, point_array);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3176 */     origin[0] = 0.0D;
/* 3177 */     origin[1] = 0.0D;
/* 3178 */     origin[2] = 0.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3183 */     neg_z[0] = 0.0D;
/* 3184 */     neg_z[1] = 0.0D;
/* 3185 */     neg_z[2] = 1.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3190 */     front_norm = new double[ncp][3];
/* 3191 */     back_norm = new double[ncp][3];
/* 3192 */     front_loop = new double[ncp][3];
/* 3193 */     back_loop = new double[ncp][3];
/* 3194 */     front_cap = new double[ncp][3];
/* 3195 */     back_cap = new double[ncp][3];
/* 3196 */     front_is_trimmed = new boolean[ncp];
/* 3197 */     back_is_trimmed = new boolean[ncp];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3206 */     i = 1;
/* 3207 */     inext = i;
/* 3208 */     inext = intersect.FIND_NON_DEGENERATE_POINT(inext, npoints, seg_len, diff, point_array);
/*      */     
/* 3210 */     seg_len = matrix.VEC_LENGTH(diff);
/* 3211 */     tube_len = seg_len;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3218 */     if (cont_normal != null) {
/* 3219 */       if (xform_array == null) {
/* 3220 */         norm_loop = front_norm;
/* 3221 */         back_norm = norm_loop;
/* 3222 */         for (j = 0; j < ncp; j++) {
/* 3223 */           norm_loop[j][0] = cont_normal[j][0];
/* 3224 */           norm_loop[j][1] = cont_normal[j][1];
/* 3225 */           norm_loop[j][2] = 0.0D;
/*      */         }
/*      */       }
/* 3228 */       for (j = 0; j < ncp; j++) {
/* 3229 */         front_norm[j] = matrix.NORM_XFORM_2X2(xform_array[(inext - 1)], cont_normal[j]);
/*      */         
/*      */ 
/* 3232 */         front_norm[j][2] = 0.0D;
/* 3233 */         back_norm[j][2] = 0.0D;
/*      */       }
/*      */     }
/*      */     
/* 3237 */     front_norm = back_norm = norm_loop = (double[][])null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3243 */     bi_0 = intersect.bisecting_plane(point_array[(i - 1)], point_array[i], point_array[inext]);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3250 */     valid_cut_0 = intersect.CUTTING_PLANE(cut_0, point_array[(i - 1)], point_array[i], point_array[inext]);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3257 */     yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3266 */     while (inext < npoints - 1)
/*      */     {
/* 3268 */       inextnext = inext;
/*      */       
/*      */ 
/*      */ 
/* 3272 */       inextnext = intersect.FIND_NON_DEGENERATE_POINT(inextnext, npoints, seg_len, diff, point_array);
/*      */       
/*      */ 
/*      */ 
/* 3276 */       seg_len = matrix.VEC_LENGTH(diff);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3281 */       bi_1 = intersect.bisecting_plane(point_array[i], point_array[inext], point_array[inextnext]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3289 */       valid_cut_1 = intersect.CUTTING_PLANE(cut_1, point_array[i], point_array[inext], point_array[inextnext]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3297 */       m = matrix.uviewpoint_d(point_array[i], point_array[inext], yup);
/* 3298 */       DoubleBuffer mbuffer = BufferUtils.createDoubleBuffer(16);
/* 3299 */       mbuffer.put(new double[] { m[0][0], m[0][1], m[0][2], m[0][3], m[1][0], m[1][1], m[1][2], m[1][3], m[2][0], m[2][1], m[2][2], m[2][3], m[3][0], m[3][1], m[3][2], m[3][3] });
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3307 */       mbuffer.flip();
/* 3308 */       GL11.glPushMatrix();
/* 3309 */       GL11.glMultMatrix(mbuffer);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3314 */       lcut_0 = matrix.MAT_DOT_VEC_3X3(m, cut_0);
/* 3315 */       lcut_1 = matrix.MAT_DOT_VEC_3X3(m, cut_1);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3320 */       bisector_0 = matrix.MAT_DOT_VEC_3X3(m, bi_0);
/* 3321 */       bisector_1 = matrix.MAT_DOT_VEC_3X3(m, bi_1);
/*      */       
/* 3323 */       neg_z[2] = (-tube_len);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3331 */       for (j = 0; j < ncp; j++)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 3336 */         if (xform_array == null) {
/* 3337 */           end_point_0 = matrix.VEC_COPY_2(contour[j]);
/* 3338 */           end_point_1 = matrix.VEC_COPY_2(contour[j]);
/* 3339 */           torsion_point_0 = matrix.VEC_COPY_2(contour[j]);
/* 3340 */           torsion_point_1 = matrix.VEC_COPY_2(contour[j]);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 3345 */           end_point_0 = matrix.MAT_DOT_VEC_2X3(xform_array[(inext - 1)], contour[j]);
/*      */           
/* 3347 */           torsion_point_0 = matrix.MAT_DOT_VEC_2X3(xform_array[inext], contour[j]);
/*      */           
/* 3349 */           end_point_1 = matrix.MAT_DOT_VEC_2X3(xform_array[inext], contour[j]);
/*      */           
/* 3351 */           torsion_point_1 = matrix.MAT_DOT_VEC_2X3(xform_array[(inext - 1)], contour[j]);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3360 */           if (cont_normal != null)
/*      */           {
/*      */ 
/*      */ 
/* 3364 */             back_norm[j] = matrix.NORM_XFORM_2X2(xform_array[inext], cont_normal[j]);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 3369 */         end_point_0[2] = 0.0D;
/* 3370 */         torsion_point_0[2] = 0.0D;
/* 3371 */         end_point_1[2] = (-tube_len);
/* 3372 */         torsion_point_1[2] = (-tube_len);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3384 */         if ((valid_cut_0) && (join_style_is_cut)) {
/* 3385 */           isect_point = intersect.INNERSECT(origin, lcut_0, end_point_0, end_point_1);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3405 */           if (lcut_0[2] < 0.0D) {
/* 3406 */             lcut_0 = matrix.VEC_SCALE(-1.0D, lcut_0);
/*      */           }
/* 3408 */           dot = lcut_0[0] * end_point_0[0];
/* 3409 */           dot += lcut_0[1] * end_point_0[1];
/*      */           
/* 3411 */           front_loop[j] = matrix.VEC_COPY(isect_point);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 3417 */           dot = 1.0D;
/* 3418 */           front_loop[j] = matrix.VEC_COPY(end_point_0);
/*      */         }
/*      */         
/* 3421 */         isect_point = intersect.INNERSECT(origin, bisector_0, end_point_0, torsion_point_1);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3445 */         if ((dot <= 0.0D) || (isect_point[2] < front_loop[j][2]))
/*      */         {
/*      */ 
/*      */ 
/* 3449 */           front_cap[j] = matrix.VEC_COPY(front_loop[j]);
/* 3450 */           front_loop[j] = matrix.VEC_COPY(isect_point);
/* 3451 */           front_is_trimmed[j] = true;
/*      */         } else {
/* 3453 */           front_is_trimmed[j] = false;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3461 */         if (front_loop[j][2] < -tube_len) {
/* 3462 */           front_loop[j] = matrix.VEC_COPY(end_point_1);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3479 */         if ((valid_cut_1) && (join_style_is_cut)) {
/* 3480 */           isect_point = intersect.INNERSECT(neg_z, lcut_1, end_point_1, end_point_0);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3494 */           if (lcut_1[2] > 0.0D) {
/* 3495 */             lcut_1 = matrix.VEC_SCALE(-1.0D, lcut_1);
/*      */           }
/* 3497 */           dot = lcut_1[0] * end_point_1[0];
/* 3498 */           dot += lcut_1[1] * end_point_1[1];
/*      */           
/* 3500 */           back_loop[j] = matrix.VEC_COPY(isect_point);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 3506 */           dot = 1.0D;
/* 3507 */           back_loop[j] = matrix.VEC_COPY(end_point_1);
/*      */         }
/*      */         
/* 3510 */         isect_point = intersect.INNERSECT(neg_z, bisector_1, torsion_point_0, end_point_1);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3537 */         if ((dot <= 0.0D) || (isect_point[2] > back_loop[j][2])) {
/* 3538 */           back_cap[j] = matrix.VEC_COPY(back_loop[j]);
/* 3539 */           back_loop[j] = matrix.VEC_COPY(isect_point);
/* 3540 */           back_is_trimmed[j] = true;
/*      */         } else {
/* 3542 */           back_is_trimmed[j] = false;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3550 */         if (back_loop[j][2] > 0.0D) {
/* 3551 */           back_loop[j] = matrix.VEC_COPY(end_point_0);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3574 */       if (xform_array == null) {
/* 3575 */         if (color_array == null) {
/* 3576 */           if (cont_normal == null) {
/* 3577 */             draw_segment_plain(ncp, front_loop, back_loop, inext, seg_len);
/*      */ 
/*      */           }
/* 3580 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 3581 */             draw_segment_facet_n(ncp, front_loop, back_loop, norm_loop, inext, seg_len);
/*      */           }
/*      */           else {
/* 3584 */             draw_segment_edge_n(ncp, front_loop, back_loop, norm_loop, inext, seg_len);
/*      */           }
/*      */           
/*      */ 
/*      */         }
/* 3589 */         else if (cont_normal == null) {
/* 3590 */           draw_segment_color(ncp, front_loop, back_loop, color_array[(inext - 1)], color_array[inext], inext, seg_len);
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/* 3595 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 3596 */           draw_segment_c_and_facet_n(ncp, front_loop, back_loop, norm_loop, color_array[(inext - 1)], color_array[inext], inext, seg_len);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 3602 */           draw_segment_c_and_edge_n(ncp, front_loop, back_loop, norm_loop, color_array[(inext - 1)], color_array[inext], inext, seg_len);
/*      */ 
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/* 3611 */       else if (color_array == null) {
/* 3612 */         if (cont_normal == null) {
/* 3613 */           draw_segment_plain(ncp, front_loop, back_loop, inext, seg_len);
/*      */ 
/*      */         }
/* 3616 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 3617 */           draw_binorm_segment_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, seg_len);
/*      */         }
/*      */         else
/*      */         {
/* 3621 */           draw_binorm_segment_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, seg_len);
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/* 3627 */       else if (cont_normal == null) {
/* 3628 */         draw_segment_color(ncp, front_loop, back_loop, color_array[(inext - 1)], color_array[inext], inext, seg_len);
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/* 3633 */       else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 3634 */         draw_binorm_segment_c_and_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, color_array[(inext - 1)], color_array[inext], inext, seg_len);
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/* 3643 */         draw_binorm_segment_c_and_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, color_array[(inext - 1)], color_array[inext], inext, seg_len);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3667 */       if (first_time) {
/* 3668 */         first_time = false;
/* 3669 */         tmp_cap_callback = cap_callback;
/* 3670 */         cap_callback = new String("null");
/* 3671 */         if ((gleGetJoinStyle() & 0x10) == 1) {
/* 3672 */           if (color_array != null) {
/* 3673 */             GL11.glColor3f(color_array[(inext - 1)][0], color_array[(inext - 1)][1], color_array[(inext - 1)][2]);
/*      */           }
/* 3675 */           draw_angle_style_front_cap(ncp, bisector_0, front_loop);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3689 */       if (color_array != null) {
/* 3690 */         front_color = color_array[(inext - 1)];
/* 3691 */         back_color = color_array[inext];
/*      */       } else {
/* 3693 */         front_color = null;
/* 3694 */         back_color = null;
/*      */       }
/*      */       
/* 3697 */       if (cont_normal == null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 3702 */         if (valid_cut_0) {
/* 3703 */           cut_vec = lcut_0;
/*      */         } else {
/* 3705 */           cut_vec = null;
/*      */         }
/* 3707 */         draw_fillets_and_join_plain(ncp, front_loop, front_cap, front_is_trimmed, origin, bisector_0, front_color, back_color, cut_vec, true, cap_callback);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3722 */         if (inext == npoints - 2) {
/* 3723 */           if ((gleGetJoinStyle() & 0x10) == 1) {
/* 3724 */             if (color_array != null) {
/* 3725 */               GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/*      */             }
/* 3727 */             draw_angle_style_back_cap(ncp, bisector_1, back_loop);
/*      */             
/* 3729 */             cap_callback = new String("null");
/*      */           }
/*      */           
/*      */ 
/*      */         }
/*      */         else {
/* 3735 */           cap_callback = tmp_cap_callback;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3745 */         if (valid_cut_1) {
/* 3746 */           cut_vec = lcut_1;
/*      */         } else {
/* 3748 */           cut_vec = null;
/*      */         }
/* 3750 */         draw_fillets_and_join_plain(ncp, back_loop, back_cap, back_is_trimmed, neg_z, bisector_1, back_color, front_color, cut_vec, false, cap_callback);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3767 */         if (valid_cut_0) {
/* 3768 */           cut_vec = lcut_0;
/*      */         } else {
/* 3770 */           cut_vec = null;
/*      */         }
/* 3772 */         draw_fillets_and_join_n_norms(ncp, front_loop, front_cap, front_is_trimmed, origin, bisector_0, front_norm, front_color, back_color, cut_vec, true, cap_callback);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3788 */         if (inext == npoints - 2) {
/* 3789 */           if ((gleGetJoinStyle() & 0x10) == 1) {
/* 3790 */             if (color_array != null) {
/* 3791 */               GL11.glColor3f(color_array[inext][0], color_array[inext][1], color_array[inext][2]);
/*      */             }
/* 3793 */             draw_angle_style_back_cap(ncp, bisector_1, back_loop);
/*      */             
/* 3795 */             cap_callback = new String("null");
/*      */           }
/*      */           
/*      */ 
/*      */         }
/*      */         else {
/* 3801 */           cap_callback = tmp_cap_callback;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3811 */         if (valid_cut_1) {
/* 3812 */           cut_vec = lcut_1;
/*      */         } else {
/* 3814 */           cut_vec = null;
/*      */         }
/* 3816 */         draw_fillets_and_join_n_norms(ncp, back_loop, back_cap, back_is_trimmed, neg_z, bisector_1, back_norm, back_color, front_color, cut_vec, false, cap_callback);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3837 */       GL11.glPopMatrix();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3842 */       tmp = front_norm;
/* 3843 */       front_norm = back_norm;
/* 3844 */       back_norm = tmp;
/*      */       
/* 3846 */       tube_len = seg_len;
/* 3847 */       i = inext;
/* 3848 */       inext = inextnext;
/* 3849 */       bi_0 = matrix.VEC_COPY(bi_1);
/* 3850 */       cut_0 = matrix.VEC_COPY(cut_1);
/* 3851 */       valid_cut_0 = valid_cut_1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3856 */       yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_fillets_and_join_plain(int ncp, double[][] trimmed_loop, double[][] untrimmed_loop, boolean[] is_trimmed, double[] bis_origin, double[] bis_vector, float[] front_color, float[] back_color, double[] cut_vector, boolean face, String cap_callback)
/*      */   {
/* 3874 */     int istop = 0;
/* 3875 */     int icnt = 0;
/* 3876 */     int icnt_prev = 0;
/* 3877 */     int iloop = 0;
/* 3878 */     double[][] cap_loop = (double[][])null;
/* 3879 */     double[] sect = new double[3];
/* 3880 */     double[] tmp_vec = new double[3];
/* 3881 */     int save_style = 0;
/* 3882 */     boolean was_trimmed = false;
/*      */     
/* 3884 */     cap_loop = new double[ncp + 3][3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3892 */     icnt = 0;
/* 3893 */     iloop = 0;
/* 3894 */     if (is_trimmed[0] == 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3902 */       if (((gleGetJoinStyle() & 0x3) == 3) && ((save_style & 0x1000) != 4096)) {
/* 3903 */         tmp_vec = matrix.VEC_SUM(trimmed_loop[0], bis_vector);
/* 3904 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[0], tmp_vec);
/*      */         
/*      */ 
/*      */ 
/* 3908 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 3909 */         iloop++;
/*      */       }
/* 3911 */       cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[0]);
/* 3912 */       iloop++;
/* 3913 */       icnt_prev = icnt;
/* 3914 */       icnt++;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 3919 */       was_trimmed = true;
/* 3920 */       while (is_trimmed[icnt] != 0) {
/* 3921 */         icnt_prev = icnt;
/* 3922 */         icnt++;
/* 3923 */         if (icnt >= ncp) {
/* 3924 */           return;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3936 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 3937 */       istop = ncp;
/*      */     } else {
/* 3939 */       istop = ncp - 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3946 */     save_style = gleGetJoinStyle();
/* 3947 */     gleSetJoinStyle(save_style & 0xEFFF);
/* 3949 */     for (; 
/* 3949 */         icnt_prev < istop; icnt %= ncp)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3962 */       if (((is_trimmed[icnt_prev] == 0) || (is_trimmed[icnt] == 0)) || (
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3968 */         (is_trimmed[icnt_prev] != 0) && (is_trimmed[icnt] == 0)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3979 */         sect = intersect.INNERSECT(bis_origin, bis_vector, untrimmed_loop[icnt_prev], trimmed_loop[icnt]);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3987 */         draw_fillet_triangle_plain(trimmed_loop[icnt_prev], trimmed_loop[icnt], sect, face, front_color, back_color);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3994 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/*      */         
/* 3996 */         iloop++;
/* 3997 */         cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[icnt]);
/*      */         
/* 3999 */         iloop++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 4004 */       if ((is_trimmed[icnt_prev] == 0) && (is_trimmed[icnt] == 0)) {
/* 4005 */         cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[icnt]);
/*      */         
/* 4007 */         iloop++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4013 */       if ((is_trimmed[icnt_prev] == 0) && (is_trimmed[icnt] != 0)) {
/* 4014 */         was_trimmed = true;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4025 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[icnt_prev], untrimmed_loop[icnt]);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4034 */         draw_fillet_triangle_plain(trimmed_loop[icnt_prev], trimmed_loop[icnt], sect, face, front_color, back_color);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4041 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/*      */         
/* 4043 */         iloop++;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 4048 */         if (iloop >= 3) {
/* 4049 */           if (cap_callback.equals("cut")) {
/* 4050 */             draw_cut_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, (double[][])null, face);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           }
/* 4058 */           else if (cap_callback.equals("round")) {
/* 4059 */             draw_round_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, (double[][])null, face);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4072 */         iloop = 0;
/*      */       }
/* 3949 */       icnt_prev++;icnt++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4082 */     icnt--;
/*      */     
/*      */ 
/* 4085 */     icnt += ncp;
/* 4086 */     icnt %= ncp;
/* 4087 */     if ((is_trimmed[icnt] == 0) && (iloop >= 2))
/*      */     {
/* 4089 */       tmp_vec = matrix.VEC_SUM(trimmed_loop[icnt], bis_vector);
/* 4090 */       sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[icnt], tmp_vec);
/*      */       
/*      */ 
/*      */ 
/* 4094 */       cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 4095 */       iloop++;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4102 */       if (!was_trimmed) {
/* 4103 */         gleSetJoinStyle(save_style);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4109 */       if (cap_callback.equals("cut")) {
/* 4110 */         draw_cut_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, (double[][])null, face);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/* 4118 */       else if (cap_callback.equals("round")) {
/* 4119 */         draw_round_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, (double[][])null, face);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4133 */     gleSetJoinStyle(save_style);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_fillets_and_join_n_norms(int ncp, double[][] trimmed_loop, double[][] untrimmed_loop, boolean[] is_trimmed, double[] bis_origin, double[] bis_vector, double[][] normals, float[] front_color, float[] back_color, double[] cut_vector, boolean face, String cap_callback)
/*      */   {
/* 4149 */     int istop = 0;
/* 4150 */     int icnt = 0;
/* 4151 */     int icnt_prev = 0;
/* 4152 */     int iloop = 0;
/* 4153 */     double[][] cap_loop = (double[][])null;
/* 4154 */     double[][] norm_loop = (double[][])null;
/* 4155 */     double[] sect = new double[3];
/* 4156 */     double[] tmp_vec = new double[3];
/* 4157 */     int save_style = 0;
/* 4158 */     boolean was_trimmed = false;
/*      */     
/* 4160 */     cap_loop = new double[ncp + 3][3];
/* 4161 */     norm_loop = new double[ncp + 3][3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4167 */     icnt = 0;
/* 4168 */     iloop = 0;
/* 4169 */     if (is_trimmed[0] == 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4177 */       if (((gleGetJoinStyle() & 0x3) == 3) && ((save_style & 0x1000) != 4096))
/*      */       {
/* 4179 */         tmp_vec = matrix.VEC_SUM(trimmed_loop[0], bis_vector);
/* 4180 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[0], tmp_vec);
/*      */         
/*      */ 
/*      */ 
/* 4184 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 4185 */         norm_loop[iloop] = matrix.VEC_COPY(normals[0]);
/* 4186 */         iloop++;
/*      */       }
/* 4188 */       cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[0]);
/* 4189 */       norm_loop[iloop] = matrix.VEC_COPY(normals[0]);
/* 4190 */       iloop++;
/* 4191 */       icnt_prev = icnt;
/* 4192 */       icnt++;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 4197 */       was_trimmed = true;
/* 4198 */       while (is_trimmed[icnt] != 0) {
/* 4199 */         icnt_prev = icnt;
/* 4200 */         icnt++;
/* 4201 */         if (icnt >= ncp) {
/* 4202 */           return;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4214 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 4215 */       istop = ncp;
/*      */     } else {
/* 4217 */       istop = ncp - 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4224 */     save_style = gleGetJoinStyle();
/* 4225 */     gleSetJoinStyle(save_style & 0xEFFF);
/* 4227 */     for (; 
/* 4227 */         icnt_prev < istop; icnt %= ncp)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4241 */       if (((is_trimmed[icnt_prev] == 0) || (is_trimmed[icnt] == 0)) || (
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4247 */         (is_trimmed[icnt_prev] != 0) && (is_trimmed[icnt] == 0)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4258 */         sect = intersect.INNERSECT(bis_origin, bis_vector, untrimmed_loop[icnt_prev], trimmed_loop[icnt]);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4266 */         draw_fillet_triangle_n_norms(trimmed_loop[icnt_prev], trimmed_loop[icnt], sect, face, front_color, back_color, normals[icnt_prev], normals[icnt]);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4274 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 4275 */         norm_loop[iloop] = matrix.VEC_COPY(normals[icnt_prev]);
/* 4276 */         iloop++;
/* 4277 */         cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[icnt]);
/* 4278 */         norm_loop[iloop] = matrix.VEC_COPY(normals[icnt]);
/* 4279 */         iloop++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4285 */       if ((is_trimmed[icnt_prev] == 0) && (is_trimmed[icnt] == 0)) {
/* 4286 */         cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[icnt]);
/* 4287 */         norm_loop[iloop] = matrix.VEC_COPY(normals[icnt]);
/* 4288 */         iloop++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4294 */       if ((is_trimmed[icnt_prev] == 0) && (is_trimmed[icnt] != 0)) {
/* 4295 */         was_trimmed = true;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4306 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[icnt_prev], untrimmed_loop[icnt]);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4314 */         draw_fillet_triangle_n_norms(trimmed_loop[icnt_prev], trimmed_loop[icnt], sect, face, front_color, back_color, normals[icnt_prev], normals[icnt]);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4323 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4329 */         if ((gleGetJoinStyle() & 0x100) == 256) {
/* 4330 */           norm_loop[iloop] = matrix.VEC_COPY(normals[icnt_prev]);
/*      */         } else {
/* 4332 */           norm_loop[iloop] = matrix.VEC_COPY(normals[icnt]);
/*      */         }
/* 4334 */         iloop++;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 4339 */         if (iloop >= 3) {
/* 4340 */           if (cap_callback.equals("cut")) {
/* 4341 */             draw_cut_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, norm_loop, face);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           }
/* 4349 */           else if (cap_callback.equals("round")) {
/* 4350 */             draw_round_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, norm_loop, face);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4364 */         iloop = 0;
/*      */       }
/* 4227 */       icnt_prev++;icnt++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4372 */     icnt--;
/*      */     
/*      */ 
/* 4375 */     icnt += ncp;
/* 4376 */     icnt %= ncp;
/* 4377 */     if ((is_trimmed[icnt] == 0) && (iloop >= 2))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4385 */       if (((gleGetJoinStyle() & 0x3) == 3) && ((save_style & 0x1000) != 4096))
/*      */       {
/* 4387 */         tmp_vec = matrix.VEC_SUM(trimmed_loop[icnt], bis_vector);
/* 4388 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[icnt], tmp_vec);
/*      */         
/*      */ 
/*      */ 
/* 4392 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 4393 */         norm_loop[iloop] = matrix.VEC_COPY(normals[icnt]);
/* 4394 */         iloop++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4402 */       if (!was_trimmed) {
/* 4403 */         gleSetJoinStyle(save_style);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4409 */       if (cap_callback.equals("cut")) {
/* 4410 */         draw_cut_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, norm_loop, face);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/* 4418 */       else if (cap_callback.equals("round")) {
/* 4419 */         draw_round_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, norm_loop, face);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4433 */     gleSetJoinStyle(save_style);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void draw_fillet_triangle_plain(double[] va, double[] vb, double[] vc, boolean face, float[] front_color, float[] back_color)
/*      */   {
/* 4458 */     if (front_color != null) {
/* 4459 */       GL11.glColor3f(front_color[0], front_color[1], front_color[2]);
/*      */     }
/* 4461 */     GL11.glBegin(5);
/* 4462 */     if (face) {
/* 4463 */       GL11.glVertex3d(va[0], va[1], va[2]);
/* 4464 */       GL11.glVertex3d(vb[0], vb[1], vb[2]);
/*      */     } else {
/* 4466 */       GL11.glVertex3d(vb[0], vb[1], vb[2]);
/* 4467 */       GL11.glVertex3d(va[0], va[1], va[2]);
/*      */     }
/* 4469 */     GL11.glVertex3d(vc[0], vc[1], vc[2]);
/* 4470 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_fillet_triangle_n_norms(double[] va, double[] vb, double[] vc, boolean face, float[] front_color, float[] back_color, double[] na, double[] nb)
/*      */   {
/* 4498 */     if (front_color != null) {
/* 4499 */       GL11.glColor3f(front_color[0], front_color[1], front_color[2]);
/*      */     }
/*      */     
/* 4502 */     GL11.glBegin(5);
/* 4503 */     if ((gleGetJoinStyle() & 0x100) == 256) {
/* 4504 */       GL11.glNormal3d(na[0], na[1], na[2]);
/* 4505 */       if (face) {
/* 4506 */         GL11.glVertex3d(va[0], va[1], va[2]);
/* 4507 */         GL11.glVertex3d(vb[0], vb[1], vb[2]);
/*      */       } else {
/* 4509 */         GL11.glVertex3d(vb[0], vb[1], vb[2]);
/* 4510 */         GL11.glVertex3d(va[0], va[1], va[2]);
/*      */       }
/* 4512 */       GL11.glNormal3d(vc[0], vc[1], vc[2]);
/*      */     } else {
/* 4514 */       if (face) {
/* 4515 */         GL11.glNormal3d(na[0], na[1], na[2]);
/* 4516 */         GL11.glVertex3d(va[0], va[1], va[2]);
/* 4517 */         GL11.glNormal3d(nb[0], nb[1], nb[2]);
/* 4518 */         GL11.glVertex3d(vb[0], vb[1], vb[2]);
/*      */       } else {
/* 4520 */         GL11.glNormal3d(nb[0], nb[1], nb[2]);
/* 4521 */         GL11.glVertex3d(vb[0], vb[1], vb[2]);
/* 4522 */         GL11.glNormal3d(na[0], na[1], na[2]);
/* 4523 */         GL11.glVertex3d(va[0], va[1], va[2]);
/* 4524 */         GL11.glNormal3d(nb[0], nb[1], nb[2]);
/*      */       }
/* 4526 */       GL11.glVertex3d(vc[0], vc[1], vc[2]);
/*      */     }
/* 4528 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_cut_style_cap_callback(int iloop, double[][] cap, float[] face_color, double[] cut_vector, double[] bisect_vector, double[][] norms, boolean frontwards)
/*      */   {
/* 4546 */     double[] previous_vertex = null;
/* 4547 */     double[] first_vertex = null;
/* 4548 */     boolean is_colinear = false;
/*      */     
/* 4550 */     GLUtessellator tobj = GLU.gluNewTess();
/* 4551 */     tobj.gluTessProperty(100140, 100130.0D);
/* 4552 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 4553 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 4554 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 4555 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/* 4557 */     if (face_color != null) {
/* 4558 */       GL11.glColor3f(face_color[0], face_color[1], face_color[2]);
/*      */     }
/*      */     
/* 4561 */     if (frontwards)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4566 */       if (cut_vector != null)
/*      */       {
/*      */ 
/*      */ 
/* 4570 */         if (cut_vector[2] < 0.0D) {
/* 4571 */           cut_vector = matrix.VEC_SCALE(-1.0D, cut_vector);
/*      */         }
/* 4573 */         GL11.glNormal3d(cut_vector[0], cut_vector[1], cut_vector[2]);
/*      */       }
/*      */       
/* 4576 */       tobj.gluTessBeginPolygon(null);
/* 4577 */       tobj.gluTessBeginContour();
/*      */       
/* 4579 */       first_vertex = null;
/* 4580 */       previous_vertex = cap[(iloop - 1)];
/* 4581 */       for (int i = 0; i < iloop - 1; i++) {
/* 4582 */         is_colinear = intersect.COLINEAR(previous_vertex, cap[i], cap[(i + 1)]);
/*      */         
/* 4584 */         if (!is_colinear)
/*      */         {
/* 4586 */           tobj.gluTessVertex(cap[i], 0, cap[i]);
/* 4587 */           previous_vertex = cap[i];
/* 4588 */           if (first_vertex == null) {
/* 4589 */             first_vertex = previous_vertex;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 4594 */       if (first_vertex == null) {
/* 4595 */         first_vertex = cap[0];
/*      */       }
/* 4597 */       is_colinear = intersect.COLINEAR(previous_vertex, cap[(iloop - 1)], first_vertex);
/*      */       
/*      */ 
/* 4600 */       if (!is_colinear)
/*      */       {
/* 4602 */         tobj.gluTessVertex(cap[(iloop - 1)], 0, cap[(iloop - 1)]);
/*      */       }
/*      */       
/*      */ 
/* 4606 */       tobj.gluTessEndContour();
/* 4607 */       tobj.gluTessEndPolygon();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 4612 */       if (cut_vector != null)
/*      */       {
/*      */ 
/*      */ 
/* 4616 */         if (cut_vector[2] > 0.0D) {
/* 4617 */           cut_vector = matrix.VEC_SCALE(-1.0D, cut_vector);
/*      */         }
/* 4619 */         GL11.glNormal3d(cut_vector[0], cut_vector[1], cut_vector[2]);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4625 */       tobj.gluTessBeginPolygon(null);
/* 4626 */       tobj.gluTessBeginContour();
/*      */       
/* 4628 */       first_vertex = null;
/* 4629 */       previous_vertex = cap[0];
/* 4630 */       for (int i = iloop - 1; i > 0; i--) {
/* 4631 */         is_colinear = intersect.COLINEAR(previous_vertex, cap[i], cap[(i - 1)]);
/*      */         
/* 4633 */         if (!is_colinear)
/*      */         {
/* 4635 */           tobj.gluTessVertex(cap[i], 0, cap[i]);
/* 4636 */           previous_vertex = cap[i];
/* 4637 */           if (first_vertex == null) {
/* 4638 */             first_vertex = previous_vertex;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 4643 */       if (first_vertex == null) {
/* 4644 */         first_vertex = cap[(iloop - 1)];
/*      */       }
/* 4646 */       is_colinear = intersect.COLINEAR(previous_vertex, cap[0], first_vertex);
/*      */       
/* 4648 */       if (!is_colinear)
/*      */       {
/* 4650 */         tobj.gluTessVertex(cap[0], 0, cap[0]);
/*      */       }
/*      */       
/*      */ 
/* 4654 */       tobj.gluTessEndContour();
/* 4655 */       tobj.gluTessEndPolygon();
/*      */     }
/*      */     
/* 4658 */     tobj.gluDeleteTess();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void draw_round_style_cap_callback(int ncp, double[][] cap, float[] face_color, double[] cut, double[] bi, double[][] norms, boolean frontwards)
/*      */   {
/* 4675 */     double[] axis = new double[3];
/* 4676 */     double[] xycut = new double[3];
/* 4677 */     double theta = 0.0D;
/* 4678 */     double[][] last_contour = (double[][])null;
/* 4679 */     double[][] next_contour = (double[][])null;
/* 4680 */     double[][] last_norm = (double[][])null;
/* 4681 */     double[][] next_norm = (double[][])null;
/* 4682 */     double[] cap_z = null;
/* 4683 */     double[][] tmp = (double[][])null;
/* 4684 */     int i = 0;
/* 4685 */     int j = 0;
/* 4686 */     int k = 0;
/* 4687 */     double[][] m = new double[4][4];
/*      */     
/* 4689 */     if (face_color != null) {
/* 4690 */       GL11.glColor3f(face_color[0], face_color[1], face_color[2]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4700 */     if (cut == null) {
/* 4701 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4707 */     if (cut[2] > 0.0D) {
/* 4708 */       cut = matrix.VEC_SCALE(-1.0D, cut);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4714 */     if (bi[2] < 0.0D) {
/* 4715 */       bi = matrix.VEC_SCALE(-1.0D, bi);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4722 */     axis = matrix.VEC_CROSS_PRODUCT(cut, bi);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4728 */     if (!frontwards) {
/* 4729 */       cut = matrix.VEC_SCALE(-1.0D, cut);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4736 */     xycut[0] = 0.0D;
/* 4737 */     xycut[1] = 0.0D;
/* 4738 */     xycut[2] = 1.0D;
/* 4739 */     xycut = matrix.VEC_PERP(cut, xycut);
/* 4740 */     xycut = matrix.VEC_NORMALIZE(xycut);
/* 4741 */     theta = matrix.VEC_DOT_PRODUCT(xycut, cut);
/*      */     
/* 4743 */     theta = Math.acos(theta);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4748 */     theta /= this.__ROUND_TESS_PIECES;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4753 */     m = matrix.urot_axis_d(theta, axis);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4764 */     last_contour = new double[ncp][3];
/* 4765 */     next_contour = new double[ncp][3];
/* 4766 */     cap_z = new double[ncp];
/* 4767 */     last_norm = new double[ncp][3];
/* 4768 */     next_norm = new double[ncp][3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4773 */     if (frontwards) {
/* 4774 */       for (j = 0; j < ncp; j++) {
/* 4775 */         last_contour[j][0] = cap[j][0];
/* 4776 */         last_contour[j][1] = cap[j][1];
/* 4777 */         last_contour[j][2] = (cap_z[j] = cap[j][2]);
/*      */       }
/*      */       
/* 4780 */       if (norms != null) {
/* 4781 */         for (j = 0; j < ncp; j++) {
/* 4782 */           last_norm[j] = matrix.VEC_COPY(norms[j]);
/*      */ 
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 4793 */       for (j = 0; j < ncp; j++) {
/* 4794 */         k = ncp - j - 1;
/* 4795 */         last_contour[k][0] = cap[j][0];
/* 4796 */         last_contour[k][1] = cap[j][1];
/* 4797 */         last_contour[k][2] = (cap_z[k] = cap[j][2]);
/*      */       }
/*      */       
/* 4800 */       if (norms != null) {
/* 4801 */         if ((gleGetJoinStyle() & 0x100) == 256) {
/* 4802 */           for (j = 0; j < ncp - 1; j++) {
/* 4803 */             k = ncp - j - 2;
/* 4804 */             last_norm[k] = matrix.VEC_COPY(norms[j]);
/*      */           }
/*      */         }
/* 4807 */         for (j = 0; j < ncp; j++) {
/* 4808 */           k = ncp - j - 1;
/* 4809 */           last_norm[k] = matrix.VEC_COPY(norms[j]);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4819 */     for (i = 0; i < this.__ROUND_TESS_PIECES; i++) {
/* 4820 */       for (j = 0; j < ncp; j++) {
/* 4821 */         next_contour[j][2] -= cap_z[j];
/* 4822 */         last_contour[j][2] -= cap_z[j];
/* 4823 */         next_contour[j] = matrix.MAT_DOT_VEC_3X3(m, last_contour[j]);
/* 4824 */         next_contour[j][2] += cap_z[j];
/* 4825 */         last_contour[j][2] += cap_z[j];
/*      */       }
/*      */       
/* 4828 */       if (norms != null) {
/* 4829 */         for (j = 0; j < ncp; j++) {
/* 4830 */           next_norm[j] = matrix.MAT_DOT_VEC_3X3(m, last_norm[j]);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4837 */       if (norms == null) {
/* 4838 */         draw_segment_plain(ncp, next_contour, last_contour, 0, 0.0D);
/*      */ 
/*      */       }
/* 4841 */       else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 4842 */         draw_binorm_segment_facet_n(ncp, next_contour, last_contour, next_norm, last_norm, 0, 0.0D);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 4848 */         draw_binorm_segment_edge_n(ncp, next_contour, last_contour, next_norm, last_norm, 0, 0.0D);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4857 */       tmp = next_contour;
/* 4858 */       next_contour = last_contour;
/* 4859 */       last_contour = tmp;
/*      */       
/* 4861 */       tmp = next_norm;
/* 4862 */       next_norm = last_norm;
/* 4863 */       last_norm = tmp;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   class tessellCallBack
/*      */     implements GLUtessellatorCallback
/*      */   {
/*      */     public tessellCallBack(GLU glu) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void begin(int type)
/*      */     {
/* 4887 */       GL11.glBegin(type);
/*      */     }
/*      */     
/*      */ 
/*      */     public void end() {}
/*      */     
/*      */ 
/*      */     public void vertex(Object vertexData)
/*      */     {
/* 4896 */       if ((vertexData instanceof double[])) {
/* 4897 */         double[] pointer = (double[])vertexData;
/* 4898 */         if (pointer.length == 6) {
/* 4899 */           GL11.glColor3d(pointer[3], pointer[4], pointer[5]);
/*      */         }
/* 4901 */         GL11.glVertex3d(pointer[0], pointer[1], pointer[2]);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void vertexData(Object vertexData, Object polygonData) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void combine(double[] coords, Object[] data, float[] weight, Object[] outData)
/*      */     {
/* 4917 */       double[] vertex = new double[6];
/*      */       
/*      */ 
/* 4920 */       vertex[0] = coords[0];
/* 4921 */       vertex[1] = coords[1];
/* 4922 */       vertex[2] = coords[2];
/* 4923 */       for (int i = 3; i < 6; 
/*      */           
/* 4925 */           i++) {
/* 4926 */         vertex[i] = (weight[0] * ((double[])(double[])data[0])[i] + weight[1] * ((double[])(double[])data[1])[i] + weight[2] * ((double[])(double[])data[2])[i] + weight[3] * ((double[])(double[])data[3])[i]);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4932 */       outData[0] = vertex;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public void combineData(double[] coords, Object[] data, float[] weight, Object[] outData, Object polygonData) {}
/*      */     
/*      */ 
/*      */     public void error(int errnum)
/*      */     {
/* 4942 */       String estring = GLU.gluErrorString(errnum);
/* 4943 */       System.err.println("Tessellation Error: " + estring);
/*      */     }
/*      */     
/*      */     public void beginData(int type, Object polygonData) {}
/*      */     
/*      */     public void endData(Object polygonData) {}
/*      */     
/*      */     public void edgeFlag(boolean boundaryEdge) {}
/*      */     
/*      */     public void edgeFlagData(boolean boundaryEdge, Object polygonData) {}
/*      */     
/*      */     public void errorData(int errnum, Object polygonData) {}
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/com/sasmaster/glelwjgl/java/CoreGLE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */