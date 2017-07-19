/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidFinite;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ public class BlockTaintDust extends BlockFluidFinite
/*     */ {
/*     */   public BlockTaintDust()
/*     */   {
/*  30 */     super(thaumcraft.common.config.ConfigBlocks.FluidTaintDust.instance, Material.field_151595_p);
/*  31 */     func_149711_c(0.3F);
/*  32 */     func_149663_c("taint_dust");
/*  33 */     func_149647_a(Thaumcraft.tabTC);
/*  34 */     func_149672_a(field_149776_m);
/*  35 */     setQuantaPerBlock(4);
/*  36 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(LEVEL, Integer.valueOf(3)));
/*  37 */     setHarvestLevel("shovel", 0);
/*     */   }
/*     */   
/*     */   public boolean isToolEffective(String type, IBlockState state)
/*     */   {
/*  42 */     if (type.equals("shovel")) return true;
/*  43 */     return super.isToolEffective(type, state);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumWorldBlockLayer func_180664_k()
/*     */   {
/*  50 */     return EnumWorldBlockLayer.SOLID;
/*     */   }
/*     */   
/*     */   public boolean func_176200_f(World world, BlockPos pos)
/*     */   {
/*  55 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176212_b(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/*  61 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176209_a(IBlockState state, boolean fullHit)
/*     */   {
/*  67 */     return true;
/*     */   }
/*     */   
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/*  72 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public void func_176208_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
/*     */   {
/*  77 */     int t = func_176201_c(state);
/*  78 */     if ((!worldIn.field_72995_K) && (t > 0) && (worldIn.field_73012_v.nextFloat() < t * 0.0333F)) {
/*  79 */       if (worldIn.field_73012_v.nextBoolean()) {
/*  80 */         func_180635_a(worldIn, pos, new ItemStack(Items.field_151100_aR, 1, 15));
/*     */       } else
/*  82 */         func_180635_a(worldIn, pos, new ItemStack(ItemsTC.tainted, 1, 1));
/*     */     }
/*  84 */     super.func_176208_a(worldIn, pos, state, player);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_180646_a(World worldIn, BlockPos pos)
/*     */   {
/*  89 */     return new AxisAlignedBB(pos.func_177958_n() + this.field_149759_B, pos.func_177956_o() + this.field_149760_C, pos.func_177952_p() + this.field_149754_D, pos.func_177958_n() + this.field_149755_E, pos.func_177956_o() + 0.25F * (func_176201_c(worldIn.func_180495_p(pos)) + 1), pos.func_177952_p() + this.field_149757_G);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/*  96 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F * func_176201_c(worldIn.func_180495_p(pos)), 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/* 102 */     AxisAlignedBB axisalignedbb1 = func_180640_a(worldIn, pos, state);
/*     */     
/* 104 */     if ((axisalignedbb1 != null) && (mask.func_72326_a(axisalignedbb1)))
/*     */     {
/* 106 */       list.add(axisalignedbb1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_180640_a(World world, BlockPos pos, IBlockState state)
/*     */   {
/* 113 */     return new AxisAlignedBB(pos.func_177958_n() + this.field_149759_B, pos.func_177956_o() + this.field_149760_C, pos.func_177952_p() + this.field_149754_D, pos.func_177958_n() + this.field_149755_E, pos.func_177956_o() + 0.25F * func_176201_c(state), pos.func_177952_p() + this.field_149757_G);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/* 120 */     int meta = ((Integer)state.func_177229_b(LEVEL)).intValue();
/* 121 */     if (rand.nextInt(Config.dustDegrade) == 0) {
/* 122 */       if (meta == 0) {
/* 123 */         world.func_175698_g(pos);
/*     */       } else {
/* 125 */         world.func_180501_a(pos, state.func_177226_a(LEVEL, Integer.valueOf(meta - 1)), 2);
/*     */       }
/* 127 */       return;
/*     */     }
/* 129 */     super.func_180650_b(world, pos, state, rand);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/taint/BlockTaintDust.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */