/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ 
/*    */ 
/*    */ public class BlockTC
/*    */   extends Block
/*    */ {
/*    */   public ImmutableSet<IBlockState> states;
/*    */   
/*    */   public BlockTC(Material material)
/*    */   {
/* 25 */     super(material);
/* 26 */     this.states = BlockStateUtils.getValidStatesForProperties(func_176223_P(), getProperties());
/* 27 */     func_149647_a(Thaumcraft.tabTC);
/* 28 */     func_149752_b(2.0F);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item item, CreativeTabs tab, List list)
/*    */   {
/* 34 */     if (hasProperties()) {
/* 35 */       for (IBlockState state : this.states) {
/* 36 */         list.add(new ItemStack(item, 1, func_176201_c(state)));
/*    */       }
/*    */     } else {
/* 39 */       list.add(new ItemStack(item, 1, 0));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_180651_a(IBlockState state)
/*    */   {
/* 46 */     return func_176201_c(state);
/*    */   }
/*    */   
/*    */   public IProperty[] getProperties() {
/* 50 */     return null;
/*    */   }
/*    */   
/*    */   public boolean hasProperties() {
/* 54 */     return getProperties() != null;
/*    */   }
/*    */   
/*    */   public String getStateName(IBlockState state, boolean fullName) {
/* 58 */     String unlocalizedName = state.func_177230_c().func_149739_a();
/* 59 */     return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
/*    */   }
/*    */   
/*    */   public boolean defineVariantsForItemBlock() {
/* 63 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/blocks/BlockTC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */