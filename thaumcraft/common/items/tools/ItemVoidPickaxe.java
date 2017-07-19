/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.items.IRepairable;
/*    */ import thaumcraft.api.items.IWarpingGear;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemVoidPickaxe extends ItemPickaxe implements IRepairable, IWarpingGear
/*    */ {
/*    */   public ItemVoidPickaxe(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 24 */     super(enumtoolmaterial);
/* 25 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public Set<String> getToolClasses(ItemStack stack)
/*    */   {
/* 30 */     return ImmutableSet.of("pickaxe");
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 36 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 1)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*    */   {
/* 43 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 45 */     if ((stack.func_77951_h()) && (entity != null) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/* 46 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/*    */   {
/* 52 */     if ((!player.field_70170_p.field_72995_K) && ((entity instanceof EntityLivingBase)) && (
/* 53 */       (!(entity instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())))
/*    */     {
/*    */ 
/* 56 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76437_t.func_76396_c(), 80));
/*    */     }
/*    */     
/* 59 */     return super.onLeftClickEntity(stack, player, entity);
/*    */   }
/*    */   
/*    */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 64 */     return 1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemVoidPickaxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */