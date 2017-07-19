/*    */ package thaumcraft.client.lib;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.renderer.ItemModelMesher;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
/*    */ import net.minecraft.client.renderer.entity.RenderItem;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.client.resources.model.IBakedModel;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ 
/*    */ public class CustomRenderItem
/*    */   extends RenderItem
/*    */ {
/*    */   public CustomRenderItem()
/*    */   {
/* 22 */     super(null, Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178083_a());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_175041_b() {}
/*    */   
/*    */ 
/*    */   public void func_180453_a(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, String text)
/*    */   {
/* 32 */     if ((stack != null) && (stack.field_77994_a <= 0)) text = EnumChatFormatting.GOLD + "*";
/* 33 */     Minecraft.func_71410_x().func_175599_af().func_180453_a(fr, stack, xPosition, yPosition, text);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void func_175048_a(Item itm, int subType, String identifier) {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected void func_175029_a(Block blk, int subType, String identifier) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_175039_a(boolean p_175039_1_)
/*    */   {
/* 48 */     Minecraft.func_71410_x().func_175599_af().func_175039_a(p_175039_1_);
/*    */   }
/*    */   
/*    */   public ItemModelMesher func_175037_a()
/*    */   {
/* 53 */     return Minecraft.func_71410_x().func_175599_af().func_175037_a();
/*    */   }
/*    */   
/*    */   public void func_180454_a(ItemStack stack, IBakedModel model)
/*    */   {
/* 58 */     Minecraft.func_71410_x().func_175599_af().func_180454_a(stack, model);
/*    */   }
/*    */   
/*    */   public boolean func_175050_a(ItemStack stack)
/*    */   {
/* 63 */     return Minecraft.func_71410_x().func_175599_af().func_175050_a(stack);
/*    */   }
/*    */   
/*    */   public void func_181564_a(ItemStack p_181564_1_, ItemCameraTransforms.TransformType p_181564_2_)
/*    */   {
/* 68 */     Minecraft.func_71410_x().func_175599_af().func_181564_a(p_181564_1_, p_181564_2_);
/*    */   }
/*    */   
/*    */   public void func_175049_a(ItemStack stack, EntityLivingBase entityToRenderFor, ItemCameraTransforms.TransformType cameraTransformType)
/*    */   {
/* 73 */     Minecraft.func_71410_x().func_175599_af().func_175049_a(stack, entityToRenderFor, cameraTransformType);
/*    */   }
/*    */   
/*    */   public void func_175042_a(ItemStack stack, int x, int y)
/*    */   {
/* 78 */     Minecraft.func_71410_x().func_175599_af().func_175042_a(stack, x, y);
/*    */   }
/*    */   
/*    */   public void func_180450_b(ItemStack stack, int xPosition, int yPosition)
/*    */   {
/* 83 */     Minecraft.func_71410_x().func_175599_af().func_180450_b(stack, xPosition, yPosition);
/*    */   }
/*    */   
/*    */   public void func_175030_a(FontRenderer fr, ItemStack stack, int xPosition, int yPosition)
/*    */   {
/* 88 */     Minecraft.func_71410_x().func_175599_af().func_175030_a(fr, stack, xPosition, yPosition);
/*    */   }
/*    */   
/*    */   public void func_110549_a(IResourceManager resourceManager)
/*    */   {
/* 93 */     Minecraft.func_71410_x().func_175599_af().func_110549_a(resourceManager);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/lib/CustomRenderItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */