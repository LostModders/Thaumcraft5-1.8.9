/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockRedstoneComparator;
/*     */ import net.minecraft.block.BlockRedstoneWire;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent.NeighborNotifyEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileRedstoneRelay;
/*     */ 
/*     */ public class BlockRedstoneRelay extends BlockTCDevice implements IBlockFacingHorizontal, IBlockEnabled
/*     */ {
/*     */   public BlockRedstoneRelay()
/*     */   {
/*  47 */     super(Material.field_151594_q, TileRedstoneRelay.class);
/*  48 */     func_149711_c(0.0F);
/*  49 */     func_149752_b(0.0F);
/*  50 */     func_149672_a(field_149766_f);
/*  51 */     func_149649_H();
/*  52 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  64 */     return false;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  69 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176196_c(World worldIn, BlockPos pos)
/*     */   {
/*  75 */     return World.func_175683_a(worldIn, pos.func_177977_b()) ? super.func_176196_c(worldIn, pos) : false;
/*     */   }
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos pos)
/*     */   {
/*  80 */     return World.func_175683_a(worldIn, pos.func_177977_b());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  92 */     if (!playerIn.field_71075_bZ.field_75099_e)
/*     */     {
/*  94 */       return false;
/*     */     }
/*     */     
/*  97 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, playerIn, pos);
/*  98 */     if (hit == null) { return super.func_180639_a(world, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */     }
/* 100 */     TileEntity tile = world.func_175625_s(pos);
/*     */     
/* 102 */     if ((tile != null) && ((tile instanceof TileRedstoneRelay)))
/*     */     {
/* 104 */       if (hit.subHit == 0) {
/* 105 */         ((TileRedstoneRelay)tile).increaseOut();
/* 106 */         world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:key", 0.5F, 1.0F);
/* 107 */         updateState(world, pos, state);
/* 108 */         notifyNeighbors(world, pos, state);
/*     */       }
/* 110 */       if (hit.subHit == 1) {
/* 111 */         ((TileRedstoneRelay)tile).increaseIn();
/* 112 */         world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:key", 0.5F, 1.0F);
/* 113 */         updateState(world, pos, state);
/* 114 */         notifyNeighbors(world, pos, state);
/*     */       }
/* 116 */       return true;
/*     */     }
/*     */     
/* 119 */     return super.func_180639_a(world, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/* 126 */     boolean flag = shouldBePowered(worldIn, pos, state);
/*     */     
/* 128 */     if ((isPowered(state)) && (!flag))
/*     */     {
/* 130 */       worldIn.func_180501_a(pos, getUnpoweredState(state), 2);
/* 131 */       notifyNeighbors(worldIn, pos, state);
/*     */     }
/* 133 */     else if (!isPowered(state))
/*     */     {
/* 135 */       worldIn.func_180501_a(pos, getPoweredState(state), 2);
/* 136 */       notifyNeighbors(worldIn, pos, state);
/* 137 */       if (!flag)
/*     */       {
/* 139 */         worldIn.func_175654_a(pos, getPoweredState(state).func_177230_c(), getTickDelay(state), -1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 147 */     super.func_180663_b(worldIn, pos, state);
/* 148 */     notifyNeighbors(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_176225_a(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/* 155 */     return side.func_176740_k() != net.minecraft.util.EnumFacing.Axis.Y;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean isPowered(IBlockState state)
/*     */   {
/* 161 */     return BlockStateUtils.isEnabled(state);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176211_b(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side)
/*     */   {
/* 167 */     return func_180656_a(worldIn, pos, state, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180656_a(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side)
/*     */   {
/* 173 */     return state.func_177229_b(FACING) == side ? getActiveSignal(worldIn, pos, state) : !isPowered(state) ? 0 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 179 */     if (canBlockStay(worldIn, pos))
/*     */     {
/* 181 */       updateState(worldIn, pos, state);
/*     */     }
/*     */     else
/*     */     {
/* 185 */       func_176226_b(worldIn, pos, state, 0);
/* 186 */       worldIn.func_175698_g(pos);
/* 187 */       EnumFacing[] aenumfacing = EnumFacing.values();
/* 188 */       int i = aenumfacing.length;
/*     */       
/* 190 */       for (int j = 0; j < i; j++)
/*     */       {
/* 192 */         EnumFacing enumfacing = aenumfacing[j];
/* 193 */         worldIn.func_175685_c(pos.func_177972_a(enumfacing), this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void updateState(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 201 */     boolean flag = shouldBePowered(worldIn, pos, state);
/*     */     
/* 203 */     if (((isPowered(state)) && (!flag)) || ((!isPowered(state)) && (flag) && (!worldIn.func_175691_a(pos, this))))
/*     */     {
/* 205 */       byte b0 = -1;
/*     */       
/* 207 */       if (isFacingTowardsRepeater(worldIn, pos, state))
/*     */       {
/* 209 */         b0 = -3;
/*     */       }
/* 211 */       else if (isPowered(state))
/*     */       {
/* 213 */         b0 = -2;
/*     */       }
/*     */       
/* 216 */       worldIn.func_175654_a(pos, this, getTickDelay(state), b0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean shouldBePowered(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 223 */     int pr = 1;
/* 224 */     TileEntity tile = worldIn.func_175625_s(pos);
/* 225 */     if ((tile != null) && ((tile instanceof TileRedstoneRelay)))
/*     */     {
/* 227 */       pr = ((TileRedstoneRelay)tile).getIn();
/*     */     }
/* 229 */     return calculateInputStrength(worldIn, pos, state) >= pr;
/*     */   }
/*     */   
/*     */   protected int calculateInputStrength(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 234 */     EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING);
/* 235 */     BlockPos blockpos1 = pos.func_177972_a(enumfacing);
/* 236 */     int i = worldIn.func_175651_c(blockpos1, enumfacing);
/*     */     
/* 238 */     if (i >= 15)
/*     */     {
/* 240 */       return i;
/*     */     }
/*     */     
/*     */ 
/* 244 */     IBlockState iblockstate1 = worldIn.func_180495_p(blockpos1);
/* 245 */     return Math.max(i, iblockstate1.func_177230_c() == Blocks.field_150488_af ? ((Integer)iblockstate1.func_177229_b(BlockRedstoneWire.field_176351_O)).intValue() : 0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected int getPowerOnSides(IBlockAccess worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 251 */     EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING);
/* 252 */     EnumFacing enumfacing1 = enumfacing.func_176746_e();
/* 253 */     EnumFacing enumfacing2 = enumfacing.func_176735_f();
/* 254 */     return Math.max(getPowerOnSide(worldIn, pos.func_177972_a(enumfacing1), enumfacing1), getPowerOnSide(worldIn, pos.func_177972_a(enumfacing2), enumfacing2));
/*     */   }
/*     */   
/*     */   protected int getPowerOnSide(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/* 259 */     IBlockState iblockstate = worldIn.func_180495_p(pos);
/* 260 */     Block block = iblockstate.func_177230_c();
/* 261 */     return canPowerSide(block) ? worldIn.func_175627_a(pos, side) : block == Blocks.field_150488_af ? ((Integer)iblockstate.func_177229_b(BlockRedstoneWire.field_176351_O)).intValue() : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149744_f()
/*     */   {
/* 267 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
/*     */   {
/* 273 */     if (shouldBePowered(worldIn, pos, state))
/*     */     {
/* 275 */       worldIn.func_175684_a(pos, this, 1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/* 282 */     IBlockState bs = func_176223_P();
/* 283 */     bs = bs.func_177226_a(FACING, placer.func_70093_af() ? placer.func_174811_aO() : placer.func_174811_aO().func_176734_d());
/* 284 */     bs = bs.func_177226_a(ENABLED, Boolean.valueOf(false));
/* 285 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 291 */     notifyNeighbors(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void notifyNeighbors(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 297 */     EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING);
/* 298 */     BlockPos blockpos1 = pos.func_177972_a(enumfacing.func_176734_d());
/* 299 */     if (net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(worldIn, pos, worldIn.func_180495_p(pos), EnumSet.of(enumfacing.func_176734_d())).isCanceled())
/* 300 */       return;
/* 301 */     worldIn.func_180496_d(blockpos1, this);
/* 302 */     worldIn.func_175695_a(blockpos1, this, enumfacing);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176206_d(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 308 */     if (isPowered(state))
/*     */     {
/* 310 */       EnumFacing[] aenumfacing = EnumFacing.values();
/* 311 */       int i = aenumfacing.length;
/*     */       
/* 313 */       for (int j = 0; j < i; j++)
/*     */       {
/* 315 */         EnumFacing enumfacing = aenumfacing[j];
/* 316 */         worldIn.func_175685_c(pos.func_177972_a(enumfacing), this);
/*     */       }
/*     */     }
/*     */     
/* 320 */     super.func_176206_d(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean canPowerSide(Block blockIn)
/*     */   {
/* 326 */     return blockIn.func_149744_f();
/*     */   }
/*     */   
/*     */   protected int getActiveSignal(IBlockAccess worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 331 */     TileEntity tile = worldIn.func_175625_s(pos);
/* 332 */     if ((tile != null) && ((tile instanceof TileRedstoneRelay)))
/*     */     {
/* 334 */       return ((TileRedstoneRelay)tile).getOut();
/*     */     }
/* 336 */     return 0;
/*     */   }
/*     */   
/*     */   public static boolean isRedstoneRepeaterBlockID(Block blockIn)
/*     */   {
/* 341 */     return (Blocks.field_150413_aR.func_149907_e(blockIn)) || (Blocks.field_150441_bU.func_149907_e(blockIn));
/*     */   }
/*     */   
/*     */   public boolean isAssociated(Block other)
/*     */   {
/* 346 */     return (other == getPoweredState(func_176223_P()).func_177230_c()) || (other == getUnpoweredState(func_176223_P()).func_177230_c());
/*     */   }
/*     */   
/*     */   public boolean isFacingTowardsRepeater(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 351 */     EnumFacing enumfacing = ((EnumFacing)state.func_177229_b(FACING)).func_176734_d();
/* 352 */     BlockPos blockpos1 = pos.func_177972_a(enumfacing);
/* 353 */     return worldIn.func_180495_p(blockpos1).func_177229_b(FACING) != enumfacing;
/*     */   }
/*     */   
/*     */   protected int getTickDelay(IBlockState state)
/*     */   {
/* 358 */     return 2;
/*     */   }
/*     */   
/*     */   protected IBlockState getPoweredState(IBlockState unpoweredState)
/*     */   {
/* 363 */     EnumFacing enumfacing = (EnumFacing)unpoweredState.func_177229_b(FACING);
/* 364 */     return func_176223_P().func_177226_a(FACING, enumfacing).func_177226_a(ENABLED, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   protected IBlockState getUnpoweredState(IBlockState poweredState)
/*     */   {
/* 369 */     EnumFacing enumfacing = (EnumFacing)poweredState.func_177229_b(FACING);
/* 370 */     return func_176223_P().func_177226_a(FACING, enumfacing).func_177226_a(ENABLED, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149667_c(Block other)
/*     */   {
/* 376 */     return isAssociated(other);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/* 383 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 388 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 394 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/* 395 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */ 
/* 399 */   private RayTracer rayTracer = new RayTracer();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_180646_a(World world, BlockPos pos)
/*     */   {
/* 404 */     TileEntity tile = world.func_175625_s(pos);
/* 405 */     if ((tile != null) && ((tile instanceof TileRedstoneRelay))) {
/* 406 */       MovingObjectPosition hit = RayTracer.retraceBlock(world, Minecraft.func_71410_x().field_71439_g, pos);
/* 407 */       if ((hit != null) && (hit.subHit == 0)) {
/* 408 */         Cuboid6 cubeoid = ((TileRedstoneRelay)tile).getCuboid0(BlockStateUtils.getFacing(tile.func_145832_p()));
/* 409 */         Vector3 v = new Vector3(pos);
/* 410 */         Cuboid6 c = cubeoid.sub(v);
/* 411 */         func_149676_a((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z);
/*     */ 
/*     */ 
/*     */       }
/* 415 */       else if ((hit != null) && (hit.subHit == 1)) {
/* 416 */         Cuboid6 cubeoid = ((TileRedstoneRelay)tile).getCuboid1(BlockStateUtils.getFacing(tile.func_145832_p()));
/* 417 */         Vector3 v = new Vector3(pos);
/* 418 */         Cuboid6 c = cubeoid.sub(v);
/* 419 */         func_149676_a((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 424 */         func_180654_a(world, pos);
/*     */       }
/*     */     }
/* 427 */     return super.func_180646_a(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onBlockHighlight(DrawBlockHighlightEvent event)
/*     */   {
/* 436 */     if ((event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (event.player.field_70170_p.func_180495_p(event.target.func_178782_a()).func_177230_c() == this))
/*     */     {
/* 438 */       RayTracer.retraceBlock(event.player.field_70170_p, event.player, event.target.func_178782_a());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public MovingObjectPosition func_180636_a(World world, BlockPos pos, Vec3 start, Vec3 end)
/*     */   {
/* 445 */     TileEntity tile = world.func_175625_s(pos);
/* 446 */     if ((tile == null) || (!(tile instanceof TileRedstoneRelay))) {
/* 447 */       return super.func_180636_a(world, pos, start, end);
/*     */     }
/* 449 */     List<thaumcraft.codechicken.lib.raytracer.IndexedCuboid6> cuboids = new java.util.LinkedList();
/* 450 */     if ((tile instanceof TileRedstoneRelay)) {
/* 451 */       ((TileRedstoneRelay)tile).addTraceableCuboids(cuboids);
/*     */     }
/* 453 */     ArrayList<thaumcraft.codechicken.lib.raytracer.ExtendedMOP> list = new ArrayList();
/* 454 */     this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new thaumcraft.codechicken.lib.vec.BlockCoord(pos), this, list);
/* 455 */     return list.size() > 0 ? (MovingObjectPosition)list.get(0) : super.func_180636_a(world, pos, start, end);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockRedstoneRelay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */