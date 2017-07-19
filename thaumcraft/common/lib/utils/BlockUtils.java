/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLog.EnumAxis;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.management.ServerConfigurationManager;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumFacing.Axis;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.RegistryNamespacedDefaultedByKey;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.wands.ItemWand;
/*     */ 
/*     */ public class BlockUtils
/*     */ {
/*     */   public static boolean harvestBlock(World world, EntityPlayer player, BlockPos pos)
/*     */   {
/*  42 */     return harvestBlock(world, player, pos, false);
/*     */   }
/*     */   
/*     */   public static boolean harvestBlock(World world, EntityPlayer player, BlockPos pos, boolean alwaysDrop) {
/*  46 */     IBlockState bs = world.func_180495_p(pos);
/*  47 */     net.minecraft.tileentity.TileEntity tile = world.func_175625_s(pos);
/*     */     
/*  49 */     if (bs.func_177230_c().func_176195_g(world, pos) < 0.0F) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     world.func_175718_b(2001, pos, Block.func_176210_f(bs));
/*  54 */     boolean flag = false;
/*     */     
/*  56 */     if (player.field_71075_bZ.field_75098_d) {
/*  57 */       flag = removeBlock(world, pos, player, false);
/*     */     } else {
/*  59 */       boolean flag1 = false;
/*  60 */       if (bs != null) {
/*  61 */         flag1 = (alwaysDrop) || (bs.func_177230_c().canHarvestBlock(world, pos, player));
/*     */       }
/*     */       
/*  64 */       flag = removeBlock(world, pos, player, true);
/*  65 */       if ((flag) && (flag1)) {
/*  66 */         bs.func_177230_c().func_180657_a(world, player, pos, bs, tile);
/*     */         try
/*     */         {
/*  69 */           int fortune = EnchantmentHelper.func_77517_e(player);
/*  70 */           if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemWand))) {
/*  71 */             fortune += ((ItemWand)player.func_70694_bm().func_77973_b()).getFocusTreasure(player.func_70694_bm());
/*     */           }
/*  73 */           if ((!bs.func_177230_c().canSilkHarvest(world, pos, bs, player)) || (!EnchantmentHelper.func_77502_d(player))) {
/*  74 */             int exp = bs.func_177230_c().getExpDrop(world, pos, fortune);
/*  75 */             if (exp > 0)
/*     */             {
/*  77 */               bs.func_177230_c().func_180637_b(world, pos, exp);
/*     */             }
/*     */           }
/*     */         } catch (Exception e1) {}
/*     */       }
/*     */     }
/*  83 */     return true;
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
/*     */   public static ItemStack createStackedBlock(IBlockState bs)
/*     */   {
/* 105 */     ItemStack dropped = null;
/*     */     try {
/* 107 */       Method m = net.minecraftforge.fml.relauncher.ReflectionHelper.findMethod(Block.class, bs.func_177230_c(), new String[] { "createStackedBlock", "func_180643_i", "i" }, new Class[] { IBlockState.class });
/*     */       
/* 109 */       dropped = (ItemStack)m.invoke(bs.func_177230_c(), new Object[] { bs });
/*     */     } catch (Exception e) {
/* 111 */       Thaumcraft.log.warn("Could not invoke net.minecraft.block.Block method createStackedBlock");
/*     */     }
/* 113 */     return dropped;
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
/*     */   public static void destroyBlockPartially(World world, int par1, BlockPos pos, int par5)
/*     */   {
/* 145 */     Iterator iterator = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b.iterator();
/*     */     
/*     */ 
/* 148 */     while (iterator.hasNext()) {
/* 149 */       EntityPlayerMP entityplayermp = (EntityPlayerMP)iterator.next();
/*     */       
/* 151 */       if ((entityplayermp != null) && (entityplayermp.field_70170_p == MinecraftServer.func_71276_C().func_130014_f_()) && (entityplayermp.func_145782_y() != par1))
/*     */       {
/*     */ 
/* 154 */         double d0 = pos.func_177958_n() - entityplayermp.field_70165_t;
/* 155 */         double d1 = pos.func_177956_o() - entityplayermp.field_70163_u;
/* 156 */         double d2 = pos.func_177952_p() - entityplayermp.field_70161_v;
/*     */         
/* 158 */         if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D) {
/* 159 */           entityplayermp.field_71135_a.func_147359_a(new net.minecraft.network.play.server.S25PacketBlockBreakAnim(par1, pos, par5));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean removeBlock(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
/* 166 */     Block block = world.func_180495_p(pos).func_177230_c();
/*     */     
/* 168 */     if (block != null) {
/* 169 */       block.func_176208_a(world, pos, world.func_180495_p(pos), player);
/*     */     }
/*     */     
/* 172 */     boolean flag = (block != null) && (block.removedByPlayer(world, pos, player, willHarvest));
/*     */     
/* 174 */     if ((block != null) && (flag)) {
/* 175 */       block.func_176206_d(world, pos, world.func_180495_p(pos));
/*     */     }
/*     */     
/* 178 */     return flag;
/*     */   }
/*     */   
/*     */   public static void findBlocks(World world, BlockPos pos, IBlockState block, int reach) {
/* 182 */     int count = 0;
/* 183 */     for (int xx = -reach; xx <= reach; xx++)
/* 184 */       for (int yy = reach; yy >= -reach; yy--)
/* 185 */         for (int zz = -reach; zz <= reach; zz++) {
/* 186 */           if (Math.abs(lastPos.func_177958_n() + xx - pos.func_177958_n()) > 24) return;
/* 187 */           if (Math.abs(lastPos.func_177956_o() + yy - pos.func_177956_o()) > 48) return;
/* 188 */           if (Math.abs(lastPos.func_177952_p() + zz - pos.func_177952_p()) > 24) return;
/* 189 */           IBlockState bs = world.func_180495_p(lastPos.func_177982_a(xx, yy, zz));
/*     */           
/* 191 */           boolean same = (bs.func_177230_c() == block.func_177230_c()) && (bs.func_177230_c().func_180651_a(bs) == block.func_177230_c().func_180651_a(block));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 199 */           if ((same) && (bs.func_177230_c().func_176195_g(world, lastPos.func_177982_a(xx, yy, zz)) >= 0.0F)) {
/* 200 */             double xd = lastPos.func_177958_n() + xx - pos.func_177958_n();
/* 201 */             double yd = lastPos.func_177956_o() + yy - pos.func_177956_o();
/* 202 */             double zd = lastPos.func_177952_p() + zz - pos.func_177952_p();
/* 203 */             double d = xd * xd + yd * yd + zd * zd;
/* 204 */             if (d > lastdistance) {
/* 205 */               lastdistance = d;
/* 206 */               lastPos = lastPos.func_177982_a(xx, yy, zz);
/* 207 */               findBlocks(world, pos, block, reach);
/* 208 */               return;
/*     */             }
/*     */           }
/*     */         }
/*     */   }
/*     */   
/* 214 */   static BlockPos lastPos = BlockPos.field_177992_a;
/* 215 */   static int lasty = 0;
/* 216 */   static int lastz = 0;
/* 217 */   static double lastdistance = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean breakFurthestBlock(World world, BlockPos pos, IBlockState block, EntityPlayer player)
/*     */   {
/* 226 */     lastPos = new BlockPos(pos);
/* 227 */     lastdistance = 0.0D;
/*     */     
/* 229 */     int reach = Utils.isWoodLog(world, pos) ? 2 : 1;
/*     */     
/* 231 */     findBlocks(world, pos, block, reach);
/*     */     
/* 233 */     boolean worked = harvestBlock(world, player, lastPos, false);
/* 234 */     world.func_175689_h(pos);
/*     */     
/* 236 */     if ((worked) && (Utils.isWoodLog(world, pos)))
/*     */     {
/* 238 */       world.func_175689_h(lastPos);
/* 239 */       for (int xx = -3; xx <= 3; xx++) {
/* 240 */         for (int yy = -3; yy <= 3; yy++) {
/* 241 */           for (int zz = -3; zz <= 3; zz++) {
/* 242 */             world.func_175684_a(lastPos.func_177982_a(xx, yy, zz), world.func_180495_p(lastPos.func_177982_a(xx, yy, zz)).func_177230_c(), 50 + world.field_73012_v.nextInt(75));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 248 */     return worked;
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
/*     */   public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean par3)
/*     */   {
/* 268 */     return getTargetBlock(world, entity, par3, par3, 10.0D);
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, double range) {
/* 272 */     float var4 = 1.0F;
/* 273 */     float var5 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * var4;
/* 274 */     float var6 = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * var4;
/* 275 */     double var7 = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * var4;
/* 276 */     double var9 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * var4 + entity.func_70047_e();
/* 277 */     double var11 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * var4;
/* 278 */     Vec3 var13 = new Vec3(var7, var9, var11);
/* 279 */     float var14 = MathHelper.func_76134_b(-var6 * 0.017453292F - 3.1415927F);
/* 280 */     float var15 = MathHelper.func_76126_a(-var6 * 0.017453292F - 3.1415927F);
/* 281 */     float var16 = -MathHelper.func_76134_b(-var5 * 0.017453292F);
/* 282 */     float var17 = MathHelper.func_76126_a(-var5 * 0.017453292F);
/* 283 */     float var18 = var15 * var16;
/* 284 */     float var20 = var14 * var16;
/* 285 */     Vec3 var23 = var13.func_72441_c(var18 * range, var17 * range, var20 * range);
/* 286 */     return world.func_147447_a(var13, var23, stopOnLiquid, !ignoreBlockWithoutBoundingBox, false);
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
/*     */   public static int countExposedSides(World world, BlockPos pos)
/*     */   {
/* 316 */     int count = 0;
/* 317 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 318 */       if (world.func_175623_d(pos.func_177972_a(dir))) count++;
/*     */     }
/* 320 */     return count;
/*     */   }
/*     */   
/*     */   public static boolean isBlockExposed(World world, BlockPos pos) {
/* 324 */     for (EnumFacing face : ) {
/* 325 */       if (!world.func_180495_p(pos.func_177972_a(face)).func_177230_c().func_149662_c()) {
/* 326 */         return true;
/*     */       }
/*     */     }
/* 329 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isAdjacentToSolidBlock(World world, BlockPos pos) {
/* 333 */     for (EnumFacing face : ) {
/* 334 */       if (world.isSideSolid(pos.func_177972_a(face), face.func_176734_d())) return true;
/*     */     }
/* 336 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isBlockTouching(IBlockAccess world, BlockPos pos, IBlockState bs) {
/* 340 */     for (EnumFacing face : ) {
/* 341 */       if (world.func_180495_p(pos.func_177972_a(face)) == bs) return true;
/*     */     }
/* 343 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isBlockTouching(IBlockAccess world, BlockPos pos, Block bs) {
/* 347 */     for (EnumFacing face : ) {
/* 348 */       if (world.func_180495_p(pos.func_177972_a(face)).func_177230_c() == bs) return true;
/*     */     }
/* 350 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isBlockTouching(IBlockAccess world, BlockPos pos, Material mat, boolean solid) {
/* 354 */     for (EnumFacing face : ) {
/* 355 */       if ((world.func_180495_p(pos.func_177972_a(face)).func_177230_c().func_149688_o() == mat) && ((!solid) || (world.func_180495_p(pos.func_177972_a(face)).func_177230_c().isSideSolid(world, pos.func_177972_a(face), face.func_176734_d()))))
/*     */       {
/* 357 */         return true; }
/*     */     }
/* 359 */     return false;
/*     */   }
/*     */   
/*     */   public static EnumFacing getFaceBlockTouching(IBlockAccess world, BlockPos pos, Block bs) {
/* 363 */     for (EnumFacing face : ) {
/* 364 */       if (world.func_180495_p(pos.func_177972_a(face)).func_177230_c() == bs) return face;
/*     */     }
/* 366 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 371 */   public static ArrayList<String> portableHoleBlackList = new ArrayList();
/*     */   
/*     */   public static boolean isPortableHoleBlackListed(IBlockState blockstate) {
/* 374 */     return isBlockListed(blockstate, portableHoleBlackList);
/*     */   }
/*     */   
/*     */   public static boolean isBlockListed(IBlockState blockstate, List<String> list) {
/* 378 */     String stateString = blockstate.toString();
/* 379 */     for (String key : list) {
/* 380 */       String[] splitString = key.split(";");
/* 381 */       if (splitString[0].contains(":")) {
/* 382 */         if (((ResourceLocation)Block.field_149771_c.func_177774_c(blockstate.func_177230_c())).toString().equals(splitString[0])) {
/* 383 */           if (splitString.length > 1) {
/* 384 */             int matches = 0;
/* 385 */             for (int a = 1; a < splitString.length; a++) {
/* 386 */               if (stateString.contains(splitString[a])) matches++;
/*     */             }
/* 388 */             if (matches == splitString.length - 1) {
/* 389 */               return true;
/*     */             }
/*     */           } else {
/* 392 */             return true;
/*     */           }
/*     */         }
/*     */       } else {
/* 396 */         bs = new ItemStack(Item.func_150898_a(blockstate.func_177230_c()), 1, blockstate.func_177230_c().func_176201_c(blockstate));
/* 397 */         for (ItemStack stack : OreDictionary.getOres(splitString[0])) {
/* 398 */           if (OreDictionary.itemMatches(stack, bs, false))
/* 399 */             return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     ItemStack bs;
/* 404 */     return false;
/*     */   }
/*     */   
/*     */   public static double distance(BlockPos b1, BlockPos b2) {
/* 408 */     double d3 = b1.func_177958_n() - b2.func_177958_n();
/* 409 */     double d4 = b1.func_177956_o() - b2.func_177956_o();
/* 410 */     double d5 = b1.func_177952_p() - b2.func_177952_p();
/* 411 */     return d3 * d3 + d4 * d4 + d5 * d5;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static EnumFacing.Axis getBlockAxis(World world, BlockPos pos)
/*     */   {
/* 483 */     IBlockState state = world.func_180495_p(pos);
/* 484 */     EnumFacing.Axis ax = EnumFacing.Axis.Y;
/* 485 */     for (IProperty prop : state.func_177228_b().keySet())
/*     */     {
/* 487 */       if (prop.func_177701_a().equals("axis"))
/*     */       {
/* 489 */         if ((state.func_177229_b(prop) instanceof BlockLog.EnumAxis)) {
/* 490 */           ax = (BlockLog.EnumAxis)state.func_177229_b(prop) == BlockLog.EnumAxis.Z ? EnumFacing.Axis.Z : (BlockLog.EnumAxis)state.func_177229_b(prop) == BlockLog.EnumAxis.Y ? EnumFacing.Axis.Y : (BlockLog.EnumAxis)state.func_177229_b(prop) == BlockLog.EnumAxis.X ? EnumFacing.Axis.X : EnumFacing.Axis.Y;
/*     */           
/*     */ 
/* 493 */           break;
/*     */         }
/* 495 */         if ((state.func_177229_b(prop) instanceof EnumFacing.Axis)) {
/* 496 */           ax = (EnumFacing.Axis)state.func_177229_b(prop);
/* 497 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 501 */     if (ax == null) ax = EnumFacing.Axis.Y;
/* 502 */     return ax;
/*     */   }
/*     */   
/*     */   public static boolean hasLOS(World world, BlockPos source, BlockPos target)
/*     */   {
/* 507 */     MovingObjectPosition mop = ThaumcraftApiHelper.rayTraceIgnoringSource(world, new Vec3(source.func_177958_n() + 0.5D, source.func_177956_o() + 0.5D, source.func_177952_p() + 0.5D), new Vec3(target.func_177958_n() + 0.5D, target.func_177956_o() + 0.5D, target.func_177952_p() + 0.5D), false, true, false);
/*     */     
/*     */ 
/*     */ 
/* 511 */     return (mop == null) || ((mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) && (mop.func_178782_a().func_177958_n() == target.func_177958_n()) && (mop.func_178782_a().func_177956_o() == target.func_177956_o()) && (mop.func_178782_a().func_177952_p() == target.func_177952_p()));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/utils/BlockUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */