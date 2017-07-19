/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.common.property.ExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IUnlistedProperty;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.items.tools.ItemResonator;
/*     */ import thaumcraft.common.tiles.essentia.TileTube;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeBuffer;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeFilter;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeRestrict;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeValve;
/*     */ 
/*     */ public class BlockTube extends BlockTCDevice
/*     */ {
/*  63 */   public static final PropertyEnum TYPE = PropertyEnum.func_177709_a("type", TubeType.class);
/*     */   
/*     */   public BlockTube()
/*     */   {
/*  67 */     super(Material.field_151573_f, TileTube.class);
/*  68 */     func_149711_c(0.5F);
/*  69 */     func_149752_b(5.0F);
/*  70 */     func_149672_a(Block.field_149777_j);
/*  71 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(TYPE, TubeType.NORMAL));
/*     */   }
/*     */   
/*     */ 
/*     */   public TileEntity func_149915_a(World worldIn, int meta)
/*     */   {
/*  77 */     switch (meta) {
/*  78 */     case 0:  return new TileTube();
/*  79 */     case 1:  return new TileTubeValve();
/*  80 */     case 2:  return new TileTubeRestrict();
/*  81 */     case 3:  return new thaumcraft.common.tiles.essentia.TileTubeOneway();
/*  82 */     case 4:  return new TileTubeFilter();
/*  83 */     case 5:  return new TileTubeBuffer();
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/* 102 */     return func_176203_a(meta);
/*     */   }
/*     */   
/*     */   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
/*     */   {
/* 107 */     TileEntity tile = worldIn.func_175625_s(pos);
/* 108 */     if ((tile != null) && ((tile instanceof TileTube))) {
/* 109 */       ((TileTube)tile).facing = BlockPistonBase.func_180695_a(worldIn, pos, placer);
/* 110 */       tile.func_70296_d();
/*     */     }
/* 112 */     super.func_180633_a(worldIn, pos, state, placer, stack);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 118 */     return meta >= TubeType.values().length ? func_176223_P() : func_176223_P().func_177226_a(TYPE, TubeType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 124 */     return ((TubeType)state.func_177229_b(TYPE)).ordinal();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 130 */     return new ExtendedBlockState(this, new IProperty[] { TYPE }, new IUnlistedProperty[] { CONNECTIONS });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 139 */     TubeType type = (TubeType)state.func_177229_b(TYPE);
/* 140 */     return "tube_" + type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 146 */     return new IProperty[] { TYPE };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
/*     */   {
/* 153 */     if ((state instanceof IExtendedBlockState)) {
/* 154 */       Boolean[] cons = { Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false) };
/* 155 */       TileEntity t = world.func_175625_s(pos);
/* 156 */       if ((t != null) && ((t instanceof IEssentiaTransport))) {
/* 157 */         IEssentiaTransport tube = (IEssentiaTransport)t;
/* 158 */         int a = 0;
/* 159 */         for (EnumFacing face : EnumFacing.field_82609_l) {
/* 160 */           if ((tube.isConnectable(face)) && (ThaumcraftApiHelper.getConnectableTile(world, pos, face) != null)) {
/* 161 */             cons[a] = Boolean.valueOf(true);
/*     */           }
/* 163 */           a++;
/*     */         }
/*     */       }
/* 166 */       return ((IExtendedBlockState)state).withProperty(CONNECTIONS, cons);
/*     */     }
/* 168 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_180646_a(World world, BlockPos pos)
/*     */   {
/* 177 */     boolean noDoodads = (Minecraft.func_71410_x().field_71439_g == null) || (Minecraft.func_71410_x().field_71439_g.func_71045_bC() == null) || ((!(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof IWand)) && (!(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemResonator)));
/*     */     
/*     */ 
/*     */ 
/* 181 */     TileEntity tile = world.func_175625_s(pos);
/* 182 */     if ((tile != null) && ((tile instanceof TileTube))) {
/* 183 */       MovingObjectPosition hit = RayTracer.retraceBlock(world, Minecraft.func_71410_x().field_71439_g, pos);
/* 184 */       List<IndexedCuboid6> cuboids = new LinkedList();
/* 185 */       ((TileTube)tile).addTraceableCuboids(cuboids);
/* 186 */       if ((hit != null) && (hit.subHit >= 0) && (hit.subHit <= 6) && (!noDoodads)) {
/* 187 */         for (IndexedCuboid6 cc : cuboids) {
/* 188 */           if (((Integer)cc.data).intValue() == hit.subHit) {
/* 189 */             Vector3 v = new Vector3(pos);
/* 190 */             Cuboid6 c = cc.sub(v);
/* 191 */             func_149676_a((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z);
/*     */             
/*     */ 
/* 194 */             break;
/*     */           }
/*     */         }
/*     */       } else {
/* 198 */         func_180654_a(world, pos);
/*     */       }
/*     */     }
/*     */     
/* 202 */     return super.func_180646_a(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180654_a(IBlockAccess world, BlockPos pos)
/*     */   {
/* 208 */     float minx = 0.375F;float maxx = 0.625F;
/* 209 */     float miny = 0.3125F;float maxy = 0.6875F;
/* 210 */     float minz = 0.3125F;float maxz = 0.6875F;
/* 211 */     EnumFacing fd = null;
/* 212 */     for (int side = 0; side < 6; side++) {
/* 213 */       fd = EnumFacing.field_82609_l[side];
/* 214 */       TileEntity te = ThaumcraftApiHelper.getConnectableTile(world, pos, fd);
/* 215 */       if (te != null) {
/* 216 */         switch (side) {
/*     */         case 0: 
/* 218 */           miny = 0.0F; break;
/* 219 */         case 1:  maxy = 1.0F; break;
/* 220 */         case 2:  minz = 0.0F; break;
/* 221 */         case 3:  maxz = 1.0F; break;
/* 222 */         case 4:  minx = 0.0F; break;
/* 223 */         case 5:  maxx = 1.0F;
/*     */         }
/*     */       }
/*     */     }
/* 227 */     func_149676_a(minx, miny, minz, maxx, maxy, maxz);
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
/*     */   public void func_180638_a(World world, BlockPos pos, IBlockState state, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 241 */     float minx = 0.375F;float maxx = 0.625F;
/* 242 */     float miny = 0.375F;float maxy = 0.625F;
/* 243 */     float minz = 0.375F;float maxz = 0.625F;
/*     */     
/* 245 */     EnumFacing fd = null;
/* 246 */     for (int side = 0; side < 6; side++) {
/* 247 */       fd = EnumFacing.field_82609_l[side];
/* 248 */       TileEntity te = ThaumcraftApiHelper.getConnectableTile(world, pos, fd);
/* 249 */       if (te != null) {
/* 250 */         switch (side) {
/*     */         case 0: 
/* 252 */           miny = 0.0F; break;
/* 253 */         case 1:  maxy = 1.0F; break;
/* 254 */         case 2:  minz = 0.0F; break;
/* 255 */         case 3:  maxz = 1.0F; break;
/* 256 */         case 4:  minx = 0.0F; break;
/* 257 */         case 5:  maxx = 1.0F;
/*     */         }
/*     */         
/*     */       }
/*     */     }
/* 262 */     func_149676_a(minx, miny, minz, maxx, maxy, maxz);
/* 263 */     super.func_180638_a(world, pos, state, axisalignedbb, arraylist, par7Entity);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 268 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180641_l(World world, BlockPos pos)
/*     */   {
/* 274 */     TileEntity te = world.func_175625_s(pos);
/* 275 */     if ((te != null) && ((te instanceof TileTubeBuffer))) {
/* 276 */       ((TileTubeBuffer)te).getClass();float r = ((TileTubeBuffer)te).aspects.visSize() / 8.0F;
/* 277 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileTubeBuffer)te).aspects.visSize() > 0 ? 1 : 0);
/*     */     }
/* 279 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180662_a(IBlockAccess worldIn, BlockPos pos, int renderPass)
/*     */   {
/* 286 */     if (renderPass == 1) {
/* 287 */       TileEntity te = worldIn.func_175625_s(pos);
/* 288 */       if ((te != null) && ((te instanceof TileTubeFilter)) && (((TileTubeFilter)te).aspectFilter != null)) {
/* 289 */         return ((TileTubeFilter)te).aspectFilter.getColor();
/*     */       }
/*     */     }
/* 292 */     return super.func_180662_a(worldIn, pos, renderPass);
/*     */   }
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 297 */     TileEntity te = worldIn.func_175625_s(pos);
/* 298 */     if ((te != null) && ((te instanceof TileTube)) && (((TileTube)te).getEssentiaAmount(EnumFacing.UP) > 0)) {
/* 299 */       if (!worldIn.field_72995_K) {
/* 300 */         AuraHelper.pollute(worldIn, pos, ((TileTube)te).getEssentiaAmount(EnumFacing.UP), true);
/*     */       } else {
/* 302 */         worldIn.func_72980_b(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "random.fizz", 0.1F, 1.0F + worldIn.field_73012_v.nextFloat() * 0.1F, false);
/* 303 */         for (int a = 0; a < 5; a++) {
/* 304 */           Thaumcraft.proxy.getFX().drawVentParticles(pos.func_177958_n() + 0.33D + worldIn.field_73012_v.nextFloat() * 0.33D, pos.func_177956_o() + 0.33D + worldIn.field_73012_v.nextFloat() * 0.33D, pos.func_177952_p() + 0.33D + worldIn.field_73012_v.nextFloat() * 0.33D, 0.0D, 0.0D, 0.0D, Aspect.FLUX.getColor());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 310 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 317 */     if (state.func_177229_b(TYPE) == TubeType.VALVE) {
/* 318 */       if ((player.func_70694_bm() != null) && (((player.func_70694_bm().func_77973_b() instanceof IWand)) || ((player.func_70694_bm().func_77973_b() instanceof ItemResonator)) || (player.func_70694_bm().func_77973_b() == Item.func_150898_a(this))))
/*     */       {
/*     */ 
/*     */ 
/* 322 */         return false; }
/* 323 */       TileEntity te = world.func_175625_s(pos);
/* 324 */       if ((te instanceof TileTubeValve)) {
/* 325 */         ((TileTubeValve)te).allowFlow = (!((TileTubeValve)te).allowFlow);
/* 326 */         world.func_175689_h(pos);
/* 327 */         te.func_70296_d();
/* 328 */         if (!world.field_72995_K) {
/* 329 */           world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:squeek", 0.7F, 0.9F + world.field_73012_v.nextFloat() * 0.2F);
/*     */         }
/* 331 */         return true;
/*     */       }
/*     */     }
/* 334 */     if (state.func_177229_b(TYPE) == TubeType.FILTER) {
/* 335 */       TileEntity te = world.func_175625_s(pos);
/* 336 */       if ((te != null) && ((te instanceof TileTubeFilter)) && (player.func_70093_af()) && (((TileTubeFilter)te).aspectFilter != null))
/*     */       {
/* 338 */         ((TileTubeFilter)te).aspectFilter = null;
/* 339 */         world.func_175689_h(pos);
/* 340 */         te.func_70296_d();
/* 341 */         if (world.field_72995_K) {
/* 342 */           world.func_72980_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:page", 1.0F, 1.0F, false);
/*     */         }
/* 344 */         return true;
/*     */       }
/* 346 */       if ((te != null) && ((te instanceof TileTubeFilter)) && (player.func_70694_bm() != null) && (((TileTubeFilter)te).aspectFilter == null) && ((player.func_70694_bm().func_77973_b() instanceof IEssentiaContainerItem)))
/*     */       {
/*     */ 
/*     */ 
/* 350 */         if (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null) {
/* 351 */           ((TileTubeFilter)te).aspectFilter = ((IEssentiaContainerItem)(IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
/* 352 */           world.func_175689_h(pos);
/* 353 */           te.func_70296_d();
/* 354 */           if (world.field_72995_K) {
/* 355 */             world.func_72980_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:page", 1.0F, 1.0F, false);
/*     */           }
/*     */         }
/* 358 */         return true;
/*     */       }
/*     */     }
/* 361 */     return super.func_180639_a(world, pos, state, player, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/* 365 */   private RayTracer rayTracer = new RayTracer();
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onBlockHighlight(DrawBlockHighlightEvent event)
/*     */   {
/* 373 */     if ((event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (event.player.field_70170_p.func_180495_p(event.target.func_178782_a()).func_177230_c() == this) && (event.player.func_71045_bC() != null) && (((event.player.func_71045_bC().func_77973_b() instanceof IWand)) || ((event.player.func_71045_bC().func_77973_b() instanceof ItemResonator))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 378 */       RayTracer.retraceBlock(event.player.field_70170_p, event.player, event.target.func_178782_a());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public MovingObjectPosition func_180636_a(World world, BlockPos pos, Vec3 start, Vec3 end)
/*     */   {
/* 385 */     TileEntity tile = world.func_175625_s(pos);
/* 386 */     if ((tile == null) || ((!(tile instanceof TileTube)) && (!(tile instanceof TileTubeBuffer)))) {
/* 387 */       return super.func_180636_a(world, pos, start, end);
/*     */     }
/* 389 */     List<IndexedCuboid6> cuboids = new LinkedList();
/* 390 */     if ((tile instanceof TileTube)) {
/* 391 */       ((TileTube)tile).addTraceableCuboids(cuboids);
/* 392 */     } else if ((tile instanceof TileTubeBuffer)) {
/* 393 */       ((TileTubeBuffer)tile).addTraceableCuboids(cuboids);
/*     */     }
/* 395 */     ArrayList<thaumcraft.codechicken.lib.raytracer.ExtendedMOP> list = new ArrayList();
/* 396 */     this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new thaumcraft.codechicken.lib.vec.BlockCoord(pos), this, list);
/* 397 */     return list.size() > 0 ? (MovingObjectPosition)list.get(0) : super.func_180636_a(world, pos, start, end);
/*     */   }
/*     */   
/* 400 */   public static final IUnlistedProperty<Boolean[]> CONNECTIONS = new IUnlistedProperty()
/*     */   {
/*     */     public String getName() {
/* 403 */       return "connections";
/*     */     }
/*     */     
/*     */     public boolean isValid(Boolean[] value) {
/* 407 */       return true;
/*     */     }
/*     */     
/*     */     public Class<Boolean[]> getType() {
/* 411 */       return Boolean[].class;
/*     */     }
/*     */     
/*     */     public String valueToString(Boolean[] value) {
/* 415 */       return value.toString();
/*     */     }
/*     */   };
/*     */   
/*     */   public static enum TubeType implements IStringSerializable
/*     */   {
/* 421 */     NORMAL, 
/* 422 */     VALVE, 
/* 423 */     RESTRICT, 
/* 424 */     ONEWAY, 
/* 425 */     FILTER, 
/* 426 */     BUFFER;
/*     */     
/*     */     private TubeType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 431 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 437 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockTube.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */