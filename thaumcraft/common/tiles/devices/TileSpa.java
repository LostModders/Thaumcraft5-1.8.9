/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.FluidTankInfo;
/*     */ import net.minecraftforge.fluids.IFluidHandler;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.common.items.consumables.ItemBathSalts;
/*     */ 
/*     */ public class TileSpa extends TileThaumcraft implements net.minecraft.inventory.ISidedInventory, IFluidHandler, ITickable
/*     */ {
/*  29 */   private ItemStack[] itemStacks = new ItemStack[1];
/*  30 */   private boolean mix = true;
/*     */   private String customName;
/*     */   
/*  33 */   public void toggleMix() { this.mix = (!this.mix);
/*  34 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/*  35 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public boolean getMix() {
/*  39 */     return this.mix;
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  44 */     this.mix = nbttagcompound.func_74767_n("mix");
/*  45 */     this.tank.setFluid(FluidStack.loadFluidStackFromNBT(nbttagcompound));
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  50 */     nbttagcompound.func_74757_a("mix", this.mix);
/*  51 */     if (this.tank.getFluid() != null) {
/*  52 */       this.tank.getFluid().writeToNBT(nbttagcompound);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/*  57 */     super.func_145839_a(nbttagcompound);
/*  58 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/*  59 */     this.itemStacks = new ItemStack[func_70302_i_()];
/*  60 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/*  62 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  63 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/*  65 */       if ((b0 >= 0) && (b0 < this.itemStacks.length))
/*     */       {
/*  67 */         this.itemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  74 */     super.func_145841_b(nbttagcompound);
/*  75 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/*  77 */     for (int i = 0; i < this.itemStacks.length; i++)
/*     */     {
/*  79 */       if (this.itemStacks[i] != null)
/*     */       {
/*  81 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  82 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  83 */         this.itemStacks[i].func_77955_b(nbttagcompound1);
/*  84 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/*  88 */     nbttagcompound.func_74782_a("Items", nbttaglist);
/*     */   }
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
/*     */   public int func_70302_i_()
/*     */   {
/* 102 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/* 111 */     return this.itemStacks[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/* 121 */     if (this.itemStacks[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/* 125 */       if (this.itemStacks[par1].field_77994_a <= par2)
/*     */       {
/* 127 */         ItemStack itemstack = this.itemStacks[par1];
/* 128 */         this.itemStacks[par1] = null;
/* 129 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/* 133 */       ItemStack itemstack = this.itemStacks[par1].func_77979_a(par2);
/*     */       
/* 135 */       if (this.itemStacks[par1].field_77994_a == 0)
/*     */       {
/* 137 */         this.itemStacks[par1] = null;
/*     */       }
/*     */       
/* 140 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 145 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 156 */     if (this.itemStacks[par1] != null)
/*     */     {
/* 158 */       ItemStack itemstack = this.itemStacks[par1];
/* 159 */       this.itemStacks[par1] = null;
/* 160 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 164 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 174 */     this.itemStacks[par1] = par2ItemStack;
/*     */     
/* 176 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 178 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 190 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 199 */     return this.field_145850_b.func_175625_s(func_174877_v()) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 210 */     return (par2ItemStack != null) && ((par2ItemStack.func_77973_b() instanceof ItemBathSalts));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 222 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */   public int func_174890_g()
/*     */   {
/* 229 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 236 */     return "thaumcraft.spa";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 246 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public int[] func_180463_a(EnumFacing side)
/*     */   {
/* 252 */     return side != EnumFacing.UP ? new int[] { 0 } : new int[0];
/*     */   }
/*     */   
/*     */   public boolean func_180462_a(int index, ItemStack itemStackIn, EnumFacing side)
/*     */   {
/* 257 */     return side != EnumFacing.UP;
/*     */   }
/*     */   
/*     */   public boolean func_180461_b(int index, ItemStack stack, EnumFacing side)
/*     */   {
/* 262 */     return side != EnumFacing.UP;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 267 */   private int counter = 0;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/* 272 */     if ((!this.field_145850_b.field_72995_K) && (this.counter++ % 40 == 0) && (!this.field_145850_b.func_175640_z(this.field_174879_c)) && (hasIngredients()))
/*     */     {
/* 274 */       Block b = this.field_145850_b.func_180495_p(this.field_174879_c.func_177984_a()).func_177230_c();
/* 275 */       int m = b.func_176201_c(this.field_145850_b.func_180495_p(this.field_174879_c.func_177984_a()));
/* 276 */       Block tb = null;
/* 277 */       if (this.mix) {
/* 278 */         tb = BlocksTC.purifyingFluid;
/*     */       } else {
/* 280 */         tb = this.tank.getFluid().getFluid().getBlock();
/*     */       }
/* 282 */       if ((b == tb) && (m == 0))
/*     */       {
/* 284 */         for (int xx = -2; xx <= 2; xx++) {
/* 285 */           for (int zz = -2; zz <= 2; zz++) {
/* 286 */             BlockPos p = func_174877_v().func_177982_a(xx, 1, zz);
/* 287 */             if (isValidLocation(p, true, tb)) {
/* 288 */               consumeIngredients();
/* 289 */               this.field_145850_b.func_175656_a(p, tb.func_176223_P());
/* 290 */               checkQuanta(p);
/* 291 */               return;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 296 */       else if (isValidLocation(this.field_174879_c.func_177984_a(), false, tb)) {
/* 297 */         consumeIngredients();
/* 298 */         this.field_145850_b.func_175656_a(this.field_174879_c.func_177984_a(), tb.func_176223_P());
/* 299 */         checkQuanta(this.field_174879_c.func_177984_a());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkQuanta(BlockPos pos)
/*     */   {
/* 306 */     Block b = this.field_145850_b.func_180495_p(pos).func_177230_c();
/*     */     
/* 308 */     if ((b instanceof BlockFluidBase)) {
/* 309 */       float p = ((BlockFluidBase)b).getQuantaPercentage(this.field_145850_b, pos);
/* 310 */       if (p < 1.0F) {
/* 311 */         int md = (int)(1.0F / p) - 1;
/* 312 */         if ((md >= 0) && (md < 16)) this.field_145850_b.func_175656_a(pos, b.func_176203_a(md));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean hasIngredients()
/*     */   {
/* 319 */     if (this.mix) {
/* 320 */       if ((this.tank.getInfo().fluid == null) || (!this.tank.getInfo().fluid.containsFluid(new FluidStack(FluidRegistry.WATER, 1000))))
/*     */       {
/*     */ 
/* 323 */         return false;
/*     */       }
/* 325 */       if ((this.itemStacks[0] == null) || (!(this.itemStacks[0].func_77973_b() instanceof ItemBathSalts))) {
/* 326 */         return false;
/*     */       }
/* 328 */     } else if ((this.tank.getInfo().fluid == null) || (!this.tank.getFluid().getFluid().canBePlacedInWorld()) || (this.tank.getFluidAmount() < 1000))
/*     */     {
/*     */ 
/* 331 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 335 */     return true;
/*     */   }
/*     */   
/*     */   private void consumeIngredients() {
/* 339 */     if (this.mix) func_70298_a(0, 1);
/* 340 */     drain(EnumFacing.DOWN, 1000, true);
/*     */   }
/*     */   
/*     */   private boolean isValidLocation(BlockPos pos, boolean mustBeAdjacent, Block target) {
/* 344 */     if (((target == Blocks.field_150355_j) || (target == Blocks.field_150358_i)) && (this.field_145850_b.field_73011_w.func_177500_n())) return false;
/* 345 */     Block b = this.field_145850_b.func_180495_p(pos).func_177230_c();
/* 346 */     Block bb = this.field_145850_b.func_180495_p(pos.func_177977_b()).func_177230_c();
/* 347 */     int m = b.func_176201_c(this.field_145850_b.func_180495_p(pos));
/* 348 */     if ((bb.isSideSolid(this.field_145850_b, pos.func_177977_b(), EnumFacing.UP)) && (b.func_176200_f(this.field_145850_b, pos)) && ((b != target) || (m != 0))) {
/* 349 */       if (!mustBeAdjacent) {
/* 350 */         return true;
/*     */       }
/* 352 */       return thaumcraft.common.lib.utils.BlockUtils.isBlockTouching(this.field_145850_b, pos, target.func_176203_a(0));
/*     */     }
/*     */     
/* 355 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 360 */   public FluidTank tank = new FluidTank(5000);
/*     */   
/*     */ 
/*     */   public int fill(EnumFacing from, FluidStack resource, boolean doFill)
/*     */   {
/* 365 */     int df = this.tank.fill(resource, doFill);
/* 366 */     if ((df > 0) && (doFill)) {
/* 367 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 368 */       func_70296_d();
/*     */     }
/* 370 */     return df;
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
/*     */   {
/* 376 */     if ((resource == null) || (!resource.isFluidEqual(this.tank.getFluid())))
/*     */     {
/* 378 */       return null;
/*     */     }
/* 380 */     return this.tank.drain(resource.amount, doDrain);
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
/*     */   {
/* 386 */     FluidStack fs = this.tank.drain(maxDrain, doDrain);
/* 387 */     if ((fs != null) && (doDrain)) {
/* 388 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 389 */       func_70296_d();
/*     */     }
/* 391 */     return fs;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canFill(EnumFacing from, Fluid fluid)
/*     */   {
/* 397 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canDrain(EnumFacing from, Fluid fluid)
/*     */   {
/* 403 */     return from != EnumFacing.UP;
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidTankInfo[] getTankInfo(EnumFacing from)
/*     */   {
/* 409 */     return new FluidTankInfo[] { this.tank.getInfo() };
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileSpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */