/*     */ package thaumcraft.common.items.wands;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.items.IArchitect;
/*     */ import thaumcraft.api.items.IArchitect.EnumAxis;
/*     */ import thaumcraft.api.items.IArchitectExtended;
/*     */ import thaumcraft.api.items.IRechargable;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.IWandRodOnUpdate;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.api.wands.WandTriggerRegistry;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.aura.AuraHandler;
/*     */ 
/*     */ public class ItemWand extends Item implements IArchitectExtended, IRechargable, IWand
/*     */ {
/*     */   public ItemWand()
/*     */   {
/*  58 */     this.field_77777_bU = 1;
/*  59 */     func_77656_e(0);
/*  60 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */   public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
/*     */   {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_77645_m()
/*     */   {
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77662_d()
/*     */   {
/*  76 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaxVis(ItemStack stack)
/*     */   {
/*  87 */     return (int)(getRod(stack).getCapacity() * (isSceptre(stack) ? 1.5D : 1.0D));
/*     */   }
/*     */   
/*     */   private int getMaxVisInternal(ItemStack stack) {
/*  91 */     return getMaxVis(stack) * 100;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getVis(ItemStack is, Aspect aspect)
/*     */   {
/*  99 */     return getVisInternal(is, aspect) / 100;
/*     */   }
/*     */   
/*     */   private int getVisInternal(ItemStack is, Aspect aspect) {
/* 103 */     int out = 0;
/* 104 */     if ((is != null) && (aspect != null) && (is.func_77942_o()) && (is.func_77978_p().func_74764_b(aspect.getTag()))) {
/* 105 */       out = is.func_77978_p().func_74762_e(aspect.getTag());
/*     */     }
/* 107 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList getAllVis(ItemStack is)
/*     */   {
/* 115 */     AspectList out = new AspectList();
/* 116 */     for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 117 */       if ((is.func_77942_o()) && (is.func_77978_p().func_74764_b(aspect.getTag()))) {
/* 118 */         out.merge(aspect, is.func_77978_p().func_74762_e(aspect.getTag()) / 100);
/*     */       } else {
/* 120 */         out.merge(aspect, 0);
/*     */       }
/*     */     }
/* 123 */     return out;
/*     */   }
/*     */   
/*     */   private AspectList getAllVisInternal(ItemStack is) {
/* 127 */     AspectList out = new AspectList();
/* 128 */     for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 129 */       if ((is.func_77942_o()) && (is.func_77978_p().func_74764_b(aspect.getTag()))) {
/* 130 */         out.merge(aspect, is.func_77978_p().func_74762_e(aspect.getTag()));
/*     */       } else {
/* 132 */         out.merge(aspect, 0);
/*     */       }
/*     */     }
/* 135 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList getAspectsWithRoom(ItemStack wandstack)
/*     */   {
/* 143 */     AspectList out = new AspectList();
/* 144 */     AspectList cur = getAllVisInternal(wandstack);
/* 145 */     for (Aspect aspect : cur.getAspects()) {
/* 146 */       if (cur.getAmount(aspect) < getMaxVisInternal(wandstack)) {
/* 147 */         int a = Math.max(1, (getMaxVisInternal(wandstack) - cur.getAmount(aspect)) / 100);
/* 148 */         out.add(aspect, a);
/*     */       }
/*     */     }
/* 151 */     return out;
/*     */   }
/*     */   
/*     */   private void storeVisInternal(ItemStack is, Aspect aspect, int amount) {
/* 155 */     is.func_77983_a(aspect.getTag(), new NBTTagInt(amount));
/*     */   }
/*     */   
/*     */   private void storeVisInternal(ItemStack is, AspectList in) {
/* 159 */     for (Aspect aspect : in.getAspects()) {
/* 160 */       storeVisInternal(is, aspect, in.getAmount(aspect));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean consumeVis(ItemStack is, EntityPlayer player, Aspect aspect, int amount, boolean crafting)
/*     */   {
/* 171 */     amount *= 100;
/* 172 */     amount = (int)(amount * getConsumptionModifier(is, player, aspect, crafting));
/* 173 */     if (getVisInternal(is, aspect) >= amount) {
/* 174 */       storeVisInternal(is, aspect, getVisInternal(is, aspect) - amount);
/* 175 */       return true;
/*     */     }
/* 177 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean consumeAllVis(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit, boolean crafting)
/*     */   {
/* 185 */     if ((aspects == null) || (aspects.size() == 0)) { return false;
/*     */     }
/* 187 */     AspectList nl = new AspectList();
/* 188 */     for (Aspect aspect : aspects.getAspects()) {
/* 189 */       int cost = aspects.getAmount(aspect) * 100;
/* 190 */       int c = cost;
/* 191 */       cost = (int)(cost * getConsumptionModifier(is, player, aspect, crafting));
/* 192 */       if (getVisInternal(is, aspect) < cost) return false;
/* 193 */       nl.add(aspect, cost);
/*     */     }
/*     */     
/* 196 */     if ((doit) && (!player.field_70170_p.field_72995_K)) {
/* 197 */       for (Aspect aspect : nl.getAspects())
/* 198 */         storeVisInternal(is, aspect, getVisInternal(is, aspect) - nl.getAmount(aspect));
/*     */     }
/* 200 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addVis(ItemStack is, Aspect aspect, int amount, boolean doit)
/*     */   {
/* 208 */     if (!aspect.isPrimal()) return 0;
/* 209 */     int storeAmount = getVisInternal(is, aspect) + amount * 100;
/* 210 */     int leftover = Math.max(storeAmount - getMaxVisInternal(is), 0);
/* 211 */     if (doit) storeVisInternal(is, aspect, Math.min(storeAmount, getMaxVisInternal(is)));
/* 212 */     return (int)Math.floor(leftover / 100.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getConsumptionModifier(ItemStack is, EntityPlayer player, Aspect aspect, boolean crafting)
/*     */   {
/* 220 */     float consumptionModifier = 1.0F;
/* 221 */     if ((getCap(is).getSpecialCostModifierAspects() != null) && (getCap(is).getSpecialCostModifierAspects().contains(aspect)))
/*     */     {
/* 223 */       consumptionModifier = getCap(is).getSpecialCostModifier();
/*     */     } else {
/* 225 */       consumptionModifier = getCap(is).getBaseCostModifier();
/*     */     }
/*     */     
/* 228 */     if (player != null) { consumptionModifier -= WandManager.getTotalVisDiscount(player, aspect);
/*     */     }
/* 230 */     if ((getFocus(is) != null) && (!crafting)) {
/* 231 */       consumptionModifier -= getFocusFrugal(is) / 10.0F;
/* 232 */       if (isStaff(is)) {
/* 233 */         consumptionModifier -= 0.1F;
/*     */       }
/*     */     }
/*     */     
/* 237 */     if (isSceptre(is)) {
/* 238 */       consumptionModifier -= 0.1F;
/*     */     }
/*     */     
/* 241 */     return Math.max(consumptionModifier, 0.1F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AspectList getAspectsInChargable(ItemStack is)
/*     */   {
/* 248 */     return getAllVis(is);
/*     */   }
/*     */   
/*     */   public Aspect handleRecharge(World world, ItemStack is, BlockPos pos, EntityPlayer player, int amount)
/*     */   {
/* 253 */     amount += getCap(is).getChargeBonus();
/* 254 */     AspectList al = getAspectsWithRoom(is);
/* 255 */     for (Aspect aspect : al.getAspectsSortedByAmount())
/* 256 */       if ((aspect != null) && (
/* 257 */         (player == null) || (!AuraHandler.shouldPreserveAura(world, player, pos, aspect)))) {
/* 258 */         int amt = Math.min(amount, al.getAmount(aspect));
/* 259 */         int drained = AuraHandler.drainAuraAvailable(world, pos, aspect, amt);
/* 260 */         if (drained > 0) {
/* 261 */           addVis(is, aspect, drained, true);
/* 262 */           amount -= drained;
/* 263 */           if (amount <= 0) return aspect;
/*     */         }
/*     */       }
/* 266 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemFocusBasic getFocus(ItemStack stack)
/*     */   {
/* 277 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("focus"))) {
/* 278 */       NBTTagCompound nbt = stack.func_77978_p().func_74775_l("focus");
/* 279 */       return (ItemFocusBasic)ItemStack.func_77949_a(nbt).func_77973_b();
/*     */     }
/* 281 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack getFocusStack(ItemStack stack)
/*     */   {
/* 289 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("focus"))) {
/* 290 */       NBTTagCompound nbt = stack.func_77978_p().func_74775_l("focus");
/* 291 */       return ItemStack.func_77949_a(nbt);
/*     */     }
/* 293 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFocus(ItemStack stack, ItemStack focus)
/*     */   {
/* 301 */     if (focus == null) {
/* 302 */       stack.func_77978_p().func_82580_o("focus");
/*     */     } else {
/* 304 */       stack.func_77983_a("focus", focus.func_77955_b(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WandRod getRod(ItemStack stack)
/*     */   {
/* 313 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("rod"))) {
/* 314 */       return (WandRod)WandRod.rods.get(stack.func_77978_p().func_74779_i("rod"));
/*     */     }
/* 316 */     return ConfigItems.WAND_ROD_WOOD;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isStaff(ItemStack stack)
/*     */   {
/* 324 */     return getRod(stack).isStaff();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSceptre(ItemStack stack)
/*     */   {
/* 332 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("sceptre"))) {
/* 333 */       return true;
/*     */     }
/* 335 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRod(ItemStack stack, WandRod rod)
/*     */   {
/* 343 */     stack.func_77983_a("rod", new NBTTagString(rod.getTag()));
/*     */     
/* 345 */     if (rod.isStaff())
/*     */     {
/* 347 */       NBTTagList tags = new NBTTagList();
/* 348 */       NBTTagCompound tag = new NBTTagCompound();
/* 349 */       tag.func_74778_a("AttributeName", SharedMonsterAttributes.field_111264_e.func_111108_a());
/*     */       
/* 351 */       AttributeModifier am = new AttributeModifier(field_111210_e, "Weapon modifier", 6.0D, 0);
/*     */       
/* 353 */       tag.func_74778_a("Name", am.func_111166_b());
/* 354 */       tag.func_74780_a("Amount", am.func_111164_d());
/* 355 */       tag.func_74768_a("Operation", am.func_111169_c());
/* 356 */       tag.func_74772_a("UUIDMost", am.func_111167_a().getMostSignificantBits());
/* 357 */       tag.func_74772_a("UUIDLeast", am.func_111167_a().getLeastSignificantBits());
/*     */       
/* 359 */       tags.func_74742_a(tag);
/* 360 */       stack.func_77978_p().func_74782_a("AttributeModifiers", tags);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WandCap getCap(ItemStack stack)
/*     */   {
/* 370 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("cap"))) {
/* 371 */       return (WandCap)WandCap.caps.get(stack.func_77978_p().func_74779_i("cap"));
/*     */     }
/* 373 */     return ConfigItems.WAND_CAP_IRON;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCap(ItemStack stack, WandCap cap)
/*     */   {
/* 381 */     stack.func_77983_a("cap", new NBTTagString(cap.getTag()));
/*     */   }
/*     */   
/*     */   public int getFocusPotency(ItemStack itemstack)
/*     */   {
/* 386 */     if (getFocus(itemstack) == null) return 0;
/* 387 */     return getFocus(itemstack).getUpgradeLevel(getFocusStack(itemstack), FocusUpgradeType.potency) + (getRod(itemstack).hasPotencyBonus() ? 1 : 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getFocusTreasure(ItemStack itemstack)
/*     */   {
/* 393 */     if (getFocus(itemstack) == null) return 0;
/* 394 */     return getFocus(itemstack).getUpgradeLevel(getFocusStack(itemstack), FocusUpgradeType.treasure);
/*     */   }
/*     */   
/*     */   public int getFocusFrugal(ItemStack itemstack)
/*     */   {
/* 399 */     if (getFocus(itemstack) == null) return 0;
/* 400 */     return getFocus(itemstack).getUpgradeLevel(getFocusStack(itemstack), FocusUpgradeType.frugal);
/*     */   }
/*     */   
/*     */   public int getFocusEnlarge(ItemStack itemstack)
/*     */   {
/* 405 */     if (getFocus(itemstack) == null) return 0;
/* 406 */     return getFocus(itemstack).getUpgradeLevel(getFocusStack(itemstack), FocusUpgradeType.enlarge);
/*     */   }
/*     */   
/*     */   public int getFocusExtend(ItemStack itemstack)
/*     */   {
/* 411 */     if (getFocus(itemstack) == null) return 0;
/* 412 */     return getFocus(itemstack).getUpgradeLevel(getFocusStack(itemstack), FocusUpgradeType.extend);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 421 */     return EnumRarity.UNCOMMON;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 427 */     ItemStack w1 = new ItemStack(this);
/* 428 */     ItemStack w2 = new ItemStack(this);
/* 429 */     ItemStack w3 = new ItemStack(this);
/* 430 */     ((IWand)w2.func_77973_b()).setCap(w2, ConfigItems.WAND_CAP_GOLD);
/* 431 */     ((IWand)w3.func_77973_b()).setCap(w3, ConfigItems.WAND_CAP_THAUMIUM);
/* 432 */     ((IWand)w2.func_77973_b()).setRod(w2, ConfigItems.WAND_ROD_GREATWOOD);
/* 433 */     ((IWand)w3.func_77973_b()).setRod(w3, ConfigItems.WAND_ROD_SILVERWOOD);
/*     */     
/* 435 */     ItemStack sceptre = new ItemStack(this);
/* 436 */     ((IWand)sceptre.func_77973_b()).setCap(sceptre, ConfigItems.WAND_CAP_THAUMIUM);
/* 437 */     ((IWand)sceptre.func_77973_b()).setRod(sceptre, ConfigItems.WAND_ROD_SILVERWOOD);
/* 438 */     sceptre.func_77983_a("sceptre", new NBTTagByte((byte)1));
/*     */     
/* 440 */     ItemStack staff = new ItemStack(this);
/* 441 */     ((IWand)staff.func_77973_b()).setCap(staff, ConfigItems.WAND_CAP_VOID);
/* 442 */     ((IWand)staff.func_77973_b()).setRod(staff, ConfigItems.STAFF_ROD_PRIMAL);
/*     */     
/* 444 */     for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 445 */       ((IWand)w1.func_77973_b()).addVis(w1, aspect, ((IWand)w1.func_77973_b()).getMaxVis(w1), true);
/* 446 */       ((IWand)w2.func_77973_b()).addVis(w2, aspect, ((IWand)w2.func_77973_b()).getMaxVis(w2), true);
/* 447 */       ((IWand)w3.func_77973_b()).addVis(w3, aspect, ((IWand)w3.func_77973_b()).getMaxVis(w3), true);
/* 448 */       ((IWand)sceptre.func_77973_b()).addVis(sceptre, aspect, ((IWand)sceptre.func_77973_b()).getMaxVis(sceptre), true);
/* 449 */       ((IWand)staff.func_77973_b()).addVis(staff, aspect, ((IWand)staff.func_77973_b()).getMaxVis(staff), true);
/*     */     }
/* 451 */     par3List.add(w1);
/* 452 */     par3List.add(w2);
/* 453 */     par3List.add(w3);
/* 454 */     par3List.add(sceptre);
/* 455 */     par3List.add(staff);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String func_77653_i(ItemStack is)
/*     */   {
/* 462 */     String name = StatCollector.func_74838_a("item.Wand.name");
/* 463 */     name = name.replace("%CAP", StatCollector.func_74838_a("item.Wand." + getCap(is).getTag() + ".cap"));
/* 464 */     String rod = getRod(is).getTag();
/* 465 */     if (rod.indexOf("_staff") >= 0) rod = rod.substring(0, getRod(is).getTag().indexOf("_staff"));
/* 466 */     name = name.replace("%ROD", StatCollector.func_74838_a("item.Wand." + rod + ".rod"));
/* 467 */     name = name.replace("%OBJ", isSceptre(is) ? StatCollector.func_74838_a("item.Wand.sceptre.obj") : isStaff(is) ? StatCollector.func_74838_a("item.Wand.staff.obj") : StatCollector.func_74838_a("item.Wand.wand.obj"));
/*     */     
/*     */ 
/*     */ 
/* 471 */     return name;
/*     */   }
/*     */   
/* 474 */   DecimalFormat myFormatter = new DecimalFormat("#######.#");
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 478 */     int pos = list.size();
/* 479 */     String tt2 = "";
/* 480 */     if (stack.func_77942_o()) {
/* 481 */       String tt = "";
/* 482 */       int tot = 0;
/* 483 */       int num = 0;
/* 484 */       for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 485 */         if (stack.func_77978_p().func_74764_b(aspect.getTag()))
/*     */         {
/* 487 */           String amount = "" + getVis(stack, aspect);
/* 488 */           float mod = getConsumptionModifier(stack, player, aspect, false);
/* 489 */           String consumption = this.myFormatter.format(mod * 100.0F);
/* 490 */           num++;
/* 491 */           tot = (int)(tot + mod * 100.0F);
/* 492 */           String text = "";
/* 493 */           ItemStack focus = getFocusStack(stack);
/* 494 */           if (focus != null) {
/* 495 */             float amt = ((ItemFocusBasic)focus.func_77973_b()).getVisCost(focus).getAmount(aspect) * mod;
/* 496 */             if (amt > 0.0F) {
/* 497 */               text = "§r, " + this.myFormatter.format(amt) + " " + StatCollector.func_74838_a(((ItemFocusBasic)focus.func_77973_b()).isVisCostPerTick(focus) ? "item.Focus.cost2" : "item.Focus.cost1");
/*     */             }
/*     */           }
/*     */           
/* 501 */           if (Thaumcraft.proxy.isShiftKeyDown()) {
/* 502 */             list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount + ", §o(" + consumption + "% " + StatCollector.func_74838_a("tc.vis.cost") + ")" + text);
/*     */           }
/*     */           else {
/* 505 */             if (tt.length() > 0) tt = tt + " | ";
/* 506 */             tt = tt + "§" + aspect.getChatcolor() + amount + "§r";
/*     */           }
/*     */         }
/*     */       }
/* 510 */       if ((!Thaumcraft.proxy.isShiftKeyDown()) && (num > 0)) {
/* 511 */         list.add(tt);
/* 512 */         tot /= num;
/* 513 */         tt2 = " (" + tot + "% " + StatCollector.func_74838_a("tc.vis.costavg") + ")";
/*     */       }
/*     */     }
/* 516 */     list.add(pos, EnumChatFormatting.GOLD + StatCollector.func_74838_a("item.capacity.text") + " " + getMaxVis(stack) + "§r" + tt2);
/* 517 */     if (getCap(stack).getChargeBonus() > 0) {
/* 518 */       list.add(pos + 1, EnumChatFormatting.AQUA + StatCollector.func_74838_a("item.chargebonus.text") + " +" + getCap(stack).getChargeBonus() + "§r");
/*     */     }
/*     */     
/* 521 */     if (getFocus(stack) != null) {
/* 522 */       list.add(EnumChatFormatting.BOLD + "" + EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GREEN + getFocus(stack).func_77653_i(getFocusStack(stack)));
/*     */       
/* 524 */       if (Thaumcraft.proxy.isShiftKeyDown()) {
/* 525 */         getFocus(stack).addFocusInformation(getFocusStack(stack), player, list, par4);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77663_a(ItemStack is, World w, Entity e, int slot, boolean currentItem)
/*     */   {
/* 533 */     if ((!w.field_72995_K) && ((e instanceof EntityPlayer)))
/*     */     {
/* 535 */       EntityPlayer player = (EntityPlayer)e;
/*     */       
/* 537 */       if (getRod(is).getOnUpdate() != null) {
/* 538 */         getRod(is).getOnUpdate().onUpdate(is, player);
/*     */       }
/*     */       
/* 541 */       if (player.field_70173_aa % 5 == 0) {
/* 542 */         int cr = WandManager.getBaseChargeRate(player, currentItem, slot);
/* 543 */         if (cr > 0) { handleRecharge(player.field_70170_p, is, new BlockPos(player), player, cr);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
/*     */   {
/* 553 */     IBlockState bs = world.func_180495_p(pos);
/*     */     
/* 555 */     boolean result = false;
/*     */     
/* 557 */     if (((bs.func_177230_c() instanceof IWandable)) && 
/* 558 */       (((IWandable)bs.func_177230_c()).onWandRightClick(world, itemstack, player, pos, side))) {
/* 559 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 563 */     TileEntity tile = world.func_175625_s(pos);
/* 564 */     if ((tile != null) && ((tile instanceof IWandable)) && 
/* 565 */       (((IWandable)tile).onWandRightClick(world, itemstack, player, pos, side))) {
/* 566 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 570 */     if (WandTriggerRegistry.hasTrigger(bs)) {
/* 571 */       return WandTriggerRegistry.performTrigger(world, itemstack, player, pos, side, bs);
/*     */     }
/*     */     
/* 574 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*     */   {
/* 581 */     MovingObjectPosition movingobjectposition = func_77621_a(world, player, true);
/*     */     
/*     */ 
/* 584 */     ItemFocusBasic focus = getFocus(itemstack);
/* 585 */     if ((focus != null) && (!focus.isVisCostPerTick(getFocusStack(itemstack))) && (!WandManager.isOnCooldown(player)) && (consumeAllVis(itemstack, player, focus.getVisCost(itemstack), false, false)))
/*     */     {
/*     */ 
/* 588 */       WandManager.setCooldown(player, focus.getActivationCooldown(getFocusStack(itemstack)));
/* 589 */       if (focus.onFocusActivation(itemstack, world, player, movingobjectposition, 0)) {
/* 590 */         consumeAllVis(itemstack, player, focus.getVisCost(itemstack), !player.field_70170_p.field_72995_K, false);
/*     */       }
/*     */       
/* 593 */       return itemstack;
/*     */     }
/*     */     
/* 596 */     if ((focus != null) && (focus.isVisCostPerTick(getFocusStack(itemstack))) && (!WandManager.isOnCooldown(player))) {
/* 597 */       player.func_71008_a(itemstack, Integer.MAX_VALUE);
/* 598 */       WandManager.setCooldown(player, -1);
/* 599 */       return itemstack;
/*     */     }
/*     */     
/* 602 */     return super.func_77659_a(itemstack, world, player);
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/* 607 */     IWandable tv = getObjectInUse(stack, player.field_70170_p);
/* 608 */     if (tv != null)
/*     */     {
/* 610 */       tv.onUsingWandTick(stack, player, count);
/*     */     }
/*     */     else {
/* 613 */       ItemFocusBasic focus = getFocus(stack);
/* 614 */       if ((focus != null) && (!WandManager.isOnCooldown(player))) {
/* 615 */         if (consumeAllVis(stack, player, focus.getVisCost(stack), false, false)) {
/* 616 */           MovingObjectPosition movingobjectposition = func_77621_a(player.field_70170_p, player, true);
/* 617 */           WandManager.setCooldown(player, focus.getActivationCooldown(getFocusStack(stack)));
/* 618 */           if (focus.onFocusActivation(stack, player.field_70170_p, player, movingobjectposition, count)) {
/* 619 */             consumeAllVis(stack, player, focus.getVisCost(stack), !player.field_70170_p.field_72995_K, false);
/*     */           }
/*     */         } else {
/* 622 */           player.func_71034_by();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setObjectInUse(ItemStack stack, int x, int y, int z) {
/* 629 */     if (stack.func_77978_p() == null) stack.func_77982_d(new NBTTagCompound());
/* 630 */     stack.func_77978_p().func_74768_a("IIUX", x);
/* 631 */     stack.func_77978_p().func_74768_a("IIUY", y);
/* 632 */     stack.func_77978_p().func_74768_a("IIUZ", z);
/*     */   }
/*     */   
/*     */   public void clearObjectInUse(ItemStack stack) {
/* 636 */     if (stack.func_77978_p() == null) stack.func_77982_d(new NBTTagCompound());
/* 637 */     stack.func_77978_p().func_82580_o("IIUX");
/* 638 */     stack.func_77978_p().func_82580_o("IIUY");
/* 639 */     stack.func_77978_p().func_82580_o("IIUZ");
/*     */   }
/*     */   
/*     */   public IWandable getObjectInUse(ItemStack stack, World world) {
/* 643 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("IIUX"))) {
/* 644 */       TileEntity te = world.func_175625_s(new BlockPos(stack.func_77978_p().func_74762_e("IIUX"), stack.func_77978_p().func_74762_e("IIUY"), stack.func_77978_p().func_74762_e("IIUZ")));
/*     */       
/*     */ 
/*     */ 
/* 648 */       if ((te != null) && ((te instanceof IWandable))) {
/* 649 */         return (IWandable)te;
/*     */       }
/*     */     }
/* 652 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int count)
/*     */   {
/* 662 */     IWandable tv = getObjectInUse(stack, player.field_70170_p);
/* 663 */     if (tv != null) {
/* 664 */       tv.onWandStoppedUsing(stack, world, player, count);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 673 */     clearObjectInUse(stack);
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumAction func_77661_b(ItemStack par1ItemStack)
/*     */   {
/* 679 */     return EnumAction.BOW;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77626_a(ItemStack itemstack)
/*     */   {
/* 685 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
/*     */   {
/* 690 */     ItemStack focus = getFocusStack(stack);
/* 691 */     if ((focus != null) && (!WandManager.isOnCooldown(entityLiving))) {
/* 692 */       WandManager.setCooldown(entityLiving, getFocus(stack).getActivationCooldown(focus));
/* 693 */       return focus.func_77973_b().onEntitySwing(entityLiving, stack);
/*     */     }
/* 695 */     return super.onEntitySwing(entityLiving, stack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
/*     */   {
/* 702 */     ItemFocusBasic focus = getFocus(itemstack);
/* 703 */     if ((focus != null) && (!WandManager.isOnCooldown(player))) {
/* 704 */       WandManager.setCooldown(player, focus.getActivationCooldown(getFocusStack(itemstack)));
/* 705 */       return focus.onFocusBlockStartBreak(itemstack, pos, player);
/*     */     }
/*     */     
/* 708 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canHarvestBlock(Block par1Block, ItemStack itemstack)
/*     */   {
/* 714 */     ItemFocusBasic focus = getFocus(itemstack);
/* 715 */     if (focus != null) {
/* 716 */       return getFocusStack(itemstack).func_77973_b().canHarvestBlock(par1Block, itemstack);
/*     */     }
/* 718 */     return false;
/*     */   }
/*     */   
/*     */   public float func_150893_a(ItemStack stack, Block block)
/*     */   {
/* 723 */     ItemFocusBasic focus = getFocus(stack);
/* 724 */     if (focus != null) {
/* 725 */       return getFocusStack(stack).func_77973_b().func_150893_a(stack, null);
/*     */     }
/* 727 */     return super.func_150893_a(stack, block);
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList<BlockPos> getArchitectBlocks(ItemStack stack, World world, BlockPos pos, EnumFacing side, EntityPlayer player)
/*     */   {
/* 733 */     ItemFocusBasic focus = getFocus(stack);
/* 734 */     if ((focus != null) && ((focus instanceof IArchitect))) {
/* 735 */       return ((IArchitect)focus).getArchitectBlocks(stack, world, pos, side, player);
/*     */     }
/* 737 */     return null;
/*     */   }
/*     */   
/*     */   public MovingObjectPosition getArchitectMOP(ItemStack stack, World world, EntityLivingBase player)
/*     */   {
/* 742 */     ItemFocusBasic focus = getFocus(stack);
/* 743 */     if ((focus != null) && ((focus instanceof IArchitectExtended))) {
/* 744 */       return ((IArchitectExtended)focus).getArchitectMOP(stack, world, player);
/*     */     }
/* 746 */     return null;
/*     */   }
/*     */   
/*     */   public boolean useBlockHighlight(ItemStack stack)
/*     */   {
/* 751 */     ItemFocusBasic focus = getFocus(stack);
/* 752 */     if ((focus != null) && ((focus instanceof IArchitectExtended))) {
/* 753 */       return ((IArchitectExtended)focus).useBlockHighlight(stack);
/*     */     }
/* 755 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, EnumFacing side, IArchitect.EnumAxis axis)
/*     */   {
/* 761 */     ItemFocusBasic focus = getFocus(stack);
/* 762 */     if ((focus != null) && ((focus instanceof IArchitect)) && (focus.isUpgradedWith(getFocusStack(stack), FocusUpgradeType.architect)))
/*     */     {
/* 764 */       return ((IArchitect)focus).showAxis(stack, world, player, side, axis);
/*     */     }
/* 766 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_82790_a(ItemStack stack, int renderPass)
/*     */   {
/* 772 */     if ((renderPass == 1) && (getFocus(stack) != null)) {
/* 773 */       return getFocus(stack).getFocusColor(stack);
/*     */     }
/* 775 */     return super.func_82790_a(stack, renderPass);
/*     */   }
/*     */   
/*     */   public float getChargeLevel(ItemStack is)
/*     */   {
/* 780 */     return -1.0F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/ItemWand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */