/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityGolemDart
/*    */   extends EntityPrimalArrow
/*    */ {
/*    */   public EntityGolemDart(World par1World)
/*    */   {
/* 11 */     super(par1World);
/* 12 */     func_70105_a(0.2F, 0.2F);
/*    */   }
/*    */   
/*    */   public EntityGolemDart(World par1World, double par2, double par4, double par6)
/*    */   {
/* 17 */     super(par1World, par2, par4, par6);
/* 18 */     func_70105_a(0.2F, 0.2F);
/*    */   }
/*    */   
/*    */   public EntityGolemDart(World par1World, EntityLivingBase par2EntityLivingBase, float par3)
/*    */   {
/* 23 */     super(par1World, par2EntityLivingBase, par3, 8);
/* 24 */     func_70105_a(0.2F, 0.2F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityGolemDart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */