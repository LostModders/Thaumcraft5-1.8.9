/*     */ package thaumcraft.common.items.wands;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.IWandTriggerManager;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.blocks.basic.BlockPillar;
/*     */ import thaumcraft.common.blocks.basic.BlockPillar.PillarType;
/*     */ import thaumcraft.common.blocks.devices.BlockGolemBuilder;
/*     */ import thaumcraft.common.blocks.misc.BlockPlaceholder;
/*     */ import thaumcraft.common.blocks.misc.BlockPlaceholder.PlaceholderType;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockBamf;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.tiles.crafting.TileInfusionMatrix;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchAltar;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchPortal;
/*     */ 
/*     */ public class WandTriggers implements IWandTriggerManager
/*     */ {
/*     */   public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, BlockPos pos, EnumFacing side, int event)
/*     */   {
/*  41 */     switch (event) {
/*  42 */     case 0:  return createThaumonomicon(wand, player, world, pos);
/*  43 */     case 1:  return createCrucible(wand, player, world, pos);
/*  44 */     case 2:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "INFERNALFURNACE"))
/*  45 */         return createInfernalFurnace(wand, player, world, pos);
/*     */       break;
/*  47 */     case 3:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "INFUSION"))
/*  48 */         return createInfusionAltar(wand, player, world, pos);
/*     */       break;
/*  50 */     case 4: case 5:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "THAUMATORIUM"))
/*  51 */         return createThaumatorium(wand, player, world, pos, side);
/*     */       break;
/*  53 */     case 6:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "OCULUS"))
/*  54 */         return createOculus(wand, player, world, pos);
/*     */       break;
/*  56 */     case 7: case 8: case 9: case 10:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "MINDCLOCKWORK"))
/*  57 */         return createGolemPress(wand, player, world, pos);
/*     */       break;
/*     */     }
/*  60 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createGolemPress(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos) {
/*  64 */     if (!world.field_72995_K) {
/*  65 */       BlockPos start = null;
/*     */       
/*  67 */       for (int yy = -1; yy < 2; yy++)
/*  68 */         for (int xx = -1; xx < 2; xx++)
/*  69 */           for (int zz = -1; zz < 2; zz++)
/*  70 */             if (world.func_180495_p(pos.func_177982_a(xx, yy, zz)) == Blocks.field_150331_J.func_176223_P().func_177226_a(BlockPistonBase.field_176321_a, EnumFacing.UP)) {
/*  71 */               start = pos.func_177982_a(xx, yy, zz);
/*     */               break label104;
/*     */             }
/*     */       label104:
/*  75 */       if (start == null) return false;
/*  76 */       if (world.func_180495_p(start.func_177984_a()).func_177230_c() != Blocks.field_150411_aY) return false;
/*  77 */       EnumFacing face = null;
/*  78 */       for (EnumFacing f : EnumFacing.field_176754_o) {
/*  79 */         if (world.func_180495_p(start.func_177972_a(f)).func_177230_c() == BlocksTC.tableStone) {
/*  80 */           face = f;
/*  81 */           break;
/*     */         }
/*     */       }
/*  84 */       if (face == null) return false;
/*  85 */       if (world.func_180495_p(start.func_177972_a(face).func_177972_a(face.func_176746_e())).func_177230_c() != Blocks.field_150467_bQ) return false;
/*  86 */       if (world.func_180495_p(start.func_177972_a(face).func_177972_a(face.func_176746_e()).func_177972_a(face.func_176746_e().func_176746_e())).func_177230_c() != Blocks.field_150383_bp) return false;
/*  87 */       IWand wand = (IWand)itemstack.func_77973_b();
/*  88 */       if (wand.consumeAllVis(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.ORDER, 50).add(Aspect.AIR, 50), true, true)) {
/*  89 */         setBlockSparkle(world, start.func_177984_a(), BlocksTC.placeholder.func_176203_a(2));
/*  90 */         setBlockSparkle(world, start.func_177972_a(face).func_177972_a(face.func_176746_e()), BlocksTC.placeholder.func_176203_a(3));
/*  91 */         setBlockSparkle(world, start.func_177972_a(face).func_177972_a(face.func_176746_e()).func_177972_a(face.func_176746_e().func_176746_e()), BlocksTC.placeholder.func_176203_a(4));
/*  92 */         setBlockSparkle(world, start.func_177972_a(face), BlocksTC.placeholder.func_176203_a(5));
/*  93 */         setBlockSparkle(world, start, BlocksTC.golemBuilder.func_176223_P().func_177226_a(BlockGolemBuilder.FACING, face));
/*  94 */         world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/*  95 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createThaumonomicon(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos)
/*     */   {
/* 104 */     if (!world.field_72995_K) {
/* 105 */       IWand wand = (IWand)itemstack.func_77973_b();
/* 106 */       if (wand.getFocus(itemstack) != null) return false;
/* 107 */       world.func_175698_g(pos);
/* 108 */       EntitySpecialItem entityItem = new EntitySpecialItem(world, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.3D, pos.func_177952_p() + 0.5D, new ItemStack(ItemsTC.thaumonomicon));
/*     */       
/* 110 */       entityItem.field_70181_x = 0.0D;
/* 111 */       entityItem.field_70159_w = 0.0D;
/* 112 */       entityItem.field_70179_y = 0.0D;
/* 113 */       world.func_72838_d(entityItem);
/* 114 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(pos, 55537, true, true, true), new NetworkRegistry.TargetPoint(world.field_73011_w.func_177502_q(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */       
/*     */ 
/* 117 */       world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/* 118 */       return true;
/*     */     }
/*     */     
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createCrucible(ItemStack is, EntityPlayer player, World world, BlockPos pos) {
/* 125 */     IWand wand = (IWand)is.func_77973_b();
/* 126 */     if (!world.field_72995_K) {
/* 127 */       world.func_175698_g(pos);
/* 128 */       world.func_175656_a(pos, BlocksTC.crucible.func_176223_P());
/* 129 */       world.func_175685_c(pos, BlocksTC.crucible);
/* 130 */       world.func_175689_h(pos);
/* 131 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(pos, 55537, true, true, true), new NetworkRegistry.TargetPoint(world.field_73011_w.func_177502_q(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */       
/*     */ 
/* 134 */       world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/* 135 */       return true;
/*     */     }
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createInfusionAltar(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos) {
/* 141 */     IWand wand = (IWand)itemstack.func_77973_b();
/* 142 */     if ((fitInfusionAltar(world, pos)) && (wand.consumeAllVis(itemstack, player, new AspectList().add(Aspect.FIRE, 75).add(Aspect.EARTH, 75).add(Aspect.ORDER, 75).add(Aspect.AIR, 75).add(Aspect.ENTROPY, 75).add(Aspect.WATER, 75), true, true)))
/*     */     {
/*     */ 
/* 145 */       if (!world.field_72995_K) {
/* 146 */         replaceInfusionAltar(world, pos);
/* 147 */         return true;
/*     */       }
/* 149 */       return false;
/*     */     }
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean fitInfusionAltar(World world, BlockPos pos) {
/* 155 */     Block br1 = BlocksTC.stone;
/*     */     
/* 157 */     Block[][][] blueprint = { { { br1, null, br1 }, { null, null, null }, { br1, null, br1 } }, { { br1, null, br1 }, { null, null, null }, { br1, null, br1 } } };
/*     */     
/*     */ 
/* 160 */     IBlockState j = world.func_180495_p(pos.func_177982_a(-1, -2, -1));
/* 161 */     if ((j != world.func_180495_p(pos.func_177982_a(1, -2, -1))) || (j != world.func_180495_p(pos.func_177982_a(1, -2, 1))) || (j != world.func_180495_p(pos.func_177982_a(-1, -2, 1))))
/*     */     {
/*     */ 
/* 164 */       return false; }
/* 165 */     int meta = -1;
/* 166 */     for (int yy = 0; yy < 2; yy++)
/* 167 */       for (int xx = 0; xx < 3; xx++)
/* 168 */         for (int zz = 0; zz < 3; zz++) {
/* 169 */           BlockPos cp = pos.func_177982_a(xx - 1, -(yy + 1), zz - 1);
/* 170 */           if (blueprint[yy][xx][zz] == null) {
/* 171 */             if ((xx == 1) && (zz == 1) && (yy == 1)) {
/* 172 */               TileEntity t = world.func_175625_s(cp);
/* 173 */               if ((t == null) || (!(t instanceof thaumcraft.common.tiles.crafting.TilePedestal))) return false;
/*     */             }
/* 175 */             else if (!world.func_175623_d(cp)) {
/* 176 */               return false;
/*     */             }
/* 178 */           } else { IBlockState b = world.func_180495_p(cp);
/* 179 */             if (b.func_177230_c() != blueprint[yy][xx][zz]) {
/* 180 */               return false;
/*     */             }
/* 182 */             int m = b.func_177230_c().func_176201_c(b);
/* 183 */             if (meta < 0) {
/* 184 */               meta = b.func_177230_c().func_176201_c(b);
/*     */             }
/* 186 */             else if (((m != 0) && (m != 2) && (m != 4)) || (meta != m)) {
/* 187 */               return false;
/*     */             }
/* 189 */             if ((yy == 2) && (b != world.func_180495_p(cp.func_177984_a())))
/* 190 */               return false;
/*     */           }
/*     */         }
/* 193 */     return true;
/*     */   }
/*     */   
/*     */   public static void replaceInfusionAltar(World world, BlockPos pos) {
/* 197 */     IBlockState j = world.func_180495_p(pos.func_177982_a(-1, -2, -1));
/* 198 */     BlockPillar.PillarType p = BlockPillar.PillarType.NORMAL;
/* 199 */     switch (j.func_177230_c().func_176201_c(j)) {
/* 200 */     case 2:  p = BlockPillar.PillarType.ANCIENT; break;
/* 201 */     case 4:  p = BlockPillar.PillarType.ELDRITCH;
/*     */     }
/*     */     
/* 204 */     setBlockSparkle(world, pos.func_177982_a(-1, -1, -1), Blocks.field_150350_a.func_176223_P());
/* 205 */     setBlockSparkle(world, pos.func_177982_a(1, -1, -1), Blocks.field_150350_a.func_176223_P());
/* 206 */     setBlockSparkle(world, pos.func_177982_a(1, -1, 1), Blocks.field_150350_a.func_176223_P());
/* 207 */     setBlockSparkle(world, pos.func_177982_a(-1, -1, 1), Blocks.field_150350_a.func_176223_P());
/*     */     
/* 209 */     setBlockSparkle(world, pos.func_177982_a(-1, -2, -1), BlocksTC.pillar.func_176223_P().func_177226_a(BlockPillar.TYPE, p).func_177226_a(BlockPillar.FACING, EnumFacing.EAST));
/* 210 */     setBlockSparkle(world, pos.func_177982_a(1, -2, -1), BlocksTC.pillar.func_176223_P().func_177226_a(BlockPillar.TYPE, p).func_177226_a(BlockPillar.FACING, EnumFacing.SOUTH));
/* 211 */     setBlockSparkle(world, pos.func_177982_a(1, -2, 1), BlocksTC.pillar.func_176223_P().func_177226_a(BlockPillar.TYPE, p).func_177226_a(BlockPillar.FACING, EnumFacing.WEST));
/* 212 */     setBlockSparkle(world, pos.func_177982_a(-1, -2, 1), BlocksTC.pillar.func_176223_P().func_177226_a(BlockPillar.TYPE, p).func_177226_a(BlockPillar.FACING, EnumFacing.NORTH));
/*     */     
/* 214 */     TileInfusionMatrix tis = (TileInfusionMatrix)world.func_175625_s(pos);
/* 215 */     tis.active = true;
/* 216 */     world.func_175689_h(pos);
/* 217 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(pos, 55537, true, true, false), new NetworkRegistry.TargetPoint(world.field_73011_w.func_177502_q(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */     
/* 219 */     world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() - 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private static void setBlockSparkle(World world, BlockPos pos, IBlockState state) {
/* 223 */     world.func_175656_a(pos, state);
/* 224 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(pos, 55537, false, true, false), new NetworkRegistry.TargetPoint(world.field_73011_w.func_177502_q(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */   }
/*     */   
/*     */   public static boolean createThaumatorium(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side) {
/* 228 */     IWand wand = (IWand)itemstack.func_77973_b();
/* 229 */     if ((world.func_180495_p(pos.func_177984_a()) != BlocksTC.metal.func_176223_P()) || (world.func_180495_p(pos.func_177977_b()) != BlocksTC.crucible.func_176223_P()))
/*     */     {
/*     */ 
/* 232 */       if ((world.func_180495_p(pos.func_177977_b()) == BlocksTC.metal.func_176223_P()) && (world.func_180495_p(pos.func_177979_c(2)) == BlocksTC.crucible.func_176223_P())) {
/* 233 */         pos = pos.func_177977_b();
/*     */       }
/* 235 */       else if ((world.func_180495_p(pos.func_177984_a()) == BlocksTC.metal.func_176223_P()) && (world.func_180495_p(pos.func_177981_b(2)) == BlocksTC.metal.func_176223_P())) {
/* 236 */         pos = pos.func_177984_a();
/*     */       } else {
/* 238 */         return false;
/*     */       }
/*     */     }
/* 241 */     if (wand.consumeAllVis(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.ORDER, 100).add(Aspect.WATER, 100), true, true))
/*     */     {
/* 243 */       if (!world.field_72995_K) {
/* 244 */         world.func_180501_a(pos, BlocksTC.thaumatorium.func_176223_P().func_177226_a(IBlockFacingHorizontal.FACING, side).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)), 0);
/*     */         
/*     */ 
/* 247 */         world.func_180501_a(pos.func_177984_a(), BlocksTC.thaumatorium.func_176223_P().func_177226_a(IBlockFacingHorizontal.FACING, side).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false)), 0);
/*     */         
/*     */ 
/*     */ 
/* 251 */         world.func_175689_h(pos);
/* 252 */         world.func_175689_h(pos.func_177984_a());
/* 253 */         world.func_175685_c(pos, BlocksTC.thaumatorium);
/* 254 */         world.func_175685_c(pos.func_177984_a(), BlocksTC.thaumatorium);
/*     */         
/* 256 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(pos, 55537, true, true, false), new NetworkRegistry.TargetPoint(world.field_73011_w.func_177502_q(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */         
/*     */ 
/* 259 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(pos.func_177984_a(), 55537, true, true, false), new NetworkRegistry.TargetPoint(world.field_73011_w.func_177502_q(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */         
/*     */ 
/* 262 */         world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:wand", 1.0F, 1.0F);
/* 263 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 267 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createInfernalFurnace(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos)
/*     */   {
/* 272 */     IWand wand = (IWand)itemstack.func_77973_b();
/* 273 */     for (int xx = -2; xx <= 0; xx++)
/* 274 */       for (int yy = -2; yy <= 0; yy++)
/* 275 */         for (int zz = -2; zz <= 0; zz++) {
/* 276 */           BlockPos p2 = pos.func_177982_a(xx, yy, zz);
/* 277 */           if ((fitInfernalFurnace(world, p2)) && (wand.consumeAllVis(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50), true, true)))
/*     */           {
/* 279 */             if (!world.field_72995_K) {
/* 280 */               replaceInfernalFurnace(world, p2);
/* 281 */               return true;
/*     */             }
/* 283 */             return false;
/*     */           }
/*     */         }
/* 286 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean fitInfernalFurnace(World world, BlockPos pos) {
/* 290 */     Block bo = Blocks.field_150343_Z;
/* 291 */     Block bn = Blocks.field_150385_bj;
/* 292 */     Block bf = Blocks.field_150411_aY;
/* 293 */     Block bl = Blocks.field_150353_l;
/* 294 */     Block[][][] blueprint = { { { bn, bo, bn }, { bo, Blocks.field_150350_a, bo }, { bn, bo, bn } }, { { bn, bo, bn }, { bo, bl, bo }, { bn, bo, bn } }, { { bn, bo, bn }, { bo, bo, bo }, { bn, bo, bn } } };
/*     */     
/*     */ 
/* 297 */     boolean barsfound = false;
/* 298 */     for (int yy = 0; yy < 3; yy++) {
/* 299 */       for (int xx = 0; xx < 3; xx++)
/* 300 */         for (int zz = 0; zz < 3; zz++) {
/* 301 */           IBlockState bs = world.func_180495_p(pos.func_177982_a(xx, -yy + 2, zz));
/* 302 */           Block block = bs.func_177230_c();
/* 303 */           if (world.func_175623_d(pos.func_177982_a(xx, -yy + 2, zz))) block = Blocks.field_150350_a;
/* 304 */           if (block != blueprint[yy][xx][zz])
/* 305 */             if ((yy == 1) && (!barsfound) && (block == bf) && (xx != zz) && ((xx == 1) || (zz == 1)))
/* 306 */               barsfound = true; else
/* 307 */               return false;
/*     */         }
/*     */     }
/* 310 */     return barsfound;
/*     */   }
/*     */   
/*     */   public static void replaceInfernalFurnace(World world, BlockPos pos)
/*     */   {
/* 315 */     int[][][] blueprint = { { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } }, { { 0, 1, 0 }, { 1, 3, 1 }, { 0, 1, 0 } }, { { 0, 1, 0 }, { 1, 4, 1 }, { 0, 1, 0 } } };
/*     */     
/*     */ 
/*     */ 
/* 319 */     BlockPos p1 = pos.func_177982_a(1, 1, 1);
/* 320 */     for (EnumFacing face : EnumFacing.field_176754_o) {
/* 321 */       if (world.func_180495_p(p1.func_177972_a(face)).func_177230_c() == Blocks.field_150411_aY)
/*     */       {
/* 323 */         setBlockSparkle(world, p1, BlocksTC.infernalFurnace.func_176223_P().func_177226_a(IBlockFacingHorizontal.FACING, face.func_176734_d()));
/*     */         
/* 325 */         break;
/*     */       }
/*     */     }
/*     */     
/* 329 */     for (int yy = 0; yy < 3; yy++) {
/* 330 */       for (int zz = 0; zz < 3; zz++)
/* 331 */         for (int xx = 0; xx < 3; xx++)
/*     */         {
/* 333 */           BlockPos p2 = pos.func_177982_a(xx, yy, zz);
/* 334 */           if (world.func_180495_p(p2).func_177230_c() == Blocks.field_150411_aY) {
/* 335 */             world.func_175698_g(p2);
/*     */           }
/*     */           else {
/* 338 */             if (blueprint[yy][xx][zz] == 0) {
/* 339 */               setBlockSparkle(world, p2, BlocksTC.placeholder.func_176223_P().func_177226_a(BlockPlaceholder.VARIANT, BlockPlaceholder.PlaceholderType.FURNACE_BRICK));
/*     */             }
/* 341 */             if (blueprint[yy][xx][zz] == 1)
/* 342 */               setBlockSparkle(world, p2, BlocksTC.placeholder.func_176223_P().func_177226_a(BlockPlaceholder.VARIANT, BlockPlaceholder.PlaceholderType.FURNACE_OBSIDIAN));
/*     */           }
/*     */         }
/*     */     }
/* 346 */     world.func_72908_a(pos.func_177958_n() + 1.5D, pos.func_177956_o() + 1.5D, pos.func_177952_p() + 1.5D, "thaumcraft:wand", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   private static boolean createOculus(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos)
/*     */   {
/* 352 */     if (!world.field_72995_K) {
/* 353 */       TileEntity tile = world.func_175625_s(pos);
/* 354 */       TileEntity portal = world.func_175625_s(pos.func_177984_a());
/* 355 */       if ((tile != null) && (portal != null) && ((tile instanceof TileEldritchAltar)) && (((TileEldritchAltar)tile).getEyes() == 4) && (!((TileEldritchAltar)tile).isOpen()) && ((portal instanceof TileEldritchPortal)) && (!((TileEldritchPortal)portal).open) && (((TileEldritchAltar)tile).checkForMaze()))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 361 */         IWand wand = (IWand)itemstack.func_77973_b();
/* 362 */         if (wand.consumeAllVis(itemstack, player, new AspectList().add(Aspect.AIR, 500).add(Aspect.FIRE, 500).add(Aspect.EARTH, 500).add(Aspect.WATER, 500).add(Aspect.ORDER, 500).add(Aspect.ENTROPY, 500), true, true))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 368 */           world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/*     */           
/* 370 */           ((TileEldritchAltar)tile).setOpen(true);
/* 371 */           ((TileEldritchPortal)portal).open = true;
/* 372 */           portal.func_70296_d();
/* 373 */           world.func_175689_h(pos.func_177984_a());
/* 374 */           tile.func_70296_d();
/* 375 */           world.func_175689_h(pos);
/*     */         }
/*     */       }
/*     */     }
/* 379 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/WandTriggers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */