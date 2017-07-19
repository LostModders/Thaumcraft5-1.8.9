/*    */ package thaumcraft.common.items.resources;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.blocks.ILabelable;
/*    */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*    */ import thaumcraft.common.items.IVariantItems;
/*    */ 
/*    */ public class ItemLabel
/*    */   extends ItemGenericEssentiaContainer implements IVariantItems
/*    */ {
/*    */   public ItemLabel()
/*    */   {
/* 27 */     super(1);
/*    */   }
/*    */   
/*    */   public String[] getVariantNames()
/*    */   {
/* 32 */     return new String[] { "blank", "filled" };
/*    */   }
/*    */   
/*    */   public int[] getVariantMeta()
/*    */   {
/* 37 */     return new int[] { 0, 1 };
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack stack, int layer)
/*    */   {
/* 44 */     if ((stack.func_77952_i() == 1) && (getAspects(stack) != null) && (layer == 1)) {
/* 45 */       return getAspects(stack).getAspects()[0].getColor();
/*    */     }
/* 47 */     return 16777215;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 53 */     par3List.add(new ItemStack(this, 1, 0));
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack stack)
/*    */   {
/* 59 */     return super.func_77658_a() + "." + getVariantNames()[stack.func_77952_i()];
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*    */   {
/* 66 */     if (world.field_72995_K) { return false;
/*    */     }
/* 68 */     IBlockState bs = world.func_180495_p(pos);
/* 69 */     if ((bs.func_177230_c() instanceof ILabelable)) {
/* 70 */       if (((ILabelable)bs.func_177230_c()).applyLabel(player, pos, side, stack)) {
/* 71 */         stack.field_77994_a -= 1;
/* 72 */         player.field_71069_bz.func_75142_b();
/*    */       }
/* 74 */       return true;
/*    */     }
/*    */     
/* 77 */     TileEntity te = world.func_175625_s(pos);
/* 78 */     if ((te instanceof ILabelable)) {
/* 79 */       if (((ILabelable)te).applyLabel(player, pos, side, stack)) {
/* 80 */         stack.field_77994_a -= 1;
/* 81 */         player.field_71069_bz.func_75142_b();
/*    */       }
/* 83 */       return true;
/*    */     }
/*    */     
/* 86 */     return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {}
/*    */   
/*    */   public void func_77622_d(ItemStack stack, World world, EntityPlayer player) {}
/*    */   
/*    */   public boolean ignoreContainedAspects()
/*    */   {
/* 96 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/resources/ItemLabel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */