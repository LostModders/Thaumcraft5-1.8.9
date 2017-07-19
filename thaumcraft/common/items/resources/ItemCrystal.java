/*    */ package thaumcraft.common.items.resources;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*    */ 
/*    */ public class ItemCrystal
/*    */   extends ItemGenericEssentiaContainer
/*    */ {
/*    */   public ItemCrystal()
/*    */   {
/* 21 */     super(1);
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 27 */     return par1ItemStack.func_77952_i() == 1 ? super.func_77658_a() + ".balanced" : super.func_77667_c(par1ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 35 */     for (Aspect tag : Aspect.aspects.values()) {
/* 36 */       ItemStack i = new ItemStack(this);
/* 37 */       setAspects(i, new AspectList().add(tag, this.base));
/* 38 */       par3List.add(i);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack stack, int par2)
/*    */   {
/* 52 */     if (stack.func_77952_i() == 1) {
/* 53 */       int t = (int)(System.currentTimeMillis() / 100L % 1000L);
/* 54 */       float r = MathHelper.func_76126_a(t / 2.0F) * 0.1F + 0.9F;
/* 55 */       float g = MathHelper.func_76126_a(t / 3.0F) * 0.1F + 0.9F;
/* 56 */       float b = MathHelper.func_76126_a(t / 4.0F) * 0.1F + 0.9F;
/* 57 */       return new Color(r, g, b).getRGB();
/*    */     }
/* 59 */     if (getAspects(stack) != null) {
/* 60 */       return getAspects(stack).getAspects()[0].getColor();
/*    */     }
/* 62 */     return 16777215;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/resources/ItemCrystal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */