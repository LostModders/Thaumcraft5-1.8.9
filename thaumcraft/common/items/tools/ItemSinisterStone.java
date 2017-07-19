/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.internal.WorldCoordinates;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemSinisterStone extends Item
/*    */ {
/*    */   public ItemSinisterStone()
/*    */   {
/* 24 */     func_77625_d(1);
/* 25 */     func_77627_a(true);
/* 26 */     func_77656_e(0);
/* 27 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 30 */   public static ConcurrentHashMap<WorldCoordinates, Long> eldritchPortals = new ConcurrentHashMap();
/*    */   
/* 32 */   private boolean active = false;
/*    */   
/*    */   public void func_77663_a(ItemStack p_77663_1_, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*    */   {
/* 36 */     if (world.field_72995_K) {
/* 37 */       this.active = false;
/* 38 */       for (WorldCoordinates wc : eldritchPortals.keySet()) {
/* 39 */         if ((wc.dim == world.field_73011_w.func_177502_q()) && 
/* 40 */           (thaumcraft.common.lib.utils.EntityUtils.isVisibleTo(0.66F, entity, wc.pos.func_177958_n() + 0.5D, wc.pos.func_177956_o() + 0.5D, wc.pos.func_177952_p() + 0.5D, 256.0F))) {
/* 41 */           this.active = true;
/* 42 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
/*    */   {
/* 51 */     return this.active ? new ModelResourceLocation("Thaumcraft:sinister_stone_on", "inventory") : super.getModel(stack, player, useRemaining);
/*    */   }
/*    */   
/*    */ 
/*    */   private double directionToPoint(double x1, double z1, double x2, double z2)
/*    */   {
/* 57 */     double dx = x1 - x2;
/* 58 */     double dz = z1 - z2;
/* 59 */     return Math.atan2(dz, dx) * 180.0D / 3.141592653589793D;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 66 */     par3List.add(new ItemStack(this, 1, 0));
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack stack)
/*    */   {
/* 72 */     return EnumRarity.RARE;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/tools/ItemSinisterStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */