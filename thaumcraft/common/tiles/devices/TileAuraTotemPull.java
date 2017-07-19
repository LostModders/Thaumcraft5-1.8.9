/*    */ package thaumcraft.common.tiles.devices;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aura.AuraHelper;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXVisSparkle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileAuraTotemPull
/*    */   extends TileAuraTotem
/*    */ {
/*    */   protected void performMagnet()
/*    */   {
/* 24 */     int x1 = MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -this.zoneInner, this.zoneInner);
/* 25 */     int z1 = MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -this.zoneInner, this.zoneInner);
/* 26 */     int x2 = 0;
/* 27 */     int z2 = 0;
/* 28 */     int cc = 0;
/* 29 */     while ((cc < 100) && (x2 >= -this.zoneInner) && (x2 <= this.zoneInner) && (z2 >= -this.zoneInner) && (z2 <= this.zoneInner)) {
/* 30 */       x2 = MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -(this.zoneInner + this.zoneOuter + 1), this.zoneInner + this.zoneOuter + 1);
/* 31 */       z2 = MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -(this.zoneInner + this.zoneOuter + 1), this.zoneInner + this.zoneOuter + 1);
/* 32 */       cc++;
/*    */     }
/* 34 */     if (cc >= 100) { return;
/*    */     }
/* 36 */     int x = func_174877_v().func_177958_n() + x2 * 16;
/* 37 */     int z = func_174877_v().func_177952_p() + z2 * 16;
/*    */     
/* 39 */     Aspect a = getAspect();
/* 40 */     if (a == null) {
/* 41 */       a = (Aspect)Aspect.getPrimalAspects().get(this.field_145850_b.field_73012_v.nextInt(6));
/*    */     }
/*    */     
/* 44 */     if (AuraHelper.drainAura(func_145831_w(), new BlockPos(x, 0, z), a, 1)) {
/* 45 */       float tc = 0.0125F * (this.zoneInner + this.zoneOuter + 2) - 0.025F * this.zonePure;
/* 46 */       x = func_174877_v().func_177958_n() + x1 * 16;
/* 47 */       z = func_174877_v().func_177952_p() + z1 * 16;
/* 48 */       if (this.field_145850_b.field_73012_v.nextFloat() < tc) {
/* 49 */         AuraHelper.pollute(func_145831_w(), func_174877_v(), 1, true);
/*    */       } else
/* 51 */         AuraHelper.addAura(func_145831_w(), new BlockPos(x, 0, z), a, 1);
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   protected void drawEffect() {
/* 57 */     super.drawEffect();
/* 58 */     float sx = func_174877_v().func_177958_n() + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * (5 + this.zoneOuter);
/* 59 */     float sy = func_174877_v().func_177956_o() + this.field_145850_b.field_73012_v.nextInt(4) - this.field_145850_b.field_73012_v.nextInt(3) + this.field_145850_b.field_73012_v.nextFloat();
/* 60 */     float sz = func_174877_v().func_177952_p() + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * (5 + this.zoneOuter);
/*    */     
/* 62 */     FXVisSparkle fb = new FXVisSparkle(this.field_145850_b, sx, sy, sz, func_174877_v().func_177958_n() + this.field_145850_b.field_73012_v.nextFloat(), func_174877_v().func_177956_o() + this.field_145850_b.field_73012_v.nextFloat(), func_174877_v().func_177952_p() + this.field_145850_b.field_73012_v.nextFloat());
/*    */     
/* 64 */     if (getAspect() != null) {
/* 65 */       Color c = new Color(getAspect().getColor());
/* 66 */       fb.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*    */     } else {
/* 68 */       fb.func_70538_b(0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.7F, 0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.7F, 0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.7F);
/*    */     }
/* 70 */     ParticleEngine.instance.addEffect(this.field_145850_b, fb);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileAuraTotemPull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */