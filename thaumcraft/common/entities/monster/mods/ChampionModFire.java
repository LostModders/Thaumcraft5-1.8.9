/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ChampionModFire implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 13 */     if (boss.field_70170_p.field_73012_v.nextFloat() < 0.4F) {
/* 14 */       target.func_70015_d(4);
/*    */     }
/* 16 */     return amount;
/*    */   }
/*    */   
/*    */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 22 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 23 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 24 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 26 */     thaumcraft.common.Thaumcraft.proxy.getFX().drawGenericParticles(boss.func_174813_aQ().field_72340_a + w, boss.func_174813_aQ().field_72338_b + h, boss.func_174813_aQ().field_72339_c + d, 0.0D, 0.03D, 0.0D, 0.9F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 1.0F, 1.0F, 0.7F, false, 160, 10, 1, 8 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.7F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.0F, 0);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/mods/ChampionModFire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */