/*     */ package thaumcraft.client.lib;
/*     */ 
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.shader.ShaderGroup;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
/*     */ import thaumcraft.common.lib.potions.PotionBlurredVision;
/*     */ import thaumcraft.common.lib.potions.PotionDeathGaze;
/*     */ import thaumcraft.common.lib.potions.PotionSunScorned;
/*     */ import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
/*     */ 
/*     */ public class ShaderHandler
/*     */ {
/*  19 */   public static int warpVignette = 0;
/*     */   
/*     */   public static final int SHADER_DESAT = 0;
/*     */   
/*     */   public static final int SHADER_BLUR = 1;
/*     */   public static final int SHADER_HUNGER = 2;
/*     */   public static final int SHADER_SUNSCORNED = 3;
/*  26 */   public static ResourceLocation[] shader_resources = { new ResourceLocation("shaders/post/desaturatetc.json"), new ResourceLocation("shaders/post/blurtc.json"), new ResourceLocation("shaders/post/hunger.json"), new ResourceLocation("shaders/post/sunscorned.json") };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkShaders(TickEvent.PlayerTickEvent event, Minecraft mc)
/*     */   {
/*  33 */     if (event.player.func_70644_a(PotionDeathGaze.instance)) {
/*  34 */       warpVignette = 10;
/*  35 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(0))) {
/*     */         try {
/*  37 */           setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shader_resources[0]), 0);
/*     */ 
/*     */         }
/*     */         catch (JsonSyntaxException e) {}catch (IOException e) {}
/*     */       }
/*     */       
/*     */     }
/*  44 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(0))) {
/*  45 */       deactivateShader(0);
/*     */     }
/*     */     
/*     */ 
/*  49 */     if (event.player.func_70644_a(PotionBlurredVision.instance)) {
/*  50 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(1))) {
/*     */         try {
/*  52 */           setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shader_resources[1]), 1);
/*     */ 
/*     */         }
/*     */         catch (JsonSyntaxException e) {}catch (IOException e) {}
/*     */       }
/*     */       
/*     */     }
/*  59 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(1))) {
/*  60 */       deactivateShader(1);
/*     */     }
/*     */     
/*     */ 
/*  64 */     if (event.player.func_70644_a(PotionUnnaturalHunger.instance)) {
/*  65 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(2))) {
/*     */         try {
/*  67 */           setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shader_resources[2]), 2);
/*     */ 
/*     */         }
/*     */         catch (JsonSyntaxException e) {}catch (IOException e) {}
/*     */       }
/*     */       
/*     */     }
/*  74 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(2))) {
/*  75 */       deactivateShader(2);
/*     */     }
/*     */     
/*     */ 
/*  79 */     if (event.player.func_70644_a(PotionSunScorned.instance)) {
/*  80 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(3))) {
/*     */         try {
/*  82 */           setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shader_resources[3]), 3);
/*     */ 
/*     */         }
/*     */         catch (JsonSyntaxException e) {}catch (IOException e) {}
/*     */       }
/*     */       
/*     */     }
/*  89 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(3))) {
/*  90 */       deactivateShader(3);
/*     */     }
/*     */   }
/*     */   
/*     */   void setShader(ShaderGroup target, int shaderId)
/*     */   {
/*  96 */     if (OpenGlHelper.field_148824_g)
/*     */     {
/*  98 */       Minecraft mc = Minecraft.func_71410_x();
/*  99 */       if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(shaderId)))
/*     */       {
/* 101 */         ((ShaderGroup)RenderEventHandler.shaderGroups.get(Integer.valueOf(shaderId))).func_148021_a();
/* 102 */         RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */       }
/*     */       
/*     */       try
/*     */       {
/* 107 */         if (target == null) {
/* 108 */           deactivateShader(shaderId);
/*     */         } else {
/* 110 */           RenderEventHandler.resetShaders = true;
/* 111 */           RenderEventHandler.shaderGroups.put(Integer.valueOf(shaderId), target);
/*     */         }
/*     */       }
/*     */       catch (Exception ioexception)
/*     */       {
/* 116 */         RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void deactivateShader(int shaderId)
/*     */   {
/* 123 */     if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(shaderId)))
/*     */     {
/* 125 */       ((ShaderGroup)RenderEventHandler.shaderGroups.get(Integer.valueOf(shaderId))).func_148021_a();
/*     */     }
/*     */     
/* 128 */     RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/ShaderHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */