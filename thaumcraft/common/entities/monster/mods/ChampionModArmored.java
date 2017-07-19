/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ChampionModArmored implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase mob, EntityLivingBase target, net.minecraft.util.DamageSource source, float amount)
/*    */   {
/* 11 */     if (!source.func_76363_c()) {
/* 12 */       float f1 = amount * 19.0F;
/* 13 */       amount = f1 / 25.0F;
/*    */     }
/* 15 */     return amount;
/*    */   }
/*    */   
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 20 */     if (boss.field_70170_p.field_73012_v.nextInt(4) != 0) return;
/* 21 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 22 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 23 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 25 */     thaumcraft.common.Thaumcraft.proxy.getFX().drawGenericParticles(boss.func_174813_aQ().field_72340_a + w, boss.func_174813_aQ().field_72338_b + h, boss.func_174813_aQ().field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.9F, 0.9F, 0.9F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.7F, false, 112, 9, 1, 5 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.6F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.0F, 0);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/mods/ChampionModArmored.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */