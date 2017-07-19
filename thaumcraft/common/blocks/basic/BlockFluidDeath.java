/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.material.MaterialLiquid;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.BlockFluidClassic;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.damagesource.DamageSourceThaumcraft;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXSlimyBubble;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.ConfigBlocks.FluidDeath;
/*    */ 
/*    */ public class BlockFluidDeath
/*    */   extends BlockFluidClassic
/*    */ {
/* 24 */   public static final Material FLUID_DEATH_MATERIAL = new MaterialLiquid(MapColor.field_151678_z);
/*    */   
/*    */   public BlockFluidDeath() {
/* 27 */     super(ConfigBlocks.FluidDeath.instance, FLUID_DEATH_MATERIAL);
/* 28 */     func_149663_c("liquid_death");
/* 29 */     func_149647_a(Thaumcraft.tabTC);
/* 30 */     setQuantaPerBlock(4);
/*    */   }
/*    */   
/*    */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
/*    */   {
/* 35 */     if ((!world.field_72995_K) && ((entity instanceof EntityLivingBase))) {
/* 36 */       entity.func_70097_a(DamageSourceThaumcraft.dissolve, 4 - func_176201_c(state) + 1);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getQuanta() {
/* 41 */     return this.quantaPerBlock;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(World world, BlockPos pos, IBlockState state, Random rand)
/*    */   {
/* 49 */     if (rand.nextInt(20) == 0) {
/* 50 */       int meta = func_176201_c(state);
/* 51 */       float h = rand.nextFloat() * 0.075F;
/* 52 */       FXSlimyBubble ef = new FXSlimyBubble(world, pos.func_177958_n() + rand.nextFloat(), pos.func_177956_o() + 0.1F + 0.225F * (4 - meta), pos.func_177952_p() + rand.nextFloat(), 0.075F + h);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 58 */       ef.func_82338_g(0.8F);
/* 59 */       ef.func_70538_b(0.3F - rand.nextFloat() * 0.1F, 0.0F, 0.4F + rand.nextFloat() * 0.1F);
/*    */       
/* 61 */       ParticleEngine.instance.addEffect(world, ef);
/*    */     }
/*    */     
/* 64 */     if (rand.nextInt(50) == 0) {
/* 65 */       double var21 = pos.func_177958_n() + rand.nextFloat();
/* 66 */       double var22 = pos.func_177956_o() + this.field_149756_F;
/* 67 */       double var23 = pos.func_177952_p() + rand.nextFloat();
/* 68 */       world.func_72980_b(var21, var22, var23, "liquid.lavapop", 0.1F + rand.nextFloat() * 0.1F, 0.9F + rand.nextFloat() * 0.15F, false);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockFluidDeath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */