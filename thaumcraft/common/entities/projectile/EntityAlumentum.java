/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class EntityAlumentum extends EntityThrowable
/*    */ {
/*    */   public EntityAlumentum(World par1World)
/*    */   {
/* 13 */     super(par1World);
/*    */   }
/*    */   
/*    */   public EntityAlumentum(World par1World, net.minecraft.entity.EntityLivingBase par2EntityLiving)
/*    */   {
/* 18 */     super(par1World, par2EntityLiving);
/*    */   }
/*    */   
/*    */   public EntityAlumentum(World par1World, double par2, double par4, double par6)
/*    */   {
/* 23 */     super(par1World, par2, par4, par6);
/*    */   }
/*    */   
/*    */ 
/*    */   protected float func_70182_d()
/*    */   {
/* 29 */     return 0.75F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 36 */     super.func_70071_h_();
/*    */     
/* 38 */     if (this.field_70170_p.field_72995_K)
/*    */     {
/* 40 */       for (int a = 0; a < 3; a++) {
/* 41 */         Thaumcraft.proxy.getFX().wispFX2(this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F, this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F, this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F, 0.3F, 5, true, true, 0.02F);
/*    */         
/*    */ 
/*    */ 
/* 45 */         double x2 = (this.field_70165_t + this.field_70169_q) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 46 */         double y2 = (this.field_70163_u + this.field_70167_r) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 47 */         double z2 = (this.field_70161_v + this.field_70166_s) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 48 */         Thaumcraft.proxy.getFX().wispFX2(x2, y2, z2, 0.3F, 5, true, true, 0.02F);
/* 49 */         Thaumcraft.proxy.getFX().sparkle((float)this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, (float)this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, (float)this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, 6);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_70184_a(net.minecraft.util.MovingObjectPosition par1MovingObjectPosition)
/*    */   {
/* 61 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 63 */       boolean var2 = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing");
/* 64 */       this.field_70170_p.func_72876_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.66F, var2);
/* 65 */       func_70106_y();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityAlumentum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */