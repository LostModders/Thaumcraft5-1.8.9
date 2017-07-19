/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ChampionModVampire implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 13 */     boss.func_70691_i(Math.max(2.0F, amount / 2.0F));
/* 14 */     return amount;
/*    */   }
/*    */   
/*    */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 20 */     if (boss.field_70170_p.field_73012_v.nextFloat() > 0.2F) return;
/* 21 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 22 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 23 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 25 */     thaumcraft.common.Thaumcraft.proxy.getFX().drawGenericParticles(boss.func_174813_aQ().field_72340_a + w, boss.func_174813_aQ().field_72338_b + h, boss.func_174813_aQ().field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.9F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.0F, 0.9F, false, 147, 4, 1, 8 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.0F, 0);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/mods/ChampionModVampire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */