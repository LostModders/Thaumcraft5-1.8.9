/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class FXSlimyBubble extends EntityFX
/*    */ {
/*    */   public FXSlimyBubble(World world, double d, double d1, double d2, float f)
/*    */   {
/* 13 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*    */     
/* 15 */     this.field_70552_h = 1.0F;
/* 16 */     this.field_70553_i = 1.0F;
/* 17 */     this.field_70551_j = 1.0F;
/* 18 */     this.field_70545_g = 0.0F;
/* 19 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/* 20 */     this.field_70544_f = f;
/* 21 */     this.field_70547_e = (15 + world.field_73012_v.nextInt(5));
/* 22 */     this.field_70145_X = false;
/* 23 */     func_70105_a(0.01F, 0.01F);
/*    */   }
/*    */   
/* 26 */   int particle = 144;
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 32 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as);
/*    */     
/* 34 */     float var8 = this.particle % 16 / 16.0F;
/* 35 */     float var9 = var8 + 0.0625F;
/* 36 */     float var10 = this.particle / 16 / 16.0F;
/* 37 */     float var11 = var10 + 0.0625F;
/* 38 */     float var12 = this.field_70544_f;
/* 39 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 40 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 41 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*    */     
/* 43 */     int i = func_70070_b(f);
/* 44 */     int j = i >> 16 & 0xFFFF;
/* 45 */     int k = i & 0xFFFF;
/*    */     
/* 47 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 49 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 51 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */     
/* 53 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int func_70537_b()
/*    */   {
/* 60 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 66 */     this.field_70169_q = this.field_70165_t;
/* 67 */     this.field_70167_r = this.field_70163_u;
/* 68 */     this.field_70166_s = this.field_70161_v;
/*    */     
/* 70 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 71 */       func_70106_y();
/*    */     }
/* 73 */     if (this.field_70546_d - 1 < 6) {
/* 74 */       this.particle = (144 + this.field_70546_d / 2);
/* 75 */       if (this.field_70546_d == 5) {
/* 76 */         this.field_70163_u += 0.1D;
/*    */       }
/*    */     }
/* 79 */     else if (this.field_70546_d < this.field_70547_e - 4) {
/* 80 */       this.field_70181_x += 0.005D;
/* 81 */       this.particle = (147 + this.field_70546_d % 4 / 2);
/*    */     } else {
/* 83 */       this.field_70181_x /= 2.0D;
/* 84 */       this.particle = (150 - (this.field_70547_e - this.field_70546_d) / 2);
/*    */     }
/*    */     
/* 87 */     this.field_70163_u += this.field_70181_x;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXSlimyBubble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */