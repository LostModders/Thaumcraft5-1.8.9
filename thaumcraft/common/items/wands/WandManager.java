/*     */ package thaumcraft.common.items.wands;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import java.util.HashMap;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.items.IArchitect;
/*     */ import thaumcraft.api.items.IVisDiscountGear;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.api.research.ResearchHelper;
/*     */ import thaumcraft.api.wands.IWand;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*     */ 
/*     */ public class WandManager
/*     */ {
/*  26 */   public WandTriggers triggers = new WandTriggers();
/*     */   
/*     */   public static int getBaseChargeRate(EntityPlayer player, boolean currentItem, int slot) {
/*  29 */     return currentItem ? 1 : (ResearchHelper.isResearchComplete(player.func_70005_c_(), "NODETAPPER1")) && (slot < 9) ? 2 : (ResearchHelper.isResearchComplete(player.func_70005_c_(), "NODETAPPER2")) && (slot < 9) ? 3 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public static float getTotalVisDiscount(EntityPlayer player, Aspect aspect)
/*     */   {
/*  35 */     int total = 0;
/*  36 */     if (player == null) { return 0.0F;
/*     */     }
/*  38 */     IInventory baubles = BaublesApi.getBaubles(player);
/*  39 */     for (int a = 0; a < 4; a++) {
/*  40 */       if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof IVisDiscountGear)))
/*     */       {
/*  42 */         total += ((IVisDiscountGear)baubles.func_70301_a(a).func_77973_b()).getVisDiscount(baubles.func_70301_a(a), player, aspect);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  47 */     for (int a = 0; a < 4; a++) {
/*  48 */       if ((player.field_71071_by.func_70440_f(a) != null) && ((player.field_71071_by.func_70440_f(a).func_77973_b() instanceof IVisDiscountGear))) {
/*  49 */         total += ((IVisDiscountGear)player.field_71071_by.func_70440_f(a).func_77973_b()).getVisDiscount(player.field_71071_by.func_70440_f(a), player, aspect);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  54 */     if ((player.func_70644_a(PotionVisExhaust.instance)) || (player.func_70644_a(PotionInfectiousVisExhaust.instance)))
/*     */     {
/*  56 */       int level1 = 0;
/*  57 */       int level2 = 0;
/*  58 */       if (player.func_70644_a(PotionVisExhaust.instance)) {
/*  59 */         level1 = player.func_70660_b(PotionVisExhaust.instance).func_76458_c();
/*     */       }
/*  61 */       if (player.func_70644_a(PotionInfectiousVisExhaust.instance)) {
/*  62 */         level2 = player.func_70660_b(PotionInfectiousVisExhaust.instance).func_76458_c();
/*     */       }
/*  64 */       total -= (Math.max(level1, level2) + 1) * 10;
/*     */     }
/*     */     
/*  67 */     return total / 100.0F;
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
/*     */   public static boolean consumeVisFromInventory(EntityPlayer player, AspectList cost)
/*     */   {
/*  81 */     for (int a = player.field_71071_by.field_70462_a.length - 1; a >= 0; a--) {
/*  82 */       ItemStack item = player.field_71071_by.field_70462_a[a];
/*  83 */       if ((item != null) && ((item.func_77973_b() instanceof IWand))) {
/*  84 */         boolean done = ((IWand)item.func_77973_b()).consumeAllVis(item, player, cost, true, true);
/*  85 */         if (done) return true;
/*     */       }
/*     */     }
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public static void changeFocus(ItemStack is, World w, EntityPlayer player, String focus) {
/*  92 */     IWand wand = (IWand)is.func_77973_b();
/*  93 */     TreeMap<String, Integer> foci = new TreeMap();
/*  94 */     HashMap<Integer, Integer> pouches = new HashMap();
/*  95 */     int pouchcount = 0;
/*  96 */     ItemStack item = null;
/*     */     
/*     */ 
/*  99 */     IInventory baubles = BaublesApi.getBaubles(player);
/* 100 */     for (int a = 0; a < 4; a++) {
/* 101 */       if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof ItemFocusPouch)))
/*     */       {
/* 103 */         pouchcount++;
/* 104 */         item = baubles.func_70301_a(a);
/* 105 */         pouches.put(Integer.valueOf(pouchcount), Integer.valueOf(a - 4));
/* 106 */         ItemStack[] inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/* 107 */         for (int q = 0; q < inv.length; q++) {
/* 108 */           item = inv[q];
/* 109 */           if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/* 110 */             foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(q + pouchcount * 1000));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 117 */     for (int a = 0; a < 36; a++) {
/* 118 */       item = player.field_71071_by.field_70462_a[a];
/* 119 */       if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/* 120 */         foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(a));
/*     */       }
/* 122 */       if ((item != null) && ((item.func_77973_b() instanceof ItemFocusPouch))) {
/* 123 */         pouchcount++;
/* 124 */         pouches.put(Integer.valueOf(pouchcount), Integer.valueOf(a));
/* 125 */         ItemStack[] inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/* 126 */         for (int q = 0; q < inv.length; q++) {
/* 127 */           item = inv[q];
/* 128 */           if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/* 129 */             foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(q + pouchcount * 1000));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 135 */     if ((focus.equals("REMOVE")) || (foci.size() == 0)) {
/* 136 */       if ((wand.getFocus(is) != null) && (
/* 137 */         (addFocusToPouch(player, wand.getFocusStack(is).func_77946_l(), pouches)) || (player.field_71071_by.func_70441_a(wand.getFocusStack(is).func_77946_l()))))
/*     */       {
/* 139 */         wand.setFocus(is, null);
/* 140 */         w.func_72956_a(player, "thaumcraft:cameraticks", 0.3F, 0.9F);
/*     */       }
/*     */       
/* 143 */       return; }
/* 144 */     if ((foci != null) && (foci.size() > 0) && (focus != null))
/*     */     {
/*     */ 
/* 147 */       String newkey = focus;
/* 148 */       if (foci.get(newkey) == null) newkey = (String)foci.higherKey(newkey);
/* 149 */       if ((newkey == null) || (foci.get(newkey) == null)) { newkey = (String)foci.firstKey();
/*     */       }
/* 151 */       if ((((Integer)foci.get(newkey)).intValue() < 1000) && (((Integer)foci.get(newkey)).intValue() >= 0)) {
/* 152 */         item = player.field_71071_by.field_70462_a[((Integer)foci.get(newkey)).intValue()].func_77946_l();
/*     */       } else {
/* 154 */         int pid = ((Integer)foci.get(newkey)).intValue() / 1000;
/* 155 */         if (pouches.containsKey(Integer.valueOf(pid))) {
/* 156 */           int pouchslot = ((Integer)pouches.get(Integer.valueOf(pid))).intValue();
/* 157 */           int focusslot = ((Integer)foci.get(newkey)).intValue() - pid * 1000;
/* 158 */           ItemStack tmp = null;
/* 159 */           if (pouchslot >= 0) {
/* 160 */             tmp = player.field_71071_by.field_70462_a[pouchslot].func_77946_l();
/*     */           } else {
/* 162 */             tmp = baubles.func_70301_a(pouchslot + 4).func_77946_l();
/*     */           }
/* 164 */           item = fetchFocusFromPouch(player, focusslot, tmp, pouchslot);
/*     */         }
/*     */       }
/*     */       
/* 168 */       if (item != null) {
/* 169 */         if ((((Integer)foci.get(newkey)).intValue() < 1000) && (((Integer)foci.get(newkey)).intValue() >= 0)) {
/* 170 */           player.field_71071_by.func_70299_a(((Integer)foci.get(newkey)).intValue(), null);
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/* 175 */         return;
/*     */       }
/*     */       
/* 178 */       w.func_72956_a(player, "thaumcraft:cameraticks", 0.3F, 1.0F);
/*     */       
/*     */ 
/* 181 */       if ((wand.getFocus(is) != null) && (
/* 182 */         (addFocusToPouch(player, wand.getFocusStack(is).func_77946_l(), pouches)) || (player.field_71071_by.func_70441_a(wand.getFocusStack(is).func_77946_l()))))
/*     */       {
/* 184 */         wand.setFocus(is, null);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 189 */       if (wand.getFocus(is) == null) {
/* 190 */         wand.setFocus(is, item);
/*     */       }
/* 192 */       else if (!addFocusToPouch(player, item, pouches)) {
/* 193 */         player.field_71071_by.func_70441_a(item);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static ItemStack fetchFocusFromPouch(EntityPlayer player, int focusid, ItemStack pouch, int pouchslot)
/*     */   {
/* 200 */     ItemStack focus = null;
/* 201 */     ItemStack[] inv = ((ItemFocusPouch)pouch.func_77973_b()).getInventory(pouch);
/* 202 */     ItemStack contents = inv[focusid];
/* 203 */     if ((contents != null) && ((contents.func_77973_b() instanceof ItemFocusBasic))) {
/* 204 */       focus = contents.func_77946_l();
/* 205 */       inv[focusid] = null;
/* 206 */       ((ItemFocusPouch)pouch.func_77973_b()).setInventory(pouch, inv);
/* 207 */       if (pouchslot >= 0) {
/* 208 */         player.field_71071_by.func_70299_a(pouchslot, pouch);
/* 209 */         player.field_71071_by.func_70296_d();
/*     */       } else {
/* 211 */         IInventory baubles = BaublesApi.getBaubles(player);
/* 212 */         baubles.func_70299_a(pouchslot + 4, pouch);
/* 213 */         baubles.func_70296_d();
/*     */       }
/*     */     }
/* 216 */     return focus;
/*     */   }
/*     */   
/*     */   private static boolean addFocusToPouch(EntityPlayer player, ItemStack focus, HashMap<Integer, Integer> pouches)
/*     */   {
/* 221 */     IInventory baubles = BaublesApi.getBaubles(player);
/* 222 */     for (Integer pouchslot : pouches.values()) { ItemStack pouch;
/* 223 */       ItemStack pouch; if (pouchslot.intValue() >= 0) {
/* 224 */         pouch = player.field_71071_by.field_70462_a[pouchslot.intValue()];
/*     */       } else {
/* 226 */         pouch = baubles.func_70301_a(pouchslot.intValue() + 4);
/*     */       }
/* 228 */       ItemStack[] inv = ((ItemFocusPouch)pouch.func_77973_b()).getInventory(pouch);
/*     */       
/* 230 */       for (int q = 0; q < inv.length; q++) {
/* 231 */         ItemStack contents = inv[q];
/* 232 */         if (contents == null) {
/* 233 */           inv[q] = focus.func_77946_l();
/* 234 */           ((ItemFocusPouch)pouch.func_77973_b()).setInventory(pouch, inv);
/* 235 */           if (pouchslot.intValue() >= 0) {
/* 236 */             player.field_71071_by.func_70299_a(pouchslot.intValue(), pouch);
/* 237 */             player.field_71071_by.func_70296_d();
/*     */           } else {
/* 239 */             baubles.func_70299_a(pouchslot.intValue() + 4, pouch);
/* 240 */             baubles.func_70296_d();
/*     */           }
/* 242 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 246 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void toggleMisc(ItemStack itemstack, World world, EntityPlayer player)
/*     */   {
/* 252 */     if (!(itemstack.func_77973_b() instanceof IWand)) return;
/* 253 */     IWand wand = (IWand)itemstack.func_77973_b();
/* 254 */     if ((wand.getFocus(itemstack) != null) && ((wand.getFocus(itemstack) instanceof IArchitect)) && (wand.getFocus(itemstack).isUpgradedWith(wand.getFocusStack(itemstack), thaumcraft.api.wands.FocusUpgradeType.architect)))
/*     */     {
/* 256 */       int dim = getAreaDim(itemstack);
/* 257 */       IArchitect fa = (IArchitect)wand.getFocus(itemstack);
/* 258 */       if (player.func_70093_af()) {
/* 259 */         dim++;
/* 260 */         if (dim > ((wand.getFocusStack(itemstack).func_77973_b() instanceof thaumcraft.common.items.wands.foci.ItemFocusTrade) ? 2 : 3)) dim = 0;
/* 261 */         setAreaDim(itemstack, dim);
/*     */       } else {
/* 263 */         int areax = getAreaX(itemstack);
/* 264 */         int areay = getAreaY(itemstack);
/* 265 */         int areaz = getAreaZ(itemstack);
/* 266 */         if (dim == 0) {
/* 267 */           areax++;
/* 268 */           areaz++;
/* 269 */           areay++;
/* 270 */         } else if (dim == 1) {
/* 271 */           areax++;
/* 272 */         } else if (dim == 2) {
/* 273 */           areaz++;
/* 274 */         } else if (dim == 3) {
/* 275 */           areay++;
/*     */         }
/* 277 */         if (areax > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusStack(itemstack))) {
/* 278 */           areax = 0;
/*     */         }
/* 280 */         if (areaz > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusStack(itemstack))) {
/* 281 */           areaz = 0;
/*     */         }
/* 283 */         if (areay > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusStack(itemstack))) {
/* 284 */           areay = 0;
/*     */         }
/* 286 */         setAreaX(itemstack, areax);
/* 287 */         setAreaY(itemstack, areay);
/* 288 */         setAreaZ(itemstack, areaz);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getAreaDim(ItemStack stack)
/*     */   {
/* 295 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("aread"))) {
/* 296 */       return stack.func_77978_p().func_74762_e("aread");
/*     */     }
/* 298 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getAreaX(ItemStack stack) {
/* 302 */     IWand wand = (IWand)stack.func_77973_b();
/* 303 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("areax"))) {
/* 304 */       int a = stack.func_77978_p().func_74762_e("areax");
/* 305 */       if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack))) {
/* 306 */         a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack));
/*     */       }
/* 308 */       return a;
/*     */     }
/* 310 */     return wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack));
/*     */   }
/*     */   
/*     */   public static int getAreaY(ItemStack stack) {
/* 314 */     IWand wand = (IWand)stack.func_77973_b();
/* 315 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("areay"))) {
/* 316 */       int a = stack.func_77978_p().func_74762_e("areay");
/* 317 */       if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack))) {
/* 318 */         a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack));
/*     */       }
/* 320 */       return a;
/*     */     }
/* 322 */     return wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack));
/*     */   }
/*     */   
/*     */   public static int getAreaZ(ItemStack stack) {
/* 326 */     IWand wand = (IWand)stack.func_77973_b();
/* 327 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("areaz"))) {
/* 328 */       int a = stack.func_77978_p().func_74762_e("areaz");
/* 329 */       if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack))) {
/* 330 */         a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack));
/*     */       }
/* 332 */       return a;
/*     */     }
/* 334 */     return wand.getFocus(stack).getMaxAreaSize(wand.getFocusStack(stack));
/*     */   }
/*     */   
/*     */   public static void setAreaX(ItemStack stack, int area) {
/* 338 */     if (stack.func_77942_o()) {
/* 339 */       stack.func_77978_p().func_74768_a("areax", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaY(ItemStack stack, int area) {
/* 344 */     if (stack.func_77942_o()) {
/* 345 */       stack.func_77978_p().func_74768_a("areay", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaZ(ItemStack stack, int area) {
/* 350 */     if (stack.func_77942_o()) {
/* 351 */       stack.func_77978_p().func_74768_a("areaz", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaDim(ItemStack stack, int dim) {
/* 356 */     if (stack.func_77942_o()) {
/* 357 */       stack.func_77978_p().func_74768_a("aread", dim);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 362 */   static HashMap<Integer, Long> cooldownServer = new HashMap();
/* 363 */   static HashMap<Integer, Long> cooldownClient = new HashMap();
/*     */   
/*     */   static boolean isOnCooldown(EntityLivingBase entityLiving) {
/* 366 */     if ((entityLiving.field_70170_p.field_72995_K) && (cooldownClient.containsKey(Integer.valueOf(entityLiving.func_145782_y())))) {
/* 367 */       return ((Long)cooldownClient.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() > System.currentTimeMillis();
/*     */     }
/* 369 */     if ((!entityLiving.field_70170_p.field_72995_K) && (cooldownServer.containsKey(Integer.valueOf(entityLiving.func_145782_y())))) {
/* 370 */       return ((Long)cooldownServer.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() > System.currentTimeMillis();
/*     */     }
/* 372 */     return false;
/*     */   }
/*     */   
/*     */   public static float getCooldown(EntityLivingBase entityLiving) {
/* 376 */     if ((entityLiving.field_70170_p.field_72995_K) && (cooldownClient.containsKey(Integer.valueOf(entityLiving.func_145782_y())))) {
/* 377 */       return (float)(((Long)cooldownClient.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() - System.currentTimeMillis()) / 1000.0F;
/*     */     }
/* 379 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static void setCooldown(EntityLivingBase entityLiving, int cd) {
/* 383 */     if (cd == 0) {
/* 384 */       cooldownClient.remove(Integer.valueOf(entityLiving.func_145782_y()));
/* 385 */       cooldownServer.remove(Integer.valueOf(entityLiving.func_145782_y()));
/*     */     }
/* 387 */     else if (entityLiving.field_70170_p.field_72995_K) {
/* 388 */       cooldownClient.put(Integer.valueOf(entityLiving.func_145782_y()), Long.valueOf(System.currentTimeMillis() + cd));
/*     */     } else {
/* 390 */       cooldownServer.put(Integer.valueOf(entityLiving.func_145782_y()), Long.valueOf(System.currentTimeMillis() + cd));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.8.9-5.2.4.jar!/thaumcraft/common/items/wands/WandManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */