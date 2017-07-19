/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ 
/*     */ public class TileAlembic extends TileThaumcraft implements IAspectContainer, IEssentiaTransport
/*     */ {
/*     */   public Aspect aspect;
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/*  23 */     return this.aspect != null ? new AspectList().add(this.aspect, this.amount) : new AspectList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  30 */   public Aspect aspectFilter = null;
/*  31 */   public int amount = 0;
/*  32 */   public int maxAmount = 32;
/*  33 */   public int facing = EnumFacing.DOWN.ordinal();
/*     */   
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  38 */   public AxisAlignedBB getRenderBoundingBox() { return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 0.1D, func_174877_v().func_177956_o() - 0.1D, func_174877_v().func_177952_p() - 0.1D, func_174877_v().func_177958_n() + 1.1D, func_174877_v().func_177956_o() + 1.1D, func_174877_v().func_177952_p() + 1.1D); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  46 */     this.facing = nbttagcompound.func_74771_c("facing");
/*  47 */     this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
/*  48 */     String tag = nbttagcompound.func_74779_i("aspect");
/*  49 */     if (tag != null) this.aspect = Aspect.getAspect(tag);
/*  50 */     this.amount = nbttagcompound.func_74765_d("amount");
/*     */     
/*  52 */     this.fd = EnumFacing.field_82609_l[this.facing];
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  58 */     if (this.aspect != null) nbttagcompound.func_74778_a("aspect", this.aspect.getTag());
/*  59 */     if (this.aspectFilter != null) nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag());
/*  60 */     nbttagcompound.func_74777_a("amount", (short)this.amount);
/*  61 */     nbttagcompound.func_74774_a("facing", (byte)this.facing);
/*     */   }
/*     */   
/*  64 */   public boolean aboveFurnace = false;
/*  65 */   EnumFacing fd = null;
/*     */   
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  69 */     if ((this.aspectFilter != null) && (tt != this.aspectFilter)) return am;
/*  70 */     if (((this.amount < this.maxAmount) && (tt == this.aspect)) || (this.amount == 0)) {
/*  71 */       this.aspect = tt;
/*  72 */       int added = Math.min(am, this.maxAmount - this.amount);
/*  73 */       this.amount += added;
/*  74 */       am -= added;
/*     */     }
/*  76 */     func_70296_d();
/*  77 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/*  78 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  83 */     if ((this.amount == 0) || (this.aspect == null)) {
/*  84 */       this.aspect = null;
/*  85 */       this.amount = 0;
/*     */     }
/*  87 */     if ((this.aspect != null) && (this.amount >= am) && (tt == this.aspect)) {
/*  88 */       this.amount -= am;
/*  89 */       if (this.amount <= 0) {
/*  90 */         this.aspect = null;
/*  91 */         this.amount = 0;
/*     */       }
/*  93 */       func_70296_d();
/*  94 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/*  95 */       return true;
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 102 */     if ((this.amount > 0) && (this.aspect != null) && 
/* 103 */       (ot.getAmount(this.aspect) > 0)) { return true;
/*     */     }
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tt, int am)
/*     */   {
/* 110 */     if ((this.amount >= am) && (tt == this.aspect)) return true;
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tt)
/*     */   {
/* 116 */     return tt == this.aspect ? this.amount : 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 121 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 127 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/* 134 */     return (face != EnumFacing.field_82609_l[this.facing]) && (face != EnumFacing.DOWN);
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/* 139 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/* 144 */     return (face != EnumFacing.field_82609_l[this.facing]) && (face != EnumFacing.DOWN);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public Aspect getSuctionType(EnumFacing loc)
/*     */   {
/* 154 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc)
/*     */   {
/* 159 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/* 164 */     return this.aspect;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/* 169 */     return this.amount;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 174 */     return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing loc)
/*     */   {
/* 179 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 184 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static boolean processAlembics(World world, BlockPos pos, Aspect aspect)
/*     */   {
/* 193 */     int deep = 1;
/*     */     for (;;) {
/* 195 */       TileEntity te = world.func_175625_s(pos.func_177981_b(deep));
/* 196 */       if ((te == null) || (!(te instanceof TileAlembic))) break;
/* 197 */       TileAlembic alembic = (TileAlembic)te;
/* 198 */       if ((alembic.amount > 0) && (alembic.aspect == aspect) && 
/* 199 */         (alembic.addToContainer(aspect, 1) == 0)) { return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 204 */       deep++;
/*     */     }
/*     */     
/*     */ 
/* 208 */     deep = 1;
/*     */     for (;;) {
/* 210 */       TileEntity te = world.func_175625_s(pos.func_177981_b(deep));
/* 211 */       if ((te == null) || (!(te instanceof TileAlembic))) break;
/* 212 */       TileAlembic alembic = (TileAlembic)te;
/* 213 */       if (((alembic.aspectFilter == null) || (alembic.aspectFilter == aspect)) && 
/* 214 */         (alembic.addToContainer(aspect, 1) == 0)) { return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 219 */       deep++;
/*     */     }
/*     */     
/* 222 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/crafting/TileAlembic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */