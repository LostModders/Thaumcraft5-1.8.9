/*    */ package thaumcraft.client.renderers.models.gear;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.lib.models.AdvancedModelLoader;
/*    */ import thaumcraft.client.lib.models.IModelCustom;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class ModelHoverHarness extends ModelBiped
/*    */ {
/* 22 */   private static final ResourceLocation HARNESS = new ResourceLocation("thaumcraft", "models/obj/hoverharness.obj");
/*    */   
/* 24 */   private static final ResourceLocation HARNESSTEX = new ResourceLocation("thaumcraft", "textures/models/armor/hoverharness2.png");
/*    */   
/* 26 */   private static final ResourceLocation RING = new ResourceLocation("thaumcraft", "textures/models/armor/lightningring.png");
/*    */   
/*    */   private IModelCustom modelBack;
/*    */   
/*    */   public ModelHoverHarness()
/*    */   {
/* 32 */     this.field_78115_e = new ModelRenderer(this, 16, 16);
/* 33 */     this.field_78115_e.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.6F);
/* 34 */     this.modelBack = AdvancedModelLoader.loadModel(HARNESS);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*    */   {
/* 42 */     GL11.glPushMatrix();
/* 43 */     GL11.glPushMatrix();
/* 44 */     if ((entity != null) && (entity.func_70093_af())) {
/* 45 */       GL11.glRotatef(28.64789F, 1.0F, 0.0F, 0.0F);
/*    */     }
/* 47 */     this.field_78115_e.func_78785_a(par7);
/* 48 */     GL11.glPopMatrix();
/*    */     
/* 50 */     GL11.glPushMatrix();
/* 51 */     GL11.glDisable(2896);
/* 52 */     GL11.glScalef(0.1F, 0.1F, 0.1F);
/* 53 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 54 */     if ((entity != null) && (entity.func_70093_af())) {
/* 55 */       GL11.glRotatef(28.64789F, 1.0F, 0.0F, 0.0F);
/*    */     }
/* 57 */     GL11.glTranslatef(0.0F, 0.33F, -3.7F);
/*    */     
/* 59 */     FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(HARNESSTEX);
/* 60 */     this.modelBack.renderAll();
/* 61 */     GL11.glEnable(2896);
/* 62 */     GL11.glPopMatrix();
/*    */     
/* 64 */     if ((entity != null) && ((entity instanceof EntityPlayer)) && (!GL11.glIsEnabled(3042)) && (GL11.glGetInteger(2976) == 5888) && (((EntityPlayer)entity).field_71071_by.func_70440_f(2).func_77942_o()) && (((EntityPlayer)entity).field_71071_by.func_70440_f(2).func_77978_p().func_74764_b("hover")) && (((EntityPlayer)entity).field_71071_by.func_70440_f(2).func_77978_p().func_74771_c("hover") == 1))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 70 */       GL11.glPushMatrix();
/* 71 */       float mod = 0.0F;
/* 72 */       if (entity.func_70093_af()) {
/* 73 */         GL11.glRotatef(28.64789F, 1.0F, 0.0F, 0.0F);
/* 74 */         GL11.glTranslatef(0.0F, 0.075F, -0.05F);
/* 75 */         mod = 0.075F;
/*    */       }
/*    */       
/* 78 */       int frame = entity.field_70173_aa % 16;
/* 79 */       int frame2 = (entity.field_70173_aa + 8) % 16;
/* 80 */       GL11.glTranslatef(0.0F, 0.2F, 0.55F);
/* 81 */       GL11.glPushMatrix();
/* 82 */       UtilsFX.renderQuadCentered(RING, 16, 1, frame, 2.5F, 1.0F, 1.0F, 1.0F, 230, 1, 1.0F);
/* 83 */       GL11.glPopMatrix();
/* 84 */       GL11.glPushMatrix();
/* 85 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 86 */       GL11.glTranslatef(0.0F, 0.0F, 0.03F);
/* 87 */       UtilsFX.renderQuadCentered(RING, 16, 1, frame2, 1.5F, 1.0F, 0.5F, 1.0F, 230, 1, 1.0F);
/* 88 */       GL11.glPopMatrix();
/* 89 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 92 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/models/gear/ModelHoverHarness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */