/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class FXSmokeSpiral extends EntityFX
/*    */ {
/* 13 */   private float radius = 1.0F;
/* 14 */   private int start = 0;
/* 15 */   private int miny = 0;
/*    */   
/*    */   public FXSmokeSpiral(World world, double d, double d1, double d2, float radius, int start, int miny)
/*    */   {
/* 19 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*    */     
/* 21 */     this.field_70545_g = -0.01F;
/* 22 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/* 23 */     this.field_70544_f *= 1.0F;
/* 24 */     this.field_70547_e = (20 + world.field_73012_v.nextInt(10));
/* 25 */     this.field_70145_X = false;
/* 26 */     func_70105_a(0.01F, 0.01F);
/* 27 */     this.field_70169_q = this.field_70165_t;
/* 28 */     this.field_70167_r = this.field_70163_u;
/* 29 */     this.field_70166_s = this.field_70161_v;
/* 30 */     this.radius = radius;
/* 31 */     this.start = start;
/* 32 */     this.miny = miny;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 38 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F * this.field_82339_as);
/*    */     
/* 40 */     int particle = (int)(1.0F + this.field_70546_d / this.field_70547_e * 4.0F);
/*    */     
/* 42 */     float r1 = this.start + 720.0F * ((this.field_70546_d + f) / this.field_70547_e);
/* 43 */     float r2 = 90.0F - 180.0F * ((this.field_70546_d + f) / this.field_70547_e);
/*    */     
/* 45 */     float mX = -MathHelper.func_76126_a(r1 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(r2 / 180.0F * 3.1415927F);
/*    */     
/* 47 */     float mZ = MathHelper.func_76134_b(r1 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(r2 / 180.0F * 3.1415927F);
/*    */     
/* 49 */     float mY = -MathHelper.func_76126_a(r2 / 180.0F * 3.1415927F);
/* 50 */     mX *= this.radius;
/* 51 */     mY *= this.radius;
/* 52 */     mZ *= this.radius;
/*    */     
/* 54 */     float var8 = particle % 16 / 16.0F;
/* 55 */     float var9 = var8 + 0.0624375F;
/* 56 */     float var10 = particle / 16 / 16.0F;
/* 57 */     float var11 = var10 + 0.0624375F;
/* 58 */     float var12 = 0.15F * this.field_70544_f;
/* 59 */     float var13 = (float)(this.field_70165_t + mX - field_70556_an);
/* 60 */     float var14 = (float)(Math.max(this.field_70163_u + mY, this.miny + 0.1F) - field_70554_ao);
/* 61 */     float var15 = (float)(this.field_70161_v + mZ - field_70555_ap);
/* 62 */     float var16 = 1.0F;
/* 63 */     int i = func_70070_b(f);
/* 64 */     int j = i >> 16 & 0xFFFF;
/* 65 */     int k = i & 0xFFFF;
/* 66 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 68 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 70 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 72 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int func_70537_b()
/*    */   {
/* 79 */     return 1;
/*    */   }
/*    */   
/*    */   public void func_70071_h_()
/*    */   {
/* 84 */     func_82338_g((this.field_70547_e - this.field_70546_d) / this.field_70547_e);
/* 85 */     if (this.field_70546_d++ >= this.field_70547_e)
/*    */     {
/* 87 */       func_70106_y();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXSmokeSpiral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */