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
/*     */ import net.minecraft.entity.EntityLivingBase;
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
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TilePatternCrafter;
/*     */ 
/*     */ public class BlockPatternCrafter
/*     */   extends BlockTCDevice
/*     */   implements IBlockFacingHorizontal, IBlockEnabled
/*     */ {
/*     */   public BlockPatternCrafter()
/*     */   {
/*  44 */     super(Material.field_151573_f, TilePatternCrafter.class);
/*  45 */     func_149672_a(Block.field_149777_j);
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  50 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  56 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side)
/*     */   {
/*  67 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  73 */     IBlockState bs = func_176223_P();
/*  74 */     bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_174811_aO());
/*  75 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  84 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, playerIn, pos);
/*  85 */     if (hit == null) { return super.func_180639_a(world, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */     }
/*  87 */     TileEntity tile = world.func_175625_s(pos);
/*     */     
/*  89 */     if ((hit.subHit == 0) && ((tile instanceof TilePatternCrafter)))
/*     */     {
/*  91 */       ((TilePatternCrafter)tile).cycle();
/*  92 */       world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:key", 0.5F, 1.0F);
/*  93 */       return true;
/*     */     }
/*     */     
/*  96 */     return super.func_180639_a(world, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_180646_a(World world, BlockPos pos)
/*     */   {
/* 102 */     TileEntity tile = world.func_175625_s(pos);
/* 103 */     if ((tile != null) && ((tile instanceof TilePatternCrafter))) {
/* 104 */       MovingObjectPosition hit = RayTracer.retraceBlock(world, Minecraft.func_71410_x().field_71439_g, pos);
/* 105 */       if ((hit != null) && (hit.subHit == 0)) {
/* 106 */         Cuboid6 cubeoid = ((TilePatternCrafter)tile).getCuboidByFacing(BlockStateUtils.getFacing(tile.func_145832_p()));
/* 107 */         Vector3 v = new Vector3(pos);
/* 108 */         Cuboid6 c = cubeoid.sub(v);
/* 109 */         func_149676_a((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z);
/*     */       }
/*     */       else
/*     */       {
/* 113 */         func_180654_a(world, pos);
/*     */       }
/*     */     }
/* 116 */     return super.func_180646_a(world, pos);
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 121 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 127 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 128 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 133 */   private RayTracer rayTracer = new RayTracer();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onBlockHighlight(DrawBlockHighlightEvent event)
/*     */   {
/* 139 */     if ((event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (event.player.field_70170_p.func_180495_p(event.target.func_178782_a()).func_177230_c() == this))
/*     */     {
/* 141 */       RayTracer.retraceBlock(event.player.field_70170_p, event.player, event.target.func_178782_a());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public MovingObjectPosition func_180636_a(World world, BlockPos pos, Vec3 start, Vec3 end)
/*     */   {
/* 148 */     TileEntity tile = world.func_175625_s(pos);
/* 149 */     if ((tile == null) || (!(tile instanceof TilePatternCrafter))) {
/* 150 */       return super.func_180636_a(world, pos, start, end);
/*     */     }
/* 152 */     List<IndexedCuboid6> cuboids = new LinkedList();
/* 153 */     if ((tile instanceof TilePatternCrafter)) {
/* 154 */       ((TilePatternCrafter)tile).addTraceableCuboids(cuboids);
/*     */     }
/* 156 */     ArrayList<ExtendedMOP> list = new ArrayList();
/* 157 */     this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(pos), this, list);
/* 158 */     return list.size() > 0 ? (MovingObjectPosition)list.get(0) : super.func_180636_a(world, pos, start, end);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockPatternCrafter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */