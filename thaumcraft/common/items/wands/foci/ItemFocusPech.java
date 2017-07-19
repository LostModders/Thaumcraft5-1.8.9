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
/*    */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*    */ 
/*    */ public class ItemFocusPech extends ItemFocusBasic
/*    */ {
/*    */   public ItemFocusPech()
/*    */   {
/* 18 */     super("pech", 2267460);
/*    */   }
/*    */   
/*    */   public boolean canBePlacedInTurret()
/*    */   {
/* 23 */     return true;
/*    */   }
/*    */   
/*    */   public float getTurretCorrection(ItemStack focusstack)
/*    */   {
/* 28 */     return 12.0F;
/*    */   }
/*    */   
/*    */   public int getActivationCooldown(ItemStack focusstack)
/*    */   {
/* 33 */     return 250;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onFocusActivation(ItemStack itemstack, World world, EntityLivingBase p, MovingObjectPosition movingobjectposition, int count)
/*    */   {
/* 39 */     IWand wand = (IWand)itemstack.func_77973_b();
/* 40 */     EntityPechBlast blast = new EntityPechBlast(world, p, wand.getFocusPotency(itemstack), wand.getFocusExtend(itemstack), isUpgradedWith(wand.getFocusStack(itemstack), nightshade));
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 45 */     if (!world.field_72995_K)
/*    */     {
/* 47 */       world.func_72838_d(blast);
/* 48 */       world.func_72956_a(blast, "thaumcraft:ice", 0.4F, 1.0F + world.field_73012_v.nextFloat() * 0.1F);
/*    */     }
/*    */     
/* 51 */     p.func_71038_i();
/* 52 */     return true;
/*    */   }
/*    */   
/*    */ 
/* 56 */   private static final AspectList cost = new AspectList().add(Aspect.EARTH, 2).add(Aspect.ENTROPY, 2).add(Aspect.WATER, 2);
/* 57 */   private static final AspectList costAll = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1).add(Aspect.WATER, 1);
/*    */   
/*    */   public AspectList getVisCost(ItemStack itemstack)
/*    */   {
/* 61 */     return isUpgradedWith(itemstack, nightshade) ? costAll : cost;
/*    */   }
/*    */   
/*    */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*    */   {
/* 66 */     switch (rank) {
/* 67 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 68 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.extend };
/* 69 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 70 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.extend };
/* 71 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, nightshade };
/*    */     }
/* 73 */     return null;
/*    */   }
/*    */   
/* 76 */   public static FocusUpgradeType nightshade = new FocusUpgradeType(new net.minecraft.util.ResourceLocation("thaumcraft", "textures/foci/nightshade.png"), "focus.upgrade.nightshade.name", "focus.upgrade.nightshade.text", new AspectList().add(Aspect.LIFE, 1).add(Aspect.FLUX, 1).add(Aspect.ENERGY, 1));
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */