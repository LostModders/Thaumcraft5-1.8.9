/*    */ package thaumcraft.common.items.wands.foci;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.wands.FocusUpgradeType;
/*    */ import thaumcraft.api.wands.IWand;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.common.entities.projectile.EntityPrimalOrb;
/*    */ 
/*    */ public class ItemFocusPrimal extends ItemFocusBasic
/*    */ {
/*    */   public ItemFocusPrimal()
/*    */   {
/* 20 */     super("primal", 10854849);
/*    */   }
/*    */   
/*    */   public boolean canBePlacedInTurret()
/*    */   {
/* 25 */     return true;
/*    */   }
/*    */   
/*    */   public int getActivationCooldown(ItemStack focusstack)
/*    */   {
/* 30 */     return 500;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onFocusActivation(ItemStack itemstack, World world, EntityLivingBase p, MovingObjectPosition mop, int count)
/*    */   {
/* 36 */     IWand wand = (IWand)itemstack.func_77973_b();
/* 37 */     EntityPrimalOrb shard = new EntityPrimalOrb(world, p, isUpgradedWith(wand.getFocusStack(itemstack), seeker));
/* 38 */     if (!world.field_72995_K)
/*    */     {
/* 40 */       world.func_72838_d(shard);
/* 41 */       world.func_72956_a(shard, "thaumcraft:ice", 0.3F, 0.8F + world.field_73012_v.nextFloat() * 0.1F);
/*    */     }
/*    */     
/* 44 */     p.func_71038_i();
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public AspectList getVisCost(ItemStack itemstack)
/*    */   {
/* 50 */     Random rand = new Random(System.currentTimeMillis() / 200L);
/* 51 */     AspectList cost = new AspectList().add(Aspect.WATER, 10 + rand.nextInt(5) * 2).add(Aspect.AIR, 10 + rand.nextInt(5) * 2).add(Aspect.EARTH, 10 + rand.nextInt(5) * 2).add(Aspect.FIRE, 10 + rand.nextInt(5) * 2).add(Aspect.ORDER, 10 + rand.nextInt(5) * 2).add(Aspect.ENTROPY, 10 + rand.nextInt(5) * 2);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 58 */     return cost;
/*    */   }
/*    */   
/*    */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*    */   {
/* 63 */     switch (rank) {
/* 64 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/* 65 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/* 66 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, seeker };
/* 67 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/* 68 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/*    */     }
/* 70 */     return null;
/*    */   }
/*    */   
/* 73 */   public static FocusUpgradeType seeker = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/seeker.png"), "focus.upgrade.seeker.name", "focus.upgrade.seeker.text", new AspectList().add(Aspect.SENSES, 1).add(Aspect.MIND, 1));
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusPrimal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */