/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ChampionModMighty implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float ammount)
/*    */   {
/* 15 */     return 0.0F;
/*    */   }
/*    */   
/*    */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 21 */     if (boss.field_70170_p.field_73012_v.nextFloat() > 0.3F) return;
/* 22 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 23 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 24 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/* 25 */     int p = 176 + boss.field_70170_p.field_73012_v.nextInt(4) * 3;
/* 26 */     Thaumcraft.proxy.getFX().drawGenericParticles(boss.func_174813_aQ().field_72340_a + w, boss.func_174813_aQ().field_72338_b + h, boss.func_174813_aQ().field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.7F, false, p, 3, 1, 4 + boss.field_70170_p.field_73012_v.nextInt(3), 0, 1.0F + boss.field_70170_p.field_73012_v.nextFloat() * 0.3F, 0.0F, 0);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/mods/ChampionModMighty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */