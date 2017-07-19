/*    */ package thaumcraft.common.tiles.crafting;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.items.IRechargable;
/*    */ import thaumcraft.common.container.InventoryArcaneWorkbench;
/*    */ 
/*    */ public class TileArcaneWorkbenchCharger extends thaumcraft.api.blocks.TileThaumcraft implements net.minecraft.util.ITickable
/*    */ {
/* 12 */   int counter = 0;
/*    */   
/*    */   public void func_73660_a()
/*    */   {
/* 16 */     if ((!this.field_145850_b.field_72995_K) && (this.counter++ % 10 == 0)) {
/* 17 */       net.minecraft.tileentity.TileEntity te = this.field_145850_b.func_175625_s(this.field_174879_c.func_177977_b());
/* 18 */       if ((te != null) && ((te instanceof TileArcaneWorkbench))) {
/* 19 */         TileArcaneWorkbench tm = (TileArcaneWorkbench)te;
/* 20 */         if ((tm.inventory.func_70301_a(10) != null) && ((tm.inventory.func_70301_a(10).func_77973_b() instanceof IRechargable)))
/*    */         {
/* 22 */           IRechargable wand = (IRechargable)tm.inventory.func_70301_a(10).func_77973_b();
/*    */           
/* 24 */           Aspect aspect = wand.handleRecharge(func_145831_w(), tm.inventory.func_70301_a(10), this.field_174879_c, null, 5);
/* 25 */           if (aspect != null) {
/* 26 */             this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 5, aspect.getColor());
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_145842_c(int i, int j)
/*    */   {
/* 36 */     if (i == 5)
/*    */     {
/* 38 */       if (this.field_145850_b.field_72995_K) {
/* 39 */         thaumcraft.common.Thaumcraft.proxy.getFX().visSparkle(this.field_174879_c.func_177958_n() + func_145831_w().field_73012_v.nextInt(3) - func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177956_o() + func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177952_p() + func_145831_w().field_73012_v.nextInt(3) - func_145831_w().field_73012_v.nextInt(3), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), j);
/*    */       }
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 45 */       return true;
/*    */     }
/*    */     
/* 48 */     return super.func_145842_c(i, j);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/crafting/TileArcaneWorkbenchCharger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */