/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ScanItem implements IScanThing
/*    */ {
/*    */   String research;
/*    */   ItemStack stack;
/*    */   
/*    */   public ScanItem(String research, ItemStack stack)
/*    */   {
/* 14 */     this.research = research;
/* 15 */     this.stack = stack;
/*    */   }
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj)
/*    */   {
/* 20 */     if (obj == null) { return false;
/*    */     }
/* 22 */     ItemStack is = null;
/*    */     
/* 24 */     if ((obj instanceof ItemStack))
/* 25 */       is = (ItemStack)obj;
/* 26 */     if (((obj instanceof EntityItem)) && (((EntityItem)obj).func_92059_d() != null)) {
/* 27 */       is = ((EntityItem)obj).func_92059_d();
/*    */     }
/* 29 */     return (is != null) && (thaumcraft.api.ThaumcraftApiHelper.areItemStacksEqualForCrafting(is, this.stack));
/*    */   }
/*    */   
/*    */   public String getResearchKey()
/*    */   {
/* 34 */     return this.research;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/research/ScanItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */