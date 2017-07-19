/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ChampionModUndying implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase mob, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 13 */     if (mob.field_70173_aa % 20 == 0) {
/* 14 */       mob.func_70691_i(1.0F);
/*    */     }
/* 16 */     return amount;
/*    */   }
/*    */   
/*    */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 22 */     if (boss.field_70170_p.field_73012_v.nextBoolean()) return;
/* 23 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 24 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 25 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 27 */     thaumcraft.common.Thaumcraft.proxy.getFX().drawGenericParticles(boss.func_174813_aQ().field_72340_a + w, boss.func_174813_aQ().field_72338_b + h, boss.func_174813_aQ().field_72339_c + d, 0.0D, 0.03D, 0.0D, 0.1F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.1F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.9F, true, 21, 4, 1, 4 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.0F, 0);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/mods/ChampionModUndying.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */