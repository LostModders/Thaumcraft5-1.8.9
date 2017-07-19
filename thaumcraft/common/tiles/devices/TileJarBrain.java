/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.tiles.essentia.TileJar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileJarBrain
/*     */   extends TileJar
/*     */ {
/*     */   public float field_40063_b;
/*     */   public float field_40061_d;
/*     */   public float field_40059_f;
/*     */   public float field_40066_q;
/*     */   public float rota;
/*     */   public float rotb;
/*  25 */   public int xp = 0;
/*  26 */   public int xpMax = 2000;
/*  27 */   public int eatDelay = 0;
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  32 */     this.xp = nbttagcompound.func_74762_e("XP");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  38 */     nbttagcompound.func_74768_a("XP", this.xp);
/*     */   }
/*     */   
/*     */ 
/*  42 */   long lastsigh = System.currentTimeMillis() + 1500L;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/*  46 */     Entity entity = null;
/*  47 */     if (this.xp > this.xpMax) this.xp = this.xpMax;
/*  48 */     if (this.xp < this.xpMax) {
/*  49 */       entity = getClosestXPOrb();
/*     */       
/*  51 */       if ((entity != null) && (this.eatDelay == 0)) {
/*  52 */         double var3 = (this.field_174879_c.func_177958_n() + 0.5D - entity.field_70165_t) / 7.0D;
/*  53 */         double var5 = (this.field_174879_c.func_177956_o() + 0.5D - entity.field_70163_u) / 7.0D;
/*  54 */         double var7 = (this.field_174879_c.func_177952_p() + 0.5D - entity.field_70161_v) / 7.0D;
/*  55 */         double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
/*  56 */         double var11 = 1.0D - var9;
/*     */         
/*  58 */         if (var11 > 0.0D)
/*     */         {
/*  60 */           var11 *= var11;
/*  61 */           entity.field_70159_w += var3 / var9 * var11 * 0.15D;
/*  62 */           entity.field_70181_x += var5 / var9 * var11 * 0.33D;
/*  63 */           entity.field_70179_y += var7 / var9 * var11 * 0.15D;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  68 */     if (this.field_145850_b.field_72995_K)
/*     */     {
/*  70 */       this.rotb = this.rota;
/*  71 */       if (entity == null) {
/*  72 */         entity = this.field_145850_b.func_72977_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, 6.0D);
/*  73 */         if ((entity != null) && (this.lastsigh < System.currentTimeMillis())) {
/*  74 */           this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "thaumcraft:brain", 0.15F, 0.8F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F, false);
/*  75 */           this.lastsigh = (System.currentTimeMillis() + 5000L + this.field_145850_b.field_73012_v.nextInt(25000));
/*     */         }
/*     */       }
/*     */       
/*  79 */       if (entity != null)
/*     */       {
/*  81 */         double d = entity.field_70165_t - (this.field_174879_c.func_177958_n() + 0.5F);
/*  82 */         double d1 = entity.field_70161_v - (this.field_174879_c.func_177952_p() + 0.5F);
/*  83 */         this.field_40066_q = ((float)Math.atan2(d1, d));
/*  84 */         this.field_40059_f += 0.1F;
/*  85 */         if ((this.field_40059_f < 0.5F) || (rand.nextInt(40) == 0))
/*     */         {
/*  87 */           float f3 = this.field_40061_d;
/*     */           do
/*     */           {
/*  90 */             this.field_40061_d += rand.nextInt(4) - rand.nextInt(4);
/*     */           }
/*  92 */           while (f3 == this.field_40061_d);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*  97 */         this.field_40066_q += 0.01F;
/*     */       }
/*  99 */       while (this.rota >= 3.141593F) this.rota -= 6.283185F;
/* 100 */       while (this.rota < -3.141593F) this.rota += 6.283185F;
/* 101 */       while (this.field_40066_q >= 3.141593F) this.field_40066_q -= 6.283185F;
/* 102 */       while (this.field_40066_q < -3.141593F) { this.field_40066_q += 6.283185F;
/*     */       }
/* 104 */       for (float f = this.field_40066_q - this.rota; f >= 3.141593F; f -= 6.283185F) {}
/* 105 */       while (f < -3.141593F) f += 6.283185F;
/* 106 */       this.rota += f * 0.04F;
/*     */     }
/*     */     
/*     */ 
/* 110 */     if (this.eatDelay > 0) {
/* 111 */       this.eatDelay -= 1;
/* 112 */     } else if (this.xp < this.xpMax) {
/* 113 */       List ents = this.field_145850_b.func_72872_a(EntityXPOrb.class, AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n() - 0.1D, this.field_174879_c.func_177956_o() - 0.1D, this.field_174879_c.func_177952_p() - 0.1D, this.field_174879_c.func_177958_n() + 1.1D, this.field_174879_c.func_177956_o() + 1.1D, this.field_174879_c.func_177952_p() + 1.1D));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 119 */       if (ents.size() > 0) {
/* 120 */         for (Object ent : ents) {
/* 121 */           EntityXPOrb eo = (EntityXPOrb)ent;
/* 122 */           this.xp += eo.func_70526_d();
/* 123 */           this.field_145850_b.func_72956_a(eo, "random.eat", 0.1F, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/*     */           
/* 125 */           eo.func_70106_y();
/*     */         }
/* 127 */         this.field_145850_b.func_175689_h(this.field_174879_c);
/* 128 */         func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Entity getClosestXPOrb()
/*     */   {
/* 136 */     double cdist = Double.MAX_VALUE;
/* 137 */     Entity orb = null;
/*     */     
/* 139 */     List ents = this.field_145850_b.func_72872_a(EntityXPOrb.class, AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), this.field_174879_c.func_177958_n() + 1, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + 1).func_72314_b(6.0D, 6.0D, 6.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 145 */     if (ents.size() > 0) {
/* 146 */       for (Object ent : ents) {
/* 147 */         EntityXPOrb eo = (EntityXPOrb)ent;
/* 148 */         double d = func_145835_a(eo.field_70165_t, eo.field_70163_u, eo.field_70161_v);
/* 149 */         if (d < cdist) {
/* 150 */           orb = eo;
/* 151 */           cdist = d;
/*     */         }
/*     */       }
/*     */     }
/* 155 */     return orb;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileJarBrain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */