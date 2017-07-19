/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.tiles.misc.TileHole;
/*     */ 
/*     */ public class ItemFocusPortableHole extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusPortableHole()
/*     */   {
/*  25 */     super("hole", 594985);
/*     */   }
/*     */   
/*     */ 
/*  29 */   private static final AspectList cost = new AspectList().add(Aspect.ENTROPY, 25).add(Aspect.AIR, 25).add(Aspect.EARTH, 25);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  33 */     return cost;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean createHole(World world, BlockPos pos, EnumFacing side, byte count, int max)
/*     */   {
/*  39 */     IBlockState bs = world.func_180495_p(pos);
/*  40 */     if ((!world.field_72995_K) && (world.func_175625_s(pos) == null) && (!BlockUtils.isPortableHoleBlackListed(bs)) && (bs.func_177230_c() != Blocks.field_150357_h) && (bs.func_177230_c() != BlocksTC.hole) && ((bs.func_177230_c().isAir(world, pos)) || (!bs.func_177230_c().func_176196_c(world, pos))) && (bs.func_177230_c().func_176195_g(world, pos) != -1.0F))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  46 */       if (world.func_175656_a(pos, BlocksTC.hole.func_176223_P())) {
/*  47 */         TileHole ts = (TileHole)world.func_175625_s(pos);
/*  48 */         ts.oldblock = bs;
/*  49 */         ts.countdownmax = ((short)max);
/*  50 */         ts.count = count;
/*  51 */         ts.direction = side;
/*  52 */         ts.func_70296_d();
/*     */       }
/*  54 */       return true; }
/*  55 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onFocusActivation(ItemStack itemstack, World world, EntityLivingBase player, MovingObjectPosition mop, int count)
/*     */   {
/*  61 */     IWand wand = (IWand)itemstack.func_77973_b();
/*     */     
/*  63 */     if ((mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK))
/*     */     {
/*  65 */       if (world.field_73011_w.func_177502_q() == thaumcraft.common.config.Config.dimensionOuterId) {
/*  66 */         if (!world.field_72995_K) {
/*  67 */           world.func_72908_a(mop.func_178782_a().func_177958_n() + 0.5D, mop.func_178782_a().func_177956_o() + 0.5D, mop.func_178782_a().func_177952_p() + 0.5D, "thaumcraft:wandfail", 1.0F, 1.0F);
/*     */         }
/*     */         
/*     */ 
/*  71 */         player.func_71038_i();
/*  72 */         return false;
/*     */       }
/*     */       
/*  75 */       int enlarge = wand.getFocusEnlarge(itemstack);
/*  76 */       int distance = 0;
/*  77 */       int maxdis = 33 + enlarge * 8;
/*  78 */       BlockPos pos = new BlockPos(mop.func_178782_a());
/*  79 */       for (distance = 0; distance < maxdis; distance++) {
/*  80 */         IBlockState bi = world.func_180495_p(pos);
/*  81 */         if ((BlockUtils.isPortableHoleBlackListed(bi)) || (bi.func_177230_c() == Blocks.field_150357_h) || (bi.func_177230_c() == BlocksTC.hole) || (bi.func_177230_c().isAir(world, pos)) || (bi.func_177230_c().func_176195_g(world, pos) == -1.0F)) {
/*     */           break;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*  87 */         pos = pos.func_177972_a(mop.field_178784_b.func_176734_d());
/*     */       }
/*     */       
/*     */ 
/*  91 */       int di = getUpgradeLevel(wand.getFocusStack(itemstack), FocusUpgradeType.extend);
/*  92 */       short dur = (short)(120 + 60 * di);
/*  93 */       createHole(world, mop.func_178782_a(), mop.field_178784_b, (byte)(distance + 1), dur);
/*     */       
/*  95 */       player.func_71038_i();
/*  96 */       if (!world.field_72995_K) { world.func_72908_a(mop.func_178782_a().func_177958_n() + 0.5D, mop.func_178782_a().func_177956_o() + 0.5D, mop.func_178782_a().func_177952_p() + 0.5D, "mob.endermen.portal", 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */     
/* 100 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 106 */     switch (rank) {
/* 107 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/* 108 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/* 109 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/* 110 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/* 111 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/*     */     }
/* 113 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusPortableHole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */