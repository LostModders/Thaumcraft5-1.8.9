/*    */ package thaumcraft.common.items.resources;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.research.ResearchHelper;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPrimalCharm
/*    */   extends Item
/*    */ {
/*    */   public ItemPrimalCharm()
/*    */   {
/* 25 */     func_77625_d(1);
/* 26 */     func_77627_a(true);
/* 27 */     func_77656_e(0);
/*    */   }
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5)
/*    */   {
/* 32 */     super.func_77663_a(stack, world, entity, par4, par5);
/*    */     
/* 34 */     if (!entity.field_70170_p.field_72995_K) {
/* 35 */       int r = world.field_73012_v.nextInt(20000);
/* 36 */       if ((r == 42) && ((entity instanceof EntityPlayer)) && (!ResearchManager.isResearchComplete(((EntityPlayer)entity).func_70005_c_(), "FOCUSPRIMAL")) && (!ResearchManager.isResearchComplete(((EntityPlayer)entity).func_70005_c_(), "@FOCUSPRIMAL")))
/*    */       {
/*    */ 
/* 39 */         ((EntityPlayer)entity).func_145747_a(new ChatComponentTranslation("§5§o" + StatCollector.func_74838_a("tc.primalcharm.trigger"), new Object[0]));
/* 40 */         ResearchHelper.completeResearch((EntityPlayerMP)entity, "@FOCUSPRIMAL");
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 47 */     Random rand = new Random(player.func_145782_y() + player.field_70173_aa / 120);
/* 48 */     int r = rand.nextInt(100);
/* 49 */     if (r < 25) {
/* 50 */       list.add("§6" + StatCollector.func_74838_a(new StringBuilder().append("tc.primalcharm.").append(rand.nextInt(5)).toString()));
/*    */     }
/* 52 */     super.func_77624_a(stack, player, list, par4);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/resources/ItemPrimalCharm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */