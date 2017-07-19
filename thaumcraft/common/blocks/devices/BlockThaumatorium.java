/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileThaumatorium;
/*     */ import thaumcraft.common.tiles.devices.TileThaumatoriumTop;
/*     */ 
/*     */ 
/*     */ public class BlockThaumatorium
/*     */   extends BlockTCDevice
/*     */   implements IBlockFacingHorizontal, IBlockEnabled
/*     */ {
/*     */   public BlockThaumatorium()
/*     */   {
/*  31 */     super(Material.field_151573_f, null);
/*  32 */     func_149672_a(Block.field_149777_j);
/*  33 */     func_149647_a(null);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata)
/*     */   {
/*  38 */     if (BlockStateUtils.isEnabled(metadata)) return new TileThaumatorium();
/*  39 */     if (!BlockStateUtils.isEnabled(metadata)) return new TileThaumatoriumTop();
/*  40 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  60 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float fx, float fy, float fz)
/*     */   {
/*  65 */     if ((!world.field_72995_K) && (!player.func_70093_af())) {
/*  66 */       if (BlockStateUtils.isEnabled(state)) {
/*  67 */         player.openGui(Thaumcraft.instance, 3, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */       }
/*  69 */       if (!BlockStateUtils.isEnabled(state)) {
/*  70 */         player.openGui(Thaumcraft.instance, 3, world, pos.func_177977_b().func_177958_n(), pos.func_177977_b().func_177956_o(), pos.func_177977_b().func_177952_p());
/*     */       }
/*     */     }
/*  73 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/*  79 */     return Item.func_150898_a(BlocksTC.metal);
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  84 */     return 2;
/*     */   }
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/*  89 */     if ((!BlockStateUtils.isEnabled(state)) && (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() != BlocksTC.thaumatorium)) {
/*  90 */       worldIn.func_175656_a(pos, BlocksTC.metal.func_176203_a(2));
/*     */     }
/*  92 */     if ((BlockStateUtils.isEnabled(state)) && ((worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != BlocksTC.thaumatorium) || (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() != BlocksTC.crucible)))
/*     */     {
/*  94 */       worldIn.func_175656_a(pos, BlocksTC.metal.func_176203_a(2));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   public int func_180641_l(World world, BlockPos pos)
/*     */   {
/* 105 */     TileEntity tile = world.func_175625_s(pos);
/* 106 */     if ((tile != null) && ((tile instanceof TileThaumatorium))) {
/* 107 */       return Container.func_94526_b((IInventory)tile);
/*     */     }
/* 109 */     return super.func_180641_l(world, pos);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockThaumatorium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */