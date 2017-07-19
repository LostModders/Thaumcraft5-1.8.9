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
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.codechicken.lib.raytracer.ExtendedMOP;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.items.resources.ItemShard;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.devices.TileDioptra;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockDioptra
/*     */   extends BlockTCDevice
/*     */   implements IBlockEnabled
/*     */ {
/*     */   public BlockDioptra()
/*     */   {
/*  49 */     super(Material.field_151576_e, TileDioptra.class);
/*  50 */     func_149672_a(Block.field_149769_e);
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  55 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  61 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  67 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  73 */     TileEntity tile = worldIn.func_175625_s(pos);
/*     */     
/*  75 */     if ((tile instanceof TileDioptra))
/*     */     {
/*  77 */       int d = -1;
/*  78 */       switch (((TileDioptra)tile).type) {
/*  79 */       case 0:  d = 7; break;
/*  80 */       case 1:  d = 0; break;
/*  81 */       case 2:  d = 1; break;
/*  82 */       case 3:  d = 2; break;
/*  83 */       case 4:  d = 3; break;
/*  84 */       case 5:  d = 4; break;
/*  85 */       case 6:  d = 5; break;
/*  86 */       case 7:  d = 6;
/*     */       }
/*  88 */       if (d >= 0) {
/*  89 */         InventoryUtils.dropItemAtPos(worldIn, new ItemStack(ItemsTC.shard, 1, d), pos);
/*     */       }
/*     */     }
/*     */     
/*  93 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 101 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, playerIn, pos);
/* 102 */     if (hit == null) { return super.func_180639_a(world, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */     }
/* 104 */     TileEntity tile = world.func_175625_s(pos);
/*     */     
/* 106 */     if ((hit.subHit == 0) && ((tile instanceof TileDioptra)))
/*     */     {
/* 108 */       int d = -1;
/* 109 */       switch (((TileDioptra)tile).type) {
/* 110 */       case 0:  d = 7; break;
/* 111 */       case 1:  d = 0; break;
/* 112 */       case 2:  d = 1; break;
/* 113 */       case 3:  d = 2; break;
/* 114 */       case 4:  d = 3; break;
/* 115 */       case 5:  d = 4; break;
/* 116 */       case 6:  d = 5; break;
/* 117 */       case 7:  d = 6;
/*     */       }
/* 119 */       if (d >= 0) {
/* 120 */         InventoryUtils.dropItemAtEntity(world, new ItemStack(ItemsTC.shard, 1, d), playerIn);
/*     */       }
/*     */       
/* 123 */       byte t = -1;
/* 124 */       if ((playerIn.func_70694_bm() != null) && ((playerIn.func_70694_bm().func_77973_b() instanceof ItemShard))) {
/* 125 */         switch (playerIn.func_70694_bm().func_77952_i()) {
/* 126 */         case 0:  t = 1; break;
/* 127 */         case 1:  t = 2; break;
/* 128 */         case 2:  t = 3; break;
/* 129 */         case 3:  t = 4; break;
/* 130 */         case 4:  t = 5; break;
/* 131 */         case 5:  t = 6; break;
/* 132 */         case 7:  t = 0; break;
/* 133 */         case 6:  t = 7;
/*     */         }
/* 135 */         if (t >= 0) playerIn.func_70694_bm().field_77994_a -= 1;
/*     */       }
/* 137 */       ((TileDioptra)tile).switchType(playerIn, t);
/*     */       
/* 139 */       if ((d >= 0) || (t >= 0)) { world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "thaumcraft:crystal", 0.5F, 1.0F);
/*     */       }
/*     */     }
/*     */     
/* 143 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_180646_a(World world, BlockPos pos)
/*     */   {
/* 149 */     TileEntity tile = world.func_175625_s(pos);
/* 150 */     if ((tile != null) && ((tile instanceof TileDioptra))) {
/* 151 */       MovingObjectPosition hit = RayTracer.retraceBlock(world, Minecraft.func_71410_x().field_71439_g, pos);
/* 152 */       if ((hit != null) && (hit.subHit == 0)) {
/* 153 */         List<IndexedCuboid6> cuboids = new LinkedList();
/* 154 */         Cuboid6 cubeoid = new Cuboid6(pos.func_177958_n() + 0.375D, pos.func_177956_o() + 0.875D, pos.func_177952_p() + 0.375D, pos.func_177958_n() + 0.625D, pos.func_177956_o() + 1.1D, pos.func_177952_p() + 0.625D);
/*     */         
/*     */ 
/* 157 */         Vector3 v = new Vector3(pos);
/* 158 */         Cuboid6 c = cubeoid.sub(v);
/* 159 */         func_149676_a((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z);
/*     */       }
/*     */       else
/*     */       {
/* 163 */         func_180654_a(world, pos);
/*     */       }
/*     */     }
/* 166 */     return super.func_180646_a(world, pos);
/*     */   }
/*     */   
/*     */ 
/* 170 */   private RayTracer rayTracer = new RayTracer();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onBlockHighlight(DrawBlockHighlightEvent event)
/*     */   {
/* 176 */     if ((event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (event.player.field_70170_p.func_180495_p(event.target.func_178782_a()).func_177230_c() == this))
/*     */     {
/* 178 */       RayTracer.retraceBlock(event.player.field_70170_p, event.player, event.target.func_178782_a());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public MovingObjectPosition func_180636_a(World world, BlockPos pos, Vec3 start, Vec3 end)
/*     */   {
/* 185 */     TileEntity tile = world.func_175625_s(pos);
/* 186 */     if ((tile == null) || (!(tile instanceof TileDioptra))) {
/* 187 */       return super.func_180636_a(world, pos, start, end);
/*     */     }
/* 189 */     List<IndexedCuboid6> cuboids = new LinkedList();
/* 190 */     if ((tile instanceof TileDioptra)) {
/* 191 */       ((TileDioptra)tile).addTraceableCuboids(cuboids);
/*     */     }
/* 193 */     ArrayList<ExtendedMOP> list = new ArrayList();
/* 194 */     this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(pos), this, list);
/* 195 */     return list.size() > 0 ? (MovingObjectPosition)list.get(0) : super.func_180636_a(world, pos, start, end);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 201 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 207 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 208 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149740_M()
/*     */   {
/* 214 */     return true;
/*     */   }
/*     */   
/*     */   public int func_180641_l(World world, BlockPos pos)
/*     */   {
/* 219 */     TileEntity tile = world.func_175625_s(pos);
/* 220 */     if ((tile != null) && ((tile instanceof TileDioptra))) {
/* 221 */       float r = ((TileDioptra)tile).grid_amt[84] / 110.0F;
/* 222 */       return MathHelper.func_76141_d(r * 14.0F) + (r > 0.0F ? 1 : 0);
/*     */     }
/* 224 */     return super.func_180641_l(world, pos);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockDioptra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */