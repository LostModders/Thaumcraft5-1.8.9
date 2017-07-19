/*     */ package thaumcraft.common.blocks.world.eldritch;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLiving.SpawnPlacementType;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.particles.FXSpark;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.tiles.devices.TileAuraTotem;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchAltar;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchCap;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchCrabSpawner;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchLock;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchObelisk;
/*     */ import thaumcraft.common.tiles.misc.TileEldritchPortal;
/*     */ 
/*     */ 
/*     */ public class BlockEldritch
/*     */   extends BlockTCDevice
/*     */ {
/*  39 */   public static final PropertyEnum TYPE = PropertyEnum.func_177709_a("type", EldritchType.class);
/*     */   
/*     */   public BlockEldritch()
/*     */   {
/*  43 */     super(Material.field_151576_e, TileEldritchAltar.class);
/*  44 */     func_149752_b(20000.0F);
/*  45 */     func_149711_c(50.0F);
/*  46 */     func_149672_a(Block.field_149769_e);
/*  47 */     func_149675_a(true);
/*  48 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  49 */     func_149713_g(0);
/*  50 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(TYPE, EldritchType.ALTAR));
/*  51 */     func_149647_a(null);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World worldIn, int meta)
/*     */   {
/*  56 */     switch (meta) {
/*  57 */     case 0:  return new TileEldritchAltar();
/*  58 */     case 1:  return new TileEldritchCap();
/*  59 */     case 2:  return new TileEldritchObelisk();
/*  60 */     case 4:  return new TileEldritchLock();
/*  61 */     case 6:  return new TileEldritchPortal();
/*  62 */     case 7:  return new TileEldritchCrabSpawner(); }
/*  63 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/*  70 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   protected boolean func_149700_E()
/*     */   {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(IBlockState state)
/*     */   {
/*  80 */     int meta = state.func_177230_c().func_176201_c(state);
/*  81 */     return (meta != 3) && (meta != 5);
/*     */   }
/*     */   
/*     */   public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type)
/*     */   {
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, BlockPos pos)
/*     */   {
/*  91 */     return 8;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  97 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 102 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 108 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World world, BlockPos pos, IBlockState state)
/*     */   {
/* 114 */     int meta = state.func_177230_c().func_176201_c(state);
/* 115 */     if ((!world.field_72995_K) && (meta > 1) && (meta < 4)) {
/* 116 */       for (int xx = pos.func_177958_n() - 3; xx <= pos.func_177958_n() + 3; xx++) {
/* 117 */         for (int yy = pos.func_177956_o() - 2; yy <= pos.func_177956_o() + 2; yy++)
/* 118 */           for (int zz = pos.func_177952_p() - 3; zz <= pos.func_177952_p() + 3; zz++)
/* 119 */             if (world.func_180495_p(new BlockPos(xx, yy, zz)).func_177230_c() == this) {
/* 120 */               IBlockState bs = world.func_180495_p(new BlockPos(xx, yy, zz));
/* 121 */               int m = bs.func_177230_c().func_176201_c(bs);
/* 122 */               if ((meta > 1) && (meta < 4)) world.func_175698_g(new BlockPos(xx, yy, zz));
/*     */             }
/*     */       }
/* 125 */       world.func_72876_a(null, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, 1.0F, false);
/*     */     }
/*     */     
/* 128 */     super.func_180663_b(world, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/* 136 */     return func_176203_a(meta);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 142 */     return func_176223_P().func_177226_a(TYPE, EldritchType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 148 */     return ((EldritchType)state.func_177229_b(TYPE)).ordinal();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 155 */     return new BlockState(this, new IProperty[] { TYPE });
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 161 */     EldritchType type = (EldritchType)state.func_177229_b(TYPE);
/* 162 */     return (fullName ? "eldritch_" : "") + type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 168 */     return new IProperty[] { TYPE };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 175 */     TileEntity tile = worldIn.func_175625_s(pos);
/* 176 */     if ((tile != null) && ((tile instanceof TileAuraTotem)))
/*     */     {
/* 178 */       ((TileAuraTotem)tile).checkPoles();
/* 179 */       worldIn.func_175641_c(pos, this, 1, 1);
/*     */     } else {
/* 181 */       worldIn.func_180496_d(pos.func_177984_a(), this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 188 */     int metadata = state.func_177230_c().func_176201_c(state);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 198 */     if ((metadata == 0) && (!world.field_72995_K) && (!player.func_70093_af()) && (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == ItemsTC.eldritchEye))
/*     */     {
/* 200 */       TileEntity te = world.func_175625_s(pos);
/* 201 */       if ((te != null) && ((te instanceof TileEldritchAltar))) {
/* 202 */         TileEldritchAltar tile = (TileEldritchAltar)te;
/* 203 */         if (tile.getEyes() < 4) {
/* 204 */           if (tile.getEyes() >= 2) {
/* 205 */             tile.setSpawner(true);
/* 206 */             tile.setSpawnType((byte)1);
/*     */           }
/* 208 */           tile.setEyes((byte)(tile.getEyes() + 1));
/* 209 */           tile.checkForMaze();
/* 210 */           player.func_70694_bm().field_77994_a -= 1;
/* 211 */           tile.func_70296_d();
/* 212 */           world.func_175689_h(pos);
/* 213 */           world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:crystal", 0.2F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 218 */     if ((metadata == 4) && (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == ItemsTC.runedTablet)) {
/* 219 */       TileEntity te = world.func_175625_s(pos);
/* 220 */       if ((te != null) && ((te instanceof TileEldritchLock)) && (((TileEldritchLock)te).count < 0)) {
/* 221 */         ((TileEldritchLock)te).count = 0;
/* 222 */         te.func_70296_d();
/* 223 */         world.func_175689_h(pos);
/* 224 */         player.func_70694_bm().field_77994_a -= 1;
/* 225 */         world.func_72908_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:runicShieldCharge", 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 230 */     return super.func_180639_a(world, pos, state, player, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */   public float func_176195_g(World worldIn, BlockPos pos)
/*     */   {
/* 235 */     IBlockState state = worldIn.func_180495_p(pos);
/* 236 */     if (state.func_177230_c() != this) return super.func_176195_g(worldIn, pos);
/* 237 */     return (EldritchType)state.func_177229_b(TYPE) == EldritchType.CRABSPAWNER ? 25.0F : ((EldritchType)state.func_177229_b(TYPE) == EldritchType.LOCK) || ((EldritchType)state.func_177229_b(TYPE) == EldritchType.DOOR) || ((EldritchType)state.func_177229_b(TYPE) == EldritchType.TELEPORTER) ? -1.0F : super.func_176195_g(worldIn, pos);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_180646_a(World worldIn, BlockPos pos)
/*     */   {
/* 248 */     IBlockState state = worldIn.func_180495_p(pos);
/* 249 */     return (EldritchType)state.func_177229_b(TYPE) == EldritchType.TELEPORTER ? AxisAlignedBB.func_178781_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D) : super.func_180646_a(worldIn, pos);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 256 */     return (EldritchType)state.func_177229_b(TYPE) == EldritchType.TELEPORTER ? null : super.func_180640_a(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/* 265 */     if ((EldritchType)state.func_177229_b(TYPE) == EldritchType.LOCK) {
/* 266 */       TileEntity te = worldIn.func_175625_s(pos);
/* 267 */       if ((te == null) || (!(te instanceof TileEldritchLock)) || (((TileEldritchLock)te).count < 0)) return;
/* 268 */       FXSpark ef = new FXSpark(worldIn, pos.func_177958_n() + rand.nextFloat(), pos.func_177956_o() + rand.nextFloat(), pos.func_177952_p() + rand.nextFloat(), 0.5F);
/*     */       
/*     */ 
/*     */ 
/* 272 */       ef.func_70538_b(0.65F + rand.nextFloat() * 0.1F, 1.0F, 1.0F);
/* 273 */       ef.func_82338_g(0.8F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum EldritchType
/*     */     implements IStringSerializable
/*     */   {
/* 282 */     ALTAR, 
/* 283 */     CAP, 
/* 284 */     OBELISK_BASE, 
/* 285 */     OBELISK_BODY, 
/* 286 */     LOCK, 
/* 287 */     DOOR, 
/* 288 */     TELEPORTER, 
/* 289 */     CRABSPAWNER;
/*     */     
/*     */     private EldritchType() {}
/*     */     
/*     */     public String func_176610_l() {
/* 294 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 300 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/eldritch/BlockEldritch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */