/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.particles.FXDigging;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.tiles.crafting.TileInfusionMatrix;
/*     */ 
/*     */ public class BlockInfusionMatrix extends BlockTCDevice
/*     */ {
/*     */   public BlockInfusionMatrix()
/*     */   {
/*  22 */     super(Material.field_151576_e, TileInfusionMatrix.class);
/*  23 */     func_149672_a(Block.field_149769_e);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  29 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  35 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  41 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*  45 */   private final Random rand = new Random();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
/*     */   {
/*  50 */     int i = target.func_178782_a().func_177958_n();
/*  51 */     int j = target.func_178782_a().func_177956_o();
/*  52 */     int k = target.func_178782_a().func_177952_p();
/*  53 */     float f = 0.1F;
/*  54 */     double d0 = i + this.rand.nextDouble() * (func_149753_y() - func_149704_x() - f * 2.0F) + f + func_149704_x();
/*  55 */     double d1 = j + this.rand.nextDouble() * (func_149669_A() - func_149665_z() - f * 2.0F) + f + func_149665_z();
/*  56 */     double d2 = k + this.rand.nextDouble() * (func_149693_C() - func_149706_B() - f * 2.0F) + f + func_149706_B();
/*     */     
/*  58 */     if (target.field_178784_b == EnumFacing.DOWN)
/*     */     {
/*  60 */       d1 = j + func_149665_z() - f;
/*     */     }
/*     */     
/*  63 */     if (target.field_178784_b == EnumFacing.UP)
/*     */     {
/*  65 */       d1 = j + func_149669_A() + f;
/*     */     }
/*     */     
/*  68 */     if (target.field_178784_b == EnumFacing.NORTH)
/*     */     {
/*  70 */       d2 = k + func_149706_B() - f;
/*     */     }
/*     */     
/*  73 */     if (target.field_178784_b == EnumFacing.SOUTH)
/*     */     {
/*  75 */       d2 = k + func_149693_C() + f;
/*     */     }
/*     */     
/*  78 */     if (target.field_178784_b == EnumFacing.WEST)
/*     */     {
/*  80 */       d0 = i + func_149704_x() - f;
/*     */     }
/*     */     
/*  83 */     if (target.field_178784_b == EnumFacing.EAST)
/*     */     {
/*  85 */       d0 = i + func_149753_y() + f;
/*     */     }
/*     */     
/*  88 */     effectRenderer.func_78873_a(new FXDigging(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, BlocksTC.stone.func_176223_P()).func_174846_a(target.func_178782_a()));
/*     */     
/*  90 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer)
/*     */   {
/*  96 */     byte b0 = 4;
/*  97 */     for (int i = 0; i < b0; i++)
/*     */     {
/*  99 */       for (int j = 0; j < b0; j++)
/*     */       {
/* 101 */         for (int k = 0; k < b0; k++)
/*     */         {
/* 103 */           double d0 = pos.func_177958_n() + (i + 0.5D) / b0;
/* 104 */           double d1 = pos.func_177956_o() + (j + 0.5D) / b0;
/* 105 */           double d2 = pos.func_177952_p() + (k + 0.5D) / b0;
/* 106 */           effectRenderer.func_78873_a(new FXDigging(world, d0, d1, d2, d0 - pos.func_177958_n() - 0.5D, d1 - pos.func_177956_o() - 0.5D, d2 - pos.func_177952_p() - 0.5D, BlocksTC.stone.func_176223_P()).func_174846_a(pos));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 114 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/devices/BlockInfusionMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */