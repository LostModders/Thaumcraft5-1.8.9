/*     */ package thaumcraft.common.tiles.misc;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ import thaumcraft.common.lib.world.dim.MazeThread;
/*     */ 
/*     */ public class TileEldritchAltar extends TileThaumcraft implements ITickable
/*     */ {
/*  24 */   private boolean spawner = false;
/*  25 */   private boolean open = false;
/*  26 */   private boolean spawnedClerics = false;
/*  27 */   private byte spawnType = 0;
/*     */   
/*  29 */   private byte eyes = 0;
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  33 */     setEyes(nbttagcompound.func_74771_c("eyes"));
/*  34 */     setOpen(nbttagcompound.func_74767_n("open"));
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  39 */     nbttagcompound.func_74774_a("eyes", getEyes());
/*  40 */     nbttagcompound.func_74757_a("open", isOpen());
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/*  45 */     super.func_145839_a(nbttagcompound);
/*  46 */     this.spawnedClerics = nbttagcompound.func_74767_n("spawnedClerics");
/*  47 */     this.spawner = nbttagcompound.func_74767_n("spawner");
/*  48 */     this.spawnType = nbttagcompound.func_74771_c("spawntype");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  53 */     super.func_145841_b(nbttagcompound);
/*  54 */     nbttagcompound.func_74757_a("spawnedClerics", this.spawnedClerics);
/*  55 */     nbttagcompound.func_74757_a("spawner", this.spawner);
/*  56 */     nbttagcompound.func_74774_a("spawntype", this.spawnType);
/*     */   }
/*     */   
/*     */   public double func_145833_n()
/*     */   {
/*  61 */     return 9216.0D;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  67 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 0.3D, func_174877_v().func_177956_o() - 0.3D, func_174877_v().func_177952_p() - 0.3D, func_174877_v().func_177958_n() + 1.3D, func_174877_v().func_177956_o() + 1.3D, func_174877_v().func_177952_p() + 1.3D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSpawner()
/*     */   {
/*  73 */     return this.spawner;
/*     */   }
/*     */   
/*     */   public void setSpawner(boolean spawner) {
/*  77 */     this.spawner = spawner;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  82 */   private int counter = 0;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  87 */     if ((!this.field_145850_b.field_72995_K) && (isSpawner()) && (this.counter++ >= 80) && (this.counter % 40 == 0))
/*     */     {
/*  89 */       switch (this.spawnType) {
/*     */       case 0: 
/*  91 */         if (!this.spawnedClerics) {
/*  92 */           spawnClerics();
/*     */         } else {
/*  94 */           spawnGuards();
/*     */         }
/*  96 */         break;
/*     */       case 1: 
/*  98 */         spawnGuardian();
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void spawnGuards()
/*     */   {
/* 110 */     List ents = this.field_145850_b.func_72872_a(EntityCultistCleric.class, AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1).func_72314_b(24.0D, 16.0D, 24.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 116 */     if (ents.size() < 1) {
/* 117 */       setSpawner(false);
/* 118 */       return;
/*     */     }
/*     */     
/* 121 */     ents = this.field_145850_b.func_72872_a(EntityCultist.class, AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1).func_72314_b(24.0D, 16.0D, 24.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 126 */     if (ents.size() < 8) {
/* 127 */       EntityCultistKnight eg = new EntityCultistKnight(this.field_145850_b);
/* 128 */       int i1 = func_174877_v().func_177958_n() + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 4, 10) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/* 129 */       int j1 = func_174877_v().func_177956_o() + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 0, 3) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/* 130 */       int k1 = func_174877_v().func_177952_p() + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 4, 10) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/*     */       
/* 132 */       if (World.func_175683_a(this.field_145850_b, new BlockPos(i1, j1 - 1, k1)))
/*     */       {
/* 134 */         eg.func_70107_b(i1, j1, k1);
/*     */         
/* 136 */         if ((this.field_145850_b.func_72855_b(eg.func_174813_aQ())) && (this.field_145850_b.func_72945_a(eg, eg.func_174813_aQ()).isEmpty()) && (!this.field_145850_b.func_72953_d(eg.func_174813_aQ())))
/*     */         {
/*     */ 
/*     */ 
/* 140 */           eg.func_180482_a(this.field_145850_b.func_175649_E(this.field_174879_c), (IEntityLivingData)null);
/* 141 */           eg.func_175449_a(this.field_174879_c, 16);
/* 142 */           this.field_145850_b.func_72838_d(eg);
/* 143 */           eg.func_70656_aK();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void spawnGuardian()
/*     */   {
/* 151 */     EntityEldritchGuardian eg = new EntityEldritchGuardian(this.field_145850_b);
/* 152 */     int i1 = func_174877_v().func_177958_n() + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 4, 10) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/* 153 */     int j1 = func_174877_v().func_177956_o() + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 0, 3) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/* 154 */     int k1 = func_174877_v().func_177952_p() + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 4, 10) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/*     */     
/* 156 */     if (World.func_175683_a(this.field_145850_b, new BlockPos(i1, j1 - 1, k1)))
/*     */     {
/* 158 */       eg.func_70107_b(i1, j1, k1);
/*     */       
/* 160 */       if ((this.field_145850_b.func_72855_b(eg.func_174813_aQ())) && (this.field_145850_b.func_72945_a(eg, eg.func_174813_aQ()).isEmpty()) && (!this.field_145850_b.func_72953_d(eg.func_174813_aQ())) && (eg.func_70601_bi()))
/*     */       {
/*     */ 
/*     */ 
/* 164 */         eg.func_180482_a(this.field_145850_b.func_175649_E(this.field_174879_c), (IEntityLivingData)null);
/* 165 */         eg.func_70656_aK();
/* 166 */         eg.func_175449_a(this.field_174879_c, 16);
/* 167 */         this.field_145850_b.func_72838_d(eg);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void spawnClerics()
/*     */   {
/* 174 */     int success = 0;
/* 175 */     for (int a = 0; a < 4; a++) {
/* 176 */       int xx = 0;
/* 177 */       int zz = 0;
/* 178 */       switch (a) {
/* 179 */       case 0:  xx = -2;zz = -2; break;
/* 180 */       case 1:  xx = -2;zz = 2; break;
/* 181 */       case 2:  xx = 2;zz = -2; break;
/* 182 */       case 3:  xx = 2;zz = 2;
/*     */       }
/* 184 */       EntityCultistCleric cleric = new EntityCultistCleric(this.field_145850_b);
/* 185 */       if (World.func_175683_a(this.field_145850_b, this.field_174879_c.func_177982_a(xx, -1, zz)))
/*     */       {
/* 187 */         cleric.func_70107_b(func_174877_v().func_177958_n() + 0.5D + xx, func_174877_v().func_177956_o(), func_174877_v().func_177952_p() + 0.5D + zz);
/*     */         
/* 189 */         if ((this.field_145850_b.func_72855_b(cleric.func_174813_aQ())) && (this.field_145850_b.func_72945_a(cleric, cleric.func_174813_aQ()).isEmpty()) && (!this.field_145850_b.func_72953_d(cleric.func_174813_aQ())))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 194 */           cleric.func_175449_a(this.field_174879_c, 8);
/* 195 */           cleric.func_180482_a(this.field_145850_b.func_175649_E(this.field_174879_c), (IEntityLivingData)null);
/*     */           
/* 197 */           if (this.field_145850_b.func_72838_d(cleric)) {
/* 198 */             success++;
/* 199 */             cleric.setIsRitualist(true);
/* 200 */             cleric.func_70656_aK();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 205 */     if (success > 2) {
/* 206 */       this.spawnedClerics = true;
/* 207 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */   public byte getSpawnType()
/*     */   {
/* 213 */     return this.spawnType;
/*     */   }
/*     */   
/*     */   public void setSpawnType(byte spawnType) {
/* 217 */     this.spawnType = spawnType;
/*     */   }
/*     */   
/*     */   public byte getEyes() {
/* 221 */     return this.eyes;
/*     */   }
/*     */   
/*     */   public void setEyes(byte eyes) {
/* 225 */     this.eyes = eyes;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/* 229 */     return this.open;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean open) {
/* 233 */     this.open = open;
/*     */   }
/*     */   
/*     */   public boolean checkForMaze() {
/* 237 */     int w = 15 + this.field_145850_b.field_73012_v.nextInt(8) * 2;
/* 238 */     int h = 15 + this.field_145850_b.field_73012_v.nextInt(8) * 2;
/* 239 */     if (!MazeHandler.mazesInRange(this.field_174879_c.func_177958_n() >> 4, this.field_174879_c.func_177952_p() >> 4, w, h)) {
/* 240 */       Thread t = new Thread(new MazeThread(this.field_174879_c.func_177956_o() >> 4, this.field_174879_c.func_177952_p() >> 4, w, h, this.field_145850_b.field_73012_v.nextLong()));
/* 241 */       t.start();
/* 242 */       return false;
/*     */     }
/* 244 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileEldritchAltar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */