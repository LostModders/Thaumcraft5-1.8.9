/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*    */ 
/*    */ public class AICultistHurtByTarget extends EntityAITarget
/*    */ {
/*    */   boolean entityCallsForHelp;
/*    */   private int field_142052_b;
/*    */   
/*    */   public AICultistHurtByTarget(EntityCreature p_i1660_1_, boolean p_i1660_2_)
/*    */   {
/* 19 */     super(p_i1660_1_, false);
/* 20 */     this.entityCallsForHelp = p_i1660_2_;
/* 21 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 29 */     int i = this.field_75299_d.func_142015_aE();
/* 30 */     return (i != this.field_142052_b) && (func_75296_a(this.field_75299_d.func_70643_av(), false));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 38 */     this.field_75299_d.func_70624_b(this.field_75299_d.func_70643_av());
/* 39 */     this.field_142052_b = this.field_75299_d.func_142015_aE();
/*    */     
/* 41 */     if (this.entityCallsForHelp)
/*    */     {
/* 43 */       double d0 = func_111175_f() * 2.0D;
/* 44 */       List list = this.field_75299_d.field_70170_p.func_72872_a(thaumcraft.common.entities.monster.cult.EntityCultist.class, AxisAlignedBB.func_178781_a(this.field_75299_d.field_70165_t, this.field_75299_d.field_70163_u, this.field_75299_d.field_70161_v, this.field_75299_d.field_70165_t + 1.0D, this.field_75299_d.field_70163_u + 1.0D, this.field_75299_d.field_70161_v + 1.0D).func_72314_b(d0, 10.0D, d0));
/*    */       
/*    */ 
/* 47 */       Iterator iterator = list.iterator();
/*    */       
/* 49 */       while (iterator.hasNext())
/*    */       {
/* 51 */         EntityCreature entitycreature = (EntityCreature)iterator.next();
/*    */         
/* 53 */         if ((this.field_75299_d != entitycreature) && (entitycreature.func_70638_az() == null) && (!entitycreature.func_142014_c(this.field_75299_d.func_70643_av())))
/*    */         {
/*    */ 
/* 56 */           if (((entitycreature instanceof EntityCultistCleric)) && (((EntityCultistCleric)entitycreature).getIsRitualist()))
/*    */           {
/* 58 */             ((EntityCultistCleric)entitycreature).rage += 1;
/* 59 */             this.field_75299_d.field_70170_p.func_72960_a(entitycreature, (byte)19);
/* 60 */             if (this.field_75299_d.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 61 */               ((EntityCultistCleric)entitycreature).setIsRitualist(false);
/* 62 */               entitycreature.func_70624_b(this.field_75299_d.func_70643_av());
/*    */             }
/*    */           } else {
/* 65 */             entitycreature.func_70624_b(this.field_75299_d.func_70643_av());
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 70 */     super.func_75249_e();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/ai/combat/AICultistHurtByTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */