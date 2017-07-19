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
/*     */ 
/*     */ public class TileEssentiaInput
/*     */   extends TileThaumcraft
/*     */   implements IEssentiaTransport, ITickable
/*     */ {
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/*  34 */     return face == getFacing().func_176734_d();
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/*  39 */     return face == getFacing().func_176734_d();
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public int getMinimumSuction()
/*     */   {
/*  53 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing loc)
/*     */   {
/*  58 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc)
/*     */   {
/*  63 */     return 128;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/*  68 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/*  73 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/*  78 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/*  83 */     return amount;
/*     */   }
/*     */   
/*  86 */   int count = 0;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  91 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*  93 */       if (++this.count % 5 == 0) {
/*  94 */         fillJar();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void fillJar()
/*     */   {
/* 101 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), getFacing().func_176734_d());
/* 102 */     if (te != null) {
/* 103 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 104 */       if (!ic.canOutputTo(getFacing())) return;
/* 105 */       if ((ic.getEssentiaAmount(getFacing()) > 0) && (ic.getSuctionAmount(getFacing()) < getSuctionAmount(getFacing().func_176734_d())) && (getSuctionAmount(getFacing().func_176734_d()) >= ic.getMinimumSuction()))
/*     */       {
/* 107 */         Aspect ta = ic.getEssentiaType(getFacing());
/* 108 */         if (EssentiaHandler.addEssentia(this, ta, getFacing(), 16, false, 5)) {
/* 109 */           ic.takeEssentia(ta, 1, getFacing());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileEssentiaInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */