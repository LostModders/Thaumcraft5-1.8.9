/*     */ package thaumcraft.common.entities.construct.golem.tasks;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.entities.construct.golem.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.entities.construct.golem.seals.SealHandler;
/*     */ 
/*     */ public class TaskHandler
/*     */ {
/*     */   static final int TASK_LIMIT = 1000;
/*  21 */   public static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Task>> tasks = new ConcurrentHashMap();
/*     */   
/*     */   public static void addTask(int dim, Task ticket) {
/*  24 */     if (!tasks.containsKey(Integer.valueOf(dim))) tasks.put(Integer.valueOf(dim), new ConcurrentHashMap());
/*  25 */     ConcurrentHashMap<Integer, Task> dc = (ConcurrentHashMap)tasks.get(Integer.valueOf(dim));
/*  26 */     if (dc.size() > 1000) {
/*     */       try {
/*  28 */         Iterator<Task> i = dc.values().iterator();
/*  29 */         if (i.hasNext()) {
/*  30 */           i.next();
/*  31 */           i.remove();
/*     */         }
/*     */       } catch (Exception e) {}
/*     */     }
/*  35 */     dc.put(Integer.valueOf(ticket.getId()), ticket);
/*     */   }
/*     */   
/*     */   public static Task getTask(int dim, int id) {
/*  39 */     return (Task)getTasks(dim).get(Integer.valueOf(id));
/*     */   }
/*     */   
/*     */   public static ConcurrentHashMap<Integer, Task> getTasks(int dim) {
/*  43 */     if (!tasks.containsKey(Integer.valueOf(dim))) {
/*  44 */       tasks.put(Integer.valueOf(dim), new ConcurrentHashMap());
/*     */     }
/*  46 */     return (ConcurrentHashMap)tasks.get(Integer.valueOf(dim));
/*     */   }
/*     */   
/*     */   public static ArrayList<Task> getBlockTasksSorted(int dim, UUID uuid, Entity golem) {
/*  50 */     ConcurrentHashMap<Integer, Task> tickets = getTasks(dim);
/*  51 */     ArrayList<Task> out = new ArrayList();
/*     */     
/*  53 */     for (Task ticket : tickets.values()) {
/*  54 */       if ((!ticket.isReserved()) && (ticket.getType() == 0) && (
/*  55 */         (uuid == null) || (ticket.getGolemUUID() == null) || (uuid.equals(ticket.getGolemUUID()))))
/*  56 */         if (out.size() == 0) {
/*  57 */           out.add(ticket);
/*     */         } else {
/*  59 */           double d = ticket.getPos().func_177957_d(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v);
/*  60 */           d -= ticket.getPriority() * 256;
/*  61 */           for (int a = 0;; a++) { if (a >= out.size()) break label241;
/*  62 */             double d1 = ((Task)out.get(a)).getPos().func_177957_d(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v);
/*  63 */             d1 -= ((Task)out.get(a)).getPriority() * 256;
/*  64 */             if (d < d1) {
/*  65 */               out.add(a, ticket);
/*  66 */               break;
/*     */             }
/*     */           }
/*  69 */           out.add(ticket);
/*     */         }
/*     */     }
/*     */     label241:
/*  73 */     return out;
/*     */   }
/*     */   
/*     */   public static ArrayList<Task> getEntityTasksSorted(int dim, UUID uuid, Entity golem)
/*     */   {
/*  78 */     ConcurrentHashMap<Integer, Task> tickets = getTasks(dim);
/*  79 */     ArrayList<Task> out = new ArrayList();
/*     */     
/*     */ 
/*  82 */     for (Task ticket : tickets.values()) {
/*  83 */       if ((!ticket.isReserved()) && (ticket.getType() == 1) && (
/*  84 */         (uuid == null) || (ticket.getGolemUUID() == null) || (uuid.equals(ticket.getGolemUUID()))))
/*  85 */         if (out.size() == 0) {
/*  86 */           out.add(ticket);
/*     */         } else {
/*  88 */           double d = ticket.getPos().func_177957_d(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v);
/*  89 */           d -= ticket.getPriority() * 256;
/*  90 */           for (int a = 0;; a++) { if (a >= out.size()) break label242;
/*  91 */             double d1 = ((Task)out.get(a)).getPos().func_177957_d(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v);
/*  92 */             d1 -= ((Task)out.get(a)).getPriority() * 256;
/*  93 */             if (d < d1) {
/*  94 */               out.add(a, ticket);
/*  95 */               break;
/*     */             }
/*     */           }
/*  98 */           out.add(ticket);
/*     */         }
/*     */     }
/*     */     label242:
/* 102 */     return out;
/*     */   }
/*     */   
/*     */   public static void completeTask(Task task, EntityThaumcraftGolem golem) {
/* 106 */     if ((task.isCompleted()) || (task.isSuspended())) return;
/* 107 */     ISealEntity se = SealHandler.getSealEntity(golem.field_70170_p.field_73011_w.func_177502_q(), task.getSealPos());
/* 108 */     if (se != null) {
/* 109 */       task.setCompletion(se.getSeal().onTaskCompletion(golem.field_70170_p, golem, task));
/*     */     } else
/* 111 */       task.setCompletion(true);
/*     */   }
/*     */   
/*     */   public static void clearSuspendedOrExpiredTasks(World world) {
/* 115 */     ConcurrentHashMap<Integer, Task> tickets = getTasks(world.field_73011_w.func_177502_q());
/* 116 */     ConcurrentHashMap<Integer, Task> temp = new ConcurrentHashMap();
/* 117 */     for (Task ticket : tickets.values()) {
/* 118 */       if ((!ticket.isSuspended()) && (ticket.getLifespan() > 0L)) {
/* 119 */         ticket.setLifespan((short)(int)(ticket.getLifespan() - 1L));
/* 120 */         temp.put(Integer.valueOf(ticket.getId()), ticket);
/*     */       } else {
/* 122 */         ISealEntity sEnt = SealHandler.getSealEntity(world.field_73011_w.func_177502_q(), ticket.getSealPos());
/* 123 */         if (sEnt != null)
/* 124 */           sEnt.getSeal().onTaskSuspension(world, ticket);
/*     */       }
/*     */     }
/* 127 */     tasks.put(Integer.valueOf(world.field_73011_w.func_177502_q()), temp);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/tasks/TaskHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */