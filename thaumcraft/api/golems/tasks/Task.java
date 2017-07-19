/*     */ package thaumcraft.api.golems.tasks;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ 
/*     */ public class Task
/*     */ {
/*     */   private UUID golemUUID;
/*     */   private int id;
/*     */   private byte type;
/*     */   private SealPos sealPos;
/*     */   private BlockPos pos;
/*     */   private Entity entity;
/*     */   private boolean reserved;
/*     */   private boolean suspended;
/*     */   private boolean completed;
/*     */   private int data;
/*     */   private short lifespan;
/*  28 */   private byte priority = 0;
/*     */   
/*     */   private Task() {}
/*     */   
/*     */   public Task(SealPos sealPos, BlockPos pos) {
/*  33 */     this.sealPos = sealPos;
/*  34 */     this.pos = pos;
/*  35 */     if (sealPos == null) {
/*  36 */       this.id = (System.currentTimeMillis() + "/BNPOS/" + pos.toString()).hashCode();
/*     */     } else
/*  38 */       this.id = (System.currentTimeMillis() + "/B/" + sealPos.face.toString() + "/" + sealPos.pos.toString() + "/" + pos.toString()).hashCode();
/*  39 */     this.type = 0;
/*  40 */     this.lifespan = 300;
/*     */   }
/*     */   
/*     */   public Task(SealPos sealPos, Entity entity) {
/*  44 */     this.sealPos = sealPos;
/*  45 */     this.entity = entity;
/*  46 */     if (sealPos == null) {
/*  47 */       this.id = (System.currentTimeMillis() + "/ENPOS/" + this.pos.toString()).hashCode();
/*     */     } else
/*  49 */       this.id = (System.currentTimeMillis() + "/E/" + sealPos.face.toString() + "/" + sealPos.pos.toString() + "/" + entity.func_145782_y()).hashCode();
/*  50 */     this.type = 1;
/*  51 */     this.lifespan = 300;
/*     */   }
/*     */   
/*     */   public byte getPriority() {
/*  55 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setPriority(byte priority) {
/*  59 */     this.priority = priority;
/*     */   }
/*     */   
/*     */   public boolean isCompleted() {
/*  63 */     return this.completed;
/*     */   }
/*     */   
/*     */   public void setCompletion(boolean fulfilled) {
/*  67 */     this.completed = fulfilled;
/*  68 */     this.lifespan = ((short)(this.lifespan + 1));
/*     */   }
/*     */   
/*     */   public UUID getGolemUUID() {
/*  72 */     return this.golemUUID;
/*     */   }
/*     */   
/*     */   public void setGolemUUID(UUID golemUUID) {
/*  76 */     this.golemUUID = golemUUID;
/*     */   }
/*     */   
/*     */   public BlockPos getPos() {
/*  80 */     return this.type == 1 ? this.entity.func_180425_c() : this.pos;
/*     */   }
/*     */   
/*     */   public byte getType() {
/*  84 */     return this.type;
/*     */   }
/*     */   
/*     */   public Entity getEntity() {
/*  88 */     return this.entity;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  92 */     return this.id;
/*     */   }
/*     */   
/*     */   public boolean isReserved() {
/*  96 */     return this.reserved;
/*     */   }
/*     */   
/*     */   public void setReserved(boolean res) {
/* 100 */     this.reserved = res;
/* 101 */     this.lifespan = ((short)(this.lifespan + 120));
/*     */   }
/*     */   
/*     */   public boolean isSuspended() {
/* 105 */     return this.suspended;
/*     */   }
/*     */   
/*     */   public void setSuspended(boolean suspended) {
/* 109 */     this.suspended = suspended;
/*     */   }
/*     */   
/*     */   public SealPos getSealPos() {
/* 113 */     return this.sealPos;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o)
/*     */   {
/* 118 */     if (!(o instanceof Task))
/*     */     {
/* 120 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 124 */     Task t = (Task)o;
/* 125 */     return t.id == this.id;
/*     */   }
/*     */   
/*     */   public long getLifespan()
/*     */   {
/* 130 */     return this.lifespan;
/*     */   }
/*     */   
/*     */   public void setLifespan(short ls) {
/* 134 */     this.lifespan = ls;
/*     */   }
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem) {
/* 138 */     ISealEntity se = GolemHelper.getSealEntity(golem.getGolemWorld().field_73011_w.func_177502_q(), this.sealPos);
/* 139 */     if (se != null) {
/* 140 */       if ((golem.getGolemColor() > 0) && (se.getColor() > 0) && (golem.getGolemColor() != se.getColor())) return false;
/* 141 */       return se.getSeal().canGolemPerformTask(golem, this);
/*     */     }
/* 143 */     return true;
/*     */   }
/*     */   
/*     */   public int getData()
/*     */   {
/* 148 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(int data) {
/* 152 */     this.data = data;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/golems/tasks/Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */