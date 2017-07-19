/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.properties.PropertyEnum;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.IStringSerializable;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ 
/*    */ public class BlockPlanksTC extends BlockTC
/*    */ {
/* 18 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", PlankType.class);
/*    */   
/*    */   public BlockPlanksTC()
/*    */   {
/* 22 */     super(Material.field_151575_d);
/* 23 */     func_149647_a(Thaumcraft.tabTC);
/* 24 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, PlankType.GREATWOOD));
/*    */     
/* 26 */     setHarvestLevel("axe", 0);
/*    */     
/* 28 */     func_149711_c(2.0F);
/* 29 */     func_149672_a(Block.field_149766_f);
/*    */   }
/*    */   
/*    */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/* 33 */     return 20;
/*    */   }
/*    */   
/* 36 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 5; }
/*    */   
/*    */ 
/*    */   public IBlockState func_176203_a(int meta)
/*    */   {
/* 41 */     return meta < PlankType.values().length ? func_176223_P().func_177226_a(VARIANT, PlankType.values()[meta]) : func_176223_P();
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_176201_c(IBlockState state)
/*    */   {
/* 47 */     int meta = ((PlankType)state.func_177229_b(VARIANT)).ordinal();
/*    */     
/* 49 */     return meta;
/*    */   }
/*    */   
/*    */ 
/*    */   protected BlockState func_180661_e()
/*    */   {
/* 55 */     return new BlockState(this, new IProperty[] { VARIANT });
/*    */   }
/*    */   
/*    */ 
/*    */   public IProperty[] getProperties()
/*    */   {
/* 61 */     return new IProperty[] { VARIANT };
/*    */   }
/*    */   
/*    */ 
/*    */   public String getStateName(IBlockState state, boolean fullName)
/*    */   {
/* 67 */     PlankType type = (PlankType)state.func_177229_b(VARIANT);
/*    */     
/* 69 */     return type.func_176610_l() + (fullName ? "_plank" : "");
/*    */   }
/*    */   
/*    */   public static enum PlankType
/*    */     implements IStringSerializable
/*    */   {
/* 75 */     GREATWOOD, 
/* 76 */     SILVERWOOD;
/*    */     
/*    */     private PlankType() {}
/*    */     
/*    */     public String func_176610_l() {
/* 81 */       return name().toLowerCase();
/*    */     }
/*    */     
/*    */ 
/*    */     public String toString()
/*    */     {
/* 87 */       return func_176610_l();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockPlanksTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */