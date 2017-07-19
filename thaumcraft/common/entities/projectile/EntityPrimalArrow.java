/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IProjectile;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import thaumcraft.api.damagesource.DamageSourceIndirectThaumcraftEntity;
/*     */ 
/*     */ public class EntityPrimalArrow extends EntityArrow implements IProjectile, IEntityAdditionalSpawnData
/*     */ {
/*  37 */   protected int field_145791_d = -1;
/*  38 */   protected int field_145792_e = -1;
/*  39 */   protected int field_145789_f = -1;
/*  40 */   protected Block field_145790_g = net.minecraft.init.Blocks.field_150350_a;
/*  41 */   protected int field_70253_h = 0;
/*  42 */   protected boolean field_70254_i = false;
/*     */   
/*     */   public int field_70252_j;
/*     */   
/*  46 */   protected int field_70257_an = 0;
/*  47 */   protected double field_70255_ao = 2.1D;
/*     */   
/*     */   public int shootingEntityId;
/*     */   
/*     */   protected int field_70256_ap;
/*  52 */   public int type = 0;
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  56 */     data.writeDouble(this.field_70159_w);
/*  57 */     data.writeDouble(this.field_70181_x);
/*  58 */     data.writeDouble(this.field_70179_y);
/*  59 */     data.writeFloat(this.field_70177_z);
/*  60 */     data.writeFloat(this.field_70125_A);
/*  61 */     data.writeByte(this.type);
/*  62 */     data.writeInt(this.shootingEntityId);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  67 */     this.field_70159_w = data.readDouble();
/*  68 */     this.field_70181_x = data.readDouble();
/*  69 */     this.field_70179_y = data.readDouble();
/*  70 */     this.field_70177_z = data.readFloat();
/*  71 */     this.field_70125_A = data.readFloat();
/*  72 */     this.field_70126_B = this.field_70177_z;
/*  73 */     this.field_70127_C = this.field_70125_A;
/*  74 */     this.type = data.readByte();
/*  75 */     this.shootingEntityId = data.readInt();
/*     */   }
/*     */   
/*     */   public EntityPrimalArrow(World par1World)
/*     */   {
/*  80 */     super(par1World);
/*  81 */     this.field_70155_l = 10.0D;
/*  82 */     func_70105_a(0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   public EntityPrimalArrow(World par1World, double par2, double par4, double par6)
/*     */   {
/*  87 */     super(par1World);
/*  88 */     this.field_70155_l = 10.0D;
/*  89 */     func_70105_a(0.25F, 0.25F);
/*  90 */     func_70107_b(par2, par4, par6);
/*     */   }
/*     */   
/*     */   public EntityPrimalArrow(World par1World, EntityLivingBase par2EntityLivingBase, float par3, int type)
/*     */   {
/*  95 */     super(par1World);
/*  96 */     this.field_70155_l = 10.0D;
/*  97 */     this.field_70250_c = par2EntityLivingBase;
/*  98 */     this.type = type;
/*  99 */     this.field_70251_a = 0;
/* 100 */     this.shootingEntityId = this.field_70250_c.func_145782_y();
/* 101 */     func_70105_a(0.5F, 0.5F);
/* 102 */     func_70012_b(par2EntityLivingBase.field_70165_t, par2EntityLivingBase.field_70163_u + par2EntityLivingBase.func_70047_e(), par2EntityLivingBase.field_70161_v, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
/* 103 */     this.field_70165_t -= MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/* 104 */     this.field_70163_u -= 0.10000000014901161D;
/* 105 */     this.field_70161_v -= MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/* 106 */     Vec3 vec3d = par2EntityLivingBase.func_70676_i(1.0F);
/* 107 */     this.field_70165_t += vec3d.field_72450_a;
/* 108 */     this.field_70163_u += vec3d.field_72448_b;
/* 109 */     this.field_70161_v += vec3d.field_72449_c;
/*     */     
/* 111 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 112 */     this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/* 113 */     this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/* 114 */     this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F));
/* 115 */     func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, par3 * 1.5F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70100_b_(EntityPlayer par1EntityPlayer) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 128 */     func_70030_z();
/*     */     
/* 130 */     if ((this.field_70127_C == 0.0F) && (this.field_70126_B == 0.0F))
/*     */     {
/* 132 */       float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 133 */       this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/* 134 */       this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / 3.141592653589793D));
/*     */     }
/*     */     
/* 137 */     BlockPos blockpos = new BlockPos(this.field_145791_d, this.field_145792_e, this.field_145789_f);
/* 138 */     IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
/* 139 */     Block block = iblockstate.func_177230_c();
/*     */     
/* 141 */     if (block.func_149688_o() != Material.field_151579_a)
/*     */     {
/* 143 */       block.func_180654_a(this.field_70170_p, blockpos);
/* 144 */       AxisAlignedBB axisalignedbb = block.func_180640_a(this.field_70170_p, blockpos, iblockstate);
/*     */       
/* 146 */       if ((axisalignedbb != null) && (axisalignedbb.func_72318_a(new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v))))
/*     */       {
/* 148 */         this.field_70254_i = true;
/*     */       }
/*     */     }
/*     */     
/* 152 */     if (this.field_70249_b > 0)
/*     */     {
/* 154 */       this.field_70249_b -= 1;
/*     */     }
/*     */     
/* 157 */     if (this.field_70254_i)
/*     */     {
/* 159 */       int j = block.func_176201_c(iblockstate);
/*     */       
/* 161 */       if ((block == this.field_145790_g) && (j == this.field_70253_h))
/*     */       {
/* 163 */         this.field_70252_j += 1;
/*     */         
/* 165 */         if (this.field_70252_j >= (this.type < 8 ? 100 : 20))
/*     */         {
/* 167 */           func_70106_y();
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 172 */         this.field_70254_i = false;
/* 173 */         this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2F;
/* 174 */         this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2F;
/* 175 */         this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2F;
/* 176 */         this.field_70252_j = 0;
/* 177 */         this.field_70257_an = 0;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 182 */       this.field_70257_an += 1;
/* 183 */       Vec3 vec31 = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 184 */       Vec3 vec3 = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 185 */       MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec31, vec3, false, true, false);
/* 186 */       vec31 = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 187 */       vec3 = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/* 189 */       if (movingobjectposition != null)
/*     */       {
/* 191 */         vec3 = new Vec3(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */       }
/*     */       
/* 194 */       Entity entity = null;
/* 195 */       List list = this.field_70170_p.func_72839_b(this, func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/* 196 */       double d0 = 0.0D;
/*     */       
/*     */ 
/*     */ 
/* 200 */       for (int i = 0; i < list.size(); i++)
/*     */       {
/* 202 */         Entity entity1 = (Entity)list.get(i);
/*     */         
/* 204 */         if ((entity1.func_70067_L()) && ((entity1 != this.field_70250_c) || (this.field_70257_an >= 5)))
/*     */         {
/* 206 */           float f1 = 0.3F;
/* 207 */           AxisAlignedBB axisalignedbb1 = entity1.func_174813_aQ().func_72314_b(f1, f1, f1);
/* 208 */           MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec31, vec3);
/*     */           
/* 210 */           if (movingobjectposition1 != null)
/*     */           {
/* 212 */             double d1 = vec31.func_72438_d(movingobjectposition1.field_72307_f);
/*     */             
/* 214 */             if ((d1 < d0) || (d0 == 0.0D))
/*     */             {
/* 216 */               entity = entity1;
/* 217 */               d0 = d1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 223 */       if (entity != null)
/*     */       {
/* 225 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */       
/* 228 */       if ((movingobjectposition != null) && (movingobjectposition.field_72308_g != null) && ((movingobjectposition.field_72308_g instanceof EntityPlayer)))
/*     */       {
/* 230 */         EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
/*     */         
/* 232 */         if ((entityplayer.field_71075_bZ.field_75102_a) || (((this.field_70250_c instanceof EntityPlayer)) && (!((EntityPlayer)this.field_70250_c).func_96122_a(entityplayer))))
/*     */         {
/* 234 */           movingobjectposition = null;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 241 */       if (movingobjectposition != null)
/*     */       {
/* 243 */         if (movingobjectposition.field_72308_g != null)
/*     */         {
/*     */ 
/*     */ 
/* 247 */           if (inflictDamage(movingobjectposition))
/*     */           {
/* 249 */             if ((movingobjectposition.field_72308_g instanceof EntityLivingBase))
/*     */             {
/* 251 */               EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
/*     */               
/* 253 */               if (this.field_70256_ap > 0)
/*     */               {
/* 255 */                 float f3 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */                 
/* 257 */                 if (f3 > 0.0F)
/*     */                 {
/* 259 */                   movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * this.field_70256_ap * 0.6000000238418579D / f3, 0.1D, this.field_70179_y * this.field_70256_ap * 0.6000000238418579D / f3);
/*     */                 }
/*     */               }
/*     */               
/* 263 */               if ((this.field_70250_c != null) && ((this.field_70250_c instanceof EntityLivingBase)))
/*     */               {
/* 265 */                 EnchantmentHelper.func_151384_a(entitylivingbase, this.field_70250_c);
/* 266 */                 EnchantmentHelper.func_151385_b((EntityLivingBase)this.field_70250_c, entitylivingbase);
/*     */               }
/*     */               
/* 269 */               if ((this.field_70250_c != null) && (movingobjectposition.field_72308_g != this.field_70250_c) && ((movingobjectposition.field_72308_g instanceof EntityPlayer)) && ((this.field_70250_c instanceof EntityPlayerMP)))
/*     */               {
/* 271 */                 ((EntityPlayerMP)this.field_70250_c).field_71135_a.func_147359_a(new S2BPacketChangeGameState(6, 0.0F));
/*     */               }
/*     */             }
/*     */             
/* 275 */             func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/*     */             
/* 277 */             if (!(movingobjectposition.field_72308_g instanceof EntityEnderman))
/*     */             {
/* 279 */               func_70106_y();
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 284 */             this.field_70159_w *= -0.10000000149011612D;
/* 285 */             this.field_70181_x *= -0.10000000149011612D;
/* 286 */             this.field_70179_y *= -0.10000000149011612D;
/* 287 */             this.field_70177_z += 180.0F;
/* 288 */             this.field_70126_B += 180.0F;
/* 289 */             this.field_70257_an = 0;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 294 */           BlockPos blockpos1 = movingobjectposition.func_178782_a();
/* 295 */           this.field_145791_d = blockpos1.func_177958_n();
/* 296 */           this.field_145792_e = blockpos1.func_177956_o();
/* 297 */           this.field_145789_f = blockpos1.func_177952_p();
/* 298 */           iblockstate = this.field_70170_p.func_180495_p(blockpos1);
/* 299 */           this.field_145790_g = iblockstate.func_177230_c();
/* 300 */           this.field_70253_h = this.field_145790_g.func_176201_c(iblockstate);
/* 301 */           this.field_70159_w = ((float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t));
/* 302 */           this.field_70181_x = ((float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u));
/* 303 */           this.field_70179_y = ((float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v));
/* 304 */           float f3 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 305 */           this.field_70165_t -= this.field_70159_w / f3 * 0.05000000074505806D;
/* 306 */           this.field_70163_u -= this.field_70181_x / f3 * 0.05000000074505806D;
/* 307 */           this.field_70161_v -= this.field_70179_y / f3 * 0.05000000074505806D;
/* 308 */           func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 309 */           this.field_70254_i = true;
/* 310 */           this.field_70249_b = 7;
/* 311 */           func_70243_d(false);
/*     */           
/* 313 */           if (this.field_145790_g.func_149688_o() != Material.field_151579_a)
/*     */           {
/* 315 */             this.field_145790_g.func_180634_a(this.field_70170_p, blockpos1, iblockstate, this);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 320 */       if (func_70241_g())
/*     */       {
/* 322 */         for (i = 0; i < 4; i++)
/*     */         {
/* 324 */           this.field_70170_p.func_175688_a(EnumParticleTypes.CRIT, this.field_70165_t + this.field_70159_w * i / 4.0D, this.field_70163_u + this.field_70181_x * i / 4.0D, this.field_70161_v + this.field_70179_y * i / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y, new int[0]);
/*     */         }
/*     */       }
/*     */       
/* 328 */       this.field_70165_t += this.field_70159_w;
/* 329 */       this.field_70163_u += this.field_70181_x;
/* 330 */       this.field_70161_v += this.field_70179_y;
/* 331 */       float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 332 */       this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */       
/* 334 */       for (this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, f2) * 180.0D / 3.141592653589793D)); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 339 */       while (this.field_70125_A - this.field_70127_C >= 180.0F)
/*     */       {
/* 341 */         this.field_70127_C += 360.0F;
/*     */       }
/*     */       
/* 344 */       while (this.field_70177_z - this.field_70126_B < -180.0F)
/*     */       {
/* 346 */         this.field_70126_B -= 360.0F;
/*     */       }
/*     */       
/* 349 */       while (this.field_70177_z - this.field_70126_B >= 180.0F)
/*     */       {
/* 351 */         this.field_70126_B += 360.0F;
/*     */       }
/*     */       
/* 354 */       this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 355 */       this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/* 356 */       float f3 = 0.99F;
/* 357 */       float f1 = 0.05F;
/*     */       
/* 359 */       if (func_70090_H())
/*     */       {
/* 361 */         for (int l = 0; l < 4; l++)
/*     */         {
/* 363 */           float f4 = 0.25F;
/* 364 */           this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t - this.field_70159_w * f4, this.field_70163_u - this.field_70181_x * f4, this.field_70161_v - this.field_70179_y * f4, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
/*     */         }
/*     */         
/* 367 */         f3 = 0.6F;
/*     */       }
/*     */       
/* 370 */       if (func_70026_G())
/*     */       {
/* 372 */         func_70066_B();
/*     */       }
/*     */       
/* 375 */       this.field_70159_w *= f3;
/* 376 */       this.field_70181_x *= f3;
/* 377 */       this.field_70179_y *= f3;
/* 378 */       this.field_70181_x -= f1;
/* 379 */       func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 380 */       func_145775_I();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean inflictDamage(MovingObjectPosition movingobjectposition)
/*     */   {
/* 387 */     float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 388 */     int i1 = MathHelper.func_76143_f(f2 * func_70242_d());
/* 389 */     int fire = (func_70027_ad()) && (this.type != 2) ? 5 : 0;
/* 390 */     if (func_70241_g())
/*     */     {
/* 392 */       i1 += this.field_70146_Z.nextInt(i1 / 2 + 2);
/*     */     }
/*     */     
/* 395 */     DamageSource damagesource = null;
/*     */     
/* 397 */     switch (this.type) {
/*     */     case 0: 
/* 399 */       if (this.field_70250_c == null)
/*     */       {
/* 401 */         damagesource = new DamageSourceIndirectThaumcraftEntity("airarrow", this, this).func_76348_h().func_82726_p().func_76349_b();
/*     */       }
/*     */       else
/*     */       {
/* 405 */         damagesource = new DamageSourceIndirectThaumcraftEntity("airarrow", this, this.field_70250_c).func_76348_h().func_82726_p().func_76349_b();
/*     */       }
/* 407 */       break;
/*     */     
/*     */     case 1: 
/* 410 */       fire += 5;
/* 411 */       if (this.field_70250_c == null)
/*     */       {
/* 413 */         damagesource = new DamageSourceIndirectThaumcraftEntity("firearrow", this, this).func_76361_j().func_76349_b();
/*     */       }
/*     */       else
/*     */       {
/* 417 */         damagesource = new DamageSourceIndirectThaumcraftEntity("firearrow", this, this.field_70250_c).func_76361_j().func_76349_b();
/*     */       }
/* 419 */       break;
/*     */     
/*     */     case 4: 
/* 422 */       if (this.field_70250_c == null)
/*     */       {
/* 424 */         damagesource = new DamageSourceIndirectThaumcraftEntity("orderarrow", this, this).func_76348_h().func_82726_p().func_76349_b();
/*     */       }
/*     */       else
/*     */       {
/* 428 */         damagesource = new DamageSourceIndirectThaumcraftEntity("orderarrow", this, this.field_70250_c).func_76348_h().func_82726_p().func_76349_b();
/*     */       }
/* 430 */       if (!(movingobjectposition.field_72308_g instanceof EntityLivingBase)) break label479;
/* 431 */       ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 200, 4)); break;
/*     */     
/*     */ 
/*     */ 
/*     */     case 2: 
/* 436 */       if ((movingobjectposition.field_72308_g instanceof EntityLivingBase)) {
/* 437 */         ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, 4));
/*     */       }
/*     */     
/*     */     case 5: 
/* 441 */       if ((this.type == 5) && ((movingobjectposition.field_72308_g instanceof EntityLivingBase))) {
/* 442 */         ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 100));
/*     */       }
/*     */       break;
/*     */     }
/* 446 */     if (this.field_70250_c == null)
/*     */     {
/* 448 */       damagesource = new EntityDamageSourceIndirect("arrow", this, this).func_76349_b();
/*     */     }
/*     */     else
/*     */     {
/* 452 */       damagesource = new EntityDamageSourceIndirect("arrow", this, this.field_70250_c).func_76349_b();
/*     */     }
/*     */     
/*     */     label479:
/*     */     
/* 457 */     if ((fire > 0) && (!(movingobjectposition.field_72308_g instanceof EntityEnderman)))
/*     */     {
/* 459 */       movingobjectposition.field_72308_g.func_70015_d(fire);
/*     */     }
/*     */     
/*     */ 
/* 463 */     return movingobjectposition.field_72308_g.func_70097_a(damagesource, i1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70239_b(double damageIn)
/*     */   {
/* 470 */     this.field_70255_ao = damageIn;
/*     */   }
/*     */   
/*     */   public double func_70242_d()
/*     */   {
/* 475 */     switch (this.type) {
/* 476 */     case 3:  return this.field_70255_ao * 1.5D;
/* 477 */     case 4:  return this.field_70255_ao * 0.8D;
/* 478 */     case 5:  return this.field_70255_ao * 0.8D;
/*     */     }
/* 480 */     return this.field_70255_ao;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70240_a(int knockbackStrengthIn)
/*     */   {
/* 486 */     this.field_70256_ap = knockbackStrengthIn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 493 */     super.func_70014_b(par1NBTTagCompound);
/* 494 */     par1NBTTagCompound.func_74774_a("type", (byte)this.type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 503 */     super.func_70037_a(par1NBTTagCompound);
/* 504 */     this.type = par1NBTTagCompound.func_74771_c("type");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityPrimalArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */