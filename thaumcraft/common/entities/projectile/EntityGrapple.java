/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import thaumcraft.common.items.armor.Hover;
/*     */ 
/*     */ public class EntityGrapple
/*     */   extends EntityThrowable implements IEntityAdditionalSpawnData
/*     */ {
/*     */   private int potency;
/*  20 */   public boolean sticky = false;
/*     */   EntityLivingBase cthrower;
/*     */   
/*     */   public EntityGrapple(World par1World) {
/*  24 */     super(par1World);
/*  25 */     func_70105_a(0.1F, 0.1F);
/*     */   }
/*     */   
/*     */   public void func_70186_c(double x, double y, double z, float velocity, float inaccuracy)
/*     */   {
/*  30 */     super.func_70186_c(x, y, z, velocity, 0.0F);
/*     */   }
/*     */   
/*     */   public EntityGrapple(World par1World, EntityLivingBase par2EntityLiving, int potency, boolean sticky)
/*     */   {
/*  35 */     super(par1World, par2EntityLiving);
/*  36 */     this.potency = potency;
/*  37 */     this.sticky = sticky;
/*  38 */     func_70105_a(0.1F, 0.1F);
/*     */   }
/*     */   
/*     */   public EntityGrapple(World par1World, double par2, double par4, double par6)
/*     */   {
/*  43 */     super(par1World, par2, par4, par6);
/*  44 */     func_70105_a(0.1F, 0.1F);
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  49 */     int id = -1;
/*  50 */     if (func_85052_h() != null) id = func_85052_h().func_145782_y();
/*  51 */     data.writeInt(id);
/*  52 */     data.writeByte(this.potency);
/*  53 */     data.writeBoolean(this.sticky);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  58 */     int id = data.readInt();
/*  59 */     this.potency = data.readByte();
/*  60 */     this.sticky = data.readBoolean();
/*     */     try {
/*  62 */       if (id >= 0) this.cthrower = ((EntityLivingBase)this.field_70170_p.func_73045_a(id));
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public EntityLivingBase func_85052_h()
/*     */   {
/*  69 */     if (this.cthrower != null) return this.cthrower;
/*  70 */     return super.func_85052_h();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70182_d()
/*     */   {
/*  80 */     return 3.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70185_h()
/*     */   {
/*  86 */     return getPulling() ? 0.0F : 0.03F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70088_a()
/*     */   {
/*  92 */     super.func_70088_a();
/*  93 */     this.field_70180_af.func_75682_a(17, new Byte((byte)0));
/*     */   }
/*     */   
/*     */ 
/*  97 */   boolean p = false;
/*     */   boolean boost;
/*     */   
/*     */   public void setPulling() {
/* 101 */     this.p = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getPulling()
/*     */   {
/* 107 */     return this.p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 112 */   int prevDist = 0;
/* 113 */   int count = 0;
/* 114 */   boolean added = false;
/*     */   
/* 116 */   public float ampl = 0.0F;
/*     */   
/* 118 */   private static HashMap<Integer, Integer> grapples = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 124 */     super.func_70071_h_();
/*     */     
/* 126 */     if ((!getPulling()) && (!this.field_70128_L) && ((this.field_70173_aa > 10 + this.potency * 5) || (func_85052_h() == null))) {
/* 127 */       func_70106_y();
/*     */     }
/*     */     
/* 130 */     if (func_85052_h() != null)
/*     */     {
/* 132 */       if ((!this.field_70170_p.field_72995_K) && (!this.field_70128_L) && (!this.added)) {
/* 133 */         if (grapples.containsKey(Integer.valueOf(func_85052_h().func_145782_y()))) {
/* 134 */           int ii = ((Integer)grapples.get(Integer.valueOf(func_85052_h().func_145782_y()))).intValue();
/* 135 */           if (ii != func_145782_y()) {
/* 136 */             Entity e = this.field_70170_p.func_73045_a(ii);
/* 137 */             if (e != null) e.func_70106_y();
/*     */           }
/*     */         }
/* 140 */         grapples.put(Integer.valueOf(func_85052_h().func_145782_y()), Integer.valueOf(func_145782_y()));
/* 141 */         this.added = true;
/*     */       }
/*     */       
/* 144 */       double dis = func_85052_h().func_70032_d(this);
/* 145 */       if ((getPulling()) && (!this.field_70128_L)) {
/* 146 */         if (((!this.sticky) && (dis < 2.0D)) || (func_85052_h().func_70093_af()) || ((!this.sticky) && (this.count > 20))) {
/* 147 */           func_70106_y();
/*     */         }
/*     */         else {
/* 150 */           if ((!this.field_70170_p.field_72995_K) && ((func_85052_h() instanceof EntityPlayerMP))) {
/* 151 */             Hover.resetFloatCounter((EntityPlayerMP)func_85052_h());
/*     */           }
/* 153 */           func_85052_h().field_70143_R = 0.0F;
/* 154 */           double mx = this.field_70165_t - func_85052_h().field_70165_t;
/* 155 */           double my = this.field_70163_u - func_85052_h().field_70163_u;
/* 156 */           double mz = this.field_70161_v - func_85052_h().field_70161_v;
/* 157 */           double dd = dis;
/* 158 */           if ((this.sticky) && (dis < 8.0D))
/* 159 */             dd = dis * (8.0D - dis);
/* 160 */           mx /= dd * 5.0D;
/* 161 */           my /= dd * 5.0D;
/* 162 */           mz /= dd * 5.0D;
/* 163 */           Vec3 v2 = new Vec3(mx, my, mz);
/* 164 */           if (v2.func_72433_c() > 0.25D) {
/* 165 */             v2 = v2.func_72432_b();
/* 166 */             mx = v2.field_72450_a / 4.0D;
/* 167 */             my = v2.field_72448_b / 4.0D;
/* 168 */             mz = v2.field_72449_c / 4.0D;
/*     */           }
/* 170 */           func_85052_h().field_70159_w += mx;
/* 171 */           func_85052_h().field_70181_x += my + 0.033D;
/* 172 */           func_85052_h().field_70179_y += mz;
/* 173 */           if (!this.boost) {
/* 174 */             func_85052_h().field_70181_x += 0.4000000059604645D;
/* 175 */             this.boost = true;
/*     */           }
/* 177 */           int d = (int)(dis / 2.0D);
/* 178 */           if (d == this.prevDist) {
/* 179 */             this.count += 1;
/*     */           } else {
/* 181 */             this.count = 0;
/*     */           }
/* 183 */           this.prevDist = d;
/*     */         }
/*     */       }
/*     */       
/* 187 */       if (this.field_70170_p.field_72995_K) {
/* 188 */         if (!getPulling()) {
/* 189 */           this.ampl += 0.02F;
/*     */         } else {
/* 191 */           this.ampl *= 0.66F;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/* 201 */     setPulling();
/* 202 */     this.field_70159_w = 0.0D;
/* 203 */     this.field_70181_x = 0.0D;
/* 204 */     this.field_70179_y = 0.0D;
/* 205 */     this.field_70165_t = mop.field_72307_f.field_72450_a;
/* 206 */     this.field_70163_u = mop.field_72307_f.field_72448_b;
/* 207 */     this.field_70161_v = mop.field_72307_f.field_72449_c;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityGrapple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */