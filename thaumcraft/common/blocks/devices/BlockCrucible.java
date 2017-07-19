/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.tiles.crafting.TileCrucible;
/*     */ 
/*     */ public class BlockCrucible extends BlockTCDevice
/*     */ {
/*     */   public BlockCrucible()
/*     */   {
/*  36 */     super(net.minecraft.block.material.Material.field_151573_f, TileCrucible.class);
/*  37 */     func_149672_a(net.minecraft.block.Block.field_149777_j);
/*     */   }
/*     */   
/*  40 */   private int delay = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
/*     */   {
/*  46 */     if (!world.field_72995_K) {
/*  47 */       TileCrucible tile = (TileCrucible)world.func_175625_s(pos);
/*  48 */       if ((tile != null) && ((entity instanceof EntityItem)) && (!(entity instanceof thaumcraft.common.entities.EntitySpecialItem)) && (tile.heat > 150) && (tile.tank.getFluidAmount() > 0))
/*     */       {
/*     */ 
/*     */ 
/*  52 */         tile.attemptSmelt((EntityItem)entity);
/*     */       } else {
/*  54 */         this.delay += 1;
/*  55 */         if (this.delay < 10) return;
/*  56 */         this.delay = 0;
/*  57 */         if (((entity instanceof net.minecraft.entity.EntityLivingBase)) && (tile != null) && (tile.heat > 150) && (tile.tank.getFluidAmount() > 0))
/*     */         {
/*     */ 
/*  60 */           entity.func_70097_a(DamageSource.field_76372_a, 1.0F);
/*  61 */           world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "random.fizz", 0.4F, 2.0F + world.field_73012_v.nextFloat() * 0.4F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  66 */     super.func_180634_a(world, pos, state, entity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
/*     */   {
/*  75 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
/*  76 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*  77 */     float f = 0.125F;
/*  78 */     func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*  79 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*  80 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*  81 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*  82 */     func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  83 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*  84 */     func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*  85 */     super.func_180638_a(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */   
/*     */   public void func_180654_a(IBlockAccess worldIn, BlockPos pos)
/*     */   {
/*  90 */     func_149683_g();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_180646_a(World world, BlockPos pos)
/*     */   {
/*  96 */     return new AxisAlignedBB(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1.0D, pos.func_177956_o() + 1.0D, pos.func_177952_p() + 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149683_g()
/*     */   {
/* 104 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 110 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 116 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
/*     */   {
/* 122 */     TileEntity te = worldIn.func_175625_s(pos);
/* 123 */     if ((te != null) && ((te instanceof TileCrucible))) {
/* 124 */       ((TileCrucible)te).spillRemnants();
/*     */     }
/* 126 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 134 */     if (!world.field_72995_K) {
/* 135 */       FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(player.field_71071_by.func_70448_g());
/* 136 */       if ((fs != null) && (fs.isFluidEqual(new FluidStack(FluidRegistry.WATER, 1000)))) {
/* 137 */         int volume = fs.amount;
/* 138 */         TileEntity te = world.func_175625_s(pos);
/* 139 */         if ((te != null) && ((te instanceof TileCrucible))) {
/* 140 */           TileCrucible tile = (TileCrucible)te;
/* 141 */           if (tile.tank.getFluidAmount() < tile.tank.getCapacity()) {
/* 142 */             tile.fill(EnumFacing.UP, FluidContainerRegistry.getFluidForFilledItem(player.field_71071_by.func_70448_g()), true);
/* 143 */             ItemStack emptyContainer = null;
/* 144 */             FluidContainerRegistry.FluidContainerData[] fcs = FluidContainerRegistry.getRegisteredFluidContainerData();
/* 145 */             for (FluidContainerRegistry.FluidContainerData fcd : fcs) {
/* 146 */               if (fcd.filledContainer.func_77969_a(player.field_71071_by.func_70448_g())) {
/* 147 */                 emptyContainer = fcd.emptyContainer.func_77946_l();
/*     */               }
/*     */             }
/* 150 */             player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
/* 151 */             if ((emptyContainer != null) && 
/* 152 */               (!player.field_71071_by.func_70441_a(emptyContainer))) {
/* 153 */               player.func_71019_a(emptyContainer, false);
/*     */             }
/*     */             
/* 156 */             player.field_71069_bz.func_75142_b();
/* 157 */             te.func_70296_d();
/* 158 */             world.func_175689_h(pos);
/* 159 */             world.func_72908_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, "game.neutral.swim", 0.33F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*     */             
/* 161 */             return true;
/*     */           }
/*     */         }
/*     */       }
/* 165 */       else if ((player.field_71071_by.func_70448_g() != null) && (!player.func_70093_af()) && (!(player.func_71045_bC().func_77973_b() instanceof IWand)) && (side == EnumFacing.UP))
/*     */       {
/* 167 */         TileEntity te = world.func_175625_s(pos);
/* 168 */         if ((te != null) && ((te instanceof TileCrucible))) {
/* 169 */           TileCrucible tile = (TileCrucible)te;
/* 170 */           ItemStack ti = player.field_71071_by.func_70448_g().func_77946_l();
/* 171 */           ti.field_77994_a = 1;
/* 172 */           if ((tile.heat > 150) && (tile.tank.getFluidAmount() > 0) && (tile.attemptSmelt(ti, player.func_70005_c_()) == null)) {
/* 173 */             player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
/* 174 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     } else {
/* 179 */       return true;
/*     */     }
/*     */     
/* 182 */     return super.func_180639_a(world, pos, state, player, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 187 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_180641_l(World world, BlockPos pos)
/*     */   {
/* 193 */     TileEntity te = world.func_175625_s(pos);
/* 194 */     if ((te != null) && ((te instanceof TileCrucible))) {
/* 195 */       ((TileCrucible)te).getClass();float r = ((TileCrucible)te).aspects.visSize() / 100.0F;
/* 196 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileCrucible)te).aspects.visSize() > 0 ? 1 : 0);
/*     */     }
/* 198 */     return 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(World w, BlockPos pos, IBlockState state, Random r)
/*     */   {
/* 204 */     if (r.nextInt(10) == 0) {
/* 205 */       TileEntity te = w.func_175625_s(pos);
/* 206 */       if ((te != null) && ((te instanceof TileCrucible)) && 
/* 207 */         (((TileCrucible)te).tank.getFluidAmount() > 0) && (((TileCrucible)te).heat > 150)) {
/* 208 */         w.func_72980_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), "liquid.lavapop", 0.1F + r.nextFloat() * 0.1F, 1.2F + r.nextFloat() * 0.2F, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockCrucible.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */