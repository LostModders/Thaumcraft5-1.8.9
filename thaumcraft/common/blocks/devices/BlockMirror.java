/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ 
/*     */ public class BlockMirror extends BlockTCDevice implements IBlockFacing
/*     */ {
/*     */   public BlockMirror(Class cls)
/*     */   {
/*  32 */     super(net.minecraft.block.material.Material.field_151573_f, cls);
/*  33 */     func_149672_a(new thaumcraft.common.lib.CustomSoundType("jar", 1.0F, 1.0F));
/*  34 */     func_149711_c(0.1F);
/*  35 */     setHarvestLevel(null, 0);
/*  36 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  37 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  38 */     func_180632_j(bs);
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  51 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  57 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  68 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  74 */     IBlockState bs = func_176223_P();
/*  75 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing);
/*  76 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/*  84 */     EnumFacing d = BlockStateUtils.getFacing(state);
/*  85 */     if (!worldIn.func_180495_p(pos.func_177972_a(d.func_176734_d())).func_177230_c().isSideSolid(worldIn, pos.func_177972_a(d.func_176734_d()), d)) {
/*  86 */       func_176226_b(worldIn, pos, func_176223_P(), 0);
/*  87 */       worldIn.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/*  93 */     EnumFacing facing = BlockStateUtils.getFacing(func_176201_c(worldIn.func_180495_p(pos)));
/*  94 */     switch (facing.ordinal()) {
/*  95 */     case 0:  func_149676_a(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F); break;
/*  96 */     case 1:  func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F); break;
/*  97 */     case 2:  func_149676_a(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F); break;
/*  98 */     case 3:  func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F); break;
/*  99 */     case 4:  func_149676_a(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F); break;
/* 100 */     case 5:  func_149676_a(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 1.0F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 107 */     func_180654_a(worldIn, pos);
/* 108 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 114 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/* 119 */     return worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177230_c().isSideSolid(worldIn, pos.func_177972_a(side.func_176734_d()), side);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 126 */     if (world.field_72995_K) return true;
/* 127 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
/*     */   {
/* 134 */     TileEntity te = worldIn.func_175625_s(pos);
/* 135 */     if (((te instanceof TileMirror)) || ((te instanceof TileMirrorEssentia)))
/*     */     {
/* 137 */       dropMirror(worldIn, pos, state, te);
/*     */     }
/*     */     else
/*     */     {
/* 141 */       super.func_180653_a(worldIn, pos, state, chance, fortune);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
/*     */   {
/* 148 */     if (((te instanceof TileMirror)) || ((te instanceof TileMirrorEssentia)))
/*     */     {
/* 150 */       dropMirror(worldIn, pos, state, te);
/*     */     }
/*     */     else
/*     */     {
/* 154 */       super.func_180657_a(worldIn, player, pos, state, (TileEntity)null);
/*     */     }
/*     */   }
/*     */   
/*     */   public void dropMirror(World world, BlockPos pos, IBlockState state, TileEntity te)
/*     */   {
/* 160 */     if (this.tileClass == TileMirror.class) {
/* 161 */       TileMirror tm = (TileMirror)te;
/* 162 */       ItemStack drop = new ItemStack(this, 1, 0);
/* 163 */       if ((tm != null) && ((tm instanceof TileMirror))) {
/* 164 */         if (tm.linked) {
/* 165 */           drop.func_77964_b(1);
/* 166 */           drop.func_77983_a("linkX", new NBTTagInt(tm.linkX));
/* 167 */           drop.func_77983_a("linkY", new NBTTagInt(tm.linkY));
/* 168 */           drop.func_77983_a("linkZ", new NBTTagInt(tm.linkZ));
/* 169 */           drop.func_77983_a("linkDim", new NBTTagInt(tm.linkDim));
/* 170 */           drop.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(tm.func_145831_w().field_73011_w.func_177502_q()).func_80007_l()));
/* 171 */           tm.invalidateLink();
/*     */         }
/* 173 */         func_180635_a(world, pos, drop);
/*     */       }
/*     */     } else {
/* 176 */       TileMirrorEssentia tm = (TileMirrorEssentia)te;
/* 177 */       ItemStack drop = new ItemStack(this, 1, 0);
/* 178 */       if ((tm != null) && ((tm instanceof TileMirrorEssentia))) {
/* 179 */         if (tm.linked) {
/* 180 */           drop.func_77964_b(1);
/* 181 */           drop.func_77983_a("linkX", new NBTTagInt(tm.linkX));
/* 182 */           drop.func_77983_a("linkY", new NBTTagInt(tm.linkY));
/* 183 */           drop.func_77983_a("linkZ", new NBTTagInt(tm.linkZ));
/* 184 */           drop.func_77983_a("linkDim", new NBTTagInt(tm.linkDim));
/* 185 */           drop.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(tm.func_145831_w().field_73011_w.func_177502_q()).func_80007_l()));
/* 186 */           tm.invalidateLink();
/*     */         }
/* 188 */         func_180635_a(world, pos, drop);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
/*     */   {
/* 196 */     if ((!world.field_72995_K) && (this.tileClass == TileMirror.class) && ((entity instanceof EntityItem)) && (!entity.field_70128_L) && (((EntityItem)entity).field_71088_bW == 0))
/*     */     {
/* 198 */       TileMirror taf = (TileMirror)world.func_175625_s(pos);
/* 199 */       if (taf != null) {
/* 200 */         taf.transport((EntityItem)entity);
/*     */       }
/*     */     }
/* 203 */     super.func_180634_a(world, pos, state, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */