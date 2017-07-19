/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.lib.UtilsFX.Vector;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class EntityHomingShard extends EntityThrowable implements net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityHomingShard(World par1World)
/*     */   {
/*  26 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityHomingShard(World par1World, EntityLivingBase p, EntityLivingBase t, int strength, boolean b) {
/*  30 */     super(par1World, p);
/*  31 */     this.target = t;
/*  32 */     this.tclass = t.getClass();
/*  33 */     this.persistant = b;
/*  34 */     setStrength(strength);
/*  35 */     Vec3 v = p.func_70040_Z();
/*  36 */     func_70012_b(p.field_70165_t + v.field_72450_a / 2.0D, p.field_70163_u + p.func_70047_e() + v.field_72448_b / 2.0D, p.field_70161_v + v.field_72449_c / 2.0D, p.field_70177_z, p.field_70125_A);
/*  37 */     float f = 0.5F;
/*  38 */     float ry = p.field_70177_z + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 60.0F;
/*  39 */     float rp = p.field_70125_A + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 60.0F;
/*  40 */     this.field_70159_w = (-MathHelper.func_76126_a(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F) * f);
/*  41 */     this.field_70179_y = (MathHelper.func_76134_b(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F) * f);
/*  42 */     this.field_70181_x = (-MathHelper.func_76126_a((rp + func_70183_g()) / 180.0F * 3.1415927F) * f);
/*     */   }
/*     */   
/*  45 */   Class tclass = null;
/*  46 */   boolean persistant = false;
/*     */   
/*  48 */   int targetID = 0;
/*     */   
/*     */   EntityLivingBase target;
/*     */   
/*     */   public void func_70088_a()
/*     */   {
/*  54 */     super.func_70088_a();
/*  55 */     this.field_70180_af.func_75682_a(17, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public void setStrength(int str)
/*     */   {
/*  60 */     this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)str));
/*     */   }
/*     */   
/*     */   public int getStrength()
/*     */   {
/*  65 */     return this.field_70180_af.func_75683_a(17);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  70 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  75 */     int id = -1;
/*  76 */     if (this.target != null) id = this.target.func_145782_y();
/*  77 */     data.writeInt(id);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  82 */     int id = data.readInt();
/*     */     try {
/*  84 */       if (id >= 0) { this.target = ((EntityLivingBase)this.field_70170_p.func_73045_a(id));
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  93 */     if ((!this.field_70170_p.field_72995_K) && 
/*  94 */       (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((func_85052_h() == null) || ((func_85052_h() != null) && (mop.field_72308_g != func_85052_h())))) {
/*  95 */       mop.field_72308_g.func_70097_a(DamageSource.func_76354_b(this, func_85052_h()), 1.0F + getStrength() * 0.5F);
/*  96 */       this.field_70170_p.func_72956_a(this, "thaumcraft:zap", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*  97 */       this.field_70170_p.func_72960_a(this, (byte)16);
/*  98 */       func_70106_y();
/*     */     }
/*     */     
/* 101 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/* 102 */       if (mop.field_178784_b.func_82599_e() != 0) this.field_70179_y *= -0.8D;
/* 103 */       if (mop.field_178784_b.func_82601_c() != 0) this.field_70159_w *= -0.8D;
/* 104 */       if (mop.field_178784_b.func_96559_d() != 0) { this.field_70181_x *= -0.8D;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 114 */     if (par1 == 16)
/*     */     {
/* 116 */       Thaumcraft.proxy.getFX().burst(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.3F);
/*     */     }
/*     */     else
/*     */     {
/* 120 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/* 124 */   public ArrayList<UtilsFX.Vector> vl = new ArrayList();
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 129 */     this.vl.add(0, new UtilsFX.Vector((float)this.field_70142_S, (float)this.field_70137_T, (float)this.field_70136_U));
/* 130 */     if (this.vl.size() > 6) { this.vl.remove(this.vl.size() - 1);
/*     */     }
/* 132 */     super.func_70071_h_();
/*     */     
/* 134 */     if (!this.field_70170_p.field_72995_K) {
/* 135 */       if ((this.persistant) && ((this.target == null) || (this.target.field_70128_L) || (this.target.func_70068_e(this) > 1250.0D))) {
/* 136 */         ArrayList<Entity> es = thaumcraft.common.lib.utils.EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, this.tclass, 16.0D);
/* 137 */         for (Entity e : es) {
/* 138 */           if (((e instanceof EntityLivingBase)) && (!e.field_70128_L) && ((func_85052_h() == null) || (e.func_145782_y() != func_85052_h().func_145782_y())))
/*     */           {
/* 140 */             this.target = ((EntityLivingBase)e);
/* 141 */             break;
/*     */           }
/*     */         }
/*     */       }
/* 145 */       if ((this.target == null) || (this.target.field_70128_L)) {
/* 146 */         this.field_70170_p.func_72960_a(this, (byte)16);
/* 147 */         func_70106_y();
/*     */       }
/*     */     }
/*     */     
/* 151 */     if (this.field_70173_aa > 300) {
/* 152 */       this.field_70170_p.func_72960_a(this, (byte)16);
/* 153 */       func_70106_y();
/*     */     }
/*     */     
/*     */ 
/* 157 */     if ((this.field_70173_aa % 20 == 0) && (this.target != null) && (!this.target.field_70128_L)) {
/* 158 */       double d = func_70032_d(this.target);
/* 159 */       double dx = this.target.field_70165_t - this.field_70165_t;
/* 160 */       double dy = this.target.func_174813_aQ().field_72338_b + this.target.field_70131_O * 0.6D - this.field_70163_u;
/* 161 */       double dz = this.target.field_70161_v - this.field_70161_v;
/* 162 */       dx /= d;
/* 163 */       dy /= d;
/* 164 */       dz /= d;
/*     */       
/* 166 */       this.field_70159_w = dx;
/* 167 */       this.field_70181_x = dy;
/* 168 */       this.field_70179_y = dz;
/*     */     }
/*     */     
/*     */ 
/* 172 */     this.field_70159_w *= 0.85D;
/* 173 */     this.field_70181_x *= 0.85D;
/* 174 */     this.field_70179_y *= 0.85D;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityHomingShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */