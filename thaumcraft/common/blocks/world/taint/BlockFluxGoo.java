/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidFinite;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXBubble;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks.FluidFluxGoo;
/*     */ import thaumcraft.common.entities.monster.EntityThaumicSlime;
/*     */ 
/*     */ public class BlockFluxGoo extends BlockFluidFinite
/*     */ {
/*     */   public BlockFluxGoo()
/*     */   {
/*  30 */     super(ConfigBlocks.FluidFluxGoo.instance, thaumcraft.api.ThaumcraftMaterials.MATERIAL_TAINT);
/*  31 */     func_149663_c("flux_goo");
/*  32 */     func_149647_a(Thaumcraft.tabTC);
/*  33 */     func_149672_a(new thaumcraft.common.lib.CustomSoundType("gore", 1.0F, 1.0F));
/*  34 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(LEVEL, Integer.valueOf(7)));
/*     */   }
/*     */   
/*     */   static {
/*  38 */     defaultDisplacements.put(BlocksTC.taintFibre, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
/*     */   {
/*  43 */     int md = ((Integer)state.func_177229_b(LEVEL)).intValue();
/*  44 */     if ((entity instanceof EntityThaumicSlime)) {
/*  45 */       EntityThaumicSlime slime = (EntityThaumicSlime)entity;
/*  46 */       if ((slime.func_70809_q() < md) && (world.field_73012_v.nextBoolean())) {
/*  47 */         slime.func_70799_a(slime.func_70809_q() + 1);
/*  48 */         if (md > 1) {
/*  49 */           world.func_180501_a(pos, state.func_177226_a(LEVEL, Integer.valueOf(md - 1)), 2);
/*     */         } else {
/*  51 */           world.func_175698_g(pos);
/*     */         }
/*     */       }
/*     */     } else {
/*  55 */       entity.field_70159_w *= (1.0F - getQuantaPercentage(world, pos));
/*  56 */       entity.field_70179_y *= (1.0F - getQuantaPercentage(world, pos));
/*  57 */       if ((entity instanceof EntityLivingBase)) {
/*  58 */         PotionEffect pe = new PotionEffect(PotionVisExhaust.instance.func_76396_c(), 600, md / 3, true, true);
/*  59 */         pe.getCurativeItems().clear();
/*  60 */         ((EntityLivingBase)entity).func_70690_d(pe);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/*  68 */     int meta = ((Integer)state.func_177229_b(LEVEL)).intValue();
/*     */     
/*  70 */     if ((meta >= 2) && (meta < 6) && (world.func_175623_d(pos.func_177984_a())) && (rand.nextInt(50) == 0)) {
/*  71 */       world.func_175698_g(pos);
/*  72 */       EntityThaumicSlime slime = new EntityThaumicSlime(world);
/*  73 */       slime.func_70012_b(pos.func_177958_n() + 0.5F, pos.func_177956_o(), pos.func_177952_p() + 0.5F, 0.0F, 0.0F);
/*  74 */       slime.func_70799_a(1);
/*  75 */       world.func_72838_d(slime);
/*  76 */       world.func_72956_a(slime, "thaumcraft:gore", 1.0F, 1.0F);
/*  77 */       return; }
/*  78 */     if ((meta >= 6) && (world.func_175623_d(pos.func_177984_a())) && (rand.nextInt(50) == 0)) {
/*  79 */       world.func_175698_g(pos);
/*  80 */       EntityThaumicSlime slime = new EntityThaumicSlime(world);
/*  81 */       slime.func_70012_b(pos.func_177958_n() + 0.5F, pos.func_177956_o(), pos.func_177952_p() + 0.5F, 0.0F, 0.0F);
/*  82 */       slime.func_70799_a(2);
/*  83 */       world.func_72838_d(slime);
/*  84 */       world.func_72956_a(slime, "thaumcraft:gore", 1.0F, 1.0F);
/*  85 */       return; }
/*  86 */     if (rand.nextInt(4) == 0) {
/*  87 */       if (meta == 0) {
/*  88 */         if (rand.nextBoolean()) {
/*  89 */           AuraHelper.pollute(world, pos, 1, true);
/*  90 */           world.func_175698_g(pos);
/*     */         } else {
/*  92 */           world.func_175656_a(pos, BlocksTC.taintFibre.func_176223_P());
/*     */         }
/*     */       } else {
/*  95 */         world.func_180501_a(pos, state.func_177226_a(LEVEL, Integer.valueOf(meta - 1)), 2);
/*  96 */         AuraHelper.pollute(world, pos, 1, true);
/*     */       }
/*  98 */       return;
/*     */     }
/*     */     
/* 101 */     super.func_180650_b(world, pos, state, rand);
/*     */   }
/*     */   
/*     */   public boolean func_176200_f(World world, BlockPos pos)
/*     */   {
/* 106 */     return ((Integer)world.func_180495_p(pos).func_177229_b(LEVEL)).intValue() < 4;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_176212_b(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
/*     */   {
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*     */   public void func_180655_c(World world, BlockPos pos, IBlockState state, Random rand)
/*     */   {
/* 118 */     int meta = func_176201_c(state);
/* 119 */     if (rand.nextInt(66 - Thaumcraft.proxy.getFX().particleCount(10)) <= meta) {
/* 120 */       FXBubble fb = new FXBubble(world, pos.func_177958_n() + rand.nextFloat(), pos.func_177956_o() + 0.125F * meta, pos.func_177952_p() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, 0);
/*     */       
/* 122 */       fb.func_82338_g(0.25F);
/* 123 */       ParticleEngine.instance.addEffect(world, fb);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/world/taint/BlockFluxGoo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */