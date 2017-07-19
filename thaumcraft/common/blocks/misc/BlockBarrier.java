/*     */ package thaumcraft.common.blocks.misc;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.tiles.misc.TileBarrierStone;
/*     */ 
/*     */ 
/*     */ public class BlockBarrier
/*     */   extends Block
/*     */ {
/*  29 */   public static final Material barrierMat = new MaterialBarrier();
/*     */   
/*     */   public BlockBarrier()
/*     */   {
/*  33 */     super(barrierMat);
/*  34 */     func_149647_a(null);
/*  35 */     func_149713_g(0);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  41 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149666_a(Item itemIn, CreativeTabs tab, List list) {}
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
/*     */   {
/*  49 */     return null;
/*     */   }
/*     */   
/*  52 */   public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing o) { return false; }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180638_a(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/*  58 */     if ((collidingEntity != null) && ((collidingEntity instanceof EntityLivingBase)) && (!(collidingEntity instanceof EntityPlayer))) {
/*  59 */       int a = 1;
/*  60 */       if (world.func_180495_p(pos.func_177979_c(a)) != BlocksTC.pavingStone.func_176223_P()) a++;
/*  61 */       if (world.func_175687_A(pos.func_177979_c(a)) == 0) {
/*  62 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  63 */         super.func_180638_a(world, pos, state, mask, list, collidingEntity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_176204_a(World world, BlockPos pos, IBlockState state, Block neighborBlock)
/*     */   {
/*  70 */     if ((world.func_180495_p(pos.func_177979_c(1)) != BlocksTC.pavingStone.func_176223_P()) && (world.func_180495_p(pos.func_177979_c(1)) != func_176223_P()))
/*     */     {
/*  72 */       world.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/*  78 */     int a = 1;
/*  79 */     while (a < 3) {
/*  80 */       TileEntity te = worldIn.func_175625_s(pos.func_177979_c(a));
/*  81 */       if ((te != null) && ((te instanceof TileBarrierStone))) {
/*  82 */         return te.func_145831_w().func_175687_A(pos.func_177979_c(a)) > 0;
/*     */       }
/*  84 */       a++;
/*     */     }
/*     */     
/*  87 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180654_a(IBlockAccess iblockaccess, BlockPos pos)
/*     */   {
/*  93 */     func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public boolean func_176200_f(World worldIn, BlockPos pos)
/*     */   {
/*  98 */     return true;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_180646_a(World worldIn, BlockPos pos)
/*     */   {
/* 103 */     return AxisAlignedBB.func_178781_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/* 107 */     return false;
/*     */   }
/*     */   
/* 110 */   public boolean func_149662_c() { return false; }
/*     */   
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune)
/*     */   {
/* 114 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public boolean isAir(IBlockAccess world, BlockPos pos)
/*     */   {
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   private static class MaterialBarrier extends Material
/*     */   {
/*     */     public MaterialBarrier()
/*     */     {
/* 126 */       super();
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_76230_c()
/*     */     {
/* 132 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_76220_a()
/*     */     {
/* 138 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_76228_b()
/*     */     {
/* 144 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/misc/BlockBarrier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */