/*     */ package com.sasmaster.glelwjgl.java;
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
/*     */ public class intersect
/*     */ {
/*  36 */   public static final String VERSION = new String("$Id: intersect.java,v 1.1 1998/05/05 23:31:21 descarte Exp descarte $");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double DEGENERATE_TOLERANCE = 2.0E-6D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final int FIND_NON_DEGENERATE_POINT(int index, int npoints, double len, double[] diff, double[][] point_array)
/*     */   {
/*  53 */     double[] summa = null;
/*  54 */     int i = index;
/*  55 */     double tlen = len;
/*  56 */     double[] tdiff = null;
/*     */     
/*     */ 
/*     */ 
/*     */     double slen;
/*     */     
/*     */ 
/*     */     do
/*     */     {
/*  65 */       tdiff = matrix.VEC_DIFF(point_array[(i + 1)], point_array[i]);
/*     */       
/*  67 */       diff[0] = tdiff[0];diff[1] = tdiff[1];diff[2] = tdiff[2];
/*  68 */       tlen = matrix.VEC_LENGTH(diff);
/*  69 */       len = tlen;
/*  70 */       summa = matrix.VEC_SUM(point_array[(i + 1)], point_array[i]);
/*  71 */       slen = matrix.VEC_LENGTH(summa);
/*  72 */       slen *= 2.0E-6D;
/*  73 */       i++;
/*  74 */     } while ((tlen <= slen) && (i < npoints - 1));
/*  75 */     return i;
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
/*     */   public static final double[] INNERSECT(double[] p, double[] n, double[] v1, double[] v2)
/*     */   {
/* 113 */     double deno = 0.0D;
/* 114 */     double numer = 0.0D;
/* 115 */     double t = 0.0D;
/* 116 */     double omt = 0.0D;
/* 117 */     boolean valid = false;
/* 118 */     double[] sect = new double[3];
/*     */     
/* 120 */     deno = (v1[0] - v2[0]) * n[0];
/* 121 */     deno += (v1[1] - v2[1]) * n[1];
/* 122 */     deno += (v1[2] - v2[2]) * n[2];
/*     */     
/* 124 */     if (deno == 0.0D) {
/* 125 */       valid = false;
/* 126 */       n = matrix.VEC_COPY(v1);
/*     */     } else {
/* 128 */       valid = true;
/* 129 */       numer = (p[0] - v2[0]) * n[0];
/* 130 */       numer += (p[1] - v2[1]) * n[1];
/* 131 */       numer += (p[2] - v2[2]) * n[2];
/*     */       
/* 133 */       t = numer / deno;
/* 134 */       omt = 1.0D - t;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 139 */       if ((1.0D < t * 2.0E-6D) || (-1.0D > t * 2.0E-6D))
/*     */       {
/* 141 */         valid = false;
/*     */       }
/*     */       
/* 144 */       sect[0] = (t * v1[0] + omt * v2[0]);
/* 145 */       sect[1] = (t * v1[1] + omt * v2[1]);
/* 146 */       sect[2] = (t * v1[2] + omt * v2[2]);
/*     */     }
/* 148 */     return sect;
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
/*     */   public static final double[] bisecting_plane(double[] v1, double[] v2, double[] v3)
/*     */   {
/* 170 */     boolean valid = false;
/* 171 */     double[] v21 = null;
/* 172 */     double[] v32 = null;
/*     */     
/*     */ 
/*     */ 
/* 176 */     double[] n = new double[3];
/*     */     
/* 178 */     v21 = matrix.VEC_DIFF(v2, v1);
/* 179 */     v32 = matrix.VEC_DIFF(v3, v2);
/*     */     
/* 181 */     double len21 = matrix.VEC_LENGTH(v21);
/* 182 */     double len32 = matrix.VEC_LENGTH(v32);
/*     */     
/* 184 */     if (len21 <= 2.0E-6D * len32) {
/* 185 */       if (len32 == 0.0D)
/*     */       {
/* 187 */         n = matrix.VEC_ZERO();
/* 188 */         valid = false;
/*     */       }
/*     */       else {
/* 191 */         len32 = 1.0D / len32;
/* 192 */         n = matrix.VEC_SCALE(len32, v32);
/* 193 */         valid = true;
/*     */       }
/*     */     } else {
/* 196 */       valid = true;
/* 197 */       if (len32 <= 2.0E-6D * len21)
/*     */       {
/* 199 */         len21 = 1.0D / len21;
/* 200 */         n = matrix.VEC_SCALE(len21, v21);
/*     */       }
/*     */       else {
/* 203 */         len21 = 1.0D / len21;
/* 204 */         v21 = matrix.VEC_SCALE(len21, v21);
/*     */         
/*     */ 
/* 207 */         len32 = 1.0D / len32;
/* 208 */         v32 = matrix.VEC_SCALE(len32, v32);
/*     */         
/* 210 */         double dot = matrix.VEC_DOT_PRODUCT(v32, v21);
/*     */         
/*     */ 
/* 213 */         if ((dot >= 0.999998D) || (dot <= -0.999998D))
/*     */         {
/* 215 */           n = matrix.VEC_COPY(v21);
/*     */         }
/*     */         else {
/* 218 */           n[0] = (dot * (v32[0] + v21[0]) - v32[0] - v21[0]);
/* 219 */           n[1] = (dot * (v32[1] + v21[1]) - v32[1] - v21[1]);
/* 220 */           n[2] = (dot * (v32[2] + v21[2]) - v32[2] - v21[2]);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 226 */           n = matrix.VEC_NORMALIZE(n);
/*     */         }
/*     */       }
/*     */     }
/* 230 */     return n;
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
/*     */   public static final boolean CUTTING_PLANE(double[] n, double[] v1, double[] v2, double[] v3)
/*     */   {
/* 246 */     double[] v21 = new double[3];
/* 247 */     double[] v32 = new double[3];
/* 248 */     double len21 = 0.0D;
/* 249 */     double len32 = 0.0D;
/* 250 */     double lendiff = 0.0D;
/* 251 */     boolean valid = false;
/* 252 */     double[] vtmp = matrix.VEC_COPY(n);
/*     */     
/* 254 */     v21 = matrix.VEC_DIFF(v2, v1);
/* 255 */     v32 = matrix.VEC_DIFF(v3, v2);
/*     */     
/* 257 */     len21 = matrix.VEC_LENGTH(v21);
/* 258 */     len32 = matrix.VEC_LENGTH(v32);
/*     */     
/* 260 */     if (len21 <= 2.0E-6D * len32) {
/* 261 */       if (len32 == 0.0D) {
/* 262 */         vtmp = matrix.VEC_ZERO();
/* 263 */         valid = false;
/*     */       } else {
/* 265 */         len32 = 1.0D / len32;
/* 266 */         vtmp = matrix.VEC_SCALE(len32, v32);
/* 267 */         valid = true;
/*     */       }
/*     */     } else {
/* 270 */       valid = true;
/*     */       
/* 272 */       if (len32 <= 2.0E-6D * len21) {
/* 273 */         len21 = 1.0D / len21;
/* 274 */         vtmp = matrix.VEC_SCALE(len21, v21);
/*     */       } else {
/* 276 */         len21 = 1.0D / len21;
/* 277 */         v21 = matrix.VEC_SCALE(len21, v21);
/*     */         
/* 279 */         len32 = 1.0D / len32;
/* 280 */         v32 = matrix.VEC_SCALE(len32, v32);
/*     */         
/* 282 */         vtmp = matrix.VEC_DIFF(v21, v32);
/* 283 */         lendiff = matrix.VEC_LENGTH(vtmp);
/*     */         
/* 285 */         if (lendiff < 2.0E-6D) {
/* 286 */           vtmp = matrix.VEC_ZERO();
/* 287 */           valid = false;
/*     */         } else {
/* 289 */           lendiff = 1.0D / lendiff;
/* 290 */           vtmp = matrix.VEC_SCALE(lendiff, vtmp);
/*     */         }
/*     */       }
/*     */     }
/* 294 */     n[0] = vtmp[0];
/* 295 */     n[1] = vtmp[1];
/* 296 */     n[2] = vtmp[2];
/* 297 */     return valid;
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
/*     */   public static boolean COLINEAR(double[] v1, double[] v2, double[] v3)
/*     */   {
/* 310 */     double[] v21 = new double[3];
/* 311 */     double[] v32 = new double[3];
/* 312 */     boolean rv = false;
/* 313 */     double len21 = 0.0D;
/* 314 */     double len32 = 0.0D;
/* 315 */     double dot = 0.0D;
/*     */     
/* 317 */     v21 = matrix.VEC_DIFF(v2, v1);
/* 318 */     v32 = matrix.VEC_DIFF(v3, v2);
/*     */     
/* 320 */     len21 = matrix.VEC_DOT_PRODUCT(v21, v21);
/* 321 */     len32 = matrix.VEC_DOT_PRODUCT(v32, v32);
/* 322 */     rv = len32 <= 2.0E-6D * len21;
/* 323 */     rv = (rv) || (len21 <= 2.0E-6D * len32);
/* 324 */     dot = matrix.VEC_DOT_PRODUCT(v21, v32);
/* 325 */     rv = (rv) || (len21 * len32 - dot * dot <= len21 * len32 * 2.0E-6D * 2.0E-6D);
/*     */     
/*     */ 
/* 328 */     return rv;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/com/sasmaster/glelwjgl/java/intersect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */