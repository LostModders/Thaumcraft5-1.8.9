/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.entities.monster.EntityFireBat;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemFocusHellbat extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusHellbat()
/*     */   {
/*  27 */     super("bat", 14431746);
/*     */   }
/*     */   
/*     */   public boolean canBePlacedInTurret()
/*     */   {
/*  32 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onFocusActivation(ItemStack itemstack, World world, EntityLivingBase player, MovingObjectPosition movingobjectposition, int count)
/*     */   {
/*  38 */     IWand wand = (IWand)itemstack.func_77973_b();
/*     */     
/*  40 */     net.minecraft.entity.Entity pointedEntity = EntityUtils.getPointedEntity(player.field_70170_p, player, 32.0D, EntityFireBat.class);
/*     */     
/*  42 */     double px = player.field_70165_t;
/*  43 */     double py = player.field_70163_u;
/*  44 */     double pz = player.field_70161_v;
/*  45 */     py = player.func_174813_aQ().field_72338_b + player.field_70131_O / 2.0F + 0.25D;
/*  46 */     px -= MathHelper.func_76134_b(player.field_70177_z / 180.0F * 3.141593F) * 0.16F;
/*  47 */     py -= 0.05000000014901161D;
/*  48 */     pz -= MathHelper.func_76126_a(player.field_70177_z / 180.0F * 3.141593F) * 0.16F;
/*  49 */     Vec3 vec3d = player.func_70676_i(1.0F);
/*  50 */     px += vec3d.field_72450_a * 0.5D;
/*  51 */     py += vec3d.field_72448_b * 0.5D;
/*  52 */     pz += vec3d.field_72449_c * 0.5D;
/*     */     
/*  54 */     if ((pointedEntity != null) && ((pointedEntity instanceof EntityLivingBase))) {
/*  55 */       if (!world.field_72995_K) {
/*  56 */         if (((pointedEntity instanceof EntityPlayer)) && (!MinecraftServer.func_71276_C().func_71219_W())) return false;
/*  57 */         EntityFireBat firebat = new EntityFireBat(world);
/*  58 */         firebat.func_70012_b(px, py + firebat.field_70131_O, pz, player.field_70177_z, 0.0F);
/*  59 */         firebat.func_70624_b((EntityLivingBase)pointedEntity);
/*  60 */         firebat.damBonus = wand.getFocusPotency(itemstack);
/*  61 */         firebat.setIsSummoned(true);
/*  62 */         firebat.setIsBatHanging(false);
/*  63 */         if (isUpgradedWith(wand.getFocusStack(itemstack), devilbats)) {
/*  64 */           firebat.setIsDevil(true);
/*     */         }
/*  66 */         if (isUpgradedWith(wand.getFocusStack(itemstack), batbombs)) {
/*  67 */           firebat.setIsExplosive(true);
/*     */         }
/*  69 */         if (isUpgradedWith(wand.getFocusStack(itemstack), vampirebats)) {
/*  70 */           firebat.owner = player;
/*  71 */           firebat.setIsVampire(true);
/*     */         }
/*  73 */         if (world.func_72838_d(firebat)) {
/*  74 */           world.func_175718_b(2004, new net.minecraft.util.BlockPos((int)px, (int)py, (int)pz), 0);
/*  75 */           world.func_72956_a(firebat, "thaumcraft:ice", 0.2F, 0.95F + world.field_73012_v.nextFloat() * 0.1F);
/*     */         } else {
/*  77 */           world.func_72956_a(player, "thaumcraft:wandfail", 0.1F, 0.8F + world.field_73012_v.nextFloat() * 0.1F);
/*     */         }
/*     */       }
/*  80 */       player.func_71038_i();
/*  81 */       return true;
/*     */     }
/*  83 */     return false;
/*     */   }
/*     */   
/*     */ 
/*  87 */   private static final AspectList costBase = new AspectList().add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 5).add(Aspect.AIR, 5);
/*  88 */   private static final AspectList costBomb = new AspectList().add(Aspect.FIRE, 5).add(Aspect.ENTROPY, 10).add(Aspect.AIR, 5);
/*  89 */   private static final AspectList costDevil = new AspectList().add(Aspect.FIRE, 5).add(Aspect.ENTROPY, 5).add(Aspect.AIR, 5).add(Aspect.EARTH, 5);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  93 */     return isUpgradedWith(itemstack, devilbats) ? costDevil : isUpgradedWith(itemstack, batbombs) ? costBomb : costBase;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/* 100 */     return 1000;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 105 */     switch (rank) {
/* 106 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 107 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 108 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, batbombs, devilbats };
/* 109 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 110 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, vampirebats };
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 117 */     if ((type.equals(vampirebats)) && (!ResearchHelper.isResearchComplete(player.func_70005_c_(), "VAMPBAT")))
/*     */     {
/* 119 */       return false; }
/* 120 */     return true;
/*     */   }
/*     */   
/* 123 */   public static FocusUpgradeType batbombs = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/batbombs.png"), "focus.upgrade.batbombs.name", "focus.upgrade.batbombs.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.TRAP, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 128 */   public static FocusUpgradeType devilbats = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/devilbats.png"), "focus.upgrade.devilbats.name", "focus.upgrade.devilbats.text", new AspectList().add(Aspect.PROTECT, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 133 */   public static FocusUpgradeType vampirebats = new FocusUpgradeType(new ResourceLocation("thaumcraft", "textures/foci/vampirebats.png"), "focus.upgrade.vampirebats.name", "focus.upgrade.vampirebats.text", new AspectList().add(Aspect.DESIRE, 1).add(Aspect.LIFE, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusHellbat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */