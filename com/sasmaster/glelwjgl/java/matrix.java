/*      */ package com.sasmaster.glelwjgl.java;
/*      */ 
/*      */ import java.nio.DoubleBuffer;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class matrix
/*      */ {
/*   46 */   public static final String VERSION = new String("$Id: matrix.java,v 1.2 1998/05/05 23:31:09 descarte Exp descarte $");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final double[][] ROTX_CS(double cosine, double sine)
/*      */   {
/*   61 */     double[][] m = new double[4][4];
/*   62 */     m[0][0] = 1.0D;
/*   63 */     m[0][1] = 0.0D;
/*   64 */     m[0][2] = 0.0D;
/*   65 */     m[0][3] = 0.0D;
/*      */     
/*   67 */     m[1][0] = 0.0D;
/*   68 */     m[1][1] = cosine;
/*   69 */     m[1][2] = sine;
/*   70 */     m[1][3] = 0.0D;
/*      */     
/*   72 */     m[2][0] = 0.0D;
/*   73 */     m[2][1] = (-sine);
/*   74 */     m[2][2] = cosine;
/*   75 */     m[2][3] = 0.0D;
/*      */     
/*   77 */     m[3][0] = 0.0D;
/*   78 */     m[3][1] = 0.0D;
/*   79 */     m[3][2] = 0.0D;
/*   80 */     m[3][3] = 1.0D;
/*   81 */     return m;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static final double[][] ROTY_CS(double cosine, double sine)
/*      */   {
/*   88 */     double[][] m = new double[4][4];
/*   89 */     m[0][0] = cosine;
/*   90 */     m[0][1] = 0.0D;
/*   91 */     m[0][2] = (-sine);
/*   92 */     m[0][3] = 0.0D;
/*      */     
/*   94 */     m[1][0] = 0.0D;
/*   95 */     m[1][1] = 1.0D;
/*   96 */     m[1][2] = 0.0D;
/*   97 */     m[1][3] = 0.0D;
/*      */     
/*   99 */     m[2][0] = sine;
/*  100 */     m[2][1] = 0.0D;
/*  101 */     m[2][2] = cosine;
/*  102 */     m[2][3] = 0.0D;
/*      */     
/*  104 */     m[3][0] = 0.0D;
/*  105 */     m[3][1] = 0.0D;
/*  106 */     m[3][2] = 0.0D;
/*  107 */     m[3][3] = 1.0D;
/*      */     
/*  109 */     return m;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static final double[][] ROTZ_CS(double cosine, double sine)
/*      */   {
/*  116 */     double[][] m = new double[4][4];
/*  117 */     m[0][0] = cosine;
/*  118 */     m[0][1] = sine;
/*  119 */     m[0][2] = 0.0D;
/*  120 */     m[0][3] = 0.0D;
/*      */     
/*  122 */     m[1][0] = (-sine);
/*  123 */     m[1][1] = cosine;
/*  124 */     m[1][2] = 0.0D;
/*  125 */     m[1][3] = 0.0D;
/*      */     
/*  127 */     m[2][0] = 0.0D;
/*  128 */     m[2][1] = 0.0D;
/*  129 */     m[2][2] = 1.0D;
/*  130 */     m[2][3] = 0.0D;
/*      */     
/*  132 */     m[3][0] = 0.0D;
/*  133 */     m[3][1] = 0.0D;
/*  134 */     m[3][2] = 0.0D;
/*  135 */     m[3][3] = 1.0D;
/*      */     
/*  137 */     return m;
/*      */   }
/*      */   
/*      */   private static DoubleBuffer getBufferedMatrix(double[][] m) {
/*  141 */     DoubleBuffer mbuffer = BufferUtils.createDoubleBuffer(16);
/*  142 */     mbuffer.put(new double[] { m[0][0], m[0][1], m[0][2], m[0][3], m[1][0], m[1][1], m[1][2], m[1][3], m[2][0], m[2][1], m[2][2], m[2][3], m[3][0], m[3][1], m[3][2], m[3][3] });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  150 */     mbuffer.flip();
/*  151 */     return mbuffer;
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
/*      */   public static final double[][] urotx_cs_d(double cosine, double sine)
/*      */   {
/*  164 */     return ROTX_CS(cosine, sine);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final void rotx_cs_d(double cosine, double sine)
/*      */   {
/*  172 */     GL11.glMultMatrix(getBufferedMatrix(urotx_cs_d(cosine, sine)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[][] uroty_cs_d(double cosine, double sine)
/*      */   {
/*  179 */     return ROTX_CS(cosine, sine);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final void roty_cs_d(double cosine, double sine)
/*      */   {
/*  186 */     GL11.glMultMatrix(getBufferedMatrix(uroty_cs_d(cosine, sine)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[][] urotz_cs_d(double cosine, double sine)
/*      */   {
/*  193 */     return ROTX_CS(cosine, sine);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final void rotz_cs_d(double cosine, double sine)
/*      */   {
/*  200 */     GL11.glMultMatrix(getBufferedMatrix(urotz_cs_d(cosine, sine)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[][] urot_cs_d(double cosine, double sine, char axis)
/*      */   {
/*  208 */     switch (axis) {
/*      */     case 'X': 
/*      */     case 'x': 
/*  211 */       return urotx_cs_d(cosine, sine);
/*      */     
/*      */     case 'Y': 
/*      */     case 'y': 
/*  215 */       return uroty_cs_d(cosine, sine);
/*      */     
/*      */     case 'Z': 
/*      */     case 'z': 
/*  219 */       return urotz_cs_d(cosine, sine);
/*      */     }
/*      */     
/*  222 */     return (double[][])null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final void rot_cs_d(double cosine, double sine, char axis)
/*      */   {
/*  229 */     GL11.glMultMatrix(getBufferedMatrix(urot_cs_d(cosine, sine, axis)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[][] urot_prince_d(double theta, char axis)
/*      */   {
/*  238 */     return urot_cs_d(Math.cos(theta), Math.sin(theta), axis);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final void rot_prince_d(double theta, char axis)
/*      */   {
/*  247 */     GL11.glMultMatrix(getBufferedMatrix(urot_prince_d(theta, axis)));
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
/*      */   public static final void rot_axis_d(double omega, double[] axis)
/*      */   {
/*  260 */     GL11.glMultMatrix(getBufferedMatrix(urot_axis_d(omega, axis)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final void rot_about_axis_d(double angle, double[] axis)
/*      */   {
/*  267 */     GL11.glMultMatrix(getBufferedMatrix(urot_about_axis_d(angle, axis)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final void rot_omega_d(double[] axis)
/*      */   {
/*  274 */     GL11.glMultMatrix(getBufferedMatrix(urot_omega_d(axis)));
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
/*      */   public static final double[][] urot_axis_d(double omega, double[] axis)
/*      */   {
/*  288 */     double[][] m = new double[4][4];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  296 */     if (axis.length != 3) {
/*  297 */       throw new GLEException("Length of axis parameter != 3. This is not a valid vector!");
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
/*  316 */     double tmp = omega / 2.0D;
/*  317 */     double s = Math.sin(tmp);
/*  318 */     double c = Math.cos(tmp);
/*      */     
/*  320 */     double ssq = s * s;
/*  321 */     double csq = c * c;
/*      */     
/*  323 */     m[0][0] = (m[1][1] = m[2][2] = csq - ssq);
/*      */     
/*  325 */     ssq *= 2.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  330 */     m[0][0] += ssq * axis[0] * axis[0];
/*  331 */     m[1][1] += ssq * axis[1] * axis[1];
/*  332 */     m[2][2] += ssq * axis[2] * axis[2];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  337 */     m[0][1] = (m[1][0] = axis[0] * axis[1] * ssq);
/*  338 */     m[1][2] = (m[2][1] = axis[1] * axis[2] * ssq);
/*  339 */     m[2][0] = (m[0][2] = axis[2] * axis[0] * ssq);
/*      */     
/*  341 */     double cts = 2.0D * c * s;
/*      */     
/*  343 */     tmp = cts * axis[2];
/*  344 */     m[0][1] += tmp;
/*  345 */     m[1][0] -= tmp;
/*      */     
/*  347 */     tmp = cts * axis[0];
/*  348 */     m[1][2] += tmp;
/*  349 */     m[2][1] -= tmp;
/*      */     
/*  351 */     tmp = cts * axis[1];
/*  352 */     m[2][0] += tmp;
/*  353 */     m[0][2] -= tmp;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  358 */     m[0][3] = (m[1][3] = m[2][3] = m[3][2] = m[3][1] = m[3][0] = 0.0D);
/*  359 */     m[3][3] = 1.0D;
/*      */     
/*  361 */     return m;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[][] urot_about_axis_d(double angle, double[] axis)
/*      */   {
/*  370 */     double[][] m = (double[][])null;
/*      */     
/*  372 */     double[] ax = new double[3];
/*  373 */     double ang = angle;
/*      */     
/*  375 */     if (axis.length != 3) {
/*  376 */       throw new GLEException("Length of axis parameter != 3. This is not a valid vector!");
/*      */     }
/*      */     
/*  379 */     ang *= 0.017453292519943295D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  384 */     double len = axis[0] * axis[0] + axis[1] * axis[1] + axis[2] * axis[2];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  390 */     if (len != 1.0D) {
/*  391 */       len = 1.0D / Math.sqrt(len);
/*  392 */       axis[0] *= len;
/*  393 */       axis[1] *= len;
/*  394 */       axis[2] *= len;
/*  395 */       m = urot_axis_d(ang, ax);
/*      */     } else {
/*  397 */       m = urot_axis_d(ang, axis);
/*      */     }
/*      */     
/*  400 */     return m;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[][] urot_omega_d(double[] axis)
/*      */   {
/*  408 */     double[][] m = (double[][])null;
/*      */     
/*  410 */     double[] ax = new double[3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  415 */     double len = axis[0] * axis[0] + axis[1] * axis[1] + axis[2] * axis[2];
/*      */     
/*  417 */     len = 1.0D / Math.sqrt(len);
/*  418 */     axis[0] *= len;
/*  419 */     axis[1] *= len;
/*  420 */     axis[2] *= len;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  425 */     return urot_axis_d(len, ax);
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
/*      */   public static final double[] VEC_ZERO()
/*      */   {
/*  438 */     double[] vtmp = new double[3];
/*  439 */     vtmp[0] = (vtmp[1] = vtmp[2] = 0.0D);
/*  440 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_NORMALIZE(double[] v)
/*      */   {
/*  447 */     double[] vtmp = new double[3];
/*  448 */     double vlen = VEC_LENGTH(v);
/*  449 */     if (vlen != 0.0D) {
/*  450 */       vlen = 1.0D / vlen;
/*  451 */       v[0] *= vlen;
/*  452 */       v[1] *= vlen;
/*  453 */       v[2] *= vlen;
/*      */     }
/*  455 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_REFLECT(double[] v, double[] n)
/*      */   {
/*  463 */     double[] vtmp = new double[3];
/*  464 */     double dot = VEC_DOT_PRODUCT(v, n);
/*  465 */     v[0] -= 2.0D * dot * n[0];
/*  466 */     v[1] -= 2.0D * dot * n[1];
/*  467 */     v[2] -= 2.0D * dot * n[2];
/*      */     
/*  469 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_COPY_2(double[] v)
/*      */   {
/*  476 */     double[] vtmp = new double[3];
/*  477 */     vtmp[0] = v[0];
/*  478 */     vtmp[1] = v[1];
/*  479 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_COPY(double[] v)
/*      */   {
/*  486 */     double[] vtmp = new double[3];
/*  487 */     vtmp[0] = v[0];
/*  488 */     vtmp[1] = v[1];
/*  489 */     vtmp[2] = v[2];
/*  490 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double VEC_LENGTH_2(double[] v)
/*      */   {
/*  497 */     double length = v[0] * v[0] + v[1] * v[1];
/*  498 */     return length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double VEC_LENGTH(double[] v)
/*      */   {
/*  505 */     double length = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
/*  506 */     return length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_SCALE(double scale, double[] v)
/*      */   {
/*  513 */     double[] vtmp = new double[3];
/*  514 */     vtmp[0] = (scale * v[0]);
/*  515 */     vtmp[1] = (scale * v[1]);
/*  516 */     vtmp[2] = (scale * v[2]);
/*  517 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_CROSS_PRODUCT(double[] v1, double[] v2)
/*      */   {
/*  524 */     double[] vtmp = new double[3];
/*  525 */     vtmp[0] = (v1[1] * v2[2] - v1[2] * v2[1]);
/*  526 */     vtmp[1] = (v1[2] * v2[0] - v1[0] * v2[2]);
/*  527 */     vtmp[2] = (v1[0] * v2[1] - v1[1] * v2[0]);
/*  528 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double VEC_DOT_PRODUCT(double[] v1, double[] v2)
/*      */   {
/*  535 */     double dot = 0.0D;
/*  536 */     if ((v1.length != 3) || (v2.length != 3)) {
/*  537 */       throw new GLEException("Length of v1 or v2 != 3. Invalid vectors!");
/*      */     }
/*      */     
/*  540 */     dot = v1[0] * v2[0] + v1[1] * v2[1] + v1[2] * v2[2];
/*  541 */     return dot;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_PERP(double[] v, double[] n)
/*      */   {
/*  548 */     double[] vtmp = new double[3];
/*  549 */     double dot = VEC_DOT_PRODUCT(v, n);
/*      */     
/*  551 */     if ((v.length != 3) || (n.length != 3)) {
/*  552 */       throw new GLEException("Length of v or n !=3. Invalid vectors!");
/*      */     }
/*      */     
/*  555 */     v[0] -= dot * n[0];
/*  556 */     v[1] -= dot * n[1];
/*  557 */     v[2] -= dot * n[2];
/*      */     
/*  559 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_DIFF(double[] v2, double[] v1)
/*      */   {
/*  567 */     double[] vtmp = new double[3];
/*      */     
/*  569 */     if ((v1.length != 3) || (v2.length != 3)) {
/*  570 */       throw new GLEException("Length of v1 or v2 != 3. Invalid vectors!");
/*      */     }
/*      */     
/*  573 */     v2[0] -= v1[0];
/*  574 */     v2[1] -= v1[1];
/*  575 */     v2[2] -= v1[2];
/*      */     
/*  577 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[] VEC_SUM(double[] v1, double[] v2)
/*      */   {
/*  584 */     double[] vtmp = new double[3];
/*      */     
/*  586 */     if ((v1.length != 3) || (v2.length != 3)) {
/*  587 */       throw new GLEException("Length of v1 or v2 != 3. Invalid vectors!");
/*      */     }
/*      */     
/*  590 */     v2[0] += v1[0];
/*  591 */     v2[1] += v1[1];
/*  592 */     v2[2] += v1[2];
/*      */     
/*  594 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[][] IDENTIFY_MATRIX_3X3()
/*      */   {
/*  601 */     double[][] m = new double[3][3];
/*      */     
/*  603 */     m[0][0] = 1.0D;
/*  604 */     m[0][1] = 0.0D;
/*  605 */     m[0][2] = 0.0D;
/*      */     
/*  607 */     m[1][0] = 0.0D;
/*  608 */     m[1][1] = 1.0D;
/*  609 */     m[1][2] = 0.0D;
/*      */     
/*  611 */     m[2][0] = 0.0D;
/*  612 */     m[2][1] = 0.0D;
/*  613 */     m[2][2] = 1.0D;
/*      */     
/*  615 */     return m;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[][] IDENTIFY_MATRIX_4X4()
/*      */   {
/*  622 */     double[][] m = new double[4][4];
/*      */     
/*  624 */     m[0][0] = 1.0D;
/*  625 */     m[0][1] = 0.0D;
/*  626 */     m[0][2] = 0.0D;
/*  627 */     m[0][3] = 0.0D;
/*      */     
/*  629 */     m[1][0] = 0.0D;
/*  630 */     m[1][1] = 1.0D;
/*  631 */     m[1][2] = 0.0D;
/*  632 */     m[1][3] = 0.0D;
/*      */     
/*  634 */     m[2][0] = 0.0D;
/*  635 */     m[2][1] = 0.0D;
/*  636 */     m[2][2] = 1.0D;
/*  637 */     m[2][3] = 0.0D;
/*      */     
/*  639 */     m[3][0] = 0.0D;
/*  640 */     m[3][1] = 0.0D;
/*  641 */     m[3][2] = 0.0D;
/*  642 */     m[3][3] = 1.0D;
/*      */     
/*  644 */     return m;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[][] COPY_MATRIX_2X2(double[][] a)
/*      */   {
/*  651 */     double[][] b = new double[2][2];
/*      */     
/*  653 */     b[0][0] = a[0][0];
/*  654 */     b[0][1] = a[0][1];
/*      */     
/*  656 */     b[1][0] = a[1][0];
/*  657 */     b[1][1] = a[1][1];
/*      */     
/*  659 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[][] COPY_MATRIX_2X3(double[][] a)
/*      */   {
/*  666 */     double[][] b = new double[2][3];
/*      */     
/*  668 */     b[0][0] = a[0][0];
/*  669 */     b[0][1] = a[0][1];
/*  670 */     b[0][2] = a[0][2];
/*      */     
/*  672 */     b[1][0] = a[1][0];
/*  673 */     b[1][1] = a[1][1];
/*  674 */     b[1][2] = a[1][2];
/*      */     
/*  676 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double[][] COPY_MATRIX_4X4(double[][] a)
/*      */   {
/*  683 */     double[][] b = new double[4][4];
/*      */     
/*  685 */     b[0][0] = a[0][0];
/*  686 */     b[0][1] = a[0][1];
/*  687 */     b[0][2] = a[0][2];
/*  688 */     b[0][3] = a[0][3];
/*      */     
/*  690 */     b[1][0] = a[1][0];
/*  691 */     b[1][1] = a[1][1];
/*  692 */     b[1][2] = a[1][2];
/*  693 */     b[1][3] = a[1][3];
/*      */     
/*  695 */     b[2][0] = a[2][0];
/*  696 */     b[2][1] = a[2][1];
/*  697 */     b[2][2] = a[2][2];
/*  698 */     b[2][3] = a[2][3];
/*      */     
/*  700 */     b[3][0] = a[3][0];
/*  701 */     b[3][1] = a[3][1];
/*  702 */     b[3][2] = a[3][2];
/*  703 */     b[3][3] = a[3][3];
/*      */     
/*  705 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[][] MATRIX_PRODUCT_2X2(double[][] a, double[][] b)
/*      */   {
/*  714 */     double[][] c = new double[2][2];
/*      */     
/*  716 */     c[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]);
/*  717 */     c[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]);
/*      */     
/*  719 */     c[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]);
/*  720 */     c[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]);
/*      */     
/*  722 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[][] MATRIX_PRODUCT_4X4(double[][] a, double[][] b)
/*      */   {
/*  730 */     double[][] c = new double[4][4];
/*      */     
/*  732 */     c[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0] + a[0][2] * b[2][0] + a[0][3] * b[3][0]);
/*      */     
/*      */ 
/*  735 */     c[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1] + a[0][2] * b[2][1] + a[0][3] * b[3][1]);
/*      */     
/*      */ 
/*  738 */     c[0][2] = (a[0][0] * b[0][2] + a[0][1] * b[1][2] + a[0][2] * b[2][2] + a[0][3] * b[3][2]);
/*      */     
/*      */ 
/*  741 */     c[0][3] = (a[0][0] * b[0][3] + a[0][1] * b[1][3] + a[0][2] * b[2][3] + a[0][3] * b[3][3]);
/*      */     
/*      */ 
/*      */ 
/*  745 */     c[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0] + a[1][2] * b[2][0] + a[1][3] * b[3][0]);
/*      */     
/*      */ 
/*  748 */     c[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1] + a[1][2] * b[2][1] + a[1][3] * b[3][1]);
/*      */     
/*      */ 
/*  751 */     c[1][2] = (a[1][0] * b[0][2] + a[1][1] * b[1][2] + a[1][2] * b[2][2] + a[1][3] * b[3][2]);
/*      */     
/*      */ 
/*  754 */     c[1][3] = (a[1][0] * b[0][3] + a[1][1] * b[1][3] + a[1][2] * b[2][3] + a[1][3] * b[3][3]);
/*      */     
/*      */ 
/*      */ 
/*  758 */     c[2][0] = (a[2][0] * b[0][0] + a[2][1] * b[1][0] + a[2][2] * b[2][0] + a[2][3] * b[3][0]);
/*      */     
/*      */ 
/*  761 */     c[2][1] = (a[2][0] * b[0][1] + a[2][1] * b[1][1] + a[2][2] * b[2][1] + a[2][3] * b[3][1]);
/*      */     
/*      */ 
/*  764 */     c[2][2] = (a[2][0] * b[0][2] + a[2][1] * b[1][2] + a[2][2] * b[2][2] + a[2][3] * b[3][2]);
/*      */     
/*      */ 
/*  767 */     c[2][3] = (a[2][0] * b[0][3] + a[2][1] * b[1][3] + a[2][2] * b[2][3] + a[2][3] * b[3][3]);
/*      */     
/*      */ 
/*      */ 
/*  771 */     c[3][0] = (a[3][0] * b[0][0] + a[3][1] * b[1][0] + a[3][2] * b[2][0] + a[3][3] * b[3][0]);
/*      */     
/*      */ 
/*  774 */     c[3][1] = (a[3][0] * b[0][1] + a[3][1] * b[1][1] + a[3][2] * b[2][1] + a[3][3] * b[3][1]);
/*      */     
/*      */ 
/*  777 */     c[3][2] = (a[3][0] * b[0][2] + a[3][1] * b[1][2] + a[3][2] * b[2][2] + a[3][3] * b[3][2]);
/*      */     
/*      */ 
/*  780 */     c[3][3] = (a[3][0] * b[0][3] + a[3][1] * b[1][3] + a[3][2] * b[2][3] + a[3][3] * b[3][3]);
/*      */     
/*      */ 
/*      */ 
/*  784 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[] MAT_DOT_VEC_2X3(double[][] m, double[] v)
/*      */   {
/*  793 */     double[] vtmp = new double[3];
/*  794 */     vtmp[0] = (m[0][0] * v[0] + m[0][1] * v[1] + m[0][2]);
/*      */     
/*  796 */     vtmp[1] = (m[1][0] * v[0] + m[1][1] * v[1] + m[1][2]);
/*      */     
/*  798 */     vtmp[2] = 0.0D;
/*  799 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[] MAT_DOT_VEC_3X3(double[][] m, double[] v)
/*      */   {
/*  807 */     double[] vtmp = new double[3];
/*      */     
/*  809 */     vtmp[0] = (m[0][0] * v[0] + m[0][1] * v[1] + m[0][2] * v[2]);
/*  810 */     vtmp[1] = (m[1][0] * v[0] + m[1][1] * v[1] + m[1][2] * v[2]);
/*  811 */     vtmp[2] = (m[2][0] * v[0] + m[2][1] * v[1] + m[2][2] * v[2]);
/*      */     
/*  813 */     return vtmp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[] NORM_XFORM_2X2(double[][] m, double[] v)
/*      */   {
/*  825 */     double len = 0.0D;
/*  826 */     double[] p = new double[3];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  831 */     if ((m[0][1] != 0.0D) || (m[1][0] != 0.0D) || (m[0][0] != m[1][1]))
/*      */     {
/*  833 */       p[0] = (m[1][1] * v[0] - m[1][0] * v[1]);
/*  834 */       p[1] = (-m[0][1] * v[0] + m[0][0] * v[1]);
/*      */       
/*  836 */       len = p[0] * p[0] + p[1] * p[1];
/*  837 */       len = 1.0D / Math.sqrt(len);
/*  838 */       p[0] *= len;
/*  839 */       p[1] *= len;
/*      */     } else {
/*  841 */       p = VEC_COPY_2(v);
/*      */     }
/*      */     
/*  844 */     return p;
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
/*      */   public static final double[][] uview_direction_d(double[] v21, double[] up)
/*      */   {
/*  865 */     double[][] amat = (double[][])null;
/*  866 */     double[][] bmat = (double[][])null;
/*  867 */     double[][] cmat = (double[][])null;
/*  868 */     double[] v_hat_21 = new double[3];
/*  869 */     double[] v_xy = new double[3];
/*      */     
/*      */ 
/*      */ 
/*  873 */     double[] up_proj = new double[3];
/*  874 */     double[] tmp = new double[3];
/*  875 */     double[][] m = (double[][])null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  880 */     v_hat_21 = VEC_COPY(v21);
/*  881 */     double len = VEC_LENGTH(v_hat_21);
/*  882 */     if (len != 0.0D) {
/*  883 */       len = 1.0D / len;
/*  884 */       v_hat_21 = VEC_SCALE(len, v_hat_21);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  889 */       double sine = Math.sqrt(1.0D - v_hat_21[2] * v_hat_21[2]);
/*  890 */       amat = ROTY_CS(-v_hat_21[2], -sine);
/*      */     } else {
/*  892 */       amat = IDENTIFY_MATRIX_4X4();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  900 */     v_xy[0] = v21[0];
/*  901 */     v_xy[1] = v21[1];
/*  902 */     v_xy[2] = 0.0D;
/*  903 */     len = VEC_LENGTH(v_xy);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  909 */     if (len != 0.0D)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  914 */       len = 1.0D / len;
/*  915 */       v_xy = VEC_SCALE(len, v_xy);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  920 */       bmat = ROTZ_CS(v_xy[0], v_xy[1]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  925 */       cmat = MATRIX_PRODUCT_4X4(amat, bmat);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  930 */       cmat = COPY_MATRIX_4X4(amat);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  938 */     up_proj = VEC_PERP(up, v_hat_21);
/*      */     
/*  940 */     len = VEC_LENGTH(up_proj);
/*  941 */     if (len != 0.0D)
/*      */     {
/*      */ 
/*      */ 
/*  945 */       len = 1.0D / len;
/*  946 */       up_proj = VEC_SCALE(len, up_proj);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  952 */       tmp[0] = cmat[1][0];
/*  953 */       tmp[1] = cmat[1][1];
/*  954 */       tmp[2] = cmat[1][2];
/*  955 */       double cosine = VEC_DOT_PRODUCT(tmp, up_proj);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  960 */       tmp[0] = cmat[0][0];
/*  961 */       tmp[1] = cmat[0][1];
/*  962 */       tmp[2] = cmat[0][2];
/*  963 */       double sine = VEC_DOT_PRODUCT(tmp, up_proj);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  968 */       amat = ROTZ_CS(cosine, -sine);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  973 */       m = MATRIX_PRODUCT_4X4(amat, cmat);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  979 */       m = COPY_MATRIX_4X4(cmat);
/*      */     }
/*  981 */     return m;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double[][] uviewpoint_d(double[] v1, double[] v2, double[] up)
/*      */   {
/*  991 */     double[] v_hat_21 = null;
/*  992 */     double[][] trans_mat = (double[][])null;
/*  993 */     double[][] rot_mat = (double[][])null;
/*  994 */     double[][] m = (double[][])null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  999 */     v_hat_21 = VEC_DIFF(v2, v1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1010 */     rot_mat = uview_direction_d(v_hat_21, up);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1015 */     trans_mat = IDENTIFY_MATRIX_4X4();
/* 1016 */     trans_mat[3][0] = v1[0];
/* 1017 */     trans_mat[3][1] = v1[1];
/* 1018 */     trans_mat[3][2] = v1[2];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1023 */     m = MATRIX_PRODUCT_4X4(rot_mat, trans_mat);
/*      */     
/* 1025 */     return m;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/com/sasmaster/glelwjgl/java/matrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */