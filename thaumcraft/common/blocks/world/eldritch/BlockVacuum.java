/*     */ package thaumcraft.common.blocks.world.eldritch;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXGenericP2P;
/*     */ 
/*     */ public class BlockVacuum extends Block
/*     */ {
/*  31 */   public static final PropertyInteger SPREAD = PropertyInteger.func_177719_a("spread", 0, 12);
/*     */   
/*     */   public BlockVacuum()
/*     */   {
/*  35 */     super(new MaterialAiry(MapColor.field_151660_b));
/*  36 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(SPREAD, Integer.valueOf(0)));
/*  37 */     func_149722_s();
/*  38 */     this.field_149781_w = 999.0F;
/*  39 */     func_149675_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_149700_E()
/*     */   {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/*  51 */     return func_176223_P().func_177226_a(SPREAD, Integer.valueOf(meta));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/*  57 */     return ((Integer)state.func_177229_b(SPREAD)).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/*  63 */     return new BlockState(this, new IProperty[] { SPREAD });
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  69 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/*  75 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  81 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176214_u()
/*     */   {
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149637_q()
/*     */   {
/*  92 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176209_a(IBlockState state, boolean hitIfLiquid)
/*     */   {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {}
/*     */   
/*     */   public boolean func_176200_f(World worldIn, BlockPos pos)
/*     */   {
/* 106 */     IBlockState state = worldIn.func_180495_p(pos);
/* 107 */     int spread = state.func_177230_c().func_176201_c(state);
/* 108 */     return spread > 0;
/*     */   }
/*     */   
/*     */   private void check(World worldIn, BlockPos pos, IBlockState state) {
/* 112 */     if (!worldIn.field_72995_K)
/*     */     {
/* 114 */       int spread = state.func_177230_c().func_176201_c(state);
/* 115 */       boolean con = spread == 0;
/* 116 */       if (!con) {
/* 117 */         for (EnumFacing face : EnumFacing.field_82609_l)
/* 118 */           if (worldIn.func_175667_e(pos.func_177972_a(face))) {
/* 119 */             IBlockState bs = worldIn.func_180495_p(pos.func_177972_a(face));
/* 120 */             if ((bs.func_177230_c() == this) && (bs.func_177230_c().func_176201_c(bs) < spread)) {
/* 121 */               con = true;
/* 122 */               break;
/*     */             }
/*     */           }
/*     */       }
/* 126 */       if (con) {
/* 127 */         if (spread < 12) {
/* 128 */           for (EnumFacing face : EnumFacing.field_82609_l) {
/* 129 */             if (worldIn.func_175667_e(pos.func_177972_a(face))) {
/* 130 */               IBlockState bs = worldIn.func_180495_p(pos.func_177972_a(face));
/* 131 */               if (((bs.func_177230_c() != this) && (worldIn.func_175623_d(pos.func_177972_a(face)))) || ((bs.func_177230_c() == this) && (bs.func_177230_c().func_176201_c(bs) > spread + 1)))
/*     */               {
/* 133 */                 worldIn.func_175656_a(pos.func_177972_a(face), BlocksTC.vacuum.func_176203_a(spread + 1));
/* 134 */                 worldIn.func_175684_a(pos.func_177972_a(face), this, 5);
/* 135 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 140 */       } else if (spread < 12) {
/* 141 */         worldIn.func_175656_a(pos, BlocksTC.vacuum.func_176203_a(spread + 1));
/* 142 */         worldIn.func_175684_a(pos, this, 5);
/*     */       } else {
/* 144 */         worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/* 154 */     check(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public int getLightOpacity(IBlockAccess world, BlockPos pos)
/*     */   {
/* 165 */     IBlockState state = world.func_180495_p(pos);
/* 166 */     int spread = state.func_177230_c().func_176201_c(state);
/* 167 */     return spread * 21;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_176204_a(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/* 173 */     check(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
/*     */   {
/* 179 */     if ((((entityIn instanceof EntityPlayer)) && (((EntityPlayer)entityIn).field_71075_bZ.field_75098_d)) || (!entityIn.func_180431_b(DamageSource.field_76380_i))) {
/* 180 */       return;
/*     */     }
/* 182 */     int md = func_176201_c(state);
/* 183 */     if ((!worldIn.field_72995_K) && (
/* 184 */       (md == 0) || (!(entityIn instanceof EntityLiving)) || (!((EntityLiving)entityIn).func_70648_aU())))
/*     */     {
/*     */ 
/* 187 */       if ((md == 0) || (worldIn.field_73012_v.nextInt(2000) < 12 - func_176201_c(state))) {
/* 188 */         entityIn.func_70097_a(DamageSource.field_76380_i, md == 0 ? 10.0F : 1.0F);
/*     */       }
/*     */     }
/*     */     
/* 192 */     if ((md > 0) && (entityIn.func_70104_M())) {
/* 193 */       for (EnumFacing face : EnumFacing.field_82609_l) {
/* 194 */         IBlockState bs = worldIn.func_180495_p(pos.func_177972_a(face));
/* 195 */         if ((bs.func_177230_c() == this) && (bs.func_177230_c().func_176201_c(bs) < md)) {
/* 196 */           entityIn.field_70159_w += face.func_82601_c() / (25.0F * (md + 1));
/* 197 */           entityIn.field_70181_x += face.func_96559_d() / (((entityIn instanceof EntityItem) ? 1.0F : 10.0F) * (md + 1));
/* 198 */           entityIn.field_70179_y += face.func_82599_e() / (25.0F * (md + 1));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/* 207 */     if ((func_176201_c(state) > 3) && (rand.nextInt(500) < func_176201_c(state))) {
/* 208 */       BlockPos p = findSource(worldIn, pos, func_176201_c(state));
/* 209 */       if (p != pos) {
/* 210 */         FXGenericP2P bp = new FXGenericP2P(worldIn, pos.func_177958_n() + rand.nextFloat(), pos.func_177956_o() + rand.nextFloat(), pos.func_177952_p() + rand.nextFloat(), p.func_177958_n() + 0.5D, p.func_177956_o() + 0.5D, p.func_177952_p() + 0.5D);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 215 */         bp.func_82338_g(0.4F);
/* 216 */         bp.setParticles(48, 14, 1);
/* 217 */         bp.setLoop(true);
/* 218 */         bp.setScale(3.0F);
/* 219 */         bp.setLayer(0);
/* 220 */         ParticleEngine.instance.addEffect(worldIn, bp);
/* 221 */         if (rand.nextInt(33) == 0) {
/* 222 */           worldIn.func_72980_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:wind", 0.33F, 1.0F, true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   BlockPos findSource(World worldIn, BlockPos pos, int spread) {
/* 229 */     for (EnumFacing face : EnumFacing.field_82609_l) {
/* 230 */       IBlockState bs = worldIn.func_180495_p(pos.func_177972_a(face));
/* 231 */       if ((bs.func_177230_c() == this) && (bs.func_177230_c().func_176201_c(bs) < spread)) {
/* 232 */         return findSource(worldIn, pos.func_177972_a(face), bs.func_177230_c().func_176201_c(bs));
/*     */       }
/*     */     }
/* 235 */     return pos;
/*     */   }
/*     */   
/*     */   public static class MaterialAiry extends Material
/*     */   {
/*     */     public MaterialAiry(MapColor par1MapColor)
/*     */     {
/* 242 */       super();
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_76220_a()
/*     */     {
/* 248 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_76228_b()
/*     */     {
/* 254 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_76222_j()
/*     */     {
/* 260 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_76230_c()
/*     */     {
/* 266 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/eldritch/BlockVacuum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */