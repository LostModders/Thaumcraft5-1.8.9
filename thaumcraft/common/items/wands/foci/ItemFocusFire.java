/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;
/*     */ import thaumcraft.common.entities.projectile.EntityEmber;
/*     */ import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
/*     */ 
/*     */ public class ItemFocusFire extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusFire()
/*     */   {
/*  22 */     super("fire", 15028484);
/*     */   }
/*     */   
/*  25 */   private static final AspectList costBase = new AspectList().add(Aspect.FIRE, 2);
/*  26 */   private static final AspectList costBeam = new AspectList().add(Aspect.FIRE, 2).add(Aspect.ORDER, 2);
/*  27 */   private static final AspectList costBall = new AspectList().add(Aspect.FIRE, 8).add(Aspect.ENTROPY, 8);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  31 */     return isUpgradedWith(itemstack, fireball) ? costBall : isUpgradedWith(itemstack, firebeam) ? costBeam : costBase;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean canBePlacedInTurret()
/*     */   {
/*  38 */     return true;
/*     */   }
/*     */   
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/*  43 */     return isUpgradedWith(focusstack, fireball) ? 1000 : 0;
/*     */   }
/*     */   
/*     */   public boolean isVisCostPerTick(ItemStack itemstack)
/*     */   {
/*  48 */     return !isUpgradedWith(itemstack, fireball);
/*     */   }
/*     */   
/*     */   public float getTurretCorrection(ItemStack focusstack)
/*     */   {
/*  53 */     return isUpgradedWith(focusstack, fireball) ? 3.0F : 0.0F;
/*     */   }
/*     */   
/*     */   public float getTurretRange(ItemStack focusstack)
/*     */   {
/*  58 */     return isUpgradedWith(focusstack, firebeam) ? 16.0F : isUpgradedWith(focusstack, fireball) ? 32.0F : 12.0F;
/*     */   }
/*     */   
/*     */   public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack)
/*     */   {
/*  63 */     return isUpgradedWith(itemstack, fireball) ? ItemFocusBasic.WandFocusAnimation.WAVE : ItemFocusBasic.WandFocusAnimation.CHARGE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onFocusActivation(ItemStack wandstack, World world, EntityLivingBase player, MovingObjectPosition movingobjectposition, int count)
/*     */   {
/*  71 */     IWand wand = (IWand)wandstack.func_77973_b();
/*     */     
/*  73 */     if (isUpgradedWith(wand.getFocusStack(wandstack), fireball)) {
/*  74 */       if (!world.field_72995_K) {
/*  75 */         EntityExplosiveOrb orb = new EntityExplosiveOrb(world, player);
/*  76 */         orb.strength += wand.getFocusPotency(wandstack) * 0.4F;
/*  77 */         orb.onFire = isUpgradedWith(wand.getFocusStack(wandstack), FocusUpgradeType.alchemistsfire);
/*  78 */         world.func_72838_d(orb);
/*  79 */         world.func_180498_a((EntityPlayer)null, 1009, new net.minecraft.util.BlockPos(player), 0);
/*     */       }
/*  81 */       player.func_71038_i();
/*     */     } else {
/*  83 */       int range = 17;
/*  84 */       net.minecraft.util.Vec3 vec3d = player.func_70676_i(range);
/*  85 */       if ((!world.field_72995_K) && (this.soundDelay < System.currentTimeMillis()))
/*     */       {
/*  87 */         world.func_72956_a(player, "thaumcraft:fireloop", 0.33F, 2.0F);
/*  88 */         this.soundDelay = (System.currentTimeMillis() + 500L);
/*     */       }
/*     */       
/*  91 */       if (!world.field_72995_K) {
/*  92 */         float scatter = isUpgradedWith(wand.getFocusStack(wandstack), firebeam) ? 0.25F : 15.0F;
/*  93 */         for (int a = 0; a < 2 + wand.getFocusPotency(wandstack); a++) {
/*  94 */           EntityEmber orb = new EntityEmber(world, player, scatter);
/*  95 */           orb.damage = (2 + wand.getFocusPotency(wandstack));
/*  96 */           if (isUpgradedWith(wand.getFocusStack(wandstack), firebeam)) {
/*  97 */             orb.damage += 0.5F;
/*  98 */             orb.damage *= 1.5F;
/*  99 */             orb.duration = 30;
/*     */           }
/* 101 */           orb.firey = getUpgradeLevel(wand.getFocusStack(wandstack), FocusUpgradeType.alchemistsfire);
/* 102 */           orb.field_70165_t += orb.field_70159_w;
/* 103 */           orb.field_70163_u += orb.field_70181_x;
/* 104 */           orb.field_70161_v += orb.field_70179_y;
/* 105 */           world.func_72838_d(orb);
/*     */         }
/*     */       }
/*     */     }
/* 109 */     return true;
/*     */   }
/*     */   
/*     */ 
/* 113 */   long soundDelay = 0L;
/*     */   
/*     */ 
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 118 */     switch (rank) {
/* 119 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*     */     case 2: 
/* 121 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfire };
/*     */     case 3: 
/* 123 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, fireball, firebeam };
/*     */     case 4: 
/* 125 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfire };
/*     */     case 5: 
/* 127 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*     */     }
/*     */     
/* 130 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 135 */     if ((type.equals(FocusUpgradeType.alchemistsfire)) && (isUpgradedWith(focusstack, fireball)) && (isUpgradedWith(focusstack, FocusUpgradeType.alchemistsfire)))
/*     */     {
/* 137 */       return false; }
/* 138 */     return true;
/*     */   }
/*     */   
/* 141 */   public static FocusUpgradeType fireball = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/fireball.png"), "focus.upgrade.fireball.name", "focus.upgrade.fireball.text", new AspectList().add(Aspect.DARKNESS, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 146 */   public static FocusUpgradeType firebeam = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/firebeam.png"), "focus.upgrade.firebeam.name", "focus.upgrade.firebeam.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.AIR, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusFire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */