/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class FXSpark extends EntityFX
/*    */ {
/*    */   public FXSpark(World world, double d, double d1, double d2, float f)
/*    */   {
/* 13 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*    */     
/* 15 */     this.field_70552_h = 1.0F;
/* 16 */     this.field_70553_i = 1.0F;
/* 17 */     this.field_70551_j = 1.0F;
/* 18 */     this.field_70545_g = 0.0F;
/* 19 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/* 20 */     this.field_70544_f = f;
/* 21 */     this.field_70547_e = (5 + world.field_73012_v.nextInt(5));
/* 22 */     this.field_70145_X = false;
/* 23 */     func_70105_a(0.01F, 0.01F);
/* 24 */     this.particle = (world.field_73012_v.nextInt(3) * 8);
/* 25 */     this.flip = world.field_73012_v.nextBoolean();
/*    */   }
/*    */   
/* 28 */   int particle = 0;
/* 29 */   boolean flip = false;
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 35 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as);
/* 36 */     int part = this.particle + (int)(this.field_70546_d / this.field_70547_e * 7.0F);
/*    */     
/* 38 */     float var8 = part % 8 / 8.0F;
/* 39 */     float var9 = var8 + 0.125F;
/* 40 */     float var10 = part / 8 / 8.0F;
/* 41 */     float var11 = var10 + 0.125F;
/* 42 */     float var12 = this.field_70544_f;
/* 43 */     if (this.flip) {
/* 44 */       float t = var8;
/* 45 */       var8 = var9;
/* 46 */       var9 = t;
/*    */     }
/* 48 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 49 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 50 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*    */     
/* 52 */     int i = func_70070_b(f);
/* 53 */     int j = i >> 16 & 0xFFFF;
/* 54 */     int k = i & 0xFFFF;
/*    */     
/* 56 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 58 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 60 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 62 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 67 */   public int layer = 2;
/*    */   
/*    */   public int func_70537_b() {
/* 70 */     return this.layer;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 76 */     this.field_70169_q = this.field_70165_t;
/* 77 */     this.field_70167_r = this.field_70163_u;
/* 78 */     this.field_70166_s = this.field_70161_v;
/*    */     
/* 80 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 81 */       func_70106_y();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXSpark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */