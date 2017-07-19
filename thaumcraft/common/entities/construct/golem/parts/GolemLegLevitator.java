/*    */ package thaumcraft.common.entities.construct.golem.parts;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ 
/*    */ public class GolemLegLevitator implements thaumcraft.api.golems.parts.GolemLeg.ILegFunction
/*    */ {
/*    */   public void onUpdateTick(IGolemAPI golem)
/*    */   {
/* 11 */     if ((golem.getGolemWorld().field_72995_K) && ((!golem.getGolemEntity().field_70122_E) || (golem.getGolemEntity().field_70173_aa % 5 == 0))) {
/* 12 */       thaumcraft.common.Thaumcraft.proxy.getFX().drawGolemFlyParticles(golem.getGolemEntity().field_70165_t, golem.getGolemEntity().field_70163_u + 0.1D, golem.getGolemEntity().field_70161_v, golem.getGolemWorld().field_73012_v.nextGaussian() / 100.0D, -0.1D, golem.getGolemWorld().field_73012_v.nextGaussian() / 100.0D);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/parts/GolemLegLevitator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */