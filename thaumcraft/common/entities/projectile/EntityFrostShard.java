/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class EntityFrostShard extends EntityThrowable implements net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*  25 */   public double bounce = 0.5D;
/*     */   
/*  27 */   public int bounceLimit = 3;
/*     */   
/*     */   public EntityFrostShard(World par1World) {
/*  30 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityFrostShard(World par1World, EntityLivingBase par2EntityLiving, float scatter) {
/*  34 */     super(par1World, par2EntityLiving);
/*  35 */     func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, func_70182_d(), scatter);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  40 */     return this.fragile ? 0.015F : 0.05F;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  45 */     data.writeDouble(this.bounce);
/*  46 */     data.writeInt(this.bounceLimit);
/*  47 */     data.writeBoolean(this.fragile);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  52 */     this.bounce = data.readDouble();
/*  53 */     this.bounceLimit = data.readInt();
/*  54 */     this.fragile = data.readBoolean();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  60 */     if (mop.field_72308_g != null)
/*     */     {
/*  62 */       int ox = MathHelper.func_76128_c(this.field_70165_t) - MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/*  63 */       int oy = MathHelper.func_76128_c(this.field_70163_u) - MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/*  64 */       int oz = MathHelper.func_76128_c(this.field_70161_v) - MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/*  65 */       if (oz != 0) this.field_70179_y *= -1.0D;
/*  66 */       if (ox != 0) this.field_70159_w *= -1.0D;
/*  67 */       if (oy != 0) this.field_70181_x *= -0.9D;
/*  68 */       this.field_70159_w *= 0.15D;
/*  69 */       this.field_70181_x *= 0.15D;
/*  70 */       this.field_70179_y *= 0.15D;
/*  71 */       for (int a = 0; a < getDamage(); a++) {
/*  72 */         this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D, new int[] { Block.func_176210_f(Blocks.field_150432_aD.func_176223_P()) });
/*     */       }
/*     */       
/*     */     }
/*  76 */     else if (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) {
/*  77 */       if (mop.field_178784_b.func_82599_e() != 0) this.field_70179_y *= -1.0D;
/*  78 */       if (mop.field_178784_b.func_82601_c() != 0) this.field_70159_w *= -1.0D;
/*  79 */       if (mop.field_178784_b.func_96559_d() != 0) this.field_70181_x *= -0.9D;
/*  80 */       IBlockState bhit = this.field_70170_p.func_180495_p(mop.func_178782_a());
/*  81 */       try { func_85030_a(bhit.func_177230_c().field_149762_H.func_150495_a(), 0.3F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/*     */       } catch (Exception e) {}
/*  83 */       for (int a = 0; a < getDamage(); a++) {
/*  84 */         this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D, new int[] { Block.func_176210_f(bhit) });
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  90 */     this.field_70159_w *= this.bounce;
/*  91 */     this.field_70181_x *= this.bounce;
/*  92 */     this.field_70179_y *= this.bounce;
/*  93 */     float var20 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/*  94 */     this.field_70165_t -= this.field_70159_w / var20 * 0.05000000074505806D;
/*  95 */     this.field_70163_u -= this.field_70181_x / var20 * 0.05000000074505806D;
/*  96 */     this.field_70161_v -= this.field_70179_y / var20 * 0.05000000074505806D;
/*  97 */     func_70018_K();
/*     */     
/*  99 */     if ((!this.field_70170_p.field_72995_K) && (mop.field_72308_g != null)) {
/* 100 */       double mx = mop.field_72308_g.field_70159_w;
/* 101 */       double my = mop.field_72308_g.field_70181_x;
/* 102 */       double mz = mop.field_72308_g.field_70179_y;
/*     */       
/* 104 */       if ((this.fragile) && (mop.field_72308_g.field_70172_ad > 0) && (this.field_70146_Z.nextInt(10) < this.field_70173_aa + 3)) {
/* 105 */         mop.field_72308_g.field_70172_ad = 0;
/*     */       }
/* 107 */       mop.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, func_85052_h()), getDamage());
/*     */       
/* 109 */       if (((mop.field_72308_g instanceof EntityLivingBase)) && (getFrosty() > 0)) {
/* 110 */         ((EntityLivingBase)mop.field_72308_g).func_70690_d(new net.minecraft.potion.PotionEffect(Potion.field_76421_d.field_76415_H, 200, getFrosty() - 1));
/*     */       }
/*     */       
/* 113 */       if (this.fragile) {
/* 114 */         func_70106_y();
/* 115 */         func_85030_a(Blocks.field_150432_aD.field_149762_H.func_150495_a(), 0.3F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 116 */         mop.field_72308_g.field_70159_w = (mx + (mop.field_72308_g.field_70159_w - mx) / 10.0D);
/* 117 */         mop.field_72308_g.field_70181_x = (my + (mop.field_72308_g.field_70181_x - my) / 10.0D);
/* 118 */         mop.field_72308_g.field_70179_y = (mz + (mop.field_72308_g.field_70179_y - mz) / 10.0D);
/*     */       }
/*     */     }
/*     */     
/* 122 */     if (this.bounceLimit-- <= 0) {
/* 123 */       func_70106_y();
/* 124 */       func_85030_a(Blocks.field_150432_aD.field_149762_H.func_150495_a(), 0.3F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 125 */       for (int a = 0; a < 8.0F * getDamage(); a++) {
/* 126 */         this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D, new int[] { Block.func_176210_f(Blocks.field_150432_aD.func_176223_P()) });
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 136 */     super.func_70071_h_();
/*     */     
/* 138 */     if ((this.field_70170_p.field_72995_K) && (getFrosty() > 0)) {
/* 139 */       float s = getDamage() / 10.0F;
/* 140 */       for (int a = 0; a < getFrosty(); a++) {
/* 141 */         Thaumcraft.proxy.getFX().sparkle((float)this.field_70165_t - s + this.field_70146_Z.nextFloat() * (s * 2.0F), (float)this.field_70163_u - s + this.field_70146_Z.nextFloat() * (s * 2.0F), (float)this.field_70161_v - s + this.field_70146_Z.nextFloat() * (s * 2.0F), 0.4F, 6, 0.005F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 148 */     float var20 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 149 */     this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */     
/* 151 */     for (this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, var20) * 180.0D / 3.141592653589793D)); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 156 */     while (this.field_70125_A - this.field_70127_C >= 180.0F)
/*     */     {
/* 158 */       this.field_70127_C += 360.0F;
/*     */     }
/*     */     
/* 161 */     while (this.field_70177_z - this.field_70126_B < -180.0F)
/*     */     {
/* 163 */       this.field_70126_B -= 360.0F;
/*     */     }
/*     */     
/* 166 */     while (this.field_70177_z - this.field_70126_B >= 180.0F)
/*     */     {
/* 168 */       this.field_70126_B += 360.0F;
/*     */     }
/*     */     
/* 171 */     this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 172 */     this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 178 */     super.func_70014_b(par1NBTTagCompound);
/* 179 */     par1NBTTagCompound.func_74776_a("damage", getDamage());
/* 180 */     par1NBTTagCompound.func_74757_a("fragile", this.fragile);
/* 181 */     par1NBTTagCompound.func_74768_a("frost", getFrosty());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 187 */     super.func_70037_a(par1NBTTagCompound);
/* 188 */     setDamage(par1NBTTagCompound.func_74760_g("damage"));
/* 189 */     this.fragile = par1NBTTagCompound.func_74767_n("fragile");
/* 190 */     setFrosty(par1NBTTagCompound.func_74762_e("frost"));
/*     */   }
/*     */   
/*     */ 
/* 194 */   public boolean fragile = false;
/*     */   
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 199 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70088_a()
/*     */   {
/* 206 */     super.func_70088_a();
/* 207 */     this.field_70180_af.func_75682_a(16, new Float(0.0F));
/* 208 */     this.field_70180_af.func_75682_a(17, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public void setDamage(float par1)
/*     */   {
/* 213 */     this.field_70180_af.func_75692_b(16, Float.valueOf(par1));
/*     */     
/* 215 */     func_70105_a(0.15F + par1 * 0.15F, 0.15F + par1 * 0.15F);
/*     */   }
/*     */   
/*     */   public float getDamage()
/*     */   {
/* 220 */     return this.field_70180_af.func_111145_d(16);
/*     */   }
/*     */   
/*     */   public void setFrosty(int frosty)
/*     */   {
/* 225 */     this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)frosty));
/*     */   }
/*     */   
/*     */   public int getFrosty()
/*     */   {
/* 230 */     return this.field_70180_af.func_75683_a(17);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/projectile/EntityFrostShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */