/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldSettings.GameType;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ public class ItemFocusExcavation extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusExcavation()
/*     */   {
/*  37 */     super("excavate", 409606);
/*     */   }
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  42 */     AspectList cost = new AspectList().add(Aspect.EARTH, 1);
/*     */     
/*  44 */     if (isUpgradedWith(itemstack, FocusUpgradeType.silktouch)) {
/*  45 */       AspectList cost2 = new AspectList();
/*  46 */       cost2 = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
/*  47 */       cost2.add(cost);
/*  48 */       cost2.add(Aspect.EARTH, getUpgradeLevel(itemstack, FocusUpgradeType.enlarge));
/*  49 */       return cost2;
/*     */     }
/*  51 */     if (isUpgradedWith(itemstack, dowsing)) {
/*  52 */       AspectList cost2 = new AspectList();
/*  53 */       cost2 = new AspectList().add(Aspect.FIRE, 1).add(Aspect.ORDER, 1);
/*  54 */       cost2.add(cost);
/*  55 */       cost2.add(Aspect.EARTH, getUpgradeLevel(itemstack, FocusUpgradeType.enlarge));
/*  56 */       return cost2;
/*     */     }
/*     */     
/*  59 */     cost.add(Aspect.EARTH, getUpgradeLevel(itemstack, FocusUpgradeType.enlarge));
/*  60 */     return cost;
/*     */   }
/*     */   
/*     */   public boolean isVisCostPerTick(ItemStack itemstack)
/*     */   {
/*  65 */     return true;
/*     */   }
/*     */   
/*  68 */   static HashMap<String, Long> soundDelay = new HashMap();
/*  69 */   static HashMap<String, Object> beam = new HashMap();
/*  70 */   static HashMap<String, Float> breakcount = new HashMap();
/*  71 */   static HashMap<String, Integer> lastX = new HashMap();
/*  72 */   static HashMap<String, Integer> lastY = new HashMap();
/*  73 */   static HashMap<String, Integer> lastZ = new HashMap();
/*     */   
/*     */ 
/*     */   public boolean onFocusActivation(ItemStack stack, World world, EntityLivingBase p, MovingObjectPosition movingobjectposition, int count)
/*     */   {
/*  78 */     IWand wand = (IWand)stack.func_77973_b();
/*  79 */     String pp = "R" + p.func_70005_c_();
/*  80 */     if (!p.field_70170_p.field_72995_K) { pp = "S" + p.func_70005_c_();
/*     */     }
/*  82 */     if (soundDelay.get(pp) == null) soundDelay.put(pp, Long.valueOf(0L));
/*  83 */     if (breakcount.get(pp) == null) breakcount.put(pp, Float.valueOf(0.0F));
/*  84 */     if (lastX.get(pp) == null) lastX.put(pp, Integer.valueOf(0));
/*  85 */     if (lastY.get(pp) == null) lastY.put(pp, Integer.valueOf(0));
/*  86 */     if (lastZ.get(pp) == null) { lastZ.put(pp, Integer.valueOf(0));
/*     */     }
/*  88 */     MovingObjectPosition mop = BlockUtils.getTargetBlock(p.field_70170_p, p, false);
/*  89 */     Vec3 v = p.func_70040_Z();
/*  90 */     double tx = p.field_70165_t + v.field_72450_a * 10.0D;
/*  91 */     double ty = p.field_70163_u + p.func_70047_e() + v.field_72448_b * 10.0D;
/*  92 */     double tz = p.field_70161_v + v.field_72449_c * 10.0D;
/*  93 */     int impact = 0;
/*  94 */     if (mop != null) {
/*  95 */       tx = mop.field_72307_f.field_72450_a;
/*  96 */       ty = mop.field_72307_f.field_72448_b;
/*  97 */       tz = mop.field_72307_f.field_72449_c;
/*  98 */       impact = 5;
/*  99 */       if ((!p.field_70170_p.field_72995_K) && (((Long)soundDelay.get(pp)).longValue() < System.currentTimeMillis()))
/*     */       {
/* 101 */         p.field_70170_p.func_72908_a(tx, ty, tz, "thaumcraft:rumble", 0.3F, 1.0F);
/* 102 */         soundDelay.put(pp, Long.valueOf(System.currentTimeMillis() + 1200L));
/*     */       }
/*     */     } else {
/* 105 */       soundDelay.put(pp, Long.valueOf(0L));
/*     */     }
/*     */     
/* 108 */     if (p.field_70170_p.field_72995_K) {
/* 109 */       beam.put(pp, Thaumcraft.proxy.getFX().beamCont(p, tx, ty, tz, 2, 65382, false, impact > 0 ? 2.0F : 0.0F, beam.get(pp), impact));
/*     */     }
/*     */     
/* 112 */     if ((mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) && ((!(p instanceof EntityPlayer)) || (p.field_70170_p.canMineBlockBody((EntityPlayer)p, mop.func_178782_a()))))
/*     */     {
/*     */ 
/* 115 */       IBlockState bs = p.field_70170_p.func_180495_p(mop.func_178782_a());
/*     */       
/* 117 */       float hardness = bs.func_177230_c().func_176195_g(p.field_70170_p, mop.func_178782_a());
/*     */       
/* 119 */       if (hardness >= 0.0F) {
/* 120 */         int pot = wand.getFocusPotency(stack);
/* 121 */         float speed = 0.05F + pot * 0.1F;
/* 122 */         if ((bs.func_177230_c().func_149688_o() == Material.field_151576_e) || (bs.func_177230_c().func_149688_o() == Material.field_151577_b) || (bs.func_177230_c().func_149688_o() == Material.field_151578_c) || (bs.func_177230_c().func_149688_o() == Material.field_151595_p))
/*     */         {
/* 124 */           speed = 0.25F + pot * 0.25F;
/*     */         }
/* 126 */         if (bs.func_177230_c() == Blocks.field_150343_Z) { speed *= 3.0F;
/*     */         }
/* 128 */         if ((((Integer)lastX.get(pp)).intValue() == mop.func_178782_a().func_177958_n()) && (((Integer)lastY.get(pp)).intValue() == mop.func_178782_a().func_177956_o()) && (((Integer)lastZ.get(pp)).intValue() == mop.func_178782_a().func_177952_p()))
/*     */         {
/* 130 */           float bc = ((Float)breakcount.get(pp)).floatValue();
/*     */           
/* 132 */           if ((p.field_70170_p.field_72995_K) && (bc > 0.0F) && (bs.func_177230_c() != Blocks.field_150350_a)) {
/* 133 */             int progress = (int)(bc / hardness * 9.0F);
/* 134 */             Thaumcraft.proxy.getFX().excavateFX(mop.func_178782_a(), p, progress);
/*     */           }
/*     */           
/* 137 */           if (p.field_70170_p.field_72995_K) {
/* 138 */             if (bc >= hardness) {
/* 139 */               breakcount.put(pp, Float.valueOf(0.0F));
/*     */             } else {
/* 141 */               breakcount.put(pp, Float.valueOf(bc + speed));
/*     */             }
/*     */           }
/* 144 */           else if (bc >= hardness) {
/* 145 */             if (excavate(p.field_70170_p, stack, p, bs, mop.func_178782_a()))
/*     */             {
/* 147 */               for (int a = 0; a < wand.getFocusEnlarge(stack); a++) {
/* 148 */                 breakNeighbour(p, mop.func_178782_a(), bs, stack);
/*     */               }
/*     */             }
/*     */             
/* 152 */             lastX.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 153 */             lastY.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 154 */             lastZ.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 155 */             breakcount.put(pp, Float.valueOf(0.0F));
/*     */           } else {
/* 157 */             breakcount.put(pp, Float.valueOf(bc + speed));
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 162 */           lastX.put(pp, Integer.valueOf(mop.func_178782_a().func_177958_n()));
/* 163 */           lastY.put(pp, Integer.valueOf(mop.func_178782_a().func_177956_o()));
/* 164 */           lastZ.put(pp, Integer.valueOf(mop.func_178782_a().func_177952_p()));
/* 165 */           breakcount.put(pp, Float.valueOf(0.0F));
/*     */         }
/*     */       }
/*     */     } else {
/* 169 */       lastX.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 170 */       lastY.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 171 */       lastZ.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 172 */       breakcount.put(pp, Float.valueOf(0.0F));
/*     */     }
/* 174 */     return true;
/*     */   }
/*     */   
/*     */   private boolean excavate(World world, ItemStack stack, EntityLivingBase p, IBlockState bs, BlockPos pos) {
/* 178 */     WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
/* 179 */     if ((p instanceof EntityPlayer)) {
/* 180 */       if (((EntityPlayer)p).field_71075_bZ.field_75099_e) {
/* 181 */         if (((EntityPlayer)p).field_71075_bZ.field_75098_d) {
/* 182 */           gt = WorldSettings.GameType.CREATIVE;
/*     */         }
/*     */       } else {
/* 185 */         gt = WorldSettings.GameType.ADVENTURE;
/*     */       }
/*     */     }
/*     */     
/* 189 */     int xp = net.minecraftforge.common.ForgeHooks.onBlockBreakEvent(world, gt, (net.minecraft.entity.player.EntityPlayerMP)p, pos);
/* 190 */     if (xp >= 0)
/*     */     {
/* 192 */       IWand wand = (IWand)stack.func_77973_b();
/* 193 */       int fortune = wand.getFocusTreasure(stack);
/* 194 */       boolean silk = isUpgradedWith(wand.getFocusStack(stack), FocusUpgradeType.silktouch);
/*     */       
/* 196 */       if (((p instanceof EntityPlayer)) && (silk) && (bs.func_177230_c().canSilkHarvest(p.field_70170_p, pos, bs, (EntityPlayer)p)))
/*     */       {
/* 198 */         ArrayList<ItemStack> items = new ArrayList();
/*     */         
/* 200 */         ItemStack itemstack = BlockUtils.createStackedBlock(bs);
/*     */         
/* 202 */         if (itemstack != null)
/*     */         {
/* 204 */           items.add(itemstack);
/*     */         }
/*     */         
/* 207 */         net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, world, pos, bs, 0, 1.0F, true, (EntityPlayer)p);
/* 208 */         ItemStack is; for (Iterator i$ = items.iterator(); i$.hasNext(); 
/*     */             
/* 210 */             Block.func_180635_a(world, pos, is))
/*     */         {
/* 208 */           is = (ItemStack)i$.next();
/*     */           
/* 210 */           bs.func_177230_c();
/*     */         }
/*     */       }
/*     */       else {
/* 214 */         bs.func_177230_c().func_180653_a(world, pos, bs, 1.0F, fortune);
/* 215 */         bs.func_177230_c().func_180637_b(world, pos, bs.func_177230_c().getExpDrop(world, pos, fortune));
/*     */       }
/*     */       
/* 218 */       world.func_175698_g(pos);
/* 219 */       world.func_175718_b(2001, pos, Block.func_176210_f(bs));
/* 220 */       return true;
/*     */     }
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   boolean breakNeighbour(EntityLivingBase p, BlockPos pos, IBlockState bs, ItemStack stack) {
/* 226 */     ArrayList<EnumFacing> faces = new ArrayList();
/* 227 */     faces.add(EnumFacing.DOWN);faces.add(EnumFacing.EAST);
/* 228 */     faces.add(EnumFacing.NORTH);faces.add(EnumFacing.SOUTH);
/* 229 */     faces.add(EnumFacing.UP);faces.add(EnumFacing.WEST);
/* 230 */     Collections.shuffle(faces, p.func_70681_au());
/*     */     
/* 232 */     Collections.shuffle(faces, p.field_70170_p.field_73012_v);
/* 233 */     for (EnumFacing dir : faces) {
/* 234 */       if (p.field_70170_p.func_180495_p(pos.func_177972_a(dir)) == bs)
/*     */       {
/* 236 */         if (excavate(p.field_70170_p, stack, p, bs, pos.func_177972_a(dir)))
/*     */         {
/* 238 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 243 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer p, int count)
/*     */   {
/* 251 */     String pp = "R" + p.func_70005_c_();
/* 252 */     if (!p.field_70170_p.field_72995_K) { pp = "S" + p.func_70005_c_();
/*     */     }
/* 254 */     if (soundDelay.get(pp) == null) soundDelay.put(pp, Long.valueOf(0L));
/* 255 */     if (breakcount.get(pp) == null) breakcount.put(pp, Float.valueOf(0.0F));
/* 256 */     if (lastX.get(pp) == null) lastX.put(pp, Integer.valueOf(0));
/* 257 */     if (lastY.get(pp) == null) lastY.put(pp, Integer.valueOf(0));
/* 258 */     if (lastZ.get(pp) == null) lastZ.put(pp, Integer.valueOf(0));
/* 259 */     beam.put(pp, null);
/* 260 */     lastX.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 261 */     lastY.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 262 */     lastZ.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 263 */     breakcount.put(pp, Float.valueOf(0.0F));
/*     */   }
/*     */   
/*     */   public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack)
/*     */   {
/* 268 */     return ItemFocusBasic.WandFocusAnimation.CHARGE;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 273 */     switch (rank) {
/* 274 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure };
/* 275 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
/* 276 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure, dowsing };
/* 277 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
/* 278 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure, FocusUpgradeType.silktouch };
/*     */     }
/* 280 */     return null;
/*     */   }
/*     */   
/* 283 */   public static FocusUpgradeType dowsing = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/dowsing.png"), "focus.upgrade.dowsing.name", "focus.upgrade.dowsing.text", new AspectList().add(Aspect.CRAFT, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusExcavation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */