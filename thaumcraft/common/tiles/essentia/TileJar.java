/*    */ package thaumcraft.common.tiles.essentia;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.blocks.TileThaumcraft;
/*    */ 
/*    */ public class TileJar
/*    */   extends TileThaumcraft
/*    */   implements ITickable
/*    */ {
/* 15 */   protected static Random rand = new Random();
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 20 */     return AxisAlignedBB.func_178781_a(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), func_174877_v().func_177958_n() + 1, func_174877_v().func_177956_o() + 1, func_174877_v().func_177952_p() + 1);
/*    */   }
/*    */   
/*    */   public void func_73660_a() {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/tiles/essentia/TileJar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */