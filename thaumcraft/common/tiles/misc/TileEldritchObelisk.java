/*    */ package thaumcraft.common.tiles.misc;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.entities.IEldritchMob;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.EntityUtils;
/*    */ 
/*    */ public class TileEldritchObelisk extends thaumcraft.api.blocks.TileThaumcraft implements ITickable
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 25 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 0.3D, func_174877_v().func_177956_o() - 0.3D, func_174877_v().func_177952_p() - 0.3D, func_174877_v().func_177958_n() + 1.3D, func_174877_v().func_177956_o() + 5.3D, func_174877_v().func_177952_p() + 1.3D);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double func_145833_n()
/*    */   {
/* 32 */     return 9216.0D;
/*    */   }
/*    */   
/* 35 */   private int counter = 0;
/*    */   
/*    */ 
/*    */   public void func_73660_a()
/*    */   {
/* 40 */     if ((!this.field_145850_b.field_72995_K) && (this.counter % 20 == 0))
/*    */     {
/* 42 */       ArrayList<Entity> list = EntityUtils.getEntitiesInRange(func_145831_w(), func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o(), func_174877_v().func_177952_p() + 0.5D, null, EntityLivingBase.class, 6.0D);
/* 43 */       if ((list != null) && (list.size() > 0)) {
/* 44 */         for (Entity e : list) {
/* 45 */           if (((e instanceof IEldritchMob)) && ((e instanceof EntityLivingBase)) && (!((EntityLivingBase)e).func_82165_m(Potion.field_76428_l.field_76415_H)))
/*    */             try
/*    */             {
/* 48 */               ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76420_g.func_76396_c(), 40, 0, true, true));
/* 49 */               ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76428_l.func_76396_c(), 40, 0, true, true));
/*    */             } catch (Exception e1) {}
/*    */         }
/*    */       }
/*    */     }
/* 54 */     if (this.field_145850_b.field_72995_K)
/*    */     {
/* 56 */       ArrayList<Entity> list = EntityUtils.getEntitiesInRange(func_145831_w(), func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o(), func_174877_v().func_177952_p() + 0.5D, null, EntityLivingBase.class, 6.0D);
/* 57 */       if ((list != null) && (list.size() > 0)) {
/* 58 */         for (Entity e : list) {
/* 59 */           if (((e instanceof IEldritchMob)) && ((e instanceof EntityLivingBase))) {
/* 60 */             Thaumcraft.proxy.getFX().wispFX4(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o() + 1 + this.field_145850_b.field_73012_v.nextFloat() * 3.0F, func_174877_v().func_177952_p() + 0.5D, e, 5, true, 1.0F);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileEldritchObelisk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */