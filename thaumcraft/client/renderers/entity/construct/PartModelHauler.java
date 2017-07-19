/*    */ package thaumcraft.client.renderers.entity.construct;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel;
/*    */ import thaumcraft.api.golems.parts.PartModel.EnumAttachPoint;
/*    */ import thaumcraft.api.golems.parts.PartModel.EnumLimbSide;
/*    */ 
/*    */ public class PartModelHauler extends PartModel
/*    */ {
/*    */   public PartModelHauler(ResourceLocation objModel, ResourceLocation objTexture, PartModel.EnumAttachPoint attachPoint)
/*    */   {
/* 16 */     super(objModel, objTexture, attachPoint);
/*    */   }
/*    */   
/*    */   public void postRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side)
/*    */   {
/* 21 */     if ((golem.getCarrying().length > 1) && (golem.getCarrying()[1] != null)) {
/* 22 */       ItemStack itemstack = golem.getCarrying()[1];
/* 23 */       if (itemstack != null)
/*    */       {
/* 25 */         GlStateManager.func_179094_E();
/* 26 */         net.minecraft.item.Item item = itemstack.func_77973_b();
/* 27 */         Minecraft minecraft = Minecraft.func_71410_x();
/* 28 */         GlStateManager.func_179139_a(0.375D, 0.375D, 0.375D);
/* 29 */         GlStateManager.func_179109_b(0.0F, 0.33F, 0.825F);
/*    */         
/* 31 */         if (!(item instanceof net.minecraft.item.ItemBlock)) {
/* 32 */           GlStateManager.func_179109_b(0.0F, 0.0F, -0.25F);
/*    */         }
/*    */         
/* 35 */         minecraft.func_175597_ag().func_178099_a(golem.getGolemEntity(), itemstack, net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType.HEAD);
/* 36 */         GlStateManager.func_179121_F();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/PartModelHauler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */