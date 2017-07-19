/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityPrimalOrb extends EntityThrowable implements net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityPrimalOrb(World par1World)
/*     */   {
/*  26 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityPrimalOrb(World par1World, EntityLivingBase p, boolean seeker) {
/*  30 */     super(par1World, p);
/*  31 */     Vec3 v = p.func_70040_Z();
/*  32 */     func_70012_b(p.field_70165_t + v.field_72450_a / 2.0D, p.field_70163_u + p.func_70047_e() + v.field_72448_b / 2.0D, p.field_70161_v + v.field_72449_c / 2.0D, p.field_70177_z, p.field_70125_A);
/*  33 */     this.seeker = seeker;
/*  34 */     this.oi = p.func_145782_y();
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  39 */     data.writeBoolean(this.seeker);
/*  40 */     data.writeInt(this.oi);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  45 */     this.seeker = data.readBoolean();
/*  46 */     this.oi = data.readInt();
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  51 */     return 0.001F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d()
/*     */   {
/*  56 */     return 0.5F;
/*     */   }
/*     */   
/*  59 */   int count = 0;
/*  60 */   boolean seeker = false;
/*  61 */   int oi = 0;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  65 */     this.count += 1;
/*  66 */     if (func_70055_a(Material.field_151567_E)) {
/*  67 */       func_70184_a(new MovingObjectPosition(this));
/*     */     }
/*     */     
/*  70 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/*  72 */       for (int a = 0; a < 6; a++) {
/*  73 */         Thaumcraft.proxy.getFX().wispFX4((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this, a, true, 0.01F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */       Thaumcraft.proxy.getFX().wispFX2(this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, 0.1F, this.field_70146_Z.nextInt(6), true, true, 0.0F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  91 */     Random rr = new Random(func_145782_y() + this.count);
/*     */     
/*  93 */     if (this.field_70173_aa > 20) {
/*  94 */       if (!this.seeker) {
/*  95 */         this.field_70159_w += (rr.nextFloat() - rr.nextFloat()) * 0.01F;
/*  96 */         this.field_70181_x += (rr.nextFloat() - rr.nextFloat()) * 0.01F;
/*  97 */         this.field_70179_y += (rr.nextFloat() - rr.nextFloat()) * 0.01F;
/*     */       } else {
/*  99 */         java.util.List<Entity> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityLivingBase.class, 16.0D);
/* 100 */         double d = Double.MAX_VALUE;
/* 101 */         Entity t = null;
/* 102 */         for (Entity e : l) {
/* 103 */           if ((e.func_145782_y() != this.oi) && (!e.field_70128_L)) {
/* 104 */             double dd = func_70068_e(e);
/* 105 */             if (dd < d) {
/* 106 */               d = dd;
/* 107 */               t = e;
/*     */             }
/*     */           }
/*     */         }
/* 111 */         if (t != null) {
/* 112 */           double dx = t.field_70165_t - this.field_70165_t;
/* 113 */           double dy = t.func_174813_aQ().field_72338_b + t.field_70131_O * 0.9D - this.field_70163_u;
/* 114 */           double dz = t.field_70161_v - this.field_70161_v;
/* 115 */           double d13 = 0.2D;
/* 116 */           dx /= d;
/* 117 */           dy /= d;
/* 118 */           dz /= d;
/*     */           
/* 120 */           this.field_70159_w += dx * d13;
/* 121 */           this.field_70181_x += dy * d13;
/* 122 */           this.field_70179_y += dz * d13;
/*     */           
/* 124 */           this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.2F, 0.2F);
/* 125 */           this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.2F, 0.2F);
/* 126 */           this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.2F, 0.2F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 131 */     super.func_70071_h_();
/* 132 */     if (this.field_70173_aa > 5000) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/* 140 */     if (this.field_70170_p.field_72995_K) {
/* 141 */       for (int a = 0; a < 6; a++) {
/* 142 */         for (int b = 0; b < 6; b++) {
/* 143 */           float fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5F;
/*     */           
/* 145 */           float fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5F;
/*     */           
/* 147 */           float fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5F;
/*     */           
/* 149 */           Thaumcraft.proxy.getFX().wispFX3(this.field_70165_t + fx, this.field_70163_u + fy, this.field_70161_v + fz, this.field_70165_t + fx * 10.0F, this.field_70163_u + fy * 10.0F, this.field_70161_v + fz * 10.0F, 0.4F, b, true, 0.05F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 155 */     if (!this.field_70170_p.field_72995_K) {
/* 156 */       float specialchance = 1.0F;
/* 157 */       float expl = 2.0F;
/* 158 */       if ((mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) && 
/* 159 */         (func_70055_a(Material.field_151567_E))) {
/* 160 */         expl = 4.0F;
/* 161 */         specialchance = 10.0F;
/*     */       }
/*     */       
/* 164 */       this.field_70170_p.func_72876_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, expl, true);
/*     */       
/* 166 */       if ((!this.seeker) && (this.field_70146_Z.nextInt(100) <= specialchance)) {
/* 167 */         taintSplosion();
/*     */       }
/*     */       
/* 170 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void taintSplosion()
/*     */   {
/* 176 */     int x = (int)this.field_70165_t;
/* 177 */     int y = (int)this.field_70163_u;
/* 178 */     int z = (int)this.field_70161_v;
/* 179 */     for (int a = 0; a < 15; a++) {
/* 180 */       int xx = x + (int)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat() * 6.0F);
/* 181 */       int zz = z + (int)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat() * 6.0F);
/* 182 */       int yy = y + (int)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat() * 6.0F);
/* 183 */       BlockPos bp = new BlockPos(xx, yy, zz);
/*     */       
/* 185 */       if ((this.field_70170_p.func_175623_d(bp)) && (thaumcraft.common.lib.utils.BlockUtils.isAdjacentToSolidBlock(this.field_70170_p, bp))) {
/* 186 */         this.field_70170_p.func_180501_a(bp, BlocksTC.taintFibre.func_176223_P(), 3);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityPrimalOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */