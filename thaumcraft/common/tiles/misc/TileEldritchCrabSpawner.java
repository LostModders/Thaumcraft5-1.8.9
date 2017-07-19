/*     */ package thaumcraft.common.tiles.misc;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchCrab;
/*     */ 
/*     */ public class TileEldritchCrabSpawner extends TileThaumcraft implements ITickable
/*     */ {
/*  20 */   public int count = 150;
/*  21 */   public int ticks = 0;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/*  25 */     if (this.ticks == 0) this.ticks = this.field_145850_b.field_73012_v.nextInt(500);
/*  26 */     this.ticks += 1;
/*  27 */     if (!this.field_145850_b.field_72995_K) {
/*  28 */       this.count -= 1;
/*  29 */       if (this.count < 0) {
/*  30 */         this.count = (50 + this.field_145850_b.field_73012_v.nextInt(50));
/*     */       } else {
/*  32 */         if ((this.count == 15) && (isActivated()) && (!maxEntitiesReached())) {
/*  33 */           this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 1, 0);
/*  34 */           this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "random.fizz", 0.5F, 1.0F);
/*     */         }
/*  36 */         if ((this.count <= 0) && (isActivated()) && (!maxEntitiesReached())) {
/*  37 */           this.count = (150 + this.field_145850_b.field_73012_v.nextInt(100));
/*  38 */           spawnCrab();
/*  39 */           this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "thaumcraft:gore", 0.5F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*  43 */     else if (this.venting > 0) {
/*  44 */       this.venting -= 1;
/*  45 */       for (int a = 0; a < 3; a++) {
/*  46 */         drawVent();
/*     */       }
/*     */     }
/*  49 */     else if (this.field_145850_b.field_73012_v.nextInt(20) == 0) {
/*  50 */       drawVent();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   void drawVent()
/*     */   {
/*  57 */     EnumFacing dir = EnumFacing.field_82609_l[this.facing];
/*  58 */     float fx = 0.15F - this.field_145850_b.field_73012_v.nextFloat() * 0.3F;
/*  59 */     float fz = 0.15F - this.field_145850_b.field_73012_v.nextFloat() * 0.3F;
/*  60 */     float fy = 0.15F - this.field_145850_b.field_73012_v.nextFloat() * 0.3F;
/*  61 */     float fx2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/*  62 */     float fz2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/*  63 */     float fy2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/*  64 */     Thaumcraft.proxy.getFX().drawVentParticles(this.field_174879_c.func_177958_n() + 0.5F + fx + dir.func_82601_c() / 2.1F, this.field_174879_c.func_177956_o() + 0.5F + fy + dir.func_96559_d() / 2.1F, this.field_174879_c.func_177952_p() + 0.5F + fz + dir.func_82599_e() / 2.1F, dir.func_82601_c() / 3.0F + fx2, dir.func_96559_d() / 3.0F + fy2, dir.func_82599_e() / 3.0F + fz2, 10061994, 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  73 */   int venting = 0;
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/*  78 */     if (i == 1)
/*     */     {
/*  80 */       this.venting = 20;
/*  81 */       return true;
/*     */     }
/*     */     
/*  84 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   private boolean maxEntitiesReached()
/*     */   {
/*  89 */     List ents = this.field_145850_b.func_72872_a(EntityEldritchCrab.class, AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), this.field_174879_c.func_177958_n() + 1.0D, this.field_174879_c.func_177956_o() + 1.0D, this.field_174879_c.func_177952_p() + 1.0D).func_72314_b(32.0D, 32.0D, 32.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  94 */     return ents.size() > 5;
/*     */   }
/*     */   
/*     */   public boolean isActivated()
/*     */   {
/*  99 */     return this.field_145850_b.func_72977_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, 16.0D) != null;
/*     */   }
/*     */   
/*     */   private void spawnCrab() {
/* 103 */     EnumFacing dir = EnumFacing.field_82609_l[this.facing];
/* 104 */     EntityEldritchCrab crab = new EntityEldritchCrab(this.field_145850_b);
/* 105 */     double x = this.field_174879_c.func_177958_n() + dir.func_82601_c();
/* 106 */     double y = this.field_174879_c.func_177956_o() + dir.func_96559_d();
/* 107 */     double z = this.field_174879_c.func_177952_p() + dir.func_82599_e();
/* 108 */     crab.func_70012_b(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.0F);
/* 109 */     crab.func_180482_a(this.field_145850_b.func_175649_E(this.field_174879_c), (IEntityLivingData)null);
/* 110 */     crab.setHelm(false);
/* 111 */     crab.field_70159_w = (dir.func_82601_c() * 0.2F);
/* 112 */     crab.field_70181_x = (dir.func_96559_d() * 0.2F);
/* 113 */     crab.field_70179_y = (dir.func_82599_e() * 0.2F);
/* 114 */     this.field_145850_b.func_72838_d(crab);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 122 */     return AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n() - 1.0D, this.field_174879_c.func_177956_o() - 1.0D, this.field_174879_c.func_177952_p() - 1.0D, this.field_174879_c.func_177958_n() + 2.0D, this.field_174879_c.func_177956_o() + 2.0D, this.field_174879_c.func_177952_p() + 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 127 */   byte facing = 0;
/*     */   
/*     */   public byte getVentFacing() {
/* 130 */     return this.facing;
/*     */   }
/*     */   
/*     */   public void setVentFacing(byte face) {
/* 134 */     this.facing = face;
/* 135 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/* 136 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 141 */     this.facing = nbttagcompound.func_74771_c("facing");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 146 */     nbttagcompound.func_74774_a("facing", this.facing);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileEldritchCrabSpawner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */