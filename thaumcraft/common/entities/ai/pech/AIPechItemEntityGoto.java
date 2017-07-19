/*     */ package thaumcraft.common.entities.ai.pech;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ 
/*     */ public class AIPechItemEntityGoto extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityPech pech;
/*     */   private Entity targetEntity;
/*  19 */   float maxTargetDistance = 16.0F;
/*     */   private int count;
/*     */   private int failedPathFindingPenalty;
/*     */   
/*  23 */   public AIPechItemEntityGoto(EntityPech par1EntityCreature) { this.pech = par1EntityCreature;
/*  24 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  32 */     if (--this.count > 0) { return false;
/*     */     }
/*  34 */     double range = Double.MAX_VALUE;
/*     */     
/*     */ 
/*  37 */     java.util.List<Entity> targets = this.pech.field_70170_p.func_72839_b(this.pech, this.pech.func_174813_aQ().func_72314_b(this.maxTargetDistance, this.maxTargetDistance, this.maxTargetDistance));
/*     */     
/*     */ 
/*  40 */     if (targets.size() == 0) return false;
/*  41 */     for (Entity e : targets) {
/*  42 */       if (((e instanceof EntityItem)) && (this.pech.canPickup(((EntityItem)e).func_92059_d())))
/*     */       {
/*  44 */         net.minecraft.nbt.NBTTagCompound itemData = ((EntityItem)e).getEntityData();
/*  45 */         String username = ((EntityItem)e).func_145800_j();
/*  46 */         if ((username == null) || (!username.equals("PechDrop")))
/*     */         {
/*  48 */           double distance = e.func_70092_e(this.pech.field_70165_t, this.pech.field_70163_u, this.pech.field_70161_v);
/*  49 */           if ((distance < range) && (distance <= this.maxTargetDistance * this.maxTargetDistance)) {
/*  50 */             range = distance;
/*  51 */             this.targetEntity = e;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  56 */     if (this.targetEntity == null)
/*     */     {
/*  58 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  63 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  73 */     return this.targetEntity != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  83 */     this.targetEntity = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  94 */     this.pech.func_70661_as().func_75484_a(this.pech.func_70661_as().func_75494_a(this.targetEntity), this.pech.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 1.5D);
/*     */     
/*  96 */     this.count = 0;
/*     */   }
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 101 */     this.pech.func_70671_ap().func_75651_a(this.targetEntity, 30.0F, 30.0F);
/*     */     
/* 103 */     if ((this.pech.func_70635_at().func_75522_a(this.targetEntity)) && (--this.count <= 0))
/*     */     {
/* 105 */       this.count = (this.failedPathFindingPenalty + 4 + this.pech.func_70681_au().nextInt(4));
/* 106 */       this.pech.func_70661_as().func_75497_a(this.targetEntity, this.pech.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 1.5D);
/* 107 */       if (this.pech.func_70661_as().func_75505_d() != null)
/*     */       {
/* 109 */         PathPoint finalPathPoint = this.pech.func_70661_as().func_75505_d().func_75870_c();
/* 110 */         if ((finalPathPoint != null) && (this.targetEntity.func_70092_e(finalPathPoint.field_75839_a, finalPathPoint.field_75837_b, finalPathPoint.field_75838_c) < 1.0D))
/*     */         {
/* 112 */           this.failedPathFindingPenalty = 0;
/*     */         }
/*     */         else
/*     */         {
/* 116 */           this.failedPathFindingPenalty += 10;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 121 */         this.failedPathFindingPenalty += 10;
/*     */       }
/*     */     }
/* 124 */     double distance = this.pech.func_70092_e(this.targetEntity.field_70165_t, this.targetEntity.func_174813_aQ().field_72338_b, this.targetEntity.field_70161_v);
/*     */     
/*     */ 
/*     */ 
/* 128 */     if (distance <= 1.5D)
/*     */     {
/* 130 */       this.count = 0;
/*     */       
/* 132 */       int am = ((EntityItem)this.targetEntity).func_92059_d().field_77994_a;
/* 133 */       ItemStack is = this.pech.pickupItem(((EntityItem)this.targetEntity).func_92059_d());
/* 134 */       if ((is != null) && (is.field_77994_a > 0)) {
/* 135 */         ((EntityItem)this.targetEntity).func_92058_a(is);
/*     */       } else {
/* 137 */         this.targetEntity.func_70106_y();
/*     */       }
/* 139 */       if ((is == null) || (is.field_77994_a != am)) {
/* 140 */         this.targetEntity.field_70170_p.func_72956_a(this.targetEntity, "random.pop", 0.2F, ((this.targetEntity.field_70170_p.field_73012_v.nextFloat() - this.targetEntity.field_70170_p.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/ai/pech/AIPechItemEntityGoto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */