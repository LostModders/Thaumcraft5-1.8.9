/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class EntityEldritchOrb extends EntityThrowable
/*    */ {
/*    */   public EntityEldritchOrb(World par1World)
/*    */   {
/* 19 */     super(par1World);
/*    */   }
/*    */   
/*    */   public EntityEldritchOrb(World par1World, EntityLivingBase p) {
/* 23 */     super(par1World, p);
/* 24 */     Vec3 v = p.func_70040_Z();
/* 25 */     func_70012_b(p.field_70165_t + v.field_72450_a / 2.0D, p.field_70163_u + p.func_70047_e() + v.field_72448_b / 2.0D, p.field_70161_v + v.field_72449_c / 2.0D, p.field_70177_z, p.field_70125_A);
/*    */   }
/*    */   
/*    */   protected float func_70185_h()
/*    */   {
/* 30 */     return 0.0F;
/*    */   }
/*    */   
/*    */   public void func_70071_h_()
/*    */   {
/* 35 */     super.func_70071_h_();
/* 36 */     if (this.field_70173_aa > 100) {
/* 37 */       func_70106_y();
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_70103_a(byte b)
/*    */   {
/* 43 */     if (b == 16) {
/* 44 */       if (this.field_70170_p.field_72995_K) {
/* 45 */         for (int a = 0; a < 30; a++) {
/* 46 */           float fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 47 */           float fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 48 */           float fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 49 */           Thaumcraft.proxy.getFX().wispFX3(this.field_70165_t + fx, this.field_70163_u + fy, this.field_70161_v + fz, this.field_70165_t + fx * 8.0F, this.field_70163_u + fy * 8.0F, this.field_70161_v + fz * 8.0F, 0.3F, 5, true, 0.02F);
/*    */         }
/*    */         
/*    */       }
/*    */       
/*    */     }
/*    */     else {
/* 56 */       super.func_70103_a(b);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_70184_a(MovingObjectPosition mop)
/*    */   {
/* 65 */     if ((!this.field_70170_p.field_72995_K) && (func_85052_h() != null)) {
/* 66 */       List list = this.field_70170_p.func_72839_b(func_85052_h(), func_174813_aQ().func_72314_b(2.0D, 2.0D, 2.0D));
/* 67 */       for (int i = 0; i < list.size(); i++) {
/* 68 */         Entity entity1 = (Entity)list.get(i);
/* 69 */         if (((entity1 instanceof EntityLivingBase)) && (!((EntityLivingBase)entity1).func_70662_br())) {
/* 70 */           ((EntityLivingBase)entity1).func_70097_a(net.minecraft.util.DamageSource.func_76354_b(this, func_85052_h()), (float)func_85052_h().func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.666F);
/*    */           
/*    */           try
/*    */           {
/* 74 */             ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 160, 0));
/*    */           }
/*    */           catch (Exception e) {}
/*    */         }
/*    */       }
/*    */       
/* 80 */       this.field_70170_p.func_72956_a(this, "random.fizz", 0.5F, 2.6F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.8F);
/* 81 */       this.field_70173_aa = 100;
/* 82 */       this.field_70170_p.func_72960_a(this, (byte)16);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityEldritchOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */