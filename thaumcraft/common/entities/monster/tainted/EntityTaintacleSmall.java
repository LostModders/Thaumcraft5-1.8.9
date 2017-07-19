/*    */ package thaumcraft.common.entities.monster.tainted;
/*    */ 
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.entities.ITaintedMob;
/*    */ 
/*    */ public class EntityTaintacleSmall extends EntityTaintacle implements ITaintedMob
/*    */ {
/* 11 */   int lifetime = 200;
/*    */   
/*    */   public EntityTaintacleSmall(World par1World)
/*    */   {
/* 15 */     super(par1World);
/* 16 */     func_70105_a(0.22F, 1.0F);
/* 17 */     this.field_70728_aV = 0;
/*    */   }
/*    */   
/*    */   public double getReach()
/*    */   {
/* 22 */     return 0.0D;
/*    */   }
/*    */   
/*    */   protected void func_110147_ax()
/*    */   {
/* 27 */     super.func_110147_ax();
/* 28 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5.0D);
/* 29 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 35 */     super.func_70071_h_();
/*    */     
/* 37 */     if (this.lifetime-- <= 0) {
/* 38 */       func_70665_d(net.minecraft.util.DamageSource.field_76376_m, 10.0F);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_70601_bi()
/*    */   {
/* 45 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   protected Item func_146068_u()
/*    */   {
/* 51 */     return Item.func_150899_d(0);
/*    */   }
/*    */   
/*    */   protected void func_70628_a(boolean flag, int i) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/monster/tainted/EntityTaintacleSmall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */