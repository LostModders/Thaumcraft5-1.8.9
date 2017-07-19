/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotem;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotemPole;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotemPull;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotemPush;
/*     */ 
/*     */ 
/*     */ public class BlockAuraTotem
/*     */   extends BlockTCDevice
/*     */ {
/*  36 */   public static final PropertyEnum TYPE = PropertyEnum.func_177709_a("type", TotemType.class);
/*     */   
/*     */   public BlockAuraTotem()
/*     */   {
/*  40 */     super(Material.field_151575_d, TileAuraTotemPush.class);
/*  41 */     func_149711_c(0.5F);
/*  42 */     func_149752_b(5.0F);
/*  43 */     func_149672_a(Block.field_149766_f);
/*  44 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(TYPE, TotemType.PUSH));
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World worldIn, int meta)
/*     */   {
/*  49 */     switch (meta) {
/*  50 */     case 0:  return new TileAuraTotemPush();
/*  51 */     case 1:  return new TileAuraTotemPull(); }
/*  52 */     return new TileAuraTotemPole();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  64 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  70 */     if (((TotemType)state.func_177229_b(TYPE)).isPole) {
/*  71 */       worldIn.func_175713_t(pos);
/*     */     } else {
/*  73 */       super.func_180663_b(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess world, BlockPos pos)
/*     */   {
/*  79 */     IBlockState state = world.func_180495_p(pos);
/*  80 */     if (((TotemType)state.func_177229_b(TYPE)).isPole) {
/*  81 */       func_149676_a(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
/*     */     } else {
/*  83 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/*  89 */     func_180654_a(worldIn, pos);
/*  90 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  96 */     return func_176203_a(meta);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 102 */     return func_176223_P().func_177226_a(TYPE, TotemType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 108 */     return ((TotemType)state.func_177229_b(TYPE)).ordinal();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 115 */     return new BlockState(this, new IProperty[] { TYPE });
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 121 */     TotemType type = (TotemType)state.func_177229_b(TYPE);
/* 122 */     return (fullName ? "aura_totem_" : "") + type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 128 */     return new IProperty[] { TYPE };
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/* 135 */     return EnumWorldBlockLayer.CUTOUT_MIPPED;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_180662_a(IBlockAccess worldIn, BlockPos pos, int renderPass)
/*     */   {
/* 142 */     TileEntity te = worldIn.func_175625_s(pos);
/* 143 */     if ((te != null) && ((te instanceof TileAuraTotem))) {
/* 144 */       if (((TileAuraTotem)te).type >= 0) {
/* 145 */         Aspect a = ((TileAuraTotem)te).getAspect();
/* 146 */         return a == null ? 15790320 : a.getColor();
/*     */       }
/* 148 */       return 1052688;
/*     */     }
/*     */     
/*     */ 
/* 152 */     return super.func_180662_a(worldIn, pos, renderPass);
/*     */   }
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 157 */     TileEntity tile = worldIn.func_175625_s(pos);
/* 158 */     if ((tile != null) && ((tile instanceof TileAuraTotem)))
/*     */     {
/* 160 */       ((TileAuraTotem)tile).checkPoles();
/* 161 */       worldIn.func_175641_c(pos, this, 1, 1);
/*     */     } else {
/* 163 */       worldIn.func_180496_d(pos.func_177984_a(), this);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 169 */     if ((player.func_70694_bm() != null) && (Block.func_149634_a(player.func_70694_bm().func_77973_b()) == this)) return false;
/* 170 */     if (!((TotemType)state.func_177229_b(TYPE)).isPole) {
/* 171 */       player.openGui(Thaumcraft.instance, 8, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */     } else {
/* 173 */       IBlockState bs = world.func_180495_p(pos.func_177984_a());
/* 174 */       if (bs.func_177230_c() == this)
/* 175 */         return bs.func_177230_c().func_180639_a(world, pos.func_177984_a(), bs, player, side, hitX, hitY, hitZ);
/*     */     }
/* 177 */     return true;
/*     */   }
/*     */   
/*     */   public static enum TotemType
/*     */     implements IStringSerializable
/*     */   {
/* 183 */     PUSH(Boolean.valueOf(false)), 
/* 184 */     PULL(Boolean.valueOf(false)), 
/* 185 */     POLE_OUTER(Boolean.valueOf(true)), 
/* 186 */     POLE_INNER(Boolean.valueOf(true)), 
/* 187 */     POLE_PURE(Boolean.valueOf(true));
/*     */     
/*     */     private final boolean isPole;
/*     */     
/*     */     private TotemType(Boolean b) {
/* 192 */       this.isPole = b.booleanValue();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public String func_176610_l()
/*     */     {
/* 199 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 205 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockAuraTotem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */