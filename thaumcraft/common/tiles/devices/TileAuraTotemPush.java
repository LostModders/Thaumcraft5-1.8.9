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
/*    */ 
/*    */ 
/*    */ public class TileAuraTotemPush
/*    */   extends TileAuraTotem
/*    */ {
/*    */   protected void performMagnet()
/*    */   {
/* 26 */     int x1 = MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -this.zoneInner, this.zoneInner);
/* 27 */     int z1 = MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -this.zoneInner, this.zoneInner);
/* 28 */     int x2 = 0;
/* 29 */     int z2 = 0;
/* 30 */     int cc = 0;
/* 31 */     while ((cc < 100) && (x2 >= -this.zoneInner) && (x2 <= this.zoneInner) && (z2 >= -this.zoneInner) && (z2 <= this.zoneInner)) {
/* 32 */       x2 = MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -(this.zoneInner + this.zoneOuter + 1), this.zoneInner + this.zoneOuter + 1);
/* 33 */       z2 = MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -(this.zoneInner + this.zoneOuter + 1), this.zoneInner + this.zoneOuter + 1);
/* 34 */       cc++;
/*    */     }
/* 36 */     if (cc >= 100) { return;
/*    */     }
/* 38 */     int x = func_174877_v().func_177958_n() + x1 * 16;
/* 39 */     int z = func_174877_v().func_177952_p() + z1 * 16;
/*    */     
/* 41 */     Aspect a = getAspect();
/* 42 */     if (a == null) {
/* 43 */       a = (Aspect)Aspect.getPrimalAspects().get(this.field_145850_b.field_73012_v.nextInt(6));
/*    */     }
/*    */     
/* 46 */     if (AuraHelper.drainAura(func_145831_w(), new BlockPos(x, 0, z), a, 1)) {
/* 47 */       float tc = 0.0125F * (this.zoneInner + this.zoneOuter + 2) - 0.1F * this.zonePure;
/* 48 */       x = func_174877_v().func_177958_n() + x2 * 16;
/* 49 */       z = func_174877_v().func_177952_p() + z2 * 16;
/* 50 */       if (this.field_145850_b.field_73012_v.nextFloat() < tc) {
/* 51 */         AuraHelper.pollute(func_145831_w(), func_174877_v(), 1, true);
/*    */       } else {
/* 53 */         AuraHelper.addAura(func_145831_w(), new BlockPos(x, 0, z), a, 1);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   protected void drawEffect()
/*    */   {
/* 61 */     super.drawEffect();
/* 62 */     float sx = func_174877_v().func_177958_n() + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * (5 + this.zoneOuter);
/* 63 */     float sy = func_174877_v().func_177956_o() + this.field_145850_b.field_73012_v.nextInt(4) - this.field_145850_b.field_73012_v.nextInt(3) + this.field_145850_b.field_73012_v.nextFloat();
/* 64 */     float sz = func_174877_v().func_177952_p() + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * (5 + this.zoneOuter);
/*    */     
/* 66 */     FXVisSparkle fb = new FXVisSparkle(this.field_145850_b, func_174877_v().func_177958_n() + this.field_145850_b.field_73012_v.nextFloat(), func_174877_v().func_177956_o() + this.field_145850_b.field_73012_v.nextFloat(), func_174877_v().func_177952_p() + this.field_145850_b.field_73012_v.nextFloat(), sx, sy, sz);
/*    */     
/* 68 */     if (getAspect() != null) {
/* 69 */       Color c = new Color(getAspect().getColor());
/* 70 */       fb.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*    */     } else {
/* 72 */       fb.func_70538_b(0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.7F, 0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.7F, 0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.7F);
/*    */     }
/* 74 */     ParticleEngine.instance.addEffect(this.field_145850_b, fb);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileAuraTotemPush.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */