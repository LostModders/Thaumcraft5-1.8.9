/*    */ package thaumcraft.common.entities.construct.golem.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.EntityLookHelper;
/*    */ import net.minecraft.pathfinding.PathEntity;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.pathfinding.PathPoint;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.golems.tasks.Task;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.entities.construct.golem.EntityThaumcraftGolem;
/*    */ 
/*    */ public class AIGotoEntity extends AIGoto
/*    */ {
/*    */   public AIGotoEntity(EntityThaumcraftGolem g)
/*    */   {
/* 18 */     super(g, (byte)1);
/*    */   }
/*    */   
/*    */   public void func_75246_d()
/*    */   {
/* 23 */     super.func_75246_d();
/* 24 */     if (this.golem.func_70671_ap() != null) {
/* 25 */       this.golem.func_70671_ap().func_75651_a(this.golem.getTask().getEntity(), 10.0F, this.golem.func_70646_bf());
/*    */     }
/*    */   }
/*    */   
/*    */   protected void moveTo() {
/* 30 */     this.golem.func_70661_as().func_75497_a(this.golem.getTask().getEntity(), this.golem.getGolemMoveSpeed());
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean findDestination()
/*    */   {
/* 36 */     java.util.ArrayList<Task> list = thaumcraft.common.entities.construct.golem.tasks.TaskHandler.getEntityTasksSorted(this.golem.field_70170_p.field_73011_w.func_177502_q(), this.golem.func_110124_au(), this.golem);
/*    */     
/* 38 */     for (Task ticket : list) {
/* 39 */       if ((areGolemTagsValidForTask(ticket)) && (ticket.canGolemPerformTask(this.golem)) && (this.golem.func_180485_d(ticket.getEntity().func_180425_c())) && (isValidDestination(this.golem.field_70170_p, ticket.getEntity().func_180425_c())) && (canEasilyReach(ticket.getEntity())))
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/* 44 */         this.golem.setTask(ticket);
/* 45 */         this.golem.getTask().setReserved(true);
/* 46 */         this.minDist = (3.5D + this.golem.getTask().getEntity().field_70130_N / 2.0F * (this.golem.getTask().getEntity().field_70130_N / 2.0F));
/* 47 */         if (Config.showGolemEmotes) this.golem.field_70170_p.func_72960_a(this.golem, (byte)5);
/* 48 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 52 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private boolean canEasilyReach(Entity e)
/*    */   {
/* 59 */     if (this.golem.func_70068_e(e) < this.minDist) { return true;
/*    */     }
/* 61 */     PathEntity pathentity = this.golem.func_70661_as().func_75494_a(e);
/*    */     
/* 63 */     if (pathentity == null)
/*    */     {
/* 65 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 69 */     PathPoint pathpoint = pathentity.func_75870_c();
/*    */     
/* 71 */     if (pathpoint == null)
/*    */     {
/* 73 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 77 */     int i = pathpoint.field_75839_a - MathHelper.func_76128_c(e.field_70165_t);
/* 78 */     int j = pathpoint.field_75838_c - MathHelper.func_76128_c(e.field_70161_v);
/* 79 */     return i * i + j * j < this.minDist;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/AIGotoEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */