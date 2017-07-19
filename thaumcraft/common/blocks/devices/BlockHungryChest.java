/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumFacing.Axis;
/*     */ import net.minecraft.util.EnumFacing.Plane;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.particles.FXDigging;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.devices.TileHungryChest;
/*     */ 
/*     */ public class BlockHungryChest extends BlockContainer
/*     */ {
/*  40 */   public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
/*  41 */   private final Random rand = new Random();
/*     */   
/*     */   public BlockHungryChest() {
/*  44 */     super(Material.field_151575_d);
/*  45 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH));
/*  46 */     func_149711_c(2.5F);
/*  47 */     func_149672_a(field_149766_f);
/*  48 */     func_149647_a(Thaumcraft.tabTC);
/*  49 */     func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
/*     */   {
/*  54 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  60 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  66 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
/*     */   {
/*  78 */     int i = target.func_178782_a().func_177958_n();
/*  79 */     int j = target.func_178782_a().func_177956_o();
/*  80 */     int k = target.func_178782_a().func_177952_p();
/*  81 */     float f = 0.1F;
/*  82 */     double d0 = i + this.rand.nextDouble() * (func_149753_y() - func_149704_x() - f * 2.0F) + f + func_149704_x();
/*  83 */     double d1 = j + this.rand.nextDouble() * (func_149669_A() - func_149665_z() - f * 2.0F) + f + func_149665_z();
/*  84 */     double d2 = k + this.rand.nextDouble() * (func_149693_C() - func_149706_B() - f * 2.0F) + f + func_149706_B();
/*     */     
/*  86 */     if (target.field_178784_b == EnumFacing.DOWN)
/*     */     {
/*  88 */       d1 = j + func_149665_z() - f;
/*     */     }
/*     */     
/*  91 */     if (target.field_178784_b == EnumFacing.UP)
/*     */     {
/*  93 */       d1 = j + func_149669_A() + f;
/*     */     }
/*     */     
/*  96 */     if (target.field_178784_b == EnumFacing.NORTH)
/*     */     {
/*  98 */       d2 = k + func_149706_B() - f;
/*     */     }
/*     */     
/* 101 */     if (target.field_178784_b == EnumFacing.SOUTH)
/*     */     {
/* 103 */       d2 = k + func_149693_C() + f;
/*     */     }
/*     */     
/* 106 */     if (target.field_178784_b == EnumFacing.WEST)
/*     */     {
/* 108 */       d0 = i + func_149704_x() - f;
/*     */     }
/*     */     
/* 111 */     if (target.field_178784_b == EnumFacing.EAST)
/*     */     {
/* 113 */       d0 = i + func_149753_y() + f;
/*     */     }
/*     */     
/* 116 */     effectRenderer.func_78873_a(new FXDigging(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, BlocksTC.plank.func_176223_P()).func_174846_a(target.func_178782_a()));
/*     */     
/* 118 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer)
/*     */   {
/* 124 */     byte b0 = 4;
/* 125 */     for (int i = 0; i < b0; i++)
/*     */     {
/* 127 */       for (int j = 0; j < b0; j++)
/*     */       {
/* 129 */         for (int k = 0; k < b0; k++)
/*     */         {
/* 131 */           double d0 = pos.func_177958_n() + (i + 0.5D) / b0;
/* 132 */           double d1 = pos.func_177956_o() + (j + 0.5D) / b0;
/* 133 */           double d2 = pos.func_177952_p() + (k + 0.5D) / b0;
/* 134 */           effectRenderer.func_78873_a(new FXDigging(world, d0, d1, d2, d0 - pos.func_177958_n() - 0.5D, d1 - pos.func_177956_o() - 0.5D, d2 - pos.func_177952_p() - 0.5D, BlocksTC.plank.func_176223_P()).func_174846_a(pos));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 142 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/* 149 */     func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_180640_a(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 154 */     float var5 = 0.0625F;
/* 155 */     return AxisAlignedBB.func_178781_a(pos.func_177958_n() + var5, pos.func_177956_o(), pos.func_177952_p() + var5, pos.func_177958_n() + 1 - var5, pos.func_177956_o() + 1 - var5, pos.func_177952_p() + 1 - var5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/* 163 */     return func_176223_P().func_177226_a(FACING, placer.func_174811_aO());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
/*     */   {
/* 169 */     EnumFacing enumfacing = EnumFacing.func_176731_b(MathHelper.func_76128_c(placer.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3).func_176734_d();
/* 170 */     state = state.func_177226_a(FACING, enumfacing);
/* 171 */     worldIn.func_180501_a(pos, state, 3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 177 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/*     */     
/* 179 */     if ((tileentity instanceof IInventory))
/*     */     {
/* 181 */       InventoryHelper.func_180175_a(worldIn, pos, (IInventory)tileentity);
/* 182 */       worldIn.func_175666_e(pos, this);
/*     */     }
/*     */     
/* 185 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 191 */     if (worldIn.field_72995_K)
/*     */     {
/* 193 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 197 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/*     */     
/* 199 */     if ((tileentity instanceof IInventory))
/*     */     {
/* 201 */       playerIn.func_71007_a((IInventory)tileentity);
/*     */     }
/*     */     
/* 204 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
/*     */   {
/* 213 */     Object var10 = (TileHungryChest)world.func_175625_s(pos);
/* 214 */     if (var10 == null)
/*     */     {
/* 216 */       return;
/*     */     }
/* 218 */     if (world.field_72995_K) {
/* 219 */       return;
/*     */     }
/*     */     
/* 222 */     if (((entity instanceof EntityItem)) && (!entity.field_70128_L)) {
/* 223 */       ItemStack leftovers = InventoryUtils.placeItemStackIntoInventory(((EntityItem)entity).func_92059_d(), (IInventory)var10, EnumFacing.UP, true);
/* 224 */       if ((leftovers == null) || (leftovers.field_77994_a != ((EntityItem)entity).func_92059_d().field_77994_a)) {
/* 225 */         world.func_72956_a(entity, "random.eat", 0.25F, (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/* 226 */         world.func_175641_c(pos, BlocksTC.hungryChest, 2, 2);
/*     */       }
/*     */       
/* 229 */       if (leftovers != null) {
/* 230 */         ((EntityItem)entity).func_92058_a(leftovers);
/*     */       } else {
/* 232 */         entity.func_70106_y();
/*     */       }
/* 234 */       ((TileHungryChest)var10).func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149740_M()
/*     */   {
/* 241 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180641_l(World worldIn, BlockPos pos)
/*     */   {
/* 247 */     Object var10 = worldIn.func_175625_s(pos);
/* 248 */     if ((var10 instanceof TileHungryChest)) {
/* 249 */       return Container.func_94526_b((IInventory)var10);
/*     */     }
/* 251 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_176203_a(int meta)
/*     */   {
/* 257 */     EnumFacing enumfacing = EnumFacing.func_82600_a(meta);
/*     */     
/* 259 */     if (enumfacing.func_176740_k() == EnumFacing.Axis.Y)
/*     */     {
/* 261 */       enumfacing = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 264 */     return func_176223_P().func_177226_a(FACING, enumfacing);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_176201_c(IBlockState state)
/*     */   {
/* 270 */     return ((EnumFacing)state.func_177229_b(FACING)).func_176745_a();
/*     */   }
/*     */   
/*     */ 
/*     */   protected BlockState func_180661_e()
/*     */   {
/* 276 */     return new BlockState(this, new IProperty[] { FACING });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TileEntity func_149915_a(World par1World, int m)
/*     */   {
/* 283 */     return new TileHungryChest();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockHungryChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */