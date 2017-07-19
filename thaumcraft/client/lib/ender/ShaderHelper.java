/*     */ package thaumcraft.client.lib.ender;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.ARBShaderObjects;
/*     */ import thaumcraft.common.config.Config;
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
/*     */ public final class ShaderHelper
/*     */ {
/*     */   private static final int VERT = 35633;
/*     */   private static final int FRAG = 35632;
/*     */   private static final String PREFIX = "/assets/thaumcraft/shader/";
/*  49 */   public static int endShader = 0;
/*  50 */   public static int bloomShader = 0;
/*     */   
/*     */   public static void initShaders() {
/*  53 */     if (!useShaders()) return;
/*  54 */     endShader = createProgram("ender.vert", "ender.frag");
/*     */   }
/*     */   
/*     */   public static void useShader(int shader, ShaderCallback callback) {
/*  58 */     if (!useShaders()) {
/*  59 */       return;
/*     */     }
/*  61 */     ARBShaderObjects.glUseProgramObjectARB(shader);
/*     */     
/*  63 */     if (shader != 0) {
/*  64 */       int time = ARBShaderObjects.glGetUniformLocationARB(shader, "time");
/*  65 */       ARBShaderObjects.glUniform1iARB(time, Minecraft.func_71410_x().func_175606_aa().field_70173_aa);
/*     */       
/*  67 */       if (callback != null)
/*  68 */         callback.call(shader);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void useShader(int shader) {
/*  73 */     useShader(shader, null);
/*     */   }
/*     */   
/*     */   public static void releaseShader() {
/*  77 */     useShader(0);
/*     */   }
/*     */   
/*     */   public static boolean useShaders() {
/*  81 */     return (Config.shaders) && (OpenGlHelper.field_148824_g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static int createProgram(String vert, String frag)
/*     */   {
/*  88 */     int vertId = 0;int fragId = 0;int program = 0;
/*  89 */     if (vert != null)
/*  90 */       vertId = createShader("/assets/thaumcraft/shader/" + vert, 35633);
/*  91 */     if (frag != null) {
/*  92 */       fragId = createShader("/assets/thaumcraft/shader/" + frag, 35632);
/*     */     }
/*  94 */     program = ARBShaderObjects.glCreateProgramObjectARB();
/*  95 */     if (program == 0) {
/*  96 */       return 0;
/*     */     }
/*  98 */     if (vert != null)
/*  99 */       ARBShaderObjects.glAttachObjectARB(program, vertId);
/* 100 */     if (frag != null) {
/* 101 */       ARBShaderObjects.glAttachObjectARB(program, fragId);
/*     */     }
/* 103 */     ARBShaderObjects.glLinkProgramARB(program);
/* 104 */     if (ARBShaderObjects.glGetObjectParameteriARB(program, 35714) == 0) {
/* 105 */       return 0;
/*     */     }
/*     */     
/* 108 */     ARBShaderObjects.glValidateProgramARB(program);
/* 109 */     if (ARBShaderObjects.glGetObjectParameteriARB(program, 35715) == 0) {
/* 110 */       return 0;
/*     */     }
/*     */     
/* 113 */     return program;
/*     */   }
/*     */   
/*     */   private static int createShader(String filename, int shaderType) {
/* 117 */     int shader = 0;
/*     */     try {
/* 119 */       shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
/*     */       
/* 121 */       if (shader == 0) {
/* 122 */         return 0;
/*     */       }
/* 124 */       ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(filename));
/* 125 */       ARBShaderObjects.glCompileShaderARB(shader);
/*     */       
/* 127 */       if (ARBShaderObjects.glGetObjectParameteriARB(shader, 35713) == 0) {
/* 128 */         throw new RuntimeException("Error creating shader: " + getLogInfo(shader));
/*     */       }
/* 130 */       return shader;
/*     */     }
/*     */     catch (Exception e) {
/* 133 */       ARBShaderObjects.glDeleteObjectARB(shader);
/* 134 */       e.printStackTrace(); }
/* 135 */     return -1;
/*     */   }
/*     */   
/*     */   private static String getLogInfo(int obj)
/*     */   {
/* 140 */     return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, 35716));
/*     */   }
/*     */   
/*     */   private static String readFileAsString(String filename) throws Exception {
/* 144 */     StringBuilder source = new StringBuilder();
/* 145 */     InputStream in = ShaderHelper.class.getResourceAsStream(filename);
/* 146 */     Exception exception = null;
/*     */     
/*     */ 
/* 149 */     if (in == null)
/* 150 */       return "";
/*     */     for (;;) {
/*     */       try {
/* 153 */         BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
/*     */         
/* 155 */         Exception innerExc = null;
/*     */         try {
/*     */           String line;
/* 158 */           if ((line = reader.readLine()) != null) {
/* 159 */             source.append(line).append('\n'); continue;
/*     */           }
/*     */           
/*     */           try
/*     */           {
/* 164 */             reader.close();
/*     */           } catch (Exception exc) {
/* 166 */             if (innerExc == null)
/* 167 */               innerExc = exc; else {
/* 168 */               exc.printStackTrace();
/*     */             }
/*     */           }
/*     */           
/* 172 */           if (innerExc == null) {
/*     */             continue;
/*     */           }
/*     */         }
/*     */         catch (Exception exc)
/*     */         {
/* 161 */           exception = exc;
/*     */         } finally {
/*     */           try {
/* 164 */             reader.close();
/*     */           } catch (Exception exc) {
/* 166 */             if (innerExc == null)
/* 167 */               innerExc = exc; else {
/* 168 */               exc.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 173 */         throw innerExc;
/*     */       } catch (Exception exc) {
/* 175 */         exception = exc;
/*     */       } finally {
/*     */         try {
/* 178 */           in.close();
/*     */         } catch (Exception exc) {
/* 180 */           if (exception == null)
/* 181 */             exception = exc; else {
/* 182 */             exc.printStackTrace();
/*     */           }
/*     */         }
/* 185 */         if (exception == null) continue;
/* 186 */         throw exception;
/*     */       }
/*     */       try
/*     */       {
/* 178 */         in.close();
/*     */       } catch (Exception exc) {
/* 180 */         if (exception == null)
/* 181 */           exception = exc; else
/* 182 */           exc.printStackTrace();
/*     */       }
/*     */     }
/* 185 */     if (exception != null) {
/* 186 */       throw exception;
/*     */     }
/*     */     
/* 189 */     return source.toString();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/ender/ShaderHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */