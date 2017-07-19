/*     */ package thaumcraft.common.blocks.world.ore;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.property.ExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IUnlistedProperty;
/*     */ import net.minecraftforge.common.property.Properties.PropertyAdapter;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.resources.ItemShard.ShardType;
/*     */ import thaumcraft.common.lib.CustomSoundType;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ public class BlockCrystal
/*     */   extends Block
/*     */   implements IWandable
/*     */ {
/*  45 */   public static final PropertyInteger SIZE = PropertyInteger.func_177719_a("size", 0, 3);
/*  46 */   public static final PropertyInteger GENERATION = PropertyInteger.func_177719_a("gen", 1, 4);
/*  47 */   public static final IUnlistedProperty<Boolean> NORTH = new Properties.PropertyAdapter(PropertyBool.func_177716_a("north"));
/*  48 */   public static final IUnlistedProperty<Boolean> EAST = new Properties.PropertyAdapter(PropertyBool.func_177716_a("east"));
/*  49 */   public static final IUnlistedProperty<Boolean> SOUTH = new Properties.PropertyAdapter(PropertyBool.func_177716_a("south"));
/*  50 */   public static final IUnlistedProperty<Boolean> WEST = new Properties.PropertyAdapter(PropertyBool.func_177716_a("west"));
/*  51 */   public static final IUnlistedProperty<Boolean> UP = new Properties.PropertyAdapter(PropertyBool.func_177716_a("up"));
/*  52 */   public static final IUnlistedProperty<Boolean> DOWN = new Properties.PropertyAdapter(PropertyBool.func_177716_a("down"));
/*  53 */   public static final IUnlistedProperty<Integer> SEED = new Properties.PropertyAdapter(PropertyInteger.func_177719_a("seed", 0, 7));
/*     */   protected Aspect aspect;
/*     */   
/*     */   public BlockCrystal(Aspect aspect)
/*     */   {
/*  58 */     super(Material.field_151592_s);
/*  59 */     this.aspect = aspect;
/*  60 */     func_149711_c(0.25F);
/*  61 */     func_149672_a(new CustomSoundType("crystal", 1.0F, 1.0F));
/*  62 */     func_149675_a(true);
/*  63 */     func_149647_a(Thaumcraft.tabTC);
/*  64 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(SIZE, Integer.valueOf(0)).func_177226_a(GENERATION, Integer.valueOf(1)));
/*     */   }
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
/*     */ 
/*     */   public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
/*     */   {
/*  81 */     List<ItemStack> ret = new ArrayList();
/*     */     
/*  83 */     int md = ItemShard.ShardType.getMetaByAspect(this.aspect);
/*     */     
/*  85 */     int count = getGrowth(state) + 1;
/*  86 */     for (int i = 0; i < count; i++)
/*     */     {
/*  88 */       ret.add(new ItemStack(ItemsTC.shard, 1, md));
/*     */     }
/*  90 */     return ret;
/*     */   }
/*     */   
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/*  95 */     if ((!worldIn.field_72995_K) && (rand.nextInt(3 + getGeneration(state)) == 0))
/*     */     {
/*  97 */       int growth = getGrowth(state);
/*  98 */       int generation = getGeneration(state);
/*     */       
/* 100 */       if (AuraHandler.getAuraCurrent(worldIn, pos, this.aspect) < AuraHandler.getAuraBase(worldIn, pos) / 8.0F) {
/* 101 */         if (growth > 0) {
/* 102 */           worldIn.func_175656_a(pos, state.func_177226_a(SIZE, Integer.valueOf(growth - 1)));
/* 103 */           AuraHandler.addRechargeTicket(worldIn, pos, this.aspect, Config.AURABASE / 9);
/*     */         }
/* 105 */         else if (BlockUtils.isBlockTouching(worldIn, pos, this)) {
/* 106 */           worldIn.func_175698_g(pos);
/* 107 */           AuraHandler.addRechargeTicket(worldIn, pos, this.aspect, Config.AURABASE / 9);
/* 108 */           AuraHandler.addRechargeTicket(worldIn, pos, Aspect.FLUX, 1);
/*     */         }
/*     */         
/*     */       }
/* 112 */       else if (AuraHandler.getAuraCurrent(worldIn, pos, this.aspect) >= AuraHandler.getAuraBase(worldIn, pos) + Config.AURABASE / 16) {
/* 113 */         if ((growth < 3) && (growth < 5 - generation + pos.func_177986_g() % 3L)) {
/* 114 */           if (AuraHandler.drainAura(worldIn, pos, this.aspect, Config.AURABASE / 8))
/*     */           {
/* 116 */             worldIn.func_175656_a(pos, state.func_177226_a(SIZE, Integer.valueOf(growth + 1)));
/*     */           }
/*     */           
/*     */         }
/* 120 */         else if (generation < 4) {
/* 121 */           BlockPos p2 = spreadCrystal(worldIn, pos);
/* 122 */           if ((p2 != null) && (AuraHandler.drainAura(worldIn, pos, this.aspect, Config.AURABASE / 8))) {
/* 123 */             if (rand.nextInt(8) == 0) generation--;
/* 124 */             worldIn.func_175656_a(p2, func_176223_P().func_177226_a(GENERATION, Integer.valueOf(generation + 1)));
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 131 */       else if ((this.aspect != Aspect.FLUX) && (AuraHandler.getAuraCurrent(worldIn, pos, Aspect.FLUX) > AuraHandler.getAuraCurrent(worldIn, pos, this.aspect)) && (AuraHandler.getAuraCurrent(worldIn, pos, Aspect.FLUX) > AuraHandler.getAuraBase(worldIn, pos) / 2))
/*     */       {
/*     */ 
/* 134 */         if (AuraHandler.drainAura(worldIn, pos, Aspect.FLUX, growth + 1)) {
/* 135 */           worldIn.func_175656_a(pos, BlocksTC.crystalTaint.func_176203_a(func_176201_c(state)));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static BlockPos spreadCrystal(World world, BlockPos pos) {
/* 142 */     int xx = pos.func_177958_n() + world.field_73012_v.nextInt(3) - 1;
/* 143 */     int yy = pos.func_177956_o() + world.field_73012_v.nextInt(3) - 1;
/* 144 */     int zz = pos.func_177952_p() + world.field_73012_v.nextInt(3) - 1;
/*     */     
/* 146 */     BlockPos t = new BlockPos(xx, yy, zz);
/*     */     
/* 148 */     if (t.equals(pos)) { return null;
/*     */     }
/* 150 */     IBlockState bs = world.func_180495_p(t);
/* 151 */     Material bm = bs.func_177230_c().func_149688_o();
/*     */     
/* 153 */     if ((!bm.func_76224_d()) && ((world.func_175623_d(t)) || (bs.func_177230_c().func_176200_f(world, t))) && (world.field_73012_v.nextInt(16) == 0) && (BlockUtils.isBlockTouching(world, t, Material.field_151576_e, true)))
/*     */     {
/* 155 */       return t;
/*     */     }
/* 157 */     return null;
/*     */   }
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 162 */     if (!BlockUtils.isBlockTouching(worldIn, pos, Material.field_151576_e, true)) {
/* 163 */       func_176226_b(worldIn, pos, state, 0);
/* 164 */       worldIn.func_175698_g(pos);
/*     */     }
/* 166 */     super.func_176204_a(worldIn, pos, state, neighborBlock);
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing o) {
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   private boolean drawAt(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 174 */     Block b = worldIn.func_180495_p(pos).func_177230_c();
/* 175 */     return (b.func_149688_o() == Material.field_151576_e) && (b.isSideSolid(worldIn, pos, side.func_176734_d()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180654_a(IBlockAccess iblockaccess, BlockPos pos)
/*     */   {
/* 181 */     IBlockState state = getExtendedState(iblockaccess.func_180495_p(pos), iblockaccess, pos);
/* 182 */     if ((state instanceof IExtendedBlockState)) {
/* 183 */       IExtendedBlockState es = (IExtendedBlockState)state;
/* 184 */       int c = 0;
/* 185 */       if (((Boolean)es.getValue(UP)).booleanValue()) c++;
/* 186 */       if (((Boolean)es.getValue(DOWN)).booleanValue()) c++;
/* 187 */       if (((Boolean)es.getValue(EAST)).booleanValue()) c++;
/* 188 */       if (((Boolean)es.getValue(WEST)).booleanValue()) c++;
/* 189 */       if (((Boolean)es.getValue(SOUTH)).booleanValue()) c++;
/* 190 */       if (((Boolean)es.getValue(NORTH)).booleanValue()) c++;
/* 191 */       if (c > 1) {
/* 192 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       } else {
/* 194 */         if (((Boolean)es.getValue(UP)).booleanValue()) {
/* 195 */           func_149676_a(0.125F, 0.5F, 0.125F, 0.875F, 1.0F, 0.875F);
/*     */         }
/* 197 */         if (((Boolean)es.getValue(DOWN)).booleanValue()) {
/* 198 */           func_149676_a(0.125F, 0.0F, 0.125F, 0.875F, 0.5F, 0.875F);
/*     */         }
/* 200 */         if (((Boolean)es.getValue(EAST)).booleanValue()) {
/* 201 */           func_149676_a(0.5F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
/*     */         }
/* 203 */         if (((Boolean)es.getValue(WEST)).booleanValue()) {
/* 204 */           func_149676_a(0.0F, 0.125F, 0.125F, 0.5F, 0.875F, 0.875F);
/*     */         }
/* 206 */         if (((Boolean)es.getValue(SOUTH)).booleanValue()) {
/* 207 */           func_149676_a(0.125F, 0.125F, 0.5F, 0.875F, 0.875F, 1.0F);
/*     */         }
/* 209 */         if (((Boolean)es.getValue(NORTH)).booleanValue()) {
/* 210 */           func_149676_a(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.5F);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 225 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 231 */     return false;
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, BlockPos pos)
/*     */   {
/* 236 */     IBlockState st = world.func_180495_p(pos);
/* 237 */     return 2 + (st.func_177230_c().func_176201_c(st) & 0x3) * 3;
/*     */   }
/*     */   
/*     */   public int func_176207_c(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 242 */     IBlockState bs = worldIn.func_180495_p(pos);
/* 243 */     int i = worldIn.func_175626_b(pos, bs.func_177230_c().getLightValue(worldIn, pos));
/* 244 */     int j = 180;
/* 245 */     int k = i & 0xFF;
/* 246 */     int l = j & 0xFF;
/* 247 */     int i1 = i >> 16 & 0xFF;
/* 248 */     int j1 = j >> 16 & 0xFF;
/* 249 */     return (k > l ? k : l) | (i1 > j1 ? i1 : j1) << 16;
/*     */   }
/*     */   
/*     */   public int func_180644_h(IBlockState state)
/*     */   {
/* 254 */     return this.aspect.getColor();
/*     */   }
/*     */   
/*     */   public int func_180662_a(IBlockAccess worldIn, BlockPos pos, int renderPass)
/*     */   {
/* 259 */     return this.aspect.getColor();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 265 */     IProperty[] listedProperties = { SIZE, GENERATION };
/* 266 */     IUnlistedProperty[] unlistedProperties = { UP, DOWN, NORTH, EAST, WEST, SOUTH, SEED };
/* 267 */     return new ExtendedBlockState(this, listedProperties, unlistedProperties);
/*     */   }
/*     */   
/*     */   public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
/*     */   {
/* 272 */     if ((state instanceof IExtendedBlockState)) {
/* 273 */       IExtendedBlockState retval = (IExtendedBlockState)state;
/* 274 */       return retval.withProperty(UP, Boolean.valueOf(drawAt(world, pos.func_177984_a(), EnumFacing.UP))).withProperty(DOWN, Boolean.valueOf(drawAt(world, pos.func_177977_b(), EnumFacing.DOWN))).withProperty(NORTH, Boolean.valueOf(drawAt(world, pos.func_177978_c(), EnumFacing.NORTH))).withProperty(EAST, Boolean.valueOf(drawAt(world, pos.func_177974_f(), EnumFacing.EAST))).withProperty(SOUTH, Boolean.valueOf(drawAt(world, pos.func_177968_d(), EnumFacing.SOUTH))).withProperty(WEST, Boolean.valueOf(drawAt(world, pos.func_177976_e(), EnumFacing.WEST))).withProperty(SEED, Integer.valueOf(Math.abs(pos.hashCode() % 8)));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 283 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 289 */     return state;
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
/*     */ 
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 305 */     return func_176223_P().func_177226_a(SIZE, Integer.valueOf(meta & 0x3)).func_177226_a(GENERATION, Integer.valueOf(1 + (meta >> 2)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 313 */     int i = 0;
/* 314 */     i |= ((Integer)state.func_177229_b(SIZE)).intValue();
/* 315 */     i |= ((Integer)state.func_177229_b(GENERATION)).intValue() - 1 << 2;
/* 316 */     return i;
/*     */   }
/*     */   
/*     */   public int getGrowth(IBlockState state) {
/* 320 */     return func_176201_c(state) & 0x3;
/*     */   }
/*     */   
/*     */   public int getGeneration(IBlockState state) {
/* 324 */     return 1 + (func_176201_c(state) >> 2);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item itemIn, CreativeTabs tab, List list)
/*     */   {
/* 331 */     list.add(new ItemStack(itemIn, 1, 0));
/*     */   }
/*     */   
/*     */   public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
/*     */   {
/* 336 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_176196_c(World worldIn, BlockPos pos)
/*     */   {
/* 341 */     return (BlockUtils.isBlockTouching(worldIn, pos, Material.field_151576_e, true)) && (super.func_176196_c(worldIn, pos));
/*     */   }
/*     */   
/*     */   public boolean onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos pos, EnumFacing side)
/*     */   {
/* 346 */     if ((!world.field_72995_K) && (ResearchHelper.isResearchComplete(player.func_70005_c_(), "CRYSTALFARMER"))) {
/* 347 */       IBlockState state = world.func_180495_p(pos);
/* 348 */       world.func_175718_b(2001, pos, Block.func_176210_f(state));
/* 349 */       int growth = getGrowth(state);
/* 350 */       if (growth > 0) {
/* 351 */         world.func_175656_a(pos, state.func_177226_a(SIZE, Integer.valueOf(growth - 1)));
/*     */       } else {
/* 353 */         world.func_175698_g(pos);
/*     */       }
/* 355 */       int md = ItemShard.ShardType.getMetaByAspect(this.aspect);
/* 356 */       player.func_146097_a(new ItemStack(ItemsTC.shard, 1, md), false, true);
/* 357 */       return true;
/*     */     }
/* 359 */     return false;
/*     */   }
/*     */   
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/ore/BlockCrystal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */