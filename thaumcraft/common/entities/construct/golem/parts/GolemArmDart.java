/*    */ package thaumcraft.common.entities.construct.golem.parts;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IRangedAttackMob;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.GolemArm.IArmFunction;
/*    */ import thaumcraft.common.entities.construct.golem.ai.AIArrowAttack;
/*    */ import thaumcraft.common.entities.projectile.EntityGolemDart;
/*    */ 
/*    */ public class GolemArmDart implements GolemArm.IArmFunction
/*    */ {
/*    */   public void onMeleeAttack(IGolemAPI golem, Entity target) {}
/*    */   
/*    */   public void onRangedAttack(IGolemAPI golem, EntityLivingBase target, float range)
/*    */   {
/* 20 */     EntityGolemDart entityarrow = new EntityGolemDart(golem.getGolemWorld(), golem.getGolemEntity(), 1.6F);
/* 21 */     float dmg = (float)golem.getGolemEntity().func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111264_e).func_111126_e() / 3.0F;
/* 22 */     entityarrow.func_70239_b(dmg + range + golem.getGolemWorld().field_73012_v.nextGaussian() * 0.25D);
/* 23 */     double d0 = target.field_70165_t - golem.getGolemEntity().field_70165_t;
/* 24 */     double d1 = target.func_174813_aQ().field_72338_b + target.func_70047_e() + range * range - entityarrow.field_70163_u;
/* 25 */     double d2 = target.field_70161_v - golem.getGolemEntity().field_70161_v;
/* 26 */     entityarrow.func_70186_c(d0, d1, d2, 1.6F, 3.0F);
/*    */     
/* 28 */     golem.getGolemWorld().func_72838_d(entityarrow);
/* 29 */     golem.getGolemEntity().func_85030_a("random.bow", 1.0F, 1.0F / (golem.getGolemWorld().field_73012_v.nextFloat() * 0.4F + 0.8F));
/*    */   }
/*    */   
/*    */ 
/*    */   public net.minecraft.entity.ai.EntityAIArrowAttack getRangedAttackAI(IRangedAttackMob golem)
/*    */   {
/* 35 */     return new AIArrowAttack(golem, 1.0D, 20, 25, 16.0F);
/*    */   }
/*    */   
/*    */   public void onUpdateTick(IGolemAPI golem) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/parts/GolemArmDart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */