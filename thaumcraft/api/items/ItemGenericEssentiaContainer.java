/*    */ package thaumcraft.api.items;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*    */ 
/*    */ public class ItemGenericEssentiaContainer extends Item implements IEssentiaContainerItem
/*    */ {
/*    */   public ItemGenericEssentiaContainer(int base)
/*    */   {
/* 23 */     this.base = base;
/* 24 */     func_77625_d(64);
/* 25 */     func_77627_a(true);
/* 26 */     func_77656_e(0);
/*    */   }
/*    */   
/* 29 */   protected int base = 1;
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 34 */     for (Aspect tag : Aspect.aspects.values()) {
/* 35 */       ItemStack i = new ItemStack(this);
/* 36 */       setAspects(i, new AspectList().add(tag, this.base));
/* 37 */       par3List.add(i);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack stack, int par2)
/*    */   {
/* 45 */     if (getAspects(stack) != null) {
/* 46 */       return getAspects(stack).getAspects()[0].getColor();
/*    */     }
/* 48 */     return 16777215;
/*    */   }
/*    */   
/*    */ 
/*    */   public AspectList getAspects(ItemStack itemstack)
/*    */   {
/* 54 */     if (itemstack.func_77942_o()) {
/* 55 */       AspectList aspects = new AspectList();
/* 56 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 57 */       return aspects.size() > 0 ? aspects : null;
/*    */     }
/* 59 */     return null;
/*    */   }
/*    */   
/*    */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*    */   {
/* 64 */     if (!itemstack.func_77942_o())
/* 65 */       itemstack.func_77982_d(new NBTTagCompound());
/* 66 */     aspects.writeToNBT(itemstack.func_77978_p());
/*    */   }
/*    */   
/*    */   public boolean ignoreContainedAspects() {
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
/* 74 */     if ((!world.field_72995_K) && (!stack.func_77942_o())) {
/* 75 */       Aspect[] displayAspects = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/* 76 */       setAspects(stack, new AspectList().add(displayAspects[world.field_73012_v.nextInt(displayAspects.length)], this.base));
/*    */     }
/* 78 */     super.func_77663_a(stack, world, entity, par4, par5);
/*    */   }
/*    */   
/*    */   public void func_77622_d(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 83 */     if ((!world.field_72995_K) && (!stack.func_77942_o())) {
/* 84 */       Aspect[] displayAspects = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/* 85 */       setAspects(stack, new AspectList().add(displayAspects[world.field_73012_v.nextInt(displayAspects.length)], this.base));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/api/items/ItemGenericEssentiaContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */