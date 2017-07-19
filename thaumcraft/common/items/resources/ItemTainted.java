/*    */ package thaumcraft.common.items.resources;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.potions.PotionFluxTaint;
/*    */ import thaumcraft.common.items.ItemGenericVariants;
/*    */ import thaumcraft.common.lib.utils.InventoryUtils;
/*    */ 
/*    */ public class ItemTainted extends ItemGenericVariants
/*    */ {
/*    */   public ItemTainted()
/*    */   {
/* 19 */     super(new String[] { "slime", "tendril" });
/*    */   }
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5)
/*    */   {
/* 24 */     super.func_77663_a(stack, world, entity, par4, par5);
/*    */     
/* 26 */     if ((!entity.field_70170_p.field_72995_K) && ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase)entity).func_70662_br()) && (!((EntityLivingBase)entity).func_70644_a(PotionFluxTaint.instance)) && (world.field_73012_v.nextInt(4321) <= stack.field_77994_a))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/* 31 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(PotionFluxTaint.instance.func_76396_c(), 120, 0, false, true));
/*    */       
/*    */ 
/* 34 */       if ((entity instanceof EntityPlayer)) {
/* 35 */         String s = net.minecraft.util.StatCollector.func_74838_a("tc.taint_item_poison").replace("%s", "§5§o" + stack.func_82833_r() + "§r");
/* 36 */         ((EntityPlayer)entity).func_145747_a(new ChatComponentTranslation(s, new Object[0]));
/* 37 */         InventoryUtils.consumeInventoryItem((EntityPlayer)entity, stack.func_77973_b(), stack.func_77952_i());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/resources/ItemTainted.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */