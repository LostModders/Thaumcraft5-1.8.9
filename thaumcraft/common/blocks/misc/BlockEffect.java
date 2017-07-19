/*     */ package thaumcraft.common.blocks.misc;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXSpark;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEffect
/*     */   extends BlockTC
/*     */ {
/*  35 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", EffType.class);
/*     */   
/*     */   public BlockEffect()
/*     */   {
/*  39 */     super(Material.field_151579_a);
/*  40 */     func_149675_a(true);
/*  41 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, EffType.SHOCK));
/*  42 */     this.field_149781_w = 999.0F;
/*  43 */     func_149715_a(0.5F);
/*  44 */     func_149647_a(null);
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, BlockPos pos)
/*     */   {
/*  49 */     IBlockState state = world.func_180495_p(pos);
/*  50 */     if (state.func_177230_c() != this) return super.getLightValue(world, pos);
/*  51 */     if ((EffType)world.func_180495_p(pos).func_177229_b(VARIANT) == EffType.GLIMMER) {
/*  52 */       return 15;
/*     */     }
/*  54 */     return super.getLightValue(world, pos);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
/*     */   {
/*  60 */     if ((EffType)world.func_180495_p(pos).func_177229_b(VARIANT) == EffType.SHOCK) {
/*  61 */       entity.func_70097_a(DamageSource.field_76376_m, 1 + world.field_73012_v.nextInt(2));
/*  62 */       if ((entity instanceof EntityLivingBase)) {
/*  63 */         PotionEffect pe = new PotionEffect(Potion.field_76421_d.field_76415_H, 20, 0, true, true);
/*  64 */         ((EntityLivingBase)entity).func_70690_d(pe);
/*     */       }
/*  66 */       if ((!world.field_72995_K) && (world.field_73012_v.nextInt(100) == 0)) {
/*  67 */         world.func_175698_g(pos);
/*     */       }
/*     */     }
/*  70 */     else if (((EffType)world.func_180495_p(pos).func_177229_b(VARIANT) == EffType.SAPPING) && (!(entity instanceof IEldritchMob)) && 
/*  71 */       ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase)entity).func_70644_a(Potion.field_82731_v))) {
/*  72 */       PotionEffect pe0 = new PotionEffect(Potion.field_82731_v.field_76415_H, 40, 0, true, true);
/*  73 */       ((EntityLivingBase)entity).func_70690_d(pe0);
/*  74 */       PotionEffect pe1 = new PotionEffect(Potion.field_76421_d.field_76415_H, 40, 1, true, true);
/*  75 */       ((EntityLivingBase)entity).func_70690_d(pe1);
/*  76 */       PotionEffect pe2 = new PotionEffect(Potion.field_76438_s.field_76415_H, 40, 1, true, true);
/*  77 */       ((EntityLivingBase)entity).func_70690_d(pe2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/*  84 */     super.func_180650_b(worldIn, pos, state, rand);
/*  85 */     if ((!worldIn.field_72995_K) && (((EffType)state.func_177229_b(VARIANT) == EffType.SAPPING) || ((EffType)state.func_177229_b(VARIANT) == EffType.SHOCK))) {
/*  86 */       worldIn.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(World w, BlockPos pos, IBlockState state, Random r)
/*     */   {
/*  94 */     if (((EffType)state.func_177229_b(VARIANT) == EffType.SAPPING) || ((EffType)state.func_177229_b(VARIANT) == EffType.SHOCK)) {
/*  95 */       float h = r.nextFloat() * 0.33F;
/*  96 */       FXSpark ef = new FXSpark(w, pos.func_177958_n() + w.field_73012_v.nextFloat(), pos.func_177956_o() + 0.1515F + h / 2.0F, pos.func_177952_p() + w.field_73012_v.nextFloat(), 0.33F + h);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 101 */       if ((EffType)state.func_177229_b(VARIANT) == EffType.SHOCK) {
/* 102 */         ef.func_70538_b(0.65F + w.field_73012_v.nextFloat() * 0.1F, 1.0F, 1.0F);
/* 103 */         ef.func_82338_g(0.8F);
/*     */       } else {
/* 105 */         ef.func_70538_b(0.3F - w.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.5F + w.field_73012_v.nextFloat() * 0.2F);
/*     */       }
/*     */       
/* 108 */       ParticleEngine.instance.addEffect(w, ef);
/*     */       
/* 110 */       if (r.nextInt(50) == 0) {
/* 111 */         w.func_72980_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "thaumcraft:jacobs", 0.5F, 1.0F + (r.nextFloat() - r.nextFloat()) * 0.2F, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAir(IBlockAccess world, BlockPos pos)
/*     */   {
/* 121 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 126 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_176193_a(World worldIn, BlockPos pos, EnumFacing side, ItemStack stack)
/*     */   {
/* 131 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
/* 135 */     return null;
/*     */   }
/*     */   
/* 138 */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing o) { return false; }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 144 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 151 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176209_a(IBlockState state, boolean hitIfLiquid)
/*     */   {
/* 157 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_180646_a(World worldIn, BlockPos pos)
/*     */   {
/* 162 */     return AxisAlignedBB.func_178781_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/* 166 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/* 172 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 178 */     return func_176223_P().func_177226_a(VARIANT, EffType.values()[meta]);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 184 */     int meta = ((EffType)state.func_177229_b(VARIANT)).ordinal();
/*     */     
/* 186 */     return meta;
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 192 */     return new BlockState(this, new IProperty[] { VARIANT });
/*     */   }
/*     */   
/*     */ 
/*     */   public IProperty[] getProperties()
/*     */   {
/* 198 */     return new IProperty[] { VARIANT };
/*     */   }
/*     */   
/*     */ 
/*     */   public String getStateName(IBlockState state, boolean fullName)
/*     */   {
/* 204 */     EffType type = (EffType)state.func_177229_b(VARIANT);
/*     */     
/* 206 */     return type.func_176610_l();
/*     */   }
/*     */   
/*     */   public static enum EffType implements IStringSerializable
/*     */   {
/* 211 */     SHOCK, 
/* 212 */     SAPPING, 
/* 213 */     GLIMMER;
/*     */     
/*     */     private EffType() {}
/*     */     
/*     */     public String func_176610_l()
/*     */     {
/* 219 */       return name().toLowerCase();
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 225 */       return func_176610_l();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/misc/BlockEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */