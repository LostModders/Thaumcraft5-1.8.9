/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.particles.FXDigging;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.tiles.devices.TileBellows;
/*     */ 
/*     */ public class BlockBellows extends BlockTCDevice implements IBlockFacing, IBlockEnabled
/*     */ {
/*     */   public BlockBellows()
/*     */   {
/*  26 */     super(Material.field_151575_d, TileBellows.class);
/*  27 */     func_149672_a(Block.field_149766_f);
/*  28 */     func_149711_c(1.0F);
/*     */   }
/*     */   
/*     */   public int func_180651_a(IBlockState state)
/*     */   {
/*  33 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  39 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  51 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
/*     */   {
/*  57 */     IBlockState bs = func_176223_P();
/*  58 */     if ((this instanceof IBlockFacing)) bs = bs.func_177226_a(IBlockFacing.FACING, facing.func_176734_d());
/*  59 */     if ((this instanceof IBlockEnabled)) bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true));
/*  60 */     return bs;
/*     */   }
/*     */   
/*  63 */   private final Random rand = new Random();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
/*     */   {
/*  68 */     int i = target.func_178782_a().func_177958_n();
/*  69 */     int j = target.func_178782_a().func_177956_o();
/*  70 */     int k = target.func_178782_a().func_177952_p();
/*  71 */     float f = 0.1F;
/*  72 */     double d0 = i + this.rand.nextDouble() * (func_149753_y() - func_149704_x() - f * 2.0F) + f + func_149704_x();
/*  73 */     double d1 = j + this.rand.nextDouble() * (func_149669_A() - func_149665_z() - f * 2.0F) + f + func_149665_z();
/*  74 */     double d2 = k + this.rand.nextDouble() * (func_149693_C() - func_149706_B() - f * 2.0F) + f + func_149706_B();
/*     */     
/*  76 */     if (target.field_178784_b == EnumFacing.DOWN)
/*     */     {
/*  78 */       d1 = j + func_149665_z() - f;
/*     */     }
/*     */     
/*  81 */     if (target.field_178784_b == EnumFacing.UP)
/*     */     {
/*  83 */       d1 = j + func_149669_A() + f;
/*     */     }
/*     */     
/*  86 */     if (target.field_178784_b == EnumFacing.NORTH)
/*     */     {
/*  88 */       d2 = k + func_149706_B() - f;
/*     */     }
/*     */     
/*  91 */     if (target.field_178784_b == EnumFacing.SOUTH)
/*     */     {
/*  93 */       d2 = k + func_149693_C() + f;
/*     */     }
/*     */     
/*  96 */     if (target.field_178784_b == EnumFacing.WEST)
/*     */     {
/*  98 */       d0 = i + func_149704_x() - f;
/*     */     }
/*     */     
/* 101 */     if (target.field_178784_b == EnumFacing.EAST)
/*     */     {
/* 103 */       d0 = i + func_149753_y() + f;
/*     */     }
/*     */     
/* 106 */     effectRenderer.func_78873_a(new FXDigging(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, BlocksTC.plank.func_176223_P()).func_174846_a(target.func_178782_a()));
/*     */     
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer)
/*     */   {
/* 114 */     byte b0 = 4;
/* 115 */     for (int i = 0; i < b0; i++)
/*     */     {
/* 117 */       for (int j = 0; j < b0; j++)
/*     */       {
/* 119 */         for (int k = 0; k < b0; k++)
/*     */         {
/* 121 */           double d0 = pos.func_177958_n() + (i + 0.5D) / b0;
/* 122 */           double d1 = pos.func_177956_o() + (j + 0.5D) / b0;
/* 123 */           double d2 = pos.func_177952_p() + (k + 0.5D) / b0;
/* 124 */           effectRenderer.func_78873_a(new FXDigging(world, d0, d1, d2, d0 - pos.func_177958_n() - 0.5D, d1 - pos.func_177956_o() - 0.5D, d2 - pos.func_177952_p() - 0.5D, BlocksTC.plank.func_176223_P()).func_174846_a(pos));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 132 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockBellows.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */