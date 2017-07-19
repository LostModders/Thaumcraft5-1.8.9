/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemBow;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class LayerHeldItemPech implements net.minecraft.client.renderer.entity.layers.LayerRenderer
/*    */ {
/*    */   private final RendererLivingEntity field_177206_a;
/*    */   
/*    */   public LayerHeldItemPech(RendererLivingEntity p_i46115_1_)
/*    */   {
/* 28 */     this.field_177206_a = p_i46115_1_;
/*    */   }
/*    */   
/*    */   public void func_177141_a(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
/*    */   {
/* 33 */     ItemStack itemstack = p_177141_1_.func_70694_bm();
/*    */     
/* 35 */     if (itemstack != null)
/*    */     {
/* 37 */       GlStateManager.func_179094_E();
/*    */       
/* 39 */       if (this.field_177206_a.func_177087_b().field_78091_s)
/*    */       {
/* 41 */         float f7 = 0.5F;
/* 42 */         GlStateManager.func_179109_b(0.0F, 0.625F, 0.0F);
/* 43 */         GlStateManager.func_179114_b(-20.0F, -1.0F, 0.0F, 0.0F);
/* 44 */         GlStateManager.func_179152_a(f7, f7, f7);
/*    */       }
/*    */       
/* 47 */       ((ModelBiped)this.field_177206_a.func_177087_b()).func_178718_a(0.0625F);
/* 48 */       GlStateManager.func_179109_b(-0.0625F, 0.32F, 0.0625F);
/*    */       
/* 50 */       if ((itemstack.func_77973_b() instanceof IWand)) {
/* 51 */         GlStateManager.func_179137_b(0.0D, -0.1D, 0.0D);
/*    */       }
/*    */       
/* 54 */       if ((itemstack.func_77973_b() instanceof ItemBow)) {
/* 55 */         GlStateManager.func_179137_b(-0.07500000298023224D, -0.1D, 0.0D);
/*    */       }
/*    */       
/* 58 */       if (((p_177141_1_ instanceof EntityPlayer)) && (((EntityPlayer)p_177141_1_).field_71104_cf != null))
/*    */       {
/* 60 */         itemstack = new ItemStack(Items.field_151112_aM, 0);
/*    */       }
/*    */       
/* 63 */       Item item = itemstack.func_77973_b();
/* 64 */       Minecraft minecraft = Minecraft.func_71410_x();
/*    */       
/* 66 */       if (((item instanceof ItemBlock)) && (Block.func_149634_a(item).func_149645_b() == 2))
/*    */       {
/* 68 */         GlStateManager.func_179109_b(0.0F, 0.1875F, -0.3125F);
/* 69 */         GlStateManager.func_179114_b(20.0F, 1.0F, 0.0F, 0.0F);
/* 70 */         GlStateManager.func_179114_b(45.0F, 0.0F, 1.0F, 0.0F);
/* 71 */         float f8 = 0.375F;
/* 72 */         GlStateManager.func_179152_a(-f8, -f8, f8);
/*    */       }
/*    */       
/* 75 */       minecraft.func_175597_ag().func_178099_a(p_177141_1_, itemstack, net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType.THIRD_PERSON);
/* 76 */       GlStateManager.func_179121_F();
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean func_177142_b()
/*    */   {
/* 82 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/mob/LayerHeldItemPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */