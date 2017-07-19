/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.crafting.IInfusionStabiliser;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.internal.EnumWarpType;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.basic.BlockPillar;
/*     */ import thaumcraft.common.blocks.basic.BlockPillar.PillarType;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneTC;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneTC.StoneType;
/*     */ import thaumcraft.common.blocks.devices.BlockPedestal;
/*     */ import thaumcraft.common.blocks.devices.BlockPedestal.PedestalType;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.lib.events.EssentiaHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXInfusionSource;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class TileInfusionMatrix extends TileThaumcraft implements thaumcraft.api.wands.IWandable, IAspectContainer, ITickable
/*     */ {
/*  68 */   private ArrayList<BlockPos> pedestals = new ArrayList();
/*  69 */   private int dangerCount = 0;
/*  70 */   public boolean active = false;
/*  71 */   public boolean crafting = false;
/*  72 */   public boolean checkSurroundings = true;
/*  73 */   public int symmetryInstability = 0;
/*  74 */   public float costMult = 0.0F;
/*  75 */   public int instability = 0;
/*  76 */   private int cycleTime = 20;
/*     */   
/*     */ 
/*  79 */   private AspectList recipeEssentia = new AspectList();
/*  80 */   private ArrayList<ItemStack> recipeIngredients = null;
/*  81 */   private Object recipeOutput = null;
/*  82 */   private String recipePlayer = null;
/*  83 */   private String recipeOutputLabel = null;
/*  84 */   private ItemStack recipeInput = null;
/*  85 */   private int recipeInstability = 0;
/*  86 */   private int recipeXP = 0;
/*  87 */   private int recipeType = 0;
/*     */   
/*     */   public class SourceFX { public BlockPos loc;
/*     */     public int ticks;
/*     */     
/*  92 */     public SourceFX(BlockPos loc, int ticks, int color) { this.loc = loc;
/*  93 */       this.ticks = ticks;
/*  94 */       this.color = color;
/*     */     }
/*     */     
/*     */ 
/*     */     public int color;
/*     */     
/*     */     public int entity;
/*     */   }
/*     */   
/* 103 */   public HashMap<String, SourceFX> sourceFX = new HashMap();
/*     */   
/*     */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 108 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n() - 0.1D, func_174877_v().func_177956_o() - 0.1D, func_174877_v().func_177952_p() - 0.1D, func_174877_v().func_177958_n() + 1.1D, func_174877_v().func_177956_o() + 1.1D, func_174877_v().func_177952_p() + 1.1D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbtCompound)
/*     */   {
/* 116 */     this.active = nbtCompound.func_74767_n("active");
/* 117 */     this.crafting = nbtCompound.func_74767_n("crafting");
/* 118 */     this.instability = nbtCompound.func_74765_d("instability");
/* 119 */     this.recipeEssentia.readFromNBT(nbtCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbtCompound)
/*     */   {
/* 125 */     nbtCompound.func_74757_a("active", this.active);
/* 126 */     nbtCompound.func_74757_a("crafting", this.crafting);
/* 127 */     nbtCompound.func_74777_a("instability", (short)this.instability);
/* 128 */     this.recipeEssentia.writeToNBT(nbtCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 134 */     super.func_145839_a(nbtCompound);
/*     */     
/* 136 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("recipein", 10);
/* 137 */     this.recipeIngredients = new ArrayList();
/* 138 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 140 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 141 */       byte b0 = nbttagcompound1.func_74771_c("item");
/* 142 */       this.recipeIngredients.add(ItemStack.func_77949_a(nbttagcompound1));
/*     */     }
/*     */     
/* 145 */     String rot = nbtCompound.func_74779_i("rotype");
/* 146 */     if ((rot != null) && (rot.equals("@"))) {
/* 147 */       this.recipeOutput = ItemStack.func_77949_a(nbtCompound.func_74775_l("recipeout"));
/*     */     }
/* 149 */     else if (rot != null) {
/* 150 */       this.recipeOutputLabel = rot;
/* 151 */       this.recipeOutput = nbtCompound.func_74781_a("recipeout");
/*     */     }
/*     */     
/* 154 */     this.recipeInput = ItemStack.func_77949_a(nbtCompound.func_74775_l("recipeinput"));
/* 155 */     this.recipeInstability = nbtCompound.func_74762_e("recipeinst");
/* 156 */     this.recipeType = nbtCompound.func_74762_e("recipetype");
/* 157 */     this.recipeXP = nbtCompound.func_74762_e("recipexp");
/* 158 */     this.recipePlayer = nbtCompound.func_74779_i("recipeplayer");
/* 159 */     if (this.recipePlayer.isEmpty()) { this.recipePlayer = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 165 */     super.func_145841_b(nbtCompound);
/*     */     
/* 167 */     if ((this.recipeIngredients != null) && (this.recipeIngredients.size() > 0)) {
/* 168 */       NBTTagList nbttaglist = new NBTTagList();
/* 169 */       int count = 0;
/* 170 */       for (ItemStack stack : this.recipeIngredients)
/*     */       {
/* 172 */         if (stack != null)
/*     */         {
/* 174 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 175 */           nbttagcompound1.func_74774_a("item", (byte)count);
/* 176 */           stack.func_77955_b(nbttagcompound1);
/* 177 */           nbttaglist.func_74742_a(nbttagcompound1);
/* 178 */           count++;
/*     */         }
/*     */       }
/* 181 */       nbtCompound.func_74782_a("recipein", nbttaglist);
/*     */     }
/* 183 */     if ((this.recipeOutput != null) && ((this.recipeOutput instanceof ItemStack))) nbtCompound.func_74778_a("rotype", "@");
/* 184 */     if ((this.recipeOutput != null) && ((this.recipeOutput instanceof NBTBase))) {
/* 185 */       nbtCompound.func_74778_a("rotype", this.recipeOutputLabel);
/*     */     }
/*     */     
/* 188 */     if ((this.recipeOutput != null) && ((this.recipeOutput instanceof ItemStack))) {
/* 189 */       nbtCompound.func_74782_a("recipeout", ((ItemStack)this.recipeOutput).func_77955_b(new NBTTagCompound()));
/*     */     }
/* 191 */     if ((this.recipeOutput != null) && ((this.recipeOutput instanceof NBTBase))) {
/* 192 */       nbtCompound.func_74782_a("recipeout", (NBTBase)this.recipeOutput);
/*     */     }
/*     */     
/* 195 */     if (this.recipeInput != null) nbtCompound.func_74782_a("recipeinput", this.recipeInput.func_77955_b(new NBTTagCompound()));
/* 196 */     nbtCompound.func_74768_a("recipeinst", this.recipeInstability);
/* 197 */     nbtCompound.func_74768_a("recipetype", this.recipeType);
/* 198 */     nbtCompound.func_74768_a("recipexp", this.recipeXP);
/*     */     
/* 200 */     if (this.recipePlayer == null) {
/* 201 */       nbtCompound.func_74778_a("recipeplayer", "");
/*     */     } else {
/* 203 */       nbtCompound.func_74778_a("recipeplayer", this.recipePlayer);
/*     */     }
/*     */   }
/*     */   
/* 207 */   public int count = 0;
/* 208 */   public int craftCount = 0;
/*     */   public float startUp;
/* 210 */   private int countDelay = this.cycleTime / 2;
/*     */   
/*     */   public void func_73660_a()
/*     */   {
/* 214 */     this.count += 1;
/* 215 */     if (this.checkSurroundings) {
/* 216 */       this.checkSurroundings = false;
/* 217 */       getSurroundings();
/*     */     }
/* 219 */     if (this.field_145850_b.field_72995_K) {
/* 220 */       doEffects();
/*     */     } else {
/* 222 */       if ((this.count % (this.crafting ? 20 : 100) == 0) && 
/* 223 */         (!validLocation())) {
/* 224 */         this.active = false;
/* 225 */         func_70296_d();
/* 226 */         this.field_145850_b.func_175689_h(this.field_174879_c);
/* 227 */         return;
/*     */       }
/*     */       
/*     */ 
/* 231 */       if ((this.active) && (this.crafting) && (this.count % this.countDelay == 0)) {
/* 232 */         craftCycle();
/* 233 */         func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 240 */   ArrayList<ItemStack> ingredients = new ArrayList();
/*     */   
/*     */   public boolean validLocation()
/*     */   {
/* 244 */     if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(0, -2, 0)).func_177230_c() != BlocksTC.pedestal) return false;
/* 245 */     if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, 1)).func_177230_c() != BlocksTC.pillar) return false;
/* 246 */     if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, 1)).func_177230_c() != BlocksTC.pillar) return false;
/* 247 */     if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, -1)).func_177230_c() != BlocksTC.pillar) return false;
/* 248 */     if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, -1)).func_177230_c() != BlocksTC.pillar) { return false;
/*     */     }
/* 250 */     return true;
/*     */   }
/*     */   
/*     */   public void craftingStart(EntityPlayer player) {
/* 254 */     if (!validLocation()) {
/* 255 */       this.active = false;
/* 256 */       func_70296_d();
/* 257 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 258 */       return;
/*     */     }
/*     */     
/* 261 */     getSurroundings();
/* 262 */     TileEntity te = null;
/* 263 */     this.recipeInput = null;
/* 264 */     te = this.field_145850_b.func_175625_s(this.field_174879_c.func_177979_c(2));
/* 265 */     if ((te != null) && ((te instanceof TilePedestal))) {
/* 266 */       TilePedestal ped = (TilePedestal)te;
/* 267 */       if (ped.func_70301_a(0) != null) {
/* 268 */         this.recipeInput = ped.func_70301_a(0).func_77946_l();
/*     */       }
/*     */     }
/*     */     
/* 272 */     if (this.recipeInput == null) { return;
/*     */     }
/* 274 */     ArrayList<ItemStack> components = new ArrayList();
/* 275 */     for (BlockPos cc : this.pedestals) {
/* 276 */       te = this.field_145850_b.func_175625_s(cc);
/* 277 */       if ((te != null) && ((te instanceof TilePedestal))) {
/* 278 */         TilePedestal ped = (TilePedestal)te;
/* 279 */         if (ped.func_70301_a(0) != null) {
/* 280 */           components.add(ped.func_70301_a(0).func_77946_l());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 285 */     if (components.size() == 0) { return;
/*     */     }
/*     */     
/* 288 */     InfusionRecipe recipe = thaumcraft.common.lib.crafting.ThaumcraftCraftingManager.findMatchingInfusionRecipe(components, this.recipeInput, player);
/*     */     
/* 290 */     if (this.costMult < 0.5D) { this.costMult = 0.5F;
/*     */     }
/*     */     
/* 293 */     if (recipe != null) {
/* 294 */       this.recipeType = 0;
/* 295 */       this.recipeIngredients = components;
/*     */       
/* 297 */       if ((recipe.getRecipeOutput(player, this.recipeInput, components) instanceof Object[])) {
/* 298 */         Object[] obj = (Object[])recipe.getRecipeOutput(player, this.recipeInput, components);
/* 299 */         this.recipeOutputLabel = ((String)obj[0]);
/* 300 */         this.recipeOutput = ((NBTBase)obj[1]);
/*     */       } else {
/* 302 */         this.recipeOutput = recipe.getRecipeOutput(player, this.recipeInput, components);
/*     */       }
/* 304 */       this.recipeInstability = recipe.getInstability(player, this.recipeInput, components);
/* 305 */       AspectList al = recipe.getAspects(player, this.recipeInput, components);
/* 306 */       AspectList al2 = new AspectList();
/* 307 */       for (Aspect as : al.getAspects()) {
/* 308 */         if ((int)(al.getAmount(as) * this.costMult) > 0)
/* 309 */           al2.add(as, (int)(al.getAmount(as) * this.costMult));
/*     */       }
/* 311 */       this.recipeEssentia = al2;
/*     */       
/* 313 */       this.recipePlayer = player.func_70005_c_();
/* 314 */       this.instability = (this.symmetryInstability + this.recipeInstability);
/* 315 */       this.crafting = true;
/* 316 */       this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:craftstart", 0.5F, 1.0F);
/* 317 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 318 */       func_70296_d();
/* 319 */       return;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void craftCycle()
/*     */   {
/* 356 */     boolean valid = false;
/*     */     
/* 358 */     TileEntity te = this.field_145850_b.func_175625_s(this.field_174879_c.func_177979_c(2));
/* 359 */     if ((te != null) && ((te instanceof TilePedestal))) {
/* 360 */       TilePedestal ped = (TilePedestal)te;
/* 361 */       if (ped.func_70301_a(0) != null) {
/* 362 */         ItemStack i2 = ped.func_70301_a(0).func_77946_l();
/* 363 */         if (this.recipeInput.func_77952_i() == 32767) {
/* 364 */           i2.func_77964_b(32767);
/*     */         }
/* 366 */         if (ThaumcraftApiHelper.areItemStacksEqualForCrafting(i2, this.recipeInput)) { valid = true;
/*     */         }
/*     */       }
/*     */     }
/* 370 */     if ((!valid) || ((this.instability > 0) && (this.field_145850_b.field_73012_v.nextInt(500) <= this.instability)))
/*     */     {
/* 372 */       switch (this.field_145850_b.field_73012_v.nextInt(21)) {
/* 373 */       case 0: case 2: case 10: case 13:  inEvEjectItem(0); break;
/* 374 */       case 6: case 17:  inEvEjectItem(1); break;
/* 375 */       case 1: case 11:  inEvEjectItem(2); break;
/* 376 */       case 3: case 8: case 14:  inEvZap(false); break;
/* 377 */       case 5: case 16:  inEvHarm(false); break;
/* 378 */       case 12:  inEvZap(true); break;
/* 379 */       case 19:  inEvEjectItem(3); break;
/* 380 */       case 7:  inEvEjectItem(4); break;
/* 381 */       case 4: case 15:  inEvEjectItem(5); break;
/* 382 */       case 18:  inEvHarm(true); break;
/* 383 */       case 9:  this.field_145850_b.func_72876_a(null, this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, 1.5F + this.field_145850_b.field_73012_v.nextFloat(), false); break;
/* 384 */       case 20:  inEvWarp();
/*     */       }
/*     */       
/* 387 */       if (valid) { return;
/*     */       }
/*     */     }
/* 390 */     if (!valid) {
/* 391 */       this.instability = 0;
/* 392 */       this.crafting = false;
/* 393 */       this.recipeEssentia = new AspectList();
/* 394 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 395 */       this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:craftfail", 1.0F, 0.6F);
/* 396 */       func_70296_d();
/* 397 */       return;
/*     */     }
/*     */     
/* 400 */     if ((this.recipeType == 1) && (this.recipeXP > 0)) {
/* 401 */       List<EntityPlayer> targets = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1).func_72314_b(10.0D, 10.0D, 10.0D));
/*     */       
/*     */ 
/* 404 */       if ((targets != null) && (targets.size() > 0)) {
/* 405 */         for (EntityPlayer target : targets) {
/* 406 */           if ((target.field_71075_bZ.field_75098_d) || (target.field_71068_ca > 0)) {
/* 407 */             if (!target.field_71075_bZ.field_75098_d) target.func_71013_b(1);
/* 408 */             this.recipeXP -= 1;
/* 409 */             target.func_70097_a(DamageSource.field_76376_m, this.field_145850_b.field_73012_v.nextInt(2));
/* 410 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXInfusionSource(this.field_174879_c, (byte)0, (byte)0, (byte)0, target.func_145782_y()), new NetworkRegistry.TargetPoint(func_145831_w().field_73011_w.func_177502_q(), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), 32.0D));
/*     */             
/*     */ 
/*     */ 
/* 414 */             this.field_145850_b.func_72956_a(target, "random.fizz", 1.0F, 2.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F);
/* 415 */             this.countDelay = this.cycleTime;
/* 416 */             return;
/*     */           }
/*     */         }
/* 419 */         Aspect[] ingEss = this.recipeEssentia.getAspects();
/* 420 */         if ((ingEss != null) && (ingEss.length > 0) && (this.field_145850_b.field_73012_v.nextInt(3) == 0)) {
/* 421 */           Aspect as = ingEss[this.field_145850_b.field_73012_v.nextInt(ingEss.length)];
/* 422 */           this.recipeEssentia.add(as, 1);
/* 423 */           if (this.field_145850_b.field_73012_v.nextInt(50 - this.recipeInstability * 2) == 0) this.instability += 1;
/* 424 */           if (this.instability > 25) this.instability = 25;
/* 425 */           this.field_145850_b.func_175689_h(this.field_174879_c);
/* 426 */           func_70296_d();
/*     */         }
/*     */       }
/* 429 */       return;
/*     */     }
/*     */     
/* 432 */     if ((this.recipeType == 1) && (this.recipeXP == 0)) { this.countDelay = (this.cycleTime / 2);
/*     */     }
/* 434 */     if (this.recipeEssentia.visSize() > 0) {
/* 435 */       for (Aspect aspect : this.recipeEssentia.getAspects()) {
/* 436 */         int na = this.recipeEssentia.getAmount(aspect);
/* 437 */         if (na > 0) {
/* 438 */           if (EssentiaHandler.drainEssentia(this, aspect, null, 12, na > 1 ? this.countDelay : 0))
/*     */           {
/* 440 */             this.recipeEssentia.reduce(aspect, 1);
/* 441 */             this.field_145850_b.func_175689_h(this.field_174879_c);
/* 442 */             func_70296_d();
/* 443 */             return;
/*     */           }
/*     */           
/*     */ 
/* 447 */           if (this.field_145850_b.field_73012_v.nextInt(100 - this.recipeInstability * 3) == 0) this.instability += 1;
/* 448 */           if (this.instability > 25) this.instability = 25;
/* 449 */           this.field_145850_b.func_175689_h(this.field_174879_c);
/* 450 */           func_70296_d();
/*     */         }
/*     */       }
/* 453 */       this.checkSurroundings = true;
/* 454 */       return;
/*     */     }
/*     */     
/* 457 */     if (this.recipeIngredients.size() > 0) {
/* 458 */       for (int a = 0; a < this.recipeIngredients.size(); a++) {
/* 459 */         for (BlockPos cc : this.pedestals) {
/* 460 */           te = this.field_145850_b.func_175625_s(cc);
/* 461 */           if ((te != null) && ((te instanceof TilePedestal)) && (((TilePedestal)te).func_70301_a(0) != null) && 
/* 462 */             (ThaumcraftApiHelper.areItemStacksEqualForCrafting(((TilePedestal)te).func_70301_a(0), this.recipeIngredients.get(a))))
/*     */           {
/* 464 */             if (this.itemCount == 0) {
/* 465 */               this.itemCount = 5;
/* 466 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXInfusionSource(this.field_174879_c, (byte)(this.field_174879_c.func_177958_n() - cc.func_177958_n()), (byte)(this.field_174879_c.func_177956_o() - cc.func_177956_o()), (byte)(this.field_174879_c.func_177952_p() - cc.func_177952_p()), 0), new NetworkRegistry.TargetPoint(func_145831_w().field_73011_w.func_177502_q(), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), 32.0D));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             }
/* 472 */             else if (this.itemCount-- <= 1)
/*     */             {
/* 474 */               ItemStack is = ((TilePedestal)te).func_70301_a(0).func_77973_b().getContainerItem(((TilePedestal)te).func_70301_a(0));
/*     */               
/* 476 */               ((TilePedestal)te).func_70299_a(0, is == null ? null : is.func_77946_l());
/* 477 */               ((TilePedestal)te).func_70296_d();
/* 478 */               this.recipeIngredients.remove(a);
/* 479 */               func_70296_d();
/*     */             }
/* 481 */             return;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 486 */         Aspect[] ingEss = this.recipeEssentia.getAspects();
/* 487 */         if ((ingEss != null) && (ingEss.length > 0) && (this.field_145850_b.field_73012_v.nextInt(1 + a) == 0)) {
/* 488 */           Aspect as = ingEss[this.field_145850_b.field_73012_v.nextInt(ingEss.length)];
/* 489 */           this.recipeEssentia.add(as, 1);
/* 490 */           if (this.field_145850_b.field_73012_v.nextInt(50 - this.recipeInstability * 2) == 0) this.instability += 1;
/* 491 */           if (this.instability > 25) this.instability = 25;
/* 492 */           this.field_145850_b.func_175689_h(this.field_174879_c);
/* 493 */           func_70296_d();
/*     */         }
/*     */       }
/*     */       
/* 497 */       return;
/*     */     }
/* 499 */     this.instability = 0;
/* 500 */     this.crafting = false;
/* 501 */     craftingFinish(this.recipeOutput, this.recipeOutputLabel);
/* 502 */     this.recipeOutput = null;
/* 503 */     this.field_145850_b.func_175689_h(this.field_174879_c);
/* 504 */     func_70296_d();
/*     */   }
/*     */   
/*     */   private void inEvZap(boolean all)
/*     */   {
/* 509 */     List<EntityLivingBase> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1).func_72314_b(10.0D, 10.0D, 10.0D));
/*     */     
/*     */ 
/* 512 */     if ((targets != null) && (targets.size() > 0))
/* 513 */       for (EntityLivingBase target : targets) {
/* 514 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(this.field_174879_c, target, 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.func_177502_q(), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), 32.0D));
/*     */         
/*     */ 
/* 517 */         target.func_70097_a(DamageSource.field_76376_m, 4 + this.field_145850_b.field_73012_v.nextInt(4));
/* 518 */         if (!all)
/*     */           break;
/*     */       }
/*     */   }
/*     */   
/*     */   private void inEvHarm(boolean all) {
/* 524 */     List<EntityLivingBase> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1).func_72314_b(10.0D, 10.0D, 10.0D));
/*     */     
/*     */ 
/* 527 */     if ((targets != null) && (targets.size() > 0))
/* 528 */       for (EntityLivingBase target : targets) {
/* 529 */         if (this.field_145850_b.field_73012_v.nextBoolean()) {
/* 530 */           target.func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), 120, 0, false, true));
/*     */         } else {
/* 532 */           PotionEffect pe = new PotionEffect(PotionVisExhaust.instance.func_76396_c(), 2400, 0, true, true);
/* 533 */           pe.getCurativeItems().clear();
/* 534 */           target.func_70690_d(pe);
/*     */         }
/* 536 */         if (!all)
/*     */           break;
/*     */       }
/*     */   }
/*     */   
/*     */   private void inEvWarp() {
/* 542 */     List<EntityPlayer> targets = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1).func_72314_b(10.0D, 10.0D, 10.0D));
/*     */     
/*     */ 
/* 545 */     if ((targets != null) && (targets.size() > 0)) {
/* 546 */       EntityPlayer target = (EntityPlayer)targets.get(this.field_145850_b.field_73012_v.nextInt(targets.size()));
/* 547 */       if (this.field_145850_b.field_73012_v.nextFloat() < 0.25F) {
/* 548 */         ResearchHelper.addWarpToPlayer(target, 1, EnumWarpType.NORMAL);
/*     */       } else {
/* 550 */         ResearchHelper.addWarpToPlayer(target, 2 + this.field_145850_b.field_73012_v.nextInt(4), EnumWarpType.TEMPORARY);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void inEvEjectItem(int type)
/*     */   {
/* 557 */     int q = 0;
/* 558 */     while ((q < 50) && (this.pedestals.size() > 0)) {
/* 559 */       BlockPos cc = (BlockPos)this.pedestals.get(this.field_145850_b.field_73012_v.nextInt(this.pedestals.size()));
/* 560 */       TileEntity te = this.field_145850_b.func_175625_s(cc);
/* 561 */       if ((te != null) && ((te instanceof TilePedestal)) && (((TilePedestal)te).func_70301_a(0) != null))
/*     */       {
/* 563 */         if ((type < 3) || (type == 5)) {
/* 564 */           InventoryUtils.dropItems(this.field_145850_b, cc);
/*     */         } else {
/* 566 */           ((TilePedestal)te).func_70299_a(0, null);
/*     */         }
/* 568 */         if ((type == 1) || (type == 3))
/*     */         {
/* 570 */           this.field_145850_b.func_72908_a(cc.func_177958_n(), cc.func_177956_o(), cc.func_177952_p(), "game.neutral.swim", 0.3F, 1.0F);
/*     */         }
/* 572 */         else if ((type == 2) || (type == 4)) {
/* 573 */           int a = 5 + this.field_145850_b.field_73012_v.nextInt(5);
/* 574 */           AuraHelper.pollute(this.field_145850_b, cc, a, true);
/*     */         }
/* 576 */         else if (type == 5) {
/* 577 */           this.field_145850_b.func_72876_a(null, cc.func_177958_n() + 0.5F, cc.func_177956_o() + 0.5F, cc.func_177952_p() + 0.5F, 1.0F, false);
/*     */         }
/* 579 */         this.field_145850_b.func_175641_c(cc, BlocksTC.pedestal, 11, 0);
/* 580 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(this.field_174879_c, cc.func_177984_a(), 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.func_177502_q(), cc.func_177958_n(), cc.func_177956_o(), cc.func_177952_p(), 32.0D));
/*     */         
/*     */ 
/* 583 */         return;
/*     */       }
/* 585 */       q++;
/*     */     }
/*     */   }
/*     */   
/* 589 */   int itemCount = 0;
/*     */   
/*     */   public void craftingFinish(Object out, String label) {
/* 592 */     TileEntity te = this.field_145850_b.func_175625_s(this.field_174879_c.func_177979_c(2));
/* 593 */     if ((te != null) && ((te instanceof TilePedestal))) {
/* 594 */       if ((out instanceof ItemStack)) {
/* 595 */         ((TilePedestal)te).setInventorySlotContentsFromInfusion(0, ((ItemStack)out).func_77946_l());
/*     */       }
/* 597 */       else if ((out instanceof NBTBase)) {
/* 598 */         ItemStack temp = ((TilePedestal)te).func_70301_a(0);
/* 599 */         NBTBase tag = (NBTBase)out;
/* 600 */         temp.func_77983_a(label, tag);
/* 601 */         this.field_145850_b.func_175689_h(this.field_174879_c.func_177979_c(2));
/* 602 */         te.func_70296_d();
/*     */       }
/* 604 */       else if ((out instanceof Enchantment)) {
/* 605 */         ItemStack temp = ((TilePedestal)te).func_70301_a(0);
/* 606 */         Map enchantments = EnchantmentHelper.func_82781_a(temp);
/* 607 */         enchantments.put(Integer.valueOf(((Enchantment)out).field_77352_x), Integer.valueOf(EnchantmentHelper.func_77506_a(((Enchantment)out).field_77352_x, temp) + 1));
/*     */         
/*     */ 
/* 610 */         EnchantmentHelper.func_82782_a(enchantments, temp);
/* 611 */         this.field_145850_b.func_175689_h(this.field_174879_c.func_177979_c(2));
/* 612 */         te.func_70296_d();
/*     */       }
/*     */       
/* 615 */       if (this.recipePlayer != null) {
/* 616 */         EntityPlayer p = this.field_145850_b.func_72924_a(this.recipePlayer);
/* 617 */         if (p != null) {
/* 618 */           FMLCommonHandler.instance().firePlayerCraftingEvent(p, ((TilePedestal)te).func_70301_a(0), new InventoryFake(this.recipeIngredients));
/*     */         }
/*     */       }
/*     */       
/* 622 */       this.recipeEssentia = new AspectList();
/* 623 */       this.field_145850_b.func_175689_h(this.field_174879_c);
/* 624 */       func_70296_d();
/* 625 */       this.field_145850_b.func_175641_c(this.field_174879_c.func_177979_c(2), BlocksTC.pedestal, 12, 0);
/* 626 */       this.field_145850_b.func_72908_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p() + 0.5D, "thaumcraft:wand", 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void getSurroundings() {
/* 631 */     ArrayList<BlockPos> stuff = new ArrayList();
/* 632 */     this.pedestals.clear();
/*     */     try
/*     */     {
/* 635 */       for (int xx = -12; xx <= 12; xx++) {
/* 636 */         for (int zz = -12; zz <= 12; zz++) {
/* 637 */           boolean skip = false;
/* 638 */           for (int yy = -5; yy <= 10; yy++) {
/* 639 */             if ((xx != 0) || (zz != 0)) {
/* 640 */               int x = this.field_174879_c.func_177958_n() + xx;
/* 641 */               int y = this.field_174879_c.func_177956_o() - yy;
/* 642 */               int z = this.field_174879_c.func_177952_p() + zz;
/* 643 */               BlockPos bp = new BlockPos(x, y, z);
/* 644 */               TileEntity te = this.field_145850_b.func_175625_s(bp);
/* 645 */               if ((!skip) && (yy > 0) && (Math.abs(xx) <= 8) && (Math.abs(zz) <= 8) && (te != null) && ((te instanceof TilePedestal)))
/*     */               {
/* 647 */                 this.pedestals.add(bp);
/* 648 */                 skip = true;
/*     */               } else {
/* 650 */                 Block bi = this.field_145850_b.func_180495_p(bp).func_177230_c();
/*     */                 try {
/* 652 */                   if ((bi != null) && ((bi == Blocks.field_150465_bP) || (((bi instanceof IInfusionStabiliser)) && (((IInfusionStabiliser)bi).canStabaliseInfusion(func_145831_w(), bp)))))
/*     */                   {
/* 654 */                     stuff.add(bp); }
/*     */                 } catch (Exception e) {}
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 661 */       this.cycleTime = 20;
/* 662 */       this.symmetryInstability = 0;
/* 663 */       this.costMult = 1.0F;
/* 664 */       if ((this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, -1)).func_177230_c() == BlocksTC.pillar) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, -1)).func_177230_c() == BlocksTC.pillar) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, 1)).func_177230_c() == BlocksTC.pillar) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, 1)).func_177230_c() == BlocksTC.pillar))
/*     */       {
/*     */ 
/* 667 */         if ((this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, -1)).func_177229_b(BlockPillar.TYPE) == BlockPillar.PillarType.ANCIENT) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, -1)).func_177229_b(BlockPillar.TYPE) == BlockPillar.PillarType.ANCIENT) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, 1)).func_177229_b(BlockPillar.TYPE) == BlockPillar.PillarType.ANCIENT) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, 1)).func_177229_b(BlockPillar.TYPE) == BlockPillar.PillarType.ANCIENT))
/*     */         {
/*     */ 
/*     */ 
/* 671 */           this.cycleTime -= 2;
/* 672 */           this.costMult -= 0.1F;
/* 673 */           this.symmetryInstability += 2;
/*     */         }
/*     */         
/* 676 */         if ((this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, -1)).func_177229_b(BlockPillar.TYPE) == BlockPillar.PillarType.ELDRITCH) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, -1)).func_177229_b(BlockPillar.TYPE) == BlockPillar.PillarType.ELDRITCH) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, 1)).func_177229_b(BlockPillar.TYPE) == BlockPillar.PillarType.ELDRITCH) && (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, 1)).func_177229_b(BlockPillar.TYPE) == BlockPillar.PillarType.ELDRITCH))
/*     */         {
/*     */ 
/*     */ 
/* 680 */           this.cycleTime -= 4;
/* 681 */           this.costMult += 0.05F;
/* 682 */           this.symmetryInstability -= 4;
/*     */         }
/*     */       }
/*     */       
/* 686 */       int[] xm = { -1, 1, 1, -1 };int[] zm = { -1, -1, 1, 1 };
/* 687 */       for (int a = 0; a < 4; a++) {
/* 688 */         if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(xm[a], -3, zm[a])).func_177230_c() == BlocksTC.stone) {
/* 689 */           Comparable c = this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(xm[a], -3, zm[a])).func_177229_b(BlockStoneTC.VARIANT);
/* 690 */           if (c == BlockStoneTC.StoneType.MATRIX_SPEED) {
/* 691 */             this.cycleTime -= 2;
/* 692 */             this.costMult += 0.01F;
/*     */           }
/* 694 */           if (c == BlockStoneTC.StoneType.MATRIX_COST) {
/* 695 */             this.cycleTime += 1;
/* 696 */             this.costMult -= 0.02F;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 701 */       this.countDelay = (this.cycleTime / 2);
/*     */       
/* 703 */       int apc = 0;
/* 704 */       for (BlockPos cc : this.pedestals) {
/* 705 */         boolean items = false;
/* 706 */         int x = this.field_174879_c.func_177958_n() - cc.func_177958_n();
/* 707 */         int z = this.field_174879_c.func_177952_p() - cc.func_177952_p();
/*     */         
/* 709 */         TileEntity te = this.field_145850_b.func_175625_s(cc);
/* 710 */         if ((te != null) && ((te instanceof TilePedestal))) {
/* 711 */           this.symmetryInstability += 2;
/* 712 */           if (((TilePedestal)te).func_70301_a(0) != null) {
/* 713 */             this.symmetryInstability += 1;
/* 714 */             if (this.field_145850_b.func_180495_p(cc).func_177229_b(BlockPedestal.VARIANT) == BlockPedestal.PedestalType.ELDRITCH) this.symmetryInstability += 1;
/* 715 */             items = true;
/*     */           }
/* 717 */           if (this.field_145850_b.func_180495_p(cc).func_177229_b(BlockPedestal.VARIANT) == BlockPedestal.PedestalType.ELDRITCH) {
/* 718 */             this.costMult += 0.0025F;
/*     */           }
/* 720 */           if (this.field_145850_b.func_180495_p(cc).func_177229_b(BlockPedestal.VARIANT) == BlockPedestal.PedestalType.ANCIENT) {
/* 721 */             this.costMult -= 0.01F;
/* 722 */             apc++;
/*     */           }
/*     */         }
/*     */         
/* 726 */         int xx = this.field_174879_c.func_177958_n() + x;
/* 727 */         int zz = this.field_174879_c.func_177952_p() + z;
/* 728 */         BlockPos cc2 = new BlockPos(xx, cc.func_177956_o(), zz);
/* 729 */         te = this.field_145850_b.func_175625_s(cc2);
/* 730 */         if ((te != null) && ((te instanceof TilePedestal)) && (this.field_145850_b.func_180495_p(cc2) == this.field_145850_b.func_180495_p(cc))) {
/* 731 */           this.symmetryInstability -= 2;
/* 732 */           if ((((TilePedestal)te).func_70301_a(0) != null) && (items)) {
/* 733 */             this.symmetryInstability -= 1;
/* 734 */             if (this.field_145850_b.func_180495_p(cc2).func_177229_b(BlockPedestal.VARIANT) == BlockPedestal.PedestalType.ELDRITCH) this.symmetryInstability -= 2;
/*     */           }
/*     */         }
/*     */       }
/* 738 */       this.symmetryInstability += apc / 4;
/*     */       
/* 740 */       float sym = 0.0F;
/* 741 */       for (BlockPos cc : stuff) {
/* 742 */         boolean items = false;
/* 743 */         int x = this.field_174879_c.func_177958_n() - cc.func_177958_n();
/* 744 */         int z = this.field_174879_c.func_177952_p() - cc.func_177952_p();
/*     */         
/* 746 */         Block bi = this.field_145850_b.func_180495_p(cc).func_177230_c();
/*     */         try {
/* 748 */           if ((bi == Blocks.field_150465_bP) || (((bi instanceof IInfusionStabiliser)) && (((IInfusionStabiliser)bi).canStabaliseInfusion(func_145831_w(), cc))))
/*     */           {
/* 750 */             sym += 0.1F;
/*     */           }
/*     */         }
/*     */         catch (Exception e) {}
/* 754 */         int xx = this.field_174879_c.func_177958_n() + x;
/* 755 */         int zz = this.field_174879_c.func_177952_p() + z;
/* 756 */         bi = this.field_145850_b.func_180495_p(new BlockPos(xx, cc.func_177956_o(), zz)).func_177230_c();
/*     */         try {
/* 758 */           if ((bi == Blocks.field_150465_bP) || (((bi instanceof IInfusionStabiliser)) && (((IInfusionStabiliser)bi).canStabaliseInfusion(func_145831_w(), cc))))
/*     */           {
/* 760 */             sym -= 0.2F;
/*     */           }
/*     */         }
/*     */         catch (Exception e) {}
/*     */       }
/* 765 */       this.symmetryInstability = ((int)(this.symmetryInstability + sym));
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public boolean onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos pos, EnumFacing side) {
/* 771 */     if ((!world.field_72995_K) && (this.active) && (!this.crafting)) {
/* 772 */       craftingStart(player);
/* 773 */       return false;
/*     */     }
/* 775 */     if ((!world.field_72995_K) && (!this.active) && (validLocation())) {
/* 776 */       this.active = true;
/* 777 */       this.field_145850_b.func_175689_h(pos);
/* 778 */       func_70296_d();
/* 779 */       return false;
/*     */     }
/* 781 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void doEffects()
/*     */   {
/* 799 */     if (this.crafting) {
/* 800 */       if (this.craftCount == 0) {
/* 801 */         this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:infuserstart", 0.5F, 1.0F, false);
/* 802 */       } else if ((this.craftCount == 0) || (this.craftCount % 65 == 0)) {
/* 803 */         this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), "thaumcraft:infuser", 0.5F, 1.0F, false);
/*     */       }
/* 805 */       this.craftCount += 1;
/* 806 */       Thaumcraft.proxy.getFX().blockRunes(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() - 2, this.field_174879_c.func_177952_p(), 0.5F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, 0.1F, 0.7F + this.field_145850_b.field_73012_v.nextFloat() * 0.3F, 25, -0.03F);
/*     */     }
/* 808 */     else if (this.craftCount > 0) {
/* 809 */       this.craftCount -= 2;
/* 810 */       if (this.craftCount < 0) this.craftCount = 0;
/* 811 */       if (this.craftCount > 50) { this.craftCount = 50;
/*     */       }
/*     */     }
/* 814 */     if ((this.active) && (this.startUp != 1.0F)) {
/* 815 */       if (this.startUp < 1.0F) this.startUp += Math.max(this.startUp / 10.0F, 0.001F);
/* 816 */       if (this.startUp > 0.999D) { this.startUp = 1.0F;
/*     */       }
/*     */     }
/* 819 */     if ((!this.active) && (this.startUp > 0.0F)) {
/* 820 */       if (this.startUp > 0.0F) this.startUp -= this.startUp / 10.0F;
/* 821 */       if (this.startUp < 0.001D) this.startUp = 0.0F;
/*     */     }
/* 823 */     for (String fxk : (String[])this.sourceFX.keySet().toArray(new String[0])) {
/* 824 */       SourceFX fx = (SourceFX)this.sourceFX.get(fxk);
/* 825 */       if (fx.ticks <= 0) {
/* 826 */         this.sourceFX.remove(fxk);
/*     */       }
/*     */       else {
/* 829 */         if (fx.loc.equals(this.field_174879_c)) {
/* 830 */           Entity player = this.field_145850_b.func_73045_a(fx.color);
/* 831 */           if (player != null) {
/* 832 */             for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(2); a++) {
/* 833 */               Thaumcraft.proxy.getFX().drawInfusionParticles4(player.field_70165_t + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * player.field_70130_N, player.func_174813_aQ().field_72338_b + this.field_145850_b.field_73012_v.nextFloat() * player.field_70131_O, player.field_70161_v + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * player.field_70130_N, this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p());
/*     */             }
/*     */             
/*     */           }
/*     */           
/*     */         }
/*     */         else
/*     */         {
/* 841 */           TileEntity tile = this.field_145850_b.func_175625_s(fx.loc);
/* 842 */           if ((tile instanceof TilePedestal)) {
/* 843 */             ItemStack is = ((TilePedestal)tile).func_70301_a(0);
/* 844 */             if (is != null) {
/* 845 */               if (this.field_145850_b.field_73012_v.nextInt(3) == 0) {
/* 846 */                 Thaumcraft.proxy.getFX().drawInfusionParticles3(fx.loc.func_177958_n() + this.field_145850_b.field_73012_v.nextFloat(), fx.loc.func_177956_o() + this.field_145850_b.field_73012_v.nextFloat() + 1.0F, fx.loc.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat(), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p());
/*     */ 
/*     */               }
/*     */               else
/*     */               {
/* 851 */                 Item bi = is.func_77973_b();
/* 852 */                 int md = is.func_77952_i();
/* 853 */                 if ((bi instanceof ItemBlock)) {
/* 854 */                   for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(2); a++) {
/* 855 */                     Thaumcraft.proxy.getFX().drawInfusionParticles2(fx.loc.func_177958_n() + this.field_145850_b.field_73012_v.nextFloat(), fx.loc.func_177956_o() + this.field_145850_b.field_73012_v.nextFloat() + 1.0F, fx.loc.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat(), this.field_174879_c, Block.func_149634_a(bi), md);
/*     */                   }
/*     */                   
/*     */                 }
/*     */                 else
/*     */                 {
/* 861 */                   for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(2); a++) {
/* 862 */                     Thaumcraft.proxy.getFX().drawInfusionParticles1(fx.loc.func_177958_n() + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, fx.loc.func_177956_o() + 1.23F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, fx.loc.func_177952_p() + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, this.field_174879_c, bi, md);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 870 */             fx.ticks = 0;
/*     */           } }
/* 872 */         fx.ticks -= 1;
/* 873 */         this.sourceFX.put(fxk, fx);
/*     */       }
/*     */     }
/*     */     
/* 877 */     if ((this.crafting) && (this.instability > 0) && (this.field_145850_b.field_73012_v.nextInt(200) <= this.instability)) {
/* 878 */       float xx = this.field_174879_c.func_177958_n() + 0.5F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 4.0F;
/* 879 */       float zz = this.field_174879_c.func_177952_p() + 0.5F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 4.0F;
/* 880 */       int yy = this.field_174879_c.func_177956_o() - 2;
/* 881 */       while (!this.field_145850_b.func_175623_d(new BlockPos(xx, yy, zz))) yy++;
/* 882 */       Thaumcraft.proxy.getFX().arcLightning(this.field_174879_c.func_177958_n() + 0.5F, this.field_174879_c.func_177956_o() + 0.5F, this.field_174879_c.func_177952_p() + 0.5F, xx, yy, zz, 0.8F, 0.1F, 1.0F, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/* 889 */     return this.recipeEssentia;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 899 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 904 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 909 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 914 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 919 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 924 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 929 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/crafting/TileInfusionMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */