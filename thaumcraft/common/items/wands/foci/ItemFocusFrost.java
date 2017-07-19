/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.entities.projectile.EntityFrostShard;
/*     */ 
/*     */ public class ItemFocusFrost extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusFrost()
/*     */   {
/*  18 */     super("frost", 5204428);
/*     */   }
/*     */   
/*     */   public boolean canBePlacedInTurret()
/*     */   {
/*  23 */     return true;
/*     */   }
/*     */   
/*     */   public float getTurretCorrection(ItemStack focusstack)
/*     */   {
/*  28 */     return isUpgradedWith(focusstack, scattershot) ? 1.0F : 15.0F;
/*     */   }
/*     */   
/*     */   public float getTurretRange(ItemStack focusstack)
/*     */   {
/*  33 */     return isUpgradedWith(focusstack, scattershot) ? 20.0F : super.getTurretRange(focusstack);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onFocusActivation(ItemStack wandstack, World world, EntityLivingBase p, net.minecraft.util.MovingObjectPosition movingobjectposition, int count)
/*     */   {
/*  39 */     IWand wand = (IWand)wandstack.func_77973_b();
/*     */     
/*  41 */     if (!world.field_72995_K)
/*     */     {
/*  43 */       int frosty = getUpgradeLevel(wand.getFocusStack(wandstack), FocusUpgradeType.alchemistsfrost);
/*  44 */       EntityFrostShard shard = null;
/*  45 */       if (isUpgradedWith(wand.getFocusStack(wandstack), scattershot)) {
/*  46 */         for (int a = 0; a < 5 + wand.getFocusPotency(wandstack) * 2; a++) {
/*  47 */           shard = new EntityFrostShard(world, p, 8.0F);
/*  48 */           shard.setDamage(1.0F);
/*  49 */           shard.fragile = true;
/*  50 */           shard.setFrosty(frosty);
/*  51 */           world.func_72838_d(shard);
/*     */         }
/*     */       }
/*  54 */       else if (isUpgradedWith(wand.getFocusStack(wandstack), iceboulder)) {
/*  55 */         shard = new EntityFrostShard(world, p, 1.0F);
/*  56 */         shard.setDamage(4 + wand.getFocusPotency(wandstack) * 2);
/*  57 */         shard.bounce = 0.8D;
/*  58 */         shard.bounceLimit = 6;
/*  59 */         shard.setFrosty(frosty);
/*  60 */         world.func_72838_d(shard);
/*     */       }
/*     */       else {
/*  63 */         shard = new EntityFrostShard(world, p, 1.0F);
/*  64 */         shard.setDamage((float)(3.0D + wand.getFocusPotency(wandstack) * 1.5D));
/*  65 */         shard.setFrosty(frosty);
/*  66 */         world.func_72838_d(shard);
/*     */       }
/*  68 */       world.func_72956_a(shard, "thaumcraft:ice", 0.4F, 1.0F + world.field_73012_v.nextFloat() * 0.1F);
/*     */     }
/*     */     
/*  71 */     p.func_71038_i();
/*  72 */     return true;
/*     */   }
/*     */   
/*     */ 
/*  76 */   private static final AspectList costBase = new AspectList().add(Aspect.WATER, 2).add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 2);
/*  77 */   private static final AspectList costScatter = new AspectList().add(Aspect.WATER, 2).add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1).add(Aspect.AIR, 2);
/*  78 */   private static final AspectList costBoulder = new AspectList().add(Aspect.WATER, 2).add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1).add(Aspect.EARTH, 2);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  82 */     return isUpgradedWith(itemstack, iceboulder) ? costBoulder : isUpgradedWith(itemstack, scattershot) ? costScatter : costBase;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/*  89 */     return (getUpgradeLevel(focusstack, scattershot) > 0) || (getUpgradeLevel(focusstack, iceboulder) > 0) ? 500 : 200;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/*  94 */     switch (rank) {
/*  95 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfrost };
/*  96 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*  97 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, scattershot, iceboulder, FocusUpgradeType.alchemistsfrost };
/*  98 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*  99 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfrost };
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */   
/* 104 */   public static FocusUpgradeType scattershot = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/scattershot.png"), "focus.upgrade.scattershot.name", "focus.upgrade.scattershot.text", new AspectList().add(Aspect.COLD, 1).add(Aspect.AVERSION, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 109 */   public static FocusUpgradeType iceboulder = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/iceboulder.png"), "focus.upgrade.iceboulder.name", "focus.upgrade.iceboulder.text", new AspectList().add(Aspect.COLD, 1).add(Aspect.CRYSTAL, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusFrost.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */