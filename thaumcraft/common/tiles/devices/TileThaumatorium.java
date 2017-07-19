/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class TileThaumatorium extends TileThaumcraft implements IAspectContainer, IEssentiaTransport, ISidedInventory, ITickable
/*     */ {
/*  45 */   public ItemStack inputStack = null;
/*  46 */   public AspectList essentia = new AspectList();
/*     */   
/*  48 */   public ArrayList<Integer> recipeHash = new ArrayList();
/*  49 */   public ArrayList<AspectList> recipeEssentia = new ArrayList();
/*     */   
/*  51 */   public ArrayList<String> recipePlayer = new ArrayList();
/*  52 */   public int currentCraft = -1;
/*  53 */   public int maxRecipes = 1;
/*     */   
/*  55 */   public Aspect currentSuction = null;
/*     */   
/*     */ 
/*  58 */   int venting = 0;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  63 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 0.1D, func_174877_v().func_177956_o() - 0.1D, func_174877_v().func_177952_p() - 0.1D, func_174877_v().func_177958_n() + 1.1D, func_174877_v().func_177956_o() + 2.1D, func_174877_v().func_177952_p() + 1.1D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  71 */     this.essentia.readFromNBT(nbttagcompound);
/*  72 */     this.maxRecipes = nbttagcompound.func_74771_c("maxrec");
/*     */     
/*  74 */     this.recipeEssentia = new ArrayList();
/*  75 */     this.recipeHash = new ArrayList();
/*  76 */     this.recipePlayer = new ArrayList();
/*  77 */     int[] hashes = nbttagcompound.func_74759_k("recipes");
/*  78 */     if (hashes != null) {
/*  79 */       for (int hash : hashes) {
/*  80 */         CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(hash);
/*  81 */         if (recipe != null) {
/*  82 */           this.recipeEssentia.add(recipe.aspects.copy());
/*  83 */           this.recipePlayer.add("");
/*  84 */           this.recipeHash.add(Integer.valueOf(hash));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  94 */     nbttagcompound.func_74774_a("maxrec", (byte)this.maxRecipes);
/*  95 */     this.essentia.writeToNBT(nbttagcompound);
/*  96 */     int[] hashes = new int[this.recipeHash.size()];
/*  97 */     int a = 0;
/*  98 */     for (Integer i : this.recipeHash) {
/*  99 */       hashes[a] = i.intValue();
/* 100 */       a++;
/*     */     }
/* 102 */     nbttagcompound.func_74783_a("recipes", hashes);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 108 */     super.func_145839_a(nbtCompound);
/* 109 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
/* 110 */     if (nbttaglist.func_74745_c() > 0) {
/* 111 */       this.inputStack = ItemStack.func_77949_a(nbttaglist.func_150305_b(0));
/*     */     }
/*     */     
/* 114 */     NBTTagList nbttaglist2 = nbtCompound.func_150295_c("OutputPlayer", 8);
/* 115 */     for (int a = 0; a < nbttaglist2.func_74745_c(); a++) {
/* 116 */       if (this.recipePlayer.size() > a) {
/* 117 */         this.recipePlayer.set(a, nbttaglist2.func_150307_f(a));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 124 */     super.func_145841_b(nbtCompound);
/* 125 */     NBTTagList nbttaglist = new NBTTagList();
/* 126 */     if (this.inputStack != null)
/*     */     {
/* 128 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 129 */       nbttagcompound1.func_74774_a("Slot", (byte)0);
/* 130 */       this.inputStack.func_77955_b(nbttagcompound1);
/* 131 */       nbttaglist.func_74742_a(nbttagcompound1);
/*     */     }
/* 133 */     nbtCompound.func_74782_a("Items", nbttaglist);
/*     */     
/* 135 */     NBTTagList nbttaglist2 = new NBTTagList();
/* 136 */     if (this.recipePlayer.size() > 0)
/*     */     {
/* 138 */       for (int a = 0; a < this.recipePlayer.size(); a++) {
/* 139 */         if (this.recipePlayer.get(a) != null)
/*     */         {
/* 141 */           NBTTagString nbttagcompound1 = new NBTTagString((String)this.recipePlayer.get(a));
/* 142 */           nbttaglist2.func_74742_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */     }
/* 146 */     nbtCompound.func_74782_a("OutputPlayer", nbttaglist2);
/*     */   }
/*     */   
/*     */   boolean checkHeat() {
/* 150 */     Material mat = this.field_145850_b.func_180495_p(this.field_174879_c.func_177979_c(2)).func_177230_c().func_149688_o();
/* 151 */     Block bi = this.field_145850_b.func_180495_p(this.field_174879_c.func_177979_c(2)).func_177230_c();
/* 152 */     return (mat == Material.field_151587_i) || (mat == Material.field_151581_o) || (bi == BlocksTC.nitor);
/*     */   }
/*     */   
/* 155 */   int counter = 0;
/* 156 */   boolean heated = false;
/* 157 */   CrucibleRecipe currentRecipe = null;
/*     */   public Container eventHandler;
/*     */   
/* 160 */   public ItemStack getCurrentOutputRecipe() { ItemStack out = null;
/* 161 */     if ((this.currentCraft >= 0) && (this.recipeHash != null) && (this.recipeHash.size() > 0)) {
/* 162 */       CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(((Integer)this.recipeHash.get(this.currentCraft)).intValue());
/* 163 */       if (recipe != null) {
/* 164 */         out = recipe.getRecipeOutput().func_77946_l();
/*     */       }
/*     */     }
/* 167 */     return out;
/*     */   }
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/* 172 */     if (!this.field_145850_b.field_72995_K) {
/* 173 */       if ((this.counter == 0) || (this.counter % 40 == 0)) {
/* 174 */         this.heated = checkHeat();
/* 175 */         getUpgrades();
/*     */       }
/* 177 */       this.counter += 1;
/* 178 */       if ((this.heated) && (!gettingPower()) && (this.counter % 5 == 0) && (this.recipeHash != null) && (this.recipeHash.size() > 0)) {
/* 179 */         if (this.inputStack == null) {
/* 180 */           this.currentSuction = null;
/* 181 */           return;
/*     */         }
/*     */         
/* 184 */         if ((this.currentCraft < 0) || (this.currentCraft >= this.recipeHash.size()) || (this.currentRecipe == null) || (!this.currentRecipe.catalystMatches(this.inputStack))) {
/* 185 */           for (int a = 0; a < this.recipeHash.size(); a++) {
/* 186 */             CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(((Integer)this.recipeHash.get(a)).intValue());
/* 187 */             if (recipe.catalystMatches(this.inputStack)) {
/* 188 */               this.currentCraft = a;
/* 189 */               this.currentRecipe = recipe;
/* 190 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 195 */         if ((this.currentCraft < 0) || (this.currentCraft >= this.recipeHash.size())) { return;
/*     */         }
/* 197 */         TileEntity inventory = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(BlockStateUtils.getFacing(func_145832_p())));
/* 198 */         if ((inventory != null) && ((inventory instanceof IInventory))) {
/* 199 */           ItemStack dropped = getCurrentOutputRecipe();
/* 200 */           dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, BlockStateUtils.getFacing(func_145832_p()).func_176734_d(), false);
/*     */           
/* 202 */           if (dropped != null) {
/* 203 */             return;
/*     */           }
/*     */         }
/*     */         
/* 207 */         boolean done = true;
/* 208 */         this.currentSuction = null;
/* 209 */         for (Aspect aspect : ((AspectList)this.recipeEssentia.get(this.currentCraft)).getAspectsSortedByName()) {
/* 210 */           if (this.essentia.getAmount(aspect) < ((AspectList)this.recipeEssentia.get(this.currentCraft)).getAmount(aspect)) {
/* 211 */             this.currentSuction = aspect;
/* 212 */             done = false;
/* 213 */             break;
/*     */           }
/*     */         }
/*     */         
/* 217 */         if (done) {
/* 218 */           completeRecipe();
/*     */ 
/*     */         }
/* 221 */         else if (this.currentSuction != null) fill();
/*     */       }
/*     */     }
/* 224 */     else if (this.venting > 0) {
/* 225 */       this.venting -= 1;
/* 226 */       float fx = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 227 */       float fz = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 228 */       float fy = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 229 */       float fx2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 230 */       float fz2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 231 */       float fy2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 232 */       int color = 16777215;
/* 233 */       EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/* 234 */       Thaumcraft.proxy.getFX().drawVentParticles(this.field_174879_c.func_177958_n() + 0.5F + fx + facing.func_82601_c() / 2.0F, this.field_174879_c.func_177956_o() + 0.5F + fy, this.field_174879_c.func_177952_p() + 0.5F + fz + facing.func_82599_e() / 2.0F, facing.func_82601_c() / 4.0F + fx2, fy2, facing.func_82599_e() / 4.0F + fz2, color);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void completeRecipe()
/*     */   {
/* 244 */     if ((this.currentRecipe != null) && (this.currentCraft < this.recipeHash.size()) && 
/* 245 */       (this.currentRecipe.matches(this.essentia, this.inputStack)) && 
/* 246 */       (func_70298_a(0, 1) != null)) {
/* 247 */       this.essentia = new AspectList();
/* 248 */       ItemStack dropped = getCurrentOutputRecipe();
/*     */       
/* 250 */       EntityPlayer p = this.field_145850_b.func_72924_a((String)this.recipePlayer.get(this.currentCraft));
/* 251 */       if (p != null) {
/* 252 */         FMLCommonHandler.instance().firePlayerCraftingEvent(p, dropped, new InventoryFake(new ItemStack[] { this.inputStack }));
/*     */       }
/*     */       
/* 255 */       EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/* 256 */       TileEntity inventory = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(facing));
/* 257 */       if ((inventory != null) && ((inventory instanceof IInventory))) {
/* 258 */         dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, facing.func_176734_d(), true);
/*     */       }
/*     */       
/*     */ 
/* 262 */       if (dropped != null) {
/* 263 */         EntityItem ei = new EntityItem(this.field_145850_b, this.field_174879_c.func_177958_n() + 0.5D + facing.func_82601_c() * 0.66D, this.field_174879_c.func_177956_o() + 0.33D + facing.func_176734_d().func_96559_d(), this.field_174879_c.func_177952_p() + 0.5D + facing.func_82599_e() * 0.66D, dropped.func_77946_l());
/*     */         
/*     */ 
/*     */ 
/* 267 */         ei.field_70159_w = (0.075F * facing.func_82601_c());
/* 268 */         ei.field_70181_x = 0.02500000037252903D;
/* 269 */         ei.field_70179_y = (0.075F * facing.func_82599_e());
/* 270 */         this.field_145850_b.func_72838_d(ei);
/* 271 */         this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 0, 0);
/*     */       }
/* 273 */       this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "random.fizz", 0.25F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F);
/*     */       
/* 275 */       this.currentCraft = -1;
/* 276 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 277 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   void fill()
/*     */   {
/* 284 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/* 285 */     TileEntity te = null;
/* 286 */     IEssentiaTransport ic = null;
/* 287 */     for (int y = 0; y <= 1; y++) {
/* 288 */       for (EnumFacing dir : EnumFacing.field_82609_l)
/*     */       {
/* 290 */         if ((dir != facing) && (dir != EnumFacing.DOWN) && ((y != 0) || (dir != EnumFacing.UP)))
/*     */         {
/* 292 */           te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c.func_177981_b(y), dir);
/* 293 */           if (te != null) {
/* 294 */             ic = (IEssentiaTransport)te;
/* 295 */             if ((ic.getEssentiaAmount(dir.func_176734_d()) > 0) && (ic.getSuctionAmount(dir.func_176734_d()) < getSuctionAmount(null)) && (getSuctionAmount(null) >= ic.getMinimumSuction()))
/*     */             {
/*     */ 
/* 298 */               int ess = ic.takeEssentia(this.currentSuction, 1, dir.func_176734_d());
/* 299 */               if (ess > 0) {
/* 300 */                 addToContainer(this.currentSuction, ess);
/* 301 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/* 314 */     int ce = this.currentRecipe.aspects.getAmount(tt) - this.essentia.getAmount(tt);
/* 315 */     if ((this.currentRecipe == null) || (ce <= 0)) return am;
/* 316 */     int add = Math.min(ce, am);
/* 317 */     this.essentia.add(tt, add);
/* 318 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/* 319 */     func_70296_d();
/* 320 */     return am - add;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/* 325 */     if (this.essentia.getAmount(tt) >= am) {
/* 326 */       this.essentia.remove(tt, am);
/* 327 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 328 */       func_70296_d();
/* 329 */       return true;
/*     */     }
/* 331 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 337 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 342 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tt, int am)
/*     */   {
/* 347 */     return this.essentia.getAmount(tt) >= am;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tt)
/*     */   {
/* 352 */     return this.essentia.getAmount(tt);
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 357 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 364 */     if (i >= 0)
/*     */     {
/* 366 */       if (this.field_145850_b.field_72995_K) {
/* 367 */         this.venting = 7;
/*     */       }
/* 369 */       return true;
/*     */     }
/*     */     
/* 372 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(EnumFacing face)
/*     */   {
/* 380 */     return face != BlockStateUtils.getFacing(func_145832_p());
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face)
/*     */   {
/* 385 */     return face != BlockStateUtils.getFacing(func_145832_p());
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face)
/*     */   {
/* 390 */     return false;
/*     */   }
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount)
/*     */   {
/* 395 */     this.currentSuction = aspect;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(EnumFacing loc)
/*     */   {
/* 400 */     return this.currentSuction;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc)
/*     */   {
/* 405 */     return this.currentSuction != null ? 128 : 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(EnumFacing loc)
/*     */   {
/* 410 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(EnumFacing loc)
/*     */   {
/* 415 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 420 */     return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face)
/*     */   {
/* 425 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 430 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/* 436 */     return this.essentia;
/*     */   }
/*     */   
/*     */   public void setAspects(AspectList aspects)
/*     */   {
/* 441 */     this.essentia = aspects;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/* 452 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/* 458 */     return this.inputStack;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/* 464 */     if (this.inputStack != null)
/*     */     {
/*     */ 
/*     */ 
/* 468 */       if (this.inputStack.field_77994_a <= par2)
/*     */       {
/* 470 */         ItemStack itemstack = this.inputStack;
/* 471 */         this.inputStack = null;
/* 472 */         if (this.eventHandler != null) this.eventHandler.func_75130_a(this);
/* 473 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/* 477 */       ItemStack itemstack = this.inputStack.func_77979_a(par2);
/*     */       
/* 479 */       if (this.inputStack.field_77994_a == 0)
/*     */       {
/* 481 */         this.inputStack = null;
/*     */       }
/* 483 */       if (this.eventHandler != null) this.eventHandler.func_75130_a(this);
/* 484 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 489 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 496 */     if (this.inputStack != null)
/*     */     {
/* 498 */       ItemStack itemstack = this.inputStack;
/* 499 */       this.inputStack = null;
/* 500 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 504 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 513 */     this.inputStack = par2ItemStack;
/*     */     
/* 515 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 517 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*     */     
/* 520 */     if (this.eventHandler != null) { this.eventHandler.func_75130_a(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70297_j_()
/*     */   {
/* 526 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 532 */     return this.field_145850_b.func_175625_s(this.field_174879_c) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 541 */     return true;
/*     */   }
/*     */   
/*     */   public int[] func_180463_a(EnumFacing side)
/*     */   {
/* 546 */     return new int[] { 0 };
/*     */   }
/*     */   
/*     */   public boolean gettingPower() {
/* 550 */     return (this.field_145850_b.func_175687_A(this.field_174879_c) > 0) || (this.field_145850_b.func_175687_A(this.field_174879_c.func_177977_b()) > 0) || (this.field_145850_b.func_175687_A(this.field_174879_c.func_177984_a()) > 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void getUpgrades()
/*     */   {
/* 556 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/* 557 */     int mr = 1;
/* 558 */     for (int yy = 0; yy <= 1; yy++) {
/* 559 */       for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 560 */         if ((dir != EnumFacing.DOWN) && (dir != facing)) {
/* 561 */           int xx = this.field_174879_c.func_177958_n() + dir.func_82601_c();
/* 562 */           int zz = this.field_174879_c.func_177952_p() + dir.func_82599_e();
/* 563 */           BlockPos bp = new BlockPos(xx, this.field_174879_c.func_177956_o() + yy + dir.func_96559_d(), zz);
/* 564 */           IBlockState bs = this.field_145850_b.func_180495_p(bp);
/*     */           
/* 566 */           if (bs == BlocksTC.brainBox.func_176223_P().func_177226_a(IBlockFacing.FACING, dir.func_176734_d()))
/* 567 */             mr += 2;
/*     */         }
/*     */       }
/*     */     }
/* 571 */     if (mr != this.maxRecipes) {
/* 572 */       this.maxRecipes = mr;
/* 573 */       while (this.recipeHash.size() > this.maxRecipes) {
/* 574 */         this.recipeHash.remove(this.recipeHash.size() - 1);
/*     */       }
/* 576 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 577 */       func_70296_d();
/*     */     }
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
/* 590 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */   public int func_174890_g()
/*     */   {
/* 598 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_174888_l() {}
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 606 */     return "container.alchemyfurnace";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 611 */     return false;
/*     */   }
/*     */   
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 616 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180462_a(int index, ItemStack itemStackIn, EnumFacing direction)
/*     */   {
/* 622 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_180461_b(int index, ItemStack stack, EnumFacing direction)
/*     */   {
/* 627 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileThaumatorium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */