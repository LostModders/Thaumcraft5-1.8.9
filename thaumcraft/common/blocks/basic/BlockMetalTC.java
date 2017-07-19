/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.properties.PropertyEnum;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.IStringSerializable;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ 
/*    */ public class BlockMetalTC extends BlockTC
/*    */ {
/* 17 */   public static final PropertyEnum VARIANT = PropertyEnum.func_177709_a("variant", MetalType.class);
/*    */   
/*    */   public BlockMetalTC()
/*    */   {
/* 21 */     super(Material.field_151573_f);
/* 22 */     func_149647_a(Thaumcraft.tabTC);
/* 23 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, MetalType.ALCHEMICAL));
/* 24 */     func_149711_c(4.0F);
/* 25 */     func_149752_b(10.0F);
/* 26 */     func_149672_a(Block.field_149777_j);
/*    */   }
/*    */   
/*    */ 
/*    */   public IBlockState func_176203_a(int meta)
/*    */   {
/* 32 */     return meta < MetalType.values().length ? func_176223_P().func_177226_a(VARIANT, MetalType.values()[meta]) : func_176223_P();
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_176201_c(IBlockState state)
/*    */   {
/* 38 */     int meta = ((MetalType)state.func_177229_b(VARIANT)).ordinal();
/*    */     
/* 40 */     return meta;
/*    */   }
/*    */   
/*    */ 
/*    */   protected BlockState func_180661_e()
/*    */   {
/* 46 */     return new BlockState(this, new IProperty[] { VARIANT });
/*    */   }
/*    */   
/*    */ 
/*    */   public IProperty[] getProperties()
/*    */   {
/* 52 */     return new IProperty[] { VARIANT };
/*    */   }
/*    */   
/*    */ 
/*    */   public String getStateName(IBlockState state, boolean fullName)
/*    */   {
/* 58 */     MetalType type = (MetalType)state.func_177229_b(VARIANT);
/*    */     
/* 60 */     return type.func_176610_l() + (fullName ? "_metal" : "");
/*    */   }
/*    */   
/*    */   public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
/*    */   {
/* 65 */     return true;
/*    */   }
/*    */   
/*    */   public static enum MetalType implements IStringSerializable
/*    */   {
/* 70 */     THAUMIUM, 
/* 71 */     VOID, 
/* 72 */     ALCHEMICAL, 
/* 73 */     ADVANCED_ALCHEMICAL, 
/* 74 */     BRASS;
/*    */     
/*    */     private MetalType() {}
/*    */     
/*    */     public String func_176610_l() {
/* 79 */       return name().toLowerCase();
/*    */     }
/*    */     
/*    */ 
/*    */     public String toString()
/*    */     {
/* 85 */       return func_176610_l();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/basic/BlockMetalTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */