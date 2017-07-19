/*    */ package thaumcraft.common.items.wands.foci;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.wands.FocusUpgradeType;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.common.entities.projectile.EntityGrapple;
/*    */ 
/*    */ public class ItemFocusGrapple extends ItemFocusBasic
/*    */ {
/*    */   public ItemFocusGrapple()
/*    */   {
/* 18 */     super("grapple", 1381887);
/*    */   }
/*    */   
/*    */   public boolean canBePlacedInTurret()
/*    */   {
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   public int getActivationCooldown(ItemStack focusstack)
/*    */   {
/* 28 */     return 250;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onFocusActivation(ItemStack itemstack, World world, EntityLivingBase p, MovingObjectPosition mop, int count)
/*    */   {
/* 34 */     IWand wand = (IWand)itemstack.func_77973_b();
/* 35 */     if (!world.field_72995_K)
/*    */     {
/* 37 */       EntityGrapple grapple = new EntityGrapple(world, p, wand.getFocusPotency(itemstack), isUpgradedWith(wand.getFocusStack(itemstack), sticky));
/*    */       
/* 39 */       world.func_72838_d(grapple);
/* 40 */       world.func_72956_a(grapple, "thaumcraft:ice", 0.3F, 0.8F + world.field_73012_v.nextFloat() * 0.1F);
/*    */     }
/*    */     
/* 43 */     p.func_71038_i();
/* 44 */     return true;
/*    */   }
/*    */   
/* 47 */   private static final AspectList cost = new AspectList().add(Aspect.WATER, 10).add(Aspect.AIR, 10);
/*    */   
/*    */   public AspectList getVisCost(ItemStack itemstack)
/*    */   {
/* 51 */     return cost;
/*    */   }
/*    */   
/*    */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*    */   {
/* 56 */     switch (rank) {
/* 57 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 58 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 59 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, sticky };
/* 60 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 61 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*    */     }
/* 63 */     return null;
/*    */   }
/*    */   
/* 66 */   public static FocusUpgradeType sticky = new FocusUpgradeType(new net.minecraft.util.ResourceLocation("thaumcraft", "textures/foci/sticky.png"), "focus.upgrade.sticky.name", "focus.upgrade.sticky.text", new AspectList().add(Aspect.TRAP, 1));
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusGrapple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */