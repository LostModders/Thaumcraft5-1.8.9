/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.FluidTankInfo;
/*     */ import net.minecraftforge.fluids.IFluidHandler;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ 
/*     */ public class TileCrucible extends TileThaumcraft implements ITickable, IFluidHandler, thaumcraft.api.wands.IWandable, IAspectContainer
/*     */ {
/*     */   public short heat;
/*  43 */   public AspectList aspects = new AspectList();
/*  44 */   public final int maxTags = 100;
/*  45 */   int bellows = -1;
/*  46 */   private int delay = 0;
/*     */   
/*     */ 
/*  49 */   public FluidTank tank = new FluidTank(FluidRegistry.WATER, 0, 1000);
/*     */   
/*     */ 
/*     */   public TileCrucible()
/*     */   {
/*  54 */     this.heat = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  60 */     this.heat = nbttagcompound.func_74765_d("Heat");
/*     */     
/*  62 */     this.tank.readFromNBT(nbttagcompound);
/*  63 */     if (nbttagcompound.func_74764_b("Empty")) { this.tank.setFluid(null);
/*     */     }
/*  65 */     this.aspects.readFromNBT(nbttagcompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  72 */     nbttagcompound.func_74777_a("Heat", this.heat);
/*     */     
/*  74 */     this.tank.writeToNBT(nbttagcompound);
/*     */     
/*  76 */     this.aspects.writeToNBT(nbttagcompound);
/*     */   }
/*     */   
/*     */ 
/*  80 */   private long counter = -100L;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/*  85 */     this.counter += 1L;
/*  86 */     int prevheat = this.heat;
/*  87 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*     */ 
/*     */ 
/*  91 */       if (this.tank.getFluidAmount() > 0) {
/*  92 */         Block block = this.field_145850_b.func_180495_p(func_174877_v().func_177977_b()).func_177230_c();
/*  93 */         if ((block.func_149688_o() == Material.field_151587_i) || (block.func_149688_o() == Material.field_151581_o) || (block == BlocksTC.nitor))
/*     */         {
/*     */ 
/*  96 */           if (this.heat < 200) {
/*  97 */             this.heat = ((short)(this.heat + 1));
/*  98 */             if ((prevheat < 151) && (this.heat >= 151)) {
/*  99 */               func_70296_d();
/* 100 */               this.field_145850_b.func_175689_h(func_174877_v());
/*     */             }
/*     */           }
/* 103 */         } else if (this.heat > 0) {
/* 104 */           this.heat = ((short)(this.heat - 1));
/* 105 */           if (this.heat == 149) {
/* 106 */             func_70296_d();
/* 107 */             this.field_145850_b.func_175689_h(func_174877_v());
/*     */           }
/*     */         }
/* 110 */       } else if (this.heat > 0) {
/* 111 */         this.heat = ((short)(this.heat - 1));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 116 */       if (this.aspects.visSize() > 100) {
/* 117 */         spillRandom();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 125 */       if ((this.counter >= 100L) && (this.heat > 150)) {
/* 126 */         spillRandom();
/* 127 */         this.counter = 0L;
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 132 */     else if (this.tank.getFluidAmount() > 0) {
/* 133 */       drawEffects();
/*     */     }
/*     */     
/*     */ 
/* 137 */     if ((this.field_145850_b.field_72995_K) && (prevheat < 151) && (this.heat >= 151)) {
/* 138 */       this.heat = ((short)(this.heat + 1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 143 */   int prevcolor = 0;
/* 144 */   int prevx = 0;
/* 145 */   int prevy = 0;
/*     */   
/*     */   private void drawEffects()
/*     */   {
/* 149 */     if (this.heat > 150) {
/* 150 */       Thaumcraft.proxy.getFX().crucibleFroth(this.field_174879_c.func_177958_n() + 0.2F + this.field_145850_b.field_73012_v.nextFloat() * 0.6F, this.field_174879_c.func_177956_o() + getFluidHeight(), this.field_174879_c.func_177952_p() + 0.2F + this.field_145850_b.field_73012_v.nextFloat() * 0.6F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 155 */       if (this.aspects.visSize() > 100) {
/* 156 */         for (int a = 0; a < 2; a++) {
/* 157 */           Thaumcraft.proxy.getFX().crucibleFrothDown(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat());
/* 158 */           Thaumcraft.proxy.getFX().crucibleFrothDown(this.field_174879_c.func_177958_n() + 1, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat());
/* 159 */           Thaumcraft.proxy.getFX().crucibleFrothDown(this.field_174879_c.func_177958_n() + this.field_145850_b.field_73012_v.nextFloat(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p());
/* 160 */           Thaumcraft.proxy.getFX().crucibleFrothDown(this.field_174879_c.func_177958_n() + this.field_145850_b.field_73012_v.nextFloat(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 168 */     if ((this.field_145850_b.field_73012_v.nextInt(6) == 0) && (this.aspects.size() > 0))
/*     */     {
/* 170 */       int color = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.size())].getColor() + -16777216;
/* 171 */       int x = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 172 */       int y = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 173 */       this.delay = this.field_145850_b.field_73012_v.nextInt(10);
/* 174 */       this.prevcolor = color;
/* 175 */       this.prevx = x;
/* 176 */       this.prevy = y;
/*     */       
/* 178 */       Color c = new Color(color);
/* 179 */       float r = c.getRed() / 255.0F;
/* 180 */       float g = c.getGreen() / 255.0F;
/* 181 */       float b = c.getBlue() / 255.0F;
/*     */       
/* 183 */       Thaumcraft.proxy.getFX().crucibleBubble(this.field_174879_c.func_177958_n() + x / 32.0F + 0.015625F, this.field_174879_c.func_177956_o() + 0.05F + getFluidHeight(), this.field_174879_c.func_177952_p() + y / 32.0F + 0.015625F, r, g, b);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ejectItem(ItemStack items)
/*     */   {
/* 196 */     int stacks = 1;
/* 197 */     boolean first = true;
/*     */     do {
/* 199 */       ItemStack spitout = items.func_77946_l();
/* 200 */       if (spitout.field_77994_a > spitout.func_77976_d()) {
/* 201 */         spitout.field_77994_a = spitout.func_77976_d();
/*     */       }
/* 203 */       items.field_77994_a -= spitout.field_77994_a;
/*     */       
/* 205 */       EntitySpecialItem entityitem = new EntitySpecialItem(this.field_145850_b, this.field_174879_c.func_177958_n() + 0.5F, this.field_174879_c.func_177956_o() + 0.71F, this.field_174879_c.func_177952_p() + 0.5F, spitout);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 210 */       entityitem.field_70181_x = 0.10000000149011612D;
/* 211 */       entityitem.field_70159_w = (first ? 0.0D : (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01F);
/* 212 */       entityitem.field_70179_y = (first ? 0.0D : (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01F);
/* 213 */       this.field_145850_b.func_72838_d(entityitem);
/*     */       
/* 215 */       first = false;
/* 216 */     } while (items.field_77994_a > 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack attemptSmelt(ItemStack item, String username)
/*     */   {
/* 222 */     boolean bubble = false;
/* 223 */     boolean craftDone = false;
/* 224 */     int stacksize = item.field_77994_a;
/* 225 */     for (int a = 0; a < stacksize; a++)
/*     */     {
/*     */ 
/*     */ 
/* 229 */       CrucibleRecipe rc = ThaumcraftCraftingManager.findMatchingCrucibleRecipe(username, this.aspects, item);
/*     */       
/* 231 */       if ((rc != null) && (this.tank.getFluidAmount() > 0)) {
/* 232 */         ItemStack out = rc.getRecipeOutput().func_77946_l();
/*     */         
/* 234 */         EntityPlayer p = this.field_145850_b.func_72924_a(username);
/* 235 */         if (p != null) {
/* 236 */           FMLCommonHandler.instance().firePlayerCraftingEvent(p, out, new InventoryFake(new ItemStack[] { item }));
/*     */         }
/*     */         
/* 239 */         this.aspects = rc.removeMatching(this.aspects);
/* 240 */         this.tank.drain(50, true);
/* 241 */         ejectItem(out);
/* 242 */         craftDone = true;
/* 243 */         stacksize--;
/* 244 */         this.counter = -250L;
/*     */       }
/*     */       else {
/* 247 */         AspectList ot = ThaumcraftCraftingManager.getObjectTags(item);
/* 248 */         if ((ot != null) && (ot.size() != 0))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 257 */           for (Aspect tag : ot.getAspects()) {
/* 258 */             this.aspects.add(tag, ot.getAmount(tag));
/*     */           }
/*     */           
/* 261 */           bubble = true;
/* 262 */           stacksize--;
/* 263 */           this.counter = -150L;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 269 */     if (bubble) {
/* 270 */       this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "thaumcraft:bubble", 0.2F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F);
/* 271 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 272 */       this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.crucible, 2, 1);
/*     */     }
/* 274 */     if (craftDone) {
/* 275 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 276 */       this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.crucible, 99, 0);
/*     */     }
/*     */     
/* 279 */     func_70296_d();
/*     */     
/* 281 */     if (stacksize <= 0) {
/* 282 */       return null;
/*     */     }
/* 284 */     item.field_77994_a = stacksize;
/* 285 */     return item;
/*     */   }
/*     */   
/*     */   public void attemptSmelt(EntityItem entity)
/*     */   {
/* 290 */     ItemStack item = entity.func_92059_d();
/* 291 */     NBTTagCompound itemData = entity.getEntityData();
/* 292 */     String username = itemData.func_74779_i("thrower");
/* 293 */     ItemStack res = attemptSmelt(item, username);
/* 294 */     if ((res == null) || (res.field_77994_a <= 0)) {
/* 295 */       entity.func_70106_y();
/*     */     } else {
/* 297 */       item.field_77994_a = res.field_77994_a;
/* 298 */       entity.func_92058_a(item);
/*     */     }
/*     */   }
/*     */   
/*     */   public float getFluidHeight()
/*     */   {
/* 304 */     float base = 0.3F + 0.5F * (this.tank.getFluidAmount() / this.tank.getCapacity());
/* 305 */     float out = base + this.aspects.visSize() / 100.0F * (1.0F - base);
/* 306 */     if (out > 1.0F) out = 1.001F;
/* 307 */     if (out == 1.0F) out = 0.9999F;
/* 308 */     return out;
/*     */   }
/*     */   
/*     */   public void spillRandom() {
/* 312 */     if (this.aspects.size() > 0) {
/* 313 */       Aspect tag = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.getAspects().length)];
/* 314 */       this.aspects.remove(tag, 1);
/* 315 */       AuraHelper.pollute(this.field_145850_b, func_174877_v(), 1, true);
/*     */     }
/* 317 */     func_70296_d();
/* 318 */     this.field_145850_b.func_175689_h(func_174877_v());
/*     */   }
/*     */   
/*     */   public void spillRemnants()
/*     */   {
/* 323 */     if ((this.tank.getFluidAmount() > 0) || (this.aspects.visSize() > 0)) {
/* 324 */       this.tank.setFluid(null);
/* 325 */       if (this.aspects.visSize() > 0) {
/* 326 */         AuraHelper.pollute(this.field_145850_b, func_174877_v(), this.aspects.visSize(), true);
/*     */       }
/* 328 */       this.aspects = new AspectList();
/* 329 */       this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.crucible, 2, 5);
/* 330 */       func_70296_d();
/* 331 */       this.field_145850_b.func_175689_h(func_174877_v());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 339 */     if (i == 99)
/*     */     {
/* 341 */       if (this.field_145850_b.field_72995_K) {
/* 342 */         Thaumcraft.proxy.getFX().drawBamf(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 1.25F, this.field_174879_c.func_177952_p() + 0.5D, true, true, true);
/* 343 */         this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.5F, "thaumcraft:spill", 0.2F, 1.0F, false);
/*     */       }
/*     */       
/* 346 */       return true;
/*     */     }
/*     */     
/* 349 */     if (i == 1)
/*     */     {
/* 351 */       if (this.field_145850_b.field_72995_K) {
/* 352 */         Thaumcraft.proxy.getFX().drawBamf(this.field_174879_c.func_177984_a(), true, true, true);
/*     */       }
/* 354 */       return true;
/*     */     }
/*     */     
/* 357 */     if (i == 2)
/*     */     {
/* 359 */       this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.5F, "thaumcraft:spill", 0.2F, 1.0F, false);
/*     */       
/* 361 */       if (this.field_145850_b.field_72995_K) {
/* 362 */         for (int q = 0; q < 10; q++) {
/* 363 */           int x = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 364 */           int y = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 365 */           Thaumcraft.proxy.getFX().crucibleBoil(this.field_174879_c, this, j);
/*     */         }
/*     */       }
/*     */       
/* 369 */       return true;
/*     */     }
/* 371 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int fill(EnumFacing from, FluidStack resource, boolean doFill)
/*     */   {
/* 378 */     if ((resource != null) && (!FluidRegistry.getFluidName(resource).equals(FluidRegistry.getFluidName(FluidRegistry.WATER)))) { return 0;
/*     */     }
/* 380 */     if (doFill) {
/* 381 */       func_70296_d();
/* 382 */       this.field_145850_b.func_175689_h(func_174877_v());
/*     */     }
/*     */     
/* 385 */     return this.tank.fill(resource, doFill);
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
/*     */   {
/* 391 */     if ((resource == null) || (!resource.isFluidEqual(this.tank.getFluid())))
/*     */     {
/* 393 */       return null;
/*     */     }
/* 395 */     if (doDrain) {
/* 396 */       func_70296_d();
/* 397 */       this.field_145850_b.func_175689_h(func_174877_v());
/*     */     }
/* 399 */     return this.tank.drain(resource.amount, doDrain);
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
/*     */   {
/* 405 */     return this.tank.drain(maxDrain, doDrain);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canFill(EnumFacing from, Fluid fluid)
/*     */   {
/* 411 */     return (fluid != null) && (fluid.getID() == FluidRegistry.WATER.getID());
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canDrain(EnumFacing from, Fluid fluid)
/*     */   {
/* 417 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidTankInfo[] getTankInfo(EnumFacing from)
/*     */   {
/* 423 */     return new FluidTankInfo[] { this.tank.getInfo() };
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos pos, EnumFacing side)
/*     */   {
/* 429 */     if ((!world.field_72995_K) && (player.func_70093_af())) {
/* 430 */       spillRemnants();
/* 431 */       return true;
/*     */     }
/* 433 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 445 */     return AxisAlignedBB.func_178781_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), this.field_174879_c.func_177958_n() + 1, this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p() + 1);
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 450 */     return this.aspects;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 459 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 465 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 471 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 477 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 483 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 489 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 494 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/crafting/TileCrucible.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */