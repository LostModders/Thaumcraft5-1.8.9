/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.projectile.EntityShockOrb;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemFocusShock extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusShock()
/*     */   {
/*  33 */     super("shock", 10466239);
/*     */   }
/*     */   
/*  36 */   private static final AspectList costBase = new AspectList().add(Aspect.AIR, 8);
/*  37 */   private static final AspectList costChain = new AspectList().add(Aspect.AIR, 8).add(Aspect.WATER, 8);
/*  38 */   private static final AspectList costGround = new AspectList().add(Aspect.AIR, 8).add(Aspect.EARTH, 8);
/*     */   
/*     */   public boolean canBePlacedInTurret()
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */   
/*     */   public float getTurretCorrection(ItemStack focusstack)
/*     */   {
/*  47 */     return isUpgradedWith(focusstack, earthshock) ? 5.0F : 0.0F;
/*     */   }
/*     */   
/*     */   public float getTurretRange(ItemStack focusstack)
/*     */   {
/*  52 */     return 20.0F;
/*     */   }
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  57 */     return isUpgradedWith(itemstack, earthshock) ? costGround : isUpgradedWith(itemstack, chainlightning) ? costChain : costBase;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/*  63 */     return isUpgradedWith(focusstack, earthshock) ? 1000 : isUpgradedWith(focusstack, chainlightning) ? 500 : 250;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack)
/*     */   {
/*  70 */     return isUpgradedWith(itemstack, earthshock) ? ItemFocusBasic.WandFocusAnimation.WAVE : ItemFocusBasic.WandFocusAnimation.CHARGE;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void shootLightning(World world, EntityLivingBase entityplayer, double xx, double yy, double zz, boolean offset)
/*     */   {
/*  76 */     double px = entityplayer.field_70165_t;
/*  77 */     double py = entityplayer.func_174813_aQ().field_72338_b + entityplayer.field_70131_O / 2.0F + 0.25D;
/*  78 */     double pz = entityplayer.field_70161_v;
/*  79 */     px += -MathHelper.func_76134_b(entityplayer.field_70177_z / 180.0F * 3.141593F) * 0.06F;
/*  80 */     py += -0.05999999865889549D;
/*  81 */     pz += -MathHelper.func_76126_a(entityplayer.field_70177_z / 180.0F * 3.141593F) * 0.06F;
/*  82 */     Vec3 vec3d = entityplayer.func_70676_i(1.0F);
/*  83 */     px += vec3d.field_72450_a * 0.5D;
/*  84 */     py += vec3d.field_72448_b * 0.5D;
/*  85 */     pz += vec3d.field_72449_c * 0.5D;
/*  86 */     Thaumcraft.proxy.getFX().arcBolt(px, py, pz, xx, yy, zz, 0.5F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onFocusActivation(ItemStack itemstack, World world, EntityLivingBase p, MovingObjectPosition movingobjectposition, int count)
/*     */   {
/*  93 */     IWand wand = (IWand)itemstack.func_77973_b();
/*  94 */     if (isUpgradedWith(wand.getFocusStack(itemstack), earthshock)) {
/*  95 */       if (!world.field_72995_K) {
/*  96 */         EntityShockOrb orb = new EntityShockOrb(world, p);
/*  97 */         orb.area += getUpgradeLevel(wand.getFocusStack(itemstack), FocusUpgradeType.enlarge) * 2; EntityShockOrb 
/*  98 */           tmp74_72 = orb;tmp74_72.damage = ((int)(tmp74_72.damage + wand.getFocusPotency(itemstack) * 1.33D));
/*  99 */         world.func_72838_d(orb);
/* 100 */         world.func_72956_a(orb, "thaumcraft:zap", 1.0F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2F);
/*     */       }
/* 102 */       p.func_71038_i();
/*     */     } else {
/* 104 */       doLightningBolt(itemstack, p, count);
/*     */     }
/* 106 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void doLightningBolt(ItemStack stack, EntityLivingBase p, int count)
/*     */   {
/* 112 */     IWand wand = (IWand)stack.func_77973_b();
/*     */     
/* 114 */     int potency = wand.getFocusPotency(stack);
/*     */     
/* 116 */     Entity pointedEntity = EntityUtils.getPointedEntity(p.field_70170_p, p, 0.0D, 20.0D, 1.1F);
/*     */     
/* 118 */     if (p.field_70170_p.field_72995_K) {
/* 119 */       MovingObjectPosition mop = thaumcraft.common.lib.utils.BlockUtils.getTargetBlock(p.field_70170_p, p, false);
/* 120 */       Vec3 v = p.func_70676_i(2.0F);
/* 121 */       double px = p.field_70165_t + v.field_72450_a * 10.0D;
/* 122 */       double py = p.func_174813_aQ().field_72338_b + 0.25D + p.field_70131_O / 2.0F + v.field_72448_b * 10.0D;
/* 123 */       double pz = p.field_70161_v + v.field_72449_c * 10.0D;
/* 124 */       if (mop != null) {
/* 125 */         px = mop.field_72307_f.field_72450_a;
/* 126 */         py = mop.field_72307_f.field_72448_b;
/* 127 */         pz = mop.field_72307_f.field_72449_c;
/* 128 */         for (int a = 0; a < 5; a++) {
/* 129 */           Thaumcraft.proxy.getFX().sparkle((float)px + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3F, (float)py + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3F, (float)pz + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3F, 2.0F + p.field_70170_p.field_73012_v.nextFloat(), 2, 0.05F + p.field_70170_p.field_73012_v.nextFloat() * 0.05F);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 134 */       if (pointedEntity != null) {
/* 135 */         px = pointedEntity.field_70165_t;
/* 136 */         py = pointedEntity.func_174813_aQ().field_72338_b + pointedEntity.field_70131_O / 2.0F;
/* 137 */         pz = pointedEntity.field_70161_v;
/* 138 */         for (int a = 0; a < 5; a++) {
/* 139 */           Thaumcraft.proxy.getFX().sparkle((float)px + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6F, (float)py + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6F, (float)pz + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6F, 2.0F + p.field_70170_p.field_73012_v.nextFloat(), 2, 0.05F + p.field_70170_p.field_73012_v.nextFloat() * 0.05F);
/*     */         }
/*     */       }
/*     */       
/* 143 */       shootLightning(p.field_70170_p, p, px, py, pz, true);
/*     */     } else {
/* 145 */       p.field_70170_p.func_72908_a(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:shock", 0.25F, 1.0F);
/*     */       
/* 147 */       if ((pointedEntity != null) && ((pointedEntity instanceof EntityLivingBase)) && (
/* 148 */         (!(pointedEntity instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())))
/*     */       {
/* 150 */         int cl = getUpgradeLevel(wand.getFocusStack(stack), chainlightning) * 2;
/* 151 */         pointedEntity.func_70097_a(DamageSource.func_76354_b(p, p), (cl > 0 ? 6 : 4) + potency);
/*     */         
/* 153 */         if (cl > 0) {
/* 154 */           cl += getUpgradeLevel(wand.getFocusStack(stack), FocusUpgradeType.enlarge) * 2;
/* 155 */           EntityLivingBase center = (EntityLivingBase)pointedEntity;
/* 156 */           ArrayList<Integer> targets = new ArrayList();
/* 157 */           targets.add(Integer.valueOf(pointedEntity.func_145782_y()));
/* 158 */           while (cl > 0) {
/* 159 */             cl--;
/* 160 */             ArrayList<Entity> list = EntityUtils.getEntitiesInRange(p.field_70170_p, center.field_70165_t, center.field_70163_u, center.field_70161_v, p, EntityLivingBase.class, 8.0D);
/*     */             
/*     */ 
/*     */ 
/* 164 */             double d = Double.MAX_VALUE;
/* 165 */             Entity closest = null;
/* 166 */             for (Entity e : list)
/* 167 */               if ((!targets.contains(Integer.valueOf(e.func_145782_y()))) && (!e.field_70128_L) && 
/* 168 */                 ((!(e instanceof IEntityOwnable)) || (!((IEntityOwnable)e).func_70902_q().equals(p))) && (
/* 169 */                 (!(e instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W()))) {
/* 170 */                 double dd = e.func_70068_e(center);
/* 171 */                 if (dd < d) {
/* 172 */                   closest = e;
/* 173 */                   d = dd;
/*     */                 }
/*     */               }
/* 176 */             if (closest != null) {
/* 177 */               thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new thaumcraft.common.lib.network.fx.PacketFXZap(center.func_145782_y(), closest.func_145782_y()), new net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint(p.field_70170_p.field_73011_w.func_177502_q(), center.field_70165_t, center.field_70163_u, center.field_70161_v, 64.0D));
/*     */               
/*     */ 
/*     */ 
/* 181 */               targets.add(Integer.valueOf(closest.func_145782_y()));
/* 182 */               closest.func_70097_a(DamageSource.func_76354_b(p, p), 4 + potency);
/*     */               
/* 184 */               center = (EntityLivingBase)closest;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 196 */     if ((type.equals(FocusUpgradeType.enlarge)) && 
/* 197 */       (!isUpgradedWith(focusstack, chainlightning)) && (!isUpgradedWith(focusstack, earthshock)))
/*     */     {
/* 199 */       return false;
/*     */     }
/* 201 */     return true;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 206 */     switch (rank) {
/* 207 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*     */     case 2: 
/* 209 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*     */     case 3: 
/* 211 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, chainlightning, earthshock };
/*     */     case 4: 
/* 213 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
/*     */     case 5: 
/* 215 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
/*     */     }
/*     */     
/* 218 */     return null;
/*     */   }
/*     */   
/* 221 */   public static FocusUpgradeType chainlightning = new FocusUpgradeType(new net.minecraft.util.ResourceLocation("thaumcraft", "textures/foci/chainlightning.png"), "focus.upgrade.chainlightning.name", "focus.upgrade.chainlightning.text", new AspectList().add(Aspect.ENERGY, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 226 */   public static FocusUpgradeType earthshock = new FocusUpgradeType(new net.minecraft.util.ResourceLocation("thaumcraft", "textures/foci/earthshock.png"), "focus.upgrade.earthshock.name", "focus.upgrade.earthshock.text", new AspectList().add(Aspect.ENERGY, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/foci/ItemFocusShock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */