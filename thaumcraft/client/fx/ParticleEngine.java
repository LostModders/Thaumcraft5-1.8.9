/*     */ package thaumcraft.client.fx;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ParticleEngine
/*     */ {
/*  34 */   public static ParticleEngine instance = new ParticleEngine();
/*     */   
/*  36 */   public static final ResourceLocation particleTexture = new ResourceLocation("thaumcraft", "textures/misc/particles.png");
/*     */   
/*  38 */   public static final ResourceLocation particleTexture2 = new ResourceLocation("thaumcraft", "textures/misc/particles2.png");
/*     */   
/*     */ 
/*     */   protected World worldObj;
/*     */   
/*  43 */   private HashMap<Integer, ArrayList<EntityFX>>[] particles = { new HashMap(), new HashMap(), new HashMap(), new HashMap() };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  49 */   private Random rand = new Random();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onRenderWorldLast(RenderWorldLastEvent event) {
/*  54 */     float frame = event.partialTicks;
/*  55 */     Entity entity = Minecraft.func_71410_x().field_71439_g;
/*  56 */     TextureManager renderer = Minecraft.func_71410_x().field_71446_o;
/*  57 */     int dim = Minecraft.func_71410_x().field_71441_e.field_73011_w.func_177502_q();
/*     */     
/*  59 */     renderer.func_110577_a(particleTexture2);
/*     */     
/*  61 */     GL11.glPushMatrix();
/*     */     
/*  63 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  64 */     GlStateManager.func_179147_l();
/*  65 */     GL11.glEnable(3042);
/*  66 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  67 */     GlStateManager.func_179132_a(false);
/*     */     
/*  69 */     boolean rebound = false;
/*  70 */     for (int layer = 3; layer >= 0; layer--)
/*  71 */       if (this.particles[layer].containsKey(Integer.valueOf(dim))) {
/*  72 */         ArrayList<EntityFX> parts = (ArrayList)this.particles[layer].get(Integer.valueOf(dim));
/*  73 */         if (parts.size() != 0)
/*     */         {
/*  75 */           if ((!rebound) && (layer < 2)) {
/*  76 */             renderer.func_110577_a(particleTexture);
/*  77 */             rebound = true;
/*     */           }
/*     */           
/*  80 */           GL11.glPushMatrix();
/*     */           
/*  82 */           switch (layer) {
/*     */           case 0: case 2: 
/*  84 */             GlStateManager.func_179112_b(770, 1);
/*  85 */             break;
/*     */           case 1: case 3: 
/*  87 */             GlStateManager.func_179112_b(770, 771);
/*     */           }
/*     */           
/*     */           
/*  91 */           float f1 = ActiveRenderInfo.func_178808_b();
/*  92 */           float f2 = ActiveRenderInfo.func_178803_d();
/*  93 */           float f3 = ActiveRenderInfo.func_178805_e();
/*  94 */           float f4 = ActiveRenderInfo.func_178807_f();
/*  95 */           float f5 = ActiveRenderInfo.func_178809_c();
/*  96 */           EntityFX.field_70556_an = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * frame;
/*  97 */           EntityFX.field_70554_ao = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * frame;
/*  98 */           EntityFX.field_70555_ap = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * frame;
/*     */           
/* 100 */           Tessellator tessellator = Tessellator.func_178181_a();
/* 101 */           WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 102 */           worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */           
/* 104 */           for (int j = 0; j < parts.size(); j++) {
/* 105 */             final EntityFX entityfx = (EntityFX)parts.get(j);
/* 106 */             if (entityfx != null) {
/*     */               try
/*     */               {
/* 109 */                 entityfx.func_180434_a(worldrenderer, entity, frame, f1, f5, f2, f3, f4);
/*     */               } catch (Throwable throwable) {
/* 111 */                 CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering Particle");
/* 112 */                 CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being rendered");
/* 113 */                 crashreportcategory.func_71500_a("Particle", new Callable()
/*     */                 {
/*     */                   public String call() {
/* 116 */                     return entityfx.toString();
/*     */                   }
/* 118 */                 });
/* 119 */                 crashreportcategory.func_71500_a("Particle Type", new Callable()
/*     */                 {
/*     */                   public String call() {
/* 122 */                     return "ENTITY_PARTICLE_TEXTURE";
/*     */                   }
/* 124 */                 });
/* 125 */                 throw new ReportedException(crashreport);
/*     */               }
/*     */             }
/*     */           }
/* 129 */           tessellator.func_78381_a();
/*     */           
/* 131 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/* 134 */     GlStateManager.func_179132_a(true);
/* 135 */     GlStateManager.func_179112_b(770, 771);
/* 136 */     GlStateManager.func_179084_k();
/* 137 */     GlStateManager.func_179092_a(516, 0.1F);
/* 138 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void addEffect(World world, EntityFX fx) {
/* 142 */     if (!this.particles[fx.func_70537_b()].containsKey(Integer.valueOf(world.field_73011_w.func_177502_q()))) {
/* 143 */       this.particles[fx.func_70537_b()].put(Integer.valueOf(world.field_73011_w.func_177502_q()), new ArrayList());
/*     */     }
/* 145 */     ArrayList<EntityFX> parts = (ArrayList)this.particles[fx.func_70537_b()].get(Integer.valueOf(world.field_73011_w.func_177502_q()));
/* 146 */     if (parts.size() >= 2000) {
/* 147 */       parts.remove(0);
/*     */     }
/* 149 */     parts.add(fx);
/* 150 */     this.particles[fx.func_70537_b()].put(Integer.valueOf(world.field_73011_w.func_177502_q()), parts);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void updateParticles(TickEvent.ClientTickEvent event) {
/* 156 */     if (event.side == Side.SERVER) return;
/* 157 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 158 */     World world = mc.field_71441_e;
/* 159 */     if (mc.field_71441_e == null) return;
/* 160 */     int dim = world.field_73011_w.func_177502_q();
/* 161 */     if (event.phase == TickEvent.Phase.START) {
/* 162 */       for (int layer = 0; layer < 4; layer++) {
/* 163 */         if (this.particles[layer].containsKey(Integer.valueOf(dim))) {
/* 164 */           ArrayList<EntityFX> parts = (ArrayList)this.particles[layer].get(Integer.valueOf(dim));
/*     */           
/* 166 */           for (int j = 0; j < parts.size(); j++) {
/* 167 */             final EntityFX entityfx = (EntityFX)parts.get(j);
/*     */             try
/*     */             {
/* 170 */               if (entityfx != null) {
/* 171 */                 entityfx.func_70071_h_();
/*     */               }
/*     */             } catch (Throwable throwable) {
/* 174 */               CrashReport crashreport = CrashReport.func_85055_a(throwable, "Ticking Particle");
/*     */               
/* 176 */               CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being ticked");
/*     */               
/* 178 */               crashreportcategory.func_71500_a("Particle", new Callable()
/*     */               {
/*     */                 public String call() {
/* 181 */                   return entityfx.toString();
/*     */                 }
/* 183 */               });
/* 184 */               crashreportcategory.func_71500_a("Particle Type", new Callable()
/*     */               {
/*     */                 public String call() {
/* 187 */                   return "ENTITY_PARTICLE_TEXTURE";
/*     */                 }
/* 189 */               });
/* 190 */               throw new ReportedException(crashreport);
/*     */             }
/*     */             
/* 193 */             if ((entityfx == null) || (entityfx.field_70128_L)) {
/* 194 */               parts.remove(j--);
/* 195 */               this.particles[layer].put(Integer.valueOf(dim), parts);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/ParticleEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */