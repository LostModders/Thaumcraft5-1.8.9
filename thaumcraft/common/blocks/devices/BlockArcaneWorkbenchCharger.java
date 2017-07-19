/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.particles.FXDigging;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbenchCharger;
/*     */ 
/*     */ public class BlockArcaneWorkbenchCharger
/*     */   extends BlockTCDevice
/*     */ {
/*     */   public BlockArcaneWorkbenchCharger()
/*     */   {
/*  26 */     super(Material.field_151575_d, TileArcaneWorkbenchCharger.class);
/*  27 */     func_149672_a(Block.field_149766_f);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  33 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  39 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  45 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/*  51 */     if (world.field_72995_K) return true;
/*  52 */     if (world.func_180495_p(pos.func_177977_b()).func_177230_c() == BlocksTC.arcaneWorkbench)
/*  53 */       player.openGui(Thaumcraft.instance, 13, world, pos.func_177958_n(), pos.func_177977_b().func_177956_o(), pos.func_177952_p());
/*  54 */     return true;
/*     */   }
/*     */   
/*  57 */   private final Random rand = new Random();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
/*     */   {
/*  62 */     int i = target.func_178782_a().func_177958_n();
/*  63 */     int j = target.func_178782_a().func_177956_o();
/*  64 */     int k = target.func_178782_a().func_177952_p();
/*  65 */     float f = 0.1F;
/*  66 */     double d0 = i + this.rand.nextDouble() * (func_149753_y() - func_149704_x() - f * 2.0F) + f + func_149704_x();
/*  67 */     double d1 = j + this.rand.nextDouble() * (func_149669_A() - func_149665_z() - f * 2.0F) + f + func_149665_z();
/*  68 */     double d2 = k + this.rand.nextDouble() * (func_149693_C() - func_149706_B() - f * 2.0F) + f + func_149706_B();
/*     */     
/*  70 */     if (target.field_178784_b == EnumFacing.DOWN)
/*     */     {
/*  72 */       d1 = j + func_149665_z() - f;
/*     */     }
/*     */     
/*  75 */     if (target.field_178784_b == EnumFacing.UP)
/*     */     {
/*  77 */       d1 = j + func_149669_A() + f;
/*     */     }
/*     */     
/*  80 */     if (target.field_178784_b == EnumFacing.NORTH)
/*     */     {
/*  82 */       d2 = k + func_149706_B() - f;
/*     */     }
/*     */     
/*  85 */     if (target.field_178784_b == EnumFacing.SOUTH)
/*     */     {
/*  87 */       d2 = k + func_149693_C() + f;
/*     */     }
/*     */     
/*  90 */     if (target.field_178784_b == EnumFacing.WEST)
/*     */     {
/*  92 */       d0 = i + func_149704_x() - f;
/*     */     }
/*     */     
/*  95 */     if (target.field_178784_b == EnumFacing.EAST)
/*     */     {
/*  97 */       d0 = i + func_149753_y() + f;
/*     */     }
/*     */     
/* 100 */     effectRenderer.func_78873_a(new FXDigging(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, BlocksTC.plank.func_176223_P()).func_174846_a(target.func_178782_a()));
/*     */     
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer)
/*     */   {
/* 108 */     byte b0 = 4;
/* 109 */     for (int i = 0; i < b0; i++)
/*     */     {
/* 111 */       for (int j = 0; j < b0; j++)
/*     */       {
/* 113 */         for (int k = 0; k < b0; k++)
/*     */         {
/* 115 */           double d0 = pos.func_177958_n() + (i + 0.5D) / b0;
/* 116 */           double d1 = pos.func_177956_o() + (j + 0.5D) / b0;
/* 117 */           double d2 = pos.func_177952_p() + (k + 0.5D) / b0;
/* 118 */           effectRenderer.func_78873_a(new FXDigging(world, d0, d1, d2, d0 - pos.func_177958_n() - 0.5D, d1 - pos.func_177956_o() - 0.5D, d2 - pos.func_177952_p() - 0.5D, BlocksTC.plank.func_176223_P()).func_174846_a(pos));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 126 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockArcaneWorkbenchCharger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */