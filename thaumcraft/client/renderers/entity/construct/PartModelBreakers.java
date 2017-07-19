/*    */ package thaumcraft.client.renderers.entity.construct;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel.EnumLimbSide;
/*    */ 
/*    */ public class PartModelBreakers extends thaumcraft.api.golems.parts.PartModel
/*    */ {
/*    */   public PartModelBreakers(ResourceLocation objModel, ResourceLocation objTexture, thaumcraft.api.golems.parts.PartModel.EnumAttachPoint attachPoint)
/*    */   {
/* 13 */     super(objModel, objTexture, attachPoint);
/*    */   }
/*    */   
/*    */   public void preRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side)
/*    */   {
/* 18 */     if (partName.equals("grinder")) {
/* 19 */       float lastSpeed = 0.0F;
/* 20 */       float lastRot = 0.0F;
/* 21 */       if (this.ani.containsKey(Integer.valueOf(golem.getGolemEntity().func_145782_y()))) {
/* 22 */         lastSpeed = ((Float[])this.ani.get(Integer.valueOf(golem.getGolemEntity().func_145782_y())))[0].floatValue();
/* 23 */         lastRot = ((Float[])this.ani.get(Integer.valueOf(golem.getGolemEntity().func_145782_y())))[1].floatValue();
/*    */       }
/* 25 */       float f = Math.max(lastSpeed, golem.getGolemEntity().func_70678_g(partialTicks) * 20.0F);
/* 26 */       float rot = lastRot + f;
/* 27 */       lastSpeed = f * 0.99F;
/* 28 */       this.ani.put(Integer.valueOf(golem.getGolemEntity().func_145782_y()), new Float[] { Float.valueOf(lastSpeed), Float.valueOf(rot) });
/* 29 */       net.minecraft.client.renderer.GlStateManager.func_179137_b(0.0D, -0.34D, 0.0D);
/* 30 */       net.minecraft.client.renderer.GlStateManager.func_179114_b((golem.getGolemEntity().field_70173_aa + partialTicks) / 2.0F + rot + (side == PartModel.EnumLimbSide.LEFT ? 22 : 0), side == PartModel.EnumLimbSide.LEFT ? -1.0F : 1.0F, 0.0F, 0.0F);
/*    */     }
/*    */   }
/*    */   
/* 34 */   private HashMap<Integer, Float[]> ani = new HashMap();
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/renderers/entity/construct/PartModelBreakers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */