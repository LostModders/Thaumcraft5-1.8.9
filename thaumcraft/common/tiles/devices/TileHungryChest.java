/*    */ package thaumcraft.common.tiles.devices;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntityChest;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.devices.BlockHungryChest;
/*    */ 
/*    */ 
/*    */ public class TileHungryChest
/*    */   extends TileEntityChest
/*    */ {
/*    */   public boolean func_145842_c(int par1, int par2)
/*    */   {
/* 15 */     if (par1 == 2)
/*    */     {
/* 17 */       if (this.field_145989_m < par2 / 10.0F) this.field_145989_m = (par2 / 10.0F);
/* 18 */       return true;
/*    */     }
/* 20 */     return super.func_145842_c(par1, par2);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_145979_i()
/*    */   {
/* 26 */     if (!this.field_145984_a)
/*    */     {
/* 28 */       this.field_145984_a = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean canRenderBreaking()
/*    */   {
/* 34 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_174886_c(EntityPlayer player)
/*    */   {
/* 40 */     if ((!player.func_175149_v()) && ((func_145838_q() instanceof BlockHungryChest)))
/*    */     {
/* 42 */       this.field_145987_o -= 1;
/* 43 */       this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 1, this.field_145987_o);
/* 44 */       this.field_145850_b.func_175685_c(this.field_174879_c, func_145838_q());
/* 45 */       this.field_145850_b.func_175685_c(this.field_174879_c.func_177977_b(), func_145838_q());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileHungryChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */