/*    */ package thaumcraft.common.items.wands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ import thaumcraft.api.wands.IWandRodOnUpdate;
/*    */ 
/*    */ public class WandRodPrimalOnUpdate implements IWandRodOnUpdate
/*    */ {
/*    */   Aspect aspect;
/*    */   ArrayList<Aspect> primals;
/*    */   
/*    */   public WandRodPrimalOnUpdate(Aspect aspect)
/*    */   {
/* 17 */     this.aspect = aspect;
/*    */   }
/*    */   
/*    */   public WandRodPrimalOnUpdate() {
/* 21 */     this.aspect = null;
/* 22 */     this.primals = Aspect.getPrimalAspects();
/*    */   }
/*    */   
/*    */   public void onUpdate(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 27 */     if (this.aspect != null) {
/* 28 */       if ((player.field_70173_aa % 5 == 0) && 
/* 29 */         (((IWand)itemstack.func_77973_b()).getVis(itemstack, this.aspect) < ((IWand)itemstack.func_77973_b()).getMaxVis(itemstack) / 2)) {
/* 30 */         ((IWand)itemstack.func_77973_b()).addVis(itemstack, this.aspect, 1, true);
/*    */       }
/*    */       
/*    */     }
/* 34 */     else if (player.field_70173_aa % 5 == 0) {
/* 35 */       ArrayList<Aspect> q = new ArrayList();
/* 36 */       for (Aspect as : this.primals) {
/* 37 */         if (((IWand)itemstack.func_77973_b()).getVis(itemstack, as) < ((IWand)itemstack.func_77973_b()).getMaxVis(itemstack) / 2) {
/* 38 */           q.add(as);
/*    */         }
/*    */       }
/* 41 */       if (q.size() > 0) {
/* 42 */         ((IWand)itemstack.func_77973_b()).addVis(itemstack, (Aspect)q.get(player.field_70170_p.field_73012_v.nextInt(q.size())), 1, true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/WandRodPrimalOnUpdate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */