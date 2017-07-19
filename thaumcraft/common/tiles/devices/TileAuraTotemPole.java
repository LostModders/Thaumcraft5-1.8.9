/*    */ package thaumcraft.common.tiles.devices;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileAuraTotemPole
/*    */   extends TileThaumcraftInventory
/*    */ {
/*    */   public boolean func_94041_b(int par1, ItemStack stack)
/*    */   {
/* 20 */     return (stack != null) && (stack.func_77973_b() == ItemsTC.shard);
/*    */   }
/*    */   
/*    */   public ItemStack func_70298_a(int par1, int par2)
/*    */   {
/* 25 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ItemStack func_70301_a(int par1)
/*    */   {
/* 32 */     TileEntity tile = this.field_145850_b.func_175625_s(func_174877_v().func_177984_a());
/* 33 */     if ((tile != null) && (((tile instanceof TileAuraTotem)) || ((tile instanceof TileAuraTotemPole)))) {
/* 34 */       return ((TileThaumcraftInventory)tile).func_70301_a(par1);
/*    */     }
/* 36 */     return null;
/*    */   }
/*    */   
/*    */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*    */   {
/* 41 */     TileEntity tile = this.field_145850_b.func_175625_s(func_174877_v().func_177984_a());
/* 42 */     if ((tile != null) && (((tile instanceof TileAuraTotem)) || ((tile instanceof TileAuraTotemPole)))) {
/* 43 */       ((TileThaumcraftInventory)tile).func_70299_a(par1, par2ItemStack);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean func_180462_a(int par1, ItemStack par2ItemStack, EnumFacing par3)
/*    */   {
/* 49 */     TileEntity tile = this.field_145850_b.func_175625_s(func_174877_v().func_177984_a());
/* 50 */     if ((tile != null) && (((tile instanceof TileAuraTotem)) || ((tile instanceof TileAuraTotemPole)))) {
/* 51 */       return ((TileThaumcraftInventory)tile).func_180462_a(par1, par2ItemStack, par3);
/*    */     }
/* 53 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_180461_b(int par1, ItemStack par2ItemStack, EnumFacing par3)
/*    */   {
/* 59 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/devices/TileAuraTotemPole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */