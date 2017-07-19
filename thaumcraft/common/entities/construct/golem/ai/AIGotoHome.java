/*    */ package thaumcraft.common.entities.construct.golem.ai;
/*    */ 
/*    */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.Vec3;
/*    */ import thaumcraft.common.entities.construct.golem.EntityThaumcraftGolem;
/*    */ 
/*    */ public class AIGotoHome extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   protected EntityThaumcraftGolem golem;
/*    */   private double movePosX;
/*    */   private double movePosY;
/*    */   private double movePosZ;
/* 15 */   protected int idleCounter = 10;
/*    */   
/*    */   public AIGotoHome(EntityThaumcraftGolem g)
/*    */   {
/* 19 */     this.golem = g;
/* 20 */     func_75248_a(5);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 26 */     if (this.idleCounter > 0)
/*    */     {
/* 28 */       this.idleCounter -= 1;
/* 29 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 33 */     this.idleCounter = 50;
/* 34 */     double dd = this.golem.func_174831_c(this.golem.func_180486_cf());
/* 35 */     if (dd < 9.0D) return false;
/* 36 */     if (dd > 256.0D) {
/* 37 */       Vec3 vec3 = RandomPositionGenerator.func_75464_a(this.golem, 16, 7, new Vec3(this.golem.func_180486_cf().func_177958_n(), this.golem.func_180486_cf().func_177956_o(), this.golem.func_180486_cf().func_177952_p()));
/*    */       
/*    */ 
/* 40 */       if (vec3 == null)
/*    */       {
/* 42 */         return false;
/*    */       }
/*    */       
/*    */ 
/* 46 */       this.movePosX = vec3.field_72450_a;
/* 47 */       this.movePosY = vec3.field_72448_b;
/* 48 */       this.movePosZ = vec3.field_72449_c;
/* 49 */       return true;
/*    */     }
/*    */     
/* 52 */     this.movePosX = this.golem.func_180486_cf().func_177958_n();
/* 53 */     this.movePosY = this.golem.func_180486_cf().func_177956_o();
/* 54 */     this.movePosZ = this.golem.func_180486_cf().func_177952_p();
/* 55 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 63 */     this.golem.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.golem.getGolemMoveSpeed());
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 69 */     return (this.golem.getTask() != null) && (!this.golem.func_70661_as().func_75500_f()) && (this.golem.func_174831_c(this.golem.func_180486_cf()) > 3.0D);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_75251_c()
/*    */   {
/* 75 */     this.idleCounter = 50;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/AIGotoHome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */