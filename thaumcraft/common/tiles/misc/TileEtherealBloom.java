/*     */ package thaumcraft.common.tiles.misc;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.world.taint.ITaintBlock;
/*     */ import thaumcraft.common.lib.utils.TCVec3;
/*     */ 
/*     */ public class TileEtherealBloom extends net.minecraft.tileentity.TileEntity implements ITickable
/*     */ {
/*  15 */   public int counter = 0;
/*  16 */   public int rad; public int rad1 = 0;
/*  17 */   public int growthCounter = 100;
/*     */   public int foundTaint;
/*     */   public static final int bloomsleep = 300;
/*     */   public int sleepcounter;
/*     */   public boolean sleep;
/*     */   
/*     */   public TileEtherealBloom()
/*     */   {
/*  25 */     this.counter = 0;
/*  26 */     this.rad1 = 0;
/*  27 */     this.growthCounter = 100;
/*  28 */     this.foundTaint = 300;
/*  29 */     this.sleepcounter = ('Ĭ' * 4);
/*  30 */     this.sleep = false;
/*     */   }
/*     */   
/*     */   public void resetSleep() {
/*  34 */     this.foundTaint = 300;
/*  35 */     this.sleepcounter = ('Ĭ' * 4);
/*  36 */     this.sleep = false;
/*     */   }
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/*  41 */     if (this.counter == 0) {
/*  42 */       this.counter = this.field_145850_b.field_73012_v.nextInt(100);
/*  43 */       this.rad = this.counter;
/*     */     }
/*  45 */     this.counter += 1;
/*     */     
/*  47 */     if (this.foundTaint == 0) {
/*  48 */       this.sleepcounter -= 1;
/*  49 */       this.sleep = (this.sleepcounter != 0);
/*  50 */       if (!this.sleep) {
/*  51 */         this.counter = 0;
/*  52 */         this.sleepcounter = ('Ĭ' * 4);
/*     */       }
/*     */     }
/*     */     
/*  56 */     if ((!this.field_145850_b.field_72995_K) && (this.counter % 20 == 0) && (!this.sleep)) {
/*  57 */       this.rad = ((int)(this.rad + (5.0D + Math.sqrt(1 + this.rad1) * 5.0D + this.field_145850_b.field_73012_v.nextInt(5))));
/*  58 */       if (this.rad > 360) {
/*  59 */         this.rad -= 360;
/*  60 */         this.rad1 += 5 + this.field_145850_b.field_73012_v.nextInt(5);
/*  61 */         if (this.rad1 > 87) {
/*  62 */           this.rad1 -= 87;
/*     */         }
/*     */       }
/*     */       
/*  66 */       boolean foundsomething = false;
/*     */       
/*  68 */       TCVec3 vsource = TCVec3.createVectorHelper(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D);
/*  69 */       for (int q = 1; q < 8; q++) {
/*  70 */         TCVec3 vtar = TCVec3.createVectorHelper(q, 0.0D, 0.0D);
/*  71 */         vtar.rotateAroundZ(this.rad1 / 180.0F * 3.1415927F);
/*  72 */         vtar.rotateAroundY(this.rad / 180.0F * 3.1415927F);
/*     */         
/*  74 */         TCVec3 vres1 = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
/*  75 */         TCVec3 vres2 = vsource.addVector(-vtar.xCoord, -vtar.yCoord, -vtar.zCoord);
/*  76 */         BlockPos t1 = new BlockPos(MathHelper.func_76128_c(vres1.xCoord), MathHelper.func_76128_c(vres1.yCoord), MathHelper.func_76128_c(vres1.zCoord));
/*  77 */         while ((this.field_145850_b.func_175623_d(t1)) && (t1.func_177956_o() > 0)) {
/*  78 */           t1 = t1.func_177977_b();
/*     */         }
/*  80 */         BlockPos t2 = new BlockPos(MathHelper.func_76128_c(vres2.xCoord), MathHelper.func_76128_c(vres2.yCoord), MathHelper.func_76128_c(vres2.zCoord));
/*  81 */         while ((this.field_145850_b.func_175623_d(t2)) && (t2.func_177956_o() > 0)) {
/*  82 */           t2 = t2.func_177977_b();
/*     */         }
/*     */         
/*  85 */         if (clearBlock(t1)) foundsomething = true;
/*  86 */         if (clearBlock(t2)) { foundsomething = true;
/*     */         }
/*  88 */         if (foundsomething) {
/*  89 */           resetSleep();
/*  90 */           break;
/*     */         }
/*     */       }
/*  93 */       if ((this.foundTaint > 0) && (!foundsomething)) {
/*  94 */         this.foundTaint -= 1;
/*     */       }
/*     */     }
/*     */     
/*  98 */     if ((this.field_145850_b.field_72995_K) && 
/*  99 */       (this.growthCounter == 0)) {
/* 100 */       this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "thaumcraft:roots", 1.0F, 0.6F, false);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 105 */     this.growthCounter += 1;
/*     */   }
/*     */   
/*     */   private boolean clearBlock(BlockPos p) {
/* 109 */     boolean bc = thaumcraft.common.lib.utils.Utils.resetBiomeAt(this.field_145850_b, p);
/* 110 */     Block bt = this.field_145850_b.func_180495_p(p).func_177230_c();
/* 111 */     if ((bt instanceof ITaintBlock)) {
/* 112 */       ((ITaintBlock)bt).die(this.field_145850_b, p, this.field_145850_b.func_180495_p(p));
/* 113 */       bc = true;
/*     */     }
/* 115 */     return bc;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/misc/TileEtherealBloom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */