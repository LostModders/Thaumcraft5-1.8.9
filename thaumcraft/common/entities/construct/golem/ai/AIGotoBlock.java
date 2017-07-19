/*     */ package thaumcraft.common.entities.construct.golem.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.construct.golem.EntityThaumcraftGolem;
/*     */ 
/*     */ public class AIGotoBlock extends AIGoto
/*     */ {
/*     */   public AIGotoBlock(EntityThaumcraftGolem g)
/*     */   {
/*  20 */     super(g, (byte)0);
/*     */   }
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/*  25 */     super.func_75246_d();
/*  26 */     if (this.golem.func_70671_ap() != null) {
/*  27 */       this.golem.func_70671_ap().func_75650_a(this.golem.getTask().getPos().func_177958_n() + 0.5D, this.golem.getTask().getPos().func_177956_o() + 0.5D, this.golem.getTask().getPos().func_177952_p() + 0.5D, 10.0F, this.golem.func_70646_bf());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void moveTo()
/*     */   {
/*  34 */     if (this.targetBlock != null) {
/*  35 */       this.golem.func_70661_as().func_75492_a(this.targetBlock.func_177958_n() + 0.5D, this.targetBlock.func_177956_o() + 0.5D, this.targetBlock.func_177952_p() + 0.5D, this.golem.getGolemMoveSpeed());
/*     */     } else {
/*  37 */       this.golem.func_70661_as().func_75492_a(this.golem.getTask().getPos().func_177958_n() + 0.5D, this.golem.getTask().getPos().func_177956_o() + 0.5D, this.golem.getTask().getPos().func_177952_p() + 0.5D, this.golem.getGolemMoveSpeed());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean findDestination()
/*     */   {
/*  44 */     java.util.ArrayList<Task> list = thaumcraft.common.entities.construct.golem.tasks.TaskHandler.getBlockTasksSorted(this.golem.field_70170_p.field_73011_w.func_177502_q(), this.golem.func_110124_au(), this.golem);
/*     */     
/*  46 */     for (Task ticket : list) {
/*  47 */       if ((areGolemTagsValidForTask(ticket)) && (ticket.canGolemPerformTask(this.golem)) && (this.golem.func_180485_d(ticket.getPos())) && (isValidDestination(this.golem.field_70170_p, ticket.getPos())) && (canEasilyReach(ticket.getPos())))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*  52 */         this.targetBlock = getAdjacentSpace(ticket.getPos());
/*  53 */         this.golem.setTask(ticket);
/*  54 */         this.golem.getTask().setReserved(true);
/*  55 */         if (Config.showGolemEmotes) { this.golem.field_70170_p.func_72960_a(this.golem, (byte)5);
/*     */         }
/*  57 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   private BlockPos getAdjacentSpace(BlockPos pos) {
/*  65 */     double d = Double.MAX_VALUE;
/*  66 */     BlockPos closest = null;
/*  67 */     for (EnumFacing face : EnumFacing.field_176754_o) {
/*  68 */       Block block = this.golem.field_70170_p.func_180495_p(pos.func_177972_a(face)).func_177230_c();
/*  69 */       if (!block.func_149688_o().func_76230_c()) {
/*  70 */         double dist = pos.func_177972_a(face).func_177957_d(this.golem.field_70165_t, this.golem.field_70163_u, this.golem.field_70161_v);
/*  71 */         if (dist < d) {
/*  72 */           closest = pos.func_177972_a(face);
/*  73 */           d = dist;
/*     */         }
/*     */       }
/*     */     }
/*  77 */     return closest;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean canEasilyReach(BlockPos pos)
/*     */   {
/*  83 */     if (this.golem.func_174831_c(pos) < this.minDist) { return true;
/*     */     }
/*  85 */     PathEntity pathentity = this.golem.func_70661_as().func_75488_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D);
/*     */     
/*  87 */     if (pathentity == null)
/*     */     {
/*  89 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  93 */     PathPoint pathpoint = pathentity.func_75870_c();
/*     */     
/*  95 */     if (pathpoint == null)
/*     */     {
/*  97 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 101 */     int i = pathpoint.field_75839_a - MathHelper.func_76128_c(pos.func_177958_n());
/* 102 */     int j = pathpoint.field_75838_c - MathHelper.func_76128_c(pos.func_177952_p());
/* 103 */     int k = pathpoint.field_75837_b - MathHelper.func_76128_c(pos.func_177956_o());
/* 104 */     return i * i + j * j + k * k < 2.25D;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/ai/AIGotoBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */