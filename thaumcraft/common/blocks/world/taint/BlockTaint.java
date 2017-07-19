/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import net.minecraftforge.fluids.BlockFluidFinite;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.internal.WeightedRandomLoot;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.EntityFallingTaint;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSwarm;
/*     */ import thaumcraft.common.lib.CustomSoundType;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class BlockTaint
/*     */   extends BlockTC
/*     */   implements ITaintBlock
/*     */ {
/*  51 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", TaintType.class);
/*     */   
/*     */   public BlockTaint()
/*     */   {
/*  55 */     super(ThaumcraftMaterials.MATERIAL_TAINT);
/*  56 */     func_149711_c(10.0F);
/*  57 */     func_149752_b(100.0F);
/*  58 */     func_149672_a(new CustomSoundType("gore", 0.5F, 0.8F));
/*  59 */     func_149675_a(true);
/*  60 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public MapColor func_180659_g(IBlockState state)
/*     */   {
/*  67 */     return MapColor.field_151678_z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/*  76 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */   
/*     */   public void die(World world, BlockPos pos, IBlockState blockState)
/*     */   {
/*  81 */     if ((TaintType)blockState.func_177229_b(VARIANT) == TaintType.ROCK) {
/*  82 */       world.func_175656_a(pos, BlocksTC.stone.func_176203_a(13));
/*     */     }
/*  84 */     else if (((TaintType)blockState.func_177229_b(VARIANT) == TaintType.CRUST) || ((TaintType)blockState.func_177229_b(VARIANT) == TaintType.SOIL))
/*     */     {
/*  86 */       if ((Config.soilToDirt) && ((TaintType)blockState.func_177229_b(VARIANT) == TaintType.SOIL)) {
/*  87 */         world.func_175656_a(pos, Blocks.field_150346_d.func_176223_P());
/*     */       } else {
/*  89 */         world.func_175656_a(pos, BlocksTC.taintDust.func_176223_P().func_177226_a(BlockFluidBase.LEVEL, Integer.valueOf(3)));
/*     */       }
/*     */     }
/*  92 */     else if ((TaintType)blockState.func_177229_b(VARIANT) == TaintType.GEYSER) {
/*  93 */       world.func_175656_a(pos, BlocksTC.fluxGoo.func_176223_P());
/*     */     } else {
/*  95 */       world.func_175698_g(pos);
/*     */     }
/*  97 */     Utils.resetBiomeAt(world, pos, world.field_73012_v.nextInt(25) == 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random)
/*     */   {
/* 105 */     if (!world.field_72995_K)
/*     */     {
/*     */ 
/* 108 */       if ((AuraHelper.getAura(world, pos, Aspect.FLUX) < Config.AURABASE / 25) && (random.nextInt(10) == 0)) {
/* 109 */         die(world, pos, state);
/* 110 */         return;
/*     */       }
/*     */       
/* 113 */       if ((TaintType)state.func_177229_b(VARIANT) != TaintType.ROCK) { BlockTaintFibre.spreadFibres(world, pos);
/*     */       }
/*     */       
/* 116 */       if ((TaintType)state.func_177229_b(VARIANT) == TaintType.CRUST) {
/* 117 */         Random r = new Random(pos.func_177986_g());
/* 118 */         if (tryToFall(world, pos, pos)) {
/* 119 */           return;
/*     */         }
/* 121 */         if (world.func_175623_d(pos.func_177984_a())) {
/* 122 */           boolean doIt = true;
/* 123 */           EnumFacing dir = EnumFacing.field_176754_o[random.nextInt(4)];
/* 124 */           for (int a = 1; a < 4; a++) {
/* 125 */             if (!world.func_175623_d(pos.func_177972_a(dir).func_177979_c(a))) {
/* 126 */               doIt = false;
/* 127 */               break;
/*     */             }
/* 129 */             if (world.func_180495_p(pos.func_177979_c(a)).func_177230_c() != this) {
/* 130 */               doIt = false;
/* 131 */               break;
/*     */             }
/*     */           }
/* 134 */           if ((doIt) && 
/* 135 */             (tryToFall(world, pos, pos.func_177972_a(dir)))) { return;
/*     */           }
/*     */           
/*     */         }
/*     */       }
/* 140 */       else if ((TaintType)state.func_177229_b(VARIANT) == TaintType.GEYSER) {
/* 141 */         if ((world.field_73012_v.nextFloat() < 0.2D) && (world.func_72977_a(pos.func_177958_n() + 0.5F, pos.func_177956_o() + 0.5F, pos.func_177952_p() + 0.5F, 24.0D) != null)) {
/* 142 */           Entity e = new EntityTaintSwarm(world);
/* 143 */           e.func_70012_b(pos.func_177958_n() + 0.5F, pos.func_177956_o() + 1.25F, pos.func_177952_p() + 0.5F, world.field_73012_v.nextInt(360), 0.0F);
/* 144 */           world.func_72838_d(e);
/*     */         }
/* 146 */         else if (AuraHelper.getAura(world, pos, Aspect.FLUX) < Config.AURABASE / 4) {
/* 147 */           AuraHelper.pollute(world, pos, 1, true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/* 157 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/* 162 */     return func_176201_c(state);
/*     */   }
/*     */   
/*     */   public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
/*     */   {
/* 167 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_176199_a(World world, BlockPos pos, Entity entity)
/*     */   {
/* 174 */     if ((!world.field_72995_K) && ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase)entity).func_70662_br()) && 
/* 175 */       (world.field_73012_v.nextInt(750) == 0)) {
/* 176 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), 200, 0, false, true));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180648_a(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam)
/*     */   {
/* 183 */     if (eventID == 1)
/*     */     {
/* 185 */       if (worldIn.field_72995_K) {
/* 186 */         worldIn.func_72980_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:roots", 0.1F, 0.9F + worldIn.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/* 188 */       return true;
/*     */     }
/* 190 */     return super.func_180648_a(worldIn, pos, state, eventID, eventParam);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 196 */     return func_176223_P().func_177226_a(VARIANT, TaintType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 202 */     int meta = ((TaintType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/* 204 */     return meta;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 210 */     return new BlockState(this, new IProperty[] { VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 216 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 222 */     TaintType type = (TaintType)state.func_177229_b(VARIANT);
/*     */     
/* 224 */     return (fullName ? "taint_" : "") + type.func_176610_l();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean canFallBelow(World world, BlockPos pos)
/*     */   {
/* 234 */     IBlockState bs = world.func_180495_p(pos);
/* 235 */     Block l = bs.func_177230_c();
/*     */     
/* 237 */     for (int xx = -1; xx <= 1; xx++) for (int zz = -1; zz <= 1; zz++) for (int yy = -1; yy <= 1; yy++) {
/* 238 */           if (Utils.isWoodLog(world, pos.func_177982_a(xx, yy, zz))) {
/* 239 */             return false;
/*     */           }
/*     */         }
/* 242 */     if (l.isAir(world, pos))
/*     */     {
/* 244 */       return true;
/*     */     }
/* 246 */     if ((l == BlocksTC.fluxGoo) && (((Integer)bs.func_177229_b(BlockFluidFinite.LEVEL)).intValue() >= 4))
/*     */     {
/* 248 */       return false;
/*     */     }
/* 250 */     if ((l == Blocks.field_150480_ab) || (l == BlocksTC.taintFibre))
/*     */     {
/* 252 */       return true;
/*     */     }
/* 254 */     if (l.func_176200_f(world, pos))
/*     */     {
/* 256 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 260 */     return l.func_149688_o() == Material.field_151586_h;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean tryToFall(World world, BlockPos pos, BlockPos pos2)
/*     */   {
/* 266 */     if (!BlockTaintFibre.isOnlyAdjacentToTaint(world, pos)) { return false;
/*     */     }
/* 268 */     if ((canFallBelow(world, pos2.func_177977_b())) && (pos2.func_177956_o() >= 0))
/*     */     {
/* 270 */       byte b0 = 32;
/*     */       
/* 272 */       if (world.func_175707_a(pos2.func_177982_a(-b0, -b0, -b0), pos2.func_177982_a(b0, b0, b0)))
/*     */       {
/* 274 */         if (!world.field_72995_K)
/*     */         {
/* 276 */           EntityFallingTaint entityfalling = new EntityFallingTaint(world, pos2.func_177958_n() + 0.5F, pos2.func_177956_o() + 0.5F, pos2.func_177952_p() + 0.5F, world.func_180495_p(pos), pos);
/*     */           
/* 278 */           world.func_72838_d(entityfalling);
/* 279 */           return true;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 284 */         world.func_175698_g(pos);
/* 285 */         BlockPos p2 = new BlockPos(pos2);
/* 286 */         while ((canFallBelow(world, p2.func_177977_b())) && (p2.func_177956_o() > 0))
/*     */         {
/* 288 */           p2 = p2.func_177977_b();
/*     */         }
/*     */         
/* 291 */         if (p2.func_177956_o() > 0)
/*     */         {
/* 293 */           world.func_175656_a(p2, BlocksTC.taintBlock.func_176223_P());
/*     */         }
/*     */       }
/*     */     }
/* 297 */     return false;
/*     */   }
/*     */   
/* 300 */   static Random r = new Random(System.currentTimeMillis());
/* 301 */   static ArrayList<WeightedRandomLoot> pdrops = null;
/*     */   
/*     */ 
/*     */   public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
/*     */   {
/* 306 */     if ((state.func_177230_c() == this) && ((TaintType)state.func_177229_b(VARIANT) == TaintType.ROCK)) {
/* 307 */       int rr = r.nextInt(15) + fortune;
/* 308 */       if (rr > 13) {
/* 309 */         List<ItemStack> ret = new ArrayList();
/* 310 */         ItemStack is = new ItemStack(ItemsTC.crystalEssence);
/* 311 */         ((ItemGenericEssentiaContainer)ItemsTC.crystalEssence).setAspects(is, new AspectList().add(Aspect.FLUX, 1));
/* 312 */         ret.add(is);
/* 313 */         return ret;
/*     */       }
/*     */     }
/* 316 */     return super.getDrops(world, pos, state, fortune);
/*     */   }
/*     */   
/*     */   protected boolean func_149700_E()
/*     */   {
/* 321 */     return false;
/*     */   }
/*     */   
/*     */   public static enum TaintType implements IStringSerializable
/*     */   {
/* 326 */     CRUST, 
/* 327 */     SOIL, 
/* 328 */     GEYSER, 
/* 329 */     ROCK;
/*     */     
/*     */     private TaintType() {}
/*     */     
/*     */     public String func_176610_l()
/*     */     {
/* 335 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 341 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/taint/BlockTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */