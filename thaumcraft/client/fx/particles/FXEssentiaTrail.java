/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.blocks.devices.BlockEssentiaTransport;
/*     */ import thaumcraft.common.blocks.devices.BlockJar;
/*     */ 
/*     */ public class FXEssentiaTrail extends EntityFX
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*  25 */   private int count = 0;
/*     */   
/*     */ 
/*     */   public FXEssentiaTrail(World w, double par2, double par4, double par6, double tx, double ty, double tz, int count, int color, float scale)
/*     */   {
/*  30 */     super(w, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  31 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  32 */     this.field_70544_f = ((MathHelper.func_76126_a(count / 2.0F) * 0.1F + 1.0F) * scale);
/*     */     
/*  34 */     this.count = count;
/*  35 */     this.targetX = tx;
/*  36 */     this.targetY = ty;
/*  37 */     this.targetZ = tz;
/*  38 */     IBlockState bs = w.func_180495_p(new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v));
/*  39 */     if ((bs.func_177230_c() instanceof BlockJar)) {
/*  40 */       this.field_70163_u += 0.33000001311302185D;
/*     */     }
/*     */     
/*  43 */     if ((bs.func_177230_c() instanceof BlockEssentiaTransport)) {
/*  44 */       EnumFacing f = thaumcraft.common.lib.utils.BlockStateUtils.getFacing(bs);
/*  45 */       this.field_70165_t += f.func_82601_c() * 0.05F;
/*  46 */       this.field_70163_u += f.func_96559_d() * 0.05F;
/*  47 */       this.field_70161_v += f.func_82599_e() * 0.05F;
/*     */     }
/*     */     
/*  50 */     double dx = tx - this.field_70165_t;
/*  51 */     double dy = ty - this.field_70163_u;
/*  52 */     double dz = tz - this.field_70161_v;
/*  53 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 30.0F);
/*  54 */     if (base < 1)
/*  55 */       base = 1;
/*  56 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  58 */     this.field_70159_w = (MathHelper.func_76126_a(count / 4.0F) * 0.015F + this.field_70146_Z.nextGaussian() * 0.0020000000949949026D);
/*  59 */     this.field_70181_x = (MathHelper.func_76126_a(count / 3.0F) * 0.015F + this.field_70146_Z.nextGaussian() * 0.0020000000949949026D);
/*  60 */     this.field_70179_y = (MathHelper.func_76126_a(count / 2.0F) * 0.015F + this.field_70146_Z.nextGaussian() * 0.0020000000949949026D);
/*     */     
/*  62 */     Color c = new Color(color);
/*  63 */     float mr = c.getRed() / 255.0F * 0.2F;
/*  64 */     float mg = c.getGreen() / 255.0F * 0.2F;
/*  65 */     float mb = c.getBlue() / 255.0F * 0.2F;
/*  66 */     this.field_70552_h = (c.getRed() / 255.0F - mr + this.field_70146_Z.nextFloat() * mr);
/*  67 */     this.field_70553_i = (c.getGreen() / 255.0F - mg + this.field_70146_Z.nextFloat() * mg);
/*  68 */     this.field_70551_j = (c.getBlue() / 255.0F - mb + this.field_70146_Z.nextFloat() * mb);
/*     */     
/*  70 */     this.field_70545_g = 0.2F;
/*  71 */     this.field_70145_X = false;
/*     */     try
/*     */     {
/*  74 */       Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/*  75 */       int visibleDistance = 64;
/*  76 */       if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 32;
/*  77 */       if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180434_a(WorldRenderer wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  87 */     float t2 = 0.5625F;
/*  88 */     float t3 = 0.625F;
/*  89 */     float t4 = 0.0625F;
/*  90 */     float t5 = 0.125F;
/*     */     
/*  92 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/*  93 */     int part = this.particle + this.field_70546_d % 16;
/*     */     
/*  95 */     float s = MathHelper.func_76126_a((this.field_70546_d - this.count) / 5.0F) * 0.25F + 1.0F;
/*     */     
/*  97 */     float var12 = 0.1F * this.field_70544_f * s;
/*     */     
/*  99 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 100 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 101 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 102 */     float var16 = 1.0F;
/*     */     
/* 104 */     int i = 240;
/* 105 */     int j = i >> 16 & 0xFFFF;
/* 106 */     int k = i & 0xFFFF;
/*     */     
/* 108 */     wr.func_181662_b(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12).func_181673_a(t2, t5).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 110 */     wr.func_181662_b(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12).func_181673_a(t3, t5).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 112 */     wr.func_181662_b(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12).func_181673_a(t3, t4).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */     
/* 114 */     wr.func_181662_b(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12).func_181673_a(t2, t4).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_181671_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 121 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 125 */     this.field_70169_q = this.field_70165_t;
/* 126 */     this.field_70167_r = this.field_70163_u;
/* 127 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 129 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 130 */       func_70106_y();
/* 131 */       return;
/*     */     }
/*     */     
/* 134 */     this.field_70181_x += 0.01D * this.field_70545_g;
/* 135 */     if (!this.field_70145_X)
/* 136 */       func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 137 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 139 */     this.field_70159_w *= 0.985D;
/* 140 */     this.field_70181_x *= 0.985D;
/* 141 */     this.field_70179_y *= 0.985D;
/*     */     
/* 143 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.05F, 0.05F);
/* 144 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.05F, 0.05F);
/* 145 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.05F, 0.05F);
/*     */     
/* 147 */     double dx = this.targetX - this.field_70165_t;
/* 148 */     double dy = this.targetY - this.field_70163_u;
/* 149 */     double dz = this.targetZ - this.field_70161_v;
/* 150 */     double d13 = 0.01D;
/* 151 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 153 */     if (d11 < 2.0D) {
/* 154 */       this.field_70544_f *= 0.98F;
/*     */     }
/*     */     
/* 157 */     if (this.field_70544_f < 0.2F) {
/* 158 */       func_70106_y();
/* 159 */       return;
/*     */     }
/*     */     
/* 162 */     dx /= d11;
/* 163 */     dy /= d11;
/* 164 */     dz /= d11;
/*     */     
/* 166 */     this.field_70159_w += dx * (d13 / Math.min(1.0D, d11));
/* 167 */     this.field_70181_x += dy * (d13 / Math.min(1.0D, d11));
/* 168 */     this.field_70179_y += dz * (d13 / Math.min(1.0D, d11));
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 173 */     this.field_70545_g = value;
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
/*     */ 
/* 271 */   public int particle = 24;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXEssentiaTrail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */