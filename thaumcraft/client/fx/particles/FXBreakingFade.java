/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.client.particle.EntityBreakingFX;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class FXBreakingFade
/*    */   extends EntityBreakingFX
/*    */ {
/*    */   public FXBreakingFade(World worldIn, double p_i1197_2_, double p_i1197_4_, double p_i1197_6_, double p_i1197_8_, double p_i1197_10_, double p_i1197_12_, Item p_i1197_14_, int p_i1197_15_)
/*    */   {
/* 19 */     super(worldIn, p_i1197_2_, p_i1197_4_, p_i1197_6_, p_i1197_8_, p_i1197_10_, p_i1197_12_, p_i1197_14_, p_i1197_15_);
/*    */   }
/*    */   
/*    */ 
/*    */   public FXBreakingFade(World worldIn, double p_i1196_2_, double p_i1196_4_, double p_i1196_6_, Item p_i1196_8_, int p_i1196_9_)
/*    */   {
/* 25 */     super(worldIn, p_i1196_2_, p_i1196_4_, p_i1196_6_, p_i1196_8_, p_i1196_9_);
/*    */   }
/*    */   
/*    */   public FXBreakingFade(World worldIn, double p_i1195_2_, double p_i1195_4_, double p_i1195_6_, Item p_i1195_8_)
/*    */   {
/* 30 */     super(worldIn, p_i1195_2_, p_i1195_4_, p_i1195_6_, p_i1195_8_);
/*    */   }
/*    */   
/*    */   public void setParticleMaxAge(int particleMaxAge) {
/* 34 */     this.field_70547_e = particleMaxAge;
/*    */   }
/*    */   
/*    */   public void setParticleGravity(float f) {
/* 38 */     this.field_70545_g = f;
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_70537_b()
/*    */   {
/* 44 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_)
/*    */   {
/* 50 */     GlStateManager.func_179132_a(false);
/* 51 */     float f6 = (this.field_94054_b + this.field_70548_b / 4.0F) / 16.0F;
/* 52 */     float f7 = f6 + 0.015609375F;
/* 53 */     float f8 = (this.field_94055_c + this.field_70549_c / 4.0F) / 16.0F;
/* 54 */     float f9 = f8 + 0.015609375F;
/* 55 */     float f10 = 0.1F * this.field_70544_f;
/* 56 */     float fade = 1.0F - this.field_70546_d / this.field_70547_e;
/*    */     
/* 58 */     if (this.field_70550_a != null)
/*    */     {
/* 60 */       f6 = this.field_70550_a.func_94214_a(this.field_70548_b / 4.0F * 16.0F);
/* 61 */       f7 = this.field_70550_a.func_94214_a((this.field_70548_b + 1.0F) / 4.0F * 16.0F);
/* 62 */       f8 = this.field_70550_a.func_94207_b(this.field_70549_c / 4.0F * 16.0F);
/* 63 */       f9 = this.field_70550_a.func_94207_b((this.field_70549_c + 1.0F) / 4.0F * 16.0F);
/*    */     }
/* 65 */     int i = func_70070_b(p_180434_3_);
/* 66 */     int j = i >> 16 & 0xFFFF;
/* 67 */     int k = i & 0xFFFF;
/* 68 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * p_180434_3_ - field_70556_an);
/* 69 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * p_180434_3_ - field_70554_ao);
/* 70 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * p_180434_3_ - field_70555_ap);
/* 71 */     p_180434_1_.func_181662_b(f11 - p_180434_4_ * f10 - p_180434_7_ * f10, f12 - p_180434_5_ * f10, f13 - p_180434_6_ * f10 - p_180434_8_ * f10).func_181673_a(f6, f9).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * fade).func_181671_a(j, k).func_181675_d();
/*    */     
/* 73 */     p_180434_1_.func_181662_b(f11 - p_180434_4_ * f10 + p_180434_7_ * f10, f12 + p_180434_5_ * f10, f13 - p_180434_6_ * f10 + p_180434_8_ * f10).func_181673_a(f6, f8).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * fade).func_181671_a(j, k).func_181675_d();
/*    */     
/* 75 */     p_180434_1_.func_181662_b(f11 + p_180434_4_ * f10 + p_180434_7_ * f10, f12 + p_180434_5_ * f10, f13 + p_180434_6_ * f10 + p_180434_8_ * f10).func_181673_a(f7, f8).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * fade).func_181671_a(j, k).func_181675_d();
/*    */     
/* 77 */     p_180434_1_.func_181662_b(f11 + p_180434_4_ * f10 - p_180434_7_ * f10, f12 - p_180434_5_ * f10, f13 + p_180434_6_ * f10 - p_180434_8_ * f10).func_181673_a(f7, f9).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * fade).func_181671_a(j, k).func_181675_d();
/*    */     
/* 79 */     GlStateManager.func_179132_a(true);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/fx/particles/FXBreakingFade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */