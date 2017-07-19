/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.items.IRepairable;
/*    */ import thaumcraft.api.items.IWarpingGear;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemVoidSword extends ItemSword implements IRepairable, IWarpingGear
/*    */ {
/*    */   public ItemVoidSword(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 24 */     super(enumtoolmaterial);
/* 25 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 32 */     return par2ItemStack.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 1)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*    */   {
/* 39 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 41 */     if ((stack.func_77951_h()) && (entity != null) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/* 42 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean func_77644_a(ItemStack is, EntityLivingBase target, EntityLivingBase hitter)
/*    */   {
/* 48 */     if ((!target.field_70170_p.field_72995_K) && (
/* 49 */       (!(target instanceof EntityPlayer)) || (!(hitter instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())))
/*    */     {
/*    */       try
/*    */       {
/* 53 */         target.func_70690_d(new PotionEffect(Potion.field_76437_t.func_76396_c(), 60));
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/* 57 */     return super.func_77644_a(is, target, hitter);
/*    */   }
/*    */   
/*    */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 62 */     return 1;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 67 */     list.add(EnumChatFormatting.GOLD + net.minecraft.util.StatCollector.func_74838_a("enchantment.special.sapless"));
/* 68 */     super.func_77624_a(stack, player, list, par4);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemVoidSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */