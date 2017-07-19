/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemPickaxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.blocks.TileThaumcraft;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.events.PlayerEvents;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketBoreDig;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.lib.utils.TCVec3;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class TileArcaneBore extends TileThaumcraft implements IInventory, thaumcraft.api.wands.IWandable, ITickable
/*     */ {
/*  59 */   public int spiral = 0;
/*  60 */   public float currentRadius = 0.0F;
/*  61 */   public int maxRadius = 2;
/*     */   
/*     */ 
/*  64 */   public float vRadX = 0.0F;
/*  65 */   public float vRadZ = 0.0F;
/*  66 */   public float tRadX = 0.0F;
/*  67 */   public float tRadZ = 0.0F;
/*  68 */   public float mRadX = 0.0F;
/*  69 */   public float mRadZ = 0.0F;
/*  70 */   private int count = 0;
/*  71 */   public int topRotation = 0;
/*  72 */   long soundDelay = 0L;
/*  73 */   Object beam1 = null;
/*  74 */   Object beam2 = null;
/*  75 */   int beamlength = 0;
/*     */   
/*  77 */   TileArcaneBoreBase base = null;
/*     */   
/*     */ 
/*  80 */   public ItemStack[] contents = new ItemStack[2];
/*  81 */   public int rotX = 0;
/*  82 */   public int rotZ = 0;
/*  83 */   public int tarX = 0;
/*  84 */   public int tarZ = 0;
/*  85 */   public int speedX = 0;
/*  86 */   public int speedZ = 0;
/*  87 */   public boolean hasFocus = false;
/*  88 */   public boolean hasPickaxe = false;
/*  89 */   int lastX = 0;
/*  90 */   int lastZ = 0;
/*  91 */   int lastY = 0;
/*  92 */   boolean toDig = false;
/*  93 */   int digX = 0;
/*  94 */   int digZ = 0;
/*  95 */   int digY = 0;
/*  96 */   Block digBlock = Blocks.field_150350_a;
/*  97 */   int digMd = 0;
/*  98 */   float radInc = 0.0F;
/*  99 */   int paused = 100;
/* 100 */   int maxPause = 100;
/* 101 */   long repairCounter = 0L;
/* 102 */   public boolean refresh = true;
/* 103 */   int powered = 0;
/*     */   
/* 105 */   public EnumFacing baseOrientation = EnumFacing.WEST;
/*     */   
/* 107 */   net.minecraftforge.common.util.FakePlayer fakePlayer = null;
/*     */   
/*     */ 
/*     */   public void func_73660_a()
/*     */   {
/* 112 */     if (!this.field_145850_b.field_72995_K) {
/* 113 */       if ((this.speedyTime < 20.0F) && (this.base != null) && (this.base.drawEssentia())) {
/* 114 */         getClass();this.speedyTime += 20.0F;
/* 115 */         this.powered += 20;
/*     */       }
/*     */       
/* 118 */       if ((this.speedyTime < 1.0F) && (this.powered < 5)) {
/* 119 */         this.powered += AuraHelper.drainAuraAvailable(func_145831_w(), func_174877_v(), Aspect.ENTROPY, 5);
/*     */       }
/*     */     }
/*     */     
/* 123 */     if ((!this.field_145850_b.field_72995_K) && (this.fakePlayer == null)) {
/* 124 */       this.fakePlayer = FakePlayerFactory.get((WorldServer)this.field_145850_b, new GameProfile((UUID)null, "FakeThaumcraftBore"));
/*     */     }
/*     */     
/* 127 */     if ((this.baseOrientation.func_176736_b() >= 0) || (this.refresh)) {
/* 128 */       if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177984_a()).func_177230_c() == BlocksTC.arcaneBoreBase) {
/* 129 */         this.baseOrientation = EnumFacing.UP;
/*     */       } else {
/* 131 */         this.baseOrientation = EnumFacing.DOWN;
/*     */       }
/* 133 */       this.base = null;
/*     */     }
/*     */     
/* 136 */     if ((this.field_145850_b.field_72995_K) && (this.refresh)) {
/* 137 */       setOrientation(BlockStateUtils.getFacing(func_145832_p()), true);
/* 138 */       this.refresh = false;
/*     */     }
/*     */     
/* 141 */     if (this.rotX < this.tarX) {
/* 142 */       this.rotX += this.speedX;
/* 143 */       if (this.rotX < this.tarX) {
/* 144 */         this.speedX += 1;
/*     */       } else
/* 146 */         this.speedX = ((int)(this.speedX / 3.0F));
/* 147 */     } else if (this.rotX > this.tarX) {
/* 148 */       this.rotX += this.speedX;
/* 149 */       if (this.rotX > this.tarX) {
/* 150 */         this.speedX -= 1;
/*     */       } else
/* 152 */         this.speedX = ((int)(this.speedX / 3.0F));
/*     */     } else {
/* 154 */       this.speedX = 0;
/*     */     }
/*     */     
/* 157 */     if (this.rotZ < this.tarZ) {
/* 158 */       this.rotZ += this.speedZ;
/* 159 */       if (this.rotZ < this.tarZ) {
/* 160 */         this.speedZ += 1;
/*     */       } else
/* 162 */         this.speedZ = ((int)(this.speedZ / 3.0F));
/* 163 */     } else if (this.rotZ > this.tarZ) {
/* 164 */       this.rotZ += this.speedZ;
/* 165 */       if (this.rotZ > this.tarZ) {
/* 166 */         this.speedZ -= 1;
/*     */       } else
/* 168 */         this.speedZ = ((int)(this.speedZ / 3.0F));
/*     */     } else {
/* 170 */       this.speedZ = 0;
/*     */     }
/*     */     
/*     */ 
/* 174 */     if ((gettingPower()) && (areItemsValid()))
/*     */     {
/* 176 */       dig();
/*     */ 
/*     */     }
/* 179 */     else if (this.field_145850_b.field_72995_K) {
/* 180 */       if (this.topRotation % 90 != 0)
/* 181 */         this.topRotation += Math.min(10, 90 - this.topRotation % 90);
/* 182 */       this.vRadX *= 0.9F;
/* 183 */       this.vRadZ *= 0.9F;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 188 */     if ((!this.field_145850_b.field_72995_K) && (this.hasPickaxe) && (func_70301_a(1) != null)) {
/* 189 */       if ((this.repairCounter++ % 40L == 0L) && (func_70301_a(1).func_77951_h())) {
/* 190 */         PlayerEvents.doRepair(func_70301_a(1), this.fakePlayer);
/*     */       }
/* 192 */       this.fakePlayer.field_70173_aa = ((int)this.repairCounter);
/*     */       try {
/* 194 */         func_70301_a(1).func_77945_a(this.field_145850_b, this.fakePlayer, 0, true);
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean areItemsValid() {
/* 201 */     boolean notNearBroken = true;
/* 202 */     if ((this.hasPickaxe) && (func_70301_a(1).func_77952_i() + 1 >= func_70301_a(1).func_77958_k()))
/* 203 */       notNearBroken = false;
/* 204 */     return (this.hasFocus) && (this.hasPickaxe) && (func_70301_a(1).func_77984_f()) && (notNearBroken);
/*     */   }
/*     */   
/*     */ 
/* 208 */   public int fortune = 0;
/* 209 */   public int speed = 0;
/* 210 */   public int area = 0;
/*     */   
/*     */   public void func_70296_d()
/*     */   {
/* 214 */     super.func_70296_d();
/*     */     
/* 216 */     this.fortune = 0;
/* 217 */     this.area = 0;
/* 218 */     this.speed = 0;
/*     */     
/* 220 */     if ((func_70301_a(0) != null) && ((func_70301_a(0).func_77973_b() instanceof ItemFocusExcavation))) {
/* 221 */       this.fortune = ((ItemFocusExcavation)func_70301_a(0).func_77973_b()).getUpgradeLevel(func_70301_a(0), FocusUpgradeType.treasure);
/* 222 */       this.area = ((ItemFocusExcavation)func_70301_a(0).func_77973_b()).getUpgradeLevel(func_70301_a(0), FocusUpgradeType.enlarge);
/* 223 */       this.speed += ((ItemFocusExcavation)func_70301_a(0).func_77973_b()).getUpgradeLevel(func_70301_a(0), FocusUpgradeType.potency);
/* 224 */       this.hasFocus = true;
/*     */     } else {
/* 226 */       this.hasFocus = false;
/*     */     }
/*     */     
/* 229 */     if ((func_70301_a(1) != null) && ((func_70301_a(1).func_77973_b() instanceof ItemPickaxe)))
/*     */     {
/* 231 */       this.hasPickaxe = true;
/* 232 */       int f = EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, func_70301_a(1));
/*     */       
/* 234 */       if (f > this.fortune)
/* 235 */         this.fortune = f;
/* 236 */       this.speed += EnchantmentHelper.func_77506_a(Enchantment.field_77349_p.field_77352_x, func_70301_a(1));
/*     */     }
/*     */     else {
/* 239 */       this.hasPickaxe = false;
/*     */     }
/*     */   }
/*     */   
/*     */   private void dig()
/*     */   {
/* 245 */     if ((this.rotX != this.tarX) || (this.rotZ != this.tarZ)) {
/* 246 */       if (this.field_145850_b.field_72995_K) {
/* 247 */         if (this.topRotation % 90 != 0)
/* 248 */           this.topRotation += Math.min(10, 90 - this.topRotation % 90);
/* 249 */         this.vRadX *= 0.9F;
/* 250 */         this.vRadZ *= 0.9F;
/*     */       }
/* 252 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 257 */     if (!this.field_145850_b.field_72995_K) {
/* 258 */       boolean dug = false;
/*     */       
/* 260 */       if (this.base == null) {
/* 261 */         this.base = ((TileArcaneBoreBase)this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(this.baseOrientation)));
/*     */       }
/*     */       
/* 264 */       if ((--this.count > 0) || (this.powered < 1)) return;
/* 265 */       if (this.toDig) {
/* 266 */         this.toDig = false;
/* 267 */         BlockPos digPos = new BlockPos(this.digX, this.digY, this.digZ);
/* 268 */         IBlockState digBs = this.field_145850_b.func_180495_p(digPos);
/*     */         boolean silktouch;
/* 270 */         int refining; if (!digBs.func_177230_c().isAir(this.field_145850_b, digPos)) {
/* 271 */           int tfortune = this.fortune;
/* 272 */           silktouch = false;
/*     */           
/* 274 */           if ((func_70301_a(1) != null) && (EnchantmentHelper.func_77506_a(Enchantment.field_77348_q.field_77352_x, func_70301_a(1)) > 0) && (digBs.func_177230_c().canSilkHarvest(this.field_145850_b, digPos, digBs, null)))
/*     */           {
/*     */ 
/*     */ 
/* 278 */             silktouch = true;
/* 279 */             tfortune = 0;
/*     */           }
/*     */           
/* 282 */           if ((!silktouch) && (func_70301_a(0) != null) && (((ItemFocusExcavation)func_70301_a(0).func_77973_b()).isUpgradedWith(func_70301_a(0), FocusUpgradeType.silktouch)) && (digBs.func_177230_c().canSilkHarvest(this.field_145850_b, digPos, digBs, null)))
/*     */           {
/*     */ 
/*     */ 
/* 286 */             silktouch = true;
/* 287 */             tfortune = 0;
/*     */           }
/*     */           
/* 290 */           this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.arcaneBore, 99, Block.func_149682_b(digBs.func_177230_c()) + (digBs.func_177230_c().func_176201_c(digBs) << 12));
/*     */           
/* 292 */           List<ItemStack> items = new ArrayList();
/* 293 */           if (silktouch) {
/* 294 */             ItemStack dropped = thaumcraft.common.lib.utils.BlockUtils.createStackedBlock(digBs);
/* 295 */             if (dropped != null) {
/* 296 */               items.add(dropped);
/*     */             }
/*     */           } else {
/* 299 */             items = digBs.func_177230_c().getDrops(this.field_145850_b, digPos, digBs, tfortune);
/*     */           }
/*     */           
/*     */ 
/* 303 */           List<EntityItem> targets = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_178781_a(this.digX, this.digY, this.digZ, this.digX + 1, this.digY + 1, this.digZ + 1).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */           
/* 305 */           if (targets.size() > 0) {
/* 306 */             for (EntityItem e : targets) {
/* 307 */               items.add(e.func_92059_d().func_77946_l());
/* 308 */               e.func_70106_y();
/*     */             }
/*     */           }
/* 311 */           refining = func_70301_a(1) != null ? EnumInfusionEnchantment.getInfusionEnchantmentLevel(func_70301_a(1), EnumInfusionEnchantment.REFINING) : 0;
/*     */           
/* 313 */           if ((func_70301_a(0) != null) && ((func_70301_a(0).func_77973_b() instanceof ItemFocusBasic)) && (((ItemFocusBasic)func_70301_a(0).func_77973_b()).isUpgradedWith(func_70301_a(0), ItemFocusExcavation.dowsing)))
/*     */           {
/* 315 */             refining++;
/*     */           }
/* 317 */           if (items.size() > 0) {
/* 318 */             for (ItemStack is : items) {
/* 319 */               ItemStack dropped = is.func_77946_l();
/*     */               
/* 321 */               if ((!silktouch) && (refining > 0)) {
/* 322 */                 dropped = Utils.findSpecialMiningResult(is, (refining + 1) * 0.125F, this.field_145850_b.field_73012_v);
/*     */               }
/* 324 */               if ((this.base != null) && ((this.base instanceof TileArcaneBoreBase))) {
/* 325 */                 TileEntity inventory = this.field_145850_b.func_175625_s(this.base.func_174877_v().func_177972_a(BlockStateUtils.getFacing(this.base.func_145832_p())));
/* 326 */                 if ((inventory != null) && ((inventory instanceof IInventory))) {
/* 327 */                   dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, BlockStateUtils.getFacing(this.base.func_145832_p()).func_176734_d(), true);
/*     */                 }
/*     */                 
/*     */ 
/* 331 */                 if (dropped != null) {
/* 332 */                   EntityItem ei = new EntityItem(this.field_145850_b, this.field_174879_c.func_177958_n() + 0.5D + BlockStateUtils.getFacing(this.base.func_145832_p()).func_82601_c() * 0.66D, this.field_174879_c.func_177956_o() + 0.4D + this.baseOrientation.func_96559_d(), this.field_174879_c.func_177952_p() + 0.5D + BlockStateUtils.getFacing(this.base.func_145832_p()).func_82599_e() * 0.66D, dropped.func_77946_l());
/*     */                   
/*     */ 
/*     */ 
/* 336 */                   ei.field_70159_w = (0.075F * BlockStateUtils.getFacing(this.base.func_145832_p()).func_82601_c());
/* 337 */                   ei.field_70181_x = 0.02500000037252903D;
/* 338 */                   ei.field_70179_y = (0.075F * BlockStateUtils.getFacing(this.base.func_145832_p()).func_82599_e());
/* 339 */                   this.field_145850_b.func_72838_d(ei);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 346 */         func_70299_a(1, InventoryUtils.damageItem(1, func_70301_a(1), this.field_145850_b));
/*     */         
/* 348 */         if (func_70301_a(1).field_77994_a <= 0) {
/* 349 */           func_70299_a(1, null);
/*     */         }
/* 351 */         this.field_145850_b.func_175698_g(digPos);
/*     */         
/* 353 */         if (this.base != null) {
/* 354 */           for (EnumFacing lbd : EnumFacing.field_176754_o) {
/* 355 */             TileEntity lbte = this.field_145850_b.func_175625_s(this.base.func_174877_v().func_177972_a(lbd));
/* 356 */             if ((lbte != null) && ((lbte instanceof TileLampArcane))) {
/* 357 */               int d = this.field_145850_b.field_73012_v.nextInt(32) * 2;
/* 358 */               int xx = this.field_174879_c.func_177958_n() + BlockStateUtils.getFacing(func_145832_p()).func_82601_c() + BlockStateUtils.getFacing(func_145832_p()).func_82601_c() * d;
/* 359 */               int yy = this.field_174879_c.func_177956_o() + BlockStateUtils.getFacing(func_145832_p()).func_96559_d() + BlockStateUtils.getFacing(func_145832_p()).func_96559_d() * d;
/* 360 */               int zz = this.field_174879_c.func_177952_p() + BlockStateUtils.getFacing(func_145832_p()).func_82599_e() + BlockStateUtils.getFacing(func_145832_p()).func_82599_e() * d;
/* 361 */               int p = d / 2 % 4;
/* 362 */               if (BlockStateUtils.getFacing(func_145832_p()).func_82601_c() != 0) {
/* 363 */                 zz += ((p == 1) || (p == 3) ? 0 : p == 0 ? 3 : -3);
/*     */               } else {
/* 365 */                 xx += ((p == 1) || (p == 3) ? 0 : p == 0 ? 3 : -3);
/*     */               }
/* 367 */               if ((p == 3) && (BlockStateUtils.getFacing(func_145832_p()).func_96559_d() == 0)) {
/* 368 */                 yy -= 2;
/*     */               }
/*     */               
/* 371 */               BlockPos bp = new BlockPos(xx, yy, zz);
/*     */               
/* 373 */               if ((!this.field_145850_b.func_175623_d(bp)) || (this.field_145850_b.func_180495_p(bp) == BlocksTC.effect.func_176203_a(2)) || (this.field_145850_b.func_175642_b(EnumSkyBlock.BLOCK, bp) >= 11))
/*     */                 break;
/* 375 */               this.field_145850_b.func_180501_a(bp, BlocksTC.effect.func_176203_a(2), 3); break;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 382 */         dug = true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 387 */       findNextBlockToDig();
/* 388 */       if (dug) {
/* 389 */         if (this.speedyTime > 0.0F) this.speedyTime -= 1.0F;
/* 390 */         if (this.powered > 0) this.powered -= 1;
/*     */       }
/*     */     }
/*     */     else {
/* 394 */       this.paused += 1;
/*     */       
/* 396 */       if (this.field_145850_b.func_175623_d(this.field_174879_c)) {
/* 397 */         func_145843_s();
/*     */       }
/* 399 */       if ((this.paused < this.maxPause) && (this.soundDelay < System.currentTimeMillis())) {
/* 400 */         this.soundDelay = (System.currentTimeMillis() + 1200L + this.field_145850_b.field_73012_v.nextInt(100));
/* 401 */         this.field_145850_b.func_72980_b(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, "thaumcraft:rumble", 0.25F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/*     */       
/*     */ 
/* 405 */       if ((this.beamlength > 0) && (this.paused > this.maxPause)) {
/* 406 */         this.beamlength -= 1;
/*     */       }
/* 408 */       BlockPos digPos = new BlockPos(this.digX, this.digY, this.digZ);
/*     */       
/* 410 */       if (this.toDig)
/*     */       {
/* 412 */         this.paused = 0;
/* 413 */         this.beamlength = 64;
/*     */         
/*     */ 
/* 416 */         IBlockState digBs = this.field_145850_b.func_180495_p(digPos);
/*     */         
/*     */ 
/*     */ 
/* 420 */         if (digBs.func_177230_c() != null) {
/* 421 */           this.maxPause = (10 + Math.max(10 - this.speed, (int)(digBs.func_177230_c().func_176195_g(this.field_145850_b, digPos) * 2.0F) - this.speed * 2));
/*     */         } else {
/* 423 */           this.maxPause = 20;
/*     */         }
/*     */         
/* 426 */         if (this.speedyTime <= 0.0F) { this.maxPause *= 4;
/*     */         }
/* 428 */         this.toDig = false;
/*     */         
/* 430 */         double xd = this.field_174879_c.func_177958_n() + 0.5D - (this.digX + 0.5D);
/* 431 */         double yd = this.field_174879_c.func_177956_o() + 0.5D - (this.digY + 0.5D);
/* 432 */         double zd = this.field_174879_c.func_177952_p() + 0.5D - (this.digZ + 0.5D);
/*     */         
/* 434 */         double var12 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*     */         
/* 436 */         float rx = (float)(Math.atan2(zd, xd) * 180.0D / 3.141592653589793D);
/* 437 */         float rz = (float)-(Math.atan2(yd, var12) * 180.0D / 3.141592653589793D) + 90.0F;
/*     */         
/* 439 */         this.tRadX = (MathHelper.func_76142_g(this.rotX) + rx);
/*     */         
/* 441 */         if (BlockStateUtils.getFacing(func_145832_p()).ordinal() == 5) {
/* 442 */           if (this.tRadX > 180.0F)
/* 443 */             this.tRadX -= 360.0F;
/* 444 */           if (this.tRadX < -180.0F) {
/* 445 */             this.tRadX += 360.0F;
/*     */           }
/*     */         }
/* 448 */         this.tRadZ = (rz - this.rotZ);
/* 449 */         if (BlockStateUtils.getFacing(func_145832_p()).ordinal() <= 1) {
/* 450 */           this.tRadZ += 180.0F;
/*     */           
/* 452 */           if (this.vRadX - this.tRadX >= 180.0F) {
/* 453 */             this.vRadX -= 360.0F;
/*     */           }
/* 455 */           if (this.vRadX - this.tRadX <= -180.0F) {
/* 456 */             this.vRadX += 360.0F;
/*     */           }
/*     */         }
/*     */         
/* 460 */         this.mRadX = Math.abs((this.vRadX - this.tRadX) / 6.0F);
/* 461 */         this.mRadZ = Math.abs((this.vRadZ - this.tRadZ) / 6.0F);
/*     */         
/* 463 */         if (this.speedyTime > 0.0F) this.speedyTime -= 1.0F;
/* 464 */         if (this.powered > 0) { this.powered -= 1;
/*     */         }
/*     */       }
/* 467 */       if (this.paused < this.maxPause) {
/* 468 */         if (this.vRadX < this.tRadX) {
/* 469 */           this.vRadX += this.mRadX;
/* 470 */         } else if (this.vRadX > this.tRadX) {
/* 471 */           this.vRadX -= this.mRadX;
/*     */         }
/*     */         
/* 474 */         if (this.vRadZ < this.tRadZ) {
/* 475 */           this.vRadZ += this.mRadZ;
/* 476 */         } else if (this.vRadZ > this.tRadZ) {
/* 477 */           this.vRadZ -= this.mRadZ;
/*     */         }
/*     */       } else {
/* 480 */         this.vRadX *= 0.9F;
/* 481 */         this.vRadZ *= 0.9F;
/*     */       }
/*     */       
/* 484 */       this.mRadX *= 0.9F;
/* 485 */       this.mRadZ *= 0.9F;
/*     */       
/* 487 */       float vx = this.rotX + 90 - this.vRadX;
/* 488 */       float vz = this.rotZ + 90 - this.vRadZ;
/* 489 */       float var3 = 1.0F;
/* 490 */       float dX = MathHelper.func_76126_a(vx / 180.0F * 3.1415927F) * MathHelper.func_76134_b(vz / 180.0F * 3.1415927F) * var3;
/* 491 */       float dZ = MathHelper.func_76134_b(vx / 180.0F * 3.1415927F) * MathHelper.func_76134_b(vz / 180.0F * 3.1415927F) * var3;
/* 492 */       float dY = MathHelper.func_76126_a(vz / 180.0F * 3.1415927F) * var3;
/*     */       
/* 494 */       Vec3 var13 = new Vec3(this.field_174879_c.func_177958_n() + 0.5D + dX, func_174877_v().func_177956_o() + 0.5D + dY, func_174877_v().func_177952_p() + 0.5D + dZ);
/* 495 */       Vec3 var14 = new Vec3(this.field_174879_c.func_177958_n() + 0.5D + dX * this.beamlength, func_174877_v().func_177956_o() + 0.5D + dY * this.beamlength, func_174877_v().func_177952_p() + 0.5D + dZ * this.beamlength);
/*     */       
/* 497 */       MovingObjectPosition mop = this.field_145850_b.func_147447_a(var13, var14, false, true, false);
/* 498 */       int impact = 0;
/* 499 */       float length = 64.0F;
/* 500 */       double bx = var14.field_72450_a;
/* 501 */       double by = var14.field_72448_b;
/* 502 */       double bz = var14.field_72449_c;
/* 503 */       if (mop != null) {
/* 504 */         double xd = func_174877_v().func_177958_n() + 0.5D + dX - mop.field_72307_f.field_72450_a;
/* 505 */         double yd = func_174877_v().func_177956_o() + 0.5D + dY - mop.field_72307_f.field_72448_b;
/* 506 */         double zd = func_174877_v().func_177952_p() + 0.5D + dZ - mop.field_72307_f.field_72449_c;
/*     */         
/* 508 */         bx = mop.field_72307_f.field_72450_a;
/* 509 */         by = mop.field_72307_f.field_72448_b;
/* 510 */         bz = mop.field_72307_f.field_72449_c;
/*     */         
/* 512 */         length = MathHelper.func_76133_a(xd * xd + yd * yd + zd * zd) + 0.5F;
/*     */         
/* 514 */         impact = 5;
/* 515 */         int x = MathHelper.func_76128_c(bx);
/* 516 */         int y = MathHelper.func_76128_c(by);
/* 517 */         int z = MathHelper.func_76128_c(bz);
/* 518 */         BlockPos tp = new BlockPos(x, y, z);
/* 519 */         IBlockState ts = this.field_145850_b.func_180495_p(tp);
/* 520 */         if (!this.field_145850_b.func_175623_d(tp)) {
/* 521 */           Thaumcraft.proxy.getFX().boreDigFx(x, y, z, func_174877_v().func_177958_n() + BlockStateUtils.getFacing(func_145832_p()).func_82601_c(), func_174877_v().func_177956_o() + BlockStateUtils.getFacing(func_145832_p()).func_96559_d(), func_174877_v().func_177952_p() + BlockStateUtils.getFacing(func_145832_p()).func_82599_e(), ts.func_177230_c(), ts.func_177230_c().func_176201_c(ts) >> 12 & 0xFF);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 526 */       this.topRotation += this.beamlength / 6;
/* 527 */       this.beam1 = Thaumcraft.proxy.getFX().beamBore(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o() + 0.5D, func_174877_v().func_177952_p() + 0.5D, bx, by, bz, 1, 65382, true, impact > 0 ? 2.0F : 0.0F, this.beam1, impact);
/*     */       
/*     */ 
/* 530 */       this.beam2 = Thaumcraft.proxy.getFX().beamBore(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o() + 0.5D, func_174877_v().func_177952_p() + 0.5D, bx, by, bz, 2, 16746581, false, impact > 0 ? 2.0F : 0.0F, this.beam2, impact);
/*     */       
/*     */ 
/*     */ 
/* 534 */       if ((this.field_145850_b.func_175623_d(digPos)) && (this.digBlock != Blocks.field_150350_a)) {
/* 535 */         this.field_145850_b.func_72980_b(this.digX + 0.5F, this.digY + 0.5F, this.digZ + 0.5F, this.digBlock.field_149762_H.func_150495_a(), (this.digBlock.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.digBlock.field_149762_H.func_150494_d() * 0.8F, false);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 540 */         for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(10); a++) {
/* 541 */           Thaumcraft.proxy.getFX().boreDigFx(this.digX, this.digY, this.digZ, func_174877_v().func_177958_n() + BlockStateUtils.getFacing(func_145832_p()).func_82601_c(), func_174877_v().func_177956_o() + BlockStateUtils.getFacing(func_145832_p()).func_96559_d(), func_174877_v().func_177952_p() + BlockStateUtils.getFacing(func_145832_p()).func_82599_e(), this.digBlock, this.digMd >> 12 & 0xFF);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 547 */         this.digBlock = Blocks.field_150350_a;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 552 */   int stick = 0;
/*     */   private float speedyTime;
/*     */   
/*     */   private void findNextBlockToDig() {
/* 556 */     if (this.radInc == 0.0F) { this.radInc = 1.0F;
/*     */     }
/* 558 */     int x = this.lastX;
/* 559 */     int z = this.lastZ;
/* 560 */     int y = this.lastY;
/* 561 */     if (this.stick == 0) {
/* 562 */       while ((x == this.lastX) && (z == this.lastZ) && (y == this.lastY)) {
/* 563 */         this.spiral = ((int)(this.spiral + (5.0F + (10.0F - Math.abs(this.currentRadius)) * 2.0F)));
/* 564 */         if (this.spiral >= 360) {
/* 565 */           this.spiral -= 360;
/* 566 */           this.currentRadius += this.radInc;
/* 567 */           if ((this.currentRadius >= this.maxRadius + this.area) || (this.currentRadius <= -(this.maxRadius + this.area))) {
/* 568 */             this.radInc *= -1.0F;
/*     */           }
/*     */         }
/*     */         
/* 572 */         TCVec3 vsource = TCVec3.createVectorHelper(func_174877_v().func_177958_n() + BlockStateUtils.getFacing(func_145832_p()).func_82601_c() + 0.5D, func_174877_v().func_177956_o() + BlockStateUtils.getFacing(func_145832_p()).func_96559_d() + 0.5D, func_174877_v().func_177952_p() + BlockStateUtils.getFacing(func_145832_p()).func_82599_e() + 0.5D);
/*     */         
/*     */ 
/* 575 */         TCVec3 vtar = TCVec3.createVectorHelper(0.0D, this.currentRadius, 0.0D);
/*     */         
/* 577 */         vtar.rotateAroundZ(this.spiral / 180.0F * 3.1415927F);
/* 578 */         vtar.rotateAroundY(1.5707964F * BlockStateUtils.getFacing(func_145832_p()).func_82601_c());
/*     */         
/* 580 */         vtar.rotateAroundX(1.5707964F * BlockStateUtils.getFacing(func_145832_p()).func_96559_d());
/*     */         
/* 582 */         TCVec3 vres = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
/*     */         
/*     */ 
/* 585 */         x = MathHelper.func_76128_c(vres.xCoord);
/* 586 */         y = MathHelper.func_76128_c(vres.yCoord);
/* 587 */         z = MathHelper.func_76128_c(vres.zCoord);
/*     */       }
/*     */     }
/* 590 */     this.lastX = x;
/* 591 */     this.lastZ = z;
/* 592 */     this.lastY = y;
/*     */     
/* 594 */     x += BlockStateUtils.getFacing(func_145832_p()).func_82601_c();
/* 595 */     y += BlockStateUtils.getFacing(func_145832_p()).func_96559_d();
/* 596 */     z += BlockStateUtils.getFacing(func_145832_p()).func_82599_e();
/*     */     
/* 598 */     for (int depth = 0; depth < 64; depth++)
/*     */     {
/* 600 */       x += BlockStateUtils.getFacing(func_145832_p()).func_82601_c();
/* 601 */       y += BlockStateUtils.getFacing(func_145832_p()).func_96559_d();
/* 602 */       z += BlockStateUtils.getFacing(func_145832_p()).func_82599_e();
/*     */       
/* 604 */       if (depth >= this.stick)
/*     */       {
/* 606 */         BlockPos bp = new BlockPos(x, y, z);
/* 607 */         Block block = this.field_145850_b.func_180495_p(bp).func_177230_c();
/*     */         
/* 609 */         if ((block != null) && (block.func_176195_g(this.field_145850_b, bp) < 0.0F)) {
/*     */           break;
/*     */         }
/*     */         
/* 613 */         if ((!this.field_145850_b.func_175623_d(bp)) && (block != null) && (block.func_176209_a(this.field_145850_b.func_180495_p(bp), false)) && (block.func_180640_a(this.field_145850_b, bp, this.field_145850_b.func_180495_p(bp)) != null))
/*     */         {
/*     */ 
/*     */ 
/* 617 */           this.digX = x;
/* 618 */           this.digY = y;
/* 619 */           this.digZ = z;
/*     */           
/* 621 */           this.stick = depth;
/*     */           
/* 623 */           this.count = Math.max(10 - this.speed, (int)(block.func_176195_g(this.field_145850_b, bp) * 2.0F) - this.speed * 2);
/* 624 */           if (this.speedyTime < 1.0F) this.count *= 4;
/* 625 */           this.toDig = true;
/*     */           
/* 627 */           Vec3 var13 = new Vec3(func_174877_v().func_177958_n() + 0.5D + BlockStateUtils.getFacing(func_145832_p()).func_82601_c(), func_174877_v().func_177956_o() + 0.5D + BlockStateUtils.getFacing(func_145832_p()).func_96559_d(), func_174877_v().func_177952_p() + 0.5D + BlockStateUtils.getFacing(func_145832_p()).func_82599_e());
/*     */           
/*     */ 
/*     */ 
/* 631 */           Vec3 var14 = new Vec3(this.digX + 0.5D, this.digY + 0.5D, this.digZ + 0.5D);
/*     */           
/* 633 */           MovingObjectPosition mop = this.field_145850_b.func_147447_a(var13, var14, false, true, false);
/* 634 */           if (mop != null) {
/* 635 */             block = this.field_145850_b.func_180495_p(mop.func_178782_a()).func_177230_c();
/* 636 */             if ((block.func_176195_g(this.field_145850_b, mop.func_178782_a()) > -1.0F) && (block.func_180640_a(this.field_145850_b, mop.func_178782_a(), this.field_145850_b.func_180495_p(mop.func_178782_a())) != null))
/*     */             {
/*     */ 
/* 639 */               this.count = Math.max(10 - this.speed, (int)(block.func_176195_g(this.field_145850_b, mop.func_178782_a()) * 2.0F) - this.speed * 2);
/*     */               
/* 641 */               if (this.speedyTime < 1.0F) {
/* 642 */                 this.count *= 4;
/*     */               }
/* 644 */               if ((this.digX != mop.func_178782_a().func_177958_n()) || (this.digY != mop.func_178782_a().func_177956_o()) || (this.digZ != mop.func_178782_a().func_177952_p())) {
/* 645 */                 this.stick = 0;
/*     */               }
/* 647 */               this.digX = mop.func_178782_a().func_177958_n();
/* 648 */               this.digY = mop.func_178782_a().func_177956_o();
/* 649 */               this.digZ = mop.func_178782_a().func_177952_p();
/*     */             }
/*     */           }
/* 652 */           sendDigEvent();
/* 653 */           return;
/*     */         }
/* 655 */         this.stick = 0;
/*     */       } }
/* 657 */     this.stick = 0;
/*     */   }
/*     */   
/*     */   public boolean gettingPower() {
/* 661 */     return (this.field_145850_b.func_175687_A(this.field_174879_c) > 0) || (this.field_145850_b.func_175687_A(this.field_174879_c.func_177972_a(this.baseOrientation)) > 0);
/*     */   }
/*     */   
/*     */   public void setOrientation(EnumFacing or, boolean initial)
/*     */   {
/* 666 */     ((BlockTCDevice)func_145838_q()).updateFacing(this.field_145850_b, func_174877_v(), or);
/* 667 */     this.lastX = 0;
/* 668 */     this.lastZ = 0;
/* 669 */     switch (or.ordinal()) {
/*     */     case 0: 
/* 671 */       this.tarZ = 180;
/* 672 */       this.tarX = 0;
/* 673 */       break;
/*     */     case 1: 
/* 675 */       this.tarZ = 0;
/* 676 */       this.tarX = 0;
/* 677 */       break;
/*     */     case 2: 
/* 679 */       this.tarZ = 90;
/* 680 */       this.tarX = 270;
/* 681 */       break;
/*     */     case 3: 
/* 683 */       this.tarZ = 90;
/* 684 */       this.tarX = 90;
/* 685 */       break;
/*     */     case 4: 
/* 687 */       this.tarZ = 90;
/* 688 */       this.tarX = 0;
/* 689 */       break;
/*     */     case 5: 
/* 691 */       this.tarZ = 90;
/* 692 */       this.tarX = 180;
/*     */     }
/*     */     
/*     */     
/* 696 */     if (initial) {
/* 697 */       this.rotX = this.tarX;
/* 698 */       this.rotZ = this.tarZ;
/*     */     }
/*     */     
/* 701 */     this.toDig = false;
/* 702 */     this.radInc = 0.0F;
/* 703 */     this.paused = 100;
/* 704 */     this.tRadX = 0.0F;
/* 705 */     this.tRadZ = 0.0F;
/* 706 */     this.mRadX = 0.0F;
/* 707 */     this.mRadZ = 0.0F;
/* 708 */     this.digX = 0;
/* 709 */     this.digY = 0;
/* 710 */     this.digZ = 0;
/* 711 */     if (this.field_145850_b != null) this.field_145850_b.func_175689_h(this.field_174879_c);
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/* 716 */     super.func_145839_a(nbttagcompound);
/* 717 */     this.speedyTime = nbttagcompound.func_74765_d("SpeedyTime");
/* 718 */     this.powered = ((int)Math.ceil(this.speedyTime));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/* 724 */     super.func_145841_b(nbttagcompound);
/* 725 */     nbttagcompound.func_74777_a("SpeedyTime", (short)(int)this.speedyTime);
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 730 */     NBTTagList var2 = nbttagcompound.func_150295_c("Inventory", 10);
/* 731 */     this.contents = new ItemStack[func_70302_i_()];
/*     */     
/* 733 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++) {
/* 734 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/* 735 */       int var5 = var4.func_74771_c("Slot") & 0xFF;
/*     */       
/* 737 */       if ((var5 >= 0) && (var5 < this.contents.length)) {
/* 738 */         this.contents[var5] = ItemStack.func_77949_a(var4);
/*     */       }
/*     */     }
/*     */     
/* 742 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound) {
/* 746 */     NBTTagList var2 = new NBTTagList();
/* 747 */     for (int var3 = 0; var3 < this.contents.length; var3++) {
/* 748 */       if (this.contents[var3] != null) {
/* 749 */         NBTTagCompound var4 = new NBTTagCompound();
/* 750 */         var4.func_74774_a("Slot", (byte)var3);
/* 751 */         this.contents[var3].func_77955_b(var4);
/* 752 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/* 755 */     nbttagcompound.func_74782_a("Inventory", var2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 762 */     if (i == 99) {
/*     */       try {
/* 764 */         if ((this.field_145850_b.field_72995_K) && ((j & 0xFFF) > 0)) {
/* 765 */           Block var40 = Block.func_149729_e(j & 0xFFF);
/* 766 */           if (var40 != null) {
/* 767 */             this.field_145850_b.func_72980_b(this.digX + 0.5F, this.digY + 0.5F, this.digZ + 0.5F, var40.field_149762_H.func_150495_a(), (var40.field_149762_H.func_150497_c() + 1.0F) / 2.0F, var40.field_149762_H.func_150494_d() * 0.8F, false);
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 772 */             for (int a = 0; a < Thaumcraft.proxy.getFX().particleCount(10); a++) {
/* 773 */               Thaumcraft.proxy.getFX().boreDigFx(this.digX, this.digY, this.digZ, this.field_174879_c.func_177958_n() + BlockStateUtils.getFacing(func_145832_p()).func_82601_c(), func_174877_v().func_177956_o() + BlockStateUtils.getFacing(func_145832_p()).func_96559_d(), func_174877_v().func_177952_p() + BlockStateUtils.getFacing(func_145832_p()).func_82599_e(), var40, j >> 12 & 0xFF);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception e) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 783 */       return true;
/*     */     }
/* 785 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   public void getDigEvent(BlockPos blockPos) {
/* 789 */     this.digX = blockPos.func_177958_n();
/* 790 */     this.digY = blockPos.func_177956_o();
/* 791 */     this.digZ = blockPos.func_177952_p();
/* 792 */     this.toDig = true;
/* 793 */     this.digBlock = this.field_145850_b.func_180495_p(new BlockPos(this.digX, this.digY, this.digZ)).func_177230_c();
/* 794 */     this.digMd = this.digBlock.func_176201_c(this.field_145850_b.func_180495_p(new BlockPos(this.digX, this.digY, this.digZ)));
/*     */   }
/*     */   
/*     */   public void sendDigEvent() {
/* 798 */     PacketHandler.INSTANCE.sendToAllAround(new PacketBoreDig(func_174877_v(), new BlockPos(this.digX, this.digY, this.digZ)), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.func_177502_q(), func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), 64.0D));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/* 804 */     return 2;
/*     */   }
/*     */   
/*     */   public ItemStack func_70301_a(int var1)
/*     */   {
/* 809 */     return this.contents[var1];
/*     */   }
/*     */   
/*     */   public ItemStack func_70298_a(int var1, int var2)
/*     */   {
/* 814 */     if (this.contents[var1] != null)
/*     */     {
/*     */ 
/* 817 */       if (this.contents[var1].field_77994_a <= var2) {
/* 818 */         ItemStack var3 = this.contents[var1];
/* 819 */         this.contents[var1] = null;
/* 820 */         func_70296_d();
/* 821 */         return var3;
/*     */       }
/* 823 */       ItemStack var3 = this.contents[var1].func_77979_a(var2);
/*     */       
/* 825 */       if (this.contents[var1].field_77994_a == 0) {
/* 826 */         this.contents[var1] = null;
/*     */       }
/*     */       
/* 829 */       func_70296_d();
/* 830 */       return var3;
/*     */     }
/*     */     
/* 833 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70304_b(int var1)
/*     */   {
/* 839 */     if (this.contents[var1] != null) {
/* 840 */       ItemStack var2 = this.contents[var1];
/* 841 */       this.contents[var1] = null;
/* 842 */       return var2;
/*     */     }
/* 844 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int var1, ItemStack var2)
/*     */   {
/* 850 */     this.contents[var1] = var2;
/*     */     
/* 852 */     if ((var2 != null) && (var2.field_77994_a > func_70297_j_())) {
/* 853 */       var2.field_77994_a = func_70297_j_();
/*     */     }
/*     */     
/* 856 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 862 */     return 64;
/*     */   }
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer var1)
/*     */   {
/* 867 */     return this.field_145850_b.func_175625_s(this.field_174879_c) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 875 */     if ((i == 0) && (itemstack != null) && ((itemstack.func_77973_b() instanceof ItemFocusExcavation))) return true;
/* 876 */     if ((i == 1) && (itemstack != null) && ((itemstack.func_77973_b() instanceof ItemPickaxe))) return true;
/* 877 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos pos, EnumFacing side)
/*     */   {
/* 883 */     setOrientation(side, false);
/* 884 */     player.field_70170_p.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*     */     
/* 886 */     player.func_71038_i();
/* 887 */     func_70296_d();
/* 888 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 905 */   private final int itemsPerVis = 20;
/*     */   
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */   public String func_70005_c_() {
/* 910 */     return null; }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 916 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public IChatComponent func_145748_c_()
/*     */   {
/* 922 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_174887_a_(int id)
/*     */   {
/* 940 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_174885_b(int id, int value) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_174890_g()
/*     */   {
/* 952 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_174888_l() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileArcaneBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */