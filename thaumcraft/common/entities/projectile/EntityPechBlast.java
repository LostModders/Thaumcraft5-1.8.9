/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockBamf;
/*     */ 
/*     */ public class EntityPechBlast extends EntityThrowable implements net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityPechBlast(World par1World)
/*     */   {
/*  25 */     super(par1World);
/*     */   }
/*     */   
/*  28 */   int strength = 0;
/*  29 */   int duration = 0;
/*  30 */   boolean nightshade = false;
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  34 */     data.writeBoolean(this.nightshade);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  39 */     this.nightshade = data.readBoolean();
/*     */   }
/*     */   
/*     */   public EntityPechBlast(World par1World, EntityLivingBase p, int strength, int duration, boolean nightshade)
/*     */   {
/*  44 */     super(par1World, p);
/*  45 */     Vec3 v = p.func_70040_Z();
/*  46 */     func_70012_b(p.field_70165_t + v.field_72450_a / 2.0D, p.field_70163_u + p.func_70047_e() + v.field_72448_b / 2.0D, p.field_70161_v + v.field_72449_c / 2.0D, p.field_70177_z, p.field_70125_A);
/*  47 */     this.strength = strength;
/*  48 */     this.nightshade = nightshade;
/*  49 */     this.duration = duration;
/*     */   }
/*     */   
/*     */   public EntityPechBlast(World par1World, double par2, double par4, double par6, int strength, int duration, boolean nightshade)
/*     */   {
/*  54 */     super(par1World, par2, par4, par6);
/*     */     
/*  56 */     this.strength = strength;
/*  57 */     this.nightshade = nightshade;
/*  58 */     this.duration = duration;
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  63 */     return 0.025F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d()
/*     */   {
/*  68 */     return 1.5F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  74 */     if (this.field_70170_p.field_72995_K) {
/*  75 */       Thaumcraft.proxy.getFX().pechsCurseTick(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */     }
/*     */     
/*  78 */     super.func_70071_h_();
/*     */     
/*  80 */     if (this.field_70173_aa > 500) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70106_y()
/*     */   {
/*  86 */     if (!this.field_70170_p.field_72995_K) {}
/*     */     
/*     */ 
/*  89 */     super.func_70106_y();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  97 */     if (!this.field_70170_p.field_72995_K) {
/*  98 */       if (this.nightshade) {
/*  99 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(mop.field_72307_f.field_72450_a, Math.max(mop.field_72307_f.field_72448_b, this.field_70163_u), mop.field_72307_f.field_72449_c, 5770890, true, true, false), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.func_177502_q(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */       }
/*     */       else
/*     */       {
/* 103 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(mop.field_72307_f.field_72450_a, Math.max(mop.field_72307_f.field_72448_b, this.field_70163_u), mop.field_72307_f.field_72449_c, 2815273, true, true, false), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.func_177502_q(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */       }
/*     */       
/*     */ 
/* 107 */       List list = this.field_70170_p.func_72839_b(func_85052_h(), func_174813_aQ().func_72314_b(2.0D, 2.0D, 2.0D));
/*     */       
/* 109 */       for (int i = 0; i < list.size(); i++) {
/* 110 */         Entity entity1 = (Entity)list.get(i);
/*     */         
/* 112 */         if ((!(entity1 instanceof EntityPech)) && ((entity1 instanceof EntityLivingBase)))
/*     */         {
/* 114 */           ((EntityLivingBase)entity1).func_70097_a(net.minecraft.util.DamageSource.func_76356_a(this, func_85052_h()), this.strength + 2);
/*     */           try {
/* 116 */             if (this.nightshade) {
/* 117 */               ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100 + this.duration * 40, this.strength));
/* 118 */               ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100 + this.duration * 40, this.strength + 1));
/* 119 */               ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100 + this.duration * 40, this.strength));
/*     */             } else {
/* 121 */               switch (this.field_70146_Z.nextInt(3)) {
/*     */               case 0: 
/* 123 */                 ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100 + this.duration * 40, this.strength));
/* 124 */                 break;
/*     */               case 1: 
/* 126 */                 ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100 + this.duration * 40, this.strength + 1));
/* 127 */                 break;
/*     */               case 2: 
/* 129 */                 ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100 + this.duration * 40, this.strength));
/*     */               }
/*     */               
/*     */             }
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/*     */       
/* 138 */       func_70106_y();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityPechBlast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */