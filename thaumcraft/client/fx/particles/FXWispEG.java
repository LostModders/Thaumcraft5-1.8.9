/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXWispEG extends EntityFX
/*     */ {
/*  14 */   Entity target = null;
/*  15 */   double rx = 0.0D;
/*  16 */   double ry = 0.0D;
/*  17 */   double rz = 0.0D;
/*     */   
/*     */   public FXWispEG(World worldObj, double posX, double posY, double posZ, Entity target2)
/*     */   {
/*  21 */     super(worldObj, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*     */     
/*  23 */     this.target = target2;
/*     */     
/*  25 */     this.field_70159_w = (this.field_70146_Z.nextGaussian() * 0.03D);
/*  26 */     this.field_70181_x = -0.05D;
/*  27 */     this.field_70179_y = (this.field_70146_Z.nextGaussian() * 0.03D);
/*     */     
/*  29 */     this.field_70544_f *= 0.4F;
/*  30 */     this.field_70547_e = ((int)(40.0D / (Math.random() * 0.3D + 0.7D)));
/*     */     
/*  32 */     this.field_70145_X = false;
/*  33 */     func_70105_a(0.01F, 0.01F);
/*  34 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/*  35 */     int visibleDistance = 50;
/*  36 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  37 */       visibleDistance = 25;
/*  38 */     if (renderentity.func_70011_f(posX, posY, posZ) > visibleDistance)
/*  39 */       this.field_70547_e = 0;
/*  40 */     this.field_70169_q = posX;
/*  41 */     this.field_70167_r = posY;
/*  42 */     this.field_70166_s = posZ;
/*  43 */     this.blendmode = 771;
/*  44 */     this.field_70552_h = (this.field_70146_Z.nextFloat() * 0.05F);
/*  45 */     this.field_70553_i = (this.field_70146_Z.nextFloat() * 0.05F);
/*  46 */     this.field_70551_j = (this.field_70146_Z.nextFloat() * 0.05F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  52 */     Entity e = Minecraft.func_71410_x().func_175606_aa();
/*  53 */     float agescale = 1.0F - this.field_70546_d / this.field_70547_e;
/*  54 */     float d6 = 1024.0F;
/*  55 */     float base = (float)(1.0D - Math.min(d6, func_70092_e(e.field_70165_t, e.field_70163_u, e.field_70161_v)) / d6);
/*     */     
/*  57 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F * base);
/*     */     
/*  59 */     float f10 = 0.5F * this.field_70544_f;
/*  60 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  61 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  62 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  64 */     float var8 = this.field_70546_d % 13 / 16.0F;
/*  65 */     float var9 = var8 + 0.0624375F;
/*  66 */     float var10 = 0.1875F;
/*  67 */     float var11 = var10 + 0.0624375F;
/*     */     
/*  69 */     int i = 240;
/*  70 */     int j = i >> 16 & 0xFFFF;
/*  71 */     int k = i & 0xFFFF;
/*     */     
/*  73 */     wr.func_181662_b(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10).func_181673_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base).func_181671_a(j, k).func_181675_d();
/*  74 */     wr.func_181662_b(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10).func_181673_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base).func_181671_a(j, k).func_181675_d();
/*  75 */     wr.func_181662_b(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10).func_181673_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base).func_181671_a(j, k).func_181675_d();
/*  76 */     wr.func_181662_b(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10).func_181673_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  82 */     return this.blendmode == 1 ? 0 : 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  87 */     this.field_70169_q = this.field_70165_t;
/*  88 */     this.field_70167_r = this.field_70163_u;
/*  89 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  91 */     if ((this.target != null) && (!this.field_70122_E)) {
/*  92 */       this.field_70165_t += this.target.field_70159_w;
/*  93 */       this.field_70161_v += this.target.field_70179_y;
/*     */     }
/*     */     
/*     */ 
/*  97 */     func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  98 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 100 */     this.field_70159_w *= 0.9800000190734863D;
/* 101 */     this.field_70181_x *= 0.9800000190734863D;
/* 102 */     this.field_70179_y *= 0.9800000190734863D;
/* 103 */     if (this.field_70122_E) {
/* 104 */       this.field_70159_w *= 0.8500000190734863D;
/* 105 */       this.field_70179_y *= 0.8500000190734863D;
/*     */     }
/*     */     
/*     */ 
/* 109 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 111 */       func_70106_y();
/*     */     }
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
/*     */   public void setGravity(float value)
/*     */   {
/* 211 */     this.field_70545_g = value;
/*     */   }
/*     */   
/* 214 */   public int blendmode = 1;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXWispEG.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */