/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockModelRenderer;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityFallingBlock;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.entities.EntityFallingTaint;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderFallingTaint extends Render
/*    */ {
/*    */   public RenderFallingTaint(RenderManager p_i46177_1_)
/*    */   {
/* 29 */     super(p_i46177_1_);
/* 30 */     this.field_76989_e = 0.5F;
/*    */   }
/*    */   
/*    */   public void doRender(EntityFallingTaint p_180557_1_, double p_180557_2_, double p_180557_4_, double p_180557_6_, float p_180557_8_, float p_180557_9_)
/*    */   {
/* 35 */     if (p_180557_1_.getBlock() != null)
/*    */     {
/* 37 */       func_110776_a(TextureMap.field_110575_b);
/* 38 */       IBlockState iblockstate = p_180557_1_.getBlock();
/* 39 */       Block block = iblockstate.func_177230_c();
/* 40 */       BlockPos blockpos = new BlockPos(p_180557_1_);
/* 41 */       World world = p_180557_1_.getWorld();
/*    */       
/* 43 */       if ((iblockstate != world.func_180495_p(blockpos)) && (block.func_149645_b() != -1))
/*    */       {
/* 45 */         if (block.func_149645_b() == 3)
/*    */         {
/* 47 */           GlStateManager.func_179094_E();
/* 48 */           GlStateManager.func_179109_b((float)p_180557_2_, (float)p_180557_4_, (float)p_180557_6_);
/* 49 */           GlStateManager.func_179140_f();
/* 50 */           Tessellator tessellator = Tessellator.func_178181_a();
/* 51 */           WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 52 */           worldrenderer.func_181668_a(7, DefaultVertexFormats.field_176600_a);
/*    */           
/* 54 */           int i = blockpos.func_177958_n();
/* 55 */           int j = blockpos.func_177956_o();
/* 56 */           int k = blockpos.func_177952_p();
/* 57 */           worldrenderer.func_178969_c(-i - 0.5F, -j, -k - 0.5F);
/* 58 */           BlockRendererDispatcher blockrendererdispatcher = Minecraft.func_71410_x().func_175602_ab();
/* 59 */           net.minecraft.client.resources.model.IBakedModel ibakedmodel = blockrendererdispatcher.func_175022_a(iblockstate, world, (BlockPos)null);
/* 60 */           blockrendererdispatcher.func_175019_b().func_178267_a(world, ibakedmodel, iblockstate, blockpos, worldrenderer, false);
/* 61 */           worldrenderer.func_178969_c(0.0D, 0.0D, 0.0D);
/* 62 */           tessellator.func_78381_a();
/* 63 */           GlStateManager.func_179145_e();
/* 64 */           GlStateManager.func_179121_F();
/* 65 */           super.func_76986_a(p_180557_1_, p_180557_2_, p_180557_4_, p_180557_6_, p_180557_8_, p_180557_9_);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityFallingBlock entity)
/*    */   {
/* 73 */     return TextureMap.field_110575_b;
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 79 */     return getEntityTexture((EntityFallingBlock)entity);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
/*    */   {
/* 85 */     doRender((EntityFallingTaint)entity, x, y, z, p_76986_8_, partialTicks);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/RenderFallingTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */