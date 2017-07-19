/*     */ package thaumcraft.common.tiles.misc;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusPortableHole;
/*     */ import thaumcraft.common.tiles.TileMemory;
/*     */ 
/*     */ public class TileHole extends TileMemory implements net.minecraft.util.ITickable
/*     */ {
/*  17 */   public short countdown = 0;
/*  18 */   public short countdownmax = 120;
/*  19 */   public byte count = 0;
/*  20 */   public EnumFacing direction = null;
/*     */   
/*     */ 
/*     */   public TileHole() {}
/*     */   
/*     */ 
/*     */   public TileHole(IBlockState bi, short max, byte count, EnumFacing direction)
/*     */   {
/*  28 */     super(bi);
/*  29 */     this.count = count;
/*  30 */     this.countdownmax = max;
/*  31 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public TileHole(byte count)
/*     */   {
/*  36 */     this.count = count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  43 */     if (this.field_145850_b.field_72995_K)
/*     */     {
/*  45 */       for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(1); a++) {
/*  46 */         surroundwithsparkles();
/*     */       }
/*     */     } else {
/*  49 */       if ((this.countdown == 0) && (this.count > 1) && (this.direction != null)) {
/*  50 */         switch (this.direction.func_176740_k()) {
/*     */         case Y: 
/*  52 */           for (int a = 0; a < 9; a++) if ((a / 3 != 1) || (a % 3 != 1)) {
/*  53 */               ItemFocusPortableHole.createHole(this.field_145850_b, func_174877_v().func_177982_a(-1 + a / 3, 0, -1 + a % 3), null, (byte)1, this.countdownmax);
/*     */             }
/*  55 */           break;
/*     */         case Z: 
/*  57 */           for (int a = 0; a < 9; a++) if ((a / 3 != 1) || (a % 3 != 1)) {
/*  58 */               ItemFocusPortableHole.createHole(this.field_145850_b, func_174877_v().func_177982_a(-1 + a / 3, -1 + a % 3, 0), null, (byte)1, this.countdownmax);
/*     */             }
/*  60 */           break;
/*     */         case X: 
/*  62 */           for (int a = 0; a < 9; a++) { if ((a / 3 != 1) || (a % 3 != 1)) {
/*  63 */               ItemFocusPortableHole.createHole(this.field_145850_b, func_174877_v().func_177982_a(0, -1 + a / 3, -1 + a % 3), null, (byte)1, this.countdownmax);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*  68 */         if (!ItemFocusPortableHole.createHole(this.field_145850_b, func_174877_v().func_177972_a(this.direction.func_176734_d()), this.direction, (byte)(this.count - 1), this.countdownmax))
/*     */         {
/*     */ 
/*  71 */           this.count = 0;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  76 */       this.countdown = ((short)(this.countdown + 1));
/*     */       
/*  78 */       if (this.countdown % 20 == 0) { func_70296_d();
/*     */       }
/*  80 */       if (this.countdown >= this.countdownmax) {
/*  81 */         this.field_145850_b.func_180501_a(func_174877_v(), this.oldblock, 3);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void surroundwithsparkles()
/*     */   {
/*  88 */     for (EnumFacing d1 : ) {
/*  89 */       Block b1 = this.field_145850_b.func_180495_p(func_174877_v().func_177972_a(d1)).func_177230_c();
/*  90 */       if ((b1 != thaumcraft.api.blocks.BlocksTC.hole) && (!b1.func_149662_c())) {
/*  91 */         for (EnumFacing d2 : EnumFacing.values()) {
/*  92 */           if ((d1.func_176740_k() != d2.func_176740_k()) && (
/*  93 */             (this.field_145850_b.func_180495_p(func_174877_v().func_177972_a(d2)).func_177230_c().func_149662_c()) || (this.field_145850_b.func_180495_p(func_174877_v().func_177972_a(d1).func_177972_a(d2)).func_177230_c().func_149662_c())))
/*     */           {
/*     */ 
/*  96 */             float sx = 0.5F * d1.func_82601_c();
/*  97 */             float sy = 0.5F * d1.func_96559_d();
/*  98 */             float sz = 0.5F * d1.func_82599_e();
/*     */             
/* 100 */             if (sx == 0.0F) sx = 0.5F * d2.func_82601_c();
/* 101 */             if (sy == 0.0F) sy = 0.5F * d2.func_96559_d();
/* 102 */             if (sz == 0.0F) { sz = 0.5F * d2.func_82599_e();
/*     */             }
/* 104 */             if (sx == 0.0F) sx = this.field_145850_b.field_73012_v.nextFloat(); else sx += 0.5F;
/* 105 */             if (sy == 0.0F) sy = this.field_145850_b.field_73012_v.nextFloat(); else sy += 0.5F;
/* 106 */             if (sz == 0.0F) sz = this.field_145850_b.field_73012_v.nextFloat(); else { sz += 0.5F;
/*     */             }
/* 108 */             Thaumcraft.proxy.getFX().sparkle(func_174877_v().func_177958_n() + sx, func_174877_v().func_177956_o() + sy, func_174877_v().func_177952_p() + sz, 2);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/* 120 */     super.func_145839_a(nbttagcompound);
/* 121 */     this.countdown = nbttagcompound.func_74765_d("countdown");
/* 122 */     this.countdownmax = nbttagcompound.func_74765_d("countdownmax");
/* 123 */     this.count = nbttagcompound.func_74771_c("count");
/* 124 */     byte db = nbttagcompound.func_74771_c("direction");
/* 125 */     this.direction = (db >= 0 ? EnumFacing.values()[db] : null);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/* 131 */     super.func_145841_b(nbttagcompound);
/* 132 */     nbttagcompound.func_74777_a("countdown", this.countdown);
/* 133 */     nbttagcompound.func_74777_a("countdownmax", this.countdownmax);
/* 134 */     nbttagcompound.func_74774_a("count", this.count);
/* 135 */     nbttagcompound.func_74774_a("direction", this.direction == null ? -1 : (byte)this.direction.ordinal());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileHole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */