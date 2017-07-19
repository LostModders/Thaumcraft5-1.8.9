/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving.SpawnPlacementType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.internal.WeightedRandomLoot;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ public class BlockStoneTC
/*     */   extends BlockTC
/*     */ {
/*  37 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", StoneType.class);
/*     */   
/*     */   public BlockStoneTC()
/*     */   {
/*  41 */     super(Material.field_151576_e);
/*  42 */     func_149647_a(Thaumcraft.tabTC);
/*  43 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, StoneType.ARCANE));
/*  44 */     func_149711_c(2.0F);
/*  45 */     func_149752_b(10.0F);
/*  46 */     func_149672_a(Block.field_149769_e);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
/*     */   {
/*  53 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  61 */     return meta < StoneType.values().length ? func_176223_P().func_177226_a(VARIANT, StoneType.values()[meta]) : func_176223_P();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  67 */     int meta = ((StoneType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/*  69 */     return meta;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  75 */     return new BlockState(this, new IProperty[] { VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/*  81 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/*  87 */     StoneType type = (StoneType)state.func_177229_b(VARIANT);
/*     */     
/*  89 */     return type.func_176610_l() + (fullName ? "_stone" : "");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type)
/*     */   {
/*  97 */     IBlockState state = world.func_180495_p(pos);
/*  98 */     if (state.func_177230_c() != this) return super.canCreatureSpawn(world, pos, type);
/*  99 */     return ((StoneType)state.func_177229_b(VARIANT) == StoneType.ANCIENT_ROCK) || ((StoneType)state.func_177229_b(VARIANT) == StoneType.ANCIENT_DOORWAY) || ((StoneType)state.func_177229_b(VARIANT) == StoneType.GLYPHED) || ((StoneType)state.func_177229_b(VARIANT) == StoneType.BEDROCK) || ((StoneType)state.func_177229_b(VARIANT) == StoneType.ANCIENT_TILE) ? false : super.canCreatureSpawn(world, pos, type);
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
/*     */   public int getLightValue(IBlockAccess world, BlockPos pos)
/*     */   {
/* 112 */     IBlockState state = world.func_180495_p(pos);
/* 113 */     if (state.func_177230_c() != this) return super.getLightValue(world, pos);
/* 114 */     return (StoneType)state.func_177229_b(VARIANT) == StoneType.CRUST_GLOW ? 9 : super.getLightValue(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_176195_g(World worldIn, BlockPos pos)
/*     */   {
/* 120 */     IBlockState state = worldIn.func_180495_p(pos);
/* 121 */     if (state.func_177230_c() != this) return super.func_176195_g(worldIn, pos);
/* 122 */     return (StoneType)state.func_177229_b(VARIANT) == StoneType.BEDROCK ? -1.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.ANCIENT_DOORWAY ? -1.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.ELDRITCH ? 15.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.CRUST_GLOW ? 1.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.POROUS ? 0.5F : (StoneType)state.func_177229_b(VARIANT) == StoneType.CRUST ? 1.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.ANCIENT_ROCK ? 6.0F : super.func_176195_g(worldIn, pos);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion)
/*     */   {
/* 134 */     IBlockState state = world.func_180495_p(pos);
/* 135 */     if (state.func_177230_c() != this) return super.getExplosionResistance(world, pos, exploder, explosion);
/* 136 */     return (StoneType)state.func_177229_b(VARIANT) == StoneType.ELDRITCH ? 1000.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.CRUST_GLOW ? 5.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.POROUS ? 5.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.CRUST ? 5.0F : (StoneType)state.func_177229_b(VARIANT) == StoneType.ANCIENT_ROCK ? 20.0F : super.getExplosionResistance(world, pos, exploder, explosion);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 144 */   private static int cc = 0;
/*     */   
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 148 */     if ((state.func_177230_c() == this) && ((StoneType)state.func_177229_b(VARIANT) == StoneType.BEDROCK) && 
/* 149 */       (isBlockExposed(worldIn, pos)) && 
/* 150 */       (cc++ < 10)) {
/* 151 */       worldIn.func_180501_a(pos, BlocksTC.vacuum.func_176223_P(), 2);
/* 152 */       worldIn.func_175684_a(pos, BlocksTC.vacuum, 1);
/*     */     }
/*     */     
/*     */ 
/* 156 */     cc = 0;
/*     */   }
/*     */   
/*     */   private boolean isBlockExposed(World world, BlockPos pos) {
/* 160 */     for (EnumFacing face : ) {
/* 161 */       if (world.func_175623_d(pos.func_177972_a(face))) {
/* 162 */         return true;
/*     */       }
/*     */     }
/* 165 */     return false;
/*     */   }
/*     */   
/* 168 */   static Random r = new Random(System.currentTimeMillis());
/* 169 */   static ArrayList<WeightedRandomLoot> pdrops = null;
/*     */   
/*     */ 
/*     */   public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
/*     */   {
/* 174 */     if ((state.func_177230_c() == this) && ((StoneType)state.func_177229_b(VARIANT) == StoneType.POROUS)) {
/* 175 */       List<ItemStack> ret = new ArrayList();
/* 176 */       int rr = r.nextInt(15) + fortune;
/* 177 */       if (rr > 13) {
/* 178 */         if ((pdrops == null) || (pdrops.size() <= 0)) createDrops();
/* 179 */         ItemStack s = ((WeightedRandomLoot)WeightedRandom.func_76271_a(r, pdrops)).item.func_77946_l();
/* 180 */         ret.add(s);
/*     */       } else {
/* 182 */         ret.add(new ItemStack(Blocks.field_150347_e));
/*     */       }
/* 184 */       return ret;
/*     */     }
/* 186 */     return super.getDrops(world, pos, state, fortune);
/*     */   }
/*     */   
/*     */   private void createDrops() {
/* 190 */     pdrops = new ArrayList();
/* 191 */     for (Aspect aspect : Aspect.getCompoundAspects()) {
/* 192 */       ItemStack is = new ItemStack(ItemsTC.crystalEssence);
/* 193 */       ((ItemGenericEssentiaContainer)ItemsTC.crystalEssence).setAspects(is, new AspectList().add(aspect, 1));
/* 194 */       pdrops.add(new WeightedRandomLoot(is, 1));
/*     */     }
/* 196 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.shard, 1, 0), 20));
/* 197 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.shard, 1, 1), 20));
/* 198 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.shard, 1, 2), 20));
/* 199 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.shard, 1, 3), 20));
/* 200 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.shard, 1, 4), 20));
/* 201 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.shard, 1, 5), 20));
/* 202 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.shard, 1, 6), 100));
/* 203 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.shard, 1, 7), 5));
/* 204 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.amber), 20));
/* 205 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 0), 20));
/* 206 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 1), 10));
/* 207 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 6), 10));
/* 208 */     if (Config.foundCopperIngot) pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 2), 10));
/* 209 */     if (Config.foundTinIngot) pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 3), 10));
/* 210 */     if (Config.foundSilverIngot) pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 4), 8));
/* 211 */     if (Config.foundLeadIngot) pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 5), 10));
/* 212 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151045_i), 6));
/* 213 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151166_bC), 10));
/* 214 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151137_ax), 15));
/* 215 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_179563_cD), 3));
/* 216 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_179562_cC), 3));
/* 217 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151119_aD), 30));
/* 218 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151128_bU), 15));
/*     */   }
/*     */   
/*     */   public static enum StoneType
/*     */     implements IStringSerializable
/*     */   {
/* 224 */     ARCANE, 
/* 225 */     ARCANE_BRICK, 
/* 226 */     ANCIENT, 
/* 227 */     ANCIENT_ROCK, 
/* 228 */     ELDRITCH, 
/* 229 */     ANCIENT_TILE, 
/* 230 */     CRUST, 
/* 231 */     CRUST_GLOW, 
/* 232 */     MATRIX_SPEED, 
/* 233 */     MATRIX_COST, 
/* 234 */     ANCIENT_DOORWAY, 
/* 235 */     GLYPHED, 
/* 236 */     BEDROCK, 
/* 237 */     POROUS;
/*     */     
/*     */     private StoneType() {}
/*     */     
/*     */     public String func_176610_l()
/*     */     {
/* 243 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 249 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockStoneTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */