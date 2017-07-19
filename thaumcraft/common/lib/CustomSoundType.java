/*    */ package thaumcraft.common.lib;
/*    */ 
/*    */ import net.minecraft.block.Block.SoundType;
/*    */ 
/*    */ public class CustomSoundType extends Block.SoundType
/*    */ {
/*    */   public CustomSoundType(String par1Str, float par2, float par3)
/*    */   {
/*  9 */     super(par1Str, par2, par3);
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_150495_a()
/*    */   {
/* 15 */     return "thaumcraft:" + this.field_150501_a;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_150498_e()
/*    */   {
/* 21 */     return func_150495_a();
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_150496_b()
/*    */   {
/* 27 */     return func_150495_a();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/CustomSoundType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */