/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.potions.PotionFluxTaint;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.Config;
/*    */ 
/*    */ public class EntityBottleTaint extends EntityThrowable
/*    */ {
/*    */   public EntityBottleTaint(World p_i1788_1_)
/*    */   {
/* 25 */     super(p_i1788_1_);
/*    */   }
/*    */   
/*    */   public EntityBottleTaint(World p_i1790_1_, EntityLivingBase p_i1790_2)
/*    */   {
/* 30 */     super(p_i1790_1_, p_i1790_2);
/*    */   }
/*    */   
/*    */ 
/*    */   protected float func_70185_h()
/*    */   {
/* 36 */     return 0.05F;
/*    */   }
/*    */   
/*    */   protected float func_70182_d()
/*    */   {
/* 41 */     return 0.5F;
/*    */   }
/*    */   
/*    */ 
/*    */   protected float func_70183_g()
/*    */   {
/* 47 */     return -20.0F;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_70184_a(MovingObjectPosition p_70184_1_)
/*    */   {
/* 53 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 55 */       List ents = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_178781_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v).func_72314_b(5.0D, 5.0D, 5.0D));
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 60 */       if (ents.size() > 0) {
/* 61 */         for (Object ent : ents) {
/* 62 */           EntityLivingBase el = (EntityLivingBase)ent;
/* 63 */           if ((!(el instanceof thaumcraft.api.entities.ITaintedMob)) && (!el.func_70662_br())) {
/* 64 */             el.func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), 100, 0, false, true));
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 69 */       for (int a = 0; a < 10; a++) {
/* 70 */         int xx = (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 4.0F);
/* 71 */         int zz = (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 4.0F);
/* 72 */         BlockPos p = func_180425_c().func_177982_a(xx, 0, zz);
/* 73 */         if ((this.field_70170_p.field_73012_v.nextBoolean()) && (this.field_70170_p.func_180494_b(p).field_76756_M != Config.biomeTaintID))
/*    */         {
/* 75 */           thaumcraft.common.lib.utils.Utils.setBiomeAt(this.field_70170_p, p, thaumcraft.common.lib.world.biomes.BiomeHandler.biomeTaint);
/*    */           
/* 77 */           if ((this.field_70170_p.func_175677_d(p.func_177977_b(), false)) && (this.field_70170_p.func_180495_p(p).func_177230_c().func_176200_f(this.field_70170_p, p)))
/*    */           {
/* 79 */             this.field_70170_p.func_175656_a(p, BlocksTC.fluxGoo.func_176223_P());
/*    */           } else {
/* 81 */             p = p.func_177977_b();
/* 82 */             if ((this.field_70170_p.func_175677_d(p.func_177977_b(), false)) && (this.field_70170_p.func_180495_p(p).func_177230_c().func_176200_f(this.field_70170_p, p)))
/*    */             {
/* 84 */               this.field_70170_p.func_175656_a(p, BlocksTC.fluxGoo.func_176223_P());
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 91 */       func_70106_y();
/*    */     } else {
/* 93 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(100); a++) Thaumcraft.proxy.getFX().taintsplosionFX(this);
/* 94 */       Thaumcraft.proxy.getFX().bottleTaintBreak(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityBottleTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */