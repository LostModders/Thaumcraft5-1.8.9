/*    */ package thaumcraft.common.entities.construct.golem.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.pathfinding.PathEntity;
/*    */ import net.minecraft.pathfinding.PathFinder;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PathNavigateGolemAir extends PathNavigate
/*    */ {
/*    */   public PathNavigateGolemAir(EntityLiving p_i45873_1_, World worldIn)
/*    */   {
/* 15 */     super(p_i45873_1_, worldIn);
/*    */   }
/*    */   
/*    */ 
/*    */   protected PathFinder func_179679_a()
/*    */   {
/* 21 */     return new PathFinder(new FlightNodeProcessor());
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean func_75485_k()
/*    */   {
/* 27 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   protected Vec3 func_75502_i()
/*    */   {
/* 33 */     return new Vec3(this.field_75515_a.field_70165_t, this.field_75515_a.field_70163_u + this.field_75515_a.field_70131_O * 0.5D, this.field_75515_a.field_70161_v);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_75508_h()
/*    */   {
/* 39 */     Vec3 vec3 = func_75502_i();
/* 40 */     float f = this.field_75515_a.field_70130_N * this.field_75515_a.field_70130_N;
/* 41 */     byte b0 = 6;
/*    */     
/* 43 */     if (vec3.func_72436_e(this.field_75514_c.func_75881_a(this.field_75515_a, this.field_75514_c.func_75873_e())) < f)
/*    */     {
/* 45 */       this.field_75514_c.func_75875_a();
/*    */     }
/*    */     
/* 48 */     for (int i = Math.min(this.field_75514_c.func_75873_e() + b0, this.field_75514_c.func_75874_d() - 1); i > this.field_75514_c.func_75873_e(); i--)
/*    */     {
/* 50 */       Vec3 vec31 = this.field_75514_c.func_75881_a(this.field_75515_a, i);
/*    */       
/* 52 */       if ((vec31.func_72436_e(vec3) <= 36.0D) && (func_75493_a(vec3, vec31, 0, 0, 0)))
/*    */       {
/* 54 */         this.field_75514_c.func_75872_c(i);
/* 55 */         break;
/*    */       }
/*    */     }
/*    */     
/* 59 */     func_179677_a(vec3);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_75487_m()
/*    */   {
/* 65 */     super.func_75487_m();
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean func_75493_a(Vec3 p_75493_1_, Vec3 p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_)
/*    */   {
/* 71 */     MovingObjectPosition movingobjectposition = this.field_75513_b.func_147447_a(p_75493_1_, new Vec3(p_75493_2_.field_72450_a, p_75493_2_.field_72448_b + this.field_75515_a.field_70131_O * 0.5D, p_75493_2_.field_72449_c), false, true, false);
/* 72 */     return (movingobjectposition == null) || (movingobjectposition.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.MISS);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/PathNavigateGolemAir.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */