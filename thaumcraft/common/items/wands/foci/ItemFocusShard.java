/*    */ package thaumcraft.common.items.wands.foci;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.wands.FocusUpgradeType;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.common.entities.projectile.EntityHomingShard;
/*    */ import thaumcraft.common.lib.utils.EntityUtils;
/*    */ 
/*    */ public class ItemFocusShard extends ItemFocusBasic
/*    */ {
/*    */   public ItemFocusShard()
/*    */   {
/* 20 */     super("shard", 10037693);
/*    */   }
/*    */   
/*    */   public boolean canBePlacedInTurret()
/*    */   {
/* 25 */     return true;
/*    */   }
/*    */   
/*    */   public int getActivationCooldown(ItemStack focusstack)
/*    */   {
/* 30 */     return 300;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onFocusActivation(ItemStack itemstack, World world, EntityLivingBase p, MovingObjectPosition movingobjectposition, int count)
/*    */   {
/* 36 */     IWand wand = (IWand)itemstack.func_77973_b();
/*    */     
/* 38 */     net.minecraft.entity.Entity pointedEntity = EntityUtils.getPointedEntity(p.field_70170_p, p, 0.0D, 32.0D, 1.1F);
/* 39 */     if ((pointedEntity != null) && ((pointedEntity instanceof EntityLivingBase))) {
/* 40 */       if (!world.field_72995_K)
/*    */       {
/* 42 */         EntityHomingShard blast = new EntityHomingShard(world, p, (EntityLivingBase)pointedEntity, wand.getFocusPotency(itemstack), isUpgradedWith(wand.getFocusStack(itemstack), persistant));
/* 43 */         world.func_72838_d(blast);
/* 44 */         world.func_72956_a(blast, "thaumcraft:scan", 0.3F, 1.1F + world.field_73012_v.nextFloat() * 0.1F);
/*    */       }
/* 46 */       p.func_71038_i();
/* 47 */       return true;
/*    */     }
/* 49 */     return false;
/*    */   }
/*    */   
/*    */ 
/* 53 */   private static final AspectList cost = new AspectList().add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1).add(Aspect.AIR, 1);
/* 54 */   private static final AspectList costP = new AspectList().add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1).add(Aspect.WATER, 1).add(Aspect.AIR, 1);
/*    */   
/*    */   public AspectList getVisCost(ItemStack itemstack)
/*    */   {
/* 58 */     return isUpgradedWith(itemstack, persistant) ? costP : cost;
/*    */   }
/*    */   
/*    */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*    */   {
/* 63 */     switch (rank) {
/* 64 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 65 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 66 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 67 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 68 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, persistant };
/*    */     }
/* 70 */     return null;
/*    */   }
/*    */   
/* 73 */   public static FocusUpgradeType persistant = new FocusUpgradeType(new net.minecraft.util.ResourceLocation("thaumcraft", "textures/foci/persistant.png"), "focus.upgrade.persistant.name", "focus.upgrade.persistant.text", new AspectList().add(Aspect.PROTECT, 1).add(Aspect.AVERSION, 1).add(Aspect.ENERGY, 1));
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */