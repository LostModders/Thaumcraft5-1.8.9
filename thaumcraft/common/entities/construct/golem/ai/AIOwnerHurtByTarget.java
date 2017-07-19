/*    */ package thaumcraft.common.entities.construct.golem.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ import thaumcraft.common.entities.construct.EntityOwnedConstruct;
/*    */ 
/*    */ public class AIOwnerHurtByTarget extends EntityAITarget
/*    */ {
/*    */   EntityOwnedConstruct theDefendingTameable;
/*    */   EntityLivingBase theOwnerAttacker;
/*    */   private int field_142051_e;
/*    */   
/*    */   public AIOwnerHurtByTarget(EntityOwnedConstruct p_i1667_1_)
/*    */   {
/* 15 */     super(p_i1667_1_, false);
/* 16 */     this.theDefendingTameable = p_i1667_1_;
/* 17 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */   public boolean func_75250_a()
/*    */   {
/* 22 */     if (!this.theDefendingTameable.isOwned())
/*    */     {
/* 24 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 28 */     EntityLivingBase entitylivingbase = this.theDefendingTameable.getOwnerEntity();
/*    */     
/* 30 */     if (entitylivingbase == null)
/*    */     {
/* 32 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 36 */     this.theOwnerAttacker = entitylivingbase.func_70643_av();
/* 37 */     int i = entitylivingbase.func_142015_aE();
/* 38 */     return (i != this.field_142051_e) && (func_75296_a(this.theOwnerAttacker, false));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 45 */     this.field_75299_d.func_70624_b(this.theOwnerAttacker);
/* 46 */     EntityLivingBase entitylivingbase = this.theDefendingTameable.getOwnerEntity();
/*    */     
/* 48 */     if (entitylivingbase != null)
/*    */     {
/* 50 */       this.field_142051_e = entitylivingbase.func_142015_aE();
/*    */     }
/*    */     
/* 53 */     super.func_75249_e();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/AIOwnerHurtByTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */