/*    */ package thaumcraft.common.tiles.essentia;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aura.AuraHelper;
/*    */ 
/*    */ public class TileJarFillableVoid
/*    */   extends TileJarFillable
/*    */ {
/*    */   public int addToContainer(Aspect tt, int am)
/*    */   {
/* 14 */     boolean up = this.amount < this.maxAmount;
/* 15 */     if (am == 0) return am;
/* 16 */     if ((tt == this.aspect) || (this.amount == 0)) {
/* 17 */       this.aspect = tt;
/* 18 */       this.amount += am;
/* 19 */       am = 0;
/* 20 */       if (this.amount > this.maxAmount) {
/* 21 */         if (this.field_145850_b.field_73012_v.nextInt(100) == 0)
/* 22 */           AuraHelper.pollute(func_145831_w(), func_174877_v(), 1, true);
/* 23 */         this.amount = this.maxAmount;
/*    */       }
/*    */     }
/* 26 */     if (up) {
/* 27 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 28 */       func_70296_d();
/*    */     }
/* 30 */     return am;
/*    */   }
/*    */   
/*    */   public int getMinimumSuction()
/*    */   {
/* 35 */     return this.aspectFilter != null ? 48 : 32;
/*    */   }
/*    */   
/*    */   public int getSuctionAmount(EnumFacing loc)
/*    */   {
/* 40 */     if ((this.aspectFilter != null) && (this.amount < this.maxAmount)) {
/* 41 */       return 48;
/*    */     }
/* 43 */     return 32;
/*    */   }
/*    */   
/*    */ 
/* 47 */   int count = 0;
/*    */   
/*    */ 
/*    */   public void func_73660_a()
/*    */   {
/* 52 */     if ((!this.field_145850_b.field_72995_K) && (++this.count % 5 == 0)) {
/* 53 */       fillJar();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileJarFillableVoid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */