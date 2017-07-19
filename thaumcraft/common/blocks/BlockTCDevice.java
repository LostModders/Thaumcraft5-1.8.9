/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class BlockTCDevice
/*     */   extends BlockTC implements ITileEntityProvider
/*     */ {
/*     */   protected final Class tileClass;
/*     */   
/*     */   public BlockTCDevice(Material mat, Class tc)
/*     */   {
/*  33 */     super(mat);
/*     */     
/*  35 */     func_149711_c(2.0F);
/*  36 */     func_149752_b(20.0F);
/*     */     
/*  38 */     this.tileClass = tc;
/*  39 */     this.field_149758_A = true;
/*     */     
/*  41 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  42 */     if ((this instanceof IBlockFacingHorizontal)) { bs.func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.NORTH);
/*  43 */     } else if ((this instanceof IBlockFacing)) bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  44 */     if ((this instanceof IBlockEnabled)) bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true));
/*  45 */     func_180632_j(bs);
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*     */   {
/*  50 */     return true;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World worldIn, int meta)
/*     */   {
/*     */     try {
/*  56 */       return (TileEntity)this.tileClass.newInstance();
/*     */     } catch (InstantiationException e) {
/*  58 */       Thaumcraft.log.catching(e);
/*     */     } catch (IllegalAccessException e) {
/*  60 */       Thaumcraft.log.catching(e);
/*     */     }
/*  62 */     return null;
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(IBlockState state)
/*     */   {
/*  67 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  73 */     updateState(worldIn, pos, state);
/*     */   }
/*     */   
/*  76 */   protected static boolean keepInventory = false;
/*  77 */   protected static boolean spillEssentia = true;
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  82 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/*     */     
/*  84 */     if ((tileentity != null) && ((tileentity instanceof IInventory)) && (!keepInventory))
/*     */     {
/*  86 */       InventoryUtils.dropItems(worldIn, pos);
/*     */     }
/*     */     
/*  89 */     if ((tileentity != null) && ((tileentity instanceof IEssentiaTransport)) && (spillEssentia) && (!worldIn.field_72995_K))
/*     */     {
/*  91 */       int ess = ((IEssentiaTransport)tileentity).getEssentiaAmount(EnumFacing.UP);
/*  92 */       if (ess > 0) {
/*  93 */         AuraHelper.pollute(worldIn, pos, ess, true);
/*     */       }
/*     */     }
/*     */     
/*  97 */     super.func_180663_b(worldIn, pos, state);
/*  98 */     worldIn.func_175713_t(pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 104 */     updateState(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180648_a(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam)
/*     */   {
/* 110 */     super.func_180648_a(worldIn, pos, state, eventID, eventParam);
/* 111 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/* 112 */     return tileentity == null ? false : tileentity.func_145842_c(eventID, eventParam);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/* 120 */     IBlockState bs = func_176223_P();
/* 121 */     if ((this instanceof IBlockFacingHorizontal)) bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_70093_af() ? placer.func_174811_aO() : placer.func_174811_aO().func_176734_d());
/* 122 */     if ((this instanceof IBlockFacing)) { bs = bs.func_177226_a(IBlockFacing.FACING, placer.func_70093_af() ? BlockPistonBase.func_180695_a(worldIn, pos, placer).func_176734_d() : BlockPistonBase.func_180695_a(worldIn, pos, placer));
/*     */     }
/* 124 */     if ((this instanceof IBlockEnabled)) bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true));
/* 125 */     return bs;
/*     */   }
/*     */   
/*     */   protected void updateState(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 130 */     if ((this instanceof IBlockEnabled)) {
/* 131 */       boolean flag = !worldIn.func_175640_z(pos);
/*     */       
/* 133 */       if (flag != ((Boolean)state.func_177229_b(IBlockEnabled.ENABLED)).booleanValue())
/*     */       {
/* 135 */         worldIn.func_180501_a(pos, state.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(flag)), 3);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateFacing(World world, BlockPos pos, EnumFacing face) {
/* 141 */     if (((this instanceof IBlockFacing)) || ((this instanceof IBlockFacingHorizontal))) {
/* 142 */       if (face == BlockStateUtils.getFacing(world.func_180495_p(pos))) return;
/* 143 */       if (((this instanceof IBlockFacingHorizontal)) && (face.func_176736_b() >= 0))
/* 144 */         world.func_180501_a(pos, world.func_180495_p(pos).func_177226_a(IBlockFacingHorizontal.FACING, face), 3);
/* 145 */       if ((this instanceof IBlockFacing)) {
/* 146 */         world.func_180501_a(pos, world.func_180495_p(pos).func_177226_a(IBlockFacing.FACING, face), 3);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 153 */     IBlockState bs = func_176223_P();
/*     */     try {
/* 155 */       if ((this instanceof IBlockFacingHorizontal)) bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, BlockStateUtils.getFacing(meta));
/* 156 */       if ((this instanceof IBlockFacing)) bs = bs.func_177226_a(IBlockFacing.FACING, BlockStateUtils.getFacing(meta));
/* 157 */       if ((this instanceof IBlockEnabled)) bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(BlockStateUtils.isEnabled(meta)));
/*     */     } catch (Exception e) {}
/* 159 */     return bs;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 165 */     byte b0 = 0;
/* 166 */     int i = (this instanceof IBlockFacing) ? b0 | ((EnumFacing)state.func_177229_b(IBlockFacing.FACING)).func_176745_a() : (this instanceof IBlockFacingHorizontal) ? b0 | ((EnumFacing)state.func_177229_b(IBlockFacingHorizontal.FACING)).func_176745_a() : b0;
/*     */     
/*     */ 
/*     */ 
/* 170 */     if (((this instanceof IBlockEnabled)) && (!((Boolean)state.func_177229_b(IBlockEnabled.ENABLED)).booleanValue()))
/*     */     {
/* 172 */       i |= 0x8;
/*     */     }
/*     */     
/* 175 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 181 */     ArrayList<IProperty> ip = new ArrayList();
/* 182 */     if ((this instanceof IBlockFacingHorizontal)) ip.add(IBlockFacingHorizontal.FACING);
/* 183 */     if ((this instanceof IBlockFacing)) ip.add(IBlockFacing.FACING);
/* 184 */     if ((this instanceof IBlockEnabled)) ip.add(IBlockEnabled.ENABLED);
/* 185 */     return ip.size() == 0 ? super.func_180661_e() : new BlockState(this, (IProperty[])ip.toArray(new IProperty[ip.size()]));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/BlockTCDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */