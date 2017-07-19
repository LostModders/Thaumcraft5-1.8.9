/*     */ package thaumcraft.common.entities.construct.golem;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.golems.parts.GolemAddon;
/*     */ import thaumcraft.api.golems.parts.GolemArm;
/*     */ import thaumcraft.api.golems.parts.GolemHead;
/*     */ import thaumcraft.api.golems.parts.GolemLeg;
/*     */ import thaumcraft.api.golems.parts.GolemMaterial;
/*     */ import thaumcraft.api.golems.parts.PartModel;
/*     */ import thaumcraft.api.golems.parts.PartModel.EnumAttachPoint;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.renderers.entity.construct.PartModelBreakers;
/*     */ import thaumcraft.client.renderers.entity.construct.PartModelClaws;
/*     */ import thaumcraft.client.renderers.entity.construct.PartModelDarts;
/*     */ import thaumcraft.client.renderers.entity.construct.PartModelHauler;
/*     */ import thaumcraft.client.renderers.entity.construct.PartModelWheel;
/*     */ import thaumcraft.common.entities.construct.golem.parts.GolemArmDart;
/*     */ import thaumcraft.common.entities.construct.golem.parts.GolemLegLevitator;
/*     */ import thaumcraft.common.entities.construct.golem.parts.GolemLegWheels;
/*     */ 
/*     */ public class GolemProperties
/*     */   implements IGolemProperties
/*     */ {
/*  34 */   private long data = 0L;
/*     */   
/*  36 */   private Set<EnumGolemTrait> traitCache = null;
/*     */   
/*     */   public Set<EnumGolemTrait> getTraits()
/*     */   {
/*  40 */     if (this.traitCache == null) {
/*  41 */       this.traitCache = new HashSet();
/*  42 */       for (EnumGolemTrait trait : getMaterial().traits) addTraitSmart(trait);
/*  43 */       for (EnumGolemTrait trait : getHead().traits) addTraitSmart(trait);
/*  44 */       for (EnumGolemTrait trait : getArms().traits) addTraitSmart(trait);
/*  45 */       for (EnumGolemTrait trait : getLegs().traits) addTraitSmart(trait);
/*  46 */       for (EnumGolemTrait trait : getAddon().traits) addTraitSmart(trait);
/*     */     }
/*  48 */     return this.traitCache;
/*     */   }
/*     */   
/*     */   private void addTraitSmart(EnumGolemTrait trait) {
/*  52 */     if ((trait.opposite != null) && (this.traitCache.contains(trait.opposite))) {
/*  53 */       this.traitCache.remove(trait.opposite);
/*     */     } else {
/*  55 */       this.traitCache.add(trait);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasTrait(EnumGolemTrait trait)
/*     */   {
/*  61 */     return getTraits().contains(trait);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setMaterial(GolemMaterial mat)
/*     */   {
/*  67 */     this.data = ThaumcraftApiHelper.setByteInLong(this.data, mat.id, 0);
/*  68 */     this.traitCache = null;
/*     */   }
/*     */   
/*     */   public GolemMaterial getMaterial()
/*     */   {
/*  73 */     return GolemMaterial.getMaterials()[ThaumcraftApiHelper.getByteInLong(this.data, 0)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setHead(GolemHead mat)
/*     */   {
/*  80 */     this.data = ThaumcraftApiHelper.setByteInLong(this.data, mat.id, 1);
/*  81 */     this.traitCache = null;
/*     */   }
/*     */   
/*     */   public GolemHead getHead()
/*     */   {
/*  86 */     return GolemHead.getHeads()[ThaumcraftApiHelper.getByteInLong(this.data, 1)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setArms(GolemArm mat)
/*     */   {
/*  93 */     this.data = ThaumcraftApiHelper.setByteInLong(this.data, mat.id, 2);
/*  94 */     this.traitCache = null;
/*     */   }
/*     */   
/*     */   public GolemArm getArms()
/*     */   {
/*  99 */     return GolemArm.getArms()[ThaumcraftApiHelper.getByteInLong(this.data, 2)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLegs(GolemLeg mat)
/*     */   {
/* 106 */     this.data = ThaumcraftApiHelper.setByteInLong(this.data, mat.id, 3);
/* 107 */     this.traitCache = null;
/*     */   }
/*     */   
/*     */   public GolemLeg getLegs()
/*     */   {
/* 112 */     return GolemLeg.getLegs()[ThaumcraftApiHelper.getByteInLong(this.data, 3)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAddon(GolemAddon mat)
/*     */   {
/* 119 */     this.data = ThaumcraftApiHelper.setByteInLong(this.data, mat.id, 4);
/* 120 */     this.traitCache = null;
/*     */   }
/*     */   
/*     */   public GolemAddon getAddon()
/*     */   {
/* 125 */     return GolemAddon.getAddons()[ThaumcraftApiHelper.getByteInLong(this.data, 4)];
/*     */   }
/*     */   
/*     */   public void setRank(int rank)
/*     */   {
/* 130 */     this.data = ThaumcraftApiHelper.setByteInLong(this.data, (byte)rank, 5);
/*     */   }
/*     */   
/*     */   public int getRank()
/*     */   {
/* 135 */     return ThaumcraftApiHelper.getByteInLong(this.data, 5);
/*     */   }
/*     */   
/*     */   public static IGolemProperties fromLong(long d) {
/* 139 */     GolemProperties out = new GolemProperties();
/* 140 */     out.data = d;
/* 141 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */   public long toLong()
/*     */   {
/* 147 */     return this.data;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack[] generateComponents()
/*     */   {
/* 153 */     ArrayList<ItemStack> comps = new ArrayList();
/* 154 */     ItemStack base = getMaterial().componentBase;
/* 155 */     ItemStack mech = getMaterial().componentMechanism;
/*     */     
/* 157 */     addToList(comps, base, 2);
/* 158 */     addToList(comps, mech, 1);
/*     */     
/* 160 */     addToListFromComps(comps, getArms().components, getMaterial());
/* 161 */     addToListFromComps(comps, getLegs().components, getMaterial());
/* 162 */     addToListFromComps(comps, getHead().components, getMaterial());
/* 163 */     addToListFromComps(comps, getAddon().components, getMaterial());
/*     */     
/* 165 */     return (ItemStack[])comps.toArray(new ItemStack[0]);
/*     */   }
/*     */   
/*     */   private static void addToListFromComps(ArrayList<ItemStack> comps, Object[] objs, GolemMaterial mat) {
/* 169 */     for (Object o : objs) {
/* 170 */       if ((o instanceof ItemStack)) { addToList(comps, (ItemStack)o, 1);
/*     */       }
/* 172 */       else if ((o instanceof String)) {
/* 173 */         String s = (String)o;
/* 174 */         if (s.equalsIgnoreCase("base")) { addToList(comps, mat.componentBase, 1);
/* 175 */         } else if (s.equalsIgnoreCase("mech")) addToList(comps, mat.componentMechanism, 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void addToList(ArrayList<ItemStack> comps, ItemStack newItem, int mult) {
/* 181 */     for (ItemStack stack : comps) {
/* 182 */       if ((stack.func_77969_a(newItem)) && (ItemStack.func_77970_a(stack, newItem))) {
/* 183 */         stack.field_77994_a += newItem.field_77994_a * mult;
/* 184 */         return;
/*     */       }
/*     */     }
/* 187 */     ItemStack stack = newItem.func_77946_l();
/* 188 */     stack.field_77994_a *= mult;
/* 189 */     comps.add(stack);
/*     */   }
/*     */   
/*     */   static
/*     */   {
/* 194 */     GolemMaterial.register(new GolemMaterial("WOOD", new String[] { "MATSTUDWOOD" }, new ResourceLocation("thaumcraft", "textures/models/golems/mat_wood.png"), 5059370, 6, 2, 1, new ItemStack(BlocksTC.plank, 1, 0), new ItemStack(ItemsTC.gear), new EnumGolemTrait[] { EnumGolemTrait.LIGHT }));
/*     */     
/*     */ 
/*     */ 
/* 198 */     GolemMaterial.register(new GolemMaterial("IRON", new String[] { "MATSTUDIRON" }, new ResourceLocation("thaumcraft", "textures/models/golems/mat_iron.png"), 16777215, 20, 8, 3, new ItemStack(ItemsTC.plate, 1, 1), new ItemStack(ItemsTC.gear), new EnumGolemTrait[] { EnumGolemTrait.HEAVY, EnumGolemTrait.FIREPROOF, EnumGolemTrait.BLASTPROOF }));
/*     */     
/*     */ 
/*     */ 
/* 202 */     GolemMaterial.register(new GolemMaterial("CLAY", new String[] { "MATSTUDCLAY" }, new ResourceLocation("thaumcraft", "textures/models/golems/mat_clay.png"), 13071447, 10, 4, 2, new ItemStack(Blocks.field_150405_ch, 1, 0), new ItemStack(ItemsTC.gear), new EnumGolemTrait[] { EnumGolemTrait.FIREPROOF }));
/*     */     
/*     */ 
/*     */ 
/* 206 */     GolemMaterial.register(new GolemMaterial("BRASS", new String[] { "MATSTUDBRASS" }, new ResourceLocation("thaumcraft", "textures/models/golems/mat_brass.png"), 15638812, 16, 6, 3, new ItemStack(ItemsTC.plate, 1, 0), new ItemStack(ItemsTC.gear), new EnumGolemTrait[] { EnumGolemTrait.LIGHT }));
/*     */     
/*     */ 
/*     */ 
/* 210 */     GolemMaterial.register(new GolemMaterial("THAUMIUM", new String[] { "MATSTUDTHAUMIUM" }, new ResourceLocation("thaumcraft", "textures/models/golems/mat_thaumium.png"), 5257074, 24, 10, 4, new ItemStack(ItemsTC.plate, 1, 2), new ItemStack(ItemsTC.gear, 1, 1), new EnumGolemTrait[] { EnumGolemTrait.HEAVY, EnumGolemTrait.FIREPROOF, EnumGolemTrait.BLASTPROOF }));
/*     */     
/*     */ 
/*     */ 
/* 214 */     GolemMaterial.register(new GolemMaterial("VOID", new String[] { "MATSTUDVOID" }, new ResourceLocation("thaumcraft", "textures/models/golems/mat_void.png"), 1445161, 20, 6, 4, new ItemStack(ItemsTC.plate, 1, 3), new ItemStack(ItemsTC.gear, 1, 2), new EnumGolemTrait[] { EnumGolemTrait.REPAIR }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 220 */     GolemHead.register(new GolemHead("BASIC", new String[] { "MINDCLOCKWORK" }, new ResourceLocation("thaumcraft", "textures/misc/golem/head_basic.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_head_basic.obj"), null, PartModel.EnumAttachPoint.HEAD), new Object[] { new ItemStack(ItemsTC.mind, 1, 0) }, new EnumGolemTrait[0]));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 225 */     GolemHead.register(new GolemHead("SMART", new String[] { "MINDBIOTHAUMIC" }, new ResourceLocation("thaumcraft", "textures/misc/golem/head_smart.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_head_smart.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_head_other.png"), PartModel.EnumAttachPoint.HEAD), new Object[] { new ItemStack(ItemsTC.mind, 1, 1) }, new EnumGolemTrait[] { EnumGolemTrait.SMART, EnumGolemTrait.FRAGILE }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 231 */     GolemHead.register(new GolemHead("SMART_ARMORED", new String[] { "MINDBIOTHAUMIC", "GOLEMCOMBATADV" }, new ResourceLocation("thaumcraft", "textures/misc/golem/head_smartarmor.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_head_smart_armor.obj"), null, PartModel.EnumAttachPoint.HEAD), new Object[] { new ItemStack(ItemsTC.mind, 1, 1), new ItemStack(ItemsTC.plate), "base", new ItemStack(Blocks.field_150325_L) }, new EnumGolemTrait[] { EnumGolemTrait.SMART }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 236 */     GolemHead.register(new GolemHead("SCOUT", new String[] { "GOLEMVISION" }, new ResourceLocation("thaumcraft", "textures/misc/golem/head_scout.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_head_scout.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_head_other.png"), PartModel.EnumAttachPoint.HEAD), new Object[] { new ItemStack(ItemsTC.mind, 1, 0), new ItemStack(ItemsTC.modules) }, new EnumGolemTrait[] { EnumGolemTrait.SCOUT, EnumGolemTrait.FRAGILE }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 243 */     GolemHead.register(new GolemHead("SMART_SCOUT", new String[] { "GOLEMVISION", "MINDBIOTHAUMIC" }, new ResourceLocation("thaumcraft", "textures/misc/golem/head_smartscout.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_head_scout_smart.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_head_other.png"), PartModel.EnumAttachPoint.HEAD), new Object[] { new ItemStack(ItemsTC.mind, 1, 1), new ItemStack(ItemsTC.modules) }, new EnumGolemTrait[] { EnumGolemTrait.SCOUT, EnumGolemTrait.SMART, EnumGolemTrait.FRAGILE }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 252 */     GolemArm.register(new GolemArm("BASIC", new String[] { "MINDCLOCKWORK" }, new ResourceLocation("thaumcraft", "textures/misc/golem/arms_basic.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_arms_basic.obj"), null, PartModel.EnumAttachPoint.ARMS), new Object[0], new EnumGolemTrait[0]));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 257 */     GolemArm.register(new GolemArm("FINE", new String[] { "MATSTUDBRASS" }, new ResourceLocation("thaumcraft", "textures/misc/golem/arms_fine.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_arms_fine.obj"), null, PartModel.EnumAttachPoint.ARMS), new Object[] { new ItemStack(ItemsTC.gear, 2), "base" }, new EnumGolemTrait[] { EnumGolemTrait.DEFT, EnumGolemTrait.FRAGILE }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 262 */     GolemArm.register(new GolemArm("CLAWS", new String[] { "GOLEMCOMBATADV" }, new ResourceLocation("thaumcraft", "textures/misc/golem/arms_claws.png"), new PartModelClaws(new ResourceLocation("thaumcraft", "models/obj/golem_arms_claws.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_arms_claws.png"), PartModel.EnumAttachPoint.ARMS), new Object[] { new ItemStack(ItemsTC.modules, 1, 1), new ItemStack(Items.field_151097_aZ, 2), "base" }, new EnumGolemTrait[] { EnumGolemTrait.FIGHTER, EnumGolemTrait.CLUMSY, EnumGolemTrait.BRUTAL }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 268 */     GolemArm.register(new GolemArm("BREAKERS", new String[] { "GOLEMBREAKER" }, new ResourceLocation("thaumcraft", "textures/misc/golem/arms_breakers.png"), new PartModelBreakers(new ResourceLocation("thaumcraft", "models/obj/golem_arms_breakers.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_arms_breakers.png"), PartModel.EnumAttachPoint.ARMS), new Object[] { new ItemStack(Items.field_151045_i, 2), "base", new ItemStack(Blocks.field_150331_J, 2) }, new EnumGolemTrait[] { EnumGolemTrait.BREAKER, EnumGolemTrait.CLUMSY, EnumGolemTrait.BRUTAL }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 274 */     GolemArm.register(new GolemArm("DARTS", new String[] { "GOLEMCOMBATADV" }, new ResourceLocation("thaumcraft", "textures/misc/golem/arms_darts.png"), new PartModelDarts(new ResourceLocation("thaumcraft", "models/obj/golem_arms_darter.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_arms_darter.png"), PartModel.EnumAttachPoint.ARMS), new Object[] { new ItemStack(ItemsTC.modules, 1, 1), new ItemStack(Blocks.field_150367_z, 2), new ItemStack(Items.field_151032_g, 32), "mech" }, new GolemArmDart(), new EnumGolemTrait[] { EnumGolemTrait.FIGHTER, EnumGolemTrait.CLUMSY, EnumGolemTrait.RANGED, EnumGolemTrait.FRAGILE }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 283 */     GolemLeg.register(new GolemLeg("WALKER", new String[] { "MINDCLOCKWORK" }, new ResourceLocation("thaumcraft", "textures/misc/golem/legs_walker.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_legs_walker.obj"), null, PartModel.EnumAttachPoint.LEGS), new Object[] { "base", "mech" }, new EnumGolemTrait[0]));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 288 */     GolemLeg.register(new GolemLeg("ROLLER", new String[] { "MINDCLOCKWORK" }, new ResourceLocation("thaumcraft", "textures/misc/golem/legs_roller.png"), new PartModelWheel(new ResourceLocation("thaumcraft", "models/obj/golem_legs_wheel.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_legs_wheel.png"), PartModel.EnumAttachPoint.BODY), new Object[] { new ItemStack(Items.field_151054_z, 2), new ItemStack(Items.field_151116_aA), "mech" }, new GolemLegWheels(), new EnumGolemTrait[] { EnumGolemTrait.WHEELED }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 295 */     GolemLeg.register(new GolemLeg("CLIMBER", new String[] { "GOLEMCLIMBER" }, new ResourceLocation("thaumcraft", "textures/misc/golem/legs_climber.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_legs_climber.obj"), new ResourceLocation("thaumcraft", "textures/blocks/base_metal.png"), PartModel.EnumAttachPoint.LEGS), new Object[] { new ItemStack(Items.field_151145_ak, 6), "base", "mech", "mech" }, new EnumGolemTrait[] { EnumGolemTrait.CLIMBER }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 301 */     GolemLeg.register(new GolemLeg("FLYER", new String[] { "GOLEMFLYER" }, new ResourceLocation("thaumcraft", "textures/misc/golem/legs_flyer.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_legs_floater.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_legs_floater.png"), PartModel.EnumAttachPoint.BODY), new Object[] { new ItemStack(BlocksTC.levitator), new ItemStack(ItemsTC.plate, 4), new ItemStack(Items.field_151123_aH), "mech" }, new GolemLegLevitator(), new EnumGolemTrait[] { EnumGolemTrait.FLYER, EnumGolemTrait.FRAGILE }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 310 */     GolemAddon.register(new GolemAddon("NONE", new String[] { "MINDCLOCKWORK" }, new ResourceLocation("thaumcraft", "textures/blocks/blank.png"), null, new Object[0], new EnumGolemTrait[0]));
/*     */     
/*     */ 
/*     */ 
/* 314 */     GolemAddon.register(new GolemAddon("ARMORED", new String[] { "GOLEMCOMBATADV" }, new ResourceLocation("thaumcraft", "textures/misc/golem/addon_armored.png"), new PartModel(new ResourceLocation("thaumcraft", "models/obj/golem_armor.obj"), null, PartModel.EnumAttachPoint.BODY), new Object[] { "base", "base", "base", "base" }, new EnumGolemTrait[] { EnumGolemTrait.ARMORED, EnumGolemTrait.HEAVY }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 319 */     GolemAddon.register(new GolemAddon("FIGHTER", new String[] { "SEALGUARD" }, new ResourceLocation("thaumcraft", "textures/misc/golem/addon_fighter.png"), null, new Object[] { new ItemStack(ItemsTC.modules, 1, 1), "mech" }, new EnumGolemTrait[] { EnumGolemTrait.FIGHTER }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 324 */     GolemAddon.register(new GolemAddon("HAULER", new String[] { "MINDCLOCKWORK" }, new ResourceLocation("thaumcraft", "textures/misc/golem/addon_hauler.png"), new PartModelHauler(new ResourceLocation("thaumcraft", "models/obj/golem_hauler.obj"), new ResourceLocation("thaumcraft", "textures/models/golems/golem_hauler.png"), PartModel.EnumAttachPoint.BODY), new Object[] { new ItemStack(Items.field_151116_aA), new ItemStack(Blocks.field_150486_ae) }, new EnumGolemTrait[] { EnumGolemTrait.HAULER }));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/entities/construct/golem/GolemProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */