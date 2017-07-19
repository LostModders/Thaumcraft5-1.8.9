/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class FXGenericP2P extends FXGeneric
/*    */ {
/*    */   private double targetX;
/*    */   private double targetY;
/*    */   private double targetZ;
/*    */   
/*    */   public FXGenericP2P(World world, double x, double y, double z, double xx, double yy, double zz)
/*    */   {
/* 14 */     super(world, x, y, z, 0.0D, 0.0D, 0.0D);
/* 15 */     func_70105_a(0.1F, 0.1F);
/* 16 */     this.field_70169_q = this.field_70165_t;
/* 17 */     this.field_70167_r = this.field_70163_u;
/* 18 */     this.field_70166_s = this.field_70161_v;
/*    */     
/* 20 */     this.targetX = xx;
/* 21 */     this.targetY = yy;
/* 22 */     this.targetZ = zz;
/*    */     
/* 24 */     double dx = xx - this.field_70165_t;
/* 25 */     double dy = yy - this.field_70163_u;
/* 26 */     double dz = zz - this.field_70161_v;
/* 27 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/* 28 */     if (base < 1) base = 1;
/* 29 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*    */     
/* 31 */     this.field_70142_S = x;
/* 32 */     this.field_70137_T = y;
/* 33 */     this.field_70136_U = z;
/* 34 */     this.field_70548_b = 0.0F;
/* 35 */     this.field_70549_c = 0.0F;
/* 36 */     float f3 = 0.01F;
/* 37 */     this.field_70159_w = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/* 38 */     this.field_70181_x = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/* 39 */     this.field_70179_y = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*    */     
/* 41 */     this.field_70545_g = 0.2F;
/* 42 */     this.field_70145_X = false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 50 */     this.field_70169_q = this.field_70165_t;
/* 51 */     this.field_70167_r = this.field_70163_u;
/* 52 */     this.field_70166_s = this.field_70161_v;
/*    */     
/*    */ 
/*    */ 
/* 56 */     if ((this.field_70546_d++ >= this.field_70547_e) || ((MathHelper.func_76128_c(this.field_70165_t) == MathHelper.func_76128_c(this.targetX)) && (MathHelper.func_76128_c(this.field_70163_u) == MathHelper.func_76128_c(this.targetY)) && (MathHelper.func_76128_c(this.field_70161_v) == MathHelper.func_76128_c(this.targetZ))))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/* 61 */       func_70106_y();
/* 62 */       return;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 67 */     if (!this.field_70145_X) func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 68 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*    */     
/* 70 */     this.field_70159_w *= 0.985D;
/* 71 */     this.field_70181_x *= 0.985D;
/* 72 */     this.field_70179_y *= 0.985D;
/*    */     
/* 74 */     double dx = this.targetX - this.field_70165_t;
/* 75 */     double dy = this.targetY - this.field_70163_u;
/* 76 */     double dz = this.targetZ - this.field_70161_v;
/* 77 */     double d13 = 0.3D;
/* 78 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*    */     
/* 80 */     if (d11 < 4.0D) {
/* 81 */       this.field_70544_f *= 0.9F;
/* 82 */       d13 = 0.6D;
/*    */     }
/*    */     
/* 85 */     dx /= d11;
/* 86 */     dy /= d11;
/* 87 */     dz /= d11;
/*    */     
/* 89 */     this.field_70159_w += dx * d13;
/* 90 */     this.field_70181_x += dy * d13;
/* 91 */     this.field_70179_y += dz * d13;
/*    */     
/* 93 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.35F, 0.35F);
/* 94 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.35F, 0.35F);
/* 95 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.35F, 0.35F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXGenericP2P.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */