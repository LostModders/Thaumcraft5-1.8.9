/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.common.lib.events.EssentiaHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEssentiaOutput
/*     */   extends TileThaumcraft
/*     */   implements IEssentiaTransport, ITickable
/*     */ {
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/*  33 */     return face == getFacing().func_176734_d();
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/*  38 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/*  43 */     return face == getFacing().func_176734_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public int getMinimumSuction()
/*     */   {
/*  52 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing loc)
/*     */   {
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc)
/*     */   {
/*  62 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/*  67 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/*  72 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/*  77 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/*  82 */     return amount;
/*     */   }
/*     */   
/*  85 */   int count = 0;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/*  89 */     if ((!this.field_145850_b.field_72995_K) && 
/*  90 */       (++this.count % 5 == 0)) {
/*  91 */       fillBuffer();
/*     */     }
/*     */   }
/*     */   
/*     */   void fillBuffer()
/*     */   {
/*  97 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), getFacing().func_176734_d());
/*  98 */     if (te != null) {
/*  99 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 100 */       if (!ic.canInputFrom(getFacing())) return;
/* 101 */       if ((ic.getSuctionAmount(getFacing()) > 0) && (ic.getSuctionType(getFacing()) != null)) {
/* 102 */         Aspect ta = ic.getSuctionType(getFacing());
/* 103 */         if ((EssentiaHandler.drainEssentiaWithConfirmation(this, ta, getFacing(), 16, false, 5)) && 
/* 104 */           (ic.addEssentia(ta, 1, getFacing()) > 0)) {
/* 105 */           EssentiaHandler.confirmDrain();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileEssentiaOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */