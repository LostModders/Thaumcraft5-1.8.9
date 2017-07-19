/*    */ package thaumcraft.client;
/*    */ 
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraftforge.fml.client.IModGuiFactory;
/*    */ import net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
/*    */ import net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
/*    */ import thaumcraft.client.gui.ThaumcraftGuiConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThaumcraftGuiFactory
/*    */   implements IModGuiFactory
/*    */ {
/*    */   public void initialize(Minecraft minecraftInstance) {}
/*    */   
/*    */   public Class<? extends GuiScreen> mainConfigGuiClass()
/*    */   {
/* 20 */     return ThaumcraftGuiConfig.class;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories()
/*    */   {
/* 27 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element)
/*    */   {
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/client/ThaumcraftGuiFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */