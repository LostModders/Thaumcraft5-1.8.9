/*   */ package thaumcraft.common.blocks;
/*   */ 
/*   */ import com.google.common.base.Predicate;
/*   */ import net.minecraft.block.properties.PropertyDirection;
/*   */ import net.minecraft.util.EnumFacing;
/*   */ 
/*   */ public abstract interface IBlockFacing
/*   */ {
/* 9 */   public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", new Predicate()
/*   */   {
/*   */     public boolean apply(EnumFacing facing)
/*   */     {
/* = */       return true;
/*   */     }
/*   */     
/*   */     public boolean apply(Object p_apply_1_) {
/* A */       return apply((EnumFacing)p_apply_1_);
/*   */     }
/* 9 */   });
/*   */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/IBlockFacing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */