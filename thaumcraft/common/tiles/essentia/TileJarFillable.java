/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ 
/*     */ 
/*     */ public class TileJarFillable
/*     */   extends TileJar
/*     */   implements IAspectSource, IEssentiaTransport
/*     */ {
/*  18 */   public Aspect aspect = null;
/*  19 */   public Aspect aspectFilter = null;
/*  20 */   public int amount = 0;
/*  21 */   public int maxAmount = 64;
/*  22 */   public int facing = 2;
/*  23 */   public boolean blocked = false;
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  28 */     this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
/*  29 */     this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
/*  30 */     this.amount = nbttagcompound.func_74765_d("Amount");
/*  31 */     this.facing = nbttagcompound.func_74771_c("facing");
/*  32 */     this.blocked = nbttagcompound.func_74767_n("blocked");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  38 */     if (this.aspect != null) nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
/*  39 */     if (this.aspectFilter != null) nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag());
/*  40 */     nbttagcompound.func_74777_a("Amount", (short)this.amount);
/*  41 */     nbttagcompound.func_74774_a("facing", (byte)this.facing);
/*  42 */     nbttagcompound.func_74757_a("blocked", this.blocked);
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/*  47 */     AspectList al = new AspectList();
/*  48 */     if ((this.aspect != null) && (this.amount > 0)) al.add(this.aspect, this.amount);
/*  49 */     return al;
/*     */   }
/*     */   
/*     */   public void setAspects(AspectList aspects)
/*     */   {
/*  54 */     if ((aspects != null) && (aspects.size() > 0)) {
/*  55 */       this.aspect = aspects.getAspectsSortedByAmount()[0];
/*  56 */       this.amount = aspects.getAmount(aspects.getAspectsSortedByAmount()[0]);
/*     */     }
/*     */   }
/*     */   
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  62 */     if (am == 0) return am;
/*  63 */     if (((this.amount < this.maxAmount) && (tt == this.aspect)) || (this.amount == 0))
/*     */     {
/*  65 */       this.aspect = tt;
/*  66 */       int added = Math.min(am, this.maxAmount - this.amount);
/*  67 */       this.amount += added;
/*  68 */       am -= added;
/*     */     }
/*  70 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/*  71 */     func_70296_d();
/*  72 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  77 */     if ((this.amount >= am) && (tt == this.aspect)) {
/*  78 */       this.amount -= am;
/*  79 */       if (this.amount <= 0) {
/*  80 */         this.aspect = null;
/*  81 */         this.amount = 0;
/*     */       }
/*  83 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/*  84 */       func_70296_d();
/*  85 */       return true;
/*     */     }
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/*  92 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/*  98 */     if ((this.amount >= amt) && (tag == this.aspect)) return true;
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 104 */     for (Aspect tt : ot.getAspects()) {
/* 105 */       if ((this.amount > 0) && (tt == this.aspect)) return true;
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 112 */     if (tag == this.aspect) return this.amount;
/* 113 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 118 */     return this.aspectFilter != null ? tag.equals(this.aspectFilter) : true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/* 126 */     return face == EnumFacing.UP;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/* 131 */     return face == EnumFacing.UP;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/* 136 */     return face == EnumFacing.UP;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public int getMinimumSuction()
/*     */   {
/* 145 */     return this.aspectFilter != null ? 64 : 32;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing loc)
/*     */   {
/* 150 */     return this.aspectFilter != null ? this.aspectFilter : this.aspect;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc)
/*     */   {
/* 155 */     if (this.amount < this.maxAmount) {
/* 156 */       if (this.aspectFilter != null) {
/* 157 */         return 64;
/*     */       }
/* 159 */       return 32;
/*     */     }
/*     */     
/* 162 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/* 168 */     return this.aspect;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/* 173 */     return this.amount;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 178 */     return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 183 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/* 186 */   int count = 0;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/* 190 */     if ((!this.field_145850_b.field_72995_K) && (++this.count % 5 == 0) && (this.amount < this.maxAmount)) {
/* 191 */       fillJar();
/*     */     }
/*     */   }
/*     */   
/*     */   void fillJar() {
/* 196 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, EnumFacing.UP);
/* 197 */     if (te != null) {
/* 198 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 199 */       if (!ic.canOutputTo(EnumFacing.DOWN)) { return;
/*     */       }
/* 201 */       Aspect ta = null;
/* 202 */       if (this.aspectFilter != null) {
/* 203 */         ta = this.aspectFilter;
/*     */       }
/* 205 */       else if ((this.aspect != null) && (this.amount > 0)) {
/* 206 */         ta = this.aspect;
/*     */       }
/* 208 */       else if ((ic.getEssentiaAmount(EnumFacing.DOWN) > 0) && 
/* 209 */         (ic.getSuctionAmount(EnumFacing.DOWN) < getSuctionAmount(EnumFacing.UP)) && (getSuctionAmount(EnumFacing.UP) >= ic.getMinimumSuction()))
/*     */       {
/* 211 */         ta = ic.getEssentiaType(EnumFacing.DOWN);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 217 */       if ((ta != null) && (ic.getSuctionAmount(EnumFacing.DOWN) < getSuctionAmount(EnumFacing.UP))) {
/* 218 */         addToContainer(ta, ic.takeEssentia(ta, 1, EnumFacing.DOWN));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBlocked()
/*     */   {
/* 226 */     return this.blocked;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileJarFillable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */