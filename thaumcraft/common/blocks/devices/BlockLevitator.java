/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.codechicken.lib.raytracer.ExtendedMOP;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileLevitator;
/*     */ 
/*     */ 
/*     */ public class BlockLevitator
/*     */   extends BlockTCDevice
/*     */   implements IBlockFacing, IBlockEnabled
/*     */ {
/*     */   public BlockLevitator()
/*     */   {
/*  44 */     super(Material.field_151575_d, TileLevitator.class);
/*  45 */     func_149672_a(Block.field_149766_f);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  51 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  57 */     return false;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  62 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  70 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, playerIn, pos);
/*  71 */     if (hit == null) { return super.func_180639_a(world, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */     }
/*  73 */     TileEntity tile = world.func_175625_s(pos);
/*     */     
/*  75 */     if ((hit.subHit == 0) && ((tile instanceof TileLevitator)))
/*     */     {
/*  77 */       ((TileLevitator)tile).increaseRange(playerIn);
/*  78 */       world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:key", 0.5F, 1.0F);
/*  79 */       return true;
/*     */     }
/*     */     
/*  82 */     return super.func_180639_a(world, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_180646_a(World world, BlockPos pos)
/*     */   {
/*  88 */     TileEntity tile = world.func_175625_s(pos);
/*  89 */     if ((tile != null) && ((tile instanceof TileLevitator))) {
/*  90 */       MovingObjectPosition hit = RayTracer.retraceBlock(world, Minecraft.func_71410_x().field_71439_g, pos);
/*  91 */       if ((hit != null) && (hit.subHit == 0)) {
/*  92 */         Cuboid6 cubeoid = ((TileLevitator)tile).getCuboidByFacing(BlockStateUtils.getFacing(tile.func_145832_p()));
/*  93 */         Vector3 v = new Vector3(pos);
/*  94 */         Cuboid6 c = cubeoid.sub(v);
/*  95 */         func_149676_a((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z);
/*     */       }
/*     */       else
/*     */       {
/*  99 */         func_180654_a(world, pos);
/*     */       }
/*     */     }
/* 102 */     return super.func_180646_a(world, pos);
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 107 */     EnumFacing facing = BlockStateUtils.getFacing(func_176201_c(worldIn.func_180495_p(pos)));
/* 108 */     float f = 0.125F;
/* 109 */     float minx = 0.0F + (facing.func_82601_c() > 0 ? f : 0.0F);
/* 110 */     float maxx = 1.0F - (facing.func_82601_c() < 0 ? f : 0.0F);
/* 111 */     float miny = 0.0F + (facing.func_96559_d() > 0 ? f : 0.0F);
/* 112 */     float maxy = 1.0F - (facing.func_96559_d() < 0 ? f : 0.0F);
/* 113 */     float minz = 0.0F + (facing.func_82599_e() > 0 ? f : 0.0F);
/* 114 */     float maxz = 1.0F - (facing.func_82599_e() < 0 ? f : 0.0F);
/* 115 */     func_149676_a(minx, miny, minz, maxx, maxy, maxz);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 121 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 122 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 127 */   private RayTracer rayTracer = new RayTracer();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onBlockHighlight(DrawBlockHighlightEvent event)
/*     */   {
/* 133 */     if ((event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (event.player.field_70170_p.func_180495_p(event.target.func_178782_a()).func_177230_c() == this))
/*     */     {
/* 135 */       RayTracer.retraceBlock(event.player.field_70170_p, event.player, event.target.func_178782_a());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public MovingObjectPosition func_180636_a(World world, BlockPos pos, Vec3 start, Vec3 end)
/*     */   {
/* 142 */     TileEntity tile = world.func_175625_s(pos);
/* 143 */     if ((tile == null) || (!(tile instanceof TileLevitator))) {
/* 144 */       return super.func_180636_a(world, pos, start, end);
/*     */     }
/* 146 */     List<IndexedCuboid6> cuboids = new LinkedList();
/* 147 */     if ((tile instanceof TileLevitator)) {
/* 148 */       ((TileLevitator)tile).addTraceableCuboids(cuboids);
/*     */     }
/* 150 */     ArrayList<ExtendedMOP> list = new ArrayList();
/* 151 */     this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(pos), this, list);
/* 152 */     return list.size() > 0 ? (MovingObjectPosition)list.get(0) : super.func_180636_a(world, pos, start, end);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockLevitator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */