/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import thaumcraft.api.items.IGoggles;
/*     */ import thaumcraft.api.items.IRevealer;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ 
/*     */ public class EntityUtils
/*     */ {
/*     */   public static boolean hasGoggles(Entity e)
/*     */   {
/*  40 */     if (!(e instanceof EntityPlayer)) return false;
/*  41 */     EntityPlayer viewer = (EntityPlayer)e;
/*     */     
/*  43 */     if ((viewer.func_70694_bm() != null) && ((viewer.func_70694_bm().func_77973_b() instanceof IGoggles)) && 
/*  44 */       (showPopups(viewer.func_70694_bm(), viewer))) { return true;
/*     */     }
/*     */     
/*  47 */     for (int a = 0; a < 4; a++) {
/*  48 */       if ((viewer.field_71071_by.field_70460_b[a] != null) && ((viewer.field_71071_by.field_70460_b[a].func_77973_b() instanceof IGoggles)))
/*     */       {
/*  50 */         if (showPopups(viewer.field_71071_by.field_70460_b[a], viewer)) { return true;
/*     */         }
/*     */       }
/*     */     }
/*  54 */     IInventory baubles = BaublesApi.getBaubles(viewer);
/*  55 */     for (int a = 0; a < 4; a++) {
/*  56 */       if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof IGoggles)) && 
/*  57 */         (showPopups(baubles.func_70301_a(a), viewer))) { return true;
/*     */       }
/*     */     }
/*     */     
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean showPopups(ItemStack stack, EntityPlayer player) {
/*  65 */     return ((IGoggles)stack.func_77973_b()).showIngamePopups(stack, player);
/*     */   }
/*     */   
/*     */   public static boolean hasRevealer(Entity e) {
/*  69 */     if (!(e instanceof EntityPlayer)) return false;
/*  70 */     EntityPlayer viewer = (EntityPlayer)e;
/*     */     
/*  72 */     if ((viewer.func_70694_bm() != null) && ((viewer.func_70694_bm().func_77973_b() instanceof IRevealer)) && 
/*  73 */       (reveals(viewer.func_70694_bm(), viewer))) { return true;
/*     */     }
/*     */     
/*  76 */     for (int a = 0; a < 4; a++) {
/*  77 */       if ((viewer.field_71071_by.field_70460_b[a] != null) && ((viewer.field_71071_by.field_70460_b[a].func_77973_b() instanceof IRevealer)))
/*     */       {
/*  79 */         if (reveals(viewer.field_71071_by.field_70460_b[a], viewer)) { return true;
/*     */         }
/*     */       }
/*     */     }
/*  83 */     IInventory baubles = BaublesApi.getBaubles(viewer);
/*  84 */     for (int a = 0; a < 4; a++) {
/*  85 */       if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof IRevealer)) && 
/*  86 */         (reveals(baubles.func_70301_a(a), viewer))) { return true;
/*     */       }
/*     */     }
/*     */     
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean reveals(ItemStack stack, EntityPlayer player) {
/*  94 */     return ((IRevealer)stack.func_77973_b()).showNodes(stack, player);
/*     */   }
/*     */   
/*     */ 
/*     */   public static Entity getPointedEntity(World world, Entity entityplayer, double minrange, double range, float padding)
/*     */   {
/* 100 */     return getPointedEntity(world, entityplayer, minrange, range, padding, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Entity getPointedEntity(World world, Entity entityplayer, double minrange, double range, float padding, boolean nonCollide)
/*     */   {
/* 107 */     Entity pointedEntity = null;
/* 108 */     double d = range;
/* 109 */     Vec3 vec3d = new Vec3(entityplayer.field_70165_t, entityplayer.field_70163_u + entityplayer.func_70047_e(), entityplayer.field_70161_v);
/*     */     
/*     */ 
/* 112 */     Vec3 vec3d1 = entityplayer.func_70040_Z();
/* 113 */     Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d);
/*     */     
/* 115 */     float f1 = padding;
/* 116 */     List list = world.func_72839_b(entityplayer, entityplayer.func_174813_aQ().func_72321_a(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d).func_72314_b(f1, f1, f1));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 121 */     double d2 = 0.0D;
/* 122 */     for (int i = 0; i < list.size(); i++) {
/* 123 */       Entity entity = (Entity)list.get(i);
/* 124 */       if (entity.func_70032_d(entityplayer) >= minrange)
/*     */       {
/* 126 */         if (((entity.func_70067_L()) || (nonCollide)) && (world.func_147447_a(new Vec3(entityplayer.field_70165_t, entityplayer.field_70163_u + entityplayer.func_70047_e(), entityplayer.field_70161_v), new Vec3(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e(), entity.field_70161_v), false, true, false) == null))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 138 */           float f2 = Math.max(0.8F, entity.func_70111_Y());
/* 139 */           AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_72314_b(f2, f2, f2);
/* 140 */           MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
/*     */           
/* 142 */           if (axisalignedbb.func_72318_a(vec3d)) {
/* 143 */             if ((0.0D < d2) || (d2 == 0.0D)) {
/* 144 */               pointedEntity = entity;
/* 145 */               d2 = 0.0D;
/*     */             }
/*     */             
/*     */           }
/* 149 */           else if (movingobjectposition != null)
/*     */           {
/*     */ 
/* 152 */             double d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f);
/* 153 */             if ((d3 < d2) || (d2 == 0.0D)) {
/* 154 */               pointedEntity = entity;
/* 155 */               d2 = d3;
/*     */             }
/*     */           } } } }
/* 158 */     return pointedEntity;
/*     */   }
/*     */   
/*     */   public static Entity getPointedEntity(World world, EntityLivingBase player, double range, Class<?> clazz) {
/* 162 */     Entity pointedEntity = null;
/* 163 */     double d = range;
/* 164 */     Vec3 vec3d = new Vec3(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/*     */     
/*     */ 
/* 167 */     Vec3 vec3d1 = player.func_70040_Z();
/* 168 */     Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d);
/*     */     
/* 170 */     float f1 = 1.1F;
/* 171 */     List list = world.func_72839_b(player, player.func_174813_aQ().func_72321_a(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d).func_72314_b(f1, f1, f1));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 176 */     double d2 = 0.0D;
/* 177 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/* 179 */       Entity entity = (Entity)list.get(i);
/* 180 */       if ((entity.func_70067_L()) && (world.func_147447_a(new Vec3(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v), new Vec3(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e(), entity.field_70161_v), false, true, false) == null) && (!clazz.isInstance(entity)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 191 */         float f2 = Math.max(0.8F, entity.func_70111_Y());
/* 192 */         AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_72314_b(f2, f2, f2);
/* 193 */         MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
/*     */         
/* 195 */         if (axisalignedbb.func_72318_a(vec3d)) {
/* 196 */           if ((0.0D < d2) || (d2 == 0.0D)) {
/* 197 */             pointedEntity = entity;
/* 198 */             d2 = 0.0D;
/*     */           }
/*     */           
/*     */         }
/* 202 */         else if (movingobjectposition != null)
/*     */         {
/*     */ 
/* 205 */           double d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f);
/* 206 */           if ((d3 < d2) || (d2 == 0.0D)) {
/* 207 */             pointedEntity = entity;
/* 208 */             d2 = d3;
/*     */           }
/*     */         } } }
/* 211 */     return pointedEntity;
/*     */   }
/*     */   
/*     */   public static boolean canEntityBeSeen(Entity entity, TileEntity te) {
/* 215 */     return te.func_145831_w().func_72901_a(new Vec3(te.func_174877_v().func_177958_n() + 0.5D, te.func_174877_v().func_177956_o() + 1.25D, te.func_174877_v().func_177952_p() + 0.5D), new Vec3(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v), false) == null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean canEntityBeSeen(Entity entity, double x, double y, double z)
/*     */   {
/* 222 */     return entity.field_70170_p.func_72901_a(new Vec3(x, y, z), new Vec3(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v), false) == null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean canEntityBeSeen(Entity entity, Entity entity2)
/*     */   {
/* 229 */     return entity.field_70170_p.func_72901_a(new Vec3(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v), new Vec3(entity2.field_70165_t, entity2.field_70163_u, entity2.field_70161_v), false) == null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void setRecentlyHit(EntityLivingBase ent, int hit)
/*     */   {
/*     */     try
/*     */     {
/* 237 */       ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, ent, Integer.valueOf(hit), new String[] { "recentlyHit", "field_70718_bc", "aM" });
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public static int getRecentlyHit(EntityLivingBase ent) {
/*     */     try {
/* 244 */       return ((Integer)net.minecraftforge.fml.relauncher.ReflectionHelper.getPrivateValue(EntityLivingBase.class, ent, new String[] { "recentlyHit", "field_70718_bc", "aM" })).intValue();
/*     */     }
/*     */     catch (Exception e) {}
/* 247 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void resetFloatCounter(EntityPlayerMP player)
/*     */   {
/*     */     try
/*     */     {
/* 300 */       ObfuscationReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, player.field_71135_a, Integer.valueOf(0), new String[] { "floatingTickCount", "field_147365_f", "g" });
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static ArrayList<Entity> getEntitiesInRange(World world, BlockPos pos, Entity entity, Class clazz, double range)
/*     */   {
/* 309 */     return getEntitiesInRange(world, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, entity, clazz, range);
/*     */   }
/*     */   
/*     */ 
/*     */   public static ArrayList<Entity> getEntitiesInRange(World world, double x, double y, double z, Entity entity, Class clazz, double range)
/*     */   {
/* 315 */     ArrayList<Entity> out = new ArrayList();
/* 316 */     List list = world.func_72872_a(clazz, AxisAlignedBB.func_178781_a(x, y, z, x, y, z).func_72314_b(range, range, range));
/*     */     
/*     */ 
/* 319 */     if (list.size() > 0) {
/* 320 */       for (Object e : list) {
/* 321 */         Entity ent = (Entity)e;
/* 322 */         if ((entity == null) || (entity.func_145782_y() != ent.func_145782_y()))
/*     */         {
/*     */ 
/* 325 */           out.add(ent);
/*     */         }
/*     */       }
/*     */     }
/* 329 */     return out;
/*     */   }
/*     */   
/*     */   public static boolean isVisibleTo(float fov, Entity ent, Entity ent2, float range)
/*     */   {
/* 334 */     double[] x = { ent2.field_70165_t, ent2.func_174813_aQ().field_72338_b + ent2.field_70131_O / 2.0F, ent2.field_70161_v };
/* 335 */     double[] t = { ent.field_70165_t, ent.func_174813_aQ().field_72338_b + ent.func_70047_e(), ent.field_70161_v };
/* 336 */     Vec3 q = ent.func_70040_Z();
/* 337 */     q = new Vec3(q.field_72450_a * range, q.field_72448_b * range, q.field_72449_c * range);
/* 338 */     Vec3 l = q.func_72441_c(ent.field_70165_t, ent.func_174813_aQ().field_72338_b + ent.func_70047_e(), ent.field_70161_v);
/* 339 */     double[] b = { l.field_72450_a, l.field_72448_b, l.field_72449_c };
/* 340 */     return Utils.isLyingInCone(x, t, b, fov);
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean isVisibleTo(float fov, Entity ent, double xx, double yy, double zz, float range)
/*     */   {
/* 346 */     double[] x = { xx, yy, zz };
/* 347 */     double[] t = { ent.field_70165_t, ent.func_174813_aQ().field_72338_b + ent.func_70047_e(), ent.field_70161_v };
/* 348 */     Vec3 q = ent.func_70040_Z();
/* 349 */     q = new Vec3(q.field_72450_a * range, q.field_72448_b * range, q.field_72449_c * range);
/* 350 */     Vec3 l = q.func_72441_c(ent.field_70165_t, ent.func_174813_aQ().field_72338_b + ent.func_70047_e(), ent.field_70161_v);
/* 351 */     double[] b = { l.field_72450_a, l.field_72448_b, l.field_72449_c };
/* 352 */     return Utils.isLyingInCone(x, t, b, fov);
/*     */   }
/*     */   
/*     */   public static EntityItem entityDropSpecialItem(Entity entity, ItemStack stack, float dropheight)
/*     */   {
/* 357 */     if ((stack.field_77994_a != 0) && (stack.func_77973_b() != null))
/*     */     {
/* 359 */       EntitySpecialItem entityitem = new EntitySpecialItem(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u + dropheight, entity.field_70161_v, stack);
/*     */       
/* 361 */       entityitem.func_174869_p();
/* 362 */       entityitem.field_70181_x = 0.10000000149011612D;
/* 363 */       entityitem.field_70159_w = 0.0D;
/* 364 */       entityitem.field_70179_y = 0.0D;
/* 365 */       if (entity.captureDrops)
/*     */       {
/* 367 */         entity.capturedDrops.add(entityitem);
/*     */       }
/*     */       else
/*     */       {
/* 371 */         entity.field_70170_p.func_72838_d(entityitem);
/*     */       }
/* 373 */       return entityitem;
/*     */     }
/*     */     
/*     */ 
/* 377 */     return null;
/*     */   }
/*     */   
/*     */   public static void makeChampion(EntityMob entity, boolean persist)
/*     */   {
/*     */     try
/*     */     {
/* 384 */       if (entity.func_110148_a(CHAMPION_MOD).func_111126_e() > -2.0D) return;
/*     */     } catch (Exception e) {
/* 386 */       return;
/*     */     }
/*     */     
/* 389 */     int type = entity.field_70170_p.field_73012_v.nextInt(ChampionModifier.mods.length);
/*     */     
/* 391 */     if ((entity instanceof EntityCreeper)) {
/* 392 */       type = 0;
/*     */     }
/*     */     
/* 395 */     IAttributeInstance modai = entity.func_110148_a(CHAMPION_MOD);
/* 396 */     modai.func_111124_b(ChampionModifier.mods[type].attributeMod);
/* 397 */     modai.func_111121_a(ChampionModifier.mods[type].attributeMod);
/*     */     
/* 399 */     if (!(entity instanceof EntityThaumcraftBoss)) {
/* 400 */       IAttributeInstance iattributeinstance = entity.func_110148_a(SharedMonsterAttributes.field_111267_a);
/* 401 */       iattributeinstance.func_111124_b(CHAMPION_HEALTH);
/* 402 */       iattributeinstance.func_111121_a(CHAMPION_HEALTH);
/* 403 */       IAttributeInstance iattributeinstance2 = entity.func_110148_a(SharedMonsterAttributes.field_111264_e);
/* 404 */       iattributeinstance2.func_111124_b(CHAMPION_DAMAGE);
/* 405 */       iattributeinstance2.func_111121_a(CHAMPION_DAMAGE);
/* 406 */       entity.func_70691_i(25.0F);
/* 407 */       entity.func_96094_a(ChampionModifier.mods[type].getModNameLocalized() + " " + entity.func_70005_c_());
/*     */     } else {
/* 409 */       ((EntityThaumcraftBoss)entity).generateName();
/*     */     }
/*     */     
/* 412 */     if (persist) { entity.func_110163_bv();
/*     */     }
/* 414 */     switch (type) {
/*     */     case 0: 
/* 416 */       IAttributeInstance sai = entity.func_110148_a(SharedMonsterAttributes.field_111263_d);
/* 417 */       sai.func_111124_b(BOLDBUFF);
/* 418 */       sai.func_111121_a(BOLDBUFF);
/* 419 */       break;
/*     */     case 3: 
/* 421 */       IAttributeInstance mai = entity.func_110148_a(SharedMonsterAttributes.field_111264_e);
/* 422 */       mai.func_111124_b(MIGHTYBUFF);
/* 423 */       mai.func_111121_a(MIGHTYBUFF);
/* 424 */       break;
/*     */     case 5: 
/* 426 */       int bh = (int)entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 427 */       entity.func_110149_m(entity.func_110139_bj() + bh);
/*     */     }
/*     */     
/*     */   }
/*     */   
/* 432 */   public static final IAttribute CHAMPION_MOD = new RangedAttribute((IAttribute)null, "tc.mobmod", -2.0D, -2.0D, 100.0D).func_111117_a("Champion modifier").func_111112_a(true);
/* 433 */   public static final AttributeModifier CHAMPION_HEALTH = new AttributeModifier(UUID.fromString("a62bef38-48cc-42a6-ac5e-ef913841c4fd"), "Champion health buff", 100.0D, 0);
/* 434 */   public static final AttributeModifier CHAMPION_DAMAGE = new AttributeModifier(UUID.fromString("a340d2db-d881-4c25-ac62-f0ad14cd63b0"), "Champion damage buff", 2.0D, 2);
/* 435 */   public static final AttributeModifier BOLDBUFF = new AttributeModifier(UUID.fromString("4b1edd33-caa9-47ae-a702-d86c05701037"), "Bold speed boost", 0.3D, 1);
/* 436 */   public static final AttributeModifier MIGHTYBUFF = new AttributeModifier(UUID.fromString("7163897f-07f5-49b3-9ce4-b74beb83d2d3"), "Mighty damage boost", 2.0D, 2);
/* 437 */   public static final AttributeModifier[] HPBUFF = { new AttributeModifier(UUID.fromString("54d621c1-dd4d-4b43-8bd2-5531c8875797"), "HEALTH BUFF 1", 50.0D, 0), new AttributeModifier(UUID.fromString("f51257dc-b7fa-4f7a-92d7-75d68e8592c4"), "HEALTH BUFF 2", 50.0D, 0), new AttributeModifier(UUID.fromString("3d6b2e42-4141-4364-b76d-0e8664bbd0bb"), "HEALTH BUFF 3", 50.0D, 0), new AttributeModifier(UUID.fromString("02c97a08-801c-4131-afa2-1427a6151934"), "HEALTH BUFF 4", 50.0D, 0), new AttributeModifier(UUID.fromString("0f354f6a-33c5-40be-93be-81b1338567f1"), "HEALTH BUFF 5", 50.0D, 0) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 443 */   public static final AttributeModifier[] DMGBUFF = { new AttributeModifier(UUID.fromString("534f8c57-929a-48cf-bbd6-0fd851030748"), "DAMAGE BUFF 1", 0.5D, 0), new AttributeModifier(UUID.fromString("d317a76e-0e7c-4c61-acfd-9fa286053b32"), "DAMAGE BUFF 2", 0.5D, 0), new AttributeModifier(UUID.fromString("ff462d63-26a2-4363-830e-143ed97e2a4f"), "DAMAGE BUFF 3", 0.5D, 0), new AttributeModifier(UUID.fromString("cf1eb39e-0c67-495f-887c-0d3080828d2f"), "DAMAGE BUFF 4", 0.5D, 0), new AttributeModifier(UUID.fromString("3cfab9da-2701-43d8-ac07-885f16fa4117"), "DAMAGE BUFF 5", 0.5D, 0) };
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/lib/utils/EntityUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */